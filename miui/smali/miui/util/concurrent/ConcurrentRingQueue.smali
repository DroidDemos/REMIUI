.class public Lmiui/util/concurrent/ConcurrentRingQueue;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lmiui/util/concurrent/Queue;


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lmiui/util/concurrent/ConcurrentRingQueue$1;,
        Lmiui/util/concurrent/ConcurrentRingQueue$a;
    }
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "<T:",
        "Ljava/lang/Object;",
        ">",
        "Ljava/lang/Object;",
        "Lmiui/util/concurrent/Queue",
        "<TT;>;"
    }
.end annotation


# instance fields
.field private yo:I

.field private final yp:Z

.field private final yq:Z

.field private final yr:Ljava/util/concurrent/atomic/AtomicInteger;

.field private volatile ys:Lmiui/util/concurrent/ConcurrentRingQueue$a;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lmiui/util/concurrent/ConcurrentRingQueue$a",
            "<TT;>;"
        }
    .end annotation
.end field

.field private final yt:Ljava/util/concurrent/atomic/AtomicInteger;

.field private volatile yu:Lmiui/util/concurrent/ConcurrentRingQueue$a;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lmiui/util/concurrent/ConcurrentRingQueue$a",
            "<TT;>;"
        }
    .end annotation
.end field

.field private volatile yv:I


# direct methods
.method public constructor <init>(IZZ)V
    .locals 4

    .prologue
    const/4 v3, 0x0

    const/4 v0, 0x0

    .line 44
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 45
    iput p1, p0, Lmiui/util/concurrent/ConcurrentRingQueue;->yo:I

    .line 46
    iput-boolean p2, p0, Lmiui/util/concurrent/ConcurrentRingQueue;->yp:Z

    .line 47
    iput-boolean p3, p0, Lmiui/util/concurrent/ConcurrentRingQueue;->yq:Z

    .line 48
    new-instance v1, Ljava/util/concurrent/atomic/AtomicInteger;

    invoke-direct {v1, v0}, Ljava/util/concurrent/atomic/AtomicInteger;-><init>(I)V

    iput-object v1, p0, Lmiui/util/concurrent/ConcurrentRingQueue;->yr:Ljava/util/concurrent/atomic/AtomicInteger;

    .line 49
    new-instance v1, Ljava/util/concurrent/atomic/AtomicInteger;

    invoke-direct {v1, v0}, Ljava/util/concurrent/atomic/AtomicInteger;-><init>(I)V

    iput-object v1, p0, Lmiui/util/concurrent/ConcurrentRingQueue;->yt:Ljava/util/concurrent/atomic/AtomicInteger;

    .line 51
    new-instance v1, Lmiui/util/concurrent/ConcurrentRingQueue$a;

    invoke-direct {v1, v3}, Lmiui/util/concurrent/ConcurrentRingQueue$a;-><init>(Lmiui/util/concurrent/ConcurrentRingQueue$1;)V

    iput-object v1, p0, Lmiui/util/concurrent/ConcurrentRingQueue;->ys:Lmiui/util/concurrent/ConcurrentRingQueue$a;

    .line 52
    iget-object v1, p0, Lmiui/util/concurrent/ConcurrentRingQueue;->ys:Lmiui/util/concurrent/ConcurrentRingQueue$a;

    iput-object v1, p0, Lmiui/util/concurrent/ConcurrentRingQueue;->yu:Lmiui/util/concurrent/ConcurrentRingQueue$a;

    .line 53
    iget-object v1, p0, Lmiui/util/concurrent/ConcurrentRingQueue;->ys:Lmiui/util/concurrent/ConcurrentRingQueue$a;

    .line 54
    :goto_0
    if-ge v0, p1, :cond_0

    .line 55
    new-instance v2, Lmiui/util/concurrent/ConcurrentRingQueue$a;

    invoke-direct {v2, v3}, Lmiui/util/concurrent/ConcurrentRingQueue$a;-><init>(Lmiui/util/concurrent/ConcurrentRingQueue$1;)V

    iput-object v2, v1, Lmiui/util/concurrent/ConcurrentRingQueue$a;->dD:Lmiui/util/concurrent/ConcurrentRingQueue$a;

    .line 56
    iget-object v1, v1, Lmiui/util/concurrent/ConcurrentRingQueue$a;->dD:Lmiui/util/concurrent/ConcurrentRingQueue$a;

    .line 54
    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    .line 58
    :cond_0
    iget-object v0, p0, Lmiui/util/concurrent/ConcurrentRingQueue;->ys:Lmiui/util/concurrent/ConcurrentRingQueue$a;

    iput-object v0, v1, Lmiui/util/concurrent/ConcurrentRingQueue$a;->dD:Lmiui/util/concurrent/ConcurrentRingQueue$a;

    .line 59
    return-void
.end method


# virtual methods
.method public clear()I
    .locals 4

    .prologue
    const/4 v2, 0x0

    .line 159
    iget-object v0, p0, Lmiui/util/concurrent/ConcurrentRingQueue;->yr:Ljava/util/concurrent/atomic/AtomicInteger;

    invoke-virtual {v0}, Ljava/util/concurrent/atomic/AtomicInteger;->get()I

    move-result v0

    :goto_0
    if-nez v0, :cond_0

    iget-object v0, p0, Lmiui/util/concurrent/ConcurrentRingQueue;->yr:Ljava/util/concurrent/atomic/AtomicInteger;

    const/4 v1, -0x1

    invoke-virtual {v0, v2, v1}, Ljava/util/concurrent/atomic/AtomicInteger;->compareAndSet(II)Z

    move-result v0

    if-nez v0, :cond_1

    .line 160
    :cond_0
    invoke-static {}, Ljava/lang/Thread;->yield()V

    .line 159
    iget-object v0, p0, Lmiui/util/concurrent/ConcurrentRingQueue;->yr:Ljava/util/concurrent/atomic/AtomicInteger;

    invoke-virtual {v0}, Ljava/util/concurrent/atomic/AtomicInteger;->get()I

    move-result v0

    goto :goto_0

    .line 165
    :cond_1
    iget-object v0, p0, Lmiui/util/concurrent/ConcurrentRingQueue;->ys:Lmiui/util/concurrent/ConcurrentRingQueue$a;

    move v1, v2

    :goto_1
    iget-object v3, p0, Lmiui/util/concurrent/ConcurrentRingQueue;->yu:Lmiui/util/concurrent/ConcurrentRingQueue$a;

    if-eq v0, v3, :cond_2

    .line 166
    const/4 v3, 0x0

    iput-object v3, v0, Lmiui/util/concurrent/ConcurrentRingQueue$a;->dC:Ljava/lang/Object;

    .line 167
    add-int/lit8 v1, v1, 0x1

    .line 165
    iget-object v0, v0, Lmiui/util/concurrent/ConcurrentRingQueue$a;->dD:Lmiui/util/concurrent/ConcurrentRingQueue$a;

    goto :goto_1

    .line 170
    :cond_2
    iput-object v0, p0, Lmiui/util/concurrent/ConcurrentRingQueue;->ys:Lmiui/util/concurrent/ConcurrentRingQueue$a;

    .line 172
    iget-object v0, p0, Lmiui/util/concurrent/ConcurrentRingQueue;->yr:Ljava/util/concurrent/atomic/AtomicInteger;

    invoke-virtual {v0, v2}, Ljava/util/concurrent/atomic/AtomicInteger;->set(I)V

    .line 173
    return v1
.end method

.method public decreaseCapacity(I)V
    .locals 3

    .prologue
    const/4 v2, 0x0

    .line 215
    iget-boolean v0, p0, Lmiui/util/concurrent/ConcurrentRingQueue;->yq:Z

    if-eqz v0, :cond_0

    if-gtz p1, :cond_1

    .line 227
    :cond_0
    :goto_0
    return-void

    .line 219
    :cond_1
    iget-object v0, p0, Lmiui/util/concurrent/ConcurrentRingQueue;->yt:Ljava/util/concurrent/atomic/AtomicInteger;

    invoke-virtual {v0}, Ljava/util/concurrent/atomic/AtomicInteger;->get()I

    move-result v0

    :goto_1
    if-nez v0, :cond_2

    iget-object v0, p0, Lmiui/util/concurrent/ConcurrentRingQueue;->yt:Ljava/util/concurrent/atomic/AtomicInteger;

    const/4 v1, -0x1

    invoke-virtual {v0, v2, v1}, Ljava/util/concurrent/atomic/AtomicInteger;->compareAndSet(II)Z

    move-result v0

    if-nez v0, :cond_3

    .line 220
    :cond_2
    invoke-static {}, Ljava/lang/Thread;->yield()V

    .line 219
    iget-object v0, p0, Lmiui/util/concurrent/ConcurrentRingQueue;->yt:Ljava/util/concurrent/atomic/AtomicInteger;

    invoke-virtual {v0}, Ljava/util/concurrent/atomic/AtomicInteger;->get()I

    move-result v0

    goto :goto_1

    .line 223
    :cond_3
    iget v0, p0, Lmiui/util/concurrent/ConcurrentRingQueue;->yo:I

    sub-int/2addr v0, p1

    iput v0, p0, Lmiui/util/concurrent/ConcurrentRingQueue;->yo:I

    .line 224
    iput p1, p0, Lmiui/util/concurrent/ConcurrentRingQueue;->yv:I

    .line 226
    iget-object v0, p0, Lmiui/util/concurrent/ConcurrentRingQueue;->yt:Ljava/util/concurrent/atomic/AtomicInteger;

    invoke-virtual {v0, v2}, Ljava/util/concurrent/atomic/AtomicInteger;->set(I)V

    goto :goto_0
.end method

.method public get()Ljava/lang/Object;
    .locals 5
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()TT;"
        }
    .end annotation

    .prologue
    const/4 v3, 0x0

    const/4 v4, 0x0

    .line 97
    iget-object v0, p0, Lmiui/util/concurrent/ConcurrentRingQueue;->yr:Ljava/util/concurrent/atomic/AtomicInteger;

    invoke-virtual {v0}, Ljava/util/concurrent/atomic/AtomicInteger;->get()I

    move-result v0

    :goto_0
    if-nez v0, :cond_0

    iget-object v0, p0, Lmiui/util/concurrent/ConcurrentRingQueue;->yr:Ljava/util/concurrent/atomic/AtomicInteger;

    const/4 v1, -0x1

    invoke-virtual {v0, v4, v1}, Ljava/util/concurrent/atomic/AtomicInteger;->compareAndSet(II)Z

    move-result v0

    if-nez v0, :cond_1

    .line 98
    :cond_0
    invoke-static {}, Ljava/lang/Thread;->yield()V

    .line 97
    iget-object v0, p0, Lmiui/util/concurrent/ConcurrentRingQueue;->yr:Ljava/util/concurrent/atomic/AtomicInteger;

    invoke-virtual {v0}, Ljava/util/concurrent/atomic/AtomicInteger;->get()I

    move-result v0

    goto :goto_0

    .line 102
    :cond_1
    iget-object v1, p0, Lmiui/util/concurrent/ConcurrentRingQueue;->ys:Lmiui/util/concurrent/ConcurrentRingQueue$a;

    .line 103
    iget-object v0, p0, Lmiui/util/concurrent/ConcurrentRingQueue;->yu:Lmiui/util/concurrent/ConcurrentRingQueue$a;

    move-object v2, v3

    :goto_1
    if-nez v2, :cond_2

    if-eq v1, v0, :cond_2

    .line 104
    iget-object v2, v1, Lmiui/util/concurrent/ConcurrentRingQueue$a;->dC:Ljava/lang/Object;

    .line 105
    iput-object v3, v1, Lmiui/util/concurrent/ConcurrentRingQueue$a;->dC:Ljava/lang/Object;

    .line 103
    iget-object v1, v1, Lmiui/util/concurrent/ConcurrentRingQueue$a;->dD:Lmiui/util/concurrent/ConcurrentRingQueue$a;

    iget-object v0, p0, Lmiui/util/concurrent/ConcurrentRingQueue;->yu:Lmiui/util/concurrent/ConcurrentRingQueue$a;

    goto :goto_1

    .line 107
    :cond_2
    if-eqz v2, :cond_3

    .line 108
    iput-object v1, p0, Lmiui/util/concurrent/ConcurrentRingQueue;->ys:Lmiui/util/concurrent/ConcurrentRingQueue$a;

    .line 110
    :cond_3
    iget-object v0, p0, Lmiui/util/concurrent/ConcurrentRingQueue;->yr:Ljava/util/concurrent/atomic/AtomicInteger;

    invoke-virtual {v0, v4}, Ljava/util/concurrent/atomic/AtomicInteger;->set(I)V

    .line 111
    return-object v2
.end method

.method public getCapacity()I
    .locals 2

    .prologue
    .line 184
    iget v0, p0, Lmiui/util/concurrent/ConcurrentRingQueue;->yv:I

    if-lez v0, :cond_0

    iget v1, p0, Lmiui/util/concurrent/ConcurrentRingQueue;->yo:I

    add-int/2addr v0, v1

    :goto_0
    return v0

    :cond_0
    iget v0, p0, Lmiui/util/concurrent/ConcurrentRingQueue;->yo:I

    goto :goto_0
.end method

.method public increaseCapacity(I)V
    .locals 3

    .prologue
    const/4 v2, 0x0

    .line 194
    iget-boolean v0, p0, Lmiui/util/concurrent/ConcurrentRingQueue;->yp:Z

    if-nez v0, :cond_0

    if-gtz p1, :cond_1

    .line 206
    :cond_0
    :goto_0
    return-void

    .line 198
    :cond_1
    iget-object v0, p0, Lmiui/util/concurrent/ConcurrentRingQueue;->yt:Ljava/util/concurrent/atomic/AtomicInteger;

    invoke-virtual {v0}, Ljava/util/concurrent/atomic/AtomicInteger;->get()I

    move-result v0

    :goto_1
    if-nez v0, :cond_2

    iget-object v0, p0, Lmiui/util/concurrent/ConcurrentRingQueue;->yt:Ljava/util/concurrent/atomic/AtomicInteger;

    const/4 v1, -0x1

    invoke-virtual {v0, v2, v1}, Ljava/util/concurrent/atomic/AtomicInteger;->compareAndSet(II)Z

    move-result v0

    if-nez v0, :cond_3

    .line 199
    :cond_2
    invoke-static {}, Ljava/lang/Thread;->yield()V

    .line 198
    iget-object v0, p0, Lmiui/util/concurrent/ConcurrentRingQueue;->yt:Ljava/util/concurrent/atomic/AtomicInteger;

    invoke-virtual {v0}, Ljava/util/concurrent/atomic/AtomicInteger;->get()I

    move-result v0

    goto :goto_1

    .line 202
    :cond_3
    neg-int v0, p1

    iput v0, p0, Lmiui/util/concurrent/ConcurrentRingQueue;->yv:I

    .line 203
    iget v0, p0, Lmiui/util/concurrent/ConcurrentRingQueue;->yo:I

    add-int/2addr v0, p1

    iput v0, p0, Lmiui/util/concurrent/ConcurrentRingQueue;->yo:I

    .line 205
    iget-object v0, p0, Lmiui/util/concurrent/ConcurrentRingQueue;->yt:Ljava/util/concurrent/atomic/AtomicInteger;

    invoke-virtual {v0, v2}, Ljava/util/concurrent/atomic/AtomicInteger;->set(I)V

    goto :goto_0
.end method

.method public isEmpty()Z
    .locals 2

    .prologue
    .line 178
    iget-object v0, p0, Lmiui/util/concurrent/ConcurrentRingQueue;->yu:Lmiui/util/concurrent/ConcurrentRingQueue$a;

    iget-object v1, p0, Lmiui/util/concurrent/ConcurrentRingQueue;->ys:Lmiui/util/concurrent/ConcurrentRingQueue$a;

    if-ne v0, v1, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public put(Ljava/lang/Object;)Z
    .locals 7
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(TT;)Z"
        }
    .end annotation

    .prologue
    const/4 v0, 0x1

    const/4 v1, 0x0

    .line 63
    if-nez p1, :cond_0

    .line 92
    :goto_0
    return v1

    .line 67
    :cond_0
    iget-object v2, p0, Lmiui/util/concurrent/ConcurrentRingQueue;->yt:Ljava/util/concurrent/atomic/AtomicInteger;

    invoke-virtual {v2}, Ljava/util/concurrent/atomic/AtomicInteger;->get()I

    move-result v2

    :goto_1
    if-nez v2, :cond_1

    iget-object v2, p0, Lmiui/util/concurrent/ConcurrentRingQueue;->yt:Ljava/util/concurrent/atomic/AtomicInteger;

    const/4 v3, -0x1

    invoke-virtual {v2, v1, v3}, Ljava/util/concurrent/atomic/AtomicInteger;->compareAndSet(II)Z

    move-result v2

    if-nez v2, :cond_2

    .line 68
    :cond_1
    invoke-static {}, Ljava/lang/Thread;->yield()V

    .line 67
    iget-object v2, p0, Lmiui/util/concurrent/ConcurrentRingQueue;->yt:Ljava/util/concurrent/atomic/AtomicInteger;

    invoke-virtual {v2}, Ljava/util/concurrent/atomic/AtomicInteger;->get()I

    move-result v2

    goto :goto_1

    .line 72
    :cond_2
    iget-object v2, p0, Lmiui/util/concurrent/ConcurrentRingQueue;->ys:Lmiui/util/concurrent/ConcurrentRingQueue$a;

    .line 73
    iget-object v3, p0, Lmiui/util/concurrent/ConcurrentRingQueue;->yu:Lmiui/util/concurrent/ConcurrentRingQueue$a;

    .line 74
    iget v4, p0, Lmiui/util/concurrent/ConcurrentRingQueue;->yv:I

    .line 75
    iget-object v5, v3, Lmiui/util/concurrent/ConcurrentRingQueue$a;->dD:Lmiui/util/concurrent/ConcurrentRingQueue$a;

    if-eq v5, v2, :cond_4

    .line 76
    iput-object p1, v3, Lmiui/util/concurrent/ConcurrentRingQueue$a;->dC:Ljava/lang/Object;

    .line 77
    iget-object v5, v3, Lmiui/util/concurrent/ConcurrentRingQueue$a;->dD:Lmiui/util/concurrent/ConcurrentRingQueue$a;

    iget-object v5, v5, Lmiui/util/concurrent/ConcurrentRingQueue$a;->dD:Lmiui/util/concurrent/ConcurrentRingQueue$a;

    if-eq v5, v2, :cond_3

    iget-boolean v2, p0, Lmiui/util/concurrent/ConcurrentRingQueue;->yq:Z

    if-eqz v2, :cond_3

    if-lez v4, :cond_3

    .line 78
    iget-object v2, v3, Lmiui/util/concurrent/ConcurrentRingQueue$a;->dD:Lmiui/util/concurrent/ConcurrentRingQueue$a;

    iget-object v2, v2, Lmiui/util/concurrent/ConcurrentRingQueue$a;->dD:Lmiui/util/concurrent/ConcurrentRingQueue$a;

    iput-object v2, v3, Lmiui/util/concurrent/ConcurrentRingQueue$a;->dD:Lmiui/util/concurrent/ConcurrentRingQueue$a;

    .line 79
    add-int/lit8 v2, v4, -0x1

    iput v2, p0, Lmiui/util/concurrent/ConcurrentRingQueue;->yv:I

    .line 81
    :cond_3
    iget-object v2, v3, Lmiui/util/concurrent/ConcurrentRingQueue$a;->dD:Lmiui/util/concurrent/ConcurrentRingQueue$a;

    iput-object v2, p0, Lmiui/util/concurrent/ConcurrentRingQueue;->yu:Lmiui/util/concurrent/ConcurrentRingQueue$a;

    .line 91
    :goto_2
    iget-object v2, p0, Lmiui/util/concurrent/ConcurrentRingQueue;->yt:Ljava/util/concurrent/atomic/AtomicInteger;

    invoke-virtual {v2, v1}, Ljava/util/concurrent/atomic/AtomicInteger;->set(I)V

    move v1, v0

    .line 92
    goto :goto_0

    .line 83
    :cond_4
    iget-boolean v5, p0, Lmiui/util/concurrent/ConcurrentRingQueue;->yp:Z

    if-nez v5, :cond_5

    if-gez v4, :cond_6

    .line 84
    :cond_5
    new-instance v5, Lmiui/util/concurrent/ConcurrentRingQueue$a;

    const/4 v6, 0x0

    invoke-direct {v5, v6}, Lmiui/util/concurrent/ConcurrentRingQueue$a;-><init>(Lmiui/util/concurrent/ConcurrentRingQueue$1;)V

    iput-object v5, v3, Lmiui/util/concurrent/ConcurrentRingQueue$a;->dD:Lmiui/util/concurrent/ConcurrentRingQueue$a;

    .line 85
    iget-object v5, v3, Lmiui/util/concurrent/ConcurrentRingQueue$a;->dD:Lmiui/util/concurrent/ConcurrentRingQueue$a;

    iput-object v2, v5, Lmiui/util/concurrent/ConcurrentRingQueue$a;->dD:Lmiui/util/concurrent/ConcurrentRingQueue$a;

    .line 86
    iput-object p1, v3, Lmiui/util/concurrent/ConcurrentRingQueue$a;->dC:Ljava/lang/Object;

    .line 87
    add-int/lit8 v2, v4, 0x1

    iput v2, p0, Lmiui/util/concurrent/ConcurrentRingQueue;->yv:I

    .line 88
    iget-object v2, v3, Lmiui/util/concurrent/ConcurrentRingQueue$a;->dD:Lmiui/util/concurrent/ConcurrentRingQueue$a;

    iput-object v2, p0, Lmiui/util/concurrent/ConcurrentRingQueue;->yu:Lmiui/util/concurrent/ConcurrentRingQueue$a;

    goto :goto_2

    :cond_6
    move v0, v1

    goto :goto_2
.end method

.method public remove(Lmiui/util/concurrent/Queue$Predicate;)I
    .locals 4
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lmiui/util/concurrent/Queue$Predicate",
            "<TT;>;)I"
        }
    .end annotation

    .prologue
    const/4 v2, 0x0

    .line 138
    if-nez p1, :cond_0

    .line 154
    :goto_0
    return v2

    .line 142
    :cond_0
    iget-object v0, p0, Lmiui/util/concurrent/ConcurrentRingQueue;->yr:Ljava/util/concurrent/atomic/AtomicInteger;

    invoke-virtual {v0}, Ljava/util/concurrent/atomic/AtomicInteger;->get()I

    move-result v0

    :goto_1
    if-nez v0, :cond_1

    iget-object v0, p0, Lmiui/util/concurrent/ConcurrentRingQueue;->yr:Ljava/util/concurrent/atomic/AtomicInteger;

    const/4 v1, -0x1

    invoke-virtual {v0, v2, v1}, Ljava/util/concurrent/atomic/AtomicInteger;->compareAndSet(II)Z

    move-result v0

    if-nez v0, :cond_2

    .line 143
    :cond_1
    invoke-static {}, Ljava/lang/Thread;->yield()V

    .line 142
    iget-object v0, p0, Lmiui/util/concurrent/ConcurrentRingQueue;->yr:Ljava/util/concurrent/atomic/AtomicInteger;

    invoke-virtual {v0}, Ljava/util/concurrent/atomic/AtomicInteger;->get()I

    move-result v0

    goto :goto_1

    .line 147
    :cond_2
    iget-object v0, p0, Lmiui/util/concurrent/ConcurrentRingQueue;->ys:Lmiui/util/concurrent/ConcurrentRingQueue$a;

    move v1, v2

    :goto_2
    iget-object v3, p0, Lmiui/util/concurrent/ConcurrentRingQueue;->yu:Lmiui/util/concurrent/ConcurrentRingQueue$a;

    if-eq v0, v3, :cond_4

    .line 148
    iget-object v3, v0, Lmiui/util/concurrent/ConcurrentRingQueue$a;->dC:Ljava/lang/Object;

    invoke-interface {p1, v3}, Lmiui/util/concurrent/Queue$Predicate;->apply(Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_3

    .line 149
    const/4 v3, 0x0

    iput-object v3, v0, Lmiui/util/concurrent/ConcurrentRingQueue$a;->dC:Ljava/lang/Object;

    .line 150
    add-int/lit8 v1, v1, 0x1

    .line 147
    :cond_3
    iget-object v0, v0, Lmiui/util/concurrent/ConcurrentRingQueue$a;->dD:Lmiui/util/concurrent/ConcurrentRingQueue$a;

    goto :goto_2

    .line 153
    :cond_4
    iget-object v0, p0, Lmiui/util/concurrent/ConcurrentRingQueue;->yr:Ljava/util/concurrent/atomic/AtomicInteger;

    invoke-virtual {v0, v2}, Ljava/util/concurrent/atomic/AtomicInteger;->set(I)V

    move v2, v1

    .line 154
    goto :goto_0
.end method

.method public remove(Ljava/lang/Object;)Z
    .locals 3
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(TT;)Z"
        }
    .end annotation

    .prologue
    const/4 v1, 0x0

    .line 116
    if-nez p1, :cond_0

    .line 133
    :goto_0
    return v1

    .line 120
    :cond_0
    iget-object v0, p0, Lmiui/util/concurrent/ConcurrentRingQueue;->yr:Ljava/util/concurrent/atomic/AtomicInteger;

    invoke-virtual {v0}, Ljava/util/concurrent/atomic/AtomicInteger;->get()I

    move-result v0

    :goto_1
    if-nez v0, :cond_1

    iget-object v0, p0, Lmiui/util/concurrent/ConcurrentRingQueue;->yr:Ljava/util/concurrent/atomic/AtomicInteger;

    const/4 v2, -0x1

    invoke-virtual {v0, v1, v2}, Ljava/util/concurrent/atomic/AtomicInteger;->compareAndSet(II)Z

    move-result v0

    if-nez v0, :cond_2

    .line 121
    :cond_1
    invoke-static {}, Ljava/lang/Thread;->yield()V

    .line 120
    iget-object v0, p0, Lmiui/util/concurrent/ConcurrentRingQueue;->yr:Ljava/util/concurrent/atomic/AtomicInteger;

    invoke-virtual {v0}, Ljava/util/concurrent/atomic/AtomicInteger;->get()I

    move-result v0

    goto :goto_1

    .line 125
    :cond_2
    iget-object v0, p0, Lmiui/util/concurrent/ConcurrentRingQueue;->ys:Lmiui/util/concurrent/ConcurrentRingQueue$a;

    :goto_2
    iget-object v2, p0, Lmiui/util/concurrent/ConcurrentRingQueue;->yu:Lmiui/util/concurrent/ConcurrentRingQueue$a;

    if-eq v0, v2, :cond_4

    .line 126
    iget-object v2, v0, Lmiui/util/concurrent/ConcurrentRingQueue$a;->dC:Ljava/lang/Object;

    invoke-virtual {p1, v2}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_3

    .line 127
    const/4 v2, 0x0

    iput-object v2, v0, Lmiui/util/concurrent/ConcurrentRingQueue$a;->dC:Ljava/lang/Object;

    .line 128
    const/4 v0, 0x1

    .line 132
    :goto_3
    iget-object v2, p0, Lmiui/util/concurrent/ConcurrentRingQueue;->yr:Ljava/util/concurrent/atomic/AtomicInteger;

    invoke-virtual {v2, v1}, Ljava/util/concurrent/atomic/AtomicInteger;->set(I)V

    move v1, v0

    .line 133
    goto :goto_0

    .line 125
    :cond_3
    iget-object v0, v0, Lmiui/util/concurrent/ConcurrentRingQueue$a;->dD:Lmiui/util/concurrent/ConcurrentRingQueue$a;

    goto :goto_2

    :cond_4
    move v0, v1

    goto :goto_3
.end method
