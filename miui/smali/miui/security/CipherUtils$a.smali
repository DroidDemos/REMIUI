.class Lmiui/security/CipherUtils$a;
.super Ljava/io/InputStream;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/security/CipherUtils;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0xa
    name = "a"
.end annotation


# instance fields
.field final TB:Ljavax/crypto/Cipher;

.field final TE:Ljava/io/InputStream;

.field TF:Z

.field TI:[B

.field TP:[B

.field TQ:I


# direct methods
.method constructor <init>(Ljavax/crypto/Cipher;Ljava/io/InputStream;)V
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 243
    invoke-direct {p0}, Ljava/io/InputStream;-><init>()V

    .line 244
    iput-object p1, p0, Lmiui/security/CipherUtils$a;->TB:Ljavax/crypto/Cipher;

    .line 245
    iput-object p2, p0, Lmiui/security/CipherUtils$a;->TE:Ljava/io/InputStream;

    .line 246
    invoke-virtual {p1}, Ljavax/crypto/Cipher;->getBlockSize()I

    move-result v0

    new-array v0, v0, [B

    iput-object v0, p0, Lmiui/security/CipherUtils$a;->TI:[B

    .line 247
    invoke-direct {p0}, Lmiui/security/CipherUtils$a;->update()V

    .line 248
    return-void
.end method

.method private update()V
    .locals 4
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    const/4 v3, 0x0

    .line 285
    iget-object v0, p0, Lmiui/security/CipherUtils$a;->TE:Ljava/io/InputStream;

    iget-object v1, p0, Lmiui/security/CipherUtils$a;->TI:[B

    invoke-virtual {v0, v1}, Ljava/io/InputStream;->read([B)I

    move-result v0

    .line 286
    if-lez v0, :cond_0

    .line 287
    iget-object v1, p0, Lmiui/security/CipherUtils$a;->TB:Ljavax/crypto/Cipher;

    iget-object v2, p0, Lmiui/security/CipherUtils$a;->TI:[B

    invoke-virtual {v1, v2, v3, v0}, Ljavax/crypto/Cipher;->update([BII)[B

    move-result-object v0

    iput-object v0, p0, Lmiui/security/CipherUtils$a;->TP:[B

    .line 288
    iput v3, p0, Lmiui/security/CipherUtils$a;->TQ:I

    .line 300
    :goto_0
    return-void

    .line 290
    :cond_0
    const/4 v0, 0x1

    iput-boolean v0, p0, Lmiui/security/CipherUtils$a;->TF:Z

    .line 292
    :try_start_0
    iget-object v0, p0, Lmiui/security/CipherUtils$a;->TB:Ljavax/crypto/Cipher;

    invoke-virtual {v0}, Ljavax/crypto/Cipher;->doFinal()[B

    move-result-object v0

    iput-object v0, p0, Lmiui/security/CipherUtils$a;->TP:[B

    .line 293
    const/4 v0, 0x0

    iput v0, p0, Lmiui/security/CipherUtils$a;->TQ:I
    :try_end_0
    .catch Ljavax/crypto/IllegalBlockSizeException; {:try_start_0 .. :try_end_0} :catch_0
    .catch Ljavax/crypto/BadPaddingException; {:try_start_0 .. :try_end_0} :catch_1

    goto :goto_0

    .line 294
    :catch_0
    move-exception v0

    .line 295
    new-instance v1, Ljava/lang/RuntimeException;

    const-string v2, "IllegalBlockSizeException"

    invoke-direct {v1, v2, v0}, Ljava/lang/RuntimeException;-><init>(Ljava/lang/String;Ljava/lang/Throwable;)V

    throw v1

    .line 296
    :catch_1
    move-exception v0

    .line 297
    new-instance v1, Ljava/lang/RuntimeException;

    const-string v2, "BadPaddingException"

    invoke-direct {v1, v2, v0}, Ljava/lang/RuntimeException;-><init>(Ljava/lang/String;Ljava/lang/Throwable;)V

    throw v1
.end method


# virtual methods
.method public available()I
    .locals 2
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 255
    iget-object v0, p0, Lmiui/security/CipherUtils$a;->TP:[B

    if-eqz v0, :cond_0

    iget-object v0, p0, Lmiui/security/CipherUtils$a;->TP:[B

    array-length v0, v0

    iget v1, p0, Lmiui/security/CipherUtils$a;->TQ:I

    if-le v0, v1, :cond_0

    .line 256
    iget-object v0, p0, Lmiui/security/CipherUtils$a;->TP:[B

    array-length v0, v0

    iget v1, p0, Lmiui/security/CipherUtils$a;->TQ:I

    sub-int/2addr v0, v1

    iget-object v1, p0, Lmiui/security/CipherUtils$a;->TE:Ljava/io/InputStream;

    invoke-virtual {v1}, Ljava/io/InputStream;->available()I

    move-result v1

    add-int/2addr v0, v1

    .line 258
    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public read()I
    .locals 3
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    const/4 v0, -0x1

    .line 266
    iget-object v1, p0, Lmiui/security/CipherUtils$a;->TP:[B

    if-nez v1, :cond_1

    .line 281
    :cond_0
    :goto_0
    return v0

    .line 270
    :cond_1
    iget v1, p0, Lmiui/security/CipherUtils$a;->TQ:I

    iget-object v2, p0, Lmiui/security/CipherUtils$a;->TP:[B

    array-length v2, v2

    if-ge v1, v2, :cond_2

    .line 271
    iget-object v0, p0, Lmiui/security/CipherUtils$a;->TP:[B

    iget v1, p0, Lmiui/security/CipherUtils$a;->TQ:I

    add-int/lit8 v2, v1, 0x1

    iput v2, p0, Lmiui/security/CipherUtils$a;->TQ:I

    aget-byte v0, v0, v1

    goto :goto_0

    .line 274
    :cond_2
    const/4 v1, 0x0

    iput-object v1, p0, Lmiui/security/CipherUtils$a;->TP:[B

    .line 276
    iget-boolean v1, p0, Lmiui/security/CipherUtils$a;->TF:Z

    if-nez v1, :cond_0

    .line 280
    invoke-direct {p0}, Lmiui/security/CipherUtils$a;->update()V

    .line 281
    invoke-virtual {p0}, Lmiui/security/CipherUtils$a;->read()I

    move-result v0

    goto :goto_0
.end method
