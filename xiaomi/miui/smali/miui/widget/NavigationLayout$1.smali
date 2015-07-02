.class Lmiui/widget/NavigationLayout$1;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroid/animation/ValueAnimator$AnimatorUpdateListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lmiui/widget/NavigationLayout;->aT()Landroid/animation/ValueAnimator$AnimatorUpdateListener;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic ep:Lmiui/widget/NavigationLayout;


# direct methods
.method constructor <init>(Lmiui/widget/NavigationLayout;)V
    .locals 0

    .prologue
    .line 410
    iput-object p1, p0, Lmiui/widget/NavigationLayout$1;->ep:Lmiui/widget/NavigationLayout;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onAnimationUpdate(Landroid/animation/ValueAnimator;)V
    .locals 5

    .prologue
    .line 413
    iget-object v0, p0, Lmiui/widget/NavigationLayout$1;->ep:Lmiui/widget/NavigationLayout;

    invoke-static {v0}, Lmiui/widget/NavigationLayout;->d(Lmiui/widget/NavigationLayout;)Landroid/view/View;

    move-result-object v0

    if-eqz v0, :cond_0

    .line 414
    iget-object v1, p0, Lmiui/widget/NavigationLayout$1;->ep:Lmiui/widget/NavigationLayout;

    invoke-virtual {p1}, Landroid/animation/ValueAnimator;->getAnimatedValue()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/Float;

    invoke-virtual {v0}, Ljava/lang/Float;->floatValue()F

    move-result v0

    invoke-static {v1, v0}, Lmiui/widget/NavigationLayout;->a(Lmiui/widget/NavigationLayout;F)F

    .line 415
    iget-object v0, p0, Lmiui/widget/NavigationLayout$1;->ep:Lmiui/widget/NavigationLayout;

    iget-object v1, p0, Lmiui/widget/NavigationLayout$1;->ep:Lmiui/widget/NavigationLayout;

    invoke-static {v1}, Lmiui/widget/NavigationLayout;->d(Lmiui/widget/NavigationLayout;)Landroid/view/View;

    move-result-object v1

    invoke-virtual {v1}, Landroid/view/View;->getLeft()I

    move-result v1

    iget-object v2, p0, Lmiui/widget/NavigationLayout$1;->ep:Lmiui/widget/NavigationLayout;

    invoke-static {v2}, Lmiui/widget/NavigationLayout;->d(Lmiui/widget/NavigationLayout;)Landroid/view/View;

    move-result-object v2

    invoke-virtual {v2}, Landroid/view/View;->getTop()I

    move-result v2

    iget-object v3, p0, Lmiui/widget/NavigationLayout$1;->ep:Lmiui/widget/NavigationLayout;

    invoke-static {v3}, Lmiui/widget/NavigationLayout;->d(Lmiui/widget/NavigationLayout;)Landroid/view/View;

    move-result-object v3

    invoke-virtual {v3}, Landroid/view/View;->getRight()I

    move-result v3

    iget-object v4, p0, Lmiui/widget/NavigationLayout$1;->ep:Lmiui/widget/NavigationLayout;

    invoke-static {v4}, Lmiui/widget/NavigationLayout;->d(Lmiui/widget/NavigationLayout;)Landroid/view/View;

    move-result-object v4

    invoke-virtual {v4}, Landroid/view/View;->getBottom()I

    move-result v4

    invoke-virtual {v0, v1, v2, v3, v4}, Lmiui/widget/NavigationLayout;->postInvalidateOnAnimation(IIII)V

    .line 418
    :cond_0
    return-void
.end method
