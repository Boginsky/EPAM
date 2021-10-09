package by.boginsky.audiostore.model.service;

import by.boginsky.audiostore.exception.ServiceException;
import by.boginsky.audiostore.model.entity.audio.Album;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AlbumService {

    Optional<Album> findAlbumById(Long albumId) throws ServiceException;

    List<Album> findAllAlbums() throws ServiceException;

    Optional<Album> findAlbumByName(String albumName) throws ServiceException;

    List<Album> findAlbumByGenre(String nameOfGenre) throws ServiceException;

    void addNewAlbum(String nameOfAlbum, LocalDateTime dateOfCreation, String informationAboutAlbum) throws ServiceException;

    List<Album> findByAuthor(Long authorId) throws ServiceException;
}
