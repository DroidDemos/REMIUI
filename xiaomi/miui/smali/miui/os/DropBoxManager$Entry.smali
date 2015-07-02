.class public Lmiui/os/DropBoxManager$Entry;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroid/os/Parcelable;
.implements Ljava/io/Closeable;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/os/DropBoxManager;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x9
    name = "Entry"
.end annotation


# static fields
.field public static final CREATOR:Landroid/os/Parcelable$Creator;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Landroid/os/Parcelable$Creator",
            "<",
            "Lmiui/os/DropBoxManager$Entry;",
            ">;"
        }
    .end annotation
.end field


# instance fields
.field private final aV:Ljava/lang/String;

.field private final aW:J

.field private final aX:[B

.field private final aY:Landroid/os/ParcelFileDescriptor;

.field private final aZ:I


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .prologue
    .line 236
    new-instance v0, Lmiui/os/b;

    invoke-direct {v0}, Lmiui/os/b;-><init>()V

    sput-object v0, Lmiui/os/DropBoxManager$Entry;->CREATOR:Landroid/os/Parcelable$Creator;

    return-void
.end method

.method public constructor <init>(Ljava/lang/String;J)V
    .locals 2

    .prologue
    const/4 v0, 0x0

    .line 112
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 113
    if-nez p1, :cond_0

    new-instance v0, Ljava/lang/NullPointerException;

    const-string v1, "tag == null"

    invoke-direct {v0, v1}, Ljava/lang/NullPointerException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 115
    :cond_0
    iput-object p1, p0, Lmiui/os/DropBoxManager$Entry;->aV:Ljava/lang/String;

    .line 116
    iput-wide p2, p0, Lmiui/os/DropBoxManager$Entry;->aW:J

    .line 117
    iput-object v0, p0, Lmiui/os/DropBoxManager$Entry;->aX:[B

    .line 118
    iput-object v0, p0, Lmiui/os/DropBoxManager$Entry;->aY:Landroid/os/ParcelFileDescriptor;

    .line 119
    const/4 v0, 0x1

    iput v0, p0, Lmiui/os/DropBoxManager$Entry;->aZ:I

    .line 120
    return-void
.end method

.method public constructor <init>(Ljava/lang/String;JLandroid/os/ParcelFileDescriptor;I)V
    .locals 3

    .prologue
    const/4 v0, 0x1

    const/4 v1, 0x0

    .line 155
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 156
    if-nez p1, :cond_0

    new-instance v0, Ljava/lang/NullPointerException;

    const-string v1, "tag == null"

    invoke-direct {v0, v1}, Ljava/lang/NullPointerException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 157
    :cond_0
    and-int/lit8 v2, p5, 0x1

    if-eqz v2, :cond_1

    move v2, v0

    :goto_0
    if-nez p4, :cond_2

    :goto_1
    if-eq v2, v0, :cond_3

    .line 158
    new-instance v0, Ljava/lang/IllegalArgumentException;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "Bad flags: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p5}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    :cond_1
    move v2, v1

    .line 157
    goto :goto_0

    :cond_2
    move v0, v1

    goto :goto_1

    .line 161
    :cond_3
    iput-object p1, p0, Lmiui/os/DropBoxManager$Entry;->aV:Ljava/lang/String;

    .line 162
    iput-wide p2, p0, Lmiui/os/DropBoxManager$Entry;->aW:J

    .line 163
    const/4 v0, 0x0

    iput-object v0, p0, Lmiui/os/DropBoxManager$Entry;->aX:[B

    .line 164
    iput-object p4, p0, Lmiui/os/DropBoxManager$Entry;->aY:Landroid/os/ParcelFileDescriptor;

    .line 165
    iput p5, p0, Lmiui/os/DropBoxManager$Entry;->aZ:I

    .line 166
    return-void
.end method

.method public constructor <init>(Ljava/lang/String;JLjava/io/File;I)V
    .locals 3
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 172
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 173
    if-nez p1, :cond_0

    new-instance v0, Ljava/lang/NullPointerException;

    const-string v1, "tag == null"

    invoke-direct {v0, v1}, Ljava/lang/NullPointerException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 174
    :cond_0
    and-int/lit8 v0, p5, 0x1

    if-eqz v0, :cond_1

    new-instance v0, Ljava/lang/IllegalArgumentException;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "Bad flags: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p5}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 176
    :cond_1
    iput-object p1, p0, Lmiui/os/DropBoxManager$Entry;->aV:Ljava/lang/String;

    .line 177
    iput-wide p2, p0, Lmiui/os/DropBoxManager$Entry;->aW:J

    .line 178
    const/4 v0, 0x0

    iput-object v0, p0, Lmiui/os/DropBoxManager$Entry;->aX:[B

    .line 179
    const/high16 v0, 0x10000000

    invoke-static {p4, v0}, Landroid/os/ParcelFileDescriptor;->open(Ljava/io/File;I)Landroid/os/ParcelFileDescriptor;

    move-result-object v0

    iput-object v0, p0, Lmiui/os/DropBoxManager$Entry;->aY:Landroid/os/ParcelFileDescriptor;

    .line 180
    iput p5, p0, Lmiui/os/DropBoxManager$Entry;->aZ:I

    .line 181
    return-void
.end method

.method public constructor <init>(Ljava/lang/String;JLjava/lang/String;)V
    .locals 2

    .prologue
    .line 123
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 124
    if-nez p1, :cond_0

    new-instance v0, Ljava/lang/NullPointerException;

    const-string v1, "tag == null"

    invoke-direct {v0, v1}, Ljava/lang/NullPointerException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 125
    :cond_0
    if-nez p4, :cond_1

    new-instance v0, Ljava/lang/NullPointerException;

    const-string v1, "text == null"

    invoke-direct {v0, v1}, Ljava/lang/NullPointerException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 127
    :cond_1
    iput-object p1, p0, Lmiui/os/DropBoxManager$Entry;->aV:Ljava/lang/String;

    .line 128
    iput-wide p2, p0, Lmiui/os/DropBoxManager$Entry;->aW:J

    .line 129
    invoke-virtual {p4}, Ljava/lang/String;->getBytes()[B

    move-result-object v0

    iput-object v0, p0, Lmiui/os/DropBoxManager$Entry;->aX:[B

    .line 130
    const/4 v0, 0x0

    iput-object v0, p0, Lmiui/os/DropBoxManager$Entry;->aY:Landroid/os/ParcelFileDescriptor;

    .line 131
    const/4 v0, 0x2

    iput v0, p0, Lmiui/os/DropBoxManager$Entry;->aZ:I

    .line 132
    return-void
.end method

.method public constructor <init>(Ljava/lang/String;J[BI)V
    .locals 3

    .prologue
    const/4 v0, 0x1

    const/4 v1, 0x0

    .line 138
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 139
    if-nez p1, :cond_0

    new-instance v0, Ljava/lang/NullPointerException;

    const-string v1, "tag == null"

    invoke-direct {v0, v1}, Ljava/lang/NullPointerException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 140
    :cond_0
    and-int/lit8 v2, p5, 0x1

    if-eqz v2, :cond_1

    move v2, v0

    :goto_0
    if-nez p4, :cond_2

    :goto_1
    if-eq v2, v0, :cond_3

    .line 141
    new-instance v0, Ljava/lang/IllegalArgumentException;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "Bad flags: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p5}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    :cond_1
    move v2, v1

    .line 140
    goto :goto_0

    :cond_2
    move v0, v1

    goto :goto_1

    .line 144
    :cond_3
    iput-object p1, p0, Lmiui/os/DropBoxManager$Entry;->aV:Ljava/lang/String;

    .line 145
    iput-wide p2, p0, Lmiui/os/DropBoxManager$Entry;->aW:J

    .line 146
    iput-object p4, p0, Lmiui/os/DropBoxManager$Entry;->aX:[B

    .line 147
    const/4 v0, 0x0

    iput-object v0, p0, Lmiui/os/DropBoxManager$Entry;->aY:Landroid/os/ParcelFileDescriptor;

    .line 148
    iput p5, p0, Lmiui/os/DropBoxManager$Entry;->aZ:I

    .line 149
    return-void
.end method


# virtual methods
.method public close()V
    .locals 1

    .prologue
    .line 185
    :try_start_0
    iget-object v0, p0, Lmiui/os/DropBoxManager$Entry;->aY:Landroid/os/ParcelFileDescriptor;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lmiui/os/DropBoxManager$Entry;->aY:Landroid/os/ParcelFileDescriptor;

    invoke-virtual {v0}, Landroid/os/ParcelFileDescriptor;->close()V
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_0

    .line 186
    :cond_0
    :goto_0
    return-void

    .line 185
    :catch_0
    move-exception v0

    goto :goto_0
.end method

.method public describeContents()I
    .locals 1

    .prologue
    .line 251
    iget-object v0, p0, Lmiui/os/DropBoxManager$Entry;->aY:Landroid/os/ParcelFileDescriptor;

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public getFlags()I
    .locals 1

    .prologue
    .line 195
    iget v0, p0, Lmiui/os/DropBoxManager$Entry;->aZ:I

    and-int/lit8 v0, v0, -0x5

    return v0
.end method

.method public getInputStream()Ljava/io/InputStream;
    .locals 2
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 226
    iget-object v0, p0, Lmiui/os/DropBoxManager$Entry;->aX:[B

    if-eqz v0, :cond_1

    .line 227
    new-instance v0, Ljava/io/ByteArrayInputStream;

    iget-object v1, p0, Lmiui/os/DropBoxManager$Entry;->aX:[B

    invoke-direct {v0, v1}, Ljava/io/ByteArrayInputStream;-><init>([B)V

    .line 233
    :goto_0
    iget v1, p0, Lmiui/os/DropBoxManager$Entry;->aZ:I

    and-int/lit8 v1, v1, 0x4

    if-eqz v1, :cond_0

    new-instance v1, Ljava/util/zip/GZIPInputStream;

    invoke-direct {v1, v0}, Ljava/util/zip/GZIPInputStream;-><init>(Ljava/io/InputStream;)V

    move-object v0, v1

    :cond_0
    :goto_1
    return-object v0

    .line 228
    :cond_1
    iget-object v0, p0, Lmiui/os/DropBoxManager$Entry;->aY:Landroid/os/ParcelFileDescriptor;

    if-eqz v0, :cond_2

    .line 229
    new-instance v0, Landroid/os/ParcelFileDescriptor$AutoCloseInputStream;

    iget-object v1, p0, Lmiui/os/DropBoxManager$Entry;->aY:Landroid/os/ParcelFileDescriptor;

    invoke-direct {v0, v1}, Landroid/os/ParcelFileDescriptor$AutoCloseInputStream;-><init>(Landroid/os/ParcelFileDescriptor;)V

    goto :goto_0

    .line 231
    :cond_2
    const/4 v0, 0x0

    goto :goto_1
.end method

.method public getTag()Ljava/lang/String;
    .locals 1

    .prologue
    .line 189
    iget-object v0, p0, Lmiui/os/DropBoxManager$Entry;->aV:Ljava/lang/String;

    return-object v0
.end method

.method public getText(I)Ljava/lang/String;
    .locals 6

    .prologue
    const/4 v0, 0x0

    const/4 v1, 0x0

    .line 202
    iget v2, p0, Lmiui/os/DropBoxManager$Entry;->aZ:I

    and-int/lit8 v2, v2, 0x2

    if-nez v2, :cond_1

    .line 219
    :cond_0
    :goto_0
    return-object v0

    .line 203
    :cond_1
    iget-object v2, p0, Lmiui/os/DropBoxManager$Entry;->aX:[B

    if-eqz v2, :cond_2

    new-instance v0, Ljava/lang/String;

    iget-object v2, p0, Lmiui/os/DropBoxManager$Entry;->aX:[B

    iget-object v3, p0, Lmiui/os/DropBoxManager$Entry;->aX:[B

    array-length v3, v3

    invoke-static {p1, v3}, Ljava/lang/Math;->min(II)I

    move-result v3

    invoke-direct {v0, v2, v1, v3}, Ljava/lang/String;-><init>([BII)V

    goto :goto_0

    .line 207
    :cond_2
    :try_start_0
    invoke-virtual {p0}, Lmiui/os/DropBoxManager$Entry;->getInputStream()Ljava/io/InputStream;
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_1
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    move-result-object v2

    .line 208
    if-nez v2, :cond_3

    .line 219
    if-eqz v2, :cond_0

    :try_start_1
    invoke-virtual {v2}, Ljava/io/InputStream;->close()V
    :try_end_1
    .catch Ljava/io/IOException; {:try_start_1 .. :try_end_1} :catch_0

    goto :goto_0

    :catch_0
    move-exception v1

    goto :goto_0

    .line 209
    :cond_3
    :try_start_2
    new-array v4, p1, [B

    move v3, v1

    .line 212
    :goto_1
    if-ltz v3, :cond_4

    add-int/2addr v1, v3

    if-ge v1, p1, :cond_4

    .line 213
    sub-int v3, p1, v1

    invoke-virtual {v2, v4, v1, v3}, Ljava/io/InputStream;->read([BII)I

    move-result v3

    goto :goto_1

    :cond_4
    move v3, v1

    .line 215
    new-instance v1, Ljava/lang/String;

    const/4 v5, 0x0

    invoke-direct {v1, v4, v5, v3}, Ljava/lang/String;-><init>([BII)V
    :try_end_2
    .catch Ljava/io/IOException; {:try_start_2 .. :try_end_2} :catch_5
    .catchall {:try_start_2 .. :try_end_2} :catchall_1

    .line 219
    if-eqz v2, :cond_5

    :try_start_3
    invoke-virtual {v2}, Ljava/io/InputStream;->close()V
    :try_end_3
    .catch Ljava/io/IOException; {:try_start_3 .. :try_end_3} :catch_3

    :cond_5
    :goto_2
    move-object v0, v1

    goto :goto_0

    .line 216
    :catch_1
    move-exception v1

    move-object v1, v0

    .line 219
    :goto_3
    if-eqz v1, :cond_0

    :try_start_4
    invoke-virtual {v1}, Ljava/io/InputStream;->close()V
    :try_end_4
    .catch Ljava/io/IOException; {:try_start_4 .. :try_end_4} :catch_2

    goto :goto_0

    :catch_2
    move-exception v1

    goto :goto_0

    :catchall_0
    move-exception v1

    move-object v2, v0

    move-object v0, v1

    :goto_4
    if-eqz v2, :cond_6

    :try_start_5
    invoke-virtual {v2}, Ljava/io/InputStream;->close()V
    :try_end_5
    .catch Ljava/io/IOException; {:try_start_5 .. :try_end_5} :catch_4

    :cond_6
    :goto_5
    throw v0

    :catch_3
    move-exception v0

    goto :goto_2

    :catch_4
    move-exception v1

    goto :goto_5

    :catchall_1
    move-exception v0

    goto :goto_4

    .line 216
    :catch_5
    move-exception v1

    move-object v1, v2

    goto :goto_3
.end method

.method public getTimeMillis()J
    .locals 2

    .prologue
    .line 192
    iget-wide v0, p0, Lmiui/os/DropBoxManager$Entry;->aW:J

    return-wide v0
.end method

.method public writeToParcel(Landroid/os/Parcel;I)V
    .locals 2

    .prologue
    .line 255
    iget-object v0, p0, Lmiui/os/DropBoxManager$Entry;->aV:Ljava/lang/String;

    invoke-virtual {p1, v0}, Landroid/os/Parcel;->writeString(Ljava/lang/String;)V

    .line 256
    iget-wide v0, p0, Lmiui/os/DropBoxManager$Entry;->aW:J

    invoke-virtual {p1, v0, v1}, Landroid/os/Parcel;->writeLong(J)V

    .line 257
    iget-object v0, p0, Lmiui/os/DropBoxManager$Entry;->aY:Landroid/os/ParcelFileDescriptor;

    if-eqz v0, :cond_0

    .line 258
    iget v0, p0, Lmiui/os/DropBoxManager$Entry;->aZ:I

    and-int/lit8 v0, v0, -0x9

    invoke-virtual {p1, v0}, Landroid/os/Parcel;->writeInt(I)V

    .line 259
    iget-object v0, p0, Lmiui/os/DropBoxManager$Entry;->aY:Landroid/os/ParcelFileDescriptor;

    invoke-virtual {v0, p1, p2}, Landroid/os/ParcelFileDescriptor;->writeToParcel(Landroid/os/Parcel;I)V

    .line 264
    :goto_0
    return-void

    .line 261
    :cond_0
    iget v0, p0, Lmiui/os/DropBoxManager$Entry;->aZ:I

    or-int/lit8 v0, v0, 0x8

    invoke-virtual {p1, v0}, Landroid/os/Parcel;->writeInt(I)V

    .line 262
    iget-object v0, p0, Lmiui/os/DropBoxManager$Entry;->aX:[B

    invoke-virtual {p1, v0}, Landroid/os/Parcel;->writeByteArray([B)V

    goto :goto_0
.end method
