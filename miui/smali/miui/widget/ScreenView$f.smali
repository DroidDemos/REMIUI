.class Lmiui/widget/ScreenView$f;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroid/view/View$OnTouchListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/widget/ScreenView;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x2
    name = "f"
.end annotation


# instance fields
.field final synthetic bW:Lmiui/widget/ScreenView;


# direct methods
.method private constructor <init>(Lmiui/widget/ScreenView;)V
    .locals 0

    .prologue
    .line 2227
    iput-object p1, p0, Lmiui/widget/ScreenView$f;->bW:Lmiui/widget/ScreenView;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method synthetic constructor <init>(Lmiui/widget/ScreenView;Lmiui/widget/n;)V
    .locals 0

    .prologue
    .line 2227
    invoke-direct {p0, p1}, Lmiui/widget/ScreenView$f;-><init>(Lmiui/widget/ScreenView;)V

    return-void
.end method


# virtual methods
.method public onTouch(Landroid/view/View;Landroid/view/MotionEvent;)Z
    .locals 5

    .prologue
    .line 2230
    invoke-virtual {p1}, Landroid/view/View;->getWidth()I

    move-result v0

    .line 2231
    const/4 v1, 0x0

    invoke-virtual {p2}, Landroid/view/MotionEvent;->getX()F

    move-result v2

    add-int/lit8 v3, v0, -0x1

    int-to-float v3, v3

    invoke-static {v2, v3}, Ljava/lang/Math;->min(FF)F

    move-result v2

    invoke-static {v1, v2}, Ljava/lang/Math;->max(FF)F

    move-result v1

    .line 2233
    iget-object v2, p0, Lmiui/widget/ScreenView$f;->bW:Lmiui/widget/ScreenView;

    invoke-virtual {v2}, Lmiui/widget/ScreenView;->getScreenCount()I

    move-result v2

    .line 2234
    int-to-float v3, v2

    mul-float/2addr v3, v1

    int-to-float v4, v0

    div-float/2addr v3, v4

    float-to-double v3, v3

    invoke-static {v3, v4}, Ljava/lang/Math;->floor(D)D

    move-result-wide v3

    double-to-int v3, v3

    .line 2235
    invoke-virtual {p2}, Landroid/view/MotionEvent;->getAction()I

    move-result v4

    packed-switch v4, :pswitch_data_0

    .line 2255
    :goto_0
    const/4 v0, 0x1

    return v0

    .line 2237
    :pswitch_0
    iget-object v0, p0, Lmiui/widget/ScreenView$f;->bW:Lmiui/widget/ScreenView;

    invoke-static {v0}, Lmiui/widget/ScreenView;->d(Lmiui/widget/ScreenView;)Landroid/widget/Scroller;

    move-result-object v0

    invoke-virtual {v0}, Landroid/widget/Scroller;->isFinished()Z

    move-result v0

    if-nez v0, :cond_0

    .line 2238
    iget-object v0, p0, Lmiui/widget/ScreenView$f;->bW:Lmiui/widget/ScreenView;

    invoke-static {v0}, Lmiui/widget/ScreenView;->d(Lmiui/widget/ScreenView;)Landroid/widget/Scroller;

    move-result-object v0

    invoke-virtual {v0}, Landroid/widget/Scroller;->abortAnimation()V

    .line 2240
    :cond_0
    iget-object v0, p0, Lmiui/widget/ScreenView$f;->bW:Lmiui/widget/ScreenView;

    const/4 v1, 0x3

    invoke-static {v0, p2, v1}, Lmiui/widget/ScreenView;->a(Lmiui/widget/ScreenView;Landroid/view/MotionEvent;I)V

    goto :goto_0

    .line 2243
    :pswitch_1
    iget-object v4, p0, Lmiui/widget/ScreenView$f;->bW:Lmiui/widget/ScreenView;

    invoke-static {v4, v3}, Lmiui/widget/ScreenView;->a(Lmiui/widget/ScreenView;I)V

    .line 2244
    iget-object v3, p0, Lmiui/widget/ScreenView$f;->bW:Lmiui/widget/ScreenView;

    iget-object v4, p0, Lmiui/widget/ScreenView$f;->bW:Lmiui/widget/ScreenView;

    iget v4, v4, Lmiui/widget/ScreenView;->mChildScreenWidth:I

    mul-int/2addr v2, v4

    int-to-float v2, v2

    mul-float/2addr v1, v2

    int-to-float v0, v0

    div-float v0, v1, v0

    iget-object v1, p0, Lmiui/widget/ScreenView$f;->bW:Lmiui/widget/ScreenView;

    iget v1, v1, Lmiui/widget/ScreenView;->mChildScreenWidth:I

    div-int/lit8 v1, v1, 0x2

    int-to-float v1, v1

    sub-float/2addr v0, v1

    float-to-int v0, v0

    const/4 v1, 0x0

    invoke-virtual {v3, v0, v1}, Lmiui/widget/ScreenView;->scrollTo(II)V

    goto :goto_0

    .line 2250
    :pswitch_2
    iget-object v0, p0, Lmiui/widget/ScreenView$f;->bW:Lmiui/widget/ScreenView;

    invoke-virtual {v0, v3}, Lmiui/widget/ScreenView;->snapToScreen(I)V

    .line 2251
    iget-object v0, p0, Lmiui/widget/ScreenView$f;->bW:Lmiui/widget/ScreenView;

    iget-object v1, p0, Lmiui/widget/ScreenView$f;->bW:Lmiui/widget/ScreenView;

    iget v1, v1, Lmiui/widget/ScreenView;->mCurrentScreen:I

    iget-object v2, p0, Lmiui/widget/ScreenView$f;->bW:Lmiui/widget/ScreenView;

    invoke-static {v2}, Lmiui/widget/ScreenView;->e(Lmiui/widget/ScreenView;)I

    move-result v2

    invoke-static {v0, v1, v2}, Lmiui/widget/ScreenView;->a(Lmiui/widget/ScreenView;II)V

    goto :goto_0

    .line 2235
    nop

    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_0
        :pswitch_2
        :pswitch_1
        :pswitch_2
    .end packed-switch
.end method
