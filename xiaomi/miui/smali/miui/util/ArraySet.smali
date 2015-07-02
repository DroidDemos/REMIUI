.class public final Lmiui/util/ArraySet;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/util/Collection;
.implements Ljava/util/Set;


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "<E:",
        "Ljava/lang/Object;",
        ">",
        "Ljava/lang/Object;",
        "Ljava/util/Collection",
        "<TE;>;",
        "Ljava/util/Set",
        "<TE;>;"
    }
.end annotation


# static fields
.field private static final DEBUG:Z = false

.field private static final TAG:Ljava/lang/String; = "ArraySet"

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
            "<TE;TE;>;"
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

    iput-object v0, p0, Lmiui/util/ArraySet;->hk:[I

    .line 215
    sget-object v0, Lmiui/util/b;->ao:[Ljava/lang/Object;

    iput-object v0, p0, Lmiui/util/ArraySet;->hl:[Ljava/lang/Object;

    .line 216
    const/4 v0, 0x0

    iput v0, p0, Lmiui/util/ArraySet;->hm:I

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

    iput-object v0, p0, Lmiui/util/ArraySet;->hk:[I

    .line 225
    sget-object v0, Lmiui/util/b;->ao:[Ljava/lang/Object;

    iput-object v0, p0, Lmiui/util/ArraySet;->hl:[Ljava/lang/Object;

    .line 229
    :goto_0
    const/4 v0, 0x0

    iput v0, p0, Lmiui/util/ArraySet;->hm:I

    .line 230
    return-void

    .line 227
    :cond_0
    invoke-direct {p0, p1}, Lmiui/util/ArraySet;->y(I)V

    goto :goto_0
.end method

.method public constructor <init>(Lmiui/util/ArraySet;)V
    .locals 0

    .prologue
    .line 236
    invoke-direct {p0}, Lmiui/util/ArraySet;-><init>()V

    .line 237
    if-eqz p1, :cond_0

    .line 238
    invoke-virtual {p0, p1}, Lmiui/util/ArraySet;->addAll(Ljava/util/Collection;)Z

    .line 240
    :cond_0
    return-void
.end method

.method static synthetic a(Lmiui/util/ArraySet;)I
    .locals 1

    .prologue
    .line 35
    invoke-direct {p0}, Lmiui/util/ArraySet;->ai()I

    move-result v0

    return v0
.end method

.method static synthetic a(Lmiui/util/ArraySet;Ljava/lang/Object;I)I
    .locals 1

    .prologue
    .line 35
    invoke-direct {p0, p1, p2}, Lmiui/util/ArraySet;->indexOf(Ljava/lang/Object;I)I

    move-result v0

    return v0
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
    const-class v1, Lmiui/util/ArraySet;

    monitor-enter v1

    .line 180
    :try_start_0
    sget v0, Lmiui/util/ArraySet;->hj:I

    if-ge v0, v2, :cond_1

    .line 181
    const/4 v0, 0x0

    sget-object v2, Lmiui/util/ArraySet;->hi:[Ljava/lang/Object;

    aput-object v2, p1, v0

    .line 182
    const/4 v0, 0x1

    aput-object p0, p1, v0

    .line 183
    add-int/lit8 v0, p2, -0x1

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
    sput-object p1, Lmiui/util/ArraySet;->hi:[Ljava/lang/Object;

    .line 187
    sget v0, Lmiui/util/ArraySet;->hj:I

    add-int/lit8 v0, v0, 0x1

    sput v0, Lmiui/util/ArraySet;->hj:I

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
    const-class v1, Lmiui/util/ArraySet;

    monitor-enter v1

    .line 194
    :try_start_1
    sget v0, Lmiui/util/ArraySet;->hh:I

    if-ge v0, v2, :cond_5

    .line 195
    const/4 v0, 0x0

    sget-object v2, Lmiui/util/ArraySet;->hg:[Ljava/lang/Object;

    aput-object v2, p1, v0

    .line 196
    const/4 v0, 0x1

    aput-object p0, p1, v0

    .line 197
    add-int/lit8 v0, p2, -0x1

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
    sput-object p1, Lmiui/util/ArraySet;->hg:[Ljava/lang/Object;

    .line 201
    sget v0, Lmiui/util/ArraySet;->hh:I

    add-int/lit8 v0, v0, 0x1

    sput v0, Lmiui/util/ArraySet;->hh:I

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

.method private ai()I
    .locals 4

    .prologue
    .line 105
    iget v2, p0, Lmiui/util/ArraySet;->hm:I

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
    iget-object v0, p0, Lmiui/util/ArraySet;->hk:[I

    const/4 v1, 0x0

    invoke-static {v0, v2, v1}, Lmiui/util/b;->a([III)I

    move-result v0

    .line 115
    if-ltz v0, :cond_0

    .line 120
    iget-object v1, p0, Lmiui/util/ArraySet;->hl:[Ljava/lang/Object;

    aget-object v1, v1, v0

    if-eqz v1, :cond_0

    .line 126
    add-int/lit8 v1, v0, 0x1

    :goto_1
    if-ge v1, v2, :cond_3

    iget-object v3, p0, Lmiui/util/ArraySet;->hk:[I

    aget v3, v3, v1

    if-nez v3, :cond_3

    .line 127
    iget-object v3, p0, Lmiui/util/ArraySet;->hl:[Ljava/lang/Object;

    aget-object v3, v3, v1

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

    iget-object v2, p0, Lmiui/util/ArraySet;->hk:[I

    aget v2, v2, v0

    if-nez v2, :cond_4

    .line 132
    iget-object v2, p0, Lmiui/util/ArraySet;->hl:[Ljava/lang/Object;

    aget-object v2, v2, v0

    if-eqz v2, :cond_0

    .line 131
    add-int/lit8 v0, v0, -0x1

    goto :goto_2

    .line 139
    :cond_4
    xor-int/lit8 v0, v1, -0x1

    goto :goto_0
.end method

.method private aj()Lmiui/util/a;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Lmiui/util/a",
            "<TE;TE;>;"
        }
    .end annotation

    .prologue
    .line 563
    iget-object v0, p0, Lmiui/util/ArraySet;->hn:Lmiui/util/a;

    if-nez v0, :cond_0

    .line 564
    new-instance v0, Lmiui/util/ArraySet$1;

    invoke-direct {v0, p0}, Lmiui/util/ArraySet$1;-><init>(Lmiui/util/ArraySet;)V

    iput-object v0, p0, Lmiui/util/ArraySet;->hn:Lmiui/util/a;

    .line 611
    :cond_0
    iget-object v0, p0, Lmiui/util/ArraySet;->hn:Lmiui/util/a;

    return-object v0
.end method

.method private indexOf(Ljava/lang/Object;I)I
    .locals 4

    .prologue
    .line 67
    iget v2, p0, Lmiui/util/ArraySet;->hm:I

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
    iget-object v0, p0, Lmiui/util/ArraySet;->hk:[I

    invoke-static {v0, v2, p2}, Lmiui/util/b;->a([III)I

    move-result v0

    .line 77
    if-ltz v0, :cond_0

    .line 82
    iget-object v1, p0, Lmiui/util/ArraySet;->hl:[Ljava/lang/Object;

    aget-object v1, v1, v0

    invoke-virtual {p1, v1}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_0

    .line 88
    add-int/lit8 v1, v0, 0x1

    :goto_1
    if-ge v1, v2, :cond_3

    iget-object v3, p0, Lmiui/util/ArraySet;->hk:[I

    aget v3, v3, v1

    if-ne v3, p2, :cond_3

    .line 89
    iget-object v3, p0, Lmiui/util/ArraySet;->hl:[Ljava/lang/Object;

    aget-object v3, v3, v1

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

    iget-object v2, p0, Lmiui/util/ArraySet;->hk:[I

    aget v2, v2, v0

    if-ne v2, p2, :cond_4

    .line 94
    iget-object v2, p0, Lmiui/util/ArraySet;->hl:[Ljava/lang/Object;

    aget-object v2, v2, v0

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

.method private y(I)V
    .locals 5

    .prologue
    .line 143
    const/16 v0, 0x8

    if-ne p1, v0, :cond_2

    .line 144
    const-class v1, Lmiui/util/ArraySet;

    monitor-enter v1

    .line 145
    :try_start_0
    sget-object v0, Lmiui/util/ArraySet;->hi:[Ljava/lang/Object;

    if-eqz v0, :cond_0

    .line 146
    sget-object v2, Lmiui/util/ArraySet;->hi:[Ljava/lang/Object;

    .line 147
    iput-object v2, p0, Lmiui/util/ArraySet;->hl:[Ljava/lang/Object;

    .line 148
    const/4 v0, 0x0

    aget-object v0, v2, v0

    check-cast v0, [Ljava/lang/Object;

    check-cast v0, [Ljava/lang/Object;

    sput-object v0, Lmiui/util/ArraySet;->hi:[Ljava/lang/Object;

    .line 149
    const/4 v0, 0x1

    aget-object v0, v2, v0

    check-cast v0, [I

    check-cast v0, [I

    iput-object v0, p0, Lmiui/util/ArraySet;->hk:[I

    .line 150
    const/4 v0, 0x0

    const/4 v3, 0x1

    const/4 v4, 0x0

    aput-object v4, v2, v3

    aput-object v4, v2, v0

    .line 151
    sget v0, Lmiui/util/ArraySet;->hj:I

    add-int/lit8 v0, v0, -0x1

    sput v0, Lmiui/util/ArraySet;->hj:I

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

    iput-object v0, p0, Lmiui/util/ArraySet;->hk:[I

    .line 174
    new-array v0, p1, [Ljava/lang/Object;

    iput-object v0, p0, Lmiui/util/ArraySet;->hl:[Ljava/lang/Object;

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
    const-class v1, Lmiui/util/ArraySet;

    monitor-enter v1

    .line 159
    :try_start_2
    sget-object v0, Lmiui/util/ArraySet;->hg:[Ljava/lang/Object;

    if-eqz v0, :cond_3

    .line 160
    sget-object v2, Lmiui/util/ArraySet;->hg:[Ljava/lang/Object;

    .line 161
    iput-object v2, p0, Lmiui/util/ArraySet;->hl:[Ljava/lang/Object;

    .line 162
    const/4 v0, 0x0

    aget-object v0, v2, v0

    check-cast v0, [Ljava/lang/Object;

    check-cast v0, [Ljava/lang/Object;

    sput-object v0, Lmiui/util/ArraySet;->hg:[Ljava/lang/Object;

    .line 163
    const/4 v0, 0x1

    aget-object v0, v2, v0

    check-cast v0, [I

    check-cast v0, [I

    iput-object v0, p0, Lmiui/util/ArraySet;->hk:[I

    .line 164
    const/4 v0, 0x0

    const/4 v3, 0x1

    const/4 v4, 0x0

    aput-object v4, v2, v3

    aput-object v4, v2, v0

    .line 165
    sget v0, Lmiui/util/ArraySet;->hh:I

    add-int/lit8 v0, v0, -0x1

    sput v0, Lmiui/util/ArraySet;->hh:I

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
.method public add(Ljava/lang/Object;)Z
    .locals 7
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(TE;)Z"
        }
    .end annotation

    .prologue
    const/16 v0, 0x8

    const/4 v1, 0x4

    const/4 v2, 0x0

    .line 314
    if-nez p1, :cond_0

    .line 316
    invoke-direct {p0}, Lmiui/util/ArraySet;->ai()I

    move-result v3

    move v4, v2

    .line 321
    :goto_0
    if-ltz v3, :cond_1

    move v0, v2

    .line 355
    :goto_1
    return v0

    .line 318
    :cond_0
    invoke-virtual {p1}, Ljava/lang/Object;->hashCode()I

    move-result v4

    .line 319
    invoke-direct {p0, p1, v4}, Lmiui/util/ArraySet;->indexOf(Ljava/lang/Object;I)I

    move-result v3

    goto :goto_0

    .line 325
    :cond_1
    xor-int/lit8 v3, v3, -0x1

    .line 326
    iget v5, p0, Lmiui/util/ArraySet;->hm:I

    iget-object v6, p0, Lmiui/util/ArraySet;->hk:[I

    array-length v6, v6

    if-lt v5, v6, :cond_4

    .line 327
    iget v5, p0, Lmiui/util/ArraySet;->hm:I

    if-lt v5, v0, :cond_6

    iget v0, p0, Lmiui/util/ArraySet;->hm:I

    iget v1, p0, Lmiui/util/ArraySet;->hm:I

    shr-int/lit8 v1, v1, 0x1

    add-int/2addr v0, v1

    .line 332
    :cond_2
    :goto_2
    iget-object v1, p0, Lmiui/util/ArraySet;->hk:[I

    .line 333
    iget-object v5, p0, Lmiui/util/ArraySet;->hl:[Ljava/lang/Object;

    .line 334
    invoke-direct {p0, v0}, Lmiui/util/ArraySet;->y(I)V

    .line 336
    iget-object v0, p0, Lmiui/util/ArraySet;->hk:[I

    array-length v0, v0

    if-lez v0, :cond_3

    .line 338
    iget-object v0, p0, Lmiui/util/ArraySet;->hk:[I

    array-length v6, v1

    invoke-static {v1, v2, v0, v2, v6}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    .line 339
    iget-object v0, p0, Lmiui/util/ArraySet;->hl:[Ljava/lang/Object;

    array-length v6, v5

    invoke-static {v5, v2, v0, v2, v6}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    .line 342
    :cond_3
    iget v0, p0, Lmiui/util/ArraySet;->hm:I

    invoke-static {v1, v5, v0}, Lmiui/util/ArraySet;->a([I[Ljava/lang/Object;I)V

    .line 345
    :cond_4
    iget v0, p0, Lmiui/util/ArraySet;->hm:I

    if-ge v3, v0, :cond_5

    .line 348
    iget-object v0, p0, Lmiui/util/ArraySet;->hk:[I

    iget-object v1, p0, Lmiui/util/ArraySet;->hk:[I

    add-int/lit8 v2, v3, 0x1

    iget v5, p0, Lmiui/util/ArraySet;->hm:I

    sub-int/2addr v5, v3

    invoke-static {v0, v3, v1, v2, v5}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    .line 349
    iget-object v0, p0, Lmiui/util/ArraySet;->hl:[Ljava/lang/Object;

    iget-object v1, p0, Lmiui/util/ArraySet;->hl:[Ljava/lang/Object;

    add-int/lit8 v2, v3, 0x1

    iget v5, p0, Lmiui/util/ArraySet;->hm:I

    sub-int/2addr v5, v3

    invoke-static {v0, v3, v1, v2, v5}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    .line 352
    :cond_5
    iget-object v0, p0, Lmiui/util/ArraySet;->hk:[I

    aput v4, v0, v3

    .line 353
    iget-object v0, p0, Lmiui/util/ArraySet;->hl:[Ljava/lang/Object;

    aput-object p1, v0, v3

    .line 354
    iget v0, p0, Lmiui/util/ArraySet;->hm:I

    add-int/lit8 v0, v0, 0x1

    iput v0, p0, Lmiui/util/ArraySet;->hm:I

    .line 355
    const/4 v0, 0x1

    goto :goto_1

    .line 327
    :cond_6
    iget v5, p0, Lmiui/util/ArraySet;->hm:I

    if-ge v5, v1, :cond_2

    move v0, v1

    goto :goto_2
.end method

.method public addAll(Ljava/util/Collection;)Z
    .locals 3
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/Collection",
            "<+TE;>;)Z"
        }
    .end annotation

    .prologue
    .line 632
    iget v0, p0, Lmiui/util/ArraySet;->hm:I

    invoke-interface {p1}, Ljava/util/Collection;->size()I

    move-result v1

    add-int/2addr v0, v1

    invoke-virtual {p0, v0}, Lmiui/util/ArraySet;->ensureCapacity(I)V

    .line 633
    const/4 v0, 0x0

    .line 634
    invoke-interface {p1}, Ljava/util/Collection;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :goto_0
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_0

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    .line 635
    invoke-virtual {p0, v2}, Lmiui/util/ArraySet;->add(Ljava/lang/Object;)Z

    move-result v2

    or-int/2addr v0, v2

    goto :goto_0

    .line 637
    :cond_0
    return v0
.end method

.method public clear()V
    .locals 3

    .prologue
    .line 248
    iget v0, p0, Lmiui/util/ArraySet;->hm:I

    if-eqz v0, :cond_0

    .line 249
    iget-object v0, p0, Lmiui/util/ArraySet;->hk:[I

    iget-object v1, p0, Lmiui/util/ArraySet;->hl:[Ljava/lang/Object;

    iget v2, p0, Lmiui/util/ArraySet;->hm:I

    invoke-static {v0, v1, v2}, Lmiui/util/ArraySet;->a([I[Ljava/lang/Object;I)V

    .line 250
    sget-object v0, Lmiui/util/b;->al:[I

    iput-object v0, p0, Lmiui/util/ArraySet;->hk:[I

    .line 251
    sget-object v0, Lmiui/util/b;->ao:[Ljava/lang/Object;

    iput-object v0, p0, Lmiui/util/ArraySet;->hl:[Ljava/lang/Object;

    .line 252
    const/4 v0, 0x0

    iput v0, p0, Lmiui/util/ArraySet;->hm:I

    .line 254
    :cond_0
    return-void
.end method

.method public contains(Ljava/lang/Object;)Z
    .locals 3

    .prologue
    const/4 v0, 0x1

    const/4 v1, 0x0

    .line 281
    if-nez p1, :cond_2

    invoke-direct {p0}, Lmiui/util/ArraySet;->ai()I

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

    invoke-direct {p0, p1, v2}, Lmiui/util/ArraySet;->indexOf(Ljava/lang/Object;I)I

    move-result v2

    if-gez v2, :cond_0

    move v0, v1

    goto :goto_0
.end method

.method public containsAll(Ljava/util/Collection;)Z
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/Collection",
            "<*>;)Z"
        }
    .end annotation

    .prologue
    .line 621
    invoke-interface {p1}, Ljava/util/Collection;->iterator()Ljava/util/Iterator;

    move-result-object v0

    .line 622
    :cond_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_1

    .line 623
    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    invoke-virtual {p0, v1}, Lmiui/util/ArraySet;->contains(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_0

    .line 624
    const/4 v0, 0x0

    .line 627
    :goto_0
    return v0

    :cond_1
    const/4 v0, 0x1

    goto :goto_0
.end method

.method public ensureCapacity(I)V
    .locals 5

    .prologue
    const/4 v4, 0x0

    .line 261
    iget-object v0, p0, Lmiui/util/ArraySet;->hk:[I

    array-length v0, v0

    if-ge v0, p1, :cond_1

    .line 262
    iget-object v0, p0, Lmiui/util/ArraySet;->hk:[I

    .line 263
    iget-object v1, p0, Lmiui/util/ArraySet;->hl:[Ljava/lang/Object;

    .line 264
    invoke-direct {p0, p1}, Lmiui/util/ArraySet;->y(I)V

    .line 265
    iget v2, p0, Lmiui/util/ArraySet;->hm:I

    if-lez v2, :cond_0

    .line 266
    iget-object v2, p0, Lmiui/util/ArraySet;->hk:[I

    iget v3, p0, Lmiui/util/ArraySet;->hm:I

    invoke-static {v0, v4, v2, v4, v3}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    .line 267
    iget-object v2, p0, Lmiui/util/ArraySet;->hl:[Ljava/lang/Object;

    iget v3, p0, Lmiui/util/ArraySet;->hm:I

    invoke-static {v1, v4, v2, v4, v3}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    .line 269
    :cond_0
    iget v2, p0, Lmiui/util/ArraySet;->hm:I

    invoke-static {v0, v1, v2}, Lmiui/util/ArraySet;->a([I[Ljava/lang/Object;I)V

    .line 271
    :cond_1
    return-void
.end method

.method public equals(Ljava/lang/Object;)Z
    .locals 4

    .prologue
    const/4 v0, 0x1

    const/4 v1, 0x0

    .line 488
    if-ne p0, p1, :cond_1

    .line 511
    :cond_0
    :goto_0
    return v0

    .line 491
    :cond_1
    instance-of v2, p1, Ljava/util/Set;

    if-eqz v2, :cond_4

    .line 492
    check-cast p1, Ljava/util/Set;

    .line 493
    invoke-virtual {p0}, Lmiui/util/ArraySet;->size()I

    move-result v2

    invoke-interface {p1}, Ljava/util/Set;->size()I

    move-result v3

    if-eq v2, v3, :cond_2

    move v0, v1

    .line 494
    goto :goto_0

    :cond_2
    move v2, v1

    .line 498
    :goto_1
    :try_start_0
    iget v3, p0, Lmiui/util/ArraySet;->hm:I

    if-ge v2, v3, :cond_0

    .line 499
    invoke-virtual {p0, v2}, Lmiui/util/ArraySet;->valueAt(I)Ljava/lang/Object;

    move-result-object v3

    .line 500
    invoke-interface {p1, v3}, Ljava/util/Set;->contains(Ljava/lang/Object;)Z
    :try_end_0
    .catch Ljava/lang/NullPointerException; {:try_start_0 .. :try_end_0} :catch_0
    .catch Ljava/lang/ClassCastException; {:try_start_0 .. :try_end_0} :catch_1

    move-result v3

    if-nez v3, :cond_3

    move v0, v1

    .line 501
    goto :goto_0

    .line 498
    :cond_3
    add-int/lit8 v2, v2, 0x1

    goto :goto_1

    .line 504
    :catch_0
    move-exception v0

    move v0, v1

    .line 505
    goto :goto_0

    .line 506
    :catch_1
    move-exception v0

    move v0, v1

    .line 507
    goto :goto_0

    :cond_4
    move v0, v1

    .line 511
    goto :goto_0
.end method

.method public hashCode()I
    .locals 5

    .prologue
    const/4 v0, 0x0

    .line 519
    iget-object v2, p0, Lmiui/util/ArraySet;->hk:[I

    .line 521
    iget v3, p0, Lmiui/util/ArraySet;->hm:I

    move v1, v0

    :goto_0
    if-ge v0, v3, :cond_0

    .line 522
    aget v4, v2, v0

    add-int/2addr v1, v4

    .line 521
    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    .line 524
    :cond_0
    return v1
.end method

.method public isEmpty()Z
    .locals 1

    .prologue
    .line 298
    iget v0, p0, Lmiui/util/ArraySet;->hm:I

    if-gtz v0, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public iterator()Ljava/util/Iterator;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/Iterator",
            "<TE;>;"
        }
    .end annotation

    .prologue
    .line 616
    invoke-direct {p0}, Lmiui/util/ArraySet;->aj()Lmiui/util/a;

    move-result-object v0

    invoke-virtual {v0}, Lmiui/util/a;->cr()Ljava/util/Set;

    move-result-object v0

    invoke-interface {v0}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v0

    return-object v0
.end method

.method public putAll(Lmiui/util/ArraySet;)V
    .locals 4
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lmiui/util/ArraySet",
            "<+TE;>;)V"
        }
    .end annotation

    .prologue
    const/4 v0, 0x0

    .line 363
    iget v1, p1, Lmiui/util/ArraySet;->hm:I

    .line 364
    iget v2, p0, Lmiui/util/ArraySet;->hm:I

    add-int/2addr v2, v1

    invoke-virtual {p0, v2}, Lmiui/util/ArraySet;->ensureCapacity(I)V

    .line 365
    iget v2, p0, Lmiui/util/ArraySet;->hm:I

    if-nez v2, :cond_1

    .line 366
    if-lez v1, :cond_0

    .line 367
    iget-object v2, p1, Lmiui/util/ArraySet;->hk:[I

    iget-object v3, p0, Lmiui/util/ArraySet;->hk:[I

    invoke-static {v2, v0, v3, v0, v1}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    .line 368
    iget-object v2, p1, Lmiui/util/ArraySet;->hl:[Ljava/lang/Object;

    iget-object v3, p0, Lmiui/util/ArraySet;->hl:[Ljava/lang/Object;

    invoke-static {v2, v0, v3, v0, v1}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    .line 369
    iput v1, p0, Lmiui/util/ArraySet;->hm:I

    .line 376
    :cond_0
    return-void

    .line 372
    :cond_1
    :goto_0
    if-ge v0, v1, :cond_0

    .line 373
    invoke-virtual {p1, v0}, Lmiui/util/ArraySet;->valueAt(I)Ljava/lang/Object;

    move-result-object v2

    invoke-virtual {p0, v2}, Lmiui/util/ArraySet;->add(Ljava/lang/Object;)Z

    .line 372
    add-int/lit8 v0, v0, 0x1

    goto :goto_0
.end method

.method public remove(Ljava/lang/Object;)Z
    .locals 1

    .prologue
    .line 386
    if-nez p1, :cond_0

    invoke-direct {p0}, Lmiui/util/ArraySet;->ai()I

    move-result v0

    .line 387
    :goto_0
    if-ltz v0, :cond_1

    .line 388
    invoke-virtual {p0, v0}, Lmiui/util/ArraySet;->removeAt(I)Ljava/lang/Object;

    .line 389
    const/4 v0, 0x1

    .line 391
    :goto_1
    return v0

    .line 386
    :cond_0
    invoke-virtual {p1}, Ljava/lang/Object;->hashCode()I

    move-result v0

    invoke-direct {p0, p1, v0}, Lmiui/util/ArraySet;->indexOf(Ljava/lang/Object;I)I

    move-result v0

    goto :goto_0

    .line 391
    :cond_1
    const/4 v0, 0x0

    goto :goto_1
.end method

.method public removeAll(Ljava/util/Collection;)Z
    .locals 3
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/Collection",
            "<*>;)Z"
        }
    .end annotation

    .prologue
    .line 642
    const/4 v0, 0x0

    .line 643
    invoke-interface {p1}, Ljava/util/Collection;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :goto_0
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_0

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    .line 644
    invoke-virtual {p0, v2}, Lmiui/util/ArraySet;->remove(Ljava/lang/Object;)Z

    move-result v2

    or-int/2addr v0, v2

    goto :goto_0

    .line 646
    :cond_0
    return v0
.end method

.method public removeAt(I)Ljava/lang/Object;
    .locals 6
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(I)TE;"
        }
    .end annotation

    .prologue
    const/16 v0, 0x8

    const/4 v4, 0x0

    .line 400
    iget-object v1, p0, Lmiui/util/ArraySet;->hl:[Ljava/lang/Object;

    aget-object v1, v1, p1

    .line 401
    iget v2, p0, Lmiui/util/ArraySet;->hm:I

    const/4 v3, 0x1

    if-gt v2, v3, :cond_1

    .line 404
    iget-object v0, p0, Lmiui/util/ArraySet;->hk:[I

    iget-object v2, p0, Lmiui/util/ArraySet;->hl:[Ljava/lang/Object;

    iget v3, p0, Lmiui/util/ArraySet;->hm:I

    invoke-static {v0, v2, v3}, Lmiui/util/ArraySet;->a([I[Ljava/lang/Object;I)V

    .line 405
    sget-object v0, Lmiui/util/b;->al:[I

    iput-object v0, p0, Lmiui/util/ArraySet;->hk:[I

    .line 406
    sget-object v0, Lmiui/util/b;->ao:[Ljava/lang/Object;

    iput-object v0, p0, Lmiui/util/ArraySet;->hl:[Ljava/lang/Object;

    .line 407
    iput v4, p0, Lmiui/util/ArraySet;->hm:I

    .line 445
    :cond_0
    :goto_0
    return-object v1

    .line 409
    :cond_1
    iget-object v2, p0, Lmiui/util/ArraySet;->hk:[I

    array-length v2, v2

    if-le v2, v0, :cond_4

    iget v2, p0, Lmiui/util/ArraySet;->hm:I

    iget-object v3, p0, Lmiui/util/ArraySet;->hk:[I

    array-length v3, v3

    div-int/lit8 v3, v3, 0x3

    if-ge v2, v3, :cond_4

    .line 413
    iget v2, p0, Lmiui/util/ArraySet;->hm:I

    if-le v2, v0, :cond_2

    iget v0, p0, Lmiui/util/ArraySet;->hm:I

    iget v2, p0, Lmiui/util/ArraySet;->hm:I

    shr-int/lit8 v2, v2, 0x1

    add-int/2addr v0, v2

    .line 417
    :cond_2
    iget-object v2, p0, Lmiui/util/ArraySet;->hk:[I

    .line 418
    iget-object v3, p0, Lmiui/util/ArraySet;->hl:[Ljava/lang/Object;

    .line 419
    invoke-direct {p0, v0}, Lmiui/util/ArraySet;->y(I)V

    .line 421
    iget v0, p0, Lmiui/util/ArraySet;->hm:I

    add-int/lit8 v0, v0, -0x1

    iput v0, p0, Lmiui/util/ArraySet;->hm:I

    .line 422
    if-lez p1, :cond_3

    .line 425
    iget-object v0, p0, Lmiui/util/ArraySet;->hk:[I

    invoke-static {v2, v4, v0, v4, p1}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    .line 426
    iget-object v0, p0, Lmiui/util/ArraySet;->hl:[Ljava/lang/Object;

    invoke-static {v3, v4, v0, v4, p1}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    .line 428
    :cond_3
    iget v0, p0, Lmiui/util/ArraySet;->hm:I

    if-ge p1, v0, :cond_0

    .line 431
    add-int/lit8 v0, p1, 0x1

    iget-object v4, p0, Lmiui/util/ArraySet;->hk:[I

    iget v5, p0, Lmiui/util/ArraySet;->hm:I

    sub-int/2addr v5, p1

    invoke-static {v2, v0, v4, p1, v5}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    .line 432
    add-int/lit8 v0, p1, 0x1

    iget-object v2, p0, Lmiui/util/ArraySet;->hl:[Ljava/lang/Object;

    iget v4, p0, Lmiui/util/ArraySet;->hm:I

    sub-int/2addr v4, p1

    invoke-static {v3, v0, v2, p1, v4}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    goto :goto_0

    .line 435
    :cond_4
    iget v0, p0, Lmiui/util/ArraySet;->hm:I

    add-int/lit8 v0, v0, -0x1

    iput v0, p0, Lmiui/util/ArraySet;->hm:I

    .line 436
    iget v0, p0, Lmiui/util/ArraySet;->hm:I

    if-ge p1, v0, :cond_5

    .line 439
    iget-object v0, p0, Lmiui/util/ArraySet;->hk:[I

    add-int/lit8 v2, p1, 0x1

    iget-object v3, p0, Lmiui/util/ArraySet;->hk:[I

    iget v4, p0, Lmiui/util/ArraySet;->hm:I

    sub-int/2addr v4, p1

    invoke-static {v0, v2, v3, p1, v4}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    .line 440
    iget-object v0, p0, Lmiui/util/ArraySet;->hl:[Ljava/lang/Object;

    add-int/lit8 v2, p1, 0x1

    iget-object v3, p0, Lmiui/util/ArraySet;->hl:[Ljava/lang/Object;

    iget v4, p0, Lmiui/util/ArraySet;->hm:I

    sub-int/2addr v4, p1

    invoke-static {v0, v2, v3, p1, v4}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    .line 442
    :cond_5
    iget-object v0, p0, Lmiui/util/ArraySet;->hl:[Ljava/lang/Object;

    iget v2, p0, Lmiui/util/ArraySet;->hm:I

    const/4 v3, 0x0

    aput-object v3, v0, v2

    goto :goto_0
.end method

.method public retainAll(Ljava/util/Collection;)Z
    .locals 4
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/Collection",
            "<*>;)Z"
        }
    .end annotation

    .prologue
    .line 651
    const/4 v1, 0x0

    .line 652
    iget v0, p0, Lmiui/util/ArraySet;->hm:I

    add-int/lit8 v0, v0, -0x1

    move v3, v0

    move v0, v1

    move v1, v3

    :goto_0
    if-ltz v1, :cond_1

    .line 653
    iget-object v2, p0, Lmiui/util/ArraySet;->hl:[Ljava/lang/Object;

    aget-object v2, v2, v1

    invoke-interface {p1, v2}, Ljava/util/Collection;->contains(Ljava/lang/Object;)Z

    move-result v2

    if-nez v2, :cond_0

    .line 654
    invoke-virtual {p0, v1}, Lmiui/util/ArraySet;->removeAt(I)Ljava/lang/Object;

    .line 655
    const/4 v0, 0x1

    .line 652
    :cond_0
    add-int/lit8 v1, v1, -0x1

    goto :goto_0

    .line 658
    :cond_1
    return v0
.end method

.method public size()I
    .locals 1

    .prologue
    .line 453
    iget v0, p0, Lmiui/util/ArraySet;->hm:I

    return v0
.end method

.method public toArray()[Ljava/lang/Object;
    .locals 4

    .prologue
    const/4 v3, 0x0

    .line 458
    iget v0, p0, Lmiui/util/ArraySet;->hm:I

    new-array v0, v0, [Ljava/lang/Object;

    .line 459
    iget-object v1, p0, Lmiui/util/ArraySet;->hl:[Ljava/lang/Object;

    iget v2, p0, Lmiui/util/ArraySet;->hm:I

    invoke-static {v1, v3, v0, v3, v2}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    .line 460
    return-object v0
.end method

.method public toArray([Ljava/lang/Object;)[Ljava/lang/Object;
    .locals 4
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "<T:",
            "Ljava/lang/Object;",
            ">([TT;)[TT;"
        }
    .end annotation

    .prologue
    const/4 v3, 0x0

    .line 465
    array-length v0, p1

    iget v1, p0, Lmiui/util/ArraySet;->hm:I

    if-ge v0, v1, :cond_1

    .line 466
    invoke-virtual {p1}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/Class;->getComponentType()Ljava/lang/Class;

    move-result-object v0

    iget v1, p0, Lmiui/util/ArraySet;->hm:I

    invoke-static {v0, v1}, Ljava/lang/reflect/Array;->newInstance(Ljava/lang/Class;I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Ljava/lang/Object;

    check-cast v0, [Ljava/lang/Object;

    .line 470
    :goto_0
    iget-object v1, p0, Lmiui/util/ArraySet;->hl:[Ljava/lang/Object;

    iget v2, p0, Lmiui/util/ArraySet;->hm:I

    invoke-static {v1, v3, v0, v3, v2}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    .line 471
    array-length v1, v0

    iget v2, p0, Lmiui/util/ArraySet;->hm:I

    if-le v1, v2, :cond_0

    .line 472
    iget v1, p0, Lmiui/util/ArraySet;->hm:I

    const/4 v2, 0x0

    aput-object v2, v0, v1

    .line 474
    :cond_0
    return-object v0

    :cond_1
    move-object v0, p1

    goto :goto_0
.end method

.method public toString()Ljava/lang/String;
    .locals 3

    .prologue
    .line 536
    invoke-virtual {p0}, Lmiui/util/ArraySet;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 537
    const-string v0, "{}"

    .line 554
    :goto_0
    return-object v0

    .line 540
    :cond_0
    new-instance v1, Ljava/lang/StringBuilder;

    iget v0, p0, Lmiui/util/ArraySet;->hm:I

    mul-int/lit8 v0, v0, 0xe

    invoke-direct {v1, v0}, Ljava/lang/StringBuilder;-><init>(I)V

    .line 541
    const/16 v0, 0x7b

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    .line 542
    const/4 v0, 0x0

    :goto_1
    iget v2, p0, Lmiui/util/ArraySet;->hm:I

    if-ge v0, v2, :cond_3

    .line 543
    if-lez v0, :cond_1

    .line 544
    const-string v2, ", "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 546
    :cond_1
    invoke-virtual {p0, v0}, Lmiui/util/ArraySet;->valueAt(I)Ljava/lang/Object;

    move-result-object v2

    .line 547
    if-eq v2, p0, :cond_2

    .line 548
    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    .line 542
    :goto_2
    add-int/lit8 v0, v0, 0x1

    goto :goto_1

    .line 550
    :cond_2
    const-string v2, "(this Set)"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    goto :goto_2

    .line 553
    :cond_3
    const/16 v0, 0x7d

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    .line 554
    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    goto :goto_0
.end method

.method public valueAt(I)Ljava/lang/Object;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(I)TE;"
        }
    .end annotation

    .prologue
    .line 290
    iget-object v0, p0, Lmiui/util/ArraySet;->hl:[Ljava/lang/Object;

    aget-object v0, v0, p1

    return-object v0
.end method
