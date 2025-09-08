package com.epam.greenandtasty.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;



public class ApiReader {
    static Properties properties;
    static {
        properties = new Properties();

        try (FileInputStream fileInput = new FileInputStream("src/main/resources/apis.properties")) {
            properties.load(fileInput);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String getBaseUrl(){
        return properties.getProperty("baseUrl");
    }
    public static String getSignUp(){
        return properties.getProperty("signUp");
    }
    public static String getSignIn(){
        return properties.getProperty("signIn");
    }
    public static String getLocations(){
        return properties.getProperty("locations");
    }

    public static String getCustomerBookings(){
        return properties.getProperty("customer_bookings");
    }


    public static String getWaiterBookings(){
        return properties.getProperty("waiter_bookings");
    }

    public static String getWaiterBookingById(){
        return properties.getProperty("waiter_booking_by_id");
    }

    public static String getWaiterEmail(){
        return properties.getProperty("waiter_email");
    }

    public static String getWaiterPassword(){
        return properties.getProperty("waiter_password");
    }

    public static String getCustomerEmail(){
        return properties.getProperty("customer_email");
    }

    public static String getCustomerPassword(){
        return properties.getProperty("customer_password");
    }

}
