package service.Impl;

import java.util.HashMap;
import java.util.Map;

import dao.UserDAO;
import dao.Impl.UserDAOImpl;
import service.UserService;

public class UserServiceImpl implements UserService {
	private UserDAO udao = new UserDAOImpl();
	@Override
	public int insertUser(Map<String, String> user) {
		return udao.insertUser(user);
	}
	@Override
	public Map<String, String> login(String uiId, String uiPwd) {
		return udao.selectUserByUiId(uiId,uiPwd);
	}
}
