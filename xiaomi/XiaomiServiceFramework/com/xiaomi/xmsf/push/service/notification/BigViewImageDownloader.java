package com.xiaomi.xmsf.push.service.notification;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import com.xiaomi.xmsf.push.service.MyLog;
import java.io.InputStream;
import java.net.URL;

public class BigViewImageDownloader extends AsyncTask<Object, Object, Bitmap> {
    private Callback mCB;
    private String mImageUrl;

    public interface Callback {
        void onFinish(Bitmap bitmap);
    }

    public BigViewImageDownloader(String imageUrl, Callback cb) {
        this.mImageUrl = imageUrl;
        this.mCB = cb;
    }

    protected Bitmap doInBackground(Object... params) {
        if (this.mCB != null) {
            try {
                InputStream in = new URL(this.mImageUrl).openConnection().getInputStream();
                Bitmap ret = BitmapFactory.decodeStream(in);
                in.close();
                return ret;
            } catch (Throwable e) {
                MyLog.e(e);
            } catch (Throwable e2) {
                MyLog.e(e2);
            }
        }
        return null;
    }

    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        if (bitmap != null) {
            this.mCB.onFinish(bitmap);
        }
    }
}
