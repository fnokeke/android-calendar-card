package com.wt.calendarcardsample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.wt.calendarcard.CalendarCard;
import com.wt.calendarcard.CardGridItem;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class Sample1 extends Activity {

    private CalendarCard mCalendarCard;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample1);
        mTextView = (TextView) findViewById(R.id.textView1);
        mCalendarCard = (CalendarCard) findViewById(R.id.calendarCard1);
        mCalendarCard.setOnDateSelectedListener(new CalendarCard.OnDateSelectedListener() {
            @Override
            public void onDateSelected(View view, CardGridItem item) {
                mTextView.setText(getResources().getString(R.string.sel_date, new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(item.getDate().getTime())));
            }
        });

    }

}
