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
        return calculateDurationInHours() * 12;
    }

    private int calculateDurationInHours() {
        long seconds = (this.endTime.getTime() - this.startTime.getTime()) / 1000;

        return (int) (seconds / 3600);  // calculate hours
    }
}
