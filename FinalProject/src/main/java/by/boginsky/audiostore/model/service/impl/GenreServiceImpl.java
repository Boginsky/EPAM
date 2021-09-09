package by.boginsky.audiostore.model.service.impl;

import by.boginsky.audiostore.exception.DaoException;
import by.boginsky.audiostore.exception.ServiceException;
import by.boginsky.audiostore.model.dao.TransactionManager;
import by.boginsky.audiostore.model.dao.impl.GenreDaoImpl;
import by.boginsky.audiostore.model.entity.audio.Genre;
import by.boginsky.audiostore.model.service.GenreService;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public class GenreServiceImpl implements GenreService {

    private static GenreService instance;
    private static final AtomicBoolean isGenreService = new AtomicBoolean(false);

    private GenreServiceImpl(){}

    public static GenreService getInstance(){
        while(instance == null){
            if(isGenreService.compareAndSet(false,true)){
                instance = new GenreServiceImpl();
            }
        }
        return instance;
    }

    public List<Genre> findAllGenres() throws ServiceException{
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

    public Optional<Genre> findByName(String genreName) throws ServiceException{
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

    public void addNewGenre(String genreName, String informationAboutGenre) throws ServiceException{
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

}
