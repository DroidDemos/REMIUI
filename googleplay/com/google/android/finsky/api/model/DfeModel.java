package com.google.android.finsky.api.model;

import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import java.util.HashSet;

public abstract class DfeModel implements ErrorListener {
    private HashSet<ErrorListener> mErrorListeners;
    private HashSet<OnDataChangedListener> mListeners;
    private VolleyError mVolleyError;

    public abstract boolean isReady();

    public DfeModel() {
        this.mListeners = new HashSet();
        this.mErrorListeners = new HashSet();
    }

    public final void addDataChangedListener(OnDataChangedListener handler) {
        this.mListeners.add(handler);
    }

    public final void addErrorListener(ErrorListener errorListener) {
        this.mErrorListeners.add(errorListener);
    }

    public VolleyError getVolleyError() {
        return this.mVolleyError;
    }

    public boolean inErrorState() {
        return this.mVolleyError != null;
    }

    public void onErrorResponse(VolleyError error) {
        this.mVolleyError = error;
        notifyErrorOccured(error);
    }

    public final void removeDataChangedListener(OnDataChangedListener handler) {
        this.mListeners.remove(handler);
    }

    public final void removeErrorListener(ErrorListener errorListener) {
        this.mErrorListeners.remove(errorListener);
    }

    protected void clearErrors() {
        this.mVolleyError = null;
    }

    public final void unregisterAll() {
        this.mListeners.clear();
        this.mErrorListeners.clear();
    }

    protected void notifyDataSetChanged() {
        OnDataChangedListener[] listeners = (OnDataChangedListener[]) this.mListeners.toArray(new OnDataChangedListener[this.mListeners.size()]);
        for (OnDataChangedListener onDataChanged : listeners) {
            onDataChanged.onDataChanged();
        }
    }

    protected void notifyErrorOccured(VolleyError error) {
        ErrorListener[] listeners = (ErrorListener[]) this.mErrorListeners.toArray(new ErrorListener[this.mErrorListeners.size()]);
        for (ErrorListener onErrorResponse : listeners) {
            onErrorResponse.onErrorResponse(error);
        }
    }
}
