.class Lmiui/preference/ButtonPreference$1;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroid/view/View$OnClickListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lmiui/preference/ButtonPreference;->onCreateView(Landroid/view/ViewGroup;)Landroid/view/View;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic mc:Lmiui/preference/ButtonPreference;


# direct methods
.method constructor <init>(Lmiui/preference/ButtonPreference;)V
    .locals 0

    .prologue
    .line 72
    iput-object p1, p0, Lmiui/preference/ButtonPreference$1;->mc:Lmiui/preference/ButtonPreference;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onClick(Landroid/view/View;)V
    .locals 2

    .prologue
    .line 75
    iget-object v0, p0, Lmiui/preference/ButtonPreference$1;->mc:Lmiui/preference/ButtonPreference;

    invoke-static {v0}, Lmiui/preference/ButtonPreference;->a(Lmiui/preference/ButtonPreference;)Landroid/preference/Preference$OnPreferenceClickListener;

    move-result-object v0

    if-eqz v0, :cond_0

    .line 76
    iget-object v0, p0, Lmiui/preference/ButtonPreference$1;->mc:Lmiui/preference/ButtonPreference;

    invoke-static {v0}, Lmiui/preference/ButtonPreference;->a(Lmiui/preference/ButtonPreference;)Landroid/preference/Preference$OnPreferenceClickListener;

    move-result-object v0

    iget-object v1, p0, Lmiui/preference/ButtonPreference$1;->mc:Lmiui/preference/ButtonPreference;

    invoke-interface {v0, v1}, Landroid/preference/Preference$OnPreferenceClickListener;->onPreferenceClick(Landroid/preference/Preference;)Z

    .line 78
    :cond_0
    return-void
.end method
