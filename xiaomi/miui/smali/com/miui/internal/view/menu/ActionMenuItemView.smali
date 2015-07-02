.class public Lcom/miui/internal/view/menu/ActionMenuItemView;
.super Landroid/widget/Button;
.source "SourceFile"

# interfaces
.implements Lcom/miui/internal/view/menu/MenuView$ItemView;


# instance fields
.field private uL:Lcom/miui/internal/view/menu/MenuItemImpl;

.field private uM:Lcom/miui/internal/view/menu/MenuBuilder$ItemInvoker;

.field private uN:Z


# direct methods
.method public constructor <init>(Landroid/content/Context;)V
    .locals 2

    .prologue
    .line 23
    const/4 v0, 0x0

    const/4 v1, 0x0

    invoke-direct {p0, p1, v0, v1}, Lcom/miui/internal/view/menu/ActionMenuItemView;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    .line 24
    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 1

    .prologue
    .line 27
    const/4 v0, 0x0

    invoke-direct {p0, p1, p2, v0}, Lcom/miui/internal/view/menu/ActionMenuItemView;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    .line 28
    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V
    .locals 0

    .prologue
    .line 31
    invoke-direct {p0, p1, p2, p3}, Landroid/widget/Button;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    .line 32
    return-void
.end method


# virtual methods
.method public getItemData()Lcom/miui/internal/view/menu/MenuItemImpl;
    .locals 1

    .prologue
    .line 51
    iget-object v0, p0, Lcom/miui/internal/view/menu/ActionMenuItemView;->uL:Lcom/miui/internal/view/menu/MenuItemImpl;

    return-object v0
.end method

.method public initialize(Lcom/miui/internal/view/menu/MenuItemImpl;I)V
    .locals 1

    .prologue
    .line 36
    iput-object p1, p0, Lcom/miui/internal/view/menu/ActionMenuItemView;->uL:Lcom/miui/internal/view/menu/MenuItemImpl;

    .line 39
    const/4 v0, 0x0

    invoke-virtual {p0, v0}, Lcom/miui/internal/view/menu/ActionMenuItemView;->setSelected(Z)V

    .line 41
    invoke-virtual {p1}, Lcom/miui/internal/view/menu/MenuItemImpl;->getTitle()Ljava/lang/CharSequence;

    move-result-object v0

    invoke-virtual {p0, v0}, Lcom/miui/internal/view/menu/ActionMenuItemView;->setTitle(Ljava/lang/CharSequence;)V

    .line 42
    invoke-virtual {p1}, Lcom/miui/internal/view/menu/MenuItemImpl;->getIcon()Landroid/graphics/drawable/Drawable;

    move-result-object v0

    invoke-virtual {p0, v0}, Lcom/miui/internal/view/menu/ActionMenuItemView;->setIcon(Landroid/graphics/drawable/Drawable;)V

    .line 43
    invoke-virtual {p1}, Lcom/miui/internal/view/menu/MenuItemImpl;->isCheckable()Z

    move-result v0

    invoke-virtual {p0, v0}, Lcom/miui/internal/view/menu/ActionMenuItemView;->setCheckable(Z)V

    .line 44
    invoke-virtual {p1}, Lcom/miui/internal/view/menu/MenuItemImpl;->isChecked()Z

    move-result v0

    invoke-virtual {p0, v0}, Lcom/miui/internal/view/menu/ActionMenuItemView;->setChecked(Z)V

    .line 45
    invoke-virtual {p1}, Lcom/miui/internal/view/menu/MenuItemImpl;->isEnabled()Z

    move-result v0

    invoke-virtual {p0, v0}, Lcom/miui/internal/view/menu/ActionMenuItemView;->setEnabled(Z)V

    .line 46
    const/4 v0, 0x1

    invoke-virtual {p0, v0}, Lcom/miui/internal/view/menu/ActionMenuItemView;->setClickable(Z)V

    .line 47
    return-void
.end method

.method public performClick()Z
    .locals 4

    .prologue
    const/4 v0, 0x1

    const/4 v1, 0x0

    .line 95
    invoke-super {p0}, Landroid/widget/Button;->performClick()Z

    move-result v2

    if-eqz v2, :cond_0

    .line 103
    :goto_0
    return v0

    .line 99
    :cond_0
    iget-object v2, p0, Lcom/miui/internal/view/menu/ActionMenuItemView;->uM:Lcom/miui/internal/view/menu/MenuBuilder$ItemInvoker;

    if-eqz v2, :cond_1

    iget-object v2, p0, Lcom/miui/internal/view/menu/ActionMenuItemView;->uM:Lcom/miui/internal/view/menu/MenuBuilder$ItemInvoker;

    iget-object v3, p0, Lcom/miui/internal/view/menu/ActionMenuItemView;->uL:Lcom/miui/internal/view/menu/MenuItemImpl;

    invoke-interface {v2, v3}, Lcom/miui/internal/view/menu/MenuBuilder$ItemInvoker;->invokeItem(Lcom/miui/internal/view/menu/MenuItemImpl;)Z

    move-result v2

    if-eqz v2, :cond_1

    .line 100
    invoke-virtual {p0, v1}, Lcom/miui/internal/view/menu/ActionMenuItemView;->playSoundEffect(I)V

    goto :goto_0

    :cond_1
    move v0, v1

    .line 103
    goto :goto_0
.end method

.method public prefersCondensedTitle()Z
    .locals 1

    .prologue
    .line 85
    const/4 v0, 0x0

    return v0
.end method

.method public setCheckable(Z)V
    .locals 0

    .prologue
    .line 61
    iput-boolean p1, p0, Lcom/miui/internal/view/menu/ActionMenuItemView;->uN:Z

    .line 62
    return-void
.end method

.method public setChecked(Z)V
    .locals 1

    .prologue
    .line 66
    iget-boolean v0, p0, Lcom/miui/internal/view/menu/ActionMenuItemView;->uN:Z

    if-eqz v0, :cond_0

    .line 67
    invoke-virtual {p0, p1}, Lcom/miui/internal/view/menu/ActionMenuItemView;->setSelected(Z)V

    .line 69
    :cond_0
    return-void
.end method

.method public setIcon(Landroid/graphics/drawable/Drawable;)V
    .locals 3

    .prologue
    const/4 v2, 0x0

    .line 77
    invoke-virtual {p0}, Lcom/miui/internal/view/menu/ActionMenuItemView;->getCompoundDrawables()[Landroid/graphics/drawable/Drawable;

    move-result-object v0

    const/4 v1, 0x1

    aget-object v0, v0, v1

    .line 78
    if-eq v0, p1, :cond_0

    .line 79
    invoke-virtual {p0, v2, p1, v2, v2}, Lcom/miui/internal/view/menu/ActionMenuItemView;->setCompoundDrawablesWithIntrinsicBounds(Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;)V

    .line 81
    :cond_0
    return-void
.end method

.method public setItemInvoker(Lcom/miui/internal/view/menu/MenuBuilder$ItemInvoker;)V
    .locals 0

    .prologue
    .line 108
    iput-object p1, p0, Lcom/miui/internal/view/menu/ActionMenuItemView;->uM:Lcom/miui/internal/view/menu/MenuBuilder$ItemInvoker;

    .line 109
    return-void
.end method

.method public setShortcut(ZC)V
    .locals 0

    .prologue
    .line 73
    return-void
.end method

.method public setTitle(Ljava/lang/CharSequence;)V
    .locals 0

    .prologue
    .line 56
    invoke-virtual {p0, p1}, Lcom/miui/internal/view/menu/ActionMenuItemView;->setText(Ljava/lang/CharSequence;)V

    .line 57
    return-void
.end method

.method public showsIcon()Z
    .locals 1

    .prologue
    .line 90
    const/4 v0, 0x1

    return v0
.end method
