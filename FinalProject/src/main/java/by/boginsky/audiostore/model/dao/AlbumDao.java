package by.boginsky.audiostore.model.dao;

import by.boginsky.audiostore.exception.DaoException;
import by.boginsky.audiostore.model.entity.audio.Album;

import java.util.List;
import java.util.Optional;

/**
 * The interface Album dao.
 */
public interface AlbumDao {

    /**
     * Find album img list.
     *
     * @return the list
     * @throws DaoException the dao exception
     */
    List<String> findAlbumImg() throws DaoException;

    /**
     * Find all list.
     *
     * @param startPosition the start position
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Album> findAll(Long startPosition) throws DaoException;

    /**
     * Find all list.
     *
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Album> findAll() throws DaoException;

    /**
     * Find by id optional.
     *
     * @param albumId the album id
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<Album> findById(Long albumId) throws DaoException;

    /**
     * Find by author list.
     *
     * @param authorId the author id
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Album> findByAuthor(Long authorId) throws DaoException;

    /**
     * Insert album long.
     *
     * @param name                  the name
     * @param informationAboutAlbum the information about album
     * @return the long
     * @throws DaoException the dao exception
     */
    Long insertAlbum(String name, String informationAboutAlbum) throws DaoException;

    /**
     * Update album photo.
     *
     * @param albumImageUrl the album image url
     * @param albumId       the album id
     * @throws DaoException the dao exception
     */
    void updateAlbumPhoto(String albumImageUrl, Long albumId) throws DaoException;

    /**
     * Remove album.
     *
     * @param albumId the album id
     * @throws DaoException the dao exception
     */
    void removeAlbum(Long albumId) throws DaoException;

    /**
     * Update album.
     *
     * @param albumId   the album id
     * @param albumName the album name
     * @param albumInfo the album info
     * @throws DaoException the dao exception
     */
    void updateAlbum(Long albumId, String albumName, String albumInfo) throws DaoException;

}
