package com.wt.calendarcard;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.*;
import com.wt.calendar_card.R;

import java.text.SimpleDateFormat;
import java.util.*;

public class CalendarCardList extends LinearLayout{
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


    private ViewPager calendarPager;
    private ImageView todayBtn;
    private TextView monthToggle;
    private ListView listView;

    private CardPagerAdapter calendarAdapter;
    private BaseAdapter arrayAdapter;

    private Map<Date, String[]> data;

    private void init() {
        setOrientation(VERTICAL);

        data = new HashMap<Date, String[]>();
        arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, contents);

        setDefaultData();

        inflate(getContext(), R.layout.card_list, this);
        calendarPager = (ViewPager) findViewById(R.id.viewPager);
        todayBtn = (ImageView) findViewById(R.id.todayBtn);
        monthToggle = (TextView) findViewById(R.id.monthToggle);
        listView = (ListView) findViewById(R.id.listView);

        listView.setAdapter(arrayAdapter);

        calendarAdapter = new CardPagerAdapter(getContext());
        calendarPager.setAdapter(calendarAdapter);
        calendarPager.setCurrentItem(CardPagerAdapter.MAX_WEEKS / 2);
        calendarPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {

            }

            @Override
            public void onPageSelected(int position) {
                CalendarCard calendarCard = calendarAdapter.cards[position];
                calendarCard.notifyChanges();
                monthToggle.setText(
                        new SimpleDateFormat(
                                "MMM yyyy",
                                Locale.getDefault()).format(calendarCard.getDateDisplay().getTime()
                        )
                );
                setDefaultData();
                arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        todayBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarPager.setCurrentItem(CardPagerAdapter.MAX_WEEKS / 2);
            }
        });

    }

    public void setDataSource(Map<Date, String[]> data) {
        this.data = data;
    }

    List<String> contents = new ArrayList<String>();

    private void setDefaultData() {
        contents.add("This is the event");
        contents.add("I have a new event");
        contents.add("Create a gift card");
    }


}
