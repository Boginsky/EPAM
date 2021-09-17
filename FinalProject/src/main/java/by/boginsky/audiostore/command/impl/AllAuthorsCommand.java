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

import static by.boginsky.audiostore.util.constants.Attribute.ALL_AUTHORS;

public class AllAuthorsCommand implements Command {
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws CommandException {
        List<Author> listOfAuthors;
        AuthorService authorService = AuthorServiceImpl.getInstance();
        try {
            listOfAuthors = authorService.findAllAuthors();
            httpServletRequest.setAttribute(ALL_AUTHORS, listOfAuthors);
        }catch (ServiceException e){
            throw new CommandException("Exception all authors command",e);
        }
        Router router = new Router();
        router.setPagePath(ConfigurationManager.getProperty(PathPage.PATH_PAGE_ALL_AUTHORS));
        return router;
    }
}
