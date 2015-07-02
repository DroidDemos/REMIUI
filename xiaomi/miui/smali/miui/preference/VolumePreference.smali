.class public Lmiui/preference/VolumePreference;
.super Lmiui/preference/SeekBarDialogPreference;
.source "SourceFile"

# interfaces
.implements Landroid/preference/PreferenceManager$OnActivityStopListener;
.implements Landroid/view/View$OnKeyListener;


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lmiui/preference/VolumePreference$SeekBarVolumizer;,
        Lmiui/preference/VolumePreference$a;,
        Lmiui/preference/VolumePreference$VolumeStore;
    }
.end annotation


# static fields
.field private static final Or:Lmiui/reflect/Method;

.field private static final Os:Lmiui/reflect/Method;

.field private static final Ot:I

.field private static final TAG:Ljava/lang/String; = "VolumePreference"


# instance fields
.field private Ou:Lmiui/preference/VolumePreference$SeekBarVolumizer;

.field private qW:I


# direct methods
.method static constructor <clinit>()V
    .locals 3

    .prologue
    .line 52
    const-class v0, Landroid/preference/PreferenceManager;

    const-string v1, "registerOnActivityStopListener"

    const-string v2, "(Landroid/preference/PreferenceManager$OnActivityStopListener;)V"

    invoke-static {v0, v1, v2}, Lmiui/reflect/Method;->of(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lmiui/reflect/Method;

    move-result-object v0

    sput-object v0, Lmiui/preference/VolumePreference;->Or:Lmiui/reflect/Method;

    .line 54
    const-class v0, Landroid/preference/PreferenceManager;

    const-string v1, "unregisterOnActivityStopListener"

    const-string v2, "(Landroid/preference/PreferenceManager$OnActivityStopListener;)V"

    invoke-static {v0, v1, v2}, Lmiui/reflect/Method;->of(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lmiui/reflect/Method;

    move-result-object v0

    sput-object v0, Lmiui/preference/VolumePreference;->Os:Lmiui/reflect/Method;

    .line 57
    invoke-static {}, Lmiui/preference/VolumePreference;->eu()I

    move-result v0

    sput v0, Lmiui/preference/VolumePreference;->Ot:I

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 3

    .prologue
    const/4 v2, 0x0

    .line 75
    invoke-direct {p0, p1, p2}, Lmiui/preference/SeekBarDialogPreference;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    .line 77
    sget-object v0, Lcom/miui/internal/R$styleable;->VolumePreference:[I

    invoke-virtual {p1, p2, v0, v2, v2}, Landroid/content/Context;->obtainStyledAttributes(Landroid/util/AttributeSet;[III)Landroid/content/res/TypedArray;

    move-result-object v0

    .line 79
    sget v1, Lmiui/preference/VolumePreference;->Ot:I

    invoke-virtual {v0, v1, v2}, Landroid/content/res/TypedArray;->getInt(II)I

    move-result v1

    iput v1, p0, Lmiui/preference/VolumePreference;->qW:I

    .line 80
    invoke-virtual {v0}, Landroid/content/res/TypedArray;->recycle()V

    .line 81
    return-void
.end method

.method private static eu()I
    .locals 3

    .prologue
    .line 63
    :try_start_0
    const-string v0, "android.R.styleable"

    const-string v1, "VolumePreference_streamType"

    const-string v2, "I"

    invoke-static {v0, v1, v2}, Lmiui/reflect/Field;->of(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lmiui/reflect/Field;

    move-result-object v0

    .line 64
    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Lmiui/reflect/Field;->getInt(Ljava/lang/Object;)I
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    move-result v0

    .line 67
    :goto_0
    return v0

    .line 65
    :catch_0
    move-exception v0

    .line 66
    invoke-virtual {v0}, Ljava/lang/Exception;->printStackTrace()V

    .line 67
    const/4 v0, 0x0

    goto :goto_0
.end method

.method private ev()V
    .locals 6

    .prologue
    const/4 v5, 0x0

    .line 150
    sget-object v0, Lmiui/preference/VolumePreference;->Os:Lmiui/reflect/Method;

    const-class v1, Landroid/preference/PreferenceManager;

    invoke-virtual {p0}, Lmiui/preference/VolumePreference;->getPreferenceManager()Landroid/preference/PreferenceManager;

    move-result-object v2

    const/4 v3, 0x1

    new-array v3, v3, [Ljava/lang/Object;

    const/4 v4, 0x0

    aput-object p0, v3, v4

    invoke-virtual {v0, v1, v2, v3}, Lmiui/reflect/Method;->invoke(Ljava/lang/Class;Ljava/lang/Object;[Ljava/lang/Object;)V

    .line 153
    iget-object v0, p0, Lmiui/preference/VolumePreference;->Ou:Lmiui/preference/VolumePreference$SeekBarVolumizer;

    if-eqz v0, :cond_2

    .line 154
    invoke-virtual {p0}, Lmiui/preference/VolumePreference;->getDialog()Landroid/app/Dialog;

    move-result-object v0

    .line 155
    if-eqz v0, :cond_1

    invoke-virtual {v0}, Landroid/app/Dialog;->isShowing()Z

    move-result v1

    if-eqz v1, :cond_1

    .line 156
    invoke-virtual {v0}, Landroid/app/Dialog;->getWindow()Landroid/view/Window;

    move-result-object v0

    invoke-virtual {v0}, Landroid/view/Window;->getDecorView()Landroid/view/View;

    move-result-object v0

    sget v1, Lcom/miui/internal/R$id;->seekbar:I

    invoke-virtual {v0, v1}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    .line 158
    if-eqz v0, :cond_0

    invoke-virtual {v0, v5}, Landroid/view/View;->setOnKeyListener(Landroid/view/View$OnKeyListener;)V

    .line 160
    :cond_0
    iget-object v0, p0, Lmiui/preference/VolumePreference;->Ou:Lmiui/preference/VolumePreference$SeekBarVolumizer;

    invoke-virtual {v0}, Lmiui/preference/VolumePreference$SeekBarVolumizer;->revertVolume()V

    .line 162
    :cond_1
    iget-object v0, p0, Lmiui/preference/VolumePreference;->Ou:Lmiui/preference/VolumePreference$SeekBarVolumizer;

    invoke-virtual {v0}, Lmiui/preference/VolumePreference$SeekBarVolumizer;->stop()V

    .line 163
    iput-object v5, p0, Lmiui/preference/VolumePreference;->Ou:Lmiui/preference/VolumePreference$SeekBarVolumizer;

    .line 166
    :cond_2
    return-void
.end method


# virtual methods
.method public onActivityStop()V
    .locals 1

    .prologue
    .line 141
    iget-object v0, p0, Lmiui/preference/VolumePreference;->Ou:Lmiui/preference/VolumePreference$SeekBarVolumizer;

    if-eqz v0, :cond_0

    .line 142
    iget-object v0, p0, Lmiui/preference/VolumePreference;->Ou:Lmiui/preference/VolumePreference$SeekBarVolumizer;

    invoke-static {v0}, Lmiui/preference/VolumePreference$SeekBarVolumizer;->a(Lmiui/preference/VolumePreference$SeekBarVolumizer;)V

    .line 144
    :cond_0
    return-void
.end method

.method protected onBindDialogView(Landroid/view/View;)V
    .locals 6

    .prologue
    const/4 v5, 0x1

    .line 89
    invoke-super {p0, p1}, Lmiui/preference/SeekBarDialogPreference;->onBindDialogView(Landroid/view/View;)V

    .line 91
    sget v0, Lcom/miui/internal/R$id;->seekbar:I

    invoke-virtual {p1, v0}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/SeekBar;

    .line 92
    new-instance v1, Lmiui/preference/VolumePreference$SeekBarVolumizer;

    invoke-virtual {p0}, Lmiui/preference/VolumePreference;->getContext()Landroid/content/Context;

    move-result-object v2

    iget v3, p0, Lmiui/preference/VolumePreference;->qW:I

    invoke-direct {v1, p0, v2, v0, v3}, Lmiui/preference/VolumePreference$SeekBarVolumizer;-><init>(Lmiui/preference/VolumePreference;Landroid/content/Context;Landroid/widget/SeekBar;I)V

    iput-object v1, p0, Lmiui/preference/VolumePreference;->Ou:Lmiui/preference/VolumePreference$SeekBarVolumizer;

    .line 94
    sget-object v0, Lmiui/preference/VolumePreference;->Or:Lmiui/reflect/Method;

    const-class v1, Landroid/preference/PreferenceManager;

    invoke-virtual {p0}, Lmiui/preference/VolumePreference;->getPreferenceManager()Landroid/preference/PreferenceManager;

    move-result-object v2

    new-array v3, v5, [Ljava/lang/Object;

    const/4 v4, 0x0

    aput-object p0, v3, v4

    invoke-virtual {v0, v1, v2, v3}, Lmiui/reflect/Method;->invoke(Ljava/lang/Class;Ljava/lang/Object;[Ljava/lang/Object;)V

    .line 99
    invoke-virtual {p1, p0}, Landroid/view/View;->setOnKeyListener(Landroid/view/View$OnKeyListener;)V

    .line 100
    invoke-virtual {p1, v5}, Landroid/view/View;->setFocusableInTouchMode(Z)V

    .line 101
    invoke-virtual {p1}, Landroid/view/View;->requestFocus()Z

    .line 102
    return-void
.end method

.method protected onDialogClosed(Z)V
    .locals 1

    .prologue
    .line 131
    invoke-super {p0, p1}, Lmiui/preference/SeekBarDialogPreference;->onDialogClosed(Z)V

    .line 133
    if-nez p1, :cond_0

    iget-object v0, p0, Lmiui/preference/VolumePreference;->Ou:Lmiui/preference/VolumePreference$SeekBarVolumizer;

    if-eqz v0, :cond_0

    .line 134
    iget-object v0, p0, Lmiui/preference/VolumePreference;->Ou:Lmiui/preference/VolumePreference$SeekBarVolumizer;

    invoke-virtual {v0}, Lmiui/preference/VolumePreference$SeekBarVolumizer;->revertVolume()V

    .line 137
    :cond_0
    invoke-direct {p0}, Lmiui/preference/VolumePreference;->ev()V

    .line 138
    return-void
.end method

.method public onKey(Landroid/view/View;ILandroid/view/KeyEvent;)Z
    .locals 3

    .prologue
    const/4 v1, 0x0

    const/4 v0, 0x1

    .line 106
    iget-object v2, p0, Lmiui/preference/VolumePreference;->Ou:Lmiui/preference/VolumePreference$SeekBarVolumizer;

    if-nez v2, :cond_1

    .line 125
    :cond_0
    :goto_0
    return v0

    .line 107
    :cond_1
    invoke-virtual {p3}, Landroid/view/KeyEvent;->getAction()I

    move-result v2

    if-nez v2, :cond_2

    move v2, v0

    .line 108
    :goto_1
    sparse-switch p2, :sswitch_data_0

    move v0, v1

    .line 125
    goto :goto_0

    :cond_2
    move v2, v1

    .line 107
    goto :goto_1

    .line 110
    :sswitch_0
    if-eqz v2, :cond_0

    .line 111
    iget-object v1, p0, Lmiui/preference/VolumePreference;->Ou:Lmiui/preference/VolumePreference$SeekBarVolumizer;

    const/4 v2, -0x1

    invoke-virtual {v1, v2}, Lmiui/preference/VolumePreference$SeekBarVolumizer;->changeVolumeBy(I)V

    goto :goto_0

    .line 115
    :sswitch_1
    if-eqz v2, :cond_0

    .line 116
    iget-object v1, p0, Lmiui/preference/VolumePreference;->Ou:Lmiui/preference/VolumePreference$SeekBarVolumizer;

    invoke-virtual {v1, v0}, Lmiui/preference/VolumePreference$SeekBarVolumizer;->changeVolumeBy(I)V

    goto :goto_0

    .line 120
    :sswitch_2
    if-eqz v2, :cond_0

    .line 121
    iget-object v1, p0, Lmiui/preference/VolumePreference;->Ou:Lmiui/preference/VolumePreference$SeekBarVolumizer;

    invoke-virtual {v1}, Lmiui/preference/VolumePreference$SeekBarVolumizer;->muteVolume()V

    goto :goto_0

    .line 108
    :sswitch_data_0
    .sparse-switch
        0x18 -> :sswitch_1
        0x19 -> :sswitch_0
        0xa4 -> :sswitch_2
    .end sparse-switch
.end method

.method protected onRestoreInstanceState(Landroid/os/Parcelable;)V
    .locals 2

    .prologue
    .line 191
    if-eqz p1, :cond_0

    invoke-virtual {p1}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v0

    const-class v1, Lmiui/preference/VolumePreference$a;

    invoke-virtual {v0, v1}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_2

    .line 193
    :cond_0
    invoke-super {p0, p1}, Lmiui/preference/SeekBarDialogPreference;->onRestoreInstanceState(Landroid/os/Parcelable;)V

    .line 202
    :cond_1
    :goto_0
    return-void

    .line 197
    :cond_2
    check-cast p1, Lmiui/preference/VolumePreference$a;

    .line 198
    invoke-virtual {p1}, Lmiui/preference/VolumePreference$a;->getSuperState()Landroid/os/Parcelable;

    move-result-object v0

    invoke-super {p0, v0}, Lmiui/preference/SeekBarDialogPreference;->onRestoreInstanceState(Landroid/os/Parcelable;)V

    .line 199
    iget-object v0, p0, Lmiui/preference/VolumePreference;->Ou:Lmiui/preference/VolumePreference$SeekBarVolumizer;

    if-eqz v0, :cond_1

    .line 200
    iget-object v0, p0, Lmiui/preference/VolumePreference;->Ou:Lmiui/preference/VolumePreference$SeekBarVolumizer;

    invoke-virtual {p1}, Lmiui/preference/VolumePreference$a;->ds()Lmiui/preference/VolumePreference$VolumeStore;

    move-result-object v1

    invoke-virtual {v0, v1}, Lmiui/preference/VolumePreference$SeekBarVolumizer;->onRestoreInstanceState(Lmiui/preference/VolumePreference$VolumeStore;)V

    goto :goto_0
.end method

.method protected onSampleStarting(Lmiui/preference/VolumePreference$SeekBarVolumizer;)V
    .locals 1

    .prologue
    .line 169
    iget-object v0, p0, Lmiui/preference/VolumePreference;->Ou:Lmiui/preference/VolumePreference$SeekBarVolumizer;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lmiui/preference/VolumePreference;->Ou:Lmiui/preference/VolumePreference$SeekBarVolumizer;

    if-eq p1, v0, :cond_0

    .line 170
    iget-object v0, p0, Lmiui/preference/VolumePreference;->Ou:Lmiui/preference/VolumePreference$SeekBarVolumizer;

    invoke-virtual {v0}, Lmiui/preference/VolumePreference$SeekBarVolumizer;->stopSample()V

    .line 172
    :cond_0
    return-void
.end method

.method protected onSaveInstanceState()Landroid/os/Parcelable;
    .locals 3

    .prologue
    .line 176
    invoke-super {p0}, Lmiui/preference/SeekBarDialogPreference;->onSaveInstanceState()Landroid/os/Parcelable;

    move-result-object v0

    .line 177
    invoke-virtual {p0}, Lmiui/preference/VolumePreference;->isPersistent()Z

    move-result v1

    if-eqz v1, :cond_0

    .line 186
    :goto_0
    return-object v0

    .line 182
    :cond_0
    new-instance v1, Lmiui/preference/VolumePreference$a;

    invoke-direct {v1, v0}, Lmiui/preference/VolumePreference$a;-><init>(Landroid/os/Parcelable;)V

    .line 183
    iget-object v0, p0, Lmiui/preference/VolumePreference;->Ou:Lmiui/preference/VolumePreference$SeekBarVolumizer;

    if-eqz v0, :cond_1

    .line 184
    iget-object v0, p0, Lmiui/preference/VolumePreference;->Ou:Lmiui/preference/VolumePreference$SeekBarVolumizer;

    invoke-virtual {v1}, Lmiui/preference/VolumePreference$a;->ds()Lmiui/preference/VolumePreference$VolumeStore;

    move-result-object v2

    invoke-virtual {v0, v2}, Lmiui/preference/VolumePreference$SeekBarVolumizer;->onSaveInstanceState(Lmiui/preference/VolumePreference$VolumeStore;)V

    :cond_1
    move-object v0, v1

    .line 186
    goto :goto_0
.end method

.method public setStreamType(I)V
    .locals 0

    .prologue
    .line 84
    iput p1, p0, Lmiui/preference/VolumePreference;->qW:I

    .line 85
    return-void
.end method
