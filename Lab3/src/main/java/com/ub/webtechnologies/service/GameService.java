package com.ub.webtechnologies.service;


import com.ub.webtechnologies.domain.Game;
import com.ub.webtechnologies.domain.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GameService {
    public Game createGame(List<User> users, String name){
        Game.GameBuilder builder = Game.builder();
        return builder
                .name(name)
                .users(users)
                .build();

    }
}
