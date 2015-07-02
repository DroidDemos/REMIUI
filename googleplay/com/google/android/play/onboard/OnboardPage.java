package com.google.android.play.onboard;

import com.google.android.play.R;

public interface OnboardPage {
    public static final int DK_PAGE_ID;
    public static final int DK_PAGE_INFO;

    static {
        DK_PAGE_ID = R.id.play_onboard__OnboardPage_pageId;
        DK_PAGE_INFO = R.id.play_onboard__OnboardPage_pageInfo;
    }
}
