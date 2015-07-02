.class public final Lcom/miui/internal/view/menu/MenuItemImpl;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroid/view/MenuItem;


# static fields
.field private static final ENABLED:I = 0x10

.field private static final OL:I = 0x1

.field private static final ON:I = 0x2

.field private static final OO:I = 0x4

.field private static final OP:I = 0x8

.field private static final Rk:I = 0x3

.field private static final Ro:I = 0x20

.field static final Ru:I = 0x0

.field private static Rw:Ljava/lang/String; = null

.field private static Rx:Ljava/lang/String; = null

.field private static Ry:Ljava/lang/String; = null

.field private static Rz:Ljava/lang/String; = null

.field private static final TAG:Ljava/lang/String; = "MenuItemImpl"


# instance fields
.field private final OC:I

.field private final OD:I

.field private final OE:I

.field private OF:Ljava/lang/CharSequence;

.field private OG:Landroid/content/Intent;

.field private OH:C

.field private OI:C

.field private OJ:Landroid/graphics/drawable/Drawable;

.field private OK:Landroid/view/MenuItem$OnMenuItemClickListener;

.field private Rl:I

.field private Rm:Lcom/miui/internal/view/menu/SubMenuBuilder;

.field private Rn:Ljava/lang/Runnable;

.field private Rp:I

.field private Rq:Landroid/view/View;

.field private Rr:Landroid/view/ActionProvider;

.field private Rs:Landroid/view/MenuItem$OnActionExpandListener;

.field private Rt:Z

.field private Rv:Landroid/view/ContextMenu$ContextMenuInfo;

.field private aZ:I

.field private final ko:I

.field private mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

.field private mTitle:Ljava/lang/CharSequence;


# direct methods
.method constructor <init>(Lcom/miui/internal/view/menu/MenuBuilder;IIIILjava/lang/CharSequence;I)V
    .locals 2

    .prologue
    const/4 v1, 0x0

    .line 108
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 49
    iput v1, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->Rl:I

    .line 64
    const/16 v0, 0x10

    iput v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->aZ:I

    .line 72
    iput v1, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->Rp:I

    .line 77
    iput-boolean v1, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->Rt:Z

    .line 122
    iput-object p1, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    .line 123
    iput p3, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->ko:I

    .line 124
    iput p2, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->OC:I

    .line 125
    iput p4, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->OD:I

    .line 126
    iput p5, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->OE:I

    .line 127
    iput-object p6, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->mTitle:Ljava/lang/CharSequence;

    .line 128
    iput p7, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->Rp:I

    .line 129
    return-void
.end method

.method static synthetic c(Lcom/miui/internal/view/menu/MenuItemImpl;)Lcom/miui/internal/view/menu/MenuBuilder;
    .locals 1

    .prologue
    .line 24
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    return-object v0
.end method


# virtual methods
.method a(Lcom/miui/internal/view/menu/MenuView$ItemView;)Ljava/lang/CharSequence;
    .locals 1

    .prologue
    .line 348
    if-eqz p1, :cond_0

    invoke-interface {p1}, Lcom/miui/internal/view/menu/MenuView$ItemView;->prefersCondensedTitle()Z

    move-result v0

    if-eqz v0, :cond_0

    invoke-virtual {p0}, Lcom/miui/internal/view/menu/MenuItemImpl;->getTitleCondensed()Ljava/lang/CharSequence;

    move-result-object v0

    :goto_0
    return-object v0

    :cond_0
    invoke-virtual {p0}, Lcom/miui/internal/view/menu/MenuItemImpl;->getTitle()Ljava/lang/CharSequence;

    move-result-object v0

    goto :goto_0
.end method

.method a(Landroid/view/ContextMenu$ContextMenuInfo;)V
    .locals 0

    .prologue
    .line 513
    iput-object p1, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->Rv:Landroid/view/ContextMenu$ContextMenuInfo;

    .line 514
    return-void
.end method

.method public actionFormatChanged()V
    .locals 1

    .prologue
    .line 522
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    invoke-virtual {v0, p0}, Lcom/miui/internal/view/menu/MenuBuilder;->b(Lcom/miui/internal/view/menu/MenuItemImpl;)V

    .line 523
    return-void
.end method

.method b(Lcom/miui/internal/view/menu/SubMenuBuilder;)V
    .locals 1

    .prologue
    .line 330
    iput-object p1, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->Rm:Lcom/miui/internal/view/menu/SubMenuBuilder;

    .line 332
    invoke-virtual {p0}, Lcom/miui/internal/view/menu/MenuItemImpl;->getTitle()Ljava/lang/CharSequence;

    move-result-object v0

    invoke-virtual {p1, v0}, Lcom/miui/internal/view/menu/SubMenuBuilder;->setHeaderTitle(Ljava/lang/CharSequence;)Landroid/view/SubMenu;

    .line 333
    return-void
.end method

.method public collapseActionView()Z
    .locals 1

    .prologue
    .line 658
    iget v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->Rp:I

    and-int/lit8 v0, v0, 0x8

    if-eqz v0, :cond_2

    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->Rq:Landroid/view/View;

    if-eqz v0, :cond_1

    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->Rs:Landroid/view/MenuItem$OnActionExpandListener;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->Rs:Landroid/view/MenuItem$OnActionExpandListener;

    invoke-interface {v0, p0}, Landroid/view/MenuItem$OnActionExpandListener;->onMenuItemActionCollapse(Landroid/view/MenuItem;)Z

    move-result v0

    if-eqz v0, :cond_2

    :cond_0
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    invoke-virtual {v0, p0}, Lcom/miui/internal/view/menu/MenuBuilder;->collapseItemActionView(Lcom/miui/internal/view/menu/MenuItemImpl;)Z

    move-result v0

    if-eqz v0, :cond_2

    :cond_1
    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_2
    const/4 v0, 0x0

    goto :goto_0
.end method

.method eR()C
    .locals 1

    .prologue
    .line 272
    iget-char v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->OI:C

    return v0
.end method

.method eS()Ljava/lang/String;
    .locals 3

    .prologue
    .line 282
    invoke-virtual {p0}, Lcom/miui/internal/view/menu/MenuItemImpl;->eR()C

    move-result v0

    .line 283
    if-nez v0, :cond_0

    .line 284
    const-string v0, ""

    .line 307
    :goto_0
    return-object v0

    .line 287
    :cond_0
    new-instance v1, Ljava/lang/StringBuilder;

    sget-object v2, Lcom/miui/internal/view/menu/MenuItemImpl;->Rw:Ljava/lang/String;

    invoke-direct {v1, v2}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    .line 288
    sparse-switch v0, :sswitch_data_0

    .line 303
    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    .line 307
    :goto_1
    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    goto :goto_0

    .line 291
    :sswitch_0
    sget-object v0, Lcom/miui/internal/view/menu/MenuItemImpl;->Rx:Ljava/lang/String;

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    goto :goto_1

    .line 295
    :sswitch_1
    sget-object v0, Lcom/miui/internal/view/menu/MenuItemImpl;->Ry:Ljava/lang/String;

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    goto :goto_1

    .line 299
    :sswitch_2
    sget-object v0, Lcom/miui/internal/view/menu/MenuItemImpl;->Rz:Ljava/lang/String;

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    goto :goto_1

    .line 288
    nop

    :sswitch_data_0
    .sparse-switch
        0x8 -> :sswitch_1
        0xa -> :sswitch_0
        0x20 -> :sswitch_2
    .end sparse-switch
.end method

.method eT()Z
    .locals 1

    .prologue
    .line 316
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    invoke-virtual {v0}, Lcom/miui/internal/view/menu/MenuBuilder;->isShortcutsVisible()Z

    move-result v0

    if-eqz v0, :cond_0

    invoke-virtual {p0}, Lcom/miui/internal/view/menu/MenuItemImpl;->eR()C

    move-result v0

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public expandActionView()Z
    .locals 1

    .prologue
    .line 651
    iget v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->Rp:I

    and-int/lit8 v0, v0, 0x8

    if-eqz v0, :cond_1

    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->Rq:Landroid/view/View;

    if-eqz v0, :cond_1

    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->Rs:Landroid/view/MenuItem$OnActionExpandListener;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->Rs:Landroid/view/MenuItem$OnActionExpandListener;

    invoke-interface {v0, p0}, Landroid/view/MenuItem$OnActionExpandListener;->onMenuItemActionExpand(Landroid/view/MenuItem;)Z

    move-result v0

    if-eqz v0, :cond_1

    :cond_0
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    invoke-virtual {v0, p0}, Lcom/miui/internal/view/menu/MenuBuilder;->expandItemActionView(Lcom/miui/internal/view/menu/MenuItemImpl;)Z

    move-result v0

    if-eqz v0, :cond_1

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_1
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public getActionProvider()Landroid/view/ActionProvider;
    .locals 2

    .prologue
    .line 613
    new-instance v0, Ljava/lang/UnsupportedOperationException;

    const-string v1, "Implementation should use getSupportActionProvider!"

    invoke-direct {v0, v1}, Ljava/lang/UnsupportedOperationException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

.method public getActionView()Landroid/view/View;
    .locals 1

    .prologue
    .line 595
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->Rq:Landroid/view/View;

    if-eqz v0, :cond_0

    .line 596
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->Rq:Landroid/view/View;

    .line 601
    :goto_0
    return-object v0

    .line 597
    :cond_0
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->Rr:Landroid/view/ActionProvider;

    if-eqz v0, :cond_1

    .line 598
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->Rr:Landroid/view/ActionProvider;

    invoke-virtual {v0, p0}, Landroid/view/ActionProvider;->onCreateActionView(Landroid/view/MenuItem;)Landroid/view/View;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->Rq:Landroid/view/View;

    .line 599
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->Rq:Landroid/view/View;

    goto :goto_0

    .line 601
    :cond_1
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public getAlphabeticShortcut()C
    .locals 1

    .prologue
    .line 223
    iget-char v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->OI:C

    return v0
.end method

.method getCallback()Ljava/lang/Runnable;
    .locals 1

    .prologue
    .line 213
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->Rn:Ljava/lang/Runnable;

    return-object v0
.end method

.method public getGroupId()I
    .locals 1

    .prologue
    .line 183
    iget v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->OC:I

    return v0
.end method

.method public getIcon()Landroid/graphics/drawable/Drawable;
    .locals 2

    .prologue
    .line 385
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->OJ:Landroid/graphics/drawable/Drawable;

    if-eqz v0, :cond_0

    .line 386
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->OJ:Landroid/graphics/drawable/Drawable;

    .line 396
    :goto_0
    return-object v0

    .line 389
    :cond_0
    iget v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->Rl:I

    if-eqz v0, :cond_1

    .line 390
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    invoke-virtual {v0}, Lcom/miui/internal/view/menu/MenuBuilder;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    iget v1, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->Rl:I

    invoke-virtual {v0, v1}, Landroid/content/res/Resources;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v0

    .line 391
    const/4 v1, 0x0

    iput v1, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->Rl:I

    .line 392
    iput-object v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->OJ:Landroid/graphics/drawable/Drawable;

    goto :goto_0

    .line 396
    :cond_1
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public getIntent()Landroid/content/Intent;
    .locals 1

    .prologue
    .line 203
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->OG:Landroid/content/Intent;

    return-object v0
.end method

.method public getItemId()I
    .locals 1
    .annotation runtime Landroid/view/ViewDebug$CapturedViewProperty;
    .end annotation

    .prologue
    .line 189
    iget v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->ko:I

    return v0
.end method

.method public getMenuInfo()Landroid/view/ContextMenu$ContextMenuInfo;
    .locals 1

    .prologue
    .line 518
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->Rv:Landroid/view/ContextMenu$ContextMenuInfo;

    return-object v0
.end method

.method public getNumericShortcut()C
    .locals 1

    .prologue
    .line 241
    iget-char v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->OH:C

    return v0
.end method

.method public getOrder()I
    .locals 1

    .prologue
    .line 194
    iget v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->OD:I

    return v0
.end method

.method public getOrdering()I
    .locals 1

    .prologue
    .line 198
    iget v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->OE:I

    return v0
.end method

.method public getSubMenu()Landroid/view/SubMenu;
    .locals 1

    .prologue
    .line 321
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->Rm:Lcom/miui/internal/view/menu/SubMenuBuilder;

    return-object v0
.end method

.method public getSupportActionProvider()Landroid/view/ActionProvider;
    .locals 1

    .prologue
    .line 618
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->Rr:Landroid/view/ActionProvider;

    return-object v0
.end method

.method public getTitle()Ljava/lang/CharSequence;
    .locals 1
    .annotation runtime Landroid/view/ViewDebug$CapturedViewProperty;
    .end annotation

    .prologue
    .line 338
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->mTitle:Ljava/lang/CharSequence;

    return-object v0
.end method

.method public getTitleCondensed()Ljava/lang/CharSequence;
    .locals 1

    .prologue
    .line 373
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->OF:Ljava/lang/CharSequence;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->OF:Ljava/lang/CharSequence;

    :goto_0
    return-object v0

    :cond_0
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->mTitle:Ljava/lang/CharSequence;

    goto :goto_0
.end method

.method public hasCollapsibleActionView()Z
    .locals 1

    .prologue
    .line 669
    iget v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->Rp:I

    and-int/lit8 v0, v0, 0x8

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->Rq:Landroid/view/View;

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public hasSubMenu()Z
    .locals 1

    .prologue
    .line 326
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->Rm:Lcom/miui/internal/view/menu/SubMenuBuilder;

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public invoke()Z
    .locals 4

    .prologue
    const/4 v0, 0x1

    .line 137
    iget-object v1, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->OK:Landroid/view/MenuItem$OnMenuItemClickListener;

    if-eqz v1, :cond_1

    iget-object v1, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->OK:Landroid/view/MenuItem$OnMenuItemClickListener;

    invoke-interface {v1, p0}, Landroid/view/MenuItem$OnMenuItemClickListener;->onMenuItemClick(Landroid/view/MenuItem;)Z

    move-result v1

    if-eqz v1, :cond_1

    .line 160
    :cond_0
    :goto_0
    return v0

    .line 142
    :cond_1
    iget-object v1, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    iget-object v2, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    invoke-virtual {v2}, Lcom/miui/internal/view/menu/MenuBuilder;->getRootMenu()Lcom/miui/internal/view/menu/MenuBuilder;

    move-result-object v2

    invoke-virtual {v1, v2, p0}, Lcom/miui/internal/view/menu/MenuBuilder;->dispatchMenuItemSelected(Lcom/miui/internal/view/menu/MenuBuilder;Landroid/view/MenuItem;)Z

    move-result v1

    if-nez v1, :cond_0

    .line 146
    iget-object v1, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->Rn:Ljava/lang/Runnable;

    if-eqz v1, :cond_2

    .line 147
    iget-object v1, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->Rn:Ljava/lang/Runnable;

    invoke-interface {v1}, Ljava/lang/Runnable;->run()V

    goto :goto_0

    .line 151
    :cond_2
    iget-object v1, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->OG:Landroid/content/Intent;

    if-eqz v1, :cond_3

    .line 153
    :try_start_0
    iget-object v1, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    invoke-virtual {v1}, Lcom/miui/internal/view/menu/MenuBuilder;->getContext()Landroid/content/Context;

    move-result-object v1

    iget-object v2, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->OG:Landroid/content/Intent;

    invoke-virtual {v1, v2}, Landroid/content/Context;->startActivity(Landroid/content/Intent;)V
    :try_end_0
    .catch Landroid/content/ActivityNotFoundException; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    .line 155
    :catch_0
    move-exception v1

    .line 156
    const-string v2, "MenuItemImpl"

    const-string v3, "Can\'t find activity to handle intent; ignoring"

    invoke-static {v2, v3, v1}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    .line 160
    :cond_3
    iget-object v1, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->Rr:Landroid/view/ActionProvider;

    if-eqz v1, :cond_4

    iget-object v1, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->Rr:Landroid/view/ActionProvider;

    invoke-virtual {v1}, Landroid/view/ActionProvider;->onPerformDefaultAction()Z

    move-result v1

    if-nez v1, :cond_0

    :cond_4
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public isActionButton()Z
    .locals 2

    .prologue
    .line 533
    iget v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->aZ:I

    and-int/lit8 v0, v0, 0x20

    const/16 v1, 0x20

    if-ne v0, v1, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public isActionViewExpanded()Z
    .locals 1

    .prologue
    .line 679
    iget-boolean v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->Rt:Z

    return v0
.end method

.method public isCheckable()Z
    .locals 2

    .prologue
    const/4 v0, 0x1

    .line 421
    iget v1, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->aZ:I

    and-int/lit8 v1, v1, 0x1

    if-ne v1, v0, :cond_0

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public isChecked()Z
    .locals 2

    .prologue
    .line 445
    iget v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->aZ:I

    and-int/lit8 v0, v0, 0x2

    const/4 v1, 0x2

    if-ne v0, v1, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public isEnabled()Z
    .locals 1

    .prologue
    .line 165
    iget v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->aZ:I

    and-int/lit8 v0, v0, 0x10

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public isExclusiveCheckable()Z
    .locals 1

    .prologue
    .line 440
    iget v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->aZ:I

    and-int/lit8 v0, v0, 0x4

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public isVisible()Z
    .locals 3

    .prologue
    const/4 v0, 0x1

    const/4 v1, 0x0

    .line 471
    iget-object v2, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->Rr:Landroid/view/ActionProvider;

    if-eqz v2, :cond_2

    iget-object v2, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->Rr:Landroid/view/ActionProvider;

    invoke-virtual {v2}, Landroid/view/ActionProvider;->overridesItemVisibility()Z

    move-result v2

    if-eqz v2, :cond_2

    .line 472
    iget v2, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->aZ:I

    and-int/lit8 v2, v2, 0x8

    if-nez v2, :cond_1

    iget-object v2, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->Rr:Landroid/view/ActionProvider;

    invoke-virtual {v2}, Landroid/view/ActionProvider;->isVisible()Z

    move-result v2

    if-eqz v2, :cond_1

    .line 474
    :cond_0
    :goto_0
    return v0

    :cond_1
    move v0, v1

    .line 472
    goto :goto_0

    .line 474
    :cond_2
    iget v2, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->aZ:I

    and-int/lit8 v2, v2, 0x8

    if-eqz v2, :cond_0

    move v0, v1

    goto :goto_0
.end method

.method public requestsActionButton()Z
    .locals 2

    .prologue
    const/4 v0, 0x1

    .line 537
    iget v1, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->Rp:I

    and-int/lit8 v1, v1, 0x1

    if-ne v1, v0, :cond_0

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public requiresActionButton()Z
    .locals 2

    .prologue
    .line 541
    iget v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->Rp:I

    and-int/lit8 v0, v0, 0x2

    const/4 v1, 0x2

    if-ne v0, v1, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public setActionProvider(Landroid/view/ActionProvider;)Landroid/view/MenuItem;
    .locals 2

    .prologue
    .line 607
    new-instance v0, Ljava/lang/UnsupportedOperationException;

    const-string v1, "Implementation should use setSupportActionProvider!"

    invoke-direct {v0, v1}, Ljava/lang/UnsupportedOperationException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

.method public setActionView(I)Landroid/view/MenuItem;
    .locals 3

    .prologue
    .line 587
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    invoke-virtual {v0}, Lcom/miui/internal/view/menu/MenuBuilder;->getContext()Landroid/content/Context;

    move-result-object v0

    .line 588
    invoke-static {v0}, Landroid/view/LayoutInflater;->from(Landroid/content/Context;)Landroid/view/LayoutInflater;

    move-result-object v1

    .line 589
    new-instance v2, Landroid/widget/LinearLayout;

    invoke-direct {v2, v0}, Landroid/widget/LinearLayout;-><init>(Landroid/content/Context;)V

    const/4 v0, 0x0

    invoke-virtual {v1, p1, v2, v0}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;Z)Landroid/view/View;

    move-result-object v0

    invoke-virtual {p0, v0}, Lcom/miui/internal/view/menu/MenuItemImpl;->setActionView(Landroid/view/View;)Landroid/view/MenuItem;

    .line 590
    return-object p0
.end method

.method public setActionView(Landroid/view/View;)Landroid/view/MenuItem;
    .locals 2

    .prologue
    .line 576
    iput-object p1, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->Rq:Landroid/view/View;

    .line 577
    const/4 v0, 0x0

    iput-object v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->Rr:Landroid/view/ActionProvider;

    .line 578
    if-eqz p1, :cond_0

    invoke-virtual {p1}, Landroid/view/View;->getId()I

    move-result v0

    const/4 v1, -0x1

    if-ne v0, v1, :cond_0

    iget v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->ko:I

    if-lez v0, :cond_0

    .line 579
    iget v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->ko:I

    invoke-virtual {p1, v0}, Landroid/view/View;->setId(I)V

    .line 581
    :cond_0
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    invoke-virtual {v0, p0}, Lcom/miui/internal/view/menu/MenuBuilder;->b(Lcom/miui/internal/view/menu/MenuItemImpl;)V

    .line 582
    return-object p0
.end method

.method public setActionViewExpanded(Z)V
    .locals 2

    .prologue
    .line 673
    iput-boolean p1, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->Rt:Z

    .line 674
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Lcom/miui/internal/view/menu/MenuBuilder;->d(Z)V

    .line 675
    return-void
.end method

.method public setAlphabeticShortcut(C)Landroid/view/MenuItem;
    .locals 2

    .prologue
    .line 228
    iget-char v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->OI:C

    if-ne v0, p1, :cond_0

    .line 236
    :goto_0
    return-object p0

    .line 232
    :cond_0
    invoke-static {p1}, Ljava/lang/Character;->toLowerCase(C)C

    move-result v0

    iput-char v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->OI:C

    .line 234
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Lcom/miui/internal/view/menu/MenuBuilder;->d(Z)V

    goto :goto_0
.end method

.method public setCallback(Ljava/lang/Runnable;)Landroid/view/MenuItem;
    .locals 0

    .prologue
    .line 217
    iput-object p1, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->Rn:Ljava/lang/Runnable;

    .line 218
    return-object p0
.end method

.method public setCheckable(Z)Landroid/view/MenuItem;
    .locals 4

    .prologue
    const/4 v1, 0x0

    .line 426
    iget v2, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->aZ:I

    .line 427
    iget v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->aZ:I

    and-int/lit8 v3, v0, -0x2

    if-eqz p1, :cond_1

    const/4 v0, 0x1

    :goto_0
    or-int/2addr v0, v3

    iput v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->aZ:I

    .line 428
    iget v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->aZ:I

    if-eq v2, v0, :cond_0

    .line 429
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    invoke-virtual {v0, v1}, Lcom/miui/internal/view/menu/MenuBuilder;->d(Z)V

    .line 432
    :cond_0
    return-object p0

    :cond_1
    move v0, v1

    .line 427
    goto :goto_0
.end method

.method public setChecked(Z)Landroid/view/MenuItem;
    .locals 1

    .prologue
    .line 450
    iget v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->aZ:I

    and-int/lit8 v0, v0, 0x4

    if-eqz v0, :cond_0

    .line 453
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    invoke-virtual {v0, p0}, Lcom/miui/internal/view/menu/MenuBuilder;->a(Landroid/view/MenuItem;)V

    .line 458
    :goto_0
    return-object p0

    .line 455
    :cond_0
    invoke-virtual {p0, p1}, Lcom/miui/internal/view/menu/MenuItemImpl;->v(Z)V

    goto :goto_0
.end method

.method public setEnabled(Z)Landroid/view/MenuItem;
    .locals 2

    .prologue
    .line 170
    if-eqz p1, :cond_0

    .line 171
    iget v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->aZ:I

    or-int/lit8 v0, v0, 0x10

    iput v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->aZ:I

    .line 176
    :goto_0
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Lcom/miui/internal/view/menu/MenuBuilder;->d(Z)V

    .line 178
    return-object p0

    .line 173
    :cond_0
    iget v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->aZ:I

    and-int/lit8 v0, v0, -0x11

    iput v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->aZ:I

    goto :goto_0
.end method

.method public setExclusiveCheckable(Z)V
    .locals 2

    .prologue
    .line 436
    iget v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->aZ:I

    and-int/lit8 v1, v0, -0x5

    if-eqz p1, :cond_0

    const/4 v0, 0x4

    :goto_0
    or-int/2addr v0, v1

    iput v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->aZ:I

    .line 437
    return-void

    .line 436
    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public setIcon(I)Landroid/view/MenuItem;
    .locals 2

    .prologue
    .line 410
    const/4 v0, 0x0

    iput-object v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->OJ:Landroid/graphics/drawable/Drawable;

    .line 411
    iput p1, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->Rl:I

    .line 414
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Lcom/miui/internal/view/menu/MenuBuilder;->d(Z)V

    .line 416
    return-object p0
.end method

.method public setIcon(Landroid/graphics/drawable/Drawable;)Landroid/view/MenuItem;
    .locals 2

    .prologue
    const/4 v1, 0x0

    .line 401
    iput v1, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->Rl:I

    .line 402
    iput-object p1, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->OJ:Landroid/graphics/drawable/Drawable;

    .line 403
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    invoke-virtual {v0, v1}, Lcom/miui/internal/view/menu/MenuBuilder;->d(Z)V

    .line 405
    return-object p0
.end method

.method public setIntent(Landroid/content/Intent;)Landroid/view/MenuItem;
    .locals 0

    .prologue
    .line 208
    iput-object p1, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->OG:Landroid/content/Intent;

    .line 209
    return-object p0
.end method

.method public setIsActionButton(Z)V
    .locals 1

    .prologue
    .line 545
    if-eqz p1, :cond_0

    .line 546
    iget v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->aZ:I

    or-int/lit8 v0, v0, 0x20

    iput v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->aZ:I

    .line 550
    :goto_0
    return-void

    .line 548
    :cond_0
    iget v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->aZ:I

    and-int/lit8 v0, v0, -0x21

    iput v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->aZ:I

    goto :goto_0
.end method

.method public setNumericShortcut(C)Landroid/view/MenuItem;
    .locals 2

    .prologue
    .line 246
    iget-char v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->OH:C

    if-ne v0, p1, :cond_0

    .line 254
    :goto_0
    return-object p0

    .line 250
    :cond_0
    iput-char p1, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->OH:C

    .line 252
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Lcom/miui/internal/view/menu/MenuBuilder;->d(Z)V

    goto :goto_0
.end method

.method public setOnActionExpandListener(Landroid/view/MenuItem$OnActionExpandListener;)Landroid/view/MenuItem;
    .locals 2

    .prologue
    .line 684
    new-instance v0, Ljava/lang/UnsupportedOperationException;

    const-string v1, "Implementation should use setSupportOnActionExpandListener!"

    invoke-direct {v0, v1}, Ljava/lang/UnsupportedOperationException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

.method public setOnMenuItemClickListener(Landroid/view/MenuItem$OnMenuItemClickListener;)Landroid/view/MenuItem;
    .locals 0

    .prologue
    .line 503
    iput-object p1, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->OK:Landroid/view/MenuItem$OnMenuItemClickListener;

    .line 504
    return-object p0
.end method

.method public setShortcut(CC)Landroid/view/MenuItem;
    .locals 2

    .prologue
    .line 259
    iput-char p1, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->OH:C

    .line 260
    invoke-static {p2}, Ljava/lang/Character;->toLowerCase(C)C

    move-result v0

    iput-char v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->OI:C

    .line 262
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Lcom/miui/internal/view/menu/MenuBuilder;->d(Z)V

    .line 264
    return-object p0
.end method

.method public setShowAsAction(I)V
    .locals 2

    .prologue
    .line 558
    and-int/lit8 v0, p1, 0x3

    packed-switch v0, :pswitch_data_0

    .line 567
    new-instance v0, Ljava/lang/IllegalArgumentException;

    const-string v1, "SHOW_AS_ACTION_ALWAYS, SHOW_AS_ACTION_IF_ROOM, and SHOW_AS_ACTION_NEVER are mutually exclusive."

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 570
    :pswitch_0
    iput p1, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->Rp:I

    .line 571
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    invoke-virtual {v0, p0}, Lcom/miui/internal/view/menu/MenuBuilder;->b(Lcom/miui/internal/view/menu/MenuItemImpl;)V

    .line 572
    return-void

    .line 558
    nop

    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_0
        :pswitch_0
        :pswitch_0
    .end packed-switch
.end method

.method public setShowAsActionFlags(I)Landroid/view/MenuItem;
    .locals 0

    .prologue
    .line 645
    invoke-virtual {p0, p1}, Lcom/miui/internal/view/menu/MenuItemImpl;->setShowAsAction(I)V

    .line 646
    return-object p0
.end method

.method public setSupportActionProvider(Landroid/view/ActionProvider;)Landroid/view/MenuItem;
    .locals 2

    .prologue
    const/4 v1, 0x0

    .line 622
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->Rr:Landroid/view/ActionProvider;

    if-ne v0, p1, :cond_1

    .line 640
    :cond_0
    :goto_0
    return-object p0

    .line 626
    :cond_1
    iput-object v1, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->Rq:Landroid/view/View;

    .line 627
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->Rr:Landroid/view/ActionProvider;

    if-eqz v0, :cond_2

    .line 628
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->Rr:Landroid/view/ActionProvider;

    invoke-virtual {v0, v1}, Landroid/view/ActionProvider;->setVisibilityListener(Landroid/view/ActionProvider$VisibilityListener;)V

    .line 630
    :cond_2
    iput-object p1, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->Rr:Landroid/view/ActionProvider;

    .line 631
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Lcom/miui/internal/view/menu/MenuBuilder;->d(Z)V

    .line 632
    if-eqz p1, :cond_0

    .line 633
    new-instance v0, Lcom/miui/internal/view/menu/MenuItemImpl$1;

    invoke-direct {v0, p0}, Lcom/miui/internal/view/menu/MenuItemImpl$1;-><init>(Lcom/miui/internal/view/menu/MenuItemImpl;)V

    invoke-virtual {p1, v0}, Landroid/view/ActionProvider;->setVisibilityListener(Landroid/view/ActionProvider$VisibilityListener;)V

    goto :goto_0
.end method

.method public setSupportOnActionExpandListener(Landroid/view/MenuItem$OnActionExpandListener;)Landroid/view/MenuItem;
    .locals 0

    .prologue
    .line 664
    iput-object p1, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->Rs:Landroid/view/MenuItem$OnActionExpandListener;

    .line 665
    return-object p0
.end method

.method public setTitle(I)Landroid/view/MenuItem;
    .locals 1

    .prologue
    .line 368
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    invoke-virtual {v0}, Lcom/miui/internal/view/menu/MenuBuilder;->getContext()Landroid/content/Context;

    move-result-object v0

    invoke-virtual {v0, p1}, Landroid/content/Context;->getString(I)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, v0}, Lcom/miui/internal/view/menu/MenuItemImpl;->setTitle(Ljava/lang/CharSequence;)Landroid/view/MenuItem;

    move-result-object v0

    return-object v0
.end method

.method public setTitle(Ljava/lang/CharSequence;)Landroid/view/MenuItem;
    .locals 2

    .prologue
    .line 355
    iput-object p1, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->mTitle:Ljava/lang/CharSequence;

    .line 357
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Lcom/miui/internal/view/menu/MenuBuilder;->d(Z)V

    .line 359
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->Rm:Lcom/miui/internal/view/menu/SubMenuBuilder;

    if-eqz v0, :cond_0

    .line 360
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->Rm:Lcom/miui/internal/view/menu/SubMenuBuilder;

    invoke-virtual {v0, p1}, Lcom/miui/internal/view/menu/SubMenuBuilder;->setHeaderTitle(Ljava/lang/CharSequence;)Landroid/view/SubMenu;

    .line 363
    :cond_0
    return-object p0
.end method

.method public setTitleCondensed(Ljava/lang/CharSequence;)Landroid/view/MenuItem;
    .locals 2

    .prologue
    .line 378
    iput-object p1, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->OF:Ljava/lang/CharSequence;

    .line 379
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Lcom/miui/internal/view/menu/MenuBuilder;->d(Z)V

    .line 380
    return-object p0
.end method

.method public setVisible(Z)Landroid/view/MenuItem;
    .locals 1

    .prologue
    .line 496
    invoke-virtual {p0, p1}, Lcom/miui/internal/view/menu/MenuItemImpl;->w(Z)Z

    move-result v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    invoke-virtual {v0, p0}, Lcom/miui/internal/view/menu/MenuBuilder;->a(Lcom/miui/internal/view/menu/MenuItemImpl;)V

    .line 498
    :cond_0
    return-object p0
.end method

.method public shouldShowIcon()Z
    .locals 1

    .prologue
    .line 529
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    invoke-virtual {v0}, Lcom/miui/internal/view/menu/MenuBuilder;->e()Z

    move-result v0

    return v0
.end method

.method public showsTextAsAction()Z
    .locals 2

    .prologue
    .line 553
    iget v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->Rp:I

    and-int/lit8 v0, v0, 0x4

    const/4 v1, 0x4

    if-ne v0, v1, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public toString()Ljava/lang/String;
    .locals 1

    .prologue
    .line 509
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->mTitle:Ljava/lang/CharSequence;

    invoke-virtual {v0}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method v(Z)V
    .locals 4

    .prologue
    const/4 v1, 0x0

    .line 462
    iget v2, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->aZ:I

    .line 463
    iget v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->aZ:I

    and-int/lit8 v3, v0, -0x3

    if-eqz p1, :cond_1

    const/4 v0, 0x2

    :goto_0
    or-int/2addr v0, v3

    iput v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->aZ:I

    .line 464
    iget v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->aZ:I

    if-eq v2, v0, :cond_0

    .line 465
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    invoke-virtual {v0, v1}, Lcom/miui/internal/view/menu/MenuBuilder;->d(Z)V

    .line 467
    :cond_0
    return-void

    :cond_1
    move v0, v1

    .line 463
    goto :goto_0
.end method

.method w(Z)Z
    .locals 4

    .prologue
    const/4 v1, 0x0

    .line 486
    iget v2, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->aZ:I

    .line 487
    iget v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->aZ:I

    and-int/lit8 v3, v0, -0x9

    if-eqz p1, :cond_1

    move v0, v1

    :goto_0
    or-int/2addr v0, v3

    iput v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->aZ:I

    .line 488
    iget v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl;->aZ:I

    if-eq v2, v0, :cond_0

    const/4 v1, 0x1

    :cond_0
    return v1

    .line 487
    :cond_1
    const/16 v0, 0x8

    goto :goto_0
.end method
