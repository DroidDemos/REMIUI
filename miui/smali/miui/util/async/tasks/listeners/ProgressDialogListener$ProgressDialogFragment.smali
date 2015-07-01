.class public Lmiui/util/async/tasks/listeners/ProgressDialogListener$ProgressDialogFragment;
.super Landroid/app/DialogFragment;
.source "SourceFile"

# interfaces
.implements Landroid/content/DialogInterface$OnClickListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/util/async/tasks/listeners/ProgressDialogListener;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x9
    name = "ProgressDialogFragment"
.end annotation


# instance fields
.field private dk:Lmiui/util/async/tasks/listeners/ProgressDialogListener;


# direct methods
.method public constructor <init>()V
    .locals 0

    .prologue
    .line 284
    invoke-direct {p0}, Landroid/app/DialogFragment;-><init>()V

    return-void
.end method


# virtual methods
.method public onCancel(Landroid/content/DialogInterface;)V
    .locals 1

    .prologue
    .line 346
    iget-object v0, p0, Lmiui/util/async/tasks/listeners/ProgressDialogListener$ProgressDialogFragment;->dk:Lmiui/util/async/tasks/listeners/ProgressDialogListener;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lmiui/util/async/tasks/listeners/ProgressDialogListener$ProgressDialogFragment;->dk:Lmiui/util/async/tasks/listeners/ProgressDialogListener;

    iget-boolean v0, v0, Lmiui/util/async/tasks/listeners/ProgressDialogListener;->mCancelable:Z

    if-eqz v0, :cond_0

    .line 347
    iget-object v0, p0, Lmiui/util/async/tasks/listeners/ProgressDialogListener$ProgressDialogFragment;->dk:Lmiui/util/async/tasks/listeners/ProgressDialogListener;

    iget-object v0, v0, Lmiui/util/async/tasks/listeners/ProgressDialogListener;->uI:Ljava/lang/ref/WeakReference;

    .line 348
    if-eqz v0, :cond_0

    .line 349
    invoke-virtual {v0}, Ljava/lang/ref/WeakReference;->get()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/util/async/Task;

    .line 350
    if-eqz v0, :cond_0

    .line 351
    invoke-virtual {v0}, Lmiui/util/async/Task;->cancel()V

    .line 355
    :cond_0
    invoke-super {p0, p1}, Landroid/app/DialogFragment;->onCancel(Landroid/content/DialogInterface;)V

    .line 356
    return-void
.end method

.method public onClick(Landroid/content/DialogInterface;I)V
    .locals 0

    .prologue
    .line 396
    invoke-virtual {p0, p1}, Lmiui/util/async/tasks/listeners/ProgressDialogListener$ProgressDialogFragment;->onCancel(Landroid/content/DialogInterface;)V

    .line 397
    return-void
.end method

.method public onCreateDialog(Landroid/os/Bundle;)Landroid/app/Dialog;
    .locals 5

    .prologue
    const/4 v1, 0x0

    const/4 v4, -0x2

    .line 360
    iget-object v0, p0, Lmiui/util/async/tasks/listeners/ProgressDialogListener$ProgressDialogFragment;->dk:Lmiui/util/async/tasks/listeners/ProgressDialogListener;

    if-nez v0, :cond_0

    .line 361
    invoke-super {p0, p1}, Landroid/app/DialogFragment;->onCreateDialog(Landroid/os/Bundle;)Landroid/app/Dialog;

    move-result-object v0

    .line 391
    :goto_0
    return-object v0

    .line 364
    :cond_0
    new-instance v2, Lmiui/app/ProgressDialog;

    invoke-virtual {p0}, Lmiui/util/async/tasks/listeners/ProgressDialogListener$ProgressDialogFragment;->getActivity()Landroid/app/Activity;

    move-result-object v0

    iget-object v3, p0, Lmiui/util/async/tasks/listeners/ProgressDialogListener$ProgressDialogFragment;->dk:Lmiui/util/async/tasks/listeners/ProgressDialogListener;

    iget v3, v3, Lmiui/util/async/tasks/listeners/ProgressDialogListener;->kP:I

    invoke-direct {v2, v0, v3}, Lmiui/app/ProgressDialog;-><init>(Landroid/content/Context;I)V

    .line 365
    iget-object v0, p0, Lmiui/util/async/tasks/listeners/ProgressDialogListener$ProgressDialogFragment;->dk:Lmiui/util/async/tasks/listeners/ProgressDialogListener;

    iget v0, v0, Lmiui/util/async/tasks/listeners/ProgressDialogListener;->kQ:I

    if-eqz v0, :cond_2

    .line 366
    iget-object v0, p0, Lmiui/util/async/tasks/listeners/ProgressDialogListener$ProgressDialogFragment;->dk:Lmiui/util/async/tasks/listeners/ProgressDialogListener;

    iget v0, v0, Lmiui/util/async/tasks/listeners/ProgressDialogListener;->kQ:I

    invoke-virtual {v2, v0}, Lmiui/app/ProgressDialog;->setTitle(I)V

    .line 371
    :goto_1
    iget-object v0, p0, Lmiui/util/async/tasks/listeners/ProgressDialogListener$ProgressDialogFragment;->dk:Lmiui/util/async/tasks/listeners/ProgressDialogListener;

    iget v0, v0, Lmiui/util/async/tasks/listeners/ProgressDialogListener;->kR:I

    if-eqz v0, :cond_3

    .line 372
    iget-object v0, p0, Lmiui/util/async/tasks/listeners/ProgressDialogListener$ProgressDialogFragment;->dk:Lmiui/util/async/tasks/listeners/ProgressDialogListener;

    iget v0, v0, Lmiui/util/async/tasks/listeners/ProgressDialogListener;->kR:I

    invoke-virtual {v2, v0}, Lmiui/app/ProgressDialog;->setTitle(I)V

    .line 377
    :goto_2
    iget-object v0, p0, Lmiui/util/async/tasks/listeners/ProgressDialogListener$ProgressDialogFragment;->dk:Lmiui/util/async/tasks/listeners/ProgressDialogListener;

    iget v0, v0, Lmiui/util/async/tasks/listeners/ProgressDialogListener;->kU:I

    invoke-virtual {v2, v0}, Lmiui/app/ProgressDialog;->setProgressStyle(I)V

    .line 378
    iget-object v0, p0, Lmiui/util/async/tasks/listeners/ProgressDialogListener$ProgressDialogFragment;->dk:Lmiui/util/async/tasks/listeners/ProgressDialogListener;

    iget-boolean v0, v0, Lmiui/util/async/tasks/listeners/ProgressDialogListener;->kS:Z

    invoke-virtual {v2, v0}, Lmiui/app/ProgressDialog;->setIndeterminate(Z)V

    .line 379
    iget-object v0, p0, Lmiui/util/async/tasks/listeners/ProgressDialogListener$ProgressDialogFragment;->dk:Lmiui/util/async/tasks/listeners/ProgressDialogListener;

    iget-boolean v0, v0, Lmiui/util/async/tasks/listeners/ProgressDialogListener;->kS:Z

    if-eqz v0, :cond_1

    .line 380
    iget-object v0, p0, Lmiui/util/async/tasks/listeners/ProgressDialogListener$ProgressDialogFragment;->dk:Lmiui/util/async/tasks/listeners/ProgressDialogListener;

    iget v0, v0, Lmiui/util/async/tasks/listeners/ProgressDialogListener;->kT:I

    invoke-virtual {v2, v0}, Lmiui/app/ProgressDialog;->setMax(I)V

    .line 381
    iget-object v0, p0, Lmiui/util/async/tasks/listeners/ProgressDialogListener$ProgressDialogFragment;->dk:Lmiui/util/async/tasks/listeners/ProgressDialogListener;

    iget v0, v0, Lmiui/util/async/tasks/listeners/ProgressDialogListener;->kV:I

    invoke-virtual {v2, v0}, Lmiui/app/ProgressDialog;->setProgress(I)V

    .line 384
    :cond_1
    iget-object v0, p0, Lmiui/util/async/tasks/listeners/ProgressDialogListener$ProgressDialogFragment;->dk:Lmiui/util/async/tasks/listeners/ProgressDialogListener;

    iget-boolean v0, v0, Lmiui/util/async/tasks/listeners/ProgressDialogListener;->mCancelable:Z

    if-eqz v0, :cond_4

    .line 385
    invoke-virtual {v2}, Lmiui/app/ProgressDialog;->getContext()Landroid/content/Context;

    move-result-object v0

    const/high16 v1, 0x1040000

    invoke-virtual {v0, v1}, Landroid/content/Context;->getText(I)Ljava/lang/CharSequence;

    move-result-object v0

    invoke-virtual {v2, v4, v0, p0}, Lmiui/app/ProgressDialog;->setButton(ILjava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)V

    .line 386
    const/4 v0, 0x1

    invoke-virtual {v2, v0}, Lmiui/app/ProgressDialog;->setCancelable(Z)V

    :goto_3
    move-object v0, v2

    .line 391
    goto :goto_0

    .line 368
    :cond_2
    iget-object v0, p0, Lmiui/util/async/tasks/listeners/ProgressDialogListener$ProgressDialogFragment;->dk:Lmiui/util/async/tasks/listeners/ProgressDialogListener;

    iget-object v0, v0, Lmiui/util/async/tasks/listeners/ProgressDialogListener;->mTitle:Ljava/lang/CharSequence;

    invoke-virtual {v2, v0}, Lmiui/app/ProgressDialog;->setTitle(Ljava/lang/CharSequence;)V

    goto :goto_1

    .line 374
    :cond_3
    iget-object v0, p0, Lmiui/util/async/tasks/listeners/ProgressDialogListener$ProgressDialogFragment;->dk:Lmiui/util/async/tasks/listeners/ProgressDialogListener;

    iget-object v0, v0, Lmiui/util/async/tasks/listeners/ProgressDialogListener;->mMessage:Ljava/lang/CharSequence;

    invoke-virtual {v2, v0}, Lmiui/app/ProgressDialog;->setMessage(Ljava/lang/CharSequence;)V

    goto :goto_2

    :cond_4
    move-object v0, v1

    .line 388
    check-cast v0, Landroid/content/DialogInterface$OnClickListener;

    invoke-virtual {v2, v4, v1, v0}, Lmiui/app/ProgressDialog;->setButton(ILjava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)V

    .line 389
    const/4 v0, 0x0

    invoke-virtual {v2, v0}, Lmiui/app/ProgressDialog;->setCancelable(Z)V

    goto :goto_3
.end method

.method public onResume()V
    .locals 3

    .prologue
    .line 321
    invoke-super {p0}, Landroid/app/DialogFragment;->onResume()V

    .line 322
    sget-object v0, Lmiui/util/async/tasks/listeners/ProgressDialogListener;->HY:Ljava/util/LinkedHashMap;

    invoke-virtual {p0}, Lmiui/util/async/tasks/listeners/ProgressDialogListener$ProgressDialogFragment;->getArguments()Landroid/os/Bundle;

    move-result-object v1

    const-string v2, "ProgressDialogListener"

    invoke-virtual {v1, v2}, Landroid/os/Bundle;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/util/LinkedHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/util/async/tasks/listeners/ProgressDialogListener;

    iput-object v0, p0, Lmiui/util/async/tasks/listeners/ProgressDialogListener$ProgressDialogFragment;->dk:Lmiui/util/async/tasks/listeners/ProgressDialogListener;

    .line 323
    iget-object v0, p0, Lmiui/util/async/tasks/listeners/ProgressDialogListener$ProgressDialogFragment;->dk:Lmiui/util/async/tasks/listeners/ProgressDialogListener;

    if-nez v0, :cond_1

    .line 324
    invoke-virtual {p0}, Lmiui/util/async/tasks/listeners/ProgressDialogListener$ProgressDialogFragment;->getFragmentManager()Landroid/app/FragmentManager;

    move-result-object v0

    invoke-virtual {v0}, Landroid/app/FragmentManager;->beginTransaction()Landroid/app/FragmentTransaction;

    move-result-object v0

    .line 325
    invoke-virtual {v0, p0}, Landroid/app/FragmentTransaction;->remove(Landroid/app/Fragment;)Landroid/app/FragmentTransaction;

    .line 326
    invoke-virtual {v0}, Landroid/app/FragmentTransaction;->commit()I

    .line 342
    :cond_0
    :goto_0
    return-void

    .line 328
    :cond_1
    iget-object v0, p0, Lmiui/util/async/tasks/listeners/ProgressDialogListener$ProgressDialogFragment;->dk:Lmiui/util/async/tasks/listeners/ProgressDialogListener;

    iput-object p0, v0, Lmiui/util/async/tasks/listeners/ProgressDialogListener;->HZ:Lmiui/util/async/tasks/listeners/ProgressDialogListener$ProgressDialogFragment;

    .line 329
    iget-object v0, p0, Lmiui/util/async/tasks/listeners/ProgressDialogListener$ProgressDialogFragment;->dk:Lmiui/util/async/tasks/listeners/ProgressDialogListener;

    invoke-virtual {p0}, Lmiui/util/async/tasks/listeners/ProgressDialogListener$ProgressDialogFragment;->getFragmentManager()Landroid/app/FragmentManager;

    move-result-object v1

    iput-object v1, v0, Lmiui/util/async/tasks/listeners/ProgressDialogListener;->kO:Landroid/app/FragmentManager;

    .line 331
    iget-object v0, p0, Lmiui/util/async/tasks/listeners/ProgressDialogListener$ProgressDialogFragment;->dk:Lmiui/util/async/tasks/listeners/ProgressDialogListener;

    iget-object v1, v0, Lmiui/util/async/tasks/listeners/ProgressDialogListener;->uI:Ljava/lang/ref/WeakReference;

    .line 332
    const/4 v0, 0x0

    .line 333
    if-eqz v1, :cond_2

    .line 334
    invoke-virtual {v1}, Ljava/lang/ref/WeakReference;->get()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/util/async/Task;

    .line 337
    :cond_2
    if-eqz v0, :cond_3

    invoke-virtual {v0}, Lmiui/util/async/Task;->isRunning()Z

    move-result v0

    if-nez v0, :cond_0

    .line 338
    :cond_3
    invoke-virtual {p0}, Lmiui/util/async/tasks/listeners/ProgressDialogListener$ProgressDialogFragment;->dismiss()V

    .line 339
    sget-object v0, Lmiui/util/async/tasks/listeners/ProgressDialogListener;->HY:Ljava/util/LinkedHashMap;

    invoke-virtual {p0}, Lmiui/util/async/tasks/listeners/ProgressDialogListener$ProgressDialogFragment;->getArguments()Landroid/os/Bundle;

    move-result-object v1

    const-string v2, "ProgressDialogListener"

    invoke-virtual {v1, v2}, Landroid/os/Bundle;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/util/LinkedHashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    goto :goto_0
.end method

.method public onSaveInstanceState(Landroid/os/Bundle;)V
    .locals 2

    .prologue
    const/4 v1, 0x0

    .line 311
    invoke-super {p0, p1}, Landroid/app/DialogFragment;->onSaveInstanceState(Landroid/os/Bundle;)V

    .line 312
    iget-object v0, p0, Lmiui/util/async/tasks/listeners/ProgressDialogListener$ProgressDialogFragment;->dk:Lmiui/util/async/tasks/listeners/ProgressDialogListener;

    if-eqz v0, :cond_0

    .line 313
    iget-object v0, p0, Lmiui/util/async/tasks/listeners/ProgressDialogListener$ProgressDialogFragment;->dk:Lmiui/util/async/tasks/listeners/ProgressDialogListener;

    iput-object v1, v0, Lmiui/util/async/tasks/listeners/ProgressDialogListener;->HZ:Lmiui/util/async/tasks/listeners/ProgressDialogListener$ProgressDialogFragment;

    .line 314
    iget-object v0, p0, Lmiui/util/async/tasks/listeners/ProgressDialogListener$ProgressDialogFragment;->dk:Lmiui/util/async/tasks/listeners/ProgressDialogListener;

    iput-object v1, v0, Lmiui/util/async/tasks/listeners/ProgressDialogListener;->kO:Landroid/app/FragmentManager;

    .line 315
    iput-object v1, p0, Lmiui/util/async/tasks/listeners/ProgressDialogListener$ProgressDialogFragment;->dk:Lmiui/util/async/tasks/listeners/ProgressDialogListener;

    .line 317
    :cond_0
    return-void
.end method

.method s(I)V
    .locals 2

    .prologue
    .line 303
    invoke-virtual {p0}, Lmiui/util/async/tasks/listeners/ProgressDialogListener$ProgressDialogFragment;->getDialog()Landroid/app/Dialog;

    move-result-object v0

    .line 304
    instance-of v1, v0, Lmiui/app/ProgressDialog;

    if-eqz v1, :cond_0

    .line 305
    check-cast v0, Lmiui/app/ProgressDialog;

    invoke-virtual {v0, p1}, Lmiui/app/ProgressDialog;->setMax(I)V

    .line 307
    :cond_0
    return-void
.end method

.method setIndeterminate(Z)V
    .locals 2

    .prologue
    .line 289
    invoke-virtual {p0}, Lmiui/util/async/tasks/listeners/ProgressDialogListener$ProgressDialogFragment;->getDialog()Landroid/app/Dialog;

    move-result-object v0

    .line 290
    instance-of v1, v0, Lmiui/app/ProgressDialog;

    if-eqz v1, :cond_0

    .line 291
    check-cast v0, Lmiui/app/ProgressDialog;

    invoke-virtual {v0, p1}, Lmiui/app/ProgressDialog;->setIndeterminate(Z)V

    .line 293
    :cond_0
    return-void
.end method

.method setProgress(I)V
    .locals 2

    .prologue
    .line 296
    invoke-virtual {p0}, Lmiui/util/async/tasks/listeners/ProgressDialogListener$ProgressDialogFragment;->getDialog()Landroid/app/Dialog;

    move-result-object v0

    .line 297
    instance-of v1, v0, Lmiui/app/ProgressDialog;

    if-eqz v1, :cond_0

    .line 298
    check-cast v0, Lmiui/app/ProgressDialog;

    invoke-virtual {v0, p1}, Lmiui/app/ProgressDialog;->setProgress(I)V

    .line 300
    :cond_0
    return-void
.end method
