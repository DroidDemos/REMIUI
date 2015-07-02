.class final Lcom/miui/internal/server/DropBoxManagerService$a;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Comparable;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/internal/server/DropBoxManagerService;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x1a
    name = "a"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Object;",
        "Ljava/lang/Comparable",
        "<",
        "Lcom/miui/internal/server/DropBoxManagerService$a;",
        ">;"
    }
.end annotation


# instance fields
.field public final Bj:J

.field public final Bk:I

.field public final file:Ljava/io/File;

.field public final flags:I

.field public final tag:Ljava/lang/String;


# direct methods
.method public constructor <init>(J)V
    .locals 2

    .prologue
    const/4 v1, 0x0

    .line 543
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 544
    iput-object v1, p0, Lcom/miui/internal/server/DropBoxManagerService$a;->tag:Ljava/lang/String;

    .line 545
    iput-wide p1, p0, Lcom/miui/internal/server/DropBoxManagerService$a;->Bj:J

    .line 546
    const/4 v0, 0x1

    iput v0, p0, Lcom/miui/internal/server/DropBoxManagerService$a;->flags:I

    .line 547
    iput-object v1, p0, Lcom/miui/internal/server/DropBoxManagerService$a;->file:Ljava/io/File;

    .line 548
    const/4 v0, 0x0

    iput v0, p0, Lcom/miui/internal/server/DropBoxManagerService$a;->Bk:I

    .line 549
    return-void
.end method

.method public constructor <init>(Ljava/io/File;I)V
    .locals 10

    .prologue
    const/4 v9, 0x1

    const-wide/16 v3, 0x0

    const/4 v1, 0x0

    .line 500
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 501
    iput-object p1, p0, Lcom/miui/internal/server/DropBoxManagerService$a;->file:Ljava/io/File;

    .line 502
    iget-object v0, p0, Lcom/miui/internal/server/DropBoxManagerService$a;->file:Ljava/io/File;

    invoke-virtual {v0}, Ljava/io/File;->length()J

    move-result-wide v5

    int-to-long v7, p2

    add-long/2addr v5, v7

    const-wide/16 v7, 0x1

    sub-long/2addr v5, v7

    int-to-long v7, p2

    div-long/2addr v5, v7

    long-to-int v0, v5

    iput v0, p0, Lcom/miui/internal/server/DropBoxManagerService$a;->Bk:I

    .line 504
    invoke-virtual {p1}, Ljava/io/File;->getName()Ljava/lang/String;

    move-result-object v2

    .line 505
    const/16 v0, 0x40

    invoke-virtual {v2, v0}, Ljava/lang/String;->lastIndexOf(I)I

    move-result v5

    .line 506
    if-gez v5, :cond_0

    .line 507
    const/4 v0, 0x0

    iput-object v0, p0, Lcom/miui/internal/server/DropBoxManagerService$a;->tag:Ljava/lang/String;

    .line 508
    iput-wide v3, p0, Lcom/miui/internal/server/DropBoxManagerService$a;->Bj:J

    .line 509
    iput v9, p0, Lcom/miui/internal/server/DropBoxManagerService$a;->flags:I

    .line 537
    :goto_0
    return-void

    .line 514
    :cond_0
    invoke-virtual {v2, v1, v5}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Landroid/net/Uri;->decode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/internal/server/DropBoxManagerService$a;->tag:Ljava/lang/String;

    .line 515
    const-string v0, ".gz"

    invoke-virtual {v2, v0}, Ljava/lang/String;->endsWith(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_4

    .line 516
    const/4 v0, 0x4

    .line 517
    invoke-virtual {v2}, Ljava/lang/String;->length()I

    move-result v6

    add-int/lit8 v6, v6, -0x3

    invoke-virtual {v2, v1, v6}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object v1

    .line 519
    :goto_1
    const-string v2, ".lost"

    invoke-virtual {v1, v2}, Ljava/lang/String;->endsWith(Ljava/lang/String;)Z

    move-result v2

    if-eqz v2, :cond_1

    .line 520
    or-int/lit8 v0, v0, 0x1

    .line 521
    add-int/lit8 v2, v5, 0x1

    invoke-virtual {v1}, Ljava/lang/String;->length()I

    move-result v5

    add-int/lit8 v5, v5, -0x5

    invoke-virtual {v1, v2, v5}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object v1

    .line 532
    :goto_2
    iput v0, p0, Lcom/miui/internal/server/DropBoxManagerService$a;->flags:I

    .line 535
    :try_start_0
    invoke-static {v1}, Ljava/lang/Long;->valueOf(Ljava/lang/String;)Ljava/lang/Long;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/Long;->longValue()J
    :try_end_0
    .catch Ljava/lang/NumberFormatException; {:try_start_0 .. :try_end_0} :catch_0

    move-result-wide v0

    .line 536
    :goto_3
    iput-wide v0, p0, Lcom/miui/internal/server/DropBoxManagerService$a;->Bj:J

    goto :goto_0

    .line 522
    :cond_1
    const-string v2, ".txt"

    invoke-virtual {v1, v2}, Ljava/lang/String;->endsWith(Ljava/lang/String;)Z

    move-result v2

    if-eqz v2, :cond_2

    .line 523
    or-int/lit8 v0, v0, 0x2

    .line 524
    add-int/lit8 v2, v5, 0x1

    invoke-virtual {v1}, Ljava/lang/String;->length()I

    move-result v5

    add-int/lit8 v5, v5, -0x4

    invoke-virtual {v1, v2, v5}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object v1

    goto :goto_2

    .line 525
    :cond_2
    const-string v2, ".dat"

    invoke-virtual {v1, v2}, Ljava/lang/String;->endsWith(Ljava/lang/String;)Z

    move-result v2

    if-eqz v2, :cond_3

    .line 526
    add-int/lit8 v2, v5, 0x1

    invoke-virtual {v1}, Ljava/lang/String;->length()I

    move-result v5

    add-int/lit8 v5, v5, -0x4

    invoke-virtual {v1, v2, v5}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object v1

    goto :goto_2

    .line 528
    :cond_3
    iput v9, p0, Lcom/miui/internal/server/DropBoxManagerService$a;->flags:I

    .line 529
    iput-wide v3, p0, Lcom/miui/internal/server/DropBoxManagerService$a;->Bj:J

    goto :goto_0

    .line 535
    :catch_0
    move-exception v0

    move-wide v0, v3

    goto :goto_3

    :cond_4
    move v0, v1

    move-object v1, v2

    goto :goto_1
.end method

.method public constructor <init>(Ljava/io/File;Ljava/io/File;Ljava/lang/String;JII)V
    .locals 4
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 463
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 464
    and-int/lit8 v0, p6, 0x1

    if-eqz v0, :cond_0

    new-instance v0, Ljava/lang/IllegalArgumentException;

    invoke-direct {v0}, Ljava/lang/IllegalArgumentException;-><init>()V

    throw v0

    .line 466
    :cond_0
    iput-object p3, p0, Lcom/miui/internal/server/DropBoxManagerService$a;->tag:Ljava/lang/String;

    .line 467
    iput-wide p4, p0, Lcom/miui/internal/server/DropBoxManagerService$a;->Bj:J

    .line 468
    iput p6, p0, Lcom/miui/internal/server/DropBoxManagerService$a;->flags:I

    .line 469
    new-instance v1, Ljava/io/File;

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    invoke-static {p3}, Landroid/net/Uri;->encode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    const-string v2, "@"

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0, p4, p5}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    move-result-object v2

    and-int/lit8 v0, p6, 0x2

    if-eqz v0, :cond_1

    const-string v0, ".txt"

    :goto_0
    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    and-int/lit8 v0, p6, 0x4

    if-eqz v0, :cond_2

    const-string v0, ".gz"

    :goto_1
    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-direct {v1, p2, v0}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    iput-object v1, p0, Lcom/miui/internal/server/DropBoxManagerService$a;->file:Ljava/io/File;

    .line 473
    iget-object v0, p0, Lcom/miui/internal/server/DropBoxManagerService$a;->file:Ljava/io/File;

    invoke-virtual {p1, v0}, Ljava/io/File;->renameTo(Ljava/io/File;)Z

    move-result v0

    if-nez v0, :cond_3

    .line 474
    new-instance v0, Ljava/io/IOException;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "Can\'t rename "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, " to "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    iget-object v2, p0, Lcom/miui/internal/server/DropBoxManagerService$a;->file:Ljava/io/File;

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/io/IOException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 469
    :cond_1
    const-string v0, ".dat"

    goto :goto_0

    :cond_2
    const-string v0, ""

    goto :goto_1

    .line 476
    :cond_3
    iget-object v0, p0, Lcom/miui/internal/server/DropBoxManagerService$a;->file:Ljava/io/File;

    invoke-virtual {v0}, Ljava/io/File;->length()J

    move-result-wide v0

    int-to-long v2, p7

    add-long/2addr v0, v2

    const-wide/16 v2, 0x1

    sub-long/2addr v0, v2

    int-to-long v2, p7

    div-long/2addr v0, v2

    long-to-int v0, v0

    iput v0, p0, Lcom/miui/internal/server/DropBoxManagerService$a;->Bk:I

    .line 477
    return-void
.end method

.method public constructor <init>(Ljava/io/File;Ljava/lang/String;J)V
    .locals 3
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 486
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 487
    iput-object p2, p0, Lcom/miui/internal/server/DropBoxManagerService$a;->tag:Ljava/lang/String;

    .line 488
    iput-wide p3, p0, Lcom/miui/internal/server/DropBoxManagerService$a;->Bj:J

    .line 489
    const/4 v0, 0x1

    iput v0, p0, Lcom/miui/internal/server/DropBoxManagerService$a;->flags:I

    .line 490
    new-instance v0, Ljava/io/File;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    invoke-static {p2}, Landroid/net/Uri;->encode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, "@"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p3, p4}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, ".lost"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, p1, v1}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    iput-object v0, p0, Lcom/miui/internal/server/DropBoxManagerService$a;->file:Ljava/io/File;

    .line 491
    const/4 v0, 0x0

    iput v0, p0, Lcom/miui/internal/server/DropBoxManagerService$a;->Bk:I

    .line 492
    new-instance v0, Ljava/io/FileOutputStream;

    iget-object v1, p0, Lcom/miui/internal/server/DropBoxManagerService$a;->file:Ljava/io/File;

    invoke-direct {v0, v1}, Ljava/io/FileOutputStream;-><init>(Ljava/io/File;)V

    invoke-virtual {v0}, Ljava/io/FileOutputStream;->close()V

    .line 493
    return-void
.end method


# virtual methods
.method public final a(Lcom/miui/internal/server/DropBoxManagerService$a;)I
    .locals 7

    .prologue
    const/4 v2, 0x0

    const/4 v1, 0x1

    const/4 v0, -0x1

    .line 441
    iget-wide v3, p0, Lcom/miui/internal/server/DropBoxManagerService$a;->Bj:J

    iget-wide v5, p1, Lcom/miui/internal/server/DropBoxManagerService$a;->Bj:J

    cmp-long v3, v3, v5

    if-gez v3, :cond_1

    .line 449
    :cond_0
    :goto_0
    return v0

    .line 442
    :cond_1
    iget-wide v3, p0, Lcom/miui/internal/server/DropBoxManagerService$a;->Bj:J

    iget-wide v5, p1, Lcom/miui/internal/server/DropBoxManagerService$a;->Bj:J

    cmp-long v3, v3, v5

    if-lez v3, :cond_2

    move v0, v1

    goto :goto_0

    .line 443
    :cond_2
    iget-object v3, p0, Lcom/miui/internal/server/DropBoxManagerService$a;->file:Ljava/io/File;

    if-eqz v3, :cond_3

    iget-object v3, p1, Lcom/miui/internal/server/DropBoxManagerService$a;->file:Ljava/io/File;

    if-eqz v3, :cond_3

    iget-object v0, p0, Lcom/miui/internal/server/DropBoxManagerService$a;->file:Ljava/io/File;

    iget-object v1, p1, Lcom/miui/internal/server/DropBoxManagerService$a;->file:Ljava/io/File;

    invoke-virtual {v0, v1}, Ljava/io/File;->compareTo(Ljava/io/File;)I

    move-result v0

    goto :goto_0

    .line 444
    :cond_3
    iget-object v3, p1, Lcom/miui/internal/server/DropBoxManagerService$a;->file:Ljava/io/File;

    if-nez v3, :cond_0

    .line 445
    iget-object v3, p0, Lcom/miui/internal/server/DropBoxManagerService$a;->file:Ljava/io/File;

    if-eqz v3, :cond_4

    move v0, v1

    goto :goto_0

    .line 446
    :cond_4
    if-ne p0, p1, :cond_5

    move v0, v2

    goto :goto_0

    .line 447
    :cond_5
    invoke-virtual {p0}, Ljava/lang/Object;->hashCode()I

    move-result v3

    invoke-virtual {p1}, Ljava/lang/Object;->hashCode()I

    move-result v4

    if-lt v3, v4, :cond_0

    .line 448
    invoke-virtual {p0}, Ljava/lang/Object;->hashCode()I

    move-result v0

    invoke-virtual {p1}, Ljava/lang/Object;->hashCode()I

    move-result v3

    if-le v0, v3, :cond_6

    move v0, v1

    goto :goto_0

    :cond_6
    move v0, v2

    .line 449
    goto :goto_0
.end method

.method public bridge synthetic compareTo(Ljava/lang/Object;)I
    .locals 1

    .prologue
    .line 432
    check-cast p1, Lcom/miui/internal/server/DropBoxManagerService$a;

    invoke-virtual {p0, p1}, Lcom/miui/internal/server/DropBoxManagerService$a;->a(Lcom/miui/internal/server/DropBoxManagerService$a;)I

    move-result v0

    return v0
.end method
