package android.support.v4.app;

import android.os.Bundle;
import android.support.v4.a.i;
import android.support.v4.d.a;
import android.support.v4.d.b;
import android.util.Log;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Modifier;

/* compiled from: LoaderManager */
final class h implements a {
    y lO;
    b lP;
    h lQ;
    final /* synthetic */ r lR;
    final Bundle mArgs;
    Object mData;
    boolean mDeliveredData;
    boolean mDestroyed;
    boolean mHaveData;
    final int mId;
    boolean mListenerRegistered;
    boolean mReportNextStart;
    boolean mRetaining;
    boolean mRetainingStarted;
    boolean mStarted;

    void start() {
        if (this.mRetaining && this.mRetainingStarted) {
            this.mStarted = true;
        } else if (!this.mStarted) {
            this.mStarted = true;
            if (r.DEBUG) {
                Log.v("LoaderManager", "  Starting: " + this);
            }
            if (this.lP == null && this.lO != null) {
                this.lP = this.lO.a(this.mId, this.mArgs);
            }
            if (this.lP == null) {
                return;
            }
            if (!this.lP.getClass().isMemberClass() || Modifier.isStatic(this.lP.getClass().getModifiers())) {
                if (!this.mListenerRegistered) {
                    this.lP.a(this.mId, this);
                    this.mListenerRegistered = true;
                }
                this.lP.startLoading();
                return;
            }
            throw new IllegalArgumentException("Object returned from onCreateLoader must not be a non-static inner member class: " + this.lP);
        }
    }

    void retain() {
        if (r.DEBUG) {
            Log.v("LoaderManager", "  Retaining: " + this);
        }
        this.mRetaining = true;
        this.mRetainingStarted = this.mStarted;
        this.mStarted = false;
        this.lO = null;
    }

    void finishRetain() {
        if (this.mRetaining) {
            if (r.DEBUG) {
                Log.v("LoaderManager", "  Finished Retaining: " + this);
            }
            this.mRetaining = false;
            if (!(this.mStarted == this.mRetainingStarted || this.mStarted)) {
                stop();
            }
        }
        if (this.mStarted && this.mHaveData && !this.mReportNextStart) {
            a(this.lP, this.mData);
        }
    }

    void reportStart() {
        if (this.mStarted && this.mReportNextStart) {
            this.mReportNextStart = false;
            if (this.mHaveData) {
                a(this.lP, this.mData);
            }
        }
    }

    void stop() {
        if (r.DEBUG) {
            Log.v("LoaderManager", "  Stopping: " + this);
        }
        this.mStarted = false;
        if (!this.mRetaining && this.lP != null && this.mListenerRegistered) {
            this.mListenerRegistered = false;
            this.lP.a(this);
            this.lP.stopLoading();
        }
    }

    void destroy() {
        String str;
        y yVar = null;
        if (r.DEBUG) {
            Log.v("LoaderManager", "  Destroying: " + this);
        }
        this.mDestroyed = true;
        boolean z = this.mDeliveredData;
        this.mDeliveredData = false;
        if (this.lO != null && this.lP != null && this.mHaveData && z) {
            if (r.DEBUG) {
                Log.v("LoaderManager", "  Reseting: " + this);
            }
            if (this.lR.tz != null) {
                String str2 = this.lR.tz.oa.mNoTransactionsBecause;
                this.lR.tz.oa.mNoTransactionsBecause = "onLoaderReset";
                str = str2;
            } else {
                str = null;
            }
            try {
                this.lO.a(this.lP);
            } finally {
                yVar = this.lR.tz;
                if (yVar != null) {
                    yVar = this.lR.tz.oa;
                    yVar.mNoTransactionsBecause = str;
                }
            }
        }
        this.lO = yVar;
        this.mData = yVar;
        this.mHaveData = false;
        if (this.lP != null) {
            if (this.mListenerRegistered) {
                this.mListenerRegistered = false;
                this.lP.a(this);
            }
            this.lP.reset();
        }
        if (this.lQ != null) {
            this.lQ.destroy();
        }
    }

    void a(b bVar, Object obj) {
        String str;
        if (this.lO != null) {
            if (this.lR.tz != null) {
                String str2 = this.lR.tz.oa.mNoTransactionsBecause;
                this.lR.tz.oa.mNoTransactionsBecause = "onLoadFinished";
                str = str2;
            } else {
                str = null;
            }
            try {
                if (r.DEBUG) {
                    Log.v("LoaderManager", "  onLoadFinished in " + bVar + ": " + bVar.dataToString(obj));
                }
                this.lO.b(bVar, obj);
                this.mDeliveredData = true;
            } finally {
                if (this.lR.tz != null) {
                    this.lR.tz.oa.mNoTransactionsBecause = str;
                }
            }
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(64);
        stringBuilder.append("LoaderInfo{");
        stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
        stringBuilder.append(" #");
        stringBuilder.append(this.mId);
        stringBuilder.append(" : ");
        i.buildShortClassTag(this.lP, stringBuilder);
        stringBuilder.append("}}");
        return stringBuilder.toString();
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.print(str);
        printWriter.print("mId=");
        printWriter.print(this.mId);
        printWriter.print(" mArgs=");
        printWriter.println(this.mArgs);
        printWriter.print(str);
        printWriter.print("mCallbacks=");
        printWriter.println(this.lO);
        printWriter.print(str);
        printWriter.print("mLoader=");
        printWriter.println(this.lP);
        if (this.lP != null) {
            this.lP.dump(str + "  ", fileDescriptor, printWriter, strArr);
        }
        if (this.mHaveData || this.mDeliveredData) {
            printWriter.print(str);
            printWriter.print("mHaveData=");
            printWriter.print(this.mHaveData);
            printWriter.print("  mDeliveredData=");
            printWriter.println(this.mDeliveredData);
            printWriter.print(str);
            printWriter.print("mData=");
            printWriter.println(this.mData);
        }
        printWriter.print(str);
        printWriter.print("mStarted=");
        printWriter.print(this.mStarted);
        printWriter.print(" mReportNextStart=");
        printWriter.print(this.mReportNextStart);
        printWriter.print(" mDestroyed=");
        printWriter.println(this.mDestroyed);
        printWriter.print(str);
        printWriter.print("mRetaining=");
        printWriter.print(this.mRetaining);
        printWriter.print(" mRetainingStarted=");
        printWriter.print(this.mRetainingStarted);
        printWriter.print(" mListenerRegistered=");
        printWriter.println(this.mListenerRegistered);
        if (this.lQ != null) {
            printWriter.print(str);
            printWriter.println("Pending Loader ");
            printWriter.print(this.lQ);
            printWriter.println(":");
            this.lQ.dump(str + "  ", fileDescriptor, printWriter, strArr);
        }
    }
}
