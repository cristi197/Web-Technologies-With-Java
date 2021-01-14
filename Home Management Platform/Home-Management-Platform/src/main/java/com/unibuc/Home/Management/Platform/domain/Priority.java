package com.unibuc.Home.Management.Platform.domain;

import lombok.Builder;

@Builder
public class Priority {
    private long id;
    private String name;
    private int priorityNumber;

    public Priority() {
    }

    public Priority(long id, String name, int priorityNumber) {
        this.id = id;
        this.name = name;
        this.priorityNumber = priorityNumber;
    }

    public Priority(String name, int priorityNumber) {
        this.name = name;
        this.priorityNumber = priorityNumber;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPriorityNumber() {
        return priorityNumber;
    }

    public void setPriorityNumber(int priorityNumber) {
        this.priorityNumber = priorityNumber;
    }
}
