package com.wt.calendarcard;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.Calendar;

public class CardPagerAdapter extends PagerAdapter {

    private Context mContext;
    private CalendarCard.OnDateSelectedListener onDateSelectedListener;

    public static final int MAX_WEEKS = 1000;
    public CalendarCard[] cards = new CalendarCard[MAX_WEEKS];
    public CardPagerAdapter(Context context) {
        mContext = context;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, position - MAX_WEEKS / 2);
        CalendarCard card = new CalendarCard(mContext);
        card.setDateDisplay(cal);
        card.notifyChanges();
        if (card.getOnDateSelectedListener() == null)
            card.setOnDateSelectedListener(onDateSelectedListener);
        container.addView(card, 0);
        cards[position] = card;
        return card;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object view) {
        container.removeView((View) view);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public int getCount() {
        return MAX_WEEKS;
    }

    public void setOnDateSelectedListener(CalendarCard.OnDateSelectedListener onDateSelectedListener) {
        this.onDateSelectedListener = onDateSelectedListener;
    }



}
