package android.support.v4.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.a.a;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.alipay.sdk.protocol.WindowData;
import com.alipay.sdk.util.DeviceInfo;
import com.ta.utdid2.android.utils.Base64;
import com.ta.utdid2.core.persistent.TransactionXMLFile;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;

public class FragmentActivity extends Activity {
    boolean mCheckedForLoaderManager;
    boolean mCreated;
    final Handler mHandler;
    boolean mLoadersStarted;
    boolean mResumed;
    boolean mRetaining;
    boolean mStopped;
    final p oa;
    final w ob;
    boolean oc;
    boolean od;
    a oe;
    r of;

    public FragmentActivity() {
        this.mHandler = new l(this);
        this.oa = new p();
        this.ob = new k(this);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        this.oa.noteStateNotSaved();
        int i3 = i >> 16;
        if (i3 != 0) {
            i3--;
            if (this.oa.mActive == null || i3 < 0 || i3 >= this.oa.mActive.size()) {
                Log.w("FragmentActivity", "Activity result fragment index out of range: 0x" + Integer.toHexString(i));
                return;
            }
            Fragment fragment = (Fragment) this.oa.mActive.get(i3);
            if (fragment == null) {
                Log.w("FragmentActivity", "Activity result no fragment exists for index: 0x" + Integer.toHexString(i));
                return;
            } else {
                fragment.onActivityResult(65535 & i, i2, intent);
                return;
            }
        }
        super.onActivityResult(i, i2, intent);
    }

    public void onBackPressed() {
        if (!this.oa.popBackStackImmediate()) {
            finish();
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.oa.dispatchConfigurationChanged(configuration);
    }

    protected void onCreate(Bundle bundle) {
        this.oa.a(this, this.ob, null);
        if (getLayoutInflater().getFactory() == null) {
            getLayoutInflater().setFactory(this);
        }
        super.onCreate(bundle);
        z zVar = (z) getLastNonConfigurationInstance();
        if (zVar != null) {
            this.oe = zVar.Jp;
        }
        if (bundle != null) {
            ArrayList arrayList;
            Parcelable parcelable = bundle.getParcelable("android:support:fragments");
            p pVar = this.oa;
            if (zVar != null) {
                arrayList = zVar.fragments;
            } else {
                arrayList = null;
            }
            pVar.restoreAllState(parcelable, arrayList);
        }
        this.oa.dispatchCreate();
    }

    public boolean onCreatePanelMenu(int i, Menu menu) {
        if (i != 0) {
            return super.onCreatePanelMenu(i, menu);
        }
        boolean onCreatePanelMenu = super.onCreatePanelMenu(i, menu) | this.oa.dispatchCreateOptionsMenu(menu, getMenuInflater());
        if (VERSION.SDK_INT >= 11) {
            return onCreatePanelMenu;
        }
        return true;
    }

    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        int i = 0;
        Fragment fragment = null;
        if (!"fragment".equals(str)) {
            return super.onCreateView(str, context, attributeSet);
        }
        String attributeValue = attributeSet.getAttributeValue(fragment, "class");
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, t.Fragment);
        if (attributeValue == null) {
            attributeValue = obtainStyledAttributes.getString(0);
        }
        int resourceId = obtainStyledAttributes.getResourceId(1, -1);
        String string = obtainStyledAttributes.getString(2);
        obtainStyledAttributes.recycle();
        if (!Fragment.s(this, attributeValue)) {
            return super.onCreateView(str, context, attributeSet);
        }
        if (fragment != null) {
            i = fragment.getId();
        }
        if (i == -1 && resourceId == -1 && string == null) {
            throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Must specify unique android:id, android:tag, or have a parent with an id for " + attributeValue);
        }
        if (resourceId != -1) {
            fragment = this.oa.T(resourceId);
        }
        if (fragment == null && string != null) {
            fragment = this.oa.cc(string);
        }
        if (fragment == null && i != -1) {
            fragment = this.oa.T(i);
        }
        if (p.DEBUG) {
            Log.v("FragmentActivity", "onCreateView: id=0x" + Integer.toHexString(resourceId) + " fname=" + attributeValue + " existing=" + fragment);
        }
        if (fragment == null) {
            Fragment r = Fragment.r(this, attributeValue);
            r.mFromLayout = true;
            r.mFragmentId = resourceId != 0 ? resourceId : i;
            r.mContainerId = i;
            r.mTag = string;
            r.mInLayout = true;
            r.ty = this.oa;
            r.onInflate(this, attributeSet, r.mSavedFragmentState);
            this.oa.a(r, true);
            fragment = r;
        } else if (fragment.mInLayout) {
            throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Duplicate id 0x" + Integer.toHexString(resourceId) + ", tag " + string + ", or parent id 0x" + Integer.toHexString(i) + " with another fragment for " + attributeValue);
        } else {
            fragment.mInLayout = true;
            if (!fragment.mRetaining) {
                fragment.onInflate(this, attributeSet, fragment.mSavedFragmentState);
            }
            this.oa.c(fragment);
        }
        if (fragment.mView == null) {
            throw new IllegalStateException("Fragment " + attributeValue + " did not create a view.");
        }
        if (resourceId != 0) {
            fragment.mView.setId(resourceId);
        }
        if (fragment.mView.getTag() == null) {
            fragment.mView.setTag(string);
        }
        return fragment.mView;
    }

    protected void onDestroy() {
        super.onDestroy();
        m(false);
        this.oa.dispatchDestroy();
        if (this.of != null) {
            this.of.doDestroy();
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (VERSION.SDK_INT >= 5 || i != 4 || keyEvent.getRepeatCount() != 0) {
            return super.onKeyDown(i, keyEvent);
        }
        onBackPressed();
        return true;
    }

    public void onLowMemory() {
        super.onLowMemory();
        this.oa.dispatchLowMemory();
    }

    public boolean onMenuItemSelected(int i, MenuItem menuItem) {
        if (super.onMenuItemSelected(i, menuItem)) {
            return true;
        }
        switch (i) {
            case TransactionXMLFile.MODE_PRIVATE /*0*/:
                return this.oa.dispatchOptionsItemSelected(menuItem);
            case WindowData.g /*6*/:
                return this.oa.dispatchContextItemSelected(menuItem);
            default:
                return false;
        }
    }

    public void onPanelClosed(int i, Menu menu) {
        switch (i) {
            case TransactionXMLFile.MODE_PRIVATE /*0*/:
                this.oa.dispatchOptionsMenuClosed(menu);
                break;
        }
        super.onPanelClosed(i, menu);
    }

    protected void onPause() {
        super.onPause();
        this.mResumed = false;
        if (this.mHandler.hasMessages(2)) {
            this.mHandler.removeMessages(2);
            ck();
        }
        this.oa.dispatchPause();
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.oa.noteStateNotSaved();
    }

    protected void onResume() {
        super.onResume();
        this.mHandler.sendEmptyMessage(2);
        this.mResumed = true;
        this.oa.execPendingActions();
    }

    protected void onPostResume() {
        super.onPostResume();
        this.mHandler.removeMessages(2);
        ck();
        this.oa.execPendingActions();
    }

    protected void ck() {
        this.oa.dispatchResume();
    }

    public boolean onPreparePanel(int i, View view, Menu menu) {
        if (i != 0 || menu == null) {
            return super.onPreparePanel(i, view, menu);
        }
        if (this.od) {
            this.od = false;
            menu.clear();
            onCreatePanelMenu(i, menu);
        }
        return a(view, menu) | this.oa.dispatchPrepareOptionsMenu(menu);
    }

    protected boolean a(View view, Menu menu) {
        return super.onPreparePanel(0, view, menu);
    }

    public final Object onRetainNonConfigurationInstance() {
        int i = 0;
        if (this.mStopped) {
            m(true);
        }
        Object cl = cl();
        ArrayList retainNonConfig = this.oa.retainNonConfig();
        int i2;
        if (this.oe != null) {
            int size = this.oe.size();
            r[] rVarArr = new r[size];
            for (int i3 = size - 1; i3 >= 0; i3--) {
                rVarArr[i3] = (r) this.oe.valueAt(i3);
            }
            i2 = 0;
            while (i < size) {
                r rVar = rVarArr[i];
                if (rVar.mRetaining) {
                    i2 = true;
                } else {
                    rVar.doDestroy();
                    this.oe.remove(rVar.mWho);
                }
                i++;
            }
        } else {
            i2 = 0;
        }
        if (retainNonConfig == null && r0 == 0 && cl == null) {
            return null;
        }
        Object zVar = new z();
        zVar.activity = null;
        zVar.Jn = cl;
        zVar.Jo = null;
        zVar.fragments = retainNonConfig;
        zVar.Jp = this.oe;
        return zVar;
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        Parcelable saveAllState = this.oa.saveAllState();
        if (saveAllState != null) {
            bundle.putParcelable("android:support:fragments", saveAllState);
        }
    }

    protected void onStart() {
        super.onStart();
        this.mStopped = false;
        this.oc = false;
        this.mHandler.removeMessages(1);
        if (!this.mCreated) {
            this.mCreated = true;
            this.oa.dispatchActivityCreated();
        }
        this.oa.noteStateNotSaved();
        this.oa.execPendingActions();
        if (!this.mLoadersStarted) {
            this.mLoadersStarted = true;
            if (this.of != null) {
                this.of.doStart();
            } else if (!this.mCheckedForLoaderManager) {
                this.of = a("(root)", this.mLoadersStarted, false);
                if (!(this.of == null || this.of.mStarted)) {
                    this.of.doStart();
                }
            }
            this.mCheckedForLoaderManager = true;
        }
        this.oa.dispatchStart();
        if (this.oe != null) {
            int size = this.oe.size();
            r[] rVarArr = new r[size];
            for (int i = size - 1; i >= 0; i--) {
                rVarArr[i] = (r) this.oe.valueAt(i);
            }
            for (int i2 = 0; i2 < size; i2++) {
                r rVar = rVarArr[i2];
                rVar.finishRetain();
                rVar.doReportStart();
            }
        }
    }

    protected void onStop() {
        super.onStop();
        this.mStopped = true;
        this.mHandler.sendEmptyMessage(1);
        this.oa.dispatchStop();
    }

    public Object cl() {
        return null;
    }

    public void cm() {
        if (VERSION.SDK_INT >= 11) {
            m.a(this);
        } else {
            this.od = true;
        }
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        String str2;
        if (VERSION.SDK_INT >= 11) {
            printWriter.print(str);
            printWriter.print("Local FragmentActivity ");
            printWriter.print(Integer.toHexString(System.identityHashCode(this)));
            printWriter.println(" State:");
            str2 = str + "  ";
            printWriter.print(str2);
            printWriter.print("mCreated=");
            printWriter.print(this.mCreated);
            printWriter.print("mResumed=");
            printWriter.print(this.mResumed);
            printWriter.print(" mStopped=");
            printWriter.print(this.mStopped);
            printWriter.print(" mReallyStopped=");
            printWriter.println(this.oc);
            printWriter.print(str2);
            printWriter.print("mLoadersStarted=");
            printWriter.println(this.mLoadersStarted);
        } else {
            printWriter.print(str);
            printWriter.print("Local FragmentActivity ");
            printWriter.print(Integer.toHexString(System.identityHashCode(this)));
            printWriter.println(" State:");
            str2 = str + "  ";
            printWriter.print(str2);
            printWriter.print("mCreated=");
            printWriter.print(this.mCreated);
            printWriter.print("mResumed=");
            printWriter.print(this.mResumed);
            printWriter.print(" mStopped=");
            printWriter.print(this.mStopped);
            printWriter.print(" mReallyStopped=");
            printWriter.println(this.oc);
            printWriter.print(str2);
            printWriter.print("mLoadersStarted=");
            printWriter.println(this.mLoadersStarted);
        }
        if (this.of != null) {
            printWriter.print(str);
            printWriter.print("Loader Manager ");
            printWriter.print(Integer.toHexString(System.identityHashCode(this.of)));
            printWriter.println(":");
            this.of.dump(str + "  ", fileDescriptor, printWriter, strArr);
        }
        this.oa.dump(str, fileDescriptor, printWriter, strArr);
        printWriter.print(str);
        printWriter.println("View Hierarchy:");
        a(str + "  ", printWriter, getWindow().getDecorView());
    }

    private static String h(View view) {
        char c;
        char c2 = 'F';
        char c3 = '.';
        StringBuilder stringBuilder = new StringBuilder(128);
        stringBuilder.append(view.getClass().getName());
        stringBuilder.append('{');
        stringBuilder.append(Integer.toHexString(System.identityHashCode(view)));
        stringBuilder.append(' ');
        switch (view.getVisibility()) {
            case TransactionXMLFile.MODE_PRIVATE /*0*/:
                stringBuilder.append('V');
                break;
            case Base64.CRLF /*4*/:
                stringBuilder.append('I');
                break;
            case Base64.URL_SAFE /*8*/:
                stringBuilder.append('G');
                break;
            default:
                stringBuilder.append('.');
                break;
        }
        if (view.isFocusable()) {
            c = 'F';
        } else {
            c = '.';
        }
        stringBuilder.append(c);
        if (view.isEnabled()) {
            c = 'E';
        } else {
            c = '.';
        }
        stringBuilder.append(c);
        stringBuilder.append(view.willNotDraw() ? '.' : 'D');
        if (view.isHorizontalScrollBarEnabled()) {
            c = 'H';
        } else {
            c = '.';
        }
        stringBuilder.append(c);
        if (view.isVerticalScrollBarEnabled()) {
            c = 'V';
        } else {
            c = '.';
        }
        stringBuilder.append(c);
        if (view.isClickable()) {
            c = 'C';
        } else {
            c = '.';
        }
        stringBuilder.append(c);
        if (view.isLongClickable()) {
            c = 'L';
        } else {
            c = '.';
        }
        stringBuilder.append(c);
        stringBuilder.append(' ');
        if (!view.isFocused()) {
            c2 = '.';
        }
        stringBuilder.append(c2);
        if (view.isSelected()) {
            c = 'S';
        } else {
            c = '.';
        }
        stringBuilder.append(c);
        if (view.isPressed()) {
            c3 = 'P';
        }
        stringBuilder.append(c3);
        stringBuilder.append(' ');
        stringBuilder.append(view.getLeft());
        stringBuilder.append(',');
        stringBuilder.append(view.getTop());
        stringBuilder.append('-');
        stringBuilder.append(view.getRight());
        stringBuilder.append(',');
        stringBuilder.append(view.getBottom());
        int id = view.getId();
        if (id != -1) {
            stringBuilder.append(" #");
            stringBuilder.append(Integer.toHexString(id));
            Resources resources = view.getResources();
            if (!(id == 0 || resources == null)) {
                String str;
                switch (-16777216 & id) {
                    case 16777216:
                        str = DeviceInfo.d;
                        break;
                    case 2130706432:
                        str = "app";
                        break;
                    default:
                        try {
                            str = resources.getResourcePackageName(id);
                            break;
                        } catch (NotFoundException e) {
                            break;
                        }
                }
                String resourceTypeName = resources.getResourceTypeName(id);
                String resourceEntryName = resources.getResourceEntryName(id);
                stringBuilder.append(" ");
                stringBuilder.append(str);
                stringBuilder.append(":");
                stringBuilder.append(resourceTypeName);
                stringBuilder.append("/");
                stringBuilder.append(resourceEntryName);
            }
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    private void a(String str, PrintWriter printWriter, View view) {
        printWriter.print(str);
        if (view == null) {
            printWriter.println("null");
            return;
        }
        printWriter.println(h(view));
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            if (childCount > 0) {
                String str2 = str + "  ";
                for (int i = 0; i < childCount; i++) {
                    a(str2, printWriter, viewGroup.getChildAt(i));
                }
            }
        }
    }

    void m(boolean z) {
        if (!this.oc) {
            this.oc = true;
            this.mRetaining = z;
            this.mHandler.removeMessages(1);
            cn();
        }
    }

    void cn() {
        if (this.mLoadersStarted) {
            this.mLoadersStarted = false;
            if (this.of != null) {
                if (this.mRetaining) {
                    this.of.doRetain();
                } else {
                    this.of.doStop();
                }
            }
        }
        this.oa.gN();
    }

    public void a(Fragment fragment) {
    }

    public void startActivityForResult(Intent intent, int i) {
        if (i == -1 || (-65536 & i) == 0) {
            super.startActivityForResult(intent, i);
            return;
        }
        throw new IllegalArgumentException("Can only use lower 16 bits for requestCode");
    }

    void Y(String str) {
        if (this.oe != null) {
            r rVar = (r) this.oe.get(str);
            if (rVar != null && !rVar.mRetaining) {
                rVar.doDestroy();
                this.oe.remove(str);
            }
        }
    }

    r a(String str, boolean z, boolean z2) {
        if (this.oe == null) {
            this.oe = new a();
        }
        r rVar = (r) this.oe.get(str);
        if (rVar != null) {
            rVar.a(this);
            return rVar;
        } else if (!z2) {
            return rVar;
        } else {
            rVar = new r(str, this, z);
            this.oe.put(str, rVar);
            return rVar;
        }
    }
}
