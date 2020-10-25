package com.ub.webtechnologies.domain;

public class Profile {
    private final User user;
    private final int points;

    public Profile(ProfileBuilder profileBuilder) {
        this.user = profileBuilder.user;
        this.points = profileBuilder.points;
    }

    public User getUser() {
        return user;
    }

    public int getPoints() {
        return points;
    }

    public static class ProfileBuilder{
        private User user;
        private int points;

        public ProfileBuilder user (User user){
            this.user = user;
            return this;
        }
        public ProfileBuilder points (int points){
            this.points = points;
            return this;
        }
        public Profile build(){
            return new Profile(this);
        }
    }
}
