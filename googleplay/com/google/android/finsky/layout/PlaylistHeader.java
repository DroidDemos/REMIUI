package com.google.android.finsky.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.RelativeLayout;
import com.android.vending.R;

public class PlaylistHeader extends RelativeLayout {
    private View mHeader;
    private final int mMinFullHeight;
    private View mPlaylistControl;
    private View mSubheader;

    public PlaylistHeader(Context context) {
        this(context, null);
    }

    public PlaylistHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mMinFullHeight = context.getResources().getDimensionPixelSize(R.dimen.details_section_header_height);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mHeader = findViewById(R.id.header);
        this.mSubheader = findViewById(R.id.subheader);
        this.mPlaylistControl = findViewById(R.id.song_list_control);
    }

    private boolean isCompactMode() {
        return this.mSubheader.getVisibility() == 8 && this.mPlaylistControl.getVisibility() == 8;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int availableWidth = MeasureSpec.getSize(widthMeasureSpec);
        if (isCompactMode()) {
            this.mHeader.measure(widthMeasureSpec, 0);
            int headerHeight = this.mHeader.getMeasuredHeight();
            int fullContentHeight = (headerHeight + paddingTop) + paddingBottom;
            setMeasuredDimension(availableWidth, fullContentHeight > this.mMinFullHeight ? fullContentHeight : ((paddingTop + headerHeight) + ((((this.mMinFullHeight - headerHeight) - paddingTop) - paddingBottom) / 2)) + paddingBottom);
            return;
        }
        MarginLayoutParams playlistControlLp = (MarginLayoutParams) this.mPlaylistControl.getLayoutParams();
        this.mPlaylistControl.measure(0, 0);
        int headerWidthSpec = MeasureSpec.makeMeasureSpec((availableWidth - this.mPlaylistControl.getMeasuredWidth()) - playlistControlLp.rightMargin, 1073741824);
        int height = paddingTop;
        this.mHeader.measure(headerWidthSpec, 0);
        height += this.mHeader.getMeasuredHeight();
        if (this.mSubheader.getVisibility() != 8) {
            this.mSubheader.measure(headerWidthSpec, 0);
            height += this.mSubheader.getMeasuredHeight();
        }
        setMeasuredDimension(availableWidth, Math.max(height + paddingBottom, this.mMinFullHeight));
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (isCompactMode()) {
            int headerBottom = getHeight() - getPaddingBottom();
            this.mHeader.layout(0, headerBottom - this.mHeader.getMeasuredHeight(), this.mHeader.getMeasuredWidth(), headerBottom);
            return;
        }
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingTop();
        int contentHeight = this.mHeader.getMeasuredHeight();
        if (this.mSubheader.getVisibility() != 8) {
            contentHeight += this.mSubheader.getMeasuredHeight();
        }
        int contentTop = paddingTop + ((((getHeight() - paddingTop) - contentHeight) - paddingBottom) / 2);
        this.mHeader.layout(0, contentTop, this.mHeader.getMeasuredWidth(), this.mHeader.getMeasuredHeight() + contentTop);
        int playlistControlLeft = (getWidth() - ((MarginLayoutParams) this.mPlaylistControl.getLayoutParams()).rightMargin) - this.mPlaylistControl.getMeasuredWidth();
        int playlistControlTop = (this.mHeader.getBaseline() + contentTop) - this.mPlaylistControl.getBaseline();
        this.mPlaylistControl.layout(playlistControlLeft, playlistControlTop, this.mPlaylistControl.getMeasuredWidth() + playlistControlLeft, this.mPlaylistControl.getMeasuredHeight() + playlistControlTop);
        if (this.mSubheader.getVisibility() != 8) {
            int subheaderTop = this.mHeader.getBottom();
            this.mSubheader.layout(0, subheaderTop, this.mSubheader.getMeasuredWidth(), this.mSubheader.getMeasuredHeight() + subheaderTop);
        }
    }
}
