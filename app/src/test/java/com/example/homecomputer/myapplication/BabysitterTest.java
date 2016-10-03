package com.example.homecomputer.myapplication;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Period;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class BabysitterTest {
    @Test
    public void whenBabysitterWorksUntilBedtime() throws Exception {
        // Arrange
        int expectedPay = 48;

        DateTime startTime = new DateTime(2016, 8, 29, 17, 0, 0);
        DateTime bedTime = new DateTime(2016, 8, 29, 21, 0, 0);
        DateTime endTime = new DateTime(2016, 8, 29, 21, 0, 0);

        Babysitter babysitter = new Babysitter(startTime, endTime, bedTime);

        // Act
        int actualPay = babysitter.calculatePay();

        // Assert
        assertEquals(expectedPay, actualPay);
    }

    @Test
    public void whenBabysitterWorksUntilMidnight() throws Exception {
        // Arrange
        int expectedPay = 72;

        DateTime startTime = new DateTime(2016, 8, 29, 17, 0, 0);
        DateTime bedTime = new DateTime(2016, 8, 29, 21, 0, 0);
        DateTime endTime = new DateTime(2016, 8, 30, 0, 0, 0);

        Babysitter babysitter = new Babysitter(startTime, endTime, bedTime);

        // Act
        int actualPay = babysitter.calculatePay();

        // Assert
        assertEquals(expectedPay, actualPay);
    }

    @Test
    public void whenBabysitterWorksAllPossibleHours() throws Exception {
        // Arrange
        int expectedPay = 136;

        DateTime startTime = new DateTime(2016, 8, 29, 17, 0, 0);
        DateTime bedTime = new DateTime(2016, 8, 29, 21, 0, 0);
        DateTime endTime = new DateTime(2016, 8, 30, 4, 0, 0);

        Babysitter babysitter = new Babysitter(startTime, endTime, bedTime);

        // Act
        int actualPay = babysitter.calculatePay();

        // Assert
        assertEquals(expectedPay, actualPay);
    }

    @Test
    public void whenBabysitterStartsWorkBefore5pmAndStopsWorkAfter4am() throws Exception {
        // Arrange
        int expectedPay = 136;

        DateTime startTime = new DateTime(2016, 8, 29, 16, 0, 0);
        DateTime bedTime = new DateTime(2016, 8, 29, 21, 0, 0);
        DateTime endTime = new DateTime(2016, 8, 30, 5, 0, 0);

        Babysitter babysitter = new Babysitter(startTime, endTime, bedTime);

        // Act
        int actualPay = babysitter.calculatePay();

        // Assert
        assertEquals(expectedPay, actualPay);
    }

    @Test
    public void whenBabysitterStartsWorkingAtMidnightAndStopsWorkingAt4am() throws Exception {
        // Arrange
        int expectedPay = 64;

        DateTime startTime = new DateTime(2016, 8, 30, 0, 0, 0);
        DateTime bedTime = new DateTime(2016, 8, 29, 21, 0, 0);
        DateTime endTime = new DateTime(2016, 8, 30, 4, 0, 0);

        Babysitter babysitter = new Babysitter(startTime, endTime, bedTime);

        // Act
        int actualPay = babysitter.calculatePay();

        // Assert
        assertEquals(expectedPay, actualPay);
    }

    @Test
    public void whenBabysitterStartsAndEndsWorkWithFractionalHours() throws Exception {
        // Arrange
        int expectedPay = 136;

        DateTime startTime = new DateTime(2016, 8, 29, 17, 30, 0);
        DateTime bedTime = new DateTime(2016, 8, 29, 21, 0, 0);
        DateTime endTime = new DateTime(2016, 8, 30, 4, 30, 0);

        Babysitter babysitter = new Babysitter(startTime, endTime, bedTime);

        // Act
        int actualPay = babysitter.calculatePay();

        // Assert
        assertEquals(expectedPay, actualPay);
    }
}