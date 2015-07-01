.class public Lcom/miui/internal/view/menu/ListMenuItemView;
.super Landroid/widget/LinearLayout;
.source "SourceFile"

# interfaces
.implements Lcom/miui/internal/view/menu/MenuView$ItemView;


# instance fields
.field private Mk:Landroid/widget/RadioButton;

.field private Ml:Landroid/widget/CheckBox;

.field private Mm:Landroid/widget/TextView;

.field private Mn:Landroid/view/View;

.field private Mo:I

.field private Mp:Landroid/content/Context;

.field private Mq:Z

.field private hJ:Landroid/widget/TextView;

.field private lp:Landroid/graphics/drawable/Drawable;

.field private mContext:Landroid/content/Context;

.field private mInflater:Landroid/view/LayoutInflater;

.field private n:Landroid/widget/ImageView;

.field private nt:Z

.field private uL:Lcom/miui/internal/view/menu/MenuItemImpl;


# direct methods
.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 1

    .prologue
    .line 68
    const/4 v0, 0x0

    invoke-direct {p0, p1, p2, v0}, Lcom/miui/internal/view/menu/ListMenuItemView;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    .line 69
    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V
    .locals 4

    .prologue
    const/4 v3, 0x0

    .line 50
    invoke-direct {p0, p1, p2}, Landroid/widget/LinearLayout;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    .line 51
    iput-object p1, p0, Lcom/miui/internal/view/menu/ListMenuItemView;->mContext:Landroid/content/Context;

    .line 53
    sget-object v0, Lmiui/R$styleable;->MenuView:[I

    invoke-virtual {p1, p2, v0, p3, v3}, Landroid/content/Context;->obtainStyledAttributes(Landroid/util/AttributeSet;[III)Landroid/content/res/TypedArray;

    move-result-object v0

    .line 57
    const/4 v1, 0x5

    invoke-virtual {v0, v1}, Landroid/content/res/TypedArray;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v1

    iput-object v1, p0, Lcom/miui/internal/view/menu/ListMenuItemView;->lp:Landroid/graphics/drawable/Drawable;

    .line 58
    const/4 v1, 0x1

    const/4 v2, -0x1

    invoke-virtual {v0, v1, v2}, Landroid/content/res/TypedArray;->getResourceId(II)I

    move-result v1

    iput v1, p0, Lcom/miui/internal/view/menu/ListMenuItemView;->Mo:I

    .line 60
    const/4 v1, 0x7

    invoke-virtual {v0, v1, v3}, Landroid/content/res/TypedArray;->getBoolean(IZ)Z

    move-result v1

    iput-boolean v1, p0, Lcom/miui/internal/view/menu/ListMenuItemView;->Mq:Z

    .line 62
    iput-object p1, p0, Lcom/miui/internal/view/menu/ListMenuItemView;->Mp:Landroid/content/Context;

    .line 64
    invoke-virtual {v0}, Landroid/content/res/TypedArray;->recycle()V

    .line 65
    return-void
.end method

.method private eg()V
    .locals 3

    .prologue
    const/4 v2, 0x0

    .line 251
    invoke-direct {p0}, Lcom/miui/internal/view/menu/ListMenuItemView;->ej()Landroid/view/LayoutInflater;

    move-result-object v0

    .line 252
    sget v1, Lcom/miui/internal/R$layout;->list_menu_item_icon:I

    invoke-virtual {v0, v1, p0, v2}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;Z)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/ImageView;

    iput-object v0, p0, Lcom/miui/internal/view/menu/ListMenuItemView;->n:Landroid/widget/ImageView;

    .line 254
    iget-object v0, p0, Lcom/miui/internal/view/menu/ListMenuItemView;->n:Landroid/widget/ImageView;

    invoke-virtual {p0, v0, v2}, Lcom/miui/internal/view/menu/ListMenuItemView;->addView(Landroid/view/View;I)V

    .line 255
    return-void
.end method

.method private eh()V
    .locals 3

    .prologue
    const/4 v2, 0x0

    .line 258
    invoke-direct {p0}, Lcom/miui/internal/view/menu/ListMenuItemView;->ej()Landroid/view/LayoutInflater;

    move-result-object v0

    .line 259
    sget v1, Lcom/miui/internal/R$layout;->list_menu_item_radio:I

    invoke-virtual {v0, v1, p0, v2}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;Z)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/RadioButton;

    iput-object v0, p0, Lcom/miui/internal/view/menu/ListMenuItemView;->Mk:Landroid/widget/RadioButton;

    .line 261
    iget-object v0, p0, Lcom/miui/internal/view/menu/ListMenuItemView;->Mk:Landroid/widget/RadioButton;

    invoke-virtual {p0, v0, v2}, Lcom/miui/internal/view/menu/ListMenuItemView;->addView(Landroid/view/View;I)V

    .line 262
    return-void
.end method

.method private ei()V
    .locals 3

    .prologue
    .line 265
    invoke-direct {p0}, Lcom/miui/internal/view/menu/ListMenuItemView;->ej()Landroid/view/LayoutInflater;

    move-result-object v0

    .line 266
    sget v1, Lcom/miui/internal/R$layout;->list_menu_item_checkbox:I

    const/4 v2, 0x0

    invoke-virtual {v0, v1, p0, v2}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;Z)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/CheckBox;

    iput-object v0, p0, Lcom/miui/internal/view/menu/ListMenuItemView;->Ml:Landroid/widget/CheckBox;

    .line 269
    iget-object v0, p0, Lcom/miui/internal/view/menu/ListMenuItemView;->Ml:Landroid/widget/CheckBox;

    invoke-virtual {p0, v0}, Lcom/miui/internal/view/menu/ListMenuItemView;->addView(Landroid/view/View;)V

    .line 270
    return-void
.end method

.method private ej()Landroid/view/LayoutInflater;
    .locals 1

    .prologue
    .line 281
    iget-object v0, p0, Lcom/miui/internal/view/menu/ListMenuItemView;->mInflater:Landroid/view/LayoutInflater;

    if-nez v0, :cond_0

    .line 282
    iget-object v0, p0, Lcom/miui/internal/view/menu/ListMenuItemView;->mContext:Landroid/content/Context;

    invoke-static {v0}, Landroid/view/LayoutInflater;->from(Landroid/content/Context;)Landroid/view/LayoutInflater;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/internal/view/menu/ListMenuItemView;->mInflater:Landroid/view/LayoutInflater;

    .line 284
    :cond_0
    iget-object v0, p0, Lcom/miui/internal/view/menu/ListMenuItemView;->mInflater:Landroid/view/LayoutInflater;

    return-object v0
.end method


# virtual methods
.method public getItemData()Lcom/miui/internal/view/menu/MenuItemImpl;
    .locals 1

    .prologue
    .line 118
    iget-object v0, p0, Lcom/miui/internal/view/menu/ListMenuItemView;->uL:Lcom/miui/internal/view/menu/MenuItemImpl;

    return-object v0
.end method

.method public initialize(Lcom/miui/internal/view/menu/MenuItemImpl;I)V
    .locals 2

    .prologue
    .line 88
    iput-object p1, p0, Lcom/miui/internal/view/menu/ListMenuItemView;->uL:Lcom/miui/internal/view/menu/MenuItemImpl;

    .line 90
    invoke-virtual {p1}, Lcom/miui/internal/view/menu/MenuItemImpl;->isVisible()Z

    move-result v0

    if-eqz v0, :cond_0

    const/4 v0, 0x0

    :goto_0
    invoke-virtual {p0, v0}, Lcom/miui/internal/view/menu/ListMenuItemView;->setVisibility(I)V

    .line 92
    invoke-virtual {p1, p0}, Lcom/miui/internal/view/menu/MenuItemImpl;->a(Lcom/miui/internal/view/menu/MenuView$ItemView;)Ljava/lang/CharSequence;

    move-result-object v0

    invoke-virtual {p0, v0}, Lcom/miui/internal/view/menu/ListMenuItemView;->setTitle(Ljava/lang/CharSequence;)V

    .line 93
    invoke-virtual {p1}, Lcom/miui/internal/view/menu/MenuItemImpl;->isCheckable()Z

    move-result v0

    invoke-virtual {p0, v0}, Lcom/miui/internal/view/menu/ListMenuItemView;->setCheckable(Z)V

    .line 94
    invoke-virtual {p1}, Lcom/miui/internal/view/menu/MenuItemImpl;->eT()Z

    move-result v0

    invoke-virtual {p1}, Lcom/miui/internal/view/menu/MenuItemImpl;->eR()C

    move-result v1

    invoke-virtual {p0, v0, v1}, Lcom/miui/internal/view/menu/ListMenuItemView;->setShortcut(ZC)V

    .line 95
    invoke-virtual {p1}, Lcom/miui/internal/view/menu/MenuItemImpl;->getIcon()Landroid/graphics/drawable/Drawable;

    move-result-object v0

    invoke-virtual {p0, v0}, Lcom/miui/internal/view/menu/ListMenuItemView;->setIcon(Landroid/graphics/drawable/Drawable;)V

    .line 96
    invoke-virtual {p1}, Lcom/miui/internal/view/menu/MenuItemImpl;->isEnabled()Z

    move-result v0

    invoke-virtual {p0, v0}, Lcom/miui/internal/view/menu/ListMenuItemView;->setEnabled(Z)V

    .line 97
    return-void

    .line 90
    :cond_0
    const/16 v0, 0x8

    goto :goto_0
.end method

.method protected onFinishInflate()V
    .locals 3

    .prologue
    .line 73
    invoke-super {p0}, Landroid/widget/LinearLayout;->onFinishInflate()V

    .line 75
    iget-object v0, p0, Lcom/miui/internal/view/menu/ListMenuItemView;->lp:Landroid/graphics/drawable/Drawable;

    invoke-virtual {p0, v0}, Lcom/miui/internal/view/menu/ListMenuItemView;->setBackground(Landroid/graphics/drawable/Drawable;)V

    .line 77
    sget v0, Lcom/miui/internal/R$id;->title:I

    invoke-virtual {p0, v0}, Lcom/miui/internal/view/menu/ListMenuItemView;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/TextView;

    iput-object v0, p0, Lcom/miui/internal/view/menu/ListMenuItemView;->hJ:Landroid/widget/TextView;

    .line 78
    iget v0, p0, Lcom/miui/internal/view/menu/ListMenuItemView;->Mo:I

    const/4 v1, -0x1

    if-eq v0, v1, :cond_0

    .line 79
    iget-object v0, p0, Lcom/miui/internal/view/menu/ListMenuItemView;->hJ:Landroid/widget/TextView;

    iget-object v1, p0, Lcom/miui/internal/view/menu/ListMenuItemView;->Mp:Landroid/content/Context;

    iget v2, p0, Lcom/miui/internal/view/menu/ListMenuItemView;->Mo:I

    invoke-virtual {v0, v1, v2}, Landroid/widget/TextView;->setTextAppearance(Landroid/content/Context;I)V

    .line 83
    :cond_0
    sget v0, Lcom/miui/internal/R$id;->shortcut:I

    invoke-virtual {p0, v0}, Lcom/miui/internal/view/menu/ListMenuItemView;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/TextView;

    iput-object v0, p0, Lcom/miui/internal/view/menu/ListMenuItemView;->Mm:Landroid/widget/TextView;

    .line 84
    const/4 v0, 0x0

    invoke-virtual {p0, v0}, Lcom/miui/internal/view/menu/ListMenuItemView;->getChildAt(I)Landroid/view/View;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/internal/view/menu/ListMenuItemView;->Mn:Landroid/view/View;

    .line 85
    return-void
.end method

.method protected onMeasure(II)V
    .locals 3

    .prologue
    .line 239
    iget-object v0, p0, Lcom/miui/internal/view/menu/ListMenuItemView;->n:Landroid/widget/ImageView;

    if-eqz v0, :cond_0

    iget-boolean v0, p0, Lcom/miui/internal/view/menu/ListMenuItemView;->Mq:Z

    if-eqz v0, :cond_0

    .line 241
    invoke-virtual {p0}, Lcom/miui/internal/view/menu/ListMenuItemView;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v1

    .line 242
    iget-object v0, p0, Lcom/miui/internal/view/menu/ListMenuItemView;->n:Landroid/widget/ImageView;

    invoke-virtual {v0}, Landroid/widget/ImageView;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v0

    check-cast v0, Landroid/widget/LinearLayout$LayoutParams;

    .line 243
    iget v2, v1, Landroid/view/ViewGroup$LayoutParams;->height:I

    if-lez v2, :cond_0

    iget v2, v0, Landroid/widget/LinearLayout$LayoutParams;->width:I

    if-gtz v2, :cond_0

    .line 244
    iget v1, v1, Landroid/view/ViewGroup$LayoutParams;->height:I

    iput v1, v0, Landroid/widget/LinearLayout$LayoutParams;->width:I

    .line 247
    :cond_0
    invoke-super {p0, p1, p2}, Landroid/widget/LinearLayout;->onMeasure(II)V

    .line 248
    return-void
.end method

.method public prefersCondensedTitle()Z
    .locals 1

    .prologue
    .line 273
    const/4 v0, 0x0

    return v0
.end method

.method public setCheckable(Z)V
    .locals 5

    .prologue
    const/4 v1, 0x0

    const/16 v4, 0x8

    .line 122
    if-nez p1, :cond_0

    iget-object v0, p0, Lcom/miui/internal/view/menu/ListMenuItemView;->Mk:Landroid/widget/RadioButton;

    if-nez v0, :cond_0

    iget-object v0, p0, Lcom/miui/internal/view/menu/ListMenuItemView;->Ml:Landroid/widget/CheckBox;

    if-nez v0, :cond_0

    .line 173
    :goto_0
    return-void

    .line 131
    :cond_0
    iget-object v0, p0, Lcom/miui/internal/view/menu/ListMenuItemView;->uL:Lcom/miui/internal/view/menu/MenuItemImpl;

    invoke-virtual {v0}, Lcom/miui/internal/view/menu/MenuItemImpl;->isExclusiveCheckable()Z

    move-result v0

    if-eqz v0, :cond_6

    .line 132
    iget-object v0, p0, Lcom/miui/internal/view/menu/ListMenuItemView;->Mk:Landroid/widget/RadioButton;

    if-nez v0, :cond_1

    .line 133
    invoke-direct {p0}, Lcom/miui/internal/view/menu/ListMenuItemView;->eh()V

    .line 135
    :cond_1
    iget-object v2, p0, Lcom/miui/internal/view/menu/ListMenuItemView;->Mk:Landroid/widget/RadioButton;

    .line 136
    iget-object v0, p0, Lcom/miui/internal/view/menu/ListMenuItemView;->Ml:Landroid/widget/CheckBox;

    .line 145
    :goto_1
    if-eqz p1, :cond_8

    .line 146
    iget-object v3, p0, Lcom/miui/internal/view/menu/ListMenuItemView;->uL:Lcom/miui/internal/view/menu/MenuItemImpl;

    invoke-virtual {v3}, Lcom/miui/internal/view/menu/MenuItemImpl;->isChecked()Z

    move-result v3

    invoke-virtual {v2, v3}, Landroid/widget/CompoundButton;->setChecked(Z)V

    .line 148
    invoke-virtual {v2}, Landroid/widget/CompoundButton;->getVisibility()I

    move-result v3

    if-eqz v3, :cond_2

    .line 149
    invoke-virtual {v2, v1}, Landroid/widget/CompoundButton;->setVisibility(I)V

    .line 153
    :cond_2
    if-eqz v0, :cond_3

    invoke-virtual {v0}, Landroid/widget/CompoundButton;->getVisibility()I

    move-result v2

    if-eq v2, v4, :cond_3

    .line 154
    invoke-virtual {v0, v4}, Landroid/widget/CompoundButton;->setVisibility(I)V

    .line 166
    :cond_3
    :goto_2
    iget-object v0, p0, Lcom/miui/internal/view/menu/ListMenuItemView;->Mn:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v0

    check-cast v0, Landroid/view/ViewGroup$MarginLayoutParams;

    .line 167
    if-eqz p1, :cond_4

    iget-object v2, p0, Lcom/miui/internal/view/menu/ListMenuItemView;->uL:Lcom/miui/internal/view/menu/MenuItemImpl;

    invoke-virtual {v2}, Lcom/miui/internal/view/menu/MenuItemImpl;->isExclusiveCheckable()Z

    move-result v2

    if-nez v2, :cond_5

    :cond_4
    invoke-virtual {p0}, Lcom/miui/internal/view/menu/ListMenuItemView;->getContext()Landroid/content/Context;

    move-result-object v1

    const v2, 0x10103a3

    invoke-static {v1, v2}, Lmiui/util/AttributeResolver;->resolveDimensionPixelSize(Landroid/content/Context;I)I

    move-result v1

    :cond_5
    iput v1, v0, Landroid/view/ViewGroup$MarginLayoutParams;->leftMargin:I

    .line 169
    iget-object v1, p0, Lcom/miui/internal/view/menu/ListMenuItemView;->Mn:Landroid/view/View;

    invoke-virtual {v1, v0}, Landroid/view/View;->setLayoutParams(Landroid/view/ViewGroup$LayoutParams;)V

    .line 172
    iget-object v0, p0, Lcom/miui/internal/view/menu/ListMenuItemView;->uL:Lcom/miui/internal/view/menu/MenuItemImpl;

    invoke-virtual {v0}, Lcom/miui/internal/view/menu/MenuItemImpl;->isChecked()Z

    move-result v0

    invoke-virtual {p0, v0}, Lcom/miui/internal/view/menu/ListMenuItemView;->setActivated(Z)V

    goto :goto_0

    .line 138
    :cond_6
    iget-object v0, p0, Lcom/miui/internal/view/menu/ListMenuItemView;->Ml:Landroid/widget/CheckBox;

    if-nez v0, :cond_7

    .line 139
    invoke-direct {p0}, Lcom/miui/internal/view/menu/ListMenuItemView;->ei()V

    .line 141
    :cond_7
    iget-object v2, p0, Lcom/miui/internal/view/menu/ListMenuItemView;->Ml:Landroid/widget/CheckBox;

    .line 142
    iget-object v0, p0, Lcom/miui/internal/view/menu/ListMenuItemView;->Mk:Landroid/widget/RadioButton;

    goto :goto_1

    .line 157
    :cond_8
    iget-object v0, p0, Lcom/miui/internal/view/menu/ListMenuItemView;->Ml:Landroid/widget/CheckBox;

    if-eqz v0, :cond_9

    .line 158
    iget-object v0, p0, Lcom/miui/internal/view/menu/ListMenuItemView;->Ml:Landroid/widget/CheckBox;

    invoke-virtual {v0, v4}, Landroid/widget/CheckBox;->setVisibility(I)V

    .line 160
    :cond_9
    iget-object v0, p0, Lcom/miui/internal/view/menu/ListMenuItemView;->Mk:Landroid/widget/RadioButton;

    if-eqz v0, :cond_3

    .line 161
    iget-object v0, p0, Lcom/miui/internal/view/menu/ListMenuItemView;->Mk:Landroid/widget/RadioButton;

    invoke-virtual {v0, v4}, Landroid/widget/RadioButton;->setVisibility(I)V

    goto :goto_2
.end method

.method public setChecked(Z)V
    .locals 1

    .prologue
    .line 178
    iget-object v0, p0, Lcom/miui/internal/view/menu/ListMenuItemView;->uL:Lcom/miui/internal/view/menu/MenuItemImpl;

    invoke-virtual {v0}, Lcom/miui/internal/view/menu/MenuItemImpl;->isExclusiveCheckable()Z

    move-result v0

    if-eqz v0, :cond_1

    .line 179
    iget-object v0, p0, Lcom/miui/internal/view/menu/ListMenuItemView;->Mk:Landroid/widget/RadioButton;

    if-nez v0, :cond_0

    .line 180
    invoke-direct {p0}, Lcom/miui/internal/view/menu/ListMenuItemView;->eh()V

    .line 182
    :cond_0
    iget-object v0, p0, Lcom/miui/internal/view/menu/ListMenuItemView;->Mk:Landroid/widget/RadioButton;

    .line 190
    :goto_0
    invoke-virtual {v0, p1}, Landroid/widget/CompoundButton;->setChecked(Z)V

    .line 192
    invoke-virtual {p0, p1}, Lcom/miui/internal/view/menu/ListMenuItemView;->setActivated(Z)V

    .line 193
    return-void

    .line 184
    :cond_1
    iget-object v0, p0, Lcom/miui/internal/view/menu/ListMenuItemView;->Ml:Landroid/widget/CheckBox;

    if-nez v0, :cond_2

    .line 185
    invoke-direct {p0}, Lcom/miui/internal/view/menu/ListMenuItemView;->ei()V

    .line 187
    :cond_2
    iget-object v0, p0, Lcom/miui/internal/view/menu/ListMenuItemView;->Ml:Landroid/widget/CheckBox;

    goto :goto_0
.end method

.method public setForceShowIcon(Z)V
    .locals 0

    .prologue
    .line 100
    iput-boolean p1, p0, Lcom/miui/internal/view/menu/ListMenuItemView;->nt:Z

    iput-boolean p1, p0, Lcom/miui/internal/view/menu/ListMenuItemView;->Mq:Z

    .line 101
    return-void
.end method

.method public setIcon(Landroid/graphics/drawable/Drawable;)V
    .locals 3

    .prologue
    const/4 v1, 0x0

    .line 213
    iget-object v0, p0, Lcom/miui/internal/view/menu/ListMenuItemView;->uL:Lcom/miui/internal/view/menu/MenuItemImpl;

    invoke-virtual {v0}, Lcom/miui/internal/view/menu/MenuItemImpl;->shouldShowIcon()Z

    move-result v0

    if-nez v0, :cond_0

    iget-boolean v0, p0, Lcom/miui/internal/view/menu/ListMenuItemView;->nt:Z

    if-eqz v0, :cond_2

    :cond_0
    const/4 v0, 0x1

    .line 214
    :goto_0
    if-nez v0, :cond_3

    iget-boolean v2, p0, Lcom/miui/internal/view/menu/ListMenuItemView;->Mq:Z

    if-nez v2, :cond_3

    .line 235
    :cond_1
    :goto_1
    return-void

    :cond_2
    move v0, v1

    .line 213
    goto :goto_0

    .line 218
    :cond_3
    iget-object v2, p0, Lcom/miui/internal/view/menu/ListMenuItemView;->n:Landroid/widget/ImageView;

    if-nez v2, :cond_4

    if-nez p1, :cond_4

    iget-boolean v2, p0, Lcom/miui/internal/view/menu/ListMenuItemView;->Mq:Z

    if-eqz v2, :cond_1

    .line 222
    :cond_4
    iget-object v2, p0, Lcom/miui/internal/view/menu/ListMenuItemView;->n:Landroid/widget/ImageView;

    if-nez v2, :cond_5

    .line 223
    invoke-direct {p0}, Lcom/miui/internal/view/menu/ListMenuItemView;->eg()V

    .line 226
    :cond_5
    if-nez p1, :cond_6

    iget-boolean v2, p0, Lcom/miui/internal/view/menu/ListMenuItemView;->Mq:Z

    if-eqz v2, :cond_8

    .line 227
    :cond_6
    iget-object v2, p0, Lcom/miui/internal/view/menu/ListMenuItemView;->n:Landroid/widget/ImageView;

    if-eqz v0, :cond_7

    :goto_2
    invoke-virtual {v2, p1}, Landroid/widget/ImageView;->setImageDrawable(Landroid/graphics/drawable/Drawable;)V

    .line 229
    iget-object v0, p0, Lcom/miui/internal/view/menu/ListMenuItemView;->n:Landroid/widget/ImageView;

    invoke-virtual {v0}, Landroid/widget/ImageView;->getVisibility()I

    move-result v0

    if-eqz v0, :cond_1

    .line 230
    iget-object v0, p0, Lcom/miui/internal/view/menu/ListMenuItemView;->n:Landroid/widget/ImageView;

    invoke-virtual {v0, v1}, Landroid/widget/ImageView;->setVisibility(I)V

    goto :goto_1

    .line 227
    :cond_7
    const/4 p1, 0x0

    goto :goto_2

    .line 233
    :cond_8
    iget-object v0, p0, Lcom/miui/internal/view/menu/ListMenuItemView;->n:Landroid/widget/ImageView;

    const/16 v1, 0x8

    invoke-virtual {v0, v1}, Landroid/widget/ImageView;->setVisibility(I)V

    goto :goto_1
.end method

.method public setItemInvoker(Lcom/miui/internal/view/menu/MenuBuilder$ItemInvoker;)V
    .locals 0

    .prologue
    .line 197
    return-void
.end method

.method public setShortcut(ZC)V
    .locals 3

    .prologue
    .line 200
    if-eqz p1, :cond_2

    iget-object v0, p0, Lcom/miui/internal/view/menu/ListMenuItemView;->uL:Lcom/miui/internal/view/menu/MenuItemImpl;

    invoke-virtual {v0}, Lcom/miui/internal/view/menu/MenuItemImpl;->eT()Z

    move-result v0

    if-eqz v0, :cond_2

    const/4 v0, 0x0

    .line 203
    :goto_0
    if-nez v0, :cond_0

    .line 204
    iget-object v1, p0, Lcom/miui/internal/view/menu/ListMenuItemView;->Mm:Landroid/widget/TextView;

    iget-object v2, p0, Lcom/miui/internal/view/menu/ListMenuItemView;->uL:Lcom/miui/internal/view/menu/MenuItemImpl;

    invoke-virtual {v2}, Lcom/miui/internal/view/menu/MenuItemImpl;->eS()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 207
    :cond_0
    iget-object v1, p0, Lcom/miui/internal/view/menu/ListMenuItemView;->Mm:Landroid/widget/TextView;

    invoke-virtual {v1}, Landroid/widget/TextView;->getVisibility()I

    move-result v1

    if-eq v1, v0, :cond_1

    .line 208
    iget-object v1, p0, Lcom/miui/internal/view/menu/ListMenuItemView;->Mm:Landroid/widget/TextView;

    invoke-virtual {v1, v0}, Landroid/widget/TextView;->setVisibility(I)V

    .line 210
    :cond_1
    return-void

    .line 200
    :cond_2
    const/16 v0, 0x8

    goto :goto_0
.end method

.method public setTitle(Ljava/lang/CharSequence;)V
    .locals 2

    .prologue
    const/16 v1, 0x8

    .line 104
    if-eqz p1, :cond_1

    .line 105
    iget-object v0, p0, Lcom/miui/internal/view/menu/ListMenuItemView;->hJ:Landroid/widget/TextView;

    invoke-virtual {v0, p1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 107
    iget-object v0, p0, Lcom/miui/internal/view/menu/ListMenuItemView;->hJ:Landroid/widget/TextView;

    invoke-virtual {v0}, Landroid/widget/TextView;->getVisibility()I

    move-result v0

    if-eqz v0, :cond_0

    .line 108
    iget-object v0, p0, Lcom/miui/internal/view/menu/ListMenuItemView;->hJ:Landroid/widget/TextView;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setVisibility(I)V

    .line 115
    :cond_0
    :goto_0
    return-void

    .line 111
    :cond_1
    iget-object v0, p0, Lcom/miui/internal/view/menu/ListMenuItemView;->hJ:Landroid/widget/TextView;

    invoke-virtual {v0}, Landroid/widget/TextView;->getVisibility()I

    move-result v0

    if-eq v0, v1, :cond_0

    .line 112
    iget-object v0, p0, Lcom/miui/internal/view/menu/ListMenuItemView;->hJ:Landroid/widget/TextView;

    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setVisibility(I)V

    goto :goto_0
.end method

.method public showsIcon()Z
    .locals 1

    .prologue
    .line 277
    iget-boolean v0, p0, Lcom/miui/internal/view/menu/ListMenuItemView;->nt:Z

    return v0
.end method
