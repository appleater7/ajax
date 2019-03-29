package dao.Impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import dao.UserDAO;
import db.DBCon;

public class UserDAOImpl implements UserDAO {

	private String insertUser = "insert into user_info(ui_num, ui_id, ui_pwd, ui_name, ui_email) "
			+ " values(seq_ui_num.nextval,?,?,?,?)";
	
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

}
