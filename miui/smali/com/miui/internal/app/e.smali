.class Lcom/miui/internal/app/e;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


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
    .line 290
    iput-object p1, p0, Lcom/miui/internal/app/e;->dV:Lcom/miui/internal/app/AlertControllerImpl;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .locals 2

    .prologue
    .line 293
    iget-object v0, p0, Lcom/miui/internal/app/e;->dV:Lcom/miui/internal/app/AlertControllerImpl;

    invoke-virtual {v0}, Lcom/miui/internal/app/AlertControllerImpl;->createMenu()Lcom/miui/internal/view/menu/MenuBuilder;

    move-result-object v0

    .line 294
    iget-object v1, p0, Lcom/miui/internal/app/e;->dV:Lcom/miui/internal/app/AlertControllerImpl;

    invoke-static {v1, v0}, Lcom/miui/internal/app/AlertControllerImpl;->a(Lcom/miui/internal/app/AlertControllerImpl;Lcom/miui/internal/view/menu/MenuBuilder;)Z

    move-result v1

    if-eqz v1, :cond_0

    iget-object v1, p0, Lcom/miui/internal/app/e;->dV:Lcom/miui/internal/app/AlertControllerImpl;

    invoke-static {v1, v0}, Lcom/miui/internal/app/AlertControllerImpl;->b(Lcom/miui/internal/app/AlertControllerImpl;Lcom/miui/internal/view/menu/MenuBuilder;)Z

    move-result v1

    if-eqz v1, :cond_0

    .line 295
    iget-object v1, p0, Lcom/miui/internal/app/e;->dV:Lcom/miui/internal/app/AlertControllerImpl;

    invoke-static {v1, v0}, Lcom/miui/internal/app/AlertControllerImpl;->c(Lcom/miui/internal/app/AlertControllerImpl;Lcom/miui/internal/view/menu/MenuBuilder;)V

    .line 299
    :goto_0
    return-void

    .line 297
    :cond_0
    iget-object v0, p0, Lcom/miui/internal/app/e;->dV:Lcom/miui/internal/app/AlertControllerImpl;

    const/4 v1, 0x0

    invoke-static {v0, v1}, Lcom/miui/internal/app/AlertControllerImpl;->c(Lcom/miui/internal/app/AlertControllerImpl;Lcom/miui/internal/view/menu/MenuBuilder;)V

    goto :goto_0
.end method
