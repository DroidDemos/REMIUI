.class Lcom/miui/internal/view/menu/MenuPopupHelper$a;
.super Landroid/widget/BaseAdapter;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/internal/view/menu/MenuPopupHelper;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x2
    name = "a"
.end annotation


# instance fields
.field private aS:Lcom/miui/internal/view/menu/MenuBuilder;

.field private aT:I

.field final synthetic aU:Lcom/miui/internal/view/menu/MenuPopupHelper;


# direct methods
.method public constructor <init>(Lcom/miui/internal/view/menu/MenuPopupHelper;Lcom/miui/internal/view/menu/MenuBuilder;)V
    .locals 1

    .prologue
    .line 312
    iput-object p1, p0, Lcom/miui/internal/view/menu/MenuPopupHelper$a;->aU:Lcom/miui/internal/view/menu/MenuPopupHelper;

    invoke-direct {p0}, Landroid/widget/BaseAdapter;-><init>()V

    .line 310
    const/4 v0, -0x1

    iput v0, p0, Lcom/miui/internal/view/menu/MenuPopupHelper$a;->aT:I

    .line 313
    iput-object p2, p0, Lcom/miui/internal/view/menu/MenuPopupHelper$a;->aS:Lcom/miui/internal/view/menu/MenuBuilder;

    .line 314
    invoke-virtual {p0}, Lcom/miui/internal/view/menu/MenuPopupHelper$a;->k()V

    .line 315
    return-void
.end method

.method static synthetic a(Lcom/miui/internal/view/menu/MenuPopupHelper$a;)Lcom/miui/internal/view/menu/MenuBuilder;
    .locals 1

    .prologue
    .line 306
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuPopupHelper$a;->aS:Lcom/miui/internal/view/menu/MenuBuilder;

    return-object v0
.end method


# virtual methods
.method public getCount()I
    .locals 2

    .prologue
    .line 318
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuPopupHelper$a;->aU:Lcom/miui/internal/view/menu/MenuPopupHelper;

    invoke-static {v0}, Lcom/miui/internal/view/menu/MenuPopupHelper;->a(Lcom/miui/internal/view/menu/MenuPopupHelper;)Z

    move-result v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuPopupHelper$a;->aS:Lcom/miui/internal/view/menu/MenuBuilder;

    invoke-virtual {v0}, Lcom/miui/internal/view/menu/MenuBuilder;->getNonActionItems()Ljava/util/ArrayList;

    move-result-object v0

    .line 320
    :goto_0
    iget v1, p0, Lcom/miui/internal/view/menu/MenuPopupHelper$a;->aT:I

    if-gez v1, :cond_1

    .line 321
    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    .line 323
    :goto_1
    return v0

    .line 318
    :cond_0
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuPopupHelper$a;->aS:Lcom/miui/internal/view/menu/MenuBuilder;

    invoke-virtual {v0}, Lcom/miui/internal/view/menu/MenuBuilder;->getVisibleItems()Ljava/util/ArrayList;

    move-result-object v0

    goto :goto_0

    .line 323
    :cond_1
    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    add-int/lit8 v0, v0, -0x1

    goto :goto_1
.end method

.method public bridge synthetic getItem(I)Ljava/lang/Object;
    .locals 1

    .prologue
    .line 306
    invoke-virtual {p0, p1}, Lcom/miui/internal/view/menu/MenuPopupHelper$a;->i(I)Lcom/miui/internal/view/menu/MenuItemImpl;

    move-result-object v0

    return-object v0
.end method

.method public getItemId(I)J
    .locals 2

    .prologue
    .line 338
    int-to-long v0, p1

    return-wide v0
.end method

.method public getView(ILandroid/view/View;Landroid/view/ViewGroup;)Landroid/view/View;
    .locals 5

    .prologue
    const/4 v4, 0x0

    .line 342
    if-nez p2, :cond_1

    .line 343
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuPopupHelper$a;->aU:Lcom/miui/internal/view/menu/MenuPopupHelper;

    invoke-static {v0}, Lcom/miui/internal/view/menu/MenuPopupHelper;->c(Lcom/miui/internal/view/menu/MenuPopupHelper;)Landroid/view/LayoutInflater;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/internal/view/menu/MenuPopupHelper$a;->aU:Lcom/miui/internal/view/menu/MenuPopupHelper;

    invoke-static {v1}, Lcom/miui/internal/view/menu/MenuPopupHelper;->b(Lcom/miui/internal/view/menu/MenuPopupHelper;)I

    move-result v1

    invoke-virtual {v0, v1, p3, v4}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;Z)Landroid/view/View;

    move-result-object v1

    :goto_0
    move-object v0, v1

    .line 346
    check-cast v0, Lcom/miui/internal/view/menu/MenuView$ItemView;

    .line 347
    iget-object v2, p0, Lcom/miui/internal/view/menu/MenuPopupHelper$a;->aU:Lcom/miui/internal/view/menu/MenuPopupHelper;

    iget-boolean v2, v2, Lcom/miui/internal/view/menu/MenuPopupHelper;->nt:Z

    if-eqz v2, :cond_0

    move-object v2, v1

    .line 348
    check-cast v2, Lcom/miui/internal/view/menu/ListMenuItemView;

    const/4 v3, 0x1

    invoke-virtual {v2, v3}, Lcom/miui/internal/view/menu/ListMenuItemView;->setForceShowIcon(Z)V

    .line 350
    :cond_0
    invoke-virtual {p0, p1}, Lcom/miui/internal/view/menu/MenuPopupHelper$a;->i(I)Lcom/miui/internal/view/menu/MenuItemImpl;

    move-result-object v2

    invoke-interface {v0, v2, v4}, Lcom/miui/internal/view/menu/MenuView$ItemView;->initialize(Lcom/miui/internal/view/menu/MenuItemImpl;I)V

    .line 351
    return-object v1

    :cond_1
    move-object v1, p2

    goto :goto_0
.end method

.method public i(I)Lcom/miui/internal/view/menu/MenuItemImpl;
    .locals 2

    .prologue
    .line 327
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuPopupHelper$a;->aU:Lcom/miui/internal/view/menu/MenuPopupHelper;

    invoke-static {v0}, Lcom/miui/internal/view/menu/MenuPopupHelper;->a(Lcom/miui/internal/view/menu/MenuPopupHelper;)Z

    move-result v0

    if-eqz v0, :cond_1

    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuPopupHelper$a;->aS:Lcom/miui/internal/view/menu/MenuBuilder;

    invoke-virtual {v0}, Lcom/miui/internal/view/menu/MenuBuilder;->getNonActionItems()Ljava/util/ArrayList;

    move-result-object v0

    .line 329
    :goto_0
    iget v1, p0, Lcom/miui/internal/view/menu/MenuPopupHelper$a;->aT:I

    if-ltz v1, :cond_0

    iget v1, p0, Lcom/miui/internal/view/menu/MenuPopupHelper$a;->aT:I

    if-lt p1, v1, :cond_0

    .line 330
    add-int/lit8 p1, p1, 0x1

    .line 332
    :cond_0
    invoke-virtual {v0, p1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/view/menu/MenuItemImpl;

    return-object v0

    .line 327
    :cond_1
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuPopupHelper$a;->aS:Lcom/miui/internal/view/menu/MenuBuilder;

    invoke-virtual {v0}, Lcom/miui/internal/view/menu/MenuBuilder;->getVisibleItems()Ljava/util/ArrayList;

    move-result-object v0

    goto :goto_0
.end method

.method k()V
    .locals 5

    .prologue
    .line 355
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuPopupHelper$a;->aU:Lcom/miui/internal/view/menu/MenuPopupHelper;

    invoke-static {v0}, Lcom/miui/internal/view/menu/MenuPopupHelper;->d(Lcom/miui/internal/view/menu/MenuPopupHelper;)Lcom/miui/internal/view/menu/MenuBuilder;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/internal/view/menu/MenuBuilder;->getExpandedItem()Lcom/miui/internal/view/menu/MenuItemImpl;

    move-result-object v2

    .line 356
    if-eqz v2, :cond_1

    .line 357
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuPopupHelper$a;->aU:Lcom/miui/internal/view/menu/MenuPopupHelper;

    invoke-static {v0}, Lcom/miui/internal/view/menu/MenuPopupHelper;->d(Lcom/miui/internal/view/menu/MenuPopupHelper;)Lcom/miui/internal/view/menu/MenuBuilder;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/internal/view/menu/MenuBuilder;->getNonActionItems()Ljava/util/ArrayList;

    move-result-object v3

    .line 358
    invoke-virtual {v3}, Ljava/util/ArrayList;->size()I

    move-result v4

    .line 359
    const/4 v0, 0x0

    move v1, v0

    :goto_0
    if-ge v1, v4, :cond_1

    .line 360
    invoke-virtual {v3, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/view/menu/MenuItemImpl;

    .line 361
    if-ne v0, v2, :cond_0

    .line 362
    iput v1, p0, Lcom/miui/internal/view/menu/MenuPopupHelper$a;->aT:I

    .line 368
    :goto_1
    return-void

    .line 359
    :cond_0
    add-int/lit8 v0, v1, 0x1

    move v1, v0

    goto :goto_0

    .line 367
    :cond_1
    const/4 v0, -0x1

    iput v0, p0, Lcom/miui/internal/view/menu/MenuPopupHelper$a;->aT:I

    goto :goto_1
.end method

.method public notifyDataSetChanged()V
    .locals 0

    .prologue
    .line 372
    invoke-virtual {p0}, Lcom/miui/internal/view/menu/MenuPopupHelper$a;->k()V

    .line 373
    invoke-super {p0}, Landroid/widget/BaseAdapter;->notifyDataSetChanged()V

    .line 374
    return-void
.end method
