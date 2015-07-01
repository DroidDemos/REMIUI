.class public Lcom/miui/internal/app/MiuiApplication;
.super Landroid/app/Application;
.source "SourceFile"


# static fields
.field public static final PREF_KEY_MAC_ADDRESS:Ljava/lang/String; = "mac_address"

.field public static final PUBLIC_PREFERENCE_NAME:Ljava/lang/String; = "public"


# direct methods
.method public constructor <init>()V
    .locals 1

    .prologue
    .line 20
    invoke-direct {p0}, Landroid/app/Application;-><init>()V

    .line 21
    const/4 v0, 0x0

    invoke-static {p0, v0}, Lcom/miui/internal/core/SdkManager;->initialize(Landroid/app/Application;Ljava/util/Map;)I

    .line 22
    return-void
.end method


# virtual methods
.method public onCreate()V
    .locals 1

    .prologue
    .line 26
    invoke-super {p0}, Landroid/app/Application;->onCreate()V

    .line 27
    const/4 v0, 0x0

    invoke-static {v0}, Lcom/miui/internal/core/SdkManager;->start(Ljava/util/Map;)I

    .line 28
    return-void
.end method
