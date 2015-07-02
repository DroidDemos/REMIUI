.class public Lmiui/preference/ValuePreference;
.super Landroid/preference/Preference;
.source "SourceFile"


# instance fields
.field private qu:Ljava/lang/CharSequence;

.field private qv:I

.field private qw:Z

.field private qx:Landroid/graphics/drawable/Drawable;


# direct methods
.method public constructor <init>(Landroid/content/Context;)V
    .locals 1

    .prologue
    .line 66
    const/4 v0, 0x0

    invoke-direct {p0, p1, v0}, Lmiui/preference/ValuePreference;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    .line 67
    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 1

    .prologue
    .line 56
    invoke-direct {p0, p1, p2}, Landroid/preference/Preference;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    .line 57
    sget v0, Lcom/miui/internal/R$layout;->preference_value:I

    invoke-virtual {p0, v0}, Lmiui/preference/ValuePreference;->setLayoutResource(I)V

    .line 58
    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V
    .locals 1

    .prologue
    .line 42
    invoke-direct {p0, p1, p2, p3}, Landroid/preference/Preference;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    .line 43
    sget v0, Lcom/miui/internal/R$layout;->preference_value:I

    invoke-virtual {p0, v0}, Lmiui/preference/ValuePreference;->setLayoutResource(I)V

    .line 44
    return-void
.end method


# virtual methods
.method public getRightArrowDrawable()Landroid/graphics/drawable/Drawable;
    .locals 1

    .prologue
    .line 159
    iget-object v0, p0, Lmiui/preference/ValuePreference;->qx:Landroid/graphics/drawable/Drawable;

    return-object v0
.end method

.method public getValue()Ljava/lang/CharSequence;
    .locals 1

    .prologue
    .line 107
    iget-object v0, p0, Lmiui/preference/ValuePreference;->qu:Ljava/lang/CharSequence;

    return-object v0
.end method

.method public getValueRes()I
    .locals 1

    .prologue
    .line 98
    iget v0, p0, Lmiui/preference/ValuePreference;->qv:I

    return v0
.end method

.method public isShowRightArrow()Z
    .locals 1

    .prologue
    .line 116
    iget-boolean v0, p0, Lmiui/preference/ValuePreference;->qw:Z

    return v0
.end method

.method protected onBindView(Landroid/view/View;)V
    .locals 5

    .prologue
    const/16 v3, 0x8

    const/4 v2, 0x0

    .line 164
    invoke-super {p0, p1}, Landroid/preference/Preference;->onBindView(Landroid/view/View;)V

    .line 165
    sget v0, Lcom/miui/internal/R$id;->value_right:I

    invoke-virtual {p1, v0}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/TextView;

    .line 166
    if-eqz v0, :cond_0

    .line 167
    invoke-virtual {p0}, Lmiui/preference/ValuePreference;->getValue()Ljava/lang/CharSequence;

    move-result-object v1

    .line 168
    invoke-static {v1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v4

    if-nez v4, :cond_2

    .line 169
    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 170
    invoke-virtual {v0, v2}, Landroid/widget/TextView;->setVisibility(I)V

    .line 176
    :cond_0
    :goto_0
    sget v0, Lcom/miui/internal/R$id;->arrow_right:I

    invoke-virtual {p1, v0}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/ImageView;

    .line 177
    if-eqz v0, :cond_1

    .line 178
    iget-boolean v1, p0, Lmiui/preference/ValuePreference;->qw:Z

    if-eqz v1, :cond_3

    move v1, v2

    :goto_1
    invoke-virtual {v0, v1}, Landroid/widget/ImageView;->setVisibility(I)V

    .line 179
    iget-boolean v1, p0, Lmiui/preference/ValuePreference;->qw:Z

    if-eqz v1, :cond_5

    .line 180
    invoke-virtual {v0, v2}, Landroid/widget/ImageView;->setVisibility(I)V

    .line 181
    iget-object v1, p0, Lmiui/preference/ValuePreference;->qx:Landroid/graphics/drawable/Drawable;

    if-eqz v1, :cond_4

    .line 182
    iget-object v1, p0, Lmiui/preference/ValuePreference;->qx:Landroid/graphics/drawable/Drawable;

    invoke-virtual {v0, v1}, Landroid/widget/ImageView;->setImageDrawable(Landroid/graphics/drawable/Drawable;)V

    .line 190
    :cond_1
    :goto_2
    return-void

    .line 172
    :cond_2
    invoke-virtual {v0, v3}, Landroid/widget/TextView;->setVisibility(I)V

    goto :goto_0

    :cond_3
    move v1, v3

    .line 178
    goto :goto_1

    .line 184
    :cond_4
    invoke-virtual {v0}, Landroid/widget/ImageView;->getDrawable()Landroid/graphics/drawable/Drawable;

    move-result-object v0

    iput-object v0, p0, Lmiui/preference/ValuePreference;->qx:Landroid/graphics/drawable/Drawable;

    goto :goto_2

    .line 187
    :cond_5
    invoke-virtual {v0, v3}, Landroid/widget/ImageView;->setVisibility(I)V

    goto :goto_2
.end method

.method public setRightArrowDrawable(Landroid/graphics/drawable/Drawable;)V
    .locals 1

    .prologue
    .line 143
    if-nez p1, :cond_0

    iget-object v0, p0, Lmiui/preference/ValuePreference;->qx:Landroid/graphics/drawable/Drawable;

    if-nez v0, :cond_1

    :cond_0
    if-eqz p1, :cond_2

    iget-object v0, p0, Lmiui/preference/ValuePreference;->qx:Landroid/graphics/drawable/Drawable;

    if-eq v0, p1, :cond_2

    .line 145
    :cond_1
    iput-object p1, p0, Lmiui/preference/ValuePreference;->qx:Landroid/graphics/drawable/Drawable;

    .line 146
    if-eqz p1, :cond_3

    const/4 v0, 0x1

    :goto_0
    invoke-virtual {p0, v0}, Lmiui/preference/ValuePreference;->setShowRightArrow(Z)V

    .line 148
    invoke-virtual {p0}, Lmiui/preference/ValuePreference;->notifyChanged()V

    .line 150
    :cond_2
    return-void

    .line 146
    :cond_3
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public setRightArrowRes(I)V
    .locals 1

    .prologue
    .line 134
    invoke-virtual {p0}, Lmiui/preference/ValuePreference;->getContext()Landroid/content/Context;

    move-result-object v0

    invoke-virtual {v0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    invoke-virtual {v0, p1}, Landroid/content/res/Resources;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v0

    invoke-virtual {p0, v0}, Lmiui/preference/ValuePreference;->setRightArrowDrawable(Landroid/graphics/drawable/Drawable;)V

    .line 135
    return-void
.end method

.method public setShowRightArrow(Z)V
    .locals 0

    .prologue
    .line 125
    iput-boolean p1, p0, Lmiui/preference/ValuePreference;->qw:Z

    .line 126
    return-void
.end method

.method public setValue(I)V
    .locals 1

    .prologue
    .line 88
    invoke-virtual {p0}, Lmiui/preference/ValuePreference;->getContext()Landroid/content/Context;

    move-result-object v0

    invoke-virtual {v0, p1}, Landroid/content/Context;->getString(I)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, v0}, Lmiui/preference/ValuePreference;->setValue(Ljava/lang/String;)V

    .line 89
    iput p1, p0, Lmiui/preference/ValuePreference;->qv:I

    .line 90
    return-void
.end method

.method public setValue(Ljava/lang/String;)V
    .locals 1

    .prologue
    .line 75
    iget-object v0, p0, Lmiui/preference/ValuePreference;->qu:Ljava/lang/CharSequence;

    invoke-static {p1, v0}, Landroid/text/TextUtils;->equals(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_0

    .line 76
    const/4 v0, 0x0

    iput v0, p0, Lmiui/preference/ValuePreference;->qv:I

    .line 77
    iput-object p1, p0, Lmiui/preference/ValuePreference;->qu:Ljava/lang/CharSequence;

    .line 78
    invoke-virtual {p0}, Lmiui/preference/ValuePreference;->notifyChanged()V

    .line 80
    :cond_0
    return-void
.end method
