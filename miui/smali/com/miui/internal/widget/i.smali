.class Lcom/miui/internal/widget/i;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroid/animation/Animator$AnimatorListener;


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
    .line 48
    iput-object p1, p0, Lcom/miui/internal/widget/i;->pI:Lcom/miui/internal/widget/DropDownPopupWindow;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method private bm()V
    .locals 1

    .prologue
    .line 51
    iget-object v0, p0, Lcom/miui/internal/widget/i;->pI:Lcom/miui/internal/widget/DropDownPopupWindow;

    invoke-static {v0}, Lcom/miui/internal/widget/DropDownPopupWindow;->f(Lcom/miui/internal/widget/DropDownPopupWindow;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 52
    iget-object v0, p0, Lcom/miui/internal/widget/i;->pI:Lcom/miui/internal/widget/DropDownPopupWindow;

    invoke-static {v0}, Lcom/miui/internal/widget/DropDownPopupWindow;->g(Lcom/miui/internal/widget/DropDownPopupWindow;)V

    .line 54
    :cond_0
    return-void
.end method


# virtual methods
.method public onAnimationCancel(Landroid/animation/Animator;)V
    .locals 0

    .prologue
    .line 70
    invoke-direct {p0}, Lcom/miui/internal/widget/i;->bm()V

    .line 71
    return-void
.end method

.method public onAnimationEnd(Landroid/animation/Animator;)V
    .locals 0

    .prologue
    .line 65
    invoke-direct {p0}, Lcom/miui/internal/widget/i;->bm()V

    .line 66
    return-void
.end method

.method public onAnimationRepeat(Landroid/animation/Animator;)V
    .locals 0

    .prologue
    .line 75
    return-void
.end method

.method public onAnimationStart(Landroid/animation/Animator;)V
    .locals 1

    .prologue
    .line 58
    iget-object v0, p0, Lcom/miui/internal/widget/i;->pI:Lcom/miui/internal/widget/DropDownPopupWindow;

    invoke-static {v0}, Lcom/miui/internal/widget/DropDownPopupWindow;->f(Lcom/miui/internal/widget/DropDownPopupWindow;)Z

    move-result v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/internal/widget/i;->pI:Lcom/miui/internal/widget/DropDownPopupWindow;

    invoke-static {v0}, Lcom/miui/internal/widget/DropDownPopupWindow;->h(Lcom/miui/internal/widget/DropDownPopupWindow;)Lcom/miui/internal/widget/DropDownPopupWindow$Controller;

    move-result-object v0

    if-eqz v0, :cond_0

    .line 59
    iget-object v0, p0, Lcom/miui/internal/widget/i;->pI:Lcom/miui/internal/widget/DropDownPopupWindow;

    invoke-static {v0}, Lcom/miui/internal/widget/DropDownPopupWindow;->h(Lcom/miui/internal/widget/DropDownPopupWindow;)Lcom/miui/internal/widget/DropDownPopupWindow$Controller;

    move-result-object v0

    invoke-interface {v0}, Lcom/miui/internal/widget/DropDownPopupWindow$Controller;->onDismiss()V

    .line 61
    :cond_0
    return-void
.end method
