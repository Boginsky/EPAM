package by.boginsky.audiostore.model.dao.impl;

import by.boginsky.audiostore.exception.DaoException;
import by.boginsky.audiostore.model.dao.BaseDao;
import by.boginsky.audiostore.model.dao.SongDao;
import by.boginsky.audiostore.model.entity.audio.Song;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static by.boginsky.audiostore.model.dao.ColumnName.*;

/**
 * The type Song dao.
 */
public class SongDaoImpl extends BaseDao<Song> implements SongDao {

    private static final String FIND_SONG_BY_ID = "SELECT song_id,song_name,album_img,song_price,author_name,genre_name,album_name FROM songs JOIN authors ON authors_author_id = author_id JOIN genres ON genres_genre_id = genre_id JOIN albums ON albums_album_id = album_id WHERE song_id = ?";
    private static final String FIND_SONG_BY_ORDER_ID = "SELECT song_id,song_name,song_price,author_name,genre_name,album_name FROM songs JOIN authors ON authors_author_id = author_id JOIN genres ON genres_genre_id = genre_id JOIN albums ON albums_album_id = album_id JOIN songs_has_orders ON song_id = songs_song_id JOIN orders ON orders_order_id = order_id WHERE order_id = ?";
    private static final String FIND_ALL_SONGS = "SELECT song_id,song_name,album_img,song_price,author_name,genre_name,album_name FROM songs JOIN authors ON authors_author_id = author_id JOIN genres ON genres_genre_id = genre_id JOIN albums ON albums_album_id = album_id ORDER BY RAND() LIMIT 10";
    private static final String FIND_SONG_BY_ALBUM_ID = "SELECT song_id,song_name,album_img,song_price,author_name,genre_name,album_name FROM songs JOIN authors ON authors_author_id = author_id JOIN genres ON genres_genre_id = genre_id JOIN albums ON albums_album_id = album_id WHERE album_id = ?";
    private static final String INSERT_INTO_SONGS_NEW_SONG = "INSERT INTO songs(song_name,song_price,genres_genre_id,authors_author_id,albums_album_id)VALUES(?,?,?,?,?)";
    private static final String REMOVE_SONG = "DELETE FROM songs WHERE song_id = ?";
    private static final String UPDATE_SONG = "UPDATE songs SET song_name = ?,song_price = ?,genres_genre_id = ?,authors_author_id = ?,albums_album_id = ? WHERE song_id = ?";

    @Override
    public List<Song> findAll() throws DaoException {
        List<Song> listOfSongs = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SONGS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long songId = resultSet.getLong(SONG_ID);
                String songName = resultSet.getString(SONG_NAME);
                String imageUrl = resultSet.getString(ALBUM_IMG);
                BigDecimal songPrice = resultSet.getBigDecimal(SONG_PRICE);
                String authorName = resultSet.getString(AUTHOR_NAME);
                String genreName = resultSet.getString(GENRE_NAME);
                String albumName = resultSet.getString(ALBUM_NAME);
                listOfSongs.add(Song.builder()
                        .setId(songId)
                        .setSongName(songName)
                        .setImageUrl(imageUrl)
                        .setPrice(songPrice)
                        .setAuthor(authorName)
                        .setGenre(genreName)
                        .setAlbum(albumName)
                        .build());
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException, finding all songs", e);
        }
        return listOfSongs;
    }

    @Override
    public Optional<Song> findSongById(Long songId) throws DaoException {
        Optional<Song> foundSong = Optional.empty();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_SONG_BY_ID)) {
            preparedStatement.setLong(1, songId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong(SONG_ID);
                String songName = resultSet.getString(SONG_NAME);
                String imageUrl = resultSet.getString(ALBUM_IMG);
                BigDecimal songPrice = resultSet.getBigDecimal(SONG_PRICE);
                String authorName = resultSet.getString(AUTHOR_NAME);
                String genreName = resultSet.getString(GENRE_NAME);
                String albumName = resultSet.getString(ALBUM_NAME);
                foundSong = Optional.of(Song.builder()
                        .setId(id)
                        .setSongName(songName)
                        .setImageUrl(imageUrl)
                        .setPrice(songPrice)
                        .setAuthor(authorName)
                        .setGenre(genreName)
                        .setAlbum(albumName)
                        .build());
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException in method finding song by id");
        }
        return foundSong;
    }


    @Override
    public List<Song> findSongByOrderId(Long orderId) throws DaoException {
        List<Song> listOfSongs = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_SONG_BY_ORDER_ID)) {
            preparedStatement.setLong(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String songName = resultSet.getString(SONG_NAME);
                BigDecimal songPrice = resultSet.getBigDecimal(SONG_PRICE);
                String authorName = resultSet.getString(AUTHOR_NAME);
                String genreName = resultSet.getString(GENRE_NAME);
                String albumName = resultSet.getString(ALBUM_NAME);
                listOfSongs.add(Song.builder()
                        .setId(orderId)
                        .setSongName(songName)
                        .setPrice(songPrice)
                        .setAuthor(authorName)
                        .setGenre(genreName)
                        .setAlbum(albumName)
                        .build());
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException in method finding song by order id");
        }
        return listOfSongs;
    }

    @Override
    public List<Song> findSongByAlbumId(Long albumId) throws DaoException {
        List<Song> listOfSongs = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_SONG_BY_ALBUM_ID)) {
            preparedStatement.setLong(1, albumId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong(SONG_ID);
                String songName = resultSet.getString(SONG_NAME);
                String imageUrl = resultSet.getString(ALBUM_IMG);
                BigDecimal songPrice = resultSet.getBigDecimal(SONG_PRICE);
                String authorName = resultSet.getString(AUTHOR_NAME);
                String genreName = resultSet.getString(GENRE_NAME);
                String albumName = resultSet.getString(ALBUM_NAME);
                listOfSongs.add(Song.builder()
                        .setId(id)
                        .setSongName(songName)
                        .setImageUrl(imageUrl)
                        .setPrice(songPrice)
                        .setAuthor(authorName)
                        .setGenre(genreName)
                        .setAlbum(albumName)
                        .build());
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException, finding song by album's id", e);
        }
        return listOfSongs;
    }


    @Override
    public void updateSong(String songName, BigDecimal songPrice, Long authorId, Long genreId, Long albumId, Long songId) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SONG)) {
            preparedStatement.setString(1, songName);
            preparedStatement.setBigDecimal(2, songPrice);
            preparedStatement.setLong(3, genreId);
            preparedStatement.setLong(4, authorId);
            preparedStatement.setLong(5, albumId);
            preparedStatement.setLong(6, songId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("SQLException, updating song by id", e);
        }
    }

    @Override
    public Long insertSong(String songName, BigDecimal songPrice, Long authorId, Long genreId, Long albumId) throws DaoException {
        Long id = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_SONGS_NEW_SONG, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, songName);
            preparedStatement.setBigDecimal(2, songPrice);
            preparedStatement.setLong(3, genreId);
            preparedStatement.setLong(4, authorId);
            preparedStatement.setLong(5, albumId);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getLong(1);
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException, inserting new song", e);
        }
        return id;
    }

    @Override
    public void removeSong(Long songId) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_SONG)) {
            preparedStatement.setLong(1, songId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("SQLException, removing song", e);
        }
    }
}
