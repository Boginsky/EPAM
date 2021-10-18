package by.boginsky.audiostore.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet(name = "ImageTransferServlet", urlPatterns = "/imageTransfer")
public class ImageTransferServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OutputStream streamOut = response.getOutputStream();
        String imageName = (request.getParameter("imageUuid"));
        File image = new File(imageName);
        response.setContentLength((int) image.length());
        String contentType = "image/" + imageName.substring(imageName.lastIndexOf(".") + 1);
        response.setContentType(contentType);
        InputStream streamIn = new FileInputStream(image);
        byte[] buffer = new byte[1024];
        int count = 0;
        while ((count = streamIn.read(buffer)) >= 0) {
            streamOut.write(buffer, 0, count);
        }
    }
}
