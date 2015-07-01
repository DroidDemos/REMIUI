.class public Lmiui/preference/PreferenceFragment;
.super Landroid/preference/PreferenceFragment;
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

    .line 31
    invoke-direct {p0}, Landroid/preference/PreferenceFragment;-><init>()V

    .line 35
    iput-boolean v0, p0, Lmiui/preference/PreferenceFragment;->pp:Z

    .line 37
    iput-boolean v0, p0, Lmiui/preference/PreferenceFragment;->pq:Z

    return-void
.end method


# virtual methods
.method public dismissImmersionMenu(Z)V
    .locals 1

    .prologue
    .line 357
    iget-object v0, p0, Lmiui/preference/PreferenceFragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    invoke-virtual {v0, p1}, Lcom/miui/internal/app/FragmentDelegate;->dismissImmersionMenu(Z)V

    .line 358
    return-void
.end method

.method public getActionBar()Lmiui/app/ActionBar;
    .locals 1

    .prologue
    .line 94
    iget-object v0, p0, Lmiui/preference/PreferenceFragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    invoke-virtual {v0}, Lcom/miui/internal/app/FragmentDelegate;->getActionBar()Lmiui/app/ActionBar;

    move-result-object v0

    return-object v0
.end method

.method public getMenuInflater()Landroid/view/MenuInflater;
    .locals 1

    .prologue
    .line 103
    iget-object v0, p0, Lmiui/preference/PreferenceFragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    invoke-virtual {v0}, Lcom/miui/internal/app/FragmentDelegate;->getMenuInflater()Landroid/view/MenuInflater;

    move-result-object v0

    return-object v0
.end method

.method public getThemedContext()Landroid/content/Context;
    .locals 1

    .prologue
    .line 118
    iget-object v0, p0, Lmiui/preference/PreferenceFragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    invoke-virtual {v0}, Lcom/miui/internal/app/FragmentDelegate;->getThemedContext()Landroid/content/Context;

    move-result-object v0

    return-object v0
.end method

.method public getView()Landroid/view/View;
    .locals 1

    .prologue
    .line 79
    iget-object v0, p0, Lmiui/preference/PreferenceFragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    invoke-virtual {v0}, Lcom/miui/internal/app/FragmentDelegate;->getView()Landroid/view/View;

    move-result-object v0

    return-object v0
.end method

.method public invalidateOptionsMenu()V
    .locals 2

    .prologue
    .line 158
    iget-object v0, p0, Lmiui/preference/PreferenceFragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    if-eqz v0, :cond_0

    .line 159
    iget-object v0, p0, Lmiui/preference/PreferenceFragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Lcom/miui/internal/app/FragmentDelegate;->updateOptionsMenu(I)V

    .line 160
    invoke-virtual {p0}, Lmiui/preference/PreferenceFragment;->isHidden()Z

    move-result v0

    if-nez v0, :cond_0

    iget-boolean v0, p0, Lmiui/preference/PreferenceFragment;->pp:Z

    if-eqz v0, :cond_0

    iget-boolean v0, p0, Lmiui/preference/PreferenceFragment;->pq:Z

    if-eqz v0, :cond_0

    invoke-virtual {p0}, Lmiui/preference/PreferenceFragment;->isAdded()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 161
    iget-object v0, p0, Lmiui/preference/PreferenceFragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    invoke-virtual {v0}, Lcom/miui/internal/app/FragmentDelegate;->invalidateOptionsMenu()V

    .line 164
    :cond_0
    return-void
.end method

.method public final onActionModeFinished(Landroid/view/ActionMode;)V
    .locals 0

    .prologue
    .line 195
    return-void
.end method

.method public final onActionModeStarted(Landroid/view/ActionMode;)V
    .locals 0

    .prologue
    .line 185
    return-void
.end method

.method public onConfigurationChanged(Landroid/content/res/Configuration;)V
    .locals 1

    .prologue
    .line 84
    invoke-super {p0, p1}, Landroid/preference/PreferenceFragment;->onConfigurationChanged(Landroid/content/res/Configuration;)V

    .line 85
    iget-object v0, p0, Lmiui/preference/PreferenceFragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    invoke-virtual {v0, p1}, Lcom/miui/internal/app/FragmentDelegate;->onConfigurationChanged(Landroid/content/res/Configuration;)V

    .line 86
    return-void
.end method

.method public onCreate(Landroid/os/Bundle;)V
    .locals 1

    .prologue
    .line 41
    invoke-super {p0, p1}, Landroid/preference/PreferenceFragment;->onCreate(Landroid/os/Bundle;)V

    .line 42
    new-instance v0, Lcom/miui/internal/app/FragmentDelegate;

    invoke-direct {v0, p0}, Lcom/miui/internal/app/FragmentDelegate;-><init>(Landroid/app/Fragment;)V

    iput-object v0, p0, Lmiui/preference/PreferenceFragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    .line 43
    iget-object v0, p0, Lmiui/preference/PreferenceFragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    invoke-virtual {v0, p1}, Lcom/miui/internal/app/FragmentDelegate;->onCreate(Landroid/os/Bundle;)V

    .line 44
    return-void
.end method

.method public onCreateOptionsMenu(Landroid/view/Menu;)Z
    .locals 1

    .prologue
    .line 271
    const/4 v0, 0x1

    return v0
.end method

.method public onCreatePanelMenu(ILandroid/view/Menu;)Z
    .locals 2

    .prologue
    const/4 v0, 0x0

    .line 210
    if-nez p1, :cond_0

    .line 212
    iget-boolean v1, p0, Lmiui/preference/PreferenceFragment;->pp:Z

    if-eqz v1, :cond_0

    iget-boolean v1, p0, Lmiui/preference/PreferenceFragment;->pq:Z

    if-eqz v1, :cond_0

    .line 213
    invoke-virtual {p0}, Lmiui/preference/PreferenceFragment;->isHidden()Z

    move-result v1

    if-nez v1, :cond_0

    invoke-virtual {p0}, Lmiui/preference/PreferenceFragment;->isAdded()Z

    move-result v1

    if-eqz v1, :cond_0

    .line 214
    invoke-virtual {p0, p2}, Lmiui/preference/PreferenceFragment;->onCreateOptionsMenu(Landroid/view/Menu;)Z

    move-result v0

    .line 219
    :cond_0
    return v0
.end method

.method public final onCreateView(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Landroid/os/Bundle;)Landroid/view/View;
    .locals 1

    .prologue
    .line 74
    iget-object v0, p0, Lmiui/preference/PreferenceFragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    invoke-virtual {v0, p2, p3}, Lcom/miui/internal/app/FragmentDelegate;->onCreateView(Landroid/view/ViewGroup;Landroid/os/Bundle;)Landroid/view/View;

    move-result-object v0

    return-object v0
.end method

.method public onDestroy()V
    .locals 2

    .prologue
    .line 362
    invoke-super {p0}, Landroid/preference/PreferenceFragment;->onDestroy()V

    .line 364
    iget-object v0, p0, Lmiui/preference/PreferenceFragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Lcom/miui/internal/app/FragmentDelegate;->dismissImmersionMenu(Z)V

    .line 365
    return-void
.end method

.method public final onHiddenChanged(Z)V
    .locals 1

    .prologue
    .line 304
    invoke-super {p0, p1}, Landroid/preference/PreferenceFragment;->onHiddenChanged(Z)V

    .line 305
    if-nez p1, :cond_0

    iget-object v0, p0, Lmiui/preference/PreferenceFragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    if-eqz v0, :cond_0

    .line 306
    iget-object v0, p0, Lmiui/preference/PreferenceFragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    invoke-virtual {v0}, Lcom/miui/internal/app/FragmentDelegate;->invalidateOptionsMenu()V

    .line 308
    :cond_0
    if-nez p1, :cond_1

    const/4 v0, 0x1

    :goto_0
    invoke-virtual {p0, v0}, Lmiui/preference/PreferenceFragment;->onVisibilityChanged(Z)V

    .line 309
    return-void

    .line 308
    :cond_1
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public onInflateView(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Landroid/os/Bundle;)Landroid/view/View;
    .locals 1

    .prologue
    .line 289
    invoke-super {p0, p1, p2, p3}, Landroid/preference/PreferenceFragment;->onCreateView(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Landroid/os/Bundle;)Landroid/view/View;

    move-result-object v0

    return-object v0
.end method

.method public onPreparePanel(ILandroid/view/View;Landroid/view/Menu;)V
    .locals 1

    .prologue
    .line 233
    if-nez p1, :cond_0

    .line 234
    iget-boolean v0, p0, Lmiui/preference/PreferenceFragment;->pp:Z

    if-eqz v0, :cond_0

    iget-boolean v0, p0, Lmiui/preference/PreferenceFragment;->pq:Z

    if-eqz v0, :cond_0

    .line 235
    invoke-virtual {p0}, Lmiui/preference/PreferenceFragment;->isHidden()Z

    move-result v0

    if-nez v0, :cond_0

    invoke-virtual {p0}, Lmiui/preference/PreferenceFragment;->isAdded()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 236
    invoke-virtual {p0, p3}, Lmiui/preference/PreferenceFragment;->onPrepareOptionsMenu(Landroid/view/Menu;)V

    .line 240
    :cond_0
    return-void
.end method

.method public onResume()V
    .locals 1

    .prologue
    .line 54
    invoke-super {p0}, Landroid/preference/PreferenceFragment;->onResume()V

    .line 55
    iget-object v0, p0, Lmiui/preference/PreferenceFragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    invoke-virtual {v0}, Lcom/miui/internal/app/FragmentDelegate;->onPostResume()V

    .line 56
    return-void
.end method

.method public onStop()V
    .locals 1

    .prologue
    .line 48
    invoke-super {p0}, Landroid/preference/PreferenceFragment;->onStop()V

    .line 49
    iget-object v0, p0, Lmiui/preference/PreferenceFragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    invoke-virtual {v0}, Lcom/miui/internal/app/FragmentDelegate;->onStop()V

    .line 50
    return-void
.end method

.method public onVisibilityChanged(Z)V
    .locals 0

    .prologue
    .line 322
    return-void
.end method

.method public requestWindowFeature(I)Z
    .locals 1

    .prologue
    .line 150
    iget-object v0, p0, Lmiui/preference/PreferenceFragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    invoke-virtual {v0, p1}, Lcom/miui/internal/app/FragmentDelegate;->requestWindowFeature(I)Z

    move-result v0

    return v0
.end method

.method public setHasOptionsMenu(Z)V
    .locals 1

    .prologue
    .line 123
    invoke-super {p0, p1}, Landroid/preference/PreferenceFragment;->setHasOptionsMenu(Z)V

    .line 124
    iget-boolean v0, p0, Lmiui/preference/PreferenceFragment;->pp:Z

    if-eq v0, p1, :cond_0

    .line 125
    iput-boolean p1, p0, Lmiui/preference/PreferenceFragment;->pp:Z

    .line 126
    invoke-virtual {p0}, Lmiui/preference/PreferenceFragment;->isHidden()Z

    move-result v0

    if-nez v0, :cond_0

    invoke-virtual {p0}, Lmiui/preference/PreferenceFragment;->isAdded()Z

    move-result v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lmiui/preference/PreferenceFragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    if-eqz v0, :cond_0

    .line 127
    iget-object v0, p0, Lmiui/preference/PreferenceFragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    invoke-virtual {v0}, Lcom/miui/internal/app/FragmentDelegate;->invalidateOptionsMenu()V

    .line 130
    :cond_0
    return-void
.end method

.method public setImmersionMenuEnabled(Z)V
    .locals 1

    .prologue
    .line 330
    iget-object v0, p0, Lmiui/preference/PreferenceFragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    invoke-virtual {v0, p1}, Lcom/miui/internal/app/FragmentDelegate;->setImmersionMenuEnabled(Z)V

    .line 331
    return-void
.end method

.method public setMenuVisibility(Z)V
    .locals 1

    .prologue
    .line 134
    invoke-super {p0, p1}, Landroid/preference/PreferenceFragment;->setMenuVisibility(Z)V

    .line 135
    iget-boolean v0, p0, Lmiui/preference/PreferenceFragment;->pq:Z

    if-eq v0, p1, :cond_0

    .line 136
    iput-boolean p1, p0, Lmiui/preference/PreferenceFragment;->pq:Z

    .line 137
    invoke-virtual {p0}, Lmiui/preference/PreferenceFragment;->isHidden()Z

    move-result v0

    if-nez v0, :cond_0

    invoke-virtual {p0}, Lmiui/preference/PreferenceFragment;->isAdded()Z

    move-result v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lmiui/preference/PreferenceFragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    if-eqz v0, :cond_0

    .line 138
    iget-object v0, p0, Lmiui/preference/PreferenceFragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    invoke-virtual {v0}, Lcom/miui/internal/app/FragmentDelegate;->invalidateOptionsMenu()V

    .line 141
    :cond_0
    return-void
.end method

.method public setThemeRes(I)V
    .locals 1

    .prologue
    .line 113
    iget-object v0, p0, Lmiui/preference/PreferenceFragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    invoke-virtual {v0, p1}, Lcom/miui/internal/app/FragmentDelegate;->setExtraThemeRes(I)V

    .line 114
    return-void
.end method

.method public showImmersionMenu()V
    .locals 1

    .prologue
    .line 339
    iget-object v0, p0, Lmiui/preference/PreferenceFragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    invoke-virtual {v0}, Lcom/miui/internal/app/FragmentDelegate;->showImmersionMenu()V

    .line 340
    return-void
.end method

.method public showImmersionMenu(Landroid/view/View;Landroid/view/ViewGroup;)V
    .locals 1

    .prologue
    .line 348
    iget-object v0, p0, Lmiui/preference/PreferenceFragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    invoke-virtual {v0, p1, p2}, Lcom/miui/internal/app/FragmentDelegate;->showImmersionMenu(Landroid/view/View;Landroid/view/ViewGroup;)V

    .line 349
    return-void
.end method

.method public startActionMode(Landroid/view/ActionMode$Callback;)Landroid/view/ActionMode;
    .locals 1

    .prologue
    .line 108
    iget-object v0, p0, Lmiui/preference/PreferenceFragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    invoke-virtual {v0, p1}, Lcom/miui/internal/app/FragmentDelegate;->startActionMode(Landroid/view/ActionMode$Callback;)Landroid/view/ActionMode;

    move-result-object v0

    return-object v0
.end method

.method public updateOptionsMenuContent()V
    .locals 1

    .prologue
    .line 171
    iget-object v0, p0, Lmiui/preference/PreferenceFragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    if-eqz v0, :cond_0

    invoke-virtual {p0}, Lmiui/preference/PreferenceFragment;->isHidden()Z

    move-result v0

    if-nez v0, :cond_0

    iget-boolean v0, p0, Lmiui/preference/PreferenceFragment;->pp:Z

    if-eqz v0, :cond_0

    iget-boolean v0, p0, Lmiui/preference/PreferenceFragment;->pq:Z

    if-eqz v0, :cond_0

    invoke-virtual {p0}, Lmiui/preference/PreferenceFragment;->isAdded()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 173
    iget-object v0, p0, Lmiui/preference/PreferenceFragment;->po:Lcom/miui/internal/app/FragmentDelegate;

    invoke-virtual {v0}, Lcom/miui/internal/app/FragmentDelegate;->invalidateOptionsMenu()V

    .line 175
    :cond_0
    return-void
.end method
