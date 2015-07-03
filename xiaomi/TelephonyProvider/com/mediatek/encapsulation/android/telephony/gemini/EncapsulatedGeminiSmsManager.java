package com.mediatek.encapsulation.android.telephony.gemini;

import android.telephony.SmsMessage;
import com.mediatek.telephony.SmsManagerEx;
import java.util.ArrayList;

public class EncapsulatedGeminiSmsManager {
    public static ArrayList<SmsMessage> getAllMessagesFromIccGemini(int simId) {
        return SmsManagerEx.getDefault().getAllMessagesFromIcc(simId);
    }

    public static boolean deleteMessageFromIccGemini(int messageIndex, int simId) {
        return SmsManagerEx.getDefault().deleteMessageFromIcc(messageIndex, simId);
    }
}
