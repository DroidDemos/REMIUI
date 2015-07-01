.class Lmiui/util/b;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field static final al:[I

.field static final an:[J

.field static final ao:[Ljava/lang/Object;


# direct methods
.method static constructor <clinit>()V
    .locals 2

    .prologue
    const/4 v1, 0x0

    .line 8
    new-array v0, v1, [I

    sput-object v0, Lmiui/util/b;->al:[I

    .line 9
    new-array v0, v1, [J

    sput-object v0, Lmiui/util/b;->an:[J

    .line 10
    new-array v0, v1, [Ljava/lang/Object;

    sput-object v0, Lmiui/util/b;->ao:[Ljava/lang/Object;

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
    .line 16
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 17
    new-instance v0, Ljava/lang/InstantiationException;

    const-string v1, "Cannot instantiate utility class"

    invoke-direct {v0, v1}, Ljava/lang/InstantiationException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

.method static a([III)I
    .locals 4

    .prologue
    .line 42
    const/4 v1, 0x0

    .line 43
    add-int/lit8 v0, p1, -0x1

    .line 45
    :goto_0
    if-gt v1, v0, :cond_2

    .line 46
    add-int v2, v1, v0

    ushr-int/lit8 v2, v2, 0x1

    .line 47
    aget v3, p0, v2

    .line 49
    if-ge v3, p2, :cond_0

    .line 50
    add-int/lit8 v1, v2, 0x1

    goto :goto_0

    .line 51
    :cond_0
    if-le v3, p2, :cond_1

    .line 52
    add-int/lit8 v0, v2, -0x1

    goto :goto_0

    :cond_1
    move v0, v2

    .line 57
    :goto_1
    return v0

    :cond_2
    xor-int/lit8 v0, v1, -0x1

    goto :goto_1
.end method

.method static a([JIJ)I
    .locals 6

    .prologue
    .line 61
    const/4 v1, 0x0

    .line 62
    add-int/lit8 v0, p1, -0x1

    .line 64
    :goto_0
    if-gt v1, v0, :cond_2

    .line 65
    add-int v2, v1, v0

    ushr-int/lit8 v2, v2, 0x1

    .line 66
    aget-wide v3, p0, v2

    .line 68
    cmp-long v5, v3, p2

    if-gez v5, :cond_0

    .line 69
    add-int/lit8 v1, v2, 0x1

    goto :goto_0

    .line 70
    :cond_0
    cmp-long v0, v3, p2

    if-lez v0, :cond_1

    .line 71
    add-int/lit8 v0, v2, -0x1

    goto :goto_0

    :cond_1
    move v0, v2

    .line 76
    :goto_1
    return v0

    :cond_2
    xor-int/lit8 v0, v1, -0x1

    goto :goto_1
.end method

.method public static a(Ljava/lang/Object;Ljava/lang/Object;)Z
    .locals 1

    .prologue
    .line 37
    if-eq p0, p1, :cond_0

    if-eqz p0, :cond_1

    invoke-virtual {p0, p1}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_1

    :cond_0
    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_1
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public static d(I)I
    .locals 1

    .prologue
    .line 21
    mul-int/lit8 v0, p0, 0x4

    invoke-static {v0}, Lmiui/util/b;->f(I)I

    move-result v0

    div-int/lit8 v0, v0, 0x4

    return v0
.end method

.method public static e(I)I
    .locals 1

    .prologue
    .line 25
    mul-int/lit8 v0, p0, 0x8

    invoke-static {v0}, Lmiui/util/b;->f(I)I

    move-result v0

    div-int/lit8 v0, v0, 0x8

    return v0
.end method

.method public static f(I)I
    .locals 3

    .prologue
    const/4 v2, 0x1

    .line 29
    const/4 v0, 0x4

    :goto_0
    const/16 v1, 0x20

    if-ge v0, v1, :cond_0

    .line 30
    shl-int v1, v2, v0

    add-int/lit8 v1, v1, -0xc

    if-gt p0, v1, :cond_1

    .line 31
    shl-int v0, v2, v0

    add-int/lit8 p0, v0, -0xc

    .line 33
    :cond_0
    return p0

    .line 29
    :cond_1
    add-int/lit8 v0, v0, 0x1

    goto :goto_0
.end method
