package by.boginsky.controller;

import by.boginsky.command.ActionFactory;
import by.boginsky.command.Command;
import by.boginsky.exception.CommandException;
import by.boginsky.model.pool.ConnectionPool;
import by.boginsky.util.ConfigurationManager;
import by.boginsky.util.constants.ConstantPathPages;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
                        // FIXME: 03.09.2021
                        throw new ServletException("Unknown router type");
                }
            } else {
                String page = ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_LOGIN);
                response.sendRedirect(request.getContextPath() + page);
            }
        } catch (CommandException e) {
            // FIXME: 03.09.2021
            logger.error("Internal error",e);
        }
    }

    @Override
    public void destroy() {
        ConnectionPool.getInstance().destroyPool();
    }

}
