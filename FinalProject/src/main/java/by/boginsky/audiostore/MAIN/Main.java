package by.boginsky.audiostore.MAIN;

import by.boginsky.audiostore.exception.DaoException;
import by.boginsky.audiostore.exception.PropertyReaderException;
import by.boginsky.audiostore.exception.ServiceException;
import by.boginsky.audiostore.model.dao.TransactionManager;
import by.boginsky.audiostore.model.dao.impl.SongDaoImpl;
import by.boginsky.audiostore.util.MailSender;
import by.boginsky.audiostore.util.PasswordEncryption;

public class Main {
    public static void main(String[] args) {
        MailSender mailSender = new MailSender();
        try {
            mailSender.sendEmail("boginsky.kirill.workspace@gmail.com","123123123123");
        } catch (PropertyReaderException e) {
            e.printStackTrace();
        }
    }
}
