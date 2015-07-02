.class public Lcom/miui/internal/view/menu/ActionMenuPresenter;
.super Lcom/miui/internal/view/menu/BaseMenuPresenter;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/internal/view/menu/ActionMenuPresenter$1;,
        Lcom/miui/internal/view/menu/ActionMenuPresenter$a;,
        Lcom/miui/internal/view/menu/ActionMenuPresenter$f;,
        Lcom/miui/internal/view/menu/ActionMenuPresenter$h;,
        Lcom/miui/internal/view/menu/ActionMenuPresenter$g;,
        Lcom/miui/internal/view/menu/ActionMenuPresenter$b;,
        Lcom/miui/internal/view/menu/ActionMenuPresenter$d;,
        Lcom/miui/internal/view/menu/ActionMenuPresenter$e;,
        Lcom/miui/internal/view/menu/ActionMenuPresenter$c;
    }
.end annotation


# instance fields
.field private b:Z

.field private mA:Lcom/miui/internal/view/menu/ActionMenuPresenter$h;

.field private mB:Lcom/miui/internal/view/menu/ActionMenuPresenter$a;

.field final mC:Lcom/miui/internal/view/menu/ActionMenuPresenter$f;

.field mD:I

.field private mj:Landroid/view/View;

.field private mk:Z

.field private ml:I

.field private mm:I

.field private mn:I

.field private mo:I

.field private mp:I

.field private mq:Z

.field private mr:Z

.field private ms:Z

.field private mt:Z

.field private mu:I

.field private final mv:Landroid/util/SparseBooleanArray;

.field private mw:Landroid/view/View;

.field private mx:Lcom/miui/internal/view/menu/ActionMenuPresenter$d;

.field private my:Lcom/miui/internal/view/menu/ActionMenuPresenter$d;

.field private mz:Lcom/miui/internal/view/menu/MenuItemImpl;


# direct methods
.method public constructor <init>(Landroid/content/Context;II)V
    .locals 6

    .prologue
    const/4 v4, 0x0

    .line 68
    move-object v0, p0

    move-object v1, p1

    move v2, p2

    move v3, p3

    move v5, v4

    invoke-direct/range {v0 .. v5}, Lcom/miui/internal/view/menu/ActionMenuPresenter;-><init>(Landroid/content/Context;IIII)V

    .line 69
    return-void
.end method

.method public constructor <init>(Landroid/content/Context;IIII)V
    .locals 2

    .prologue
    .line 73
    invoke-direct {p0, p1, p2, p3}, Lcom/miui/internal/view/menu/BaseMenuPresenter;-><init>(Landroid/content/Context;II)V

    .line 47
    const v0, 0x10102f6

    iput v0, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mu:I

    .line 50
    new-instance v0, Landroid/util/SparseBooleanArray;

    invoke-direct {v0}, Landroid/util/SparseBooleanArray;-><init>()V

    iput-object v0, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mv:Landroid/util/SparseBooleanArray;

    .line 64
    new-instance v0, Lcom/miui/internal/view/menu/ActionMenuPresenter$f;

    const/4 v1, 0x0

    invoke-direct {v0, p0, v1}, Lcom/miui/internal/view/menu/ActionMenuPresenter$f;-><init>(Lcom/miui/internal/view/menu/ActionMenuPresenter;Lcom/miui/internal/view/menu/ActionMenuPresenter$1;)V

    iput-object v0, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mC:Lcom/miui/internal/view/menu/ActionMenuPresenter$f;

    .line 74
    iput p4, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mp:I

    .line 75
    iput p5, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mo:I

    .line 76
    return-void
.end method

.method static synthetic a(Lcom/miui/internal/view/menu/ActionMenuPresenter;)I
    .locals 1

    .prologue
    .line 33
    iget v0, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mu:I

    return v0
.end method

.method static synthetic a(Lcom/miui/internal/view/menu/ActionMenuPresenter;Lcom/miui/internal/view/menu/ActionMenuPresenter$a;)Lcom/miui/internal/view/menu/ActionMenuPresenter$a;
    .locals 0

    .prologue
    .line 33
    iput-object p1, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mB:Lcom/miui/internal/view/menu/ActionMenuPresenter$a;

    return-object p1
.end method

.method static synthetic a(Lcom/miui/internal/view/menu/ActionMenuPresenter;Lcom/miui/internal/view/menu/ActionMenuPresenter$d;)Lcom/miui/internal/view/menu/ActionMenuPresenter$d;
    .locals 0

    .prologue
    .line 33
    iput-object p1, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mx:Lcom/miui/internal/view/menu/ActionMenuPresenter$d;

    return-object p1
.end method

.method static synthetic a(Lcom/miui/internal/view/menu/ActionMenuPresenter;Lcom/miui/internal/view/menu/ActionMenuPresenter$h;)Lcom/miui/internal/view/menu/ActionMenuPresenter$h;
    .locals 0

    .prologue
    .line 33
    iput-object p1, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mA:Lcom/miui/internal/view/menu/ActionMenuPresenter$h;

    return-object p1
.end method

.method private aM()Lcom/miui/internal/view/menu/ActionMenuPresenter$d;
    .locals 6

    .prologue
    .line 303
    sget-boolean v0, Lmiui/os/Build;->IS_TABLET:Z

    if-eqz v0, :cond_0

    .line 304
    new-instance v0, Lcom/miui/internal/view/menu/ActionMenuPresenter$g;

    iget-object v2, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mContext:Landroid/content/Context;

    iget-object v3, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    iget-object v4, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mj:Landroid/view/View;

    const/4 v5, 0x1

    move-object v1, p0

    invoke-direct/range {v0 .. v5}, Lcom/miui/internal/view/menu/ActionMenuPresenter$g;-><init>(Lcom/miui/internal/view/menu/ActionMenuPresenter;Landroid/content/Context;Lcom/miui/internal/view/menu/MenuBuilder;Landroid/view/View;Z)V

    .line 309
    :goto_0
    return-object v0

    .line 306
    :cond_0
    iget-object v0, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->my:Lcom/miui/internal/view/menu/ActionMenuPresenter$d;

    if-nez v0, :cond_1

    .line 307
    new-instance v0, Lcom/miui/internal/view/menu/ActionMenuPresenter$b;

    const/4 v1, 0x0

    invoke-direct {v0, p0, v1}, Lcom/miui/internal/view/menu/ActionMenuPresenter$b;-><init>(Lcom/miui/internal/view/menu/ActionMenuPresenter;Lcom/miui/internal/view/menu/ActionMenuPresenter$1;)V

    iput-object v0, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->my:Lcom/miui/internal/view/menu/ActionMenuPresenter$d;

    .line 309
    :cond_1
    iget-object v0, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->my:Lcom/miui/internal/view/menu/ActionMenuPresenter$d;

    goto :goto_0
.end method

.method private aN()Lcom/miui/internal/view/menu/MenuItemImpl;
    .locals 8

    .prologue
    const/4 v2, 0x0

    .line 314
    iget-object v0, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mz:Lcom/miui/internal/view/menu/MenuItemImpl;

    if-nez v0, :cond_0

    .line 315
    new-instance v0, Lcom/miui/internal/view/menu/MenuItemImpl;

    iget-object v1, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    sget v3, Lcom/miui/internal/R$id;->more:I

    iget-object v4, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mContext:Landroid/content/Context;

    sget v5, Lcom/miui/internal/R$string;->more:I

    invoke-virtual {v4, v5}, Landroid/content/Context;->getString(I)Ljava/lang/String;

    move-result-object v6

    move v4, v2

    move v5, v2

    move v7, v2

    invoke-direct/range {v0 .. v7}, Lcom/miui/internal/view/menu/MenuItemImpl;-><init>(Lcom/miui/internal/view/menu/MenuBuilder;IIIILjava/lang/CharSequence;I)V

    iput-object v0, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mz:Lcom/miui/internal/view/menu/MenuItemImpl;

    .line 318
    :cond_0
    iget-object v0, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mz:Lcom/miui/internal/view/menu/MenuItemImpl;

    return-object v0
.end method

.method private b(Landroid/view/MenuItem;)Landroid/view/View;
    .locals 6

    .prologue
    const/4 v3, 0x0

    .line 262
    iget-object v0, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mMenuView:Lcom/miui/internal/view/menu/MenuView;

    check-cast v0, Landroid/view/ViewGroup;

    .line 263
    if-nez v0, :cond_1

    move-object v2, v3

    .line 275
    :cond_0
    :goto_0
    return-object v2

    .line 267
    :cond_1
    invoke-virtual {v0}, Landroid/view/ViewGroup;->getChildCount()I

    move-result v5

    .line 268
    const/4 v1, 0x0

    move v4, v1

    :goto_1
    if-ge v4, v5, :cond_3

    .line 269
    invoke-virtual {v0, v4}, Landroid/view/ViewGroup;->getChildAt(I)Landroid/view/View;

    move-result-object v2

    .line 270
    instance-of v1, v2, Lcom/miui/internal/view/menu/MenuView$ItemView;

    if-eqz v1, :cond_2

    move-object v1, v2

    check-cast v1, Lcom/miui/internal/view/menu/MenuView$ItemView;

    invoke-interface {v1}, Lcom/miui/internal/view/menu/MenuView$ItemView;->getItemData()Lcom/miui/internal/view/menu/MenuItemImpl;

    move-result-object v1

    if-eq v1, p1, :cond_0

    .line 268
    :cond_2
    add-int/lit8 v1, v4, 0x1

    move v4, v1

    goto :goto_1

    :cond_3
    move-object v2, v3

    .line 275
    goto :goto_0
.end method

.method static synthetic b(Lcom/miui/internal/view/menu/ActionMenuPresenter;)Lcom/miui/internal/view/menu/MenuItemImpl;
    .locals 1

    .prologue
    .line 33
    invoke-direct {p0}, Lcom/miui/internal/view/menu/ActionMenuPresenter;->aN()Lcom/miui/internal/view/menu/MenuItemImpl;

    move-result-object v0

    return-object v0
.end method

.method static synthetic c(Lcom/miui/internal/view/menu/ActionMenuPresenter;)I
    .locals 1

    .prologue
    .line 33
    iget v0, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mp:I

    return v0
.end method

.method static synthetic d(Lcom/miui/internal/view/menu/ActionMenuPresenter;)I
    .locals 1

    .prologue
    .line 33
    iget v0, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mo:I

    return v0
.end method


# virtual methods
.method public bindItemView(Lcom/miui/internal/view/menu/MenuItemImpl;Lcom/miui/internal/view/menu/MenuView$ItemView;)V
    .locals 1

    .prologue
    .line 180
    const/4 v0, 0x0

    invoke-interface {p2, p1, v0}, Lcom/miui/internal/view/menu/MenuView$ItemView;->initialize(Lcom/miui/internal/view/menu/MenuItemImpl;I)V

    .line 181
    iget-object v0, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mMenuView:Lcom/miui/internal/view/menu/MenuView;

    check-cast v0, Lcom/miui/internal/view/menu/MenuBuilder$ItemInvoker;

    invoke-interface {p2, v0}, Lcom/miui/internal/view/menu/MenuView$ItemView;->setItemInvoker(Lcom/miui/internal/view/menu/MenuBuilder$ItemInvoker;)V

    .line 182
    return-void
.end method

.method protected createOverflowMenuButton(Landroid/content/Context;)Landroid/view/View;
    .locals 1

    .prologue
    .line 647
    new-instance v0, Lcom/miui/internal/view/menu/ActionMenuPresenter$e;

    invoke-direct {v0, p0, p1}, Lcom/miui/internal/view/menu/ActionMenuPresenter$e;-><init>(Lcom/miui/internal/view/menu/ActionMenuPresenter;Landroid/content/Context;)V

    return-object v0
.end method

.method public dismissPopupMenus(Z)Z
    .locals 1

    .prologue
    .line 348
    invoke-virtual {p0, p1}, Lcom/miui/internal/view/menu/ActionMenuPresenter;->hideOverflowMenu(Z)Z

    move-result v0

    return v0
.end method

.method public flagActionItems()Z
    .locals 8

    .prologue
    const/4 v3, 0x1

    const/4 v2, 0x0

    .line 379
    iget-object v0, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    invoke-virtual {v0}, Lcom/miui/internal/view/menu/MenuBuilder;->getVisibleItems()Ljava/util/ArrayList;

    move-result-object v6

    .line 380
    invoke-virtual {v6}, Ljava/util/ArrayList;->size()I

    move-result v7

    .line 381
    iget v4, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mn:I

    move v5, v2

    .line 383
    :goto_0
    if-ge v5, v7, :cond_4

    if-lez v4, :cond_4

    .line 384
    invoke-virtual {v6, v5}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/view/menu/MenuItemImpl;

    .line 385
    invoke-virtual {v0}, Lcom/miui/internal/view/menu/MenuItemImpl;->requestsActionButton()Z

    move-result v1

    if-nez v1, :cond_0

    invoke-virtual {v0}, Lcom/miui/internal/view/menu/MenuItemImpl;->requiresActionButton()Z

    move-result v1

    if-eqz v1, :cond_1

    :cond_0
    move v1, v3

    .line 386
    :goto_1
    invoke-virtual {v0, v1}, Lcom/miui/internal/view/menu/MenuItemImpl;->setIsActionButton(Z)V

    .line 387
    if-eqz v1, :cond_3

    .line 388
    add-int/lit8 v0, v4, -0x1

    .line 390
    :goto_2
    add-int/lit8 v5, v5, 0x1

    move v4, v0

    .line 391
    goto :goto_0

    :cond_1
    move v1, v2

    .line 385
    goto :goto_1

    .line 393
    :goto_3
    if-ge v1, v7, :cond_2

    .line 394
    invoke-virtual {v6, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/view/menu/MenuItemImpl;

    .line 395
    invoke-virtual {v0, v2}, Lcom/miui/internal/view/menu/MenuItemImpl;->setIsActionButton(Z)V

    .line 396
    add-int/lit8 v0, v1, 0x1

    move v1, v0

    .line 397
    goto :goto_3

    .line 398
    :cond_2
    return v3

    :cond_3
    move v0, v4

    goto :goto_2

    :cond_4
    move v1, v5

    goto :goto_3
.end method

.method public getItemView(Lcom/miui/internal/view/menu/MenuItemImpl;Landroid/view/View;Landroid/view/ViewGroup;)Landroid/view/View;
    .locals 3

    .prologue
    .line 161
    invoke-virtual {p1}, Lcom/miui/internal/view/menu/MenuItemImpl;->getActionView()Landroid/view/View;

    move-result-object v0

    .line 162
    if-eqz v0, :cond_0

    invoke-virtual {p1}, Lcom/miui/internal/view/menu/MenuItemImpl;->hasCollapsibleActionView()Z

    move-result v1

    if-eqz v1, :cond_2

    .line 163
    :cond_0
    instance-of v0, p2, Lcom/miui/internal/view/menu/ActionMenuItemView;

    if-nez v0, :cond_1

    .line 164
    const/4 p2, 0x0

    .line 166
    :cond_1
    invoke-super {p0, p1, p2, p3}, Lcom/miui/internal/view/menu/BaseMenuPresenter;->getItemView(Lcom/miui/internal/view/menu/MenuItemImpl;Landroid/view/View;Landroid/view/ViewGroup;)Landroid/view/View;

    move-result-object v0

    .line 168
    :cond_2
    invoke-virtual {p1}, Lcom/miui/internal/view/menu/MenuItemImpl;->isActionViewExpanded()Z

    move-result v1

    if-eqz v1, :cond_4

    const/16 v1, 0x8

    :goto_0
    invoke-virtual {v0, v1}, Landroid/view/View;->setVisibility(I)V

    .line 170
    check-cast p3, Lcom/miui/internal/view/menu/ActionMenuView;

    .line 171
    invoke-virtual {v0}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v1

    .line 172
    invoke-virtual {p3, v1}, Lcom/miui/internal/view/menu/ActionMenuView;->checkLayoutParams(Landroid/view/ViewGroup$LayoutParams;)Z

    move-result v2

    if-nez v2, :cond_3

    .line 173
    invoke-virtual {p3, v1}, Lcom/miui/internal/view/menu/ActionMenuView;->generateLayoutParams(Landroid/view/ViewGroup$LayoutParams;)Lcom/miui/internal/view/menu/ActionMenuView$LayoutParams;

    move-result-object v1

    invoke-virtual {v0, v1}, Landroid/view/View;->setLayoutParams(Landroid/view/ViewGroup$LayoutParams;)V

    .line 175
    :cond_3
    return-object v0

    .line 168
    :cond_4
    const/4 v1, 0x0

    goto :goto_0
.end method

.method public getMenuView(Landroid/view/ViewGroup;)Lcom/miui/internal/view/menu/MenuView;
    .locals 2

    .prologue
    .line 154
    invoke-super {p0, p1}, Lcom/miui/internal/view/menu/BaseMenuPresenter;->getMenuView(Landroid/view/ViewGroup;)Lcom/miui/internal/view/menu/MenuView;

    move-result-object v1

    move-object v0, v1

    .line 155
    check-cast v0, Lcom/miui/internal/view/menu/ActionMenuView;

    invoke-virtual {v0, p0}, Lcom/miui/internal/view/menu/ActionMenuView;->setPresenter(Lcom/miui/internal/view/menu/ActionMenuPresenter;)V

    .line 156
    return-object v1
.end method

.method public hideOverflowMenu(Z)Z
    .locals 2

    .prologue
    const/4 v0, 0x0

    .line 327
    iget-object v1, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mj:Landroid/view/View;

    invoke-virtual {v1, v0}, Landroid/view/View;->setSelected(Z)V

    .line 328
    iget-object v1, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mB:Lcom/miui/internal/view/menu/ActionMenuPresenter$a;

    if-eqz v1, :cond_1

    iget-object v1, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mMenuView:Lcom/miui/internal/view/menu/MenuView;

    if-eqz v1, :cond_1

    .line 329
    iget-object v0, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mMenuView:Lcom/miui/internal/view/menu/MenuView;

    check-cast v0, Landroid/view/View;

    iget-object v1, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mB:Lcom/miui/internal/view/menu/ActionMenuPresenter$a;

    invoke-virtual {v0, v1}, Landroid/view/View;->removeCallbacks(Ljava/lang/Runnable;)Z

    .line 330
    const/4 v0, 0x0

    iput-object v0, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mB:Lcom/miui/internal/view/menu/ActionMenuPresenter$a;

    .line 331
    const/4 v0, 0x1

    .line 339
    :cond_0
    :goto_0
    return v0

    .line 334
    :cond_1
    iget-object v1, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mx:Lcom/miui/internal/view/menu/ActionMenuPresenter$d;

    if-eqz v1, :cond_0

    .line 335
    iget-object v0, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mx:Lcom/miui/internal/view/menu/ActionMenuPresenter$d;

    invoke-interface {v0}, Lcom/miui/internal/view/menu/ActionMenuPresenter$d;->isShowing()Z

    move-result v0

    .line 336
    iget-object v1, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mx:Lcom/miui/internal/view/menu/ActionMenuPresenter$d;

    invoke-interface {v1, p1}, Lcom/miui/internal/view/menu/ActionMenuPresenter$d;->dismiss(Z)V

    goto :goto_0
.end method

.method public hideSubMenus()Z
    .locals 1

    .prologue
    .line 357
    iget-object v0, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mA:Lcom/miui/internal/view/menu/ActionMenuPresenter$h;

    if-eqz v0, :cond_0

    .line 358
    iget-object v0, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mA:Lcom/miui/internal/view/menu/ActionMenuPresenter$h;

    invoke-virtual {v0}, Lcom/miui/internal/view/menu/ActionMenuPresenter$h;->dismiss()V

    .line 359
    const/4 v0, 0x1

    .line 361
    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public initForMenu(Landroid/content/Context;Lcom/miui/internal/view/menu/MenuBuilder;)V
    .locals 4

    .prologue
    const/4 v3, 0x0

    const/4 v2, 0x0

    .line 80
    invoke-super {p0, p1, p2}, Lcom/miui/internal/view/menu/BaseMenuPresenter;->initForMenu(Landroid/content/Context;Lcom/miui/internal/view/menu/MenuBuilder;)V

    .line 82
    invoke-virtual {p1}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    .line 84
    invoke-static {p1}, Lcom/miui/internal/view/ActionBarPolicy;->get(Landroid/content/Context;)Lcom/miui/internal/view/ActionBarPolicy;

    move-result-object v0

    .line 85
    iget-boolean v1, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mk:Z

    if-nez v1, :cond_0

    .line 86
    invoke-virtual {v0}, Lcom/miui/internal/view/ActionBarPolicy;->showsOverflowMenuButton()Z

    move-result v1

    iput-boolean v1, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->b:Z

    .line 89
    :cond_0
    iget-boolean v1, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->ms:Z

    if-nez v1, :cond_1

    .line 90
    invoke-virtual {v0}, Lcom/miui/internal/view/ActionBarPolicy;->getEmbeddedMenuWidthLimit()I

    move-result v1

    iput v1, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->ml:I

    .line 94
    :cond_1
    iget-boolean v1, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mq:Z

    if-nez v1, :cond_2

    .line 95
    invoke-virtual {v0}, Lcom/miui/internal/view/ActionBarPolicy;->getMaxActionButtons()I

    move-result v0

    iput v0, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mn:I

    .line 98
    :cond_2
    iget v0, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->ml:I

    .line 99
    iget-boolean v1, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->b:Z

    if-eqz v1, :cond_4

    .line 100
    iget-object v1, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mj:Landroid/view/View;

    if-nez v1, :cond_3

    .line 101
    iget-object v1, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mSystemContext:Landroid/content/Context;

    invoke-virtual {p0, v1}, Lcom/miui/internal/view/menu/ActionMenuPresenter;->createOverflowMenuButton(Landroid/content/Context;)Landroid/view/View;

    move-result-object v1

    iput-object v1, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mj:Landroid/view/View;

    .line 102
    invoke-static {v2, v2}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result v1

    .line 103
    iget-object v2, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mj:Landroid/view/View;

    invoke-virtual {v2, v1, v1}, Landroid/view/View;->measure(II)V

    .line 105
    :cond_3
    iget-object v1, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mj:Landroid/view/View;

    invoke-virtual {v1}, Landroid/view/View;->getMeasuredWidth()I

    move-result v1

    sub-int/2addr v0, v1

    .line 110
    :goto_0
    iput v0, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mm:I

    .line 113
    iput-object v3, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mw:Landroid/view/View;

    .line 114
    return-void

    .line 107
    :cond_4
    iput-object v3, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mj:Landroid/view/View;

    goto :goto_0
.end method

.method public isOverflowMenuShowing()Z
    .locals 1

    .prologue
    .line 368
    iget-object v0, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mx:Lcom/miui/internal/view/menu/ActionMenuPresenter$d;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mx:Lcom/miui/internal/view/menu/ActionMenuPresenter$d;

    invoke-interface {v0}, Lcom/miui/internal/view/menu/ActionMenuPresenter$d;->isShowing()Z

    move-result v0

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public isOverflowReserved()Z
    .locals 1

    .prologue
    .line 375
    iget-boolean v0, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->b:Z

    return v0
.end method

.method public onCloseMenu(Lcom/miui/internal/view/menu/MenuBuilder;Z)V
    .locals 1

    .prologue
    .line 403
    const/4 v0, 0x1

    invoke-virtual {p0, v0}, Lcom/miui/internal/view/menu/ActionMenuPresenter;->dismissPopupMenus(Z)Z

    .line 404
    invoke-super {p0, p1, p2}, Lcom/miui/internal/view/menu/BaseMenuPresenter;->onCloseMenu(Lcom/miui/internal/view/menu/MenuBuilder;Z)V

    .line 405
    return-void
.end method

.method public onConfigurationChanged(Landroid/content/res/Configuration;)V
    .locals 2

    .prologue
    .line 117
    iget-boolean v0, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mq:Z

    if-nez v0, :cond_0

    .line 118
    iget-object v0, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mContext:Landroid/content/Context;

    invoke-virtual {v0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    sget v1, Lcom/miui/internal/R$integer;->abc_max_action_buttons:I

    invoke-virtual {v0, v1}, Landroid/content/res/Resources;->getInteger(I)I

    move-result v0

    iput v0, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mn:I

    .line 121
    :cond_0
    iget-object v0, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    if-eqz v0, :cond_1

    .line 122
    iget-object v0, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Lcom/miui/internal/view/menu/MenuBuilder;->d(Z)V

    .line 124
    :cond_1
    return-void
.end method

.method public onRestoreInstanceState(Landroid/os/Parcelable;)V
    .locals 2

    .prologue
    .line 416
    check-cast p1, Lcom/miui/internal/view/menu/ActionMenuPresenter$c;

    .line 417
    iget v0, p1, Lcom/miui/internal/view/menu/ActionMenuPresenter$c;->lK:I

    if-lez v0, :cond_0

    .line 418
    iget-object v0, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    iget v1, p1, Lcom/miui/internal/view/menu/ActionMenuPresenter$c;->lK:I

    invoke-virtual {v0, v1}, Lcom/miui/internal/view/menu/MenuBuilder;->findItem(I)Landroid/view/MenuItem;

    move-result-object v0

    .line 419
    if-eqz v0, :cond_0

    .line 420
    invoke-interface {v0}, Landroid/view/MenuItem;->getSubMenu()Landroid/view/SubMenu;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/view/menu/SubMenuBuilder;

    .line 421
    invoke-virtual {p0, v0}, Lcom/miui/internal/view/menu/ActionMenuPresenter;->onSubMenuSelected(Lcom/miui/internal/view/menu/SubMenuBuilder;)Z

    .line 424
    :cond_0
    return-void
.end method

.method public onSaveInstanceState()Landroid/os/Parcelable;
    .locals 2

    .prologue
    .line 409
    new-instance v0, Lcom/miui/internal/view/menu/ActionMenuPresenter$c;

    invoke-direct {v0}, Lcom/miui/internal/view/menu/ActionMenuPresenter$c;-><init>()V

    .line 410
    iget v1, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mD:I

    iput v1, v0, Lcom/miui/internal/view/menu/ActionMenuPresenter$c;->lK:I

    .line 411
    return-object v0
.end method

.method public onSubMenuSelected(Lcom/miui/internal/view/menu/SubMenuBuilder;)Z
    .locals 4

    .prologue
    const/4 v1, 0x0

    .line 238
    invoke-virtual {p1}, Lcom/miui/internal/view/menu/SubMenuBuilder;->hasVisibleItems()Z

    move-result v0

    if-nez v0, :cond_0

    move v0, v1

    .line 258
    :goto_0
    return v0

    :cond_0
    move-object v0, p1

    .line 243
    :goto_1
    invoke-virtual {v0}, Lcom/miui/internal/view/menu/SubMenuBuilder;->getParentMenu()Landroid/view/Menu;

    move-result-object v2

    iget-object v3, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    if-eq v2, v3, :cond_1

    .line 244
    invoke-virtual {v0}, Lcom/miui/internal/view/menu/SubMenuBuilder;->getParentMenu()Landroid/view/Menu;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/view/menu/SubMenuBuilder;

    goto :goto_1

    .line 246
    :cond_1
    invoke-virtual {v0}, Lcom/miui/internal/view/menu/SubMenuBuilder;->getItem()Landroid/view/MenuItem;

    move-result-object v0

    invoke-direct {p0, v0}, Lcom/miui/internal/view/menu/ActionMenuPresenter;->b(Landroid/view/MenuItem;)Landroid/view/View;

    move-result-object v0

    .line 247
    if-nez v0, :cond_3

    .line 248
    iget-object v0, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mj:Landroid/view/View;

    if-nez v0, :cond_2

    move v0, v1

    .line 249
    goto :goto_0

    .line 251
    :cond_2
    iget-object v0, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mj:Landroid/view/View;

    .line 254
    :cond_3
    invoke-virtual {p1}, Lcom/miui/internal/view/menu/SubMenuBuilder;->getItem()Landroid/view/MenuItem;

    move-result-object v0

    invoke-interface {v0}, Landroid/view/MenuItem;->getItemId()I

    move-result v0

    iput v0, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mD:I

    .line 255
    new-instance v0, Lcom/miui/internal/view/menu/ActionMenuPresenter$h;

    invoke-direct {v0, p0, p1}, Lcom/miui/internal/view/menu/ActionMenuPresenter$h;-><init>(Lcom/miui/internal/view/menu/ActionMenuPresenter;Lcom/miui/internal/view/menu/SubMenuBuilder;)V

    iput-object v0, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mA:Lcom/miui/internal/view/menu/ActionMenuPresenter$h;

    .line 256
    iget-object v0, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mA:Lcom/miui/internal/view/menu/ActionMenuPresenter$h;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Lcom/miui/internal/view/menu/ActionMenuPresenter$h;->show(Landroid/os/IBinder;)V

    .line 257
    invoke-super {p0, p1}, Lcom/miui/internal/view/menu/BaseMenuPresenter;->onSubMenuSelected(Lcom/miui/internal/view/menu/SubMenuBuilder;)Z

    .line 258
    const/4 v0, 0x1

    goto :goto_0
.end method

.method public onSubUiVisibilityChanged(Z)V
    .locals 2

    .prologue
    .line 427
    if-eqz p1, :cond_0

    .line 429
    const/4 v0, 0x0

    invoke-super {p0, v0}, Lcom/miui/internal/view/menu/BaseMenuPresenter;->onSubMenuSelected(Lcom/miui/internal/view/menu/SubMenuBuilder;)Z

    .line 433
    :goto_0
    return-void

    .line 431
    :cond_0
    iget-object v0, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Lcom/miui/internal/view/menu/MenuBuilder;->c(Z)V

    goto :goto_0
.end method

.method public setActionEditMode(Z)V
    .locals 1

    .prologue
    .line 147
    if-eqz p1, :cond_0

    .line 148
    sget v0, Lcom/miui/internal/R$attr;->actionModeOverflowButtonStyle:I

    iput v0, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mu:I

    .line 150
    :cond_0
    return-void
.end method

.method public setExpandedActionViewsExclusive(Z)V
    .locals 0

    .prologue
    .line 143
    iput-boolean p1, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mt:Z

    .line 144
    return-void
.end method

.method public setItemLimit(I)V
    .locals 1

    .prologue
    .line 138
    iput p1, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mn:I

    .line 139
    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mq:Z

    .line 140
    return-void
.end method

.method public setReserveOverflow(Z)V
    .locals 1

    .prologue
    .line 133
    iput-boolean p1, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->b:Z

    .line 134
    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mk:Z

    .line 135
    return-void
.end method

.method public setWidthLimit(IZ)V
    .locals 1

    .prologue
    .line 127
    iput p1, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->ml:I

    .line 128
    iput-boolean p2, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mr:Z

    .line 129
    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->ms:Z

    .line 130
    return-void
.end method

.method public shouldIncludeItem(ILcom/miui/internal/view/menu/MenuItemImpl;)Z
    .locals 1

    .prologue
    .line 186
    invoke-virtual {p2}, Lcom/miui/internal/view/menu/MenuItemImpl;->isActionButton()Z

    move-result v0

    return v0
.end method

.method public showOverflowMenu()Z
    .locals 3

    .prologue
    const/4 v1, 0x1

    .line 284
    iget-boolean v0, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->b:Z

    if-eqz v0, :cond_0

    invoke-virtual {p0}, Lcom/miui/internal/view/menu/ActionMenuPresenter;->isOverflowMenuShowing()Z

    move-result v0

    if-nez v0, :cond_0

    iget-object v0, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mMenuView:Lcom/miui/internal/view/menu/MenuView;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mB:Lcom/miui/internal/view/menu/ActionMenuPresenter$a;

    if-nez v0, :cond_0

    .line 286
    invoke-direct {p0}, Lcom/miui/internal/view/menu/ActionMenuPresenter;->aM()Lcom/miui/internal/view/menu/ActionMenuPresenter$d;

    move-result-object v0

    .line 287
    new-instance v2, Lcom/miui/internal/view/menu/ActionMenuPresenter$a;

    invoke-direct {v2, p0, v0}, Lcom/miui/internal/view/menu/ActionMenuPresenter$a;-><init>(Lcom/miui/internal/view/menu/ActionMenuPresenter;Lcom/miui/internal/view/menu/ActionMenuPresenter$d;)V

    iput-object v2, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mB:Lcom/miui/internal/view/menu/ActionMenuPresenter$a;

    .line 289
    iget-object v0, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mMenuView:Lcom/miui/internal/view/menu/MenuView;

    check-cast v0, Landroid/view/View;

    iget-object v2, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mB:Lcom/miui/internal/view/menu/ActionMenuPresenter$a;

    invoke-virtual {v0, v2}, Landroid/view/View;->post(Ljava/lang/Runnable;)Z

    .line 293
    const/4 v0, 0x0

    invoke-super {p0, v0}, Lcom/miui/internal/view/menu/BaseMenuPresenter;->onSubMenuSelected(Lcom/miui/internal/view/menu/SubMenuBuilder;)Z

    .line 295
    iget-object v0, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mj:Landroid/view/View;

    invoke-virtual {v0, v1}, Landroid/view/View;->setSelected(Z)V

    move v0, v1

    .line 299
    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public updateMenuView(Z)V
    .locals 4

    .prologue
    const/4 v1, 0x1

    const/4 v2, 0x0

    .line 191
    invoke-super {p0, p1}, Lcom/miui/internal/view/menu/BaseMenuPresenter;->updateMenuView(Z)V

    .line 193
    iget-object v0, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mMenuView:Lcom/miui/internal/view/menu/MenuView;

    if-nez v0, :cond_1

    .line 235
    :cond_0
    :goto_0
    return-void

    .line 197
    :cond_1
    iget-object v0, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    if-eqz v0, :cond_5

    iget-object v0, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    invoke-virtual {v0}, Lcom/miui/internal/view/menu/MenuBuilder;->getNonActionItems()Ljava/util/ArrayList;

    move-result-object v0

    .line 201
    :goto_1
    iget-boolean v3, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->b:Z

    if-eqz v3, :cond_2

    if-eqz v0, :cond_2

    .line 202
    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v3

    .line 203
    if-ne v3, v1, :cond_7

    .line 204
    invoke-virtual {v0, v2}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/view/menu/MenuItemImpl;

    invoke-virtual {v0}, Lcom/miui/internal/view/menu/MenuItemImpl;->isActionViewExpanded()Z

    move-result v0

    if-nez v0, :cond_6

    move v0, v1

    :goto_2
    move v2, v0

    .line 210
    :cond_2
    :goto_3
    if-eqz v2, :cond_a

    .line 211
    iget-object v0, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mj:Landroid/view/View;

    if-nez v0, :cond_9

    .line 212
    iget-object v0, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mSystemContext:Landroid/content/Context;

    invoke-virtual {p0, v0}, Lcom/miui/internal/view/menu/ActionMenuPresenter;->createOverflowMenuButton(Landroid/content/Context;)Landroid/view/View;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mj:Landroid/view/View;

    .line 217
    :goto_4
    iget-object v0, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mj:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->getParent()Landroid/view/ViewParent;

    move-result-object v0

    check-cast v0, Landroid/view/ViewGroup;

    .line 218
    iget-object v1, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mMenuView:Lcom/miui/internal/view/menu/MenuView;

    if-eq v0, v1, :cond_4

    .line 219
    if-eqz v0, :cond_3

    .line 220
    iget-object v1, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mj:Landroid/view/View;

    invoke-virtual {v0, v1}, Landroid/view/ViewGroup;->removeView(Landroid/view/View;)V

    .line 222
    :cond_3
    iget-object v0, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mMenuView:Lcom/miui/internal/view/menu/MenuView;

    check-cast v0, Lcom/miui/internal/view/menu/ActionMenuView;

    .line 223
    iget-object v1, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mj:Landroid/view/View;

    invoke-virtual {v0}, Lcom/miui/internal/view/menu/ActionMenuView;->generateOverflowButtonLayoutParams()Lcom/miui/internal/view/menu/ActionMenuView$LayoutParams;

    move-result-object v2

    invoke-virtual {v0, v1, v2}, Lcom/miui/internal/view/menu/ActionMenuView;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 229
    :cond_4
    :goto_5
    iget-object v0, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mMenuView:Lcom/miui/internal/view/menu/MenuView;

    check-cast v0, Lcom/miui/internal/view/menu/ActionMenuView;

    iget-boolean v1, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->b:Z

    invoke-virtual {v0, v1}, Lcom/miui/internal/view/menu/ActionMenuView;->setOverflowReserved(Z)V

    .line 232
    sget-boolean v0, Lmiui/os/Build;->IS_TABLET:Z

    if-nez v0, :cond_0

    .line 233
    invoke-direct {p0}, Lcom/miui/internal/view/menu/ActionMenuPresenter;->aM()Lcom/miui/internal/view/menu/ActionMenuPresenter$d;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    invoke-interface {v0, v1}, Lcom/miui/internal/view/menu/ActionMenuPresenter$d;->e(Lcom/miui/internal/view/menu/MenuBuilder;)V

    goto :goto_0

    .line 197
    :cond_5
    const/4 v0, 0x0

    goto :goto_1

    :cond_6
    move v0, v2

    .line 204
    goto :goto_2

    .line 206
    :cond_7
    if-lez v3, :cond_8

    :goto_6
    move v2, v1

    goto :goto_3

    :cond_8
    move v1, v2

    goto :goto_6

    .line 215
    :cond_9
    iget-object v0, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mj:Landroid/view/View;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Landroid/view/View;->setTranslationY(F)V

    goto :goto_4

    .line 225
    :cond_a
    iget-object v0, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mj:Landroid/view/View;

    if-eqz v0, :cond_4

    iget-object v0, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mj:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->getParent()Landroid/view/ViewParent;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mMenuView:Lcom/miui/internal/view/menu/MenuView;

    if-ne v0, v1, :cond_4

    .line 226
    iget-object v0, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mMenuView:Lcom/miui/internal/view/menu/MenuView;

    check-cast v0, Landroid/view/ViewGroup;

    iget-object v1, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mj:Landroid/view/View;

    invoke-virtual {v0, v1}, Landroid/view/ViewGroup;->removeView(Landroid/view/View;)V

    goto :goto_5
.end method
