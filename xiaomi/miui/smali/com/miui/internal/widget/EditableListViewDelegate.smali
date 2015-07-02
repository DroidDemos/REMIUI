.class public Lcom/miui/internal/widget/EditableListViewDelegate;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/internal/widget/EditableListViewDelegate$a;,
        Lcom/miui/internal/widget/EditableListViewDelegate$b;,
        Lcom/miui/internal/widget/EditableListViewDelegate$UpdateListener;
    }
.end annotation


# static fields
.field private static final xc:I = 0x7fffffff

.field private static final xd:Lmiui/reflect/Method;

.field private static final xe:Lmiui/reflect/Method;

.field private static final xf:Lmiui/reflect/Method;

.field private static final xg:Lmiui/reflect/Method;

.field private static final xh:Lmiui/reflect/Method;

.field private static final xi:Lmiui/reflect/Method;


# instance fields
.field private mActionMode:Landroid/view/ActionMode;

.field private xj:Landroid/widget/AbsListView;

.field private xk:Ljava/lang/Class;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/lang/Class",
            "<+",
            "Landroid/widget/AbsListView;",
            ">;"
        }
    .end annotation
.end field

.field private xl:Lcom/miui/internal/widget/EditableListViewDelegate$a;

.field private xm:Lcom/miui/internal/widget/EditableListViewDelegate$b;

.field private xn:Z

.field private xo:Z

.field private xp:I

.field private xq:I

.field private xr:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List",
            "<",
            "Landroid/widget/CheckBox;",
            ">;"
        }
    .end annotation
.end field

.field private xs:Lcom/miui/internal/widget/EditableListViewDelegate$UpdateListener;

.field private xt:Landroid/widget/AbsListView$RecyclerListener;

.field private xu:Lmiui/widget/EditableListView$ItemCheckFilter;


# direct methods
.method static constructor <clinit>()V
    .locals 3

    .prologue
    .line 40
    const-class v0, Landroid/widget/AbsListView;

    const-string v1, "setAdapter"

    const-string v2, "(Landroid/widget/ListAdapter;)V"

    invoke-static {v0, v1, v2}, Lmiui/reflect/Method;->of(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lmiui/reflect/Method;

    move-result-object v0

    sput-object v0, Lcom/miui/internal/widget/EditableListViewDelegate;->xd:Lmiui/reflect/Method;

    .line 42
    const-class v0, Landroid/widget/AbsListView;

    const-string v1, "clearChoices"

    const-string v2, "()V"

    invoke-static {v0, v1, v2}, Lmiui/reflect/Method;->of(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lmiui/reflect/Method;

    move-result-object v0

    sput-object v0, Lcom/miui/internal/widget/EditableListViewDelegate;->xe:Lmiui/reflect/Method;

    .line 43
    const-class v0, Landroid/widget/AbsListView;

    const-string v1, "setItemChecked"

    const-string v2, "(IZ)V"

    invoke-static {v0, v1, v2}, Lmiui/reflect/Method;->of(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lmiui/reflect/Method;

    move-result-object v0

    sput-object v0, Lcom/miui/internal/widget/EditableListViewDelegate;->xf:Lmiui/reflect/Method;

    .line 44
    const-class v0, Landroid/widget/AbsListView;

    const-string v1, "isItemChecked"

    const-string v2, "(I)Z"

    invoke-static {v0, v1, v2}, Lmiui/reflect/Method;->of(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lmiui/reflect/Method;

    move-result-object v0

    sput-object v0, Lcom/miui/internal/widget/EditableListViewDelegate;->xg:Lmiui/reflect/Method;

    .line 45
    const-class v0, Landroid/widget/AbsListView;

    const-string v1, "getCheckedItemPositions"

    const-string v2, "()Landroid/util/SparseBooleanArray;"

    invoke-static {v0, v1, v2}, Lmiui/reflect/Method;->of(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lmiui/reflect/Method;

    move-result-object v0

    sput-object v0, Lcom/miui/internal/widget/EditableListViewDelegate;->xh:Lmiui/reflect/Method;

    .line 47
    const-class v0, Landroid/widget/AbsListView;

    const-string v1, "setMultiChoiceModeListener"

    const-string v2, "(Landroid/widget/AbsListView$MultiChoiceModeListener;)V"

    invoke-static {v0, v1, v2}, Lmiui/reflect/Method;->of(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lmiui/reflect/Method;

    move-result-object v0

    sput-object v0, Lcom/miui/internal/widget/EditableListViewDelegate;->xi:Lmiui/reflect/Method;

    return-void
.end method

.method public constructor <init>()V
    .locals 1

    .prologue
    .line 31
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 68
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->xr:Ljava/util/List;

    .line 70
    new-instance v0, Lcom/miui/internal/widget/c;

    invoke-direct {v0, p0}, Lcom/miui/internal/widget/c;-><init>(Lcom/miui/internal/widget/EditableListViewDelegate;)V

    iput-object v0, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->xs:Lcom/miui/internal/widget/EditableListViewDelegate$UpdateListener;

    .line 81
    new-instance v0, Lcom/miui/internal/widget/b;

    invoke-direct {v0, p0}, Lcom/miui/internal/widget/b;-><init>(Lcom/miui/internal/widget/EditableListViewDelegate;)V

    iput-object v0, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->xt:Landroid/widget/AbsListView$RecyclerListener;

    .line 520
    return-void
.end method

.method static synthetic a(Lcom/miui/internal/widget/EditableListViewDelegate;I)I
    .locals 0

    .prologue
    .line 31
    iput p1, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->xp:I

    return p1
.end method

.method static synthetic a(Lcom/miui/internal/widget/EditableListViewDelegate;Landroid/view/ActionMode;)Landroid/view/ActionMode;
    .locals 0

    .prologue
    .line 31
    iput-object p1, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->mActionMode:Landroid/view/ActionMode;

    return-object p1
.end method

.method static synthetic a(Lcom/miui/internal/widget/EditableListViewDelegate;Landroid/view/View;)Landroid/widget/CheckBox;
    .locals 1

    .prologue
    .line 31
    invoke-direct {p0, p1}, Lcom/miui/internal/widget/EditableListViewDelegate;->e(Landroid/view/View;)Landroid/widget/CheckBox;

    move-result-object v0

    return-object v0
.end method

.method static synthetic a(Lcom/miui/internal/widget/EditableListViewDelegate;)Ljava/util/List;
    .locals 1

    .prologue
    .line 31
    invoke-direct {p0}, Lcom/miui/internal/widget/EditableListViewDelegate;->cp()Ljava/util/List;

    move-result-object v0

    return-object v0
.end method

.method static synthetic b(Lcom/miui/internal/widget/EditableListViewDelegate;I)I
    .locals 0

    .prologue
    .line 31
    iput p1, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->xq:I

    return p1
.end method

.method static synthetic b(Lcom/miui/internal/widget/EditableListViewDelegate;)Landroid/widget/AbsListView;
    .locals 1

    .prologue
    .line 31
    iget-object v0, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->xj:Landroid/widget/AbsListView;

    return-object v0
.end method

.method static synthetic c(Lcom/miui/internal/widget/EditableListViewDelegate;)I
    .locals 1

    .prologue
    .line 31
    iget v0, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->xq:I

    return v0
.end method

.method static synthetic c(Lcom/miui/internal/widget/EditableListViewDelegate;I)Z
    .locals 1

    .prologue
    .line 31
    invoke-direct {p0, p1}, Lcom/miui/internal/widget/EditableListViewDelegate;->isItemCheckable(I)Z

    move-result v0

    return v0
.end method

.method private co()V
    .locals 8

    .prologue
    .line 312
    iget-object v0, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->xj:Landroid/widget/AbsListView;

    invoke-virtual {v0}, Landroid/widget/AbsListView;->getFirstVisiblePosition()I

    move-result v1

    .line 313
    iget-object v0, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->xj:Landroid/widget/AbsListView;

    invoke-virtual {v0}, Landroid/widget/AbsListView;->getChildCount()I

    move-result v2

    .line 314
    const/4 v0, 0x0

    :goto_0
    if-ge v0, v2, :cond_0

    .line 315
    iget-object v3, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->xj:Landroid/widget/AbsListView;

    invoke-virtual {v3, v0}, Landroid/widget/AbsListView;->getChildAt(I)Landroid/view/View;

    move-result-object v3

    .line 316
    add-int v4, v1, v0

    .line 317
    iget-object v5, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->xs:Lcom/miui/internal/widget/EditableListViewDelegate$UpdateListener;

    invoke-virtual {p0}, Lcom/miui/internal/widget/EditableListViewDelegate;->getAdapter()Landroid/widget/ListAdapter;

    move-result-object v6

    invoke-interface {v6, v4}, Landroid/widget/ListAdapter;->getItemId(I)J

    move-result-wide v6

    invoke-interface {v5, v3, v4, v6, v7}, Lcom/miui/internal/widget/EditableListViewDelegate$UpdateListener;->updateOnScreenCheckedView(Landroid/view/View;IJ)V

    .line 314
    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    .line 319
    :cond_0
    return-void
.end method

.method private cp()Ljava/util/List;
    .locals 4
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/List",
            "<",
            "Landroid/widget/CheckBox;",
            ">;"
        }
    .end annotation

    .prologue
    .line 379
    iget-object v0, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->xj:Landroid/widget/AbsListView;

    invoke-virtual {v0}, Landroid/widget/AbsListView;->getChildCount()I

    move-result v1

    .line 380
    iget-object v2, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->xr:Ljava/util/List;

    .line 381
    iget-object v0, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->xr:Ljava/util/List;

    invoke-interface {v0}, Ljava/util/List;->clear()V

    .line 382
    const/4 v0, 0x0

    :goto_0
    if-ge v0, v1, :cond_1

    .line 383
    iget-object v3, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->xj:Landroid/widget/AbsListView;

    invoke-virtual {v3, v0}, Landroid/widget/AbsListView;->getChildAt(I)Landroid/view/View;

    move-result-object v3

    invoke-direct {p0, v3}, Lcom/miui/internal/widget/EditableListViewDelegate;->e(Landroid/view/View;)Landroid/widget/CheckBox;

    move-result-object v3

    .line 384
    if-eqz v3, :cond_0

    .line 385
    invoke-interface {v2, v3}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 382
    :cond_0
    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    .line 388
    :cond_1
    return-object v2
.end method

.method static synthetic d(Lcom/miui/internal/widget/EditableListViewDelegate;I)I
    .locals 1

    .prologue
    .line 31
    iget v0, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->xp:I

    add-int/2addr v0, p1

    iput v0, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->xp:I

    return v0
.end method

.method static synthetic d(Lcom/miui/internal/widget/EditableListViewDelegate;)Landroid/view/ActionMode;
    .locals 1

    .prologue
    .line 31
    iget-object v0, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->mActionMode:Landroid/view/ActionMode;

    return-object v0
.end method

.method private e(Landroid/view/View;)Landroid/widget/CheckBox;
    .locals 2

    .prologue
    const v1, 0x7fffffff

    .line 367
    invoke-virtual {p1, v1}, Landroid/view/View;->getTag(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/widget/CheckBox;

    .line 368
    if-nez v0, :cond_0

    .line 369
    const v0, 0x1020001

    invoke-virtual {p1, v0}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/CheckBox;

    .line 370
    if-eqz v0, :cond_0

    .line 371
    invoke-virtual {p1, v1, v0}, Landroid/view/View;->setTag(ILjava/lang/Object;)V

    .line 375
    :cond_0
    return-object v0
.end method

.method static synthetic e(Lcom/miui/internal/widget/EditableListViewDelegate;)Lcom/miui/internal/widget/EditableListViewDelegate$UpdateListener;
    .locals 1

    .prologue
    .line 31
    iget-object v0, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->xs:Lcom/miui/internal/widget/EditableListViewDelegate$UpdateListener;

    return-object v0
.end method

.method static synthetic f(Lcom/miui/internal/widget/EditableListViewDelegate;)Z
    .locals 1

    .prologue
    .line 31
    iget-boolean v0, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->xn:Z

    return v0
.end method

.method static synthetic g(Lcom/miui/internal/widget/EditableListViewDelegate;)Z
    .locals 1

    .prologue
    .line 31
    iget-boolean v0, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->xo:Z

    return v0
.end method

.method private getCheckableItemCount()I
    .locals 1

    .prologue
    .line 95
    iget-object v0, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->xu:Lmiui/widget/EditableListView$ItemCheckFilter;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->xu:Lmiui/widget/EditableListView$ItemCheckFilter;

    invoke-interface {v0}, Lmiui/widget/EditableListView$ItemCheckFilter;->getCheckableItemCount()I

    move-result v0

    :goto_0
    return v0

    :cond_0
    invoke-virtual {p0}, Lcom/miui/internal/widget/EditableListViewDelegate;->getAdapter()Landroid/widget/ListAdapter;

    move-result-object v0

    if-eqz v0, :cond_1

    invoke-virtual {p0}, Lcom/miui/internal/widget/EditableListViewDelegate;->getAdapter()Landroid/widget/ListAdapter;

    move-result-object v0

    invoke-interface {v0}, Landroid/widget/ListAdapter;->getCount()I

    move-result v0

    goto :goto_0

    :cond_1
    const/4 v0, 0x0

    goto :goto_0
.end method

.method private isItemCheckable(I)Z
    .locals 1

    .prologue
    .line 101
    iget-object v0, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->xu:Lmiui/widget/EditableListView$ItemCheckFilter;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->xu:Lmiui/widget/EditableListView$ItemCheckFilter;

    invoke-interface {v0, p1}, Lmiui/widget/EditableListView$ItemCheckFilter;->isItemCheckable(I)Z

    move-result v0

    if-eqz v0, :cond_1

    :cond_0
    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_1
    const/4 v0, 0x0

    goto :goto_0
.end method


# virtual methods
.method public clearChoices()V
    .locals 5

    .prologue
    const/4 v4, 0x0

    .line 181
    sget-object v0, Lcom/miui/internal/widget/EditableListViewDelegate;->xe:Lmiui/reflect/Method;

    iget-object v1, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->xk:Ljava/lang/Class;

    iget-object v2, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->xj:Landroid/widget/AbsListView;

    new-array v3, v4, [Ljava/lang/Object;

    invoke-virtual {v0, v1, v2, v3}, Lmiui/reflect/Method;->invoke(Ljava/lang/Class;Ljava/lang/Object;[Ljava/lang/Object;)V

    .line 182
    iput v4, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->xp:I

    .line 184
    iget-object v0, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->xs:Lcom/miui/internal/widget/EditableListViewDelegate$UpdateListener;

    iget-object v1, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->mActionMode:Landroid/view/ActionMode;

    invoke-interface {v0, v1}, Lcom/miui/internal/widget/EditableListViewDelegate$UpdateListener;->updateCheckStatus(Landroid/view/ActionMode;)V

    .line 185
    invoke-direct {p0}, Lcom/miui/internal/widget/EditableListViewDelegate;->co()V

    .line 186
    iput v4, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->xp:I

    .line 187
    return-void
.end method

.method public getAdapter()Landroid/widget/ListAdapter;
    .locals 1

    .prologue
    .line 256
    iget-object v0, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->xl:Lcom/miui/internal/widget/EditableListViewDelegate$a;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->xl:Lcom/miui/internal/widget/EditableListViewDelegate$a;

    invoke-virtual {v0}, Lcom/miui/internal/widget/EditableListViewDelegate$a;->getWrappedAdapter()Landroid/widget/ListAdapter;

    move-result-object v0

    :goto_0
    return-object v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public getCheckedItemPositions()Landroid/util/SparseBooleanArray;
    .locals 4

    .prologue
    .line 243
    sget-object v0, Lcom/miui/internal/widget/EditableListViewDelegate;->xh:Lmiui/reflect/Method;

    iget-object v1, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->xk:Ljava/lang/Class;

    iget-object v2, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->xj:Landroid/widget/AbsListView;

    const/4 v3, 0x0

    new-array v3, v3, [Ljava/lang/Object;

    invoke-virtual {v0, v1, v2, v3}, Lmiui/reflect/Method;->invokeObject(Ljava/lang/Class;Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/util/SparseBooleanArray;

    invoke-virtual {v0}, Landroid/util/SparseBooleanArray;->clone()Landroid/util/SparseBooleanArray;

    move-result-object v0

    return-object v0
.end method

.method public getListView()Landroid/widget/AbsListView;
    .locals 1

    .prologue
    .line 133
    iget-object v0, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->xj:Landroid/widget/AbsListView;

    return-object v0
.end method

.method public initialize(Landroid/widget/AbsListView;Ljava/lang/Class;)V
    .locals 7
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Landroid/widget/AbsListView;",
            "Ljava/lang/Class",
            "<+",
            "Landroid/widget/AbsListView;",
            ">;)V"
        }
    .end annotation

    .prologue
    .line 112
    if-nez p1, :cond_0

    .line 113
    new-instance v0, Ljava/lang/IllegalArgumentException;

    const-string v1, "absListView is null"

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 116
    :cond_0
    iput-object p1, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->xj:Landroid/widget/AbsListView;

    .line 117
    iput-object p2, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->xk:Ljava/lang/Class;

    .line 118
    iget-object v0, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->xj:Landroid/widget/AbsListView;

    const/4 v1, 0x3

    invoke-virtual {v0, v1}, Landroid/widget/AbsListView;->setChoiceMode(I)V

    .line 119
    iget-object v0, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->xj:Landroid/widget/AbsListView;

    iget-object v1, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->xt:Landroid/widget/AbsListView$RecyclerListener;

    invoke-virtual {v0, v1}, Landroid/widget/AbsListView;->setRecyclerListener(Landroid/widget/AbsListView$RecyclerListener;)V

    .line 120
    iget-object v0, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->xj:Landroid/widget/AbsListView;

    instance-of v0, v0, Landroid/widget/ListView;

    iput-boolean v0, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->xo:Z

    .line 122
    iget-object v0, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->xj:Landroid/widget/AbsListView;

    invoke-virtual {v0}, Landroid/widget/AbsListView;->getAdapter()Landroid/widget/Adapter;

    move-result-object v0

    if-eqz v0, :cond_1

    .line 123
    iget-object v0, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->xj:Landroid/widget/AbsListView;

    invoke-virtual {v0}, Landroid/widget/AbsListView;->getAdapter()Landroid/widget/Adapter;

    move-result-object v0

    check-cast v0, Landroid/widget/ListAdapter;

    .line 124
    sget-object v2, Lcom/miui/internal/widget/EditableListViewDelegate;->xd:Lmiui/reflect/Method;

    iget-object v3, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->xk:Ljava/lang/Class;

    iget-object v4, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->xj:Landroid/widget/AbsListView;

    const/4 v1, 0x1

    new-array v5, v1, [Ljava/lang/Object;

    const/4 v6, 0x0

    const/4 v1, 0x0

    check-cast v1, Landroid/widget/ListAdapter;

    aput-object v1, v5, v6

    invoke-virtual {v2, v3, v4, v5}, Lmiui/reflect/Method;->invoke(Ljava/lang/Class;Ljava/lang/Object;[Ljava/lang/Object;)V

    .line 125
    invoke-virtual {p0, v0}, Lcom/miui/internal/widget/EditableListViewDelegate;->setAdapter(Landroid/widget/ListAdapter;)V

    .line 127
    :cond_1
    return-void
.end method

.method public isAllItemsChecked()Z
    .locals 2

    .prologue
    .line 148
    iget v0, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->xp:I

    if-eqz v0, :cond_0

    invoke-direct {p0}, Lcom/miui/internal/widget/EditableListViewDelegate;->getCheckableItemCount()I

    move-result v0

    iget v1, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->xp:I

    if-ne v0, v1, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public isInActionMode()Z
    .locals 1

    .prologue
    .line 308
    iget-object v0, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->mActionMode:Landroid/view/ActionMode;

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public isItemChecked(I)Z
    .locals 6

    .prologue
    const/4 v1, 0x0

    .line 210
    iget-boolean v0, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->xo:Z

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->xj:Landroid/widget/AbsListView;

    check-cast v0, Landroid/widget/ListView;

    invoke-virtual {v0}, Landroid/widget/ListView;->getHeaderViewsCount()I

    move-result v0

    .line 211
    :goto_0
    sget-object v2, Lcom/miui/internal/widget/EditableListViewDelegate;->xg:Lmiui/reflect/Method;

    iget-object v3, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->xk:Ljava/lang/Class;

    iget-object v4, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->xj:Landroid/widget/AbsListView;

    const/4 v5, 0x1

    new-array v5, v5, [Ljava/lang/Object;

    add-int/2addr v0, p1

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    aput-object v0, v5, v1

    invoke-virtual {v2, v3, v4, v5}, Lmiui/reflect/Method;->invokeBoolean(Ljava/lang/Class;Ljava/lang/Object;[Ljava/lang/Object;)Z

    move-result v0

    return v0

    :cond_0
    move v0, v1

    .line 210
    goto :goto_0
.end method

.method public isItemIdChecked(J)Z
    .locals 6

    .prologue
    const/4 v0, 0x0

    .line 222
    .line 223
    iget-object v1, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->xj:Landroid/widget/AbsListView;

    invoke-virtual {v1}, Landroid/widget/AbsListView;->getCheckedItemIds()[J

    move-result-object v2

    array-length v3, v2

    move v1, v0

    :goto_0
    if-ge v1, v3, :cond_0

    aget-wide v4, v2, v1

    .line 224
    cmp-long v4, p1, v4

    if-nez v4, :cond_1

    .line 225
    const/4 v0, 0x1

    .line 230
    :cond_0
    return v0

    .line 223
    :cond_1
    add-int/lit8 v1, v1, 0x1

    goto :goto_0
.end method

.method public setAdapter(Landroid/widget/ListAdapter;)V
    .locals 6

    .prologue
    const/4 v0, 0x0

    const/4 v4, 0x1

    const/4 v5, 0x0

    .line 275
    invoke-virtual {p0}, Lcom/miui/internal/widget/EditableListViewDelegate;->getAdapter()Landroid/widget/ListAdapter;

    move-result-object v1

    if-eq p1, v1, :cond_0

    .line 276
    if-nez p1, :cond_1

    .line 277
    iput-object v0, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->xl:Lcom/miui/internal/widget/EditableListViewDelegate$a;

    .line 278
    sget-object v1, Lcom/miui/internal/widget/EditableListViewDelegate;->xd:Lmiui/reflect/Method;

    iget-object v2, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->xk:Ljava/lang/Class;

    iget-object v3, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->xj:Landroid/widget/AbsListView;

    new-array v4, v4, [Ljava/lang/Object;

    check-cast v0, Landroid/widget/ListAdapter;

    aput-object v0, v4, v5

    invoke-virtual {v1, v2, v3, v4}, Lmiui/reflect/Method;->invoke(Ljava/lang/Class;Ljava/lang/Object;[Ljava/lang/Object;)V

    .line 284
    :cond_0
    :goto_0
    return-void

    .line 280
    :cond_1
    new-instance v0, Lcom/miui/internal/widget/EditableListViewDelegate$a;

    invoke-direct {v0, p0, p1}, Lcom/miui/internal/widget/EditableListViewDelegate$a;-><init>(Lcom/miui/internal/widget/EditableListViewDelegate;Landroid/widget/ListAdapter;)V

    iput-object v0, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->xl:Lcom/miui/internal/widget/EditableListViewDelegate$a;

    .line 281
    sget-object v0, Lcom/miui/internal/widget/EditableListViewDelegate;->xd:Lmiui/reflect/Method;

    iget-object v1, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->xk:Ljava/lang/Class;

    iget-object v2, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->xj:Landroid/widget/AbsListView;

    new-array v3, v4, [Ljava/lang/Object;

    iget-object v4, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->xl:Lcom/miui/internal/widget/EditableListViewDelegate$a;

    aput-object v4, v3, v5

    invoke-virtual {v0, v1, v2, v3}, Lmiui/reflect/Method;->invoke(Ljava/lang/Class;Ljava/lang/Object;[Ljava/lang/Object;)V

    goto :goto_0
.end method

.method public setAllItemsChecked(Z)V
    .locals 4

    .prologue
    const/4 v1, 0x0

    .line 159
    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->xn:Z

    .line 160
    invoke-virtual {p0}, Lcom/miui/internal/widget/EditableListViewDelegate;->getAdapter()Landroid/widget/ListAdapter;

    move-result-object v0

    invoke-interface {v0}, Landroid/widget/ListAdapter;->getCount()I

    move-result v2

    move v0, v1

    .line 161
    :goto_0
    if-ge v0, v2, :cond_1

    .line 162
    invoke-direct {p0, v0}, Lcom/miui/internal/widget/EditableListViewDelegate;->isItemCheckable(I)Z

    move-result v3

    if-eqz v3, :cond_0

    .line 163
    invoke-virtual {p0, v0, p1}, Lcom/miui/internal/widget/EditableListViewDelegate;->setItemChecked(IZ)V

    .line 161
    :cond_0
    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    .line 166
    :cond_1
    if-eqz p1, :cond_3

    invoke-direct {p0}, Lcom/miui/internal/widget/EditableListViewDelegate;->getCheckableItemCount()I

    move-result v0

    :goto_1
    iput v0, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->xp:I

    .line 167
    iput-boolean v1, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->xn:Z

    .line 169
    iget-object v0, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->xs:Lcom/miui/internal/widget/EditableListViewDelegate$UpdateListener;

    iget-object v1, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->mActionMode:Landroid/view/ActionMode;

    invoke-interface {v0, v1}, Lcom/miui/internal/widget/EditableListViewDelegate$UpdateListener;->updateCheckStatus(Landroid/view/ActionMode;)V

    .line 170
    invoke-direct {p0}, Lcom/miui/internal/widget/EditableListViewDelegate;->co()V

    .line 172
    iget-object v0, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->xm:Lcom/miui/internal/widget/EditableListViewDelegate$b;

    if-eqz v0, :cond_2

    .line 173
    iget-object v0, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->xm:Lcom/miui/internal/widget/EditableListViewDelegate$b;

    iget-object v1, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->mActionMode:Landroid/view/ActionMode;

    invoke-virtual {v0, v1, p1}, Lcom/miui/internal/widget/EditableListViewDelegate$b;->onAllItemCheckedStateChanged(Landroid/view/ActionMode;Z)V

    .line 175
    :cond_2
    return-void

    :cond_3
    move v0, v1

    .line 166
    goto :goto_1
.end method

.method public setItemCheckFilter(Lmiui/widget/EditableListView$ItemCheckFilter;)V
    .locals 0

    .prologue
    .line 264
    iput-object p1, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->xu:Lmiui/widget/EditableListView$ItemCheckFilter;

    .line 265
    return-void
.end method

.method public setItemChecked(IZ)V
    .locals 6

    .prologue
    const/4 v1, 0x0

    .line 198
    iget-boolean v0, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->xo:Z

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->xj:Landroid/widget/AbsListView;

    check-cast v0, Landroid/widget/ListView;

    invoke-virtual {v0}, Landroid/widget/ListView;->getHeaderViewsCount()I

    move-result v0

    .line 199
    :goto_0
    sget-object v2, Lcom/miui/internal/widget/EditableListViewDelegate;->xf:Lmiui/reflect/Method;

    iget-object v3, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->xk:Ljava/lang/Class;

    iget-object v4, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->xj:Landroid/widget/AbsListView;

    const/4 v5, 0x2

    new-array v5, v5, [Ljava/lang/Object;

    add-int/2addr v0, p1

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    aput-object v0, v5, v1

    const/4 v0, 0x1

    invoke-static {p2}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v1

    aput-object v1, v5, v0

    invoke-virtual {v2, v3, v4, v5}, Lmiui/reflect/Method;->invoke(Ljava/lang/Class;Ljava/lang/Object;[Ljava/lang/Object;)V

    .line 200
    return-void

    :cond_0
    move v0, v1

    .line 198
    goto :goto_0
.end method

.method public setMultiChoiceModeListener(Landroid/widget/AbsListView$MultiChoiceModeListener;)V
    .locals 6

    .prologue
    .line 294
    iget-object v0, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->xm:Lcom/miui/internal/widget/EditableListViewDelegate$b;

    if-nez v0, :cond_0

    .line 295
    new-instance v0, Lcom/miui/internal/widget/EditableListViewDelegate$b;

    invoke-direct {v0, p0}, Lcom/miui/internal/widget/EditableListViewDelegate$b;-><init>(Lcom/miui/internal/widget/EditableListViewDelegate;)V

    iput-object v0, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->xm:Lcom/miui/internal/widget/EditableListViewDelegate$b;

    .line 298
    :cond_0
    iget-object v0, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->xm:Lcom/miui/internal/widget/EditableListViewDelegate$b;

    invoke-virtual {v0, p1}, Lcom/miui/internal/widget/EditableListViewDelegate$b;->a(Landroid/widget/AbsListView$MultiChoiceModeListener;)V

    .line 299
    sget-object v0, Lcom/miui/internal/widget/EditableListViewDelegate;->xi:Lmiui/reflect/Method;

    iget-object v1, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->xk:Ljava/lang/Class;

    iget-object v2, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->xj:Landroid/widget/AbsListView;

    const/4 v3, 0x1

    new-array v3, v3, [Ljava/lang/Object;

    const/4 v4, 0x0

    iget-object v5, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->xm:Lcom/miui/internal/widget/EditableListViewDelegate$b;

    aput-object v5, v3, v4

    invoke-virtual {v0, v1, v2, v3}, Lmiui/reflect/Method;->invoke(Ljava/lang/Class;Ljava/lang/Object;[Ljava/lang/Object;)V

    .line 300
    return-void
.end method

.method public setUpdateListener(Lcom/miui/internal/widget/EditableListViewDelegate$UpdateListener;)V
    .locals 0

    .prologue
    .line 137
    if-eqz p1, :cond_0

    .line 138
    iput-object p1, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->xs:Lcom/miui/internal/widget/EditableListViewDelegate$UpdateListener;

    .line 140
    :cond_0
    return-void
.end method

.method public updateCheckStatus(Landroid/view/ActionMode;)V
    .locals 6

    .prologue
    const/4 v2, 0x1

    const/4 v3, 0x0

    .line 348
    if-eqz p1, :cond_0

    .line 349
    iget v4, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->xp:I

    .line 350
    iget-object v0, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->xj:Landroid/widget/AbsListView;

    invoke-virtual {v0}, Landroid/widget/AbsListView;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    .line 351
    if-nez v4, :cond_1

    .line 352
    sget v1, Lcom/miui/internal/R$string;->select_item:I

    invoke-virtual {v0, v1}, Landroid/content/res/Resources;->getString(I)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p1, v0}, Landroid/view/ActionMode;->setTitle(Ljava/lang/CharSequence;)V

    :goto_0
    move-object v0, p1

    .line 358
    check-cast v0, Lmiui/view/EditActionMode;

    const v5, 0x102001a

    invoke-virtual {p0}, Lcom/miui/internal/widget/EditableListViewDelegate;->isAllItemsChecked()Z

    move-result v1

    if-eqz v1, :cond_2

    sget v1, Lcom/miui/internal/R$string;->deselect_all:I

    :goto_1
    invoke-interface {v0, v5, v1}, Lmiui/view/EditActionMode;->setButton(II)V

    .line 362
    invoke-virtual {p1}, Landroid/view/ActionMode;->getMenu()Landroid/view/Menu;

    move-result-object v1

    if-eqz v4, :cond_3

    move v0, v2

    :goto_2
    invoke-interface {v1, v3, v0}, Landroid/view/Menu;->setGroupEnabled(IZ)V

    .line 364
    :cond_0
    return-void

    .line 354
    :cond_1
    sget v1, Lcom/miui/internal/R$plurals;->items_selected:I

    invoke-virtual {v0, v1, v4}, Landroid/content/res/Resources;->getQuantityString(II)Ljava/lang/String;

    move-result-object v0

    .line 355
    new-array v1, v2, [Ljava/lang/Object;

    invoke-static {v4}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v5

    aput-object v5, v1, v3

    invoke-static {v0, v1}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p1, v0}, Landroid/view/ActionMode;->setTitle(Ljava/lang/CharSequence;)V

    goto :goto_0

    .line 358
    :cond_2
    sget v1, Lcom/miui/internal/R$string;->select_all:I

    goto :goto_1

    :cond_3
    move v0, v3

    .line 362
    goto :goto_2
.end method

.method public updateOnScreenCheckedView(Landroid/view/View;IJ)V
    .locals 4

    .prologue
    .line 329
    if-eqz p1, :cond_0

    .line 330
    sget-object v0, Lcom/miui/internal/widget/EditableListViewDelegate;->xh:Lmiui/reflect/Method;

    iget-object v1, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->xk:Ljava/lang/Class;

    iget-object v2, p0, Lcom/miui/internal/widget/EditableListViewDelegate;->xj:Landroid/widget/AbsListView;

    const/4 v3, 0x0

    new-array v3, v3, [Ljava/lang/Object;

    invoke-virtual {v0, v1, v2, v3}, Lmiui/reflect/Method;->invokeObject(Ljava/lang/Class;Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/util/SparseBooleanArray;

    invoke-virtual {v0, p2}, Landroid/util/SparseBooleanArray;->get(I)Z

    move-result v0

    .line 333
    invoke-direct {p0, p1}, Lcom/miui/internal/widget/EditableListViewDelegate;->e(Landroid/view/View;)Landroid/widget/CheckBox;

    move-result-object v1

    .line 334
    if-eqz v1, :cond_1

    .line 335
    invoke-virtual {v1, v0}, Landroid/widget/CheckBox;->setChecked(Z)V

    .line 340
    :cond_0
    :goto_0
    return-void

    .line 336
    :cond_1
    instance-of v1, p1, Landroid/widget/Checkable;

    if-eqz v1, :cond_0

    .line 337
    check-cast p1, Landroid/widget/Checkable;

    invoke-interface {p1, v0}, Landroid/widget/Checkable;->setChecked(Z)V

    goto :goto_0
.end method
