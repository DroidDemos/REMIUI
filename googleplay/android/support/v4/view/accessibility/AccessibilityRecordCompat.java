package android.support.v4.view.accessibility;

import android.os.Build.VERSION;
import android.view.View;

public class AccessibilityRecordCompat {
    private static final AccessibilityRecordImpl IMPL;
    private final Object mRecord;

    interface AccessibilityRecordImpl {
        Object obtain();

        void setFromIndex(Object obj, int i);

        void setItemCount(Object obj, int i);

        void setScrollable(Object obj, boolean z);

        void setSource(Object obj, View view);

        void setToIndex(Object obj, int i);
    }

    static class AccessibilityRecordStubImpl implements AccessibilityRecordImpl {
        AccessibilityRecordStubImpl() {
        }

        public Object obtain() {
            return null;
        }

        public void setFromIndex(Object record, int fromIndex) {
        }

        public void setItemCount(Object record, int itemCount) {
        }

        public void setScrollable(Object record, boolean scrollable) {
        }

        public void setSource(Object record, View source) {
        }

        public void setToIndex(Object record, int toIndex) {
        }
    }

    static class AccessibilityRecordIcsImpl extends AccessibilityRecordStubImpl {
        AccessibilityRecordIcsImpl() {
        }

        public Object obtain() {
            return AccessibilityRecordCompatIcs.obtain();
        }

        public void setFromIndex(Object record, int fromIndex) {
            AccessibilityRecordCompatIcs.setFromIndex(record, fromIndex);
        }

        public void setItemCount(Object record, int itemCount) {
            AccessibilityRecordCompatIcs.setItemCount(record, itemCount);
        }

        public void setScrollable(Object record, boolean scrollable) {
            AccessibilityRecordCompatIcs.setScrollable(record, scrollable);
        }

        public void setSource(Object record, View source) {
            AccessibilityRecordCompatIcs.setSource(record, source);
        }

        public void setToIndex(Object record, int toIndex) {
            AccessibilityRecordCompatIcs.setToIndex(record, toIndex);
        }
    }

    static class AccessibilityRecordIcsMr1Impl extends AccessibilityRecordIcsImpl {
        AccessibilityRecordIcsMr1Impl() {
        }
    }

    static class AccessibilityRecordJellyBeanImpl extends AccessibilityRecordIcsMr1Impl {
        AccessibilityRecordJellyBeanImpl() {
        }
    }

    static {
        if (VERSION.SDK_INT >= 16) {
            IMPL = new AccessibilityRecordJellyBeanImpl();
        } else if (VERSION.SDK_INT >= 15) {
            IMPL = new AccessibilityRecordIcsMr1Impl();
        } else if (VERSION.SDK_INT >= 14) {
            IMPL = new AccessibilityRecordIcsImpl();
        } else {
            IMPL = new AccessibilityRecordStubImpl();
        }
    }

    public AccessibilityRecordCompat(Object record) {
        this.mRecord = record;
    }

    public static AccessibilityRecordCompat obtain() {
        return new AccessibilityRecordCompat(IMPL.obtain());
    }

    public void setSource(View source) {
        IMPL.setSource(this.mRecord, source);
    }

    public void setScrollable(boolean scrollable) {
        IMPL.setScrollable(this.mRecord, scrollable);
    }

    public void setItemCount(int itemCount) {
        IMPL.setItemCount(this.mRecord, itemCount);
    }

    public void setFromIndex(int fromIndex) {
        IMPL.setFromIndex(this.mRecord, fromIndex);
    }

    public void setToIndex(int toIndex) {
        IMPL.setToIndex(this.mRecord, toIndex);
    }

    public int hashCode() {
        return this.mRecord == null ? 0 : this.mRecord.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        AccessibilityRecordCompat other = (AccessibilityRecordCompat) obj;
        if (this.mRecord == null) {
            if (other.mRecord != null) {
                return false;
            }
            return true;
        } else if (this.mRecord.equals(other.mRecord)) {
            return true;
        } else {
            return false;
        }
    }
}
