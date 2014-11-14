package com.wt.calendarcard;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.CheckedTextView;
import android.widget.RelativeLayout;
import com.wt.calendar_card.R;

public class CheckableLayout extends RelativeLayout implements Checkable {

    private static final int[] CHECKED_STATE_SET = {
        android.R.attr.state_checked
    };
    
    private boolean checked = false;

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

    private CheckedTextView checkedTextView;
    private void init() {
        inflate(getContext(), R.layout.card_cell, this);
        checkedTextView = (CheckedTextView)findViewById(R.id.checkedTextView);
    }

    @Override
    public boolean isChecked() {
        return checked;
    }
    
    @Override
    public void setChecked(boolean checked) {
        this.checked = checked;
        refreshDrawableState();
        checkedTextView.setChecked(checked);
    }

    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        checkedTextView.setSelected(selected);
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        checkedTextView.setEnabled(enabled);
    }

    @Override
    protected int[] onCreateDrawableState(int extraSpace) {
        final int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
        if (isChecked()) {
            mergeDrawableStates(drawableState, CHECKED_STATE_SET);
        }
        return drawableState;
    }

    @Override
    public void toggle() {
        this.checked = !this.checked;
    }

}
