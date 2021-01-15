package com.unibuc.demo.exception;

public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException(long id) {
        super("Person with id " + id + " doesn't exist ");
    }
    public PersonNotFoundException(String firstName) {
        super("Person with firstName: " + firstName + " doesn't exist ");
    }
    public PersonNotFoundException(long id, String firstName){
        super(firstName + " you cannot change other people details!");
    }
}
