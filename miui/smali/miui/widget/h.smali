.class Lmiui/widget/h;
.super Landroid/animation/AnimatorListenerAdapter;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/widget/SlidingButton;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic OB:Lmiui/widget/SlidingButton;

.field private z:Z


# direct methods
.method constructor <init>(Lmiui/widget/SlidingButton;)V
    .locals 0

    .prologue
    .line 93
    iput-object p1, p0, Lmiui/widget/h;->OB:Lmiui/widget/SlidingButton;

    invoke-direct {p0}, Landroid/animation/AnimatorListenerAdapter;-><init>()V

    return-void
.end method


# virtual methods
.method public onAnimationCancel(Landroid/animation/Animator;)V
    .locals 1

    .prologue
    .line 104
    const/4 v0, 0x1

    iput-boolean v0, p0, Lmiui/widget/h;->z:Z

    .line 105
    return-void
.end method

.method public onAnimationEnd(Landroid/animation/Animator;)V
    .locals 3

    .prologue
    const/4 v0, 0x0

    .line 109
    iget-object v1, p0, Lmiui/widget/h;->OB:Lmiui/widget/SlidingButton;

    invoke-static {v1, v0}, Lmiui/widget/SlidingButton;->a(Lmiui/widget/SlidingButton;Z)Z

    .line 110
    iget-boolean v1, p0, Lmiui/widget/h;->z:Z

    if-eqz v1, :cond_1

    .line 128
    :cond_0
    :goto_0
    return-void

    .line 113
    :cond_1
    iget-object v1, p0, Lmiui/widget/h;->OB:Lmiui/widget/SlidingButton;

    const/4 v2, 0x0

    invoke-static {v1, v2}, Lmiui/widget/SlidingButton;->a(Lmiui/widget/SlidingButton;Landroid/animation/Animator;)Landroid/animation/Animator;

    .line 114
    iget-object v1, p0, Lmiui/widget/h;->OB:Lmiui/widget/SlidingButton;

    invoke-static {v1}, Lmiui/widget/SlidingButton;->a(Lmiui/widget/SlidingButton;)I

    move-result v1

    iget-object v2, p0, Lmiui/widget/h;->OB:Lmiui/widget/SlidingButton;

    invoke-static {v2}, Lmiui/widget/SlidingButton;->b(Lmiui/widget/SlidingButton;)I

    move-result v2

    if-lt v1, v2, :cond_2

    const/4 v0, 0x1

    .line 115
    :cond_2
    iget-object v1, p0, Lmiui/widget/h;->OB:Lmiui/widget/SlidingButton;

    invoke-virtual {v1}, Lmiui/widget/SlidingButton;->isChecked()Z

    move-result v1

    if-eq v0, v1, :cond_0

    .line 116
    iget-object v1, p0, Lmiui/widget/h;->OB:Lmiui/widget/SlidingButton;

    invoke-virtual {v1, v0}, Lmiui/widget/SlidingButton;->setChecked(Z)V

    .line 117
    iget-object v1, p0, Lmiui/widget/h;->OB:Lmiui/widget/SlidingButton;

    invoke-static {v1}, Lmiui/widget/SlidingButton;->c(Lmiui/widget/SlidingButton;)Landroid/widget/CompoundButton$OnCheckedChangeListener;

    move-result-object v1

    if-eqz v1, :cond_0

    .line 118
    iget-object v1, p0, Lmiui/widget/h;->OB:Lmiui/widget/SlidingButton;

    new-instance v2, Lmiui/widget/h$1;

    invoke-direct {v2, p0, v0}, Lmiui/widget/h$1;-><init>(Lmiui/widget/h;Z)V

    invoke-virtual {v1, v2}, Lmiui/widget/SlidingButton;->post(Ljava/lang/Runnable;)Z

    goto :goto_0
.end method

.method public onAnimationStart(Landroid/animation/Animator;)V
    .locals 2

    .prologue
    .line 98
    const/4 v0, 0x0

    iput-boolean v0, p0, Lmiui/widget/h;->z:Z

    .line 99
    iget-object v0, p0, Lmiui/widget/h;->OB:Lmiui/widget/SlidingButton;

    const/4 v1, 0x1

    invoke-static {v0, v1}, Lmiui/widget/SlidingButton;->a(Lmiui/widget/SlidingButton;Z)Z

    .line 100
    return-void
.end method
