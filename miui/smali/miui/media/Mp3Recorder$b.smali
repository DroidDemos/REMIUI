.class Lmiui/media/Mp3Recorder$b;
.super Ljava/lang/Thread;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/media/Mp3Recorder;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x2
    name = "b"
.end annotation


# instance fields
.field final synthetic pA:Lmiui/media/Mp3Recorder;


# direct methods
.method private constructor <init>(Lmiui/media/Mp3Recorder;)V
    .locals 0

    .prologue
    .line 466
    iput-object p1, p0, Lmiui/media/Mp3Recorder$b;->pA:Lmiui/media/Mp3Recorder;

    invoke-direct {p0}, Ljava/lang/Thread;-><init>()V

    return-void
.end method

.method synthetic constructor <init>(Lmiui/media/Mp3Recorder;Lmiui/media/Mp3Recorder$1;)V
    .locals 0

    .prologue
    .line 466
    invoke-direct {p0, p1}, Lmiui/media/Mp3Recorder$b;-><init>(Lmiui/media/Mp3Recorder;)V

    return-void
.end method


# virtual methods
.method public run()V
    .locals 8

    .prologue
    const/4 v7, 0x2

    const/4 v6, 0x0

    .line 469
    const/16 v0, -0x13

    invoke-static {v0}, Landroid/os/Process;->setThreadPriority(I)V

    .line 470
    const-string v0, "Mp3Recorder"

    const-string v1, "RecordingThread started"

    invoke-static {v0, v1}, Landroid/util/Log;->v(Ljava/lang/String;Ljava/lang/String;)I

    .line 471
    iget-object v0, p0, Lmiui/media/Mp3Recorder$b;->pA:Lmiui/media/Mp3Recorder;

    invoke-static {v0}, Lmiui/media/Mp3Recorder;->a(Lmiui/media/Mp3Recorder;)I

    move-result v0

    if-eq v0, v7, :cond_0

    .line 472
    const-string v0, "Mp3Recorder"

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "Error illegal state: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    iget-object v2, p0, Lmiui/media/Mp3Recorder$b;->pA:Lmiui/media/Mp3Recorder;

    invoke-static {v2}, Lmiui/media/Mp3Recorder;->a(Lmiui/media/Mp3Recorder;)I

    move-result v2

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 473
    iget-object v0, p0, Lmiui/media/Mp3Recorder$b;->pA:Lmiui/media/Mp3Recorder;

    const/4 v1, 0x1

    invoke-static {v0, v1}, Lmiui/media/Mp3Recorder;->a(Lmiui/media/Mp3Recorder;I)V

    .line 527
    :goto_0
    return-void

    .line 476
    :cond_0
    iget-object v0, p0, Lmiui/media/Mp3Recorder$b;->pA:Lmiui/media/Mp3Recorder;

    invoke-static {v0}, Lmiui/media/Mp3Recorder;->b(Lmiui/media/Mp3Recorder;)Ljava/io/FileOutputStream;

    move-result-object v0

    if-nez v0, :cond_2

    .line 477
    const-string v0, "Mp3Recorder"

    const-string v1, "Error out put stream not ready"

    invoke-static {v0, v1}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 478
    iget-object v0, p0, Lmiui/media/Mp3Recorder$b;->pA:Lmiui/media/Mp3Recorder;

    invoke-static {v0, v7}, Lmiui/media/Mp3Recorder;->a(Lmiui/media/Mp3Recorder;I)V

    goto :goto_0

    .line 489
    :cond_1
    iget-object v0, p0, Lmiui/media/Mp3Recorder$b;->pA:Lmiui/media/Mp3Recorder;

    iget-object v1, p0, Lmiui/media/Mp3Recorder$b;->pA:Lmiui/media/Mp3Recorder;

    iget-object v2, p0, Lmiui/media/Mp3Recorder$b;->pA:Lmiui/media/Mp3Recorder;

    invoke-static {v2}, Lmiui/media/Mp3Recorder;->c(Lmiui/media/Mp3Recorder;)[S

    move-result-object v2

    invoke-static {v1, v2, v3}, Lmiui/media/Mp3Recorder;->a(Lmiui/media/Mp3Recorder;[SI)I

    move-result v1

    invoke-static {v0, v1}, Lmiui/media/Mp3Recorder;->b(Lmiui/media/Mp3Recorder;I)I

    .line 491
    iget-object v0, p0, Lmiui/media/Mp3Recorder$b;->pA:Lmiui/media/Mp3Recorder;

    invoke-static {v0}, Lmiui/media/Mp3Recorder;->e(Lmiui/media/Mp3Recorder;)I

    move-result v0

    const/16 v1, 0x10

    if-ne v0, v1, :cond_4

    .line 492
    iget-object v0, p0, Lmiui/media/Mp3Recorder$b;->pA:Lmiui/media/Mp3Recorder;

    invoke-static {v0}, Lmiui/media/Mp3Recorder;->g(Lmiui/media/Mp3Recorder;)Lmiui/media/Mp3Encoder;

    move-result-object v0

    iget-object v1, p0, Lmiui/media/Mp3Recorder$b;->pA:Lmiui/media/Mp3Recorder;

    invoke-static {v1}, Lmiui/media/Mp3Recorder;->c(Lmiui/media/Mp3Recorder;)[S

    move-result-object v1

    iget-object v2, p0, Lmiui/media/Mp3Recorder$b;->pA:Lmiui/media/Mp3Recorder;

    invoke-static {v2}, Lmiui/media/Mp3Recorder;->c(Lmiui/media/Mp3Recorder;)[S

    move-result-object v2

    iget-object v4, p0, Lmiui/media/Mp3Recorder$b;->pA:Lmiui/media/Mp3Recorder;

    invoke-static {v4}, Lmiui/media/Mp3Recorder;->f(Lmiui/media/Mp3Recorder;)[B

    move-result-object v4

    iget-object v5, p0, Lmiui/media/Mp3Recorder$b;->pA:Lmiui/media/Mp3Recorder;

    invoke-static {v5}, Lmiui/media/Mp3Recorder;->f(Lmiui/media/Mp3Recorder;)[B

    move-result-object v5

    array-length v5, v5

    invoke-virtual/range {v0 .. v5}, Lmiui/media/Mp3Encoder;->encode([S[SI[BI)I

    move-result v0

    .line 496
    :goto_1
    if-gtz v0, :cond_6

    .line 497
    if-nez v0, :cond_5

    .line 498
    const-string v0, "Mp3Recorder"

    const-string v1, "Not a complete frame samples to encode: skiped"

    invoke-static {v0, v1}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 481
    :cond_2
    iget-object v0, p0, Lmiui/media/Mp3Recorder$b;->pA:Lmiui/media/Mp3Recorder;

    invoke-static {v0}, Lmiui/media/Mp3Recorder;->a(Lmiui/media/Mp3Recorder;)I

    move-result v0

    if-ne v0, v7, :cond_3

    .line 482
    iget-object v0, p0, Lmiui/media/Mp3Recorder$b;->pA:Lmiui/media/Mp3Recorder;

    invoke-static {v0}, Lmiui/media/Mp3Recorder;->d(Lmiui/media/Mp3Recorder;)Landroid/media/AudioRecord;

    move-result-object v0

    iget-object v1, p0, Lmiui/media/Mp3Recorder$b;->pA:Lmiui/media/Mp3Recorder;

    invoke-static {v1}, Lmiui/media/Mp3Recorder;->c(Lmiui/media/Mp3Recorder;)[S

    move-result-object v1

    iget-object v2, p0, Lmiui/media/Mp3Recorder$b;->pA:Lmiui/media/Mp3Recorder;

    invoke-static {v2}, Lmiui/media/Mp3Recorder;->c(Lmiui/media/Mp3Recorder;)[S

    move-result-object v2

    array-length v2, v2

    invoke-virtual {v0, v1, v6, v2}, Landroid/media/AudioRecord;->read([SII)I

    move-result v3

    .line 483
    iget-object v0, p0, Lmiui/media/Mp3Recorder$b;->pA:Lmiui/media/Mp3Recorder;

    int-to-long v1, v3

    invoke-static {v0, v1, v2}, Lmiui/media/Mp3Recorder;->a(Lmiui/media/Mp3Recorder;J)J

    .line 484
    if-gtz v3, :cond_1

    .line 485
    const-string v0, "Mp3Recorder"

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "Error record sample failed, read size: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, v3}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 486
    iget-object v0, p0, Lmiui/media/Mp3Recorder$b;->pA:Lmiui/media/Mp3Recorder;

    const/4 v1, 0x3

    invoke-static {v0, v1}, Lmiui/media/Mp3Recorder;->a(Lmiui/media/Mp3Recorder;I)V

    .line 525
    :cond_3
    :goto_2
    iget-object v0, p0, Lmiui/media/Mp3Recorder$b;->pA:Lmiui/media/Mp3Recorder;

    invoke-static {v0, v6}, Lmiui/media/Mp3Recorder;->b(Lmiui/media/Mp3Recorder;I)I

    .line 526
    const-string v0, "Mp3Recorder"

    const-string v1, "RecordingThread stoped"

    invoke-static {v0, v1}, Landroid/util/Log;->v(Ljava/lang/String;Ljava/lang/String;)I

    goto/16 :goto_0

    .line 494
    :cond_4
    iget-object v0, p0, Lmiui/media/Mp3Recorder$b;->pA:Lmiui/media/Mp3Recorder;

    invoke-static {v0}, Lmiui/media/Mp3Recorder;->g(Lmiui/media/Mp3Recorder;)Lmiui/media/Mp3Encoder;

    move-result-object v0

    iget-object v1, p0, Lmiui/media/Mp3Recorder$b;->pA:Lmiui/media/Mp3Recorder;

    invoke-static {v1}, Lmiui/media/Mp3Recorder;->c(Lmiui/media/Mp3Recorder;)[S

    move-result-object v1

    iget-object v2, p0, Lmiui/media/Mp3Recorder$b;->pA:Lmiui/media/Mp3Recorder;

    invoke-static {v2}, Lmiui/media/Mp3Recorder;->h(Lmiui/media/Mp3Recorder;)I

    move-result v2

    div-int v2, v3, v2

    iget-object v4, p0, Lmiui/media/Mp3Recorder$b;->pA:Lmiui/media/Mp3Recorder;

    invoke-static {v4}, Lmiui/media/Mp3Recorder;->f(Lmiui/media/Mp3Recorder;)[B

    move-result-object v4

    iget-object v5, p0, Lmiui/media/Mp3Recorder$b;->pA:Lmiui/media/Mp3Recorder;

    invoke-static {v5}, Lmiui/media/Mp3Recorder;->f(Lmiui/media/Mp3Recorder;)[B

    move-result-object v5

    array-length v5, v5

    invoke-virtual {v0, v1, v2, v4, v5}, Lmiui/media/Mp3Encoder;->encodeInterleaved([SI[BI)I

    move-result v0

    goto/16 :goto_1

    .line 501
    :cond_5
    const-string v1, "Mp3Recorder"

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v4, "Error encode PCM failed, encode size: "

    invoke-virtual {v2, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v0

    const-string v2, " read size: "

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0, v3}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-static {v1, v0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 502
    iget-object v0, p0, Lmiui/media/Mp3Recorder$b;->pA:Lmiui/media/Mp3Recorder;

    invoke-static {v0}, Lmiui/media/Mp3Recorder;->i(Lmiui/media/Mp3Recorder;)Lmiui/media/Mp3Recorder$RecordingErrorListener;

    move-result-object v0

    if-eqz v0, :cond_3

    .line 503
    iget-object v0, p0, Lmiui/media/Mp3Recorder$b;->pA:Lmiui/media/Mp3Recorder;

    const/4 v1, 0x4

    invoke-static {v0, v1}, Lmiui/media/Mp3Recorder;->a(Lmiui/media/Mp3Recorder;I)V

    goto :goto_2

    .line 508
    :cond_6
    iget-object v1, p0, Lmiui/media/Mp3Recorder$b;->pA:Lmiui/media/Mp3Recorder;

    invoke-static {v1}, Lmiui/media/Mp3Recorder;->j(Lmiui/media/Mp3Recorder;)Ljava/io/File;

    move-result-object v1

    invoke-virtual {v1}, Ljava/io/File;->exists()Z

    move-result v1

    if-nez v1, :cond_7

    .line 509
    iget-object v0, p0, Lmiui/media/Mp3Recorder$b;->pA:Lmiui/media/Mp3Recorder;

    const/16 v1, 0x8

    invoke-static {v0, v1}, Lmiui/media/Mp3Recorder;->a(Lmiui/media/Mp3Recorder;I)V

    goto :goto_2

    .line 513
    :cond_7
    :try_start_0
    iget-object v1, p0, Lmiui/media/Mp3Recorder$b;->pA:Lmiui/media/Mp3Recorder;

    invoke-static {v1}, Lmiui/media/Mp3Recorder;->b(Lmiui/media/Mp3Recorder;)Ljava/io/FileOutputStream;

    move-result-object v1

    iget-object v2, p0, Lmiui/media/Mp3Recorder$b;->pA:Lmiui/media/Mp3Recorder;

    invoke-static {v2}, Lmiui/media/Mp3Recorder;->f(Lmiui/media/Mp3Recorder;)[B

    move-result-object v2

    const/4 v3, 0x0

    invoke-virtual {v1, v2, v3, v0}, Ljava/io/FileOutputStream;->write([BII)V

    .line 514
    iget-object v1, p0, Lmiui/media/Mp3Recorder$b;->pA:Lmiui/media/Mp3Recorder;

    int-to-long v2, v0

    invoke-static {v1, v2, v3}, Lmiui/media/Mp3Recorder;->b(Lmiui/media/Mp3Recorder;J)J

    .line 515
    iget-object v0, p0, Lmiui/media/Mp3Recorder$b;->pA:Lmiui/media/Mp3Recorder;

    invoke-static {v0}, Lmiui/media/Mp3Recorder;->k(Lmiui/media/Mp3Recorder;)J

    move-result-wide v0

    iget-object v2, p0, Lmiui/media/Mp3Recorder$b;->pA:Lmiui/media/Mp3Recorder;

    invoke-static {v2}, Lmiui/media/Mp3Recorder;->l(Lmiui/media/Mp3Recorder;)J

    move-result-wide v2

    cmp-long v0, v0, v2

    if-ltz v0, :cond_2

    .line 516
    iget-object v0, p0, Lmiui/media/Mp3Recorder$b;->pA:Lmiui/media/Mp3Recorder;

    const/4 v1, 0x7

    invoke-static {v0, v1}, Lmiui/media/Mp3Recorder;->a(Lmiui/media/Mp3Recorder;I)V
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_0

    goto/16 :goto_2

    .line 519
    :catch_0
    move-exception v0

    .line 520
    const-string v0, "Mp3Recorder"

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "Error when write sample to file: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    iget-object v2, p0, Lmiui/media/Mp3Recorder$b;->pA:Lmiui/media/Mp3Recorder;

    invoke-static {v2}, Lmiui/media/Mp3Recorder;->m(Lmiui/media/Mp3Recorder;)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 521
    iget-object v0, p0, Lmiui/media/Mp3Recorder$b;->pA:Lmiui/media/Mp3Recorder;

    const/4 v1, 0x5

    invoke-static {v0, v1}, Lmiui/media/Mp3Recorder;->a(Lmiui/media/Mp3Recorder;I)V

    goto/16 :goto_2
.end method
