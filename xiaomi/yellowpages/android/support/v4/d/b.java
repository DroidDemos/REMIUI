package android.support.v4.d;

import android.support.v4.a.i;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* compiled from: Loader */
public class b {
    a Ln;
    boolean mAbandoned;
    boolean mContentChanged;
    int mId;
    boolean mProcessingChange;
    boolean mReset;
    boolean mStarted;

    public void a(int i, a aVar) {
        if (this.Ln != null) {
            throw new IllegalStateException("There is already a listener registered");
        }
        this.Ln = aVar;
        this.mId = i;
    }

    public void a(a aVar) {
        if (this.Ln == null) {
            throw new IllegalStateException("No listener register");
        } else if (this.Ln != aVar) {
            throw new IllegalArgumentException("Attempting to unregister the wrong listener");
        } else {
            this.Ln = null;
        }
    }

    public final void startLoading() {
        this.mStarted = true;
        this.mReset = false;
        this.mAbandoned = false;
        onStartLoading();
    }

    protected void onStartLoading() {
    }

    public void stopLoading() {
        this.mStarted = false;
        onStopLoading();
    }

    protected void onStopLoading() {
    }

    public void reset() {
        onReset();
        this.mReset = true;
        this.mStarted = false;
        this.mAbandoned = false;
        this.mContentChanged = false;
        this.mProcessingChange = false;
    }

    protected void onReset() {
    }

    public String dataToString(Object obj) {
        StringBuilder stringBuilder = new StringBuilder(64);
        i.buildShortClassTag(obj, stringBuilder);
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(64);
        i.buildShortClassTag(this, stringBuilder);
        stringBuilder.append(" id=");
        stringBuilder.append(this.mId);
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.print(str);
        printWriter.print("mId=");
        printWriter.print(this.mId);
        printWriter.print(" mListener=");
        printWriter.println(this.Ln);
        if (this.mStarted || this.mContentChanged || this.mProcessingChange) {
            printWriter.print(str);
            printWriter.print("mStarted=");
            printWriter.print(this.mStarted);
            printWriter.print(" mContentChanged=");
            printWriter.print(this.mContentChanged);
            printWriter.print(" mProcessingChange=");
            printWriter.println(this.mProcessingChange);
        }
        if (this.mAbandoned || this.mReset) {
            printWriter.print(str);
            printWriter.print("mAbandoned=");
            printWriter.print(this.mAbandoned);
            printWriter.print(" mReset=");
            printWriter.println(this.mReset);
        }
    }
}
