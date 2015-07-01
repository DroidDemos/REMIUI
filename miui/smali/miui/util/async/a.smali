.class Lmiui/util/async/a;
.super Ljava/lang/Thread;
.source "SourceFile"


# instance fields
.field private final do:Lmiui/util/async/TaskManager;

.field private final dp:Lmiui/util/async/b;

.field private volatile dq:Z


# direct methods
.method public constructor <init>(Lmiui/util/async/TaskManager;Lmiui/util/async/b;I)V
    .locals 2

    .prologue
    .line 38
    invoke-direct {p0}, Ljava/lang/Thread;-><init>()V

    .line 39
    iput-object p1, p0, Lmiui/util/async/a;->do:Lmiui/util/async/TaskManager;

    .line 40
    iput-object p2, p0, Lmiui/util/async/a;->dp:Lmiui/util/async/b;

    .line 41
    const/4 v0, 0x0

    iput-boolean v0, p0, Lmiui/util/async/a;->dq:Z

    .line 42
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "TaskThread-"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0, p3}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, v0}, Lmiui/util/async/a;->setName(Ljava/lang/String;)V

    .line 43
    return-void
.end method

.method private static a(Lmiui/util/async/TaskManager;Ljava/lang/Thread;Lmiui/util/async/Task;)V
    .locals 5
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lmiui/util/async/TaskManager;",
            "Ljava/lang/Thread;",
            "Lmiui/util/async/Task",
            "<*>;)V"
        }
    .end annotation

    .prologue
    const/4 v2, 0x0

    .line 112
    if-eqz p0, :cond_1

    if-eqz p2, :cond_1

    .line 113
    sget-object v0, Lmiui/util/async/Task$Status;->Executing:Lmiui/util/async/Task$Status;

    invoke-virtual {p2, v0, v2}, Lmiui/util/async/Task;->a(Lmiui/util/async/Task$Status;Ljava/lang/Object;)V

    .line 114
    invoke-virtual {p2, p1}, Lmiui/util/async/Task;->a(Ljava/lang/Thread;)V

    .line 118
    :try_start_0
    invoke-virtual {p2}, Lmiui/util/async/Task;->doLoad()Ljava/lang/Object;
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    move-result-object v0

    .line 124
    :try_start_1
    sget-object v3, Lmiui/util/async/Task$Status;->Done:Lmiui/util/async/Task$Status;

    if-nez v0, :cond_2

    new-instance v1, Ljava/lang/NullPointerException;

    const-string v4, "result is null"

    invoke-direct {v1, v4}, Ljava/lang/NullPointerException;-><init>(Ljava/lang/String;)V

    :goto_0
    invoke-virtual {p2, v3, v1}, Lmiui/util/async/Task;->a(Lmiui/util/async/Task$Status;Ljava/lang/Object;)V
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_1

    move-object v1, v0

    .line 134
    :goto_1
    invoke-virtual {p0}, Lmiui/util/async/TaskManager;->Q()Lmiui/util/cache/Cache;

    move-result-object v3

    .line 135
    if-eqz v3, :cond_0

    if-eqz v1, :cond_0

    instance-of v0, p2, Lmiui/util/async/Cacheable;

    if-eqz v0, :cond_0

    move-object v0, p2

    .line 136
    check-cast v0, Lmiui/util/async/Cacheable;

    .line 137
    invoke-interface {v0}, Lmiui/util/async/Cacheable;->getCacheKey()Ljava/lang/String;

    move-result-object v4

    .line 138
    if-eqz v4, :cond_0

    .line 143
    invoke-interface {v0}, Lmiui/util/async/Cacheable;->getCacheKey()Ljava/lang/String;

    move-result-object v4

    invoke-interface {v0, v1}, Lmiui/util/async/Cacheable;->sizeOf(Ljava/lang/Object;)I

    move-result v0

    invoke-interface {v3, v4, v1, v0}, Lmiui/util/cache/Cache;->put(Ljava/lang/Object;Ljava/lang/Object;I)V

    .line 147
    :cond_0
    invoke-virtual {p2, v2}, Lmiui/util/async/Task;->a(Ljava/lang/Thread;)V

    .line 149
    :cond_1
    return-void

    :cond_2
    move-object v1, v0

    .line 124
    goto :goto_0

    .line 125
    :catch_0
    move-exception v0

    move-object v1, v0

    move-object v0, v2

    .line 131
    :goto_2
    sget-object v3, Lmiui/util/async/Task$Status;->Done:Lmiui/util/async/Task$Status;

    new-instance v4, Lmiui/util/async/TaskExecutingException;

    invoke-direct {v4, v1}, Lmiui/util/async/TaskExecutingException;-><init>(Ljava/lang/Exception;)V

    invoke-virtual {p2, v3, v4}, Lmiui/util/async/Task;->a(Lmiui/util/async/Task$Status;Ljava/lang/Object;)V

    move-object v1, v0

    goto :goto_1

    .line 125
    :catch_1
    move-exception v1

    goto :goto_2
.end method

.method public static a(Lmiui/util/async/TaskManager;Lmiui/util/async/Task;)V
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lmiui/util/async/TaskManager;",
            "Lmiui/util/async/Task",
            "<*>;)V"
        }
    .end annotation

    .prologue
    .line 52
    new-instance v0, Lmiui/util/async/a$1;

    invoke-direct {v0, p0, p1}, Lmiui/util/async/a$1;-><init>(Lmiui/util/async/TaskManager;Lmiui/util/async/Task;)V

    .line 59
    const-string v1, "TaskThread-RealTime"

    invoke-virtual {v0, v1}, Ljava/lang/Thread;->setName(Ljava/lang/String;)V

    .line 60
    invoke-virtual {v0}, Ljava/lang/Thread;->start()V

    .line 61
    return-void
.end method

.method static synthetic b(Lmiui/util/async/TaskManager;Ljava/lang/Thread;Lmiui/util/async/Task;)V
    .locals 0

    .prologue
    .line 14
    invoke-static {p0, p1, p2}, Lmiui/util/async/a;->a(Lmiui/util/async/TaskManager;Ljava/lang/Thread;Lmiui/util/async/Task;)V

    return-void
.end method


# virtual methods
.method public run()V
    .locals 4

    .prologue
    .line 76
    iget-object v0, p0, Lmiui/util/async/a;->dp:Lmiui/util/async/b;

    .line 77
    :cond_0
    :goto_0
    iget-boolean v1, p0, Lmiui/util/async/a;->dq:Z

    if-nez v1, :cond_1

    .line 82
    invoke-virtual {v0}, Lmiui/util/async/b;->bb()Lmiui/util/async/Task;

    move-result-object v1

    .line 83
    if-eqz v1, :cond_0

    .line 89
    iget-object v2, p0, Lmiui/util/async/a;->do:Lmiui/util/async/TaskManager;

    invoke-static {v2, p0, v1}, Lmiui/util/async/a;->a(Lmiui/util/async/TaskManager;Ljava/lang/Thread;Lmiui/util/async/Task;)V

    .line 90
    const/4 v1, 0x5

    invoke-virtual {p0, v1}, Lmiui/util/async/a;->setPriority(I)V

    goto :goto_0

    .line 95
    :cond_1
    :goto_1
    invoke-virtual {v0}, Lmiui/util/async/b;->isEmpty()Z

    move-result v1

    if-nez v1, :cond_2

    .line 96
    invoke-virtual {v0}, Lmiui/util/async/b;->bb()Lmiui/util/async/Task;

    move-result-object v1

    .line 97
    if-eqz v1, :cond_1

    .line 99
    sget-object v2, Lmiui/util/async/Task$Status;->Canceled:Lmiui/util/async/Task$Status;

    const/4 v3, 0x0

    invoke-virtual {v1, v2, v3}, Lmiui/util/async/Task;->a(Lmiui/util/async/Task$Status;Ljava/lang/Object;)V

    goto :goto_1

    .line 102
    :cond_2
    return-void
.end method

.method public shutdown()V
    .locals 1

    .prologue
    .line 67
    const/4 v0, 0x1

    iput-boolean v0, p0, Lmiui/util/async/a;->dq:Z

    .line 68
    invoke-virtual {p0}, Lmiui/util/async/a;->interrupt()V

    .line 69
    return-void
.end method
