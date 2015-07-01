.class public Lmiui/app/ListFragment;
.super Landroid/app/ListFragment;
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
    invoke-direct {p0}, Landroid/app/ListFragment;-><init>()V

    .line 25
    iput-boolean v0, p0, Lmiui/app/ListFragment;->pp:Z

    .line 27
    iput-boolean v0, p0, Lmiui/app/ListFragment;->pq:Z

    return-void
.end method


# virtual methods
.method public dismissImmersionMenu(Z)V
    .locals 1

    .prologue
    .line 345
    iget-object v0, p0, Lmiui/app/ListFragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    invoke-virtual {v0, p1}, Lcom/miui/internal/app/FragmentDelegate;->dismissImmersionMenu(Z)V

    .line 346
    return-void
.end method

.method public getActionBar()Lmiui/app/ActionBar;
    .locals 1

    .prologue
    .line 84
    iget-object v0, p0, Lmiui/app/ListFragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    invoke-virtual {v0}, Lcom/miui/internal/app/FragmentDelegate;->getActionBar()Lmiui/app/ActionBar;

    move-result-object v0

    return-object v0
.end method

.method public getMenuInflater()Landroid/view/MenuInflater;
    .locals 1

    .prologue
    .line 93
    iget-object v0, p0, Lmiui/app/ListFragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    invoke-virtual {v0}, Lcom/miui/internal/app/FragmentDelegate;->getMenuInflater()Landroid/view/MenuInflater;

    move-result-object v0

    return-object v0
.end method

.method public getThemedContext()Landroid/content/Context;
    .locals 1

    .prologue
    .line 108
    iget-object v0, p0, Lmiui/app/ListFragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    invoke-virtual {v0}, Lcom/miui/internal/app/FragmentDelegate;->getThemedContext()Landroid/content/Context;

    move-result-object v0

    return-object v0
.end method

.method public getView()Landroid/view/View;
    .locals 1

    .prologue
    .line 69
    iget-object v0, p0, Lmiui/app/ListFragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    invoke-virtual {v0}, Lcom/miui/internal/app/FragmentDelegate;->getView()Landroid/view/View;

    move-result-object v0

    return-object v0
.end method

.method public invalidateOptionsMenu()V
    .locals 2

    .prologue
    .line 148
    iget-object v0, p0, Lmiui/app/ListFragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    if-eqz v0, :cond_0

    .line 149
    iget-object v0, p0, Lmiui/app/ListFragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Lcom/miui/internal/app/FragmentDelegate;->updateOptionsMenu(I)V

    .line 150
    invoke-virtual {p0}, Lmiui/app/ListFragment;->isHidden()Z

    move-result v0

    if-nez v0, :cond_0

    iget-boolean v0, p0, Lmiui/app/ListFragment;->pp:Z

    if-eqz v0, :cond_0

    iget-boolean v0, p0, Lmiui/app/ListFragment;->pq:Z

    if-eqz v0, :cond_0

    invoke-virtual {p0}, Lmiui/app/ListFragment;->isAdded()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 151
    iget-object v0, p0, Lmiui/app/ListFragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    invoke-virtual {v0}, Lcom/miui/internal/app/FragmentDelegate;->invalidateOptionsMenu()V

    .line 154
    :cond_0
    return-void
.end method

.method public final onActionModeFinished(Landroid/view/ActionMode;)V
    .locals 0

    .prologue
    .line 185
    return-void
.end method

.method public final onActionModeStarted(Landroid/view/ActionMode;)V
    .locals 0

    .prologue
    .line 175
    return-void
.end method

.method public onConfigurationChanged(Landroid/content/res/Configuration;)V
    .locals 1

    .prologue
    .line 74
    invoke-super {p0, p1}, Landroid/app/ListFragment;->onConfigurationChanged(Landroid/content/res/Configuration;)V

    .line 75
    iget-object v0, p0, Lmiui/app/ListFragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    invoke-virtual {v0, p1}, Lcom/miui/internal/app/FragmentDelegate;->onConfigurationChanged(Landroid/content/res/Configuration;)V

    .line 76
    return-void
.end method

.method public onCreate(Landroid/os/Bundle;)V
    .locals 1

    .prologue
    .line 31
    invoke-super {p0, p1}, Landroid/app/ListFragment;->onCreate(Landroid/os/Bundle;)V

    .line 32
    new-instance v0, Lcom/miui/internal/app/FragmentDelegate;

    invoke-direct {v0, p0}, Lcom/miui/internal/app/FragmentDelegate;-><init>(Landroid/app/Fragment;)V

    iput-object v0, p0, Lmiui/app/ListFragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    .line 33
    iget-object v0, p0, Lmiui/app/ListFragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    invoke-virtual {v0, p1}, Lcom/miui/internal/app/FragmentDelegate;->onCreate(Landroid/os/Bundle;)V

    .line 34
    return-void
.end method

.method public onCreateOptionsMenu(Landroid/view/Menu;)Z
    .locals 1

    .prologue
    .line 261
    const/4 v0, 0x1

    return v0
.end method

.method public onCreatePanelMenu(ILandroid/view/Menu;)Z
    .locals 2

    .prologue
    const/4 v0, 0x0

    .line 200
    if-nez p1, :cond_0

    .line 202
    iget-boolean v1, p0, Lmiui/app/ListFragment;->pp:Z

    if-eqz v1, :cond_0

    iget-boolean v1, p0, Lmiui/app/ListFragment;->pq:Z

    if-eqz v1, :cond_0

    .line 203
    invoke-virtual {p0}, Lmiui/app/ListFragment;->isHidden()Z

    move-result v1

    if-nez v1, :cond_0

    invoke-virtual {p0}, Lmiui/app/ListFragment;->isAdded()Z

    move-result v1

    if-eqz v1, :cond_0

    .line 204
    invoke-virtual {p0, p2}, Lmiui/app/ListFragment;->onCreateOptionsMenu(Landroid/view/Menu;)Z

    move-result v0

    .line 209
    :cond_0
    return v0
.end method

.method public final onCreateView(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Landroid/os/Bundle;)Landroid/view/View;
    .locals 1

    .prologue
    .line 64
    iget-object v0, p0, Lmiui/app/ListFragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    invoke-virtual {v0, p2, p3}, Lcom/miui/internal/app/FragmentDelegate;->onCreateView(Landroid/view/ViewGroup;Landroid/os/Bundle;)Landroid/view/View;

    move-result-object v0

    return-object v0
.end method

.method public onDestroy()V
    .locals 2

    .prologue
    .line 350
    invoke-super {p0}, Landroid/app/ListFragment;->onDestroy()V

    .line 352
    iget-object v0, p0, Lmiui/app/ListFragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Lcom/miui/internal/app/FragmentDelegate;->dismissImmersionMenu(Z)V

    .line 353
    return-void
.end method

.method public final onHiddenChanged(Z)V
    .locals 1

    .prologue
    .line 294
    invoke-super {p0, p1}, Landroid/app/ListFragment;->onHiddenChanged(Z)V

    .line 295
    if-nez p1, :cond_0

    iget-object v0, p0, Lmiui/app/ListFragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    if-eqz v0, :cond_0

    .line 296
    iget-object v0, p0, Lmiui/app/ListFragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    invoke-virtual {v0}, Lcom/miui/internal/app/FragmentDelegate;->invalidateOptionsMenu()V

    .line 298
    :cond_0
    if-nez p1, :cond_1

    const/4 v0, 0x1

    :goto_0
    invoke-virtual {p0, v0}, Lmiui/app/ListFragment;->onVisibilityChanged(Z)V

    .line 299
    return-void

    .line 298
    :cond_1
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public onInflateView(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Landroid/os/Bundle;)Landroid/view/View;
    .locals 1

    .prologue
    .line 279
    invoke-super {p0, p1, p2, p3}, Landroid/app/ListFragment;->onCreateView(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Landroid/os/Bundle;)Landroid/view/View;

    move-result-object v0

    return-object v0
.end method

.method public onPreparePanel(ILandroid/view/View;Landroid/view/Menu;)V
    .locals 1

    .prologue
    .line 223
    if-nez p1, :cond_0

    .line 224
    iget-boolean v0, p0, Lmiui/app/ListFragment;->pp:Z

    if-eqz v0, :cond_0

    iget-boolean v0, p0, Lmiui/app/ListFragment;->pq:Z

    if-eqz v0, :cond_0

    .line 225
    invoke-virtual {p0}, Lmiui/app/ListFragment;->isHidden()Z

    move-result v0

    if-nez v0, :cond_0

    invoke-virtual {p0}, Lmiui/app/ListFragment;->isAdded()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 226
    invoke-virtual {p0, p3}, Lmiui/app/ListFragment;->onPrepareOptionsMenu(Landroid/view/Menu;)V

    .line 230
    :cond_0
    return-void
.end method

.method public onResume()V
    .locals 1

    .prologue
    .line 44
    invoke-super {p0}, Landroid/app/ListFragment;->onResume()V

    .line 45
    iget-object v0, p0, Lmiui/app/ListFragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    invoke-virtual {v0}, Lcom/miui/internal/app/FragmentDelegate;->onPostResume()V

    .line 46
    return-void
.end method

.method public onStop()V
    .locals 1

    .prologue
    .line 38
    invoke-super {p0}, Landroid/app/ListFragment;->onStop()V

    .line 39
    iget-object v0, p0, Lmiui/app/ListFragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    invoke-virtual {v0}, Lcom/miui/internal/app/FragmentDelegate;->onStop()V

    .line 40
    return-void
.end method

.method public onVisibilityChanged(Z)V
    .locals 0

    .prologue
    .line 312
    return-void
.end method

.method public requestWindowFeature(I)Z
    .locals 1

    .prologue
    .line 140
    iget-object v0, p0, Lmiui/app/ListFragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    invoke-virtual {v0, p1}, Lcom/miui/internal/app/FragmentDelegate;->requestWindowFeature(I)Z

    move-result v0

    return v0
.end method

.method public setHasOptionsMenu(Z)V
    .locals 1

    .prologue
    .line 113
    invoke-super {p0, p1}, Landroid/app/ListFragment;->setHasOptionsMenu(Z)V

    .line 114
    iget-boolean v0, p0, Lmiui/app/ListFragment;->pp:Z

    if-eq v0, p1, :cond_0

    .line 115
    iput-boolean p1, p0, Lmiui/app/ListFragment;->pp:Z

    .line 116
    invoke-virtual {p0}, Lmiui/app/ListFragment;->isHidden()Z

    move-result v0

    if-nez v0, :cond_0

    invoke-virtual {p0}, Lmiui/app/ListFragment;->isAdded()Z

    move-result v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lmiui/app/ListFragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    if-eqz v0, :cond_0

    .line 117
    iget-object v0, p0, Lmiui/app/ListFragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    invoke-virtual {v0}, Lcom/miui/internal/app/FragmentDelegate;->invalidateOptionsMenu()V

    .line 120
    :cond_0
    return-void
.end method

.method public setImmersionMenuEnabled(Z)V
    .locals 1

    .prologue
    .line 320
    iget-object v0, p0, Lmiui/app/ListFragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    invoke-virtual {v0, p1}, Lcom/miui/internal/app/FragmentDelegate;->setImmersionMenuEnabled(Z)V

    .line 321
    return-void
.end method

.method public setMenuVisibility(Z)V
    .locals 1

    .prologue
    .line 124
    invoke-super {p0, p1}, Landroid/app/ListFragment;->setMenuVisibility(Z)V

    .line 125
    iget-boolean v0, p0, Lmiui/app/ListFragment;->pq:Z

    if-eq v0, p1, :cond_0

    .line 126
    iput-boolean p1, p0, Lmiui/app/ListFragment;->pq:Z

    .line 127
    invoke-virtual {p0}, Lmiui/app/ListFragment;->isHidden()Z

    move-result v0

    if-nez v0, :cond_0

    invoke-virtual {p0}, Lmiui/app/ListFragment;->isAdded()Z

    move-result v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lmiui/app/ListFragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    if-eqz v0, :cond_0

    .line 128
    iget-object v0, p0, Lmiui/app/ListFragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    invoke-virtual {v0}, Lcom/miui/internal/app/FragmentDelegate;->invalidateOptionsMenu()V

    .line 131
    :cond_0
    return-void
.end method

.method public setThemeRes(I)V
    .locals 1

    .prologue
    .line 103
    iget-object v0, p0, Lmiui/app/ListFragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    invoke-virtual {v0, p1}, Lcom/miui/internal/app/FragmentDelegate;->setExtraThemeRes(I)V

    .line 104
    return-void
.end method

.method public showImmersionMenu()V
    .locals 1

    .prologue
    .line 328
    iget-object v0, p0, Lmiui/app/ListFragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    invoke-virtual {v0}, Lcom/miui/internal/app/FragmentDelegate;->showImmersionMenu()V

    .line 329
    return-void
.end method

.method public showImmersionMenu(Landroid/view/View;Landroid/view/ViewGroup;)V
    .locals 1

    .prologue
    .line 337
    iget-object v0, p0, Lmiui/app/ListFragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    invoke-virtual {v0, p1, p2}, Lcom/miui/internal/app/FragmentDelegate;->showImmersionMenu(Landroid/view/View;Landroid/view/ViewGroup;)V

    .line 338
    return-void
.end method

.method public startActionMode(Landroid/view/ActionMode$Callback;)Landroid/view/ActionMode;
    .locals 1

    .prologue
    .line 98
    iget-object v0, p0, Lmiui/app/ListFragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    invoke-virtual {v0, p1}, Lcom/miui/internal/app/FragmentDelegate;->startActionMode(Landroid/view/ActionMode$Callback;)Landroid/view/ActionMode;

    move-result-object v0

    return-object v0
.end method

.method public updateOptionsMenuContent()V
    .locals 1

    .prologue
    .line 161
    iget-object v0, p0, Lmiui/app/ListFragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    if-eqz v0, :cond_0

    invoke-virtual {p0}, Lmiui/app/ListFragment;->isHidden()Z

    move-result v0

    if-nez v0, :cond_0

    iget-boolean v0, p0, Lmiui/app/ListFragment;->pp:Z

    if-eqz v0, :cond_0

    iget-boolean v0, p0, Lmiui/app/ListFragment;->pq:Z

    if-eqz v0, :cond_0

    invoke-virtual {p0}, Lmiui/app/ListFragment;->isAdded()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 163
    iget-object v0, p0, Lmiui/app/ListFragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    invoke-virtual {v0}, Lcom/miui/internal/app/FragmentDelegate;->invalidateOptionsMenu()V

    .line 165
    :cond_0
    return-void
.end method
