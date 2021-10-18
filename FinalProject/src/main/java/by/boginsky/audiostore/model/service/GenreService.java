package by.boginsky.audiostore.model.service;

import by.boginsky.audiostore.exception.ServiceException;
import by.boginsky.audiostore.model.entity.audio.Genre;

import java.util.List;

public interface GenreService {

    List<Genre> findAllGenres() throws ServiceException;
}
