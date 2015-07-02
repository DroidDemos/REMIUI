package com.google.android.finsky.layout;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.analytics.PlayStore.PlayStoreUiElement;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.protos.Browse.BrowseLink;
import com.google.android.play.layout.ForegroundRelativeLayout;

public class CategoryRow extends ForegroundRelativeLayout implements PlayStoreUiElementNode {
    private ImageView mCategoryIcon;
    private TextView mCategoryTitle;
    private PlayStoreUiElementNode mParentNode;
    private PlayStoreUiElement mUiElementProto;

    public CategoryRow(Context context) {
        super(context);
        this.mUiElementProto = FinskyEventLog.obtainPlayStoreUiElement(100);
    }

    public CategoryRow(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mUiElementProto = FinskyEventLog.obtainPlayStoreUiElement(100);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mCategoryTitle = (TextView) findViewById(R.id.category_item_title);
        this.mCategoryIcon = (ImageView) findViewById(R.id.category_item_image);
    }

    public void bind(BrowseLink category, ColorStateList titleColorStateList, PlayStoreUiElementNode parent) {
        this.mCategoryTitle.setText(category.name);
        this.mCategoryTitle.setTextColor(titleColorStateList);
        this.mCategoryTitle.setContentDescription(category.name);
        this.mParentNode = parent;
        FinskyEventLog.setServerLogCookie(this.mUiElementProto, category.serverLogsCookie);
        this.mParentNode.childImpression(this);
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
