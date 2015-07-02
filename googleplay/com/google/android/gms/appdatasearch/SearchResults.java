package com.google.android.gms.appdatasearch;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

public class SearchResults implements SafeParcelable, Iterable<Result> {
    public static final ae CREATOR;
    final int[] Ef;
    final byte[] Eg;
    final Bundle[] Eh;
    final Bundle[] Ei;
    final Bundle[] Ej;
    final int Ek;
    final int[] El;
    final String[] Em;
    final byte[] En;
    final double[] Eo;
    final String mErrorMessage;
    final int mVersionCode;

    public class Result {
        private final ResultIterator Es;
        private final int Et;
        final /* synthetic */ SearchResults Eu;

        Result(SearchResults searchResults, int idx, ResultIterator it) {
            this.Eu = searchResults;
            this.Es = it;
            this.Et = idx;
        }
    }

    public class ResultIterator implements Iterator<Result> {
        final /* synthetic */ SearchResults Eu;
        private final Map<String, Object>[] Ew;
        protected int mCurIdx;

        ResultIterator(SearchResults searchResults) {
            this.Eu = searchResults;
            this.Ew = searchResults.hasError() ? null : new Map[searchResults.Em.length];
        }

        public boolean hasNext() {
            return !this.Eu.hasError() && this.mCurIdx < this.Eu.getNumResults();
        }

        protected void moveToNext() {
            this.mCurIdx++;
        }

        public Result next() {
            if (hasNext()) {
                Result result = new Result(this.Eu, this.mCurIdx, this);
                moveToNext();
                return result;
            }
            throw new NoSuchElementException("No more results.");
        }

        public void remove() {
            throw new IllegalStateException("remove not supported");
        }
    }

    static {
        CREATOR = new ae();
    }

    SearchResults(int versionCode, String errorMessage, int[] uriLengths, byte[] uriBuffer, Bundle[] tags, Bundle[] sectionLengths, Bundle[] sectionBuffers, int numResults, int[] corpusIds, String[] corpusNames, byte[] debugInfo, double[] scores) {
        this.mVersionCode = versionCode;
        this.mErrorMessage = errorMessage;
        this.Ef = uriLengths;
        this.Eg = uriBuffer;
        this.Eh = tags;
        this.Ei = sectionLengths;
        this.Ej = sectionBuffers;
        this.Ek = numResults;
        this.El = corpusIds;
        this.Em = corpusNames;
        this.En = debugInfo;
        this.Eo = scores;
    }

    public int describeContents() {
        ae aeVar = CREATOR;
        return 0;
    }

    public int getNumResults() {
        return this.Ek;
    }

    public boolean hasError() {
        return this.mErrorMessage != null;
    }

    public ResultIterator iterator() {
        return new ResultIterator(this);
    }

    public void writeToParcel(Parcel out, int flags) {
        ae aeVar = CREATOR;
        ae.a(this, out, flags);
    }
}
