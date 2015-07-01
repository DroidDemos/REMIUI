.class public Lcom/miui/internal/widget/ActionBarMovableLayout;
.super Lcom/miui/internal/widget/ActionBarOverlayLayout;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/internal/widget/ActionBarMovableLayout$OnScrollListener;
    }
.end annotation


# static fields
.field public static final DEFAULT_SPRING_BACK_DURATION:I = 0x320

.field private static final Gf:Z = false

.field public static final STATE_DOWN:I = 0x1

.field public static final STATE_UNKNOWN:I = -0x1

.field public static final STATE_UP:I

.field private static final TAG:Ljava/lang/String;


# instance fields
.field private CJ:Z

.field private final Da:I

.field private Gg:Landroid/view/View;

.field private Gh:Landroid/widget/OverScroller;

.field private Gi:I

.field private Gj:I

.field private Gk:I

.field private Gl:I

.field private Gm:I

.field private Gn:I

.field private Go:Z

.field private Gp:Z

.field private Gq:Z

.field private Gr:Lcom/miui/internal/widget/ActionBarMovableLayout$OnScrollListener;

.field private bR:Landroid/view/VelocityTracker;

.field private final cT:I

.field private final cU:I

.field private cV:I

.field private mLastMotionX:F

.field private mLastMotionY:F

.field private vP:I


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .prologue
    .line 20
    const-class v0, Lcom/miui/internal/widget/ActionBarMovableLayout;

    invoke-virtual {v0}, Ljava/lang/Class;->getSimpleName()Ljava/lang/String;

    move-result-object v0

    sput-object v0, Lcom/miui/internal/widget/ActionBarMovableLayout;->TAG:Ljava/lang/String;

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 5

    .prologue
    const/4 v4, 0x1

    const/4 v3, 0x0

    const/4 v2, -0x1

    .line 88
    invoke-direct {p0, p1, p2}, Lcom/miui/internal/widget/ActionBarOverlayLayout;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    .line 46
    iput v2, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->vP:I

    .line 54
    iput v2, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Gj:I

    .line 58
    iput v2, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Gl:I

    .line 62
    const/16 v0, 0x8

    iput v0, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Gn:I

    .line 66
    iput-boolean v4, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Gp:Z

    .line 90
    sget-object v0, Lcom/miui/internal/R$styleable;->ActionBarMovableLayout:[I

    sget v1, Lcom/miui/internal/R$attr;->actionBarMovableLayoutStyle:I

    invoke-virtual {p1, p2, v0, v1, v3}, Landroid/content/Context;->obtainStyledAttributes(Landroid/util/AttributeSet;[III)Landroid/content/res/TypedArray;

    move-result-object v0

    .line 93
    invoke-virtual {v0, v3, v3}, Landroid/content/res/TypedArray;->getDimensionPixelSize(II)I

    move-result v1

    iput v1, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Gk:I

    .line 95
    invoke-virtual {v0, v4, v2}, Landroid/content/res/TypedArray;->getDimensionPixelSize(II)I

    move-result v1

    iput v1, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Gj:I

    .line 96
    const/4 v1, 0x2

    invoke-virtual {v0, v1, v2}, Landroid/content/res/TypedArray;->getDimensionPixelSize(II)I

    move-result v1

    iput v1, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Gl:I

    .line 98
    invoke-static {p1}, Landroid/view/ViewConfiguration;->get(Landroid/content/Context;)Landroid/view/ViewConfiguration;

    move-result-object v1

    .line 99
    invoke-virtual {v1}, Landroid/view/ViewConfiguration;->getScaledTouchSlop()I

    move-result v2

    iput v2, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->cT:I

    .line 101
    new-instance v2, Landroid/widget/OverScroller;

    invoke-direct {v2, p1}, Landroid/widget/OverScroller;-><init>(Landroid/content/Context;)V

    iput-object v2, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Gh:Landroid/widget/OverScroller;

    .line 102
    invoke-virtual {v1}, Landroid/view/ViewConfiguration;->getScaledMinimumFlingVelocity()I

    move-result v2

    iput v2, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Da:I

    .line 103
    invoke-virtual {v1}, Landroid/view/ViewConfiguration;->getScaledMaximumFlingVelocity()I

    move-result v1

    iput v1, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->cU:I

    .line 105
    invoke-virtual {p0, v3}, Lcom/miui/internal/widget/ActionBarMovableLayout;->setOverScrollMode(I)V

    .line 107
    invoke-virtual {v0}, Landroid/content/res/TypedArray;->recycle()V

    .line 108
    return-void
.end method

.method private b(Landroid/view/View;II)Z
    .locals 6

    .prologue
    const/4 v0, 0x0

    .line 390
    if-nez p1, :cond_1

    .line 405
    :cond_0
    :goto_0
    return v0

    .line 394
    :cond_1
    invoke-virtual {p1}, Landroid/view/View;->getY()F

    move-result v1

    float-to-int v2, v1

    .line 395
    invoke-virtual {p1}, Landroid/view/View;->getX()F

    move-result v1

    float-to-int v3, v1

    .line 396
    invoke-virtual {p1}, Landroid/view/View;->getY()F

    move-result v1

    invoke-virtual {p1}, Landroid/view/View;->getHeight()I

    move-result v4

    int-to-float v4, v4

    add-float/2addr v1, v4

    float-to-int v1, v1

    .line 397
    invoke-virtual {p1}, Landroid/view/View;->getX()F

    move-result v4

    invoke-virtual {p1}, Landroid/view/View;->getWidth()I

    move-result v5

    int-to-float v5, v5

    add-float/2addr v4, v5

    float-to-int v4, v4

    .line 399
    iget-object v5, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Gg:Landroid/view/View;

    if-ne p1, v5, :cond_2

    .line 400
    iget-object v5, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->mActionBarTop:Lcom/miui/internal/widget/ActionBarContainer;

    invoke-virtual {v5}, Lcom/miui/internal/widget/ActionBarContainer;->getTop()I

    move-result v5

    .line 401
    add-int/2addr v2, v5

    .line 402
    add-int/2addr v1, v5

    .line 405
    :cond_2
    if-lt p3, v2, :cond_0

    if-ge p3, v1, :cond_0

    if-lt p2, v3, :cond_0

    if-ge p2, v4, :cond_0

    const/4 v0, 0x1

    goto :goto_0
.end method

.method private d(Landroid/view/MotionEvent;)V
    .locals 3

    .prologue
    .line 626
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getAction()I

    move-result v0

    const v1, 0xff00

    and-int/2addr v0, v1

    shr-int/lit8 v0, v0, 0x8

    .line 628
    invoke-virtual {p1, v0}, Landroid/view/MotionEvent;->getPointerId(I)I

    move-result v1

    .line 629
    iget v2, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->cV:I

    if-ne v1, v2, :cond_0

    .line 630
    if-nez v0, :cond_1

    const/4 v0, 0x1

    .line 631
    :goto_0
    invoke-virtual {p1, v0}, Landroid/view/MotionEvent;->getY(I)F

    move-result v1

    float-to-int v1, v1

    int-to-float v1, v1

    iput v1, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->mLastMotionY:F

    .line 632
    invoke-virtual {p1, v0}, Landroid/view/MotionEvent;->getPointerId(I)I

    move-result v0

    iput v0, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->cV:I

    .line 633
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->bR:Landroid/view/VelocityTracker;

    if-eqz v0, :cond_0

    .line 634
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->bR:Landroid/view/VelocityTracker;

    invoke-virtual {v0}, Landroid/view/VelocityTracker;->clear()V

    .line 637
    :cond_0
    return-void

    .line 630
    :cond_1
    const/4 v0, 0x0

    goto :goto_0
.end method

.method private du()V
    .locals 1

    .prologue
    .line 409
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->bR:Landroid/view/VelocityTracker;

    if-nez v0, :cond_0

    .line 410
    invoke-static {}, Landroid/view/VelocityTracker;->obtain()Landroid/view/VelocityTracker;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->bR:Landroid/view/VelocityTracker;

    .line 414
    :goto_0
    return-void

    .line 412
    :cond_0
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->bR:Landroid/view/VelocityTracker;

    invoke-virtual {v0}, Landroid/view/VelocityTracker;->clear()V

    goto :goto_0
.end method

.method private dv()V
    .locals 1

    .prologue
    .line 417
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->bR:Landroid/view/VelocityTracker;

    if-nez v0, :cond_0

    .line 418
    invoke-static {}, Landroid/view/VelocityTracker;->obtain()Landroid/view/VelocityTracker;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->bR:Landroid/view/VelocityTracker;

    .line 420
    :cond_0
    return-void
.end method

.method private dw()V
    .locals 1

    .prologue
    .line 423
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->bR:Landroid/view/VelocityTracker;

    if-eqz v0, :cond_0

    .line 424
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->bR:Landroid/view/VelocityTracker;

    invoke-virtual {v0}, Landroid/view/VelocityTracker;->recycle()V

    .line 425
    const/4 v0, 0x0

    iput-object v0, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->bR:Landroid/view/VelocityTracker;

    .line 427
    :cond_0
    return-void
.end method

.method private dy()Z
    .locals 3

    .prologue
    .line 640
    const/4 v0, 0x0

    .line 641
    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarMovableLayout;->dx()V

    .line 642
    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Gg:Landroid/view/View;

    if-eqz v1, :cond_0

    .line 643
    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Gg:Landroid/view/View;

    invoke-virtual {v1}, Landroid/view/View;->getVisibility()I

    move-result v1

    .line 644
    iget v2, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Gn:I

    if-eq v1, v2, :cond_0

    .line 645
    iput v1, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Gn:I

    .line 646
    const/4 v0, 0x1

    .line 650
    :cond_0
    return v0
.end method


# virtual methods
.method protected applyTranslationY(F)V
    .locals 2

    .prologue
    .line 607
    invoke-virtual {p0, p1}, Lcom/miui/internal/widget/ActionBarMovableLayout;->motionToTranslation(F)F

    move-result v0

    .line 608
    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->mContentView:Landroid/view/View;

    invoke-virtual {v1, v0}, Landroid/view/View;->setTranslationY(F)V

    .line 610
    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarMovableLayout;->dx()V

    .line 611
    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Gg:Landroid/view/View;

    if-eqz v1, :cond_0

    .line 612
    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Gg:Landroid/view/View;

    invoke-virtual {v1, v0}, Landroid/view/View;->setTranslationY(F)V

    .line 614
    :cond_0
    return-void
.end method

.method public computeScroll()V
    .locals 10

    .prologue
    const/4 v1, 0x0

    .line 278
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Gh:Landroid/widget/OverScroller;

    invoke-virtual {v0}, Landroid/widget/OverScroller;->computeScrollOffset()Z

    move-result v0

    if-eqz v0, :cond_2

    .line 279
    iget v0, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Gi:I

    .line 280
    iget-object v2, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Gh:Landroid/widget/OverScroller;

    invoke-virtual {v2}, Landroid/widget/OverScroller;->getCurrY()I

    move-result v2

    .line 282
    if-eq v0, v2, :cond_0

    .line 283
    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarMovableLayout;->getScrollRange()I

    move-result v6

    .line 284
    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarMovableLayout;->getOverScrollDistance()I

    move-result v8

    .line 285
    sub-int/2addr v2, v0

    iget v4, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Gi:I

    const/4 v9, 0x1

    move-object v0, p0

    move v3, v1

    move v5, v1

    move v7, v1

    invoke-virtual/range {v0 .. v9}, Lcom/miui/internal/widget/ActionBarMovableLayout;->overScrollBy(IIIIIIIIZ)Z

    .line 288
    :cond_0
    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarMovableLayout;->postInvalidateOnAnimation()V

    .line 295
    :cond_1
    :goto_0
    return-void

    .line 290
    :cond_2
    iget-boolean v0, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Gq:Z

    if-eqz v0, :cond_1

    .line 291
    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarMovableLayout;->springBack()V

    .line 292
    iput-boolean v1, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Gq:Z

    goto :goto_0
.end method

.method protected computeVerticalScrollExtent()I
    .locals 1

    .prologue
    .line 479
    const/4 v0, 0x0

    return v0
.end method

.method protected computeVerticalScrollRange()I
    .locals 1

    .prologue
    .line 484
    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarMovableLayout;->getScrollRange()I

    move-result v0

    return v0
.end method

.method protected computeVerticalVelocity()I
    .locals 3

    .prologue
    .line 493
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->bR:Landroid/view/VelocityTracker;

    .line 494
    const/16 v1, 0x3e8

    iget v2, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->cU:I

    int-to-float v2, v2

    invoke-virtual {v0, v1, v2}, Landroid/view/VelocityTracker;->computeCurrentVelocity(IF)V

    .line 495
    iget v1, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->cV:I

    invoke-virtual {v0, v1}, Landroid/view/VelocityTracker;->getYVelocity(I)F

    move-result v0

    float-to-int v0, v0

    return v0
.end method

.method dx()V
    .locals 1

    .prologue
    .line 431
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->mActionBarTop:Lcom/miui/internal/widget/ActionBarContainer;

    invoke-virtual {v0}, Lcom/miui/internal/widget/ActionBarContainer;->getTabContainer()Landroid/view/View;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Gg:Landroid/view/View;

    .line 432
    return-void
.end method

.method protected fling(I)V
    .locals 11

    .prologue
    const/4 v1, 0x0

    .line 503
    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarMovableLayout;->getOverScrollDistance()I

    move-result v10

    .line 504
    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarMovableLayout;->getScrollRange()I

    move-result v8

    .line 505
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Gh:Landroid/widget/OverScroller;

    iget v2, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Gi:I

    move v3, v1

    move v4, p1

    move v5, v1

    move v6, v1

    move v7, v1

    move v9, v1

    invoke-virtual/range {v0 .. v10}, Landroid/widget/OverScroller;->fling(IIIIIIIIII)V

    .line 506
    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Gq:Z

    .line 508
    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarMovableLayout;->postInvalidate()V

    .line 509
    return-void
.end method

.method public getOverScrollDistance()I
    .locals 1

    .prologue
    .line 362
    iget v0, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Gk:I

    return v0
.end method

.method public getScrollRange()I
    .locals 1

    .prologue
    .line 370
    iget v0, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Gj:I

    return v0
.end method

.method public getScrollStart()I
    .locals 1

    .prologue
    .line 382
    iget v0, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Gm:I

    return v0
.end method

.method protected measureChildWithMargins(Landroid/view/View;IIII)V
    .locals 5

    .prologue
    .line 437
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->mContentView:Landroid/view/View;

    if-eq p1, v0, :cond_0

    .line 438
    invoke-super/range {p0 .. p5}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->measureChildWithMargins(Landroid/view/View;IIII)V

    .line 453
    :goto_0
    return-void

    .line 443
    :cond_0
    invoke-virtual {p1}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v0

    check-cast v0, Landroid/view/ViewGroup$MarginLayoutParams;

    .line 444
    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->mActionBarView:Lcom/miui/internal/widget/ActionBarView;

    invoke-virtual {v1}, Lcom/miui/internal/widget/ActionBarView;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v1

    check-cast v1, Landroid/view/ViewGroup$MarginLayoutParams;

    .line 446
    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarMovableLayout;->getPaddingLeft()I

    move-result v2

    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarMovableLayout;->getPaddingRight()I

    move-result v3

    add-int/2addr v2, v3

    iget v3, v0, Landroid/view/ViewGroup$MarginLayoutParams;->leftMargin:I

    add-int/2addr v2, v3

    iget v3, v0, Landroid/view/ViewGroup$MarginLayoutParams;->rightMargin:I

    add-int/2addr v2, v3

    add-int/2addr v2, p3

    iget v3, v0, Landroid/view/ViewGroup$MarginLayoutParams;->width:I

    invoke-static {p2, v2, v3}, Lcom/miui/internal/widget/ActionBarMovableLayout;->getChildMeasureSpec(III)I

    move-result v2

    .line 448
    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarMovableLayout;->getPaddingTop()I

    move-result v3

    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarMovableLayout;->getPaddingBottom()I

    move-result v4

    add-int/2addr v3, v4

    iget v4, v0, Landroid/view/ViewGroup$MarginLayoutParams;->bottomMargin:I

    add-int/2addr v3, v4

    iget-object v4, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->mActionBarView:Lcom/miui/internal/widget/ActionBarView;

    invoke-virtual {v4}, Lcom/miui/internal/widget/ActionBarView;->getMeasuredHeight()I

    move-result v4

    add-int/2addr v3, v4

    iget v1, v1, Landroid/view/ViewGroup$MarginLayoutParams;->topMargin:I

    add-int/2addr v1, v3

    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarMovableLayout;->getScrollRange()I

    move-result v3

    sub-int/2addr v1, v3

    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarMovableLayout;->getOverScrollDistance()I

    move-result v3

    sub-int/2addr v1, v3

    iget v3, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Gm:I

    sub-int/2addr v1, v3

    iget v0, v0, Landroid/view/ViewGroup$MarginLayoutParams;->height:I

    invoke-static {p4, v1, v0}, Lcom/miui/internal/widget/ActionBarMovableLayout;->getChildMeasureSpec(III)I

    move-result v0

    .line 452
    invoke-virtual {p1, v2, v0}, Landroid/view/View;->measure(II)V

    goto :goto_0
.end method

.method protected motionToTranslation(F)F
    .locals 2

    .prologue
    .line 596
    iget v0, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Gk:I

    neg-int v0, v0

    int-to-float v0, v0

    add-float/2addr v0, p1

    iget v1, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Gj:I

    int-to-float v1, v1

    sub-float/2addr v0, v1

    iget v1, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Gm:I

    int-to-float v1, v1

    sub-float/2addr v0, v1

    .line 598
    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarMovableLayout;->dx()V

    .line 599
    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Gg:Landroid/view/View;

    if-eqz v1, :cond_0

    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Gg:Landroid/view/View;

    invoke-virtual {v1}, Landroid/view/View;->getVisibility()I

    move-result v1

    if-nez v1, :cond_0

    .line 600
    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Gg:Landroid/view/View;

    invoke-virtual {v1}, Landroid/view/View;->getHeight()I

    move-result v1

    int-to-float v1, v1

    sub-float/2addr v0, v1

    .line 603
    :cond_0
    return v0
.end method

.method public onInterceptTouchEvent(Landroid/view/MotionEvent;)Z
    .locals 4

    .prologue
    const/4 v1, 0x1

    const/4 v0, 0x0

    .line 113
    sget v2, Lcom/miui/internal/R$id;->content_mask:I

    invoke-virtual {p0, v2}, Lcom/miui/internal/widget/ActionBarMovableLayout;->findViewById(I)Landroid/view/View;

    move-result-object v2

    .line 114
    if-eqz v2, :cond_0

    invoke-virtual {v2}, Landroid/view/View;->getVisibility()I

    move-result v2

    if-nez v2, :cond_0

    .line 169
    :goto_0
    return v0

    .line 118
    :cond_0
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getAction()I

    move-result v2

    .line 120
    const/4 v3, 0x2

    if-ne v2, v3, :cond_1

    iget-boolean v3, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->CJ:Z

    if-eqz v3, :cond_1

    move v0, v1

    .line 121
    goto :goto_0

    .line 124
    :cond_1
    and-int/lit16 v2, v2, 0xff

    packed-switch v2, :pswitch_data_0

    .line 169
    :cond_2
    :goto_1
    :pswitch_0
    iget-boolean v0, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->CJ:Z

    goto :goto_0

    .line 126
    :pswitch_1
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getY()F

    move-result v2

    iput v2, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->mLastMotionY:F

    .line 127
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getX()F

    move-result v2

    iput v2, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->mLastMotionX:F

    .line 128
    invoke-virtual {p1, v0}, Landroid/view/MotionEvent;->getPointerId(I)I

    move-result v0

    iput v0, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->cV:I

    .line 130
    invoke-direct {p0}, Lcom/miui/internal/widget/ActionBarMovableLayout;->du()V

    .line 131
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->bR:Landroid/view/VelocityTracker;

    invoke-virtual {v0, p1}, Landroid/view/VelocityTracker;->addMovement(Landroid/view/MotionEvent;)V

    .line 132
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Gh:Landroid/widget/OverScroller;

    invoke-virtual {v0, v1}, Landroid/widget/OverScroller;->forceFinished(Z)V

    goto :goto_1

    .line 141
    :pswitch_2
    invoke-virtual {p0, p1}, Lcom/miui/internal/widget/ActionBarMovableLayout;->shouldStartScroll(Landroid/view/MotionEvent;)Z

    move-result v0

    if-eqz v0, :cond_2

    .line 142
    iput-boolean v1, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->CJ:Z

    .line 143
    invoke-direct {p0}, Lcom/miui/internal/widget/ActionBarMovableLayout;->dv()V

    .line 144
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->bR:Landroid/view/VelocityTracker;

    invoke-virtual {v0, p1}, Landroid/view/VelocityTracker;->addMovement(Landroid/view/MotionEvent;)V

    .line 145
    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarMovableLayout;->onScrollBegin()V

    goto :goto_1

    .line 156
    :pswitch_3
    iput-boolean v0, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->CJ:Z

    .line 157
    const/4 v0, -0x1

    iput v0, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->cV:I

    .line 158
    invoke-direct {p0}, Lcom/miui/internal/widget/ActionBarMovableLayout;->dw()V

    .line 159
    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarMovableLayout;->onScrollEnd()V

    goto :goto_1

    .line 164
    :pswitch_4
    invoke-direct {p0, p1}, Lcom/miui/internal/widget/ActionBarMovableLayout;->d(Landroid/view/MotionEvent;)V

    goto :goto_1

    .line 124
    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_1
        :pswitch_3
        :pswitch_2
        :pswitch_3
        :pswitch_0
        :pswitch_0
        :pswitch_4
    .end packed-switch
.end method

.method protected onLayout(ZIIII)V
    .locals 3

    .prologue
    const/4 v1, 0x1

    .line 457
    invoke-super/range {p0 .. p5}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->onLayout(ZIIII)V

    .line 462
    iget-boolean v0, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Go:Z

    if-eqz v0, :cond_0

    invoke-direct {p0}, Lcom/miui/internal/widget/ActionBarMovableLayout;->dy()Z

    move-result v0

    if-eqz v0, :cond_4

    :cond_0
    move v0, v1

    .line 463
    :goto_0
    iget-boolean v2, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Go:Z

    if-nez v2, :cond_2

    .line 464
    iget v2, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Gl:I

    if-gez v2, :cond_1

    .line 465
    iget v2, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Gj:I

    iput v2, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Gl:I

    .line 468
    :cond_1
    iget v2, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Gl:I

    iput v2, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Gi:I

    .line 469
    iput-boolean v1, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Go:Z

    .line 472
    :cond_2
    if-eqz v0, :cond_3

    .line 473
    iget v0, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Gi:I

    int-to-float v0, v0

    invoke-virtual {p0, v0}, Lcom/miui/internal/widget/ActionBarMovableLayout;->applyTranslationY(F)V

    .line 475
    :cond_3
    return-void

    .line 462
    :cond_4
    const/4 v0, 0x0

    goto :goto_0
.end method

.method protected onOverScrolled(IIZZ)V
    .locals 3

    .prologue
    .line 334
    int-to-float v0, p2

    invoke-virtual {p0, v0}, Lcom/miui/internal/widget/ActionBarMovableLayout;->onScroll(F)V

    .line 336
    iput p2, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Gi:I

    .line 342
    iget v0, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Gi:I

    if-nez v0, :cond_0

    if-eqz p4, :cond_0

    .line 343
    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarMovableLayout;->computeVerticalVelocity()I

    move-result v0

    .line 345
    invoke-static {v0}, Ljava/lang/Math;->abs(I)I

    move-result v1

    iget v2, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Da:I

    mul-int/lit8 v2, v2, 0x2

    if-le v1, v2, :cond_0

    .line 346
    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Gr:Lcom/miui/internal/widget/ActionBarMovableLayout$OnScrollListener;

    if-eqz v1, :cond_0

    .line 347
    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Gr:Lcom/miui/internal/widget/ActionBarMovableLayout$OnScrollListener;

    neg-int v0, v0

    int-to-float v0, v0

    const v2, 0x3e4ccccd

    mul-float/2addr v0, v2

    const/16 v2, 0x1f4

    invoke-interface {v1, v0, v2}, Lcom/miui/internal/widget/ActionBarMovableLayout$OnScrollListener;->onFling(FI)V

    .line 351
    :cond_0
    return-void
.end method

.method protected onScroll(F)V
    .locals 3

    .prologue
    .line 580
    invoke-virtual {p0, p1}, Lcom/miui/internal/widget/ActionBarMovableLayout;->applyTranslationY(F)V

    .line 582
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Gr:Lcom/miui/internal/widget/ActionBarMovableLayout$OnScrollListener;

    if-eqz v0, :cond_0

    .line 583
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Gr:Lcom/miui/internal/widget/ActionBarMovableLayout$OnScrollListener;

    iget v1, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->vP:I

    iget v2, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Gj:I

    int-to-float v2, v2

    div-float v2, p1, v2

    invoke-interface {v0, v1, v2}, Lcom/miui/internal/widget/ActionBarMovableLayout$OnScrollListener;->onScroll(IF)V

    .line 585
    :cond_0
    return-void
.end method

.method protected onScrollBegin()V
    .locals 1

    .prologue
    .line 574
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Gr:Lcom/miui/internal/widget/ActionBarMovableLayout$OnScrollListener;

    if-eqz v0, :cond_0

    .line 575
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Gr:Lcom/miui/internal/widget/ActionBarMovableLayout$OnScrollListener;

    invoke-interface {v0}, Lcom/miui/internal/widget/ActionBarMovableLayout$OnScrollListener;->onStartScroll()V

    .line 577
    :cond_0
    return-void
.end method

.method protected onScrollEnd()V
    .locals 1

    .prologue
    .line 588
    const/4 v0, -0x1

    iput v0, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->vP:I

    .line 590
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Gr:Lcom/miui/internal/widget/ActionBarMovableLayout$OnScrollListener;

    if-eqz v0, :cond_0

    .line 591
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Gr:Lcom/miui/internal/widget/ActionBarMovableLayout$OnScrollListener;

    invoke-interface {v0}, Lcom/miui/internal/widget/ActionBarMovableLayout$OnScrollListener;->onStopScroll()V

    .line 593
    :cond_0
    return-void
.end method

.method public onTouchEvent(Landroid/view/MotionEvent;)Z
    .locals 12

    .prologue
    const/4 v9, 0x1

    const/4 v11, -0x1

    const/4 v1, 0x0

    .line 174
    invoke-direct {p0}, Lcom/miui/internal/widget/ActionBarMovableLayout;->dv()V

    .line 175
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->bR:Landroid/view/VelocityTracker;

    invoke-virtual {v0, p1}, Landroid/view/VelocityTracker;->addMovement(Landroid/view/MotionEvent;)V

    .line 177
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getAction()I

    move-result v0

    .line 178
    and-int/lit16 v0, v0, 0xff

    packed-switch v0, :pswitch_data_0

    :cond_0
    :goto_0
    :pswitch_0
    move v1, v9

    .line 273
    :cond_1
    return v1

    .line 184
    :pswitch_1
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getY()F

    move-result v0

    iput v0, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->mLastMotionY:F

    .line 185
    invoke-virtual {p1, v1}, Landroid/view/MotionEvent;->getPointerId(I)I

    move-result v0

    iput v0, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->cV:I

    goto :goto_0

    .line 190
    :pswitch_2
    iget-boolean v0, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->CJ:Z

    if-eqz v0, :cond_3

    .line 191
    iget v0, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->cV:I

    invoke-virtual {p1, v0}, Landroid/view/MotionEvent;->findPointerIndex(I)I

    move-result v0

    .line 192
    if-eq v0, v11, :cond_1

    .line 195
    invoke-virtual {p1, v0}, Landroid/view/MotionEvent;->getY(I)F

    move-result v10

    .line 201
    iget v0, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->mLastMotionY:F

    .line 202
    sub-float v0, v10, v0

    float-to-int v2, v0

    .line 203
    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarMovableLayout;->getScrollRange()I

    move-result v6

    .line 204
    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarMovableLayout;->getOverScrollDistance()I

    move-result v8

    .line 205
    iget v4, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Gi:I

    move-object v0, p0

    move v3, v1

    move v5, v1

    move v7, v1

    invoke-virtual/range {v0 .. v9}, Lcom/miui/internal/widget/ActionBarMovableLayout;->overScrollBy(IIIIIIIIZ)Z

    move-result v0

    .line 207
    iput v10, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->mLastMotionY:F

    .line 209
    if-eqz v0, :cond_0

    .line 210
    iget v0, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Gi:I

    if-nez v0, :cond_2

    .line 211
    iput-boolean v1, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->CJ:Z

    .line 212
    iput v11, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->cV:I

    .line 214
    invoke-virtual {p1, v1}, Landroid/view/MotionEvent;->setAction(I)V

    .line 215
    invoke-virtual {p0, p1}, Lcom/miui/internal/widget/ActionBarMovableLayout;->dispatchTouchEvent(Landroid/view/MotionEvent;)Z

    .line 217
    :cond_2
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->bR:Landroid/view/VelocityTracker;

    invoke-virtual {v0}, Landroid/view/VelocityTracker;->clear()V

    goto :goto_0

    .line 220
    :cond_3
    invoke-virtual {p0, p1}, Lcom/miui/internal/widget/ActionBarMovableLayout;->shouldStartScroll(Landroid/view/MotionEvent;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 221
    iput-boolean v9, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->CJ:Z

    .line 222
    invoke-direct {p0}, Lcom/miui/internal/widget/ActionBarMovableLayout;->dv()V

    .line 223
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->bR:Landroid/view/VelocityTracker;

    invoke-virtual {v0, p1}, Landroid/view/VelocityTracker;->addMovement(Landroid/view/MotionEvent;)V

    .line 224
    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarMovableLayout;->onScrollBegin()V

    goto :goto_0

    .line 232
    :pswitch_3
    iget-boolean v0, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->CJ:Z

    if-eqz v0, :cond_0

    .line 233
    iput-boolean v1, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->CJ:Z

    .line 234
    iput v11, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->cV:I

    .line 236
    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarMovableLayout;->computeVerticalVelocity()I

    move-result v0

    .line 242
    invoke-static {v0}, Ljava/lang/Math;->abs(I)I

    move-result v2

    iget v3, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Da:I

    if-le v2, v3, :cond_4

    .line 243
    invoke-virtual {p0, v0}, Lcom/miui/internal/widget/ActionBarMovableLayout;->fling(I)V

    goto :goto_0

    .line 245
    :cond_4
    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarMovableLayout;->getScrollRange()I

    move-result v6

    .line 246
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Gh:Landroid/widget/OverScroller;

    iget v2, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Gi:I

    move v3, v1

    move v4, v1

    move v5, v1

    invoke-virtual/range {v0 .. v6}, Landroid/widget/OverScroller;->springBack(IIIIII)Z

    move-result v0

    if-eqz v0, :cond_5

    .line 247
    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarMovableLayout;->invalidate()V

    goto/16 :goto_0

    .line 249
    :cond_5
    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarMovableLayout;->springBack()V

    goto/16 :goto_0

    .line 260
    :pswitch_4
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getActionIndex()I

    move-result v0

    .line 261
    invoke-virtual {p1, v0}, Landroid/view/MotionEvent;->getY(I)F

    move-result v1

    float-to-int v1, v1

    int-to-float v1, v1

    iput v1, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->mLastMotionY:F

    .line 262
    invoke-virtual {p1, v0}, Landroid/view/MotionEvent;->getPointerId(I)I

    move-result v0

    iput v0, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->cV:I

    goto/16 :goto_0

    .line 267
    :pswitch_5
    invoke-direct {p0, p1}, Lcom/miui/internal/widget/ActionBarMovableLayout;->d(Landroid/view/MotionEvent;)V

    .line 268
    iget v0, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->cV:I

    invoke-virtual {p1, v0}, Landroid/view/MotionEvent;->findPointerIndex(I)I

    move-result v0

    invoke-virtual {p1, v0}, Landroid/view/MotionEvent;->getY(I)F

    move-result v0

    float-to-int v0, v0

    int-to-float v0, v0

    iput v0, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->mLastMotionY:F

    goto/16 :goto_0

    .line 178
    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_1
        :pswitch_3
        :pswitch_2
        :pswitch_3
        :pswitch_0
        :pswitch_4
        :pswitch_5
    .end packed-switch
.end method

.method protected overScrollBy(IIIIIIIIZ)Z
    .locals 5

    .prologue
    const/4 v0, 0x1

    const/4 v1, 0x0

    .line 303
    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarMovableLayout;->getOverScrollMode()I

    move-result v3

    .line 304
    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarMovableLayout;->computeVerticalScrollRange()I

    move-result v2

    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarMovableLayout;->computeVerticalScrollExtent()I

    move-result v4

    if-le v2, v4, :cond_2

    move v2, v0

    .line 306
    :goto_0
    if-eqz v3, :cond_0

    if-ne v3, v0, :cond_3

    if-eqz v2, :cond_3

    :cond_0
    move v2, v0

    .line 309
    :goto_1
    add-int v3, p4, p2

    .line 310
    if-nez v2, :cond_1

    move p8, v1

    .line 316
    :cond_1
    add-int v2, p8, p6

    .line 319
    if-le v3, v2, :cond_4

    .line 327
    :goto_2
    invoke-virtual {p0, v1, v2, v1, v0}, Lcom/miui/internal/widget/ActionBarMovableLayout;->onOverScrolled(IIZZ)V

    .line 329
    return v0

    :cond_2
    move v2, v1

    .line 304
    goto :goto_0

    :cond_3
    move v2, v1

    .line 306
    goto :goto_1

    .line 322
    :cond_4
    if-gez v3, :cond_5

    move v2, v1

    .line 324
    goto :goto_2

    :cond_5
    move v0, v1

    move v2, v3

    goto :goto_2
.end method

.method public requestDisallowInterceptTouchEvent(Z)V
    .locals 0

    .prologue
    .line 490
    return-void
.end method

.method public setInitialMotionY(I)V
    .locals 0

    .prologue
    .line 354
    iput p1, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Gl:I

    .line 355
    return-void
.end method

.method public setMotionY(I)V
    .locals 1

    .prologue
    .line 654
    iput p1, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Gi:I

    .line 655
    int-to-float v0, p1

    invoke-virtual {p0, v0}, Lcom/miui/internal/widget/ActionBarMovableLayout;->onScroll(F)V

    .line 656
    return-void
.end method

.method public setOnScrollListener(Lcom/miui/internal/widget/ActionBarMovableLayout$OnScrollListener;)V
    .locals 0

    .prologue
    .line 374
    iput-object p1, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Gr:Lcom/miui/internal/widget/ActionBarMovableLayout$OnScrollListener;

    .line 375
    return-void
.end method

.method public setOverScrollDistance(I)V
    .locals 0

    .prologue
    .line 358
    iput p1, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Gk:I

    .line 359
    return-void
.end method

.method public setScrollRange(I)V
    .locals 0

    .prologue
    .line 366
    iput p1, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Gj:I

    .line 367
    return-void
.end method

.method public setScrollStart(I)V
    .locals 0

    .prologue
    .line 378
    iput p1, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Gm:I

    .line 379
    return-void
.end method

.method public setSpringBackEnabled(Z)V
    .locals 0

    .prologue
    .line 386
    iput-boolean p1, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Gp:Z

    .line 387
    return-void
.end method

.method protected shouldStartScroll(Landroid/view/MotionEvent;)Z
    .locals 11

    .prologue
    const/4 v3, -0x1

    const/4 v1, 0x1

    const/4 v0, 0x0

    .line 512
    iget v2, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->cV:I

    .line 513
    if-ne v2, v3, :cond_0

    .line 570
    :goto_0
    return v0

    .line 517
    :cond_0
    invoke-virtual {p1, v2}, Landroid/view/MotionEvent;->findPointerIndex(I)I

    move-result v2

    .line 518
    if-ne v2, v3, :cond_1

    .line 519
    sget-object v1, Lcom/miui/internal/widget/ActionBarMovableLayout;->TAG:Ljava/lang/String;

    const-string v2, "invalid pointer index"

    invoke-static {v1, v2}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    goto :goto_0

    .line 523
    :cond_1
    invoke-virtual {p1, v2}, Landroid/view/MotionEvent;->getX(I)F

    move-result v3

    .line 524
    invoke-virtual {p1, v2}, Landroid/view/MotionEvent;->getY(I)F

    move-result v4

    .line 526
    iget v2, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->mLastMotionY:F

    sub-float v2, v4, v2

    float-to-int v5, v2

    .line 527
    invoke-static {v5}, Ljava/lang/Math;->abs(I)I

    move-result v6

    .line 528
    iget v2, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->mLastMotionX:F

    sub-float v2, v3, v2

    invoke-static {v2}, Ljava/lang/Math;->abs(F)F

    move-result v2

    float-to-int v7, v2

    .line 530
    iget-object v2, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->mContentView:Landroid/view/View;

    float-to-int v8, v3

    float-to-int v9, v4

    invoke-direct {p0, v2, v8, v9}, Lcom/miui/internal/widget/ActionBarMovableLayout;->b(Landroid/view/View;II)Z

    move-result v2

    .line 531
    iget-object v8, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Gg:Landroid/view/View;

    float-to-int v9, v3

    float-to-int v10, v4

    invoke-direct {p0, v8, v9, v10}, Lcom/miui/internal/widget/ActionBarMovableLayout;->b(Landroid/view/View;II)Z

    move-result v8

    .line 533
    if-nez v2, :cond_2

    if-eqz v8, :cond_5

    :cond_2
    move v2, v1

    .line 536
    :goto_1
    if-eqz v2, :cond_9

    .line 537
    iget v2, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->cT:I

    if-le v6, v2, :cond_9

    if-le v6, v7, :cond_9

    .line 539
    iget v2, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Gi:I

    if-nez v2, :cond_7

    .line 540
    if-gez v5, :cond_6

    move v2, v0

    .line 554
    :goto_2
    if-eqz v2, :cond_4

    .line 555
    iput v4, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->mLastMotionY:F

    .line 556
    iput v3, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->mLastMotionX:F

    .line 558
    if-lez v5, :cond_3

    move v0, v1

    :cond_3
    iput v0, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->vP:I

    .line 560
    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarMovableLayout;->getParent()Landroid/view/ViewParent;

    move-result-object v0

    .line 561
    if-eqz v0, :cond_4

    .line 562
    invoke-interface {v0, v1}, Landroid/view/ViewParent;->requestDisallowInterceptTouchEvent(Z)V

    :cond_4
    move v0, v2

    .line 570
    goto :goto_0

    :cond_5
    move v2, v0

    .line 533
    goto :goto_1

    .line 543
    :cond_6
    iget-object v2, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Gr:Lcom/miui/internal/widget/ActionBarMovableLayout$OnScrollListener;

    if-eqz v2, :cond_8

    iget-object v2, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Gr:Lcom/miui/internal/widget/ActionBarMovableLayout$OnScrollListener;

    invoke-interface {v2}, Lcom/miui/internal/widget/ActionBarMovableLayout$OnScrollListener;->onContentScrolled()Z

    move-result v2

    if-eqz v2, :cond_8

    move v2, v0

    .line 544
    goto :goto_2

    .line 547
    :cond_7
    if-lez v5, :cond_8

    iget v2, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Gi:I

    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarMovableLayout;->getOverScrollDistance()I

    move-result v6

    if-lt v2, v6, :cond_8

    iget-object v2, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Gr:Lcom/miui/internal/widget/ActionBarMovableLayout$OnScrollListener;

    if-eqz v2, :cond_8

    iget-object v2, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Gr:Lcom/miui/internal/widget/ActionBarMovableLayout$OnScrollListener;

    invoke-interface {v2}, Lcom/miui/internal/widget/ActionBarMovableLayout$OnScrollListener;->onContentScrolled()Z

    move-result v2

    if-eqz v2, :cond_8

    move v2, v0

    .line 549
    goto :goto_2

    :cond_8
    move v2, v1

    goto :goto_2

    :cond_9
    move v2, v0

    goto :goto_2
.end method

.method protected springBack()V
    .locals 6

    .prologue
    const/4 v1, 0x0

    .line 617
    iget-boolean v0, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Gp:Z

    if-eqz v0, :cond_0

    .line 618
    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarMovableLayout;->getScrollRange()I

    move-result v0

    .line 619
    iget v2, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Gi:I

    div-int/lit8 v3, v0, 0x2

    if-le v2, v3, :cond_1

    iget v2, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Gi:I

    sub-int v4, v0, v2

    .line 620
    :goto_0
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Gh:Landroid/widget/OverScroller;

    iget v2, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Gi:I

    const/16 v5, 0x320

    move v3, v1

    invoke-virtual/range {v0 .. v5}, Landroid/widget/OverScroller;->startScroll(IIIII)V

    .line 621
    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarMovableLayout;->postInvalidateOnAnimation()V

    .line 623
    :cond_0
    return-void

    .line 619
    :cond_1
    iget v0, p0, Lcom/miui/internal/widget/ActionBarMovableLayout;->Gi:I

    neg-int v4, v0

    goto :goto_0
.end method
