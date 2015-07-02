.class public final Lmiui/util/Pools;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lmiui/util/Pools$SoftReferencePool;,
        Lmiui/util/Pools$SimplePool;,
        Lmiui/util/Pools$c;,
        Lmiui/util/Pools$d;,
        Lmiui/util/Pools$a;,
        Lmiui/util/Pools$b;,
        Lmiui/util/Pools$Manager;,
        Lmiui/util/Pools$Pool;
    }
.end annotation


# static fields
.field private static final Jm:Ljava/util/HashMap;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/HashMap",
            "<",
            "Ljava/lang/Class",
            "<*>;",
            "Lmiui/util/Pools$a",
            "<*>;>;"
        }
    .end annotation
.end field

.field private static final Jn:Ljava/util/HashMap;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/HashMap",
            "<",
            "Ljava/lang/Class",
            "<*>;",
            "Lmiui/util/Pools$d",
            "<*>;>;"
        }
    .end annotation
.end field

.field private static final Jo:Lmiui/util/Pools$Pool;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lmiui/util/Pools$Pool",
            "<",
            "Ljava/lang/StringBuilder;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method static constructor <clinit>()V
    .locals 2

    .prologue
    .line 259
    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    sput-object v0, Lmiui/util/Pools;->Jm:Ljava/util/HashMap;

    .line 262
    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    sput-object v0, Lmiui/util/Pools;->Jn:Ljava/util/HashMap;

    .line 266
    new-instance v0, Lmiui/util/e;

    invoke-direct {v0}, Lmiui/util/e;-><init>()V

    const/4 v1, 0x4

    invoke-static {v0, v1}, Lmiui/util/Pools;->createSoftReferencePool(Lmiui/util/Pools$Manager;I)Lmiui/util/Pools$SoftReferencePool;

    move-result-object v0

    sput-object v0, Lmiui/util/Pools;->Jo:Lmiui/util/Pools$Pool;

    return-void
.end method

.method public constructor <init>()V
    .locals 0

    .prologue
    .line 28
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 496
    return-void
.end method

.method static a(Lmiui/util/Pools$a;I)V
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "<T:",
            "Ljava/lang/Object;",
            ">(",
            "Lmiui/util/Pools$a",
            "<TT;>;I)V"
        }
    .end annotation

    .prologue
    .line 310
    sget-object v1, Lmiui/util/Pools;->Jm:Ljava/util/HashMap;

    monitor-enter v1

    .line 311
    neg-int v0, p1

    :try_start_0
    invoke-virtual {p0, v0}, Lmiui/util/Pools$a;->resize(I)V

    .line 312
    monitor-exit v1

    .line 313
    return-void

    .line 312
    :catchall_0
    move-exception v0

    monitor-exit v1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw v0
.end method

.method static a(Lmiui/util/Pools$d;I)V
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "<T:",
            "Ljava/lang/Object;",
            ">(",
            "Lmiui/util/Pools$d",
            "<TT;>;I)V"
        }
    .end annotation

    .prologue
    .line 341
    sget-object v1, Lmiui/util/Pools;->Jn:Ljava/util/HashMap;

    monitor-enter v1

    .line 342
    neg-int v0, p1

    :try_start_0
    invoke-virtual {p0, v0}, Lmiui/util/Pools$d;->resize(I)V

    .line 343
    monitor-exit v1

    .line 344
    return-void

    .line 343
    :catchall_0
    move-exception v0

    monitor-exit v1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw v0
.end method

.method static b(Ljava/lang/Class;I)Lmiui/util/Pools$a;
    .locals 3
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "<T:",
            "Ljava/lang/Object;",
            ">(",
            "Ljava/lang/Class",
            "<TT;>;I)",
            "Lmiui/util/Pools$a",
            "<TT;>;"
        }
    .end annotation

    .prologue
    .line 291
    sget-object v1, Lmiui/util/Pools;->Jm:Ljava/util/HashMap;

    monitor-enter v1

    .line 292
    :try_start_0
    sget-object v0, Lmiui/util/Pools;->Jm:Ljava/util/HashMap;

    invoke-virtual {v0, p0}, Ljava/util/HashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/util/Pools$a;

    .line 294
    if-nez v0, :cond_0

    .line 295
    new-instance v0, Lmiui/util/Pools$a;

    invoke-direct {v0, p0, p1}, Lmiui/util/Pools$a;-><init>(Ljava/lang/Class;I)V

    .line 296
    sget-object v2, Lmiui/util/Pools;->Jm:Ljava/util/HashMap;

    invoke-virtual {v2, p0, v0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 300
    :goto_0
    monitor-exit v1

    return-object v0

    .line 298
    :cond_0
    invoke-virtual {v0, p1}, Lmiui/util/Pools$a;->resize(I)V

    goto :goto_0

    .line 301
    :catchall_0
    move-exception v0

    monitor-exit v1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw v0
.end method

.method static c(Ljava/lang/Class;I)Lmiui/util/Pools$d;
    .locals 3
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "<T:",
            "Ljava/lang/Object;",
            ">(",
            "Ljava/lang/Class",
            "<TT;>;I)",
            "Lmiui/util/Pools$d",
            "<TT;>;"
        }
    .end annotation

    .prologue
    .line 322
    sget-object v1, Lmiui/util/Pools;->Jn:Ljava/util/HashMap;

    monitor-enter v1

    .line 323
    :try_start_0
    sget-object v0, Lmiui/util/Pools;->Jn:Ljava/util/HashMap;

    invoke-virtual {v0, p0}, Ljava/util/HashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/util/Pools$d;

    .line 325
    if-nez v0, :cond_0

    .line 326
    new-instance v0, Lmiui/util/Pools$d;

    invoke-direct {v0, p0, p1}, Lmiui/util/Pools$d;-><init>(Ljava/lang/Class;I)V

    .line 327
    sget-object v2, Lmiui/util/Pools;->Jn:Ljava/util/HashMap;

    invoke-virtual {v2, p0, v0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 331
    :goto_0
    monitor-exit v1

    return-object v0

    .line 329
    :cond_0
    invoke-virtual {v0, p1}, Lmiui/util/Pools$d;->resize(I)V

    goto :goto_0

    .line 332
    :catchall_0
    move-exception v0

    monitor-exit v1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw v0
.end method

.method public static createSimplePool(Lmiui/util/Pools$Manager;I)Lmiui/util/Pools$SimplePool;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "<T:",
            "Ljava/lang/Object;",
            ">(",
            "Lmiui/util/Pools$Manager",
            "<TT;>;I)",
            "Lmiui/util/Pools$SimplePool",
            "<TT;>;"
        }
    .end annotation

    .prologue
    .line 453
    new-instance v0, Lmiui/util/Pools$SimplePool;

    invoke-direct {v0, p0, p1}, Lmiui/util/Pools$SimplePool;-><init>(Lmiui/util/Pools$Manager;I)V

    return-object v0
.end method

.method public static createSoftReferencePool(Lmiui/util/Pools$Manager;I)Lmiui/util/Pools$SoftReferencePool;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "<T:",
            "Ljava/lang/Object;",
            ">(",
            "Lmiui/util/Pools$Manager",
            "<TT;>;I)",
            "Lmiui/util/Pools$SoftReferencePool",
            "<TT;>;"
        }
    .end annotation

    .prologue
    .line 467
    new-instance v0, Lmiui/util/Pools$SoftReferencePool;

    invoke-direct {v0, p0, p1}, Lmiui/util/Pools$SoftReferencePool;-><init>(Lmiui/util/Pools$Manager;I)V

    return-object v0
.end method

.method static synthetic dQ()Ljava/util/HashMap;
    .locals 1

    .prologue
    .line 28
    sget-object v0, Lmiui/util/Pools;->Jm:Ljava/util/HashMap;

    return-object v0
.end method

.method static synthetic dR()Ljava/util/HashMap;
    .locals 1

    .prologue
    .line 28
    sget-object v0, Lmiui/util/Pools;->Jn:Ljava/util/HashMap;

    return-object v0
.end method

.method public static getStringBuilderPool()Lmiui/util/Pools$Pool;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Lmiui/util/Pools$Pool",
            "<",
            "Ljava/lang/StringBuilder;",
            ">;"
        }
    .end annotation

    .prologue
    .line 281
    sget-object v0, Lmiui/util/Pools;->Jo:Lmiui/util/Pools$Pool;

    return-object v0
.end method
