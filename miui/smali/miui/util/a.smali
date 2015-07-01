.class abstract Lmiui/util/a;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lmiui/util/a$d;,
        Lmiui/util/a$b;,
        Lmiui/util/a$a;,
        Lmiui/util/a$c;,
        Lmiui/util/a$e;
    }
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "<K:",
        "Ljava/lang/Object;",
        "V:",
        "Ljava/lang/Object;",
        ">",
        "Ljava/lang/Object;"
    }
.end annotation


# instance fields
.field xx:Lmiui/util/a$a;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lmiui/util/a",
            "<TK;TV;>.a;"
        }
    .end annotation
.end field

.field xy:Lmiui/util/a$b;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lmiui/util/a",
            "<TK;TV;>.b;"
        }
    .end annotation
.end field

.field xz:Lmiui/util/a$d;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lmiui/util/a",
            "<TK;TV;>.d;"
        }
    .end annotation
.end field


# direct methods
.method constructor <init>()V
    .locals 0

    .prologue
    .line 18
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method public static a(Ljava/util/Map;Ljava/util/Collection;)Z
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "<K:",
            "Ljava/lang/Object;",
            "V:",
            "Ljava/lang/Object;",
            ">(",
            "Ljava/util/Map",
            "<TK;TV;>;",
            "Ljava/util/Collection",
            "<*>;)Z"
        }
    .end annotation

    .prologue
    .line 443
    invoke-interface {p1}, Ljava/util/Collection;->iterator()Ljava/util/Iterator;

    move-result-object v0

    .line 444
    :cond_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_1

    .line 445
    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    invoke-interface {p0, v1}, Ljava/util/Map;->containsKey(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_0

    .line 446
    const/4 v0, 0x0

    .line 449
    :goto_0
    return v0

    :cond_1
    const/4 v0, 0x1

    goto :goto_0
.end method

.method public static a(Ljava/util/Set;Ljava/lang/Object;)Z
    .locals 4
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "<T:",
            "Ljava/lang/Object;",
            ">(",
            "Ljava/util/Set",
            "<TT;>;",
            "Ljava/lang/Object;",
            ")Z"
        }
    .end annotation

    .prologue
    const/4 v0, 0x1

    const/4 v1, 0x0

    .line 498
    if-ne p0, p1, :cond_1

    move v1, v0

    .line 512
    :cond_0
    :goto_0
    return v1

    .line 501
    :cond_1
    instance-of v2, p1, Ljava/util/Set;

    if-eqz v2, :cond_0

    .line 502
    check-cast p1, Ljava/util/Set;

    .line 505
    :try_start_0
    invoke-interface {p0}, Ljava/util/Set;->size()I

    move-result v2

    invoke-interface {p1}, Ljava/util/Set;->size()I

    move-result v3

    if-ne v2, v3, :cond_2

    invoke-interface {p0, p1}, Ljava/util/Set;->containsAll(Ljava/util/Collection;)Z
    :try_end_0
    .catch Ljava/lang/NullPointerException; {:try_start_0 .. :try_end_0} :catch_1
    .catch Ljava/lang/ClassCastException; {:try_start_0 .. :try_end_0} :catch_0

    move-result v2

    if-eqz v2, :cond_2

    :goto_1
    move v1, v0

    goto :goto_0

    :cond_2
    move v0, v1

    goto :goto_1

    .line 508
    :catch_0
    move-exception v0

    goto :goto_0

    .line 506
    :catch_1
    move-exception v0

    goto :goto_0
.end method

.method public static b(Ljava/util/Map;Ljava/util/Collection;)Z
    .locals 3
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "<K:",
            "Ljava/lang/Object;",
            "V:",
            "Ljava/lang/Object;",
            ">(",
            "Ljava/util/Map",
            "<TK;TV;>;",
            "Ljava/util/Collection",
            "<*>;)Z"
        }
    .end annotation

    .prologue
    .line 453
    invoke-interface {p0}, Ljava/util/Map;->size()I

    move-result v0

    .line 454
    invoke-interface {p1}, Ljava/util/Collection;->iterator()Ljava/util/Iterator;

    move-result-object v1

    .line 455
    :goto_0
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_0

    .line 456
    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    invoke-interface {p0, v2}, Ljava/util/Map;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    goto :goto_0

    .line 458
    :cond_0
    invoke-interface {p0}, Ljava/util/Map;->size()I

    move-result v1

    if-eq v0, v1, :cond_1

    const/4 v0, 0x1

    :goto_1
    return v0

    :cond_1
    const/4 v0, 0x0

    goto :goto_1
.end method

.method public static c(Ljava/util/Map;Ljava/util/Collection;)Z
    .locals 3
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "<K:",
            "Ljava/lang/Object;",
            "V:",
            "Ljava/lang/Object;",
            ">(",
            "Ljava/util/Map",
            "<TK;TV;>;",
            "Ljava/util/Collection",
            "<*>;)Z"
        }
    .end annotation

    .prologue
    .line 462
    invoke-interface {p0}, Ljava/util/Map;->size()I

    move-result v0

    .line 463
    invoke-interface {p0}, Ljava/util/Map;->keySet()Ljava/util/Set;

    move-result-object v1

    invoke-interface {v1}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v1

    .line 464
    :cond_0
    :goto_0
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_1

    .line 465
    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    invoke-interface {p1, v2}, Ljava/util/Collection;->contains(Ljava/lang/Object;)Z

    move-result v2

    if-nez v2, :cond_0

    .line 466
    invoke-interface {v1}, Ljava/util/Iterator;->remove()V

    goto :goto_0

    .line 469
    :cond_1
    invoke-interface {p0}, Ljava/util/Map;->size()I

    move-result v1

    if-eq v0, v1, :cond_2

    const/4 v0, 0x1

    :goto_1
    return v0

    :cond_2
    const/4 v0, 0x0

    goto :goto_1
.end method


# virtual methods
.method public R(I)[Ljava/lang/Object;
    .locals 4

    .prologue
    .line 473
    invoke-virtual {p0}, Lmiui/util/a;->S()I

    move-result v1

    .line 474
    new-array v2, v1, [Ljava/lang/Object;

    .line 475
    const/4 v0, 0x0

    :goto_0
    if-ge v0, v1, :cond_0

    .line 476
    invoke-virtual {p0, v0, p1}, Lmiui/util/a;->d(II)Ljava/lang/Object;

    move-result-object v3

    aput-object v3, v2, v0

    .line 475
    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    .line 478
    :cond_0
    return-object v2
.end method

.method protected abstract S()I
.end method

.method protected abstract T()Ljava/util/Map;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/Map",
            "<TK;TV;>;"
        }
    .end annotation
.end method

.method protected abstract U()V
.end method

.method protected abstract a(ILjava/lang/Object;)Ljava/lang/Object;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(ITV;)TV;"
        }
    .end annotation
.end method

.method public a([Ljava/lang/Object;I)[Ljava/lang/Object;
    .locals 4
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "<T:",
            "Ljava/lang/Object;",
            ">([TT;I)[TT;"
        }
    .end annotation

    .prologue
    .line 482
    invoke-virtual {p0}, Lmiui/util/a;->S()I

    move-result v2

    .line 483
    array-length v0, p1

    if-ge v0, v2, :cond_2

    .line 484
    invoke-virtual {p1}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/Class;->getComponentType()Ljava/lang/Class;

    move-result-object v0

    invoke-static {v0, v2}, Ljava/lang/reflect/Array;->newInstance(Ljava/lang/Class;I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Ljava/lang/Object;

    check-cast v0, [Ljava/lang/Object;

    .line 488
    :goto_0
    const/4 v1, 0x0

    :goto_1
    if-ge v1, v2, :cond_0

    .line 489
    invoke-virtual {p0, v1, p2}, Lmiui/util/a;->d(II)Ljava/lang/Object;

    move-result-object v3

    aput-object v3, v0, v1

    .line 488
    add-int/lit8 v1, v1, 0x1

    goto :goto_1

    .line 491
    :cond_0
    array-length v1, v0

    if-le v1, v2, :cond_1

    .line 492
    const/4 v1, 0x0

    aput-object v1, v0, v2

    .line 494
    :cond_1
    return-object v0

    :cond_2
    move-object v0, p1

    goto :goto_0
.end method

.method protected abstract c(Ljava/lang/Object;)I
.end method

.method protected abstract c(Ljava/lang/Object;Ljava/lang/Object;)V
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(TK;TV;)V"
        }
    .end annotation
.end method

.method public cq()Ljava/util/Set;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/Set",
            "<",
            "Ljava/util/Map$Entry",
            "<TK;TV;>;>;"
        }
    .end annotation

    .prologue
    .line 516
    iget-object v0, p0, Lmiui/util/a;->xx:Lmiui/util/a$a;

    if-nez v0, :cond_0

    .line 517
    new-instance v0, Lmiui/util/a$a;

    invoke-direct {v0, p0}, Lmiui/util/a$a;-><init>(Lmiui/util/a;)V

    iput-object v0, p0, Lmiui/util/a;->xx:Lmiui/util/a$a;

    .line 519
    :cond_0
    iget-object v0, p0, Lmiui/util/a;->xx:Lmiui/util/a$a;

    return-object v0
.end method

.method public cr()Ljava/util/Set;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/Set",
            "<TK;>;"
        }
    .end annotation

    .prologue
    .line 523
    iget-object v0, p0, Lmiui/util/a;->xy:Lmiui/util/a$b;

    if-nez v0, :cond_0

    .line 524
    new-instance v0, Lmiui/util/a$b;

    invoke-direct {v0, p0}, Lmiui/util/a$b;-><init>(Lmiui/util/a;)V

    iput-object v0, p0, Lmiui/util/a;->xy:Lmiui/util/a$b;

    .line 526
    :cond_0
    iget-object v0, p0, Lmiui/util/a;->xy:Lmiui/util/a$b;

    return-object v0
.end method

.method public cs()Ljava/util/Collection;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/Collection",
            "<TV;>;"
        }
    .end annotation

    .prologue
    .line 530
    iget-object v0, p0, Lmiui/util/a;->xz:Lmiui/util/a$d;

    if-nez v0, :cond_0

    .line 531
    new-instance v0, Lmiui/util/a$d;

    invoke-direct {v0, p0}, Lmiui/util/a$d;-><init>(Lmiui/util/a;)V

    iput-object v0, p0, Lmiui/util/a;->xz:Lmiui/util/a$d;

    .line 533
    :cond_0
    iget-object v0, p0, Lmiui/util/a;->xz:Lmiui/util/a$d;

    return-object v0
.end method

.method protected abstract d(Ljava/lang/Object;)I
.end method

.method protected abstract d(II)Ljava/lang/Object;
.end method

.method protected abstract v(I)V
.end method
