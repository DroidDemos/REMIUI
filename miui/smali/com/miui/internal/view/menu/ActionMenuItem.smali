.class public Lcom/miui/internal/view/menu/ActionMenuItem;
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

.field private aZ:I

.field private final ko:I

.field private mContext:Landroid/content/Context;

.field private mTitle:Ljava/lang/CharSequence;


# direct methods
.method public constructor <init>(Landroid/content/Context;IIIILjava/lang/CharSequence;)V
    .locals 1

    .prologue
    .line 46
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 38
    const/16 v0, 0x10

    iput v0, p0, Lcom/miui/internal/view/menu/ActionMenuItem;->aZ:I

    .line 47
    iput-object p1, p0, Lcom/miui/internal/view/menu/ActionMenuItem;->mContext:Landroid/content/Context;

    .line 48
    iput p3, p0, Lcom/miui/internal/view/menu/ActionMenuItem;->ko:I

    .line 49
    iput p2, p0, Lcom/miui/internal/view/menu/ActionMenuItem;->OC:I

    .line 50
    iput p4, p0, Lcom/miui/internal/view/menu/ActionMenuItem;->OD:I

    .line 51
    iput p5, p0, Lcom/miui/internal/view/menu/ActionMenuItem;->OE:I

    .line 52
    iput-object p6, p0, Lcom/miui/internal/view/menu/ActionMenuItem;->mTitle:Ljava/lang/CharSequence;

    .line 53
    return-void
.end method


# virtual methods
.method public collapseActionView()Z
    .locals 1

    .prologue
    .line 256
    const/4 v0, 0x0

    return v0
.end method

.method public expandActionView()Z
    .locals 1

    .prologue
    .line 251
    const/4 v0, 0x0

    return v0
.end method

.method public getActionProvider()Landroid/view/ActionProvider;
    .locals 1

    .prologue
    .line 227
    new-instance v0, Ljava/lang/UnsupportedOperationException;

    invoke-direct {v0}, Ljava/lang/UnsupportedOperationException;-><init>()V

    throw v0
.end method

.method public getActionView()Landroid/view/View;
    .locals 1

    .prologue
    .line 217
    const/4 v0, 0x0

    return-object v0
.end method

.method public getAlphabeticShortcut()C
    .locals 1

    .prologue
    .line 56
    iget-char v0, p0, Lcom/miui/internal/view/menu/ActionMenuItem;->OI:C

    return v0
.end method

.method public getGroupId()I
    .locals 1

    .prologue
    .line 60
    iget v0, p0, Lcom/miui/internal/view/menu/ActionMenuItem;->OC:I

    return v0
.end method

.method public getIcon()Landroid/graphics/drawable/Drawable;
    .locals 1

    .prologue
    .line 64
    iget-object v0, p0, Lcom/miui/internal/view/menu/ActionMenuItem;->OJ:Landroid/graphics/drawable/Drawable;

    return-object v0
.end method

.method public getIntent()Landroid/content/Intent;
    .locals 1

    .prologue
    .line 68
    iget-object v0, p0, Lcom/miui/internal/view/menu/ActionMenuItem;->OG:Landroid/content/Intent;

    return-object v0
.end method

.method public getItemId()I
    .locals 1

    .prologue
    .line 72
    iget v0, p0, Lcom/miui/internal/view/menu/ActionMenuItem;->ko:I

    return v0
.end method

.method public getMenuInfo()Landroid/view/ContextMenu$ContextMenuInfo;
    .locals 1

    .prologue
    .line 76
    const/4 v0, 0x0

    return-object v0
.end method

.method public getNumericShortcut()C
    .locals 1

    .prologue
    .line 80
    iget-char v0, p0, Lcom/miui/internal/view/menu/ActionMenuItem;->OH:C

    return v0
.end method

.method public getOrder()I
    .locals 1

    .prologue
    .line 84
    iget v0, p0, Lcom/miui/internal/view/menu/ActionMenuItem;->OE:I

    return v0
.end method

.method public getSubMenu()Landroid/view/SubMenu;
    .locals 1

    .prologue
    .line 88
    const/4 v0, 0x0

    return-object v0
.end method

.method public getSupportActionProvider()Landroid/view/ActionProvider;
    .locals 1

    .prologue
    .line 236
    const/4 v0, 0x0

    return-object v0
.end method

.method public getTitle()Ljava/lang/CharSequence;
    .locals 1

    .prologue
    .line 92
    iget-object v0, p0, Lcom/miui/internal/view/menu/ActionMenuItem;->mTitle:Ljava/lang/CharSequence;

    return-object v0
.end method

.method public getTitleCondensed()Ljava/lang/CharSequence;
    .locals 1

    .prologue
    .line 96
    iget-object v0, p0, Lcom/miui/internal/view/menu/ActionMenuItem;->OF:Ljava/lang/CharSequence;

    return-object v0
.end method

.method public hasSubMenu()Z
    .locals 1

    .prologue
    .line 100
    const/4 v0, 0x0

    return v0
.end method

.method public invoke()Z
    .locals 3

    .prologue
    const/4 v0, 0x1

    .line 196
    iget-object v1, p0, Lcom/miui/internal/view/menu/ActionMenuItem;->OK:Landroid/view/MenuItem$OnMenuItemClickListener;

    if-eqz v1, :cond_0

    iget-object v1, p0, Lcom/miui/internal/view/menu/ActionMenuItem;->OK:Landroid/view/MenuItem$OnMenuItemClickListener;

    invoke-interface {v1, p0}, Landroid/view/MenuItem$OnMenuItemClickListener;->onMenuItemClick(Landroid/view/MenuItem;)Z

    move-result v1

    if-eqz v1, :cond_0

    .line 205
    :goto_0
    return v0

    .line 200
    :cond_0
    iget-object v1, p0, Lcom/miui/internal/view/menu/ActionMenuItem;->OG:Landroid/content/Intent;

    if-eqz v1, :cond_1

    .line 201
    iget-object v1, p0, Lcom/miui/internal/view/menu/ActionMenuItem;->mContext:Landroid/content/Context;

    iget-object v2, p0, Lcom/miui/internal/view/menu/ActionMenuItem;->OG:Landroid/content/Intent;

    invoke-virtual {v1, v2}, Landroid/content/Context;->startActivity(Landroid/content/Intent;)V

    goto :goto_0

    .line 205
    :cond_1
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public isActionViewExpanded()Z
    .locals 1

    .prologue
    .line 261
    const/4 v0, 0x0

    return v0
.end method

.method public isCheckable()Z
    .locals 1

    .prologue
    .line 104
    iget v0, p0, Lcom/miui/internal/view/menu/ActionMenuItem;->aZ:I

    and-int/lit8 v0, v0, 0x1

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public isChecked()Z
    .locals 1

    .prologue
    .line 108
    iget v0, p0, Lcom/miui/internal/view/menu/ActionMenuItem;->aZ:I

    and-int/lit8 v0, v0, 0x2

    if-eqz v0, :cond_0

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
    .line 112
    iget v0, p0, Lcom/miui/internal/view/menu/ActionMenuItem;->aZ:I

    and-int/lit8 v0, v0, 0x10

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public isVisible()Z
    .locals 1

    .prologue
    .line 116
    iget v0, p0, Lcom/miui/internal/view/menu/ActionMenuItem;->aZ:I

    and-int/lit8 v0, v0, 0x8

    if-nez v0, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public setActionProvider(Landroid/view/ActionProvider;)Landroid/view/MenuItem;
    .locals 1

    .prologue
    .line 222
    new-instance v0, Ljava/lang/UnsupportedOperationException;

    invoke-direct {v0}, Ljava/lang/UnsupportedOperationException;-><init>()V

    throw v0
.end method

.method public setActionView(I)Landroid/view/MenuItem;
    .locals 1

    .prologue
    .line 232
    new-instance v0, Ljava/lang/UnsupportedOperationException;

    invoke-direct {v0}, Ljava/lang/UnsupportedOperationException;-><init>()V

    throw v0
.end method

.method public setActionView(Landroid/view/View;)Landroid/view/MenuItem;
    .locals 1

    .prologue
    .line 213
    new-instance v0, Ljava/lang/UnsupportedOperationException;

    invoke-direct {v0}, Ljava/lang/UnsupportedOperationException;-><init>()V

    throw v0
.end method

.method public setAlphabeticShortcut(C)Landroid/view/MenuItem;
    .locals 0

    .prologue
    .line 120
    iput-char p1, p0, Lcom/miui/internal/view/menu/ActionMenuItem;->OI:C

    .line 121
    return-object p0
.end method

.method public setCheckable(Z)Landroid/view/MenuItem;
    .locals 2

    .prologue
    .line 125
    iget v0, p0, Lcom/miui/internal/view/menu/ActionMenuItem;->aZ:I

    and-int/lit8 v1, v0, -0x2

    if-eqz p1, :cond_0

    const/4 v0, 0x1

    :goto_0
    or-int/2addr v0, v1

    iput v0, p0, Lcom/miui/internal/view/menu/ActionMenuItem;->aZ:I

    .line 126
    return-object p0

    .line 125
    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public setChecked(Z)Landroid/view/MenuItem;
    .locals 2

    .prologue
    .line 135
    iget v0, p0, Lcom/miui/internal/view/menu/ActionMenuItem;->aZ:I

    and-int/lit8 v1, v0, -0x3

    if-eqz p1, :cond_0

    const/4 v0, 0x2

    :goto_0
    or-int/2addr v0, v1

    iput v0, p0, Lcom/miui/internal/view/menu/ActionMenuItem;->aZ:I

    .line 136
    return-object p0

    .line 135
    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public setEnabled(Z)Landroid/view/MenuItem;
    .locals 2

    .prologue
    .line 140
    iget v0, p0, Lcom/miui/internal/view/menu/ActionMenuItem;->aZ:I

    and-int/lit8 v1, v0, -0x11

    if-eqz p1, :cond_0

    const/16 v0, 0x10

    :goto_0
    or-int/2addr v0, v1

    iput v0, p0, Lcom/miui/internal/view/menu/ActionMenuItem;->aZ:I

    .line 141
    return-object p0

    .line 140
    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public setExclusiveCheckable(Z)Lcom/miui/internal/view/menu/ActionMenuItem;
    .locals 2

    .prologue
    .line 130
    iget v0, p0, Lcom/miui/internal/view/menu/ActionMenuItem;->aZ:I

    and-int/lit8 v1, v0, -0x5

    if-eqz p1, :cond_0

    const/4 v0, 0x4

    :goto_0
    or-int/2addr v0, v1

    iput v0, p0, Lcom/miui/internal/view/menu/ActionMenuItem;->aZ:I

    .line 131
    return-object p0

    .line 130
    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public setIcon(I)Landroid/view/MenuItem;
    .locals 1

    .prologue
    .line 150
    iget-object v0, p0, Lcom/miui/internal/view/menu/ActionMenuItem;->mContext:Landroid/content/Context;

    invoke-virtual {v0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    invoke-virtual {v0, p1}, Landroid/content/res/Resources;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/internal/view/menu/ActionMenuItem;->OJ:Landroid/graphics/drawable/Drawable;

    .line 151
    return-object p0
.end method

.method public setIcon(Landroid/graphics/drawable/Drawable;)Landroid/view/MenuItem;
    .locals 0

    .prologue
    .line 145
    iput-object p1, p0, Lcom/miui/internal/view/menu/ActionMenuItem;->OJ:Landroid/graphics/drawable/Drawable;

    .line 146
    return-object p0
.end method

.method public setIntent(Landroid/content/Intent;)Landroid/view/MenuItem;
    .locals 0

    .prologue
    .line 155
    iput-object p1, p0, Lcom/miui/internal/view/menu/ActionMenuItem;->OG:Landroid/content/Intent;

    .line 156
    return-object p0
.end method

.method public setNumericShortcut(C)Landroid/view/MenuItem;
    .locals 0

    .prologue
    .line 160
    iput-char p1, p0, Lcom/miui/internal/view/menu/ActionMenuItem;->OH:C

    .line 161
    return-object p0
.end method

.method public setOnActionExpandListener(Landroid/view/MenuItem$OnActionExpandListener;)Landroid/view/MenuItem;
    .locals 1

    .prologue
    .line 266
    new-instance v0, Ljava/lang/UnsupportedOperationException;

    invoke-direct {v0}, Ljava/lang/UnsupportedOperationException;-><init>()V

    throw v0
.end method

.method public setOnMenuItemClickListener(Landroid/view/MenuItem$OnMenuItemClickListener;)Landroid/view/MenuItem;
    .locals 0

    .prologue
    .line 165
    iput-object p1, p0, Lcom/miui/internal/view/menu/ActionMenuItem;->OK:Landroid/view/MenuItem$OnMenuItemClickListener;

    .line 166
    return-object p0
.end method

.method public setShortcut(CC)Landroid/view/MenuItem;
    .locals 0

    .prologue
    .line 170
    iput-char p1, p0, Lcom/miui/internal/view/menu/ActionMenuItem;->OH:C

    .line 171
    iput-char p2, p0, Lcom/miui/internal/view/menu/ActionMenuItem;->OI:C

    .line 172
    return-object p0
.end method

.method public setShowAsAction(I)V
    .locals 0

    .prologue
    .line 210
    return-void
.end method

.method public setShowAsActionFlags(I)Landroid/view/MenuItem;
    .locals 0

    .prologue
    .line 245
    invoke-virtual {p0, p1}, Lcom/miui/internal/view/menu/ActionMenuItem;->setShowAsAction(I)V

    .line 246
    return-object p0
.end method

.method public setSupportActionProvider(Landroid/view/ActionProvider;)Landroid/view/MenuItem;
    .locals 1

    .prologue
    .line 240
    new-instance v0, Ljava/lang/UnsupportedOperationException;

    invoke-direct {v0}, Ljava/lang/UnsupportedOperationException;-><init>()V

    throw v0
.end method

.method public setSupportOnActionExpandListener(Landroid/view/MenuItem$OnActionExpandListener;)Landroid/view/MenuItem;
    .locals 0

    .prologue
    .line 271
    return-object p0
.end method

.method public setTitle(I)Landroid/view/MenuItem;
    .locals 1

    .prologue
    .line 181
    iget-object v0, p0, Lcom/miui/internal/view/menu/ActionMenuItem;->mContext:Landroid/content/Context;

    invoke-virtual {v0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    invoke-virtual {v0, p1}, Landroid/content/res/Resources;->getString(I)Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/internal/view/menu/ActionMenuItem;->mTitle:Ljava/lang/CharSequence;

    .line 182
    return-object p0
.end method

.method public setTitle(Ljava/lang/CharSequence;)Landroid/view/MenuItem;
    .locals 0

    .prologue
    .line 176
    iput-object p1, p0, Lcom/miui/internal/view/menu/ActionMenuItem;->mTitle:Ljava/lang/CharSequence;

    .line 177
    return-object p0
.end method

.method public setTitleCondensed(Ljava/lang/CharSequence;)Landroid/view/MenuItem;
    .locals 0

    .prologue
    .line 186
    iput-object p1, p0, Lcom/miui/internal/view/menu/ActionMenuItem;->OF:Ljava/lang/CharSequence;

    .line 187
    return-object p0
.end method

.method public setVisible(Z)Landroid/view/MenuItem;
    .locals 2

    .prologue
    .line 191
    iget v0, p0, Lcom/miui/internal/view/menu/ActionMenuItem;->aZ:I

    and-int/lit8 v1, v0, 0x8

    if-eqz p1, :cond_0

    const/4 v0, 0x0

    :goto_0
    or-int/2addr v0, v1

    iput v0, p0, Lcom/miui/internal/view/menu/ActionMenuItem;->aZ:I

    .line 192
    return-object p0

    .line 191
    :cond_0
    const/16 v0, 0x8

    goto :goto_0
.end method
