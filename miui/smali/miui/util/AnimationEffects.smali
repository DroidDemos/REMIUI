.class public Lmiui/util/AnimationEffects;
.super Ljava/lang/Object;
.source "SourceFile"


# direct methods
.method public constructor <init>()V
    .locals 0

    .prologue
    .line 17
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method private static l(Landroid/view/View;)Landroid/os/Bundle;
    .locals 2

    .prologue
    const/16 v1, 0x32

    const/4 v0, 0x0

    .line 84
    invoke-static {p0, v0, v0, v1, v1}, Landroid/app/ActivityOptions;->makeScaleUpAnimation(Landroid/view/View;IIII)Landroid/app/ActivityOptions;

    move-result-object v0

    invoke-virtual {v0}, Landroid/app/ActivityOptions;->toBundle()Landroid/os/Bundle;

    move-result-object v0

    return-object v0
.end method

.method public static setActivityExitAnimation(Landroid/app/Activity;)V
    .locals 1

    .prologue
    .line 80
    sget v0, Lcom/miui/internal/R$anim;->dialog_scale_down:I

    invoke-static {p0, v0}, Lmiui/util/AnimationEffects;->setActivityExitAnimation(Landroid/app/Activity;I)V

    .line 81
    return-void
.end method

.method public static setActivityExitAnimation(Landroid/app/Activity;I)V
    .locals 1

    .prologue
    .line 60
    const/4 v0, 0x0

    invoke-virtual {p0, v0, p1}, Landroid/app/Activity;->overridePendingTransition(II)V

    .line 61
    return-void
.end method

.method public static startActivityForResultWithScaleUpAnimation(Landroid/app/Activity;Landroid/content/Intent;ILandroid/view/View;)V
    .locals 1

    .prologue
    .line 40
    invoke-static {p3}, Lmiui/util/AnimationEffects;->l(Landroid/view/View;)Landroid/os/Bundle;

    move-result-object v0

    invoke-virtual {p0, p1, p2, v0}, Landroid/app/Activity;->startActivityForResult(Landroid/content/Intent;ILandroid/os/Bundle;)V

    .line 41
    return-void
.end method

.method public static startActivityWithScaleUpAnimation(Landroid/content/Context;Landroid/content/Intent;Landroid/view/View;)V
    .locals 1

    .prologue
    .line 27
    invoke-static {p2}, Lmiui/util/AnimationEffects;->l(Landroid/view/View;)Landroid/os/Bundle;

    move-result-object v0

    invoke-virtual {p0, p1, v0}, Landroid/content/Context;->startActivity(Landroid/content/Intent;Landroid/os/Bundle;)V

    .line 28
    return-void
.end method
