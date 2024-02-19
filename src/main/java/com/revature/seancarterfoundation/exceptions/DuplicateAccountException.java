package com.revature.seancarterfoundation.exceptions;

public class DuplicateAccountException extends Exception{
    public DuplicateAccountException(String errorMessage){
        super(errorMessage);
    }
    
}
