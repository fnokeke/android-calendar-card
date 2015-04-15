package com.wt.calendarcardsample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import com.wt.calendarcard.CalendarCard;
import com.wt.calendarcard.CalendarCardPager;
import com.wt.calendarcard.CardGridItem;

public class Sample2 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample2);
        CalendarCardPager pager = (CalendarCardPager) findViewById(R.id.calendarCard2);
        pager.setOnCellItemClick(new CalendarCard.OnDateSelectedListener() {
            @Override
            public void onDateSelected(View view, CardGridItem target) {
                // Do something to get the clicked cell with date
            }
        });
    }

}
