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

	public static void main(String[] args) {
		UserService us = new UserServiceImpl();
		Map<String, String> map = new HashMap<>();
		map.put("uiId", "id test4");
		map.put("uiPwd", "pwd test4");
		map.put("uiName", "name test4");
		map.put("uiEmail", "email test4");
			
		System.out.println(us.insertUser(map));
	}
}
