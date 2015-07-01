.class Lmiui/widget/ImmersionListPopupWindow$b;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroid/view/animation/Animation$AnimationListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/widget/ImmersionListPopupWindow;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x2
    name = "b"
.end annotation


# instance fields
.field private dP:Z

.field private dQ:Landroid/animation/Animator;

.field final synthetic dv:Lmiui/widget/ImmersionListPopupWindow;


# direct methods
.method private constructor <init>(Lmiui/widget/ImmersionListPopupWindow;)V
    .locals 0

    .prologue
    .line 394
    iput-object p1, p0, Lmiui/widget/ImmersionListPopupWindow$b;->dv:Lmiui/widget/ImmersionListPopupWindow;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method synthetic constructor <init>(Lmiui/widget/ImmersionListPopupWindow;Lmiui/widget/ImmersionListPopupWindow$4;)V
    .locals 0

    .prologue
    .line 394
    invoke-direct {p0, p1}, Lmiui/widget/ImmersionListPopupWindow$b;-><init>(Lmiui/widget/ImmersionListPopupWindow;)V

    return-void
.end method


# virtual methods
.method public g(Z)V
    .locals 0

    .prologue
    .line 400
    iput-boolean p1, p0, Lmiui/widget/ImmersionListPopupWindow$b;->dP:Z

    .line 401
    return-void
.end method

.method public onAnimationEnd(Landroid/view/animation/Animation;)V
    .locals 2

    .prologue
    const/4 v1, 0x0

    .line 415
    iget-object v0, p0, Lmiui/widget/ImmersionListPopupWindow$b;->dQ:Landroid/animation/Animator;

    if-eqz v0, :cond_0

    .line 416
    iget-object v0, p0, Lmiui/widget/ImmersionListPopupWindow$b;->dQ:Landroid/animation/Animator;

    invoke-virtual {v0}, Landroid/animation/Animator;->cancel()V

    .line 417
    iput-object v1, p0, Lmiui/widget/ImmersionListPopupWindow$b;->dQ:Landroid/animation/Animator;

    .line 419
    :cond_0
    iget-object v0, p0, Lmiui/widget/ImmersionListPopupWindow$b;->dv:Lmiui/widget/ImmersionListPopupWindow;

    invoke-static {v0}, Lmiui/widget/ImmersionListPopupWindow;->a(Lmiui/widget/ImmersionListPopupWindow;)Landroid/widget/ListView;

    move-result-object v0

    invoke-virtual {v0, v1}, Landroid/widget/ListView;->setLayoutAnimation(Landroid/view/animation/LayoutAnimationController;)V

    .line 420
    iget-boolean v0, p0, Lmiui/widget/ImmersionListPopupWindow$b;->dP:Z

    if-nez v0, :cond_1

    .line 421
    iget-object v0, p0, Lmiui/widget/ImmersionListPopupWindow$b;->dv:Lmiui/widget/ImmersionListPopupWindow;

    invoke-static {v0}, Lmiui/widget/ImmersionListPopupWindow;->i(Lmiui/widget/ImmersionListPopupWindow;)V

    .line 423
    :cond_1
    return-void
.end method

.method public onAnimationRepeat(Landroid/view/animation/Animation;)V
    .locals 0

    .prologue
    .line 427
    return-void
.end method

.method public onAnimationStart(Landroid/view/animation/Animation;)V
    .locals 3

    .prologue
    .line 405
    iget-boolean v0, p0, Lmiui/widget/ImmersionListPopupWindow$b;->dP:Z

    if-eqz v0, :cond_1

    iget-object v0, p0, Lmiui/widget/ImmersionListPopupWindow$b;->dv:Lmiui/widget/ImmersionListPopupWindow;

    invoke-static {v0}, Lmiui/widget/ImmersionListPopupWindow;->g(Lmiui/widget/ImmersionListPopupWindow;)Landroid/view/animation/LayoutAnimationController;

    move-result-object v0

    .line 407
    :goto_0
    iget-object v1, p0, Lmiui/widget/ImmersionListPopupWindow$b;->dv:Lmiui/widget/ImmersionListPopupWindow;

    iget-boolean v2, p0, Lmiui/widget/ImmersionListPopupWindow$b;->dP:Z

    invoke-virtual {v1, v0, v2}, Lmiui/widget/ImmersionListPopupWindow;->getBackgroundAnimator(Landroid/view/animation/LayoutAnimationController;Z)Landroid/animation/Animator;

    move-result-object v0

    iput-object v0, p0, Lmiui/widget/ImmersionListPopupWindow$b;->dQ:Landroid/animation/Animator;

    .line 408
    iget-object v0, p0, Lmiui/widget/ImmersionListPopupWindow$b;->dQ:Landroid/animation/Animator;

    if-eqz v0, :cond_0

    .line 409
    iget-object v0, p0, Lmiui/widget/ImmersionListPopupWindow$b;->dQ:Landroid/animation/Animator;

    invoke-virtual {v0}, Landroid/animation/Animator;->start()V

    .line 411
    :cond_0
    return-void

    .line 405
    :cond_1
    iget-object v0, p0, Lmiui/widget/ImmersionListPopupWindow$b;->dv:Lmiui/widget/ImmersionListPopupWindow;

    invoke-static {v0}, Lmiui/widget/ImmersionListPopupWindow;->h(Lmiui/widget/ImmersionListPopupWindow;)Landroid/view/animation/LayoutAnimationController;

    move-result-object v0

    goto :goto_0
.end method
