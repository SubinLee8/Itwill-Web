package com.itwill.jsp2.web.post;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.jsp2.domain.Post;
import com.itwill.jsp2.service.PostService;

/**
 * Servlet implementation class PostCreateController
 */
@WebServlet(name = "postCreateController", urlPatterns = { "/post/create" })
public class PostCreateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log= LoggerFactory.getLogger(PostListController.class);
	private final PostService postService = PostService.INSTANCE;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostCreateController() {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/post/create.jsp").forward(request, response);
	}
    
    /**
     * @see HttpServlet#doPost(HttpServletRequest, HttpServletResponse))
     * 
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	log.debug("doPost()");
    	
    	//양식 데이터에 포함된 요청 파라미터 값들(title, content, author)을 읽음.
    	String title=request.getParameter("title");
    	String author=request.getParameter("author");
    	String content=request.getParameter("content");
    	Post post=Post.builder().author(author).title(title).content(content).build();
    	
    	//서비스 계층의 메서드를 호출 -> DB에 저장.
    	postService.create(post);
    	
    	
    	//포스트 목록 페이지로 이동(redirect)
    	String url=request.getContextPath()+"/post/list";
    	log.debug("redirecting to {}",url);
    	response.sendRedirect(url);
    }

}
