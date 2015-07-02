.class Lmiui/util/DirectIndexedFile$e;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lmiui/util/DirectIndexedFile$c;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/util/DirectIndexedFile;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0xa
    name = "e"
.end annotation


# instance fields
.field private yC:Ljava/io/InputStream;

.field private yD:J


# direct methods
.method constructor <init>(Ljava/io/InputStream;)V
    .locals 2

    .prologue
    .line 685
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 686
    iput-object p1, p0, Lmiui/util/DirectIndexedFile$e;->yC:Ljava/io/InputStream;

    .line 687
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$e;->yC:Ljava/io/InputStream;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Ljava/io/InputStream;->mark(I)V

    .line 688
    const-wide/16 v0, 0x0

    iput-wide v0, p0, Lmiui/util/DirectIndexedFile$e;->yD:J

    .line 689
    return-void
.end method


# virtual methods
.method public close()V
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 828
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$e;->yC:Ljava/io/InputStream;

    invoke-virtual {v0}, Ljava/io/InputStream;->close()V

    .line 829
    return-void
.end method

.method public getFilePointer()J
    .locals 2
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 833
    iget-wide v0, p0, Lmiui/util/DirectIndexedFile$e;->yD:J

    return-wide v0
.end method

.method public readBoolean()Z
    .locals 4
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 693
    iget-wide v0, p0, Lmiui/util/DirectIndexedFile$e;->yD:J

    const-wide/16 v2, 0x1

    add-long/2addr v0, v2

    iput-wide v0, p0, Lmiui/util/DirectIndexedFile$e;->yD:J

    .line 694
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$e;->yC:Ljava/io/InputStream;

    invoke-virtual {v0}, Ljava/io/InputStream;->read()I

    move-result v0

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public readByte()B
    .locals 4
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 699
    iget-wide v0, p0, Lmiui/util/DirectIndexedFile$e;->yD:J

    const-wide/16 v2, 0x1

    add-long/2addr v0, v2

    iput-wide v0, p0, Lmiui/util/DirectIndexedFile$e;->yD:J

    .line 700
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$e;->yC:Ljava/io/InputStream;

    invoke-virtual {v0}, Ljava/io/InputStream;->read()I

    move-result v0

    int-to-byte v0, v0

    return v0
.end method

.method public readChar()C
    .locals 7
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    const/4 v6, 0x2

    const/4 v0, 0x0

    .line 705
    new-array v1, v6, [B

    .line 707
    iget-wide v2, p0, Lmiui/util/DirectIndexedFile$e;->yD:J

    const-wide/16 v4, 0x2

    add-long/2addr v2, v4

    iput-wide v2, p0, Lmiui/util/DirectIndexedFile$e;->yD:J

    .line 708
    iget-object v2, p0, Lmiui/util/DirectIndexedFile$e;->yC:Ljava/io/InputStream;

    invoke-virtual {v2, v1}, Ljava/io/InputStream;->read([B)I

    move-result v2

    if-ne v2, v6, :cond_0

    .line 709
    const/4 v2, 0x1

    aget-byte v2, v1, v2

    and-int/lit16 v2, v2, 0xff

    int-to-char v2, v2

    .line 710
    aget-byte v0, v1, v0

    shl-int/lit8 v0, v0, 0x8

    const v1, 0xff00

    and-int/2addr v0, v1

    or-int/2addr v0, v2

    int-to-char v0, v0

    .line 712
    :cond_0
    return v0
.end method

.method public readDouble()D
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 717
    new-instance v0, Ljava/io/IOException;

    invoke-direct {v0}, Ljava/io/IOException;-><init>()V

    throw v0
.end method

.method public readFloat()F
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 722
    new-instance v0, Ljava/io/IOException;

    invoke-direct {v0}, Ljava/io/IOException;-><init>()V

    throw v0
.end method

.method public readFully([B)V
    .locals 5
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 727
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$e;->yC:Ljava/io/InputStream;

    invoke-virtual {v0, p1}, Ljava/io/InputStream;->read([B)I

    move-result v0

    .line 728
    iget-wide v1, p0, Lmiui/util/DirectIndexedFile$e;->yD:J

    int-to-long v3, v0

    add-long v0, v1, v3

    iput-wide v0, p0, Lmiui/util/DirectIndexedFile$e;->yD:J

    .line 729
    return-void
.end method

.method public readFully([BII)V
    .locals 5
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 733
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$e;->yC:Ljava/io/InputStream;

    invoke-virtual {v0, p1, p2, p3}, Ljava/io/InputStream;->read([BII)I

    move-result v0

    .line 734
    iget-wide v1, p0, Lmiui/util/DirectIndexedFile$e;->yD:J

    int-to-long v3, v0

    add-long v0, v1, v3

    iput-wide v0, p0, Lmiui/util/DirectIndexedFile$e;->yD:J

    .line 735
    return-void
.end method

.method public readInt()I
    .locals 7
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    const/4 v6, 0x4

    const/4 v0, 0x0

    .line 739
    new-array v1, v6, [B

    .line 741
    iget-wide v2, p0, Lmiui/util/DirectIndexedFile$e;->yD:J

    const-wide/16 v4, 0x4

    add-long/2addr v2, v4

    iput-wide v2, p0, Lmiui/util/DirectIndexedFile$e;->yD:J

    .line 742
    iget-object v2, p0, Lmiui/util/DirectIndexedFile$e;->yC:Ljava/io/InputStream;

    invoke-virtual {v2, v1}, Ljava/io/InputStream;->read([B)I

    move-result v2

    if-ne v2, v6, :cond_0

    .line 743
    const/4 v2, 0x3

    aget-byte v2, v1, v2

    and-int/lit16 v2, v2, 0xff

    .line 744
    const/4 v3, 0x2

    aget-byte v3, v1, v3

    shl-int/lit8 v3, v3, 0x8

    const v4, 0xff00

    and-int/2addr v3, v4

    or-int/2addr v2, v3

    .line 745
    const/4 v3, 0x1

    aget-byte v3, v1, v3

    shl-int/lit8 v3, v3, 0x10

    const/high16 v4, 0xff0000

    and-int/2addr v3, v4

    or-int/2addr v2, v3

    .line 746
    aget-byte v0, v1, v0

    shl-int/lit8 v0, v0, 0x18

    const/high16 v1, -0x1000000

    and-int/2addr v0, v1

    or-int/2addr v0, v2

    .line 748
    :cond_0
    return v0
.end method

.method public readLine()Ljava/lang/String;
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 753
    new-instance v0, Ljava/io/IOException;

    invoke-direct {v0}, Ljava/io/IOException;-><init>()V

    throw v0
.end method

.method public readLong()J
    .locals 8
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    const/16 v7, 0x8

    .line 758
    new-array v2, v7, [B

    .line 759
    const-wide/16 v0, 0x0

    .line 760
    iget-wide v3, p0, Lmiui/util/DirectIndexedFile$e;->yD:J

    const-wide/16 v5, 0x8

    add-long/2addr v3, v5

    iput-wide v3, p0, Lmiui/util/DirectIndexedFile$e;->yD:J

    .line 761
    iget-object v3, p0, Lmiui/util/DirectIndexedFile$e;->yC:Ljava/io/InputStream;

    invoke-virtual {v3, v2}, Ljava/io/InputStream;->read([B)I

    move-result v3

    if-ne v3, v7, :cond_0

    .line 762
    const/4 v0, 0x7

    aget-byte v0, v2, v0

    and-int/lit16 v0, v0, 0xff

    int-to-long v0, v0

    .line 763
    const/4 v3, 0x6

    aget-byte v3, v2, v3

    shl-int/lit8 v3, v3, 0x8

    int-to-long v3, v3

    const-wide/32 v5, 0xff00

    and-long/2addr v3, v5

    or-long/2addr v0, v3

    .line 764
    const/4 v3, 0x5

    aget-byte v3, v2, v3

    shl-int/lit8 v3, v3, 0x10

    int-to-long v3, v3

    const-wide/32 v5, 0xff0000

    and-long/2addr v3, v5

    or-long/2addr v0, v3

    .line 765
    const/4 v3, 0x4

    aget-byte v3, v2, v3

    shl-int/lit8 v3, v3, 0x18

    int-to-long v3, v3

    const-wide v5, 0xff000000L

    and-long/2addr v3, v5

    or-long/2addr v0, v3

    .line 766
    const/4 v3, 0x3

    aget-byte v3, v2, v3

    int-to-long v3, v3

    const/16 v5, 0x20

    shl-long/2addr v3, v5

    const-wide v5, 0xff00000000L

    and-long/2addr v3, v5

    or-long/2addr v0, v3

    .line 767
    const/4 v3, 0x2

    aget-byte v3, v2, v3

    int-to-long v3, v3

    const/16 v5, 0x28

    shl-long/2addr v3, v5

    const-wide v5, 0xff0000000000L

    and-long/2addr v3, v5

    or-long/2addr v0, v3

    .line 768
    const/4 v3, 0x1

    aget-byte v3, v2, v3

    int-to-long v3, v3

    const/16 v5, 0x30

    shl-long/2addr v3, v5

    const-wide/high16 v5, 0xff000000000000L

    and-long/2addr v3, v5

    or-long/2addr v0, v3

    .line 769
    const/4 v3, 0x0

    aget-byte v2, v2, v3

    int-to-long v2, v2

    const/16 v4, 0x38

    shl-long/2addr v2, v4

    const-wide/high16 v4, -0x100000000000000L

    and-long/2addr v2, v4

    or-long/2addr v0, v2

    .line 771
    :cond_0
    return-wide v0
.end method

.method public readShort()S
    .locals 7
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    const/4 v6, 0x2

    const/4 v0, 0x0

    .line 776
    new-array v1, v6, [B

    .line 778
    iget-wide v2, p0, Lmiui/util/DirectIndexedFile$e;->yD:J

    const-wide/16 v4, 0x2

    add-long/2addr v2, v4

    iput-wide v2, p0, Lmiui/util/DirectIndexedFile$e;->yD:J

    .line 779
    iget-object v2, p0, Lmiui/util/DirectIndexedFile$e;->yC:Ljava/io/InputStream;

    invoke-virtual {v2, v1}, Ljava/io/InputStream;->read([B)I

    move-result v2

    if-ne v2, v6, :cond_0

    .line 780
    const/4 v2, 0x1

    aget-byte v2, v1, v2

    and-int/lit16 v2, v2, 0xff

    int-to-short v2, v2

    .line 781
    aget-byte v0, v1, v0

    shl-int/lit8 v0, v0, 0x8

    const v1, 0xff00

    and-int/2addr v0, v1

    or-int/2addr v0, v2

    int-to-short v0, v0

    .line 783
    :cond_0
    return v0
.end method

.method public readUTF()Ljava/lang/String;
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 806
    new-instance v0, Ljava/io/IOException;

    invoke-direct {v0}, Ljava/io/IOException;-><init>()V

    throw v0
.end method

.method public readUnsignedByte()I
    .locals 4
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 788
    iget-wide v0, p0, Lmiui/util/DirectIndexedFile$e;->yD:J

    const-wide/16 v2, 0x1

    add-long/2addr v0, v2

    iput-wide v0, p0, Lmiui/util/DirectIndexedFile$e;->yD:J

    .line 789
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$e;->yC:Ljava/io/InputStream;

    invoke-virtual {v0}, Ljava/io/InputStream;->read()I

    move-result v0

    int-to-byte v0, v0

    return v0
.end method

.method public readUnsignedShort()I
    .locals 7
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    const/4 v6, 0x2

    const/4 v0, 0x0

    .line 794
    new-array v1, v6, [B

    .line 796
    iget-wide v2, p0, Lmiui/util/DirectIndexedFile$e;->yD:J

    const-wide/16 v4, 0x2

    add-long/2addr v2, v4

    iput-wide v2, p0, Lmiui/util/DirectIndexedFile$e;->yD:J

    .line 797
    iget-object v2, p0, Lmiui/util/DirectIndexedFile$e;->yC:Ljava/io/InputStream;

    invoke-virtual {v2, v1}, Ljava/io/InputStream;->read([B)I

    move-result v2

    if-ne v2, v6, :cond_0

    .line 798
    const/4 v2, 0x1

    aget-byte v2, v1, v2

    and-int/lit16 v2, v2, 0xff

    int-to-short v2, v2

    .line 799
    aget-byte v0, v1, v0

    shl-int/lit8 v0, v0, 0x8

    const v1, 0xff00

    and-int/2addr v0, v1

    or-int/2addr v0, v2

    int-to-short v0, v0

    .line 801
    :cond_0
    return v0
.end method

.method public seek(J)V
    .locals 2
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 818
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$e;->yC:Ljava/io/InputStream;

    invoke-virtual {v0}, Ljava/io/InputStream;->reset()V

    .line 819
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$e;->yC:Ljava/io/InputStream;

    invoke-virtual {v0, p1, p2}, Ljava/io/InputStream;->skip(J)J

    move-result-wide v0

    cmp-long v0, v0, p1

    if-nez v0, :cond_0

    .line 820
    iput-wide p1, p0, Lmiui/util/DirectIndexedFile$e;->yD:J

    .line 824
    return-void

    .line 822
    :cond_0
    new-instance v0, Ljava/io/IOException;

    const-string v1, "Skip failed"

    invoke-direct {v0, v1}, Ljava/io/IOException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

.method public skipBytes(I)I
    .locals 5
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 811
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$e;->yC:Ljava/io/InputStream;

    int-to-long v1, p1

    invoke-virtual {v0, v1, v2}, Ljava/io/InputStream;->skip(J)J

    move-result-wide v0

    long-to-int v0, v0

    .line 812
    iget-wide v1, p0, Lmiui/util/DirectIndexedFile$e;->yD:J

    int-to-long v3, v0

    add-long/2addr v1, v3

    iput-wide v1, p0, Lmiui/util/DirectIndexedFile$e;->yD:J

    .line 813
    return v0
.end method
