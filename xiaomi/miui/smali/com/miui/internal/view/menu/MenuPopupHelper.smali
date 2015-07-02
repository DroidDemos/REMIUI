.class public Lcom/miui/internal/view/menu/MenuPopupHelper;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroid/view/View$OnKeyListener;
.implements Landroid/view/ViewTreeObserver$OnGlobalLayoutListener;
.implements Landroid/widget/AdapterView$OnItemClickListener;
.implements Landroid/widget/PopupWindow$OnDismissListener;
.implements Lcom/miui/internal/view/menu/MenuPresenter;


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/internal/view/menu/MenuPopupHelper$a;
    }
.end annotation


# static fields
.field private static final ns:I


# instance fields
.field private kw:Landroid/view/View;

.field private mContext:Landroid/content/Context;

.field private mInflater:Landroid/view/LayoutInflater;

.field private mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

.field private nA:Landroid/view/ViewGroup;

.field private nB:I

.field private nC:I

.field nt:Z

.field private nu:Lmiui/widget/ListPopupWindow;

.field private nv:I

.field private nw:Z

.field private nx:Landroid/view/ViewTreeObserver;

.field private ny:Lcom/miui/internal/view/menu/MenuPopupHelper$a;

.field private nz:Lcom/miui/internal/view/menu/MenuPresenter$Callback;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .prologue
    .line 36
    sget v0, Lcom/miui/internal/R$layout;->popup_menu_item_layout:I

    sput v0, Lcom/miui/internal/view/menu/MenuPopupHelper;->ns:I

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Lcom/miui/internal/view/menu/MenuBuilder;)V
    .locals 2

    .prologue
    .line 67
    const/4 v0, 0x0

    const/4 v1, 0x0

    invoke-direct {p0, p1, p2, v0, v1}, Lcom/miui/internal/view/menu/MenuPopupHelper;-><init>(Landroid/content/Context;Lcom/miui/internal/view/menu/MenuBuilder;Landroid/view/View;Z)V

    .line 68
    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Lcom/miui/internal/view/menu/MenuBuilder;Landroid/view/View;)V
    .locals 1

    .prologue
    .line 71
    const/4 v0, 0x0

    invoke-direct {p0, p1, p2, p3, v0}, Lcom/miui/internal/view/menu/MenuPopupHelper;-><init>(Landroid/content/Context;Lcom/miui/internal/view/menu/MenuBuilder;Landroid/view/View;Z)V

    .line 72
    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Lcom/miui/internal/view/menu/MenuBuilder;Landroid/view/View;Z)V
    .locals 3

    .prologue
    .line 75
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 64
    sget v0, Lcom/miui/internal/view/menu/MenuPopupHelper;->ns:I

    iput v0, p0, Lcom/miui/internal/view/menu/MenuPopupHelper;->nC:I

    .line 76
    iput-object p1, p0, Lcom/miui/internal/view/menu/MenuPopupHelper;->mContext:Landroid/content/Context;

    .line 77
    invoke-static {p1}, Landroid/view/LayoutInflater;->from(Landroid/content/Context;)Landroid/view/LayoutInflater;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/internal/view/menu/MenuPopupHelper;->mInflater:Landroid/view/LayoutInflater;

    .line 78
    iput-object p2, p0, Lcom/miui/internal/view/menu/MenuPopupHelper;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    .line 79
    iput-boolean p4, p0, Lcom/miui/internal/view/menu/MenuPopupHelper;->nw:Z

    .line 81
    invoke-virtual {p1}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    .line 82
    invoke-virtual {v0}, Landroid/content/res/Resources;->getDisplayMetrics()Landroid/util/DisplayMetrics;

    move-result-object v1

    iget v1, v1, Landroid/util/DisplayMetrics;->widthPixels:I

    div-int/lit8 v1, v1, 0x2

    sget v2, Lcom/miui/internal/R$dimen;->config_prefDialogWidth:I

    invoke-virtual {v0, v2}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v0

    invoke-static {v1, v0}, Ljava/lang/Math;->max(II)I

    move-result v0

    iput v0, p0, Lcom/miui/internal/view/menu/MenuPopupHelper;->nv:I

    .line 85
    iput-object p3, p0, Lcom/miui/internal/view/menu/MenuPopupHelper;->kw:Landroid/view/View;

    .line 87
    invoke-virtual {p2, p0}, Lcom/miui/internal/view/menu/MenuBuilder;->addMenuPresenter(Lcom/miui/internal/view/menu/MenuPresenter;)V

    .line 88
    return-void
.end method

.method private a(Landroid/widget/ListAdapter;)I
    .locals 10

    .prologue
    const/4 v2, 0x0

    const/high16 v3, -0x80000000

    const/4 v0, 0x0

    .line 179
    .line 182
    iget v1, p0, Lcom/miui/internal/view/menu/MenuPopupHelper;->nv:I

    invoke-static {v1, v3}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result v6

    .line 184
    iget v1, p0, Lcom/miui/internal/view/menu/MenuPopupHelper;->nv:I

    invoke-static {v1, v3}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result v7

    .line 186
    invoke-interface {p1}, Landroid/widget/ListAdapter;->getCount()I

    move-result v8

    move v4, v0

    move v1, v0

    move-object v3, v2

    move v5, v0

    .line 187
    :goto_0
    if-ge v4, v8, :cond_1

    .line 188
    invoke-interface {p1, v4}, Landroid/widget/ListAdapter;->getItemViewType(I)I

    move-result v0

    .line 189
    if-eq v0, v1, :cond_2

    move-object v1, v2

    .line 193
    :goto_1
    iget-object v3, p0, Lcom/miui/internal/view/menu/MenuPopupHelper;->nA:Landroid/view/ViewGroup;

    if-nez v3, :cond_0

    .line 194
    new-instance v3, Landroid/widget/FrameLayout;

    iget-object v9, p0, Lcom/miui/internal/view/menu/MenuPopupHelper;->mContext:Landroid/content/Context;

    invoke-direct {v3, v9}, Landroid/widget/FrameLayout;-><init>(Landroid/content/Context;)V

    iput-object v3, p0, Lcom/miui/internal/view/menu/MenuPopupHelper;->nA:Landroid/view/ViewGroup;

    .line 196
    :cond_0
    iget-object v3, p0, Lcom/miui/internal/view/menu/MenuPopupHelper;->nA:Landroid/view/ViewGroup;

    invoke-interface {p1, v4, v1, v3}, Landroid/widget/ListAdapter;->getView(ILandroid/view/View;Landroid/view/ViewGroup;)Landroid/view/View;

    move-result-object v3

    .line 197
    invoke-virtual {v3, v6, v7}, Landroid/view/View;->measure(II)V

    .line 198
    invoke-virtual {v3}, Landroid/view/View;->getMeasuredWidth()I

    move-result v1

    invoke-static {v5, v1}, Ljava/lang/Math;->max(II)I

    move-result v5

    .line 187
    add-int/lit8 v1, v4, 0x1

    move v4, v1

    move v1, v0

    goto :goto_0

    .line 200
    :cond_1
    return v5

    :cond_2
    move v0, v1

    move-object v1, v3

    goto :goto_1
.end method

.method static synthetic a(Lcom/miui/internal/view/menu/MenuPopupHelper;)Z
    .locals 1

    .prologue
    .line 32
    iget-boolean v0, p0, Lcom/miui/internal/view/menu/MenuPopupHelper;->nw:Z

    return v0
.end method

.method static synthetic b(Lcom/miui/internal/view/menu/MenuPopupHelper;)I
    .locals 1

    .prologue
    .line 32
    iget v0, p0, Lcom/miui/internal/view/menu/MenuPopupHelper;->nC:I

    return v0
.end method

.method static synthetic c(Lcom/miui/internal/view/menu/MenuPopupHelper;)Landroid/view/LayoutInflater;
    .locals 1

    .prologue
    .line 32
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuPopupHelper;->mInflater:Landroid/view/LayoutInflater;

    return-object v0
.end method

.method static synthetic d(Lcom/miui/internal/view/menu/MenuPopupHelper;)Lcom/miui/internal/view/menu/MenuBuilder;
    .locals 1

    .prologue
    .line 32
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuPopupHelper;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    return-object v0
.end method


# virtual methods
.method public collapseItemActionView(Lcom/miui/internal/view/menu/MenuBuilder;Lcom/miui/internal/view/menu/MenuItemImpl;)Z
    .locals 1

    .prologue
    .line 289
    const/4 v0, 0x0

    return v0
.end method

.method public dismiss(Z)V
    .locals 1

    .prologue
    .line 142
    invoke-virtual {p0}, Lcom/miui/internal/view/menu/MenuPopupHelper;->isShowing()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 143
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuPopupHelper;->nu:Lmiui/widget/ListPopupWindow;

    invoke-virtual {v0, p1}, Lmiui/widget/ListPopupWindow;->dismiss(Z)V

    .line 145
    :cond_0
    return-void
.end method

.method public expandItemActionView(Lcom/miui/internal/view/menu/MenuBuilder;Lcom/miui/internal/view/menu/MenuItemImpl;)Z
    .locals 1

    .prologue
    .line 285
    const/4 v0, 0x0

    return v0
.end method

.method public flagActionItems()Z
    .locals 1

    .prologue
    .line 281
    const/4 v0, 0x0

    return v0
.end method

.method public getId()I
    .locals 1

    .prologue
    .line 294
    const/4 v0, 0x0

    return v0
.end method

.method public getMenuView(Landroid/view/ViewGroup;)Lcom/miui/internal/view/menu/MenuView;
    .locals 2

    .prologue
    .line 224
    new-instance v0, Ljava/lang/UnsupportedOperationException;

    const-string v1, "MenuPopupHelpers manage their own views"

    invoke-direct {v0, v1}, Ljava/lang/UnsupportedOperationException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

.method public initForMenu(Landroid/content/Context;Lcom/miui/internal/view/menu/MenuBuilder;)V
    .locals 0

    .prologue
    .line 220
    return-void
.end method

.method public isShowing()Z
    .locals 1

    .prologue
    .line 160
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuPopupHelper;->nu:Lmiui/widget/ListPopupWindow;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuPopupHelper;->nu:Lmiui/widget/ListPopupWindow;

    invoke-virtual {v0}, Lmiui/widget/ListPopupWindow;->isShowing()Z

    move-result v0

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public onCloseMenu(Lcom/miui/internal/view/menu/MenuBuilder;Z)V
    .locals 1

    .prologue
    .line 269
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuPopupHelper;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    if-eq p1, v0, :cond_1

    .line 277
    :cond_0
    :goto_0
    return-void

    .line 273
    :cond_1
    const/4 v0, 0x1

    invoke-virtual {p0, v0}, Lcom/miui/internal/view/menu/MenuPopupHelper;->dismiss(Z)V

    .line 274
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuPopupHelper;->nz:Lcom/miui/internal/view/menu/MenuPresenter$Callback;

    if-eqz v0, :cond_0

    .line 275
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuPopupHelper;->nz:Lcom/miui/internal/view/menu/MenuPresenter$Callback;

    invoke-interface {v0, p1, p2}, Lcom/miui/internal/view/menu/MenuPresenter$Callback;->onCloseMenu(Lcom/miui/internal/view/menu/MenuBuilder;Z)V

    goto :goto_0
.end method

.method public onDismiss()V
    .locals 2

    .prologue
    const/4 v1, 0x0

    .line 148
    iput-object v1, p0, Lcom/miui/internal/view/menu/MenuPopupHelper;->nu:Lmiui/widget/ListPopupWindow;

    .line 149
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuPopupHelper;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    invoke-virtual {v0}, Lcom/miui/internal/view/menu/MenuBuilder;->close()V

    .line 150
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuPopupHelper;->nx:Landroid/view/ViewTreeObserver;

    if-eqz v0, :cond_1

    .line 151
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuPopupHelper;->nx:Landroid/view/ViewTreeObserver;

    invoke-virtual {v0}, Landroid/view/ViewTreeObserver;->isAlive()Z

    move-result v0

    if-nez v0, :cond_0

    .line 152
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuPopupHelper;->kw:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->getViewTreeObserver()Landroid/view/ViewTreeObserver;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/internal/view/menu/MenuPopupHelper;->nx:Landroid/view/ViewTreeObserver;

    .line 154
    :cond_0
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuPopupHelper;->nx:Landroid/view/ViewTreeObserver;

    invoke-virtual {v0, p0}, Landroid/view/ViewTreeObserver;->removeOnGlobalLayoutListener(Landroid/view/ViewTreeObserver$OnGlobalLayoutListener;)V

    .line 155
    iput-object v1, p0, Lcom/miui/internal/view/menu/MenuPopupHelper;->nx:Landroid/view/ViewTreeObserver;

    .line 157
    :cond_1
    return-void
.end method

.method public onGlobalLayout()V
    .locals 3

    .prologue
    .line 205
    invoke-virtual {p0}, Lcom/miui/internal/view/menu/MenuPopupHelper;->isShowing()Z

    move-result v0

    if-eqz v0, :cond_1

    .line 206
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuPopupHelper;->kw:Landroid/view/View;

    .line 207
    if-eqz v0, :cond_0

    invoke-virtual {v0}, Landroid/view/View;->isShown()Z

    move-result v0

    if-nez v0, :cond_2

    .line 208
    :cond_0
    const/4 v0, 0x0

    invoke-virtual {p0, v0}, Lcom/miui/internal/view/menu/MenuPopupHelper;->dismiss(Z)V

    .line 215
    :cond_1
    :goto_0
    return-void

    .line 209
    :cond_2
    invoke-virtual {p0}, Lcom/miui/internal/view/menu/MenuPopupHelper;->isShowing()Z

    move-result v0

    if-eqz v0, :cond_1

    .line 210
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuPopupHelper;->nu:Lmiui/widget/ListPopupWindow;

    iget-object v1, p0, Lcom/miui/internal/view/menu/MenuPopupHelper;->ny:Lcom/miui/internal/view/menu/MenuPopupHelper$a;

    invoke-direct {p0, v1}, Lcom/miui/internal/view/menu/MenuPopupHelper;->a(Landroid/widget/ListAdapter;)I

    move-result v1

    iget v2, p0, Lcom/miui/internal/view/menu/MenuPopupHelper;->nv:I

    invoke-static {v1, v2}, Ljava/lang/Math;->min(II)I

    move-result v1

    invoke-virtual {v0, v1}, Lmiui/widget/ListPopupWindow;->setContentWidth(I)V

    .line 212
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuPopupHelper;->nu:Lmiui/widget/ListPopupWindow;

    invoke-virtual {v0}, Lmiui/widget/ListPopupWindow;->show()V

    goto :goto_0
.end method

.method public onItemClick(Landroid/widget/AdapterView;Landroid/view/View;IJ)V
    .locals 3
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Landroid/widget/AdapterView",
            "<*>;",
            "Landroid/view/View;",
            "IJ)V"
        }
    .end annotation

    .prologue
    .line 165
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuPopupHelper;->ny:Lcom/miui/internal/view/menu/MenuPopupHelper$a;

    .line 166
    invoke-static {v0}, Lcom/miui/internal/view/menu/MenuPopupHelper$a;->a(Lcom/miui/internal/view/menu/MenuPopupHelper$a;)Lcom/miui/internal/view/menu/MenuBuilder;

    move-result-object v1

    invoke-virtual {v0, p3}, Lcom/miui/internal/view/menu/MenuPopupHelper$a;->i(I)Lcom/miui/internal/view/menu/MenuItemImpl;

    move-result-object v0

    const/4 v2, 0x0

    invoke-virtual {v1, v0, v2}, Lcom/miui/internal/view/menu/MenuBuilder;->performItemAction(Landroid/view/MenuItem;I)Z

    .line 167
    return-void
.end method

.method public onKey(Landroid/view/View;ILandroid/view/KeyEvent;)Z
    .locals 3

    .prologue
    const/4 v0, 0x1

    const/4 v1, 0x0

    .line 170
    invoke-virtual {p3}, Landroid/view/KeyEvent;->getAction()I

    move-result v2

    if-ne v2, v0, :cond_0

    const/16 v2, 0x52

    if-ne p2, v2, :cond_0

    .line 171
    invoke-virtual {p0, v1}, Lcom/miui/internal/view/menu/MenuPopupHelper;->dismiss(Z)V

    .line 174
    :goto_0
    return v0

    :cond_0
    move v0, v1

    goto :goto_0
.end method

.method public onRestoreInstanceState(Landroid/os/Parcelable;)V
    .locals 0

    .prologue
    .line 304
    return-void
.end method

.method public onSaveInstanceState()Landroid/os/Parcelable;
    .locals 1

    .prologue
    .line 299
    const/4 v0, 0x0

    return-object v0
.end method

.method public onSubMenuSelected(Lcom/miui/internal/view/menu/SubMenuBuilder;)Z
    .locals 7

    .prologue
    const/4 v1, 0x1

    const/4 v2, 0x0

    .line 241
    invoke-virtual {p1}, Lcom/miui/internal/view/menu/SubMenuBuilder;->hasVisibleItems()Z

    move-result v0

    if-eqz v0, :cond_2

    .line 242
    new-instance v3, Lcom/miui/internal/view/menu/MenuPopupHelper;

    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuPopupHelper;->mContext:Landroid/content/Context;

    iget-object v4, p0, Lcom/miui/internal/view/menu/MenuPopupHelper;->kw:Landroid/view/View;

    invoke-direct {v3, v0, p1, v4, v2}, Lcom/miui/internal/view/menu/MenuPopupHelper;-><init>(Landroid/content/Context;Lcom/miui/internal/view/menu/MenuBuilder;Landroid/view/View;Z)V

    .line 243
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuPopupHelper;->nz:Lcom/miui/internal/view/menu/MenuPresenter$Callback;

    invoke-virtual {v3, v0}, Lcom/miui/internal/view/menu/MenuPopupHelper;->setCallback(Lcom/miui/internal/view/menu/MenuPresenter$Callback;)V

    .line 246
    invoke-virtual {p1}, Lcom/miui/internal/view/menu/SubMenuBuilder;->size()I

    move-result v4

    move v0, v2

    .line 247
    :goto_0
    if-ge v0, v4, :cond_3

    .line 248
    invoke-virtual {p1, v0}, Lcom/miui/internal/view/menu/SubMenuBuilder;->getItem(I)Landroid/view/MenuItem;

    move-result-object v5

    .line 249
    invoke-interface {v5}, Landroid/view/MenuItem;->isVisible()Z

    move-result v6

    if-eqz v6, :cond_1

    invoke-interface {v5}, Landroid/view/MenuItem;->getIcon()Landroid/graphics/drawable/Drawable;

    move-result-object v5

    if-eqz v5, :cond_1

    move v0, v1

    .line 254
    :goto_1
    invoke-virtual {v3, v0}, Lcom/miui/internal/view/menu/MenuPopupHelper;->setForceShowIcon(Z)V

    .line 256
    invoke-virtual {v3}, Lcom/miui/internal/view/menu/MenuPopupHelper;->tryShow()Z

    move-result v0

    if-eqz v0, :cond_2

    .line 257
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuPopupHelper;->nz:Lcom/miui/internal/view/menu/MenuPresenter$Callback;

    if-eqz v0, :cond_0

    .line 258
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuPopupHelper;->nz:Lcom/miui/internal/view/menu/MenuPresenter$Callback;

    invoke-interface {v0, p1}, Lcom/miui/internal/view/menu/MenuPresenter$Callback;->onOpenSubMenu(Lcom/miui/internal/view/menu/MenuBuilder;)Z

    .line 263
    :cond_0
    :goto_2
    return v1

    .line 247
    :cond_1
    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    :cond_2
    move v1, v2

    .line 263
    goto :goto_2

    :cond_3
    move v0, v2

    goto :goto_1
.end method

.method public setAnchorView(Landroid/view/View;)V
    .locals 0

    .prologue
    .line 91
    iput-object p1, p0, Lcom/miui/internal/view/menu/MenuPopupHelper;->kw:Landroid/view/View;

    .line 92
    return-void
.end method

.method public setCallback(Lcom/miui/internal/view/menu/MenuPresenter$Callback;)V
    .locals 0

    .prologue
    .line 236
    iput-object p1, p0, Lcom/miui/internal/view/menu/MenuPopupHelper;->nz:Lcom/miui/internal/view/menu/MenuPresenter$Callback;

    .line 237
    return-void
.end method

.method public setForceShowIcon(Z)V
    .locals 0

    .prologue
    .line 95
    iput-boolean p1, p0, Lcom/miui/internal/view/menu/MenuPopupHelper;->nt:Z

    .line 96
    return-void
.end method

.method public setMenuItemLayout(I)V
    .locals 0

    .prologue
    .line 103
    iput p1, p0, Lcom/miui/internal/view/menu/MenuPopupHelper;->nC:I

    .line 104
    return-void
.end method

.method public setVerticalOffset(I)V
    .locals 0

    .prologue
    .line 99
    iput p1, p0, Lcom/miui/internal/view/menu/MenuPopupHelper;->nB:I

    .line 100
    return-void
.end method

.method public show()V
    .locals 2

    .prologue
    .line 107
    invoke-virtual {p0}, Lcom/miui/internal/view/menu/MenuPopupHelper;->tryShow()Z

    move-result v0

    if-nez v0, :cond_0

    .line 108
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "MenuPopupHelper cannot be used without an anchor"

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 110
    :cond_0
    return-void
.end method

.method public tryShow()Z
    .locals 6

    .prologue
    const/4 v0, 0x0

    const/4 v1, 0x1

    .line 113
    new-instance v2, Lmiui/widget/ListPopupWindow;

    iget-object v3, p0, Lcom/miui/internal/view/menu/MenuPopupHelper;->mContext:Landroid/content/Context;

    const/4 v4, 0x0

    const v5, 0x1010300

    invoke-direct {v2, v3, v4, v5}, Lmiui/widget/ListPopupWindow;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    iput-object v2, p0, Lcom/miui/internal/view/menu/MenuPopupHelper;->nu:Lmiui/widget/ListPopupWindow;

    .line 114
    iget-object v2, p0, Lcom/miui/internal/view/menu/MenuPopupHelper;->nu:Lmiui/widget/ListPopupWindow;

    invoke-virtual {v2, p0}, Lmiui/widget/ListPopupWindow;->setOnDismissListener(Landroid/widget/PopupWindow$OnDismissListener;)V

    .line 115
    iget-object v2, p0, Lcom/miui/internal/view/menu/MenuPopupHelper;->nu:Lmiui/widget/ListPopupWindow;

    invoke-virtual {v2, p0}, Lmiui/widget/ListPopupWindow;->setOnItemClickListener(Landroid/widget/AdapterView$OnItemClickListener;)V

    .line 116
    iget-object v2, p0, Lcom/miui/internal/view/menu/MenuPopupHelper;->nu:Lmiui/widget/ListPopupWindow;

    iget v3, p0, Lcom/miui/internal/view/menu/MenuPopupHelper;->nB:I

    invoke-virtual {v2, v3}, Lmiui/widget/ListPopupWindow;->setVerticalOffset(I)V

    .line 118
    new-instance v2, Lcom/miui/internal/view/menu/MenuPopupHelper$a;

    iget-object v3, p0, Lcom/miui/internal/view/menu/MenuPopupHelper;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    invoke-direct {v2, p0, v3}, Lcom/miui/internal/view/menu/MenuPopupHelper$a;-><init>(Lcom/miui/internal/view/menu/MenuPopupHelper;Lcom/miui/internal/view/menu/MenuBuilder;)V

    iput-object v2, p0, Lcom/miui/internal/view/menu/MenuPopupHelper;->ny:Lcom/miui/internal/view/menu/MenuPopupHelper$a;

    .line 119
    iget-object v2, p0, Lcom/miui/internal/view/menu/MenuPopupHelper;->nu:Lmiui/widget/ListPopupWindow;

    iget-object v3, p0, Lcom/miui/internal/view/menu/MenuPopupHelper;->ny:Lcom/miui/internal/view/menu/MenuPopupHelper$a;

    invoke-virtual {v2, v3}, Lmiui/widget/ListPopupWindow;->setAdapter(Landroid/widget/ListAdapter;)V

    .line 120
    iget-object v2, p0, Lcom/miui/internal/view/menu/MenuPopupHelper;->nu:Lmiui/widget/ListPopupWindow;

    invoke-virtual {v2, v1}, Lmiui/widget/ListPopupWindow;->setModal(Z)V

    .line 122
    iget-object v2, p0, Lcom/miui/internal/view/menu/MenuPopupHelper;->kw:Landroid/view/View;

    .line 123
    if-eqz v2, :cond_2

    .line 124
    iget-object v3, p0, Lcom/miui/internal/view/menu/MenuPopupHelper;->nx:Landroid/view/ViewTreeObserver;

    if-nez v3, :cond_0

    move v0, v1

    .line 125
    :cond_0
    invoke-virtual {v2}, Landroid/view/View;->getViewTreeObserver()Landroid/view/ViewTreeObserver;

    move-result-object v3

    iput-object v3, p0, Lcom/miui/internal/view/menu/MenuPopupHelper;->nx:Landroid/view/ViewTreeObserver;

    .line 126
    if-eqz v0, :cond_1

    .line 127
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuPopupHelper;->nx:Landroid/view/ViewTreeObserver;

    invoke-virtual {v0, p0}, Landroid/view/ViewTreeObserver;->addOnGlobalLayoutListener(Landroid/view/ViewTreeObserver$OnGlobalLayoutListener;)V

    .line 129
    :cond_1
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuPopupHelper;->nu:Lmiui/widget/ListPopupWindow;

    invoke-virtual {v0, v2}, Lmiui/widget/ListPopupWindow;->setAnchorView(Landroid/view/View;)V

    .line 134
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuPopupHelper;->nu:Lmiui/widget/ListPopupWindow;

    iget-object v2, p0, Lcom/miui/internal/view/menu/MenuPopupHelper;->ny:Lcom/miui/internal/view/menu/MenuPopupHelper$a;

    invoke-direct {p0, v2}, Lcom/miui/internal/view/menu/MenuPopupHelper;->a(Landroid/widget/ListAdapter;)I

    move-result v2

    iget v3, p0, Lcom/miui/internal/view/menu/MenuPopupHelper;->nv:I

    invoke-static {v2, v3}, Ljava/lang/Math;->min(II)I

    move-result v2

    invoke-virtual {v0, v2}, Lmiui/widget/ListPopupWindow;->setContentWidth(I)V

    .line 135
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuPopupHelper;->nu:Lmiui/widget/ListPopupWindow;

    const/4 v2, 0x2

    invoke-virtual {v0, v2}, Lmiui/widget/ListPopupWindow;->setInputMethodMode(I)V

    .line 136
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuPopupHelper;->nu:Lmiui/widget/ListPopupWindow;

    invoke-virtual {v0}, Lmiui/widget/ListPopupWindow;->show()V

    .line 137
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuPopupHelper;->nu:Lmiui/widget/ListPopupWindow;

    invoke-virtual {v0}, Lmiui/widget/ListPopupWindow;->getListView()Landroid/widget/ListView;

    move-result-object v0

    invoke-virtual {v0, p0}, Landroid/widget/ListView;->setOnKeyListener(Landroid/view/View$OnKeyListener;)V

    .line 138
    :goto_0
    return v1

    :cond_2
    move v1, v0

    .line 131
    goto :goto_0
.end method

.method public updateMenuView(Z)V
    .locals 1

    .prologue
    .line 229
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuPopupHelper;->ny:Lcom/miui/internal/view/menu/MenuPopupHelper$a;

    if-eqz v0, :cond_0

    .line 230
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuPopupHelper;->ny:Lcom/miui/internal/view/menu/MenuPopupHelper$a;

    invoke-virtual {v0}, Lcom/miui/internal/view/menu/MenuPopupHelper$a;->notifyDataSetChanged()V

    .line 232
    :cond_0
    return-void
.end method
