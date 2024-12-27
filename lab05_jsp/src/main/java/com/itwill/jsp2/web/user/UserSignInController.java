package com.itwill.jsp2.web.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import oracle.net.ns.SessionAtts;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.jsp2.domain.Member;
import com.itwill.jsp2.service.MemberService;

/**
 * Servlet implementation class UserSignInController
 */
@WebServlet(name = "userSignInController", urlPatterns = { "/user/signin" })
public class UserSignInController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final MemberService memberService=MemberService.INSTANCE;
	private static final Logger log=LoggerFactory.getLogger(UserSignInController.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserSignInController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/user/signin.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		Member member = memberService.signIn(username, password);
		if(member!=null) {
			//username과 password가 일치하는 사용자가 있는 경우 -> 로그인 성공
			//세션에 로그인 정보 저장.
			HttpSession session = request.getSession();
			session.setAttribute("signedInUser", member.getUserName());
			
			//목록으로 이동(redirect)
			log.debug("로그인 성공:redirect to list page");
			response.sendRedirect(request.getContextPath()+"/post/list");
		}else {
			//로그인 실패 -> 로그인 페이지로 redirect
			log.debug("로그인 실패:redirect to signin page");
			response.sendRedirect(request.getContextPath()+"/user/signin");
		}
		
	}

}
