package com.ub.webtechnologies;

import com.ub.webtechnologies.configuration.ApplicationConfiguration;
import com.ub.webtechnologies.domain.Profile;
import com.ub.webtechnologies.domain.User;
import com.ub.webtechnologies.service.ProfileService;
import com.ub.webtechnologies.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String[] args) {
      AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);

     UserService userService =  context.getBean(UserService.class);
     User user = userService.createUser("Silvia","Butan",27);

     ProfileService profileService = context.getBean(ProfileService.class);
     Profile profile = profileService.createProfile(user,0);

        System.out.println(profile.getUser().getFirstName());
        System.out.println(profile.getPoints());

      //  System.out.println(user.getFirstName());
    }
}
