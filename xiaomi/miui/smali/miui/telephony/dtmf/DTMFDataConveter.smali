.class public Lmiui/telephony/dtmf/DTMFDataConveter;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field private static final LOG_TAG:Ljava/lang/String; = "DTMFDataConveter"


# instance fields
.field private di:I

.field private dj:Z


# direct methods
.method public constructor <init>()V
    .locals 2

    .prologue
    .line 18
    const/16 v0, 0x8

    const/4 v1, 0x1

    invoke-direct {p0, v0, v1}, Lmiui/telephony/dtmf/DTMFDataConveter;-><init>(IZ)V

    .line 19
    return-void
.end method

.method public constructor <init>(IZ)V
    .locals 0

    .prologue
    .line 21
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 22
    invoke-virtual {p0, p1}, Lmiui/telephony/dtmf/DTMFDataConveter;->setBitPerSample(I)V

    .line 23
    invoke-virtual {p0, p2}, Lmiui/telephony/dtmf/DTMFDataConveter;->setSign(Z)V

    .line 24
    return-void
.end method


# virtual methods
.method public byteToFloat([B)[F
    .locals 5

    .prologue
    const v4, 0x38000100

    .line 43
    if-nez p1, :cond_0

    .line 44
    const-string v0, "DTMFDataConveter"

    const-string v1, "bit mode can not match"

    invoke-static {v0, v1}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I

    .line 45
    const/4 v0, 0x0

    .line 67
    :goto_0
    return-object v0

    .line 47
    :cond_0
    array-length v0, p1

    invoke-virtual {p0}, Lmiui/telephony/dtmf/DTMFDataConveter;->getBitPerSample()I

    move-result v1

    div-int/lit8 v1, v1, 0x8

    div-int/2addr v0, v1

    new-array v1, v0, [F

    .line 48
    const/4 v0, 0x0

    :goto_1
    array-length v2, v1

    if-ge v0, v2, :cond_5

    .line 49
    invoke-virtual {p0}, Lmiui/telephony/dtmf/DTMFDataConveter;->getBitPerSample()I

    move-result v2

    const/16 v3, 0x8

    if-ne v2, v3, :cond_3

    .line 50
    iget-boolean v2, p0, Lmiui/telephony/dtmf/DTMFDataConveter;->dj:Z

    if-eqz v2, :cond_2

    .line 51
    aget-byte v2, p1, v0

    int-to-float v2, v2

    const/high16 v3, 0x42fe0000

    div-float/2addr v2, v3

    aput v2, v1, v0

    .line 48
    :cond_1
    :goto_2
    add-int/lit8 v0, v0, 0x1

    goto :goto_1

    .line 53
    :cond_2
    aget-byte v2, p1, v0

    and-int/lit16 v2, v2, 0xff

    add-int/lit8 v2, v2, -0x7f

    int-to-float v2, v2

    const v3, 0x3c010204

    mul-float/2addr v2, v3

    aput v2, v1, v0

    goto :goto_2

    .line 56
    :cond_3
    invoke-virtual {p0}, Lmiui/telephony/dtmf/DTMFDataConveter;->getBitPerSample()I

    move-result v2

    const/16 v3, 0x10

    if-ne v2, v3, :cond_1

    .line 57
    iget-boolean v2, p0, Lmiui/telephony/dtmf/DTMFDataConveter;->dj:Z

    if-eqz v2, :cond_4

    .line 58
    mul-int/lit8 v2, v0, 0x2

    aget-byte v2, p1, v2

    and-int/lit16 v2, v2, 0xff

    mul-int/lit8 v3, v0, 0x2

    add-int/lit8 v3, v3, 0x1

    aget-byte v3, p1, v3

    shl-int/lit8 v3, v3, 0x8

    or-int/2addr v2, v3

    int-to-short v2, v2

    int-to-float v2, v2

    mul-float/2addr v2, v4

    aput v2, v1, v0

    goto :goto_2

    .line 61
    :cond_4
    mul-int/lit8 v2, v0, 0x2

    aget-byte v2, p1, v2

    and-int/lit16 v2, v2, 0xff

    mul-int/lit8 v3, v0, 0x2

    add-int/lit8 v3, v3, 0x1

    aget-byte v3, p1, v3

    and-int/lit16 v3, v3, 0xff

    shl-int/lit8 v3, v3, 0x8

    or-int/2addr v2, v3

    .line 63
    add-int/lit16 v2, v2, -0x7fff

    int-to-float v2, v2

    mul-float/2addr v2, v4

    aput v2, v1, v0

    goto :goto_2

    :cond_5
    move-object v0, v1

    .line 67
    goto :goto_0
.end method

.method public floatToByte([F)[B
    .locals 8

    .prologue
    const-wide v6, 0x40dfffc000000000L

    const/high16 v5, 0x42fe0000

    .line 71
    if-nez p1, :cond_0

    .line 72
    const-string v0, "DTMFDataConveter"

    const-string v1, "bit mode can not match"

    invoke-static {v0, v1}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I

    .line 73
    const/4 v0, 0x0

    .line 95
    :goto_0
    return-object v0

    .line 75
    :cond_0
    array-length v0, p1

    invoke-virtual {p0}, Lmiui/telephony/dtmf/DTMFDataConveter;->getBitPerSample()I

    move-result v1

    div-int/lit8 v1, v1, 0x8

    mul-int/2addr v0, v1

    new-array v1, v0, [B

    .line 76
    const/4 v0, 0x0

    :goto_1
    array-length v2, p1

    if-ge v0, v2, :cond_5

    .line 77
    invoke-virtual {p0}, Lmiui/telephony/dtmf/DTMFDataConveter;->getBitPerSample()I

    move-result v2

    const/16 v3, 0x8

    if-ne v2, v3, :cond_3

    .line 78
    iget-boolean v2, p0, Lmiui/telephony/dtmf/DTMFDataConveter;->dj:Z

    if-eqz v2, :cond_2

    .line 79
    aget v2, p1, v0

    mul-float/2addr v2, v5

    float-to-int v2, v2

    int-to-byte v2, v2

    aput-byte v2, v1, v0

    .line 76
    :cond_1
    :goto_2
    add-int/lit8 v0, v0, 0x1

    goto :goto_1

    .line 81
    :cond_2
    aget v2, p1, v0

    mul-float/2addr v2, v5

    add-float/2addr v2, v5

    float-to-int v2, v2

    int-to-byte v2, v2

    aput-byte v2, v1, v0

    goto :goto_2

    .line 83
    :cond_3
    invoke-virtual {p0}, Lmiui/telephony/dtmf/DTMFDataConveter;->getBitPerSample()I

    move-result v2

    const/16 v3, 0x10

    if-ne v2, v3, :cond_1

    .line 84
    iget-boolean v2, p0, Lmiui/telephony/dtmf/DTMFDataConveter;->dj:Z

    if-eqz v2, :cond_4

    .line 85
    aget v2, p1, v0

    float-to-double v2, v2

    mul-double/2addr v2, v6

    double-to-int v2, v2

    .line 86
    mul-int/lit8 v3, v0, 0x2

    int-to-byte v4, v2

    aput-byte v4, v1, v3

    .line 87
    mul-int/lit8 v3, v0, 0x2

    add-int/lit8 v3, v3, 0x1

    ushr-int/lit8 v2, v2, 0x8

    int-to-byte v2, v2

    aput-byte v2, v1, v3

    goto :goto_2

    .line 89
    :cond_4
    aget v2, p1, v0

    float-to-double v2, v2

    mul-double/2addr v2, v6

    double-to-int v2, v2

    add-int/lit16 v2, v2, 0x7fff

    .line 90
    mul-int/lit8 v3, v0, 0x2

    int-to-byte v4, v2

    aput-byte v4, v1, v3

    .line 91
    mul-int/lit8 v3, v0, 0x2

    add-int/lit8 v3, v3, 0x1

    ushr-int/lit8 v2, v2, 0x8

    int-to-byte v2, v2

    aput-byte v2, v1, v3

    goto :goto_2

    :cond_5
    move-object v0, v1

    .line 95
    goto :goto_0
.end method

.method public getBitPerSample()I
    .locals 1

    .prologue
    .line 27
    iget v0, p0, Lmiui/telephony/dtmf/DTMFDataConveter;->di:I

    return v0
.end method

.method public getSign()Z
    .locals 1

    .prologue
    .line 35
    iget-boolean v0, p0, Lmiui/telephony/dtmf/DTMFDataConveter;->dj:Z

    return v0
.end method

.method public setBitPerSample(I)V
    .locals 0

    .prologue
    .line 31
    iput p1, p0, Lmiui/telephony/dtmf/DTMFDataConveter;->di:I

    .line 32
    return-void
.end method

.method public setSign(Z)V
    .locals 0

    .prologue
    .line 39
    iput-boolean p1, p0, Lmiui/telephony/dtmf/DTMFDataConveter;->dj:Z

    .line 40
    return-void
.end method
