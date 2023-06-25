package com.victoria.library.exception;

public class BookException extends RuntimeException{
    private static final long serialVersionUID = 1l;
    public BookException(String message) { super(message);}
    public BookException(Throwable cause) { super(cause); }
    public BookException(String message, Throwable cause) { super(message, cause);}
}
