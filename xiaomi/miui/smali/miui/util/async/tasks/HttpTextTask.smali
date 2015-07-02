.class public abstract Lmiui/util/async/tasks/HttpTextTask;
.super Lmiui/util/async/tasks/HttpTask;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "<T:",
        "Ljava/lang/Object;",
        ">",
        "Lmiui/util/async/tasks/HttpTask",
        "<TT;>;"
    }
.end annotation


# direct methods
.method public constructor <init>(Ljava/lang/String;)V
    .locals 2

    .prologue
    const/4 v1, 0x0

    .line 25
    sget-object v0, Lmiui/util/async/tasks/HttpTask$Method;->Get:Lmiui/util/async/tasks/HttpTask$Method;

    invoke-direct {p0, v1, v0, p1, v1}, Lmiui/util/async/tasks/HttpTextTask;-><init>(Lmiui/net/http/HttpSession;Lmiui/util/async/tasks/HttpTask$Method;Ljava/lang/String;Ljava/util/Map;)V

    .line 26
    return-void
.end method

.method public constructor <init>(Lmiui/net/http/HttpSession;Ljava/lang/String;)V
    .locals 2

    .prologue
    .line 35
    sget-object v0, Lmiui/util/async/tasks/HttpTask$Method;->Get:Lmiui/util/async/tasks/HttpTask$Method;

    const/4 v1, 0x0

    invoke-direct {p0, p1, v0, p2, v1}, Lmiui/util/async/tasks/HttpTextTask;-><init>(Lmiui/net/http/HttpSession;Lmiui/util/async/tasks/HttpTask$Method;Ljava/lang/String;Ljava/util/Map;)V

    .line 36
    return-void
.end method

.method public constructor <init>(Lmiui/net/http/HttpSession;Lmiui/util/async/tasks/HttpTask$Method;Ljava/lang/String;Ljava/util/Map;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lmiui/net/http/HttpSession;",
            "Lmiui/util/async/tasks/HttpTask$Method;",
            "Ljava/lang/String;",
            "Ljava/util/Map",
            "<",
            "Ljava/lang/String;",
            "Ljava/lang/String;",
            ">;)V"
        }
    .end annotation

    .prologue
    .line 47
    invoke-direct {p0, p1, p2, p3, p4}, Lmiui/util/async/tasks/HttpTask;-><init>(Lmiui/net/http/HttpSession;Lmiui/util/async/tasks/HttpTask$Method;Ljava/lang/String;Ljava/util/Map;)V

    .line 48
    return-void
.end method

.method static c([B)Ljava/lang/String;
    .locals 6

    .prologue
    const/16 v0, 0x1f4

    const/16 v5, 0xa

    const/4 v1, 0x0

    .line 77
    const/4 v2, 0x0

    .line 79
    new-instance v3, Ljava/lang/String;

    array-length v4, p0

    if-le v4, v0, :cond_4

    :goto_0
    const-string v4, "UTF-8"

    invoke-static {v4}, Ljava/nio/charset/Charset;->forName(Ljava/lang/String;)Ljava/nio/charset/Charset;

    move-result-object v4

    invoke-direct {v3, p0, v1, v0, v4}, Ljava/lang/String;-><init>([BIILjava/nio/charset/Charset;)V

    invoke-virtual {v3}, Ljava/lang/String;->toUpperCase()Ljava/lang/String;

    move-result-object v3

    .line 82
    const-string v0, "CONTENT-TYPE"

    invoke-virtual {v3, v0}, Ljava/lang/String;->indexOf(Ljava/lang/String;)I

    move-result v0

    .line 83
    if-ltz v0, :cond_7

    .line 84
    invoke-virtual {v3, v5}, Ljava/lang/String;->lastIndexOf(I)I

    move-result v0

    .line 85
    if-gez v0, :cond_0

    move v0, v1

    .line 88
    :cond_0
    invoke-virtual {v3, v5}, Ljava/lang/String;->indexOf(I)I

    move-result v1

    .line 89
    if-gez v1, :cond_1

    .line 90
    invoke-virtual {v3}, Ljava/lang/String;->length()I

    move-result v1

    .line 92
    :cond_1
    invoke-virtual {v3, v0, v1}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object v0

    .line 95
    :goto_1
    if-eqz v0, :cond_6

    .line 96
    invoke-virtual {v0}, Ljava/lang/String;->toUpperCase()Ljava/lang/String;

    move-result-object v1

    .line 97
    const-string v0, "CHARSET="

    invoke-virtual {v1, v0}, Ljava/lang/String;->indexOf(Ljava/lang/String;)I

    move-result v0

    .line 98
    if-ltz v0, :cond_6

    .line 99
    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    .line 100
    add-int/lit8 v0, v0, 0x8

    :goto_2
    invoke-virtual {v1}, Ljava/lang/String;->length()I

    move-result v3

    if-ge v0, v3, :cond_5

    .line 101
    invoke-virtual {v1, v0}, Ljava/lang/String;->charAt(I)C

    move-result v3

    .line 102
    const/16 v4, 0x27

    if-eq v3, v4, :cond_2

    const/16 v4, 0x22

    if-eq v3, v4, :cond_2

    const/16 v4, 0x20

    if-ne v3, v4, :cond_3

    .line 103
    :cond_2
    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    .line 100
    :cond_3
    add-int/lit8 v0, v0, 0x1

    goto :goto_2

    .line 79
    :cond_4
    array-length v0, p0

    goto :goto_0

    .line 106
    :cond_5
    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    .line 110
    :goto_3
    return-object v0

    :cond_6
    const-string v0, "UTF-8"

    goto :goto_3

    :cond_7
    move-object v0, v2

    goto :goto_1
.end method


# virtual methods
.method protected final requestText()Ljava/lang/String;
    .locals 3
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/Exception;
        }
    .end annotation

    .prologue
    .line 56
    invoke-virtual {p0}, Lmiui/util/async/tasks/HttpTextTask;->request()Lmiui/net/http/HttpResponse;

    move-result-object v0

    .line 57
    invoke-interface {v0}, Lmiui/net/http/HttpResponse;->getContentEncoding()Ljava/lang/String;

    move-result-object v1

    .line 59
    if-eqz v1, :cond_0

    invoke-virtual {v1}, Ljava/lang/String;->length()I

    move-result v2

    if-lez v2, :cond_0

    .line 60
    invoke-interface {v0}, Lmiui/net/http/HttpResponse;->getContent()Ljava/io/InputStream;

    move-result-object v0

    invoke-virtual {v1}, Ljava/lang/String;->toUpperCase()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Lmiui/util/IOUtils;->toString(Ljava/io/InputStream;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 66
    :goto_0
    return-object v0

    .line 62
    :cond_0
    invoke-interface {v0}, Lmiui/net/http/HttpResponse;->getContent()Ljava/io/InputStream;

    move-result-object v0

    invoke-static {v0}, Lmiui/util/IOUtils;->toByteArray(Ljava/io/InputStream;)[B

    move-result-object v1

    .line 63
    invoke-static {v1}, Lmiui/util/async/tasks/HttpTextTask;->c([B)Ljava/lang/String;

    move-result-object v2

    .line 64
    new-instance v0, Ljava/lang/String;

    invoke-static {v2}, Ljava/nio/charset/Charset;->forName(Ljava/lang/String;)Ljava/nio/charset/Charset;

    move-result-object v2

    invoke-direct {v0, v1, v2}, Ljava/lang/String;-><init>([BLjava/nio/charset/Charset;)V

    goto :goto_0
.end method
