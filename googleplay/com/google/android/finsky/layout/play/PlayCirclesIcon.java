package com.google.android.finsky.layout.play;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.android.vending.R;
import com.google.android.play.image.AvatarCropTransformation;

public class PlayCirclesIcon extends ImageView {
    public PlayCirclesIcon(Context context) {
        this(context, null);
    }

    public PlayCirclesIcon(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
    }

    public void configure(String name, boolean isInCircles) {
        if (isInCircles) {
            setContentDescription(getResources().getString(R.string.content_description_following_on_gplus, new Object[]{name}));
            setImageResource(R.drawable.btn_added_friend);
            return;
        }
        setContentDescription(getResources().getString(R.string.content_description_add_on_gplus, new Object[]{name}));
        setImageResource(R.drawable.btn_add_friend);
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        invalidate();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        if (isPressed() && isClickable()) {
            AvatarCropTransformation.getFullAvatarCrop(getResources()).drawPressedOverlay(canvas, width, height);
        } else if (isFocused()) {
            AvatarCropTransformation.getFullAvatarCrop(getResources()).drawFocusedOverlay(canvas, width, height);
        }
    }
}
