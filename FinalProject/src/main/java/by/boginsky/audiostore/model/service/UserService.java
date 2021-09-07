package by.boginsky.audiostore.model.service;

import by.boginsky.audiostore.exception.ServiceException;
import by.boginsky.audiostore.model.entity.user.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAllUsers() throws ServiceException;

    Optional<User> findById(Long userId) throws ServiceException;

    Optional<User> findUserByEmailAndPassword(String email, String password) throws ServiceException;

    Optional<Long> findUserByEmail(String email) throws ServiceException;

    void updateUserPasswordByEmail(String email, String newPassword) throws ServiceException;

    void updateUserEmail(String email, String newEmail) throws ServiceException;

    void addUserMoney(String email, BigDecimal additionSum, BigDecimal userMoney) throws ServiceException;

    void createUser(String email, String password, String firstName, String lastName, LocalDateTime dateOfCreation) throws ServiceException;
}
