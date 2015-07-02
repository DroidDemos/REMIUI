.class Lmiui/net/http/e;
.super Ljava/lang/Object;
.source "SourceFile"


# direct methods
.method constructor <init>()V
    .locals 0

    .prologue
    .line 15
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method public static a(Lmiui/net/http/HttpResponse;)Lmiui/net/http/Cache$Entry;
    .locals 19

    .prologue
    .line 23
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v11

    .line 25
    const-wide/16 v1, 0x0

    .line 26
    const-wide/16 v8, 0x0

    .line 27
    const-wide/16 v6, 0x0

    .line 28
    const/4 v3, 0x0

    .line 29
    const/4 v10, 0x0

    .line 31
    invoke-interface/range {p0 .. p0}, Lmiui/net/http/HttpResponse;->getHeaders()Ljava/util/Map;

    move-result-object v13

    .line 32
    const-string v0, "date"

    invoke-interface {v13, v0}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/String;

    .line 33
    if-eqz v0, :cond_0

    .line 34
    invoke-static {v0}, Lmiui/net/http/e;->v(Ljava/lang/String;)J

    move-result-wide v0

    move-wide v1, v0

    .line 37
    :cond_0
    const-string v0, "cache-control"

    invoke-interface {v13, v0}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/String;

    .line 38
    if-eqz v0, :cond_c

    .line 39
    const/4 v5, 0x1

    .line 40
    const-string v3, ","

    invoke-virtual {v0, v3}, Ljava/lang/String;->split(Ljava/lang/String;)[Ljava/lang/String;

    move-result-object v14

    .line 41
    array-length v15, v14

    const/4 v0, 0x0

    move-wide v3, v6

    :goto_0
    if-ge v0, v15, :cond_6

    aget-object v6, v14, v0

    .line 42
    invoke-virtual {v6}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v6

    .line 43
    const-string v7, "no-cache"

    invoke-virtual {v6, v7}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v7

    if-nez v7, :cond_1

    const-string v7, "no-store"

    invoke-virtual {v6, v7}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v7

    if-eqz v7, :cond_2

    .line 44
    :cond_1
    const/4 v0, 0x0

    .line 87
    :goto_1
    return-object v0

    .line 45
    :cond_2
    const-string v7, "max-age="

    invoke-virtual {v6, v7}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v7

    if-eqz v7, :cond_4

    .line 47
    const/16 v7, 0x8

    :try_start_0
    invoke-virtual {v6, v7}, Ljava/lang/String;->substring(I)Ljava/lang/String;

    move-result-object v6

    invoke-static {v6}, Ljava/lang/Long;->parseLong(Ljava/lang/String;)J
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    move-result-wide v3

    .line 41
    :cond_3
    :goto_2
    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    .line 51
    :cond_4
    const-string v7, "must-revalidate"

    invoke-virtual {v6, v7}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v7

    if-nez v7, :cond_5

    const-string v7, "proxy-revalidate"

    invoke-virtual {v6, v7}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v6

    if-eqz v6, :cond_3

    .line 52
    :cond_5
    const-wide/16 v3, 0x0

    goto :goto_2

    :cond_6
    move/from16 v16, v5

    move-wide/from16 v17, v3

    move-wide/from16 v4, v17

    move/from16 v3, v16

    .line 57
    :goto_3
    const-string v0, "expires"

    invoke-interface {v13, v0}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/String;

    .line 58
    if-eqz v0, :cond_b

    .line 59
    invoke-static {v0}, Lmiui/net/http/e;->v(Ljava/lang/String;)J

    move-result-wide v6

    .line 62
    :goto_4
    const-string v0, "etag"

    invoke-interface {v13, v0}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/String;

    .line 63
    if-eqz v0, :cond_a

    move-object v8, v0

    .line 68
    :goto_5
    if-eqz v3, :cond_7

    .line 69
    const-wide/16 v6, 0x3e8

    mul-long v3, v4, v6

    add-long/2addr v3, v11

    .line 76
    :goto_6
    invoke-interface/range {p0 .. p0}, Lmiui/net/http/HttpResponse;->getContent()Ljava/io/InputStream;

    move-result-object v0

    if-eqz v0, :cond_9

    .line 77
    new-instance v0, Lmiui/net/http/Cache$Entry;

    invoke-direct {v0}, Lmiui/net/http/Cache$Entry;-><init>()V

    .line 78
    invoke-interface/range {p0 .. p0}, Lmiui/net/http/HttpResponse;->getContent()Ljava/io/InputStream;

    move-result-object v5

    iput-object v5, v0, Lmiui/net/http/Cache$Entry;->data:Ljava/io/InputStream;

    .line 79
    invoke-interface/range {p0 .. p0}, Lmiui/net/http/HttpResponse;->getContentLength()J

    move-result-wide v5

    iput-wide v5, v0, Lmiui/net/http/Cache$Entry;->length:J

    .line 80
    iput-object v8, v0, Lmiui/net/http/Cache$Entry;->etag:Ljava/lang/String;

    .line 81
    iput-wide v3, v0, Lmiui/net/http/Cache$Entry;->softTtl:J

    .line 82
    iput-wide v3, v0, Lmiui/net/http/Cache$Entry;->ttl:J

    .line 83
    iput-wide v1, v0, Lmiui/net/http/Cache$Entry;->serverDate:J

    .line 84
    invoke-interface/range {p0 .. p0}, Lmiui/net/http/HttpResponse;->getHeaders()Ljava/util/Map;

    move-result-object v1

    iput-object v1, v0, Lmiui/net/http/Cache$Entry;->responseHeaders:Ljava/util/Map;

    goto :goto_1

    .line 70
    :cond_7
    const-wide/16 v3, 0x0

    cmp-long v0, v1, v3

    if-lez v0, :cond_8

    cmp-long v0, v6, v1

    if-ltz v0, :cond_8

    .line 71
    sub-long v3, v6, v1

    add-long/2addr v3, v11

    goto :goto_6

    .line 73
    :cond_8
    const/4 v0, 0x0

    goto/16 :goto_1

    .line 87
    :cond_9
    const/4 v0, 0x0

    goto/16 :goto_1

    .line 48
    :catch_0
    move-exception v6

    goto :goto_2

    :cond_a
    move-object v8, v10

    goto :goto_5

    :cond_b
    move-wide v6, v8

    goto :goto_4

    :cond_c
    move-wide v4, v6

    goto :goto_3
.end method

.method public static v(Ljava/lang/String;)J
    .locals 2

    .prologue
    .line 97
    :try_start_0
    invoke-static {p0}, Lorg/apache/http/impl/cookie/DateUtils;->parseDate(Ljava/lang/String;)Ljava/util/Date;

    move-result-object v0

    invoke-virtual {v0}, Ljava/util/Date;->getTime()J
    :try_end_0
    .catch Lorg/apache/http/impl/cookie/DateParseException; {:try_start_0 .. :try_end_0} :catch_0

    move-result-wide v0

    .line 99
    :goto_0
    return-wide v0

    .line 98
    :catch_0
    move-exception v0

    .line 99
    const-wide/16 v0, 0x0

    goto :goto_0
.end method
