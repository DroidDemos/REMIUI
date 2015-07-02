.class Lcom/miui/internal/widget/j;
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
    .line 129
    iput-object p1, p0, Lcom/miui/internal/widget/j;->kK:Lcom/miui/internal/widget/ArrowPopupView;

    invoke-direct {p0}, Landroid/animation/AnimatorListenerAdapter;-><init>()V

    return-void
.end method


# virtual methods
.method public onAnimationCancel(Landroid/animation/Animator;)V
    .locals 1

    .prologue
    .line 139
    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/miui/internal/widget/j;->mCancel:Z

    .line 140
    return-void
.end method

.method public onAnimationEnd(Landroid/animation/Animator;)V
    .locals 2

    .prologue
    .line 144
    iget-boolean v0, p0, Lcom/miui/internal/widget/j;->mCancel:Z

    if-eqz v0, :cond_1

    .line 151
    :cond_0
    :goto_0
    return-void

    .line 147
    :cond_1
    iget-object v0, p0, Lcom/miui/internal/widget/j;->kK:Lcom/miui/internal/widget/ArrowPopupView;

    const/4 v1, 0x0

    invoke-static {v0, v1}, Lcom/miui/internal/widget/ArrowPopupView;->a(Lcom/miui/internal/widget/ArrowPopupView;Landroid/animation/AnimatorSet;)Landroid/animation/AnimatorSet;

    .line 148
    iget-object v0, p0, Lcom/miui/internal/widget/j;->kK:Lcom/miui/internal/widget/ArrowPopupView;

    invoke-static {v0}, Lcom/miui/internal/widget/ArrowPopupView;->a(Lcom/miui/internal/widget/ArrowPopupView;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 149
    iget-object v0, p0, Lcom/miui/internal/widget/j;->kK:Lcom/miui/internal/widget/ArrowPopupView;

    invoke-static {v0}, Lcom/miui/internal/widget/ArrowPopupView;->b(Lcom/miui/internal/widget/ArrowPopupView;)V

    goto :goto_0
.end method

.method public onAnimationStart(Landroid/animation/Animator;)V
    .locals 1

    .prologue
    .line 134
    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/miui/internal/widget/j;->mCancel:Z

    .line 135
    return-void
.end method
