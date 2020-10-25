package com.ub.webtechnologies.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
@Builder
@Getter
public class Game {
    private final List<User> users;
    private final String name;
}
