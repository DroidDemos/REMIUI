package com.google.android.finsky.layout.play;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.analytics.PlayStore.PlayStoreUiElement;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.protos.Browse.QuickLink;
import com.google.android.finsky.protos.Common.Image;
import com.google.android.finsky.utils.CorpusResourceUtils;
import com.google.android.play.cardview.CardViewGroupDelegate;
import com.google.android.play.cardview.CardViewGroupDelegates;
import com.google.android.play.image.BitmapLoader;
import com.google.android.play.image.FifeImageView;
import com.google.android.play.layout.ForegroundRelativeLayout;

public class PlayQuickLinkBase extends ForegroundRelativeLayout implements PlayStoreUiElementNode {
    protected FifeImageView mLinkIcon;
    protected TextView mLinkText;
    private PlayStoreUiElementNode mParentNode;
    private PlayStoreUiElement mUiElementProto;

    public PlayQuickLinkBase(Context context) {
        this(context, null, 0);
    }

    public PlayQuickLinkBase(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PlayQuickLinkBase(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        getCardViewGroupDelegate().initialize(this, context, attrs, defStyle);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mLinkIcon = (FifeImageView) findViewById(R.id.li_icon);
        this.mLinkText = (TextView) findViewById(R.id.li_title);
    }

    public CardViewGroupDelegate getCardViewGroupDelegate() {
        return CardViewGroupDelegates.IMPL;
    }

    public void bind(QuickLink quickLink, int backendId, NavigationManager navigationManager, DfeToc dfeToc, BitmapLoader bitmapLoader, PlayStoreUiElementNode parentNode) {
        this.mLinkIcon.setVisibility(8);
        String name = quickLink.name;
        if (!TextUtils.isEmpty(name)) {
            this.mLinkText.setText(name.toUpperCase());
        }
        Image image = quickLink.image;
        if (image != null) {
            this.mLinkIcon.setImage(image.imageUrl, image.supportsFifeUrlOptions, bitmapLoader);
            this.mLinkIcon.setVisibility(0);
            this.mLinkText.setGravity(19);
        } else {
            this.mLinkIcon.setVisibility(8);
            this.mLinkText.setGravity(17);
        }
        Resources res = getResources();
        CardViewGroupDelegate cardViewGroupDelegate = getCardViewGroupDelegate();
        if (quickLink.hasPrismStyle && quickLink.prismStyle) {
            cardViewGroupDelegate.setBackgroundColor(this, CorpusResourceUtils.getPrimaryColor(getContext(), quickLink.backendId));
            cardViewGroupDelegate.setCardElevation(this, 0.0f);
            this.mLinkText.setTextColor(res.getColor(R.color.white));
            setForeground(res.getDrawable(R.drawable.play_highlight_round_overlay_dark));
        } else {
            cardViewGroupDelegate.setBackgroundColor(this, res.getColor(R.color.play_card_light_background));
            cardViewGroupDelegate.setCardElevation(this, (float) res.getDimensionPixelSize(R.dimen.play_card_default_elevation));
            this.mLinkText.setTextColor(res.getColor(R.color.play_fg_secondary));
            setForeground(res.getDrawable(R.drawable.play_highlight_round_overlay_light));
        }
        int linkInset = res.getDimensionPixelSize(R.dimen.play_card_default_inset);
        setForegroundPadding(linkInset, linkInset, linkInset, linkInset);
        if (quickLink.link != null) {
            final NavigationManager navigationManager2 = navigationManager;
            final QuickLink quickLink2 = quickLink;
            final int i = backendId;
            final DfeToc dfeToc2 = dfeToc;
            setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    navigationManager2.resolveLink(quickLink2.link, quickLink2.name, i, dfeToc2, PlayQuickLinkBase.this);
                }
            });
            this.mUiElementProto = FinskyEventLog.obtainPlayStoreUiElement(101);
            FinskyEventLog.setServerLogCookie(this.mUiElementProto, quickLink.serverLogsCookie);
            this.mParentNode = parentNode;
            getParentNode().childImpression(this);
        } else {
            setOnClickListener(null);
            setClickable(false);
        }
        setContentDescription(name);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int contentHeight = (height - getPaddingTop()) - getPaddingBottom();
        int textWidth = (width - getPaddingLeft()) - getPaddingRight();
        if (this.mLinkIcon.getVisibility() == 0) {
            MarginLayoutParams iconLp = (MarginLayoutParams) this.mLinkIcon.getLayoutParams();
            int iconSize = (contentHeight - iconLp.topMargin) - iconLp.bottomMargin;
            int iconSpec = MeasureSpec.makeMeasureSpec(iconSize, 1073741824);
            this.mLinkIcon.measure(iconSpec, iconSpec);
            textWidth -= (iconLp.leftMargin + iconSize) + iconLp.rightMargin;
        }
        this.mLinkText.measure(MeasureSpec.makeMeasureSpec(textWidth, 1073741824), MeasureSpec.makeMeasureSpec(contentHeight, 1073741824));
        setMeasuredDimension(width, height);
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int textLeft = paddingLeft;
        if (this.mLinkIcon.getVisibility() == 0) {
            MarginLayoutParams iconLp = (MarginLayoutParams) this.mLinkIcon.getLayoutParams();
            int iconLeft = paddingLeft + iconLp.leftMargin;
            int iconTop = paddingTop + iconLp.topMargin;
            this.mLinkIcon.layout(iconLeft, iconTop, this.mLinkIcon.getMeasuredWidth() + iconLeft, this.mLinkIcon.getMeasuredHeight() + iconTop);
            textLeft += (iconLp.leftMargin + this.mLinkIcon.getMeasuredWidth()) + iconLp.rightMargin;
        }
        this.mLinkText.layout(textLeft, paddingTop, this.mLinkText.getMeasuredWidth() + textLeft, this.mLinkText.getMeasuredHeight() + paddingTop);
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
