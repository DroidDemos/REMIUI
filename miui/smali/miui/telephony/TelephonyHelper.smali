.class public Lmiui/telephony/TelephonyHelper;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field private static final f:Lmiui/util/SoftReferenceSingleton;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lmiui/util/SoftReferenceSingleton",
            "<",
            "Lmiui/telephony/TelephonyHelper;",
            ">;"
        }
    .end annotation
.end field


# instance fields
.field private dY:Landroid/telephony/TelephonyManager;

.field private dZ:Ljava/lang/String;

.field private mContext:Landroid/content/Context;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .prologue
    .line 22
    new-instance v0, Lmiui/telephony/a;

    invoke-direct {v0}, Lmiui/telephony/a;-><init>()V

    sput-object v0, Lmiui/telephony/TelephonyHelper;->f:Lmiui/util/SoftReferenceSingleton;

    return-void
.end method

.method private constructor <init>()V
    .locals 2

    .prologue
    .line 44
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 45
    invoke-static {}, Lcom/miui/internal/util/PackageConstants;->getCurrentApplication()Landroid/app/Application;

    move-result-object v0

    iput-object v0, p0, Lmiui/telephony/TelephonyHelper;->mContext:Landroid/content/Context;

    .line 46
    iget-object v0, p0, Lmiui/telephony/TelephonyHelper;->mContext:Landroid/content/Context;

    const-string v1, "phone"

    invoke-virtual {v0, v1}, Landroid/content/Context;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/telephony/TelephonyManager;

    iput-object v0, p0, Lmiui/telephony/TelephonyHelper;->dY:Landroid/telephony/TelephonyManager;

    .line 48
    return-void
.end method

.method synthetic constructor <init>(Lmiui/telephony/a;)V
    .locals 0

    .prologue
    .line 20
    invoke-direct {p0}, Lmiui/telephony/TelephonyHelper;-><init>()V

    return-void
.end method

.method public static getInstance()Lmiui/telephony/TelephonyHelper;
    .locals 1

    .prologue
    .line 35
    sget-object v0, Lmiui/telephony/TelephonyHelper;->f:Lmiui/util/SoftReferenceSingleton;

    invoke-virtual {v0}, Lmiui/util/SoftReferenceSingleton;->get()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/telephony/TelephonyHelper;

    return-object v0
.end method


# virtual methods
.method public getDeviceId()Ljava/lang/String;
    .locals 2

    .prologue
    .line 62
    iget-object v0, p0, Lmiui/telephony/TelephonyHelper;->dZ:Ljava/lang/String;

    if-eqz v0, :cond_0

    .line 63
    iget-object v0, p0, Lmiui/telephony/TelephonyHelper;->dZ:Ljava/lang/String;

    .line 80
    :goto_0
    return-object v0

    .line 66
    :cond_0
    iget-object v0, p0, Lmiui/telephony/TelephonyHelper;->dY:Landroid/telephony/TelephonyManager;

    invoke-virtual {v0}, Landroid/telephony/TelephonyManager;->getDeviceId()Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lmiui/telephony/TelephonyHelper;->dZ:Ljava/lang/String;

    .line 67
    iget-object v0, p0, Lmiui/telephony/TelephonyHelper;->dZ:Ljava/lang/String;

    if-eqz v0, :cond_1

    .line 68
    iget-object v0, p0, Lmiui/telephony/TelephonyHelper;->dZ:Ljava/lang/String;

    goto :goto_0

    .line 71
    :cond_1
    const-string v0, "ro.ril.miui.imei"

    const/4 v1, 0x0

    invoke-static {v0, v1}, Lmiui/os/SystemProperties;->get(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lmiui/telephony/TelephonyHelper;->dZ:Ljava/lang/String;

    .line 72
    iget-object v0, p0, Lmiui/telephony/TelephonyHelper;->dZ:Ljava/lang/String;

    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_2

    .line 73
    iget-object v0, p0, Lmiui/telephony/TelephonyHelper;->dZ:Ljava/lang/String;

    goto :goto_0

    .line 76
    :cond_2
    invoke-static {}, Lmiui/net/ConnectivityHelper;->getInstance()Lmiui/net/ConnectivityHelper;

    move-result-object v0

    invoke-virtual {v0}, Lmiui/net/ConnectivityHelper;->isWifiOnly()Z

    move-result v0

    if-eqz v0, :cond_3

    .line 77
    invoke-static {}, Lmiui/net/ConnectivityHelper;->getInstance()Lmiui/net/ConnectivityHelper;

    move-result-object v0

    invoke-virtual {v0}, Lmiui/net/ConnectivityHelper;->getMacAddress()Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lmiui/telephony/TelephonyHelper;->dZ:Ljava/lang/String;

    .line 80
    :cond_3
    iget-object v0, p0, Lmiui/telephony/TelephonyHelper;->dZ:Ljava/lang/String;

    goto :goto_0
.end method
