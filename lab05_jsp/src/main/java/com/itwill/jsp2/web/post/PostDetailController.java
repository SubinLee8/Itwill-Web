package com.itwill.jsp2.web.post;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.jsp2.domain.Post;
import com.itwill.jsp2.service.PostService;

/**
 * Servlet implementation class PostDetailController
 */
@WebServlet(name = "postDetailController", urlPatterns = { "/post/details" })
public class PostDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log=LoggerFactory.getLogger(PostDetailController.class);
	private static final PostService postService=PostService.INSTANCE;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//요청 파라미터 id(글 번호)를 찾는다.
    	int id=Integer.parseInt(request.getParameter("id"));
    	log.debug("doGet(id={})",id);
    	
    	//todo:서비스 계층의 메서드를 호출해서 해당 아이디의 글 상세정보를 가져옴.
    	//글 상세정보를 뷰에 전달.
    	Post post=postService.read(id);
    	request.setAttribute("post", post);
    	log.debug(post.toString());
    	
    	
    	//포워드
    	request.getRequestDispatcher("/WEB-INF/views/post/details.jsp").forward(request, response);
	}

	
}
