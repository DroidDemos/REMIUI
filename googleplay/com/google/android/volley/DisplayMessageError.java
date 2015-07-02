package com.google.android.volley;

import com.android.volley.VolleyError;

public abstract class DisplayMessageError extends VolleyError {
    String mDisplayErrorHtml;

    public DisplayMessageError(String displayErrorHtml) {
        this.mDisplayErrorHtml = displayErrorHtml;
    }

    public String getDisplayErrorHtml() {
        return this.mDisplayErrorHtml;
    }

    public String toString() {
        return "DisplayErrorMessage[" + this.mDisplayErrorHtml + "]";
    }
}
