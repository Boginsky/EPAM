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

    public static SongService getInstance(){
        while (instance == null){
            if(isSongService.compareAndSet(false,true)){
                instance = new SongServiceImpl();
            }
        }
        return instance;
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

    public Optional<Song> findSongById(Long songId) throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        SongDaoImpl songDaoImpl = new SongDaoImpl();
        try {
            transactionManager.startTransaction(songDaoImpl);
            return songDaoImpl.findById(songId);
        } catch (DaoException e) {
            throw new ServiceException("Exception in method finding song by id", e);
        } finally {
            transactionManager.endTransaction();
        }
    }

    public List<Song> findSongByName(String nameOfSong) throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        SongDaoImpl songDaoImpl = new SongDaoImpl();
        try {
            transactionManager.startTransaction(songDaoImpl);
            return songDaoImpl.findSongByName(nameOfSong);
        } catch (DaoException e) {
            throw new ServiceException("Exception in method finding song by name", e);
        } finally {
            transactionManager.endTransaction();
        }
    }

    public List<Song> findSongByAuthor(String authorFirstName,String authorLasName) throws ServiceException{
        TransactionManager transactionManager = new TransactionManager();
        SongDaoImpl songDaoImpl = new SongDaoImpl();
        try {
            transactionManager.startTransaction(songDaoImpl);
            return songDaoImpl.findSongByAuthor(authorFirstName,authorLasName);
        } catch (DaoException e) {
            throw new ServiceException("Exception in method finding song by author's name", e);
        } finally {
            transactionManager.endTransaction();
        }
    }

    public List<Song> findSongByGenre(String nameOfGenre) throws ServiceException{
        TransactionManager transactionManager = new TransactionManager();
        SongDaoImpl songDaoImpl = new SongDaoImpl();
        try {
            transactionManager.startTransaction(songDaoImpl);
            return songDaoImpl.findSongByGenre(nameOfGenre);
        } catch (DaoException e) {
            throw new ServiceException("Exception in method finding song by genre's name", e);
        } finally {
            transactionManager.endTransaction();
        }
    }

    public List<Song> findSongByAlbum(String nameOfAlbum) throws ServiceException{
        TransactionManager transactionManager = new TransactionManager();
        SongDaoImpl songDaoImpl = new SongDaoImpl();
        try {
            transactionManager.startTransaction(songDaoImpl);
            return songDaoImpl.findSongByAlbum(nameOfAlbum);
        } catch (DaoException e) {
            throw new ServiceException("Exception in method finding song by album's name", e);
        } finally {
            transactionManager.endTransaction();
        }
    }

    public void updateSongName(String songPreviousName, String songNewName ) throws ServiceException{
        TransactionManager transactionManager = new TransactionManager();
        SongDaoImpl songDaoImpl = new SongDaoImpl();
        try {
            transactionManager.startTransaction(songDaoImpl);
            songDaoImpl.updateSongName(songNewName,songPreviousName);
            transactionManager.commit();
        }catch (DaoException e){
            try {
                transactionManager.rollback();
            }catch (DaoException daoException){
                throw new ServiceException("Exception in method updating song's name(rollback)",daoException);
            }
            throw new ServiceException("Exception in method updating song's name",e);
        }
    }

    public void updateSongPrice(BigDecimal songPrice,String songName) throws ServiceException{
        TransactionManager transactionManager = new TransactionManager();
        SongDaoImpl songDaoImpl = new SongDaoImpl();
        try {
            transactionManager.startTransaction(songDaoImpl);
            songDaoImpl.updateSongPrice(songPrice,songName);
            transactionManager.commit();
        }catch (DaoException e){
            try {
                transactionManager.rollback();
            }catch (DaoException daoException){
                throw new ServiceException("Exception in method updating song's price(rollback)",daoException);
            }
            throw new ServiceException("Exception in method updating song's price",e);
        }
    }

    public void insertSong(String songName, String imageUrl, BigDecimal songPrice, Long authorId, Long genreId, Long albumId) throws ServiceException{
        TransactionManager transactionManager = new TransactionManager();
        SongDaoImpl songDaoImpl = new SongDaoImpl();
        try {
            transactionManager.startTransaction(songDaoImpl);
            songDaoImpl.insertSong(songName,imageUrl,songPrice,authorId,genreId,albumId);
            transactionManager.commit();
        }catch (DaoException e){
            try {
                transactionManager.rollback();
            }catch (DaoException daoException){
                throw new ServiceException("Exception in method adding new song(rollback)",daoException);
            }
            throw new ServiceException("Exception in method adding new song",e);
        }
    }

    public void insertSongWithoutAlbum(String songName, String imageUrl, BigDecimal songPrice, Long authorId, Long genreId) throws ServiceException{
        TransactionManager transactionManager = new TransactionManager();
        SongDaoImpl songDaoImpl = new SongDaoImpl();
        try {
            transactionManager.startTransaction(songDaoImpl);
            songDaoImpl.insertSongWithoutAlbum(songName,imageUrl,songPrice,authorId,genreId);
            transactionManager.commit();
        }catch (DaoException e){
            try {
                transactionManager.rollback();
            }catch (DaoException daoException){
                throw new ServiceException("Exception in method adding new song without album(rollback)",daoException);
            }
            throw new ServiceException("Exception in method adding new song without album",e);
        }
    }
}
