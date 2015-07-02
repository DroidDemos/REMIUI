package com.miui.yellowpage.utils;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.webkit.ValueCallback;
import android.widget.Toast;
import com.miui.yellowpage.R;
import com.miui.yellowpage.ui.bw;
import java.io.File;

/* compiled from: WebUploadHandler */
public class H {
    private ValueCallback GL;
    private String GM;
    private boolean GN;
    private bw GO;
    private boolean mHandled;

    public H(bw bwVar) {
        this.GO = bwVar;
    }

    private Activity getActivity() {
        return this.GO.getActivity();
    }

    public void a(int i, Intent intent) {
        if (i == 0 && this.GN) {
            this.GN = false;
            return;
        }
        Uri data = (intent == null || i != -1) ? null : intent.getData();
        if (data == null && intent == null && i == -1) {
            File file = new File(this.GM);
            if (file.exists()) {
                data = Uri.fromFile(file);
                getActivity().sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", data));
            }
        }
        if (data != null) {
            String uri = data.toString();
            if (uri.startsWith("file")) {
                this.GL.onReceiveValue(Uri.parse(uri));
            } else {
                new Thread(new b(this, data)).start();
            }
        } else {
            this.GL.onReceiveValue(null);
        }
        this.mHandled = true;
        this.GN = false;
    }

    public void openFileChooser(ValueCallback valueCallback, String str, String str2) {
        String str3 = "image/*";
        str3 = "video/*";
        str3 = "audio/*";
        str3 = "capture";
        str3 = "camera";
        str3 = "filesystem";
        str3 = "camcorder";
        str3 = "microphone";
        str3 = "filesystem";
        if (this.GL == null) {
            this.GL = valueCallback;
            String[] split = str.split(";");
            String str4 = split[0];
            if (str2.length() > 0) {
                str3 = str2;
            }
            if (str2.equals("filesystem")) {
                for (String split2 : split) {
                    String[] split3 = split2.split("=");
                    if (split3.length == 2 && "capture".equals(split3[0])) {
                        str3 = split3[1];
                    }
                }
            }
            this.GM = null;
            Intent a;
            if (str4.equals("image/*")) {
                if (str3.equals("camera")) {
                    startActivity(hH());
                    return;
                }
                a = a(hH());
                a.putExtra("android.intent.extra.INTENT", ci("image/*"));
                startActivity(a);
            } else if (str4.equals("video/*")) {
                if (str3.equals("camcorder")) {
                    startActivity(hI());
                    return;
                }
                a = a(hI());
                a.putExtra("android.intent.extra.INTENT", ci("video/*"));
                startActivity(a);
            } else if (!str4.equals("audio/*")) {
                startActivity(hG());
            } else if (str3.equals("microphone")) {
                startActivity(hJ());
            } else {
                a = a(hJ());
                a.putExtra("android.intent.extra.INTENT", ci("audio/*"));
                startActivity(a);
            }
        }
    }

    private void startActivity(Intent intent) {
        bw bwVar;
        try {
            bwVar = this.GO;
            bw bwVar2 = this.GO;
            bwVar.startActivityForResult(intent, bw.FILE_CHOOSER_RESULT_CODE);
        } catch (ActivityNotFoundException e) {
            try {
                this.GN = true;
                bwVar = this.GO;
                Intent hG = hG();
                bw bwVar3 = this.GO;
                bwVar.startActivityForResult(hG, bw.FILE_CHOOSER_RESULT_CODE);
            } catch (ActivityNotFoundException e2) {
                Toast.makeText(getActivity(), R.string.uploads_disabled, 1).show();
            }
        }
    }

    private Intent hG() {
        Parcelable intent = new Intent("android.intent.action.GET_CONTENT");
        intent.addCategory("android.intent.category.OPENABLE");
        intent.setType("*/*");
        Intent a = a(hH(), hJ());
        a.putExtra("android.intent.extra.INTENT", intent);
        return a;
    }

    private Intent a(Intent... intentArr) {
        Intent intent = new Intent("android.intent.action.CHOOSER");
        intent.putExtra("android.intent.extra.INITIAL_INTENTS", intentArr);
        intent.putExtra("android.intent.extra.TITLE", getActivity().getResources().getString(R.string.choose_upload));
        return intent;
    }

    private Intent ci(String str) {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.addCategory("android.intent.category.OPENABLE");
        intent.setType(str);
        return intent;
    }

    private Intent hH() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        File file = z.Cc;
        file.mkdirs();
        this.GM = file.getAbsolutePath() + File.separator + System.currentTimeMillis() + ".jpg";
        intent.putExtra("output", Uri.fromFile(new File(this.GM)));
        return intent;
    }

    private Intent hI() {
        return new Intent("android.media.action.VIDEO_CAPTURE");
    }

    private Intent hJ() {
        return new Intent("android.provider.MediaStore.RECORD_SOUND");
    }
}
