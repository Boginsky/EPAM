package by.boginsky.audiostore.model.dao.impl;

import by.boginsky.audiostore.exception.DaoException;
import by.boginsky.audiostore.model.entity.audio.Genre;
import by.boginsky.audiostore.model.dao.BaseDao;
import by.boginsky.audiostore.model.dao.GenreDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static by.boginsky.audiostore.model.dao.ColumnName.*;

public class GenreDaoImpl extends BaseDao implements GenreDao {

    private static final String FIND_ALL_GENRES = "SELECT genre_name,genre_info FROM genres";
    private static final String FIND_GENRE_BY_ID = "SELECT genre_name,genre_info FROM genres WHERE genre_id = ?";
    private static final String FIND_GENRE_BY_NAME = "SELECT genre_name,genre_info FROM genres WHERE genre_name = ?";
    private static final String INSERT_INTO_GENRE = "INSERT INTO genres (genre_name, genre_info) VALUES(?,?)";

    @Override
    public List<Genre> findAll() throws DaoException {
        List<Genre> listOfGenres = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_GENRES)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String genreName = resultSet.getString(GENRE_NAME);
                String informationAboutGenre = resultSet.getString(GENRE_INFO);
                listOfGenres.add(Genre.builder()
                        .setGenreName(genreName)
                        .setInformationAboutGenre(informationAboutGenre)
                        .build());
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException, finding all genres", e);
        }
        return listOfGenres;
    }

    @Override
    public Optional<Genre> findByName(String nameOfGenre) throws DaoException {
        Optional<Genre> genre = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_GENRE_BY_NAME)) {
            preparedStatement.setString(1, nameOfGenre);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String genreName = resultSet.getString(GENRE_NAME);
                String informationAboutGenre = resultSet.getString(GENRE_INFO);
                genre = Optional.of(Genre.builder()
                        .setGenreName(genreName)
                        .setInformationAboutGenre(informationAboutGenre)
                        .build());
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException, searching genre by name", e);
        }
        return genre;
    }

    @Override
    public void insertGenre(String genreName, String informationAboutGenre) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_GENRE)) {
            preparedStatement.setString(1, genreName);
            preparedStatement.setString(2, informationAboutGenre);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("SQLException, inserting new genre", e);
        }
    }
}
