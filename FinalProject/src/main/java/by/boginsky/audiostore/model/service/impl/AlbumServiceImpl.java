package by.boginsky.audiostore.model.service.impl;

import by.boginsky.audiostore.exception.DaoException;
import by.boginsky.audiostore.exception.ServiceException;
import by.boginsky.audiostore.model.dao.TransactionManager;
import by.boginsky.audiostore.model.dao.impl.AlbumDaoImpl;
import by.boginsky.audiostore.model.entity.audio.Album;
import by.boginsky.audiostore.model.service.AlbumService;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public class AlbumServiceImpl implements AlbumService {

    private static AlbumService instance;
    private static final AtomicBoolean isAlbumService = new AtomicBoolean(false);

    private AlbumServiceImpl() {
    }

    public static AlbumService getInstance() {
        while (instance == null) {
            if (isAlbumService.compareAndSet(false, true)) {
                instance = new AlbumServiceImpl();
            }
        }
        return instance;
    }

    @Override
    public Optional<Album> findAlbumById(Long albumId) throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        AlbumDaoImpl albumDaoImpl = new AlbumDaoImpl();
        try {
            transactionManager.startTransaction(albumDaoImpl);
            return albumDaoImpl.findById(albumId);
        } catch (DaoException e) {
            throw new ServiceException("Exception in method finding album by id", e);
        } finally {
            transactionManager.endTransaction();
        }
    }

    public List<Album> findAllAlbums() throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        AlbumDaoImpl albumDaoImpl = new AlbumDaoImpl();
        try {
            transactionManager.startTransaction(albumDaoImpl);
            return albumDaoImpl.findAll();
        } catch (DaoException e) {
            throw new ServiceException("Exception in method finding all albums", e);
        } finally {
            transactionManager.endTransaction();
        }
    }

    public Optional<Album> findAlbumByName(String albumName) throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        AlbumDaoImpl albumDaoImpl = new AlbumDaoImpl();
        try {
            transactionManager.startTransaction(albumDaoImpl);
            return albumDaoImpl.findByName(albumName);
        } catch (DaoException e) {
            throw new ServiceException("Exception in method finding album by name", e);
        } finally {
            transactionManager.endTransaction();
        }
    }

    @Override
    public List<Album> findAlbumByGenre(String nameOfGenre) throws ServiceException {
        return null;
    }


    public void addNewAlbum(String nameOfAlbum, LocalDateTime dateOfCreation, String informationAboutAlbum) throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        AlbumDaoImpl albumDaoImpl = new AlbumDaoImpl();
        try {
            transactionManager.startTransaction(albumDaoImpl);
            albumDaoImpl.insertAlbum(nameOfAlbum, Timestamp.valueOf(dateOfCreation), informationAboutAlbum);
            transactionManager.commit();
        } catch (DaoException e) {
            try {
                transactionManager.rollback();
            } catch (DaoException daoException) {
                throw new ServiceException("Exception in method adding new album (rollback)", daoException);
            }
            throw new ServiceException("Exception in method adding new album", e);
        } finally {
            transactionManager.endTransaction();
        }
    }

    @Override
    public List<Album> findByAuthor(Long authorID) throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        AlbumDaoImpl albumDaoImpl = new AlbumDaoImpl();
        try {
            transactionManager.startTransaction(albumDaoImpl);
            return albumDaoImpl.findByAuthor(authorID);
        } catch (DaoException e) {
            throw new ServiceException("Exception in method finding album by author's id", e);
        } finally {
            transactionManager.endTransaction();
        }
    }
}
