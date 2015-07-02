package com.google.android.finsky.api;

import com.google.android.volley.DisplayMessageError;

public class DfeServerError extends DisplayMessageError {
    public DfeServerError(String displayMessageHtml) {
        super(displayMessageHtml);
    }
}
