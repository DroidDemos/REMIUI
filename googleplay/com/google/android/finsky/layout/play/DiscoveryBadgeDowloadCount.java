package com.google.android.finsky.layout.play;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.drawable.GradientDrawable;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.protos.Details.DiscoveryBadge;
import com.google.android.play.image.BitmapLoader;

public class DiscoveryBadgeDowloadCount extends DiscoveryBadgeBase {
    private TextView mDownloadCountNumber;
    private TextView mDownloadMagnitude;
    private GradientDrawable mDownloadMagnitudeBackground;
    private RelativeLayout mDownloadMagnitudeContainer;
    private int mFocusedOutlineColor;
    private float mOutlineStrokeWidth;
    private Paint mPaint;
    private int mPressedFillColor;
    private int mPressedOutlineColor;
    private int mRingStrokeWidth;

    public DiscoveryBadgeDowloadCount(Context context) {
        this(context, null);
    }

    public DiscoveryBadgeDowloadCount(Context context, AttributeSet attrs) {
        super(context, attrs);
        Resources res = getResources();
        this.mBadgeRadius = res.getDimensionPixelSize(R.dimen.discovery_badge_icon_container_size) / 2;
        this.mDownloadMagnitudeBackground = (GradientDrawable) getResources().getDrawable(R.drawable.download_magnitude_background);
        this.mPaint = new Paint(1);
        this.mRingStrokeWidth = res.getDimensionPixelSize(R.dimen.discovery_badge_download_count_stroke_width);
        this.mOutlineStrokeWidth = 0.5f * ((float) res.getDimensionPixelSize(R.dimen.play_avatar_noring_outline));
        this.mPressedFillColor = res.getColor(R.color.play_avatar_pressed_fill);
        this.mPressedOutlineColor = res.getColor(R.color.play_avatar_pressed_outline);
        this.mFocusedOutlineColor = res.getColor(R.color.play_avatar_focused_outline);
        setWillNotDraw(false);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mDownloadCountNumber = (TextView) findViewById(R.id.download_count_number);
        this.mDownloadMagnitude = (TextView) findViewById(R.id.download_magnitude);
        this.mDownloadMagnitudeContainer = (RelativeLayout) findViewById(R.id.download_magnitude_container);
    }

    public void bind(DiscoveryBadge badge, BitmapLoader bitmapLoader, NavigationManager navigationManager, Document doc, DfeToc dfeToc, PackageManager packageManager, PlayStoreUiElementNode parentNode) {
        super.bind(badge, bitmapLoader, navigationManager, doc, dfeToc, packageManager, parentNode);
        Resources res = getResources();
        if (badge.downloadCount.length() < 4) {
            this.mDownloadCountNumber.setTextSize(0, (float) res.getDimensionPixelSize(R.dimen.discovery_badge_download_count_size_big));
        } else {
            this.mDownloadCountNumber.setTextSize(0, (float) res.getDimensionPixelSize(R.dimen.discovery_badge_download_count_size_small));
        }
        this.mDownloadCountNumber.setText(badge.downloadCount);
        this.mDownloadMagnitude.setText(badge.downloadUnits);
        if (TextUtils.isEmpty(badge.downloadUnits)) {
            this.mDownloadMagnitudeContainer.setVisibility(8);
        } else {
            this.mDownloadMagnitudeContainer.setVisibility(0);
            LayoutParams params = (LayoutParams) this.mDownloadMagnitudeContainer.getLayoutParams();
            if (badge.downloadUnits.length() == 1) {
                params.addRule(7, R.id.icon_container);
            } else {
                params.addRule(14);
            }
            this.mDownloadMagnitudeContainer.setLayoutParams(params);
        }
        this.mDownloadMagnitudeBackground.setColor(this.mCurrentFillColor);
        if (VERSION.SDK_INT >= 16) {
            this.mDownloadMagnitudeContainer.setBackground(this.mDownloadMagnitudeBackground);
        } else {
            this.mDownloadMagnitudeContainer.setBackgroundDrawable(this.mDownloadMagnitudeBackground);
        }
        this.mTitle.setText(badge.title);
        this.mDownloadCountNumber.setContentDescription(null);
        this.mDownloadMagnitude.setContentDescription(null);
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        invalidate();
    }

    protected void onDraw(Canvas canvas) {
        int x = getWidth() / 2;
        int y = this.mBadgeRadius;
        this.mPaint.setColor(this.mCurrentFillColor);
        this.mPaint.setStyle(Style.STROKE);
        this.mPaint.setStrokeWidth((float) this.mRingStrokeWidth);
        canvas.drawCircle((float) x, (float) y, (float) (this.mBadgeRadius - ((this.mRingStrokeWidth * 2) / 3)), this.mPaint);
    }

    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        int x = getWidth() / 2;
        int y = this.mBadgeRadius;
        boolean drawPressed = isPressed() && (isDuplicateParentStateEnabled() || isClickable());
        if (drawPressed) {
            this.mPaint.setColor(this.mPressedFillColor);
            this.mPaint.setStyle(Style.FILL);
            canvas.drawCircle((float) x, (float) y, (float) this.mBadgeRadius, this.mPaint);
            this.mPaint.setColor(this.mPressedOutlineColor);
            this.mPaint.setStyle(Style.STROKE);
            this.mPaint.setStrokeWidth(this.mOutlineStrokeWidth);
            canvas.drawCircle((float) x, (float) y, (float) this.mBadgeRadius, this.mPaint);
        } else if (isFocused()) {
            this.mPaint.setColor(this.mFocusedOutlineColor);
            this.mPaint.setStyle(Style.STROKE);
            this.mPaint.setStrokeWidth(this.mOutlineStrokeWidth);
            canvas.drawCircle((float) x, (float) y, (float) this.mBadgeRadius, this.mPaint);
        }
    }

    protected int getPlayStoreUiElementType() {
        return 1805;
    }
}
