package com.google.android.play.cardview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

public interface CardViewGroupDelegate {
    void initialize(View view, Context context, AttributeSet attributeSet, int i);

    void setBackgroundColor(View view, int i);

    void setBackgroundResource(View view, int i);

    void setCardElevation(View view, float f);
}
