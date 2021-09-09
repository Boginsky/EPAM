package by.boginsky.audiostore.MAIN;

import by.boginsky.audiostore.exception.DaoException;
import by.boginsky.audiostore.exception.ServiceException;
import by.boginsky.audiostore.model.dao.TransactionManager;
import by.boginsky.audiostore.model.dao.impl.SongDaoImpl;
import by.boginsky.audiostore.util.PasswordEncryption;

public class Main {
    public static void main(String[] args) throws ServiceException, DaoException {
        System.out.println(PasswordEncryption.encryptsPassword("12"));
        TransactionManager transactionManager = new TransactionManager();
        SongDaoImpl songDaoImpl = new SongDaoImpl();
        transactionManager.startTransaction(songDaoImpl);
        System.out.println(songDaoImpl.findAll());
        transactionManager.endTransaction();
    }
}
