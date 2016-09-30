package com.example.homecomputer.myapplication;

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
    public void caculatePay() throws Exception {
        // Arrange
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2016);
        calendar.set(Calendar.MONTH, Calendar.SEPTEMBER);
        calendar.set(Calendar.DAY_OF_MONTH, 30);
        calendar.set(Calendar.HOUR_OF_DAY, 17);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Date startTime = calendar.getTime();

        calendar.set(Calendar.HOUR_OF_DAY, 19);
        Date endTime = calendar.getTime();

        Babysitter babysitter = new Babysitter(startTime, endTime);

        // Act
        int actualPay = babysitter.calculatePay();

        // Assert
        assertEquals(24, actualPay);
    }
}