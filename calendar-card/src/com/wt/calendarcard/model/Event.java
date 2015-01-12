package com.wt.calendarcard.model;

import java.io.Serializable;
import java.util.Calendar;

public class Event implements Serializable {
    private String time;
    private String content;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
