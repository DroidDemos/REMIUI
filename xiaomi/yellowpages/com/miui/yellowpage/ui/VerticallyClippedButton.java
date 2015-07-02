package com.miui.yellowpage.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.util.AttributeSet;
import android.widget.Button;

public class VerticallyClippedButton extends Button {
    private float zH;
    private Path zI;

    public VerticallyClippedButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.zI = new Path();
    }

    public void d(float f) {
        this.zH = f;
    }

    protected void onDraw(Canvas canvas) {
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        this.zI.addRect(0.0f, ((float) measuredHeight) - (((float) measuredHeight) * this.zH), (float) measuredWidth, (float) measuredHeight, Direction.CW);
        canvas.clipPath(this.zI);
        this.zI.reset();
        super.onDraw(canvas);
    }
}
