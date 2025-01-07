package com.subin.myblog.web.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * Servlet implementation class UserSignOutController
 */
@Slf4j
@WebServlet(name = "userSignOutController", urlPatterns = { "/user/signout" })
public class UserSignOutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserSignOutController() {
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		session.removeAttribute("signedInUser");
		session.invalidate(); //(2)
		log.debug("로그아웃:redirect to hompage");
		response.sendRedirect(request.getContextPath()+"/"); //로그아웃 후 홈페이지로 이동
	}

}
