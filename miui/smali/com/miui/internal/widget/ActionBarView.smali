.class public Lcom/miui/internal/widget/ActionBarView;
.super Lcom/miui/internal/widget/g;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/internal/widget/ActionBarView$a;,
        Lcom/miui/internal/widget/ActionBarView$HomeView;,
        Lcom/miui/internal/widget/ActionBarView$b;
    }
.end annotation


# static fields
.field public static final DISPLAY_DEFAULT:I = 0x0

.field private static final TAG:Ljava/lang/String; = "ActionBarView"

.field private static final ht:I = 0x1f

.field private static final hu:I = 0x13

.field private static final hv:I = 0x1

.field private static final hw:I = 0x2


# instance fields
.field private hA:I

.field private hB:Landroid/graphics/drawable/Drawable;

.field private hC:Landroid/graphics/drawable/Drawable;

.field private final hD:I

.field private hE:Landroid/graphics/drawable/Drawable;

.field private hF:I

.field private hG:Lcom/miui/internal/widget/ActionBarView$HomeView;

.field private hH:Lcom/miui/internal/widget/ActionBarView$HomeView;

.field private hI:Landroid/widget/LinearLayout;

.field private hJ:Landroid/widget/TextView;

.field private hK:Landroid/widget/TextView;

.field private hL:Landroid/view/View;

.field private hM:Landroid/widget/Spinner;

.field private hN:Landroid/widget/LinearLayout;

.field private hO:Lcom/miui/internal/widget/ScrollingTabContainerView;

.field private hP:Landroid/view/View;

.field private hQ:Landroid/widget/ProgressBar;

.field private hR:Landroid/widget/ProgressBar;

.field private hS:Landroid/view/View;

.field private hT:I

.field private hU:I

.field private hV:I

.field private hW:I

.field private hX:Z

.field private hY:Z

.field private hZ:Z

.field private hx:I

.field private hy:I

.field private hz:Ljava/lang/CharSequence;

.field private ia:Z

.field private ib:Lcom/miui/internal/view/menu/MenuBuilder;

.field private ic:Lcom/miui/internal/view/menu/ActionMenuItem;

.field private ie:Landroid/widget/SpinnerAdapter;

.field private if:Landroid/app/ActionBar$OnNavigationListener;

.field private ig:Lcom/miui/internal/widget/ActionBarView$a;

.field ii:Landroid/view/View;

.field ij:Landroid/view/Window$Callback;

.field private final ik:Landroid/widget/AdapterView$OnItemSelectedListener;

.field private final il:Landroid/view/View$OnClickListener;

.field private final im:Landroid/view/View$OnClickListener;

.field private mContext:Landroid/content/Context;

.field private mTitle:Ljava/lang/CharSequence;


# direct methods
.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 7

    .prologue
    const/4 v2, 0x0

    .line 162
    invoke-direct {p0, p1, p2}, Lcom/miui/internal/widget/g;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    .line 79
    const/4 v0, -0x1

    iput v0, p0, Lcom/miui/internal/widget/ActionBarView;->hy:I

    .line 131
    new-instance v0, Lcom/miui/internal/widget/d;

    invoke-direct {v0, p0}, Lcom/miui/internal/widget/d;-><init>(Lcom/miui/internal/widget/ActionBarView;)V

    iput-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->ik:Landroid/widget/AdapterView$OnItemSelectedListener;

    .line 145
    new-instance v0, Lcom/miui/internal/widget/f;

    invoke-direct {v0, p0}, Lcom/miui/internal/widget/f;-><init>(Lcom/miui/internal/widget/ActionBarView;)V

    iput-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->il:Landroid/view/View$OnClickListener;

    .line 155
    new-instance v0, Lcom/miui/internal/widget/e;

    invoke-direct {v0, p0}, Lcom/miui/internal/widget/e;-><init>(Lcom/miui/internal/widget/ActionBarView;)V

    iput-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->im:Landroid/view/View$OnClickListener;

    .line 163
    iput-object p1, p0, Lcom/miui/internal/widget/ActionBarView;->mContext:Landroid/content/Context;

    .line 166
    invoke-virtual {p0, v2}, Lcom/miui/internal/widget/ActionBarView;->setBackgroundResource(I)V

    .line 168
    sget-object v0, Lmiui/R$styleable;->ActionBar:[I

    const v1, 0x10102ce

    invoke-virtual {p1, p2, v0, v1, v2}, Landroid/content/Context;->obtainStyledAttributes(Landroid/util/AttributeSet;[III)Landroid/content/res/TypedArray;

    move-result-object v0

    .line 171
    const/4 v1, 0x6

    invoke-virtual {v0, v1, v2}, Landroid/content/res/TypedArray;->getInt(II)I

    move-result v1

    iput v1, p0, Lcom/miui/internal/widget/ActionBarView;->hx:I

    .line 173
    const/4 v1, 0x4

    invoke-virtual {v0, v1}, Landroid/content/res/TypedArray;->getText(I)Ljava/lang/CharSequence;

    move-result-object v1

    iput-object v1, p0, Lcom/miui/internal/widget/ActionBarView;->mTitle:Ljava/lang/CharSequence;

    .line 174
    const/16 v1, 0x8

    invoke-virtual {v0, v1}, Landroid/content/res/TypedArray;->getText(I)Ljava/lang/CharSequence;

    move-result-object v1

    iput-object v1, p0, Lcom/miui/internal/widget/ActionBarView;->hz:Ljava/lang/CharSequence;

    .line 175
    const/16 v1, 0x16

    invoke-virtual {v0, v1, v2}, Landroid/content/res/TypedArray;->getBoolean(IZ)Z

    move-result v1

    iput-boolean v1, p0, Lcom/miui/internal/widget/ActionBarView;->ia:Z

    .line 177
    const/4 v1, 0x5

    invoke-virtual {v0, v1}, Landroid/content/res/TypedArray;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v1

    iput-object v1, p0, Lcom/miui/internal/widget/ActionBarView;->hC:Landroid/graphics/drawable/Drawable;

    .line 178
    invoke-virtual {v0, v2}, Landroid/content/res/TypedArray;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v1

    iput-object v1, p0, Lcom/miui/internal/widget/ActionBarView;->hB:Landroid/graphics/drawable/Drawable;

    .line 180
    invoke-static {p1}, Landroid/view/LayoutInflater;->from(Landroid/content/Context;)Landroid/view/LayoutInflater;

    move-result-object v1

    .line 182
    const/16 v3, 0xd

    sget v4, Lcom/miui/internal/R$layout;->action_bar_home:I

    invoke-virtual {v0, v3, v4}, Landroid/content/res/TypedArray;->getResourceId(II)I

    move-result v3

    iput v3, p0, Lcom/miui/internal/widget/ActionBarView;->hD:I

    .line 186
    const/16 v3, 0xa

    invoke-virtual {v0, v3, v2}, Landroid/content/res/TypedArray;->getResourceId(II)I

    move-result v3

    iput v3, p0, Lcom/miui/internal/widget/ActionBarView;->hV:I

    .line 187
    const/16 v3, 0xb

    invoke-virtual {v0, v3, v2}, Landroid/content/res/TypedArray;->getResourceId(II)I

    move-result v3

    iput v3, p0, Lcom/miui/internal/widget/ActionBarView;->hW:I

    .line 189
    const/16 v3, 0xc

    invoke-virtual {v0, v3, v2}, Landroid/content/res/TypedArray;->getDimensionPixelOffset(II)I

    move-result v3

    iput v3, p0, Lcom/miui/internal/widget/ActionBarView;->hT:I

    .line 191
    const/16 v3, 0xe

    invoke-virtual {v0, v3, v2}, Landroid/content/res/TypedArray;->getDimensionPixelOffset(II)I

    move-result v3

    iput v3, p0, Lcom/miui/internal/widget/ActionBarView;->hU:I

    .line 193
    const/4 v3, 0x7

    invoke-virtual {v0, v3, v2}, Landroid/content/res/TypedArray;->getInt(II)I

    move-result v3

    invoke-virtual {p0, v3}, Lcom/miui/internal/widget/ActionBarView;->setDisplayOptions(I)V

    .line 195
    const/16 v3, 0x9

    invoke-virtual {v0, v3, v2}, Landroid/content/res/TypedArray;->getResourceId(II)I

    move-result v3

    .line 196
    if-eqz v3, :cond_0

    .line 197
    invoke-virtual {v1, v3, p0, v2}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;Z)Landroid/view/View;

    move-result-object v1

    iput-object v1, p0, Lcom/miui/internal/widget/ActionBarView;->hP:Landroid/view/View;

    .line 198
    iput v2, p0, Lcom/miui/internal/widget/ActionBarView;->hx:I

    .line 199
    iget v1, p0, Lcom/miui/internal/widget/ActionBarView;->hy:I

    or-int/lit8 v1, v1, 0x10

    invoke-virtual {p0, v1}, Lcom/miui/internal/widget/ActionBarView;->setDisplayOptions(I)V

    .line 202
    :cond_0
    const/4 v1, 0x3

    invoke-virtual {v0, v1, v2}, Landroid/content/res/TypedArray;->getLayoutDimension(II)I

    move-result v1

    iput v1, p0, Lcom/miui/internal/widget/ActionBarView;->mContentHeight:I

    .line 203
    invoke-virtual {v0}, Landroid/content/res/TypedArray;->recycle()V

    .line 204
    new-instance v0, Lcom/miui/internal/view/menu/ActionMenuItem;

    const v3, 0x102002c

    iget-object v6, p0, Lcom/miui/internal/widget/ActionBarView;->mTitle:Ljava/lang/CharSequence;

    move-object v1, p1

    move v4, v2

    move v5, v2

    invoke-direct/range {v0 .. v6}, Lcom/miui/internal/view/menu/ActionMenuItem;-><init>(Landroid/content/Context;IIIILjava/lang/CharSequence;)V

    iput-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->ic:Lcom/miui/internal/view/menu/ActionMenuItem;

    .line 205
    return-void
.end method

.method static synthetic a(Lcom/miui/internal/widget/ActionBarView;)Landroid/app/ActionBar$OnNavigationListener;
    .locals 1

    .prologue
    .line 54
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->if:Landroid/app/ActionBar$OnNavigationListener;

    return-object v0
.end method

.method private a(Landroid/widget/ProgressBar;Landroid/widget/ProgressBar;)V
    .locals 3

    .prologue
    const/4 v2, 0x0

    .line 1760
    if-eqz p2, :cond_0

    invoke-virtual {p2}, Landroid/widget/ProgressBar;->getVisibility()I

    move-result v0

    const/4 v1, 0x4

    if-ne v0, v1, :cond_0

    .line 1761
    invoke-virtual {p2, v2}, Landroid/widget/ProgressBar;->setVisibility(I)V

    .line 1764
    :cond_0
    if-eqz p1, :cond_1

    invoke-virtual {p1}, Landroid/widget/ProgressBar;->getProgress()I

    move-result v0

    const/16 v1, 0x2710

    if-ge v0, v1, :cond_1

    .line 1765
    invoke-virtual {p1, v2}, Landroid/widget/ProgressBar;->setVisibility(I)V

    .line 1767
    :cond_1
    return-void
.end method

.method private a(Lcom/miui/internal/view/menu/MenuBuilder;)V
    .locals 4

    .prologue
    const/4 v3, 0x0

    const/4 v2, 0x1

    .line 456
    if-eqz p1, :cond_0

    .line 457
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->mActionMenuPresenter:Lcom/miui/internal/view/menu/ActionMenuPresenter;

    invoke-virtual {p1, v0}, Lcom/miui/internal/view/menu/MenuBuilder;->addMenuPresenter(Lcom/miui/internal/view/menu/MenuPresenter;)V

    .line 458
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->ig:Lcom/miui/internal/widget/ActionBarView$a;

    invoke-virtual {p1, v0}, Lcom/miui/internal/view/menu/MenuBuilder;->addMenuPresenter(Lcom/miui/internal/view/menu/MenuPresenter;)V

    .line 465
    :goto_0
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->mActionMenuPresenter:Lcom/miui/internal/view/menu/ActionMenuPresenter;

    invoke-virtual {v0, v2}, Lcom/miui/internal/view/menu/ActionMenuPresenter;->updateMenuView(Z)V

    .line 466
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->ig:Lcom/miui/internal/widget/ActionBarView$a;

    invoke-virtual {v0, v2}, Lcom/miui/internal/widget/ActionBarView$a;->updateMenuView(Z)V

    .line 467
    return-void

    .line 460
    :cond_0
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->mActionMenuPresenter:Lcom/miui/internal/view/menu/ActionMenuPresenter;

    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarView;->mContext:Landroid/content/Context;

    invoke-virtual {v0, v1, v3}, Lcom/miui/internal/view/menu/ActionMenuPresenter;->initForMenu(Landroid/content/Context;Lcom/miui/internal/view/menu/MenuBuilder;)V

    .line 461
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->ig:Lcom/miui/internal/widget/ActionBarView$a;

    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarView;->mContext:Landroid/content/Context;

    invoke-virtual {v0, v1, v3}, Lcom/miui/internal/widget/ActionBarView$a;->initForMenu(Landroid/content/Context;Lcom/miui/internal/view/menu/MenuBuilder;)V

    goto :goto_0
.end method

.method private a(Ljava/lang/CharSequence;)V
    .locals 3

    .prologue
    const/4 v1, 0x0

    .line 521
    iput-object p1, p0, Lcom/miui/internal/widget/ActionBarView;->mTitle:Ljava/lang/CharSequence;

    .line 522
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hJ:Landroid/widget/TextView;

    if-eqz v0, :cond_1

    .line 523
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hJ:Landroid/widget/TextView;

    invoke-virtual {v0, p1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 524
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->ii:Landroid/view/View;

    if-nez v0, :cond_3

    iget v0, p0, Lcom/miui/internal/widget/ActionBarView;->hy:I

    and-int/lit8 v0, v0, 0x8

    if-eqz v0, :cond_3

    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->mTitle:Ljava/lang/CharSequence;

    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hz:Ljava/lang/CharSequence;

    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_3

    :cond_0
    const/4 v0, 0x1

    .line 527
    :goto_0
    iget-object v2, p0, Lcom/miui/internal/widget/ActionBarView;->hI:Landroid/widget/LinearLayout;

    if-eqz v0, :cond_4

    :goto_1
    invoke-virtual {v2, v1}, Landroid/widget/LinearLayout;->setVisibility(I)V

    .line 529
    :cond_1
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->ic:Lcom/miui/internal/view/menu/ActionMenuItem;

    if-eqz v0, :cond_2

    .line 530
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->ic:Lcom/miui/internal/view/menu/ActionMenuItem;

    invoke-virtual {v0, p1}, Lcom/miui/internal/view/menu/ActionMenuItem;->setTitle(Ljava/lang/CharSequence;)Landroid/view/MenuItem;

    .line 532
    :cond_2
    return-void

    :cond_3
    move v0, v1

    .line 524
    goto :goto_0

    .line 527
    :cond_4
    const/16 v1, 0x8

    goto :goto_1
.end method

.method private ak()V
    .locals 2

    .prologue
    .line 823
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->mContext:Landroid/content/Context;

    invoke-static {v0}, Lcom/miui/internal/view/ActionBarPolicy;->get(Landroid/content/Context;)Lcom/miui/internal/view/ActionBarPolicy;

    move-result-object v0

    .line 824
    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarView;->hasEmbeddedTabs()Z

    move-result v1

    if-eqz v1, :cond_2

    invoke-virtual {v0}, Lcom/miui/internal/view/ActionBarPolicy;->isTightTitle()Z

    move-result v0

    if-eqz v0, :cond_2

    const/16 v0, 0x8

    .line 825
    :goto_0
    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarView;->hJ:Landroid/widget/TextView;

    if-eqz v1, :cond_0

    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarView;->hJ:Landroid/widget/TextView;

    invoke-virtual {v1}, Landroid/widget/TextView;->getVisibility()I

    move-result v1

    if-nez v1, :cond_0

    .line 826
    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarView;->hJ:Landroid/widget/TextView;

    invoke-virtual {v1, v0}, Landroid/widget/TextView;->setVisibility(I)V

    .line 828
    :cond_0
    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarView;->hK:Landroid/widget/TextView;

    if-eqz v1, :cond_1

    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarView;->hK:Landroid/widget/TextView;

    invoke-virtual {v1}, Landroid/widget/TextView;->getVisibility()I

    move-result v1

    if-nez v1, :cond_1

    .line 829
    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarView;->hK:Landroid/widget/TextView;

    invoke-virtual {v1, v0}, Landroid/widget/TextView;->setVisibility(I)V

    .line 831
    :cond_1
    return-void

    .line 824
    :cond_2
    const/4 v0, 0x0

    goto :goto_0
.end method

.method private al()Z
    .locals 4

    .prologue
    const/4 v2, 0x1

    const/4 v1, 0x0

    .line 834
    .line 835
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hP:Landroid/view/View;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hP:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->getVisibility()I

    move-result v0

    if-nez v0, :cond_0

    .line 836
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hP:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v0

    .line 837
    instance-of v3, v0, Landroid/app/ActionBar$LayoutParams;

    if-eqz v3, :cond_1

    check-cast v0, Landroid/app/ActionBar$LayoutParams;

    .line 839
    :goto_0
    if-nez v0, :cond_2

    move v0, v1

    :goto_1
    move v2, v0

    .line 842
    :cond_0
    return v2

    .line 837
    :cond_1
    const/4 v0, 0x0

    goto :goto_0

    .line 839
    :cond_2
    iget v0, v0, Landroid/app/ActionBar$LayoutParams;->gravity:I

    and-int/lit8 v0, v0, 0x7

    const/4 v3, 0x5

    if-ne v0, v3, :cond_3

    move v0, v2

    goto :goto_1

    :cond_3
    move v0, v1

    goto :goto_1
.end method

.method private am()Z
    .locals 2

    .prologue
    .line 846
    iget-boolean v0, p0, Lcom/miui/internal/widget/ActionBarView;->ia:Z

    if-eqz v0, :cond_1

    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hL:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->getVisibility()I

    move-result v0

    if-eqz v0, :cond_1

    invoke-direct {p0}, Lcom/miui/internal/widget/ActionBarView;->al()Z

    move-result v0

    if-eqz v0, :cond_1

    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hG:Lcom/miui/internal/widget/ActionBarView$HomeView;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hG:Lcom/miui/internal/widget/ActionBarView$HomeView;

    invoke-virtual {v0}, Lcom/miui/internal/widget/ActionBarView$HomeView;->getVisibility()I

    move-result v0

    const/16 v1, 0x8

    if-ne v0, v1, :cond_1

    :cond_0
    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarView;->hasEmbeddedTabs()Z

    move-result v0

    if-nez v0, :cond_1

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_1
    const/4 v0, 0x0

    goto :goto_0
.end method

.method private an()Z
    .locals 2

    .prologue
    .line 852
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hI:Landroid/widget/LinearLayout;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hI:Landroid/widget/LinearLayout;

    invoke-virtual {v0}, Landroid/widget/LinearLayout;->getVisibility()I

    move-result v0

    const/16 v1, 0x8

    if-eq v0, v1, :cond_0

    iget v0, p0, Lcom/miui/internal/widget/ActionBarView;->hy:I

    and-int/lit8 v0, v0, 0x8

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method private ao()V
    .locals 5

    .prologue
    const/4 v3, 0x3

    const/4 v2, 0x1

    .line 858
    invoke-direct {p0}, Lcom/miui/internal/widget/ActionBarView;->am()Z

    move-result v4

    .line 859
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hJ:Landroid/widget/TextView;

    invoke-virtual {v0}, Landroid/widget/TextView;->getParent()Landroid/view/ViewParent;

    move-result-object v0

    check-cast v0, Landroid/view/ViewGroup;

    .line 860
    instance-of v1, v0, Landroid/widget/LinearLayout;

    if-eqz v1, :cond_0

    .line 861
    check-cast v0, Landroid/widget/LinearLayout;

    .line 862
    if-eqz v4, :cond_2

    move v1, v2

    :goto_0
    or-int/lit8 v1, v1, 0x10

    invoke-virtual {v0, v1}, Landroid/widget/LinearLayout;->setGravity(I)V

    .line 864
    :cond_0
    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarView;->hJ:Landroid/widget/TextView;

    if-eqz v4, :cond_3

    move v0, v2

    :goto_1
    or-int/lit8 v0, v0, 0x10

    invoke-virtual {v1, v0}, Landroid/widget/TextView;->setGravity(I)V

    .line 865
    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarView;->hJ:Landroid/widget/TextView;

    if-eqz v4, :cond_4

    sget-object v0, Landroid/text/TextUtils$TruncateAt;->MIDDLE:Landroid/text/TextUtils$TruncateAt;

    :goto_2
    invoke-virtual {v1, v0}, Landroid/widget/TextView;->setEllipsize(Landroid/text/TextUtils$TruncateAt;)V

    .line 866
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hK:Landroid/widget/TextView;

    if-eqz v0, :cond_1

    .line 867
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hK:Landroid/widget/TextView;

    if-eqz v4, :cond_5

    :goto_3
    or-int/lit8 v1, v2, 0x10

    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setGravity(I)V

    .line 868
    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarView;->hK:Landroid/widget/TextView;

    if-eqz v4, :cond_6

    sget-object v0, Landroid/text/TextUtils$TruncateAt;->MIDDLE:Landroid/text/TextUtils$TruncateAt;

    :goto_4
    invoke-virtual {v1, v0}, Landroid/widget/TextView;->setEllipsize(Landroid/text/TextUtils$TruncateAt;)V

    .line 870
    :cond_1
    return-void

    :cond_2
    move v1, v3

    .line 862
    goto :goto_0

    :cond_3
    move v0, v3

    .line 864
    goto :goto_1

    .line 865
    :cond_4
    sget-object v0, Landroid/text/TextUtils$TruncateAt;->END:Landroid/text/TextUtils$TruncateAt;

    goto :goto_2

    :cond_5
    move v2, v3

    .line 867
    goto :goto_3

    .line 868
    :cond_6
    sget-object v0, Landroid/text/TextUtils$TruncateAt;->END:Landroid/text/TextUtils$TruncateAt;

    goto :goto_4
.end method

.method private ap()Landroid/widget/ProgressBar;
    .locals 2

    .prologue
    .line 1780
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hR:Landroid/widget/ProgressBar;

    .line 1781
    if-eqz v0, :cond_0

    .line 1782
    const/4 v1, 0x4

    invoke-virtual {v0, v1}, Landroid/widget/ProgressBar;->setVisibility(I)V

    .line 1784
    :cond_0
    return-object v0
.end method

.method private aq()Landroid/widget/ProgressBar;
    .locals 2

    .prologue
    .line 1788
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hQ:Landroid/widget/ProgressBar;

    .line 1789
    if-eqz v0, :cond_0

    .line 1790
    const/4 v1, 0x4

    invoke-virtual {v0, v1}, Landroid/widget/ProgressBar;->setVisibility(I)V

    .line 1792
    :cond_0
    return-object v0
.end method

.method private ar()Landroid/graphics/drawable/Drawable;
    .locals 3

    .prologue
    .line 1813
    iget v0, p0, Lcom/miui/internal/widget/ActionBarView;->hA:I

    and-int/lit8 v0, v0, 0x2

    const/4 v1, 0x2

    if-eq v0, v1, :cond_2

    .line 1814
    sget v0, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v1, 0x9

    if-lt v0, v1, :cond_1

    .line 1815
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->mContext:Landroid/content/Context;

    instance-of v0, v0, Landroid/app/Activity;

    if-eqz v0, :cond_0

    .line 1817
    :try_start_0
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->mContext:Landroid/content/Context;

    invoke-virtual {v0}, Landroid/content/Context;->getPackageManager()Landroid/content/pm/PackageManager;

    move-result-object v1

    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->mContext:Landroid/content/Context;

    check-cast v0, Landroid/app/Activity;

    invoke-virtual {v0}, Landroid/app/Activity;->getComponentName()Landroid/content/ComponentName;

    move-result-object v0

    invoke-virtual {v1, v0}, Landroid/content/pm/PackageManager;->getActivityLogo(Landroid/content/ComponentName;)Landroid/graphics/drawable/Drawable;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hC:Landroid/graphics/drawable/Drawable;
    :try_end_0
    .catch Landroid/content/pm/PackageManager$NameNotFoundException; {:try_start_0 .. :try_end_0} :catch_0

    .line 1822
    :cond_0
    :goto_0
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hC:Landroid/graphics/drawable/Drawable;

    if-nez v0, :cond_1

    .line 1823
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->mContext:Landroid/content/Context;

    invoke-virtual {v0}, Landroid/content/Context;->getApplicationInfo()Landroid/content/pm/ApplicationInfo;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarView;->mContext:Landroid/content/Context;

    invoke-virtual {v1}, Landroid/content/Context;->getPackageManager()Landroid/content/pm/PackageManager;

    move-result-object v1

    invoke-virtual {v0, v1}, Landroid/content/pm/ApplicationInfo;->loadLogo(Landroid/content/pm/PackageManager;)Landroid/graphics/drawable/Drawable;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hC:Landroid/graphics/drawable/Drawable;

    .line 1826
    :cond_1
    iget v0, p0, Lcom/miui/internal/widget/ActionBarView;->hA:I

    or-int/lit8 v0, v0, 0x2

    iput v0, p0, Lcom/miui/internal/widget/ActionBarView;->hA:I

    .line 1828
    :cond_2
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hC:Landroid/graphics/drawable/Drawable;

    return-object v0

    .line 1818
    :catch_0
    move-exception v0

    .line 1819
    const-string v1, "ActionBarView"

    const-string v2, "Activity component name not found!"

    invoke-static {v1, v2, v0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    goto :goto_0
.end method

.method private as()V
    .locals 4

    .prologue
    const/4 v3, 0x1

    const/4 v2, 0x0

    .line 1832
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hG:Lcom/miui/internal/widget/ActionBarView$HomeView;

    if-nez v0, :cond_2

    .line 1833
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->mContext:Landroid/content/Context;

    invoke-static {v0}, Landroid/view/LayoutInflater;->from(Landroid/content/Context;)Landroid/view/LayoutInflater;

    move-result-object v0

    iget v1, p0, Lcom/miui/internal/widget/ActionBarView;->hD:I

    invoke-virtual {v0, v1, p0, v2}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;Z)Landroid/view/View;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/widget/ActionBarView$HomeView;

    iput-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hG:Lcom/miui/internal/widget/ActionBarView$HomeView;

    .line 1834
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hG:Lcom/miui/internal/widget/ActionBarView$HomeView;

    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarView;->im:Landroid/view/View$OnClickListener;

    invoke-virtual {v0, v1}, Lcom/miui/internal/widget/ActionBarView$HomeView;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 1835
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hG:Lcom/miui/internal/widget/ActionBarView$HomeView;

    invoke-virtual {v0, v3}, Lcom/miui/internal/widget/ActionBarView$HomeView;->setClickable(Z)V

    .line 1836
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hG:Lcom/miui/internal/widget/ActionBarView$HomeView;

    invoke-virtual {v0, v3}, Lcom/miui/internal/widget/ActionBarView$HomeView;->setFocusable(Z)V

    .line 1838
    iget v0, p0, Lcom/miui/internal/widget/ActionBarView;->hF:I

    if-eqz v0, :cond_0

    .line 1839
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hG:Lcom/miui/internal/widget/ActionBarView$HomeView;

    iget v1, p0, Lcom/miui/internal/widget/ActionBarView;->hF:I

    invoke-virtual {v0, v1}, Lcom/miui/internal/widget/ActionBarView$HomeView;->G(I)V

    .line 1840
    iput v2, p0, Lcom/miui/internal/widget/ActionBarView;->hF:I

    .line 1843
    :cond_0
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hE:Landroid/graphics/drawable/Drawable;

    if-eqz v0, :cond_1

    .line 1844
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hG:Lcom/miui/internal/widget/ActionBarView$HomeView;

    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarView;->hE:Landroid/graphics/drawable/Drawable;

    invoke-virtual {v0, v1}, Lcom/miui/internal/widget/ActionBarView$HomeView;->b(Landroid/graphics/drawable/Drawable;)V

    .line 1845
    const/4 v0, 0x0

    iput-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hE:Landroid/graphics/drawable/Drawable;

    .line 1848
    :cond_1
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hG:Lcom/miui/internal/widget/ActionBarView$HomeView;

    invoke-virtual {p0, v0}, Lcom/miui/internal/widget/ActionBarView;->addView(Landroid/view/View;)V

    .line 1850
    :cond_2
    return-void
.end method

.method private at()V
    .locals 3

    .prologue
    .line 1853
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hH:Lcom/miui/internal/widget/ActionBarView$HomeView;

    if-nez v0, :cond_0

    .line 1854
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->mContext:Landroid/content/Context;

    invoke-static {v0}, Landroid/view/LayoutInflater;->from(Landroid/content/Context;)Landroid/view/LayoutInflater;

    move-result-object v0

    iget v1, p0, Lcom/miui/internal/widget/ActionBarView;->hD:I

    const/4 v2, 0x0

    invoke-virtual {v0, v1, p0, v2}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;Z)Landroid/view/View;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/widget/ActionBarView$HomeView;

    iput-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hH:Lcom/miui/internal/widget/ActionBarView$HomeView;

    .line 1855
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hH:Lcom/miui/internal/widget/ActionBarView$HomeView;

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Lcom/miui/internal/widget/ActionBarView$HomeView;->h(Z)V

    .line 1856
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hH:Lcom/miui/internal/widget/ActionBarView$HomeView;

    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarView;->il:Landroid/view/View$OnClickListener;

    invoke-virtual {v0, v1}, Lcom/miui/internal/widget/ActionBarView$HomeView;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 1858
    :cond_0
    return-void
.end method

.method static synthetic b(Lcom/miui/internal/widget/ActionBarView;)Lcom/miui/internal/widget/ActionBarView$a;
    .locals 1

    .prologue
    .line 54
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->ig:Lcom/miui/internal/widget/ActionBarView$a;

    return-object v0
.end method

.method private b(Landroid/widget/ProgressBar;Landroid/widget/ProgressBar;)V
    .locals 2

    .prologue
    const/4 v1, 0x4

    .line 1771
    if-eqz p2, :cond_0

    invoke-virtual {p2}, Landroid/widget/ProgressBar;->getVisibility()I

    move-result v0

    if-nez v0, :cond_0

    .line 1772
    invoke-virtual {p2, v1}, Landroid/widget/ProgressBar;->setVisibility(I)V

    .line 1774
    :cond_0
    if-eqz p1, :cond_1

    invoke-virtual {p1}, Landroid/widget/ProgressBar;->getVisibility()I

    move-result v0

    if-nez v0, :cond_1

    .line 1775
    invoke-virtual {p1, v1}, Landroid/widget/ProgressBar;->setVisibility(I)V

    .line 1777
    :cond_1
    return-void
.end method

.method static synthetic c(Lcom/miui/internal/widget/ActionBarView;)Lcom/miui/internal/view/menu/ActionMenuItem;
    .locals 1

    .prologue
    .line 54
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->ic:Lcom/miui/internal/view/menu/ActionMenuItem;

    return-object v0
.end method

.method static synthetic d(Lcom/miui/internal/widget/ActionBarView;)V
    .locals 0

    .prologue
    .line 54
    invoke-direct {p0}, Lcom/miui/internal/widget/ActionBarView;->at()V

    return-void
.end method

.method static synthetic e(Lcom/miui/internal/widget/ActionBarView;)Landroid/graphics/drawable/Drawable;
    .locals 1

    .prologue
    .line 54
    invoke-direct {p0}, Lcom/miui/internal/widget/ActionBarView;->getIcon()Landroid/graphics/drawable/Drawable;

    move-result-object v0

    return-object v0
.end method

.method static synthetic f(Lcom/miui/internal/widget/ActionBarView;)Lcom/miui/internal/widget/ActionBarView$HomeView;
    .locals 1

    .prologue
    .line 54
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hH:Lcom/miui/internal/widget/ActionBarView$HomeView;

    return-object v0
.end method

.method static synthetic g(Lcom/miui/internal/widget/ActionBarView;)Lcom/miui/internal/widget/ActionBarView$HomeView;
    .locals 1

    .prologue
    .line 54
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hG:Lcom/miui/internal/widget/ActionBarView$HomeView;

    return-object v0
.end method

.method private getIcon()Landroid/graphics/drawable/Drawable;
    .locals 3

    .prologue
    .line 1796
    iget v0, p0, Lcom/miui/internal/widget/ActionBarView;->hA:I

    and-int/lit8 v0, v0, 0x1

    const/4 v1, 0x1

    if-eq v0, v1, :cond_2

    .line 1797
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->mContext:Landroid/content/Context;

    instance-of v0, v0, Landroid/app/Activity;

    if-eqz v0, :cond_0

    .line 1799
    :try_start_0
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->mContext:Landroid/content/Context;

    invoke-virtual {v0}, Landroid/content/Context;->getPackageManager()Landroid/content/pm/PackageManager;

    move-result-object v1

    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->mContext:Landroid/content/Context;

    check-cast v0, Landroid/app/Activity;

    invoke-virtual {v0}, Landroid/app/Activity;->getComponentName()Landroid/content/ComponentName;

    move-result-object v0

    invoke-virtual {v1, v0}, Landroid/content/pm/PackageManager;->getActivityIcon(Landroid/content/ComponentName;)Landroid/graphics/drawable/Drawable;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hB:Landroid/graphics/drawable/Drawable;
    :try_end_0
    .catch Landroid/content/pm/PackageManager$NameNotFoundException; {:try_start_0 .. :try_end_0} :catch_0

    .line 1804
    :cond_0
    :goto_0
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hB:Landroid/graphics/drawable/Drawable;

    if-nez v0, :cond_1

    .line 1805
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->mContext:Landroid/content/Context;

    invoke-virtual {v0}, Landroid/content/Context;->getApplicationInfo()Landroid/content/pm/ApplicationInfo;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarView;->mContext:Landroid/content/Context;

    invoke-virtual {v1}, Landroid/content/Context;->getPackageManager()Landroid/content/pm/PackageManager;

    move-result-object v1

    invoke-virtual {v0, v1}, Landroid/content/pm/ApplicationInfo;->loadIcon(Landroid/content/pm/PackageManager;)Landroid/graphics/drawable/Drawable;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hB:Landroid/graphics/drawable/Drawable;

    .line 1807
    :cond_1
    iget v0, p0, Lcom/miui/internal/widget/ActionBarView;->hA:I

    or-int/lit8 v0, v0, 0x1

    iput v0, p0, Lcom/miui/internal/widget/ActionBarView;->hA:I

    .line 1809
    :cond_2
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hB:Landroid/graphics/drawable/Drawable;

    return-object v0

    .line 1800
    :catch_0
    move-exception v0

    .line 1801
    const-string v1, "ActionBarView"

    const-string v2, "Activity component name not found!"

    invoke-static {v1, v2, v0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    goto :goto_0
.end method

.method static synthetic h(Lcom/miui/internal/widget/ActionBarView;)Landroid/widget/LinearLayout;
    .locals 1

    .prologue
    .line 54
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hI:Landroid/widget/LinearLayout;

    return-object v0
.end method

.method static synthetic i(Lcom/miui/internal/widget/ActionBarView;)Lcom/miui/internal/widget/ScrollingTabContainerView;
    .locals 1

    .prologue
    .line 54
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hO:Lcom/miui/internal/widget/ScrollingTabContainerView;

    return-object v0
.end method

.method private initTitle()V
    .locals 7

    .prologue
    const/16 v5, 0x8

    const/4 v1, 0x1

    const/4 v2, 0x0

    .line 773
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hI:Landroid/widget/LinearLayout;

    if-nez v0, :cond_4

    .line 774
    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarView;->getContext()Landroid/content/Context;

    move-result-object v0

    invoke-static {v0}, Landroid/view/LayoutInflater;->from(Landroid/content/Context;)Landroid/view/LayoutInflater;

    move-result-object v0

    .line 775
    sget v3, Lcom/miui/internal/R$layout;->action_bar_title_item:I

    invoke-virtual {v0, v3, p0, v2}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;Z)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/LinearLayout;

    iput-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hI:Landroid/widget/LinearLayout;

    .line 777
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hI:Landroid/widget/LinearLayout;

    sget v3, Lcom/miui/internal/R$id;->action_bar_title:I

    invoke-virtual {v0, v3}, Landroid/widget/LinearLayout;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/TextView;

    iput-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hJ:Landroid/widget/TextView;

    .line 778
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hI:Landroid/widget/LinearLayout;

    sget v3, Lcom/miui/internal/R$id;->action_bar_subtitle:I

    invoke-virtual {v0, v3}, Landroid/widget/LinearLayout;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/TextView;

    iput-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hK:Landroid/widget/TextView;

    .line 779
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hI:Landroid/widget/LinearLayout;

    sget v3, Lcom/miui/internal/R$id;->up:I

    invoke-virtual {v0, v3}, Landroid/widget/LinearLayout;->findViewById(I)Landroid/view/View;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hL:Landroid/view/View;

    .line 781
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hI:Landroid/widget/LinearLayout;

    iget-object v3, p0, Lcom/miui/internal/widget/ActionBarView;->im:Landroid/view/View$OnClickListener;

    invoke-virtual {v0, v3}, Landroid/widget/LinearLayout;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 783
    iget v0, p0, Lcom/miui/internal/widget/ActionBarView;->hV:I

    if-eqz v0, :cond_0

    .line 784
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hJ:Landroid/widget/TextView;

    iget-object v3, p0, Lcom/miui/internal/widget/ActionBarView;->mContext:Landroid/content/Context;

    iget v4, p0, Lcom/miui/internal/widget/ActionBarView;->hV:I

    invoke-virtual {v0, v3, v4}, Landroid/widget/TextView;->setTextAppearance(Landroid/content/Context;I)V

    .line 786
    :cond_0
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->mTitle:Ljava/lang/CharSequence;

    if-eqz v0, :cond_1

    .line 787
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hJ:Landroid/widget/TextView;

    iget-object v3, p0, Lcom/miui/internal/widget/ActionBarView;->mTitle:Ljava/lang/CharSequence;

    invoke-virtual {v0, v3}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 790
    :cond_1
    iget v0, p0, Lcom/miui/internal/widget/ActionBarView;->hW:I

    if-eqz v0, :cond_2

    .line 791
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hK:Landroid/widget/TextView;

    iget-object v3, p0, Lcom/miui/internal/widget/ActionBarView;->mContext:Landroid/content/Context;

    iget v4, p0, Lcom/miui/internal/widget/ActionBarView;->hW:I

    invoke-virtual {v0, v3, v4}, Landroid/widget/TextView;->setTextAppearance(Landroid/content/Context;I)V

    .line 793
    :cond_2
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hz:Ljava/lang/CharSequence;

    if-eqz v0, :cond_3

    .line 794
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hK:Landroid/widget/TextView;

    iget-object v3, p0, Lcom/miui/internal/widget/ActionBarView;->hz:Ljava/lang/CharSequence;

    invoke-virtual {v0, v3}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 795
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hK:Landroid/widget/TextView;

    invoke-virtual {v0, v2}, Landroid/widget/TextView;->setVisibility(I)V

    .line 798
    :cond_3
    iget v0, p0, Lcom/miui/internal/widget/ActionBarView;->hy:I

    and-int/lit8 v0, v0, 0x4

    if-eqz v0, :cond_7

    move v0, v1

    .line 799
    :goto_0
    iget v3, p0, Lcom/miui/internal/widget/ActionBarView;->hy:I

    and-int/lit8 v3, v3, 0x2

    if-eqz v3, :cond_8

    move v3, v1

    .line 800
    :goto_1
    iget-object v6, p0, Lcom/miui/internal/widget/ActionBarView;->hL:Landroid/view/View;

    if-nez v3, :cond_a

    if-eqz v0, :cond_9

    move v4, v2

    :goto_2
    invoke-virtual {v6, v4}, Landroid/view/View;->setVisibility(I)V

    .line 801
    iget-object v4, p0, Lcom/miui/internal/widget/ActionBarView;->hI:Landroid/widget/LinearLayout;

    if-eqz v0, :cond_b

    if-nez v3, :cond_b

    :goto_3
    invoke-virtual {v4, v1}, Landroid/widget/LinearLayout;->setEnabled(Z)V

    .line 803
    invoke-direct {p0}, Lcom/miui/internal/widget/ActionBarView;->ak()V

    .line 806
    :cond_4
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hI:Landroid/widget/LinearLayout;

    invoke-virtual {p0, v0}, Lcom/miui/internal/widget/ActionBarView;->addView(Landroid/view/View;)V

    .line 807
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->ii:Landroid/view/View;

    if-nez v0, :cond_5

    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->mTitle:Ljava/lang/CharSequence;

    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_6

    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hz:Ljava/lang/CharSequence;

    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_6

    .line 810
    :cond_5
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hI:Landroid/widget/LinearLayout;

    invoke-virtual {v0, v5}, Landroid/widget/LinearLayout;->setVisibility(I)V

    .line 812
    :cond_6
    return-void

    :cond_7
    move v0, v2

    .line 798
    goto :goto_0

    :cond_8
    move v3, v2

    .line 799
    goto :goto_1

    .line 800
    :cond_9
    const/4 v4, 0x4

    goto :goto_2

    :cond_a
    move v4, v5

    goto :goto_2

    :cond_b
    move v1, v2

    .line 801
    goto :goto_3
.end method

.method static synthetic j(Lcom/miui/internal/widget/ActionBarView;)Landroid/widget/Spinner;
    .locals 1

    .prologue
    .line 54
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hM:Landroid/widget/Spinner;

    return-object v0
.end method

.method static synthetic k(Lcom/miui/internal/widget/ActionBarView;)Landroid/view/View;
    .locals 1

    .prologue
    .line 54
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hP:Landroid/view/View;

    return-object v0
.end method

.method static synthetic l(Lcom/miui/internal/widget/ActionBarView;)I
    .locals 1

    .prologue
    .line 54
    iget v0, p0, Lcom/miui/internal/widget/ActionBarView;->hy:I

    return v0
.end method

.method static synthetic m(Lcom/miui/internal/widget/ActionBarView;)V
    .locals 0

    .prologue
    .line 54
    invoke-direct {p0}, Lcom/miui/internal/widget/ActionBarView;->initTitle()V

    return-void
.end method

.method static synthetic n(Lcom/miui/internal/widget/ActionBarView;)I
    .locals 1

    .prologue
    .line 54
    iget v0, p0, Lcom/miui/internal/widget/ActionBarView;->hx:I

    return v0
.end method

.method private z(I)V
    .locals 6

    .prologue
    const/16 v4, 0x8

    const/16 v5, 0x2710

    const/4 v1, 0x0

    .line 1720
    invoke-direct {p0}, Lcom/miui/internal/widget/ActionBarView;->ap()Landroid/widget/ProgressBar;

    move-result-object v2

    .line 1721
    invoke-direct {p0}, Lcom/miui/internal/widget/ActionBarView;->aq()Landroid/widget/ProgressBar;

    move-result-object v3

    .line 1723
    const/4 v0, -0x1

    if-ne p1, v0, :cond_4

    .line 1724
    if-eqz v3, :cond_1

    .line 1725
    invoke-virtual {v3}, Landroid/widget/ProgressBar;->getProgress()I

    move-result v0

    .line 1726
    invoke-virtual {v3}, Landroid/widget/ProgressBar;->isIndeterminate()Z

    move-result v4

    if-nez v4, :cond_0

    if-ge v0, v5, :cond_3

    :cond_0
    move v0, v1

    .line 1728
    :goto_0
    invoke-virtual {v3, v0}, Landroid/widget/ProgressBar;->setVisibility(I)V

    .line 1730
    :cond_1
    if-eqz v2, :cond_2

    .line 1731
    invoke-virtual {v2, v1}, Landroid/widget/ProgressBar;->setVisibility(I)V

    .line 1756
    :cond_2
    :goto_1
    return-void

    .line 1726
    :cond_3
    const/4 v0, 0x4

    goto :goto_0

    .line 1733
    :cond_4
    const/4 v0, -0x2

    if-ne p1, v0, :cond_6

    .line 1734
    if-eqz v3, :cond_5

    .line 1735
    invoke-virtual {v3, v4}, Landroid/widget/ProgressBar;->setVisibility(I)V

    .line 1737
    :cond_5
    if-eqz v2, :cond_2

    .line 1738
    invoke-virtual {v2, v4}, Landroid/widget/ProgressBar;->setVisibility(I)V

    goto :goto_1

    .line 1740
    :cond_6
    const/4 v0, -0x3

    if-ne p1, v0, :cond_7

    .line 1741
    const/4 v0, 0x1

    invoke-virtual {v3, v0}, Landroid/widget/ProgressBar;->setIndeterminate(Z)V

    goto :goto_1

    .line 1742
    :cond_7
    const/4 v0, -0x4

    if-ne p1, v0, :cond_8

    .line 1743
    invoke-virtual {v3, v1}, Landroid/widget/ProgressBar;->setIndeterminate(Z)V

    goto :goto_1

    .line 1744
    :cond_8
    if-ltz p1, :cond_2

    if-gt p1, v5, :cond_2

    .line 1748
    add-int/lit8 v0, p1, 0x0

    invoke-virtual {v3, v0}, Landroid/widget/ProgressBar;->setProgress(I)V

    .line 1750
    if-ge p1, v5, :cond_9

    .line 1751
    invoke-direct {p0, v3, v2}, Lcom/miui/internal/widget/ActionBarView;->a(Landroid/widget/ProgressBar;Landroid/widget/ProgressBar;)V

    goto :goto_1

    .line 1753
    :cond_9
    invoke-direct {p0, v3, v2}, Lcom/miui/internal/widget/ActionBarView;->b(Landroid/widget/ProgressBar;Landroid/widget/ProgressBar;)V

    goto :goto_1
.end method


# virtual methods
.method public bridge synthetic animateToVisibility(I)V
    .locals 0

    .prologue
    .line 54
    invoke-super {p0, p1}, Lcom/miui/internal/widget/g;->animateToVisibility(I)V

    return-void
.end method

.method public collapseActionView()V
    .locals 1

    .prologue
    .line 475
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->ig:Lcom/miui/internal/widget/ActionBarView$a;

    if-nez v0, :cond_1

    const/4 v0, 0x0

    .line 477
    :goto_0
    if-eqz v0, :cond_0

    .line 478
    invoke-virtual {v0}, Lcom/miui/internal/view/menu/MenuItemImpl;->collapseActionView()Z

    .line 480
    :cond_0
    return-void

    .line 475
    :cond_1
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->ig:Lcom/miui/internal/widget/ActionBarView$a;

    iget-object v0, v0, Lcom/miui/internal/widget/ActionBarView$a;->mS:Lcom/miui/internal/view/menu/MenuItemImpl;

    goto :goto_0
.end method

.method protected createActionMenuPresenter(Lcom/miui/internal/view/menu/MenuPresenter$Callback;)Lcom/miui/internal/view/menu/ActionMenuPresenter;
    .locals 6

    .prologue
    .line 1678
    new-instance v0, Lcom/miui/internal/view/menu/ActionMenuPresenter;

    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarView;->mContext:Landroid/content/Context;

    sget v2, Lcom/miui/internal/R$layout;->action_menu_layout:I

    sget v3, Lcom/miui/internal/R$layout;->action_menu_item_layout:I

    sget v4, Lcom/miui/internal/R$layout;->action_bar_expanded_menu_layout:I

    sget v5, Lcom/miui/internal/R$layout;->action_bar_list_menu_item_layout:I

    invoke-direct/range {v0 .. v5}, Lcom/miui/internal/view/menu/ActionMenuPresenter;-><init>(Landroid/content/Context;IIII)V

    .line 1683
    invoke-virtual {v0, p1}, Lcom/miui/internal/view/menu/ActionMenuPresenter;->setCallback(Lcom/miui/internal/view/menu/MenuPresenter$Callback;)V

    .line 1684
    sget v1, Lcom/miui/internal/R$id;->action_menu_presenter:I

    invoke-virtual {v0, v1}, Lcom/miui/internal/view/menu/ActionMenuPresenter;->setId(I)V

    .line 1685
    return-object v0
.end method

.method protected createExpandedActionViewMenuPresenter()Lcom/miui/internal/widget/ActionBarView$a;
    .locals 2

    .prologue
    .line 1689
    new-instance v0, Lcom/miui/internal/widget/ActionBarView$a;

    const/4 v1, 0x0

    invoke-direct {v0, p0, v1}, Lcom/miui/internal/widget/ActionBarView$a;-><init>(Lcom/miui/internal/widget/ActionBarView;Lcom/miui/internal/widget/d;)V

    return-object v0
.end method

.method public bridge synthetic dismissPopupMenus()V
    .locals 0

    .prologue
    .line 54
    invoke-super {p0}, Lcom/miui/internal/widget/g;->dismissPopupMenus()V

    return-void
.end method

.method protected generateDefaultLayoutParams()Landroid/view/ViewGroup$LayoutParams;
    .locals 2

    .prologue
    .line 754
    new-instance v0, Landroid/app/ActionBar$LayoutParams;

    const/16 v1, 0x13

    invoke-direct {v0, v1}, Landroid/app/ActionBar$LayoutParams;-><init>(I)V

    return-object v0
.end method

.method public generateLayoutParams(Landroid/util/AttributeSet;)Landroid/view/ViewGroup$LayoutParams;
    .locals 2

    .prologue
    .line 1299
    new-instance v0, Landroid/app/ActionBar$LayoutParams;

    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarView;->getContext()Landroid/content/Context;

    move-result-object v1

    invoke-direct {v0, v1, p1}, Landroid/app/ActionBar$LayoutParams;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    return-object v0
.end method

.method public generateLayoutParams(Landroid/view/ViewGroup$LayoutParams;)Landroid/view/ViewGroup$LayoutParams;
    .locals 0

    .prologue
    .line 1304
    if-nez p1, :cond_0

    .line 1305
    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarView;->generateDefaultLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object p1

    .line 1307
    :cond_0
    return-object p1
.end method

.method public bridge synthetic getActionMenuView()Lcom/miui/internal/view/menu/ActionMenuView;
    .locals 1

    .prologue
    .line 54
    invoke-super {p0}, Lcom/miui/internal/widget/g;->getActionMenuView()Lcom/miui/internal/view/menu/ActionMenuView;

    move-result-object v0

    return-object v0
.end method

.method public bridge synthetic getAnimatedVisibility()I
    .locals 1

    .prologue
    .line 54
    invoke-super {p0}, Lcom/miui/internal/widget/g;->getAnimatedVisibility()I

    move-result v0

    return v0
.end method

.method public bridge synthetic getContentHeight()I
    .locals 1

    .prologue
    .line 54
    invoke-super {p0}, Lcom/miui/internal/widget/g;->getContentHeight()I

    move-result v0

    return v0
.end method

.method public getCustomNavigationView()Landroid/view/View;
    .locals 1

    .prologue
    .line 739
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hP:Landroid/view/View;

    return-object v0
.end method

.method public getDisplayOptions()I
    .locals 1

    .prologue
    .line 747
    iget v0, p0, Lcom/miui/internal/widget/ActionBarView;->hy:I

    return v0
.end method

.method public getDropdownAdapter()Landroid/widget/SpinnerAdapter;
    .locals 1

    .prologue
    .line 727
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->ie:Landroid/widget/SpinnerAdapter;

    return-object v0
.end method

.method public getDropdownSelectedPosition()I
    .locals 1

    .prologue
    .line 735
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hM:Landroid/widget/Spinner;

    invoke-virtual {v0}, Landroid/widget/Spinner;->getSelectedItemPosition()I

    move-result v0

    return v0
.end method

.method public bridge synthetic getMenuView()Lcom/miui/internal/view/menu/ActionMenuView;
    .locals 1

    .prologue
    .line 54
    invoke-super {p0}, Lcom/miui/internal/widget/g;->getMenuView()Lcom/miui/internal/view/menu/ActionMenuView;

    move-result-object v0

    return-object v0
.end method

.method public getNavigationMode()I
    .locals 1

    .prologue
    .line 743
    iget v0, p0, Lcom/miui/internal/widget/ActionBarView;->hx:I

    return v0
.end method

.method public getSplitActionBarHeight(Z)I
    .locals 2

    .prologue
    const/4 v0, 0x0

    .line 357
    if-eqz p1, :cond_1

    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarView;->mSplitView:Lcom/miui/internal/widget/ActionBarContainer;

    if-eqz v1, :cond_0

    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->mSplitView:Lcom/miui/internal/widget/ActionBarContainer;

    invoke-virtual {v0}, Lcom/miui/internal/widget/ActionBarContainer;->getContentHeight()I

    move-result v0

    :cond_0
    :goto_0
    return v0

    :cond_1
    iget-boolean v1, p0, Lcom/miui/internal/widget/ActionBarView;->mSplitActionBar:Z

    if-eqz v1, :cond_0

    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->mSplitView:Lcom/miui/internal/widget/ActionBarContainer;

    invoke-virtual {v0}, Lcom/miui/internal/widget/ActionBarContainer;->getHeight()I

    move-result v0

    goto :goto_0
.end method

.method public getSubtitle()Ljava/lang/CharSequence;
    .locals 1

    .prologue
    .line 535
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hz:Ljava/lang/CharSequence;

    return-object v0
.end method

.method public getTitle()Ljava/lang/CharSequence;
    .locals 1

    .prologue
    .line 494
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->mTitle:Ljava/lang/CharSequence;

    return-object v0
.end method

.method public hasEmbeddedTabs()Z
    .locals 1

    .prologue
    .line 363
    iget-boolean v0, p0, Lcom/miui/internal/widget/ActionBarView;->hY:Z

    return v0
.end method

.method public hasExpandedActionView()Z
    .locals 1

    .prologue
    .line 470
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->ig:Lcom/miui/internal/widget/ActionBarView$a;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->ig:Lcom/miui/internal/widget/ActionBarView$a;

    iget-object v0, v0, Lcom/miui/internal/widget/ActionBarView$a;->mS:Lcom/miui/internal/view/menu/MenuItemImpl;

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public hideImmersionMore()Z
    .locals 2

    .prologue
    .line 306
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hS:Landroid/view/View;

    if-eqz v0, :cond_0

    .line 307
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hS:Landroid/view/View;

    const/16 v1, 0x8

    invoke-virtual {v0, v1}, Landroid/view/View;->setVisibility(I)V

    .line 308
    const/4 v0, 0x1

    .line 310
    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public bridge synthetic hideOverflowMenu()Z
    .locals 1

    .prologue
    .line 54
    invoke-super {p0}, Lcom/miui/internal/widget/g;->hideOverflowMenu()Z

    move-result v0

    return v0
.end method

.method public initImmersionMore(ILcom/miui/internal/app/ActionBarDelegateImpl;)V
    .locals 3

    .prologue
    .line 270
    if-gtz p1, :cond_1

    .line 271
    const-string v0, "ActionBarView"

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "Try to initialize invalid layout for immersion more button: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    .line 295
    :cond_0
    :goto_0
    return-void

    .line 275
    :cond_1
    iget v0, p0, Lcom/miui/internal/widget/ActionBarView;->hy:I

    and-int/lit8 v0, v0, 0x10

    if-eqz v0, :cond_2

    .line 276
    const-string v0, "ActionBarView"

    const-string v1, "Don\'t show immersion menu button for custom action bar"

    invoke-static {v0, v1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    goto :goto_0

    .line 278
    :cond_2
    iget v0, p0, Lcom/miui/internal/widget/ActionBarView;->hy:I

    if-nez v0, :cond_3

    .line 279
    const-string v0, "ActionBarView"

    const-string v1, "Don\'t show immersion menu button for null display option"

    invoke-static {v0, v1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    goto :goto_0

    .line 283
    :cond_3
    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarView;->getContext()Landroid/content/Context;

    move-result-object v0

    invoke-static {v0}, Landroid/view/LayoutInflater;->from(Landroid/content/Context;)Landroid/view/LayoutInflater;

    move-result-object v0

    const/4 v1, 0x0

    invoke-virtual {v0, p1, p0, v1}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;Z)Landroid/view/View;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hS:Landroid/view/View;

    .line 284
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hS:Landroid/view/View;

    invoke-virtual {p0, v0}, Lcom/miui/internal/widget/ActionBarView;->addView(Landroid/view/View;)V

    .line 286
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hS:Landroid/view/View;

    sget v1, Lcom/miui/internal/R$id;->more:I

    invoke-virtual {v0, v1}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    .line 287
    if-eqz v0, :cond_0

    .line 288
    new-instance v1, Lcom/miui/internal/widget/ActionBarView$1;

    invoke-direct {v1, p0, p2, v0}, Lcom/miui/internal/widget/ActionBarView$1;-><init>(Lcom/miui/internal/widget/ActionBarView;Lcom/miui/internal/app/ActionBarDelegateImpl;Landroid/view/View;)V

    invoke-virtual {v0, v1}, Landroid/view/View;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    goto :goto_0
.end method

.method public initIndeterminateProgress()V
    .locals 4

    .prologue
    .line 262
    new-instance v0, Landroid/widget/ProgressBar;

    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarView;->mContext:Landroid/content/Context;

    const/4 v2, 0x0

    sget v3, Lmiui/R$attr;->actionBarIndeterminateProgressStyle:I

    invoke-direct {v0, v1, v2, v3}, Landroid/widget/ProgressBar;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    iput-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hR:Landroid/widget/ProgressBar;

    .line 263
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hR:Landroid/widget/ProgressBar;

    sget v1, Lcom/miui/internal/R$id;->progress_circular:I

    invoke-virtual {v0, v1}, Landroid/widget/ProgressBar;->setId(I)V

    .line 264
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hR:Landroid/widget/ProgressBar;

    const/16 v1, 0x8

    invoke-virtual {v0, v1}, Landroid/widget/ProgressBar;->setVisibility(I)V

    .line 265
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hR:Landroid/widget/ProgressBar;

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Landroid/widget/ProgressBar;->setIndeterminate(Z)V

    .line 266
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hR:Landroid/widget/ProgressBar;

    invoke-virtual {p0, v0}, Lcom/miui/internal/widget/ActionBarView;->addView(Landroid/view/View;)V

    .line 267
    return-void
.end method

.method public initProgress()V
    .locals 4

    .prologue
    .line 254
    new-instance v0, Landroid/widget/ProgressBar;

    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarView;->mContext:Landroid/content/Context;

    const/4 v2, 0x0

    sget v3, Lmiui/R$attr;->actionBarProgressStyle:I

    invoke-direct {v0, v1, v2, v3}, Landroid/widget/ProgressBar;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    iput-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hQ:Landroid/widget/ProgressBar;

    .line 255
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hQ:Landroid/widget/ProgressBar;

    sget v1, Lcom/miui/internal/R$id;->progress_horizontal:I

    invoke-virtual {v0, v1}, Landroid/widget/ProgressBar;->setId(I)V

    .line 256
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hQ:Landroid/widget/ProgressBar;

    const/16 v1, 0x2710

    invoke-virtual {v0, v1}, Landroid/widget/ProgressBar;->setMax(I)V

    .line 257
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hQ:Landroid/widget/ProgressBar;

    const/16 v1, 0x8

    invoke-virtual {v0, v1}, Landroid/widget/ProgressBar;->setVisibility(I)V

    .line 258
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hQ:Landroid/widget/ProgressBar;

    invoke-virtual {p0, v0}, Lcom/miui/internal/widget/ActionBarView;->addView(Landroid/view/View;)V

    .line 259
    return-void
.end method

.method public isCollapsed()Z
    .locals 1

    .prologue
    .line 819
    iget-boolean v0, p0, Lcom/miui/internal/widget/ActionBarView;->hZ:Z

    return v0
.end method

.method public bridge synthetic isOverflowMenuShowing()Z
    .locals 1

    .prologue
    .line 54
    invoke-super {p0}, Lcom/miui/internal/widget/g;->isOverflowMenuShowing()Z

    move-result v0

    return v0
.end method

.method public bridge synthetic isOverflowReserved()Z
    .locals 1

    .prologue
    .line 54
    invoke-super {p0}, Lcom/miui/internal/widget/g;->isOverflowReserved()Z

    move-result v0

    return v0
.end method

.method public isSplitActionBar()Z
    .locals 1

    .prologue
    .line 353
    iget-boolean v0, p0, Lcom/miui/internal/widget/ActionBarView;->mSplitActionBar:Z

    return v0
.end method

.method protected onConfigurationChanged(Landroid/content/res/Configuration;)V
    .locals 2

    .prologue
    const/4 v1, 0x0

    .line 209
    invoke-super {p0, p1}, Lcom/miui/internal/widget/g;->onConfigurationChanged(Landroid/content/res/Configuration;)V

    .line 211
    iput-object v1, p0, Lcom/miui/internal/widget/ActionBarView;->hJ:Landroid/widget/TextView;

    .line 212
    iput-object v1, p0, Lcom/miui/internal/widget/ActionBarView;->hK:Landroid/widget/TextView;

    .line 213
    iput-object v1, p0, Lcom/miui/internal/widget/ActionBarView;->hL:Landroid/view/View;

    .line 214
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hI:Landroid/widget/LinearLayout;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hI:Landroid/widget/LinearLayout;

    invoke-virtual {v0}, Landroid/widget/LinearLayout;->getParent()Landroid/view/ViewParent;

    move-result-object v0

    if-ne v0, p0, :cond_0

    .line 215
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hI:Landroid/widget/LinearLayout;

    invoke-virtual {p0, v0}, Lcom/miui/internal/widget/ActionBarView;->removeView(Landroid/view/View;)V

    .line 217
    :cond_0
    iput-object v1, p0, Lcom/miui/internal/widget/ActionBarView;->hI:Landroid/widget/LinearLayout;

    .line 218
    iget v0, p0, Lcom/miui/internal/widget/ActionBarView;->hy:I

    and-int/lit8 v0, v0, 0x8

    if-eqz v0, :cond_1

    .line 219
    invoke-direct {p0}, Lcom/miui/internal/widget/ActionBarView;->initTitle()V

    .line 222
    :cond_1
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hO:Lcom/miui/internal/widget/ScrollingTabContainerView;

    if-eqz v0, :cond_2

    iget-boolean v0, p0, Lcom/miui/internal/widget/ActionBarView;->hY:Z

    if-eqz v0, :cond_2

    .line 223
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hO:Lcom/miui/internal/widget/ScrollingTabContainerView;

    invoke-virtual {v0}, Lcom/miui/internal/widget/ScrollingTabContainerView;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v0

    .line 224
    if-eqz v0, :cond_2

    .line 225
    const/4 v1, -0x2

    iput v1, v0, Landroid/view/ViewGroup$LayoutParams;->width:I

    .line 226
    const/4 v1, -0x1

    iput v1, v0, Landroid/view/ViewGroup$LayoutParams;->height:I

    .line 229
    :cond_2
    return-void
.end method

.method public onDetachedFromWindow()V
    .locals 2

    .prologue
    .line 242
    invoke-super {p0}, Lcom/miui/internal/widget/g;->onDetachedFromWindow()V

    .line 243
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->mActionMenuPresenter:Lcom/miui/internal/view/menu/ActionMenuPresenter;

    if-eqz v0, :cond_0

    .line 244
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->mActionMenuPresenter:Lcom/miui/internal/view/menu/ActionMenuPresenter;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Lcom/miui/internal/view/menu/ActionMenuPresenter;->hideOverflowMenu(Z)Z

    .line 245
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->mActionMenuPresenter:Lcom/miui/internal/view/menu/ActionMenuPresenter;

    invoke-virtual {v0}, Lcom/miui/internal/view/menu/ActionMenuPresenter;->hideSubMenus()Z

    .line 247
    :cond_0
    return-void
.end method

.method protected onFinishInflate()V
    .locals 2

    .prologue
    .line 759
    invoke-super {p0}, Lcom/miui/internal/widget/g;->onFinishInflate()V

    .line 761
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hP:Landroid/view/View;

    if-eqz v0, :cond_1

    iget v0, p0, Lcom/miui/internal/widget/ActionBarView;->hy:I

    and-int/lit8 v0, v0, 0x10

    if-eqz v0, :cond_1

    .line 762
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hP:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->getParent()Landroid/view/ViewParent;

    move-result-object v0

    .line 763
    if-eq v0, p0, :cond_1

    .line 764
    instance-of v1, v0, Landroid/view/ViewGroup;

    if-eqz v1, :cond_0

    .line 765
    check-cast v0, Landroid/view/ViewGroup;

    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarView;->hP:Landroid/view/View;

    invoke-virtual {v0, v1}, Landroid/view/ViewGroup;->removeView(Landroid/view/View;)V

    .line 767
    :cond_0
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hP:Landroid/view/View;

    invoke-virtual {p0, v0}, Lcom/miui/internal/widget/ActionBarView;->addView(Landroid/view/View;)V

    .line 770
    :cond_1
    return-void
.end method

.method protected onLayout(ZIIII)V
    .locals 10

    .prologue
    .line 1059
    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarView;->getPaddingLeft()I

    move-result v0

    .line 1060
    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarView;->getPaddingTop()I

    move-result v6

    .line 1061
    sub-int v1, p5, p3

    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarView;->getPaddingTop()I

    move-result v2

    sub-int/2addr v1, v2

    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarView;->getPaddingBottom()I

    move-result v2

    sub-int v7, v1, v2

    .line 1063
    if-gtz v7, :cond_1

    .line 1295
    :cond_0
    :goto_0
    return-void

    .line 1068
    :cond_1
    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarView;->ii:Landroid/view/View;

    if-eqz v1, :cond_d

    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarView;->hH:Lcom/miui/internal/widget/ActionBarView$HomeView;

    .line 1069
    :goto_1
    if-eqz v1, :cond_25

    invoke-virtual {v1}, Lcom/miui/internal/widget/ActionBarView$HomeView;->getVisibility()I

    move-result v2

    const/16 v3, 0x8

    if-eq v2, v3, :cond_25

    .line 1070
    invoke-virtual {v1}, Lcom/miui/internal/widget/ActionBarView$HomeView;->bn()I

    move-result v2

    .line 1071
    add-int v3, v0, v2

    invoke-virtual {p0, v1, v3, v6, v7}, Lcom/miui/internal/widget/ActionBarView;->positionChild(Landroid/view/View;III)I

    move-result v1

    add-int/2addr v1, v2

    add-int/2addr v0, v1

    move v1, v0

    .line 1074
    :goto_2
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->ii:Landroid/view/View;

    if-nez v0, :cond_6

    .line 1075
    invoke-direct {p0}, Lcom/miui/internal/widget/ActionBarView;->an()Z

    move-result v8

    .line 1077
    if-eqz v8, :cond_5

    .line 1078
    invoke-direct {p0}, Lcom/miui/internal/widget/ActionBarView;->am()Z

    move-result v0

    if-eqz v0, :cond_f

    .line 1079
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hJ:Landroid/widget/TextView;

    invoke-virtual {v0}, Landroid/widget/TextView;->getMeasuredWidth()I

    move-result v0

    .line 1080
    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarView;->getWidth()I

    move-result v2

    sub-int/2addr v2, v1

    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarView;->getPaddingRight()I

    move-result v3

    sub-int/2addr v2, v3

    sub-int v0, v2, v0

    div-int/lit8 v3, v0, 0x2

    .line 1081
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hL:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->getMeasuredWidth()I

    move-result v2

    .line 1083
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hL:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v0

    .line 1084
    instance-of v4, v0, Landroid/widget/LinearLayout$LayoutParams;

    if-eqz v4, :cond_2

    .line 1085
    check-cast v0, Landroid/widget/LinearLayout$LayoutParams;

    .line 1086
    iget v4, v0, Landroid/widget/LinearLayout$LayoutParams;->leftMargin:I

    iget v0, v0, Landroid/widget/LinearLayout$LayoutParams;->rightMargin:I

    add-int/2addr v0, v4

    add-int/2addr v0, v2

    move v2, v0

    .line 1089
    :cond_2
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hJ:Landroid/widget/TextView;

    invoke-virtual {v0}, Landroid/widget/TextView;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v0

    .line 1090
    instance-of v4, v0, Landroid/widget/LinearLayout$LayoutParams;

    if-eqz v4, :cond_3

    .line 1091
    check-cast v0, Landroid/widget/LinearLayout$LayoutParams;

    .line 1092
    iget v0, v0, Landroid/widget/LinearLayout$LayoutParams;->leftMargin:I

    add-int/2addr v2, v0

    .line 1095
    :cond_3
    sub-int v4, v3, v2

    .line 1097
    add-int v0, v1, v4

    iget-object v3, p0, Lcom/miui/internal/widget/ActionBarView;->hI:Landroid/widget/LinearLayout;

    invoke-virtual {v3}, Landroid/widget/LinearLayout;->getMeasuredWidth()I

    move-result v3

    add-int v5, v0, v3

    .line 1099
    const/4 v0, 0x0

    .line 1100
    iget-object v3, p0, Lcom/miui/internal/widget/ActionBarView;->hP:Landroid/view/View;

    if-eqz v3, :cond_4

    .line 1101
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hP:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v0

    .line 1102
    instance-of v3, v0, Landroid/app/ActionBar$LayoutParams;

    if-eqz v3, :cond_e

    check-cast v0, Landroid/app/ActionBar$LayoutParams;

    .line 1104
    :goto_3
    iget-object v3, p0, Lcom/miui/internal/widget/ActionBarView;->hP:Landroid/view/View;

    invoke-virtual {v3}, Landroid/view/View;->getMeasuredWidth()I

    move-result v3

    .line 1105
    if-eqz v0, :cond_24

    .line 1106
    iget v9, v0, Landroid/app/ActionBar$LayoutParams;->leftMargin:I

    iget v0, v0, Landroid/app/ActionBar$LayoutParams;->rightMargin:I

    add-int/2addr v0, v9

    add-int/2addr v0, v3

    .line 1110
    :cond_4
    :goto_4
    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarView;->getWidth()I

    move-result v3

    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarView;->getPaddingRight()I

    move-result v9

    sub-int/2addr v3, v9

    sub-int/2addr v3, v0

    .line 1111
    if-le v5, v3, :cond_23

    .line 1112
    add-int v0, v4, v5

    div-int/lit8 v0, v0, 0x2

    if-ge v3, v0, :cond_22

    .line 1113
    sub-int v0, v1, v2

    .line 1116
    :goto_5
    iget-object v2, p0, Lcom/miui/internal/widget/ActionBarView;->hI:Landroid/widget/LinearLayout;

    sub-int v4, v3, v0

    iget v5, p0, Lcom/miui/internal/widget/ActionBarView;->mContentHeight:I

    const/high16 v9, 0x40000000

    invoke-static {v5, v9}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result v5

    const/4 v9, 0x0

    invoke-virtual {p0, v2, v4, v5, v9}, Lcom/miui/internal/widget/ActionBarView;->measureChildView(Landroid/view/View;III)I

    move v4, v0

    move v0, v3

    .line 1120
    :goto_6
    iget-object v2, p0, Lcom/miui/internal/widget/ActionBarView;->hI:Landroid/widget/LinearLayout;

    invoke-virtual {v2}, Landroid/widget/LinearLayout;->getMeasuredHeight()I

    move-result v2

    .line 1121
    sub-int v3, v7, v2

    div-int/lit8 v3, v3, 0x2

    add-int/2addr v3, v6

    .line 1122
    iget-object v5, p0, Lcom/miui/internal/widget/ActionBarView;->hI:Landroid/widget/LinearLayout;

    add-int/2addr v2, v3

    invoke-virtual {v5, v4, v3, v0, v2}, Landroid/widget/LinearLayout;->layout(IIII)V

    .line 1128
    :cond_5
    :goto_7
    iget v0, p0, Lcom/miui/internal/widget/ActionBarView;->hx:I

    packed-switch v0, :pswitch_data_0

    .line 1179
    :cond_6
    :goto_8
    :pswitch_0
    sub-int v0, p4, p2

    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarView;->getPaddingRight()I

    move-result v2

    sub-int/2addr v0, v2

    .line 1180
    iget-object v2, p0, Lcom/miui/internal/widget/ActionBarView;->mMenuView:Lcom/miui/internal/view/menu/ActionMenuView;

    if-eqz v2, :cond_7

    iget-object v2, p0, Lcom/miui/internal/widget/ActionBarView;->mMenuView:Lcom/miui/internal/view/menu/ActionMenuView;

    invoke-virtual {v2}, Lcom/miui/internal/view/menu/ActionMenuView;->getParent()Landroid/view/ViewParent;

    move-result-object v2

    if-ne v2, p0, :cond_7

    .line 1181
    iget-object v2, p0, Lcom/miui/internal/widget/ActionBarView;->mMenuView:Lcom/miui/internal/view/menu/ActionMenuView;

    invoke-virtual {p0, v2, v0, v6, v7}, Lcom/miui/internal/widget/ActionBarView;->positionChildInverse(Landroid/view/View;III)I

    .line 1182
    iget-object v2, p0, Lcom/miui/internal/widget/ActionBarView;->mMenuView:Lcom/miui/internal/view/menu/ActionMenuView;

    invoke-virtual {v2}, Lcom/miui/internal/view/menu/ActionMenuView;->getMeasuredWidth()I

    move-result v2

    sub-int/2addr v0, v2

    .line 1185
    :cond_7
    iget-object v2, p0, Lcom/miui/internal/widget/ActionBarView;->hR:Landroid/widget/ProgressBar;

    if-eqz v2, :cond_8

    iget-object v2, p0, Lcom/miui/internal/widget/ActionBarView;->hR:Landroid/widget/ProgressBar;

    invoke-virtual {v2}, Landroid/widget/ProgressBar;->getVisibility()I

    move-result v2

    const/16 v3, 0x8

    if-eq v2, v3, :cond_8

    .line 1187
    iget-object v2, p0, Lcom/miui/internal/widget/ActionBarView;->hR:Landroid/widget/ProgressBar;

    iget v3, p0, Lcom/miui/internal/widget/ActionBarView;->hT:I

    sub-int v3, v0, v3

    invoke-virtual {p0, v2, v3, v6, v7}, Lcom/miui/internal/widget/ActionBarView;->positionChildInverse(Landroid/view/View;III)I

    .line 1188
    iget-object v2, p0, Lcom/miui/internal/widget/ActionBarView;->hR:Landroid/widget/ProgressBar;

    invoke-virtual {v2}, Landroid/widget/ProgressBar;->getMeasuredWidth()I

    move-result v2

    iget v3, p0, Lcom/miui/internal/widget/ActionBarView;->hT:I

    mul-int/lit8 v3, v3, 0x2

    sub-int/2addr v2, v3

    sub-int/2addr v0, v2

    .line 1191
    :cond_8
    iget-object v2, p0, Lcom/miui/internal/widget/ActionBarView;->hS:Landroid/view/View;

    if-eqz v2, :cond_1e

    iget-object v2, p0, Lcom/miui/internal/widget/ActionBarView;->hS:Landroid/view/View;

    invoke-virtual {v2}, Landroid/view/View;->getVisibility()I

    move-result v2

    const/16 v3, 0x8

    if-eq v2, v3, :cond_1e

    .line 1193
    iget-object v2, p0, Lcom/miui/internal/widget/ActionBarView;->hS:Landroid/view/View;

    invoke-virtual {p0, v2, v0, v6, v7}, Lcom/miui/internal/widget/ActionBarView;->positionChildInverse(Landroid/view/View;III)I

    .line 1194
    iget-object v2, p0, Lcom/miui/internal/widget/ActionBarView;->hS:Landroid/view/View;

    invoke-virtual {v2}, Landroid/view/View;->getMeasuredWidth()I

    move-result v2

    sub-int/2addr v0, v2

    move v2, v0

    .line 1197
    :goto_9
    const/4 v0, 0x0

    .line 1198
    iget-object v3, p0, Lcom/miui/internal/widget/ActionBarView;->ii:Landroid/view/View;

    if-eqz v3, :cond_15

    .line 1199
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->ii:Landroid/view/View;

    move-object v7, v0

    .line 1204
    :goto_a
    if-eqz v7, :cond_b

    .line 1205
    invoke-virtual {v7}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v0

    .line 1206
    instance-of v3, v0, Landroid/app/ActionBar$LayoutParams;

    if-eqz v3, :cond_16

    check-cast v0, Landroid/app/ActionBar$LayoutParams;

    move-object v5, v0

    .line 1209
    :goto_b
    if-eqz v5, :cond_17

    iget v0, v5, Landroid/app/ActionBar$LayoutParams;->gravity:I

    .line 1210
    :goto_c
    invoke-virtual {v7}, Landroid/view/View;->getMeasuredWidth()I

    move-result v8

    .line 1212
    const/4 v4, 0x0

    .line 1213
    const/4 v3, 0x0

    .line 1214
    if-eqz v5, :cond_1c

    .line 1215
    iget v3, v5, Landroid/app/ActionBar$LayoutParams;->leftMargin:I

    add-int v4, v1, v3

    .line 1216
    iget v1, v5, Landroid/app/ActionBar$LayoutParams;->rightMargin:I

    sub-int v3, v2, v1

    .line 1217
    iget v2, v5, Landroid/app/ActionBar$LayoutParams;->topMargin:I

    .line 1218
    iget v1, v5, Landroid/app/ActionBar$LayoutParams;->bottomMargin:I

    move v5, v2

    move v6, v3

    move v2, v4

    move v4, v1

    .line 1221
    :goto_d
    and-int/lit8 v1, v0, 0x7

    .line 1223
    const/4 v3, 0x1

    if-ne v1, v3, :cond_19

    .line 1224
    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarView;->getWidth()I

    move-result v3

    sub-int/2addr v3, v8

    div-int/lit8 v3, v3, 0x2

    .line 1225
    if-ge v3, v2, :cond_18

    .line 1226
    const/4 v1, 0x3

    :cond_9
    :goto_e
    move v3, v1

    .line 1234
    :goto_f
    const/4 v1, 0x0

    .line 1235
    packed-switch v3, :pswitch_data_1

    :pswitch_1
    move v2, v1

    .line 1247
    :goto_10
    :pswitch_2
    and-int/lit8 v1, v0, 0x70

    .line 1249
    const/4 v3, -0x1

    if-ne v0, v3, :cond_a

    .line 1250
    const/16 v0, 0x10

    move v1, v0

    .line 1253
    :cond_a
    const/4 v0, 0x0

    .line 1254
    sparse-switch v1, :sswitch_data_0

    .line 1268
    :goto_11
    invoke-virtual {v7}, Landroid/view/View;->getMeasuredWidth()I

    move-result v1

    .line 1269
    add-int/2addr v1, v2

    invoke-virtual {v7}, Landroid/view/View;->getMeasuredHeight()I

    move-result v3

    add-int/2addr v3, v0

    invoke-virtual {v7, v2, v0, v1, v3}, Landroid/view/View;->layout(IIII)V

    .line 1273
    :cond_b
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hQ:Landroid/widget/ProgressBar;

    if-eqz v0, :cond_c

    .line 1274
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hQ:Landroid/widget/ProgressBar;

    invoke-virtual {v0}, Landroid/widget/ProgressBar;->bringToFront()V

    .line 1275
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hQ:Landroid/widget/ProgressBar;

    invoke-virtual {v0}, Landroid/widget/ProgressBar;->getMeasuredHeight()I

    move-result v0

    div-int/lit8 v0, v0, 0x2

    .line 1276
    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarView;->hQ:Landroid/widget/ProgressBar;

    iget v2, p0, Lcom/miui/internal/widget/ActionBarView;->hT:I

    neg-int v3, v0

    iget v4, p0, Lcom/miui/internal/widget/ActionBarView;->hT:I

    iget-object v5, p0, Lcom/miui/internal/widget/ActionBarView;->hQ:Landroid/widget/ProgressBar;

    invoke-virtual {v5}, Landroid/widget/ProgressBar;->getMeasuredWidth()I

    move-result v5

    add-int/2addr v4, v5

    invoke-virtual {v1, v2, v3, v4, v0}, Landroid/widget/ProgressBar;->layout(IIII)V

    .line 1280
    :cond_c
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hL:Landroid/view/View;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hL:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->getVisibility()I

    move-result v0

    const/16 v1, 0x8

    if-eq v0, v1, :cond_0

    .line 1281
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hL:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->getHeight()I

    move-result v2

    .line 1282
    const/4 v0, 0x2

    new-array v3, v0, [I

    .line 1283
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hJ:Landroid/widget/TextView;

    invoke-virtual {v0, v3}, Landroid/widget/TextView;->getLocationInWindow([I)V

    .line 1284
    const/4 v0, 0x0

    .line 1285
    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarView;->hJ:Landroid/widget/TextView;

    invoke-virtual {v1}, Landroid/widget/TextView;->getVisibility()I

    move-result v1

    if-nez v1, :cond_1a

    .line 1286
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hJ:Landroid/widget/TextView;

    invoke-virtual {v0}, Landroid/widget/TextView;->getHeight()I

    move-result v0

    move v1, v0

    .line 1288
    :goto_12
    const/4 v0, 0x2

    new-array v4, v0, [I

    .line 1289
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hL:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->getParent()Landroid/view/ViewParent;

    move-result-object v0

    check-cast v0, Landroid/view/ViewGroup;

    check-cast v0, Landroid/view/ViewGroup;

    invoke-virtual {v0, v4}, Landroid/view/ViewGroup;->getLocationInWindow([I)V

    .line 1291
    const/4 v0, 0x1

    aget v0, v3, v0

    sub-int/2addr v1, v2

    div-int/lit8 v1, v1, 0x2

    add-int/2addr v0, v1

    const/4 v1, 0x1

    aget v1, v4, v1

    sub-int/2addr v0, v1

    .line 1292
    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarView;->hL:Landroid/view/View;

    invoke-virtual {v1, v0}, Landroid/view/View;->setTop(I)V

    .line 1293
    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarView;->hL:Landroid/view/View;

    add-int/2addr v0, v2

    invoke-virtual {v1, v0}, Landroid/view/View;->setBottom(I)V

    goto/16 :goto_0

    .line 1068
    :cond_d
    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarView;->hG:Lcom/miui/internal/widget/ActionBarView$HomeView;

    goto/16 :goto_1

    .line 1102
    :cond_e
    const/4 v0, 0x0

    goto/16 :goto_3

    .line 1124
    :cond_f
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hI:Landroid/widget/LinearLayout;

    invoke-virtual {p0, v0, v1, v6, v7}, Lcom/miui/internal/widget/ActionBarView;->positionChild(Landroid/view/View;III)I

    move-result v0

    add-int/2addr v1, v0

    goto/16 :goto_7

    .line 1132
    :pswitch_3
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hN:Landroid/widget/LinearLayout;

    if-eqz v0, :cond_6

    .line 1133
    if-eqz v8, :cond_10

    .line 1134
    iget v0, p0, Lcom/miui/internal/widget/ActionBarView;->hU:I

    add-int/2addr v1, v0

    .line 1136
    :cond_10
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hN:Landroid/widget/LinearLayout;

    invoke-virtual {p0, v0, v1, v6, v7}, Lcom/miui/internal/widget/ActionBarView;->positionChild(Landroid/view/View;III)I

    move-result v0

    iget v2, p0, Lcom/miui/internal/widget/ActionBarView;->hU:I

    add-int/2addr v0, v2

    add-int/2addr v1, v0

    goto/16 :goto_8

    .line 1140
    :pswitch_4
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hO:Lcom/miui/internal/widget/ScrollingTabContainerView;

    if-eqz v0, :cond_6

    .line 1141
    if-eqz v8, :cond_11

    .line 1142
    iget v0, p0, Lcom/miui/internal/widget/ActionBarView;->hU:I

    add-int/2addr v1, v0

    .line 1145
    :cond_11
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hO:Lcom/miui/internal/widget/ScrollingTabContainerView;

    invoke-virtual {v0}, Lcom/miui/internal/widget/ScrollingTabContainerView;->getMeasuredWidth()I

    move-result v3

    .line 1146
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hO:Lcom/miui/internal/widget/ScrollingTabContainerView;

    invoke-virtual {v0}, Lcom/miui/internal/widget/ScrollingTabContainerView;->getMeasuredHeight()I

    move-result v5

    .line 1148
    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarView;->getWidth()I

    move-result v0

    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarView;->getPaddingLeft()I

    move-result v2

    sub-int/2addr v0, v2

    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarView;->getPaddingRight()I

    move-result v2

    sub-int/2addr v0, v2

    sub-int/2addr v0, v3

    div-int/lit8 v0, v0, 0x2

    .line 1149
    if-le v1, v0, :cond_13

    move v2, v1

    .line 1151
    :goto_13
    add-int v4, v2, v3

    .line 1153
    const/4 v0, 0x0

    .line 1154
    iget-object v3, p0, Lcom/miui/internal/widget/ActionBarView;->hP:Landroid/view/View;

    if-eqz v3, :cond_12

    .line 1155
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hP:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v0

    .line 1156
    instance-of v3, v0, Landroid/app/ActionBar$LayoutParams;

    if-eqz v3, :cond_14

    check-cast v0, Landroid/app/ActionBar$LayoutParams;

    .line 1158
    :goto_14
    iget-object v3, p0, Lcom/miui/internal/widget/ActionBarView;->hP:Landroid/view/View;

    invoke-virtual {v3}, Landroid/view/View;->getMeasuredWidth()I

    move-result v3

    .line 1159
    if-eqz v0, :cond_21

    .line 1160
    iget v8, v0, Landroid/app/ActionBar$LayoutParams;->leftMargin:I

    iget v0, v0, Landroid/app/ActionBar$LayoutParams;->rightMargin:I

    add-int/2addr v0, v8

    add-int/2addr v0, v3

    .line 1164
    :cond_12
    :goto_15
    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarView;->getWidth()I

    move-result v3

    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarView;->getPaddingRight()I

    move-result v8

    sub-int/2addr v3, v8

    sub-int v0, v3, v0

    .line 1165
    if-le v4, v0, :cond_20

    .line 1166
    add-int v3, v2, v4

    div-int/lit8 v3, v3, 0x2

    if-ge v0, v3, :cond_1f

    .line 1172
    :goto_16
    iget-object v2, p0, Lcom/miui/internal/widget/ActionBarView;->hO:Lcom/miui/internal/widget/ScrollingTabContainerView;

    add-int v3, v6, v5

    invoke-virtual {v2, v1, v6, v0, v3}, Lcom/miui/internal/widget/ScrollingTabContainerView;->layout(IIII)V

    move v1, v0

    .line 1173
    goto/16 :goto_8

    :cond_13
    move v2, v0

    .line 1149
    goto :goto_13

    .line 1156
    :cond_14
    const/4 v0, 0x0

    goto :goto_14

    .line 1200
    :cond_15
    iget v3, p0, Lcom/miui/internal/widget/ActionBarView;->hy:I

    and-int/lit8 v3, v3, 0x10

    if-eqz v3, :cond_1d

    iget-object v3, p0, Lcom/miui/internal/widget/ActionBarView;->hP:Landroid/view/View;

    if-eqz v3, :cond_1d

    .line 1202
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hP:Landroid/view/View;

    move-object v7, v0

    goto/16 :goto_a

    .line 1206
    :cond_16
    const/4 v0, 0x0

    move-object v5, v0

    goto/16 :goto_b

    .line 1209
    :cond_17
    const/16 v0, 0x13

    goto/16 :goto_c

    .line 1227
    :cond_18
    add-int/2addr v3, v8

    if-le v3, v6, :cond_9

    .line 1228
    const/4 v1, 0x5

    goto/16 :goto_e

    .line 1230
    :cond_19
    const/4 v3, -0x1

    if-ne v0, v3, :cond_1b

    .line 1231
    const/4 v1, 0x3

    move v3, v1

    goto/16 :goto_f

    .line 1237
    :pswitch_5
    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarView;->getWidth()I

    move-result v1

    sub-int/2addr v1, v8

    div-int/lit8 v1, v1, 0x2

    move v2, v1

    .line 1238
    goto/16 :goto_10

    .line 1243
    :pswitch_6
    sub-int v1, v6, v8

    move v2, v1

    goto/16 :goto_10

    .line 1256
    :sswitch_0
    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarView;->getPaddingTop()I

    move-result v0

    .line 1257
    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarView;->getHeight()I

    move-result v1

    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarView;->getPaddingBottom()I

    move-result v3

    sub-int/2addr v1, v3

    .line 1258
    sub-int v0, v1, v0

    invoke-virtual {v7}, Landroid/view/View;->getMeasuredHeight()I

    move-result v1

    sub-int/2addr v0, v1

    div-int/lit8 v0, v0, 0x2

    goto/16 :goto_11

    .line 1261
    :sswitch_1
    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarView;->getPaddingTop()I

    move-result v0

    add-int/2addr v0, v5

    .line 1262
    goto/16 :goto_11

    .line 1264
    :sswitch_2
    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarView;->getHeight()I

    move-result v0

    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarView;->getPaddingBottom()I

    move-result v1

    sub-int/2addr v0, v1

    invoke-virtual {v7}, Landroid/view/View;->getMeasuredHeight()I

    move-result v1

    sub-int/2addr v0, v1

    sub-int/2addr v0, v4

    goto/16 :goto_11

    :cond_1a
    move v1, v0

    goto/16 :goto_12

    :cond_1b
    move v3, v1

    goto/16 :goto_f

    :cond_1c
    move v5, v4

    move v6, v2

    move v4, v3

    move v2, v1

    goto/16 :goto_d

    :cond_1d
    move-object v7, v0

    goto/16 :goto_a

    :cond_1e
    move v2, v0

    goto/16 :goto_9

    :cond_1f
    move v1, v2

    goto/16 :goto_16

    :cond_20
    move v0, v4

    move v1, v2

    goto/16 :goto_16

    :cond_21
    move v0, v3

    goto/16 :goto_15

    :cond_22
    move v0, v4

    goto/16 :goto_5

    :cond_23
    move v0, v5

    goto/16 :goto_6

    :cond_24
    move v0, v3

    goto/16 :goto_4

    :cond_25
    move v1, v0

    goto/16 :goto_2

    .line 1128
    nop

    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_0
        :pswitch_3
        :pswitch_4
    .end packed-switch

    .line 1235
    :pswitch_data_1
    .packed-switch 0x1
        :pswitch_5
        :pswitch_1
        :pswitch_2
        :pswitch_1
        :pswitch_6
    .end packed-switch

    .line 1254
    :sswitch_data_0
    .sparse-switch
        0x10 -> :sswitch_0
        0x30 -> :sswitch_1
        0x50 -> :sswitch_2
    .end sparse-switch
.end method

.method protected onMeasure(II)V
    .locals 20

    .prologue
    .line 874
    invoke-virtual/range {p0 .. p0}, Lcom/miui/internal/widget/ActionBarView;->getChildCount()I

    move-result v13

    .line 875
    const/4 v2, 0x0

    .line 876
    const/4 v1, 0x0

    move/from16 v19, v1

    move v1, v2

    move/from16 v2, v19

    :goto_0
    if-ge v2, v13, :cond_2

    .line 877
    move-object/from16 v0, p0

    invoke-virtual {v0, v2}, Lcom/miui/internal/widget/ActionBarView;->getChildAt(I)Landroid/view/View;

    move-result-object v3

    .line 878
    invoke-virtual {v3}, Landroid/view/View;->getVisibility()I

    move-result v4

    const/16 v5, 0x8

    if-eq v4, v5, :cond_1

    move-object/from16 v0, p0

    iget-object v4, v0, Lcom/miui/internal/widget/ActionBarView;->mMenuView:Lcom/miui/internal/view/menu/ActionMenuView;

    if-ne v3, v4, :cond_0

    move-object/from16 v0, p0

    iget-object v3, v0, Lcom/miui/internal/widget/ActionBarView;->mMenuView:Lcom/miui/internal/view/menu/ActionMenuView;

    invoke-virtual {v3}, Lcom/miui/internal/view/menu/ActionMenuView;->getChildCount()I

    move-result v3

    if-eqz v3, :cond_1

    .line 880
    :cond_0
    add-int/lit8 v1, v1, 0x1

    .line 876
    :cond_1
    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    .line 884
    :cond_2
    if-nez v1, :cond_4

    .line 886
    const/4 v1, 0x0

    const/4 v2, 0x0

    move-object/from16 v0, p0

    invoke-virtual {v0, v1, v2}, Lcom/miui/internal/widget/ActionBarView;->setMeasuredDimension(II)V

    .line 887
    const/4 v1, 0x1

    move-object/from16 v0, p0

    iput-boolean v1, v0, Lcom/miui/internal/widget/ActionBarView;->hZ:Z

    .line 1055
    :cond_3
    :goto_1
    return-void

    .line 891
    :cond_4
    const/4 v1, 0x0

    move-object/from16 v0, p0

    iput-boolean v1, v0, Lcom/miui/internal/widget/ActionBarView;->hZ:Z

    .line 893
    invoke-static/range {p1 .. p1}, Landroid/view/View$MeasureSpec;->getSize(I)I

    move-result v14

    .line 895
    move-object/from16 v0, p0

    iget v1, v0, Lcom/miui/internal/widget/ActionBarView;->mContentHeight:I

    if-lez v1, :cond_c

    move-object/from16 v0, p0

    iget v1, v0, Lcom/miui/internal/widget/ActionBarView;->mContentHeight:I

    move v3, v1

    .line 898
    :goto_2
    invoke-virtual/range {p0 .. p0}, Lcom/miui/internal/widget/ActionBarView;->getPaddingTop()I

    move-result v1

    invoke-virtual/range {p0 .. p0}, Lcom/miui/internal/widget/ActionBarView;->getPaddingBottom()I

    move-result v2

    add-int v15, v1, v2

    .line 899
    invoke-virtual/range {p0 .. p0}, Lcom/miui/internal/widget/ActionBarView;->getPaddingLeft()I

    move-result v1

    .line 900
    invoke-virtual/range {p0 .. p0}, Lcom/miui/internal/widget/ActionBarView;->getPaddingRight()I

    move-result v2

    .line 901
    sub-int v10, v3, v15

    .line 902
    const/high16 v4, -0x80000000

    invoke-static {v10, v4}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result v6

    .line 904
    sub-int v1, v14, v1

    sub-int v4, v1, v2

    .line 905
    div-int/lit8 v2, v4, 0x2

    .line 908
    move-object/from16 v0, p0

    iget-object v1, v0, Lcom/miui/internal/widget/ActionBarView;->ii:Landroid/view/View;

    if-eqz v1, :cond_d

    move-object/from16 v0, p0

    iget-object v1, v0, Lcom/miui/internal/widget/ActionBarView;->hH:Lcom/miui/internal/widget/ActionBarView$HomeView;

    move-object v5, v1

    .line 910
    :goto_3
    if-eqz v5, :cond_20

    invoke-virtual {v5}, Lcom/miui/internal/widget/ActionBarView$HomeView;->getVisibility()I

    move-result v1

    const/16 v7, 0x8

    if-eq v1, v7, :cond_20

    .line 911
    invoke-virtual {v5}, Lcom/miui/internal/widget/ActionBarView$HomeView;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v1

    .line 913
    iget v7, v1, Landroid/view/ViewGroup$LayoutParams;->width:I

    if-gez v7, :cond_e

    .line 914
    const/high16 v1, -0x80000000

    invoke-static {v4, v1}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result v1

    .line 918
    :goto_4
    const/high16 v7, 0x40000000

    invoke-static {v10, v7}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result v7

    invoke-virtual {v5, v1, v7}, Lcom/miui/internal/widget/ActionBarView$HomeView;->measure(II)V

    .line 920
    invoke-virtual {v5}, Lcom/miui/internal/widget/ActionBarView$HomeView;->getMeasuredWidth()I

    move-result v1

    invoke-virtual {v5}, Lcom/miui/internal/widget/ActionBarView$HomeView;->bn()I

    move-result v5

    add-int/2addr v1, v5

    .line 921
    const/4 v5, 0x0

    sub-int/2addr v4, v1

    invoke-static {v5, v4}, Ljava/lang/Math;->max(II)I

    move-result v4

    .line 922
    const/4 v5, 0x0

    sub-int v1, v4, v1

    invoke-static {v5, v1}, Ljava/lang/Math;->max(II)I

    move-result v1

    move/from16 v19, v1

    move v1, v4

    move/from16 v4, v19

    .line 925
    :goto_5
    move-object/from16 v0, p0

    iget-object v5, v0, Lcom/miui/internal/widget/ActionBarView;->mMenuView:Lcom/miui/internal/view/menu/ActionMenuView;

    if-eqz v5, :cond_5

    move-object/from16 v0, p0

    iget-object v5, v0, Lcom/miui/internal/widget/ActionBarView;->mMenuView:Lcom/miui/internal/view/menu/ActionMenuView;

    invoke-virtual {v5}, Lcom/miui/internal/view/menu/ActionMenuView;->getParent()Landroid/view/ViewParent;

    move-result-object v5

    move-object/from16 v0, p0

    if-ne v5, v0, :cond_5

    .line 926
    move-object/from16 v0, p0

    iget-object v5, v0, Lcom/miui/internal/widget/ActionBarView;->mMenuView:Lcom/miui/internal/view/menu/ActionMenuView;

    const/4 v7, 0x0

    move-object/from16 v0, p0

    invoke-virtual {v0, v5, v1, v6, v7}, Lcom/miui/internal/widget/ActionBarView;->measureChildView(Landroid/view/View;III)I

    move-result v1

    .line 928
    const/4 v5, 0x0

    move-object/from16 v0, p0

    iget-object v7, v0, Lcom/miui/internal/widget/ActionBarView;->mMenuView:Lcom/miui/internal/view/menu/ActionMenuView;

    invoke-virtual {v7}, Lcom/miui/internal/view/menu/ActionMenuView;->getMeasuredWidth()I

    move-result v7

    sub-int/2addr v2, v7

    invoke-static {v5, v2}, Ljava/lang/Math;->max(II)I

    move-result v2

    .line 931
    :cond_5
    move-object/from16 v0, p0

    iget-object v5, v0, Lcom/miui/internal/widget/ActionBarView;->hR:Landroid/widget/ProgressBar;

    if-eqz v5, :cond_6

    move-object/from16 v0, p0

    iget-object v5, v0, Lcom/miui/internal/widget/ActionBarView;->hR:Landroid/widget/ProgressBar;

    invoke-virtual {v5}, Landroid/widget/ProgressBar;->getVisibility()I

    move-result v5

    const/16 v7, 0x8

    if-eq v5, v7, :cond_6

    .line 933
    move-object/from16 v0, p0

    iget-object v5, v0, Lcom/miui/internal/widget/ActionBarView;->hR:Landroid/widget/ProgressBar;

    move-object/from16 v0, p0

    iget v7, v0, Lcom/miui/internal/widget/ActionBarView;->hT:I

    mul-int/lit8 v7, v7, 0x2

    move-object/from16 v0, p0

    invoke-virtual {v0, v5, v1, v6, v7}, Lcom/miui/internal/widget/ActionBarView;->measureChildView(Landroid/view/View;III)I

    move-result v1

    .line 935
    const/4 v5, 0x0

    move-object/from16 v0, p0

    iget-object v7, v0, Lcom/miui/internal/widget/ActionBarView;->hR:Landroid/widget/ProgressBar;

    invoke-virtual {v7}, Landroid/widget/ProgressBar;->getMeasuredWidth()I

    move-result v7

    sub-int/2addr v2, v7

    move-object/from16 v0, p0

    iget v7, v0, Lcom/miui/internal/widget/ActionBarView;->hT:I

    mul-int/lit8 v7, v7, 0x2

    sub-int/2addr v2, v7

    invoke-static {v5, v2}, Ljava/lang/Math;->max(II)I

    move-result v2

    .line 939
    :cond_6
    move-object/from16 v0, p0

    iget-object v5, v0, Lcom/miui/internal/widget/ActionBarView;->hS:Landroid/view/View;

    if-eqz v5, :cond_1f

    move-object/from16 v0, p0

    iget-object v5, v0, Lcom/miui/internal/widget/ActionBarView;->hS:Landroid/view/View;

    invoke-virtual {v5}, Landroid/view/View;->getVisibility()I

    move-result v5

    const/16 v7, 0x8

    if-eq v5, v7, :cond_1f

    .line 941
    move-object/from16 v0, p0

    iget-object v5, v0, Lcom/miui/internal/widget/ActionBarView;->hS:Landroid/view/View;

    const/4 v7, 0x0

    move-object/from16 v0, p0

    invoke-virtual {v0, v5, v1, v6, v7}, Lcom/miui/internal/widget/ActionBarView;->measureChildView(Landroid/view/View;III)I

    move-result v1

    .line 943
    const/4 v5, 0x0

    move-object/from16 v0, p0

    iget-object v6, v0, Lcom/miui/internal/widget/ActionBarView;->hS:Landroid/view/View;

    invoke-virtual {v6}, Landroid/view/View;->getMeasuredWidth()I

    move-result v6

    sub-int/2addr v2, v6

    invoke-static {v5, v2}, Ljava/lang/Math;->max(II)I

    move-result v2

    move v5, v2

    move v6, v1

    .line 947
    :goto_6
    invoke-direct/range {p0 .. p0}, Lcom/miui/internal/widget/ActionBarView;->an()Z

    move-result v16

    .line 949
    if-eqz v16, :cond_7

    .line 950
    invoke-direct/range {p0 .. p0}, Lcom/miui/internal/widget/ActionBarView;->ao()V

    .line 953
    :cond_7
    const/4 v1, 0x0

    .line 954
    move-object/from16 v0, p0

    iget-object v2, v0, Lcom/miui/internal/widget/ActionBarView;->ii:Landroid/view/View;

    if-eqz v2, :cond_f

    .line 955
    move-object/from16 v0, p0

    iget-object v1, v0, Lcom/miui/internal/widget/ActionBarView;->ii:Landroid/view/View;

    move-object v12, v1

    .line 961
    :goto_7
    if-eqz v12, :cond_9

    .line 962
    invoke-virtual {v12}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v1

    move-object/from16 v0, p0

    invoke-virtual {v0, v1}, Lcom/miui/internal/widget/ActionBarView;->generateLayoutParams(Landroid/view/ViewGroup$LayoutParams;)Landroid/view/ViewGroup$LayoutParams;

    move-result-object v2

    .line 963
    instance-of v1, v2, Landroid/app/ActionBar$LayoutParams;

    if-eqz v1, :cond_10

    move-object v1, v2

    check-cast v1, Landroid/app/ActionBar$LayoutParams;

    move-object v11, v1

    .line 966
    :goto_8
    const/4 v7, 0x0

    .line 967
    const/4 v1, 0x0

    .line 968
    if-eqz v11, :cond_8

    .line 969
    iget v1, v11, Landroid/app/ActionBar$LayoutParams;->leftMargin:I

    iget v7, v11, Landroid/app/ActionBar$LayoutParams;->rightMargin:I

    add-int/2addr v7, v1

    .line 970
    iget v1, v11, Landroid/app/ActionBar$LayoutParams;->topMargin:I

    iget v8, v11, Landroid/app/ActionBar$LayoutParams;->bottomMargin:I

    add-int/2addr v1, v8

    .line 976
    :cond_8
    move-object/from16 v0, p0

    iget v8, v0, Lcom/miui/internal/widget/ActionBarView;->mContentHeight:I

    if-gtz v8, :cond_11

    .line 977
    const/high16 v8, -0x80000000

    .line 982
    :goto_9
    const/16 v17, 0x0

    iget v9, v2, Landroid/view/ViewGroup$LayoutParams;->height:I

    if-ltz v9, :cond_13

    iget v9, v2, Landroid/view/ViewGroup$LayoutParams;->height:I

    invoke-static {v9, v10}, Ljava/lang/Math;->min(II)I

    move-result v9

    :goto_a
    sub-int v1, v9, v1

    move/from16 v0, v17

    invoke-static {v0, v1}, Ljava/lang/Math;->max(II)I

    move-result v17

    .line 985
    iget v1, v2, Landroid/view/ViewGroup$LayoutParams;->width:I

    const/4 v9, -0x2

    if-eq v1, v9, :cond_14

    const/high16 v1, 0x40000000

    .line 987
    :goto_b
    const/16 v18, 0x0

    iget v9, v2, Landroid/view/ViewGroup$LayoutParams;->width:I

    if-ltz v9, :cond_15

    iget v9, v2, Landroid/view/ViewGroup$LayoutParams;->width:I

    invoke-static {v9, v6}, Ljava/lang/Math;->min(II)I

    move-result v9

    :goto_c
    sub-int/2addr v9, v7

    move/from16 v0, v18

    invoke-static {v0, v9}, Ljava/lang/Math;->max(II)I

    move-result v9

    .line 990
    if-eqz v11, :cond_16

    iget v11, v11, Landroid/app/ActionBar$LayoutParams;->gravity:I

    :goto_d
    and-int/lit8 v11, v11, 0x7

    .line 995
    const/16 v18, 0x1

    move/from16 v0, v18

    if-ne v11, v0, :cond_1d

    iget v2, v2, Landroid/view/ViewGroup$LayoutParams;->width:I

    const/4 v11, -0x1

    if-ne v2, v11, :cond_1d

    .line 996
    invoke-static {v4, v5}, Ljava/lang/Math;->min(II)I

    move-result v2

    mul-int/lit8 v2, v2, 0x2

    .line 999
    :goto_e
    invoke-static {v2, v1}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result v1

    move/from16 v0, v17

    invoke-static {v0, v8}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result v2

    invoke-virtual {v12, v1, v2}, Landroid/view/View;->measure(II)V

    .line 1002
    invoke-virtual {v12}, Landroid/view/View;->getMeasuredWidth()I

    move-result v1

    add-int/2addr v1, v7

    sub-int/2addr v6, v1

    .line 1005
    :cond_9
    move-object/from16 v0, p0

    iget-object v1, v0, Lcom/miui/internal/widget/ActionBarView;->ii:Landroid/view/View;

    if-nez v1, :cond_a

    if-eqz v16, :cond_a

    .line 1006
    move-object/from16 v0, p0

    iget-object v1, v0, Lcom/miui/internal/widget/ActionBarView;->hI:Landroid/widget/LinearLayout;

    move-object/from16 v0, p0

    iget v2, v0, Lcom/miui/internal/widget/ActionBarView;->mContentHeight:I

    const/high16 v4, 0x40000000

    invoke-static {v2, v4}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result v2

    const/4 v4, 0x0

    move-object/from16 v0, p0

    invoke-virtual {v0, v1, v6, v2, v4}, Lcom/miui/internal/widget/ActionBarView;->measureChildView(Landroid/view/View;III)I

    move-result v6

    .line 1010
    :cond_a
    move-object/from16 v0, p0

    iget-object v1, v0, Lcom/miui/internal/widget/ActionBarView;->ii:Landroid/view/View;

    if-nez v1, :cond_b

    .line 1011
    move-object/from16 v0, p0

    iget v1, v0, Lcom/miui/internal/widget/ActionBarView;->hx:I

    packed-switch v1, :pswitch_data_0

    .line 1036
    :cond_b
    :goto_f
    move-object/from16 v0, p0

    iget v1, v0, Lcom/miui/internal/widget/ActionBarView;->mContentHeight:I

    if-gtz v1, :cond_1b

    .line 1037
    const/4 v2, 0x0

    .line 1038
    const/4 v1, 0x0

    move v3, v1

    :goto_10
    if-ge v3, v13, :cond_1a

    .line 1039
    move-object/from16 v0, p0

    invoke-virtual {v0, v3}, Lcom/miui/internal/widget/ActionBarView;->getChildAt(I)Landroid/view/View;

    move-result-object v1

    .line 1040
    invoke-virtual {v1}, Landroid/view/View;->getMeasuredHeight()I

    move-result v1

    add-int/2addr v1, v15

    .line 1041
    if-le v1, v2, :cond_1c

    .line 1038
    :goto_11
    add-int/lit8 v2, v3, 0x1

    move v3, v2

    move v2, v1

    goto :goto_10

    .line 895
    :cond_c
    invoke-static/range {p2 .. p2}, Landroid/view/View$MeasureSpec;->getSize(I)I

    move-result v1

    move v3, v1

    goto/16 :goto_2

    .line 908
    :cond_d
    move-object/from16 v0, p0

    iget-object v1, v0, Lcom/miui/internal/widget/ActionBarView;->hG:Lcom/miui/internal/widget/ActionBarView$HomeView;

    move-object v5, v1

    goto/16 :goto_3

    .line 916
    :cond_e
    iget v1, v1, Landroid/view/ViewGroup$LayoutParams;->width:I

    const/high16 v7, 0x40000000

    invoke-static {v1, v7}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result v1

    goto/16 :goto_4

    .line 956
    :cond_f
    move-object/from16 v0, p0

    iget v2, v0, Lcom/miui/internal/widget/ActionBarView;->hy:I

    and-int/lit8 v2, v2, 0x10

    if-eqz v2, :cond_1e

    move-object/from16 v0, p0

    iget-object v2, v0, Lcom/miui/internal/widget/ActionBarView;->hP:Landroid/view/View;

    if-eqz v2, :cond_1e

    .line 958
    move-object/from16 v0, p0

    iget-object v1, v0, Lcom/miui/internal/widget/ActionBarView;->hP:Landroid/view/View;

    move-object v12, v1

    goto/16 :goto_7

    .line 963
    :cond_10
    const/4 v1, 0x0

    move-object v11, v1

    goto/16 :goto_8

    .line 979
    :cond_11
    iget v8, v2, Landroid/view/ViewGroup$LayoutParams;->height:I

    const/4 v9, -0x2

    if-eq v8, v9, :cond_12

    const/high16 v8, 0x40000000

    goto/16 :goto_9

    :cond_12
    const/high16 v8, -0x80000000

    goto/16 :goto_9

    :cond_13
    move v9, v10

    .line 982
    goto/16 :goto_a

    .line 985
    :cond_14
    const/high16 v1, -0x80000000

    goto/16 :goto_b

    :cond_15
    move v9, v6

    .line 987
    goto/16 :goto_c

    .line 990
    :cond_16
    const/16 v11, 0x13

    goto/16 :goto_d

    .line 1013
    :pswitch_0
    move-object/from16 v0, p0

    iget-object v1, v0, Lcom/miui/internal/widget/ActionBarView;->hN:Landroid/widget/LinearLayout;

    if-eqz v1, :cond_b

    .line 1014
    if-eqz v16, :cond_17

    move-object/from16 v0, p0

    iget v1, v0, Lcom/miui/internal/widget/ActionBarView;->hU:I

    mul-int/lit8 v1, v1, 0x2

    .line 1015
    :goto_12
    const/4 v2, 0x0

    sub-int v1, v6, v1

    invoke-static {v2, v1}, Ljava/lang/Math;->max(II)I

    move-result v1

    .line 1016
    move-object/from16 v0, p0

    iget-object v2, v0, Lcom/miui/internal/widget/ActionBarView;->hN:Landroid/widget/LinearLayout;

    const/high16 v4, -0x80000000

    invoke-static {v1, v4}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result v1

    const/high16 v4, 0x40000000

    invoke-static {v10, v4}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result v4

    invoke-virtual {v2, v1, v4}, Landroid/widget/LinearLayout;->measure(II)V

    goto/16 :goto_f

    .line 1014
    :cond_17
    move-object/from16 v0, p0

    iget v1, v0, Lcom/miui/internal/widget/ActionBarView;->hU:I

    goto :goto_12

    .line 1022
    :pswitch_1
    move-object/from16 v0, p0

    iget-object v1, v0, Lcom/miui/internal/widget/ActionBarView;->hO:Lcom/miui/internal/widget/ScrollingTabContainerView;

    if-eqz v1, :cond_b

    .line 1023
    if-eqz v16, :cond_18

    move-object/from16 v0, p0

    iget v1, v0, Lcom/miui/internal/widget/ActionBarView;->hU:I

    mul-int/lit8 v1, v1, 0x2

    .line 1024
    :goto_13
    const/4 v2, 0x0

    sub-int v1, v6, v1

    invoke-static {v2, v1}, Ljava/lang/Math;->max(II)I

    move-result v2

    .line 1027
    move-object/from16 v0, p0

    iget-object v1, v0, Lcom/miui/internal/widget/ActionBarView;->mContext:Landroid/content/Context;

    invoke-virtual {v1}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    sget v4, Lcom/miui/internal/R$integer;->action_bar_tab_layout_weight:I

    invoke-virtual {v1, v4}, Landroid/content/res/Resources;->getInteger(I)I

    move-result v1

    .line 1028
    move-object/from16 v0, p0

    iget-object v4, v0, Lcom/miui/internal/widget/ActionBarView;->hO:Lcom/miui/internal/widget/ScrollingTabContainerView;

    if-nez v1, :cond_19

    const/high16 v1, -0x80000000

    :goto_14
    invoke-static {v2, v1}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result v1

    const/high16 v2, 0x40000000

    invoke-static {v10, v2}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result v2

    invoke-virtual {v4, v1, v2}, Lcom/miui/internal/widget/ScrollingTabContainerView;->measure(II)V

    goto/16 :goto_f

    .line 1023
    :cond_18
    move-object/from16 v0, p0

    iget v1, v0, Lcom/miui/internal/widget/ActionBarView;->hU:I

    goto :goto_13

    .line 1028
    :cond_19
    const/high16 v1, 0x40000000

    goto :goto_14

    .line 1045
    :cond_1a
    move-object/from16 v0, p0

    invoke-virtual {v0, v14, v2}, Lcom/miui/internal/widget/ActionBarView;->setMeasuredDimension(II)V

    .line 1050
    :goto_15
    move-object/from16 v0, p0

    iget-object v1, v0, Lcom/miui/internal/widget/ActionBarView;->hQ:Landroid/widget/ProgressBar;

    if-eqz v1, :cond_3

    move-object/from16 v0, p0

    iget-object v1, v0, Lcom/miui/internal/widget/ActionBarView;->hQ:Landroid/widget/ProgressBar;

    invoke-virtual {v1}, Landroid/widget/ProgressBar;->getVisibility()I

    move-result v1

    const/16 v2, 0x8

    if-eq v1, v2, :cond_3

    .line 1051
    move-object/from16 v0, p0

    iget-object v1, v0, Lcom/miui/internal/widget/ActionBarView;->hQ:Landroid/widget/ProgressBar;

    move-object/from16 v0, p0

    iget v2, v0, Lcom/miui/internal/widget/ActionBarView;->hT:I

    mul-int/lit8 v2, v2, 0x2

    sub-int v2, v14, v2

    const/high16 v3, 0x40000000

    invoke-static {v2, v3}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result v2

    invoke-virtual/range {p0 .. p0}, Lcom/miui/internal/widget/ActionBarView;->getMeasuredHeight()I

    move-result v3

    const/high16 v4, -0x80000000

    invoke-static {v3, v4}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result v3

    invoke-virtual {v1, v2, v3}, Landroid/widget/ProgressBar;->measure(II)V

    goto/16 :goto_1

    .line 1047
    :cond_1b
    move-object/from16 v0, p0

    invoke-virtual {v0, v14, v3}, Lcom/miui/internal/widget/ActionBarView;->setMeasuredDimension(II)V

    goto :goto_15

    :cond_1c
    move v1, v2

    goto/16 :goto_11

    :cond_1d
    move v2, v9

    goto/16 :goto_e

    :cond_1e
    move-object v12, v1

    goto/16 :goto_7

    :cond_1f
    move v5, v2

    move v6, v1

    goto/16 :goto_6

    :cond_20
    move v1, v4

    move v4, v2

    goto/16 :goto_5

    .line 1011
    nop

    :pswitch_data_0
    .packed-switch 0x1
        :pswitch_0
        :pswitch_1
    .end packed-switch
.end method

.method public onRestoreInstanceState(Landroid/os/Parcelable;)V
    .locals 2

    .prologue
    .line 1326
    check-cast p1, Lcom/miui/internal/widget/ActionBarView$b;

    .line 1328
    invoke-virtual {p1}, Lcom/miui/internal/widget/ActionBarView$b;->getSuperState()Landroid/os/Parcelable;

    move-result-object v0

    invoke-super {p0, v0}, Lcom/miui/internal/widget/g;->onRestoreInstanceState(Landroid/os/Parcelable;)V

    .line 1330
    iget v0, p1, Lcom/miui/internal/widget/ActionBarView$b;->QH:I

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->ig:Lcom/miui/internal/widget/ActionBarView$a;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->ib:Lcom/miui/internal/view/menu/MenuBuilder;

    if-eqz v0, :cond_0

    .line 1332
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->ib:Lcom/miui/internal/view/menu/MenuBuilder;

    iget v1, p1, Lcom/miui/internal/widget/ActionBarView$b;->QH:I

    invoke-virtual {v0, v1}, Lcom/miui/internal/view/menu/MenuBuilder;->findItem(I)Landroid/view/MenuItem;

    move-result-object v0

    .line 1333
    if-eqz v0, :cond_0

    .line 1334
    invoke-interface {v0}, Landroid/view/MenuItem;->expandActionView()Z

    .line 1338
    :cond_0
    iget-boolean v0, p1, Lcom/miui/internal/widget/ActionBarView$b;->QI:Z

    if-eqz v0, :cond_1

    .line 1339
    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarView;->postShowOverflowMenu()V

    .line 1341
    :cond_1
    return-void
.end method

.method public onSaveInstanceState()Landroid/os/Parcelable;
    .locals 2

    .prologue
    .line 1312
    invoke-super {p0}, Lcom/miui/internal/widget/g;->onSaveInstanceState()Landroid/os/Parcelable;

    move-result-object v0

    .line 1313
    new-instance v1, Lcom/miui/internal/widget/ActionBarView$b;

    invoke-direct {v1, v0}, Lcom/miui/internal/widget/ActionBarView$b;-><init>(Landroid/os/Parcelable;)V

    .line 1315
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->ig:Lcom/miui/internal/widget/ActionBarView$a;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->ig:Lcom/miui/internal/widget/ActionBarView$a;

    iget-object v0, v0, Lcom/miui/internal/widget/ActionBarView$a;->mS:Lcom/miui/internal/view/menu/MenuItemImpl;

    if-eqz v0, :cond_0

    .line 1316
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->ig:Lcom/miui/internal/widget/ActionBarView$a;

    iget-object v0, v0, Lcom/miui/internal/widget/ActionBarView$a;->mS:Lcom/miui/internal/view/menu/MenuItemImpl;

    invoke-virtual {v0}, Lcom/miui/internal/view/menu/MenuItemImpl;->getItemId()I

    move-result v0

    iput v0, v1, Lcom/miui/internal/widget/ActionBarView$b;->QH:I

    .line 1319
    :cond_0
    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarView;->isOverflowMenuShowing()Z

    move-result v0

    iput-boolean v0, v1, Lcom/miui/internal/widget/ActionBarView$b;->QI:Z

    .line 1321
    return-object v1
.end method

.method public onWindowHide()V
    .locals 1

    .prologue
    .line 1697
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->mSplitView:Lcom/miui/internal/widget/ActionBarContainer;

    invoke-virtual {v0}, Lcom/miui/internal/widget/ActionBarContainer;->onWindowHide()V

    .line 1698
    return-void
.end method

.method public onWindowShow()V
    .locals 1

    .prologue
    .line 1693
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->mSplitView:Lcom/miui/internal/widget/ActionBarContainer;

    invoke-virtual {v0}, Lcom/miui/internal/widget/ActionBarContainer;->onWindowShow()V

    .line 1694
    return-void
.end method

.method public bridge synthetic postShowOverflowMenu()V
    .locals 0

    .prologue
    .line 54
    invoke-super {p0}, Lcom/miui/internal/widget/g;->postShowOverflowMenu()V

    return-void
.end method

.method public setCallback(Landroid/app/ActionBar$OnNavigationListener;)V
    .locals 0

    .prologue
    .line 382
    iput-object p1, p0, Lcom/miui/internal/widget/ActionBarView;->if:Landroid/app/ActionBar$OnNavigationListener;

    .line 383
    return-void
.end method

.method public setCollapsable(Z)V
    .locals 0

    .prologue
    .line 816
    return-void
.end method

.method public bridge synthetic setContentHeight(I)V
    .locals 0

    .prologue
    .line 54
    invoke-super {p0, p1}, Lcom/miui/internal/widget/g;->setContentHeight(I)V

    return-void
.end method

.method public setCustomNavigationView(Landroid/view/View;)V
    .locals 2

    .prologue
    .line 483
    iget v0, p0, Lcom/miui/internal/widget/ActionBarView;->hy:I

    and-int/lit8 v0, v0, 0x10

    if-eqz v0, :cond_2

    const/4 v0, 0x1

    .line 484
    :goto_0
    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarView;->hP:Landroid/view/View;

    if-eqz v1, :cond_0

    if-eqz v0, :cond_0

    .line 485
    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarView;->hP:Landroid/view/View;

    invoke-virtual {p0, v1}, Lcom/miui/internal/widget/ActionBarView;->removeView(Landroid/view/View;)V

    .line 487
    :cond_0
    iput-object p1, p0, Lcom/miui/internal/widget/ActionBarView;->hP:Landroid/view/View;

    .line 488
    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarView;->hP:Landroid/view/View;

    if-eqz v1, :cond_1

    if-eqz v0, :cond_1

    .line 489
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hP:Landroid/view/View;

    invoke-virtual {p0, v0}, Lcom/miui/internal/widget/ActionBarView;->addView(Landroid/view/View;)V

    .line 491
    :cond_1
    return-void

    .line 483
    :cond_2
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public setDisplayOptions(I)V
    .locals 8

    .prologue
    const/16 v3, 0x8

    const/4 v0, -0x1

    const/4 v4, 0x1

    const/4 v2, 0x0

    .line 568
    iget v1, p0, Lcom/miui/internal/widget/ActionBarView;->hy:I

    if-ne v1, v0, :cond_7

    .line 569
    :goto_0
    iput p1, p0, Lcom/miui/internal/widget/ActionBarView;->hy:I

    .line 571
    and-int/lit8 v1, v0, 0x1f

    if-eqz v1, :cond_13

    .line 573
    and-int/lit8 v1, p1, 0x2

    if-eqz v1, :cond_8

    move v6, v4

    .line 574
    :goto_1
    if-eqz v6, :cond_d

    .line 575
    invoke-direct {p0}, Lcom/miui/internal/widget/ActionBarView;->as()V

    .line 576
    iget-object v5, p0, Lcom/miui/internal/widget/ActionBarView;->hG:Lcom/miui/internal/widget/ActionBarView$HomeView;

    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarView;->ii:Landroid/view/View;

    if-nez v1, :cond_9

    move v1, v2

    :goto_2
    invoke-virtual {v5, v1}, Lcom/miui/internal/widget/ActionBarView$HomeView;->setVisibility(I)V

    .line 577
    and-int/lit8 v1, v0, 0x4

    if-eqz v1, :cond_0

    .line 578
    and-int/lit8 v1, p1, 0x4

    if-eqz v1, :cond_a

    move v1, v4

    .line 579
    :goto_3
    iget-object v5, p0, Lcom/miui/internal/widget/ActionBarView;->hG:Lcom/miui/internal/widget/ActionBarView$HomeView;

    invoke-virtual {v5, v1}, Lcom/miui/internal/widget/ActionBarView$HomeView;->h(Z)V

    .line 585
    if-eqz v1, :cond_0

    .line 586
    invoke-virtual {p0, v4}, Lcom/miui/internal/widget/ActionBarView;->setHomeButtonEnabled(Z)V

    .line 590
    :cond_0
    and-int/lit8 v1, v0, 0x1

    if-eqz v1, :cond_1

    .line 591
    invoke-direct {p0}, Lcom/miui/internal/widget/ActionBarView;->ar()Landroid/graphics/drawable/Drawable;

    move-result-object v5

    .line 592
    if-eqz v5, :cond_b

    and-int/lit8 v1, p1, 0x1

    if-eqz v1, :cond_b

    move v1, v4

    .line 593
    :goto_4
    iget-object v7, p0, Lcom/miui/internal/widget/ActionBarView;->hG:Lcom/miui/internal/widget/ActionBarView$HomeView;

    if-eqz v1, :cond_c

    move-object v1, v5

    :goto_5
    invoke-virtual {v7, v1}, Lcom/miui/internal/widget/ActionBarView$HomeView;->setIcon(Landroid/graphics/drawable/Drawable;)V

    .line 601
    :cond_1
    :goto_6
    and-int/lit8 v1, v0, 0x8

    if-eqz v1, :cond_2

    .line 602
    and-int/lit8 v1, p1, 0x8

    if-eqz v1, :cond_e

    .line 603
    invoke-direct {p0}, Lcom/miui/internal/widget/ActionBarView;->initTitle()V

    .line 609
    :cond_2
    :goto_7
    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarView;->hI:Landroid/widget/LinearLayout;

    if-eqz v1, :cond_4

    and-int/lit8 v1, v0, 0x6

    if-eqz v1, :cond_4

    .line 611
    iget v1, p0, Lcom/miui/internal/widget/ActionBarView;->hy:I

    and-int/lit8 v1, v1, 0x4

    if-eqz v1, :cond_f

    move v1, v4

    .line 612
    :goto_8
    iget-object v5, p0, Lcom/miui/internal/widget/ActionBarView;->hL:Landroid/view/View;

    if-nez v6, :cond_3

    if-eqz v1, :cond_10

    move v3, v2

    :cond_3
    :goto_9
    invoke-virtual {v5, v3}, Landroid/view/View;->setVisibility(I)V

    .line 613
    iget-object v3, p0, Lcom/miui/internal/widget/ActionBarView;->hI:Landroid/widget/LinearLayout;

    if-nez v6, :cond_11

    if-eqz v1, :cond_11

    :goto_a
    invoke-virtual {v3, v4}, Landroid/widget/LinearLayout;->setEnabled(Z)V

    .line 616
    :cond_4
    and-int/lit8 v0, v0, 0x10

    if-eqz v0, :cond_5

    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hP:Landroid/view/View;

    if-eqz v0, :cond_5

    .line 617
    and-int/lit8 v0, p1, 0x10

    if-eqz v0, :cond_12

    .line 618
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hP:Landroid/view/View;

    invoke-virtual {p0, v0}, Lcom/miui/internal/widget/ActionBarView;->addView(Landroid/view/View;)V

    .line 624
    :cond_5
    :goto_b
    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarView;->requestLayout()V

    .line 630
    :goto_c
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hG:Lcom/miui/internal/widget/ActionBarView$HomeView;

    if-eqz v0, :cond_6

    .line 631
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hG:Lcom/miui/internal/widget/ActionBarView$HomeView;

    invoke-virtual {v0}, Lcom/miui/internal/widget/ActionBarView$HomeView;->isEnabled()Z

    move-result v0

    if-nez v0, :cond_14

    .line 632
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hG:Lcom/miui/internal/widget/ActionBarView$HomeView;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Lcom/miui/internal/widget/ActionBarView$HomeView;->setContentDescription(Ljava/lang/CharSequence;)V

    .line 641
    :cond_6
    :goto_d
    return-void

    .line 568
    :cond_7
    iget v0, p0, Lcom/miui/internal/widget/ActionBarView;->hy:I

    xor-int/2addr v0, p1

    goto/16 :goto_0

    :cond_8
    move v6, v2

    .line 573
    goto/16 :goto_1

    :cond_9
    move v1, v3

    .line 576
    goto/16 :goto_2

    :cond_a
    move v1, v2

    .line 578
    goto/16 :goto_3

    :cond_b
    move v1, v2

    .line 592
    goto :goto_4

    .line 593
    :cond_c
    invoke-direct {p0}, Lcom/miui/internal/widget/ActionBarView;->getIcon()Landroid/graphics/drawable/Drawable;

    move-result-object v1

    goto :goto_5

    .line 596
    :cond_d
    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarView;->hG:Lcom/miui/internal/widget/ActionBarView$HomeView;

    if-eqz v1, :cond_1

    .line 597
    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarView;->hG:Lcom/miui/internal/widget/ActionBarView$HomeView;

    invoke-virtual {p0, v1}, Lcom/miui/internal/widget/ActionBarView;->removeView(Landroid/view/View;)V

    goto :goto_6

    .line 605
    :cond_e
    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarView;->hI:Landroid/widget/LinearLayout;

    invoke-virtual {p0, v1}, Lcom/miui/internal/widget/ActionBarView;->removeView(Landroid/view/View;)V

    goto :goto_7

    :cond_f
    move v1, v2

    .line 611
    goto :goto_8

    .line 612
    :cond_10
    const/4 v3, 0x4

    goto :goto_9

    :cond_11
    move v4, v2

    .line 613
    goto :goto_a

    .line 620
    :cond_12
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hP:Landroid/view/View;

    invoke-virtual {p0, v0}, Lcom/miui/internal/widget/ActionBarView;->removeView(Landroid/view/View;)V

    goto :goto_b

    .line 626
    :cond_13
    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarView;->invalidate()V

    goto :goto_c

    .line 633
    :cond_14
    and-int/lit8 v0, p1, 0x4

    if-eqz v0, :cond_15

    .line 634
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hG:Lcom/miui/internal/widget/ActionBarView$HomeView;

    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarView;->mContext:Landroid/content/Context;

    invoke-virtual {v1}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    sget v2, Lcom/miui/internal/R$string;->abc_action_bar_up_description:I

    invoke-virtual {v1, v2}, Landroid/content/res/Resources;->getText(I)Ljava/lang/CharSequence;

    move-result-object v1

    invoke-virtual {v0, v1}, Lcom/miui/internal/widget/ActionBarView$HomeView;->setContentDescription(Ljava/lang/CharSequence;)V

    goto :goto_d

    .line 637
    :cond_15
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hG:Lcom/miui/internal/widget/ActionBarView$HomeView;

    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarView;->mContext:Landroid/content/Context;

    invoke-virtual {v1}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    sget v2, Lcom/miui/internal/R$string;->abc_action_bar_home_description:I

    invoke-virtual {v1, v2}, Landroid/content/res/Resources;->getText(I)Ljava/lang/CharSequence;

    move-result-object v1

    invoke-virtual {v0, v1}, Lcom/miui/internal/widget/ActionBarView$HomeView;->setContentDescription(Ljava/lang/CharSequence;)V

    goto :goto_d
.end method

.method public setDropdownAdapter(Landroid/widget/SpinnerAdapter;)V
    .locals 1

    .prologue
    .line 720
    iput-object p1, p0, Lcom/miui/internal/widget/ActionBarView;->ie:Landroid/widget/SpinnerAdapter;

    .line 721
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hM:Landroid/widget/Spinner;

    if-eqz v0, :cond_0

    .line 722
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hM:Landroid/widget/Spinner;

    invoke-virtual {v0, p1}, Landroid/widget/Spinner;->setAdapter(Landroid/widget/SpinnerAdapter;)V

    .line 724
    :cond_0
    return-void
.end method

.method public setDropdownSelectedPosition(I)V
    .locals 1

    .prologue
    .line 731
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hM:Landroid/widget/Spinner;

    invoke-virtual {v0, p1}, Landroid/widget/Spinner;->setSelection(I)V

    .line 732
    return-void
.end method

.method public setEmbeddedTabView(Lcom/miui/internal/widget/ScrollingTabContainerView;)V
    .locals 2

    .prologue
    .line 367
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hO:Lcom/miui/internal/widget/ScrollingTabContainerView;

    if-eqz v0, :cond_0

    .line 368
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hO:Lcom/miui/internal/widget/ScrollingTabContainerView;

    invoke-virtual {p0, v0}, Lcom/miui/internal/widget/ActionBarView;->removeView(Landroid/view/View;)V

    .line 370
    :cond_0
    iput-object p1, p0, Lcom/miui/internal/widget/ActionBarView;->hO:Lcom/miui/internal/widget/ScrollingTabContainerView;

    .line 371
    if-eqz p1, :cond_2

    const/4 v0, 0x1

    :goto_0
    iput-boolean v0, p0, Lcom/miui/internal/widget/ActionBarView;->hY:Z

    .line 372
    iget-boolean v0, p0, Lcom/miui/internal/widget/ActionBarView;->hY:Z

    if-eqz v0, :cond_1

    iget v0, p0, Lcom/miui/internal/widget/ActionBarView;->hx:I

    const/4 v1, 0x2

    if-ne v0, v1, :cond_1

    .line 373
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hO:Lcom/miui/internal/widget/ScrollingTabContainerView;

    invoke-virtual {p0, v0}, Lcom/miui/internal/widget/ActionBarView;->addView(Landroid/view/View;)V

    .line 374
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hO:Lcom/miui/internal/widget/ScrollingTabContainerView;

    invoke-virtual {v0}, Lcom/miui/internal/widget/ScrollingTabContainerView;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v0

    .line 375
    const/4 v1, -0x2

    iput v1, v0, Landroid/view/ViewGroup$LayoutParams;->width:I

    .line 376
    const/4 v1, -0x1

    iput v1, v0, Landroid/view/ViewGroup$LayoutParams;->height:I

    .line 377
    invoke-direct {p0}, Lcom/miui/internal/widget/ActionBarView;->ak()V

    .line 379
    :cond_1
    return-void

    .line 371
    :cond_2
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public setHomeAsUpIndicator(I)V
    .locals 1

    .prologue
    .line 1353
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hG:Lcom/miui/internal/widget/ActionBarView$HomeView;

    if-eqz v0, :cond_0

    .line 1354
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hG:Lcom/miui/internal/widget/ActionBarView$HomeView;

    invoke-virtual {v0, p1}, Lcom/miui/internal/widget/ActionBarView$HomeView;->G(I)V

    .line 1359
    :goto_0
    return-void

    .line 1356
    :cond_0
    const/4 v0, 0x0

    iput-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hE:Landroid/graphics/drawable/Drawable;

    .line 1357
    iput p1, p0, Lcom/miui/internal/widget/ActionBarView;->hF:I

    goto :goto_0
.end method

.method public setHomeAsUpIndicator(Landroid/graphics/drawable/Drawable;)V
    .locals 1

    .prologue
    .line 1344
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hG:Lcom/miui/internal/widget/ActionBarView$HomeView;

    if-eqz v0, :cond_0

    .line 1345
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hG:Lcom/miui/internal/widget/ActionBarView$HomeView;

    invoke-virtual {v0, p1}, Lcom/miui/internal/widget/ActionBarView$HomeView;->b(Landroid/graphics/drawable/Drawable;)V

    .line 1350
    :goto_0
    return-void

    .line 1347
    :cond_0
    iput-object p1, p0, Lcom/miui/internal/widget/ActionBarView;->hE:Landroid/graphics/drawable/Drawable;

    .line 1348
    const/4 v0, 0x0

    iput v0, p0, Lcom/miui/internal/widget/ActionBarView;->hF:I

    goto :goto_0
.end method

.method public setHomeButtonEnabled(Z)V
    .locals 3

    .prologue
    .line 551
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hG:Lcom/miui/internal/widget/ActionBarView$HomeView;

    if-eqz v0, :cond_0

    .line 552
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hG:Lcom/miui/internal/widget/ActionBarView$HomeView;

    invoke-virtual {v0, p1}, Lcom/miui/internal/widget/ActionBarView$HomeView;->setEnabled(Z)V

    .line 553
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hG:Lcom/miui/internal/widget/ActionBarView$HomeView;

    invoke-virtual {v0, p1}, Lcom/miui/internal/widget/ActionBarView$HomeView;->setFocusable(Z)V

    .line 555
    if-nez p1, :cond_1

    .line 556
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hG:Lcom/miui/internal/widget/ActionBarView$HomeView;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Lcom/miui/internal/widget/ActionBarView$HomeView;->setContentDescription(Ljava/lang/CharSequence;)V

    .line 565
    :cond_0
    :goto_0
    return-void

    .line 557
    :cond_1
    iget v0, p0, Lcom/miui/internal/widget/ActionBarView;->hy:I

    and-int/lit8 v0, v0, 0x4

    if-eqz v0, :cond_2

    .line 558
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hG:Lcom/miui/internal/widget/ActionBarView$HomeView;

    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarView;->mContext:Landroid/content/Context;

    invoke-virtual {v1}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    sget v2, Lcom/miui/internal/R$string;->abc_action_bar_up_description:I

    invoke-virtual {v1, v2}, Landroid/content/res/Resources;->getText(I)Ljava/lang/CharSequence;

    move-result-object v1

    invoke-virtual {v0, v1}, Lcom/miui/internal/widget/ActionBarView$HomeView;->setContentDescription(Ljava/lang/CharSequence;)V

    goto :goto_0

    .line 561
    :cond_2
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hG:Lcom/miui/internal/widget/ActionBarView$HomeView;

    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarView;->mContext:Landroid/content/Context;

    invoke-virtual {v1}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    sget v2, Lcom/miui/internal/R$string;->abc_action_bar_home_description:I

    invoke-virtual {v1, v2}, Landroid/content/res/Resources;->getText(I)Ljava/lang/CharSequence;

    move-result-object v1

    invoke-virtual {v0, v1}, Lcom/miui/internal/widget/ActionBarView$HomeView;->setContentDescription(Ljava/lang/CharSequence;)V

    goto :goto_0
.end method

.method public setIcon(I)V
    .locals 1

    .prologue
    .line 658
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->mContext:Landroid/content/Context;

    invoke-virtual {v0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    invoke-virtual {v0, p1}, Landroid/content/res/Resources;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v0

    invoke-virtual {p0, v0}, Lcom/miui/internal/widget/ActionBarView;->setIcon(Landroid/graphics/drawable/Drawable;)V

    .line 659
    return-void
.end method

.method public setIcon(Landroid/graphics/drawable/Drawable;)V
    .locals 3

    .prologue
    .line 644
    iput-object p1, p0, Lcom/miui/internal/widget/ActionBarView;->hB:Landroid/graphics/drawable/Drawable;

    .line 645
    iget v0, p0, Lcom/miui/internal/widget/ActionBarView;->hA:I

    or-int/lit8 v0, v0, 0x1

    iput v0, p0, Lcom/miui/internal/widget/ActionBarView;->hA:I

    .line 646
    if-eqz p1, :cond_1

    iget v0, p0, Lcom/miui/internal/widget/ActionBarView;->hy:I

    and-int/lit8 v0, v0, 0x1

    if-eqz v0, :cond_0

    invoke-direct {p0}, Lcom/miui/internal/widget/ActionBarView;->ar()Landroid/graphics/drawable/Drawable;

    move-result-object v0

    if-nez v0, :cond_1

    .line 648
    :cond_0
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hG:Lcom/miui/internal/widget/ActionBarView$HomeView;

    if-eqz v0, :cond_1

    .line 649
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hG:Lcom/miui/internal/widget/ActionBarView$HomeView;

    invoke-virtual {v0, p1}, Lcom/miui/internal/widget/ActionBarView$HomeView;->setIcon(Landroid/graphics/drawable/Drawable;)V

    .line 652
    :cond_1
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->ii:Landroid/view/View;

    if-eqz v0, :cond_2

    .line 653
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hH:Lcom/miui/internal/widget/ActionBarView$HomeView;

    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarView;->hB:Landroid/graphics/drawable/Drawable;

    invoke-virtual {v1}, Landroid/graphics/drawable/Drawable;->getConstantState()Landroid/graphics/drawable/Drawable$ConstantState;

    move-result-object v1

    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarView;->getResources()Landroid/content/res/Resources;

    move-result-object v2

    invoke-virtual {v1, v2}, Landroid/graphics/drawable/Drawable$ConstantState;->newDrawable(Landroid/content/res/Resources;)Landroid/graphics/drawable/Drawable;

    move-result-object v1

    invoke-virtual {v0, v1}, Lcom/miui/internal/widget/ActionBarView$HomeView;->setIcon(Landroid/graphics/drawable/Drawable;)V

    .line 655
    :cond_2
    return-void
.end method

.method public setLogo(I)V
    .locals 1

    .prologue
    .line 672
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->mContext:Landroid/content/Context;

    invoke-virtual {v0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    invoke-virtual {v0, p1}, Landroid/content/res/Resources;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v0

    invoke-virtual {p0, v0}, Lcom/miui/internal/widget/ActionBarView;->setLogo(Landroid/graphics/drawable/Drawable;)V

    .line 673
    return-void
.end method

.method public setLogo(Landroid/graphics/drawable/Drawable;)V
    .locals 1

    .prologue
    .line 662
    iput-object p1, p0, Lcom/miui/internal/widget/ActionBarView;->hC:Landroid/graphics/drawable/Drawable;

    .line 663
    iget v0, p0, Lcom/miui/internal/widget/ActionBarView;->hA:I

    or-int/lit8 v0, v0, 0x2

    iput v0, p0, Lcom/miui/internal/widget/ActionBarView;->hA:I

    .line 664
    if-eqz p1, :cond_0

    iget v0, p0, Lcom/miui/internal/widget/ActionBarView;->hy:I

    and-int/lit8 v0, v0, 0x1

    if-eqz v0, :cond_0

    .line 665
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hG:Lcom/miui/internal/widget/ActionBarView$HomeView;

    if-eqz v0, :cond_0

    .line 666
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hG:Lcom/miui/internal/widget/ActionBarView$HomeView;

    invoke-virtual {v0, p1}, Lcom/miui/internal/widget/ActionBarView$HomeView;->setIcon(Landroid/graphics/drawable/Drawable;)V

    .line 669
    :cond_0
    return-void
.end method

.method public setMenu(Landroid/view/Menu;Lcom/miui/internal/view/menu/MenuPresenter$Callback;)V
    .locals 7

    .prologue
    const/4 v6, 0x0

    const/4 v5, -0x1

    const/4 v4, -0x2

    .line 386
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->ib:Lcom/miui/internal/view/menu/MenuBuilder;

    if-ne p1, v0, :cond_0

    .line 453
    :goto_0
    return-void

    .line 390
    :cond_0
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->ib:Lcom/miui/internal/view/menu/MenuBuilder;

    if-eqz v0, :cond_1

    .line 391
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->ib:Lcom/miui/internal/view/menu/MenuBuilder;

    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarView;->mActionMenuPresenter:Lcom/miui/internal/view/menu/ActionMenuPresenter;

    invoke-virtual {v0, v1}, Lcom/miui/internal/view/menu/MenuBuilder;->removeMenuPresenter(Lcom/miui/internal/view/menu/MenuPresenter;)V

    .line 392
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->ib:Lcom/miui/internal/view/menu/MenuBuilder;

    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarView;->ig:Lcom/miui/internal/widget/ActionBarView$a;

    invoke-virtual {v0, v1}, Lcom/miui/internal/view/menu/MenuBuilder;->removeMenuPresenter(Lcom/miui/internal/view/menu/MenuPresenter;)V

    .line 395
    :cond_1
    check-cast p1, Lcom/miui/internal/view/menu/MenuBuilder;

    .line 396
    iput-object p1, p0, Lcom/miui/internal/widget/ActionBarView;->ib:Lcom/miui/internal/view/menu/MenuBuilder;

    .line 397
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->mMenuView:Lcom/miui/internal/view/menu/ActionMenuView;

    if-eqz v0, :cond_2

    .line 398
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->mMenuView:Lcom/miui/internal/view/menu/ActionMenuView;

    invoke-virtual {v0}, Lcom/miui/internal/view/menu/ActionMenuView;->getParent()Landroid/view/ViewParent;

    move-result-object v0

    check-cast v0, Landroid/view/ViewGroup;

    .line 399
    if-eqz v0, :cond_2

    .line 400
    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarView;->mMenuView:Lcom/miui/internal/view/menu/ActionMenuView;

    invoke-virtual {v0, v1}, Landroid/view/ViewGroup;->removeView(Landroid/view/View;)V

    .line 403
    :cond_2
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->mActionMenuPresenter:Lcom/miui/internal/view/menu/ActionMenuPresenter;

    if-nez v0, :cond_3

    .line 404
    invoke-virtual {p0, p2}, Lcom/miui/internal/widget/ActionBarView;->createActionMenuPresenter(Lcom/miui/internal/view/menu/MenuPresenter$Callback;)Lcom/miui/internal/view/menu/ActionMenuPresenter;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->mActionMenuPresenter:Lcom/miui/internal/view/menu/ActionMenuPresenter;

    .line 405
    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarView;->createExpandedActionViewMenuPresenter()Lcom/miui/internal/widget/ActionBarView$a;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->ig:Lcom/miui/internal/widget/ActionBarView$a;

    .line 409
    :cond_3
    new-instance v2, Landroid/widget/FrameLayout$LayoutParams;

    invoke-direct {v2, v4, v5}, Landroid/widget/FrameLayout$LayoutParams;-><init>(II)V

    .line 411
    iget-boolean v0, p0, Lcom/miui/internal/widget/ActionBarView;->mSplitActionBar:Z

    if-nez v0, :cond_6

    .line 412
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->mActionMenuPresenter:Lcom/miui/internal/view/menu/ActionMenuPresenter;

    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarView;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    sget v3, Lcom/miui/internal/R$bool;->abc_action_bar_expanded_action_views_exclusive:I

    invoke-virtual {v1, v3}, Landroid/content/res/Resources;->getBoolean(I)Z

    move-result v1

    invoke-virtual {v0, v1}, Lcom/miui/internal/view/menu/ActionMenuPresenter;->setExpandedActionViewsExclusive(Z)V

    .line 415
    invoke-direct {p0, p1}, Lcom/miui/internal/widget/ActionBarView;->a(Lcom/miui/internal/view/menu/MenuBuilder;)V

    .line 416
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->mActionMenuPresenter:Lcom/miui/internal/view/menu/ActionMenuPresenter;

    invoke-virtual {v0, p0}, Lcom/miui/internal/view/menu/ActionMenuPresenter;->getMenuView(Landroid/view/ViewGroup;)Lcom/miui/internal/view/menu/MenuView;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/view/menu/ActionMenuView;

    .line 417
    invoke-virtual {v0}, Lcom/miui/internal/view/menu/ActionMenuView;->getParent()Landroid/view/ViewParent;

    move-result-object v1

    check-cast v1, Landroid/view/ViewGroup;

    .line 418
    if-eqz v1, :cond_4

    if-eq v1, p0, :cond_4

    .line 419
    invoke-virtual {v1, v0}, Landroid/view/ViewGroup;->removeView(Landroid/view/View;)V

    .line 421
    :cond_4
    invoke-virtual {p0, v0, v2}, Lcom/miui/internal/widget/ActionBarView;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 452
    :cond_5
    :goto_1
    iput-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->mMenuView:Lcom/miui/internal/view/menu/ActionMenuView;

    goto :goto_0

    .line 423
    :cond_6
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->mActionMenuPresenter:Lcom/miui/internal/view/menu/ActionMenuPresenter;

    invoke-virtual {v0, v6}, Lcom/miui/internal/view/menu/ActionMenuPresenter;->setExpandedActionViewsExclusive(Z)V

    .line 425
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->mActionMenuPresenter:Lcom/miui/internal/view/menu/ActionMenuPresenter;

    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarView;->getContext()Landroid/content/Context;

    move-result-object v1

    invoke-virtual {v1}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    invoke-virtual {v1}, Landroid/content/res/Resources;->getDisplayMetrics()Landroid/util/DisplayMetrics;

    move-result-object v1

    iget v1, v1, Landroid/util/DisplayMetrics;->widthPixels:I

    const/4 v3, 0x1

    invoke-virtual {v0, v1, v3}, Lcom/miui/internal/view/menu/ActionMenuPresenter;->setWidthLimit(IZ)V

    .line 428
    iput v5, v2, Landroid/widget/FrameLayout$LayoutParams;->width:I

    .line 429
    iput v4, v2, Landroid/widget/FrameLayout$LayoutParams;->height:I

    .line 430
    sget-boolean v0, Lmiui/os/Build;->IS_TABLET:Z

    if-eqz v0, :cond_8

    const/16 v0, 0x11

    :goto_2
    iput v0, v2, Landroid/widget/FrameLayout$LayoutParams;->gravity:I

    .line 431
    invoke-direct {p0, p1}, Lcom/miui/internal/widget/ActionBarView;->a(Lcom/miui/internal/view/menu/MenuBuilder;)V

    .line 432
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->mActionMenuPresenter:Lcom/miui/internal/view/menu/ActionMenuPresenter;

    invoke-virtual {v0, p0}, Lcom/miui/internal/view/menu/ActionMenuPresenter;->getMenuView(Landroid/view/ViewGroup;)Lcom/miui/internal/view/menu/MenuView;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/view/menu/ActionMenuView;

    .line 433
    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarView;->mSplitView:Lcom/miui/internal/widget/ActionBarContainer;

    if-eqz v1, :cond_9

    .line 434
    invoke-virtual {v0}, Lcom/miui/internal/view/menu/ActionMenuView;->getParent()Landroid/view/ViewParent;

    move-result-object v1

    check-cast v1, Landroid/view/ViewGroup;

    .line 435
    if-eqz v1, :cond_7

    iget-object v3, p0, Lcom/miui/internal/widget/ActionBarView;->mSplitView:Lcom/miui/internal/widget/ActionBarContainer;

    if-eq v1, v3, :cond_7

    .line 436
    invoke-virtual {v1, v0}, Landroid/view/ViewGroup;->removeView(Landroid/view/View;)V

    .line 438
    :cond_7
    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarView;->getAnimatedVisibility()I

    move-result v1

    invoke-virtual {v0, v1}, Lcom/miui/internal/view/menu/ActionMenuView;->setVisibility(I)V

    .line 440
    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarView;->mSplitView:Lcom/miui/internal/widget/ActionBarContainer;

    invoke-virtual {v1, v0, v6, v2}, Lcom/miui/internal/widget/ActionBarContainer;->addView(Landroid/view/View;ILandroid/view/ViewGroup$LayoutParams;)V

    .line 442
    sget v1, Lcom/miui/internal/R$id;->expanded_menu:I

    invoke-virtual {v0, v1}, Lcom/miui/internal/view/menu/ActionMenuView;->findViewById(I)Landroid/view/View;

    move-result-object v1

    .line 444
    if-eqz v1, :cond_5

    .line 445
    invoke-virtual {v1}, Landroid/view/View;->requestLayout()V

    goto :goto_1

    .line 430
    :cond_8
    const/16 v0, 0x50

    goto :goto_2

    .line 449
    :cond_9
    invoke-virtual {v0, v2}, Lcom/miui/internal/view/menu/ActionMenuView;->setLayoutParams(Landroid/view/ViewGroup$LayoutParams;)V

    goto :goto_1
.end method

.method public setNavigationMode(I)V
    .locals 4

    .prologue
    const/4 v3, 0x0

    .line 676
    iget v0, p0, Lcom/miui/internal/widget/ActionBarView;->hx:I

    .line 677
    if-eq p1, v0, :cond_2

    .line 678
    packed-switch v0, :pswitch_data_0

    .line 690
    :cond_0
    :goto_0
    packed-switch p1, :pswitch_data_1

    .line 714
    :cond_1
    :goto_1
    iput p1, p0, Lcom/miui/internal/widget/ActionBarView;->hx:I

    .line 715
    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarView;->requestLayout()V

    .line 717
    :cond_2
    return-void

    .line 680
    :pswitch_0
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hN:Landroid/widget/LinearLayout;

    if-eqz v0, :cond_0

    .line 681
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hN:Landroid/widget/LinearLayout;

    invoke-virtual {p0, v0}, Lcom/miui/internal/widget/ActionBarView;->removeView(Landroid/view/View;)V

    goto :goto_0

    .line 685
    :pswitch_1
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hO:Lcom/miui/internal/widget/ScrollingTabContainerView;

    if-eqz v0, :cond_0

    iget-boolean v0, p0, Lcom/miui/internal/widget/ActionBarView;->hY:Z

    if-eqz v0, :cond_0

    .line 686
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hO:Lcom/miui/internal/widget/ScrollingTabContainerView;

    invoke-virtual {p0, v0}, Lcom/miui/internal/widget/ActionBarView;->removeView(Landroid/view/View;)V

    goto :goto_0

    .line 692
    :pswitch_2
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hM:Landroid/widget/Spinner;

    if-nez v0, :cond_3

    .line 693
    new-instance v0, Landroid/widget/Spinner;

    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarView;->mContext:Landroid/content/Context;

    const v2, 0x10102d7

    invoke-direct {v0, v1, v3, v2}, Landroid/widget/Spinner;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    iput-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hM:Landroid/widget/Spinner;

    .line 695
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->mContext:Landroid/content/Context;

    invoke-static {v0}, Landroid/view/LayoutInflater;->from(Landroid/content/Context;)Landroid/view/LayoutInflater;

    move-result-object v0

    sget v1, Lcom/miui/internal/R$layout;->action_bar_view_list_nav_layout:I

    invoke-virtual {v0, v1, v3}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/LinearLayout;

    iput-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hN:Landroid/widget/LinearLayout;

    .line 697
    new-instance v0, Landroid/widget/LinearLayout$LayoutParams;

    const/4 v1, -0x2

    const/4 v2, -0x1

    invoke-direct {v0, v1, v2}, Landroid/widget/LinearLayout$LayoutParams;-><init>(II)V

    .line 699
    const/16 v1, 0x11

    iput v1, v0, Landroid/widget/LinearLayout$LayoutParams;->gravity:I

    .line 700
    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarView;->hN:Landroid/widget/LinearLayout;

    iget-object v2, p0, Lcom/miui/internal/widget/ActionBarView;->hM:Landroid/widget/Spinner;

    invoke-virtual {v1, v2, v0}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 702
    :cond_3
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hM:Landroid/widget/Spinner;

    invoke-virtual {v0}, Landroid/widget/Spinner;->getAdapter()Landroid/widget/SpinnerAdapter;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarView;->ie:Landroid/widget/SpinnerAdapter;

    if-eq v0, v1, :cond_4

    .line 703
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hM:Landroid/widget/Spinner;

    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarView;->ie:Landroid/widget/SpinnerAdapter;

    invoke-virtual {v0, v1}, Landroid/widget/Spinner;->setAdapter(Landroid/widget/SpinnerAdapter;)V

    .line 705
    :cond_4
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hM:Landroid/widget/Spinner;

    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarView;->ik:Landroid/widget/AdapterView$OnItemSelectedListener;

    invoke-virtual {v0, v1}, Landroid/widget/Spinner;->setOnItemSelectedListener(Landroid/widget/AdapterView$OnItemSelectedListener;)V

    .line 706
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hN:Landroid/widget/LinearLayout;

    invoke-virtual {p0, v0}, Lcom/miui/internal/widget/ActionBarView;->addView(Landroid/view/View;)V

    goto :goto_1

    .line 709
    :pswitch_3
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hO:Lcom/miui/internal/widget/ScrollingTabContainerView;

    if-eqz v0, :cond_1

    iget-boolean v0, p0, Lcom/miui/internal/widget/ActionBarView;->hY:Z

    if-eqz v0, :cond_1

    .line 710
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hO:Lcom/miui/internal/widget/ScrollingTabContainerView;

    invoke-virtual {p0, v0}, Lcom/miui/internal/widget/ActionBarView;->addView(Landroid/view/View;)V

    goto :goto_1

    .line 678
    nop

    :pswitch_data_0
    .packed-switch 0x1
        :pswitch_0
        :pswitch_1
    .end packed-switch

    .line 690
    :pswitch_data_1
    .packed-switch 0x1
        :pswitch_2
        :pswitch_3
    .end packed-switch
.end method

.method public setProgress(I)V
    .locals 1

    .prologue
    .line 1716
    add-int/lit8 v0, p1, 0x0

    invoke-direct {p0, v0}, Lcom/miui/internal/widget/ActionBarView;->z(I)V

    .line 1717
    return-void
.end method

.method public setProgressBarIndeterminate(Z)V
    .locals 1

    .prologue
    .line 1711
    if-eqz p1, :cond_0

    const/4 v0, -0x3

    :goto_0
    invoke-direct {p0, v0}, Lcom/miui/internal/widget/ActionBarView;->z(I)V

    .line 1713
    return-void

    .line 1711
    :cond_0
    const/4 v0, -0x4

    goto :goto_0
.end method

.method public setProgressBarIndeterminateVisibility(Z)V
    .locals 1

    .prologue
    .line 1706
    if-eqz p1, :cond_0

    const/4 v0, -0x1

    :goto_0
    invoke-direct {p0, v0}, Lcom/miui/internal/widget/ActionBarView;->z(I)V

    .line 1708
    return-void

    .line 1706
    :cond_0
    const/4 v0, -0x2

    goto :goto_0
.end method

.method public setProgressBarVisibility(Z)V
    .locals 1

    .prologue
    .line 1701
    if-eqz p1, :cond_0

    const/4 v0, -0x1

    :goto_0
    invoke-direct {p0, v0}, Lcom/miui/internal/widget/ActionBarView;->z(I)V

    .line 1703
    return-void

    .line 1701
    :cond_0
    const/4 v0, -0x2

    goto :goto_0
.end method

.method public setSplitActionBar(Z)V
    .locals 3

    .prologue
    const/4 v1, 0x0

    .line 315
    iget-boolean v0, p0, Lcom/miui/internal/widget/ActionBarView;->mSplitActionBar:Z

    if-eq v0, p1, :cond_5

    .line 316
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->mMenuView:Lcom/miui/internal/view/menu/ActionMenuView;

    if-eqz v0, :cond_2

    .line 317
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->mMenuView:Lcom/miui/internal/view/menu/ActionMenuView;

    invoke-virtual {v0}, Lcom/miui/internal/view/menu/ActionMenuView;->getParent()Landroid/view/ViewParent;

    move-result-object v0

    check-cast v0, Landroid/view/ViewGroup;

    .line 318
    if-eqz v0, :cond_0

    .line 319
    iget-object v2, p0, Lcom/miui/internal/widget/ActionBarView;->mMenuView:Lcom/miui/internal/view/menu/ActionMenuView;

    invoke-virtual {v0, v2}, Landroid/view/ViewGroup;->removeView(Landroid/view/View;)V

    .line 321
    :cond_0
    if-eqz p1, :cond_6

    .line 322
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->mSplitView:Lcom/miui/internal/widget/ActionBarContainer;

    if-eqz v0, :cond_1

    .line 323
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->mSplitView:Lcom/miui/internal/widget/ActionBarContainer;

    iget-object v2, p0, Lcom/miui/internal/widget/ActionBarView;->mMenuView:Lcom/miui/internal/view/menu/ActionMenuView;

    invoke-virtual {v0, v2}, Lcom/miui/internal/widget/ActionBarContainer;->addView(Landroid/view/View;)V

    .line 325
    :cond_1
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->mMenuView:Lcom/miui/internal/view/menu/ActionMenuView;

    invoke-virtual {v0}, Lcom/miui/internal/view/menu/ActionMenuView;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v0

    const/4 v2, -0x1

    iput v2, v0, Landroid/view/ViewGroup$LayoutParams;->width:I

    .line 330
    :goto_0
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->mMenuView:Lcom/miui/internal/view/menu/ActionMenuView;

    invoke-virtual {v0}, Lcom/miui/internal/view/menu/ActionMenuView;->requestLayout()V

    .line 332
    :cond_2
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->mSplitView:Lcom/miui/internal/widget/ActionBarContainer;

    if-eqz v0, :cond_3

    .line 333
    iget-object v2, p0, Lcom/miui/internal/widget/ActionBarView;->mSplitView:Lcom/miui/internal/widget/ActionBarContainer;

    if-eqz p1, :cond_7

    move v0, v1

    :goto_1
    invoke-virtual {v2, v0}, Lcom/miui/internal/widget/ActionBarContainer;->setVisibility(I)V

    .line 336
    :cond_3
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->mActionMenuPresenter:Lcom/miui/internal/view/menu/ActionMenuPresenter;

    if-eqz v0, :cond_4

    .line 337
    if-nez p1, :cond_8

    .line 338
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->mActionMenuPresenter:Lcom/miui/internal/view/menu/ActionMenuPresenter;

    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarView;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    sget v2, Lcom/miui/internal/R$bool;->abc_action_bar_expanded_action_views_exclusive:I

    invoke-virtual {v1, v2}, Landroid/content/res/Resources;->getBoolean(I)Z

    move-result v1

    invoke-virtual {v0, v1}, Lcom/miui/internal/view/menu/ActionMenuPresenter;->setExpandedActionViewsExclusive(Z)V

    .line 348
    :cond_4
    :goto_2
    invoke-super {p0, p1}, Lcom/miui/internal/widget/g;->setSplitActionBar(Z)V

    .line 350
    :cond_5
    return-void

    .line 327
    :cond_6
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->mMenuView:Lcom/miui/internal/view/menu/ActionMenuView;

    invoke-virtual {p0, v0}, Lcom/miui/internal/widget/ActionBarView;->addView(Landroid/view/View;)V

    .line 328
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->mMenuView:Lcom/miui/internal/view/menu/ActionMenuView;

    invoke-virtual {v0}, Lcom/miui/internal/view/menu/ActionMenuView;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v0

    const/4 v2, -0x2

    iput v2, v0, Landroid/view/ViewGroup$LayoutParams;->width:I

    goto :goto_0

    .line 333
    :cond_7
    const/16 v0, 0x8

    goto :goto_1

    .line 342
    :cond_8
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->mActionMenuPresenter:Lcom/miui/internal/view/menu/ActionMenuPresenter;

    invoke-virtual {v0, v1}, Lcom/miui/internal/view/menu/ActionMenuPresenter;->setExpandedActionViewsExclusive(Z)V

    .line 344
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->mActionMenuPresenter:Lcom/miui/internal/view/menu/ActionMenuPresenter;

    invoke-virtual {p0}, Lcom/miui/internal/widget/ActionBarView;->getContext()Landroid/content/Context;

    move-result-object v1

    invoke-virtual {v1}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    invoke-virtual {v1}, Landroid/content/res/Resources;->getDisplayMetrics()Landroid/util/DisplayMetrics;

    move-result-object v1

    iget v1, v1, Landroid/util/DisplayMetrics;->widthPixels:I

    const/4 v2, 0x1

    invoke-virtual {v0, v1, v2}, Lcom/miui/internal/view/menu/ActionMenuPresenter;->setWidthLimit(IZ)V

    goto :goto_2
.end method

.method public bridge synthetic setSplitView(Lcom/miui/internal/widget/ActionBarContainer;)V
    .locals 0

    .prologue
    .line 54
    invoke-super {p0, p1}, Lcom/miui/internal/widget/g;->setSplitView(Lcom/miui/internal/widget/ActionBarContainer;)V

    return-void
.end method

.method public bridge synthetic setSplitWhenNarrow(Z)V
    .locals 0

    .prologue
    .line 54
    invoke-super {p0, p1}, Lcom/miui/internal/widget/g;->setSplitWhenNarrow(Z)V

    return-void
.end method

.method public setSubtitle(Ljava/lang/CharSequence;)V
    .locals 4

    .prologue
    const/16 v2, 0x8

    const/4 v1, 0x0

    .line 539
    iput-object p1, p0, Lcom/miui/internal/widget/ActionBarView;->hz:Ljava/lang/CharSequence;

    .line 540
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hK:Landroid/widget/TextView;

    if-eqz v0, :cond_1

    .line 541
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hK:Landroid/widget/TextView;

    invoke-virtual {v0, p1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 542
    iget-object v3, p0, Lcom/miui/internal/widget/ActionBarView;->hK:Landroid/widget/TextView;

    if-eqz p1, :cond_2

    move v0, v1

    :goto_0
    invoke-virtual {v3, v0}, Landroid/widget/TextView;->setVisibility(I)V

    .line 543
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->ii:Landroid/view/View;

    if-nez v0, :cond_3

    iget v0, p0, Lcom/miui/internal/widget/ActionBarView;->hy:I

    and-int/lit8 v0, v0, 0x8

    if-eqz v0, :cond_3

    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->mTitle:Ljava/lang/CharSequence;

    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView;->hz:Ljava/lang/CharSequence;

    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_3

    :cond_0
    const/4 v0, 0x1

    .line 546
    :goto_1
    iget-object v3, p0, Lcom/miui/internal/widget/ActionBarView;->hI:Landroid/widget/LinearLayout;

    if-eqz v0, :cond_4

    :goto_2
    invoke-virtual {v3, v1}, Landroid/widget/LinearLayout;->setVisibility(I)V

    .line 548
    :cond_1
    return-void

    :cond_2
    move v0, v2

    .line 542
    goto :goto_0

    :cond_3
    move v0, v1

    .line 543
    goto :goto_1

    :cond_4
    move v1, v2

    .line 546
    goto :goto_2
.end method

.method public setTitle(Ljava/lang/CharSequence;)V
    .locals 1

    .prologue
    .line 504
    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/miui/internal/widget/ActionBarView;->hX:Z

    .line 505
    invoke-direct {p0, p1}, Lcom/miui/internal/widget/ActionBarView;->a(Ljava/lang/CharSequence;)V

    .line 506
    return-void
.end method

.method public bridge synthetic setVisibility(I)V
    .locals 0

    .prologue
    .line 54
    invoke-super {p0, p1}, Lcom/miui/internal/widget/g;->setVisibility(I)V

    return-void
.end method

.method public setWindowCallback(Landroid/view/Window$Callback;)V
    .locals 0

    .prologue
    .line 237
    iput-object p1, p0, Lcom/miui/internal/widget/ActionBarView;->ij:Landroid/view/Window$Callback;

    .line 238
    return-void
.end method

.method public setWindowTitle(Ljava/lang/CharSequence;)V
    .locals 1

    .prologue
    .line 515
    iget-boolean v0, p0, Lcom/miui/internal/widget/ActionBarView;->hX:Z

    if-nez v0, :cond_0

    .line 516
    invoke-direct {p0, p1}, Lcom/miui/internal/widget/ActionBarView;->a(Ljava/lang/CharSequence;)V

    .line 518
    :cond_0
    return-void
.end method

.method public shouldDelayChildPressedState()Z
    .locals 1

    .prologue
    .line 250
    const/4 v0, 0x0

    return v0
.end method

.method public showImmersionMore()Z
    .locals 2

    .prologue
    const/4 v0, 0x0

    .line 298
    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarView;->hS:Landroid/view/View;

    if-eqz v1, :cond_0

    .line 299
    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarView;->hS:Landroid/view/View;

    invoke-virtual {v1, v0}, Landroid/view/View;->setVisibility(I)V

    .line 300
    const/4 v0, 0x1

    .line 302
    :cond_0
    return v0
.end method

.method public bridge synthetic showOverflowMenu()Z
    .locals 1

    .prologue
    .line 54
    invoke-super {p0}, Lcom/miui/internal/widget/g;->showOverflowMenu()Z

    move-result v0

    return v0
.end method
