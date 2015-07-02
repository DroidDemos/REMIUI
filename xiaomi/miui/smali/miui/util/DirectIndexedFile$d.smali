.class Lmiui/util/DirectIndexedFile$d;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/util/DirectIndexedFile;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0xa
    name = "d"
.end annotation


# static fields
.field private static final qK:[B

.field private static final qL:I = 0x2


# instance fields
.field private qM:[Lmiui/util/DirectIndexedFile$b;

.field private qN:I


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .prologue
    .line 524
    const/4 v0, 0x4

    new-array v0, v0, [B

    fill-array-data v0, :array_0

    sput-object v0, Lmiui/util/DirectIndexedFile$d;->qK:[B

    return-void

    nop

    :array_0
    .array-data 1
        0x49t
        0x44t
        0x46t
        0x20t
    .end array-data
.end method

.method private constructor <init>(II)V
    .locals 1

    .prologue
    .line 533
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 534
    new-array v0, p1, [Lmiui/util/DirectIndexedFile$b;

    iput-object v0, p0, Lmiui/util/DirectIndexedFile$d;->qM:[Lmiui/util/DirectIndexedFile$b;

    .line 535
    iput p2, p0, Lmiui/util/DirectIndexedFile$d;->qN:I

    .line 536
    return-void
.end method

.method synthetic constructor <init>(IILmiui/util/DirectIndexedFile$1;)V
    .locals 0

    .prologue
    .line 523
    invoke-direct {p0, p1, p2}, Lmiui/util/DirectIndexedFile$d;-><init>(II)V

    return-void
.end method

.method private a(Ljava/io/DataOutput;)I
    .locals 5
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 561
    sget-object v0, Lmiui/util/DirectIndexedFile$d;->qK:[B

    array-length v0, v0

    add-int/lit8 v0, v0, 0x4

    add-int/lit8 v0, v0, 0x4

    add-int/lit8 v1, v0, 0x4

    .line 562
    if-eqz p1, :cond_0

    .line 563
    sget-object v0, Lmiui/util/DirectIndexedFile$d;->qK:[B

    invoke-interface {p1, v0}, Ljava/io/DataOutput;->write([B)V

    .line 564
    const/4 v0, 0x2

    invoke-interface {p1, v0}, Ljava/io/DataOutput;->writeInt(I)V

    .line 565
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$d;->qM:[Lmiui/util/DirectIndexedFile$b;

    array-length v0, v0

    invoke-interface {p1, v0}, Ljava/io/DataOutput;->writeInt(I)V

    .line 566
    iget v0, p0, Lmiui/util/DirectIndexedFile$d;->qN:I

    invoke-interface {p1, v0}, Ljava/io/DataOutput;->writeInt(I)V

    .line 568
    :cond_0
    iget-object v2, p0, Lmiui/util/DirectIndexedFile$d;->qM:[Lmiui/util/DirectIndexedFile$b;

    array-length v3, v2

    const/4 v0, 0x0

    :goto_0
    if-ge v0, v3, :cond_1

    aget-object v4, v2, v0

    .line 569
    invoke-static {v4, p1}, Lmiui/util/DirectIndexedFile$b;->a(Lmiui/util/DirectIndexedFile$b;Ljava/io/DataOutput;)I

    move-result v4

    add-int/2addr v1, v4

    .line 568
    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    .line 571
    :cond_1
    return v1
.end method

.method static synthetic a(Lmiui/util/DirectIndexedFile$d;Ljava/io/DataOutput;)I
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 523
    invoke-direct {p0, p1}, Lmiui/util/DirectIndexedFile$d;->a(Ljava/io/DataOutput;)I

    move-result v0

    return v0
.end method

.method static synthetic a(Lmiui/util/DirectIndexedFile$d;)[Lmiui/util/DirectIndexedFile$b;
    .locals 1

    .prologue
    .line 523
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$d;->qM:[Lmiui/util/DirectIndexedFile$b;

    return-object v0
.end method

.method static synthetic b(Lmiui/util/DirectIndexedFile$d;)I
    .locals 1

    .prologue
    .line 523
    iget v0, p0, Lmiui/util/DirectIndexedFile$d;->qN:I

    return v0
.end method

.method private static e(Ljava/io/DataInput;)Lmiui/util/DirectIndexedFile$d;
    .locals 5
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    const/4 v1, 0x0

    .line 539
    sget-object v0, Lmiui/util/DirectIndexedFile$d;->qK:[B

    array-length v0, v0

    new-array v2, v0, [B

    move v0, v1

    .line 540
    :goto_0
    array-length v3, v2

    if-ge v0, v3, :cond_0

    .line 541
    invoke-interface {p0}, Ljava/io/DataInput;->readByte()B

    move-result v3

    aput-byte v3, v2, v0

    .line 540
    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    .line 543
    :cond_0
    sget-object v0, Lmiui/util/DirectIndexedFile$d;->qK:[B

    invoke-static {v2, v0}, Ljava/util/Arrays;->equals([B[B)Z

    move-result v0

    if-nez v0, :cond_1

    .line 544
    new-instance v0, Ljava/io/IOException;

    const-string v1, "File tag unmatched, file may be corrupt"

    invoke-direct {v0, v1}, Ljava/io/IOException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 546
    :cond_1
    invoke-interface {p0}, Ljava/io/DataInput;->readInt()I

    move-result v0

    .line 547
    const/4 v2, 0x2

    if-eq v0, v2, :cond_2

    .line 548
    new-instance v0, Ljava/io/IOException;

    const-string v1, "File version unmatched, please upgrade your reader"

    invoke-direct {v0, v1}, Ljava/io/IOException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 551
    :cond_2
    invoke-interface {p0}, Ljava/io/DataInput;->readInt()I

    move-result v0

    .line 552
    invoke-interface {p0}, Ljava/io/DataInput;->readInt()I

    move-result v2

    .line 553
    new-instance v3, Lmiui/util/DirectIndexedFile$d;

    invoke-direct {v3, v0, v2}, Lmiui/util/DirectIndexedFile$d;-><init>(II)V

    .line 554
    :goto_1
    if-ge v1, v0, :cond_3

    .line 555
    iget-object v2, v3, Lmiui/util/DirectIndexedFile$d;->qM:[Lmiui/util/DirectIndexedFile$b;

    invoke-static {p0}, Lmiui/util/DirectIndexedFile$b;->d(Ljava/io/DataInput;)Lmiui/util/DirectIndexedFile$b;

    move-result-object v4

    aput-object v4, v2, v1

    .line 554
    add-int/lit8 v1, v1, 0x1

    goto :goto_1

    .line 557
    :cond_3
    return-object v3
.end method

.method static synthetic f(Ljava/io/DataInput;)Lmiui/util/DirectIndexedFile$d;
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 523
    invoke-static {p0}, Lmiui/util/DirectIndexedFile$d;->e(Ljava/io/DataInput;)Lmiui/util/DirectIndexedFile$d;

    move-result-object v0

    return-object v0
.end method
