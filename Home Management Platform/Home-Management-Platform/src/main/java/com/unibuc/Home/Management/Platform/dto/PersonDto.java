package com.unibuc.Home.Management.Platform.dto;

import com.unibuc.Home.Management.Platform.domain.Task;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;


public class PersonDto {
    @NotBlank(message = "First name cannot be null")
    private String firstName;
    @NotBlank(message = "Last name cannot be null")
    private String lastName;
   // @NotNull
    @Min(value = 3, message = "To use the application you must be at least 5 years old")
    private int age;
    @NotNull(message = "roleId cannot be null")
    @Min(value = 1,message = "roleId must be positive")
    private long roleId;

    public PersonDto() {
    }

    public PersonDto(String firstName, String lastName, int age, long roleId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.roleId = roleId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }
}
