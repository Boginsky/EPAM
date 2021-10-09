package by.boginsky.audiostore.model.dao.impl;

import by.boginsky.audiostore.exception.DaoException;
import by.boginsky.audiostore.model.dao.BaseDao;
import by.boginsky.audiostore.model.dao.SongDao;
import by.boginsky.audiostore.model.entity.audio.Song;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static by.boginsky.audiostore.model.dao.ColumnName.*;

public class SongDaoImpl extends BaseDao implements SongDao {

    private static final String FIND_SONG_BY_ID = "SELECT song_id,song_name,song_img,song_price,author_name,genre_name,album_name FROM songs JOIN authors ON authors_author_id = author_id JOIN genres ON genres_genre_id = genre_id JOIN albums ON albums_album_id = album_id WHERE song_id = ?";
    private static final String FIND_SONG_BY_ORDER_ID = "SELECT song_id,song_name,song_price,author_name,genre_name,album_name FROM songs JOIN authors ON authors_author_id = author_id JOIN genres ON genres_genre_id = genre_id JOIN albums ON albums_album_id = album_id JOIN songs_has_orders ON song_id = songs_song_id JOIN orders ON orders_order_id = order_id WHERE order_id = ?";
    private static final String FIND_ALL_SONGS = "SELECT song_id,song_name,song_img,song_price,author_name,genre_name,album_name FROM songs JOIN authors ON authors_author_id = author_id JOIN genres ON genres_genre_id = genre_id JOIN albums ON albums_album_id = album_id ORDER BY RAND ()";
    private static final String FIND_SONG_BY_NAME = "SELECT song_name,song_img,song_price,author_id,genre_id,album_id FROM songs JOIN authors ON authors_author_id = author_id JOIN genres ON genres_genre_id = genre_id JOIN albums ON albums_album_id = album_id WHERE song_name = ?";
    private static final String FIND_SONG_BY_AUTHOR_NAME = "SELECT song_name,song_img,song_price,author_id,genre_id,album_id FROM songs JOIN authors ON authors_author_id = author_id JOIN genres ON genres_genre_id = genre_id JOIN albums ON albums_album_id = album_id WHERE author_first_name = ? AND author_last_name = ?";
    private static final String FIND_SONG_BY_GENRE_NAME = "SELECT song_name,song_img,song_price,author_id,genre_id,album_id FROM songs JOIN authors ON authors_author_id = author_id JOIN genres ON genres_genre_id = genre_id JOIN albums ON albums_album_id = album_id WHERE genre_name = ?";
    private static final String FIND_SONG_BY_ALBUM_ID = "SELECT song_id,song_name,song_img,song_price,author_name,genre_name,album_name FROM songs JOIN authors ON authors_author_id = author_id JOIN genres ON genres_genre_id = genre_id JOIN albums ON albums_album_id = album_id WHERE album_id = ?";
    private static final String UPDATE_SONG_NAME_BY_NAME = "UPDATE songs SET song_name = ? WHERE song_name = ?";
    private static final String UPDATE_SONG_PRICE_BY_NAME = "UPDATE songs " +
            "SET song_price = ? " +
            "WHERE song_name = ?";
    private static final String INSERT_INTO_SONGS_NEW_SONG = "INSERT INTO songs(song_name,song_img,song_price,genres_genre_id,authors_author_id,albums_album_id)VALUES(?,?,?,?,?,?)";
    private static final String INSERT_INTO_SONGS_NEW_SONG_WITHOUT_ALBUM = "INSERT INTO songs(song_name,song_img,song_price,genres_genre_id,authors_author_id)VALUES(?,?,?,?,?)";
    private static final String FIND_SONG_IMG = "SELECT song_img FROM songs ORDER BY RAND() LIMIT 3";


    @Override
    public List<Song> findAll() throws DaoException {
        List<Song> listOfSongs = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SONGS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long songId = resultSet.getLong(SONG_ID);
                String songName = resultSet.getString(SONG_NAME);
                String imageUrl = resultSet.getString(SONG_IMG);
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
    public List<Song> findSongByName(String nameOfSong) throws DaoException {
        List<Song> listOfSongs = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_SONG_BY_NAME)) {
            preparedStatement.setString(1, nameOfSong);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String songName = resultSet.getString(SONG_NAME);
                String imageUrl = resultSet.getString(SONG_IMG);
                BigDecimal songPrice = resultSet.getBigDecimal(SONG_PRICE);
                Long authorId = resultSet.getLong(AUTHOR_ID);
                Long genreId = resultSet.getLong(GENRE_ID);
                Long albumId = resultSet.getLong(ALBUM_ID);
                listOfSongs.add(Song.builder()
                        .setSongName(songName)
                        .setImageUrl(imageUrl)
                        .setPrice(songPrice)
//                        .setAuthorId(authorId)
//                        .setGenreId(genreId)
//                        .setAlbumId(albumId)
                        .build());
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException, finding song by name", e);
        }
        return listOfSongs;
    }

    @Override
    public List<Song> findSongByAuthor(String firstNameOfAuthor, String lastNameOfAuthor) throws DaoException {
        List<Song> listOfSongs = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_SONG_BY_AUTHOR_NAME)) {
            preparedStatement.setString(1, firstNameOfAuthor);
            preparedStatement.setString(2, lastNameOfAuthor);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String songName = resultSet.getString(SONG_NAME);
                String imageUrl = resultSet.getString(SONG_IMG);
                BigDecimal songPrice = resultSet.getBigDecimal(SONG_PRICE);
                Long authorId = resultSet.getLong(AUTHOR_ID);
                Long genreId = resultSet.getLong(GENRE_ID);
                Long albumId = resultSet.getLong(ALBUM_ID);
                listOfSongs.add(Song.builder()
                        .setSongName(songName)
                        .setImageUrl(imageUrl)
                        .setPrice(songPrice)
//                        .setAuthorId(authorId)
//                        .setGenreId(genreId)
//                        .setAlbumId(albumId)
                        .build());
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException, finding song by author's name", e);
        }
        return listOfSongs;
    }

    @Override
    public List<Song> findSongByGenre(String nameOfGenre) throws DaoException {
        List<Song> listOfSongs = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_SONG_BY_GENRE_NAME)) {
            preparedStatement.setString(1, nameOfGenre);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String songName = resultSet.getString(SONG_NAME);
                String imageUrl = resultSet.getString(SONG_IMG);
                BigDecimal songPrice = resultSet.getBigDecimal(SONG_PRICE);
                Long authorId = resultSet.getLong(AUTHOR_ID);
                Long genreId = resultSet.getLong(GENRE_ID);
                Long albumId = resultSet.getLong(ALBUM_ID);
                listOfSongs.add(Song.builder()
                        .setSongName(songName)
                        .setImageUrl(imageUrl)
                        .setPrice(songPrice)
//                        .setAuthorId(authorId)
//                        .setGenreId(genreId)
//                        .setAlbumId(albumId)
                        .build());
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException, finding song by genre's name", e);
        }
        return listOfSongs;
    }

    @Override
    public Optional<Song> findSongById(Long songId) throws DaoException {
        Optional<Song> foundSong = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_SONG_BY_ID)) {
            preparedStatement.setLong(1, songId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong(SONG_ID);
                String songName = resultSet.getString(SONG_NAME);
                String imageUrl = resultSet.getString(SONG_IMG);
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
                String imageUrl = resultSet.getString(SONG_IMG);
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
    public List<String> findSongImg() throws DaoException {
        List<String> listOfSongsImgUrl = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_SONG_IMG)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String imageUrl = resultSet.getString(SONG_IMG);
                listOfSongsImgUrl.add(imageUrl);
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException, finding song's img", e);
        }
        return listOfSongsImgUrl;
    }

    @Override
    public void updateSongName(String songPreviousName, String songNewName) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SONG_NAME_BY_NAME)) {
            preparedStatement.setString(1, songNewName);
            preparedStatement.setString(2, songPreviousName);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("SQLException,updating song's name", e);
        }
    }

    @Override
    public void updateSongPrice(BigDecimal songPrice, String songName) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SONG_PRICE_BY_NAME)) {
            preparedStatement.setBigDecimal(1, songPrice);
            preparedStatement.setString(2, songName);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("SQLException,updating song's price", e);
        }
    }

    @Override
    public void insertSong(String songName, String imageUrl, BigDecimal songPrice, Long authorId, Long genreId, Long albumId) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_SONGS_NEW_SONG)) {
            preparedStatement.setString(1, songName);
            preparedStatement.setString(2, imageUrl);
            preparedStatement.setBigDecimal(3, songPrice);
            preparedStatement.setLong(4, authorId);
            preparedStatement.setLong(5, genreId);
            preparedStatement.setLong(6, albumId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("SQLException, inserting new song", e);
        }
    }

    @Override
    public void insertSongWithoutAlbum(String songName, String imageUrl, BigDecimal songPrice, Long authorId, Long genreId) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_SONGS_NEW_SONG_WITHOUT_ALBUM)) {
            preparedStatement.setString(1, songName);
            preparedStatement.setString(2, imageUrl);
            preparedStatement.setBigDecimal(3, songPrice);
            preparedStatement.setLong(4, authorId);
            preparedStatement.setLong(5, genreId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("SQLException, inserting new song without album", e);
        }
    }
}
