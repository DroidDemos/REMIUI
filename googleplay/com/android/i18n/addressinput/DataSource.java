package com.android.i18n.addressinput;

public interface DataSource {
    AddressVerificationNodeData get(String str);

    AddressVerificationNodeData getDefaultData(String str);
}
