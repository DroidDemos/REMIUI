.class Lmiui/net/http/a$a;
.super Ljava/io/InputStream;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/net/http/a;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0xa
    name = "a"
.end annotation


# instance fields
.field KA:I

.field Ky:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List",
            "<",
            "Ljava/lang/Object;",
            ">;"
        }
    .end annotation
.end field

.field Kz:I


# direct methods
.method public constructor <init>(Ljava/util/List;)V
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List",
            "<",
            "Ljava/lang/Object;",
            ">;)V"
        }
    .end annotation

    .prologue
    const/4 v0, 0x0

    .line 192
    invoke-direct {p0}, Ljava/io/InputStream;-><init>()V

    .line 193
    iput-object p1, p0, Lmiui/net/http/a$a;->Ky:Ljava/util/List;

    .line 194
    iput v0, p0, Lmiui/net/http/a$a;->Kz:I

    .line 195
    iput v0, p0, Lmiui/net/http/a$a;->KA:I

    .line 196
    return-void
.end method


# virtual methods
.method public read()I
    .locals 5
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    const/4 v4, 0x0

    .line 248
    const/4 v0, -0x1

    move v1, v0

    .line 250
    :goto_0
    iget v0, p0, Lmiui/net/http/a$a;->Kz:I

    iget-object v2, p0, Lmiui/net/http/a$a;->Ky:Ljava/util/List;

    invoke-interface {v2}, Ljava/util/List;->size()I

    move-result v2

    if-ge v0, v2, :cond_4

    .line 251
    iget-object v0, p0, Lmiui/net/http/a$a;->Ky:Ljava/util/List;

    iget v2, p0, Lmiui/net/http/a$a;->Kz:I

    invoke-interface {v0, v2}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v0

    .line 252
    instance-of v2, v0, [B

    if-eqz v2, :cond_2

    .line 253
    check-cast v0, [B

    check-cast v0, [B

    .line 254
    iget v2, p0, Lmiui/net/http/a$a;->KA:I

    add-int/lit8 v2, v2, 0x1

    iput v2, p0, Lmiui/net/http/a$a;->KA:I

    .line 255
    iget v2, p0, Lmiui/net/http/a$a;->KA:I

    array-length v3, v0

    if-ge v2, v3, :cond_1

    .line 256
    iget v1, p0, Lmiui/net/http/a$a;->KA:I

    aget-byte v0, v0, v1

    .line 274
    :cond_0
    :goto_1
    return v0

    .line 259
    :cond_1
    iget v0, p0, Lmiui/net/http/a$a;->Kz:I

    add-int/lit8 v0, v0, 0x1

    iput v0, p0, Lmiui/net/http/a$a;->Kz:I

    .line 260
    iput v4, p0, Lmiui/net/http/a$a;->KA:I

    move v0, v1

    :goto_2
    move v1, v0

    .line 272
    goto :goto_0

    .line 262
    :cond_2
    instance-of v1, v0, Ljava/io/InputStream;

    if-eqz v1, :cond_3

    .line 263
    check-cast v0, Ljava/io/InputStream;

    .line 264
    invoke-virtual {v0}, Ljava/io/InputStream;->read()I

    move-result v0

    if-gez v0, :cond_0

    .line 267
    iget v1, p0, Lmiui/net/http/a$a;->Kz:I

    add-int/lit8 v1, v1, 0x1

    iput v1, p0, Lmiui/net/http/a$a;->Kz:I

    .line 268
    iput v4, p0, Lmiui/net/http/a$a;->KA:I

    goto :goto_2

    .line 270
    :cond_3
    new-instance v0, Ljava/io/IOException;

    const-string v1, "Unexpected value"

    invoke-direct {v0, v1}, Ljava/io/IOException;-><init>(Ljava/lang/String;)V

    throw v0

    :cond_4
    move v0, v1

    goto :goto_1
.end method

.method public read([B)I
    .locals 2
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 200
    const/4 v0, 0x0

    array-length v1, p1

    invoke-virtual {p0, p1, v0, v1}, Lmiui/net/http/a$a;->read([BII)I

    move-result v0

    return v0
.end method

.method public read([BII)I
    .locals 6
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    const/4 v3, -0x1

    const/4 v2, 0x0

    .line 205
    move v1, v2

    .line 206
    :goto_0
    iget v0, p0, Lmiui/net/http/a$a;->Kz:I

    iget-object v4, p0, Lmiui/net/http/a$a;->Ky:Ljava/util/List;

    invoke-interface {v4}, Ljava/util/List;->size()I

    move-result v4

    if-lt v0, v4, :cond_0

    if-nez p3, :cond_4

    .line 207
    :cond_0
    iget-object v0, p0, Lmiui/net/http/a$a;->Ky:Ljava/util/List;

    iget v4, p0, Lmiui/net/http/a$a;->Kz:I

    invoke-interface {v0, v4}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v0

    .line 208
    instance-of v4, v0, [B

    if-eqz v4, :cond_3

    .line 209
    check-cast v0, [B

    check-cast v0, [B

    .line 210
    array-length v4, v0

    iget v5, p0, Lmiui/net/http/a$a;->KA:I

    if-ne v4, v5, :cond_2

    .line 211
    iget v0, p0, Lmiui/net/http/a$a;->Kz:I

    add-int/lit8 v0, v0, 0x1

    iput v0, p0, Lmiui/net/http/a$a;->Kz:I

    .line 212
    iput v2, p0, Lmiui/net/http/a$a;->KA:I

    :cond_1
    :goto_1
    move v0, v1

    :goto_2
    move v1, v0

    .line 241
    goto :goto_0

    .line 214
    :cond_2
    array-length v4, v0

    iget v5, p0, Lmiui/net/http/a$a;->KA:I

    sub-int/2addr v4, v5

    invoke-static {v4, p3}, Ljava/lang/Math;->min(II)I

    move-result v4

    .line 215
    iget v5, p0, Lmiui/net/http/a$a;->KA:I

    invoke-static {v0, v5, p1, p2, v4}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    .line 216
    iget v5, p0, Lmiui/net/http/a$a;->KA:I

    add-int/2addr v5, v4

    iput v5, p0, Lmiui/net/http/a$a;->KA:I

    .line 217
    add-int/2addr p2, v4

    .line 218
    sub-int/2addr p3, v4

    .line 219
    add-int/2addr v1, v4

    .line 220
    array-length v0, v0

    iget v4, p0, Lmiui/net/http/a$a;->KA:I

    if-ne v0, v4, :cond_1

    .line 221
    iget v0, p0, Lmiui/net/http/a$a;->Kz:I

    add-int/lit8 v0, v0, 0x1

    iput v0, p0, Lmiui/net/http/a$a;->Kz:I

    .line 222
    iput v2, p0, Lmiui/net/http/a$a;->KA:I

    goto :goto_1

    .line 225
    :cond_3
    instance-of v4, v0, Ljava/io/InputStream;

    if-eqz v4, :cond_8

    .line 226
    check-cast v0, Ljava/io/InputStream;

    .line 227
    invoke-virtual {v0, p1, p2, p3}, Ljava/io/InputStream;->read([BII)I

    move-result v0

    .line 228
    if-nez v0, :cond_6

    .line 243
    :cond_4
    if-nez v1, :cond_5

    iget v0, p0, Lmiui/net/http/a$a;->Kz:I

    iget-object v2, p0, Lmiui/net/http/a$a;->Ky:Ljava/util/List;

    invoke-interface {v2}, Ljava/util/List;->size()I

    move-result v2

    if-ne v0, v2, :cond_5

    move v1, v3

    :cond_5
    return v1

    .line 230
    :cond_6
    if-ne v0, v3, :cond_7

    .line 231
    iget v0, p0, Lmiui/net/http/a$a;->Kz:I

    add-int/lit8 v0, v0, 0x1

    iput v0, p0, Lmiui/net/http/a$a;->Kz:I

    .line 232
    iput v2, p0, Lmiui/net/http/a$a;->KA:I

    :goto_3
    move v0, v1

    .line 238
    goto :goto_2

    .line 234
    :cond_7
    add-int/2addr p2, v0

    .line 235
    sub-int/2addr p3, v0

    .line 236
    add-int/2addr v1, v0

    goto :goto_3

    .line 239
    :cond_8
    new-instance v0, Ljava/io/IOException;

    const-string v1, "Unexpected value"

    invoke-direct {v0, v1}, Ljava/io/IOException;-><init>(Ljava/lang/String;)V

    throw v0
.end method
