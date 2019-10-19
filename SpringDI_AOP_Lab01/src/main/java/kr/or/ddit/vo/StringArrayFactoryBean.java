package kr.or.ddit.vo;

import org.springframework.beans.factory.FactoryBean;

public class StringArrayFactoryBean implements FactoryBean<String[]>{

	@Override
	public String[] getObject() throws Exception {
		String[] array = new String[] {"array1", "array2"};
		return array;
	}

	@Override
	public Class<?> getObjectType() {
		return String[].class;
	}

	@Override
	public boolean isSingleton() {
		//singleton으로 변경해줌 false는 prototype
		return true;
	}

}
