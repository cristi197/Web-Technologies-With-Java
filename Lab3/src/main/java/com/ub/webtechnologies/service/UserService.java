package com.ub.webtechnologies.service;

import com.ub.webtechnologies.domain.User;
import org.springframework.stereotype.Component;

@Component
public class UserService {
    public User createUser(String firstName, String lastName, int age){
        System.out.println(String.format("Request to create user with name %s",firstName));
        User.UserBuilder userBuilder = new User.UserBuilder();
        User user =  userBuilder
                        .firstName(firstName)
                        .lastName(lastName)
                        .age(age)
                        .builder();

        System.out.println(String.format("User with name %s was created",firstName));
       // throw new RuntimeException();
        return user;
    }
}
