package com.subin.myblog.web.post;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet(name = "ImageServlet", urlPatterns = {"/uploadTest/*"})
public class ImageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String BASE_IMAGE_DIR = "C:\\uploadTest";
 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String imagePath = BASE_IMAGE_DIR + request.getPathInfo();
        log.debug("Requested Image Path: " + imagePath); // 추가된 로그
        File imageFile = new File(imagePath);

        if (imageFile.exists()) {
            response.setContentType(getServletContext().getMimeType(imagePath));
            response.setContentLengthLong(imageFile.length());

            try (FileInputStream in = new FileInputStream(imageFile);
                 OutputStream out = response.getOutputStream()) {

                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
            }
        } else {
            log.error("File not found: " + imagePath); // 추가된 로그
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
    

