.class final Lcom/miui/internal/analytics/b;
.super Lmiui/util/SoftReferenceSingleton;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/internal/analytics/PersistenceHelper;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x8
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lmiui/util/SoftReferenceSingleton",
        "<",
        "Lcom/miui/internal/analytics/PersistenceHelper;",
        ">;"
    }
.end annotation


# direct methods
.method constructor <init>()V
    .locals 0

    .prologue
    .line 37
    invoke-direct {p0}, Lmiui/util/SoftReferenceSingleton;-><init>()V

    return-void
.end method


# virtual methods
.method protected D()Lcom/miui/internal/analytics/PersistenceHelper;
    .locals 2

    .prologue
    .line 40
    new-instance v0, Lcom/miui/internal/analytics/PersistenceHelper;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lcom/miui/internal/analytics/PersistenceHelper;-><init>(Lcom/miui/internal/analytics/b;)V

    return-object v0
.end method

.method protected bridge synthetic createInstance()Ljava/lang/Object;
    .locals 1

    .prologue
    .line 37
    invoke-virtual {p0}, Lcom/miui/internal/analytics/b;->D()Lcom/miui/internal/analytics/PersistenceHelper;

    move-result-object v0

    return-object v0
.end method
