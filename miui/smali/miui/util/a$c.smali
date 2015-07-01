.class final Lmiui/util/a$c;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/util/Iterator;
.implements Ljava/util/Map$Entry;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/util/a;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x10
    name = "c"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Object;",
        "Ljava/util/Iterator",
        "<",
        "Ljava/util/Map$Entry",
        "<TK;TV;>;>;",
        "Ljava/util/Map$Entry",
        "<TK;TV;>;"
    }
.end annotation


# instance fields
.field final synthetic E:Lmiui/util/a;

.field mIndex:I

.field vV:I

.field vW:Z


# direct methods
.method constructor <init>(Lmiui/util/a;)V
    .locals 1

    .prologue
    .line 64
    iput-object p1, p0, Lmiui/util/a$c;->E:Lmiui/util/a;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 62
    const/4 v0, 0x0

    iput-boolean v0, p0, Lmiui/util/a$c;->vW:Z

    .line 65
    invoke-virtual {p1}, Lmiui/util/a;->S()I

    move-result v0

    add-int/lit8 v0, v0, -0x1

    iput v0, p0, Lmiui/util/a$c;->vV:I

    .line 66
    const/4 v0, -0x1

    iput v0, p0, Lmiui/util/a$c;->mIndex:I

    .line 67
    return-void
.end method


# virtual methods
.method public cb()Ljava/util/Map$Entry;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/Map$Entry",
            "<TK;TV;>;"
        }
    .end annotation

    .prologue
    .line 76
    iget v0, p0, Lmiui/util/a$c;->mIndex:I

    add-int/lit8 v0, v0, 0x1

    iput v0, p0, Lmiui/util/a$c;->mIndex:I

    .line 77
    const/4 v0, 0x1

    iput-boolean v0, p0, Lmiui/util/a$c;->vW:Z

    .line 78
    return-object p0
.end method

.method public final equals(Ljava/lang/Object;)Z
    .locals 5

    .prologue
    const/4 v0, 0x1

    const/4 v1, 0x0

    .line 121
    iget-boolean v2, p0, Lmiui/util/a$c;->vW:Z

    if-nez v2, :cond_0

    .line 122
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "This container does not support retaining Map.Entry objects"

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 125
    :cond_0
    instance-of v2, p1, Ljava/util/Map$Entry;

    if-nez v2, :cond_1

    .line 129
    :goto_0
    return v1

    .line 128
    :cond_1
    check-cast p1, Ljava/util/Map$Entry;

    .line 129
    invoke-interface {p1}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v2

    iget-object v3, p0, Lmiui/util/a$c;->E:Lmiui/util/a;

    iget v4, p0, Lmiui/util/a$c;->mIndex:I

    invoke-virtual {v3, v4, v1}, Lmiui/util/a;->d(II)Ljava/lang/Object;

    move-result-object v3

    invoke-static {v2, v3}, Lmiui/util/b;->a(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_2

    invoke-interface {p1}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v2

    iget-object v3, p0, Lmiui/util/a$c;->E:Lmiui/util/a;

    iget v4, p0, Lmiui/util/a$c;->mIndex:I

    invoke-virtual {v3, v4, v0}, Lmiui/util/a;->d(II)Ljava/lang/Object;

    move-result-object v3

    invoke-static {v2, v3}, Lmiui/util/b;->a(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_2

    :goto_1
    move v1, v0

    goto :goto_0

    :cond_2
    move v0, v1

    goto :goto_1
.end method

.method public getKey()Ljava/lang/Object;
    .locals 3
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()TK;"
        }
    .end annotation

    .prologue
    .line 94
    iget-boolean v0, p0, Lmiui/util/a$c;->vW:Z

    if-nez v0, :cond_0

    .line 95
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "This container does not support retaining Map.Entry objects"

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 98
    :cond_0
    iget-object v0, p0, Lmiui/util/a$c;->E:Lmiui/util/a;

    iget v1, p0, Lmiui/util/a$c;->mIndex:I

    const/4 v2, 0x0

    invoke-virtual {v0, v1, v2}, Lmiui/util/a;->d(II)Ljava/lang/Object;

    move-result-object v0

    return-object v0
.end method

.method public getValue()Ljava/lang/Object;
    .locals 3
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()TV;"
        }
    .end annotation

    .prologue
    .line 103
    iget-boolean v0, p0, Lmiui/util/a$c;->vW:Z

    if-nez v0, :cond_0

    .line 104
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "This container does not support retaining Map.Entry objects"

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 107
    :cond_0
    iget-object v0, p0, Lmiui/util/a$c;->E:Lmiui/util/a;

    iget v1, p0, Lmiui/util/a$c;->mIndex:I

    const/4 v2, 0x1

    invoke-virtual {v0, v1, v2}, Lmiui/util/a;->d(II)Ljava/lang/Object;

    move-result-object v0

    return-object v0
.end method

.method public hasNext()Z
    .locals 2

    .prologue
    .line 71
    iget v0, p0, Lmiui/util/a$c;->mIndex:I

    iget v1, p0, Lmiui/util/a$c;->vV:I

    if-ge v0, v1, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public final hashCode()I
    .locals 5

    .prologue
    const/4 v0, 0x0

    .line 135
    iget-boolean v1, p0, Lmiui/util/a$c;->vW:Z

    if-nez v1, :cond_0

    .line 136
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "This container does not support retaining Map.Entry objects"

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 139
    :cond_0
    iget-object v1, p0, Lmiui/util/a$c;->E:Lmiui/util/a;

    iget v2, p0, Lmiui/util/a$c;->mIndex:I

    invoke-virtual {v1, v2, v0}, Lmiui/util/a;->d(II)Ljava/lang/Object;

    move-result-object v1

    .line 140
    iget-object v2, p0, Lmiui/util/a$c;->E:Lmiui/util/a;

    iget v3, p0, Lmiui/util/a$c;->mIndex:I

    const/4 v4, 0x1

    invoke-virtual {v2, v3, v4}, Lmiui/util/a;->d(II)Ljava/lang/Object;

    move-result-object v2

    .line 141
    if-nez v1, :cond_1

    move v1, v0

    :goto_0
    if-nez v2, :cond_2

    :goto_1
    xor-int/2addr v0, v1

    return v0

    :cond_1
    invoke-virtual {v1}, Ljava/lang/Object;->hashCode()I

    move-result v1

    goto :goto_0

    :cond_2
    invoke-virtual {v2}, Ljava/lang/Object;->hashCode()I

    move-result v0

    goto :goto_1
.end method

.method public bridge synthetic next()Ljava/lang/Object;
    .locals 1

    .prologue
    .line 59
    invoke-virtual {p0}, Lmiui/util/a$c;->cb()Ljava/util/Map$Entry;

    move-result-object v0

    return-object v0
.end method

.method public remove()V
    .locals 2

    .prologue
    .line 83
    iget-boolean v0, p0, Lmiui/util/a$c;->vW:Z

    if-nez v0, :cond_0

    .line 84
    new-instance v0, Ljava/lang/IllegalStateException;

    invoke-direct {v0}, Ljava/lang/IllegalStateException;-><init>()V

    throw v0

    .line 86
    :cond_0
    iget v0, p0, Lmiui/util/a$c;->mIndex:I

    add-int/lit8 v0, v0, -0x1

    iput v0, p0, Lmiui/util/a$c;->mIndex:I

    .line 87
    iget v0, p0, Lmiui/util/a$c;->vV:I

    add-int/lit8 v0, v0, -0x1

    iput v0, p0, Lmiui/util/a$c;->vV:I

    .line 88
    const/4 v0, 0x0

    iput-boolean v0, p0, Lmiui/util/a$c;->vW:Z

    .line 89
    iget-object v0, p0, Lmiui/util/a$c;->E:Lmiui/util/a;

    iget v1, p0, Lmiui/util/a$c;->mIndex:I

    invoke-virtual {v0, v1}, Lmiui/util/a;->v(I)V

    .line 90
    return-void
.end method

.method public setValue(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(TV;)TV;"
        }
    .end annotation

    .prologue
    .line 112
    iget-boolean v0, p0, Lmiui/util/a$c;->vW:Z

    if-nez v0, :cond_0

    .line 113
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "This container does not support retaining Map.Entry objects"

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 116
    :cond_0
    iget-object v0, p0, Lmiui/util/a$c;->E:Lmiui/util/a;

    iget v1, p0, Lmiui/util/a$c;->mIndex:I

    invoke-virtual {v0, v1, p1}, Lmiui/util/a;->a(ILjava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    return-object v0
.end method

.method public final toString()Ljava/lang/String;
    .locals 2

    .prologue
    .line 147
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {p0}, Lmiui/util/a$c;->getKey()Ljava/lang/Object;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v0

    const-string v1, "="

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {p0}, Lmiui/util/a$c;->getValue()Ljava/lang/Object;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method
