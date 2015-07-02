package com.miui.yellowpage.utils;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.MailTo;
import android.net.Uri;
import android.provider.MiuiSettings.Secure;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import com.alipay.sdk.cons.MiniDefine;
import com.miui.yellowpage.R;
import com.miui.yellowpage.base.model.Image;
import com.miui.yellowpage.base.model.Image.ImageFormat;
import com.miui.yellowpage.base.reference.RefMethods.IntentScope;
import com.miui.yellowpage.base.request.JSONRequest;
import com.miui.yellowpage.base.utils.HostManager;
import com.miui.yellowpage.base.utils.ImageLoader;
import com.miui.yellowpage.base.utils.IntentUtil;
import com.miui.yellowpage.base.utils.Log;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import miui.yellowpage.YellowPageContract.Provider;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: Common */
public class x {
    public static boolean a(Collection collection) {
        return collection == null || collection.size() == 0;
    }

    public static List b(List list, List list2) {
        List linkedList = new LinkedList();
        if (list != null && list.size() > 0) {
            linkedList.addAll(list);
        }
        if (list2 != null && list2.size() > 0) {
            linkedList.addAll(list2);
        }
        return linkedList;
    }

    public static void a(View view, boolean z) {
        InputMethodManager peekInstance = InputMethodManager.peekInstance();
        if (z) {
            peekInstance.viewClicked(view);
            peekInstance.showSoftInput(view, 0);
            return;
        }
        peekInstance.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static Intent a(Context context, MailTo mailTo) {
        if (mailTo == null) {
            return null;
        }
        String str = "com.android.email";
        Intent intent = new Intent("android.intent.action.SEND");
        intent.putExtra("android.intent.extra.EMAIL", new String[]{mailTo.getTo()});
        intent.putExtra("android.intent.extra.TEXT", mailTo.getBody());
        intent.putExtra("android.intent.extra.SUBJECT", mailTo.getSubject());
        intent.putExtra("android.intent.extra.CC", mailTo.getCc());
        intent.setPackage(str);
        intent.setType("text/plain");
        if (IntentUtil.canResolveIntent(context, intent)) {
            return intent;
        }
        intent = new Intent();
        intent.setData(Uri.parse("content://ui.email.android.com/view/mailbox"));
        intent.setPackage(str);
        return intent;
    }

    public static Intent x(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Uri parse = Uri.parse(str);
        String scheme = parse.getScheme();
        Intent a;
        if ("mailto".equalsIgnoreCase(scheme)) {
            a = a(context, MailTo.parse(str));
            a.addCategory("android.intent.category.BROWSABLE");
            return a;
        } else if ("tel".equalsIgnoreCase(scheme)) {
            a = new Intent("android.intent.action.CALL_PRIVILEGED", parse);
            a.setFlags(268435456);
            return IntentScope.processIntentScope(context, a, IntentScope.PACKAGE_NAME_PHONE);
        } else if (TextUtils.equals(scheme, "calendar")) {
            if (TextUtils.equals("event", parse.getHost())) {
                return new Intent("android.intent.action.INSERT", parse);
            }
            return null;
        } else if ("app.xiaomi.com".equalsIgnoreCase(parse.getHost()) && Pattern.matches("/detail/\\d+.*", parse.getPath())) {
            a = new Intent("android.intent.action.VIEW", parse);
            a.addCategory("android.intent.category.BROWSABLE");
            return a;
        } else if ("http".equalsIgnoreCase(scheme) || MiniDefine.aQ.equalsIgnoreCase(scheme)) {
            return null;
        } else {
            a = new Intent("android.intent.action.VIEW", parse);
            a.addCategory("android.intent.category.BROWSABLE");
            return a;
        }
    }

    public static boolean X(Context context) {
        return Secure.isCtaSupported(context.getContentResolver());
    }

    public static void a(Context context, Set set) {
        String join = TextUtils.join(".", set);
        JSONRequest jSONRequest = new JSONRequest(context, HostManager.getProviderBatchUrl());
        jSONRequest.addParam("pids", join);
        if (jSONRequest.getStatus() == 0) {
            try {
                JSONArray jSONArray = new JSONArray(jSONRequest.requestData());
                for (int i = 0; i < jSONArray.length(); i++) {
                    byte[] loadImageBytes;
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    String string = jSONObject.getString("id");
                    String string2 = jSONObject.getString(MiniDefine.l);
                    String string3 = jSONObject.getString("icon");
                    String string4 = jSONObject.getString("transparentIcon");
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("pid", string);
                    contentValues.put(MiniDefine.l, string2);
                    if (!TextUtils.isEmpty(string4)) {
                        Log.d("Common", "load " + string4);
                        int dimension = (int) context.getResources().getDimension(R.dimen.provider_icon_size);
                        loadImageBytes = ImageLoader.getInstance(context).loadImageBytes(new Image(HostManager.getImageUrl(context, string4, dimension, dimension, ImageFormat.PNG)), true);
                        if (loadImageBytes != null) {
                            contentValues.put("icon", loadImageBytes);
                        }
                    }
                    if (!TextUtils.isEmpty(string3)) {
                        Log.d("Common", "load " + string3);
                        int dimension2 = (int) context.getResources().getDimension(R.dimen.provider_big_icon_size);
                        loadImageBytes = ImageLoader.getInstance(context).loadImageBytes(new Image(HostManager.getImageUrl(context, string3, dimension2, dimension2, ImageFormat.PNG)), true);
                        if (loadImageBytes != null) {
                            contentValues.put("icon_big", loadImageBytes);
                        }
                    }
                    context.getContentResolver().insert(Provider.CONTENT_URI, contentValues);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public static String b(double d) {
        if (d == ((double) ((int) d))) {
            return String.valueOf((int) d);
        }
        return String.valueOf(d);
    }
}
