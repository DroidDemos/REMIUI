package com.xiaomi.xmsf.push.service.notification;

import android.content.Context;
import android.graphics.Bitmap;
import com.xiaomi.xmsf.R;

public class NotificationBigRemoteView extends NotificationBaseRemoteView {
    public NotificationBigRemoteView(Context context) {
        super(context.getPackageName(), R.layout.notification_big_picture_layout);
    }

    public void setBigPicture(Bitmap bitmap) {
        setImageViewBitmap(R.id.big_picture, bitmap);
    }
}
