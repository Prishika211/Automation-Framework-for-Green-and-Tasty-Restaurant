package com.epam.greenandtasty.models;

public class UserRegistration {

    private String firstName;
    private String lastName;
    private String email;
    private String password;

    UserRegistration(UserBuilder userBuilder){
        this.firstName = userBuilder.firstName;
        this.lastName = userBuilder.lastName;
        this.email = userBuilder.email;
        this.password = userBuilder.password;
    }
    //getters
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public String getPassword(){
        return password;
    }

    public String getEmail() {
        return email;
    }

    //builder
    public static class UserBuilder{

        private String firstName;
        private String lastName;
        private String email;
        private String password;
        //Setters
        public UserBuilder setFirstName(String firstName){
            this.firstName = firstName;
            return this;
        }
        public UserBuilder setLastName(String lastName){
            this.lastName = lastName;
            return this;
        }
        public UserBuilder setEmail(String email){
            this.email = email;
            return this;
        }
        public UserBuilder setPassword(String password){
            this.password = password;
            return this;
        }
        public UserRegistration builder(){
            return new UserRegistration(this);
        }

    }





}
