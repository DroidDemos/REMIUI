package com.google.android.finsky.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.android.vending.R;

public class SongIndex extends FrameLayout {
    private ProgressBar mProgressIndicator;
    private TextView mSongIndexText;
    private int mState;
    private ImageView mStatusIndicator;

    public SongIndex(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mState = 0;
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mSongIndexText = (TextView) findViewById(R.id.song_index_text);
        this.mStatusIndicator = (ImageView) findViewById(R.id.status_indicator);
        this.mProgressIndicator = (ProgressBar) findViewById(R.id.loading_progress);
    }

    public int getBaseline() {
        if (this.mSongIndexText != null) {
            this.mSongIndexText.getBaseline();
        }
        return super.getBaseline();
    }

    public void setState(int state) {
        updateUiVisibility(this.mState, 8);
        updateUiVisibility(state, 0);
        this.mStatusIndicator.setImageResource(getStateDrawable(state));
        this.mState = state;
    }

    public void setTrackNumber(int trackNumber) {
        this.mSongIndexText.setText(String.valueOf(trackNumber));
    }

    private void updateUiVisibility(int state, int visibility) {
        switch (state) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                if (this.mSongIndexText != null) {
                    this.mSongIndexText.setVisibility(visibility);
                    return;
                }
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                this.mProgressIndicator.setVisibility(visibility);
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_required /*4*/:
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                this.mStatusIndicator.setVisibility(visibility);
                return;
            default:
                return;
        }
    }

    private int getStateDrawable(int state) {
        switch (state) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return R.drawable.ic_music_pause;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_required /*4*/:
                return R.drawable.ic_network_error;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                return R.drawable.btn_music_play;
            default:
                return R.drawable.ic_music_play;
        }
    }
}
