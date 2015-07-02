package com.google.android.gms.car.support;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import com.google.android.gms.car.CarActivity;
import com.google.android.gms.car.CarLog;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public class FragmentActivity extends CarActivity {
    final g Nl;
    boolean mCreated;
    final Handler mHandler;
    boolean mReallyStopped;
    boolean mResumed;
    boolean mStopped;

    static class a {
        public static final int[] Fragment;

        static {
            Fragment = new int[]{16842755, 16842960, 16842961};
        }
    }

    public void dump(String prefix, FileDescriptor fd, PrintWriter writer, String[] args) {
        writer.print(prefix);
        writer.print("Local FragmentActivity ");
        writer.print(Integer.toHexString(System.identityHashCode(this)));
        writer.println(" State:");
        writer.print(prefix + "  ");
        writer.print("mCreated=");
        writer.print(this.mCreated);
        writer.print("mResumed=");
        writer.print(this.mResumed);
        writer.print(" mStopped=");
        writer.print(this.mStopped);
        writer.print(" mReallyStopped=");
        writer.println(this.mReallyStopped);
        this.Nl.dump(prefix, fd, writer, args);
        writer.print(prefix);
        writer.println("View Hierarchy:");
    }

    void invalidateSupportFragment(String who) {
    }

    public void onAttachFragment(Fragment fragment) {
    }

    public View onCreateView(String name, Context context, AttributeSet attrs) {
        int i = 0;
        Fragment fragment = null;
        if (!"fragment".equals(name)) {
            return super.onCreateView(name, context, attrs);
        }
        String attributeValue = attrs.getAttributeValue(fragment, "class");
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attrs, a.Fragment);
        if (attributeValue == null) {
            attributeValue = obtainStyledAttributes.getString(0);
        }
        int resourceId = obtainStyledAttributes.getResourceId(1, -1);
        String string = obtainStyledAttributes.getString(2);
        obtainStyledAttributes.recycle();
        if (!Fragment.isSupportFragmentClass(getContext(), attributeValue)) {
            return super.onCreateView(name, context, attrs);
        }
        if (fragment != null) {
            i = fragment.getId();
        }
        if (i == -1 && resourceId == -1 && string == null) {
            throw new IllegalArgumentException(attrs.getPositionDescription() + ": Must specify unique android:id, android:tag, or have a parent with an id for " + attributeValue);
        }
        if (resourceId != -1) {
            fragment = this.Nl.findFragmentById(resourceId);
        }
        if (fragment == null && string != null) {
            fragment = this.Nl.findFragmentByTag(string);
        }
        if (fragment == null && i != -1) {
            fragment = this.Nl.findFragmentById(i);
        }
        if (CarLog.isLoggable("CAR.PROJECTION", 2)) {
            Log.v("CAR.PROJECTION", "onCreateView: id=0x" + Integer.toHexString(resourceId) + " fname=" + attributeValue + " existing=" + fragment);
        }
        if (fragment == null) {
            Fragment instantiate = Fragment.instantiate(getContext(), attributeValue);
            instantiate.mFromLayout = true;
            instantiate.mFragmentId = resourceId != 0 ? resourceId : i;
            instantiate.mContainerId = i;
            instantiate.mTag = string;
            instantiate.mInLayout = true;
            instantiate.Ng = this.Nl;
            instantiate.onInflate(this, attrs, instantiate.mSavedFragmentState);
            this.Nl.a(instantiate, true);
            fragment = instantiate;
        } else if (fragment.mInLayout) {
            throw new IllegalArgumentException(attrs.getPositionDescription() + ": Duplicate id 0x" + Integer.toHexString(resourceId) + ", tag " + string + ", or parent id 0x" + Integer.toHexString(i) + " with another fragment for " + attributeValue);
        } else {
            fragment.mInLayout = true;
            if (!fragment.mRetaining) {
                fragment.onInflate(this, attrs, fragment.mSavedFragmentState);
            }
            this.Nl.b(fragment);
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

    public void supportInvalidateOptionsMenu() {
    }
}
