package com.subin.myblog.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.subin.myblog.domain.Post;
import com.subin.myblog.service.PostService;



/**
 * Servlet implementation class HomeController
 */
@Slf4j
@WebServlet(name = "homeController", urlPatterns = { "" })
public class HomeController extends HttpServlet {
	private PostService postService = PostService.INSTANCE;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeController() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("doGet()");
		List<Post> list=postService.read();
		List<Post> previewList=new ArrayList<Post>();
		
		if(list.size()>=6) {
			for(int i=0;i<6;i++) {
				previewList.add(list.get(i));
				log.debug("{}",previewList);
				request.setAttribute("list", previewList);
			}
		}
		request.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(request, response);
	}

	

}
