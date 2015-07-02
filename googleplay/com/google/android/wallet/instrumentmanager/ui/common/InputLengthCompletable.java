package com.google.android.wallet.instrumentmanager.ui.common;

public class InputLengthCompletable implements Completable {
    protected final FormEditText mFormEditText;
    private final int mLength;

    public InputLengthCompletable(FormEditText formEditText, int length) {
        this.mFormEditText = formEditText;
        this.mLength = length;
    }

    public boolean isComplete() {
        return this.mFormEditText.getText().length() >= this.mLength;
    }
}
