package com.example.homecomputer.myapplication;

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
        int payStartTimeToBedtime = calculateDurationInHours(startTime, bedTime) * 12;
        int payBedtimeToMidnight = calculateDurationInHours(bedTime, endTime) * 8;

        return payStartTimeToBedtime + payBedtimeToMidnight;
    }

    private int calculateDurationInHours(Date startTime, Date endTime) {
        long seconds = (endTime.getTime() - startTime.getTime()) / 1000;

        return (int) (seconds / 3600);  // calculate hours
    }
}
