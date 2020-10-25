package com.ub.webtechnologies.aspects;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserServiceAspect {
//    @Before("execution(* com.ub.webtechnologies.service.UserService.createUser(..))")
//    public void before(){
//        System.out.println("Request to create user...");
//    }
//
//    @After("execution(* com.ub.webtechnologies.service.UserService.createUser(..))")
//    public void after(){
//        System.out.println("After user was created...");
//    }
//
//    @AfterReturning("execution(* com.ub.webtechnologies.service.UserService.createUser(..))")
//    public void afterReturning(){
//        System.out.println("User was created successfully...");
//    }
//
//    @AfterThrowing("execution(* com.ub.webtechnologies.service.UserService.createUser(..))")
//    public void afterThrowing(){
//        System.out.println("User flow was finished but error happened");
//    }
}
