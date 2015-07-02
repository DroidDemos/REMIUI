.class public Lcom/miui/internal/server/Receiver;
.super Landroid/content/BroadcastReceiver;
.source "SourceFile"


# direct methods
.method public constructor <init>()V
    .locals 0

    .prologue
    .line 16
    invoke-direct {p0}, Landroid/content/BroadcastReceiver;-><init>()V

    return-void
.end method

.method public static isActionEquals(Landroid/content/Intent;Ljava/lang/String;)Z
    .locals 1

    .prologue
    .line 18
    if-eqz p0, :cond_0

    invoke-virtual {p0}, Landroid/content/Intent;->getAction()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method


# virtual methods
.method public onReceive(Landroid/content/Context;Landroid/content/Intent;)V
    .locals 0

    .prologue
    .line 23
    invoke-static {p1, p2}, Lcom/miui/internal/server/CoreService;->onReceive(Landroid/content/Context;Landroid/content/Intent;)V

    .line 24
    invoke-static {p1, p2}, Lcom/miui/internal/analytics/UploadManager;->onReceive(Landroid/content/Context;Landroid/content/Intent;)V

    .line 25
    invoke-static {p1, p2}, Lcom/miui/internal/server/ErrorReportService;->onReceive(Landroid/content/Context;Landroid/content/Intent;)V

    .line 26
    invoke-static {p1, p2}, Lcom/miui/internal/server/DropBoxManagerService;->onReceive(Landroid/content/Context;Landroid/content/Intent;)V

    .line 27
    invoke-static {p1, p2}, Lcom/miui/internal/server/DataUpdateReceiver;->onReceive(Landroid/content/Context;Landroid/content/Intent;)V

    .line 28
    invoke-static {p1, p2}, Lcom/miui/internal/server/AssetsExtractReceiver;->onReceive(Landroid/content/Context;Landroid/content/Intent;)V

    .line 29
    return-void
.end method
