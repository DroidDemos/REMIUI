.class public Lmiui/util/Pools$SoftReferencePool;
.super Lmiui/util/Pools$c;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/util/Pools;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x9
    name = "SoftReferencePool"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "<T:",
        "Ljava/lang/Object;",
        ">",
        "Lmiui/util/Pools$c",
        "<TT;>;"
    }
.end annotation


# direct methods
.method constructor <init>(Lmiui/util/Pools$Manager;I)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lmiui/util/Pools$Manager",
            "<TT;>;I)V"
        }
    .end annotation

    .prologue
    .line 500
    invoke-direct {p0, p1, p2}, Lmiui/util/Pools$c;-><init>(Lmiui/util/Pools$Manager;I)V

    .line 501
    return-void
.end method


# virtual methods
.method final a(Ljava/lang/Class;I)Lmiui/util/Pools$b;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/Class",
            "<TT;>;I)",
            "Lmiui/util/Pools$b",
            "<TT;>;"
        }
    .end annotation

    .prologue
    .line 505
    invoke-static {p1, p2}, Lmiui/util/Pools;->c(Ljava/lang/Class;I)Lmiui/util/Pools$d;

    move-result-object v0

    return-object v0
.end method

.method final a(Lmiui/util/Pools$b;I)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lmiui/util/Pools$b",
            "<TT;>;I)V"
        }
    .end annotation

    .prologue
    .line 510
    check-cast p1, Lmiui/util/Pools$d;

    invoke-static {p1, p2}, Lmiui/util/Pools;->a(Lmiui/util/Pools$d;I)V

    .line 512
    return-void
.end method

.method public bridge synthetic acquire()Ljava/lang/Object;
    .locals 1

    .prologue
    .line 496
    invoke-super {p0}, Lmiui/util/Pools$c;->acquire()Ljava/lang/Object;

    move-result-object v0

    return-object v0
.end method

.method public bridge synthetic close()V
    .locals 0

    .prologue
    .line 496
    invoke-super {p0}, Lmiui/util/Pools$c;->close()V

    return-void
.end method

.method public bridge synthetic getSize()I
    .locals 1

    .prologue
    .line 496
    invoke-super {p0}, Lmiui/util/Pools$c;->getSize()I

    move-result v0

    return v0
.end method

.method public bridge synthetic release(Ljava/lang/Object;)V
    .locals 0

    .prologue
    .line 496
    invoke-super {p0, p1}, Lmiui/util/Pools$c;->release(Ljava/lang/Object;)V

    return-void
.end method
