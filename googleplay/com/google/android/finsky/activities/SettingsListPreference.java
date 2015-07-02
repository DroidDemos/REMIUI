package com.google.android.finsky.activities;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.preference.ListPreference;
import android.preference.Preference.BaseSavedState;
import android.text.TextUtils;
import android.util.AttributeSet;

public class SettingsListPreference extends ListPreference {

    public interface SettingsListEntry {
        int getResource();
    }

    private static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR;
        private CharSequence[] entries;
        private CharSequence[] entryValues;
        private CharSequence summary;
        private String value;

        public SavedState(Parcel source) {
            super(source);
            readCharSequenceArray(source, this.entries);
            readCharSequenceArray(source, this.entryValues);
            this.value = source.readString();
            this.summary = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(source);
        }

        public SavedState(Parcelable superState) {
            super(superState);
        }

        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            writeCharSequenceArray(dest, this.entries);
            writeCharSequenceArray(dest, this.entryValues);
            dest.writeString(this.value);
            TextUtils.writeToParcel(this.summary, dest, 0);
        }

        private static void writeCharSequenceArray(Parcel source, CharSequence[] val) {
            if (val != null) {
                source.writeInt(N);
                for (CharSequence writeToParcel : val) {
                    TextUtils.writeToParcel(writeToParcel, source, 0);
                }
                return;
            }
            source.writeInt(-1);
        }

        private static void readCharSequenceArray(Parcel source, CharSequence[] val) {
            int N = source.readInt();
            if (N == val.length) {
                for (int i = 0; i < N; i++) {
                    val[i] = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(source);
                }
                return;
            }
            throw new RuntimeException("bad array lengths");
        }

        static {
            CREATOR = new Creator<SavedState>() {
                public SavedState createFromParcel(Parcel in) {
                    return new SavedState(in);
                }

                public SavedState[] newArray(int size) {
                    return new SavedState[size];
                }
            };
        }
    }

    public SettingsListPreference(Context context) {
        super(context);
    }

    public SettingsListPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setEntriesAndValues(SettingsListEntry[] listEntries) {
        int length = listEntries.length;
        CharSequence[] entries = new CharSequence[length];
        CharSequence[] entryValues = new CharSequence[length];
        for (int i = 0; i < length; i++) {
            SettingsListEntry listEntry = listEntries[i];
            entries[i] = getContext().getString(listEntry.getResource());
            entryValues[i] = listEntry.toString();
        }
        setEntries(entries);
        setEntryValues(entryValues);
    }

    public void setValueAndUpdateSummary(SettingsListEntry entry) {
        setValue(entry.toString());
        updateListPreferenceSummary();
    }

    public void updateListPreferenceSummary() {
        setSummary(getEntry());
    }

    protected void onRestoreInstanceState(Parcelable state) {
        SavedState savedState = (SavedState) state;
        setEntries(savedState.entries);
        setEntryValues(savedState.entryValues);
        setValue(savedState.value);
        setSummary(savedState.summary);
        super.onRestoreInstanceState(savedState.getSuperState());
    }

    protected Parcelable onSaveInstanceState() {
        SavedState state = new SavedState(super.onSaveInstanceState());
        state.entries = getEntries();
        state.entryValues = getEntryValues();
        state.value = getValue();
        state.summary = getSummary();
        return state;
    }
}
