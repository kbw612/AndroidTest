package com.example.homecomputer.myapplication;

import java.util.Calendar;
import java.util.Date;

import javax.xml.datatype.Duration;

class Babysitter {
    private Date startTime;
    private Date endTime;
    private Date bedTime;

    Babysitter(Date startTime, Date endTime, Date bedTime) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.bedTime = bedTime;
    }

    int calculatePay() {
        Calendar startTimeCalendar = Calendar.getInstance();
        Calendar midnightCalendar = Calendar.getInstance();
        int startTimeToBedtimeDurationInHours, bedTimeToMidnightDurationInHours = 0, midnightToEndTimeDurationInHours = 0;

        startTimeCalendar.setTime(this.startTime);
        midnightCalendar.set(startTimeCalendar.get(Calendar.YEAR), startTimeCalendar.get(Calendar.MONTH), startTimeCalendar.get(Calendar.DATE), 0 , 0, 0);

        // if start time after 4 am then set midnight to next day midnight
        if (startTimeCalendar.get(Calendar.HOUR) > 4) {
            midnightCalendar.add(Calendar.DATE, 1);
        }

        if (this.endTime.before(this.bedTime)) {
            startTimeToBedtimeDurationInHours = calculateDurationInHours(this.startTime, this.endTime);
        }
        else {
            startTimeToBedtimeDurationInHours = calculateDurationInHours(this.startTime, this.bedTime);
        }

        if (endTime.after(this.bedTime)) {
            bedTimeToMidnightDurationInHours = calculateDurationInHours(this.bedTime, midnightCalendar.getTime());
            midnightToEndTimeDurationInHours = calculateDurationInHours(midnightCalendar.getTime(), this.endTime);
        }

        int payStartTimeToBedtime = startTimeToBedtimeDurationInHours * 12;
        int payBedtimeToMidnight = bedTimeToMidnightDurationInHours * 8;
        int payMidnightToMaxEndTime = midnightToEndTimeDurationInHours * 16;

        return payStartTimeToBedtime + payBedtimeToMidnight + payMidnightToMaxEndTime;








    }

    private int calculateDurationInHours(Date startTime, Date endTime) {
        long seconds = (endTime.getTime() - startTime.getTime()) / 1000;

        return (int) (seconds / 3600);  // calculate hours
    }
}
