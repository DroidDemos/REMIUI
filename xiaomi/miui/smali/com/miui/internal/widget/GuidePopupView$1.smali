.class Lcom/miui/internal/widget/GuidePopupView$1;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroid/view/ViewTreeObserver$OnPreDrawListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/internal/widget/GuidePopupView;->animateToShow()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic bb:Lcom/miui/internal/widget/GuidePopupView;


# direct methods
.method constructor <init>(Lcom/miui/internal/widget/GuidePopupView;)V
    .locals 0

    .prologue
    .line 483
    iput-object p1, p0, Lcom/miui/internal/widget/GuidePopupView$1;->bb:Lcom/miui/internal/widget/GuidePopupView;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onPreDraw()Z
    .locals 7

    .prologue
    const/4 v6, 0x1

    .line 486
    iget-object v0, p0, Lcom/miui/internal/widget/GuidePopupView$1;->bb:Lcom/miui/internal/widget/GuidePopupView;

    invoke-virtual {v0}, Lcom/miui/internal/widget/GuidePopupView;->getViewTreeObserver()Landroid/view/ViewTreeObserver;

    move-result-object v0

    invoke-virtual {v0, p0}, Landroid/view/ViewTreeObserver;->removeOnPreDrawListener(Landroid/view/ViewTreeObserver$OnPreDrawListener;)V

    .line 487
    iget-object v0, p0, Lcom/miui/internal/widget/GuidePopupView$1;->bb:Lcom/miui/internal/widget/GuidePopupView;

    invoke-static {v0}, Lcom/miui/internal/widget/GuidePopupView;->b(Lcom/miui/internal/widget/GuidePopupView;)Landroid/animation/ObjectAnimator;

    move-result-object v0

    if-eqz v0, :cond_0

    .line 488
    iget-object v0, p0, Lcom/miui/internal/widget/GuidePopupView$1;->bb:Lcom/miui/internal/widget/GuidePopupView;

    invoke-static {v0}, Lcom/miui/internal/widget/GuidePopupView;->b(Lcom/miui/internal/widget/GuidePopupView;)Landroid/animation/ObjectAnimator;

    move-result-object v0

    invoke-virtual {v0}, Landroid/animation/ObjectAnimator;->cancel()V

    .line 490
    :cond_0
    iget-object v0, p0, Lcom/miui/internal/widget/GuidePopupView$1;->bb:Lcom/miui/internal/widget/GuidePopupView;

    iget-object v1, p0, Lcom/miui/internal/widget/GuidePopupView$1;->bb:Lcom/miui/internal/widget/GuidePopupView;

    sget-object v2, Landroid/view/View;->ALPHA:Landroid/util/Property;

    new-array v3, v6, [F

    const/4 v4, 0x0

    const/high16 v5, 0x3f800000

    aput v5, v3, v4

    invoke-static {v1, v2, v3}, Landroid/animation/ObjectAnimator;->ofFloat(Ljava/lang/Object;Landroid/util/Property;[F)Landroid/animation/ObjectAnimator;

    move-result-object v1

    invoke-static {v0, v1}, Lcom/miui/internal/widget/GuidePopupView;->a(Lcom/miui/internal/widget/GuidePopupView;Landroid/animation/ObjectAnimator;)Landroid/animation/ObjectAnimator;

    .line 491
    iget-object v0, p0, Lcom/miui/internal/widget/GuidePopupView$1;->bb:Lcom/miui/internal/widget/GuidePopupView;

    invoke-static {v0}, Lcom/miui/internal/widget/GuidePopupView;->b(Lcom/miui/internal/widget/GuidePopupView;)Landroid/animation/ObjectAnimator;

    move-result-object v0

    const-wide/16 v1, 0x12c

    invoke-virtual {v0, v1, v2}, Landroid/animation/ObjectAnimator;->setDuration(J)Landroid/animation/ObjectAnimator;

    .line 492
    iget-object v0, p0, Lcom/miui/internal/widget/GuidePopupView$1;->bb:Lcom/miui/internal/widget/GuidePopupView;

    invoke-static {v0}, Lcom/miui/internal/widget/GuidePopupView;->b(Lcom/miui/internal/widget/GuidePopupView;)Landroid/animation/ObjectAnimator;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/internal/widget/GuidePopupView$1;->bb:Lcom/miui/internal/widget/GuidePopupView;

    invoke-static {v1}, Lcom/miui/internal/widget/GuidePopupView;->c(Lcom/miui/internal/widget/GuidePopupView;)Landroid/animation/Animator$AnimatorListener;

    move-result-object v1

    invoke-virtual {v0, v1}, Landroid/animation/ObjectAnimator;->addListener(Landroid/animation/Animator$AnimatorListener;)V

    .line 493
    iget-object v0, p0, Lcom/miui/internal/widget/GuidePopupView$1;->bb:Lcom/miui/internal/widget/GuidePopupView;

    invoke-static {v0}, Lcom/miui/internal/widget/GuidePopupView;->b(Lcom/miui/internal/widget/GuidePopupView;)Landroid/animation/ObjectAnimator;

    move-result-object v0

    invoke-virtual {v0}, Landroid/animation/ObjectAnimator;->start()V

    .line 495
    return v6
.end method
