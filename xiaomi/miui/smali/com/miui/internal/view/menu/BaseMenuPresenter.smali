.class public abstract Lcom/miui/internal/view/menu/BaseMenuPresenter;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/miui/internal/view/menu/MenuPresenter;


# instance fields
.field private kl:Lcom/miui/internal/view/menu/MenuPresenter$Callback;

.field private km:I

.field private kn:I

.field private ko:I

.field protected mContext:Landroid/content/Context;

.field protected mInflater:Landroid/view/LayoutInflater;

.field protected mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

.field protected mMenuView:Lcom/miui/internal/view/menu/MenuView;

.field protected mSystemContext:Landroid/content/Context;

.field protected mSystemInflater:Landroid/view/LayoutInflater;


# direct methods
.method public constructor <init>(Landroid/content/Context;II)V
    .locals 1

    .prologue
    .line 44
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 45
    iput-object p1, p0, Lcom/miui/internal/view/menu/BaseMenuPresenter;->mSystemContext:Landroid/content/Context;

    .line 46
    invoke-static {p1}, Landroid/view/LayoutInflater;->from(Landroid/content/Context;)Landroid/view/LayoutInflater;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/internal/view/menu/BaseMenuPresenter;->mSystemInflater:Landroid/view/LayoutInflater;

    .line 47
    iput p2, p0, Lcom/miui/internal/view/menu/BaseMenuPresenter;->km:I

    .line 48
    iput p3, p0, Lcom/miui/internal/view/menu/BaseMenuPresenter;->kn:I

    .line 49
    return-void
.end method


# virtual methods
.method protected addItemView(Landroid/view/View;I)V
    .locals 1

    .prologue
    .line 116
    invoke-virtual {p1}, Landroid/view/View;->getParent()Landroid/view/ViewParent;

    move-result-object v0

    check-cast v0, Landroid/view/ViewGroup;

    .line 117
    if-eqz v0, :cond_0

    .line 118
    invoke-virtual {v0, p1}, Landroid/view/ViewGroup;->removeView(Landroid/view/View;)V

    .line 120
    :cond_0
    iget-object v0, p0, Lcom/miui/internal/view/menu/BaseMenuPresenter;->mMenuView:Lcom/miui/internal/view/menu/MenuView;

    check-cast v0, Landroid/view/ViewGroup;

    invoke-virtual {v0, p1, p2}, Landroid/view/ViewGroup;->addView(Landroid/view/View;I)V

    .line 121
    return-void
.end method

.method public abstract bindItemView(Lcom/miui/internal/view/menu/MenuItemImpl;Lcom/miui/internal/view/menu/MenuView$ItemView;)V
.end method

.method public collapseItemActionView(Lcom/miui/internal/view/menu/MenuBuilder;Lcom/miui/internal/view/menu/MenuItemImpl;)Z
    .locals 1

    .prologue
    .line 196
    const/4 v0, 0x0

    return v0
.end method

.method public createItemView(Landroid/view/ViewGroup;)Lcom/miui/internal/view/menu/MenuView$ItemView;
    .locals 3

    .prologue
    .line 133
    iget-object v0, p0, Lcom/miui/internal/view/menu/BaseMenuPresenter;->mSystemInflater:Landroid/view/LayoutInflater;

    iget v1, p0, Lcom/miui/internal/view/menu/BaseMenuPresenter;->kn:I

    const/4 v2, 0x0

    invoke-virtual {v0, v1, p1, v2}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;Z)Landroid/view/View;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/view/menu/MenuView$ItemView;

    return-object v0
.end method

.method public expandItemActionView(Lcom/miui/internal/view/menu/MenuBuilder;Lcom/miui/internal/view/menu/MenuItemImpl;)Z
    .locals 1

    .prologue
    .line 192
    const/4 v0, 0x0

    return v0
.end method

.method public flagActionItems()Z
    .locals 1

    .prologue
    .line 188
    const/4 v0, 0x0

    return v0
.end method

.method public getId()I
    .locals 1

    .prologue
    .line 200
    iget v0, p0, Lcom/miui/internal/view/menu/BaseMenuPresenter;->ko:I

    return v0
.end method

.method public getItemView(Lcom/miui/internal/view/menu/MenuItemImpl;Landroid/view/View;Landroid/view/ViewGroup;)Landroid/view/View;
    .locals 1

    .prologue
    .line 149
    instance-of v0, p2, Lcom/miui/internal/view/menu/MenuView$ItemView;

    if-eqz v0, :cond_0

    .line 150
    check-cast p2, Lcom/miui/internal/view/menu/MenuView$ItemView;

    move-object v0, p2

    .line 154
    :goto_0
    invoke-virtual {p0, p1, v0}, Lcom/miui/internal/view/menu/BaseMenuPresenter;->bindItemView(Lcom/miui/internal/view/menu/MenuItemImpl;Lcom/miui/internal/view/menu/MenuView$ItemView;)V

    .line 155
    check-cast v0, Landroid/view/View;

    return-object v0

    .line 152
    :cond_0
    invoke-virtual {p0, p3}, Lcom/miui/internal/view/menu/BaseMenuPresenter;->createItemView(Landroid/view/ViewGroup;)Lcom/miui/internal/view/menu/MenuView$ItemView;

    move-result-object v0

    goto :goto_0
.end method

.method public getMenuView(Landroid/view/ViewGroup;)Lcom/miui/internal/view/menu/MenuView;
    .locals 3

    .prologue
    .line 59
    iget-object v0, p0, Lcom/miui/internal/view/menu/BaseMenuPresenter;->mMenuView:Lcom/miui/internal/view/menu/MenuView;

    if-nez v0, :cond_0

    .line 60
    iget-object v0, p0, Lcom/miui/internal/view/menu/BaseMenuPresenter;->mSystemInflater:Landroid/view/LayoutInflater;

    iget v1, p0, Lcom/miui/internal/view/menu/BaseMenuPresenter;->km:I

    const/4 v2, 0x0

    invoke-virtual {v0, v1, p1, v2}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;Z)Landroid/view/View;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/view/menu/MenuView;

    iput-object v0, p0, Lcom/miui/internal/view/menu/BaseMenuPresenter;->mMenuView:Lcom/miui/internal/view/menu/MenuView;

    .line 61
    iget-object v0, p0, Lcom/miui/internal/view/menu/BaseMenuPresenter;->mMenuView:Lcom/miui/internal/view/menu/MenuView;

    iget-object v1, p0, Lcom/miui/internal/view/menu/BaseMenuPresenter;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    invoke-interface {v0, v1}, Lcom/miui/internal/view/menu/MenuView;->initialize(Lcom/miui/internal/view/menu/MenuBuilder;)V

    .line 62
    const/4 v0, 0x1

    invoke-virtual {p0, v0}, Lcom/miui/internal/view/menu/BaseMenuPresenter;->updateMenuView(Z)V

    .line 65
    :cond_0
    iget-object v0, p0, Lcom/miui/internal/view/menu/BaseMenuPresenter;->mMenuView:Lcom/miui/internal/view/menu/MenuView;

    return-object v0
.end method

.method public initForMenu(Landroid/content/Context;Lcom/miui/internal/view/menu/MenuBuilder;)V
    .locals 1

    .prologue
    .line 53
    iput-object p1, p0, Lcom/miui/internal/view/menu/BaseMenuPresenter;->mContext:Landroid/content/Context;

    .line 54
    iget-object v0, p0, Lcom/miui/internal/view/menu/BaseMenuPresenter;->mContext:Landroid/content/Context;

    invoke-static {v0}, Landroid/view/LayoutInflater;->from(Landroid/content/Context;)Landroid/view/LayoutInflater;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/internal/view/menu/BaseMenuPresenter;->mInflater:Landroid/view/LayoutInflater;

    .line 55
    iput-object p2, p0, Lcom/miui/internal/view/menu/BaseMenuPresenter;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    .line 56
    return-void
.end method

.method public onCloseMenu(Lcom/miui/internal/view/menu/MenuBuilder;Z)V
    .locals 1

    .prologue
    .line 178
    iget-object v0, p0, Lcom/miui/internal/view/menu/BaseMenuPresenter;->kl:Lcom/miui/internal/view/menu/MenuPresenter$Callback;

    if-eqz v0, :cond_0

    .line 179
    iget-object v0, p0, Lcom/miui/internal/view/menu/BaseMenuPresenter;->kl:Lcom/miui/internal/view/menu/MenuPresenter$Callback;

    invoke-interface {v0, p1, p2}, Lcom/miui/internal/view/menu/MenuPresenter$Callback;->onCloseMenu(Lcom/miui/internal/view/menu/MenuBuilder;Z)V

    .line 181
    :cond_0
    return-void
.end method

.method public onSubMenuSelected(Lcom/miui/internal/view/menu/SubMenuBuilder;)Z
    .locals 1

    .prologue
    .line 184
    iget-object v0, p0, Lcom/miui/internal/view/menu/BaseMenuPresenter;->kl:Lcom/miui/internal/view/menu/MenuPresenter$Callback;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/internal/view/menu/BaseMenuPresenter;->kl:Lcom/miui/internal/view/menu/MenuPresenter$Callback;

    invoke-interface {v0, p1}, Lcom/miui/internal/view/menu/MenuPresenter$Callback;->onOpenSubMenu(Lcom/miui/internal/view/menu/MenuBuilder;)Z

    move-result v0

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public setCallback(Lcom/miui/internal/view/menu/MenuPresenter$Callback;)V
    .locals 0

    .prologue
    .line 124
    iput-object p1, p0, Lcom/miui/internal/view/menu/BaseMenuPresenter;->kl:Lcom/miui/internal/view/menu/MenuPresenter$Callback;

    .line 125
    return-void
.end method

.method public setId(I)V
    .locals 0

    .prologue
    .line 204
    iput p1, p0, Lcom/miui/internal/view/menu/BaseMenuPresenter;->ko:I

    .line 205
    return-void
.end method

.method public shouldIncludeItem(ILcom/miui/internal/view/menu/MenuItemImpl;)Z
    .locals 1

    .prologue
    .line 174
    const/4 v0, 0x1

    return v0
.end method

.method public updateMenuView(Z)V
    .locals 8

    .prologue
    const/4 v5, 0x0

    .line 72
    iget-object v0, p0, Lcom/miui/internal/view/menu/BaseMenuPresenter;->mMenuView:Lcom/miui/internal/view/menu/MenuView;

    check-cast v0, Landroid/view/ViewGroup;

    .line 73
    if-nez v0, :cond_1

    .line 107
    :cond_0
    return-void

    .line 78
    :cond_1
    iget-object v1, p0, Lcom/miui/internal/view/menu/BaseMenuPresenter;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    if-eqz v1, :cond_7

    .line 79
    iget-object v1, p0, Lcom/miui/internal/view/menu/BaseMenuPresenter;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    invoke-virtual {v1}, Lcom/miui/internal/view/menu/MenuBuilder;->flagActionItems()V

    .line 80
    iget-object v1, p0, Lcom/miui/internal/view/menu/BaseMenuPresenter;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    invoke-virtual {v1}, Lcom/miui/internal/view/menu/MenuBuilder;->getVisibleItems()Ljava/util/ArrayList;

    move-result-object v1

    .line 81
    invoke-virtual {v1}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v6

    move v4, v5

    :goto_0
    invoke-interface {v6}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_5

    invoke-interface {v6}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/miui/internal/view/menu/MenuItemImpl;

    .line 82
    invoke-virtual {p0, v4, v1}, Lcom/miui/internal/view/menu/BaseMenuPresenter;->shouldIncludeItem(ILcom/miui/internal/view/menu/MenuItemImpl;)Z

    move-result v2

    if-eqz v2, :cond_6

    .line 83
    invoke-virtual {v0, v4}, Landroid/view/ViewGroup;->getChildAt(I)Landroid/view/View;

    move-result-object v3

    .line 84
    instance-of v2, v3, Lcom/miui/internal/view/menu/MenuView$ItemView;

    if-eqz v2, :cond_4

    move-object v2, v3

    check-cast v2, Lcom/miui/internal/view/menu/MenuView$ItemView;

    invoke-interface {v2}, Lcom/miui/internal/view/menu/MenuView$ItemView;->getItemData()Lcom/miui/internal/view/menu/MenuItemImpl;

    move-result-object v2

    .line 86
    :goto_1
    invoke-virtual {p0, v1, v3, v0}, Lcom/miui/internal/view/menu/BaseMenuPresenter;->getItemView(Lcom/miui/internal/view/menu/MenuItemImpl;Landroid/view/View;Landroid/view/ViewGroup;)Landroid/view/View;

    move-result-object v7

    .line 87
    if-eq v1, v2, :cond_2

    .line 89
    invoke-virtual {v7, v5}, Landroid/view/View;->setPressed(Z)V

    .line 93
    :cond_2
    if-eq v7, v3, :cond_3

    .line 94
    invoke-virtual {p0, v7, v4}, Lcom/miui/internal/view/menu/BaseMenuPresenter;->addItemView(Landroid/view/View;I)V

    .line 96
    :cond_3
    add-int/lit8 v1, v4, 0x1

    :goto_2
    move v4, v1

    .line 97
    goto :goto_0

    .line 84
    :cond_4
    const/4 v2, 0x0

    goto :goto_1

    .line 102
    :cond_5
    :goto_3
    invoke-virtual {v0}, Landroid/view/ViewGroup;->getChildCount()I

    move-result v1

    if-ge v4, v1, :cond_0

    .line 103
    iget-object v1, p0, Lcom/miui/internal/view/menu/BaseMenuPresenter;->mMenuView:Lcom/miui/internal/view/menu/MenuView;

    invoke-interface {v1, v4}, Lcom/miui/internal/view/menu/MenuView;->filterLeftoverView(I)Z

    move-result v1

    if-nez v1, :cond_5

    .line 104
    add-int/lit8 v4, v4, 0x1

    goto :goto_3

    :cond_6
    move v1, v4

    goto :goto_2

    :cond_7
    move v4, v5

    goto :goto_3
.end method
