package com.miui.yellowpage.model;

import android.location.Address;
import android.os.Bundle;
import android.text.TextUtils;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ExpressAddress extends Address implements Serializable {

    public enum Type {
        ANY,
        SENDER,
        ADDRESSEE
    }

    public class Validator {

        public class Result {
            State DF;
            int DG;

            public enum State {
            }

            public Result() {
                /* JADX: method processing error */
/*
                Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                /*
                r1 = this;
                r1.<init>();
                r0 = com.miui.yellowpage.model.ExpressAddress.Validator.Result.State.PASSED;
                r1.DF = r0;
                return;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.miui.yellowpage.model.ExpressAddress.Validator.Result.<init>():void");
            }

            public com.miui.yellowpage.model.ExpressAddress.Validator.Result.State hf() {
                /* JADX: method processing error */
/*
                Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                /*
                r1 = this;
                r0 = r1.DF;
                return r0;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.miui.yellowpage.model.ExpressAddress.Validator.Result.hf():com.miui.yellowpage.model.ExpressAddress$Validator$Result$State");
            }

            public int hg() {
                /* JADX: method processing error */
/*
                Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                /*
                r1 = this;
                r0 = r1.DG;
                return r0;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.miui.yellowpage.model.ExpressAddress.Validator.Result.hg():int");
            }
        }

        public static com.miui.yellowpage.model.ExpressAddress.Validator.Result f(com.miui.yellowpage.model.ExpressAddress r2) {
            /* JADX: method processing error */
/*
            Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
            /*
            r0 = new com.miui.yellowpage.model.ExpressAddress$Validator$Result;
            r0.<init>();
            r1 = r2.getName();
            r1 = android.text.TextUtils.isEmpty(r1);
            if (r1 == 0) goto L_0x0019;
        L_0x000f:
            r1 = 2131558612; // 0x7f0d00d4 float:1.8742545E38 double:1.0531298823E-314;
            r0.DG = r1;
            r1 = com.miui.yellowpage.model.ExpressAddress.Validator.Result.State.FAILED;
            r0.DF = r1;
        L_0x0018:
            return r0;
        L_0x0019:
            r1 = r2.getPhone();
            r1 = android.text.TextUtils.isEmpty(r1);
            if (r1 == 0) goto L_0x002d;
        L_0x0023:
            r1 = 2131558613; // 0x7f0d00d5 float:1.8742547E38 double:1.053129883E-314;
            r0.DG = r1;
            r1 = com.miui.yellowpage.model.ExpressAddress.Validator.Result.State.FAILED;
            r0.DF = r1;
            goto L_0x0018;
        L_0x002d:
            r1 = r2.eV();
            r1 = android.text.TextUtils.isEmpty(r1);
            if (r1 == 0) goto L_0x0041;
        L_0x0037:
            r1 = 2131558614; // 0x7f0d00d6 float:1.8742549E38 double:1.0531298833E-314;
            r0.DG = r1;
            r1 = com.miui.yellowpage.model.ExpressAddress.Validator.Result.State.FAILED;
            r0.DF = r1;
            goto L_0x0018;
        L_0x0041:
            r1 = r2.eQ();
            r1 = android.text.TextUtils.isEmpty(r1);
            if (r1 == 0) goto L_0x0018;
        L_0x004b:
            r1 = 2131558615; // 0x7f0d00d7 float:1.874255E38 double:1.053129884E-314;
            r0.DG = r1;
            r1 = com.miui.yellowpage.model.ExpressAddress.Validator.Result.State.FAILED;
            r0.DF = r1;
            goto L_0x0018;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.miui.yellowpage.model.ExpressAddress.Validator.f(com.miui.yellowpage.model.ExpressAddress):com.miui.yellowpage.model.ExpressAddress$Validator$Result");
        }
    }

    public ExpressAddress() {
        super(Locale.CHINA);
    }

    public void setStatus(int i) {
        Bundle extras = getExtras();
        if (extras == null) {
            extras = new Bundle();
        }
        extras.putInt("key_status", i);
        setExtras(extras);
    }

    public int getStatus() {
        Bundle extras = getExtras();
        if (extras == null) {
            return 0;
        }
        return extras.getInt("key_status");
    }

    public void setName(String str) {
        Bundle extras = getExtras();
        if (extras == null) {
            extras = new Bundle();
        }
        extras.putString("key_name", str);
        setExtras(extras);
    }

    public String getName() {
        Bundle extras = getExtras();
        if (extras == null) {
            return null;
        }
        return extras.getString("key_name");
    }

    public void setId(String str) {
        Bundle extras = getExtras();
        if (extras == null) {
            extras = new Bundle();
        }
        extras.putString("key_id", str);
        setExtras(extras);
    }

    public String getId() {
        Bundle extras = getExtras();
        if (extras == null) {
            return null;
        }
        return extras.getString("key_id");
    }

    public void be(String str) {
        Bundle extras = getExtras();
        if (extras == null) {
            extras = new Bundle();
        }
        extras.putString("key_full_address", str);
        setExtras(extras);
    }

    public String eQ() {
        Bundle extras = getExtras();
        if (extras == null) {
            return null;
        }
        return extras.getString("key_full_address");
    }

    public void bf(String str) {
        Bundle extras = getExtras();
        if (extras == null) {
            extras = new Bundle();
        }
        extras.putString("key_admin_area_id", str);
        setExtras(extras);
    }

    public String eR() {
        Bundle extras = getExtras();
        if (extras == null) {
            return null;
        }
        return extras.getString("key_admin_area_id");
    }

    public void bg(String str) {
        Bundle extras = getExtras();
        if (extras == null) {
            extras = new Bundle();
        }
        extras.putString("key_sub_admin_area_id", str);
        setExtras(extras);
    }

    public String eS() {
        Bundle extras = getExtras();
        if (extras == null) {
            return null;
        }
        return extras.getString("key_sub_admin_area_id");
    }

    public void bh(String str) {
        Bundle extras = getExtras();
        if (extras == null) {
            extras = new Bundle();
        }
        extras.putString("key_locality_id", str);
        setExtras(extras);
    }

    public String eT() {
        Bundle extras = getExtras();
        if (extras == null) {
            return null;
        }
        return extras.getString("key_locality_id");
    }

    public void bi(String str) {
        Bundle extras = getExtras();
        if (extras == null) {
            extras = new Bundle();
        }
        extras.putString("key_sub_locality_id", str);
        setExtras(extras);
    }

    public String eU() {
        Bundle extras = getExtras();
        if (extras == null) {
            return null;
        }
        return extras.getString("key_sub_locality_id");
    }

    public String bj(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        if (!TextUtils.isEmpty(getAdminArea())) {
            stringBuilder.append(getAdminArea()).append(str);
        }
        if (!TextUtils.isEmpty(getSubAdminArea())) {
            stringBuilder.append(getSubAdminArea()).append(str);
        }
        if (!TextUtils.isEmpty(getLocality())) {
            stringBuilder.append(getLocality()).append(str);
        }
        if (!TextUtils.isEmpty(getSubLocality())) {
            stringBuilder.append(getSubLocality()).append(str);
        }
        return stringBuilder.toString().trim();
    }

    public String eV() {
        return bj(" ");
    }

    public static ArrayList bk(String str) {
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray jSONArray = new JSONArray(str);
            for (int i = 0; i < jSONArray.length(); i++) {
                ExpressAddress j = j(jSONArray.getJSONObject(i));
                if (j != null) {
                    arrayList.add(j);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public static ExpressAddress j(JSONObject jSONObject) {
        ExpressAddress expressAddress = new ExpressAddress();
        expressAddress.setAdminArea(jSONObject.optString("admin_area"));
        expressAddress.bf(jSONObject.optString("admin_area_id"));
        expressAddress.setSubAdminArea(jSONObject.optString("sub_admin_area"));
        expressAddress.bg(jSONObject.optString("sub_admin_area_id"));
        expressAddress.setLocality(jSONObject.optString("locality"));
        expressAddress.bh(jSONObject.optString("locality_id"));
        expressAddress.setSubLocality(jSONObject.optString("sub_locality"));
        expressAddress.bi(jSONObject.optString("sub_locality_id"));
        expressAddress.setCountryCode(jSONObject.optString("country_code"));
        expressAddress.setPhone(jSONObject.optString("phone"));
        expressAddress.setPostalCode(jSONObject.optString("postal_code"));
        expressAddress.setId(jSONObject.optString("address_id"));
        expressAddress.setName(jSONObject.optString("owner"));
        expressAddress.be(jSONObject.optString("address"));
        return expressAddress;
    }

    public JSONObject eW() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("address", eQ());
            jSONObject.put("address_id", getId());
            jSONObject.put("admin_area", getAdminArea());
            jSONObject.put("admin_area_id", eR());
            Object subAdminArea = getSubAdminArea();
            String str = "sub_admin_area";
            if (subAdminArea == null) {
                subAdminArea = ConfigConstant.WIRELESS_FILENAME;
            }
            jSONObject.put(str, subAdminArea);
            subAdminArea = eS();
            str = "sub_admin_area_id";
            if (subAdminArea == null) {
                subAdminArea = ConfigConstant.WIRELESS_FILENAME;
            }
            jSONObject.put(str, subAdminArea);
            jSONObject.put("country_code", getCountryCode());
            jSONObject.put("locality", getLocality());
            jSONObject.put("locality_id", eT());
            jSONObject.put("owner", getName());
            jSONObject.put("phone", getPhone());
            jSONObject.put("postal_code", getPostalCode());
            jSONObject.put("sub_locality", getSubLocality());
            jSONObject.put("sub_locality_id", eU());
            subAdminArea = getSubThoroughfare();
            str = "sub_thoroughfare";
            if (subAdminArea == null) {
                subAdminArea = ConfigConstant.WIRELESS_FILENAME;
            }
            jSONObject.put(str, subAdminArea);
            subAdminArea = getThoroughfare();
            str = "thoroughfare";
            if (subAdminArea == null) {
                subAdminArea = ConfigConstant.WIRELESS_FILENAME;
            }
            jSONObject.put(str, subAdminArea);
            return jSONObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ExpressAddress a(Address address) {
        ExpressAddress expressAddress = new ExpressAddress();
        if (address != null) {
            expressAddress.setAdminArea(address.getAdminArea());
            expressAddress.setSubAdminArea(address.getSubAdminArea());
            expressAddress.setLocality(address.getLocality());
            expressAddress.setSubLocality(address.getSubLocality());
            expressAddress.setCountryCode(address.getCountryCode());
            expressAddress.setCountryName(address.getCountryName());
            expressAddress.setExtras(address.getExtras());
            expressAddress.setFeatureName(address.getFeatureName());
            expressAddress.setPhone(address.getPhone());
            expressAddress.setPostalCode(address.getPostalCode());
            expressAddress.setPremises(address.getPremises());
            expressAddress.setThoroughfare(address.getThoroughfare());
            expressAddress.setSubThoroughfare(address.getSubThoroughfare());
            expressAddress.setUrl(address.getUrl());
            if (address.hasLongitude()) {
                expressAddress.setLongitude(address.getLongitude());
            }
            if (address.hasLatitude()) {
                expressAddress.setLatitude(address.getLatitude());
            }
            Bundle extras = address.getExtras();
            if (extras != null) {
                expressAddress.setName(extras.getString("key_name"));
                expressAddress.setId(extras.getString("key_id"));
                expressAddress.be(extras.getString("key_full_address"));
                expressAddress.setStatus(extras.getInt("key_status"));
            }
        }
        return expressAddress;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !(obj instanceof ExpressAddress)) {
            return false;
        }
        Object id = getId();
        if (TextUtils.isEmpty(id)) {
            return false;
        }
        return id.equals(((ExpressAddress) obj).getId());
    }

    public int hashCode() {
        String id = getId();
        if (id == null) {
            return 0;
        }
        return id.hashCode();
    }
}
