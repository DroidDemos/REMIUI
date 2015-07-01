.class final Lcom/miui/internal/analytics/c;
.super Lmiui/util/SoftReferenceSingleton;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/internal/analytics/a;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x8
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lmiui/util/SoftReferenceSingleton",
        "<",
        "Lcom/miui/internal/analytics/a;",
        ">;"
    }
.end annotation


# direct methods
.method constructor <init>()V
    .locals 0

    .prologue
    .line 65
    invoke-direct {p0}, Lmiui/util/SoftReferenceSingleton;-><init>()V

    return-void
.end method


# virtual methods
.method protected P()Lcom/miui/internal/analytics/a;
    .locals 2

    .prologue
    .line 68
    new-instance v0, Lcom/miui/internal/analytics/a;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lcom/miui/internal/analytics/a;-><init>(Lcom/miui/internal/analytics/a$1;)V

    return-object v0
.end method

.method protected bridge synthetic createInstance()Ljava/lang/Object;
    .locals 1

    .prologue
    .line 65
    invoke-virtual {p0}, Lcom/miui/internal/analytics/c;->P()Lcom/miui/internal/analytics/a;

    move-result-object v0

    return-object v0
.end method
