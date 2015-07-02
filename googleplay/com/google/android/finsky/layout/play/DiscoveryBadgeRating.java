package com.google.android.finsky.layout.play;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.FillType;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.protos.Details.DiscoveryBadge;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.play.image.BitmapLoader;
import com.google.android.play.layout.StarRatingBar;
import java.text.NumberFormat;
import java.text.ParseException;

public class DiscoveryBadgeRating extends DiscoveryBadgeBase {
    private TextView mAverageValue;
    private TextView mCount;
    private NumberFormat mFloatFormatter;
    private int mFocusedOutlineColor;
    private Path mOctagonPath;
    private float mOutlineStrokeWidth;
    private Paint mPaint;
    private int mPressedFillColor;
    private int mPressedOutlineColor;
    private StarRatingBar mRatingBar;
    private PointF[] mVertices;
    private float mWhiteOctagonRadius;
    private int mWhiteOctagonStrokeWidth;

    public DiscoveryBadgeRating(Context context) {
        this(context, null);
    }

    public DiscoveryBadgeRating(Context context, AttributeSet attrs) {
        super(context, attrs);
        Resources res = getResources();
        this.mBadgeRadius = res.getDimensionPixelSize(R.dimen.discovery_badge_icon_container_size) / 2;
        this.mFloatFormatter = NumberFormat.getNumberInstance();
        this.mFloatFormatter.setMinimumFractionDigits(1);
        this.mFloatFormatter.setMaximumFractionDigits(1);
        this.mPaint = new Paint(1);
        this.mOctagonPath = new Path();
        this.mOctagonPath.setFillType(FillType.EVEN_ODD);
        setWillNotDraw(false);
        this.mVertices = new PointF[8];
        for (int i = 0; i < 8; i++) {
            this.mVertices[i] = new PointF();
        }
        this.mWhiteOctagonStrokeWidth = res.getDimensionPixelSize(R.dimen.discovery_badge_rating_white_stroke_width);
        this.mWhiteOctagonRadius = (float) ((this.mBadgeRadius - this.mWhiteOctagonStrokeWidth) - (this.mWhiteOctagonStrokeWidth / 2));
        this.mPressedFillColor = res.getColor(R.color.play_avatar_pressed_fill);
        this.mPressedOutlineColor = res.getColor(R.color.play_avatar_pressed_outline);
        this.mFocusedOutlineColor = res.getColor(R.color.play_avatar_focused_outline);
        this.mOutlineStrokeWidth = 0.5f * ((float) res.getDimensionPixelSize(R.dimen.play_avatar_noring_outline));
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mAverageValue = (TextView) findViewById(R.id.average_value);
        this.mRatingBar = (StarRatingBar) findViewById(R.id.discovery_summary_rating_bar);
        this.mCount = (TextView) findViewById(R.id.title);
    }

    public void bind(DiscoveryBadge badge, BitmapLoader bitmapLoader, NavigationManager navigationManager, Document doc, DfeToc dfeToc, PackageManager packageManager, PlayStoreUiElementNode parentNode) {
        super.bind(badge, bitmapLoader, navigationManager, doc, dfeToc, packageManager, parentNode);
        String formattedRating = this.mFloatFormatter.format((double) badge.aggregateRating);
        this.mAverageValue.setText(formattedRating);
        try {
            this.mRatingBar.setRating(this.mFloatFormatter.parse(formattedRating).floatValue());
        } catch (ParseException e) {
            FinskyLog.d("Cannot parse %s. Exception %s", formattedRating, e);
        }
        this.mCount.setText(badge.title);
        this.mAverageValue.setContentDescription(null);
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        invalidate();
    }

    protected void onDraw(Canvas canvas) {
        int x = getWidth() / 2;
        int y = this.mBadgeRadius;
        this.mPaint.setColor(this.mCurrentFillColor);
        this.mPaint.setStyle(Style.FILL);
        setOctagonPath((float) x, (float) y, (float) this.mBadgeRadius);
        canvas.drawPath(this.mOctagonPath, this.mPaint);
        this.mPaint.setStrokeWidth((float) this.mWhiteOctagonStrokeWidth);
        this.mPaint.setColor(-1);
        this.mPaint.setStyle(Style.STROKE);
        setOctagonPath((float) x, (float) y, this.mWhiteOctagonRadius);
        canvas.drawPath(this.mOctagonPath, this.mPaint);
        boolean drawPressed = isPressed() && (isDuplicateParentStateEnabled() || isClickable());
        if (drawPressed) {
            setOctagonPath((float) x, (float) y, (float) this.mBadgeRadius);
            this.mPaint.setColor(this.mPressedFillColor);
            this.mPaint.setStyle(Style.FILL);
            canvas.drawPath(this.mOctagonPath, this.mPaint);
            this.mPaint.setColor(this.mPressedOutlineColor);
            this.mPaint.setStyle(Style.STROKE);
            this.mPaint.setStrokeWidth(this.mOutlineStrokeWidth);
            canvas.drawPath(this.mOctagonPath, this.mPaint);
        } else if (isFocused()) {
            this.mPaint.setColor(this.mFocusedOutlineColor);
            this.mPaint.setStyle(Style.STROKE);
            this.mPaint.setStrokeWidth(this.mOutlineStrokeWidth);
            setOctagonPath((float) x, (float) y, (float) this.mBadgeRadius);
            canvas.drawPath(this.mOctagonPath, this.mPaint);
        }
    }

    private void setOctagonPath(float centerX, float centerY, float radius) {
        updateOctagonCoordinates(centerX, centerY, radius, this.mVertices);
        this.mOctagonPath.reset();
        this.mOctagonPath.moveTo(this.mVertices[0].x, this.mVertices[0].y);
        for (int i = 1; i < this.mVertices.length; i++) {
            this.mOctagonPath.lineTo(this.mVertices[i].x, this.mVertices[i].y);
        }
        this.mOctagonPath.close();
    }

    private void updateOctagonCoordinates(float centerX, float centerY, float radius, PointF[] vertices) {
        double offset = Math.sin(0.7853981633974483d);
        this.mVertices[0].x = -1.0f * radius;
        this.mVertices[0].y = 0.0f;
        this.mVertices[1].x = (float) (-((int) (((double) radius) * offset)));
        this.mVertices[1].y = (float) (-((int) (((double) radius) * offset)));
        this.mVertices[2].x = 0.0f;
        this.mVertices[2].y = -radius;
        this.mVertices[3].x = (float) ((int) (((double) radius) * offset));
        this.mVertices[3].y = (float) (-((int) (((double) radius) * offset)));
        this.mVertices[4].x = radius;
        this.mVertices[4].y = 0.0f;
        this.mVertices[5].x = (float) ((int) (((double) radius) * offset));
        this.mVertices[5].y = (float) ((int) (((double) radius) * offset));
        this.mVertices[6].x = 0.0f;
        this.mVertices[6].y = radius;
        this.mVertices[7].x = (float) (-((int) (((double) radius) * offset)));
        this.mVertices[7].y = (float) ((int) (((double) radius) * offset));
        for (PointF p : this.mVertices) {
            p.x += centerX;
            p.y += centerY;
        }
    }

    protected int getPlayStoreUiElementType() {
        return 1802;
    }
}
