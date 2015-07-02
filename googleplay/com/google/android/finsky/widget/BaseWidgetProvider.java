package com.google.android.finsky.widget;

import android.accounts.AccountManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.AuthenticatedActivity;
import com.google.android.finsky.analytics.BackgroundEventBuilder;
import com.google.android.finsky.analytics.PlayStore.WidgetEventData;
import com.google.android.finsky.api.AccountHandler;
import com.google.android.finsky.config.G;
import com.google.android.finsky.utils.FinskyLog;

public abstract class BaseWidgetProvider extends AppWidgetProvider {
    private final String[] LOGGABLE_INTENT_ACTIONS;

    protected abstract int getWidgetClassId();

    protected abstract void updateWidgets(Context context, AppWidgetManager appWidgetManager, int... iArr);

    public BaseWidgetProvider() {
        this.LOGGABLE_INTENT_ACTIONS = new String[]{"android.appwidget.action.APPWIDGET_ENABLED", "android.appwidget.action.APPWIDGET_DISABLED", "android.appwidget.action.APPWIDGET_UPDATE_OPTIONS", "android.appwidget.action.APPWIDGET_DELETED"};
    }

    protected ComponentName getComponentName(Context context) {
        return new ComponentName(context, getClass());
    }

    protected static int[] getBoundingBoxes(Context context, int appWidgetId) {
        int[] results = new int[]{WidgetUtils.getDips(context, R.dimen.now_playing_default_width), WidgetUtils.getDips(context, R.dimen.now_playing_default_height), WidgetUtils.getDips(context, R.dimen.now_playing_default_width), WidgetUtils.getDips(context, R.dimen.now_playing_default_height)};
        if (VERSION.SDK_INT >= 16) {
            Bundle result = AppWidgetManager.getInstance(context).getAppWidgetOptions(appWidgetId);
            results[0] = result.getInt("appWidgetMinWidth");
            results[1] = result.getInt("appWidgetMinHeight");
            results[2] = result.getInt("appWidgetMaxWidth");
            results[3] = result.getInt("appWidgetMaxHeight");
        }
        return results;
    }

    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        String action = intent.getAction();
        for (String loggableAction : this.LOGGABLE_INTENT_ACTIONS) {
            if (loggableAction.equals(action)) {
                sendAnalytics(context, intent);
                break;
            }
        }
        if ("com.android.launcher.action.APPWIDGET_DEFAULT_WORKSPACE_CONFIGURE".equals(action)) {
            onDefaultConfiguration(context, AppWidgetManager.getInstance(context), intent, intent.getIntExtra("appWidgetId", 0));
        }
        if ("com.android.vending.action.APPWIDGET_UPDATE_CONSUMPTION_DATA".equals(action)) {
            if (intent.hasExtra("backendId")) {
                WidgetTypeMap typeMap = WidgetTypeMap.get(context);
                updateWidgets(context, appWidgetManager, typeMap.getWidgetsOfExactType(getClass(), WidgetUtils.translate(intent.getIntExtra("backendId", 0))));
                updateWidgets(context, appWidgetManager, typeMap.getWidgetsOfExactType(getClass(), "all"));
            } else {
                FinskyLog.wtf("No backend specified for update!", new Object[0]);
            }
        }
        if ("android.appwidget.action.APPWIDGET_UPDATE".equals(intent.getAction())) {
            if (intent.hasExtra("appWidgetId")) {
                if (intent.getIntExtra("appWidgetId", -1) == -1) {
                    FinskyLog.d("Received ACTION_APPWIDGET_UPDATE, with invalid widget ID.", new Object[0]);
                    return;
                }
                FinskyLog.d("Received ACTION_APPWIDGET_UPDATE, updating widget %d.", Integer.valueOf(intent.getIntExtra("appWidgetId", -1)));
                updateWidgets(context, appWidgetManager, appWidgetId);
                return;
            }
            if (intent.hasExtra("appWidgetIds")) {
                int[] appWidgetIds = intent.getIntArrayExtra("appWidgetIds");
                Object[] objArr = new Object[1];
                objArr[0] = Integer.valueOf(appWidgetIds.length);
                FinskyLog.d("Received ACTION_APPWIDGET_UPDATE, updating %d widgets.", objArr);
                updateWidgets(context, appWidgetManager, appWidgetIds);
            } else if (((Boolean) G.debugOptionsEnabled.get()).booleanValue()) {
                updateWidgets(context, appWidgetManager, appWidgetManager.getAppWidgetIds(getComponentName(context)));
            } else {
                FinskyLog.e("Refusing to update all widgets; need to narrow scope", new Object[0]);
            }
        }
    }

    public void onDeleted(Context context, int[] appWidgetIds) {
        WidgetTypeMap typeMap = WidgetTypeMap.get(context);
        for (int appWidgetId : appWidgetIds) {
            typeMap.delete(getClass(), appWidgetId);
        }
    }

    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        updateWidgets(context, appWidgetManager, appWidgetIds);
    }

    protected void onDefaultConfiguration(Context context, AppWidgetManager manager, Intent intent, int appWidgetId) {
        WidgetTypeMap.get(context).put(getClass(), appWidgetId, intent.getStringExtra("type"));
        updateWidgets(context, manager, appWidgetId);
    }

    private void sendAnalytics(Context context, Intent intent) {
        int num = AppWidgetManager.getInstance(context).getAppWidgetIds(getComponentName(context)).length;
        String action = intent.getAction();
        int actionId = 0;
        for (int i = 0; i < this.LOGGABLE_INTENT_ACTIONS.length; i++) {
            if (this.LOGGABLE_INTENT_ACTIONS[i].equals(action)) {
                actionId = i;
                break;
            }
        }
        WidgetEventData eventData = new WidgetEventData();
        eventData.classId = getWidgetClassId();
        eventData.hasClassId = true;
        eventData.intentActionId = actionId;
        eventData.hasIntentActionId = true;
        eventData.numWidgets = num;
        eventData.hasNumWidgets = true;
        if ("android.appwidget.action.APPWIDGET_UPDATE_OPTIONS".equals(intent.getAction())) {
            int appWidgetId = intent.getIntExtra("appWidgetId", 0);
            int[] dimens = getBoundingBoxes(context, appWidgetId);
            try {
                eventData.backendId = WidgetUtils.translate(WidgetTypeMap.get(context).get(getClass(), appWidgetId));
                eventData.hasBackendId = true;
            } catch (IllegalArgumentException e) {
                eventData.backendId = -1;
                eventData.hasBackendId = false;
            }
            eventData.minWidth = dimens[0];
            eventData.hasMinWidth = true;
            eventData.minHeight = dimens[1];
            eventData.hasMinHeight = true;
            eventData.maxWidth = dimens[2];
            eventData.hasMaxWidth = true;
            eventData.maxHeight = dimens[3];
            eventData.hasMaxHeight = true;
        }
        FinskyApp.get().getEventLogger().logBackgroundEvent(new BackgroundEventBuilder(507).setWidgetEventData(eventData).build());
    }

    protected static PendingIntent getAddAccountIntent(Context context) {
        return PendingIntent.getActivity(context, 0, AccountManager.newChooseAccountIntent(null, null, AccountHandler.getAccountTypes(), true, null, "androidmarket", null, AuthenticatedActivity.createAddAccountOptions(context)), 0);
    }

    public static void update(Context context, Class<?> cls, int... appWidgetIds) {
        Intent intent = new Intent(context, cls);
        intent.setAction("android.appwidget.action.APPWIDGET_UPDATE");
        intent.putExtra("appWidgetIds", appWidgetIds);
        context.sendBroadcast(intent);
    }
}
