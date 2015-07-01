.class public Lcom/miui/internal/server/ErrorReportService;
.super Landroid/app/IntentService;
.source "SourceFile"


# static fields
.field public static final ACTION_UPLOAD_REPORT:Ljava/lang/String; = "miui.action.upload_error_report"

.field private static final TAG:Ljava/lang/String; = "ErrorReportService"

.field private static final sB:Ljava/lang/String; = "error_report_last_update_time"

.field private static final sC:I = 0x7d000


# direct methods
.method public constructor <init>()V
    .locals 1

    .prologue
    .line 45
    const-string v0, "ErrorReportService"

    invoke-direct {p0, v0}, Landroid/app/IntentService;-><init>(Ljava/lang/String;)V

    .line 46
    return-void
.end method

.method private bI()V
    .locals 6

    .prologue
    .line 59
    invoke-static {}, Lcom/miui/internal/server/DropBoxManagerService;->getInstance()Lcom/miui/internal/server/DropBoxManagerService;

    move-result-object v3

    .line 61
    invoke-direct {p0}, Lcom/miui/internal/server/ErrorReportService;->bJ()J

    move-result-wide v0

    .line 62
    const-string v2, "fc_anr"

    invoke-virtual {v3, v2, v0, v1}, Lcom/miui/internal/server/DropBoxManagerService;->getNextEntry(Ljava/lang/String;J)Lmiui/os/DropBoxManager$Entry;

    move-result-object v0

    move-object v2, v0

    .line 65
    :goto_0
    if-eqz v2, :cond_0

    invoke-static {p0}, Lmiui/util/ErrorReport;->isUserAllowed(Landroid/content/Context;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 66
    const v0, 0x7d000

    invoke-virtual {v2, v0}, Lmiui/os/DropBoxManager$Entry;->getText(I)Ljava/lang/String;

    move-result-object v4

    .line 67
    const/4 v1, 0x0

    .line 69
    :try_start_0
    new-instance v0, Lorg/json/JSONObject;

    invoke-direct {v0, v4}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V
    :try_end_0
    .catch Lorg/json/JSONException; {:try_start_0 .. :try_end_0} :catch_0

    .line 73
    :goto_1
    if-eqz v0, :cond_1

    .line 74
    const/4 v1, 0x3

    invoke-static {p0, v0, v1}, Lmiui/util/ErrorReport;->sendReportRequest(Landroid/content/Context;Lorg/json/JSONObject;I)Z

    move-result v0

    .line 77
    if-nez v0, :cond_1

    invoke-static {}, Lmiui/net/ConnectivityHelper;->getInstance()Lmiui/net/ConnectivityHelper;

    move-result-object v0

    invoke-virtual {v0}, Lmiui/net/ConnectivityHelper;->isUnmeteredNetworkConnected()Z

    move-result v0

    if-nez v0, :cond_1

    .line 78
    const-string v0, "ErrorReportService"

    const-string v1, "Stop uploading error report for free network disconnect"

    invoke-static {v0, v1}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    .line 89
    :cond_0
    return-void

    .line 70
    :catch_0
    move-exception v0

    .line 71
    const-string v4, "ErrorReportService"

    const-string v5, "Fail to parse json"

    invoke-static {v4, v5, v0}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    move-object v0, v1

    goto :goto_1

    .line 84
    :cond_1
    invoke-virtual {v2}, Lmiui/os/DropBoxManager$Entry;->getTimeMillis()J

    move-result-wide v0

    .line 85
    invoke-direct {p0, v0, v1}, Lcom/miui/internal/server/ErrorReportService;->f(J)V

    .line 86
    invoke-virtual {v2}, Lmiui/os/DropBoxManager$Entry;->close()V

    .line 87
    const-string v2, "fc_anr"

    invoke-virtual {v3, v2, v0, v1}, Lcom/miui/internal/server/DropBoxManagerService;->getNextEntry(Ljava/lang/String;J)Lmiui/os/DropBoxManager$Entry;

    move-result-object v0

    move-object v2, v0

    .line 88
    goto :goto_0
.end method

.method private bJ()J
    .locals 6

    .prologue
    .line 92
    invoke-static {p0}, Landroid/preference/PreferenceManager;->getDefaultSharedPreferences(Landroid/content/Context;)Landroid/content/SharedPreferences;

    move-result-object v0

    .line 93
    const-string v1, "error_report_last_update_time"

    const-wide/16 v2, 0x0

    invoke-interface {v0, v1, v2, v3}, Landroid/content/SharedPreferences;->getLong(Ljava/lang/String;J)J

    move-result-wide v0

    .line 94
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v2

    .line 95
    const-wide/32 v4, 0x5265c00

    rem-long v4, v2, v4

    sub-long/2addr v2, v4

    .line 96
    invoke-static {v0, v1, v2, v3}, Ljava/lang/Math;->max(JJ)J

    move-result-wide v0

    return-wide v0
.end method

.method private f(J)V
    .locals 2

    .prologue
    .line 100
    invoke-static {p0}, Landroid/preference/PreferenceManager;->getDefaultSharedPreferences(Landroid/content/Context;)Landroid/content/SharedPreferences;

    move-result-object v0

    .line 101
    invoke-interface {v0}, Landroid/content/SharedPreferences;->edit()Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    const-string v1, "error_report_last_update_time"

    invoke-interface {v0, v1, p1, p2}, Landroid/content/SharedPreferences$Editor;->putLong(Ljava/lang/String;J)Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    invoke-interface {v0}, Landroid/content/SharedPreferences$Editor;->apply()V

    .line 102
    return-void
.end method

.method public static onReceive(Landroid/content/Context;Landroid/content/Intent;)V
    .locals 2

    .prologue
    .line 34
    const-string v0, "android.net.wifi.STATE_CHANGE"

    invoke-static {p1, v0}, Lcom/miui/internal/server/Receiver;->isActionEquals(Landroid/content/Intent;Ljava/lang/String;)Z

    move-result v0

    if-nez v0, :cond_0

    const-string v0, "com.miui.internal.action.DAILY_ALARM"

    invoke-static {p1, v0}, Lcom/miui/internal/server/Receiver;->isActionEquals(Landroid/content/Intent;Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_1

    .line 36
    :cond_0
    invoke-static {}, Lmiui/net/ConnectivityHelper;->getInstance()Lmiui/net/ConnectivityHelper;

    move-result-object v0

    invoke-virtual {v0}, Lmiui/net/ConnectivityHelper;->isUnmeteredNetworkConnected()Z

    move-result v0

    if-eqz v0, :cond_1

    .line 37
    new-instance v0, Landroid/content/Intent;

    const-string v1, "miui.action.upload_error_report"

    invoke-direct {v0, v1}, Landroid/content/Intent;-><init>(Ljava/lang/String;)V

    .line 38
    const-class v1, Lcom/miui/internal/server/ErrorReportService;

    invoke-virtual {v0, p0, v1}, Landroid/content/Intent;->setClass(Landroid/content/Context;Ljava/lang/Class;)Landroid/content/Intent;

    .line 39
    invoke-virtual {p0, v0}, Landroid/content/Context;->startService(Landroid/content/Intent;)Landroid/content/ComponentName;

    .line 42
    :cond_1
    return-void
.end method


# virtual methods
.method protected onHandleIntent(Landroid/content/Intent;)V
    .locals 2

    .prologue
    .line 50
    if-nez p1, :cond_1

    const-string v0, ""

    .line 51
    :goto_0
    const-string v1, "miui.action.upload_error_report"

    invoke-virtual {v1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 52
    invoke-static {p0}, Lmiui/util/ErrorReport;->isUserAllowed(Landroid/content/Context;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 53
    invoke-direct {p0}, Lcom/miui/internal/server/ErrorReportService;->bI()V

    .line 56
    :cond_0
    return-void

    .line 50
    :cond_1
    invoke-virtual {p1}, Landroid/content/Intent;->getAction()Ljava/lang/String;

    move-result-object v0

    goto :goto_0
.end method
