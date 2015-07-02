.class public Lmiui/preference/PreferenceActivity;
.super Landroid/preference/PreferenceActivity;
.source "SourceFile"

# interfaces
.implements Lcom/miui/internal/app/IActivity;


# instance fields
.field private ds:Lcom/miui/internal/app/ActivityDelegate;


# direct methods
.method public constructor <init>()V
    .locals 2

    .prologue
    .line 32
    invoke-direct {p0}, Landroid/preference/PreferenceActivity;-><init>()V

    .line 33
    new-instance v0, Lcom/miui/internal/app/ActivityDelegate;

    const-class v1, Landroid/preference/PreferenceActivity;

    invoke-direct {v0, p0, v1}, Lcom/miui/internal/app/ActivityDelegate;-><init>(Landroid/app/Activity;Ljava/lang/Class;)V

    iput-object v0, p0, Lmiui/preference/PreferenceActivity;->ds:Lcom/miui/internal/app/ActivityDelegate;

    .line 34
    return-void
.end method


# virtual methods
.method public dismissImmersionMenu(Z)V
    .locals 1

    .prologue
    .line 205
    iget-object v0, p0, Lmiui/preference/PreferenceActivity;->ds:Lcom/miui/internal/app/ActivityDelegate;

    invoke-virtual {v0, p1}, Lcom/miui/internal/app/ActivityDelegate;->dismissImmersionMenu(Z)V

    .line 206
    return-void
.end method

.method public bridge synthetic getActionBar()Landroid/app/ActionBar;
    .locals 1

    .prologue
    .line 24
    invoke-virtual {p0}, Lmiui/preference/PreferenceActivity;->getActionBar()Lmiui/app/ActionBar;

    move-result-object v0

    return-object v0
.end method

.method public getActionBar()Lmiui/app/ActionBar;
    .locals 1

    .prologue
    .line 38
    iget-object v0, p0, Lmiui/preference/PreferenceActivity;->ds:Lcom/miui/internal/app/ActivityDelegate;

    invoke-virtual {v0}, Lcom/miui/internal/app/ActivityDelegate;->getActionBar()Lmiui/app/ActionBar;

    move-result-object v0

    return-object v0
.end method

.method public getMenuInflater()Landroid/view/MenuInflater;
    .locals 1

    .prologue
    .line 43
    iget-object v0, p0, Lmiui/preference/PreferenceActivity;->ds:Lcom/miui/internal/app/ActivityDelegate;

    invoke-virtual {v0}, Lcom/miui/internal/app/ActivityDelegate;->getMenuInflater()Landroid/view/MenuInflater;

    move-result-object v0

    return-object v0
.end method

.method public getTranslucentStatus()I
    .locals 1

    .prologue
    .line 225
    iget-object v0, p0, Lmiui/preference/PreferenceActivity;->ds:Lcom/miui/internal/app/ActivityDelegate;

    invoke-virtual {v0}, Lcom/miui/internal/app/ActivityDelegate;->getTranslucentStatus()I

    move-result v0

    return v0
.end method

.method public invalidateOptionsMenu()V
    .locals 1

    .prologue
    .line 110
    iget-object v0, p0, Lmiui/preference/PreferenceActivity;->ds:Lcom/miui/internal/app/ActivityDelegate;

    invoke-virtual {v0}, Lcom/miui/internal/app/ActivityDelegate;->invalidateOptionsMenu()V

    .line 111
    return-void
.end method

.method public onActionModeFinished(Landroid/view/ActionMode;)V
    .locals 1

    .prologue
    .line 147
    iget-object v0, p0, Lmiui/preference/PreferenceActivity;->ds:Lcom/miui/internal/app/ActivityDelegate;

    invoke-virtual {v0, p1}, Lcom/miui/internal/app/ActivityDelegate;->onActionModeFinished(Landroid/view/ActionMode;)V

    .line 148
    return-void
.end method

.method public onActionModeStarted(Landroid/view/ActionMode;)V
    .locals 1

    .prologue
    .line 142
    iget-object v0, p0, Lmiui/preference/PreferenceActivity;->ds:Lcom/miui/internal/app/ActivityDelegate;

    invoke-virtual {v0, p1}, Lcom/miui/internal/app/ActivityDelegate;->onActionModeStarted(Landroid/view/ActionMode;)V

    .line 143
    return-void
.end method

.method public onBackPressed()V
    .locals 1

    .prologue
    .line 167
    iget-object v0, p0, Lmiui/preference/PreferenceActivity;->ds:Lcom/miui/internal/app/ActivityDelegate;

    invoke-virtual {v0}, Lcom/miui/internal/app/ActivityDelegate;->onBackPressed()Z

    move-result v0

    if-nez v0, :cond_0

    .line 168
    invoke-super {p0}, Landroid/preference/PreferenceActivity;->onBackPressed()V

    .line 170
    :cond_0
    return-void
.end method

.method public onConfigurationChanged(Landroid/content/res/Configuration;)V
    .locals 1

    .prologue
    .line 65
    invoke-super {p0, p1}, Landroid/preference/PreferenceActivity;->onConfigurationChanged(Landroid/content/res/Configuration;)V

    .line 66
    iget-object v0, p0, Lmiui/preference/PreferenceActivity;->ds:Lcom/miui/internal/app/ActivityDelegate;

    invoke-virtual {v0, p1}, Lcom/miui/internal/app/ActivityDelegate;->onConfigurationChanged(Landroid/content/res/Configuration;)V

    .line 67
    return-void
.end method

.method protected onCreate(Landroid/os/Bundle;)V
    .locals 2

    .prologue
    const/4 v1, 0x0

    .line 48
    invoke-super {p0, p1}, Landroid/preference/PreferenceActivity;->onCreate(Landroid/os/Bundle;)V

    .line 49
    iget-object v0, p0, Lmiui/preference/PreferenceActivity;->ds:Lcom/miui/internal/app/ActivityDelegate;

    invoke-virtual {v0, p1}, Lcom/miui/internal/app/ActivityDelegate;->onCreate(Landroid/os/Bundle;)V

    .line 52
    const v0, 0x102000a

    invoke-virtual {p0, v0}, Lmiui/preference/PreferenceActivity;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/ListView;

    .line 53
    if-eqz v0, :cond_0

    .line 54
    invoke-virtual {v0}, Landroid/widget/ListView;->getParent()Landroid/view/ViewParent;

    move-result-object v0

    check-cast v0, Landroid/view/ViewGroup;

    .line 55
    if-eqz v0, :cond_0

    .line 56
    invoke-virtual {v0, v1, v1, v1, v1}, Landroid/view/ViewGroup;->setPadding(IIII)V

    .line 60
    :cond_0
    invoke-static {}, Lcom/miui/internal/variable/Android_App_PreferenceActivity_class$Factory;->getInstance()Lcom/miui/internal/variable/Android_App_PreferenceActivity_class$Factory;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/internal/variable/Android_App_PreferenceActivity_class$Factory;->get()Lcom/miui/internal/variable/Android_App_PreferenceActivity_class;

    move-result-object v0

    invoke-virtual {v0, p0, p1}, Lcom/miui/internal/variable/Android_App_PreferenceActivity_class;->onCreate(Landroid/app/Activity;Landroid/os/Bundle;)V

    .line 61
    return-void
.end method

.method public onCreatePanelMenu(ILandroid/view/Menu;)Z
    .locals 1

    .prologue
    .line 152
    iget-object v0, p0, Lmiui/preference/PreferenceActivity;->ds:Lcom/miui/internal/app/ActivityDelegate;

    invoke-virtual {v0, p1, p2}, Lcom/miui/internal/app/ActivityDelegate;->onCreatePanelMenu(ILandroid/view/Menu;)Z

    move-result v0

    return v0
.end method

.method public onCreatePanelView(I)Landroid/view/View;
    .locals 1

    .prologue
    .line 95
    if-nez p1, :cond_0

    .line 96
    iget-object v0, p0, Lmiui/preference/PreferenceActivity;->ds:Lcom/miui/internal/app/ActivityDelegate;

    invoke-virtual {v0, p1}, Lcom/miui/internal/app/ActivityDelegate;->onCreatePanelView(I)Landroid/view/View;

    move-result-object v0

    .line 98
    :goto_0
    return-object v0

    :cond_0
    invoke-super {p0, p1}, Landroid/preference/PreferenceActivity;->onCreatePanelView(I)Landroid/view/View;

    move-result-object v0

    goto :goto_0
.end method

.method protected onDestroy()V
    .locals 2

    .prologue
    .line 230
    invoke-super {p0}, Landroid/preference/PreferenceActivity;->onDestroy()V

    .line 232
    iget-object v0, p0, Lmiui/preference/PreferenceActivity;->ds:Lcom/miui/internal/app/ActivityDelegate;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Lcom/miui/internal/app/ActivityDelegate;->dismissImmersionMenu(Z)V

    .line 233
    return-void
.end method

.method public onMenuItemSelected(ILandroid/view/MenuItem;)Z
    .locals 1

    .prologue
    .line 162
    iget-object v0, p0, Lmiui/preference/PreferenceActivity;->ds:Lcom/miui/internal/app/ActivityDelegate;

    invoke-virtual {v0, p1, p2}, Lcom/miui/internal/app/ActivityDelegate;->onMenuItemSelected(ILandroid/view/MenuItem;)Z

    move-result v0

    return v0
.end method

.method protected onPostResume()V
    .locals 1

    .prologue
    .line 89
    invoke-super {p0}, Landroid/preference/PreferenceActivity;->onPostResume()V

    .line 90
    iget-object v0, p0, Lmiui/preference/PreferenceActivity;->ds:Lcom/miui/internal/app/ActivityDelegate;

    invoke-virtual {v0}, Lcom/miui/internal/app/ActivityDelegate;->onPostResume()V

    .line 91
    return-void
.end method

.method public onPreparePanel(ILandroid/view/View;Landroid/view/Menu;)Z
    .locals 1

    .prologue
    .line 157
    iget-object v0, p0, Lmiui/preference/PreferenceActivity;->ds:Lcom/miui/internal/app/ActivityDelegate;

    invoke-virtual {v0, p1, p2, p3}, Lcom/miui/internal/app/ActivityDelegate;->onPreparePanel(ILandroid/view/View;Landroid/view/Menu;)Z

    move-result v0

    return v0
.end method

.method protected onRestoreInstanceState(Landroid/os/Bundle;)V
    .locals 1

    .prologue
    .line 77
    invoke-super {p0, p1}, Landroid/preference/PreferenceActivity;->onRestoreInstanceState(Landroid/os/Bundle;)V

    .line 78
    iget-object v0, p0, Lmiui/preference/PreferenceActivity;->ds:Lcom/miui/internal/app/ActivityDelegate;

    invoke-virtual {v0, p1}, Lcom/miui/internal/app/ActivityDelegate;->onRestoreInstanceState(Landroid/os/Bundle;)V

    .line 79
    return-void
.end method

.method protected onSaveInstanceState(Landroid/os/Bundle;)V
    .locals 1

    .prologue
    .line 71
    invoke-super {p0, p1}, Landroid/preference/PreferenceActivity;->onSaveInstanceState(Landroid/os/Bundle;)V

    .line 72
    iget-object v0, p0, Lmiui/preference/PreferenceActivity;->ds:Lcom/miui/internal/app/ActivityDelegate;

    invoke-virtual {v0, p1}, Lcom/miui/internal/app/ActivityDelegate;->onSaveInstanceState(Landroid/os/Bundle;)V

    .line 73
    return-void
.end method

.method protected onStop()V
    .locals 1

    .prologue
    .line 83
    invoke-super {p0}, Landroid/preference/PreferenceActivity;->onStop()V

    .line 84
    iget-object v0, p0, Lmiui/preference/PreferenceActivity;->ds:Lcom/miui/internal/app/ActivityDelegate;

    invoke-virtual {v0}, Lcom/miui/internal/app/ActivityDelegate;->onStop()V

    .line 85
    return-void
.end method

.method protected onTitleChanged(Ljava/lang/CharSequence;I)V
    .locals 1

    .prologue
    .line 104
    invoke-super {p0, p1, p2}, Landroid/preference/PreferenceActivity;->onTitleChanged(Ljava/lang/CharSequence;I)V

    .line 105
    iget-object v0, p0, Lmiui/preference/PreferenceActivity;->ds:Lcom/miui/internal/app/ActivityDelegate;

    invoke-virtual {v0, p1}, Lcom/miui/internal/app/ActivityDelegate;->onTitleChanged(Ljava/lang/CharSequence;)V

    .line 106
    return-void
.end method

.method public onWindowStartingActionMode(Landroid/view/ActionMode$Callback;)Landroid/view/ActionMode;
    .locals 1

    .prologue
    .line 137
    iget-object v0, p0, Lmiui/preference/PreferenceActivity;->ds:Lcom/miui/internal/app/ActivityDelegate;

    invoke-virtual {v0, p1}, Lcom/miui/internal/app/ActivityDelegate;->onWindowStartingActionMode(Landroid/view/ActionMode$Callback;)Landroid/view/ActionMode;

    move-result-object v0

    return-object v0
.end method

.method public requestExtraWindowFeature(I)Z
    .locals 1

    .prologue
    .line 128
    iget-object v0, p0, Lmiui/preference/PreferenceActivity;->ds:Lcom/miui/internal/app/ActivityDelegate;

    invoke-virtual {v0, p1}, Lcom/miui/internal/app/ActivityDelegate;->requestWindowFeature(I)Z

    move-result v0

    return v0
.end method

.method public setImmersionMenuEnabled(Z)V
    .locals 1

    .prologue
    .line 178
    iget-object v0, p0, Lmiui/preference/PreferenceActivity;->ds:Lcom/miui/internal/app/ActivityDelegate;

    invoke-virtual {v0, p1}, Lcom/miui/internal/app/ActivityDelegate;->setImmersionMenuEnabled(Z)V

    .line 179
    return-void
.end method

.method public setTranslucentStatus(I)V
    .locals 1

    .prologue
    .line 217
    iget-object v0, p0, Lmiui/preference/PreferenceActivity;->ds:Lcom/miui/internal/app/ActivityDelegate;

    invoke-virtual {v0, p1}, Lcom/miui/internal/app/ActivityDelegate;->setTranslucentStatus(I)V

    .line 218
    return-void
.end method

.method public showImmersionMenu()V
    .locals 1

    .prologue
    .line 187
    iget-object v0, p0, Lmiui/preference/PreferenceActivity;->ds:Lcom/miui/internal/app/ActivityDelegate;

    invoke-virtual {v0}, Lcom/miui/internal/app/ActivityDelegate;->showImmersionMenu()V

    .line 188
    return-void
.end method

.method public showImmersionMenu(Landroid/view/View;Landroid/view/ViewGroup;)V
    .locals 1

    .prologue
    .line 196
    iget-object v0, p0, Lmiui/preference/PreferenceActivity;->ds:Lcom/miui/internal/app/ActivityDelegate;

    invoke-virtual {v0, p1, p2}, Lcom/miui/internal/app/ActivityDelegate;->showImmersionMenu(Landroid/view/View;Landroid/view/ViewGroup;)V

    .line 197
    return-void
.end method

.method public startActionMode(Landroid/view/ActionMode$Callback;)Landroid/view/ActionMode;
    .locals 1

    .prologue
    .line 132
    iget-object v0, p0, Lmiui/preference/PreferenceActivity;->ds:Lcom/miui/internal/app/ActivityDelegate;

    invoke-virtual {v0, p1}, Lcom/miui/internal/app/ActivityDelegate;->startActionMode(Landroid/view/ActionMode$Callback;)Landroid/view/ActionMode;

    move-result-object v0

    return-object v0
.end method
