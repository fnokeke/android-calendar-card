package com.wt.calendarcard;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.wt.calendarcard.model.Event;

public class EventsAdapter extends BaseAdapter {


    private final Context context;
    private Event[] events;


    public EventsAdapter(Context context) {
        this.context = context;
    }

    public void setData(Event[] events) {
        this.events = events;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return events == null ? 0 : events.length;
    }

    @Override
    public Object getItem(int position) {
        return events == null ? null : events[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = new TextView(context);
        textView.setText(events[position].getContent());
        return textView;
    }
}
