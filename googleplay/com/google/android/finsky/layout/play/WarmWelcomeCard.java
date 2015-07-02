package com.google.android.finsky.layout.play;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.adapters.Recyclable;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.analytics.PlayStore.PlayStoreUiElement;
import com.google.android.finsky.layout.IdentifiableFrameLayout;
import com.google.android.finsky.protos.Common.Image;
import com.google.android.finsky.utils.CorpusResourceUtils;
import com.google.android.play.image.FifeImageView;

public class WarmWelcomeCard extends IdentifiableFrameLayout implements Recyclable, PlayStoreUiElementNode {
    private TextView mBody;
    private WarmWelcomeCardButton mButtonDismiss;
    private WarmWelcomeCardButton mButtonSecondary;
    private View mButtonSeparator;
    private final int mDefaultTitleTopPadding;
    private FifeImageView mGraphic;
    private View mGraphicBox;
    private PlayStoreUiElementNode mParentNode;
    private final boolean mSupportsDynamicTitleTopPadding;
    private TextView mTitle;
    private PlayStoreUiElement mUiElementProto;

    public WarmWelcomeCard(Context context) {
        this(context, null);
    }

    public WarmWelcomeCard(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray viewAttrs = context.obtainStyledAttributes(attrs, R.styleable.WarmWelcomeCard);
        this.mSupportsDynamicTitleTopPadding = viewAttrs.getBoolean(0, true);
        viewAttrs.recycle();
        this.mDefaultTitleTopPadding = context.getResources().getDimensionPixelSize(R.dimen.warm_welcome_title_top_padding);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mTitle = (TextView) findViewById(R.id.warm_welcome_title);
        this.mBody = (TextView) findViewById(R.id.warm_welcome_body);
        this.mGraphicBox = findViewById(R.id.warm_welcome_graphic_box);
        this.mGraphic = (FifeImageView) this.mGraphicBox.findViewById(R.id.warm_welcome_graphic);
        this.mButtonDismiss = (WarmWelcomeCardButton) findViewById(R.id.button_dismiss);
        this.mButtonSecondary = (WarmWelcomeCardButton) findViewById(R.id.button_secondary);
        this.mButtonSeparator = findViewById(R.id.button_separator);
    }

    public void configureContent(CharSequence title, CharSequence body, Image graphic, int backendId, PlayStoreUiElementNode parentNode, byte[] serverLogsCookie) {
        boolean isContentCorpus;
        boolean setTitleTopPaddingToZero = true;
        int titleTopPadding = 0;
        this.mTitle.setText(title);
        this.mBody.setText(body);
        if (backendId == 0 || backendId == 9) {
            isContentCorpus = false;
        } else {
            isContentCorpus = true;
        }
        if (graphic != null) {
            this.mGraphicBox.setVisibility(0);
            this.mGraphic.setImage(graphic.imageUrl, graphic.supportsFifeUrlOptions, FinskyApp.get().getBitmapLoader());
            if (isContentCorpus) {
                this.mGraphicBox.setBackgroundColor(CorpusResourceUtils.getPrimaryColor(getContext(), backendId));
            } else {
                this.mGraphicBox.setBackgroundDrawable(null);
            }
        } else {
            this.mGraphicBox.setVisibility(8);
        }
        if (graphic == null || isContentCorpus || !this.mSupportsDynamicTitleTopPadding) {
            setTitleTopPaddingToZero = false;
        }
        if (!setTitleTopPaddingToZero) {
            titleTopPadding = this.mDefaultTitleTopPadding;
        }
        this.mTitle.setPadding(this.mTitle.getPaddingLeft(), titleTopPadding, this.mTitle.getPaddingRight(), this.mTitle.getPaddingBottom());
        this.mUiElementProto = FinskyEventLog.obtainPlayStoreUiElement(516);
        FinskyEventLog.setServerLogCookie(this.mUiElementProto, serverLogsCookie);
        this.mParentNode = parentNode;
        getParentNode().childImpression(this);
    }

    public void showSecondaryButton(String buttonText, Image buttonIcon, OnClickListener actionCallback) {
        this.mButtonSeparator.setVisibility(0);
        this.mButtonSecondary.setVisibility(0);
        this.mButtonSecondary.configure(buttonText, buttonIcon, FinskyApp.get().getBitmapLoader());
        this.mButtonSecondary.setOnClickListener(actionCallback);
        syncButtonAlignment();
    }

    public void hideSecondaryAction() {
        this.mButtonSeparator.setVisibility(8);
        this.mButtonSecondary.setVisibility(8);
        syncButtonAlignment();
    }

    public void configureDismissAction(String buttonText, Image buttonIcon, OnClickListener dismissCallback) {
        this.mButtonDismiss.configure(buttonText, buttonIcon, FinskyApp.get().getBitmapLoader());
        this.mButtonDismiss.setOnClickListener(dismissCallback);
        syncButtonAlignment();
    }

    private void syncButtonAlignment() {
        if (this.mButtonSecondary.getVisibility() == 8) {
            this.mButtonDismiss.alignStart();
            return;
        }
        this.mButtonDismiss.alignCenter();
        this.mButtonSecondary.alignCenter();
    }

    public void onRecycle() {
        this.mGraphic.clearImage();
        this.mButtonDismiss.onRecycle();
        this.mButtonSecondary.onRecycle();
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
