.class public Lmiui/text/ExtraTextUtils;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field public static final GB:J = 0x3b9aca00L

.field public static final KB:J = 0x3e8L

.field public static final MB:J = 0xf4240L

.field private static final uc:J = 0x3e8L

.field private static final ud:[C


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .prologue
    .line 25
    const/16 v0, 0x10

    new-array v0, v0, [C

    fill-array-data v0, :array_0

    sput-object v0, Lmiui/text/ExtraTextUtils;->ud:[C

    return-void

    :array_0
    .array-data 2
        0x30s
        0x31s
        0x32s
        0x33s
        0x34s
        0x35s
        0x36s
        0x37s
        0x38s
        0x39s
        0x61s
        0x62s
        0x63s
        0x64s
        0x65s
        0x66s
    .end array-data
.end method

.method protected constructor <init>()V
    .locals 2
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/InstantiationException;
        }
    .end annotation

    .prologue
    .line 32
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 33
    new-instance v0, Ljava/lang/InstantiationException;

    const-string v1, "Cannot instantiate utility class"

    invoke-direct {v0, v1}, Ljava/lang/InstantiationException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

.method private static a(Landroid/content/Context;JZ)Ljava/lang/String;
    .locals 9

    .prologue
    const/16 v7, 0x30

    const/high16 v4, 0x447a0000

    const/high16 v3, 0x44610000

    const/4 v6, 0x1

    const/4 v5, 0x0

    .line 145
    if-nez p0, :cond_0

    .line 146
    const-string v0, ""

    .line 199
    :goto_0
    return-object v0

    .line 149
    :cond_0
    long-to-float v1, p1

    .line 150
    sget v0, Lcom/miui/internal/R$string;->size_byte:I

    .line 151
    cmpl-float v2, v1, v3

    if-lez v2, :cond_1

    .line 152
    sget v0, Lcom/miui/internal/R$string;->size_kilo_byte:I

    .line 153
    div-float/2addr v1, v4

    .line 155
    :cond_1
    cmpl-float v2, v1, v3

    if-lez v2, :cond_2

    .line 156
    sget v0, Lcom/miui/internal/R$string;->size_mega_byte:I

    .line 157
    div-float/2addr v1, v4

    .line 159
    :cond_2
    cmpl-float v2, v1, v3

    if-lez v2, :cond_3

    .line 160
    sget v0, Lcom/miui/internal/R$string;->size_giga_byte:I

    .line 161
    div-float/2addr v1, v4

    .line 163
    :cond_3
    cmpl-float v2, v1, v3

    if-lez v2, :cond_4

    .line 164
    sget v0, Lcom/miui/internal/R$string;->size_tera_byte:I

    .line 165
    div-float/2addr v1, v4

    .line 167
    :cond_4
    cmpl-float v2, v1, v3

    if-lez v2, :cond_c

    .line 168
    sget v0, Lcom/miui/internal/R$string;->size_peta_byte:I

    .line 169
    div-float/2addr v1, v4

    move v8, v0

    move v0, v1

    move v1, v8

    .line 173
    :goto_1
    const/high16 v2, 0x3f800000

    cmpg-float v2, v0, v2

    if-gez v2, :cond_6

    .line 174
    const-string v2, "%.2f"

    new-array v3, v6, [Ljava/lang/Object;

    invoke-static {v0}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object v0

    aput-object v0, v3, v5

    invoke-static {v2, v3}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    .line 191
    :goto_2
    invoke-virtual {v0}, Ljava/lang/String;->length()I

    move-result v2

    .line 192
    const/4 v3, 0x3

    if-le v2, v3, :cond_b

    add-int/lit8 v3, v2, -0x3

    invoke-virtual {v0, v3}, Ljava/lang/String;->charAt(I)C

    move-result v3

    const/16 v4, 0x2e

    if-ne v3, v4, :cond_b

    add-int/lit8 v3, v2, -0x2

    invoke-virtual {v0, v3}, Ljava/lang/String;->charAt(I)C

    move-result v3

    if-ne v3, v7, :cond_b

    add-int/lit8 v3, v2, -0x1

    invoke-virtual {v0, v3}, Ljava/lang/String;->charAt(I)C

    move-result v3

    if-ne v3, v7, :cond_b

    .line 194
    add-int/lit8 v2, v2, -0x3

    invoke-virtual {v0, v5, v2}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object v0

    .line 199
    :cond_5
    :goto_3
    invoke-virtual {p0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v2

    sget v3, Lcom/miui/internal/R$string;->size_suffix:I

    const/4 v4, 0x2

    new-array v4, v4, [Ljava/lang/Object;

    aput-object v0, v4, v5

    invoke-virtual {p0, v1}, Landroid/content/Context;->getString(I)Ljava/lang/String;

    move-result-object v0

    aput-object v0, v4, v6

    invoke-virtual {v2, v3, v4}, Landroid/content/res/Resources;->getString(I[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    goto :goto_0

    .line 175
    :cond_6
    const/high16 v2, 0x41200000

    cmpg-float v2, v0, v2

    if-gez v2, :cond_8

    .line 176
    if-eqz p3, :cond_7

    .line 177
    const-string v2, "%.1f"

    new-array v3, v6, [Ljava/lang/Object;

    invoke-static {v0}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object v0

    aput-object v0, v3, v5

    invoke-static {v2, v3}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    goto :goto_2

    .line 179
    :cond_7
    const-string v2, "%.2f"

    new-array v3, v6, [Ljava/lang/Object;

    invoke-static {v0}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object v0

    aput-object v0, v3, v5

    invoke-static {v2, v3}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    goto :goto_2

    .line 181
    :cond_8
    const/high16 v2, 0x42c80000

    cmpg-float v2, v0, v2

    if-gez v2, :cond_a

    .line 182
    if-eqz p3, :cond_9

    .line 183
    const-string v2, "%.0f"

    new-array v3, v6, [Ljava/lang/Object;

    invoke-static {v0}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object v0

    aput-object v0, v3, v5

    invoke-static {v2, v3}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    goto :goto_2

    .line 185
    :cond_9
    const-string v2, "%.2f"

    new-array v3, v6, [Ljava/lang/Object;

    invoke-static {v0}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object v0

    aput-object v0, v3, v5

    invoke-static {v2, v3}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    goto/16 :goto_2

    .line 188
    :cond_a
    const-string v2, "%.0f"

    new-array v3, v6, [Ljava/lang/Object;

    invoke-static {v0}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object v0

    aput-object v0, v3, v5

    invoke-static {v2, v3}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    goto/16 :goto_2

    .line 195
    :cond_b
    const/4 v3, 0x2

    if-le v2, v3, :cond_5

    add-int/lit8 v3, v2, -0x2

    invoke-virtual {v0, v3}, Ljava/lang/String;->charAt(I)C

    move-result v3

    const/16 v4, 0x2e

    if-ne v3, v4, :cond_5

    add-int/lit8 v3, v2, -0x1

    invoke-virtual {v0, v3}, Ljava/lang/String;->charAt(I)C

    move-result v3

    if-ne v3, v7, :cond_5

    .line 197
    add-int/lit8 v2, v2, -0x2

    invoke-virtual {v0, v5, v2}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object v0

    goto/16 :goto_3

    :cond_c
    move v8, v0

    move v0, v1

    move v1, v8

    goto/16 :goto_1
.end method

.method public static formatFileSize(Landroid/content/Context;J)Ljava/lang/String;
    .locals 1

    .prologue
    .line 133
    const/4 v0, 0x0

    invoke-static {p0, p1, p2, v0}, Lmiui/text/ExtraTextUtils;->a(Landroid/content/Context;JZ)Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method public static formatShortFileSize(Landroid/content/Context;J)Ljava/lang/String;
    .locals 1

    .prologue
    .line 141
    const/4 v0, 0x1

    invoke-static {p0, p1, p2, v0}, Lmiui/text/ExtraTextUtils;->a(Landroid/content/Context;JZ)Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method public static fromHexReadable(Ljava/lang/String;)[B
    .locals 11

    .prologue
    const/16 v10, 0x61

    const/16 v9, 0x46

    const/16 v8, 0x41

    const/16 v7, 0x39

    const/16 v6, 0x30

    .line 89
    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result v2

    .line 90
    rem-int/lit8 v0, v2, 0x2

    if-eqz v0, :cond_0

    .line 91
    new-instance v0, Ljava/lang/IllegalArgumentException;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "s is not a readable string: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 94
    :cond_0
    shr-int/lit8 v0, v2, 0x1

    new-array v3, v0, [B

    .line 95
    const/4 v0, 0x0

    move v1, v0

    :goto_0
    if-ge v1, v2, :cond_7

    .line 96
    invoke-virtual {p0, v1}, Ljava/lang/String;->charAt(I)C

    move-result v0

    .line 98
    if-lt v0, v6, :cond_1

    if-gt v0, v7, :cond_1

    .line 99
    add-int/lit8 v0, v0, -0x30

    .line 107
    :goto_1
    shl-int/lit8 v0, v0, 0x4

    .line 109
    add-int/lit8 v4, v1, 0x1

    invoke-virtual {p0, v4}, Ljava/lang/String;->charAt(I)C

    move-result v4

    .line 110
    if-lt v4, v6, :cond_4

    if-gt v4, v7, :cond_4

    .line 111
    add-int/lit8 v4, v4, -0x30

    add-int/2addr v0, v4

    .line 120
    :goto_2
    shr-int/lit8 v4, v1, 0x1

    int-to-byte v0, v0

    aput-byte v0, v3, v4

    .line 95
    add-int/lit8 v0, v1, 0x2

    move v1, v0

    goto :goto_0

    .line 100
    :cond_1
    if-lt v0, v10, :cond_2

    const/16 v4, 0x66

    if-gt v0, v4, :cond_2

    .line 101
    add-int/lit8 v0, v0, -0x61

    add-int/lit8 v0, v0, 0xa

    goto :goto_1

    .line 102
    :cond_2
    if-lt v0, v8, :cond_3

    if-gt v0, v9, :cond_3

    .line 103
    add-int/lit8 v0, v0, -0x41

    add-int/lit8 v0, v0, 0xa

    goto :goto_1

    .line 105
    :cond_3
    new-instance v0, Ljava/lang/IllegalArgumentException;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "s is not a readable string: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 112
    :cond_4
    if-lt v4, v10, :cond_5

    const/16 v5, 0x66

    if-gt v4, v5, :cond_5

    .line 113
    add-int/lit8 v4, v4, -0x61

    add-int/lit8 v4, v4, 0xa

    add-int/2addr v0, v4

    goto :goto_2

    .line 114
    :cond_5
    if-lt v4, v8, :cond_6

    if-gt v4, v9, :cond_6

    .line 115
    add-int/lit8 v4, v4, -0x41

    add-int/lit8 v4, v4, 0xa

    add-int/2addr v0, v4

    goto :goto_2

    .line 117
    :cond_6
    new-instance v0, Ljava/lang/IllegalArgumentException;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "s is not a readable string: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 122
    :cond_7
    return-object v3
.end method

.method public static toHexReadable([B)Ljava/lang/String;
    .locals 3

    .prologue
    .line 74
    invoke-static {}, Lmiui/util/Pools;->getStringBuilderPool()Lmiui/util/Pools$Pool;

    move-result-object v0

    invoke-interface {v0}, Lmiui/util/Pools$Pool;->acquire()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/StringBuilder;

    .line 75
    invoke-static {p0, v0}, Lmiui/text/ExtraTextUtils;->toHexReadable([BLjava/lang/Appendable;)V

    .line 76
    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    .line 77
    invoke-static {}, Lmiui/util/Pools;->getStringBuilderPool()Lmiui/util/Pools$Pool;

    move-result-object v2

    invoke-interface {v2, v0}, Lmiui/util/Pools$Pool;->release(Ljava/lang/Object;)V

    .line 78
    return-object v1
.end method

.method public static toHexReadable([BLjava/lang/Appendable;)V
    .locals 5

    .prologue
    .line 46
    if-nez p0, :cond_1

    .line 64
    :cond_0
    return-void

    .line 51
    :cond_1
    :try_start_0
    array-length v2, p0

    const/4 v0, 0x0

    move v1, v0

    :goto_0
    if-ge v1, v2, :cond_0

    aget-byte v0, p0, v1

    .line 53
    if-gez v0, :cond_2

    .line 54
    add-int/lit16 v0, v0, 0x100

    .line 57
    :cond_2
    shr-int/lit8 v3, v0, 0x4

    .line 58
    and-int/lit8 v0, v0, 0xf

    .line 59
    sget-object v4, Lmiui/text/ExtraTextUtils;->ud:[C

    aget-char v3, v4, v3

    invoke-interface {p1, v3}, Ljava/lang/Appendable;->append(C)Ljava/lang/Appendable;

    move-result-object v3

    sget-object v4, Lmiui/text/ExtraTextUtils;->ud:[C

    aget-char v0, v4, v0

    invoke-interface {v3, v0}, Ljava/lang/Appendable;->append(C)Ljava/lang/Appendable;
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_0

    .line 51
    add-int/lit8 v0, v1, 0x1

    move v1, v0

    goto :goto_0

    .line 61
    :catch_0
    move-exception v0

    .line 62
    new-instance v1, Ljava/lang/RuntimeException;

    const-string v2, "Exception throw during when append"

    invoke-direct {v1, v2, v0}, Ljava/lang/RuntimeException;-><init>(Ljava/lang/String;Ljava/lang/Throwable;)V

    throw v1
.end method
