package by.boginsky.audiostore.model.dao.impl;

import by.boginsky.audiostore.exception.DaoException;
import by.boginsky.audiostore.model.dao.BaseDao;
import by.boginsky.audiostore.model.dao.GenreDao;
import by.boginsky.audiostore.model.entity.audio.Genre;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.boginsky.audiostore.model.dao.ColumnName.GENRE_ID;
import static by.boginsky.audiostore.model.dao.ColumnName.GENRE_NAME;

/**
 * The type Genre dao.
 */
public class GenreDaoImpl extends BaseDao<Genre> implements GenreDao {

    private static final String FIND_ALL_GENRES = "SELECT genre_id,genre_name FROM genres";

    @Override
    public List<Genre> findAll() throws DaoException {
        List<Genre> listOfGenres = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_GENRES)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long genreId = resultSet.getLong(GENRE_ID);
                String genreName = resultSet.getString(GENRE_NAME);
                listOfGenres.add(Genre.builder()
                        .setId(genreId)
                        .setGenreName(genreName)
                        .build());
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException, finding all genres", e);
        }
        return listOfGenres;
    }
}
