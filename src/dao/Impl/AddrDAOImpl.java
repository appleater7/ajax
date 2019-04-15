package dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.AddrDAO;
import db.DBCon;

public class AddrDAOImpl implements AddrDAO {
	private static String selectAddrListSQL = "select * from\r\n" + 
			"(select rownum as rown, addr.* FROM\r\n" + 
			"(select * from  address $where$ order by ad_num) ADDR\r\n" + 
			"where rownum<=?)\r\n" + 
			"where rown>=?";
	
	private static String selectAddrCount = "select count(1) from address $where$"; //전체갯수를 알아야 페이징 할수있으니까 필요
	private static String selectAddr = "select * from address where 1=1 and ad_num=?";
	private static String updateAddr = "update address set ad_code=?, ad_sido=?, ad_gugun=?, ad_dong=?, ad_lee=?, ad_bunji=?, ad_ho=? where ad_num=? ";
	private static String deleteAddr = "delete from address where ad_num=?";
	private static String selectAdSido = "select DISTINCT ad_sido from address order by ad_sido";
	private static String selectAdGugun = "select DISTINCT ad_gugun from address where ad_sido=? order by ad_gugun";
	
	@Override
	public List<Map<String, String>> selectAddrList(Map<String, String> addr) {
		String adDong = addr.get("ad_dong");
		String sql = selectAddrListSQL.replace("$where$", "");
		try {
			if (adDong != null) {
				sql = selectAddrListSQL.replace("$where$", " where ad_dong like '%' || ? || '%'");
			}
			PreparedStatement ps = DBCon.getCon().prepareStatement(sql);
			ps.setString(1, addr.get("lNum"));
			ps.setString(2, addr.get("sNum"));			
			if (adDong != null)	{
				ps.setString(1, adDong);
				ps.setString(2, addr.get("lNum"));
				ps.setString(3, addr.get("sNum"));
			}
			ResultSet rs = ps.executeQuery();
			List<Map<String,String>> addrList = new ArrayList<>();
			while(rs.next()) {
				Map<String,String> address = new HashMap<>();
				address.put("ad_num", rs.getString("ad_num"));
				address.put("ad_code", rs.getString("ad_code"));
				address.put("ad_sido", rs.getString("ad_sido"));
				address.put("ad_gugun", rs.getString("ad_gugun"));
				address.put("ad_dong", rs.getString("ad_dong"));
				address.put("ad_lee", rs.getString("ad_lee"));
				address.put("ad_bunji", rs.getString("ad_bunji"));
				address.put("ad_ho", rs.getString("ad_ho"));
				addrList.add(address);
			}
			return addrList;			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	@Override
	public int selectTotalAddrCnt(Map<String, String> addr) {
		String adDong = addr.get("ad_dong");
		String sql = selectAddrCount.replace("$where$", "");
		try {
			if (adDong != null) {
				sql = selectAddrCount.replace("$where$", "where ad_dong like '%' || ? || '%'");
			}
			PreparedStatement ps = DBCon.getCon().prepareStatement(sql);
			if (adDong != null)	{
				ps.setString(1, adDong);
			}
			ResultSet rs = ps.executeQuery();			
			while(rs.next()) {
				return rs.getInt(1); 
				//컬럼 첫번째꺼만 가져오라고 하는거여서 1 써줌. 데이터베이스 시작은 1 이니까 
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}	
		return 0;
	}
	@Override
	public Map<String, String> selectAddr(Map<String, String> addr) {
		PreparedStatement ps;
		try {
			ps = DBCon.getCon().prepareStatement(selectAddr);
			ps.setString(1, addr.get("ad_num"));		
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Map<String,String> address = new HashMap<>();				
				address.put("ad_num", rs.getString("ad_num"));
				address.put("ad_code", rs.getString("ad_code"));
				address.put("ad_sido", rs.getString("ad_sido"));
				address.put("ad_gugun", rs.getString("ad_gugun"));
				address.put("ad_dong", rs.getString("ad_dong"));
				address.put("ad_lee", rs.getString("ad_lee"));
				address.put("ad_bunji", rs.getString("ad_bunji"));
				address.put("ad_ho", rs.getString("ad_ho"));
				return address;
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public int updateAddr(Map<String, String> param) {
		try {
			PreparedStatement ps = DBCon.getCon().prepareStatement(updateAddr);
			ps.setString(1, param.get("adCode"));
			ps.setString(2, param.get("adSido"));
			ps.setString(3, param.get("adGugun"));
			ps.setString(4, param.get("adDong"));
			ps.setString(5, param.get("adLee"));
			ps.setString(6, param.get("adBunji"));
			ps.setString(7, param.get("adHo"));
			ps.setString(8, param.get("adNum"));
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	@Override
	public int deleteAddr(Map<String, String> param) {
		try {
			PreparedStatement ps = DBCon.getCon().prepareStatement(deleteAddr);
			ps.setString(1, param.get("adNum"));
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	@Override
	public List<String> selectAdSido() {
		try(Connection con = DBCon.getCon();
			PreparedStatement ps = con.prepareStatement(selectAdSido);) {
			ResultSet rs = ps.executeQuery();
			List<String> adSidoList	= new ArrayList<>();
			while (rs.next()) {
				adSidoList.add(rs.getString("ad_sido"));
			}
			rs.close();
			return adSidoList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public List<String> selectAdGugunList(String adSido) {
		try(Connection con = DBCon.getCon();
				PreparedStatement ps = con.prepareStatement(selectAdGugun);) {
				ps.setString(1, adSido);
				ResultSet rs = ps.executeQuery();
				List<String> adGugunList = new ArrayList<>();
				while (rs.next()) {
					adGugunList.add(rs.getString("ad_gugun"));
				}
				rs.close();
				return adGugunList;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
	}
}