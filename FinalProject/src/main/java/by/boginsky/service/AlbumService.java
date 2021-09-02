package by.boginsky.service;

import by.boginsky.exception.DaoException;
import by.boginsky.exception.ServiceException;
import by.boginsky.model.dao.TransactionManager;
import by.boginsky.model.dao.impl.AlbumDaoImpl;
import by.boginsky.model.entity.audio.Album;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public class AlbumService {

    private AlbumService() {
    }

    public static List<Album> findAllAlbums() throws ServiceException {
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

    public static Optional<Album> findAlbumByName(String albumName) throws ServiceException {
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


    public static List<Album> findAlbumByGenre(String nameOfGenre) throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        AlbumDaoImpl albumDaoImpl = new AlbumDaoImpl();
        try {
            transactionManager.startTransaction(albumDaoImpl);
            return albumDaoImpl.findByGenre(nameOfGenre);
        } catch (DaoException e) {
            throw new ServiceException("Exception in method finding album by genre name", e);
        } finally {
            transactionManager.endTransaction();
        }
    }

    public static void addNewAlbum(String nameOfAlbum, Timestamp dateOfCreation, String informationAboutAlbum) throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        AlbumDaoImpl albumDaoImpl = new AlbumDaoImpl();
        try {
            transactionManager.startTransaction(albumDaoImpl);
            albumDaoImpl.insertAlbum(nameOfAlbum,dateOfCreation,informationAboutAlbum);
            transactionManager.commit();
        }catch (DaoException e){
            try {
                transactionManager.rollback();
            } catch (DaoException daoException) {
                throw new ServiceException("Exception in method adding new album (rollback)",daoException);
            }
            throw new ServiceException("Exception in method adding new album",e);
        }finally {
            transactionManager.endTransaction();
        }
    }

    public static Optional<Album> findAlbumById(Long albumId) throws ServiceException{
        TransactionManager transactionManager = new TransactionManager();
        AlbumDaoImpl albumDaoImpl = new AlbumDaoImpl();
        try {
            transactionManager.startTransaction(albumDaoImpl);
            return albumDaoImpl.findById(albumId);
        }catch (DaoException e){
            throw new ServiceException("Exception in method finding album by id",e);
        }finally {
            transactionManager.endTransaction();
        }
    }
}
