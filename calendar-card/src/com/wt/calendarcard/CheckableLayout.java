package com.wt.calendarcard;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.*;
import com.wt.calendar_card.R;

public class CheckableLayout extends RelativeLayout{

    public CheckableLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public CheckableLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CheckableLayout(Context context) {
        super(context);
        init();
    }

    private TextView textView;
    private ImageView imageView;

    private void init() {
        inflate(getContext(), R.layout.card_cell, this);
        textView = (TextView)findViewById(R.id.textView);
        imageView = (ImageView) findViewById(R.id.imageView);
        setClicked(false);
    }

    public void setClicked(boolean checked) {
        imageView.setEnabled(checked);
    }

    @Override
    public void setActivated(boolean activated) {
        super.setActivated(activated);
        imageView.setActivated(activated);
        textView.setActivated(activated);
    }

    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        imageView.setSelected(selected);
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        textView.setEnabled(enabled);
    }

}
