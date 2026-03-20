package org.example.exceptions;

public class CatalogException extends Exception{

    public CatalogException(Throwable cause) {
        super(cause);
    }

    public CatalogException(String message) {
        super(message);


    }
}
