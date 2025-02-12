package com.eleven.mvp_back.exception;

public class ResourceAlreadyExistException extends RuntimeException {
    public ResourceAlreadyExistException(String message) {
        super(message + "already exists.");
    }
}
