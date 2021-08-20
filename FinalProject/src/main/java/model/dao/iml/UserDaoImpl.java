package model.dao.iml;

import exception.DaoException;
import model.dao.UserDao;
import model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class UserDaoImpl implements UserDao {

    private static Logger logger = LogManager.getLogger();

    private static UserDaoImpl instance;

    private static final String SQL_FIND_ALL_USERS = "SELECT user_id, email,password,first_name,last_name,user_created,balance,user_role,user_status FROM users JOIN user_roles on user_roles_user_role_id = user_role_id JOIN user_statuses on user_statuses_user_status_id = user_status_id ";
    private static final String SQL_FIND_USER_BY_ID = "SELECT user_id, email,password,first_name,last_name,user_created,balance,user_role,user_status FROM users JOIN user_roles on user_roles_user_role_id = user_role_id JOIN user_statuses on user_statuses_user_status_id = user_status_id WHERE user_id = ?";

    private static final String SQL_CREATE_USER = "INSERT INTO users (email,password,first_name,last_name,user_created,balance,user_roles_user_role_id,user_statuses_user_status_id) VALUES(?,?,?,?,?,?,(SELECT user_role_id FROM user_roles WHERE role = ?),(SELECT user_status_id FROM user_statuses WHERE status = ?))";

    private static final String SQL_UPDATE_EMAIL = "UPDATE users SET email=? WHERE user_id =?";
    private static final String SQL_UPDATE_PASSWORD = "UPDATE users SET password =? WHERE user_id =?";
    private static final String SQL_UPDATE_FIRST_NAME = "UPDATE users SET first_name=? WHERE user_id =?";
    private static final String SQL_UPDATE_LAST_NAME = "UPDATE users SET last_name = ? WHERE user_id =?";
    private static final String SQL_UPDATE_BALANCE = "UPDATE users SET balance= ? WHERE user_id =?";
    private static final String SQL_UPDATE_ROLE = "UPDATE users SET user_roles_user_role_id WHERE user_id =?";
    private static final String SQL_UPDATE_STATUS = "UPDATE users SET user_statuses_user_status WHERE user_id =?";

    private UserDaoImpl() {
    }

    public static UserDaoImpl getInstance() {
        if (instance == null) {
            instance = new UserDaoImpl();
        }
        return instance;
    }

    @Override
    public List<User> findAll() throws DaoException {
        return null;
    }

    @Override
    public User findEntityById(Long id) throws DaoException {
        return null;
    }

    @Override
    public boolean delete(User user) throws DaoException {
        return false;
    }

    @Override
    public boolean delete(Long id) throws DaoException {
        return false;
    }

    @Override
    public boolean create(User user) throws DaoException {
        return false;
    }

    @Override
    public User update(User user) throws DaoException {
        return null;
    }
}
