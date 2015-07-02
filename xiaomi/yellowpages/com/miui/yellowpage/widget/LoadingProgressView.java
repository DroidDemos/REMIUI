package com.miui.yellowpage.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.miui.yellowpage.R;
import com.miui.yellowpage.a.e;
import com.miui.yellowpage.a.g;
import com.miui.yellowpage.request.BaseResult.State;

public class LoadingProgressView extends LinearLayout implements g {
    private Button hO;
    private int lF;
    private e lG;
    private ProgressBar mProgressBar;
    private CharSequence mText;
    private TextView mTextView;

    public LoadingProgressView(Context context) {
        this(context, null);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        return true;
    }

    public LoadingProgressView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(R.layout.loading_progress, this, true);
        this.mProgressBar = (ProgressBar) findViewById(R.id.progress);
        this.mTextView = (TextView) findViewById(R.id.text);
        this.hO = (Button) findViewById(R.id.button);
    }

    private void l(boolean z) {
        if (z) {
            getLayoutParams().height = -2;
            setBackgroundResource(R.drawable.loading_view_bg);
            return;
        }
        getLayoutParams().height = -1;
        setBackground(null);
    }

    public void onStartLoading(boolean z) {
        l(z);
        this.mProgressBar.setVisibility(0);
        this.mTextView.setVisibility(8);
        this.hO.setVisibility(8);
        f(this);
    }

    public void onStopLoading(boolean z) {
        l(z);
        if (z) {
            g(this);
            return;
        }
        f(this);
        this.mProgressBar.setVisibility(8);
        if (this.lF != 0) {
            this.mTextView.setVisibility(0);
            this.mTextView.setText(this.lF);
        } else if (!TextUtils.isEmpty(this.mText)) {
            this.mTextView.setVisibility(0);
            this.mTextView.setText(this.mText);
        }
        this.mTextView.setCompoundDrawablesWithIntrinsicBounds(null, this.mContext.getResources().getDrawable(R.drawable.list_empty), null, null);
        this.hO.setVisibility(8);
    }

    public void a(boolean z, boolean z2, e eVar) {
        this.lG = eVar;
        l(z);
        if (z2) {
            setVisibility(0);
            this.mProgressBar.setVisibility(0);
            this.mTextView.setVisibility(8);
            this.hO.setVisibility(8);
        } else if (z) {
            setVisibility(8);
        } else {
            setVisibility(0);
            this.mProgressBar.setVisibility(8);
            this.hO.setVisibility(8);
        }
    }

    private void f(View view) {
        if (view != null && view.getVisibility() == 8) {
            view.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.appear));
            view.setVisibility(0);
        }
    }

    private void g(View view) {
        if (view != null && view.getVisibility() == 0) {
            if (view.isShown()) {
                view.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.disappear));
            }
            view.setVisibility(8);
        }
    }

    public void t(int i) {
        this.lF = i;
    }

    public void a(boolean z, State state) {
        a(z, state, null);
    }

    public void a(boolean z, State state, String str) {
        if (TextUtils.isEmpty(str)) {
            str = getContext().getResources().getString(state.gL());
        }
        l(z);
        if (z) {
            g(this);
            Toast.makeText(getContext(), str, 0).show();
            return;
        }
        f(this);
        this.mProgressBar.setVisibility(8);
        this.mTextView.setVisibility(0);
        this.mTextView.setText(str);
        this.hO.setVisibility(0);
        if (state == State.NETWORK_ERROR) {
            this.mTextView.setCompoundDrawablesWithIntrinsicBounds(null, this.mContext.getResources().getDrawable(R.drawable.network_error), null, null);
            this.hO.setText(R.string.check_network);
        } else {
            this.mTextView.setCompoundDrawablesWithIntrinsicBounds(null, this.mContext.getResources().getDrawable(R.drawable.list_empty), null, null);
            this.hO.setText(R.string.try_again);
            if (this.lG == null) {
                this.hO.setVisibility(8);
            }
        }
        this.hO.setOnClickListener(new c(this, state));
    }

    public void setIndeterminate(boolean z) {
        if (this.mProgressBar.isIndeterminate() != z) {
            LayoutParams layoutParams = (LayoutParams) this.mProgressBar.getLayoutParams();
            if (z) {
                layoutParams.width = -2;
                layoutParams.height = -2;
                layoutParams.gravity = 17;
            } else {
                layoutParams.width = -1;
                layoutParams.height = (int) TypedValue.applyDimension(1, 1.0f, getResources().getDisplayMetrics());
                layoutParams.gravity = 48;
            }
            this.mProgressBar.setLayoutParams(layoutParams);
            this.mProgressBar.setIndeterminate(z);
        }
    }

    public void setProgress(int i) {
        this.mProgressBar.setProgress(i);
    }

    public int getProgress() {
        return this.mProgressBar.getProgress();
    }
}
