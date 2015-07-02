.class Lmiui/widget/ScreenView$h;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroid/view/ScaleGestureDetector$OnScaleGestureListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/widget/ScreenView;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x2
    name = "h"
.end annotation


# static fields
.field private static final OX:F = 200.0f

.field private static final OY:F = 0.95f

.field private static final OZ:F = 0.8f

.field private static final Pa:F = 1.2f


# instance fields
.field final synthetic bW:Lmiui/widget/ScreenView;


# direct methods
.method private constructor <init>(Lmiui/widget/ScreenView;)V
    .locals 0

    .prologue
    .line 2259
    iput-object p1, p0, Lmiui/widget/ScreenView$h;->bW:Lmiui/widget/ScreenView;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method synthetic constructor <init>(Lmiui/widget/ScreenView;Lmiui/widget/n;)V
    .locals 0

    .prologue
    .line 2259
    invoke-direct {p0, p1}, Lmiui/widget/ScreenView$h;-><init>(Lmiui/widget/ScreenView;)V

    return-void
.end method


# virtual methods
.method public onScale(Landroid/view/ScaleGestureDetector;)Z
    .locals 5

    .prologue
    const/4 v0, 0x1

    .line 2279
    invoke-virtual {p1}, Landroid/view/ScaleGestureDetector;->getScaleFactor()F

    move-result v1

    .line 2280
    iget-object v2, p0, Lmiui/widget/ScreenView$h;->bW:Lmiui/widget/ScreenView;

    invoke-static {v2}, Lmiui/widget/ScreenView;->f(Lmiui/widget/ScreenView;)I

    move-result v2

    if-nez v2, :cond_1

    invoke-virtual {p1}, Landroid/view/ScaleGestureDetector;->getTimeDelta()J

    move-result-wide v2

    long-to-float v2, v2

    const/high16 v3, 0x43480000

    cmpl-float v2, v2, v3

    if-gtz v2, :cond_0

    const v2, 0x3f733333

    cmpg-float v2, v1, v2

    if-ltz v2, :cond_0

    const v2, 0x3f86bca2

    cmpl-float v2, v1, v2

    if-lez v2, :cond_1

    .line 2284
    :cond_0
    iget-object v2, p0, Lmiui/widget/ScreenView$h;->bW:Lmiui/widget/ScreenView;

    const/4 v3, 0x0

    const/4 v4, 0x4

    invoke-static {v2, v3, v4}, Lmiui/widget/ScreenView;->a(Lmiui/widget/ScreenView;Landroid/view/MotionEvent;I)V

    .line 2286
    :cond_1
    const v2, 0x3f4ccccd

    cmpg-float v2, v1, v2

    if-gez v2, :cond_2

    .line 2287
    iget-object v1, p0, Lmiui/widget/ScreenView$h;->bW:Lmiui/widget/ScreenView;

    invoke-virtual {v1, p1}, Lmiui/widget/ScreenView;->onPinchIn(Landroid/view/ScaleGestureDetector;)V

    .line 2294
    :goto_0
    return v0

    .line 2290
    :cond_2
    const v2, 0x3f99999a

    cmpl-float v1, v1, v2

    if-lez v1, :cond_3

    .line 2291
    iget-object v1, p0, Lmiui/widget/ScreenView$h;->bW:Lmiui/widget/ScreenView;

    invoke-virtual {v1, p1}, Lmiui/widget/ScreenView;->onPinchOut(Landroid/view/ScaleGestureDetector;)V

    goto :goto_0

    .line 2294
    :cond_3
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public onScaleBegin(Landroid/view/ScaleGestureDetector;)Z
    .locals 1

    .prologue
    .line 2271
    iget-object v0, p0, Lmiui/widget/ScreenView$h;->bW:Lmiui/widget/ScreenView;

    invoke-static {v0}, Lmiui/widget/ScreenView;->f(Lmiui/widget/ScreenView;)I

    move-result v0

    if-nez v0, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public onScaleEnd(Landroid/view/ScaleGestureDetector;)V
    .locals 1

    .prologue
    .line 2275
    iget-object v0, p0, Lmiui/widget/ScreenView$h;->bW:Lmiui/widget/ScreenView;

    invoke-virtual {v0}, Lmiui/widget/ScreenView;->finishCurrentGesture()V

    .line 2276
    return-void
.end method
