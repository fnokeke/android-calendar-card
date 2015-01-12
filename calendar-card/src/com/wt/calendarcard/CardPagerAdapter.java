package com.wt.calendarcard;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import com.wt.calendarcard.model.Note;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CardPagerAdapter extends PagerAdapter {

    private Context mContext;
    private CalendarCard.OnDateSelectedListener onDateSelectedListener;
    private ViewGroup.LayoutParams lp;

    public static final int MAX_WEEKS = 1000;
    public CalendarCard[] cards = new CalendarCard[MAX_WEEKS];
    private Note[] notes;

    public CardPagerAdapter(Context context) {
        lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mContext = context;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, position - MAX_WEEKS / 2);
        CalendarCard card = new CalendarCard(mContext);
        card.setNotes(getNoteOfMonth(cal));
        card.hideTitle();
        card.setDateDisplay(cal);
        card.notifyChanges();
        if (card.getOnDateSelectedListener() == null)
            card.setOnDateSelectedListener(onDateSelectedListener);
        container.addView(card, 0, lp);
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

    public void setNotes(Note[] notes) {
        this.notes = notes;
    }

    private List<Note> getNoteOfMonth(Calendar calendar) {
        if (notes == null || notes.length == 0 || calendar == null) return null;
        List<Note> monthNotes = new ArrayList<Note>();
        for(Note note : notes) {
            if (calendar.get(Calendar.YEAR) == note.getDate().get(Calendar.YEAR) && calendar.get(Calendar.MONTH) == note.getDate().get(Calendar.MONTH))
                monthNotes.add(note);
        }
        return monthNotes;
    }
}
