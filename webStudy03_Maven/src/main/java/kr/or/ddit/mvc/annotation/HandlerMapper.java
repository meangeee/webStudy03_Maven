package kr.or.ddit.mvc.annotation;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.utils.ReflectionUtils;

public class HandlerMapper implements IHandlerMapper{
   public Map<URIMappingCondition, URIMappingInfo> handlerMap;
   
   public HandlerMapper(String[] basePackages) {
      handlerMap = new LinkedHashMap<>();
      List<Class<?>> classes = new ArrayList<>();
      for(String basePkg: basePackages) {
         classes.addAll(ReflectionUtils.getClassesAtBasePackageWithAnnotation(basePkg.trim(), CommandHandler.class));
      }
      
      for(Class<?> tmp : classes) {
         List<Method> methods = ReflectionUtils.getMethodsAtClassWithAnnotation(tmp, URIMapping.class, String.class, HttpServletRequest.class, HttpServletResponse.class);
         
         Object handler;
         try {
            handler = tmp.newInstance();
            for(Method mtd : methods) {
               // URIMappingCondition : URIMappingInfo
               URIMapping mapping =  mtd.getAnnotation(URIMapping.class);
               URIMappingCondition key =  new URIMappingCondition(mapping.value(), mapping.method());
               URIMappingInfo info = new URIMappingInfo(key, handler, mtd);
               handlerMap.put(key, info);
               System.out.printf("%s 핸들러 : %s\n", key, info);
            }
         } catch (InstantiationException | IllegalAccessException e) {
            System.err.println(e.getMessage());
            continue;
         }
      }
   }

   @Override
   public URIMappingInfo findCommandHandler(HttpServletRequest req) {
      String uri = req.getRequestURI();
      uri = uri.substring(req.getContextPath().length());
      uri = uri.split(";")[0];
      HttpMethod method = HttpMethod.valueOf(req.getMethod().toUpperCase()); // 메소드가 없는 경우 예외발생해서 넘어간다.
      URIMappingCondition key = new URIMappingCondition(uri, method);
      return handlerMap.get(key);
   }
   
   
}