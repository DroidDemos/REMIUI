.class public Lmiui/telephony/dtmf/DTMFUtil;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field private static final DEFAULT_SAMPLE_RATE:I = 0x1f40

.field private static final LOG_TAG:Ljava/lang/String; = "DTMFUtil"

.field private static final bm:I = 0x2

.field private static final mW:[F

.field private static final mX:[I

.field private static final mY:[I

.field private static final mZ:I = 0x5

.field private static final na:I = 0x100

.field private static final nb:I = 0x3

.field private static final nc:F = 600.0f

.field private static final nd:F = 1075.0f


# instance fields
.field private ne:[F

.field private nf:[F

.field private ng:I

.field private nh:I


# direct methods
.method static constructor <clinit>()V
    .locals 2

    .prologue
    const/4 v1, 0x5

    .line 16
    const/16 v0, 0xa

    new-array v0, v0, [F

    fill-array-data v0, :array_0

    sput-object v0, Lmiui/telephony/dtmf/DTMFUtil;->mW:[F

    .line 19
    new-array v0, v1, [I

    fill-array-data v0, :array_1

    sput-object v0, Lmiui/telephony/dtmf/DTMFUtil;->mX:[I

    .line 22
    new-array v0, v1, [I

    fill-array-data v0, :array_2

    sput-object v0, Lmiui/telephony/dtmf/DTMFUtil;->mY:[I

    return-void

    .line 16
    nop

    :array_0
    .array-data 4
        0x44160000
        0x442e4000
        0x44408000
        0x44550000
        0x446b4000
        0x44866000
        0x44972000
        0x44a70000
        0x44b8a000
        0x44cc2000
    .end array-data

    .line 19
    :array_1
    .array-data 4
        0x1f40
        0x2b11
        0x5622
        0x7d00
        0xac44
    .end array-data

    .line 22
    :array_2
    .array-data 4
        0x17
        0x20
        0x1f
        0x22
        0x25
    .end array-data
.end method

.method public constructor <init>()V
    .locals 2

    .prologue
    .line 59
    const/16 v0, 0x1f40

    const/4 v1, 0x3

    invoke-direct {p0, v0, v1}, Lmiui/telephony/dtmf/DTMFUtil;-><init>(II)V

    .line 60
    return-void
.end method

.method public constructor <init>(II)V
    .locals 1

    .prologue
    .line 62
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 36
    sget-object v0, Lmiui/telephony/dtmf/DTMFUtil;->mW:[F

    array-length v0, v0

    new-array v0, v0, [F

    iput-object v0, p0, Lmiui/telephony/dtmf/DTMFUtil;->ne:[F

    .line 37
    sget-object v0, Lmiui/telephony/dtmf/DTMFUtil;->mW:[F

    array-length v0, v0

    new-array v0, v0, [F

    iput-object v0, p0, Lmiui/telephony/dtmf/DTMFUtil;->nf:[F

    .line 63
    iput p1, p0, Lmiui/telephony/dtmf/DTMFUtil;->ng:I

    .line 64
    invoke-direct {p0}, Lmiui/telephony/dtmf/DTMFUtil;->aR()V

    .line 65
    if-lez p2, :cond_0

    :goto_0
    iput p2, p0, Lmiui/telephony/dtmf/DTMFUtil;->nh:I

    .line 66
    return-void

    .line 65
    :cond_0
    const/4 p2, 0x3

    goto :goto_0
.end method

.method private E(I)I
    .locals 3

    .prologue
    const/4 v1, -0x1

    .line 222
    sget-object v0, Lmiui/telephony/dtmf/DTMFUtil;->mX:[I

    array-length v0, v0

    sget-object v2, Lmiui/telephony/dtmf/DTMFUtil;->mY:[I

    array-length v2, v2

    if-eq v0, v2, :cond_0

    .line 223
    const-string v0, "DTMFUtil"

    const-string v2, "the number of SAMPLE_RATE_LIST and DECIBEL_THRESHOLD_LIST can not match"

    invoke-static {v0, v2}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I

    move v0, v1

    .line 237
    :goto_0
    return v0

    .line 228
    :cond_0
    const/4 v0, 0x0

    :goto_1
    sget-object v2, Lmiui/telephony/dtmf/DTMFUtil;->mX:[I

    array-length v2, v2

    if-ge v0, v2, :cond_1

    .line 229
    sget-object v2, Lmiui/telephony/dtmf/DTMFUtil;->mX:[I

    aget v2, v2, v0

    if-ne p1, v2, :cond_2

    .line 233
    :cond_1
    sget-object v2, Lmiui/telephony/dtmf/DTMFUtil;->mX:[I

    array-length v2, v2

    if-lt v0, v2, :cond_3

    .line 234
    const-string v0, "DTMFUtil"

    const-string v2, "can not find db threshold"

    invoke-static {v0, v2}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I

    move v0, v1

    .line 235
    goto :goto_0

    .line 228
    :cond_2
    add-int/lit8 v0, v0, 0x1

    goto :goto_1

    .line 237
    :cond_3
    sget-object v1, Lmiui/telephony/dtmf/DTMFUtil;->mY:[I

    aget v0, v1, v0

    goto :goto_0
.end method

.method private a([BIZ)[F
    .locals 10
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    const/4 v1, 0x0

    .line 132
    .line 135
    const/16 v0, 0x100

    new-array v4, v0, [B

    .line 136
    iget v0, p0, Lmiui/telephony/dtmf/DTMFUtil;->nh:I

    mul-int/lit8 v0, v0, 0x2

    new-array v5, v0, [F

    .line 137
    const/4 v0, 0x2

    new-array v0, v0, [F

    .line 138
    new-instance v6, Lmiui/telephony/dtmf/DTMFAudioInputStream;

    invoke-direct {v6, p1}, Lmiui/telephony/dtmf/DTMFAudioInputStream;-><init>([B)V

    .line 140
    new-instance v7, Lmiui/telephony/dtmf/DTMFDataConveter;

    invoke-direct {v7, p2, p3}, Lmiui/telephony/dtmf/DTMFDataConveter;-><init>(IZ)V

    move v0, v1

    move v2, v1

    .line 141
    :goto_0
    iget v3, p0, Lmiui/telephony/dtmf/DTMFUtil;->nh:I

    if-ge v0, v3, :cond_0

    .line 142
    array-length v3, p1

    if-lt v2, v3, :cond_1

    .line 158
    :cond_0
    invoke-virtual {v6}, Lmiui/telephony/dtmf/DTMFAudioInputStream;->close()V

    .line 159
    iget v1, p0, Lmiui/telephony/dtmf/DTMFUtil;->nh:I

    if-ne v0, v1, :cond_4

    .line 160
    mul-int/lit8 v0, v0, 0x2

    invoke-direct {p0, v5, v0}, Lmiui/telephony/dtmf/DTMFUtil;->a([FI)[F

    move-result-object v0

    .line 162
    :goto_1
    return-object v0

    .line 146
    :cond_1
    add-int/lit16 v3, v2, 0x100

    array-length v8, p1

    if-le v3, v8, :cond_3

    array-length v3, p1

    .line 148
    :goto_2
    sub-int v2, v3, v2

    invoke-virtual {v6, v4, v2}, Lmiui/telephony/dtmf/DTMFAudioInputStream;->read([BI)I

    .line 150
    invoke-virtual {v7, v4}, Lmiui/telephony/dtmf/DTMFDataConveter;->byteToFloat([B)[F

    move-result-object v2

    .line 151
    invoke-direct {p0, v2}, Lmiui/telephony/dtmf/DTMFUtil;->a([F)[F

    move-result-object v2

    .line 152
    if-eqz v2, :cond_2

    .line 153
    mul-int/lit8 v8, v0, 0x2

    aget v9, v2, v1

    aput v9, v5, v8

    .line 154
    mul-int/lit8 v8, v0, 0x2

    add-int/lit8 v8, v8, 0x1

    const/4 v9, 0x1

    aget v2, v2, v9

    aput v2, v5, v8

    .line 155
    add-int/lit8 v0, v0, 0x1

    :cond_2
    move v2, v3

    .line 157
    goto :goto_0

    .line 146
    :cond_3
    add-int/lit16 v3, v2, 0x100

    goto :goto_2

    .line 162
    :cond_4
    const/4 v0, 0x0

    goto :goto_1
.end method

.method private a([F)[F
    .locals 13

    .prologue
    const/4 v11, 0x1

    const/4 v1, 0x0

    .line 86
    sget-object v0, Lmiui/telephony/dtmf/DTMFUtil;->mW:[F

    array-length v0, v0

    new-array v6, v0, [Z

    .line 87
    const/4 v0, 0x2

    new-array v5, v0, [F

    .line 88
    const/high16 v0, -0x40800000

    aput v0, v5, v11

    aput v0, v5, v1

    .line 90
    iget v0, p0, Lmiui/telephony/dtmf/DTMFUtil;->ng:I

    invoke-direct {p0, v0}, Lmiui/telephony/dtmf/DTMFUtil;->E(I)I

    move-result v7

    .line 91
    const/4 v0, -0x1

    if-ne v7, v0, :cond_0

    .line 92
    const-string v0, "DTMFUtil"

    const-string v1, "can not get threshold"

    invoke-static {v0, v1}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I

    move-object v0, v5

    .line 126
    :goto_0
    return-object v0

    .line 97
    :cond_0
    sget-object v0, Lmiui/telephony/dtmf/DTMFUtil;->mW:[F

    array-length v0, v0

    new-array v8, v0, [F

    move v0, v1

    .line 98
    :goto_1
    sget-object v2, Lmiui/telephony/dtmf/DTMFUtil;->mW:[F

    array-length v2, v2

    if-ge v0, v2, :cond_3

    .line 99
    const/4 v2, 0x0

    move v3, v2

    move v4, v2

    move v2, v1

    .line 100
    :goto_2
    array-length v9, p1

    if-ge v2, v9, :cond_1

    .line 103
    iget-object v9, p0, Lmiui/telephony/dtmf/DTMFUtil;->ne:[F

    aget v9, v9, v0

    mul-float/2addr v9, v4

    sub-float v3, v9, v3

    aget v9, p1, v2

    add-float/2addr v3, v9

    .line 100
    add-int/lit8 v2, v2, 0x1

    move v12, v4

    move v4, v3

    move v3, v12

    goto :goto_2

    .line 106
    :cond_1
    const-wide/high16 v9, 0x4034000000000000L

    iget-object v2, p0, Lmiui/telephony/dtmf/DTMFUtil;->nf:[F

    aget v2, v2, v0

    mul-float/2addr v2, v3

    sub-float v2, v4, v2

    invoke-static {v2}, Ljava/lang/Math;->abs(F)F

    move-result v2

    float-to-double v2, v2

    invoke-static {v2, v3}, Ljava/lang/Math;->log10(D)D

    move-result-wide v2

    mul-double/2addr v2, v9

    .line 108
    double-to-float v2, v2

    aput v2, v8, v0

    .line 110
    aget v2, v8, v0

    int-to-float v3, v7

    cmpl-float v2, v2, v3

    if-lez v2, :cond_2

    .line 111
    aput-boolean v11, v6, v0

    .line 98
    :cond_2
    add-int/lit8 v0, v0, 0x1

    goto :goto_1

    :cond_3
    move v0, v1

    .line 115
    :goto_3
    array-length v2, v6

    div-int/lit8 v2, v2, 0x2

    if-ge v0, v2, :cond_5

    .line 116
    add-int/lit8 v2, v0, 0x5

    sget-object v3, Lmiui/telephony/dtmf/DTMFUtil;->mW:[F

    array-length v3, v3

    rem-int/2addr v2, v3

    .line 117
    aget-boolean v3, v6, v0

    if-eqz v3, :cond_4

    aget-boolean v3, v6, v2

    if-eqz v3, :cond_4

    .line 118
    sget-object v3, Lmiui/telephony/dtmf/DTMFUtil;->mW:[F

    aget v3, v3, v0

    aput v3, v5, v1

    .line 119
    sget-object v3, Lmiui/telephony/dtmf/DTMFUtil;->mW:[F

    aget v2, v3, v2

    aput v2, v5, v11

    .line 120
    invoke-direct {p0, v5}, Lmiui/telephony/dtmf/DTMFUtil;->b([F)Z

    move-result v2

    if-eqz v2, :cond_4

    move-object v0, v5

    .line 121
    goto :goto_0

    .line 115
    :cond_4
    add-int/lit8 v0, v0, 0x1

    goto :goto_3

    .line 126
    :cond_5
    const/4 v0, 0x0

    goto :goto_0
.end method

.method private a([FI)[F
    .locals 7

    .prologue
    const/4 v2, 0x2

    const/4 v3, 0x1

    const/4 v1, 0x0

    .line 183
    array-length v0, p1

    if-gt v0, v2, :cond_0

    .line 204
    :goto_0
    return-object p1

    .line 186
    :cond_0
    new-array v5, v2, [F

    .line 187
    aget v0, p1, v1

    .line 188
    aget v4, p1, v3

    move v2, v0

    move v0, v1

    .line 189
    :goto_1
    array-length v6, p1

    div-int/lit8 v6, v6, 0x2

    if-ge v0, v6, :cond_2

    .line 190
    aget v6, p1, v0

    cmpl-float v6, v2, v6

    if-eqz v6, :cond_1

    .line 191
    aget v2, p1, v0

    .line 189
    :cond_1
    add-int/lit8 v0, v0, 0x2

    goto :goto_1

    :cond_2
    move v0, v3

    .line 194
    :goto_2
    array-length v6, p1

    div-int/lit8 v6, v6, 0x2

    if-ge v0, v6, :cond_4

    .line 195
    aget v6, p1, v0

    cmpl-float v6, v4, v6

    if-eqz v6, :cond_3

    .line 196
    aget v4, p1, v0

    .line 194
    :cond_3
    add-int/lit8 v0, v0, 0x2

    goto :goto_2

    .line 199
    :cond_4
    aget v0, p1, v1

    cmpl-float v0, v2, v0

    if-nez v0, :cond_5

    aget v0, p1, v3

    cmpl-float v0, v4, v0

    if-nez v0, :cond_5

    .line 200
    aget v0, p1, v1

    aput v0, v5, v1

    .line 201
    aget v0, p1, v3

    aput v0, v5, v3

    move-object p1, v5

    .line 202
    goto :goto_0

    .line 204
    :cond_5
    const/4 p1, 0x0

    goto :goto_0
.end method

.method private static a([FII)[F
    .locals 12

    .prologue
    const/4 v0, 0x0

    const-wide v6, 0x401921fb54442d18L

    const-wide/high16 v10, 0x3fe0000000000000L

    .line 268
    mul-int v1, p1, p2

    div-int/lit16 v1, v1, 0x3e8

    .line 269
    array-length v2, p0

    const/4 v3, 0x2

    if-eq v2, v3, :cond_0

    .line 270
    const-string v0, "DTMFUtil"

    const-string v1, "parameter buffer is null"

    invoke-static {v0, v1}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I

    .line 271
    const/4 v0, 0x0

    .line 285
    :goto_0
    return-object v0

    .line 276
    :cond_0
    aget v2, p0, v0

    float-to-double v2, v2

    mul-double/2addr v2, v6

    .line 277
    const/4 v4, 0x1

    aget v4, p0, v4

    float-to-double v4, v4

    mul-double/2addr v4, v6

    .line 278
    new-array v1, v1, [F

    .line 279
    :goto_1
    array-length v6, v1

    if-ge v0, v6, :cond_1

    .line 280
    int-to-double v6, v0

    int-to-double v8, p1

    div-double/2addr v6, v8

    .line 281
    mul-double v8, v2, v6

    invoke-static {v8, v9}, Ljava/lang/Math;->sin(D)D

    move-result-wide v8

    mul-double/2addr v8, v10

    .line 282
    mul-double/2addr v6, v4

    invoke-static {v6, v7}, Ljava/lang/Math;->sin(D)D

    move-result-wide v6

    mul-double/2addr v6, v10

    .line 283
    add-double/2addr v6, v8

    double-to-float v6, v6

    aput v6, v1, v0

    .line 279
    add-int/lit8 v0, v0, 0x1

    goto :goto_1

    :cond_1
    move-object v0, v1

    .line 285
    goto :goto_0
.end method

.method private aR()V
    .locals 7

    .prologue
    .line 167
    iget v0, p0, Lmiui/telephony/dtmf/DTMFUtil;->ng:I

    if-nez v0, :cond_1

    .line 168
    const-string v0, "DTMFUtil"

    const-string v1, "fail to dispatching funtion initPrecalculatedCosines: you need to set mSampleRate"

    invoke-static {v0, v1}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I

    .line 180
    :cond_0
    return-void

    .line 172
    :cond_1
    const/4 v0, 0x0

    :goto_0
    sget-object v1, Lmiui/telephony/dtmf/DTMFUtil;->mW:[F

    array-length v1, v1

    if-ge v0, v1, :cond_0

    .line 173
    const-wide/high16 v1, 0x4000000000000000L

    const-wide v3, 0x401921fb54442d18L

    sget-object v5, Lmiui/telephony/dtmf/DTMFUtil;->mW:[F

    aget v5, v5, v0

    float-to-double v5, v5

    mul-double/2addr v3, v5

    iget v5, p0, Lmiui/telephony/dtmf/DTMFUtil;->ng:I

    int-to-double v5, v5

    div-double/2addr v3, v5

    invoke-static {v3, v4}, Ljava/lang/Math;->cos(D)D

    move-result-wide v3

    mul-double/2addr v1, v3

    .line 175
    iget-object v3, p0, Lmiui/telephony/dtmf/DTMFUtil;->ne:[F

    double-to-float v1, v1

    aput v1, v3, v0

    .line 176
    const-wide v1, -0x3fe6de04abbbd2e8L

    sget-object v3, Lmiui/telephony/dtmf/DTMFUtil;->mW:[F

    aget v3, v3, v0

    float-to-double v3, v3

    mul-double/2addr v1, v3

    iget v3, p0, Lmiui/telephony/dtmf/DTMFUtil;->ng:I

    int-to-double v3, v3

    div-double/2addr v1, v3

    invoke-static {v1, v2}, Ljava/lang/Math;->exp(D)D

    move-result-wide v1

    .line 178
    iget-object v3, p0, Lmiui/telephony/dtmf/DTMFUtil;->nf:[F

    double-to-float v1, v1

    aput v1, v3, v0

    .line 172
    add-int/lit8 v0, v0, 0x1

    goto :goto_0
.end method

.method private b([F)Z
    .locals 7

    .prologue
    const/4 v4, 0x1

    const/4 v1, 0x0

    .line 241
    if-nez p1, :cond_1

    .line 242
    const-string v0, "DTMFUtil"

    const-string v2, "null parameter"

    invoke-static {v0, v2}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I

    .line 263
    :cond_0
    :goto_0
    return v1

    .line 245
    :cond_1
    const/high16 v0, 0x43860000

    .line 246
    aget v2, p1, v4

    aget v3, p1, v1

    sub-float/2addr v2, v3

    cmpg-float v0, v2, v0

    if-ltz v0, :cond_0

    move v0, v1

    move v2, v1

    move v3, v1

    .line 252
    :goto_1
    sget-object v5, Lmiui/telephony/dtmf/DTMFUtil;->mW:[F

    array-length v5, v5

    if-ge v0, v5, :cond_4

    .line 253
    aget v5, p1, v1

    sget-object v6, Lmiui/telephony/dtmf/DTMFUtil;->mW:[F

    aget v6, v6, v0

    cmpl-float v5, v5, v6

    if-nez v5, :cond_2

    move v3, v0

    .line 256
    :cond_2
    aget v5, p1, v4

    sget-object v6, Lmiui/telephony/dtmf/DTMFUtil;->mW:[F

    aget v6, v6, v0

    cmpl-float v5, v5, v6

    if-nez v5, :cond_3

    move v2, v0

    .line 252
    :cond_3
    add-int/lit8 v0, v0, 0x1

    goto :goto_1

    .line 260
    :cond_4
    sub-int v0, v2, v3

    const/4 v2, 0x5

    if-ne v0, v2, :cond_0

    move v1, v4

    .line 261
    goto :goto_0
.end method

.method public static getDualFrequence(C)[F
    .locals 7

    .prologue
    const v6, 0x44b8a000

    const/high16 v5, 0x44a70000

    const v4, 0x44972000

    const/4 v3, 0x1

    const/4 v2, 0x0

    .line 289
    const/4 v0, 0x2

    new-array v0, v0, [F

    .line 290
    packed-switch p0, :pswitch_data_0

    .line 340
    :pswitch_0
    const/high16 v1, -0x40800000

    aput v1, v0, v3

    aput v1, v0, v2

    .line 342
    :goto_0
    return-object v0

    .line 292
    :pswitch_1
    const v1, 0x446b4000

    aput v1, v0, v2

    .line 293
    aput v5, v0, v3

    goto :goto_0

    .line 296
    :pswitch_2
    const v1, 0x442e4000

    aput v1, v0, v2

    .line 297
    aput v4, v0, v3

    goto :goto_0

    .line 300
    :pswitch_3
    const v1, 0x442e4000

    aput v1, v0, v2

    .line 301
    aput v5, v0, v3

    goto :goto_0

    .line 304
    :pswitch_4
    const v1, 0x442e4000

    aput v1, v0, v2

    .line 305
    aput v6, v0, v3

    goto :goto_0

    .line 308
    :pswitch_5
    const v1, 0x44408000

    aput v1, v0, v2

    .line 309
    aput v4, v0, v3

    goto :goto_0

    .line 312
    :pswitch_6
    const v1, 0x44408000

    aput v1, v0, v2

    .line 313
    aput v5, v0, v3

    goto :goto_0

    .line 316
    :pswitch_7
    const v1, 0x44408000

    aput v1, v0, v2

    .line 317
    aput v6, v0, v3

    goto :goto_0

    .line 320
    :pswitch_8
    const/high16 v1, 0x44550000

    aput v1, v0, v2

    .line 321
    aput v4, v0, v3

    goto :goto_0

    .line 324
    :pswitch_9
    const/high16 v1, 0x44550000

    aput v1, v0, v2

    .line 325
    aput v5, v0, v3

    goto :goto_0

    .line 328
    :pswitch_a
    const/high16 v1, 0x44550000

    aput v1, v0, v2

    .line 329
    aput v6, v0, v3

    goto :goto_0

    .line 332
    :pswitch_b
    const v1, 0x446b4000

    aput v1, v0, v2

    .line 333
    aput v4, v0, v3

    goto :goto_0

    .line 336
    :pswitch_c
    const v1, 0x446b4000

    aput v1, v0, v2

    .line 337
    aput v6, v0, v3

    goto :goto_0

    .line 290
    :pswitch_data_0
    .packed-switch 0x23
        :pswitch_c
        :pswitch_0
        :pswitch_0
        :pswitch_0
        :pswitch_0
        :pswitch_0
        :pswitch_0
        :pswitch_b
        :pswitch_0
        :pswitch_0
        :pswitch_0
        :pswitch_0
        :pswitch_0
        :pswitch_1
        :pswitch_2
        :pswitch_3
        :pswitch_4
        :pswitch_5
        :pswitch_6
        :pswitch_7
        :pswitch_8
        :pswitch_9
        :pswitch_a
    .end packed-switch
.end method

.method public static getTargetHighFrequency()F
    .locals 1

    .prologue
    .line 55
    const v0, 0x44866000

    return v0
.end method

.method public static getTargetLowFrequency()F
    .locals 1

    .prologue
    .line 51
    const/high16 v0, 0x44160000

    return v0
.end method

.method public static getVersion()I
    .locals 1

    .prologue
    .line 47
    const/4 v0, 0x2

    return v0
.end method


# virtual methods
.method public getHealthy()I
    .locals 1

    .prologue
    .line 78
    iget v0, p0, Lmiui/telephony/dtmf/DTMFUtil;->nh:I

    return v0
.end method

.method public getSampleRate()I
    .locals 1

    .prologue
    .line 69
    iget v0, p0, Lmiui/telephony/dtmf/DTMFUtil;->ng:I

    return v0
.end method

.method public parseFrequency([BIZ)[F
    .locals 2
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 214
    if-nez p1, :cond_0

    .line 215
    const-string v0, "DTMFUtil"

    const-string v1, "parameter error: null"

    invoke-static {v0, v1}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I

    .line 216
    const/4 v0, 0x0

    .line 218
    :goto_0
    return-object v0

    :cond_0
    invoke-direct {p0, p1, p2, p3}, Lmiui/telephony/dtmf/DTMFUtil;->a([BIZ)[F

    move-result-object v0

    goto :goto_0
.end method

.method public setHealthy(I)V
    .locals 0

    .prologue
    .line 82
    iput p1, p0, Lmiui/telephony/dtmf/DTMFUtil;->nh:I

    .line 83
    return-void
.end method

.method public setSampleRate(I)V
    .locals 0

    .prologue
    .line 73
    iput p1, p0, Lmiui/telephony/dtmf/DTMFUtil;->ng:I

    .line 74
    invoke-direct {p0}, Lmiui/telephony/dtmf/DTMFUtil;->aR()V

    .line 75
    return-void
.end method
