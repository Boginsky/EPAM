package by.boginsky.audiostore.model.service.impl;

import by.boginsky.audiostore.exception.DaoException;
import by.boginsky.audiostore.exception.ServiceException;
import by.boginsky.audiostore.model.dao.TransactionManager;
import by.boginsky.audiostore.model.dao.impl.SongDaoImpl;
import by.boginsky.audiostore.model.entity.audio.Song;
import by.boginsky.audiostore.model.service.SongService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public class SongServiceImpl implements SongService {

    private static SongService instance;
    private static final AtomicBoolean isSongService = new AtomicBoolean(false);

    private SongServiceImpl() {
    }

    public static SongService getInstance() {
        while (instance == null) {
            if (isSongService.compareAndSet(false, true)) {
                instance = new SongServiceImpl();
            }
        }
        return instance;
    }

    @Override
    public Optional<Song> findSongById(Long songId) throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        SongDaoImpl songDaoImpl = new SongDaoImpl();
        try {
            transactionManager.startTransaction(songDaoImpl);
            return songDaoImpl.findSongById(songId);
        } catch (DaoException e) {
            throw new ServiceException("Exception in method finding song by id", e);
        } finally {
            transactionManager.endTransaction();
        }
    }


    public List<Song> findAllSongs() throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        SongDaoImpl songDaoImpl = new SongDaoImpl();
        try {
            transactionManager.startTransaction(songDaoImpl);
            return songDaoImpl.findAll();
        } catch (DaoException e) {
            throw new ServiceException("Exception in method finding all songs", e);
        } finally {
            transactionManager.endTransaction();
        }
    }

    public List<Song> findSongByAlbumId(Long albumId) throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        SongDaoImpl songDaoImpl = new SongDaoImpl();
        try {
            transactionManager.startTransaction(songDaoImpl);
            return songDaoImpl.findSongByAlbumId(albumId);
        } catch (DaoException e) {
            throw new ServiceException("Exception in method finding song by album's name", e);
        } finally {
            transactionManager.endTransaction();
        }
    }

    @Override
    public List<Song> findSongByOrderId(Long orderId) throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        SongDaoImpl songDaoImpl = new SongDaoImpl();
        try {
            transactionManager.startTransaction(songDaoImpl);
            return songDaoImpl.findSongByOrderId(orderId);
        } catch (DaoException e) {
            throw new ServiceException("Exception in method finding song by order's id", e);
        } finally {
            transactionManager.endTransaction();
        }
    }

    @Override
    public List<String> findSongImg() throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        SongDaoImpl songDaoImpl = new SongDaoImpl();
        try {
            transactionManager.startTransaction(songDaoImpl);
            return songDaoImpl.findSongImg();
        } catch (DaoException e) {
            throw new ServiceException("Exception in method finding song's img", e);
        } finally {
            transactionManager.endTransaction();
        }
    }

    public Long addNewSong(String songName,  BigDecimal songPrice, Long authorId, Long genreId, Long albumId) throws ServiceException {
        Long id;
        TransactionManager transactionManager = new TransactionManager();
        SongDaoImpl songDaoImpl = new SongDaoImpl();
        try {
            transactionManager.startTransaction(songDaoImpl);
            id = songDaoImpl.insertSong(songName, songPrice, authorId, genreId, albumId);
            transactionManager.commit();
        } catch (DaoException e) {
            try {
                transactionManager.rollback();
            } catch (DaoException daoException) {
                throw new ServiceException("Exception in method adding new song(rollback)", daoException);
            }
            throw new ServiceException("Exception in method adding new song", e);
        }
        return id;
    }

    @Override
    public void removeSong(Long songId) throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        SongDaoImpl songDaoImpl = new SongDaoImpl();
        try {
            transactionManager.startTransaction(songDaoImpl);
            songDaoImpl.removeSong(songId);
            transactionManager.commit();
        } catch (DaoException e) {
            try {
                transactionManager.rollback();
            } catch (DaoException daoException) {
                throw new ServiceException("Exception in method removing song(rollback)", daoException);
            }
            throw new ServiceException("Exception in method removing song", e);
        }
    }

    @Override
    public void updateSong(String songName, BigDecimal songPrice, Long authorId, Long genreId, Long albumId, Long songId) throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        SongDaoImpl songDaoImpl = new SongDaoImpl();
        try {
            transactionManager.startTransaction(songDaoImpl);
            songDaoImpl.updateSong(songName,songPrice,authorId,genreId,albumId,songId);
            transactionManager.commit();
        } catch (DaoException e) {
            try {
                transactionManager.rollback();
            } catch (DaoException daoException) {
                throw new ServiceException("Exception in method updating song(rollback)", daoException);
            }
            throw new ServiceException("Exception in method updating song", e);
        }
    }
}
