.class public Lcom/miui/internal/widget/ScrollingTabContainerView$TabView;
.super Landroid/widget/LinearLayout;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/internal/widget/ScrollingTabContainerView;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x9
    name = "TabView"
.end annotation


# instance fields
.field private l:Landroid/app/ActionBar$Tab;

.field private m:Landroid/widget/TextView;

.field private n:Landroid/widget/ImageView;

.field private o:Landroid/view/View;

.field private p:Lcom/miui/internal/widget/ScrollingTabContainerView;


# direct methods
.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 0

    .prologue
    .line 485
    invoke-direct {p0, p1, p2}, Landroid/widget/LinearLayout;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    .line 486
    return-void
.end method


# virtual methods
.method a(Lcom/miui/internal/widget/ScrollingTabContainerView;Landroid/app/ActionBar$Tab;Z)V
    .locals 1

    .prologue
    .line 489
    iput-object p1, p0, Lcom/miui/internal/widget/ScrollingTabContainerView$TabView;->p:Lcom/miui/internal/widget/ScrollingTabContainerView;

    .line 490
    iput-object p2, p0, Lcom/miui/internal/widget/ScrollingTabContainerView$TabView;->l:Landroid/app/ActionBar$Tab;

    .line 492
    if-eqz p3, :cond_0

    .line 493
    const/16 v0, 0x13

    invoke-virtual {p0, v0}, Lcom/miui/internal/widget/ScrollingTabContainerView$TabView;->setGravity(I)V

    .line 496
    :cond_0
    invoke-virtual {p0}, Lcom/miui/internal/widget/ScrollingTabContainerView$TabView;->update()V

    .line 497
    return-void
.end method

.method public bindTab(Landroid/app/ActionBar$Tab;)V
    .locals 0

    .prologue
    .line 500
    iput-object p1, p0, Lcom/miui/internal/widget/ScrollingTabContainerView$TabView;->l:Landroid/app/ActionBar$Tab;

    .line 501
    invoke-virtual {p0}, Lcom/miui/internal/widget/ScrollingTabContainerView$TabView;->update()V

    .line 502
    return-void
.end method

.method public getTab()Landroid/app/ActionBar$Tab;
    .locals 1

    .prologue
    .line 588
    iget-object v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView$TabView;->l:Landroid/app/ActionBar$Tab;

    return-object v0
.end method

.method public onMeasure(II)V
    .locals 2

    .prologue
    .line 506
    invoke-super {p0, p1, p2}, Landroid/widget/LinearLayout;->onMeasure(II)V

    .line 508
    iget-object v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView$TabView;->p:Lcom/miui/internal/widget/ScrollingTabContainerView;

    if-eqz v0, :cond_1

    iget-object v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView$TabView;->p:Lcom/miui/internal/widget/ScrollingTabContainerView;

    iget v0, v0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aF:I

    .line 511
    :goto_0
    if-lez v0, :cond_0

    invoke-virtual {p0}, Lcom/miui/internal/widget/ScrollingTabContainerView$TabView;->getMeasuredWidth()I

    move-result v1

    if-le v1, v0, :cond_0

    .line 512
    const/high16 v1, 0x40000000

    invoke-static {v0, v1}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result v0

    invoke-super {p0, v0, p2}, Landroid/widget/LinearLayout;->onMeasure(II)V

    .line 515
    :cond_0
    return-void

    .line 508
    :cond_1
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public update()V
    .locals 10

    .prologue
    const/16 v9, 0x10

    const/4 v8, 0x0

    const/16 v7, 0x8

    const/4 v6, -0x2

    const/4 v5, 0x0

    .line 518
    iget-object v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView$TabView;->l:Landroid/app/ActionBar$Tab;

    .line 519
    invoke-virtual {v0}, Landroid/app/ActionBar$Tab;->getCustomView()Landroid/view/View;

    move-result-object v1

    .line 520
    if-eqz v1, :cond_4

    .line 521
    invoke-virtual {v1}, Landroid/view/View;->getParent()Landroid/view/ViewParent;

    move-result-object v0

    .line 522
    if-eq v0, p0, :cond_1

    .line 523
    if-eqz v0, :cond_0

    .line 524
    check-cast v0, Landroid/view/ViewGroup;

    invoke-virtual {v0, v1}, Landroid/view/ViewGroup;->removeView(Landroid/view/View;)V

    .line 526
    :cond_0
    invoke-virtual {p0, v1}, Lcom/miui/internal/widget/ScrollingTabContainerView$TabView;->addView(Landroid/view/View;)V

    .line 528
    :cond_1
    iput-object v1, p0, Lcom/miui/internal/widget/ScrollingTabContainerView$TabView;->o:Landroid/view/View;

    .line 529
    iget-object v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView$TabView;->m:Landroid/widget/TextView;

    if-eqz v0, :cond_2

    .line 530
    iget-object v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView$TabView;->m:Landroid/widget/TextView;

    invoke-virtual {v0, v7}, Landroid/widget/TextView;->setVisibility(I)V

    .line 532
    :cond_2
    iget-object v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView$TabView;->n:Landroid/widget/ImageView;

    if-eqz v0, :cond_3

    .line 533
    iget-object v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView$TabView;->n:Landroid/widget/ImageView;

    invoke-virtual {v0, v7}, Landroid/widget/ImageView;->setVisibility(I)V

    .line 534
    iget-object v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView$TabView;->n:Landroid/widget/ImageView;

    invoke-virtual {v0, v5}, Landroid/widget/ImageView;->setImageDrawable(Landroid/graphics/drawable/Drawable;)V

    .line 585
    :cond_3
    :goto_0
    return-void

    .line 537
    :cond_4
    iget-object v1, p0, Lcom/miui/internal/widget/ScrollingTabContainerView$TabView;->o:Landroid/view/View;

    if-eqz v1, :cond_5

    .line 538
    iget-object v1, p0, Lcom/miui/internal/widget/ScrollingTabContainerView$TabView;->o:Landroid/view/View;

    invoke-virtual {p0, v1}, Lcom/miui/internal/widget/ScrollingTabContainerView$TabView;->removeView(Landroid/view/View;)V

    .line 539
    iput-object v5, p0, Lcom/miui/internal/widget/ScrollingTabContainerView$TabView;->o:Landroid/view/View;

    .line 542
    :cond_5
    invoke-virtual {v0}, Landroid/app/ActionBar$Tab;->getIcon()Landroid/graphics/drawable/Drawable;

    move-result-object v1

    .line 543
    invoke-virtual {v0}, Landroid/app/ActionBar$Tab;->getText()Ljava/lang/CharSequence;

    move-result-object v2

    .line 545
    if-eqz v1, :cond_a

    .line 546
    iget-object v3, p0, Lcom/miui/internal/widget/ScrollingTabContainerView$TabView;->n:Landroid/widget/ImageView;

    if-nez v3, :cond_6

    .line 547
    new-instance v3, Landroid/widget/ImageView;

    invoke-virtual {p0}, Lcom/miui/internal/widget/ScrollingTabContainerView$TabView;->getContext()Landroid/content/Context;

    move-result-object v4

    invoke-direct {v3, v4}, Landroid/widget/ImageView;-><init>(Landroid/content/Context;)V

    .line 548
    new-instance v4, Landroid/widget/LinearLayout$LayoutParams;

    invoke-direct {v4, v6, v6}, Landroid/widget/LinearLayout$LayoutParams;-><init>(II)V

    .line 550
    iput v9, v4, Landroid/widget/LinearLayout$LayoutParams;->gravity:I

    .line 551
    invoke-virtual {v3, v4}, Landroid/widget/ImageView;->setLayoutParams(Landroid/view/ViewGroup$LayoutParams;)V

    .line 552
    invoke-virtual {p0, v3, v8}, Lcom/miui/internal/widget/ScrollingTabContainerView$TabView;->addView(Landroid/view/View;I)V

    .line 553
    iput-object v3, p0, Lcom/miui/internal/widget/ScrollingTabContainerView$TabView;->n:Landroid/widget/ImageView;

    .line 555
    :cond_6
    iget-object v3, p0, Lcom/miui/internal/widget/ScrollingTabContainerView$TabView;->n:Landroid/widget/ImageView;

    invoke-virtual {v3, v1}, Landroid/widget/ImageView;->setImageDrawable(Landroid/graphics/drawable/Drawable;)V

    .line 556
    iget-object v1, p0, Lcom/miui/internal/widget/ScrollingTabContainerView$TabView;->n:Landroid/widget/ImageView;

    invoke-virtual {v1, v8}, Landroid/widget/ImageView;->setVisibility(I)V

    .line 562
    :cond_7
    :goto_1
    if-eqz v2, :cond_b

    .line 563
    iget-object v1, p0, Lcom/miui/internal/widget/ScrollingTabContainerView$TabView;->m:Landroid/widget/TextView;

    if-nez v1, :cond_8

    .line 564
    new-instance v1, Landroid/widget/TextView;

    invoke-virtual {p0}, Lcom/miui/internal/widget/ScrollingTabContainerView$TabView;->getContext()Landroid/content/Context;

    move-result-object v3

    const v4, 0x10102f5

    invoke-direct {v1, v3, v5, v4}, Landroid/widget/TextView;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    .line 566
    sget-object v3, Landroid/text/TextUtils$TruncateAt;->END:Landroid/text/TextUtils$TruncateAt;

    invoke-virtual {v1, v3}, Landroid/widget/TextView;->setEllipsize(Landroid/text/TextUtils$TruncateAt;)V

    .line 567
    new-instance v3, Landroid/widget/LinearLayout$LayoutParams;

    invoke-direct {v3, v6, v6}, Landroid/widget/LinearLayout$LayoutParams;-><init>(II)V

    .line 569
    iput v9, v3, Landroid/widget/LinearLayout$LayoutParams;->gravity:I

    .line 570
    invoke-virtual {v1, v3}, Landroid/widget/TextView;->setLayoutParams(Landroid/view/ViewGroup$LayoutParams;)V

    .line 571
    invoke-virtual {p0, v1}, Lcom/miui/internal/widget/ScrollingTabContainerView$TabView;->addView(Landroid/view/View;)V

    .line 572
    iput-object v1, p0, Lcom/miui/internal/widget/ScrollingTabContainerView$TabView;->m:Landroid/widget/TextView;

    .line 574
    :cond_8
    iget-object v1, p0, Lcom/miui/internal/widget/ScrollingTabContainerView$TabView;->m:Landroid/widget/TextView;

    invoke-virtual {v1, v2}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 575
    iget-object v1, p0, Lcom/miui/internal/widget/ScrollingTabContainerView$TabView;->m:Landroid/widget/TextView;

    invoke-virtual {v1, v8}, Landroid/widget/TextView;->setVisibility(I)V

    .line 581
    :cond_9
    :goto_2
    iget-object v1, p0, Lcom/miui/internal/widget/ScrollingTabContainerView$TabView;->n:Landroid/widget/ImageView;

    if-eqz v1, :cond_3

    .line 582
    iget-object v1, p0, Lcom/miui/internal/widget/ScrollingTabContainerView$TabView;->n:Landroid/widget/ImageView;

    invoke-virtual {v0}, Landroid/app/ActionBar$Tab;->getContentDescription()Ljava/lang/CharSequence;

    move-result-object v0

    invoke-virtual {v1, v0}, Landroid/widget/ImageView;->setContentDescription(Ljava/lang/CharSequence;)V

    goto :goto_0

    .line 557
    :cond_a
    iget-object v1, p0, Lcom/miui/internal/widget/ScrollingTabContainerView$TabView;->n:Landroid/widget/ImageView;

    if-eqz v1, :cond_7

    .line 558
    iget-object v1, p0, Lcom/miui/internal/widget/ScrollingTabContainerView$TabView;->n:Landroid/widget/ImageView;

    invoke-virtual {v1, v7}, Landroid/widget/ImageView;->setVisibility(I)V

    .line 559
    iget-object v1, p0, Lcom/miui/internal/widget/ScrollingTabContainerView$TabView;->n:Landroid/widget/ImageView;

    invoke-virtual {v1, v5}, Landroid/widget/ImageView;->setImageDrawable(Landroid/graphics/drawable/Drawable;)V

    goto :goto_1

    .line 576
    :cond_b
    iget-object v1, p0, Lcom/miui/internal/widget/ScrollingTabContainerView$TabView;->m:Landroid/widget/TextView;

    if-eqz v1, :cond_9

    .line 577
    iget-object v1, p0, Lcom/miui/internal/widget/ScrollingTabContainerView$TabView;->m:Landroid/widget/TextView;

    invoke-virtual {v1, v7}, Landroid/widget/TextView;->setVisibility(I)V

    .line 578
    iget-object v1, p0, Lcom/miui/internal/widget/ScrollingTabContainerView$TabView;->m:Landroid/widget/TextView;

    invoke-virtual {v1, v5}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    goto :goto_2
.end method
