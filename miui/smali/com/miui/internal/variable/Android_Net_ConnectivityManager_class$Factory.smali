.class public Lcom/miui/internal/variable/Android_Net_ConnectivityManager_class$Factory;
.super Lcom/miui/internal/variable/AbsClassFactory;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/internal/variable/Android_Net_ConnectivityManager_class;
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
            "Lcom/miui/internal/variable/Android_Net_ConnectivityManager_class$Factory;",
            ">;"
        }
    .end annotation
.end field


# instance fields
.field private Fu:Lcom/miui/internal/variable/Android_Net_ConnectivityManager_class;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .prologue
    .line 17
    new-instance v0, Lcom/miui/internal/variable/g;

    invoke-direct {v0}, Lcom/miui/internal/variable/g;-><init>()V

    sput-object v0, Lcom/miui/internal/variable/Android_Net_ConnectivityManager_class$Factory;->f:Lmiui/util/SoftReferenceSingleton;

    return-void
.end method

.method private constructor <init>()V
    .locals 1

    .prologue
    .line 27
    invoke-direct {p0}, Lcom/miui/internal/variable/AbsClassFactory;-><init>()V

    .line 28
    const-string v0, "Android_Net_ConnectivityManager_class"

    .line 29
    invoke-virtual {p0, v0}, Lcom/miui/internal/variable/Android_Net_ConnectivityManager_class$Factory;->create(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/variable/Android_Net_ConnectivityManager_class;

    iput-object v0, p0, Lcom/miui/internal/variable/Android_Net_ConnectivityManager_class$Factory;->Fu:Lcom/miui/internal/variable/Android_Net_ConnectivityManager_class;

    .line 30
    return-void
.end method

.method synthetic constructor <init>(Lcom/miui/internal/variable/Android_Net_ConnectivityManager_class$1;)V
    .locals 0

    .prologue
    .line 15
    invoke-direct {p0}, Lcom/miui/internal/variable/Android_Net_ConnectivityManager_class$Factory;-><init>()V

    return-void
.end method

.method public static getInstance()Lcom/miui/internal/variable/Android_Net_ConnectivityManager_class$Factory;
    .locals 1

    .prologue
    .line 33
    sget-object v0, Lcom/miui/internal/variable/Android_Net_ConnectivityManager_class$Factory;->f:Lmiui/util/SoftReferenceSingleton;

    invoke-virtual {v0}, Lmiui/util/SoftReferenceSingleton;->get()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/variable/Android_Net_ConnectivityManager_class$Factory;

    return-object v0
.end method


# virtual methods
.method public get()Lcom/miui/internal/variable/Android_Net_ConnectivityManager_class;
    .locals 1

    .prologue
    .line 38
    iget-object v0, p0, Lcom/miui/internal/variable/Android_Net_ConnectivityManager_class$Factory;->Fu:Lcom/miui/internal/variable/Android_Net_ConnectivityManager_class;

    return-object v0
.end method

.method public bridge synthetic get()Ljava/lang/Object;
    .locals 1

    .prologue
    .line 15
    invoke-virtual {p0}, Lcom/miui/internal/variable/Android_Net_ConnectivityManager_class$Factory;->get()Lcom/miui/internal/variable/Android_Net_ConnectivityManager_class;

    move-result-object v0

    return-object v0
.end method
