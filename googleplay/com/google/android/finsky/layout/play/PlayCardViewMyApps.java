package com.google.android.finsky.layout.play;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.TouchDelegate;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Checkable;
import com.android.vending.R;
import com.google.android.finsky.api.model.Document;
import com.google.android.play.layout.PlayCardViewBase;

public class PlayCardViewMyApps extends PlayCardViewBase implements Checkable, Identifiable {
    private static final int[] CHECKED_STATE_SET;
    private final Rect mArchiveArea;
    private View mArchiveView;
    private boolean mChecked;
    private String mIdentifier;
    private final Rect mOldArchiveArea;

    public interface OnArchiveActionListener {
        void onArchiveAction(Document document);
    }

    static {
        CHECKED_STATE_SET = new int[]{16842912};
    }

    public PlayCardViewMyApps(Context context) {
        this(context, null);
    }

    public PlayCardViewMyApps(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mChecked = false;
        this.mThumbnailAspectRatio = 1.0f;
        this.mArchiveArea = new Rect();
        this.mOldArchiveArea = new Rect();
    }

    public int getCardType() {
        return 9;
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mArchiveView = findViewById(R.id.li_archive);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureThumbnailSpanningHeight(heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void setArchivable(boolean isArchivable, final OnArchiveActionListener onArchiveActionListener) {
        if (isArchivable) {
            this.mArchiveView.setVisibility(0);
            this.mArchiveView.setFocusable(true);
            this.mArchiveView.setNextFocusLeftId(R.id.accessibility_overlay);
            setNextFocusRightId(this.mArchiveView.getId());
            this.mArchiveView.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    onArchiveActionListener.onArchiveAction((Document) PlayCardViewMyApps.this.getData());
                }
            });
            return;
        }
        this.mArchiveView.setVisibility(8);
        setNextFocusRightId(-1);
        this.mArchiveView.setOnClickListener(null);
        this.mArchiveView.setClickable(false);
    }

    public void setChecked(boolean checked) {
        if (this.mChecked != checked) {
            this.mChecked = checked;
            refreshDrawableState();
        }
    }

    public boolean isChecked() {
        return this.mChecked;
    }

    public void toggle() {
        setChecked(!this.mChecked);
    }

    protected int[] onCreateDrawableState(int extraSpace) {
        int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
        if (this.mChecked) {
            mergeDrawableStates(drawableState, CHECKED_STATE_SET);
        }
        return drawableState;
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (this.mArchiveView != null && this.mArchiveView.getVisibility() != 8) {
            this.mArchiveView.getHitRect(this.mArchiveArea);
            Rect rect = this.mArchiveArea;
            rect.bottom += this.mArchiveArea.height() / 2;
            rect = this.mArchiveArea;
            rect.top -= this.mArchiveView.getTop();
            rect = this.mArchiveArea;
            rect.left -= this.mArchiveArea.width() / 2;
            rect = this.mArchiveArea;
            rect.right += getWidth() - this.mArchiveView.getRight();
            if (!this.mArchiveArea.equals(this.mOldArchiveArea)) {
                setTouchDelegate(new TouchDelegate(this.mArchiveArea, this.mArchiveView));
                this.mOldArchiveArea.set(this.mArchiveArea);
            }
        }
    }

    public void setIdentifier(String identifier) {
        this.mIdentifier = identifier;
    }

    public String getIdentifier() {
        return this.mIdentifier;
    }
}
