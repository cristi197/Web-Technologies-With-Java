package com.unibuc.demo.exception;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(long id) {
        super("Task with id " + id + " doesn't exist ");
    }
    public TaskNotFoundException(String status) {
        super("Status " + status + " doesn't exist ");
    }
    public TaskNotFoundException() {
        super("Task doesn't exist ");
    }
}
