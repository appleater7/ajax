package service.Impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import dao.AddrDAO;
import dao.Impl.AddrDAOImpl;
import service.AddrService;
import utils.Command;

public class AddrServiceImpl implements AddrService {
	private AddrDAO adao = new AddrDAOImpl();
	private static final int PAGE_COUNT = 10;
	private static final int BLOCK_COUNT = 10;

	@Override
	public List<Map<String, String>> selectAddrList(HttpServletRequest request) {
		Map<String, String> paramMap = Command.getSingleMap(request);
		int page = 1;
		if (paramMap.get("page") != null) {
			page = Integer.parseInt(paramMap.get("page"));
		}
		request.setAttribute("page", page);
		int lNum = page * PAGE_COUNT;
		int sNum = lNum - (PAGE_COUNT-1);
		paramMap.put("lNum", lNum+"" );
		paramMap.put("sNum", sNum+ "");
		List<Map<String,String>> addrList = adao.selectAddrList(paramMap);
		request.setAttribute("list", addrList);
		
		int totalCnt = adao.selectTotalAddrCnt();
		request.setAttribute("totalCnt", totalCnt);
		int totalPageCnt = totalCnt/PAGE_COUNT; //인트는 소수점이 잘리기때문에~~ 
		if(totalCnt%PAGE_COUNT>0) {
			totalPageCnt ++; //나머지 숫자가 있는경우 페이지 한개를 더 늘려줘야함. 
		}
		// fBlock 부터 계산
//		int fBlock = ((page-1)/BLOCK_COUNT) * BLOCK_COUNT + 1;
//		int lBlock = fBlock + BLOCK_COUNT - 1;	
		// lBlock 부터 계산 
		int lBlock = ((page-1)/BLOCK_COUNT + 1) * BLOCK_COUNT;
		int fBlock = lBlock - BLOCK_COUNT + 1;	
		if (lBlock > totalPageCnt) {
			lBlock = totalPageCnt;
		}		
		request.setAttribute("fBlock", fBlock);
		request.setAttribute("lBlock", lBlock);	
		request.setAttribute("totalPageCnt", totalPageCnt);	
		return addrList;
	}
	@Override
	public int selectTotalAddrCnt() {
		return adao.selectTotalAddrCnt();
	}
}