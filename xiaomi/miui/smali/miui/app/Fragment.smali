.class public Lmiui/app/Fragment;
.super Landroid/app/Fragment;
.source "SourceFile"

# interfaces
.implements Lcom/miui/internal/app/IFragment;


# instance fields
.field private po:Lcom/miui/internal/app/FragmentDelegate;

.field private pp:Z

.field private pq:Z


# direct methods
.method public constructor <init>()V
    .locals 1

    .prologue
    const/4 v0, 0x1

    .line 21
    invoke-direct {p0}, Landroid/app/Fragment;-><init>()V

    .line 25
    iput-boolean v0, p0, Lmiui/app/Fragment;->pp:Z

    .line 27
    iput-boolean v0, p0, Lmiui/app/Fragment;->pq:Z

    return-void
.end method


# virtual methods
.method public dismissImmersionMenu(Z)V
    .locals 1

    .prologue
    .line 345
    iget-object v0, p0, Lmiui/app/Fragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    invoke-virtual {v0, p1}, Lcom/miui/internal/app/FragmentDelegate;->dismissImmersionMenu(Z)V

    .line 346
    return-void
.end method

.method public getActionBar()Lmiui/app/ActionBar;
    .locals 1

    .prologue
    .line 83
    iget-object v0, p0, Lmiui/app/Fragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    invoke-virtual {v0}, Lcom/miui/internal/app/FragmentDelegate;->getActionBar()Lmiui/app/ActionBar;

    move-result-object v0

    return-object v0
.end method

.method public getMenuInflater()Landroid/view/MenuInflater;
    .locals 1

    .prologue
    .line 92
    iget-object v0, p0, Lmiui/app/Fragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    invoke-virtual {v0}, Lcom/miui/internal/app/FragmentDelegate;->getMenuInflater()Landroid/view/MenuInflater;

    move-result-object v0

    return-object v0
.end method

.method public getThemedContext()Landroid/content/Context;
    .locals 1

    .prologue
    .line 107
    iget-object v0, p0, Lmiui/app/Fragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    invoke-virtual {v0}, Lcom/miui/internal/app/FragmentDelegate;->getThemedContext()Landroid/content/Context;

    move-result-object v0

    return-object v0
.end method

.method public getView()Landroid/view/View;
    .locals 1

    .prologue
    .line 68
    iget-object v0, p0, Lmiui/app/Fragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    invoke-virtual {v0}, Lcom/miui/internal/app/FragmentDelegate;->getView()Landroid/view/View;

    move-result-object v0

    return-object v0
.end method

.method public invalidateOptionsMenu()V
    .locals 2

    .prologue
    .line 147
    iget-object v0, p0, Lmiui/app/Fragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    if-eqz v0, :cond_0

    .line 148
    iget-object v0, p0, Lmiui/app/Fragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Lcom/miui/internal/app/FragmentDelegate;->updateOptionsMenu(I)V

    .line 149
    invoke-virtual {p0}, Lmiui/app/Fragment;->isHidden()Z

    move-result v0

    if-nez v0, :cond_0

    iget-boolean v0, p0, Lmiui/app/Fragment;->pp:Z

    if-eqz v0, :cond_0

    iget-object v0, p0, Lmiui/app/Fragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    invoke-virtual {v0}, Lcom/miui/internal/app/FragmentDelegate;->isImmersionMenuEnabled()Z

    move-result v0

    if-nez v0, :cond_0

    iget-boolean v0, p0, Lmiui/app/Fragment;->pq:Z

    if-eqz v0, :cond_0

    invoke-virtual {p0}, Lmiui/app/Fragment;->isAdded()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 150
    iget-object v0, p0, Lmiui/app/Fragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    invoke-virtual {v0}, Lcom/miui/internal/app/FragmentDelegate;->invalidateOptionsMenu()V

    .line 153
    :cond_0
    return-void
.end method

.method public final onActionModeFinished(Landroid/view/ActionMode;)V
    .locals 0

    .prologue
    .line 184
    return-void
.end method

.method public final onActionModeStarted(Landroid/view/ActionMode;)V
    .locals 0

    .prologue
    .line 174
    return-void
.end method

.method public onConfigurationChanged(Landroid/content/res/Configuration;)V
    .locals 1

    .prologue
    .line 73
    invoke-super {p0, p1}, Landroid/app/Fragment;->onConfigurationChanged(Landroid/content/res/Configuration;)V

    .line 74
    iget-object v0, p0, Lmiui/app/Fragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    invoke-virtual {v0, p1}, Lcom/miui/internal/app/FragmentDelegate;->onConfigurationChanged(Landroid/content/res/Configuration;)V

    .line 75
    return-void
.end method

.method public onCreate(Landroid/os/Bundle;)V
    .locals 1

    .prologue
    .line 31
    invoke-super {p0, p1}, Landroid/app/Fragment;->onCreate(Landroid/os/Bundle;)V

    .line 32
    new-instance v0, Lcom/miui/internal/app/FragmentDelegate;

    invoke-direct {v0, p0}, Lcom/miui/internal/app/FragmentDelegate;-><init>(Landroid/app/Fragment;)V

    iput-object v0, p0, Lmiui/app/Fragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    .line 33
    iget-object v0, p0, Lmiui/app/Fragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    invoke-virtual {v0, p1}, Lcom/miui/internal/app/FragmentDelegate;->onCreate(Landroid/os/Bundle;)V

    .line 34
    return-void
.end method

.method public onCreateOptionsMenu(Landroid/view/Menu;)Z
    .locals 1

    .prologue
    .line 260
    const/4 v0, 0x1

    return v0
.end method

.method public onCreatePanelMenu(ILandroid/view/Menu;)Z
    .locals 2

    .prologue
    const/4 v0, 0x0

    .line 199
    if-nez p1, :cond_0

    .line 201
    iget-boolean v1, p0, Lmiui/app/Fragment;->pp:Z

    if-eqz v1, :cond_0

    iget-object v1, p0, Lmiui/app/Fragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    invoke-virtual {v1}, Lcom/miui/internal/app/FragmentDelegate;->isImmersionMenuEnabled()Z

    move-result v1

    if-nez v1, :cond_0

    iget-boolean v1, p0, Lmiui/app/Fragment;->pq:Z

    if-eqz v1, :cond_0

    .line 202
    invoke-virtual {p0}, Lmiui/app/Fragment;->isHidden()Z

    move-result v1

    if-nez v1, :cond_0

    invoke-virtual {p0}, Lmiui/app/Fragment;->isAdded()Z

    move-result v1

    if-eqz v1, :cond_0

    .line 203
    invoke-virtual {p0, p2}, Lmiui/app/Fragment;->onCreateOptionsMenu(Landroid/view/Menu;)Z

    move-result v0

    .line 208
    :cond_0
    return v0
.end method

.method public final onCreateView(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Landroid/os/Bundle;)Landroid/view/View;
    .locals 1

    .prologue
    .line 63
    iget-object v0, p0, Lmiui/app/Fragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    invoke-virtual {v0, p2, p3}, Lcom/miui/internal/app/FragmentDelegate;->onCreateView(Landroid/view/ViewGroup;Landroid/os/Bundle;)Landroid/view/View;

    move-result-object v0

    return-object v0
.end method

.method public onDestroy()V
    .locals 2

    .prologue
    .line 350
    invoke-super {p0}, Landroid/app/Fragment;->onDestroy()V

    .line 352
    iget-object v0, p0, Lmiui/app/Fragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Lcom/miui/internal/app/FragmentDelegate;->dismissImmersionMenu(Z)V

    .line 353
    return-void
.end method

.method public final onHiddenChanged(Z)V
    .locals 1

    .prologue
    .line 292
    invoke-super {p0, p1}, Landroid/app/Fragment;->onHiddenChanged(Z)V

    .line 293
    if-nez p1, :cond_0

    iget-object v0, p0, Lmiui/app/Fragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    if-eqz v0, :cond_0

    .line 294
    iget-object v0, p0, Lmiui/app/Fragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    invoke-virtual {v0}, Lcom/miui/internal/app/FragmentDelegate;->invalidateOptionsMenu()V

    .line 296
    :cond_0
    if-nez p1, :cond_1

    const/4 v0, 0x1

    :goto_0
    invoke-virtual {p0, v0}, Lmiui/app/Fragment;->onVisibilityChanged(Z)V

    .line 297
    return-void

    .line 296
    :cond_1
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public onInflateView(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Landroid/os/Bundle;)Landroid/view/View;
    .locals 1

    .prologue
    .line 277
    const/4 v0, 0x0

    return-object v0
.end method

.method public onPreparePanel(ILandroid/view/View;Landroid/view/Menu;)V
    .locals 1

    .prologue
    .line 222
    if-nez p1, :cond_0

    .line 223
    iget-boolean v0, p0, Lmiui/app/Fragment;->pp:Z

    if-eqz v0, :cond_0

    iget-object v0, p0, Lmiui/app/Fragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    invoke-virtual {v0}, Lcom/miui/internal/app/FragmentDelegate;->isImmersionMenuEnabled()Z

    move-result v0

    if-nez v0, :cond_0

    iget-boolean v0, p0, Lmiui/app/Fragment;->pq:Z

    if-eqz v0, :cond_0

    .line 224
    invoke-virtual {p0}, Lmiui/app/Fragment;->isHidden()Z

    move-result v0

    if-nez v0, :cond_0

    invoke-virtual {p0}, Lmiui/app/Fragment;->isAdded()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 225
    invoke-virtual {p0, p3}, Lmiui/app/Fragment;->onPrepareOptionsMenu(Landroid/view/Menu;)V

    .line 229
    :cond_0
    return-void
.end method

.method public onResume()V
    .locals 1

    .prologue
    .line 44
    invoke-super {p0}, Landroid/app/Fragment;->onResume()V

    .line 45
    iget-object v0, p0, Lmiui/app/Fragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    invoke-virtual {v0}, Lcom/miui/internal/app/FragmentDelegate;->onPostResume()V

    .line 46
    return-void
.end method

.method public onStop()V
    .locals 1

    .prologue
    .line 38
    invoke-super {p0}, Landroid/app/Fragment;->onStop()V

    .line 39
    iget-object v0, p0, Lmiui/app/Fragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    invoke-virtual {v0}, Lcom/miui/internal/app/FragmentDelegate;->onStop()V

    .line 40
    return-void
.end method

.method public onVisibilityChanged(Z)V
    .locals 0

    .prologue
    .line 310
    return-void
.end method

.method public requestWindowFeature(I)Z
    .locals 1

    .prologue
    .line 139
    iget-object v0, p0, Lmiui/app/Fragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    invoke-virtual {v0, p1}, Lcom/miui/internal/app/FragmentDelegate;->requestWindowFeature(I)Z

    move-result v0

    return v0
.end method

.method public setHasOptionsMenu(Z)V
    .locals 1

    .prologue
    .line 112
    invoke-super {p0, p1}, Landroid/app/Fragment;->setHasOptionsMenu(Z)V

    .line 113
    iget-boolean v0, p0, Lmiui/app/Fragment;->pp:Z

    if-eq v0, p1, :cond_0

    .line 114
    iput-boolean p1, p0, Lmiui/app/Fragment;->pp:Z

    .line 115
    iget-boolean v0, p0, Lmiui/app/Fragment;->pp:Z

    if-eqz v0, :cond_0

    iget-object v0, p0, Lmiui/app/Fragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    invoke-virtual {v0}, Lcom/miui/internal/app/FragmentDelegate;->isImmersionMenuEnabled()Z

    move-result v0

    if-nez v0, :cond_0

    invoke-virtual {p0}, Lmiui/app/Fragment;->isHidden()Z

    move-result v0

    if-nez v0, :cond_0

    invoke-virtual {p0}, Lmiui/app/Fragment;->isAdded()Z

    move-result v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lmiui/app/Fragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    if-eqz v0, :cond_0

    .line 116
    iget-object v0, p0, Lmiui/app/Fragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    invoke-virtual {v0}, Lcom/miui/internal/app/FragmentDelegate;->invalidateOptionsMenu()V

    .line 119
    :cond_0
    return-void
.end method

.method public setImmersionMenuEnabled(Z)V
    .locals 1

    .prologue
    .line 318
    iget-object v0, p0, Lmiui/app/Fragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    invoke-virtual {v0, p1}, Lcom/miui/internal/app/FragmentDelegate;->setImmersionMenuEnabled(Z)V

    .line 319
    return-void
.end method

.method public setMenuVisibility(Z)V
    .locals 1

    .prologue
    .line 123
    invoke-super {p0, p1}, Landroid/app/Fragment;->setMenuVisibility(Z)V

    .line 124
    iget-boolean v0, p0, Lmiui/app/Fragment;->pq:Z

    if-eq v0, p1, :cond_0

    .line 125
    iput-boolean p1, p0, Lmiui/app/Fragment;->pq:Z

    .line 126
    invoke-virtual {p0}, Lmiui/app/Fragment;->isHidden()Z

    move-result v0

    if-nez v0, :cond_0

    invoke-virtual {p0}, Lmiui/app/Fragment;->isAdded()Z

    move-result v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lmiui/app/Fragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    if-eqz v0, :cond_0

    .line 127
    iget-object v0, p0, Lmiui/app/Fragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    invoke-virtual {v0}, Lcom/miui/internal/app/FragmentDelegate;->invalidateOptionsMenu()V

    .line 130
    :cond_0
    return-void
.end method

.method public setOnStatusBarChangeListener(Lmiui/app/OnStatusBarChangeListener;)V
    .locals 1

    .prologue
    .line 361
    iget-object v0, p0, Lmiui/app/Fragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    invoke-virtual {v0, p1}, Lcom/miui/internal/app/FragmentDelegate;->setOnStatusBarChangeListener(Lmiui/app/OnStatusBarChangeListener;)V

    .line 362
    return-void
.end method

.method public setThemeRes(I)V
    .locals 1

    .prologue
    .line 102
    iget-object v0, p0, Lmiui/app/Fragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    invoke-virtual {v0, p1}, Lcom/miui/internal/app/FragmentDelegate;->setExtraThemeRes(I)V

    .line 103
    return-void
.end method

.method public showImmersionMenu()V
    .locals 1

    .prologue
    .line 327
    iget-object v0, p0, Lmiui/app/Fragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    invoke-virtual {v0}, Lcom/miui/internal/app/FragmentDelegate;->showImmersionMenu()V

    .line 328
    return-void
.end method

.method public showImmersionMenu(Landroid/view/View;Landroid/view/ViewGroup;)V
    .locals 1

    .prologue
    .line 336
    iget-object v0, p0, Lmiui/app/Fragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    invoke-virtual {v0, p1, p2}, Lcom/miui/internal/app/FragmentDelegate;->showImmersionMenu(Landroid/view/View;Landroid/view/ViewGroup;)V

    .line 337
    return-void
.end method

.method public startActionMode(Landroid/view/ActionMode$Callback;)Landroid/view/ActionMode;
    .locals 1

    .prologue
    .line 97
    iget-object v0, p0, Lmiui/app/Fragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    invoke-virtual {v0, p1}, Lcom/miui/internal/app/FragmentDelegate;->startActionMode(Landroid/view/ActionMode$Callback;)Landroid/view/ActionMode;

    move-result-object v0

    return-object v0
.end method

.method public updateOptionsMenuContent()V
    .locals 1

    .prologue
    .line 160
    iget-object v0, p0, Lmiui/app/Fragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    if-eqz v0, :cond_0

    invoke-virtual {p0}, Lmiui/app/Fragment;->isHidden()Z

    move-result v0

    if-nez v0, :cond_0

    iget-boolean v0, p0, Lmiui/app/Fragment;->pp:Z

    if-eqz v0, :cond_0

    iget-object v0, p0, Lmiui/app/Fragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    invoke-virtual {v0}, Lcom/miui/internal/app/FragmentDelegate;->isImmersionMenuEnabled()Z

    move-result v0

    if-nez v0, :cond_0

    iget-boolean v0, p0, Lmiui/app/Fragment;->pq:Z

    if-eqz v0, :cond_0

    invoke-virtual {p0}, Lmiui/app/Fragment;->isAdded()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 162
    iget-object v0, p0, Lmiui/app/Fragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    invoke-virtual {v0}, Lcom/miui/internal/app/FragmentDelegate;->invalidateOptionsMenu()V

    .line 164
    :cond_0
    return-void
.end method
