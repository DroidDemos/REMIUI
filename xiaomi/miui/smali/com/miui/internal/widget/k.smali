.class Lcom/miui/internal/widget/k;
.super Landroid/animation/AnimatorListenerAdapter;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/internal/widget/ArrowPopupView;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic kK:Lcom/miui/internal/widget/ArrowPopupView;

.field private mCancel:Z


# direct methods
.method constructor <init>(Lcom/miui/internal/widget/ArrowPopupView;)V
    .locals 0

    .prologue
    .line 154
    iput-object p1, p0, Lcom/miui/internal/widget/k;->kK:Lcom/miui/internal/widget/ArrowPopupView;

    invoke-direct {p0}, Landroid/animation/AnimatorListenerAdapter;-><init>()V

    return-void
.end method


# virtual methods
.method public onAnimationCancel(Landroid/animation/Animator;)V
    .locals 1

    .prologue
    .line 165
    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/miui/internal/widget/k;->mCancel:Z

    .line 166
    return-void
.end method

.method public onAnimationEnd(Landroid/animation/Animator;)V
    .locals 2

    .prologue
    .line 170
    iget-boolean v0, p0, Lcom/miui/internal/widget/k;->mCancel:Z

    if-eqz v0, :cond_0

    .line 177
    :goto_0
    return-void

    .line 173
    :cond_0
    iget-object v0, p0, Lcom/miui/internal/widget/k;->kK:Lcom/miui/internal/widget/ArrowPopupView;

    const/4 v1, 0x0

    invoke-static {v0, v1}, Lcom/miui/internal/widget/ArrowPopupView;->a(Lcom/miui/internal/widget/ArrowPopupView;Z)Z

    .line 174
    iget-object v0, p0, Lcom/miui/internal/widget/k;->kK:Lcom/miui/internal/widget/ArrowPopupView;

    const/4 v1, 0x0

    invoke-static {v0, v1}, Lcom/miui/internal/widget/ArrowPopupView;->a(Lcom/miui/internal/widget/ArrowPopupView;Landroid/animation/AnimatorSet;)Landroid/animation/AnimatorSet;

    .line 175
    iget-object v0, p0, Lcom/miui/internal/widget/k;->kK:Lcom/miui/internal/widget/ArrowPopupView;

    invoke-static {v0}, Lcom/miui/internal/widget/ArrowPopupView;->c(Lcom/miui/internal/widget/ArrowPopupView;)Lmiui/widget/ArrowPopupWindow;

    move-result-object v0

    invoke-virtual {v0}, Lmiui/widget/ArrowPopupWindow;->dismiss()V

    .line 176
    iget-object v0, p0, Lcom/miui/internal/widget/k;->kK:Lcom/miui/internal/widget/ArrowPopupView;

    const/4 v1, -0x1

    invoke-virtual {v0, v1}, Lcom/miui/internal/widget/ArrowPopupView;->setArrowMode(I)V

    goto :goto_0
.end method

.method public onAnimationStart(Landroid/animation/Animator;)V
    .locals 2

    .prologue
    .line 159
    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/miui/internal/widget/k;->mCancel:Z

    .line 160
    iget-object v0, p0, Lcom/miui/internal/widget/k;->kK:Lcom/miui/internal/widget/ArrowPopupView;

    const/4 v1, 0x1

    invoke-static {v0, v1}, Lcom/miui/internal/widget/ArrowPopupView;->a(Lcom/miui/internal/widget/ArrowPopupView;Z)Z

    .line 161
    return-void
.end method
