.class Lmiui/util/DirectIndexedFile$DataItemDescriptor;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/util/DirectIndexedFile;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0xa
    name = "DataItemDescriptor"
.end annotation

.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;
    }
.end annotation


# static fields
.field private static Pj:[B


# instance fields
.field private Pk:Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;

.field private Pl:B

.field private Pm:B

.field private Pn:B

.field private fK:J


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .prologue
    .line 116
    const/16 v0, 0x400

    new-array v0, v0, [B

    sput-object v0, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->Pj:[B

    return-void
.end method

.method private constructor <init>(Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;BBBJ)V
    .locals 0

    .prologue
    .line 143
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 144
    iput-object p1, p0, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->Pk:Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;

    .line 145
    iput-byte p2, p0, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->Pl:B

    .line 146
    iput-byte p3, p0, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->Pm:B

    .line 147
    iput-byte p4, p0, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->Pn:B

    .line 148
    iput-wide p5, p0, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->fK:J

    .line 149
    return-void
.end method

.method synthetic constructor <init>(Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;BBBJLmiui/util/DirectIndexedFile$1;)V
    .locals 0

    .prologue
    .line 103
    invoke-direct/range {p0 .. p6}, Lmiui/util/DirectIndexedFile$DataItemDescriptor;-><init>(Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;BBBJ)V

    return-void
.end method

.method static synthetic a(Lmiui/util/DirectIndexedFile$DataItemDescriptor;)B
    .locals 1

    .prologue
    .line 103
    iget-byte v0, p0, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->Pl:B

    return v0
.end method

.method static synthetic a(Lmiui/util/DirectIndexedFile$DataItemDescriptor;B)B
    .locals 0

    .prologue
    .line 103
    iput-byte p1, p0, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->Pl:B

    return p1
.end method

.method private a(Ljava/io/DataOutput;)I
    .locals 2
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 161
    if-eqz p1, :cond_0

    .line 162
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->Pk:Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;

    invoke-virtual {v0}, Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;->ordinal()I

    move-result v0

    invoke-interface {p1, v0}, Ljava/io/DataOutput;->writeByte(I)V

    .line 163
    iget-byte v0, p0, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->Pl:B

    invoke-interface {p1, v0}, Ljava/io/DataOutput;->writeByte(I)V

    .line 164
    iget-byte v0, p0, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->Pm:B

    invoke-interface {p1, v0}, Ljava/io/DataOutput;->writeByte(I)V

    .line 165
    iget-byte v0, p0, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->Pn:B

    invoke-interface {p1, v0}, Ljava/io/DataOutput;->writeByte(I)V

    .line 166
    iget-wide v0, p0, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->fK:J

    invoke-interface {p1, v0, v1}, Ljava/io/DataOutput;->writeLong(J)V

    .line 168
    :cond_0
    const/16 v0, 0xc

    return v0
.end method

.method private a(Ljava/io/DataOutput;Ljava/util/List;)I
    .locals 8
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/io/DataOutput;",
            "Ljava/util/List",
            "<",
            "Ljava/lang/Object;",
            ">;)I"
        }
    .end annotation

    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    const/4 v3, 0x0

    .line 172
    if-eqz p1, :cond_0

    iget-byte v0, p0, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->Pn:B

    if-eqz v0, :cond_0

    iget-byte v0, p0, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->Pm:B

    if-nez v0, :cond_2

    .line 174
    :cond_0
    invoke-interface {p2}, Ljava/util/List;->size()I

    move-result v0

    mul-int/lit8 v0, v0, 0x4

    .line 175
    invoke-interface {p2}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v4

    move v1, v0

    move v2, v3

    :goto_0
    invoke-interface {v4}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_1

    invoke-interface {v4}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    .line 177
    sget-object v5, Lmiui/util/DirectIndexedFile$1;->FF:[I

    iget-object v6, p0, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->Pk:Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;

    invoke-virtual {v6}, Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;->ordinal()I

    move-result v6

    aget v5, v5, v6

    packed-switch v5, :pswitch_data_0

    move v0, v1

    move v1, v3

    .line 199
    :goto_1
    if-ge v2, v1, :cond_4

    :goto_2
    move v2, v1

    move v1, v0

    .line 202
    goto :goto_0

    .line 179
    :pswitch_0
    check-cast v0, Ljava/lang/String;

    invoke-virtual {v0}, Ljava/lang/String;->getBytes()[B

    move-result-object v0

    array-length v0, v0

    .line 180
    add-int/2addr v1, v0

    move v7, v0

    move v0, v1

    move v1, v7

    .line 181
    goto :goto_1

    .line 183
    :pswitch_1
    check-cast v0, [B

    check-cast v0, [B

    array-length v0, v0

    .line 184
    add-int/2addr v1, v0

    move v7, v0

    move v0, v1

    move v1, v7

    .line 185
    goto :goto_1

    .line 187
    :pswitch_2
    check-cast v0, [S

    check-cast v0, [S

    array-length v0, v0

    .line 188
    mul-int/lit8 v5, v0, 0x2

    add-int/2addr v1, v5

    move v7, v0

    move v0, v1

    move v1, v7

    .line 189
    goto :goto_1

    .line 191
    :pswitch_3
    check-cast v0, [I

    check-cast v0, [I

    array-length v0, v0

    .line 192
    mul-int/lit8 v5, v0, 0x4

    add-int/2addr v1, v5

    move v7, v0

    move v0, v1

    move v1, v7

    .line 193
    goto :goto_1

    .line 195
    :pswitch_4
    check-cast v0, [J

    check-cast v0, [J

    array-length v0, v0

    .line 196
    mul-int/lit8 v5, v0, 0x8

    add-int/2addr v1, v5

    move v7, v0

    move v0, v1

    move v1, v7

    goto :goto_1

    .line 203
    :cond_1
    invoke-static {v2}, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->ap(I)B

    move-result v0

    iput-byte v0, p0, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->Pm:B

    .line 204
    iget-byte v0, p0, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->Pm:B

    invoke-interface {p2}, Ljava/util/List;->size()I

    move-result v2

    mul-int/2addr v0, v2

    add-int/2addr v0, v1

    .line 205
    invoke-static {v0}, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->ap(I)B

    move-result v0

    iput-byte v0, p0, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->Pn:B

    .line 208
    :cond_2
    iget-byte v0, p0, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->Pn:B

    invoke-interface {p2}, Ljava/util/List;->size()I

    move-result v1

    mul-int v2, v0, v1

    .line 209
    if-eqz p1, :cond_3

    .line 211
    invoke-interface {p2}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v3

    move v1, v2

    :goto_3
    invoke-interface {v3}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_3

    invoke-interface {v3}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    .line 212
    iget-byte v4, p0, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->Pn:B

    int-to-long v5, v1

    invoke-static {p1, v4, v5, v6}, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->a(Ljava/io/DataOutput;IJ)V

    .line 213
    sget-object v4, Lmiui/util/DirectIndexedFile$1;->FF:[I

    iget-object v5, p0, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->Pk:Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;

    invoke-virtual {v5}, Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;->ordinal()I

    move-result v5

    aget v4, v4, v5

    packed-switch v4, :pswitch_data_1

    move v0, v1

    :goto_4
    move v1, v0

    .line 228
    goto :goto_3

    .line 215
    :pswitch_5
    iget-byte v4, p0, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->Pm:B

    check-cast v0, Ljava/lang/String;

    invoke-virtual {v0}, Ljava/lang/String;->getBytes()[B

    move-result-object v0

    array-length v0, v0

    add-int/2addr v0, v4

    add-int/2addr v0, v1

    .line 216
    goto :goto_4

    .line 218
    :pswitch_6
    iget-byte v4, p0, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->Pm:B

    check-cast v0, [B

    check-cast v0, [B

    array-length v0, v0

    add-int/2addr v0, v4

    add-int/2addr v0, v1

    .line 219
    goto :goto_4

    .line 221
    :pswitch_7
    iget-byte v4, p0, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->Pm:B

    check-cast v0, [S

    check-cast v0, [S

    array-length v0, v0

    mul-int/lit8 v0, v0, 0x2

    add-int/2addr v0, v4

    add-int/2addr v0, v1

    .line 222
    goto :goto_4

    .line 224
    :pswitch_8
    iget-byte v4, p0, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->Pm:B

    check-cast v0, [I

    check-cast v0, [I

    array-length v0, v0

    mul-int/lit8 v0, v0, 0x4

    add-int/2addr v0, v4

    add-int/2addr v0, v1

    .line 225
    goto :goto_4

    .line 227
    :pswitch_9
    iget-byte v4, p0, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->Pm:B

    check-cast v0, [J

    check-cast v0, [J

    array-length v0, v0

    mul-int/lit8 v0, v0, 0x8

    add-int/2addr v0, v4

    add-int/2addr v0, v1

    goto :goto_4

    .line 232
    :cond_3
    return v2

    :cond_4
    move v1, v2

    goto/16 :goto_2

    .line 177
    :pswitch_data_0
    .packed-switch 0x1
        :pswitch_0
        :pswitch_1
        :pswitch_2
        :pswitch_3
        :pswitch_4
    .end packed-switch

    .line 213
    :pswitch_data_1
    .packed-switch 0x1
        :pswitch_5
        :pswitch_6
        :pswitch_7
        :pswitch_8
        :pswitch_9
    .end packed-switch
.end method

.method static synthetic a(Lmiui/util/DirectIndexedFile$DataItemDescriptor;Ljava/io/DataOutput;)I
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 103
    invoke-direct {p0, p1}, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->a(Ljava/io/DataOutput;)I

    move-result v0

    return v0
.end method

.method static synthetic a(Lmiui/util/DirectIndexedFile$DataItemDescriptor;Ljava/io/DataOutput;Ljava/util/List;)I
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 103
    invoke-direct {p0, p1, p2}, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->b(Ljava/io/DataOutput;Ljava/util/List;)I

    move-result v0

    return v0
.end method

.method private static a(Ljava/io/DataInput;I)J
    .locals 3
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 464
    packed-switch p1, :pswitch_data_0

    .line 478
    :pswitch_0
    new-instance v0, Ljava/lang/IllegalArgumentException;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "Unsuppoert size "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 466
    :pswitch_1
    invoke-interface {p0}, Ljava/io/DataInput;->readByte()B

    move-result v0

    int-to-long v0, v0

    .line 480
    :goto_0
    return-wide v0

    .line 469
    :pswitch_2
    invoke-interface {p0}, Ljava/io/DataInput;->readShort()S

    move-result v0

    int-to-long v0, v0

    .line 470
    goto :goto_0

    .line 472
    :pswitch_3
    invoke-interface {p0}, Ljava/io/DataInput;->readInt()I

    move-result v0

    int-to-long v0, v0

    .line 473
    goto :goto_0

    .line 475
    :pswitch_4
    invoke-interface {p0}, Ljava/io/DataInput;->readLong()J

    move-result-wide v0

    goto :goto_0

    .line 464
    nop

    :pswitch_data_0
    .packed-switch 0x1
        :pswitch_1
        :pswitch_2
        :pswitch_0
        :pswitch_3
        :pswitch_0
        :pswitch_0
        :pswitch_0
        :pswitch_4
    .end packed-switch
.end method

.method static synthetic a(Lmiui/util/DirectIndexedFile$DataItemDescriptor;J)J
    .locals 0

    .prologue
    .line 103
    iput-wide p1, p0, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->fK:J

    return-wide p1
.end method

.method static synthetic a(Lmiui/util/DirectIndexedFile$DataItemDescriptor;Lmiui/util/DirectIndexedFile$c;I)Ljava/lang/Object;
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 103
    invoke-direct {p0, p1, p2}, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->a(Lmiui/util/DirectIndexedFile$c;I)Ljava/lang/Object;

    move-result-object v0

    return-object v0
.end method

.method private a(Lmiui/util/DirectIndexedFile$c;I)Ljava/lang/Object;
    .locals 7
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    const/4 v1, 0x0

    const/4 v0, 0x0

    .line 358
    .line 361
    invoke-interface {p1}, Lmiui/util/DirectIndexedFile$c;->getFilePointer()J

    move-result-wide v2

    .line 362
    if-eqz p2, :cond_0

    .line 363
    iget-byte v4, p0, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->Pn:B

    mul-int/2addr v4, p2

    int-to-long v4, v4

    add-long/2addr v4, v2

    invoke-interface {p1, v4, v5}, Lmiui/util/DirectIndexedFile$c;->seek(J)V

    .line 365
    :cond_0
    iget-byte v4, p0, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->Pn:B

    invoke-static {p1, v4}, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->a(Ljava/io/DataInput;I)J

    move-result-wide v4

    add-long/2addr v2, v4

    invoke-interface {p1, v2, v3}, Lmiui/util/DirectIndexedFile$c;->seek(J)V

    .line 367
    sget-object v2, Lmiui/util/DirectIndexedFile$1;->FF:[I

    iget-object v3, p0, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->Pk:Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;

    invoke-virtual {v3}, Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;->ordinal()I

    move-result v3

    aget v2, v2, v3

    packed-switch v2, :pswitch_data_0

    move-object v0, v1

    .line 406
    :goto_0
    invoke-static {v0}, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->d([B)V

    .line 407
    return-object v1

    .line 369
    :pswitch_0
    iget-byte v1, p0, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->Pm:B

    invoke-static {p1, v1}, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->a(Ljava/io/DataInput;I)J

    move-result-wide v1

    long-to-int v3, v1

    .line 370
    invoke-static {v3}, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->ao(I)[B

    move-result-object v1

    .line 371
    invoke-interface {p1, v1, v0, v3}, Lmiui/util/DirectIndexedFile$c;->readFully([BII)V

    .line 372
    new-instance v2, Ljava/lang/String;

    invoke-direct {v2, v1, v0, v3}, Ljava/lang/String;-><init>([BII)V

    move-object v0, v1

    move-object v1, v2

    .line 373
    goto :goto_0

    .line 376
    :pswitch_1
    iget-byte v0, p0, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->Pm:B

    invoke-static {p1, v0}, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->a(Ljava/io/DataInput;I)J

    move-result-wide v2

    long-to-int v0, v2

    new-array v0, v0, [B

    .line 377
    invoke-interface {p1, v0}, Lmiui/util/DirectIndexedFile$c;->readFully([B)V

    move-object v6, v1

    move-object v1, v0

    move-object v0, v6

    .line 380
    goto :goto_0

    .line 382
    :pswitch_2
    iget-byte v2, p0, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->Pm:B

    invoke-static {p1, v2}, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->a(Ljava/io/DataInput;I)J

    move-result-wide v2

    long-to-int v2, v2

    new-array v2, v2, [S

    .line 384
    :goto_1
    array-length v3, v2

    if-ge v0, v3, :cond_1

    .line 385
    invoke-interface {p1}, Lmiui/util/DirectIndexedFile$c;->readShort()S

    move-result v3

    aput-short v3, v2, v0

    .line 384
    add-int/lit8 v0, v0, 0x1

    goto :goto_1

    :cond_1
    move-object v0, v1

    move-object v1, v2

    .line 387
    goto :goto_0

    .line 390
    :pswitch_3
    iget-byte v2, p0, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->Pm:B

    invoke-static {p1, v2}, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->a(Ljava/io/DataInput;I)J

    move-result-wide v2

    long-to-int v2, v2

    new-array v2, v2, [I

    .line 392
    :goto_2
    array-length v3, v2

    if-ge v0, v3, :cond_2

    .line 393
    invoke-interface {p1}, Lmiui/util/DirectIndexedFile$c;->readInt()I

    move-result v3

    aput v3, v2, v0

    .line 392
    add-int/lit8 v0, v0, 0x1

    goto :goto_2

    :cond_2
    move-object v0, v1

    move-object v1, v2

    .line 395
    goto :goto_0

    .line 398
    :pswitch_4
    iget-byte v2, p0, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->Pm:B

    invoke-static {p1, v2}, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->a(Ljava/io/DataInput;I)J

    move-result-wide v2

    long-to-int v2, v2

    new-array v2, v2, [J

    .line 400
    :goto_3
    array-length v3, v2

    if-ge v0, v3, :cond_3

    .line 401
    invoke-interface {p1}, Lmiui/util/DirectIndexedFile$c;->readLong()J

    move-result-wide v3

    aput-wide v3, v2, v0

    .line 400
    add-int/lit8 v0, v0, 0x1

    goto :goto_3

    :cond_3
    move-object v0, v1

    move-object v1, v2

    .line 403
    goto :goto_0

    .line 367
    :pswitch_data_0
    .packed-switch 0x1
        :pswitch_0
        :pswitch_1
        :pswitch_2
        :pswitch_3
        :pswitch_4
    .end packed-switch
.end method

.method private static a(Ljava/io/DataOutput;IJ)V
    .locals 3
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 444
    packed-switch p1, :pswitch_data_0

    .line 458
    :pswitch_0
    new-instance v0, Ljava/lang/IllegalArgumentException;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "Unsuppoert size "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 446
    :pswitch_1
    long-to-int v0, p2

    invoke-interface {p0, v0}, Ljava/io/DataOutput;->writeByte(I)V

    .line 460
    :goto_0
    return-void

    .line 449
    :pswitch_2
    long-to-int v0, p2

    invoke-interface {p0, v0}, Ljava/io/DataOutput;->writeShort(I)V

    goto :goto_0

    .line 452
    :pswitch_3
    long-to-int v0, p2

    invoke-interface {p0, v0}, Ljava/io/DataOutput;->writeInt(I)V

    goto :goto_0

    .line 455
    :pswitch_4
    invoke-interface {p0, p2, p3}, Ljava/io/DataOutput;->writeLong(J)V

    goto :goto_0

    .line 444
    nop

    :pswitch_data_0
    .packed-switch 0x1
        :pswitch_1
        :pswitch_2
        :pswitch_0
        :pswitch_3
        :pswitch_0
        :pswitch_0
        :pswitch_0
        :pswitch_4
    .end packed-switch
.end method

.method static synthetic a(Lmiui/util/DirectIndexedFile$DataItemDescriptor;Lmiui/util/DirectIndexedFile$c;)[Ljava/lang/Object;
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 103
    invoke-direct {p0, p1}, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->a(Lmiui/util/DirectIndexedFile$c;)[Ljava/lang/Object;

    move-result-object v0

    return-object v0
.end method

.method private a(Lmiui/util/DirectIndexedFile$c;)[Ljava/lang/Object;
    .locals 5
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    const/4 v4, 0x1

    const/4 v3, 0x0

    .line 411
    const/4 v0, 0x0

    .line 412
    sget-object v1, Lmiui/util/DirectIndexedFile$1;->FF:[I

    iget-object v2, p0, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->Pk:Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;

    invoke-virtual {v2}, Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;->ordinal()I

    move-result v2

    aget v1, v1, v2

    packed-switch v1, :pswitch_data_0

    .line 440
    :goto_0
    return-object v0

    .line 414
    :pswitch_0
    new-array v0, v4, [Ljava/lang/Object;

    .line 415
    invoke-interface {p1}, Lmiui/util/DirectIndexedFile$c;->readByte()B

    move-result v1

    invoke-static {v1}, Ljava/lang/Byte;->valueOf(B)Ljava/lang/Byte;

    move-result-object v1

    aput-object v1, v0, v3

    goto :goto_0

    .line 418
    :pswitch_1
    new-array v0, v4, [Ljava/lang/Object;

    .line 419
    invoke-interface {p1}, Lmiui/util/DirectIndexedFile$c;->readShort()S

    move-result v1

    invoke-static {v1}, Ljava/lang/Short;->valueOf(S)Ljava/lang/Short;

    move-result-object v1

    aput-object v1, v0, v3

    goto :goto_0

    .line 422
    :pswitch_2
    new-array v0, v4, [Ljava/lang/Object;

    .line 423
    invoke-interface {p1}, Lmiui/util/DirectIndexedFile$c;->readInt()I

    move-result v1

    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    aput-object v1, v0, v3

    goto :goto_0

    .line 426
    :pswitch_3
    new-array v0, v4, [Ljava/lang/Object;

    .line 427
    invoke-interface {p1}, Lmiui/util/DirectIndexedFile$c;->readLong()J

    move-result-wide v1

    invoke-static {v1, v2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v1

    aput-object v1, v0, v3

    goto :goto_0

    .line 434
    :pswitch_4
    invoke-interface {p1}, Lmiui/util/DirectIndexedFile$c;->readInt()I

    move-result v0

    new-array v0, v0, [Ljava/lang/Object;

    .line 435
    invoke-direct {p0, p1, v3}, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->a(Lmiui/util/DirectIndexedFile$c;I)Ljava/lang/Object;

    move-result-object v1

    aput-object v1, v0, v3

    goto :goto_0

    .line 412
    :pswitch_data_0
    .packed-switch 0x1
        :pswitch_4
        :pswitch_4
        :pswitch_4
        :pswitch_4
        :pswitch_4
        :pswitch_0
        :pswitch_1
        :pswitch_2
        :pswitch_3
    .end packed-switch
.end method

.method private static ao(I)[B
    .locals 3

    .prologue
    .line 125
    const-class v1, Lmiui/util/DirectIndexedFile$DataItemDescriptor;

    monitor-enter v1

    .line 126
    :try_start_0
    sget-object v0, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->Pj:[B

    if-eqz v0, :cond_0

    sget-object v0, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->Pj:[B

    array-length v0, v0

    if-ge v0, p0, :cond_1

    .line 127
    :cond_0
    new-array v0, p0, [B

    sput-object v0, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->Pj:[B

    .line 129
    :cond_1
    sget-object v0, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->Pj:[B

    .line 130
    const/4 v2, 0x0

    sput-object v2, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->Pj:[B

    .line 131
    monitor-exit v1

    return-object v0

    .line 132
    :catchall_0
    move-exception v0

    monitor-exit v1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw v0
.end method

.method private static ap(I)B
    .locals 9

    .prologue
    const/4 v0, 0x4

    const/16 v1, 0x8

    .line 484
    const/4 v4, 0x0

    .line 485
    mul-int/lit8 v2, p0, 0x2

    int-to-long v2, v2

    move-wide v7, v2

    move v2, v4

    move-wide v3, v7

    :goto_0
    const-wide/16 v5, 0x0

    cmp-long v5, v3, v5

    if-lez v5, :cond_0

    .line 486
    add-int/lit8 v2, v2, 0x1

    int-to-byte v5, v2

    .line 485
    shr-long v2, v3, v1

    move-wide v7, v2

    move-wide v3, v7

    move v2, v5

    goto :goto_0

    .line 489
    :cond_0
    const/4 v3, 0x3

    if-ne v2, v3, :cond_1

    .line 495
    :goto_1
    return v0

    .line 491
    :cond_1
    if-le v2, v0, :cond_2

    if-ge v2, v1, :cond_2

    move v0, v1

    .line 492
    goto :goto_1

    :cond_2
    move v0, v2

    goto :goto_1
.end method

.method static synthetic aq(I)B
    .locals 1

    .prologue
    .line 103
    invoke-static {p0}, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->ap(I)B

    move-result v0

    return v0
.end method

.method private b(Ljava/io/DataOutput;Ljava/util/List;)I
    .locals 8
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/io/DataOutput;",
            "Ljava/util/List",
            "<",
            "Ljava/lang/Object;",
            ">;)I"
        }
    .end annotation

    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    const/4 v2, 0x0

    .line 236
    .line 237
    sget-object v0, Lmiui/util/DirectIndexedFile$1;->FF:[I

    iget-object v1, p0, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->Pk:Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;

    invoke-virtual {v1}, Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;->ordinal()I

    move-result v1

    aget v0, v0, v1

    packed-switch v0, :pswitch_data_0

    move v1, v2

    .line 354
    :cond_0
    :goto_0
    return v1

    .line 239
    :pswitch_0
    if-eqz p1, :cond_1

    .line 240
    invoke-interface {p2, v2}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/Byte;

    invoke-virtual {v0}, Ljava/lang/Byte;->byteValue()B

    move-result v0

    invoke-interface {p1, v0}, Ljava/io/DataOutput;->writeByte(I)V

    .line 242
    :cond_1
    const/4 v1, 0x1

    .line 243
    goto :goto_0

    .line 245
    :pswitch_1
    if-eqz p1, :cond_2

    .line 246
    invoke-interface {p2, v2}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/Short;

    invoke-virtual {v0}, Ljava/lang/Short;->shortValue()S

    move-result v0

    invoke-interface {p1, v0}, Ljava/io/DataOutput;->writeShort(I)V

    .line 248
    :cond_2
    const/4 v1, 0x2

    .line 249
    goto :goto_0

    .line 251
    :pswitch_2
    if-eqz p1, :cond_3

    .line 252
    invoke-interface {p2, v2}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/Integer;

    invoke-virtual {v0}, Ljava/lang/Integer;->intValue()I

    move-result v0

    invoke-interface {p1, v0}, Ljava/io/DataOutput;->writeInt(I)V

    .line 254
    :cond_3
    const/4 v1, 0x4

    .line 255
    goto :goto_0

    .line 257
    :pswitch_3
    if-eqz p1, :cond_4

    .line 258
    invoke-interface {p2, v2}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/Long;

    invoke-virtual {v0}, Ljava/lang/Long;->longValue()J

    move-result-wide v0

    invoke-interface {p1, v0, v1}, Ljava/io/DataOutput;->writeLong(J)V

    .line 260
    :cond_4
    const/16 v1, 0x8

    .line 261
    goto :goto_0

    .line 263
    :pswitch_4
    if-eqz p1, :cond_5

    .line 264
    invoke-interface {p2}, Ljava/util/List;->size()I

    move-result v0

    invoke-interface {p1, v0}, Ljava/io/DataOutput;->writeInt(I)V

    .line 267
    :cond_5
    invoke-direct {p0, p1, p2}, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->a(Ljava/io/DataOutput;Ljava/util/List;)I

    move-result v0

    add-int/lit8 v0, v0, 0x4

    .line 269
    invoke-interface {p2}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v3

    move v1, v0

    :goto_1
    invoke-interface {v3}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_0

    invoke-interface {v3}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    .line 270
    check-cast v0, Ljava/lang/String;

    .line 271
    invoke-virtual {v0}, Ljava/lang/String;->getBytes()[B

    move-result-object v4

    .line 272
    if-eqz p1, :cond_6

    .line 273
    iget-byte v0, p0, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->Pm:B

    array-length v5, v4

    int-to-long v5, v5

    invoke-static {p1, v0, v5, v6}, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->a(Ljava/io/DataOutput;IJ)V

    .line 274
    array-length v5, v4

    move v0, v2

    :goto_2
    if-ge v0, v5, :cond_6

    aget-byte v6, v4, v0

    .line 275
    invoke-interface {p1, v6}, Ljava/io/DataOutput;->writeByte(I)V

    .line 274
    add-int/lit8 v0, v0, 0x1

    goto :goto_2

    .line 278
    :cond_6
    iget-byte v0, p0, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->Pm:B

    array-length v4, v4

    add-int/2addr v0, v4

    add-int/2addr v0, v1

    move v1, v0

    .line 279
    goto :goto_1

    .line 282
    :pswitch_5
    if-eqz p1, :cond_7

    .line 283
    invoke-interface {p2}, Ljava/util/List;->size()I

    move-result v0

    invoke-interface {p1, v0}, Ljava/io/DataOutput;->writeInt(I)V

    .line 286
    :cond_7
    invoke-direct {p0, p1, p2}, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->a(Ljava/io/DataOutput;Ljava/util/List;)I

    move-result v0

    add-int/lit8 v0, v0, 0x4

    .line 288
    invoke-interface {p2}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v2

    move v1, v0

    :goto_3
    invoke-interface {v2}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_0

    invoke-interface {v2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    .line 289
    check-cast v0, [B

    check-cast v0, [B

    .line 290
    if-eqz p1, :cond_8

    .line 291
    iget-byte v3, p0, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->Pm:B

    array-length v4, v0

    int-to-long v4, v4

    invoke-static {p1, v3, v4, v5}, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->a(Ljava/io/DataOutput;IJ)V

    .line 292
    invoke-interface {p1, v0}, Ljava/io/DataOutput;->write([B)V

    .line 294
    :cond_8
    iget-byte v3, p0, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->Pm:B

    array-length v0, v0

    add-int/2addr v0, v3

    add-int/2addr v0, v1

    move v1, v0

    .line 295
    goto :goto_3

    .line 298
    :pswitch_6
    if-eqz p1, :cond_9

    .line 299
    invoke-interface {p2}, Ljava/util/List;->size()I

    move-result v0

    invoke-interface {p1, v0}, Ljava/io/DataOutput;->writeInt(I)V

    .line 302
    :cond_9
    invoke-direct {p0, p1, p2}, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->a(Ljava/io/DataOutput;Ljava/util/List;)I

    move-result v0

    add-int/lit8 v0, v0, 0x4

    .line 304
    invoke-interface {p2}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v4

    move v1, v0

    :goto_4
    invoke-interface {v4}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_0

    invoke-interface {v4}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    .line 305
    check-cast v0, [S

    check-cast v0, [S

    .line 306
    if-eqz p1, :cond_a

    .line 307
    iget-byte v3, p0, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->Pm:B

    array-length v5, v0

    int-to-long v5, v5

    invoke-static {p1, v3, v5, v6}, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->a(Ljava/io/DataOutput;IJ)V

    .line 308
    array-length v5, v0

    move v3, v2

    :goto_5
    if-ge v3, v5, :cond_a

    aget-short v6, v0, v3

    .line 309
    invoke-interface {p1, v6}, Ljava/io/DataOutput;->writeShort(I)V

    .line 308
    add-int/lit8 v3, v3, 0x1

    goto :goto_5

    .line 312
    :cond_a
    iget-byte v3, p0, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->Pm:B

    array-length v0, v0

    mul-int/lit8 v0, v0, 0x2

    add-int/2addr v0, v3

    add-int/2addr v0, v1

    move v1, v0

    .line 313
    goto :goto_4

    .line 316
    :pswitch_7
    if-eqz p1, :cond_b

    .line 317
    invoke-interface {p2}, Ljava/util/List;->size()I

    move-result v0

    invoke-interface {p1, v0}, Ljava/io/DataOutput;->writeInt(I)V

    .line 320
    :cond_b
    invoke-direct {p0, p1, p2}, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->a(Ljava/io/DataOutput;Ljava/util/List;)I

    move-result v0

    add-int/lit8 v0, v0, 0x4

    .line 322
    invoke-interface {p2}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v4

    move v1, v0

    :goto_6
    invoke-interface {v4}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_0

    invoke-interface {v4}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    .line 323
    check-cast v0, [I

    check-cast v0, [I

    .line 324
    if-eqz p1, :cond_c

    .line 325
    iget-byte v3, p0, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->Pm:B

    array-length v5, v0

    int-to-long v5, v5

    invoke-static {p1, v3, v5, v6}, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->a(Ljava/io/DataOutput;IJ)V

    .line 326
    array-length v5, v0

    move v3, v2

    :goto_7
    if-ge v3, v5, :cond_c

    aget v6, v0, v3

    .line 327
    invoke-interface {p1, v6}, Ljava/io/DataOutput;->writeInt(I)V

    .line 326
    add-int/lit8 v3, v3, 0x1

    goto :goto_7

    .line 330
    :cond_c
    iget-byte v3, p0, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->Pm:B

    array-length v0, v0

    mul-int/lit8 v0, v0, 0x4

    add-int/2addr v0, v3

    add-int/2addr v0, v1

    move v1, v0

    .line 331
    goto :goto_6

    .line 334
    :pswitch_8
    if-eqz p1, :cond_d

    .line 335
    invoke-interface {p2}, Ljava/util/List;->size()I

    move-result v0

    invoke-interface {p1, v0}, Ljava/io/DataOutput;->writeInt(I)V

    .line 338
    :cond_d
    invoke-direct {p0, p1, p2}, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->a(Ljava/io/DataOutput;Ljava/util/List;)I

    move-result v0

    add-int/lit8 v0, v0, 0x4

    .line 340
    invoke-interface {p2}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v4

    move v1, v0

    :goto_8
    invoke-interface {v4}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_0

    invoke-interface {v4}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    .line 341
    check-cast v0, [J

    check-cast v0, [J

    .line 342
    if-eqz p1, :cond_e

    .line 343
    iget-byte v3, p0, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->Pm:B

    array-length v5, v0

    int-to-long v5, v5

    invoke-static {p1, v3, v5, v6}, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->a(Ljava/io/DataOutput;IJ)V

    .line 344
    array-length v5, v0

    move v3, v2

    :goto_9
    if-ge v3, v5, :cond_e

    aget-wide v6, v0, v3

    .line 345
    invoke-interface {p1, v6, v7}, Ljava/io/DataOutput;->writeLong(J)V

    .line 344
    add-int/lit8 v3, v3, 0x1

    goto :goto_9

    .line 348
    :cond_e
    iget-byte v3, p0, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->Pm:B

    array-length v0, v0

    mul-int/lit8 v0, v0, 0x8

    add-int/2addr v0, v3

    add-int/2addr v0, v1

    move v1, v0

    .line 349
    goto :goto_8

    .line 237
    :pswitch_data_0
    .packed-switch 0x1
        :pswitch_4
        :pswitch_5
        :pswitch_6
        :pswitch_7
        :pswitch_8
        :pswitch_0
        :pswitch_1
        :pswitch_2
        :pswitch_3
    .end packed-switch
.end method

.method static synthetic b(Ljava/io/DataInput;I)J
    .locals 2
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 103
    invoke-static {p0, p1}, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->a(Ljava/io/DataInput;I)J

    move-result-wide v0

    return-wide v0
.end method

.method static synthetic b(Lmiui/util/DirectIndexedFile$DataItemDescriptor;)J
    .locals 2

    .prologue
    .line 103
    iget-wide v0, p0, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->fK:J

    return-wide v0
.end method

.method static synthetic c(Lmiui/util/DirectIndexedFile$DataItemDescriptor;)Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;
    .locals 1

    .prologue
    .line 103
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->Pk:Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;

    return-object v0
.end method

.method private static d([B)V
    .locals 3

    .prologue
    .line 136
    const-class v1, Lmiui/util/DirectIndexedFile$DataItemDescriptor;

    monitor-enter v1

    .line 137
    if-eqz p0, :cond_1

    :try_start_0
    sget-object v0, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->Pj:[B

    if-eqz v0, :cond_0

    sget-object v0, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->Pj:[B

    array-length v0, v0

    array-length v2, p0

    if-ge v0, v2, :cond_1

    .line 138
    :cond_0
    sput-object p0, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->Pj:[B

    .line 140
    :cond_1
    monitor-exit v1

    .line 141
    return-void

    .line 140
    :catchall_0
    move-exception v0

    monitor-exit v1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw v0
.end method

.method private static g(Ljava/io/DataInput;)Lmiui/util/DirectIndexedFile$DataItemDescriptor;
    .locals 7
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 152
    invoke-static {}, Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;->values()[Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;

    move-result-object v0

    invoke-interface {p0}, Ljava/io/DataInput;->readByte()B

    move-result v1

    aget-object v1, v0, v1

    .line 153
    invoke-interface {p0}, Ljava/io/DataInput;->readByte()B

    move-result v2

    .line 154
    invoke-interface {p0}, Ljava/io/DataInput;->readByte()B

    move-result v3

    .line 155
    invoke-interface {p0}, Ljava/io/DataInput;->readByte()B

    move-result v4

    .line 156
    invoke-interface {p0}, Ljava/io/DataInput;->readLong()J

    move-result-wide v5

    .line 157
    new-instance v0, Lmiui/util/DirectIndexedFile$DataItemDescriptor;

    invoke-direct/range {v0 .. v6}, Lmiui/util/DirectIndexedFile$DataItemDescriptor;-><init>(Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;BBBJ)V

    return-object v0
.end method

.method static synthetic h(Ljava/io/DataInput;)Lmiui/util/DirectIndexedFile$DataItemDescriptor;
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 103
    invoke-static {p0}, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->g(Ljava/io/DataInput;)Lmiui/util/DirectIndexedFile$DataItemDescriptor;

    move-result-object v0

    return-object v0
.end method
