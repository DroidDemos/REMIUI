package com.google.android.finsky.fragments;

import android.os.Bundle;
import com.google.android.finsky.api.model.DfeToc;

public abstract class UrlBasedPageFragment extends PageFragment {
    protected String mUrl;

    protected void setDfeTocAndUrl(DfeToc toc, String url) {
        super.setDfeToc(toc);
        setArgument("finsky.UrlBasedPageFragment.url", url);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mUrl = getArguments().getString("finsky.UrlBasedPageFragment.url");
    }

    public String getUrl() {
        return this.mUrl;
    }
}
