.class final Lmiui/util/a$d;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/util/Collection;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/util/a;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x10
    name = "d"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Object;",
        "Ljava/util/Collection",
        "<TV;>;"
    }
.end annotation


# instance fields
.field final synthetic E:Lmiui/util/a;


# direct methods
.method constructor <init>(Lmiui/util/a;)V
    .locals 0

    .prologue
    .line 341
    iput-object p1, p0, Lmiui/util/a$d;->E:Lmiui/util/a;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public add(Ljava/lang/Object;)Z
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(TV;)Z"
        }
    .end annotation

    .prologue
    .line 345
    new-instance v0, Ljava/lang/UnsupportedOperationException;

    invoke-direct {v0}, Ljava/lang/UnsupportedOperationException;-><init>()V

    throw v0
.end method

.method public addAll(Ljava/util/Collection;)Z
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/Collection",
            "<+TV;>;)Z"
        }
    .end annotation

    .prologue
    .line 350
    new-instance v0, Ljava/lang/UnsupportedOperationException;

    invoke-direct {v0}, Ljava/lang/UnsupportedOperationException;-><init>()V

    throw v0
.end method

.method public clear()V
    .locals 1

    .prologue
    .line 355
    iget-object v0, p0, Lmiui/util/a$d;->E:Lmiui/util/a;

    invoke-virtual {v0}, Lmiui/util/a;->U()V

    .line 356
    return-void
.end method

.method public contains(Ljava/lang/Object;)Z
    .locals 1

    .prologue
    .line 360
    iget-object v0, p0, Lmiui/util/a$d;->E:Lmiui/util/a;

    invoke-virtual {v0, p1}, Lmiui/util/a;->d(Ljava/lang/Object;)I

    move-result v0

    if-ltz v0, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public containsAll(Ljava/util/Collection;)Z
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/Collection",
            "<*>;)Z"
        }
    .end annotation

    .prologue
    .line 365
    invoke-interface {p1}, Ljava/util/Collection;->iterator()Ljava/util/Iterator;

    move-result-object v0

    .line 366
    :cond_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_1

    .line 367
    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    invoke-virtual {p0, v1}, Lmiui/util/a$d;->contains(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_0

    .line 368
    const/4 v0, 0x0

    .line 371
    :goto_0
    return v0

    :cond_1
    const/4 v0, 0x1

    goto :goto_0
.end method

.method public isEmpty()Z
    .locals 1

    .prologue
    .line 376
    iget-object v0, p0, Lmiui/util/a$d;->E:Lmiui/util/a;

    invoke-virtual {v0}, Lmiui/util/a;->S()I

    move-result v0

    if-nez v0, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public iterator()Ljava/util/Iterator;
    .locals 3
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/Iterator",
            "<TV;>;"
        }
    .end annotation

    .prologue
    .line 381
    new-instance v0, Lmiui/util/a$e;

    iget-object v1, p0, Lmiui/util/a$d;->E:Lmiui/util/a;

    const/4 v2, 0x1

    invoke-direct {v0, v1, v2}, Lmiui/util/a$e;-><init>(Lmiui/util/a;I)V

    return-object v0
.end method

.method public remove(Ljava/lang/Object;)Z
    .locals 2

    .prologue
    .line 386
    iget-object v0, p0, Lmiui/util/a$d;->E:Lmiui/util/a;

    invoke-virtual {v0, p1}, Lmiui/util/a;->d(Ljava/lang/Object;)I

    move-result v0

    .line 387
    if-ltz v0, :cond_0

    .line 388
    iget-object v1, p0, Lmiui/util/a$d;->E:Lmiui/util/a;

    invoke-virtual {v1, v0}, Lmiui/util/a;->v(I)V

    .line 389
    const/4 v0, 0x1

    .line 391
    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public removeAll(Ljava/util/Collection;)Z
    .locals 5
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/Collection",
            "<*>;)Z"
        }
    .end annotation

    .prologue
    const/4 v2, 0x1

    const/4 v0, 0x0

    .line 396
    iget-object v1, p0, Lmiui/util/a$d;->E:Lmiui/util/a;

    invoke-virtual {v1}, Lmiui/util/a;->S()I

    move-result v3

    move v1, v0

    .line 398
    :goto_0
    if-ge v0, v3, :cond_1

    .line 399
    iget-object v4, p0, Lmiui/util/a$d;->E:Lmiui/util/a;

    invoke-virtual {v4, v0, v2}, Lmiui/util/a;->d(II)Ljava/lang/Object;

    move-result-object v4

    .line 400
    invoke-interface {p1, v4}, Ljava/util/Collection;->contains(Ljava/lang/Object;)Z

    move-result v4

    if-eqz v4, :cond_0

    .line 401
    iget-object v1, p0, Lmiui/util/a$d;->E:Lmiui/util/a;

    invoke-virtual {v1, v0}, Lmiui/util/a;->v(I)V

    .line 402
    add-int/lit8 v0, v0, -0x1

    .line 403
    add-int/lit8 v1, v3, -0x1

    move v3, v1

    move v1, v2

    .line 398
    :cond_0
    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    .line 407
    :cond_1
    return v1
.end method

.method public retainAll(Ljava/util/Collection;)Z
    .locals 5
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/Collection",
            "<*>;)Z"
        }
    .end annotation

    .prologue
    const/4 v2, 0x1

    const/4 v0, 0x0

    .line 412
    iget-object v1, p0, Lmiui/util/a$d;->E:Lmiui/util/a;

    invoke-virtual {v1}, Lmiui/util/a;->S()I

    move-result v3

    move v1, v0

    .line 414
    :goto_0
    if-ge v0, v3, :cond_1

    .line 415
    iget-object v4, p0, Lmiui/util/a$d;->E:Lmiui/util/a;

    invoke-virtual {v4, v0, v2}, Lmiui/util/a;->d(II)Ljava/lang/Object;

    move-result-object v4

    .line 416
    invoke-interface {p1, v4}, Ljava/util/Collection;->contains(Ljava/lang/Object;)Z

    move-result v4

    if-nez v4, :cond_0

    .line 417
    iget-object v1, p0, Lmiui/util/a$d;->E:Lmiui/util/a;

    invoke-virtual {v1, v0}, Lmiui/util/a;->v(I)V

    .line 418
    add-int/lit8 v0, v0, -0x1

    .line 419
    add-int/lit8 v1, v3, -0x1

    move v3, v1

    move v1, v2

    .line 414
    :cond_0
    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    .line 423
    :cond_1
    return v1
.end method

.method public size()I
    .locals 1

    .prologue
    .line 428
    iget-object v0, p0, Lmiui/util/a$d;->E:Lmiui/util/a;

    invoke-virtual {v0}, Lmiui/util/a;->S()I

    move-result v0

    return v0
.end method

.method public toArray()[Ljava/lang/Object;
    .locals 2

    .prologue
    .line 433
    iget-object v0, p0, Lmiui/util/a$d;->E:Lmiui/util/a;

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Lmiui/util/a;->R(I)[Ljava/lang/Object;

    move-result-object v0

    return-object v0
.end method

.method public toArray([Ljava/lang/Object;)[Ljava/lang/Object;
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "<T:",
            "Ljava/lang/Object;",
            ">([TT;)[TT;"
        }
    .end annotation

    .prologue
    .line 438
    iget-object v0, p0, Lmiui/util/a$d;->E:Lmiui/util/a;

    const/4 v1, 0x1

    invoke-virtual {v0, p1, v1}, Lmiui/util/a;->a([Ljava/lang/Object;I)[Ljava/lang/Object;

    move-result-object v0

    return-object v0
.end method
