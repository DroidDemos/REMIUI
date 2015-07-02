package org.keyczar;

import com.google.gson.annotations.Expose;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.keyczar.enums.KeyPurpose;
import org.keyczar.interfaces.KeyType;
import org.keyczar.util.Util;

public class KeyMetadata {
    @Expose
    boolean encrypted;
    @Expose
    String name;
    @Expose
    KeyPurpose purpose;
    @Expose
    KeyType type;
    protected Map<Integer, KeyVersion> versionMap;
    @Expose
    List<KeyVersion> versions;

    public KeyMetadata() {
        this.name = "";
        this.purpose = KeyPurpose.TEST;
        this.type = DefaultKeyType.TEST;
        this.versions = new ArrayList();
        this.encrypted = false;
        this.versionMap = new HashMap();
    }

    public KeyMetadata(String n, KeyPurpose p, KeyType t) {
        this.name = "";
        this.purpose = KeyPurpose.TEST;
        this.type = DefaultKeyType.TEST;
        this.versions = new ArrayList();
        this.encrypted = false;
        this.versionMap = new HashMap();
        this.name = n;
        this.purpose = p;
        this.type = t;
    }

    public String toString() {
        return Util.gson().toJson((Object) this);
    }

    public boolean addVersion(KeyVersion version) {
        int versionNumber = version.getVersionNumber();
        if (this.versionMap.containsKey(Integer.valueOf(versionNumber))) {
            return false;
        }
        this.versionMap.put(Integer.valueOf(versionNumber), version);
        this.versions.add(version);
        return true;
    }

    public KeyPurpose getPurpose() {
        return this.purpose;
    }

    public KeyType getType() {
        return this.type;
    }

    public boolean isEncrypted() {
        return this.encrypted;
    }

    public List<KeyVersion> getVersions() {
        return this.versions;
    }

    public static KeyMetadata read(String jsonString) {
        KeyMetadata kmd = (KeyMetadata) Util.gson().fromJson(jsonString, KeyMetadata.class);
        for (KeyVersion version : kmd.getVersions()) {
            kmd.versionMap.put(Integer.valueOf(version.getVersionNumber()), version);
        }
        return kmd;
    }
}
