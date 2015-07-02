.class public Lcom/miui/internal/app/ActivityDelegate;
.super Lcom/miui/internal/app/ActionBarDelegateImpl;
.source "SourceFile"

# interfaces
.implements Lcom/miui/internal/view/menu/MenuBuilder$Callback;
.implements Lcom/miui/internal/view/menu/MenuPresenter$Callback;


# static fields
.field private static final kd:Ljava/lang/String; = "miui:ActionBar"

.field private static final ke:Lmiui/reflect/Method;

.field private static final kf:Lmiui/reflect/Method;

.field private static final kg:Lmiui/reflect/Method;


# instance fields
.field private final kh:Ljava/lang/Class;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/lang/Class",
            "<+",
            "Landroid/app/Activity;",
            ">;"
        }
    .end annotation
.end field

.field private ki:Lcom/miui/internal/widget/ActionBarOverlayLayout;

.field private kj:Lcom/miui/internal/widget/ActionBarContainer;

.field private final kk:Ljava/lang/Runnable;


# direct methods
.method static constructor <clinit>()V
    .locals 3

    .prologue
    .line 48
    const-class v0, Landroid/app/Activity;

    const-string v1, "onCreatePanelMenu"

    const-string v2, "(ILandroid/view/Menu;)Z"

    invoke-static {v0, v1, v2}, Lmiui/reflect/Method;->of(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lmiui/reflect/Method;

    move-result-object v0

    sput-object v0, Lcom/miui/internal/app/ActivityDelegate;->ke:Lmiui/reflect/Method;

    .line 51
    const-class v0, Landroid/app/Activity;

    const-string v1, "onPreparePanel"

    const-string v2, "(ILandroid/view/View;Landroid/view/Menu;)Z"

    invoke-static {v0, v1, v2}, Lmiui/reflect/Method;->of(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lmiui/reflect/Method;

    move-result-object v0

    sput-object v0, Lcom/miui/internal/app/ActivityDelegate;->kf:Lmiui/reflect/Method;

    .line 54
    const-class v0, Landroid/app/Activity;

    const-string v1, "onMenuItemSelected"

    const-string v2, "(ILandroid/view/MenuItem;)Z"

    invoke-static {v0, v1, v2}, Lmiui/reflect/Method;->of(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lmiui/reflect/Method;

    move-result-object v0

    sput-object v0, Lcom/miui/internal/app/ActivityDelegate;->kg:Lmiui/reflect/Method;

    return-void
.end method

.method public constructor <init>(Landroid/app/Activity;Ljava/lang/Class;)V
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Landroid/app/Activity;",
            "Ljava/lang/Class",
            "<+",
            "Landroid/app/Activity;",
            ">;)V"
        }
    .end annotation

    .prologue
    .line 78
    invoke-direct {p0, p1}, Lcom/miui/internal/app/ActionBarDelegateImpl;-><init>(Landroid/app/Activity;)V

    .line 63
    new-instance v0, Lcom/miui/internal/app/k;

    invoke-direct {v0, p0}, Lcom/miui/internal/app/k;-><init>(Lcom/miui/internal/app/ActivityDelegate;)V

    iput-object v0, p0, Lcom/miui/internal/app/ActivityDelegate;->kk:Ljava/lang/Runnable;

    .line 79
    iput-object p2, p0, Lcom/miui/internal/app/ActivityDelegate;->kh:Ljava/lang/Class;

    .line 80
    return-void
.end method

.method private a(ILandroid/view/Menu;)Z
    .locals 6

    .prologue
    .line 388
    sget-object v0, Lcom/miui/internal/app/ActivityDelegate;->ke:Lmiui/reflect/Method;

    iget-object v1, p0, Lcom/miui/internal/app/ActivityDelegate;->kh:Ljava/lang/Class;

    invoke-virtual {p0}, Lcom/miui/internal/app/ActivityDelegate;->getActivity()Landroid/app/Activity;

    move-result-object v2

    const/4 v3, 0x2

    new-array v3, v3, [Ljava/lang/Object;

    const/4 v4, 0x0

    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v5

    aput-object v5, v3, v4

    const/4 v4, 0x1

    aput-object p2, v3, v4

    invoke-virtual {v0, v1, v2, v3}, Lmiui/reflect/Method;->invokeBoolean(Ljava/lang/Class;Ljava/lang/Object;[Ljava/lang/Object;)Z

    move-result v0

    return v0
.end method

.method private a(ILandroid/view/MenuItem;)Z
    .locals 6

    .prologue
    .line 398
    sget-object v0, Lcom/miui/internal/app/ActivityDelegate;->kg:Lmiui/reflect/Method;

    iget-object v1, p0, Lcom/miui/internal/app/ActivityDelegate;->kh:Ljava/lang/Class;

    invoke-virtual {p0}, Lcom/miui/internal/app/ActivityDelegate;->getActivity()Landroid/app/Activity;

    move-result-object v2

    const/4 v3, 0x2

    new-array v3, v3, [Ljava/lang/Object;

    const/4 v4, 0x0

    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v5

    aput-object v5, v3, v4

    const/4 v4, 0x1

    aput-object p2, v3, v4

    invoke-virtual {v0, v1, v2, v3}, Lmiui/reflect/Method;->invokeBoolean(Ljava/lang/Class;Ljava/lang/Object;[Ljava/lang/Object;)Z

    move-result v0

    return v0
.end method

.method private a(ILandroid/view/View;Landroid/view/Menu;)Z
    .locals 6

    .prologue
    .line 393
    sget-object v0, Lcom/miui/internal/app/ActivityDelegate;->kf:Lmiui/reflect/Method;

    iget-object v1, p0, Lcom/miui/internal/app/ActivityDelegate;->kh:Ljava/lang/Class;

    invoke-virtual {p0}, Lcom/miui/internal/app/ActivityDelegate;->getActivity()Landroid/app/Activity;

    move-result-object v2

    const/4 v3, 0x3

    new-array v3, v3, [Ljava/lang/Object;

    const/4 v4, 0x0

    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v5

    aput-object v5, v3, v4

    const/4 v4, 0x1

    aput-object p2, v3, v4

    const/4 v4, 0x2

    aput-object p3, v3, v4

    invoke-virtual {v0, v1, v2, v3}, Lmiui/reflect/Method;->invokeBoolean(Ljava/lang/Class;Ljava/lang/Object;[Ljava/lang/Object;)Z

    move-result v0

    return v0
.end method

.method static synthetic a(Lcom/miui/internal/app/ActivityDelegate;ILandroid/view/Menu;)Z
    .locals 1

    .prologue
    .line 44
    invoke-direct {p0, p1, p2}, Lcom/miui/internal/app/ActivityDelegate;->a(ILandroid/view/Menu;)Z

    move-result v0

    return v0
.end method

.method static synthetic a(Lcom/miui/internal/app/ActivityDelegate;ILandroid/view/View;Landroid/view/Menu;)Z
    .locals 1

    .prologue
    .line 44
    invoke-direct {p0, p1, p2, p3}, Lcom/miui/internal/app/ActivityDelegate;->a(ILandroid/view/View;Landroid/view/Menu;)Z

    move-result v0

    return v0
.end method

.method public static getDecorViewLayoutRes(Landroid/view/Window;)I
    .locals 4

    .prologue
    const/4 v3, 0x0

    .line 88
    invoke-virtual {p0}, Landroid/view/Window;->getContext()Landroid/content/Context;

    move-result-object v1

    .line 89
    sget v0, Lcom/miui/internal/R$attr;->windowActionBar:I

    invoke-static {v1, v0, v3}, Lmiui/util/AttributeResolver;->resolveBoolean(Landroid/content/Context;IZ)Z

    move-result v0

    if-eqz v0, :cond_2

    .line 91
    sget v0, Lcom/miui/internal/R$attr;->windowActionBarMovable:I

    invoke-static {v1, v0, v3}, Lmiui/util/AttributeResolver;->resolveBoolean(Landroid/content/Context;IZ)Z

    move-result v0

    if-eqz v0, :cond_1

    .line 93
    sget v0, Lcom/miui/internal/R$layout;->screen_action_bar_movable:I

    .line 100
    :goto_0
    invoke-virtual {p0}, Landroid/view/Window;->isFloating()Z

    move-result v2

    if-nez v2, :cond_0

    invoke-virtual {p0}, Landroid/view/Window;->getCallback()Landroid/view/Window$Callback;

    move-result-object v2

    instance-of v2, v2, Landroid/app/Dialog;

    if-eqz v2, :cond_0

    .line 101
    sget v2, Lcom/miui/internal/R$attr;->windowTranslucentStatus:I

    invoke-static {v1, v2, v3}, Lmiui/util/AttributeResolver;->resolveInt(Landroid/content/Context;II)I

    move-result v1

    .line 103
    invoke-static {}, Lcom/miui/internal/variable/Android_View_Window_class$Factory;->getInstance()Lcom/miui/internal/variable/Android_View_Window_class$Factory;

    move-result-object v2

    invoke-virtual {v2}, Lcom/miui/internal/variable/Android_View_Window_class$Factory;->get()Lcom/miui/internal/variable/Android_View_Window_class;

    move-result-object v2

    .line 104
    invoke-virtual {v2, p0, v1}, Lcom/miui/internal/variable/Android_View_Window_class;->setTranslucentStatus(Landroid/view/Window;I)Z

    .line 106
    :cond_0
    return v0

    .line 95
    :cond_1
    sget v0, Lcom/miui/internal/R$layout;->screen_action_bar:I

    goto :goto_0

    .line 98
    :cond_2
    sget v0, Lcom/miui/internal/R$layout;->screen_simple:I

    goto :goto_0
.end method


# virtual methods
.method public createActionBar()Lmiui/app/ActionBar;
    .locals 2

    .prologue
    .line 116
    new-instance v0, Lcom/miui/internal/app/ActionBarImpl;

    iget-object v1, p0, Lcom/miui/internal/app/ActivityDelegate;->dK:Landroid/app/Activity;

    invoke-direct {v0, v1}, Lcom/miui/internal/app/ActionBarImpl;-><init>(Landroid/app/Activity;)V

    return-object v0
.end method

.method protected installSubDecor()V
    .locals 8

    .prologue
    const/4 v7, 0x1

    const/4 v6, 0x0

    .line 138
    iget-boolean v0, p0, Lcom/miui/internal/app/ActivityDelegate;->mSubDecorInstalled:Z

    if-eqz v0, :cond_0

    .line 232
    :goto_0
    return-void

    .line 142
    :cond_0
    iput-boolean v7, p0, Lcom/miui/internal/app/ActivityDelegate;->mSubDecorInstalled:Z

    .line 144
    iget-object v0, p0, Lcom/miui/internal/app/ActivityDelegate;->dK:Landroid/app/Activity;

    sget-object v1, Lmiui/R$styleable;->Window:[I

    invoke-virtual {v0, v1}, Landroid/app/Activity;->obtainStyledAttributes([I)Landroid/content/res/TypedArray;

    move-result-object v3

    .line 146
    const/16 v0, 0xb

    invoke-virtual {v3, v0, v6}, Landroid/content/res/TypedArray;->getInt(II)I

    move-result v0

    if-ne v0, v7, :cond_1

    .line 149
    iget-object v0, p0, Lcom/miui/internal/app/ActivityDelegate;->dK:Landroid/app/Activity;

    invoke-virtual {v0}, Landroid/app/Activity;->getWindow()Landroid/view/Window;

    move-result-object v0

    const/16 v1, 0x50

    invoke-virtual {v0, v1}, Landroid/view/Window;->setGravity(I)V

    .line 152
    :cond_1
    invoke-virtual {v3, v7}, Landroid/content/res/TypedArray;->hasValue(I)Z

    move-result v0

    if-nez v0, :cond_2

    .line 153
    invoke-virtual {v3}, Landroid/content/res/TypedArray;->recycle()V

    .line 154
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "You need to use a miui theme (or descendant) with this activity."

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 157
    :cond_2
    invoke-virtual {v3, v7, v6}, Landroid/content/res/TypedArray;->getBoolean(IZ)Z

    move-result v0

    if-eqz v0, :cond_3

    .line 158
    const/16 v0, 0x8

    invoke-virtual {p0, v0}, Lcom/miui/internal/app/ActivityDelegate;->requestWindowFeature(I)Z

    .line 160
    :cond_3
    const/4 v0, 0x2

    invoke-virtual {v3, v0, v6}, Landroid/content/res/TypedArray;->getBoolean(IZ)Z

    move-result v0

    if-eqz v0, :cond_4

    .line 161
    const/16 v0, 0x9

    invoke-virtual {p0, v0}, Lcom/miui/internal/app/ActivityDelegate;->requestWindowFeature(I)Z

    .line 164
    :cond_4
    const/16 v0, 0xa

    invoke-virtual {v3, v0, v6}, Landroid/content/res/TypedArray;->getInt(II)I

    move-result v0

    invoke-virtual {p0, v0}, Lcom/miui/internal/app/ActivityDelegate;->setTranslucentStatus(I)V

    .line 168
    iget-object v0, p0, Lcom/miui/internal/app/ActivityDelegate;->dK:Landroid/app/Activity;

    invoke-virtual {v0}, Landroid/app/Activity;->getWindow()Landroid/view/Window;

    move-result-object v0

    invoke-virtual {v0}, Landroid/view/Window;->getDecorView()Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/view/ViewGroup;

    .line 170
    invoke-virtual {v0, v6}, Landroid/view/ViewGroup;->getChildAt(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/widget/ActionBarOverlayLayout;

    iput-object v0, p0, Lcom/miui/internal/app/ActivityDelegate;->ki:Lcom/miui/internal/widget/ActionBarOverlayLayout;

    .line 171
    iget-object v0, p0, Lcom/miui/internal/app/ActivityDelegate;->ki:Lcom/miui/internal/widget/ActionBarOverlayLayout;

    iget-object v1, p0, Lcom/miui/internal/app/ActivityDelegate;->dK:Landroid/app/Activity;

    invoke-virtual {v0, v1}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->setCallback(Landroid/view/Window$Callback;)V

    .line 172
    iget-object v0, p0, Lcom/miui/internal/app/ActivityDelegate;->ki:Lcom/miui/internal/widget/ActionBarOverlayLayout;

    invoke-virtual {v0, v7}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->setRootSubDecor(Z)V

    .line 173
    iget-object v0, p0, Lcom/miui/internal/app/ActivityDelegate;->ki:Lcom/miui/internal/widget/ActionBarOverlayLayout;

    invoke-virtual {p0}, Lcom/miui/internal/app/ActivityDelegate;->getTranslucentStatus()I

    move-result v1

    invoke-virtual {v0, v1}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->setTranslucentStatus(I)V

    .line 176
    iget-boolean v0, p0, Lcom/miui/internal/app/ActivityDelegate;->ja:Z

    if-eqz v0, :cond_9

    .line 177
    iget-object v0, p0, Lcom/miui/internal/app/ActivityDelegate;->ki:Lcom/miui/internal/widget/ActionBarOverlayLayout;

    sget v1, Lcom/miui/internal/R$id;->action_bar_container:I

    invoke-virtual {v0, v1}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/widget/ActionBarContainer;

    iput-object v0, p0, Lcom/miui/internal/app/ActivityDelegate;->kj:Lcom/miui/internal/widget/ActionBarContainer;

    .line 179
    iget-object v0, p0, Lcom/miui/internal/app/ActivityDelegate;->ki:Lcom/miui/internal/widget/ActionBarOverlayLayout;

    iget-boolean v1, p0, Lcom/miui/internal/app/ActivityDelegate;->jb:Z

    invoke-virtual {v0, v1}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->setOverlayMode(Z)V

    .line 180
    iget-object v0, p0, Lcom/miui/internal/app/ActivityDelegate;->ki:Lcom/miui/internal/widget/ActionBarOverlayLayout;

    sget v1, Lcom/miui/internal/R$id;->action_bar:I

    invoke-virtual {v0, v1}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/widget/ActionBarView;

    iput-object v0, p0, Lcom/miui/internal/app/ActivityDelegate;->mActionBarView:Lcom/miui/internal/widget/ActionBarView;

    .line 181
    iget-object v0, p0, Lcom/miui/internal/app/ActivityDelegate;->mActionBarView:Lcom/miui/internal/widget/ActionBarView;

    iget-object v1, p0, Lcom/miui/internal/app/ActivityDelegate;->dK:Landroid/app/Activity;

    invoke-virtual {v0, v1}, Lcom/miui/internal/widget/ActionBarView;->setWindowCallback(Landroid/view/Window$Callback;)V

    .line 184
    iget-boolean v0, p0, Lcom/miui/internal/app/ActivityDelegate;->mFeatureProgress:Z

    if-eqz v0, :cond_5

    .line 185
    iget-object v0, p0, Lcom/miui/internal/app/ActivityDelegate;->mActionBarView:Lcom/miui/internal/widget/ActionBarView;

    invoke-virtual {v0}, Lcom/miui/internal/widget/ActionBarView;->initProgress()V

    .line 187
    :cond_5
    iget-boolean v0, p0, Lcom/miui/internal/app/ActivityDelegate;->mFeatureIndeterminateProgress:Z

    if-eqz v0, :cond_6

    .line 188
    iget-object v0, p0, Lcom/miui/internal/app/ActivityDelegate;->mActionBarView:Lcom/miui/internal/widget/ActionBarView;

    invoke-virtual {v0}, Lcom/miui/internal/widget/ActionBarView;->initIndeterminateProgress()V

    .line 192
    :cond_6
    const/16 v0, 0x12

    invoke-virtual {v3, v0, v6}, Landroid/content/res/TypedArray;->getResourceId(II)I

    move-result v0

    iput v0, p0, Lcom/miui/internal/app/ActivityDelegate;->mImmersionLayoutResourceId:I

    .line 193
    invoke-virtual {p0}, Lcom/miui/internal/app/ActivityDelegate;->isImmersionMenuEnabled()Z

    move-result v0

    if-eqz v0, :cond_7

    .line 194
    iget-object v0, p0, Lcom/miui/internal/app/ActivityDelegate;->mActionBarView:Lcom/miui/internal/widget/ActionBarView;

    iget v1, p0, Lcom/miui/internal/app/ActivityDelegate;->mImmersionLayoutResourceId:I

    invoke-virtual {v0, v1, p0}, Lcom/miui/internal/widget/ActionBarView;->initImmersionMore(ILcom/miui/internal/app/ActionBarDelegateImpl;)V

    .line 198
    :cond_7
    const-string v0, "splitActionBarWhenNarrow"

    invoke-virtual {p0}, Lcom/miui/internal/app/ActivityDelegate;->getUiOptionsFromMetadata()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v4

    .line 202
    if-eqz v4, :cond_b

    .line 203
    iget-object v0, p0, Lcom/miui/internal/app/ActivityDelegate;->dK:Landroid/app/Activity;

    invoke-virtual {v0}, Landroid/app/Activity;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    sget v1, Lcom/miui/internal/R$bool;->abc_split_action_bar_is_narrow:I

    invoke-virtual {v0, v1}, Landroid/content/res/Resources;->getBoolean(I)Z

    move-result v0

    move v2, v0

    .line 209
    :goto_1
    iget-object v0, p0, Lcom/miui/internal/app/ActivityDelegate;->ki:Lcom/miui/internal/widget/ActionBarOverlayLayout;

    sget v1, Lcom/miui/internal/R$id;->split_action_bar:I

    invoke-virtual {v0, v1}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/widget/ActionBarContainer;

    .line 211
    if-eqz v0, :cond_8

    .line 212
    iget-object v1, p0, Lcom/miui/internal/app/ActivityDelegate;->mActionBarView:Lcom/miui/internal/widget/ActionBarView;

    invoke-virtual {v1, v0}, Lcom/miui/internal/widget/ActionBarView;->setSplitView(Lcom/miui/internal/widget/ActionBarContainer;)V

    .line 213
    iget-object v1, p0, Lcom/miui/internal/app/ActivityDelegate;->mActionBarView:Lcom/miui/internal/widget/ActionBarView;

    invoke-virtual {v1, v2}, Lcom/miui/internal/widget/ActionBarView;->setSplitActionBar(Z)V

    .line 214
    iget-object v1, p0, Lcom/miui/internal/app/ActivityDelegate;->mActionBarView:Lcom/miui/internal/widget/ActionBarView;

    invoke-virtual {v1, v4}, Lcom/miui/internal/widget/ActionBarView;->setSplitWhenNarrow(Z)V

    .line 216
    iget-object v1, p0, Lcom/miui/internal/app/ActivityDelegate;->ki:Lcom/miui/internal/widget/ActionBarOverlayLayout;

    sget v5, Lcom/miui/internal/R$id;->action_context_bar:I

    invoke-virtual {v1, v5}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->findViewById(I)Landroid/view/View;

    move-result-object v1

    check-cast v1, Lcom/miui/internal/widget/ActionBarContextView;

    .line 218
    invoke-virtual {v0, v1}, Lcom/miui/internal/widget/ActionBarContainer;->setActionBarContextView(Lcom/miui/internal/widget/ActionBarContextView;)V

    .line 219
    invoke-virtual {v1, v0}, Lcom/miui/internal/widget/ActionBarContextView;->setSplitView(Lcom/miui/internal/widget/ActionBarContainer;)V

    .line 220
    invoke-virtual {v1, v2}, Lcom/miui/internal/widget/ActionBarContextView;->setSplitActionBar(Z)V

    .line 221
    invoke-virtual {v1, v4}, Lcom/miui/internal/widget/ActionBarContextView;->setSplitWhenNarrow(Z)V

    .line 224
    :cond_8
    iget-object v0, p0, Lcom/miui/internal/app/ActivityDelegate;->dK:Landroid/app/Activity;

    invoke-virtual {v0}, Landroid/app/Activity;->getWindow()Landroid/view/Window;

    move-result-object v0

    invoke-virtual {v0}, Landroid/view/Window;->getDecorView()Landroid/view/View;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/internal/app/ActivityDelegate;->kk:Ljava/lang/Runnable;

    invoke-virtual {v0, v1}, Landroid/view/View;->post(Ljava/lang/Runnable;)Z

    .line 227
    :cond_9
    const/4 v0, 0x5

    invoke-virtual {v3, v0, v6}, Landroid/content/res/TypedArray;->getBoolean(IZ)Z

    move-result v0

    if-eqz v0, :cond_a

    .line 228
    invoke-virtual {p0, v7}, Lcom/miui/internal/app/ActivityDelegate;->setImmersionMenuEnabled(Z)V

    .line 231
    :cond_a
    invoke-virtual {v3}, Landroid/content/res/TypedArray;->recycle()V

    goto/16 :goto_0

    .line 206
    :cond_b
    invoke-virtual {v3, v6, v6}, Landroid/content/res/TypedArray;->getBoolean(IZ)Z

    move-result v0

    move v2, v0

    goto :goto_1
.end method

.method public invalidateOptionsMenu()V
    .locals 1

    .prologue
    .line 347
    iget-object v0, p0, Lcom/miui/internal/app/ActivityDelegate;->kk:Ljava/lang/Runnable;

    invoke-interface {v0}, Ljava/lang/Runnable;->run()V

    .line 348
    return-void
.end method

.method public onActionModeFinished(Landroid/view/ActionMode;)V
    .locals 1

    .prologue
    .line 370
    const/4 v0, 0x0

    iput-object v0, p0, Lcom/miui/internal/app/ActivityDelegate;->mActionMode:Landroid/view/ActionMode;

    .line 371
    return-void
.end method

.method public onActionModeStarted(Landroid/view/ActionMode;)V
    .locals 0

    .prologue
    .line 365
    iput-object p1, p0, Lcom/miui/internal/app/ActivityDelegate;->mActionMode:Landroid/view/ActionMode;

    .line 366
    return-void
.end method

.method public onBackPressed()Z
    .locals 2

    .prologue
    const/4 v0, 0x1

    .line 374
    iget-object v1, p0, Lcom/miui/internal/app/ActivityDelegate;->mActionMode:Landroid/view/ActionMode;

    if-eqz v1, :cond_0

    .line 375
    iget-object v1, p0, Lcom/miui/internal/app/ActivityDelegate;->mActionMode:Landroid/view/ActionMode;

    invoke-virtual {v1}, Landroid/view/ActionMode;->finish()V

    .line 384
    :goto_0
    return v0

    .line 379
    :cond_0
    iget-object v1, p0, Lcom/miui/internal/app/ActivityDelegate;->mActionBarView:Lcom/miui/internal/widget/ActionBarView;

    if-eqz v1, :cond_1

    iget-object v1, p0, Lcom/miui/internal/app/ActivityDelegate;->mActionBarView:Lcom/miui/internal/widget/ActionBarView;

    invoke-virtual {v1}, Lcom/miui/internal/widget/ActionBarView;->hasExpandedActionView()Z

    move-result v1

    if-eqz v1, :cond_1

    .line 380
    iget-object v1, p0, Lcom/miui/internal/app/ActivityDelegate;->mActionBarView:Lcom/miui/internal/widget/ActionBarView;

    invoke-virtual {v1}, Lcom/miui/internal/widget/ActionBarView;->collapseActionView()V

    goto :goto_0

    .line 384
    :cond_1
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public onCreate(Landroid/os/Bundle;)V
    .locals 0

    .prologue
    .line 111
    invoke-super {p0, p1}, Lcom/miui/internal/app/ActionBarDelegateImpl;->onCreate(Landroid/os/Bundle;)V

    .line 112
    invoke-virtual {p0}, Lcom/miui/internal/app/ActivityDelegate;->installSubDecor()V

    .line 113
    return-void
.end method

.method public onCreatePanelMenu(ILandroid/view/Menu;)Z
    .locals 1

    .prologue
    .line 281
    if-eqz p1, :cond_0

    invoke-direct {p0, p1, p2}, Lcom/miui/internal/app/ActivityDelegate;->a(ILandroid/view/Menu;)Z

    move-result v0

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public onCreatePanelView(I)Landroid/view/View;
    .locals 5

    .prologue
    const/4 v4, 0x0

    const/4 v3, 0x0

    .line 242
    if-nez p1, :cond_2

    invoke-virtual {p0}, Lcom/miui/internal/app/ActivityDelegate;->isImmersionMenuEnabled()Z

    move-result v0

    if-nez v0, :cond_2

    .line 243
    const/4 v1, 0x1

    .line 244
    iget-object v0, p0, Lcom/miui/internal/app/ActivityDelegate;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    .line 247
    iget-object v2, p0, Lcom/miui/internal/app/ActivityDelegate;->mActionMode:Landroid/view/ActionMode;

    if-nez v2, :cond_1

    .line 248
    if-nez v0, :cond_0

    .line 250
    invoke-virtual {p0}, Lcom/miui/internal/app/ActivityDelegate;->createMenu()Lcom/miui/internal/view/menu/MenuBuilder;

    move-result-object v0

    .line 251
    invoke-virtual {p0, v0}, Lcom/miui/internal/app/ActivityDelegate;->setMenu(Lcom/miui/internal/view/menu/MenuBuilder;)V

    .line 254
    invoke-virtual {v0}, Lcom/miui/internal/view/menu/MenuBuilder;->stopDispatchingItemsChanged()V

    .line 256
    invoke-direct {p0, v4, v0}, Lcom/miui/internal/app/ActivityDelegate;->a(ILandroid/view/Menu;)Z

    move-result v1

    .line 259
    :cond_0
    if-eqz v1, :cond_1

    .line 261
    invoke-virtual {v0}, Lcom/miui/internal/view/menu/MenuBuilder;->stopDispatchingItemsChanged()V

    .line 263
    invoke-direct {p0, v4, v3, v0}, Lcom/miui/internal/app/ActivityDelegate;->a(ILandroid/view/View;Landroid/view/Menu;)Z

    move-result v1

    .line 267
    :cond_1
    if-eqz v1, :cond_3

    .line 269
    invoke-virtual {v0}, Lcom/miui/internal/view/menu/MenuBuilder;->startDispatchingItemsChanged()V

    .line 276
    :cond_2
    :goto_0
    return-object v3

    .line 272
    :cond_3
    invoke-virtual {p0, v3}, Lcom/miui/internal/app/ActivityDelegate;->setMenu(Lcom/miui/internal/view/menu/MenuBuilder;)V

    goto :goto_0
.end method

.method public onMenuItemSelected(ILandroid/view/MenuItem;)Z
    .locals 2

    .prologue
    .line 320
    invoke-direct {p0, p1, p2}, Lcom/miui/internal/app/ActivityDelegate;->a(ILandroid/view/MenuItem;)Z

    move-result v0

    if-nez v0, :cond_0

    .line 321
    if-nez p1, :cond_0

    .line 322
    invoke-interface {p2}, Landroid/view/MenuItem;->getItemId()I

    move-result v0

    const v1, 0x102002c

    if-ne v0, v1, :cond_0

    invoke-virtual {p0}, Lcom/miui/internal/app/ActivityDelegate;->getActionBar()Lmiui/app/ActionBar;

    move-result-object v0

    if-eqz v0, :cond_0

    invoke-virtual {p0}, Lcom/miui/internal/app/ActivityDelegate;->getActionBar()Lmiui/app/ActionBar;

    move-result-object v0

    invoke-virtual {v0}, Lmiui/app/ActionBar;->getDisplayOptions()I

    move-result v0

    and-int/lit8 v0, v0, 0x4

    if-eqz v0, :cond_0

    .line 325
    iget-object v0, p0, Lcom/miui/internal/app/ActivityDelegate;->dK:Landroid/app/Activity;

    invoke-virtual {v0}, Landroid/app/Activity;->getParent()Landroid/app/Activity;

    move-result-object v0

    if-nez v0, :cond_1

    .line 326
    iget-object v0, p0, Lcom/miui/internal/app/ActivityDelegate;->dK:Landroid/app/Activity;

    invoke-virtual {v0}, Landroid/app/Activity;->onNavigateUp()Z

    move-result v0

    .line 331
    :goto_0
    if-nez v0, :cond_0

    .line 332
    iget-object v0, p0, Lcom/miui/internal/app/ActivityDelegate;->dK:Landroid/app/Activity;

    invoke-virtual {v0}, Landroid/app/Activity;->finish()V

    .line 337
    :cond_0
    const/4 v0, 0x1

    return v0

    .line 328
    :cond_1
    iget-object v0, p0, Lcom/miui/internal/app/ActivityDelegate;->dK:Landroid/app/Activity;

    invoke-virtual {v0}, Landroid/app/Activity;->getParent()Landroid/app/Activity;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/internal/app/ActivityDelegate;->dK:Landroid/app/Activity;

    invoke-virtual {v0, v1}, Landroid/app/Activity;->onNavigateUpFromChild(Landroid/app/Activity;)Z

    move-result v0

    goto :goto_0
.end method

.method public onMenuItemSelected(Lcom/miui/internal/view/menu/MenuBuilder;Landroid/view/MenuItem;)Z
    .locals 2

    .prologue
    .line 342
    iget-object v0, p0, Lcom/miui/internal/app/ActivityDelegate;->dK:Landroid/app/Activity;

    const/4 v1, 0x0

    invoke-virtual {v0, v1, p2}, Landroid/app/Activity;->onMenuItemSelected(ILandroid/view/MenuItem;)Z

    move-result v0

    return v0
.end method

.method public onPostResume()V
    .locals 2

    .prologue
    .line 131
    invoke-virtual {p0}, Lcom/miui/internal/app/ActivityDelegate;->getActionBar()Lmiui/app/ActionBar;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/app/ActionBarImpl;

    .line 132
    if-eqz v0, :cond_0

    .line 133
    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Lcom/miui/internal/app/ActionBarImpl;->setShowHideAnimationEnabled(Z)V

    .line 135
    :cond_0
    return-void
.end method

.method protected onPrepareImmersionMenu(Lcom/miui/internal/view/menu/MenuBuilder;)Z
    .locals 1

    .prologue
    .line 404
    iget-object v0, p0, Lcom/miui/internal/app/ActivityDelegate;->dK:Landroid/app/Activity;

    invoke-virtual {v0, p1}, Landroid/app/Activity;->onCreateOptionsMenu(Landroid/view/Menu;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 405
    iget-object v0, p0, Lcom/miui/internal/app/ActivityDelegate;->dK:Landroid/app/Activity;

    invoke-virtual {v0, p1}, Landroid/app/Activity;->onPrepareOptionsMenu(Landroid/view/Menu;)Z

    .line 406
    const/4 v0, 0x1

    .line 409
    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public onPreparePanel(ILandroid/view/View;Landroid/view/Menu;)Z
    .locals 1

    .prologue
    .line 286
    if-eqz p1, :cond_0

    invoke-direct {p0, p1, p2, p3}, Lcom/miui/internal/app/ActivityDelegate;->a(ILandroid/view/View;Landroid/view/Menu;)Z

    move-result v0

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public onRestoreInstanceState(Landroid/os/Bundle;)V
    .locals 2

    .prologue
    .line 298
    iget-object v0, p0, Lcom/miui/internal/app/ActivityDelegate;->kj:Lcom/miui/internal/widget/ActionBarContainer;

    if-eqz v0, :cond_0

    .line 299
    const-string v0, "miui:ActionBar"

    invoke-virtual {p1, v0}, Landroid/os/Bundle;->getSparseParcelableArray(Ljava/lang/String;)Landroid/util/SparseArray;

    move-result-object v0

    .line 300
    if-eqz v0, :cond_0

    .line 301
    iget-object v1, p0, Lcom/miui/internal/app/ActivityDelegate;->kj:Lcom/miui/internal/widget/ActionBarContainer;

    invoke-virtual {v1, v0}, Lcom/miui/internal/widget/ActionBarContainer;->restoreHierarchyState(Landroid/util/SparseArray;)V

    .line 304
    :cond_0
    return-void
.end method

.method public onSaveInstanceState(Landroid/os/Bundle;)V
    .locals 2

    .prologue
    .line 290
    iget-object v0, p0, Lcom/miui/internal/app/ActivityDelegate;->kj:Lcom/miui/internal/widget/ActionBarContainer;

    if-eqz v0, :cond_0

    .line 291
    new-instance v0, Landroid/util/SparseArray;

    invoke-direct {v0}, Landroid/util/SparseArray;-><init>()V

    .line 292
    iget-object v1, p0, Lcom/miui/internal/app/ActivityDelegate;->kj:Lcom/miui/internal/widget/ActionBarContainer;

    invoke-virtual {v1, v0}, Lcom/miui/internal/widget/ActionBarContainer;->saveHierarchyState(Landroid/util/SparseArray;)V

    .line 293
    const-string v1, "miui:ActionBar"

    invoke-virtual {p1, v1, v0}, Landroid/os/Bundle;->putSparseParcelableArray(Ljava/lang/String;Landroid/util/SparseArray;)V

    .line 295
    :cond_0
    return-void
.end method

.method public onStop()V
    .locals 2

    .prologue
    const/4 v1, 0x0

    .line 121
    invoke-virtual {p0, v1}, Lcom/miui/internal/app/ActivityDelegate;->dismissImmersionMenu(Z)V

    .line 123
    invoke-virtual {p0}, Lcom/miui/internal/app/ActivityDelegate;->getActionBar()Lmiui/app/ActionBar;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/app/ActionBarImpl;

    .line 124
    if-eqz v0, :cond_0

    .line 125
    invoke-virtual {v0, v1}, Lcom/miui/internal/app/ActionBarImpl;->setShowHideAnimationEnabled(Z)V

    .line 127
    :cond_0
    return-void
.end method

.method public onTitleChanged(Ljava/lang/CharSequence;)V
    .locals 1

    .prologue
    .line 235
    iget-object v0, p0, Lcom/miui/internal/app/ActivityDelegate;->mActionBarView:Lcom/miui/internal/widget/ActionBarView;

    if-eqz v0, :cond_0

    .line 236
    iget-object v0, p0, Lcom/miui/internal/app/ActivityDelegate;->mActionBarView:Lcom/miui/internal/widget/ActionBarView;

    invoke-virtual {v0, p1}, Lcom/miui/internal/widget/ActionBarView;->setWindowTitle(Ljava/lang/CharSequence;)V

    .line 238
    :cond_0
    return-void
.end method

.method public onWindowStartingActionMode(Landroid/view/ActionMode$Callback;)Landroid/view/ActionMode;
    .locals 1

    .prologue
    .line 357
    invoke-virtual {p0}, Lcom/miui/internal/app/ActivityDelegate;->getActionBar()Lmiui/app/ActionBar;

    move-result-object v0

    if-eqz v0, :cond_0

    .line 358
    invoke-virtual {p0}, Lcom/miui/internal/app/ActivityDelegate;->getActionBar()Lmiui/app/ActionBar;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/app/ActionBarImpl;

    invoke-virtual {v0, p1}, Lcom/miui/internal/app/ActionBarImpl;->startActionMode(Landroid/view/ActionMode$Callback;)Landroid/view/ActionMode;

    move-result-object v0

    .line 360
    :goto_0
    return-object v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public setOnStatusBarChangeListener(Lmiui/app/OnStatusBarChangeListener;)V
    .locals 1

    .prologue
    .line 413
    iget-object v0, p0, Lcom/miui/internal/app/ActivityDelegate;->ki:Lcom/miui/internal/widget/ActionBarOverlayLayout;

    if-eqz v0, :cond_0

    .line 414
    iget-object v0, p0, Lcom/miui/internal/app/ActivityDelegate;->ki:Lcom/miui/internal/widget/ActionBarOverlayLayout;

    invoke-virtual {v0, p1}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->setOnStatusBarChangeListener(Lmiui/app/OnStatusBarChangeListener;)V

    .line 416
    :cond_0
    return-void
.end method

.method public startActionMode(Landroid/view/ActionMode$Callback;)Landroid/view/ActionMode;
    .locals 1

    .prologue
    .line 352
    iget-object v0, p0, Lcom/miui/internal/app/ActivityDelegate;->ki:Lcom/miui/internal/widget/ActionBarOverlayLayout;

    invoke-virtual {v0, p1}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->startActionMode(Landroid/view/ActionMode$Callback;)Landroid/view/ActionMode;

    move-result-object v0

    return-object v0
.end method
