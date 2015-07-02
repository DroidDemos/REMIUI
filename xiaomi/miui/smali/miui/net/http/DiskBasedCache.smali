.class public Lmiui/net/http/DiskBasedCache;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lmiui/net/http/Cache;


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lmiui/net/http/DiskBasedCache$b;,
        Lmiui/net/http/DiskBasedCache$a;
    }
.end annotation


# static fields
.field private static final TAG:Ljava/lang/String; = "DisBasedCache"

.field private static final f:Lmiui/util/SoftReferenceSingleton;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lmiui/util/SoftReferenceSingleton",
            "<",
            "Lmiui/net/http/DiskBasedCache;",
            ">;"
        }
    .end annotation
.end field

.field private static final sl:I = 0xa00000

.field private static final sm:F = 0.9f

.field private static final sn:I = 0x20140228


# instance fields
.field private final sh:Ljava/util/Map;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map",
            "<",
            "Ljava/lang/String;",
            "Lmiui/net/http/DiskBasedCache$a;",
            ">;"
        }
    .end annotation
.end field

.field private si:J

.field private final sj:Ljava/io/File;

.field private final sk:I


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .prologue
    .line 73
    new-instance v0, Lmiui/net/http/f;

    invoke-direct {v0}, Lmiui/net/http/f;-><init>()V

    sput-object v0, Lmiui/net/http/DiskBasedCache;->f:Lmiui/util/SoftReferenceSingleton;

    return-void
.end method

.method public constructor <init>()V
    .locals 2

    .prologue
    .line 94
    const/4 v0, 0x0

    const/high16 v1, 0xa00000

    invoke-direct {p0, v0, v1}, Lmiui/net/http/DiskBasedCache;-><init>(Ljava/io/File;I)V

    .line 95
    return-void
.end method

.method public constructor <init>(Ljava/io/File;)V
    .locals 1

    .prologue
    .line 102
    const/high16 v0, 0xa00000

    invoke-direct {p0, p1, v0}, Lmiui/net/http/DiskBasedCache;-><init>(Ljava/io/File;I)V

    .line 103
    return-void
.end method

.method public constructor <init>(Ljava/io/File;I)V
    .locals 3

    .prologue
    .line 111
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 40
    new-instance v0, Ljava/util/concurrent/ConcurrentHashMap;

    const/16 v1, 0x10

    const/high16 v2, 0x3f400000

    invoke-direct {v0, v1, v2}, Ljava/util/concurrent/ConcurrentHashMap;-><init>(IF)V

    iput-object v0, p0, Lmiui/net/http/DiskBasedCache;->sh:Ljava/util/Map;

    .line 46
    const-wide/16 v0, 0x0

    iput-wide v0, p0, Lmiui/net/http/DiskBasedCache;->si:J

    .line 112
    if-nez p1, :cond_0

    .line 113
    new-instance p1, Ljava/io/File;

    invoke-static {}, Lcom/miui/internal/util/PackageConstants;->getCurrentApplication()Landroid/app/Application;

    move-result-object v0

    invoke-virtual {v0}, Landroid/app/Application;->getCacheDir()Ljava/io/File;

    move-result-object v0

    const-string v1, "miui.net.http"

    invoke-direct {p1, v0, v1}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    .line 115
    :cond_0
    iput-object p1, p0, Lmiui/net/http/DiskBasedCache;->sj:Ljava/io/File;

    .line 116
    iput p2, p0, Lmiui/net/http/DiskBasedCache;->sk:I

    .line 117
    return-void
.end method

.method private a(Lmiui/net/http/DiskBasedCache$a;)V
    .locals 8

    .prologue
    .line 289
    iget-object v1, p0, Lmiui/net/http/DiskBasedCache;->sh:Ljava/util/Map;

    monitor-enter v1

    .line 290
    :try_start_0
    iget-object v0, p0, Lmiui/net/http/DiskBasedCache;->sh:Ljava/util/Map;

    iget-object v2, p1, Lmiui/net/http/DiskBasedCache$a;->FP:Ljava/lang/String;

    invoke-interface {v0, v2}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/net/http/DiskBasedCache$a;

    .line 291
    if-nez v0, :cond_0

    .line 292
    iget-wide v2, p0, Lmiui/net/http/DiskBasedCache;->si:J

    iget-wide v4, p1, Lmiui/net/http/DiskBasedCache$a;->FG:J

    add-long/2addr v2, v4

    iput-wide v2, p0, Lmiui/net/http/DiskBasedCache;->si:J

    .line 296
    :goto_0
    iget-object v0, p0, Lmiui/net/http/DiskBasedCache;->sh:Ljava/util/Map;

    iget-object v2, p1, Lmiui/net/http/DiskBasedCache$a;->FP:Ljava/lang/String;

    invoke-interface {v0, v2, p1}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 297
    monitor-exit v1

    .line 298
    return-void

    .line 294
    :cond_0
    iget-wide v2, p0, Lmiui/net/http/DiskBasedCache;->si:J

    iget-wide v4, p1, Lmiui/net/http/DiskBasedCache$a;->FG:J

    iget-wide v6, v0, Lmiui/net/http/DiskBasedCache$a;->FG:J

    sub-long/2addr v4, v6

    add-long/2addr v2, v4

    iput-wide v2, p0, Lmiui/net/http/DiskBasedCache;->si:J

    goto :goto_0

    .line 297
    :catchall_0
    move-exception v0

    monitor-exit v1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw v0
.end method

.method private b(Lmiui/net/http/DiskBasedCache$a;)V
    .locals 6

    .prologue
    .line 306
    iget-object v1, p0, Lmiui/net/http/DiskBasedCache;->sh:Ljava/util/Map;

    monitor-enter v1

    .line 307
    :try_start_0
    iget-wide v2, p0, Lmiui/net/http/DiskBasedCache;->si:J

    iget-wide v4, p1, Lmiui/net/http/DiskBasedCache$a;->FG:J

    sub-long/2addr v2, v4

    iput-wide v2, p0, Lmiui/net/http/DiskBasedCache;->si:J

    .line 308
    iget-object v0, p0, Lmiui/net/http/DiskBasedCache;->sh:Ljava/util/Map;

    iget-object v2, p1, Lmiui/net/http/DiskBasedCache$a;->FP:Ljava/lang/String;

    invoke-interface {v0, v2}, Ljava/util/Map;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    .line 309
    monitor-exit v1

    .line 310
    return-void

    .line 309
    :catchall_0
    move-exception v0

    monitor-exit v1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw v0
.end method

.method private e(J)V
    .locals 10

    .prologue
    const v9, 0x3f666666

    .line 217
    iget-wide v0, p0, Lmiui/net/http/DiskBasedCache;->si:J

    add-long/2addr v0, p1

    iget v2, p0, Lmiui/net/http/DiskBasedCache;->sk:I

    int-to-long v2, v2

    cmp-long v0, v0, v2

    if-gez v0, :cond_1

    .line 281
    :cond_0
    :goto_0
    return-void

    .line 221
    :cond_1
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v1

    .line 223
    iget-object v3, p0, Lmiui/net/http/DiskBasedCache;->sh:Ljava/util/Map;

    monitor-enter v3

    .line 225
    :try_start_0
    iget-object v0, p0, Lmiui/net/http/DiskBasedCache;->sh:Ljava/util/Map;

    invoke-interface {v0}, Ljava/util/Map;->entrySet()Ljava/util/Set;

    move-result-object v0

    invoke-interface {v0}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v4

    .line 226
    :cond_2
    :goto_1
    invoke-interface {v4}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_3

    .line 227
    invoke-interface {v4}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/util/Map$Entry;

    .line 228
    invoke-interface {v0}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/net/http/DiskBasedCache$a;

    .line 229
    iget-wide v5, v0, Lmiui/net/http/DiskBasedCache$a;->ttl:J

    cmp-long v5, v5, v1

    if-gez v5, :cond_2

    .line 230
    invoke-virtual {v0}, Lmiui/net/http/DiskBasedCache$a;->delete()V

    .line 231
    iget-wide v5, p0, Lmiui/net/http/DiskBasedCache;->si:J

    iget-wide v7, v0, Lmiui/net/http/DiskBasedCache$a;->FG:J

    sub-long/2addr v5, v7

    iput-wide v5, p0, Lmiui/net/http/DiskBasedCache;->si:J

    .line 232
    invoke-interface {v4}, Ljava/util/Iterator;->remove()V

    goto :goto_1

    .line 235
    :catchall_0
    move-exception v0

    monitor-exit v3
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw v0

    :cond_3
    :try_start_1
    monitor-exit v3
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 237
    iget-wide v3, p0, Lmiui/net/http/DiskBasedCache;->si:J

    add-long/2addr v3, p1

    iget v0, p0, Lmiui/net/http/DiskBasedCache;->sk:I

    int-to-long v5, v0

    cmp-long v0, v3, v5

    if-ltz v0, :cond_0

    .line 241
    iget-object v3, p0, Lmiui/net/http/DiskBasedCache;->sh:Ljava/util/Map;

    monitor-enter v3

    .line 243
    :try_start_2
    iget-object v0, p0, Lmiui/net/http/DiskBasedCache;->sh:Ljava/util/Map;

    invoke-interface {v0}, Ljava/util/Map;->entrySet()Ljava/util/Set;

    move-result-object v0

    invoke-interface {v0}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v4

    .line 244
    :cond_4
    invoke-interface {v4}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_5

    .line 245
    invoke-interface {v4}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/util/Map$Entry;

    .line 246
    invoke-interface {v0}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/net/http/DiskBasedCache$a;

    .line 247
    iget-wide v5, v0, Lmiui/net/http/DiskBasedCache$a;->softTtl:J

    cmp-long v5, v5, v1

    if-gez v5, :cond_4

    .line 248
    invoke-virtual {v0}, Lmiui/net/http/DiskBasedCache$a;->delete()V

    .line 249
    iget-wide v5, p0, Lmiui/net/http/DiskBasedCache;->si:J

    iget-wide v7, v0, Lmiui/net/http/DiskBasedCache$a;->FG:J

    sub-long/2addr v5, v7

    iput-wide v5, p0, Lmiui/net/http/DiskBasedCache;->si:J

    .line 250
    invoke-interface {v4}, Ljava/util/Iterator;->remove()V

    .line 252
    iget-wide v5, p0, Lmiui/net/http/DiskBasedCache;->si:J

    add-long/2addr v5, p1

    long-to-float v0, v5

    iget v5, p0, Lmiui/net/http/DiskBasedCache;->sk:I

    int-to-float v5, v5

    mul-float/2addr v5, v9

    cmpg-float v0, v0, v5

    if-gez v0, :cond_4

    .line 257
    :cond_5
    monitor-exit v3
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_2

    .line 259
    iget-wide v0, p0, Lmiui/net/http/DiskBasedCache;->si:J

    add-long/2addr v0, p1

    iget v2, p0, Lmiui/net/http/DiskBasedCache;->sk:I

    int-to-long v2, v2

    cmp-long v0, v0, v2

    if-ltz v0, :cond_0

    .line 263
    iget-object v1, p0, Lmiui/net/http/DiskBasedCache;->sh:Ljava/util/Map;

    monitor-enter v1

    .line 265
    :try_start_3
    iget-object v0, p0, Lmiui/net/http/DiskBasedCache;->sh:Ljava/util/Map;

    invoke-interface {v0}, Ljava/util/Map;->entrySet()Ljava/util/Set;

    move-result-object v0

    invoke-interface {v0}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v2

    .line 266
    :cond_6
    invoke-interface {v2}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_7

    .line 267
    invoke-interface {v2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/util/Map$Entry;

    .line 268
    invoke-interface {v0}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/net/http/DiskBasedCache$a;

    .line 269
    invoke-virtual {v0}, Lmiui/net/http/DiskBasedCache$a;->dp()Z

    move-result v3

    if-nez v3, :cond_6

    .line 273
    invoke-virtual {v0}, Lmiui/net/http/DiskBasedCache$a;->delete()V

    .line 274
    invoke-interface {v2}, Ljava/util/Iterator;->remove()V

    .line 276
    iget-wide v3, p0, Lmiui/net/http/DiskBasedCache;->si:J

    add-long/2addr v3, p1

    long-to-float v0, v3

    iget v3, p0, Lmiui/net/http/DiskBasedCache;->sk:I

    int-to-float v3, v3

    mul-float/2addr v3, v9

    cmpg-float v0, v0, v3

    if-gez v0, :cond_6

    .line 280
    :cond_7
    monitor-exit v1

    goto/16 :goto_0

    :catchall_1
    move-exception v0

    monitor-exit v1
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_1

    throw v0

    .line 257
    :catchall_2
    move-exception v0

    :try_start_4
    monitor-exit v3
    :try_end_4
    .catchall {:try_start_4 .. :try_end_4} :catchall_2

    throw v0
.end method

.method public static getDefault()Lmiui/net/http/DiskBasedCache;
    .locals 1

    .prologue
    .line 86
    sget-object v0, Lmiui/net/http/DiskBasedCache;->f:Lmiui/util/SoftReferenceSingleton;

    invoke-virtual {v0}, Lmiui/util/SoftReferenceSingleton;->get()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/net/http/DiskBasedCache;

    return-object v0
.end method

.method private q(Ljava/lang/String;)Ljava/io/File;
    .locals 3

    .prologue
    .line 207
    const-string v0, "MD5"

    invoke-static {p1, v0}, Lmiui/security/DigestUtils;->get(Ljava/lang/CharSequence;Ljava/lang/String;)[B

    move-result-object v0

    invoke-static {v0}, Lmiui/text/ExtraTextUtils;->toHexReadable([B)Ljava/lang/String;

    move-result-object v0

    .line 208
    new-instance v1, Ljava/io/File;

    iget-object v2, p0, Lmiui/net/http/DiskBasedCache;->sj:Ljava/io/File;

    invoke-direct {v1, v2, v0}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    return-object v1
.end method


# virtual methods
.method public clear()V
    .locals 2

    .prologue
    .line 192
    iget-object v0, p0, Lmiui/net/http/DiskBasedCache;->sh:Ljava/util/Map;

    invoke-interface {v0}, Ljava/util/Map;->entrySet()Ljava/util/Set;

    move-result-object v0

    invoke-interface {v0}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :goto_0
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_0

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/util/Map$Entry;

    .line 193
    invoke-interface {v0}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/net/http/DiskBasedCache$a;

    invoke-virtual {v0}, Lmiui/net/http/DiskBasedCache$a;->delete()V

    goto :goto_0

    .line 196
    :cond_0
    iget-object v0, p0, Lmiui/net/http/DiskBasedCache;->sh:Ljava/util/Map;

    invoke-interface {v0}, Ljava/util/Map;->clear()V

    .line 197
    const-wide/16 v0, 0x0

    iput-wide v0, p0, Lmiui/net/http/DiskBasedCache;->si:J

    .line 198
    return-void
.end method

.method public get(Ljava/lang/String;)Lmiui/net/http/Cache$Entry;
    .locals 1

    .prologue
    .line 121
    iget-object v0, p0, Lmiui/net/http/DiskBasedCache;->sh:Ljava/util/Map;

    invoke-interface {v0, p1}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/net/http/DiskBasedCache$a;

    .line 122
    if-nez v0, :cond_0

    .line 123
    const/4 v0, 0x0

    .line 126
    :goto_0
    return-object v0

    :cond_0
    invoke-virtual {v0}, Lmiui/net/http/DiskBasedCache$a;->do()Lmiui/net/http/Cache$Entry;

    move-result-object v0

    goto :goto_0
.end method

.method public initialize()V
    .locals 7

    .prologue
    .line 143
    iget-object v0, p0, Lmiui/net/http/DiskBasedCache;->sh:Ljava/util/Map;

    invoke-interface {v0}, Ljava/util/Map;->clear()V

    .line 144
    const-wide/16 v0, 0x0

    iput-wide v0, p0, Lmiui/net/http/DiskBasedCache;->si:J

    .line 146
    iget-object v0, p0, Lmiui/net/http/DiskBasedCache;->sj:Ljava/io/File;

    invoke-virtual {v0}, Ljava/io/File;->exists()Z

    move-result v0

    if-nez v0, :cond_1

    .line 147
    iget-object v0, p0, Lmiui/net/http/DiskBasedCache;->sj:Ljava/io/File;

    invoke-virtual {v0}, Ljava/io/File;->mkdirs()Z

    move-result v0

    if-nez v0, :cond_0

    .line 148
    const-string v0, "DisBasedCache"

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "Cannot create directory "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    iget-object v2, p0, Lmiui/net/http/DiskBasedCache;->sj:Ljava/io/File;

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 168
    :cond_0
    return-void

    .line 153
    :cond_1
    iget-object v0, p0, Lmiui/net/http/DiskBasedCache;->sj:Ljava/io/File;

    invoke-virtual {v0}, Ljava/io/File;->listFiles()[Ljava/io/File;

    move-result-object v1

    .line 154
    if-eqz v1, :cond_0

    .line 158
    array-length v2, v1

    const/4 v0, 0x0

    :goto_0
    if-ge v0, v2, :cond_0

    aget-object v3, v1, v0

    .line 159
    invoke-static {v3}, Lmiui/net/http/DiskBasedCache$a;->b(Ljava/io/File;)Lmiui/net/http/DiskBasedCache$a;

    move-result-object v4

    .line 160
    if-eqz v4, :cond_3

    .line 161
    invoke-direct {p0, v4}, Lmiui/net/http/DiskBasedCache;->a(Lmiui/net/http/DiskBasedCache$a;)V

    .line 158
    :cond_2
    :goto_1
    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    .line 163
    :cond_3
    invoke-virtual {v3}, Ljava/io/File;->delete()Z

    move-result v4

    if-nez v4, :cond_2

    .line 164
    const-string v4, "DisBasedCache"

    new-instance v5, Ljava/lang/StringBuilder;

    invoke-direct {v5}, Ljava/lang/StringBuilder;-><init>()V

    const-string v6, "Cannot delete file "

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v5

    invoke-virtual {v5, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-static {v4, v3}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    goto :goto_1
.end method

.method public invalidate(Ljava/lang/String;Z)V
    .locals 3

    .prologue
    const-wide/16 v1, 0x0

    .line 172
    iget-object v0, p0, Lmiui/net/http/DiskBasedCache;->sh:Ljava/util/Map;

    invoke-interface {v0, p1}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/net/http/DiskBasedCache$a;

    .line 173
    if-eqz v0, :cond_0

    .line 174
    iput-wide v1, v0, Lmiui/net/http/DiskBasedCache$a;->softTtl:J

    .line 175
    if-eqz p2, :cond_0

    .line 176
    iput-wide v1, v0, Lmiui/net/http/DiskBasedCache$a;->ttl:J

    .line 179
    :cond_0
    return-void
.end method

.method public put(Ljava/lang/String;Lmiui/net/http/Cache$Entry;)Z
    .locals 3

    .prologue
    .line 131
    invoke-direct {p0, p1}, Lmiui/net/http/DiskBasedCache;->q(Ljava/lang/String;)Ljava/io/File;

    move-result-object v0

    .line 132
    invoke-static {v0, p1, p2}, Lmiui/net/http/DiskBasedCache$a;->a(Ljava/io/File;Ljava/lang/String;Lmiui/net/http/Cache$Entry;)Lmiui/net/http/DiskBasedCache$a;

    move-result-object v0

    .line 133
    if-eqz v0, :cond_0

    .line 134
    iget-wide v1, v0, Lmiui/net/http/DiskBasedCache$a;->FG:J

    invoke-direct {p0, v1, v2}, Lmiui/net/http/DiskBasedCache;->e(J)V

    .line 135
    invoke-direct {p0, v0}, Lmiui/net/http/DiskBasedCache;->a(Lmiui/net/http/DiskBasedCache$a;)V

    .line 136
    const/4 v0, 0x1

    .line 138
    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public remove(Ljava/lang/String;)V
    .locals 1

    .prologue
    .line 183
    iget-object v0, p0, Lmiui/net/http/DiskBasedCache;->sh:Ljava/util/Map;

    invoke-interface {v0, p1}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/net/http/DiskBasedCache$a;

    .line 184
    if-eqz v0, :cond_0

    .line 185
    invoke-virtual {v0}, Lmiui/net/http/DiskBasedCache$a;->delete()V

    .line 186
    invoke-direct {p0, v0}, Lmiui/net/http/DiskBasedCache;->b(Lmiui/net/http/DiskBasedCache$a;)V

    .line 188
    :cond_0
    return-void
.end method
