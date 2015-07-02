package com.google.android.finsky.widget.recommendation;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.utils.IntentUtils;
import com.google.android.finsky.widget.AdvanceFlipperReceiver;

public class OpenRecommendationReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        Intent viewIntent = (Intent) intent.getParcelableExtra("viewIntent");
        viewIntent.addFlags(268435456);
        context.startActivity(viewIntent);
        AdvanceFlipperReceiver.advance(context, intent.getIntExtra("appWidgetId", 0));
    }

    public static PendingIntent getIntent(Context context, Document document, int widgetBackendId, int appWidgetId) {
        Intent viewIntent = IntentUtils.createViewDocumentIntent(context, document);
        if (TextUtils.isEmpty(RecommendationsStore.getRecsWidgetUrl(widgetBackendId))) {
            return null;
        }
        Intent intent = new Intent(context, OpenRecommendationReceiver.class);
        intent.setData(Uri.parse("content://market/open-rec/" + appWidgetId + "/" + document.getDocId()));
        intent.putExtra("viewIntent", viewIntent);
        intent.putExtra("appWidgetId", appWidgetId);
        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }
}
