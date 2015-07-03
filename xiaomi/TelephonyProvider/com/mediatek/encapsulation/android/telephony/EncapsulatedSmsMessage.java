package com.mediatek.encapsulation.android.telephony;

import android.telephony.SmsMessage;
import com.android.internal.telephony.SmsHeader;

public class EncapsulatedSmsMessage {
    public static SmsHeader getUserDataHeader(SmsMessage sms) {
        return sms.getUserDataHeader();
    }

    public static String getDestinationAddress(SmsMessage sms) {
        return sms.getDestinationAddress();
    }
}
