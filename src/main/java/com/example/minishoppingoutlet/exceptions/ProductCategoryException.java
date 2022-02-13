package com.example.minishoppingoutlet.exceptions;

public class ProductCategoryException extends MiniShoppingException {

    public ProductCategoryException(String message) {
        super(message);
    }

    public ProductCategoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductCategoryException(Throwable cause) {
        super(cause);
    }

    public ProductCategoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
