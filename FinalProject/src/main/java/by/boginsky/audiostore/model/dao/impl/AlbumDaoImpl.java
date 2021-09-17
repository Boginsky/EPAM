package by.boginsky.audiostore.model.dao.impl;

import by.boginsky.audiostore.exception.DaoException;
import by.boginsky.audiostore.model.dao.AlbumDao;
import by.boginsky.audiostore.model.dao.BaseDao;
import by.boginsky.audiostore.model.entity.audio.Album;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static by.boginsky.audiostore.model.dao.ColumnName.*;

public class AlbumDaoImpl extends BaseDao implements AlbumDao {

    private static final String FIND_ALBUM_BY_NAME = "SELECT album_name,album_date_of_creation,album_info FROM albums WHERE album_name = ?";
    private static final String FIND_ALBUM_BY_GENRE = "SELECT album_name,album_date_of_creation,album_info FROM albums JOIN songs ON album_id = albums_album_id JOIN genres ON genres_genre_id = genre_id WHERE genre_name = ?";
    private static final String INSERT_INTO_ALBUMS = "INSERT INTO albums (album_name,album_date_of_creation,album_info) values (?,?,?)";
    private static final String FIND_ALL_ALBUMS = "SELECT DISTINCT album_id,album_name,album_info,album_img,author_first_name,author_last_name FROM albums JOIN songs ON album_id = albums_album_id JOIN authors ON authors_author_id = author_id";
    private static final String FIND_ALBUM_BY_ID = "SELECT DISTINCT album_id,album_name,album_info,album_img,author_first_name,author_last_name FROM albums JOIN songs ON album_id = albums_album_id JOIN authors ON authors_author_id = author_id WHERE album_id = ?";


    @Override
    public Optional<Album> findById(Long albumId) throws DaoException {
        Optional<Album> album = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALBUM_BY_ID)) {
            preparedStatement.setLong(1, albumId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String albumName = resultSet.getString(ALBUM_NAME);
                String authorFirstName = resultSet.getString(AUTHOR_FIRST_NAME);
                String authorLastName = resultSet.getString(AUTHOR_LAST_NAME);
                String informationAboutAlbum = resultSet.getString(ALBUM_INFO);
                String imageUrl = resultSet.getString(ALBUM_IMG);
                album = Optional.ofNullable(Album.builder()
                        .setAlbumId(albumId)
                        .setAlbumName(albumName)
                        .setAuthorName(authorFirstName.concat(" ").concat(authorLastName))
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
    public Optional<Album> findByName(String nameOfAlbum) throws DaoException {
        Optional<Album> album = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALBUM_BY_NAME)) {
            preparedStatement.setString(1, nameOfAlbum);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String albumName = resultSet.getString(ALBUM_NAME);
                Timestamp dateOfCreation = resultSet.getTimestamp(ALBUM_DATE_OF_CREATION);
                String albumInfo = resultSet.getString(ALBUM_INFO);
                album = Optional.of(Album.builder()
                        .setAlbumName(albumName)
//                        .setDateOfCreation(dateOfCreation.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())
                        .setInformationAboutAlbum(albumInfo)
                        .build());
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException, searching album by name", e);
        }
        return album;
    }

    @Override
    public List<Album> findByGenre(String nameOfGenre) throws DaoException {
        List<Album> listOfIdAlbums = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALBUM_BY_GENRE)) {
            preparedStatement.setString(1, nameOfGenre);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String albumName = resultSet.getString(ALBUM_NAME);
                Timestamp dateOfCreation = resultSet.getTimestamp(ALBUM_DATE_OF_CREATION);
                String albumInfo = resultSet.getString(ALBUM_INFO);
                listOfIdAlbums.add(Album.builder()
                        .setAlbumName(albumName)
//                        .setDateOfCreation(dateOfCreation.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())
                        .setInformationAboutAlbum(albumInfo)
                        .build());
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException, searching album by genre", e);
        }
        return listOfIdAlbums;
    }

    @Override
    public void insertAlbum(String nameOfAlbum, Timestamp dateOfCreation, String informationAboutAlbum) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_ALBUMS)) {
            preparedStatement.setString(1, nameOfAlbum);
            preparedStatement.setTimestamp(2, dateOfCreation);
            preparedStatement.setString(3, informationAboutAlbum);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("SQLException, inserting new album", e);
        }
    }

    @Override
    public List<Album> findAll() throws DaoException {
        List<Album> listOfAlbums = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_ALBUMS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long albumId = resultSet.getLong(ALBUM_ID);
                String albumName = resultSet.getString(ALBUM_NAME);
                String authorFirstName = resultSet.getString(AUTHOR_FIRST_NAME);
                String authorLastName = resultSet.getString(AUTHOR_LAST_NAME);
                String informationAboutAlbum = resultSet.getString(ALBUM_INFO);
                String imageUrl = resultSet.getString(ALBUM_IMG);
                listOfAlbums.add(Album.builder()
                        .setAlbumId(albumId)
                        .setAlbumName(albumName)
                        .setAuthorName(authorFirstName.concat(" ").concat(authorLastName))
                        .setInformationAboutAlbum(informationAboutAlbum)
                        .setImageUrl(imageUrl)
                        .build());
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException, finding all genres", e);
        }
        return listOfAlbums;
    }
}
