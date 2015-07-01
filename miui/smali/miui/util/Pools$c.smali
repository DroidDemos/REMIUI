.class abstract Lmiui/util/Pools$c;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lmiui/util/Pools$Pool;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/util/Pools;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x408
    name = "c"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "<T:",
        "Ljava/lang/Object;",
        ">",
        "Ljava/lang/Object;",
        "Lmiui/util/Pools$Pool",
        "<TT;>;"
    }
.end annotation


# instance fields
.field private final NJ:Lmiui/util/Pools$Manager;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lmiui/util/Pools$Manager",
            "<TT;>;"
        }
    .end annotation
.end field

.field private NK:Lmiui/util/Pools$b;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lmiui/util/Pools$b",
            "<TT;>;"
        }
    .end annotation
.end field

.field private final eC:Ljava/lang/Object;

.field private final hm:I


# direct methods
.method public constructor <init>(Lmiui/util/Pools$Manager;I)V
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lmiui/util/Pools$Manager",
            "<TT;>;I)V"
        }
    .end annotation

    .prologue
    .line 362
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 351
    new-instance v0, Lmiui/util/c;

    invoke-direct {v0, p0}, Lmiui/util/c;-><init>(Lmiui/util/Pools$c;)V

    iput-object v0, p0, Lmiui/util/Pools$c;->eC:Ljava/lang/Object;

    .line 363
    if-eqz p1, :cond_0

    const/4 v0, 0x1

    if-ge p2, v0, :cond_1

    .line 365
    :cond_0
    iget-object v0, p0, Lmiui/util/Pools$c;->eC:Ljava/lang/Object;

    invoke-virtual {v0}, Ljava/lang/Object;->hashCode()I

    move-result v0

    iput v0, p0, Lmiui/util/Pools$c;->hm:I

    .line 366
    new-instance v0, Ljava/lang/IllegalArgumentException;

    const-string v1, "manager cannot be null and size cannot less then 1"

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 369
    :cond_1
    iput-object p1, p0, Lmiui/util/Pools$c;->NJ:Lmiui/util/Pools$Manager;

    .line 370
    iput p2, p0, Lmiui/util/Pools$c;->hm:I

    .line 371
    iget-object v0, p0, Lmiui/util/Pools$c;->NJ:Lmiui/util/Pools$Manager;

    invoke-virtual {v0}, Lmiui/util/Pools$Manager;->createInstance()Ljava/lang/Object;

    move-result-object v0

    .line 372
    if-nez v0, :cond_2

    .line 373
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "manager create instance cannot return null"

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 376
    :cond_2
    invoke-virtual {v0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v1

    .line 377
    invoke-virtual {p0, v1, p2}, Lmiui/util/Pools$c;->a(Ljava/lang/Class;I)Lmiui/util/Pools$b;

    move-result-object v1

    iput-object v1, p0, Lmiui/util/Pools$c;->NK:Lmiui/util/Pools$b;

    .line 378
    invoke-virtual {p0, v0}, Lmiui/util/Pools$c;->doRelease(Ljava/lang/Object;)V

    .line 379
    return-void
.end method


# virtual methods
.method abstract a(Ljava/lang/Class;I)Lmiui/util/Pools$b;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/Class",
            "<TT;>;I)",
            "Lmiui/util/Pools$b",
            "<TT;>;"
        }
    .end annotation
.end method

.method abstract a(Lmiui/util/Pools$b;I)V
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lmiui/util/Pools$b",
            "<TT;>;I)V"
        }
    .end annotation
.end method

.method public acquire()Ljava/lang/Object;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()TT;"
        }
    .end annotation

    .prologue
    .line 420
    invoke-virtual {p0}, Lmiui/util/Pools$c;->doAcquire()Ljava/lang/Object;

    move-result-object v0

    return-object v0
.end method

.method public close()V
    .locals 2

    .prologue
    .line 430
    iget-object v0, p0, Lmiui/util/Pools$c;->NK:Lmiui/util/Pools$b;

    if-eqz v0, :cond_0

    .line 431
    iget-object v0, p0, Lmiui/util/Pools$c;->NK:Lmiui/util/Pools$b;

    iget v1, p0, Lmiui/util/Pools$c;->hm:I

    invoke-virtual {p0, v0, v1}, Lmiui/util/Pools$c;->a(Lmiui/util/Pools$b;I)V

    .line 432
    const/4 v0, 0x0

    iput-object v0, p0, Lmiui/util/Pools$c;->NK:Lmiui/util/Pools$b;

    .line 434
    :cond_0
    return-void
.end method

.method protected final doAcquire()Ljava/lang/Object;
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()TT;"
        }
    .end annotation

    .prologue
    .line 385
    iget-object v0, p0, Lmiui/util/Pools$c;->NK:Lmiui/util/Pools$b;

    if-nez v0, :cond_0

    .line 386
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "Cannot acquire object after close()"

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 389
    :cond_0
    iget-object v0, p0, Lmiui/util/Pools$c;->NK:Lmiui/util/Pools$b;

    invoke-interface {v0}, Lmiui/util/Pools$b;->get()Ljava/lang/Object;

    move-result-object v0

    .line 390
    if-nez v0, :cond_1

    .line 391
    iget-object v0, p0, Lmiui/util/Pools$c;->NJ:Lmiui/util/Pools$Manager;

    invoke-virtual {v0}, Lmiui/util/Pools$Manager;->createInstance()Ljava/lang/Object;

    move-result-object v0

    .line 392
    if-nez v0, :cond_1

    .line 393
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "manager create instance cannot return null"

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 397
    :cond_1
    iget-object v1, p0, Lmiui/util/Pools$c;->NJ:Lmiui/util/Pools$Manager;

    invoke-virtual {v1, v0}, Lmiui/util/Pools$Manager;->onAcquire(Ljava/lang/Object;)V

    .line 399
    return-object v0
.end method

.method protected final doRelease(Ljava/lang/Object;)V
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(TT;)V"
        }
    .end annotation

    .prologue
    .line 403
    iget-object v0, p0, Lmiui/util/Pools$c;->NK:Lmiui/util/Pools$b;

    if-nez v0, :cond_0

    .line 404
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "Cannot release object after close()"

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 407
    :cond_0
    if-nez p1, :cond_2

    .line 416
    :cond_1
    :goto_0
    return-void

    .line 411
    :cond_2
    iget-object v0, p0, Lmiui/util/Pools$c;->NJ:Lmiui/util/Pools$Manager;

    invoke-virtual {v0, p1}, Lmiui/util/Pools$Manager;->onRelease(Ljava/lang/Object;)V

    .line 413
    iget-object v0, p0, Lmiui/util/Pools$c;->NK:Lmiui/util/Pools$b;

    invoke-interface {v0, p1}, Lmiui/util/Pools$b;->put(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_1

    .line 414
    iget-object v0, p0, Lmiui/util/Pools$c;->NJ:Lmiui/util/Pools$Manager;

    invoke-virtual {v0, p1}, Lmiui/util/Pools$Manager;->onDestroy(Ljava/lang/Object;)V

    goto :goto_0
.end method

.method public getSize()I
    .locals 1

    .prologue
    .line 438
    iget-object v0, p0, Lmiui/util/Pools$c;->NK:Lmiui/util/Pools$b;

    if-nez v0, :cond_0

    const/4 v0, 0x0

    :goto_0
    return v0

    :cond_0
    iget v0, p0, Lmiui/util/Pools$c;->hm:I

    goto :goto_0
.end method

.method public release(Ljava/lang/Object;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(TT;)V"
        }
    .end annotation

    .prologue
    .line 425
    invoke-virtual {p0, p1}, Lmiui/util/Pools$c;->doRelease(Ljava/lang/Object;)V

    .line 426
    return-void
.end method
