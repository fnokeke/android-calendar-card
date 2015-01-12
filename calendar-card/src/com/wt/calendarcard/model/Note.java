package com.wt.calendarcard.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Note implements Serializable{
    private String date;
    private Event[] events;

    public Calendar getDate() {
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
        Date tmpDate = null;
        try {
            tmpDate = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(tmpDate);
        return calendar;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Event[] getEvents() {
        return events;
    }

    public void setEvents(Event[] events) {
        this.events = events;
    }
}
