package service;

import java.util.Map;

public interface UserService {
	
	public int insertUser(Map<String, String> user);
	
	public boolean loginUser(Map<String, String> login);
}
