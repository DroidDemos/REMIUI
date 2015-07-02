package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@fd
public final class ch {
    public static final ci pR;
    public static final ci pS;
    public static final ci pT;
    public static final ci pU;
    public static final ci pV;
    public static final ci pW;
    public static final ci pX;
    public static final ci pY;
    public static final ci pZ;
    public static final ci qa;

    static {
        pR = new ci() {
            public void a(gz gzVar, Map<String, String> map) {
            }
        };
        pS = new ci() {
            public void a(gz gzVar, Map<String, String> map) {
                String str = (String) map.get("urls");
                if (TextUtils.isEmpty(str)) {
                    gw.w("URLs missing in canOpenURLs GMSG.");
                    return;
                }
                String[] split = str.split(",");
                Map hashMap = new HashMap();
                PackageManager packageManager = gzVar.getContext().getPackageManager();
                for (String str2 : split) {
                    String[] split2 = str2.split(";", 2);
                    hashMap.put(str2, Boolean.valueOf(packageManager.resolveActivity(new Intent(split2.length > 1 ? split2[1].trim() : "android.intent.action.VIEW", Uri.parse(split2[0].trim())), 65536) != null));
                }
                gzVar.a("openableURLs", hashMap);
            }
        };
        pT = new ci() {
            public void a(gz gzVar, Map<String, String> map) {
                PackageManager packageManager = gzVar.getContext().getPackageManager();
                try {
                    try {
                        JSONArray jSONArray = new JSONObject((String) map.get("data")).getJSONArray("intents");
                        JSONObject jSONObject = new JSONObject();
                        for (int i = 0; i < jSONArray.length(); i++) {
                            try {
                                JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                                String optString = jSONObject2.optString("id");
                                Object optString2 = jSONObject2.optString("u");
                                Object optString3 = jSONObject2.optString("i");
                                Object optString4 = jSONObject2.optString("m");
                                Object optString5 = jSONObject2.optString("p");
                                Object optString6 = jSONObject2.optString("c");
                                jSONObject2.optString("f");
                                jSONObject2.optString("e");
                                Intent intent = new Intent();
                                if (!TextUtils.isEmpty(optString2)) {
                                    intent.setData(Uri.parse(optString2));
                                }
                                if (!TextUtils.isEmpty(optString3)) {
                                    intent.setAction(optString3);
                                }
                                if (!TextUtils.isEmpty(optString4)) {
                                    intent.setType(optString4);
                                }
                                if (!TextUtils.isEmpty(optString5)) {
                                    intent.setPackage(optString5);
                                }
                                if (!TextUtils.isEmpty(optString6)) {
                                    String[] split = optString6.split("/", 2);
                                    if (split.length == 2) {
                                        intent.setComponent(new ComponentName(split[0], split[1]));
                                    }
                                }
                                try {
                                    jSONObject.put(optString, packageManager.resolveActivity(intent, 65536) != null);
                                } catch (Throwable e) {
                                    gw.e("Error constructing openable urls response.", e);
                                }
                            } catch (Throwable e2) {
                                gw.e("Error parsing the intent data.", e2);
                            }
                        }
                        gzVar.b("openableIntents", jSONObject);
                    } catch (JSONException e3) {
                        gzVar.b("openableIntents", new JSONObject());
                    }
                } catch (JSONException e4) {
                    gzVar.b("openableIntents", new JSONObject());
                }
            }
        };
        pU = new ci() {
            public void a(gz gzVar, Map<String, String> map) {
                String str = (String) map.get("u");
                if (str == null) {
                    gw.w("URL missing from click GMSG.");
                    return;
                }
                Uri b;
                Uri parse = Uri.parse(str);
                try {
                    k dF = gzVar.dF();
                    if (dF != null && dF.isGoogleAdUrl(parse)) {
                        b = dF.b(parse, gzVar.getContext());
                        new gu(gzVar.getContext(), gzVar.dG().wR, b.toString()).start();
                    }
                } catch (l e) {
                    gw.w("Unable to append parameter to URL: " + str);
                }
                b = parse;
                new gu(gzVar.getContext(), gzVar.dG().wR, b.toString()).start();
            }
        };
        pV = new ci() {
            public void a(gz gzVar, Map<String, String> map) {
                du dC = gzVar.dC();
                if (dC == null) {
                    gw.w("A GMSG tried to close something that wasn't an overlay.");
                } else {
                    dC.close();
                }
            }
        };
        pW = new ci() {
            public void a(gz gzVar, Map<String, String> map) {
                gzVar.q("1".equals(map.get("custom_close")));
            }
        };
        pX = new ci() {
            public void a(gz gzVar, Map<String, String> map) {
                String str = (String) map.get("u");
                if (str == null) {
                    gw.w("URL missing from httpTrack GMSG.");
                } else {
                    new gu(gzVar.getContext(), gzVar.dG().wR, str).start();
                }
            }
        };
        pY = new ci() {
            public void a(gz gzVar, Map<String, String> map) {
                gw.i("Received log message: " + ((String) map.get("string")));
            }
        };
        pZ = new ci() {
            public void a(gz gzVar, Map<String, String> map) {
                String str = (String) map.get("ty");
                String str2 = (String) map.get("td");
                try {
                    int parseInt = Integer.parseInt((String) map.get("tx"));
                    int parseInt2 = Integer.parseInt(str);
                    int parseInt3 = Integer.parseInt(str2);
                    k dF = gzVar.dF();
                    if (dF != null) {
                        dF.C().a(parseInt, parseInt2, parseInt3);
                    }
                } catch (NumberFormatException e) {
                    gw.w("Could not parse touch parameters from gmsg.");
                }
            }
        };
        qa = new co();
    }
}
