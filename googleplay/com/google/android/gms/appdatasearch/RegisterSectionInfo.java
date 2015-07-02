package com.google.android.gms.appdatasearch;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class RegisterSectionInfo implements SafeParcelable {
    public static final z CREATOR;
    final int[] DU;
    public final Feature[] features;
    public final String format;
    public final boolean indexPrefixes;
    final int mVersionCode;
    public final String name;
    public final boolean noIndex;
    public final String schemaOrgProperty;
    public final String subsectionSeparator;
    public final int weight;

    public static final class Builder {
        private String DV;
        private boolean DW;
        private int DX;
        private boolean DY;
        private String DZ;
        private final List<Feature> Ea;
        private BitSet Eb;
        private String Ec;
        private final String mName;

        public Builder(String name) {
            this.mName = name;
            this.DX = 1;
            this.Ea = new ArrayList();
        }

        public RegisterSectionInfo build() {
            int i = 0;
            int[] iArr = null;
            if (this.Eb != null) {
                iArr = new int[this.Eb.cardinality()];
                int nextSetBit = this.Eb.nextSetBit(0);
                while (nextSetBit >= 0) {
                    int i2 = i + 1;
                    iArr[i] = nextSetBit;
                    nextSetBit = this.Eb.nextSetBit(nextSetBit + 1);
                    i = i2;
                }
            }
            return new RegisterSectionInfo(this.mName, this.DV, this.DW, this.DX, this.DY, this.DZ, (Feature[]) this.Ea.toArray(new Feature[this.Ea.size()]), iArr, this.Ec);
        }

        public Builder format(String format) {
            this.DV = format;
            return this;
        }

        public Builder noIndex(boolean noIndex) {
            this.DW = noIndex;
            return this;
        }
    }

    static {
        CREATOR = new z();
    }

    RegisterSectionInfo(int versionCode, String name, String format, boolean noIndex, int weight, boolean indexPrefixes, String subsectionSeparator, Feature[] features, int[] semanticLabels, String schemaOrgProperty) {
        this.mVersionCode = versionCode;
        this.name = name;
        this.format = format;
        this.noIndex = noIndex;
        this.weight = weight;
        this.indexPrefixes = indexPrefixes;
        this.subsectionSeparator = subsectionSeparator;
        this.features = features;
        this.DU = semanticLabels;
        this.schemaOrgProperty = schemaOrgProperty;
    }

    RegisterSectionInfo(String name, String format, boolean noIndex, int weight, boolean indexPrefixes, String subsectionSeparator, Feature[] features, int[] semanticLabels, String schemaOrgProperty) {
        this(2, name, format, noIndex, weight, indexPrefixes, subsectionSeparator, features, semanticLabels, schemaOrgProperty);
    }

    public int describeContents() {
        z zVar = CREATOR;
        return 0;
    }

    public boolean equals(Object object) {
        if (!(object instanceof RegisterSectionInfo)) {
            return false;
        }
        RegisterSectionInfo registerSectionInfo = (RegisterSectionInfo) object;
        return this.name.equals(registerSectionInfo.name) && this.format.equals(registerSectionInfo.format) && this.noIndex == registerSectionInfo.noIndex;
    }

    public void writeToParcel(Parcel out, int flags) {
        z zVar = CREATOR;
        z.a(this, out, flags);
    }
}
