package com.unibuc.demo.domain;

import lombok.Builder;

@Builder

public class Person {
    private long id;
    private String firstName;
    private String lastName;
    private int age;
    private long roleId;

    public Person() {
    }

    public Person(long id, String firstName, String lastName, int age, long roleId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.roleId = roleId;
    }

    public Person(String firstName, String lastName, int age, long roleId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.roleId = roleId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
