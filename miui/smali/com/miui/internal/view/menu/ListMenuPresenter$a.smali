.class Lcom/miui/internal/view/menu/ListMenuPresenter$a;
.super Landroid/widget/BaseAdapter;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/internal/view/menu/ListMenuPresenter;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x2
    name = "a"
.end annotation


# instance fields
.field final synthetic Jl:Lcom/miui/internal/view/menu/ListMenuPresenter;

.field private aT:I


# direct methods
.method public constructor <init>(Lcom/miui/internal/view/menu/ListMenuPresenter;)V
    .locals 1

    .prologue
    .line 232
    iput-object p1, p0, Lcom/miui/internal/view/menu/ListMenuPresenter$a;->Jl:Lcom/miui/internal/view/menu/ListMenuPresenter;

    invoke-direct {p0}, Landroid/widget/BaseAdapter;-><init>()V

    .line 230
    const/4 v0, -0x1

    iput v0, p0, Lcom/miui/internal/view/menu/ListMenuPresenter$a;->aT:I

    .line 233
    invoke-virtual {p0}, Lcom/miui/internal/view/menu/ListMenuPresenter$a;->k()V

    .line 234
    return-void
.end method


# virtual methods
.method public getCount()I
    .locals 2

    .prologue
    .line 237
    iget-object v0, p0, Lcom/miui/internal/view/menu/ListMenuPresenter$a;->Jl:Lcom/miui/internal/view/menu/ListMenuPresenter;

    iget-object v0, v0, Lcom/miui/internal/view/menu/ListMenuPresenter;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    invoke-virtual {v0}, Lcom/miui/internal/view/menu/MenuBuilder;->getNonActionItems()Ljava/util/ArrayList;

    move-result-object v0

    .line 238
    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    iget-object v1, p0, Lcom/miui/internal/view/menu/ListMenuPresenter$a;->Jl:Lcom/miui/internal/view/menu/ListMenuPresenter;

    invoke-static {v1}, Lcom/miui/internal/view/menu/ListMenuPresenter;->a(Lcom/miui/internal/view/menu/ListMenuPresenter;)I

    move-result v1

    sub-int/2addr v0, v1

    .line 239
    iget v1, p0, Lcom/miui/internal/view/menu/ListMenuPresenter$a;->aT:I

    if-gez v1, :cond_0

    .line 242
    :goto_0
    return v0

    :cond_0
    add-int/lit8 v0, v0, -0x1

    goto :goto_0
.end method

.method public bridge synthetic getItem(I)Ljava/lang/Object;
    .locals 1

    .prologue
    .line 229
    invoke-virtual {p0, p1}, Lcom/miui/internal/view/menu/ListMenuPresenter$a;->i(I)Lcom/miui/internal/view/menu/MenuItemImpl;

    move-result-object v0

    return-object v0
.end method

.method public getItemId(I)J
    .locals 2

    .prologue
    .line 257
    int-to-long v0, p1

    return-wide v0
.end method

.method public getView(ILandroid/view/View;Landroid/view/ViewGroup;)Landroid/view/View;
    .locals 4

    .prologue
    const/4 v3, 0x0

    .line 261
    if-nez p2, :cond_0

    .line 262
    iget-object v0, p0, Lcom/miui/internal/view/menu/ListMenuPresenter$a;->Jl:Lcom/miui/internal/view/menu/ListMenuPresenter;

    iget-object v0, v0, Lcom/miui/internal/view/menu/ListMenuPresenter;->mInflater:Landroid/view/LayoutInflater;

    iget-object v1, p0, Lcom/miui/internal/view/menu/ListMenuPresenter$a;->Jl:Lcom/miui/internal/view/menu/ListMenuPresenter;

    iget v1, v1, Lcom/miui/internal/view/menu/ListMenuPresenter;->kn:I

    invoke-virtual {v0, v1, p3, v3}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;Z)Landroid/view/View;

    move-result-object v1

    :goto_0
    move-object v0, v1

    .line 265
    check-cast v0, Lcom/miui/internal/view/menu/MenuView$ItemView;

    .line 266
    invoke-virtual {p0, p1}, Lcom/miui/internal/view/menu/ListMenuPresenter$a;->i(I)Lcom/miui/internal/view/menu/MenuItemImpl;

    move-result-object v2

    invoke-interface {v0, v2, v3}, Lcom/miui/internal/view/menu/MenuView$ItemView;->initialize(Lcom/miui/internal/view/menu/MenuItemImpl;I)V

    .line 267
    return-object v1

    :cond_0
    move-object v1, p2

    goto :goto_0
.end method

.method public i(I)Lcom/miui/internal/view/menu/MenuItemImpl;
    .locals 3

    .prologue
    .line 246
    iget-object v0, p0, Lcom/miui/internal/view/menu/ListMenuPresenter$a;->Jl:Lcom/miui/internal/view/menu/ListMenuPresenter;

    iget-object v0, v0, Lcom/miui/internal/view/menu/ListMenuPresenter;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    invoke-virtual {v0}, Lcom/miui/internal/view/menu/MenuBuilder;->getNonActionItems()Ljava/util/ArrayList;

    move-result-object v1

    .line 247
    iget-object v0, p0, Lcom/miui/internal/view/menu/ListMenuPresenter$a;->Jl:Lcom/miui/internal/view/menu/ListMenuPresenter;

    invoke-static {v0}, Lcom/miui/internal/view/menu/ListMenuPresenter;->a(Lcom/miui/internal/view/menu/ListMenuPresenter;)I

    move-result v0

    add-int/2addr v0, p1

    .line 248
    iget v2, p0, Lcom/miui/internal/view/menu/ListMenuPresenter$a;->aT:I

    if-ltz v2, :cond_0

    iget v2, p0, Lcom/miui/internal/view/menu/ListMenuPresenter$a;->aT:I

    if-lt v0, v2, :cond_0

    .line 249
    add-int/lit8 v0, v0, 0x1

    .line 251
    :cond_0
    invoke-virtual {v1, v0}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/view/menu/MenuItemImpl;

    return-object v0
.end method

.method k()V
    .locals 5

    .prologue
    .line 271
    iget-object v0, p0, Lcom/miui/internal/view/menu/ListMenuPresenter$a;->Jl:Lcom/miui/internal/view/menu/ListMenuPresenter;

    iget-object v0, v0, Lcom/miui/internal/view/menu/ListMenuPresenter;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    invoke-virtual {v0}, Lcom/miui/internal/view/menu/MenuBuilder;->getExpandedItem()Lcom/miui/internal/view/menu/MenuItemImpl;

    move-result-object v2

    .line 272
    if-eqz v2, :cond_1

    .line 273
    iget-object v0, p0, Lcom/miui/internal/view/menu/ListMenuPresenter$a;->Jl:Lcom/miui/internal/view/menu/ListMenuPresenter;

    iget-object v0, v0, Lcom/miui/internal/view/menu/ListMenuPresenter;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    invoke-virtual {v0}, Lcom/miui/internal/view/menu/MenuBuilder;->getNonActionItems()Ljava/util/ArrayList;

    move-result-object v3

    .line 274
    invoke-virtual {v3}, Ljava/util/ArrayList;->size()I

    move-result v4

    .line 275
    const/4 v0, 0x0

    move v1, v0

    :goto_0
    if-ge v1, v4, :cond_1

    .line 276
    invoke-virtual {v3, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/view/menu/MenuItemImpl;

    .line 277
    if-ne v0, v2, :cond_0

    .line 278
    iput v1, p0, Lcom/miui/internal/view/menu/ListMenuPresenter$a;->aT:I

    .line 284
    :goto_1
    return-void

    .line 275
    :cond_0
    add-int/lit8 v0, v1, 0x1

    move v1, v0

    goto :goto_0

    .line 283
    :cond_1
    const/4 v0, -0x1

    iput v0, p0, Lcom/miui/internal/view/menu/ListMenuPresenter$a;->aT:I

    goto :goto_1
.end method

.method public notifyDataSetChanged()V
    .locals 0

    .prologue
    .line 288
    invoke-virtual {p0}, Lcom/miui/internal/view/menu/ListMenuPresenter$a;->k()V

    .line 289
    invoke-super {p0}, Landroid/widget/BaseAdapter;->notifyDataSetChanged()V

    .line 290
    return-void
.end method
