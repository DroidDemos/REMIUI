package com.google.android.gms.common.data;

import android.database.CursorIndexOutOfBoundsException;
import android.database.CursorWindow;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.util.Log;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kn;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class DataHolder implements SafeParcelable {
    public static final e CREATOR;
    private static final a Uj;
    private final int Rk;
    private final String[] Ub;
    Bundle Uc;
    private final CursorWindow[] Ud;
    private final Bundle Ue;
    int[] Uf;
    int Ug;
    private Object Uh;
    private boolean Ui;
    boolean mClosed;
    private final int mVersionCode;

    public static class a {
        private final String[] Ub;
        private final ArrayList<HashMap<String, Object>> Uk;
        private final String Ul;
        private final HashMap<Object, Integer> Um;
        private boolean Un;
        private String Uo;

        private a(String[] strArr, String str) {
            this.Ub = (String[]) kn.k(strArr);
            this.Uk = new ArrayList();
            this.Ul = str;
            this.Um = new HashMap();
            this.Un = false;
            this.Uo = null;
        }
    }

    public static class b extends RuntimeException {
        public b(String str) {
            super(str);
        }
    }

    static {
        CREATOR = new e();
        Uj = new a(new String[0], null) {
        };
    }

    DataHolder(int versionCode, String[] columns, CursorWindow[] windows, int statusCode, Bundle metadata) {
        this.mClosed = false;
        this.Ui = true;
        this.mVersionCode = versionCode;
        this.Ub = columns;
        this.Ud = windows;
        this.Rk = statusCode;
        this.Ue = metadata;
    }

    private DataHolder(a builder, int statusCode, Bundle metadata) {
        this(builder.Ub, a(builder, -1), statusCode, metadata);
    }

    public DataHolder(String[] columns, CursorWindow[] windows, int statusCode, Bundle metadata) {
        this.mClosed = false;
        this.Ui = true;
        this.mVersionCode = 1;
        this.Ub = (String[]) kn.k(columns);
        this.Ud = (CursorWindow[]) kn.k(windows);
        this.Rk = statusCode;
        this.Ue = metadata;
        ir();
    }

    public static DataHolder a(int i, Bundle bundle) {
        return new DataHolder(Uj, i, bundle);
    }

    private static CursorWindow[] a(a aVar, int i) {
        int size;
        int i2 = 0;
        if (aVar.Ub.length == 0) {
            return new CursorWindow[0];
        }
        List b = (i < 0 || i >= aVar.Uk.size()) ? aVar.Uk : aVar.Uk.subList(0, i);
        int size2 = b.size();
        CursorWindow cursorWindow = new CursorWindow(false);
        ArrayList arrayList = new ArrayList();
        arrayList.add(cursorWindow);
        cursorWindow.setNumColumns(aVar.Ub.length);
        int i3 = 0;
        int i4 = 0;
        while (i3 < size2) {
            if (!cursorWindow.allocRow()) {
                Log.d("DataHolder", "Allocating additional cursor window for large data set (row " + i3 + ")");
                cursorWindow = new CursorWindow(false);
                cursorWindow.setStartPosition(i3);
                cursorWindow.setNumColumns(aVar.Ub.length);
                arrayList.add(cursorWindow);
                if (!cursorWindow.allocRow()) {
                    Log.e("DataHolder", "Unable to allocate row to hold data.");
                    arrayList.remove(cursorWindow);
                    return (CursorWindow[]) arrayList.toArray(new CursorWindow[arrayList.size()]);
                }
            }
            try {
                int i5;
                int i6;
                CursorWindow cursorWindow2;
                Map map = (Map) b.get(i3);
                boolean z = true;
                for (int i7 = 0; i7 < aVar.Ub.length && z; i7++) {
                    String str = aVar.Ub[i7];
                    Object obj = map.get(str);
                    if (obj == null) {
                        z = cursorWindow.putNull(i3, i7);
                    } else if (obj instanceof String) {
                        z = cursorWindow.putString((String) obj, i3, i7);
                    } else if (obj instanceof Long) {
                        z = cursorWindow.putLong(((Long) obj).longValue(), i3, i7);
                    } else if (obj instanceof Integer) {
                        z = cursorWindow.putLong((long) ((Integer) obj).intValue(), i3, i7);
                    } else if (obj instanceof Boolean) {
                        z = cursorWindow.putLong(((Boolean) obj).booleanValue() ? 1 : 0, i3, i7);
                    } else if (obj instanceof byte[]) {
                        z = cursorWindow.putBlob((byte[]) obj, i3, i7);
                    } else if (obj instanceof Double) {
                        z = cursorWindow.putDouble(((Double) obj).doubleValue(), i3, i7);
                    } else if (obj instanceof Float) {
                        z = cursorWindow.putDouble((double) ((Float) obj).floatValue(), i3, i7);
                    } else {
                        throw new IllegalArgumentException("Unsupported object for column " + str + ": " + obj);
                    }
                }
                if (z) {
                    i5 = i3;
                    i6 = 0;
                    cursorWindow2 = cursorWindow;
                } else if (i4 != 0) {
                    throw new b("Could not add the value to a new CursorWindow. The size of value may be larger than what a CursorWindow can handle.");
                } else {
                    Log.d("DataHolder", "Couldn't populate window data for row " + i3 + " - allocating new window.");
                    cursorWindow.freeLastRow();
                    CursorWindow cursorWindow3 = new CursorWindow(false);
                    cursorWindow3.setStartPosition(i3);
                    cursorWindow3.setNumColumns(aVar.Ub.length);
                    arrayList.add(cursorWindow3);
                    i5 = i3 - 1;
                    cursorWindow2 = cursorWindow3;
                    i6 = 1;
                }
                i4 = i6;
                cursorWindow = cursorWindow2;
                i3 = i5 + 1;
            } catch (RuntimeException e) {
                RuntimeException runtimeException = e;
                size = arrayList.size();
                while (i2 < size) {
                    ((CursorWindow) arrayList.get(i2)).close();
                    i2++;
                }
                throw runtimeException;
            }
        }
        return (CursorWindow[]) arrayList.toArray(new CursorWindow[arrayList.size()]);
    }

    public static DataHolder cX(int i) {
        return a(i, null);
    }

    private void h(String str, int i) {
        if (this.Uc == null || !this.Uc.containsKey(str)) {
            throw new IllegalArgumentException("No such column: " + str);
        } else if (isClosed()) {
            throw new IllegalArgumentException("Buffer is closed.");
        } else if (i < 0 || i >= this.Ug) {
            throw new CursorIndexOutOfBoundsException(i, this.Ug);
        }
    }

    public long a(String str, int i, int i2) {
        h(str, i);
        return this.Ud[i2].getLong(i, this.Uc.getInt(str));
    }

    public boolean aZ(String str) {
        return this.Uc.containsKey(str);
    }

    public int b(String str, int i, int i2) {
        h(str, i);
        return this.Ud[i2].getInt(i, this.Uc.getInt(str));
    }

    public String c(String str, int i, int i2) {
        h(str, i);
        return this.Ud[i2].getString(i, this.Uc.getInt(str));
    }

    public int cW(int i) {
        int i2 = 0;
        boolean z = i >= 0 && i < this.Ug;
        kn.K(z);
        while (i2 < this.Uf.length) {
            if (i < this.Uf[i2]) {
                i2--;
                break;
            }
            i2++;
        }
        return i2 == this.Uf.length ? i2 - 1 : i2;
    }

    public void close() {
        synchronized (this) {
            if (!this.mClosed) {
                this.mClosed = true;
                for (CursorWindow close : this.Ud) {
                    close.close();
                }
            }
        }
    }

    public boolean d(String str, int i, int i2) {
        h(str, i);
        return Long.valueOf(this.Ud[i2].getLong(i, this.Uc.getInt(str))).longValue() == 1;
    }

    public int describeContents() {
        return 0;
    }

    public float e(String str, int i, int i2) {
        h(str, i);
        return this.Ud[i2].getFloat(i, this.Uc.getInt(str));
    }

    public double f(String str, int i, int i2) {
        h(str, i);
        return this.Ud[i2].getDouble(i, this.Uc.getInt(str));
    }

    protected void finalize() throws Throwable {
        try {
            if (this.Ui && this.Ud.length > 0 && !isClosed()) {
                Log.e("DataBuffer", "Internal data leak within a DataBuffer object detected!  Be sure to explicitly call release() on all DataBuffer extending objects when you are done with them. (" + (this.Uh == null ? "internal object: " + toString() : this.Uh.toString()) + ")");
                close();
            }
            super.finalize();
        } catch (Throwable th) {
            super.finalize();
        }
    }

    public void g(Object obj) {
        this.Uh = obj;
    }

    public byte[] g(String str, int i, int i2) {
        h(str, i);
        return this.Ud[i2].getBlob(i, this.Uc.getInt(str));
    }

    public int getCount() {
        return this.Ug;
    }

    public Bundle getMetadata() {
        return this.Ue;
    }

    public int getStatusCode() {
        return this.Rk;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public Uri h(String str, int i, int i2) {
        String c = c(str, i, i2);
        return c == null ? null : Uri.parse(c);
    }

    public boolean i(String str, int i, int i2) {
        h(str, i);
        return this.Ud[i2].isNull(i, this.Uc.getInt(str));
    }

    public void ir() {
        int i;
        int i2 = 0;
        this.Uc = new Bundle();
        for (i = 0; i < this.Ub.length; i++) {
            this.Uc.putInt(this.Ub[i], i);
        }
        this.Uf = new int[this.Ud.length];
        i = 0;
        while (i2 < this.Ud.length) {
            this.Uf[i2] = i;
            i += this.Ud[i2].getNumRows() - (i - this.Ud[i2].getStartPosition());
            i2++;
        }
        this.Ug = i;
    }

    String[] is() {
        return this.Ub;
    }

    public boolean isClosed() {
        boolean z;
        synchronized (this) {
            z = this.mClosed;
        }
        return z;
    }

    CursorWindow[] it() {
        return this.Ud;
    }

    public void writeToParcel(Parcel dest, int flags) {
        e.a(this, dest, flags);
    }
}
