.class Lcom/miui/internal/widget/h;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroid/animation/ValueAnimator$AnimatorUpdateListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/internal/widget/DropDownPopupWindow;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic pI:Lcom/miui/internal/widget/DropDownPopupWindow;


# direct methods
.method constructor <init>(Lcom/miui/internal/widget/DropDownPopupWindow;)V
    .locals 0

    .prologue
    .line 36
    iput-object p1, p0, Lcom/miui/internal/widget/h;->pI:Lcom/miui/internal/widget/DropDownPopupWindow;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onAnimationUpdate(Landroid/animation/ValueAnimator;)V
    .locals 3

    .prologue
    .line 39
    iget-object v0, p0, Lcom/miui/internal/widget/h;->pI:Lcom/miui/internal/widget/DropDownPopupWindow;

    invoke-static {v0}, Lcom/miui/internal/widget/DropDownPopupWindow;->a(Lcom/miui/internal/widget/DropDownPopupWindow;)Landroid/animation/ValueAnimator;

    move-result-object v0

    invoke-virtual {v0}, Landroid/animation/ValueAnimator;->getAnimatedValue()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/Float;

    check-cast v0, Ljava/lang/Float;

    invoke-virtual {v0}, Ljava/lang/Float;->floatValue()F

    move-result v0

    .line 40
    iget-object v1, p0, Lcom/miui/internal/widget/h;->pI:Lcom/miui/internal/widget/DropDownPopupWindow;

    invoke-static {v1}, Lcom/miui/internal/widget/DropDownPopupWindow;->b(Lcom/miui/internal/widget/DropDownPopupWindow;)Lcom/miui/internal/widget/DropDownPopupWindow$ContainerController;

    move-result-object v1

    if-eqz v1, :cond_0

    .line 41
    iget-object v1, p0, Lcom/miui/internal/widget/h;->pI:Lcom/miui/internal/widget/DropDownPopupWindow;

    invoke-static {v1}, Lcom/miui/internal/widget/DropDownPopupWindow;->b(Lcom/miui/internal/widget/DropDownPopupWindow;)Lcom/miui/internal/widget/DropDownPopupWindow$ContainerController;

    move-result-object v1

    iget-object v2, p0, Lcom/miui/internal/widget/h;->pI:Lcom/miui/internal/widget/DropDownPopupWindow;

    invoke-static {v2}, Lcom/miui/internal/widget/DropDownPopupWindow;->c(Lcom/miui/internal/widget/DropDownPopupWindow;)Lcom/miui/internal/widget/DropDownPopupWindow$a;

    move-result-object v2

    invoke-interface {v1, v2, v0}, Lcom/miui/internal/widget/DropDownPopupWindow$ContainerController;->onAniamtionUpdate(Landroid/view/View;F)V

    .line 43
    :cond_0
    iget-object v1, p0, Lcom/miui/internal/widget/h;->pI:Lcom/miui/internal/widget/DropDownPopupWindow;

    invoke-static {v1}, Lcom/miui/internal/widget/DropDownPopupWindow;->d(Lcom/miui/internal/widget/DropDownPopupWindow;)Lcom/miui/internal/widget/DropDownPopupWindow$ContentController;

    move-result-object v1

    if-eqz v1, :cond_1

    .line 44
    iget-object v1, p0, Lcom/miui/internal/widget/h;->pI:Lcom/miui/internal/widget/DropDownPopupWindow;

    invoke-static {v1}, Lcom/miui/internal/widget/DropDownPopupWindow;->d(Lcom/miui/internal/widget/DropDownPopupWindow;)Lcom/miui/internal/widget/DropDownPopupWindow$ContentController;

    move-result-object v1

    iget-object v2, p0, Lcom/miui/internal/widget/h;->pI:Lcom/miui/internal/widget/DropDownPopupWindow;

    invoke-static {v2}, Lcom/miui/internal/widget/DropDownPopupWindow;->e(Lcom/miui/internal/widget/DropDownPopupWindow;)Landroid/view/View;

    move-result-object v2

    invoke-interface {v1, v2, v0}, Lcom/miui/internal/widget/DropDownPopupWindow$ContentController;->onAniamtionUpdate(Landroid/view/View;F)V

    .line 46
    :cond_1
    return-void
.end method
