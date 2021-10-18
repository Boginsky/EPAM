package by.boginsky.audiostore.command.impl;

import by.boginsky.audiostore.command.Command;
import by.boginsky.audiostore.controller.Router;
import by.boginsky.audiostore.exception.CommandException;
import by.boginsky.audiostore.exception.ServiceException;
import by.boginsky.audiostore.model.entity.audio.Author;
import by.boginsky.audiostore.model.service.AuthorService;
import by.boginsky.audiostore.model.service.impl.AuthorServiceImpl;
import by.boginsky.audiostore.util.ConfigurationManager;
import by.boginsky.audiostore.util.constants.PathPage;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static by.boginsky.audiostore.util.constants.Constant.ALL_AUTHORS;
import static by.boginsky.audiostore.util.constants.Constant.PAGE_ID;

public class AllAuthorsCommand implements Command {

    private static final Long START_PAGE = 1L;

    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws CommandException {
        List<Author> listOfAuthors;
        AuthorService authorService = AuthorServiceImpl.getInstance();
        String id = httpServletRequest.getParameter(PAGE_ID);
        Long pageId;
        if (id == null) {
            pageId = START_PAGE;
        } else {
            pageId = Long.parseLong(id);
        }
        try {
            listOfAuthors = authorService.findAllAuthors(pageId);
            httpServletRequest.setAttribute(PAGE_ID, ++pageId);
            httpServletRequest.setAttribute(ALL_AUTHORS, listOfAuthors);
        } catch (ServiceException e) {
            throw new CommandException("Exception all authors command", e);
        }
        Router router = new Router();
        router.setPagePath(ConfigurationManager.getProperty(PathPage.PATH_PAGE_ALL_AUTHORS));
        return router;
    }
}
