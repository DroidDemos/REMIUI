package com.google.android.libraries.bind.data;

import com.google.android.libraries.bind.util.Util;
import java.util.Locale;

public class DataViewHelper extends DataObserver {
    private boolean attached;
    private boolean clearDataOnDetach;
    private DataList dataRow;
    private boolean registered;
    private boolean temporarilyDetached;
    private final DataView view;

    public DataViewHelper(DataView view) {
        this.clearDataOnDetach = true;
        this.view = view;
    }

    public void setDataRow(DataList dataRow) {
        if (dataRow != this.dataRow) {
            if (this.registered) {
                unregister();
            }
            this.dataRow = dataRow;
            updateRegistrationIfNeeded();
            onDataChanged(DataChange.INVALIDATION);
        }
    }

    public DataList getDataRow() {
        return this.dataRow;
    }

    public Data getData() {
        return isReady() ? this.dataRow.getData(0) : null;
    }

    public String toString() {
        boolean z = false;
        Locale locale = Locale.US;
        String str = "View type: %s, hasData: %b, registered: %b, attached: %b,temporarilyDetached: %b, clearDataOnDetach: %b";
        Object[] objArr = new Object[6];
        objArr[0] = this.view.getClass().getSimpleName();
        if (this.dataRow != null) {
            z = true;
        }
        objArr[1] = Boolean.valueOf(z);
        objArr[2] = Boolean.valueOf(this.registered);
        objArr[3] = Boolean.valueOf(this.attached);
        objArr[4] = Boolean.valueOf(this.temporarilyDetached);
        objArr[5] = Boolean.valueOf(this.clearDataOnDetach);
        return String.format(locale, str, objArr);
    }

    public void onDataChanged(DataChange change) {
        boolean z = true;
        if (this.dataRow != null && this.dataRow.getCount() > 1) {
            z = false;
        }
        Util.checkPrecondition(z, "Passed DataList with more than one row.");
        this.view.onDataUpdated(getData());
    }

    public void onAttachedToWindow() {
        this.attached = true;
        this.temporarilyDetached = false;
        updateRegistrationIfNeeded();
    }

    public void onDetachedFromWindow() {
        this.attached = false;
        this.temporarilyDetached = false;
        updateRegistrationIfNeeded();
    }

    public void onStartTemporaryDetach() {
        this.temporarilyDetached = true;
        updateRegistrationIfNeeded();
    }

    public void onFinishTemporaryDetach() {
        this.temporarilyDetached = false;
        updateRegistrationIfNeeded();
    }

    public boolean isTemporarilyDetached() {
        return this.temporarilyDetached;
    }

    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (isTemporarilyDetached()) {
            onFinishTemporaryDetach();
        }
    }

    private void updateRegistrationIfNeeded() {
        boolean shouldBeRegistered = (this.attached && !this.temporarilyDetached) || !this.clearDataOnDetach;
        if (shouldBeRegistered) {
            if (!this.registered && this.dataRow != null) {
                register();
            }
        } else if (this.registered) {
            unregister();
        }
    }

    private void register() {
        this.dataRow.registerDataObserver(this);
        this.registered = true;
    }

    private void unregister() {
        this.dataRow.unregisterDataObserver(this);
        this.registered = false;
    }

    public boolean isReady() {
        return (this.dataRow == null || this.dataRow.isEmpty()) ? false : true;
    }
}
