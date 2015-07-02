.class Lcom/miui/internal/widget/l;
.super Landroid/animation/AnimatorListenerAdapter;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/internal/widget/GuidePopupView;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic bb:Lcom/miui/internal/widget/GuidePopupView;

.field private mCancel:Z


# direct methods
.method constructor <init>(Lcom/miui/internal/widget/GuidePopupView;)V
    .locals 0

    .prologue
    .line 133
    iput-object p1, p0, Lcom/miui/internal/widget/l;->bb:Lcom/miui/internal/widget/GuidePopupView;

    invoke-direct {p0}, Landroid/animation/AnimatorListenerAdapter;-><init>()V

    return-void
.end method


# virtual methods
.method public onAnimationCancel(Landroid/animation/Animator;)V
    .locals 1

    .prologue
    .line 144
    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/miui/internal/widget/l;->mCancel:Z

    .line 145
    return-void
.end method

.method public onAnimationEnd(Landroid/animation/Animator;)V
    .locals 2

    .prologue
    .line 149
    iget-boolean v0, p0, Lcom/miui/internal/widget/l;->mCancel:Z

    if-eqz v0, :cond_0

    .line 156
    :goto_0
    return-void

    .line 152
    :cond_0
    iget-object v0, p0, Lcom/miui/internal/widget/l;->bb:Lcom/miui/internal/widget/GuidePopupView;

    const/4 v1, 0x0

    invoke-static {v0, v1}, Lcom/miui/internal/widget/GuidePopupView;->a(Lcom/miui/internal/widget/GuidePopupView;Z)Z

    .line 153
    iget-object v0, p0, Lcom/miui/internal/widget/l;->bb:Lcom/miui/internal/widget/GuidePopupView;

    const/4 v1, 0x0

    invoke-static {v0, v1}, Lcom/miui/internal/widget/GuidePopupView;->a(Lcom/miui/internal/widget/GuidePopupView;Landroid/animation/ObjectAnimator;)Landroid/animation/ObjectAnimator;

    .line 154
    iget-object v0, p0, Lcom/miui/internal/widget/l;->bb:Lcom/miui/internal/widget/GuidePopupView;

    invoke-static {v0}, Lcom/miui/internal/widget/GuidePopupView;->a(Lcom/miui/internal/widget/GuidePopupView;)Lmiui/widget/GuidePopupWindow;

    move-result-object v0

    invoke-virtual {v0}, Lmiui/widget/GuidePopupWindow;->dismiss()V

    .line 155
    iget-object v0, p0, Lcom/miui/internal/widget/l;->bb:Lcom/miui/internal/widget/GuidePopupView;

    const/4 v1, -0x1

    invoke-virtual {v0, v1}, Lcom/miui/internal/widget/GuidePopupView;->setArrowMode(I)V

    goto :goto_0
.end method

.method public onAnimationStart(Landroid/animation/Animator;)V
    .locals 2

    .prologue
    .line 138
    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/miui/internal/widget/l;->mCancel:Z

    .line 139
    iget-object v0, p0, Lcom/miui/internal/widget/l;->bb:Lcom/miui/internal/widget/GuidePopupView;

    const/4 v1, 0x1

    invoke-static {v0, v1}, Lcom/miui/internal/widget/GuidePopupView;->a(Lcom/miui/internal/widget/GuidePopupView;Z)Z

    .line 140
    return-void
.end method
