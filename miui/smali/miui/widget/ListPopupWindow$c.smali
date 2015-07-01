.class Lmiui/widget/ListPopupWindow$c;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroid/view/View$OnTouchListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/widget/ListPopupWindow;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x2
    name = "c"
.end annotation


# instance fields
.field final synthetic D:Lmiui/widget/ListPopupWindow;


# direct methods
.method private constructor <init>(Lmiui/widget/ListPopupWindow;)V
    .locals 0

    .prologue
    .line 1372
    iput-object p1, p0, Lmiui/widget/ListPopupWindow$c;->D:Lmiui/widget/ListPopupWindow;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method synthetic constructor <init>(Lmiui/widget/ListPopupWindow;Lmiui/widget/ListPopupWindow$2;)V
    .locals 0

    .prologue
    .line 1372
    invoke-direct {p0, p1}, Lmiui/widget/ListPopupWindow$c;-><init>(Lmiui/widget/ListPopupWindow;)V

    return-void
.end method


# virtual methods
.method public onTouch(Landroid/view/View;Landroid/view/MotionEvent;)Z
    .locals 4

    .prologue
    .line 1375
    invoke-virtual {p2}, Landroid/view/MotionEvent;->getAction()I

    move-result v0

    .line 1376
    invoke-virtual {p2}, Landroid/view/MotionEvent;->getX()F

    move-result v1

    float-to-int v1, v1

    .line 1377
    invoke-virtual {p2}, Landroid/view/MotionEvent;->getY()F

    move-result v2

    float-to-int v2, v2

    .line 1379
    if-nez v0, :cond_1

    iget-object v3, p0, Lmiui/widget/ListPopupWindow$c;->D:Lmiui/widget/ListPopupWindow;

    invoke-static {v3}, Lmiui/widget/ListPopupWindow;->b(Lmiui/widget/ListPopupWindow;)Lmiui/widget/ArrowPopupWindow;

    move-result-object v3

    if-eqz v3, :cond_1

    iget-object v3, p0, Lmiui/widget/ListPopupWindow$c;->D:Lmiui/widget/ListPopupWindow;

    invoke-static {v3}, Lmiui/widget/ListPopupWindow;->b(Lmiui/widget/ListPopupWindow;)Lmiui/widget/ArrowPopupWindow;

    move-result-object v3

    invoke-virtual {v3}, Lmiui/widget/ArrowPopupWindow;->isShowing()Z

    move-result v3

    if-eqz v3, :cond_1

    if-ltz v1, :cond_1

    iget-object v3, p0, Lmiui/widget/ListPopupWindow$c;->D:Lmiui/widget/ListPopupWindow;

    invoke-static {v3}, Lmiui/widget/ListPopupWindow;->b(Lmiui/widget/ListPopupWindow;)Lmiui/widget/ArrowPopupWindow;

    move-result-object v3

    invoke-virtual {v3}, Lmiui/widget/ArrowPopupWindow;->getWidth()I

    move-result v3

    if-ge v1, v3, :cond_1

    if-ltz v2, :cond_1

    iget-object v1, p0, Lmiui/widget/ListPopupWindow$c;->D:Lmiui/widget/ListPopupWindow;

    invoke-static {v1}, Lmiui/widget/ListPopupWindow;->b(Lmiui/widget/ListPopupWindow;)Lmiui/widget/ArrowPopupWindow;

    move-result-object v1

    invoke-virtual {v1}, Lmiui/widget/ArrowPopupWindow;->getHeight()I

    move-result v1

    if-ge v2, v1, :cond_1

    .line 1382
    iget-object v0, p0, Lmiui/widget/ListPopupWindow$c;->D:Lmiui/widget/ListPopupWindow;

    invoke-static {v0}, Lmiui/widget/ListPopupWindow;->d(Lmiui/widget/ListPopupWindow;)Landroid/os/Handler;

    move-result-object v0

    iget-object v1, p0, Lmiui/widget/ListPopupWindow$c;->D:Lmiui/widget/ListPopupWindow;

    invoke-static {v1}, Lmiui/widget/ListPopupWindow;->c(Lmiui/widget/ListPopupWindow;)Lmiui/widget/ListPopupWindow$f;

    move-result-object v1

    const-wide/16 v2, 0xfa

    invoke-virtual {v0, v1, v2, v3}, Landroid/os/Handler;->postDelayed(Ljava/lang/Runnable;J)Z

    .line 1386
    :cond_0
    :goto_0
    const/4 v0, 0x0

    return v0

    .line 1383
    :cond_1
    const/4 v1, 0x1

    if-ne v0, v1, :cond_0

    .line 1384
    iget-object v0, p0, Lmiui/widget/ListPopupWindow$c;->D:Lmiui/widget/ListPopupWindow;

    invoke-static {v0}, Lmiui/widget/ListPopupWindow;->d(Lmiui/widget/ListPopupWindow;)Landroid/os/Handler;

    move-result-object v0

    iget-object v1, p0, Lmiui/widget/ListPopupWindow$c;->D:Lmiui/widget/ListPopupWindow;

    invoke-static {v1}, Lmiui/widget/ListPopupWindow;->c(Lmiui/widget/ListPopupWindow;)Lmiui/widget/ListPopupWindow$f;

    move-result-object v1

    invoke-virtual {v0, v1}, Landroid/os/Handler;->removeCallbacks(Ljava/lang/Runnable;)V

    goto :goto_0
.end method
