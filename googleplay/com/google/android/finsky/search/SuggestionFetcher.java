package com.google.android.finsky.search;

import android.content.Context;
import java.util.Locale;
import java.util.concurrent.Semaphore;

public abstract class SuggestionFetcher {
    protected final Context mContext;
    protected final String mQuery;
    protected long mStartTimeMs;

    protected interface OnCompleteListener {
        void onComplete();
    }

    protected abstract void makeRequest(OnCompleteListener onCompleteListener);

    public SuggestionFetcher(String query, Context context) {
        this.mQuery = query;
        this.mContext = context;
    }

    public void startRequestLatencyTimer() {
        this.mStartTimeMs = System.currentTimeMillis();
    }

    public void gatherSuggestions() {
        final Semaphore sem = new Semaphore(0);
        makeRequest(new OnCompleteListener() {
            public void onComplete() {
                sem.release();
            }
        });
        try {
            sem.acquire();
        } catch (InterruptedException e) {
        }
    }

    protected String getLanguage() {
        return Locale.getDefault().getLanguage();
    }

    protected String getCountry() {
        return Locale.getDefault().getCountry();
    }
}
