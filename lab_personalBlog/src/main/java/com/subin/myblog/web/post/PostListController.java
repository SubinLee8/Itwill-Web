package com.subin.myblog.web.post;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.subin.myblog.domain.Post;
import com.subin.myblog.service.PostService;



/**
 * Servlet implementation class PostListController
 */
@WebServlet(name = "postListController", urlPatterns = { "/post/list" })
public class PostListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final PostService postService = PostService.INSTANCE;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostListController() {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Post> posts=postService.read();
		request.setAttribute("posts", posts);
		request.getRequestDispatcher("/WEB-INF/views/post/list.jsp").forward(request, response);
	}

	

}
