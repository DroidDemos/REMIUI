.class public Lcom/miui/internal/app/FragmentDelegate;
.super Lcom/miui/internal/app/ActionBarDelegateImpl;
.source "SourceFile"


# static fields
.field public static final MENU_INVALIDATE:I = 0x1

.field private static final xA:I = 0x10


# instance fields
.field private final ij:Landroid/view/Window$Callback;

.field private final kk:Ljava/lang/Runnable;

.field private mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

.field private xB:Landroid/app/Fragment;

.field private xC:Landroid/view/View;

.field private xD:I

.field private xE:Landroid/content/Context;

.field private xF:B


# direct methods
.method public constructor <init>(Landroid/app/Fragment;)V
    .locals 1

    .prologue
    .line 103
    invoke-virtual {p1}, Landroid/app/Fragment;->getActivity()Landroid/app/Activity;

    move-result-object v0

    invoke-direct {p0, v0}, Lcom/miui/internal/app/ActionBarDelegateImpl;-><init>(Landroid/app/Activity;)V

    .line 52
    new-instance v0, Lcom/miui/internal/app/f;

    invoke-direct {v0, p0}, Lcom/miui/internal/app/f;-><init>(Lcom/miui/internal/app/FragmentDelegate;)V

    iput-object v0, p0, Lcom/miui/internal/app/FragmentDelegate;->kk:Ljava/lang/Runnable;

    .line 80
    new-instance v0, Lcom/miui/internal/app/g;

    invoke-direct {v0, p0}, Lcom/miui/internal/app/g;-><init>(Lcom/miui/internal/app/FragmentDelegate;)V

    iput-object v0, p0, Lcom/miui/internal/app/FragmentDelegate;->ij:Landroid/view/Window$Callback;

    .line 104
    iput-object p1, p0, Lcom/miui/internal/app/FragmentDelegate;->xB:Landroid/app/Fragment;

    .line 105
    return-void
.end method

.method static synthetic a(Lcom/miui/internal/app/FragmentDelegate;)B
    .locals 1

    .prologue
    .line 35
    iget-byte v0, p0, Lcom/miui/internal/app/FragmentDelegate;->xF:B

    return v0
.end method

.method static synthetic a(Lcom/miui/internal/app/FragmentDelegate;I)B
    .locals 1

    .prologue
    .line 35
    iget-byte v0, p0, Lcom/miui/internal/app/FragmentDelegate;->xF:B

    and-int/2addr v0, p1

    int-to-byte v0, v0

    iput-byte v0, p0, Lcom/miui/internal/app/FragmentDelegate;->xF:B

    return v0
.end method

.method static synthetic a(Lcom/miui/internal/app/FragmentDelegate;Lcom/miui/internal/view/menu/MenuBuilder;)Lcom/miui/internal/view/menu/MenuBuilder;
    .locals 0

    .prologue
    .line 35
    iput-object p1, p0, Lcom/miui/internal/app/FragmentDelegate;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    return-object p1
.end method

.method static synthetic b(Lcom/miui/internal/app/FragmentDelegate;)Lcom/miui/internal/view/menu/MenuBuilder;
    .locals 1

    .prologue
    .line 35
    iget-object v0, p0, Lcom/miui/internal/app/FragmentDelegate;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    return-object v0
.end method

.method static synthetic c(Lcom/miui/internal/app/FragmentDelegate;)Landroid/app/Fragment;
    .locals 1

    .prologue
    .line 35
    iget-object v0, p0, Lcom/miui/internal/app/FragmentDelegate;->xB:Landroid/app/Fragment;

    return-object v0
.end method


# virtual methods
.method final a(Landroid/content/Context;Landroid/view/ViewGroup;Landroid/view/LayoutInflater;)V
    .locals 6

    .prologue
    const/4 v5, 0x1

    const/4 v3, 0x0

    .line 152
    iget-boolean v0, p0, Lcom/miui/internal/app/FragmentDelegate;->mSubDecorInstalled:Z

    if-nez v0, :cond_7

    .line 153
    iput-boolean v5, p0, Lcom/miui/internal/app/FragmentDelegate;->mSubDecorInstalled:Z

    .line 154
    sget v0, Lcom/miui/internal/R$layout;->screen_action_bar:I

    invoke-virtual {p3, v0, p2, v3}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;Z)Landroid/view/View;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/widget/ActionBarOverlayLayout;

    .line 156
    iget-object v1, p0, Lcom/miui/internal/app/FragmentDelegate;->ij:Landroid/view/Window$Callback;

    invoke-virtual {v0, v1}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->setCallback(Landroid/view/Window$Callback;)V

    .line 157
    iget-boolean v1, p0, Lcom/miui/internal/app/FragmentDelegate;->jb:Z

    invoke-virtual {v0, v1}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->setOverlayMode(Z)V

    .line 158
    invoke-virtual {p0}, Lcom/miui/internal/app/FragmentDelegate;->getTranslucentStatus()I

    move-result v1

    invoke-virtual {v0, v1}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->setTranslucentStatus(I)V

    .line 160
    iget v1, p0, Lcom/miui/internal/app/FragmentDelegate;->xD:I

    if-eqz v1, :cond_0

    .line 161
    const v1, 0x1010054

    invoke-static {p1, v1}, Lmiui/util/AttributeResolver;->resolveDrawable(Landroid/content/Context;I)Landroid/graphics/drawable/Drawable;

    move-result-object v1

    invoke-virtual {v0, v1}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->setBackground(Landroid/graphics/drawable/Drawable;)V

    .line 164
    :cond_0
    sget v1, Lcom/miui/internal/R$id;->action_bar:I

    invoke-virtual {v0, v1}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->findViewById(I)Landroid/view/View;

    move-result-object v1

    check-cast v1, Lcom/miui/internal/widget/ActionBarView;

    iput-object v1, p0, Lcom/miui/internal/app/FragmentDelegate;->mActionBarView:Lcom/miui/internal/widget/ActionBarView;

    .line 165
    iget-object v1, p0, Lcom/miui/internal/app/FragmentDelegate;->mActionBarView:Lcom/miui/internal/widget/ActionBarView;

    iget-object v2, p0, Lcom/miui/internal/app/FragmentDelegate;->ij:Landroid/view/Window$Callback;

    invoke-virtual {v1, v2}, Lcom/miui/internal/widget/ActionBarView;->setWindowCallback(Landroid/view/Window$Callback;)V

    .line 168
    iget-boolean v1, p0, Lcom/miui/internal/app/FragmentDelegate;->mFeatureProgress:Z

    if-eqz v1, :cond_1

    .line 169
    iget-object v1, p0, Lcom/miui/internal/app/FragmentDelegate;->mActionBarView:Lcom/miui/internal/widget/ActionBarView;

    invoke-virtual {v1}, Lcom/miui/internal/widget/ActionBarView;->initProgress()V

    .line 171
    :cond_1
    iget-boolean v1, p0, Lcom/miui/internal/app/FragmentDelegate;->mFeatureIndeterminateProgress:Z

    if-eqz v1, :cond_2

    .line 172
    iget-object v1, p0, Lcom/miui/internal/app/FragmentDelegate;->mActionBarView:Lcom/miui/internal/widget/ActionBarView;

    invoke-virtual {v1}, Lcom/miui/internal/widget/ActionBarView;->initIndeterminateProgress()V

    .line 176
    :cond_2
    invoke-virtual {p0}, Lcom/miui/internal/app/FragmentDelegate;->isImmersionMenuEnabled()Z

    move-result v1

    if-eqz v1, :cond_3

    .line 177
    iget-object v1, p0, Lcom/miui/internal/app/FragmentDelegate;->mActionBarView:Lcom/miui/internal/widget/ActionBarView;

    iget v2, p0, Lcom/miui/internal/app/FragmentDelegate;->mImmersionLayoutResourceId:I

    invoke-virtual {v1, v2, p0}, Lcom/miui/internal/widget/ActionBarView;->initImmersionMore(ILcom/miui/internal/app/ActionBarDelegateImpl;)V

    .line 181
    :cond_3
    const-string v1, "splitActionBarWhenNarrow"

    invoke-virtual {p0}, Lcom/miui/internal/app/FragmentDelegate;->getUiOptionsFromMetadata()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v4

    .line 184
    if-eqz v4, :cond_6

    .line 185
    invoke-virtual {p1}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    sget v2, Lcom/miui/internal/R$bool;->abc_split_action_bar_is_narrow:I

    invoke-virtual {v1, v2}, Landroid/content/res/Resources;->getBoolean(I)Z

    move-result v1

    move v3, v1

    .line 193
    :goto_0
    sget v1, Lcom/miui/internal/R$id;->split_action_bar:I

    invoke-virtual {v0, v1}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->findViewById(I)Landroid/view/View;

    move-result-object v1

    check-cast v1, Lcom/miui/internal/widget/ActionBarContainer;

    .line 195
    if-eqz v1, :cond_4

    .line 196
    iget-object v2, p0, Lcom/miui/internal/app/FragmentDelegate;->mActionBarView:Lcom/miui/internal/widget/ActionBarView;

    invoke-virtual {v2, v1}, Lcom/miui/internal/widget/ActionBarView;->setSplitView(Lcom/miui/internal/widget/ActionBarContainer;)V

    .line 197
    iget-object v2, p0, Lcom/miui/internal/app/FragmentDelegate;->mActionBarView:Lcom/miui/internal/widget/ActionBarView;

    invoke-virtual {v2, v3}, Lcom/miui/internal/widget/ActionBarView;->setSplitActionBar(Z)V

    .line 198
    iget-object v2, p0, Lcom/miui/internal/app/FragmentDelegate;->mActionBarView:Lcom/miui/internal/widget/ActionBarView;

    invoke-virtual {v2, v4}, Lcom/miui/internal/widget/ActionBarView;->setSplitWhenNarrow(Z)V

    .line 200
    sget v2, Lcom/miui/internal/R$id;->action_context_bar:I

    invoke-virtual {v0, v2}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->findViewById(I)Landroid/view/View;

    move-result-object v2

    check-cast v2, Lcom/miui/internal/widget/ActionBarContextView;

    .line 202
    invoke-virtual {v1, v2}, Lcom/miui/internal/widget/ActionBarContainer;->setActionBarContextView(Lcom/miui/internal/widget/ActionBarContextView;)V

    .line 203
    invoke-virtual {v2, v1}, Lcom/miui/internal/widget/ActionBarContextView;->setSplitView(Lcom/miui/internal/widget/ActionBarContainer;)V

    .line 204
    invoke-virtual {v2, v3}, Lcom/miui/internal/widget/ActionBarContextView;->setSplitActionBar(Z)V

    .line 205
    invoke-virtual {v2, v4}, Lcom/miui/internal/widget/ActionBarContextView;->setSplitWhenNarrow(Z)V

    .line 208
    :cond_4
    invoke-virtual {p0, v5}, Lcom/miui/internal/app/FragmentDelegate;->updateOptionsMenu(I)V

    .line 209
    invoke-virtual {p0}, Lcom/miui/internal/app/FragmentDelegate;->invalidateOptionsMenu()V

    .line 210
    iput-object v0, p0, Lcom/miui/internal/app/FragmentDelegate;->xC:Landroid/view/View;

    .line 217
    :cond_5
    :goto_1
    return-void

    .line 188
    :cond_6
    sget-object v1, Lmiui/R$styleable;->Window:[I

    invoke-virtual {p1, v1}, Landroid/content/Context;->obtainStyledAttributes([I)Landroid/content/res/TypedArray;

    move-result-object v2

    .line 189
    invoke-virtual {v2, v3, v3}, Landroid/content/res/TypedArray;->getBoolean(IZ)Z

    move-result v1

    .line 190
    invoke-virtual {v2}, Landroid/content/res/TypedArray;->recycle()V

    move v3, v1

    goto :goto_0

    .line 211
    :cond_7
    iget-object v0, p0, Lcom/miui/internal/app/FragmentDelegate;->xC:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->getParent()Landroid/view/ViewParent;

    move-result-object v0

    if-eqz v0, :cond_5

    iget-object v0, p0, Lcom/miui/internal/app/FragmentDelegate;->xC:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->getParent()Landroid/view/ViewParent;

    move-result-object v0

    instance-of v0, v0, Landroid/view/ViewGroup;

    if-eqz v0, :cond_5

    .line 212
    iget-object v0, p0, Lcom/miui/internal/app/FragmentDelegate;->xC:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->getParent()Landroid/view/ViewParent;

    move-result-object v0

    check-cast v0, Landroid/view/ViewGroup;

    .line 213
    invoke-virtual {v0}, Landroid/view/ViewGroup;->getChildCount()I

    move-result v1

    if-nez v1, :cond_5

    .line 214
    iget-object v1, p0, Lcom/miui/internal/app/FragmentDelegate;->xC:Landroid/view/View;

    invoke-virtual {v0, v1}, Landroid/view/ViewGroup;->endViewTransition(Landroid/view/View;)V

    goto :goto_1
.end method

.method public createActionBar()Lmiui/app/ActionBar;
    .locals 2

    .prologue
    .line 109
    new-instance v0, Lcom/miui/internal/app/ActionBarImpl;

    iget-object v1, p0, Lcom/miui/internal/app/FragmentDelegate;->xB:Landroid/app/Fragment;

    invoke-direct {v0, v1}, Lcom/miui/internal/app/ActionBarImpl;-><init>(Landroid/app/Fragment;)V

    return-object v0
.end method

.method public getThemedContext()Landroid/content/Context;
    .locals 3

    .prologue
    .line 290
    iget-object v0, p0, Lcom/miui/internal/app/FragmentDelegate;->xE:Landroid/content/Context;

    if-nez v0, :cond_0

    .line 291
    iget-object v0, p0, Lcom/miui/internal/app/FragmentDelegate;->dK:Landroid/app/Activity;

    iput-object v0, p0, Lcom/miui/internal/app/FragmentDelegate;->xE:Landroid/content/Context;

    .line 292
    iget v0, p0, Lcom/miui/internal/app/FragmentDelegate;->xD:I

    if-eqz v0, :cond_0

    .line 293
    new-instance v0, Landroid/view/ContextThemeWrapper;

    iget-object v1, p0, Lcom/miui/internal/app/FragmentDelegate;->xE:Landroid/content/Context;

    iget v2, p0, Lcom/miui/internal/app/FragmentDelegate;->xD:I

    invoke-direct {v0, v1, v2}, Landroid/view/ContextThemeWrapper;-><init>(Landroid/content/Context;I)V

    iput-object v0, p0, Lcom/miui/internal/app/FragmentDelegate;->xE:Landroid/content/Context;

    .line 296
    :cond_0
    iget-object v0, p0, Lcom/miui/internal/app/FragmentDelegate;->xE:Landroid/content/Context;

    return-object v0
.end method

.method public getView()Landroid/view/View;
    .locals 1

    .prologue
    .line 220
    iget-object v0, p0, Lcom/miui/internal/app/FragmentDelegate;->xC:Landroid/view/View;

    return-object v0
.end method

.method public invalidateOptionsMenu()V
    .locals 2

    .prologue
    .line 261
    iget-byte v0, p0, Lcom/miui/internal/app/FragmentDelegate;->xF:B

    and-int/lit8 v0, v0, 0x10

    if-nez v0, :cond_0

    .line 262
    iget-byte v0, p0, Lcom/miui/internal/app/FragmentDelegate;->xF:B

    or-int/lit8 v0, v0, 0x10

    int-to-byte v0, v0

    iput-byte v0, p0, Lcom/miui/internal/app/FragmentDelegate;->xF:B

    .line 263
    iget-object v0, p0, Lcom/miui/internal/app/FragmentDelegate;->xB:Landroid/app/Fragment;

    invoke-virtual {v0}, Landroid/app/Fragment;->getActivity()Landroid/app/Activity;

    move-result-object v0

    invoke-virtual {v0}, Landroid/app/Activity;->getWindow()Landroid/view/Window;

    move-result-object v0

    invoke-virtual {v0}, Landroid/view/Window;->getDecorView()Landroid/view/View;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/internal/app/FragmentDelegate;->kk:Ljava/lang/Runnable;

    invoke-virtual {v0, v1}, Landroid/view/View;->post(Ljava/lang/Runnable;)Z

    .line 265
    :cond_0
    return-void
.end method

.method public onCreatePanelMenu(ILandroid/view/Menu;)Z
    .locals 1

    .prologue
    .line 230
    if-nez p1, :cond_0

    .line 231
    iget-object v0, p0, Lcom/miui/internal/app/FragmentDelegate;->xB:Landroid/app/Fragment;

    check-cast v0, Lcom/miui/internal/app/IFragment;

    invoke-interface {v0, p1, p2}, Lcom/miui/internal/app/IFragment;->onCreatePanelMenu(ILandroid/view/Menu;)Z

    move-result v0

    .line 233
    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public onCreatePanelView(I)Landroid/view/View;
    .locals 1

    .prologue
    .line 225
    const/4 v0, 0x0

    return-object v0
.end method

.method public onCreateView(Landroid/view/ViewGroup;Landroid/os/Bundle;)Landroid/view/View;
    .locals 4

    .prologue
    const/4 v3, 0x1

    const/4 v2, 0x0

    .line 113
    invoke-virtual {p0}, Lcom/miui/internal/app/FragmentDelegate;->getThemedContext()Landroid/content/Context;

    move-result-object v0

    sget-object v1, Lmiui/R$styleable;->Window:[I

    invoke-virtual {v0, v1}, Landroid/content/Context;->obtainStyledAttributes([I)Landroid/content/res/TypedArray;

    move-result-object v0

    .line 114
    invoke-virtual {v0, v3}, Landroid/content/res/TypedArray;->hasValue(I)Z

    move-result v1

    if-nez v1, :cond_0

    .line 115
    invoke-virtual {v0}, Landroid/content/res/TypedArray;->recycle()V

    .line 116
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "You need to use a miui theme (or descendant) with this fragment."

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 118
    :cond_0
    invoke-virtual {v0, v3, v2}, Landroid/content/res/TypedArray;->getBoolean(IZ)Z

    move-result v1

    if-eqz v1, :cond_1

    .line 119
    const/16 v1, 0x8

    invoke-virtual {p0, v1}, Lcom/miui/internal/app/FragmentDelegate;->requestWindowFeature(I)Z

    .line 121
    :cond_1
    const/4 v1, 0x2

    invoke-virtual {v0, v1, v2}, Landroid/content/res/TypedArray;->getBoolean(IZ)Z

    move-result v1

    if-eqz v1, :cond_2

    .line 122
    const/16 v1, 0x9

    invoke-virtual {p0, v1}, Lcom/miui/internal/app/FragmentDelegate;->requestWindowFeature(I)Z

    .line 124
    :cond_2
    const/16 v1, 0xa

    invoke-virtual {v0, v1, v2}, Landroid/content/res/TypedArray;->getInt(II)I

    move-result v1

    invoke-virtual {p0, v1}, Lcom/miui/internal/app/FragmentDelegate;->setTranslucentStatus(I)V

    .line 126
    const/4 v1, 0x5

    invoke-virtual {v0, v1, v2}, Landroid/content/res/TypedArray;->getBoolean(IZ)Z

    move-result v1

    invoke-virtual {p0, v1}, Lcom/miui/internal/app/FragmentDelegate;->setImmersionMenuEnabled(Z)V

    .line 127
    const/16 v1, 0x12

    invoke-virtual {v0, v1, v2}, Landroid/content/res/TypedArray;->getResourceId(II)I

    move-result v1

    iput v1, p0, Lcom/miui/internal/app/FragmentDelegate;->mImmersionLayoutResourceId:I

    .line 128
    invoke-virtual {v0}, Landroid/content/res/TypedArray;->recycle()V

    .line 130
    invoke-virtual {p0}, Lcom/miui/internal/app/FragmentDelegate;->getThemedContext()Landroid/content/Context;

    move-result-object v0

    invoke-static {v0}, Landroid/view/LayoutInflater;->from(Landroid/content/Context;)Landroid/view/LayoutInflater;

    move-result-object v2

    .line 131
    iget-boolean v0, p0, Lcom/miui/internal/app/FragmentDelegate;->ja:Z

    if-eqz v0, :cond_5

    .line 132
    invoke-virtual {p0}, Lcom/miui/internal/app/FragmentDelegate;->getThemedContext()Landroid/content/Context;

    move-result-object v0

    invoke-virtual {p0, v0, p1, v2}, Lcom/miui/internal/app/FragmentDelegate;->a(Landroid/content/Context;Landroid/view/ViewGroup;Landroid/view/LayoutInflater;)V

    .line 135
    iget-object v0, p0, Lcom/miui/internal/app/FragmentDelegate;->xC:Landroid/view/View;

    const v1, 0x1020002

    invoke-virtual {v0, v1}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/view/ViewGroup;

    .line 136
    iget-object v1, p0, Lcom/miui/internal/app/FragmentDelegate;->xB:Landroid/app/Fragment;

    check-cast v1, Lcom/miui/internal/app/IFragment;

    invoke-interface {v1, v2, v0, p2}, Lcom/miui/internal/app/IFragment;->onInflateView(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Landroid/os/Bundle;)Landroid/view/View;

    move-result-object v2

    .line 137
    if-eqz v2, :cond_4

    invoke-virtual {v2}, Landroid/view/View;->getParent()Landroid/view/ViewParent;

    move-result-object v1

    if-eq v1, v0, :cond_4

    .line 138
    invoke-virtual {v2}, Landroid/view/View;->getParent()Landroid/view/ViewParent;

    move-result-object v1

    if-eqz v1, :cond_3

    .line 139
    invoke-virtual {v2}, Landroid/view/View;->getParent()Landroid/view/ViewParent;

    move-result-object v1

    check-cast v1, Landroid/view/ViewGroup;

    invoke-virtual {v1, v2}, Landroid/view/ViewGroup;->removeView(Landroid/view/View;)V

    .line 141
    :cond_3
    invoke-virtual {v0}, Landroid/view/ViewGroup;->removeAllViews()V

    .line 142
    invoke-virtual {v0, v2}, Landroid/view/ViewGroup;->addView(Landroid/view/View;)V

    .line 148
    :cond_4
    :goto_0
    iget-object v0, p0, Lcom/miui/internal/app/FragmentDelegate;->xC:Landroid/view/View;

    return-object v0

    .line 145
    :cond_5
    iget-object v0, p0, Lcom/miui/internal/app/FragmentDelegate;->xB:Landroid/app/Fragment;

    check-cast v0, Lcom/miui/internal/app/IFragment;

    invoke-interface {v0, v2, p1, p2}, Lcom/miui/internal/app/IFragment;->onInflateView(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Landroid/os/Bundle;)Landroid/view/View;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/internal/app/FragmentDelegate;->xC:Landroid/view/View;

    goto :goto_0
.end method

.method public onMenuItemSelected(ILandroid/view/MenuItem;)Z
    .locals 1

    .prologue
    .line 239
    if-nez p1, :cond_0

    .line 240
    iget-object v0, p0, Lcom/miui/internal/app/FragmentDelegate;->xB:Landroid/app/Fragment;

    invoke-virtual {v0, p2}, Landroid/app/Fragment;->onOptionsItemSelected(Landroid/view/MenuItem;)Z

    move-result v0

    .line 242
    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public onMenuItemSelected(Lcom/miui/internal/view/menu/MenuBuilder;Landroid/view/MenuItem;)Z
    .locals 1

    .prologue
    .line 256
    const/4 v0, 0x0

    invoke-virtual {p0, v0, p2}, Lcom/miui/internal/app/FragmentDelegate;->onMenuItemSelected(ILandroid/view/MenuItem;)Z

    move-result v0

    return v0
.end method

.method protected onPrepareImmersionMenu(Lcom/miui/internal/view/menu/MenuBuilder;)Z
    .locals 1

    .prologue
    .line 301
    iget-object v0, p0, Lcom/miui/internal/app/FragmentDelegate;->xB:Landroid/app/Fragment;

    instance-of v0, v0, Lcom/miui/internal/app/IFragment;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/internal/app/FragmentDelegate;->xB:Landroid/app/Fragment;

    check-cast v0, Lcom/miui/internal/app/IFragment;

    invoke-interface {v0, p1}, Lcom/miui/internal/app/IFragment;->onCreateOptionsMenu(Landroid/view/Menu;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 302
    iget-object v0, p0, Lcom/miui/internal/app/FragmentDelegate;->xB:Landroid/app/Fragment;

    invoke-virtual {v0, p1}, Landroid/app/Fragment;->onPrepareOptionsMenu(Landroid/view/Menu;)V

    .line 303
    const/4 v0, 0x1

    .line 306
    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public onPreparePanel(ILandroid/view/View;Landroid/view/Menu;)Z
    .locals 2

    .prologue
    .line 247
    if-nez p1, :cond_0

    .line 248
    iget-object v0, p0, Lcom/miui/internal/app/FragmentDelegate;->xB:Landroid/app/Fragment;

    check-cast v0, Lcom/miui/internal/app/IFragment;

    const/4 v1, 0x0

    invoke-interface {v0, p1, v1, p3}, Lcom/miui/internal/app/IFragment;->onPreparePanel(ILandroid/view/View;Landroid/view/Menu;)V

    .line 249
    const/4 v0, 0x1

    .line 251
    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public onWindowStartingActionMode(Landroid/view/ActionMode$Callback;)Landroid/view/ActionMode;
    .locals 1

    .prologue
    .line 279
    invoke-virtual {p0}, Lcom/miui/internal/app/FragmentDelegate;->getActionBar()Lmiui/app/ActionBar;

    move-result-object v0

    if-eqz v0, :cond_0

    .line 280
    invoke-virtual {p0}, Lcom/miui/internal/app/FragmentDelegate;->getActionBar()Lmiui/app/ActionBar;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/app/ActionBarImpl;

    invoke-virtual {v0, p1}, Lcom/miui/internal/app/ActionBarImpl;->startActionMode(Landroid/view/ActionMode$Callback;)Landroid/view/ActionMode;

    move-result-object v0

    .line 282
    :goto_0
    return-object v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public setExtraThemeRes(I)V
    .locals 0

    .prologue
    .line 286
    iput p1, p0, Lcom/miui/internal/app/FragmentDelegate;->xD:I

    .line 287
    return-void
.end method

.method public setOnStatusBarChangeListener(Lmiui/app/OnStatusBarChangeListener;)V
    .locals 1

    .prologue
    .line 310
    iget-object v0, p0, Lcom/miui/internal/app/FragmentDelegate;->xC:Landroid/view/View;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/internal/app/FragmentDelegate;->xC:Landroid/view/View;

    instance-of v0, v0, Lcom/miui/internal/widget/ActionBarOverlayLayout;

    if-eqz v0, :cond_0

    .line 311
    iget-object v0, p0, Lcom/miui/internal/app/FragmentDelegate;->xC:Landroid/view/View;

    check-cast v0, Lcom/miui/internal/widget/ActionBarOverlayLayout;

    invoke-virtual {v0, p1}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->setOnStatusBarChangeListener(Lmiui/app/OnStatusBarChangeListener;)V

    .line 313
    :cond_0
    return-void
.end method

.method public startActionMode(Landroid/view/ActionMode$Callback;)Landroid/view/ActionMode;
    .locals 1

    .prologue
    .line 274
    iget-object v0, p0, Lcom/miui/internal/app/FragmentDelegate;->xC:Landroid/view/View;

    invoke-virtual {v0, p1}, Landroid/view/View;->startActionMode(Landroid/view/ActionMode$Callback;)Landroid/view/ActionMode;

    move-result-object v0

    return-object v0
.end method

.method public updateOptionsMenu(I)V
    .locals 2

    .prologue
    .line 268
    iget-byte v0, p0, Lcom/miui/internal/app/FragmentDelegate;->xF:B

    and-int/lit8 v1, p1, 0x1

    or-int/2addr v0, v1

    int-to-byte v0, v0

    iput-byte v0, p0, Lcom/miui/internal/app/FragmentDelegate;->xF:B

    .line 269
    return-void
.end method
