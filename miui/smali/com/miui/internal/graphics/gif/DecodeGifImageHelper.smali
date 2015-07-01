.class public Lcom/miui/internal/graphics/gif/DecodeGifImageHelper;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/internal/graphics/gif/DecodeGifImageHelper$GifFrame;,
        Lcom/miui/internal/graphics/gif/DecodeGifImageHelper$GifDecodeResult;
    }
.end annotation


# static fields
.field public static final MESSAGE_WHAT_DECODE_FRAMES:I = 0x1


# instance fields
.field private JG:I

.field private JH:Lcom/miui/internal/graphics/gif/a;

.field public mDecodeFrameHandler:Landroid/os/Handler;

.field public mDecodedAllFrames:Z

.field public mFrames:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List",
            "<",
            "Lcom/miui/internal/graphics/gif/DecodeGifImageHelper$GifFrame;",
            ">;"
        }
    .end annotation
.end field

.field public mGifSource:Lmiui/io/ResettableInputStream;

.field public mMaxDecodeSize:J

.field public mRealFrameCount:I


# direct methods
.method public constructor <init>()V
    .locals 2

    .prologue
    .line 136
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 140
    const-wide/32 v0, 0x100000

    iput-wide v0, p0, Lcom/miui/internal/graphics/gif/DecodeGifImageHelper;->mMaxDecodeSize:J

    .line 166
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/miui/internal/graphics/gif/DecodeGifImageHelper;->mFrames:Ljava/util/List;

    return-void
.end method

.method static synthetic a(Lcom/miui/internal/graphics/gif/DecodeGifImageHelper;)Lcom/miui/internal/graphics/gif/a;
    .locals 1

    .prologue
    .line 136
    iget-object v0, p0, Lcom/miui/internal/graphics/gif/DecodeGifImageHelper;->JH:Lcom/miui/internal/graphics/gif/a;

    return-object v0
.end method

.method private ag(I)I
    .locals 1

    .prologue
    .line 189
    iget v0, p0, Lcom/miui/internal/graphics/gif/DecodeGifImageHelper;->mRealFrameCount:I

    if-nez v0, :cond_0

    .line 194
    :goto_0
    return p1

    :cond_0
    iget v0, p0, Lcom/miui/internal/graphics/gif/DecodeGifImageHelper;->mRealFrameCount:I

    rem-int/2addr p1, v0

    goto :goto_0
.end method

.method private dV()I
    .locals 2

    .prologue
    .line 184
    iget-object v0, p0, Lcom/miui/internal/graphics/gif/DecodeGifImageHelper;->mFrames:Ljava/util/List;

    iget-object v1, p0, Lcom/miui/internal/graphics/gif/DecodeGifImageHelper;->mFrames:Ljava/util/List;

    invoke-interface {v1}, Ljava/util/List;->size()I

    move-result v1

    add-int/lit8 v1, v1, -0x1

    invoke-interface {v0, v1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/graphics/gif/DecodeGifImageHelper$GifFrame;

    .line 185
    iget v0, v0, Lcom/miui/internal/graphics/gif/DecodeGifImageHelper$GifFrame;->mIndex:I

    return v0
.end method

.method public static decode(Lmiui/io/ResettableInputStream;JI)Lcom/miui/internal/graphics/gif/DecodeGifImageHelper$GifDecodeResult;
    .locals 3

    .prologue
    const/4 v0, 0x0

    .line 281
    new-instance v1, Lcom/miui/internal/graphics/gif/DecodeGifImageHelper$GifDecodeResult;

    invoke-direct {v1}, Lcom/miui/internal/graphics/gif/DecodeGifImageHelper$GifDecodeResult;-><init>()V

    .line 282
    const/4 v2, 0x0

    iput-object v2, v1, Lcom/miui/internal/graphics/gif/DecodeGifImageHelper$GifDecodeResult;->mGifDecoder:Lcom/miui/internal/graphics/gif/GifDecoder;

    .line 283
    iput-boolean v0, v1, Lcom/miui/internal/graphics/gif/DecodeGifImageHelper$GifDecodeResult;->mIsDecodeOk:Z

    .line 286
    :try_start_0
    invoke-virtual {p0}, Lmiui/io/ResettableInputStream;->reset()V
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_0

    .line 291
    new-instance v2, Lcom/miui/internal/graphics/gif/GifDecoder;

    invoke-direct {v2}, Lcom/miui/internal/graphics/gif/GifDecoder;-><init>()V

    iput-object v2, v1, Lcom/miui/internal/graphics/gif/DecodeGifImageHelper$GifDecodeResult;->mGifDecoder:Lcom/miui/internal/graphics/gif/GifDecoder;

    .line 292
    iget-object v2, v1, Lcom/miui/internal/graphics/gif/DecodeGifImageHelper$GifDecodeResult;->mGifDecoder:Lcom/miui/internal/graphics/gif/GifDecoder;

    .line 294
    invoke-virtual {v2, p3}, Lcom/miui/internal/graphics/gif/GifDecoder;->setStartFrame(I)V

    .line 295
    invoke-virtual {v2, p1, p2}, Lcom/miui/internal/graphics/gif/GifDecoder;->setMaxDecodeSize(J)V

    .line 297
    invoke-virtual {v2, p0}, Lcom/miui/internal/graphics/gif/GifDecoder;->read(Ljava/io/InputStream;)I

    move-result v2

    .line 299
    if-nez v2, :cond_0

    const/4 v0, 0x1

    :cond_0
    iput-boolean v0, v1, Lcom/miui/internal/graphics/gif/DecodeGifImageHelper$GifDecodeResult;->mIsDecodeOk:Z

    .line 301
    :try_start_1
    invoke-virtual {p0}, Lmiui/io/ResettableInputStream;->close()V
    :try_end_1
    .catch Ljava/io/IOException; {:try_start_1 .. :try_end_1} :catch_1

    :goto_0
    move-object v0, v1

    .line 305
    :goto_1
    return-object v0

    .line 287
    :catch_0
    move-exception v0

    move-object v0, v1

    .line 288
    goto :goto_1

    .line 302
    :catch_1
    move-exception v0

    goto :goto_0
.end method


# virtual methods
.method public decodeFrom(I)Lcom/miui/internal/graphics/gif/DecodeGifImageHelper$GifDecodeResult;
    .locals 3

    .prologue
    .line 269
    iget-object v0, p0, Lcom/miui/internal/graphics/gif/DecodeGifImageHelper;->mGifSource:Lmiui/io/ResettableInputStream;

    iget-wide v1, p0, Lcom/miui/internal/graphics/gif/DecodeGifImageHelper;->mMaxDecodeSize:J

    invoke-static {v0, v1, v2, p1}, Lcom/miui/internal/graphics/gif/DecodeGifImageHelper;->decode(Lmiui/io/ResettableInputStream;JI)Lcom/miui/internal/graphics/gif/DecodeGifImageHelper$GifDecodeResult;

    move-result-object v0

    return-object v0
.end method

.method public decodeNextFrames()V
    .locals 5

    .prologue
    const/4 v0, 0x1

    const/4 v1, 0x0

    .line 206
    iget-object v2, p0, Lcom/miui/internal/graphics/gif/DecodeGifImageHelper;->mFrames:Ljava/util/List;

    invoke-interface {v2}, Ljava/util/List;->size()I

    move-result v2

    .line 208
    iget v3, p0, Lcom/miui/internal/graphics/gif/DecodeGifImageHelper;->JG:I

    const/4 v4, 0x3

    if-gt v3, v4, :cond_3

    .line 210
    const/4 v3, 0x2

    if-gt v2, v3, :cond_2

    .line 216
    :cond_0
    :goto_0
    if-eqz v0, :cond_1

    .line 217
    invoke-direct {p0}, Lcom/miui/internal/graphics/gif/DecodeGifImageHelper;->dV()I

    move-result v0

    add-int/lit8 v0, v0, 0x1

    invoke-direct {p0, v0}, Lcom/miui/internal/graphics/gif/DecodeGifImageHelper;->ag(I)I

    move-result v0

    .line 218
    iget-object v1, p0, Lcom/miui/internal/graphics/gif/DecodeGifImageHelper;->JH:Lcom/miui/internal/graphics/gif/a;

    invoke-virtual {v1, v0}, Lcom/miui/internal/graphics/gif/a;->ae(I)V

    .line 220
    :cond_1
    return-void

    :cond_2
    move v0, v1

    .line 210
    goto :goto_0

    .line 213
    :cond_3
    iget v3, p0, Lcom/miui/internal/graphics/gif/DecodeGifImageHelper;->JG:I

    div-int/lit8 v3, v3, 0x2

    if-le v2, v3, :cond_0

    move v0, v1

    goto :goto_0
.end method

.method public destroy()V
    .locals 1

    .prologue
    .line 341
    iget-object v0, p0, Lcom/miui/internal/graphics/gif/DecodeGifImageHelper;->JH:Lcom/miui/internal/graphics/gif/a;

    if-eqz v0, :cond_0

    .line 342
    iget-object v0, p0, Lcom/miui/internal/graphics/gif/DecodeGifImageHelper;->JH:Lcom/miui/internal/graphics/gif/a;

    invoke-virtual {v0}, Lcom/miui/internal/graphics/gif/a;->destroy()V

    .line 344
    :cond_0
    return-void
.end method

.method public firstDecodeNextFrames()V
    .locals 4

    .prologue
    .line 317
    new-instance v0, Lcom/miui/internal/graphics/gif/DecodeGifImageHelper$1;

    invoke-static {}, Landroid/os/Looper;->getMainLooper()Landroid/os/Looper;

    move-result-object v1

    invoke-direct {v0, p0, v1}, Lcom/miui/internal/graphics/gif/DecodeGifImageHelper$1;-><init>(Lcom/miui/internal/graphics/gif/DecodeGifImageHelper;Landroid/os/Looper;)V

    iput-object v0, p0, Lcom/miui/internal/graphics/gif/DecodeGifImageHelper;->mDecodeFrameHandler:Landroid/os/Handler;

    .line 331
    iget-object v0, p0, Lcom/miui/internal/graphics/gif/DecodeGifImageHelper;->mGifSource:Lmiui/io/ResettableInputStream;

    iget-wide v1, p0, Lcom/miui/internal/graphics/gif/DecodeGifImageHelper;->mMaxDecodeSize:J

    iget-object v3, p0, Lcom/miui/internal/graphics/gif/DecodeGifImageHelper;->mDecodeFrameHandler:Landroid/os/Handler;

    invoke-static {v0, v1, v2, v3}, Lcom/miui/internal/graphics/gif/a;->a(Lmiui/io/ResettableInputStream;JLandroid/os/Handler;)Lcom/miui/internal/graphics/gif/a;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/internal/graphics/gif/DecodeGifImageHelper;->JH:Lcom/miui/internal/graphics/gif/a;

    .line 333
    iget-object v0, p0, Lcom/miui/internal/graphics/gif/DecodeGifImageHelper;->mFrames:Ljava/util/List;

    invoke-interface {v0}, Ljava/util/List;->size()I

    move-result v0

    iput v0, p0, Lcom/miui/internal/graphics/gif/DecodeGifImageHelper;->JG:I

    .line 337
    invoke-virtual {p0}, Lcom/miui/internal/graphics/gif/DecodeGifImageHelper;->decodeNextFrames()V

    .line 338
    return-void
.end method

.method public handleDecodeFramesResult(Lcom/miui/internal/graphics/gif/DecodeGifImageHelper$GifDecodeResult;)Z
    .locals 10

    .prologue
    const/4 v0, 0x1

    const/4 v1, 0x0

    .line 231
    iget-boolean v2, p1, Lcom/miui/internal/graphics/gif/DecodeGifImageHelper$GifDecodeResult;->mIsDecodeOk:Z

    if-eqz v2, :cond_0

    iget-object v2, p1, Lcom/miui/internal/graphics/gif/DecodeGifImageHelper$GifDecodeResult;->mGifDecoder:Lcom/miui/internal/graphics/gif/GifDecoder;

    if-nez v2, :cond_2

    :cond_0
    move v0, v1

    .line 260
    :cond_1
    return v0

    .line 235
    :cond_2
    iget-object v2, p1, Lcom/miui/internal/graphics/gif/DecodeGifImageHelper$GifDecodeResult;->mGifDecoder:Lcom/miui/internal/graphics/gif/GifDecoder;

    .line 237
    const-string v3, "Thread#%d: decoded %d frames [%s] [%d]"

    const/4 v4, 0x4

    new-array v4, v4, [Ljava/lang/Object;

    invoke-static {}, Ljava/lang/Thread;->currentThread()Ljava/lang/Thread;

    move-result-object v5

    invoke-virtual {v5}, Ljava/lang/Thread;->getId()J

    move-result-wide v5

    invoke-static {v5, v6}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v5

    aput-object v5, v4, v1

    iget-object v5, p1, Lcom/miui/internal/graphics/gif/DecodeGifImageHelper$GifDecodeResult;->mGifDecoder:Lcom/miui/internal/graphics/gif/GifDecoder;

    invoke-virtual {v5}, Lcom/miui/internal/graphics/gif/GifDecoder;->getFrameCount()I

    move-result v5

    invoke-static {v5}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v5

    aput-object v5, v4, v0

    const/4 v5, 0x2

    iget-boolean v6, p1, Lcom/miui/internal/graphics/gif/DecodeGifImageHelper$GifDecodeResult;->mIsDecodeOk:Z

    invoke-static {v6}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v6

    aput-object v6, v4, v5

    const/4 v5, 0x3

    iget v6, p0, Lcom/miui/internal/graphics/gif/DecodeGifImageHelper;->mRealFrameCount:I

    invoke-static {v6}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v6

    aput-object v6, v4, v5

    invoke-static {v3, v4}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v3

    .line 240
    const-string v4, "dumpFrameIndex"

    invoke-static {v4, v3}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 243
    invoke-virtual {v2}, Lcom/miui/internal/graphics/gif/GifDecoder;->isDecodeToTheEnd()Z

    move-result v3

    if-eqz v3, :cond_3

    .line 244
    invoke-virtual {v2}, Lcom/miui/internal/graphics/gif/GifDecoder;->getRealFrameCount()I

    move-result v3

    iput v3, p0, Lcom/miui/internal/graphics/gif/DecodeGifImageHelper;->mRealFrameCount:I

    .line 248
    :cond_3
    invoke-virtual {v2}, Lcom/miui/internal/graphics/gif/GifDecoder;->getFrameCount()I

    move-result v3

    .line 250
    if-lez v3, :cond_1

    .line 251
    invoke-direct {p0}, Lcom/miui/internal/graphics/gif/DecodeGifImageHelper;->dV()I

    move-result v4

    .line 252
    :goto_0
    if-ge v1, v3, :cond_1

    .line 253
    invoke-virtual {v2, v1}, Lcom/miui/internal/graphics/gif/GifDecoder;->getFrame(I)Landroid/graphics/Bitmap;

    move-result-object v5

    .line 254
    invoke-virtual {v2, v1}, Lcom/miui/internal/graphics/gif/GifDecoder;->getDelay(I)I

    move-result v6

    .line 255
    add-int/lit8 v7, v4, 0x1

    add-int/2addr v7, v1

    invoke-direct {p0, v7}, Lcom/miui/internal/graphics/gif/DecodeGifImageHelper;->ag(I)I

    move-result v7

    .line 256
    iget-object v8, p0, Lcom/miui/internal/graphics/gif/DecodeGifImageHelper;->mFrames:Ljava/util/List;

    new-instance v9, Lcom/miui/internal/graphics/gif/DecodeGifImageHelper$GifFrame;

    invoke-direct {v9, v5, v6, v7}, Lcom/miui/internal/graphics/gif/DecodeGifImageHelper$GifFrame;-><init>(Landroid/graphics/Bitmap;II)V

    invoke-interface {v8, v9}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 252
    add-int/lit8 v1, v1, 0x1

    goto :goto_0
.end method
