.class Lcom/miui/internal/server/DropBoxManagerService$2;
.super Landroid/os/Handler;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/internal/server/DropBoxManagerService;-><init>()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic so:Lcom/miui/internal/server/DropBoxManagerService;


# direct methods
.method constructor <init>(Lcom/miui/internal/server/DropBoxManagerService;)V
    .locals 0

    .prologue
    .line 112
    iput-object p1, p0, Lcom/miui/internal/server/DropBoxManagerService$2;->so:Lcom/miui/internal/server/DropBoxManagerService;

    invoke-direct {p0}, Landroid/os/Handler;-><init>()V

    return-void
.end method


# virtual methods
.method public handleMessage(Landroid/os/Message;)V
    .locals 3

    .prologue
    .line 115
    iget v0, p1, Landroid/os/Message;->what:I

    const/4 v1, 0x1

    if-ne v0, v1, :cond_0

    .line 116
    iget-object v0, p0, Lcom/miui/internal/server/DropBoxManagerService$2;->so:Lcom/miui/internal/server/DropBoxManagerService;

    invoke-static {v0}, Lcom/miui/internal/server/DropBoxManagerService;->a(Lcom/miui/internal/server/DropBoxManagerService;)Landroid/content/Context;

    move-result-object v1

    iget-object v0, p1, Landroid/os/Message;->obj:Ljava/lang/Object;

    check-cast v0, Landroid/content/Intent;

    const-string v2, "miui.permission.READ_LOGS"

    invoke-virtual {v1, v0, v2}, Landroid/content/Context;->sendBroadcast(Landroid/content/Intent;Ljava/lang/String;)V

    .line 119
    :cond_0
    return-void
.end method
