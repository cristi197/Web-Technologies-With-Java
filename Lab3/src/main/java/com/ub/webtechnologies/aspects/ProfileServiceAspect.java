package com.ub.webtechnologies.aspects;

import com.ub.webtechnologies.domain.Profile;
import com.ub.webtechnologies.domain.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ProfileServiceAspect {
//    @Around("execution(* com.ub.webtechnologies.service.ProfileService.createProfile(..)) && args(user, points)")
//    public Object around(ProceedingJoinPoint joinPoint, User user, int points){
//        System.out.println("This method was called with:" + user.getFirstName() + "-"+points);
//        System.out.println("This method return sometring else!!");
//        Profile.ProfileBuilder builder = new  Profile.ProfileBuilder();
//        return builder
//                .user(new User.UserBuilder()
//                        .firstName("OtherUser")
//                        .builder())
//                .points(100)
//                .build();
//    }
    @Around("execution(* com.ub.webtechnologies.service.ProfileService.createProfile(..)) && args(user, points)")
    public Object around(ProceedingJoinPoint joinPoint, User user, int points){
        System.out.println("This method was called with:" + user.getFirstName() + "-"+points);
        Object result = null;
        try {
            Object[] objects = new Object[2];
            objects[0] = user;
            objects[1] = 1200;
            result = joinPoint.proceed(objects);
        }
        catch (Throwable throwable)
        {
            throwable.printStackTrace();
        }
        return result;
    }
}
