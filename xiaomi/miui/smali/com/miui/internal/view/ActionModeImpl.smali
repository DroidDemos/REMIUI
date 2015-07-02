.class public Lcom/miui/internal/view/ActionModeImpl;
.super Landroid/view/ActionMode;
.source "SourceFile"

# interfaces
.implements Lcom/miui/internal/view/menu/MenuBuilder$Callback;
.implements Lmiui/view/ActionModeAnimationListener;


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/internal/view/ActionModeImpl$ActionModeCallback;
    }
.end annotation


# instance fields
.field private Mr:Landroid/view/ActionMode$Callback;

.field private Ms:Lcom/miui/internal/view/ActionModeImpl$ActionModeCallback;

.field Mt:Z

.field protected mActionModeView:Ljava/lang/ref/WeakReference;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/lang/ref/WeakReference",
            "<",
            "Lcom/miui/internal/widget/ActionModeView;",
            ">;"
        }
    .end annotation
.end field

.field protected mContext:Landroid/content/Context;

.field private mMenu:Lcom/miui/internal/view/menu/MenuBuilder;


# direct methods
.method public constructor <init>(Landroid/content/Context;Landroid/view/ActionMode$Callback;)V
    .locals 2

    .prologue
    .line 38
    invoke-direct {p0}, Landroid/view/ActionMode;-><init>()V

    .line 36
    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/miui/internal/view/ActionModeImpl;->Mt:Z

    .line 39
    iput-object p1, p0, Lcom/miui/internal/view/ActionModeImpl;->mContext:Landroid/content/Context;

    .line 40
    iput-object p2, p0, Lcom/miui/internal/view/ActionModeImpl;->Mr:Landroid/view/ActionMode$Callback;

    .line 41
    new-instance v0, Lcom/miui/internal/view/menu/MenuBuilder;

    invoke-direct {v0, p1}, Lcom/miui/internal/view/menu/MenuBuilder;-><init>(Landroid/content/Context;)V

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Lcom/miui/internal/view/menu/MenuBuilder;->setDefaultShowAsAction(I)Lcom/miui/internal/view/menu/MenuBuilder;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/internal/view/ActionModeImpl;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    .line 42
    iget-object v0, p0, Lcom/miui/internal/view/ActionModeImpl;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    invoke-virtual {v0, p0}, Lcom/miui/internal/view/menu/MenuBuilder;->setCallback(Lcom/miui/internal/view/menu/MenuBuilder$Callback;)V

    .line 43
    return-void
.end method


# virtual methods
.method public dispatchOnCreate()Z
    .locals 2

    .prologue
    .line 55
    iget-object v0, p0, Lcom/miui/internal/view/ActionModeImpl;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    invoke-virtual {v0}, Lcom/miui/internal/view/menu/MenuBuilder;->stopDispatchingItemsChanged()V

    .line 57
    :try_start_0
    iget-object v0, p0, Lcom/miui/internal/view/ActionModeImpl;->Mr:Landroid/view/ActionMode$Callback;

    iget-object v1, p0, Lcom/miui/internal/view/ActionModeImpl;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    invoke-interface {v0, p0, v1}, Landroid/view/ActionMode$Callback;->onCreateActionMode(Landroid/view/ActionMode;Landroid/view/Menu;)Z
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    move-result v0

    .line 59
    iget-object v1, p0, Lcom/miui/internal/view/ActionModeImpl;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    invoke-virtual {v1}, Lcom/miui/internal/view/menu/MenuBuilder;->startDispatchingItemsChanged()V

    return v0

    :catchall_0
    move-exception v0

    iget-object v1, p0, Lcom/miui/internal/view/ActionModeImpl;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    invoke-virtual {v1}, Lcom/miui/internal/view/menu/MenuBuilder;->startDispatchingItemsChanged()V

    throw v0
.end method

.method public finish()V
    .locals 1

    .prologue
    .line 85
    iget-boolean v0, p0, Lcom/miui/internal/view/ActionModeImpl;->Mt:Z

    if-eqz v0, :cond_1

    .line 93
    :cond_0
    :goto_0
    return-void

    .line 88
    :cond_1
    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/miui/internal/view/ActionModeImpl;->Mt:Z

    .line 89
    iget-object v0, p0, Lcom/miui/internal/view/ActionModeImpl;->mActionModeView:Ljava/lang/ref/WeakReference;

    invoke-virtual {v0}, Ljava/lang/ref/WeakReference;->get()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/widget/ActionModeView;

    invoke-interface {v0}, Lcom/miui/internal/widget/ActionModeView;->closeMode()V

    .line 90
    iget-object v0, p0, Lcom/miui/internal/view/ActionModeImpl;->Ms:Lcom/miui/internal/view/ActionModeImpl$ActionModeCallback;

    if-eqz v0, :cond_0

    .line 91
    iget-object v0, p0, Lcom/miui/internal/view/ActionModeImpl;->Ms:Lcom/miui/internal/view/ActionModeImpl$ActionModeCallback;

    invoke-interface {v0, p0}, Lcom/miui/internal/view/ActionModeImpl$ActionModeCallback;->onActionModeFinish(Landroid/view/ActionMode;)V

    goto :goto_0
.end method

.method public getCustomView()Landroid/view/View;
    .locals 2

    .prologue
    .line 122
    new-instance v0, Ljava/lang/UnsupportedOperationException;

    const-string v1, "getCustomView not supported"

    invoke-direct {v0, v1}, Ljava/lang/UnsupportedOperationException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

.method public getMenu()Landroid/view/Menu;
    .locals 1

    .prologue
    .line 97
    iget-object v0, p0, Lcom/miui/internal/view/ActionModeImpl;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    return-object v0
.end method

.method public getMenuInflater()Landroid/view/MenuInflater;
    .locals 2

    .prologue
    .line 132
    new-instance v0, Landroid/view/MenuInflater;

    iget-object v1, p0, Lcom/miui/internal/view/ActionModeImpl;->mContext:Landroid/content/Context;

    invoke-direct {v0, v1}, Landroid/view/MenuInflater;-><init>(Landroid/content/Context;)V

    return-object v0
.end method

.method public getSubtitle()Ljava/lang/CharSequence;
    .locals 2

    .prologue
    .line 112
    new-instance v0, Ljava/lang/UnsupportedOperationException;

    const-string v1, "getSubtitle not supported"

    invoke-direct {v0, v1}, Ljava/lang/UnsupportedOperationException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

.method public getTitle()Ljava/lang/CharSequence;
    .locals 2

    .prologue
    .line 102
    new-instance v0, Ljava/lang/UnsupportedOperationException;

    const-string v1, "getTitle not supported"

    invoke-direct {v0, v1}, Ljava/lang/UnsupportedOperationException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

.method public invalidate()V
    .locals 2

    .prologue
    .line 75
    iget-object v0, p0, Lcom/miui/internal/view/ActionModeImpl;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    invoke-virtual {v0}, Lcom/miui/internal/view/menu/MenuBuilder;->stopDispatchingItemsChanged()V

    .line 77
    :try_start_0
    iget-object v0, p0, Lcom/miui/internal/view/ActionModeImpl;->Mr:Landroid/view/ActionMode$Callback;

    iget-object v1, p0, Lcom/miui/internal/view/ActionModeImpl;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    invoke-interface {v0, p0, v1}, Landroid/view/ActionMode$Callback;->onPrepareActionMode(Landroid/view/ActionMode;Landroid/view/Menu;)Z
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 79
    iget-object v0, p0, Lcom/miui/internal/view/ActionModeImpl;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    invoke-virtual {v0}, Lcom/miui/internal/view/menu/MenuBuilder;->startDispatchingItemsChanged()V

    .line 81
    return-void

    .line 79
    :catchall_0
    move-exception v0

    iget-object v1, p0, Lcom/miui/internal/view/ActionModeImpl;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    invoke-virtual {v1}, Lcom/miui/internal/view/menu/MenuBuilder;->startDispatchingItemsChanged()V

    throw v0
.end method

.method public onMenuItemSelected(Lcom/miui/internal/view/menu/MenuBuilder;Landroid/view/MenuItem;)Z
    .locals 1

    .prologue
    .line 137
    iget-object v0, p0, Lcom/miui/internal/view/ActionModeImpl;->Mr:Landroid/view/ActionMode$Callback;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/internal/view/ActionModeImpl;->Mr:Landroid/view/ActionMode$Callback;

    invoke-interface {v0, p0, p2}, Landroid/view/ActionMode$Callback;->onActionItemClicked(Landroid/view/ActionMode;Landroid/view/MenuItem;)Z

    move-result v0

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public onMenuModeChange(Lcom/miui/internal/view/menu/MenuBuilder;)V
    .locals 1

    .prologue
    .line 142
    iget-object v0, p0, Lcom/miui/internal/view/ActionModeImpl;->Mr:Landroid/view/ActionMode$Callback;

    if-nez v0, :cond_0

    .line 146
    :goto_0
    return-void

    .line 145
    :cond_0
    invoke-virtual {p0}, Lcom/miui/internal/view/ActionModeImpl;->invalidate()V

    goto :goto_0
.end method

.method public onStart(Z)V
    .locals 0

    .prologue
    .line 150
    return-void
.end method

.method public onStop(Z)V
    .locals 1

    .prologue
    .line 158
    if-nez p1, :cond_0

    .line 160
    iget-object v0, p0, Lcom/miui/internal/view/ActionModeImpl;->Mr:Landroid/view/ActionMode$Callback;

    invoke-interface {v0, p0}, Landroid/view/ActionMode$Callback;->onDestroyActionMode(Landroid/view/ActionMode;)V

    .line 161
    const/4 v0, 0x0

    iput-object v0, p0, Lcom/miui/internal/view/ActionModeImpl;->Mr:Landroid/view/ActionMode$Callback;

    .line 163
    :cond_0
    return-void
.end method

.method public onUpdate(ZF)V
    .locals 0

    .prologue
    .line 154
    return-void
.end method

.method public setActionModeCallback(Lcom/miui/internal/view/ActionModeImpl$ActionModeCallback;)V
    .locals 0

    .prologue
    .line 46
    iput-object p1, p0, Lcom/miui/internal/view/ActionModeImpl;->Ms:Lcom/miui/internal/view/ActionModeImpl$ActionModeCallback;

    .line 47
    return-void
.end method

.method public setActionModeView(Lcom/miui/internal/widget/ActionModeView;)V
    .locals 1

    .prologue
    .line 50
    invoke-interface {p1, p0}, Lcom/miui/internal/widget/ActionModeView;->addAnimationListener(Lmiui/view/ActionModeAnimationListener;)V

    .line 51
    new-instance v0, Ljava/lang/ref/WeakReference;

    invoke-direct {v0, p1}, Ljava/lang/ref/WeakReference;-><init>(Ljava/lang/Object;)V

    iput-object v0, p0, Lcom/miui/internal/view/ActionModeImpl;->mActionModeView:Ljava/lang/ref/WeakReference;

    .line 52
    return-void
.end method

.method public setCustomView(Landroid/view/View;)V
    .locals 2

    .prologue
    .line 127
    new-instance v0, Ljava/lang/UnsupportedOperationException;

    const-string v1, "setCustomView not supported"

    invoke-direct {v0, v1}, Ljava/lang/UnsupportedOperationException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

.method public setSubtitle(I)V
    .locals 2

    .prologue
    .line 117
    new-instance v0, Ljava/lang/UnsupportedOperationException;

    const-string v1, "setSubTitle not supported"

    invoke-direct {v0, v1}, Ljava/lang/UnsupportedOperationException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

.method public setSubtitle(Ljava/lang/CharSequence;)V
    .locals 2

    .prologue
    .line 70
    new-instance v0, Ljava/lang/UnsupportedOperationException;

    const-string v1, "setSubTitle not supported"

    invoke-direct {v0, v1}, Ljava/lang/UnsupportedOperationException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

.method public setTitle(I)V
    .locals 2

    .prologue
    .line 107
    new-instance v0, Ljava/lang/UnsupportedOperationException;

    const-string v1, "setTitle not supported"

    invoke-direct {v0, v1}, Ljava/lang/UnsupportedOperationException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

.method public setTitle(Ljava/lang/CharSequence;)V
    .locals 2

    .prologue
    .line 65
    new-instance v0, Ljava/lang/UnsupportedOperationException;

    const-string v1, "setTitle not supported"

    invoke-direct {v0, v1}, Ljava/lang/UnsupportedOperationException;-><init>(Ljava/lang/String;)V

    throw v0
.end method
