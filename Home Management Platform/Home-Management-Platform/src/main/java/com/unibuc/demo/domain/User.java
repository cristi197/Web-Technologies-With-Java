package com.unibuc.demo.domain;

import lombok.Builder;

@Builder
public class User {
    private long id;
    private String username;
    private String password;
    private long personId;
    private long roleId;

    public User() {
    }

    public User(String username, String password, long personId, long roleId) {
        this.username = username;
        this.password = password;
        this.personId = personId;
        this.roleId = roleId;
    }

    public User(long id, String username, String password, long personId, long roleId) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.personId = personId;
        this.roleId = roleId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }
}
