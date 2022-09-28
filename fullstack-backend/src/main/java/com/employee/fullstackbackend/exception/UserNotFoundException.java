package com.employee.fullstackbackend.exception;

public class UserNotFoundException extends RuntimeException{
    //create constructor
    public UserNotFoundException(Long id){
        super("Could not found the user with id "+ id);
    }
}
