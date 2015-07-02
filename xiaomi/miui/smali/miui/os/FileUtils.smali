.class public Lmiui/os/FileUtils;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field private static final Qt:Ljava/util/regex/Pattern;

.field public static final S_IRGRP:I = 0x20

.field public static final S_IROTH:I = 0x4

.field public static final S_IRUSR:I = 0x100

.field public static final S_IRWXG:I = 0x38

.field public static final S_IRWXO:I = 0x7

.field public static final S_IRWXU:I = 0x1c0

.field public static final S_IWGRP:I = 0x10

.field public static final S_IWOTH:I = 0x2

.field public static final S_IWUSR:I = 0x80

.field public static final S_IXGRP:I = 0x8

.field public static final S_IXOTH:I = 0x1

.field public static final S_IXUSR:I = 0x40


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .prologue
    .line 54
    const-string v0, "[\\w%+,./=_-]+"

    invoke-static {v0}, Ljava/util/regex/Pattern;->compile(Ljava/lang/String;)Ljava/util/regex/Pattern;

    move-result-object v0

    sput-object v0, Lmiui/os/FileUtils;->Qt:Ljava/util/regex/Pattern;

    return-void
.end method

.method protected constructor <init>()V
    .locals 2
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/InstantiationException;
        }
    .end annotation

    .prologue
    .line 60
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 61
    new-instance v0, Ljava/lang/InstantiationException;

    const-string v1, "Cannot instantiate utility class"

    invoke-direct {v0, v1}, Ljava/lang/InstantiationException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

.method private static F(Ljava/lang/String;)[B
    .locals 5
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 466
    const/4 v2, 0x0

    .line 468
    :try_start_0
    new-instance v1, Ljava/io/RandomAccessFile;

    const-string v0, "r"

    invoke-direct {v1, p0, v0}, Ljava/io/RandomAccessFile;-><init>(Ljava/lang/String;Ljava/lang/String;)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_1

    .line 469
    :try_start_1
    new-instance v0, Ljava/io/ByteArrayOutputStream;

    invoke-virtual {v1}, Ljava/io/RandomAccessFile;->length()J

    move-result-wide v2

    long-to-int v2, v2

    invoke-direct {v0, v2}, Ljava/io/ByteArrayOutputStream;-><init>(I)V

    .line 470
    const/16 v2, 0x2000

    new-array v2, v2, [B

    .line 472
    :goto_0
    invoke-virtual {v1, v2}, Ljava/io/RandomAccessFile;->read([B)I

    move-result v3

    .line 473
    const/4 v4, -0x1

    if-ne v3, v4, :cond_1

    .line 474
    invoke-virtual {v0}, Ljava/io/ByteArrayOutputStream;->toByteArray()[B
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    move-result-object v0

    .line 480
    if-eqz v1, :cond_0

    .line 481
    :try_start_2
    invoke-virtual {v1}, Ljava/io/RandomAccessFile;->close()V
    :try_end_2
    .catch Ljava/io/IOException; {:try_start_2 .. :try_end_2} :catch_0

    .line 485
    :cond_0
    :goto_1
    return-object v0

    .line 476
    :cond_1
    const/4 v4, 0x0

    :try_start_3
    invoke-virtual {v0, v2, v4, v3}, Ljava/io/ByteArrayOutputStream;->write([BII)V
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_0

    goto :goto_0

    .line 479
    :catchall_0
    move-exception v0

    .line 480
    :goto_2
    if-eqz v1, :cond_2

    .line 481
    :try_start_4
    invoke-virtual {v1}, Ljava/io/RandomAccessFile;->close()V
    :try_end_4
    .catch Ljava/io/IOException; {:try_start_4 .. :try_end_4} :catch_1

    .line 485
    :cond_2
    :goto_3
    throw v0

    .line 483
    :catch_0
    move-exception v1

    goto :goto_1

    :catch_1
    move-exception v1

    goto :goto_3

    .line 479
    :catchall_1
    move-exception v0

    move-object v1, v2

    goto :goto_2
.end method

.method public static addNoMedia(Ljava/lang/String;)Z
    .locals 3

    .prologue
    .line 414
    new-instance v0, Ljava/io/File;

    invoke-direct {v0, p0}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    .line 415
    invoke-virtual {v0}, Ljava/io/File;->isDirectory()Z

    move-result v1

    if-eqz v1, :cond_0

    .line 417
    :try_start_0
    new-instance v1, Ljava/io/File;

    const-string v2, ".nomedia"

    invoke-direct {v1, v0, v2}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    invoke-virtual {v1}, Ljava/io/File;->createNewFile()Z
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_0

    move-result v0

    .line 422
    :goto_0
    return v0

    .line 418
    :catch_0
    move-exception v0

    .line 422
    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public static checksumCrc32(Ljava/io/File;)J
    .locals 4
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 304
    new-instance v0, Ljava/util/zip/CRC32;

    invoke-direct {v0}, Ljava/util/zip/CRC32;-><init>()V

    .line 305
    const/4 v2, 0x0

    .line 308
    :try_start_0
    new-instance v1, Ljava/util/zip/CheckedInputStream;

    new-instance v3, Ljava/io/FileInputStream;

    invoke-direct {v3, p0}, Ljava/io/FileInputStream;-><init>(Ljava/io/File;)V

    invoke-direct {v1, v3, v0}, Ljava/util/zip/CheckedInputStream;-><init>(Ljava/io/InputStream;Ljava/util/zip/Checksum;)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 309
    const/16 v2, 0x80

    :try_start_1
    new-array v2, v2, [B

    .line 310
    :cond_0
    invoke-virtual {v1, v2}, Ljava/util/zip/CheckedInputStream;->read([B)I

    move-result v3

    if-gez v3, :cond_0

    .line 313
    invoke-virtual {v0}, Ljava/util/zip/CRC32;->getValue()J
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_1

    move-result-wide v2

    .line 315
    if-eqz v1, :cond_1

    .line 317
    :try_start_2
    invoke-virtual {v1}, Ljava/util/zip/CheckedInputStream;->close()V
    :try_end_2
    .catch Ljava/io/IOException; {:try_start_2 .. :try_end_2} :catch_0

    .line 320
    :cond_1
    :goto_0
    return-wide v2

    .line 315
    :catchall_0
    move-exception v0

    move-object v1, v2

    :goto_1
    if-eqz v1, :cond_2

    .line 317
    :try_start_3
    invoke-virtual {v1}, Ljava/util/zip/CheckedInputStream;->close()V
    :try_end_3
    .catch Ljava/io/IOException; {:try_start_3 .. :try_end_3} :catch_1

    .line 320
    :cond_2
    :goto_2
    throw v0

    .line 318
    :catch_0
    move-exception v0

    goto :goto_0

    :catch_1
    move-exception v1

    goto :goto_2

    .line 315
    :catchall_1
    move-exception v0

    goto :goto_1
.end method

.method public static chmod(Ljava/lang/String;I)Z
    .locals 2

    .prologue
    const/4 v0, 0x1

    .line 83
    if-eqz p0, :cond_0

    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result v1

    if-nez v1, :cond_2

    .line 84
    :cond_0
    const/4 v0, 0x0

    .line 91
    :cond_1
    :goto_0
    return v0

    .line 87
    :cond_2
    if-ltz p1, :cond_1

    .line 91
    invoke-static {p0, p1, v0}, Lcom/miui/internal/os/Native;->chmod(Ljava/lang/String;II)Z

    move-result v0

    goto :goto_0
.end method

.method public static chown(Ljava/lang/String;II)Z
    .locals 3

    .prologue
    const/4 v1, 0x1

    const/4 v0, -0x1

    .line 103
    if-eqz p0, :cond_0

    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result v2

    if-nez v2, :cond_1

    .line 104
    :cond_0
    const/4 v0, 0x0

    .line 117
    :goto_0
    return v0

    .line 107
    :cond_1
    if-gez p1, :cond_2

    if-gez p2, :cond_2

    move v0, v1

    .line 108
    goto :goto_0

    .line 111
    :cond_2
    if-gez p1, :cond_4

    move p1, v0

    .line 117
    :cond_3
    :goto_1
    invoke-static {p0, p1, p2, v1}, Lcom/miui/internal/os/Native;->chown(Ljava/lang/String;III)Z

    move-result v0

    goto :goto_0

    .line 113
    :cond_4
    if-gez p2, :cond_3

    move p2, v0

    .line 114
    goto :goto_1
.end method

.method public static copyFile(Ljava/io/File;Ljava/io/File;)Z
    .locals 2

    .prologue
    .line 151
    .line 153
    :try_start_0
    new-instance v1, Ljava/io/FileInputStream;

    invoke-direct {v1, p0}, Ljava/io/FileInputStream;-><init>(Ljava/io/File;)V
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_0

    .line 155
    :try_start_1
    invoke-static {v1, p1}, Lmiui/os/FileUtils;->copyToFile(Ljava/io/InputStream;Ljava/io/File;)Z
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    move-result v0

    .line 157
    :try_start_2
    invoke-virtual {v1}, Ljava/io/InputStream;->close()V

    .line 162
    :goto_0
    return v0

    .line 157
    :catchall_0
    move-exception v0

    invoke-virtual {v1}, Ljava/io/InputStream;->close()V

    throw v0
    :try_end_2
    .catch Ljava/io/IOException; {:try_start_2 .. :try_end_2} :catch_0

    .line 159
    :catch_0
    move-exception v0

    .line 160
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public static copyToFile(Ljava/io/InputStream;Ljava/io/File;)Z
    .locals 5

    .prologue
    const/4 v0, 0x0

    .line 172
    :try_start_0
    invoke-virtual {p1}, Ljava/io/File;->exists()Z

    move-result v1

    if-eqz v1, :cond_0

    .line 173
    invoke-virtual {p1}, Ljava/io/File;->delete()Z

    move-result v1

    if-nez v1, :cond_0

    .line 195
    :goto_0
    return v0

    .line 177
    :cond_0
    new-instance v2, Ljava/io/FileOutputStream;

    invoke-direct {v2, p1}, Ljava/io/FileOutputStream;-><init>(Ljava/io/File;)V
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_0

    .line 179
    const/16 v1, 0x1000

    :try_start_1
    new-array v1, v1, [B

    .line 181
    :goto_1
    invoke-virtual {p0, v1}, Ljava/io/InputStream;->read([B)I

    move-result v3

    if-ltz v3, :cond_1

    .line 182
    const/4 v4, 0x0

    invoke-virtual {v2, v1, v4, v3}, Ljava/io/FileOutputStream;->write([BII)V
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    goto :goto_1

    .line 185
    :catchall_0
    move-exception v1

    :try_start_2
    invoke-virtual {v2}, Ljava/io/FileOutputStream;->flush()V
    :try_end_2
    .catch Ljava/io/IOException; {:try_start_2 .. :try_end_2} :catch_0

    .line 187
    :try_start_3
    invoke-virtual {v2}, Ljava/io/FileOutputStream;->getFD()Ljava/io/FileDescriptor;

    move-result-object v3

    invoke-virtual {v3}, Ljava/io/FileDescriptor;->sync()V
    :try_end_3
    .catch Ljava/io/IOException; {:try_start_3 .. :try_end_3} :catch_1

    .line 191
    :goto_2
    :try_start_4
    invoke-virtual {v2}, Ljava/io/FileOutputStream;->close()V

    throw v1

    .line 194
    :catch_0
    move-exception v1

    goto :goto_0

    .line 185
    :cond_1
    invoke-virtual {v2}, Ljava/io/FileOutputStream;->flush()V
    :try_end_4
    .catch Ljava/io/IOException; {:try_start_4 .. :try_end_4} :catch_0

    .line 187
    :try_start_5
    invoke-virtual {v2}, Ljava/io/FileOutputStream;->getFD()Ljava/io/FileDescriptor;

    move-result-object v1

    invoke-virtual {v1}, Ljava/io/FileDescriptor;->sync()V
    :try_end_5
    .catch Ljava/io/IOException; {:try_start_5 .. :try_end_5} :catch_2

    .line 191
    :goto_3
    :try_start_6
    invoke-virtual {v2}, Ljava/io/FileOutputStream;->close()V
    :try_end_6
    .catch Ljava/io/IOException; {:try_start_6 .. :try_end_6} :catch_0

    .line 193
    const/4 v0, 0x1

    goto :goto_0

    .line 188
    :catch_1
    move-exception v3

    goto :goto_2

    :catch_2
    move-exception v1

    goto :goto_3
.end method

.method public static getExtension(Ljava/lang/String;)Ljava/lang/String;
    .locals 2

    .prologue
    .line 365
    if-eqz p0, :cond_0

    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result v0

    if-nez v0, :cond_1

    .line 366
    :cond_0
    const-string v0, ""

    .line 372
    :goto_0
    return-object v0

    .line 368
    :cond_1
    const/16 v0, 0x2e

    invoke-virtual {p0, v0}, Ljava/lang/String;->lastIndexOf(I)I

    move-result v0

    .line 369
    const/4 v1, -0x1

    if-le v0, v1, :cond_2

    .line 370
    add-int/lit8 v0, v0, 0x1

    invoke-virtual {p0, v0}, Ljava/lang/String;->substring(I)Ljava/lang/String;

    move-result-object v0

    goto :goto_0

    .line 372
    :cond_2
    const-string v0, ""

    goto :goto_0
.end method

.method public static getFileName(Ljava/lang/String;)Ljava/lang/String;
    .locals 2

    .prologue
    .line 399
    if-eqz p0, :cond_0

    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result v0

    if-nez v0, :cond_2

    .line 400
    :cond_0
    const-string p0, ""

    .line 406
    :cond_1
    :goto_0
    return-object p0

    .line 402
    :cond_2
    sget-char v0, Ljava/io/File;->separatorChar:C

    invoke-virtual {p0, v0}, Ljava/lang/String;->lastIndexOf(I)I

    move-result v0

    .line 403
    const/4 v1, -0x1

    if-le v0, v1, :cond_1

    .line 404
    add-int/lit8 v0, v0, 0x1

    invoke-virtual {p0, v0}, Ljava/lang/String;->substring(I)Ljava/lang/String;

    move-result-object p0

    goto :goto_0
.end method

.method public static getFolderSize(Ljava/io/File;)J
    .locals 7

    .prologue
    const-wide/16 v0, 0x0

    .line 440
    invoke-virtual {p0}, Ljava/io/File;->exists()Z

    move-result v2

    if-nez v2, :cond_1

    .line 454
    :cond_0
    :goto_0
    return-wide v0

    .line 444
    :cond_1
    invoke-virtual {p0}, Ljava/io/File;->isDirectory()Z

    move-result v2

    if-eqz v2, :cond_2

    .line 446
    invoke-virtual {p0}, Ljava/io/File;->listFiles()[Ljava/io/File;

    move-result-object v5

    .line 447
    if-eqz v5, :cond_0

    .line 448
    array-length v6, v5

    const/4 v2, 0x0

    :goto_1
    if-ge v2, v6, :cond_0

    aget-object v3, v5, v2

    .line 449
    invoke-static {v3}, Lmiui/os/FileUtils;->getFolderSize(Ljava/io/File;)J

    move-result-wide v3

    add-long/2addr v3, v0

    .line 448
    add-int/lit8 v0, v2, 0x1

    move v2, v0

    move-wide v0, v3

    goto :goto_1

    .line 454
    :cond_2
    invoke-virtual {p0}, Ljava/io/File;->length()J

    move-result-wide v0

    goto :goto_0
.end method

.method public static getName(Ljava/lang/String;)Ljava/lang/String;
    .locals 2

    .prologue
    .line 381
    if-eqz p0, :cond_0

    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result v0

    if-nez v0, :cond_1

    .line 382
    :cond_0
    const-string v0, ""

    .line 389
    :goto_0
    return-object v0

    .line 384
    :cond_1
    sget-char v0, Ljava/io/File;->separatorChar:C

    invoke-virtual {p0, v0}, Ljava/lang/String;->lastIndexOf(I)I

    move-result v0

    .line 385
    if-gez v0, :cond_2

    .line 386
    const/4 v0, -0x1

    .line 388
    :cond_2
    const-string v1, "."

    invoke-virtual {p0, v1}, Ljava/lang/String;->lastIndexOf(Ljava/lang/String;)I

    move-result v1

    .line 389
    if-gez v1, :cond_3

    add-int/lit8 v0, v0, 0x1

    invoke-virtual {p0, v0}, Ljava/lang/String;->substring(I)Ljava/lang/String;

    move-result-object v0

    goto :goto_0

    :cond_3
    add-int/lit8 v0, v0, 0x1

    invoke-virtual {p0, v0, v1}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object v0

    goto :goto_0
.end method

.method public static isFilenameSafe(Ljava/io/File;)Z
    .locals 2

    .prologue
    .line 209
    sget-object v0, Lmiui/os/FileUtils;->Qt:Ljava/util/regex/Pattern;

    invoke-virtual {p0}, Ljava/io/File;->getPath()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/util/regex/Pattern;->matcher(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;

    move-result-object v0

    invoke-virtual {v0}, Ljava/util/regex/Matcher;->matches()Z

    move-result v0

    return v0
.end method

.method public static mkdirs(Ljava/io/File;III)Z
    .locals 2

    .prologue
    const/4 v0, 0x0

    .line 336
    invoke-virtual {p0}, Ljava/io/File;->exists()Z

    move-result v1

    if-eqz v1, :cond_1

    .line 337
    invoke-virtual {p0}, Ljava/io/File;->isDirectory()Z

    move-result v0

    .line 347
    :cond_0
    :goto_0
    return v0

    .line 341
    :cond_1
    invoke-virtual {p0}, Ljava/io/File;->getParentFile()Ljava/io/File;

    move-result-object v1

    .line 342
    invoke-static {v1, p1, p2, p3}, Lmiui/os/FileUtils;->mkdirs(Ljava/io/File;III)Z

    move-result v1

    if-eqz v1, :cond_0

    .line 347
    invoke-virtual {p0}, Ljava/io/File;->mkdir()Z

    move-result v1

    if-eqz v1, :cond_0

    invoke-virtual {p0}, Ljava/io/File;->getPath()Ljava/lang/String;

    move-result-object v1

    invoke-static {v1, p1}, Lmiui/os/FileUtils;->chmod(Ljava/lang/String;I)Z

    move-result v1

    if-eqz v1, :cond_0

    invoke-virtual {p0}, Ljava/io/File;->getPath()Ljava/lang/String;

    move-result-object v1

    invoke-static {v1, p2, p3}, Lmiui/os/FileUtils;->chown(Ljava/lang/String;II)Z

    move-result v1

    if-eqz v1, :cond_0

    const/4 v0, 0x1

    goto :goto_0
.end method

.method public static normalizeDirectoryName(Ljava/lang/String;)Ljava/lang/String;
    .locals 2

    .prologue
    .line 431
    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result v0

    add-int/lit8 v0, v0, -0x1

    invoke-virtual {p0, v0}, Ljava/lang/String;->charAt(I)C

    move-result v0

    sget-char v1, Ljava/io/File;->separatorChar:C

    if-ne v0, v1, :cond_0

    :goto_0
    return-object p0

    :cond_0
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    sget-object v1, Ljava/io/File;->separator:Ljava/lang/String;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    goto :goto_0
.end method

.method public static readFileAsString(Ljava/lang/String;)Ljava/lang/String;
    .locals 3
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 462
    new-instance v0, Ljava/lang/String;

    invoke-static {p0}, Lmiui/os/FileUtils;->F(Ljava/lang/String;)[B

    move-result-object v1

    const-string v2, "UTF-8"

    invoke-static {v2}, Ljava/nio/charset/Charset;->forName(Ljava/lang/String;)Ljava/nio/charset/Charset;

    move-result-object v2

    invoke-direct {v0, v1, v2}, Ljava/lang/String;-><init>([BLjava/nio/charset/Charset;)V

    return-object v0
.end method

.method public static readTextFile(Ljava/io/File;ILjava/lang/String;)Ljava/lang/String;
    .locals 10
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    const-wide/16 v7, 0x0

    const/4 v2, 0x0

    const/4 v0, 0x1

    const/4 v1, 0x0

    .line 222
    new-instance v4, Ljava/io/FileInputStream;

    invoke-direct {v4, p0}, Ljava/io/FileInputStream;-><init>(Ljava/io/File;)V

    .line 224
    :try_start_0
    invoke-virtual {p0}, Ljava/io/File;->length()J

    move-result-wide v5

    .line 225
    if-gtz p1, :cond_0

    cmp-long v3, v5, v7

    if-lez v3, :cond_6

    if-nez p1, :cond_6

    .line 226
    :cond_0
    cmp-long v0, v5, v7

    if-lez v0, :cond_2

    if-eqz p1, :cond_1

    int-to-long v0, p1

    cmp-long v0, v5, v0

    if-gez v0, :cond_2

    .line 227
    :cond_1
    long-to-int p1, v5

    .line 228
    :cond_2
    add-int/lit8 v0, p1, 0x1

    new-array v1, v0, [B

    .line 229
    invoke-virtual {v4, v1}, Ljava/io/InputStream;->read([B)I

    move-result v2

    .line 230
    if-gtz v2, :cond_3

    .line 231
    const-string v0, ""
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 276
    invoke-virtual {v4}, Ljava/io/InputStream;->close()V

    :goto_0
    return-object v0

    .line 232
    :cond_3
    if-gt v2, p1, :cond_4

    .line 233
    :try_start_1
    new-instance v0, Ljava/lang/String;

    const/4 v3, 0x0

    invoke-direct {v0, v1, v3, v2}, Ljava/lang/String;-><init>([BII)V
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 276
    invoke-virtual {v4}, Ljava/io/InputStream;->close()V

    goto :goto_0

    .line 234
    :cond_4
    if-nez p2, :cond_5

    .line 235
    :try_start_2
    new-instance v0, Ljava/lang/String;

    const/4 v2, 0x0

    invoke-direct {v0, v1, v2, p1}, Ljava/lang/String;-><init>([BII)V
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    .line 276
    invoke-virtual {v4}, Ljava/io/InputStream;->close()V

    goto :goto_0

    .line 236
    :cond_5
    :try_start_3
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    new-instance v2, Ljava/lang/String;

    const/4 v3, 0x0

    invoke-direct {v2, v1, v3, p1}, Ljava/lang/String;-><init>([BII)V

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_0

    move-result-object v0

    .line 276
    invoke-virtual {v4}, Ljava/io/InputStream;->close()V

    goto :goto_0

    .line 237
    :cond_6
    if-gez p1, :cond_d

    move-object v3, v2

    .line 242
    :goto_1
    if-eqz v2, :cond_7

    move v1, v0

    .line 247
    :cond_7
    if-nez v2, :cond_8

    .line 248
    neg-int v2, p1

    :try_start_4
    new-array v2, v2, [B

    .line 249
    :cond_8
    invoke-virtual {v4, v2}, Ljava/io/InputStream;->read([B)I

    move-result v5

    .line 250
    array-length v6, v2

    if-eq v5, v6, :cond_11

    .line 252
    if-nez v3, :cond_9

    if-gtz v5, :cond_9

    .line 253
    const-string v0, ""
    :try_end_4
    .catchall {:try_start_4 .. :try_end_4} :catchall_0

    .line 276
    invoke-virtual {v4}, Ljava/io/InputStream;->close()V

    goto :goto_0

    .line 254
    :cond_9
    if-nez v3, :cond_a

    .line 255
    :try_start_5
    new-instance v0, Ljava/lang/String;

    const/4 v1, 0x0

    invoke-direct {v0, v2, v1, v5}, Ljava/lang/String;-><init>([BII)V
    :try_end_5
    .catchall {:try_start_5 .. :try_end_5} :catchall_0

    .line 276
    invoke-virtual {v4}, Ljava/io/InputStream;->close()V

    goto :goto_0

    .line 256
    :cond_a
    if-lez v5, :cond_10

    .line 258
    const/4 v1, 0x0

    :try_start_6
    array-length v6, v3

    sub-int/2addr v6, v5

    invoke-static {v3, v5, v3, v1, v6}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    .line 259
    const/4 v1, 0x0

    array-length v6, v3

    sub-int/2addr v6, v5

    invoke-static {v2, v1, v3, v6, v5}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    .line 261
    :goto_2
    if-eqz p2, :cond_b

    if-nez v0, :cond_c

    .line 262
    :cond_b
    new-instance v0, Ljava/lang/String;

    invoke-direct {v0, v3}, Ljava/lang/String;-><init>([B)V
    :try_end_6
    .catchall {:try_start_6 .. :try_end_6} :catchall_0

    .line 276
    invoke-virtual {v4}, Ljava/io/InputStream;->close()V

    goto :goto_0

    .line 263
    :cond_c
    :try_start_7
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v0, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    new-instance v1, Ljava/lang/String;

    invoke-direct {v1, v3}, Ljava/lang/String;-><init>([B)V

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;
    :try_end_7
    .catchall {:try_start_7 .. :try_end_7} :catchall_0

    move-result-object v0

    .line 276
    invoke-virtual {v4}, Ljava/io/InputStream;->close()V

    goto/16 :goto_0

    .line 265
    :cond_d
    :try_start_8
    new-instance v0, Ljava/io/ByteArrayOutputStream;

    invoke-direct {v0}, Ljava/io/ByteArrayOutputStream;-><init>()V

    .line 267
    const/16 v1, 0x400

    new-array v1, v1, [B

    .line 269
    :cond_e
    invoke-virtual {v4, v1}, Ljava/io/InputStream;->read([B)I

    move-result v2

    .line 270
    if-lez v2, :cond_f

    .line 271
    const/4 v3, 0x0

    invoke-virtual {v0, v1, v3, v2}, Ljava/io/ByteArrayOutputStream;->write([BII)V

    .line 272
    :cond_f
    array-length v3, v1

    if-eq v2, v3, :cond_e

    .line 273
    invoke-virtual {v0}, Ljava/io/ByteArrayOutputStream;->toString()Ljava/lang/String;
    :try_end_8
    .catchall {:try_start_8 .. :try_end_8} :catchall_0

    move-result-object v0

    .line 276
    invoke-virtual {v4}, Ljava/io/InputStream;->close()V

    goto/16 :goto_0

    :catchall_0
    move-exception v0

    invoke-virtual {v4}, Ljava/io/InputStream;->close()V

    throw v0

    :cond_10
    move v0, v1

    goto :goto_2

    :cond_11
    move-object v9, v2

    move-object v2, v3

    move-object v3, v9

    goto/16 :goto_1
.end method

.method public static rm(Ljava/lang/String;)Z
    .locals 1

    .prologue
    .line 356
    const/4 v0, 0x1

    invoke-static {p0, v0}, Lcom/miui/internal/os/Native;->rm(Ljava/lang/String;I)Z

    move-result v0

    return v0
.end method

.method public static stringToFile(Ljava/lang/String;Ljava/lang/String;)V
    .locals 2
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 288
    new-instance v0, Ljava/io/FileWriter;

    invoke-direct {v0, p0}, Ljava/io/FileWriter;-><init>(Ljava/lang/String;)V

    .line 290
    :try_start_0
    invoke-virtual {v0, p1}, Ljava/io/FileWriter;->write(Ljava/lang/String;)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 292
    invoke-virtual {v0}, Ljava/io/FileWriter;->close()V

    .line 294
    return-void

    .line 292
    :catchall_0
    move-exception v1

    invoke-virtual {v0}, Ljava/io/FileWriter;->close()V

    throw v1
.end method

.method public static sync(Ljava/io/FileOutputStream;)Z
    .locals 1

    .prologue
    .line 135
    if-eqz p0, :cond_0

    .line 136
    :try_start_0
    invoke-virtual {p0}, Ljava/io/FileOutputStream;->getFD()Ljava/io/FileDescriptor;

    move-result-object v0

    invoke-virtual {v0}, Ljava/io/FileDescriptor;->sync()V
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_0

    .line 138
    :cond_0
    const/4 v0, 0x1

    .line 142
    :goto_0
    return v0

    .line 139
    :catch_0
    move-exception v0

    .line 142
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public static umask(I)I
    .locals 1

    .prologue
    .line 126
    invoke-static {p0}, Lcom/miui/internal/os/Native;->umask(I)I

    move-result v0

    return v0
.end method
