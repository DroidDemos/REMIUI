package android.support.v4.app;

import android.support.v4.a.i;
import android.support.v4.a.k;
import android.util.Log;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* compiled from: LoaderManager */
class r extends o {
    static boolean DEBUG;
    final k CE;
    final k CF;
    boolean mRetaining;
    boolean mStarted;
    final String mWho;
    FragmentActivity tz;

    static {
        DEBUG = false;
    }

    r(String str, FragmentActivity fragmentActivity, boolean z) {
        this.CE = new k();
        this.CF = new k();
        this.mWho = str;
        this.tz = fragmentActivity;
        this.mStarted = z;
    }

    void a(FragmentActivity fragmentActivity) {
        this.tz = fragmentActivity;
    }

    void doStart() {
        if (DEBUG) {
            Log.v("LoaderManager", "Starting in " + this);
        }
        if (this.mStarted) {
            Throwable runtimeException = new RuntimeException("here");
            runtimeException.fillInStackTrace();
            Log.w("LoaderManager", "Called doStart when already started: " + this, runtimeException);
            return;
        }
        this.mStarted = true;
        for (int size = this.CE.size() - 1; size >= 0; size--) {
            ((h) this.CE.valueAt(size)).start();
        }
    }

    void doStop() {
        if (DEBUG) {
            Log.v("LoaderManager", "Stopping in " + this);
        }
        if (this.mStarted) {
            for (int size = this.CE.size() - 1; size >= 0; size--) {
                ((h) this.CE.valueAt(size)).stop();
            }
            this.mStarted = false;
            return;
        }
        Throwable runtimeException = new RuntimeException("here");
        runtimeException.fillInStackTrace();
        Log.w("LoaderManager", "Called doStop when not started: " + this, runtimeException);
    }

    void doRetain() {
        if (DEBUG) {
            Log.v("LoaderManager", "Retaining in " + this);
        }
        if (this.mStarted) {
            this.mRetaining = true;
            this.mStarted = false;
            for (int size = this.CE.size() - 1; size >= 0; size--) {
                ((h) this.CE.valueAt(size)).retain();
            }
            return;
        }
        Throwable runtimeException = new RuntimeException("here");
        runtimeException.fillInStackTrace();
        Log.w("LoaderManager", "Called doRetain when not started: " + this, runtimeException);
    }

    void finishRetain() {
        if (this.mRetaining) {
            if (DEBUG) {
                Log.v("LoaderManager", "Finished Retaining in " + this);
            }
            this.mRetaining = false;
            for (int size = this.CE.size() - 1; size >= 0; size--) {
                ((h) this.CE.valueAt(size)).finishRetain();
            }
        }
    }

    void doReportNextStart() {
        for (int size = this.CE.size() - 1; size >= 0; size--) {
            ((h) this.CE.valueAt(size)).mReportNextStart = true;
        }
    }

    void doReportStart() {
        for (int size = this.CE.size() - 1; size >= 0; size--) {
            ((h) this.CE.valueAt(size)).reportStart();
        }
    }

    void doDestroy() {
        int size;
        if (!this.mRetaining) {
            if (DEBUG) {
                Log.v("LoaderManager", "Destroying Active in " + this);
            }
            for (size = this.CE.size() - 1; size >= 0; size--) {
                ((h) this.CE.valueAt(size)).destroy();
            }
            this.CE.clear();
        }
        if (DEBUG) {
            Log.v("LoaderManager", "Destroying Inactive in " + this);
        }
        for (size = this.CF.size() - 1; size >= 0; size--) {
            ((h) this.CF.valueAt(size)).destroy();
        }
        this.CF.clear();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(128);
        stringBuilder.append("LoaderManager{");
        stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
        stringBuilder.append(" in ");
        i.buildShortClassTag(this.tz, stringBuilder);
        stringBuilder.append("}}");
        return stringBuilder.toString();
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        int i = 0;
        if (this.CE.size() > 0) {
            printWriter.print(str);
            printWriter.println("Active Loaders:");
            String str2 = str + "    ";
            for (int i2 = 0; i2 < this.CE.size(); i2++) {
                h hVar = (h) this.CE.valueAt(i2);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(this.CE.keyAt(i2));
                printWriter.print(": ");
                printWriter.println(hVar.toString());
                hVar.dump(str2, fileDescriptor, printWriter, strArr);
            }
        }
        if (this.CF.size() > 0) {
            printWriter.print(str);
            printWriter.println("Inactive Loaders:");
            String str3 = str + "    ";
            while (i < this.CF.size()) {
                hVar = (h) this.CF.valueAt(i);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(this.CF.keyAt(i));
                printWriter.print(": ");
                printWriter.println(hVar.toString());
                hVar.dump(str3, fileDescriptor, printWriter, strArr);
                i++;
            }
        }
    }

    public boolean hasRunningLoaders() {
        int size = this.CE.size();
        boolean z = false;
        for (int i = 0; i < size; i++) {
            int i2;
            h hVar = (h) this.CE.valueAt(i);
            if (!hVar.mStarted || hVar.mDeliveredData) {
                i2 = 0;
            } else {
                i2 = 1;
            }
            z |= i2;
        }
        return z;
    }
}
