package com.google.android.wallet.instrumentmanager.pub.analytics;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class CreditCardEntryAction implements Parcelable {
    public static final Creator<CreditCardEntryAction> CREATOR;
    public int expDateEntryType;
    public boolean expDateOcrEnabled;
    public boolean expDateRecognizedByOcr;
    public boolean expDateValidationErrorOccurred;
    public int numOcrAttempts;
    public int ocrExitReason;
    public int panEntryType;
    public boolean panOcrEnabled;
    public boolean panRecognizedByOcr;
    public boolean panValidationErrorOccurred;

    public CreditCardEntryAction() {
        this.numOcrAttempts = -1;
    }

    private CreditCardEntryAction(Parcel in) {
        this.numOcrAttempts = -1;
        this.panOcrEnabled = readBooleanFromParcel(in);
        this.panEntryType = in.readInt();
        this.panRecognizedByOcr = readBooleanFromParcel(in);
        this.panValidationErrorOccurred = readBooleanFromParcel(in);
        this.expDateOcrEnabled = readBooleanFromParcel(in);
        this.expDateEntryType = in.readInt();
        this.expDateRecognizedByOcr = readBooleanFromParcel(in);
        this.expDateValidationErrorOccurred = readBooleanFromParcel(in);
        this.numOcrAttempts = in.readInt();
        this.ocrExitReason = in.readInt();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        writeBooleanToParcel(out, this.panOcrEnabled);
        out.writeInt(this.panEntryType);
        writeBooleanToParcel(out, this.panRecognizedByOcr);
        writeBooleanToParcel(out, this.panValidationErrorOccurred);
        writeBooleanToParcel(out, this.expDateOcrEnabled);
        out.writeInt(this.expDateEntryType);
        writeBooleanToParcel(out, this.expDateRecognizedByOcr);
        writeBooleanToParcel(out, this.expDateValidationErrorOccurred);
        out.writeInt(this.numOcrAttempts);
        out.writeInt(this.ocrExitReason);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(256);
        stringBuilder.append("panOcrEnabled: ").append(this.panOcrEnabled).append("\npanEntryType: ").append(this.panEntryType).append("\npanRecognizedByOcr: ").append(this.panRecognizedByOcr).append("\npanValidationErrorOccurred: ").append(this.panValidationErrorOccurred).append("\nexpDateOcrEnabled: ").append(this.expDateOcrEnabled).append("\nexpDateEntryType: ").append(this.expDateEntryType).append("\nexpDateRecognizedByOcr: ").append(this.expDateRecognizedByOcr).append("\nexpDateValidationErrorOccurred: ").append(this.expDateValidationErrorOccurred).append("\nnumOcrAttempts: ").append(this.numOcrAttempts).append("\nocrExitReason").append(this.ocrExitReason);
        return stringBuilder.toString();
    }

    private static void writeBooleanToParcel(Parcel parcel, boolean b) {
        parcel.writeInt(b ? 1 : 0);
    }

    private static boolean readBooleanFromParcel(Parcel parcel) {
        return parcel.readInt() == 1;
    }

    static {
        CREATOR = new Creator<CreditCardEntryAction>() {
            public CreditCardEntryAction createFromParcel(Parcel in) {
                return new CreditCardEntryAction(in);
            }

            public CreditCardEntryAction[] newArray(int size) {
                return new CreditCardEntryAction[size];
            }
        };
    }
}
