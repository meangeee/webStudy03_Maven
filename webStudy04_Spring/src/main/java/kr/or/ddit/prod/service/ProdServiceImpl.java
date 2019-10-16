package kr.or.ddit.prod.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.exception.CommonException;
import kr.or.ddit.prod.dao.IProdDAO;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.ProdVO;

@Service
public class ProdServiceImpl implements IProdService {
	@Inject
	private IProdDAO dao;

	// 이 부분 몰라요
	@Inject
	WebApplicationContext container;
	ServletContext application;
	String saveFolderURL = "/prodImages";
	File saveFolder;

	@PostConstruct
	public void init() {
		application = container.getServletContext();
		String saveFolerPath = application.getRealPath(saveFolderURL);
		saveFolder = new File(saveFolerPath);
		if (!saveFolder.exists())
			saveFolder.mkdirs();
	}

	@Override
	public ServiceResult createProd(ProdVO prod) {
		ServiceResult result = null;
		int cnt = dao.insertProd(prod);
		if (cnt > 0) {
			try {
				prod.transfer(saveFolder);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e); // 이거 요ㅐ요?
			}
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public int retrievevProdCount(PagingInfoVO pagingVO) {
		return dao.selectProdCount(pagingVO);
	}

	@Override
	public List<ProdVO> retrieveProdList(PagingInfoVO pagingVO) {
		return dao.selectProdList(pagingVO);
	}

	@Override
	public ProdVO retrieveProd(String prod_id) {
		ProdVO prod = dao.selectProd(prod_id);
		if (prod == null)
			throw new CommonException(prod_id + " 상품이 없음.");
		return prod;
	}

	@Override
	public ServiceResult modifyProd(ProdVO prod) {
		retrieveProd(prod.getProd_id());
		ServiceResult result = null;
		int cnt = dao.updateProd(prod);
		if (cnt > 0) {
			try {
				prod.transfer(saveFolder);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e); // 이거 요ㅐ요?
			}
			result = ServiceResult.OK;
		} else
			result = ServiceResult.FAILED;
		return result;

	}

}
