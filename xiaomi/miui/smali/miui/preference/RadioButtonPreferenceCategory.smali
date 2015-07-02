.class public Lmiui/preference/RadioButtonPreferenceCategory;
.super Landroid/preference/PreferenceCategory;
.source "SourceFile"

# interfaces
.implements Landroid/preference/Preference$OnPreferenceChangeListener;


# static fields
.field private static final TAG:Ljava/lang/String; = "RadioButtonPreferenceCategory"


# instance fields
.field private Qi:Lmiui/preference/RadioButtonPreference;

.field private Qj:I


# direct methods
.method public constructor <init>(Landroid/content/Context;)V
    .locals 1

    .prologue
    .line 61
    const/4 v0, 0x0

    invoke-direct {p0, p1, v0}, Lmiui/preference/RadioButtonPreferenceCategory;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    .line 62
    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 1

    .prologue
    .line 52
    invoke-direct {p0, p1, p2}, Landroid/preference/PreferenceCategory;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    .line 23
    const/4 v0, -0x1

    iput v0, p0, Lmiui/preference/RadioButtonPreferenceCategory;->Qj:I

    .line 53
    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V
    .locals 1

    .prologue
    .line 39
    invoke-direct {p0, p1, p2, p3}, Landroid/preference/PreferenceCategory;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    .line 23
    const/4 v0, -0x1

    iput v0, p0, Lmiui/preference/RadioButtonPreferenceCategory;->Qj:I

    .line 40
    return-void
.end method


# virtual methods
.method public addPreference(Landroid/preference/Preference;)Z
    .locals 2

    .prologue
    .line 66
    instance-of v0, p1, Lmiui/preference/RadioButtonPreference;

    if-nez v0, :cond_0

    .line 67
    new-instance v0, Ljava/lang/IllegalArgumentException;

    const-string v1, "Only RadioButtonPreference can be added toRadioButtonPreferenceCategory"

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 69
    :cond_0
    invoke-super {p0, p1}, Landroid/preference/PreferenceCategory;->addPreference(Landroid/preference/Preference;)Z

    move-result v0

    .line 70
    if-eqz v0, :cond_1

    .line 71
    invoke-virtual {p1, p0}, Landroid/preference/Preference;->setOnPreferenceChangeListener(Landroid/preference/Preference$OnPreferenceChangeListener;)V

    .line 74
    :cond_1
    return v0
.end method

.method public getCheckedPosition()I
    .locals 1

    .prologue
    .line 133
    iget v0, p0, Lmiui/preference/RadioButtonPreferenceCategory;->Qj:I

    return v0
.end method

.method public getCheckedPreference()Landroid/preference/Preference;
    .locals 1

    .prologue
    .line 124
    iget-object v0, p0, Lmiui/preference/RadioButtonPreferenceCategory;->Qi:Lmiui/preference/RadioButtonPreference;

    return-object v0
.end method

.method public onPreferenceChange(Landroid/preference/Preference;Ljava/lang/Object;)Z
    .locals 1

    .prologue
    .line 138
    iget-object v0, p0, Lmiui/preference/RadioButtonPreferenceCategory;->Qi:Lmiui/preference/RadioButtonPreference;

    if-eq p1, v0, :cond_0

    .line 139
    invoke-virtual {p0, p1}, Lmiui/preference/RadioButtonPreferenceCategory;->setCheckedPreference(Landroid/preference/Preference;)V

    .line 141
    :cond_0
    invoke-virtual {p0}, Lmiui/preference/RadioButtonPreferenceCategory;->getOnPreferenceClickListener()Landroid/preference/Preference$OnPreferenceClickListener;

    move-result-object v0

    .line 142
    if-eqz v0, :cond_1

    .line 143
    invoke-interface {v0, p0}, Landroid/preference/Preference$OnPreferenceClickListener;->onPreferenceClick(Landroid/preference/Preference;)Z

    .line 145
    :cond_1
    const/4 v0, 0x0

    return v0
.end method

.method public setCheckedPosition(I)V
    .locals 1

    .prologue
    .line 115
    invoke-virtual {p0, p1}, Lmiui/preference/RadioButtonPreferenceCategory;->getPreference(I)Landroid/preference/Preference;

    move-result-object v0

    invoke-virtual {p0, v0}, Lmiui/preference/RadioButtonPreferenceCategory;->setCheckedPreference(Landroid/preference/Preference;)V

    .line 116
    return-void
.end method

.method public setCheckedPreference(Landroid/preference/Preference;)V
    .locals 5

    .prologue
    const/4 v2, 0x0

    .line 96
    invoke-virtual {p0}, Lmiui/preference/RadioButtonPreferenceCategory;->getPreferenceCount()I

    move-result v3

    move v1, v2

    .line 97
    :goto_0
    if-ge v1, v3, :cond_1

    .line 98
    invoke-virtual {p0, v1}, Lmiui/preference/RadioButtonPreferenceCategory;->getPreference(I)Landroid/preference/Preference;

    move-result-object v0

    check-cast v0, Lmiui/preference/RadioButtonPreference;

    .line 99
    if-ne v0, p1, :cond_0

    .line 100
    iput-object v0, p0, Lmiui/preference/RadioButtonPreferenceCategory;->Qi:Lmiui/preference/RadioButtonPreference;

    .line 101
    iput v1, p0, Lmiui/preference/RadioButtonPreferenceCategory;->Qj:I

    .line 102
    const/4 v4, 0x1

    invoke-virtual {v0, v4}, Lmiui/preference/RadioButtonPreference;->setChecked(Z)V

    .line 97
    :goto_1
    add-int/lit8 v0, v1, 0x1

    move v1, v0

    goto :goto_0

    .line 104
    :cond_0
    invoke-virtual {v0, v2}, Lmiui/preference/RadioButtonPreference;->setChecked(Z)V

    goto :goto_1

    .line 107
    :cond_1
    return-void
.end method

.method public unCheckAllPreference()V
    .locals 4

    .prologue
    const/4 v2, 0x0

    .line 83
    invoke-virtual {p0}, Lmiui/preference/RadioButtonPreferenceCategory;->getPreferenceCount()I

    move-result v3

    move v1, v2

    .line 84
    :goto_0
    if-ge v1, v3, :cond_0

    .line 85
    invoke-virtual {p0, v1}, Lmiui/preference/RadioButtonPreferenceCategory;->getPreference(I)Landroid/preference/Preference;

    move-result-object v0

    check-cast v0, Lmiui/preference/RadioButtonPreference;

    .line 86
    invoke-virtual {v0, v2}, Lmiui/preference/RadioButtonPreference;->setChecked(Z)V

    .line 84
    add-int/lit8 v0, v1, 0x1

    move v1, v0

    goto :goto_0

    .line 88
    :cond_0
    return-void
.end method
