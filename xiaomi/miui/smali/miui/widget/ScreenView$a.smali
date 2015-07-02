.class Lmiui/widget/ScreenView$a;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/widget/ScreenView;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x2
    name = "a"
.end annotation


# static fields
.field private static final bQ:F = 3.0f


# instance fields
.field private bR:Landroid/view/VelocityTracker;

.field private bS:I

.field private bT:F

.field private bU:F

.field private bV:F

.field final synthetic bW:Lmiui/widget/ScreenView;


# direct methods
.method private constructor <init>(Lmiui/widget/ScreenView;)V
    .locals 2

    .prologue
    const/high16 v1, -0x40800000

    .line 1979
    iput-object p1, p0, Lmiui/widget/ScreenView$a;->bW:Lmiui/widget/ScreenView;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 1985
    const/4 v0, -0x1

    iput v0, p0, Lmiui/widget/ScreenView$a;->bS:I

    .line 1987
    iput v1, p0, Lmiui/widget/ScreenView$a;->bT:F

    .line 1989
    iput v1, p0, Lmiui/widget/ScreenView$a;->bU:F

    .line 1991
    iput v1, p0, Lmiui/widget/ScreenView$a;->bV:F

    return-void
.end method

.method synthetic constructor <init>(Lmiui/widget/ScreenView;Lmiui/widget/n;)V
    .locals 0

    .prologue
    .line 1979
    invoke-direct {p0, p1}, Lmiui/widget/ScreenView$a;-><init>(Lmiui/widget/ScreenView;)V

    return-void
.end method

.method private reset()V
    .locals 1

    .prologue
    .line 2045
    const/4 v0, -0x1

    iput v0, p0, Lmiui/widget/ScreenView$a;->bS:I

    int-to-float v0, v0

    iput v0, p0, Lmiui/widget/ScreenView$a;->bT:F

    iput v0, p0, Lmiui/widget/ScreenView$a;->bU:F

    iput v0, p0, Lmiui/widget/ScreenView$a;->bV:F

    .line 2046
    return-void
.end method


# virtual methods
.method public a(III)F
    .locals 2

    .prologue
    .line 2059
    iget-object v0, p0, Lmiui/widget/ScreenView$a;->bR:Landroid/view/VelocityTracker;

    int-to-float v1, p2

    invoke-virtual {v0, p1, v1}, Landroid/view/VelocityTracker;->computeCurrentVelocity(IF)V

    .line 2060
    iget-object v0, p0, Lmiui/widget/ScreenView$a;->bR:Landroid/view/VelocityTracker;

    invoke-virtual {v0, p3}, Landroid/view/VelocityTracker;->getXVelocity(I)F

    move-result v0

    return v0
.end method

.method public a(F)I
    .locals 5

    .prologue
    const/4 v1, 0x2

    const/4 v0, 0x1

    const/4 v2, 0x3

    .line 2064
    const/high16 v3, 0x43960000

    cmpl-float v3, p1, v3

    if-lez v3, :cond_6

    .line 2065
    iget v3, p0, Lmiui/widget/ScreenView$a;->bU:F

    const/4 v4, 0x0

    cmpg-float v3, v3, v4

    if-gez v3, :cond_2

    .line 2066
    iget v2, p0, Lmiui/widget/ScreenView$a;->bV:F

    iget v3, p0, Lmiui/widget/ScreenView$a;->bT:F

    cmpl-float v2, v2, v3

    if-lez v2, :cond_1

    .line 2082
    :cond_0
    :goto_0
    return v0

    :cond_1
    move v0, v1

    .line 2066
    goto :goto_0

    .line 2068
    :cond_2
    iget v3, p0, Lmiui/widget/ScreenView$a;->bV:F

    iget v4, p0, Lmiui/widget/ScreenView$a;->bU:F

    cmpg-float v3, v3, v4

    if-gez v3, :cond_4

    .line 2069
    iget-object v0, p0, Lmiui/widget/ScreenView$a;->bW:Lmiui/widget/ScreenView;

    invoke-virtual {v0}, Lmiui/widget/ScreenView;->getScrollX()I

    move-result v0

    iget-object v3, p0, Lmiui/widget/ScreenView$a;->bW:Lmiui/widget/ScreenView;

    invoke-virtual {v3}, Lmiui/widget/ScreenView;->getCurrentScreen()Landroid/view/View;

    move-result-object v3

    invoke-virtual {v3}, Landroid/view/View;->getLeft()I

    move-result v3

    if-ge v0, v3, :cond_3

    move v0, v2

    .line 2070
    goto :goto_0

    :cond_3
    move v0, v1

    .line 2072
    goto :goto_0

    .line 2074
    :cond_4
    iget v1, p0, Lmiui/widget/ScreenView$a;->bV:F

    iget v3, p0, Lmiui/widget/ScreenView$a;->bU:F

    cmpl-float v1, v1, v3

    if-lez v1, :cond_5

    .line 2075
    iget-object v1, p0, Lmiui/widget/ScreenView$a;->bW:Lmiui/widget/ScreenView;

    invoke-virtual {v1}, Lmiui/widget/ScreenView;->getScrollX()I

    move-result v1

    iget-object v3, p0, Lmiui/widget/ScreenView$a;->bW:Lmiui/widget/ScreenView;

    invoke-virtual {v3}, Lmiui/widget/ScreenView;->getCurrentScreen()Landroid/view/View;

    move-result-object v3

    invoke-virtual {v3}, Landroid/view/View;->getLeft()I

    move-result v3

    if-le v1, v3, :cond_0

    move v0, v2

    .line 2076
    goto :goto_0

    :cond_5
    move v0, v2

    .line 2080
    goto :goto_0

    .line 2082
    :cond_6
    const/4 v0, 0x4

    goto :goto_0
.end method

.method public addMovement(Landroid/view/MotionEvent;)V
    .locals 5

    .prologue
    const/high16 v4, 0x40400000

    const/4 v3, -0x1

    const/4 v2, 0x0

    .line 2002
    iget-object v0, p0, Lmiui/widget/ScreenView$a;->bR:Landroid/view/VelocityTracker;

    if-nez v0, :cond_0

    .line 2003
    invoke-static {}, Landroid/view/VelocityTracker;->obtain()Landroid/view/VelocityTracker;

    move-result-object v0

    iput-object v0, p0, Lmiui/widget/ScreenView$a;->bR:Landroid/view/VelocityTracker;

    .line 2005
    :cond_0
    iget-object v0, p0, Lmiui/widget/ScreenView$a;->bR:Landroid/view/VelocityTracker;

    invoke-virtual {v0, p1}, Landroid/view/VelocityTracker;->addMovement(Landroid/view/MotionEvent;)V

    .line 2006
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getX()F

    move-result v0

    .line 2007
    iget v1, p0, Lmiui/widget/ScreenView$a;->bS:I

    if-eq v1, v3, :cond_1

    .line 2008
    iget v1, p0, Lmiui/widget/ScreenView$a;->bS:I

    invoke-virtual {p1, v1}, Landroid/view/MotionEvent;->findPointerIndex(I)I

    move-result v1

    .line 2009
    if-eq v1, v3, :cond_2

    .line 2010
    invoke-virtual {p1, v1}, Landroid/view/MotionEvent;->getX(I)F

    move-result v0

    .line 2015
    :cond_1
    :goto_0
    iget v1, p0, Lmiui/widget/ScreenView$a;->bT:F

    cmpg-float v1, v1, v2

    if-gez v1, :cond_3

    .line 2016
    iput v0, p0, Lmiui/widget/ScreenView$a;->bT:F

    .line 2042
    :goto_1
    return-void

    .line 2012
    :cond_2
    iput v3, p0, Lmiui/widget/ScreenView$a;->bS:I

    goto :goto_0

    .line 2019
    :cond_3
    iget v1, p0, Lmiui/widget/ScreenView$a;->bV:F

    cmpg-float v1, v1, v2

    if-gez v1, :cond_4

    .line 2020
    iput v0, p0, Lmiui/widget/ScreenView$a;->bV:F

    goto :goto_1

    .line 2023
    :cond_4
    iget v1, p0, Lmiui/widget/ScreenView$a;->bU:F

    cmpg-float v1, v1, v2

    if-gez v1, :cond_8

    .line 2024
    iget v1, p0, Lmiui/widget/ScreenView$a;->bV:F

    iget v2, p0, Lmiui/widget/ScreenView$a;->bT:F

    cmpl-float v1, v1, v2

    if-lez v1, :cond_5

    iget v1, p0, Lmiui/widget/ScreenView$a;->bV:F

    cmpg-float v1, v0, v1

    if-ltz v1, :cond_6

    :cond_5
    iget v1, p0, Lmiui/widget/ScreenView$a;->bV:F

    iget v2, p0, Lmiui/widget/ScreenView$a;->bT:F

    cmpg-float v1, v1, v2

    if-gez v1, :cond_7

    iget v1, p0, Lmiui/widget/ScreenView$a;->bV:F

    cmpl-float v1, v0, v1

    if-lez v1, :cond_7

    .line 2026
    :cond_6
    iget v1, p0, Lmiui/widget/ScreenView$a;->bT:F

    sub-float v1, v0, v1

    invoke-static {v1}, Ljava/lang/Math;->abs(F)F

    move-result v1

    cmpl-float v1, v1, v4

    if-lez v1, :cond_7

    .line 2027
    iget v1, p0, Lmiui/widget/ScreenView$a;->bV:F

    iput v1, p0, Lmiui/widget/ScreenView$a;->bU:F

    .line 2041
    :cond_7
    :goto_2
    iput v0, p0, Lmiui/widget/ScreenView$a;->bV:F

    goto :goto_1

    .line 2031
    :cond_8
    iget v1, p0, Lmiui/widget/ScreenView$a;->bU:F

    iget v2, p0, Lmiui/widget/ScreenView$a;->bV:F

    cmpl-float v1, v1, v2

    if-eqz v1, :cond_7

    .line 2032
    iget v1, p0, Lmiui/widget/ScreenView$a;->bV:F

    iget v2, p0, Lmiui/widget/ScreenView$a;->bU:F

    cmpl-float v1, v1, v2

    if-lez v1, :cond_9

    iget v1, p0, Lmiui/widget/ScreenView$a;->bV:F

    cmpg-float v1, v0, v1

    if-ltz v1, :cond_a

    :cond_9
    iget v1, p0, Lmiui/widget/ScreenView$a;->bV:F

    iget v2, p0, Lmiui/widget/ScreenView$a;->bU:F

    cmpg-float v1, v1, v2

    if-gez v1, :cond_7

    iget v1, p0, Lmiui/widget/ScreenView$a;->bV:F

    cmpl-float v1, v0, v1

    if-lez v1, :cond_7

    .line 2034
    :cond_a
    iget v1, p0, Lmiui/widget/ScreenView$a;->bU:F

    sub-float v1, v0, v1

    invoke-static {v1}, Ljava/lang/Math;->abs(F)F

    move-result v1

    cmpl-float v1, v1, v4

    if-lez v1, :cond_7

    .line 2035
    iget v1, p0, Lmiui/widget/ScreenView$a;->bU:F

    iput v1, p0, Lmiui/widget/ScreenView$a;->bT:F

    .line 2036
    iget v1, p0, Lmiui/widget/ScreenView$a;->bV:F

    iput v1, p0, Lmiui/widget/ScreenView$a;->bU:F

    goto :goto_2
.end method

.method public init(I)V
    .locals 1

    .prologue
    .line 2049
    iget-object v0, p0, Lmiui/widget/ScreenView$a;->bR:Landroid/view/VelocityTracker;

    if-nez v0, :cond_0

    .line 2050
    invoke-static {}, Landroid/view/VelocityTracker;->obtain()Landroid/view/VelocityTracker;

    move-result-object v0

    iput-object v0, p0, Lmiui/widget/ScreenView$a;->bR:Landroid/view/VelocityTracker;

    .line 2054
    :goto_0
    invoke-direct {p0}, Lmiui/widget/ScreenView$a;->reset()V

    .line 2055
    iput p1, p0, Lmiui/widget/ScreenView$a;->bS:I

    .line 2056
    return-void

    .line 2052
    :cond_0
    iget-object v0, p0, Lmiui/widget/ScreenView$a;->bR:Landroid/view/VelocityTracker;

    invoke-virtual {v0}, Landroid/view/VelocityTracker;->clear()V

    goto :goto_0
.end method

.method public recycle()V
    .locals 1

    .prologue
    .line 1994
    iget-object v0, p0, Lmiui/widget/ScreenView$a;->bR:Landroid/view/VelocityTracker;

    if-eqz v0, :cond_0

    .line 1995
    iget-object v0, p0, Lmiui/widget/ScreenView$a;->bR:Landroid/view/VelocityTracker;

    invoke-virtual {v0}, Landroid/view/VelocityTracker;->recycle()V

    .line 1996
    const/4 v0, 0x0

    iput-object v0, p0, Lmiui/widget/ScreenView$a;->bR:Landroid/view/VelocityTracker;

    .line 1998
    :cond_0
    invoke-direct {p0}, Lmiui/widget/ScreenView$a;->reset()V

    .line 1999
    return-void
.end method
