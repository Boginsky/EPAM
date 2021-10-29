package by.boginsky.audiostore.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * The type Image transfer servlet.
 */
@WebServlet(name = "ImageTransferServlet", urlPatterns = "/imageTransfer")
public class ImageTransferServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        OutputStream streamOut = response.getOutputStream();
        File image = new File(request.getParameter("imageUuid"));
        InputStream streamIn = new FileInputStream(image);
        byte[] buffer = new byte[1024];
        int count = 0;
        while ((count = streamIn.read(buffer)) >= 0) {
            streamOut.write(buffer, 0, count);
        }
    }
}
