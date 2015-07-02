.class public Lcom/miui/internal/view/menu/MenuDialogHelper;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroid/content/DialogInterface$OnClickListener;
.implements Landroid/content/DialogInterface$OnDismissListener;
.implements Landroid/content/DialogInterface$OnKeyListener;
.implements Lcom/miui/internal/view/menu/MenuPresenter$Callback;


# instance fields
.field private mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

.field private nz:Lcom/miui/internal/view/menu/MenuPresenter$Callback;

.field private pG:Landroid/app/AlertDialog;

.field pH:Lcom/miui/internal/view/menu/ListMenuPresenter;


# direct methods
.method public constructor <init>(Lcom/miui/internal/view/menu/MenuBuilder;)V
    .locals 0

    .prologue
    .line 30
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 31
    iput-object p1, p0, Lcom/miui/internal/view/menu/MenuDialogHelper;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    .line 32
    return-void
.end method


# virtual methods
.method public dismiss()V
    .locals 1

    .prologue
    .line 128
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuDialogHelper;->pG:Landroid/app/AlertDialog;

    if-eqz v0, :cond_0

    .line 129
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuDialogHelper;->pG:Landroid/app/AlertDialog;

    invoke-virtual {v0}, Landroid/app/AlertDialog;->dismiss()V

    .line 131
    :cond_0
    return-void
.end method

.method public onClick(Landroid/content/DialogInterface;I)V
    .locals 3

    .prologue
    .line 154
    iget-object v1, p0, Lcom/miui/internal/view/menu/MenuDialogHelper;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuDialogHelper;->pH:Lcom/miui/internal/view/menu/ListMenuPresenter;

    invoke-virtual {v0}, Lcom/miui/internal/view/menu/ListMenuPresenter;->getAdapter()Landroid/widget/ListAdapter;

    move-result-object v0

    invoke-interface {v0, p2}, Landroid/widget/ListAdapter;->getItem(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/view/menu/MenuItemImpl;

    const/4 v2, 0x0

    invoke-virtual {v1, v0, v2}, Lcom/miui/internal/view/menu/MenuBuilder;->performItemAction(Landroid/view/MenuItem;I)Z

    .line 155
    return-void
.end method

.method public onCloseMenu(Lcom/miui/internal/view/menu/MenuBuilder;Z)V
    .locals 1

    .prologue
    .line 140
    if-nez p2, :cond_0

    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuDialogHelper;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    if-ne p1, v0, :cond_1

    .line 141
    :cond_0
    invoke-virtual {p0}, Lcom/miui/internal/view/menu/MenuDialogHelper;->dismiss()V

    .line 143
    :cond_1
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuDialogHelper;->nz:Lcom/miui/internal/view/menu/MenuPresenter$Callback;

    if-eqz v0, :cond_2

    .line 144
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuDialogHelper;->nz:Lcom/miui/internal/view/menu/MenuPresenter$Callback;

    invoke-interface {v0, p1, p2}, Lcom/miui/internal/view/menu/MenuPresenter$Callback;->onCloseMenu(Lcom/miui/internal/view/menu/MenuBuilder;Z)V

    .line 146
    :cond_2
    return-void
.end method

.method public onDismiss(Landroid/content/DialogInterface;)V
    .locals 3

    .prologue
    .line 135
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuDialogHelper;->pH:Lcom/miui/internal/view/menu/ListMenuPresenter;

    iget-object v1, p0, Lcom/miui/internal/view/menu/MenuDialogHelper;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    const/4 v2, 0x1

    invoke-virtual {v0, v1, v2}, Lcom/miui/internal/view/menu/ListMenuPresenter;->onCloseMenu(Lcom/miui/internal/view/menu/MenuBuilder;Z)V

    .line 136
    return-void
.end method

.method public onKey(Landroid/content/DialogInterface;ILandroid/view/KeyEvent;)Z
    .locals 2

    .prologue
    const/4 v0, 0x1

    .line 83
    const/16 v1, 0x52

    if-eq p2, v1, :cond_0

    const/4 v1, 0x4

    if-ne p2, v1, :cond_2

    .line 84
    :cond_0
    invoke-virtual {p3}, Landroid/view/KeyEvent;->getAction()I

    move-result v1

    if-nez v1, :cond_1

    invoke-virtual {p3}, Landroid/view/KeyEvent;->getRepeatCount()I

    move-result v1

    if-nez v1, :cond_1

    .line 86
    iget-object v1, p0, Lcom/miui/internal/view/menu/MenuDialogHelper;->pG:Landroid/app/AlertDialog;

    invoke-virtual {v1}, Landroid/app/AlertDialog;->getWindow()Landroid/view/Window;

    move-result-object v1

    .line 87
    if-eqz v1, :cond_2

    .line 88
    invoke-virtual {v1}, Landroid/view/Window;->getDecorView()Landroid/view/View;

    move-result-object v1

    .line 89
    if-eqz v1, :cond_2

    .line 90
    invoke-virtual {v1}, Landroid/view/View;->getKeyDispatcherState()Landroid/view/KeyEvent$DispatcherState;

    move-result-object v1

    .line 91
    if-eqz v1, :cond_2

    .line 92
    invoke-virtual {v1, p3, p0}, Landroid/view/KeyEvent$DispatcherState;->startTracking(Landroid/view/KeyEvent;Ljava/lang/Object;)V

    .line 114
    :goto_0
    return v0

    .line 97
    :cond_1
    invoke-virtual {p3}, Landroid/view/KeyEvent;->getAction()I

    move-result v1

    if-ne v1, v0, :cond_2

    invoke-virtual {p3}, Landroid/view/KeyEvent;->isCanceled()Z

    move-result v1

    if-nez v1, :cond_2

    .line 98
    iget-object v1, p0, Lcom/miui/internal/view/menu/MenuDialogHelper;->pG:Landroid/app/AlertDialog;

    invoke-virtual {v1}, Landroid/app/AlertDialog;->getWindow()Landroid/view/Window;

    move-result-object v1

    .line 99
    if-eqz v1, :cond_2

    .line 100
    invoke-virtual {v1}, Landroid/view/Window;->getDecorView()Landroid/view/View;

    move-result-object v1

    .line 101
    if-eqz v1, :cond_2

    .line 102
    invoke-virtual {v1}, Landroid/view/View;->getKeyDispatcherState()Landroid/view/KeyEvent$DispatcherState;

    move-result-object v1

    .line 103
    if-eqz v1, :cond_2

    invoke-virtual {v1, p3}, Landroid/view/KeyEvent$DispatcherState;->isTracking(Landroid/view/KeyEvent;)Z

    move-result v1

    if-eqz v1, :cond_2

    .line 104
    iget-object v1, p0, Lcom/miui/internal/view/menu/MenuDialogHelper;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    invoke-virtual {v1, v0}, Lcom/miui/internal/view/menu/MenuBuilder;->c(Z)V

    .line 105
    invoke-interface {p1}, Landroid/content/DialogInterface;->dismiss()V

    goto :goto_0

    .line 114
    :cond_2
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuDialogHelper;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    const/4 v1, 0x0

    invoke-virtual {v0, p2, p3, v1}, Lcom/miui/internal/view/menu/MenuBuilder;->performShortcut(ILandroid/view/KeyEvent;I)Z

    move-result v0

    goto :goto_0
.end method

.method public onOpenSubMenu(Lcom/miui/internal/view/menu/MenuBuilder;)Z
    .locals 1

    .prologue
    .line 150
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuDialogHelper;->nz:Lcom/miui/internal/view/menu/MenuPresenter$Callback;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuDialogHelper;->nz:Lcom/miui/internal/view/menu/MenuPresenter$Callback;

    invoke-interface {v0, p1}, Lcom/miui/internal/view/menu/MenuPresenter$Callback;->onOpenSubMenu(Lcom/miui/internal/view/menu/MenuBuilder;)Z

    move-result v0

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public setPresenterCallback(Lcom/miui/internal/view/menu/MenuPresenter$Callback;)V
    .locals 0

    .prologue
    .line 119
    iput-object p1, p0, Lcom/miui/internal/view/menu/MenuDialogHelper;->nz:Lcom/miui/internal/view/menu/MenuPresenter$Callback;

    .line 120
    return-void
.end method

.method public show(Landroid/os/IBinder;)V
    .locals 5

    .prologue
    .line 41
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuDialogHelper;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    .line 44
    new-instance v1, Landroid/app/AlertDialog$Builder;

    invoke-virtual {v0}, Lcom/miui/internal/view/menu/MenuBuilder;->getContext()Landroid/content/Context;

    move-result-object v2

    invoke-direct {v1, v2}, Landroid/app/AlertDialog$Builder;-><init>(Landroid/content/Context;)V

    .line 48
    new-instance v2, Lcom/miui/internal/view/menu/ListMenuPresenter;

    sget v3, Lcom/miui/internal/R$layout;->list_menu_item_layout:I

    sget v4, Lcom/miui/internal/R$style;->Theme_MenuDialog_Light:I

    invoke-direct {v2, v3, v4}, Lcom/miui/internal/view/menu/ListMenuPresenter;-><init>(II)V

    iput-object v2, p0, Lcom/miui/internal/view/menu/MenuDialogHelper;->pH:Lcom/miui/internal/view/menu/ListMenuPresenter;

    .line 51
    iget-object v2, p0, Lcom/miui/internal/view/menu/MenuDialogHelper;->pH:Lcom/miui/internal/view/menu/ListMenuPresenter;

    invoke-virtual {v2, p0}, Lcom/miui/internal/view/menu/ListMenuPresenter;->setCallback(Lcom/miui/internal/view/menu/MenuPresenter$Callback;)V

    .line 52
    iget-object v2, p0, Lcom/miui/internal/view/menu/MenuDialogHelper;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    iget-object v3, p0, Lcom/miui/internal/view/menu/MenuDialogHelper;->pH:Lcom/miui/internal/view/menu/ListMenuPresenter;

    invoke-virtual {v2, v3}, Lcom/miui/internal/view/menu/MenuBuilder;->addMenuPresenter(Lcom/miui/internal/view/menu/MenuPresenter;)V

    .line 53
    iget-object v2, p0, Lcom/miui/internal/view/menu/MenuDialogHelper;->pH:Lcom/miui/internal/view/menu/ListMenuPresenter;

    invoke-virtual {v2}, Lcom/miui/internal/view/menu/ListMenuPresenter;->getAdapter()Landroid/widget/ListAdapter;

    move-result-object v2

    invoke-virtual {v1, v2, p0}, Landroid/app/AlertDialog$Builder;->setAdapter(Landroid/widget/ListAdapter;Landroid/content/DialogInterface$OnClickListener;)Landroid/app/AlertDialog$Builder;

    .line 56
    invoke-virtual {v0}, Lcom/miui/internal/view/menu/MenuBuilder;->getHeaderView()Landroid/view/View;

    move-result-object v2

    .line 57
    if-eqz v2, :cond_1

    .line 59
    invoke-virtual {v1, v2}, Landroid/app/AlertDialog$Builder;->setCustomTitle(Landroid/view/View;)Landroid/app/AlertDialog$Builder;

    .line 66
    :goto_0
    invoke-virtual {v1, p0}, Landroid/app/AlertDialog$Builder;->setOnKeyListener(Landroid/content/DialogInterface$OnKeyListener;)Landroid/app/AlertDialog$Builder;

    .line 69
    invoke-virtual {v1}, Landroid/app/AlertDialog$Builder;->create()Landroid/app/AlertDialog;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/internal/view/menu/MenuDialogHelper;->pG:Landroid/app/AlertDialog;

    .line 70
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuDialogHelper;->pG:Landroid/app/AlertDialog;

    invoke-virtual {v0, p0}, Landroid/app/AlertDialog;->setOnDismissListener(Landroid/content/DialogInterface$OnDismissListener;)V

    .line 72
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuDialogHelper;->pG:Landroid/app/AlertDialog;

    invoke-virtual {v0}, Landroid/app/AlertDialog;->getWindow()Landroid/view/Window;

    move-result-object v0

    invoke-virtual {v0}, Landroid/view/Window;->getAttributes()Landroid/view/WindowManager$LayoutParams;

    move-result-object v0

    .line 73
    const/16 v1, 0x3eb

    iput v1, v0, Landroid/view/WindowManager$LayoutParams;->type:I

    .line 74
    if-eqz p1, :cond_0

    .line 75
    iput-object p1, v0, Landroid/view/WindowManager$LayoutParams;->token:Landroid/os/IBinder;

    .line 77
    :cond_0
    iget v1, v0, Landroid/view/WindowManager$LayoutParams;->flags:I

    const/high16 v2, 0x20000

    or-int/2addr v1, v2

    iput v1, v0, Landroid/view/WindowManager$LayoutParams;->flags:I

    .line 79
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuDialogHelper;->pG:Landroid/app/AlertDialog;

    invoke-virtual {v0}, Landroid/app/AlertDialog;->show()V

    .line 80
    return-void

    .line 62
    :cond_1
    invoke-virtual {v0}, Lcom/miui/internal/view/menu/MenuBuilder;->getHeaderIcon()Landroid/graphics/drawable/Drawable;

    move-result-object v2

    invoke-virtual {v1, v2}, Landroid/app/AlertDialog$Builder;->setIcon(Landroid/graphics/drawable/Drawable;)Landroid/app/AlertDialog$Builder;

    move-result-object v2

    invoke-virtual {v0}, Lcom/miui/internal/view/menu/MenuBuilder;->getHeaderTitle()Ljava/lang/CharSequence;

    move-result-object v0

    invoke-virtual {v2, v0}, Landroid/app/AlertDialog$Builder;->setTitle(Ljava/lang/CharSequence;)Landroid/app/AlertDialog$Builder;

    goto :goto_0
.end method
