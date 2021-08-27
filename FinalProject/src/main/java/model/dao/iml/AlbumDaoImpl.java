package model.dao.iml;

import exception.DaoException;
import model.dao.AlbumDao;
import model.dao.BaseDao;
import model.entity.audio.Album;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AlbumDaoImpl extends BaseDao implements AlbumDao {

    private static final String FIND_ALBUM_BY_NAME = "SELECT album_id FROM albums WHERE album_name = ? ";
    private static final String FIND_ALBUM_BY_GENRE = "SELECT album_id FROM albums JOIN songs ON album_id = albums_album_id JOIN genres ON genres_genre_id = genre_id WHERE genre_name = ?";
    private static final String INSERT_INTO_ALBUMS = "INSERT INTO albums (album_name,album_date_of_creation,album_info) values (?,?,?)";
    private static final String FIND_ALL_ALBUMS = "SELECT * FROM albums";
    private static final String FIND_ALBUM_BY_ID = "SELECT * FROM albums WHERE album_id = ?";


    @Override
    public Optional<Long> findByName(String nameOfAlbum) throws DaoException {
        PreparedStatement preparedStatement = null;
        Optional<Long> albumId = null;
        try {
            preparedStatement = connection.prepareStatement(FIND_ALBUM_BY_NAME);
            preparedStatement.setString(1, nameOfAlbum);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                albumId = Optional.of(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException, searching album by name");
        } finally {
            if (connection != null) {
                close(preparedStatement);
            }
        }
        return albumId;
    }

    @Override
    public List<Long> findByGenre(String nameOfGenre) throws DaoException {
        PreparedStatement preparedStatement = null;
        Long id = null;
        List<Long> listOfIdAlbums = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(FIND_ALBUM_BY_GENRE);
            preparedStatement.setString(1, nameOfGenre);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getLong(1);
                listOfIdAlbums.add(id);
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException, searcing album by genre");
        } finally {
            if (connection != null) {
                close(preparedStatement
                );
            }
        }
        return listOfIdAlbums;
    }

    @Override
    public void insertAlbum(String nameOfAlbum, Timestamp dateOfCreation, String informationAboutAlbum) throws DaoException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(INSERT_INTO_ALBUMS);
            preparedStatement.setString(1, nameOfAlbum);
            preparedStatement.setTimestamp(2, dateOfCreation);
            preparedStatement.setString(3, informationAboutAlbum);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("SQLException, inserting new album");
        } finally {
            if (connection != null) {
                close(preparedStatement);
            }
        }
    }

    @Override
    public List<Album> findAll() throws DaoException {
        PreparedStatement preparedStatement = null;
        List<Album> listOfAlbums = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(FIND_ALL_ALBUMS);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String albumName = resultSet.getString(2);
                Timestamp dateOfCreation = resultSet.getTimestamp(3);
                String informationAboutAlbum = resultSet.getString(4);
                listOfAlbums.add(new Album(albumName, dateOfCreation, informationAboutAlbum));
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException, finding all genres");
        } finally {
            if (connection != null) {
                close(preparedStatement);
            }
        }
        return listOfAlbums;
    }

    @Override
    public Optional<Album> findById(Long id) throws DaoException {
        PreparedStatement preparedStatement = null;
        Optional<Album> album = null;
        try{
            preparedStatement = connection.prepareStatement(FIND_ALBUM_BY_ID);
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                String albumName = resultSet.getString(2);
                Timestamp dateOfCreation = resultSet.getTimestamp(3);
                String informationAboutAlbum = resultSet.getString(4);
                album = Optional.of(new Album(albumName,dateOfCreation,informationAboutAlbum));
            }
        }catch (SQLException e){
            throw new DaoException("SQLException, finding album by id");
        }finally {
            if(connection != null){
                close(preparedStatement);
            }
        }
        return album;
    }
}
