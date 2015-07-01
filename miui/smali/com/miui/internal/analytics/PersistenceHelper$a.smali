.class Lcom/miui/internal/analytics/PersistenceHelper$a;
.super Ljava/lang/Thread;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/internal/analytics/PersistenceHelper;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x2
    name = "a"
.end annotation


# instance fields
.field final synthetic qz:Lcom/miui/internal/analytics/PersistenceHelper;


# direct methods
.method private constructor <init>(Lcom/miui/internal/analytics/PersistenceHelper;)V
    .locals 0

    .prologue
    .line 77
    iput-object p1, p0, Lcom/miui/internal/analytics/PersistenceHelper$a;->qz:Lcom/miui/internal/analytics/PersistenceHelper;

    invoke-direct {p0}, Ljava/lang/Thread;-><init>()V

    return-void
.end method

.method synthetic constructor <init>(Lcom/miui/internal/analytics/PersistenceHelper;Lcom/miui/internal/analytics/b;)V
    .locals 0

    .prologue
    .line 77
    invoke-direct {p0, p1}, Lcom/miui/internal/analytics/PersistenceHelper$a;-><init>(Lcom/miui/internal/analytics/PersistenceHelper;)V

    return-void
.end method


# virtual methods
.method public run()V
    .locals 3

    .prologue
    .line 80
    invoke-static {}, Landroid/os/Looper;->prepare()V

    .line 82
    iget-object v0, p0, Lcom/miui/internal/analytics/PersistenceHelper$a;->qz:Lcom/miui/internal/analytics/PersistenceHelper;

    invoke-static {v0}, Lcom/miui/internal/analytics/PersistenceHelper;->a(Lcom/miui/internal/analytics/PersistenceHelper;)Ljava/lang/Object;

    move-result-object v1

    monitor-enter v1

    .line 83
    :try_start_0
    iget-object v0, p0, Lcom/miui/internal/analytics/PersistenceHelper$a;->qz:Lcom/miui/internal/analytics/PersistenceHelper;

    new-instance v2, Lcom/miui/internal/analytics/PersistenceHelper$a$1;

    invoke-direct {v2, p0}, Lcom/miui/internal/analytics/PersistenceHelper$a$1;-><init>(Lcom/miui/internal/analytics/PersistenceHelper$a;)V

    invoke-static {v0, v2}, Lcom/miui/internal/analytics/PersistenceHelper;->a(Lcom/miui/internal/analytics/PersistenceHelper;Landroid/os/Handler;)Landroid/os/Handler;

    .line 94
    iget-object v0, p0, Lcom/miui/internal/analytics/PersistenceHelper$a;->qz:Lcom/miui/internal/analytics/PersistenceHelper;

    invoke-static {v0}, Lcom/miui/internal/analytics/PersistenceHelper;->a(Lcom/miui/internal/analytics/PersistenceHelper;)Ljava/lang/Object;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/Object;->notify()V

    .line 95
    monitor-exit v1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 97
    invoke-static {}, Landroid/os/Looper;->loop()V

    .line 98
    return-void

    .line 95
    :catchall_0
    move-exception v0

    :try_start_1
    monitor-exit v1
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw v0
.end method
