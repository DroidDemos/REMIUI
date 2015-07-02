package com.google.android.finsky.layout;

import android.content.Context;
import android.util.AttributeSet;
import com.android.vending.R;

public class SearchResultCorrectionLayout extends IdentifiableLinearLayout {
    private boolean mFullPageReplaced;
    private SuggestionBarLayout mReplacedLine;
    private SuggestionBarLayout mSuggestionLine;

    public SearchResultCorrectionLayout(Context context) {
        super(context);
    }

    public SearchResultCorrectionLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mSuggestionLine = (SuggestionBarLayout) findViewById(R.id.suggestion_line);
        this.mReplacedLine = (SuggestionBarLayout) findViewById(R.id.replaced_line);
    }

    public void bind(String originalQuery, String suggestionQuery, boolean fullPageReplaced) {
        this.mFullPageReplaced = fullPageReplaced;
        if (this.mFullPageReplaced) {
            this.mSuggestionLine.setDisplayLine(getContext().getString(R.string.full_page_replaced_question), suggestionQuery);
            this.mReplacedLine.setDisplayLine(getContext().getString(R.string.search_instead_question), originalQuery);
            this.mSuggestionLine.setVisibility(0);
            this.mSuggestionLine.setVisibility(0);
            return;
        }
        this.mSuggestionLine.setDisplayLine(getContext().getString(R.string.suggestion_question), suggestionQuery);
        this.mSuggestionLine.setVisibility(0);
        this.mReplacedLine.setVisibility(8);
    }
}
