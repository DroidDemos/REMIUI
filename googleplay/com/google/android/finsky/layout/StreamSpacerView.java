package com.google.android.finsky.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import com.google.android.finsky.layout.play.FinskyHeaderListLayout.StreamSpacer;

public class StreamSpacerView extends View implements StreamSpacer {
    public StreamSpacerView(Context context) {
        super(context);
    }

    public StreamSpacerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
}
