.class final Lmiui/text/b;
.super Lmiui/util/SoftReferenceSingleton;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/text/SortKeyGenerator;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x8
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lmiui/util/SoftReferenceSingleton",
        "<",
        "Lmiui/text/SortKeyGenerator;",
        ">;"
    }
.end annotation


# direct methods
.method constructor <init>()V
    .locals 0

    .prologue
    .line 39
    invoke-direct {p0}, Lmiui/util/SoftReferenceSingleton;-><init>()V

    return-void
.end method


# virtual methods
.method protected bq()Lmiui/text/SortKeyGenerator;
    .locals 2

    .prologue
    .line 42
    new-instance v0, Lmiui/text/SortKeyGenerator;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lmiui/text/SortKeyGenerator;-><init>(Lmiui/text/b;)V

    return-object v0
.end method

.method protected bridge synthetic createInstance()Ljava/lang/Object;
    .locals 1

    .prologue
    .line 39
    invoke-virtual {p0}, Lmiui/text/b;->bq()Lmiui/text/SortKeyGenerator;

    move-result-object v0

    return-object v0
.end method
