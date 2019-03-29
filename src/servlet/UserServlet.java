package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.UserService;
import service.Impl.UserServiceImpl;

public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService us = new UserServiceImpl();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cmd = request.getParameter("cmd");
		if ("insert".equals(cmd)) {
			String uiId = request.getParameter("ui_id");
			String uiPwd = request.getParameter("ui_pwd");
			String uiName = request.getParameter("ui_name");
			String uiEmail = request.getParameter("ui_email");
			Map<String, String> user = new HashMap<>();
			user.put("uiId", uiId);
			user.put("uiPwd", uiPwd);
			user.put("uiName", uiName);
			user.put("uiEmail", uiEmail);
			if (user.get("uiId") != null && user.get("uiPwd") != null && user.get("uiName") != null && user.get("uiEmail") != null) {
				if (us.insertUser(user) == 1) {
					request.setAttribute("msg", "회원가입에 성공하셨습니다."); // 본래 서비스 영역
					request.setAttribute("url", "/"); // 본래 서비스 영역
					
				}
				RequestDispatcher rd = request.getRequestDispatcher("/views/msg/result");
				rd.forward(request, response);					
				return;
			}
		}else if ("login".equals(cmd)) {
			String uiId = request.getParameter("ui_id");
			String uiPwd = request.getParameter("ui_pwd");
			Map<String, String> login = new HashMap<>();
			login.put("uiId", uiId);
			login.put("uiPwd", uiPwd);
			request.setAttribute("msg", "로그인에 실패하셨습니다."); // 본래 서비스 영역
			request.setAttribute("url", "/"); // 본래 서비스 영역
			if (us.loginUser(login)) {
				request.setAttribute("msg", "로그인에 성공하셨습니다."); // 본래 서비스 영역
				request.setAttribute("url", "/"); // 본래 서비스 영역
			}
			RequestDispatcher rd = request.getRequestDispatcher("/views/msg/result");
			rd.forward(request, response);					
			return;
		}
	}

}
