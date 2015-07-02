.class public Lmiui/widget/SimpleDialogFragment;
.super Landroid/app/DialogFragment;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lmiui/widget/SimpleDialogFragment$AlertDialogFragmentBuilder;
    }
.end annotation


# static fields
.field public static final ARG_CANCELABLE:Ljava/lang/String; = "cancelable"

.field public static final ARG_MESSAGE:Ljava/lang/String; = "msg_res_id"

.field public static final ARG_TITLE:Ljava/lang/String; = "title"

.field public static final ARG_TYPE:Ljava/lang/String; = "type"

.field public static final TYPE_ALERT:I = 0x1

.field public static final TYPE_PROGRESS:I = 0x2


# instance fields
.field private gg:Ljava/lang/String;

.field private kG:Ljava/lang/String;

.field private kI:I

.field private mCancelable:Z

.field private mOnDismissListener:Landroid/content/DialogInterface$OnDismissListener;

.field private xG:I

.field private xH:I

.field private xI:Landroid/content/DialogInterface$OnClickListener;

.field private xJ:Landroid/content/DialogInterface$OnClickListener;


# direct methods
.method public constructor <init>()V
    .locals 1

    .prologue
    .line 17
    invoke-direct {p0}, Landroid/app/DialogFragment;-><init>()V

    .line 33
    const/4 v0, 0x1

    iput-boolean v0, p0, Lmiui/widget/SimpleDialogFragment;->mCancelable:Z

    .line 119
    return-void
.end method


# virtual methods
.method public onCreate(Landroid/os/Bundle;)V
    .locals 3

    .prologue
    .line 51
    invoke-super {p0, p1}, Landroid/app/DialogFragment;->onCreate(Landroid/os/Bundle;)V

    .line 53
    invoke-virtual {p0}, Lmiui/widget/SimpleDialogFragment;->getArguments()Landroid/os/Bundle;

    move-result-object v0

    .line 54
    if-nez v0, :cond_0

    .line 55
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "no argument"

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 58
    :cond_0
    const-string v1, "type"

    invoke-virtual {v0, v1}, Landroid/os/Bundle;->getInt(Ljava/lang/String;)I

    move-result v1

    iput v1, p0, Lmiui/widget/SimpleDialogFragment;->kI:I

    .line 59
    const-string v1, "msg_res_id"

    invoke-virtual {v0, v1}, Landroid/os/Bundle;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    iput-object v1, p0, Lmiui/widget/SimpleDialogFragment;->kG:Ljava/lang/String;

    .line 60
    const-string v1, "title"

    invoke-virtual {v0, v1}, Landroid/os/Bundle;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    iput-object v1, p0, Lmiui/widget/SimpleDialogFragment;->gg:Ljava/lang/String;

    .line 61
    const-string v1, "cancelable"

    const/4 v2, 0x1

    invoke-virtual {v0, v1, v2}, Landroid/os/Bundle;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    iput-boolean v0, p0, Lmiui/widget/SimpleDialogFragment;->mCancelable:Z

    .line 62
    return-void
.end method

.method public onCreateDialog(Landroid/os/Bundle;)Landroid/app/Dialog;
    .locals 3

    .prologue
    .line 66
    iget v0, p0, Lmiui/widget/SimpleDialogFragment;->kI:I

    packed-switch v0, :pswitch_data_0

    .line 90
    new-instance v0, Ljava/lang/IllegalStateException;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "unknown dialog type:"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    iget v2, p0, Lmiui/widget/SimpleDialogFragment;->kI:I

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 68
    :pswitch_0
    new-instance v0, Landroid/app/AlertDialog$Builder;

    invoke-virtual {p0}, Lmiui/widget/SimpleDialogFragment;->getActivity()Landroid/app/Activity;

    move-result-object v1

    invoke-direct {v0, v1}, Landroid/app/AlertDialog$Builder;-><init>(Landroid/content/Context;)V

    iget-object v1, p0, Lmiui/widget/SimpleDialogFragment;->kG:Ljava/lang/String;

    invoke-virtual {v0, v1}, Landroid/app/AlertDialog$Builder;->setMessage(Ljava/lang/CharSequence;)Landroid/app/AlertDialog$Builder;

    move-result-object v0

    iget-boolean v1, p0, Lmiui/widget/SimpleDialogFragment;->mCancelable:Z

    invoke-virtual {v0, v1}, Landroid/app/AlertDialog$Builder;->setCancelable(Z)Landroid/app/AlertDialog$Builder;

    move-result-object v0

    iget-object v1, p0, Lmiui/widget/SimpleDialogFragment;->gg:Ljava/lang/String;

    invoke-virtual {v0, v1}, Landroid/app/AlertDialog$Builder;->setTitle(Ljava/lang/CharSequence;)Landroid/app/AlertDialog$Builder;

    move-result-object v0

    .line 73
    iget v1, p0, Lmiui/widget/SimpleDialogFragment;->xG:I

    if-lez v1, :cond_0

    .line 74
    iget v1, p0, Lmiui/widget/SimpleDialogFragment;->xG:I

    iget-object v2, p0, Lmiui/widget/SimpleDialogFragment;->xI:Landroid/content/DialogInterface$OnClickListener;

    invoke-virtual {v0, v1, v2}, Landroid/app/AlertDialog$Builder;->setPositiveButton(ILandroid/content/DialogInterface$OnClickListener;)Landroid/app/AlertDialog$Builder;

    .line 77
    :cond_0
    iget v1, p0, Lmiui/widget/SimpleDialogFragment;->xH:I

    if-lez v1, :cond_1

    .line 78
    iget v1, p0, Lmiui/widget/SimpleDialogFragment;->xH:I

    iget-object v2, p0, Lmiui/widget/SimpleDialogFragment;->xJ:Landroid/content/DialogInterface$OnClickListener;

    invoke-virtual {v0, v1, v2}, Landroid/app/AlertDialog$Builder;->setNegativeButton(ILandroid/content/DialogInterface$OnClickListener;)Landroid/app/AlertDialog$Builder;

    .line 81
    :cond_1
    invoke-virtual {v0}, Landroid/app/AlertDialog$Builder;->create()Landroid/app/AlertDialog;

    move-result-object v0

    .line 87
    :goto_0
    return-object v0

    .line 84
    :pswitch_1
    new-instance v0, Landroid/app/ProgressDialog;

    invoke-virtual {p0}, Lmiui/widget/SimpleDialogFragment;->getActivity()Landroid/app/Activity;

    move-result-object v1

    invoke-direct {v0, v1}, Landroid/app/ProgressDialog;-><init>(Landroid/content/Context;)V

    .line 85
    iget-object v1, p0, Lmiui/widget/SimpleDialogFragment;->kG:Ljava/lang/String;

    invoke-virtual {v0, v1}, Landroid/app/ProgressDialog;->setMessage(Ljava/lang/CharSequence;)V

    .line 86
    iget-boolean v1, p0, Lmiui/widget/SimpleDialogFragment;->mCancelable:Z

    invoke-virtual {v0, v1}, Landroid/app/ProgressDialog;->setCancelable(Z)V

    goto :goto_0

    .line 66
    :pswitch_data_0
    .packed-switch 0x1
        :pswitch_0
        :pswitch_1
    .end packed-switch
.end method

.method public onDismiss(Landroid/content/DialogInterface;)V
    .locals 1

    .prologue
    .line 101
    invoke-super {p0, p1}, Landroid/app/DialogFragment;->onDismiss(Landroid/content/DialogInterface;)V

    .line 102
    iget-object v0, p0, Lmiui/widget/SimpleDialogFragment;->mOnDismissListener:Landroid/content/DialogInterface$OnDismissListener;

    if-eqz v0, :cond_0

    .line 103
    iget-object v0, p0, Lmiui/widget/SimpleDialogFragment;->mOnDismissListener:Landroid/content/DialogInterface$OnDismissListener;

    invoke-interface {v0, p1}, Landroid/content/DialogInterface$OnDismissListener;->onDismiss(Landroid/content/DialogInterface;)V

    .line 105
    :cond_0
    return-void
.end method

.method public setNegativeButton(ILandroid/content/DialogInterface$OnClickListener;)V
    .locals 0

    .prologue
    .line 109
    iput p1, p0, Lmiui/widget/SimpleDialogFragment;->xH:I

    .line 110
    iput-object p2, p0, Lmiui/widget/SimpleDialogFragment;->xJ:Landroid/content/DialogInterface$OnClickListener;

    .line 111
    return-void
.end method

.method public setOnDismissListener(Landroid/content/DialogInterface$OnDismissListener;)V
    .locals 0

    .prologue
    .line 96
    iput-object p1, p0, Lmiui/widget/SimpleDialogFragment;->mOnDismissListener:Landroid/content/DialogInterface$OnDismissListener;

    .line 97
    return-void
.end method

.method public setPositiveButton(ILandroid/content/DialogInterface$OnClickListener;)V
    .locals 0

    .prologue
    .line 115
    iput p1, p0, Lmiui/widget/SimpleDialogFragment;->xG:I

    .line 116
    iput-object p2, p0, Lmiui/widget/SimpleDialogFragment;->xI:Landroid/content/DialogInterface$OnClickListener;

    .line 117
    return-void
.end method
