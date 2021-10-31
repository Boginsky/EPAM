package by.boginsky.audiostore.model.service.impl;

import by.boginsky.audiostore.exception.DaoException;
import by.boginsky.audiostore.exception.ServiceException;
import by.boginsky.audiostore.model.dao.TransactionManager;
import by.boginsky.audiostore.model.dao.impl.AlbumDaoImpl;
import by.boginsky.audiostore.model.entity.audio.Album;
import by.boginsky.audiostore.model.service.AlbumService;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * The type Album service.
 */
public class AlbumServiceImpl implements AlbumService {

    private static AlbumService instance;
    private static final Long MULTIPLIER = 12L;
    private static final AtomicBoolean isAlbumService = new AtomicBoolean(false);

    private AlbumServiceImpl() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
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

    @Override
    public List<String> findAlbumImg() throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        AlbumDaoImpl albumDaoImpl = new AlbumDaoImpl();
        try {
            transactionManager.startTransaction(albumDaoImpl);
            return albumDaoImpl.findAlbumImg();
        } catch (DaoException e) {
            throw new ServiceException("Exception in method finding album's img", e);
        } finally {
            transactionManager.endTransaction();
        }
    }

    public List<Album> findAllAlbums(Long pageId) throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        AlbumDaoImpl albumDaoImpl = new AlbumDaoImpl();
        Long startPosition = --pageId * MULTIPLIER;
        try {
            transactionManager.startTransaction(albumDaoImpl);
            return albumDaoImpl.findAll(startPosition);
        } catch (DaoException e) {
            throw new ServiceException("Exception in method finding all albums for page", e);
        } finally {
            transactionManager.endTransaction();
        }
    }

    @Override
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

    public Long addNewAlbum(String nameOfAlbum, String informationAboutAlbum) throws ServiceException {
        Long id; // FIXME: 29.10.2021 
        TransactionManager transactionManager = new TransactionManager();
        AlbumDaoImpl albumDaoImpl = new AlbumDaoImpl();
        try {
            transactionManager.startTransaction(albumDaoImpl);
            id = albumDaoImpl.insertAlbum(nameOfAlbum, informationAboutAlbum);
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
        return id;
    }

    @Override
    public List<Album> findByAuthor(Long authorId) throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        AlbumDaoImpl albumDaoImpl = new AlbumDaoImpl();
        try {
            transactionManager.startTransaction(albumDaoImpl);
            return albumDaoImpl.findByAuthor(authorId);
        } catch (DaoException e) {
            throw new ServiceException("Exception in method finding album by author's id", e);
        } finally {
            transactionManager.endTransaction();
        }
    }

    @Override
    public void removeAlbum(Long albumId) throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        AlbumDaoImpl albumDaoImpl = new AlbumDaoImpl();
        try {
            transactionManager.startTransaction(albumDaoImpl);
            albumDaoImpl.removeAlbum(albumId);
            transactionManager.commit();
        } catch (DaoException e) {
            try {
                transactionManager.rollback();
            } catch (DaoException daoException) {
                throw new ServiceException("Exception in method removing album (rollback)", daoException);
            }
            throw new ServiceException("Exception in method removing album", e);
        } finally {
            transactionManager.endTransaction();
        }
    }

    @Override
    public void updateAlbum(Long albumId, String albumName, String albumInfo) throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        AlbumDaoImpl albumDaoImpl = new AlbumDaoImpl();
        try {
            transactionManager.startTransaction(albumDaoImpl);
            albumDaoImpl.updateAlbum(albumId, albumName, albumInfo);
            transactionManager.commit();
        } catch (DaoException e) {
            try {
                transactionManager.rollback();
            } catch (DaoException daoException) {
                throw new ServiceException("Exception in method updating album (rollback)", daoException);
            }
            throw new ServiceException("Exception in method updating album", e);
        } finally {
            transactionManager.endTransaction();
        }
    }
}
