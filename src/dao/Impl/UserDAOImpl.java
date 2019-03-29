package dao.Impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import dao.UserDAO;
import db.DBCon;

public class UserDAOImpl implements UserDAO {

	private String insertUser = "insert into user_info(ui_num, ui_id, ui_pwd, ui_name, ui_email) "
			+ " values(seq_ui_num.nextval,?,?,?,?)";
	
	private String selectUserByID = "select ui_num, ui_id, ui_pwd, ui_name, ui_email from user_info where ui_id=?";
	
	@Override
	public int insertUser(Map<String, String> user) {
		try {
			PreparedStatement ps = DBCon.getCon().prepareStatement(insertUser);
			ps.setString(1, user.get("uiId"));
			ps.setString(2, user.get("uiPwd"));
			ps.setString(3, user.get("uiName"));
			ps.setString(4, user.get("uiEmail"));
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public Map<String, String> selectUserByID(Map<String, String> login) {
		try {
			PreparedStatement ps = DBCon.getCon().prepareStatement(selectUserByID);
			ps.setString(1, login.get("uiId"));
			ResultSet rs =  ps.executeQuery();
			while (rs.next()) {
				Map<String, String> user = new HashMap<>();
				user.put("uiNum", rs.getString("ui_num"));
				user.put("uiId", rs.getString("ui_id"));
				user.put("uiPwd", rs.getString("ui_pwd"));
				user.put("uiName", rs.getString("ui_name"));
				user.put("uiEmail", rs.getString("ui_email"));			
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		UserDAO udao = new UserDAOImpl();
		Map<String, String> map = new HashMap<>();
		map.put("uiId", "oarsars");
		map.put("uiPwd", "12345678");
		
		System.out.println(udao.selectUserByID(map));
	}

}
