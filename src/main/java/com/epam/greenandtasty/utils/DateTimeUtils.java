package com.epam.greenandtasty.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeUtils {
    private static final Logger logger =
            LogManager.getLogger(DateTimeUtils.class);

    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter TIME_FORMATTER =
            DateTimeFormatter.ofPattern("HH:mm");

    private DateTimeUtils(){}

    public static boolean isValidDate(String dateStr){
        try{
            LocalDate.parse(dateStr, DATE_FORMATTER);
            return true;
        }catch(DateTimeParseException e){
            logger.warn("Invalid date format: {}", dateStr);
            return false;
        }
    }

    public static boolean isValidTime(String timeStr){
        try{
            LocalTime.parse(timeStr, TIME_FORMATTER);
            return true;
        }catch(DateTimeParseException e){
            logger.warn("Invalid time format: {}", timeStr);
            return false;
        }
    }

    public static String getCurrentDate(){
        return LocalDate.now().format(DATE_FORMATTER);
    }

    public static String getFutureDate(int daysToAdd){
        return LocalDate.now().plusDays(daysToAdd).format(DATE_FORMATTER);
    }

    public static boolean isTimeRangeValid(String startTime, String endTime){
        if(!isValidTime(startTime) || !isValidTime(endTime)){
            return false;
        }

        LocalTime start = LocalTime.parse(startTime, TIME_FORMATTER);
        LocalTime end = LocalTime.parse(endTime, TIME_FORMATTER);

        return end.isAfter(start);
    }
}
