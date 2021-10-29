package by.boginsky.audiostore.model.service;

import by.boginsky.audiostore.exception.ServiceException;
import by.boginsky.audiostore.model.entity.audio.Album;

import java.util.List;
import java.util.Optional;

/**
 * The interface Album service.
 */
public interface AlbumService {

    /**
     * Find album by id optional.
     *
     * @param albumId the album id
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<Album> findAlbumById(Long albumId) throws ServiceException;

    /**
     * Find all albums list.
     *
     * @param pageId the page id
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Album> findAllAlbums(Long pageId) throws ServiceException;

    /**
     * Find all albums list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Album> findAllAlbums() throws ServiceException;

    /**
     * Add new album long.
     *
     * @param name                  the name
     * @param informationAboutAlbum the information about album
     * @return the long
     * @throws ServiceException the service exception
     */
    Long addNewAlbum(String name, String informationAboutAlbum) throws ServiceException;

    /**
     * Find by author list.
     *
     * @param authorId the author id
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Album> findByAuthor(Long authorId) throws ServiceException;

    /**
     * Remove album.
     *
     * @param albumId the album id
     * @throws ServiceException the service exception
     */
    void removeAlbum(Long albumId) throws ServiceException;

    /**
     * Update album.
     *
     * @param albumId   the album id
     * @param albumName the album name
     * @param albumInfo the album info
     * @throws ServiceException the service exception
     */
    void updateAlbum(Long albumId, String albumName, String albumInfo) throws ServiceException;

    /**
     * Find album img list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<String> findAlbumImg() throws ServiceException;
}
