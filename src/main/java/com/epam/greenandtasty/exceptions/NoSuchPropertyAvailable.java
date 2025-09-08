package com.epam.greenandtasty.exceptions;

public class NoSuchPropertyAvailable extends RuntimeException {
    public NoSuchPropertyAvailable() {
        super("No such Property is set in the config.properties");
    }

    public NoSuchPropertyAvailable(String message) {
        super("No such Property is set in the config.properties " + message);
    }
}
