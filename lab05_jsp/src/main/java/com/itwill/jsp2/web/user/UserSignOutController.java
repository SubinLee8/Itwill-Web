package com.itwill.jsp2.web.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet implementation class UserSignOutController
 */
@WebServlet(name = "userSignOutController", urlPatterns = { "/user/signout" })
public class UserSignOutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log=LoggerFactory.getLogger(UserSignOutController.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserSignOutController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그아웃
		//(1) 세션에 저장된 로그인 관련 정보들(signedInUser)을 삭제.
		//(2) 세션 객체를 무효화(invalidate)시킴 - 세션 객체 지움.
		//(2)를 실행하면 (1)은 자동으로 실행된다.
		
		log.debug("doGet()");
		HttpSession session=request.getSession();
		session.removeAttribute("signedInUser"); //(1)
		session.invalidate(); //(2)
		log.debug("로그아웃:redirect to hompage");
		response.sendRedirect(request.getContextPath()+"/"); //로그아웃 후 홈페이지로 이동
		
	}

	

}
