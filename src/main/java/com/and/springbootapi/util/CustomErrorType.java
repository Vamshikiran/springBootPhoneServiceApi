package com.and.springbootapi.util;

/**
 * Created by vamshikirangullapelly on 24/11/2018.
 */

public class CustomErrorType {

    private String errorMessage;

    public CustomErrorType(String errorMessage){
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
