package com.google.android.finsky.layout;

import android.content.Context;
import android.util.AttributeSet;
import com.google.android.play.cardview.CardViewGroupDelegate;
import com.google.android.play.cardview.CardViewGroupDelegates;

public class EditorialDescriptionSection extends DetailsTextSection {
    private boolean mIsExpanded;

    public EditorialDescriptionSection(Context context) {
        this(context, null);
    }

    public EditorialDescriptionSection(Context context, AttributeSet attrs) {
        super(context, attrs);
        getCardViewGroupDelegate().initialize(this, context, attrs, 0);
    }

    public CardViewGroupDelegate getCardViewGroupDelegate() {
        return CardViewGroupDelegates.IMPL;
    }

    public void setBackgroundColor(int color) {
        getCardViewGroupDelegate().setBackgroundColor(this, color);
    }

    protected void handleClick() {
        scrollTo(0, 0);
        if (this.mUrlSpanClicked) {
            this.mUrlSpanClicked = false;
        } else if (this.mIsExpanded) {
            collapseContent();
        } else {
            expandContent();
        }
    }

    private void expandContent() {
        this.mIsExpanded = true;
        this.mBodyContainerView.setBodyMaxLines(Integer.MAX_VALUE);
        this.mFooterLabel.setVisibility(8);
    }

    private void collapseContent() {
        this.mIsExpanded = false;
        this.mBodyContainerView.setBodyMaxLines(this.mMaxCollapsedLines);
        this.mFooterLabel.setVisibility(0);
    }
}
