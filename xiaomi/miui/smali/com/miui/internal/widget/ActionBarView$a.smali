.class Lcom/miui/internal/widget/ActionBarView$a;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/miui/internal/view/menu/MenuPresenter;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/internal/widget/ActionBarView;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x2
    name = "a"
.end annotation


# instance fields
.field mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

.field mS:Lcom/miui/internal/view/menu/MenuItemImpl;

.field final synthetic mT:Lcom/miui/internal/widget/ActionBarView;


# direct methods
.method private constructor <init>(Lcom/miui/internal/widget/ActionBarView;)V
    .locals 0

    .prologue
    .line 1524
    iput-object p1, p0, Lcom/miui/internal/widget/ActionBarView$a;->mT:Lcom/miui/internal/widget/ActionBarView;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method synthetic constructor <init>(Lcom/miui/internal/widget/ActionBarView;Lcom/miui/internal/widget/d;)V
    .locals 0

    .prologue
    .line 1524
    invoke-direct {p0, p1}, Lcom/miui/internal/widget/ActionBarView$a;-><init>(Lcom/miui/internal/widget/ActionBarView;)V

    return-void
.end method


# virtual methods
.method public collapseItemActionView(Lcom/miui/internal/view/menu/MenuBuilder;Lcom/miui/internal/view/menu/MenuItemImpl;)Z
    .locals 5

    .prologue
    const/4 v4, 0x1

    const/4 v3, 0x0

    const/4 v2, 0x0

    .line 1628
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView$a;->mT:Lcom/miui/internal/widget/ActionBarView;

    iget-object v0, v0, Lcom/miui/internal/widget/ActionBarView;->ii:Landroid/view/View;

    instance-of v0, v0, Landroid/view/CollapsibleActionView;

    if-eqz v0, :cond_0

    .line 1629
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView$a;->mT:Lcom/miui/internal/widget/ActionBarView;

    iget-object v0, v0, Lcom/miui/internal/widget/ActionBarView;->ii:Landroid/view/View;

    check-cast v0, Landroid/view/CollapsibleActionView;

    invoke-interface {v0}, Landroid/view/CollapsibleActionView;->onActionViewCollapsed()V

    .line 1632
    :cond_0
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView$a;->mT:Lcom/miui/internal/widget/ActionBarView;

    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarView$a;->mT:Lcom/miui/internal/widget/ActionBarView;

    iget-object v1, v1, Lcom/miui/internal/widget/ActionBarView;->ii:Landroid/view/View;

    invoke-virtual {v0, v1}, Lcom/miui/internal/widget/ActionBarView;->removeView(Landroid/view/View;)V

    .line 1633
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView$a;->mT:Lcom/miui/internal/widget/ActionBarView;

    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarView$a;->mT:Lcom/miui/internal/widget/ActionBarView;

    invoke-static {v1}, Lcom/miui/internal/widget/ActionBarView;->f(Lcom/miui/internal/widget/ActionBarView;)Lcom/miui/internal/widget/ActionBarView$HomeView;

    move-result-object v1

    invoke-virtual {v0, v1}, Lcom/miui/internal/widget/ActionBarView;->removeView(Landroid/view/View;)V

    .line 1634
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView$a;->mT:Lcom/miui/internal/widget/ActionBarView;

    iput-object v3, v0, Lcom/miui/internal/widget/ActionBarView;->ii:Landroid/view/View;

    .line 1635
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView$a;->mT:Lcom/miui/internal/widget/ActionBarView;

    invoke-static {v0}, Lcom/miui/internal/widget/ActionBarView;->l(Lcom/miui/internal/widget/ActionBarView;)I

    move-result v0

    and-int/lit8 v0, v0, 0x2

    if-eqz v0, :cond_1

    .line 1636
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView$a;->mT:Lcom/miui/internal/widget/ActionBarView;

    invoke-static {v0}, Lcom/miui/internal/widget/ActionBarView;->g(Lcom/miui/internal/widget/ActionBarView;)Lcom/miui/internal/widget/ActionBarView$HomeView;

    move-result-object v0

    invoke-virtual {v0, v2}, Lcom/miui/internal/widget/ActionBarView$HomeView;->setVisibility(I)V

    .line 1638
    :cond_1
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView$a;->mT:Lcom/miui/internal/widget/ActionBarView;

    invoke-static {v0}, Lcom/miui/internal/widget/ActionBarView;->l(Lcom/miui/internal/widget/ActionBarView;)I

    move-result v0

    and-int/lit8 v0, v0, 0x8

    if-eqz v0, :cond_2

    .line 1639
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView$a;->mT:Lcom/miui/internal/widget/ActionBarView;

    invoke-static {v0}, Lcom/miui/internal/widget/ActionBarView;->h(Lcom/miui/internal/widget/ActionBarView;)Landroid/widget/LinearLayout;

    move-result-object v0

    if-nez v0, :cond_6

    .line 1640
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView$a;->mT:Lcom/miui/internal/widget/ActionBarView;

    invoke-static {v0}, Lcom/miui/internal/widget/ActionBarView;->m(Lcom/miui/internal/widget/ActionBarView;)V

    .line 1645
    :cond_2
    :goto_0
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView$a;->mT:Lcom/miui/internal/widget/ActionBarView;

    invoke-static {v0}, Lcom/miui/internal/widget/ActionBarView;->i(Lcom/miui/internal/widget/ActionBarView;)Lcom/miui/internal/widget/ScrollingTabContainerView;

    move-result-object v0

    if-eqz v0, :cond_3

    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView$a;->mT:Lcom/miui/internal/widget/ActionBarView;

    invoke-static {v0}, Lcom/miui/internal/widget/ActionBarView;->n(Lcom/miui/internal/widget/ActionBarView;)I

    move-result v0

    const/4 v1, 0x2

    if-ne v0, v1, :cond_3

    .line 1646
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView$a;->mT:Lcom/miui/internal/widget/ActionBarView;

    invoke-static {v0}, Lcom/miui/internal/widget/ActionBarView;->i(Lcom/miui/internal/widget/ActionBarView;)Lcom/miui/internal/widget/ScrollingTabContainerView;

    move-result-object v0

    invoke-virtual {v0, v2}, Lcom/miui/internal/widget/ScrollingTabContainerView;->setVisibility(I)V

    .line 1648
    :cond_3
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView$a;->mT:Lcom/miui/internal/widget/ActionBarView;

    invoke-static {v0}, Lcom/miui/internal/widget/ActionBarView;->j(Lcom/miui/internal/widget/ActionBarView;)Landroid/widget/Spinner;

    move-result-object v0

    if-eqz v0, :cond_4

    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView$a;->mT:Lcom/miui/internal/widget/ActionBarView;

    invoke-static {v0}, Lcom/miui/internal/widget/ActionBarView;->n(Lcom/miui/internal/widget/ActionBarView;)I

    move-result v0

    if-ne v0, v4, :cond_4

    .line 1649
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView$a;->mT:Lcom/miui/internal/widget/ActionBarView;

    invoke-static {v0}, Lcom/miui/internal/widget/ActionBarView;->j(Lcom/miui/internal/widget/ActionBarView;)Landroid/widget/Spinner;

    move-result-object v0

    invoke-virtual {v0, v2}, Landroid/widget/Spinner;->setVisibility(I)V

    .line 1651
    :cond_4
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView$a;->mT:Lcom/miui/internal/widget/ActionBarView;

    invoke-static {v0}, Lcom/miui/internal/widget/ActionBarView;->k(Lcom/miui/internal/widget/ActionBarView;)Landroid/view/View;

    move-result-object v0

    if-eqz v0, :cond_5

    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView$a;->mT:Lcom/miui/internal/widget/ActionBarView;

    invoke-static {v0}, Lcom/miui/internal/widget/ActionBarView;->l(Lcom/miui/internal/widget/ActionBarView;)I

    move-result v0

    and-int/lit8 v0, v0, 0x10

    if-eqz v0, :cond_5

    .line 1652
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView$a;->mT:Lcom/miui/internal/widget/ActionBarView;

    invoke-static {v0}, Lcom/miui/internal/widget/ActionBarView;->k(Lcom/miui/internal/widget/ActionBarView;)Landroid/view/View;

    move-result-object v0

    invoke-virtual {v0, v2}, Landroid/view/View;->setVisibility(I)V

    .line 1654
    :cond_5
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView$a;->mT:Lcom/miui/internal/widget/ActionBarView;

    invoke-static {v0}, Lcom/miui/internal/widget/ActionBarView;->f(Lcom/miui/internal/widget/ActionBarView;)Lcom/miui/internal/widget/ActionBarView$HomeView;

    move-result-object v0

    invoke-virtual {v0, v3}, Lcom/miui/internal/widget/ActionBarView$HomeView;->setIcon(Landroid/graphics/drawable/Drawable;)V

    .line 1655
    iput-object v3, p0, Lcom/miui/internal/widget/ActionBarView$a;->mS:Lcom/miui/internal/view/menu/MenuItemImpl;

    .line 1656
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView$a;->mT:Lcom/miui/internal/widget/ActionBarView;

    invoke-virtual {v0}, Lcom/miui/internal/widget/ActionBarView;->requestLayout()V

    .line 1657
    invoke-virtual {p2, v2}, Lcom/miui/internal/view/menu/MenuItemImpl;->setActionViewExpanded(Z)V

    .line 1659
    return v4

    .line 1642
    :cond_6
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView$a;->mT:Lcom/miui/internal/widget/ActionBarView;

    invoke-static {v0}, Lcom/miui/internal/widget/ActionBarView;->h(Lcom/miui/internal/widget/ActionBarView;)Landroid/widget/LinearLayout;

    move-result-object v0

    invoke-virtual {v0, v2}, Landroid/widget/LinearLayout;->setVisibility(I)V

    goto :goto_0
.end method

.method public expandItemActionView(Lcom/miui/internal/view/menu/MenuBuilder;Lcom/miui/internal/view/menu/MenuItemImpl;)Z
    .locals 5

    .prologue
    const/4 v4, 0x1

    const/16 v3, 0x8

    .line 1587
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView$a;->mT:Lcom/miui/internal/widget/ActionBarView;

    invoke-virtual {p2}, Lcom/miui/internal/view/menu/MenuItemImpl;->getActionView()Landroid/view/View;

    move-result-object v1

    iput-object v1, v0, Lcom/miui/internal/widget/ActionBarView;->ii:Landroid/view/View;

    .line 1588
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView$a;->mT:Lcom/miui/internal/widget/ActionBarView;

    invoke-static {v0}, Lcom/miui/internal/widget/ActionBarView;->d(Lcom/miui/internal/widget/ActionBarView;)V

    .line 1589
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView$a;->mT:Lcom/miui/internal/widget/ActionBarView;

    invoke-static {v0}, Lcom/miui/internal/widget/ActionBarView;->f(Lcom/miui/internal/widget/ActionBarView;)Lcom/miui/internal/widget/ActionBarView$HomeView;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarView$a;->mT:Lcom/miui/internal/widget/ActionBarView;

    invoke-static {v1}, Lcom/miui/internal/widget/ActionBarView;->e(Lcom/miui/internal/widget/ActionBarView;)Landroid/graphics/drawable/Drawable;

    move-result-object v1

    invoke-virtual {v1}, Landroid/graphics/drawable/Drawable;->getConstantState()Landroid/graphics/drawable/Drawable$ConstantState;

    move-result-object v1

    iget-object v2, p0, Lcom/miui/internal/widget/ActionBarView$a;->mT:Lcom/miui/internal/widget/ActionBarView;

    invoke-virtual {v2}, Lcom/miui/internal/widget/ActionBarView;->getResources()Landroid/content/res/Resources;

    move-result-object v2

    invoke-virtual {v1, v2}, Landroid/graphics/drawable/Drawable$ConstantState;->newDrawable(Landroid/content/res/Resources;)Landroid/graphics/drawable/Drawable;

    move-result-object v1

    invoke-virtual {v0, v1}, Lcom/miui/internal/widget/ActionBarView$HomeView;->setIcon(Landroid/graphics/drawable/Drawable;)V

    .line 1590
    iput-object p2, p0, Lcom/miui/internal/widget/ActionBarView$a;->mS:Lcom/miui/internal/view/menu/MenuItemImpl;

    .line 1591
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView$a;->mT:Lcom/miui/internal/widget/ActionBarView;

    iget-object v0, v0, Lcom/miui/internal/widget/ActionBarView;->ii:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->getParent()Landroid/view/ViewParent;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarView$a;->mT:Lcom/miui/internal/widget/ActionBarView;

    if-eq v0, v1, :cond_0

    .line 1592
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView$a;->mT:Lcom/miui/internal/widget/ActionBarView;

    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarView$a;->mT:Lcom/miui/internal/widget/ActionBarView;

    iget-object v1, v1, Lcom/miui/internal/widget/ActionBarView;->ii:Landroid/view/View;

    invoke-virtual {v0, v1}, Lcom/miui/internal/widget/ActionBarView;->addView(Landroid/view/View;)V

    .line 1594
    :cond_0
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView$a;->mT:Lcom/miui/internal/widget/ActionBarView;

    invoke-static {v0}, Lcom/miui/internal/widget/ActionBarView;->f(Lcom/miui/internal/widget/ActionBarView;)Lcom/miui/internal/widget/ActionBarView$HomeView;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/internal/widget/ActionBarView$HomeView;->getParent()Landroid/view/ViewParent;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarView$a;->mT:Lcom/miui/internal/widget/ActionBarView;

    if-eq v0, v1, :cond_1

    .line 1595
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView$a;->mT:Lcom/miui/internal/widget/ActionBarView;

    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarView$a;->mT:Lcom/miui/internal/widget/ActionBarView;

    invoke-static {v1}, Lcom/miui/internal/widget/ActionBarView;->f(Lcom/miui/internal/widget/ActionBarView;)Lcom/miui/internal/widget/ActionBarView$HomeView;

    move-result-object v1

    invoke-virtual {v0, v1}, Lcom/miui/internal/widget/ActionBarView;->addView(Landroid/view/View;)V

    .line 1598
    :cond_1
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView$a;->mT:Lcom/miui/internal/widget/ActionBarView;

    invoke-static {v0}, Lcom/miui/internal/widget/ActionBarView;->g(Lcom/miui/internal/widget/ActionBarView;)Lcom/miui/internal/widget/ActionBarView$HomeView;

    move-result-object v0

    if-eqz v0, :cond_2

    .line 1599
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView$a;->mT:Lcom/miui/internal/widget/ActionBarView;

    invoke-static {v0}, Lcom/miui/internal/widget/ActionBarView;->g(Lcom/miui/internal/widget/ActionBarView;)Lcom/miui/internal/widget/ActionBarView$HomeView;

    move-result-object v0

    invoke-virtual {v0, v3}, Lcom/miui/internal/widget/ActionBarView$HomeView;->setVisibility(I)V

    .line 1602
    :cond_2
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView$a;->mT:Lcom/miui/internal/widget/ActionBarView;

    invoke-static {v0}, Lcom/miui/internal/widget/ActionBarView;->h(Lcom/miui/internal/widget/ActionBarView;)Landroid/widget/LinearLayout;

    move-result-object v0

    if-eqz v0, :cond_3

    .line 1603
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView$a;->mT:Lcom/miui/internal/widget/ActionBarView;

    invoke-static {v0}, Lcom/miui/internal/widget/ActionBarView;->h(Lcom/miui/internal/widget/ActionBarView;)Landroid/widget/LinearLayout;

    move-result-object v0

    invoke-virtual {v0, v3}, Landroid/widget/LinearLayout;->setVisibility(I)V

    .line 1605
    :cond_3
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView$a;->mT:Lcom/miui/internal/widget/ActionBarView;

    invoke-static {v0}, Lcom/miui/internal/widget/ActionBarView;->i(Lcom/miui/internal/widget/ActionBarView;)Lcom/miui/internal/widget/ScrollingTabContainerView;

    move-result-object v0

    if-eqz v0, :cond_4

    .line 1606
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView$a;->mT:Lcom/miui/internal/widget/ActionBarView;

    invoke-static {v0}, Lcom/miui/internal/widget/ActionBarView;->i(Lcom/miui/internal/widget/ActionBarView;)Lcom/miui/internal/widget/ScrollingTabContainerView;

    move-result-object v0

    invoke-virtual {v0, v3}, Lcom/miui/internal/widget/ScrollingTabContainerView;->setVisibility(I)V

    .line 1608
    :cond_4
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView$a;->mT:Lcom/miui/internal/widget/ActionBarView;

    invoke-static {v0}, Lcom/miui/internal/widget/ActionBarView;->j(Lcom/miui/internal/widget/ActionBarView;)Landroid/widget/Spinner;

    move-result-object v0

    if-eqz v0, :cond_5

    .line 1609
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView$a;->mT:Lcom/miui/internal/widget/ActionBarView;

    invoke-static {v0}, Lcom/miui/internal/widget/ActionBarView;->j(Lcom/miui/internal/widget/ActionBarView;)Landroid/widget/Spinner;

    move-result-object v0

    invoke-virtual {v0, v3}, Landroid/widget/Spinner;->setVisibility(I)V

    .line 1611
    :cond_5
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView$a;->mT:Lcom/miui/internal/widget/ActionBarView;

    invoke-static {v0}, Lcom/miui/internal/widget/ActionBarView;->k(Lcom/miui/internal/widget/ActionBarView;)Landroid/view/View;

    move-result-object v0

    if-eqz v0, :cond_6

    .line 1612
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView$a;->mT:Lcom/miui/internal/widget/ActionBarView;

    invoke-static {v0}, Lcom/miui/internal/widget/ActionBarView;->k(Lcom/miui/internal/widget/ActionBarView;)Landroid/view/View;

    move-result-object v0

    invoke-virtual {v0, v3}, Landroid/view/View;->setVisibility(I)V

    .line 1614
    :cond_6
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView$a;->mT:Lcom/miui/internal/widget/ActionBarView;

    invoke-virtual {v0}, Lcom/miui/internal/widget/ActionBarView;->requestLayout()V

    .line 1615
    invoke-virtual {p2, v4}, Lcom/miui/internal/view/menu/MenuItemImpl;->setActionViewExpanded(Z)V

    .line 1617
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView$a;->mT:Lcom/miui/internal/widget/ActionBarView;

    iget-object v0, v0, Lcom/miui/internal/widget/ActionBarView;->ii:Landroid/view/View;

    instance-of v0, v0, Landroid/view/CollapsibleActionView;

    if-eqz v0, :cond_7

    .line 1618
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView$a;->mT:Lcom/miui/internal/widget/ActionBarView;

    iget-object v0, v0, Lcom/miui/internal/widget/ActionBarView;->ii:Landroid/view/View;

    check-cast v0, Landroid/view/CollapsibleActionView;

    invoke-interface {v0}, Landroid/view/CollapsibleActionView;->onActionViewExpanded()V

    .line 1621
    :cond_7
    return v4
.end method

.method public flagActionItems()Z
    .locals 1

    .prologue
    .line 1582
    const/4 v0, 0x0

    return v0
.end method

.method public getId()I
    .locals 1

    .prologue
    .line 1664
    const/4 v0, 0x0

    return v0
.end method

.method public getMenuView(Landroid/view/ViewGroup;)Lcom/miui/internal/view/menu/MenuView;
    .locals 1

    .prologue
    .line 1540
    const/4 v0, 0x0

    return-object v0
.end method

.method public initForMenu(Landroid/content/Context;Lcom/miui/internal/view/menu/MenuBuilder;)V
    .locals 2

    .prologue
    .line 1532
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView$a;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView$a;->mS:Lcom/miui/internal/view/menu/MenuItemImpl;

    if-eqz v0, :cond_0

    .line 1533
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView$a;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarView$a;->mS:Lcom/miui/internal/view/menu/MenuItemImpl;

    invoke-virtual {v0, v1}, Lcom/miui/internal/view/menu/MenuBuilder;->collapseItemActionView(Lcom/miui/internal/view/menu/MenuItemImpl;)Z

    .line 1535
    :cond_0
    iput-object p2, p0, Lcom/miui/internal/widget/ActionBarView$a;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    .line 1536
    return-void
.end method

.method public onCloseMenu(Lcom/miui/internal/view/menu/MenuBuilder;Z)V
    .locals 0

    .prologue
    .line 1578
    return-void
.end method

.method public onRestoreInstanceState(Landroid/os/Parcelable;)V
    .locals 0

    .prologue
    .line 1674
    return-void
.end method

.method public onSaveInstanceState()Landroid/os/Parcelable;
    .locals 1

    .prologue
    .line 1669
    const/4 v0, 0x0

    return-object v0
.end method

.method public onSubMenuSelected(Lcom/miui/internal/view/menu/SubMenuBuilder;)Z
    .locals 1

    .prologue
    .line 1573
    const/4 v0, 0x0

    return v0
.end method

.method public setCallback(Lcom/miui/internal/view/menu/MenuPresenter$Callback;)V
    .locals 0

    .prologue
    .line 1569
    return-void
.end method

.method public updateMenuView(Z)V
    .locals 5

    .prologue
    const/4 v0, 0x0

    .line 1546
    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarView$a;->mS:Lcom/miui/internal/view/menu/MenuItemImpl;

    if-eqz v1, :cond_1

    .line 1549
    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarView$a;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    if-eqz v1, :cond_0

    .line 1550
    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarView$a;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    invoke-virtual {v1}, Lcom/miui/internal/view/menu/MenuBuilder;->size()I

    move-result v2

    move v1, v0

    .line 1551
    :goto_0
    if-ge v1, v2, :cond_0

    .line 1552
    iget-object v3, p0, Lcom/miui/internal/widget/ActionBarView$a;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    invoke-virtual {v3, v1}, Lcom/miui/internal/view/menu/MenuBuilder;->getItem(I)Landroid/view/MenuItem;

    move-result-object v3

    .line 1553
    iget-object v4, p0, Lcom/miui/internal/widget/ActionBarView$a;->mS:Lcom/miui/internal/view/menu/MenuItemImpl;

    if-ne v3, v4, :cond_2

    .line 1554
    const/4 v0, 0x1

    .line 1560
    :cond_0
    if-nez v0, :cond_1

    .line 1562
    iget-object v0, p0, Lcom/miui/internal/widget/ActionBarView$a;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    iget-object v1, p0, Lcom/miui/internal/widget/ActionBarView$a;->mS:Lcom/miui/internal/view/menu/MenuItemImpl;

    invoke-virtual {p0, v0, v1}, Lcom/miui/internal/widget/ActionBarView$a;->collapseItemActionView(Lcom/miui/internal/view/menu/MenuBuilder;Lcom/miui/internal/view/menu/MenuItemImpl;)Z

    .line 1565
    :cond_1
    return-void

    .line 1551
    :cond_2
    add-int/lit8 v1, v1, 0x1

    goto :goto_0
.end method
