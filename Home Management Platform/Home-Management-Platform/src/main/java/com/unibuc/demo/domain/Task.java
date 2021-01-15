package com.unibuc.demo.domain;

import lombok.Builder;

import java.sql.Date;


@Builder
public class Task {
    private long id;
    private String name;
    private String description;
    private long priorityId;
    private Status status;
    private Date startDate;
    private Date endDate;
    private long personId;

    public Task() {
    }

    public Task(long id, String name, String description, long priorityId, Status status, Date startDate, Date endDate, long personId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.priorityId = priorityId;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.personId = personId;
    }

    public Task(String name, String description, long priorityId, Status status, Date startDate, Date endDate, long personId) {
        this.name = name;
        this.description = description;
        this.priorityId = priorityId;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.personId = personId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getPriorityId() {
        return priorityId;
    }

    public void setPriorityId(long priorityId) {
        this.priorityId = priorityId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }
}
