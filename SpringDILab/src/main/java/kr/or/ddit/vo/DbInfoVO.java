package kr.or.ddit.vo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Component //Bean으로 등록
@Lazy
public class DbInfoVO {
	@Value("#{dbInfo1['main.driverClassName']}")
	private String driverClassName;
	private String url;
	private String user;
	private String password;
	private int initialSize;
	private int maxWait;
	private int maxTotal;
	
}
