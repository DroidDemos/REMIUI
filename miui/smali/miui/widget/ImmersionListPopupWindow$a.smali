.class Lmiui/widget/ImmersionListPopupWindow$a;
.super Landroid/graphics/drawable/StateListDrawable;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/widget/ImmersionListPopupWindow;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x2
    name = "a"
.end annotation


# instance fields
.field private dt:F

.field private du:Landroid/graphics/drawable/Drawable;

.field final synthetic dv:Lmiui/widget/ImmersionListPopupWindow;


# direct methods
.method public constructor <init>(Lmiui/widget/ImmersionListPopupWindow;Landroid/graphics/drawable/Drawable;)V
    .locals 2

    .prologue
    .line 365
    iput-object p1, p0, Lmiui/widget/ImmersionListPopupWindow$a;->dv:Lmiui/widget/ImmersionListPopupWindow;

    .line 366
    invoke-direct {p0}, Landroid/graphics/drawable/StateListDrawable;-><init>()V

    .line 368
    invoke-static {p1}, Lmiui/widget/ImmersionListPopupWindow;->d(Lmiui/widget/ImmersionListPopupWindow;)Landroid/content/Context;

    move-result-object v0

    sget v1, Lcom/miui/internal/R$attr;->immersionWindowFooterBackground:I

    invoke-static {v0, v1}, Lmiui/util/AttributeResolver;->resolveDrawable(Landroid/content/Context;I)Landroid/graphics/drawable/Drawable;

    move-result-object v0

    iput-object v0, p0, Lmiui/widget/ImmersionListPopupWindow$a;->du:Landroid/graphics/drawable/Drawable;

    .line 370
    const/4 v0, 0x0

    new-array v0, v0, [I

    invoke-virtual {p0, v0, p2}, Lmiui/widget/ImmersionListPopupWindow$a;->addState([ILandroid/graphics/drawable/Drawable;)V

    .line 371
    return-void
.end method


# virtual methods
.method public draw(Landroid/graphics/Canvas;)V
    .locals 5

    .prologue
    .line 380
    invoke-virtual {p1}, Landroid/graphics/Canvas;->save()I

    .line 381
    iget-object v0, p0, Lmiui/widget/ImmersionListPopupWindow$a;->dv:Lmiui/widget/ImmersionListPopupWindow;

    invoke-static {v0}, Lmiui/widget/ImmersionListPopupWindow;->e(Lmiui/widget/ImmersionListPopupWindow;)Landroid/view/View;

    move-result-object v0

    invoke-virtual {v0}, Landroid/view/View;->getBottom()I

    move-result v0

    iget-object v1, p0, Lmiui/widget/ImmersionListPopupWindow$a;->dv:Lmiui/widget/ImmersionListPopupWindow;

    invoke-static {v1}, Lmiui/widget/ImmersionListPopupWindow;->f(Lmiui/widget/ImmersionListPopupWindow;)Landroid/widget/FrameLayout;

    move-result-object v1

    invoke-virtual {v1}, Landroid/widget/FrameLayout;->getTop()I

    move-result v1

    add-int/2addr v0, v1

    int-to-float v0, v0

    iget v1, p0, Lmiui/widget/ImmersionListPopupWindow$a;->dt:F

    mul-float/2addr v0, v1

    float-to-int v0, v0

    .line 382
    iget-object v1, p0, Lmiui/widget/ImmersionListPopupWindow$a;->dv:Lmiui/widget/ImmersionListPopupWindow;

    invoke-static {v1}, Lmiui/widget/ImmersionListPopupWindow;->e(Lmiui/widget/ImmersionListPopupWindow;)Landroid/view/View;

    move-result-object v1

    invoke-virtual {v1}, Landroid/view/View;->getLeft()I

    move-result v1

    const/4 v2, 0x0

    iget-object v3, p0, Lmiui/widget/ImmersionListPopupWindow$a;->dv:Lmiui/widget/ImmersionListPopupWindow;

    invoke-static {v3}, Lmiui/widget/ImmersionListPopupWindow;->e(Lmiui/widget/ImmersionListPopupWindow;)Landroid/view/View;

    move-result-object v3

    invoke-virtual {v3}, Landroid/view/View;->getRight()I

    move-result v3

    invoke-virtual {p1, v1, v2, v3, v0}, Landroid/graphics/Canvas;->clipRect(IIII)Z

    .line 383
    invoke-super {p0, p1}, Landroid/graphics/drawable/StateListDrawable;->draw(Landroid/graphics/Canvas;)V

    .line 384
    invoke-virtual {p1}, Landroid/graphics/Canvas;->restore()V

    .line 386
    invoke-virtual {p0}, Lmiui/widget/ImmersionListPopupWindow$a;->getBounds()Landroid/graphics/Rect;

    move-result-object v1

    .line 387
    iget-object v2, p0, Lmiui/widget/ImmersionListPopupWindow$a;->du:Landroid/graphics/drawable/Drawable;

    iget v3, v1, Landroid/graphics/Rect;->left:I

    iget v4, v1, Landroid/graphics/Rect;->right:I

    iget v1, v1, Landroid/graphics/Rect;->bottom:I

    invoke-virtual {v2, v3, v0, v4, v1}, Landroid/graphics/drawable/Drawable;->setBounds(IIII)V

    .line 388
    iget-object v0, p0, Lmiui/widget/ImmersionListPopupWindow$a;->du:Landroid/graphics/drawable/Drawable;

    iget v1, p0, Lmiui/widget/ImmersionListPopupWindow$a;->dt:F

    const/high16 v2, 0x437f0000

    mul-float/2addr v1, v2

    float-to-int v1, v1

    invoke-virtual {v0, v1}, Landroid/graphics/drawable/Drawable;->setAlpha(I)V

    .line 389
    iget-object v0, p0, Lmiui/widget/ImmersionListPopupWindow$a;->du:Landroid/graphics/drawable/Drawable;

    invoke-virtual {v0, p1}, Landroid/graphics/drawable/Drawable;->draw(Landroid/graphics/Canvas;)V

    .line 390
    return-void
.end method

.method public setRangeRatio(F)V
    .locals 0

    .prologue
    .line 374
    iput p1, p0, Lmiui/widget/ImmersionListPopupWindow$a;->dt:F

    .line 375
    invoke-virtual {p0}, Lmiui/widget/ImmersionListPopupWindow$a;->invalidateSelf()V

    .line 376
    return-void
.end method
