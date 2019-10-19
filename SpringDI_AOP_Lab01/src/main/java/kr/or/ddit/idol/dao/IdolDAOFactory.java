package kr.or.ddit.idol.dao;


public class IdolDAOFactory {
	public static IIdolSearchDAO getIdolDAO() {
//		return new IdolSearchDAO_Mysql();
		return new IdolSearchDAO_Oracle();
	}
}
