.class public final Lcom/miui/internal/server/CoreService;
.super Landroid/app/Service;
.source "SourceFile"


# static fields
.field public static final ACTION_BIND_SERVICE:Ljava/lang/String; = "com.miui.internal.action.BIND_SERVICE"

.field public static final ACTION_DAILY_ALARM:Ljava/lang/String; = "com.miui.internal.action.DAILY_ALARM"

.field public static final EXTRA_SERVICE_NAME:Ljava/lang/String; = "extra_service_name"

.field private static xv:Z


# instance fields
.field xw:Landroid/os/IBinder;


# direct methods
.method public constructor <init>()V
    .locals 1

    .prologue
    .line 26
    invoke-direct {p0}, Landroid/app/Service;-><init>()V

    .line 74
    new-instance v0, Landroid/os/Binder;

    invoke-direct {v0}, Landroid/os/Binder;-><init>()V

    iput-object v0, p0, Lcom/miui/internal/server/CoreService;->xw:Landroid/os/IBinder;

    return-void
.end method

.method private static k(Landroid/content/Context;)V
    .locals 10

    .prologue
    const/4 v1, 0x0

    const-wide/high16 v8, 0x404e000000000000L

    .line 47
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v2

    .line 48
    invoke-static {}, Ljava/lang/Math;->random()D

    move-result-wide v4

    const-wide/high16 v6, 0x401c000000000000L

    mul-double/2addr v4, v6

    const-wide/high16 v6, 0x4020000000000000L

    add-double/2addr v4, v6

    mul-double/2addr v4, v8

    mul-double/2addr v4, v8

    const-wide v6, 0x408f400000000000L

    mul-double/2addr v4, v6

    double-to-long v4, v4

    add-long/2addr v2, v4

    .line 50
    new-instance v0, Landroid/content/Intent;

    const-string v4, "com.miui.internal.action.DAILY_ALARM"

    invoke-direct {v0, v4}, Landroid/content/Intent;-><init>(Ljava/lang/String;)V

    .line 51
    invoke-virtual {p0}, Landroid/content/Context;->getPackageName()Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v0, v4}, Landroid/content/Intent;->setPackage(Ljava/lang/String;)Landroid/content/Intent;

    .line 52
    const/high16 v4, 0x8000000

    invoke-static {p0, v1, v0, v4}, Landroid/app/PendingIntent;->getBroadcast(Landroid/content/Context;ILandroid/content/Intent;I)Landroid/app/PendingIntent;

    move-result-object v6

    .line 54
    const-string v0, "alarm"

    invoke-virtual {p0, v0}, Landroid/content/Context;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/app/AlarmManager;

    .line 56
    const-wide/32 v4, 0x5265c00

    invoke-virtual/range {v0 .. v6}, Landroid/app/AlarmManager;->setRepeating(IJJLandroid/app/PendingIntent;)V

    .line 57
    return-void
.end method

.method private static l(Landroid/content/Context;)V
    .locals 4

    .prologue
    const/4 v3, 0x1

    .line 60
    sget-boolean v0, Lcom/miui/internal/server/CoreService;->xv:Z

    if-eqz v0, :cond_1

    .line 71
    :cond_0
    :goto_0
    return-void

    .line 64
    :cond_1
    invoke-static {}, Lmiui/net/ConnectivityHelper;->getInstance()Lmiui/net/ConnectivityHelper;

    move-result-object v0

    invoke-virtual {v0}, Lmiui/net/ConnectivityHelper;->getMacAddress()Ljava/lang/String;

    move-result-object v0

    .line 65
    if-eqz v0, :cond_0

    .line 66
    const-string v1, "public"

    invoke-virtual {p0, v1, v3}, Landroid/content/Context;->getSharedPreferences(Ljava/lang/String;I)Landroid/content/SharedPreferences;

    move-result-object v1

    .line 68
    invoke-interface {v1}, Landroid/content/SharedPreferences;->edit()Landroid/content/SharedPreferences$Editor;

    move-result-object v1

    const-string v2, "mac_address"

    invoke-interface {v1, v2, v0}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    invoke-interface {v0}, Landroid/content/SharedPreferences$Editor;->commit()Z

    .line 69
    sput-boolean v3, Lcom/miui/internal/server/CoreService;->xv:Z

    goto :goto_0
.end method

.method public static onReceive(Landroid/content/Context;Landroid/content/Intent;)V
    .locals 2

    .prologue
    .line 35
    const-string v0, "android.intent.action.BOOT_COMPLETED"

    invoke-static {p1, v0}, Lcom/miui/internal/server/Receiver;->isActionEquals(Landroid/content/Intent;Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_1

    .line 36
    invoke-static {p0}, Lcom/miui/internal/server/CoreService;->k(Landroid/content/Context;)V

    .line 37
    invoke-static {p0}, Lcom/miui/internal/server/CoreService;->l(Landroid/content/Context;)V

    .line 44
    :cond_0
    :goto_0
    return-void

    .line 38
    :cond_1
    const-string v0, "android.net.wifi.supplicant.CONNECTION_CHANGE"

    invoke-static {p1, v0}, Lcom/miui/internal/server/Receiver;->isActionEquals(Landroid/content/Intent;Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 39
    const-string v0, "connected"

    const/4 v1, 0x0

    invoke-virtual {p1, v0, v1}, Landroid/content/Intent;->getBooleanExtra(Ljava/lang/String;Z)Z

    move-result v0

    .line 40
    if-eqz v0, :cond_0

    .line 41
    invoke-static {p0}, Lcom/miui/internal/server/CoreService;->l(Landroid/content/Context;)V

    goto :goto_0
.end method


# virtual methods
.method public onBind(Landroid/content/Intent;)Landroid/os/IBinder;
    .locals 2

    .prologue
    .line 85
    const-string v0, "com.miui.internal.action.BIND_SERVICE"

    invoke-static {p1, v0}, Lcom/miui/internal/server/Receiver;->isActionEquals(Landroid/content/Intent;Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 86
    const-string v0, "extra_service_name"

    invoke-virtual {p1, v0}, Landroid/content/Intent;->getStringExtra(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 87
    const-string v1, "DropBoxManagerService"

    invoke-virtual {v1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 88
    invoke-static {}, Lcom/miui/internal/server/DropBoxManagerService;->getInstance()Lcom/miui/internal/server/DropBoxManagerService;

    move-result-object v0

    .line 91
    :goto_0
    return-object v0

    :cond_0
    iget-object v0, p0, Lcom/miui/internal/server/CoreService;->xw:Landroid/os/IBinder;

    goto :goto_0
.end method

.method public onStartCommand(Landroid/content/Intent;II)I
    .locals 1

    .prologue
    .line 78
    invoke-super {p0, p1, p2, p3}, Landroid/app/Service;->onStartCommand(Landroid/content/Intent;II)I

    .line 80
    const/4 v0, 0x1

    return v0
.end method
