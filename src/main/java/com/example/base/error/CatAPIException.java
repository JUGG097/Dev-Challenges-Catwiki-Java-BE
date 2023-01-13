package com.example.base.error;

public class CatAPIException extends Exception {
    public CatAPIException() {
        super();
    }

    public CatAPIException(String message) {
        super(message);
    }
}
