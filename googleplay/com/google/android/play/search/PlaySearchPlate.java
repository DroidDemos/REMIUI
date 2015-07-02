package com.google.android.play.search;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.google.android.play.R;

public class PlaySearchPlate extends FrameLayout {
    public PlaySearchPlate(Context context) {
        this(context, null);
    }

    public PlaySearchPlate(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PlaySearchPlate(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setPlaySearchController(PlaySearchController playSearchController) {
        PlaySearchPlateTextContainer searchPlateTextContainer = (PlaySearchPlateTextContainer) findViewById(R.id.text_container);
        PlaySearchActionButton actionButton = (PlaySearchActionButton) findViewById(R.id.action_button);
        ((PlaySearchNavigationButton) findViewById(R.id.navigation_button)).setPlaySearchController(playSearchController);
        searchPlateTextContainer.setPlaySearchController(playSearchController);
        actionButton.setPlaySearchController(playSearchController);
    }
}
