.class public Lmiui/widget/NavigationLayout;
.super Landroid/view/ViewGroup;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lmiui/widget/NavigationLayout$a;,
        Lmiui/widget/NavigationLayout$c;,
        Lmiui/widget/NavigationLayout$LayoutParams;,
        Lmiui/widget/NavigationLayout$b;,
        Lmiui/widget/NavigationLayout$NavigationListener;
    }
.end annotation


# static fields
.field public static final ABSOLUTE:I = 0x0

.field public static final DRAWER_ENABLED_LANDSCAPE:I = 0x2

.field public static final DRAWER_ENABLED_NONE:I = 0x0

.field public static final DRAWER_ENABLED_PORTRAIT:I = 0x1

.field public static final DRAWER_MODE_CONTENT_SQUEEZED:I = 0x2

.field public static final DRAWER_MODE_OVERLAY:I = 0x0

.field public static final DRAWER_MODE_PUSHED_AWAY:I = 0x1

.field public static final LOCK_MODE_LOCKED_CLOSED:I = 0x1

.field public static final LOCK_MODE_LOCKED_OPEN:I = 0x2

.field public static final LOCK_MODE_UNLOCKED:I = 0x0

.field public static final RELATIVE_TO_PARENT:I = 0x1

.field public static final STATE_DRAGGING:I = 0x1

.field public static final STATE_IDLE:I = 0x0

.field public static final STATE_SETTLING:I = 0x2

.field private static final nD:I = 0x190

.field private static final nE:I = -0x330a0809

.field private static final nF:I = 0x96

.field private static final nG:F = 0.5f


# instance fields
.field private final nH:Lcom/miui/internal/widget/ViewDragHelper;

.field private nI:I

.field private nJ:Z

.field private nK:I

.field private nL:Landroid/view/View;

.field private nM:Landroid/view/View;

.field private nN:Landroid/graphics/Rect;

.field private nO:Lmiui/widget/NavigationLayout$c;

.field private nP:Lmiui/widget/NavigationLayout$c;

.field private nQ:Landroid/graphics/drawable/Drawable;

.field private nR:I

.field private nS:Landroid/graphics/drawable/Drawable;

.field private nT:F

.field private nU:F

.field private nV:Z

.field private nW:I

.field private nX:Landroid/graphics/Paint;

.field private nY:Lmiui/widget/NavigationLayout$NavigationListener;

.field private nZ:Z

.field private oa:I

.field private ob:Ljava/lang/Runnable;

.field private oc:F

.field private od:F

.field private oe:Landroid/animation/ValueAnimator;

.field private of:Landroid/animation/ValueAnimator$AnimatorUpdateListener;

.field private og:Landroid/view/View;


# direct methods
.method public constructor <init>(Landroid/content/Context;)V
    .locals 1

    .prologue
    .line 196
    const/4 v0, 0x0

    invoke-direct {p0, p1, v0}, Lmiui/widget/NavigationLayout;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    .line 197
    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 1

    .prologue
    .line 200
    sget v0, Lmiui/R$attr;->navigationLayoutStyle:I

    invoke-direct {p0, p1, p2, v0}, Lmiui/widget/NavigationLayout;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    .line 201
    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V
    .locals 5

    .prologue
    const v2, -0x330a0809

    const/4 v4, 0x1

    const/4 v3, 0x0

    .line 204
    invoke-direct {p0, p1, p2, p3}, Landroid/view/ViewGroup;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    .line 137
    new-instance v0, Landroid/graphics/Rect;

    invoke-direct {v0}, Landroid/graphics/Rect;-><init>()V

    iput-object v0, p0, Lmiui/widget/NavigationLayout;->nN:Landroid/graphics/Rect;

    .line 155
    iput v2, p0, Lmiui/widget/NavigationLayout;->nW:I

    .line 157
    new-instance v0, Landroid/graphics/Paint;

    invoke-direct {v0}, Landroid/graphics/Paint;-><init>()V

    iput-object v0, p0, Lmiui/widget/NavigationLayout;->nX:Landroid/graphics/Paint;

    .line 161
    iput-boolean v4, p0, Lmiui/widget/NavigationLayout;->nZ:Z

    .line 163
    iput v3, p0, Lmiui/widget/NavigationLayout;->oa:I

    .line 165
    new-instance v0, Lmiui/widget/f;

    invoke-direct {v0, p0}, Lmiui/widget/f;-><init>(Lmiui/widget/NavigationLayout;)V

    iput-object v0, p0, Lmiui/widget/NavigationLayout;->ob:Ljava/lang/Runnable;

    .line 205
    sget-object v0, Lmiui/R$styleable;->NavigationLayout:[I

    invoke-virtual {p1, p2, v0, p3, v3}, Landroid/content/Context;->obtainStyledAttributes(Landroid/util/AttributeSet;[III)Landroid/content/res/TypedArray;

    move-result-object v0

    .line 209
    invoke-virtual {v0, v3}, Landroid/content/res/TypedArray;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v1

    .line 210
    if-eqz v1, :cond_0

    .line 211
    invoke-virtual {p0, v1}, Lmiui/widget/NavigationLayout;->setDivider(Landroid/graphics/drawable/Drawable;)V

    .line 214
    :cond_0
    const/4 v1, 0x5

    invoke-virtual {v0, v1}, Landroid/content/res/TypedArray;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v1

    .line 215
    if-eqz v1, :cond_1

    .line 216
    invoke-virtual {p0, v1}, Lmiui/widget/NavigationLayout;->setNavigationShadow(Landroid/graphics/drawable/Drawable;)V

    .line 219
    :cond_1
    invoke-virtual {v0, v4, v3}, Landroid/content/res/TypedArray;->getDimensionPixelSize(II)I

    move-result v1

    .line 221
    if-eqz v1, :cond_2

    .line 222
    invoke-virtual {p0, v1}, Lmiui/widget/NavigationLayout;->setDividerWidth(I)V

    .line 225
    :cond_2
    const/4 v1, 0x6

    invoke-virtual {v0, v1, v2}, Landroid/content/res/TypedArray;->getColor(II)I

    move-result v1

    iput v1, p0, Lmiui/widget/NavigationLayout;->nW:I

    .line 227
    const/4 v1, 0x4

    invoke-virtual {v0, v1, v3}, Landroid/content/res/TypedArray;->getInt(II)I

    move-result v1

    iput v1, p0, Lmiui/widget/NavigationLayout;->nI:I

    .line 230
    const/4 v1, 0x2

    invoke-virtual {v0, v1}, Landroid/content/res/TypedArray;->peekValue(I)Landroid/util/TypedValue;

    move-result-object v1

    invoke-virtual {p0}, Lmiui/widget/NavigationLayout;->getResources()Landroid/content/res/Resources;

    move-result-object v2

    invoke-static {v1, v2}, Lmiui/widget/NavigationLayout$c;->a(Landroid/util/TypedValue;Landroid/content/res/Resources;)Lmiui/widget/NavigationLayout$c;

    move-result-object v1

    iput-object v1, p0, Lmiui/widget/NavigationLayout;->nO:Lmiui/widget/NavigationLayout$c;

    .line 233
    const/4 v1, 0x3

    invoke-virtual {v0, v1}, Landroid/content/res/TypedArray;->peekValue(I)Landroid/util/TypedValue;

    move-result-object v1

    invoke-virtual {p0}, Lmiui/widget/NavigationLayout;->getResources()Landroid/content/res/Resources;

    move-result-object v2

    invoke-static {v1, v2}, Lmiui/widget/NavigationLayout$c;->a(Landroid/util/TypedValue;Landroid/content/res/Resources;)Lmiui/widget/NavigationLayout$c;

    move-result-object v1

    iput-object v1, p0, Lmiui/widget/NavigationLayout;->nP:Lmiui/widget/NavigationLayout$c;

    .line 237
    const/4 v1, 0x7

    invoke-virtual {v0, v1, v3}, Landroid/content/res/TypedArray;->getInt(II)I

    move-result v1

    iput v1, p0, Lmiui/widget/NavigationLayout;->nK:I

    .line 240
    invoke-virtual {v0}, Landroid/content/res/TypedArray;->recycle()V

    .line 242
    const/high16 v0, 0x3f000000

    new-instance v1, Lmiui/widget/NavigationLayout$a;

    const/4 v2, 0x0

    invoke-direct {v1, p0, v2}, Lmiui/widget/NavigationLayout$a;-><init>(Lmiui/widget/NavigationLayout;Lmiui/widget/f;)V

    invoke-static {p0, v0, v1}, Lcom/miui/internal/widget/ViewDragHelper;->create(Landroid/view/ViewGroup;FLcom/miui/internal/widget/ViewDragHelper$Callback;)Lcom/miui/internal/widget/ViewDragHelper;

    move-result-object v0

    iput-object v0, p0, Lmiui/widget/NavigationLayout;->nH:Lcom/miui/internal/widget/ViewDragHelper;

    .line 243
    iget-object v0, p0, Lmiui/widget/NavigationLayout;->nH:Lcom/miui/internal/widget/ViewDragHelper;

    invoke-virtual {v0, v4}, Lcom/miui/internal/widget/ViewDragHelper;->setEdgeTrackingEnabled(I)V

    .line 245
    invoke-virtual {p0}, Lmiui/widget/NavigationLayout;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    invoke-virtual {v0}, Landroid/content/res/Resources;->getDisplayMetrics()Landroid/util/DisplayMetrics;

    move-result-object v0

    iget v0, v0, Landroid/util/DisplayMetrics;->density:F

    .line 246
    iget-object v1, p0, Lmiui/widget/NavigationLayout;->nH:Lcom/miui/internal/widget/ViewDragHelper;

    const/high16 v2, 0x43c80000

    mul-float/2addr v0, v2

    invoke-virtual {v1, v0}, Lcom/miui/internal/widget/ViewDragHelper;->setMinVelocity(F)V

    .line 248
    invoke-virtual {p0, v4}, Lmiui/widget/NavigationLayout;->setFocusableInTouchMode(Z)V

    .line 249
    return-void
.end method

.method static synthetic a(Lmiui/widget/NavigationLayout;F)F
    .locals 0

    .prologue
    .line 39
    iput p1, p0, Lmiui/widget/NavigationLayout;->od:F

    return p1
.end method

.method static synthetic a(Lmiui/widget/NavigationLayout;)Landroid/view/View;
    .locals 1

    .prologue
    .line 39
    iget-object v0, p0, Lmiui/widget/NavigationLayout;->nL:Landroid/view/View;

    return-object v0
.end method

.method private a(Landroid/view/View;Z)V
    .locals 7

    .prologue
    const/4 v4, 0x2

    const/4 v6, 0x1

    const/4 v5, 0x0

    const/high16 v1, 0x3f800000

    const/4 v2, 0x0

    .line 338
    invoke-virtual {p1}, Landroid/view/View;->isEnabled()Z

    move-result v0

    if-ne v0, p2, :cond_1

    .line 363
    :cond_0
    :goto_0
    return-void

    .line 343
    :cond_1
    iget-object v0, p0, Lmiui/widget/NavigationLayout;->og:Landroid/view/View;

    if-eqz v0, :cond_2

    iget-object v0, p0, Lmiui/widget/NavigationLayout;->og:Landroid/view/View;

    if-eq v0, p1, :cond_2

    iget-object v0, p0, Lmiui/widget/NavigationLayout;->og:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->isEnabled()Z

    move-result v0

    if-nez v0, :cond_2

    if-eqz p2, :cond_0

    .line 348
    :cond_2
    invoke-virtual {p1, p2}, Landroid/view/View;->setEnabled(Z)V

    .line 350
    iget-object v0, p0, Lmiui/widget/NavigationLayout;->oe:Landroid/animation/ValueAnimator;

    if-eqz v0, :cond_5

    .line 351
    iget-object v0, p0, Lmiui/widget/NavigationLayout;->oe:Landroid/animation/ValueAnimator;

    invoke-virtual {v0}, Landroid/animation/ValueAnimator;->cancel()V

    .line 352
    iget-object v3, p0, Lmiui/widget/NavigationLayout;->oe:Landroid/animation/ValueAnimator;

    new-array v4, v4, [F

    if-eqz p2, :cond_3

    move v0, v1

    :goto_1
    aput v0, v4, v5

    if-eqz p2, :cond_4

    move v0, v2

    :goto_2
    aput v0, v4, v6

    invoke-virtual {v3, v4}, Landroid/animation/ValueAnimator;->setFloatValues([F)V

    .line 357
    :goto_3
    iput-object p1, p0, Lmiui/widget/NavigationLayout;->og:Landroid/view/View;

    .line 359
    iget-object v0, p0, Lmiui/widget/NavigationLayout;->oe:Landroid/animation/ValueAnimator;

    const-wide/16 v3, 0x1f4

    invoke-virtual {v0, v3, v4}, Landroid/animation/ValueAnimator;->setDuration(J)Landroid/animation/ValueAnimator;

    .line 360
    iget-object v0, p0, Lmiui/widget/NavigationLayout;->oe:Landroid/animation/ValueAnimator;

    invoke-direct {p0}, Lmiui/widget/NavigationLayout;->aT()Landroid/animation/ValueAnimator$AnimatorUpdateListener;

    move-result-object v3

    invoke-virtual {v0, v3}, Landroid/animation/ValueAnimator;->addUpdateListener(Landroid/animation/ValueAnimator$AnimatorUpdateListener;)V

    .line 361
    if-eqz p2, :cond_8

    :goto_4
    iput v1, p0, Lmiui/widget/NavigationLayout;->od:F

    .line 362
    iget-object v0, p0, Lmiui/widget/NavigationLayout;->oe:Landroid/animation/ValueAnimator;

    invoke-virtual {v0}, Landroid/animation/ValueAnimator;->start()V

    goto :goto_0

    :cond_3
    move v0, v2

    .line 352
    goto :goto_1

    :cond_4
    move v0, v1

    goto :goto_2

    .line 354
    :cond_5
    new-array v3, v4, [F

    if-eqz p2, :cond_6

    move v0, v1

    :goto_5
    aput v0, v3, v5

    if-eqz p2, :cond_7

    move v0, v2

    :goto_6
    aput v0, v3, v6

    invoke-static {v3}, Landroid/animation/ValueAnimator;->ofFloat([F)Landroid/animation/ValueAnimator;

    move-result-object v0

    iput-object v0, p0, Lmiui/widget/NavigationLayout;->oe:Landroid/animation/ValueAnimator;

    goto :goto_3

    :cond_6
    move v0, v2

    goto :goto_5

    :cond_7
    move v0, v1

    goto :goto_6

    :cond_8
    move v1, v2

    .line 361
    goto :goto_4
.end method

.method private aT()Landroid/animation/ValueAnimator$AnimatorUpdateListener;
    .locals 1

    .prologue
    .line 409
    iget-object v0, p0, Lmiui/widget/NavigationLayout;->of:Landroid/animation/ValueAnimator$AnimatorUpdateListener;

    if-nez v0, :cond_0

    .line 410
    new-instance v0, Lmiui/widget/NavigationLayout$1;

    invoke-direct {v0, p0}, Lmiui/widget/NavigationLayout$1;-><init>(Lmiui/widget/NavigationLayout;)V

    iput-object v0, p0, Lmiui/widget/NavigationLayout;->of:Landroid/animation/ValueAnimator$AnimatorUpdateListener;

    .line 421
    :cond_0
    iget-object v0, p0, Lmiui/widget/NavigationLayout;->of:Landroid/animation/ValueAnimator$AnimatorUpdateListener;

    return-object v0
.end method

.method private aU()V
    .locals 1

    .prologue
    .line 425
    iget-object v0, p0, Lmiui/widget/NavigationLayout;->nM:Landroid/view/View;

    if-nez v0, :cond_0

    .line 426
    sget v0, Lmiui/R$id;->content:I

    invoke-virtual {p0, v0}, Lmiui/widget/NavigationLayout;->findViewById(I)Landroid/view/View;

    move-result-object v0

    iput-object v0, p0, Lmiui/widget/NavigationLayout;->nM:Landroid/view/View;

    .line 427
    sget v0, Lmiui/R$id;->navigation:I

    invoke-virtual {p0, v0}, Lmiui/widget/NavigationLayout;->findViewById(I)Landroid/view/View;

    move-result-object v0

    iput-object v0, p0, Lmiui/widget/NavigationLayout;->nL:Landroid/view/View;

    .line 429
    :cond_0
    return-void
.end method

.method private aV()V
    .locals 8

    .prologue
    const/4 v7, 0x0

    const/4 v5, 0x0

    .line 738
    iget-boolean v0, p0, Lmiui/widget/NavigationLayout;->nV:Z

    if-nez v0, :cond_1

    .line 739
    invoke-static {}, Landroid/os/SystemClock;->uptimeMillis()J

    move-result-wide v0

    .line 740
    const/4 v4, 0x3

    move-wide v2, v0

    move v6, v5

    invoke-static/range {v0 .. v7}, Landroid/view/MotionEvent;->obtain(JJIFFI)Landroid/view/MotionEvent;

    move-result-object v0

    .line 742
    invoke-virtual {p0}, Lmiui/widget/NavigationLayout;->getChildCount()I

    move-result v1

    .line 743
    :goto_0
    if-ge v7, v1, :cond_0

    .line 744
    invoke-virtual {p0, v7}, Lmiui/widget/NavigationLayout;->getChildAt(I)Landroid/view/View;

    move-result-object v2

    invoke-virtual {v2, v0}, Landroid/view/View;->dispatchTouchEvent(Landroid/view/MotionEvent;)Z

    .line 743
    add-int/lit8 v7, v7, 0x1

    goto :goto_0

    .line 746
    :cond_0
    invoke-virtual {v0}, Landroid/view/MotionEvent;->recycle()V

    .line 747
    const/4 v0, 0x1

    iput-boolean v0, p0, Lmiui/widget/NavigationLayout;->nV:Z

    .line 749
    :cond_1
    return-void
.end method

.method private aW()Z
    .locals 1

    .prologue
    .line 752
    iget-object v0, p0, Lmiui/widget/NavigationLayout;->nL:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v0

    check-cast v0, Lmiui/widget/NavigationLayout$LayoutParams;

    .line 753
    iget-boolean v0, v0, Lmiui/widget/NavigationLayout$LayoutParams;->Bl:Z

    return v0
.end method

.method private aX()F
    .locals 1

    .prologue
    .line 771
    iget-object v0, p0, Lmiui/widget/NavigationLayout;->nL:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v0

    check-cast v0, Lmiui/widget/NavigationLayout$LayoutParams;

    .line 772
    iget v0, v0, Lmiui/widget/NavigationLayout$LayoutParams;->lg:F

    return v0
.end method

.method private aY()V
    .locals 2

    .prologue
    .line 857
    iget-object v0, p0, Lmiui/widget/NavigationLayout;->nL:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v0

    check-cast v0, Lmiui/widget/NavigationLayout$LayoutParams;

    .line 858
    iget-boolean v1, v0, Lmiui/widget/NavigationLayout$LayoutParams;->Bl:Z

    if-eqz v1, :cond_0

    .line 859
    const/4 v1, 0x0

    iput-boolean v1, v0, Lmiui/widget/NavigationLayout$LayoutParams;->Bl:Z

    .line 860
    const/4 v0, 0x1

    invoke-virtual {p0, v0}, Lmiui/widget/NavigationLayout;->closeNavigationDrawer(Z)V

    .line 862
    :cond_0
    return-void
.end method

.method static synthetic b(Lmiui/widget/NavigationLayout;)Lcom/miui/internal/widget/ViewDragHelper;
    .locals 1

    .prologue
    .line 39
    iget-object v0, p0, Lmiui/widget/NavigationLayout;->nH:Lcom/miui/internal/widget/ViewDragHelper;

    return-object v0
.end method

.method private b(F)V
    .locals 3

    .prologue
    .line 776
    iget-object v0, p0, Lmiui/widget/NavigationLayout;->nL:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v0

    check-cast v0, Lmiui/widget/NavigationLayout$LayoutParams;

    .line 777
    iget v1, v0, Lmiui/widget/NavigationLayout$LayoutParams;->lg:F

    cmpl-float v1, p1, v1

    if-nez v1, :cond_0

    .line 793
    :goto_0
    return-void

    .line 781
    :cond_0
    iput p1, v0, Lmiui/widget/NavigationLayout$LayoutParams;->lg:F

    .line 782
    iget-object v1, p0, Lmiui/widget/NavigationLayout;->nY:Lmiui/widget/NavigationLayout$NavigationListener;

    if-eqz v1, :cond_1

    .line 783
    iget-object v1, p0, Lmiui/widget/NavigationLayout;->nY:Lmiui/widget/NavigationLayout$NavigationListener;

    invoke-interface {v1, p1}, Lmiui/widget/NavigationLayout$NavigationListener;->onDrawerSlide(F)V

    .line 786
    :cond_1
    iget v1, p0, Lmiui/widget/NavigationLayout;->nK:I

    if-nez v1, :cond_2

    .line 787
    iget-object v0, p0, Lmiui/widget/NavigationLayout;->nM:Landroid/view/View;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Landroid/view/View;->setScrollX(I)V

    goto :goto_0

    .line 788
    :cond_2
    iget v1, p0, Lmiui/widget/NavigationLayout;->nK:I

    const/4 v2, 0x1

    if-ne v1, v2, :cond_3

    .line 789
    iget-object v1, p0, Lmiui/widget/NavigationLayout;->nM:Landroid/view/View;

    iget-object v2, p0, Lmiui/widget/NavigationLayout;->nL:Landroid/view/View;

    invoke-virtual {v2}, Landroid/view/View;->getWidth()I

    move-result v2

    neg-int v2, v2

    int-to-float v2, v2

    iget v0, v0, Lmiui/widget/NavigationLayout$LayoutParams;->lg:F

    mul-float/2addr v0, v2

    float-to-int v0, v0

    invoke-virtual {v1, v0}, Landroid/view/View;->setScrollX(I)V

    goto :goto_0

    .line 791
    :cond_3
    invoke-virtual {p0}, Lmiui/widget/NavigationLayout;->requestLayout()V

    goto :goto_0
.end method

.method static synthetic b(Lmiui/widget/NavigationLayout;F)V
    .locals 0

    .prologue
    .line 39
    invoke-direct {p0, p1}, Lmiui/widget/NavigationLayout;->b(F)V

    return-void
.end method

.method private c(Landroid/graphics/Canvas;)V
    .locals 6

    .prologue
    const/4 v2, 0x0

    .line 585
    iget v0, p0, Lmiui/widget/NavigationLayout;->oc:F

    cmpl-float v0, v0, v2

    if-lez v0, :cond_0

    .line 586
    iget v0, p0, Lmiui/widget/NavigationLayout;->nW:I

    const/high16 v1, -0x1000000

    and-int/2addr v0, v1

    ushr-int/lit8 v0, v0, 0x18

    .line 587
    int-to-float v0, v0

    iget v1, p0, Lmiui/widget/NavigationLayout;->oc:F

    mul-float/2addr v0, v1

    float-to-int v0, v0

    .line 588
    shl-int/lit8 v0, v0, 0x18

    iget v1, p0, Lmiui/widget/NavigationLayout;->nW:I

    const v3, 0xffffff

    and-int/2addr v1, v3

    or-int/2addr v0, v1

    .line 589
    iget-object v1, p0, Lmiui/widget/NavigationLayout;->nX:Landroid/graphics/Paint;

    invoke-virtual {v1, v0}, Landroid/graphics/Paint;->setColor(I)V

    .line 591
    iget-object v0, p0, Lmiui/widget/NavigationLayout;->nL:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->getRight()I

    move-result v0

    int-to-float v1, v0

    invoke-virtual {p0}, Lmiui/widget/NavigationLayout;->getWidth()I

    move-result v0

    int-to-float v3, v0

    invoke-virtual {p0}, Lmiui/widget/NavigationLayout;->getHeight()I

    move-result v0

    int-to-float v4, v0

    iget-object v5, p0, Lmiui/widget/NavigationLayout;->nX:Landroid/graphics/Paint;

    move-object v0, p1

    invoke-virtual/range {v0 .. v5}, Landroid/graphics/Canvas;->drawRect(FFFFLandroid/graphics/Paint;)V

    .line 593
    :cond_0
    return-void
.end method

.method static synthetic c(Lmiui/widget/NavigationLayout;)V
    .locals 0

    .prologue
    .line 39
    invoke-direct {p0}, Lmiui/widget/NavigationLayout;->aV()V

    return-void
.end method

.method static synthetic d(Lmiui/widget/NavigationLayout;)Landroid/view/View;
    .locals 1

    .prologue
    .line 39
    iget-object v0, p0, Lmiui/widget/NavigationLayout;->og:Landroid/view/View;

    return-object v0
.end method

.method private d(Landroid/graphics/Canvas;)V
    .locals 6

    .prologue
    .line 596
    iget v0, p0, Lmiui/widget/NavigationLayout;->od:F

    const/4 v1, 0x0

    cmpl-float v0, v0, v1

    if-lez v0, :cond_0

    iget-object v0, p0, Lmiui/widget/NavigationLayout;->og:Landroid/view/View;

    if-eqz v0, :cond_0

    .line 597
    iget v0, p0, Lmiui/widget/NavigationLayout;->nW:I

    const/high16 v1, -0x1000000

    and-int/2addr v0, v1

    ushr-int/lit8 v0, v0, 0x18

    .line 598
    int-to-float v0, v0

    iget v1, p0, Lmiui/widget/NavigationLayout;->od:F

    mul-float/2addr v0, v1

    float-to-int v0, v0

    .line 599
    shl-int/lit8 v0, v0, 0x18

    iget v1, p0, Lmiui/widget/NavigationLayout;->nW:I

    const v2, 0xffffff

    and-int/2addr v1, v2

    or-int/2addr v0, v1

    .line 600
    iget-object v1, p0, Lmiui/widget/NavigationLayout;->nX:Landroid/graphics/Paint;

    invoke-virtual {v1, v0}, Landroid/graphics/Paint;->setColor(I)V

    .line 601
    iget-object v0, p0, Lmiui/widget/NavigationLayout;->og:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->getLeft()I

    move-result v0

    int-to-float v1, v0

    iget-object v0, p0, Lmiui/widget/NavigationLayout;->og:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->getTop()I

    move-result v0

    int-to-float v2, v0

    iget-object v0, p0, Lmiui/widget/NavigationLayout;->og:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->getRight()I

    move-result v0

    int-to-float v3, v0

    iget-object v0, p0, Lmiui/widget/NavigationLayout;->og:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->getBottom()I

    move-result v0

    int-to-float v4, v0

    iget-object v5, p0, Lmiui/widget/NavigationLayout;->nX:Landroid/graphics/Paint;

    move-object v0, p1

    invoke-virtual/range {v0 .. v5}, Landroid/graphics/Canvas;->drawRect(FFFFLandroid/graphics/Paint;)V

    .line 604
    :cond_0
    return-void
.end method

.method static synthetic e(Lmiui/widget/NavigationLayout;)F
    .locals 1

    .prologue
    .line 39
    invoke-direct {p0}, Lmiui/widget/NavigationLayout;->aX()F

    move-result v0

    return v0
.end method

.method private e(Landroid/graphics/Canvas;)V
    .locals 5

    .prologue
    .line 607
    iget-object v0, p0, Lmiui/widget/NavigationLayout;->nS:Landroid/graphics/drawable/Drawable;

    if-nez v0, :cond_0

    .line 616
    :goto_0
    return-void

    .line 611
    :cond_0
    iget-object v0, p0, Lmiui/widget/NavigationLayout;->nL:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->getRight()I

    move-result v0

    .line 612
    iget-object v1, p0, Lmiui/widget/NavigationLayout;->nS:Landroid/graphics/drawable/Drawable;

    invoke-virtual {v1}, Landroid/graphics/drawable/Drawable;->getIntrinsicWidth()I

    move-result v1

    .line 613
    iget-object v2, p0, Lmiui/widget/NavigationLayout;->nS:Landroid/graphics/drawable/Drawable;

    iget-object v3, p0, Lmiui/widget/NavigationLayout;->nL:Landroid/view/View;

    invoke-virtual {v3}, Landroid/view/View;->getTop()I

    move-result v3

    add-int/2addr v1, v0

    iget-object v4, p0, Lmiui/widget/NavigationLayout;->nL:Landroid/view/View;

    invoke-virtual {v4}, Landroid/view/View;->getBottom()I

    move-result v4

    invoke-virtual {v2, v0, v3, v1, v4}, Landroid/graphics/drawable/Drawable;->setBounds(IIII)V

    .line 615
    iget-object v0, p0, Lmiui/widget/NavigationLayout;->nS:Landroid/graphics/drawable/Drawable;

    invoke-virtual {v0, p1}, Landroid/graphics/drawable/Drawable;->draw(Landroid/graphics/Canvas;)V

    goto :goto_0
.end method

.method static synthetic f(Lmiui/widget/NavigationLayout;)Ljava/lang/Runnable;
    .locals 1

    .prologue
    .line 39
    iget-object v0, p0, Lmiui/widget/NavigationLayout;->ob:Ljava/lang/Runnable;

    return-object v0
.end method

.method private f(Landroid/graphics/Canvas;)V
    .locals 6

    .prologue
    .line 619
    iget-object v0, p0, Lmiui/widget/NavigationLayout;->nN:Landroid/graphics/Rect;

    .line 620
    iget-object v1, p0, Lmiui/widget/NavigationLayout;->nL:Landroid/view/View;

    invoke-virtual {v1}, Landroid/view/View;->getMeasuredWidth()I

    move-result v1

    invoke-virtual {p0}, Lmiui/widget/NavigationLayout;->getPaddingTop()I

    move-result v2

    iget-object v3, p0, Lmiui/widget/NavigationLayout;->nL:Landroid/view/View;

    invoke-virtual {v3}, Landroid/view/View;->getMeasuredWidth()I

    move-result v3

    iget v4, p0, Lmiui/widget/NavigationLayout;->nR:I

    add-int/2addr v3, v4

    invoke-virtual {p0}, Lmiui/widget/NavigationLayout;->getBottom()I

    move-result v4

    invoke-virtual {p0}, Lmiui/widget/NavigationLayout;->getPaddingBottom()I

    move-result v5

    sub-int/2addr v4, v5

    invoke-virtual {v0, v1, v2, v3, v4}, Landroid/graphics/Rect;->set(IIII)V

    .line 625
    iget-object v1, p0, Lmiui/widget/NavigationLayout;->nQ:Landroid/graphics/drawable/Drawable;

    .line 627
    invoke-virtual {v1, v0}, Landroid/graphics/drawable/Drawable;->setBounds(Landroid/graphics/Rect;)V

    .line 628
    invoke-virtual {v1, p1}, Landroid/graphics/drawable/Drawable;->draw(Landroid/graphics/Canvas;)V

    .line 629
    return-void
.end method

.method static synthetic g(Lmiui/widget/NavigationLayout;)Lmiui/widget/NavigationLayout$NavigationListener;
    .locals 1

    .prologue
    .line 39
    iget-object v0, p0, Lmiui/widget/NavigationLayout;->nY:Lmiui/widget/NavigationLayout$NavigationListener;

    return-object v0
.end method


# virtual methods
.method protected checkLayoutParams(Landroid/view/ViewGroup$LayoutParams;)Z
    .locals 1

    .prologue
    .line 561
    instance-of v0, p1, Lmiui/widget/NavigationLayout$LayoutParams;

    return v0
.end method

.method public closeNavigationDrawer(Z)V
    .locals 4

    .prologue
    .line 870
    iget-boolean v0, p0, Lmiui/widget/NavigationLayout;->nZ:Z

    if-eqz v0, :cond_0

    .line 871
    const/4 p1, 0x0

    .line 873
    :cond_0
    if-eqz p1, :cond_3

    .line 874
    iget-boolean v0, p0, Lmiui/widget/NavigationLayout;->nJ:Z

    if-nez v0, :cond_1

    .line 886
    :goto_0
    return-void

    .line 877
    :cond_1
    iget-object v0, p0, Lmiui/widget/NavigationLayout;->nH:Lcom/miui/internal/widget/ViewDragHelper;

    iget-object v1, p0, Lmiui/widget/NavigationLayout;->nL:Landroid/view/View;

    iget-object v2, p0, Lmiui/widget/NavigationLayout;->nL:Landroid/view/View;

    invoke-virtual {v2}, Landroid/view/View;->getWidth()I

    move-result v2

    neg-int v2, v2

    iget-object v3, p0, Lmiui/widget/NavigationLayout;->nL:Landroid/view/View;

    invoke-virtual {v3}, Landroid/view/View;->getTop()I

    move-result v3

    invoke-virtual {v0, v1, v2, v3}, Lcom/miui/internal/widget/ViewDragHelper;->smoothSlideViewTo(Landroid/view/View;II)Z

    .line 884
    :cond_2
    :goto_1
    invoke-virtual {p0}, Lmiui/widget/NavigationLayout;->invalidate()V

    .line 885
    iget-object v0, p0, Lmiui/widget/NavigationLayout;->ob:Ljava/lang/Runnable;

    invoke-virtual {p0, v0}, Lmiui/widget/NavigationLayout;->removeCallbacks(Ljava/lang/Runnable;)Z

    goto :goto_0

    .line 879
    :cond_3
    const/4 v0, 0x0

    invoke-direct {p0, v0}, Lmiui/widget/NavigationLayout;->b(F)V

    .line 880
    iget-object v0, p0, Lmiui/widget/NavigationLayout;->nY:Lmiui/widget/NavigationLayout$NavigationListener;

    if-eqz v0, :cond_2

    .line 881
    iget-object v0, p0, Lmiui/widget/NavigationLayout;->nY:Lmiui/widget/NavigationLayout$NavigationListener;

    invoke-interface {v0}, Lmiui/widget/NavigationLayout$NavigationListener;->onDrawerClosed()V

    goto :goto_1
.end method

.method public computeScroll()V
    .locals 2

    .prologue
    .line 758
    invoke-super {p0}, Landroid/view/ViewGroup;->computeScroll()V

    .line 760
    iget v0, p0, Lmiui/widget/NavigationLayout;->nK:I

    const/4 v1, 0x2

    if-ne v0, v1, :cond_1

    .line 761
    const/4 v0, 0x0

    iput v0, p0, Lmiui/widget/NavigationLayout;->oc:F

    .line 765
    :goto_0
    iget-object v0, p0, Lmiui/widget/NavigationLayout;->nH:Lcom/miui/internal/widget/ViewDragHelper;

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Lcom/miui/internal/widget/ViewDragHelper;->continueSettling(Z)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 766
    invoke-virtual {p0}, Lmiui/widget/NavigationLayout;->postInvalidateOnAnimation()V

    .line 768
    :cond_0
    return-void

    .line 763
    :cond_1
    iget-object v0, p0, Lmiui/widget/NavigationLayout;->nL:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v0

    check-cast v0, Lmiui/widget/NavigationLayout$LayoutParams;

    iget v0, v0, Lmiui/widget/NavigationLayout$LayoutParams;->lg:F

    iput v0, p0, Lmiui/widget/NavigationLayout;->oc:F

    goto :goto_0
.end method

.method protected dispatchDraw(Landroid/graphics/Canvas;)V
    .locals 1

    .prologue
    .line 566
    invoke-super {p0, p1}, Landroid/view/ViewGroup;->dispatchDraw(Landroid/graphics/Canvas;)V

    .line 568
    iget-boolean v0, p0, Lmiui/widget/NavigationLayout;->nJ:Z

    if-eqz v0, :cond_0

    .line 569
    invoke-direct {p0, p1}, Lmiui/widget/NavigationLayout;->c(Landroid/graphics/Canvas;)V

    .line 570
    invoke-direct {p0, p1}, Lmiui/widget/NavigationLayout;->e(Landroid/graphics/Canvas;)V

    .line 575
    :goto_0
    return-void

    .line 572
    :cond_0
    invoke-direct {p0, p1}, Lmiui/widget/NavigationLayout;->f(Landroid/graphics/Canvas;)V

    .line 573
    invoke-direct {p0, p1}, Lmiui/widget/NavigationLayout;->d(Landroid/graphics/Canvas;)V

    goto :goto_0
.end method

.method public dispatchKeyEvent(Landroid/view/KeyEvent;)Z
    .locals 3

    .prologue
    const/4 v0, 0x1

    .line 899
    invoke-super {p0, p1}, Landroid/view/ViewGroup;->dispatchKeyEvent(Landroid/view/KeyEvent;)Z

    move-result v1

    if-eqz v1, :cond_0

    .line 910
    :goto_0
    return v0

    .line 903
    :cond_0
    invoke-virtual {p0}, Lmiui/widget/NavigationLayout;->getDrawerLockMode()I

    move-result v1

    if-nez v1, :cond_1

    iget-boolean v1, p0, Lmiui/widget/NavigationLayout;->nJ:Z

    if-eqz v1, :cond_1

    invoke-virtual {p1}, Landroid/view/KeyEvent;->getKeyCode()I

    move-result v1

    const/4 v2, 0x4

    if-ne v1, v2, :cond_1

    invoke-virtual {p1}, Landroid/view/KeyEvent;->getAction()I

    move-result v1

    if-ne v1, v0, :cond_1

    .line 905
    invoke-virtual {p0}, Lmiui/widget/NavigationLayout;->isNavigationDrawerOpen()Z

    move-result v1

    .line 906
    invoke-virtual {p0, v0}, Lmiui/widget/NavigationLayout;->closeNavigationDrawer(Z)V

    move v0, v1

    .line 907
    goto :goto_0

    .line 910
    :cond_1
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public dispatchTouchEvent(Landroid/view/MotionEvent;)Z
    .locals 6

    .prologue
    .line 393
    iget-object v0, p0, Lmiui/widget/NavigationLayout;->og:Landroid/view/View;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lmiui/widget/NavigationLayout;->og:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->isEnabled()Z

    move-result v0

    if-nez v0, :cond_0

    .line 394
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getX()F

    move-result v0

    float-to-int v0, v0

    .line 395
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getY()F

    move-result v1

    float-to-int v1, v1

    .line 396
    iget-object v2, p0, Lmiui/widget/NavigationLayout;->og:Landroid/view/View;

    invoke-virtual {v2}, Landroid/view/View;->getLeft()I

    move-result v2

    .line 397
    iget-object v3, p0, Lmiui/widget/NavigationLayout;->og:Landroid/view/View;

    invoke-virtual {v3}, Landroid/view/View;->getRight()I

    move-result v3

    .line 398
    iget-object v4, p0, Lmiui/widget/NavigationLayout;->og:Landroid/view/View;

    invoke-virtual {v4}, Landroid/view/View;->getTop()I

    move-result v4

    .line 399
    iget-object v5, p0, Lmiui/widget/NavigationLayout;->og:Landroid/view/View;

    invoke-virtual {v5}, Landroid/view/View;->getBottom()I

    move-result v5

    .line 400
    if-ge v2, v0, :cond_0

    if-ge v0, v3, :cond_0

    if-ge v4, v1, :cond_0

    if-ge v1, v5, :cond_0

    .line 401
    const/4 v0, 0x1

    .line 405
    :goto_0
    return v0

    :cond_0
    invoke-super {p0, p1}, Landroid/view/ViewGroup;->dispatchTouchEvent(Landroid/view/MotionEvent;)Z

    move-result v0

    goto :goto_0
.end method

.method protected generateDefaultLayoutParams()Landroid/view/ViewGroup$LayoutParams;
    .locals 2

    .prologue
    const/4 v1, -0x1

    .line 544
    new-instance v0, Lmiui/widget/NavigationLayout$LayoutParams;

    invoke-direct {v0, v1, v1}, Lmiui/widget/NavigationLayout$LayoutParams;-><init>(II)V

    return-object v0
.end method

.method public generateLayoutParams(Landroid/util/AttributeSet;)Landroid/view/ViewGroup$LayoutParams;
    .locals 2

    .prologue
    .line 550
    new-instance v0, Lmiui/widget/NavigationLayout$LayoutParams;

    invoke-virtual {p0}, Lmiui/widget/NavigationLayout;->getContext()Landroid/content/Context;

    move-result-object v1

    invoke-direct {v0, v1, p1}, Lmiui/widget/NavigationLayout$LayoutParams;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    return-object v0
.end method

.method protected generateLayoutParams(Landroid/view/ViewGroup$LayoutParams;)Landroid/view/ViewGroup$LayoutParams;
    .locals 1

    .prologue
    .line 556
    new-instance v0, Lmiui/widget/NavigationLayout$LayoutParams;

    invoke-direct {v0, p1}, Lmiui/widget/NavigationLayout$LayoutParams;-><init>(Landroid/view/ViewGroup$LayoutParams;)V

    return-object v0
.end method

.method public getDividerWidth()I
    .locals 1

    .prologue
    .line 322
    iget v0, p0, Lmiui/widget/NavigationLayout;->nR:I

    return v0
.end method

.method public getDrawerEnabledOrientation()I
    .locals 1

    .prologue
    .line 268
    iget v0, p0, Lmiui/widget/NavigationLayout;->nI:I

    return v0
.end method

.method public getDrawerLockMode()I
    .locals 1

    .prologue
    .line 830
    iget v0, p0, Lmiui/widget/NavigationLayout;->oa:I

    return v0
.end method

.method public isNavigationDrawerOpen()Z
    .locals 2

    .prologue
    .line 894
    iget-object v0, p0, Lmiui/widget/NavigationLayout;->nL:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v0

    check-cast v0, Lmiui/widget/NavigationLayout$LayoutParams;

    iget v0, v0, Lmiui/widget/NavigationLayout$LayoutParams;->lg:F

    const/high16 v1, 0x3f800000

    cmpl-float v0, v0, v1

    if-nez v0, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method protected onFinishInflate()V
    .locals 0

    .prologue
    .line 433
    invoke-super {p0}, Landroid/view/ViewGroup;->onFinishInflate()V

    .line 434
    invoke-direct {p0}, Lmiui/widget/NavigationLayout;->aU()V

    .line 435
    return-void
.end method

.method public onInterceptTouchEvent(Landroid/view/MotionEvent;)Z
    .locals 7

    .prologue
    const/4 v1, 0x1

    const/4 v2, 0x0

    .line 633
    iget-object v0, p0, Lmiui/widget/NavigationLayout;->nH:Lcom/miui/internal/widget/ViewDragHelper;

    invoke-virtual {v0, p1}, Lcom/miui/internal/widget/ViewDragHelper;->shouldInterceptTouchEvent(Landroid/view/MotionEvent;)Z

    move-result v3

    .line 634
    iget-boolean v0, p0, Lmiui/widget/NavigationLayout;->nJ:Z

    if-eqz v0, :cond_0

    iget v0, p0, Lmiui/widget/NavigationLayout;->oa:I

    if-eqz v0, :cond_2

    .line 635
    :cond_0
    invoke-super {p0, p1}, Landroid/view/ViewGroup;->onInterceptTouchEvent(Landroid/view/MotionEvent;)Z

    move-result v2

    .line 668
    :cond_1
    :goto_0
    return v2

    .line 640
    :cond_2
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getActionMasked()I

    move-result v0

    packed-switch v0, :pswitch_data_0

    :cond_3
    :goto_1
    move v0, v2

    .line 668
    :goto_2
    if-nez v3, :cond_4

    if-nez v0, :cond_4

    invoke-direct {p0}, Lmiui/widget/NavigationLayout;->aW()Z

    move-result v0

    if-nez v0, :cond_4

    iget-boolean v0, p0, Lmiui/widget/NavigationLayout;->nV:Z

    if-eqz v0, :cond_1

    :cond_4
    move v2, v1

    goto :goto_0

    .line 642
    :pswitch_0
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getX()F

    move-result v0

    .line 643
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getY()F

    move-result v4

    .line 644
    iput v0, p0, Lmiui/widget/NavigationLayout;->nT:F

    .line 645
    iput v4, p0, Lmiui/widget/NavigationLayout;->nU:F

    .line 646
    invoke-direct {p0}, Lmiui/widget/NavigationLayout;->aX()F

    move-result v5

    const/4 v6, 0x0

    cmpl-float v5, v5, v6

    if-lez v5, :cond_5

    iget-object v5, p0, Lmiui/widget/NavigationLayout;->nH:Lcom/miui/internal/widget/ViewDragHelper;

    float-to-int v0, v0

    float-to-int v4, v4

    invoke-virtual {v5, v0, v4}, Lcom/miui/internal/widget/ViewDragHelper;->findTopChildUnder(II)Landroid/view/View;

    move-result-object v0

    iget-object v4, p0, Lmiui/widget/NavigationLayout;->nM:Landroid/view/View;

    if-ne v0, v4, :cond_5

    move v0, v1

    .line 649
    :goto_3
    iput-boolean v2, p0, Lmiui/widget/NavigationLayout;->nV:Z

    goto :goto_2

    .line 654
    :pswitch_1
    iget-object v0, p0, Lmiui/widget/NavigationLayout;->nH:Lcom/miui/internal/widget/ViewDragHelper;

    const/4 v4, 0x3

    invoke-virtual {v0, v4}, Lcom/miui/internal/widget/ViewDragHelper;->checkTouchSlop(I)Z

    move-result v0

    if-eqz v0, :cond_3

    .line 655
    iget-object v0, p0, Lmiui/widget/NavigationLayout;->ob:Ljava/lang/Runnable;

    invoke-virtual {p0, v0}, Lmiui/widget/NavigationLayout;->removeCallbacks(Ljava/lang/Runnable;)Z

    move v0, v2

    goto :goto_2

    .line 662
    :pswitch_2
    iget-object v0, p0, Lmiui/widget/NavigationLayout;->ob:Ljava/lang/Runnable;

    invoke-virtual {p0, v0}, Lmiui/widget/NavigationLayout;->removeCallbacks(Ljava/lang/Runnable;)Z

    .line 663
    invoke-direct {p0}, Lmiui/widget/NavigationLayout;->aY()V

    .line 664
    iput-boolean v2, p0, Lmiui/widget/NavigationLayout;->nV:Z

    goto :goto_1

    :cond_5
    move v0, v2

    goto :goto_3

    .line 640
    nop

    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_0
        :pswitch_2
        :pswitch_1
        :pswitch_2
    .end packed-switch
.end method

.method protected onLayout(ZIIII)V
    .locals 7

    .prologue
    const/4 v6, 0x0

    .line 506
    iget-boolean v0, p0, Lmiui/widget/NavigationLayout;->nJ:Z

    if-eqz v0, :cond_2

    .line 507
    iget-object v0, p0, Lmiui/widget/NavigationLayout;->nL:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->getMeasuredWidth()I

    move-result v2

    .line 508
    iget-object v0, p0, Lmiui/widget/NavigationLayout;->nL:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v0

    check-cast v0, Lmiui/widget/NavigationLayout$LayoutParams;

    .line 509
    neg-int v1, v2

    int-to-float v1, v1

    int-to-float v3, v2

    iget v4, v0, Lmiui/widget/NavigationLayout$LayoutParams;->lg:F

    mul-float/2addr v3, v4

    add-float/2addr v1, v3

    float-to-int v1, v1

    .line 510
    iget-object v3, p0, Lmiui/widget/NavigationLayout;->nL:Landroid/view/View;

    add-int v4, v1, v2

    iget-object v5, p0, Lmiui/widget/NavigationLayout;->nL:Landroid/view/View;

    invoke-virtual {v5}, Landroid/view/View;->getMeasuredHeight()I

    move-result v5

    add-int/2addr v5, p3

    invoke-virtual {v3, v1, p3, v4, v5}, Landroid/view/View;->layout(IIII)V

    .line 513
    iget-object v1, p0, Lmiui/widget/NavigationLayout;->nM:Landroid/view/View;

    invoke-virtual {v1}, Landroid/view/View;->getMeasuredWidth()I

    move-result v1

    add-int/2addr v1, p2

    .line 515
    iget v3, p0, Lmiui/widget/NavigationLayout;->nK:I

    const/4 v4, 0x1

    if-ne v3, v4, :cond_0

    .line 516
    iget-object v3, p0, Lmiui/widget/NavigationLayout;->nM:Landroid/view/View;

    neg-int v2, v2

    int-to-float v2, v2

    iget v0, v0, Lmiui/widget/NavigationLayout$LayoutParams;->lg:F

    mul-float/2addr v0, v2

    float-to-int v0, v0

    invoke-virtual {v3, v0}, Landroid/view/View;->setScrollX(I)V

    move p4, v1

    .line 524
    :goto_0
    iget-object v0, p0, Lmiui/widget/NavigationLayout;->nM:Landroid/view/View;

    iget-object v1, p0, Lmiui/widget/NavigationLayout;->nM:Landroid/view/View;

    invoke-virtual {v1}, Landroid/view/View;->getMeasuredHeight()I

    move-result v1

    add-int/2addr v1, p3

    invoke-virtual {v0, p2, p3, p4, v1}, Landroid/view/View;->layout(IIII)V

    .line 540
    :goto_1
    return-void

    .line 517
    :cond_0
    iget v3, p0, Lmiui/widget/NavigationLayout;->nK:I

    if-nez v3, :cond_1

    .line 518
    iget-object v0, p0, Lmiui/widget/NavigationLayout;->nM:Landroid/view/View;

    invoke-virtual {v0, v6}, Landroid/view/View;->setScrollX(I)V

    move p4, v1

    goto :goto_0

    .line 519
    :cond_1
    iget v3, p0, Lmiui/widget/NavigationLayout;->nK:I

    const/4 v4, 0x2

    if-ne v3, v4, :cond_3

    .line 520
    int-to-float v1, p2

    int-to-float v2, v2

    iget v0, v0, Lmiui/widget/NavigationLayout$LayoutParams;->lg:F

    mul-float/2addr v0, v2

    add-float/2addr v0, v1

    float-to-int p2, v0

    .line 521
    goto :goto_0

    .line 528
    :cond_2
    iget-object v0, p0, Lmiui/widget/NavigationLayout;->nL:Landroid/view/View;

    iget-object v1, p0, Lmiui/widget/NavigationLayout;->nL:Landroid/view/View;

    invoke-virtual {v1}, Landroid/view/View;->getMeasuredWidth()I

    move-result v1

    add-int/2addr v1, p2

    iget-object v2, p0, Lmiui/widget/NavigationLayout;->nL:Landroid/view/View;

    invoke-virtual {v2}, Landroid/view/View;->getMeasuredHeight()I

    move-result v2

    add-int/2addr v2, p3

    invoke-virtual {v0, p2, p3, v1, v2}, Landroid/view/View;->layout(IIII)V

    .line 531
    iget-object v0, p0, Lmiui/widget/NavigationLayout;->nL:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->getMeasuredWidth()I

    move-result v0

    add-int/2addr v0, p2

    iget v1, p0, Lmiui/widget/NavigationLayout;->nR:I

    add-int/2addr v0, v1

    .line 534
    iget-object v1, p0, Lmiui/widget/NavigationLayout;->nM:Landroid/view/View;

    iget-object v2, p0, Lmiui/widget/NavigationLayout;->nM:Landroid/view/View;

    invoke-virtual {v2}, Landroid/view/View;->getMeasuredWidth()I

    move-result v2

    add-int/2addr v2, v0

    iget-object v3, p0, Lmiui/widget/NavigationLayout;->nM:Landroid/view/View;

    invoke-virtual {v3}, Landroid/view/View;->getMeasuredHeight()I

    move-result v3

    add-int/2addr v3, p3

    invoke-virtual {v1, v0, p3, v2, v3}, Landroid/view/View;->layout(IIII)V

    .line 538
    iget-object v0, p0, Lmiui/widget/NavigationLayout;->nM:Landroid/view/View;

    invoke-virtual {v0, v6}, Landroid/view/View;->setScrollX(I)V

    goto :goto_1

    :cond_3
    move p4, v1

    goto :goto_0
.end method

.method protected onMeasure(II)V
    .locals 10

    .prologue
    const/high16 v9, 0x40000000

    const/4 v8, 0x2

    const/4 v6, 0x1

    const/high16 v7, 0x3f000000

    const/4 v5, 0x0

    .line 439
    iput-boolean v5, p0, Lmiui/widget/NavigationLayout;->nZ:Z

    .line 440
    invoke-direct {p0}, Lmiui/widget/NavigationLayout;->aU()V

    .line 442
    invoke-static {p1}, Landroid/view/View$MeasureSpec;->getSize(I)I

    move-result v1

    .line 443
    invoke-static {p2}, Landroid/view/View$MeasureSpec;->getSize(I)I

    move-result v3

    .line 445
    invoke-virtual {p0, v1, v3}, Lmiui/widget/NavigationLayout;->setMeasuredDimension(II)V

    .line 447
    invoke-virtual {p0}, Lmiui/widget/NavigationLayout;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    invoke-virtual {v0}, Landroid/content/res/Resources;->getConfiguration()Landroid/content/res/Configuration;

    move-result-object v0

    iget v0, v0, Landroid/content/res/Configuration;->orientation:I

    if-ne v0, v8, :cond_1

    move v2, v6

    .line 450
    :goto_0
    if-eqz v2, :cond_2

    iget-object v0, p0, Lmiui/widget/NavigationLayout;->nP:Lmiui/widget/NavigationLayout$c;

    .line 453
    :goto_1
    iget v4, v0, Lmiui/widget/NavigationLayout$c;->type:I

    packed-switch v4, :pswitch_data_0

    move v0, v5

    .line 461
    :goto_2
    invoke-static {v0, v9}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result v0

    .line 464
    invoke-static {v3, v9}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result v1

    .line 466
    iget-object v3, p0, Lmiui/widget/NavigationLayout;->nL:Landroid/view/View;

    invoke-virtual {p0, v3, v0, v1}, Lmiui/widget/NavigationLayout;->measureChild(Landroid/view/View;II)V

    .line 471
    iget v0, p0, Lmiui/widget/NavigationLayout;->nK:I

    if-ne v0, v8, :cond_4

    .line 472
    iget-object v0, p0, Lmiui/widget/NavigationLayout;->nL:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v0

    check-cast v0, Lmiui/widget/NavigationLayout$LayoutParams;

    .line 473
    iget v1, v0, Lmiui/widget/NavigationLayout$LayoutParams;->lg:F

    cmpl-float v1, v1, v7

    if-lez v1, :cond_3

    .line 474
    iget-object v1, p0, Lmiui/widget/NavigationLayout;->nL:Landroid/view/View;

    invoke-virtual {v1}, Landroid/view/View;->getMeasuredWidth()I

    move-result v1

    .line 478
    :goto_3
    iget v0, v0, Lmiui/widget/NavigationLayout$LayoutParams;->lg:F

    sub-float/2addr v0, v7

    invoke-static {v0}, Ljava/lang/Math;->abs(F)F

    move-result v0

    div-float/2addr v0, v7

    .line 479
    iget-object v3, p0, Lmiui/widget/NavigationLayout;->nM:Landroid/view/View;

    invoke-virtual {v3, v0}, Landroid/view/View;->setAlpha(F)V

    move v3, v1

    .line 483
    :goto_4
    iget v0, p0, Lmiui/widget/NavigationLayout;->nI:I

    and-int/lit8 v0, v0, 0x2

    if-eqz v0, :cond_5

    if-eqz v2, :cond_5

    .line 484
    iget-object v1, p0, Lmiui/widget/NavigationLayout;->nM:Landroid/view/View;

    move-object v0, p0

    move v2, p1

    move v4, p2

    invoke-virtual/range {v0 .. v5}, Lmiui/widget/NavigationLayout;->measureChildWithMargins(Landroid/view/View;IIII)V

    move v5, v6

    .line 493
    :goto_5
    iget-boolean v0, p0, Lmiui/widget/NavigationLayout;->nJ:Z

    if-eq v0, v5, :cond_0

    .line 494
    iput-boolean v5, p0, Lmiui/widget/NavigationLayout;->nJ:Z

    .line 495
    iget-object v0, p0, Lmiui/widget/NavigationLayout;->nY:Lmiui/widget/NavigationLayout$NavigationListener;

    if-eqz v0, :cond_0

    .line 496
    iget-object v0, p0, Lmiui/widget/NavigationLayout;->nY:Lmiui/widget/NavigationLayout$NavigationListener;

    invoke-interface {v0, v5}, Lmiui/widget/NavigationLayout$NavigationListener;->onDrawerEnableStateChange(Z)V

    .line 499
    :cond_0
    return-void

    :cond_1
    move v2, v5

    .line 447
    goto :goto_0

    .line 450
    :cond_2
    iget-object v0, p0, Lmiui/widget/NavigationLayout;->nO:Lmiui/widget/NavigationLayout$c;

    goto :goto_1

    .line 455
    :pswitch_0
    iget v0, v0, Lmiui/widget/NavigationLayout$c;->value:F

    float-to-int v0, v0

    .line 456
    goto :goto_2

    .line 458
    :pswitch_1
    iget v0, v0, Lmiui/widget/NavigationLayout$c;->value:F

    int-to-float v1, v1

    mul-float/2addr v0, v1

    float-to-int v0, v0

    goto :goto_2

    :cond_3
    move v1, v5

    .line 476
    goto :goto_3

    :cond_4
    move v3, v5

    .line 481
    goto :goto_4

    .line 485
    :cond_5
    iget v0, p0, Lmiui/widget/NavigationLayout;->nI:I

    and-int/lit8 v0, v0, 0x1

    if-eqz v0, :cond_6

    if-nez v2, :cond_6

    .line 486
    iget-object v1, p0, Lmiui/widget/NavigationLayout;->nM:Landroid/view/View;

    move-object v0, p0

    move v2, p1

    move v4, p2

    invoke-virtual/range {v0 .. v5}, Lmiui/widget/NavigationLayout;->measureChildWithMargins(Landroid/view/View;IIII)V

    move v5, v6

    goto :goto_5

    .line 489
    :cond_6
    iget-object v1, p0, Lmiui/widget/NavigationLayout;->nM:Landroid/view/View;

    iget-object v0, p0, Lmiui/widget/NavigationLayout;->nL:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->getMeasuredWidth()I

    move-result v0

    iget v2, p0, Lmiui/widget/NavigationLayout;->nR:I

    add-int v3, v0, v2

    move-object v0, p0

    move v2, p1

    move v4, p2

    invoke-virtual/range {v0 .. v5}, Lmiui/widget/NavigationLayout;->measureChildWithMargins(Landroid/view/View;IIII)V

    goto :goto_5

    .line 453
    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_0
        :pswitch_1
    .end packed-switch
.end method

.method protected onRestoreInstanceState(Landroid/os/Parcelable;)V
    .locals 3

    .prologue
    const/4 v2, 0x0

    .line 925
    check-cast p1, Lmiui/widget/NavigationLayout$b;

    .line 926
    invoke-virtual {p1}, Lmiui/widget/NavigationLayout$b;->getSuperState()Landroid/os/Parcelable;

    move-result-object v0

    invoke-super {p0, v0}, Landroid/view/ViewGroup;->onRestoreInstanceState(Landroid/os/Parcelable;)V

    .line 928
    iget v0, p1, Lmiui/widget/NavigationLayout$b;->lg:F

    invoke-direct {p0, v0}, Lmiui/widget/NavigationLayout;->b(F)V

    .line 929
    iget v0, p1, Lmiui/widget/NavigationLayout$b;->lg:F

    const/high16 v1, 0x3f000000

    cmpl-float v0, v0, v1

    if-ltz v0, :cond_0

    .line 930
    invoke-virtual {p0, v2}, Lmiui/widget/NavigationLayout;->openNavigationDrawer(Z)V

    .line 934
    :goto_0
    return-void

    .line 932
    :cond_0
    invoke-virtual {p0, v2}, Lmiui/widget/NavigationLayout;->closeNavigationDrawer(Z)V

    goto :goto_0
.end method

.method protected onSaveInstanceState()Landroid/os/Parcelable;
    .locals 3

    .prologue
    .line 915
    invoke-super {p0}, Landroid/view/ViewGroup;->onSaveInstanceState()Landroid/os/Parcelable;

    move-result-object v0

    .line 916
    new-instance v1, Lmiui/widget/NavigationLayout$b;

    const/4 v2, 0x0

    invoke-direct {v1, v0, v2}, Lmiui/widget/NavigationLayout$b;-><init>(Landroid/os/Parcelable;Lmiui/widget/f;)V

    .line 918
    invoke-direct {p0}, Lmiui/widget/NavigationLayout;->aX()F

    move-result v0

    iput v0, v1, Lmiui/widget/NavigationLayout$b;->lg:F

    .line 920
    return-object v1
.end method

.method public onTouchEvent(Landroid/view/MotionEvent;)Z
    .locals 8

    .prologue
    const/4 v1, 0x1

    const/4 v0, 0x0

    .line 674
    iget-object v2, p0, Lmiui/widget/NavigationLayout;->nH:Lcom/miui/internal/widget/ViewDragHelper;

    invoke-virtual {v2, p1}, Lcom/miui/internal/widget/ViewDragHelper;->processTouchEvent(Landroid/view/MotionEvent;)V

    .line 675
    iget-boolean v2, p0, Lmiui/widget/NavigationLayout;->nJ:Z

    if-eqz v2, :cond_0

    iget v2, p0, Lmiui/widget/NavigationLayout;->oa:I

    if-eqz v2, :cond_2

    .line 676
    :cond_0
    invoke-super {p0, p1}, Landroid/view/ViewGroup;->onTouchEvent(Landroid/view/MotionEvent;)Z

    move-result v1

    .line 722
    :cond_1
    :goto_0
    return v1

    .line 679
    :cond_2
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getActionMasked()I

    move-result v2

    .line 680
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getX()F

    move-result v3

    .line 681
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getY()F

    move-result v4

    .line 683
    packed-switch v2, :pswitch_data_0

    :pswitch_0
    goto :goto_0

    .line 685
    :pswitch_1
    iput v3, p0, Lmiui/widget/NavigationLayout;->nT:F

    .line 686
    iput v4, p0, Lmiui/widget/NavigationLayout;->nU:F

    .line 687
    iput-boolean v0, p0, Lmiui/widget/NavigationLayout;->nV:Z

    goto :goto_0

    .line 692
    :pswitch_2
    iget v2, p0, Lmiui/widget/NavigationLayout;->nT:F

    sub-float v2, v3, v2

    .line 693
    iget v5, p0, Lmiui/widget/NavigationLayout;->nU:F

    sub-float v5, v4, v5

    .line 694
    iget-object v6, p0, Lmiui/widget/NavigationLayout;->nH:Lcom/miui/internal/widget/ViewDragHelper;

    invoke-virtual {v6}, Lcom/miui/internal/widget/ViewDragHelper;->getTouchSlop()I

    move-result v6

    .line 697
    iget-object v7, p0, Lmiui/widget/NavigationLayout;->nH:Lcom/miui/internal/widget/ViewDragHelper;

    float-to-int v3, v3

    float-to-int v4, v4

    invoke-virtual {v7, v3, v4}, Lcom/miui/internal/widget/ViewDragHelper;->findTopChildUnder(II)Landroid/view/View;

    move-result-object v3

    .line 698
    if-eqz v3, :cond_4

    iget-object v4, p0, Lmiui/widget/NavigationLayout;->nM:Landroid/view/View;

    if-ne v3, v4, :cond_4

    .line 699
    mul-float/2addr v2, v2

    mul-float v3, v5, v5

    add-float/2addr v2, v3

    mul-int v3, v6, v6

    int-to-float v3, v3

    cmpg-float v2, v2, v3

    if-gez v2, :cond_4

    .line 700
    invoke-virtual {p0}, Lmiui/widget/NavigationLayout;->isNavigationDrawerOpen()Z

    move-result v2

    if-eqz v2, :cond_4

    .line 706
    :goto_1
    iget-object v2, p0, Lmiui/widget/NavigationLayout;->ob:Ljava/lang/Runnable;

    invoke-virtual {p0, v2}, Lmiui/widget/NavigationLayout;->removeCallbacks(Ljava/lang/Runnable;)Z

    .line 707
    if-eqz v0, :cond_3

    .line 708
    invoke-direct {p0}, Lmiui/widget/NavigationLayout;->aY()V

    goto :goto_0

    .line 710
    :cond_3
    iget v0, p0, Lmiui/widget/NavigationLayout;->oa:I

    if-nez v0, :cond_1

    .line 711
    invoke-virtual {p0, v1}, Lmiui/widget/NavigationLayout;->closeNavigationDrawer(Z)V

    goto :goto_0

    .line 716
    :pswitch_3
    invoke-direct {p0}, Lmiui/widget/NavigationLayout;->aY()V

    .line 717
    iput-boolean v0, p0, Lmiui/widget/NavigationLayout;->nV:Z

    goto :goto_0

    :cond_4
    move v0, v1

    goto :goto_1

    .line 683
    nop

    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_1
        :pswitch_2
        :pswitch_0
        :pswitch_3
    .end packed-switch
.end method

.method public openNavigationDrawer(Z)V
    .locals 4

    .prologue
    const/4 v0, 0x0

    .line 839
    iget-boolean v1, p0, Lmiui/widget/NavigationLayout;->nZ:Z

    if-eqz v1, :cond_0

    move p1, v0

    .line 842
    :cond_0
    if-eqz p1, :cond_3

    .line 843
    iget-boolean v1, p0, Lmiui/widget/NavigationLayout;->nJ:Z

    if-nez v1, :cond_1

    .line 854
    :goto_0
    return-void

    .line 846
    :cond_1
    iget-object v1, p0, Lmiui/widget/NavigationLayout;->nH:Lcom/miui/internal/widget/ViewDragHelper;

    iget-object v2, p0, Lmiui/widget/NavigationLayout;->nL:Landroid/view/View;

    iget-object v3, p0, Lmiui/widget/NavigationLayout;->nL:Landroid/view/View;

    invoke-virtual {v3}, Landroid/view/View;->getTop()I

    move-result v3

    invoke-virtual {v1, v2, v0, v3}, Lcom/miui/internal/widget/ViewDragHelper;->smoothSlideViewTo(Landroid/view/View;II)Z

    .line 853
    :cond_2
    :goto_1
    invoke-virtual {p0}, Lmiui/widget/NavigationLayout;->invalidate()V

    goto :goto_0

    .line 848
    :cond_3
    const/high16 v0, 0x3f800000

    invoke-direct {p0, v0}, Lmiui/widget/NavigationLayout;->b(F)V

    .line 849
    iget-object v0, p0, Lmiui/widget/NavigationLayout;->nY:Lmiui/widget/NavigationLayout$NavigationListener;

    if-eqz v0, :cond_2

    .line 850
    iget-object v0, p0, Lmiui/widget/NavigationLayout;->nY:Lmiui/widget/NavigationLayout$NavigationListener;

    invoke-interface {v0}, Lmiui/widget/NavigationLayout$NavigationListener;->onDrawerOpened()V

    goto :goto_1
.end method

.method public requestDisallowInterceptTouchEvent(Z)V
    .locals 2

    .prologue
    .line 727
    iget-object v0, p0, Lmiui/widget/NavigationLayout;->nH:Lcom/miui/internal/widget/ViewDragHelper;

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Lcom/miui/internal/widget/ViewDragHelper;->isEdgeTouched(I)Z

    move-result v0

    if-nez v0, :cond_0

    .line 729
    invoke-super {p0, p1}, Landroid/view/ViewGroup;->requestDisallowInterceptTouchEvent(Z)V

    .line 731
    :cond_0
    if-eqz p1, :cond_1

    .line 732
    invoke-direct {p0}, Lmiui/widget/NavigationLayout;->aY()V

    .line 734
    :cond_1
    return-void
.end method

.method public setContentEnabled(Z)V
    .locals 1

    .prologue
    .line 378
    iget-object v0, p0, Lmiui/widget/NavigationLayout;->nM:Landroid/view/View;

    invoke-direct {p0, v0, p1}, Lmiui/widget/NavigationLayout;->a(Landroid/view/View;Z)V

    .line 380
    return-void
.end method

.method public setDivider(Landroid/graphics/drawable/Drawable;)V
    .locals 1

    .prologue
    .line 308
    if-eqz p1, :cond_0

    .line 309
    invoke-virtual {p1}, Landroid/graphics/drawable/Drawable;->getIntrinsicHeight()I

    move-result v0

    iput v0, p0, Lmiui/widget/NavigationLayout;->nR:I

    .line 313
    :goto_0
    iput-object p1, p0, Lmiui/widget/NavigationLayout;->nQ:Landroid/graphics/drawable/Drawable;

    .line 314
    invoke-virtual {p0}, Lmiui/widget/NavigationLayout;->requestLayout()V

    .line 315
    invoke-virtual {p0}, Lmiui/widget/NavigationLayout;->invalidate()V

    .line 316
    return-void

    .line 311
    :cond_0
    const/4 v0, 0x0

    iput v0, p0, Lmiui/widget/NavigationLayout;->nR:I

    goto :goto_0
.end method

.method public setDividerWidth(I)V
    .locals 0

    .prologue
    .line 332
    iput p1, p0, Lmiui/widget/NavigationLayout;->nR:I

    .line 333
    invoke-virtual {p0}, Lmiui/widget/NavigationLayout;->requestLayout()V

    .line 334
    invoke-virtual {p0}, Lmiui/widget/NavigationLayout;->invalidate()V

    .line 335
    return-void
.end method

.method public setDrawerEnabledOrientation(I)V
    .locals 0

    .prologue
    .line 278
    iput p1, p0, Lmiui/widget/NavigationLayout;->nI:I

    .line 279
    invoke-virtual {p0}, Lmiui/widget/NavigationLayout;->requestLayout()V

    .line 280
    return-void
.end method

.method public setDrawerLockMode(I)V
    .locals 2

    .prologue
    const/4 v1, 0x0

    .line 805
    iget v0, p0, Lmiui/widget/NavigationLayout;->oa:I

    if-ne v0, p1, :cond_0

    .line 821
    :goto_0
    return-void

    .line 808
    :cond_0
    iput p1, p0, Lmiui/widget/NavigationLayout;->oa:I

    .line 810
    if-eqz p1, :cond_1

    .line 811
    iget-object v0, p0, Lmiui/widget/NavigationLayout;->nH:Lcom/miui/internal/widget/ViewDragHelper;

    invoke-virtual {v0}, Lcom/miui/internal/widget/ViewDragHelper;->cancel()V

    .line 813
    :cond_1
    packed-switch p1, :pswitch_data_0

    goto :goto_0

    .line 818
    :pswitch_0
    invoke-virtual {p0, v1}, Lmiui/widget/NavigationLayout;->closeNavigationDrawer(Z)V

    goto :goto_0

    .line 815
    :pswitch_1
    invoke-virtual {p0, v1}, Lmiui/widget/NavigationLayout;->openNavigationDrawer(Z)V

    goto :goto_0

    .line 813
    nop

    :pswitch_data_0
    .packed-switch 0x1
        :pswitch_0
        :pswitch_1
    .end packed-switch
.end method

.method public setDrawerMode(I)V
    .locals 0

    .prologue
    .line 387
    iput p1, p0, Lmiui/widget/NavigationLayout;->nK:I

    .line 388
    invoke-virtual {p0}, Lmiui/widget/NavigationLayout;->requestLayout()V

    .line 389
    return-void
.end method

.method public setNavigationEanbled(Z)V
    .locals 1

    .prologue
    .line 370
    iget-object v0, p0, Lmiui/widget/NavigationLayout;->nL:Landroid/view/View;

    invoke-direct {p0, v0, p1}, Lmiui/widget/NavigationLayout;->a(Landroid/view/View;Z)V

    .line 371
    return-void
.end method

.method public setNavigationListener(Lmiui/widget/NavigationLayout$NavigationListener;)V
    .locals 0

    .prologue
    .line 259
    iput-object p1, p0, Lmiui/widget/NavigationLayout;->nY:Lmiui/widget/NavigationLayout$NavigationListener;

    .line 260
    return-void
.end method

.method public setNavigationShadow(I)V
    .locals 1

    .prologue
    .line 298
    invoke-virtual {p0}, Lmiui/widget/NavigationLayout;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    invoke-virtual {v0, p1}, Landroid/content/res/Resources;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v0

    invoke-virtual {p0, v0}, Lmiui/widget/NavigationLayout;->setNavigationShadow(Landroid/graphics/drawable/Drawable;)V

    .line 299
    return-void
.end method

.method public setNavigationShadow(Landroid/graphics/drawable/Drawable;)V
    .locals 0

    .prologue
    .line 288
    iput-object p1, p0, Lmiui/widget/NavigationLayout;->nS:Landroid/graphics/drawable/Drawable;

    .line 289
    invoke-virtual {p0}, Lmiui/widget/NavigationLayout;->invalidate()V

    .line 290
    return-void
.end method

.method public setScrimColor(I)V
    .locals 0

    .prologue
    .line 581
    iput p1, p0, Lmiui/widget/NavigationLayout;->nW:I

    .line 582
    return-void
.end method
