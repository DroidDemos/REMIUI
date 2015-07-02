.class public Lcom/miui/internal/variable/Android_Preference_EditTextPreference_class;
.super Lcom/miui/internal/util/ClassProxy;
.source "SourceFile"

# interfaces
.implements Lcom/miui/internal/variable/IManagedClassProxy;


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/internal/variable/Android_Preference_EditTextPreference_class$1;,
        Lcom/miui/internal/variable/Android_Preference_EditTextPreference_class$Factory;
    }
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lcom/miui/internal/util/ClassProxy",
        "<",
        "Landroid/preference/EditTextPreference;",
        ">;",
        "Lcom/miui/internal/variable/IManagedClassProxy;"
    }
.end annotation


# direct methods
.method public constructor <init>()V
    .locals 1

    .prologue
    .line 49
    const-class v0, Landroid/preference/EditTextPreference;

    invoke-direct {p0, v0}, Lcom/miui/internal/util/ClassProxy;-><init>(Ljava/lang/Class;)V

    .line 50
    return-void
.end method


# virtual methods
.method public buildProxy()V
    .locals 2

    .prologue
    .line 54
    const-string v0, "onAddEditTextToDialogView"

    const-string v1, "(Landroid/view/View;Landroid/widget/EditText;)V"

    invoke-virtual {p0, v0, v1}, Lcom/miui/internal/variable/Android_Preference_EditTextPreference_class;->attachMethod(Ljava/lang/String;Ljava/lang/String;)J

    .line 55
    return-void
.end method

.method protected handle()V
    .locals 6

    .prologue
    const/4 v3, 0x0

    .line 59
    const-wide/16 v1, 0x0

    move-object v0, p0

    move-object v4, v3

    move-object v5, v3

    invoke-virtual/range {v0 .. v5}, Lcom/miui/internal/variable/Android_Preference_EditTextPreference_class;->handleOnAddEditTextToDialogView(JLandroid/preference/EditTextPreference;Landroid/view/View;Landroid/widget/EditText;)V

    .line 60
    return-void
.end method

.method protected handleOnAddEditTextToDialogView(JLandroid/preference/EditTextPreference;Landroid/view/View;Landroid/widget/EditText;)V
    .locals 3

    .prologue
    .line 63
    invoke-virtual/range {p0 .. p5}, Lcom/miui/internal/variable/Android_Preference_EditTextPreference_class;->originalOnAddEditTextToDialogView(JLandroid/preference/EditTextPreference;Landroid/view/View;Landroid/widget/EditText;)V

    .line 64
    sget v0, Lcom/miui/internal/R$id;->edittext_container:I

    invoke-virtual {p4, v0}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/view/ViewGroup;

    .line 66
    if-eqz v0, :cond_0

    .line 67
    const/4 v1, -0x1

    const/4 v2, -0x2

    invoke-virtual {v0, p5, v1, v2}, Landroid/view/ViewGroup;->addView(Landroid/view/View;II)V

    .line 70
    :cond_0
    return-void
.end method

.method protected originalOnAddEditTextToDialogView(JLandroid/preference/EditTextPreference;Landroid/view/View;Landroid/widget/EditText;)V
    .locals 2

    .prologue
    .line 73
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "com.miui.internal.variable.Android_Preference_EditTextPreference_class.originalOnAddEditTextToDialogView(long, EditTextPreference, View, EditText)"

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0
.end method
