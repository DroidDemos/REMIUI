.class public Lmiui/preference/AnimatedPreference;
.super Landroid/preference/CheckBoxPreference;
.source "SourceFile"


# static fields
.field private static final jj:I = 0x0

.field private static final jk:I = 0x1

.field private static final jl:I = 0x2

.field private static final jm:I = 0x4


# instance fields
.field private jn:I

.field private jo:Landroid/graphics/drawable/Drawable;

.field private jp:Landroid/graphics/drawable/Drawable;


# direct methods
.method public constructor <init>(Landroid/content/Context;)V
    .locals 1

    .prologue
    .line 38
    const/4 v0, 0x0

    invoke-direct {p0, p1, v0}, Lmiui/preference/AnimatedPreference;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    .line 39
    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 1

    .prologue
    .line 69
    invoke-direct {p0, p1, p2}, Landroid/preference/CheckBoxPreference;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    .line 28
    const/4 v0, 0x0

    iput v0, p0, Lmiui/preference/AnimatedPreference;->jn:I

    .line 70
    sget v0, Lcom/miui/internal/R$layout;->preference_widget_animated:I

    invoke-virtual {p0, v0}, Lmiui/preference/AnimatedPreference;->setWidgetLayoutResource(I)V

    .line 71
    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V
    .locals 1

    .prologue
    .line 55
    invoke-direct {p0, p1, p2, p3}, Landroid/preference/CheckBoxPreference;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    .line 28
    const/4 v0, 0x0

    iput v0, p0, Lmiui/preference/AnimatedPreference;->jn:I

    .line 56
    sget v0, Lcom/miui/internal/R$layout;->preference_widget_animated:I

    invoke-virtual {p0, v0}, Lmiui/preference/AnimatedPreference;->setWidgetLayoutResource(I)V

    .line 57
    return-void
.end method

.method private c(IZ)V
    .locals 2

    .prologue
    .line 174
    if-eqz p2, :cond_0

    .line 175
    iget v0, p0, Lmiui/preference/AnimatedPreference;->jn:I

    or-int/2addr v0, p1

    iput v0, p0, Lmiui/preference/AnimatedPreference;->jn:I

    .line 179
    :goto_0
    invoke-virtual {p0}, Lmiui/preference/AnimatedPreference;->notifyChanged()V

    .line 180
    return-void

    .line 177
    :cond_0
    iget v0, p0, Lmiui/preference/AnimatedPreference;->jn:I

    xor-int/lit8 v1, p1, -0x1

    and-int/2addr v0, v1

    iput v0, p0, Lmiui/preference/AnimatedPreference;->jn:I

    goto :goto_0
.end method


# virtual methods
.method public isAnimateFinish()Z
    .locals 1

    .prologue
    .line 136
    iget v0, p0, Lmiui/preference/AnimatedPreference;->jn:I

    and-int/lit8 v0, v0, 0x4

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public isAnimateVisible()Z
    .locals 1

    .prologue
    .line 106
    iget v0, p0, Lmiui/preference/AnimatedPreference;->jn:I

    and-int/lit8 v0, v0, 0x1

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public isAnimating()Z
    .locals 1

    .prologue
    .line 121
    iget v0, p0, Lmiui/preference/AnimatedPreference;->jn:I

    and-int/lit8 v0, v0, 0x2

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public onBindView(Landroid/view/View;)V
    .locals 5

    .prologue
    const/16 v4, 0x8

    const/4 v3, 0x0

    .line 75
    invoke-super {p0, p1}, Landroid/preference/CheckBoxPreference;->onBindView(Landroid/view/View;)V

    .line 76
    sget v0, Lcom/miui/internal/R$id;->animate:I

    invoke-virtual {p1, v0}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/widget/AnimatedImageView;

    .line 78
    sget v1, Lcom/miui/internal/R$id;->animate_finish:I

    invoke-virtual {p1, v1}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v1

    check-cast v1, Landroid/widget/ImageView;

    .line 80
    iget-object v2, p0, Lmiui/preference/AnimatedPreference;->jo:Landroid/graphics/drawable/Drawable;

    if-eqz v2, :cond_0

    .line 81
    iget-object v2, p0, Lmiui/preference/AnimatedPreference;->jo:Landroid/graphics/drawable/Drawable;

    invoke-virtual {v0, v2}, Lcom/miui/internal/widget/AnimatedImageView;->setImageDrawable(Landroid/graphics/drawable/Drawable;)V

    .line 83
    :cond_0
    iget-object v2, p0, Lmiui/preference/AnimatedPreference;->jp:Landroid/graphics/drawable/Drawable;

    if-eqz v2, :cond_1

    .line 84
    iget-object v2, p0, Lmiui/preference/AnimatedPreference;->jp:Landroid/graphics/drawable/Drawable;

    invoke-virtual {v1, v2}, Landroid/widget/ImageView;->setImageDrawable(Landroid/graphics/drawable/Drawable;)V

    .line 86
    :cond_1
    invoke-virtual {p0}, Lmiui/preference/AnimatedPreference;->isAnimateVisible()Z

    move-result v2

    if-eqz v2, :cond_2

    move v2, v3

    :goto_0
    invoke-virtual {v0, v2}, Lcom/miui/internal/widget/AnimatedImageView;->setVisibility(I)V

    .line 87
    invoke-virtual {p0}, Lmiui/preference/AnimatedPreference;->isAnimating()Z

    move-result v2

    invoke-virtual {v0, v2}, Lcom/miui/internal/widget/AnimatedImageView;->setAnimating(Z)V

    .line 88
    invoke-virtual {p0}, Lmiui/preference/AnimatedPreference;->isAnimateFinish()Z

    move-result v0

    if-eqz v0, :cond_3

    :goto_1
    invoke-virtual {v1, v3}, Landroid/widget/ImageView;->setVisibility(I)V

    .line 90
    const v0, 0x1020006

    invoke-virtual {p1, v0}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/ImageView;

    .line 91
    invoke-virtual {p0}, Lmiui/preference/AnimatedPreference;->isEnabled()Z

    move-result v1

    if-eqz v1, :cond_4

    const/high16 v1, 0x3f800000

    :goto_2
    invoke-virtual {v0, v1}, Landroid/widget/ImageView;->setAlpha(F)V

    .line 92
    return-void

    :cond_2
    move v2, v4

    .line 86
    goto :goto_0

    :cond_3
    move v3, v4

    .line 88
    goto :goto_1

    .line 91
    :cond_4
    const v1, 0x3e99999a

    goto :goto_2
.end method

.method public setAnimateDrawable(I)V
    .locals 1

    .prologue
    .line 153
    invoke-virtual {p0}, Lmiui/preference/AnimatedPreference;->getContext()Landroid/content/Context;

    move-result-object v0

    invoke-virtual {v0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    invoke-virtual {v0, p1}, Landroid/content/res/Resources;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v0

    invoke-virtual {p0, v0}, Lmiui/preference/AnimatedPreference;->setAnimateDrawable(Landroid/graphics/drawable/Drawable;)V

    .line 154
    return-void
.end method

.method public setAnimateDrawable(Landroid/graphics/drawable/Drawable;)V
    .locals 0

    .prologue
    .line 144
    iput-object p1, p0, Lmiui/preference/AnimatedPreference;->jo:Landroid/graphics/drawable/Drawable;

    .line 145
    invoke-virtual {p0}, Lmiui/preference/AnimatedPreference;->notifyChanged()V

    .line 146
    return-void
.end method

.method public setAnimateFinish(Z)V
    .locals 1

    .prologue
    .line 129
    const/4 v0, 0x4

    invoke-direct {p0, v0, p1}, Lmiui/preference/AnimatedPreference;->c(IZ)V

    .line 130
    return-void
.end method

.method public setAnimateFinishDrawable(I)V
    .locals 1

    .prologue
    .line 170
    invoke-virtual {p0}, Lmiui/preference/AnimatedPreference;->getContext()Landroid/content/Context;

    move-result-object v0

    invoke-virtual {v0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    invoke-virtual {v0, p1}, Landroid/content/res/Resources;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v0

    invoke-virtual {p0, v0}, Lmiui/preference/AnimatedPreference;->setAnimateFinishDrawable(Landroid/graphics/drawable/Drawable;)V

    .line 171
    return-void
.end method

.method public setAnimateFinishDrawable(Landroid/graphics/drawable/Drawable;)V
    .locals 0

    .prologue
    .line 161
    iput-object p1, p0, Lmiui/preference/AnimatedPreference;->jp:Landroid/graphics/drawable/Drawable;

    .line 162
    invoke-virtual {p0}, Lmiui/preference/AnimatedPreference;->notifyChanged()V

    .line 163
    return-void
.end method

.method public setAnimateVisible(Z)V
    .locals 1

    .prologue
    .line 99
    const/4 v0, 0x1

    invoke-direct {p0, v0, p1}, Lmiui/preference/AnimatedPreference;->c(IZ)V

    .line 100
    return-void
.end method

.method public setAnimating(Z)V
    .locals 1

    .prologue
    .line 114
    const/4 v0, 0x3

    invoke-direct {p0, v0, p1}, Lmiui/preference/AnimatedPreference;->c(IZ)V

    .line 115
    return-void
.end method
