package com.miui.yellowpage.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.widget.Toast;
import com.miui.yellowpage.R;
import com.miui.yellowpage.base.utils.YellowPageImgLoader;
import miui.graphics.BitmapFactory.CropOption;

/* compiled from: InstallYellowPageShortCutIconActivity */
class c extends AsyncTask {
    private ProgressDialog gS;
    final /* synthetic */ ac gT;

    c(ac acVar) {
        this.gT = acVar;
    }

    protected /* bridge */ /* synthetic */ Object doInBackground(Object[] objArr) {
        return a((Void[]) objArr);
    }

    protected /* bridge */ /* synthetic */ void onPostExecute(Object obj) {
        a((Bitmap) obj);
    }

    protected void onPreExecute() {
        this.gS = new ProgressDialog(this.gT.we);
        this.gS.setTitle(R.string.sending_shortcut);
        this.gS.setCancelable(false);
        this.gS.show();
    }

    protected void a(Bitmap bitmap) {
        this.gS.dismiss();
        if (bitmap == null) {
            Toast.makeText(this.gT.we, R.string.send_shortcut_failed, 0).show();
            this.gT.we.finish();
            return;
        }
        Parcelable intent = new Intent("android.intent.action.VIEW");
        intent.putExtra("source", "launcher");
        intent.addFlags(268468224);
        intent.setData(Uri.parse("yellowpage://details?id=" + this.gT.we.EW));
        Intent intent2 = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
        intent2.putExtra("android.intent.extra.shortcut.ICON", bitmap);
        intent2.putExtra("android.intent.extra.shortcut.INTENT", intent);
        intent2.putExtra("android.intent.extra.CUSTOMIZED_ICON_SHORTCUT", true);
        intent2.putExtra("android.intent.extra.shortcut.NAME", this.gT.we.EU);
        this.gT.we.sendBroadcast(intent2);
        Toast.makeText(this.gT.we, R.string.send_shortcut_success, 0).show();
        this.gT.we.finish();
    }

    protected Bitmap a(Void... voidArr) {
        byte[] loadThumbnailByName = YellowPageImgLoader.loadThumbnailByName(this.gT.we, this.gT.we.EV, true);
        if (loadThumbnailByName == null) {
            return null;
        }
        Bitmap decodeByteArray = BitmapFactory.decodeByteArray(loadThumbnailByName, 0, loadThumbnailByName.length);
        int dimensionPixelSize = this.gT.we.getResources().getDimensionPixelSize(R.dimen.shortcut_icon_round_corner);
        return miui.graphics.BitmapFactory.cropBitmap(decodeByteArray, new CropOption(dimensionPixelSize, dimensionPixelSize, 0, 0));
    }
}
