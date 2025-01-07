package com.subin.myblog.web.post;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import com.subin.myblog.domain.Post;
import com.subin.myblog.service.PostService;

/**
 * Servlet implementation class PostCreateController
 */
@Slf4j
@WebServlet(name = "postCreateController", urlPatterns = { "/post/create" })
@MultipartConfig(maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 50)
public class PostCreateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final PostService postService = PostService.INSTANCE;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PostCreateController() {
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/post/create.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String content = request.getParameter("content");
		Post post = Post.builder().title(title).author(author).content(content).build();

		Part part = request.getPart("fileName");
		String fileName = getFilename(part);
		if (!fileName.isEmpty()) {
			part.write("C:\\uploadTest\\" + fileName);
		}

		log.debug("doPost(Post={})", post);
		int result = postService.create(post);
		response.sendRedirect(request.getContextPath() + "/post/list");
	}

	private String getFilename(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] split = contentDisp.split(";");
        for (int i = 0; i < split.length; i++) {
            String temp = split[i];
            if (temp.trim().startsWith("filename")) {
                return temp.substring(temp.indexOf("=") + 2, temp.length() - 1);
            }
        }
        return "";
    }


}
