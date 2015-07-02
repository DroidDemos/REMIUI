package com.google.android.libraries.happiness;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebView;
import com.android.vending.R;

public class HatsSurveyDialog extends DialogFragment {
    private int mLayoutId;
    private Runnable mOnCancelAction;
    private int mResourceId;
    private WebView mWebView;
    private ViewGroup mWebViewContainer;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        int i = (args == null || !args.containsKey("HatsSurveyDialog.HATS_SURVEY_LAYOUT")) ? R.layout.survey_dialog : args.getInt("HatsSurveyDialog.HATS_SURVEY_LAYOUT");
        this.mLayoutId = i;
        i = (args == null || !args.containsKey("HatsSurveyDialog.HATS_SURVEY_LAYOUT_ID")) ? R.id.survey_container : args.getInt("HatsSurveyDialog.HATS_SURVEY_LAYOUT_ID");
        this.mResourceId = i;
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("HatsSurveyDialog.HATS_SURVEY_LAYOUT", this.mLayoutId);
        outState.putInt("HatsSurveyDialog.HATS_SURVEY_LAYOUT_ID", this.mResourceId);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setCancelable(true);
        View view = inflater.inflate(this.mLayoutId, container);
        this.mWebViewContainer = (ViewGroup) view.findViewById(this.mResourceId);
        bindWebview();
        return view;
    }

    public void onCancel(DialogInterface unused) {
        if (this.mOnCancelAction != null) {
            this.mOnCancelAction.run();
        }
    }

    public void setOnCancelAction(Runnable action) {
        this.mOnCancelAction = action;
    }

    public void setWebView(WebView webView) {
        this.mWebView = webView;
        bindWebview();
    }

    private void bindWebview() {
        if (this.mWebView != null && this.mWebViewContainer != null) {
            ViewGroup container = (ViewGroup) this.mWebView.getParent();
            if (container != null) {
                container.removeView(this.mWebView);
            }
            this.mWebViewContainer.addView(this.mWebView, 0, new LayoutParams(-1, -1));
        }
    }
}
