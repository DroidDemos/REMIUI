.class Lcom/miui/internal/app/o;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lmiui/app/ActionBar$FragmentViewPagerChangeListener;


# instance fields
.field Bt:Landroid/graphics/Rect;

.field Bu:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList",
            "<",
            "Landroid/view/View;",
            ">;"
        }
    .end annotation
.end field

.field Bv:I

.field Bw:Z

.field Bx:I

.field By:I

.field Bz:Landroid/view/ViewGroup;

.field ip:Lmiui/view/ViewPager;

.field iq:Lcom/miui/internal/app/n;


# direct methods
.method public constructor <init>(Lmiui/view/ViewPager;Lcom/miui/internal/app/n;)V
    .locals 2

    .prologue
    const/4 v1, -0x1

    .line 477
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 456
    new-instance v0, Landroid/graphics/Rect;

    invoke-direct {v0}, Landroid/graphics/Rect;-><init>()V

    iput-object v0, p0, Lcom/miui/internal/app/o;->Bt:Landroid/graphics/Rect;

    .line 457
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/miui/internal/app/o;->Bu:Ljava/util/ArrayList;

    .line 460
    iput v1, p0, Lcom/miui/internal/app/o;->Bv:I

    .line 461
    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/miui/internal/app/o;->Bw:Z

    .line 465
    iput v1, p0, Lcom/miui/internal/app/o;->Bx:I

    .line 468
    iput v1, p0, Lcom/miui/internal/app/o;->By:I

    .line 471
    const/4 v0, 0x0

    iput-object v0, p0, Lcom/miui/internal/app/o;->Bz:Landroid/view/ViewGroup;

    .line 478
    iput-object p1, p0, Lcom/miui/internal/app/o;->ip:Lmiui/view/ViewPager;

    .line 479
    iput-object p2, p0, Lcom/miui/internal/app/o;->iq:Lcom/miui/internal/app/n;

    .line 480
    return-void
.end method


# virtual methods
.method a(IIIF)I
    .locals 4

    .prologue
    .line 607
    .line 609
    if-ge p1, p3, :cond_0

    mul-int v0, p1, p2

    div-int/2addr v0, p3

    .line 610
    :goto_0
    mul-float v1, p4, p4

    .line 611
    int-to-float v0, v0

    const v2, 0x3dcccccd

    const v3, 0x3f666666

    div-float/2addr v1, v3

    sub-float v1, v2, v1

    int-to-float v2, p2

    mul-float/2addr v1, v2

    add-float/2addr v0, v1

    .line 612
    const/4 v1, 0x0

    cmpl-float v1, v0, v1

    if-lez v1, :cond_1

    float-to-int v0, v0

    :goto_1
    return v0

    :cond_0
    move v0, p2

    .line 609
    goto :goto_0

    .line 612
    :cond_1
    const/4 v0, 0x0

    goto :goto_1
.end method

.method a(Landroid/view/ViewGroup;IIFZ)V
    .locals 6

    .prologue
    const/4 v1, 0x0

    .line 589
    iget-object v0, p0, Lcom/miui/internal/app/o;->Bu:Ljava/util/ArrayList;

    invoke-virtual {p0, p1, v0}, Lcom/miui/internal/app/o;->a(Landroid/view/ViewGroup;Ljava/util/ArrayList;)V

    .line 590
    iget-object v0, p0, Lcom/miui/internal/app/o;->Bu:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_2

    .line 591
    iget-object v0, p0, Lcom/miui/internal/app/o;->Bu:Ljava/util/ArrayList;

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->getTop()I

    move-result v3

    .line 592
    const v0, 0x7fffffff

    .line 594
    iget-object v2, p0, Lcom/miui/internal/app/o;->Bu:Ljava/util/ArrayList;

    invoke-virtual {v2}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v4

    move v2, v0

    :goto_0
    invoke-interface {v4}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_2

    invoke-interface {v4}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/view/View;

    .line 595
    invoke-virtual {v0}, Landroid/view/View;->getTop()I

    move-result v5

    if-eq v2, v5, :cond_0

    .line 596
    invoke-virtual {v0}, Landroid/view/View;->getTop()I

    move-result v2

    .line 597
    sub-int v1, v2, v3

    invoke-virtual {p0, v1, p2, p3, p4}, Lcom/miui/internal/app/o;->a(IIIF)I

    move-result v1

    .line 598
    if-eqz p5, :cond_1

    .line 601
    :cond_0
    :goto_1
    int-to-float v5, v1

    invoke-virtual {v0, v5}, Landroid/view/View;->setTranslationX(F)V

    goto :goto_0

    .line 598
    :cond_1
    neg-int v1, v1

    goto :goto_1

    .line 604
    :cond_2
    return-void
.end method

.method a(Landroid/view/ViewGroup;Ljava/util/ArrayList;)V
    .locals 5
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Landroid/view/ViewGroup;",
            "Ljava/util/ArrayList",
            "<",
            "Landroid/view/View;",
            ">;)V"
        }
    .end annotation

    .prologue
    .line 552
    invoke-virtual {p0, p2, p1}, Lcom/miui/internal/app/o;->a(Ljava/util/ArrayList;Landroid/view/ViewGroup;)V

    .line 553
    invoke-virtual {p2}, Ljava/util/ArrayList;->clear()V

    .line 555
    iget-object v0, p0, Lcom/miui/internal/app/o;->Bt:Landroid/graphics/Rect;

    invoke-static {p1, v0}, Lmiui/util/ViewUtils;->getContentRect(Landroid/view/View;Landroid/graphics/Rect;)V

    .line 556
    iget-object v0, p0, Lcom/miui/internal/app/o;->Bt:Landroid/graphics/Rect;

    invoke-virtual {v0}, Landroid/graphics/Rect;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_1

    .line 567
    :cond_0
    return-void

    .line 560
    :cond_1
    invoke-virtual {p1}, Landroid/view/ViewGroup;->getChildCount()I

    move-result v1

    .line 561
    const/4 v0, 0x0

    :goto_0
    if-ge v0, v1, :cond_0

    .line 562
    invoke-virtual {p1, v0}, Landroid/view/ViewGroup;->getChildAt(I)Landroid/view/View;

    move-result-object v2

    .line 563
    invoke-virtual {v2}, Landroid/view/View;->getVisibility()I

    move-result v3

    const/16 v4, 0x8

    if-ne v3, v4, :cond_2

    invoke-virtual {v2}, Landroid/view/View;->getHeight()I

    move-result v3

    if-lez v3, :cond_3

    .line 564
    :cond_2
    invoke-virtual {p2, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 561
    :cond_3
    add-int/lit8 v0, v0, 0x1

    goto :goto_0
.end method

.method a(Ljava/util/ArrayList;Landroid/view/ViewGroup;)V
    .locals 5
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/ArrayList",
            "<",
            "Landroid/view/View;",
            ">;",
            "Landroid/view/ViewGroup;",
            ")V"
        }
    .end annotation

    .prologue
    const/4 v4, 0x0

    .line 570
    invoke-virtual {p1}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :cond_0
    :goto_0
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_1

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/view/View;

    .line 571
    invoke-virtual {p2, v0}, Landroid/view/ViewGroup;->indexOfChild(Landroid/view/View;)I

    move-result v2

    const/4 v3, -0x1

    if-ne v2, v3, :cond_0

    .line 572
    invoke-virtual {v0}, Landroid/view/View;->getTranslationX()F

    move-result v2

    cmpl-float v2, v2, v4

    if-eqz v2, :cond_0

    .line 573
    invoke-virtual {v0, v4}, Landroid/view/View;->setTranslationX(F)V

    goto :goto_0

    .line 577
    :cond_1
    return-void
.end method

.method d(Landroid/view/ViewGroup;)V
    .locals 3

    .prologue
    .line 580
    iget-object v0, p0, Lcom/miui/internal/app/o;->Bu:Ljava/util/ArrayList;

    invoke-virtual {p0, p1, v0}, Lcom/miui/internal/app/o;->a(Landroid/view/ViewGroup;Ljava/util/ArrayList;)V

    .line 581
    iget-object v0, p0, Lcom/miui/internal/app/o;->Bu:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_0

    .line 582
    iget-object v0, p0, Lcom/miui/internal/app/o;->Bu:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :goto_0
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_0

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/view/View;

    .line 583
    const/4 v2, 0x0

    invoke-virtual {v0, v2}, Landroid/view/View;->setTranslationX(F)V

    goto :goto_0

    .line 586
    :cond_0
    return-void
.end method

.method public onPageScrollStateChanged(I)V
    .locals 1

    .prologue
    .line 544
    if-nez p1, :cond_0

    .line 545
    iget-object v0, p0, Lcom/miui/internal/app/o;->ip:Lmiui/view/ViewPager;

    invoke-virtual {v0}, Lmiui/view/ViewPager;->getCurrentItem()I

    move-result v0

    iput v0, p0, Lcom/miui/internal/app/o;->Bv:I

    .line 546
    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/miui/internal/app/o;->Bw:Z

    .line 548
    :cond_0
    return-void
.end method

.method public onPageScrolled(IFZZ)V
    .locals 7

    .prologue
    const/4 v2, 0x0

    const/4 v5, 0x1

    const/4 v6, 0x0

    .line 485
    cmpl-float v0, p2, v2

    if-nez v0, :cond_0

    .line 486
    iput p1, p0, Lcom/miui/internal/app/o;->Bv:I

    .line 487
    iput-boolean v5, p0, Lcom/miui/internal/app/o;->Bw:Z

    .line 489
    iget-object v0, p0, Lcom/miui/internal/app/o;->Bz:Landroid/view/ViewGroup;

    if-eqz v0, :cond_0

    .line 490
    iget-object v0, p0, Lcom/miui/internal/app/o;->Bz:Landroid/view/ViewGroup;

    invoke-virtual {p0, v0}, Lcom/miui/internal/app/o;->d(Landroid/view/ViewGroup;)V

    .line 494
    :cond_0
    iget v0, p0, Lcom/miui/internal/app/o;->Bx:I

    if-eq v0, p1, :cond_2

    .line 495
    iget v0, p0, Lcom/miui/internal/app/o;->Bv:I

    if-ge v0, p1, :cond_5

    .line 496
    iput p1, p0, Lcom/miui/internal/app/o;->Bv:I

    .line 500
    :cond_1
    :goto_0
    iput p1, p0, Lcom/miui/internal/app/o;->Bx:I

    .line 501
    iput-boolean v5, p0, Lcom/miui/internal/app/o;->Bw:Z

    .line 503
    iget-object v0, p0, Lcom/miui/internal/app/o;->Bz:Landroid/view/ViewGroup;

    if-eqz v0, :cond_2

    .line 504
    iget-object v0, p0, Lcom/miui/internal/app/o;->Bz:Landroid/view/ViewGroup;

    invoke-virtual {p0, v0}, Lcom/miui/internal/app/o;->d(Landroid/view/ViewGroup;)V

    .line 508
    :cond_2
    cmpl-float v0, p2, v2

    if-lez v0, :cond_4

    .line 510
    iget-boolean v0, p0, Lcom/miui/internal/app/o;->Bw:Z

    if-eqz v0, :cond_3

    .line 511
    iput-boolean v6, p0, Lcom/miui/internal/app/o;->Bw:Z

    .line 512
    iget v0, p0, Lcom/miui/internal/app/o;->Bv:I

    if-ne v0, p1, :cond_6

    .line 513
    add-int/lit8 v0, p1, 0x1

    iput v0, p0, Lcom/miui/internal/app/o;->By:I

    .line 517
    :goto_1
    iget-object v0, p0, Lcom/miui/internal/app/o;->iq:Lcom/miui/internal/app/n;

    iget v1, p0, Lcom/miui/internal/app/o;->By:I

    invoke-virtual {v0, v1, v6}, Lcom/miui/internal/app/n;->d(IZ)Landroid/app/Fragment;

    move-result-object v0

    .line 518
    const/4 v1, 0x0

    iput-object v1, p0, Lcom/miui/internal/app/o;->Bz:Landroid/view/ViewGroup;

    .line 519
    if-eqz v0, :cond_3

    invoke-virtual {v0}, Landroid/app/Fragment;->getView()Landroid/view/View;

    move-result-object v1

    if-eqz v1, :cond_3

    .line 520
    invoke-virtual {v0}, Landroid/app/Fragment;->getView()Landroid/view/View;

    move-result-object v0

    const v1, 0x102000a

    invoke-virtual {v0, v1}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    .line 521
    instance-of v1, v0, Landroid/view/ViewGroup;

    if-eqz v1, :cond_3

    .line 522
    check-cast v0, Landroid/view/ViewGroup;

    iput-object v0, p0, Lcom/miui/internal/app/o;->Bz:Landroid/view/ViewGroup;

    .line 527
    :cond_3
    iget v0, p0, Lcom/miui/internal/app/o;->By:I

    if-ne v0, p1, :cond_8

    .line 528
    const/high16 v0, 0x3f800000

    sub-float v4, v0, p2

    .line 531
    :goto_2
    iget-object v0, p0, Lcom/miui/internal/app/o;->Bz:Landroid/view/ViewGroup;

    if-eqz v0, :cond_4

    .line 532
    iget-object v1, p0, Lcom/miui/internal/app/o;->Bz:Landroid/view/ViewGroup;

    iget-object v0, p0, Lcom/miui/internal/app/o;->Bz:Landroid/view/ViewGroup;

    invoke-virtual {v0}, Landroid/view/ViewGroup;->getWidth()I

    move-result v2

    iget-object v0, p0, Lcom/miui/internal/app/o;->Bz:Landroid/view/ViewGroup;

    invoke-virtual {v0}, Landroid/view/ViewGroup;->getHeight()I

    move-result v3

    iget v0, p0, Lcom/miui/internal/app/o;->By:I

    if-eq v0, p1, :cond_7

    :goto_3
    move-object v0, p0

    invoke-virtual/range {v0 .. v5}, Lcom/miui/internal/app/o;->a(Landroid/view/ViewGroup;IIFZ)V

    .line 536
    :cond_4
    return-void

    .line 497
    :cond_5
    iget v0, p0, Lcom/miui/internal/app/o;->Bv:I

    add-int/lit8 v1, p1, 0x1

    if-le v0, v1, :cond_1

    .line 498
    add-int/lit8 v0, p1, 0x1

    iput v0, p0, Lcom/miui/internal/app/o;->Bv:I

    goto :goto_0

    .line 515
    :cond_6
    iput p1, p0, Lcom/miui/internal/app/o;->By:I

    goto :goto_1

    :cond_7
    move v5, v6

    .line 532
    goto :goto_3

    :cond_8
    move v4, p2

    goto :goto_2
.end method

.method public onPageSelected(I)V
    .locals 0

    .prologue
    .line 540
    return-void
.end method
