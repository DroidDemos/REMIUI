.class abstract Lcom/miui/internal/widget/g;
.super Landroid/view/ViewGroup;
.source "SourceFile"


# instance fields
.field protected mActionMenuPresenter:Lcom/miui/internal/view/menu/ActionMenuPresenter;

.field protected mContentHeight:I

.field protected mMenuView:Lcom/miui/internal/view/menu/ActionMenuView;

.field protected mSplitActionBar:Z

.field protected mSplitView:Lcom/miui/internal/widget/ActionBarContainer;

.field protected mSplitWhenNarrow:Z


# direct methods
.method constructor <init>(Landroid/content/Context;)V
    .locals 0

    .prologue
    .line 41
    invoke-direct {p0, p1}, Landroid/view/ViewGroup;-><init>(Landroid/content/Context;)V

    .line 42
    return-void
.end method

.method constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 0

    .prologue
    .line 45
    invoke-direct {p0, p1, p2}, Landroid/view/ViewGroup;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    .line 46
    return-void
.end method

.method constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V
    .locals 0

    .prologue
    .line 49
    invoke-direct {p0, p1, p2, p3}, Landroid/view/ViewGroup;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    .line 50
    return-void
.end method


# virtual methods
.method public animateToVisibility(I)V
    .locals 2

    .prologue
    .line 119
    invoke-virtual {p0}, Lcom/miui/internal/widget/g;->clearAnimation()V

    .line 121
    invoke-virtual {p0}, Lcom/miui/internal/widget/g;->getVisibility()I

    move-result v0

    if-eq p1, v0, :cond_0

    .line 122
    invoke-virtual {p0}, Lcom/miui/internal/widget/g;->getContext()Landroid/content/Context;

    move-result-object v1

    if-nez p1, :cond_1

    sget v0, Lcom/miui/internal/R$anim;->action_bar_fade_in:I

    :goto_0
    invoke-static {v1, v0}, Landroid/view/animation/AnimationUtils;->loadAnimation(Landroid/content/Context;I)Landroid/view/animation/Animation;

    move-result-object v0

    .line 126
    invoke-virtual {p0, v0}, Lcom/miui/internal/widget/g;->startAnimation(Landroid/view/animation/Animation;)V

    .line 127
    invoke-virtual {p0, p1}, Lcom/miui/internal/widget/g;->setVisibility(I)V

    .line 129
    iget-object v1, p0, Lcom/miui/internal/widget/g;->mSplitView:Lcom/miui/internal/widget/ActionBarContainer;

    if-eqz v1, :cond_0

    iget-object v1, p0, Lcom/miui/internal/widget/g;->mMenuView:Lcom/miui/internal/view/menu/ActionMenuView;

    if-eqz v1, :cond_0

    .line 130
    iget-object v1, p0, Lcom/miui/internal/widget/g;->mMenuView:Lcom/miui/internal/view/menu/ActionMenuView;

    invoke-virtual {v1, v0}, Lcom/miui/internal/view/menu/ActionMenuView;->startAnimation(Landroid/view/animation/Animation;)V

    .line 131
    iget-object v0, p0, Lcom/miui/internal/widget/g;->mMenuView:Lcom/miui/internal/view/menu/ActionMenuView;

    invoke-virtual {v0, p1}, Lcom/miui/internal/view/menu/ActionMenuView;->setVisibility(I)V

    .line 134
    :cond_0
    return-void

    .line 122
    :cond_1
    sget v0, Lcom/miui/internal/R$anim;->action_bar_fade_out:I

    goto :goto_0
.end method

.method bX()I
    .locals 1

    .prologue
    .line 73
    const v0, 0x10102ce

    return v0
.end method

.method public dismissPopupMenus()V
    .locals 2

    .prologue
    .line 168
    iget-object v0, p0, Lcom/miui/internal/widget/g;->mActionMenuPresenter:Lcom/miui/internal/view/menu/ActionMenuPresenter;

    if-eqz v0, :cond_0

    .line 169
    iget-object v0, p0, Lcom/miui/internal/widget/g;->mActionMenuPresenter:Lcom/miui/internal/view/menu/ActionMenuPresenter;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Lcom/miui/internal/view/menu/ActionMenuPresenter;->dismissPopupMenus(Z)Z

    .line 171
    :cond_0
    return-void
.end method

.method public getActionMenuView()Lcom/miui/internal/view/menu/ActionMenuView;
    .locals 1

    .prologue
    .line 115
    iget-object v0, p0, Lcom/miui/internal/widget/g;->mMenuView:Lcom/miui/internal/view/menu/ActionMenuView;

    return-object v0
.end method

.method public getAnimatedVisibility()I
    .locals 1

    .prologue
    .line 111
    invoke-virtual {p0}, Lcom/miui/internal/widget/g;->getVisibility()I

    move-result v0

    return v0
.end method

.method public getContentHeight()I
    .locals 1

    .prologue
    .line 100
    iget v0, p0, Lcom/miui/internal/widget/g;->mContentHeight:I

    return v0
.end method

.method public getMenuView()Lcom/miui/internal/view/menu/ActionMenuView;
    .locals 1

    .prologue
    .line 205
    iget-object v0, p0, Lcom/miui/internal/widget/g;->mMenuView:Lcom/miui/internal/view/menu/ActionMenuView;

    return-object v0
.end method

.method public hideOverflowMenu()Z
    .locals 2

    .prologue
    const/4 v0, 0x0

    .line 156
    iget-object v1, p0, Lcom/miui/internal/widget/g;->mActionMenuPresenter:Lcom/miui/internal/view/menu/ActionMenuPresenter;

    if-eqz v1, :cond_0

    iget-object v1, p0, Lcom/miui/internal/widget/g;->mActionMenuPresenter:Lcom/miui/internal/view/menu/ActionMenuPresenter;

    invoke-virtual {v1, v0}, Lcom/miui/internal/view/menu/ActionMenuPresenter;->hideOverflowMenu(Z)Z

    move-result v1

    if-eqz v1, :cond_0

    const/4 v0, 0x1

    :cond_0
    return v0
.end method

.method public isOverflowMenuShowing()Z
    .locals 1

    .prologue
    .line 160
    iget-object v0, p0, Lcom/miui/internal/widget/g;->mActionMenuPresenter:Lcom/miui/internal/view/menu/ActionMenuPresenter;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/internal/widget/g;->mActionMenuPresenter:Lcom/miui/internal/view/menu/ActionMenuPresenter;

    invoke-virtual {v0}, Lcom/miui/internal/view/menu/ActionMenuPresenter;->isOverflowMenuShowing()Z

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
    .line 164
    iget-object v0, p0, Lcom/miui/internal/widget/g;->mActionMenuPresenter:Lcom/miui/internal/view/menu/ActionMenuPresenter;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/internal/widget/g;->mActionMenuPresenter:Lcom/miui/internal/view/menu/ActionMenuPresenter;

    invoke-virtual {v0}, Lcom/miui/internal/view/menu/ActionMenuPresenter;->isOverflowReserved()Z

    move-result v0

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method protected measureChildView(Landroid/view/View;III)I
    .locals 2

    .prologue
    .line 175
    const/high16 v0, -0x80000000

    invoke-static {p2, v0}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result v0

    invoke-virtual {p1, v0, p3}, Landroid/view/View;->measure(II)V

    .line 178
    invoke-virtual {p1}, Landroid/view/View;->getMeasuredWidth()I

    move-result v0

    sub-int v0, p2, v0

    .line 179
    sub-int/2addr v0, p4

    .line 181
    const/4 v1, 0x0

    invoke-static {v1, v0}, Ljava/lang/Math;->max(II)I

    move-result v0

    return v0
.end method

.method protected onConfigurationChanged(Landroid/content/res/Configuration;)V
    .locals 5

    .prologue
    const/4 v4, 0x0

    .line 54
    sget v0, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v1, 0x8

    if-lt v0, v1, :cond_0

    .line 55
    invoke-super {p0, p1}, Landroid/view/ViewGroup;->onConfigurationChanged(Landroid/content/res/Configuration;)V

    .line 60
    :cond_0
    invoke-virtual {p0}, Lcom/miui/internal/widget/g;->getContext()Landroid/content/Context;

    move-result-object v0

    const/4 v1, 0x0

    sget-object v2, Lmiui/R$styleable;->ActionBar:[I

    invoke-virtual {p0}, Lcom/miui/internal/widget/g;->bX()I

    move-result v3

    invoke-virtual {v0, v1, v2, v3, v4}, Landroid/content/Context;->obtainStyledAttributes(Landroid/util/AttributeSet;[III)Landroid/content/res/TypedArray;

    move-result-object v0

    .line 61
    const/4 v1, 0x3

    invoke-virtual {v0, v1, v4}, Landroid/content/res/TypedArray;->getLayoutDimension(II)I

    move-result v1

    invoke-virtual {p0, v1}, Lcom/miui/internal/widget/g;->setContentHeight(I)V

    .line 62
    invoke-virtual {v0}, Landroid/content/res/TypedArray;->recycle()V

    .line 63
    iget-boolean v0, p0, Lcom/miui/internal/widget/g;->mSplitWhenNarrow:Z

    if-eqz v0, :cond_1

    .line 64
    invoke-virtual {p0}, Lcom/miui/internal/widget/g;->getContext()Landroid/content/Context;

    move-result-object v0

    invoke-virtual {v0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    sget v1, Lcom/miui/internal/R$bool;->abc_split_action_bar_is_narrow:I

    invoke-virtual {v0, v1}, Landroid/content/res/Resources;->getBoolean(I)Z

    move-result v0

    invoke-virtual {p0, v0}, Lcom/miui/internal/widget/g;->setSplitActionBar(Z)V

    .line 67
    :cond_1
    iget-object v0, p0, Lcom/miui/internal/widget/g;->mActionMenuPresenter:Lcom/miui/internal/view/menu/ActionMenuPresenter;

    if-eqz v0, :cond_2

    .line 68
    iget-object v0, p0, Lcom/miui/internal/widget/g;->mActionMenuPresenter:Lcom/miui/internal/view/menu/ActionMenuPresenter;

    invoke-virtual {v0, p1}, Lcom/miui/internal/view/menu/ActionMenuPresenter;->onConfigurationChanged(Landroid/content/res/Configuration;)V

    .line 70
    :cond_2
    return-void
.end method

.method protected positionChild(Landroid/view/View;III)I
    .locals 4

    .prologue
    .line 185
    invoke-virtual {p1}, Landroid/view/View;->getMeasuredWidth()I

    move-result v0

    .line 186
    invoke-virtual {p1}, Landroid/view/View;->getMeasuredHeight()I

    move-result v1

    .line 187
    sub-int v2, p4, v1

    div-int/lit8 v2, v2, 0x2

    add-int/2addr v2, p3

    .line 189
    add-int v3, p2, v0

    add-int/2addr v1, v2

    invoke-virtual {p1, p2, v2, v3, v1}, Landroid/view/View;->layout(IIII)V

    .line 191
    return v0
.end method

.method protected positionChildInverse(Landroid/view/View;III)I
    .locals 4

    .prologue
    .line 195
    invoke-virtual {p1}, Landroid/view/View;->getMeasuredWidth()I

    move-result v0

    .line 196
    invoke-virtual {p1}, Landroid/view/View;->getMeasuredHeight()I

    move-result v1

    .line 197
    sub-int v2, p4, v1

    div-int/lit8 v2, v2, 0x2

    add-int/2addr v2, p3

    .line 199
    sub-int v3, p2, v0

    add-int/2addr v1, v2

    invoke-virtual {p1, v3, v2, p2, v1}, Landroid/view/View;->layout(IIII)V

    .line 201
    return v0
.end method

.method public postShowOverflowMenu()V
    .locals 1

    .prologue
    .line 148
    new-instance v0, Lcom/miui/internal/widget/g$1;

    invoke-direct {v0, p0}, Lcom/miui/internal/widget/g$1;-><init>(Lcom/miui/internal/widget/g;)V

    invoke-virtual {p0, v0}, Lcom/miui/internal/widget/g;->post(Ljava/lang/Runnable;)Z

    .line 153
    return-void
.end method

.method public setContentHeight(I)V
    .locals 0

    .prologue
    .line 95
    iput p1, p0, Lcom/miui/internal/widget/g;->mContentHeight:I

    .line 96
    invoke-virtual {p0}, Lcom/miui/internal/widget/g;->requestLayout()V

    .line 97
    return-void
.end method

.method public setSplitActionBar(Z)V
    .locals 0

    .prologue
    .line 82
    iput-boolean p1, p0, Lcom/miui/internal/widget/g;->mSplitActionBar:Z

    .line 83
    return-void
.end method

.method public setSplitView(Lcom/miui/internal/widget/ActionBarContainer;)V
    .locals 0

    .prologue
    .line 104
    iput-object p1, p0, Lcom/miui/internal/widget/g;->mSplitView:Lcom/miui/internal/widget/ActionBarContainer;

    .line 105
    return-void
.end method

.method public setSplitWhenNarrow(Z)V
    .locals 0

    .prologue
    .line 91
    iput-boolean p1, p0, Lcom/miui/internal/widget/g;->mSplitWhenNarrow:Z

    .line 92
    return-void
.end method

.method public setVisibility(I)V
    .locals 1

    .prologue
    .line 138
    invoke-virtual {p0}, Lcom/miui/internal/widget/g;->getVisibility()I

    move-result v0

    if-eq p1, v0, :cond_0

    .line 139
    invoke-super {p0, p1}, Landroid/view/ViewGroup;->setVisibility(I)V

    .line 141
    :cond_0
    return-void
.end method

.method public showOverflowMenu()Z
    .locals 1

    .prologue
    .line 144
    iget-object v0, p0, Lcom/miui/internal/widget/g;->mActionMenuPresenter:Lcom/miui/internal/view/menu/ActionMenuPresenter;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/internal/widget/g;->mActionMenuPresenter:Lcom/miui/internal/view/menu/ActionMenuPresenter;

    invoke-virtual {v0}, Lcom/miui/internal/view/menu/ActionMenuPresenter;->showOverflowMenu()Z

    move-result v0

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method
