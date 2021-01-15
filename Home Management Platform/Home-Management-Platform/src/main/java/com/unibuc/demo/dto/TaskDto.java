package com.unibuc.demo.dto;

import com.unibuc.demo.domain.Status;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Component
public class TaskDto {
    @NotNull
    private String name;
    private String description;
    @NotNull
    @Min(1)
    private long priorityId;
    @NotNull
    private Status status;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
//    @Min(value = 0, message = "Person id must be positive")
    private long personId;

    public TaskDto() {
    }

    public TaskDto(String name, String description, long priorityId, Status status, Date startDate, Date endDate, long personId) {
        this.name = name;
        this.description = description;
        this.priorityId = priorityId;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.personId = personId;
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
