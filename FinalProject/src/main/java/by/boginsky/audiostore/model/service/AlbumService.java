package by.boginsky.audiostore.model.service;

import by.boginsky.audiostore.exception.DaoException;
import by.boginsky.audiostore.exception.ServiceException;
import by.boginsky.audiostore.model.entity.audio.Album;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AlbumService {

    Optional<Album> findAlbumById(Long albumId) throws ServiceException;

    List<Album> findAllAlbums(Long pageId) throws ServiceException;

    List<Album> findAllAlbums() throws ServiceException;

    Long addNewAlbum(String name, String informationAboutAlbum) throws ServiceException;

    List<Album> findByAuthor(Long authorId) throws ServiceException;

    void removeAlbum(Long albumId) throws ServiceException;

    void updateAlbum(Long albumId,String albumName, String albumInfo) throws ServiceException;

}
