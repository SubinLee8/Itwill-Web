package com.subin.myblog.web.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.subin.myblog.domain.Member;
import com.subin.myblog.service.MemberService;

/**
 * Servlet implementation class UserSignUpController
 */
@WebServlet(name = "userSignUpController", urlPatterns = { "/user/signup" })
public class UserSignUpController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final MemberService memberService=MemberService.INSTANCE;
	private static final Logger log=LoggerFactory.getLogger(UserSignUpController.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserSignUpController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/user/signup.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String email=request.getParameter("email");
		Member member=Member.builder().userName(username).passWord(password).email(email).build();
		String target=request.getParameter("target");
		
		int result=memberService.signUp(member);
		log.debug("signUp {} result={}",member,result);
		if(result==0) { //회원가입 실패
			response.sendRedirect(request.getContextPath()+"/user/signup?result=f&target=");
			log.debug("회원가입 실패:redirect to signin page");
			URLEncoder.encode(target, "UTF-8");
		}
		else { //회원가입 성공
			if(target.equals("")||target==null)
			{
				response.sendRedirect(request.getContextPath()+"/");
			}
			else {
				response.sendRedirect(target);
			}
		}
	}

}
