.class Lmiui/widget/NavigationLayout$a;
.super Lcom/miui/internal/widget/ViewDragHelper$Callback;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/widget/NavigationLayout;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x2
    name = "a"
.end annotation


# instance fields
.field final synthetic ep:Lmiui/widget/NavigationLayout;


# direct methods
.method private constructor <init>(Lmiui/widget/NavigationLayout;)V
    .locals 0

    .prologue
    .line 1075
    iput-object p1, p0, Lmiui/widget/NavigationLayout$a;->ep:Lmiui/widget/NavigationLayout;

    invoke-direct {p0}, Lcom/miui/internal/widget/ViewDragHelper$Callback;-><init>()V

    return-void
.end method

.method synthetic constructor <init>(Lmiui/widget/NavigationLayout;Lmiui/widget/f;)V
    .locals 0

    .prologue
    .line 1075
    invoke-direct {p0, p1}, Lmiui/widget/NavigationLayout$a;-><init>(Lmiui/widget/NavigationLayout;)V

    return-void
.end method


# virtual methods
.method public clampViewPositionHorizontal(Landroid/view/View;II)I
    .locals 2

    .prologue
    .line 1116
    invoke-virtual {p1}, Landroid/view/View;->getWidth()I

    move-result v0

    neg-int v0, v0

    const/4 v1, 0x0

    invoke-static {p2, v1}, Ljava/lang/Math;->min(II)I

    move-result v1

    invoke-static {v0, v1}, Ljava/lang/Math;->max(II)I

    move-result v0

    return v0
.end method

.method public getViewHorizontalDragRange(Landroid/view/View;)I
    .locals 1

    .prologue
    .line 1084
    iget-object v0, p0, Lmiui/widget/NavigationLayout$a;->ep:Lmiui/widget/NavigationLayout;

    invoke-static {v0}, Lmiui/widget/NavigationLayout;->a(Lmiui/widget/NavigationLayout;)Landroid/view/View;

    move-result-object v0

    if-ne p1, v0, :cond_0

    iget-object v0, p0, Lmiui/widget/NavigationLayout$a;->ep:Lmiui/widget/NavigationLayout;

    invoke-static {v0}, Lmiui/widget/NavigationLayout;->a(Lmiui/widget/NavigationLayout;)Landroid/view/View;

    move-result-object v0

    invoke-virtual {v0}, Landroid/view/View;->getWidth()I

    move-result v0

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public onEdgeDragStarted(II)V
    .locals 2

    .prologue
    .line 1126
    iget-object v0, p0, Lmiui/widget/NavigationLayout$a;->ep:Lmiui/widget/NavigationLayout;

    invoke-virtual {v0}, Lmiui/widget/NavigationLayout;->getDrawerLockMode()I

    move-result v0

    if-nez v0, :cond_0

    .line 1127
    iget-object v0, p0, Lmiui/widget/NavigationLayout$a;->ep:Lmiui/widget/NavigationLayout;

    invoke-static {v0}, Lmiui/widget/NavigationLayout;->b(Lmiui/widget/NavigationLayout;)Lcom/miui/internal/widget/ViewDragHelper;

    move-result-object v0

    iget-object v1, p0, Lmiui/widget/NavigationLayout$a;->ep:Lmiui/widget/NavigationLayout;

    invoke-static {v1}, Lmiui/widget/NavigationLayout;->a(Lmiui/widget/NavigationLayout;)Landroid/view/View;

    move-result-object v1

    invoke-virtual {v0, v1, p2}, Lcom/miui/internal/widget/ViewDragHelper;->captureChildView(Landroid/view/View;I)V

    .line 1128
    iget-object v0, p0, Lmiui/widget/NavigationLayout$a;->ep:Lmiui/widget/NavigationLayout;

    iget-object v1, p0, Lmiui/widget/NavigationLayout$a;->ep:Lmiui/widget/NavigationLayout;

    invoke-static {v1}, Lmiui/widget/NavigationLayout;->f(Lmiui/widget/NavigationLayout;)Ljava/lang/Runnable;

    move-result-object v1

    invoke-virtual {v0, v1}, Lmiui/widget/NavigationLayout;->removeCallbacks(Ljava/lang/Runnable;)Z

    .line 1130
    :cond_0
    return-void
.end method

.method public onEdgeTouched(II)V
    .locals 4

    .prologue
    .line 1121
    iget-object v0, p0, Lmiui/widget/NavigationLayout$a;->ep:Lmiui/widget/NavigationLayout;

    iget-object v1, p0, Lmiui/widget/NavigationLayout$a;->ep:Lmiui/widget/NavigationLayout;

    invoke-static {v1}, Lmiui/widget/NavigationLayout;->f(Lmiui/widget/NavigationLayout;)Ljava/lang/Runnable;

    move-result-object v1

    const-wide/16 v2, 0x96

    invoke-virtual {v0, v1, v2, v3}, Lmiui/widget/NavigationLayout;->postDelayed(Ljava/lang/Runnable;J)Z

    .line 1122
    return-void
.end method

.method public onViewCaptured(Landroid/view/View;I)V
    .locals 2

    .prologue
    .line 1099
    invoke-virtual {p1}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v0

    check-cast v0, Lmiui/widget/NavigationLayout$LayoutParams;

    .line 1100
    const/4 v1, 0x0

    iput-boolean v1, v0, Lmiui/widget/NavigationLayout$LayoutParams;->Bl:Z

    .line 1101
    return-void
.end method

.method public onViewDragStateChanged(I)V
    .locals 1

    .prologue
    .line 1134
    iget-object v0, p0, Lmiui/widget/NavigationLayout$a;->ep:Lmiui/widget/NavigationLayout;

    invoke-static {v0}, Lmiui/widget/NavigationLayout;->g(Lmiui/widget/NavigationLayout;)Lmiui/widget/NavigationLayout$NavigationListener;

    move-result-object v0

    if-eqz v0, :cond_1

    .line 1135
    if-nez p1, :cond_0

    .line 1136
    iget-object v0, p0, Lmiui/widget/NavigationLayout$a;->ep:Lmiui/widget/NavigationLayout;

    invoke-virtual {v0}, Lmiui/widget/NavigationLayout;->isNavigationDrawerOpen()Z

    move-result v0

    if-eqz v0, :cond_2

    .line 1137
    iget-object v0, p0, Lmiui/widget/NavigationLayout$a;->ep:Lmiui/widget/NavigationLayout;

    invoke-static {v0}, Lmiui/widget/NavigationLayout;->g(Lmiui/widget/NavigationLayout;)Lmiui/widget/NavigationLayout$NavigationListener;

    move-result-object v0

    invoke-interface {v0}, Lmiui/widget/NavigationLayout$NavigationListener;->onDrawerOpened()V

    .line 1142
    :cond_0
    :goto_0
    iget-object v0, p0, Lmiui/widget/NavigationLayout$a;->ep:Lmiui/widget/NavigationLayout;

    invoke-static {v0}, Lmiui/widget/NavigationLayout;->g(Lmiui/widget/NavigationLayout;)Lmiui/widget/NavigationLayout$NavigationListener;

    move-result-object v0

    invoke-interface {v0, p1}, Lmiui/widget/NavigationLayout$NavigationListener;->onDrawerDragStateChanged(I)V

    .line 1144
    :cond_1
    return-void

    .line 1139
    :cond_2
    iget-object v0, p0, Lmiui/widget/NavigationLayout$a;->ep:Lmiui/widget/NavigationLayout;

    invoke-static {v0}, Lmiui/widget/NavigationLayout;->g(Lmiui/widget/NavigationLayout;)Lmiui/widget/NavigationLayout$NavigationListener;

    move-result-object v0

    invoke-interface {v0}, Lmiui/widget/NavigationLayout$NavigationListener;->onDrawerClosed()V

    goto :goto_0
.end method

.method public onViewPositionChanged(Landroid/view/View;IIII)V
    .locals 2

    .prologue
    .line 1089
    iget-object v0, p0, Lmiui/widget/NavigationLayout$a;->ep:Lmiui/widget/NavigationLayout;

    invoke-static {v0}, Lmiui/widget/NavigationLayout;->a(Lmiui/widget/NavigationLayout;)Landroid/view/View;

    move-result-object v0

    if-ne p1, v0, :cond_0

    .line 1090
    iget-object v0, p0, Lmiui/widget/NavigationLayout$a;->ep:Lmiui/widget/NavigationLayout;

    invoke-static {v0}, Lmiui/widget/NavigationLayout;->a(Lmiui/widget/NavigationLayout;)Landroid/view/View;

    move-result-object v0

    invoke-virtual {v0}, Landroid/view/View;->getWidth()I

    move-result v0

    .line 1091
    add-int v1, p2, v0

    int-to-float v1, v1

    int-to-float v0, v0

    div-float v0, v1, v0

    .line 1092
    iget-object v1, p0, Lmiui/widget/NavigationLayout$a;->ep:Lmiui/widget/NavigationLayout;

    invoke-static {v1, v0}, Lmiui/widget/NavigationLayout;->b(Lmiui/widget/NavigationLayout;F)V

    .line 1093
    iget-object v0, p0, Lmiui/widget/NavigationLayout$a;->ep:Lmiui/widget/NavigationLayout;

    invoke-virtual {v0}, Lmiui/widget/NavigationLayout;->invalidate()V

    .line 1095
    :cond_0
    return-void
.end method

.method public onViewReleased(Landroid/view/View;FF)V
    .locals 4

    .prologue
    const/4 v3, 0x0

    .line 1105
    iget-object v0, p0, Lmiui/widget/NavigationLayout$a;->ep:Lmiui/widget/NavigationLayout;

    invoke-static {v0}, Lmiui/widget/NavigationLayout;->e(Lmiui/widget/NavigationLayout;)F

    move-result v0

    .line 1106
    invoke-virtual {p1}, Landroid/view/View;->getWidth()I

    move-result v1

    .line 1108
    cmpl-float v2, p2, v3

    if-gtz v2, :cond_0

    cmpl-float v2, p2, v3

    if-nez v2, :cond_1

    const/high16 v2, 0x3f000000

    cmpl-float v0, v0, v2

    if-lez v0, :cond_1

    :cond_0
    const/4 v0, 0x0

    .line 1110
    :goto_0
    iget-object v1, p0, Lmiui/widget/NavigationLayout$a;->ep:Lmiui/widget/NavigationLayout;

    invoke-static {v1}, Lmiui/widget/NavigationLayout;->b(Lmiui/widget/NavigationLayout;)Lcom/miui/internal/widget/ViewDragHelper;

    move-result-object v1

    invoke-virtual {p1}, Landroid/view/View;->getTop()I

    move-result v2

    invoke-virtual {v1, v0, v2}, Lcom/miui/internal/widget/ViewDragHelper;->settleCapturedViewAt(II)Z

    .line 1111
    iget-object v0, p0, Lmiui/widget/NavigationLayout$a;->ep:Lmiui/widget/NavigationLayout;

    invoke-virtual {v0}, Lmiui/widget/NavigationLayout;->invalidate()V

    .line 1112
    return-void

    .line 1108
    :cond_1
    neg-int v0, v1

    goto :goto_0
.end method

.method public tryCaptureView(Landroid/view/View;I)Z
    .locals 1

    .prologue
    .line 1079
    iget-object v0, p0, Lmiui/widget/NavigationLayout$a;->ep:Lmiui/widget/NavigationLayout;

    invoke-static {v0}, Lmiui/widget/NavigationLayout;->a(Lmiui/widget/NavigationLayout;)Landroid/view/View;

    move-result-object v0

    if-ne p1, v0, :cond_0

    iget-object v0, p0, Lmiui/widget/NavigationLayout$a;->ep:Lmiui/widget/NavigationLayout;

    invoke-virtual {v0}, Lmiui/widget/NavigationLayout;->getDrawerLockMode()I

    move-result v0

    if-nez v0, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method
