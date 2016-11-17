package com.test;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.ResourceBundle;

import static com.test.Main.getMessage;

public class MainTest {

    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("app", Locale.getDefault());
    private static LocalTime currentTimeMorning = LocalTime.parse("06:00");
    private static LocalTime currentTimeDay = LocalTime.parse("14:00");
    private static LocalTime currentTimeEvening = LocalTime.parse("22:00");
    private static LocalTime currentTimeNight = LocalTime.parse("00:00");
    private String lang = Locale.getDefault().getLanguage();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void getMorningMessageTest()throws Exception{
        getMessage(currentTimeMorning);
        if (lang.equals("en")){
            Assert.assertEquals("Good morning, World!",resourceBundle.getString("app.morning"));
        }
        else if (lang.equals("ru")){
            Assert.assertEquals("Доброе утро, мир!", new String(resourceBundle.getString("app.morning").getBytes("ISO-8859-1"),"UTF-8"));
        }
    }


    @Test
    public void getDayMessageTest()throws Exception{
        getMessage(currentTimeDay);
        if (lang.equals("en")){
            Assert.assertEquals("Good day, World!",resourceBundle.getString("app.day"));
        }
        else if (lang.equals("ru")){
            Assert.assertEquals("Добрый день, мир!", new String(resourceBundle.getString("app.day").getBytes("ISO-8859-1"),"UTF-8"));
        }
    }
    @Test
    public void getEveningMessageTest()throws Exception{
        getMessage(currentTimeEvening);
        if (lang.equals("en")){
            Assert.assertEquals("Good evening, World!",resourceBundle.getString("app.evening"));
        }
        else if (lang.equals("ru")){
            Assert.assertEquals("Добрый вечер, мир!", new String(resourceBundle.getString("app.evening").getBytes("ISO-8859-1"),"UTF-8"));
        }
    }

    @Test
    public void getNightMessageTest()throws Exception{
        getMessage(currentTimeNight);
        if (lang.equals("en")){
            Assert.assertEquals("Good night, World!",resourceBundle.getString("app.night"));
        }
        else if (lang.equals("ru")){
            Assert.assertEquals("Доброй ночи, мир!", new String(resourceBundle.getString("app.night").getBytes("ISO-8859-1"),"UTF-8"));
        }
    }

    @Test
    public void getInitializerError() throws Exception{
        thrown.expect(DateTimeParseException.class);
        getMessage(LocalTime.parse("24:00"));
    }


}