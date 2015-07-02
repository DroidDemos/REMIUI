package com.xiaomi.xmsf.push.service.notification;

import android.app.PendingIntent;
import android.content.Context;
import android.text.TextUtils;
import android.widget.RemoteViews;
import com.xiaomi.xmsf.R;

public class NotificationBaseRemoteView extends RemoteViews {
    protected Context mContext;

    public NotificationBaseRemoteView(Context context) {
        super(context.getPackageName(), R.layout.notification_base_layout);
        this.mContext = context;
    }

    protected NotificationBaseRemoteView(String packageName, int layoutId) {
        super(packageName, layoutId);
    }

    public void setIcon(int srcId) {
        setImageViewResource(16908294, srcId);
    }

    public void setTitles(String title, String subTitle) {
        if (!TextUtils.isEmpty(subTitle)) {
            setTextViewText(R.id.sub_title, subTitle);
            setViewVisibility(R.id.sub_title, 0);
        }
        setTextViewText(R.id.title, title);
    }

    public void setActionButton(String text, PendingIntent pendingIntent) {
        if (text != null) {
            text = text.trim();
        }
        if (TextUtils.isEmpty(text)) {
            setViewVisibility(R.id.action_button, 8);
            return;
        }
        setTextViewText(R.id.action_button, text);
        if (pendingIntent != null) {
            setOnClickPendingIntent(R.id.action_button, pendingIntent);
        }
        setViewVisibility(R.id.action_button, 0);
    }
}
