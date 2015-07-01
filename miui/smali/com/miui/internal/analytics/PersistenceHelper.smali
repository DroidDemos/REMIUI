.class public Lcom/miui/internal/analytics/PersistenceHelper;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/internal/analytics/PersistenceHelper$a;
    }
.end annotation


# static fields
.field private static final Fn:I = 0x1

.field private static final TAG:Ljava/lang/String; = "PersistenceHelper"

.field private static final f:Lmiui/util/SoftReferenceSingleton;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lmiui/util/SoftReferenceSingleton",
            "<",
            "Lcom/miui/internal/analytics/PersistenceHelper;",
            ">;"
        }
    .end annotation
.end field


# instance fields
.field private final Fo:Ljava/lang/Object;

.field private Fp:Lcom/miui/internal/analytics/h;

.field private Fq:Lcom/miui/internal/analytics/ObjectBuilder;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lcom/miui/internal/analytics/ObjectBuilder",
            "<",
            "Lcom/miui/internal/analytics/h;",
            ">;"
        }
    .end annotation
.end field

.field private Fr:Lcom/miui/internal/analytics/ObjectBuilder;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lcom/miui/internal/analytics/ObjectBuilder",
            "<",
            "Lcom/miui/internal/analytics/Event;",
            ">;"
        }
    .end annotation
.end field

.field private Fs:Landroid/os/Handler;

.field private mContext:Landroid/content/Context;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .prologue
    .line 36
    new-instance v0, Lcom/miui/internal/analytics/b;

    invoke-direct {v0}, Lcom/miui/internal/analytics/b;-><init>()V

    sput-object v0, Lcom/miui/internal/analytics/PersistenceHelper;->f:Lmiui/util/SoftReferenceSingleton;

    return-void
.end method

.method private constructor <init>()V
    .locals 3

    .prologue
    .line 48
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 28
    new-instance v0, Ljava/lang/Object;

    invoke-direct {v0}, Ljava/lang/Object;-><init>()V

    iput-object v0, p0, Lcom/miui/internal/analytics/PersistenceHelper;->Fo:Ljava/lang/Object;

    .line 32
    new-instance v0, Lcom/miui/internal/analytics/ObjectBuilder;

    invoke-direct {v0}, Lcom/miui/internal/analytics/ObjectBuilder;-><init>()V

    iput-object v0, p0, Lcom/miui/internal/analytics/PersistenceHelper;->Fq:Lcom/miui/internal/analytics/ObjectBuilder;

    .line 33
    new-instance v0, Lcom/miui/internal/analytics/ObjectBuilder;

    invoke-direct {v0}, Lcom/miui/internal/analytics/ObjectBuilder;-><init>()V

    iput-object v0, p0, Lcom/miui/internal/analytics/PersistenceHelper;->Fr:Lcom/miui/internal/analytics/ObjectBuilder;

    .line 50
    iget-object v0, p0, Lcom/miui/internal/analytics/PersistenceHelper;->Fr:Lcom/miui/internal/analytics/ObjectBuilder;

    const-class v1, Lcom/miui/internal/analytics/TrackEvent;

    const-string v2, "2"

    invoke-virtual {v0, v1, v2}, Lcom/miui/internal/analytics/ObjectBuilder;->registerClass(Ljava/lang/Class;Ljava/lang/String;)Z

    .line 51
    iget-object v0, p0, Lcom/miui/internal/analytics/PersistenceHelper;->Fr:Lcom/miui/internal/analytics/ObjectBuilder;

    const-class v1, Lcom/miui/internal/analytics/LogEvent;

    const-string v2, "1"

    invoke-virtual {v0, v1, v2}, Lcom/miui/internal/analytics/ObjectBuilder;->registerClass(Ljava/lang/Class;Ljava/lang/String;)Z

    .line 52
    iget-object v0, p0, Lcom/miui/internal/analytics/PersistenceHelper;->Fr:Lcom/miui/internal/analytics/ObjectBuilder;

    const-class v1, Lcom/miui/internal/analytics/TrackPageViewEvent;

    const-string v2, "3"

    invoke-virtual {v0, v1, v2}, Lcom/miui/internal/analytics/ObjectBuilder;->registerClass(Ljava/lang/Class;Ljava/lang/String;)Z

    .line 55
    iget-object v0, p0, Lcom/miui/internal/analytics/PersistenceHelper;->Fq:Lcom/miui/internal/analytics/ObjectBuilder;

    const-class v1, Lcom/miui/internal/analytics/f;

    const-string v2, "ANALYTICS.SQLITESTORE"

    invoke-virtual {v0, v1, v2}, Lcom/miui/internal/analytics/ObjectBuilder;->registerClass(Ljava/lang/Class;Ljava/lang/String;)Z

    .line 57
    invoke-static {}, Lcom/miui/internal/util/PackageConstants;->getCurrentApplication()Landroid/app/Application;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/internal/analytics/PersistenceHelper;->mContext:Landroid/content/Context;

    .line 59
    invoke-direct {p0}, Lcom/miui/internal/analytics/PersistenceHelper;->dn()V

    .line 61
    iget-object v1, p0, Lcom/miui/internal/analytics/PersistenceHelper;->Fo:Ljava/lang/Object;

    monitor-enter v1

    .line 62
    :try_start_0
    new-instance v0, Lcom/miui/internal/analytics/PersistenceHelper$a;

    const/4 v2, 0x0

    invoke-direct {v0, p0, v2}, Lcom/miui/internal/analytics/PersistenceHelper$a;-><init>(Lcom/miui/internal/analytics/PersistenceHelper;Lcom/miui/internal/analytics/b;)V

    invoke-virtual {v0}, Lcom/miui/internal/analytics/PersistenceHelper$a;->start()V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 64
    :try_start_1
    iget-object v0, p0, Lcom/miui/internal/analytics/PersistenceHelper;->Fo:Ljava/lang/Object;

    invoke-virtual {v0}, Ljava/lang/Object;->wait()V
    :try_end_1
    .catch Ljava/lang/InterruptedException; {:try_start_1 .. :try_end_1} :catch_0
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 68
    :goto_0
    :try_start_2
    monitor-exit v1

    .line 69
    return-void

    .line 68
    :catchall_0
    move-exception v0

    monitor-exit v1
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    throw v0

    .line 65
    :catch_0
    move-exception v0

    goto :goto_0
.end method

.method synthetic constructor <init>(Lcom/miui/internal/analytics/b;)V
    .locals 0

    .prologue
    .line 23
    invoke-direct {p0}, Lcom/miui/internal/analytics/PersistenceHelper;-><init>()V

    return-void
.end method

.method static synthetic a(Lcom/miui/internal/analytics/PersistenceHelper;Landroid/os/Handler;)Landroid/os/Handler;
    .locals 0

    .prologue
    .line 23
    iput-object p1, p0, Lcom/miui/internal/analytics/PersistenceHelper;->Fs:Landroid/os/Handler;

    return-object p1
.end method

.method static synthetic a(Lcom/miui/internal/analytics/PersistenceHelper;)Ljava/lang/Object;
    .locals 1

    .prologue
    .line 23
    iget-object v0, p0, Lcom/miui/internal/analytics/PersistenceHelper;->Fo:Ljava/lang/Object;

    return-object v0
.end method

.method private a(Lcom/miui/internal/analytics/Event;)V
    .locals 1

    .prologue
    .line 127
    iget-object v0, p0, Lcom/miui/internal/analytics/PersistenceHelper;->Fp:Lcom/miui/internal/analytics/h;

    if-eqz v0, :cond_0

    .line 128
    iget-object v0, p0, Lcom/miui/internal/analytics/PersistenceHelper;->Fp:Lcom/miui/internal/analytics/h;

    invoke-virtual {p1, v0}, Lcom/miui/internal/analytics/Event;->writeEvent(Lcom/miui/internal/analytics/h;)V

    .line 130
    :cond_0
    return-void
.end method

.method static synthetic a(Lcom/miui/internal/analytics/PersistenceHelper;Lcom/miui/internal/analytics/Event;)V
    .locals 0

    .prologue
    .line 23
    invoke-direct {p0, p1}, Lcom/miui/internal/analytics/PersistenceHelper;->a(Lcom/miui/internal/analytics/Event;)V

    return-void
.end method

.method private dn()V
    .locals 2

    .prologue
    .line 73
    iget-object v0, p0, Lcom/miui/internal/analytics/PersistenceHelper;->Fq:Lcom/miui/internal/analytics/ObjectBuilder;

    const-string v1, "ANALYTICS.SQLITESTORE"

    invoke-virtual {v0, v1}, Lcom/miui/internal/analytics/ObjectBuilder;->buildObject(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/analytics/h;

    iput-object v0, p0, Lcom/miui/internal/analytics/PersistenceHelper;->Fp:Lcom/miui/internal/analytics/h;

    .line 74
    iget-object v0, p0, Lcom/miui/internal/analytics/PersistenceHelper;->Fp:Lcom/miui/internal/analytics/h;

    iget-object v1, p0, Lcom/miui/internal/analytics/PersistenceHelper;->mContext:Landroid/content/Context;

    invoke-interface {v0, v1}, Lcom/miui/internal/analytics/h;->o(Landroid/content/Context;)V

    .line 75
    return-void
.end method

.method public static getInstance()Lcom/miui/internal/analytics/PersistenceHelper;
    .locals 1

    .prologue
    .line 45
    sget-object v0, Lcom/miui/internal/analytics/PersistenceHelper;->f:Lmiui/util/SoftReferenceSingleton;

    invoke-virtual {v0}, Lmiui/util/SoftReferenceSingleton;->get()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/analytics/PersistenceHelper;

    return-object v0
.end method


# virtual methods
.method public close()V
    .locals 2

    .prologue
    const/4 v1, 0x0

    .line 102
    iget-object v0, p0, Lcom/miui/internal/analytics/PersistenceHelper;->Fs:Landroid/os/Handler;

    invoke-virtual {v0}, Landroid/os/Handler;->getLooper()Landroid/os/Looper;

    move-result-object v0

    invoke-virtual {v0}, Landroid/os/Looper;->quit()V

    .line 103
    iput-object v1, p0, Lcom/miui/internal/analytics/PersistenceHelper;->Fs:Landroid/os/Handler;

    .line 104
    iget-object v0, p0, Lcom/miui/internal/analytics/PersistenceHelper;->Fp:Lcom/miui/internal/analytics/h;

    if-eqz v0, :cond_0

    .line 105
    iget-object v0, p0, Lcom/miui/internal/analytics/PersistenceHelper;->Fp:Lcom/miui/internal/analytics/h;

    invoke-interface {v0}, Lcom/miui/internal/analytics/h;->close()V

    .line 106
    iput-object v1, p0, Lcom/miui/internal/analytics/PersistenceHelper;->Fp:Lcom/miui/internal/analytics/h;

    .line 109
    :cond_0
    iput-object v1, p0, Lcom/miui/internal/analytics/PersistenceHelper;->mContext:Landroid/content/Context;

    .line 110
    return-void
.end method

.method public readEvents(Ljava/lang/String;Ljava/util/List;)Ljava/util/List;
    .locals 6
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            "Ljava/util/List",
            "<",
            "Lcom/miui/internal/analytics/Item;",
            ">;)",
            "Ljava/util/List",
            "<",
            "Lcom/miui/internal/analytics/Event;",
            ">;"
        }
    .end annotation

    .prologue
    .line 146
    iget-object v0, p0, Lcom/miui/internal/analytics/PersistenceHelper;->Fp:Lcom/miui/internal/analytics/h;

    if-eqz v0, :cond_3

    .line 147
    iget-object v0, p0, Lcom/miui/internal/analytics/PersistenceHelper;->Fp:Lcom/miui/internal/analytics/h;

    invoke-interface {v0, p1}, Lcom/miui/internal/analytics/h;->D(Ljava/lang/String;)Landroid/database/Cursor;

    move-result-object v3

    .line 148
    if-eqz v3, :cond_3

    .line 149
    new-instance v2, Ljava/util/ArrayList;

    invoke-direct {v2}, Ljava/util/ArrayList;-><init>()V

    .line 151
    :cond_0
    :goto_0
    :try_start_0
    invoke-interface {v3}, Landroid/database/Cursor;->moveToNext()Z

    move-result v0

    if-eqz v0, :cond_2

    .line 152
    const-string v0, "type"

    invoke-interface {v3, v0}, Landroid/database/Cursor;->getColumnIndexOrThrow(Ljava/lang/String;)I

    move-result v0

    invoke-interface {v3, v0}, Landroid/database/Cursor;->getInt(I)I

    move-result v0

    .line 153
    iget-object v1, p0, Lcom/miui/internal/analytics/PersistenceHelper;->Fr:Lcom/miui/internal/analytics/ObjectBuilder;

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v4, v0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v0

    const-string v4, ""

    invoke-virtual {v0, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v1, v0}, Lcom/miui/internal/analytics/ObjectBuilder;->buildObject(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/analytics/Event;

    .line 155
    if-eqz v0, :cond_0

    .line 156
    invoke-virtual {v0, v3}, Lcom/miui/internal/analytics/Event;->restore(Landroid/database/Cursor;)V

    .line 157
    invoke-interface {p2}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v4

    :cond_1
    invoke-interface {v4}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_0

    invoke-interface {v4}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/miui/internal/analytics/Item;

    .line 158
    invoke-virtual {v0}, Lcom/miui/internal/analytics/Event;->getEventId()Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v1, v5}, Lcom/miui/internal/analytics/Item;->idMatches(Ljava/lang/String;)Z

    move-result v5

    if-eqz v5, :cond_1

    .line 159
    sget-object v4, Landroid/os/Build$VERSION;->INCREMENTAL:Ljava/lang/String;

    invoke-virtual {v1, v4}, Lcom/miui/internal/analytics/Item;->isDispatch(Ljava/lang/String;)Z

    move-result v4

    if-eqz v4, :cond_0

    .line 160
    invoke-virtual {v1}, Lcom/miui/internal/analytics/Item;->getPolicy()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Lcom/miui/internal/analytics/Event;->setPolicy(Ljava/lang/String;)V

    .line 161
    invoke-virtual {v2, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z
    :try_end_0
    .catch Ljava/lang/IllegalArgumentException; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    .line 168
    :catch_0
    move-exception v0

    .line 169
    const-string v1, "PersistenceHelper"

    const-string v4, "IllegalArgumentException catched when readEvents from storage"

    invoke-static {v1, v4, v0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    .line 172
    :cond_2
    invoke-interface {v3}, Landroid/database/Cursor;->close()V

    move-object v0, v2

    .line 177
    :goto_1
    return-object v0

    :cond_3
    invoke-static {}, Ljava/util/Collections;->emptyList()Ljava/util/List;

    move-result-object v0

    goto :goto_1
.end method

.method public selectStore(Ljava/lang/String;)Z
    .locals 2

    .prologue
    .line 113
    const/4 v1, 0x0

    .line 115
    iget-object v0, p0, Lcom/miui/internal/analytics/PersistenceHelper;->Fq:Lcom/miui/internal/analytics/ObjectBuilder;

    invoke-virtual {v0, p1}, Lcom/miui/internal/analytics/ObjectBuilder;->buildObject(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/analytics/h;

    .line 116
    if-eqz v0, :cond_1

    .line 117
    iget-object v1, p0, Lcom/miui/internal/analytics/PersistenceHelper;->Fp:Lcom/miui/internal/analytics/h;

    if-eqz v1, :cond_0

    .line 118
    iget-object v1, p0, Lcom/miui/internal/analytics/PersistenceHelper;->Fp:Lcom/miui/internal/analytics/h;

    invoke-interface {v1}, Lcom/miui/internal/analytics/h;->close()V

    .line 119
    :cond_0
    iput-object v0, p0, Lcom/miui/internal/analytics/PersistenceHelper;->Fp:Lcom/miui/internal/analytics/h;

    .line 120
    const/4 v0, 0x1

    .line 123
    :goto_0
    return v0

    :cond_1
    move v0, v1

    goto :goto_0
.end method

.method public writeEvent(Lcom/miui/internal/analytics/Event;)V
    .locals 2

    .prologue
    .line 137
    iget-object v0, p0, Lcom/miui/internal/analytics/PersistenceHelper;->Fs:Landroid/os/Handler;

    if-eqz v0, :cond_0

    .line 138
    new-instance v0, Landroid/os/Message;

    invoke-direct {v0}, Landroid/os/Message;-><init>()V

    .line 139
    const/4 v1, 0x1

    iput v1, v0, Landroid/os/Message;->what:I

    .line 140
    iput-object p1, v0, Landroid/os/Message;->obj:Ljava/lang/Object;

    .line 141
    iget-object v1, p0, Lcom/miui/internal/analytics/PersistenceHelper;->Fs:Landroid/os/Handler;

    invoke-virtual {v1, v0}, Landroid/os/Handler;->sendMessage(Landroid/os/Message;)Z

    .line 143
    :cond_0
    return-void
.end method

.method public writeEvent(Ljava/lang/String;Ljava/lang/String;J)V
    .locals 6

    .prologue
    .line 133
    new-instance v0, Lcom/miui/internal/analytics/TrackEvent;

    const/4 v3, 0x0

    move-object v1, p1

    move-object v2, p2

    move-wide v4, p3

    invoke-direct/range {v0 .. v5}, Lcom/miui/internal/analytics/TrackEvent;-><init>(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;J)V

    invoke-virtual {p0, v0}, Lcom/miui/internal/analytics/PersistenceHelper;->writeEvent(Lcom/miui/internal/analytics/Event;)V

    .line 134
    return-void
.end method
