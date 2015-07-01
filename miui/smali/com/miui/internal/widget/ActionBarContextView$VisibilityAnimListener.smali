.class public Lcom/miui/internal/widget/ActionBarContextView$VisibilityAnimListener;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroid/animation/Animator$AnimatorListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/internal/widget/ActionBarContextView;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x4
    name = "VisibilityAnimListener"
.end annotation


# instance fields
.field final synthetic A:Lcom/miui/internal/widget/ActionBarContextView;

.field y:Z

.field private z:Z


# direct methods
.method protected constructor <init>(Lcom/miui/internal/widget/ActionBarContextView;)V
    .locals 1

    .prologue
    .line 619
    iput-object p1, p0, Lcom/miui/internal/widget/ActionBarContextView$VisibilityAnimListener;->A:Lcom/miui/internal/widget/ActionBarContextView;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 623
    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/miui/internal/widget/ActionBarContextView$VisibilityAnimListener;->z:Z

    return-void
.end method


# virtual methods
.method public onAnimationCancel(Landroid/animation/Animator;)V
    .locals 1

    .prologue
    .line 658
    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/miui/internal/widget/ActionBarContextView$VisibilityAnimListener;->z:Z

    .line 659
    return-void
.end method

.method public onAnimationEnd(Landroid/animation/Animator;)V
    .locals 4

    .prologue
    const/16 v2, 0x8

    const/4 v1, 0x0

    .line 639
    iget-boolean v0, p0, Lcom/miui/internal/widget/ActionBarContextView$VisibilityAnimListener;->z:Z

    if-eqz v0, :cond_1

    .line 654
    :cond_0
    :goto_0
    return-void

    .line 643
    :cond_1
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContextView$VisibilityAnimListener;->A:Lcom/miui/internal/widget/ActionBarContextView;

    invoke-static {v0}, Lcom/miui/internal/widget/ActionBarContextView;->d(Lcom/miui/internal/widget/ActionBarContextView;)I

    move-result v0

    const/4 v3, 0x2

    if-ne v0, v3, :cond_2

    .line 644
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContextView$VisibilityAnimListener;->A:Lcom/miui/internal/widget/ActionBarContextView;

    iget-boolean v3, p0, Lcom/miui/internal/widget/ActionBarContextView$VisibilityAnimListener;->y:Z

    invoke-virtual {v0, v3}, Lcom/miui/internal/widget/ActionBarContextView;->notifyAnimationEnd(Z)V

    .line 645
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContextView$VisibilityAnimListener;->A:Lcom/miui/internal/widget/ActionBarContextView;

    invoke-virtual {v0}, Lcom/miui/internal/widget/ActionBarContextView;->killMode()V

    .line 647
    :cond_2
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContextView$VisibilityAnimListener;->A:Lcom/miui/internal/widget/ActionBarContextView;

    invoke-static {v0, v1}, Lcom/miui/internal/widget/ActionBarContextView;->a(Lcom/miui/internal/widget/ActionBarContextView;I)I

    .line 649
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContextView$VisibilityAnimListener;->A:Lcom/miui/internal/widget/ActionBarContextView;

    const/4 v3, 0x0

    invoke-static {v0, v3}, Lcom/miui/internal/widget/ActionBarContextView;->a(Lcom/miui/internal/widget/ActionBarContextView;Landroid/animation/Animator;)Landroid/animation/Animator;

    .line 650
    iget-object v3, p0, Lcom/miui/internal/widget/ActionBarContextView$VisibilityAnimListener;->A:Lcom/miui/internal/widget/ActionBarContextView;

    iget-boolean v0, p0, Lcom/miui/internal/widget/ActionBarContextView$VisibilityAnimListener;->y:Z

    if-eqz v0, :cond_3

    move v0, v1

    :goto_1
    invoke-virtual {v3, v0}, Lcom/miui/internal/widget/ActionBarContextView;->setVisibility(I)V

    .line 651
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContextView$VisibilityAnimListener;->A:Lcom/miui/internal/widget/ActionBarContextView;

    iget-object v0, v0, Lcom/miui/internal/widget/ActionBarContextView;->mSplitView:Lcom/miui/internal/widget/ActionBarContainer;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContextView$VisibilityAnimListener;->A:Lcom/miui/internal/widget/ActionBarContextView;

    iget-object v0, v0, Lcom/miui/internal/widget/ActionBarContextView;->mMenuView:Lcom/miui/internal/view/menu/ActionMenuView;

    if-eqz v0, :cond_0

    .line 652
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContextView$VisibilityAnimListener;->A:Lcom/miui/internal/widget/ActionBarContextView;

    iget-object v0, v0, Lcom/miui/internal/widget/ActionBarContextView;->mMenuView:Lcom/miui/internal/view/menu/ActionMenuView;

    iget-boolean v3, p0, Lcom/miui/internal/widget/ActionBarContextView$VisibilityAnimListener;->y:Z

    if-eqz v3, :cond_4

    :goto_2
    invoke-virtual {v0, v1}, Lcom/miui/internal/view/menu/ActionMenuView;->setVisibility(I)V

    goto :goto_0

    :cond_3
    move v0, v2

    .line 650
    goto :goto_1

    :cond_4
    move v1, v2

    .line 652
    goto :goto_2
.end method

.method public onAnimationRepeat(Landroid/animation/Animator;)V
    .locals 0

    .prologue
    .line 663
    return-void
.end method

.method public onAnimationStart(Landroid/animation/Animator;)V
    .locals 2

    .prologue
    const/4 v1, 0x0

    .line 632
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContextView$VisibilityAnimListener;->A:Lcom/miui/internal/widget/ActionBarContextView;

    invoke-virtual {v0, v1}, Lcom/miui/internal/widget/ActionBarContextView;->setVisibility(I)V

    .line 633
    iput-boolean v1, p0, Lcom/miui/internal/widget/ActionBarContextView$VisibilityAnimListener;->z:Z

    .line 634
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContextView$VisibilityAnimListener;->A:Lcom/miui/internal/widget/ActionBarContextView;

    iget-boolean v1, p0, Lcom/miui/internal/widget/ActionBarContextView$VisibilityAnimListener;->y:Z

    invoke-virtual {v0, v1}, Lcom/miui/internal/widget/ActionBarContextView;->notifyAnimationStart(Z)V

    .line 635
    return-void
.end method

.method public withFinalVisibility(Z)Lcom/miui/internal/widget/ActionBarContextView$VisibilityAnimListener;
    .locals 0

    .prologue
    .line 626
    iput-boolean p1, p0, Lcom/miui/internal/widget/ActionBarContextView$VisibilityAnimListener;->y:Z

    .line 627
    return-object p0
.end method
