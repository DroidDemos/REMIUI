package com.google.android.gms.appdatasearch;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Iterator;

public class SuggestionResults implements SafeParcelable, Iterable<Result> {
    public static final ai CREATOR;
    final String[] Ex;
    final String[] Ey;
    final String mErrorMessage;
    final int mVersionCode;

    public class Result {
        private final int Et;
        final /* synthetic */ SuggestionResults Ez;

        Result(SuggestionResults suggestionResults, int idx) {
            this.Ez = suggestionResults;
            this.Et = idx;
        }
    }

    public final class ResultIterator implements Iterator<Result> {
        final /* synthetic */ SuggestionResults Ez;
        private int mCurIdx;

        public ResultIterator(SuggestionResults suggestionResults) {
            this.Ez = suggestionResults;
            this.mCurIdx = 0;
        }

        public boolean hasNext() {
            return this.mCurIdx < this.Ez.Ex.length;
        }

        public Result next() {
            SuggestionResults suggestionResults = this.Ez;
            int i = this.mCurIdx;
            this.mCurIdx = i + 1;
            return new Result(suggestionResults, i);
        }

        public void remove() {
            throw new IllegalStateException("remove not supported");
        }
    }

    static {
        CREATOR = new ai();
    }

    SuggestionResults(int versionCode, String errorMessage, String[] queries, String[] displayTexts) {
        this.mVersionCode = versionCode;
        this.mErrorMessage = errorMessage;
        this.Ex = queries;
        this.Ey = displayTexts;
    }

    public int describeContents() {
        ai aiVar = CREATOR;
        return 0;
    }

    public boolean hasError() {
        return this.mErrorMessage != null;
    }

    public Iterator<Result> iterator() {
        return hasError() ? null : new ResultIterator(this);
    }

    public void writeToParcel(Parcel out, int flags) {
        ai aiVar = CREATOR;
        ai.a(this, out, flags);
    }
}
