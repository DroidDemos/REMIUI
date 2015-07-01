.class public Lcom/miui/internal/widget/ActionBarContainer;
.super Landroid/widget/FrameLayout;
.source "SourceFile"

# interfaces
.implements Lmiui/app/ActionBar$FragmentViewPagerChangeListener;


# static fields
.field private static final lA:I = 0x0

.field private static final lB:I = 0x1

.field private static final lC:I = 0x2

.field private static final lD:I = 0x3


# instance fields
.field private lE:Z

.field private lF:Landroid/animation/AnimatorListenerAdapter;

.field private lG:Landroid/animation/AnimatorListenerAdapter;

.field private ll:Z

.field private lm:Landroid/view/View;

.field private ln:Lcom/miui/internal/widget/ActionBarContextView;

.field private lo:Landroid/animation/Animator;

.field private lp:Landroid/graphics/drawable/Drawable;

.field private lq:[Landroid/graphics/drawable/Drawable;

.field private lr:Landroid/graphics/drawable/Drawable;

.field private ls:Landroid/graphics/drawable/Drawable;

.field private lt:Z

.field private lu:Z

.field private lv:Z

.field private lw:Z

.field private lx:Z

.field private ly:Landroid/graphics/Rect;

.field private lz:I

.field private mActionBarView:Lcom/miui/internal/widget/ActionBarView;

.field private mContentHeight:I


# direct methods
.method public constructor <init>(Landroid/content/Context;)V
    .locals 1

    .prologue
    .line 94
    const/4 v0, 0x0

    invoke-direct {p0, p1, v0}, Lcom/miui/internal/widget/ActionBarContainer;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    .line 95
    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 7

    .prologue
    const/4 v6, 0x3

    const/4 v0, 0x1

    const/4 v1, 0x0

    .line 98
    invoke-direct {p0, p1, p2}, Landroid/widget/FrameLayout;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    .line 77
    iput-boolean v1, p0, Lcom/miui/internal/widget/ActionBarContainer;->lE:Z

    .line 79
    new-instance v2, Lcom/miui/internal/widget/p;

    invoke-direct {v2, p0}, Lcom/miui/internal/widget/p;-><init>(Lcom/miui/internal/widget/ActionBarContainer;)V

    iput-object v2, p0, Lcom/miui/internal/widget/ActionBarContainer;->lF:Landroid/animation/AnimatorListenerAdapter;

    .line 87
    new-instance v2, Lcom/miui/internal/widget/n;

    invoke-direct {v2, p0}, Lcom/miui/internal/widget/n;-><init>(Lcom/miui/internal/widget/ActionBarContainer;)V

    iput-object v2, p0, Lcom/miui/internal/widget/ActionBarContainer;->lG:Landroid/animation/AnimatorListenerAdapter;

    .line 100
    const/4 v2, 0x0

    invoke-virtual {p0, v2}, Lcom/miui/internal/widget/ActionBarContainer;->setBackground(Landroid/graphics/drawable/Drawable;)V

    .line 102
    sget-object v2, Lmiui/R$styleable;->ActionBar:[I

    invoke-virtual {p1, p2, v2}, Landroid/content/Context;->obtainStyledAttributes(Landroid/util/AttributeSet;[I)Landroid/content/res/TypedArray;

    move-result-object v2

    .line 104
    invoke-virtual {v2, v0}, Landroid/content/res/TypedArray;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v3

    iput-object v3, p0, Lcom/miui/internal/widget/ActionBarContainer;->lp:Landroid/graphics/drawable/Drawable;

    .line 105
    new-array v3, v6, [Landroid/graphics/drawable/Drawable;

    iget-object v4, p0, Lcom/miui/internal/widget/ActionBarContainer;->lp:Landroid/graphics/drawable/Drawable;

    aput-object v4, v3, v1

    const/16 v4, 0x14

    invoke-virtual {v2, v4}, Landroid/content/res/TypedArray;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v4

    aput-object v4, v3, v0

    const/4 v4, 0x2

    const/16 v5, 0x15

    invoke-virtual {v2, v5}, Landroid/content/res/TypedArray;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v5

    aput-object v5, v3, v4

    iput-object v3, p0, Lcom/miui/internal/widget/ActionBarContainer;->lq:[Landroid/graphics/drawable/Drawable;

    .line 107
    const/16 v3, 0xf

    invoke-virtual {v2, v3}, Landroid/content/res/TypedArray;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v3

    iput-object v3, p0, Lcom/miui/internal/widget/ActionBarContainer;->lr:Landroid/graphics/drawable/Drawable;

    .line 109
    const/16 v3, 0x13

    invoke-virtual {v2, v3, v1}, Landroid/content/res/TypedArray;->getBoolean(IZ)Z

    move-result v3

    iput-boolean v3, p0, Lcom/miui/internal/widget/ActionBarContainer;->lx:Z

    .line 111
    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarContainer;->getId()I

    move-result v3

    sget v4, Lcom/miui/internal/R$id;->split_action_bar:I

    if-ne v3, v4, :cond_0

    .line 112
    iput-boolean v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->lt:Z

    .line 113
    const/16 v3, 0x10

    invoke-virtual {v2, v3}, Landroid/content/res/TypedArray;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v3

    iput-object v3, p0, Lcom/miui/internal/widget/ActionBarContainer;->ls:Landroid/graphics/drawable/Drawable;

    .line 114
    invoke-virtual {v2, v6, v1}, Landroid/content/res/TypedArray;->getDimensionPixelSize(II)I

    move-result v3

    iput v3, p0, Lcom/miui/internal/widget/ActionBarContainer;->mContentHeight:I

    .line 116
    :cond_0
    invoke-virtual {v2}, Landroid/content/res/TypedArray;->recycle()V

    .line 118
    iget-boolean v2, p0, Lcom/miui/internal/widget/ActionBarContainer;->lt:Z

    if-eqz v2, :cond_3

    iget-object v2, p0, Lcom/miui/internal/widget/ActionBarContainer;->ls:Landroid/graphics/drawable/Drawable;

    if-nez v2, :cond_2

    :cond_1
    :goto_0
    invoke-virtual {p0, v0}, Lcom/miui/internal/widget/ActionBarContainer;->setWillNotDraw(Z)V

    .line 120
    return-void

    :cond_2
    move v0, v1

    .line 118
    goto :goto_0

    :cond_3
    iget-object v2, p0, Lcom/miui/internal/widget/ActionBarContainer;->lp:Landroid/graphics/drawable/Drawable;

    if-nez v2, :cond_4

    iget-object v2, p0, Lcom/miui/internal/widget/ActionBarContainer;->lr:Landroid/graphics/drawable/Drawable;

    if-eqz v2, :cond_1

    :cond_4
    move v0, v1

    goto :goto_0
.end method

.method static synthetic a(Lcom/miui/internal/widget/ActionBarContainer;Landroid/animation/Animator;)Landroid/animation/Animator;
    .locals 0

    .prologue
    .line 35
    iput-object p1, p0, Lcom/miui/internal/widget/ActionBarContainer;->lo:Landroid/animation/Animator;

    return-object p1
.end method

.method private aH()V
    .locals 3

    .prologue
    .line 559
    iget-boolean v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->lE:Z

    if-nez v0, :cond_0

    iget-boolean v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->lt:Z

    if-nez v0, :cond_0

    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->mActionBarView:Lcom/miui/internal/widget/ActionBarView;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->lp:Landroid/graphics/drawable/Drawable;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->lq:[Landroid/graphics/drawable/Drawable;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->lq:[Landroid/graphics/drawable/Drawable;

    array-length v0, v0

    const/4 v1, 0x3

    if-ge v0, v1, :cond_1

    .line 578
    :cond_0
    :goto_0
    return-void

    .line 565
    :cond_1
    const/4 v0, 0x0

    .line 566
    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarContainer;->mActionBarView:Lcom/miui/internal/widget/ActionBarView;

    invoke-virtual {v1}, Lcom/miui/internal/widget/ActionBarView;->hasEmbeddedTabs()Z

    move-result v1

    if-eqz v1, :cond_3

    .line 567
    const/4 v0, 0x1

    .line 568
    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarContainer;->mActionBarView:Lcom/miui/internal/widget/ActionBarView;

    invoke-virtual {v1}, Lcom/miui/internal/widget/ActionBarView;->getDisplayOptions()I

    move-result v1

    .line 569
    and-int/lit8 v2, v1, 0x2

    if-nez v2, :cond_2

    and-int/lit8 v2, v1, 0x4

    if-nez v2, :cond_2

    and-int/lit8 v1, v1, 0x10

    if-eqz v1, :cond_3

    .line 572
    :cond_2
    const/4 v0, 0x2

    .line 575
    :cond_3
    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarContainer;->lq:[Landroid/graphics/drawable/Drawable;

    aget-object v1, v1, v0

    if-eqz v1, :cond_0

    .line 576
    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarContainer;->lq:[Landroid/graphics/drawable/Drawable;

    aget-object v0, v1, v0

    iput-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->lp:Landroid/graphics/drawable/Drawable;

    goto :goto_0
.end method

.method private c(Landroid/view/View;)V
    .locals 2

    .prologue
    .line 349
    if-eqz p1, :cond_1

    invoke-virtual {p1}, Landroid/view/View;->getVisibility()I

    move-result v0

    if-nez v0, :cond_1

    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->mActionBarView:Lcom/miui/internal/widget/ActionBarView;

    if-ne p1, v0, :cond_0

    iget-boolean v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->lx:Z

    if-nez v0, :cond_1

    .line 351
    :cond_0
    invoke-virtual {p1}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v0

    check-cast v0, Landroid/view/ViewGroup$MarginLayoutParams;

    .line 352
    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarContainer;->ly:Landroid/graphics/Rect;

    if-eqz v1, :cond_2

    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarContainer;->ly:Landroid/graphics/Rect;

    iget v1, v1, Landroid/graphics/Rect;->top:I

    :goto_0
    iput v1, v0, Landroid/view/ViewGroup$MarginLayoutParams;->topMargin:I

    .line 354
    :cond_1
    return-void

    .line 352
    :cond_2
    const/4 v1, 0x0

    goto :goto_0
.end method

.method private e(II)V
    .locals 5

    .prologue
    const/high16 v3, 0x40000000

    const/4 v1, 0x0

    .line 410
    invoke-static {p1}, Landroid/view/View$MeasureSpec;->getMode(I)I

    move-result v0

    const/high16 v2, -0x80000000

    if-ne v0, v2, :cond_0

    .line 411
    invoke-static {p1}, Landroid/view/View$MeasureSpec;->getSize(I)I

    move-result v0

    invoke-static {v0, v3}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result p1

    .line 415
    :cond_0
    sget-boolean v0, Lmiui/os/Build;->IS_TABLET:Z

    if-eqz v0, :cond_1

    iget v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->mContentHeight:I

    if-lez v0, :cond_1

    .line 416
    iget v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->mContentHeight:I

    invoke-static {v0, v3}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result p2

    .line 419
    :cond_1
    invoke-super {p0, p1, p2}, Landroid/widget/FrameLayout;->onMeasure(II)V

    .line 422
    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarContainer;->getChildCount()I

    move-result v3

    move v0, v1

    move v2, v1

    .line 424
    :goto_0
    if-ge v0, v3, :cond_2

    .line 425
    invoke-virtual {p0, v0}, Lcom/miui/internal/widget/ActionBarContainer;->getChildAt(I)Landroid/view/View;

    move-result-object v4

    invoke-virtual {v4}, Landroid/view/View;->getMeasuredHeight()I

    move-result v4

    invoke-static {v2, v4}, Ljava/lang/Math;->max(II)I

    move-result v2

    .line 424
    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    .line 427
    :cond_2
    if-nez v2, :cond_3

    .line 428
    invoke-virtual {p0, v1, v1}, Lcom/miui/internal/widget/ActionBarContainer;->setMeasuredDimension(II)V

    .line 430
    :cond_3
    return-void
.end method


# virtual methods
.method public draw(Landroid/graphics/Canvas;)V
    .locals 1

    .prologue
    .line 545
    invoke-super {p0, p1}, Landroid/widget/FrameLayout;->draw(Landroid/graphics/Canvas;)V

    .line 547
    iget-boolean v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->lv:Z

    if-eqz v0, :cond_0

    .line 548
    new-instance v0, Lcom/miui/internal/widget/ActionBarContainer$1;

    invoke-direct {v0, p0}, Lcom/miui/internal/widget/ActionBarContainer$1;-><init>(Lcom/miui/internal/widget/ActionBarContainer;)V

    invoke-virtual {p0, v0}, Lcom/miui/internal/widget/ActionBarContainer;->post(Ljava/lang/Runnable;)Z

    .line 554
    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->lv:Z

    .line 556
    :cond_0
    return-void
.end method

.method protected drawableStateChanged()V
    .locals 2

    .prologue
    .line 257
    invoke-super {p0}, Landroid/widget/FrameLayout;->drawableStateChanged()V

    .line 258
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->lp:Landroid/graphics/drawable/Drawable;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->lp:Landroid/graphics/drawable/Drawable;

    invoke-virtual {v0}, Landroid/graphics/drawable/Drawable;->isStateful()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 259
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->lp:Landroid/graphics/drawable/Drawable;

    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarContainer;->getDrawableState()[I

    move-result-object v1

    invoke-virtual {v0, v1}, Landroid/graphics/drawable/Drawable;->setState([I)Z

    .line 261
    :cond_0
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->lr:Landroid/graphics/drawable/Drawable;

    if-eqz v0, :cond_1

    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->lr:Landroid/graphics/drawable/Drawable;

    invoke-virtual {v0}, Landroid/graphics/drawable/Drawable;->isStateful()Z

    move-result v0

    if-eqz v0, :cond_1

    .line 262
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->lr:Landroid/graphics/drawable/Drawable;

    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarContainer;->getDrawableState()[I

    move-result-object v1

    invoke-virtual {v0, v1}, Landroid/graphics/drawable/Drawable;->setState([I)Z

    .line 264
    :cond_1
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->ls:Landroid/graphics/drawable/Drawable;

    if-eqz v0, :cond_2

    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->ls:Landroid/graphics/drawable/Drawable;

    invoke-virtual {v0}, Landroid/graphics/drawable/Drawable;->isStateful()Z

    move-result v0

    if-eqz v0, :cond_2

    .line 265
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->ls:Landroid/graphics/drawable/Drawable;

    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarContainer;->getDrawableState()[I

    move-result-object v1

    invoke-virtual {v0, v1}, Landroid/graphics/drawable/Drawable;->setState([I)Z

    .line 267
    :cond_2
    return-void
.end method

.method getCollapsedHeight()I
    .locals 6

    .prologue
    const/4 v2, 0x0

    .line 151
    .line 152
    iget-boolean v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->lt:Z

    if-eqz v0, :cond_5

    move v1, v2

    move v3, v2

    move v4, v2

    .line 154
    :goto_0
    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarContainer;->getChildCount()I

    move-result v0

    if-ge v1, v0, :cond_1

    .line 155
    invoke-virtual {p0, v1}, Lcom/miui/internal/widget/ActionBarContainer;->getChildAt(I)Landroid/view/View;

    move-result-object v0

    instance-of v0, v0, Lcom/miui/internal/view/menu/ActionMenuView;

    if-eqz v0, :cond_0

    .line 156
    invoke-virtual {p0, v1}, Lcom/miui/internal/widget/ActionBarContainer;->getChildAt(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/view/menu/ActionMenuView;

    .line 157
    invoke-virtual {v0}, Lcom/miui/internal/view/menu/ActionMenuView;->getVisibility()I

    move-result v5

    if-nez v5, :cond_0

    invoke-virtual {v0}, Lcom/miui/internal/view/menu/ActionMenuView;->getCollapsedHeight()I

    move-result v5

    if-lez v5, :cond_0

    .line 158
    add-int/lit8 v3, v3, 0x1

    .line 159
    invoke-virtual {v0}, Lcom/miui/internal/view/menu/ActionMenuView;->getCollapsedHeight()I

    move-result v0

    invoke-static {v4, v0}, Ljava/lang/Math;->max(II)I

    move-result v4

    .line 154
    :cond_0
    add-int/lit8 v0, v1, 0x1

    move v1, v0

    goto :goto_0

    .line 164
    :cond_1
    sget-boolean v0, Lmiui/os/Build;->IS_TABLET:Z

    if-eqz v0, :cond_2

    .line 165
    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarContainer;->getMeasuredHeight()I

    move-result v0

    invoke-static {v4, v0}, Ljava/lang/Math;->max(II)I

    move-result v4

    .line 168
    :cond_2
    iget-boolean v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->lw:Z

    if-eqz v0, :cond_4

    const/4 v0, 0x1

    if-ne v3, v0, :cond_3

    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->ln:Lcom/miui/internal/widget/ActionBarContextView;

    invoke-virtual {v0}, Lcom/miui/internal/widget/ActionBarContextView;->getAnimatedVisibility()I

    move-result v0

    if-eqz v0, :cond_4

    :cond_3
    move v4, v2

    .line 173
    :cond_4
    :goto_1
    return v4

    :cond_5
    move v4, v2

    goto :goto_1
.end method

.method public getContentHeight()I
    .locals 1

    .prologue
    .line 231
    iget v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->mContentHeight:I

    return v0
.end method

.method public getPendingInsets()Landroid/graphics/Rect;
    .locals 1

    .prologue
    .line 143
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->ly:Landroid/graphics/Rect;

    return-object v0
.end method

.method public getTabContainer()Landroid/view/View;
    .locals 1

    .prologue
    .line 301
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->lm:Landroid/view/View;

    return-object v0
.end method

.method public hide(Z)V
    .locals 4

    .prologue
    .line 489
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->lo:Landroid/animation/Animator;

    if-eqz v0, :cond_0

    .line 490
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->lo:Landroid/animation/Animator;

    invoke-virtual {v0}, Landroid/animation/Animator;->cancel()V

    .line 493
    :cond_0
    if-eqz p1, :cond_1

    iget-boolean v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->lt:Z

    if-eqz v0, :cond_1

    .line 494
    const-string v0, "TranslationY"

    const/4 v1, 0x2

    new-array v1, v1, [F

    const/4 v2, 0x0

    const/4 v3, 0x0

    aput v3, v1, v2

    const/4 v2, 0x1

    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarContainer;->getHeight()I

    move-result v3

    int-to-float v3, v3

    aput v3, v1, v2

    invoke-static {p0, v0, v1}, Landroid/animation/ObjectAnimator;->ofFloat(Ljava/lang/Object;Ljava/lang/String;[F)Landroid/animation/ObjectAnimator;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->lo:Landroid/animation/Animator;

    .line 496
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->lo:Landroid/animation/Animator;

    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarContainer;->getContext()Landroid/content/Context;

    move-result-object v1

    invoke-virtual {v1}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    const/high16 v2, 0x10e0000

    invoke-virtual {v1, v2}, Landroid/content/res/Resources;->getInteger(I)I

    move-result v1

    int-to-long v1, v1

    invoke-virtual {v0, v1, v2}, Landroid/animation/Animator;->setDuration(J)Landroid/animation/Animator;

    .line 498
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->lo:Landroid/animation/Animator;

    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarContainer;->lF:Landroid/animation/AnimatorListenerAdapter;

    invoke-virtual {v0, v1}, Landroid/animation/Animator;->addListener(Landroid/animation/Animator$AnimatorListener;)V

    .line 499
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->lo:Landroid/animation/Animator;

    invoke-virtual {v0}, Landroid/animation/Animator;->start()V

    .line 503
    :goto_0
    return-void

    .line 501
    :cond_1
    const/16 v0, 0x8

    invoke-virtual {p0, v0}, Lcom/miui/internal/widget/ActionBarContainer;->setVisibility(I)V

    goto :goto_0
.end method

.method public onDraw(Landroid/graphics/Canvas;)V
    .locals 1

    .prologue
    .line 326
    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarContainer;->getWidth()I

    move-result v0

    if-eqz v0, :cond_0

    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarContainer;->getHeight()I

    move-result v0

    if-nez v0, :cond_1

    .line 340
    :cond_0
    :goto_0
    return-void

    .line 330
    :cond_1
    iget-boolean v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->lt:Z

    if-eqz v0, :cond_2

    .line 331
    sget-boolean v0, Lmiui/os/Build;->IS_TABLET:Z

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->ls:Landroid/graphics/drawable/Drawable;

    if-eqz v0, :cond_0

    .line 333
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->ls:Landroid/graphics/drawable/Drawable;

    invoke-virtual {v0, p1}, Landroid/graphics/drawable/Drawable;->draw(Landroid/graphics/Canvas;)V

    goto :goto_0

    .line 336
    :cond_2
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->lp:Landroid/graphics/drawable/Drawable;

    if-eqz v0, :cond_0

    .line 337
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->lp:Landroid/graphics/drawable/Drawable;

    invoke-virtual {v0, p1}, Landroid/graphics/drawable/Drawable;->draw(Landroid/graphics/Canvas;)V

    goto :goto_0
.end method

.method public onFinishInflate()V
    .locals 1

    .prologue
    .line 124
    invoke-super {p0}, Landroid/widget/FrameLayout;->onFinishInflate()V

    .line 125
    sget v0, Lcom/miui/internal/R$id;->action_bar:I

    invoke-virtual {p0, v0}, Lcom/miui/internal/widget/ActionBarContainer;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/widget/ActionBarView;

    iput-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->mActionBarView:Lcom/miui/internal/widget/ActionBarView;

    .line 126
    sget v0, Lcom/miui/internal/R$id;->action_context_bar:I

    invoke-virtual {p0, v0}, Lcom/miui/internal/widget/ActionBarContainer;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/widget/ActionBarContextView;

    iput-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->ln:Lcom/miui/internal/widget/ActionBarContextView;

    .line 127
    return-void
.end method

.method public onHoverEvent(Landroid/view/MotionEvent;)Z
    .locals 1

    .prologue
    .line 297
    const/4 v0, 0x1

    return v0
.end method

.method public onInterceptTouchEvent(Landroid/view/MotionEvent;)Z
    .locals 1

    .prologue
    .line 284
    iget-boolean v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->ll:Z

    if-nez v0, :cond_0

    invoke-super {p0, p1}, Landroid/widget/FrameLayout;->onInterceptTouchEvent(Landroid/view/MotionEvent;)Z

    move-result v0

    if-eqz v0, :cond_1

    :cond_0
    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_1
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public onLayout(ZIIII)V
    .locals 9

    .prologue
    const/4 v3, 0x1

    const/4 v1, 0x0

    .line 434
    invoke-super/range {p0 .. p5}, Landroid/widget/FrameLayout;->onLayout(ZIIII)V

    .line 436
    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarContainer;->getMeasuredHeight()I

    move-result v4

    .line 438
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->lm:Landroid/view/View;

    if-eqz v0, :cond_6

    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->lm:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->getVisibility()I

    move-result v0

    const/16 v2, 0x8

    if-eq v0, v2, :cond_6

    .line 439
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->lm:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->getMeasuredHeight()I

    move-result v2

    .line 440
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->mActionBarView:Lcom/miui/internal/widget/ActionBarView;

    if-eqz v0, :cond_2

    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->mActionBarView:Lcom/miui/internal/widget/ActionBarView;

    invoke-virtual {v0}, Lcom/miui/internal/widget/ActionBarView;->getVisibility()I

    move-result v0

    if-nez v0, :cond_2

    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->mActionBarView:Lcom/miui/internal/widget/ActionBarView;

    invoke-virtual {v0}, Lcom/miui/internal/widget/ActionBarView;->getMeasuredHeight()I

    move-result v0

    if-lez v0, :cond_2

    .line 444
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->lm:Landroid/view/View;

    iget-object v5, p0, Lcom/miui/internal/widget/ActionBarContainer;->lm:Landroid/view/View;

    invoke-virtual {v5}, Landroid/view/View;->getPaddingLeft()I

    move-result v5

    iget v6, p0, Lcom/miui/internal/widget/ActionBarContainer;->lz:I

    iget-object v7, p0, Lcom/miui/internal/widget/ActionBarContainer;->lm:Landroid/view/View;

    invoke-virtual {v7}, Landroid/view/View;->getPaddingRight()I

    move-result v7

    iget-object v8, p0, Lcom/miui/internal/widget/ActionBarContainer;->lm:Landroid/view/View;

    invoke-virtual {v8}, Landroid/view/View;->getPaddingBottom()I

    move-result v8

    invoke-virtual {v0, v5, v6, v7, v8}, Landroid/view/View;->setPadding(IIII)V

    move v0, v2

    .line 453
    :goto_0
    iget-object v5, p0, Lcom/miui/internal/widget/ActionBarContainer;->lm:Landroid/view/View;

    sub-int v0, v4, v0

    invoke-virtual {v5, p2, v0, p4, v4}, Landroid/view/View;->layout(IIII)V

    .line 458
    :goto_1
    iget-boolean v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->lt:Z

    if-eqz v0, :cond_5

    .line 459
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->ls:Landroid/graphics/drawable/Drawable;

    if-eqz v0, :cond_0

    .line 460
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->ls:Landroid/graphics/drawable/Drawable;

    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarContainer;->getMeasuredWidth()I

    move-result v2

    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarContainer;->getMeasuredHeight()I

    move-result v4

    invoke-virtual {v0, v1, v1, v2, v4}, Landroid/graphics/drawable/Drawable;->setBounds(IIII)V

    move v1, v3

    .line 471
    :cond_0
    :goto_2
    if-eqz v1, :cond_1

    .line 472
    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarContainer;->invalidate()V

    .line 474
    :cond_1
    return-void

    .line 447
    :cond_2
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->ly:Landroid/graphics/Rect;

    if-eqz v0, :cond_3

    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->ly:Landroid/graphics/Rect;

    iget v0, v0, Landroid/graphics/Rect;->top:I

    :goto_3
    add-int/2addr v2, v0

    .line 448
    iget-object v5, p0, Lcom/miui/internal/widget/ActionBarContainer;->lm:Landroid/view/View;

    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->lm:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->getPaddingLeft()I

    move-result v6

    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->ly:Landroid/graphics/Rect;

    if-eqz v0, :cond_4

    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->ly:Landroid/graphics/Rect;

    iget v0, v0, Landroid/graphics/Rect;->top:I

    iget v7, p0, Lcom/miui/internal/widget/ActionBarContainer;->lz:I

    add-int/2addr v0, v7

    :goto_4
    iget-object v7, p0, Lcom/miui/internal/widget/ActionBarContainer;->lm:Landroid/view/View;

    invoke-virtual {v7}, Landroid/view/View;->getPaddingRight()I

    move-result v7

    iget-object v8, p0, Lcom/miui/internal/widget/ActionBarContainer;->lm:Landroid/view/View;

    invoke-virtual {v8}, Landroid/view/View;->getPaddingBottom()I

    move-result v8

    invoke-virtual {v5, v6, v0, v7, v8}, Landroid/view/View;->setPadding(IIII)V

    move v0, v2

    move v2, v1

    goto :goto_0

    :cond_3
    move v0, v1

    .line 447
    goto :goto_3

    .line 448
    :cond_4
    iget v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->lz:I

    goto :goto_4

    .line 464
    :cond_5
    invoke-direct {p0}, Lcom/miui/internal/widget/ActionBarContainer;->aH()V

    .line 465
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->lp:Landroid/graphics/drawable/Drawable;

    if-eqz v0, :cond_0

    .line 466
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->lp:Landroid/graphics/drawable/Drawable;

    sub-int v5, p4, p2

    sub-int v2, v4, v2

    invoke-virtual {v0, v1, v1, v5, v2}, Landroid/graphics/drawable/Drawable;->setBounds(IIII)V

    move v1, v3

    .line 467
    goto :goto_2

    :cond_6
    move v2, v1

    goto :goto_1
.end method

.method public onMeasure(II)V
    .locals 7

    .prologue
    const/16 v6, 0x8

    const/4 v1, 0x0

    .line 358
    iget-boolean v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->lt:Z

    if-eqz v0, :cond_1

    .line 359
    invoke-direct {p0, p1, p2}, Lcom/miui/internal/widget/ActionBarContainer;->e(II)V

    .line 406
    :cond_0
    :goto_0
    return-void

    .line 363
    :cond_1
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->lm:Landroid/view/View;

    if-eqz v0, :cond_2

    .line 365
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->lm:Landroid/view/View;

    iget-object v2, p0, Lcom/miui/internal/widget/ActionBarContainer;->lm:Landroid/view/View;

    invoke-virtual {v2}, Landroid/view/View;->getPaddingLeft()I

    move-result v2

    iget v3, p0, Lcom/miui/internal/widget/ActionBarContainer;->lz:I

    iget-object v4, p0, Lcom/miui/internal/widget/ActionBarContainer;->lm:Landroid/view/View;

    invoke-virtual {v4}, Landroid/view/View;->getPaddingRight()I

    move-result v4

    iget-object v5, p0, Lcom/miui/internal/widget/ActionBarContainer;->lm:Landroid/view/View;

    invoke-virtual {v5}, Landroid/view/View;->getPaddingBottom()I

    move-result v5

    invoke-virtual {v0, v2, v3, v4, v5}, Landroid/view/View;->setPadding(IIII)V

    .line 369
    :cond_2
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->mActionBarView:Lcom/miui/internal/widget/ActionBarView;

    invoke-direct {p0, v0}, Lcom/miui/internal/widget/ActionBarContainer;->c(Landroid/view/View;)V

    .line 370
    iget-object v2, p0, Lcom/miui/internal/widget/ActionBarContainer;->ln:Lcom/miui/internal/widget/ActionBarContextView;

    .line 371
    if-eqz v2, :cond_3

    .line 372
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->ly:Landroid/graphics/Rect;

    if-eqz v0, :cond_6

    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->ly:Landroid/graphics/Rect;

    iget v0, v0, Landroid/graphics/Rect;->top:I

    :goto_1
    invoke-virtual {v2, v0}, Lcom/miui/internal/widget/ActionBarContextView;->setContentInset(I)V

    .line 375
    :cond_3
    invoke-super {p0, p1, p2}, Landroid/widget/FrameLayout;->onMeasure(II)V

    .line 377
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->mActionBarView:Lcom/miui/internal/widget/ActionBarView;

    if-eqz v0, :cond_7

    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->mActionBarView:Lcom/miui/internal/widget/ActionBarView;

    invoke-virtual {v0}, Lcom/miui/internal/widget/ActionBarView;->getVisibility()I

    move-result v0

    if-eq v0, v6, :cond_7

    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->mActionBarView:Lcom/miui/internal/widget/ActionBarView;

    invoke-virtual {v0}, Lcom/miui/internal/widget/ActionBarView;->getMeasuredHeight()I

    move-result v0

    if-lez v0, :cond_7

    const/4 v0, 0x1

    move v2, v0

    .line 379
    :goto_2
    if-eqz v2, :cond_b

    .line 380
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->mActionBarView:Lcom/miui/internal/widget/ActionBarView;

    invoke-virtual {v0}, Lcom/miui/internal/widget/ActionBarView;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v0

    check-cast v0, Landroid/widget/FrameLayout$LayoutParams;

    .line 381
    iget-object v3, p0, Lcom/miui/internal/widget/ActionBarContainer;->mActionBarView:Lcom/miui/internal/widget/ActionBarView;

    invoke-virtual {v3}, Lcom/miui/internal/widget/ActionBarView;->isCollapsed()Z

    move-result v3

    if-eqz v3, :cond_8

    iget v0, v0, Landroid/widget/FrameLayout$LayoutParams;->topMargin:I

    .line 385
    :goto_3
    iget-object v3, p0, Lcom/miui/internal/widget/ActionBarContainer;->lm:Landroid/view/View;

    if-eqz v3, :cond_4

    iget-object v3, p0, Lcom/miui/internal/widget/ActionBarContainer;->lm:Landroid/view/View;

    invoke-virtual {v3}, Landroid/view/View;->getVisibility()I

    move-result v3

    if-eq v3, v6, :cond_4

    .line 386
    invoke-static {p2}, Landroid/view/View$MeasureSpec;->getMode(I)I

    move-result v3

    .line 387
    const/high16 v4, -0x80000000

    if-ne v3, v4, :cond_4

    .line 388
    invoke-static {p2}, Landroid/view/View$MeasureSpec;->getSize(I)I

    move-result v3

    .line 389
    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarContainer;->getMeasuredWidth()I

    move-result v4

    iget-object v5, p0, Lcom/miui/internal/widget/ActionBarContainer;->lm:Landroid/view/View;

    invoke-virtual {v5}, Landroid/view/View;->getMeasuredHeight()I

    move-result v5

    add-int/2addr v0, v5

    invoke-static {v0, v3}, Ljava/lang/Math;->min(II)I

    move-result v3

    if-nez v2, :cond_9

    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->ly:Landroid/graphics/Rect;

    if-eqz v0, :cond_9

    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->ly:Landroid/graphics/Rect;

    iget v0, v0, Landroid/graphics/Rect;->top:I

    :goto_4
    add-int/2addr v0, v3

    invoke-virtual {p0, v4, v0}, Lcom/miui/internal/widget/ActionBarContainer;->setMeasuredDimension(II)V

    :cond_4
    move v0, v1

    move v2, v1

    .line 397
    :goto_5
    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarContainer;->getChildCount()I

    move-result v3

    if-ge v0, v3, :cond_a

    .line 398
    invoke-virtual {p0, v0}, Lcom/miui/internal/widget/ActionBarContainer;->getChildAt(I)Landroid/view/View;

    move-result-object v3

    .line 399
    invoke-virtual {v3}, Landroid/view/View;->getVisibility()I

    move-result v4

    if-nez v4, :cond_5

    invoke-virtual {v3}, Landroid/view/View;->getMeasuredHeight()I

    move-result v4

    if-lez v4, :cond_5

    invoke-virtual {v3}, Landroid/view/View;->getMeasuredWidth()I

    move-result v3

    if-lez v3, :cond_5

    .line 400
    add-int/lit8 v2, v2, 0x1

    .line 397
    :cond_5
    add-int/lit8 v0, v0, 0x1

    goto :goto_5

    :cond_6
    move v0, v1

    .line 372
    goto/16 :goto_1

    :cond_7
    move v2, v1

    .line 377
    goto :goto_2

    .line 381
    :cond_8
    iget-object v3, p0, Lcom/miui/internal/widget/ActionBarContainer;->mActionBarView:Lcom/miui/internal/widget/ActionBarView;

    invoke-virtual {v3}, Lcom/miui/internal/widget/ActionBarView;->getMeasuredHeight()I

    move-result v3

    iget v4, v0, Landroid/widget/FrameLayout$LayoutParams;->topMargin:I

    add-int/2addr v3, v4

    iget v0, v0, Landroid/widget/FrameLayout$LayoutParams;->bottomMargin:I

    add-int/2addr v0, v3

    goto :goto_3

    :cond_9
    move v0, v1

    .line 389
    goto :goto_4

    .line 403
    :cond_a
    if-nez v2, :cond_0

    .line 404
    invoke-virtual {p0, v1, v1}, Lcom/miui/internal/widget/ActionBarContainer;->setMeasuredDimension(II)V

    goto/16 :goto_0

    :cond_b
    move v0, v1

    goto :goto_3
.end method

.method public onPageScrollStateChanged(I)V
    .locals 0

    .prologue
    .line 541
    return-void
.end method

.method public onPageScrolled(IFZZ)V
    .locals 1

    .prologue
    .line 529
    iget-boolean v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->lt:Z

    if-eqz v0, :cond_0

    .line 530
    const/4 v0, 0x0

    invoke-virtual {p0, v0}, Lcom/miui/internal/widget/ActionBarContainer;->getChildAt(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/view/menu/ActionMenuView;

    .line 531
    if-eqz v0, :cond_0

    .line 532
    invoke-virtual {v0, p1, p2, p3, p4}, Lcom/miui/internal/view/menu/ActionMenuView;->onPageScrolled(IFZZ)V

    .line 535
    :cond_0
    return-void
.end method

.method public onPageSelected(I)V
    .locals 0

    .prologue
    .line 538
    return-void
.end method

.method public onTouchEvent(Landroid/view/MotionEvent;)Z
    .locals 1

    .prologue
    .line 289
    iget-boolean v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->lt:Z

    if-nez v0, :cond_0

    invoke-super {p0, p1}, Landroid/widget/FrameLayout;->onTouchEvent(Landroid/view/MotionEvent;)Z

    move-result v0

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public onWindowHide()V
    .locals 1

    .prologue
    .line 483
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->mActionBarView:Lcom/miui/internal/widget/ActionBarView;

    invoke-virtual {v0}, Lcom/miui/internal/widget/ActionBarView;->getMenuView()Lcom/miui/internal/view/menu/ActionMenuView;

    move-result-object v0

    if-eqz v0, :cond_0

    .line 484
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->mActionBarView:Lcom/miui/internal/widget/ActionBarView;

    invoke-virtual {v0}, Lcom/miui/internal/widget/ActionBarView;->getMenuView()Lcom/miui/internal/view/menu/ActionMenuView;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/internal/view/menu/ActionMenuView;->startLayoutAnimation()V

    .line 486
    :cond_0
    return-void
.end method

.method public onWindowShow()V
    .locals 1

    .prologue
    .line 477
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->mActionBarView:Lcom/miui/internal/widget/ActionBarView;

    invoke-virtual {v0}, Lcom/miui/internal/widget/ActionBarView;->getMenuView()Lcom/miui/internal/view/menu/ActionMenuView;

    move-result-object v0

    if-eqz v0, :cond_0

    .line 478
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->mActionBarView:Lcom/miui/internal/widget/ActionBarView;

    invoke-virtual {v0}, Lcom/miui/internal/widget/ActionBarView;->getMenuView()Lcom/miui/internal/view/menu/ActionMenuView;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/internal/view/menu/ActionMenuView;->startLayoutAnimation()V

    .line 480
    :cond_0
    return-void
.end method

.method public setActionBarContextView(Lcom/miui/internal/widget/ActionBarContextView;)V
    .locals 0

    .prologue
    .line 130
    iput-object p1, p0, Lcom/miui/internal/widget/ActionBarContainer;->ln:Lcom/miui/internal/widget/ActionBarContextView;

    .line 131
    return-void
.end method

.method public setFragmentViewPagerMode(Z)V
    .locals 0

    .prologue
    .line 147
    iput-boolean p1, p0, Lcom/miui/internal/widget/ActionBarContainer;->lw:Z

    .line 148
    return-void
.end method

.method public setPendingInsets(Landroid/graphics/Rect;)V
    .locals 1

    .prologue
    .line 134
    iget-boolean v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->lt:Z

    if-nez v0, :cond_1

    .line 135
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->ly:Landroid/graphics/Rect;

    if-nez v0, :cond_0

    .line 136
    new-instance v0, Landroid/graphics/Rect;

    invoke-direct {v0}, Landroid/graphics/Rect;-><init>()V

    iput-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->ly:Landroid/graphics/Rect;

    .line 138
    :cond_0
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->ly:Landroid/graphics/Rect;

    invoke-virtual {v0, p1}, Landroid/graphics/Rect;->set(Landroid/graphics/Rect;)V

    .line 140
    :cond_1
    return-void
.end method

.method public setPrimaryBackground(Landroid/graphics/drawable/Drawable;)V
    .locals 5

    .prologue
    const/4 v1, 0x0

    const/4 v2, 0x1

    const/4 v3, 0x0

    .line 177
    .line 178
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->lp:Landroid/graphics/drawable/Drawable;

    if-eqz v0, :cond_5

    .line 179
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->lp:Landroid/graphics/drawable/Drawable;

    invoke-virtual {v0}, Landroid/graphics/drawable/Drawable;->getBounds()Landroid/graphics/Rect;

    move-result-object v0

    .line 180
    iget-object v4, p0, Lcom/miui/internal/widget/ActionBarContainer;->lp:Landroid/graphics/drawable/Drawable;

    invoke-virtual {v4, v1}, Landroid/graphics/drawable/Drawable;->setCallback(Landroid/graphics/drawable/Drawable$Callback;)V

    .line 181
    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarContainer;->lp:Landroid/graphics/drawable/Drawable;

    invoke-virtual {p0, v1}, Lcom/miui/internal/widget/ActionBarContainer;->unscheduleDrawable(Landroid/graphics/drawable/Drawable;)V

    .line 183
    :goto_0
    iput-object p1, p0, Lcom/miui/internal/widget/ActionBarContainer;->lp:Landroid/graphics/drawable/Drawable;

    .line 184
    if-eqz p1, :cond_1

    .line 185
    invoke-virtual {p1, p0}, Landroid/graphics/drawable/Drawable;->setCallback(Landroid/graphics/drawable/Drawable$Callback;)V

    .line 186
    if-nez v0, :cond_0

    .line 187
    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarContainer;->requestLayout()V

    .line 191
    :goto_1
    iput-boolean v2, p0, Lcom/miui/internal/widget/ActionBarContainer;->lE:Z

    .line 195
    :goto_2
    iget-boolean v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->lt:Z

    if-eqz v0, :cond_3

    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->ls:Landroid/graphics/drawable/Drawable;

    if-nez v0, :cond_2

    move v0, v2

    :goto_3
    invoke-virtual {p0, v0}, Lcom/miui/internal/widget/ActionBarContainer;->setWillNotDraw(Z)V

    .line 197
    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarContainer;->invalidate()V

    .line 198
    return-void

    .line 189
    :cond_0
    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarContainer;->lp:Landroid/graphics/drawable/Drawable;

    invoke-virtual {v1, v0}, Landroid/graphics/drawable/Drawable;->setBounds(Landroid/graphics/Rect;)V

    goto :goto_1

    .line 193
    :cond_1
    iput-boolean v3, p0, Lcom/miui/internal/widget/ActionBarContainer;->lE:Z

    goto :goto_2

    :cond_2
    move v0, v3

    .line 195
    goto :goto_3

    :cond_3
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->lp:Landroid/graphics/drawable/Drawable;

    if-nez v0, :cond_4

    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->lr:Landroid/graphics/drawable/Drawable;

    if-nez v0, :cond_4

    move v0, v2

    goto :goto_3

    :cond_4
    move v0, v3

    goto :goto_3

    :cond_5
    move-object v0, v1

    goto :goto_0
.end method

.method public setSplitBackground(Landroid/graphics/drawable/Drawable;)V
    .locals 4

    .prologue
    const/4 v0, 0x1

    const/4 v1, 0x0

    .line 217
    iget-object v2, p0, Lcom/miui/internal/widget/ActionBarContainer;->ls:Landroid/graphics/drawable/Drawable;

    if-eqz v2, :cond_0

    .line 218
    iget-object v2, p0, Lcom/miui/internal/widget/ActionBarContainer;->ls:Landroid/graphics/drawable/Drawable;

    const/4 v3, 0x0

    invoke-virtual {v2, v3}, Landroid/graphics/drawable/Drawable;->setCallback(Landroid/graphics/drawable/Drawable$Callback;)V

    .line 219
    iget-object v2, p0, Lcom/miui/internal/widget/ActionBarContainer;->ls:Landroid/graphics/drawable/Drawable;

    invoke-virtual {p0, v2}, Lcom/miui/internal/widget/ActionBarContainer;->unscheduleDrawable(Landroid/graphics/drawable/Drawable;)V

    .line 221
    :cond_0
    iput-object p1, p0, Lcom/miui/internal/widget/ActionBarContainer;->ls:Landroid/graphics/drawable/Drawable;

    .line 222
    if-eqz p1, :cond_1

    .line 223
    invoke-virtual {p1, p0}, Landroid/graphics/drawable/Drawable;->setCallback(Landroid/graphics/drawable/Drawable$Callback;)V

    .line 225
    :cond_1
    iget-boolean v2, p0, Lcom/miui/internal/widget/ActionBarContainer;->lt:Z

    if-eqz v2, :cond_4

    iget-object v2, p0, Lcom/miui/internal/widget/ActionBarContainer;->ls:Landroid/graphics/drawable/Drawable;

    if-nez v2, :cond_3

    :cond_2
    :goto_0
    invoke-virtual {p0, v0}, Lcom/miui/internal/widget/ActionBarContainer;->setWillNotDraw(Z)V

    .line 227
    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarContainer;->invalidate()V

    .line 228
    return-void

    :cond_3
    move v0, v1

    .line 225
    goto :goto_0

    :cond_4
    iget-object v2, p0, Lcom/miui/internal/widget/ActionBarContainer;->lp:Landroid/graphics/drawable/Drawable;

    if-nez v2, :cond_5

    iget-object v2, p0, Lcom/miui/internal/widget/ActionBarContainer;->lr:Landroid/graphics/drawable/Drawable;

    if-eqz v2, :cond_2

    :cond_5
    move v0, v1

    goto :goto_0
.end method

.method public setStackedBackground(Landroid/graphics/drawable/Drawable;)V
    .locals 4

    .prologue
    const/4 v0, 0x1

    const/4 v1, 0x0

    .line 201
    iget-object v2, p0, Lcom/miui/internal/widget/ActionBarContainer;->lr:Landroid/graphics/drawable/Drawable;

    if-eqz v2, :cond_0

    .line 202
    iget-object v2, p0, Lcom/miui/internal/widget/ActionBarContainer;->lr:Landroid/graphics/drawable/Drawable;

    const/4 v3, 0x0

    invoke-virtual {v2, v3}, Landroid/graphics/drawable/Drawable;->setCallback(Landroid/graphics/drawable/Drawable$Callback;)V

    .line 203
    iget-object v2, p0, Lcom/miui/internal/widget/ActionBarContainer;->lr:Landroid/graphics/drawable/Drawable;

    invoke-virtual {p0, v2}, Lcom/miui/internal/widget/ActionBarContainer;->unscheduleDrawable(Landroid/graphics/drawable/Drawable;)V

    .line 205
    :cond_0
    iput-object p1, p0, Lcom/miui/internal/widget/ActionBarContainer;->lr:Landroid/graphics/drawable/Drawable;

    .line 206
    if-eqz p1, :cond_1

    .line 207
    invoke-virtual {p1, p0}, Landroid/graphics/drawable/Drawable;->setCallback(Landroid/graphics/drawable/Drawable$Callback;)V

    .line 209
    :cond_1
    iget-boolean v2, p0, Lcom/miui/internal/widget/ActionBarContainer;->lt:Z

    if-eqz v2, :cond_5

    iget-object v2, p0, Lcom/miui/internal/widget/ActionBarContainer;->ls:Landroid/graphics/drawable/Drawable;

    if-nez v2, :cond_4

    :cond_2
    :goto_0
    invoke-virtual {p0, v0}, Lcom/miui/internal/widget/ActionBarContainer;->setWillNotDraw(Z)V

    .line 211
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->lm:Landroid/view/View;

    if-eqz v0, :cond_3

    .line 212
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->lm:Landroid/view/View;

    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarContainer;->lr:Landroid/graphics/drawable/Drawable;

    invoke-virtual {v0, v1}, Landroid/view/View;->setBackground(Landroid/graphics/drawable/Drawable;)V

    .line 214
    :cond_3
    return-void

    :cond_4
    move v0, v1

    .line 209
    goto :goto_0

    :cond_5
    iget-object v2, p0, Lcom/miui/internal/widget/ActionBarContainer;->lp:Landroid/graphics/drawable/Drawable;

    if-nez v2, :cond_6

    iget-object v2, p0, Lcom/miui/internal/widget/ActionBarContainer;->lr:Landroid/graphics/drawable/Drawable;

    if-eqz v2, :cond_2

    :cond_6
    move v0, v1

    goto :goto_0
.end method

.method public setTabContainer(Lcom/miui/internal/widget/ScrollingTabContainerView;)V
    .locals 2

    .prologue
    .line 305
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->lm:Landroid/view/View;

    if-eqz v0, :cond_0

    .line 306
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->lm:Landroid/view/View;

    invoke-virtual {p0, v0}, Lcom/miui/internal/widget/ActionBarContainer;->removeView(Landroid/view/View;)V

    .line 309
    :cond_0
    if-eqz p1, :cond_2

    .line 310
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->lr:Landroid/graphics/drawable/Drawable;

    invoke-virtual {p1, v0}, Lcom/miui/internal/widget/ScrollingTabContainerView;->setBackground(Landroid/graphics/drawable/Drawable;)V

    .line 311
    invoke-virtual {p0, p1}, Lcom/miui/internal/widget/ActionBarContainer;->addView(Landroid/view/View;)V

    .line 312
    invoke-virtual {p1}, Lcom/miui/internal/widget/ScrollingTabContainerView;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v0

    .line 313
    const/4 v1, -0x1

    iput v1, v0, Landroid/view/ViewGroup$LayoutParams;->width:I

    .line 314
    const/4 v1, -0x2

    iput v1, v0, Landroid/view/ViewGroup$LayoutParams;->height:I

    .line 315
    const/4 v0, 0x0

    invoke-virtual {p1, v0}, Lcom/miui/internal/widget/ScrollingTabContainerView;->setAllowCollapse(Z)V

    .line 317
    invoke-virtual {p1}, Lcom/miui/internal/widget/ScrollingTabContainerView;->getPaddingTop()I

    move-result v0

    iput v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->lz:I

    .line 321
    :cond_1
    :goto_0
    iput-object p1, p0, Lcom/miui/internal/widget/ActionBarContainer;->lm:Landroid/view/View;

    .line 322
    return-void

    .line 318
    :cond_2
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->lm:Landroid/view/View;

    if-eqz v0, :cond_1

    .line 319
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->lm:Landroid/view/View;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Landroid/view/View;->setBackground(Landroid/graphics/drawable/Drawable;)V

    goto :goto_0
.end method

.method public setTransitioning(Z)V
    .locals 1

    .prologue
    .line 277
    iput-boolean p1, p0, Lcom/miui/internal/widget/ActionBarContainer;->ll:Z

    .line 278
    if-eqz p1, :cond_0

    const/high16 v0, 0x60000

    :goto_0
    invoke-virtual {p0, v0}, Lcom/miui/internal/widget/ActionBarContainer;->setDescendantFocusability(I)V

    .line 280
    return-void

    .line 278
    :cond_0
    const/high16 v0, 0x40000

    goto :goto_0
.end method

.method public setVisibility(I)V
    .locals 3

    .prologue
    const/4 v1, 0x0

    .line 236
    invoke-super {p0, p1}, Landroid/widget/FrameLayout;->setVisibility(I)V

    .line 237
    if-nez p1, :cond_3

    const/4 v0, 0x1

    .line 238
    :goto_0
    iget-object v2, p0, Lcom/miui/internal/widget/ActionBarContainer;->lp:Landroid/graphics/drawable/Drawable;

    if-eqz v2, :cond_0

    .line 239
    iget-object v2, p0, Lcom/miui/internal/widget/ActionBarContainer;->lp:Landroid/graphics/drawable/Drawable;

    invoke-virtual {v2, v0, v1}, Landroid/graphics/drawable/Drawable;->setVisible(ZZ)Z

    .line 241
    :cond_0
    iget-object v2, p0, Lcom/miui/internal/widget/ActionBarContainer;->lr:Landroid/graphics/drawable/Drawable;

    if-eqz v2, :cond_1

    .line 242
    iget-object v2, p0, Lcom/miui/internal/widget/ActionBarContainer;->lr:Landroid/graphics/drawable/Drawable;

    invoke-virtual {v2, v0, v1}, Landroid/graphics/drawable/Drawable;->setVisible(ZZ)Z

    .line 244
    :cond_1
    iget-object v2, p0, Lcom/miui/internal/widget/ActionBarContainer;->ls:Landroid/graphics/drawable/Drawable;

    if-eqz v2, :cond_2

    .line 245
    iget-object v2, p0, Lcom/miui/internal/widget/ActionBarContainer;->ls:Landroid/graphics/drawable/Drawable;

    invoke-virtual {v2, v0, v1}, Landroid/graphics/drawable/Drawable;->setVisible(ZZ)Z

    .line 247
    :cond_2
    return-void

    :cond_3
    move v0, v1

    .line 237
    goto :goto_0
.end method

.method public show(Z)V
    .locals 5

    .prologue
    const/4 v4, 0x0

    const/4 v3, 0x0

    .line 506
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->lo:Landroid/animation/Animator;

    if-eqz v0, :cond_0

    .line 507
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->lo:Landroid/animation/Animator;

    invoke-virtual {v0}, Landroid/animation/Animator;->cancel()V

    .line 510
    :cond_0
    invoke-virtual {p0, v3}, Lcom/miui/internal/widget/ActionBarContainer;->setVisibility(I)V

    .line 511
    if-eqz p1, :cond_2

    .line 512
    iget-boolean v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->lt:Z

    if-eqz v0, :cond_1

    .line 513
    const-string v0, "TranslationY"

    const/4 v1, 0x2

    new-array v1, v1, [F

    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarContainer;->getHeight()I

    move-result v2

    int-to-float v2, v2

    aput v2, v1, v3

    const/4 v2, 0x1

    aput v4, v1, v2

    invoke-static {p0, v0, v1}, Landroid/animation/ObjectAnimator;->ofFloat(Ljava/lang/Object;Ljava/lang/String;[F)Landroid/animation/ObjectAnimator;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->lo:Landroid/animation/Animator;

    .line 514
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->lo:Landroid/animation/Animator;

    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarContainer;->getContext()Landroid/content/Context;

    move-result-object v1

    invoke-virtual {v1}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    const/high16 v2, 0x10e0000

    invoke-virtual {v1, v2}, Landroid/content/res/Resources;->getInteger(I)I

    move-result v1

    int-to-long v1, v1

    invoke-virtual {v0, v1, v2}, Landroid/animation/Animator;->setDuration(J)Landroid/animation/Animator;

    .line 516
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->lo:Landroid/animation/Animator;

    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarContainer;->lG:Landroid/animation/AnimatorListenerAdapter;

    invoke-virtual {v0, v1}, Landroid/animation/Animator;->addListener(Landroid/animation/Animator$AnimatorListener;)V

    .line 517
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->lo:Landroid/animation/Animator;

    invoke-virtual {v0}, Landroid/animation/Animator;->start()V

    .line 519
    invoke-virtual {p0, v3}, Lcom/miui/internal/widget/ActionBarContainer;->getChildAt(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/view/menu/ActionMenuView;

    .line 520
    invoke-virtual {v0}, Lcom/miui/internal/view/menu/ActionMenuView;->startLayoutAnimation()V

    .line 525
    :cond_1
    :goto_0
    return-void

    .line 523
    :cond_2
    invoke-virtual {p0, v4}, Lcom/miui/internal/widget/ActionBarContainer;->setTranslationY(F)V

    goto :goto_0
.end method

.method public startActionModeForChild(Landroid/view/View;Landroid/view/ActionMode$Callback;)Landroid/view/ActionMode;
    .locals 1

    .prologue
    .line 345
    const/4 v0, 0x0

    return-object v0
.end method

.method protected verifyDrawable(Landroid/graphics/drawable/Drawable;)Z
    .locals 1

    .prologue
    .line 251
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->lp:Landroid/graphics/drawable/Drawable;

    if-ne p1, v0, :cond_0

    iget-boolean v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->lt:Z

    if-eqz v0, :cond_3

    :cond_0
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->lr:Landroid/graphics/drawable/Drawable;

    if-ne p1, v0, :cond_1

    iget-boolean v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->lu:Z

    if-nez v0, :cond_3

    :cond_1
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->ls:Landroid/graphics/drawable/Drawable;

    if-ne p1, v0, :cond_2

    iget-boolean v0, p0, Lcom/miui/internal/widget/ActionBarContainer;->lt:Z

    if-nez v0, :cond_3

    :cond_2
    invoke-super {p0, p1}, Landroid/widget/FrameLayout;->verifyDrawable(Landroid/graphics/drawable/Drawable;)Z

    move-result v0

    if-eqz v0, :cond_4

    :cond_3
    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_4
    const/4 v0, 0x0

    goto :goto_0
.end method
