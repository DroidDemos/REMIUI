.class final Lcom/miui/internal/analytics/e;
.super Lmiui/util/SoftReferenceSingleton;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/internal/analytics/XiaomiServer;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x8
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lmiui/util/SoftReferenceSingleton",
        "<",
        "Lcom/miui/internal/analytics/XiaomiServer;",
        ">;"
    }
.end annotation


# direct methods
.method constructor <init>()V
    .locals 0

    .prologue
    .line 45
    invoke-direct {p0}, Lmiui/util/SoftReferenceSingleton;-><init>()V

    return-void
.end method


# virtual methods
.method protected cZ()Lcom/miui/internal/analytics/XiaomiServer;
    .locals 2

    .prologue
    .line 48
    new-instance v0, Lcom/miui/internal/analytics/XiaomiServer;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lcom/miui/internal/analytics/XiaomiServer;-><init>(Lcom/miui/internal/analytics/e;)V

    return-object v0
.end method

.method protected bridge synthetic createInstance()Ljava/lang/Object;
    .locals 1

    .prologue
    .line 45
    invoke-virtual {p0}, Lcom/miui/internal/analytics/e;->cZ()Lcom/miui/internal/analytics/XiaomiServer;

    move-result-object v0

    return-object v0
.end method
