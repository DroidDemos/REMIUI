package com.google.android.finsky.layout.play;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View.OnClickListener;
import com.android.vending.R;
import com.google.android.finsky.adapters.Recyclable;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.analytics.PlayStore.PlayStoreUiElement;
import com.google.android.finsky.layout.IdentifiableRelativeLayout;

public abstract class PlayClusterView extends IdentifiableRelativeLayout implements Recyclable, PlayStoreUiElementNode {
    protected PlayCardClusterViewHeader mHeader;
    private PlayStoreUiElementNode mParentNode;
    private PlayStoreUiElementNode mParentOfChildren;
    private PlayStoreUiElement mUiElementProto;
    private final int mVerticalPadding;

    public PlayClusterView(Context context) {
        this(context, null);
    }

    public PlayClusterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mVerticalPadding = context.getResources().getDimensionPixelSize(R.dimen.play_cluster_vpadding);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mHeader = (PlayCardClusterViewHeader) findViewById(R.id.cluster_header);
    }

    public void onRecycle() {
        this.mParentNode = null;
        this.mParentOfChildren = null;
    }

    protected int getPlayStoreUiElementType() {
        return 400;
    }

    protected void configureLogging(byte[] serverLogsCookie, PlayStoreUiElementNode parentNode) {
        if (serverLogsCookie != null) {
            this.mUiElementProto = FinskyEventLog.obtainPlayStoreUiElement(getPlayStoreUiElementType());
            FinskyEventLog.setServerLogCookie(this.mUiElementProto, serverLogsCookie);
            this.mParentNode = parentNode;
            this.mParentOfChildren = this;
            return;
        }
        this.mUiElementProto = null;
        this.mParentNode = null;
        this.mParentOfChildren = parentNode;
    }

    protected void logEmptyClusterImpression() {
        getParentNode().childImpression(this);
    }

    public boolean hasHeader() {
        return this.mHeader != null;
    }

    public void hideHeader() {
        this.mHeader.setVisibility(8);
        setPadding(0, 0, 0, 0);
    }

    public void showHeader(int backendId, String titleMain, String titleSecondary, String more, OnClickListener clickListener, int headerExtraHorizontalPadding) {
        this.mHeader.setContent(backendId, titleMain, titleSecondary, more, clickListener);
        this.mHeader.setVisibility(0);
        this.mHeader.setExtraHorizontalPadding(headerExtraHorizontalPadding);
        setPadding(0, this.mVerticalPadding, 0, this.mVerticalPadding);
    }

    protected PlayStoreUiElementNode getParentOfChildren() {
        return this.mParentOfChildren;
    }

    public PlayStoreUiElement getPlayStoreUiElement() {
        return this.mUiElementProto;
    }

    public PlayStoreUiElementNode getParentNode() {
        return this.mParentNode;
    }

    public void childImpression(PlayStoreUiElementNode childNode) {
        FinskyEventLog.childImpression(this, childNode);
    }
}
