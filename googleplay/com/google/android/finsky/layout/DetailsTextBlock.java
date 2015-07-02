package com.google.android.finsky.layout;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.view.View.OnClickListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.utils.CorpusResourceUtils;
import com.google.android.play.layout.PlayTextView;

public class DetailsTextBlock extends LinearLayout {
    private TextView mBodyHeaderView;
    private PlayTextView mBodyView;
    private final int mCorpusXMargin;
    private final int mRegularXMargin;

    public DetailsTextBlock(Context context) {
        this(context, null);
    }

    public DetailsTextBlock(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray viewAttrs = context.obtainStyledAttributes(attrs, R.styleable.DetailsTextBlock);
        this.mRegularXMargin = viewAttrs.getDimensionPixelSize(0, 0);
        this.mCorpusXMargin = viewAttrs.getDimensionPixelSize(1, 0);
        viewAttrs.recycle();
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mBodyHeaderView = (TextView) findViewById(R.id.body_header);
        this.mBodyView = (PlayTextView) findViewById(R.id.body);
        this.mBodyView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public void bind(CharSequence header, CharSequence body, int bodyMaxLines) {
        if (TextUtils.isEmpty(header)) {
            this.mBodyHeaderView.setVisibility(8);
        } else {
            this.mBodyHeaderView.setText(header);
            this.mBodyHeaderView.setVisibility(0);
        }
        setBody(body);
        setBodyMaxLines(bodyMaxLines);
        setVisibility(0);
    }

    public void setBody(CharSequence body) {
        if (TextUtils.isEmpty(body)) {
            this.mBodyView.setVisibility(8);
            return;
        }
        this.mBodyView.setText(body);
        this.mBodyView.setVisibility(0);
    }

    public void setBodyMaxLines(int bodyMaxLines) {
        this.mBodyView.setMaxLines(bodyMaxLines);
    }

    public void resetContent() {
        this.mBodyHeaderView.setText(null);
        this.mBodyView.setText(null);
    }

    public void setEditorialStyle(int backgroundFillColor, int textColor) {
        this.mBodyView.setLastLineOverdrawColor(backgroundFillColor);
        this.mBodyView.setTextColor(textColor);
        this.mBodyView.setLinkTextColor(textColor);
    }

    public void setCorpusStyle(int backend, int topMargin, int bottomMargin) {
        Context context = getContext();
        setBackgroundResource(CorpusResourceUtils.getWhatsNewBackgroundDrawable(backend));
        this.mBodyView.setLastLineOverdrawColor(CorpusResourceUtils.getWhatsNewFillColor(context, backend));
        MarginLayoutParams layoutParams = (MarginLayoutParams) getLayoutParams();
        layoutParams.topMargin = topMargin;
        layoutParams.bottomMargin = bottomMargin;
        layoutParams.leftMargin = this.mCorpusXMargin;
        layoutParams.rightMargin = this.mCorpusXMargin;
        setLayoutParams(layoutParams);
    }

    public void removeCorpusStyle() {
        setBackgroundResource(0);
        this.mBodyView.setLastLineOverdrawColor(-1);
        MarginLayoutParams layoutParams = (MarginLayoutParams) getLayoutParams();
        layoutParams.topMargin = 0;
        layoutParams.bottomMargin = 0;
        layoutParams.leftMargin = this.mRegularXMargin;
        layoutParams.rightMargin = this.mRegularXMargin;
        setLayoutParams(layoutParams);
    }

    public void setBodyClickListener(OnClickListener bodyClickListener) {
        this.mBodyView.setOnClickListener(bodyClickListener);
    }

    public boolean hasBody() {
        return this.mBodyView.getVisibility() == 0 && !TextUtils.isEmpty(this.mBodyView.getText());
    }
}
