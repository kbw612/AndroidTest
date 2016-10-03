package com.example.homecomputer.myapplication;

import org.joda.time.DateTime;
import org.joda.time.Period;

class Babysitter {
    private DateTime startTime;
    private DateTime endTime;
    private DateTime bedTime;
    private DateTime midnight;
    private DateTime minimumStartTime;

    private final int START_TIME_TO_BEDTIME_HOURLY_RATE = 12;
    private final int BEDTIME_TO_MIDNIGHT_HOURLY_RATE = 8;
    private final int MIDNIGHT_TO_END_TIME_HOURLY_RATE = 16;

    Babysitter(DateTime startTime, DateTime endTime, DateTime bedTime) {
        this.startTime = removeMinutesAndSeconds(startTime);
        this.endTime = removeMinutesAndSeconds(endTime);
        this.bedTime = removeMinutesAndSeconds(bedTime);
        minimumStartTime = new DateTime(this.startTime);
     }

    int calculatePay() {
        initializeTimeProperties();

        if (areTimePropertiesValid()) {
            int payStartTimeToBedtime = calcuatePayBeforeBedtime();
            int payBedtimeToMidnight = calcuatePayFromBedtimeToMidnight();
            int payMidnightToEndTime = calcuatePayFromMidnightToEndTime();

            return payStartTimeToBedtime + payBedtimeToMidnight + payMidnightToEndTime;
        }
        else {
            return 0;
        }
    }

    private int calcuatePayBeforeBedtime()
    {
        int startTimeToBedtimeDurationInHours = 0;

        if (this.endTime.isBefore(this.bedTime)) {
            startTimeToBedtimeDurationInHours = calculateDurationInHours(this.startTime, this.endTime);
        }
        else if (this.startTime.isBefore(bedTime)) {
            startTimeToBedtimeDurationInHours = calculateDurationInHours(this.startTime, this.bedTime);
        }

        return startTimeToBedtimeDurationInHours * START_TIME_TO_BEDTIME_HOURLY_RATE;
    }

    private int calcuatePayFromBedtimeToMidnight()
    {
        int bedTimeToMidnightDurationInHours = 0;

        if (this.startTime.isBefore(this.midnight) && this.endTime.isAfter(this.bedTime)) {
            bedTimeToMidnightDurationInHours = calculateDurationInHours(this.bedTime, this.midnight);
        }

        return bedTimeToMidnightDurationInHours * BEDTIME_TO_MIDNIGHT_HOURLY_RATE;
    }

    private int calcuatePayFromMidnightToEndTime()
    {
        int midnightToEndTimeDurationInHours = 0;

        if ((startTime.isAfter(midnight) || startTime.equals(midnight)) && (endTime.isAfter(midnight)) || endTime.equals(midnight)) {
            midnightToEndTimeDurationInHours = calculateDurationInHours(this.midnight, this.endTime);
        }
        else {
            if (this.endTime.isAfter(this.bedTime)) {
                midnightToEndTimeDurationInHours = calculateDurationInHours(this.midnight, this.endTime);
            }
        }

        return midnightToEndTimeDurationInHours * MIDNIGHT_TO_END_TIME_HOURLY_RATE;
    }

    private boolean areTimePropertiesValid() {
        boolean isValid = true;

        if (this.bedTime.isBefore(this.minimumStartTime) && this.minimumStartTime.isBefore(this.midnight)) {
            isValid = false;
        }

        return isValid;
    }

    private int calculateDurationInHours(DateTime startTime, DateTime endTime) {
        Period duration = new Period(startTime, endTime);

        return duration.getHours();
    }

    private void initializeTimeProperties() {
        this.midnight = new DateTime(this.minimumStartTime.getYear(), this.minimumStartTime.getMonthOfYear(), this.minimumStartTime.getDayOfMonth(), 0, 0, 0);

        // if start time after 4 am then set midnight to next day midnight
        if (this.minimumStartTime.getHourOfDay() > 4) {
            this.midnight = this.midnight.plusDays(1);
            this.minimumStartTime = new DateTime(this.minimumStartTime.getYear(), this.minimumStartTime.getMonthOfYear(), this.minimumStartTime.getDayOfMonth(), 17, 0, 0);
        }

        DateTime maxEndTime = midnight.plusHours(4);

        // make sure end time is 4 am or earlier
        if (this.endTime.isAfter(maxEndTime)) {
            this.endTime = maxEndTime;
        }

        // make sure start time is 5 pm or later
        if (this.startTime.isBefore(this.minimumStartTime)) {
            this.startTime = this.minimumStartTime;
        }
    }

    private DateTime removeMinutesAndSeconds(DateTime dateTime) {
        return new DateTime(dateTime.getYear(), dateTime.getMonthOfYear(), dateTime.getDayOfMonth(), dateTime.getHourOfDay(), 0, 0);
    }
}
