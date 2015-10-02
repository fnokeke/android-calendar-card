package com.wt.calendarcard;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.wt.calendar_card.R;
import com.wt.calendarcard.model.Event;
import com.wt.calendarcard.model.Note;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class CalendarCardList extends LinearLayout {
    private WrapViewPager calendarPager;
    private ImageView todayBtn;
    private TextView monthToggle;
    private ListView listView;
    private CardPagerAdapter calendarAdapter;
    private EventsAdapter eventsAdapter;
    private ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i2) {

        }

        @Override
        public void onPageSelected(int position) {
            CalendarCard calendarCard = calendarAdapter.cards[position];
            if (calendarCard == null) return;
            calendarCard.notifyChanges();
            monthToggle.setText(
                    new SimpleDateFormat(
                            "MMM yyyy",
                            Locale.getDefault()).format(calendarCard.getDateDisplay().getTime()
                    )
            );
            eventsAdapter.notifyDataSetChanged();

        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };

    public CalendarCardList(Context context) {
        super(context);
        init();
    }

    public CalendarCardList(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CalendarCardList(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, R.style.CalendarCard_CalendarCardList);
        init();
    }

    private void init() {
        setOrientation(VERTICAL);

        inflate(getContext(), R.layout.card_list, this);
        calendarPager = (WrapViewPager) findViewById(R.id.viewPager);
        todayBtn = (ImageView) findViewById(R.id.todayBtn);
        monthToggle = (TextView) findViewById(R.id.monthToggle);

        listView = (ListView) findViewById(R.id.listView);
        eventsAdapter = new EventsAdapter(getContext());
        listView.setAdapter(eventsAdapter);

        calendarAdapter = new CardPagerAdapter(getContext());
        calendarPager.setAdapter(calendarAdapter);

        bindListener();
    }

    private void bindListener() {
        calendarPager.setOnPageChangeListener(pageChangeListener);

        calendarAdapter.setOnDateSelectedListener(new CalendarCard.OnDateSelectedListener() {
            @Override
            public void onDateSelected(View view, CardGridItem target) {
                if (target == null) return;
                Event[] events = (Event[]) target.getData();
                eventsAdapter.setData(events);

                String totalStr = "";
                if (events == null)
                    totalStr = "Nada here";
                else
                    for (Event e : events)
                      totalStr += e.getContent() + " ";
                //@TODO: get rid of this logger
                Log.i("===date selected==", totalStr);
            }
        });

        todayBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarPager.setCurrentItem(CardPagerAdapter.MAX_WEEKS / 2);
            }
        });
        monthToggle.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (calendarPager.getVisibility() == View.GONE) {
                    calendarPager.setVisibility(View.VISIBLE);
                } else {
                    calendarPager.setVisibility(View.GONE);
                }
            }
        });
    }

    public void setDataSource(Note[] data) {
        calendarAdapter.setNotes(data);
        calendarPager.setCurrentItem(CardPagerAdapter.MAX_WEEKS / 2);
    }


}
