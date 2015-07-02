.class Lcom/miui/internal/app/AlertControllerImpl$1;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroid/view/View$OnClickListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/internal/app/AlertControllerImpl;->cv()V
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
    .line 625
    iput-object p1, p0, Lcom/miui/internal/app/AlertControllerImpl$1;->dV:Lcom/miui/internal/app/AlertControllerImpl;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onClick(Landroid/view/View;)V
    .locals 1

    .prologue
    .line 628
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl$1;->dV:Lcom/miui/internal/app/AlertControllerImpl;

    invoke-static {v0}, Lcom/miui/internal/app/AlertControllerImpl;->g(Lcom/miui/internal/app/AlertControllerImpl;)Landroid/content/DialogInterface;

    move-result-object v0

    invoke-interface {v0}, Landroid/content/DialogInterface;->dismiss()V

    .line 629
    return-void
.end method
