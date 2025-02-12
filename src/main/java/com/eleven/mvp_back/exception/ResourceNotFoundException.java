package com.eleven.mvp_back.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resourceName, Long resourceId) {
        super(resourceName + " with ID " + resourceId + " Not Found.");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
