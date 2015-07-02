.class public Lcom/miui/internal/graphics/gif/GifDecoder;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/internal/graphics/gif/GifDecoder$a;
    }
.end annotation


# static fields
.field public static final MAX_DECODE_SIZE:I = 0x100000

.field protected static final MAX_STACK_SIZE:I = 0x1000

.field public static final STATUS_DECODE_CANCEL:I = 0x3

.field public static final STATUS_FORMAT_ERROR:I = 0x1

.field public static final STATUS_OK:I = 0x0

.field public static final STATUS_OPEN_ERROR:I = 0x2


# instance fields
.field private KC:J

.field private KD:Z

.field private KF:I

.field private KJ:I

.field private KK:[I

.field private KL:Z

.field protected act:[I

.field protected bgColor:I

.field protected bgIndex:I

.field protected block:[B

.field protected blockSize:I

.field protected delay:I

.field protected dispose:I

.field protected frames:Ljava/util/Vector;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Vector",
            "<",
            "Lcom/miui/internal/graphics/gif/GifDecoder$a;",
            ">;"
        }
    .end annotation
.end field

.field protected gct:[I

.field protected gctFlag:Z

.field protected gctSize:I

.field private height:I

.field protected ih:I

.field protected image:Landroid/graphics/Bitmap;

.field protected in:Ljava/io/BufferedInputStream;

.field protected interlace:Z

.field protected iw:I

.field protected ix:I

.field protected iy:I

.field protected lastBgColor:I

.field protected lastBitmap:Landroid/graphics/Bitmap;

.field protected lastDispose:I

.field protected lct:[I

.field protected lctFlag:Z

.field protected lctSize:I

.field protected loopCount:I

.field protected lrh:I

.field protected lrw:I

.field protected lrx:I

.field protected lry:I

.field private mCancel:Z

.field private mMaxDecodeSize:J

.field protected pixelAspect:I

.field protected pixelStack:[B

.field protected pixels:[B

.field protected prefix:[S

.field protected status:I

.field protected suffix:[B

.field protected transIndex:I

.field protected transparency:Z

.field private width:I


# direct methods
.method public constructor <init>()V
    .locals 3

    .prologue
    const/4 v2, 0x0

    .line 42
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 70
    const-wide/32 v0, 0x100000

    iput-wide v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->mMaxDecodeSize:J

    .line 96
    const/4 v0, 0x1

    iput v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->loopCount:I

    .line 126
    const/16 v0, 0x100

    new-array v0, v0, [B

    iput-object v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->block:[B

    .line 128
    iput v2, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->blockSize:I

    .line 131
    iput v2, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->dispose:I

    .line 134
    iput v2, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->lastDispose:I

    .line 136
    iput-boolean v2, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->transparency:Z

    .line 138
    iput v2, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->delay:I

    .line 363
    iput-boolean v2, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->mCancel:Z

    .line 409
    iput-boolean v2, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->KL:Z

    return-void
.end method

.method private eb()V
    .locals 0

    .prologue
    .line 374
    return-void
.end method

.method public static isGifStream(Ljava/io/InputStream;)Z
    .locals 4

    .prologue
    const/4 v0, 0x0

    .line 394
    .line 395
    if-eqz p0, :cond_1

    .line 396
    const-string v1, ""

    .line 397
    :goto_0
    const/4 v2, 0x6

    if-ge v0, v2, :cond_0

    .line 398
    invoke-static {p0}, Lcom/miui/internal/graphics/gif/GifDecoder;->readOneByte(Ljava/io/InputStream;)I

    move-result v2

    .line 399
    const/4 v3, -0x1

    if-ne v2, v3, :cond_2

    .line 404
    :cond_0
    const-string v0, "GIF"

    invoke-virtual {v1, v0}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v0

    .line 406
    :cond_1
    return v0

    .line 402
    :cond_2
    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v3, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    int-to-char v2, v2

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    .line 397
    add-int/lit8 v0, v0, 0x1

    goto :goto_0
.end method

.method protected static readOneByte(Ljava/io/InputStream;)I
    .locals 1

    .prologue
    .line 381
    :try_start_0
    invoke-virtual {p0}, Ljava/io/InputStream;->read()I
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    move-result v0

    .line 383
    :goto_0
    return v0

    .line 382
    :catch_0
    move-exception v0

    .line 383
    const/4 v0, -0x1

    goto :goto_0
.end method


# virtual methods
.method protected decodeBitmapData()V
    .locals 23

    .prologue
    .line 454
    const/4 v15, -0x1

    .line 455
    move-object/from16 v0, p0

    iget v1, v0, Lcom/miui/internal/graphics/gif/GifDecoder;->iw:I

    move-object/from16 v0, p0

    iget v2, v0, Lcom/miui/internal/graphics/gif/GifDecoder;->ih:I

    mul-int v16, v1, v2

    .line 457
    move-object/from16 v0, p0

    iget-object v1, v0, Lcom/miui/internal/graphics/gif/GifDecoder;->pixels:[B

    if-eqz v1, :cond_0

    move-object/from16 v0, p0

    iget-object v1, v0, Lcom/miui/internal/graphics/gif/GifDecoder;->pixels:[B

    array-length v1, v1

    move/from16 v0, v16

    if-ge v1, v0, :cond_1

    .line 458
    :cond_0
    move/from16 v0, v16

    new-array v1, v0, [B

    move-object/from16 v0, p0

    iput-object v1, v0, Lcom/miui/internal/graphics/gif/GifDecoder;->pixels:[B

    .line 460
    :cond_1
    move-object/from16 v0, p0

    iget-object v1, v0, Lcom/miui/internal/graphics/gif/GifDecoder;->prefix:[S

    if-nez v1, :cond_2

    .line 461
    const/16 v1, 0x1000

    new-array v1, v1, [S

    move-object/from16 v0, p0

    iput-object v1, v0, Lcom/miui/internal/graphics/gif/GifDecoder;->prefix:[S

    .line 463
    :cond_2
    move-object/from16 v0, p0

    iget-object v1, v0, Lcom/miui/internal/graphics/gif/GifDecoder;->suffix:[B

    if-nez v1, :cond_3

    .line 464
    const/16 v1, 0x1000

    new-array v1, v1, [B

    move-object/from16 v0, p0

    iput-object v1, v0, Lcom/miui/internal/graphics/gif/GifDecoder;->suffix:[B

    .line 466
    :cond_3
    move-object/from16 v0, p0

    iget-object v1, v0, Lcom/miui/internal/graphics/gif/GifDecoder;->pixelStack:[B

    if-nez v1, :cond_4

    .line 467
    const/16 v1, 0x1001

    new-array v1, v1, [B

    move-object/from16 v0, p0

    iput-object v1, v0, Lcom/miui/internal/graphics/gif/GifDecoder;->pixelStack:[B

    .line 470
    :cond_4
    invoke-virtual/range {p0 .. p0}, Lcom/miui/internal/graphics/gif/GifDecoder;->read()I

    move-result v17

    .line 471
    const/4 v1, 0x1

    shl-int v18, v1, v17

    .line 472
    add-int/lit8 v19, v18, 0x1

    .line 473
    add-int/lit8 v11, v18, 0x2

    .line 475
    add-int/lit8 v3, v17, 0x1

    .line 476
    const/4 v1, 0x1

    shl-int/2addr v1, v3

    add-int/lit8 v4, v1, -0x1

    .line 477
    const/4 v1, 0x0

    :goto_0
    move/from16 v0, v18

    if-ge v1, v0, :cond_5

    .line 478
    move-object/from16 v0, p0

    iget-object v2, v0, Lcom/miui/internal/graphics/gif/GifDecoder;->prefix:[S

    const/4 v5, 0x0

    aput-short v5, v2, v1

    .line 479
    move-object/from16 v0, p0

    iget-object v2, v0, Lcom/miui/internal/graphics/gif/GifDecoder;->suffix:[B

    int-to-byte v5, v1

    aput-byte v5, v2, v1

    .line 477
    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    .line 482
    :cond_5
    const/4 v12, 0x0

    .line 483
    const/4 v1, 0x0

    move v5, v12

    move v6, v12

    move v7, v12

    move v14, v1

    move v2, v12

    move v8, v12

    move v10, v15

    move v1, v12

    :goto_1
    move/from16 v0, v16

    if-ge v14, v0, :cond_6

    .line 484
    if-nez v5, :cond_10

    .line 485
    if-ge v8, v3, :cond_9

    .line 487
    if-nez v2, :cond_8

    .line 489
    invoke-virtual/range {p0 .. p0}, Lcom/miui/internal/graphics/gif/GifDecoder;->readBlock()I

    move-result v2

    .line 490
    if-gtz v2, :cond_7

    :cond_6
    move v1, v12

    .line 552
    :goto_2
    move/from16 v0, v16

    if-ge v1, v0, :cond_e

    .line 553
    move-object/from16 v0, p0

    iget-object v2, v0, Lcom/miui/internal/graphics/gif/GifDecoder;->pixels:[B

    const/4 v3, 0x0

    aput-byte v3, v2, v1

    .line 552
    add-int/lit8 v1, v1, 0x1

    goto :goto_2

    .line 493
    :cond_7
    const/4 v1, 0x0

    .line 495
    :cond_8
    move-object/from16 v0, p0

    iget-object v9, v0, Lcom/miui/internal/graphics/gif/GifDecoder;->block:[B

    aget-byte v9, v9, v1

    and-int/lit16 v9, v9, 0xff

    shl-int/2addr v9, v8

    add-int/2addr v7, v9

    .line 496
    add-int/lit8 v8, v8, 0x8

    .line 497
    add-int/lit8 v1, v1, 0x1

    .line 498
    add-int/lit8 v2, v2, -0x1

    .line 499
    goto :goto_1

    .line 502
    :cond_9
    and-int v9, v7, v4

    .line 503
    shr-int/2addr v7, v3

    .line 504
    sub-int/2addr v8, v3

    .line 506
    if-gt v9, v11, :cond_6

    move/from16 v0, v19

    if-eq v9, v0, :cond_6

    .line 509
    move/from16 v0, v18

    if-ne v9, v0, :cond_a

    .line 511
    add-int/lit8 v3, v17, 0x1

    .line 512
    const/4 v4, 0x1

    shl-int/2addr v4, v3

    add-int/lit8 v4, v4, -0x1

    .line 513
    add-int/lit8 v11, v18, 0x2

    move v10, v15

    .line 515
    goto :goto_1

    .line 517
    :cond_a
    if-ne v10, v15, :cond_b

    .line 518
    move-object/from16 v0, p0

    iget-object v10, v0, Lcom/miui/internal/graphics/gif/GifDecoder;->pixelStack:[B

    add-int/lit8 v6, v5, 0x1

    move-object/from16 v0, p0

    iget-object v13, v0, Lcom/miui/internal/graphics/gif/GifDecoder;->suffix:[B

    aget-byte v13, v13, v9

    aput-byte v13, v10, v5

    move v5, v6

    move v10, v9

    move v6, v9

    .line 521
    goto :goto_1

    .line 524
    :cond_b
    if-ne v9, v11, :cond_f

    .line 525
    move-object/from16 v0, p0

    iget-object v0, v0, Lcom/miui/internal/graphics/gif/GifDecoder;->pixelStack:[B

    move-object/from16 v20, v0

    add-int/lit8 v13, v5, 0x1

    int-to-byte v6, v6

    aput-byte v6, v20, v5

    move v6, v10

    .line 528
    :goto_3
    move/from16 v0, v18

    if-le v6, v0, :cond_c

    .line 529
    move-object/from16 v0, p0

    iget-object v0, v0, Lcom/miui/internal/graphics/gif/GifDecoder;->pixelStack:[B

    move-object/from16 v20, v0

    add-int/lit8 v5, v13, 0x1

    move-object/from16 v0, p0

    iget-object v0, v0, Lcom/miui/internal/graphics/gif/GifDecoder;->suffix:[B

    move-object/from16 v21, v0

    aget-byte v21, v21, v6

    aput-byte v21, v20, v13

    .line 530
    move-object/from16 v0, p0

    iget-object v13, v0, Lcom/miui/internal/graphics/gif/GifDecoder;->prefix:[S

    aget-short v6, v13, v6

    move v13, v5

    goto :goto_3

    .line 532
    :cond_c
    move-object/from16 v0, p0

    iget-object v5, v0, Lcom/miui/internal/graphics/gif/GifDecoder;->suffix:[B

    aget-byte v5, v5, v6

    and-int/lit16 v6, v5, 0xff

    .line 534
    const/16 v5, 0x1000

    if-ge v11, v5, :cond_6

    .line 537
    move-object/from16 v0, p0

    iget-object v0, v0, Lcom/miui/internal/graphics/gif/GifDecoder;->pixelStack:[B

    move-object/from16 v20, v0

    add-int/lit8 v5, v13, 0x1

    int-to-byte v0, v6

    move/from16 v21, v0

    aput-byte v21, v20, v13

    .line 538
    move-object/from16 v0, p0

    iget-object v13, v0, Lcom/miui/internal/graphics/gif/GifDecoder;->prefix:[S

    int-to-short v10, v10

    aput-short v10, v13, v11

    .line 539
    move-object/from16 v0, p0

    iget-object v10, v0, Lcom/miui/internal/graphics/gif/GifDecoder;->suffix:[B

    int-to-byte v13, v6

    aput-byte v13, v10, v11

    .line 540
    add-int/lit8 v10, v11, 0x1

    .line 541
    and-int v11, v10, v4

    if-nez v11, :cond_d

    const/16 v11, 0x1000

    if-ge v10, v11, :cond_d

    .line 542
    add-int/lit8 v3, v3, 0x1

    .line 543
    add-int/2addr v4, v10

    :cond_d
    move/from16 v22, v5

    move v5, v7

    move v7, v9

    move v9, v4

    move v4, v6

    move v6, v8

    move v8, v3

    move/from16 v3, v22

    .line 548
    :goto_4
    add-int/lit8 v11, v3, -0x1

    .line 549
    move-object/from16 v0, p0

    iget-object v13, v0, Lcom/miui/internal/graphics/gif/GifDecoder;->pixels:[B

    add-int/lit8 v3, v12, 0x1

    move-object/from16 v0, p0

    iget-object v0, v0, Lcom/miui/internal/graphics/gif/GifDecoder;->pixelStack:[B

    move-object/from16 v20, v0

    aget-byte v20, v20, v11

    aput-byte v20, v13, v12

    .line 550
    add-int/lit8 v12, v14, 0x1

    move v14, v12

    move v12, v3

    move v3, v8

    move v8, v6

    move v6, v4

    move v4, v9

    move/from16 v22, v7

    move v7, v5

    move v5, v11

    move v11, v10

    move/from16 v10, v22

    goto/16 :goto_1

    .line 555
    :cond_e
    return-void

    :cond_f
    move v13, v5

    move v6, v9

    goto/16 :goto_3

    :cond_10
    move v9, v4

    move v4, v6

    move v6, v8

    move v8, v3

    move v3, v5

    move v5, v7

    move v7, v10

    move v10, v11

    goto :goto_4
.end method

.method protected err()Z
    .locals 1

    .prologue
    .line 561
    iget v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->status:I

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public getBitmap()Landroid/graphics/Bitmap;
    .locals 1

    .prologue
    .line 233
    const/4 v0, 0x0

    invoke-virtual {p0, v0}, Lcom/miui/internal/graphics/gif/GifDecoder;->getFrame(I)Landroid/graphics/Bitmap;

    move-result-object v0

    return-object v0
.end method

.method public getDelay(I)I
    .locals 1

    .prologue
    .line 210
    const/4 v0, -0x1

    iput v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->delay:I

    .line 211
    invoke-virtual {p0}, Lcom/miui/internal/graphics/gif/GifDecoder;->getFrameCount()I

    move-result v0

    .line 212
    if-ltz p1, :cond_0

    if-ge p1, v0, :cond_0

    .line 213
    iget-object v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->frames:Ljava/util/Vector;

    invoke-virtual {v0, p1}, Ljava/util/Vector;->elementAt(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/graphics/gif/GifDecoder$a;

    iget v0, v0, Lcom/miui/internal/graphics/gif/GifDecoder$a;->delay:I

    iput v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->delay:I

    .line 215
    :cond_0
    iget v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->delay:I

    return v0
.end method

.method public getFrame(I)Landroid/graphics/Bitmap;
    .locals 2

    .prologue
    .line 356
    invoke-virtual {p0}, Lcom/miui/internal/graphics/gif/GifDecoder;->getFrameCount()I

    move-result v0

    .line 357
    if-gtz v0, :cond_0

    .line 358
    const/4 v0, 0x0

    .line 360
    :goto_0
    return-object v0

    .line 359
    :cond_0
    rem-int v0, p1, v0

    .line 360
    iget-object v1, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->frames:Ljava/util/Vector;

    invoke-virtual {v1, v0}, Ljava/util/Vector;->elementAt(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/graphics/gif/GifDecoder$a;

    iget-object v0, v0, Lcom/miui/internal/graphics/gif/GifDecoder$a;->image:Landroid/graphics/Bitmap;

    goto :goto_0
.end method

.method public getFrameCount()I
    .locals 1

    .prologue
    .line 224
    iget-object v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->frames:Ljava/util/Vector;

    if-nez v0, :cond_0

    const/4 v0, 0x0

    :goto_0
    return v0

    :cond_0
    iget-object v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->frames:Ljava/util/Vector;

    invoke-virtual {v0}, Ljava/util/Vector;->size()I

    move-result v0

    goto :goto_0
.end method

.method public getHeight()I
    .locals 1

    .prologue
    .line 898
    iget v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->height:I

    return v0
.end method

.method public getLoopCount()I
    .locals 1

    .prologue
    .line 242
    iget v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->loopCount:I

    return v0
.end method

.method public getRealFrameCount()I
    .locals 1

    .prologue
    .line 652
    iget-boolean v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->KD:Z

    if-eqz v0, :cond_0

    .line 653
    iget v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->KF:I

    .line 655
    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public getWidth()I
    .locals 1

    .prologue
    .line 894
    iget v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->width:I

    return v0
.end method

.method protected init()V
    .locals 2

    .prologue
    const/4 v1, 0x0

    .line 568
    const/4 v0, 0x0

    iput v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->status:I

    .line 569
    new-instance v0, Ljava/util/Vector;

    invoke-direct {v0}, Ljava/util/Vector;-><init>()V

    iput-object v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->frames:Ljava/util/Vector;

    .line 570
    iput-object v1, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->gct:[I

    .line 571
    iput-object v1, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->lct:[I

    .line 572
    return-void
.end method

.method public isDecodeToTheEnd()Z
    .locals 1

    .prologue
    .line 156
    iget-boolean v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->KD:Z

    return v0
.end method

.method protected read()I
    .locals 2

    .prologue
    .line 578
    const/4 v0, 0x0

    .line 580
    :try_start_0
    iget-object v1, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->in:Ljava/io/BufferedInputStream;

    invoke-virtual {v1}, Ljava/io/BufferedInputStream;->read()I
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    move-result v0

    .line 584
    :goto_0
    return v0

    .line 581
    :catch_0
    move-exception v1

    .line 582
    const/4 v1, 0x1

    iput v1, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->status:I

    goto :goto_0
.end method

.method public read(Ljava/io/InputStream;)I
    .locals 3

    .prologue
    const/4 v2, 0x2

    const/4 v1, 0x1

    .line 418
    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->KD:Z

    .line 419
    iget-boolean v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->KL:Z

    if-eqz v0, :cond_0

    .line 420
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "decoder cannot be called more than once"

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 422
    :cond_0
    iput-boolean v1, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->KL:Z

    .line 423
    invoke-virtual {p0}, Lcom/miui/internal/graphics/gif/GifDecoder;->init()V

    .line 424
    if-eqz p1, :cond_3

    .line 425
    new-instance v0, Ljava/io/BufferedInputStream;

    invoke-direct {v0, p1}, Ljava/io/BufferedInputStream;-><init>(Ljava/io/InputStream;)V

    iput-object v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->in:Ljava/io/BufferedInputStream;

    .line 427
    :try_start_0
    invoke-virtual {p0}, Lcom/miui/internal/graphics/gif/GifDecoder;->readHeader()V

    .line 428
    iget-boolean v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->mCancel:Z

    if-nez v0, :cond_1

    invoke-virtual {p0}, Lcom/miui/internal/graphics/gif/GifDecoder;->err()Z

    move-result v0

    if-nez v0, :cond_1

    .line 429
    invoke-virtual {p0}, Lcom/miui/internal/graphics/gif/GifDecoder;->readContents()V

    .line 430
    invoke-virtual {p0}, Lcom/miui/internal/graphics/gif/GifDecoder;->getFrameCount()I

    move-result v0

    .line 431
    if-gez v0, :cond_1

    .line 432
    const/4 v0, 0x1

    iput v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->status:I
    :try_end_0
    .catch Ljava/lang/OutOfMemoryError; {:try_start_0 .. :try_end_0} :catch_0

    .line 443
    :cond_1
    :goto_0
    iget-boolean v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->mCancel:Z

    if-eqz v0, :cond_2

    .line 444
    invoke-virtual {p0}, Lcom/miui/internal/graphics/gif/GifDecoder;->recycle()V

    .line 445
    const/4 v0, 0x3

    iput v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->status:I

    .line 447
    :cond_2
    iget v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->status:I

    return v0

    .line 435
    :catch_0
    move-exception v0

    .line 436
    iput v2, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->status:I

    .line 437
    invoke-virtual {p0}, Lcom/miui/internal/graphics/gif/GifDecoder;->recycle()V

    goto :goto_0

    .line 440
    :cond_3
    iput v2, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->status:I

    goto :goto_0
.end method

.method protected readBitmap()V
    .locals 5

    .prologue
    const/4 v1, 0x1

    const/4 v2, 0x0

    .line 772
    invoke-virtual {p0}, Lcom/miui/internal/graphics/gif/GifDecoder;->readShort()I

    move-result v0

    iput v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->ix:I

    .line 773
    invoke-virtual {p0}, Lcom/miui/internal/graphics/gif/GifDecoder;->readShort()I

    move-result v0

    iput v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->iy:I

    .line 774
    invoke-virtual {p0}, Lcom/miui/internal/graphics/gif/GifDecoder;->readShort()I

    move-result v0

    iput v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->iw:I

    .line 775
    invoke-virtual {p0}, Lcom/miui/internal/graphics/gif/GifDecoder;->readShort()I

    move-result v0

    iput v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->ih:I

    .line 776
    invoke-virtual {p0}, Lcom/miui/internal/graphics/gif/GifDecoder;->read()I

    move-result v3

    .line 777
    and-int/lit16 v0, v3, 0x80

    if-eqz v0, :cond_4

    move v0, v1

    :goto_0
    iput-boolean v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->lctFlag:Z

    .line 778
    const/4 v0, 0x2

    and-int/lit8 v4, v3, 0x7

    shl-int/2addr v0, v4

    iput v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->lctSize:I

    .line 782
    and-int/lit8 v0, v3, 0x40

    if-eqz v0, :cond_5

    move v0, v1

    :goto_1
    iput-boolean v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->interlace:Z

    .line 783
    iget-boolean v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->lctFlag:Z

    if-eqz v0, :cond_6

    .line 784
    iget v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->lctSize:I

    invoke-virtual {p0, v0}, Lcom/miui/internal/graphics/gif/GifDecoder;->readColorTable(I)[I

    move-result-object v0

    iput-object v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->lct:[I

    .line 785
    iget-object v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->lct:[I

    iput-object v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->act:[I

    .line 793
    :cond_0
    :goto_2
    iget-boolean v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->transparency:Z

    if-eqz v0, :cond_1

    .line 794
    iget-object v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->act:[I

    iget v3, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->transIndex:I

    aget v0, v0, v3

    .line 795
    iget-object v3, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->act:[I

    iget v4, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->transIndex:I

    aput v2, v3, v4

    move v2, v0

    .line 797
    :cond_1
    iget-object v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->act:[I

    if-nez v0, :cond_2

    .line 798
    iput v1, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->status:I

    .line 800
    :cond_2
    invoke-virtual {p0}, Lcom/miui/internal/graphics/gif/GifDecoder;->err()Z

    move-result v0

    if-eqz v0, :cond_7

    .line 825
    :cond_3
    :goto_3
    return-void

    :cond_4
    move v0, v2

    .line 777
    goto :goto_0

    :cond_5
    move v0, v2

    .line 782
    goto :goto_1

    .line 787
    :cond_6
    iget-object v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->gct:[I

    iput-object v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->act:[I

    .line 788
    iget v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->bgIndex:I

    iget v3, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->transIndex:I

    if-ne v0, v3, :cond_0

    .line 789
    iput v2, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->bgColor:I

    goto :goto_2

    .line 803
    :cond_7
    invoke-virtual {p0}, Lcom/miui/internal/graphics/gif/GifDecoder;->decodeBitmapData()V

    .line 804
    invoke-virtual {p0}, Lcom/miui/internal/graphics/gif/GifDecoder;->skip()V

    .line 805
    invoke-virtual {p0}, Lcom/miui/internal/graphics/gif/GifDecoder;->err()Z

    move-result v0

    if-nez v0, :cond_3

    .line 809
    iget-boolean v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->mCancel:Z

    if-nez v0, :cond_3

    .line 812
    invoke-virtual {p0}, Lcom/miui/internal/graphics/gif/GifDecoder;->setPixels()V

    .line 815
    iget v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->KF:I

    iget v1, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->KJ:I

    if-lt v0, v1, :cond_8

    .line 816
    iget-object v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->frames:Ljava/util/Vector;

    new-instance v1, Lcom/miui/internal/graphics/gif/GifDecoder$a;

    iget-object v3, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->image:Landroid/graphics/Bitmap;

    iget v4, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->delay:I

    invoke-direct {v1, v3, v4}, Lcom/miui/internal/graphics/gif/GifDecoder$a;-><init>(Landroid/graphics/Bitmap;I)V

    invoke-virtual {v0, v1}, Ljava/util/Vector;->addElement(Ljava/lang/Object;)V

    .line 818
    :cond_8
    iget v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->KF:I

    add-int/lit8 v0, v0, 0x1

    iput v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->KF:I

    .line 821
    iget-boolean v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->transparency:Z

    if-eqz v0, :cond_9

    .line 822
    iget-object v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->act:[I

    iget v1, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->transIndex:I

    aput v2, v0, v1

    .line 824
    :cond_9
    invoke-virtual {p0}, Lcom/miui/internal/graphics/gif/GifDecoder;->resetFrame()V

    goto :goto_3
.end method

.method protected readBlock()I
    .locals 4

    .prologue
    .line 593
    invoke-virtual {p0}, Lcom/miui/internal/graphics/gif/GifDecoder;->read()I

    move-result v0

    iput v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->blockSize:I

    .line 594
    const/4 v0, 0x0

    .line 595
    iget v1, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->blockSize:I

    if-lez v1, :cond_1

    .line 598
    :goto_0
    :try_start_0
    iget v1, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->blockSize:I

    if-ge v0, v1, :cond_0

    .line 599
    iget-object v1, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->in:Ljava/io/BufferedInputStream;

    iget-object v2, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->block:[B

    iget v3, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->blockSize:I

    sub-int/2addr v3, v0

    invoke-virtual {v1, v2, v0, v3}, Ljava/io/BufferedInputStream;->read([BII)I
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    move-result v1

    .line 600
    const/4 v2, -0x1

    if-ne v1, v2, :cond_2

    .line 608
    :cond_0
    :goto_1
    iget v1, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->blockSize:I

    if-ge v0, v1, :cond_1

    .line 609
    const/4 v1, 0x1

    iput v1, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->status:I

    .line 612
    :cond_1
    return v0

    .line 603
    :cond_2
    add-int/2addr v0, v1

    goto :goto_0

    .line 605
    :catch_0
    move-exception v1

    .line 606
    invoke-virtual {v1}, Ljava/lang/Exception;->printStackTrace()V

    goto :goto_1
.end method

.method protected readColorTable(I)[I
    .locals 9

    .prologue
    const/4 v2, 0x0

    .line 622
    mul-int/lit8 v3, p1, 0x3

    .line 623
    const/4 v0, 0x0

    .line 624
    new-array v4, v3, [B

    .line 627
    :try_start_0
    iget-object v1, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->in:Ljava/io/BufferedInputStream;

    const/4 v5, 0x0

    array-length v6, v4

    invoke-virtual {v1, v4, v5, v6}, Ljava/io/BufferedInputStream;->read([BII)I
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    move-result v1

    .line 631
    :goto_0
    if-ge v1, v3, :cond_1

    .line 632
    const/4 v1, 0x1

    iput v1, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->status:I

    .line 644
    :cond_0
    return-object v0

    .line 628
    :catch_0
    move-exception v1

    .line 629
    invoke-virtual {v1}, Ljava/lang/Exception;->printStackTrace()V

    move v1, v2

    goto :goto_0

    .line 634
    :cond_1
    const/16 v0, 0x100

    new-array v0, v0, [I

    move v1, v2

    .line 637
    :goto_1
    if-ge v2, p1, :cond_0

    .line 638
    add-int/lit8 v3, v1, 0x1

    aget-byte v1, v4, v1

    and-int/lit16 v5, v1, 0xff

    .line 639
    add-int/lit8 v6, v3, 0x1

    aget-byte v1, v4, v3

    and-int/lit16 v7, v1, 0xff

    .line 640
    add-int/lit8 v1, v6, 0x1

    aget-byte v3, v4, v6

    and-int/lit16 v6, v3, 0xff

    .line 641
    add-int/lit8 v3, v2, 0x1

    const/high16 v8, -0x1000000

    shl-int/lit8 v5, v5, 0x10

    or-int/2addr v5, v8

    shl-int/lit8 v7, v7, 0x8

    or-int/2addr v5, v7

    or-int/2addr v5, v6

    aput v5, v0, v2

    move v2, v3

    .line 642
    goto :goto_1
.end method

.method protected readContents()V
    .locals 8

    .prologue
    const/4 v3, 0x0

    const/4 v1, 0x1

    .line 663
    iput v3, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->KF:I

    move v0, v3

    .line 665
    :cond_0
    :goto_0
    if-nez v0, :cond_1

    invoke-virtual {p0}, Lcom/miui/internal/graphics/gif/GifDecoder;->err()Z

    move-result v2

    if-nez v2, :cond_1

    .line 666
    iget-boolean v2, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->mCancel:Z

    if-eqz v2, :cond_2

    .line 723
    :cond_1
    return-void

    .line 669
    :cond_2
    invoke-virtual {p0}, Lcom/miui/internal/graphics/gif/GifDecoder;->read()I

    move-result v2

    .line 670
    sparse-switch v2, :sswitch_data_0

    .line 720
    iput v1, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->status:I

    goto :goto_0

    .line 673
    :sswitch_0
    iget-object v2, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->frames:Ljava/util/Vector;

    invoke-virtual {v2}, Ljava/util/Vector;->size()I

    move-result v2

    .line 675
    invoke-virtual {p0}, Lcom/miui/internal/graphics/gif/GifDecoder;->readBitmap()V

    .line 677
    iget-object v4, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->frames:Ljava/util/Vector;

    invoke-virtual {v4}, Ljava/util/Vector;->size()I

    move-result v4

    if-le v4, v2, :cond_3

    .line 678
    iget-wide v4, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->KC:J

    iget-object v2, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->image:Landroid/graphics/Bitmap;

    invoke-virtual {v2}, Landroid/graphics/Bitmap;->getRowBytes()I

    move-result v2

    iget-object v6, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->image:Landroid/graphics/Bitmap;

    invoke-virtual {v6}, Landroid/graphics/Bitmap;->getHeight()I

    move-result v6

    mul-int/2addr v2, v6

    int-to-long v6, v2

    add-long/2addr v4, v6

    iput-wide v4, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->KC:J

    .line 680
    :cond_3
    iget-wide v4, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->KC:J

    iget-wide v6, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->mMaxDecodeSize:J

    cmp-long v2, v4, v6

    if-lez v2, :cond_0

    move v0, v1

    .line 681
    goto :goto_0

    .line 685
    :sswitch_1
    invoke-virtual {p0}, Lcom/miui/internal/graphics/gif/GifDecoder;->read()I

    move-result v2

    .line 686
    sparse-switch v2, :sswitch_data_1

    .line 709
    invoke-virtual {p0}, Lcom/miui/internal/graphics/gif/GifDecoder;->skip()V

    goto :goto_0

    .line 688
    :sswitch_2
    invoke-virtual {p0}, Lcom/miui/internal/graphics/gif/GifDecoder;->readGraphicControlExt()V

    goto :goto_0

    .line 691
    :sswitch_3
    invoke-virtual {p0}, Lcom/miui/internal/graphics/gif/GifDecoder;->readBlock()I

    .line 692
    const-string v2, ""

    move-object v4, v2

    move v2, v3

    .line 693
    :goto_1
    const/16 v5, 0xb

    if-ge v2, v5, :cond_4

    .line 694
    new-instance v5, Ljava/lang/StringBuilder;

    invoke-direct {v5}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v5, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    iget-object v5, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->block:[B

    aget-byte v5, v5, v2

    int-to-char v5, v5

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    .line 693
    add-int/lit8 v2, v2, 0x1

    goto :goto_1

    .line 696
    :cond_4
    const-string v2, "NETSCAPE2.0"

    invoke-virtual {v4, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_5

    .line 697
    invoke-virtual {p0}, Lcom/miui/internal/graphics/gif/GifDecoder;->readNetscapeExt()V

    goto/16 :goto_0

    .line 699
    :cond_5
    invoke-virtual {p0}, Lcom/miui/internal/graphics/gif/GifDecoder;->skip()V

    goto/16 :goto_0

    .line 703
    :sswitch_4
    invoke-virtual {p0}, Lcom/miui/internal/graphics/gif/GifDecoder;->skip()V

    goto/16 :goto_0

    .line 706
    :sswitch_5
    invoke-virtual {p0}, Lcom/miui/internal/graphics/gif/GifDecoder;->skip()V

    goto/16 :goto_0

    .line 715
    :sswitch_6
    iput-boolean v1, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->KD:Z

    move v0, v1

    .line 716
    goto/16 :goto_0

    .line 670
    :sswitch_data_0
    .sparse-switch
        0x21 -> :sswitch_1
        0x2c -> :sswitch_0
        0x3b -> :sswitch_6
    .end sparse-switch

    .line 686
    :sswitch_data_1
    .sparse-switch
        0x1 -> :sswitch_5
        0xf9 -> :sswitch_2
        0xfe -> :sswitch_4
        0xff -> :sswitch_3
    .end sparse-switch
.end method

.method protected readGraphicControlExt()V
    .locals 3

    .prologue
    const/4 v0, 0x1

    .line 729
    invoke-virtual {p0}, Lcom/miui/internal/graphics/gif/GifDecoder;->read()I

    .line 730
    invoke-virtual {p0}, Lcom/miui/internal/graphics/gif/GifDecoder;->read()I

    move-result v1

    .line 731
    and-int/lit8 v2, v1, 0x1c

    shr-int/lit8 v2, v2, 0x2

    iput v2, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->dispose:I

    .line 732
    iget v2, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->dispose:I

    if-nez v2, :cond_0

    .line 733
    iput v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->dispose:I

    .line 735
    :cond_0
    and-int/lit8 v1, v1, 0x1

    if-eqz v1, :cond_2

    :goto_0
    iput-boolean v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->transparency:Z

    .line 736
    invoke-virtual {p0}, Lcom/miui/internal/graphics/gif/GifDecoder;->readShort()I

    move-result v0

    mul-int/lit8 v0, v0, 0xa

    iput v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->delay:I

    .line 737
    iget v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->delay:I

    if-gtz v0, :cond_1

    .line 738
    const/16 v0, 0x64

    iput v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->delay:I

    .line 740
    :cond_1
    invoke-virtual {p0}, Lcom/miui/internal/graphics/gif/GifDecoder;->read()I

    move-result v0

    iput v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->transIndex:I

    .line 741
    invoke-virtual {p0}, Lcom/miui/internal/graphics/gif/GifDecoder;->read()I

    .line 742
    return-void

    .line 735
    :cond_2
    const/4 v0, 0x0

    goto :goto_0
.end method

.method protected readHeader()V
    .locals 3

    .prologue
    .line 748
    iget-boolean v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->mCancel:Z

    if-eqz v0, :cond_1

    .line 766
    :cond_0
    :goto_0
    return-void

    .line 752
    :cond_1
    const-string v1, ""

    .line 753
    const/4 v0, 0x0

    :goto_1
    const/4 v2, 0x6

    if-ge v0, v2, :cond_2

    .line 754
    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {p0}, Lcom/miui/internal/graphics/gif/GifDecoder;->read()I

    move-result v2

    int-to-char v2, v2

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    .line 753
    add-int/lit8 v0, v0, 0x1

    goto :goto_1

    .line 756
    :cond_2
    const-string v0, "GIF"

    invoke-virtual {v1, v0}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v0

    if-nez v0, :cond_3

    .line 757
    const/4 v0, 0x1

    iput v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->status:I

    goto :goto_0

    .line 761
    :cond_3
    invoke-virtual {p0}, Lcom/miui/internal/graphics/gif/GifDecoder;->readLSD()V

    .line 762
    iget-boolean v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->gctFlag:Z

    if-eqz v0, :cond_0

    invoke-virtual {p0}, Lcom/miui/internal/graphics/gif/GifDecoder;->err()Z

    move-result v0

    if-nez v0, :cond_0

    .line 763
    iget v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->gctSize:I

    invoke-virtual {p0, v0}, Lcom/miui/internal/graphics/gif/GifDecoder;->readColorTable(I)[I

    move-result-object v0

    iput-object v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->gct:[I

    .line 764
    iget-object v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->gct:[I

    iget v1, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->bgIndex:I

    aget v0, v0, v1

    iput v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->bgColor:I

    goto :goto_0
.end method

.method protected readLSD()V
    .locals 2

    .prologue
    .line 832
    invoke-virtual {p0}, Lcom/miui/internal/graphics/gif/GifDecoder;->readShort()I

    move-result v0

    iput v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->width:I

    .line 833
    invoke-virtual {p0}, Lcom/miui/internal/graphics/gif/GifDecoder;->readShort()I

    move-result v0

    iput v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->height:I

    .line 835
    invoke-virtual {p0}, Lcom/miui/internal/graphics/gif/GifDecoder;->read()I

    move-result v1

    .line 836
    and-int/lit16 v0, v1, 0x80

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    :goto_0
    iput-boolean v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->gctFlag:Z

    .line 839
    const/4 v0, 0x2

    and-int/lit8 v1, v1, 0x7

    shl-int/2addr v0, v1

    iput v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->gctSize:I

    .line 840
    invoke-virtual {p0}, Lcom/miui/internal/graphics/gif/GifDecoder;->read()I

    move-result v0

    iput v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->bgIndex:I

    .line 841
    invoke-virtual {p0}, Lcom/miui/internal/graphics/gif/GifDecoder;->read()I

    move-result v0

    iput v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->pixelAspect:I

    .line 842
    return-void

    .line 836
    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method protected readNetscapeExt()V
    .locals 4

    .prologue
    const/4 v3, 0x1

    .line 849
    :cond_0
    invoke-virtual {p0}, Lcom/miui/internal/graphics/gif/GifDecoder;->readBlock()I

    .line 850
    iget-object v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->block:[B

    const/4 v1, 0x0

    aget-byte v0, v0, v1

    if-ne v0, v3, :cond_1

    .line 852
    iget-object v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->block:[B

    aget-byte v0, v0, v3

    and-int/lit16 v0, v0, 0xff

    .line 853
    iget-object v1, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->block:[B

    const/4 v2, 0x2

    aget-byte v1, v1, v2

    and-int/lit16 v1, v1, 0xff

    .line 854
    shl-int/lit8 v1, v1, 0x8

    or-int/2addr v0, v1

    iput v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->loopCount:I

    .line 856
    :cond_1
    iget v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->blockSize:I

    if-lez v0, :cond_2

    invoke-virtual {p0}, Lcom/miui/internal/graphics/gif/GifDecoder;->err()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 857
    :cond_2
    return-void
.end method

.method protected readShort()I
    .locals 2

    .prologue
    .line 864
    invoke-virtual {p0}, Lcom/miui/internal/graphics/gif/GifDecoder;->read()I

    move-result v0

    invoke-virtual {p0}, Lcom/miui/internal/graphics/gif/GifDecoder;->read()I

    move-result v1

    shl-int/lit8 v1, v1, 0x8

    or-int/2addr v0, v1

    return v0
.end method

.method public recycle()V
    .locals 3

    .prologue
    .line 169
    iget-object v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->frames:Ljava/util/Vector;

    if-eqz v0, :cond_0

    .line 170
    iget-object v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->frames:Ljava/util/Vector;

    invoke-virtual {v0}, Ljava/util/Vector;->size()I

    move-result v2

    .line 171
    const/4 v0, 0x0

    move v1, v0

    :goto_0
    if-ge v1, v2, :cond_0

    .line 172
    iget-object v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->frames:Ljava/util/Vector;

    invoke-virtual {v0, v1}, Ljava/util/Vector;->elementAt(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/graphics/gif/GifDecoder$a;

    invoke-virtual {v0}, Lcom/miui/internal/graphics/gif/GifDecoder$a;->recycle()V

    .line 171
    add-int/lit8 v0, v1, 0x1

    move v1, v0

    goto :goto_0

    .line 175
    :cond_0
    return-void
.end method

.method public requestCancelDecode()V
    .locals 1

    .prologue
    .line 368
    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->mCancel:Z

    .line 369
    invoke-direct {p0}, Lcom/miui/internal/graphics/gif/GifDecoder;->eb()V

    .line 370
    return-void
.end method

.method protected resetFrame()V
    .locals 2

    .prologue
    const/4 v1, 0x0

    .line 871
    iget v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->dispose:I

    iput v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->lastDispose:I

    .line 872
    iget v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->ix:I

    iput v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->lrx:I

    .line 873
    iget v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->iy:I

    iput v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->lry:I

    .line 874
    iget v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->iw:I

    iput v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->lrw:I

    .line 875
    iget v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->ih:I

    iput v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->lrh:I

    .line 876
    iget-object v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->image:Landroid/graphics/Bitmap;

    iput-object v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->lastBitmap:Landroid/graphics/Bitmap;

    .line 877
    iget v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->bgColor:I

    iput v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->lastBgColor:I

    .line 878
    iput v1, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->dispose:I

    .line 879
    iput-boolean v1, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->transparency:Z

    .line 880
    iput v1, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->delay:I

    .line 881
    const/4 v0, 0x0

    iput-object v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->lct:[I

    .line 882
    return-void
.end method

.method public setMaxDecodeSize(J)V
    .locals 0

    .prologue
    .line 183
    iput-wide p1, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->mMaxDecodeSize:J

    .line 184
    return-void
.end method

.method protected setPixels()V
    .locals 13

    .prologue
    const/4 v9, 0x4

    const/4 v8, 0x1

    const/4 v10, 0x2

    const/4 v2, 0x0

    .line 251
    iget-object v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->KK:[I

    if-nez v0, :cond_0

    .line 252
    iget v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->width:I

    iget v1, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->height:I

    mul-int/2addr v0, v1

    new-array v0, v0, [I

    iput-object v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->KK:[I

    .line 255
    :cond_0
    iget v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->lastDispose:I

    if-lez v0, :cond_4

    .line 256
    iget v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->lastDispose:I

    const/4 v1, 0x3

    if-ne v0, v1, :cond_1

    .line 258
    invoke-virtual {p0}, Lcom/miui/internal/graphics/gif/GifDecoder;->getFrameCount()I

    move-result v0

    add-int/lit8 v0, v0, -0x2

    .line 259
    if-lez v0, :cond_2

    .line 260
    add-int/lit8 v0, v0, -0x1

    invoke-virtual {p0, v0}, Lcom/miui/internal/graphics/gif/GifDecoder;->getFrame(I)Landroid/graphics/Bitmap;

    move-result-object v0

    .line 261
    iget-object v1, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->lastBitmap:Landroid/graphics/Bitmap;

    invoke-virtual {v0, v1}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_1

    .line 262
    iput-object v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->lastBitmap:Landroid/graphics/Bitmap;

    .line 263
    iget-object v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->lastBitmap:Landroid/graphics/Bitmap;

    iget-object v1, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->KK:[I

    iget v3, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->width:I

    iget v6, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->width:I

    iget v7, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->height:I

    move v4, v2

    move v5, v2

    invoke-virtual/range {v0 .. v7}, Landroid/graphics/Bitmap;->getPixels([IIIIIII)V

    .line 270
    :cond_1
    :goto_0
    iget-object v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->lastBitmap:Landroid/graphics/Bitmap;

    if-eqz v0, :cond_4

    .line 272
    iget v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->lastDispose:I

    if-ne v0, v10, :cond_4

    .line 275
    iget-boolean v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->transparency:Z

    if-nez v0, :cond_c

    .line 276
    iget v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->lastBgColor:I

    .line 278
    :goto_1
    iget v1, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->lry:I

    iget v3, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->width:I

    mul-int/2addr v1, v3

    iget v3, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->lrx:I

    add-int/2addr v1, v3

    move v3, v1

    move v1, v2

    .line 279
    :goto_2
    iget v4, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->lrh:I

    if-ge v1, v4, :cond_4

    .line 280
    iget v4, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->lrw:I

    add-int v5, v3, v4

    move v4, v3

    .line 281
    :goto_3
    if-ge v4, v5, :cond_3

    .line 282
    iget-object v6, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->KK:[I

    aput v0, v6, v4

    .line 281
    add-int/lit8 v4, v4, 0x1

    goto :goto_3

    .line 266
    :cond_2
    const/4 v0, 0x0

    iput-object v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->lastBitmap:Landroid/graphics/Bitmap;

    .line 267
    iget v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->width:I

    iget v1, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->height:I

    mul-int/2addr v0, v1

    new-array v0, v0, [I

    iput-object v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->KK:[I

    goto :goto_0

    .line 284
    :cond_3
    iget v4, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->width:I

    add-int/2addr v3, v4

    .line 279
    add-int/lit8 v1, v1, 0x1

    goto :goto_2

    .line 291
    :cond_4
    const/16 v0, 0x8

    move v1, v0

    move v3, v8

    move v0, v2

    .line 293
    :goto_4
    iget v4, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->ih:I

    if-ge v2, v4, :cond_9

    .line 295
    iget-boolean v4, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->interlace:Z

    if-eqz v4, :cond_b

    .line 296
    iget v4, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->ih:I

    if-lt v0, v4, :cond_5

    .line 297
    add-int/lit8 v3, v3, 0x1

    .line 298
    packed-switch v3, :pswitch_data_0

    .line 315
    :cond_5
    :goto_5
    add-int v4, v0, v1

    move v12, v0

    move v0, v4

    move v4, v12

    .line 317
    :goto_6
    iget v5, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->iy:I

    add-int/2addr v4, v5

    .line 318
    iget v5, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->height:I

    if-ge v4, v5, :cond_8

    .line 319
    iget v5, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->width:I

    mul-int/2addr v5, v4

    .line 320
    iget v4, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->ix:I

    add-int v6, v5, v4

    .line 321
    iget v4, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->iw:I

    add-int/2addr v4, v6

    .line 322
    iget v7, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->width:I

    add-int/2addr v7, v5

    if-ge v7, v4, :cond_6

    .line 323
    iget v4, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->width:I

    add-int/2addr v4, v5

    .line 325
    :cond_6
    iget v5, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->iw:I

    mul-int/2addr v5, v2

    move v7, v6

    .line 326
    :goto_7
    if-ge v7, v4, :cond_8

    .line 328
    iget-object v11, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->pixels:[B

    add-int/lit8 v6, v5, 0x1

    aget-byte v5, v11, v5

    and-int/lit16 v5, v5, 0xff

    .line 329
    iget-object v11, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->act:[I

    aget v5, v11, v5

    .line 330
    if-eqz v5, :cond_7

    .line 331
    iget-object v11, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->KK:[I

    aput v5, v11, v7

    .line 333
    :cond_7
    add-int/lit8 v5, v7, 0x1

    move v7, v5

    move v5, v6

    .line 334
    goto :goto_7

    :pswitch_0
    move v0, v9

    .line 301
    goto :goto_5

    :pswitch_1
    move v0, v10

    move v1, v9

    .line 305
    goto :goto_5

    :pswitch_2
    move v0, v8

    move v1, v10

    .line 309
    goto :goto_5

    .line 293
    :cond_8
    add-int/lit8 v2, v2, 0x1

    goto :goto_4

    .line 341
    :cond_9
    iget v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->KF:I

    iget v1, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->KJ:I

    if-gt v0, v1, :cond_a

    .line 342
    iget-object v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->image:Landroid/graphics/Bitmap;

    if-eqz v0, :cond_a

    iget-object v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->image:Landroid/graphics/Bitmap;

    invoke-virtual {v0}, Landroid/graphics/Bitmap;->isRecycled()Z

    move-result v0

    if-nez v0, :cond_a

    .line 343
    iget-object v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->image:Landroid/graphics/Bitmap;

    invoke-virtual {v0}, Landroid/graphics/Bitmap;->recycle()V

    .line 347
    :cond_a
    iget-object v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->KK:[I

    iget v1, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->width:I

    iget v2, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->height:I

    sget-object v3, Landroid/graphics/Bitmap$Config;->ARGB_8888:Landroid/graphics/Bitmap$Config;

    invoke-static {v0, v1, v2, v3}, Landroid/graphics/Bitmap;->createBitmap([IIILandroid/graphics/Bitmap$Config;)Landroid/graphics/Bitmap;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->image:Landroid/graphics/Bitmap;

    .line 348
    return-void

    :cond_b
    move v4, v2

    goto :goto_6

    :cond_c
    move v0, v2

    goto/16 :goto_1

    .line 298
    nop

    :pswitch_data_0
    .packed-switch 0x2
        :pswitch_0
        :pswitch_1
        :pswitch_2
    .end packed-switch
.end method

.method public setStartFrame(I)V
    .locals 0

    .prologue
    .line 165
    iput p1, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->KJ:I

    .line 166
    return-void
.end method

.method protected skip()V
    .locals 1

    .prologue
    .line 889
    :cond_0
    invoke-virtual {p0}, Lcom/miui/internal/graphics/gif/GifDecoder;->readBlock()I

    .line 890
    iget v0, p0, Lcom/miui/internal/graphics/gif/GifDecoder;->blockSize:I

    if-lez v0, :cond_1

    invoke-virtual {p0}, Lcom/miui/internal/graphics/gif/GifDecoder;->err()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 891
    :cond_1
    return-void
.end method
