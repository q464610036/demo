package org.jeecg.modules.common.entity;

public class ServiceException extends RuntimeException{
    public ServiceException(String message) {
        super(message);
    }
}
