package com.google.android.finsky.detailspage;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.layout.DecoratedTextView;
import com.google.android.finsky.layout.DetailsTextBlock;
import com.google.android.finsky.layout.DetailsTextIconContainer;
import com.google.android.finsky.protos.Common.Image;
import com.google.android.finsky.protos.DocAnnotations.Badge;
import com.google.android.finsky.utils.BadgeUtils;
import com.google.android.finsky.utils.UiUtils;
import com.google.android.play.image.BitmapLoader;
import com.google.android.play.layout.PlayTextView;
import com.google.android.play.utils.PlayCorpusUtils;
import com.google.android.play.utils.UrlSpanUtils;
import com.google.android.play.utils.UrlSpanUtils.Listener;
import java.util.List;

public class TextModuleLayout extends LinearLayout {
    private DetailsTextBlock mBodyContainerView;
    private PlayTextView mCalloutView;
    private final CharSequence mExpandedWhatsNewHeader;
    private TextView mFooterLabel;
    private DetailsTextIconContainer mIconContainer;
    private Listener mLinkClickListener;
    private int mMaxCollapsedLines;
    private OnClickListener mReadMoreClickListener;
    private View mSpacerView;
    private final int mTopDeveloperColor;
    private DecoratedTextView mTopDeveloperLabel;
    private boolean mUrlSpanClicked;
    private final int mWhatsNewVerticalMargin;

    public TextModuleLayout(Context context) {
        this(context, null);
    }

    public TextModuleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        Resources res = context.getResources();
        this.mMaxCollapsedLines = res.getInteger(R.integer.details_text_collapsed_lines);
        this.mTopDeveloperColor = res.getColor(R.color.top_developer);
        this.mWhatsNewVerticalMargin = res.getDimensionPixelSize(R.dimen.details_whatsnew_vmargin);
        this.mExpandedWhatsNewHeader = res.getString(R.string.details_whats_new).toUpperCase();
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mCalloutView = (PlayTextView) findViewById(R.id.callout);
        this.mCalloutView.setMovementMethod(LinkMovementMethod.getInstance());
        this.mSpacerView = findViewById(R.id.spacer);
        this.mBodyContainerView = (DetailsTextBlock) findViewById(R.id.body_container);
        this.mIconContainer = (DetailsTextIconContainer) findViewById(R.id.icon_container);
        this.mTopDeveloperLabel = (DecoratedTextView) findViewById(R.id.top_developer_label);
        this.mFooterLabel = (TextView) findViewById(R.id.footer_message);
        this.mFooterLabel.setText(getContext().getString(R.string.read_more).toUpperCase());
    }

    public void bind(int backend, int docType, CharSequence callout, int calloutGravity, boolean restrictMaxCalloutLines, String bodyHeader, CharSequence body, CharSequence translatedBody, CharSequence whatsNew, boolean hideWhatsNewInCollapsed, Badge creatorBadge, List<Pair<Image, String>> icons, Integer backgroundFillColor, OnClickListener readMoreClickListener, Listener linkClickListener, BitmapLoader bitmapLoader) {
        this.mReadMoreClickListener = readMoreClickListener;
        this.mLinkClickListener = linkClickListener;
        selfishifyUrlSpans(callout);
        selfishifyUrlSpans(body);
        selfishifyUrlSpans(translatedBody);
        selfishifyUrlSpans(whatsNew);
        Context context = getContext();
        this.mIconContainer.populate(icons, bitmapLoader);
        if (this.mFooterLabel != null) {
            this.mFooterLabel.setTextColor(PlayCorpusUtils.getPrimaryTextColor(context, backend));
        }
        boolean isApp = docType == 1;
        boolean hasCallout = !TextUtils.isEmpty(callout);
        if (hasCallout) {
            this.mCalloutView.setVisibility(0);
            this.mCalloutView.setText(callout);
            this.mCalloutView.setMaxLines(restrictMaxCalloutLines ? this.mMaxCollapsedLines : Integer.MAX_VALUE);
            this.mCalloutView.setGravity(calloutGravity);
        } else {
            this.mCalloutView.setVisibility(8);
        }
        this.mSpacerView.setVisibility(8);
        if (!hideWhatsNewInCollapsed && !TextUtils.isEmpty(whatsNew)) {
            this.mBodyContainerView.bind(this.mExpandedWhatsNewHeader, whatsNew, this.mMaxCollapsedLines);
            this.mBodyContainerView.setCorpusStyle(backend, this.mWhatsNewVerticalMargin, this.mWhatsNewVerticalMargin);
        } else if (isApp && hasCallout) {
            this.mBodyContainerView.setVisibility(8);
        } else {
            this.mBodyContainerView.bind(bodyHeader, body, this.mMaxCollapsedLines);
            this.mBodyContainerView.removeCorpusStyle();
            if (!hasCallout && TextUtils.isEmpty(bodyHeader)) {
                this.mSpacerView.setVisibility(0);
            }
        }
        if (creatorBadge != null) {
            this.mTopDeveloperLabel.setText(creatorBadge.title);
            this.mTopDeveloperLabel.setTextColor(this.mTopDeveloperColor);
            BadgeUtils.configureBadge(creatorBadge, bitmapLoader, this.mTopDeveloperLabel);
            this.mTopDeveloperLabel.setVisibility(0);
        } else {
            this.mTopDeveloperLabel.setVisibility(8);
        }
        if (backgroundFillColor != null) {
            int textColor = getResources().getColor(UiUtils.isColorBright(backgroundFillColor.intValue()) ? R.color.play_fg_primary : R.color.white);
            setBackgroundColor(backgroundFillColor.intValue());
            this.mBodyContainerView.setEditorialStyle(backgroundFillColor.intValue(), textColor);
            this.mFooterLabel.setTextColor(textColor);
        }
        configureContentClicks();
        setVisibility(0);
    }

    private void configureContentClicks() {
        OnClickListener clickListener = new OnClickListener() {
            public void onClick(View view) {
                TextModuleLayout.this.handleClick(view);
            }
        };
        setOnClickListener(clickListener);
        this.mBodyContainerView.setBodyClickListener(clickListener);
        this.mCalloutView.setOnClickListener(clickListener);
    }

    private void handleClick(View view) {
        if (this.mUrlSpanClicked) {
            this.mUrlSpanClicked = false;
        } else if (this.mReadMoreClickListener != null) {
            this.mReadMoreClickListener.onClick(view);
        }
    }

    private void selfishifyUrlSpans(CharSequence string) {
        if (!TextUtils.isEmpty(string)) {
            UrlSpanUtils.selfishifyUrlSpans(string, new Listener() {
                public void onClick(View view, String url) {
                    TextModuleLayout.this.mUrlSpanClicked = true;
                    if (TextModuleLayout.this.mLinkClickListener != null) {
                        TextModuleLayout.this.mLinkClickListener.onClick(view, url);
                    }
                }
            });
        }
    }
}
