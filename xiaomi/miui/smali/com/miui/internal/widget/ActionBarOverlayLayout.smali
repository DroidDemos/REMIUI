.class public Lcom/miui/internal/widget/ActionBarOverlayLayout;
.super Landroid/widget/FrameLayout;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/internal/widget/ActionBarOverlayLayout$1;,
        Lcom/miui/internal/widget/ActionBarOverlayLayout$a;,
        Lcom/miui/internal/widget/ActionBarOverlayLayout$ContentMaskAnimator;,
        Lcom/miui/internal/widget/ActionBarOverlayLayout$c;,
        Lcom/miui/internal/widget/ActionBarOverlayLayout$b;
    }
.end annotation


# instance fields
.field private aP:Landroid/graphics/Paint;

.field private ha:Landroid/graphics/Bitmap;

.field private hb:Landroid/graphics/Bitmap;

.field private hc:Landroid/graphics/Bitmap;

.field private hd:Landroid/graphics/Bitmap;

.field private iQ:Landroid/util/TypedValue;

.field private iR:Landroid/util/TypedValue;

.field private iS:Landroid/util/TypedValue;

.field private iT:Landroid/util/TypedValue;

.field private jc:Lmiui/app/ActionBar;

.field private je:I

.field private ln:Lcom/miui/internal/widget/ActionBarContextView;

.field protected mActionBarTop:Lcom/miui/internal/widget/ActionBarContainer;

.field protected mActionBarView:Lcom/miui/internal/widget/ActionBarView;

.field private mActionMode:Landroid/view/ActionMode;

.field protected mContentView:Landroid/view/View;

.field private rA:Lcom/miui/internal/view/menu/MenuDialogHelper;

.field private rB:Lcom/miui/internal/widget/ActionBarOverlayLayout$a;

.field private rC:Lmiui/app/OnStatusBarChangeListener;

.field private ri:Lcom/miui/internal/widget/ActionBarContainer;

.field private rj:Landroid/view/View;

.field private rk:Landroid/view/Window$Callback;

.field private rl:Z

.field private rm:Z

.field private rn:Z

.field private ro:Z

.field private rp:Landroid/graphics/drawable/Drawable;

.field private rq:Z

.field private rr:Landroid/graphics/Rect;

.field private rs:Landroid/graphics/Rect;

.field private rt:Landroid/graphics/Rect;

.field private ru:Landroid/graphics/Rect;

.field private rv:Landroid/graphics/Rect;

.field private rw:Landroid/graphics/Rect;

.field private rz:Lcom/miui/internal/view/menu/ContextMenuBuilder;


# direct methods
.method public constructor <init>(Landroid/content/Context;)V
    .locals 1

    .prologue
    .line 120
    const/4 v0, 0x0

    invoke-direct {p0, p1, v0}, Lcom/miui/internal/widget/ActionBarOverlayLayout;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    .line 121
    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 1

    .prologue
    .line 124
    const/4 v0, 0x0

    invoke-direct {p0, p1, p2, v0}, Lcom/miui/internal/widget/ActionBarOverlayLayout;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    .line 125
    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V
    .locals 7

    .prologue
    const/16 v6, 0x9

    const/16 v5, 0x8

    const/4 v4, 0x7

    const/4 v3, 0x6

    const/4 v2, 0x0

    .line 128
    invoke-direct {p0, p1, p2, p3}, Landroid/widget/FrameLayout;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    .line 99
    new-instance v0, Landroid/graphics/Rect;

    invoke-direct {v0}, Landroid/graphics/Rect;-><init>()V

    iput-object v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->rr:Landroid/graphics/Rect;

    .line 101
    new-instance v0, Landroid/graphics/Rect;

    invoke-direct {v0}, Landroid/graphics/Rect;-><init>()V

    iput-object v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->rs:Landroid/graphics/Rect;

    .line 103
    new-instance v0, Landroid/graphics/Rect;

    invoke-direct {v0}, Landroid/graphics/Rect;-><init>()V

    iput-object v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->rt:Landroid/graphics/Rect;

    .line 105
    new-instance v0, Landroid/graphics/Rect;

    invoke-direct {v0}, Landroid/graphics/Rect;-><init>()V

    iput-object v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->ru:Landroid/graphics/Rect;

    .line 107
    new-instance v0, Landroid/graphics/Rect;

    invoke-direct {v0}, Landroid/graphics/Rect;-><init>()V

    iput-object v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->rv:Landroid/graphics/Rect;

    .line 109
    new-instance v0, Landroid/graphics/Rect;

    invoke-direct {v0}, Landroid/graphics/Rect;-><init>()V

    iput-object v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->rw:Landroid/graphics/Rect;

    .line 115
    new-instance v0, Lcom/miui/internal/widget/ActionBarOverlayLayout$a;

    const/4 v1, 0x0

    invoke-direct {v0, p0, v1}, Lcom/miui/internal/widget/ActionBarOverlayLayout$a;-><init>(Lcom/miui/internal/widget/ActionBarOverlayLayout;Lcom/miui/internal/widget/ActionBarOverlayLayout$1;)V

    iput-object v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->rB:Lcom/miui/internal/widget/ActionBarOverlayLayout$a;

    .line 130
    sget-object v0, Lcom/miui/internal/R$styleable;->Window:[I

    invoke-virtual {p1, p2, v0, p3, v2}, Landroid/content/Context;->obtainStyledAttributes(Landroid/util/AttributeSet;[III)Landroid/content/res/TypedArray;

    move-result-object v0

    .line 132
    invoke-virtual {v0, v3}, Landroid/content/res/TypedArray;->hasValue(I)Z

    move-result v1

    if-eqz v1, :cond_0

    .line 133
    new-instance v1, Landroid/util/TypedValue;

    invoke-direct {v1}, Landroid/util/TypedValue;-><init>()V

    iput-object v1, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->iS:Landroid/util/TypedValue;

    .line 134
    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->iS:Landroid/util/TypedValue;

    invoke-virtual {v0, v3, v1}, Landroid/content/res/TypedArray;->getValue(ILandroid/util/TypedValue;)Z

    .line 136
    :cond_0
    invoke-virtual {v0, v4}, Landroid/content/res/TypedArray;->hasValue(I)Z

    move-result v1

    if-eqz v1, :cond_1

    .line 137
    new-instance v1, Landroid/util/TypedValue;

    invoke-direct {v1}, Landroid/util/TypedValue;-><init>()V

    iput-object v1, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->iQ:Landroid/util/TypedValue;

    .line 138
    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->iQ:Landroid/util/TypedValue;

    invoke-virtual {v0, v4, v1}, Landroid/content/res/TypedArray;->getValue(ILandroid/util/TypedValue;)Z

    .line 140
    :cond_1
    invoke-virtual {v0, v5}, Landroid/content/res/TypedArray;->hasValue(I)Z

    move-result v1

    if-eqz v1, :cond_2

    .line 141
    new-instance v1, Landroid/util/TypedValue;

    invoke-direct {v1}, Landroid/util/TypedValue;-><init>()V

    iput-object v1, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->iR:Landroid/util/TypedValue;

    .line 142
    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->iR:Landroid/util/TypedValue;

    invoke-virtual {v0, v5, v1}, Landroid/content/res/TypedArray;->getValue(ILandroid/util/TypedValue;)Z

    .line 144
    :cond_2
    invoke-virtual {v0, v6}, Landroid/content/res/TypedArray;->hasValue(I)Z

    move-result v1

    if-eqz v1, :cond_3

    .line 145
    new-instance v1, Landroid/util/TypedValue;

    invoke-direct {v1}, Landroid/util/TypedValue;-><init>()V

    iput-object v1, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->iT:Landroid/util/TypedValue;

    .line 146
    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->iT:Landroid/util/TypedValue;

    invoke-virtual {v0, v6, v1}, Landroid/content/res/TypedArray;->getValue(ILandroid/util/TypedValue;)Z

    .line 149
    :cond_3
    const/4 v1, 0x3

    invoke-virtual {v0, v1, v2}, Landroid/content/res/TypedArray;->getBoolean(IZ)Z

    move-result v1

    iput-boolean v1, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->ro:Z

    .line 150
    iget-boolean v1, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->ro:Z

    if-eqz v1, :cond_4

    .line 151
    const/4 v1, 0x4

    invoke-virtual {v0, v1}, Landroid/content/res/TypedArray;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v1

    iput-object v1, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->rp:Landroid/graphics/drawable/Drawable;

    .line 153
    :cond_4
    invoke-virtual {v0}, Landroid/content/res/TypedArray;->recycle()V

    .line 154
    return-void
.end method

.method private B(I)I
    .locals 6

    .prologue
    const/4 v0, 0x0

    .line 406
    invoke-static {p1}, Landroid/view/View$MeasureSpec;->getMode(I)I

    move-result v1

    .line 407
    invoke-static {p1}, Landroid/view/View$MeasureSpec;->getSize(I)I

    move-result v2

    .line 409
    const/high16 v3, -0x80000000

    if-ne v1, v3, :cond_1

    .line 410
    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->getContext()Landroid/content/Context;

    move-result-object v1

    invoke-virtual {v1}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    invoke-virtual {v1}, Landroid/content/res/Resources;->getDisplayMetrics()Landroid/util/DisplayMetrics;

    move-result-object v3

    .line 411
    iget v1, v3, Landroid/util/DisplayMetrics;->widthPixels:I

    iget v4, v3, Landroid/util/DisplayMetrics;->heightPixels:I

    if-ge v1, v4, :cond_2

    const/4 v1, 0x1

    .line 413
    :goto_0
    if-eqz v1, :cond_3

    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->iQ:Landroid/util/TypedValue;

    .line 414
    :goto_1
    if-eqz v1, :cond_1

    iget v4, v1, Landroid/util/TypedValue;->type:I

    if-eqz v4, :cond_1

    .line 416
    iget v4, v1, Landroid/util/TypedValue;->type:I

    const/4 v5, 0x5

    if-ne v4, v5, :cond_4

    .line 417
    invoke-virtual {v1, v3}, Landroid/util/TypedValue;->getDimension(Landroid/util/DisplayMetrics;)F

    move-result v0

    float-to-int v0, v0

    .line 424
    :cond_0
    :goto_2
    if-lez v0, :cond_1

    .line 425
    invoke-static {v0, v2}, Ljava/lang/Math;->min(II)I

    move-result v0

    const/high16 v1, 0x40000000

    invoke-static {v0, v1}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result p1

    .line 429
    :cond_1
    return p1

    :cond_2
    move v1, v0

    .line 411
    goto :goto_0

    .line 413
    :cond_3
    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->iS:Landroid/util/TypedValue;

    goto :goto_1

    .line 418
    :cond_4
    iget v4, v1, Landroid/util/TypedValue;->type:I

    const/4 v5, 0x6

    if-ne v4, v5, :cond_0

    .line 419
    iget v0, v3, Landroid/util/DisplayMetrics;->widthPixels:I

    int-to-float v0, v0

    iget v3, v3, Landroid/util/DisplayMetrics;->widthPixels:I

    int-to-float v3, v3

    invoke-virtual {v1, v0, v3}, Landroid/util/TypedValue;->getFraction(FF)F

    move-result v0

    float-to-int v0, v0

    goto :goto_2
.end method

.method private C(I)I
    .locals 6

    .prologue
    const/4 v0, 0x0

    .line 433
    invoke-static {p1}, Landroid/view/View$MeasureSpec;->getMode(I)I

    move-result v1

    .line 434
    invoke-static {p1}, Landroid/view/View$MeasureSpec;->getSize(I)I

    move-result v2

    .line 437
    const/high16 v3, -0x80000000

    if-ne v1, v3, :cond_1

    .line 438
    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->getContext()Landroid/content/Context;

    move-result-object v1

    invoke-virtual {v1}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    invoke-virtual {v1}, Landroid/content/res/Resources;->getDisplayMetrics()Landroid/util/DisplayMetrics;

    move-result-object v3

    .line 439
    iget v1, v3, Landroid/util/DisplayMetrics;->widthPixels:I

    iget v4, v3, Landroid/util/DisplayMetrics;->heightPixels:I

    if-ge v1, v4, :cond_2

    const/4 v1, 0x1

    .line 441
    :goto_0
    if-eqz v1, :cond_3

    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->iR:Landroid/util/TypedValue;

    .line 442
    :goto_1
    if-eqz v1, :cond_1

    iget v4, v1, Landroid/util/TypedValue;->type:I

    if-eqz v4, :cond_1

    .line 444
    iget v4, v1, Landroid/util/TypedValue;->type:I

    const/4 v5, 0x5

    if-ne v4, v5, :cond_4

    .line 445
    invoke-virtual {v1, v3}, Landroid/util/TypedValue;->getDimension(Landroid/util/DisplayMetrics;)F

    move-result v0

    float-to-int v0, v0

    .line 452
    :cond_0
    :goto_2
    if-lez v0, :cond_1

    .line 453
    invoke-static {v0, v2}, Ljava/lang/Math;->min(II)I

    move-result v0

    const/high16 v1, 0x40000000

    invoke-static {v0, v1}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result p1

    .line 457
    :cond_1
    return p1

    :cond_2
    move v1, v0

    .line 439
    goto :goto_0

    .line 441
    :cond_3
    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->iT:Landroid/util/TypedValue;

    goto :goto_1

    .line 446
    :cond_4
    iget v4, v1, Landroid/util/TypedValue;->type:I

    const/4 v5, 0x6

    if-ne v4, v5, :cond_0

    .line 447
    iget v0, v3, Landroid/util/DisplayMetrics;->heightPixels:I

    int-to-float v0, v0

    iget v3, v3, Landroid/util/DisplayMetrics;->heightPixels:I

    int-to-float v3, v3

    invoke-virtual {v1, v0, v3}, Landroid/util/TypedValue;->getFraction(FF)F

    move-result v0

    float-to-int v0, v0

    goto :goto_2
.end method

.method static synthetic a(Lcom/miui/internal/widget/ActionBarOverlayLayout;Landroid/view/ActionMode;)Landroid/view/ActionMode;
    .locals 0

    .prologue
    .line 47
    iput-object p1, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->mActionMode:Landroid/view/ActionMode;

    return-object p1
.end method

.method static synthetic a(Lcom/miui/internal/widget/ActionBarOverlayLayout;)Landroid/view/View;
    .locals 1

    .prologue
    .line 47
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->rj:Landroid/view/View;

    return-object v0
.end method

.method private a(Landroid/view/ActionMode$Callback;)Lcom/miui/internal/widget/ActionBarOverlayLayout$b;
    .locals 1

    .prologue
    .line 629
    instance-of v0, p1, Lmiui/view/SearchActionMode$Callback;

    if-eqz v0, :cond_0

    .line 630
    new-instance v0, Lcom/miui/internal/widget/ActionBarOverlayLayout$c;

    invoke-direct {v0, p0, p1}, Lcom/miui/internal/widget/ActionBarOverlayLayout$c;-><init>(Lcom/miui/internal/widget/ActionBarOverlayLayout;Landroid/view/ActionMode$Callback;)V

    .line 632
    :goto_0
    return-object v0

    :cond_0
    new-instance v0, Lcom/miui/internal/widget/ActionBarOverlayLayout$b;

    invoke-direct {v0, p0, p1}, Lcom/miui/internal/widget/ActionBarOverlayLayout$b;-><init>(Lcom/miui/internal/widget/ActionBarOverlayLayout;Landroid/view/ActionMode$Callback;)V

    goto :goto_0
.end method

.method private a(Landroid/graphics/Rect;Landroid/graphics/Rect;)V
    .locals 3

    .prologue
    const/4 v2, 0x0

    .line 226
    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->isRootSubDecor()Z

    move-result v0

    .line 227
    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->isTranslucentStatus()Z

    move-result v1

    .line 229
    if-nez v0, :cond_0

    .line 230
    iput v2, p1, Landroid/graphics/Rect;->bottom:I

    .line 233
    :cond_0
    invoke-virtual {p2, p1}, Landroid/graphics/Rect;->set(Landroid/graphics/Rect;)V

    .line 234
    if-eqz v0, :cond_1

    if-eqz v1, :cond_2

    :cond_1
    iget-boolean v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->ro:Z

    if-nez v0, :cond_2

    .line 236
    iput v2, p2, Landroid/graphics/Rect;->top:I

    .line 238
    :cond_2
    return-void
.end method

.method private a(Landroid/view/KeyEvent;)Z
    .locals 3

    .prologue
    const/4 v0, 0x1

    .line 581
    invoke-virtual {p1}, Landroid/view/KeyEvent;->getKeyCode()I

    move-result v1

    const/4 v2, 0x4

    if-ne v1, v2, :cond_0

    invoke-virtual {p1}, Landroid/view/KeyEvent;->getAction()I

    move-result v1

    if-ne v1, v0, :cond_0

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method private a(Landroid/view/View;Landroid/graphics/Rect;ZZZZ)Z
    .locals 5

    .prologue
    const/4 v1, 0x1

    .line 241
    const/4 v2, 0x0

    .line 242
    invoke-virtual {p1}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v0

    check-cast v0, Landroid/widget/FrameLayout$LayoutParams;

    .line 243
    if-eqz p3, :cond_0

    iget v3, v0, Landroid/widget/FrameLayout$LayoutParams;->leftMargin:I

    iget v4, p2, Landroid/graphics/Rect;->left:I

    if-eq v3, v4, :cond_0

    .line 245
    iget v2, p2, Landroid/graphics/Rect;->left:I

    iput v2, v0, Landroid/widget/FrameLayout$LayoutParams;->leftMargin:I

    move v2, v1

    .line 247
    :cond_0
    if-eqz p4, :cond_1

    iget v3, v0, Landroid/widget/FrameLayout$LayoutParams;->topMargin:I

    iget v4, p2, Landroid/graphics/Rect;->top:I

    if-eq v3, v4, :cond_1

    .line 249
    iget v2, p2, Landroid/graphics/Rect;->top:I

    iput v2, v0, Landroid/widget/FrameLayout$LayoutParams;->topMargin:I

    move v2, v1

    .line 251
    :cond_1
    if-eqz p6, :cond_2

    iget v3, v0, Landroid/widget/FrameLayout$LayoutParams;->rightMargin:I

    iget v4, p2, Landroid/graphics/Rect;->right:I

    if-eq v3, v4, :cond_2

    .line 253
    iget v2, p2, Landroid/graphics/Rect;->right:I

    iput v2, v0, Landroid/widget/FrameLayout$LayoutParams;->rightMargin:I

    move v2, v1

    .line 255
    :cond_2
    if-eqz p5, :cond_3

    iget v3, v0, Landroid/widget/FrameLayout$LayoutParams;->bottomMargin:I

    iget v4, p2, Landroid/graphics/Rect;->bottom:I

    if-eq v3, v4, :cond_3

    .line 257
    iget v2, p2, Landroid/graphics/Rect;->bottom:I

    iput v2, v0, Landroid/widget/FrameLayout$LayoutParams;->bottomMargin:I

    move v0, v1

    .line 259
    :goto_0
    return v0

    :cond_3
    move v0, v2

    goto :goto_0
.end method

.method private aU()V
    .locals 2

    .prologue
    .line 534
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->mContentView:Landroid/view/View;

    if-nez v0, :cond_1

    .line 535
    const v0, 0x1020002

    invoke-virtual {p0, v0}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->findViewById(I)Landroid/view/View;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->mContentView:Landroid/view/View;

    .line 536
    sget v0, Lcom/miui/internal/R$id;->content_mask:I

    invoke-virtual {p0, v0}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->findViewById(I)Landroid/view/View;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->rj:Landroid/view/View;

    .line 537
    sget v0, Lcom/miui/internal/R$id;->action_bar_container:I

    invoke-virtual {p0, v0}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/widget/ActionBarContainer;

    iput-object v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->mActionBarTop:Lcom/miui/internal/widget/ActionBarContainer;

    .line 538
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->mActionBarTop:Lcom/miui/internal/widget/ActionBarContainer;

    if-eqz v0, :cond_0

    .line 539
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->mActionBarTop:Lcom/miui/internal/widget/ActionBarContainer;

    sget v1, Lcom/miui/internal/R$id;->action_context_bar:I

    invoke-virtual {v0, v1}, Lcom/miui/internal/widget/ActionBarContainer;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/widget/ActionBarContextView;

    iput-object v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->ln:Lcom/miui/internal/widget/ActionBarContextView;

    .line 540
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->mActionBarTop:Lcom/miui/internal/widget/ActionBarContainer;

    sget v1, Lcom/miui/internal/R$id;->action_bar:I

    invoke-virtual {v0, v1}, Lcom/miui/internal/widget/ActionBarContainer;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/widget/ActionBarView;

    iput-object v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->mActionBarView:Lcom/miui/internal/widget/ActionBarView;

    .line 542
    :cond_0
    sget v0, Lcom/miui/internal/R$id;->split_action_bar:I

    invoke-virtual {p0, v0}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/widget/ActionBarContainer;

    iput-object v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->ri:Lcom/miui/internal/widget/ActionBarContainer;

    .line 544
    :cond_1
    return-void
.end method

.method static synthetic b(Lcom/miui/internal/widget/ActionBarOverlayLayout;)Lcom/miui/internal/widget/ActionBarContainer;
    .locals 1

    .prologue
    .line 47
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->ri:Lcom/miui/internal/widget/ActionBarContainer;

    return-object v0
.end method

.method private bw()Z
    .locals 6

    .prologue
    const/4 v0, 0x1

    const/4 v1, 0x0

    .line 482
    const/4 v2, 0x2

    new-array v2, v2, [I

    .line 483
    invoke-virtual {p0, v2}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->getLocationOnScreen([I)V

    .line 484
    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->getResources()Landroid/content/res/Resources;

    move-result-object v3

    invoke-virtual {v3}, Landroid/content/res/Resources;->getDisplayMetrics()Landroid/util/DisplayMetrics;

    move-result-object v3

    .line 485
    aget v4, v2, v1

    if-eqz v4, :cond_0

    aget v4, v2, v0

    if-eqz v4, :cond_0

    aget v4, v2, v1

    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->getMeasuredWidth()I

    move-result v5

    add-int/2addr v4, v5

    iget v5, v3, Landroid/util/DisplayMetrics;->widthPixels:I

    if-eq v4, v5, :cond_0

    aget v2, v2, v0

    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->getMeasuredHeight()I

    move-result v4

    add-int/2addr v2, v4

    iget v3, v3, Landroid/util/DisplayMetrics;->heightPixels:I

    if-eq v2, v3, :cond_0

    :goto_0
    return v0

    :cond_0
    move v0, v1

    goto :goto_0
.end method

.method private bx()V
    .locals 1

    .prologue
    .line 797
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->rA:Lcom/miui/internal/view/menu/MenuDialogHelper;

    if-eqz v0, :cond_0

    .line 798
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->rA:Lcom/miui/internal/view/menu/MenuDialogHelper;

    invoke-virtual {v0}, Lcom/miui/internal/view/menu/MenuDialogHelper;->dismiss()V

    .line 799
    const/4 v0, 0x0

    iput-object v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->rz:Lcom/miui/internal/view/menu/ContextMenuBuilder;

    .line 801
    :cond_0
    return-void
.end method

.method static synthetic c(Lcom/miui/internal/widget/ActionBarOverlayLayout;)V
    .locals 0

    .prologue
    .line 47
    invoke-direct {p0}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->bx()V

    return-void
.end method

.method public static getActionBarOverlayLayout(Landroid/view/View;)Lcom/miui/internal/widget/ActionBarOverlayLayout;
    .locals 3

    .prologue
    const/4 v1, 0x0

    .line 157
    move-object v0, p0

    .line 158
    :goto_0
    if-eqz v0, :cond_2

    .line 159
    instance-of v2, v0, Lcom/miui/internal/widget/ActionBarOverlayLayout;

    if-eqz v2, :cond_0

    .line 160
    check-cast v0, Lcom/miui/internal/widget/ActionBarOverlayLayout;

    .line 170
    :goto_1
    return-object v0

    .line 164
    :cond_0
    invoke-virtual {v0}, Landroid/view/View;->getParent()Landroid/view/ViewParent;

    move-result-object v2

    instance-of v2, v2, Landroid/view/View;

    if-eqz v2, :cond_1

    .line 165
    invoke-virtual {v0}, Landroid/view/View;->getParent()Landroid/view/ViewParent;

    move-result-object v0

    check-cast v0, Landroid/view/View;

    goto :goto_0

    :cond_1
    move-object v0, v1

    .line 167
    goto :goto_0

    :cond_2
    move-object v0, v1

    goto :goto_1
.end method


# virtual methods
.method protected dispatchDraw(Landroid/graphics/Canvas;)V
    .locals 7

    .prologue
    const/4 v4, 0x0

    const/4 v1, 0x0

    .line 462
    iget-boolean v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->ro:Z

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->rp:Landroid/graphics/drawable/Drawable;

    if-eqz v0, :cond_0

    .line 464
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->rp:Landroid/graphics/drawable/Drawable;

    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->getRight()I

    move-result v2

    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->getLeft()I

    move-result v3

    sub-int/2addr v2, v3

    iget-object v3, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->rr:Landroid/graphics/Rect;

    iget v3, v3, Landroid/graphics/Rect;->top:I

    invoke-virtual {v0, v4, v4, v2, v3}, Landroid/graphics/drawable/Drawable;->setBounds(IIII)V

    .line 465
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->rp:Landroid/graphics/drawable/Drawable;

    invoke-virtual {v0, p1}, Landroid/graphics/drawable/Drawable;->draw(Landroid/graphics/Canvas;)V

    .line 468
    :cond_0
    iget-boolean v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->rq:Z

    if-eqz v0, :cond_1

    .line 469
    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->getWidth()I

    move-result v0

    int-to-float v3, v0

    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->getHeight()I

    move-result v0

    int-to-float v4, v0

    const/4 v5, 0x0

    const/16 v6, 0x1f

    move-object v0, p1

    move v2, v1

    invoke-virtual/range {v0 .. v6}, Landroid/graphics/Canvas;->saveLayer(FFFFLandroid/graphics/Paint;I)I

    .line 470
    invoke-super {p0, p1}, Landroid/widget/FrameLayout;->dispatchDraw(Landroid/graphics/Canvas;)V

    .line 471
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->ha:Landroid/graphics/Bitmap;

    iget-object v2, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->aP:Landroid/graphics/Paint;

    invoke-virtual {p1, v0, v1, v1, v2}, Landroid/graphics/Canvas;->drawBitmap(Landroid/graphics/Bitmap;FFLandroid/graphics/Paint;)V

    .line 472
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->hb:Landroid/graphics/Bitmap;

    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->getWidth()I

    move-result v2

    iget-object v3, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->hb:Landroid/graphics/Bitmap;

    invoke-virtual {v3}, Landroid/graphics/Bitmap;->getWidth()I

    move-result v3

    sub-int/2addr v2, v3

    int-to-float v2, v2

    iget-object v3, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->aP:Landroid/graphics/Paint;

    invoke-virtual {p1, v0, v2, v1, v3}, Landroid/graphics/Canvas;->drawBitmap(Landroid/graphics/Bitmap;FFLandroid/graphics/Paint;)V

    .line 473
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->hc:Landroid/graphics/Bitmap;

    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->getHeight()I

    move-result v2

    iget-object v3, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->hc:Landroid/graphics/Bitmap;

    invoke-virtual {v3}, Landroid/graphics/Bitmap;->getHeight()I

    move-result v3

    sub-int/2addr v2, v3

    int-to-float v2, v2

    iget-object v3, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->aP:Landroid/graphics/Paint;

    invoke-virtual {p1, v0, v1, v2, v3}, Landroid/graphics/Canvas;->drawBitmap(Landroid/graphics/Bitmap;FFLandroid/graphics/Paint;)V

    .line 474
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->hd:Landroid/graphics/Bitmap;

    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->getWidth()I

    move-result v1

    iget-object v2, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->hd:Landroid/graphics/Bitmap;

    invoke-virtual {v2}, Landroid/graphics/Bitmap;->getWidth()I

    move-result v2

    sub-int/2addr v1, v2

    int-to-float v1, v1

    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->getHeight()I

    move-result v2

    iget-object v3, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->hd:Landroid/graphics/Bitmap;

    invoke-virtual {v3}, Landroid/graphics/Bitmap;->getHeight()I

    move-result v3

    sub-int/2addr v2, v3

    int-to-float v2, v2

    iget-object v3, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->aP:Landroid/graphics/Paint;

    invoke-virtual {p1, v0, v1, v2, v3}, Landroid/graphics/Canvas;->drawBitmap(Landroid/graphics/Bitmap;FFLandroid/graphics/Paint;)V

    .line 475
    invoke-virtual {p1}, Landroid/graphics/Canvas;->restore()V

    .line 479
    :goto_0
    return-void

    .line 477
    :cond_1
    invoke-super {p0, p1}, Landroid/widget/FrameLayout;->dispatchDraw(Landroid/graphics/Canvas;)V

    goto :goto_0
.end method

.method public dispatchKeyEvent(Landroid/view/KeyEvent;)Z
    .locals 3

    .prologue
    const/4 v1, 0x0

    const/4 v0, 0x1

    .line 560
    invoke-super {p0, p1}, Landroid/widget/FrameLayout;->dispatchKeyEvent(Landroid/view/KeyEvent;)Z

    move-result v2

    if-eqz v2, :cond_1

    .line 577
    :cond_0
    :goto_0
    return v0

    .line 565
    :cond_1
    invoke-direct {p0, p1}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->a(Landroid/view/KeyEvent;)Z

    move-result v2

    if-eqz v2, :cond_5

    .line 566
    iget-object v2, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->mActionMode:Landroid/view/ActionMode;

    if-eqz v2, :cond_3

    .line 567
    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->ln:Lcom/miui/internal/widget/ActionBarContextView;

    if-eqz v1, :cond_2

    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->ln:Lcom/miui/internal/widget/ActionBarContextView;

    invoke-virtual {v1}, Lcom/miui/internal/widget/ActionBarContextView;->hideOverflowMenu()Z

    move-result v1

    if-nez v1, :cond_0

    .line 568
    :cond_2
    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->mActionMode:Landroid/view/ActionMode;

    invoke-virtual {v1}, Landroid/view/ActionMode;->finish()V

    .line 569
    const/4 v1, 0x0

    iput-object v1, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->mActionMode:Landroid/view/ActionMode;

    goto :goto_0

    .line 573
    :cond_3
    iget-object v2, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->mActionBarView:Lcom/miui/internal/widget/ActionBarView;

    if-eqz v2, :cond_4

    iget-object v2, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->mActionBarView:Lcom/miui/internal/widget/ActionBarView;

    invoke-virtual {v2}, Lcom/miui/internal/widget/ActionBarView;->hideOverflowMenu()Z

    move-result v2

    if-nez v2, :cond_0

    :cond_4
    move v0, v1

    goto :goto_0

    :cond_5
    move v0, v1

    goto :goto_0
.end method

.method protected fitSystemWindows(Landroid/graphics/Rect;)Z
    .locals 13

    .prologue
    const/4 v5, 0x0

    const/4 v3, 0x1

    .line 193
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->rC:Lmiui/app/OnStatusBarChangeListener;

    if-eqz v0, :cond_0

    .line 194
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->rC:Lmiui/app/OnStatusBarChangeListener;

    iget v1, p1, Landroid/graphics/Rect;->top:I

    invoke-interface {v0, v1}, Lmiui/app/OnStatusBarChangeListener;->onStatusBarHeightChange(I)V

    .line 197
    :cond_0
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->ru:Landroid/graphics/Rect;

    invoke-virtual {v0, p1}, Landroid/graphics/Rect;->set(Landroid/graphics/Rect;)V

    .line 198
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->ru:Landroid/graphics/Rect;

    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->rr:Landroid/graphics/Rect;

    invoke-direct {p0, v0, v1}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->a(Landroid/graphics/Rect;Landroid/graphics/Rect;)V

    .line 201
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->mActionBarTop:Lcom/miui/internal/widget/ActionBarContainer;

    if-eqz v0, :cond_6

    .line 202
    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->isTranslucentStatus()Z

    move-result v0

    if-eqz v0, :cond_1

    .line 203
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->mActionBarTop:Lcom/miui/internal/widget/ActionBarContainer;

    invoke-virtual {v0, p1}, Lcom/miui/internal/widget/ActionBarContainer;->setPendingInsets(Landroid/graphics/Rect;)V

    .line 205
    :cond_1
    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->mActionBarTop:Lcom/miui/internal/widget/ActionBarContainer;

    iget-object v2, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->ru:Landroid/graphics/Rect;

    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->isRootSubDecor()Z

    move-result v0

    if-eqz v0, :cond_4

    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->isTranslucentStatus()Z

    move-result v0

    if-nez v0, :cond_4

    move v4, v3

    :goto_0
    move-object v0, p0

    move v6, v3

    invoke-direct/range {v0 .. v6}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->a(Landroid/view/View;Landroid/graphics/Rect;ZZZZ)Z

    move-result v0

    .line 209
    :goto_1
    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->ri:Lcom/miui/internal/widget/ActionBarContainer;

    if-eqz v1, :cond_2

    .line 210
    iget-object v7, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->ri:Lcom/miui/internal/widget/ActionBarContainer;

    iget-object v8, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->ru:Landroid/graphics/Rect;

    move-object v6, p0

    move v9, v3

    move v10, v5

    move v11, v3

    move v12, v3

    invoke-direct/range {v6 .. v12}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->a(Landroid/view/View;Landroid/graphics/Rect;ZZZZ)Z

    move-result v1

    or-int/2addr v0, v1

    .line 213
    :cond_2
    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->rs:Landroid/graphics/Rect;

    iget-object v2, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->rr:Landroid/graphics/Rect;

    invoke-virtual {v1, v2}, Landroid/graphics/Rect;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_5

    .line 214
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->rs:Landroid/graphics/Rect;

    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->rr:Landroid/graphics/Rect;

    invoke-virtual {v0, v1}, Landroid/graphics/Rect;->set(Landroid/graphics/Rect;)V

    .line 218
    :goto_2
    if-eqz v3, :cond_3

    .line 219
    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->requestLayout()V

    .line 222
    :cond_3
    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->isRootSubDecor()Z

    move-result v0

    return v0

    :cond_4
    move v4, v5

    .line 205
    goto :goto_0

    :cond_5
    move v3, v0

    goto :goto_2

    :cond_6
    move v0, v5

    goto :goto_1
.end method

.method public getActionBar()Lmiui/app/ActionBar;
    .locals 1

    .prologue
    .line 495
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->jc:Lmiui/app/ActionBar;

    return-object v0
.end method

.method public getActionBarView()Lcom/miui/internal/widget/ActionBarView;
    .locals 1

    .prologue
    .line 515
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->mActionBarView:Lcom/miui/internal/widget/ActionBarView;

    return-object v0
.end method

.method protected getBottomInset()I
    .locals 1

    .prologue
    .line 491
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->ri:Lcom/miui/internal/widget/ActionBarContainer;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->ri:Lcom/miui/internal/widget/ActionBarContainer;

    invoke-virtual {v0}, Lcom/miui/internal/widget/ActionBarContainer;->getCollapsedHeight()I

    move-result v0

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public getCallback()Landroid/view/Window$Callback;
    .locals 1

    .prologue
    .line 519
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->rk:Landroid/view/Window$Callback;

    return-object v0
.end method

.method public getContentMaskAnimator(Landroid/view/View$OnClickListener;)Lcom/miui/internal/widget/ActionBarOverlayLayout$ContentMaskAnimator;
    .locals 2

    .prologue
    .line 511
    new-instance v0, Lcom/miui/internal/widget/ActionBarOverlayLayout$ContentMaskAnimator;

    const/4 v1, 0x0

    invoke-direct {v0, p0, p1, v1}, Lcom/miui/internal/widget/ActionBarOverlayLayout$ContentMaskAnimator;-><init>(Lcom/miui/internal/widget/ActionBarOverlayLayout;Landroid/view/View$OnClickListener;Lcom/miui/internal/widget/ActionBarOverlayLayout$1;)V

    return-object v0
.end method

.method public getContentView()Landroid/view/View;
    .locals 1

    .prologue
    .line 507
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->mContentView:Landroid/view/View;

    return-object v0
.end method

.method public isRootSubDecor()Z
    .locals 1

    .prologue
    .line 547
    iget-boolean v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->rm:Z

    return v0
.end method

.method public isTranslucentStatus()Z
    .locals 1

    .prologue
    .line 551
    iget v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->je:I

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method protected onAttachedToWindow()V
    .locals 0

    .prologue
    .line 264
    invoke-super {p0}, Landroid/widget/FrameLayout;->onAttachedToWindow()V

    .line 265
    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->requestFitSystemWindows()V

    .line 266
    return-void
.end method

.method protected onFinishInflate()V
    .locals 0

    .prologue
    .line 276
    invoke-super {p0}, Landroid/widget/FrameLayout;->onFinishInflate()V

    .line 277
    invoke-direct {p0}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->aU()V

    .line 278
    return-void
.end method

.method protected onMeasure(II)V
    .locals 18

    .prologue
    .line 282
    invoke-direct/range {p0 .. p1}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->B(I)I

    move-result v4

    .line 283
    move-object/from16 v0, p0

    move/from16 v1, p2

    invoke-direct {v0, v1}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->C(I)I

    move-result v6

    .line 285
    const/4 v7, 0x0

    .line 286
    const/4 v5, 0x0

    .line 287
    const/4 v3, 0x0

    .line 289
    const/4 v10, 0x0

    .line 290
    const/4 v11, 0x0

    .line 292
    move-object/from16 v0, p0

    iget-object v8, v0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->mContentView:Landroid/view/View;

    .line 293
    move-object/from16 v0, p0

    iget-object v0, v0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->rj:Landroid/view/View;

    move-object/from16 v17, v0

    .line 297
    const/4 v2, 0x0

    move v9, v2

    move v14, v3

    move v15, v5

    move/from16 v16, v7

    :goto_0
    invoke-virtual/range {p0 .. p0}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->getChildCount()I

    move-result v2

    if-ge v9, v2, :cond_1

    .line 298
    move-object/from16 v0, p0

    invoke-virtual {v0, v9}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->getChildAt(I)Landroid/view/View;

    move-result-object v3

    .line 300
    if-eq v3, v8, :cond_f

    move-object/from16 v0, v17

    if-eq v3, v0, :cond_f

    invoke-virtual {v3}, Landroid/view/View;->getVisibility()I

    move-result v2

    const/16 v5, 0x8

    if-ne v2, v5, :cond_0

    move v3, v14

    move v5, v15

    move/from16 v7, v16

    .line 297
    :goto_1
    add-int/lit8 v2, v9, 0x1

    move v9, v2

    move v14, v3

    move v15, v5

    move/from16 v16, v7

    goto :goto_0

    .line 304
    :cond_0
    const/4 v5, 0x0

    const/4 v7, 0x0

    move-object/from16 v2, p0

    invoke-virtual/range {v2 .. v7}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->measureChildWithMargins(Landroid/view/View;IIII)V

    .line 305
    invoke-virtual {v3}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v2

    check-cast v2, Landroid/widget/FrameLayout$LayoutParams;

    .line 306
    invoke-virtual {v3}, Landroid/view/View;->getMeasuredWidth()I

    move-result v5

    iget v7, v2, Landroid/widget/FrameLayout$LayoutParams;->leftMargin:I

    add-int/2addr v5, v7

    iget v7, v2, Landroid/widget/FrameLayout$LayoutParams;->rightMargin:I

    add-int/2addr v5, v7

    invoke-static {v15, v5}, Ljava/lang/Math;->max(II)I

    move-result v15

    .line 307
    invoke-virtual {v3}, Landroid/view/View;->getMeasuredHeight()I

    move-result v5

    iget v7, v2, Landroid/widget/FrameLayout$LayoutParams;->topMargin:I

    add-int/2addr v5, v7

    iget v2, v2, Landroid/widget/FrameLayout$LayoutParams;->bottomMargin:I

    add-int/2addr v2, v5

    move/from16 v0, v16

    invoke-static {v0, v2}, Ljava/lang/Math;->max(II)I

    move-result v16

    .line 308
    invoke-virtual {v3}, Landroid/view/View;->getMeasuredState()I

    move-result v2

    invoke-static {v14, v2}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->combineMeasuredStates(II)I

    move-result v14

    move v3, v14

    move v5, v15

    move/from16 v7, v16

    goto :goto_1

    .line 311
    :cond_1
    move-object/from16 v0, p0

    iget-object v2, v0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->mActionBarTop:Lcom/miui/internal/widget/ActionBarContainer;

    if-eqz v2, :cond_e

    move-object/from16 v0, p0

    iget-object v2, v0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->mActionBarTop:Lcom/miui/internal/widget/ActionBarContainer;

    invoke-virtual {v2}, Lcom/miui/internal/widget/ActionBarContainer;->getVisibility()I

    move-result v2

    if-nez v2, :cond_e

    .line 313
    move-object/from16 v0, p0

    iget-object v2, v0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->mActionBarTop:Lcom/miui/internal/widget/ActionBarContainer;

    invoke-virtual {v2}, Lcom/miui/internal/widget/ActionBarContainer;->getMeasuredHeight()I

    move-result v2

    move v3, v2

    .line 316
    :goto_2
    move-object/from16 v0, p0

    iget-object v2, v0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->mActionBarView:Lcom/miui/internal/widget/ActionBarView;

    if-eqz v2, :cond_d

    move-object/from16 v0, p0

    iget-object v2, v0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->mActionBarView:Lcom/miui/internal/widget/ActionBarView;

    invoke-virtual {v2}, Lcom/miui/internal/widget/ActionBarView;->isSplitActionBar()Z

    move-result v2

    if-eqz v2, :cond_d

    .line 318
    move-object/from16 v0, p0

    iget-object v2, v0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->ri:Lcom/miui/internal/widget/ActionBarContainer;

    if-eqz v2, :cond_d

    .line 319
    invoke-virtual/range {p0 .. p0}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->getBottomInset()I

    move-result v2

    move v5, v2

    .line 323
    :goto_3
    invoke-virtual/range {p0 .. p0}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->isTranslucentStatus()Z

    move-result v2

    if-eqz v2, :cond_2

    move-object/from16 v0, p0

    iget-boolean v2, v0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->ro:Z

    if-eqz v2, :cond_2

    .line 324
    move-object/from16 v0, p0

    iget-object v2, v0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->rp:Landroid/graphics/drawable/Drawable;

    if-eqz v2, :cond_8

    .line 326
    move-object/from16 v0, p0

    iget-object v2, v0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->rp:Landroid/graphics/drawable/Drawable;

    const/4 v7, 0x0

    const/4 v9, 0x0

    invoke-virtual/range {p0 .. p0}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->getRight()I

    move-result v10

    invoke-virtual/range {p0 .. p0}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->getLeft()I

    move-result v11

    sub-int/2addr v10, v11

    move-object/from16 v0, p0

    iget-object v11, v0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->rr:Landroid/graphics/Rect;

    iget v11, v11, Landroid/graphics/Rect;->top:I

    invoke-virtual {v2, v7, v9, v10, v11}, Landroid/graphics/drawable/Drawable;->setBounds(IIII)V

    .line 341
    :cond_2
    :goto_4
    move-object/from16 v0, p0

    iget-object v2, v0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->rw:Landroid/graphics/Rect;

    move-object/from16 v0, p0

    iget-object v7, v0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->ru:Landroid/graphics/Rect;

    invoke-virtual {v2, v7}, Landroid/graphics/Rect;->set(Landroid/graphics/Rect;)V

    .line 342
    move-object/from16 v0, p0

    iget-object v2, v0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->rt:Landroid/graphics/Rect;

    move-object/from16 v0, p0

    iget-object v7, v0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->rr:Landroid/graphics/Rect;

    invoke-virtual {v2, v7}, Landroid/graphics/Rect;->set(Landroid/graphics/Rect;)V

    .line 343
    invoke-virtual/range {p0 .. p0}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->isTranslucentStatus()Z

    move-result v2

    if-eqz v2, :cond_3

    if-lez v3, :cond_3

    .line 345
    move-object/from16 v0, p0

    iget-object v2, v0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->rt:Landroid/graphics/Rect;

    const/4 v7, 0x0

    iput v7, v2, Landroid/graphics/Rect;->top:I

    .line 347
    :cond_3
    move-object/from16 v0, p0

    iget-boolean v2, v0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->rl:Z

    if-nez v2, :cond_a

    .line 348
    move-object/from16 v0, p0

    iget-object v2, v0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->rt:Landroid/graphics/Rect;

    iget v7, v2, Landroid/graphics/Rect;->top:I

    add-int/2addr v3, v7

    iput v3, v2, Landroid/graphics/Rect;->top:I

    .line 349
    move-object/from16 v0, p0

    iget-object v2, v0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->rt:Landroid/graphics/Rect;

    iget v3, v2, Landroid/graphics/Rect;->bottom:I

    add-int/2addr v3, v5

    iput v3, v2, Landroid/graphics/Rect;->bottom:I

    .line 360
    :goto_5
    move-object/from16 v0, p0

    iget-object v9, v0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->rt:Landroid/graphics/Rect;

    const/4 v10, 0x1

    const/4 v11, 0x1

    const/4 v12, 0x1

    const/4 v13, 0x1

    move-object/from16 v7, p0

    invoke-direct/range {v7 .. v13}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->a(Landroid/view/View;Landroid/graphics/Rect;ZZZZ)Z

    .line 362
    move-object/from16 v0, p0

    iget-object v2, v0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->rv:Landroid/graphics/Rect;

    move-object/from16 v0, p0

    iget-object v3, v0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->rw:Landroid/graphics/Rect;

    invoke-virtual {v2, v3}, Landroid/graphics/Rect;->equals(Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_4

    move-object/from16 v0, p0

    iget-boolean v2, v0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->rn:Z

    if-eqz v2, :cond_5

    .line 363
    :cond_4
    move-object/from16 v0, p0

    iget-object v2, v0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->rv:Landroid/graphics/Rect;

    move-object/from16 v0, p0

    iget-object v3, v0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->rw:Landroid/graphics/Rect;

    invoke-virtual {v2, v3}, Landroid/graphics/Rect;->set(Landroid/graphics/Rect;)V

    .line 364
    move-object/from16 v0, p0

    iget-object v2, v0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->rw:Landroid/graphics/Rect;

    move-object/from16 v0, p0

    invoke-super {v0, v2}, Landroid/widget/FrameLayout;->fitSystemWindows(Landroid/graphics/Rect;)Z

    .line 365
    const/4 v2, 0x0

    move-object/from16 v0, p0

    iput-boolean v2, v0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->rn:Z

    .line 368
    :cond_5
    const/4 v5, 0x0

    const/4 v7, 0x0

    move-object/from16 v2, p0

    move-object v3, v8

    invoke-virtual/range {v2 .. v7}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->measureChildWithMargins(Landroid/view/View;IIII)V

    .line 369
    invoke-virtual {v8}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v2

    check-cast v2, Landroid/widget/FrameLayout$LayoutParams;

    .line 370
    invoke-virtual {v8}, Landroid/view/View;->getMeasuredWidth()I

    move-result v3

    iget v5, v2, Landroid/widget/FrameLayout$LayoutParams;->leftMargin:I

    add-int/2addr v3, v5

    iget v5, v2, Landroid/widget/FrameLayout$LayoutParams;->rightMargin:I

    add-int/2addr v3, v5

    invoke-static {v15, v3}, Ljava/lang/Math;->max(II)I

    move-result v9

    .line 371
    invoke-virtual {v8}, Landroid/view/View;->getMeasuredHeight()I

    move-result v3

    iget v5, v2, Landroid/widget/FrameLayout$LayoutParams;->topMargin:I

    add-int/2addr v3, v5

    iget v2, v2, Landroid/widget/FrameLayout$LayoutParams;->bottomMargin:I

    add-int/2addr v2, v3

    move/from16 v0, v16

    invoke-static {v0, v2}, Ljava/lang/Math;->max(II)I

    move-result v10

    .line 372
    invoke-virtual {v8}, Landroid/view/View;->getMeasuredState()I

    move-result v2

    invoke-static {v14, v2}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->combineMeasuredStates(II)I

    move-result v8

    .line 375
    if-eqz v17, :cond_6

    invoke-virtual/range {v17 .. v17}, Landroid/view/View;->getVisibility()I

    move-result v2

    if-nez v2, :cond_6

    .line 376
    const/4 v5, 0x0

    const/4 v7, 0x0

    move-object/from16 v2, p0

    move-object/from16 v3, v17

    invoke-virtual/range {v2 .. v7}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->measureChildWithMargins(Landroid/view/View;IIII)V

    .line 380
    :cond_6
    invoke-virtual/range {p0 .. p0}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->getPaddingLeft()I

    move-result v2

    invoke-virtual/range {p0 .. p0}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->getPaddingRight()I

    move-result v3

    add-int/2addr v2, v3

    add-int/2addr v2, v9

    .line 381
    invoke-virtual/range {p0 .. p0}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->getPaddingTop()I

    move-result v3

    invoke-virtual/range {p0 .. p0}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->getPaddingBottom()I

    move-result v5

    add-int/2addr v3, v5

    add-int/2addr v3, v10

    .line 384
    invoke-virtual/range {p0 .. p0}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->getSuggestedMinimumHeight()I

    move-result v5

    invoke-static {v3, v5}, Ljava/lang/Math;->max(II)I

    move-result v3

    .line 385
    invoke-virtual/range {p0 .. p0}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->getSuggestedMinimumWidth()I

    move-result v5

    invoke-static {v2, v5}, Ljava/lang/Math;->max(II)I

    move-result v2

    .line 388
    invoke-static {v2, v4, v8}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->resolveSizeAndState(III)I

    move-result v2

    shl-int/lit8 v4, v8, 0x10

    invoke-static {v3, v6, v4}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->resolveSizeAndState(III)I

    move-result v3

    move-object/from16 v0, p0

    invoke-virtual {v0, v2, v3}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->setMeasuredDimension(II)V

    .line 391
    invoke-direct/range {p0 .. p0}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->bw()Z

    move-result v2

    move-object/from16 v0, p0

    iput-boolean v2, v0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->rq:Z

    .line 392
    move-object/from16 v0, p0

    iget-boolean v2, v0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->rq:Z

    if-eqz v2, :cond_7

    move-object/from16 v0, p0

    iget-object v2, v0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->aP:Landroid/graphics/Paint;

    if-nez v2, :cond_7

    .line 393
    new-instance v2, Landroid/graphics/Paint;

    invoke-direct {v2}, Landroid/graphics/Paint;-><init>()V

    move-object/from16 v0, p0

    iput-object v2, v0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->aP:Landroid/graphics/Paint;

    .line 394
    move-object/from16 v0, p0

    iget-object v2, v0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->aP:Landroid/graphics/Paint;

    const/4 v3, 0x1

    invoke-virtual {v2, v3}, Landroid/graphics/Paint;->setAntiAlias(Z)V

    .line 395
    move-object/from16 v0, p0

    iget-object v2, v0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->aP:Landroid/graphics/Paint;

    new-instance v3, Landroid/graphics/PorterDuffXfermode;

    sget-object v4, Landroid/graphics/PorterDuff$Mode;->DST_OUT:Landroid/graphics/PorterDuff$Mode;

    invoke-direct {v3, v4}, Landroid/graphics/PorterDuffXfermode;-><init>(Landroid/graphics/PorterDuff$Mode;)V

    invoke-virtual {v2, v3}, Landroid/graphics/Paint;->setXfermode(Landroid/graphics/Xfermode;)Landroid/graphics/Xfermode;

    .line 396
    invoke-virtual/range {p0 .. p0}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->getResources()Landroid/content/res/Resources;

    move-result-object v2

    .line 397
    sget v3, Lcom/miui/internal/R$drawable;->popup_mask_1:I

    invoke-static {v2, v3}, Landroid/graphics/BitmapFactory;->decodeResource(Landroid/content/res/Resources;I)Landroid/graphics/Bitmap;

    move-result-object v3

    move-object/from16 v0, p0

    iput-object v3, v0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->ha:Landroid/graphics/Bitmap;

    .line 398
    sget v3, Lcom/miui/internal/R$drawable;->popup_mask_2:I

    invoke-static {v2, v3}, Landroid/graphics/BitmapFactory;->decodeResource(Landroid/content/res/Resources;I)Landroid/graphics/Bitmap;

    move-result-object v3

    move-object/from16 v0, p0

    iput-object v3, v0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->hb:Landroid/graphics/Bitmap;

    .line 399
    sget v3, Lcom/miui/internal/R$drawable;->popup_mask_3:I

    invoke-static {v2, v3}, Landroid/graphics/BitmapFactory;->decodeResource(Landroid/content/res/Resources;I)Landroid/graphics/Bitmap;

    move-result-object v3

    move-object/from16 v0, p0

    iput-object v3, v0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->hc:Landroid/graphics/Bitmap;

    .line 400
    sget v3, Lcom/miui/internal/R$drawable;->popup_mask_4:I

    invoke-static {v2, v3}, Landroid/graphics/BitmapFactory;->decodeResource(Landroid/content/res/Resources;I)Landroid/graphics/Bitmap;

    move-result-object v2

    move-object/from16 v0, p0

    iput-object v2, v0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->hd:Landroid/graphics/Bitmap;

    .line 403
    :cond_7
    return-void

    .line 328
    :cond_8
    const v2, 0x1020002

    move-object/from16 v0, p0

    invoke-virtual {v0, v2}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->findViewById(I)Landroid/view/View;

    move-result-object v2

    check-cast v2, Landroid/view/ViewGroup;

    .line 329
    if-eqz v2, :cond_2

    invoke-virtual {v2}, Landroid/view/ViewGroup;->getChildCount()I

    move-result v7

    const/4 v9, 0x1

    if-ne v7, v9, :cond_2

    .line 330
    const/4 v7, 0x0

    invoke-virtual {v2, v7}, Landroid/view/ViewGroup;->getChildAt(I)Landroid/view/View;

    move-result-object v2

    .line 331
    if-gtz v3, :cond_9

    .line 332
    invoke-virtual {v2}, Landroid/view/View;->getPaddingLeft()I

    move-result v7

    move-object/from16 v0, p0

    iget-object v9, v0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->ru:Landroid/graphics/Rect;

    iget v9, v9, Landroid/graphics/Rect;->top:I

    invoke-virtual {v2}, Landroid/view/View;->getPaddingRight()I

    move-result v10

    invoke-virtual {v2}, Landroid/view/View;->getPaddingBottom()I

    move-result v11

    invoke-virtual {v2, v7, v9, v10, v11}, Landroid/view/View;->setPadding(IIII)V

    goto/16 :goto_4

    .line 334
    :cond_9
    invoke-virtual {v2}, Landroid/view/View;->getPaddingLeft()I

    move-result v7

    const/4 v9, 0x0

    invoke-virtual {v2}, Landroid/view/View;->getPaddingRight()I

    move-result v10

    invoke-virtual {v2}, Landroid/view/View;->getPaddingBottom()I

    move-result v11

    invoke-virtual {v2, v7, v9, v10, v11}, Landroid/view/View;->setPadding(IIII)V

    goto/16 :goto_4

    .line 351
    :cond_a
    invoke-virtual/range {p0 .. p0}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->isTranslucentStatus()Z

    move-result v2

    if-eqz v2, :cond_c

    .line 352
    if-lez v3, :cond_b

    .line 353
    move-object/from16 v0, p0

    iget-object v2, v0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->rw:Landroid/graphics/Rect;

    iput v3, v2, Landroid/graphics/Rect;->top:I

    .line 358
    :cond_b
    :goto_6
    move-object/from16 v0, p0

    iget-object v2, v0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->rw:Landroid/graphics/Rect;

    iget v3, v2, Landroid/graphics/Rect;->bottom:I

    add-int/2addr v3, v5

    iput v3, v2, Landroid/graphics/Rect;->bottom:I

    goto/16 :goto_5

    .line 356
    :cond_c
    move-object/from16 v0, p0

    iget-object v2, v0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->rw:Landroid/graphics/Rect;

    iget v7, v2, Landroid/graphics/Rect;->top:I

    add-int/2addr v3, v7

    iput v3, v2, Landroid/graphics/Rect;->top:I

    goto :goto_6

    :cond_d
    move v5, v11

    goto/16 :goto_3

    :cond_e
    move v3, v10

    goto/16 :goto_2

    :cond_f
    move v3, v14

    move v5, v15

    move/from16 v7, v16

    goto/16 :goto_1
.end method

.method public requestFitSystemWindows()V
    .locals 1

    .prologue
    .line 270
    invoke-super {p0}, Landroid/widget/FrameLayout;->requestFitSystemWindows()V

    .line 271
    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->rn:Z

    .line 272
    return-void
.end method

.method public setActionBar(Lmiui/app/ActionBar;)V
    .locals 0

    .prologue
    .line 499
    iput-object p1, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->jc:Lmiui/app/ActionBar;

    .line 500
    return-void
.end method

.method public setCallback(Landroid/view/Window$Callback;)V
    .locals 0

    .prologue
    .line 523
    iput-object p1, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->rk:Landroid/view/Window$Callback;

    .line 524
    return-void
.end method

.method public setOnStatusBarChangeListener(Lmiui/app/OnStatusBarChangeListener;)V
    .locals 0

    .prologue
    .line 804
    iput-object p1, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->rC:Lmiui/app/OnStatusBarChangeListener;

    .line 805
    return-void
.end method

.method public setOverlayMode(Z)V
    .locals 0

    .prologue
    .line 503
    iput-boolean p1, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->rl:Z

    .line 504
    return-void
.end method

.method public setRootSubDecor(Z)V
    .locals 0

    .prologue
    .line 555
    iput-boolean p1, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->rm:Z

    .line 556
    return-void
.end method

.method public setTranslucentStatus(I)V
    .locals 1

    .prologue
    .line 527
    iget v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->je:I

    if-eq v0, p1, :cond_0

    .line 528
    iput p1, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->je:I

    .line 529
    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->requestFitSystemWindows()V

    .line 531
    :cond_0
    return-void
.end method

.method public showContextMenuForChild(Landroid/view/View;)Z
    .locals 2

    .prologue
    .line 175
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->rz:Lcom/miui/internal/view/menu/ContextMenuBuilder;

    if-nez v0, :cond_0

    .line 176
    new-instance v0, Lcom/miui/internal/view/menu/ContextMenuBuilder;

    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->getContext()Landroid/content/Context;

    move-result-object v1

    invoke-direct {v0, v1}, Lcom/miui/internal/view/menu/ContextMenuBuilder;-><init>(Landroid/content/Context;)V

    iput-object v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->rz:Lcom/miui/internal/view/menu/ContextMenuBuilder;

    .line 177
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->rz:Lcom/miui/internal/view/menu/ContextMenuBuilder;

    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->rB:Lcom/miui/internal/widget/ActionBarOverlayLayout$a;

    invoke-virtual {v0, v1}, Lcom/miui/internal/view/menu/ContextMenuBuilder;->setCallback(Lcom/miui/internal/view/menu/MenuBuilder$Callback;)V

    .line 182
    :goto_0
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->rz:Lcom/miui/internal/view/menu/ContextMenuBuilder;

    invoke-virtual {p1}, Landroid/view/View;->getWindowToken()Landroid/os/IBinder;

    move-result-object v1

    invoke-virtual {v0, p1, v1}, Lcom/miui/internal/view/menu/ContextMenuBuilder;->show(Landroid/view/View;Landroid/os/IBinder;)Lcom/miui/internal/view/menu/MenuDialogHelper;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->rA:Lcom/miui/internal/view/menu/MenuDialogHelper;

    .line 183
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->rA:Lcom/miui/internal/view/menu/MenuDialogHelper;

    if-eqz v0, :cond_1

    .line 184
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->rA:Lcom/miui/internal/view/menu/MenuDialogHelper;

    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->rB:Lcom/miui/internal/widget/ActionBarOverlayLayout$a;

    invoke-virtual {v0, v1}, Lcom/miui/internal/view/menu/MenuDialogHelper;->setPresenterCallback(Lcom/miui/internal/view/menu/MenuPresenter$Callback;)V

    .line 185
    const/4 v0, 0x1

    .line 187
    :goto_1
    return v0

    .line 179
    :cond_0
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->rz:Lcom/miui/internal/view/menu/ContextMenuBuilder;

    invoke-virtual {v0}, Lcom/miui/internal/view/menu/ContextMenuBuilder;->clear()V

    goto :goto_0

    .line 187
    :cond_1
    invoke-super {p0, p1}, Landroid/widget/FrameLayout;->showContextMenuForChild(Landroid/view/View;)Z

    move-result v0

    goto :goto_1
.end method

.method public startActionMode(Landroid/view/ActionMode$Callback;)Landroid/view/ActionMode;
    .locals 2

    .prologue
    const/4 v0, 0x0

    .line 591
    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->mActionMode:Landroid/view/ActionMode;

    if-eqz v1, :cond_0

    .line 592
    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->mActionMode:Landroid/view/ActionMode;

    invoke-virtual {v1}, Landroid/view/ActionMode;->finish()V

    .line 594
    :cond_0
    iput-object v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->mActionMode:Landroid/view/ActionMode;

    .line 597
    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->getCallback()Landroid/view/Window$Callback;

    move-result-object v1

    if-eqz v1, :cond_1

    .line 598
    invoke-direct {p0, p1}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->a(Landroid/view/ActionMode$Callback;)Lcom/miui/internal/widget/ActionBarOverlayLayout$b;

    move-result-object v0

    .line 599
    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->getCallback()Landroid/view/Window$Callback;

    move-result-object v1

    invoke-interface {v1, v0}, Landroid/view/Window$Callback;->onWindowStartingActionMode(Landroid/view/ActionMode$Callback;)Landroid/view/ActionMode;

    move-result-object v0

    .line 602
    :cond_1
    if-eqz v0, :cond_2

    .line 603
    iput-object v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->mActionMode:Landroid/view/ActionMode;

    .line 606
    :cond_2
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->mActionMode:Landroid/view/ActionMode;

    if-eqz v0, :cond_3

    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->getCallback()Landroid/view/Window$Callback;

    move-result-object v0

    if-eqz v0, :cond_3

    .line 607
    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->getCallback()Landroid/view/Window$Callback;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->mActionMode:Landroid/view/ActionMode;

    invoke-interface {v0, v1}, Landroid/view/Window$Callback;->onActionModeStarted(Landroid/view/ActionMode;)V

    .line 610
    :cond_3
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->mActionMode:Landroid/view/ActionMode;

    return-object v0
.end method

.method public startActionMode(Landroid/view/View;Landroid/view/ActionMode$Callback;)Landroid/view/ActionMode;
    .locals 1

    .prologue
    .line 614
    instance-of v0, p1, Lcom/miui/internal/widget/ActionBarOverlayLayout;

    if-eqz v0, :cond_1

    .line 616
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->mActionMode:Landroid/view/ActionMode;

    if-eqz v0, :cond_0

    .line 617
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->mActionMode:Landroid/view/ActionMode;

    invoke-virtual {v0}, Landroid/view/ActionMode;->finish()V

    .line 620
    :cond_0
    invoke-direct {p0, p2}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->a(Landroid/view/ActionMode$Callback;)Lcom/miui/internal/widget/ActionBarOverlayLayout$b;

    move-result-object v0

    .line 621
    invoke-virtual {p1, v0}, Landroid/view/View;->startActionMode(Landroid/view/ActionMode$Callback;)Landroid/view/ActionMode;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->mActionMode:Landroid/view/ActionMode;

    .line 622
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarOverlayLayout;->mActionMode:Landroid/view/ActionMode;

    .line 624
    :goto_0
    return-object v0

    :cond_1
    invoke-virtual {p0, p2}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->startActionMode(Landroid/view/ActionMode$Callback;)Landroid/view/ActionMode;

    move-result-object v0

    goto :goto_0
.end method

.method public startActionModeForChild(Landroid/view/View;Landroid/view/ActionMode$Callback;)Landroid/view/ActionMode;
    .locals 1

    .prologue
    .line 586
    invoke-virtual {p0, p1, p2}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->startActionMode(Landroid/view/View;Landroid/view/ActionMode$Callback;)Landroid/view/ActionMode;

    move-result-object v0

    return-object v0
.end method
