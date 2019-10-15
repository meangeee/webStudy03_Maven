package kr.or.ddit.vo;

import java.io.File;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor // constructor injection
@NoArgsConstructor
@Data				// setter injection
public class VariousDIVO {
	private int num;
	private char ch;
	private boolean bool;
	private String str;
	
	private File file;
	
}
