package com.subin.myblog.web.post;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.subin.myblog.domain.Post;
import com.subin.myblog.service.PostService;

/**
 * Servlet implementation class PostDetailsController
 */
@WebServlet(name = "postDetailsController", urlPatterns = { "/post/details" })
public class PostDetailsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final PostService postService=PostService.INSTANCE;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostDetailsController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id=Integer.parseInt(request.getParameter("id"));
		Post post = postService.read(id);
		request.setAttribute("post", post);
		request.getRequestDispatcher("/WEB-INF/views/post/details.jsp").forward(request, response);
	}

}
