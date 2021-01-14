package com.unibuc.Home.Management.Platform.exception;

public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException(long id) {
        super("Person with id " + id + " doesn't exist ");
    }
    public PersonNotFoundException(String firstName) {
        super("Person with firstName: " + firstName + " doesn't exist ");
    }
}
