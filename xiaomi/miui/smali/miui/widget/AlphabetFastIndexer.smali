.class public Lmiui/widget/AlphabetFastIndexer;
.super Landroid/widget/ImageView;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lmiui/widget/AlphabetFastIndexer$a;,
        Lmiui/widget/AlphabetFastIndexer$b;
    }
.end annotation


# static fields
.field public static final STARRED_TITLE:Ljava/lang/String; = "!"

.field public static final STATE_DRAGGING:I = 0x1

.field public static final STATE_NONE:I = 0x0

.field private static final vC:Ljava/lang/String; = "\u2605"

.field private static final vQ:I = 0x1

.field private static final vR:I = 0x5dc


# instance fields
.field private mHandler:Landroid/os/Handler;

.field private vD:Landroid/widget/AdapterView;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Landroid/widget/AdapterView",
            "<*>;"
        }
    .end annotation
.end field

.field private vE:Landroid/widget/TextView;

.field private vF:I

.field private vG:I

.field private vH:I

.field private vI:I

.field private vJ:I

.field private vK:I

.field private vL:Landroid/graphics/drawable/Drawable;

.field private vM:Landroid/animation/ValueAnimator$AnimatorUpdateListener;

.field private vN:Lmiui/widget/AlphabetFastIndexer$b;

.field private vO:I

.field private vP:I

.field private vS:Ljava/lang/Runnable;


# direct methods
.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 1

    .prologue
    .line 279
    const/4 v0, 0x0

    invoke-direct {p0, p1, p2, v0}, Lmiui/widget/AlphabetFastIndexer;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    .line 280
    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V
    .locals 4

    .prologue
    const/4 v2, 0x0

    .line 283
    invoke-direct {p0, p1, p2, p3}, Landroid/widget/ImageView;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    .line 105
    new-instance v0, Lmiui/widget/a;

    invoke-direct {v0, p0}, Lmiui/widget/a;-><init>(Lmiui/widget/AlphabetFastIndexer;)V

    iput-object v0, p0, Lmiui/widget/AlphabetFastIndexer;->vM:Landroid/animation/ValueAnimator$AnimatorUpdateListener;

    .line 115
    iput v2, p0, Lmiui/widget/AlphabetFastIndexer;->vO:I

    .line 117
    iput v2, p0, Lmiui/widget/AlphabetFastIndexer;->vP:I

    .line 370
    new-instance v0, Lmiui/widget/b;

    invoke-direct {v0, p0}, Lmiui/widget/b;-><init>(Lmiui/widget/AlphabetFastIndexer;)V

    iput-object v0, p0, Lmiui/widget/AlphabetFastIndexer;->vS:Ljava/lang/Runnable;

    .line 774
    new-instance v0, Lmiui/widget/c;

    invoke-direct {v0, p0}, Lmiui/widget/c;-><init>(Lmiui/widget/AlphabetFastIndexer;)V

    iput-object v0, p0, Lmiui/widget/AlphabetFastIndexer;->mHandler:Landroid/os/Handler;

    .line 285
    invoke-virtual {p1}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    .line 286
    invoke-interface {p2}, Landroid/util/AttributeSet;->getStyleAttribute()I

    move-result v0

    if-nez v0, :cond_2

    .line 287
    :goto_0
    sget-object v0, Lcom/miui/internal/R$styleable;->AlphabetFastIndexer:[I

    invoke-virtual {p1, p2, v0, v2, p3}, Landroid/content/Context;->obtainStyledAttributes(Landroid/util/AttributeSet;[III)Landroid/content/res/TypedArray;

    move-result-object v2

    .line 290
    new-instance v0, Lmiui/widget/AlphabetFastIndexer$b;

    invoke-direct {v0, p1, v2}, Lmiui/widget/AlphabetFastIndexer$b;-><init>(Landroid/content/Context;Landroid/content/res/TypedArray;)V

    iput-object v0, p0, Lmiui/widget/AlphabetFastIndexer;->vN:Lmiui/widget/AlphabetFastIndexer$b;

    .line 292
    const/16 v0, 0x8

    sget v3, Lcom/miui/internal/R$dimen;->alphabet_indexer_overlay_offset:I

    invoke-virtual {v1, v3}, Landroid/content/res/Resources;->getDimensionPixelOffset(I)I

    move-result v3

    invoke-virtual {v2, v0, v3}, Landroid/content/res/TypedArray;->getDimensionPixelOffset(II)I

    move-result v0

    iput v0, p0, Lmiui/widget/AlphabetFastIndexer;->vH:I

    .line 294
    const/16 v0, 0x9

    sget v3, Lcom/miui/internal/R$dimen;->alphabet_indexer_overlay_padding_top:I

    invoke-virtual {v1, v3}, Landroid/content/res/Resources;->getDimensionPixelOffset(I)I

    move-result v3

    invoke-virtual {v2, v0, v3}, Landroid/content/res/TypedArray;->getDimensionPixelOffset(II)I

    move-result v0

    iput v0, p0, Lmiui/widget/AlphabetFastIndexer;->vI:I

    .line 296
    const/16 v0, 0xa

    sget v3, Lcom/miui/internal/R$dimen;->alphabet_indexer_overlay_text_size:I

    invoke-virtual {v1, v3}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v3

    invoke-virtual {v2, v0, v3}, Landroid/content/res/TypedArray;->getDimensionPixelSize(II)I

    move-result v0

    iput v0, p0, Lmiui/widget/AlphabetFastIndexer;->vJ:I

    .line 298
    const/16 v0, 0xb

    sget v3, Lcom/miui/internal/R$color;->alphabet_indexer_overlay_text_color:I

    invoke-virtual {v1, v3}, Landroid/content/res/Resources;->getColor(I)I

    move-result v3

    invoke-virtual {v2, v0, v3}, Landroid/content/res/TypedArray;->getColor(II)I

    move-result v0

    iput v0, p0, Lmiui/widget/AlphabetFastIndexer;->vK:I

    .line 300
    const/4 v0, 0x7

    invoke-virtual {v2, v0}, Landroid/content/res/TypedArray;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v0

    iput-object v0, p0, Lmiui/widget/AlphabetFastIndexer;->vL:Landroid/graphics/drawable/Drawable;

    .line 301
    iget-object v0, p0, Lmiui/widget/AlphabetFastIndexer;->vL:Landroid/graphics/drawable/Drawable;

    if-nez v0, :cond_0

    .line 302
    sget v0, Lcom/miui/internal/R$drawable;->alphabet_indexer_overlay:I

    invoke-virtual {v1, v0}, Landroid/content/res/Resources;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v0

    iput-object v0, p0, Lmiui/widget/AlphabetFastIndexer;->vL:Landroid/graphics/drawable/Drawable;

    .line 306
    :cond_0
    const/4 v0, 0x6

    invoke-virtual {v2, v0}, Landroid/content/res/TypedArray;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v0

    .line 307
    if-nez v0, :cond_1

    .line 308
    sget v0, Lcom/miui/internal/R$drawable;->alphabet_indexer_bg:I

    invoke-virtual {v1, v0}, Landroid/content/res/Resources;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v0

    .line 310
    :cond_1
    invoke-virtual {p0, v0}, Lmiui/widget/AlphabetFastIndexer;->setBackground(Landroid/graphics/drawable/Drawable;)V

    .line 312
    invoke-virtual {v2}, Landroid/content/res/TypedArray;->recycle()V

    .line 313
    const/4 v0, 0x5

    iput v0, p0, Lmiui/widget/AlphabetFastIndexer;->vG:I

    .line 314
    return-void

    .line 286
    :cond_2
    invoke-interface {p2}, Landroid/util/AttributeSet;->getStyleAttribute()I

    move-result p3

    goto :goto_0
.end method

.method private P(I)V
    .locals 3

    .prologue
    .line 789
    iget-object v0, p0, Lmiui/widget/AlphabetFastIndexer;->vN:Lmiui/widget/AlphabetFastIndexer$b;

    if-nez v0, :cond_0

    .line 803
    :goto_0
    return-void

    .line 793
    :cond_0
    if-gez p1, :cond_1

    .line 794
    const/4 p1, 0x0

    .line 797
    :cond_1
    invoke-virtual {p0}, Lmiui/widget/AlphabetFastIndexer;->getPaddingTop()I

    move-result v0

    .line 798
    invoke-virtual {p0}, Lmiui/widget/AlphabetFastIndexer;->getHeight()I

    move-result v1

    sub-int/2addr v1, v0

    invoke-virtual {p0}, Lmiui/widget/AlphabetFastIndexer;->getPaddingBottom()I

    move-result v2

    sub-int/2addr v1, v2

    .line 799
    int-to-float v1, v1

    iget-object v2, p0, Lmiui/widget/AlphabetFastIndexer;->vN:Lmiui/widget/AlphabetFastIndexer$b;

    iget-object v2, v2, Lmiui/widget/AlphabetFastIndexer$b;->AQ:[Ljava/lang/String;

    array-length v2, v2

    int-to-float v2, v2

    div-float/2addr v1, v2

    .line 801
    int-to-float v2, p1

    mul-float/2addr v2, v1

    int-to-float v0, v0

    add-float/2addr v0, v2

    const/high16 v2, 0x40000000

    div-float/2addr v1, v2

    add-float/2addr v0, v1

    const/high16 v1, 0x3f800000

    add-float/2addr v0, v1

    .line 802
    iget-object v1, p0, Lmiui/widget/AlphabetFastIndexer;->vN:Lmiui/widget/AlphabetFastIndexer$b;

    iget-object v2, p0, Lmiui/widget/AlphabetFastIndexer;->vM:Landroid/animation/ValueAnimator$AnimatorUpdateListener;

    invoke-virtual {v1, v0, v2}, Lmiui/widget/AlphabetFastIndexer$b;->a(FLandroid/animation/ValueAnimator$AnimatorUpdateListener;)V

    goto :goto_0
.end method

.method private a(FLandroid/widget/SectionIndexer;)I
    .locals 5

    .prologue
    const/4 v0, -0x1

    .line 616
    invoke-interface {p2}, Landroid/widget/SectionIndexer;->getSections()[Ljava/lang/Object;

    move-result-object v1

    .line 617
    if-nez v1, :cond_1

    .line 645
    :cond_0
    :goto_0
    return v0

    .line 621
    :cond_1
    invoke-virtual {p0}, Lmiui/widget/AlphabetFastIndexer;->getPaddingTop()I

    move-result v2

    .line 622
    invoke-virtual {p0}, Lmiui/widget/AlphabetFastIndexer;->getPaddingBottom()I

    move-result v3

    .line 623
    invoke-virtual {p0}, Lmiui/widget/AlphabetFastIndexer;->getHeight()I

    move-result v4

    sub-int/2addr v4, v2

    sub-int v3, v4, v3

    .line 624
    if-lez v3, :cond_0

    .line 627
    int-to-float v2, v2

    sub-float v2, p1, v2

    int-to-float v3, v3

    div-float/2addr v2, v3

    .line 628
    iget-object v3, p0, Lmiui/widget/AlphabetFastIndexer;->vN:Lmiui/widget/AlphabetFastIndexer$b;

    iget-object v3, v3, Lmiui/widget/AlphabetFastIndexer$b;->AQ:[Ljava/lang/String;

    array-length v3, v3

    int-to-float v3, v3

    mul-float/2addr v2, v3

    float-to-int v2, v2

    .line 629
    if-ltz v2, :cond_0

    .line 631
    iget-object v0, p0, Lmiui/widget/AlphabetFastIndexer;->vN:Lmiui/widget/AlphabetFastIndexer$b;

    iget-object v0, v0, Lmiui/widget/AlphabetFastIndexer$b;->AQ:[Ljava/lang/String;

    array-length v0, v0

    if-lt v2, v0, :cond_2

    .line 632
    array-length v0, v1

    goto :goto_0

    .line 635
    :cond_2
    iget-object v0, p0, Lmiui/widget/AlphabetFastIndexer;->vN:Lmiui/widget/AlphabetFastIndexer$b;

    iget-object v0, v0, Lmiui/widget/AlphabetFastIndexer$b;->AQ:[Ljava/lang/String;

    aget-object v0, v0, v2

    invoke-static {v1, v0}, Ljava/util/Arrays;->binarySearch([Ljava/lang/Object;Ljava/lang/Object;)I

    move-result v0

    .line 636
    if-gez v0, :cond_3

    .line 639
    neg-int v0, v0

    add-int/lit8 v0, v0, -0x2

    .line 642
    :cond_3
    if-gez v0, :cond_0

    .line 643
    const/4 v0, 0x0

    goto :goto_0
.end method

.method static synthetic a(Lmiui/widget/AlphabetFastIndexer;I)I
    .locals 0

    .prologue
    .line 76
    iput p1, p0, Lmiui/widget/AlphabetFastIndexer;->vO:I

    return p1
.end method

.method static synthetic a(Lmiui/widget/AlphabetFastIndexer;)Lmiui/widget/AlphabetFastIndexer$b;
    .locals 1

    .prologue
    .line 76
    iget-object v0, p0, Lmiui/widget/AlphabetFastIndexer;->vN:Lmiui/widget/AlphabetFastIndexer$b;

    return-object v0
.end method

.method private a(Landroid/widget/SectionIndexer;I)V
    .locals 15

    .prologue
    .line 652
    iget-object v1, p0, Lmiui/widget/AlphabetFastIndexer;->vD:Landroid/widget/AdapterView;

    invoke-virtual {v1}, Landroid/widget/AdapterView;->getCount()I

    move-result v8

    .line 653
    invoke-direct {p0}, Lmiui/widget/AlphabetFastIndexer;->ca()I

    move-result v9

    .line 654
    const/high16 v1, 0x3f800000

    int-to-float v2, v8

    div-float/2addr v1, v2

    const/high16 v2, 0x41000000

    div-float v10, v1, v2

    .line 655
    invoke-interface/range {p1 .. p1}, Landroid/widget/SectionIndexer;->getSections()[Ljava/lang/Object;

    move-result-object v11

    .line 657
    if-eqz v11, :cond_6

    array-length v1, v11

    const/4 v2, 0x1

    if-le v1, v2, :cond_6

    .line 658
    array-length v12, v11

    .line 660
    move/from16 v0, p2

    if-lt v0, v12, :cond_e

    .line 661
    add-int/lit8 v1, v12, -0x1

    .line 665
    :goto_0
    move-object/from16 v0, p1

    invoke-interface {v0, v1}, Landroid/widget/SectionIndexer;->getPositionForSection(I)I

    move-result v3

    .line 675
    add-int/lit8 v6, v1, 0x1

    .line 677
    add-int/lit8 v2, v12, -0x1

    if-ge v1, v2, :cond_d

    .line 678
    add-int/lit8 v2, v1, 0x1

    move-object/from16 v0, p1

    invoke-interface {v0, v2}, Landroid/widget/SectionIndexer;->getPositionForSection(I)I

    move-result v2

    move v7, v2

    .line 682
    :goto_1
    if-ne v7, v3, :cond_c

    move v2, v3

    move v4, v1

    .line 684
    :goto_2
    if-lez v4, :cond_b

    .line 685
    add-int/lit8 v2, v4, -0x1

    .line 686
    move-object/from16 v0, p1

    invoke-interface {v0, v2}, Landroid/widget/SectionIndexer;->getPositionForSection(I)I

    move-result v4

    .line 687
    if-eq v4, v3, :cond_0

    move v3, v4

    move v4, v2

    .line 706
    :goto_3
    add-int/lit8 v5, v6, 0x1

    .line 708
    :goto_4
    if-ge v5, v12, :cond_1

    move-object/from16 v0, p1

    invoke-interface {v0, v5}, Landroid/widget/SectionIndexer;->getPositionForSection(I)I

    move-result v13

    if-ne v13, v7, :cond_1

    .line 709
    add-int/lit8 v5, v5, 0x1

    .line 710
    add-int/lit8 v6, v6, 0x1

    goto :goto_4

    .line 691
    :cond_0
    if-nez v2, :cond_a

    .line 696
    const/4 v2, 0x0

    move v3, v4

    move v4, v2

    move v2, v1

    .line 697
    goto :goto_3

    .line 715
    :cond_1
    int-to-float v5, v2

    int-to-float v13, v12

    div-float/2addr v5, v13

    .line 716
    int-to-float v6, v6

    int-to-float v13, v12

    div-float/2addr v6, v13

    .line 717
    move/from16 v0, p2

    int-to-float v13, v0

    int-to-float v12, v12

    div-float v12, v13, v12

    .line 718
    if-ne v2, v1, :cond_3

    sub-float v1, v12, v5

    cmpg-float v1, v1, v10

    if-gez v1, :cond_3

    move v1, v3

    .line 724
    :goto_5
    add-int/lit8 v2, v8, -0x1

    if-le v1, v2, :cond_9

    .line 725
    add-int/lit8 v1, v8, -0x1

    move v2, v1

    .line 727
    :goto_6
    iget-object v1, p0, Lmiui/widget/AlphabetFastIndexer;->vD:Landroid/widget/AdapterView;

    instance-of v1, v1, Landroid/widget/ExpandableListView;

    if-eqz v1, :cond_4

    .line 728
    iget-object v1, p0, Lmiui/widget/AlphabetFastIndexer;->vD:Landroid/widget/AdapterView;

    check-cast v1, Landroid/widget/ExpandableListView;

    .line 729
    add-int/2addr v2, v9

    invoke-static {v2}, Landroid/widget/ExpandableListView;->getPackedPositionForGroup(I)J

    move-result-wide v2

    invoke-virtual {v1, v2, v3}, Landroid/widget/ExpandableListView;->getFlatListPosition(J)I

    move-result v2

    const/4 v3, 0x0

    invoke-virtual {v1, v2, v3}, Landroid/widget/ExpandableListView;->setSelectionFromTop(II)V

    .line 750
    :goto_7
    if-ltz v4, :cond_2

    if-eqz v11, :cond_2

    .line 751
    aget-object v1, v11, v4

    invoke-virtual {v1}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v1

    .line 752
    invoke-static {v1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v2

    if-nez v2, :cond_2

    .line 753
    const/4 v2, 0x0

    const/4 v3, 0x1

    invoke-virtual {v1, v2, v3}, Ljava/lang/String;->subSequence(II)Ljava/lang/CharSequence;

    move-result-object v1

    invoke-direct {p0, v1}, Lmiui/widget/AlphabetFastIndexer;->b(Ljava/lang/CharSequence;)V

    .line 756
    :cond_2
    return-void

    .line 721
    :cond_3
    sub-int v1, v7, v3

    int-to-float v1, v1

    sub-float v2, v12, v5

    mul-float/2addr v1, v2

    sub-float v2, v6, v5

    div-float/2addr v1, v2

    invoke-static {v1}, Ljava/lang/Math;->round(F)I

    move-result v1

    add-int/2addr v1, v3

    goto :goto_5

    .line 731
    :cond_4
    iget-object v1, p0, Lmiui/widget/AlphabetFastIndexer;->vD:Landroid/widget/AdapterView;

    instance-of v1, v1, Landroid/widget/ListView;

    if-eqz v1, :cond_5

    .line 732
    iget-object v1, p0, Lmiui/widget/AlphabetFastIndexer;->vD:Landroid/widget/AdapterView;

    check-cast v1, Landroid/widget/ListView;

    add-int/2addr v2, v9

    const/4 v3, 0x0

    invoke-virtual {v1, v2, v3}, Landroid/widget/ListView;->setSelectionFromTop(II)V

    goto :goto_7

    .line 734
    :cond_5
    iget-object v1, p0, Lmiui/widget/AlphabetFastIndexer;->vD:Landroid/widget/AdapterView;

    add-int/2addr v2, v9

    invoke-virtual {v1, v2}, Landroid/widget/AdapterView;->setSelection(I)V

    goto :goto_7

    .line 737
    :cond_6
    mul-int v1, p2, v8

    int-to-float v1, v1

    invoke-static {v1}, Ljava/lang/Math;->round(F)I

    move-result v2

    .line 738
    iget-object v1, p0, Lmiui/widget/AlphabetFastIndexer;->vD:Landroid/widget/AdapterView;

    instance-of v1, v1, Landroid/widget/ExpandableListView;

    if-eqz v1, :cond_7

    .line 739
    iget-object v1, p0, Lmiui/widget/AlphabetFastIndexer;->vD:Landroid/widget/AdapterView;

    check-cast v1, Landroid/widget/ExpandableListView;

    .line 740
    add-int/2addr v2, v9

    invoke-static {v2}, Landroid/widget/ExpandableListView;->getPackedPositionForGroup(I)J

    move-result-wide v2

    invoke-virtual {v1, v2, v3}, Landroid/widget/ExpandableListView;->getFlatListPosition(J)I

    move-result v2

    const/4 v3, 0x0

    invoke-virtual {v1, v2, v3}, Landroid/widget/ExpandableListView;->setSelectionFromTop(II)V

    .line 747
    :goto_8
    const/4 v4, -0x1

    goto :goto_7

    .line 742
    :cond_7
    iget-object v1, p0, Lmiui/widget/AlphabetFastIndexer;->vD:Landroid/widget/AdapterView;

    instance-of v1, v1, Landroid/widget/ListView;

    if-eqz v1, :cond_8

    .line 743
    iget-object v1, p0, Lmiui/widget/AlphabetFastIndexer;->vD:Landroid/widget/AdapterView;

    check-cast v1, Landroid/widget/ListView;

    add-int/2addr v2, v9

    const/4 v3, 0x0

    invoke-virtual {v1, v2, v3}, Landroid/widget/ListView;->setSelectionFromTop(II)V

    goto :goto_8

    .line 745
    :cond_8
    iget-object v1, p0, Lmiui/widget/AlphabetFastIndexer;->vD:Landroid/widget/AdapterView;

    add-int/2addr v2, v9

    invoke-virtual {v1, v2}, Landroid/widget/AdapterView;->setSelection(I)V

    goto :goto_8

    :cond_9
    move v2, v1

    goto/16 :goto_6

    :cond_a
    move v14, v4

    move v4, v2

    move v2, v14

    goto/16 :goto_2

    :cond_b
    move v3, v2

    move v4, v1

    move v2, v1

    goto/16 :goto_3

    :cond_c
    move v2, v1

    move v4, v1

    goto/16 :goto_3

    :cond_d
    move v7, v8

    goto/16 :goto_1

    :cond_e
    move/from16 v1, p2

    goto/16 :goto_0
.end method

.method private b(Ljava/lang/CharSequence;)V
    .locals 4

    .prologue
    const/4 v2, 0x1

    .line 759
    iget-object v0, p0, Lmiui/widget/AlphabetFastIndexer;->vD:Landroid/widget/AdapterView;

    if-nez v0, :cond_1

    .line 772
    :cond_0
    :goto_0
    return-void

    .line 764
    :cond_1
    const-string v0, "!"

    invoke-static {p1, v0}, Landroid/text/TextUtils;->equals(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_2

    const-string p1, "\u2605"

    .line 765
    :cond_2
    iget-object v0, p0, Lmiui/widget/AlphabetFastIndexer;->vE:Landroid/widget/TextView;

    invoke-virtual {v0, p1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 766
    invoke-virtual {p0}, Lmiui/widget/AlphabetFastIndexer;->getVisibility()I

    move-result v0

    if-nez v0, :cond_0

    .line 767
    iget-object v0, p0, Lmiui/widget/AlphabetFastIndexer;->vE:Landroid/widget/TextView;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setVisibility(I)V

    .line 768
    iget-object v0, p0, Lmiui/widget/AlphabetFastIndexer;->mHandler:Landroid/os/Handler;

    invoke-virtual {v0, v2}, Landroid/os/Handler;->removeMessages(I)V

    .line 769
    iget-object v0, p0, Lmiui/widget/AlphabetFastIndexer;->mHandler:Landroid/os/Handler;

    invoke-virtual {v0, v2}, Landroid/os/Handler;->obtainMessage(I)Landroid/os/Message;

    move-result-object v0

    .line 770
    iget-object v1, p0, Lmiui/widget/AlphabetFastIndexer;->mHandler:Landroid/os/Handler;

    const-wide/16 v2, 0x5dc

    invoke-virtual {v1, v0, v2, v3}, Landroid/os/Handler;->sendMessageDelayed(Landroid/os/Message;J)Z

    goto :goto_0
.end method

.method static synthetic b(Lmiui/widget/AlphabetFastIndexer;)V
    .locals 0

    .prologue
    .line 76
    invoke-direct {p0}, Lmiui/widget/AlphabetFastIndexer;->bY()V

    return-void
.end method

.method private bY()V
    .locals 4

    .prologue
    .line 463
    iget-object v0, p0, Lmiui/widget/AlphabetFastIndexer;->vD:Landroid/widget/AdapterView;

    if-nez v0, :cond_1

    .line 487
    :cond_0
    :goto_0
    return-void

    .line 467
    :cond_1
    const/4 v1, 0x0

    .line 468
    invoke-direct {p0}, Lmiui/widget/AlphabetFastIndexer;->bZ()Landroid/widget/SectionIndexer;

    move-result-object v0

    .line 469
    if-eqz v0, :cond_3

    .line 470
    iget-object v2, p0, Lmiui/widget/AlphabetFastIndexer;->vD:Landroid/widget/AdapterView;

    invoke-virtual {v2}, Landroid/widget/AdapterView;->getFirstVisiblePosition()I

    move-result v2

    invoke-direct {p0}, Lmiui/widget/AlphabetFastIndexer;->ca()I

    move-result v3

    sub-int/2addr v2, v3

    .line 471
    invoke-interface {v0, v2}, Landroid/widget/SectionIndexer;->getSectionForPosition(I)I

    move-result v2

    .line 472
    const/4 v3, -0x1

    if-eq v2, v3, :cond_3

    .line 473
    invoke-interface {v0}, Landroid/widget/SectionIndexer;->getSections()[Ljava/lang/Object;

    move-result-object v0

    aget-object v0, v0, v2

    check-cast v0, Ljava/lang/String;

    .line 474
    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v2

    if-nez v2, :cond_3

    .line 475
    iget-object v1, p0, Lmiui/widget/AlphabetFastIndexer;->vN:Lmiui/widget/AlphabetFastIndexer$b;

    iget-object v1, v1, Lmiui/widget/AlphabetFastIndexer$b;->AQ:[Ljava/lang/String;

    invoke-static {v1, v0}, Ljava/util/Arrays;->binarySearch([Ljava/lang/Object;Ljava/lang/Object;)I

    move-result v0

    .line 480
    :goto_1
    iget v1, p0, Lmiui/widget/AlphabetFastIndexer;->vF:I

    if-eq v1, v0, :cond_0

    .line 481
    iput v0, p0, Lmiui/widget/AlphabetFastIndexer;->vF:I

    .line 482
    const/4 v0, 0x1

    iget v1, p0, Lmiui/widget/AlphabetFastIndexer;->vP:I

    if-eq v0, v1, :cond_2

    .line 483
    iget v0, p0, Lmiui/widget/AlphabetFastIndexer;->vF:I

    invoke-direct {p0, v0}, Lmiui/widget/AlphabetFastIndexer;->P(I)V

    .line 485
    :cond_2
    invoke-virtual {p0}, Lmiui/widget/AlphabetFastIndexer;->invalidate()V

    goto :goto_0

    :cond_3
    move v0, v1

    goto :goto_1
.end method

.method private bZ()Landroid/widget/SectionIndexer;
    .locals 3

    .prologue
    const/4 v1, 0x0

    .line 498
    iget-object v0, p0, Lmiui/widget/AlphabetFastIndexer;->vD:Landroid/widget/AdapterView;

    if-nez v0, :cond_0

    .line 512
    :goto_0
    return-object v1

    .line 503
    :cond_0
    iget-object v0, p0, Lmiui/widget/AlphabetFastIndexer;->vD:Landroid/widget/AdapterView;

    invoke-virtual {v0}, Landroid/widget/AdapterView;->getAdapter()Landroid/widget/Adapter;

    move-result-object v0

    .line 505
    :goto_1
    instance-of v2, v0, Landroid/widget/SectionIndexer;

    if-nez v2, :cond_1

    instance-of v2, v0, Landroid/widget/WrapperListAdapter;

    if-eqz v2, :cond_1

    .line 506
    check-cast v0, Landroid/widget/WrapperListAdapter;

    invoke-interface {v0}, Landroid/widget/WrapperListAdapter;->getWrappedAdapter()Landroid/widget/ListAdapter;

    move-result-object v0

    goto :goto_1

    .line 508
    :cond_1
    instance-of v2, v0, Landroid/widget/SectionIndexer;

    if-eqz v2, :cond_2

    .line 509
    check-cast v0, Landroid/widget/SectionIndexer;

    :goto_2
    move-object v1, v0

    .line 512
    goto :goto_0

    :cond_2
    move-object v0, v1

    goto :goto_2
.end method

.method static synthetic c(Lmiui/widget/AlphabetFastIndexer;)Landroid/widget/TextView;
    .locals 1

    .prologue
    .line 76
    iget-object v0, p0, Lmiui/widget/AlphabetFastIndexer;->vE:Landroid/widget/TextView;

    return-object v0
.end method

.method private ca()I
    .locals 1

    .prologue
    .line 516
    iget-object v0, p0, Lmiui/widget/AlphabetFastIndexer;->vD:Landroid/widget/AdapterView;

    instance-of v0, v0, Landroid/widget/ListView;

    if-eqz v0, :cond_0

    .line 517
    iget-object v0, p0, Lmiui/widget/AlphabetFastIndexer;->vD:Landroid/widget/AdapterView;

    check-cast v0, Landroid/widget/ListView;

    invoke-virtual {v0}, Landroid/widget/ListView;->getHeaderViewsCount()I

    move-result v0

    .line 520
    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method


# virtual methods
.method public attatch(Landroid/widget/AdapterView;)V
    .locals 6
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Landroid/widget/AdapterView",
            "<*>;)V"
        }
    .end annotation

    .prologue
    const/16 v5, 0x11

    const/4 v4, -0x2

    const/4 v3, 0x0

    .line 385
    iget-object v0, p0, Lmiui/widget/AlphabetFastIndexer;->vD:Landroid/widget/AdapterView;

    if-ne v0, p1, :cond_1

    .line 424
    :cond_0
    :goto_0
    return-void

    .line 389
    :cond_1
    invoke-virtual {p0}, Lmiui/widget/AlphabetFastIndexer;->detach()V

    .line 390
    if-eqz p1, :cond_0

    .line 394
    const/4 v0, -0x1

    iput v0, p0, Lmiui/widget/AlphabetFastIndexer;->vF:I

    .line 396
    iput-object p1, p0, Lmiui/widget/AlphabetFastIndexer;->vD:Landroid/widget/AdapterView;

    .line 397
    invoke-virtual {p0}, Lmiui/widget/AlphabetFastIndexer;->getContext()Landroid/content/Context;

    move-result-object v1

    .line 399
    invoke-virtual {p0}, Lmiui/widget/AlphabetFastIndexer;->getParent()Landroid/view/ViewParent;

    move-result-object v0

    check-cast v0, Landroid/widget/FrameLayout;

    .line 402
    new-instance v2, Landroid/widget/TextView;

    invoke-direct {v2, v1}, Landroid/widget/TextView;-><init>(Landroid/content/Context;)V

    iput-object v2, p0, Lmiui/widget/AlphabetFastIndexer;->vE:Landroid/widget/TextView;

    .line 403
    new-instance v1, Landroid/widget/FrameLayout$LayoutParams;

    invoke-direct {v1, v4, v4, v5}, Landroid/widget/FrameLayout$LayoutParams;-><init>(III)V

    .line 406
    iget v2, p0, Lmiui/widget/AlphabetFastIndexer;->vH:I

    iput v2, v1, Landroid/widget/FrameLayout$LayoutParams;->leftMargin:I

    .line 407
    iget v2, p0, Lmiui/widget/AlphabetFastIndexer;->vI:I

    iput v2, v1, Landroid/widget/FrameLayout$LayoutParams;->topMargin:I

    .line 408
    iget-object v2, p0, Lmiui/widget/AlphabetFastIndexer;->vE:Landroid/widget/TextView;

    invoke-virtual {v2, v1}, Landroid/widget/TextView;->setLayoutParams(Landroid/view/ViewGroup$LayoutParams;)V

    .line 409
    iget-object v1, p0, Lmiui/widget/AlphabetFastIndexer;->vE:Landroid/widget/TextView;

    invoke-static {v3, v3}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result v2

    invoke-static {v3, v3}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result v3

    invoke-virtual {v1, v2, v3}, Landroid/widget/TextView;->measure(II)V

    .line 411
    iget-object v1, p0, Lmiui/widget/AlphabetFastIndexer;->vE:Landroid/widget/TextView;

    iget-object v2, p0, Lmiui/widget/AlphabetFastIndexer;->vL:Landroid/graphics/drawable/Drawable;

    invoke-virtual {v1, v2}, Landroid/widget/TextView;->setBackground(Landroid/graphics/drawable/Drawable;)V

    .line 412
    iget-object v1, p0, Lmiui/widget/AlphabetFastIndexer;->vE:Landroid/widget/TextView;

    invoke-virtual {v1, v5}, Landroid/widget/TextView;->setGravity(I)V

    .line 413
    iget-object v1, p0, Lmiui/widget/AlphabetFastIndexer;->vE:Landroid/widget/TextView;

    iget v2, p0, Lmiui/widget/AlphabetFastIndexer;->vJ:I

    int-to-float v2, v2

    invoke-virtual {v1, v2}, Landroid/widget/TextView;->setTextSize(F)V

    .line 414
    iget-object v1, p0, Lmiui/widget/AlphabetFastIndexer;->vE:Landroid/widget/TextView;

    iget v2, p0, Lmiui/widget/AlphabetFastIndexer;->vK:I

    invoke-virtual {v1, v2}, Landroid/widget/TextView;->setTextColor(I)V

    .line 415
    iget-object v1, p0, Lmiui/widget/AlphabetFastIndexer;->vE:Landroid/widget/TextView;

    const/16 v2, 0x8

    invoke-virtual {v1, v2}, Landroid/widget/TextView;->setVisibility(I)V

    .line 416
    iget-object v1, p0, Lmiui/widget/AlphabetFastIndexer;->vE:Landroid/widget/TextView;

    invoke-virtual {v0, v1}, Landroid/widget/FrameLayout;->addView(Landroid/view/View;)V

    .line 419
    invoke-virtual {p0}, Lmiui/widget/AlphabetFastIndexer;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v0

    check-cast v0, Landroid/widget/FrameLayout$LayoutParams;

    .line 420
    iget v1, p0, Lmiui/widget/AlphabetFastIndexer;->vG:I

    or-int/lit8 v1, v1, 0x30

    iput v1, v0, Landroid/widget/FrameLayout$LayoutParams;->gravity:I

    .line 421
    invoke-virtual {p0, v0}, Lmiui/widget/AlphabetFastIndexer;->setLayoutParams(Landroid/view/ViewGroup$LayoutParams;)V

    .line 423
    invoke-direct {p0}, Lmiui/widget/AlphabetFastIndexer;->bY()V

    goto :goto_0
.end method

.method public decorateScrollListener(Landroid/widget/AbsListView$OnScrollListener;)Landroid/widget/AbsListView$OnScrollListener;
    .locals 1

    .prologue
    .line 447
    new-instance v0, Lmiui/widget/AlphabetFastIndexer$a;

    invoke-direct {v0, p0, p1}, Lmiui/widget/AlphabetFastIndexer$a;-><init>(Lmiui/widget/AlphabetFastIndexer;Landroid/widget/AbsListView$OnScrollListener;)V

    return-object v0
.end method

.method public detach()V
    .locals 2

    .prologue
    .line 430
    iget-object v0, p0, Lmiui/widget/AlphabetFastIndexer;->vD:Landroid/widget/AdapterView;

    if-eqz v0, :cond_0

    .line 431
    const/4 v0, 0x0

    invoke-virtual {p0, v0}, Lmiui/widget/AlphabetFastIndexer;->stop(I)V

    .line 432
    invoke-virtual {p0}, Lmiui/widget/AlphabetFastIndexer;->getParent()Landroid/view/ViewParent;

    move-result-object v0

    check-cast v0, Landroid/widget/FrameLayout;

    .line 433
    iget-object v1, p0, Lmiui/widget/AlphabetFastIndexer;->vE:Landroid/widget/TextView;

    invoke-virtual {v0, v1}, Landroid/widget/FrameLayout;->removeView(Landroid/view/View;)V

    .line 435
    const/16 v0, 0x8

    invoke-virtual {p0, v0}, Lmiui/widget/AlphabetFastIndexer;->setVisibility(I)V

    .line 436
    const/4 v0, 0x0

    iput-object v0, p0, Lmiui/widget/AlphabetFastIndexer;->vD:Landroid/widget/AdapterView;

    .line 438
    :cond_0
    return-void
.end method

.method public drawThumb(Ljava/lang/CharSequence;)V
    .locals 2

    .prologue
    .line 456
    iget v0, p0, Lmiui/widget/AlphabetFastIndexer;->vP:I

    if-nez v0, :cond_0

    iget v0, p0, Lmiui/widget/AlphabetFastIndexer;->vO:I

    const/4 v1, 0x2

    if-ne v0, v1, :cond_0

    .line 458
    invoke-direct {p0, p1}, Lmiui/widget/AlphabetFastIndexer;->b(Ljava/lang/CharSequence;)V

    .line 460
    :cond_0
    return-void
.end method

.method public getIndexerIntrinsicWidth()I
    .locals 1

    .prologue
    .line 493
    invoke-virtual {p0}, Lmiui/widget/AlphabetFastIndexer;->getBackground()Landroid/graphics/drawable/Drawable;

    move-result-object v0

    .line 494
    if-eqz v0, :cond_0

    invoke-virtual {v0}, Landroid/graphics/drawable/Drawable;->getIntrinsicWidth()I

    move-result v0

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method protected onDraw(Landroid/graphics/Canvas;)V
    .locals 8

    .prologue
    const/high16 v3, 0x40000000

    .line 344
    invoke-super {p0, p1}, Landroid/widget/ImageView;->onDraw(Landroid/graphics/Canvas;)V

    .line 346
    invoke-virtual {p0}, Lmiui/widget/AlphabetFastIndexer;->getPaddingTop()I

    move-result v0

    .line 347
    invoke-virtual {p0}, Lmiui/widget/AlphabetFastIndexer;->getHeight()I

    move-result v1

    sub-int/2addr v1, v0

    invoke-virtual {p0}, Lmiui/widget/AlphabetFastIndexer;->getPaddingBottom()I

    move-result v2

    sub-int/2addr v1, v2

    .line 348
    if-gtz v1, :cond_0

    .line 368
    :goto_0
    return-void

    .line 352
    :cond_0
    iget-object v2, p0, Lmiui/widget/AlphabetFastIndexer;->vN:Lmiui/widget/AlphabetFastIndexer$b;

    iget-object v6, v2, Lmiui/widget/AlphabetFastIndexer$b;->AQ:[Ljava/lang/String;

    .line 353
    int-to-float v1, v1

    array-length v2, v6

    int-to-float v2, v2

    div-float v7, v1, v2

    .line 354
    invoke-virtual {p0}, Lmiui/widget/AlphabetFastIndexer;->getWidth()I

    move-result v1

    int-to-float v1, v1

    div-float v4, v1, v3

    .line 356
    iget-object v1, p0, Lmiui/widget/AlphabetFastIndexer;->vN:Lmiui/widget/AlphabetFastIndexer$b;

    invoke-virtual {v1}, Lmiui/widget/AlphabetFastIndexer$b;->cL()V

    .line 359
    int-to-float v0, v0

    div-float v1, v7, v3

    add-float v5, v0, v1

    .line 360
    const/4 v3, 0x0

    :goto_1
    array-length v0, v6

    if-ge v3, v0, :cond_1

    .line 362
    iget-object v0, p0, Lmiui/widget/AlphabetFastIndexer;->vN:Lmiui/widget/AlphabetFastIndexer$b;

    invoke-virtual {p0}, Lmiui/widget/AlphabetFastIndexer;->isPressed()Z

    move-result v2

    move-object v1, p1

    invoke-virtual/range {v0 .. v5}, Lmiui/widget/AlphabetFastIndexer$b;->a(Landroid/graphics/Canvas;ZIFF)V

    .line 364
    add-float/2addr v5, v7

    .line 360
    add-int/lit8 v3, v3, 0x1

    goto :goto_1

    .line 367
    :cond_1
    iget-object v0, p0, Lmiui/widget/AlphabetFastIndexer;->vN:Lmiui/widget/AlphabetFastIndexer$b;

    invoke-virtual {v0, p1}, Lmiui/widget/AlphabetFastIndexer$b;->g(Landroid/graphics/Canvas;)V

    goto :goto_0
.end method

.method protected onSizeChanged(IIII)V
    .locals 1

    .prologue
    .line 337
    invoke-super {p0, p1, p2, p3, p4}, Landroid/widget/ImageView;->onSizeChanged(IIII)V

    .line 338
    const/4 v0, -0x1

    iput v0, p0, Lmiui/widget/AlphabetFastIndexer;->vF:I

    .line 339
    iget-object v0, p0, Lmiui/widget/AlphabetFastIndexer;->vS:Ljava/lang/Runnable;

    invoke-virtual {p0, v0}, Lmiui/widget/AlphabetFastIndexer;->post(Ljava/lang/Runnable;)Z

    .line 340
    return-void
.end method

.method public onTouchEvent(Landroid/view/MotionEvent;)Z
    .locals 4

    .prologue
    const/4 v1, 0x1

    const/4 v0, 0x0

    .line 559
    iget-object v2, p0, Lmiui/widget/AlphabetFastIndexer;->vD:Landroid/widget/AdapterView;

    if-nez v2, :cond_0

    .line 560
    invoke-virtual {p0, v0}, Lmiui/widget/AlphabetFastIndexer;->stop(I)V

    .line 597
    :goto_0
    return v0

    .line 564
    :cond_0
    invoke-direct {p0}, Lmiui/widget/AlphabetFastIndexer;->bZ()Landroid/widget/SectionIndexer;

    move-result-object v2

    .line 565
    if-nez v2, :cond_1

    .line 566
    invoke-virtual {p0, v0}, Lmiui/widget/AlphabetFastIndexer;->stop(I)V

    goto :goto_0

    .line 570
    :cond_1
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getAction()I

    move-result v3

    and-int/lit16 v3, v3, 0xff

    .line 571
    packed-switch v3, :pswitch_data_0

    .line 594
    :goto_1
    const/16 v0, 0x5dc

    invoke-virtual {p0, v0}, Lmiui/widget/AlphabetFastIndexer;->stop(I)V

    :cond_2
    :goto_2
    move v0, v1

    .line 597
    goto :goto_0

    .line 573
    :pswitch_0
    iput v1, p0, Lmiui/widget/AlphabetFastIndexer;->vP:I

    .line 574
    invoke-virtual {p0, v1}, Lmiui/widget/AlphabetFastIndexer;->setPressed(Z)V

    .line 577
    :pswitch_1
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getY()F

    move-result v3

    invoke-direct {p0, v3, v2}, Lmiui/widget/AlphabetFastIndexer;->a(FLandroid/widget/SectionIndexer;)I

    move-result v3

    .line 578
    if-gez v3, :cond_3

    .line 579
    iget-object v2, p0, Lmiui/widget/AlphabetFastIndexer;->vD:Landroid/widget/AdapterView;

    invoke-virtual {v2, v0}, Landroid/widget/AdapterView;->setSelection(I)V

    goto :goto_2

    .line 581
    :cond_3
    invoke-direct {p0, v2, v3}, Lmiui/widget/AlphabetFastIndexer;->a(Landroid/widget/SectionIndexer;I)V

    .line 582
    iget-object v0, p0, Lmiui/widget/AlphabetFastIndexer;->vN:Lmiui/widget/AlphabetFastIndexer$b;

    if-eqz v0, :cond_2

    invoke-virtual {p1}, Landroid/view/MotionEvent;->getY()F

    move-result v0

    invoke-virtual {p0}, Lmiui/widget/AlphabetFastIndexer;->getTop()I

    move-result v2

    invoke-virtual {p0}, Lmiui/widget/AlphabetFastIndexer;->getPaddingTop()I

    move-result v3

    add-int/2addr v2, v3

    int-to-float v2, v2

    cmpl-float v0, v0, v2

    if-lez v0, :cond_2

    invoke-virtual {p1}, Landroid/view/MotionEvent;->getY()F

    move-result v0

    invoke-virtual {p0}, Lmiui/widget/AlphabetFastIndexer;->getBottom()I

    move-result v2

    invoke-virtual {p0}, Lmiui/widget/AlphabetFastIndexer;->getPaddingBottom()I

    move-result v3

    sub-int/2addr v2, v3

    int-to-float v2, v2

    cmpg-float v0, v0, v2

    if-gez v0, :cond_2

    .line 585
    iget-object v0, p0, Lmiui/widget/AlphabetFastIndexer;->vN:Lmiui/widget/AlphabetFastIndexer$b;

    invoke-virtual {p0}, Lmiui/widget/AlphabetFastIndexer;->getWidth()I

    move-result v2

    div-int/lit8 v2, v2, 0x2

    int-to-float v2, v2

    invoke-virtual {p1}, Landroid/view/MotionEvent;->getY()F

    move-result v3

    invoke-virtual {v0, v2, v3}, Lmiui/widget/AlphabetFastIndexer$b;->b(FF)V

    .line 586
    invoke-virtual {p0}, Lmiui/widget/AlphabetFastIndexer;->postInvalidate()V

    goto :goto_2

    .line 592
    :pswitch_2
    iget v0, p0, Lmiui/widget/AlphabetFastIndexer;->vF:I

    invoke-direct {p0, v0}, Lmiui/widget/AlphabetFastIndexer;->P(I)V

    goto :goto_1

    .line 571
    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_0
        :pswitch_2
        :pswitch_1
        :pswitch_2
    .end packed-switch
.end method

.method public setOverlayOffset(II)V
    .locals 0

    .prologue
    .line 331
    iput p1, p0, Lmiui/widget/AlphabetFastIndexer;->vH:I

    .line 332
    iput p2, p0, Lmiui/widget/AlphabetFastIndexer;->vI:I

    .line 333
    return-void
.end method

.method public setVerticalPosition(Z)V
    .locals 1

    .prologue
    .line 322
    if-eqz p1, :cond_0

    const/4 v0, 0x5

    :goto_0
    iput v0, p0, Lmiui/widget/AlphabetFastIndexer;->vG:I

    .line 323
    return-void

    .line 322
    :cond_0
    const/4 v0, 0x3

    goto :goto_0
.end method

.method stop(I)V
    .locals 4

    .prologue
    const/4 v1, 0x1

    const/4 v0, 0x0

    .line 601
    invoke-virtual {p0, v0}, Lmiui/widget/AlphabetFastIndexer;->setPressed(Z)V

    .line 602
    iput v0, p0, Lmiui/widget/AlphabetFastIndexer;->vP:I

    .line 603
    invoke-virtual {p0}, Lmiui/widget/AlphabetFastIndexer;->postInvalidate()V

    .line 604
    iget-object v0, p0, Lmiui/widget/AlphabetFastIndexer;->mHandler:Landroid/os/Handler;

    invoke-virtual {v0, v1}, Landroid/os/Handler;->removeMessages(I)V

    .line 605
    if-gtz p1, :cond_1

    .line 606
    iget-object v0, p0, Lmiui/widget/AlphabetFastIndexer;->vE:Landroid/widget/TextView;

    if-eqz v0, :cond_0

    .line 607
    iget-object v0, p0, Lmiui/widget/AlphabetFastIndexer;->vE:Landroid/widget/TextView;

    const/16 v1, 0x8

    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setVisibility(I)V

    .line 613
    :cond_0
    :goto_0
    return-void

    .line 610
    :cond_1
    iget-object v0, p0, Lmiui/widget/AlphabetFastIndexer;->mHandler:Landroid/os/Handler;

    invoke-virtual {v0, v1}, Landroid/os/Handler;->obtainMessage(I)Landroid/os/Message;

    move-result-object v0

    .line 611
    iget-object v1, p0, Lmiui/widget/AlphabetFastIndexer;->mHandler:Landroid/os/Handler;

    int-to-long v2, p1

    invoke-virtual {v1, v0, v2, v3}, Landroid/os/Handler;->sendMessageDelayed(Landroid/os/Message;J)Z

    goto :goto_0
.end method
