package com.ub.webtechnologies.service;

import com.ub.webtechnologies.domain.Profile;
import com.ub.webtechnologies.domain.User;
import org.springframework.stereotype.Component;

@Component
public class ProfileService {
    public Profile createProfile(User user, int point){
        Profile.ProfileBuilder builder = new Profile.ProfileBuilder();
         return builder.user(user)
                .points(point)
                .build();
    }
}
