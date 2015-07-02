package com.google.android.play.onboard;

import android.content.Context;
import android.util.AttributeSet;
import com.google.android.libraries.bind.data.Data;
import com.google.android.libraries.bind.widget.BindingFrameLayout;
import com.google.android.play.R;

public class OnboardTutorialPage extends BindingFrameLayout implements OnboardPage {
    public static final int DK_BACKGROUND_COLOR;
    public static final int DK_BODY_TEXT;
    public static final int DK_ICON_DRAWABLE_ID;
    public static final int DK_TITLE_TEXT;
    protected OnboardHostControl mHostControl;

    static {
        DK_BACKGROUND_COLOR = R.id.play_onboard__OnboardTutorialPage_backgroundColor;
        DK_TITLE_TEXT = R.id.play_onboard__OnboardTutorialPage_titleText;
        DK_BODY_TEXT = R.id.play_onboard__OnboardTutorialPage_bodyText;
        DK_ICON_DRAWABLE_ID = R.id.play_onboard__OnboardTutorialPage_iconDrawableId;
    }

    public OnboardTutorialPage(Context context) {
        this(context, null);
    }

    public OnboardTutorialPage(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public OnboardTutorialPage(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setOnboardHostControl(OnboardHostControl control) {
        this.mHostControl = control;
    }

    public OnboardPageInfo getPageInfo() {
        Data data = getData();
        return (OnboardPageInfo) (data == null ? null : data.get(OnboardPage.DK_PAGE_INFO));
    }
}
