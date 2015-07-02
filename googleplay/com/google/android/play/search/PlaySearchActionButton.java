package com.google.android.play.search;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import com.google.android.play.R;

public class PlaySearchActionButton extends ImageView implements PlaySearchListener {
    private final boolean mCanPerformVoiceSearch;
    private Drawable mClearDrawable;
    private PlaySearchController mController;
    private int mCurrentMode;
    private Drawable mMicDrawable;

    public PlaySearchActionButton(Context context) {
        this(context, null);
    }

    public PlaySearchActionButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PlaySearchActionButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mCurrentMode = 0;
        this.mClearDrawable = context.getResources().getDrawable(R.drawable.play_ic_clear);
        this.mMicDrawable = context.getResources().getDrawable(R.drawable.ic_mic_dark);
        this.mCanPerformVoiceSearch = PlaySearchVoiceController.canPerformVoiceSearch(context);
        setMode(2);
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (PlaySearchActionButton.this.mController != null) {
                    if (PlaySearchActionButton.this.mCurrentMode == 1) {
                        PlaySearchActionButton.this.mController.setQuery("");
                    } else if (PlaySearchActionButton.this.mCurrentMode == 2) {
                        PlaySearchActionButton.this.mController.setMode(3);
                    }
                }
            }
        });
    }

    public void setPlaySearchController(PlaySearchController playSearchController) {
        if (this.mController != null) {
            this.mController.removePlaySearchListener(this);
        }
        this.mController = playSearchController;
        this.mController.addPlaySearchListener(this);
    }

    public void onModeChanged(int searchMode) {
        handleChange();
    }

    public void onQueryChanged(String query, boolean canUpdateSuggestions) {
        handleChange();
    }

    public void onSuggestionClicked(PlaySearchSuggestionModel model) {
    }

    public void onSearch(String query) {
    }

    private void handleChange() {
        int i = 2;
        if (this.mController != null) {
            int searchMode = this.mController.getMode();
            String query = this.mController.getQuery();
            if (searchMode == 1 || TextUtils.isEmpty(query)) {
                if (!this.mCanPerformVoiceSearch) {
                    i = 0;
                }
                setMode(i);
            } else if (searchMode == 2 || searchMode == 3) {
                setMode(1);
            } else {
                setMode(0);
            }
        }
    }

    private void setMode(int mode) {
        if (this.mCurrentMode != mode) {
            this.mCurrentMode = mode;
            if (mode == 0) {
                setVisibility(8);
                return;
            }
            Drawable drawable = null;
            int accessibilityString = -1;
            if (mode == 1) {
                drawable = this.mClearDrawable;
                accessibilityString = R.string.play_accessibility_search_plate_clear;
            } else if (mode == 2) {
                drawable = this.mMicDrawable;
                accessibilityString = R.string.play_accessibility_search_plate_voice_search_button;
            }
            if (drawable != null) {
                setImageDrawable(drawable);
                setContentDescription(getContext().getResources().getString(accessibilityString));
                setVisibility(0);
            }
        }
    }
}
