package com.wt.calendarcard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.wt.calendar_card.R;
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
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, null);
            holder.textView = (TextView) convertView.findViewById(R.id.textView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textView.setText(events[position].getContent());
        return convertView;
    }

    private class ViewHolder {
        TextView textView;
    }
}
