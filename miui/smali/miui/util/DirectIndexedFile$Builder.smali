.class public Lmiui/util/DirectIndexedFile$Builder;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/util/DirectIndexedFile;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x9
    name = "Builder"
.end annotation

.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lmiui/util/DirectIndexedFile$Builder$b;,
        Lmiui/util/DirectIndexedFile$Builder$a;,
        Lmiui/util/DirectIndexedFile$Builder$c;
    }
.end annotation


# instance fields
.field private sI:Lmiui/util/DirectIndexedFile$d;

.field private sJ:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList",
            "<",
            "Lmiui/util/DirectIndexedFile$Builder$b;",
            ">;"
        }
    .end annotation
.end field

.field private sK:Lmiui/util/DirectIndexedFile$Builder$b;

.field private sL:I


# direct methods
.method private constructor <init>(I)V
    .locals 1

    .prologue
    .line 1188
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 1189
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lmiui/util/DirectIndexedFile$Builder;->sJ:Ljava/util/ArrayList;

    .line 1190
    iput p1, p0, Lmiui/util/DirectIndexedFile$Builder;->sL:I

    .line 1191
    return-void
.end method

.method synthetic constructor <init>(ILmiui/util/DirectIndexedFile$1;)V
    .locals 0

    .prologue
    .line 1110
    invoke-direct {p0, p1}, Lmiui/util/DirectIndexedFile$Builder;-><init>(I)V

    return-void
.end method

.method private b(Ljava/io/DataOutput;)I
    .locals 11
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    const/4 v3, 0x0

    .line 1436
    move v2, v3

    move v0, v3

    .line 1437
    :goto_0
    iget-object v1, p0, Lmiui/util/DirectIndexedFile$Builder;->sI:Lmiui/util/DirectIndexedFile$d;

    invoke-static {v1}, Lmiui/util/DirectIndexedFile$d;->a(Lmiui/util/DirectIndexedFile$d;)[Lmiui/util/DirectIndexedFile$b;

    move-result-object v1

    array-length v1, v1

    if-ge v2, v1, :cond_d

    .line 1438
    iget-object v1, p0, Lmiui/util/DirectIndexedFile$Builder;->sI:Lmiui/util/DirectIndexedFile$d;

    invoke-static {v1, p1}, Lmiui/util/DirectIndexedFile$d;->a(Lmiui/util/DirectIndexedFile$d;Ljava/io/DataOutput;)I

    move-result v1

    add-int/2addr v1, v0

    .line 1439
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$Builder;->sI:Lmiui/util/DirectIndexedFile$d;

    invoke-static {v0}, Lmiui/util/DirectIndexedFile$d;->a(Lmiui/util/DirectIndexedFile$d;)[Lmiui/util/DirectIndexedFile$b;

    move-result-object v0

    aget-object v0, v0, v2

    int-to-long v4, v1

    invoke-static {v0, v4, v5}, Lmiui/util/DirectIndexedFile$b;->a(Lmiui/util/DirectIndexedFile$b;J)J

    .line 1441
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$Builder;->sJ:Ljava/util/ArrayList;

    invoke-virtual {v0, v2}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/util/DirectIndexedFile$Builder$b;

    .line 1442
    if-eqz p1, :cond_0

    .line 1443
    invoke-static {v0}, Lmiui/util/DirectIndexedFile$Builder$b;->e(Lmiui/util/DirectIndexedFile$Builder$b;)[Lmiui/util/DirectIndexedFile$a;

    move-result-object v4

    array-length v4, v4

    invoke-interface {p1, v4}, Ljava/io/DataOutput;->writeInt(I)V

    .line 1445
    :cond_0
    add-int/lit8 v1, v1, 0x4

    move v4, v1

    move v1, v3

    .line 1446
    :goto_1
    invoke-static {v0}, Lmiui/util/DirectIndexedFile$Builder$b;->e(Lmiui/util/DirectIndexedFile$Builder$b;)[Lmiui/util/DirectIndexedFile$a;

    move-result-object v5

    array-length v5, v5

    if-ge v1, v5, :cond_1

    .line 1447
    invoke-static {v0}, Lmiui/util/DirectIndexedFile$Builder$b;->e(Lmiui/util/DirectIndexedFile$Builder$b;)[Lmiui/util/DirectIndexedFile$a;

    move-result-object v5

    aget-object v5, v5, v1

    invoke-static {v5, p1}, Lmiui/util/DirectIndexedFile$a;->a(Lmiui/util/DirectIndexedFile$a;Ljava/io/DataOutput;)I

    move-result v5

    add-int/2addr v4, v5

    .line 1446
    add-int/lit8 v1, v1, 0x1

    goto :goto_1

    .line 1450
    :cond_1
    iget-object v1, p0, Lmiui/util/DirectIndexedFile$Builder;->sI:Lmiui/util/DirectIndexedFile$d;

    invoke-static {v1}, Lmiui/util/DirectIndexedFile$d;->a(Lmiui/util/DirectIndexedFile$d;)[Lmiui/util/DirectIndexedFile$b;

    move-result-object v1

    aget-object v1, v1, v2

    int-to-long v5, v4

    invoke-static {v1, v5, v6}, Lmiui/util/DirectIndexedFile$b;->b(Lmiui/util/DirectIndexedFile$b;J)J

    .line 1451
    if-eqz p1, :cond_2

    .line 1452
    invoke-static {v0}, Lmiui/util/DirectIndexedFile$Builder$b;->b(Lmiui/util/DirectIndexedFile$Builder$b;)[Lmiui/util/DirectIndexedFile$DataItemDescriptor;

    move-result-object v1

    array-length v1, v1

    invoke-interface {p1, v1}, Ljava/io/DataOutput;->writeInt(I)V

    .line 1454
    :cond_2
    add-int/lit8 v1, v4, 0x4

    move v4, v1

    move v1, v3

    .line 1455
    :goto_2
    invoke-static {v0}, Lmiui/util/DirectIndexedFile$Builder$b;->b(Lmiui/util/DirectIndexedFile$Builder$b;)[Lmiui/util/DirectIndexedFile$DataItemDescriptor;

    move-result-object v5

    array-length v5, v5

    if-ge v1, v5, :cond_3

    .line 1456
    invoke-static {v0}, Lmiui/util/DirectIndexedFile$Builder$b;->b(Lmiui/util/DirectIndexedFile$Builder$b;)[Lmiui/util/DirectIndexedFile$DataItemDescriptor;

    move-result-object v5

    aget-object v5, v5, v1

    invoke-static {v5, p1}, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->a(Lmiui/util/DirectIndexedFile$DataItemDescriptor;Ljava/io/DataOutput;)I

    move-result v5

    add-int/2addr v4, v5

    .line 1455
    add-int/lit8 v1, v1, 0x1

    goto :goto_2

    :cond_3
    move v5, v4

    move v4, v3

    .line 1459
    :goto_3
    invoke-static {v0}, Lmiui/util/DirectIndexedFile$Builder$b;->b(Lmiui/util/DirectIndexedFile$Builder$b;)[Lmiui/util/DirectIndexedFile$DataItemDescriptor;

    move-result-object v1

    array-length v1, v1

    if-ge v4, v1, :cond_4

    .line 1460
    invoke-static {v0}, Lmiui/util/DirectIndexedFile$Builder$b;->b(Lmiui/util/DirectIndexedFile$Builder$b;)[Lmiui/util/DirectIndexedFile$DataItemDescriptor;

    move-result-object v1

    aget-object v1, v1, v4

    int-to-long v6, v5

    invoke-static {v1, v6, v7}, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->a(Lmiui/util/DirectIndexedFile$DataItemDescriptor;J)J

    .line 1461
    invoke-static {v0}, Lmiui/util/DirectIndexedFile$Builder$b;->b(Lmiui/util/DirectIndexedFile$Builder$b;)[Lmiui/util/DirectIndexedFile$DataItemDescriptor;

    move-result-object v1

    aget-object v6, v1, v4

    invoke-static {v0}, Lmiui/util/DirectIndexedFile$Builder$b;->a(Lmiui/util/DirectIndexedFile$Builder$b;)Ljava/util/ArrayList;

    move-result-object v1

    invoke-virtual {v1, v4}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lmiui/util/DirectIndexedFile$Builder$a;

    invoke-static {v1}, Lmiui/util/DirectIndexedFile$Builder$a;->b(Lmiui/util/DirectIndexedFile$Builder$a;)Ljava/util/ArrayList;

    move-result-object v1

    invoke-static {v6, p1, v1}, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->a(Lmiui/util/DirectIndexedFile$DataItemDescriptor;Ljava/io/DataOutput;Ljava/util/List;)I

    move-result v1

    add-int/2addr v5, v1

    .line 1459
    add-int/lit8 v1, v4, 0x1

    move v4, v1

    goto :goto_3

    :cond_4
    move v4, v3

    move v1, v5

    .line 1464
    :goto_4
    invoke-static {v0}, Lmiui/util/DirectIndexedFile$Builder$b;->e(Lmiui/util/DirectIndexedFile$Builder$b;)[Lmiui/util/DirectIndexedFile$a;

    move-result-object v5

    array-length v5, v5

    if-ge v4, v5, :cond_c

    .line 1465
    invoke-static {v0}, Lmiui/util/DirectIndexedFile$Builder$b;->e(Lmiui/util/DirectIndexedFile$Builder$b;)[Lmiui/util/DirectIndexedFile$a;

    move-result-object v5

    aget-object v5, v5, v4

    int-to-long v6, v1

    iput-wide v6, v5, Lmiui/util/DirectIndexedFile$a;->fK:J

    .line 1466
    if-nez p1, :cond_6

    move v5, v3

    move v6, v3

    .line 1468
    :goto_5
    invoke-static {v0}, Lmiui/util/DirectIndexedFile$Builder$b;->b(Lmiui/util/DirectIndexedFile$Builder$b;)[Lmiui/util/DirectIndexedFile$DataItemDescriptor;

    move-result-object v7

    array-length v7, v7

    if-ge v5, v7, :cond_5

    .line 1469
    invoke-static {v0}, Lmiui/util/DirectIndexedFile$Builder$b;->b(Lmiui/util/DirectIndexedFile$Builder$b;)[Lmiui/util/DirectIndexedFile$DataItemDescriptor;

    move-result-object v7

    aget-object v7, v7, v5

    invoke-static {v7}, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->a(Lmiui/util/DirectIndexedFile$DataItemDescriptor;)B

    move-result v7

    add-int/2addr v6, v7

    .line 1468
    add-int/lit8 v5, v5, 0x1

    goto :goto_5

    .line 1471
    :cond_5
    invoke-static {v0}, Lmiui/util/DirectIndexedFile$Builder$b;->e(Lmiui/util/DirectIndexedFile$Builder$b;)[Lmiui/util/DirectIndexedFile$a;

    move-result-object v5

    aget-object v5, v5, v4

    iget v5, v5, Lmiui/util/DirectIndexedFile$a;->fJ:I

    invoke-static {v0}, Lmiui/util/DirectIndexedFile$Builder$b;->e(Lmiui/util/DirectIndexedFile$Builder$b;)[Lmiui/util/DirectIndexedFile$a;

    move-result-object v7

    aget-object v7, v7, v4

    iget v7, v7, Lmiui/util/DirectIndexedFile$a;->fI:I

    sub-int/2addr v5, v7

    mul-int/2addr v5, v6

    add-int/2addr v5, v1

    .line 1464
    :goto_6
    add-int/lit8 v1, v4, 0x1

    move v4, v1

    move v1, v5

    goto :goto_4

    .line 1474
    :cond_6
    invoke-static {v0}, Lmiui/util/DirectIndexedFile$Builder$b;->e(Lmiui/util/DirectIndexedFile$Builder$b;)[Lmiui/util/DirectIndexedFile$a;

    move-result-object v5

    aget-object v5, v5, v4

    iget v5, v5, Lmiui/util/DirectIndexedFile$a;->fI:I

    move v6, v1

    :goto_7
    invoke-static {v0}, Lmiui/util/DirectIndexedFile$Builder$b;->e(Lmiui/util/DirectIndexedFile$Builder$b;)[Lmiui/util/DirectIndexedFile$a;

    move-result-object v1

    aget-object v1, v1, v4

    iget v1, v1, Lmiui/util/DirectIndexedFile$a;->fJ:I

    if-ge v5, v1, :cond_f

    .line 1475
    invoke-static {v0}, Lmiui/util/DirectIndexedFile$Builder$b;->d(Lmiui/util/DirectIndexedFile$Builder$b;)Ljava/util/HashMap;

    move-result-object v1

    invoke-static {v5}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v7

    invoke-virtual {v1, v7}, Ljava/util/HashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lmiui/util/DirectIndexedFile$Builder$c;

    .line 1476
    if-nez v1, :cond_e

    .line 1477
    invoke-static {v0}, Lmiui/util/DirectIndexedFile$Builder$b;->f(Lmiui/util/DirectIndexedFile$Builder$b;)Lmiui/util/DirectIndexedFile$Builder$c;

    move-result-object v1

    move-object v7, v1

    :goto_8
    move v8, v3

    .line 1479
    :goto_9
    invoke-static {v0}, Lmiui/util/DirectIndexedFile$Builder$b;->b(Lmiui/util/DirectIndexedFile$Builder$b;)[Lmiui/util/DirectIndexedFile$DataItemDescriptor;

    move-result-object v1

    array-length v1, v1

    if-ge v8, v1, :cond_b

    .line 1480
    invoke-static {v0}, Lmiui/util/DirectIndexedFile$Builder$b;->b(Lmiui/util/DirectIndexedFile$Builder$b;)[Lmiui/util/DirectIndexedFile$DataItemDescriptor;

    move-result-object v1

    aget-object v1, v1, v8

    invoke-static {v1}, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->a(Lmiui/util/DirectIndexedFile$DataItemDescriptor;)B

    move-result v1

    const/4 v9, 0x1

    if-ne v1, v9, :cond_8

    .line 1481
    invoke-static {v7}, Lmiui/util/DirectIndexedFile$Builder$c;->c(Lmiui/util/DirectIndexedFile$Builder$c;)[Ljava/lang/Object;

    move-result-object v1

    aget-object v1, v1, v8

    check-cast v1, Ljava/lang/Integer;

    invoke-virtual {v1}, Ljava/lang/Integer;->intValue()I

    move-result v1

    invoke-interface {p1, v1}, Ljava/io/DataOutput;->writeByte(I)V

    .line 1489
    :cond_7
    :goto_a
    invoke-static {v0}, Lmiui/util/DirectIndexedFile$Builder$b;->b(Lmiui/util/DirectIndexedFile$Builder$b;)[Lmiui/util/DirectIndexedFile$DataItemDescriptor;

    move-result-object v1

    aget-object v1, v1, v8

    invoke-static {v1}, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->a(Lmiui/util/DirectIndexedFile$DataItemDescriptor;)B

    move-result v1

    add-int/2addr v6, v1

    .line 1479
    add-int/lit8 v1, v8, 0x1

    move v8, v1

    goto :goto_9

    .line 1482
    :cond_8
    invoke-static {v0}, Lmiui/util/DirectIndexedFile$Builder$b;->b(Lmiui/util/DirectIndexedFile$Builder$b;)[Lmiui/util/DirectIndexedFile$DataItemDescriptor;

    move-result-object v1

    aget-object v1, v1, v8

    invoke-static {v1}, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->a(Lmiui/util/DirectIndexedFile$DataItemDescriptor;)B

    move-result v1

    const/4 v9, 0x2

    if-ne v1, v9, :cond_9

    .line 1483
    invoke-static {v7}, Lmiui/util/DirectIndexedFile$Builder$c;->c(Lmiui/util/DirectIndexedFile$Builder$c;)[Ljava/lang/Object;

    move-result-object v1

    aget-object v1, v1, v8

    check-cast v1, Ljava/lang/Integer;

    invoke-virtual {v1}, Ljava/lang/Integer;->intValue()I

    move-result v1

    invoke-interface {p1, v1}, Ljava/io/DataOutput;->writeShort(I)V

    goto :goto_a

    .line 1484
    :cond_9
    invoke-static {v0}, Lmiui/util/DirectIndexedFile$Builder$b;->b(Lmiui/util/DirectIndexedFile$Builder$b;)[Lmiui/util/DirectIndexedFile$DataItemDescriptor;

    move-result-object v1

    aget-object v1, v1, v8

    invoke-static {v1}, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->a(Lmiui/util/DirectIndexedFile$DataItemDescriptor;)B

    move-result v1

    const/4 v9, 0x4

    if-ne v1, v9, :cond_a

    .line 1485
    invoke-static {v7}, Lmiui/util/DirectIndexedFile$Builder$c;->c(Lmiui/util/DirectIndexedFile$Builder$c;)[Ljava/lang/Object;

    move-result-object v1

    aget-object v1, v1, v8

    check-cast v1, Ljava/lang/Integer;

    invoke-virtual {v1}, Ljava/lang/Integer;->intValue()I

    move-result v1

    invoke-interface {p1, v1}, Ljava/io/DataOutput;->writeInt(I)V

    goto :goto_a

    .line 1486
    :cond_a
    invoke-static {v0}, Lmiui/util/DirectIndexedFile$Builder$b;->b(Lmiui/util/DirectIndexedFile$Builder$b;)[Lmiui/util/DirectIndexedFile$DataItemDescriptor;

    move-result-object v1

    aget-object v1, v1, v8

    invoke-static {v1}, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->a(Lmiui/util/DirectIndexedFile$DataItemDescriptor;)B

    move-result v1

    const/16 v9, 0x8

    if-ne v1, v9, :cond_7

    .line 1487
    invoke-static {v7}, Lmiui/util/DirectIndexedFile$Builder$c;->c(Lmiui/util/DirectIndexedFile$Builder$c;)[Ljava/lang/Object;

    move-result-object v1

    aget-object v1, v1, v8

    check-cast v1, Ljava/lang/Long;

    invoke-virtual {v1}, Ljava/lang/Long;->longValue()J

    move-result-wide v9

    invoke-interface {p1, v9, v10}, Ljava/io/DataOutput;->writeLong(J)V

    goto :goto_a

    .line 1474
    :cond_b
    add-int/lit8 v1, v5, 0x1

    move v5, v1

    goto/16 :goto_7

    .line 1437
    :cond_c
    add-int/lit8 v0, v2, 0x1

    move v2, v0

    move v0, v1

    goto/16 :goto_0

    .line 1495
    :cond_d
    return v0

    :cond_e
    move-object v7, v1

    goto/16 :goto_8

    :cond_f
    move v5, v6

    goto/16 :goto_6
.end method

.method private bK()V
    .locals 2

    .prologue
    .line 1390
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$Builder;->sK:Lmiui/util/DirectIndexedFile$Builder$b;

    if-nez v0, :cond_0

    .line 1391
    new-instance v0, Ljava/lang/IllegalArgumentException;

    const-string v1, "Please add a data kind before adding group"

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 1393
    :cond_0
    return-void
.end method

.method private bL()V
    .locals 2

    .prologue
    .line 1396
    invoke-direct {p0}, Lmiui/util/DirectIndexedFile$Builder;->bK()V

    .line 1397
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$Builder;->sK:Lmiui/util/DirectIndexedFile$Builder$b;

    invoke-static {v0}, Lmiui/util/DirectIndexedFile$Builder$b;->c(Lmiui/util/DirectIndexedFile$Builder$b;)Ljava/util/ArrayList;

    move-result-object v0

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    if-nez v0, :cond_0

    .line 1398
    new-instance v0, Ljava/lang/IllegalArgumentException;

    const-string v1, "Please add a data group before adding data"

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 1400
    :cond_0
    return-void
.end method

.method private bM()V
    .locals 11

    .prologue
    .line 1403
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$Builder;->sJ:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v9

    .line 1404
    new-instance v0, Lmiui/util/DirectIndexedFile$d;

    iget v1, p0, Lmiui/util/DirectIndexedFile$Builder;->sL:I

    const/4 v2, 0x0

    invoke-direct {v0, v9, v1, v2}, Lmiui/util/DirectIndexedFile$d;-><init>(IILmiui/util/DirectIndexedFile$1;)V

    iput-object v0, p0, Lmiui/util/DirectIndexedFile$Builder;->sI:Lmiui/util/DirectIndexedFile$d;

    .line 1406
    const/4 v0, 0x0

    move v8, v0

    :goto_0
    if-ge v8, v9, :cond_3

    .line 1407
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$Builder;->sJ:Ljava/util/ArrayList;

    invoke-virtual {v0, v8}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    move-object v6, v0

    check-cast v6, Lmiui/util/DirectIndexedFile$Builder$b;

    .line 1408
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$Builder;->sI:Lmiui/util/DirectIndexedFile$d;

    invoke-static {v0}, Lmiui/util/DirectIndexedFile$d;->a(Lmiui/util/DirectIndexedFile$d;)[Lmiui/util/DirectIndexedFile$b;

    move-result-object v7

    new-instance v0, Lmiui/util/DirectIndexedFile$b;

    const-wide/16 v1, 0x0

    const-wide/16 v3, 0x0

    const/4 v5, 0x0

    invoke-direct/range {v0 .. v5}, Lmiui/util/DirectIndexedFile$b;-><init>(JJLmiui/util/DirectIndexedFile$1;)V

    aput-object v0, v7, v8

    .line 1411
    const/4 v0, 0x0

    move v1, v0

    :goto_1
    invoke-static {v6}, Lmiui/util/DirectIndexedFile$Builder$b;->c(Lmiui/util/DirectIndexedFile$Builder$b;)Ljava/util/ArrayList;

    move-result-object v0

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    if-ge v1, v0, :cond_0

    .line 1412
    invoke-static {v6}, Lmiui/util/DirectIndexedFile$Builder$b;->c(Lmiui/util/DirectIndexedFile$Builder$b;)Ljava/util/ArrayList;

    move-result-object v0

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    if-nez v0, :cond_1

    .line 1417
    :cond_0
    new-array v0, v1, [Lmiui/util/DirectIndexedFile$a;

    invoke-static {v6, v0}, Lmiui/util/DirectIndexedFile$Builder$b;->a(Lmiui/util/DirectIndexedFile$Builder$b;[Lmiui/util/DirectIndexedFile$a;)[Lmiui/util/DirectIndexedFile$a;

    .line 1418
    const/4 v0, 0x0

    move v7, v0

    :goto_2
    invoke-static {v6}, Lmiui/util/DirectIndexedFile$Builder$b;->e(Lmiui/util/DirectIndexedFile$Builder$b;)[Lmiui/util/DirectIndexedFile$a;

    move-result-object v0

    array-length v0, v0

    if-ge v7, v0, :cond_2

    .line 1419
    invoke-static {v6}, Lmiui/util/DirectIndexedFile$Builder$b;->c(Lmiui/util/DirectIndexedFile$Builder$b;)Ljava/util/ArrayList;

    move-result-object v0

    invoke-virtual {v0, v7}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/util/ArrayList;

    .line 1420
    invoke-static {v0}, Ljava/util/Collections;->sort(Ljava/util/List;)V

    .line 1422
    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lmiui/util/DirectIndexedFile$Builder$c;

    invoke-static {v1}, Lmiui/util/DirectIndexedFile$Builder$c;->b(Lmiui/util/DirectIndexedFile$Builder$c;)I

    move-result v1

    .line 1423
    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v2

    add-int/lit8 v2, v2, -0x1

    invoke-virtual {v0, v2}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/util/DirectIndexedFile$Builder$c;

    invoke-static {v0}, Lmiui/util/DirectIndexedFile$Builder$c;->b(Lmiui/util/DirectIndexedFile$Builder$c;)I

    move-result v0

    add-int/lit8 v2, v0, 0x1

    .line 1424
    invoke-static {v6}, Lmiui/util/DirectIndexedFile$Builder$b;->e(Lmiui/util/DirectIndexedFile$Builder$b;)[Lmiui/util/DirectIndexedFile$a;

    move-result-object v10

    new-instance v0, Lmiui/util/DirectIndexedFile$a;

    const-wide/16 v3, 0x0

    const/4 v5, 0x0

    invoke-direct/range {v0 .. v5}, Lmiui/util/DirectIndexedFile$a;-><init>(IIJLmiui/util/DirectIndexedFile$1;)V

    aput-object v0, v10, v7

    .line 1418
    add-int/lit8 v0, v7, 0x1

    move v7, v0

    goto :goto_2

    .line 1411
    :cond_1
    add-int/lit8 v0, v1, 0x1

    move v1, v0

    goto :goto_1

    .line 1406
    :cond_2
    add-int/lit8 v0, v8, 0x1

    move v8, v0

    goto/16 :goto_0

    .line 1429
    :cond_3
    const/4 v0, 0x0

    :try_start_0
    invoke-direct {p0, v0}, Lmiui/util/DirectIndexedFile$Builder;->b(Ljava/io/DataOutput;)I
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_0

    .line 1433
    :goto_3
    return-void

    .line 1430
    :catch_0
    move-exception v0

    goto :goto_3
.end method


# virtual methods
.method public varargs add(I[Ljava/lang/Object;)V
    .locals 4

    .prologue
    .line 1282
    invoke-direct {p0}, Lmiui/util/DirectIndexedFile$Builder;->bL()V

    .line 1284
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$Builder;->sK:Lmiui/util/DirectIndexedFile$Builder$b;

    invoke-static {v0}, Lmiui/util/DirectIndexedFile$Builder$b;->b(Lmiui/util/DirectIndexedFile$Builder$b;)[Lmiui/util/DirectIndexedFile$DataItemDescriptor;

    move-result-object v0

    array-length v0, v0

    array-length v1, p2

    if-eq v0, v1, :cond_0

    .line 1285
    new-instance v0, Ljava/lang/IllegalArgumentException;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "Different number of objects inputted, "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    iget-object v2, p0, Lmiui/util/DirectIndexedFile$Builder;->sK:Lmiui/util/DirectIndexedFile$Builder$b;

    invoke-static {v2}, Lmiui/util/DirectIndexedFile$Builder$b;->b(Lmiui/util/DirectIndexedFile$Builder$b;)[Lmiui/util/DirectIndexedFile$DataItemDescriptor;

    move-result-object v2

    array-length v2, v2

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, " data expected"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 1289
    :cond_0
    const/4 v0, 0x0

    move v1, v0

    :goto_0
    array-length v0, p2

    if-ge v1, v0, :cond_7

    .line 1290
    sget-object v0, Lmiui/util/DirectIndexedFile$1;->FF:[I

    iget-object v2, p0, Lmiui/util/DirectIndexedFile$Builder;->sK:Lmiui/util/DirectIndexedFile$Builder$b;

    invoke-static {v2}, Lmiui/util/DirectIndexedFile$Builder$b;->b(Lmiui/util/DirectIndexedFile$Builder$b;)[Lmiui/util/DirectIndexedFile$DataItemDescriptor;

    move-result-object v2

    aget-object v2, v2, v1

    invoke-static {v2}, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->c(Lmiui/util/DirectIndexedFile$DataItemDescriptor;)Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;

    move-result-object v2

    invoke-virtual {v2}, Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;->ordinal()I

    move-result v2

    aget v0, v0, v2

    packed-switch v0, :pswitch_data_0

    .line 1352
    new-instance v0, Ljava/lang/IllegalArgumentException;

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "Unsupported type of objects "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v2

    const-string v3, ", "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    iget-object v3, p0, Lmiui/util/DirectIndexedFile$Builder;->sK:Lmiui/util/DirectIndexedFile$Builder$b;

    invoke-static {v3}, Lmiui/util/DirectIndexedFile$Builder$b;->b(Lmiui/util/DirectIndexedFile$Builder$b;)[Lmiui/util/DirectIndexedFile$DataItemDescriptor;

    move-result-object v3

    aget-object v1, v3, v1

    invoke-static {v1}, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->c(Lmiui/util/DirectIndexedFile$DataItemDescriptor;)Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;

    move-result-object v1

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, " expected"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 1292
    :pswitch_0
    aget-object v0, p2, v1

    instance-of v0, v0, Ljava/lang/Byte;

    if-nez v0, :cond_2

    .line 1293
    new-instance v0, Ljava/lang/IllegalArgumentException;

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "Object["

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, "] should be byte"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 1297
    :pswitch_1
    aget-object v0, p2, v1

    instance-of v0, v0, Ljava/lang/Short;

    if-nez v0, :cond_2

    .line 1298
    new-instance v0, Ljava/lang/IllegalArgumentException;

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "Object["

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, "] should be short"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 1302
    :pswitch_2
    aget-object v0, p2, v1

    instance-of v0, v0, Ljava/lang/Integer;

    if-nez v0, :cond_2

    .line 1303
    new-instance v0, Ljava/lang/IllegalArgumentException;

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "Object["

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, "] should be int"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 1307
    :pswitch_3
    aget-object v0, p2, v1

    instance-of v0, v0, Ljava/lang/Long;

    if-nez v0, :cond_2

    .line 1308
    new-instance v0, Ljava/lang/IllegalArgumentException;

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "Object["

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, "] should be long"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 1312
    :pswitch_4
    aget-object v0, p2, v1

    instance-of v0, v0, Ljava/lang/String;

    if-nez v0, :cond_1

    .line 1313
    new-instance v0, Ljava/lang/IllegalArgumentException;

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "Object["

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, "] should be String"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 1315
    :cond_1
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$Builder;->sK:Lmiui/util/DirectIndexedFile$Builder$b;

    invoke-static {v0}, Lmiui/util/DirectIndexedFile$Builder$b;->a(Lmiui/util/DirectIndexedFile$Builder$b;)Ljava/util/ArrayList;

    move-result-object v0

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/util/DirectIndexedFile$Builder$a;

    aget-object v2, p2, v1

    invoke-static {v0, v2}, Lmiui/util/DirectIndexedFile$Builder$a;->a(Lmiui/util/DirectIndexedFile$Builder$a;Ljava/lang/Object;)Ljava/lang/Integer;

    move-result-object v0

    aput-object v0, p2, v1

    .line 1316
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$Builder;->sK:Lmiui/util/DirectIndexedFile$Builder$b;

    invoke-static {v0}, Lmiui/util/DirectIndexedFile$Builder$b;->b(Lmiui/util/DirectIndexedFile$Builder$b;)[Lmiui/util/DirectIndexedFile$DataItemDescriptor;

    move-result-object v0

    aget-object v2, v0, v1

    iget-object v0, p0, Lmiui/util/DirectIndexedFile$Builder;->sK:Lmiui/util/DirectIndexedFile$Builder$b;

    invoke-static {v0}, Lmiui/util/DirectIndexedFile$Builder$b;->a(Lmiui/util/DirectIndexedFile$Builder$b;)Ljava/util/ArrayList;

    move-result-object v0

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/util/DirectIndexedFile$Builder$a;

    invoke-static {v0}, Lmiui/util/DirectIndexedFile$Builder$a;->a(Lmiui/util/DirectIndexedFile$Builder$a;)I

    move-result v0

    invoke-static {v0}, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->aq(I)B

    move-result v0

    invoke-static {v2, v0}, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->a(Lmiui/util/DirectIndexedFile$DataItemDescriptor;B)B

    .line 1289
    :cond_2
    :goto_1
    add-int/lit8 v0, v1, 0x1

    move v1, v0

    goto/16 :goto_0

    .line 1320
    :pswitch_5
    aget-object v0, p2, v1

    instance-of v0, v0, [B

    if-nez v0, :cond_3

    .line 1321
    new-instance v0, Ljava/lang/IllegalArgumentException;

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "Object["

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, "] should be byte[]"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 1323
    :cond_3
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$Builder;->sK:Lmiui/util/DirectIndexedFile$Builder$b;

    invoke-static {v0}, Lmiui/util/DirectIndexedFile$Builder$b;->a(Lmiui/util/DirectIndexedFile$Builder$b;)Ljava/util/ArrayList;

    move-result-object v0

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/util/DirectIndexedFile$Builder$a;

    aget-object v2, p2, v1

    invoke-static {v0, v2}, Lmiui/util/DirectIndexedFile$Builder$a;->a(Lmiui/util/DirectIndexedFile$Builder$a;Ljava/lang/Object;)Ljava/lang/Integer;

    move-result-object v0

    aput-object v0, p2, v1

    .line 1324
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$Builder;->sK:Lmiui/util/DirectIndexedFile$Builder$b;

    invoke-static {v0}, Lmiui/util/DirectIndexedFile$Builder$b;->b(Lmiui/util/DirectIndexedFile$Builder$b;)[Lmiui/util/DirectIndexedFile$DataItemDescriptor;

    move-result-object v0

    aget-object v2, v0, v1

    iget-object v0, p0, Lmiui/util/DirectIndexedFile$Builder;->sK:Lmiui/util/DirectIndexedFile$Builder$b;

    invoke-static {v0}, Lmiui/util/DirectIndexedFile$Builder$b;->a(Lmiui/util/DirectIndexedFile$Builder$b;)Ljava/util/ArrayList;

    move-result-object v0

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/util/DirectIndexedFile$Builder$a;

    invoke-static {v0}, Lmiui/util/DirectIndexedFile$Builder$a;->a(Lmiui/util/DirectIndexedFile$Builder$a;)I

    move-result v0

    invoke-static {v0}, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->aq(I)B

    move-result v0

    invoke-static {v2, v0}, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->a(Lmiui/util/DirectIndexedFile$DataItemDescriptor;B)B

    goto :goto_1

    .line 1328
    :pswitch_6
    aget-object v0, p2, v1

    instance-of v0, v0, [S

    if-nez v0, :cond_4

    .line 1329
    new-instance v0, Ljava/lang/IllegalArgumentException;

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "Object["

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, "] should be short[]"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 1331
    :cond_4
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$Builder;->sK:Lmiui/util/DirectIndexedFile$Builder$b;

    invoke-static {v0}, Lmiui/util/DirectIndexedFile$Builder$b;->a(Lmiui/util/DirectIndexedFile$Builder$b;)Ljava/util/ArrayList;

    move-result-object v0

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/util/DirectIndexedFile$Builder$a;

    aget-object v2, p2, v1

    invoke-static {v0, v2}, Lmiui/util/DirectIndexedFile$Builder$a;->a(Lmiui/util/DirectIndexedFile$Builder$a;Ljava/lang/Object;)Ljava/lang/Integer;

    move-result-object v0

    aput-object v0, p2, v1

    .line 1332
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$Builder;->sK:Lmiui/util/DirectIndexedFile$Builder$b;

    invoke-static {v0}, Lmiui/util/DirectIndexedFile$Builder$b;->b(Lmiui/util/DirectIndexedFile$Builder$b;)[Lmiui/util/DirectIndexedFile$DataItemDescriptor;

    move-result-object v0

    aget-object v2, v0, v1

    iget-object v0, p0, Lmiui/util/DirectIndexedFile$Builder;->sK:Lmiui/util/DirectIndexedFile$Builder$b;

    invoke-static {v0}, Lmiui/util/DirectIndexedFile$Builder$b;->a(Lmiui/util/DirectIndexedFile$Builder$b;)Ljava/util/ArrayList;

    move-result-object v0

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/util/DirectIndexedFile$Builder$a;

    invoke-static {v0}, Lmiui/util/DirectIndexedFile$Builder$a;->a(Lmiui/util/DirectIndexedFile$Builder$a;)I

    move-result v0

    invoke-static {v0}, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->aq(I)B

    move-result v0

    invoke-static {v2, v0}, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->a(Lmiui/util/DirectIndexedFile$DataItemDescriptor;B)B

    goto/16 :goto_1

    .line 1336
    :pswitch_7
    aget-object v0, p2, v1

    instance-of v0, v0, [I

    if-nez v0, :cond_5

    .line 1337
    new-instance v0, Ljava/lang/IllegalArgumentException;

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "Object["

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, "] should be int[]"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 1339
    :cond_5
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$Builder;->sK:Lmiui/util/DirectIndexedFile$Builder$b;

    invoke-static {v0}, Lmiui/util/DirectIndexedFile$Builder$b;->a(Lmiui/util/DirectIndexedFile$Builder$b;)Ljava/util/ArrayList;

    move-result-object v0

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/util/DirectIndexedFile$Builder$a;

    aget-object v2, p2, v1

    invoke-static {v0, v2}, Lmiui/util/DirectIndexedFile$Builder$a;->a(Lmiui/util/DirectIndexedFile$Builder$a;Ljava/lang/Object;)Ljava/lang/Integer;

    move-result-object v0

    aput-object v0, p2, v1

    .line 1340
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$Builder;->sK:Lmiui/util/DirectIndexedFile$Builder$b;

    invoke-static {v0}, Lmiui/util/DirectIndexedFile$Builder$b;->b(Lmiui/util/DirectIndexedFile$Builder$b;)[Lmiui/util/DirectIndexedFile$DataItemDescriptor;

    move-result-object v0

    aget-object v2, v0, v1

    iget-object v0, p0, Lmiui/util/DirectIndexedFile$Builder;->sK:Lmiui/util/DirectIndexedFile$Builder$b;

    invoke-static {v0}, Lmiui/util/DirectIndexedFile$Builder$b;->a(Lmiui/util/DirectIndexedFile$Builder$b;)Ljava/util/ArrayList;

    move-result-object v0

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/util/DirectIndexedFile$Builder$a;

    invoke-static {v0}, Lmiui/util/DirectIndexedFile$Builder$a;->a(Lmiui/util/DirectIndexedFile$Builder$a;)I

    move-result v0

    invoke-static {v0}, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->aq(I)B

    move-result v0

    invoke-static {v2, v0}, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->a(Lmiui/util/DirectIndexedFile$DataItemDescriptor;B)B

    goto/16 :goto_1

    .line 1344
    :pswitch_8
    aget-object v0, p2, v1

    instance-of v0, v0, [J

    if-nez v0, :cond_6

    .line 1345
    new-instance v0, Ljava/lang/IllegalArgumentException;

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "Object["

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, "] should be long[]"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 1347
    :cond_6
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$Builder;->sK:Lmiui/util/DirectIndexedFile$Builder$b;

    invoke-static {v0}, Lmiui/util/DirectIndexedFile$Builder$b;->a(Lmiui/util/DirectIndexedFile$Builder$b;)Ljava/util/ArrayList;

    move-result-object v0

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/util/DirectIndexedFile$Builder$a;

    aget-object v2, p2, v1

    invoke-static {v0, v2}, Lmiui/util/DirectIndexedFile$Builder$a;->a(Lmiui/util/DirectIndexedFile$Builder$a;Ljava/lang/Object;)Ljava/lang/Integer;

    move-result-object v0

    aput-object v0, p2, v1

    .line 1348
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$Builder;->sK:Lmiui/util/DirectIndexedFile$Builder$b;

    invoke-static {v0}, Lmiui/util/DirectIndexedFile$Builder$b;->b(Lmiui/util/DirectIndexedFile$Builder$b;)[Lmiui/util/DirectIndexedFile$DataItemDescriptor;

    move-result-object v0

    aget-object v2, v0, v1

    iget-object v0, p0, Lmiui/util/DirectIndexedFile$Builder;->sK:Lmiui/util/DirectIndexedFile$Builder$b;

    invoke-static {v0}, Lmiui/util/DirectIndexedFile$Builder$b;->a(Lmiui/util/DirectIndexedFile$Builder$b;)Ljava/util/ArrayList;

    move-result-object v0

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/util/DirectIndexedFile$Builder$a;

    invoke-static {v0}, Lmiui/util/DirectIndexedFile$Builder$a;->a(Lmiui/util/DirectIndexedFile$Builder$a;)I

    move-result v0

    invoke-static {v0}, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->aq(I)B

    move-result v0

    invoke-static {v2, v0}, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->a(Lmiui/util/DirectIndexedFile$DataItemDescriptor;B)B

    goto/16 :goto_1

    .line 1357
    :cond_7
    new-instance v1, Lmiui/util/DirectIndexedFile$Builder$c;

    const/4 v0, 0x0

    invoke-direct {v1, p0, p1, p2, v0}, Lmiui/util/DirectIndexedFile$Builder$c;-><init>(Lmiui/util/DirectIndexedFile$Builder;I[Ljava/lang/Object;Lmiui/util/DirectIndexedFile$1;)V

    .line 1358
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$Builder;->sK:Lmiui/util/DirectIndexedFile$Builder$b;

    invoke-static {v0}, Lmiui/util/DirectIndexedFile$Builder$b;->d(Lmiui/util/DirectIndexedFile$Builder$b;)Ljava/util/HashMap;

    move-result-object v0

    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    invoke-virtual {v0, v2, v1}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 1359
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$Builder;->sK:Lmiui/util/DirectIndexedFile$Builder$b;

    invoke-static {v0}, Lmiui/util/DirectIndexedFile$Builder$b;->c(Lmiui/util/DirectIndexedFile$Builder$b;)Ljava/util/ArrayList;

    move-result-object v0

    iget-object v2, p0, Lmiui/util/DirectIndexedFile$Builder;->sK:Lmiui/util/DirectIndexedFile$Builder$b;

    invoke-static {v2}, Lmiui/util/DirectIndexedFile$Builder$b;->c(Lmiui/util/DirectIndexedFile$Builder$b;)Ljava/util/ArrayList;

    move-result-object v2

    invoke-virtual {v2}, Ljava/util/ArrayList;->size()I

    move-result v2

    add-int/lit8 v2, v2, -0x1

    invoke-virtual {v0, v2}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/util/ArrayList;

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 1360
    return-void

    .line 1290
    nop

    :pswitch_data_0
    .packed-switch 0x1
        :pswitch_4
        :pswitch_5
        :pswitch_6
        :pswitch_7
        :pswitch_8
        :pswitch_0
        :pswitch_1
        :pswitch_2
        :pswitch_3
    .end packed-switch
.end method

.method public addGroup([I[[Ljava/lang/Object;)V
    .locals 3

    .prologue
    .line 1264
    invoke-direct {p0}, Lmiui/util/DirectIndexedFile$Builder;->bK()V

    .line 1266
    array-length v0, p1

    array-length v1, p2

    if-ne v0, v1, :cond_0

    .line 1267
    invoke-virtual {p0}, Lmiui/util/DirectIndexedFile$Builder;->newGroup()V

    .line 1268
    const/4 v0, 0x0

    :goto_0
    array-length v1, p1

    if-ge v0, v1, :cond_1

    .line 1269
    aget v1, p1, v0

    aget-object v2, p2, v0

    invoke-virtual {p0, v1, v2}, Lmiui/util/DirectIndexedFile$Builder;->add(I[Ljava/lang/Object;)V

    .line 1268
    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    .line 1272
    :cond_0
    new-instance v0, Ljava/lang/IllegalArgumentException;

    const-string v1, "Different number between indexes and objects"

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 1274
    :cond_1
    return-void
.end method

.method public varargs addKind([Ljava/lang/Object;)V
    .locals 11

    .prologue
    const/4 v9, 0x1

    const/4 v3, 0x0

    const/4 v7, 0x0

    .line 1198
    new-instance v0, Lmiui/util/DirectIndexedFile$Builder$b;

    array-length v1, p1

    invoke-direct {v0, v1, v7}, Lmiui/util/DirectIndexedFile$Builder$b;-><init>(ILmiui/util/DirectIndexedFile$1;)V

    iput-object v0, p0, Lmiui/util/DirectIndexedFile$Builder;->sK:Lmiui/util/DirectIndexedFile$Builder$b;

    .line 1199
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$Builder;->sJ:Ljava/util/ArrayList;

    iget-object v1, p0, Lmiui/util/DirectIndexedFile$Builder;->sK:Lmiui/util/DirectIndexedFile$Builder$b;

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    move v8, v3

    .line 1201
    :goto_0
    array-length v0, p1

    if-ge v8, v0, :cond_9

    .line 1202
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$Builder;->sK:Lmiui/util/DirectIndexedFile$Builder$b;

    invoke-static {v0}, Lmiui/util/DirectIndexedFile$Builder$b;->a(Lmiui/util/DirectIndexedFile$Builder$b;)Ljava/util/ArrayList;

    move-result-object v0

    new-instance v1, Lmiui/util/DirectIndexedFile$Builder$a;

    invoke-direct {v1, p0, v7}, Lmiui/util/DirectIndexedFile$Builder$a;-><init>(Lmiui/util/DirectIndexedFile$Builder;Lmiui/util/DirectIndexedFile$1;)V

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 1206
    aget-object v0, p1, v8

    instance-of v0, v0, Ljava/lang/Byte;

    if-eqz v0, :cond_0

    .line 1207
    sget-object v1, Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;->RB:Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;

    .line 1209
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$Builder;->sK:Lmiui/util/DirectIndexedFile$Builder$b;

    invoke-static {v0}, Lmiui/util/DirectIndexedFile$Builder$b;->a(Lmiui/util/DirectIndexedFile$Builder$b;)Ljava/util/ArrayList;

    move-result-object v0

    invoke-virtual {v0, v8}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/util/DirectIndexedFile$Builder$a;

    aget-object v2, p1, v8

    invoke-static {v0, v2}, Lmiui/util/DirectIndexedFile$Builder$a;->a(Lmiui/util/DirectIndexedFile$Builder$a;Ljava/lang/Object;)Ljava/lang/Integer;

    move v2, v9

    .line 1241
    :goto_1
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$Builder;->sK:Lmiui/util/DirectIndexedFile$Builder$b;

    invoke-static {v0}, Lmiui/util/DirectIndexedFile$Builder$b;->b(Lmiui/util/DirectIndexedFile$Builder$b;)[Lmiui/util/DirectIndexedFile$DataItemDescriptor;

    move-result-object v10

    new-instance v0, Lmiui/util/DirectIndexedFile$DataItemDescriptor;

    const-wide/16 v5, 0x0

    move v4, v3

    invoke-direct/range {v0 .. v7}, Lmiui/util/DirectIndexedFile$DataItemDescriptor;-><init>(Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;BBBJLmiui/util/DirectIndexedFile$1;)V

    aput-object v0, v10, v8

    .line 1201
    add-int/lit8 v0, v8, 0x1

    move v8, v0

    goto :goto_0

    .line 1210
    :cond_0
    aget-object v0, p1, v8

    instance-of v0, v0, Ljava/lang/Short;

    if-eqz v0, :cond_1

    .line 1211
    sget-object v1, Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;->RC:Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;

    .line 1212
    const/4 v2, 0x2

    .line 1213
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$Builder;->sK:Lmiui/util/DirectIndexedFile$Builder$b;

    invoke-static {v0}, Lmiui/util/DirectIndexedFile$Builder$b;->a(Lmiui/util/DirectIndexedFile$Builder$b;)Ljava/util/ArrayList;

    move-result-object v0

    invoke-virtual {v0, v8}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/util/DirectIndexedFile$Builder$a;

    aget-object v4, p1, v8

    invoke-static {v0, v4}, Lmiui/util/DirectIndexedFile$Builder$a;->a(Lmiui/util/DirectIndexedFile$Builder$a;Ljava/lang/Object;)Ljava/lang/Integer;

    goto :goto_1

    .line 1214
    :cond_1
    aget-object v0, p1, v8

    instance-of v0, v0, Ljava/lang/Integer;

    if-eqz v0, :cond_2

    .line 1215
    sget-object v1, Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;->RD:Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;

    .line 1216
    const/4 v2, 0x4

    .line 1217
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$Builder;->sK:Lmiui/util/DirectIndexedFile$Builder$b;

    invoke-static {v0}, Lmiui/util/DirectIndexedFile$Builder$b;->a(Lmiui/util/DirectIndexedFile$Builder$b;)Ljava/util/ArrayList;

    move-result-object v0

    invoke-virtual {v0, v8}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/util/DirectIndexedFile$Builder$a;

    aget-object v4, p1, v8

    invoke-static {v0, v4}, Lmiui/util/DirectIndexedFile$Builder$a;->a(Lmiui/util/DirectIndexedFile$Builder$a;Ljava/lang/Object;)Ljava/lang/Integer;

    goto :goto_1

    .line 1218
    :cond_2
    aget-object v0, p1, v8

    instance-of v0, v0, Ljava/lang/Long;

    if-eqz v0, :cond_3

    .line 1219
    sget-object v1, Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;->RE:Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;

    .line 1220
    const/16 v2, 0x8

    .line 1221
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$Builder;->sK:Lmiui/util/DirectIndexedFile$Builder$b;

    invoke-static {v0}, Lmiui/util/DirectIndexedFile$Builder$b;->a(Lmiui/util/DirectIndexedFile$Builder$b;)Ljava/util/ArrayList;

    move-result-object v0

    invoke-virtual {v0, v8}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/util/DirectIndexedFile$Builder$a;

    aget-object v4, p1, v8

    invoke-static {v0, v4}, Lmiui/util/DirectIndexedFile$Builder$a;->a(Lmiui/util/DirectIndexedFile$Builder$a;Ljava/lang/Object;)Ljava/lang/Integer;

    goto :goto_1

    .line 1222
    :cond_3
    aget-object v0, p1, v8

    instance-of v0, v0, Ljava/lang/String;

    if-eqz v0, :cond_4

    .line 1223
    sget-object v1, Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;->RF:Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;

    .line 1224
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$Builder;->sK:Lmiui/util/DirectIndexedFile$Builder$b;

    invoke-static {v0}, Lmiui/util/DirectIndexedFile$Builder$b;->a(Lmiui/util/DirectIndexedFile$Builder$b;)Ljava/util/ArrayList;

    move-result-object v0

    invoke-virtual {v0, v8}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/util/DirectIndexedFile$Builder$a;

    aget-object v2, p1, v8

    invoke-static {v0, v2}, Lmiui/util/DirectIndexedFile$Builder$a;->a(Lmiui/util/DirectIndexedFile$Builder$a;Ljava/lang/Object;)Ljava/lang/Integer;

    move-result-object v0

    aput-object v0, p1, v8

    move v2, v9

    goto/16 :goto_1

    .line 1225
    :cond_4
    aget-object v0, p1, v8

    instance-of v0, v0, [B

    if-eqz v0, :cond_5

    .line 1226
    sget-object v1, Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;->RG:Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;

    .line 1227
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$Builder;->sK:Lmiui/util/DirectIndexedFile$Builder$b;

    invoke-static {v0}, Lmiui/util/DirectIndexedFile$Builder$b;->a(Lmiui/util/DirectIndexedFile$Builder$b;)Ljava/util/ArrayList;

    move-result-object v0

    invoke-virtual {v0, v8}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/util/DirectIndexedFile$Builder$a;

    aget-object v2, p1, v8

    invoke-static {v0, v2}, Lmiui/util/DirectIndexedFile$Builder$a;->a(Lmiui/util/DirectIndexedFile$Builder$a;Ljava/lang/Object;)Ljava/lang/Integer;

    move-result-object v0

    aput-object v0, p1, v8

    move v2, v9

    goto/16 :goto_1

    .line 1228
    :cond_5
    aget-object v0, p1, v8

    instance-of v0, v0, [S

    if-eqz v0, :cond_6

    .line 1229
    sget-object v1, Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;->RH:Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;

    .line 1230
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$Builder;->sK:Lmiui/util/DirectIndexedFile$Builder$b;

    invoke-static {v0}, Lmiui/util/DirectIndexedFile$Builder$b;->a(Lmiui/util/DirectIndexedFile$Builder$b;)Ljava/util/ArrayList;

    move-result-object v0

    invoke-virtual {v0, v8}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/util/DirectIndexedFile$Builder$a;

    aget-object v2, p1, v8

    invoke-static {v0, v2}, Lmiui/util/DirectIndexedFile$Builder$a;->a(Lmiui/util/DirectIndexedFile$Builder$a;Ljava/lang/Object;)Ljava/lang/Integer;

    move-result-object v0

    aput-object v0, p1, v8

    move v2, v9

    goto/16 :goto_1

    .line 1231
    :cond_6
    aget-object v0, p1, v8

    instance-of v0, v0, [I

    if-eqz v0, :cond_7

    .line 1232
    sget-object v1, Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;->RI:Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;

    .line 1233
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$Builder;->sK:Lmiui/util/DirectIndexedFile$Builder$b;

    invoke-static {v0}, Lmiui/util/DirectIndexedFile$Builder$b;->a(Lmiui/util/DirectIndexedFile$Builder$b;)Ljava/util/ArrayList;

    move-result-object v0

    invoke-virtual {v0, v8}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/util/DirectIndexedFile$Builder$a;

    aget-object v2, p1, v8

    invoke-static {v0, v2}, Lmiui/util/DirectIndexedFile$Builder$a;->a(Lmiui/util/DirectIndexedFile$Builder$a;Ljava/lang/Object;)Ljava/lang/Integer;

    move-result-object v0

    aput-object v0, p1, v8

    move v2, v9

    goto/16 :goto_1

    .line 1234
    :cond_7
    aget-object v0, p1, v8

    instance-of v0, v0, [J

    if-eqz v0, :cond_8

    .line 1235
    sget-object v1, Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;->RJ:Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;

    .line 1236
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$Builder;->sK:Lmiui/util/DirectIndexedFile$Builder$b;

    invoke-static {v0}, Lmiui/util/DirectIndexedFile$Builder$b;->a(Lmiui/util/DirectIndexedFile$Builder$b;)Ljava/util/ArrayList;

    move-result-object v0

    invoke-virtual {v0, v8}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/util/DirectIndexedFile$Builder$a;

    aget-object v2, p1, v8

    invoke-static {v0, v2}, Lmiui/util/DirectIndexedFile$Builder$a;->a(Lmiui/util/DirectIndexedFile$Builder$a;Ljava/lang/Object;)Ljava/lang/Integer;

    move-result-object v0

    aput-object v0, p1, v8

    move v2, v9

    goto/16 :goto_1

    .line 1238
    :cond_8
    new-instance v0, Ljava/lang/IllegalArgumentException;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "Unsupported type of the ["

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, v8}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, "] argument"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 1245
    :cond_9
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$Builder;->sK:Lmiui/util/DirectIndexedFile$Builder$b;

    new-instance v1, Lmiui/util/DirectIndexedFile$Builder$c;

    const/4 v2, -0x1

    invoke-direct {v1, p0, v2, p1, v7}, Lmiui/util/DirectIndexedFile$Builder$c;-><init>(Lmiui/util/DirectIndexedFile$Builder;I[Ljava/lang/Object;Lmiui/util/DirectIndexedFile$1;)V

    invoke-static {v0, v1}, Lmiui/util/DirectIndexedFile$Builder$b;->a(Lmiui/util/DirectIndexedFile$Builder$b;Lmiui/util/DirectIndexedFile$Builder$c;)Lmiui/util/DirectIndexedFile$Builder$c;

    .line 1246
    return-void
.end method

.method public newGroup()V
    .locals 2

    .prologue
    .line 1252
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$Builder;->sK:Lmiui/util/DirectIndexedFile$Builder$b;

    invoke-static {v0}, Lmiui/util/DirectIndexedFile$Builder$b;->c(Lmiui/util/DirectIndexedFile$Builder$b;)Ljava/util/ArrayList;

    move-result-object v0

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lmiui/util/DirectIndexedFile$Builder;->sK:Lmiui/util/DirectIndexedFile$Builder$b;

    invoke-static {v0}, Lmiui/util/DirectIndexedFile$Builder$b;->c(Lmiui/util/DirectIndexedFile$Builder$b;)Ljava/util/ArrayList;

    move-result-object v0

    iget-object v1, p0, Lmiui/util/DirectIndexedFile$Builder;->sK:Lmiui/util/DirectIndexedFile$Builder$b;

    invoke-static {v1}, Lmiui/util/DirectIndexedFile$Builder$b;->c(Lmiui/util/DirectIndexedFile$Builder$b;)Ljava/util/ArrayList;

    move-result-object v1

    invoke-virtual {v1}, Ljava/util/ArrayList;->size()I

    move-result v1

    add-int/lit8 v1, v1, -0x1

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    if-eqz v0, :cond_1

    .line 1254
    :cond_0
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$Builder;->sK:Lmiui/util/DirectIndexedFile$Builder$b;

    invoke-static {v0}, Lmiui/util/DirectIndexedFile$Builder$b;->c(Lmiui/util/DirectIndexedFile$Builder$b;)Ljava/util/ArrayList;

    move-result-object v0

    new-instance v1, Ljava/util/ArrayList;

    invoke-direct {v1}, Ljava/util/ArrayList;-><init>()V

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 1256
    :cond_1
    return-void
.end method

.method public write(Ljava/lang/String;)V
    .locals 4
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 1368
    invoke-direct {p0}, Lmiui/util/DirectIndexedFile$Builder;->bM()V

    .line 1370
    const/4 v2, 0x0

    .line 1373
    :try_start_0
    new-instance v1, Ljava/io/DataOutputStream;

    new-instance v0, Ljava/io/BufferedOutputStream;

    new-instance v3, Ljava/io/FileOutputStream;

    invoke-direct {v3, p1}, Ljava/io/FileOutputStream;-><init>(Ljava/lang/String;)V

    invoke-direct {v0, v3}, Ljava/io/BufferedOutputStream;-><init>(Ljava/io/OutputStream;)V

    invoke-direct {v1, v0}, Ljava/io/DataOutputStream;-><init>(Ljava/io/OutputStream;)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 1374
    :try_start_1
    invoke-direct {p0, v1}, Lmiui/util/DirectIndexedFile$Builder;->b(Ljava/io/DataOutput;)I
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_1

    .line 1377
    if-eqz v1, :cond_0

    .line 1378
    invoke-virtual {v1}, Ljava/io/DataOutputStream;->close()V

    .line 1387
    :cond_0
    return-void

    .line 1377
    :catchall_0
    move-exception v0

    move-object v1, v2

    :goto_0
    if-eqz v1, :cond_1

    .line 1378
    invoke-virtual {v1}, Ljava/io/DataOutputStream;->close()V

    .line 1382
    :cond_1
    new-instance v1, Ljava/io/File;

    invoke-direct {v1, p1}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    invoke-virtual {v1}, Ljava/io/File;->delete()Z

    move-result v1

    if-eqz v1, :cond_2

    .line 1383
    sget-object v1, Ljava/lang/System;->err:Ljava/io/PrintStream;

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "Cannot delete file "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/io/PrintStream;->println(Ljava/lang/String;)V

    :cond_2
    throw v0

    .line 1377
    :catchall_1
    move-exception v0

    goto :goto_0
.end method
