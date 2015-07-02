package com.google.android.finsky.layout.play;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import com.android.vending.R;
import com.google.android.play.utils.PlayTouchDelegate;

public class ProfileInfoBlock extends LinearLayout {
    private final Rect mActionButtonArea;
    private final Rect mOldActionButtonArea;
    private View mProfileActionButton;

    public ProfileInfoBlock(Context context) {
        this(context, null);
    }

    public ProfileInfoBlock(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mActionButtonArea = new Rect();
        this.mOldActionButtonArea = new Rect();
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mProfileActionButton = findViewById(R.id.profile_action_button);
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        int heightExtension = this.mProfileActionButton.getHeight() / 2;
        int widthExtension = this.mProfileActionButton.getWidth() / 3;
        this.mProfileActionButton.getHitRect(this.mActionButtonArea);
        Rect rect = this.mActionButtonArea;
        rect.top -= heightExtension;
        rect = this.mActionButtonArea;
        rect.bottom += heightExtension;
        rect = this.mActionButtonArea;
        rect.left -= widthExtension;
        rect = this.mActionButtonArea;
        rect.right += widthExtension;
        if (this.mActionButtonArea.top != this.mOldActionButtonArea.top || this.mActionButtonArea.bottom != this.mOldActionButtonArea.bottom || this.mActionButtonArea.left != this.mOldActionButtonArea.left || this.mActionButtonArea.right != this.mOldActionButtonArea.right) {
            setTouchDelegate(new PlayTouchDelegate(this.mActionButtonArea, this.mProfileActionButton));
            this.mOldActionButtonArea.set(this.mActionButtonArea);
        }
    }
}
