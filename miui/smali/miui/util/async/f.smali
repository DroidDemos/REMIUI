.class final Lmiui/util/async/f;
.super Lmiui/util/Pools$Manager;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/util/async/e;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x8
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lmiui/util/Pools$Manager",
        "<",
        "Lmiui/util/async/e$a;",
        ">;"
    }
.end annotation


# direct methods
.method constructor <init>()V
    .locals 0

    .prologue
    .line 94
    invoke-direct {p0}, Lmiui/util/Pools$Manager;-><init>()V

    return-void
.end method


# virtual methods
.method public b(Lmiui/util/async/e$a;)V
    .locals 0

    .prologue
    .line 102
    invoke-virtual {p1}, Lmiui/util/async/e$a;->clear()V

    .line 103
    return-void
.end method

.method public bl()Lmiui/util/async/e$a;
    .locals 1

    .prologue
    .line 97
    new-instance v0, Lmiui/util/async/e$a;

    invoke-direct {v0}, Lmiui/util/async/e$a;-><init>()V

    return-object v0
.end method

.method public bridge synthetic createInstance()Ljava/lang/Object;
    .locals 1

    .prologue
    .line 94
    invoke-virtual {p0}, Lmiui/util/async/f;->bl()Lmiui/util/async/e$a;

    move-result-object v0

    return-object v0
.end method

.method public bridge synthetic onRelease(Ljava/lang/Object;)V
    .locals 0

    .prologue
    .line 94
    check-cast p1, Lmiui/util/async/e$a;

    invoke-virtual {p0, p1}, Lmiui/util/async/f;->b(Lmiui/util/async/e$a;)V

    return-void
.end method
