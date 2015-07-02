package com.google.android.finsky.layout.play;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.analytics.PlayStore.PlayStoreUiElement;
import com.google.android.finsky.layout.IdentifiableFrameLayout;
import com.google.android.play.image.FifeImageView;

public class PreferredEmailCard extends IdentifiableFrameLayout implements PlayStoreUiElementNode {
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

    public PreferredEmailCard(Context context) {
        this(context, null);
    }

    public PreferredEmailCard(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray viewAttrs = context.obtainStyledAttributes(attrs, R.styleable.WarmWelcomeCard);
        this.mSupportsDynamicTitleTopPadding = viewAttrs.getBoolean(0, true);
        viewAttrs.recycle();
        this.mDefaultTitleTopPadding = context.getResources().getDimensionPixelSize(R.dimen.warm_welcome_title_top_padding);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mTitle = (TextView) findViewById(R.id.preferred_email_title);
        this.mBody = (TextView) findViewById(R.id.preferred_email_body);
        this.mGraphicBox = findViewById(R.id.preferred_email_graphic_box);
        this.mGraphic = (FifeImageView) this.mGraphicBox.findViewById(R.id.preferred_email_graphic);
        this.mButtonDismiss = (WarmWelcomeCardButton) findViewById(R.id.button_dismiss);
        this.mButtonSecondary = (WarmWelcomeCardButton) findViewById(R.id.button_secondary);
        this.mButtonSeparator = findViewById(R.id.button_separator);
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
