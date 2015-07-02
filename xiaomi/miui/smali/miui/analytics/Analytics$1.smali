.class Lmiui/analytics/Analytics$1;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroid/content/ServiceConnection;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lmiui/analytics/Analytics;->endSession()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic av:[Lcom/miui/internal/analytics/Event;

.field final synthetic aw:Landroid/content/Context;

.field final synthetic ax:Lmiui/analytics/Analytics;


# direct methods
.method constructor <init>(Lmiui/analytics/Analytics;[Lcom/miui/internal/analytics/Event;Landroid/content/Context;)V
    .locals 0

    .prologue
    .line 121
    iput-object p1, p0, Lmiui/analytics/Analytics$1;->ax:Lmiui/analytics/Analytics;

    iput-object p2, p0, Lmiui/analytics/Analytics$1;->av:[Lcom/miui/internal/analytics/Event;

    iput-object p3, p0, Lmiui/analytics/Analytics$1;->aw:Landroid/content/Context;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onServiceConnected(Landroid/content/ComponentName;Landroid/os/IBinder;)V
    .locals 3

    .prologue
    .line 128
    invoke-static {p2}, Lcom/miui/internal/analytics/IAnalytics$Stub;->asInterface(Landroid/os/IBinder;)Lcom/miui/internal/analytics/IAnalytics;

    move-result-object v0

    .line 130
    :try_start_0
    iget-object v1, p0, Lmiui/analytics/Analytics$1;->av:[Lcom/miui/internal/analytics/Event;

    invoke-interface {v0, v1}, Lcom/miui/internal/analytics/IAnalytics;->track([Lcom/miui/internal/analytics/Event;)V
    :try_end_0
    .catch Landroid/os/RemoteException; {:try_start_0 .. :try_end_0} :catch_0

    .line 135
    :goto_0
    iget-object v0, p0, Lmiui/analytics/Analytics$1;->aw:Landroid/content/Context;

    invoke-virtual {v0, p0}, Landroid/content/Context;->unbindService(Landroid/content/ServiceConnection;)V

    .line 136
    return-void

    .line 131
    :catch_0
    move-exception v0

    .line 132
    const-string v1, "Analytics"

    const-string v2, "endSession() remote exception caught"

    invoke-static {v1, v2, v0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    goto :goto_0
.end method

.method public onServiceDisconnected(Landroid/content/ComponentName;)V
    .locals 0

    .prologue
    .line 124
    return-void
.end method
