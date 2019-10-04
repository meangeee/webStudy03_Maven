package kr.or.ddit.wrapper;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.Part;

// 업로드 되고 있는 파일들만 처리
public class MultipartRequestWrapper extends HttpServletRequestWrapper {

   private Map<String, PartWrapper[]> filePartWrappertMap;
   
   public MultipartRequestWrapper(HttpServletRequest request) throws IOException, ServletException {
      super(request);
      filePartWrappertMap = new HashMap<>();
      parseRequest(request);
   }

   private void parseRequest(HttpServletRequest request) throws IOException, ServletException {
      // 문자파트 + 파일파트
      Collection<Part> parts = request.getParts();
      Iterator<Part> it =  parts.iterator();
      while (it.hasNext()) {
         Part part = (Part) it.next();
         String partName =  part.getName();
         // 문자파트 , 파일파트 구분을 위한 코드
         String disposition = part.getHeader("Content-Disposition");
         // 문자 파트 스킵하기 위한 조건문 (parameterMap 에 누적되어 있음)
         if(!disposition.contains("filename")) continue;
         if(part.getSize()<=0) continue;
         PartWrapper wrapper = new PartWrapper(part);
         PartWrapper[] array =  filePartWrappertMap.get(partName);
         PartWrapper[] target = null;
         // 기존의 배열이 있었는지, 없었는지 확인
         if(array==null) {
            target = new PartWrapper[] {wrapper};
         }else {
            target = new PartWrapper[array.length+1];
            // 배열 복사
            System.arraycopy(array, 0, target, 0, array.length);
            target[target.length-1] = wrapper;
         }
         filePartWrappertMap.put(partName, target);
      }
      
   }
   
   public PartWrapper getPartWrapper(String name){
      PartWrapper[] array = filePartWrappertMap.get(name);
      if(array != null && array.length >0 ) {
         return array[0];
      }else {
         return null;
      }
   }
   
   public PartWrapper[] getPartWrappers(String name){
      return filePartWrappertMap.get(name);
   }
   
   public Map<String, PartWrapper[]> getFilePartWrapperMap(){
      return filePartWrappertMap;
   }
   
   public void deleteAll() throws IOException {
      for(Entry<String, PartWrapper[]> entry : filePartWrappertMap.entrySet()) {
         PartWrapper[] array = entry.getValue();
         for(PartWrapper wrapper :array) {
            wrapper.delete();
         }
      }
   }
}