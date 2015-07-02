package com.google.android.finsky.layout.play;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.fragments.PageFragmentHost;
import com.google.android.finsky.model.CirclesModel;
import com.google.android.finsky.model.CirclesModel.CirclesModelListener;
import com.google.android.finsky.utils.GPlusUtils;
import com.google.android.finsky.utils.PlayCardUtils;
import com.google.android.gms.common.people.data.AudienceMember;
import com.google.android.play.cardview.CardViewGroupDelegate;
import com.google.android.play.cardview.CardViewGroupDelegates;
import com.google.android.play.layout.PlayCardThumbnail;
import com.google.android.play.layout.PlayCardViewBase;
import java.util.List;

public class PlayCardViewPerson extends PlayCardViewBase implements OnClickListener, CirclesModelListener {
    private CirclesModel mCircleModel;
    private PlayCirclesIcon mCirclesIcon;
    private TextView mCirclesStatus;
    private ImageView mCirclesStatusIcon;

    public PlayCardViewPerson(Context context) {
        this(context, null);
    }

    public PlayCardViewPerson(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mCirclesIcon = (PlayCirclesIcon) findViewById(R.id.gplus_circle_status);
        this.mCirclesIcon.setOnClickListener(this);
        this.mCirclesStatus = (TextView) findViewById(R.id.circles_status);
        this.mCirclesStatusIcon = (ImageView) findViewById(R.id.circles_status_icon);
    }

    public CardViewGroupDelegate getCardViewGroupDelegate() {
        return CardViewGroupDelegates.NO_CARD_BG_IMPL;
    }

    public void setData(Object data, int backendId) {
        super.setData(data, backendId);
        Document plusDoc = (Document) getData();
        if (!(this.mCircleModel == null || (this.mCircleModel.getOwnerAccountName() == FinskyApp.get().getCurrentAccountName() && this.mCircleModel.getTargetPersonDoc().getBackendDocId() == plusDoc.getBackendDocId()))) {
            this.mCircleModel.setCirclesModelListener(null);
            this.mCircleModel = null;
        }
        if (this.mCircleModel == null) {
            String accountName = FinskyApp.get().getCurrentAccountName();
            this.mCircleModel = FinskyApp.get().getClientMutationCache(accountName).getCachedCirclesModel(plusDoc, accountName);
            this.mCircleModel.setCirclesModelListener(this);
            this.mCircleModel.loadCircles(FinskyApp.get().getApplicationContext(), ((PageFragmentHost) getContext()).getPeopleClient());
        }
        configure(this.mCircleModel.getCircles());
    }

    public void showCirclesIcon(boolean showCirclesIcon) {
        this.mCirclesIcon.setVisibility(showCirclesIcon ? 0 : 8);
    }

    public void onCirclesUpdate(List<AudienceMember> circles) {
        configure(circles);
    }

    public void onClick(View view) {
        if ((view.getContext() instanceof FragmentActivity) && view == this.mCirclesIcon) {
            FinskyApp.get().getEventLogger().logClickEvent(280, null, PlayCardUtils.getCardParentNode(this));
            this.mCircleModel.launchCirclePicker((FragmentActivity) view.getContext());
        }
    }

    private void configure(List<AudienceMember> circles) {
        boolean isInCircles;
        if (circles == null || circles.isEmpty()) {
            isInCircles = false;
        } else {
            isInCircles = true;
        }
        if (isInCircles) {
            this.mCirclesStatus.setText(GPlusUtils.getCirclesString(circles, getResources()));
            this.mCirclesStatusIcon.setVisibility(0);
        } else {
            this.mCirclesStatus.setText(this.mCircleModel.getTargetPersonDoc().getSubtitle());
            this.mCirclesStatusIcon.setVisibility(8);
        }
        this.mCirclesIcon.configure(this.mCircleModel.getTargetPersonDoc().getTitle(), isInCircles);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int contentWidth = (width - paddingLeft) - paddingRight;
        MarginLayoutParams thumbnailLp = (MarginLayoutParams) this.mThumbnail.getLayoutParams();
        int thumbnailSpec = MeasureSpec.makeMeasureSpec((contentWidth - thumbnailLp.leftMargin) - thumbnailLp.rightMargin, 1073741824);
        this.mThumbnail.measure(thumbnailSpec, thumbnailSpec);
        MarginLayoutParams titleLp = (MarginLayoutParams) this.mTitle.getLayoutParams();
        MarginLayoutParams circleStatusLp = (MarginLayoutParams) this.mCirclesStatus.getLayoutParams();
        this.mTitle.measure(MeasureSpec.makeMeasureSpec((contentWidth - titleLp.leftMargin) - titleLp.rightMargin, 1073741824), 0);
        this.mCirclesStatusIcon.measure(0, 0);
        int circleStatusAvailableWidth = (contentWidth - circleStatusLp.leftMargin) - circleStatusLp.rightMargin;
        if (this.mCirclesStatusIcon.getVisibility() == 0) {
            circleStatusAvailableWidth -= this.mCirclesStatusIcon.getMeasuredWidth();
        }
        this.mCirclesStatus.measure(MeasureSpec.makeMeasureSpec(circleStatusAvailableWidth, Integer.MIN_VALUE), 0);
        this.mCirclesIcon.measure(0, 0);
        PlayCardThumbnail playCardThumbnail = this.mThumbnail;
        int i = thumbnailLp.bottomMargin;
        i = titleLp.topMargin;
        TextView textView = this.mTitle;
        i = titleLp.bottomMargin;
        i = circleStatusLp.topMargin;
        textView = this.mCirclesStatus;
        int contentHeight = (((((((thumbnailLp.topMargin + r0.getMeasuredHeight()) + r0) + r0) + r0.getMeasuredHeight()) + r0) + r0) + r0.getMeasuredHeight()) + circleStatusLp.bottomMargin;
        this.mLoadingIndicator.measure(0, 0);
        setMeasuredDimension(width, (paddingTop + contentHeight) + paddingBottom);
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int width = getWidth();
        int height = getHeight();
        MarginLayoutParams thumbnailLp = (MarginLayoutParams) this.mThumbnail.getLayoutParams();
        int thumbnailHeight = this.mThumbnail.getMeasuredHeight();
        this.mThumbnail.layout(thumbnailLp.leftMargin + paddingLeft, thumbnailLp.topMargin + paddingTop, (thumbnailLp.leftMargin + paddingLeft) + this.mThumbnail.getMeasuredWidth(), (thumbnailLp.topMargin + paddingTop) + thumbnailHeight);
        MarginLayoutParams titleLp = (MarginLayoutParams) this.mTitle.getLayoutParams();
        MarginLayoutParams circleStatusLp = (MarginLayoutParams) this.mCirclesStatus.getLayoutParams();
        int titleTop = (paddingTop + thumbnailHeight) + titleLp.topMargin;
        int titleLeft = paddingLeft + titleLp.leftMargin;
        int titleHeight = this.mTitle.getMeasuredHeight();
        this.mTitle.layout(titleLeft, titleTop, this.mTitle.getMeasuredWidth() + titleLeft, titleTop + titleHeight);
        if (this.mCirclesStatus.getVisibility() != 8) {
            int circleStatusTop = ((titleTop + titleHeight) + titleLp.bottomMargin) + circleStatusLp.topMargin;
            boolean isShowingStatusIcon = this.mCirclesStatusIcon.getVisibility() == 0;
            int circleStatusWidth = this.mCirclesStatus.getMeasuredWidth();
            int circleStatusIconWidth = this.mCirclesStatusIcon.getMeasuredWidth();
            int circleStatusLeft = paddingLeft + ((((width - paddingLeft) - paddingRight) - ((circleStatusLp.rightMargin + (circleStatusLp.leftMargin + circleStatusWidth)) + (isShowingStatusIcon ? circleStatusIconWidth : 0))) / 2);
            if (this.mCirclesStatusIcon.getVisibility() == 0) {
                int circleStatusIconTop = ((this.mCirclesStatus.getMeasuredHeight() / 2) + circleStatusTop) - (this.mCirclesStatusIcon.getMeasuredHeight() / 2);
                this.mCirclesStatusIcon.layout(circleStatusLeft, circleStatusIconTop, circleStatusLeft + circleStatusIconWidth, this.mCirclesStatusIcon.getMeasuredHeight() + circleStatusIconTop);
                circleStatusLeft += this.mCirclesStatusIcon.getMeasuredWidth();
            }
            circleStatusLeft += circleStatusLp.leftMargin;
            this.mCirclesStatus.layout(circleStatusLeft, circleStatusTop, circleStatusLeft + circleStatusWidth, this.mCirclesStatus.getMeasuredHeight() + circleStatusTop);
        }
        int thumbnailCoverPadding = this.mThumbnail.getCoverPadding();
        int gplusCircleStatusIconBottom = this.mThumbnail.getBottom() - thumbnailCoverPadding;
        int gplusCircleStatusIconRight = this.mThumbnail.getRight() - thumbnailCoverPadding;
        this.mCirclesIcon.layout(gplusCircleStatusIconRight - this.mCirclesIcon.getMeasuredWidth(), gplusCircleStatusIconBottom - this.mCirclesIcon.getMeasuredHeight(), gplusCircleStatusIconRight, gplusCircleStatusIconBottom);
        int loadingLeft = paddingLeft + ((((width - paddingLeft) - paddingRight) - this.mLoadingIndicator.getMeasuredWidth()) / 2);
        int loadingTop = paddingTop + ((((height - paddingTop) - paddingBottom) - this.mLoadingIndicator.getMeasuredHeight()) / 2);
        this.mLoadingIndicator.layout(loadingLeft, loadingTop, this.mLoadingIndicator.getMeasuredWidth() + loadingLeft, this.mLoadingIndicator.getMeasuredHeight() + loadingTop);
    }

    public int getCardType() {
        return 12;
    }

    public void onDetachedFromWindow() {
        if (this.mCircleModel != null) {
            this.mCircleModel.setCirclesModelListener(null);
            this.mCircleModel = null;
        }
        super.onDetachedFromWindow();
    }
}
