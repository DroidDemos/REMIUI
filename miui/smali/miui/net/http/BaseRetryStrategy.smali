.class public Lmiui/net/http/BaseRetryStrategy;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lmiui/net/http/RetryStrategy;


# static fields
.field public static final DEFAULT_BACKOFF_MULT:F = 1.0f

.field public static final DEFAULT_MAX_RETRIES:I = 0x3

.field public static final DEFAULT_TIMEOUT:I = 0x2710

.field private static Fv:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList",
            "<",
            "Ljava/lang/Class",
            "<*>;>;"
        }
    .end annotation
.end field


# instance fields
.field private Fw:I

.field private Fx:I

.field private final Fy:I

.field private final Fz:F


# direct methods
.method static constructor <clinit>()V
    .locals 2

    .prologue
    .line 37
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    sput-object v0, Lmiui/net/http/BaseRetryStrategy;->Fv:Ljava/util/ArrayList;

    .line 40
    sget-object v0, Lmiui/net/http/BaseRetryStrategy;->Fv:Ljava/util/ArrayList;

    const-class v1, Ljava/io/InterruptedIOException;

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 41
    sget-object v0, Lmiui/net/http/BaseRetryStrategy;->Fv:Ljava/util/ArrayList;

    const-class v1, Ljavax/net/ssl/SSLException;

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 42
    return-void
.end method

.method public constructor <init>()V
    .locals 3

    .prologue
    .line 68
    const/16 v0, 0x2710

    const/4 v1, 0x3

    const/high16 v2, 0x3f800000

    invoke-direct {p0, v0, v1, v2}, Lmiui/net/http/BaseRetryStrategy;-><init>(IIF)V

    .line 69
    return-void
.end method

.method public constructor <init>(IIF)V
    .locals 0

    .prologue
    .line 78
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 79
    iput p1, p0, Lmiui/net/http/BaseRetryStrategy;->Fw:I

    .line 80
    iput p2, p0, Lmiui/net/http/BaseRetryStrategy;->Fy:I

    .line 81
    iput p3, p0, Lmiui/net/http/BaseRetryStrategy;->Fz:F

    .line 82
    return-void
.end method


# virtual methods
.method public getCurrentRetryCount()I
    .locals 1

    .prologue
    .line 91
    iget v0, p0, Lmiui/net/http/BaseRetryStrategy;->Fx:I

    return v0
.end method

.method public getCurrentTimeout()I
    .locals 1

    .prologue
    .line 86
    iget v0, p0, Lmiui/net/http/BaseRetryStrategy;->Fw:I

    return v0
.end method

.method protected hasAttemptRemaining()Z
    .locals 2

    .prologue
    .line 110
    iget v0, p0, Lmiui/net/http/BaseRetryStrategy;->Fx:I

    iget v1, p0, Lmiui/net/http/BaseRetryStrategy;->Fy:I

    if-gt v0, v1, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method protected isUnretryThrowable(Ljava/lang/Throwable;)Z
    .locals 2

    .prologue
    .line 122
    sget-object v0, Lmiui/net/http/BaseRetryStrategy;->Fv:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :cond_0
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_1

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/Class;

    .line 123
    invoke-virtual {v0, p1}, Ljava/lang/Class;->isInstance(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 124
    const/4 v0, 0x1

    .line 127
    :goto_0
    return v0

    :cond_1
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public retry(Ljava/lang/Throwable;)Z
    .locals 3

    .prologue
    .line 96
    iget v0, p0, Lmiui/net/http/BaseRetryStrategy;->Fx:I

    add-int/lit8 v0, v0, 0x1

    iput v0, p0, Lmiui/net/http/BaseRetryStrategy;->Fx:I

    .line 97
    iget v0, p0, Lmiui/net/http/BaseRetryStrategy;->Fw:I

    int-to-float v0, v0

    iget v1, p0, Lmiui/net/http/BaseRetryStrategy;->Fw:I

    int-to-float v1, v1

    iget v2, p0, Lmiui/net/http/BaseRetryStrategy;->Fz:F

    mul-float/2addr v1, v2

    add-float/2addr v0, v1

    float-to-int v0, v0

    iput v0, p0, Lmiui/net/http/BaseRetryStrategy;->Fw:I

    .line 99
    invoke-virtual {p0}, Lmiui/net/http/BaseRetryStrategy;->hasAttemptRemaining()Z

    move-result v0

    if-eqz v0, :cond_0

    invoke-virtual {p0, p1}, Lmiui/net/http/BaseRetryStrategy;->isUnretryThrowable(Ljava/lang/Throwable;)Z

    move-result v0

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method
