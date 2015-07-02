.class public Lmiui/app/AlertDialog;
.super Landroid/app/Dialog;
.source "SourceFile"

# interfaces
.implements Landroid/content/DialogInterface;


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lmiui/app/AlertDialog$Builder;
    }
.end annotation


# static fields
.field public static final THEME_DARK:I = 0x2

.field public static final THEME_DARK_EDIT:I = 0x4

.field public static final THEME_DARK_EDIT_DEFAULT:I = 0x6

.field public static final THEME_LIGHT:I = 0x3

.field public static final THEME_LIGHT_EDIT:I = 0x5

.field public static final THEME_LIGHT_EDIT_DEFAULT:I = 0x7


# instance fields
.field private az:Lcom/miui/internal/variable/AlertControllerWrapper;


# direct methods
.method protected constructor <init>(Landroid/content/Context;)V
    .locals 1

    .prologue
    .line 89
    const/4 v0, 0x0

    invoke-static {p1, v0}, Lmiui/app/AlertDialog;->a(Landroid/content/Context;I)I

    move-result v0

    invoke-direct {p0, p1, v0}, Lmiui/app/AlertDialog;-><init>(Landroid/content/Context;I)V

    .line 90
    return-void
.end method

.method protected constructor <init>(Landroid/content/Context;I)V
    .locals 2

    .prologue
    .line 93
    invoke-static {p1, p2}, Lmiui/app/AlertDialog;->a(Landroid/content/Context;I)I

    move-result v0

    invoke-direct {p0, p1, v0}, Landroid/app/Dialog;-><init>(Landroid/content/Context;I)V

    .line 94
    new-instance v0, Lcom/miui/internal/variable/AlertControllerWrapper;

    invoke-virtual {p0}, Lmiui/app/AlertDialog;->getWindow()Landroid/view/Window;

    move-result-object v1

    invoke-direct {v0, p1, p0, v1}, Lcom/miui/internal/variable/AlertControllerWrapper;-><init>(Landroid/content/Context;Landroid/content/DialogInterface;Landroid/view/Window;)V

    iput-object v0, p0, Lmiui/app/AlertDialog;->az:Lcom/miui/internal/variable/AlertControllerWrapper;

    .line 95
    return-void
.end method

.method static a(Landroid/content/Context;I)I
    .locals 4

    .prologue
    .line 114
    packed-switch p1, :pswitch_data_0

    .line 140
    const/high16 v0, 0x1000000

    if-lt p1, v0, :cond_0

    .line 151
    :goto_0
    return p1

    .line 116
    :pswitch_0
    sget p1, Lmiui/R$style;->Theme_Dark_Dialog_Alert:I

    goto :goto_0

    .line 120
    :pswitch_1
    sget p1, Lmiui/R$style;->Theme_Light_Dialog_Alert:I

    goto :goto_0

    .line 124
    :pswitch_2
    sget p1, Lcom/miui/internal/R$style;->Theme_Dark_Dialog_Edit:I

    goto :goto_0

    .line 128
    :pswitch_3
    sget p1, Lcom/miui/internal/R$style;->Theme_Light_Dialog_Edit:I

    goto :goto_0

    .line 132
    :pswitch_4
    sget p1, Lcom/miui/internal/R$style;->Theme_Dark_Dialog_Edit_Default:I

    goto :goto_0

    .line 136
    :pswitch_5
    sget p1, Lcom/miui/internal/R$style;->Theme_Light_Dialog_Edit_Default:I

    goto :goto_0

    .line 143
    :cond_0
    new-instance v0, Landroid/util/TypedValue;

    invoke-direct {v0}, Landroid/util/TypedValue;-><init>()V

    .line 144
    invoke-virtual {p0}, Landroid/content/Context;->getTheme()Landroid/content/res/Resources$Theme;

    move-result-object v1

    const v2, 0x1010309

    const/4 v3, 0x1

    invoke-virtual {v1, v2, v0, v3}, Landroid/content/res/Resources$Theme;->resolveAttribute(ILandroid/util/TypedValue;Z)Z

    .line 145
    iget p1, v0, Landroid/util/TypedValue;->resourceId:I

    goto :goto_0

    .line 114
    nop

    :pswitch_data_0
    .packed-switch 0x2
        :pswitch_0
        :pswitch_1
        :pswitch_2
        :pswitch_3
        :pswitch_4
        :pswitch_5
    .end packed-switch
.end method

.method static synthetic a(Lmiui/app/AlertDialog;)Lcom/miui/internal/variable/AlertControllerWrapper;
    .locals 1

    .prologue
    .line 48
    iget-object v0, p0, Lmiui/app/AlertDialog;->az:Lcom/miui/internal/variable/AlertControllerWrapper;

    return-object v0
.end method


# virtual methods
.method public getButton(I)Landroid/widget/Button;
    .locals 1

    .prologue
    .line 165
    iget-object v0, p0, Lmiui/app/AlertDialog;->az:Lcom/miui/internal/variable/AlertControllerWrapper;

    invoke-virtual {v0, p1}, Lcom/miui/internal/variable/AlertControllerWrapper;->getButton(I)Landroid/widget/Button;

    move-result-object v0

    return-object v0
.end method

.method public getCheckedItems()[Z
    .locals 1

    .prologue
    .line 248
    iget-object v0, p0, Lmiui/app/AlertDialog;->az:Lcom/miui/internal/variable/AlertControllerWrapper;

    invoke-virtual {v0}, Lcom/miui/internal/variable/AlertControllerWrapper;->getCheckedItems()[Z

    move-result-object v0

    return-object v0
.end method

.method public getListView()Landroid/widget/ListView;
    .locals 1

    .prologue
    .line 174
    iget-object v0, p0, Lmiui/app/AlertDialog;->az:Lcom/miui/internal/variable/AlertControllerWrapper;

    invoke-virtual {v0}, Lcom/miui/internal/variable/AlertControllerWrapper;->getListView()Landroid/widget/ListView;

    move-result-object v0

    return-object v0
.end method

.method public getMessageView()Landroid/widget/TextView;
    .locals 1

    .prologue
    .line 204
    iget-object v0, p0, Lmiui/app/AlertDialog;->az:Lcom/miui/internal/variable/AlertControllerWrapper;

    invoke-virtual {v0}, Lcom/miui/internal/variable/AlertControllerWrapper;->getMessageView()Landroid/widget/TextView;

    move-result-object v0

    return-object v0
.end method

.method public getMiuiActionBar()Lmiui/app/ActionBar;
    .locals 1

    .prologue
    .line 109
    iget-object v0, p0, Lmiui/app/AlertDialog;->az:Lcom/miui/internal/variable/AlertControllerWrapper;

    invoke-virtual {v0}, Lcom/miui/internal/variable/AlertControllerWrapper;->getImpl()Lcom/miui/internal/app/AlertControllerImpl;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/internal/app/AlertControllerImpl;->getActionBar()Lmiui/app/ActionBar;

    move-result-object v0

    return-object v0
.end method

.method public isChecked()Z
    .locals 1

    .prologue
    .line 256
    iget-object v0, p0, Lmiui/app/AlertDialog;->az:Lcom/miui/internal/variable/AlertControllerWrapper;

    invoke-virtual {v0}, Lcom/miui/internal/variable/AlertControllerWrapper;->isChecked()Z

    move-result v0

    return v0
.end method

.method protected onCreate(Landroid/os/Bundle;)V
    .locals 1

    .prologue
    .line 99
    invoke-super {p0, p1}, Landroid/app/Dialog;->onCreate(Landroid/os/Bundle;)V

    .line 100
    iget-object v0, p0, Lmiui/app/AlertDialog;->az:Lcom/miui/internal/variable/AlertControllerWrapper;

    invoke-virtual {v0}, Lcom/miui/internal/variable/AlertControllerWrapper;->installContent()V

    .line 101
    return-void
.end method

.method public onKeyDown(ILandroid/view/KeyEvent;)Z
    .locals 1

    .prologue
    .line 266
    iget-object v0, p0, Lmiui/app/AlertDialog;->az:Lcom/miui/internal/variable/AlertControllerWrapper;

    invoke-virtual {v0, p1, p2}, Lcom/miui/internal/variable/AlertControllerWrapper;->onKeyDown(ILandroid/view/KeyEvent;)Z

    move-result v0

    if-nez v0, :cond_0

    invoke-super {p0, p1, p2}, Landroid/app/Dialog;->onKeyDown(ILandroid/view/KeyEvent;)Z

    move-result v0

    if-eqz v0, :cond_1

    :cond_0
    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_1
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public onKeyUp(ILandroid/view/KeyEvent;)Z
    .locals 1

    .prologue
    .line 261
    iget-object v0, p0, Lmiui/app/AlertDialog;->az:Lcom/miui/internal/variable/AlertControllerWrapper;

    invoke-virtual {v0, p1, p2}, Lcom/miui/internal/variable/AlertControllerWrapper;->onKeyUp(ILandroid/view/KeyEvent;)Z

    move-result v0

    if-nez v0, :cond_0

    invoke-super {p0, p1, p2}, Landroid/app/Dialog;->onKeyUp(ILandroid/view/KeyEvent;)Z

    move-result v0

    if-eqz v0, :cond_1

    :cond_0
    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_1
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public setButton(ILjava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)V
    .locals 2

    .prologue
    .line 240
    iget-object v0, p0, Lmiui/app/AlertDialog;->az:Lcom/miui/internal/variable/AlertControllerWrapper;

    const/4 v1, 0x0

    invoke-virtual {v0, p1, p2, p3, v1}, Lcom/miui/internal/variable/AlertControllerWrapper;->setButton(ILjava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;Landroid/os/Message;)V

    .line 241
    return-void
.end method

.method public setButton(ILjava/lang/CharSequence;Landroid/os/Message;)V
    .locals 2

    .prologue
    .line 226
    iget-object v0, p0, Lmiui/app/AlertDialog;->az:Lcom/miui/internal/variable/AlertControllerWrapper;

    const/4 v1, 0x0

    invoke-virtual {v0, p1, p2, v1, p3}, Lcom/miui/internal/variable/AlertControllerWrapper;->setButton(ILjava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;Landroid/os/Message;)V

    .line 227
    return-void
.end method

.method public setCustomTitle(Landroid/view/View;)V
    .locals 1

    .prologue
    .line 188
    iget-object v0, p0, Lmiui/app/AlertDialog;->az:Lcom/miui/internal/variable/AlertControllerWrapper;

    invoke-virtual {v0, p1}, Lcom/miui/internal/variable/AlertControllerWrapper;->setCustomTitle(Landroid/view/View;)V

    .line 189
    return-void
.end method

.method public setMessage(Ljava/lang/CharSequence;)V
    .locals 1

    .prologue
    .line 196
    iget-object v0, p0, Lmiui/app/AlertDialog;->az:Lcom/miui/internal/variable/AlertControllerWrapper;

    invoke-virtual {v0, p1}, Lcom/miui/internal/variable/AlertControllerWrapper;->setMessage(Ljava/lang/CharSequence;)V

    .line 197
    return-void
.end method

.method public setTitle(Ljava/lang/CharSequence;)V
    .locals 1

    .prologue
    .line 179
    invoke-super {p0, p1}, Landroid/app/Dialog;->setTitle(Ljava/lang/CharSequence;)V

    .line 180
    iget-object v0, p0, Lmiui/app/AlertDialog;->az:Lcom/miui/internal/variable/AlertControllerWrapper;

    invoke-virtual {v0, p1}, Lcom/miui/internal/variable/AlertControllerWrapper;->setTitle(Ljava/lang/CharSequence;)V

    .line 181
    return-void
.end method

.method public setView(Landroid/view/View;)V
    .locals 1

    .prologue
    .line 212
    iget-object v0, p0, Lmiui/app/AlertDialog;->az:Lcom/miui/internal/variable/AlertControllerWrapper;

    invoke-virtual {v0, p1}, Lcom/miui/internal/variable/AlertControllerWrapper;->setView(Landroid/view/View;)V

    .line 213
    return-void
.end method
