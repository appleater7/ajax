package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodingFilter implements Filter {
	
	private String encoding = "";
	
	public EncodingFilter() {
		System.out.println("난 생성자");
	}

	public void destroy() {
		// 서버가 종료 될 때 Destory 실행 Garbage Collector 에서 소멸될 때, 실행됨
		// 
		System.out.println("난 destory");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 두번째 알아야 할 것
		request.setCharacterEncoding("utf-8");
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// 첫번째 알아야 할 것
		encoding = fConfig.getInitParameter("encoding");
		System.out.println("난 init");
	}

}
