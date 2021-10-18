package by.boginsky.audiostore.model.service;

import by.boginsky.audiostore.exception.ServiceException;

import java.io.InputStream;

public interface ImageUpdateService {

    void updatePhoto(InputStream inputStream, String target, Long targetId,String fileName) throws ServiceException;
}
