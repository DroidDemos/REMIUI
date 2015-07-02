.class Lmiui/util/Pools$d;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lmiui/util/Pools$b;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/util/Pools;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0xa
    name = "d"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "<T:",
        "Ljava/lang/Object;",
        ">",
        "Ljava/lang/Object;",
        "Lmiui/util/Pools$b",
        "<TT;>;"
    }
.end annotation


# instance fields
.field private volatile QF:[Ljava/lang/ref/SoftReference;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "[",
            "Ljava/lang/ref/SoftReference",
            "<TT;>;"
        }
    .end annotation
.end field

.field private final gp:Ljava/lang/Class;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/lang/Class",
            "<TT;>;"
        }
    .end annotation
.end field

.field private volatile hm:I

.field private volatile mIndex:I


# direct methods
.method constructor <init>(Ljava/lang/Class;I)V
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/Class",
            "<TT;>;I)V"
        }
    .end annotation

    .prologue
    .line 177
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 178
    iput-object p1, p0, Lmiui/util/Pools$d;->gp:Ljava/lang/Class;

    .line 179
    iput p2, p0, Lmiui/util/Pools$d;->hm:I

    .line 180
    new-array v0, p2, [Ljava/lang/ref/SoftReference;

    check-cast v0, [Ljava/lang/ref/SoftReference;

    .line 182
    iput-object v0, p0, Lmiui/util/Pools$d;->QF:[Ljava/lang/ref/SoftReference;

    .line 183
    const/4 v0, 0x0

    iput v0, p0, Lmiui/util/Pools$d;->mIndex:I

    .line 184
    return-void
.end method


# virtual methods
.method public ab()Ljava/lang/Class;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/lang/Class",
            "<TT;>;"
        }
    .end annotation

    .prologue
    .line 188
    iget-object v0, p0, Lmiui/util/Pools$d;->gp:Ljava/lang/Class;

    return-object v0
.end method

.method public declared-synchronized get()Ljava/lang/Object;
    .locals 5
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()TT;"
        }
    .end annotation

    .prologue
    const/4 v1, 0x0

    .line 219
    monitor-enter p0

    :try_start_0
    iget v0, p0, Lmiui/util/Pools$d;->mIndex:I

    .line 220
    iget-object v3, p0, Lmiui/util/Pools$d;->QF:[Ljava/lang/ref/SoftReference;

    .line 221
    :goto_0
    if-eqz v0, :cond_1

    .line 222
    add-int/lit8 v2, v0, -0x1

    .line 223
    aget-object v0, v3, v2

    if-eqz v0, :cond_2

    .line 224
    aget-object v0, v3, v2

    invoke-virtual {v0}, Ljava/lang/ref/SoftReference;->get()Ljava/lang/Object;

    move-result-object v0

    .line 225
    const/4 v4, 0x0

    aput-object v4, v3, v2

    .line 227
    if-eqz v0, :cond_0

    .line 228
    iput v2, p0, Lmiui/util/Pools$d;->mIndex:I
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 234
    :goto_1
    monitor-exit p0

    return-object v0

    :cond_0
    move v0, v2

    .line 231
    goto :goto_0

    :cond_1
    move-object v0, v1

    .line 234
    goto :goto_1

    .line 219
    :catchall_0
    move-exception v0

    monitor-exit p0

    throw v0

    :cond_2
    move v0, v2

    goto :goto_0
.end method

.method public getSize()I
    .locals 1

    .prologue
    .line 193
    iget v0, p0, Lmiui/util/Pools$d;->hm:I

    return v0
.end method

.method public declared-synchronized put(Ljava/lang/Object;)Z
    .locals 6
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(TT;)Z"
        }
    .end annotation

    .prologue
    const/4 v0, 0x1

    const/4 v1, 0x0

    .line 239
    monitor-enter p0

    :try_start_0
    iget v3, p0, Lmiui/util/Pools$d;->mIndex:I

    .line 240
    iget-object v4, p0, Lmiui/util/Pools$d;->QF:[Ljava/lang/ref/SoftReference;

    .line 242
    iget v2, p0, Lmiui/util/Pools$d;->hm:I

    if-lt v3, v2, :cond_3

    move v2, v1

    .line 243
    :goto_0
    if-ge v2, v3, :cond_2

    .line 244
    aget-object v5, v4, v2

    if-eqz v5, :cond_0

    aget-object v5, v4, v2

    invoke-virtual {v5}, Ljava/lang/ref/SoftReference;->get()Ljava/lang/Object;

    move-result-object v5

    if-nez v5, :cond_1

    .line 245
    :cond_0
    new-instance v1, Ljava/lang/ref/SoftReference;

    invoke-direct {v1, p1}, Ljava/lang/ref/SoftReference;-><init>(Ljava/lang/Object;)V

    aput-object v1, v4, v2
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 254
    :goto_1
    monitor-exit p0

    return v0

    .line 243
    :cond_1
    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    :cond_2
    move v0, v1

    .line 249
    goto :goto_1

    .line 252
    :cond_3
    :try_start_1
    new-instance v1, Ljava/lang/ref/SoftReference;

    invoke-direct {v1, p1}, Ljava/lang/ref/SoftReference;-><init>(Ljava/lang/Object;)V

    aput-object v1, v4, v3

    .line 253
    add-int/lit8 v1, v3, 0x1

    iput v1, p0, Lmiui/util/Pools$d;->mIndex:I
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    goto :goto_1

    .line 239
    :catchall_0
    move-exception v0

    monitor-exit p0

    throw v0
.end method

.method public declared-synchronized resize(I)V
    .locals 5

    .prologue
    .line 198
    monitor-enter p0

    :try_start_0
    iget v0, p0, Lmiui/util/Pools$d;->hm:I

    add-int/2addr v0, p1

    .line 199
    if-gtz v0, :cond_1

    .line 200
    invoke-static {}, Lmiui/util/Pools;->dR()Ljava/util/HashMap;

    move-result-object v1

    monitor-enter v1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_1

    .line 201
    :try_start_1
    invoke-static {}, Lmiui/util/Pools;->dR()Ljava/util/HashMap;

    move-result-object v0

    invoke-virtual {p0}, Lmiui/util/Pools$d;->ab()Ljava/lang/Class;

    move-result-object v2

    invoke-virtual {v0, v2}, Ljava/util/HashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    .line 202
    monitor-exit v1
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 215
    :cond_0
    :goto_0
    monitor-exit p0

    return-void

    .line 202
    :catchall_0
    move-exception v0

    :try_start_2
    monitor-exit v1
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    :try_start_3
    throw v0
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_1

    .line 198
    :catchall_1
    move-exception v0

    monitor-exit p0

    throw v0

    .line 206
    :cond_1
    :try_start_4
    iput v0, p0, Lmiui/util/Pools$d;->hm:I

    .line 207
    iget-object v1, p0, Lmiui/util/Pools$d;->QF:[Ljava/lang/ref/SoftReference;

    .line 208
    iget v2, p0, Lmiui/util/Pools$d;->mIndex:I

    .line 209
    array-length v3, v1

    if-le v0, v3, :cond_0

    .line 210
    new-array v0, v0, [Ljava/lang/ref/SoftReference;

    check-cast v0, [Ljava/lang/ref/SoftReference;

    .line 212
    const/4 v3, 0x0

    const/4 v4, 0x0

    invoke-static {v1, v3, v0, v4, v2}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    .line 213
    iput-object v0, p0, Lmiui/util/Pools$d;->QF:[Ljava/lang/ref/SoftReference;
    :try_end_4
    .catchall {:try_start_4 .. :try_end_4} :catchall_1

    goto :goto_0
.end method
