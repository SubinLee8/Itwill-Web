package com.subin.myblog.web.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URLEncoder;

import org.apache.catalina.User;

import com.subin.myblog.domain.Member;
import com.subin.myblog.service.MemberService;

/**
 * Servlet implementation class UserSignInController
 */
@Slf4j
@WebServlet(name = "userSignInController", urlPatterns = { "/user/signin" })
public class UserSignInController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final MemberService memberService=MemberService.INSTANCE;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserSignInController() {
        
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
		Member member=memberService.signIn(username, password);
		String target=request.getParameter("target");
		log.debug("doPost(username={},password={},target={})",username,password,target);
		
		if(member!=null) {//로그인 성공
			log.debug("로그인 성공");
			HttpSession session = request.getSession();
			session.setAttribute("signedInUser", member.getUserName());
			
			if(target!=null && !target.equals(" ")) { //타겟페이지 존재
				log.debug("타겟페이지존재");
				response.sendRedirect(target);
			}
			else {//타겟페이지없음
				log.debug("타겟페이지없음");
				response.sendRedirect(request.getContextPath()+"/");
			}
		
		}
		else { //로그인 실패
			log.debug("로그인 실패");
			response.sendRedirect(request.getContextPath()+"/user/signin?result=f&target="+URLEncoder.encode(target,"UTF-8"));
		}
			
			
	}

}
