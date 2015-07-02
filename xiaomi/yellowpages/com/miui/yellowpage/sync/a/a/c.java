package com.miui.yellowpage.sync.a.a;

import android.content.ContentProviderOperation;
import android.content.ContentProviderOperation.Builder;
import android.content.Context;
import android.text.TextUtils;
import com.alipay.sdk.cons.MiniDefine;
import com.miui.yellowpage.R;
import com.miui.yellowpage.base.model.Image;
import com.miui.yellowpage.base.model.Image.ImageFormat;
import com.miui.yellowpage.base.request.JSONRequest;
import com.miui.yellowpage.base.utils.HostManager;
import com.miui.yellowpage.base.utils.ImageLoader;
import com.miui.yellowpage.base.utils.Log;
import java.io.IOException;
import miui.provider.BatchOperation;
import miui.yellowpage.YellowPageContract.Provider;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: Provider */
public class c extends d {
    protected JSONRequest m(Context context) {
        JSONRequest jSONRequest = new JSONRequest(context, HostManager.getProviderUrl());
        jSONRequest.addParam("version", String.valueOf(D(context)));
        return jSONRequest;
    }

    protected String n(Context context) {
        return "provider";
    }

    public boolean e(Context context, String str) {
        JSONArray jSONArray = new JSONArray(str);
        int dimension = (int) context.getResources().getDimension(R.dimen.provider_big_icon_size);
        int dimension2 = (int) context.getResources().getDimension(R.dimen.provider_icon_size);
        Log.d("PullTask", "big icon size:" + dimension);
        Log.d("PullTask", " icon size:" + dimension2);
        BatchOperation batchOperation = new BatchOperation(context.getContentResolver(), "miui.yellowpage");
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            long j = jSONObject.getLong("id");
            String string = jSONObject.getString(MiniDefine.l);
            String string2 = jSONObject.getString("icon");
            String string3 = jSONObject.getString("transparentIcon");
            Builder newInsert = ContentProviderOperation.newInsert(Provider.CONTENT_URI);
            newInsert.withValue("pid", Long.valueOf(j));
            newInsert.withValue(MiniDefine.l, string);
            if (!TextUtils.isEmpty(string2)) {
                Log.d("PullTask", "load " + string2);
                Object loadImageBytes = ImageLoader.getInstance(context).loadImageBytes(new Image(HostManager.getImageUrl(context, string2, dimension, dimension, ImageFormat.PNG)), true);
                if (loadImageBytes != null) {
                    newInsert.withValue("icon_big", loadImageBytes);
                } else {
                    throw new IOException("cannot load image");
                }
            }
            if (!TextUtils.isEmpty(string3)) {
                Log.d("PullTask", "load " + string3);
                Object loadImageBytes2 = ImageLoader.getInstance(context).loadImageBytes(new Image(HostManager.getImageUrl(context, string3, dimension2, dimension2, ImageFormat.PNG)), true);
                if (loadImageBytes2 != null) {
                    newInsert.withValue("icon", loadImageBytes2);
                } else {
                    throw new IOException("cannot load image");
                }
            }
            batchOperation.add(newInsert.build());
            if (batchOperation.size() > 100) {
                batchOperation.execute();
            }
        }
        if (batchOperation.size() > 0) {
            batchOperation.execute();
        }
        e(context, this.mVersion);
        return false;
    }
}
