.class public Lcom/miui/internal/server/DataUpdateReceiver;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field public static final ACTION_DATA_UPDATE:Ljava/lang/String; = "com.miui.internal.action.DATA_UPDATE"

.field public static final CHECK_UPDATE_ONLY_WIFI_AVAILABLE:Ljava/lang/String; = "check_update_only_wifi_available"

.field public static final CHECK_UPDATE_ONLY_WIFI_AVAILABLE_DEFAULT:I = 0x1

.field public static final LAST_UPDATE_TIME:Ljava/lang/String; = "last_update_time"

.field private static final gA:J = 0x240c8400L


# direct methods
.method public constructor <init>()V
    .locals 0

    .prologue
    .line 18
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method private static a(Landroid/content/Context;J)V
    .locals 3

    .prologue
    const/4 v2, 0x0

    .line 83
    new-instance v0, Landroid/content/Intent;

    const-class v1, Lcom/miui/internal/server/Receiver;

    invoke-direct {v0, p0, v1}, Landroid/content/Intent;-><init>(Landroid/content/Context;Ljava/lang/Class;)V

    .line 84
    const-string v1, "com.miui.internal.action.DATA_UPDATE"

    invoke-virtual {v0, v1}, Landroid/content/Intent;->setAction(Ljava/lang/String;)Landroid/content/Intent;

    .line 85
    invoke-static {p0, v2, v0, v2}, Landroid/app/PendingIntent;->getBroadcast(Landroid/content/Context;ILandroid/content/Intent;I)Landroid/app/PendingIntent;

    move-result-object v1

    .line 86
    const-string v0, "alarm"

    invoke-virtual {p0, v0}, Landroid/content/Context;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/app/AlarmManager;

    .line 87
    const/4 v2, 0x1

    invoke-virtual {v0, v2, p1, p2, v1}, Landroid/app/AlarmManager;->set(IJLandroid/app/PendingIntent;)V

    .line 88
    return-void
.end method

.method private static b(Landroid/content/Context;)Z
    .locals 3

    .prologue
    const/4 v0, 0x1

    .line 54
    invoke-virtual {p0}, Landroid/content/Context;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object v1

    const-string v2, "check_update_only_wifi_available"

    invoke-static {v1, v2, v0}, Landroid/provider/Settings$System;->getInt(Landroid/content/ContentResolver;Ljava/lang/String;I)I

    move-result v1

    .line 56
    if-ne v1, v0, :cond_0

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method private static c(Landroid/content/Context;)V
    .locals 4

    .prologue
    const/4 v1, 0x1

    .line 61
    const-string v0, "connectivity"

    invoke-virtual {p0, v0}, Landroid/content/Context;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/net/ConnectivityManager;

    .line 62
    const/4 v2, 0x0

    .line 63
    invoke-virtual {v0}, Landroid/net/ConnectivityManager;->getActiveNetworkInfo()Landroid/net/NetworkInfo;

    move-result-object v3

    .line 64
    if-eqz v3, :cond_2

    invoke-virtual {v3}, Landroid/net/NetworkInfo;->isAvailable()Z

    move-result v3

    if-eqz v3, :cond_2

    .line 65
    invoke-static {p0}, Lcom/miui/internal/server/DataUpdateReceiver;->b(Landroid/content/Context;)Z

    move-result v3

    if-eqz v3, :cond_1

    .line 67
    invoke-virtual {v0}, Landroid/net/ConnectivityManager;->isActiveNetworkMetered()Z

    move-result v0

    if-nez v0, :cond_2

    move v0, v1

    .line 75
    :goto_0
    if-eqz v0, :cond_0

    .line 77
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v0

    const-wide/32 v2, 0x240c8400

    add-long/2addr v0, v2

    invoke-static {p0, v0, v1}, Lcom/miui/internal/server/DataUpdateReceiver;->a(Landroid/content/Context;J)V

    .line 78
    new-instance v0, Landroid/content/Intent;

    const-class v1, Lcom/miui/internal/server/DataUpdateService;

    invoke-direct {v0, p0, v1}, Landroid/content/Intent;-><init>(Landroid/content/Context;Ljava/lang/Class;)V

    invoke-virtual {p0, v0}, Landroid/content/Context;->startService(Landroid/content/Intent;)Landroid/content/ComponentName;

    .line 80
    :cond_0
    return-void

    :cond_1
    move v0, v1

    .line 71
    goto :goto_0

    :cond_2
    move v0, v2

    goto :goto_0
.end method

.method public static isUpdateTimeOverDay(Landroid/content/Context;)Z
    .locals 6

    .prologue
    .line 92
    invoke-static {p0}, Landroid/preference/PreferenceManager;->getDefaultSharedPreferences(Landroid/content/Context;)Landroid/content/SharedPreferences;

    move-result-object v0

    .line 93
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v1

    .line 94
    const-string v3, "last_update_time"

    const-wide/16 v4, 0x0

    invoke-interface {v0, v3, v4, v5}, Landroid/content/SharedPreferences;->getLong(Ljava/lang/String;J)J

    move-result-wide v3

    .line 95
    sub-long v0, v1, v3

    const-wide/32 v2, 0x240c8400

    cmp-long v0, v0, v2

    if-lez v0, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public static onReceive(Landroid/content/Context;Landroid/content/Intent;)V
    .locals 4

    .prologue
    .line 29
    sget-boolean v0, Lmiui/os/Build;->IS_CTA_BUILD:Z

    if-eqz v0, :cond_1

    .line 50
    :cond_0
    :goto_0
    return-void

    .line 33
    :cond_1
    invoke-virtual {p1}, Landroid/content/Intent;->getAction()Ljava/lang/String;

    move-result-object v0

    .line 34
    const-string v1, "android.intent.action.BOOT_COMPLETED"

    invoke-virtual {v1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_3

    .line 36
    invoke-static {p0}, Lcom/miui/internal/server/DataUpdateReceiver;->isUpdateTimeOverDay(Landroid/content/Context;)Z

    move-result v0

    if-eqz v0, :cond_2

    .line 37
    invoke-static {p0}, Lcom/miui/internal/server/DataUpdateReceiver;->c(Landroid/content/Context;)V

    goto :goto_0

    .line 40
    :cond_2
    invoke-static {p0}, Landroid/preference/PreferenceManager;->getDefaultSharedPreferences(Landroid/content/Context;)Landroid/content/SharedPreferences;

    move-result-object v0

    const-string v1, "last_update_time"

    const-wide/16 v2, 0x0

    invoke-interface {v0, v1, v2, v3}, Landroid/content/SharedPreferences;->getLong(Ljava/lang/String;J)J

    move-result-wide v0

    .line 41
    const-wide/32 v2, 0x240c8400

    add-long/2addr v0, v2

    invoke-static {p0, v0, v1}, Lcom/miui/internal/server/DataUpdateReceiver;->a(Landroid/content/Context;J)V

    goto :goto_0

    .line 43
    :cond_3
    const-string v1, "android.net.conn.CONNECTIVITY_CHANGE"

    invoke-virtual {v1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_4

    .line 44
    invoke-static {p0}, Lcom/miui/internal/server/DataUpdateReceiver;->isUpdateTimeOverDay(Landroid/content/Context;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 45
    invoke-static {p0}, Lcom/miui/internal/server/DataUpdateReceiver;->c(Landroid/content/Context;)V

    goto :goto_0

    .line 47
    :cond_4
    const-string v1, "com.miui.internal.action.DATA_UPDATE"

    invoke-virtual {v1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 48
    invoke-static {p0}, Lcom/miui/internal/server/DataUpdateReceiver;->c(Landroid/content/Context;)V

    goto :goto_0
.end method
