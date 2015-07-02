package com.google.android.finsky.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import com.android.vending.R;
import com.google.android.finsky.layout.DetailsSectionStack.NoBottomSeparator;
import com.google.android.finsky.layout.DetailsSectionStack.NoTopSeparator;
import java.util.List;

public class DetailsTitleCombined extends LinearLayout implements DetailsPartialFadeSection, NoBottomSeparator, NoTopSeparator {
    private View mSecondaryStack;

    public DetailsTitleCombined(Context context) {
        super(context);
    }

    public DetailsTitleCombined(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mSecondaryStack = findViewById(R.id.secondary_stack);
    }

    public void addParticipatingChildViews(List<View> participatingChildViewList) {
        participatingChildViewList.add(this.mSecondaryStack);
    }

    public void addParticipatingChildViewIds(List<Integer> participatingChildViewIdList) {
        participatingChildViewIdList.add(Integer.valueOf(R.id.secondary_stack));
    }
}
