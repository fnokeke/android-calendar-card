package com.wt.calendarcardsample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CalendarView;
import com.wt.calendarcard.CalendarCard;
import com.wt.calendarcard.CardGridItem;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CalendarListAdapter extends BaseAdapter {
    
    private Context context;

    public CalendarListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 12;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CalendarCard calendarView;
        if (convertView == null) {
            calendarView = (CalendarCard) LayoutInflater.from(context).inflate(R.layout.calendar_list_item, parent, false);
        } else {
            calendarView = (CalendarCard) convertView;
        }
        
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, position); 
        calendarView.setDateDisplay(cal);
        calendarView.notifyChanges();
        
        return calendarView;
    }
}
