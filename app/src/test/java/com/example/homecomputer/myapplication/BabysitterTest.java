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
    public void calculatePay_BabysitterWorksUntilBedtime_Returns48Dollars() throws Exception {
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
    public void calculatePay_BabysitterWorksUntilMidnight_Returns72Dollars() throws Exception {
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
    public void calculatePay_BabysitterWorksAllPossibleHours_Returns136Dollars() throws Exception {
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
    public void calculatePay_BabysitterStartsWorkBefore5pmAndStopsWorkAfter4am_Returns136Dollars() throws Exception {
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
    public void calculatePay_BabysitterStartsWorkingAtMidnightAndStopsWorkingAt4am_Returns64Dollars() throws Exception {
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
    public void calculatePay_BabysitterStartsAndEndsWorkWithFractionalHours_Returns136Dollars() throws Exception {
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

    @Test
    public void calculatePay_BedtimePassedIsBefore5PmStartTime_ReturnsZeroDollars() throws Exception {
        // Arrange
        int expectedPay = 0;

        DateTime startTime = new DateTime(2016, 8, 29, 17, 0, 0);
        DateTime bedTime = new DateTime(2016, 8, 29, 15, 0, 0);
        DateTime endTime = new DateTime(2016, 8, 30, 4, 0, 0);

        Babysitter babysitter = new Babysitter(startTime, endTime, bedTime);

        // Act
        int actualPay = babysitter.calculatePay();

        // Assert
        assertEquals(expectedPay, actualPay);
    }

    @Test
    public void calculatePay_BedtimePassedIsAfter4AmEndTime_ReturnsZeroDollars() throws Exception {
        // Arrange
        int expectedPay = 0;

        DateTime startTime = new DateTime(2016, 8, 29, 17, 0, 0);
        DateTime bedTime = new DateTime(2016, 8, 30, 5, 0, 0);
        DateTime endTime = new DateTime(2016, 8, 30, 4, 0, 0);

        Babysitter babysitter = new Babysitter(startTime, endTime, bedTime);

        // Act
        int actualPay = babysitter.calculatePay();

        // Assert
        assertEquals(expectedPay, actualPay);
    }

    @Test
    public void calculatePay_StartTimeIsGreaterThanEndTime_ReturnsZeroDollars() throws Exception {
        // Arrange
        int expectedPay = 0;

        DateTime startTime = new DateTime(2016, 8, 29, 23, 0, 0);
        DateTime bedTime = new DateTime(2016, 8, 29, 21, 0, 0);
        DateTime endTime = new DateTime(2016, 8, 29, 19, 0, 0);

        Babysitter babysitter = new Babysitter(startTime, endTime, bedTime);

        // Act
        int actualPay = babysitter.calculatePay();

        // Assert
        assertEquals(expectedPay, actualPay);
    }

    @Test
    public void calculatePay_BabysitterStartsWorkingAtBedtimeAndStopsWorkingAt4am_Returns88Dollars() throws Exception {
        // Arrange
        int expectedPay = 88;

        DateTime startTime = new DateTime(2016, 8, 29, 21, 0, 0);
        DateTime bedTime = new DateTime(2016, 8, 29, 21, 0, 0);
        DateTime endTime = new DateTime(2016, 8, 30, 4, 0, 0);

        Babysitter babysitter = new Babysitter(startTime, endTime, bedTime);

        // Act
        int actualPay = babysitter.calculatePay();

        // Assert
        assertEquals(expectedPay, actualPay);
    }

    @Test
    public void calculatePay_BabysitterStartsWorkingAfterBedtimeAndStopsWorkingBeforeMidnight_Returns8Dollars() throws Exception {
        // Arrange
        int expectedPay = 8;

        DateTime startTime = new DateTime(2016, 8, 29, 22, 0, 0);
        DateTime bedTime = new DateTime(2016, 8, 29, 21, 0, 0);
        DateTime endTime = new DateTime(2016, 8, 29, 23, 0, 0);

        Babysitter babysitter = new Babysitter(startTime, endTime, bedTime);

        // Act
        int actualPay = babysitter.calculatePay();

        // Assert
        assertEquals(expectedPay, actualPay);
    }

    public void calculatePay_BabysitterStartsAndStopsWorkingBeforeBedtime_Returns24Dollars() throws Exception {
        // Arrange
        int expectedPay = 24;

        DateTime startTime = new DateTime(2016, 8, 29, 18, 0, 0);
        DateTime bedTime = new DateTime(2016, 8, 29, 21, 0, 0);
        DateTime endTime = new DateTime(2016, 8, 29, 20, 0, 0);

        Babysitter babysitter = new Babysitter(startTime, endTime, bedTime);

        // Act
        int actualPay = babysitter.calculatePay();

        // Assert
        assertEquals(expectedPay, actualPay);
    }



}