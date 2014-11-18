package com.wt.calendarcard;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

public class CalendarCardPager extends WrapViewPager {

    private CardPagerAdapter mCardPagerAdapter;
    private CalendarCard.OnDateSelectedListener onDateSelectedListener;

    private final int currentPosition = CardPagerAdapter.MAX_WEEKS / 2;

    public CalendarCardPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CalendarCardPager(Context context) {
        super(context);
        init();
    }

    private void init() {
        mCardPagerAdapter = new CardPagerAdapter(getContext());
        setAdapter(mCardPagerAdapter);
        setCurrentItem(currentPosition);
        setOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {

            }

            @Override
            public void onPageSelected(int position) {
                mCardPagerAdapter.cards[position].notifyChanges();
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    public void setOnCellItemClick(CalendarCard.OnDateSelectedListener dateSelectedListener) {
        this.onDateSelectedListener = dateSelectedListener;
        mCardPagerAdapter.setOnDateSelectedListener(this.onDateSelectedListener);
        for (int i = 0; i < getChildCount(); i++) {
            View v = getChildAt(i);
            if (v instanceof CalendarCard) {
                ((CalendarCard) v).setOnDateSelectedListener(this.onDateSelectedListener);
            }
        }
    }

}
