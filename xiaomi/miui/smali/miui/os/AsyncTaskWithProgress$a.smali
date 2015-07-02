.class Lmiui/os/AsyncTaskWithProgress$a;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroid/content/DialogInterface$OnCancelListener;
.implements Landroid/content/DialogInterface$OnClickListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/os/AsyncTaskWithProgress;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x2
    name = "a"
.end annotation


# instance fields
.field final synthetic df:Lmiui/os/AsyncTaskWithProgress;


# direct methods
.method private constructor <init>(Lmiui/os/AsyncTaskWithProgress;)V
    .locals 0

    .prologue
    .line 250
    iput-object p1, p0, Lmiui/os/AsyncTaskWithProgress$a;->df:Lmiui/os/AsyncTaskWithProgress;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method synthetic constructor <init>(Lmiui/os/AsyncTaskWithProgress;Lmiui/os/AsyncTaskWithProgress$1;)V
    .locals 0

    .prologue
    .line 250
    invoke-direct {p0, p1}, Lmiui/os/AsyncTaskWithProgress$a;-><init>(Lmiui/os/AsyncTaskWithProgress;)V

    return-void
.end method


# virtual methods
.method public onCancel(Landroid/content/DialogInterface;)V
    .locals 1

    .prologue
    .line 269
    const/4 v0, -0x2

    invoke-virtual {p0, p1, v0}, Lmiui/os/AsyncTaskWithProgress$a;->onClick(Landroid/content/DialogInterface;I)V

    .line 270
    return-void
.end method

.method public onClick(Landroid/content/DialogInterface;I)V
    .locals 2

    .prologue
    .line 256
    iget-object v0, p0, Lmiui/os/AsyncTaskWithProgress$a;->df:Lmiui/os/AsyncTaskWithProgress;

    invoke-static {v0}, Lmiui/os/AsyncTaskWithProgress;->a(Lmiui/os/AsyncTaskWithProgress;)Lmiui/os/AsyncTaskWithProgress$ProgressDialogFragment;

    move-result-object v0

    if-eqz v0, :cond_0

    .line 257
    iget-object v0, p0, Lmiui/os/AsyncTaskWithProgress$a;->df:Lmiui/os/AsyncTaskWithProgress;

    invoke-static {v0}, Lmiui/os/AsyncTaskWithProgress;->a(Lmiui/os/AsyncTaskWithProgress;)Lmiui/os/AsyncTaskWithProgress$ProgressDialogFragment;

    move-result-object v0

    invoke-virtual {v0}, Lmiui/os/AsyncTaskWithProgress$ProgressDialogFragment;->getDialog()Landroid/app/Dialog;

    move-result-object v0

    .line 258
    if-eqz v0, :cond_0

    if-ne p1, v0, :cond_0

    const/4 v0, -0x2

    if-ne p2, v0, :cond_0

    .line 259
    iget-object v0, p0, Lmiui/os/AsyncTaskWithProgress$a;->df:Lmiui/os/AsyncTaskWithProgress;

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Lmiui/os/AsyncTaskWithProgress;->cancel(Z)Z

    .line 262
    :cond_0
    return-void
.end method
