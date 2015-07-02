.class Lmiui/util/DirectIndexedFile$b;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/util/DirectIndexedFile;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0xa
    name = "b"
.end annotation


# instance fields
.field private gy:J

.field private gz:J


# direct methods
.method private constructor <init>(JJ)V
    .locals 0

    .prologue
    .line 503
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 504
    iput-wide p1, p0, Lmiui/util/DirectIndexedFile$b;->gy:J

    .line 505
    iput-wide p3, p0, Lmiui/util/DirectIndexedFile$b;->gz:J

    .line 506
    return-void
.end method

.method synthetic constructor <init>(JJLmiui/util/DirectIndexedFile$1;)V
    .locals 0

    .prologue
    .line 499
    invoke-direct {p0, p1, p2, p3, p4}, Lmiui/util/DirectIndexedFile$b;-><init>(JJ)V

    return-void
.end method

.method private a(Ljava/io/DataOutput;)I
    .locals 2
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 515
    if-eqz p1, :cond_0

    .line 516
    iget-wide v0, p0, Lmiui/util/DirectIndexedFile$b;->gy:J

    invoke-interface {p1, v0, v1}, Ljava/io/DataOutput;->writeLong(J)V

    .line 517
    iget-wide v0, p0, Lmiui/util/DirectIndexedFile$b;->gz:J

    invoke-interface {p1, v0, v1}, Ljava/io/DataOutput;->writeLong(J)V

    .line 519
    :cond_0
    const/16 v0, 0x10

    return v0
.end method

.method static synthetic a(Lmiui/util/DirectIndexedFile$b;Ljava/io/DataOutput;)I
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 499
    invoke-direct {p0, p1}, Lmiui/util/DirectIndexedFile$b;->a(Ljava/io/DataOutput;)I

    move-result v0

    return v0
.end method

.method static synthetic a(Lmiui/util/DirectIndexedFile$b;)J
    .locals 2

    .prologue
    .line 499
    iget-wide v0, p0, Lmiui/util/DirectIndexedFile$b;->gy:J

    return-wide v0
.end method

.method static synthetic a(Lmiui/util/DirectIndexedFile$b;J)J
    .locals 0

    .prologue
    .line 499
    iput-wide p1, p0, Lmiui/util/DirectIndexedFile$b;->gy:J

    return-wide p1
.end method

.method static synthetic b(Lmiui/util/DirectIndexedFile$b;)J
    .locals 2

    .prologue
    .line 499
    iget-wide v0, p0, Lmiui/util/DirectIndexedFile$b;->gz:J

    return-wide v0
.end method

.method static synthetic b(Lmiui/util/DirectIndexedFile$b;J)J
    .locals 0

    .prologue
    .line 499
    iput-wide p1, p0, Lmiui/util/DirectIndexedFile$b;->gz:J

    return-wide p1
.end method

.method private static c(Ljava/io/DataInput;)Lmiui/util/DirectIndexedFile$b;
    .locals 5
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 509
    invoke-interface {p0}, Ljava/io/DataInput;->readLong()J

    move-result-wide v0

    .line 510
    invoke-interface {p0}, Ljava/io/DataInput;->readLong()J

    move-result-wide v2

    .line 511
    new-instance v4, Lmiui/util/DirectIndexedFile$b;

    invoke-direct {v4, v0, v1, v2, v3}, Lmiui/util/DirectIndexedFile$b;-><init>(JJ)V

    return-object v4
.end method

.method static synthetic d(Ljava/io/DataInput;)Lmiui/util/DirectIndexedFile$b;
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 499
    invoke-static {p0}, Lmiui/util/DirectIndexedFile$b;->c(Ljava/io/DataInput;)Lmiui/util/DirectIndexedFile$b;

    move-result-object v0

    return-object v0
.end method
