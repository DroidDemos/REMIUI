.class public Lmiui/telephony/dtmf/DTMFAudioInputStream;
.super Ljava/io/InputStream;
.source "SourceFile"


# static fields
.field private static final LOG_TAG:Ljava/lang/String; = "DTMFAudioInputStream"


# instance fields
.field private jh:[B

.field private ji:I


# direct methods
.method public constructor <init>([B)V
    .locals 4

    .prologue
    const/4 v1, 0x0

    .line 20
    invoke-direct {p0}, Ljava/io/InputStream;-><init>()V

    .line 21
    if-nez p1, :cond_0

    .line 22
    const-string v0, "DTMFAudioInputStream"

    const-string v1, "parameter error"

    invoke-static {v0, v1}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I

    .line 30
    :goto_0
    return-void

    .line 25
    :cond_0
    array-length v0, p1

    new-array v0, v0, [B

    iput-object v0, p0, Lmiui/telephony/dtmf/DTMFAudioInputStream;->jh:[B

    move v0, v1

    .line 26
    :goto_1
    array-length v2, p1

    if-ge v0, v2, :cond_1

    .line 27
    iget-object v2, p0, Lmiui/telephony/dtmf/DTMFAudioInputStream;->jh:[B

    aget-byte v3, p1, v0

    aput-byte v3, v2, v0

    .line 26
    add-int/lit8 v0, v0, 0x1

    goto :goto_1

    .line 29
    :cond_1
    iput v1, p0, Lmiui/telephony/dtmf/DTMFAudioInputStream;->ji:I

    goto :goto_0
.end method


# virtual methods
.method public read()I
    .locals 3
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 51
    iget v0, p0, Lmiui/telephony/dtmf/DTMFAudioInputStream;->ji:I

    iget-object v1, p0, Lmiui/telephony/dtmf/DTMFAudioInputStream;->jh:[B

    array-length v1, v1

    if-lt v0, v1, :cond_0

    .line 52
    const/4 v0, -0x1

    .line 54
    :goto_0
    return v0

    :cond_0
    iget-object v0, p0, Lmiui/telephony/dtmf/DTMFAudioInputStream;->jh:[B

    iget v1, p0, Lmiui/telephony/dtmf/DTMFAudioInputStream;->ji:I

    add-int/lit8 v2, v1, 0x1

    iput v2, p0, Lmiui/telephony/dtmf/DTMFAudioInputStream;->ji:I

    aget-byte v0, v0, v1

    goto :goto_0
.end method

.method public read([BI)I
    .locals 3
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 33
    iget-object v0, p0, Lmiui/telephony/dtmf/DTMFAudioInputStream;->jh:[B

    if-eqz v0, :cond_0

    if-gtz p2, :cond_2

    .line 34
    :cond_0
    const-string v0, "DTMFAudioInputStream"

    const-string v1, "paramenter error:fail to get subdatalist"

    invoke-static {v0, v1}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I

    .line 36
    const/4 p2, -0x1

    .line 46
    :cond_1
    return p2

    .line 40
    :cond_2
    iget-object v0, p0, Lmiui/telephony/dtmf/DTMFAudioInputStream;->jh:[B

    array-length v0, v0

    iget v1, p0, Lmiui/telephony/dtmf/DTMFAudioInputStream;->ji:I

    sub-int/2addr v0, v1

    if-ge v0, p2, :cond_3

    .line 41
    iget-object v0, p0, Lmiui/telephony/dtmf/DTMFAudioInputStream;->jh:[B

    array-length v0, v0

    iget v1, p0, Lmiui/telephony/dtmf/DTMFAudioInputStream;->ji:I

    sub-int/2addr v0, v1

    .line 43
    :goto_0
    const/4 v1, 0x0

    :goto_1
    if-ge v1, v0, :cond_1

    .line 44
    invoke-virtual {p0}, Lmiui/telephony/dtmf/DTMFAudioInputStream;->read()I

    move-result v2

    int-to-byte v2, v2

    aput-byte v2, p1, v1

    .line 43
    add-int/lit8 v1, v1, 0x1

    goto :goto_1

    :cond_3
    move v0, p2

    goto :goto_0
.end method
