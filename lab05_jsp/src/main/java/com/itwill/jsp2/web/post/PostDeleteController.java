package com.itwill.jsp2.web.post;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.jsp2.service.PostService;

/**
 * Servlet implementation class PostDeleteController
 */
@WebServlet(name = "postDeleteController", urlPatterns = { "/post/delete" })
public class PostDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PostService postService=PostService.INSTANCE;
    private static final Logger log= LoggerFactory.getLogger(PostDeleteController.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostDeleteController() {
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//삭제하는 서비스
    	log.debug("doGet()");
    	int id=Integer.parseInt(request.getParameter("id"));
    	int result=postService.delete(id);
    	
    	//리스트로 리다이렉트
    	if(result==1) {
    		String url=request.getContextPath()+"/post/list";
    		response.sendRedirect(url);
    	}
    	
	}

}
  