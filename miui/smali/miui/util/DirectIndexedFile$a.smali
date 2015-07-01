.class Lmiui/util/DirectIndexedFile$a;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/util/DirectIndexedFile;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0xa
    name = "a"
.end annotation


# instance fields
.field fI:I

.field fJ:I

.field fK:J


# direct methods
.method private constructor <init>(IIJ)V
    .locals 0

    .prologue
    .line 80
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 81
    iput p1, p0, Lmiui/util/DirectIndexedFile$a;->fI:I

    .line 82
    iput p2, p0, Lmiui/util/DirectIndexedFile$a;->fJ:I

    .line 83
    iput-wide p3, p0, Lmiui/util/DirectIndexedFile$a;->fK:J

    .line 84
    return-void
.end method

.method synthetic constructor <init>(IIJLmiui/util/DirectIndexedFile$1;)V
    .locals 0

    .prologue
    .line 75
    invoke-direct {p0, p1, p2, p3, p4}, Lmiui/util/DirectIndexedFile$a;-><init>(IIJ)V

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
    .line 94
    if-eqz p1, :cond_0

    .line 95
    iget v0, p0, Lmiui/util/DirectIndexedFile$a;->fI:I

    invoke-interface {p1, v0}, Ljava/io/DataOutput;->writeInt(I)V

    .line 96
    iget v0, p0, Lmiui/util/DirectIndexedFile$a;->fJ:I

    invoke-interface {p1, v0}, Ljava/io/DataOutput;->writeInt(I)V

    .line 97
    iget-wide v0, p0, Lmiui/util/DirectIndexedFile$a;->fK:J

    invoke-interface {p1, v0, v1}, Ljava/io/DataOutput;->writeLong(J)V

    .line 99
    :cond_0
    const/16 v0, 0x10

    return v0
.end method

.method static synthetic a(Lmiui/util/DirectIndexedFile$a;Ljava/io/DataOutput;)I
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 75
    invoke-direct {p0, p1}, Lmiui/util/DirectIndexedFile$a;->a(Ljava/io/DataOutput;)I

    move-result v0

    return v0
.end method

.method private static a(Ljava/io/DataInput;)Lmiui/util/DirectIndexedFile$a;
    .locals 5
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 87
    invoke-interface {p0}, Ljava/io/DataInput;->readInt()I

    move-result v0

    .line 88
    invoke-interface {p0}, Ljava/io/DataInput;->readInt()I

    move-result v1

    .line 89
    invoke-interface {p0}, Ljava/io/DataInput;->readLong()J

    move-result-wide v2

    .line 90
    new-instance v4, Lmiui/util/DirectIndexedFile$a;

    invoke-direct {v4, v0, v1, v2, v3}, Lmiui/util/DirectIndexedFile$a;-><init>(IIJ)V

    return-object v4
.end method

.method static synthetic b(Ljava/io/DataInput;)Lmiui/util/DirectIndexedFile$a;
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 75
    invoke-static {p0}, Lmiui/util/DirectIndexedFile$a;->a(Ljava/io/DataInput;)Lmiui/util/DirectIndexedFile$a;

    move-result-object v0

    return-object v0
.end method
