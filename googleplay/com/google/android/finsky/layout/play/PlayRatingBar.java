package com.google.android.finsky.layout.play;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import com.android.vending.R;
import com.google.android.finsky.layout.play.PlayRatingStar.OnPressStateChangeListener;

public class PlayRatingBar extends ViewGroup implements OnClickListener, OnFocusChangeListener, OnPressStateChangeListener {
    private int mCurrentRating;
    private int mExtraVerticalPadding;
    private OnRatingChangeListener mListener;
    private PlayRatingStar[] mStars;

    public interface OnRatingChangeListener {
        void onRatingChanged(PlayRatingBar playRatingBar, int i);
    }

    public PlayRatingBar(Context context) {
        this(context, null);
    }

    public PlayRatingBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        Resources res = getResources();
        this.mStars = new PlayRatingStar[5];
        this.mStars[0] = (PlayRatingStar) findViewById(R.id.star1);
        this.mStars[0].setContentDescription(res.getString(R.string.one_star));
        this.mStars[1] = (PlayRatingStar) findViewById(R.id.star2);
        this.mStars[1].setContentDescription(res.getString(R.string.two_star));
        this.mStars[2] = (PlayRatingStar) findViewById(R.id.star3);
        this.mStars[2].setContentDescription(res.getString(R.string.three_star));
        this.mStars[3] = (PlayRatingStar) findViewById(R.id.star4);
        this.mStars[3].setContentDescription(res.getString(R.string.four_star));
        this.mStars[4] = (PlayRatingStar) findViewById(R.id.star5);
        this.mStars[4].setContentDescription(res.getString(R.string.five_star));
    }

    public void setVerticalPadding(int verticalPaddingResourceId) {
        this.mExtraVerticalPadding = verticalPaddingResourceId > 0 ? getResources().getDimensionPixelSize(verticalPaddingResourceId) : 0;
        requestLayout();
        invalidate();
    }

    public void configure(int initialRating, int backend, OnRatingChangeListener listener) {
        this.mListener = listener;
        for (int i = 0; i < 5; i++) {
            boolean z;
            PlayRatingStar currentRatingStar = this.mStars[i];
            currentRatingStar.configure(i, backend);
            currentRatingStar.setOnFocusChangeListener(this);
            currentRatingStar.setOnPressStateChangeListener(this);
            currentRatingStar.setOnClickListener(this);
            if (i < initialRating) {
                z = true;
            } else {
                z = false;
            }
            currentRatingStar.setFilled(z);
        }
        this.mCurrentRating = initialRating;
    }

    private void resetVisualState() {
        for (int i = 0; i < this.mStars.length; i++) {
            boolean z;
            this.mStars[i].setFocused(false);
            PlayRatingStar playRatingStar = this.mStars[i];
            if (i < this.mCurrentRating) {
                z = true;
            } else {
                z = false;
            }
            playRatingStar.setFilled(z);
        }
    }

    public void onFocusChange(View v, boolean hasFocus) {
        View focusedChild = getFocusedChild();
        if (focusedChild == null || !focusedChild.isFocused()) {
            resetVisualState();
            return;
        }
        int starIndex = ((PlayRatingStar) v).getIndex();
        if (hasFocus) {
            for (int i = 0; i < this.mStars.length; i++) {
                boolean isFocusedAndFilled;
                if (i <= starIndex) {
                    isFocusedAndFilled = true;
                } else {
                    isFocusedAndFilled = false;
                }
                this.mStars[i].setFocused(isFocusedAndFilled);
                this.mStars[i].setFilled(isFocusedAndFilled);
            }
            return;
        }
        this.mStars[starIndex].setFocused(false);
        this.mStars[starIndex].setFilled(false);
    }

    public void onPressStateChanged(View view, boolean isPressed) {
        int starIndex = ((PlayRatingStar) view).getIndex();
        int i;
        boolean z;
        if (isPressed) {
            for (i = 0; i < this.mStars.length; i++) {
                PlayRatingStar playRatingStar = this.mStars[i];
                if (i <= starIndex) {
                    z = true;
                } else {
                    z = false;
                }
                playRatingStar.setFilled(z);
            }
            return;
        }
        for (i = 0; i < this.mStars.length; i++) {
            playRatingStar = this.mStars[i];
            if (i < this.mCurrentRating) {
                z = true;
            } else {
                z = false;
            }
            playRatingStar.setFilled(z);
        }
    }

    public void onClick(View v) {
        int starIndex = ((PlayRatingStar) v).getIndex();
        int i = 0;
        while (i < this.mStars.length) {
            this.mStars[i].setFilled(i <= starIndex);
            i++;
        }
        this.mCurrentRating = starIndex + 1;
        this.mListener.onRatingChanged(this, starIndex + 1);
    }

    public int getRating() {
        return this.mCurrentRating;
    }

    public void setRating(int rating) {
        this.mCurrentRating = rating;
        resetVisualState();
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        this.mStars[0].measure(0, 0);
        int height = this.mStars[0].getMeasuredHeight() + (this.mExtraVerticalPadding * 2);
        int heightSpec = MeasureSpec.makeMeasureSpec(height, 1073741824);
        int remainingWidth = width;
        int starCount = this.mStars.length;
        for (int i = 0; i < starCount; i++) {
            int currStarWidth = remainingWidth / (starCount - i);
            this.mStars[i].measure(MeasureSpec.makeMeasureSpec(currStarWidth, 1073741824), heightSpec);
            remainingWidth -= currStarWidth;
        }
        setMeasuredDimension(width, height);
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int x = 0;
        for (View currStar : this.mStars) {
            int currStarWidth = currStar.getMeasuredWidth();
            currStar.layout(x, 0, x + currStarWidth, currStar.getMeasuredHeight());
            x += currStarWidth;
        }
    }
}
