.class public Lmiui/util/async/TaskManager;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field public static final DEFAULT_CACHE_SIZE:I = -0x1

.field static final TAG:Ljava/lang/String; = "async"

.field static final es:Z = true

.field static final et:Z = true

.field static final eu:Z = false

.field static final ev:Z = false

.field static final ew:Z = false

.field private static final ex:I = 0xa

.field private static final ey:I = -0x1

.field private static final f:Lmiui/util/SoftReferenceSingleton;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lmiui/util/SoftReferenceSingleton",
            "<",
            "Lmiui/util/async/TaskManager;",
            ">;"
        }
    .end annotation
.end field


# instance fields
.field private final dp:Lmiui/util/async/b;

.field private volatile dq:Z

.field private eA:Lmiui/util/async/e;

.field private eB:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList",
            "<",
            "Lmiui/util/async/a;",
            ">;"
        }
    .end annotation
.end field

.field private eC:Ljava/lang/Object;

.field private ez:Lmiui/util/cache/Cache;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lmiui/util/cache/Cache",
            "<",
            "Ljava/lang/String;",
            "Ljava/lang/Object;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .prologue
    .line 59
    new-instance v0, Lmiui/util/async/c;

    invoke-direct {v0}, Lmiui/util/async/c;-><init>()V

    sput-object v0, Lmiui/util/async/TaskManager;->f:Lmiui/util/SoftReferenceSingleton;

    return-void
.end method

.method public constructor <init>()V
    .locals 2

    .prologue
    const/4 v1, -0x1

    .line 125
    const/16 v0, 0xa

    invoke-direct {p0, v0, v1, v1}, Lmiui/util/async/TaskManager;-><init>(III)V

    .line 126
    return-void
.end method

.method public constructor <init>(III)V
    .locals 4

    .prologue
    const/4 v0, 0x0

    .line 135
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 110
    new-instance v1, Lmiui/util/async/d;

    invoke-direct {v1, p0}, Lmiui/util/async/d;-><init>(Lmiui/util/async/TaskManager;)V

    iput-object v1, p0, Lmiui/util/async/TaskManager;->eC:Ljava/lang/Object;

    .line 141
    iput-boolean v0, p0, Lmiui/util/async/TaskManager;->dq:Z

    .line 142
    new-instance v1, Lmiui/util/async/b;

    invoke-direct {v1, p0, p1}, Lmiui/util/async/b;-><init>(Lmiui/util/async/TaskManager;I)V

    iput-object v1, p0, Lmiui/util/async/TaskManager;->dp:Lmiui/util/async/b;

    .line 144
    if-gez p2, :cond_0

    .line 145
    invoke-static {}, Lmiui/os/Environment;->getCpuCount()I

    move-result p2

    .line 146
    if-gtz p2, :cond_0

    .line 147
    const/4 p2, 0x4

    .line 151
    :cond_0
    new-instance v1, Ljava/util/ArrayList;

    invoke-direct {v1, p2}, Ljava/util/ArrayList;-><init>(I)V

    iput-object v1, p0, Lmiui/util/async/TaskManager;->eB:Ljava/util/ArrayList;

    move v1, v0

    .line 152
    :goto_0
    if-ge v1, p2, :cond_1

    .line 153
    iget-object v0, p0, Lmiui/util/async/TaskManager;->eB:Ljava/util/ArrayList;

    new-instance v2, Lmiui/util/async/a;

    iget-object v3, p0, Lmiui/util/async/TaskManager;->dp:Lmiui/util/async/b;

    invoke-direct {v2, p0, v3, v1}, Lmiui/util/async/a;-><init>(Lmiui/util/async/TaskManager;Lmiui/util/async/b;I)V

    invoke-virtual {v0, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 154
    iget-object v0, p0, Lmiui/util/async/TaskManager;->eB:Ljava/util/ArrayList;

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/util/async/a;

    invoke-virtual {v0}, Lmiui/util/async/a;->start()V

    .line 152
    add-int/lit8 v0, v1, 0x1

    move v1, v0

    goto :goto_0

    .line 157
    :cond_1
    new-instance v0, Lmiui/util/async/e;

    invoke-direct {v0, p0}, Lmiui/util/async/e;-><init>(Lmiui/util/async/TaskManager;)V

    iput-object v0, p0, Lmiui/util/async/TaskManager;->eA:Lmiui/util/async/e;

    .line 158
    new-instance v0, Lmiui/util/cache/LruCache;

    invoke-direct {v0, p3}, Lmiui/util/cache/LruCache;-><init>(I)V

    iput-object v0, p0, Lmiui/util/async/TaskManager;->ez:Lmiui/util/cache/Cache;

    .line 159
    return-void
.end method

.method public static getDefault()Lmiui/util/async/TaskManager;
    .locals 1

    .prologue
    .line 165
    sget-object v0, Lmiui/util/async/TaskManager;->f:Lmiui/util/SoftReferenceSingleton;

    invoke-virtual {v0}, Lmiui/util/SoftReferenceSingleton;->get()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/util/async/TaskManager;

    return-object v0
.end method


# virtual methods
.method Q()Lmiui/util/cache/Cache;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Lmiui/util/cache/Cache",
            "<",
            "Ljava/lang/String;",
            "Ljava/lang/Object;",
            ">;"
        }
    .end annotation

    .prologue
    .line 341
    iget-object v0, p0, Lmiui/util/async/TaskManager;->ez:Lmiui/util/cache/Cache;

    return-object v0
.end method

.method a(Lmiui/util/async/Task;Lmiui/util/async/Task$Delivery;Ljava/lang/Object;)V
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lmiui/util/async/Task",
            "<*>;",
            "Lmiui/util/async/Task$Delivery;",
            "Ljava/lang/Object;",
            ")V"
        }
    .end annotation

    .prologue
    .line 363
    iget-object v0, p0, Lmiui/util/async/TaskManager;->eA:Lmiui/util/async/e;

    invoke-virtual {v0, p1, p2, p3}, Lmiui/util/async/e;->b(Lmiui/util/async/Task;Lmiui/util/async/Task$Delivery;Ljava/lang/Object;)V

    .line 364
    return-void
.end method

.method a(Lmiui/util/async/Task;)Z
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lmiui/util/async/Task",
            "<*>;)Z"
        }
    .end annotation

    .prologue
    .line 352
    iget-object v0, p0, Lmiui/util/async/TaskManager;->dp:Lmiui/util/async/b;

    invoke-virtual {v0, p1}, Lmiui/util/async/b;->a(Lmiui/util/async/Task;)Z

    move-result v0

    return v0
.end method

.method public add(Lmiui/util/async/Task;)V
    .locals 3
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lmiui/util/async/Task",
            "<*>;)V"
        }
    .end annotation

    .prologue
    const/4 v2, 0x0

    .line 219
    iget-boolean v0, p0, Lmiui/util/async/TaskManager;->dq:Z

    if-eqz v0, :cond_0

    .line 221
    const-string v0, "async"

    const-string v1, "Cannot add task into a shut down task manager"

    invoke-static {v0, v1}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 270
    :goto_0
    return-void

    .line 226
    :cond_0
    invoke-virtual {p1}, Lmiui/util/async/Task;->isRunning()Z

    move-result v0

    if-eqz v0, :cond_1

    .line 227
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "Task "

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v0

    const-string v1, " has already added into task manager and not finish yet"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    .line 229
    const-string v1, "async"

    invoke-static {v1, v0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 231
    new-instance v1, Ljava/lang/IllegalArgumentException;

    invoke-direct {v1, v0}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v1

    .line 234
    :cond_1
    invoke-virtual {p1}, Lmiui/util/async/Task;->getStatus()Lmiui/util/async/Task$Status;

    move-result-object v0

    sget-object v1, Lmiui/util/async/Task$Status;->New:Lmiui/util/async/Task$Status;

    if-eq v0, v1, :cond_2

    .line 235
    invoke-virtual {p1}, Lmiui/util/async/Task;->restart()Z

    move-result v0

    if-nez v0, :cond_2

    .line 236
    new-instance v0, Ljava/lang/IllegalArgumentException;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "Status of task "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, " is not New, and cannot restart."

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 244
    :cond_2
    invoke-virtual {p1, p0}, Lmiui/util/async/Task;->a(Lmiui/util/async/TaskManager;)Z

    move-result v0

    if-nez v0, :cond_3

    .line 245
    new-instance v0, Ljava/lang/IllegalArgumentException;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "Task "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, " has already added into task manager and not finish yet"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 248
    :cond_3
    iget-object v0, p0, Lmiui/util/async/TaskManager;->ez:Lmiui/util/cache/Cache;

    if-eqz v0, :cond_4

    instance-of v0, p1, Lmiui/util/async/Cacheable;

    if-eqz v0, :cond_4

    .line 249
    iget-object v1, p0, Lmiui/util/async/TaskManager;->ez:Lmiui/util/cache/Cache;

    move-object v0, p1

    check-cast v0, Lmiui/util/async/Cacheable;

    invoke-interface {v0}, Lmiui/util/async/Cacheable;->getCacheKey()Ljava/lang/String;

    move-result-object v0

    invoke-interface {v1, v0}, Lmiui/util/cache/Cache;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    .line 250
    if-eqz v0, :cond_4

    .line 254
    sget-object v1, Lmiui/util/async/Task$Status;->Queued:Lmiui/util/async/Task$Status;

    invoke-virtual {p1, v1, v2}, Lmiui/util/async/Task;->a(Lmiui/util/async/Task$Status;Ljava/lang/Object;)V

    .line 255
    sget-object v1, Lmiui/util/async/Task$Status;->Executing:Lmiui/util/async/Task$Status;

    invoke-virtual {p1, v1, v2}, Lmiui/util/async/Task;->a(Lmiui/util/async/Task$Status;Ljava/lang/Object;)V

    .line 256
    sget-object v1, Lmiui/util/async/Task$Status;->Done:Lmiui/util/async/Task$Status;

    invoke-virtual {p1, v1, v0}, Lmiui/util/async/Task;->a(Lmiui/util/async/Task$Status;Ljava/lang/Object;)V

    goto/16 :goto_0

    .line 261
    :cond_4
    invoke-virtual {p1}, Lmiui/util/async/Task;->getPriority()Lmiui/util/async/Task$Priority;

    move-result-object v0

    sget-object v1, Lmiui/util/async/Task$Priority;->RealTime:Lmiui/util/async/Task$Priority;

    if-ne v0, v1, :cond_5

    .line 265
    sget-object v0, Lmiui/util/async/Task$Status;->Queued:Lmiui/util/async/Task$Status;

    invoke-virtual {p1, v0, v2}, Lmiui/util/async/Task;->a(Lmiui/util/async/Task$Status;Ljava/lang/Object;)V

    .line 266
    invoke-static {p0, p1}, Lmiui/util/async/a;->a(Lmiui/util/async/TaskManager;Lmiui/util/async/Task;)V

    goto/16 :goto_0

    .line 268
    :cond_5
    iget-object v0, p0, Lmiui/util/async/TaskManager;->dp:Lmiui/util/async/b;

    invoke-virtual {v0, p1}, Lmiui/util/async/b;->b(Lmiui/util/async/Task;)Z

    goto/16 :goto_0
.end method

.method isShutdown()Z
    .locals 1

    .prologue
    .line 321
    iget-boolean v0, p0, Lmiui/util/async/TaskManager;->dq:Z

    return v0
.end method

.method public pause()V
    .locals 1

    .prologue
    .line 280
    iget-object v0, p0, Lmiui/util/async/TaskManager;->dp:Lmiui/util/async/b;

    invoke-virtual {v0}, Lmiui/util/async/b;->pause()V

    .line 281
    return-void
.end method

.method public resume()V
    .locals 1

    .prologue
    .line 291
    iget-object v0, p0, Lmiui/util/async/TaskManager;->dp:Lmiui/util/async/b;

    invoke-virtual {v0}, Lmiui/util/async/b;->resume()V

    .line 292
    return-void
.end method

.method public setCallbackThread(Z)V
    .locals 1

    .prologue
    .line 332
    iget-object v0, p0, Lmiui/util/async/TaskManager;->eA:Lmiui/util/async/e;

    invoke-virtual {v0, p1}, Lmiui/util/async/e;->setCallbackThread(Z)V

    .line 333
    return-void
.end method

.method public setMaxCache(I)V
    .locals 1

    .prologue
    .line 174
    iget-object v0, p0, Lmiui/util/async/TaskManager;->ez:Lmiui/util/cache/Cache;

    invoke-interface {v0, p1}, Lmiui/util/cache/Cache;->setMaxSize(I)V

    .line 175
    return-void
.end method

.method public setThreadCount(I)V
    .locals 3

    .prologue
    .line 183
    iget-boolean v0, p0, Lmiui/util/async/TaskManager;->dq:Z

    if-eqz v0, :cond_1

    .line 185
    const-string v0, "async"

    const-string v1, "Cannot add task into a shut down task manager"

    invoke-static {v0, v1}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 211
    :cond_0
    return-void

    .line 190
    :cond_1
    if-gez p1, :cond_2

    .line 191
    invoke-static {}, Lmiui/os/Environment;->getCpuCount()I

    move-result p1

    .line 192
    if-gtz p1, :cond_2

    .line 193
    const/4 p1, 0x4

    .line 197
    :cond_2
    iget-object v0, p0, Lmiui/util/async/TaskManager;->eB:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    .line 198
    if-le p1, v0, :cond_3

    .line 199
    :goto_0
    if-ge v0, p1, :cond_0

    .line 200
    new-instance v1, Lmiui/util/async/a;

    iget-object v2, p0, Lmiui/util/async/TaskManager;->dp:Lmiui/util/async/b;

    invoke-direct {v1, p0, v2, v0}, Lmiui/util/async/a;-><init>(Lmiui/util/async/TaskManager;Lmiui/util/async/b;I)V

    .line 201
    invoke-virtual {v1}, Lmiui/util/async/a;->start()V

    .line 202
    iget-object v2, p0, Lmiui/util/async/TaskManager;->eB:Ljava/util/ArrayList;

    invoke-virtual {v2, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 199
    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    .line 204
    :cond_3
    if-ge p1, v0, :cond_0

    .line 205
    add-int/lit8 v0, v0, -0x1

    move v1, v0

    :goto_1
    if-lt v1, p1, :cond_0

    .line 206
    iget-object v0, p0, Lmiui/util/async/TaskManager;->eB:Ljava/util/ArrayList;

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/util/async/a;

    .line 207
    invoke-virtual {v0}, Lmiui/util/async/a;->shutdown()V

    .line 208
    iget-object v0, p0, Lmiui/util/async/TaskManager;->eB:Ljava/util/ArrayList;

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->remove(I)Ljava/lang/Object;

    .line 205
    add-int/lit8 v0, v1, -0x1

    move v1, v0

    goto :goto_1
.end method

.method public shutdown()V
    .locals 2

    .prologue
    .line 304
    invoke-static {}, Lmiui/util/async/TaskManager;->getDefault()Lmiui/util/async/TaskManager;

    move-result-object v0

    if-eq p0, v0, :cond_1

    .line 309
    iget-boolean v0, p0, Lmiui/util/async/TaskManager;->dq:Z

    if-nez v0, :cond_1

    .line 310
    const/4 v0, 0x1

    iput-boolean v0, p0, Lmiui/util/async/TaskManager;->dq:Z

    .line 311
    iget-object v0, p0, Lmiui/util/async/TaskManager;->eB:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :goto_0
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_0

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/util/async/a;

    .line 312
    invoke-virtual {v0}, Lmiui/util/async/a;->shutdown()V

    goto :goto_0

    .line 314
    :cond_0
    iget-object v0, p0, Lmiui/util/async/TaskManager;->eB:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->clear()V

    .line 315
    iget-object v0, p0, Lmiui/util/async/TaskManager;->ez:Lmiui/util/cache/Cache;

    invoke-interface {v0}, Lmiui/util/cache/Cache;->clear()V

    .line 318
    :cond_1
    return-void
.end method
