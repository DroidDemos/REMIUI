.class public Lcom/miui/internal/app/ActionBarImpl;
.super Lmiui/app/ActionBar;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/internal/app/ActionBarImpl$TabImpl;
    }
.end annotation


# static fields
.field private static final INVALID_POSITION:I = -0x1

.field private static final MJ:I = 0x0

.field private static final Na:I = 0x1

.field private static Nb:Landroid/app/ActionBar$TabListener;


# instance fields
.field private Ms:Lcom/miui/internal/view/ActionModeImpl$ActionModeCallback;

.field private Nc:Lcom/miui/internal/widget/ActionBarOverlayLayout;

.field private Nd:Lcom/miui/internal/widget/ActionBarContainer;

.field private Ne:Lcom/miui/internal/widget/ActionBarView;

.field private Nf:Lcom/miui/internal/widget/ActionBarContextView;

.field private Ng:Lcom/miui/internal/view/menu/PhoneActionMenuView;

.field private Nh:Landroid/view/View$OnClickListener;

.field private Ni:Lcom/miui/internal/app/ActionBarViewPagerController;

.field private Nj:Lcom/miui/internal/widget/ActionModeView;

.field private Nk:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList",
            "<",
            "Lcom/miui/internal/app/ActionBarImpl$TabImpl;",
            ">;"
        }
    .end annotation
.end field

.field private Nl:Lcom/miui/internal/app/ActionBarImpl$TabImpl;

.field private Nm:I

.field private Nn:Z

.field private No:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList",
            "<",
            "Landroid/app/ActionBar$OnMenuVisibilityListener;",
            ">;"
        }
    .end annotation
.end field

.field private Np:I

.field private Nq:Z

.field private Nr:I

.field private Ns:Z

.field private Nt:Z

.field private Nu:Z

.field private Nv:Z

.field private Nw:Z

.field private Nx:Lcom/miui/internal/widget/SearchActionModeView;

.field private hO:Lcom/miui/internal/widget/ScrollingTabContainerView;

.field private kO:Landroid/app/FragmentManager;

.field mActionMode:Landroid/view/ActionMode;

.field private mContext:Landroid/content/Context;

.field private mSplitView:Lcom/miui/internal/widget/ActionBarContainer;

.field private rj:Landroid/view/View;

.field private xE:Landroid/content/Context;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .prologue
    .line 61
    new-instance v0, Lcom/miui/internal/app/l;

    invoke-direct {v0}, Lcom/miui/internal/app/l;-><init>()V

    sput-object v0, Lcom/miui/internal/app/ActionBarImpl;->Nb:Landroid/app/ActionBar$TabListener;

    return-void
.end method

.method public constructor <init>(Landroid/app/Activity;)V
    .locals 2

    .prologue
    .line 171
    invoke-direct {p0}, Lmiui/app/ActionBar;-><init>()V

    .line 131
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nk:Ljava/util/ArrayList;

    .line 137
    const/4 v0, -0x1

    iput v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nm:I

    .line 141
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->No:Ljava/util/ArrayList;

    .line 148
    const/4 v0, 0x0

    iput v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nr:I

    .line 156
    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nv:Z

    .line 162
    new-instance v0, Lcom/miui/internal/app/m;

    invoke-direct {v0, p0}, Lcom/miui/internal/app/m;-><init>(Lcom/miui/internal/app/ActionBarImpl;)V

    iput-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Ms:Lcom/miui/internal/view/ActionModeImpl$ActionModeCallback;

    .line 172
    iput-object p1, p0, Lcom/miui/internal/app/ActionBarImpl;->mContext:Landroid/content/Context;

    .line 173
    invoke-virtual {p1}, Landroid/app/Activity;->getFragmentManager()Landroid/app/FragmentManager;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->kO:Landroid/app/FragmentManager;

    .line 174
    invoke-virtual {p1}, Landroid/app/Activity;->getWindow()Landroid/view/Window;

    move-result-object v0

    invoke-virtual {v0}, Landroid/view/Window;->getDecorView()Landroid/view/View;

    move-result-object v0

    sget v1, Lcom/miui/internal/R$id;->action_bar_overlay_layout:I

    invoke-virtual {v0, v1}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/view/ViewGroup;

    invoke-virtual {p0, v0}, Lcom/miui/internal/app/ActionBarImpl;->init(Landroid/view/ViewGroup;)V

    .line 175
    invoke-virtual {p1}, Landroid/app/Activity;->getTitle()Ljava/lang/CharSequence;

    move-result-object v0

    invoke-virtual {p0, v0}, Lcom/miui/internal/app/ActionBarImpl;->setTitle(Ljava/lang/CharSequence;)V

    .line 176
    return-void
.end method

.method public constructor <init>(Landroid/app/Dialog;)V
    .locals 2

    .prologue
    .line 186
    invoke-direct {p0}, Lmiui/app/ActionBar;-><init>()V

    .line 131
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nk:Ljava/util/ArrayList;

    .line 137
    const/4 v0, -0x1

    iput v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nm:I

    .line 141
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->No:Ljava/util/ArrayList;

    .line 148
    const/4 v0, 0x0

    iput v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nr:I

    .line 156
    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nv:Z

    .line 162
    new-instance v0, Lcom/miui/internal/app/m;

    invoke-direct {v0, p0}, Lcom/miui/internal/app/m;-><init>(Lcom/miui/internal/app/ActionBarImpl;)V

    iput-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Ms:Lcom/miui/internal/view/ActionModeImpl$ActionModeCallback;

    .line 187
    invoke-virtual {p1}, Landroid/app/Dialog;->getContext()Landroid/content/Context;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->mContext:Landroid/content/Context;

    .line 188
    invoke-virtual {p1}, Landroid/app/Dialog;->getWindow()Landroid/view/Window;

    move-result-object v0

    invoke-virtual {v0}, Landroid/view/Window;->getDecorView()Landroid/view/View;

    move-result-object v0

    sget v1, Lcom/miui/internal/R$id;->action_bar_overlay_layout:I

    invoke-virtual {v0, v1}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/view/ViewGroup;

    invoke-virtual {p0, v0}, Lcom/miui/internal/app/ActionBarImpl;->init(Landroid/view/ViewGroup;)V

    .line 189
    return-void
.end method

.method public constructor <init>(Landroid/app/Fragment;)V
    .locals 1

    .prologue
    .line 178
    invoke-direct {p0}, Lmiui/app/ActionBar;-><init>()V

    .line 131
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nk:Ljava/util/ArrayList;

    .line 137
    const/4 v0, -0x1

    iput v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nm:I

    .line 141
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->No:Ljava/util/ArrayList;

    .line 148
    const/4 v0, 0x0

    iput v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nr:I

    .line 156
    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nv:Z

    .line 162
    new-instance v0, Lcom/miui/internal/app/m;

    invoke-direct {v0, p0}, Lcom/miui/internal/app/m;-><init>(Lcom/miui/internal/app/ActionBarImpl;)V

    iput-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Ms:Lcom/miui/internal/view/ActionModeImpl$ActionModeCallback;

    move-object v0, p1

    .line 179
    check-cast v0, Lcom/miui/internal/app/IFragment;

    invoke-interface {v0}, Lcom/miui/internal/app/IFragment;->getThemedContext()Landroid/content/Context;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->mContext:Landroid/content/Context;

    .line 180
    invoke-virtual {p1}, Landroid/app/Fragment;->getFragmentManager()Landroid/app/FragmentManager;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->kO:Landroid/app/FragmentManager;

    .line 181
    invoke-virtual {p1}, Landroid/app/Fragment;->getView()Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/view/ViewGroup;

    invoke-virtual {p0, v0}, Lcom/miui/internal/app/ActionBarImpl;->init(Landroid/view/ViewGroup;)V

    .line 182
    invoke-virtual {p1}, Landroid/app/Fragment;->getActivity()Landroid/app/Activity;

    move-result-object v0

    .line 183
    if-eqz v0, :cond_0

    invoke-virtual {v0}, Landroid/app/Activity;->getTitle()Ljava/lang/CharSequence;

    move-result-object v0

    :goto_0
    invoke-virtual {p0, v0}, Lcom/miui/internal/app/ActionBarImpl;->setTitle(Ljava/lang/CharSequence;)V

    .line 184
    return-void

    .line 183
    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method static synthetic a(Lcom/miui/internal/app/ActionBarImpl;)Lcom/miui/internal/view/menu/PhoneActionMenuView;
    .locals 1

    .prologue
    .line 53
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Ng:Lcom/miui/internal/view/menu/PhoneActionMenuView;

    return-object v0
.end method

.method private static a(ZZZ)Z
    .locals 1

    .prologue
    const/4 v0, 0x1

    .line 213
    if-eqz p2, :cond_1

    .line 218
    :cond_0
    :goto_0
    return v0

    .line 215
    :cond_1
    if-nez p0, :cond_2

    if-eqz p1, :cond_0

    .line 216
    :cond_2
    const/4 v0, 0x0

    goto :goto_0
.end method

.method private b(Landroid/view/ActionMode$Callback;)Landroid/view/ActionMode;
    .locals 2

    .prologue
    .line 814
    instance-of v0, p1, Lmiui/view/SearchActionMode$Callback;

    if-eqz v0, :cond_0

    .line 815
    new-instance v0, Lcom/miui/internal/view/SearchActionModeImpl;

    iget-object v1, p0, Lcom/miui/internal/app/ActionBarImpl;->mContext:Landroid/content/Context;

    invoke-direct {v0, v1, p1}, Lcom/miui/internal/view/SearchActionModeImpl;-><init>(Landroid/content/Context;Landroid/view/ActionMode$Callback;)V

    .line 819
    :goto_0
    return-object v0

    .line 817
    :cond_0
    new-instance v0, Lcom/miui/internal/view/EditActionModeImpl;

    iget-object v1, p0, Lcom/miui/internal/app/ActionBarImpl;->mContext:Landroid/content/Context;

    invoke-direct {v0, v1, p1}, Lcom/miui/internal/view/EditActionModeImpl;-><init>(Landroid/content/Context;Landroid/view/ActionMode$Callback;)V

    goto :goto_0
.end method

.method static synthetic b(Lcom/miui/internal/app/ActionBarImpl;)Lcom/miui/internal/widget/ScrollingTabContainerView;
    .locals 1

    .prologue
    .line 53
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->hO:Lcom/miui/internal/widget/ScrollingTabContainerView;

    return-object v0
.end method

.method private b(Landroid/app/ActionBar$Tab;I)V
    .locals 3

    .prologue
    .line 1022
    check-cast p1, Lcom/miui/internal/app/ActionBarImpl$TabImpl;

    .line 1023
    invoke-virtual {p1}, Lcom/miui/internal/app/ActionBarImpl$TabImpl;->getCallback()Landroid/app/ActionBar$TabListener;

    move-result-object v0

    .line 1025
    if-nez v0, :cond_0

    .line 1026
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "Action Bar Tab must have a Callback"

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 1030
    :cond_0
    invoke-virtual {p1, p2}, Lcom/miui/internal/app/ActionBarImpl$TabImpl;->setPosition(I)V

    .line 1031
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nk:Ljava/util/ArrayList;

    invoke-virtual {v0, p2, p1}, Ljava/util/ArrayList;->add(ILjava/lang/Object;)V

    .line 1033
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nk:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v2

    .line 1034
    add-int/lit8 v0, p2, 0x1

    move v1, v0

    :goto_0
    if-ge v1, v2, :cond_1

    .line 1035
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nk:Ljava/util/ArrayList;

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/app/ActionBarImpl$TabImpl;

    invoke-virtual {v0, v1}, Lcom/miui/internal/app/ActionBarImpl$TabImpl;->setPosition(I)V

    .line 1034
    add-int/lit8 v0, v1, 0x1

    move v1, v0

    goto :goto_0

    .line 1037
    :cond_1
    return-void
.end method

.method static synthetic c(Lcom/miui/internal/app/ActionBarImpl;)Landroid/content/Context;
    .locals 1

    .prologue
    .line 53
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->mContext:Landroid/content/Context;

    return-object v0
.end method

.method private eo()V
    .locals 4

    .prologue
    const/4 v3, 0x0

    .line 1000
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->hO:Lcom/miui/internal/widget/ScrollingTabContainerView;

    if-eqz v0, :cond_0

    .line 1019
    :goto_0
    return-void

    .line 1004
    :cond_0
    new-instance v0, Lcom/miui/internal/widget/ScrollingTabContainerView;

    iget-object v1, p0, Lcom/miui/internal/app/ActionBarImpl;->mContext:Landroid/content/Context;

    invoke-direct {v0, v1}, Lcom/miui/internal/widget/ScrollingTabContainerView;-><init>(Landroid/content/Context;)V

    .line 1007
    iget-boolean v1, p0, Lcom/miui/internal/app/ActionBarImpl;->Nq:Z

    if-eqz v1, :cond_1

    .line 1008
    invoke-virtual {v0, v3}, Lcom/miui/internal/widget/ScrollingTabContainerView;->setVisibility(I)V

    .line 1009
    iget-object v1, p0, Lcom/miui/internal/app/ActionBarImpl;->Ne:Lcom/miui/internal/widget/ActionBarView;

    invoke-virtual {v1, v0}, Lcom/miui/internal/widget/ActionBarView;->setEmbeddedTabView(Lcom/miui/internal/widget/ScrollingTabContainerView;)V

    .line 1018
    :goto_1
    iput-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->hO:Lcom/miui/internal/widget/ScrollingTabContainerView;

    goto :goto_0

    .line 1011
    :cond_1
    invoke-virtual {p0}, Lcom/miui/internal/app/ActionBarImpl;->getNavigationMode()I

    move-result v1

    const/4 v2, 0x2

    if-ne v1, v2, :cond_2

    .line 1012
    invoke-virtual {v0, v3}, Lcom/miui/internal/widget/ScrollingTabContainerView;->setVisibility(I)V

    .line 1016
    :goto_2
    iget-object v1, p0, Lcom/miui/internal/app/ActionBarImpl;->Nd:Lcom/miui/internal/widget/ActionBarContainer;

    invoke-virtual {v1, v0}, Lcom/miui/internal/widget/ActionBarContainer;->setTabContainer(Lcom/miui/internal/widget/ScrollingTabContainerView;)V

    goto :goto_1

    .line 1014
    :cond_2
    const/16 v1, 0x8

    invoke-virtual {v0, v1}, Lcom/miui/internal/widget/ScrollingTabContainerView;->setVisibility(I)V

    goto :goto_2
.end method

.method private ep()V
    .locals 1

    .prologue
    .line 1040
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nl:Lcom/miui/internal/app/ActionBarImpl$TabImpl;

    if-eqz v0, :cond_0

    .line 1041
    const/4 v0, 0x0

    invoke-virtual {p0, v0}, Lcom/miui/internal/app/ActionBarImpl;->selectTab(Landroid/app/ActionBar$Tab;)V

    .line 1043
    :cond_0
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nk:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->clear()V

    .line 1044
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->hO:Lcom/miui/internal/widget/ScrollingTabContainerView;

    if-eqz v0, :cond_1

    .line 1045
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->hO:Lcom/miui/internal/widget/ScrollingTabContainerView;

    invoke-virtual {v0}, Lcom/miui/internal/widget/ScrollingTabContainerView;->removeAllTabs()V

    .line 1047
    :cond_1
    const/4 v0, -0x1

    iput v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nm:I

    .line 1048
    return-void
.end method

.method static synthetic er()Landroid/app/ActionBar$TabListener;
    .locals 1

    .prologue
    .line 53
    sget-object v0, Lcom/miui/internal/app/ActionBarImpl;->Nb:Landroid/app/ActionBar$TabListener;

    return-object v0
.end method

.method public static getActionBar(Landroid/view/View;)Lcom/miui/internal/app/ActionBarImpl;
    .locals 3

    .prologue
    const/4 v1, 0x0

    .line 192
    move-object v0, p0

    .line 193
    :goto_0
    if-eqz v0, :cond_2

    .line 194
    instance-of v2, v0, Lcom/miui/internal/widget/ActionBarOverlayLayout;

    if-eqz v2, :cond_0

    .line 195
    check-cast v0, Lcom/miui/internal/widget/ActionBarOverlayLayout;

    invoke-virtual {v0}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->getActionBar()Lmiui/app/ActionBar;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/app/ActionBarImpl;

    .line 207
    :goto_1
    return-object v0

    .line 200
    :cond_0
    invoke-virtual {v0}, Landroid/view/View;->getParent()Landroid/view/ViewParent;

    move-result-object v2

    instance-of v2, v2, Landroid/view/View;

    if-eqz v2, :cond_1

    .line 201
    invoke-virtual {v0}, Landroid/view/View;->getParent()Landroid/view/ViewParent;

    move-result-object v0

    check-cast v0, Landroid/view/View;

    goto :goto_0

    :cond_1
    move-object v0, v1

    .line 203
    goto :goto_0

    :cond_2
    move-object v0, v1

    goto :goto_1
.end method

.method private q(Z)V
    .locals 5

    .prologue
    const/4 v3, 0x0

    const/4 v1, 0x1

    const/4 v2, 0x0

    .line 272
    iput-boolean p1, p0, Lcom/miui/internal/app/ActionBarImpl;->Nq:Z

    .line 274
    iget-boolean v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nq:Z

    if-nez v0, :cond_1

    .line 275
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Ne:Lcom/miui/internal/widget/ActionBarView;

    invoke-virtual {v0, v3}, Lcom/miui/internal/widget/ActionBarView;->setEmbeddedTabView(Lcom/miui/internal/widget/ScrollingTabContainerView;)V

    .line 276
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nd:Lcom/miui/internal/widget/ActionBarContainer;

    iget-object v3, p0, Lcom/miui/internal/app/ActionBarImpl;->hO:Lcom/miui/internal/widget/ScrollingTabContainerView;

    invoke-virtual {v0, v3}, Lcom/miui/internal/widget/ActionBarContainer;->setTabContainer(Lcom/miui/internal/widget/ScrollingTabContainerView;)V

    .line 281
    :goto_0
    invoke-virtual {p0}, Lcom/miui/internal/app/ActionBarImpl;->getNavigationMode()I

    move-result v0

    const/4 v3, 0x2

    if-ne v0, v3, :cond_2

    move v0, v1

    .line 282
    :goto_1
    iget-object v3, p0, Lcom/miui/internal/app/ActionBarImpl;->hO:Lcom/miui/internal/widget/ScrollingTabContainerView;

    if-eqz v3, :cond_0

    .line 283
    if-eqz v0, :cond_3

    .line 284
    iget-object v3, p0, Lcom/miui/internal/app/ActionBarImpl;->hO:Lcom/miui/internal/widget/ScrollingTabContainerView;

    invoke-virtual {v3, v2}, Lcom/miui/internal/widget/ScrollingTabContainerView;->setVisibility(I)V

    .line 289
    :cond_0
    :goto_2
    iget-object v3, p0, Lcom/miui/internal/app/ActionBarImpl;->Ne:Lcom/miui/internal/widget/ActionBarView;

    iget-boolean v4, p0, Lcom/miui/internal/app/ActionBarImpl;->Nq:Z

    if-nez v4, :cond_4

    if-eqz v0, :cond_4

    :goto_3
    invoke-virtual {v3, v1}, Lcom/miui/internal/widget/ActionBarView;->setCollapsable(Z)V

    .line 290
    return-void

    .line 278
    :cond_1
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nd:Lcom/miui/internal/widget/ActionBarContainer;

    invoke-virtual {v0, v3}, Lcom/miui/internal/widget/ActionBarContainer;->setTabContainer(Lcom/miui/internal/widget/ScrollingTabContainerView;)V

    .line 279
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Ne:Lcom/miui/internal/widget/ActionBarView;

    iget-object v3, p0, Lcom/miui/internal/app/ActionBarImpl;->hO:Lcom/miui/internal/widget/ScrollingTabContainerView;

    invoke-virtual {v0, v3}, Lcom/miui/internal/widget/ActionBarView;->setEmbeddedTabView(Lcom/miui/internal/widget/ScrollingTabContainerView;)V

    goto :goto_0

    :cond_2
    move v0, v2

    .line 281
    goto :goto_1

    .line 286
    :cond_3
    iget-object v3, p0, Lcom/miui/internal/app/ActionBarImpl;->hO:Lcom/miui/internal/widget/ScrollingTabContainerView;

    const/16 v4, 0x8

    invoke-virtual {v3, v4}, Lcom/miui/internal/widget/ScrollingTabContainerView;->setVisibility(I)V

    goto :goto_2

    :cond_4
    move v1, v2

    .line 289
    goto :goto_3
.end method

.method private s(Z)V
    .locals 3

    .prologue
    .line 1052
    iget-boolean v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Ns:Z

    iget-boolean v1, p0, Lcom/miui/internal/app/ActionBarImpl;->Nt:Z

    iget-boolean v2, p0, Lcom/miui/internal/app/ActionBarImpl;->Nu:Z

    invoke-static {v0, v1, v2}, Lcom/miui/internal/app/ActionBarImpl;->a(ZZZ)Z

    move-result v0

    .line 1055
    if-eqz v0, :cond_1

    .line 1056
    iget-boolean v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nv:Z

    if-nez v0, :cond_0

    .line 1057
    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nv:Z

    .line 1058
    invoke-virtual {p0, p1}, Lcom/miui/internal/app/ActionBarImpl;->doShow(Z)V

    .line 1066
    :cond_0
    :goto_0
    return-void

    .line 1061
    :cond_1
    iget-boolean v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nv:Z

    if-eqz v0, :cond_0

    .line 1062
    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nv:Z

    .line 1063
    invoke-virtual {p0, p1}, Lcom/miui/internal/app/ActionBarImpl;->doHide(Z)V

    goto :goto_0
.end method

.method private t(Z)V
    .locals 3

    .prologue
    const/4 v2, 0x0

    .line 1135
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->mSplitView:Lcom/miui/internal/widget/ActionBarContainer;

    invoke-virtual {v0}, Lcom/miui/internal/widget/ActionBarContainer;->getChildCount()I

    move-result v0

    const/4 v1, 0x1

    if-ne v0, v1, :cond_0

    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->mSplitView:Lcom/miui/internal/widget/ActionBarContainer;

    invoke-virtual {v0, v2}, Lcom/miui/internal/widget/ActionBarContainer;->getChildAt(I)Landroid/view/View;

    move-result-object v0

    instance-of v0, v0, Lcom/miui/internal/view/menu/PhoneActionMenuView;

    if-eqz v0, :cond_0

    .line 1137
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->mSplitView:Lcom/miui/internal/widget/ActionBarContainer;

    invoke-virtual {v0, v2}, Lcom/miui/internal/widget/ActionBarContainer;->getChildAt(I)Landroid/view/View;

    move-result-object v0

    .line 1138
    check-cast v0, Lcom/miui/internal/view/menu/PhoneActionMenuView;

    iput-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Ng:Lcom/miui/internal/view/menu/PhoneActionMenuView;

    .line 1140
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Ng:Lcom/miui/internal/view/menu/PhoneActionMenuView;

    invoke-virtual {v0}, Lcom/miui/internal/view/menu/PhoneActionMenuView;->isOverflowMenuShowing()Z

    move-result v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->rj:Landroid/view/View;

    if-eqz v0, :cond_0

    .line 1141
    if-eqz p1, :cond_1

    .line 1142
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nc:Lcom/miui/internal/widget/ActionBarOverlayLayout;

    iget-object v1, p0, Lcom/miui/internal/app/ActionBarImpl;->Nh:Landroid/view/View$OnClickListener;

    invoke-virtual {v0, v1}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->getContentMaskAnimator(Landroid/view/View$OnClickListener;)Lcom/miui/internal/widget/ActionBarOverlayLayout$ContentMaskAnimator;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/internal/widget/ActionBarOverlayLayout$ContentMaskAnimator;->show()Landroid/animation/Animator;

    move-result-object v0

    invoke-virtual {v0}, Landroid/animation/Animator;->start()V

    .line 1149
    :cond_0
    :goto_0
    return-void

    .line 1145
    :cond_1
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nc:Lcom/miui/internal/widget/ActionBarOverlayLayout;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->getContentMaskAnimator(Landroid/view/View$OnClickListener;)Lcom/miui/internal/widget/ActionBarOverlayLayout$ContentMaskAnimator;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/internal/widget/ActionBarOverlayLayout$ContentMaskAnimator;->hide()Landroid/animation/Animator;

    move-result-object v0

    invoke-virtual {v0}, Landroid/animation/Animator;->start()V

    goto :goto_0
.end method


# virtual methods
.method a(Landroid/app/ActionBar$Tab;I)V
    .locals 1

    .prologue
    .line 565
    invoke-virtual {p0}, Lcom/miui/internal/app/ActionBarImpl;->getTabCount()I

    move-result v0

    if-ne p2, v0, :cond_0

    const/4 v0, 0x1

    :goto_0
    invoke-virtual {p0, p1, p2, v0}, Lcom/miui/internal/app/ActionBarImpl;->a(Landroid/app/ActionBar$Tab;IZ)V

    .line 566
    return-void

    .line 565
    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method a(Landroid/app/ActionBar$Tab;IZ)V
    .locals 1

    .prologue
    .line 569
    invoke-direct {p0}, Lcom/miui/internal/app/ActionBarImpl;->eo()V

    .line 570
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->hO:Lcom/miui/internal/widget/ScrollingTabContainerView;

    invoke-virtual {v0, p1, p2, p3}, Lcom/miui/internal/widget/ScrollingTabContainerView;->addTab(Landroid/app/ActionBar$Tab;IZ)V

    .line 571
    invoke-direct {p0, p1, p2}, Lcom/miui/internal/app/ActionBarImpl;->b(Landroid/app/ActionBar$Tab;I)V

    .line 572
    if-eqz p3, :cond_0

    .line 573
    invoke-virtual {p0, p1}, Lcom/miui/internal/app/ActionBarImpl;->selectTab(Landroid/app/ActionBar$Tab;)V

    .line 575
    :cond_0
    return-void
.end method

.method public addFragmentTab(Ljava/lang/String;Landroid/app/ActionBar$Tab;ILjava/lang/Class;Landroid/os/Bundle;Z)I
    .locals 7
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            "Landroid/app/ActionBar$Tab;",
            "I",
            "Ljava/lang/Class",
            "<+",
            "Landroid/app/Fragment;",
            ">;",
            "Landroid/os/Bundle;",
            "Z)I"
        }
    .end annotation

    .prologue
    .line 935
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Ni:Lcom/miui/internal/app/ActionBarViewPagerController;

    move-object v1, p1

    move-object v2, p2

    move v3, p3

    move-object v4, p4

    move-object v5, p5

    move v6, p6

    invoke-virtual/range {v0 .. v6}, Lcom/miui/internal/app/ActionBarViewPagerController;->addFragmentTab(Ljava/lang/String;Landroid/app/ActionBar$Tab;ILjava/lang/Class;Landroid/os/Bundle;Z)I

    move-result v0

    return v0
.end method

.method public addFragmentTab(Ljava/lang/String;Landroid/app/ActionBar$Tab;Ljava/lang/Class;Landroid/os/Bundle;Z)I
    .locals 6
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            "Landroid/app/ActionBar$Tab;",
            "Ljava/lang/Class",
            "<+",
            "Landroid/app/Fragment;",
            ">;",
            "Landroid/os/Bundle;",
            "Z)I"
        }
    .end annotation

    .prologue
    .line 927
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Ni:Lcom/miui/internal/app/ActionBarViewPagerController;

    move-object v1, p1

    move-object v2, p2

    move-object v3, p3

    move-object v4, p4

    move v5, p5

    invoke-virtual/range {v0 .. v5}, Lcom/miui/internal/app/ActionBarViewPagerController;->addFragmentTab(Ljava/lang/String;Landroid/app/ActionBar$Tab;Ljava/lang/Class;Landroid/os/Bundle;Z)I

    move-result v0

    return v0
.end method

.method public addOnFragmentViewPagerChangeListener(Lmiui/app/ActionBar$FragmentViewPagerChangeListener;)V
    .locals 1

    .prologue
    .line 904
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Ni:Lcom/miui/internal/app/ActionBarViewPagerController;

    invoke-virtual {v0, p1}, Lcom/miui/internal/app/ActionBarViewPagerController;->addOnFragmentViewPagerChangeListener(Lmiui/app/ActionBar$FragmentViewPagerChangeListener;)V

    .line 905
    return-void
.end method

.method public addOnMenuVisibilityListener(Landroid/app/ActionBar$OnMenuVisibilityListener;)V
    .locals 1

    .prologue
    .line 749
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->No:Ljava/util/ArrayList;

    invoke-virtual {v0, p1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 750
    return-void
.end method

.method public addTab(Landroid/app/ActionBar$Tab;)V
    .locals 1

    .prologue
    .line 517
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nk:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->isEmpty()Z

    move-result v0

    invoke-virtual {p0, p1, v0}, Lcom/miui/internal/app/ActionBarImpl;->addTab(Landroid/app/ActionBar$Tab;Z)V

    .line 518
    return-void
.end method

.method public addTab(Landroid/app/ActionBar$Tab;I)V
    .locals 1

    .prologue
    .line 533
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nk:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->isEmpty()Z

    move-result v0

    invoke-virtual {p0, p1, p2, v0}, Lcom/miui/internal/app/ActionBarImpl;->addTab(Landroid/app/ActionBar$Tab;IZ)V

    .line 534
    return-void
.end method

.method public addTab(Landroid/app/ActionBar$Tab;IZ)V
    .locals 2

    .prologue
    .line 538
    invoke-virtual {p0}, Lcom/miui/internal/app/ActionBarImpl;->isFragmentViewPagerMode()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 539
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "Cannot add tab directly in fragment view pager mode!\n Please using addFragmentTab()."

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 544
    :cond_0
    invoke-virtual {p0, p1, p2, p3}, Lcom/miui/internal/app/ActionBarImpl;->a(Landroid/app/ActionBar$Tab;IZ)V

    .line 545
    return-void
.end method

.method public addTab(Landroid/app/ActionBar$Tab;Z)V
    .locals 2

    .prologue
    .line 522
    invoke-virtual {p0}, Lcom/miui/internal/app/ActionBarImpl;->isFragmentViewPagerMode()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 523
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "Cannot add tab directly in fragment view pager mode!\n Please using addFragmentTab()."

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 528
    :cond_0
    invoke-virtual {p0, p1, p2}, Lcom/miui/internal/app/ActionBarImpl;->b(Landroid/app/ActionBar$Tab;Z)V

    .line 529
    return-void
.end method

.method am(I)V
    .locals 4

    .prologue
    .line 615
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->hO:Lcom/miui/internal/widget/ScrollingTabContainerView;

    if-nez v0, :cond_1

    .line 637
    :cond_0
    :goto_0
    return-void

    .line 620
    :cond_1
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nl:Lcom/miui/internal/app/ActionBarImpl$TabImpl;

    if-eqz v0, :cond_3

    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nl:Lcom/miui/internal/app/ActionBarImpl$TabImpl;

    invoke-virtual {v0}, Lcom/miui/internal/app/ActionBarImpl$TabImpl;->getPosition()I

    move-result v0

    move v1, v0

    .line 622
    :goto_1
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->hO:Lcom/miui/internal/widget/ScrollingTabContainerView;

    invoke-virtual {v0, p1}, Lcom/miui/internal/widget/ScrollingTabContainerView;->removeTabAt(I)V

    .line 623
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nk:Ljava/util/ArrayList;

    invoke-virtual {v0, p1}, Ljava/util/ArrayList;->remove(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/app/ActionBarImpl$TabImpl;

    .line 624
    if-eqz v0, :cond_2

    .line 625
    const/4 v2, -0x1

    invoke-virtual {v0, v2}, Lcom/miui/internal/app/ActionBarImpl$TabImpl;->setPosition(I)V

    .line 628
    :cond_2
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nk:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v3

    move v2, p1

    .line 629
    :goto_2
    if-ge v2, v3, :cond_4

    .line 630
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nk:Ljava/util/ArrayList;

    invoke-virtual {v0, v2}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/app/ActionBarImpl$TabImpl;

    invoke-virtual {v0, v2}, Lcom/miui/internal/app/ActionBarImpl$TabImpl;->setPosition(I)V

    .line 629
    add-int/lit8 v0, v2, 0x1

    move v2, v0

    goto :goto_2

    .line 620
    :cond_3
    iget v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nm:I

    move v1, v0

    goto :goto_1

    .line 633
    :cond_4
    if-ne v1, p1, :cond_0

    .line 634
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nk:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_5

    const/4 v0, 0x0

    :goto_3
    invoke-virtual {p0, v0}, Lcom/miui/internal/app/ActionBarImpl;->selectTab(Landroid/app/ActionBar$Tab;)V

    goto :goto_0

    :cond_5
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nk:Ljava/util/ArrayList;

    const/4 v1, 0x0

    add-int/lit8 v2, p1, -0x1

    invoke-static {v1, v2}, Ljava/lang/Math;->max(II)I

    move-result v1

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/app/ActionBarImpl$TabImpl;

    goto :goto_3
.end method

.method b(Landroid/app/ActionBar$Tab;)V
    .locals 1

    .prologue
    .line 552
    invoke-virtual {p0}, Lcom/miui/internal/app/ActionBarImpl;->getTabCount()I

    move-result v0

    if-nez v0, :cond_0

    const/4 v0, 0x1

    :goto_0
    invoke-virtual {p0, p1, v0}, Lcom/miui/internal/app/ActionBarImpl;->b(Landroid/app/ActionBar$Tab;Z)V

    .line 553
    return-void

    .line 552
    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method b(Landroid/app/ActionBar$Tab;Z)V
    .locals 1

    .prologue
    .line 556
    invoke-direct {p0}, Lcom/miui/internal/app/ActionBarImpl;->eo()V

    .line 557
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->hO:Lcom/miui/internal/widget/ScrollingTabContainerView;

    invoke-virtual {v0, p1, p2}, Lcom/miui/internal/widget/ScrollingTabContainerView;->addTab(Landroid/app/ActionBar$Tab;Z)V

    .line 558
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nk:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    invoke-direct {p0, p1, v0}, Lcom/miui/internal/app/ActionBarImpl;->b(Landroid/app/ActionBar$Tab;I)V

    .line 559
    if-eqz p2, :cond_0

    .line 560
    invoke-virtual {p0, p1}, Lcom/miui/internal/app/ActionBarImpl;->selectTab(Landroid/app/ActionBar$Tab;)V

    .line 562
    :cond_0
    return-void
.end method

.method c(Landroid/app/ActionBar$Tab;)V
    .locals 1

    .prologue
    .line 611
    invoke-virtual {p1}, Landroid/app/ActionBar$Tab;->getPosition()I

    move-result v0

    invoke-virtual {p0, v0}, Lcom/miui/internal/app/ActionBarImpl;->am(I)V

    .line 612
    return-void
.end method

.method public createActionModeView(Landroid/view/ActionMode$Callback;)Lcom/miui/internal/widget/ActionModeView;
    .locals 2

    .prologue
    .line 824
    instance-of v0, p1, Lmiui/view/SearchActionMode$Callback;

    if-eqz v0, :cond_2

    .line 825
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nx:Lcom/miui/internal/widget/SearchActionModeView;

    if-nez v0, :cond_0

    .line 826
    invoke-virtual {p0}, Lcom/miui/internal/app/ActionBarImpl;->createSearchActionModeView()Lcom/miui/internal/widget/SearchActionModeView;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nx:Lcom/miui/internal/widget/SearchActionModeView;

    .line 828
    :cond_0
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nc:Lcom/miui/internal/widget/ActionBarOverlayLayout;

    iget-object v1, p0, Lcom/miui/internal/app/ActionBarImpl;->Nx:Lcom/miui/internal/widget/SearchActionModeView;

    invoke-virtual {v1}, Lcom/miui/internal/widget/SearchActionModeView;->getParent()Landroid/view/ViewParent;

    move-result-object v1

    if-eq v0, v1, :cond_1

    .line 829
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nc:Lcom/miui/internal/widget/ActionBarOverlayLayout;

    iget-object v1, p0, Lcom/miui/internal/app/ActionBarImpl;->Nx:Lcom/miui/internal/widget/SearchActionModeView;

    invoke-virtual {v0, v1}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->addView(Landroid/view/View;)V

    .line 831
    :cond_1
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nx:Lcom/miui/internal/widget/SearchActionModeView;

    .line 836
    :goto_0
    return-object v0

    .line 833
    :cond_2
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nf:Lcom/miui/internal/widget/ActionBarContextView;

    goto :goto_0
.end method

.method public createSearchActionModeView()Lcom/miui/internal/widget/SearchActionModeView;
    .locals 4

    .prologue
    .line 840
    invoke-virtual {p0}, Lcom/miui/internal/app/ActionBarImpl;->getThemedContext()Landroid/content/Context;

    move-result-object v0

    invoke-static {v0}, Landroid/view/LayoutInflater;->from(Landroid/content/Context;)Landroid/view/LayoutInflater;

    move-result-object v0

    .line 841
    sget v1, Lcom/miui/internal/R$layout;->search_action_mode_view:I

    iget-object v2, p0, Lcom/miui/internal/app/ActionBarImpl;->Nc:Lcom/miui/internal/widget/ActionBarOverlayLayout;

    const/4 v3, 0x0

    invoke-virtual {v0, v1, v2, v3}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;Z)Landroid/view/View;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/widget/SearchActionModeView;

    .line 843
    new-instance v1, Lcom/miui/internal/app/ActionBarImpl$1;

    invoke-direct {v1, p0}, Lcom/miui/internal/app/ActionBarImpl$1;-><init>(Lcom/miui/internal/app/ActionBarImpl;)V

    invoke-virtual {v0, v1}, Lcom/miui/internal/widget/SearchActionModeView;->setOnBackClickListener(Landroid/view/View$OnClickListener;)V

    .line 851
    iget-object v1, p0, Lcom/miui/internal/app/ActionBarImpl;->Nd:Lcom/miui/internal/widget/ActionBarContainer;

    invoke-virtual {v1}, Lcom/miui/internal/widget/ActionBarContainer;->getPendingInsets()Landroid/graphics/Rect;

    move-result-object v1

    .line 852
    if-eqz v1, :cond_0

    .line 853
    iget v1, v1, Landroid/graphics/Rect;->top:I

    invoke-virtual {v0, v1}, Lcom/miui/internal/widget/SearchActionModeView;->setStatusBarPaddingTop(I)V

    .line 855
    :cond_0
    return-object v0
.end method

.method public doHide(Z)V
    .locals 5

    .prologue
    const/4 v1, 0x0

    const/16 v4, 0x8

    .line 1095
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nd:Lcom/miui/internal/widget/ActionBarContainer;

    invoke-virtual {v0}, Lcom/miui/internal/widget/ActionBarContainer;->clearAnimation()V

    .line 1096
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nd:Lcom/miui/internal/widget/ActionBarContainer;

    invoke-virtual {v0}, Lcom/miui/internal/widget/ActionBarContainer;->getVisibility()I

    move-result v0

    if-ne v0, v4, :cond_1

    .line 1118
    :cond_0
    :goto_0
    return-void

    .line 1100
    :cond_1
    invoke-virtual {p0}, Lcom/miui/internal/app/ActionBarImpl;->eq()Z

    move-result v0

    if-nez v0, :cond_2

    if-eqz p1, :cond_5

    :cond_2
    const/4 v0, 0x1

    .line 1102
    :goto_1
    if-eqz v0, :cond_3

    .line 1103
    iget-object v2, p0, Lcom/miui/internal/app/ActionBarImpl;->mContext:Landroid/content/Context;

    sget v3, Lcom/miui/internal/R$anim;->action_bar_slide_out_top:I

    invoke-static {v2, v3}, Landroid/view/animation/AnimationUtils;->loadAnimation(Landroid/content/Context;I)Landroid/view/animation/Animation;

    move-result-object v2

    .line 1105
    iget-object v3, p0, Lcom/miui/internal/app/ActionBarImpl;->Nd:Lcom/miui/internal/widget/ActionBarContainer;

    invoke-virtual {v3, v2}, Lcom/miui/internal/widget/ActionBarContainer;->startAnimation(Landroid/view/animation/Animation;)V

    .line 1107
    :cond_3
    iget-object v2, p0, Lcom/miui/internal/app/ActionBarImpl;->Nd:Lcom/miui/internal/widget/ActionBarContainer;

    invoke-virtual {v2, v4}, Lcom/miui/internal/widget/ActionBarContainer;->setVisibility(I)V

    .line 1109
    iget-object v2, p0, Lcom/miui/internal/app/ActionBarImpl;->mSplitView:Lcom/miui/internal/widget/ActionBarContainer;

    if-eqz v2, :cond_0

    iget-object v2, p0, Lcom/miui/internal/app/ActionBarImpl;->mSplitView:Lcom/miui/internal/widget/ActionBarContainer;

    invoke-virtual {v2}, Lcom/miui/internal/widget/ActionBarContainer;->getVisibility()I

    move-result v2

    if-eq v2, v4, :cond_0

    .line 1110
    if-eqz v0, :cond_4

    .line 1111
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->mContext:Landroid/content/Context;

    sget v2, Lcom/miui/internal/R$anim;->action_bar_slide_out_bottom:I

    invoke-static {v0, v2}, Landroid/view/animation/AnimationUtils;->loadAnimation(Landroid/content/Context;I)Landroid/view/animation/Animation;

    move-result-object v0

    .line 1113
    iget-object v2, p0, Lcom/miui/internal/app/ActionBarImpl;->mSplitView:Lcom/miui/internal/widget/ActionBarContainer;

    invoke-virtual {v2, v0}, Lcom/miui/internal/widget/ActionBarContainer;->startAnimation(Landroid/view/animation/Animation;)V

    .line 1115
    :cond_4
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->mSplitView:Lcom/miui/internal/widget/ActionBarContainer;

    invoke-virtual {v0, v4}, Lcom/miui/internal/widget/ActionBarContainer;->setVisibility(I)V

    .line 1116
    invoke-direct {p0, v1}, Lcom/miui/internal/app/ActionBarImpl;->t(Z)V

    goto :goto_0

    :cond_5
    move v0, v1

    .line 1100
    goto :goto_1
.end method

.method public doShow(Z)V
    .locals 5

    .prologue
    const/4 v2, 0x1

    const/4 v1, 0x0

    .line 1069
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nd:Lcom/miui/internal/widget/ActionBarContainer;

    invoke-virtual {v0}, Lcom/miui/internal/widget/ActionBarContainer;->clearAnimation()V

    .line 1070
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nd:Lcom/miui/internal/widget/ActionBarContainer;

    invoke-virtual {v0}, Lcom/miui/internal/widget/ActionBarContainer;->getVisibility()I

    move-result v0

    if-nez v0, :cond_1

    .line 1092
    :cond_0
    :goto_0
    return-void

    .line 1074
    :cond_1
    invoke-virtual {p0}, Lcom/miui/internal/app/ActionBarImpl;->eq()Z

    move-result v0

    if-nez v0, :cond_2

    if-eqz p1, :cond_5

    :cond_2
    move v0, v2

    .line 1076
    :goto_1
    if-eqz v0, :cond_3

    .line 1077
    iget-object v3, p0, Lcom/miui/internal/app/ActionBarImpl;->mContext:Landroid/content/Context;

    sget v4, Lcom/miui/internal/R$anim;->action_bar_slide_in_top:I

    invoke-static {v3, v4}, Landroid/view/animation/AnimationUtils;->loadAnimation(Landroid/content/Context;I)Landroid/view/animation/Animation;

    move-result-object v3

    .line 1079
    iget-object v4, p0, Lcom/miui/internal/app/ActionBarImpl;->Nd:Lcom/miui/internal/widget/ActionBarContainer;

    invoke-virtual {v4, v3}, Lcom/miui/internal/widget/ActionBarContainer;->startAnimation(Landroid/view/animation/Animation;)V

    .line 1081
    :cond_3
    iget-object v3, p0, Lcom/miui/internal/app/ActionBarImpl;->Nd:Lcom/miui/internal/widget/ActionBarContainer;

    invoke-virtual {v3, v1}, Lcom/miui/internal/widget/ActionBarContainer;->setVisibility(I)V

    .line 1083
    iget-object v3, p0, Lcom/miui/internal/app/ActionBarImpl;->mSplitView:Lcom/miui/internal/widget/ActionBarContainer;

    if-eqz v3, :cond_0

    iget-object v3, p0, Lcom/miui/internal/app/ActionBarImpl;->mSplitView:Lcom/miui/internal/widget/ActionBarContainer;

    invoke-virtual {v3}, Lcom/miui/internal/widget/ActionBarContainer;->getVisibility()I

    move-result v3

    if-eqz v3, :cond_0

    .line 1084
    if-eqz v0, :cond_4

    .line 1085
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->mContext:Landroid/content/Context;

    sget v3, Lcom/miui/internal/R$anim;->action_bar_slide_in_bottom:I

    invoke-static {v0, v3}, Landroid/view/animation/AnimationUtils;->loadAnimation(Landroid/content/Context;I)Landroid/view/animation/Animation;

    move-result-object v0

    .line 1087
    iget-object v3, p0, Lcom/miui/internal/app/ActionBarImpl;->mSplitView:Lcom/miui/internal/widget/ActionBarContainer;

    invoke-virtual {v3, v0}, Lcom/miui/internal/widget/ActionBarContainer;->startAnimation(Landroid/view/animation/Animation;)V

    .line 1089
    :cond_4
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->mSplitView:Lcom/miui/internal/widget/ActionBarContainer;

    invoke-virtual {v0, v1}, Lcom/miui/internal/widget/ActionBarContainer;->setVisibility(I)V

    .line 1090
    invoke-direct {p0, v2}, Lcom/miui/internal/app/ActionBarImpl;->t(Z)V

    goto :goto_0

    :cond_5
    move v0, v1

    .line 1074
    goto :goto_1
.end method

.method ek()Lcom/miui/internal/widget/ActionBarOverlayLayout;
    .locals 1

    .prologue
    .line 548
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nc:Lcom/miui/internal/widget/ActionBarOverlayLayout;

    return-object v0
.end method

.method el()V
    .locals 0

    .prologue
    .line 640
    invoke-direct {p0}, Lcom/miui/internal/app/ActionBarImpl;->ep()V

    .line 641
    return-void
.end method

.method em()V
    .locals 1

    .prologue
    .line 721
    iget-boolean v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nu:Z

    if-nez v0, :cond_0

    .line 722
    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nu:Z

    .line 723
    const/4 v0, 0x0

    invoke-direct {p0, v0}, Lcom/miui/internal/app/ActionBarImpl;->s(Z)V

    .line 725
    :cond_0
    return-void
.end method

.method en()V
    .locals 2

    .prologue
    const/4 v1, 0x0

    .line 736
    iget-boolean v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nu:Z

    if-eqz v0, :cond_0

    .line 737
    iput-boolean v1, p0, Lcom/miui/internal/app/ActionBarImpl;->Nu:Z

    .line 738
    invoke-direct {p0, v1}, Lcom/miui/internal/app/ActionBarImpl;->s(Z)V

    .line 740
    :cond_0
    return-void
.end method

.method eq()Z
    .locals 1

    .prologue
    .line 1121
    iget-boolean v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nw:Z

    return v0
.end method

.method public getCustomView()Landroid/view/View;
    .locals 1

    .prologue
    .line 438
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Ne:Lcom/miui/internal/widget/ActionBarView;

    invoke-virtual {v0}, Lcom/miui/internal/widget/ActionBarView;->getCustomNavigationView()Landroid/view/View;

    move-result-object v0

    return-object v0
.end method

.method public getDisplayOptions()I
    .locals 1

    .prologue
    .line 499
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Ne:Lcom/miui/internal/widget/ActionBarView;

    invoke-virtual {v0}, Lcom/miui/internal/widget/ActionBarView;->getDisplayOptions()I

    move-result v0

    return v0
.end method

.method public getFragmentAt(I)Landroid/app/Fragment;
    .locals 1

    .prologue
    .line 920
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Ni:Lcom/miui/internal/app/ActionBarViewPagerController;

    invoke-virtual {v0, p1}, Lcom/miui/internal/app/ActionBarViewPagerController;->getFragmentAt(I)Landroid/app/Fragment;

    move-result-object v0

    return-object v0
.end method

.method public getFragmentTabCount()I
    .locals 1

    .prologue
    .line 915
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Ni:Lcom/miui/internal/app/ActionBarViewPagerController;

    invoke-virtual {v0}, Lcom/miui/internal/app/ActionBarViewPagerController;->getFragmentTabCount()I

    move-result v0

    return v0
.end method

.method public getHeight()I
    .locals 1

    .prologue
    .line 709
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nd:Lcom/miui/internal/widget/ActionBarContainer;

    invoke-virtual {v0}, Lcom/miui/internal/widget/ActionBarContainer;->getHeight()I

    move-result v0

    return v0
.end method

.method public getNavigationItemCount()I
    .locals 2

    .prologue
    const/4 v0, 0x0

    .line 368
    iget-object v1, p0, Lcom/miui/internal/app/ActionBarImpl;->Ne:Lcom/miui/internal/widget/ActionBarView;

    invoke-virtual {v1}, Lcom/miui/internal/widget/ActionBarView;->getNavigationMode()I

    move-result v1

    packed-switch v1, :pswitch_data_0

    .line 375
    :cond_0
    :goto_0
    return v0

    .line 370
    :pswitch_0
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nk:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    goto :goto_0

    .line 372
    :pswitch_1
    iget-object v1, p0, Lcom/miui/internal/app/ActionBarImpl;->Ne:Lcom/miui/internal/widget/ActionBarView;

    invoke-virtual {v1}, Lcom/miui/internal/widget/ActionBarView;->getDropdownAdapter()Landroid/widget/SpinnerAdapter;

    move-result-object v1

    .line 373
    if-eqz v1, :cond_0

    invoke-interface {v1}, Landroid/widget/SpinnerAdapter;->getCount()I

    move-result v0

    goto :goto_0

    .line 368
    nop

    :pswitch_data_0
    .packed-switch 0x1
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method

.method public getNavigationMode()I
    .locals 1

    .prologue
    .line 469
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Ne:Lcom/miui/internal/widget/ActionBarView;

    invoke-virtual {v0}, Lcom/miui/internal/widget/ActionBarView;->getNavigationMode()I

    move-result v0

    return v0
.end method

.method public getSelectedNavigationIndex()I
    .locals 2

    .prologue
    const/4 v0, -0x1

    .line 356
    iget-object v1, p0, Lcom/miui/internal/app/ActionBarImpl;->Ne:Lcom/miui/internal/widget/ActionBarView;

    invoke-virtual {v1}, Lcom/miui/internal/widget/ActionBarView;->getNavigationMode()I

    move-result v1

    packed-switch v1, :pswitch_data_0

    .line 362
    :cond_0
    :goto_0
    return v0

    .line 358
    :pswitch_0
    iget-object v1, p0, Lcom/miui/internal/app/ActionBarImpl;->Nl:Lcom/miui/internal/app/ActionBarImpl$TabImpl;

    if-eqz v1, :cond_0

    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nl:Lcom/miui/internal/app/ActionBarImpl$TabImpl;

    invoke-virtual {v0}, Lcom/miui/internal/app/ActionBarImpl$TabImpl;->getPosition()I

    move-result v0

    goto :goto_0

    .line 360
    :pswitch_1
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Ne:Lcom/miui/internal/widget/ActionBarView;

    invoke-virtual {v0}, Lcom/miui/internal/widget/ActionBarView;->getDropdownSelectedPosition()I

    move-result v0

    goto :goto_0

    .line 356
    nop

    :pswitch_data_0
    .packed-switch 0x1
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method

.method public getSelectedTab()Landroid/app/ActionBar$Tab;
    .locals 1

    .prologue
    .line 677
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nl:Lcom/miui/internal/app/ActionBarImpl$TabImpl;

    return-object v0
.end method

.method public getSubtitle()Ljava/lang/CharSequence;
    .locals 1

    .prologue
    .line 459
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Ne:Lcom/miui/internal/widget/ActionBarView;

    invoke-virtual {v0}, Lcom/miui/internal/widget/ActionBarView;->getSubtitle()Ljava/lang/CharSequence;

    move-result-object v0

    return-object v0
.end method

.method public getTabAt(I)Landroid/app/ActionBar$Tab;
    .locals 1

    .prologue
    .line 682
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nk:Ljava/util/ArrayList;

    invoke-virtual {v0, p1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/app/ActionBar$Tab;

    return-object v0
.end method

.method public getTabCount()I
    .locals 1

    .prologue
    .line 687
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nk:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    return v0
.end method

.method public getThemedContext()Landroid/content/Context;
    .locals 4

    .prologue
    .line 692
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->xE:Landroid/content/Context;

    if-nez v0, :cond_0

    .line 693
    new-instance v0, Landroid/util/TypedValue;

    invoke-direct {v0}, Landroid/util/TypedValue;-><init>()V

    .line 694
    iget-object v1, p0, Lcom/miui/internal/app/ActionBarImpl;->mContext:Landroid/content/Context;

    invoke-virtual {v1}, Landroid/content/Context;->getTheme()Landroid/content/res/Resources$Theme;

    move-result-object v1

    .line 695
    const v2, 0x1010397

    const/4 v3, 0x1

    invoke-virtual {v1, v2, v0, v3}, Landroid/content/res/Resources$Theme;->resolveAttribute(ILandroid/util/TypedValue;Z)Z

    .line 696
    iget v0, v0, Landroid/util/TypedValue;->resourceId:I

    .line 698
    if-eqz v0, :cond_1

    .line 699
    new-instance v1, Landroid/view/ContextThemeWrapper;

    iget-object v2, p0, Lcom/miui/internal/app/ActionBarImpl;->mContext:Landroid/content/Context;

    invoke-direct {v1, v2, v0}, Landroid/view/ContextThemeWrapper;-><init>(Landroid/content/Context;I)V

    iput-object v1, p0, Lcom/miui/internal/app/ActionBarImpl;->xE:Landroid/content/Context;

    .line 704
    :cond_0
    :goto_0
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->xE:Landroid/content/Context;

    return-object v0

    .line 701
    :cond_1
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->mContext:Landroid/content/Context;

    iput-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->xE:Landroid/content/Context;

    goto :goto_0
.end method

.method public getTitle()Ljava/lang/CharSequence;
    .locals 1

    .prologue
    .line 449
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Ne:Lcom/miui/internal/widget/ActionBarView;

    invoke-virtual {v0}, Lcom/miui/internal/widget/ActionBarView;->getTitle()Ljava/lang/CharSequence;

    move-result-object v0

    return-object v0
.end method

.method public getViewPagerOffscreenPageLimit()I
    .locals 1

    .prologue
    .line 991
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Ni:Lcom/miui/internal/app/ActionBarViewPagerController;

    invoke-virtual {v0}, Lcom/miui/internal/app/ActionBarViewPagerController;->getViewPagerOffscreenPageLimit()I

    move-result v0

    return v0
.end method

.method public hasNonEmbeddedTabs()Z
    .locals 2

    .prologue
    .line 293
    iget-boolean v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nq:Z

    if-nez v0, :cond_0

    invoke-virtual {p0}, Lcom/miui/internal/app/ActionBarImpl;->getNavigationMode()I

    move-result v0

    const/4 v1, 0x2

    if-ne v0, v1, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public hide()V
    .locals 1

    .prologue
    .line 729
    iget-boolean v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Ns:Z

    if-nez v0, :cond_0

    .line 730
    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Ns:Z

    .line 731
    const/4 v0, 0x0

    invoke-direct {p0, v0}, Lcom/miui/internal/app/ActionBarImpl;->s(Z)V

    .line 733
    :cond_0
    return-void
.end method

.method protected init(Landroid/view/ViewGroup;)V
    .locals 5

    .prologue
    const/4 v2, 0x0

    const/4 v1, 0x1

    .line 223
    move-object v0, p1

    check-cast v0, Lcom/miui/internal/widget/ActionBarOverlayLayout;

    iput-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nc:Lcom/miui/internal/widget/ActionBarOverlayLayout;

    .line 224
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nc:Lcom/miui/internal/widget/ActionBarOverlayLayout;

    invoke-virtual {v0, p0}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->setActionBar(Lmiui/app/ActionBar;)V

    .line 225
    sget v0, Lcom/miui/internal/R$id;->action_bar:I

    invoke-virtual {p1, v0}, Landroid/view/ViewGroup;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/widget/ActionBarView;

    iput-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Ne:Lcom/miui/internal/widget/ActionBarView;

    .line 226
    sget v0, Lcom/miui/internal/R$id;->action_context_bar:I

    invoke-virtual {p1, v0}, Landroid/view/ViewGroup;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/widget/ActionBarContextView;

    iput-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nf:Lcom/miui/internal/widget/ActionBarContextView;

    .line 228
    sget v0, Lcom/miui/internal/R$id;->action_bar_container:I

    invoke-virtual {p1, v0}, Landroid/view/ViewGroup;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/widget/ActionBarContainer;

    iput-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nd:Lcom/miui/internal/widget/ActionBarContainer;

    .line 230
    sget v0, Lcom/miui/internal/R$id;->split_action_bar:I

    invoke-virtual {p1, v0}, Landroid/view/ViewGroup;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/widget/ActionBarContainer;

    iput-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->mSplitView:Lcom/miui/internal/widget/ActionBarContainer;

    .line 232
    sget v0, Lcom/miui/internal/R$id;->content_mask:I

    invoke-virtual {p1, v0}, Landroid/view/ViewGroup;->findViewById(I)Landroid/view/View;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->rj:Landroid/view/View;

    .line 234
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->rj:Landroid/view/View;

    if-eqz v0, :cond_0

    .line 235
    new-instance v0, Lcom/miui/internal/app/ActionBarImpl$2;

    invoke-direct {v0, p0}, Lcom/miui/internal/app/ActionBarImpl$2;-><init>(Lcom/miui/internal/app/ActionBarImpl;)V

    iput-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nh:Landroid/view/View$OnClickListener;

    .line 245
    :cond_0
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Ne:Lcom/miui/internal/widget/ActionBarView;

    if-eqz v0, :cond_1

    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nf:Lcom/miui/internal/widget/ActionBarContextView;

    if-eqz v0, :cond_1

    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nd:Lcom/miui/internal/widget/ActionBarContainer;

    if-nez v0, :cond_2

    .line 247
    :cond_1
    new-instance v0, Ljava/lang/IllegalStateException;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/Class;->getSimpleName()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, " can only be used "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, "with a compatible window decor layout"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 252
    :cond_2
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Ne:Lcom/miui/internal/widget/ActionBarView;

    invoke-virtual {v0}, Lcom/miui/internal/widget/ActionBarView;->isSplitActionBar()Z

    move-result v0

    if-eqz v0, :cond_6

    move v0, v1

    :goto_0
    iput v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Np:I

    .line 256
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Ne:Lcom/miui/internal/widget/ActionBarView;

    invoke-virtual {v0}, Lcom/miui/internal/widget/ActionBarView;->getDisplayOptions()I

    move-result v0

    .line 257
    and-int/lit8 v0, v0, 0x4

    if-eqz v0, :cond_7

    move v0, v1

    .line 258
    :goto_1
    if-eqz v0, :cond_3

    .line 259
    iput-boolean v1, p0, Lcom/miui/internal/app/ActionBarImpl;->Nn:Z

    .line 262
    :cond_3
    iget-object v3, p0, Lcom/miui/internal/app/ActionBarImpl;->mContext:Landroid/content/Context;

    invoke-static {v3}, Lcom/miui/internal/view/ActionBarPolicy;->get(Landroid/content/Context;)Lcom/miui/internal/view/ActionBarPolicy;

    move-result-object v3

    .line 263
    invoke-virtual {v3}, Lcom/miui/internal/view/ActionBarPolicy;->enableHomeButtonByDefault()Z

    move-result v4

    if-nez v4, :cond_4

    if-eqz v0, :cond_5

    :cond_4
    move v2, v1

    :cond_5
    invoke-virtual {p0, v2}, Lcom/miui/internal/app/ActionBarImpl;->setHomeButtonEnabled(Z)V

    .line 264
    invoke-virtual {v3}, Lcom/miui/internal/view/ActionBarPolicy;->hasEmbeddedTabs()Z

    move-result v0

    invoke-direct {p0, v0}, Lcom/miui/internal/app/ActionBarImpl;->q(Z)V

    .line 265
    return-void

    :cond_6
    move v0, v2

    .line 252
    goto :goto_0

    :cond_7
    move v0, v2

    .line 257
    goto :goto_1
.end method

.method public isFragmentViewPagerMode()Z
    .locals 1

    .prologue
    .line 875
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Ni:Lcom/miui/internal/app/ActionBarViewPagerController;

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public isShowing()Z
    .locals 1

    .prologue
    .line 744
    iget-boolean v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nv:Z

    return v0
.end method

.method public newTab()Landroid/app/ActionBar$Tab;
    .locals 1

    .prologue
    .line 512
    new-instance v0, Lcom/miui/internal/app/ActionBarImpl$TabImpl;

    invoke-direct {v0, p0}, Lcom/miui/internal/app/ActionBarImpl$TabImpl;-><init>(Lcom/miui/internal/app/ActionBarImpl;)V

    return-object v0
.end method

.method public onConfigurationChanged(Landroid/content/res/Configuration;)V
    .locals 1

    .prologue
    .line 268
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->mContext:Landroid/content/Context;

    invoke-static {v0}, Lcom/miui/internal/view/ActionBarPolicy;->get(Landroid/content/Context;)Lcom/miui/internal/view/ActionBarPolicy;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/internal/view/ActionBarPolicy;->hasEmbeddedTabs()Z

    move-result v0

    invoke-direct {p0, v0}, Lcom/miui/internal/app/ActionBarImpl;->q(Z)V

    .line 269
    return-void
.end method

.method r(Z)V
    .locals 2

    .prologue
    .line 800
    if-eqz p1, :cond_1

    .line 801
    invoke-virtual {p0}, Lcom/miui/internal/app/ActionBarImpl;->em()V

    .line 806
    :goto_0
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nj:Lcom/miui/internal/widget/ActionModeView;

    invoke-interface {v0, p1}, Lcom/miui/internal/widget/ActionModeView;->animateToVisibility(Z)V

    .line 807
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->hO:Lcom/miui/internal/widget/ScrollingTabContainerView;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Ne:Lcom/miui/internal/widget/ActionBarView;

    invoke-virtual {v0}, Lcom/miui/internal/widget/ActionBarView;->hasEmbeddedTabs()Z

    move-result v0

    if-nez v0, :cond_0

    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Ne:Lcom/miui/internal/widget/ActionBarView;

    invoke-virtual {v0}, Lcom/miui/internal/widget/ActionBarView;->isCollapsed()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 808
    iget-object v1, p0, Lcom/miui/internal/app/ActionBarImpl;->hO:Lcom/miui/internal/widget/ScrollingTabContainerView;

    if-eqz p1, :cond_2

    const/16 v0, 0x8

    :goto_1
    invoke-virtual {v1, v0}, Lcom/miui/internal/widget/ScrollingTabContainerView;->setVisibility(I)V

    .line 810
    :cond_0
    return-void

    .line 803
    :cond_1
    invoke-virtual {p0}, Lcom/miui/internal/app/ActionBarImpl;->en()V

    goto :goto_0

    .line 808
    :cond_2
    const/4 v0, 0x0

    goto :goto_1
.end method

.method public removeAllFragmentTab()V
    .locals 1

    .prologue
    .line 961
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Ni:Lcom/miui/internal/app/ActionBarViewPagerController;

    invoke-virtual {v0}, Lcom/miui/internal/app/ActionBarViewPagerController;->removeAllFragmentTab()V

    .line 962
    return-void
.end method

.method public removeAllTabs()V
    .locals 2

    .prologue
    .line 601
    invoke-virtual {p0}, Lcom/miui/internal/app/ActionBarImpl;->isFragmentViewPagerMode()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 602
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "Cannot add tab directly in fragment view pager mode!\n Please using addFragmentTab()."

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 607
    :cond_0
    invoke-virtual {p0}, Lcom/miui/internal/app/ActionBarImpl;->el()V

    .line 608
    return-void
.end method

.method public removeFragmentTab(Landroid/app/ActionBar$Tab;)V
    .locals 1

    .prologue
    .line 951
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Ni:Lcom/miui/internal/app/ActionBarViewPagerController;

    invoke-virtual {v0, p1}, Lcom/miui/internal/app/ActionBarViewPagerController;->removeFragmentTab(Landroid/app/ActionBar$Tab;)V

    .line 952
    return-void
.end method

.method public removeFragmentTab(Landroid/app/Fragment;)V
    .locals 1

    .prologue
    .line 956
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Ni:Lcom/miui/internal/app/ActionBarViewPagerController;

    invoke-virtual {v0, p1}, Lcom/miui/internal/app/ActionBarViewPagerController;->a(Landroid/app/Fragment;)V

    .line 957
    return-void
.end method

.method public removeFragmentTab(Ljava/lang/String;)V
    .locals 1

    .prologue
    .line 946
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Ni:Lcom/miui/internal/app/ActionBarViewPagerController;

    invoke-virtual {v0, p1}, Lcom/miui/internal/app/ActionBarViewPagerController;->removeFragmentTab(Ljava/lang/String;)V

    .line 947
    return-void
.end method

.method public removeFragmentTabAt(I)V
    .locals 1

    .prologue
    .line 941
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Ni:Lcom/miui/internal/app/ActionBarViewPagerController;

    invoke-virtual {v0, p1}, Lcom/miui/internal/app/ActionBarViewPagerController;->A(I)V

    .line 942
    return-void
.end method

.method public removeOnFragmentViewPagerChangeListener(Lmiui/app/ActionBar$FragmentViewPagerChangeListener;)V
    .locals 1

    .prologue
    .line 910
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Ni:Lcom/miui/internal/app/ActionBarViewPagerController;

    invoke-virtual {v0, p1}, Lcom/miui/internal/app/ActionBarViewPagerController;->removeOnFragmentViewPagerChangeListener(Lmiui/app/ActionBar$FragmentViewPagerChangeListener;)V

    .line 911
    return-void
.end method

.method public removeOnMenuVisibilityListener(Landroid/app/ActionBar$OnMenuVisibilityListener;)V
    .locals 1

    .prologue
    .line 755
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->No:Ljava/util/ArrayList;

    invoke-virtual {v0, p1}, Ljava/util/ArrayList;->remove(Ljava/lang/Object;)Z

    .line 756
    return-void
.end method

.method public removeTab(Landroid/app/ActionBar$Tab;)V
    .locals 2

    .prologue
    .line 579
    invoke-virtual {p0}, Lcom/miui/internal/app/ActionBarImpl;->isFragmentViewPagerMode()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 580
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "Cannot add tab directly in fragment view pager mode!\n Please using addFragmentTab()."

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 585
    :cond_0
    invoke-virtual {p0, p1}, Lcom/miui/internal/app/ActionBarImpl;->c(Landroid/app/ActionBar$Tab;)V

    .line 586
    return-void
.end method

.method public removeTabAt(I)V
    .locals 2

    .prologue
    .line 590
    invoke-virtual {p0}, Lcom/miui/internal/app/ActionBarImpl;->isFragmentViewPagerMode()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 591
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "Cannot add tab directly in fragment view pager mode!\n Please using addFragmentTab()."

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 596
    :cond_0
    invoke-virtual {p0, p1}, Lcom/miui/internal/app/ActionBarImpl;->am(I)V

    .line 597
    return-void
.end method

.method public selectTab(Landroid/app/ActionBar$Tab;)V
    .locals 3

    .prologue
    const/4 v0, -0x1

    .line 645
    invoke-virtual {p0}, Lcom/miui/internal/app/ActionBarImpl;->getNavigationMode()I

    move-result v1

    const/4 v2, 0x2

    if-eq v1, v2, :cond_2

    .line 646
    if-eqz p1, :cond_0

    invoke-virtual {p1}, Landroid/app/ActionBar$Tab;->getPosition()I

    move-result v0

    :cond_0
    iput v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nm:I

    .line 673
    :cond_1
    :goto_0
    return-void

    .line 651
    :cond_2
    iget-object v1, p0, Lcom/miui/internal/app/ActionBarImpl;->kO:Landroid/app/FragmentManager;

    invoke-virtual {v1}, Landroid/app/FragmentManager;->beginTransaction()Landroid/app/FragmentTransaction;

    move-result-object v1

    invoke-virtual {v1}, Landroid/app/FragmentTransaction;->disallowAddToBackStack()Landroid/app/FragmentTransaction;

    move-result-object v1

    .line 653
    iget-object v2, p0, Lcom/miui/internal/app/ActionBarImpl;->Nl:Lcom/miui/internal/app/ActionBarImpl$TabImpl;

    if-ne v2, p1, :cond_4

    .line 654
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nl:Lcom/miui/internal/app/ActionBarImpl$TabImpl;

    if-eqz v0, :cond_3

    .line 655
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nl:Lcom/miui/internal/app/ActionBarImpl$TabImpl;

    invoke-virtual {v0}, Lcom/miui/internal/app/ActionBarImpl$TabImpl;->getCallback()Landroid/app/ActionBar$TabListener;

    move-result-object v0

    iget-object v2, p0, Lcom/miui/internal/app/ActionBarImpl;->Nl:Lcom/miui/internal/app/ActionBarImpl$TabImpl;

    invoke-interface {v0, v2, v1}, Landroid/app/ActionBar$TabListener;->onTabReselected(Landroid/app/ActionBar$Tab;Landroid/app/FragmentTransaction;)V

    .line 656
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->hO:Lcom/miui/internal/widget/ScrollingTabContainerView;

    invoke-virtual {p1}, Landroid/app/ActionBar$Tab;->getPosition()I

    move-result v2

    invoke-virtual {v0, v2}, Lcom/miui/internal/widget/ScrollingTabContainerView;->animateToTab(I)V

    .line 670
    :cond_3
    :goto_1
    invoke-virtual {v1}, Landroid/app/FragmentTransaction;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_1

    .line 671
    invoke-virtual {v1}, Landroid/app/FragmentTransaction;->commit()I

    goto :goto_0

    .line 659
    :cond_4
    iget-object v2, p0, Lcom/miui/internal/app/ActionBarImpl;->hO:Lcom/miui/internal/widget/ScrollingTabContainerView;

    if-eqz p1, :cond_5

    invoke-virtual {p1}, Landroid/app/ActionBar$Tab;->getPosition()I

    move-result v0

    :cond_5
    invoke-virtual {v2, v0}, Lcom/miui/internal/widget/ScrollingTabContainerView;->setTabSelected(I)V

    .line 661
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nl:Lcom/miui/internal/app/ActionBarImpl$TabImpl;

    if-eqz v0, :cond_6

    .line 662
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nl:Lcom/miui/internal/app/ActionBarImpl$TabImpl;

    invoke-virtual {v0}, Lcom/miui/internal/app/ActionBarImpl$TabImpl;->getCallback()Landroid/app/ActionBar$TabListener;

    move-result-object v0

    iget-object v2, p0, Lcom/miui/internal/app/ActionBarImpl;->Nl:Lcom/miui/internal/app/ActionBarImpl$TabImpl;

    invoke-interface {v0, v2, v1}, Landroid/app/ActionBar$TabListener;->onTabUnselected(Landroid/app/ActionBar$Tab;Landroid/app/FragmentTransaction;)V

    .line 664
    :cond_6
    check-cast p1, Lcom/miui/internal/app/ActionBarImpl$TabImpl;

    iput-object p1, p0, Lcom/miui/internal/app/ActionBarImpl;->Nl:Lcom/miui/internal/app/ActionBarImpl$TabImpl;

    .line 665
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nl:Lcom/miui/internal/app/ActionBarImpl$TabImpl;

    if-eqz v0, :cond_3

    .line 666
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nl:Lcom/miui/internal/app/ActionBarImpl$TabImpl;

    invoke-virtual {v0}, Lcom/miui/internal/app/ActionBarImpl$TabImpl;->getCallback()Landroid/app/ActionBar$TabListener;

    move-result-object v0

    iget-object v2, p0, Lcom/miui/internal/app/ActionBarImpl;->Nl:Lcom/miui/internal/app/ActionBarImpl$TabImpl;

    invoke-interface {v0, v2, v1}, Landroid/app/ActionBar$TabListener;->onTabSelected(Landroid/app/ActionBar$Tab;Landroid/app/FragmentTransaction;)V

    goto :goto_1
.end method

.method public setBackgroundDrawable(Landroid/graphics/drawable/Drawable;)V
    .locals 1

    .prologue
    .line 433
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nd:Lcom/miui/internal/widget/ActionBarContainer;

    invoke-virtual {v0, p1}, Lcom/miui/internal/widget/ActionBarContainer;->setPrimaryBackground(Landroid/graphics/drawable/Drawable;)V

    .line 434
    return-void
.end method

.method public setCustomView(I)V
    .locals 3

    .prologue
    .line 443
    invoke-virtual {p0}, Lcom/miui/internal/app/ActionBarImpl;->getThemedContext()Landroid/content/Context;

    move-result-object v0

    invoke-static {v0}, Landroid/view/LayoutInflater;->from(Landroid/content/Context;)Landroid/view/LayoutInflater;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/internal/app/ActionBarImpl;->Ne:Lcom/miui/internal/widget/ActionBarView;

    const/4 v2, 0x0

    invoke-virtual {v0, p1, v1, v2}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;Z)Landroid/view/View;

    move-result-object v0

    invoke-virtual {p0, v0}, Lcom/miui/internal/app/ActionBarImpl;->setCustomView(Landroid/view/View;)V

    .line 445
    return-void
.end method

.method public setCustomView(Landroid/view/View;)V
    .locals 1

    .prologue
    .line 303
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Ne:Lcom/miui/internal/widget/ActionBarView;

    invoke-virtual {v0, p1}, Lcom/miui/internal/widget/ActionBarView;->setCustomNavigationView(Landroid/view/View;)V

    .line 304
    return-void
.end method

.method public setCustomView(Landroid/view/View;Landroid/app/ActionBar$LayoutParams;)V
    .locals 1

    .prologue
    .line 308
    invoke-virtual {p1, p2}, Landroid/view/View;->setLayoutParams(Landroid/view/ViewGroup$LayoutParams;)V

    .line 309
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Ne:Lcom/miui/internal/widget/ActionBarView;

    invoke-virtual {v0, p1}, Lcom/miui/internal/widget/ActionBarView;->setCustomNavigationView(Landroid/view/View;)V

    .line 310
    return-void
.end method

.method public setDisplayHomeAsUpEnabled(Z)V
    .locals 2

    .prologue
    const/4 v1, 0x4

    .line 410
    if-eqz p1, :cond_0

    move v0, v1

    :goto_0
    invoke-virtual {p0, v0, v1}, Lcom/miui/internal/app/ActionBarImpl;->setDisplayOptions(II)V

    .line 412
    return-void

    .line 410
    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public setDisplayOptions(I)V
    .locals 1

    .prologue
    .line 504
    and-int/lit8 v0, p1, 0x4

    if-eqz v0, :cond_0

    .line 505
    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nn:Z

    .line 507
    :cond_0
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Ne:Lcom/miui/internal/widget/ActionBarView;

    invoke-virtual {v0, p1}, Lcom/miui/internal/widget/ActionBarView;->setDisplayOptions(I)V

    .line 508
    return-void
.end method

.method public setDisplayOptions(II)V
    .locals 4

    .prologue
    .line 391
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Ne:Lcom/miui/internal/widget/ActionBarView;

    invoke-virtual {v0}, Lcom/miui/internal/widget/ActionBarView;->getDisplayOptions()I

    move-result v0

    .line 392
    and-int/lit8 v1, p2, 0x4

    if-eqz v1, :cond_0

    .line 393
    const/4 v1, 0x1

    iput-boolean v1, p0, Lcom/miui/internal/app/ActionBarImpl;->Nn:Z

    .line 395
    :cond_0
    iget-object v1, p0, Lcom/miui/internal/app/ActionBarImpl;->Ne:Lcom/miui/internal/widget/ActionBarView;

    and-int v2, p1, p2

    xor-int/lit8 v3, p2, -0x1

    and-int/2addr v0, v3

    or-int/2addr v0, v2

    invoke-virtual {v1, v0}, Lcom/miui/internal/widget/ActionBarView;->setDisplayOptions(I)V

    .line 396
    return-void
.end method

.method public setDisplayShowCustomEnabled(Z)V
    .locals 2

    .prologue
    const/16 v1, 0x10

    .line 422
    if-eqz p1, :cond_0

    move v0, v1

    :goto_0
    invoke-virtual {p0, v0, v1}, Lcom/miui/internal/app/ActionBarImpl;->setDisplayOptions(II)V

    .line 424
    return-void

    .line 422
    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public setDisplayShowHomeEnabled(Z)V
    .locals 2

    .prologue
    const/4 v1, 0x2

    .line 405
    if-eqz p1, :cond_0

    move v0, v1

    :goto_0
    invoke-virtual {p0, v0, v1}, Lcom/miui/internal/app/ActionBarImpl;->setDisplayOptions(II)V

    .line 406
    return-void

    .line 405
    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public setDisplayShowTitleEnabled(Z)V
    .locals 2

    .prologue
    const/16 v1, 0x8

    .line 416
    if-eqz p1, :cond_0

    move v0, v1

    :goto_0
    invoke-virtual {p0, v0, v1}, Lcom/miui/internal/app/ActionBarImpl;->setDisplayOptions(II)V

    .line 418
    return-void

    .line 416
    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public setDisplayUseLogoEnabled(Z)V
    .locals 2

    .prologue
    const/4 v1, 0x1

    .line 400
    if-eqz p1, :cond_0

    move v0, v1

    :goto_0
    invoke-virtual {p0, v0, v1}, Lcom/miui/internal/app/ActionBarImpl;->setDisplayOptions(II)V

    .line 401
    return-void

    .line 400
    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public setFragmentActionMenuAt(IZ)V
    .locals 1

    .prologue
    .line 966
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Ni:Lcom/miui/internal/app/ActionBarViewPagerController;

    invoke-virtual {v0, p1, p2}, Lcom/miui/internal/app/ActionBarViewPagerController;->setFragmentActionMenuAt(IZ)V

    .line 967
    return-void
.end method

.method public setFragmentViewPagerMode(Landroid/content/Context;Landroid/app/FragmentManager;)V
    .locals 1

    .prologue
    .line 880
    const/4 v0, 0x1

    invoke-virtual {p0, p1, p2, v0}, Lcom/miui/internal/app/ActionBarImpl;->setFragmentViewPagerMode(Landroid/content/Context;Landroid/app/FragmentManager;Z)V

    .line 881
    return-void
.end method

.method public setFragmentViewPagerMode(Landroid/content/Context;Landroid/app/FragmentManager;Z)V
    .locals 2

    .prologue
    .line 886
    invoke-virtual {p0}, Lcom/miui/internal/app/ActionBarImpl;->isFragmentViewPagerMode()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 899
    :goto_0
    return-void

    .line 890
    :cond_0
    invoke-virtual {p0}, Lcom/miui/internal/app/ActionBarImpl;->removeAllTabs()V

    .line 891
    const/4 v0, 0x2

    invoke-virtual {p0, v0}, Lcom/miui/internal/app/ActionBarImpl;->setNavigationMode(I)V

    .line 892
    new-instance v0, Lcom/miui/internal/app/ActionBarViewPagerController;

    invoke-direct {v0, p0, p2, p3}, Lcom/miui/internal/app/ActionBarViewPagerController;-><init>(Lcom/miui/internal/app/ActionBarImpl;Landroid/app/FragmentManager;Z)V

    iput-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Ni:Lcom/miui/internal/app/ActionBarViewPagerController;

    .line 894
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->hO:Lcom/miui/internal/widget/ScrollingTabContainerView;

    invoke-virtual {p0}, Lcom/miui/internal/app/ActionBarImpl;->isFragmentViewPagerMode()Z

    move-result v1

    invoke-virtual {v0, v1}, Lcom/miui/internal/widget/ScrollingTabContainerView;->setFragmentViewPagerMode(Z)V

    .line 895
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->hO:Lcom/miui/internal/widget/ScrollingTabContainerView;

    invoke-virtual {p0, v0}, Lcom/miui/internal/app/ActionBarImpl;->addOnFragmentViewPagerChangeListener(Lmiui/app/ActionBar$FragmentViewPagerChangeListener;)V

    .line 896
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->mSplitView:Lcom/miui/internal/widget/ActionBarContainer;

    invoke-virtual {p0, v0}, Lcom/miui/internal/app/ActionBarImpl;->addOnFragmentViewPagerChangeListener(Lmiui/app/ActionBar$FragmentViewPagerChangeListener;)V

    .line 898
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->mSplitView:Lcom/miui/internal/widget/ActionBarContainer;

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Lcom/miui/internal/widget/ActionBarContainer;->setFragmentViewPagerMode(Z)V

    goto :goto_0
.end method

.method public setHomeButtonEnabled(Z)V
    .locals 1

    .prologue
    .line 428
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Ne:Lcom/miui/internal/widget/ActionBarView;

    invoke-virtual {v0, p1}, Lcom/miui/internal/widget/ActionBarView;->setHomeButtonEnabled(Z)V

    .line 429
    return-void
.end method

.method public setIcon(I)V
    .locals 1

    .prologue
    .line 314
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Ne:Lcom/miui/internal/widget/ActionBarView;

    invoke-virtual {v0, p1}, Lcom/miui/internal/widget/ActionBarView;->setIcon(I)V

    .line 315
    return-void
.end method

.method public setIcon(Landroid/graphics/drawable/Drawable;)V
    .locals 1

    .prologue
    .line 319
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Ne:Lcom/miui/internal/widget/ActionBarView;

    invoke-virtual {v0, p1}, Lcom/miui/internal/widget/ActionBarView;->setIcon(Landroid/graphics/drawable/Drawable;)V

    .line 320
    return-void
.end method

.method public setListNavigationCallbacks(Landroid/widget/SpinnerAdapter;Landroid/app/ActionBar$OnNavigationListener;)V
    .locals 1

    .prologue
    .line 335
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Ne:Lcom/miui/internal/widget/ActionBarView;

    invoke-virtual {v0, p1}, Lcom/miui/internal/widget/ActionBarView;->setDropdownAdapter(Landroid/widget/SpinnerAdapter;)V

    .line 336
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Ne:Lcom/miui/internal/widget/ActionBarView;

    invoke-virtual {v0, p2}, Lcom/miui/internal/widget/ActionBarView;->setCallback(Landroid/app/ActionBar$OnNavigationListener;)V

    .line 337
    return-void
.end method

.method public setLogo(I)V
    .locals 1

    .prologue
    .line 324
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Ne:Lcom/miui/internal/widget/ActionBarView;

    invoke-virtual {v0, p1}, Lcom/miui/internal/widget/ActionBarView;->setLogo(I)V

    .line 325
    return-void
.end method

.method public setLogo(Landroid/graphics/drawable/Drawable;)V
    .locals 1

    .prologue
    .line 329
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Ne:Lcom/miui/internal/widget/ActionBarView;

    invoke-virtual {v0, p1}, Lcom/miui/internal/widget/ActionBarView;->setLogo(Landroid/graphics/drawable/Drawable;)V

    .line 330
    return-void
.end method

.method public setNavigationMode(I)V
    .locals 4

    .prologue
    const/4 v0, 0x0

    const/4 v3, -0x1

    .line 474
    iget-object v1, p0, Lcom/miui/internal/app/ActionBarImpl;->Ne:Lcom/miui/internal/widget/ActionBarView;

    invoke-virtual {v1}, Lcom/miui/internal/widget/ActionBarView;->getNavigationMode()I

    move-result v1

    .line 475
    packed-switch v1, :pswitch_data_0

    .line 482
    :goto_0
    iget-object v1, p0, Lcom/miui/internal/app/ActionBarImpl;->Ne:Lcom/miui/internal/widget/ActionBarView;

    invoke-virtual {v1, p1}, Lcom/miui/internal/widget/ActionBarView;->setNavigationMode(I)V

    .line 483
    packed-switch p1, :pswitch_data_1

    .line 493
    :cond_0
    :goto_1
    iget-object v1, p0, Lcom/miui/internal/app/ActionBarImpl;->Ne:Lcom/miui/internal/widget/ActionBarView;

    const/4 v2, 0x2

    if-ne p1, v2, :cond_1

    iget-boolean v2, p0, Lcom/miui/internal/app/ActionBarImpl;->Nq:Z

    if-nez v2, :cond_1

    const/4 v0, 0x1

    :cond_1
    invoke-virtual {v1, v0}, Lcom/miui/internal/widget/ActionBarView;->setCollapsable(Z)V

    .line 495
    return-void

    .line 477
    :pswitch_0
    invoke-virtual {p0}, Lcom/miui/internal/app/ActionBarImpl;->getSelectedNavigationIndex()I

    move-result v1

    iput v1, p0, Lcom/miui/internal/app/ActionBarImpl;->Nm:I

    .line 478
    const/4 v1, 0x0

    invoke-virtual {p0, v1}, Lcom/miui/internal/app/ActionBarImpl;->selectTab(Landroid/app/ActionBar$Tab;)V

    .line 479
    iget-object v1, p0, Lcom/miui/internal/app/ActionBarImpl;->hO:Lcom/miui/internal/widget/ScrollingTabContainerView;

    const/16 v2, 0x8

    invoke-virtual {v1, v2}, Lcom/miui/internal/widget/ScrollingTabContainerView;->setVisibility(I)V

    goto :goto_0

    .line 485
    :pswitch_1
    invoke-direct {p0}, Lcom/miui/internal/app/ActionBarImpl;->eo()V

    .line 486
    iget-object v1, p0, Lcom/miui/internal/app/ActionBarImpl;->hO:Lcom/miui/internal/widget/ScrollingTabContainerView;

    invoke-virtual {v1, v0}, Lcom/miui/internal/widget/ScrollingTabContainerView;->setVisibility(I)V

    .line 487
    iget v1, p0, Lcom/miui/internal/app/ActionBarImpl;->Nm:I

    if-eq v1, v3, :cond_0

    .line 488
    iget v1, p0, Lcom/miui/internal/app/ActionBarImpl;->Nm:I

    invoke-virtual {p0, v1}, Lcom/miui/internal/app/ActionBarImpl;->setSelectedNavigationItem(I)V

    .line 489
    iput v3, p0, Lcom/miui/internal/app/ActionBarImpl;->Nm:I

    goto :goto_1

    .line 475
    nop

    :pswitch_data_0
    .packed-switch 0x2
        :pswitch_0
    .end packed-switch

    .line 483
    :pswitch_data_1
    .packed-switch 0x2
        :pswitch_1
    .end packed-switch
.end method

.method public setProgress(I)V
    .locals 1

    .prologue
    .line 986
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Ne:Lcom/miui/internal/widget/ActionBarView;

    invoke-virtual {v0, p1}, Lcom/miui/internal/widget/ActionBarView;->setProgress(I)V

    .line 987
    return-void
.end method

.method public setProgressBarIndeterminate(Z)V
    .locals 1

    .prologue
    .line 981
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Ne:Lcom/miui/internal/widget/ActionBarView;

    invoke-virtual {v0, p1}, Lcom/miui/internal/widget/ActionBarView;->setProgressBarIndeterminate(Z)V

    .line 982
    return-void
.end method

.method public setProgressBarIndeterminateVisibility(Z)V
    .locals 1

    .prologue
    .line 976
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Ne:Lcom/miui/internal/widget/ActionBarView;

    invoke-virtual {v0, p1}, Lcom/miui/internal/widget/ActionBarView;->setProgressBarIndeterminateVisibility(Z)V

    .line 977
    return-void
.end method

.method public setProgressBarVisibility(Z)V
    .locals 1

    .prologue
    .line 971
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Ne:Lcom/miui/internal/widget/ActionBarView;

    invoke-virtual {v0, p1}, Lcom/miui/internal/widget/ActionBarView;->setProgressBarVisibility(Z)V

    .line 972
    return-void
.end method

.method public setSelectedNavigationItem(I)V
    .locals 2

    .prologue
    .line 341
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Ne:Lcom/miui/internal/widget/ActionBarView;

    invoke-virtual {v0}, Lcom/miui/internal/widget/ActionBarView;->getNavigationMode()I

    move-result v0

    packed-switch v0, :pswitch_data_0

    .line 349
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "setSelectedNavigationIndex not valid for current navigation mode"

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 343
    :pswitch_0
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nk:Ljava/util/ArrayList;

    invoke-virtual {v0, p1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/app/ActionBar$Tab;

    invoke-virtual {p0, v0}, Lcom/miui/internal/app/ActionBarImpl;->selectTab(Landroid/app/ActionBar$Tab;)V

    .line 352
    :goto_0
    return-void

    .line 346
    :pswitch_1
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Ne:Lcom/miui/internal/widget/ActionBarView;

    invoke-virtual {v0, p1}, Lcom/miui/internal/widget/ActionBarView;->setDropdownSelectedPosition(I)V

    goto :goto_0

    .line 341
    nop

    :pswitch_data_0
    .packed-switch 0x1
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method

.method public setShowHideAnimationEnabled(Z)V
    .locals 1

    .prologue
    .line 1125
    iput-boolean p1, p0, Lcom/miui/internal/app/ActionBarImpl;->Nw:Z

    .line 1126
    if-nez p1, :cond_0

    .line 1127
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nd:Lcom/miui/internal/widget/ActionBarContainer;

    invoke-virtual {v0}, Lcom/miui/internal/widget/ActionBarContainer;->clearAnimation()V

    .line 1128
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->mSplitView:Lcom/miui/internal/widget/ActionBarContainer;

    if-eqz v0, :cond_0

    .line 1129
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->mSplitView:Lcom/miui/internal/widget/ActionBarContainer;

    invoke-virtual {v0}, Lcom/miui/internal/widget/ActionBarContainer;->clearAnimation()V

    .line 1132
    :cond_0
    return-void
.end method

.method public setSubtitle(I)V
    .locals 1

    .prologue
    .line 464
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->mContext:Landroid/content/Context;

    invoke-virtual {v0, p1}, Landroid/content/Context;->getString(I)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, v0}, Lcom/miui/internal/app/ActionBarImpl;->setSubtitle(Ljava/lang/CharSequence;)V

    .line 465
    return-void
.end method

.method public setSubtitle(Ljava/lang/CharSequence;)V
    .locals 1

    .prologue
    .line 386
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Ne:Lcom/miui/internal/widget/ActionBarView;

    invoke-virtual {v0, p1}, Lcom/miui/internal/widget/ActionBarView;->setSubtitle(Ljava/lang/CharSequence;)V

    .line 387
    return-void
.end method

.method public setTabsMode(Z)V
    .locals 0

    .prologue
    .line 298
    invoke-direct {p0, p1}, Lcom/miui/internal/app/ActionBarImpl;->q(Z)V

    .line 299
    return-void
.end method

.method public setTitle(I)V
    .locals 1

    .prologue
    .line 454
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->mContext:Landroid/content/Context;

    invoke-virtual {v0, p1}, Landroid/content/Context;->getString(I)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, v0}, Lcom/miui/internal/app/ActionBarImpl;->setTitle(Ljava/lang/CharSequence;)V

    .line 455
    return-void
.end method

.method public setTitle(Ljava/lang/CharSequence;)V
    .locals 1

    .prologue
    .line 381
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Ne:Lcom/miui/internal/widget/ActionBarView;

    invoke-virtual {v0, p1}, Lcom/miui/internal/widget/ActionBarView;->setTitle(Ljava/lang/CharSequence;)V

    .line 382
    return-void
.end method

.method public setViewPagerOffscreenPageLimit(I)V
    .locals 1

    .prologue
    .line 996
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Ni:Lcom/miui/internal/app/ActionBarViewPagerController;

    invoke-virtual {v0, p1}, Lcom/miui/internal/app/ActionBarViewPagerController;->setViewPagerOffscreenPageLimit(I)V

    .line 997
    return-void
.end method

.method public show()V
    .locals 2

    .prologue
    const/4 v1, 0x0

    .line 714
    iget-boolean v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Ns:Z

    if-eqz v0, :cond_0

    .line 715
    iput-boolean v1, p0, Lcom/miui/internal/app/ActionBarImpl;->Ns:Z

    .line 716
    invoke-direct {p0, v1}, Lcom/miui/internal/app/ActionBarImpl;->s(Z)V

    .line 718
    :cond_0
    return-void
.end method

.method public showActionBarShadow(Z)V
    .locals 0

    .prologue
    .line 860
    return-void
.end method

.method public showSplitActionBar(ZZ)V
    .locals 1

    .prologue
    .line 864
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Ne:Lcom/miui/internal/widget/ActionBarView;

    invoke-virtual {v0}, Lcom/miui/internal/widget/ActionBarView;->isSplitActionBar()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 865
    if-eqz p1, :cond_1

    .line 866
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->mSplitView:Lcom/miui/internal/widget/ActionBarContainer;

    invoke-virtual {v0, p2}, Lcom/miui/internal/widget/ActionBarContainer;->show(Z)V

    .line 871
    :cond_0
    :goto_0
    return-void

    .line 868
    :cond_1
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->mSplitView:Lcom/miui/internal/widget/ActionBarContainer;

    invoke-virtual {v0, p2}, Lcom/miui/internal/widget/ActionBarContainer;->hide(Z)V

    goto :goto_0
.end method

.method public startActionMode(Landroid/view/ActionMode$Callback;)Landroid/view/ActionMode;
    .locals 4

    .prologue
    const/4 v3, 0x1

    .line 759
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->mActionMode:Landroid/view/ActionMode;

    if-eqz v0, :cond_0

    .line 760
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->mActionMode:Landroid/view/ActionMode;

    invoke-virtual {v0}, Landroid/view/ActionMode;->finish()V

    .line 763
    :cond_0
    invoke-direct {p0, p1}, Lcom/miui/internal/app/ActionBarImpl;->b(Landroid/view/ActionMode$Callback;)Landroid/view/ActionMode;

    move-result-object v1

    .line 765
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nj:Lcom/miui/internal/widget/ActionModeView;

    instance-of v0, v0, Lcom/miui/internal/widget/SearchActionModeView;

    if-eqz v0, :cond_1

    instance-of v0, v1, Lcom/miui/internal/view/SearchActionModeImpl;

    if-nez v0, :cond_2

    :cond_1
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nj:Lcom/miui/internal/widget/ActionModeView;

    instance-of v0, v0, Lcom/miui/internal/widget/ActionBarContextView;

    if-eqz v0, :cond_3

    instance-of v0, v1, Lcom/miui/internal/view/EditActionModeImpl;

    if-eqz v0, :cond_3

    .line 767
    :cond_2
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nj:Lcom/miui/internal/widget/ActionModeView;

    invoke-interface {v0}, Lcom/miui/internal/widget/ActionModeView;->closeMode()V

    .line 768
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nj:Lcom/miui/internal/widget/ActionModeView;

    invoke-interface {v0}, Lcom/miui/internal/widget/ActionModeView;->killMode()V

    .line 771
    :cond_3
    invoke-virtual {p0, p1}, Lcom/miui/internal/app/ActionBarImpl;->createActionModeView(Landroid/view/ActionMode$Callback;)Lcom/miui/internal/widget/ActionModeView;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nj:Lcom/miui/internal/widget/ActionModeView;

    .line 773
    instance-of v0, v1, Lcom/miui/internal/view/ActionModeImpl;

    if-eqz v0, :cond_6

    move-object v0, v1

    .line 774
    check-cast v0, Lcom/miui/internal/view/ActionModeImpl;

    .line 775
    iget-object v2, p0, Lcom/miui/internal/app/ActionBarImpl;->Nj:Lcom/miui/internal/widget/ActionModeView;

    invoke-virtual {v0, v2}, Lcom/miui/internal/view/ActionModeImpl;->setActionModeView(Lcom/miui/internal/widget/ActionModeView;)V

    .line 776
    iget-object v2, p0, Lcom/miui/internal/app/ActionBarImpl;->Ms:Lcom/miui/internal/view/ActionModeImpl$ActionModeCallback;

    invoke-virtual {v0, v2}, Lcom/miui/internal/view/ActionModeImpl;->setActionModeCallback(Lcom/miui/internal/view/ActionModeImpl$ActionModeCallback;)V

    .line 777
    invoke-virtual {v0}, Lcom/miui/internal/view/ActionModeImpl;->dispatchOnCreate()Z

    move-result v0

    if-eqz v0, :cond_6

    .line 778
    invoke-virtual {v1}, Landroid/view/ActionMode;->invalidate()V

    .line 779
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nj:Lcom/miui/internal/widget/ActionModeView;

    invoke-interface {v0, v1}, Lcom/miui/internal/widget/ActionModeView;->initForMode(Landroid/view/ActionMode;)V

    .line 780
    invoke-virtual {p0, v3}, Lcom/miui/internal/app/ActionBarImpl;->r(Z)V

    .line 781
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->mSplitView:Lcom/miui/internal/widget/ActionBarContainer;

    if-eqz v0, :cond_4

    iget v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Np:I

    if-ne v0, v3, :cond_4

    .line 783
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->mSplitView:Lcom/miui/internal/widget/ActionBarContainer;

    invoke-virtual {v0}, Lcom/miui/internal/widget/ActionBarContainer;->getVisibility()I

    move-result v0

    if-eqz v0, :cond_4

    .line 784
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->mSplitView:Lcom/miui/internal/widget/ActionBarContainer;

    const/4 v2, 0x0

    invoke-virtual {v0, v2}, Lcom/miui/internal/widget/ActionBarContainer;->setVisibility(I)V

    .line 787
    :cond_4
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nj:Lcom/miui/internal/widget/ActionModeView;

    instance-of v0, v0, Lcom/miui/internal/widget/ActionBarContextView;

    if-eqz v0, :cond_5

    .line 788
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl;->Nj:Lcom/miui/internal/widget/ActionModeView;

    check-cast v0, Lcom/miui/internal/widget/ActionBarContextView;

    const/16 v2, 0x20

    invoke-virtual {v0, v2}, Lcom/miui/internal/widget/ActionBarContextView;->sendAccessibilityEvent(I)V

    .line 792
    :cond_5
    iput-object v1, p0, Lcom/miui/internal/app/ActionBarImpl;->mActionMode:Landroid/view/ActionMode;

    .line 796
    :goto_0
    return-object v1

    :cond_6
    const/4 v1, 0x0

    goto :goto_0
.end method
