package by.boginsky.audiostore.model.service;

import by.boginsky.audiostore.exception.ServiceException;

import java.io.InputStream;

/**
 * The interface Image update service.
 */
public interface ImageUpdateService {

    /**
     * Update photo.
     *
     * @param inputStream the input stream
     * @param target      the target
     * @param targetId    the target id
     * @param fileName    the file name
     * @throws ServiceException the service exception
     */
    void updatePhoto(InputStream inputStream, String target, Long targetId, String fileName) throws ServiceException;
}
