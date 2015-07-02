.class public Lcom/miui/internal/variable/AlertControllerWrapper;
.super Lcom/android/internal/app/AlertController;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;
    }
.end annotation


# instance fields
.field Ca:Lcom/miui/internal/app/AlertControllerImpl;


# direct methods
.method public constructor <init>(Landroid/content/Context;Landroid/content/DialogInterface;Landroid/view/Window;)V
    .locals 1

    .prologue
    .line 36
    invoke-direct {p0, p1, p2, p3}, Lcom/android/internal/app/AlertController;-><init>(Landroid/content/Context;Landroid/content/DialogInterface;Landroid/view/Window;)V

    .line 37
    new-instance v0, Lcom/miui/internal/app/AlertControllerImpl;

    invoke-direct {v0, p1, p2, p3}, Lcom/miui/internal/app/AlertControllerImpl;-><init>(Landroid/content/Context;Landroid/content/DialogInterface;Landroid/view/Window;)V

    iput-object v0, p0, Lcom/miui/internal/variable/AlertControllerWrapper;->Ca:Lcom/miui/internal/app/AlertControllerImpl;

    .line 38
    return-void
.end method


# virtual methods
.method public getButton(I)Landroid/widget/Button;
    .locals 1

    .prologue
    .line 94
    iget-object v0, p0, Lcom/miui/internal/variable/AlertControllerWrapper;->Ca:Lcom/miui/internal/app/AlertControllerImpl;

    invoke-virtual {v0, p1}, Lcom/miui/internal/app/AlertControllerImpl;->getButton(I)Landroid/widget/Button;

    move-result-object v0

    return-object v0
.end method

.method public getCheckedItems()[Z
    .locals 1

    .prologue
    .line 108
    iget-object v0, p0, Lcom/miui/internal/variable/AlertControllerWrapper;->Ca:Lcom/miui/internal/app/AlertControllerImpl;

    invoke-virtual {v0}, Lcom/miui/internal/app/AlertControllerImpl;->getCheckedItems()[Z

    move-result-object v0

    return-object v0
.end method

.method public getImpl()Lcom/miui/internal/app/AlertControllerImpl;
    .locals 1

    .prologue
    .line 41
    iget-object v0, p0, Lcom/miui/internal/variable/AlertControllerWrapper;->Ca:Lcom/miui/internal/app/AlertControllerImpl;

    return-object v0
.end method

.method public getListView()Landroid/widget/ListView;
    .locals 1

    .prologue
    .line 89
    iget-object v0, p0, Lcom/miui/internal/variable/AlertControllerWrapper;->Ca:Lcom/miui/internal/app/AlertControllerImpl;

    invoke-virtual {v0}, Lcom/miui/internal/app/AlertControllerImpl;->getListView()Landroid/widget/ListView;

    move-result-object v0

    return-object v0
.end method

.method public getMessageView()Landroid/widget/TextView;
    .locals 1

    .prologue
    .line 65
    iget-object v0, p0, Lcom/miui/internal/variable/AlertControllerWrapper;->Ca:Lcom/miui/internal/app/AlertControllerImpl;

    invoke-virtual {v0}, Lcom/miui/internal/app/AlertControllerImpl;->getMessageView()Landroid/widget/TextView;

    move-result-object v0

    return-object v0
.end method

.method public installContent()V
    .locals 1

    .prologue
    .line 46
    iget-object v0, p0, Lcom/miui/internal/variable/AlertControllerWrapper;->Ca:Lcom/miui/internal/app/AlertControllerImpl;

    invoke-virtual {v0}, Lcom/miui/internal/app/AlertControllerImpl;->installContent()V

    .line 47
    return-void
.end method

.method public isChecked()Z
    .locals 1

    .prologue
    .line 116
    iget-object v0, p0, Lcom/miui/internal/variable/AlertControllerWrapper;->Ca:Lcom/miui/internal/app/AlertControllerImpl;

    invoke-virtual {v0}, Lcom/miui/internal/app/AlertControllerImpl;->isChecked()Z

    move-result v0

    return v0
.end method

.method public onKeyDown(ILandroid/view/KeyEvent;)Z
    .locals 1

    .prologue
    .line 99
    iget-object v0, p0, Lcom/miui/internal/variable/AlertControllerWrapper;->Ca:Lcom/miui/internal/app/AlertControllerImpl;

    invoke-virtual {v0, p1, p2}, Lcom/miui/internal/app/AlertControllerImpl;->onKeyDown(ILandroid/view/KeyEvent;)Z

    move-result v0

    return v0
.end method

.method public onKeyUp(ILandroid/view/KeyEvent;)Z
    .locals 1

    .prologue
    .line 104
    iget-object v0, p0, Lcom/miui/internal/variable/AlertControllerWrapper;->Ca:Lcom/miui/internal/app/AlertControllerImpl;

    invoke-virtual {v0, p1, p2}, Lcom/miui/internal/app/AlertControllerImpl;->onKeyUp(ILandroid/view/KeyEvent;)Z

    move-result v0

    return v0
.end method

.method public setButton(ILjava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;Landroid/os/Message;)V
    .locals 1

    .prologue
    .line 76
    iget-object v0, p0, Lcom/miui/internal/variable/AlertControllerWrapper;->Ca:Lcom/miui/internal/app/AlertControllerImpl;

    invoke-virtual {v0, p1, p2, p3, p4}, Lcom/miui/internal/app/AlertControllerImpl;->setButton(ILjava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;Landroid/os/Message;)V

    .line 77
    return-void
.end method

.method public setCheckBox(ZLjava/lang/CharSequence;)V
    .locals 1

    .prologue
    .line 112
    iget-object v0, p0, Lcom/miui/internal/variable/AlertControllerWrapper;->Ca:Lcom/miui/internal/app/AlertControllerImpl;

    invoke-virtual {v0, p1, p2}, Lcom/miui/internal/app/AlertControllerImpl;->setCheckBox(ZLjava/lang/CharSequence;)V

    .line 113
    return-void
.end method

.method public setCustomTitle(Landroid/view/View;)V
    .locals 1

    .prologue
    .line 56
    iget-object v0, p0, Lcom/miui/internal/variable/AlertControllerWrapper;->Ca:Lcom/miui/internal/app/AlertControllerImpl;

    invoke-virtual {v0, p1}, Lcom/miui/internal/app/AlertControllerImpl;->setCustomTitle(Landroid/view/View;)V

    .line 57
    return-void
.end method

.method public setIcon(I)V
    .locals 0

    .prologue
    .line 81
    return-void
.end method

.method public setInverseBackgroundForced(Z)V
    .locals 0

    .prologue
    .line 85
    return-void
.end method

.method public setMessage(Ljava/lang/CharSequence;)V
    .locals 1

    .prologue
    .line 61
    iget-object v0, p0, Lcom/miui/internal/variable/AlertControllerWrapper;->Ca:Lcom/miui/internal/app/AlertControllerImpl;

    invoke-virtual {v0, p1}, Lcom/miui/internal/app/AlertControllerImpl;->setMessage(Ljava/lang/CharSequence;)V

    .line 62
    return-void
.end method

.method public setTitle(Ljava/lang/CharSequence;)V
    .locals 1

    .prologue
    .line 51
    iget-object v0, p0, Lcom/miui/internal/variable/AlertControllerWrapper;->Ca:Lcom/miui/internal/app/AlertControllerImpl;

    invoke-virtual {v0, p1}, Lcom/miui/internal/app/AlertControllerImpl;->setTitle(Ljava/lang/CharSequence;)V

    .line 52
    return-void
.end method

.method public setView(Landroid/view/View;)V
    .locals 1

    .prologue
    .line 70
    iget-object v0, p0, Lcom/miui/internal/variable/AlertControllerWrapper;->Ca:Lcom/miui/internal/app/AlertControllerImpl;

    invoke-virtual {v0, p1}, Lcom/miui/internal/app/AlertControllerImpl;->setView(Landroid/view/View;)V

    .line 71
    return-void
.end method
