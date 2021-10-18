package by.boginsky.audiostore.controller;

import by.boginsky.audiostore.command.ActionFactory;
import by.boginsky.audiostore.command.Command;
import by.boginsky.audiostore.exception.CommandException;
import by.boginsky.audiostore.util.ConfigurationManager;
import by.boginsky.audiostore.util.constants.PathPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@MultipartConfig
@WebServlet(name = "ControllerServlet", urlPatterns = "/controller")
public class ControllerServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Router router;
        Command command = ActionFactory.defineCommand(request);

        try {
            router = command.execute(request);
            if (router.getPagePath() != null) {
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(router.getPagePath());
                switch (router.getRouterType()) {
                    case FORWARD:
                        requestDispatcher.forward(request, response);
                        break;
                    case REDIRECT:
                        response.sendRedirect(request.getContextPath() + router.getPagePath());
                        break;
                    default:
                        String page = ConfigurationManager.getProperty(PathPage.PATH_PAGE_ERROR500);
                        response.sendRedirect(request.getContextPath() + page + "");
                        break;
                }
            } else {
                response.sendRedirect(request.getContextPath() + ConfigurationManager.getProperty(PathPage.PATH_PAGE_LOGIN));
            }
        } catch (CommandException e) {
            response.sendRedirect(request.getContextPath() + ConfigurationManager.getProperty(PathPage.PATH_PAGE_ERROR500));
            logger.error("Internal error", e);
            throw new ServletException(e);
        }
    }
}
