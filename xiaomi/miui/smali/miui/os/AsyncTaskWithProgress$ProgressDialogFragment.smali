.class public Lmiui/os/AsyncTaskWithProgress$ProgressDialogFragment;
.super Landroid/app/DialogFragment;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/os/AsyncTaskWithProgress;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x9
    name = "ProgressDialogFragment"
.end annotation


# instance fields
.field private Qo:Lmiui/os/AsyncTaskWithProgress;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lmiui/os/AsyncTaskWithProgress",
            "<**>;"
        }
    .end annotation
.end field


# direct methods
.method public constructor <init>()V
    .locals 0

    .prologue
    .line 274
    invoke-direct {p0}, Landroid/app/DialogFragment;-><init>()V

    return-void
.end method

.method static E(Ljava/lang/String;)Lmiui/os/AsyncTaskWithProgress$ProgressDialogFragment;
    .locals 3

    .prologue
    .line 277
    new-instance v0, Lmiui/os/AsyncTaskWithProgress$ProgressDialogFragment;

    invoke-direct {v0}, Lmiui/os/AsyncTaskWithProgress$ProgressDialogFragment;-><init>()V

    .line 278
    new-instance v1, Landroid/os/Bundle;

    invoke-direct {v1}, Landroid/os/Bundle;-><init>()V

    .line 279
    const-string v2, "task"

    invoke-virtual {v1, v2, p0}, Landroid/os/Bundle;->putString(Ljava/lang/String;Ljava/lang/String;)V

    .line 280
    invoke-virtual {v0, v1}, Lmiui/os/AsyncTaskWithProgress$ProgressDialogFragment;->setArguments(Landroid/os/Bundle;)V

    .line 281
    return-object v0
.end method


# virtual methods
.method public onCancel(Landroid/content/DialogInterface;)V
    .locals 1

    .prologue
    .line 335
    iget-object v0, p0, Lmiui/os/AsyncTaskWithProgress$ProgressDialogFragment;->Qo:Lmiui/os/AsyncTaskWithProgress;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lmiui/os/AsyncTaskWithProgress$ProgressDialogFragment;->Qo:Lmiui/os/AsyncTaskWithProgress;

    invoke-static {v0}, Lmiui/os/AsyncTaskWithProgress;->b(Lmiui/os/AsyncTaskWithProgress;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 336
    iget-object v0, p0, Lmiui/os/AsyncTaskWithProgress$ProgressDialogFragment;->Qo:Lmiui/os/AsyncTaskWithProgress;

    invoke-static {v0}, Lmiui/os/AsyncTaskWithProgress;->c(Lmiui/os/AsyncTaskWithProgress;)Lmiui/os/AsyncTaskWithProgress$a;

    move-result-object v0

    invoke-virtual {v0, p1}, Lmiui/os/AsyncTaskWithProgress$a;->onCancel(Landroid/content/DialogInterface;)V

    .line 338
    :cond_0
    invoke-super {p0, p1}, Landroid/app/DialogFragment;->onCancel(Landroid/content/DialogInterface;)V

    .line 339
    return-void
.end method

.method public onCreate(Landroid/os/Bundle;)V
    .locals 3

    .prologue
    .line 291
    invoke-super {p0, p1}, Landroid/app/DialogFragment;->onCreate(Landroid/os/Bundle;)V

    .line 292
    invoke-static {}, Lmiui/os/AsyncTaskWithProgress;->aF()Ljava/util/HashMap;

    move-result-object v0

    invoke-virtual {p0}, Lmiui/os/AsyncTaskWithProgress$ProgressDialogFragment;->getArguments()Landroid/os/Bundle;

    move-result-object v1

    const-string v2, "task"

    invoke-virtual {v1, v2}, Landroid/os/Bundle;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/util/HashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/os/AsyncTaskWithProgress;

    iput-object v0, p0, Lmiui/os/AsyncTaskWithProgress$ProgressDialogFragment;->Qo:Lmiui/os/AsyncTaskWithProgress;

    .line 293
    iget-object v0, p0, Lmiui/os/AsyncTaskWithProgress$ProgressDialogFragment;->Qo:Lmiui/os/AsyncTaskWithProgress;

    if-nez v0, :cond_0

    .line 294
    invoke-virtual {p0}, Lmiui/os/AsyncTaskWithProgress$ProgressDialogFragment;->getFragmentManager()Landroid/app/FragmentManager;

    move-result-object v0

    invoke-virtual {v0}, Landroid/app/FragmentManager;->beginTransaction()Landroid/app/FragmentTransaction;

    move-result-object v0

    .line 295
    invoke-virtual {v0, p0}, Landroid/app/FragmentTransaction;->remove(Landroid/app/Fragment;)Landroid/app/FragmentTransaction;

    .line 296
    invoke-virtual {v0}, Landroid/app/FragmentTransaction;->commit()I

    .line 298
    :cond_0
    return-void
.end method

.method public onCreateDialog(Landroid/os/Bundle;)Landroid/app/Dialog;
    .locals 5

    .prologue
    const/4 v1, 0x0

    const/4 v4, -0x2

    .line 346
    iget-object v0, p0, Lmiui/os/AsyncTaskWithProgress$ProgressDialogFragment;->Qo:Lmiui/os/AsyncTaskWithProgress;

    if-nez v0, :cond_0

    .line 347
    invoke-super {p0, p1}, Landroid/app/DialogFragment;->onCreateDialog(Landroid/os/Bundle;)Landroid/app/Dialog;

    move-result-object v0

    .line 374
    :goto_0
    return-object v0

    .line 350
    :cond_0
    new-instance v2, Lmiui/app/ProgressDialog;

    invoke-virtual {p0}, Lmiui/os/AsyncTaskWithProgress$ProgressDialogFragment;->getActivity()Landroid/app/Activity;

    move-result-object v0

    iget-object v3, p0, Lmiui/os/AsyncTaskWithProgress$ProgressDialogFragment;->Qo:Lmiui/os/AsyncTaskWithProgress;

    invoke-static {v3}, Lmiui/os/AsyncTaskWithProgress;->d(Lmiui/os/AsyncTaskWithProgress;)I

    move-result v3

    invoke-direct {v2, v0, v3}, Lmiui/app/ProgressDialog;-><init>(Landroid/content/Context;I)V

    .line 351
    iget-object v0, p0, Lmiui/os/AsyncTaskWithProgress$ProgressDialogFragment;->Qo:Lmiui/os/AsyncTaskWithProgress;

    invoke-static {v0}, Lmiui/os/AsyncTaskWithProgress;->e(Lmiui/os/AsyncTaskWithProgress;)I

    move-result v0

    if-eqz v0, :cond_2

    .line 352
    iget-object v0, p0, Lmiui/os/AsyncTaskWithProgress$ProgressDialogFragment;->Qo:Lmiui/os/AsyncTaskWithProgress;

    invoke-static {v0}, Lmiui/os/AsyncTaskWithProgress;->e(Lmiui/os/AsyncTaskWithProgress;)I

    move-result v0

    invoke-virtual {v2, v0}, Lmiui/app/ProgressDialog;->setTitle(I)V

    .line 356
    :goto_1
    iget-object v0, p0, Lmiui/os/AsyncTaskWithProgress$ProgressDialogFragment;->Qo:Lmiui/os/AsyncTaskWithProgress;

    invoke-static {v0}, Lmiui/os/AsyncTaskWithProgress;->g(Lmiui/os/AsyncTaskWithProgress;)I

    move-result v0

    if-eqz v0, :cond_3

    .line 357
    invoke-virtual {p0}, Lmiui/os/AsyncTaskWithProgress$ProgressDialogFragment;->getActivity()Landroid/app/Activity;

    move-result-object v0

    iget-object v3, p0, Lmiui/os/AsyncTaskWithProgress$ProgressDialogFragment;->Qo:Lmiui/os/AsyncTaskWithProgress;

    invoke-static {v3}, Lmiui/os/AsyncTaskWithProgress;->g(Lmiui/os/AsyncTaskWithProgress;)I

    move-result v3

    invoke-virtual {v0, v3}, Landroid/app/Activity;->getText(I)Ljava/lang/CharSequence;

    move-result-object v0

    invoke-virtual {v2, v0}, Lmiui/app/ProgressDialog;->setMessage(Ljava/lang/CharSequence;)V

    .line 361
    :goto_2
    iget-object v0, p0, Lmiui/os/AsyncTaskWithProgress$ProgressDialogFragment;->Qo:Lmiui/os/AsyncTaskWithProgress;

    invoke-static {v0}, Lmiui/os/AsyncTaskWithProgress;->i(Lmiui/os/AsyncTaskWithProgress;)I

    move-result v0

    invoke-virtual {v2, v0}, Lmiui/app/ProgressDialog;->setProgressStyle(I)V

    .line 362
    iget-object v0, p0, Lmiui/os/AsyncTaskWithProgress$ProgressDialogFragment;->Qo:Lmiui/os/AsyncTaskWithProgress;

    invoke-static {v0}, Lmiui/os/AsyncTaskWithProgress;->j(Lmiui/os/AsyncTaskWithProgress;)Z

    move-result v0

    invoke-virtual {v2, v0}, Lmiui/app/ProgressDialog;->setIndeterminate(Z)V

    .line 363
    iget-object v0, p0, Lmiui/os/AsyncTaskWithProgress$ProgressDialogFragment;->Qo:Lmiui/os/AsyncTaskWithProgress;

    invoke-static {v0}, Lmiui/os/AsyncTaskWithProgress;->j(Lmiui/os/AsyncTaskWithProgress;)Z

    move-result v0

    if-nez v0, :cond_1

    .line 364
    iget-object v0, p0, Lmiui/os/AsyncTaskWithProgress$ProgressDialogFragment;->Qo:Lmiui/os/AsyncTaskWithProgress;

    invoke-static {v0}, Lmiui/os/AsyncTaskWithProgress;->k(Lmiui/os/AsyncTaskWithProgress;)I

    move-result v0

    invoke-virtual {v2, v0}, Lmiui/app/ProgressDialog;->setMax(I)V

    .line 365
    iget-object v0, p0, Lmiui/os/AsyncTaskWithProgress$ProgressDialogFragment;->Qo:Lmiui/os/AsyncTaskWithProgress;

    invoke-static {v0}, Lmiui/os/AsyncTaskWithProgress;->l(Lmiui/os/AsyncTaskWithProgress;)I

    move-result v0

    invoke-virtual {v2, v0}, Lmiui/app/ProgressDialog;->setProgress(I)V

    .line 367
    :cond_1
    iget-object v0, p0, Lmiui/os/AsyncTaskWithProgress$ProgressDialogFragment;->Qo:Lmiui/os/AsyncTaskWithProgress;

    invoke-static {v0}, Lmiui/os/AsyncTaskWithProgress;->b(Lmiui/os/AsyncTaskWithProgress;)Z

    move-result v0

    if-eqz v0, :cond_4

    .line 368
    invoke-virtual {v2}, Lmiui/app/ProgressDialog;->getContext()Landroid/content/Context;

    move-result-object v0

    const/high16 v1, 0x1040000

    invoke-virtual {v0, v1}, Landroid/content/Context;->getText(I)Ljava/lang/CharSequence;

    move-result-object v0

    iget-object v1, p0, Lmiui/os/AsyncTaskWithProgress$ProgressDialogFragment;->Qo:Lmiui/os/AsyncTaskWithProgress;

    invoke-static {v1}, Lmiui/os/AsyncTaskWithProgress;->c(Lmiui/os/AsyncTaskWithProgress;)Lmiui/os/AsyncTaskWithProgress$a;

    move-result-object v1

    invoke-virtual {v2, v4, v0, v1}, Lmiui/app/ProgressDialog;->setButton(ILjava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)V

    .line 369
    const/4 v0, 0x1

    invoke-virtual {v2, v0}, Lmiui/app/ProgressDialog;->setCancelable(Z)V

    :goto_3
    move-object v0, v2

    .line 374
    goto/16 :goto_0

    .line 354
    :cond_2
    iget-object v0, p0, Lmiui/os/AsyncTaskWithProgress$ProgressDialogFragment;->Qo:Lmiui/os/AsyncTaskWithProgress;

    invoke-static {v0}, Lmiui/os/AsyncTaskWithProgress;->f(Lmiui/os/AsyncTaskWithProgress;)Ljava/lang/CharSequence;

    move-result-object v0

    invoke-virtual {v2, v0}, Lmiui/app/ProgressDialog;->setTitle(Ljava/lang/CharSequence;)V

    goto :goto_1

    .line 359
    :cond_3
    iget-object v0, p0, Lmiui/os/AsyncTaskWithProgress$ProgressDialogFragment;->Qo:Lmiui/os/AsyncTaskWithProgress;

    invoke-static {v0}, Lmiui/os/AsyncTaskWithProgress;->h(Lmiui/os/AsyncTaskWithProgress;)Ljava/lang/CharSequence;

    move-result-object v0

    invoke-virtual {v2, v0}, Lmiui/app/ProgressDialog;->setMessage(Ljava/lang/CharSequence;)V

    goto :goto_2

    :cond_4
    move-object v0, v1

    .line 371
    check-cast v0, Landroid/content/DialogInterface$OnClickListener;

    invoke-virtual {v2, v4, v1, v0}, Lmiui/app/ProgressDialog;->setButton(ILjava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)V

    .line 372
    const/4 v0, 0x0

    invoke-virtual {v2, v0}, Lmiui/app/ProgressDialog;->setCancelable(Z)V

    goto :goto_3
.end method

.method public onStart()V
    .locals 1

    .prologue
    .line 305
    invoke-super {p0}, Landroid/app/DialogFragment;->onStart()V

    .line 306
    iget-object v0, p0, Lmiui/os/AsyncTaskWithProgress$ProgressDialogFragment;->Qo:Lmiui/os/AsyncTaskWithProgress;

    if-eqz v0, :cond_0

    .line 307
    iget-object v0, p0, Lmiui/os/AsyncTaskWithProgress$ProgressDialogFragment;->Qo:Lmiui/os/AsyncTaskWithProgress;

    invoke-static {v0, p0}, Lmiui/os/AsyncTaskWithProgress;->a(Lmiui/os/AsyncTaskWithProgress;Lmiui/os/AsyncTaskWithProgress$ProgressDialogFragment;)Lmiui/os/AsyncTaskWithProgress$ProgressDialogFragment;

    .line 309
    :cond_0
    return-void
.end method

.method public onStop()V
    .locals 2

    .prologue
    .line 316
    iget-object v0, p0, Lmiui/os/AsyncTaskWithProgress$ProgressDialogFragment;->Qo:Lmiui/os/AsyncTaskWithProgress;

    if-eqz v0, :cond_0

    .line 317
    iget-object v0, p0, Lmiui/os/AsyncTaskWithProgress$ProgressDialogFragment;->Qo:Lmiui/os/AsyncTaskWithProgress;

    const/4 v1, 0x0

    invoke-static {v0, v1}, Lmiui/os/AsyncTaskWithProgress;->a(Lmiui/os/AsyncTaskWithProgress;Lmiui/os/AsyncTaskWithProgress$ProgressDialogFragment;)Lmiui/os/AsyncTaskWithProgress$ProgressDialogFragment;

    .line 319
    :cond_0
    invoke-super {p0}, Landroid/app/DialogFragment;->onStop()V

    .line 320
    return-void
.end method

.method setProgress(I)V
    .locals 2

    .prologue
    .line 323
    invoke-virtual {p0}, Lmiui/os/AsyncTaskWithProgress$ProgressDialogFragment;->getDialog()Landroid/app/Dialog;

    move-result-object v0

    .line 324
    instance-of v1, v0, Lmiui/app/ProgressDialog;

    if-eqz v1, :cond_0

    .line 325
    check-cast v0, Lmiui/app/ProgressDialog;

    .line 326
    invoke-virtual {v0, p1}, Lmiui/app/ProgressDialog;->setProgress(I)V

    .line 328
    :cond_0
    return-void
.end method
