.class Lmiui/util/ArrayMap$1;
.super Lmiui/util/a;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lmiui/util/ArrayMap;->aj()Lmiui/util/a;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lmiui/util/a",
        "<TK;TV;>;"
    }
.end annotation


# instance fields
.field final synthetic Up:Lmiui/util/ArrayMap;


# direct methods
.method constructor <init>(Lmiui/util/ArrayMap;)V
    .locals 0

    .prologue
    .line 669
    iput-object p1, p0, Lmiui/util/ArrayMap$1;->Up:Lmiui/util/ArrayMap;

    invoke-direct {p0}, Lmiui/util/a;-><init>()V

    return-void
.end method


# virtual methods
.method protected S()I
    .locals 1

    .prologue
    .line 672
    iget-object v0, p0, Lmiui/util/ArrayMap$1;->Up:Lmiui/util/ArrayMap;

    iget v0, v0, Lmiui/util/ArrayMap;->hm:I

    return v0
.end method

.method protected T()Ljava/util/Map;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/Map",
            "<TK;TV;>;"
        }
    .end annotation

    .prologue
    .line 692
    iget-object v0, p0, Lmiui/util/ArrayMap$1;->Up:Lmiui/util/ArrayMap;

    return-object v0
.end method

.method protected U()V
    .locals 1

    .prologue
    .line 712
    iget-object v0, p0, Lmiui/util/ArrayMap$1;->Up:Lmiui/util/ArrayMap;

    invoke-virtual {v0}, Lmiui/util/ArrayMap;->clear()V

    .line 713
    return-void
.end method

.method protected a(ILjava/lang/Object;)Ljava/lang/Object;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(ITV;)TV;"
        }
    .end annotation

    .prologue
    .line 702
    iget-object v0, p0, Lmiui/util/ArrayMap$1;->Up:Lmiui/util/ArrayMap;

    invoke-virtual {v0, p1, p2}, Lmiui/util/ArrayMap;->setValueAt(ILjava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    return-object v0
.end method

.method protected c(Ljava/lang/Object;)I
    .locals 2

    .prologue
    .line 682
    if-nez p1, :cond_0

    iget-object v0, p0, Lmiui/util/ArrayMap$1;->Up:Lmiui/util/ArrayMap;

    invoke-virtual {v0}, Lmiui/util/ArrayMap;->ai()I

    move-result v0

    :goto_0
    return v0

    :cond_0
    iget-object v0, p0, Lmiui/util/ArrayMap$1;->Up:Lmiui/util/ArrayMap;

    invoke-virtual {p1}, Ljava/lang/Object;->hashCode()I

    move-result v1

    invoke-virtual {v0, p1, v1}, Lmiui/util/ArrayMap;->indexOf(Ljava/lang/Object;I)I

    move-result v0

    goto :goto_0
.end method

.method protected c(Ljava/lang/Object;Ljava/lang/Object;)V
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(TK;TV;)V"
        }
    .end annotation

    .prologue
    .line 697
    iget-object v0, p0, Lmiui/util/ArrayMap$1;->Up:Lmiui/util/ArrayMap;

    invoke-virtual {v0, p1, p2}, Lmiui/util/ArrayMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 698
    return-void
.end method

.method protected d(Ljava/lang/Object;)I
    .locals 1

    .prologue
    .line 687
    iget-object v0, p0, Lmiui/util/ArrayMap$1;->Up:Lmiui/util/ArrayMap;

    invoke-virtual {v0, p1}, Lmiui/util/ArrayMap;->indexOfValue(Ljava/lang/Object;)I

    move-result v0

    return v0
.end method

.method protected d(II)Ljava/lang/Object;
    .locals 2

    .prologue
    .line 677
    iget-object v0, p0, Lmiui/util/ArrayMap$1;->Up:Lmiui/util/ArrayMap;

    iget-object v0, v0, Lmiui/util/ArrayMap;->hl:[Ljava/lang/Object;

    shl-int/lit8 v1, p1, 0x1

    add-int/2addr v1, p2

    aget-object v0, v0, v1

    return-object v0
.end method

.method protected v(I)V
    .locals 1

    .prologue
    .line 707
    iget-object v0, p0, Lmiui/util/ArrayMap$1;->Up:Lmiui/util/ArrayMap;

    invoke-virtual {v0, p1}, Lmiui/util/ArrayMap;->removeAt(I)Ljava/lang/Object;

    .line 708
    return-void
.end method
