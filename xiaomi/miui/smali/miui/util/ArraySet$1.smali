.class Lmiui/util/ArraySet$1;
.super Lmiui/util/a;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lmiui/util/ArraySet;->aj()Lmiui/util/a;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lmiui/util/a",
        "<TE;TE;>;"
    }
.end annotation


# instance fields
.field final synthetic eS:Lmiui/util/ArraySet;


# direct methods
.method constructor <init>(Lmiui/util/ArraySet;)V
    .locals 0

    .prologue
    .line 564
    iput-object p1, p0, Lmiui/util/ArraySet$1;->eS:Lmiui/util/ArraySet;

    invoke-direct {p0}, Lmiui/util/a;-><init>()V

    return-void
.end method


# virtual methods
.method protected S()I
    .locals 1

    .prologue
    .line 567
    iget-object v0, p0, Lmiui/util/ArraySet$1;->eS:Lmiui/util/ArraySet;

    iget v0, v0, Lmiui/util/ArraySet;->hm:I

    return v0
.end method

.method protected T()Ljava/util/Map;
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/Map",
            "<TE;TE;>;"
        }
    .end annotation

    .prologue
    .line 587
    new-instance v0, Ljava/lang/UnsupportedOperationException;

    const-string v1, "not a map"

    invoke-direct {v0, v1}, Ljava/lang/UnsupportedOperationException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

.method protected U()V
    .locals 1

    .prologue
    .line 607
    iget-object v0, p0, Lmiui/util/ArraySet$1;->eS:Lmiui/util/ArraySet;

    invoke-virtual {v0}, Lmiui/util/ArraySet;->clear()V

    .line 608
    return-void
.end method

.method protected a(ILjava/lang/Object;)Ljava/lang/Object;
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(ITE;)TE;"
        }
    .end annotation

    .prologue
    .line 597
    new-instance v0, Ljava/lang/UnsupportedOperationException;

    const-string v1, "not a map"

    invoke-direct {v0, v1}, Ljava/lang/UnsupportedOperationException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

.method protected c(Ljava/lang/Object;)I
    .locals 2

    .prologue
    .line 577
    if-nez p1, :cond_0

    iget-object v0, p0, Lmiui/util/ArraySet$1;->eS:Lmiui/util/ArraySet;

    invoke-static {v0}, Lmiui/util/ArraySet;->a(Lmiui/util/ArraySet;)I

    move-result v0

    :goto_0
    return v0

    :cond_0
    iget-object v0, p0, Lmiui/util/ArraySet$1;->eS:Lmiui/util/ArraySet;

    invoke-virtual {p1}, Ljava/lang/Object;->hashCode()I

    move-result v1

    invoke-static {v0, p1, v1}, Lmiui/util/ArraySet;->a(Lmiui/util/ArraySet;Ljava/lang/Object;I)I

    move-result v0

    goto :goto_0
.end method

.method protected c(Ljava/lang/Object;Ljava/lang/Object;)V
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(TE;TE;)V"
        }
    .end annotation

    .prologue
    .line 592
    iget-object v0, p0, Lmiui/util/ArraySet$1;->eS:Lmiui/util/ArraySet;

    invoke-virtual {v0, p1}, Lmiui/util/ArraySet;->add(Ljava/lang/Object;)Z

    .line 593
    return-void
.end method

.method protected d(Ljava/lang/Object;)I
    .locals 2

    .prologue
    .line 582
    if-nez p1, :cond_0

    iget-object v0, p0, Lmiui/util/ArraySet$1;->eS:Lmiui/util/ArraySet;

    invoke-static {v0}, Lmiui/util/ArraySet;->a(Lmiui/util/ArraySet;)I

    move-result v0

    :goto_0
    return v0

    :cond_0
    iget-object v0, p0, Lmiui/util/ArraySet$1;->eS:Lmiui/util/ArraySet;

    invoke-virtual {p1}, Ljava/lang/Object;->hashCode()I

    move-result v1

    invoke-static {v0, p1, v1}, Lmiui/util/ArraySet;->a(Lmiui/util/ArraySet;Ljava/lang/Object;I)I

    move-result v0

    goto :goto_0
.end method

.method protected d(II)Ljava/lang/Object;
    .locals 1

    .prologue
    .line 572
    iget-object v0, p0, Lmiui/util/ArraySet$1;->eS:Lmiui/util/ArraySet;

    iget-object v0, v0, Lmiui/util/ArraySet;->hl:[Ljava/lang/Object;

    aget-object v0, v0, p1

    return-object v0
.end method

.method protected v(I)V
    .locals 1

    .prologue
    .line 602
    iget-object v0, p0, Lmiui/util/ArraySet$1;->eS:Lmiui/util/ArraySet;

    invoke-virtual {v0, p1}, Lmiui/util/ArraySet;->removeAt(I)Ljava/lang/Object;

    .line 603
    return-void
.end method
