.class public final Lmiui/util/ArrayMap;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/util/Map;


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "<K:",
        "Ljava/lang/Object;",
        "V:",
        "Ljava/lang/Object;",
        ">",
        "Ljava/lang/Object;",
        "Ljava/util/Map",
        "<TK;TV;>;"
    }
.end annotation


# static fields
.field private static final DEBUG:Z = false

.field private static final TAG:Ljava/lang/String; = "ArrayMap"

.field private static final he:I = 0x4

.field private static final hf:I = 0xa

.field static hg:[Ljava/lang/Object;

.field static hh:I

.field static hi:[Ljava/lang/Object;

.field static hj:I


# instance fields
.field hk:[I

.field hl:[Ljava/lang/Object;

.field hm:I

.field hn:Lmiui/util/a;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lmiui/util/a",
            "<TK;TV;>;"
        }
    .end annotation
.end field


# direct methods
.method public constructor <init>()V
    .locals 1

    .prologue
    .line 213
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 214
    sget-object v0, Lmiui/util/b;->al:[I

    iput-object v0, p0, Lmiui/util/ArrayMap;->hk:[I

    .line 215
    sget-object v0, Lmiui/util/b;->ao:[Ljava/lang/Object;

    iput-object v0, p0, Lmiui/util/ArrayMap;->hl:[Ljava/lang/Object;

    .line 216
    const/4 v0, 0x0

    iput v0, p0, Lmiui/util/ArrayMap;->hm:I

    .line 217
    return-void
.end method

.method public constructor <init>(I)V
    .locals 1

    .prologue
    .line 222
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 223
    if-nez p1, :cond_0

    .line 224
    sget-object v0, Lmiui/util/b;->al:[I

    iput-object v0, p0, Lmiui/util/ArrayMap;->hk:[I

    .line 225
    sget-object v0, Lmiui/util/b;->ao:[Ljava/lang/Object;

    iput-object v0, p0, Lmiui/util/ArrayMap;->hl:[Ljava/lang/Object;

    .line 229
    :goto_0
    const/4 v0, 0x0

    iput v0, p0, Lmiui/util/ArrayMap;->hm:I

    .line 230
    return-void

    .line 227
    :cond_0
    invoke-direct {p0, p1}, Lmiui/util/ArrayMap;->y(I)V

    goto :goto_0
.end method

.method public constructor <init>(Lmiui/util/ArrayMap;)V
    .locals 0

    .prologue
    .line 236
    invoke-direct {p0}, Lmiui/util/ArrayMap;-><init>()V

    .line 237
    if-eqz p1, :cond_0

    .line 238
    invoke-virtual {p0, p1}, Lmiui/util/ArrayMap;->putAll(Lmiui/util/ArrayMap;)V

    .line 240
    :cond_0
    return-void
.end method

.method private static a([I[Ljava/lang/Object;I)V
    .locals 4

    .prologue
    const/16 v2, 0xa

    const/4 v3, 0x2

    .line 178
    array-length v0, p0

    const/16 v1, 0x8

    if-ne v0, v1, :cond_3

    .line 179
    const-class v1, Lmiui/util/ArrayMap;

    monitor-enter v1

    .line 180
    :try_start_0
    sget v0, Lmiui/util/ArrayMap;->hj:I

    if-ge v0, v2, :cond_1

    .line 181
    const/4 v0, 0x0

    sget-object v2, Lmiui/util/ArrayMap;->hi:[Ljava/lang/Object;

    aput-object v2, p1, v0

    .line 182
    const/4 v0, 0x1

    aput-object p0, p1, v0

    .line 183
    shl-int/lit8 v0, p2, 0x1

    add-int/lit8 v0, v0, -0x1

    :goto_0
    if-lt v0, v3, :cond_0

    .line 184
    const/4 v2, 0x0

    aput-object v2, p1, v0

    .line 183
    add-int/lit8 v0, v0, -0x1

    goto :goto_0

    .line 186
    :cond_0
    sput-object p1, Lmiui/util/ArrayMap;->hi:[Ljava/lang/Object;

    .line 187
    sget v0, Lmiui/util/ArrayMap;->hj:I

    add-int/lit8 v0, v0, 0x1

    sput v0, Lmiui/util/ArrayMap;->hj:I

    .line 191
    :cond_1
    monitor-exit v1

    .line 207
    :cond_2
    :goto_1
    return-void

    .line 191
    :catchall_0
    move-exception v0

    monitor-exit v1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw v0

    .line 192
    :cond_3
    array-length v0, p0

    const/4 v1, 0x4

    if-ne v0, v1, :cond_2

    .line 193
    const-class v1, Lmiui/util/ArrayMap;

    monitor-enter v1

    .line 194
    :try_start_1
    sget v0, Lmiui/util/ArrayMap;->hh:I

    if-ge v0, v2, :cond_5

    .line 195
    const/4 v0, 0x0

    sget-object v2, Lmiui/util/ArrayMap;->hg:[Ljava/lang/Object;

    aput-object v2, p1, v0

    .line 196
    const/4 v0, 0x1

    aput-object p0, p1, v0

    .line 197
    shl-int/lit8 v0, p2, 0x1

    add-int/lit8 v0, v0, -0x1

    :goto_2
    if-lt v0, v3, :cond_4

    .line 198
    const/4 v2, 0x0

    aput-object v2, p1, v0

    .line 197
    add-int/lit8 v0, v0, -0x1

    goto :goto_2

    .line 200
    :cond_4
    sput-object p1, Lmiui/util/ArrayMap;->hg:[Ljava/lang/Object;

    .line 201
    sget v0, Lmiui/util/ArrayMap;->hh:I

    add-int/lit8 v0, v0, 0x1

    sput v0, Lmiui/util/ArrayMap;->hh:I

    .line 205
    :cond_5
    monitor-exit v1

    goto :goto_1

    :catchall_1
    move-exception v0

    monitor-exit v1
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_1

    throw v0
.end method

.method private aj()Lmiui/util/a;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Lmiui/util/a",
            "<TK;TV;>;"
        }
    .end annotation

    .prologue
    .line 668
    iget-object v0, p0, Lmiui/util/ArrayMap;->hn:Lmiui/util/a;

    if-nez v0, :cond_0

    .line 669
    new-instance v0, Lmiui/util/ArrayMap$1;

    invoke-direct {v0, p0}, Lmiui/util/ArrayMap$1;-><init>(Lmiui/util/ArrayMap;)V

    iput-object v0, p0, Lmiui/util/ArrayMap;->hn:Lmiui/util/a;

    .line 716
    :cond_0
    iget-object v0, p0, Lmiui/util/ArrayMap;->hn:Lmiui/util/a;

    return-object v0
.end method

.method private y(I)V
    .locals 5

    .prologue
    .line 143
    const/16 v0, 0x8

    if-ne p1, v0, :cond_2

    .line 144
    const-class v1, Lmiui/util/ArrayMap;

    monitor-enter v1

    .line 145
    :try_start_0
    sget-object v0, Lmiui/util/ArrayMap;->hi:[Ljava/lang/Object;

    if-eqz v0, :cond_0

    .line 146
    sget-object v2, Lmiui/util/ArrayMap;->hi:[Ljava/lang/Object;

    .line 147
    iput-object v2, p0, Lmiui/util/ArrayMap;->hl:[Ljava/lang/Object;

    .line 148
    const/4 v0, 0x0

    aget-object v0, v2, v0

    check-cast v0, [Ljava/lang/Object;

    check-cast v0, [Ljava/lang/Object;

    sput-object v0, Lmiui/util/ArrayMap;->hi:[Ljava/lang/Object;

    .line 149
    const/4 v0, 0x1

    aget-object v0, v2, v0

    check-cast v0, [I

    check-cast v0, [I

    iput-object v0, p0, Lmiui/util/ArrayMap;->hk:[I

    .line 150
    const/4 v0, 0x0

    const/4 v3, 0x1

    const/4 v4, 0x0

    aput-object v4, v2, v3

    aput-object v4, v2, v0

    .line 151
    sget v0, Lmiui/util/ArrayMap;->hj:I

    add-int/lit8 v0, v0, -0x1

    sput v0, Lmiui/util/ArrayMap;->hj:I

    .line 154
    monitor-exit v1

    .line 175
    :goto_0
    return-void

    .line 156
    :cond_0
    monitor-exit v1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 173
    :cond_1
    :goto_1
    new-array v0, p1, [I

    iput-object v0, p0, Lmiui/util/ArrayMap;->hk:[I

    .line 174
    shl-int/lit8 v0, p1, 0x1

    new-array v0, v0, [Ljava/lang/Object;

    iput-object v0, p0, Lmiui/util/ArrayMap;->hl:[Ljava/lang/Object;

    goto :goto_0

    .line 156
    :catchall_0
    move-exception v0

    :try_start_1
    monitor-exit v1
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw v0

    .line 157
    :cond_2
    const/4 v0, 0x4

    if-ne p1, v0, :cond_1

    .line 158
    const-class v1, Lmiui/util/ArrayMap;

    monitor-enter v1

    .line 159
    :try_start_2
    sget-object v0, Lmiui/util/ArrayMap;->hg:[Ljava/lang/Object;

    if-eqz v0, :cond_3

    .line 160
    sget-object v2, Lmiui/util/ArrayMap;->hg:[Ljava/lang/Object;

    .line 161
    iput-object v2, p0, Lmiui/util/ArrayMap;->hl:[Ljava/lang/Object;

    .line 162
    const/4 v0, 0x0

    aget-object v0, v2, v0

    check-cast v0, [Ljava/lang/Object;

    check-cast v0, [Ljava/lang/Object;

    sput-object v0, Lmiui/util/ArrayMap;->hg:[Ljava/lang/Object;

    .line 163
    const/4 v0, 0x1

    aget-object v0, v2, v0

    check-cast v0, [I

    check-cast v0, [I

    iput-object v0, p0, Lmiui/util/ArrayMap;->hk:[I

    .line 164
    const/4 v0, 0x0

    const/4 v3, 0x1

    const/4 v4, 0x0

    aput-object v4, v2, v3

    aput-object v4, v2, v0

    .line 165
    sget v0, Lmiui/util/ArrayMap;->hh:I

    add-int/lit8 v0, v0, -0x1

    sput v0, Lmiui/util/ArrayMap;->hh:I

    .line 168
    monitor-exit v1

    goto :goto_0

    .line 170
    :catchall_1
    move-exception v0

    monitor-exit v1
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_1

    throw v0

    :cond_3
    :try_start_3
    monitor-exit v1
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_1

    goto :goto_1
.end method


# virtual methods
.method ai()I
    .locals 5

    .prologue
    .line 105
    iget v2, p0, Lmiui/util/ArrayMap;->hm:I

    .line 108
    if-nez v2, :cond_1

    .line 109
    const/4 v0, -0x1

    .line 139
    :cond_0
    :goto_0
    return v0

    .line 112
    :cond_1
    iget-object v0, p0, Lmiui/util/ArrayMap;->hk:[I

    const/4 v1, 0x0

    invoke-static {v0, v2, v1}, Lmiui/util/b;->a([III)I

    move-result v0

    .line 115
    if-ltz v0, :cond_0

    .line 120
    iget-object v1, p0, Lmiui/util/ArrayMap;->hl:[Ljava/lang/Object;

    shl-int/lit8 v3, v0, 0x1

    aget-object v1, v1, v3

    if-eqz v1, :cond_0

    .line 126
    add-int/lit8 v1, v0, 0x1

    :goto_1
    if-ge v1, v2, :cond_3

    iget-object v3, p0, Lmiui/util/ArrayMap;->hk:[I

    aget v3, v3, v1

    if-nez v3, :cond_3

    .line 127
    iget-object v3, p0, Lmiui/util/ArrayMap;->hl:[Ljava/lang/Object;

    shl-int/lit8 v4, v1, 0x1

    aget-object v3, v3, v4

    if-nez v3, :cond_2

    move v0, v1

    goto :goto_0

    .line 126
    :cond_2
    add-int/lit8 v1, v1, 0x1

    goto :goto_1

    .line 131
    :cond_3
    add-int/lit8 v0, v0, -0x1

    :goto_2
    if-ltz v0, :cond_4

    iget-object v2, p0, Lmiui/util/ArrayMap;->hk:[I

    aget v2, v2, v0

    if-nez v2, :cond_4

    .line 132
    iget-object v2, p0, Lmiui/util/ArrayMap;->hl:[Ljava/lang/Object;

    shl-int/lit8 v3, v0, 0x1

    aget-object v2, v2, v3

    if-eqz v2, :cond_0

    .line 131
    add-int/lit8 v0, v0, -0x1

    goto :goto_2

    .line 139
    :cond_4
    xor-int/lit8 v0, v1, -0x1

    goto :goto_0
.end method

.method public append(Ljava/lang/Object;Ljava/lang/Object;)V
    .locals 6
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(TK;TV;)V"
        }
    .end annotation

    .prologue
    .line 446
    iget v1, p0, Lmiui/util/ArrayMap;->hm:I

    .line 447
    if-nez p1, :cond_0

    const/4 v0, 0x0

    .line 448
    :goto_0
    iget-object v2, p0, Lmiui/util/ArrayMap;->hk:[I

    array-length v2, v2

    if-lt v1, v2, :cond_1

    .line 449
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "Array is full"

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 447
    :cond_0
    invoke-virtual {p1}, Ljava/lang/Object;->hashCode()I

    move-result v0

    goto :goto_0

    .line 451
    :cond_1
    if-lez v1, :cond_2

    iget-object v2, p0, Lmiui/util/ArrayMap;->hk:[I

    add-int/lit8 v3, v1, -0x1

    aget v2, v2, v3

    if-le v2, v0, :cond_2

    .line 452
    new-instance v2, Ljava/lang/RuntimeException;

    const-string v3, "here"

    invoke-direct {v2, v3}, Ljava/lang/RuntimeException;-><init>(Ljava/lang/String;)V

    .line 453
    invoke-virtual {v2}, Ljava/lang/RuntimeException;->fillInStackTrace()Ljava/lang/Throwable;

    .line 454
    const-string v3, "ArrayMap"

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    const-string v5, "New hash "

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4, v0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v0

    const-string v4, " is before end of array hash "

    invoke-virtual {v0, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    iget-object v4, p0, Lmiui/util/ArrayMap;->hk:[I

    add-int/lit8 v5, v1, -0x1

    aget v4, v4, v5

    invoke-virtual {v0, v4}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v0

    const-string v4, " at index "

    invoke-virtual {v0, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v0

    const-string v1, " key "

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-static {v3, v0, v2}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    .line 457
    invoke-virtual {p0, p1, p2}, Lmiui/util/ArrayMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 465
    :goto_1
    return-void

    .line 460
    :cond_2
    add-int/lit8 v2, v1, 0x1

    iput v2, p0, Lmiui/util/ArrayMap;->hm:I

    .line 461
    iget-object v2, p0, Lmiui/util/ArrayMap;->hk:[I

    aput v0, v2, v1

    .line 462
    shl-int/lit8 v0, v1, 0x1

    .line 463
    iget-object v1, p0, Lmiui/util/ArrayMap;->hl:[Ljava/lang/Object;

    aput-object p1, v1, v0

    .line 464
    iget-object v1, p0, Lmiui/util/ArrayMap;->hl:[Ljava/lang/Object;

    add-int/lit8 v0, v0, 0x1

    aput-object p2, v1, v0

    goto :goto_1
.end method

.method public clear()V
    .locals 3

    .prologue
    .line 247
    iget v0, p0, Lmiui/util/ArrayMap;->hm:I

    if-lez v0, :cond_0

    .line 248
    iget-object v0, p0, Lmiui/util/ArrayMap;->hk:[I

    iget-object v1, p0, Lmiui/util/ArrayMap;->hl:[Ljava/lang/Object;

    iget v2, p0, Lmiui/util/ArrayMap;->hm:I

    invoke-static {v0, v1, v2}, Lmiui/util/ArrayMap;->a([I[Ljava/lang/Object;I)V

    .line 249
    sget-object v0, Lmiui/util/b;->al:[I

    iput-object v0, p0, Lmiui/util/ArrayMap;->hk:[I

    .line 250
    sget-object v0, Lmiui/util/b;->ao:[Ljava/lang/Object;

    iput-object v0, p0, Lmiui/util/ArrayMap;->hl:[Ljava/lang/Object;

    .line 251
    const/4 v0, 0x0

    iput v0, p0, Lmiui/util/ArrayMap;->hm:I

    .line 253
    :cond_0
    return-void
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
    .line 726
    invoke-static {p0, p1}, Lmiui/util/a;->a(Ljava/util/Map;Ljava/util/Collection;)Z

    move-result v0

    return v0
.end method

.method public containsKey(Ljava/lang/Object;)Z
    .locals 3

    .prologue
    const/4 v0, 0x1

    const/4 v1, 0x0

    .line 295
    if-nez p1, :cond_2

    invoke-virtual {p0}, Lmiui/util/ArrayMap;->ai()I

    move-result v2

    if-ltz v2, :cond_1

    :cond_0
    :goto_0
    return v0

    :cond_1
    move v0, v1

    goto :goto_0

    :cond_2
    invoke-virtual {p1}, Ljava/lang/Object;->hashCode()I

    move-result v2

    invoke-virtual {p0, p1, v2}, Lmiui/util/ArrayMap;->indexOf(Ljava/lang/Object;I)I

    move-result v2

    if-gez v2, :cond_0

    move v0, v1

    goto :goto_0
.end method

.method public containsValue(Ljava/lang/Object;)Z
    .locals 1

    .prologue
    .line 326
    invoke-virtual {p0, p1}, Lmiui/util/ArrayMap;->indexOfValue(Ljava/lang/Object;)I

    move-result v0

    if-ltz v0, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public ensureCapacity(I)V
    .locals 5

    .prologue
    const/4 v4, 0x0

    .line 275
    iget-object v0, p0, Lmiui/util/ArrayMap;->hk:[I

    array-length v0, v0

    if-ge v0, p1, :cond_1

    .line 276
    iget-object v0, p0, Lmiui/util/ArrayMap;->hk:[I

    .line 277
    iget-object v1, p0, Lmiui/util/ArrayMap;->hl:[Ljava/lang/Object;

    .line 278
    invoke-direct {p0, p1}, Lmiui/util/ArrayMap;->y(I)V

    .line 279
    iget v2, p0, Lmiui/util/ArrayMap;->hm:I

    if-lez v2, :cond_0

    .line 280
    iget-object v2, p0, Lmiui/util/ArrayMap;->hk:[I

    iget v3, p0, Lmiui/util/ArrayMap;->hm:I

    invoke-static {v0, v4, v2, v4, v3}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    .line 281
    iget-object v2, p0, Lmiui/util/ArrayMap;->hl:[Ljava/lang/Object;

    iget v3, p0, Lmiui/util/ArrayMap;->hm:I

    shl-int/lit8 v3, v3, 0x1

    invoke-static {v1, v4, v2, v4, v3}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    .line 283
    :cond_0
    iget v2, p0, Lmiui/util/ArrayMap;->hm:I

    invoke-static {v0, v1, v2}, Lmiui/util/ArrayMap;->a([I[Ljava/lang/Object;I)V

    .line 285
    :cond_1
    return-void
.end method

.method public entrySet()Ljava/util/Set;
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
    .line 775
    invoke-direct {p0}, Lmiui/util/ArrayMap;->aj()Lmiui/util/a;

    move-result-object v0

    invoke-virtual {v0}, Lmiui/util/a;->cq()Ljava/util/Set;

    move-result-object v0

    return-object v0
.end method

.method public equals(Ljava/lang/Object;)Z
    .locals 6

    .prologue
    const/4 v0, 0x1

    const/4 v1, 0x0

    .line 578
    if-ne p0, p1, :cond_1

    .line 607
    :cond_0
    :goto_0
    return v0

    .line 581
    :cond_1
    instance-of v2, p1, Ljava/util/Map;

    if-eqz v2, :cond_6

    .line 582
    check-cast p1, Ljava/util/Map;

    .line 583
    invoke-virtual {p0}, Lmiui/util/ArrayMap;->size()I

    move-result v2

    invoke-interface {p1}, Ljava/util/Map;->size()I

    move-result v3

    if-eq v2, v3, :cond_2

    move v0, v1

    .line 584
    goto :goto_0

    :cond_2
    move v2, v1

    .line 588
    :goto_1
    :try_start_0
    iget v3, p0, Lmiui/util/ArrayMap;->hm:I

    if-ge v2, v3, :cond_0

    .line 589
    invoke-virtual {p0, v2}, Lmiui/util/ArrayMap;->keyAt(I)Ljava/lang/Object;

    move-result-object v3

    .line 590
    invoke-virtual {p0, v2}, Lmiui/util/ArrayMap;->valueAt(I)Ljava/lang/Object;

    move-result-object v4

    .line 591
    invoke-interface {p1, v3}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v5

    .line 592
    if-nez v4, :cond_4

    .line 593
    if-nez v5, :cond_3

    invoke-interface {p1, v3}, Ljava/util/Map;->containsKey(Ljava/lang/Object;)Z

    move-result v3

    if-nez v3, :cond_5

    :cond_3
    move v0, v1

    .line 594
    goto :goto_0

    .line 596
    :cond_4
    invoke-virtual {v4, v5}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z
    :try_end_0
    .catch Ljava/lang/NullPointerException; {:try_start_0 .. :try_end_0} :catch_0
    .catch Ljava/lang/ClassCastException; {:try_start_0 .. :try_end_0} :catch_1

    move-result v3

    if-nez v3, :cond_5

    move v0, v1

    .line 597
    goto :goto_0

    .line 588
    :cond_5
    add-int/lit8 v2, v2, 0x1

    goto :goto_1

    .line 600
    :catch_0
    move-exception v0

    move v0, v1

    .line 601
    goto :goto_0

    .line 602
    :catch_1
    move-exception v0

    move v0, v1

    .line 603
    goto :goto_0

    :cond_6
    move v0, v1

    .line 607
    goto :goto_0
.end method

.method public erase()V
    .locals 5

    .prologue
    const/4 v1, 0x0

    .line 260
    iget v0, p0, Lmiui/util/ArrayMap;->hm:I

    if-lez v0, :cond_1

    .line 261
    iget v0, p0, Lmiui/util/ArrayMap;->hm:I

    shl-int/lit8 v2, v0, 0x1

    .line 262
    iget-object v3, p0, Lmiui/util/ArrayMap;->hl:[Ljava/lang/Object;

    move v0, v1

    .line 263
    :goto_0
    if-ge v0, v2, :cond_0

    .line 264
    const/4 v4, 0x0

    aput-object v4, v3, v0

    .line 263
    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    .line 266
    :cond_0
    iput v1, p0, Lmiui/util/ArrayMap;->hm:I

    .line 268
    :cond_1
    return-void
.end method

.method public get(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/Object;",
            ")TV;"
        }
    .end annotation

    .prologue
    .line 337
    if-nez p1, :cond_0

    invoke-virtual {p0}, Lmiui/util/ArrayMap;->ai()I

    move-result v0

    .line 338
    :goto_0
    if-ltz v0, :cond_1

    iget-object v1, p0, Lmiui/util/ArrayMap;->hl:[Ljava/lang/Object;

    shl-int/lit8 v0, v0, 0x1

    add-int/lit8 v0, v0, 0x1

    aget-object v0, v1, v0

    :goto_1
    return-object v0

    .line 337
    :cond_0
    invoke-virtual {p1}, Ljava/lang/Object;->hashCode()I

    move-result v0

    invoke-virtual {p0, p1, v0}, Lmiui/util/ArrayMap;->indexOf(Ljava/lang/Object;I)I

    move-result v0

    goto :goto_0

    .line 338
    :cond_1
    const/4 v0, 0x0

    goto :goto_1
.end method

.method public hashCode()I
    .locals 9

    .prologue
    const/4 v1, 0x0

    .line 615
    iget-object v5, p0, Lmiui/util/ArrayMap;->hk:[I

    .line 616
    iget-object v6, p0, Lmiui/util/ArrayMap;->hl:[Ljava/lang/Object;

    .line 618
    const/4 v0, 0x1

    iget v7, p0, Lmiui/util/ArrayMap;->hm:I

    move v2, v0

    move v3, v1

    move v4, v1

    :goto_0
    if-ge v3, v7, :cond_1

    .line 619
    aget-object v0, v6, v2

    .line 620
    aget v8, v5, v3

    if-nez v0, :cond_0

    move v0, v1

    :goto_1
    xor-int/2addr v0, v8

    add-int/2addr v4, v0

    .line 618
    add-int/lit8 v3, v3, 0x1

    add-int/lit8 v0, v2, 0x2

    move v2, v0

    goto :goto_0

    .line 620
    :cond_0
    invoke-virtual {v0}, Ljava/lang/Object;->hashCode()I

    move-result v0

    goto :goto_1

    .line 622
    :cond_1
    return v4
.end method

.method indexOf(Ljava/lang/Object;I)I
    .locals 5

    .prologue
    .line 67
    iget v2, p0, Lmiui/util/ArrayMap;->hm:I

    .line 70
    if-nez v2, :cond_1

    .line 71
    const/4 v0, -0x1

    .line 101
    :cond_0
    :goto_0
    return v0

    .line 74
    :cond_1
    iget-object v0, p0, Lmiui/util/ArrayMap;->hk:[I

    invoke-static {v0, v2, p2}, Lmiui/util/b;->a([III)I

    move-result v0

    .line 77
    if-ltz v0, :cond_0

    .line 82
    iget-object v1, p0, Lmiui/util/ArrayMap;->hl:[Ljava/lang/Object;

    shl-int/lit8 v3, v0, 0x1

    aget-object v1, v1, v3

    invoke-virtual {p1, v1}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_0

    .line 88
    add-int/lit8 v1, v0, 0x1

    :goto_1
    if-ge v1, v2, :cond_3

    iget-object v3, p0, Lmiui/util/ArrayMap;->hk:[I

    aget v3, v3, v1

    if-ne v3, p2, :cond_3

    .line 89
    iget-object v3, p0, Lmiui/util/ArrayMap;->hl:[Ljava/lang/Object;

    shl-int/lit8 v4, v1, 0x1

    aget-object v3, v3, v4

    invoke-virtual {p1, v3}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_2

    move v0, v1

    goto :goto_0

    .line 88
    :cond_2
    add-int/lit8 v1, v1, 0x1

    goto :goto_1

    .line 93
    :cond_3
    add-int/lit8 v0, v0, -0x1

    :goto_2
    if-ltz v0, :cond_4

    iget-object v2, p0, Lmiui/util/ArrayMap;->hk:[I

    aget v2, v2, v0

    if-ne v2, p2, :cond_4

    .line 94
    iget-object v2, p0, Lmiui/util/ArrayMap;->hl:[Ljava/lang/Object;

    shl-int/lit8 v3, v0, 0x1

    aget-object v2, v2, v3

    invoke-virtual {p1, v2}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v2

    if-nez v2, :cond_0

    .line 93
    add-int/lit8 v0, v0, -0x1

    goto :goto_2

    .line 101
    :cond_4
    xor-int/lit8 v0, v1, -0x1

    goto :goto_0
.end method

.method indexOfValue(Ljava/lang/Object;)I
    .locals 4

    .prologue
    const/4 v0, 0x1

    .line 299
    iget v1, p0, Lmiui/util/ArrayMap;->hm:I

    mul-int/lit8 v1, v1, 0x2

    .line 300
    iget-object v2, p0, Lmiui/util/ArrayMap;->hl:[Ljava/lang/Object;

    .line 301
    if-nez p1, :cond_2

    .line 302
    :goto_0
    if-ge v0, v1, :cond_3

    .line 303
    aget-object v3, v2, v0

    if-nez v3, :cond_0

    .line 304
    shr-int/lit8 v0, v0, 0x1

    .line 314
    :goto_1
    return v0

    .line 302
    :cond_0
    add-int/lit8 v0, v0, 0x2

    goto :goto_0

    .line 308
    :cond_1
    add-int/lit8 v0, v0, 0x2

    :cond_2
    if-ge v0, v1, :cond_3

    .line 309
    aget-object v3, v2, v0

    invoke-virtual {p1, v3}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_1

    .line 310
    shr-int/lit8 v0, v0, 0x1

    goto :goto_1

    .line 314
    :cond_3
    const/4 v0, -0x1

    goto :goto_1
.end method

.method public isEmpty()Z
    .locals 1

    .prologue
    .line 377
    iget v0, p0, Lmiui/util/ArrayMap;->hm:I

    if-gtz v0, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public keyAt(I)Ljava/lang/Object;
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(I)TK;"
        }
    .end annotation

    .prologue
    .line 347
    iget-object v0, p0, Lmiui/util/ArrayMap;->hl:[Ljava/lang/Object;

    shl-int/lit8 v1, p1, 0x1

    aget-object v0, v0, v1

    return-object v0
.end method

.method public keySet()Ljava/util/Set;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/Set",
            "<TK;>;"
        }
    .end annotation

    .prologue
    .line 787
    invoke-direct {p0}, Lmiui/util/ArrayMap;->aj()Lmiui/util/a;

    move-result-object v0

    invoke-virtual {v0}, Lmiui/util/a;->cr()Ljava/util/Set;

    move-result-object v0

    return-object v0
.end method

.method public put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 7
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(TK;TV;)TV;"
        }
    .end annotation

    .prologue
    const/16 v0, 0x8

    const/4 v1, 0x4

    const/4 v4, 0x0

    .line 392
    if-nez p1, :cond_0

    .line 394
    invoke-virtual {p0}, Lmiui/util/ArrayMap;->ai()I

    move-result v2

    move v3, v4

    .line 399
    :goto_0
    if-ltz v2, :cond_1

    .line 400
    shl-int/lit8 v0, v2, 0x1

    add-int/lit8 v1, v0, 0x1

    .line 401
    iget-object v0, p0, Lmiui/util/ArrayMap;->hl:[Ljava/lang/Object;

    aget-object v0, v0, v1

    .line 402
    iget-object v2, p0, Lmiui/util/ArrayMap;->hl:[Ljava/lang/Object;

    aput-object p2, v2, v1

    .line 437
    :goto_1
    return-object v0

    .line 396
    :cond_0
    invoke-virtual {p1}, Ljava/lang/Object;->hashCode()I

    move-result v3

    .line 397
    invoke-virtual {p0, p1, v3}, Lmiui/util/ArrayMap;->indexOf(Ljava/lang/Object;I)I

    move-result v2

    goto :goto_0

    .line 406
    :cond_1
    xor-int/lit8 v2, v2, -0x1

    .line 407
    iget v5, p0, Lmiui/util/ArrayMap;->hm:I

    iget-object v6, p0, Lmiui/util/ArrayMap;->hk:[I

    array-length v6, v6

    if-lt v5, v6, :cond_4

    .line 408
    iget v5, p0, Lmiui/util/ArrayMap;->hm:I

    if-lt v5, v0, :cond_6

    iget v0, p0, Lmiui/util/ArrayMap;->hm:I

    iget v1, p0, Lmiui/util/ArrayMap;->hm:I

    shr-int/lit8 v1, v1, 0x1

    add-int/2addr v0, v1

    .line 413
    :cond_2
    :goto_2
    iget-object v1, p0, Lmiui/util/ArrayMap;->hk:[I

    .line 414
    iget-object v5, p0, Lmiui/util/ArrayMap;->hl:[Ljava/lang/Object;

    .line 415
    invoke-direct {p0, v0}, Lmiui/util/ArrayMap;->y(I)V

    .line 417
    iget-object v0, p0, Lmiui/util/ArrayMap;->hk:[I

    array-length v0, v0

    if-lez v0, :cond_3

    .line 419
    iget-object v0, p0, Lmiui/util/ArrayMap;->hk:[I

    array-length v6, v1

    invoke-static {v1, v4, v0, v4, v6}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    .line 420
    iget-object v0, p0, Lmiui/util/ArrayMap;->hl:[Ljava/lang/Object;

    array-length v6, v5

    invoke-static {v5, v4, v0, v4, v6}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    .line 423
    :cond_3
    iget v0, p0, Lmiui/util/ArrayMap;->hm:I

    invoke-static {v1, v5, v0}, Lmiui/util/ArrayMap;->a([I[Ljava/lang/Object;I)V

    .line 426
    :cond_4
    iget v0, p0, Lmiui/util/ArrayMap;->hm:I

    if-ge v2, v0, :cond_5

    .line 429
    iget-object v0, p0, Lmiui/util/ArrayMap;->hk:[I

    iget-object v1, p0, Lmiui/util/ArrayMap;->hk:[I

    add-int/lit8 v4, v2, 0x1

    iget v5, p0, Lmiui/util/ArrayMap;->hm:I

    sub-int/2addr v5, v2

    invoke-static {v0, v2, v1, v4, v5}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    .line 430
    iget-object v0, p0, Lmiui/util/ArrayMap;->hl:[Ljava/lang/Object;

    shl-int/lit8 v1, v2, 0x1

    iget-object v4, p0, Lmiui/util/ArrayMap;->hl:[Ljava/lang/Object;

    add-int/lit8 v5, v2, 0x1

    shl-int/lit8 v5, v5, 0x1

    iget v6, p0, Lmiui/util/ArrayMap;->hm:I

    sub-int/2addr v6, v2

    shl-int/lit8 v6, v6, 0x1

    invoke-static {v0, v1, v4, v5, v6}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    .line 433
    :cond_5
    iget-object v0, p0, Lmiui/util/ArrayMap;->hk:[I

    aput v3, v0, v2

    .line 434
    iget-object v0, p0, Lmiui/util/ArrayMap;->hl:[Ljava/lang/Object;

    shl-int/lit8 v1, v2, 0x1

    aput-object p1, v0, v1

    .line 435
    iget-object v0, p0, Lmiui/util/ArrayMap;->hl:[Ljava/lang/Object;

    shl-int/lit8 v1, v2, 0x1

    add-int/lit8 v1, v1, 0x1

    aput-object p2, v0, v1

    .line 436
    iget v0, p0, Lmiui/util/ArrayMap;->hm:I

    add-int/lit8 v0, v0, 0x1

    iput v0, p0, Lmiui/util/ArrayMap;->hm:I

    .line 437
    const/4 v0, 0x0

    goto :goto_1

    .line 408
    :cond_6
    iget v5, p0, Lmiui/util/ArrayMap;->hm:I

    if-ge v5, v1, :cond_2

    move v0, v1

    goto :goto_2
.end method

.method public putAll(Ljava/util/Map;)V
    .locals 3
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/Map",
            "<+TK;+TV;>;)V"
        }
    .end annotation

    .prologue
    .line 735
    iget v0, p0, Lmiui/util/ArrayMap;->hm:I

    invoke-interface {p1}, Ljava/util/Map;->size()I

    move-result v1

    add-int/2addr v0, v1

    invoke-virtual {p0, v0}, Lmiui/util/ArrayMap;->ensureCapacity(I)V

    .line 736
    invoke-interface {p1}, Ljava/util/Map;->entrySet()Ljava/util/Set;

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

    .line 737
    invoke-interface {v0}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v2

    invoke-interface {v0}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v0

    invoke-virtual {p0, v2, v0}, Lmiui/util/ArrayMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    goto :goto_0

    .line 739
    :cond_0
    return-void
.end method

.method public putAll(Lmiui/util/ArrayMap;)V
    .locals 5
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lmiui/util/ArrayMap",
            "<+TK;+TV;>;)V"
        }
    .end annotation

    .prologue
    const/4 v0, 0x0

    .line 472
    iget v1, p1, Lmiui/util/ArrayMap;->hm:I

    .line 473
    iget v2, p0, Lmiui/util/ArrayMap;->hm:I

    add-int/2addr v2, v1

    invoke-virtual {p0, v2}, Lmiui/util/ArrayMap;->ensureCapacity(I)V

    .line 474
    iget v2, p0, Lmiui/util/ArrayMap;->hm:I

    if-nez v2, :cond_1

    .line 475
    if-lez v1, :cond_0

    .line 476
    iget-object v2, p1, Lmiui/util/ArrayMap;->hk:[I

    iget-object v3, p0, Lmiui/util/ArrayMap;->hk:[I

    invoke-static {v2, v0, v3, v0, v1}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    .line 477
    iget-object v2, p1, Lmiui/util/ArrayMap;->hl:[Ljava/lang/Object;

    iget-object v3, p0, Lmiui/util/ArrayMap;->hl:[Ljava/lang/Object;

    shl-int/lit8 v4, v1, 0x1

    invoke-static {v2, v0, v3, v0, v4}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    .line 478
    iput v1, p0, Lmiui/util/ArrayMap;->hm:I

    .line 485
    :cond_0
    return-void

    .line 481
    :cond_1
    :goto_0
    if-ge v0, v1, :cond_0

    .line 482
    invoke-virtual {p1, v0}, Lmiui/util/ArrayMap;->keyAt(I)Ljava/lang/Object;

    move-result-object v2

    invoke-virtual {p1, v0}, Lmiui/util/ArrayMap;->valueAt(I)Ljava/lang/Object;

    move-result-object v3

    invoke-virtual {p0, v2, v3}, Lmiui/util/ArrayMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 481
    add-int/lit8 v0, v0, 0x1

    goto :goto_0
.end method

.method public remove(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/Object;",
            ")TV;"
        }
    .end annotation

    .prologue
    .line 495
    if-nez p1, :cond_0

    invoke-virtual {p0}, Lmiui/util/ArrayMap;->ai()I

    move-result v0

    .line 496
    :goto_0
    if-ltz v0, :cond_1

    .line 497
    invoke-virtual {p0, v0}, Lmiui/util/ArrayMap;->removeAt(I)Ljava/lang/Object;

    move-result-object v0

    .line 500
    :goto_1
    return-object v0

    .line 495
    :cond_0
    invoke-virtual {p1}, Ljava/lang/Object;->hashCode()I

    move-result v0

    invoke-virtual {p0, p1, v0}, Lmiui/util/ArrayMap;->indexOf(Ljava/lang/Object;I)I

    move-result v0

    goto :goto_0

    .line 500
    :cond_1
    const/4 v0, 0x0

    goto :goto_1
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
    .line 747
    invoke-static {p0, p1}, Lmiui/util/a;->b(Ljava/util/Map;Ljava/util/Collection;)Z

    move-result v0

    return v0
.end method

.method public removeAt(I)Ljava/lang/Object;
    .locals 7
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(I)TV;"
        }
    .end annotation

    .prologue
    const/4 v6, 0x0

    const/16 v0, 0x8

    const/4 v5, 0x0

    .line 509
    iget-object v1, p0, Lmiui/util/ArrayMap;->hl:[Ljava/lang/Object;

    shl-int/lit8 v2, p1, 0x1

    add-int/lit8 v2, v2, 0x1

    aget-object v1, v1, v2

    .line 510
    iget v2, p0, Lmiui/util/ArrayMap;->hm:I

    const/4 v3, 0x1

    if-gt v2, v3, :cond_1

    .line 513
    iget-object v0, p0, Lmiui/util/ArrayMap;->hk:[I

    iget-object v2, p0, Lmiui/util/ArrayMap;->hl:[Ljava/lang/Object;

    iget v3, p0, Lmiui/util/ArrayMap;->hm:I

    invoke-static {v0, v2, v3}, Lmiui/util/ArrayMap;->a([I[Ljava/lang/Object;I)V

    .line 514
    sget-object v0, Lmiui/util/b;->al:[I

    iput-object v0, p0, Lmiui/util/ArrayMap;->hk:[I

    .line 515
    sget-object v0, Lmiui/util/b;->ao:[Ljava/lang/Object;

    iput-object v0, p0, Lmiui/util/ArrayMap;->hl:[Ljava/lang/Object;

    .line 516
    iput v5, p0, Lmiui/util/ArrayMap;->hm:I

    .line 557
    :cond_0
    :goto_0
    return-object v1

    .line 518
    :cond_1
    iget-object v2, p0, Lmiui/util/ArrayMap;->hk:[I

    array-length v2, v2

    if-le v2, v0, :cond_4

    iget v2, p0, Lmiui/util/ArrayMap;->hm:I

    iget-object v3, p0, Lmiui/util/ArrayMap;->hk:[I

    array-length v3, v3

    div-int/lit8 v3, v3, 0x3

    if-ge v2, v3, :cond_4

    .line 522
    iget v2, p0, Lmiui/util/ArrayMap;->hm:I

    if-le v2, v0, :cond_2

    iget v0, p0, Lmiui/util/ArrayMap;->hm:I

    iget v2, p0, Lmiui/util/ArrayMap;->hm:I

    shr-int/lit8 v2, v2, 0x1

    add-int/2addr v0, v2

    .line 527
    :cond_2
    iget-object v2, p0, Lmiui/util/ArrayMap;->hk:[I

    .line 528
    iget-object v3, p0, Lmiui/util/ArrayMap;->hl:[Ljava/lang/Object;

    .line 529
    invoke-direct {p0, v0}, Lmiui/util/ArrayMap;->y(I)V

    .line 531
    iget v0, p0, Lmiui/util/ArrayMap;->hm:I

    add-int/lit8 v0, v0, -0x1

    iput v0, p0, Lmiui/util/ArrayMap;->hm:I

    .line 532
    if-lez p1, :cond_3

    .line 534
    iget-object v0, p0, Lmiui/util/ArrayMap;->hk:[I

    invoke-static {v2, v5, v0, v5, p1}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    .line 535
    iget-object v0, p0, Lmiui/util/ArrayMap;->hl:[Ljava/lang/Object;

    shl-int/lit8 v4, p1, 0x1

    invoke-static {v3, v5, v0, v5, v4}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    .line 537
    :cond_3
    iget v0, p0, Lmiui/util/ArrayMap;->hm:I

    if-ge p1, v0, :cond_0

    .line 540
    add-int/lit8 v0, p1, 0x1

    iget-object v4, p0, Lmiui/util/ArrayMap;->hk:[I

    iget v5, p0, Lmiui/util/ArrayMap;->hm:I

    sub-int/2addr v5, p1

    invoke-static {v2, v0, v4, p1, v5}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    .line 541
    add-int/lit8 v0, p1, 0x1

    shl-int/lit8 v0, v0, 0x1

    iget-object v2, p0, Lmiui/util/ArrayMap;->hl:[Ljava/lang/Object;

    shl-int/lit8 v4, p1, 0x1

    iget v5, p0, Lmiui/util/ArrayMap;->hm:I

    sub-int/2addr v5, p1

    shl-int/lit8 v5, v5, 0x1

    invoke-static {v3, v0, v2, v4, v5}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    goto :goto_0

    .line 545
    :cond_4
    iget v0, p0, Lmiui/util/ArrayMap;->hm:I

    add-int/lit8 v0, v0, -0x1

    iput v0, p0, Lmiui/util/ArrayMap;->hm:I

    .line 546
    iget v0, p0, Lmiui/util/ArrayMap;->hm:I

    if-ge p1, v0, :cond_5

    .line 549
    iget-object v0, p0, Lmiui/util/ArrayMap;->hk:[I

    add-int/lit8 v2, p1, 0x1

    iget-object v3, p0, Lmiui/util/ArrayMap;->hk:[I

    iget v4, p0, Lmiui/util/ArrayMap;->hm:I

    sub-int/2addr v4, p1

    invoke-static {v0, v2, v3, p1, v4}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    .line 550
    iget-object v0, p0, Lmiui/util/ArrayMap;->hl:[Ljava/lang/Object;

    add-int/lit8 v2, p1, 0x1

    shl-int/lit8 v2, v2, 0x1

    iget-object v3, p0, Lmiui/util/ArrayMap;->hl:[Ljava/lang/Object;

    shl-int/lit8 v4, p1, 0x1

    iget v5, p0, Lmiui/util/ArrayMap;->hm:I

    sub-int/2addr v5, p1

    shl-int/lit8 v5, v5, 0x1

    invoke-static {v0, v2, v3, v4, v5}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    .line 553
    :cond_5
    iget-object v0, p0, Lmiui/util/ArrayMap;->hl:[Ljava/lang/Object;

    iget v2, p0, Lmiui/util/ArrayMap;->hm:I

    shl-int/lit8 v2, v2, 0x1

    aput-object v6, v0, v2

    .line 554
    iget-object v0, p0, Lmiui/util/ArrayMap;->hl:[Ljava/lang/Object;

    iget v2, p0, Lmiui/util/ArrayMap;->hm:I

    shl-int/lit8 v2, v2, 0x1

    add-int/lit8 v2, v2, 0x1

    aput-object v6, v0, v2

    goto/16 :goto_0
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
    .line 757
    invoke-static {p0, p1}, Lmiui/util/a;->c(Ljava/util/Map;Ljava/util/Collection;)Z

    move-result v0

    return v0
.end method

.method public setValueAt(ILjava/lang/Object;)Ljava/lang/Object;
    .locals 3
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(ITV;)TV;"
        }
    .end annotation

    .prologue
    .line 366
    shl-int/lit8 v0, p1, 0x1

    add-int/lit8 v0, v0, 0x1

    .line 367
    iget-object v1, p0, Lmiui/util/ArrayMap;->hl:[Ljava/lang/Object;

    aget-object v1, v1, v0

    .line 368
    iget-object v2, p0, Lmiui/util/ArrayMap;->hl:[Ljava/lang/Object;

    aput-object p2, v2, v0

    .line 369
    return-object v1
.end method

.method public size()I
    .locals 1

    .prologue
    .line 565
    iget v0, p0, Lmiui/util/ArrayMap;->hm:I

    return v0
.end method

.method public toString()Ljava/lang/String;
    .locals 3

    .prologue
    .line 634
    invoke-virtual {p0}, Lmiui/util/ArrayMap;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 635
    const-string v0, "{}"

    .line 659
    :goto_0
    return-object v0

    .line 638
    :cond_0
    new-instance v1, Ljava/lang/StringBuilder;

    iget v0, p0, Lmiui/util/ArrayMap;->hm:I

    mul-int/lit8 v0, v0, 0x1c

    invoke-direct {v1, v0}, Ljava/lang/StringBuilder;-><init>(I)V

    .line 639
    const/16 v0, 0x7b

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    .line 640
    const/4 v0, 0x0

    :goto_1
    iget v2, p0, Lmiui/util/ArrayMap;->hm:I

    if-ge v0, v2, :cond_4

    .line 641
    if-lez v0, :cond_1

    .line 642
    const-string v2, ", "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 644
    :cond_1
    invoke-virtual {p0, v0}, Lmiui/util/ArrayMap;->keyAt(I)Ljava/lang/Object;

    move-result-object v2

    .line 645
    if-eq v2, p0, :cond_2

    .line 646
    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    .line 650
    :goto_2
    const/16 v2, 0x3d

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    .line 651
    invoke-virtual {p0, v0}, Lmiui/util/ArrayMap;->valueAt(I)Ljava/lang/Object;

    move-result-object v2

    .line 652
    if-eq v2, p0, :cond_3

    .line 653
    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    .line 640
    :goto_3
    add-int/lit8 v0, v0, 0x1

    goto :goto_1

    .line 648
    :cond_2
    const-string v2, "(this Map)"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    goto :goto_2

    .line 655
    :cond_3
    const-string v2, "(this Map)"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    goto :goto_3

    .line 658
    :cond_4
    const/16 v0, 0x7d

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    .line 659
    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    goto :goto_0
.end method

.method public valueAt(I)Ljava/lang/Object;
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(I)TV;"
        }
    .end annotation

    .prologue
    .line 356
    iget-object v0, p0, Lmiui/util/ArrayMap;->hl:[Ljava/lang/Object;

    shl-int/lit8 v1, p1, 0x1

    add-int/lit8 v1, v1, 0x1

    aget-object v0, v0, v1

    return-object v0
.end method

.method public values()Ljava/util/Collection;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/Collection",
            "<TV;>;"
        }
    .end annotation

    .prologue
    .line 799
    invoke-direct {p0}, Lmiui/util/ArrayMap;->aj()Lmiui/util/a;

    move-result-object v0

    invoke-virtual {v0}, Lmiui/util/a;->cs()Ljava/util/Collection;

    move-result-object v0

    return-object v0
.end method
