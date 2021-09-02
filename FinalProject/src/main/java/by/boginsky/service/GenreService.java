package by.boginsky.service;

import by.boginsky.exception.DaoException;
import by.boginsky.exception.ServiceException;
import by.boginsky.model.dao.TransactionManager;
import by.boginsky.model.dao.impl.GenreDaoImpl;
import by.boginsky.model.entity.audio.Genre;

import java.util.List;
import java.util.Optional;

public class GenreService {

    private GenreService(){}

    public static List<Genre> findAllGenres() throws ServiceException{
        TransactionManager transactionManager = new TransactionManager();
        GenreDaoImpl genreDaoImpl = new GenreDaoImpl();
        try {
            transactionManager.startTransaction(genreDaoImpl);
            return genreDaoImpl.findAll();
        }catch (DaoException e){
            throw new ServiceException("Exception in method finding all genres",e);
        }finally {
            transactionManager.endTransaction();
        }
    }

    public static Optional<Genre> findByName(String genreName) throws ServiceException{
        TransactionManager transactionManager = new TransactionManager();
        GenreDaoImpl genreDaoImpl = new GenreDaoImpl();
        try {
            transactionManager.startTransaction(genreDaoImpl);
            return genreDaoImpl.findByName(genreName);
        }catch (DaoException e){
            throw new ServiceException("Exception in method finding genre by name",e);
        }finally {
            transactionManager.endTransaction();
        }
    }

    public static void addNewGenre(String genreName, String informationAboutGenre) throws ServiceException{
        TransactionManager transactionManager = new TransactionManager();
        GenreDaoImpl genreDaoImpl = new GenreDaoImpl();
        try {
            transactionManager.startTransaction(genreDaoImpl);
            genreDaoImpl.insertGenre(genreName,informationAboutGenre);
            transactionManager.commit();
        }catch (DaoException e){
            try {
                transactionManager.rollback();
            }catch (DaoException daoException){
                throw new ServiceException("Exception in method adding new genre(rollback)",daoException);
            }
            throw new ServiceException("Exception in method adding new genre",e);
        }finally {
            transactionManager.endTransaction();
        }
    }

    public static Optional<Genre> findGenreById(Long genreId) throws ServiceException{
        TransactionManager transactionManager = new TransactionManager();
        GenreDaoImpl genreDaoImpl = new GenreDaoImpl();
        try {
            transactionManager.startTransaction(genreDaoImpl);
            return genreDaoImpl.findById(genreId);
        }catch (DaoException e){
            throw new ServiceException("Exception in method finding genre by id",e);
        }finally {
            transactionManager.endTransaction();
        }
    }
}
