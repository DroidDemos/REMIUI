.class Lcom/miui/internal/widget/SearchActionModeView$b;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lmiui/view/ActionModeAnimationListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/internal/widget/SearchActionModeView;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = "b"
.end annotation


# instance fields
.field final synthetic iC:Lcom/miui/internal/widget/SearchActionModeView;

.field private pd:I


# direct methods
.method constructor <init>(Lcom/miui/internal/widget/SearchActionModeView;)V
    .locals 0

    .prologue
    .line 541
    iput-object p1, p0, Lcom/miui/internal/widget/SearchActionModeView$b;->iC:Lcom/miui/internal/widget/SearchActionModeView;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onStart(Z)V
    .locals 3

    .prologue
    const/4 v0, 0x0

    .line 550
    if-eqz p1, :cond_0

    .line 552
    invoke-static {v0, v0}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result v0

    .line 553
    iget-object v1, p0, Lcom/miui/internal/widget/SearchActionModeView$b;->iC:Lcom/miui/internal/widget/SearchActionModeView;

    invoke-static {v1}, Lcom/miui/internal/widget/SearchActionModeView;->a(Lcom/miui/internal/widget/SearchActionModeView;)Landroid/view/View;

    move-result-object v1

    invoke-virtual {v1, v0, v0}, Landroid/view/View;->measure(II)V

    .line 554
    sget-boolean v0, Lmiui/os/Build;->IS_TABLET:Z

    if-eqz v0, :cond_1

    .line 555
    iget-object v0, p0, Lcom/miui/internal/widget/SearchActionModeView$b;->iC:Lcom/miui/internal/widget/SearchActionModeView;

    invoke-static {v0}, Lcom/miui/internal/widget/SearchActionModeView;->a(Lcom/miui/internal/widget/SearchActionModeView;)Landroid/view/View;

    move-result-object v0

    invoke-virtual {v0}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v0

    check-cast v0, Landroid/view/ViewGroup$MarginLayoutParams;

    iget v0, v0, Landroid/view/ViewGroup$MarginLayoutParams;->leftMargin:I

    iput v0, p0, Lcom/miui/internal/widget/SearchActionModeView$b;->pd:I

    .line 560
    :goto_0
    iget-object v0, p0, Lcom/miui/internal/widget/SearchActionModeView$b;->iC:Lcom/miui/internal/widget/SearchActionModeView;

    iget-object v1, p0, Lcom/miui/internal/widget/SearchActionModeView$b;->iC:Lcom/miui/internal/widget/SearchActionModeView;

    invoke-static {v1}, Lcom/miui/internal/widget/SearchActionModeView;->a(Lcom/miui/internal/widget/SearchActionModeView;)Landroid/view/View;

    move-result-object v1

    invoke-virtual {v1}, Landroid/view/View;->getMeasuredWidth()I

    move-result v1

    iget v2, p0, Lcom/miui/internal/widget/SearchActionModeView$b;->pd:I

    add-int/2addr v1, v2

    invoke-static {v0, v1}, Lcom/miui/internal/widget/SearchActionModeView;->a(Lcom/miui/internal/widget/SearchActionModeView;I)I

    .line 562
    iget-object v0, p0, Lcom/miui/internal/widget/SearchActionModeView$b;->iC:Lcom/miui/internal/widget/SearchActionModeView;

    iget-object v1, p0, Lcom/miui/internal/widget/SearchActionModeView$b;->iC:Lcom/miui/internal/widget/SearchActionModeView;

    invoke-static {v1}, Lcom/miui/internal/widget/SearchActionModeView;->c(Lcom/miui/internal/widget/SearchActionModeView;)I

    move-result v1

    invoke-static {v0, v1}, Lcom/miui/internal/widget/SearchActionModeView;->b(Lcom/miui/internal/widget/SearchActionModeView;I)I

    .line 563
    iget-object v0, p0, Lcom/miui/internal/widget/SearchActionModeView$b;->iC:Lcom/miui/internal/widget/SearchActionModeView;

    invoke-static {v0}, Lcom/miui/internal/widget/SearchActionModeView;->b(Lcom/miui/internal/widget/SearchActionModeView;)Landroid/widget/EditText;

    move-result-object v0

    invoke-virtual {v0}, Landroid/widget/EditText;->getText()Landroid/text/Editable;

    move-result-object v0

    invoke-interface {v0}, Landroid/text/Editable;->clear()V

    .line 564
    iget-object v0, p0, Lcom/miui/internal/widget/SearchActionModeView$b;->iC:Lcom/miui/internal/widget/SearchActionModeView;

    invoke-static {v0}, Lcom/miui/internal/widget/SearchActionModeView;->b(Lcom/miui/internal/widget/SearchActionModeView;)Landroid/widget/EditText;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/internal/widget/SearchActionModeView$b;->iC:Lcom/miui/internal/widget/SearchActionModeView;

    invoke-virtual {v0, v1}, Landroid/widget/EditText;->addTextChangedListener(Landroid/text/TextWatcher;)V

    .line 566
    :cond_0
    return-void

    .line 557
    :cond_1
    iget-object v0, p0, Lcom/miui/internal/widget/SearchActionModeView$b;->iC:Lcom/miui/internal/widget/SearchActionModeView;

    invoke-static {v0}, Lcom/miui/internal/widget/SearchActionModeView;->a(Lcom/miui/internal/widget/SearchActionModeView;)Landroid/view/View;

    move-result-object v0

    invoke-virtual {v0}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v0

    check-cast v0, Landroid/view/ViewGroup$MarginLayoutParams;

    iget v0, v0, Landroid/view/ViewGroup$MarginLayoutParams;->rightMargin:I

    iput v0, p0, Lcom/miui/internal/widget/SearchActionModeView$b;->pd:I

    .line 558
    iget-object v0, p0, Lcom/miui/internal/widget/SearchActionModeView$b;->iC:Lcom/miui/internal/widget/SearchActionModeView;

    invoke-static {v0}, Lcom/miui/internal/widget/SearchActionModeView;->b(Lcom/miui/internal/widget/SearchActionModeView;)Landroid/widget/EditText;

    move-result-object v0

    sget v1, Lcom/miui/internal/R$drawable;->edit_text_search_input_bg_light:I

    invoke-virtual {v0, v1}, Landroid/widget/EditText;->setBackgroundResource(I)V

    goto :goto_0
.end method

.method public onStop(Z)V
    .locals 3

    .prologue
    const/4 v0, 0x0

    .line 602
    iget-object v1, p0, Lcom/miui/internal/widget/SearchActionModeView$b;->iC:Lcom/miui/internal/widget/SearchActionModeView;

    invoke-static {v1}, Lcom/miui/internal/widget/SearchActionModeView;->b(Lcom/miui/internal/widget/SearchActionModeView;)Landroid/widget/EditText;

    move-result-object v1

    invoke-virtual {v1, v0}, Landroid/widget/EditText;->setTranslationX(F)V

    .line 603
    iget-object v1, p0, Lcom/miui/internal/widget/SearchActionModeView$b;->iC:Lcom/miui/internal/widget/SearchActionModeView;

    invoke-static {v1}, Lcom/miui/internal/widget/SearchActionModeView;->b(Lcom/miui/internal/widget/SearchActionModeView;)Landroid/widget/EditText;

    move-result-object v1

    if-eqz p1, :cond_0

    const/high16 v0, 0x3f800000

    :cond_0
    invoke-virtual {v1, v0}, Landroid/widget/EditText;->setAlpha(F)V

    .line 605
    sget-boolean v0, Lmiui/os/Build;->IS_TABLET:Z

    if-nez v0, :cond_1

    .line 606
    iget-object v0, p0, Lcom/miui/internal/widget/SearchActionModeView$b;->iC:Lcom/miui/internal/widget/SearchActionModeView;

    invoke-static {v0}, Lcom/miui/internal/widget/SearchActionModeView;->b(Lcom/miui/internal/widget/SearchActionModeView;)Landroid/widget/EditText;

    move-result-object v0

    invoke-virtual {v0}, Landroid/widget/EditText;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v0

    check-cast v0, Landroid/view/ViewGroup$MarginLayoutParams;

    .line 607
    if-eqz p1, :cond_3

    iget-object v1, p0, Lcom/miui/internal/widget/SearchActionModeView$b;->iC:Lcom/miui/internal/widget/SearchActionModeView;

    invoke-static {v1}, Lcom/miui/internal/widget/SearchActionModeView;->a(Lcom/miui/internal/widget/SearchActionModeView;)Landroid/view/View;

    move-result-object v1

    invoke-virtual {v1}, Landroid/view/View;->getWidth()I

    move-result v1

    iget v2, p0, Lcom/miui/internal/widget/SearchActionModeView$b;->pd:I

    add-int/2addr v1, v2

    :goto_0
    iput v1, v0, Landroid/view/ViewGroup$MarginLayoutParams;->leftMargin:I

    .line 609
    iget-object v1, p0, Lcom/miui/internal/widget/SearchActionModeView$b;->iC:Lcom/miui/internal/widget/SearchActionModeView;

    invoke-static {v1}, Lcom/miui/internal/widget/SearchActionModeView;->b(Lcom/miui/internal/widget/SearchActionModeView;)Landroid/widget/EditText;

    move-result-object v1

    invoke-virtual {v1, v0}, Landroid/widget/EditText;->setLayoutParams(Landroid/view/ViewGroup$LayoutParams;)V

    .line 611
    :cond_1
    if-eqz p1, :cond_4

    .line 612
    sget-boolean v0, Lmiui/os/Build;->IS_TABLET:Z

    if-eqz v0, :cond_2

    .line 613
    iget-object v0, p0, Lcom/miui/internal/widget/SearchActionModeView$b;->iC:Lcom/miui/internal/widget/SearchActionModeView;

    invoke-static {v0}, Lcom/miui/internal/widget/SearchActionModeView;->b(Lcom/miui/internal/widget/SearchActionModeView;)Landroid/widget/EditText;

    move-result-object v0

    invoke-virtual {v0}, Landroid/widget/EditText;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v0

    check-cast v0, Landroid/view/ViewGroup$MarginLayoutParams;

    .line 614
    iget-object v1, p0, Lcom/miui/internal/widget/SearchActionModeView$b;->iC:Lcom/miui/internal/widget/SearchActionModeView;

    invoke-static {v1}, Lcom/miui/internal/widget/SearchActionModeView;->a(Lcom/miui/internal/widget/SearchActionModeView;)Landroid/view/View;

    move-result-object v1

    invoke-virtual {v1}, Landroid/view/View;->getWidth()I

    move-result v1

    iget v2, p0, Lcom/miui/internal/widget/SearchActionModeView$b;->pd:I

    add-int/2addr v1, v2

    iput v1, v0, Landroid/view/ViewGroup$MarginLayoutParams;->rightMargin:I

    .line 615
    iget-object v1, p0, Lcom/miui/internal/widget/SearchActionModeView$b;->iC:Lcom/miui/internal/widget/SearchActionModeView;

    invoke-static {v1}, Lcom/miui/internal/widget/SearchActionModeView;->b(Lcom/miui/internal/widget/SearchActionModeView;)Landroid/widget/EditText;

    move-result-object v1

    invoke-virtual {v1, v0}, Landroid/widget/EditText;->setLayoutParams(Landroid/view/ViewGroup$LayoutParams;)V

    .line 621
    :cond_2
    :goto_1
    return-void

    .line 607
    :cond_3
    const/4 v1, 0x0

    goto :goto_0

    .line 618
    :cond_4
    iget-object v0, p0, Lcom/miui/internal/widget/SearchActionModeView$b;->iC:Lcom/miui/internal/widget/SearchActionModeView;

    invoke-static {v0}, Lcom/miui/internal/widget/SearchActionModeView;->b(Lcom/miui/internal/widget/SearchActionModeView;)Landroid/widget/EditText;

    move-result-object v0

    sget v1, Lcom/miui/internal/R$drawable;->edit_text_search_normal_light:I

    invoke-virtual {v0, v1}, Landroid/widget/EditText;->setBackgroundResource(I)V

    .line 619
    iget-object v0, p0, Lcom/miui/internal/widget/SearchActionModeView$b;->iC:Lcom/miui/internal/widget/SearchActionModeView;

    invoke-static {v0}, Lcom/miui/internal/widget/SearchActionModeView;->b(Lcom/miui/internal/widget/SearchActionModeView;)Landroid/widget/EditText;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/internal/widget/SearchActionModeView$b;->iC:Lcom/miui/internal/widget/SearchActionModeView;

    invoke-virtual {v0, v1}, Landroid/widget/EditText;->removeTextChangedListener(Landroid/text/TextWatcher;)V

    goto :goto_1
.end method

.method public onUpdate(ZF)V
    .locals 6

    .prologue
    const/high16 v5, 0x3f800000

    .line 570
    if-nez p1, :cond_0

    .line 571
    sub-float p2, v5, p2

    .line 574
    :cond_0
    sget-boolean v0, Lmiui/os/Build;->IS_TABLET:Z

    if-eqz v0, :cond_3

    .line 575
    iget-object v0, p0, Lcom/miui/internal/widget/SearchActionModeView$b;->iC:Lcom/miui/internal/widget/SearchActionModeView;

    invoke-static {v0}, Lcom/miui/internal/widget/SearchActionModeView;->a(Lcom/miui/internal/widget/SearchActionModeView;)Landroid/view/View;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/internal/widget/SearchActionModeView$b;->iC:Lcom/miui/internal/widget/SearchActionModeView;

    invoke-static {v1}, Lcom/miui/internal/widget/SearchActionModeView;->c(Lcom/miui/internal/widget/SearchActionModeView;)I

    move-result v1

    int-to-float v1, v1

    iget-object v2, p0, Lcom/miui/internal/widget/SearchActionModeView$b;->iC:Lcom/miui/internal/widget/SearchActionModeView;

    invoke-static {v2}, Lcom/miui/internal/widget/SearchActionModeView;->d(Lcom/miui/internal/widget/SearchActionModeView;)I

    move-result v2

    int-to-float v2, v2

    mul-float/2addr v2, p2

    sub-float/2addr v1, v2

    invoke-virtual {v0, v1}, Landroid/view/View;->setTranslationX(F)V

    .line 577
    iget-object v0, p0, Lcom/miui/internal/widget/SearchActionModeView$b;->iC:Lcom/miui/internal/widget/SearchActionModeView;

    invoke-static {v0}, Lcom/miui/internal/widget/SearchActionModeView;->b(Lcom/miui/internal/widget/SearchActionModeView;)Landroid/widget/EditText;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/internal/widget/SearchActionModeView$b;->iC:Lcom/miui/internal/widget/SearchActionModeView;

    invoke-virtual {v1}, Lcom/miui/internal/widget/SearchActionModeView;->getWidth()I

    move-result v1

    iget-object v2, p0, Lcom/miui/internal/widget/SearchActionModeView$b;->iC:Lcom/miui/internal/widget/SearchActionModeView;

    invoke-virtual {v2}, Lcom/miui/internal/widget/SearchActionModeView;->getPaddingRight()I

    move-result v2

    sub-int/2addr v1, v2

    int-to-float v1, v1

    iget-object v2, p0, Lcom/miui/internal/widget/SearchActionModeView$b;->iC:Lcom/miui/internal/widget/SearchActionModeView;

    invoke-static {v2}, Lcom/miui/internal/widget/SearchActionModeView;->d(Lcom/miui/internal/widget/SearchActionModeView;)I

    move-result v2

    int-to-float v2, v2

    mul-float/2addr v2, p2

    sub-float/2addr v1, v2

    float-to-int v1, v1

    invoke-virtual {v0, v1}, Landroid/widget/EditText;->setRight(I)V

    .line 588
    :goto_0
    iget-object v0, p0, Lcom/miui/internal/widget/SearchActionModeView$b;->iC:Lcom/miui/internal/widget/SearchActionModeView;

    invoke-static {v0}, Lcom/miui/internal/widget/SearchActionModeView;->e(Lcom/miui/internal/widget/SearchActionModeView;)I

    move-result v0

    if-lez v0, :cond_1

    .line 589
    iget-object v0, p0, Lcom/miui/internal/widget/SearchActionModeView$b;->iC:Lcom/miui/internal/widget/SearchActionModeView;

    iget-object v1, p0, Lcom/miui/internal/widget/SearchActionModeView$b;->iC:Lcom/miui/internal/widget/SearchActionModeView;

    invoke-virtual {v1}, Lcom/miui/internal/widget/SearchActionModeView;->getPaddingLeft()I

    move-result v1

    iget-object v2, p0, Lcom/miui/internal/widget/SearchActionModeView$b;->iC:Lcom/miui/internal/widget/SearchActionModeView;

    invoke-static {v2}, Lcom/miui/internal/widget/SearchActionModeView;->f(Lcom/miui/internal/widget/SearchActionModeView;)I

    move-result v2

    int-to-float v2, v2

    iget-object v3, p0, Lcom/miui/internal/widget/SearchActionModeView$b;->iC:Lcom/miui/internal/widget/SearchActionModeView;

    invoke-static {v3}, Lcom/miui/internal/widget/SearchActionModeView;->e(Lcom/miui/internal/widget/SearchActionModeView;)I

    move-result v3

    int-to-float v3, v3

    mul-float/2addr v3, p2

    add-float/2addr v2, v3

    float-to-int v2, v2

    iget-object v3, p0, Lcom/miui/internal/widget/SearchActionModeView$b;->iC:Lcom/miui/internal/widget/SearchActionModeView;

    invoke-virtual {v3}, Lcom/miui/internal/widget/SearchActionModeView;->getPaddingRight()I

    move-result v3

    iget-object v4, p0, Lcom/miui/internal/widget/SearchActionModeView$b;->iC:Lcom/miui/internal/widget/SearchActionModeView;

    invoke-virtual {v4}, Lcom/miui/internal/widget/SearchActionModeView;->getPaddingBottom()I

    move-result v4

    invoke-virtual {v0, v1, v2, v3, v4}, Lcom/miui/internal/widget/SearchActionModeView;->setPadding(IIII)V

    .line 593
    :cond_1
    iget-object v0, p0, Lcom/miui/internal/widget/SearchActionModeView$b;->iC:Lcom/miui/internal/widget/SearchActionModeView;

    invoke-static {v0}, Lcom/miui/internal/widget/SearchActionModeView;->b(Lcom/miui/internal/widget/SearchActionModeView;)Landroid/widget/EditText;

    move-result-object v0

    invoke-virtual {v0, p2}, Landroid/widget/EditText;->setAlpha(F)V

    .line 594
    iget-object v0, p0, Lcom/miui/internal/widget/SearchActionModeView$b;->iC:Lcom/miui/internal/widget/SearchActionModeView;

    invoke-static {v0}, Lcom/miui/internal/widget/SearchActionModeView;->g(Lcom/miui/internal/widget/SearchActionModeView;)Ljava/lang/ref/WeakReference;

    move-result-object v0

    if-eqz v0, :cond_2

    .line 595
    iget-object v0, p0, Lcom/miui/internal/widget/SearchActionModeView$b;->iC:Lcom/miui/internal/widget/SearchActionModeView;

    invoke-static {v0}, Lcom/miui/internal/widget/SearchActionModeView;->g(Lcom/miui/internal/widget/SearchActionModeView;)Ljava/lang/ref/WeakReference;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/ref/WeakReference;->get()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/view/View;

    sub-float v1, v5, p2

    invoke-virtual {v0, v1}, Landroid/view/View;->setAlpha(F)V

    .line 597
    :cond_2
    iget-object v0, p0, Lcom/miui/internal/widget/SearchActionModeView$b;->iC:Lcom/miui/internal/widget/SearchActionModeView;

    invoke-virtual {v0, p2}, Lcom/miui/internal/widget/SearchActionModeView;->setAlpha(F)V

    .line 598
    return-void

    .line 581
    :cond_3
    iget-object v0, p0, Lcom/miui/internal/widget/SearchActionModeView$b;->iC:Lcom/miui/internal/widget/SearchActionModeView;

    invoke-static {v0}, Lcom/miui/internal/widget/SearchActionModeView;->a(Lcom/miui/internal/widget/SearchActionModeView;)Landroid/view/View;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/internal/widget/SearchActionModeView$b;->iC:Lcom/miui/internal/widget/SearchActionModeView;

    invoke-static {v1}, Lcom/miui/internal/widget/SearchActionModeView;->d(Lcom/miui/internal/widget/SearchActionModeView;)I

    move-result v1

    int-to-float v1, v1

    mul-float/2addr v1, p2

    iget-object v2, p0, Lcom/miui/internal/widget/SearchActionModeView$b;->iC:Lcom/miui/internal/widget/SearchActionModeView;

    invoke-static {v2}, Lcom/miui/internal/widget/SearchActionModeView;->c(Lcom/miui/internal/widget/SearchActionModeView;)I

    move-result v2

    int-to-float v2, v2

    sub-float/2addr v1, v2

    invoke-virtual {v0, v1}, Landroid/view/View;->setTranslationX(F)V

    .line 583
    iget-object v0, p0, Lcom/miui/internal/widget/SearchActionModeView$b;->iC:Lcom/miui/internal/widget/SearchActionModeView;

    invoke-static {v0}, Lcom/miui/internal/widget/SearchActionModeView;->c(Lcom/miui/internal/widget/SearchActionModeView;)I

    move-result v0

    int-to-float v0, v0

    sub-float v1, v5, p2

    iget-object v2, p0, Lcom/miui/internal/widget/SearchActionModeView$b;->iC:Lcom/miui/internal/widget/SearchActionModeView;

    invoke-static {v2}, Lcom/miui/internal/widget/SearchActionModeView;->d(Lcom/miui/internal/widget/SearchActionModeView;)I

    move-result v2

    int-to-float v2, v2

    mul-float/2addr v1, v2

    sub-float/2addr v0, v1

    .line 585
    iget-object v1, p0, Lcom/miui/internal/widget/SearchActionModeView$b;->iC:Lcom/miui/internal/widget/SearchActionModeView;

    invoke-static {v1}, Lcom/miui/internal/widget/SearchActionModeView;->b(Lcom/miui/internal/widget/SearchActionModeView;)Landroid/widget/EditText;

    move-result-object v1

    if-eqz p1, :cond_4

    :goto_1
    invoke-virtual {v1, v0}, Landroid/widget/EditText;->setTranslationX(F)V

    goto/16 :goto_0

    :cond_4
    iget-object v2, p0, Lcom/miui/internal/widget/SearchActionModeView$b;->iC:Lcom/miui/internal/widget/SearchActionModeView;

    invoke-static {v2}, Lcom/miui/internal/widget/SearchActionModeView;->d(Lcom/miui/internal/widget/SearchActionModeView;)I

    move-result v2

    int-to-float v2, v2

    sub-float/2addr v0, v2

    goto :goto_1
.end method
