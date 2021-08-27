package model.dao.iml;

import exception.DaoException;
import model.dao.BaseDao;
import model.dao.GenreDao;
import model.entity.audio.Genre;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GenreDaoImpl extends BaseDao implements GenreDao {

    private static final String FIND_ALL_GENRES = "SELECT * FROM genres";
    private static final String FIND_GENRE_BY_ID = "SELECT * FROM genres WHERE genre_id = ?";
    private static final String FIND_GENRE_BY_NAME = "SELECT * FROM GENRES WHERE genre_name = ?";
    private static final String INSERT_INTO_GENRE = "INSERT INTO genres (genre_name, genre_info) VALUES(?,?)";

    @Override
    public List<Genre> findAll() throws DaoException {
        PreparedStatement preparedStatement = null;
        List<Genre> listOfGenres = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(FIND_ALL_GENRES);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String genreName = resultSet.getString(2);
                String informationAboutGenre = resultSet.getString(3);
                listOfGenres.add(new Genre(genreName, informationAboutGenre));
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException, finding all genres");
        } finally {
            if (connection != null) {
                close(preparedStatement);
            }
        }
        return listOfGenres;
    }

    @Override
    public Optional<Genre> findById(Long id) throws DaoException {
        PreparedStatement preparedStatement = null;
        Optional<Genre> genre = null;
        try {
            preparedStatement = connection.prepareStatement(FIND_GENRE_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String genreName = resultSet.getString(2);
                String informationAboutGenre = resultSet.getString(3);
                genre = Optional.of(new Genre(genreName, informationAboutGenre));
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException,finding genre by id");
        } finally {
            if (connection != null) {
                close(preparedStatement);
            }
        }
        return genre;
    }

    @Override
    public Optional<Long> findByName(String nameOfGenre) throws DaoException {
        PreparedStatement preparedStatement = null;
        Optional<Long> genreId = null;
        try {
            preparedStatement = connection.prepareStatement(FIND_GENRE_BY_NAME);
            preparedStatement.setString(1, nameOfGenre);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                genreId = Optional.of(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException, searchin genre by name");
        } finally {
            if (connection != null) {
                close(preparedStatement);
            }
        }
        return genreId;
    }

    @Override
    public void insertGenre(String genreName, String informationAboutGenre) throws DaoException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(INSERT_INTO_GENRE);
            preparedStatement.setString(1, genreName);
            preparedStatement.setString(2, informationAboutGenre);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("SQLException, inserting new genre");
        } finally {
            if (connection != null) {
                close(preparedStatement);
            }
        }
    }
}
