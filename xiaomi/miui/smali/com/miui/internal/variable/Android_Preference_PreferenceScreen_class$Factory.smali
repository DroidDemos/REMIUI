.class public Lcom/miui/internal/variable/Android_Preference_PreferenceScreen_class$Factory;
.super Lcom/miui/internal/variable/AbsClassFactory;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/internal/variable/Android_Preference_PreferenceScreen_class;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x9
    name = "Factory"
.end annotation


# static fields
.field private static final f:Lmiui/util/SoftReferenceSingleton;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lmiui/util/SoftReferenceSingleton",
            "<",
            "Lcom/miui/internal/variable/Android_Preference_PreferenceScreen_class$Factory;",
            ">;"
        }
    .end annotation
.end field


# instance fields
.field private Qy:Lcom/miui/internal/variable/Android_Preference_PreferenceScreen_class;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .prologue
    .line 25
    new-instance v0, Lcom/miui/internal/variable/w;

    invoke-direct {v0}, Lcom/miui/internal/variable/w;-><init>()V

    sput-object v0, Lcom/miui/internal/variable/Android_Preference_PreferenceScreen_class$Factory;->f:Lmiui/util/SoftReferenceSingleton;

    return-void
.end method

.method private constructor <init>()V
    .locals 1

    .prologue
    .line 35
    invoke-direct {p0}, Lcom/miui/internal/variable/AbsClassFactory;-><init>()V

    .line 36
    new-instance v0, Lcom/miui/internal/variable/Android_Preference_PreferenceScreen_class;

    invoke-direct {v0}, Lcom/miui/internal/variable/Android_Preference_PreferenceScreen_class;-><init>()V

    iput-object v0, p0, Lcom/miui/internal/variable/Android_Preference_PreferenceScreen_class$Factory;->Qy:Lcom/miui/internal/variable/Android_Preference_PreferenceScreen_class;

    .line 37
    return-void
.end method

.method synthetic constructor <init>(Lcom/miui/internal/variable/Android_Preference_PreferenceScreen_class$1;)V
    .locals 0

    .prologue
    .line 23
    invoke-direct {p0}, Lcom/miui/internal/variable/Android_Preference_PreferenceScreen_class$Factory;-><init>()V

    return-void
.end method

.method public static getInstance()Lcom/miui/internal/variable/Android_Preference_PreferenceScreen_class$Factory;
    .locals 1

    .prologue
    .line 40
    sget-object v0, Lcom/miui/internal/variable/Android_Preference_PreferenceScreen_class$Factory;->f:Lmiui/util/SoftReferenceSingleton;

    invoke-virtual {v0}, Lmiui/util/SoftReferenceSingleton;->get()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/variable/Android_Preference_PreferenceScreen_class$Factory;

    return-object v0
.end method


# virtual methods
.method public get()Lcom/miui/internal/variable/Android_Preference_PreferenceScreen_class;
    .locals 1

    .prologue
    .line 45
    iget-object v0, p0, Lcom/miui/internal/variable/Android_Preference_PreferenceScreen_class$Factory;->Qy:Lcom/miui/internal/variable/Android_Preference_PreferenceScreen_class;

    return-object v0
.end method

.method public bridge synthetic get()Ljava/lang/Object;
    .locals 1

    .prologue
    .line 23
    invoke-virtual {p0}, Lcom/miui/internal/variable/Android_Preference_PreferenceScreen_class$Factory;->get()Lcom/miui/internal/variable/Android_Preference_PreferenceScreen_class;

    move-result-object v0

    return-object v0
.end method
