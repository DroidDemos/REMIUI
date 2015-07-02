package com.google.android.gms.internal;

import android.app.AlertDialog.Builder;
import android.app.DownloadManager;
import android.app.DownloadManager.Request;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.webkit.URLUtil;
import com.google.android.gms.R;
import com.google.android.gms.common.util.m;
import java.util.Map;
import org.json.JSONObject;

@fd
public class do {
    private final Context mContext;
    private final gz mr;
    private final Map<String, String> re;

    public do(gz gzVar, Map<String, String> map) {
        this.mr = gzVar;
        this.re = map;
        this.mContext = gzVar.dI();
    }

    String G(String str) {
        return Uri.parse(str).getLastPathSegment();
    }

    Request c(String str, String str2) {
        Request request = new Request(Uri.parse(str));
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES, str2);
        if (m.jk()) {
            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(1);
        } else {
            request.setShowRunningNotification(true);
        }
        return request;
    }

    public void execute() {
        if (!new bq(this.mContext).bq()) {
            gw.w("Store picture feature is not supported on this device.");
        } else if (TextUtils.isEmpty((CharSequence) this.re.get("iurl"))) {
            gw.w("Image url cannot be empty.");
        } else {
            final String str = (String) this.re.get("iurl");
            if (URLUtil.isValidUrl(str)) {
                final String G = G(str);
                if (gn.S(G)) {
                    Builder builder = new Builder(this.mContext);
                    builder.setTitle(gf.c(R.string.store_picture_title, "Save image"));
                    builder.setMessage(gf.c(R.string.store_picture_message, "Allow Ad to store image in Picture gallery?"));
                    builder.setPositiveButton(gf.c(R.string.accept, "Accept"), new OnClickListener(this) {
                        final /* synthetic */ do rs;

                        public void onClick(DialogInterface dialog, int which) {
                            try {
                                ((DownloadManager) this.rs.mContext.getSystemService("download")).enqueue(this.rs.c(str, G));
                            } catch (IllegalStateException e) {
                                gw.i("Could not store picture.");
                            }
                        }
                    });
                    builder.setNegativeButton(gf.c(R.string.decline, "Decline"), new OnClickListener(this) {
                        final /* synthetic */ do rs;

                        {
                            this.rs = r1;
                        }

                        public void onClick(DialogInterface dialog, int which) {
                            this.rs.mr.b("onStorePictureCanceled", new JSONObject());
                        }
                    });
                    builder.create().show();
                    return;
                }
                gw.w("Image type not recognized:");
                return;
            }
            gw.w("Invalid image url:" + str);
        }
    }
}
