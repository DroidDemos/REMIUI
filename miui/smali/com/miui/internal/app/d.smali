.class Lcom/miui/internal/app/d;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroid/view/View$OnClickListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/internal/app/AlertControllerImpl;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic dV:Lcom/miui/internal/app/AlertControllerImpl;


# direct methods
.method constructor <init>(Lcom/miui/internal/app/AlertControllerImpl;)V
    .locals 0

    .prologue
    .line 130
    iput-object p1, p0, Lcom/miui/internal/app/d;->dV:Lcom/miui/internal/app/AlertControllerImpl;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onClick(Landroid/view/View;)V
    .locals 3

    .prologue
    .line 132
    const/4 v0, 0x0

    .line 133
    iget-object v1, p0, Lcom/miui/internal/app/d;->dV:Lcom/miui/internal/app/AlertControllerImpl;

    invoke-static {v1}, Lcom/miui/internal/app/AlertControllerImpl;->a(Lcom/miui/internal/app/AlertControllerImpl;)Landroid/widget/Button;

    move-result-object v1

    if-ne p1, v1, :cond_2

    iget-object v1, p0, Lcom/miui/internal/app/d;->dV:Lcom/miui/internal/app/AlertControllerImpl;

    invoke-static {v1}, Lcom/miui/internal/app/AlertControllerImpl;->b(Lcom/miui/internal/app/AlertControllerImpl;)Landroid/os/Message;

    move-result-object v1

    if-eqz v1, :cond_2

    .line 134
    iget-object v0, p0, Lcom/miui/internal/app/d;->dV:Lcom/miui/internal/app/AlertControllerImpl;

    invoke-static {v0}, Lcom/miui/internal/app/AlertControllerImpl;->b(Lcom/miui/internal/app/AlertControllerImpl;)Landroid/os/Message;

    move-result-object v0

    invoke-static {v0}, Landroid/os/Message;->obtain(Landroid/os/Message;)Landroid/os/Message;

    move-result-object v0

    .line 140
    :cond_0
    :goto_0
    if-eqz v0, :cond_1

    .line 141
    invoke-virtual {v0}, Landroid/os/Message;->sendToTarget()V

    .line 145
    :cond_1
    iget-object v0, p0, Lcom/miui/internal/app/d;->dV:Lcom/miui/internal/app/AlertControllerImpl;

    invoke-static {v0}, Lcom/miui/internal/app/AlertControllerImpl;->h(Lcom/miui/internal/app/AlertControllerImpl;)Landroid/os/Handler;

    move-result-object v0

    const/4 v1, 0x1

    iget-object v2, p0, Lcom/miui/internal/app/d;->dV:Lcom/miui/internal/app/AlertControllerImpl;

    invoke-static {v2}, Lcom/miui/internal/app/AlertControllerImpl;->g(Lcom/miui/internal/app/AlertControllerImpl;)Landroid/content/DialogInterface;

    move-result-object v2

    invoke-virtual {v0, v1, v2}, Landroid/os/Handler;->obtainMessage(ILjava/lang/Object;)Landroid/os/Message;

    move-result-object v0

    invoke-virtual {v0}, Landroid/os/Message;->sendToTarget()V

    .line 147
    return-void

    .line 135
    :cond_2
    iget-object v1, p0, Lcom/miui/internal/app/d;->dV:Lcom/miui/internal/app/AlertControllerImpl;

    invoke-static {v1}, Lcom/miui/internal/app/AlertControllerImpl;->c(Lcom/miui/internal/app/AlertControllerImpl;)Landroid/widget/Button;

    move-result-object v1

    if-ne p1, v1, :cond_3

    iget-object v1, p0, Lcom/miui/internal/app/d;->dV:Lcom/miui/internal/app/AlertControllerImpl;

    invoke-static {v1}, Lcom/miui/internal/app/AlertControllerImpl;->d(Lcom/miui/internal/app/AlertControllerImpl;)Landroid/os/Message;

    move-result-object v1

    if-eqz v1, :cond_3

    .line 136
    iget-object v0, p0, Lcom/miui/internal/app/d;->dV:Lcom/miui/internal/app/AlertControllerImpl;

    invoke-static {v0}, Lcom/miui/internal/app/AlertControllerImpl;->d(Lcom/miui/internal/app/AlertControllerImpl;)Landroid/os/Message;

    move-result-object v0

    invoke-static {v0}, Landroid/os/Message;->obtain(Landroid/os/Message;)Landroid/os/Message;

    move-result-object v0

    goto :goto_0

    .line 137
    :cond_3
    iget-object v1, p0, Lcom/miui/internal/app/d;->dV:Lcom/miui/internal/app/AlertControllerImpl;

    invoke-static {v1}, Lcom/miui/internal/app/AlertControllerImpl;->e(Lcom/miui/internal/app/AlertControllerImpl;)Landroid/widget/Button;

    move-result-object v1

    if-ne p1, v1, :cond_0

    iget-object v1, p0, Lcom/miui/internal/app/d;->dV:Lcom/miui/internal/app/AlertControllerImpl;

    invoke-static {v1}, Lcom/miui/internal/app/AlertControllerImpl;->f(Lcom/miui/internal/app/AlertControllerImpl;)Landroid/os/Message;

    move-result-object v1

    if-eqz v1, :cond_0

    .line 138
    iget-object v0, p0, Lcom/miui/internal/app/d;->dV:Lcom/miui/internal/app/AlertControllerImpl;

    invoke-static {v0}, Lcom/miui/internal/app/AlertControllerImpl;->f(Lcom/miui/internal/app/AlertControllerImpl;)Landroid/os/Message;

    move-result-object v0

    invoke-static {v0}, Landroid/os/Message;->obtain(Landroid/os/Message;)Landroid/os/Message;

    move-result-object v0

    goto :goto_0
.end method
