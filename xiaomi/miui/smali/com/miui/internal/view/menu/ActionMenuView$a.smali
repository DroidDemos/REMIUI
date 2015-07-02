.class Lcom/miui/internal/view/menu/ActionMenuView$a;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroid/animation/Animator$AnimatorListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/internal/view/menu/ActionMenuView;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = "a"
.end annotation


# instance fields
.field oi:Z

.field oj:Landroid/animation/Animator;

.field ok:Landroid/animation/Animator;

.field final synthetic ol:Lcom/miui/internal/view/menu/ActionMenuView;


# direct methods
.method public constructor <init>(Lcom/miui/internal/view/menu/ActionMenuView;)V
    .locals 0

    .prologue
    .line 260
    iput-object p1, p0, Lcom/miui/internal/view/menu/ActionMenuView$a;->ol:Lcom/miui/internal/view/menu/ActionMenuView;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 261
    return-void
.end method


# virtual methods
.method cancel()V
    .locals 1

    .prologue
    .line 289
    invoke-virtual {p0}, Lcom/miui/internal/view/menu/ActionMenuView$a;->initialize()V

    .line 291
    iget-object v0, p0, Lcom/miui/internal/view/menu/ActionMenuView$a;->oj:Landroid/animation/Animator;

    if-eqz v0, :cond_0

    .line 292
    iget-object v0, p0, Lcom/miui/internal/view/menu/ActionMenuView$a;->oj:Landroid/animation/Animator;

    invoke-virtual {v0}, Landroid/animation/Animator;->cancel()V

    .line 293
    const/4 v0, 0x0

    iput-object v0, p0, Lcom/miui/internal/view/menu/ActionMenuView$a;->oj:Landroid/animation/Animator;

    .line 295
    :cond_0
    return-void
.end method

.method close()V
    .locals 1

    .prologue
    .line 282
    invoke-virtual {p0}, Lcom/miui/internal/view/menu/ActionMenuView$a;->cancel()V

    .line 284
    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/miui/internal/view/menu/ActionMenuView$a;->oi:Z

    .line 285
    iget-object v0, p0, Lcom/miui/internal/view/menu/ActionMenuView$a;->ok:Landroid/animation/Animator;

    invoke-virtual {v0}, Landroid/animation/Animator;->start()V

    .line 286
    return-void
.end method

.method initialize()V
    .locals 4

    .prologue
    .line 264
    iget-object v0, p0, Lcom/miui/internal/view/menu/ActionMenuView$a;->ok:Landroid/animation/Animator;

    if-nez v0, :cond_0

    .line 265
    const-string v0, "TranslationY"

    const/4 v1, 0x1

    new-array v1, v1, [F

    const/4 v2, 0x0

    iget-object v3, p0, Lcom/miui/internal/view/menu/ActionMenuView$a;->ol:Lcom/miui/internal/view/menu/ActionMenuView;

    invoke-virtual {v3}, Lcom/miui/internal/view/menu/ActionMenuView;->getHeight()I

    move-result v3

    int-to-float v3, v3

    aput v3, v1, v2

    invoke-static {p0, v0, v1}, Landroid/animation/ObjectAnimator;->ofFloat(Ljava/lang/Object;Ljava/lang/String;[F)Landroid/animation/ObjectAnimator;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/internal/view/menu/ActionMenuView$a;->ok:Landroid/animation/Animator;

    .line 267
    iget-object v0, p0, Lcom/miui/internal/view/menu/ActionMenuView$a;->ok:Landroid/animation/Animator;

    iget-object v1, p0, Lcom/miui/internal/view/menu/ActionMenuView$a;->ol:Lcom/miui/internal/view/menu/ActionMenuView;

    invoke-virtual {v1}, Lcom/miui/internal/view/menu/ActionMenuView;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    const/high16 v2, 0x10e0000

    invoke-virtual {v1, v2}, Landroid/content/res/Resources;->getInteger(I)I

    move-result v1

    int-to-long v1, v1

    invoke-virtual {v0, v1, v2}, Landroid/animation/Animator;->setDuration(J)Landroid/animation/Animator;

    .line 269
    iget-object v0, p0, Lcom/miui/internal/view/menu/ActionMenuView$a;->ok:Landroid/animation/Animator;

    invoke-virtual {v0, p0}, Landroid/animation/Animator;->addListener(Landroid/animation/Animator$AnimatorListener;)V

    .line 271
    :cond_0
    return-void
.end method

.method public onAnimationCancel(Landroid/animation/Animator;)V
    .locals 0

    .prologue
    .line 309
    return-void
.end method

.method public onAnimationEnd(Landroid/animation/Animator;)V
    .locals 1

    .prologue
    .line 304
    const/4 v0, 0x0

    iput-object v0, p0, Lcom/miui/internal/view/menu/ActionMenuView$a;->oj:Landroid/animation/Animator;

    .line 305
    return-void
.end method

.method public onAnimationRepeat(Landroid/animation/Animator;)V
    .locals 0

    .prologue
    .line 313
    return-void
.end method

.method public onAnimationStart(Landroid/animation/Animator;)V
    .locals 0

    .prologue
    .line 299
    iput-object p1, p0, Lcom/miui/internal/view/menu/ActionMenuView$a;->oj:Landroid/animation/Animator;

    .line 300
    return-void
.end method

.method open()V
    .locals 1

    .prologue
    .line 274
    invoke-virtual {p0}, Lcom/miui/internal/view/menu/ActionMenuView$a;->cancel()V

    .line 276
    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/miui/internal/view/menu/ActionMenuView$a;->oi:Z

    .line 277
    const/4 v0, 0x0

    invoke-virtual {p0, v0}, Lcom/miui/internal/view/menu/ActionMenuView$a;->setTranslationY(F)V

    .line 278
    iget-object v0, p0, Lcom/miui/internal/view/menu/ActionMenuView$a;->ol:Lcom/miui/internal/view/menu/ActionMenuView;

    invoke-virtual {v0}, Lcom/miui/internal/view/menu/ActionMenuView;->startLayoutAnimation()V

    .line 279
    return-void
.end method

.method public setTranslationY(F)V
    .locals 2

    .prologue
    .line 255
    const/4 v0, 0x0

    :goto_0
    iget-object v1, p0, Lcom/miui/internal/view/menu/ActionMenuView$a;->ol:Lcom/miui/internal/view/menu/ActionMenuView;

    invoke-virtual {v1}, Lcom/miui/internal/view/menu/ActionMenuView;->getChildCount()I

    move-result v1

    if-ge v0, v1, :cond_0

    .line 256
    iget-object v1, p0, Lcom/miui/internal/view/menu/ActionMenuView$a;->ol:Lcom/miui/internal/view/menu/ActionMenuView;

    invoke-virtual {v1, v0}, Lcom/miui/internal/view/menu/ActionMenuView;->getChildAt(I)Landroid/view/View;

    move-result-object v1

    invoke-virtual {v1, p1}, Landroid/view/View;->setTranslationY(F)V

    .line 255
    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    .line 258
    :cond_0
    return-void
.end method
