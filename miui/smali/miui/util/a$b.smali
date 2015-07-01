.class final Lmiui/util/a$b;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/util/Set;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/util/a;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x10
    name = "b"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Object;",
        "Ljava/util/Set",
        "<TK;>;"
    }
.end annotation


# instance fields
.field final synthetic E:Lmiui/util/a;


# direct methods
.method constructor <init>(Lmiui/util/a;)V
    .locals 0

    .prologue
    .line 253
    iput-object p1, p0, Lmiui/util/a$b;->E:Lmiui/util/a;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public add(Ljava/lang/Object;)Z
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(TK;)Z"
        }
    .end annotation

    .prologue
    .line 257
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
            "<+TK;>;)Z"
        }
    .end annotation

    .prologue
    .line 262
    new-instance v0, Ljava/lang/UnsupportedOperationException;

    invoke-direct {v0}, Ljava/lang/UnsupportedOperationException;-><init>()V

    throw v0
.end method

.method public clear()V
    .locals 1

    .prologue
    .line 267
    iget-object v0, p0, Lmiui/util/a$b;->E:Lmiui/util/a;

    invoke-virtual {v0}, Lmiui/util/a;->U()V

    .line 268
    return-void
.end method

.method public contains(Ljava/lang/Object;)Z
    .locals 1

    .prologue
    .line 272
    iget-object v0, p0, Lmiui/util/a$b;->E:Lmiui/util/a;

    invoke-virtual {v0, p1}, Lmiui/util/a;->c(Ljava/lang/Object;)I

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
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/Collection",
            "<*>;)Z"
        }
    .end annotation

    .prologue
    .line 277
    iget-object v0, p0, Lmiui/util/a$b;->E:Lmiui/util/a;

    invoke-virtual {v0}, Lmiui/util/a;->T()Ljava/util/Map;

    move-result-object v0

    invoke-static {v0, p1}, Lmiui/util/a;->a(Ljava/util/Map;Ljava/util/Collection;)Z

    move-result v0

    return v0
.end method

.method public equals(Ljava/lang/Object;)Z
    .locals 1

    .prologue
    .line 327
    invoke-static {p0, p1}, Lmiui/util/a;->a(Ljava/util/Set;Ljava/lang/Object;)Z

    move-result v0

    return v0
.end method

.method public hashCode()I
    .locals 4

    .prologue
    const/4 v1, 0x0

    .line 332
    .line 333
    iget-object v0, p0, Lmiui/util/a$b;->E:Lmiui/util/a;

    invoke-virtual {v0}, Lmiui/util/a;->S()I

    move-result v0

    add-int/lit8 v0, v0, -0x1

    move v2, v0

    move v3, v1

    :goto_0
    if-ltz v2, :cond_1

    .line 334
    iget-object v0, p0, Lmiui/util/a$b;->E:Lmiui/util/a;

    invoke-virtual {v0, v2, v1}, Lmiui/util/a;->d(II)Ljava/lang/Object;

    move-result-object v0

    .line 335
    if-nez v0, :cond_0

    move v0, v1

    :goto_1
    add-int/2addr v3, v0

    .line 333
    add-int/lit8 v0, v2, -0x1

    move v2, v0

    goto :goto_0

    .line 335
    :cond_0
    invoke-virtual {v0}, Ljava/lang/Object;->hashCode()I

    move-result v0

    goto :goto_1

    .line 337
    :cond_1
    return v3
.end method

.method public isEmpty()Z
    .locals 1

    .prologue
    .line 282
    iget-object v0, p0, Lmiui/util/a$b;->E:Lmiui/util/a;

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
            "<TK;>;"
        }
    .end annotation

    .prologue
    .line 287
    new-instance v0, Lmiui/util/a$e;

    iget-object v1, p0, Lmiui/util/a$b;->E:Lmiui/util/a;

    const/4 v2, 0x0

    invoke-direct {v0, v1, v2}, Lmiui/util/a$e;-><init>(Lmiui/util/a;I)V

    return-object v0
.end method

.method public remove(Ljava/lang/Object;)Z
    .locals 2

    .prologue
    .line 292
    iget-object v0, p0, Lmiui/util/a$b;->E:Lmiui/util/a;

    invoke-virtual {v0, p1}, Lmiui/util/a;->c(Ljava/lang/Object;)I

    move-result v0

    .line 293
    if-ltz v0, :cond_0

    .line 294
    iget-object v1, p0, Lmiui/util/a$b;->E:Lmiui/util/a;

    invoke-virtual {v1, v0}, Lmiui/util/a;->v(I)V

    .line 295
    const/4 v0, 0x1

    .line 297
    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public removeAll(Ljava/util/Collection;)Z
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/Collection",
            "<*>;)Z"
        }
    .end annotation

    .prologue
    .line 302
    iget-object v0, p0, Lmiui/util/a$b;->E:Lmiui/util/a;

    invoke-virtual {v0}, Lmiui/util/a;->T()Ljava/util/Map;

    move-result-object v0

    invoke-static {v0, p1}, Lmiui/util/a;->b(Ljava/util/Map;Ljava/util/Collection;)Z

    move-result v0

    return v0
.end method

.method public retainAll(Ljava/util/Collection;)Z
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/Collection",
            "<*>;)Z"
        }
    .end annotation

    .prologue
    .line 307
    iget-object v0, p0, Lmiui/util/a$b;->E:Lmiui/util/a;

    invoke-virtual {v0}, Lmiui/util/a;->T()Ljava/util/Map;

    move-result-object v0

    invoke-static {v0, p1}, Lmiui/util/a;->c(Ljava/util/Map;Ljava/util/Collection;)Z

    move-result v0

    return v0
.end method

.method public size()I
    .locals 1

    .prologue
    .line 312
    iget-object v0, p0, Lmiui/util/a$b;->E:Lmiui/util/a;

    invoke-virtual {v0}, Lmiui/util/a;->S()I

    move-result v0

    return v0
.end method

.method public toArray()[Ljava/lang/Object;
    .locals 2

    .prologue
    .line 317
    iget-object v0, p0, Lmiui/util/a$b;->E:Lmiui/util/a;

    const/4 v1, 0x0

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
    .line 322
    iget-object v0, p0, Lmiui/util/a$b;->E:Lmiui/util/a;

    const/4 v1, 0x0

    invoke-virtual {v0, p1, v1}, Lmiui/util/a;->a([Ljava/lang/Object;I)[Ljava/lang/Object;

    move-result-object v0

    return-object v0
.end method
