package org.keyczar;

import com.google.gson.annotations.Expose;
import org.keyczar.enums.KeyStatus;
import org.keyczar.util.Util;

public class KeyVersion {
    @Expose
    private boolean exportable;
    @Expose
    private KeyStatus status;
    @Expose
    private int versionNumber;

    private KeyVersion() {
        this.exportable = false;
        this.status = KeyStatus.ACTIVE;
        this.versionNumber = 0;
    }

    public KeyVersion(int v, KeyStatus s, boolean export) {
        this.exportable = false;
        this.status = KeyStatus.ACTIVE;
        this.versionNumber = 0;
        this.versionNumber = v;
        this.status = s;
        this.exportable = export;
    }

    public String toString() {
        return Util.gson().toJson((Object) this);
    }

    public boolean equals(Object o) {
        if (!(o instanceof KeyVersion)) {
            return false;
        }
        if (getVersionNumber() == ((KeyVersion) o).getVersionNumber()) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.versionNumber;
    }

    public KeyStatus getStatus() {
        return this.status;
    }

    public int getVersionNumber() {
        return this.versionNumber;
    }
}
