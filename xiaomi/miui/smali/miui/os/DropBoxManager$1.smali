.class Lmiui/os/DropBoxManager$1;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroid/content/ServiceConnection;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lmiui/os/DropBoxManager;-><init>()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic sg:Lmiui/os/DropBoxManager;


# direct methods
.method constructor <init>(Lmiui/os/DropBoxManager;)V
    .locals 0

    .prologue
    .line 278
    iput-object p1, p0, Lmiui/os/DropBoxManager$1;->sg:Lmiui/os/DropBoxManager;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onServiceConnected(Landroid/content/ComponentName;Landroid/os/IBinder;)V
    .locals 3

    .prologue
    .line 281
    invoke-static {p2}, Lcom/miui/internal/server/IDropBoxManagerService$Stub;->asInterface(Landroid/os/IBinder;)Lcom/miui/internal/server/IDropBoxManagerService;

    move-result-object v1

    .line 283
    :try_start_0
    iget-object v0, p0, Lmiui/os/DropBoxManager$1;->sg:Lmiui/os/DropBoxManager;

    invoke-static {v0}, Lmiui/os/DropBoxManager;->a(Lmiui/os/DropBoxManager;)Ljava/util/concurrent/ArrayBlockingQueue;

    move-result-object v0

    invoke-virtual {v0}, Ljava/util/concurrent/ArrayBlockingQueue;->poll()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/os/DropBoxManager$Entry;
    :try_end_0
    .catch Landroid/os/RemoteException; {:try_start_0 .. :try_end_0} :catch_0

    .line 284
    :goto_0
    if-eqz v0, :cond_0

    .line 286
    :try_start_1
    invoke-interface {v1, v0}, Lcom/miui/internal/server/IDropBoxManagerService;->add(Lmiui/os/DropBoxManager$Entry;)V
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 288
    :try_start_2
    invoke-virtual {v0}, Lmiui/os/DropBoxManager$Entry;->close()V

    .line 290
    iget-object v0, p0, Lmiui/os/DropBoxManager$1;->sg:Lmiui/os/DropBoxManager;

    invoke-static {v0}, Lmiui/os/DropBoxManager;->a(Lmiui/os/DropBoxManager;)Ljava/util/concurrent/ArrayBlockingQueue;

    move-result-object v0

    invoke-virtual {v0}, Ljava/util/concurrent/ArrayBlockingQueue;->poll()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/os/DropBoxManager$Entry;

    goto :goto_0

    .line 288
    :catchall_0
    move-exception v1

    invoke-virtual {v0}, Lmiui/os/DropBoxManager$Entry;->close()V

    throw v1
    :try_end_2
    .catch Landroid/os/RemoteException; {:try_start_2 .. :try_end_2} :catch_0

    .line 293
    :catch_0
    move-exception v0

    .line 294
    const-string v1, "DropBoxManager"

    const-string v2, "Call remote method \'add\' failed"

    invoke-static {v1, v2, v0}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    .line 296
    :goto_1
    return-void

    .line 292
    :cond_0
    :try_start_3
    invoke-static {}, Lcom/miui/internal/util/PackageConstants;->getCurrentApplication()Landroid/app/Application;

    move-result-object v0

    invoke-virtual {v0, p0}, Landroid/app/Application;->unbindService(Landroid/content/ServiceConnection;)V
    :try_end_3
    .catch Landroid/os/RemoteException; {:try_start_3 .. :try_end_3} :catch_0

    goto :goto_1
.end method

.method public onServiceDisconnected(Landroid/content/ComponentName;)V
    .locals 0

    .prologue
    .line 300
    return-void
.end method
