package com.example.minishoppingoutlet.exceptions;

public class MiniShoppingException extends RuntimeException{

    public MiniShoppingException(String message) {
        super(message);
    }

    public MiniShoppingException(String message, Throwable cause) {
        super(message, cause);
    }

    public MiniShoppingException(Throwable cause) {
        super(cause);
    }

    public MiniShoppingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
