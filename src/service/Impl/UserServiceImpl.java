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
	public boolean loginUser(Map<String, String> login) {
		if (udao.selectUserByID(login) != null) {
			if (udao.selectUserByID(login).get("uiId").equals(login.get("uiId"))) {
				if (udao.selectUserByID(login).get("uiPwd").equals(login.get("uiPwd"))) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		UserService us = new UserServiceImpl();
		
		Map<String, String> map = new HashMap<>();
		map.put("uiId", "osfu");
		map.put("uiPwd", "123456789");
		
		System.out.println(us.loginUser(map));
	}
}
