package com.wt.calendarcard;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.wt.calendar_card.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class CalendarCard extends LinearLayout {

    private TextView cardTitle;
    private OnItemRender mOnItemRender;
    private OnItemRender mOnItemRenderDefault;
    private OnDateSelectedListener onDateSelectedListener;
    private Calendar dateDisplay;
    private ArrayList<CheckableLayout> cells = new ArrayList<CheckableLayout>();
    private LinearLayout cardGrid;

    public CalendarCard(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, R.style.CalendarCard_Root);
        init();
    }

    public CalendarCard(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CalendarCard(Context context) {
        super(context);
        init();
    }

    public void setOnDateSelectedListener(OnDateSelectedListener onDateSelectedListener) {
        this.onDateSelectedListener = onDateSelectedListener;
    }

    public OnDateSelectedListener getOnDateSelectedListener() {
        return onDateSelectedListener;
    }

    private void init() {
        setOrientation(VERTICAL);
        inflate(getContext(), R.layout.card_view, this);
        cardTitle = (TextView) findViewById(R.id.cardTitle);
        cardGrid = (LinearLayout) findViewById(R.id.cardGrid);
        if (dateDisplay == null) {
            dateDisplay = Calendar.getInstance();
        }
        cardTitle.setText(new SimpleDateFormat("MMM yyyy", Locale.getDefault()).format(dateDisplay.getTime()));
        initWeekTitle();
        initCellDay();

        mOnItemRenderDefault = new OnItemRender() {
            @Override
            public void onRender(CheckableLayout v, CardGridItem item) {
                ((TextView) v.findViewById(R.id.textView)).setText(item.getDayOfMonth().toString());
            }
        };

        updateCells();
    }

    private void initCellDay() {
        for (int y = 0; y < cardGrid.getChildCount(); y++) {
            LinearLayout rowWeek = (LinearLayout) cardGrid.getChildAt(y);
            for (int x = 0; x < rowWeek.getChildCount(); x++) {
                CheckableLayout cellDay = (CheckableLayout) rowWeek.getChildAt(x);
                cellDay.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        for (CheckableLayout cell : cells) {
                            cell.setClicked(cell == view);
                        }
                        if (onDateSelectedListener != null) {
                            onDateSelectedListener.onDateSelected(view, (CardGridItem) view.getTag());
                        }
                    }
                });
                cells.add(cellDay);
            }
        }
    }

    private void initWeekTitle() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        LinearLayout cardDays = (LinearLayout)findViewById(R.id.cardDays);
        for (int index = 0; index < cardDays.getChildCount(); index++) {
            TextView child = (TextView)cardDays.getChildAt(index);
            child.setText(calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.getDefault()));
            calendar.add(Calendar.DAY_OF_WEEK, 1);
        }
    }

    private int getDaySpacing(int dayOfWeek) {
        return Calendar.SUNDAY == dayOfWeek ? 6 : dayOfWeek - 2;
    }

    private int getDaySpacingEnd(int dayOfWeek) {
        return 8 - dayOfWeek;
    }

    private void updateCells() {
        Integer counter = 0;
        Calendar calendar = (Calendar) dateDisplay.clone();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        int daySpacing = getDaySpacing(calendar.get(Calendar.DAY_OF_WEEK));
        if (daySpacing > 0) {
            Calendar prevMonth = (Calendar) calendar.clone();
            prevMonth.add(Calendar.MONTH, -1);
            prevMonth.set(Calendar.DAY_OF_MONTH, prevMonth.getActualMaximum(Calendar.DAY_OF_MONTH) - daySpacing + 1);
            for (int i = 0; i < daySpacing; i++) {
                CheckableLayout cell = cells.get(counter);
                cell.setTag(new CardGridItem(Integer.valueOf(prevMonth.get(Calendar.DAY_OF_MONTH))).setEnabled(false));
                cell.setEnabled(false);
                (mOnItemRender == null ? mOnItemRenderDefault : mOnItemRender).onRender(cell, (CardGridItem) cell.getTag());
                counter++;
                prevMonth.add(Calendar.DAY_OF_MONTH, 1);
            }
        }

        int firstDay = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        int lastDay = calendar.get(Calendar.DAY_OF_MONTH) + 1;
        if (!isThisMonth(calendar)) cells.get(counter).setClicked(true);
        for (int i = firstDay; i < lastDay; i++) {
            calendar.set(Calendar.DAY_OF_MONTH, i - 1);
            Calendar date = (Calendar) calendar.clone();
            date.add(Calendar.DAY_OF_MONTH, 1);
            CheckableLayout cell = cells.get(counter);
            cell.setTag(new CardGridItem(i).setEnabled(true).setDate(date));
            cell.setEnabled(true);
            cell.setVisibility(View.VISIBLE);
            cell.setActivated(isToday(date));
            (mOnItemRender == null ? mOnItemRenderDefault : mOnItemRender).onRender(cell, (CardGridItem) cell.getTag());
            counter++;
        }


        calendar = (Calendar) dateDisplay.clone();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        daySpacing = getDaySpacingEnd(calendar.get(Calendar.DAY_OF_WEEK));
        if ( daySpacing < 7) {
            for (int i = 0; i < daySpacing; i++) {
                CheckableLayout cell = cells.get(counter);
                cell.setTag(new CardGridItem(i + 1).setEnabled(false));
                cell.setEnabled(false);
                cell.setVisibility(View.VISIBLE);
                (mOnItemRender == null ? mOnItemRenderDefault : mOnItemRender).onRender(cell, (CardGridItem) cell.getTag());
                counter++;
            }
        } else {
            cardGrid.getChildAt(cardGrid.getChildCount() - 1).setVisibility(View.GONE);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed && cells.size() > 0) {
            int size = (r - l) / 7;
            for (CheckableLayout cell : cells) {
                cell.getLayoutParams().height = size;
            }
        }
    }

    public OnItemRender getOnItemRender() {
        return mOnItemRender;
    }

    public void setOnItemRender(OnItemRender mOnItemRender) {
        this.mOnItemRender = mOnItemRender;
    }

    public void setDateDisplay(Calendar dateDisplay) {
        this.dateDisplay = dateDisplay;
        cardTitle.setText(new SimpleDateFormat("MMM yyyy", Locale.getDefault()).format(dateDisplay.getTime()));
    }

    public void notifyChanges() {
        updateCells();
    }

    private boolean isToday(Calendar calendar) {
        if (calendar == null) return false;
        Calendar today = Calendar.getInstance(Locale.getDefault());
        return isThisMonth(calendar) &&  today.get(Calendar.DATE) == calendar.get(Calendar.DATE);
    }

    private boolean isThisMonth(Calendar calendar) {
        if (calendar == null) return false;
        Calendar today = Calendar.getInstance(Locale.getDefault());
        return today.get(Calendar.YEAR) == calendar.get(Calendar.YEAR) &&
                today.get(Calendar.MONTH) == calendar.get(Calendar.MONTH);
    }

    public interface OnDateSelectedListener {
        void onDateSelected(View view, CardGridItem target);
    }

}
