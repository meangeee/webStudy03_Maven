package kr.or.ddit.vo;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class CollectionDIVO {
	private String[] array;
	private List<Object> list;
	private Map<Object, Object> map;
	private Set<Object> set;
	private Properties props;
	
}
