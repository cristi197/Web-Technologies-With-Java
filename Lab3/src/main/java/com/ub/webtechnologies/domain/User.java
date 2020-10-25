package com.ub.webtechnologies.domain;

public class User {

    private final String firstName;
    private final String lastName;
    private final int age;
    private final String additionalInfo;

    public User(UserBuilder userBuilder) {
    this.firstName = userBuilder.firstName;
    this.lastName = userBuilder.lastName;
    this.age = userBuilder.age;
    this.additionalInfo = userBuilder.additionalInfo;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public static class UserBuilder{
        private String firstName;
        private String lastName;
        private int age;
        private String additionalInfo;

        public UserBuilder firstName(String firstName)
        {
            this.firstName = firstName;
            return this;
        }
        public UserBuilder lastName(String lastName)
        {
            this.lastName = lastName;
            return this;
        }
        public UserBuilder age(int age)
        {
            this.age = age;
            return this;
        }
        public UserBuilder additionalInfo(String additionalInfo)
        {
            this.additionalInfo = additionalInfo;
            return this;
        }

        public User  builder(){
            return new User(this);
        }
    }
}
