package model.dao;

import exception.DaoException;

public interface OrderHasSongDao {

    void insertOrderHasSong(Long orderId, Long songId) throws DaoException;
}
