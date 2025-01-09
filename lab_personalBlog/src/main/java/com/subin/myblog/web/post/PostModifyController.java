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
 * Servlet implementation class PostModifyController
 */
@WebServlet(name = "postModifyController", urlPatterns = { "/post/modify" })
public class PostModifyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final PostService postService=PostService.INSTANCE;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostModifyController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("id"));
		request.setAttribute("id", id);
		Post post=postService.read(id);
		
		request.setAttribute("post", post);
		request.getRequestDispatcher("/WEB-INF/views/post/modify.jsp").forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("id"));
		String content=request.getParameter("content");
		String title=request.getParameter("title");
		Post post=Post.builder().id(id).title(title).content(content).build();
		int result=postService.update(post);
		
		response.sendRedirect(request.getContextPath()+"/post/details?id="+id);
		
	}
	
}
