package com.google.android.play.search;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.android.play.R;
import com.google.android.play.image.BitmapLoader;
import com.google.android.play.image.FifeImageView;
import com.google.android.play.image.FifeImageView.OnLoadedListener;

public class PlaySearchOneSuggestion extends RelativeLayout {
    private Drawable mDefaultIconDrawable;
    private View mDivider;
    private FifeImageView mIcon;
    private TextView mSuggestText;

    public PlaySearchOneSuggestion(Context context) {
        this(context, null);
    }

    public PlaySearchOneSuggestion(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PlaySearchOneSuggestion(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        this.mIcon = (FifeImageView) findViewById(R.id.icon);
        this.mSuggestText = (TextView) findViewById(R.id.suggest_text);
        this.mDivider = findViewById(R.id.suggestion_divider);
        this.mDefaultIconDrawable = getContext().getResources().getDrawable(R.drawable.ic_search);
    }

    public void bindSuggestion(PlaySearchSuggestionModel model, boolean showDivider, BitmapLoader bitmapLoader) {
        this.mSuggestText.setText(model.displayText);
        Drawable defaultIcon = model.defaultIconDrawable != null ? model.defaultIconDrawable : this.mDefaultIconDrawable;
        this.mIcon.clearImage();
        this.mIcon.setScaleType(ScaleType.CENTER_INSIDE);
        this.mIcon.setDefaultDrawable(defaultIcon);
        if (model.iconUrl != null) {
            this.mIcon.setOnLoadedListener(new OnLoadedListener() {
                public void onLoaded(FifeImageView imageView, Bitmap bitmap) {
                    PlaySearchOneSuggestion.this.mIcon.setScaleType(ScaleType.FIT_CENTER);
                }

                public void onLoadedAndFadedIn(FifeImageView imageView) {
                }
            });
            this.mIcon.setImage(model.iconUrl, model.iconUrlSupportsFifeOptions, bitmapLoader);
        } else {
            this.mIcon.setOnLoadedListener(null);
            this.mIcon.setImageDrawable(defaultIcon);
        }
        this.mDivider.setVisibility(showDivider ? 0 : 8);
    }
}
