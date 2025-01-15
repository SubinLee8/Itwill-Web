package com.itwill.spring2.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URLEncoder;

/**
 * Servlet Filter implementation class AuthentificationFilter
 */
@Slf4j
public class AuthentificationFilter extends HttpFilter implements Filter {
       
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	/**
     * @see HttpFilter#HttpFilter()
     */
    public AuthentificationFilter() {}

	/**
	 * @see Filter#destroy()
	 */
    @Override
	public void destroy() {
		// 필터 객체 소멸될 때 해야할 일(예: 리소스 해제)들을 작성.
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
    @Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 필터를 지나기 전에 해야할 일
		//로그인한 사용자는 필터를 지나고, 로그인하지 않은 사용자는 로그인 페이지로 리다이렉트시킬 것.
    	
    	//서블릿리퀘스트에서 http서블릿리퀘스트로 캐스팅 필요, response도 동일하다.
    	HttpServletRequest httpReq= (HttpServletRequest) request;
    	HttpServletResponse httpRes = (HttpServletResponse) response;
    	
    	HttpSession session=httpReq.getSession();
    	
    	Object signedInUser = session.getAttribute("signedInUser");
    	
    	if(signedInUser!=null&&!signedInUser.equals("")) {
    		log.debug("로그인 상태: username={}",signedInUser);
    		// 필터를 통과.
    		chain.doFilter(request, response);
    		return;
    	}else {
    		log.debug("로그아웃 상태==>로그인 페이지로 이동");
    		//로그인 이후에 최초 요청주소로 이동(리다이렉트)하기 위해서
    		String url=httpReq.getRequestURL().toString();
    		log.debug("[request url]{}",url);
    		
    		String qs=httpReq.getQueryString();
    		log.debug("[query string] {}",qs);
    		
    		String target=null; //로그인 성공 후 이동할 주소
    		if(qs!=null) {
    			//쿼리스트링이 존재
    			target=URLEncoder.encode(url+"?"+qs,"UTF-8");
    			
    		}else {
    			//쿼리스트링이 존재하지 않음
    			target=URLEncoder.encode(url,"UTF-8");
    		}
    		String signInPage=httpReq.getContextPath()+"/user/signin?target="+target;
    		httpRes.sendRedirect(signInPage);
    	}
    	
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
    @Override
	public void init(FilterConfig fConfig) throws ServletException {
		// WAS가 필터객체를 생성할 때 할 일
	}

}
