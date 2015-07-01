.class public abstract Lcom/miui/internal/util/ClassProxy;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "<T:",
        "Ljava/lang/Object;",
        ">",
        "Ljava/lang/Object;"
    }
.end annotation


# instance fields
.field private final kq:Ljava/lang/Object;

.field private kr:[J

.field private mPtr:J


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .prologue
    .line 101
    const-string v0, "miuiclassproxy"

    invoke-static {v0}, Ljava/lang/System;->loadLibrary(Ljava/lang/String;)V

    .line 102
    return-void
.end method

.method public constructor <init>(Ljava/lang/Class;)V
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/Class",
            "<+TT;>;)V"
        }
    .end annotation

    .prologue
    .line 124
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 104
    new-instance v0, Lcom/miui/internal/util/a;

    invoke-direct {v0, p0}, Lcom/miui/internal/util/a;-><init>(Lcom/miui/internal/util/ClassProxy;)V

    iput-object v0, p0, Lcom/miui/internal/util/ClassProxy;->kq:Ljava/lang/Object;

    .line 115
    const/4 v0, 0x0

    iput-object v0, p0, Lcom/miui/internal/util/ClassProxy;->kr:[J

    .line 118
    const-wide/16 v0, 0x0

    iput-wide v0, p0, Lcom/miui/internal/util/ClassProxy;->mPtr:J

    .line 125
    invoke-direct {p0, p1}, Lcom/miui/internal/util/ClassProxy;->initNative(Ljava/lang/Class;)V

    .line 126
    return-void
.end method

.method static synthetic a(Lcom/miui/internal/util/ClassProxy;)V
    .locals 0

    .prologue
    .line 99
    invoke-direct {p0}, Lcom/miui/internal/util/ClassProxy;->disposeNative()V

    return-void
.end method

.method private a(JZ)Z
    .locals 5

    .prologue
    const/4 v1, 0x0

    .line 158
    const/4 v2, -0x1

    move v0, v1

    .line 159
    :goto_0
    iget-object v3, p0, Lcom/miui/internal/util/ClassProxy;->kr:[J

    array-length v3, v3

    if-ge v0, v3, :cond_3

    .line 160
    iget-object v3, p0, Lcom/miui/internal/util/ClassProxy;->kr:[J

    aget-wide v3, v3, v0

    cmp-long v3, v3, p1

    if-nez v3, :cond_2

    .line 165
    :goto_1
    if-ltz v0, :cond_1

    .line 166
    if-eqz p3, :cond_0

    .line 167
    iget-object v1, p0, Lcom/miui/internal/util/ClassProxy;->kr:[J

    const-wide/16 v2, 0x0

    aput-wide v2, v1, v0

    .line 169
    :cond_0
    const/4 v1, 0x1

    .line 171
    :cond_1
    return v1

    .line 159
    :cond_2
    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    :cond_3
    move v0, v2

    goto :goto_1
.end method

.method private native attachMethodNative(Ljava/lang/String;Ljava/lang/String;)J
.end method

.method private d(J)V
    .locals 7

    .prologue
    const/4 v2, -0x1

    const/4 v1, 0x0

    .line 134
    iget-object v0, p0, Lcom/miui/internal/util/ClassProxy;->kr:[J

    if-nez v0, :cond_0

    .line 135
    const/4 v0, 0x4

    new-array v0, v0, [J

    iput-object v0, p0, Lcom/miui/internal/util/ClassProxy;->kr:[J

    .line 136
    iget-object v0, p0, Lcom/miui/internal/util/ClassProxy;->kr:[J

    aput-wide p1, v0, v1

    .line 155
    :goto_0
    return-void

    :cond_0
    move v0, v1

    .line 139
    :goto_1
    iget-object v3, p0, Lcom/miui/internal/util/ClassProxy;->kr:[J

    array-length v3, v3

    if-ge v0, v3, :cond_4

    .line 140
    iget-object v3, p0, Lcom/miui/internal/util/ClassProxy;->kr:[J

    aget-wide v3, v3, v0

    const-wide/16 v5, 0x0

    cmp-long v3, v3, v5

    if-eqz v3, :cond_1

    iget-object v3, p0, Lcom/miui/internal/util/ClassProxy;->kr:[J

    aget-wide v3, v3, v0

    cmp-long v3, v3, p1

    if-nez v3, :cond_3

    .line 146
    :cond_1
    :goto_2
    if-ne v0, v2, :cond_2

    .line 147
    iget-object v0, p0, Lcom/miui/internal/util/ClassProxy;->kr:[J

    array-length v0, v0

    add-int/lit8 v0, v0, 0x4

    new-array v2, v0, [J

    .line 148
    iget-object v0, p0, Lcom/miui/internal/util/ClassProxy;->kr:[J

    iget-object v3, p0, Lcom/miui/internal/util/ClassProxy;->kr:[J

    array-length v3, v3

    invoke-static {v0, v1, v2, v1, v3}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    .line 149
    iget-object v0, p0, Lcom/miui/internal/util/ClassProxy;->kr:[J

    array-length v0, v0

    .line 150
    iput-object v2, p0, Lcom/miui/internal/util/ClassProxy;->kr:[J

    .line 153
    :cond_2
    iget-object v1, p0, Lcom/miui/internal/util/ClassProxy;->kr:[J

    aput-wide p1, v1, v0

    goto :goto_0

    .line 139
    :cond_3
    add-int/lit8 v0, v0, 0x1

    goto :goto_1

    :cond_4
    move v0, v2

    goto :goto_2
.end method

.method private native detachMethodNative(J)V
.end method

.method private native disposeNative()V
.end method

.method private native initNative(Ljava/lang/Class;)V
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/Class",
            "<+TT;>;)V"
        }
    .end annotation
.end method


# virtual methods
.method protected final attachConstructor(Ljava/lang/String;)J
    .locals 2

    .prologue
    .line 198
    const-string v0, "<init>"

    invoke-virtual {p0, v0, p1}, Lcom/miui/internal/util/ClassProxy;->attachMethod(Ljava/lang/String;Ljava/lang/String;)J

    move-result-wide v0

    return-wide v0
.end method

.method protected final attachMethod(Ljava/lang/String;Ljava/lang/String;)J
    .locals 4

    .prologue
    .line 183
    iget-object v1, p0, Lcom/miui/internal/util/ClassProxy;->kq:Ljava/lang/Object;

    monitor-enter v1

    .line 184
    :try_start_0
    invoke-direct {p0, p1, p2}, Lcom/miui/internal/util/ClassProxy;->attachMethodNative(Ljava/lang/String;Ljava/lang/String;)J

    move-result-wide v2

    .line 185
    invoke-direct {p0, v2, v3}, Lcom/miui/internal/util/ClassProxy;->d(J)V

    .line 186
    monitor-exit v1

    return-wide v2

    .line 187
    :catchall_0
    move-exception v0

    monitor-exit v1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw v0
.end method

.method protected final detachMethod(J)V
    .locals 2

    .prologue
    .line 207
    iget-object v1, p0, Lcom/miui/internal/util/ClassProxy;->kq:Ljava/lang/Object;

    monitor-enter v1

    .line 208
    const/4 v0, 0x1

    :try_start_0
    invoke-direct {p0, p1, p2, v0}, Lcom/miui/internal/util/ClassProxy;->a(JZ)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 209
    invoke-direct {p0, p1, p2}, Lcom/miui/internal/util/ClassProxy;->detachMethodNative(J)V

    .line 211
    :cond_0
    monitor-exit v1

    .line 212
    return-void

    .line 211
    :catchall_0
    move-exception v0

    monitor-exit v1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw v0
.end method

.method protected final dispose()V
    .locals 0

    .prologue
    .line 220
    invoke-direct {p0}, Lcom/miui/internal/util/ClassProxy;->disposeNative()V

    .line 221
    return-void
.end method

.method protected abstract handle()V
.end method
