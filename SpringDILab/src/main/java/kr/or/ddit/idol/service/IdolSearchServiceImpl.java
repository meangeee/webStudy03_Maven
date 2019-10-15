package kr.or.ddit.idol.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import kr.or.ddit.idol.dao.IIdolSearchDAO;
import kr.or.ddit.idol.dao.IdolDAOFactory;
import kr.or.ddit.idol.dao.IdolSearchDAO_Mysql;
import kr.or.ddit.idol.dao.IdolSearchDAO_Oracle;
@Service("idolService")
@Scope("prototype")
public class IdolSearchServiceImpl implements IIdolSearchService {
	//결합력을 낮춰보자
//	1. 전통적인 의존관계 형성 : new 인스턴스 직접 생성 -> 결합력 최상.
//	IIdolSearchDAO dao = new IdolSearchDAO_Oracle();
//	IIdolSearchDAO dao = new IdolSearchDAO_Mysql();
//	2. Factory Object[Method] Pattern : factory 와 생성 객체 사이의 결합력 잔존.
//	IIdolSearchDAO dao = IdolDAOFactory.getIdolDAO();
//	3. DI 구조를 기반으로 한 strategy pattern : 전략 주입자 필요(결합력 전체에 대한 책임)
//	4. 전략 패턴을 확장한 DI container 구조
	private IIdolSearchDAO dao;
	
	
	
	public IdolSearchServiceImpl() {
		super();
		System.out.printf(" %s 객체 생성, 기본생성자 사용\n", getClass().getSimpleName());
	}
	
//	@Autowired //자동으로 의존관계 형성 = 자동주입
	@Inject
	public IdolSearchServiceImpl(IIdolSearchDAO dao) {
		super();
		this.dao = dao;
		System.out.printf(" %s 객체 생성, DAO를 주입받아 사용\n", getClass().getSimpleName());
	}
	//둘의 차이점 auto는 이미등록되어있는 bean중에 주입을 받지만 뭘 받는지 조건이 없음 그럴땐 파라미터 타입에 있는걸로 주입 단점 2개이상일때는 바로 터짐
	//둘의 차이점 뭐 근데 Resource가 더 나은 이유는 POJO? 
	//뭐가 없어서 setter injection에서 사용 그게 뭐가없다고요? name은 뭐에요 뭐 중복?
//	@Resource(type=IIdolSearchDAO.class, name="mysqlDAO") 
	public void setDao(IIdolSearchDAO dao) {
		this.dao = dao;
	}
	
	
	@Override
	public String readIdol(String code) {
		String[] idol = dao.selectIdol(code);
		//문자열로 바꿔야함 로우데이터를 가공함
		String information = Arrays.toString(idol)+new Date();
		return information;
	}

	@Override
	public List<String> readIdols() {
		List<String[]> list = dao.selectIdolList();
		//list를 대상으로 string element를 가지고 있는
		List<String> information = new ArrayList<>();
		for( String[] tmp : list) {
			information.add(Arrays.toString(tmp));
		}
		return information;
	}

}
