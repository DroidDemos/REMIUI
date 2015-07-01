.class public Lcom/miui/internal/widget/ViewDragHelper;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/internal/widget/ViewDragHelper$Callback;
    }
.end annotation


# static fields
.field public static final DIRECTION_ALL:I = 0x3

.field public static final DIRECTION_HORIZONTAL:I = 0x1

.field public static final DIRECTION_VERTICAL:I = 0x2

.field public static final EDGE_ALL:I = 0xf

.field public static final EDGE_BOTTOM:I = 0x8

.field public static final EDGE_LEFT:I = 0x1

.field public static final EDGE_RIGHT:I = 0x2

.field public static final EDGE_TOP:I = 0x4

.field public static final INVALID_POINTER:I = -0x1

.field public static final STATE_DRAGGING:I = 0x1

.field public static final STATE_IDLE:I = 0x0

.field public static final STATE_SETTLING:I = 0x2

.field private static final yY:I = 0x14

.field private static final yZ:I = 0x100

.field private static final za:I = 0x258

.field private static final zs:Landroid/view/animation/Interpolator;


# instance fields
.field private bR:Landroid/view/VelocityTracker;

.field private cL:Landroid/widget/Scroller;

.field private cT:I

.field private cV:I

.field private zb:I

.field private zc:[F

.field private zd:[F

.field private ze:[F

.field private zf:[F

.field private zg:[I

.field private zh:[I

.field private zi:[I

.field private zj:I

.field private zk:F

.field private zl:F

.field private zm:I

.field private zn:I

.field private final zo:Lcom/miui/internal/widget/ViewDragHelper$Callback;

.field private zp:Landroid/view/View;

.field private zq:Z

.field private final zr:Landroid/view/ViewGroup;

.field private final zt:Ljava/lang/Runnable;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .prologue
    .line 322
    new-instance v0, Lcom/miui/internal/widget/s;

    invoke-direct {v0}, Lcom/miui/internal/widget/s;-><init>()V

    sput-object v0, Lcom/miui/internal/widget/ViewDragHelper;->zs:Landroid/view/animation/Interpolator;

    return-void
.end method

.method private constructor <init>(Landroid/content/Context;Landroid/view/ViewGroup;Lcom/miui/internal/widget/ViewDragHelper$Callback;)V
    .locals 3

    .prologue
    .line 369
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 111
    const/4 v0, -0x1

    iput v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->cV:I

    .line 329
    new-instance v0, Lcom/miui/internal/widget/r;

    invoke-direct {v0, p0}, Lcom/miui/internal/widget/r;-><init>(Lcom/miui/internal/widget/ViewDragHelper;)V

    iput-object v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->zt:Ljava/lang/Runnable;

    .line 370
    if-nez p2, :cond_0

    .line 371
    new-instance v0, Ljava/lang/IllegalArgumentException;

    const-string v1, "Parent view may not be null"

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 373
    :cond_0
    if-nez p3, :cond_1

    .line 374
    new-instance v0, Ljava/lang/IllegalArgumentException;

    const-string v1, "Callback may not be null"

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 377
    :cond_1
    iput-object p2, p0, Lcom/miui/internal/widget/ViewDragHelper;->zr:Landroid/view/ViewGroup;

    .line 378
    iput-object p3, p0, Lcom/miui/internal/widget/ViewDragHelper;->zo:Lcom/miui/internal/widget/ViewDragHelper$Callback;

    .line 380
    invoke-static {p1}, Landroid/view/ViewConfiguration;->get(Landroid/content/Context;)Landroid/view/ViewConfiguration;

    move-result-object v0

    .line 381
    invoke-virtual {p1}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    invoke-virtual {v1}, Landroid/content/res/Resources;->getDisplayMetrics()Landroid/util/DisplayMetrics;

    move-result-object v1

    iget v1, v1, Landroid/util/DisplayMetrics;->density:F

    .line 382
    const/high16 v2, 0x41a00000

    mul-float/2addr v1, v2

    const/high16 v2, 0x3f000000

    add-float/2addr v1, v2

    float-to-int v1, v1

    iput v1, p0, Lcom/miui/internal/widget/ViewDragHelper;->zm:I

    .line 384
    invoke-virtual {v0}, Landroid/view/ViewConfiguration;->getScaledTouchSlop()I

    move-result v1

    iput v1, p0, Lcom/miui/internal/widget/ViewDragHelper;->cT:I

    .line 385
    invoke-virtual {v0}, Landroid/view/ViewConfiguration;->getScaledMaximumFlingVelocity()I

    move-result v1

    int-to-float v1, v1

    iput v1, p0, Lcom/miui/internal/widget/ViewDragHelper;->zk:F

    .line 386
    invoke-virtual {v0}, Landroid/view/ViewConfiguration;->getScaledMinimumFlingVelocity()I

    move-result v0

    int-to-float v0, v0

    iput v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->zl:F

    .line 387
    new-instance v0, Landroid/widget/Scroller;

    sget-object v1, Lcom/miui/internal/widget/ViewDragHelper;->zs:Landroid/view/animation/Interpolator;

    invoke-direct {v0, p1, v1}, Landroid/widget/Scroller;-><init>(Landroid/content/Context;Landroid/view/animation/Interpolator;)V

    iput-object v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->cL:Landroid/widget/Scroller;

    .line 388
    return-void
.end method

.method private T(I)V
    .locals 3

    .prologue
    const/4 v2, 0x0

    const/4 v1, 0x0

    .line 780
    iget-object v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->zc:[F

    if-nez v0, :cond_0

    .line 791
    :goto_0
    return-void

    .line 783
    :cond_0
    iget-object v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->zc:[F

    aput v1, v0, p1

    .line 784
    iget-object v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->zd:[F

    aput v1, v0, p1

    .line 785
    iget-object v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->ze:[F

    aput v1, v0, p1

    .line 786
    iget-object v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->zf:[F

    aput v1, v0, p1

    .line 787
    iget-object v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->zg:[I

    aput v2, v0, p1

    .line 788
    iget-object v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->zh:[I

    aput v2, v0, p1

    .line 789
    iget-object v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->zi:[I

    aput v2, v0, p1

    .line 790
    iget v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->zj:I

    const/4 v1, 0x1

    shl-int/2addr v1, p1

    xor-int/lit8 v1, v1, -0x1

    and-int/2addr v0, v1

    iput v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->zj:I

    goto :goto_0
.end method

.method private U(I)V
    .locals 10

    .prologue
    const/4 v9, 0x0

    .line 794
    iget-object v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->zc:[F

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->zc:[F

    array-length v0, v0

    if-gt v0, p1, :cond_2

    .line 795
    :cond_0
    add-int/lit8 v0, p1, 0x1

    new-array v0, v0, [F

    .line 796
    add-int/lit8 v1, p1, 0x1

    new-array v1, v1, [F

    .line 797
    add-int/lit8 v2, p1, 0x1

    new-array v2, v2, [F

    .line 798
    add-int/lit8 v3, p1, 0x1

    new-array v3, v3, [F

    .line 799
    add-int/lit8 v4, p1, 0x1

    new-array v4, v4, [I

    .line 800
    add-int/lit8 v5, p1, 0x1

    new-array v5, v5, [I

    .line 801
    add-int/lit8 v6, p1, 0x1

    new-array v6, v6, [I

    .line 803
    iget-object v7, p0, Lcom/miui/internal/widget/ViewDragHelper;->zc:[F

    if-eqz v7, :cond_1

    .line 804
    iget-object v7, p0, Lcom/miui/internal/widget/ViewDragHelper;->zc:[F

    iget-object v8, p0, Lcom/miui/internal/widget/ViewDragHelper;->zc:[F

    array-length v8, v8

    invoke-static {v7, v9, v0, v9, v8}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    .line 805
    iget-object v7, p0, Lcom/miui/internal/widget/ViewDragHelper;->zd:[F

    iget-object v8, p0, Lcom/miui/internal/widget/ViewDragHelper;->zd:[F

    array-length v8, v8

    invoke-static {v7, v9, v1, v9, v8}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    .line 806
    iget-object v7, p0, Lcom/miui/internal/widget/ViewDragHelper;->ze:[F

    iget-object v8, p0, Lcom/miui/internal/widget/ViewDragHelper;->ze:[F

    array-length v8, v8

    invoke-static {v7, v9, v2, v9, v8}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    .line 807
    iget-object v7, p0, Lcom/miui/internal/widget/ViewDragHelper;->zf:[F

    iget-object v8, p0, Lcom/miui/internal/widget/ViewDragHelper;->zf:[F

    array-length v8, v8

    invoke-static {v7, v9, v3, v9, v8}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    .line 808
    iget-object v7, p0, Lcom/miui/internal/widget/ViewDragHelper;->zg:[I

    iget-object v8, p0, Lcom/miui/internal/widget/ViewDragHelper;->zg:[I

    array-length v8, v8

    invoke-static {v7, v9, v4, v9, v8}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    .line 809
    iget-object v7, p0, Lcom/miui/internal/widget/ViewDragHelper;->zh:[I

    iget-object v8, p0, Lcom/miui/internal/widget/ViewDragHelper;->zh:[I

    array-length v8, v8

    invoke-static {v7, v9, v5, v9, v8}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    .line 810
    iget-object v7, p0, Lcom/miui/internal/widget/ViewDragHelper;->zi:[I

    iget-object v8, p0, Lcom/miui/internal/widget/ViewDragHelper;->zi:[I

    array-length v8, v8

    invoke-static {v7, v9, v6, v9, v8}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    .line 813
    :cond_1
    iput-object v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->zc:[F

    .line 814
    iput-object v1, p0, Lcom/miui/internal/widget/ViewDragHelper;->zd:[F

    .line 815
    iput-object v2, p0, Lcom/miui/internal/widget/ViewDragHelper;->ze:[F

    .line 816
    iput-object v3, p0, Lcom/miui/internal/widget/ViewDragHelper;->zf:[F

    .line 817
    iput-object v4, p0, Lcom/miui/internal/widget/ViewDragHelper;->zg:[I

    .line 818
    iput-object v5, p0, Lcom/miui/internal/widget/ViewDragHelper;->zh:[I

    .line 819
    iput-object v6, p0, Lcom/miui/internal/widget/ViewDragHelper;->zi:[I

    .line 821
    :cond_2
    return-void
.end method

.method private a(FFF)F
    .locals 3

    .prologue
    const/4 v0, 0x0

    .line 663
    invoke-static {p1}, Ljava/lang/Math;->abs(F)F

    move-result v1

    .line 664
    cmpg-float v2, v1, p2

    if-gez v2, :cond_1

    move p3, v0

    .line 666
    :cond_0
    :goto_0
    return p3

    .line 665
    :cond_1
    cmpl-float v1, v1, p3

    if-lez v1, :cond_2

    cmpl-float v0, p1, v0

    if-gtz v0, :cond_0

    neg-float p3, p3

    goto :goto_0

    :cond_2
    move p3, p1

    .line 666
    goto :goto_0
.end method

.method private a(Landroid/view/View;IIII)I
    .locals 8

    .prologue
    .line 593
    iget v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->zl:F

    float-to-int v0, v0

    iget v1, p0, Lcom/miui/internal/widget/ViewDragHelper;->zk:F

    float-to-int v1, v1

    invoke-direct {p0, p4, v0, v1}, Lcom/miui/internal/widget/ViewDragHelper;->g(III)I

    move-result v2

    .line 594
    iget v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->zl:F

    float-to-int v0, v0

    iget v1, p0, Lcom/miui/internal/widget/ViewDragHelper;->zk:F

    float-to-int v1, v1

    invoke-direct {p0, p5, v0, v1}, Lcom/miui/internal/widget/ViewDragHelper;->g(III)I

    move-result v3

    .line 595
    invoke-static {p2}, Ljava/lang/Math;->abs(I)I

    move-result v0

    .line 596
    invoke-static {p3}, Ljava/lang/Math;->abs(I)I

    move-result v4

    .line 597
    invoke-static {v2}, Ljava/lang/Math;->abs(I)I

    move-result v1

    .line 598
    invoke-static {v3}, Ljava/lang/Math;->abs(I)I

    move-result v5

    .line 599
    add-int v6, v1, v5

    .line 600
    add-int v7, v0, v4

    .line 602
    if-eqz v2, :cond_0

    int-to-float v0, v1

    int-to-float v1, v6

    div-float/2addr v0, v1

    move v1, v0

    .line 604
    :goto_0
    if-eqz v3, :cond_1

    int-to-float v0, v5

    int-to-float v4, v6

    div-float/2addr v0, v4

    .line 607
    :goto_1
    iget-object v4, p0, Lcom/miui/internal/widget/ViewDragHelper;->zo:Lcom/miui/internal/widget/ViewDragHelper$Callback;

    invoke-virtual {v4, p1}, Lcom/miui/internal/widget/ViewDragHelper$Callback;->getViewHorizontalDragRange(Landroid/view/View;)I

    move-result v4

    invoke-direct {p0, p2, v2, v4}, Lcom/miui/internal/widget/ViewDragHelper;->f(III)I

    move-result v2

    .line 608
    iget-object v4, p0, Lcom/miui/internal/widget/ViewDragHelper;->zo:Lcom/miui/internal/widget/ViewDragHelper$Callback;

    invoke-virtual {v4, p1}, Lcom/miui/internal/widget/ViewDragHelper$Callback;->getViewVerticalDragRange(Landroid/view/View;)I

    move-result v4

    invoke-direct {p0, p3, v3, v4}, Lcom/miui/internal/widget/ViewDragHelper;->f(III)I

    move-result v3

    .line 610
    int-to-float v2, v2

    mul-float/2addr v1, v2

    int-to-float v2, v3

    mul-float/2addr v0, v2

    add-float/2addr v0, v1

    float-to-int v0, v0

    return v0

    .line 602
    :cond_0
    int-to-float v0, v0

    int-to-float v1, v7

    div-float/2addr v0, v1

    move v1, v0

    goto :goto_0

    .line 604
    :cond_1
    int-to-float v0, v4

    int-to-float v4, v7

    div-float/2addr v0, v4

    goto :goto_1
.end method

.method private a(FF)V
    .locals 4

    .prologue
    const/4 v3, 0x1

    const/4 v2, 0x0

    .line 755
    iput-boolean v3, p0, Lcom/miui/internal/widget/ViewDragHelper;->zq:Z

    .line 756
    iget-object v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->zo:Lcom/miui/internal/widget/ViewDragHelper$Callback;

    iget-object v1, p0, Lcom/miui/internal/widget/ViewDragHelper;->zp:Landroid/view/View;

    invoke-virtual {v0, v1, p1, p2}, Lcom/miui/internal/widget/ViewDragHelper$Callback;->onViewReleased(Landroid/view/View;FF)V

    .line 757
    iput-boolean v2, p0, Lcom/miui/internal/widget/ViewDragHelper;->zq:Z

    .line 759
    iget v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->zb:I

    if-ne v0, v3, :cond_0

    .line 761
    invoke-virtual {p0, v2}, Lcom/miui/internal/widget/ViewDragHelper;->V(I)V

    .line 763
    :cond_0
    return-void
.end method

.method private a(FFII)Z
    .locals 4

    .prologue
    const/4 v0, 0x0

    .line 1216
    invoke-static {p1}, Ljava/lang/Math;->abs(F)F

    move-result v1

    .line 1217
    invoke-static {p2}, Ljava/lang/Math;->abs(F)F

    move-result v2

    .line 1219
    iget-object v3, p0, Lcom/miui/internal/widget/ViewDragHelper;->zg:[I

    aget v3, v3, p3

    and-int/2addr v3, p4

    if-ne v3, p4, :cond_0

    iget v3, p0, Lcom/miui/internal/widget/ViewDragHelper;->zn:I

    and-int/2addr v3, p4

    if-eqz v3, :cond_0

    iget-object v3, p0, Lcom/miui/internal/widget/ViewDragHelper;->zi:[I

    aget v3, v3, p3

    and-int/2addr v3, p4

    if-eq v3, p4, :cond_0

    iget-object v3, p0, Lcom/miui/internal/widget/ViewDragHelper;->zh:[I

    aget v3, v3, p3

    and-int/2addr v3, p4

    if-eq v3, p4, :cond_0

    iget v3, p0, Lcom/miui/internal/widget/ViewDragHelper;->cT:I

    int-to-float v3, v3

    cmpg-float v3, v1, v3

    if-gtz v3, :cond_1

    iget v3, p0, Lcom/miui/internal/widget/ViewDragHelper;->cT:I

    int-to-float v3, v3

    cmpg-float v3, v2, v3

    if-gtz v3, :cond_1

    .line 1229
    :cond_0
    :goto_0
    return v0

    .line 1225
    :cond_1
    const/high16 v3, 0x3f000000

    mul-float/2addr v2, v3

    cmpg-float v2, v1, v2

    if-gez v2, :cond_2

    iget-object v2, p0, Lcom/miui/internal/widget/ViewDragHelper;->zo:Lcom/miui/internal/widget/ViewDragHelper$Callback;

    invoke-virtual {v2, p4}, Lcom/miui/internal/widget/ViewDragHelper$Callback;->onEdgeLock(I)Z

    move-result v2

    if-eqz v2, :cond_2

    .line 1226
    iget-object v1, p0, Lcom/miui/internal/widget/ViewDragHelper;->zi:[I

    aget v2, v1, p3

    or-int/2addr v2, p4

    aput v2, v1, p3

    goto :goto_0

    .line 1229
    :cond_2
    iget-object v2, p0, Lcom/miui/internal/widget/ViewDragHelper;->zh:[I

    aget v2, v2, p3

    and-int/2addr v2, p4

    if-nez v2, :cond_0

    iget v2, p0, Lcom/miui/internal/widget/ViewDragHelper;->cT:I

    int-to-float v2, v2

    cmpl-float v1, v1, v2

    if-lez v1, :cond_0

    const/4 v0, 0x1

    goto :goto_0
.end method

.method private a(Landroid/view/View;FF)Z
    .locals 5

    .prologue
    const/4 v1, 0x1

    const/4 v2, 0x0

    .line 1243
    if-nez p1, :cond_1

    move v1, v2

    .line 1256
    :cond_0
    :goto_0
    return v1

    .line 1246
    :cond_1
    iget-object v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->zo:Lcom/miui/internal/widget/ViewDragHelper$Callback;

    invoke-virtual {v0, p1}, Lcom/miui/internal/widget/ViewDragHelper$Callback;->getViewHorizontalDragRange(Landroid/view/View;)I

    move-result v0

    if-lez v0, :cond_2

    move v0, v1

    .line 1247
    :goto_1
    iget-object v3, p0, Lcom/miui/internal/widget/ViewDragHelper;->zo:Lcom/miui/internal/widget/ViewDragHelper$Callback;

    invoke-virtual {v3, p1}, Lcom/miui/internal/widget/ViewDragHelper$Callback;->getViewVerticalDragRange(Landroid/view/View;)I

    move-result v3

    if-lez v3, :cond_3

    move v3, v1

    .line 1249
    :goto_2
    if-eqz v0, :cond_4

    if-eqz v3, :cond_4

    .line 1250
    mul-float v0, p2, p2

    mul-float v3, p3, p3

    add-float/2addr v0, v3

    iget v3, p0, Lcom/miui/internal/widget/ViewDragHelper;->cT:I

    iget v4, p0, Lcom/miui/internal/widget/ViewDragHelper;->cT:I

    mul-int/2addr v3, v4

    int-to-float v3, v3

    cmpl-float v0, v0, v3

    if-gtz v0, :cond_0

    move v1, v2

    goto :goto_0

    :cond_2
    move v0, v2

    .line 1246
    goto :goto_1

    :cond_3
    move v3, v2

    .line 1247
    goto :goto_2

    .line 1251
    :cond_4
    if-eqz v0, :cond_6

    .line 1252
    invoke-static {p3}, Ljava/lang/Math;->abs(F)F

    move-result v0

    iget v3, p0, Lcom/miui/internal/widget/ViewDragHelper;->cT:I

    int-to-float v3, v3

    cmpg-float v0, v0, v3

    if-gez v0, :cond_5

    invoke-static {p2}, Ljava/lang/Math;->abs(F)F

    move-result v0

    iget v3, p0, Lcom/miui/internal/widget/ViewDragHelper;->cT:I

    int-to-float v3, v3

    cmpl-float v0, v0, v3

    if-gtz v0, :cond_0

    :cond_5
    move v1, v2

    goto :goto_0

    .line 1253
    :cond_6
    if-eqz v3, :cond_8

    .line 1254
    invoke-static {p2}, Ljava/lang/Math;->abs(F)F

    move-result v0

    iget v3, p0, Lcom/miui/internal/widget/ViewDragHelper;->cT:I

    int-to-float v3, v3

    cmpg-float v0, v0, v3

    if-gez v0, :cond_7

    invoke-static {p3}, Ljava/lang/Math;->abs(F)F

    move-result v0

    iget v3, p0, Lcom/miui/internal/widget/ViewDragHelper;->cT:I

    int-to-float v3, v3

    cmpl-float v0, v0, v3

    if-gtz v0, :cond_0

    :cond_7
    move v1, v2

    goto :goto_0

    :cond_8
    move v1, v2

    .line 1256
    goto :goto_0
.end method

.method private b(FFI)V
    .locals 3

    .prologue
    .line 824
    invoke-direct {p0, p3}, Lcom/miui/internal/widget/ViewDragHelper;->U(I)V

    .line 825
    iget-object v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->zc:[F

    iget-object v1, p0, Lcom/miui/internal/widget/ViewDragHelper;->ze:[F

    aput p1, v1, p3

    aput p1, v0, p3

    .line 826
    iget-object v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->zd:[F

    iget-object v1, p0, Lcom/miui/internal/widget/ViewDragHelper;->zf:[F

    aput p2, v1, p3

    aput p2, v0, p3

    .line 827
    iget-object v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->zg:[I

    float-to-int v1, p1

    float-to-int v2, p2

    invoke-direct {p0, v1, v2}, Lcom/miui/internal/widget/ViewDragHelper;->i(II)I

    move-result v1

    aput v1, v0, p3

    .line 828
    iget v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->zj:I

    const/4 v1, 0x1

    shl-int/2addr v1, p3

    or-int/2addr v0, v1

    iput v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->zj:I

    .line 829
    return-void
.end method

.method private c(FFI)V
    .locals 3

    .prologue
    const/4 v0, 0x1

    .line 1195
    const/4 v1, 0x0

    .line 1196
    invoke-direct {p0, p1, p2, p3, v0}, Lcom/miui/internal/widget/ViewDragHelper;->a(FFII)Z

    move-result v2

    if-eqz v2, :cond_4

    .line 1199
    :goto_0
    const/4 v1, 0x4

    invoke-direct {p0, p2, p1, p3, v1}, Lcom/miui/internal/widget/ViewDragHelper;->a(FFII)Z

    move-result v1

    if-eqz v1, :cond_0

    .line 1200
    or-int/lit8 v0, v0, 0x4

    .line 1202
    :cond_0
    const/4 v1, 0x2

    invoke-direct {p0, p1, p2, p3, v1}, Lcom/miui/internal/widget/ViewDragHelper;->a(FFII)Z

    move-result v1

    if-eqz v1, :cond_1

    .line 1203
    or-int/lit8 v0, v0, 0x2

    .line 1205
    :cond_1
    const/16 v1, 0x8

    invoke-direct {p0, p2, p1, p3, v1}, Lcom/miui/internal/widget/ViewDragHelper;->a(FFII)Z

    move-result v1

    if-eqz v1, :cond_2

    .line 1206
    or-int/lit8 v0, v0, 0x8

    .line 1209
    :cond_2
    if-eqz v0, :cond_3

    .line 1210
    iget-object v1, p0, Lcom/miui/internal/widget/ViewDragHelper;->zh:[I

    aget v2, v1, p3

    or-int/2addr v2, v0

    aput v2, v1, p3

    .line 1211
    iget-object v1, p0, Lcom/miui/internal/widget/ViewDragHelper;->zo:Lcom/miui/internal/widget/ViewDragHelper$Callback;

    invoke-virtual {v1, v0, p3}, Lcom/miui/internal/widget/ViewDragHelper$Callback;->onEdgeDragStarted(II)V

    .line 1213
    :cond_3
    return-void

    :cond_4
    move v0, v1

    goto :goto_0
.end method

.method private c(Landroid/view/MotionEvent;)V
    .locals 6

    .prologue
    .line 832
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getPointerCount()I

    move-result v1

    .line 833
    const/4 v0, 0x0

    :goto_0
    if-ge v0, v1, :cond_0

    .line 834
    invoke-virtual {p1, v0}, Landroid/view/MotionEvent;->getPointerId(I)I

    move-result v2

    .line 835
    invoke-virtual {p1, v0}, Landroid/view/MotionEvent;->getX(I)F

    move-result v3

    .line 836
    invoke-virtual {p1, v0}, Landroid/view/MotionEvent;->getY(I)F

    move-result v4

    .line 837
    iget-object v5, p0, Lcom/miui/internal/widget/ViewDragHelper;->ze:[F

    aput v3, v5, v2

    .line 838
    iget-object v3, p0, Lcom/miui/internal/widget/ViewDragHelper;->zf:[F

    aput v4, v3, v2

    .line 833
    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    .line 840
    :cond_0
    return-void
.end method

.method private c(IIII)Z
    .locals 10

    .prologue
    const/4 v0, 0x0

    .line 573
    iget-object v1, p0, Lcom/miui/internal/widget/ViewDragHelper;->zp:Landroid/view/View;

    invoke-virtual {v1}, Landroid/view/View;->getLeft()I

    move-result v7

    .line 574
    iget-object v1, p0, Lcom/miui/internal/widget/ViewDragHelper;->zp:Landroid/view/View;

    invoke-virtual {v1}, Landroid/view/View;->getTop()I

    move-result v6

    .line 575
    sub-int v2, p1, v7

    .line 576
    sub-int v3, p2, v6

    .line 578
    if-nez v2, :cond_0

    if-nez v3, :cond_0

    .line 580
    iget-object v1, p0, Lcom/miui/internal/widget/ViewDragHelper;->cL:Landroid/widget/Scroller;

    invoke-virtual {v1}, Landroid/widget/Scroller;->abortAnimation()V

    .line 581
    invoke-virtual {p0, v0}, Lcom/miui/internal/widget/ViewDragHelper;->V(I)V

    .line 589
    :goto_0
    return v0

    .line 585
    :cond_0
    iget-object v1, p0, Lcom/miui/internal/widget/ViewDragHelper;->zp:Landroid/view/View;

    move-object v0, p0

    move v4, p3

    move v5, p4

    invoke-direct/range {v0 .. v5}, Lcom/miui/internal/widget/ViewDragHelper;->a(Landroid/view/View;IIII)I

    move-result v9

    .line 586
    iget-object v4, p0, Lcom/miui/internal/widget/ViewDragHelper;->cL:Landroid/widget/Scroller;

    move v5, v7

    move v7, v2

    move v8, v3

    invoke-virtual/range {v4 .. v9}, Landroid/widget/Scroller;->startScroll(IIIII)V

    .line 588
    const/4 v0, 0x2

    invoke-virtual {p0, v0}, Lcom/miui/internal/widget/ViewDragHelper;->V(I)V

    .line 589
    const/4 v0, 0x1

    goto :goto_0
.end method

.method private cD()V
    .locals 3

    .prologue
    const/4 v2, 0x0

    const/4 v1, 0x0

    .line 766
    iget-object v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->zc:[F

    if-nez v0, :cond_0

    .line 777
    :goto_0
    return-void

    .line 769
    :cond_0
    iget-object v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->zc:[F

    invoke-static {v0, v1}, Ljava/util/Arrays;->fill([FF)V

    .line 770
    iget-object v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->zd:[F

    invoke-static {v0, v1}, Ljava/util/Arrays;->fill([FF)V

    .line 771
    iget-object v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->ze:[F

    invoke-static {v0, v1}, Ljava/util/Arrays;->fill([FF)V

    .line 772
    iget-object v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->zf:[F

    invoke-static {v0, v1}, Ljava/util/Arrays;->fill([FF)V

    .line 773
    iget-object v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->zg:[I

    invoke-static {v0, v2}, Ljava/util/Arrays;->fill([II)V

    .line 774
    iget-object v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->zh:[I

    invoke-static {v0, v2}, Ljava/util/Arrays;->fill([II)V

    .line 775
    iget-object v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->zi:[I

    invoke-static {v0, v2}, Ljava/util/Arrays;->fill([II)V

    .line 776
    iput v2, p0, Lcom/miui/internal/widget/ViewDragHelper;->zj:I

    goto :goto_0
.end method

.method private cE()V
    .locals 4

    .prologue
    .line 1353
    iget-object v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->bR:Landroid/view/VelocityTracker;

    const/16 v1, 0x3e8

    iget v2, p0, Lcom/miui/internal/widget/ViewDragHelper;->zk:F

    invoke-virtual {v0, v1, v2}, Landroid/view/VelocityTracker;->computeCurrentVelocity(IF)V

    .line 1354
    iget-object v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->bR:Landroid/view/VelocityTracker;

    iget v1, p0, Lcom/miui/internal/widget/ViewDragHelper;->cV:I

    invoke-virtual {v0, v1}, Landroid/view/VelocityTracker;->getXVelocity(I)F

    move-result v0

    iget v1, p0, Lcom/miui/internal/widget/ViewDragHelper;->zl:F

    iget v2, p0, Lcom/miui/internal/widget/ViewDragHelper;->zk:F

    invoke-direct {p0, v0, v1, v2}, Lcom/miui/internal/widget/ViewDragHelper;->a(FFF)F

    move-result v0

    .line 1357
    iget-object v1, p0, Lcom/miui/internal/widget/ViewDragHelper;->bR:Landroid/view/VelocityTracker;

    iget v2, p0, Lcom/miui/internal/widget/ViewDragHelper;->cV:I

    invoke-virtual {v1, v2}, Landroid/view/VelocityTracker;->getYVelocity(I)F

    move-result v1

    iget v2, p0, Lcom/miui/internal/widget/ViewDragHelper;->zl:F

    iget v3, p0, Lcom/miui/internal/widget/ViewDragHelper;->zk:F

    invoke-direct {p0, v1, v2, v3}, Lcom/miui/internal/widget/ViewDragHelper;->a(FFF)F

    move-result v1

    .line 1360
    invoke-direct {p0, v0, v1}, Lcom/miui/internal/widget/ViewDragHelper;->a(FF)V

    .line 1361
    return-void
.end method

.method public static create(Landroid/view/ViewGroup;FLcom/miui/internal/widget/ViewDragHelper$Callback;)Lcom/miui/internal/widget/ViewDragHelper;
    .locals 3

    .prologue
    .line 356
    invoke-static {p0, p2}, Lcom/miui/internal/widget/ViewDragHelper;->create(Landroid/view/ViewGroup;Lcom/miui/internal/widget/ViewDragHelper$Callback;)Lcom/miui/internal/widget/ViewDragHelper;

    move-result-object v0

    .line 357
    iget v1, v0, Lcom/miui/internal/widget/ViewDragHelper;->cT:I

    int-to-float v1, v1

    const/high16 v2, 0x3f800000

    div-float/2addr v2, p1

    mul-float/2addr v1, v2

    float-to-int v1, v1

    iput v1, v0, Lcom/miui/internal/widget/ViewDragHelper;->cT:I

    .line 358
    return-object v0
.end method

.method public static create(Landroid/view/ViewGroup;Lcom/miui/internal/widget/ViewDragHelper$Callback;)Lcom/miui/internal/widget/ViewDragHelper;
    .locals 2

    .prologue
    .line 343
    new-instance v0, Lcom/miui/internal/widget/ViewDragHelper;

    invoke-virtual {p0}, Landroid/view/ViewGroup;->getContext()Landroid/content/Context;

    move-result-object v1

    invoke-direct {v0, v1, p0, p1}, Lcom/miui/internal/widget/ViewDragHelper;-><init>(Landroid/content/Context;Landroid/view/ViewGroup;Lcom/miui/internal/widget/ViewDragHelper$Callback;)V

    return-object v0
.end method

.method private d(F)F
    .locals 4

    .prologue
    .line 670
    const/high16 v0, 0x3f000000

    sub-float v0, p1, v0

    .line 671
    float-to-double v0, v0

    const-wide v2, 0x3fde28c7460698c7L

    mul-double/2addr v0, v2

    double-to-float v0, v0

    .line 672
    float-to-double v0, v0

    invoke-static {v0, v1}, Ljava/lang/Math;->sin(D)D

    move-result-wide v0

    double-to-float v0, v0

    return v0
.end method

.method private d(IIII)V
    .locals 6

    .prologue
    .line 1364
    .line 1366
    iget-object v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->zp:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->getLeft()I

    move-result v0

    .line 1367
    iget-object v1, p0, Lcom/miui/internal/widget/ViewDragHelper;->zp:Landroid/view/View;

    invoke-virtual {v1}, Landroid/view/View;->getTop()I

    move-result v1

    .line 1368
    if-eqz p3, :cond_3

    .line 1369
    iget-object v2, p0, Lcom/miui/internal/widget/ViewDragHelper;->zo:Lcom/miui/internal/widget/ViewDragHelper$Callback;

    iget-object v3, p0, Lcom/miui/internal/widget/ViewDragHelper;->zp:Landroid/view/View;

    invoke-virtual {v2, v3, p1, p3}, Lcom/miui/internal/widget/ViewDragHelper$Callback;->clampViewPositionHorizontal(Landroid/view/View;II)I

    move-result v2

    .line 1370
    iget-object v3, p0, Lcom/miui/internal/widget/ViewDragHelper;->zp:Landroid/view/View;

    sub-int v4, v2, v0

    invoke-virtual {v3, v4}, Landroid/view/View;->offsetLeftAndRight(I)V

    .line 1372
    :goto_0
    if-eqz p4, :cond_2

    .line 1373
    iget-object v3, p0, Lcom/miui/internal/widget/ViewDragHelper;->zo:Lcom/miui/internal/widget/ViewDragHelper$Callback;

    iget-object v4, p0, Lcom/miui/internal/widget/ViewDragHelper;->zp:Landroid/view/View;

    invoke-virtual {v3, v4, p2, p4}, Lcom/miui/internal/widget/ViewDragHelper$Callback;->clampViewPositionVertical(Landroid/view/View;II)I

    move-result v3

    .line 1374
    iget-object v4, p0, Lcom/miui/internal/widget/ViewDragHelper;->zp:Landroid/view/View;

    sub-int v5, v3, v1

    invoke-virtual {v4, v5}, Landroid/view/View;->offsetTopAndBottom(I)V

    .line 1377
    :goto_1
    if-nez p3, :cond_0

    if-eqz p4, :cond_1

    .line 1378
    :cond_0
    sub-int v4, v2, v0

    .line 1379
    sub-int v5, v3, v1

    .line 1380
    iget-object v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->zo:Lcom/miui/internal/widget/ViewDragHelper$Callback;

    iget-object v1, p0, Lcom/miui/internal/widget/ViewDragHelper;->zp:Landroid/view/View;

    invoke-virtual/range {v0 .. v5}, Lcom/miui/internal/widget/ViewDragHelper$Callback;->onViewPositionChanged(Landroid/view/View;IIII)V

    .line 1383
    :cond_1
    return-void

    :cond_2
    move v3, p2

    goto :goto_1

    :cond_3
    move v2, p1

    goto :goto_0
.end method

.method private f(III)I
    .locals 4

    .prologue
    const/high16 v3, 0x3f800000

    .line 614
    if-nez p1, :cond_0

    .line 615
    const/4 v0, 0x0

    .line 632
    :goto_0
    return v0

    .line 618
    :cond_0
    iget-object v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->zr:Landroid/view/ViewGroup;

    invoke-virtual {v0}, Landroid/view/ViewGroup;->getWidth()I

    move-result v0

    .line 619
    div-int/lit8 v1, v0, 0x2

    .line 620
    invoke-static {p1}, Ljava/lang/Math;->abs(I)I

    move-result v2

    int-to-float v2, v2

    int-to-float v0, v0

    div-float v0, v2, v0

    invoke-static {v3, v0}, Ljava/lang/Math;->min(FF)F

    move-result v0

    .line 621
    int-to-float v2, v1

    int-to-float v1, v1

    invoke-direct {p0, v0}, Lcom/miui/internal/widget/ViewDragHelper;->d(F)F

    move-result v0

    mul-float/2addr v0, v1

    add-float/2addr v0, v2

    .line 625
    invoke-static {p2}, Ljava/lang/Math;->abs(I)I

    move-result v1

    .line 626
    if-lez v1, :cond_1

    .line 627
    const/high16 v2, 0x447a0000

    int-to-float v1, v1

    div-float/2addr v0, v1

    invoke-static {v0}, Ljava/lang/Math;->abs(F)F

    move-result v0

    mul-float/2addr v0, v2

    invoke-static {v0}, Ljava/lang/Math;->round(F)I

    move-result v0

    mul-int/lit8 v0, v0, 0x4

    .line 632
    :goto_1
    const/16 v1, 0x258

    invoke-static {v0, v1}, Ljava/lang/Math;->min(II)I

    move-result v0

    goto :goto_0

    .line 629
    :cond_1
    invoke-static {p1}, Ljava/lang/Math;->abs(I)I

    move-result v0

    int-to-float v0, v0

    int-to-float v1, p3

    div-float/2addr v0, v1

    .line 630
    add-float/2addr v0, v3

    const/high16 v1, 0x43800000

    mul-float/2addr v0, v1

    float-to-int v0, v0

    goto :goto_1
.end method

.method private g(III)I
    .locals 1

    .prologue
    .line 646
    invoke-static {p1}, Ljava/lang/Math;->abs(I)I

    move-result v0

    .line 647
    if-ge v0, p2, :cond_1

    const/4 p3, 0x0

    .line 649
    :cond_0
    :goto_0
    return p3

    .line 648
    :cond_1
    if-le v0, p3, :cond_2

    if-gtz p1, :cond_0

    neg-int p3, p3

    goto :goto_0

    :cond_2
    move p3, p1

    .line 649
    goto :goto_0
.end method

.method private i(II)I
    .locals 3

    .prologue
    .line 1432
    const/4 v0, 0x0

    .line 1434
    iget-object v1, p0, Lcom/miui/internal/widget/ViewDragHelper;->zr:Landroid/view/ViewGroup;

    invoke-virtual {v1}, Landroid/view/ViewGroup;->getLeft()I

    move-result v1

    iget v2, p0, Lcom/miui/internal/widget/ViewDragHelper;->zm:I

    add-int/2addr v1, v2

    if-ge p1, v1, :cond_0

    const/4 v0, 0x1

    .line 1435
    :cond_0
    iget-object v1, p0, Lcom/miui/internal/widget/ViewDragHelper;->zr:Landroid/view/ViewGroup;

    invoke-virtual {v1}, Landroid/view/ViewGroup;->getTop()I

    move-result v1

    iget v2, p0, Lcom/miui/internal/widget/ViewDragHelper;->zm:I

    add-int/2addr v1, v2

    if-ge p2, v1, :cond_1

    or-int/lit8 v0, v0, 0x4

    .line 1436
    :cond_1
    iget-object v1, p0, Lcom/miui/internal/widget/ViewDragHelper;->zr:Landroid/view/ViewGroup;

    invoke-virtual {v1}, Landroid/view/ViewGroup;->getRight()I

    move-result v1

    iget v2, p0, Lcom/miui/internal/widget/ViewDragHelper;->zm:I

    sub-int/2addr v1, v2

    if-le p1, v1, :cond_2

    or-int/lit8 v0, v0, 0x2

    .line 1437
    :cond_2
    iget-object v1, p0, Lcom/miui/internal/widget/ViewDragHelper;->zr:Landroid/view/ViewGroup;

    invoke-virtual {v1}, Landroid/view/ViewGroup;->getBottom()I

    move-result v1

    iget v2, p0, Lcom/miui/internal/widget/ViewDragHelper;->zm:I

    sub-int/2addr v1, v2

    if-le p2, v1, :cond_3

    or-int/lit8 v0, v0, 0x8

    .line 1439
    :cond_3
    return v0
.end method


# virtual methods
.method V(I)V
    .locals 1

    .prologue
    .line 860
    iget v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->zb:I

    if-eq v0, p1, :cond_0

    .line 861
    iput p1, p0, Lcom/miui/internal/widget/ViewDragHelper;->zb:I

    .line 862
    iget-object v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->zo:Lcom/miui/internal/widget/ViewDragHelper$Callback;

    invoke-virtual {v0, p1}, Lcom/miui/internal/widget/ViewDragHelper$Callback;->onViewDragStateChanged(I)V

    .line 863
    if-nez p1, :cond_0

    .line 864
    const/4 v0, 0x0

    iput-object v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->zp:Landroid/view/View;

    .line 867
    :cond_0
    return-void
.end method

.method a(Landroid/view/View;I)Z
    .locals 2

    .prologue
    const/4 v0, 0x1

    .line 879
    iget-object v1, p0, Lcom/miui/internal/widget/ViewDragHelper;->zp:Landroid/view/View;

    if-ne p1, v1, :cond_0

    iget v1, p0, Lcom/miui/internal/widget/ViewDragHelper;->cV:I

    if-ne v1, p2, :cond_0

    .line 888
    :goto_0
    return v0

    .line 883
    :cond_0
    if-eqz p1, :cond_1

    iget-object v1, p0, Lcom/miui/internal/widget/ViewDragHelper;->zo:Lcom/miui/internal/widget/ViewDragHelper$Callback;

    invoke-virtual {v1, p1, p2}, Lcom/miui/internal/widget/ViewDragHelper$Callback;->tryCaptureView(Landroid/view/View;I)Z

    move-result v1

    if-eqz v1, :cond_1

    .line 884
    iput p2, p0, Lcom/miui/internal/widget/ViewDragHelper;->cV:I

    .line 885
    invoke-virtual {p0, p1, p2}, Lcom/miui/internal/widget/ViewDragHelper;->captureChildView(Landroid/view/View;I)V

    goto :goto_0

    .line 888
    :cond_1
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public abort()V
    .locals 6

    .prologue
    .line 508
    invoke-virtual {p0}, Lcom/miui/internal/widget/ViewDragHelper;->cancel()V

    .line 509
    iget v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->zb:I

    const/4 v1, 0x2

    if-ne v0, v1, :cond_0

    .line 510
    iget-object v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->cL:Landroid/widget/Scroller;

    invoke-virtual {v0}, Landroid/widget/Scroller;->getCurrX()I

    move-result v4

    .line 511
    iget-object v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->cL:Landroid/widget/Scroller;

    invoke-virtual {v0}, Landroid/widget/Scroller;->getCurrY()I

    move-result v5

    .line 512
    iget-object v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->cL:Landroid/widget/Scroller;

    invoke-virtual {v0}, Landroid/widget/Scroller;->abortAnimation()V

    .line 513
    iget-object v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->cL:Landroid/widget/Scroller;

    invoke-virtual {v0}, Landroid/widget/Scroller;->getCurrX()I

    move-result v2

    .line 514
    iget-object v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->cL:Landroid/widget/Scroller;

    invoke-virtual {v0}, Landroid/widget/Scroller;->getCurrY()I

    move-result v3

    .line 515
    iget-object v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->zo:Lcom/miui/internal/widget/ViewDragHelper$Callback;

    iget-object v1, p0, Lcom/miui/internal/widget/ViewDragHelper;->zp:Landroid/view/View;

    sub-int v4, v2, v4

    sub-int v5, v3, v5

    invoke-virtual/range {v0 .. v5}, Lcom/miui/internal/widget/ViewDragHelper$Callback;->onViewPositionChanged(Landroid/view/View;IIII)V

    .line 517
    :cond_0
    const/4 v0, 0x0

    invoke-virtual {p0, v0}, Lcom/miui/internal/widget/ViewDragHelper;->V(I)V

    .line 518
    return-void
.end method

.method protected canScroll(Landroid/view/View;ZIIII)Z
    .locals 11

    .prologue
    .line 904
    instance-of v0, p1, Landroid/view/ViewGroup;

    if-eqz v0, :cond_1

    move-object v7, p1

    .line 905
    check-cast v7, Landroid/view/ViewGroup;

    .line 906
    invoke-virtual {p1}, Landroid/view/View;->getScrollX()I

    move-result v9

    .line 907
    invoke-virtual {p1}, Landroid/view/View;->getScrollY()I

    move-result v10

    .line 908
    invoke-virtual {v7}, Landroid/view/ViewGroup;->getChildCount()I

    move-result v0

    .line 910
    add-int/lit8 v0, v0, -0x1

    move v8, v0

    :goto_0
    if-ltz v8, :cond_1

    .line 913
    invoke-virtual {v7, v8}, Landroid/view/ViewGroup;->getChildAt(I)Landroid/view/View;

    move-result-object v1

    .line 914
    add-int v0, p5, v9

    invoke-virtual {v1}, Landroid/view/View;->getLeft()I

    move-result v2

    if-lt v0, v2, :cond_0

    add-int v0, p5, v9

    invoke-virtual {v1}, Landroid/view/View;->getRight()I

    move-result v2

    if-ge v0, v2, :cond_0

    add-int v0, p6, v10

    invoke-virtual {v1}, Landroid/view/View;->getTop()I

    move-result v2

    if-lt v0, v2, :cond_0

    add-int v0, p6, v10

    invoke-virtual {v1}, Landroid/view/View;->getBottom()I

    move-result v2

    if-ge v0, v2, :cond_0

    const/4 v2, 0x1

    add-int v0, p5, v9

    invoke-virtual {v1}, Landroid/view/View;->getLeft()I

    move-result v3

    sub-int v5, v0, v3

    add-int v0, p6, v10

    invoke-virtual {v1}, Landroid/view/View;->getTop()I

    move-result v3

    sub-int v6, v0, v3

    move-object v0, p0

    move v3, p3

    move v4, p4

    invoke-virtual/range {v0 .. v6}, Lcom/miui/internal/widget/ViewDragHelper;->canScroll(Landroid/view/View;ZIIII)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 918
    const/4 v0, 0x1

    .line 923
    :goto_1
    return v0

    .line 910
    :cond_0
    add-int/lit8 v0, v8, -0x1

    move v8, v0

    goto :goto_0

    .line 923
    :cond_1
    if-eqz p2, :cond_3

    neg-int v0, p3

    invoke-virtual {p1, v0}, Landroid/view/View;->canScrollHorizontally(I)Z

    move-result v0

    if-nez v0, :cond_2

    neg-int v0, p4

    invoke-virtual {p1, v0}, Landroid/view/View;->canScrollVertically(I)Z

    move-result v0

    if-eqz v0, :cond_3

    :cond_2
    const/4 v0, 0x1

    goto :goto_1

    :cond_3
    const/4 v0, 0x0

    goto :goto_1
.end method

.method public cancel()V
    .locals 1

    .prologue
    .line 494
    const/4 v0, -0x1

    iput v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->cV:I

    .line 495
    invoke-direct {p0}, Lcom/miui/internal/widget/ViewDragHelper;->cD()V

    .line 497
    iget-object v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->bR:Landroid/view/VelocityTracker;

    if-eqz v0, :cond_0

    .line 498
    iget-object v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->bR:Landroid/view/VelocityTracker;

    invoke-virtual {v0}, Landroid/view/VelocityTracker;->recycle()V

    .line 499
    const/4 v0, 0x0

    iput-object v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->bR:Landroid/view/VelocityTracker;

    .line 501
    :cond_0
    return-void
.end method

.method public captureChildView(Landroid/view/View;I)V
    .locals 3

    .prologue
    .line 456
    invoke-virtual {p1}, Landroid/view/View;->getParent()Landroid/view/ViewParent;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/internal/widget/ViewDragHelper;->zr:Landroid/view/ViewGroup;

    if-eq v0, v1, :cond_0

    .line 457
    new-instance v0, Ljava/lang/IllegalArgumentException;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "captureChildView: parameter must be a descendant of the ViewDragHelper\'s tracked parent view ("

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    iget-object v2, p0, Lcom/miui/internal/widget/ViewDragHelper;->zr:Landroid/view/ViewGroup;

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, ")"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 461
    :cond_0
    iput-object p1, p0, Lcom/miui/internal/widget/ViewDragHelper;->zp:Landroid/view/View;

    .line 462
    iput p2, p0, Lcom/miui/internal/widget/ViewDragHelper;->cV:I

    .line 463
    iget-object v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->zo:Lcom/miui/internal/widget/ViewDragHelper$Callback;

    invoke-virtual {v0, p1, p2}, Lcom/miui/internal/widget/ViewDragHelper$Callback;->onViewCaptured(Landroid/view/View;I)V

    .line 464
    const/4 v0, 0x1

    invoke-virtual {p0, v0}, Lcom/miui/internal/widget/ViewDragHelper;->V(I)V

    .line 465
    return-void
.end method

.method public checkTouchSlop(I)Z
    .locals 4

    .prologue
    const/4 v0, 0x0

    .line 1274
    iget-object v1, p0, Lcom/miui/internal/widget/ViewDragHelper;->zc:[F

    array-length v2, v1

    move v1, v0

    .line 1275
    :goto_0
    if-ge v1, v2, :cond_0

    .line 1276
    invoke-virtual {p0, p1, v1}, Lcom/miui/internal/widget/ViewDragHelper;->checkTouchSlop(II)Z

    move-result v3

    if-eqz v3, :cond_1

    .line 1277
    const/4 v0, 0x1

    .line 1280
    :cond_0
    return v0

    .line 1275
    :cond_1
    add-int/lit8 v1, v1, 0x1

    goto :goto_0
.end method

.method public checkTouchSlop(II)Z
    .locals 7

    .prologue
    const/4 v1, 0x1

    const/4 v2, 0x0

    .line 1299
    invoke-virtual {p0, p2}, Lcom/miui/internal/widget/ViewDragHelper;->isPointerDown(I)Z

    move-result v0

    if-nez v0, :cond_1

    move v1, v2

    .line 1316
    :cond_0
    :goto_0
    return v1

    .line 1303
    :cond_1
    and-int/lit8 v0, p1, 0x1

    if-ne v0, v1, :cond_2

    move v3, v1

    .line 1304
    :goto_1
    and-int/lit8 v0, p1, 0x2

    const/4 v4, 0x2

    if-ne v0, v4, :cond_3

    move v0, v1

    .line 1306
    :goto_2
    iget-object v4, p0, Lcom/miui/internal/widget/ViewDragHelper;->ze:[F

    aget v4, v4, p2

    iget-object v5, p0, Lcom/miui/internal/widget/ViewDragHelper;->zc:[F

    aget v5, v5, p2

    sub-float/2addr v4, v5

    .line 1307
    iget-object v5, p0, Lcom/miui/internal/widget/ViewDragHelper;->zf:[F

    aget v5, v5, p2

    iget-object v6, p0, Lcom/miui/internal/widget/ViewDragHelper;->zd:[F

    aget v6, v6, p2

    sub-float/2addr v5, v6

    .line 1309
    if-eqz v3, :cond_4

    if-eqz v0, :cond_4

    .line 1310
    mul-float v0, v4, v4

    mul-float v3, v5, v5

    add-float/2addr v0, v3

    iget v3, p0, Lcom/miui/internal/widget/ViewDragHelper;->cT:I

    iget v4, p0, Lcom/miui/internal/widget/ViewDragHelper;->cT:I

    mul-int/2addr v3, v4

    int-to-float v3, v3

    cmpl-float v0, v0, v3

    if-gtz v0, :cond_0

    move v1, v2

    goto :goto_0

    :cond_2
    move v3, v2

    .line 1303
    goto :goto_1

    :cond_3
    move v0, v2

    .line 1304
    goto :goto_2

    .line 1311
    :cond_4
    if-eqz v3, :cond_5

    .line 1312
    invoke-static {v4}, Ljava/lang/Math;->abs(F)F

    move-result v0

    iget v3, p0, Lcom/miui/internal/widget/ViewDragHelper;->cT:I

    int-to-float v3, v3

    cmpl-float v0, v0, v3

    if-gtz v0, :cond_0

    move v1, v2

    goto :goto_0

    .line 1313
    :cond_5
    if-eqz v0, :cond_6

    .line 1314
    invoke-static {v5}, Ljava/lang/Math;->abs(F)F

    move-result v0

    iget v3, p0, Lcom/miui/internal/widget/ViewDragHelper;->cT:I

    int-to-float v3, v3

    cmpl-float v0, v0, v3

    if-gtz v0, :cond_0

    move v1, v2

    goto :goto_0

    :cond_6
    move v1, v2

    .line 1316
    goto :goto_0
.end method

.method public continueSettling(Z)Z
    .locals 9

    .prologue
    const/4 v8, 0x2

    const/4 v6, 0x0

    .line 711
    iget v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->zb:I

    if-ne v0, v8, :cond_4

    .line 712
    iget-object v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->cL:Landroid/widget/Scroller;

    invoke-virtual {v0}, Landroid/widget/Scroller;->computeScrollOffset()Z

    move-result v7

    .line 713
    iget-object v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->cL:Landroid/widget/Scroller;

    invoke-virtual {v0}, Landroid/widget/Scroller;->getCurrX()I

    move-result v2

    .line 714
    iget-object v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->cL:Landroid/widget/Scroller;

    invoke-virtual {v0}, Landroid/widget/Scroller;->getCurrY()I

    move-result v3

    .line 715
    iget-object v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->zp:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->getLeft()I

    move-result v0

    sub-int v4, v2, v0

    .line 716
    iget-object v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->zp:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->getTop()I

    move-result v0

    sub-int v5, v3, v0

    .line 718
    if-eqz v4, :cond_0

    .line 719
    iget-object v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->zp:Landroid/view/View;

    invoke-virtual {v0, v4}, Landroid/view/View;->offsetLeftAndRight(I)V

    .line 721
    :cond_0
    if-eqz v5, :cond_1

    .line 722
    iget-object v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->zp:Landroid/view/View;

    invoke-virtual {v0, v5}, Landroid/view/View;->offsetTopAndBottom(I)V

    .line 725
    :cond_1
    if-nez v4, :cond_2

    if-eqz v5, :cond_3

    .line 726
    :cond_2
    iget-object v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->zo:Lcom/miui/internal/widget/ViewDragHelper$Callback;

    iget-object v1, p0, Lcom/miui/internal/widget/ViewDragHelper;->zp:Landroid/view/View;

    invoke-virtual/range {v0 .. v5}, Lcom/miui/internal/widget/ViewDragHelper$Callback;->onViewPositionChanged(Landroid/view/View;IIII)V

    .line 729
    :cond_3
    if-eqz v7, :cond_7

    iget-object v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->cL:Landroid/widget/Scroller;

    invoke-virtual {v0}, Landroid/widget/Scroller;->getFinalX()I

    move-result v0

    if-ne v2, v0, :cond_7

    iget-object v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->cL:Landroid/widget/Scroller;

    invoke-virtual {v0}, Landroid/widget/Scroller;->getFinalY()I

    move-result v0

    if-ne v3, v0, :cond_7

    .line 732
    iget-object v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->cL:Landroid/widget/Scroller;

    invoke-virtual {v0}, Landroid/widget/Scroller;->abortAnimation()V

    .line 733
    iget-object v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->cL:Landroid/widget/Scroller;

    invoke-virtual {v0}, Landroid/widget/Scroller;->isFinished()Z

    move-result v0

    .line 736
    :goto_0
    if-nez v0, :cond_4

    .line 737
    if-eqz p1, :cond_5

    .line 738
    iget-object v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->zr:Landroid/view/ViewGroup;

    iget-object v1, p0, Lcom/miui/internal/widget/ViewDragHelper;->zt:Ljava/lang/Runnable;

    invoke-virtual {v0, v1}, Landroid/view/ViewGroup;->post(Ljava/lang/Runnable;)Z

    .line 745
    :cond_4
    :goto_1
    iget v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->zb:I

    if-ne v0, v8, :cond_6

    const/4 v0, 0x1

    :goto_2
    return v0

    .line 740
    :cond_5
    invoke-virtual {p0, v6}, Lcom/miui/internal/widget/ViewDragHelper;->V(I)V

    goto :goto_1

    :cond_6
    move v0, v6

    .line 745
    goto :goto_2

    :cond_7
    move v0, v7

    goto :goto_0
.end method

.method public findTopChildUnder(II)Landroid/view/View;
    .locals 3

    .prologue
    .line 1420
    iget-object v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->zr:Landroid/view/ViewGroup;

    invoke-virtual {v0}, Landroid/view/ViewGroup;->getChildCount()I

    move-result v0

    .line 1421
    add-int/lit8 v0, v0, -0x1

    move v1, v0

    :goto_0
    if-ltz v1, :cond_1

    .line 1422
    iget-object v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->zr:Landroid/view/ViewGroup;

    iget-object v2, p0, Lcom/miui/internal/widget/ViewDragHelper;->zo:Lcom/miui/internal/widget/ViewDragHelper$Callback;

    invoke-virtual {v2, v1}, Lcom/miui/internal/widget/ViewDragHelper$Callback;->getOrderedChildIndex(I)I

    move-result v2

    invoke-virtual {v0, v2}, Landroid/view/ViewGroup;->getChildAt(I)Landroid/view/View;

    move-result-object v0

    .line 1423
    invoke-virtual {v0}, Landroid/view/View;->getLeft()I

    move-result v2

    if-lt p1, v2, :cond_0

    invoke-virtual {v0}, Landroid/view/View;->getRight()I

    move-result v2

    if-ge p1, v2, :cond_0

    invoke-virtual {v0}, Landroid/view/View;->getTop()I

    move-result v2

    if-lt p2, v2, :cond_0

    invoke-virtual {v0}, Landroid/view/View;->getBottom()I

    move-result v2

    if-ge p2, v2, :cond_0

    .line 1428
    :goto_1
    return-object v0

    .line 1421
    :cond_0
    add-int/lit8 v0, v1, -0x1

    move v1, v0

    goto :goto_0

    .line 1428
    :cond_1
    const/4 v0, 0x0

    goto :goto_1
.end method

.method public flingCapturedView(IIII)V
    .locals 9

    .prologue
    .line 686
    iget-boolean v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->zq:Z

    if-nez v0, :cond_0

    .line 687
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "Cannot flingCapturedView outside of a call to Callback#onViewReleased"

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 691
    :cond_0
    iget-object v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->cL:Landroid/widget/Scroller;

    iget-object v1, p0, Lcom/miui/internal/widget/ViewDragHelper;->zp:Landroid/view/View;

    invoke-virtual {v1}, Landroid/view/View;->getLeft()I

    move-result v1

    iget-object v2, p0, Lcom/miui/internal/widget/ViewDragHelper;->zp:Landroid/view/View;

    invoke-virtual {v2}, Landroid/view/View;->getTop()I

    move-result v2

    iget-object v3, p0, Lcom/miui/internal/widget/ViewDragHelper;->bR:Landroid/view/VelocityTracker;

    iget v4, p0, Lcom/miui/internal/widget/ViewDragHelper;->cV:I

    invoke-virtual {v3, v4}, Landroid/view/VelocityTracker;->getXVelocity(I)F

    move-result v3

    float-to-int v3, v3

    iget-object v4, p0, Lcom/miui/internal/widget/ViewDragHelper;->bR:Landroid/view/VelocityTracker;

    iget v5, p0, Lcom/miui/internal/widget/ViewDragHelper;->cV:I

    invoke-virtual {v4, v5}, Landroid/view/VelocityTracker;->getYVelocity(I)F

    move-result v4

    float-to-int v4, v4

    move v5, p1

    move v6, p3

    move v7, p2

    move v8, p4

    invoke-virtual/range {v0 .. v8}, Landroid/widget/Scroller;->fling(IIIIIIII)V

    .line 696
    const/4 v0, 0x2

    invoke-virtual {p0, v0}, Lcom/miui/internal/widget/ViewDragHelper;->V(I)V

    .line 697
    return-void
.end method

.method public getActivePointerId()I
    .locals 1

    .prologue
    .line 479
    iget v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->cV:I

    return v0
.end method

.method public getCapturedView()Landroid/view/View;
    .locals 1

    .prologue
    .line 471
    iget-object v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->zp:Landroid/view/View;

    return-object v0
.end method

.method public getEdgeSize()I
    .locals 1

    .prologue
    .line 444
    iget v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->zm:I

    return v0
.end method

.method public getMinVelocity()F
    .locals 1

    .prologue
    .line 408
    iget v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->zl:F

    return v0
.end method

.method public getTouchSlop()I
    .locals 1

    .prologue
    .line 486
    iget v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->cT:I

    return v0
.end method

.method public getViewDragState()I
    .locals 1

    .prologue
    .line 417
    iget v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->zb:I

    return v0
.end method

.method public isCapturedViewUnder(II)Z
    .locals 1

    .prologue
    .line 1395
    iget-object v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->zp:Landroid/view/View;

    invoke-virtual {p0, v0, p1, p2}, Lcom/miui/internal/widget/ViewDragHelper;->isViewUnder(Landroid/view/View;II)Z

    move-result v0

    return v0
.end method

.method public isEdgeTouched(I)Z
    .locals 4

    .prologue
    const/4 v0, 0x0

    .line 1329
    iget-object v1, p0, Lcom/miui/internal/widget/ViewDragHelper;->zg:[I

    array-length v2, v1

    move v1, v0

    .line 1330
    :goto_0
    if-ge v1, v2, :cond_0

    .line 1331
    invoke-virtual {p0, p1, v1}, Lcom/miui/internal/widget/ViewDragHelper;->isEdgeTouched(II)Z

    move-result v3

    if-eqz v3, :cond_1

    .line 1332
    const/4 v0, 0x1

    .line 1335
    :cond_0
    return v0

    .line 1330
    :cond_1
    add-int/lit8 v1, v1, 0x1

    goto :goto_0
.end method

.method public isEdgeTouched(II)Z
    .locals 1

    .prologue
    .line 1349
    invoke-virtual {p0, p2}, Lcom/miui/internal/widget/ViewDragHelper;->isPointerDown(I)Z

    move-result v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->zg:[I

    aget v0, v0, p2

    and-int/2addr v0, p1

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public isPointerDown(I)Z
    .locals 3

    .prologue
    const/4 v0, 0x1

    .line 856
    iget v1, p0, Lcom/miui/internal/widget/ViewDragHelper;->zj:I

    shl-int v2, v0, p1

    and-int/2addr v1, v2

    if-eqz v1, :cond_0

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public isViewUnder(Landroid/view/View;II)Z
    .locals 1

    .prologue
    .line 1408
    if-eqz p1, :cond_0

    invoke-virtual {p1}, Landroid/view/View;->getLeft()I

    move-result v0

    if-lt p2, v0, :cond_0

    invoke-virtual {p1}, Landroid/view/View;->getRight()I

    move-result v0

    if-ge p2, v0, :cond_0

    invoke-virtual {p1}, Landroid/view/View;->getTop()I

    move-result v0

    if-lt p3, v0, :cond_0

    invoke-virtual {p1}, Landroid/view/View;->getBottom()I

    move-result v0

    if-ge p3, v0, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public processTouchEvent(Landroid/view/MotionEvent;)V
    .locals 9

    .prologue
    const/4 v1, -0x1

    const/4 v5, 0x0

    const/4 v0, 0x0

    const/4 v8, 0x1

    .line 1042
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getActionMasked()I

    move-result v2

    .line 1043
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getActionIndex()I

    move-result v3

    .line 1045
    if-nez v2, :cond_0

    .line 1048
    invoke-virtual {p0}, Lcom/miui/internal/widget/ViewDragHelper;->cancel()V

    .line 1051
    :cond_0
    iget-object v4, p0, Lcom/miui/internal/widget/ViewDragHelper;->bR:Landroid/view/VelocityTracker;

    if-nez v4, :cond_1

    .line 1052
    invoke-static {}, Landroid/view/VelocityTracker;->obtain()Landroid/view/VelocityTracker;

    move-result-object v4

    iput-object v4, p0, Lcom/miui/internal/widget/ViewDragHelper;->bR:Landroid/view/VelocityTracker;

    .line 1054
    :cond_1
    iget-object v4, p0, Lcom/miui/internal/widget/ViewDragHelper;->bR:Landroid/view/VelocityTracker;

    invoke-virtual {v4, p1}, Landroid/view/VelocityTracker;->addMovement(Landroid/view/MotionEvent;)V

    .line 1056
    packed-switch v2, :pswitch_data_0

    .line 1192
    :cond_2
    :goto_0
    :pswitch_0
    return-void

    .line 1058
    :pswitch_1
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getX()F

    move-result v1

    .line 1059
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getY()F

    move-result v2

    .line 1060
    invoke-virtual {p1, v0}, Landroid/view/MotionEvent;->getPointerId(I)I

    move-result v0

    .line 1061
    float-to-int v3, v1

    float-to-int v4, v2

    invoke-virtual {p0, v3, v4}, Lcom/miui/internal/widget/ViewDragHelper;->findTopChildUnder(II)Landroid/view/View;

    move-result-object v3

    .line 1063
    invoke-direct {p0, v1, v2, v0}, Lcom/miui/internal/widget/ViewDragHelper;->b(FFI)V

    .line 1068
    invoke-virtual {p0, v3, v0}, Lcom/miui/internal/widget/ViewDragHelper;->a(Landroid/view/View;I)Z

    .line 1070
    iget-object v1, p0, Lcom/miui/internal/widget/ViewDragHelper;->zg:[I

    aget v1, v1, v0

    .line 1071
    iget v2, p0, Lcom/miui/internal/widget/ViewDragHelper;->zn:I

    and-int/2addr v2, v1

    if-eqz v2, :cond_2

    .line 1072
    iget-object v2, p0, Lcom/miui/internal/widget/ViewDragHelper;->zo:Lcom/miui/internal/widget/ViewDragHelper$Callback;

    iget v3, p0, Lcom/miui/internal/widget/ViewDragHelper;->zn:I

    and-int/2addr v1, v3

    invoke-virtual {v2, v1, v0}, Lcom/miui/internal/widget/ViewDragHelper$Callback;->onEdgeTouched(II)V

    goto :goto_0

    .line 1078
    :pswitch_2
    invoke-virtual {p1, v3}, Landroid/view/MotionEvent;->getPointerId(I)I

    move-result v0

    .line 1079
    invoke-virtual {p1, v3}, Landroid/view/MotionEvent;->getX(I)F

    move-result v1

    .line 1080
    invoke-virtual {p1, v3}, Landroid/view/MotionEvent;->getY(I)F

    move-result v2

    .line 1082
    invoke-direct {p0, v1, v2, v0}, Lcom/miui/internal/widget/ViewDragHelper;->b(FFI)V

    .line 1085
    iget v3, p0, Lcom/miui/internal/widget/ViewDragHelper;->zb:I

    if-nez v3, :cond_3

    .line 1088
    float-to-int v1, v1

    float-to-int v2, v2

    invoke-virtual {p0, v1, v2}, Lcom/miui/internal/widget/ViewDragHelper;->findTopChildUnder(II)Landroid/view/View;

    move-result-object v1

    .line 1089
    invoke-virtual {p0, v1, v0}, Lcom/miui/internal/widget/ViewDragHelper;->a(Landroid/view/View;I)Z

    .line 1091
    iget-object v1, p0, Lcom/miui/internal/widget/ViewDragHelper;->zg:[I

    aget v1, v1, v0

    .line 1092
    iget v2, p0, Lcom/miui/internal/widget/ViewDragHelper;->zn:I

    and-int/2addr v2, v1

    if-eqz v2, :cond_2

    .line 1093
    iget-object v2, p0, Lcom/miui/internal/widget/ViewDragHelper;->zo:Lcom/miui/internal/widget/ViewDragHelper$Callback;

    iget v3, p0, Lcom/miui/internal/widget/ViewDragHelper;->zn:I

    and-int/2addr v1, v3

    invoke-virtual {v2, v1, v0}, Lcom/miui/internal/widget/ViewDragHelper$Callback;->onEdgeTouched(II)V

    goto :goto_0

    .line 1095
    :cond_3
    float-to-int v1, v1

    float-to-int v2, v2

    invoke-virtual {p0, v1, v2}, Lcom/miui/internal/widget/ViewDragHelper;->isCapturedViewUnder(II)Z

    move-result v1

    if-eqz v1, :cond_2

    .line 1100
    iget-object v1, p0, Lcom/miui/internal/widget/ViewDragHelper;->zp:Landroid/view/View;

    invoke-virtual {p0, v1, v0}, Lcom/miui/internal/widget/ViewDragHelper;->a(Landroid/view/View;I)Z

    goto :goto_0

    .line 1106
    :pswitch_3
    iget v1, p0, Lcom/miui/internal/widget/ViewDragHelper;->zb:I

    if-ne v1, v8, :cond_4

    .line 1107
    iget v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->cV:I

    invoke-virtual {p1, v0}, Landroid/view/MotionEvent;->findPointerIndex(I)I

    move-result v0

    .line 1108
    if-ltz v0, :cond_2

    invoke-virtual {p1}, Landroid/view/MotionEvent;->getPointerCount()I

    move-result v1

    if-ge v0, v1, :cond_2

    .line 1109
    invoke-virtual {p1, v0}, Landroid/view/MotionEvent;->getX(I)F

    move-result v1

    .line 1110
    invoke-virtual {p1, v0}, Landroid/view/MotionEvent;->getY(I)F

    move-result v0

    .line 1111
    iget-object v2, p0, Lcom/miui/internal/widget/ViewDragHelper;->ze:[F

    iget v3, p0, Lcom/miui/internal/widget/ViewDragHelper;->cV:I

    aget v2, v2, v3

    sub-float/2addr v1, v2

    float-to-int v1, v1

    .line 1112
    iget-object v2, p0, Lcom/miui/internal/widget/ViewDragHelper;->zf:[F

    iget v3, p0, Lcom/miui/internal/widget/ViewDragHelper;->cV:I

    aget v2, v2, v3

    sub-float/2addr v0, v2

    float-to-int v0, v0

    .line 1114
    iget-object v2, p0, Lcom/miui/internal/widget/ViewDragHelper;->zp:Landroid/view/View;

    invoke-virtual {v2}, Landroid/view/View;->getLeft()I

    move-result v2

    add-int/2addr v2, v1

    iget-object v3, p0, Lcom/miui/internal/widget/ViewDragHelper;->zp:Landroid/view/View;

    invoke-virtual {v3}, Landroid/view/View;->getTop()I

    move-result v3

    add-int/2addr v3, v0

    invoke-direct {p0, v2, v3, v1, v0}, Lcom/miui/internal/widget/ViewDragHelper;->d(IIII)V

    .line 1116
    invoke-direct {p0, p1}, Lcom/miui/internal/widget/ViewDragHelper;->c(Landroid/view/MotionEvent;)V

    goto/16 :goto_0

    .line 1120
    :cond_4
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getPointerCount()I

    move-result v1

    .line 1121
    :goto_1
    if-ge v0, v1, :cond_5

    .line 1122
    invoke-virtual {p1, v0}, Landroid/view/MotionEvent;->getPointerId(I)I

    move-result v2

    .line 1123
    invoke-virtual {p1, v0}, Landroid/view/MotionEvent;->getX(I)F

    move-result v3

    .line 1124
    invoke-virtual {p1, v0}, Landroid/view/MotionEvent;->getY(I)F

    move-result v4

    .line 1125
    iget-object v5, p0, Lcom/miui/internal/widget/ViewDragHelper;->zc:[F

    aget v5, v5, v2

    sub-float v5, v3, v5

    .line 1126
    iget-object v6, p0, Lcom/miui/internal/widget/ViewDragHelper;->zd:[F

    aget v6, v6, v2

    sub-float v6, v4, v6

    .line 1128
    invoke-direct {p0, v5, v6, v2}, Lcom/miui/internal/widget/ViewDragHelper;->c(FFI)V

    .line 1129
    iget v7, p0, Lcom/miui/internal/widget/ViewDragHelper;->zb:I

    if-ne v7, v8, :cond_6

    .line 1140
    :cond_5
    invoke-direct {p0, p1}, Lcom/miui/internal/widget/ViewDragHelper;->c(Landroid/view/MotionEvent;)V

    goto/16 :goto_0

    .line 1134
    :cond_6
    float-to-int v3, v3

    float-to-int v4, v4

    invoke-virtual {p0, v3, v4}, Lcom/miui/internal/widget/ViewDragHelper;->findTopChildUnder(II)Landroid/view/View;

    move-result-object v3

    .line 1135
    invoke-direct {p0, v3, v5, v6}, Lcom/miui/internal/widget/ViewDragHelper;->a(Landroid/view/View;FF)Z

    move-result v4

    if-eqz v4, :cond_7

    invoke-virtual {p0, v3, v2}, Lcom/miui/internal/widget/ViewDragHelper;->a(Landroid/view/View;I)Z

    move-result v2

    if-nez v2, :cond_5

    .line 1121
    :cond_7
    add-int/lit8 v0, v0, 0x1

    goto :goto_1

    .line 1146
    :pswitch_4
    invoke-virtual {p1, v3}, Landroid/view/MotionEvent;->getPointerId(I)I

    move-result v2

    .line 1147
    iget v3, p0, Lcom/miui/internal/widget/ViewDragHelper;->zb:I

    if-ne v3, v8, :cond_a

    iget v3, p0, Lcom/miui/internal/widget/ViewDragHelper;->cV:I

    if-ne v2, v3, :cond_a

    .line 1150
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getPointerCount()I

    move-result v3

    .line 1151
    :goto_2
    if-ge v0, v3, :cond_d

    .line 1152
    invoke-virtual {p1, v0}, Landroid/view/MotionEvent;->getPointerId(I)I

    move-result v4

    .line 1153
    iget v5, p0, Lcom/miui/internal/widget/ViewDragHelper;->cV:I

    if-ne v4, v5, :cond_9

    .line 1151
    :cond_8
    add-int/lit8 v0, v0, 0x1

    goto :goto_2

    .line 1158
    :cond_9
    invoke-virtual {p1, v0}, Landroid/view/MotionEvent;->getX(I)F

    move-result v5

    .line 1159
    invoke-virtual {p1, v0}, Landroid/view/MotionEvent;->getY(I)F

    move-result v6

    .line 1160
    float-to-int v5, v5

    float-to-int v6, v6

    invoke-virtual {p0, v5, v6}, Lcom/miui/internal/widget/ViewDragHelper;->findTopChildUnder(II)Landroid/view/View;

    move-result-object v5

    iget-object v6, p0, Lcom/miui/internal/widget/ViewDragHelper;->zp:Landroid/view/View;

    if-ne v5, v6, :cond_8

    iget-object v5, p0, Lcom/miui/internal/widget/ViewDragHelper;->zp:Landroid/view/View;

    invoke-virtual {p0, v5, v4}, Lcom/miui/internal/widget/ViewDragHelper;->a(Landroid/view/View;I)Z

    move-result v4

    if-eqz v4, :cond_8

    .line 1162
    iget v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->cV:I

    .line 1167
    :goto_3
    if-ne v0, v1, :cond_a

    .line 1169
    invoke-direct {p0}, Lcom/miui/internal/widget/ViewDragHelper;->cE()V

    .line 1172
    :cond_a
    invoke-direct {p0, v2}, Lcom/miui/internal/widget/ViewDragHelper;->T(I)V

    goto/16 :goto_0

    .line 1177
    :pswitch_5
    iget v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->zb:I

    if-ne v0, v8, :cond_b

    .line 1178
    invoke-direct {p0}, Lcom/miui/internal/widget/ViewDragHelper;->cE()V

    .line 1180
    :cond_b
    invoke-virtual {p0}, Lcom/miui/internal/widget/ViewDragHelper;->cancel()V

    goto/16 :goto_0

    .line 1185
    :pswitch_6
    iget v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->zb:I

    if-ne v0, v8, :cond_c

    .line 1186
    invoke-direct {p0, v5, v5}, Lcom/miui/internal/widget/ViewDragHelper;->a(FF)V

    .line 1188
    :cond_c
    invoke-virtual {p0}, Lcom/miui/internal/widget/ViewDragHelper;->cancel()V

    goto/16 :goto_0

    :cond_d
    move v0, v1

    goto :goto_3

    .line 1056
    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_1
        :pswitch_5
        :pswitch_3
        :pswitch_6
        :pswitch_0
        :pswitch_2
        :pswitch_4
    .end packed-switch
.end method

.method public setEdgeTrackingEnabled(I)V
    .locals 0

    .prologue
    .line 433
    iput p1, p0, Lcom/miui/internal/widget/ViewDragHelper;->zn:I

    .line 434
    return-void
.end method

.method public setMinVelocity(F)V
    .locals 0

    .prologue
    .line 397
    iput p1, p0, Lcom/miui/internal/widget/ViewDragHelper;->zl:F

    .line 398
    return-void
.end method

.method public settleCapturedViewAt(II)Z
    .locals 3

    .prologue
    .line 553
    iget-boolean v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->zq:Z

    if-nez v0, :cond_0

    .line 554
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "Cannot settleCapturedViewAt outside of a call to Callback#onViewReleased"

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 558
    :cond_0
    iget-object v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->bR:Landroid/view/VelocityTracker;

    iget v1, p0, Lcom/miui/internal/widget/ViewDragHelper;->cV:I

    invoke-virtual {v0, v1}, Landroid/view/VelocityTracker;->getXVelocity(I)F

    move-result v0

    float-to-int v0, v0

    iget-object v1, p0, Lcom/miui/internal/widget/ViewDragHelper;->bR:Landroid/view/VelocityTracker;

    iget v2, p0, Lcom/miui/internal/widget/ViewDragHelper;->cV:I

    invoke-virtual {v1, v2}, Landroid/view/VelocityTracker;->getYVelocity(I)F

    move-result v1

    float-to-int v1, v1

    invoke-direct {p0, p1, p2, v0, v1}, Lcom/miui/internal/widget/ViewDragHelper;->c(IIII)Z

    move-result v0

    return v0
.end method

.method public shouldInterceptTouchEvent(Landroid/view/MotionEvent;)Z
    .locals 10

    .prologue
    const/4 v6, 0x2

    const/4 v0, 0x1

    const/4 v1, 0x0

    .line 935
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getActionMasked()I

    move-result v2

    .line 936
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getActionIndex()I

    move-result v3

    .line 938
    if-nez v2, :cond_0

    .line 941
    invoke-virtual {p0}, Lcom/miui/internal/widget/ViewDragHelper;->cancel()V

    .line 944
    :cond_0
    iget-object v4, p0, Lcom/miui/internal/widget/ViewDragHelper;->bR:Landroid/view/VelocityTracker;

    if-nez v4, :cond_1

    .line 945
    invoke-static {}, Landroid/view/VelocityTracker;->obtain()Landroid/view/VelocityTracker;

    move-result-object v4

    iput-object v4, p0, Lcom/miui/internal/widget/ViewDragHelper;->bR:Landroid/view/VelocityTracker;

    .line 947
    :cond_1
    iget-object v4, p0, Lcom/miui/internal/widget/ViewDragHelper;->bR:Landroid/view/VelocityTracker;

    invoke-virtual {v4, p1}, Landroid/view/VelocityTracker;->addMovement(Landroid/view/MotionEvent;)V

    .line 949
    packed-switch v2, :pswitch_data_0

    .line 1032
    :cond_2
    :goto_0
    :pswitch_0
    iget v2, p0, Lcom/miui/internal/widget/ViewDragHelper;->zb:I

    if-ne v2, v0, :cond_8

    :goto_1
    return v0

    .line 951
    :pswitch_1
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getX()F

    move-result v2

    .line 952
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getY()F

    move-result v3

    .line 953
    invoke-virtual {p1, v1}, Landroid/view/MotionEvent;->getPointerId(I)I

    move-result v4

    .line 954
    invoke-direct {p0, v2, v3, v4}, Lcom/miui/internal/widget/ViewDragHelper;->b(FFI)V

    .line 956
    float-to-int v2, v2

    float-to-int v3, v3

    invoke-virtual {p0, v2, v3}, Lcom/miui/internal/widget/ViewDragHelper;->findTopChildUnder(II)Landroid/view/View;

    move-result-object v2

    .line 959
    iget-object v3, p0, Lcom/miui/internal/widget/ViewDragHelper;->zp:Landroid/view/View;

    if-ne v2, v3, :cond_3

    iget v3, p0, Lcom/miui/internal/widget/ViewDragHelper;->zb:I

    if-ne v3, v6, :cond_3

    .line 960
    invoke-virtual {p0, v2, v4}, Lcom/miui/internal/widget/ViewDragHelper;->a(Landroid/view/View;I)Z

    .line 963
    :cond_3
    iget-object v2, p0, Lcom/miui/internal/widget/ViewDragHelper;->zg:[I

    aget v2, v2, v4

    .line 964
    iget v3, p0, Lcom/miui/internal/widget/ViewDragHelper;->zn:I

    and-int/2addr v3, v2

    if-eqz v3, :cond_2

    .line 965
    iget-object v3, p0, Lcom/miui/internal/widget/ViewDragHelper;->zo:Lcom/miui/internal/widget/ViewDragHelper$Callback;

    iget v5, p0, Lcom/miui/internal/widget/ViewDragHelper;->zn:I

    and-int/2addr v2, v5

    invoke-virtual {v3, v2, v4}, Lcom/miui/internal/widget/ViewDragHelper$Callback;->onEdgeTouched(II)V

    goto :goto_0

    .line 971
    :pswitch_2
    invoke-virtual {p1, v3}, Landroid/view/MotionEvent;->getPointerId(I)I

    move-result v2

    .line 972
    invoke-virtual {p1, v3}, Landroid/view/MotionEvent;->getX(I)F

    move-result v4

    .line 973
    invoke-virtual {p1, v3}, Landroid/view/MotionEvent;->getY(I)F

    move-result v3

    .line 975
    invoke-direct {p0, v4, v3, v2}, Lcom/miui/internal/widget/ViewDragHelper;->b(FFI)V

    .line 978
    iget v5, p0, Lcom/miui/internal/widget/ViewDragHelper;->zb:I

    if-nez v5, :cond_4

    .line 979
    iget-object v3, p0, Lcom/miui/internal/widget/ViewDragHelper;->zg:[I

    aget v3, v3, v2

    .line 980
    iget v4, p0, Lcom/miui/internal/widget/ViewDragHelper;->zn:I

    and-int/2addr v4, v3

    if-eqz v4, :cond_2

    .line 981
    iget-object v4, p0, Lcom/miui/internal/widget/ViewDragHelper;->zo:Lcom/miui/internal/widget/ViewDragHelper$Callback;

    iget v5, p0, Lcom/miui/internal/widget/ViewDragHelper;->zn:I

    and-int/2addr v3, v5

    invoke-virtual {v4, v3, v2}, Lcom/miui/internal/widget/ViewDragHelper$Callback;->onEdgeTouched(II)V

    goto :goto_0

    .line 983
    :cond_4
    iget v5, p0, Lcom/miui/internal/widget/ViewDragHelper;->zb:I

    if-ne v5, v6, :cond_2

    .line 985
    float-to-int v4, v4

    float-to-int v3, v3

    invoke-virtual {p0, v4, v3}, Lcom/miui/internal/widget/ViewDragHelper;->findTopChildUnder(II)Landroid/view/View;

    move-result-object v3

    .line 986
    iget-object v4, p0, Lcom/miui/internal/widget/ViewDragHelper;->zp:Landroid/view/View;

    if-ne v3, v4, :cond_2

    .line 987
    invoke-virtual {p0, v3, v2}, Lcom/miui/internal/widget/ViewDragHelper;->a(Landroid/view/View;I)Z

    goto :goto_0

    .line 995
    :pswitch_3
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getPointerCount()I

    move-result v3

    move v2, v1

    .line 996
    :goto_2
    if-ge v2, v3, :cond_5

    .line 997
    invoke-virtual {p1, v2}, Landroid/view/MotionEvent;->getPointerId(I)I

    move-result v4

    .line 998
    invoke-virtual {p1, v2}, Landroid/view/MotionEvent;->getX(I)F

    move-result v5

    .line 999
    invoke-virtual {p1, v2}, Landroid/view/MotionEvent;->getY(I)F

    move-result v6

    .line 1000
    iget-object v7, p0, Lcom/miui/internal/widget/ViewDragHelper;->zc:[F

    aget v7, v7, v4

    sub-float v7, v5, v7

    .line 1001
    iget-object v8, p0, Lcom/miui/internal/widget/ViewDragHelper;->zd:[F

    aget v8, v8, v4

    sub-float v8, v6, v8

    .line 1003
    invoke-direct {p0, v7, v8, v4}, Lcom/miui/internal/widget/ViewDragHelper;->c(FFI)V

    .line 1004
    iget v9, p0, Lcom/miui/internal/widget/ViewDragHelper;->zb:I

    if-ne v9, v0, :cond_6

    .line 1015
    :cond_5
    invoke-direct {p0, p1}, Lcom/miui/internal/widget/ViewDragHelper;->c(Landroid/view/MotionEvent;)V

    goto/16 :goto_0

    .line 1009
    :cond_6
    float-to-int v5, v5

    float-to-int v6, v6

    invoke-virtual {p0, v5, v6}, Lcom/miui/internal/widget/ViewDragHelper;->findTopChildUnder(II)Landroid/view/View;

    move-result-object v5

    .line 1010
    if-eqz v5, :cond_7

    invoke-direct {p0, v5, v7, v8}, Lcom/miui/internal/widget/ViewDragHelper;->a(Landroid/view/View;FF)Z

    move-result v6

    if-eqz v6, :cond_7

    invoke-virtual {p0, v5, v4}, Lcom/miui/internal/widget/ViewDragHelper;->a(Landroid/view/View;I)Z

    move-result v4

    if-nez v4, :cond_5

    .line 996
    :cond_7
    add-int/lit8 v2, v2, 0x1

    goto :goto_2

    .line 1020
    :pswitch_4
    invoke-virtual {p1, v3}, Landroid/view/MotionEvent;->getPointerId(I)I

    move-result v2

    .line 1021
    invoke-direct {p0, v2}, Lcom/miui/internal/widget/ViewDragHelper;->T(I)V

    goto/16 :goto_0

    .line 1027
    :pswitch_5
    invoke-virtual {p0}, Lcom/miui/internal/widget/ViewDragHelper;->cancel()V

    goto/16 :goto_0

    :cond_8
    move v0, v1

    .line 1032
    goto/16 :goto_1

    .line 949
    nop

    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_1
        :pswitch_5
        :pswitch_3
        :pswitch_5
        :pswitch_0
        :pswitch_2
        :pswitch_4
    .end packed-switch
.end method

.method public smoothSlideViewTo(Landroid/view/View;II)Z
    .locals 2

    .prologue
    const/4 v1, 0x0

    .line 535
    iput-object p1, p0, Lcom/miui/internal/widget/ViewDragHelper;->zp:Landroid/view/View;

    .line 536
    const/4 v0, -0x1

    iput v0, p0, Lcom/miui/internal/widget/ViewDragHelper;->cV:I

    .line 538
    invoke-direct {p0, p2, p3, v1, v1}, Lcom/miui/internal/widget/ViewDragHelper;->c(IIII)Z

    move-result v0

    return v0
.end method
