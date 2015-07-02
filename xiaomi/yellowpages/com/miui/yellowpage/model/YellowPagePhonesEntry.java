package com.miui.yellowpage.model;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.miui.yellowpage.R;
import com.miui.yellowpage.base.model.YellowPage;
import com.miui.yellowpage.base.model.YellowPageDataEntry;
import com.miui.yellowpage.base.reference.RefMethods.IntentScope;
import com.miui.yellowpage.model.YellowPageDataDetailEntry.Type;
import java.util.ArrayList;
import java.util.List;
import miui.telephony.PhoneNumberUtils.PhoneNumber;
import miui.yellowpage.YellowPagePhone;

public class YellowPagePhonesEntry extends YellowPageDataDetailEntry {
    public YellowPagePhonesEntry() {
        super.a(Type.PHONE);
    }

    public static ArrayList a(Context context, YellowPage yellowPage) {
        ArrayList arrayList = new ArrayList();
        List<YellowPagePhone> phones = yellowPage.getPhones();
        if (phones == null || phones.size() <= 0) {
            return arrayList;
        }
        for (YellowPagePhone a : phones) {
            arrayList.add(a(context, a));
        }
        return arrayList;
    }

    public static YellowPageDataEntry a(Context context, YellowPagePhone yellowPagePhone) {
        Intent intent = new Intent("android.intent.action.SENDTO", Uri.fromParts("smsto", yellowPagePhone.getNumber(), null));
        Intent processIntentScope = IntentScope.processIntentScope(context, new Intent("android.intent.action.CALL_PRIVILEGED", Uri.fromParts("tel", yellowPagePhone.getNumber(), null)), IntentScope.PACKAGE_NAME_PHONE);
        Object location = PhoneNumber.getLocation(context, yellowPagePhone.getNumber());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(yellowPagePhone.getTag());
        if (!TextUtils.isEmpty(location)) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(context.getString(R.string.phone_info_divider));
            }
            stringBuilder.append(location);
        }
        return new YellowPagePhonesEntry().aH(stringBuilder.toString()).a(context.getResources().getDrawable(R.drawable.ic_send_sms)).c(processIntentScope).d(intent).aG(yellowPagePhone.getNumber());
    }
}
