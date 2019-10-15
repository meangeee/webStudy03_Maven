package kr.or.ddit.idol.service;

import java.util.List;

public interface IIdolSearchService {
	public String readIdol(String code);
	public List<String> readIdols();
}
