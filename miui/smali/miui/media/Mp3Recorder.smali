.class public Lmiui/media/Mp3Recorder;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lmiui/media/Mp3Recorder$1;,
        Lmiui/media/Mp3Recorder$a;,
        Lmiui/media/Mp3Recorder$b;,
        Lmiui/media/Mp3Recorder$RecordingErrorListener;
    }
.end annotation


# static fields
.field public static final ERR_COULD_NOT_START:I = 0x6

.field public static final ERR_ENCODE_PCM_FAILED:I = 0x4

.field public static final ERR_FILE_NOT_EXIST:I = 0x8

.field public static final ERR_ILLEGAL_STATE:I = 0x1

.field public static final ERR_MAX_SIZE_REACHED:I = 0x7

.field public static final ERR_OUT_STREAM_NOT_READY:I = 0x2

.field public static final ERR_RECORD_PCM_FAILED:I = 0x3

.field public static final ERR_WRITE_TO_FILE:I = 0x5

.field private static final LOG_TAG:Ljava/lang/String; = "Mp3Recorder"

.field private static final SQ:Lcom/miui/internal/variable/Android_Media_AudioRecord_class;

.field private static final Tp:I = 0x1

.field private static final Tr:I = 0x0

.field private static final Ts:I = 0x1

.field private static final Tt:I = 0x2

.field private static final Tu:I = 0x3

.field private static final Tv:I = 0x4


# instance fields
.field private SS:I

.field private SU:Ljava/lang/String;

.field private SW:Ljava/io/File;

.field private Ta:I

.field private Tb:I

.field private Tc:J

.field private Td:J

.field private Te:J

.field private Tf:Ljava/lang/String;

.field private Tg:[S

.field private Th:[B

.field private Ti:Ljava/io/FileOutputStream;

.field private Tj:Landroid/media/AudioRecord;

.field private Tk:Lmiui/media/Mp3Encoder;

.field private Tl:Lmiui/media/Mp3Recorder$b;

.field private Tm:I

.field private Tn:I

.field private To:Lmiui/media/Mp3Recorder$RecordingErrorListener;

.field private Tq:Landroid/os/Handler;

.field private ng:I

.field private to:I

.field private tp:I

.field private tq:I

.field private tr:I


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .prologue
    .line 27
    invoke-static {}, Lcom/miui/internal/variable/Android_Media_AudioRecord_class$Factory;->getInstance()Lcom/miui/internal/variable/Android_Media_AudioRecord_class$Factory;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/internal/variable/Android_Media_AudioRecord_class$Factory;->get()Lcom/miui/internal/variable/Android_Media_AudioRecord_class;

    move-result-object v0

    sput-object v0, Lmiui/media/Mp3Recorder;->SQ:Lcom/miui/internal/variable/Android_Media_AudioRecord_class;

    return-void
.end method

.method public constructor <init>()V
    .locals 2

    .prologue
    .line 100
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 102
    invoke-static {}, Landroid/os/Looper;->myLooper()Landroid/os/Looper;

    move-result-object v0

    if-eqz v0, :cond_0

    .line 103
    new-instance v1, Lmiui/media/Mp3Recorder$a;

    invoke-direct {v1, p0, v0}, Lmiui/media/Mp3Recorder$a;-><init>(Lmiui/media/Mp3Recorder;Landroid/os/Looper;)V

    iput-object v1, p0, Lmiui/media/Mp3Recorder;->Tq:Landroid/os/Handler;

    .line 110
    :goto_0
    invoke-virtual {p0}, Lmiui/media/Mp3Recorder;->reset()V

    .line 111
    return-void

    .line 104
    :cond_0
    invoke-static {}, Landroid/os/Looper;->getMainLooper()Landroid/os/Looper;

    move-result-object v0

    if-eqz v0, :cond_1

    .line 105
    new-instance v1, Lmiui/media/Mp3Recorder$a;

    invoke-direct {v1, p0, v0}, Lmiui/media/Mp3Recorder$a;-><init>(Lmiui/media/Mp3Recorder;Landroid/os/Looper;)V

    iput-object v1, p0, Lmiui/media/Mp3Recorder;->Tq:Landroid/os/Handler;

    goto :goto_0

    .line 107
    :cond_1
    const-string v0, "Mp3Recorder"

    const-string v1, "Could not create event handler"

    invoke-static {v0, v1}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 108
    const/4 v0, 0x0

    iput-object v0, p0, Lmiui/media/Mp3Recorder;->Tq:Landroid/os/Handler;

    goto :goto_0
.end method

.method static synthetic a(Lmiui/media/Mp3Recorder;)I
    .locals 1

    .prologue
    .line 23
    iget v0, p0, Lmiui/media/Mp3Recorder;->Tm:I

    return v0
.end method

.method static synthetic a(Lmiui/media/Mp3Recorder;[SI)I
    .locals 1

    .prologue
    .line 23
    invoke-direct {p0, p1, p2}, Lmiui/media/Mp3Recorder;->a([SI)I

    move-result v0

    return v0
.end method

.method private a([SI)I
    .locals 3

    .prologue
    const/4 v0, 0x0

    .line 440
    move v2, v0

    move v1, v0

    .line 441
    :goto_0
    if-ge v2, p2, :cond_1

    .line 442
    aget-short v0, p1, v2

    if-gez v0, :cond_0

    aget-short v0, p1, v2

    neg-int v0, v0

    .line 443
    :goto_1
    if-le v0, v1, :cond_2

    .line 441
    :goto_2
    add-int/lit8 v1, v2, 0x1

    move v2, v1

    move v1, v0

    goto :goto_0

    .line 442
    :cond_0
    aget-short v0, p1, v2

    goto :goto_1

    .line 447
    :cond_1
    return v1

    :cond_2
    move v0, v1

    goto :goto_2
.end method

.method static synthetic a(Lmiui/media/Mp3Recorder;J)J
    .locals 2

    .prologue
    .line 23
    iget-wide v0, p0, Lmiui/media/Mp3Recorder;->Tc:J

    add-long/2addr v0, p1

    iput-wide v0, p0, Lmiui/media/Mp3Recorder;->Tc:J

    return-wide v0
.end method

.method static synthetic a(Lmiui/media/Mp3Recorder;I)V
    .locals 0

    .prologue
    .line 23
    invoke-direct {p0, p1}, Lmiui/media/Mp3Recorder;->ax(I)V

    return-void
.end method

.method private ax(I)V
    .locals 2

    .prologue
    .line 531
    iget-object v0, p0, Lmiui/media/Mp3Recorder;->Tq:Landroid/os/Handler;

    if-eqz v0, :cond_0

    .line 532
    iget-object v0, p0, Lmiui/media/Mp3Recorder;->Tq:Landroid/os/Handler;

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Landroid/os/Handler;->obtainMessage(I)Landroid/os/Message;

    move-result-object v0

    .line 533
    iput p1, v0, Landroid/os/Message;->arg1:I

    .line 534
    iget-object v1, p0, Lmiui/media/Mp3Recorder;->Tq:Landroid/os/Handler;

    invoke-virtual {v1, v0}, Landroid/os/Handler;->sendMessage(Landroid/os/Message;)Z

    .line 536
    :cond_0
    return-void
.end method

.method static synthetic b(Lmiui/media/Mp3Recorder;I)I
    .locals 0

    .prologue
    .line 23
    iput p1, p0, Lmiui/media/Mp3Recorder;->Tb:I

    return p1
.end method

.method static synthetic b(Lmiui/media/Mp3Recorder;J)J
    .locals 2

    .prologue
    .line 23
    iget-wide v0, p0, Lmiui/media/Mp3Recorder;->Td:J

    add-long/2addr v0, p1

    iput-wide v0, p0, Lmiui/media/Mp3Recorder;->Td:J

    return-wide v0
.end method

.method static synthetic b(Lmiui/media/Mp3Recorder;)Ljava/io/FileOutputStream;
    .locals 1

    .prologue
    .line 23
    iget-object v0, p0, Lmiui/media/Mp3Recorder;->Ti:Ljava/io/FileOutputStream;

    return-object v0
.end method

.method static synthetic c(Lmiui/media/Mp3Recorder;)[S
    .locals 1

    .prologue
    .line 23
    iget-object v0, p0, Lmiui/media/Mp3Recorder;->Tg:[S

    return-object v0
.end method

.method static synthetic d(Lmiui/media/Mp3Recorder;)Landroid/media/AudioRecord;
    .locals 1

    .prologue
    .line 23
    iget-object v0, p0, Lmiui/media/Mp3Recorder;->Tj:Landroid/media/AudioRecord;

    return-object v0
.end method

.method static synthetic e(Lmiui/media/Mp3Recorder;)I
    .locals 1

    .prologue
    .line 23
    iget v0, p0, Lmiui/media/Mp3Recorder;->Ta:I

    return v0
.end method

.method static synthetic f(Lmiui/media/Mp3Recorder;)[B
    .locals 1

    .prologue
    .line 23
    iget-object v0, p0, Lmiui/media/Mp3Recorder;->Th:[B

    return-object v0
.end method

.method static synthetic g(Lmiui/media/Mp3Recorder;)Lmiui/media/Mp3Encoder;
    .locals 1

    .prologue
    .line 23
    iget-object v0, p0, Lmiui/media/Mp3Recorder;->Tk:Lmiui/media/Mp3Encoder;

    return-object v0
.end method

.method static synthetic h(Lmiui/media/Mp3Recorder;)I
    .locals 1

    .prologue
    .line 23
    iget v0, p0, Lmiui/media/Mp3Recorder;->to:I

    return v0
.end method

.method static synthetic i(Lmiui/media/Mp3Recorder;)Lmiui/media/Mp3Recorder$RecordingErrorListener;
    .locals 1

    .prologue
    .line 23
    iget-object v0, p0, Lmiui/media/Mp3Recorder;->To:Lmiui/media/Mp3Recorder$RecordingErrorListener;

    return-object v0
.end method

.method static synthetic j(Lmiui/media/Mp3Recorder;)Ljava/io/File;
    .locals 1

    .prologue
    .line 23
    iget-object v0, p0, Lmiui/media/Mp3Recorder;->SW:Ljava/io/File;

    return-object v0
.end method

.method static synthetic k(Lmiui/media/Mp3Recorder;)J
    .locals 2

    .prologue
    .line 23
    iget-wide v0, p0, Lmiui/media/Mp3Recorder;->Td:J

    return-wide v0
.end method

.method static synthetic l(Lmiui/media/Mp3Recorder;)J
    .locals 2

    .prologue
    .line 23
    iget-wide v0, p0, Lmiui/media/Mp3Recorder;->Te:J

    return-wide v0
.end method

.method static synthetic m(Lmiui/media/Mp3Recorder;)Ljava/lang/String;
    .locals 1

    .prologue
    .line 23
    iget-object v0, p0, Lmiui/media/Mp3Recorder;->SU:Ljava/lang/String;

    return-object v0
.end method


# virtual methods
.method public getMaxAmplitude()I
    .locals 1

    .prologue
    .line 455
    iget v0, p0, Lmiui/media/Mp3Recorder;->Tb:I

    return v0
.end method

.method public getRecordingSizeInByte()J
    .locals 2

    .prologue
    .line 332
    iget-wide v0, p0, Lmiui/media/Mp3Recorder;->Td:J

    return-wide v0
.end method

.method public getRecordingTimeInMillis()J
    .locals 4

    .prologue
    .line 324
    iget-wide v0, p0, Lmiui/media/Mp3Recorder;->Tc:J

    long-to-double v0, v0

    iget v2, p0, Lmiui/media/Mp3Recorder;->ng:I

    int-to-double v2, v2

    div-double/2addr v0, v2

    const-wide v2, 0x408f400000000000L

    mul-double/2addr v0, v2

    iget v2, p0, Lmiui/media/Mp3Recorder;->to:I

    int-to-double v2, v2

    div-double/2addr v0, v2

    double-to-long v0, v0

    return-wide v0
.end method

.method public isExtraParamSupported()Z
    .locals 1

    .prologue
    .line 185
    sget-object v0, Lmiui/media/Mp3Recorder;->SQ:Lcom/miui/internal/variable/Android_Media_AudioRecord_class;

    invoke-virtual {v0}, Lcom/miui/internal/variable/Android_Media_AudioRecord_class;->isExtraParamSupported()Z

    move-result v0

    return v0
.end method

.method public isPaused()Z
    .locals 2

    .prologue
    .line 463
    iget v0, p0, Lmiui/media/Mp3Recorder;->Tm:I

    const/4 v1, 0x3

    if-ne v0, v1, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public declared-synchronized pause()V
    .locals 3
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/IllegalStateException;
        }
    .end annotation

    .prologue
    .line 340
    monitor-enter p0

    :try_start_0
    iget v0, p0, Lmiui/media/Mp3Recorder;->Tm:I

    const/4 v1, 0x2

    if-ne v0, v1, :cond_0

    iget-object v0, p0, Lmiui/media/Mp3Recorder;->Tl:Lmiui/media/Mp3Recorder$b;

    if-nez v0, :cond_1

    .line 341
    :cond_0
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "Recording not started"

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 340
    :catchall_0
    move-exception v0

    monitor-exit p0

    throw v0

    .line 344
    :cond_1
    const/4 v0, 0x3

    :try_start_1
    iput v0, p0, Lmiui/media/Mp3Recorder;->Tm:I
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 346
    :try_start_2
    iget-object v0, p0, Lmiui/media/Mp3Recorder;->Tl:Lmiui/media/Mp3Recorder$b;

    invoke-virtual {v0}, Lmiui/media/Mp3Recorder$b;->join()V
    :try_end_2
    .catch Ljava/lang/InterruptedException; {:try_start_2 .. :try_end_2} :catch_0
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    .line 351
    :goto_0
    :try_start_3
    iget-object v0, p0, Lmiui/media/Mp3Recorder;->Tj:Landroid/media/AudioRecord;

    invoke-virtual {v0}, Landroid/media/AudioRecord;->stop()V

    .line 352
    const-string v0, "Mp3Recorder"

    const-string v1, "AudioRecord stoped"

    invoke-static {v0, v1}, Landroid/util/Log;->v(Ljava/lang/String;Ljava/lang/String;)I

    .line 353
    iget-object v0, p0, Lmiui/media/Mp3Recorder;->Tj:Landroid/media/AudioRecord;

    invoke-virtual {v0}, Landroid/media/AudioRecord;->release()V

    .line 354
    const-string v0, "Mp3Recorder"

    const-string v1, "AudioRecord released"

    invoke-static {v0, v1}, Landroid/util/Log;->v(Ljava/lang/String;Ljava/lang/String;)I

    .line 355
    const/4 v0, 0x0

    iput-object v0, p0, Lmiui/media/Mp3Recorder;->Tj:Landroid/media/AudioRecord;

    .line 356
    const-string v0, "Mp3Recorder"

    const-string v1, "Mp3Recorder paused"

    invoke-static {v0, v1}, Landroid/util/Log;->v(Ljava/lang/String;Ljava/lang/String;)I
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_0

    .line 357
    monitor-exit p0

    return-void

    .line 347
    :catch_0
    move-exception v0

    .line 348
    :try_start_4
    const-string v1, "Mp3Recorder"

    const-string v2, "InterruptedException when pause"

    invoke-static {v1, v2, v0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    :try_end_4
    .catchall {:try_start_4 .. :try_end_4} :catchall_0

    goto :goto_0
.end method

.method public prepare()V
    .locals 8
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/IllegalStateException;,
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    const/16 v7, 0xc

    const/4 v6, 0x1

    const/4 v4, 0x2

    .line 254
    iget v0, p0, Lmiui/media/Mp3Recorder;->ng:I

    iget v1, p0, Lmiui/media/Mp3Recorder;->Ta:I

    invoke-static {v0, v1, v4}, Landroid/media/AudioRecord;->getMinBufferSize(III)I

    move-result v0

    iput v0, p0, Lmiui/media/Mp3Recorder;->Tn:I

    .line 256
    iget v0, p0, Lmiui/media/Mp3Recorder;->Tn:I

    if-gez v0, :cond_0

    .line 257
    const-string v0, "Mp3Recorder"

    const-string v1, "Error when getting min buffer size"

    invoke-static {v0, v1}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 258
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "Could not calculate the min buffer size"

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 260
    :cond_0
    iget v0, p0, Lmiui/media/Mp3Recorder;->Tn:I

    mul-int/lit8 v0, v0, 0x2

    new-array v0, v0, [S

    iput-object v0, p0, Lmiui/media/Mp3Recorder;->Tg:[S

    .line 261
    new-instance v0, Landroid/media/AudioRecord;

    iget v1, p0, Lmiui/media/Mp3Recorder;->SS:I

    iget v2, p0, Lmiui/media/Mp3Recorder;->ng:I

    iget v3, p0, Lmiui/media/Mp3Recorder;->Ta:I

    iget-object v5, p0, Lmiui/media/Mp3Recorder;->Tg:[S

    array-length v5, v5

    invoke-direct/range {v0 .. v5}, Landroid/media/AudioRecord;-><init>(IIIII)V

    iput-object v0, p0, Lmiui/media/Mp3Recorder;->Tj:Landroid/media/AudioRecord;

    .line 263
    iget-object v0, p0, Lmiui/media/Mp3Recorder;->Tf:Ljava/lang/String;

    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_1

    .line 264
    sget-object v0, Lmiui/media/Mp3Recorder;->SQ:Lcom/miui/internal/variable/Android_Media_AudioRecord_class;

    invoke-virtual {v0}, Lcom/miui/internal/variable/Android_Media_AudioRecord_class;->isExtraParamSupported()Z

    move-result v0

    if-eqz v0, :cond_2

    .line 265
    sget-object v0, Lmiui/media/Mp3Recorder;->SQ:Lcom/miui/internal/variable/Android_Media_AudioRecord_class;

    iget-object v1, p0, Lmiui/media/Mp3Recorder;->Tj:Landroid/media/AudioRecord;

    iget-object v2, p0, Lmiui/media/Mp3Recorder;->Tf:Ljava/lang/String;

    invoke-virtual {v0, v1, v2}, Lcom/miui/internal/variable/Android_Media_AudioRecord_class;->setParameters(Landroid/media/AudioRecord;Ljava/lang/String;)I

    move-result v0

    .line 266
    const-string v1, "Mp3Recorder"

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "setParameters: "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-static {v1, v0}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 271
    :cond_1
    :goto_0
    const-string v0, "Mp3Recorder"

    const-string v1, "Apply new AudioRecord"

    invoke-static {v0, v1}, Landroid/util/Log;->v(Ljava/lang/String;Ljava/lang/String;)I

    .line 272
    iget v0, p0, Lmiui/media/Mp3Recorder;->Ta:I

    if-ne v0, v7, :cond_3

    const/4 v0, 0x0

    :goto_1
    iput v0, p0, Lmiui/media/Mp3Recorder;->tp:I

    .line 273
    iget v0, p0, Lmiui/media/Mp3Recorder;->Ta:I

    if-ne v0, v7, :cond_4

    :goto_2
    iput v4, p0, Lmiui/media/Mp3Recorder;->to:I

    .line 276
    const-wide v0, 0x40bc200000000000L

    iget-object v2, p0, Lmiui/media/Mp3Recorder;->Tg:[S

    array-length v2, v2

    int-to-double v2, v2

    const-wide/high16 v4, 0x3ff4000000000000L

    mul-double/2addr v2, v4

    add-double/2addr v0, v2

    double-to-int v0, v0

    new-array v0, v0, [B

    iput-object v0, p0, Lmiui/media/Mp3Recorder;->Th:[B

    .line 277
    iget-object v0, p0, Lmiui/media/Mp3Recorder;->Tk:Lmiui/media/Mp3Encoder;

    iget v1, p0, Lmiui/media/Mp3Recorder;->ng:I

    invoke-virtual {v0, v1}, Lmiui/media/Mp3Encoder;->setInSampleRate(I)V

    .line 278
    iget-object v0, p0, Lmiui/media/Mp3Recorder;->Tk:Lmiui/media/Mp3Encoder;

    iget v1, p0, Lmiui/media/Mp3Recorder;->tp:I

    invoke-virtual {v0, v1}, Lmiui/media/Mp3Encoder;->setOutMode(I)V

    .line 279
    iget-object v0, p0, Lmiui/media/Mp3Recorder;->Tk:Lmiui/media/Mp3Encoder;

    iget v1, p0, Lmiui/media/Mp3Recorder;->to:I

    invoke-virtual {v0, v1}, Lmiui/media/Mp3Encoder;->setChannelCount(I)V

    .line 280
    iget-object v0, p0, Lmiui/media/Mp3Recorder;->Tk:Lmiui/media/Mp3Encoder;

    iget v1, p0, Lmiui/media/Mp3Recorder;->ng:I

    invoke-virtual {v0, v1}, Lmiui/media/Mp3Encoder;->setOutSampleRate(I)V

    .line 281
    iget-object v0, p0, Lmiui/media/Mp3Recorder;->Tk:Lmiui/media/Mp3Encoder;

    iget v1, p0, Lmiui/media/Mp3Recorder;->tq:I

    invoke-virtual {v0, v1}, Lmiui/media/Mp3Encoder;->setOutBitRate(I)V

    .line 282
    iget-object v0, p0, Lmiui/media/Mp3Recorder;->Tk:Lmiui/media/Mp3Encoder;

    iget v1, p0, Lmiui/media/Mp3Recorder;->tr:I

    invoke-virtual {v0, v1}, Lmiui/media/Mp3Encoder;->setQuality(I)V

    .line 283
    iget-object v0, p0, Lmiui/media/Mp3Recorder;->Tk:Lmiui/media/Mp3Encoder;

    invoke-virtual {v0}, Lmiui/media/Mp3Encoder;->init()Z

    .line 285
    new-instance v0, Ljava/io/File;

    iget-object v1, p0, Lmiui/media/Mp3Recorder;->SU:Ljava/lang/String;

    invoke-direct {v0, v1}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    iput-object v0, p0, Lmiui/media/Mp3Recorder;->SW:Ljava/io/File;

    .line 286
    iput v6, p0, Lmiui/media/Mp3Recorder;->Tm:I

    .line 287
    const-string v0, "Mp3Recorder"

    const-string v1, "Mp3Recorder prepared"

    invoke-static {v0, v1}, Landroid/util/Log;->v(Ljava/lang/String;Ljava/lang/String;)I

    .line 288
    return-void

    .line 267
    :cond_2
    iget-object v0, p0, Lmiui/media/Mp3Recorder;->Tf:Ljava/lang/String;

    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_1

    .line 268
    const-string v0, "Mp3Recorder"

    const-string v1, "Do not support extra parameters"

    invoke-static {v0, v1}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    goto :goto_0

    .line 272
    :cond_3
    const/4 v0, 0x3

    goto :goto_1

    :cond_4
    move v4, v6

    .line 273
    goto :goto_2
.end method

.method public release()V
    .locals 2

    .prologue
    .line 430
    iget-object v0, p0, Lmiui/media/Mp3Recorder;->Tj:Landroid/media/AudioRecord;

    if-eqz v0, :cond_0

    .line 431
    iget-object v0, p0, Lmiui/media/Mp3Recorder;->Tj:Landroid/media/AudioRecord;

    invoke-virtual {v0}, Landroid/media/AudioRecord;->release()V

    .line 432
    const/4 v0, 0x0

    iput-object v0, p0, Lmiui/media/Mp3Recorder;->Tj:Landroid/media/AudioRecord;

    .line 433
    const-string v0, "Mp3Recorder"

    const-string v1, "AudioRecord released"

    invoke-static {v0, v1}, Landroid/util/Log;->v(Ljava/lang/String;Ljava/lang/String;)I

    .line 435
    :cond_0
    iget-object v0, p0, Lmiui/media/Mp3Recorder;->Tk:Lmiui/media/Mp3Encoder;

    invoke-virtual {v0}, Lmiui/media/Mp3Encoder;->close()I

    .line 436
    const-string v0, "Mp3Recorder"

    const-string v1, "Mp3Recorder released"

    invoke-static {v0, v1}, Landroid/util/Log;->v(Ljava/lang/String;Ljava/lang/String;)I

    .line 437
    return-void
.end method

.method public reset()V
    .locals 4

    .prologue
    const-wide/16 v2, 0x0

    const/4 v1, 0x0

    .line 232
    iput v1, p0, Lmiui/media/Mp3Recorder;->Tm:I

    .line 233
    const/4 v0, 0x0

    iput-object v0, p0, Lmiui/media/Mp3Recorder;->Tj:Landroid/media/AudioRecord;

    .line 234
    const/4 v0, 0x1

    iput v0, p0, Lmiui/media/Mp3Recorder;->SS:I

    .line 235
    const v0, 0xac44

    iput v0, p0, Lmiui/media/Mp3Recorder;->ng:I

    .line 236
    const/16 v0, 0x10

    iput v0, p0, Lmiui/media/Mp3Recorder;->Ta:I

    .line 237
    iput v1, p0, Lmiui/media/Mp3Recorder;->tr:I

    .line 238
    iput v1, p0, Lmiui/media/Mp3Recorder;->Tm:I

    .line 239
    const/16 v0, 0x40

    iput v0, p0, Lmiui/media/Mp3Recorder;->tq:I

    .line 240
    iput-wide v2, p0, Lmiui/media/Mp3Recorder;->Tc:J

    .line 241
    iput-wide v2, p0, Lmiui/media/Mp3Recorder;->Td:J

    .line 242
    const-wide v0, 0x7fffffffffffffffL

    iput-wide v0, p0, Lmiui/media/Mp3Recorder;->Te:J

    .line 243
    new-instance v0, Lmiui/media/Mp3Encoder;

    invoke-direct {v0}, Lmiui/media/Mp3Encoder;-><init>()V

    iput-object v0, p0, Lmiui/media/Mp3Recorder;->Tk:Lmiui/media/Mp3Encoder;

    .line 244
    return-void
.end method

.method public declared-synchronized resume()V
    .locals 7
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/IllegalStateException;
        }
    .end annotation

    .prologue
    const/4 v6, 0x3

    .line 364
    monitor-enter p0

    :try_start_0
    iget v0, p0, Lmiui/media/Mp3Recorder;->Tm:I

    if-ne v0, v6, :cond_1

    .line 365
    new-instance v0, Landroid/media/AudioRecord;

    iget v1, p0, Lmiui/media/Mp3Recorder;->SS:I

    iget v2, p0, Lmiui/media/Mp3Recorder;->ng:I

    iget v3, p0, Lmiui/media/Mp3Recorder;->Ta:I

    const/4 v4, 0x2

    iget-object v5, p0, Lmiui/media/Mp3Recorder;->Tg:[S

    array-length v5, v5

    invoke-direct/range {v0 .. v5}, Landroid/media/AudioRecord;-><init>(IIIII)V

    iput-object v0, p0, Lmiui/media/Mp3Recorder;->Tj:Landroid/media/AudioRecord;

    .line 367
    const-string v0, "Mp3Recorder"

    const-string v1, "Apply new AudioRecord"

    invoke-static {v0, v1}, Landroid/util/Log;->v(Ljava/lang/String;Ljava/lang/String;)I

    .line 368
    iget-object v0, p0, Lmiui/media/Mp3Recorder;->Tj:Landroid/media/AudioRecord;

    invoke-virtual {v0}, Landroid/media/AudioRecord;->startRecording()V

    .line 369
    iget-object v0, p0, Lmiui/media/Mp3Recorder;->Tj:Landroid/media/AudioRecord;

    invoke-virtual {v0}, Landroid/media/AudioRecord;->getRecordingState()I

    move-result v0

    if-eq v0, v6, :cond_0

    .line 370
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "Mp3 record could not start: other input already started"

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 364
    :catchall_0
    move-exception v0

    monitor-exit p0

    throw v0

    .line 372
    :cond_0
    :try_start_1
    const-string v0, "Mp3Recorder"

    const-string v1, "AudioRecord started"

    invoke-static {v0, v1}, Landroid/util/Log;->v(Ljava/lang/String;Ljava/lang/String;)I

    .line 373
    const/4 v0, 0x2

    iput v0, p0, Lmiui/media/Mp3Recorder;->Tm:I

    .line 374
    new-instance v0, Lmiui/media/Mp3Recorder$b;

    const/4 v1, 0x0

    invoke-direct {v0, p0, v1}, Lmiui/media/Mp3Recorder$b;-><init>(Lmiui/media/Mp3Recorder;Lmiui/media/Mp3Recorder$1;)V

    iput-object v0, p0, Lmiui/media/Mp3Recorder;->Tl:Lmiui/media/Mp3Recorder$b;

    .line 375
    iget-object v0, p0, Lmiui/media/Mp3Recorder;->Tl:Lmiui/media/Mp3Recorder$b;

    invoke-virtual {v0}, Lmiui/media/Mp3Recorder$b;->start()V

    .line 376
    const-string v0, "Mp3Recorder"

    const-string v1, "Mp3Recorder resumed"

    invoke-static {v0, v1}, Landroid/util/Log;->v(Ljava/lang/String;Ljava/lang/String;)I
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 381
    monitor-exit p0

    return-void

    .line 378
    :cond_1
    :try_start_2
    const-string v0, "Mp3Recorder"

    const-string v1, "Recording is going on"

    invoke-static {v0, v1}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 379
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "Recording is going on"

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0
.end method

.method public setAudioChannel(I)V
    .locals 0

    .prologue
    .line 198
    iput p1, p0, Lmiui/media/Mp3Recorder;->Ta:I

    .line 199
    return-void
.end method

.method public setAudioSamplingRate(I)V
    .locals 0

    .prologue
    .line 138
    iput p1, p0, Lmiui/media/Mp3Recorder;->ng:I

    .line 139
    return-void
.end method

.method public setAudioSource(I)V
    .locals 0

    .prologue
    .line 129
    iput p1, p0, Lmiui/media/Mp3Recorder;->SS:I

    .line 130
    return-void
.end method

.method public setExtraParameters(Ljava/lang/String;)V
    .locals 2

    .prologue
    .line 174
    sget-object v0, Lmiui/media/Mp3Recorder;->SQ:Lcom/miui/internal/variable/Android_Media_AudioRecord_class;

    invoke-virtual {v0}, Lcom/miui/internal/variable/Android_Media_AudioRecord_class;->isExtraParamSupported()Z

    move-result v0

    if-nez v0, :cond_0

    .line 175
    new-instance v0, Ljava/lang/IllegalArgumentException;

    const-string v1, "Do not support extra parameter"

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 177
    :cond_0
    iput-object p1, p0, Lmiui/media/Mp3Recorder;->Tf:Ljava/lang/String;

    .line 178
    return-void
.end method

.method public setMaxDuration(J)V
    .locals 2

    .prologue
    .line 221
    const-wide/16 v0, 0x0

    cmp-long v0, p1, v0

    if-lez v0, :cond_0

    .line 222
    iget v0, p0, Lmiui/media/Mp3Recorder;->tq:I

    div-int/lit8 v0, v0, 0x8

    int-to-long v0, v0

    mul-long/2addr v0, p1

    iput-wide v0, p0, Lmiui/media/Mp3Recorder;->Te:J

    .line 226
    :goto_0
    return-void

    .line 224
    :cond_0
    const-wide v0, 0x7fffffffffffffffL

    iput-wide v0, p0, Lmiui/media/Mp3Recorder;->Te:J

    goto :goto_0
.end method

.method public setMaxFileSize(J)V
    .locals 2

    .prologue
    .line 207
    const-wide/16 v0, 0x0

    cmp-long v0, p1, v0

    if-lez v0, :cond_0

    .line 208
    iput-wide p1, p0, Lmiui/media/Mp3Recorder;->Te:J

    .line 212
    :goto_0
    return-void

    .line 210
    :cond_0
    const-wide v0, 0x7fffffffffffffffL

    iput-wide v0, p0, Lmiui/media/Mp3Recorder;->Te:J

    goto :goto_0
.end method

.method public setOnErrorListener(Lmiui/media/Mp3Recorder$RecordingErrorListener;)V
    .locals 0

    .prologue
    .line 156
    iput-object p1, p0, Lmiui/media/Mp3Recorder;->To:Lmiui/media/Mp3Recorder$RecordingErrorListener;

    .line 157
    return-void
.end method

.method public setOutBitRate(I)V
    .locals 0

    .prologue
    .line 147
    iput p1, p0, Lmiui/media/Mp3Recorder;->tq:I

    .line 148
    return-void
.end method

.method public setOutputFile(Ljava/lang/String;)V
    .locals 0

    .prologue
    .line 119
    iput-object p1, p0, Lmiui/media/Mp3Recorder;->SU:Ljava/lang/String;

    .line 120
    return-void
.end method

.method public setQuality(I)V
    .locals 0

    .prologue
    .line 166
    iput p1, p0, Lmiui/media/Mp3Recorder;->tr:I

    .line 167
    return-void
.end method

.method public declared-synchronized start()V
    .locals 2
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/IllegalStateException;,
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    const/4 v1, 0x2

    .line 296
    monitor-enter p0

    :try_start_0
    iget-object v0, p0, Lmiui/media/Mp3Recorder;->Tl:Lmiui/media/Mp3Recorder$b;

    if-nez v0, :cond_0

    iget v0, p0, Lmiui/media/Mp3Recorder;->Tm:I

    if-ne v0, v1, :cond_1

    .line 297
    :cond_0
    const-string v0, "Mp3Recorder"

    const-string v1, "Recording has started"

    invoke-static {v0, v1}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 298
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "Recording has already started"

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 296
    :catchall_0
    move-exception v0

    monitor-exit p0

    throw v0

    .line 301
    :cond_1
    :try_start_1
    iget v0, p0, Lmiui/media/Mp3Recorder;->Tm:I

    const/4 v1, 0x1

    if-eq v0, v1, :cond_2

    .line 302
    const-string v0, "Mp3Recorder"

    const-string v1, "Recorder not prepared"

    invoke-static {v0, v1}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 303
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "Recorder not prepared"

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 305
    :cond_2
    const-wide/16 v0, 0x0

    iput-wide v0, p0, Lmiui/media/Mp3Recorder;->Tc:J

    .line 306
    const-wide/16 v0, 0x0

    iput-wide v0, p0, Lmiui/media/Mp3Recorder;->Td:J

    .line 307
    const/4 v0, 0x2

    iput v0, p0, Lmiui/media/Mp3Recorder;->Tm:I

    .line 308
    iget-object v0, p0, Lmiui/media/Mp3Recorder;->Tj:Landroid/media/AudioRecord;

    invoke-virtual {v0}, Landroid/media/AudioRecord;->startRecording()V

    .line 309
    iget-object v0, p0, Lmiui/media/Mp3Recorder;->Tj:Landroid/media/AudioRecord;

    invoke-virtual {v0}, Landroid/media/AudioRecord;->getRecordingState()I

    move-result v0

    const/4 v1, 0x3

    if-eq v0, v1, :cond_3

    .line 310
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "Mp3 record could not start: other input already started"

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 312
    :cond_3
    new-instance v0, Ljava/io/FileOutputStream;

    iget-object v1, p0, Lmiui/media/Mp3Recorder;->SW:Ljava/io/File;

    invoke-direct {v0, v1}, Ljava/io/FileOutputStream;-><init>(Ljava/io/File;)V

    iput-object v0, p0, Lmiui/media/Mp3Recorder;->Ti:Ljava/io/FileOutputStream;

    .line 313
    const-string v0, "Mp3Recorder"

    const-string v1, "AudioRecord started"

    invoke-static {v0, v1}, Landroid/util/Log;->v(Ljava/lang/String;Ljava/lang/String;)I

    .line 314
    new-instance v0, Lmiui/media/Mp3Recorder$b;

    const/4 v1, 0x0

    invoke-direct {v0, p0, v1}, Lmiui/media/Mp3Recorder$b;-><init>(Lmiui/media/Mp3Recorder;Lmiui/media/Mp3Recorder$1;)V

    iput-object v0, p0, Lmiui/media/Mp3Recorder;->Tl:Lmiui/media/Mp3Recorder$b;

    .line 315
    iget-object v0, p0, Lmiui/media/Mp3Recorder;->Tl:Lmiui/media/Mp3Recorder$b;

    invoke-virtual {v0}, Lmiui/media/Mp3Recorder$b;->start()V

    .line 316
    const-string v0, "Mp3Recorder"

    const-string v1, "Mp3Recorder started"

    invoke-static {v0, v1}, Landroid/util/Log;->v(Ljava/lang/String;Ljava/lang/String;)I
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 317
    monitor-exit p0

    return-void
.end method

.method public declared-synchronized stop()V
    .locals 4
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/IllegalStateException;
        }
    .end annotation

    .prologue
    .line 388
    monitor-enter p0

    :try_start_0
    iget v0, p0, Lmiui/media/Mp3Recorder;->Tm:I

    const/4 v1, 0x3

    if-eq v0, v1, :cond_0

    iget v0, p0, Lmiui/media/Mp3Recorder;->Tm:I

    const/4 v1, 0x2

    if-ne v0, v1, :cond_5

    .line 389
    :cond_0
    const/4 v0, 0x4

    iput v0, p0, Lmiui/media/Mp3Recorder;->Tm:I
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 391
    :try_start_1
    iget-object v0, p0, Lmiui/media/Mp3Recorder;->Tl:Lmiui/media/Mp3Recorder$b;

    if-eqz v0, :cond_1

    iget-object v0, p0, Lmiui/media/Mp3Recorder;->Tl:Lmiui/media/Mp3Recorder$b;

    invoke-virtual {v0}, Lmiui/media/Mp3Recorder$b;->isAlive()Z

    move-result v0

    if-eqz v0, :cond_1

    .line 392
    iget-object v0, p0, Lmiui/media/Mp3Recorder;->Tl:Lmiui/media/Mp3Recorder$b;

    invoke-virtual {v0}, Lmiui/media/Mp3Recorder$b;->join()V
    :try_end_1
    .catch Ljava/lang/InterruptedException; {:try_start_1 .. :try_end_1} :catch_0
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 397
    :cond_1
    :goto_0
    const/4 v0, 0x0

    :try_start_2
    iput-object v0, p0, Lmiui/media/Mp3Recorder;->Tl:Lmiui/media/Mp3Recorder$b;

    .line 399
    iget-object v0, p0, Lmiui/media/Mp3Recorder;->Tk:Lmiui/media/Mp3Encoder;

    iget-object v1, p0, Lmiui/media/Mp3Recorder;->Th:[B

    iget-object v2, p0, Lmiui/media/Mp3Recorder;->Th:[B

    array-length v2, v2

    invoke-virtual {v0, v1, v2}, Lmiui/media/Mp3Encoder;->flush([BI)I
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    move-result v0

    .line 401
    if-lez v0, :cond_3

    .line 402
    :try_start_3
    iget-object v1, p0, Lmiui/media/Mp3Recorder;->Ti:Ljava/io/FileOutputStream;

    iget-object v2, p0, Lmiui/media/Mp3Recorder;->Th:[B

    const/4 v3, 0x0

    invoke-virtual {v1, v2, v3, v0}, Ljava/io/FileOutputStream;->write([BII)V
    :try_end_3
    .catch Ljava/io/IOException; {:try_start_3 .. :try_end_3} :catch_1
    .catchall {:try_start_3 .. :try_end_3} :catchall_1

    .line 410
    :try_start_4
    iget-object v0, p0, Lmiui/media/Mp3Recorder;->Ti:Ljava/io/FileOutputStream;

    invoke-virtual {v0}, Ljava/io/FileOutputStream;->close()V
    :try_end_4
    .catch Ljava/io/IOException; {:try_start_4 .. :try_end_4} :catch_2
    .catchall {:try_start_4 .. :try_end_4} :catchall_0

    .line 414
    :goto_1
    :try_start_5
    iget-object v0, p0, Lmiui/media/Mp3Recorder;->Tj:Landroid/media/AudioRecord;

    if-eqz v0, :cond_2

    .line 415
    iget-object v0, p0, Lmiui/media/Mp3Recorder;->Tj:Landroid/media/AudioRecord;

    invoke-virtual {v0}, Landroid/media/AudioRecord;->stop()V

    .line 416
    const-string v0, "Mp3Recorder"

    const-string v1, "AudioRecord stoped"

    invoke-static {v0, v1}, Landroid/util/Log;->v(Ljava/lang/String;Ljava/lang/String;)I

    .line 419
    :cond_2
    :goto_2
    const-string v0, "Mp3Recorder"

    const-string v1, "Mp3Recorder stoped"

    invoke-static {v0, v1}, Landroid/util/Log;->v(Ljava/lang/String;Ljava/lang/String;)I
    :try_end_5
    .catchall {:try_start_5 .. :try_end_5} :catchall_0

    .line 424
    monitor-exit p0

    return-void

    .line 394
    :catch_0
    move-exception v0

    .line 395
    :try_start_6
    const-string v1, "Mp3Recorder"

    const-string v2, "InterruptedException when stop"

    invoke-static {v1, v2, v0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    :try_end_6
    .catchall {:try_start_6 .. :try_end_6} :catchall_0

    goto :goto_0

    .line 388
    :catchall_0
    move-exception v0

    monitor-exit p0

    throw v0

    .line 404
    :cond_3
    :try_start_7
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "Buffer flush must greater than 0"

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0
    :try_end_7
    .catch Ljava/io/IOException; {:try_start_7 .. :try_end_7} :catch_1
    .catchall {:try_start_7 .. :try_end_7} :catchall_1

    .line 406
    :catch_1
    move-exception v0

    .line 407
    :try_start_8
    const-string v1, "Mp3Recorder"

    const-string v2, "Error file cannot be written when flush"

    invoke-static {v1, v2, v0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    :try_end_8
    .catchall {:try_start_8 .. :try_end_8} :catchall_1

    .line 410
    :try_start_9
    iget-object v0, p0, Lmiui/media/Mp3Recorder;->Ti:Ljava/io/FileOutputStream;

    invoke-virtual {v0}, Ljava/io/FileOutputStream;->close()V
    :try_end_9
    .catch Ljava/io/IOException; {:try_start_9 .. :try_end_9} :catch_3
    .catchall {:try_start_9 .. :try_end_9} :catchall_0

    .line 414
    :goto_3
    :try_start_a
    iget-object v0, p0, Lmiui/media/Mp3Recorder;->Tj:Landroid/media/AudioRecord;

    if-eqz v0, :cond_2

    .line 415
    iget-object v0, p0, Lmiui/media/Mp3Recorder;->Tj:Landroid/media/AudioRecord;

    invoke-virtual {v0}, Landroid/media/AudioRecord;->stop()V

    .line 416
    const-string v0, "Mp3Recorder"

    const-string v1, "AudioRecord stoped"

    invoke-static {v0, v1}, Landroid/util/Log;->v(Ljava/lang/String;Ljava/lang/String;)I

    goto :goto_2

    .line 411
    :catch_2
    move-exception v0

    .line 412
    const-string v1, "Mp3Recorder"

    const-string v2, "Error file cannot be closed"

    invoke-static {v1, v2, v0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    goto :goto_1

    .line 411
    :catch_3
    move-exception v0

    .line 412
    const-string v1, "Mp3Recorder"

    const-string v2, "Error file cannot be closed"

    invoke-static {v1, v2, v0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    :try_end_a
    .catchall {:try_start_a .. :try_end_a} :catchall_0

    goto :goto_3

    .line 409
    :catchall_1
    move-exception v0

    .line 410
    :try_start_b
    iget-object v1, p0, Lmiui/media/Mp3Recorder;->Ti:Ljava/io/FileOutputStream;

    invoke-virtual {v1}, Ljava/io/FileOutputStream;->close()V
    :try_end_b
    .catch Ljava/io/IOException; {:try_start_b .. :try_end_b} :catch_4
    .catchall {:try_start_b .. :try_end_b} :catchall_0

    .line 414
    :goto_4
    :try_start_c
    iget-object v1, p0, Lmiui/media/Mp3Recorder;->Tj:Landroid/media/AudioRecord;

    if-eqz v1, :cond_4

    .line 415
    iget-object v1, p0, Lmiui/media/Mp3Recorder;->Tj:Landroid/media/AudioRecord;

    invoke-virtual {v1}, Landroid/media/AudioRecord;->stop()V

    .line 416
    const-string v1, "Mp3Recorder"

    const-string v2, "AudioRecord stoped"

    invoke-static {v1, v2}, Landroid/util/Log;->v(Ljava/lang/String;Ljava/lang/String;)I

    :cond_4
    throw v0

    .line 411
    :catch_4
    move-exception v1

    .line 412
    const-string v2, "Mp3Recorder"

    const-string v3, "Error file cannot be closed"

    invoke-static {v2, v3, v1}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    goto :goto_4

    .line 421
    :cond_5
    const-string v0, "Mp3Recorder"

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "Recorder should not be stopped in state:"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    iget v2, p0, Lmiui/media/Mp3Recorder;->Tm:I

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 422
    new-instance v0, Ljava/lang/IllegalStateException;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "Recorder shoul not be stopped in state : "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    iget v2, p0, Lmiui/media/Mp3Recorder;->Tm:I

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0
    :try_end_c
    .catchall {:try_start_c .. :try_end_c} :catchall_0
.end method
