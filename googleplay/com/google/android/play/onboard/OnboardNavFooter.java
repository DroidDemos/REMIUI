package com.google.android.play.onboard;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.google.android.play.R;
import com.google.android.play.widget.PageIndicator;

public class OnboardNavFooter extends FrameLayout {
    protected final TextView mEndButton;
    protected final PageIndicator mPageIndicator;
    protected final TextView mStartButton;

    public OnboardNavFooter(Context context) {
        this(context, null);
    }

    public OnboardNavFooter(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public OnboardNavFooter(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public OnboardNavFooter(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.play_onboard_nav_footer, this);
        setBackgroundColor(context.getResources().getColor(R.color.play_onboard_app_color_dark));
        this.mStartButton = (TextView) findViewById(R.id.start_button);
        this.mEndButton = (TextView) findViewById(R.id.end_button);
        this.mPageIndicator = (PageIndicator) findViewById(R.id.page_indicator);
    }
}
