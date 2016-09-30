package com.example.homecomputer.myapplication;

import java.util.Date;

class Babysitter {
    private Date startTime;
    private Date endTime;

    Babysitter(Date startTime, Date endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    int calculatePay() {
        return 0;
    }
}
