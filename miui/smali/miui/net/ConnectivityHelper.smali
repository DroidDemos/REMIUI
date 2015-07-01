.class public Lmiui/net/ConnectivityHelper;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field private static final TAG:Ljava/lang/String; = "ConnectivityHelper"

.field private static final f:Lmiui/util/SoftReferenceSingleton;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lmiui/util/SoftReferenceSingleton",
            "<",
            "Lmiui/net/ConnectivityHelper;",
            ">;"
        }
    .end annotation
.end field


# instance fields
.field private mContext:Landroid/content/Context;

.field private sp:Landroid/net/ConnectivityManager;

.field private sq:Landroid/net/wifi/WifiManager;

.field private sr:Ljava/lang/String;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .prologue
    .line 27
    new-instance v0, Lmiui/net/a;

    invoke-direct {v0}, Lmiui/net/a;-><init>()V

    sput-object v0, Lmiui/net/ConnectivityHelper;->f:Lmiui/util/SoftReferenceSingleton;

    return-void
.end method

.method private constructor <init>()V
    .locals 2

    .prologue
    .line 50
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 51
    invoke-static {}, Lcom/miui/internal/util/PackageConstants;->getCurrentApplication()Landroid/app/Application;

    move-result-object v0

    iput-object v0, p0, Lmiui/net/ConnectivityHelper;->mContext:Landroid/content/Context;

    .line 52
    iget-object v0, p0, Lmiui/net/ConnectivityHelper;->mContext:Landroid/content/Context;

    const-string v1, "connectivity"

    invoke-virtual {v0, v1}, Landroid/content/Context;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/net/ConnectivityManager;

    iput-object v0, p0, Lmiui/net/ConnectivityHelper;->sp:Landroid/net/ConnectivityManager;

    .line 53
    return-void
.end method

.method synthetic constructor <init>(Lmiui/net/a;)V
    .locals 0

    .prologue
    .line 25
    invoke-direct {p0}, Lmiui/net/ConnectivityHelper;-><init>()V

    return-void
.end method

.method public static getInstance()Lmiui/net/ConnectivityHelper;
    .locals 1

    .prologue
    .line 40
    sget-object v0, Lmiui/net/ConnectivityHelper;->f:Lmiui/util/SoftReferenceSingleton;

    invoke-virtual {v0}, Lmiui/util/SoftReferenceSingleton;->get()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/net/ConnectivityHelper;

    return-object v0
.end method


# virtual methods
.method public getMacAddress()Ljava/lang/String;
    .locals 3

    .prologue
    .line 104
    iget-object v0, p0, Lmiui/net/ConnectivityHelper;->sr:Ljava/lang/String;

    if-eqz v0, :cond_0

    .line 105
    iget-object v0, p0, Lmiui/net/ConnectivityHelper;->sr:Ljava/lang/String;

    .line 129
    :goto_0
    return-object v0

    .line 109
    :cond_0
    :try_start_0
    iget-object v0, p0, Lmiui/net/ConnectivityHelper;->mContext:Landroid/content/Context;

    const-string v1, "com.miui.sdk"

    const/4 v2, 0x2

    invoke-virtual {v0, v1, v2}, Landroid/content/Context;->createPackageContext(Ljava/lang/String;I)Landroid/content/Context;

    move-result-object v0

    .line 111
    const-string v1, "public"

    const/4 v2, 0x1

    invoke-virtual {v0, v1, v2}, Landroid/content/Context;->getSharedPreferences(Ljava/lang/String;I)Landroid/content/SharedPreferences;

    move-result-object v0

    .line 113
    const-string v1, "mac_address"

    const/4 v2, 0x0

    invoke-interface {v0, v1, v2}, Landroid/content/SharedPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lmiui/net/ConnectivityHelper;->sr:Ljava/lang/String;
    :try_end_0
    .catch Landroid/content/pm/PackageManager$NameNotFoundException; {:try_start_0 .. :try_end_0} :catch_0

    .line 119
    :goto_1
    iget-object v0, p0, Lmiui/net/ConnectivityHelper;->sr:Ljava/lang/String;

    if-nez v0, :cond_2

    .line 120
    iget-object v0, p0, Lmiui/net/ConnectivityHelper;->sq:Landroid/net/wifi/WifiManager;

    if-nez v0, :cond_1

    .line 121
    iget-object v0, p0, Lmiui/net/ConnectivityHelper;->mContext:Landroid/content/Context;

    const-string v1, "wifi"

    invoke-virtual {v0, v1}, Landroid/content/Context;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/net/wifi/WifiManager;

    iput-object v0, p0, Lmiui/net/ConnectivityHelper;->sq:Landroid/net/wifi/WifiManager;

    .line 123
    :cond_1
    iget-object v0, p0, Lmiui/net/ConnectivityHelper;->sq:Landroid/net/wifi/WifiManager;

    invoke-virtual {v0}, Landroid/net/wifi/WifiManager;->getConnectionInfo()Landroid/net/wifi/WifiInfo;

    move-result-object v0

    .line 124
    if-eqz v0, :cond_2

    .line 125
    invoke-virtual {v0}, Landroid/net/wifi/WifiInfo;->getMacAddress()Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lmiui/net/ConnectivityHelper;->sr:Ljava/lang/String;

    .line 129
    :cond_2
    iget-object v0, p0, Lmiui/net/ConnectivityHelper;->sr:Ljava/lang/String;

    goto :goto_0

    .line 114
    :catch_0
    move-exception v0

    .line 115
    const-string v1, "ConnectivityHelper"

    const-string v2, "Fail to getMacAddress"

    invoke-static {v1, v2, v0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    goto :goto_1
.end method

.method public getManager()Landroid/net/ConnectivityManager;
    .locals 1

    .prologue
    .line 60
    iget-object v0, p0, Lmiui/net/ConnectivityHelper;->sp:Landroid/net/ConnectivityManager;

    return-object v0
.end method

.method public isNetworkConnected()Z
    .locals 1

    .prologue
    .line 68
    iget-object v0, p0, Lmiui/net/ConnectivityHelper;->sp:Landroid/net/ConnectivityManager;

    invoke-virtual {v0}, Landroid/net/ConnectivityManager;->getActiveNetworkInfo()Landroid/net/NetworkInfo;

    move-result-object v0

    .line 69
    if-eqz v0, :cond_0

    invoke-virtual {v0}, Landroid/net/NetworkInfo;->isConnected()Z

    move-result v0

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public isUnmeteredNetworkConnected()Z
    .locals 1

    .prologue
    .line 87
    iget-object v0, p0, Lmiui/net/ConnectivityHelper;->sp:Landroid/net/ConnectivityManager;

    invoke-virtual {v0}, Landroid/net/ConnectivityManager;->getActiveNetworkInfo()Landroid/net/NetworkInfo;

    move-result-object v0

    .line 88
    if-eqz v0, :cond_0

    invoke-virtual {v0}, Landroid/net/NetworkInfo;->isConnected()Z

    move-result v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lmiui/net/ConnectivityHelper;->sp:Landroid/net/ConnectivityManager;

    invoke-virtual {v0}, Landroid/net/ConnectivityManager;->isActiveNetworkMetered()Z

    move-result v0

    if-nez v0, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public isWifiConnected()Z
    .locals 3

    .prologue
    const/4 v0, 0x1

    .line 77
    iget-object v1, p0, Lmiui/net/ConnectivityHelper;->sp:Landroid/net/ConnectivityManager;

    invoke-virtual {v1}, Landroid/net/ConnectivityManager;->getActiveNetworkInfo()Landroid/net/NetworkInfo;

    move-result-object v1

    .line 78
    if-eqz v1, :cond_0

    invoke-virtual {v1}, Landroid/net/NetworkInfo;->isConnected()Z

    move-result v2

    if-eqz v2, :cond_0

    invoke-virtual {v1}, Landroid/net/NetworkInfo;->getType()I

    move-result v1

    if-ne v1, v0, :cond_0

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public isWifiOnly()Z
    .locals 3

    .prologue
    const/4 v0, 0x0

    .line 137
    invoke-static {}, Lcom/miui/internal/variable/Android_Net_ConnectivityManager_class$Factory;->getInstance()Lcom/miui/internal/variable/Android_Net_ConnectivityManager_class$Factory;

    move-result-object v1

    invoke-virtual {v1}, Lcom/miui/internal/variable/Android_Net_ConnectivityManager_class$Factory;->get()Lcom/miui/internal/variable/Android_Net_ConnectivityManager_class;

    move-result-object v1

    iget-object v2, p0, Lmiui/net/ConnectivityHelper;->sp:Landroid/net/ConnectivityManager;

    invoke-virtual {v1, v2, v0}, Lcom/miui/internal/variable/Android_Net_ConnectivityManager_class;->isNetworkSupported(Landroid/net/ConnectivityManager;I)Z

    move-result v1

    .line 139
    if-nez v1, :cond_0

    const/4 v0, 0x1

    :cond_0
    return v0
.end method
