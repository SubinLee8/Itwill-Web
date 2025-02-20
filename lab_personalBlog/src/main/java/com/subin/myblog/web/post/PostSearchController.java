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
 * Servlet implementation class PostSearchController
 */
@WebServlet(name = "postSearchController", urlPatterns = { "/post/search" })
public class PostSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PostService postService = PostService.INSTANCE;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PostSearchController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page")); // 포스트 10개가 보여지는 페이지
		request.setAttribute("currentPage", page);
		int pg = Integer.parseInt(request.getParameter("pg")); // 5개의 페이지를 가진 그룹
		request.setAttribute("pageGroup", pg);

		String keyword = request.getParameter("keyword");
		List<Post> posts = postService.search("tc", keyword);
		request.setAttribute("posts", posts);

		int postNum = posts.size();

		int pageNum = 0;
		if (postNum % 10 == 0) { // 총 페이지 개수
			pageNum = postNum / 10;
		} else {
			pageNum = postNum / 10 + 1;
		}

		int finalPg = 0; // 페이지 그룹 개수
		if (pageNum % 5 == 0) {
			finalPg = pageNum / 5;
		} else {
			finalPg = pageNum / 5 + 1;
		}

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("finalPg", finalPg);

		request.getRequestDispatcher("/WEB-INF/views/post/list.jsp").forward(request, response);
	}

}
