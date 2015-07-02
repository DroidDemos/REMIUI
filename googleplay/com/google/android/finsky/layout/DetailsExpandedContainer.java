package com.google.android.finsky.layout;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.TextSectionStateListener;
import com.google.android.finsky.activities.TextSectionTranslatable;
import com.google.android.finsky.layout.DetailsTextSection.DetailsExtraCredits;
import com.google.android.finsky.layout.DetailsTextSection.DetailsExtraPrimary;
import com.google.android.finsky.layout.DetailsTextSection.DetailsExtraSecondary;
import com.google.android.finsky.layout.DetailsTextSection.ExpandedData;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.utils.IntMath;
import com.google.android.finsky.utils.PlayAnimationUtils;
import com.google.android.finsky.utils.PlayAnimationUtils.AnimationListenerAdapter;
import com.google.android.play.image.BitmapLoader;
import java.util.List;

public class DetailsExpandedContainer extends LinearLayout implements TextSectionTranslatable {
    private DetailsTextBlock mDetailsBodyForTranslation;
    private DetailsTextBlock mDetailsExpandedBody1;
    private DetailsTextBlock mDetailsExpandedBody2;
    private TextView mDetailsExpandedCallout;
    private ViewGroup mDetailsExpandedExtras;
    private boolean mIsShowingTranslation;
    private CharSequence mOriginalBody;
    private CharSequence mTranslatedBody;

    public DetailsExpandedContainer(Context context) {
        this(context, null);
    }

    public DetailsExpandedContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mDetailsExpandedCallout = (TextView) findViewById(R.id.details_expanded_callout);
        this.mDetailsExpandedCallout.setMovementMethod(LinkMovementMethod.getInstance());
        this.mDetailsExpandedBody1 = (DetailsTextBlock) findViewById(R.id.body_container1);
        this.mDetailsExpandedBody2 = (DetailsTextBlock) findViewById(R.id.body_container2);
        this.mDetailsExpandedExtras = (ViewGroup) findViewById(R.id.details_expanded_extras);
    }

    public void populateFromSection(int sectionId, ExpandedData expandedData, NavigationManager navigationManager, int backendId, PlayStoreUiElementNode parentNode, TextSectionStateListener textSectionStateListener) {
        DetailsTextBlock bodyForWhatsNew;
        DetailsTextBlock bodyForDescription;
        this.mOriginalBody = expandedData.body;
        this.mTranslatedBody = expandedData.translatedBody;
        this.mDetailsExpandedCallout.setText(expandedData.callout);
        this.mDetailsExpandedCallout.setGravity(expandedData.calloutGravity);
        this.mDetailsExpandedCallout.setPadding(this.mDetailsExpandedCallout.getPaddingLeft(), 0, this.mDetailsExpandedCallout.getPaddingRight(), 0);
        if (expandedData.promoteWhatsNew) {
            bodyForWhatsNew = this.mDetailsExpandedBody1;
            bodyForDescription = this.mDetailsExpandedBody2;
        } else {
            bodyForDescription = this.mDetailsExpandedBody1;
            bodyForWhatsNew = this.mDetailsExpandedBody2;
        }
        this.mDetailsBodyForTranslation = bodyForDescription;
        bodyForWhatsNew.bind(expandedData.whatsNewHeader, expandedData.whatsNew, Integer.MAX_VALUE);
        int whatsNewVerticalMargin = getResources().getDimensionPixelSize(R.dimen.details_whatsnew_vmargin);
        bodyForWhatsNew.setCorpusStyle(expandedData.backend, whatsNewVerticalMargin / 2, (whatsNewVerticalMargin * 3) / 2);
        if (!bodyForWhatsNew.hasBody()) {
            bodyForWhatsNew.setVisibility(8);
        }
        CharSequence charSequence = expandedData.bodyHeader;
        CharSequence charSequence2 = (!this.mIsShowingTranslation || TextUtils.isEmpty(this.mTranslatedBody)) ? this.mOriginalBody : this.mTranslatedBody;
        bodyForDescription.bind(charSequence, charSequence2, Integer.MAX_VALUE);
        bodyForDescription.removeCorpusStyle();
        bodyForDescription.setPadding(bodyForDescription.getPaddingLeft(), 0, bodyForDescription.getPaddingRight(), 0);
        this.mDetailsExpandedExtras.removeAllViews();
        LayoutInflater inflater = LayoutInflater.from(getContext());
        populatePrimaryCredits(expandedData, inflater);
        populatePrimaryExtras(sectionId, expandedData, navigationManager, backendId, parentNode, textSectionStateListener, inflater);
        populateSecondaryExtras(expandedData, inflater);
        populateAttributions(expandedData, inflater);
    }

    private void populatePrimaryCredits(ExpandedData expandedData, LayoutInflater inflater) {
        String extraCreditsHeader = expandedData.extraCreditsHeader;
        List<DetailsExtraCredits> extraCreditsList = expandedData.extraCreditsList;
        int extraCreditsCount = extraCreditsList.size();
        if (!TextUtils.isEmpty(extraCreditsHeader) && extraCreditsCount != 0) {
            ViewGroup header = (ViewGroup) inflater.inflate(R.layout.details_extra_credits_header, this.mDetailsExpandedExtras, false);
            ((DecoratedTextView) header.findViewById(R.id.section_header)).setText(extraCreditsHeader.toUpperCase());
            this.mDetailsExpandedExtras.addView(header);
            for (int creditsIndex = 0; creditsIndex < extraCreditsCount; creditsIndex++) {
                DetailsExpandedExtraCreditsView entryCreditsView = (DetailsExpandedExtraCreditsView) inflater.inflate(R.layout.details_text_extra_credits, this.mDetailsExpandedExtras, false);
                entryCreditsView.populate((DetailsExtraCredits) extraCreditsList.get(creditsIndex));
                this.mDetailsExpandedExtras.addView(entryCreditsView);
            }
        }
    }

    private void populatePrimaryExtras(int sectionId, ExpandedData expandedData, NavigationManager navigationManager, int backendId, PlayStoreUiElementNode parentNode, TextSectionStateListener textSectionStateListener, LayoutInflater inflater) {
        BitmapLoader bitmapLoader = FinskyApp.get().getBitmapLoader();
        List<DetailsExtraPrimary> extraPrimaryList = expandedData.extraPrimaryList;
        int extraPrimaryCount = extraPrimaryList.size();
        int itemsInRow = getResources().getInteger(R.integer.details_extra_primary_items_per_row);
        int rowCount = IntMath.ceil(extraPrimaryCount, itemsInRow);
        int rowIndex = 0;
        while (rowIndex < rowCount) {
            View row = (SeparatorLinearLayout) inflater.inflate(R.layout.details_text_extra_row, this.mDetailsExpandedExtras, false);
            for (int colIndex = 0; colIndex < itemsInRow; colIndex++) {
                int extraIndex = (itemsInRow * rowIndex) + colIndex;
                DetailsExpandedExtraPrimaryView entryPrimaryView = (DetailsExpandedExtraPrimaryView) inflater.inflate(R.layout.details_text_extra_primary, row, false);
                if (extraIndex >= extraPrimaryCount) {
                    entryPrimaryView.setVisibility(4);
                } else {
                    OnClickListener primaryClickListener;
                    final DetailsExtraPrimary extraPrimary = (DetailsExtraPrimary) extraPrimaryList.get(extraIndex);
                    if (TextUtils.isEmpty(extraPrimary.url)) {
                        primaryClickListener = null;
                    } else {
                        final TextSectionStateListener textSectionStateListener2 = textSectionStateListener;
                        final int i = sectionId;
                        final NavigationManager navigationManager2 = navigationManager;
                        final int i2 = backendId;
                        final PlayStoreUiElementNode playStoreUiElementNode = parentNode;
                        Object primaryClickListener2 = new OnClickListener() {
                            public void onClick(View v) {
                                if (textSectionStateListener2 != null) {
                                    textSectionStateListener2.onSectionCollapsed(i);
                                }
                                navigationManager2.goBrowse(extraPrimary.url, extraPrimary.title, i2, FinskyApp.get().getToc(), playStoreUiElementNode);
                            }
                        };
                    }
                    entryPrimaryView.populate(extraPrimary, bitmapLoader, primaryClickListener);
                }
                boolean isFirstRow = rowIndex == 0;
                boolean isLastRow = rowIndex == rowCount + -1;
                if (!isFirstRow) {
                    row.hideSeparator();
                }
                row.setPadding(row.getPaddingLeft(), isFirstRow ? row.getPaddingTop() : 0, row.getPaddingRight(), isLastRow ? row.getPaddingBottom() : 0);
                row.addView(entryPrimaryView);
            }
            this.mDetailsExpandedExtras.addView(row);
            rowIndex++;
        }
    }

    private void populateSecondaryExtras(ExpandedData expandedData, LayoutInflater inflater) {
        List<DetailsExtraSecondary> extraSecondaryList = expandedData.extraSecondaryList;
        int extraSecondaryCount = extraSecondaryList.size();
        int itemsInRow = getResources().getInteger(R.integer.details_extra_secondary_items_per_row);
        int rowCount = IntMath.ceil(extraSecondaryCount, itemsInRow);
        int rowIndex = 0;
        while (rowIndex < rowCount) {
            SeparatorLinearLayout row = (SeparatorLinearLayout) inflater.inflate(R.layout.details_text_extra_row, this.mDetailsExpandedExtras, false);
            for (int colIndex = 0; colIndex < itemsInRow; colIndex++) {
                int extraIndex = (itemsInRow * rowIndex) + colIndex;
                DetailsExpandedExtraSecondaryView entrySecondaryView = (DetailsExpandedExtraSecondaryView) inflater.inflate(R.layout.details_text_extra_secondary, row, false);
                if (extraIndex >= extraSecondaryCount) {
                    entrySecondaryView.setVisibility(4);
                } else {
                    entrySecondaryView.populate((DetailsExtraSecondary) extraSecondaryList.get(extraIndex));
                }
                row.addView(entrySecondaryView);
            }
            boolean isFirstRow = rowIndex == 0;
            boolean isLastRow = rowIndex == rowCount + -1;
            if (!isFirstRow) {
                row.hideSeparator();
            }
            row.setPadding(row.getPaddingLeft(), isFirstRow ? row.getPaddingTop() : 0, row.getPaddingRight(), isLastRow ? row.getPaddingBottom() : 0);
            this.mDetailsExpandedExtras.addView(row);
            rowIndex++;
        }
    }

    private void populateAttributions(ExpandedData expandedData, LayoutInflater inflater) {
        String attributions = expandedData.extraAttributions;
        if (!TextUtils.isEmpty(attributions)) {
            SeparatorLinearLayout row = (SeparatorLinearLayout) inflater.inflate(R.layout.details_text_extra_row, this.mDetailsExpandedExtras, false);
            TextView attributionsView = (TextView) inflater.inflate(R.layout.details_text_extra_attributions, row, false);
            attributionsView.setVisibility(0);
            attributionsView.setText(Html.fromHtml(attributions));
            attributionsView.setMovementMethod(LinkMovementMethod.getInstance());
            row.addView(attributionsView);
            this.mDetailsExpandedExtras.addView(row);
        }
    }

    public void hideAllViews() {
        this.mDetailsExpandedCallout.setVisibility(8);
        this.mDetailsExpandedBody1.setVisibility(8);
        this.mDetailsExpandedBody2.setVisibility(8);
        this.mDetailsExpandedExtras.setVisibility(8);
    }

    public void resetContent() {
        this.mDetailsExpandedCallout.setText(null);
        this.mDetailsExpandedBody1.resetContent();
        this.mDetailsExpandedBody2.resetContent();
        this.mDetailsExpandedExtras.removeAllViews();
    }

    private boolean hasExtras() {
        return this.mDetailsExpandedExtras.getChildCount() > 0;
    }

    private boolean shouldShowCallout() {
        return !TextUtils.isEmpty(this.mDetailsExpandedCallout.getText());
    }

    private boolean shouldShowBody1() {
        return this.mDetailsExpandedBody1.hasBody();
    }

    private boolean shouldShowBody2() {
        return this.mDetailsExpandedBody2.hasBody();
    }

    public void setTopPaddingOnTopView(int topPadding) {
        View topView = shouldShowCallout() ? this.mDetailsExpandedCallout : shouldShowBody1() ? this.mDetailsExpandedBody1 : this.mDetailsExpandedBody2;
        topView.setPadding(topView.getPaddingLeft(), topPadding, topView.getPaddingRight(), 0);
    }

    public void fadeInContentPreIcs() {
        Context context = getContext();
        Animation fadeInExpandedCallout = PlayAnimationUtils.getFadeInAnimation(context, 150, 150, new AnimationListenerAdapter() {
            public void onAnimationStart(Animation animation) {
                DetailsExpandedContainer.this.mDetailsExpandedCallout.setVisibility(0);
            }
        });
        Animation fadeInExpandedBody1 = PlayAnimationUtils.getFadeInAnimation(context, 150, 150, new AnimationListenerAdapter() {
            public void onAnimationStart(Animation animation) {
                DetailsExpandedContainer.this.mDetailsExpandedBody1.setVisibility(0);
            }
        });
        Animation fadeInExpandedBody2 = PlayAnimationUtils.getFadeInAnimation(context, 150, 150, new AnimationListenerAdapter() {
            public void onAnimationStart(Animation animation) {
                DetailsExpandedContainer.this.mDetailsExpandedBody2.setVisibility(0);
            }
        });
        Animation fadeInExpandedExtras = PlayAnimationUtils.getFadeInAnimation(context, 150, 150, new AnimationListenerAdapter() {
            public void onAnimationStart(Animation animation) {
                DetailsExpandedContainer.this.mDetailsExpandedExtras.setVisibility(0);
            }
        });
        if (shouldShowCallout()) {
            this.mDetailsExpandedCallout.startAnimation(fadeInExpandedCallout);
        }
        if (shouldShowBody1()) {
            this.mDetailsExpandedBody1.startAnimation(fadeInExpandedBody1);
        }
        if (shouldShowBody2()) {
            this.mDetailsExpandedBody2.startAnimation(fadeInExpandedBody2);
        }
        if (hasExtras()) {
            this.mDetailsExpandedExtras.startAnimation(fadeInExpandedExtras);
        }
    }

    public void addFadeInAnimatorsIcs(List<Animator> animators) {
        if (shouldShowCallout()) {
            animators.add(PlayAnimationUtils.getFadeAnimator(this.mDetailsExpandedCallout, 0.0f, 1.0f, 150, 150, new AnimatorListenerAdapter() {
                public void onAnimationStart(Animator animation) {
                    DetailsExpandedContainer.this.mDetailsExpandedCallout.setVisibility(0);
                }
            }));
        }
        if (shouldShowBody1()) {
            animators.add(PlayAnimationUtils.getFadeAnimator(this.mDetailsExpandedBody1, 0.0f, 1.0f, 150, 150, new AnimatorListenerAdapter() {
                public void onAnimationStart(Animator animation) {
                    DetailsExpandedContainer.this.mDetailsExpandedBody1.setVisibility(0);
                }
            }));
        }
        if (shouldShowBody2()) {
            animators.add(PlayAnimationUtils.getFadeAnimator(this.mDetailsExpandedBody2, 0.0f, 1.0f, 150, 150, new AnimatorListenerAdapter() {
                public void onAnimationStart(Animator animation) {
                    DetailsExpandedContainer.this.mDetailsExpandedBody2.setVisibility(0);
                }
            }));
        }
        if (hasExtras()) {
            animators.add(PlayAnimationUtils.getFadeAnimator(this.mDetailsExpandedExtras, 0.0f, 1.0f, 150, 150, new AnimatorListenerAdapter() {
                public void onAnimationStart(Animator animation) {
                    DetailsExpandedContainer.this.mDetailsExpandedExtras.setVisibility(0);
                }
            }));
        }
    }

    public void fadeOutContentPreIcs() {
        Context context = getContext();
        if (this.mDetailsExpandedCallout.getVisibility() == 0) {
            this.mDetailsExpandedCallout.startAnimation(PlayAnimationUtils.getFadeOutAnimation(context, 75, new AnimationListenerAdapter() {
                public void onAnimationEnd(Animation animation) {
                    DetailsExpandedContainer.this.mDetailsExpandedCallout.setVisibility(8);
                }
            }));
        }
        if (shouldShowBody1()) {
            this.mDetailsExpandedBody1.startAnimation(PlayAnimationUtils.getFadeOutAnimation(context, 75, new AnimationListenerAdapter() {
                public void onAnimationEnd(Animation animation) {
                    DetailsExpandedContainer.this.mDetailsExpandedBody1.setVisibility(8);
                }
            }));
        }
        if (shouldShowBody2()) {
            this.mDetailsExpandedBody2.startAnimation(PlayAnimationUtils.getFadeOutAnimation(context, 75, new AnimationListenerAdapter() {
                public void onAnimationEnd(Animation animation) {
                    DetailsExpandedContainer.this.mDetailsExpandedBody2.setVisibility(8);
                }
            }));
        }
        if (hasExtras()) {
            this.mDetailsExpandedExtras.startAnimation(PlayAnimationUtils.getFadeOutAnimation(context, 75, new AnimationListenerAdapter() {
                public void onAnimationEnd(Animation animation) {
                    DetailsExpandedContainer.this.mDetailsExpandedExtras.setVisibility(8);
                }
            }));
        }
    }

    public void addFadeOutAnimatorsIcs(List<Animator> animators) {
        if (this.mDetailsExpandedCallout.getVisibility() == 0) {
            animators.add(PlayAnimationUtils.getFadeAnimator(this.mDetailsExpandedCallout, 1.0f, 0.0f, 0, 75, null));
        }
        if (shouldShowBody1()) {
            animators.add(PlayAnimationUtils.getFadeAnimator(this.mDetailsExpandedBody1, 1.0f, 0.0f, 0, 75, null));
        }
        if (shouldShowBody2()) {
            animators.add(PlayAnimationUtils.getFadeAnimator(this.mDetailsExpandedBody2, 1.0f, 0.0f, 0, 75, null));
        }
        if (hasExtras()) {
            animators.add(PlayAnimationUtils.getFadeAnimator(this.mDetailsExpandedExtras, 1.0f, 0.0f, 0, 75, null));
        }
    }

    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("expanded_container.parent_instance_state", super.onSaveInstanceState());
        bundle.putBoolean("expanded_container.translation_state", this.mIsShowingTranslation);
        return bundle;
    }

    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            this.mIsShowingTranslation = bundle.getBoolean("expanded_container.translation_state");
            super.onRestoreInstanceState(bundle.getParcelable("expanded_container.parent_instance_state"));
            return;
        }
        super.onRestoreInstanceState(state);
    }

    public boolean hasTranslation() {
        return !TextUtils.isEmpty(this.mTranslatedBody);
    }

    public boolean isShowingTranslation() {
        return this.mIsShowingTranslation;
    }

    public void toggleTranslation() {
        this.mIsShowingTranslation = !this.mIsShowingTranslation;
        this.mDetailsBodyForTranslation.setBody(this.mIsShowingTranslation ? this.mTranslatedBody : this.mOriginalBody);
    }
}
