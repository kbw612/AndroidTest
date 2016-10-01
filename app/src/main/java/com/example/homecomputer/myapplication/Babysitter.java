package com.example.homecomputer.myapplication;

import java.util.Calendar;
import java.util.Date;

import javax.xml.datatype.Duration;

class Babysitter {
    private Date startTime;
    private Date endTime;
    private Date bedTime;
    private Date midnight;

    Babysitter(Date startTime, Date endTime, Date bedTime) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.bedTime = bedTime;
     }

    int calculatePay() {
        int startTimeToBedtimeDurationInHours = 0, bedTimeToMidnightDurationInHours = 0, midnightToEndTimeDurationInHours = 0;

        setTimes();

        if (this.endTime.before(this.bedTime)) {
            startTimeToBedtimeDurationInHours = calculateDurationInHours(this.startTime, this.endTime);
        }
        else if (this.startTime.before(bedTime)) {
            startTimeToBedtimeDurationInHours = calculateDurationInHours(this.startTime, this.bedTime);
        }

        if (this.endTime.after(this.bedTime)) {
            if (!this.startTime.equals(this.midnight) && this.startTime.before(this.midnight)) {
                bedTimeToMidnightDurationInHours = calculateDurationInHours(this.bedTime, this.midnight);
            }
            midnightToEndTimeDurationInHours = calculateDurationInHours(this.midnight, this.endTime);
        }

        if ((startTime.after(midnight) || startTime.equals(midnight)) && (endTime.after(midnight)) || endTime.equals(midnight)) {
            midnightToEndTimeDurationInHours = calculateDurationInHours(this.midnight, this.endTime);
        }
        else {
            if (this.endTime.after(this.bedTime)) {
                bedTimeToMidnightDurationInHours = calculateDurationInHours(this.bedTime, this.midnight);
                midnightToEndTimeDurationInHours = calculateDurationInHours(this.midnight, this.endTime);
            }
        }

        int payStartTimeToBedtime = startTimeToBedtimeDurationInHours * 12;
        int payBedtimeToMidnight = bedTimeToMidnightDurationInHours * 8;
        int payMidnightToEndTime = midnightToEndTimeDurationInHours * 16;

        return payStartTimeToBedtime + payBedtimeToMidnight + payMidnightToEndTime;
    }

    private int calculateDurationInHours(Date startTime, Date endTime) {
        long difference = endTime.getTime() - startTime.getTime();
        return (int) (difference / (60 * 60 * 1000) % 24);
    }

    private void setTimes() {
        Calendar startTimeCalendar = Calendar.getInstance();
        Calendar minStartTimeCalendar = Calendar.getInstance();
        Calendar midnightCalendar = Calendar.getInstance();

        minStartTimeCalendar.setTime(this.startTime);

        startTimeCalendar.setTime(this.startTime);
        midnightCalendar.set(startTimeCalendar.get(Calendar.YEAR), startTimeCalendar.get(Calendar.MONTH), startTimeCalendar.get(Calendar.DATE), 0 , 0, 0);

        // if start time after 4 am then set midnight to next day midnight
        if (startTimeCalendar.get(Calendar.HOUR_OF_DAY) > 4) {
            midnightCalendar.add(Calendar.DATE, 1);
            minStartTimeCalendar.set(minStartTimeCalendar.get(Calendar.YEAR), minStartTimeCalendar.get(Calendar.MONTH), minStartTimeCalendar.get(Calendar.DATE), 17 , 0, 0);
        }

        Date minStartTime = minStartTimeCalendar.getTime();
        this.midnight = midnightCalendar.getTime();

        midnightCalendar.add(Calendar.HOUR, 4);
        Date maxEndTime = midnightCalendar.getTime();

        if (this.endTime.after(maxEndTime))
        {
            this.endTime = maxEndTime;
        }

        if (this.startTime.before(minStartTime))
        {
            this.startTime = minStartTime;
        }
    }
}
