package com.alam.saif.messenger.exceptionHandler;

/**
 * Created by saif on 3/5/16.
 */
public class ResourceNotFound extends RuntimeException {

    public ResourceNotFound(String message) {
        super(message);
    }
}
