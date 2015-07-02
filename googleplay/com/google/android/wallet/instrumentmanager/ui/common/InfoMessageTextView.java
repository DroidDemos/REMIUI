package com.google.android.wallet.instrumentmanager.ui.common;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import com.google.android.wallet.instrumentmanager.R;
import com.google.android.wallet.instrumentmanager.analytics.UiNode;
import com.google.android.wallet.instrumentmanager.analytics.util.AnalyticsUtil;
import com.google.android.wallet.instrumentmanager.common.util.ParcelableProto;
import com.google.android.wallet.instrumentmanager.pub.analytics.InstrumentManagerUiElement;
import com.google.android.wallet.instrumentmanager.ui.common.ClickSpan.OnClickListener;
import com.google.commerce.payments.orchestration.proto.ui.common.components.InfoMessageOuterClass.InfoMessage;
import java.util.List;

public class InfoMessageTextView extends TextView implements UiNode, OnClickListener {
    private boolean mExpanded;
    private InfoMessage mInfoMessage;
    private boolean mInlineExpandLabel;
    private UiNode mParentUiNode;
    private int mRequestedVisibility;
    private final InstrumentManagerUiElement mUiElement;
    private OnClickListener mUrlClickListener;

    public InfoMessageTextView(Context context) {
        super(context, null);
        this.mInlineExpandLabel = true;
        this.mExpanded = true;
        this.mUiElement = new InstrumentManagerUiElement(1627);
        setVisibility(getVisibility());
    }

    public InfoMessageTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mInlineExpandLabel = true;
        this.mExpanded = true;
        this.mUiElement = new InstrumentManagerUiElement(1627);
        readAttributes(context, attrs);
        setVisibility(getVisibility());
    }

    public InfoMessageTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mInlineExpandLabel = true;
        this.mExpanded = true;
        this.mUiElement = new InstrumentManagerUiElement(1627);
        readAttributes(context, attrs);
        setVisibility(getVisibility());
    }

    private void readAttributes(Context context, AttributeSet attrs) {
        TypedArray viewAttrs = context.obtainStyledAttributes(attrs, R.styleable.WalletImInfoMessageTextView);
        this.mInlineExpandLabel = viewAttrs.getBoolean(R.styleable.WalletImInfoMessageTextView_inlineExpandLabel, true);
        viewAttrs.recycle();
    }

    public void setVisibility(int visibility) {
        this.mRequestedVisibility = visibility;
        if (this.mInfoMessage == null) {
            super.setVisibility(8);
        } else {
            super.setVisibility(visibility);
        }
    }

    public void setInfoMessage(InfoMessage infoMessage) {
        verifyInfoMessage(infoMessage);
        this.mInfoMessage = infoMessage;
        this.mExpanded = false;
        updateContent();
    }

    private void updateContent() {
        if (this.mInfoMessage == null) {
            setText("");
            this.mExpanded = true;
        } else if (TextUtils.isEmpty(this.mInfoMessage.detailedMessageHtml)) {
            this.mExpanded = true;
            ClickSpan.clickify(this, this.mInfoMessage.messageHtml, this);
        } else if (this.mExpanded) {
            ClickSpan.clickify(this, this.mInfoMessage.detailedMessageHtml, this);
        } else if (this.mInlineExpandLabel) {
            ClickSpan.clickify(this, String.format("%s <a href=\"%s\">%s</a>", new Object[]{this.mInfoMessage.messageHtml, "expandInfoText", this.mInfoMessage.showDetailedMessageLabel}), this);
        } else {
            ClickSpan.clickify(this, this.mInfoMessage.messageHtml, this);
        }
        setVisibility(this.mRequestedVisibility);
    }

    public void setParentUiNode(UiNode parentUiNode) {
        this.mParentUiNode = parentUiNode;
    }

    public void setUrlClickListener(OnClickListener listener) {
        this.mUrlClickListener = listener;
    }

    public void expand(boolean expand) {
        if (this.mExpanded != expand) {
            if (expand) {
                AnalyticsUtil.createAndSendClickEvent(this, 1628);
            }
            this.mExpanded = expand;
            updateContent();
        }
    }

    public boolean isExpanded() {
        return this.mExpanded;
    }

    public String getExpandLabel() {
        return this.mInfoMessage.showDetailedMessageLabel;
    }

    public void onClick(View view, String url) {
        if ("expandInfoText".equals(url)) {
            expand(true);
            return;
        }
        AnalyticsUtil.createAndSendClickEvent(this, 1629);
        this.mUrlClickListener.onClick(this, url);
    }

    public Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("parentState", super.onSaveInstanceState());
        bundle.putParcelable("infoMessage", ParcelableProto.forProto(this.mInfoMessage));
        bundle.putBoolean("expanded", this.mExpanded);
        return bundle;
    }

    public void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            this.mInfoMessage = (InfoMessage) ParcelableProto.getProtoFromBundle(bundle, "infoMessage");
            this.mExpanded = bundle.getBoolean("expanded");
            super.onRestoreInstanceState(bundle.getParcelable("parentState"));
            updateContent();
            return;
        }
        super.onRestoreInstanceState(state);
    }

    private static void verifyInfoMessage(InfoMessage infoMessage) {
        if (infoMessage != null) {
            if (TextUtils.isEmpty(infoMessage.messageHtml)) {
                throw new IllegalArgumentException("Info message must contain messageHtml.");
            } else if (TextUtils.isEmpty(infoMessage.detailedMessageHtml) != TextUtils.isEmpty(infoMessage.showDetailedMessageLabel)) {
                throw new IllegalArgumentException("Info message must either contain both detailedMessageHtml and showDetailedMessageLabel, or neither.");
            }
        }
    }

    public UiNode getParentUiNode() {
        return this.mParentUiNode;
    }

    public InstrumentManagerUiElement getUiElement() {
        return this.mUiElement;
    }

    public List<UiNode> getChildren() {
        return null;
    }
}
