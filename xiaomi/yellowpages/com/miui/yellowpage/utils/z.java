package com.miui.yellowpage.utils;

import android.app.DownloadManager;
import android.app.DownloadManager.Query;
import android.app.DownloadManager.Request;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.Toast;
import com.alipay.sdk.cons.MiniDefine;
import com.alipay.sdk.data.Response;
import com.miui.yellowpage.R;
import com.miui.yellowpage.base.reference.RefMethods;
import com.miui.yellowpage.base.utils.Log;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import miui.os.Environment;

/* compiled from: Download */
public class z {
    public static final File Cc;

    static {
        Cc = new File(Environment.getExternalStorageMiuiDirectory(), "YellowPage");
    }

    public static void e(Context context, String str, String str2) {
        if (!gP()) {
            Toast.makeText(context, R.string.download_failed_storage, 1).show();
        } else if (!TextUtils.isEmpty(str) && context != null) {
            Uri parse = Uri.parse(str);
            if (TextUtils.isEmpty(str2)) {
                str2 = parse.getLastPathSegment();
            }
            if (TextUtils.isEmpty(str2)) {
                str2 = new SimpleDateFormat("yyyy-MM-dd-HHmmss", Locale.US).format(new Date(System.currentTimeMillis()));
            }
            DownloadManager downloadManager = (DownloadManager) context.getSystemService("download");
            Request request = new Request(parse);
            request.setDestinationUri(Uri.fromFile(new File(Cc, str2)));
            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(1);
            long enqueue = downloadManager.enqueue(request);
            IntentFilter intentFilter = new IntentFilter("android.intent.action.DOWNLOAD_COMPLETE");
            BroadcastReceiver e = new E(enqueue, downloadManager);
            Toast.makeText(context, R.string.downloading, 0).show();
            context.registerReceiver(e, intentFilter);
        }
    }

    private static void a(DownloadManager downloadManager, Context context, long j) {
        Query query = new Query();
        query.setFilterById(new long[]{j});
        Cursor query2 = downloadManager.query(query);
        if (query2 != null) {
            try {
                if (query2.moveToNext()) {
                    int i = query2.getInt(query2.getColumnIndex(MiniDefine.b));
                    if (i == 8) {
                        Toast.makeText(context, context.getString(R.string.download_completed, new Object[]{RefMethods.Environment.getMIUIExternalStorageDirectory().getName() + "/" + "YellowPage"}), 1).show();
                    } else if (i == 16) {
                        g(context, query2.getInt(query2.getColumnIndex("reason")));
                    }
                }
                query2.close();
            } catch (Throwable th) {
                query2.close();
            }
        }
    }

    private static void g(Context context, int i) {
        switch (i) {
            case 400:
            case 401:
            case 402:
            case 403:
            case 404:
            case 405:
            case 406:
            case 407:
            case 408:
            case 409:
            case 411:
            case 412:
            case 413:
            case 414:
            case 415:
            case 416:
            case 417:
            case Response.a /*1000*/:
            case 1002:
            case 1004:
            case 1005:
                Toast.makeText(context, R.string.download_failed_network, 1).show();
                return;
            case 410:
                Toast.makeText(context, R.string.download_failed_gone, 1).show();
                return;
            case 500:
            case 501:
            case 502:
            case Response.b /*503*/:
            case 504:
            case 505:
                Toast.makeText(context, R.string.download_failed_server, 1).show();
                return;
            case 1001:
            case 1007:
                Toast.makeText(context, R.string.download_failed_storage, 1).show();
                return;
            case 1006:
                Toast.makeText(context, R.string.download_failed_space, 1).show();
                return;
            case 1008:
                Toast.makeText(context, R.string.download_failed_cannot_resume, 1).show();
                return;
            case 1009:
                Toast.makeText(context, R.string.download_failed_file_exists, 1).show();
                return;
            case 1010:
                Toast.makeText(context, R.string.download_failed_blocked, 1).show();
                return;
            default:
                Log.e("Download", "download failed, code:" + i);
                Toast.makeText(context, R.string.download_failed, 1).show();
                return;
        }
    }

    public static boolean gP() {
        File file = new File(RefMethods.Environment.getMIUIExternalStorageDirectory(), "YellowPage");
        if (file.exists()) {
            return true;
        }
        return file.mkdirs();
    }
}
