.class public Lcom/miui/internal/util/PackageConstants;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field public static final LOG_TAG:Ljava/lang/String; = "miuisdk"

.field public static final PACKAGE_NAME:Ljava/lang/String; = "com.miui.sdk"

.field public static final RESOURCE_PATH:Ljava/lang/String;

.field public static final SDK_STATUS_INITIALIZED:I = 0x2

.field public static final SDK_STATUS_LOADED:I = 0x1

.field public static final SDK_STATUS_NOT_EXISTS:I = 0x0

.field public static final SDK_STATUS_STARTED:I = 0x3

.field public static sApplication:Landroid/app/Application;

.field public static sManifest:Lcom/miui/internal/core/Manifest;

.field public static sSdkStatus:I


# direct methods
.method static constructor <clinit>()V
    .locals 3

    .prologue
    .line 44
    const/4 v0, 0x0

    sput v0, Lcom/miui/internal/util/PackageConstants;->sSdkStatus:I

    .line 50
    const/4 v0, 0x0

    const-string v1, "com.miui.sdk"

    const-string v2, "miui"

    invoke-static {v0, v1, v2}, Lcom/miui/internal/util/PackageHelper;->getApkPath(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    sput-object v0, Lcom/miui/internal/util/PackageConstants;->RESOURCE_PATH:Ljava/lang/String;

    return-void
.end method

.method private constructor <init>()V
    .locals 0

    .prologue
    .line 17
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 19
    return-void
.end method

.method public static getCurrentApplication()Landroid/app/Application;
    .locals 1

    .prologue
    .line 25
    sget-object v0, Lcom/miui/internal/util/PackageConstants;->sApplication:Landroid/app/Application;

    if-nez v0, :cond_0

    .line 26
    invoke-static {}, Lcom/miui/internal/variable/Android_App_ActivityThread_class$Factory;->getInstance()Lcom/miui/internal/variable/Android_App_ActivityThread_class$Factory;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/internal/variable/Android_App_ActivityThread_class$Factory;->get()Lcom/miui/internal/variable/Android_App_ActivityThread_class;

    move-result-object v0

    invoke-interface {v0}, Lcom/miui/internal/variable/Android_App_ActivityThread_class;->currentApplication()Landroid/app/Application;

    move-result-object v0

    sput-object v0, Lcom/miui/internal/util/PackageConstants;->sApplication:Landroid/app/Application;

    .line 29
    :cond_0
    sget-object v0, Lcom/miui/internal/util/PackageConstants;->sApplication:Landroid/app/Application;

    return-object v0
.end method

.method public static getMiuiManifest()Lcom/miui/internal/core/Manifest;
    .locals 1

    .prologue
    .line 36
    sget-object v0, Lcom/miui/internal/util/PackageConstants;->sManifest:Lcom/miui/internal/core/Manifest;

    return-object v0
.end method
