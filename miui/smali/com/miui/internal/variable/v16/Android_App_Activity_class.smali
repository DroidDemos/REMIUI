.class public Lcom/miui/internal/variable/v16/Android_App_Activity_class;
.super Lcom/miui/internal/variable/Android_App_Activity_class;
.source "SourceFile"


# direct methods
.method public constructor <init>()V
    .locals 0

    .prologue
    .line 14
    invoke-direct {p0}, Lcom/miui/internal/variable/Android_App_Activity_class;-><init>()V

    return-void
.end method


# virtual methods
.method public buildProxy()V
    .locals 2

    .prologue
    .line 19
    const-string v0, "setProgressBarVisibility"

    const-string v1, "(Z)V"

    invoke-virtual {p0, v0, v1}, Lcom/miui/internal/variable/v16/Android_App_Activity_class;->attachMethod(Ljava/lang/String;Ljava/lang/String;)J

    .line 20
    const-string v0, "setProgressBarIndeterminateVisibility"

    const-string v1, "(Z)V"

    invoke-virtual {p0, v0, v1}, Lcom/miui/internal/variable/v16/Android_App_Activity_class;->attachMethod(Ljava/lang/String;Ljava/lang/String;)J

    .line 21
    const-string v0, "setProgressBarIndeterminate"

    const-string v1, "(Z)V"

    invoke-virtual {p0, v0, v1}, Lcom/miui/internal/variable/v16/Android_App_Activity_class;->attachMethod(Ljava/lang/String;Ljava/lang/String;)J

    .line 22
    const-string v0, "setProgress"

    const-string v1, "(I)V"

    invoke-virtual {p0, v0, v1}, Lcom/miui/internal/variable/v16/Android_App_Activity_class;->attachMethod(Ljava/lang/String;Ljava/lang/String;)J

    .line 23
    return-void
.end method

.method protected handle()V
    .locals 4

    .prologue
    const-wide/16 v2, 0x0

    const/4 v1, 0x0

    const/4 v0, 0x0

    .line 27
    invoke-virtual {p0, v2, v3, v1, v0}, Lcom/miui/internal/variable/v16/Android_App_Activity_class;->handleSetProgressBarVisibility(JLandroid/app/Activity;Z)V

    .line 28
    invoke-virtual {p0, v2, v3, v1, v0}, Lcom/miui/internal/variable/v16/Android_App_Activity_class;->handleSetProgressBarIndeterminateVisibility(JLandroid/app/Activity;Z)V

    .line 29
    invoke-virtual {p0, v2, v3, v1, v0}, Lcom/miui/internal/variable/v16/Android_App_Activity_class;->handleSetProgressBarIndeterminate(JLandroid/app/Activity;Z)V

    .line 30
    invoke-virtual {p0, v2, v3, v1, v0}, Lcom/miui/internal/variable/v16/Android_App_Activity_class;->handleSetProgress(JLandroid/app/Activity;I)V

    .line 31
    return-void
.end method

.method protected handleSetProgress(JLandroid/app/Activity;I)V
    .locals 1

    .prologue
    .line 82
    instance-of v0, p3, Lmiui/app/Activity;

    if-eqz v0, :cond_1

    .line 83
    check-cast p3, Lmiui/app/Activity;

    invoke-virtual {p3}, Lmiui/app/Activity;->getActionBar()Lmiui/app/ActionBar;

    move-result-object v0

    .line 84
    if-eqz v0, :cond_0

    .line 85
    invoke-virtual {v0, p4}, Lmiui/app/ActionBar;->setProgress(I)V

    .line 90
    :cond_0
    :goto_0
    return-void

    .line 88
    :cond_1
    invoke-virtual {p0, p1, p2, p3, p4}, Lcom/miui/internal/variable/v16/Android_App_Activity_class;->originalSetProgress(JLandroid/app/Activity;I)V

    goto :goto_0
.end method

.method protected handleSetProgressBarIndeterminate(JLandroid/app/Activity;Z)V
    .locals 1

    .prologue
    .line 66
    instance-of v0, p3, Lmiui/app/Activity;

    if-eqz v0, :cond_1

    .line 67
    check-cast p3, Lmiui/app/Activity;

    invoke-virtual {p3}, Lmiui/app/Activity;->getActionBar()Lmiui/app/ActionBar;

    move-result-object v0

    .line 68
    if-eqz v0, :cond_0

    .line 69
    invoke-virtual {v0, p4}, Lmiui/app/ActionBar;->setProgressBarIndeterminate(Z)V

    .line 74
    :cond_0
    :goto_0
    return-void

    .line 72
    :cond_1
    invoke-virtual {p0, p1, p2, p3, p4}, Lcom/miui/internal/variable/v16/Android_App_Activity_class;->originalSetProgressBarIndeterminate(JLandroid/app/Activity;Z)V

    goto :goto_0
.end method

.method protected handleSetProgressBarIndeterminateVisibility(JLandroid/app/Activity;Z)V
    .locals 1

    .prologue
    .line 50
    instance-of v0, p3, Lmiui/app/Activity;

    if-eqz v0, :cond_1

    .line 51
    check-cast p3, Lmiui/app/Activity;

    invoke-virtual {p3}, Lmiui/app/Activity;->getActionBar()Lmiui/app/ActionBar;

    move-result-object v0

    .line 52
    if-eqz v0, :cond_0

    .line 53
    invoke-virtual {v0, p4}, Lmiui/app/ActionBar;->setProgressBarIndeterminateVisibility(Z)V

    .line 58
    :cond_0
    :goto_0
    return-void

    .line 56
    :cond_1
    invoke-virtual {p0, p1, p2, p3, p4}, Lcom/miui/internal/variable/v16/Android_App_Activity_class;->originalSetProgressBarIndeterminateVisibility(JLandroid/app/Activity;Z)V

    goto :goto_0
.end method

.method protected handleSetProgressBarVisibility(JLandroid/app/Activity;Z)V
    .locals 1

    .prologue
    .line 34
    instance-of v0, p3, Lmiui/app/Activity;

    if-eqz v0, :cond_1

    .line 35
    check-cast p3, Lmiui/app/Activity;

    invoke-virtual {p3}, Lmiui/app/Activity;->getActionBar()Lmiui/app/ActionBar;

    move-result-object v0

    .line 36
    if-eqz v0, :cond_0

    .line 37
    invoke-virtual {v0, p4}, Lmiui/app/ActionBar;->setProgressBarVisibility(Z)V

    .line 42
    :cond_0
    :goto_0
    return-void

    .line 40
    :cond_1
    invoke-virtual {p0, p1, p2, p3, p4}, Lcom/miui/internal/variable/v16/Android_App_Activity_class;->originalSetProgressBarVisibility(JLandroid/app/Activity;Z)V

    goto :goto_0
.end method

.method protected originalSetProgress(JLandroid/app/Activity;I)V
    .locals 2

    .prologue
    .line 93
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "com.miui.internal.variable.v16.Android_App_Activity_class.originalSetProgress(long, Activity, int)"

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

.method protected originalSetProgressBarIndeterminate(JLandroid/app/Activity;Z)V
    .locals 2

    .prologue
    .line 77
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "com.miui.internal.variable.v16.Android_App_Activity_class.originalSetProgressBarIndeterminate(long, Activity, boolean)"

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

.method protected originalSetProgressBarIndeterminateVisibility(JLandroid/app/Activity;Z)V
    .locals 2

    .prologue
    .line 61
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "com.miui.internal.variable.v16.Android_App_Activity_class.originalSetProgressBarIndeterminateVisibility(long, Activity, boolean)"

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

.method protected originalSetProgressBarVisibility(JLandroid/app/Activity;Z)V
    .locals 2

    .prologue
    .line 45
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "com.miui.internal.variable.v16.Android_App_Activity_class.originalSetProgressBarVisibility(long, Activity, boolean)"

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0
.end method
