package by.boginsky.audiostore.model.dao.impl;

import by.boginsky.audiostore.exception.DaoException;
import by.boginsky.audiostore.model.dao.AlbumDao;
import by.boginsky.audiostore.model.dao.BaseDao;
import by.boginsky.audiostore.model.entity.audio.Album;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static by.boginsky.audiostore.model.dao.ColumnName.*;


/**
 * The type Album dao.
 */
public class AlbumDaoImpl extends BaseDao<Album> implements AlbumDao {

    private static final String INSERT_INTO_ALBUMS = "INSERT INTO albums (album_name,album_info) values (?,?)";
    private static final String FIND_ALL_ALBUMS_FOR_PAGE = "SELECT album_id,album_name,album_info,album_img FROM albums LIMIT ?,5";
    private static final String FIND_AUTHOR_NAME_FOR_ALBUM = "SELECT DISTINCT author_name FROM albums JOIN songs ON album_id = albums_album_id JOIN authors ON authors_author_id = author_id WHERE album_id = ?";
    private static final String FIND_ALL_ALBUMS = "SELECT album_id,album_name FROM albums";
    private static final String FIND_ALBUM_BY_ID = "SELECT DISTINCT album_id,album_name,album_info,album_img,author_name FROM albums JOIN songs ON album_id = albums_album_id JOIN authors ON authors_author_id = author_id WHERE album_id = ?";
    private static final String FIND_ALBUM_BY_AUTHOR_ID = "SELECT DISTINCT album_id,album_name,album_info,album_img,author_name FROM albums JOIN songs ON album_id = albums_album_id JOIN authors ON authors_author_id = author_id WHERE author_id = ?";
    private static final String UPDATE_ALBUM_PHOTO = "UPDATE albums SET album_img = ? WHERE album_id = ?";
    private static final String REMOVE_ALBUM = "DELETE FROM albums WHERE album_id = ?";
    private static final String UPDATE_ALBUM = "UPDATE albums SET album_name = ?, album_info = ? WHERE album_id = ?";
    private static final String FIND_ALBUM_IMG = "SELECT album_img FROM albums ORDER BY RAND() LIMIT 3";

    @Override
    public List<String> findAlbumImg() throws DaoException {
        List<String> listOfAlbumsImgUrl = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALBUM_IMG)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String imageUrl = resultSet.getString(ALBUM_IMG);
                listOfAlbumsImgUrl.add(imageUrl);
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException, finding album's img", e);
        }
        return listOfAlbumsImgUrl;
    }


    @Override
    public List<Album> findByAuthor(Long authorId) throws DaoException {
        List<Album> listOfAlbums = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALBUM_BY_AUTHOR_ID)) {
            preparedStatement.setLong(1, authorId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long albumId = resultSet.getLong(ALBUM_ID);
                String albumName = resultSet.getString(ALBUM_NAME);
                String albumInfo = resultSet.getString(ALBUM_INFO);
                String albumImg = resultSet.getString(ALBUM_IMG);
                listOfAlbums.add(Album.builder()
                        .setAlbumId(albumId)
                        .setAlbumName(albumName)
                        .setInformationAboutAlbum(albumInfo)
                        .setImageUrl(albumImg)
                        .setListOfAuthors(findAuthorsForAlbum(albumId))
                        .build());
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException searching album by author's name", e);
        }
        return listOfAlbums;
    }

    @Override
    public Long insertAlbum(String name, String informationAboutAlbum) throws DaoException {
        Long id = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_ALBUMS, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, informationAboutAlbum);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getLong(1);
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException, inserting new album", e);
        }
        return id;
    }

    @Override
    public Optional<Album> findById(Long albumId) throws DaoException {
        Optional<Album> album = Optional.empty();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALBUM_BY_ID)) {
            preparedStatement.setLong(1, albumId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String albumName = resultSet.getString(ALBUM_NAME);
                String informationAboutAlbum = resultSet.getString(ALBUM_INFO);
                String imageUrl = resultSet.getString(ALBUM_IMG);
                album = Optional.ofNullable(Album.builder()
                        .setAlbumId(albumId)
                        .setAlbumName(albumName)
                        .setListOfAuthors(findAuthorsForAlbum(albumId))
                        .setInformationAboutAlbum(informationAboutAlbum)
                        .setImageUrl(imageUrl)
                        .build());
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException, searching album by id", e);
        }
        return album;
    }

    @Override
    public void updateAlbumPhoto(String albumImageUrl, Long albumId) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ALBUM_PHOTO)) {
            preparedStatement.setString(1, albumImageUrl);
            preparedStatement.setLong(2, albumId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("SQLException, updating album photo", e);
        }
    }

    @Override
    public void removeAlbum(Long albumId) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_ALBUM)) {
            preparedStatement.setLong(1, albumId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("SQLException,removing album", e);
        }
    }

    @Override
    public void updateAlbum(Long albumId, String albumName, String albumInfo) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ALBUM)) {
            preparedStatement.setString(1, albumName);
            preparedStatement.setString(2, albumInfo);
            preparedStatement.setLong(3, albumId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("SQLException, updating album", e);
        }
    }

    @Override
    public List<Album> findAll(Long startPosition) throws DaoException {
        List<Album> listOfAlbums = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_ALBUMS_FOR_PAGE)) {
            preparedStatement.setLong(1, startPosition);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long albumId = resultSet.getLong(ALBUM_ID);
                String albumName = resultSet.getString(ALBUM_NAME);
                String informationAboutAlbum = resultSet.getString(ALBUM_INFO);
                String imageUrl = resultSet.getString(ALBUM_IMG);
                listOfAlbums.add(Album.builder()
                        .setAlbumId(albumId)
                        .setAlbumName(albumName)
                        .setListOfAuthors(findAuthorsForAlbum(albumId))
                        .setInformationAboutAlbum(informationAboutAlbum)
                        .setImageUrl(imageUrl)
                        .build());
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException, finding all albums for page", e);
        }
        return listOfAlbums;
    }

    @Override
    public List<Album> findAll() throws DaoException {
        List<Album> listOfAlbums = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_ALBUMS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long albumId = resultSet.getLong(ALBUM_ID);
                String albumName = resultSet.getString(ALBUM_NAME);
                listOfAlbums.add(Album.builder()
                        .setAlbumId(albumId)
                        .setAlbumName(albumName)
                        .build());
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException, finding all albums", e);
        }
        return listOfAlbums;
    }

    private List<String> findAuthorsForAlbum(Long albumId) throws DaoException {
        List<String> listOfAuthors = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_AUTHOR_NAME_FOR_ALBUM)) {
            preparedStatement.setLong(1, albumId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                listOfAuthors.add(resultSet.getString(AUTHOR_NAME));
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException finding author's name for album", e);
        }
        return listOfAuthors;
    }
}
