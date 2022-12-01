package com.arkinservices.exception;

public class ErrorMessage {

    private ErrorMessage() {
        // private constructor since this is a constants class
    }

    public static final String INTERNAL_SERVER_ERROR = "General internal server error has occured.";
    public static final String NAME_IS_REQUIRED = "Name is required.";

}
