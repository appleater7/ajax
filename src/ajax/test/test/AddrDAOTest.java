package ajax.test.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import dao.AddrDAO;
import dao.Impl.AddrDAOImpl;

public class AddrDAOTest {
	AddrDAO adao = new AddrDAOImpl();
	
	
	@Test //어노테이션! 이게 반드시 있어야 테스트를 실행하는것 
	//하나의 단위마다 메소드를 실행시켜 테스트 할거라면 반드시 @Test가 있어야함! 이런거 5개있음 ㅎㅎ
	
	public void test() {
		//테스트를 위해 강제로 에러를 유발시킴. 아무것도 안하고 실행! 
		//단위테스트 할때 한페이지에 메인메소드 만들어서 테스트 했었는데 이렇게 하는게 더 간편한 것
		Map<String,String> addr = new HashMap<>();
		addr.put("sNum", "11");
		addr.put("lNum", "20");
		List<Map<String,String>> addrList = adao.selectAddrList(addr);
		assertEquals(10, addrList.size()); //주장하다!!!! 
		
	}
	@Test
	public void addrCountTest() {
		int totalCnt = adao.selectTotalAddrCnt();
		assertNotEquals(10, totalCnt);
	}
	
	

}