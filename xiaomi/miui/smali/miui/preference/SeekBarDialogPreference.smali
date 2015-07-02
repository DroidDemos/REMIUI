.class public Lmiui/preference/SeekBarDialogPreference;
.super Landroid/preference/DialogPreference;
.source "SourceFile"


# instance fields
.field private ak:Landroid/graphics/drawable/Drawable;


# direct methods
.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 1

    .prologue
    .line 24
    invoke-direct {p0, p1, p2}, Landroid/preference/DialogPreference;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    .line 26
    sget v0, Lcom/miui/internal/R$layout;->seekbar_dialog:I

    invoke-virtual {p0, v0}, Lmiui/preference/SeekBarDialogPreference;->setDialogLayoutResource(I)V

    .line 27
    invoke-virtual {p0}, Lmiui/preference/SeekBarDialogPreference;->createActionButtons()V

    .line 30
    invoke-virtual {p0}, Lmiui/preference/SeekBarDialogPreference;->getDialogIcon()Landroid/graphics/drawable/Drawable;

    move-result-object v0

    iput-object v0, p0, Lmiui/preference/SeekBarDialogPreference;->ak:Landroid/graphics/drawable/Drawable;

    .line 31
    const/4 v0, 0x0

    invoke-virtual {p0, v0}, Lmiui/preference/SeekBarDialogPreference;->setDialogIcon(Landroid/graphics/drawable/Drawable;)V

    .line 32
    return-void
.end method

.method protected static getSeekBar(Landroid/view/View;)Lmiui/widget/SeekBar;
    .locals 1

    .prologue
    .line 61
    sget v0, Lcom/miui/internal/R$id;->seekbar:I

    invoke-virtual {p0, v0}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Lmiui/widget/SeekBar;

    return-object v0
.end method


# virtual methods
.method protected createActionButtons()V
    .locals 1

    .prologue
    .line 38
    const v0, 0x104000a

    invoke-virtual {p0, v0}, Lmiui/preference/SeekBarDialogPreference;->setPositiveButtonText(I)V

    .line 39
    const/high16 v0, 0x1040000

    invoke-virtual {p0, v0}, Lmiui/preference/SeekBarDialogPreference;->setNegativeButtonText(I)V

    .line 40
    return-void
.end method

.method protected onBindDialogView(Landroid/view/View;)V
    .locals 2

    .prologue
    .line 44
    invoke-super {p0, p1}, Landroid/preference/DialogPreference;->onBindDialogView(Landroid/view/View;)V

    .line 46
    sget v0, Lcom/miui/internal/R$id;->icon:I

    invoke-virtual {p1, v0}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/ImageView;

    .line 47
    iget-object v1, p0, Lmiui/preference/SeekBarDialogPreference;->ak:Landroid/graphics/drawable/Drawable;

    if-eqz v1, :cond_0

    .line 48
    iget-object v1, p0, Lmiui/preference/SeekBarDialogPreference;->ak:Landroid/graphics/drawable/Drawable;

    invoke-virtual {v0, v1}, Landroid/widget/ImageView;->setImageDrawable(Landroid/graphics/drawable/Drawable;)V

    .line 52
    :goto_0
    return-void

    .line 50
    :cond_0
    const/16 v1, 0x8

    invoke-virtual {v0, v1}, Landroid/widget/ImageView;->setVisibility(I)V

    goto :goto_0
.end method
