.class public Lcom/miui/internal/variable/Android_Preference_PreferenceScreen_class;
.super Lcom/miui/internal/util/ClassProxy;
.source "SourceFile"

# interfaces
.implements Lcom/miui/internal/variable/IManagedClassProxy;


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/internal/variable/Android_Preference_PreferenceScreen_class$1;,
        Lcom/miui/internal/variable/Android_Preference_PreferenceScreen_class$Factory;
    }
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lcom/miui/internal/util/ClassProxy",
        "<",
        "Landroid/preference/PreferenceScreen;",
        ">;",
        "Lcom/miui/internal/variable/IManagedClassProxy;"
    }
.end annotation


# direct methods
.method public constructor <init>()V
    .locals 1

    .prologue
    .line 50
    const-class v0, Landroid/preference/PreferenceScreen;

    invoke-direct {p0, v0}, Lcom/miui/internal/util/ClassProxy;-><init>(Ljava/lang/Class;)V

    .line 51
    return-void
.end method


# virtual methods
.method public buildProxy()V
    .locals 2

    .prologue
    .line 55
    const-string v0, "bind"

    const-string v1, "(Landroid/widget/ListView;)V"

    invoke-virtual {p0, v0, v1}, Lcom/miui/internal/variable/Android_Preference_PreferenceScreen_class;->attachMethod(Ljava/lang/String;Ljava/lang/String;)J

    .line 56
    return-void
.end method

.method protected handle()V
    .locals 3

    .prologue
    const/4 v2, 0x0

    .line 60
    const-wide/16 v0, 0x0

    invoke-virtual {p0, v0, v1, v2, v2}, Lcom/miui/internal/variable/Android_Preference_PreferenceScreen_class;->handleBind(JLandroid/preference/PreferenceScreen;Landroid/widget/ListView;)V

    .line 61
    return-void
.end method

.method protected handleBind(JLandroid/preference/PreferenceScreen;Landroid/widget/ListView;)V
    .locals 3

    .prologue
    const/4 v2, 0x0

    .line 64
    invoke-virtual {p0, p1, p2, p3, p4}, Lcom/miui/internal/variable/Android_Preference_PreferenceScreen_class;->originalBind(JLandroid/preference/PreferenceScreen;Landroid/widget/ListView;)V

    .line 65
    invoke-virtual {p4}, Landroid/widget/ListView;->getContext()Landroid/content/Context;

    move-result-object v0

    .line 66
    sget v1, Lcom/miui/internal/R$attr;->preferenceScreenPaddingBottom:I

    invoke-static {v0, v1}, Lmiui/util/AttributeResolver;->resolveDimension(Landroid/content/Context;I)F

    move-result v0

    float-to-int v0, v0

    .line 68
    invoke-virtual {p4}, Landroid/widget/ListView;->getAdapter()Landroid/widget/ListAdapter;

    .line 69
    invoke-virtual {p4, v2, v2, v2, v0}, Landroid/widget/ListView;->setPadding(IIII)V

    .line 70
    return-void
.end method

.method protected originalBind(JLandroid/preference/PreferenceScreen;Landroid/widget/ListView;)V
    .locals 2

    .prologue
    .line 73
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "com.miui.internal.variable.Android_Preference_PreferenceScreen_class.originalBind(long, PreferenceScreen, ListView)"

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0
.end method
