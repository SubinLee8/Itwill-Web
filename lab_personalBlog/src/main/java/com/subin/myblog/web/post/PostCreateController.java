package com.subin.myblog.web.post;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Locale;

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
		
		//업로드된 파일의 part 가져오기
		Part part = request.getPart("fileName");
		
		//업로드된 파일 이름 가져오기
		String fileName=part.getSubmittedFileName();
		
		//업로드 될 절대경로 가져오기
		String uploadPath = getServletContext().getRealPath("") +"static"+ File.separator+"img";
		System.out.println(uploadPath);
		
		
		 // 업로드 디렉토리 생성 (존재하지 않으면)
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        
        Date today = new Date();
		Locale currentLocale = new Locale("KOREAN", "KOREA");
		String pattern = "yyyyMMddHHmmss";
		
		String img= uploadPath + File.separator + fileName+pattern;
        part.write(img);
        
        response.getWriter().write("File uploaded successfully to: " +img);
        
		
		Post post = Post.builder().title(title).author(author).content(content).fileName(fileName+pattern).build();
		
		log.debug("doPost(Post={})", post);
		int result = postService.create(post);
		response.sendRedirect(request.getContextPath() + "/post/list");
	}


    


}