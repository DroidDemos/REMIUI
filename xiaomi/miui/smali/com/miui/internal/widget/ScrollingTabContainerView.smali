.class public Lcom/miui/internal/widget/ScrollingTabContainerView;
.super Landroid/widget/HorizontalScrollView;
.source "SourceFile"

# interfaces
.implements Landroid/widget/AdapterView$OnItemClickListener;
.implements Lmiui/app/ActionBar$FragmentViewPagerChangeListener;


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/internal/widget/ScrollingTabContainerView$b;,
        Lcom/miui/internal/widget/ScrollingTabContainerView$a;,
        Lcom/miui/internal/widget/ScrollingTabContainerView$TabView;
    }
.end annotation


# static fields
.field private static final ANIMATION_DURATION:I = 0x12c


# instance fields
.field aE:Ljava/lang/Runnable;

.field aF:I

.field aG:I

.field private aH:Lcom/miui/internal/widget/ScrollingTabContainerView$b;

.field private aI:Landroid/widget/LinearLayout;

.field private aJ:Landroid/widget/Spinner;

.field private aK:Z

.field private aL:I

.field private aM:Landroid/graphics/Bitmap;

.field private aN:Z

.field private aO:F

.field private aP:Landroid/graphics/Paint;

.field private aQ:Z

.field private aR:Landroid/animation/ObjectAnimator;

.field private mContentHeight:I

.field private final mInflater:Landroid/view/LayoutInflater;


# direct methods
.method public constructor <init>(Landroid/content/Context;)V
    .locals 5

    .prologue
    const/4 v4, 0x0

    .line 82
    invoke-direct {p0, p1}, Landroid/widget/HorizontalScrollView;-><init>(Landroid/content/Context;)V

    .line 75
    new-instance v0, Landroid/graphics/Paint;

    invoke-direct {v0}, Landroid/graphics/Paint;-><init>()V

    iput-object v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aP:Landroid/graphics/Paint;

    .line 83
    invoke-static {p1}, Landroid/view/LayoutInflater;->from(Landroid/content/Context;)Landroid/view/LayoutInflater;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->mInflater:Landroid/view/LayoutInflater;

    .line 85
    const/4 v0, 0x0

    sget-object v1, Lmiui/R$styleable;->ActionBar:[I

    const v2, 0x10102ce

    invoke-virtual {p1, v0, v1, v2, v4}, Landroid/content/Context;->obtainStyledAttributes(Landroid/util/AttributeSet;[III)Landroid/content/res/TypedArray;

    move-result-object v0

    .line 88
    const/16 v1, 0x11

    invoke-virtual {v0, v1}, Landroid/content/res/TypedArray;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v1

    .line 89
    const/16 v2, 0x12

    const/4 v3, 0x1

    invoke-virtual {v0, v2, v3}, Landroid/content/res/TypedArray;->getBoolean(IZ)Z

    move-result v2

    iput-boolean v2, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aN:Z

    .line 90
    invoke-direct {p0, v1}, Lcom/miui/internal/widget/ScrollingTabContainerView;->a(Landroid/graphics/drawable/Drawable;)Landroid/graphics/Bitmap;

    move-result-object v1

    iput-object v1, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aM:Landroid/graphics/Bitmap;

    .line 91
    invoke-virtual {v0}, Landroid/content/res/TypedArray;->recycle()V

    .line 93
    iget-boolean v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aN:Z

    if-eqz v0, :cond_0

    .line 94
    iget-object v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aP:Landroid/graphics/Paint;

    new-instance v1, Landroid/graphics/PorterDuffXfermode;

    sget-object v2, Landroid/graphics/PorterDuff$Mode;->CLEAR:Landroid/graphics/PorterDuff$Mode;

    invoke-direct {v1, v2}, Landroid/graphics/PorterDuffXfermode;-><init>(Landroid/graphics/PorterDuff$Mode;)V

    invoke-virtual {v0, v1}, Landroid/graphics/Paint;->setXfermode(Landroid/graphics/Xfermode;)Landroid/graphics/Xfermode;

    .line 97
    :cond_0
    invoke-virtual {p0, v4}, Lcom/miui/internal/widget/ScrollingTabContainerView;->setHorizontalScrollBarEnabled(Z)V

    .line 99
    invoke-static {p1}, Lcom/miui/internal/view/ActionBarPolicy;->get(Landroid/content/Context;)Lcom/miui/internal/view/ActionBarPolicy;

    move-result-object v0

    .line 100
    invoke-virtual {v0}, Lcom/miui/internal/view/ActionBarPolicy;->getTabContainerHeight()I

    move-result v1

    invoke-virtual {p0, v1}, Lcom/miui/internal/widget/ScrollingTabContainerView;->setContentHeight(I)V

    .line 101
    invoke-virtual {v0}, Lcom/miui/internal/view/ActionBarPolicy;->getStackedTabMaxWidth()I

    move-result v0

    iput v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aG:I

    .line 103
    iget-object v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->mInflater:Landroid/view/LayoutInflater;

    sget v1, Lcom/miui/internal/R$layout;->action_bar_tabbar:I

    invoke-virtual {v0, v1, p0, v4}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;Z)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/LinearLayout;

    iput-object v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aI:Landroid/widget/LinearLayout;

    .line 104
    iget-object v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aI:Landroid/widget/LinearLayout;

    new-instance v1, Landroid/widget/FrameLayout$LayoutParams;

    const/4 v2, -0x2

    const/4 v3, -0x1

    invoke-direct {v1, v2, v3}, Landroid/widget/FrameLayout$LayoutParams;-><init>(II)V

    invoke-virtual {p0, v0, v1}, Lcom/miui/internal/widget/ScrollingTabContainerView;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 106
    return-void
.end method

.method private a(Landroid/graphics/drawable/Drawable;)Landroid/graphics/Bitmap;
    .locals 5

    .prologue
    const/4 v4, 0x0

    .line 113
    if-nez p1, :cond_0

    .line 114
    const/4 v0, 0x0

    .line 128
    :goto_0
    return-object v0

    .line 118
    :cond_0
    iget-boolean v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aN:Z

    if-eqz v0, :cond_1

    .line 119
    invoke-virtual {p1}, Landroid/graphics/drawable/Drawable;->getIntrinsicWidth()I

    move-result v0

    invoke-virtual {p1}, Landroid/graphics/drawable/Drawable;->getIntrinsicHeight()I

    move-result v1

    sget-object v2, Landroid/graphics/Bitmap$Config;->ALPHA_8:Landroid/graphics/Bitmap$Config;

    invoke-static {v0, v1, v2}, Landroid/graphics/Bitmap;->createBitmap(IILandroid/graphics/Bitmap$Config;)Landroid/graphics/Bitmap;

    move-result-object v0

    .line 125
    :goto_1
    new-instance v1, Landroid/graphics/Canvas;

    invoke-direct {v1, v0}, Landroid/graphics/Canvas;-><init>(Landroid/graphics/Bitmap;)V

    .line 126
    invoke-virtual {p1}, Landroid/graphics/drawable/Drawable;->getIntrinsicWidth()I

    move-result v2

    invoke-virtual {p1}, Landroid/graphics/drawable/Drawable;->getIntrinsicHeight()I

    move-result v3

    invoke-virtual {p1, v4, v4, v2, v3}, Landroid/graphics/drawable/Drawable;->setBounds(IIII)V

    .line 127
    invoke-virtual {p1, v1}, Landroid/graphics/drawable/Drawable;->draw(Landroid/graphics/Canvas;)V

    goto :goto_0

    .line 122
    :cond_1
    invoke-virtual {p1}, Landroid/graphics/drawable/Drawable;->getIntrinsicWidth()I

    move-result v0

    invoke-virtual {p1}, Landroid/graphics/drawable/Drawable;->getIntrinsicHeight()I

    move-result v1

    sget-object v2, Landroid/graphics/Bitmap$Config;->ARGB_8888:Landroid/graphics/Bitmap$Config;

    invoke-static {v0, v1, v2}, Landroid/graphics/Bitmap;->createBitmap(IILandroid/graphics/Bitmap$Config;)Landroid/graphics/Bitmap;

    move-result-object v0

    goto :goto_1
.end method

.method static synthetic a(Lcom/miui/internal/widget/ScrollingTabContainerView;)Landroid/widget/LinearLayout;
    .locals 1

    .prologue
    .line 44
    iget-object v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aI:Landroid/widget/LinearLayout;

    return-object v0
.end method

.method private a(Landroid/app/ActionBar$Tab;Z)Lcom/miui/internal/widget/ScrollingTabContainerView$TabView;
    .locals 5

    .prologue
    const/4 v4, 0x0

    .line 367
    iget-object v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->mInflater:Landroid/view/LayoutInflater;

    sget v1, Lcom/miui/internal/R$layout;->action_bar_tab:I

    iget-object v2, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aI:Landroid/widget/LinearLayout;

    const/4 v3, 0x0

    invoke-virtual {v0, v1, v2, v3}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;Z)Landroid/view/View;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/widget/ScrollingTabContainerView$TabView;

    .line 369
    invoke-virtual {v0, p0, p1, p2}, Lcom/miui/internal/widget/ScrollingTabContainerView$TabView;->a(Lcom/miui/internal/widget/ScrollingTabContainerView;Landroid/app/ActionBar$Tab;Z)V

    .line 371
    if-eqz p2, :cond_0

    .line 372
    invoke-virtual {v0, v4}, Lcom/miui/internal/widget/ScrollingTabContainerView$TabView;->setBackground(Landroid/graphics/drawable/Drawable;)V

    .line 373
    new-instance v1, Landroid/widget/AbsListView$LayoutParams;

    const/4 v2, -0x1

    iget v3, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->mContentHeight:I

    invoke-direct {v1, v2, v3}, Landroid/widget/AbsListView$LayoutParams;-><init>(II)V

    invoke-virtual {v0, v1}, Lcom/miui/internal/widget/ScrollingTabContainerView$TabView;->setLayoutParams(Landroid/view/ViewGroup$LayoutParams;)V

    .line 383
    :goto_0
    return-object v0

    .line 376
    :cond_0
    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Lcom/miui/internal/widget/ScrollingTabContainerView$TabView;->setFocusable(Z)V

    .line 378
    iget-object v1, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aH:Lcom/miui/internal/widget/ScrollingTabContainerView$b;

    if-nez v1, :cond_1

    .line 379
    new-instance v1, Lcom/miui/internal/widget/ScrollingTabContainerView$b;

    invoke-direct {v1, p0, v4}, Lcom/miui/internal/widget/ScrollingTabContainerView$b;-><init>(Lcom/miui/internal/widget/ScrollingTabContainerView;Lcom/miui/internal/widget/ScrollingTabContainerView$1;)V

    iput-object v1, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aH:Lcom/miui/internal/widget/ScrollingTabContainerView$b;

    .line 381
    :cond_1
    iget-object v1, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aH:Lcom/miui/internal/widget/ScrollingTabContainerView$b;

    invoke-virtual {v0, v1}, Lcom/miui/internal/widget/ScrollingTabContainerView$TabView;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    goto :goto_0
.end method

.method private a(Landroid/view/View;)V
    .locals 4

    .prologue
    .line 334
    iget-object v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aM:Landroid/graphics/Bitmap;

    if-eqz v0, :cond_0

    if-eqz p1, :cond_0

    invoke-virtual {p1}, Landroid/view/View;->getWidth()I

    move-result v0

    if-lez v0, :cond_0

    iget-boolean v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aQ:Z

    if-eqz v0, :cond_1

    .line 347
    :cond_0
    :goto_0
    return-void

    .line 338
    :cond_1
    iget-object v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aR:Landroid/animation/ObjectAnimator;

    if-eqz v0, :cond_2

    .line 339
    iget-object v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aR:Landroid/animation/ObjectAnimator;

    invoke-virtual {v0}, Landroid/animation/ObjectAnimator;->cancel()V

    .line 340
    const/4 v0, 0x0

    iput-object v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aR:Landroid/animation/ObjectAnimator;

    .line 342
    :cond_2
    invoke-virtual {p1}, Landroid/view/View;->getLeft()I

    move-result v0

    invoke-virtual {p1}, Landroid/view/View;->getWidth()I

    move-result v1

    iget-object v2, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aM:Landroid/graphics/Bitmap;

    invoke-virtual {v2}, Landroid/graphics/Bitmap;->getWidth()I

    move-result v2

    sub-int/2addr v1, v2

    div-int/lit8 v1, v1, 0x2

    add-int/2addr v0, v1

    int-to-float v0, v0

    .line 344
    const-string v1, "IndicatorPosition"

    const/4 v2, 0x1

    new-array v2, v2, [F

    const/4 v3, 0x0

    aput v0, v2, v3

    invoke-static {p0, v1, v2}, Landroid/animation/ObjectAnimator;->ofFloat(Ljava/lang/Object;Ljava/lang/String;[F)Landroid/animation/ObjectAnimator;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aR:Landroid/animation/ObjectAnimator;

    .line 345
    iget-object v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aR:Landroid/animation/ObjectAnimator;

    const-wide/16 v1, 0x12c

    invoke-virtual {v0, v1, v2}, Landroid/animation/ObjectAnimator;->setDuration(J)Landroid/animation/ObjectAnimator;

    .line 346
    iget-object v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aR:Landroid/animation/ObjectAnimator;

    invoke-virtual {v0}, Landroid/animation/ObjectAnimator;->start()V

    goto :goto_0
.end method

.method static synthetic b(Lcom/miui/internal/widget/ScrollingTabContainerView;Landroid/app/ActionBar$Tab;Z)Lcom/miui/internal/widget/ScrollingTabContainerView$TabView;
    .locals 1

    .prologue
    .line 44
    invoke-direct {p0, p1, p2}, Lcom/miui/internal/widget/ScrollingTabContainerView;->a(Landroid/app/ActionBar$Tab;Z)Lcom/miui/internal/widget/ScrollingTabContainerView$TabView;

    move-result-object v0

    return-object v0
.end method

.method private g(I)V
    .locals 1

    .prologue
    .line 329
    iget-object v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aI:Landroid/widget/LinearLayout;

    invoke-virtual {v0, p1}, Landroid/widget/LinearLayout;->getChildAt(I)Landroid/view/View;

    move-result-object v0

    invoke-direct {p0, v0}, Lcom/miui/internal/widget/ScrollingTabContainerView;->a(Landroid/view/View;)V

    .line 330
    return-void
.end method

.method private h()V
    .locals 5

    .prologue
    const/4 v4, 0x0

    .line 245
    invoke-direct {p0}, Lcom/miui/internal/widget/ScrollingTabContainerView;->isCollapsed()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 263
    :goto_0
    return-void

    .line 249
    :cond_0
    iget-object v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aJ:Landroid/widget/Spinner;

    if-nez v0, :cond_1

    .line 250
    invoke-direct {p0}, Lcom/miui/internal/widget/ScrollingTabContainerView;->j()Landroid/widget/Spinner;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aJ:Landroid/widget/Spinner;

    .line 252
    :cond_1
    iget-object v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aI:Landroid/widget/LinearLayout;

    invoke-virtual {p0, v0}, Lcom/miui/internal/widget/ScrollingTabContainerView;->removeView(Landroid/view/View;)V

    .line 253
    iget-object v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aJ:Landroid/widget/Spinner;

    new-instance v1, Landroid/view/ViewGroup$LayoutParams;

    const/4 v2, -0x2

    const/4 v3, -0x1

    invoke-direct {v1, v2, v3}, Landroid/view/ViewGroup$LayoutParams;-><init>(II)V

    invoke-virtual {p0, v0, v1}, Lcom/miui/internal/widget/ScrollingTabContainerView;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 255
    iget-object v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aJ:Landroid/widget/Spinner;

    invoke-virtual {v0}, Landroid/widget/Spinner;->getAdapter()Landroid/widget/SpinnerAdapter;

    move-result-object v0

    if-nez v0, :cond_2

    .line 256
    iget-object v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aJ:Landroid/widget/Spinner;

    new-instance v1, Lcom/miui/internal/widget/ScrollingTabContainerView$a;

    invoke-direct {v1, p0, v4}, Lcom/miui/internal/widget/ScrollingTabContainerView$a;-><init>(Lcom/miui/internal/widget/ScrollingTabContainerView;Lcom/miui/internal/widget/ScrollingTabContainerView$1;)V

    invoke-virtual {v0, v1}, Landroid/widget/Spinner;->setAdapter(Landroid/widget/SpinnerAdapter;)V

    .line 258
    :cond_2
    iget-object v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aE:Ljava/lang/Runnable;

    if-eqz v0, :cond_3

    .line 259
    iget-object v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aE:Ljava/lang/Runnable;

    invoke-virtual {p0, v0}, Lcom/miui/internal/widget/ScrollingTabContainerView;->removeCallbacks(Ljava/lang/Runnable;)Z

    .line 260
    iput-object v4, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aE:Ljava/lang/Runnable;

    .line 262
    :cond_3
    iget-object v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aJ:Landroid/widget/Spinner;

    iget v1, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aL:I

    invoke-virtual {v0, v1}, Landroid/widget/Spinner;->setSelection(I)V

    goto :goto_0
.end method

.method private i()Z
    .locals 5

    .prologue
    const/4 v4, 0x0

    .line 266
    invoke-direct {p0}, Lcom/miui/internal/widget/ScrollingTabContainerView;->isCollapsed()Z

    move-result v0

    if-nez v0, :cond_0

    .line 274
    :goto_0
    return v4

    .line 270
    :cond_0
    iget-object v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aJ:Landroid/widget/Spinner;

    invoke-virtual {p0, v0}, Lcom/miui/internal/widget/ScrollingTabContainerView;->removeView(Landroid/view/View;)V

    .line 271
    iget-object v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aI:Landroid/widget/LinearLayout;

    new-instance v1, Landroid/view/ViewGroup$LayoutParams;

    const/4 v2, -0x2

    const/4 v3, -0x1

    invoke-direct {v1, v2, v3}, Landroid/view/ViewGroup$LayoutParams;-><init>(II)V

    invoke-virtual {p0, v0, v1}, Lcom/miui/internal/widget/ScrollingTabContainerView;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 273
    iget-object v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aJ:Landroid/widget/Spinner;

    invoke-virtual {v0}, Landroid/widget/Spinner;->getSelectedItemPosition()I

    move-result v0

    invoke-virtual {p0, v0}, Lcom/miui/internal/widget/ScrollingTabContainerView;->setTabSelected(I)V

    goto :goto_0
.end method

.method private isCollapsed()Z
    .locals 1

    .prologue
    .line 237
    iget-object v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aJ:Landroid/widget/Spinner;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aJ:Landroid/widget/Spinner;

    invoke-virtual {v0}, Landroid/widget/Spinner;->getParent()Landroid/view/ViewParent;

    move-result-object v0

    if-ne v0, p0, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method private j()Landroid/widget/Spinner;
    .locals 4

    .prologue
    .line 296
    new-instance v0, Landroid/widget/Spinner;

    invoke-virtual {p0}, Lcom/miui/internal/widget/ScrollingTabContainerView;->getContext()Landroid/content/Context;

    move-result-object v1

    const/4 v2, 0x0

    const v3, 0x10102d7

    invoke-direct {v0, v1, v2, v3}, Landroid/widget/Spinner;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    .line 298
    new-instance v1, Landroid/widget/LinearLayout$LayoutParams;

    const/4 v2, -0x2

    const/4 v3, -0x1

    invoke-direct {v1, v2, v3}, Landroid/widget/LinearLayout$LayoutParams;-><init>(II)V

    invoke-virtual {v0, v1}, Landroid/widget/Spinner;->setLayoutParams(Landroid/view/ViewGroup$LayoutParams;)V

    .line 302
    return-object v0
.end method


# virtual methods
.method public addTab(Landroid/app/ActionBar$Tab;IZ)V
    .locals 2

    .prologue
    .line 401
    const/4 v0, 0x0

    invoke-direct {p0, p1, v0}, Lcom/miui/internal/widget/ScrollingTabContainerView;->a(Landroid/app/ActionBar$Tab;Z)Lcom/miui/internal/widget/ScrollingTabContainerView$TabView;

    move-result-object v1

    .line 402
    iget-object v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aI:Landroid/widget/LinearLayout;

    invoke-virtual {v0, v1, p2}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;I)V

    .line 403
    iget-object v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aJ:Landroid/widget/Spinner;

    if-eqz v0, :cond_0

    .line 404
    iget-object v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aJ:Landroid/widget/Spinner;

    invoke-virtual {v0}, Landroid/widget/Spinner;->getAdapter()Landroid/widget/SpinnerAdapter;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/widget/ScrollingTabContainerView$a;

    invoke-virtual {v0}, Lcom/miui/internal/widget/ScrollingTabContainerView$a;->notifyDataSetChanged()V

    .line 406
    :cond_0
    if-eqz p3, :cond_1

    .line 407
    const/4 v0, 0x1

    invoke-virtual {v1, v0}, Lcom/miui/internal/widget/ScrollingTabContainerView$TabView;->setSelected(Z)V

    .line 409
    :cond_1
    iget-boolean v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aK:Z

    if-eqz v0, :cond_2

    .line 410
    invoke-virtual {p0}, Lcom/miui/internal/widget/ScrollingTabContainerView;->requestLayout()V

    .line 412
    :cond_2
    return-void
.end method

.method public addTab(Landroid/app/ActionBar$Tab;Z)V
    .locals 2

    .prologue
    .line 387
    const/4 v0, 0x0

    invoke-direct {p0, p1, v0}, Lcom/miui/internal/widget/ScrollingTabContainerView;->a(Landroid/app/ActionBar$Tab;Z)Lcom/miui/internal/widget/ScrollingTabContainerView$TabView;

    move-result-object v1

    .line 388
    iget-object v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aI:Landroid/widget/LinearLayout;

    invoke-virtual {v0, v1}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    .line 389
    iget-object v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aJ:Landroid/widget/Spinner;

    if-eqz v0, :cond_0

    .line 390
    iget-object v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aJ:Landroid/widget/Spinner;

    invoke-virtual {v0}, Landroid/widget/Spinner;->getAdapter()Landroid/widget/SpinnerAdapter;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/widget/ScrollingTabContainerView$a;

    invoke-virtual {v0}, Lcom/miui/internal/widget/ScrollingTabContainerView$a;->notifyDataSetChanged()V

    .line 392
    :cond_0
    if-eqz p2, :cond_1

    .line 393
    const/4 v0, 0x1

    invoke-virtual {v1, v0}, Lcom/miui/internal/widget/ScrollingTabContainerView$TabView;->setSelected(Z)V

    .line 395
    :cond_1
    iget-boolean v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aK:Z

    if-eqz v0, :cond_2

    .line 396
    invoke-virtual {p0}, Lcom/miui/internal/widget/ScrollingTabContainerView;->requestLayout()V

    .line 398
    :cond_2
    return-void
.end method

.method public animateToTab(I)V
    .locals 2

    .prologue
    .line 314
    iget-object v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aI:Landroid/widget/LinearLayout;

    invoke-virtual {v0, p1}, Landroid/widget/LinearLayout;->getChildAt(I)Landroid/view/View;

    move-result-object v0

    .line 315
    iget-object v1, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aE:Ljava/lang/Runnable;

    if-eqz v1, :cond_0

    .line 316
    iget-object v1, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aE:Ljava/lang/Runnable;

    invoke-virtual {p0, v1}, Lcom/miui/internal/widget/ScrollingTabContainerView;->removeCallbacks(Ljava/lang/Runnable;)Z

    .line 318
    :cond_0
    new-instance v1, Lcom/miui/internal/widget/ScrollingTabContainerView$1;

    invoke-direct {v1, p0, v0}, Lcom/miui/internal/widget/ScrollingTabContainerView$1;-><init>(Lcom/miui/internal/widget/ScrollingTabContainerView;Landroid/view/View;)V

    iput-object v1, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aE:Ljava/lang/Runnable;

    .line 325
    iget-object v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aE:Ljava/lang/Runnable;

    invoke-virtual {p0, v0}, Lcom/miui/internal/widget/ScrollingTabContainerView;->post(Ljava/lang/Runnable;)Z

    .line 326
    return-void
.end method

.method public draw(Landroid/graphics/Canvas;)V
    .locals 7

    .prologue
    const/4 v1, 0x0

    .line 133
    invoke-virtual {p0}, Lcom/miui/internal/widget/ScrollingTabContainerView;->getWidth()I

    move-result v0

    invoke-virtual {p0}, Lcom/miui/internal/widget/ScrollingTabContainerView;->getScrollX()I

    move-result v2

    add-int/2addr v0, v2

    int-to-float v3, v0

    invoke-virtual {p0}, Lcom/miui/internal/widget/ScrollingTabContainerView;->getHeight()I

    move-result v0

    int-to-float v4, v0

    const/4 v5, 0x0

    const/16 v6, 0x1f

    move-object v0, p1

    move v2, v1

    invoke-virtual/range {v0 .. v6}, Landroid/graphics/Canvas;->saveLayer(FFFFLandroid/graphics/Paint;I)I

    .line 134
    invoke-super {p0, p1}, Landroid/widget/HorizontalScrollView;->draw(Landroid/graphics/Canvas;)V

    .line 135
    iget-object v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aM:Landroid/graphics/Bitmap;

    if-eqz v0, :cond_0

    .line 136
    iget-object v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aM:Landroid/graphics/Bitmap;

    iget v1, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aO:F

    invoke-virtual {p0}, Lcom/miui/internal/widget/ScrollingTabContainerView;->getHeight()I

    move-result v2

    iget-object v3, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aM:Landroid/graphics/Bitmap;

    invoke-virtual {v3}, Landroid/graphics/Bitmap;->getHeight()I

    move-result v3

    sub-int/2addr v2, v3

    int-to-float v2, v2

    iget-object v3, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aP:Landroid/graphics/Paint;

    invoke-virtual {p1, v0, v1, v2, v3}, Landroid/graphics/Canvas;->drawBitmap(Landroid/graphics/Bitmap;FFLandroid/graphics/Paint;)V

    .line 139
    :cond_0
    invoke-virtual {p1}, Landroid/graphics/Canvas;->restore()V

    .line 140
    return-void
.end method

.method public getTabIndicatorPosition()F
    .locals 1

    .prologue
    .line 201
    iget v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aO:F

    return v0
.end method

.method public onAttachedToWindow()V
    .locals 1

    .prologue
    .line 351
    invoke-super {p0}, Landroid/widget/HorizontalScrollView;->onAttachedToWindow()V

    .line 352
    iget-object v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aE:Ljava/lang/Runnable;

    if-eqz v0, :cond_0

    .line 354
    iget-object v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aE:Ljava/lang/Runnable;

    invoke-virtual {p0, v0}, Lcom/miui/internal/widget/ScrollingTabContainerView;->post(Ljava/lang/Runnable;)Z

    .line 356
    :cond_0
    return-void
.end method

.method protected onConfigurationChanged(Landroid/content/res/Configuration;)V
    .locals 2

    .prologue
    .line 306
    invoke-virtual {p0}, Lcom/miui/internal/widget/ScrollingTabContainerView;->getContext()Landroid/content/Context;

    move-result-object v0

    invoke-static {v0}, Lcom/miui/internal/view/ActionBarPolicy;->get(Landroid/content/Context;)Lcom/miui/internal/view/ActionBarPolicy;

    move-result-object v0

    .line 309
    invoke-virtual {v0}, Lcom/miui/internal/view/ActionBarPolicy;->getTabContainerHeight()I

    move-result v1

    invoke-virtual {p0, v1}, Lcom/miui/internal/widget/ScrollingTabContainerView;->setContentHeight(I)V

    .line 310
    invoke-virtual {v0}, Lcom/miui/internal/view/ActionBarPolicy;->getStackedTabMaxWidth()I

    move-result v0

    iput v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aG:I

    .line 311
    return-void
.end method

.method public onDetachedFromWindow()V
    .locals 1

    .prologue
    .line 360
    invoke-super {p0}, Landroid/widget/HorizontalScrollView;->onDetachedFromWindow()V

    .line 361
    iget-object v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aE:Ljava/lang/Runnable;

    if-eqz v0, :cond_0

    .line 362
    iget-object v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aE:Ljava/lang/Runnable;

    invoke-virtual {p0, v0}, Lcom/miui/internal/widget/ScrollingTabContainerView;->removeCallbacks(Ljava/lang/Runnable;)Z

    .line 364
    :cond_0
    return-void
.end method

.method public onItemClick(Landroid/widget/AdapterView;Landroid/view/View;IJ)V
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Landroid/widget/AdapterView",
            "<*>;",
            "Landroid/view/View;",
            "IJ)V"
        }
    .end annotation

    .prologue
    .line 446
    check-cast p2, Lcom/miui/internal/widget/ScrollingTabContainerView$TabView;

    .line 447
    invoke-virtual {p2}, Lcom/miui/internal/widget/ScrollingTabContainerView$TabView;->getTab()Landroid/app/ActionBar$Tab;

    move-result-object v0

    invoke-virtual {v0}, Landroid/app/ActionBar$Tab;->select()V

    .line 448
    return-void
.end method

.method protected onLayout(ZIIII)V
    .locals 2

    .prologue
    .line 144
    invoke-super/range {p0 .. p5}, Landroid/widget/HorizontalScrollView;->onLayout(ZIIII)V

    .line 146
    iget-object v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aI:Landroid/widget/LinearLayout;

    iget v1, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aL:I

    invoke-virtual {v0, v1}, Landroid/widget/LinearLayout;->getChildAt(I)Landroid/view/View;

    move-result-object v0

    .line 147
    if-eqz v0, :cond_0

    .line 148
    iget v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aL:I

    invoke-virtual {p0, v0}, Lcom/miui/internal/widget/ScrollingTabContainerView;->setTabIndicatorPosition(I)V

    .line 150
    :cond_0
    return-void
.end method

.method public onMeasure(II)V
    .locals 7

    .prologue
    const/high16 v6, 0x40000000

    const/4 v1, 0x1

    const/4 v2, 0x0

    .line 154
    invoke-static {p1}, Landroid/view/View$MeasureSpec;->getMode(I)I

    move-result v3

    .line 155
    if-ne v3, v6, :cond_2

    move v0, v1

    .line 156
    :goto_0
    invoke-virtual {p0, v0}, Lcom/miui/internal/widget/ScrollingTabContainerView;->setFillViewport(Z)V

    .line 158
    iget-object v4, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aI:Landroid/widget/LinearLayout;

    invoke-virtual {v4}, Landroid/widget/LinearLayout;->getChildCount()I

    move-result v4

    .line 159
    if-le v4, v1, :cond_4

    if-eq v3, v6, :cond_0

    const/high16 v5, -0x80000000

    if-ne v3, v5, :cond_4

    .line 161
    :cond_0
    const/4 v3, 0x2

    if-le v4, v3, :cond_3

    .line 162
    invoke-static {p1}, Landroid/view/View$MeasureSpec;->getSize(I)I

    move-result v3

    int-to-float v3, v3

    const v4, 0x3ecccccd

    mul-float/2addr v3, v4

    float-to-int v3, v3

    iput v3, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aF:I

    .line 166
    :goto_1
    iget v3, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aF:I

    iget v4, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aG:I

    invoke-static {v3, v4}, Ljava/lang/Math;->min(II)I

    move-result v3

    iput v3, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aF:I

    .line 171
    :goto_2
    iget v3, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->mContentHeight:I

    invoke-static {v3, v6}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result v3

    .line 173
    if-nez v0, :cond_5

    iget-boolean v4, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aK:Z

    if-eqz v4, :cond_5

    .line 175
    :goto_3
    if-eqz v1, :cond_7

    .line 177
    iget-object v1, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aI:Landroid/widget/LinearLayout;

    invoke-virtual {v1, v2, v3}, Landroid/widget/LinearLayout;->measure(II)V

    .line 178
    iget-object v1, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aI:Landroid/widget/LinearLayout;

    invoke-virtual {v1}, Landroid/widget/LinearLayout;->getMeasuredWidth()I

    move-result v1

    invoke-static {p1}, Landroid/view/View$MeasureSpec;->getSize(I)I

    move-result v2

    if-le v1, v2, :cond_6

    .line 179
    invoke-direct {p0}, Lcom/miui/internal/widget/ScrollingTabContainerView;->h()V

    .line 187
    :goto_4
    invoke-virtual {p0}, Lcom/miui/internal/widget/ScrollingTabContainerView;->getMeasuredWidth()I

    move-result v1

    .line 188
    invoke-super {p0, p1, v3}, Landroid/widget/HorizontalScrollView;->onMeasure(II)V

    .line 189
    invoke-virtual {p0}, Lcom/miui/internal/widget/ScrollingTabContainerView;->getMeasuredWidth()I

    move-result v2

    .line 191
    if-eqz v0, :cond_1

    if-eq v1, v2, :cond_1

    .line 193
    iget v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aL:I

    invoke-virtual {p0, v0}, Lcom/miui/internal/widget/ScrollingTabContainerView;->setTabSelected(I)V

    .line 195
    :cond_1
    return-void

    :cond_2
    move v0, v2

    .line 155
    goto :goto_0

    .line 164
    :cond_3
    invoke-static {p1}, Landroid/view/View$MeasureSpec;->getSize(I)I

    move-result v3

    int-to-float v3, v3

    const v4, 0x3f19999a

    mul-float/2addr v3, v4

    float-to-int v3, v3

    iput v3, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aF:I

    goto :goto_1

    .line 168
    :cond_4
    const/4 v3, -0x1

    iput v3, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aF:I

    goto :goto_2

    :cond_5
    move v1, v2

    .line 173
    goto :goto_3

    .line 181
    :cond_6
    invoke-direct {p0}, Lcom/miui/internal/widget/ScrollingTabContainerView;->i()Z

    goto :goto_4

    .line 184
    :cond_7
    invoke-direct {p0}, Lcom/miui/internal/widget/ScrollingTabContainerView;->i()Z

    goto :goto_4
.end method

.method public onPageScrollStateChanged(I)V
    .locals 0

    .prologue
    .line 467
    return-void
.end method

.method public onPageScrolled(IFZZ)V
    .locals 0

    .prologue
    .line 452
    invoke-virtual {p0, p1, p2}, Lcom/miui/internal/widget/ScrollingTabContainerView;->setTabIndicatorPosition(IF)V

    .line 453
    return-void
.end method

.method public onPageSelected(I)V
    .locals 2

    .prologue
    .line 457
    iget-object v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aI:Landroid/widget/LinearLayout;

    invoke-virtual {v0, p1}, Landroid/widget/LinearLayout;->getChildAt(I)Landroid/view/View;

    move-result-object v0

    .line 458
    if-eqz v0, :cond_0

    .line 459
    const/4 v1, 0x4

    invoke-virtual {v0, v1}, Landroid/view/View;->sendAccessibilityEvent(I)V

    .line 461
    :cond_0
    invoke-virtual {p0, p1}, Lcom/miui/internal/widget/ScrollingTabContainerView;->setTabIndicatorPosition(I)V

    .line 462
    return-void
.end method

.method public removeAllTabs()V
    .locals 1

    .prologue
    .line 435
    iget-object v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aI:Landroid/widget/LinearLayout;

    invoke-virtual {v0}, Landroid/widget/LinearLayout;->removeAllViews()V

    .line 436
    iget-object v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aJ:Landroid/widget/Spinner;

    if-eqz v0, :cond_0

    .line 437
    iget-object v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aJ:Landroid/widget/Spinner;

    invoke-virtual {v0}, Landroid/widget/Spinner;->getAdapter()Landroid/widget/SpinnerAdapter;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/widget/ScrollingTabContainerView$a;

    invoke-virtual {v0}, Lcom/miui/internal/widget/ScrollingTabContainerView$a;->notifyDataSetChanged()V

    .line 439
    :cond_0
    iget-boolean v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aK:Z

    if-eqz v0, :cond_1

    .line 440
    invoke-virtual {p0}, Lcom/miui/internal/widget/ScrollingTabContainerView;->requestLayout()V

    .line 442
    :cond_1
    return-void
.end method

.method public removeTabAt(I)V
    .locals 1

    .prologue
    .line 425
    iget-object v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aI:Landroid/widget/LinearLayout;

    invoke-virtual {v0, p1}, Landroid/widget/LinearLayout;->removeViewAt(I)V

    .line 426
    iget-object v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aJ:Landroid/widget/Spinner;

    if-eqz v0, :cond_0

    .line 427
    iget-object v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aJ:Landroid/widget/Spinner;

    invoke-virtual {v0}, Landroid/widget/Spinner;->getAdapter()Landroid/widget/SpinnerAdapter;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/widget/ScrollingTabContainerView$a;

    invoke-virtual {v0}, Lcom/miui/internal/widget/ScrollingTabContainerView$a;->notifyDataSetChanged()V

    .line 429
    :cond_0
    iget-boolean v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aK:Z

    if-eqz v0, :cond_1

    .line 430
    invoke-virtual {p0}, Lcom/miui/internal/widget/ScrollingTabContainerView;->requestLayout()V

    .line 432
    :cond_1
    return-void
.end method

.method public setAllowCollapse(Z)V
    .locals 0

    .prologue
    .line 241
    iput-boolean p1, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aK:Z

    .line 242
    return-void
.end method

.method public setContentHeight(I)V
    .locals 0

    .prologue
    .line 291
    iput p1, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->mContentHeight:I

    .line 292
    invoke-virtual {p0}, Lcom/miui/internal/widget/ScrollingTabContainerView;->requestLayout()V

    .line 293
    return-void
.end method

.method public setFragmentViewPagerMode(Z)V
    .locals 0

    .prologue
    .line 109
    iput-boolean p1, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aQ:Z

    .line 110
    return-void
.end method

.method public setTabIndicatorPosition(I)V
    .locals 1

    .prologue
    .line 208
    const/4 v0, 0x0

    invoke-virtual {p0, p1, v0}, Lcom/miui/internal/widget/ScrollingTabContainerView;->setTabIndicatorPosition(IF)V

    .line 209
    return-void
.end method

.method public setTabIndicatorPosition(IF)V
    .locals 4

    .prologue
    .line 220
    iget-object v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aM:Landroid/graphics/Bitmap;

    if-eqz v0, :cond_0

    .line 221
    iget-object v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aI:Landroid/widget/LinearLayout;

    invoke-virtual {v0, p1}, Landroid/widget/LinearLayout;->getChildAt(I)Landroid/view/View;

    move-result-object v1

    .line 222
    iget-object v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aI:Landroid/widget/LinearLayout;

    add-int/lit8 v2, p1, 0x1

    invoke-virtual {v0, v2}, Landroid/widget/LinearLayout;->getChildAt(I)Landroid/view/View;

    move-result-object v0

    .line 223
    if-nez v0, :cond_1

    invoke-virtual {v1}, Landroid/view/View;->getWidth()I

    move-result v0

    int-to-float v0, v0

    .line 225
    :goto_0
    invoke-virtual {v1}, Landroid/view/View;->getLeft()I

    move-result v2

    invoke-virtual {v1}, Landroid/view/View;->getWidth()I

    move-result v1

    iget-object v3, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aM:Landroid/graphics/Bitmap;

    invoke-virtual {v3}, Landroid/graphics/Bitmap;->getWidth()I

    move-result v3

    sub-int/2addr v1, v3

    div-int/lit8 v1, v1, 0x2

    add-int/2addr v1, v2

    int-to-float v1, v1

    mul-float/2addr v0, p2

    add-float/2addr v0, v1

    iput v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aO:F

    .line 227
    invoke-virtual {p0}, Lcom/miui/internal/widget/ScrollingTabContainerView;->invalidate()V

    .line 229
    :cond_0
    return-void

    .line 223
    :cond_1
    invoke-virtual {v1}, Landroid/view/View;->getWidth()I

    move-result v2

    invoke-virtual {v0}, Landroid/view/View;->getWidth()I

    move-result v0

    add-int/2addr v0, v2

    int-to-float v0, v0

    const/high16 v2, 0x40000000

    div-float/2addr v0, v2

    goto :goto_0
.end method

.method public setTabSelected(I)V
    .locals 5

    .prologue
    const/4 v1, 0x0

    .line 278
    iput p1, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aL:I

    .line 279
    iget-object v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aI:Landroid/widget/LinearLayout;

    invoke-virtual {v0}, Landroid/widget/LinearLayout;->getChildCount()I

    move-result v3

    move v2, v1

    .line 280
    :goto_0
    if-ge v2, v3, :cond_2

    .line 281
    iget-object v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aI:Landroid/widget/LinearLayout;

    invoke-virtual {v0, v2}, Landroid/widget/LinearLayout;->getChildAt(I)Landroid/view/View;

    move-result-object v4

    .line 282
    if-ne v2, p1, :cond_1

    const/4 v0, 0x1

    .line 283
    :goto_1
    invoke-virtual {v4, v0}, Landroid/view/View;->setSelected(Z)V

    .line 284
    if-eqz v0, :cond_0

    .line 285
    invoke-virtual {p0, p1}, Lcom/miui/internal/widget/ScrollingTabContainerView;->animateToTab(I)V

    .line 280
    :cond_0
    add-int/lit8 v0, v2, 0x1

    move v2, v0

    goto :goto_0

    :cond_1
    move v0, v1

    .line 282
    goto :goto_1

    .line 288
    :cond_2
    return-void
.end method

.method public updateTab(I)V
    .locals 1

    .prologue
    .line 415
    iget-object v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aI:Landroid/widget/LinearLayout;

    invoke-virtual {v0, p1}, Landroid/widget/LinearLayout;->getChildAt(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/widget/ScrollingTabContainerView$TabView;

    invoke-virtual {v0}, Lcom/miui/internal/widget/ScrollingTabContainerView$TabView;->update()V

    .line 416
    iget-object v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aJ:Landroid/widget/Spinner;

    if-eqz v0, :cond_0

    .line 417
    iget-object v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aJ:Landroid/widget/Spinner;

    invoke-virtual {v0}, Landroid/widget/Spinner;->getAdapter()Landroid/widget/SpinnerAdapter;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/widget/ScrollingTabContainerView$a;

    invoke-virtual {v0}, Lcom/miui/internal/widget/ScrollingTabContainerView$a;->notifyDataSetChanged()V

    .line 419
    :cond_0
    iget-boolean v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aK:Z

    if-eqz v0, :cond_1

    .line 420
    invoke-virtual {p0}, Lcom/miui/internal/widget/ScrollingTabContainerView;->requestLayout()V

    .line 422
    :cond_1
    return-void
.end method
