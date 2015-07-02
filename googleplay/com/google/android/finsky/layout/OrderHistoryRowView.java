package com.google.android.finsky.layout;

import android.content.Context;
import android.os.Build.VERSION;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.Transformation;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.AppActionAnalyzer;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.analytics.PlayStore.PlayStoreUiElement;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.layout.play.GenericUiElementNode;
import com.google.android.finsky.layout.play.PlayCardClusterMetadata;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.protos.Common.Offer;
import com.google.android.finsky.protos.DocAnnotations.PurchaseHistoryDetails;
import com.google.android.finsky.utils.DateUtils;
import com.google.android.finsky.utils.PlayAnimationUtils;
import com.google.android.finsky.utils.PlayCardImageTypeSequence;
import com.google.android.play.image.BitmapLoader;
import com.google.android.play.layout.ForegroundRelativeLayout;
import com.google.android.play.layout.PlayActionButton;
import com.google.android.play.layout.PlayCardThumbnail;

public class OrderHistoryRowView extends ForegroundRelativeLayout implements PlayStoreUiElementNode {
    private final int mBaseRowHeight;
    private final int mBaseRowHeightExpanded;
    private boolean mCanOpen;
    private boolean mCanRefund;
    private final ResizeAnimation mCollapseAnimation;
    private int mCollapsedBackgroundResourceId;
    private TextView mDateView;
    private final FinskyEventLog mEventLogger;
    private final ResizeAnimation mExpandAnimation;
    private boolean mExpanded;
    private boolean mHasPurchaseDetails;
    private PlayActionButton mOpenButton;
    private PlayStoreUiElementNode mParentNode;
    private TextView mPriceView;
    private TextView mPurchaseDetailsView;
    private PlayActionButton mRefundButton;
    private TextView mStatusView;
    private PlayCardThumbnail mThumbnail;
    private float mThumbnailAspectRatio;
    private TextView mTitleView;
    private PlayStoreUiElement mUiElementProto;

    public interface OnRefundActionListener {
        void onRefundAction(String str, String str2);
    }

    private static class ResizeAnimation extends Animation {
        public int startHeight;
        public int targetHeight;
        public final View view;

        public ResizeAnimation(View view) {
            this.view = view;
        }

        public void setHeights(int startHeight, int targetHeight) {
            this.startHeight = startHeight;
            this.targetHeight = targetHeight;
        }

        protected void applyTransformation(float interpolatedTime, Transformation t) {
            this.view.getLayoutParams().height = (int) (((float) this.startHeight) + (((float) (this.targetHeight - this.startHeight)) * interpolatedTime));
            this.view.requestLayout();
        }
    }

    public OrderHistoryRowView(Context context) {
        this(context, null);
    }

    public OrderHistoryRowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mUiElementProto = FinskyEventLog.obtainPlayStoreUiElement(2602);
        this.mBaseRowHeight = getResources().getDimensionPixelSize(R.dimen.my_account_row_height);
        this.mBaseRowHeightExpanded = getResources().getDimensionPixelSize(R.dimen.my_account_row_height_expanded);
        this.mEventLogger = FinskyApp.get().getEventLogger();
        this.mExpandAnimation = new ResizeAnimation(this);
        this.mExpandAnimation.setDuration(150);
        this.mExpandAnimation.setAnimationListener(new AnimationListener() {
            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                if (OrderHistoryRowView.this.mHasPurchaseDetails) {
                    OrderHistoryRowView.this.mPurchaseDetailsView.setVisibility(0);
                    PlayAnimationUtils.animateFadeIn(OrderHistoryRowView.this.mPurchaseDetailsView, 150);
                }
                if (OrderHistoryRowView.this.mCanOpen) {
                    OrderHistoryRowView.this.mOpenButton.setVisibility(0);
                    PlayAnimationUtils.animateFadeIn(OrderHistoryRowView.this.mOpenButton, 150);
                }
                if (OrderHistoryRowView.this.mCanRefund) {
                    OrderHistoryRowView.this.mRefundButton.setVisibility(0);
                    PlayAnimationUtils.animateFadeIn(OrderHistoryRowView.this.mRefundButton, 150);
                }
            }

            public void onAnimationRepeat(Animation animation) {
            }
        });
        this.mCollapseAnimation = new ResizeAnimation(this);
        this.mCollapseAnimation.setDuration(150);
        this.mCollapseAnimation.setAnimationListener(new AnimationListener() {
            public void onAnimationStart(Animation animation) {
                OrderHistoryRowView.this.mPurchaseDetailsView.setVisibility(8);
                OrderHistoryRowView.this.mOpenButton.setVisibility(8);
                OrderHistoryRowView.this.mRefundButton.setVisibility(8);
            }

            public void onAnimationEnd(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mThumbnail = (PlayCardThumbnail) findViewById(R.id.li_thumbnail_frame);
        this.mTitleView = (TextView) findViewById(R.id.title);
        this.mDateView = (TextView) findViewById(R.id.date);
        this.mPriceView = (TextView) findViewById(R.id.price);
        this.mStatusView = (TextView) findViewById(R.id.status);
        this.mPurchaseDetailsView = (TextView) findViewById(R.id.purchase_details);
        this.mOpenButton = (PlayActionButton) findViewById(R.id.open_button);
        this.mRefundButton = (PlayActionButton) findViewById(R.id.refund_button);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        MarginLayoutParams thumbnailLp = (MarginLayoutParams) this.mThumbnail.getLayoutParams();
        thumbnailLp.width = (int) (((float) ((((MeasureSpec.getSize(heightMeasureSpec) - getPaddingTop()) - getPaddingBottom()) - thumbnailLp.topMargin) - thumbnailLp.bottomMargin)) / this.mThumbnailAspectRatio);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public boolean toggleExpansion() {
        if (this.mExpanded) {
            startAnimation(this.mCollapseAnimation);
        } else {
            startAnimation(this.mExpandAnimation);
        }
        this.mExpanded = !this.mExpanded;
        syncBackground();
        return this.mExpanded;
    }

    public void bind(Document doc, BitmapLoader bitmapLoader, NavigationManager navigationManager, Boolean selected, PlayStoreUiElementNode parentNode, int collapsedBackgroundResourceId, OnRefundActionListener onRefundActionListener) {
        int i;
        this.mExpanded = selected.booleanValue();
        this.mCollapsedBackgroundResourceId = collapsedBackgroundResourceId;
        this.mParentNode = parentNode;
        FinskyEventLog.setServerLogCookie(this.mUiElementProto, doc.getServerLogsCookie());
        parentNode.childImpression(this);
        this.mThumbnailAspectRatio = PlayCardClusterMetadata.getAspectRatio(doc.getDocumentType());
        ((DocImageView) this.mThumbnail.getImageView()).bind(doc, bitmapLoader, PlayCardImageTypeSequence.CORE_IMAGE_TYPES);
        this.mTitleView.setText(doc.getTitle());
        PurchaseHistoryDetails details = doc.getPurchaseHistoryDetails();
        if (details.hasPurchaseTimestampMsec) {
            this.mDateView.setText(DateUtils.formatShortDisplayDate(details.purchaseTimestampMsec));
            this.mDateView.setVisibility(0);
        } else {
            this.mDateView.setVisibility(4);
        }
        Offer offer = details.offer;
        if (offer == null || !offer.hasFormattedAmount) {
            this.mPriceView.setVisibility(4);
        } else {
            this.mPriceView.setText(details.offer.formattedAmount);
            this.mPriceView.setVisibility(0);
        }
        if (details.hasPurchaseStatus) {
            this.mStatusView.setText(details.purchaseStatus);
            this.mStatusView.setVisibility(0);
        } else {
            this.mStatusView.setVisibility(4);
        }
        this.mHasPurchaseDetails = details.hasPurchaseDetailsHtml;
        if (this.mHasPurchaseDetails) {
            this.mPurchaseDetailsView.setText(Html.fromHtml(details.purchaseDetailsHtml));
            this.mPurchaseDetailsView.setMovementMethod(LinkMovementMethod.getInstance());
            this.mPurchaseDetailsView.setVisibility(this.mExpanded ? 0 : 8);
        } else {
            this.mPurchaseDetailsView.setVisibility(4);
        }
        this.mCanOpen = NavigationManager.hasClickListener(doc);
        if (this.mCanOpen) {
            this.mOpenButton.configure(doc.getBackend(), (int) R.string.view, navigationManager.getClickListener(doc, new GenericUiElementNode(2605, null, null, this)));
            this.mOpenButton.setVisibility(this.mExpanded ? 0 : 8);
        } else {
            this.mOpenButton.setVisibility(8);
        }
        this.mCanRefund = false;
        if (doc.getDocumentType() == 1) {
            final AppActionAnalyzer analyzer = new AppActionAnalyzer(doc.getBackendDocId(), FinskyApp.get().getAppStates(), FinskyApp.get().getLibraries());
            if (analyzer.isRefundable) {
                this.mCanRefund = true;
                final OnRefundActionListener onRefundActionListener2 = onRefundActionListener;
                final Document document = doc;
                this.mRefundButton.configure(doc.getBackend(), (int) R.string.refund, new OnClickListener() {
                    public void onClick(View v) {
                        OrderHistoryRowView.this.mEventLogger.logClickEvent(2603, null, OrderHistoryRowView.this);
                        onRefundActionListener2.onRefundAction(document.getBackendDocId(), analyzer.refundAccount);
                    }
                });
            }
        }
        if (this.mCanRefund && this.mExpanded) {
            this.mRefundButton.setVisibility(0);
        } else {
            this.mRefundButton.setVisibility(8);
        }
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int collapsedHeight = (paddingTop + paddingBottom) + ((int) (this.mThumbnailAspectRatio * ((float) ((this.mBaseRowHeight - paddingTop) - paddingBottom))));
        int expandedHeight = this.mBaseRowHeightExpanded;
        LayoutParams layoutParams = getLayoutParams();
        if (this.mExpanded) {
            i = expandedHeight;
        } else {
            i = collapsedHeight;
        }
        layoutParams.height = i;
        this.mExpandAnimation.setHeights(collapsedHeight, expandedHeight);
        this.mCollapseAnimation.setHeights(expandedHeight, collapsedHeight);
        syncBackground();
        requestLayout();
    }

    private void syncBackground() {
        if (this.mExpanded) {
            setBackgroundResource(R.color.white);
        } else {
            setBackgroundResource(this.mCollapsedBackgroundResourceId);
        }
        if (VERSION.SDK_INT >= 21) {
            setElevation(this.mExpanded ? 4.0f : 0.0f);
        }
    }

    public PlayStoreUiElement getPlayStoreUiElement() {
        return this.mUiElementProto;
    }

    public PlayStoreUiElementNode getParentNode() {
        return this.mParentNode;
    }

    public void childImpression(PlayStoreUiElementNode childNode) {
        throw new IllegalStateException("unwanted children");
    }
}
