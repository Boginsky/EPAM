package by.boginsky.audiostore.model.service.impl;

import by.boginsky.audiostore.exception.DaoException;
import by.boginsky.audiostore.exception.ServiceException;
import by.boginsky.audiostore.model.dao.TransactionManager;
import by.boginsky.audiostore.model.dao.impl.AlbumDaoImpl;
import by.boginsky.audiostore.model.dao.impl.AuthorDaoImpl;
import by.boginsky.audiostore.model.dao.impl.UserDaoImpl;
import by.boginsky.audiostore.model.service.ImageUpdateService;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.lang.String.format;

/**
 * The type Image update service.
 */
public class ImageUpdateServiceImpl implements ImageUpdateService {

    private static final String USER = "user/";
    private static final String ALBUM = "album/";
    private static final String AUTHOR = "author/";
    private static final String PATH = "E:/EPAM/HoweWork/EPAM/FinalProject/images/";
    private static ImageUpdateService instance;
    private static final AtomicBoolean isImageUpdateService = new AtomicBoolean(false);

    private ImageUpdateServiceImpl() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static ImageUpdateService getInstance() {
        while (instance == null) {
            if (isImageUpdateService.compareAndSet(false, true)) {
                instance = new ImageUpdateServiceImpl();
            }
        }
        return instance;
    }

    @Override
    public void updatePhoto(InputStream inputStream, String target, Long targetId, String fileName) throws ServiceException {
        try {
            byte[] bytesForFile = new byte[inputStream.available()];
            String path = format("%s%s%s", PATH, target, fileName);
            inputStream.read(bytesForFile);
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            fileOutputStream.write(bytesForFile);
            fileOutputStream.flush();
            fileOutputStream.close();
            updatePhoto(targetId, target, path);
        } catch (IOException e) {
            throw new ServiceException("Exception in method update user photo", e);
        }
    }

    private void updatePhoto(Long targetId, String target, String path) throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        try {
            switch (target) {
                case USER:
                    UserDaoImpl userDaoImpl = new UserDaoImpl();
                    transactionManager.startTransaction(userDaoImpl);
                    userDaoImpl.updateUserPhoto(path, targetId);
                    transactionManager.commit();
                    break;
                case ALBUM:
                    AlbumDaoImpl albumDaoImpl = new AlbumDaoImpl();
                    transactionManager.startTransaction(albumDaoImpl);
                    albumDaoImpl.updateAlbumPhoto(path, targetId);
                    transactionManager.commit();
                    break;
                case AUTHOR:
                    AuthorDaoImpl authorDaoImpl = new AuthorDaoImpl();
                    transactionManager.startTransaction(authorDaoImpl);
                    authorDaoImpl.updateAuthorPhoto(path, targetId);
                    transactionManager.commit();
                    break;
            }
        } catch (DaoException e) {
            try {
                transactionManager.rollback();
            } catch (DaoException daoException) {
                throw new ServiceException("Exception in method updating photo(rollback)", daoException);
            }
            throw new ServiceException("Exception in method updating photo", e);
        } finally {
            transactionManager.endTransaction();
        }
    }

}
