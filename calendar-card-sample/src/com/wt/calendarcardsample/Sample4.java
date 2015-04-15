package com.wt.calendarcardsample;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class Sample4 extends Activity {
    
    private ListView listView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample4);
        setupCalendarListView();
    }

    private void setupCalendarListView() {
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new CalendarListAdapter(this));
    }


}
