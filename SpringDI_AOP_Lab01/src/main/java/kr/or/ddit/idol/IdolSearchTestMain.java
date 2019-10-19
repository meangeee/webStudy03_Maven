package kr.or.ddit.idol;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class IdolSearchTestMain {
	public static void main(String[] args) {
		
		Map<String, String[]> idolMap = new HashMap<String, String[]>();
		idolMap.put("I001", new String[] {"SES", "이진", "슈", "효리"});
		idolMap.put("I002", new String[] {"COOL", "유리", "김성수", "이재훈"});
		String code = "I002";
		String[] data = idolMap.get(code);
		System.out.println(Arrays.toString(data));
	}
}
