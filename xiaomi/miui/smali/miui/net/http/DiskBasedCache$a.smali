.class Lmiui/net/http/DiskBasedCache$a;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/net/http/DiskBasedCache;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0xa
    name = "a"
.end annotation


# instance fields
.field public FG:J

.field public FH:J

.field private volatile FL:Z

.field private volatile FN:I

.field public FP:Ljava/lang/String;

.field public contentEncoding:Ljava/lang/String;

.field public contentType:Ljava/lang/String;

.field public etag:Ljava/lang/String;

.field public file:Ljava/io/File;

.field public responseHeaders:Ljava/util/Map;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map",
            "<",
            "Ljava/lang/String;",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation
.end field

.field public serverDate:J

.field public softTtl:J

.field public ttl:J


# direct methods
.method private constructor <init>()V
    .locals 0

    .prologue
    .line 382
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 383
    return-void
.end method

.method static a(Ljava/io/InputStream;)I
    .locals 2
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 578
    const/4 v0, 0x0

    .line 579
    invoke-static {p0}, Lmiui/net/http/DiskBasedCache$a;->read(Ljava/io/InputStream;)I

    move-result v1

    or-int/2addr v0, v1

    .line 580
    invoke-static {p0}, Lmiui/net/http/DiskBasedCache$a;->read(Ljava/io/InputStream;)I

    move-result v1

    shl-int/lit8 v1, v1, 0x8

    or-int/2addr v0, v1

    .line 581
    invoke-static {p0}, Lmiui/net/http/DiskBasedCache$a;->read(Ljava/io/InputStream;)I

    move-result v1

    shl-int/lit8 v1, v1, 0x10

    or-int/2addr v0, v1

    .line 582
    invoke-static {p0}, Lmiui/net/http/DiskBasedCache$a;->read(Ljava/io/InputStream;)I

    move-result v1

    shl-int/lit8 v1, v1, 0x18

    or-int/2addr v0, v1

    .line 583
    return v0
.end method

.method public static a(Ljava/io/File;Ljava/lang/String;Lmiui/net/http/Cache$Entry;)Lmiui/net/http/DiskBasedCache$a;
    .locals 10

    .prologue
    const/4 v1, 0x0

    .line 437
    .line 438
    iget-object v3, p2, Lmiui/net/http/Cache$Entry;->data:Ljava/io/InputStream;

    .line 441
    const/4 v0, 0x0

    :try_start_0
    iput-object v0, p2, Lmiui/net/http/Cache$Entry;->data:Ljava/io/InputStream;

    .line 443
    new-instance v2, Ljava/io/FileOutputStream;

    invoke-direct {v2, p0}, Ljava/io/FileOutputStream;-><init>(Ljava/io/File;)V
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_1
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 444
    const v0, 0x20140228

    :try_start_1
    invoke-static {v2, v0}, Lmiui/net/http/DiskBasedCache$a;->a(Ljava/io/OutputStream;I)V

    .line 445
    invoke-static {v2, p1}, Lmiui/net/http/DiskBasedCache$a;->a(Ljava/io/OutputStream;Ljava/lang/String;)V

    .line 446
    iget-object v0, p2, Lmiui/net/http/Cache$Entry;->etag:Ljava/lang/String;

    if-nez v0, :cond_2

    const-string v0, ""

    :goto_0
    invoke-static {v2, v0}, Lmiui/net/http/DiskBasedCache$a;->a(Ljava/io/OutputStream;Ljava/lang/String;)V

    .line 447
    iget-object v0, p2, Lmiui/net/http/Cache$Entry;->contentType:Ljava/lang/String;

    if-nez v0, :cond_3

    const-string v0, ""

    :goto_1
    invoke-static {v2, v0}, Lmiui/net/http/DiskBasedCache$a;->a(Ljava/io/OutputStream;Ljava/lang/String;)V

    .line 448
    iget-object v0, p2, Lmiui/net/http/Cache$Entry;->contentEncoding:Ljava/lang/String;

    if-nez v0, :cond_4

    const-string v0, ""

    :goto_2
    invoke-static {v2, v0}, Lmiui/net/http/DiskBasedCache$a;->a(Ljava/io/OutputStream;Ljava/lang/String;)V

    .line 449
    iget-wide v4, p2, Lmiui/net/http/Cache$Entry;->serverDate:J

    invoke-static {v2, v4, v5}, Lmiui/net/http/DiskBasedCache$a;->a(Ljava/io/OutputStream;J)V

    .line 450
    iget-wide v4, p2, Lmiui/net/http/Cache$Entry;->ttl:J

    invoke-static {v2, v4, v5}, Lmiui/net/http/DiskBasedCache$a;->a(Ljava/io/OutputStream;J)V

    .line 451
    iget-wide v4, p2, Lmiui/net/http/Cache$Entry;->softTtl:J

    invoke-static {v2, v4, v5}, Lmiui/net/http/DiskBasedCache$a;->a(Ljava/io/OutputStream;J)V

    .line 452
    iget-object v0, p2, Lmiui/net/http/Cache$Entry;->responseHeaders:Ljava/util/Map;

    invoke-static {v0, v2}, Lmiui/net/http/DiskBasedCache$a;->a(Ljava/util/Map;Ljava/io/OutputStream;)V

    .line 453
    invoke-virtual {v2}, Ljava/io/FileOutputStream;->flush()V

    .line 455
    new-instance v0, Lmiui/net/http/DiskBasedCache$a;

    invoke-direct {v0}, Lmiui/net/http/DiskBasedCache$a;-><init>()V

    .line 456
    iput-object p1, v0, Lmiui/net/http/DiskBasedCache$a;->FP:Ljava/lang/String;

    .line 457
    iget-object v4, p2, Lmiui/net/http/Cache$Entry;->etag:Ljava/lang/String;

    iput-object v4, v0, Lmiui/net/http/DiskBasedCache$a;->etag:Ljava/lang/String;

    .line 458
    iget-object v4, p2, Lmiui/net/http/Cache$Entry;->contentType:Ljava/lang/String;

    iput-object v4, v0, Lmiui/net/http/DiskBasedCache$a;->contentType:Ljava/lang/String;

    .line 459
    iget-object v4, p2, Lmiui/net/http/Cache$Entry;->contentEncoding:Ljava/lang/String;

    iput-object v4, v0, Lmiui/net/http/DiskBasedCache$a;->contentEncoding:Ljava/lang/String;

    .line 460
    iget-wide v4, p2, Lmiui/net/http/Cache$Entry;->serverDate:J

    iput-wide v4, v0, Lmiui/net/http/DiskBasedCache$a;->serverDate:J

    .line 461
    iget-wide v4, p2, Lmiui/net/http/Cache$Entry;->ttl:J

    iput-wide v4, v0, Lmiui/net/http/DiskBasedCache$a;->ttl:J

    .line 462
    iget-wide v4, p2, Lmiui/net/http/Cache$Entry;->softTtl:J

    iput-wide v4, v0, Lmiui/net/http/DiskBasedCache$a;->softTtl:J

    .line 463
    iget-object v4, p2, Lmiui/net/http/Cache$Entry;->responseHeaders:Ljava/util/Map;

    iput-object v4, v0, Lmiui/net/http/DiskBasedCache$a;->responseHeaders:Ljava/util/Map;

    .line 464
    iput-object p0, v0, Lmiui/net/http/DiskBasedCache$a;->file:Ljava/io/File;

    .line 465
    invoke-virtual {v2}, Ljava/io/FileOutputStream;->getChannel()Ljava/nio/channels/FileChannel;

    move-result-object v4

    invoke-virtual {v4}, Ljava/nio/channels/FileChannel;->position()J

    move-result-wide v4

    iput-wide v4, v0, Lmiui/net/http/DiskBasedCache$a;->FH:J

    .line 467
    invoke-static {v3, v2}, Lmiui/util/IOUtils;->copy(Ljava/io/InputStream;Ljava/io/OutputStream;)J

    .line 468
    invoke-virtual {v2}, Ljava/io/FileOutputStream;->flush()V

    .line 470
    invoke-virtual {v2}, Ljava/io/FileOutputStream;->getChannel()Ljava/nio/channels/FileChannel;

    move-result-object v4

    invoke-virtual {v4}, Ljava/nio/channels/FileChannel;->position()J

    move-result-wide v4

    iput-wide v4, v0, Lmiui/net/http/DiskBasedCache$a;->FG:J

    .line 471
    iget-wide v4, p2, Lmiui/net/http/Cache$Entry;->length:J

    const-wide/16 v6, 0x0

    cmp-long v4, v4, v6

    if-gtz v4, :cond_5

    .line 472
    iget-wide v4, v0, Lmiui/net/http/DiskBasedCache$a;->FG:J

    iget-wide v6, v0, Lmiui/net/http/DiskBasedCache$a;->FH:J

    sub-long/2addr v4, v6

    iput-wide v4, p2, Lmiui/net/http/Cache$Entry;->length:J

    .line 476
    :cond_0
    invoke-virtual {v2}, Ljava/io/FileOutputStream;->close()V
    :try_end_1
    .catch Ljava/io/IOException; {:try_start_1 .. :try_end_1} :catch_0
    .catchall {:try_start_1 .. :try_end_1} :catchall_1

    .line 479
    :try_start_2
    new-instance v2, Lmiui/net/http/DiskBasedCache$b;

    invoke-direct {v2, v0}, Lmiui/net/http/DiskBasedCache$b;-><init>(Lmiui/net/http/DiskBasedCache$a;)V

    iput-object v2, p2, Lmiui/net/http/Cache$Entry;->data:Ljava/io/InputStream;
    :try_end_2
    .catch Ljava/io/IOException; {:try_start_2 .. :try_end_2} :catch_1
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    .line 484
    invoke-static {v3}, Lmiui/util/IOUtils;->closeQuietly(Ljava/io/InputStream;)V

    .line 485
    invoke-static {v1}, Lmiui/util/IOUtils;->closeQuietly(Ljava/io/OutputStream;)V

    .line 487
    if-nez v0, :cond_1

    .line 488
    invoke-virtual {p0}, Ljava/io/File;->delete()Z

    move-result v1

    if-nez v1, :cond_1

    .line 489
    const-string v1, "DisBasedCache"

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "Cannot delete file "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-static {v1, v2}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 493
    :cond_1
    :goto_3
    return-object v0

    .line 446
    :cond_2
    :try_start_3
    iget-object v0, p2, Lmiui/net/http/Cache$Entry;->etag:Ljava/lang/String;

    goto/16 :goto_0

    .line 447
    :cond_3
    iget-object v0, p2, Lmiui/net/http/Cache$Entry;->contentType:Ljava/lang/String;

    goto/16 :goto_1

    .line 448
    :cond_4
    iget-object v0, p2, Lmiui/net/http/Cache$Entry;->contentEncoding:Ljava/lang/String;

    goto/16 :goto_2

    .line 473
    :cond_5
    iget-wide v4, p2, Lmiui/net/http/Cache$Entry;->length:J

    iget-wide v6, v0, Lmiui/net/http/DiskBasedCache$a;->FG:J

    iget-wide v8, v0, Lmiui/net/http/DiskBasedCache$a;->FH:J

    sub-long/2addr v6, v8

    cmp-long v4, v4, v6

    if-eqz v4, :cond_0

    .line 474
    new-instance v4, Ljava/io/IOException;

    new-instance v5, Ljava/lang/StringBuilder;

    invoke-direct {v5}, Ljava/lang/StringBuilder;-><init>()V

    const-string v6, "length not match "

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v5

    iget-wide v6, p2, Lmiui/net/http/Cache$Entry;->length:J

    invoke-virtual {v5, v6, v7}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    move-result-object v5

    const-string v6, ":"

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v5

    iget-wide v6, v0, Lmiui/net/http/DiskBasedCache$a;->FG:J

    iget-wide v8, v0, Lmiui/net/http/DiskBasedCache$a;->FH:J

    sub-long/2addr v6, v8

    invoke-virtual {v5, v6, v7}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-direct {v4, v0}, Ljava/io/IOException;-><init>(Ljava/lang/String;)V

    throw v4
    :try_end_3
    .catch Ljava/io/IOException; {:try_start_3 .. :try_end_3} :catch_0
    .catchall {:try_start_3 .. :try_end_3} :catchall_1

    .line 481
    :catch_0
    move-exception v0

    .line 482
    :goto_4
    :try_start_4
    const-string v4, "DisBasedCache"

    new-instance v5, Ljava/lang/StringBuilder;

    invoke-direct {v5}, Ljava/lang/StringBuilder;-><init>()V

    const-string v6, "Cannot save cache to disk file "

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v5

    invoke-virtual {v5, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v5

    invoke-virtual {v5}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v5

    invoke-static {v4, v5, v0}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    :try_end_4
    .catchall {:try_start_4 .. :try_end_4} :catchall_1

    .line 484
    invoke-static {v3}, Lmiui/util/IOUtils;->closeQuietly(Ljava/io/InputStream;)V

    .line 485
    invoke-static {v2}, Lmiui/util/IOUtils;->closeQuietly(Ljava/io/OutputStream;)V

    .line 487
    if-nez v1, :cond_7

    .line 488
    invoke-virtual {p0}, Ljava/io/File;->delete()Z

    move-result v0

    if-nez v0, :cond_7

    .line 489
    const-string v0, "DisBasedCache"

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "Cannot delete file "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-static {v0, v2}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    move-object v0, v1

    goto/16 :goto_3

    .line 484
    :catchall_0
    move-exception v0

    move-object v2, v1

    :goto_5
    invoke-static {v3}, Lmiui/util/IOUtils;->closeQuietly(Ljava/io/InputStream;)V

    .line 485
    invoke-static {v2}, Lmiui/util/IOUtils;->closeQuietly(Ljava/io/OutputStream;)V

    .line 487
    if-nez v1, :cond_6

    .line 488
    invoke-virtual {p0}, Ljava/io/File;->delete()Z

    move-result v1

    if-nez v1, :cond_6

    .line 489
    const-string v1, "DisBasedCache"

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "Cannot delete file "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-static {v1, v2}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    :cond_6
    throw v0

    .line 484
    :catchall_1
    move-exception v0

    goto :goto_5

    .line 481
    :catch_1
    move-exception v0

    move-object v2, v1

    goto :goto_4

    :cond_7
    move-object v0, v1

    goto/16 :goto_3
.end method

.method static a(Ljava/io/OutputStream;I)V
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 571
    and-int/lit16 v0, p1, 0xff

    invoke-virtual {p0, v0}, Ljava/io/OutputStream;->write(I)V

    .line 572
    shr-int/lit8 v0, p1, 0x8

    and-int/lit16 v0, v0, 0xff

    invoke-virtual {p0, v0}, Ljava/io/OutputStream;->write(I)V

    .line 573
    shr-int/lit8 v0, p1, 0x10

    and-int/lit16 v0, v0, 0xff

    invoke-virtual {p0, v0}, Ljava/io/OutputStream;->write(I)V

    .line 574
    shr-int/lit8 v0, p1, 0x18

    and-int/lit16 v0, v0, 0xff

    invoke-virtual {p0, v0}, Ljava/io/OutputStream;->write(I)V

    .line 575
    return-void
.end method

.method static a(Ljava/io/OutputStream;J)V
    .locals 2
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 587
    long-to-int v0, p1

    int-to-byte v0, v0

    invoke-virtual {p0, v0}, Ljava/io/OutputStream;->write(I)V

    .line 588
    const/16 v0, 0x8

    ushr-long v0, p1, v0

    long-to-int v0, v0

    int-to-byte v0, v0

    invoke-virtual {p0, v0}, Ljava/io/OutputStream;->write(I)V

    .line 589
    const/16 v0, 0x10

    ushr-long v0, p1, v0

    long-to-int v0, v0

    int-to-byte v0, v0

    invoke-virtual {p0, v0}, Ljava/io/OutputStream;->write(I)V

    .line 590
    const/16 v0, 0x18

    ushr-long v0, p1, v0

    long-to-int v0, v0

    int-to-byte v0, v0

    invoke-virtual {p0, v0}, Ljava/io/OutputStream;->write(I)V

    .line 591
    const/16 v0, 0x20

    ushr-long v0, p1, v0

    long-to-int v0, v0

    int-to-byte v0, v0

    invoke-virtual {p0, v0}, Ljava/io/OutputStream;->write(I)V

    .line 592
    const/16 v0, 0x28

    ushr-long v0, p1, v0

    long-to-int v0, v0

    int-to-byte v0, v0

    invoke-virtual {p0, v0}, Ljava/io/OutputStream;->write(I)V

    .line 593
    const/16 v0, 0x30

    ushr-long v0, p1, v0

    long-to-int v0, v0

    int-to-byte v0, v0

    invoke-virtual {p0, v0}, Ljava/io/OutputStream;->write(I)V

    .line 594
    const/16 v0, 0x38

    ushr-long v0, p1, v0

    long-to-int v0, v0

    int-to-byte v0, v0

    invoke-virtual {p0, v0}, Ljava/io/OutputStream;->write(I)V

    .line 595
    return-void
.end method

.method static a(Ljava/io/OutputStream;Ljava/lang/String;)V
    .locals 3
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 611
    const-string v0, "UTF-8"

    invoke-virtual {p1, v0}, Ljava/lang/String;->getBytes(Ljava/lang/String;)[B

    move-result-object v0

    .line 612
    array-length v1, v0

    int-to-long v1, v1

    invoke-static {p0, v1, v2}, Lmiui/net/http/DiskBasedCache$a;->a(Ljava/io/OutputStream;J)V

    .line 613
    const/4 v1, 0x0

    array-length v2, v0

    invoke-virtual {p0, v0, v1, v2}, Ljava/io/OutputStream;->write([BII)V

    .line 614
    return-void
.end method

.method static a(Ljava/util/Map;Ljava/io/OutputStream;)V
    .locals 3
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/Map",
            "<",
            "Ljava/lang/String;",
            "Ljava/lang/String;",
            ">;",
            "Ljava/io/OutputStream;",
            ")V"
        }
    .end annotation

    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 631
    if-eqz p0, :cond_0

    invoke-interface {p0}, Ljava/util/Map;->size()I

    move-result v0

    if-eqz v0, :cond_0

    .line 632
    invoke-interface {p0}, Ljava/util/Map;->size()I

    move-result v0

    invoke-static {p1, v0}, Lmiui/net/http/DiskBasedCache$a;->a(Ljava/io/OutputStream;I)V

    .line 633
    invoke-interface {p0}, Ljava/util/Map;->entrySet()Ljava/util/Set;

    move-result-object v0

    invoke-interface {v0}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v2

    :goto_0
    invoke-interface {v2}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_1

    invoke-interface {v2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/util/Map$Entry;

    .line 634
    invoke-interface {v0}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/lang/String;

    invoke-static {p1, v1}, Lmiui/net/http/DiskBasedCache$a;->a(Ljava/io/OutputStream;Ljava/lang/String;)V

    .line 635
    invoke-interface {v0}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/String;

    invoke-static {p1, v0}, Lmiui/net/http/DiskBasedCache$a;->a(Ljava/io/OutputStream;Ljava/lang/String;)V

    goto :goto_0

    .line 638
    :cond_0
    const/4 v0, 0x0

    invoke-static {p1, v0}, Lmiui/net/http/DiskBasedCache$a;->a(Ljava/io/OutputStream;I)V

    .line 640
    :cond_1
    return-void
.end method

.method static b(Ljava/io/InputStream;)J
    .locals 7
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    const-wide/16 v5, 0xff

    .line 598
    const-wide/16 v0, 0x0

    .line 599
    invoke-static {p0}, Lmiui/net/http/DiskBasedCache$a;->read(Ljava/io/InputStream;)I

    move-result v2

    int-to-long v2, v2

    and-long/2addr v2, v5

    or-long/2addr v0, v2

    .line 600
    invoke-static {p0}, Lmiui/net/http/DiskBasedCache$a;->read(Ljava/io/InputStream;)I

    move-result v2

    int-to-long v2, v2

    and-long/2addr v2, v5

    const/16 v4, 0x8

    shl-long/2addr v2, v4

    or-long/2addr v0, v2

    .line 601
    invoke-static {p0}, Lmiui/net/http/DiskBasedCache$a;->read(Ljava/io/InputStream;)I

    move-result v2

    int-to-long v2, v2

    and-long/2addr v2, v5

    const/16 v4, 0x10

    shl-long/2addr v2, v4

    or-long/2addr v0, v2

    .line 602
    invoke-static {p0}, Lmiui/net/http/DiskBasedCache$a;->read(Ljava/io/InputStream;)I

    move-result v2

    int-to-long v2, v2

    and-long/2addr v2, v5

    const/16 v4, 0x18

    shl-long/2addr v2, v4

    or-long/2addr v0, v2

    .line 603
    invoke-static {p0}, Lmiui/net/http/DiskBasedCache$a;->read(Ljava/io/InputStream;)I

    move-result v2

    int-to-long v2, v2

    and-long/2addr v2, v5

    const/16 v4, 0x20

    shl-long/2addr v2, v4

    or-long/2addr v0, v2

    .line 604
    invoke-static {p0}, Lmiui/net/http/DiskBasedCache$a;->read(Ljava/io/InputStream;)I

    move-result v2

    int-to-long v2, v2

    and-long/2addr v2, v5

    const/16 v4, 0x28

    shl-long/2addr v2, v4

    or-long/2addr v0, v2

    .line 605
    invoke-static {p0}, Lmiui/net/http/DiskBasedCache$a;->read(Ljava/io/InputStream;)I

    move-result v2

    int-to-long v2, v2

    and-long/2addr v2, v5

    const/16 v4, 0x30

    shl-long/2addr v2, v4

    or-long/2addr v0, v2

    .line 606
    invoke-static {p0}, Lmiui/net/http/DiskBasedCache$a;->read(Ljava/io/InputStream;)I

    move-result v2

    int-to-long v2, v2

    and-long/2addr v2, v5

    const/16 v4, 0x38

    shl-long/2addr v2, v4

    or-long/2addr v0, v2

    .line 607
    return-wide v0
.end method

.method public static b(Ljava/io/File;)Lmiui/net/http/DiskBasedCache$a;
    .locals 5

    .prologue
    const/4 v0, 0x0

    .line 391
    .line 394
    :try_start_0
    new-instance v2, Ljava/io/FileInputStream;

    invoke-direct {v2, p0}, Ljava/io/FileInputStream;-><init>(Ljava/io/File;)V
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 395
    :try_start_1
    invoke-static {v2}, Lmiui/net/http/DiskBasedCache$a;->a(Ljava/io/InputStream;)I
    :try_end_1
    .catch Ljava/io/IOException; {:try_start_1 .. :try_end_1} :catch_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_1

    move-result v1

    const v3, 0x20140228

    if-eq v1, v3, :cond_0

    .line 424
    invoke-static {v2}, Lmiui/util/IOUtils;->closeQuietly(Ljava/io/InputStream;)V

    :goto_0
    return-object v0

    .line 399
    :cond_0
    :try_start_2
    new-instance v1, Lmiui/net/http/DiskBasedCache$a;

    invoke-direct {v1}, Lmiui/net/http/DiskBasedCache$a;-><init>()V

    .line 400
    invoke-static {v2}, Lmiui/net/http/DiskBasedCache$a;->c(Ljava/io/InputStream;)Ljava/lang/String;

    move-result-object v3

    iput-object v3, v1, Lmiui/net/http/DiskBasedCache$a;->FP:Ljava/lang/String;

    .line 401
    invoke-static {v2}, Lmiui/net/http/DiskBasedCache$a;->c(Ljava/io/InputStream;)Ljava/lang/String;

    move-result-object v3

    iput-object v3, v1, Lmiui/net/http/DiskBasedCache$a;->etag:Ljava/lang/String;

    .line 402
    const-string v3, ""

    iget-object v4, v1, Lmiui/net/http/DiskBasedCache$a;->etag:Ljava/lang/String;

    invoke-virtual {v3, v4}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_1

    .line 403
    const/4 v3, 0x0

    iput-object v3, v1, Lmiui/net/http/DiskBasedCache$a;->etag:Ljava/lang/String;

    .line 405
    :cond_1
    invoke-static {v2}, Lmiui/net/http/DiskBasedCache$a;->c(Ljava/io/InputStream;)Ljava/lang/String;

    move-result-object v3

    iput-object v3, v1, Lmiui/net/http/DiskBasedCache$a;->contentType:Ljava/lang/String;

    .line 406
    const-string v3, ""

    iget-object v4, v1, Lmiui/net/http/DiskBasedCache$a;->contentType:Ljava/lang/String;

    invoke-virtual {v3, v4}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_2

    .line 407
    const/4 v3, 0x0

    iput-object v3, v1, Lmiui/net/http/DiskBasedCache$a;->contentType:Ljava/lang/String;

    .line 409
    :cond_2
    invoke-static {v2}, Lmiui/net/http/DiskBasedCache$a;->c(Ljava/io/InputStream;)Ljava/lang/String;

    move-result-object v3

    iput-object v3, v1, Lmiui/net/http/DiskBasedCache$a;->contentEncoding:Ljava/lang/String;

    .line 410
    const-string v3, ""

    iget-object v4, v1, Lmiui/net/http/DiskBasedCache$a;->contentEncoding:Ljava/lang/String;

    invoke-virtual {v3, v4}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_3

    .line 411
    const/4 v3, 0x0

    iput-object v3, v1, Lmiui/net/http/DiskBasedCache$a;->contentEncoding:Ljava/lang/String;

    .line 413
    :cond_3
    invoke-static {v2}, Lmiui/net/http/DiskBasedCache$a;->b(Ljava/io/InputStream;)J

    move-result-wide v3

    iput-wide v3, v1, Lmiui/net/http/DiskBasedCache$a;->serverDate:J

    .line 414
    invoke-static {v2}, Lmiui/net/http/DiskBasedCache$a;->b(Ljava/io/InputStream;)J

    move-result-wide v3

    iput-wide v3, v1, Lmiui/net/http/DiskBasedCache$a;->ttl:J

    .line 415
    invoke-static {v2}, Lmiui/net/http/DiskBasedCache$a;->b(Ljava/io/InputStream;)J

    move-result-wide v3

    iput-wide v3, v1, Lmiui/net/http/DiskBasedCache$a;->softTtl:J

    .line 416
    invoke-static {v2}, Lmiui/net/http/DiskBasedCache$a;->d(Ljava/io/InputStream;)Ljava/util/Map;

    move-result-object v3

    iput-object v3, v1, Lmiui/net/http/DiskBasedCache$a;->responseHeaders:Ljava/util/Map;

    .line 417
    invoke-virtual {v2}, Ljava/io/FileInputStream;->getChannel()Ljava/nio/channels/FileChannel;

    move-result-object v3

    invoke-virtual {v3}, Ljava/nio/channels/FileChannel;->position()J

    move-result-wide v3

    iput-wide v3, v1, Lmiui/net/http/DiskBasedCache$a;->FH:J

    .line 418
    iput-object p0, v1, Lmiui/net/http/DiskBasedCache$a;->file:Ljava/io/File;

    .line 419
    invoke-virtual {p0}, Ljava/io/File;->length()J

    move-result-wide v3

    iput-wide v3, v1, Lmiui/net/http/DiskBasedCache$a;->FG:J
    :try_end_2
    .catch Ljava/io/IOException; {:try_start_2 .. :try_end_2} :catch_1
    .catchall {:try_start_2 .. :try_end_2} :catchall_1

    .line 424
    invoke-static {v2}, Lmiui/util/IOUtils;->closeQuietly(Ljava/io/InputStream;)V

    move-object v0, v1

    goto :goto_0

    .line 421
    :catch_0
    move-exception v1

    move-object v1, v0

    .line 424
    :goto_1
    invoke-static {v1}, Lmiui/util/IOUtils;->closeQuietly(Ljava/io/InputStream;)V

    goto :goto_0

    :catchall_0
    move-exception v1

    move-object v2, v0

    move-object v0, v1

    :goto_2
    invoke-static {v2}, Lmiui/util/IOUtils;->closeQuietly(Ljava/io/InputStream;)V

    throw v0

    :catchall_1
    move-exception v0

    goto :goto_2

    .line 421
    :catch_1
    move-exception v1

    move-object v1, v2

    goto :goto_1
.end method

.method static c(Ljava/io/InputStream;)Ljava/lang/String;
    .locals 5
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 617
    invoke-static {p0}, Lmiui/net/http/DiskBasedCache$a;->b(Ljava/io/InputStream;)J

    move-result-wide v0

    long-to-int v1, v0

    .line 618
    new-array v2, v1, [B

    .line 620
    const/4 v0, 0x0

    .line 621
    :goto_0
    if-ge v0, v1, :cond_0

    sub-int v3, v1, v0

    invoke-virtual {p0, v2, v0, v3}, Ljava/io/InputStream;->read([BII)I

    move-result v3

    const/4 v4, -0x1

    if-eq v3, v4, :cond_0

    .line 622
    add-int/2addr v0, v3

    goto :goto_0

    .line 624
    :cond_0
    if-eq v0, v1, :cond_1

    .line 625
    new-instance v2, Ljava/io/IOException;

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    const-string v4, "Expected "

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v3, " bytes but read "

    invoke-virtual {v1, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v0

    const-string v1, " bytes"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-direct {v2, v0}, Ljava/io/IOException;-><init>(Ljava/lang/String;)V

    throw v2

    .line 627
    :cond_1
    new-instance v0, Ljava/lang/String;

    const-string v1, "UTF-8"

    invoke-direct {v0, v2, v1}, Ljava/lang/String;-><init>([BLjava/lang/String;)V

    return-object v0
.end method

.method static d(Ljava/io/InputStream;)Ljava/util/Map;
    .locals 5
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/io/InputStream;",
            ")",
            "Ljava/util/Map",
            "<",
            "Ljava/lang/String;",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation

    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 643
    invoke-static {p0}, Lmiui/net/http/DiskBasedCache$a;->a(Ljava/io/InputStream;)I

    move-result v1

    .line 644
    new-instance v2, Ljava/util/HashMap;

    invoke-direct {v2}, Ljava/util/HashMap;-><init>()V

    .line 645
    const/4 v0, 0x0

    :goto_0
    if-ge v0, v1, :cond_0

    .line 646
    invoke-static {p0}, Lmiui/net/http/DiskBasedCache$a;->c(Ljava/io/InputStream;)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/String;->intern()Ljava/lang/String;

    move-result-object v3

    invoke-static {p0}, Lmiui/net/http/DiskBasedCache$a;->c(Ljava/io/InputStream;)Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/String;->intern()Ljava/lang/String;

    move-result-object v4

    invoke-interface {v2, v3, v4}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 645
    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    .line 648
    :cond_0
    return-object v2
.end method

.method private static read(Ljava/io/InputStream;)I
    .locals 2
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 563
    invoke-virtual {p0}, Ljava/io/InputStream;->read()I

    move-result v0

    .line 564
    const/4 v1, -0x1

    if-ne v0, v1, :cond_0

    .line 565
    new-instance v0, Ljava/io/EOFException;

    invoke-direct {v0}, Ljava/io/EOFException;-><init>()V

    throw v0

    .line 567
    :cond_0
    return v0
.end method


# virtual methods
.method public declared-synchronized acquire()V
    .locals 1

    .prologue
    .line 524
    monitor-enter p0

    :try_start_0
    iget v0, p0, Lmiui/net/http/DiskBasedCache$a;->FN:I

    add-int/lit8 v0, v0, 0x1

    iput v0, p0, Lmiui/net/http/DiskBasedCache$a;->FN:I
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 525
    monitor-exit p0

    return-void

    .line 524
    :catchall_0
    move-exception v0

    monitor-exit p0

    throw v0
.end method

.method public declared-synchronized delete()V
    .locals 3

    .prologue
    .line 550
    monitor-enter p0

    :try_start_0
    iget v0, p0, Lmiui/net/http/DiskBasedCache$a;->FN:I

    if-lez v0, :cond_1

    .line 551
    const/4 v0, 0x1

    iput-boolean v0, p0, Lmiui/net/http/DiskBasedCache$a;->FL:Z
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 557
    :cond_0
    :goto_0
    monitor-exit p0

    return-void

    .line 553
    :cond_1
    :try_start_1
    iget-object v0, p0, Lmiui/net/http/DiskBasedCache$a;->file:Ljava/io/File;

    invoke-virtual {v0}, Ljava/io/File;->delete()Z

    move-result v0

    if-nez v0, :cond_0

    .line 554
    const-string v0, "DisBasedCache"

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "Cannot delete file "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    iget-object v2, p0, Lmiui/net/http/DiskBasedCache$a;->file:Ljava/io/File;

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    goto :goto_0

    .line 550
    :catchall_0
    move-exception v0

    monitor-exit p0

    throw v0
.end method

.method public do()Lmiui/net/http/Cache$Entry;
    .locals 6

    .prologue
    const/4 v0, 0x0

    .line 501
    :try_start_0
    new-instance v1, Lmiui/net/http/Cache$Entry;

    invoke-direct {v1}, Lmiui/net/http/Cache$Entry;-><init>()V

    .line 502
    new-instance v2, Ljava/io/FileInputStream;

    iget-object v3, p0, Lmiui/net/http/DiskBasedCache$a;->file:Ljava/io/File;

    invoke-direct {v2, v3}, Ljava/io/FileInputStream;-><init>(Ljava/io/File;)V

    iput-object v2, v1, Lmiui/net/http/Cache$Entry;->data:Ljava/io/InputStream;

    .line 503
    iget-object v2, v1, Lmiui/net/http/Cache$Entry;->data:Ljava/io/InputStream;

    iget-wide v3, p0, Lmiui/net/http/DiskBasedCache$a;->FH:J

    invoke-virtual {v2, v3, v4}, Ljava/io/InputStream;->skip(J)J

    move-result-wide v2

    iget-wide v4, p0, Lmiui/net/http/DiskBasedCache$a;->FH:J

    cmp-long v2, v2, v4

    if-eqz v2, :cond_0

    .line 516
    :goto_0
    return-object v0

    .line 506
    :cond_0
    iget-wide v2, p0, Lmiui/net/http/DiskBasedCache$a;->FG:J

    iget-wide v4, p0, Lmiui/net/http/DiskBasedCache$a;->FH:J

    sub-long/2addr v2, v4

    iput-wide v2, v1, Lmiui/net/http/Cache$Entry;->length:J

    .line 507
    iget-object v2, p0, Lmiui/net/http/DiskBasedCache$a;->etag:Ljava/lang/String;

    iput-object v2, v1, Lmiui/net/http/Cache$Entry;->etag:Ljava/lang/String;

    .line 508
    iget-object v2, p0, Lmiui/net/http/DiskBasedCache$a;->contentType:Ljava/lang/String;

    iput-object v2, v1, Lmiui/net/http/Cache$Entry;->contentType:Ljava/lang/String;

    .line 509
    iget-object v2, p0, Lmiui/net/http/DiskBasedCache$a;->contentEncoding:Ljava/lang/String;

    iput-object v2, v1, Lmiui/net/http/Cache$Entry;->contentEncoding:Ljava/lang/String;

    .line 510
    iget-wide v2, p0, Lmiui/net/http/DiskBasedCache$a;->serverDate:J

    iput-wide v2, v1, Lmiui/net/http/Cache$Entry;->serverDate:J

    .line 511
    iget-wide v2, p0, Lmiui/net/http/DiskBasedCache$a;->ttl:J

    iput-wide v2, v1, Lmiui/net/http/Cache$Entry;->ttl:J

    .line 512
    iget-wide v2, p0, Lmiui/net/http/DiskBasedCache$a;->softTtl:J

    iput-wide v2, v1, Lmiui/net/http/Cache$Entry;->softTtl:J

    .line 513
    iget-object v2, p0, Lmiui/net/http/DiskBasedCache$a;->responseHeaders:Ljava/util/Map;

    iput-object v2, v1, Lmiui/net/http/Cache$Entry;->responseHeaders:Ljava/util/Map;
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_0

    move-object v0, v1

    .line 514
    goto :goto_0

    .line 515
    :catch_0
    move-exception v1

    goto :goto_0
.end method

.method public declared-synchronized dp()Z
    .locals 1

    .prologue
    .line 531
    monitor-enter p0

    :try_start_0
    iget v0, p0, Lmiui/net/http/DiskBasedCache$a;->FN:I
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    if-lez v0, :cond_0

    const/4 v0, 0x1

    :goto_0
    monitor-exit p0

    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0

    :catchall_0
    move-exception v0

    monitor-exit p0

    throw v0
.end method

.method public declared-synchronized release()V
    .locals 3

    .prologue
    .line 538
    monitor-enter p0

    :try_start_0
    iget v0, p0, Lmiui/net/http/DiskBasedCache$a;->FN:I

    add-int/lit8 v0, v0, -0x1

    iput v0, p0, Lmiui/net/http/DiskBasedCache$a;->FN:I

    .line 539
    iget v0, p0, Lmiui/net/http/DiskBasedCache$a;->FN:I

    if-gtz v0, :cond_0

    iget-boolean v0, p0, Lmiui/net/http/DiskBasedCache$a;->FL:Z

    if-eqz v0, :cond_0

    .line 540
    iget-object v0, p0, Lmiui/net/http/DiskBasedCache$a;->file:Ljava/io/File;

    invoke-virtual {v0}, Ljava/io/File;->delete()Z

    move-result v0

    if-nez v0, :cond_0

    .line 541
    const-string v0, "DisBasedCache"

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "Cannot delete file "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    iget-object v2, p0, Lmiui/net/http/DiskBasedCache$a;->file:Ljava/io/File;

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 544
    :cond_0
    monitor-exit p0

    return-void

    .line 538
    :catchall_0
    move-exception v0

    monitor-exit p0

    throw v0
.end method
