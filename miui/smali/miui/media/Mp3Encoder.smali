.class public Lmiui/media/Mp3Encoder;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field public static final DEFAULT_AUDIO_CHANNEL:I = 0x10

.field public static final DEFAULT_CHANNEL_COUNT:I = 0x1

.field public static final DEFAULT_OUT_BIT_RATE:I = 0x40

.field public static final DEFAULT_OUT_MODE:I = 0x3

.field public static final DEFAULT_QUALITY:I = 0x0

.field public static final DEFAULT_SAMPLE_RATE:I = 0xac44

.field public static final OUT_MODE_MONO:I = 0x3

.field public static final OUT_MODE_STEREO:I


# instance fields
.field private tl:J

.field private tm:I

.field private tn:I

.field private to:I

.field private tp:I

.field private tq:I

.field private tr:I


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .prologue
    .line 32
    const-string v0, "mp3lame"

    invoke-static {v0}, Ljava/lang/System;->loadLibrary(Ljava/lang/String;)V

    .line 33
    return-void
.end method

.method public constructor <init>()V
    .locals 3

    .prologue
    const v2, 0xac44

    .line 9
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 10
    const-wide/16 v0, -0x1

    iput-wide v0, p0, Lmiui/media/Mp3Encoder;->tl:J

    .line 24
    iput v2, p0, Lmiui/media/Mp3Encoder;->tm:I

    .line 25
    iput v2, p0, Lmiui/media/Mp3Encoder;->tn:I

    .line 26
    const/4 v0, 0x1

    iput v0, p0, Lmiui/media/Mp3Encoder;->to:I

    .line 27
    const/4 v0, 0x3

    iput v0, p0, Lmiui/media/Mp3Encoder;->tp:I

    .line 28
    const/16 v0, 0x40

    iput v0, p0, Lmiui/media/Mp3Encoder;->tq:I

    .line 29
    const/4 v0, 0x0

    iput v0, p0, Lmiui/media/Mp3Encoder;->tr:I

    return-void
.end method

.method private native lame_close(J)I
.end method

.method private native lame_encode(J[S[SI[BI)I
.end method

.method private native lame_encode_interleaved(J[SI[BI)I
.end method

.method private native lame_flush(J[BI)I
.end method

.method private native lame_init(IIIIII)J
.end method

.method private native lame_samples_to_encode(J)I
.end method


# virtual methods
.method public close()I
    .locals 5

    .prologue
    .line 163
    const/4 v0, -0x1

    .line 164
    iget-wide v1, p0, Lmiui/media/Mp3Encoder;->tl:J

    const-wide/16 v3, 0x0

    cmp-long v1, v1, v3

    if-lez v1, :cond_0

    .line 165
    iget-wide v0, p0, Lmiui/media/Mp3Encoder;->tl:J

    invoke-direct {p0, v0, v1}, Lmiui/media/Mp3Encoder;->lame_close(J)I

    move-result v0

    .line 166
    const-wide/16 v1, -0x1

    iput-wide v1, p0, Lmiui/media/Mp3Encoder;->tl:J

    .line 168
    :cond_0
    return v0
.end method

.method public encode([S[SI[BI)I
    .locals 8

    .prologue
    .line 117
    iget-wide v1, p0, Lmiui/media/Mp3Encoder;->tl:J

    move-object v0, p0

    move-object v3, p1

    move-object v4, p2

    move v5, p3

    move-object v6, p4

    move v7, p5

    invoke-direct/range {v0 .. v7}, Lmiui/media/Mp3Encoder;->lame_encode(J[S[SI[BI)I

    move-result v0

    return v0
.end method

.method public encodeInterleaved([SI[BI)I
    .locals 7

    .prologue
    .line 135
    iget-wide v1, p0, Lmiui/media/Mp3Encoder;->tl:J

    move-object v0, p0

    move-object v3, p1

    move v4, p2

    move-object v5, p3

    move v6, p4

    invoke-direct/range {v0 .. v6}, Lmiui/media/Mp3Encoder;->lame_encode_interleaved(J[SI[BI)I

    move-result v0

    return v0
.end method

.method protected finalize()V
    .locals 4
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/Throwable;
        }
    .end annotation

    .prologue
    .line 173
    invoke-super {p0}, Ljava/lang/Object;->finalize()V

    .line 174
    iget-wide v0, p0, Lmiui/media/Mp3Encoder;->tl:J

    const-wide/16 v2, 0x0

    cmp-long v0, v0, v2

    if-lez v0, :cond_0

    .line 175
    iget-wide v0, p0, Lmiui/media/Mp3Encoder;->tl:J

    invoke-direct {p0, v0, v1}, Lmiui/media/Mp3Encoder;->lame_close(J)I

    .line 177
    :cond_0
    return-void
.end method

.method public flush([BI)I
    .locals 2

    .prologue
    .line 155
    iget-wide v0, p0, Lmiui/media/Mp3Encoder;->tl:J

    invoke-direct {p0, v0, v1, p1, p2}, Lmiui/media/Mp3Encoder;->lame_flush(J[BI)I

    move-result v0

    return v0
.end method

.method public getSamplesToEncode()I
    .locals 2

    .prologue
    .line 144
    iget-wide v0, p0, Lmiui/media/Mp3Encoder;->tl:J

    invoke-direct {p0, v0, v1}, Lmiui/media/Mp3Encoder;->lame_samples_to_encode(J)I

    move-result v0

    return v0
.end method

.method public init()Z
    .locals 7

    .prologue
    .line 94
    iget v1, p0, Lmiui/media/Mp3Encoder;->tm:I

    iget v2, p0, Lmiui/media/Mp3Encoder;->tp:I

    iget v3, p0, Lmiui/media/Mp3Encoder;->to:I

    iget v4, p0, Lmiui/media/Mp3Encoder;->tn:I

    iget v5, p0, Lmiui/media/Mp3Encoder;->tq:I

    iget v6, p0, Lmiui/media/Mp3Encoder;->tr:I

    move-object v0, p0

    invoke-direct/range {v0 .. v6}, Lmiui/media/Mp3Encoder;->lame_init(IIIIII)J

    move-result-wide v0

    iput-wide v0, p0, Lmiui/media/Mp3Encoder;->tl:J

    .line 95
    iget-wide v0, p0, Lmiui/media/Mp3Encoder;->tl:J

    const-wide/16 v2, 0x0

    cmp-long v0, v0, v2

    if-gtz v0, :cond_0

    .line 96
    const/4 v0, 0x0

    .line 98
    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x1

    goto :goto_0
.end method

.method public setChannelCount(I)V
    .locals 0

    .prologue
    .line 86
    iput p1, p0, Lmiui/media/Mp3Encoder;->to:I

    .line 87
    return-void
.end method

.method public setInSampleRate(I)V
    .locals 0

    .prologue
    .line 78
    iput p1, p0, Lmiui/media/Mp3Encoder;->tm:I

    .line 79
    return-void
.end method

.method public setOutBitRate(I)V
    .locals 0

    .prologue
    .line 70
    iput p1, p0, Lmiui/media/Mp3Encoder;->tq:I

    .line 71
    return-void
.end method

.method public setOutMode(I)V
    .locals 0

    .prologue
    .line 62
    iput p1, p0, Lmiui/media/Mp3Encoder;->tp:I

    .line 63
    return-void
.end method

.method public setOutSampleRate(I)V
    .locals 0

    .prologue
    .line 49
    iput p1, p0, Lmiui/media/Mp3Encoder;->tn:I

    .line 50
    return-void
.end method

.method public setQuality(I)V
    .locals 0

    .prologue
    .line 41
    iput p1, p0, Lmiui/media/Mp3Encoder;->tr:I

    .line 42
    return-void
.end method
