package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.MovieService;
import service.Impl.MovieServiceImpl;

public class MovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MovieService ms = new MovieServiceImpl();
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		int idx = uri.lastIndexOf("/");
		if (idx == 0) {
			throw new ServletException("원하시는 서비스가 부정확합니다."); 
			// 개발자의 실수가 아닌 사용자의 실수니까 알려줄것
		} else {
			String cmd = uri.substring(idx+1);
			if("list".equals(cmd)) {
				request.setAttribute("list", ms.selectMovieList());
				RequestDispatcher rd = request.getRequestDispatcher("/views/movie/movie_list");
				rd.forward(request, response);
			}
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		int idx = uri.lastIndexOf("/");
		String cmd = uri.substring(idx+1);
		if("insert".equals(cmd)) {
			HttpSession hs = request.getSession();
			if (hs.getAttribute("user")==null) {
				request.setAttribute("msg", "로그인하세요.");
				request.setAttribute("url", "/");
				RequestDispatcher rd = request.getRequestDispatcher("/views/msg/result");
				rd.forward(request, response);
				return;
			}
			Map<String, String> movie = new HashMap<>();
			movie.put("miName", request.getParameter("mi_name"));
			movie.put("miYear", request.getParameter("mi_year"));
			movie.put("miNational", request.getParameter("mi_national"));
			movie.put("miVendor", request.getParameter("mi_vendor"));
			movie.put("miDirector", request.getParameter("mi_director"));
			request.setAttribute("msg", "등록이 실패 하였습니다.");
			request.setAttribute("url", "/views/movie/insert/");
			if (ms.insertMovie(movie)==1) {
				request.setAttribute("msg", "등록이 성공 하였습니다.");
				request.setAttribute("url", "/movie/list");
			}
			RequestDispatcher rd = request.getRequestDispatcher("/views/msg/result");
			rd.forward(request, response);
		}
	}
}
