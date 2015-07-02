.class public Lcom/miui/internal/widget/ActionBarOverlayLayout$ContentMaskAnimator;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroid/animation/Animator$AnimatorListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/internal/widget/ActionBarOverlayLayout;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x1
    name = "ContentMaskAnimator"
.end annotation


# instance fields
.field private TU:Landroid/animation/ObjectAnimator;

.field private TX:Landroid/animation/ObjectAnimator;

.field private ba:Landroid/view/View$OnClickListener;

.field final synthetic iN:Lcom/miui/internal/widget/ActionBarOverlayLayout;


# direct methods
.method private constructor <init>(Lcom/miui/internal/widget/ActionBarOverlayLayout;Landroid/view/View$OnClickListener;)V
    .locals 4

    .prologue
    const/4 v3, 0x2

    .line 685
    iput-object p1, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout$ContentMaskAnimator;->iN:Lcom/miui/internal/widget/ActionBarOverlayLayout;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 686
    iput-object p2, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout$ContentMaskAnimator;->ba:Landroid/view/View$OnClickListener;

    .line 688
    invoke-static {p1}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->a(Lcom/miui/internal/widget/ActionBarOverlayLayout;)Landroid/view/View;

    move-result-object v0

    const-string v1, "alpha"

    new-array v2, v3, [F

    fill-array-data v2, :array_0

    invoke-static {v0, v1, v2}, Landroid/animation/ObjectAnimator;->ofFloat(Ljava/lang/Object;Ljava/lang/String;[F)Landroid/animation/ObjectAnimator;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout$ContentMaskAnimator;->TU:Landroid/animation/ObjectAnimator;

    .line 689
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout$ContentMaskAnimator;->TU:Landroid/animation/ObjectAnimator;

    invoke-virtual {v0, p0}, Landroid/animation/ObjectAnimator;->addListener(Landroid/animation/Animator$AnimatorListener;)V

    .line 691
    invoke-static {p1}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->a(Lcom/miui/internal/widget/ActionBarOverlayLayout;)Landroid/view/View;

    move-result-object v0

    const-string v1, "alpha"

    new-array v2, v3, [F

    fill-array-data v2, :array_1

    invoke-static {v0, v1, v2}, Landroid/animation/ObjectAnimator;->ofFloat(Ljava/lang/Object;Ljava/lang/String;[F)Landroid/animation/ObjectAnimator;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout$ContentMaskAnimator;->TX:Landroid/animation/ObjectAnimator;

    .line 692
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout$ContentMaskAnimator;->TX:Landroid/animation/ObjectAnimator;

    invoke-virtual {v0, p0}, Landroid/animation/ObjectAnimator;->addListener(Landroid/animation/Animator$AnimatorListener;)V

    .line 693
    return-void

    .line 688
    nop

    :array_0
    .array-data 4
        0x0
        0x3f800000
    .end array-data

    .line 691
    :array_1
    .array-data 4
        0x3f800000
        0x0
    .end array-data
.end method

.method synthetic constructor <init>(Lcom/miui/internal/widget/ActionBarOverlayLayout;Landroid/view/View$OnClickListener;Lcom/miui/internal/widget/ActionBarOverlayLayout$1;)V
    .locals 0

    .prologue
    .line 677
    invoke-direct {p0, p1, p2}, Lcom/miui/internal/widget/ActionBarOverlayLayout$ContentMaskAnimator;-><init>(Lcom/miui/internal/widget/ActionBarOverlayLayout;Landroid/view/View$OnClickListener;)V

    return-void
.end method


# virtual methods
.method public hide()Landroid/animation/Animator;
    .locals 1

    .prologue
    .line 700
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout$ContentMaskAnimator;->TX:Landroid/animation/ObjectAnimator;

    return-object v0
.end method

.method public onAnimationCancel(Landroid/animation/Animator;)V
    .locals 2

    .prologue
    .line 724
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout$ContentMaskAnimator;->TX:Landroid/animation/ObjectAnimator;

    if-ne p1, v0, :cond_0

    .line 725
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout$ContentMaskAnimator;->iN:Lcom/miui/internal/widget/ActionBarOverlayLayout;

    invoke-static {v0}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->b(Lcom/miui/internal/widget/ActionBarOverlayLayout;)Lcom/miui/internal/widget/ActionBarContainer;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/internal/widget/ActionBarContainer;->bringToFront()V

    .line 726
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout$ContentMaskAnimator;->iN:Lcom/miui/internal/widget/ActionBarOverlayLayout;

    invoke-static {v0}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->a(Lcom/miui/internal/widget/ActionBarOverlayLayout;)Landroid/view/View;

    move-result-object v0

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Landroid/view/View;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 728
    :cond_0
    return-void
.end method

.method public onAnimationEnd(Landroid/animation/Animator;)V
    .locals 2

    .prologue
    .line 715
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout$ContentMaskAnimator;->iN:Lcom/miui/internal/widget/ActionBarOverlayLayout;

    invoke-static {v0}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->a(Lcom/miui/internal/widget/ActionBarOverlayLayout;)Landroid/view/View;

    move-result-object v0

    invoke-virtual {v0}, Landroid/view/View;->getAlpha()F

    move-result v0

    const/4 v1, 0x0

    cmpl-float v0, v0, v1

    if-nez v0, :cond_0

    .line 716
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout$ContentMaskAnimator;->iN:Lcom/miui/internal/widget/ActionBarOverlayLayout;

    invoke-static {v0}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->b(Lcom/miui/internal/widget/ActionBarOverlayLayout;)Lcom/miui/internal/widget/ActionBarContainer;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/internal/widget/ActionBarContainer;->bringToFront()V

    .line 717
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout$ContentMaskAnimator;->iN:Lcom/miui/internal/widget/ActionBarOverlayLayout;

    invoke-static {v0}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->a(Lcom/miui/internal/widget/ActionBarOverlayLayout;)Landroid/view/View;

    move-result-object v0

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Landroid/view/View;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 718
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout$ContentMaskAnimator;->iN:Lcom/miui/internal/widget/ActionBarOverlayLayout;

    invoke-static {v0}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->a(Lcom/miui/internal/widget/ActionBarOverlayLayout;)Landroid/view/View;

    move-result-object v0

    const/16 v1, 0x8

    invoke-virtual {v0, v1}, Landroid/view/View;->setVisibility(I)V

    .line 720
    :cond_0
    return-void
.end method

.method public onAnimationRepeat(Landroid/animation/Animator;)V
    .locals 0

    .prologue
    .line 732
    return-void
.end method

.method public onAnimationStart(Landroid/animation/Animator;)V
    .locals 2

    .prologue
    .line 705
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout$ContentMaskAnimator;->TU:Landroid/animation/ObjectAnimator;

    if-ne p1, v0, :cond_0

    .line 706
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout$ContentMaskAnimator;->iN:Lcom/miui/internal/widget/ActionBarOverlayLayout;

    invoke-static {v0}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->a(Lcom/miui/internal/widget/ActionBarOverlayLayout;)Landroid/view/View;

    move-result-object v0

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Landroid/view/View;->setVisibility(I)V

    .line 707
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout$ContentMaskAnimator;->iN:Lcom/miui/internal/widget/ActionBarOverlayLayout;

    invoke-static {v0}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->a(Lcom/miui/internal/widget/ActionBarOverlayLayout;)Landroid/view/View;

    move-result-object v0

    invoke-virtual {v0}, Landroid/view/View;->bringToFront()V

    .line 708
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout$ContentMaskAnimator;->iN:Lcom/miui/internal/widget/ActionBarOverlayLayout;

    invoke-static {v0}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->b(Lcom/miui/internal/widget/ActionBarOverlayLayout;)Lcom/miui/internal/widget/ActionBarContainer;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/internal/widget/ActionBarContainer;->bringToFront()V

    .line 709
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout$ContentMaskAnimator;->iN:Lcom/miui/internal/widget/ActionBarOverlayLayout;

    invoke-static {v0}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->a(Lcom/miui/internal/widget/ActionBarOverlayLayout;)Landroid/view/View;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout$ContentMaskAnimator;->ba:Landroid/view/View$OnClickListener;

    invoke-virtual {v0, v1}, Landroid/view/View;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 711
    :cond_0
    return-void
.end method

.method public show()Landroid/animation/Animator;
    .locals 1

    .prologue
    .line 696
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout$ContentMaskAnimator;->TU:Landroid/animation/ObjectAnimator;

    return-object v0
.end method
