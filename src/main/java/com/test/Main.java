package com.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.time.LocalTime;
import java.util.Locale;
import java.util.ResourceBundle;

public class Main {

    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("app", Locale.getDefault());

    public static String getMessage(LocalTime currentTime) {
        try {
            if (currentTime.getHour() >= 6 && currentTime.getHour() < 9) {
                return new String(resourceBundle.getString("app.morning").getBytes("ISO-8859-1"), "UTF-8");
            } else if (currentTime.getHour() >= 9 && currentTime.getHour() < 19) {
                return new String(resourceBundle.getString("app.day").getBytes("ISO-8859-1"), "UTF-8");
            } else if (currentTime.getHour() >= 19 && currentTime.getHour() < 23) {
                return new String(resourceBundle.getString("app.evening").getBytes("ISO-8859-1"), "UTF-8");
            } else if (currentTime.getHour() == 23 || currentTime.getHour() >= 0 && currentTime.getHour() < 6) {
                return new String(resourceBundle.getString("app.night").getBytes("ISO-8859-1"), "UTF-8");
            }
        }
        catch (UnsupportedEncodingException exception){
            LOG.debug("The invalid time " + currentTime + " was set\n" + exception.getMessage());
        }
        return "Some kind of mistake must be happened!";
    }
    public static void print() {
        System.out.println(getMessage(LocalTime.now()));
    }

    public static void main(String[] args) {
        LOG.info("Hello! We have a new request.");
        print();
    }
}
