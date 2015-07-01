.class public Lmiui/net/http/HttpRequestParams;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lmiui/net/http/HttpRequestParams$a;
    }
.end annotation


# static fields
.field public static DEFAULT_ENCODING:Ljava/lang/String;


# instance fields
.field private OQ:Ljava/util/concurrent/ConcurrentHashMap;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/concurrent/ConcurrentHashMap",
            "<",
            "Ljava/lang/String;",
            "Ljava/lang/Object;",
            ">;"
        }
    .end annotation
.end field

.field private OR:Z

.field private dT:Ljava/lang/String;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .prologue
    .line 32
    const-string v0, "UTF-8"

    sput-object v0, Lmiui/net/http/HttpRequestParams;->DEFAULT_ENCODING:Ljava/lang/String;

    return-void
.end method

.method public constructor <init>()V
    .locals 1

    .prologue
    .line 52
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 53
    sget-object v0, Lmiui/net/http/HttpRequestParams;->DEFAULT_ENCODING:Ljava/lang/String;

    iput-object v0, p0, Lmiui/net/http/HttpRequestParams;->dT:Ljava/lang/String;

    .line 54
    new-instance v0, Ljava/util/concurrent/ConcurrentHashMap;

    invoke-direct {v0}, Ljava/util/concurrent/ConcurrentHashMap;-><init>()V

    iput-object v0, p0, Lmiui/net/http/HttpRequestParams;->OQ:Ljava/util/concurrent/ConcurrentHashMap;

    .line 55
    const/4 v0, 0x0

    iput-boolean v0, p0, Lmiui/net/http/HttpRequestParams;->OR:Z

    .line 56
    return-void
.end method

.method public varargs constructor <init>([Ljava/lang/Object;)V
    .locals 5

    .prologue
    .line 63
    invoke-direct {p0}, Lmiui/net/http/HttpRequestParams;-><init>()V

    .line 64
    array-length v3, p1

    .line 65
    rem-int/lit8 v0, v3, 0x2

    if-eqz v0, :cond_0

    .line 66
    new-instance v0, Ljava/lang/IllegalArgumentException;

    const-string v1, "Supplied argument must be even"

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 69
    :cond_0
    const/4 v0, 0x0

    move v2, v0

    :goto_0
    if-ge v2, v3, :cond_5

    .line 70
    aget-object v0, p1, v2

    instance-of v0, v0, Ljava/lang/String;

    if-nez v0, :cond_1

    .line 71
    new-instance v0, Ljava/lang/IllegalArgumentException;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "Unknown argument name : "

    invoke-virtual {v1, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    aget-object v2, p1, v2

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 74
    :cond_1
    aget-object v0, p1, v2

    check-cast v0, Ljava/lang/String;

    .line 75
    add-int/lit8 v1, v2, 0x1

    aget-object v1, p1, v1

    .line 76
    instance-of v4, v1, Ljava/lang/String;

    if-eqz v4, :cond_2

    .line 77
    check-cast v1, Ljava/lang/String;

    invoke-virtual {p0, v0, v1}, Lmiui/net/http/HttpRequestParams;->add(Ljava/lang/String;Ljava/lang/String;)Lmiui/net/http/HttpRequestParams;

    .line 69
    :goto_1
    add-int/lit8 v0, v2, 0x2

    move v2, v0

    goto :goto_0

    .line 78
    :cond_2
    instance-of v4, v1, Ljava/io/File;

    if-eqz v4, :cond_3

    .line 79
    check-cast v1, Ljava/io/File;

    invoke-virtual {p0, v0, v1}, Lmiui/net/http/HttpRequestParams;->add(Ljava/lang/String;Ljava/io/File;)Lmiui/net/http/HttpRequestParams;

    goto :goto_1

    .line 80
    :cond_3
    instance-of v4, v1, Ljava/util/List;

    if-eqz v4, :cond_4

    .line 82
    check-cast v1, Ljava/util/List;

    .line 83
    invoke-virtual {p0, v0, v1}, Lmiui/net/http/HttpRequestParams;->add(Ljava/lang/String;Ljava/util/List;)Lmiui/net/http/HttpRequestParams;

    goto :goto_1

    .line 85
    :cond_4
    new-instance v0, Ljava/lang/IllegalArgumentException;

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "Unknown argument type : "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 88
    :cond_5
    return-void
.end method

.method private ew()Ljava/util/List;
    .locals 7
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/List",
            "<",
            "Lorg/apache/http/message/BasicNameValuePair;",
            ">;"
        }
    .end annotation

    .prologue
    .line 253
    new-instance v3, Ljava/util/LinkedList;

    invoke-direct {v3}, Ljava/util/LinkedList;-><init>()V

    .line 255
    iget-object v0, p0, Lmiui/net/http/HttpRequestParams;->OQ:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {v0}, Ljava/util/concurrent/ConcurrentHashMap;->entrySet()Ljava/util/Set;

    move-result-object v0

    invoke-interface {v0}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v4

    :cond_0
    :goto_0
    invoke-interface {v4}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_2

    invoke-interface {v4}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/util/Map$Entry;

    .line 256
    invoke-interface {v0}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v1

    .line 257
    instance-of v2, v1, Ljava/lang/String;

    if-eqz v2, :cond_1

    .line 258
    new-instance v2, Lorg/apache/http/message/BasicNameValuePair;

    invoke-interface {v0}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/String;

    check-cast v1, Ljava/lang/String;

    invoke-direct {v2, v0, v1}, Lorg/apache/http/message/BasicNameValuePair;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-interface {v3, v2}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto :goto_0

    .line 259
    :cond_1
    instance-of v2, v1, Ljava/util/List;

    if-eqz v2, :cond_0

    .line 260
    check-cast v1, Ljava/util/List;

    invoke-interface {v1}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v5

    :goto_1
    invoke-interface {v5}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_0

    invoke-interface {v5}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    .line 261
    new-instance v6, Lorg/apache/http/message/BasicNameValuePair;

    invoke-interface {v0}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/lang/String;

    check-cast v2, Ljava/lang/String;

    invoke-direct {v6, v1, v2}, Lorg/apache/http/message/BasicNameValuePair;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-interface {v3, v6}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto :goto_1

    .line 265
    :cond_2
    return-object v3
.end method


# virtual methods
.method public add(Ljava/lang/String;Ljava/io/File;)Lmiui/net/http/HttpRequestParams;
    .locals 7

    .prologue
    .line 121
    if-eqz p1, :cond_0

    if-eqz p2, :cond_0

    .line 123
    :try_start_0
    iget-object v6, p0, Lmiui/net/http/HttpRequestParams;->OQ:Ljava/util/concurrent/ConcurrentHashMap;

    new-instance v0, Lmiui/net/http/HttpRequestParams$a;

    new-instance v1, Ljava/io/FileInputStream;

    invoke-direct {v1, p2}, Ljava/io/FileInputStream;-><init>(Ljava/io/File;)V

    invoke-virtual {p2}, Ljava/io/File;->length()J

    move-result-wide v2

    invoke-virtual {p2}, Ljava/io/File;->getName()Ljava/lang/String;

    move-result-object v4

    const/4 v5, 0x0

    invoke-direct/range {v0 .. v5}, Lmiui/net/http/HttpRequestParams$a;-><init>(Ljava/io/InputStream;JLjava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v6, p1, v0}, Ljava/util/concurrent/ConcurrentHashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 124
    const/4 v0, 0x1

    iput-boolean v0, p0, Lmiui/net/http/HttpRequestParams;->OR:Z
    :try_end_0
    .catch Ljava/io/FileNotFoundException; {:try_start_0 .. :try_end_0} :catch_0

    .line 129
    :cond_0
    return-object p0

    .line 125
    :catch_0
    move-exception v0

    .line 126
    new-instance v1, Ljava/lang/IllegalArgumentException;

    invoke-virtual {v0}, Ljava/io/FileNotFoundException;->getMessage()Ljava/lang/String;

    move-result-object v2

    invoke-direct {v1, v2, v0}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;Ljava/lang/Throwable;)V

    throw v1
.end method

.method public add(Ljava/lang/String;Ljava/io/File;Ljava/lang/String;)Lmiui/net/http/HttpRequestParams;
    .locals 7

    .prologue
    .line 141
    if-eqz p1, :cond_0

    if-eqz p2, :cond_0

    .line 143
    :try_start_0
    iget-object v6, p0, Lmiui/net/http/HttpRequestParams;->OQ:Ljava/util/concurrent/ConcurrentHashMap;

    new-instance v0, Lmiui/net/http/HttpRequestParams$a;

    new-instance v1, Ljava/io/FileInputStream;

    invoke-direct {v1, p2}, Ljava/io/FileInputStream;-><init>(Ljava/io/File;)V

    invoke-virtual {p2}, Ljava/io/File;->length()J

    move-result-wide v2

    invoke-virtual {p2}, Ljava/io/File;->getName()Ljava/lang/String;

    move-result-object v4

    move-object v5, p3

    invoke-direct/range {v0 .. v5}, Lmiui/net/http/HttpRequestParams$a;-><init>(Ljava/io/InputStream;JLjava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v6, p1, v0}, Ljava/util/concurrent/ConcurrentHashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 144
    const/4 v0, 0x1

    iput-boolean v0, p0, Lmiui/net/http/HttpRequestParams;->OR:Z
    :try_end_0
    .catch Ljava/io/FileNotFoundException; {:try_start_0 .. :try_end_0} :catch_0

    .line 149
    :cond_0
    return-object p0

    .line 145
    :catch_0
    move-exception v0

    .line 146
    new-instance v1, Ljava/lang/IllegalArgumentException;

    invoke-virtual {v0}, Ljava/io/FileNotFoundException;->getMessage()Ljava/lang/String;

    move-result-object v2

    invoke-direct {v1, v2, v0}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;Ljava/lang/Throwable;)V

    throw v1
.end method

.method public add(Ljava/lang/String;Ljava/io/InputStream;JLjava/lang/String;)Lmiui/net/http/HttpRequestParams;
    .locals 7

    .prologue
    .line 174
    if-eqz p1, :cond_0

    if-eqz p2, :cond_0

    const-wide/16 v0, 0x0

    cmp-long v0, p3, v0

    if-ltz v0, :cond_0

    .line 175
    iget-object v6, p0, Lmiui/net/http/HttpRequestParams;->OQ:Ljava/util/concurrent/ConcurrentHashMap;

    new-instance v0, Lmiui/net/http/HttpRequestParams$a;

    const/4 v5, 0x0

    move-object v1, p2

    move-wide v2, p3

    move-object v4, p5

    invoke-direct/range {v0 .. v5}, Lmiui/net/http/HttpRequestParams$a;-><init>(Ljava/io/InputStream;JLjava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v6, p1, v0}, Ljava/util/concurrent/ConcurrentHashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 176
    const/4 v0, 0x1

    iput-boolean v0, p0, Lmiui/net/http/HttpRequestParams;->OR:Z

    .line 178
    :cond_0
    return-object p0
.end method

.method public add(Ljava/lang/String;Ljava/io/InputStream;JLjava/lang/String;Ljava/lang/String;)Lmiui/net/http/HttpRequestParams;
    .locals 7

    .prologue
    .line 191
    if-eqz p1, :cond_0

    if-eqz p2, :cond_0

    const-wide/16 v0, 0x0

    cmp-long v0, p3, v0

    if-ltz v0, :cond_0

    .line 192
    iget-object v6, p0, Lmiui/net/http/HttpRequestParams;->OQ:Ljava/util/concurrent/ConcurrentHashMap;

    new-instance v0, Lmiui/net/http/HttpRequestParams$a;

    move-object v1, p2

    move-wide v2, p3

    move-object v4, p5

    move-object v5, p6

    invoke-direct/range {v0 .. v5}, Lmiui/net/http/HttpRequestParams$a;-><init>(Ljava/io/InputStream;JLjava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v6, p1, v0}, Ljava/util/concurrent/ConcurrentHashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 193
    const/4 v0, 0x1

    iput-boolean v0, p0, Lmiui/net/http/HttpRequestParams;->OR:Z

    .line 195
    :cond_0
    return-object p0
.end method

.method public add(Ljava/lang/String;Ljava/lang/String;)Lmiui/net/http/HttpRequestParams;
    .locals 1

    .prologue
    .line 107
    if-eqz p1, :cond_0

    if-eqz p2, :cond_0

    .line 108
    iget-object v0, p0, Lmiui/net/http/HttpRequestParams;->OQ:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {v0, p1, p2}, Ljava/util/concurrent/ConcurrentHashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 110
    :cond_0
    return-object p0
.end method

.method public add(Ljava/lang/String;Ljava/util/List;)Lmiui/net/http/HttpRequestParams;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            "Ljava/util/List",
            "<",
            "Ljava/lang/String;",
            ">;)",
            "Lmiui/net/http/HttpRequestParams;"
        }
    .end annotation

    .prologue
    .line 159
    if-eqz p1, :cond_0

    if-eqz p2, :cond_0

    invoke-interface {p2}, Ljava/util/List;->size()I

    move-result v0

    if-lez v0, :cond_0

    .line 160
    iget-object v0, p0, Lmiui/net/http/HttpRequestParams;->OQ:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {v0, p1, p2}, Ljava/util/concurrent/ConcurrentHashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 162
    :cond_0
    return-object p0
.end method

.method public add(Ljava/util/Map;)Lmiui/net/http/HttpRequestParams;
    .locals 3
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/Map",
            "<",
            "Ljava/lang/String;",
            "Ljava/lang/String;",
            ">;)",
            "Lmiui/net/http/HttpRequestParams;"
        }
    .end annotation

    .prologue
    .line 204
    invoke-interface {p1}, Ljava/util/Map;->entrySet()Ljava/util/Set;

    move-result-object v0

    invoke-interface {v0}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v2

    :goto_0
    invoke-interface {v2}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_0

    invoke-interface {v2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/util/Map$Entry;

    .line 205
    invoke-interface {v0}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/lang/String;

    invoke-interface {v0}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/String;

    invoke-virtual {p0, v1, v0}, Lmiui/net/http/HttpRequestParams;->add(Ljava/lang/String;Ljava/lang/String;)Lmiui/net/http/HttpRequestParams;

    goto :goto_0

    .line 207
    :cond_0
    return-object p0
.end method

.method getEntity()Lorg/apache/http/HttpEntity;
    .locals 4

    .prologue
    .line 235
    const/4 v1, 0x0

    .line 237
    :try_start_0
    iget-boolean v0, p0, Lmiui/net/http/HttpRequestParams;->OR:Z

    if-eqz v0, :cond_0

    .line 238
    new-instance v0, Lmiui/net/http/a;

    iget-object v2, p0, Lmiui/net/http/HttpRequestParams;->dT:Ljava/lang/String;

    iget-object v3, p0, Lmiui/net/http/HttpRequestParams;->OQ:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-direct {v0, v2, v3}, Lmiui/net/http/a;-><init>(Ljava/lang/String;Ljava/util/Map;)V

    .line 246
    :goto_0
    return-object v0

    .line 240
    :cond_0
    new-instance v0, Lorg/apache/http/client/entity/UrlEncodedFormEntity;

    invoke-direct {p0}, Lmiui/net/http/HttpRequestParams;->ew()Ljava/util/List;

    move-result-object v2

    iget-object v3, p0, Lmiui/net/http/HttpRequestParams;->dT:Ljava/lang/String;

    invoke-direct {v0, v2, v3}, Lorg/apache/http/client/entity/UrlEncodedFormEntity;-><init>(Ljava/util/List;Ljava/lang/String;)V
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    .line 242
    :catch_0
    move-exception v0

    move-object v0, v1

    goto :goto_0
.end method

.method public getParamString()Ljava/lang/String;
    .locals 2

    .prologue
    .line 227
    invoke-direct {p0}, Lmiui/net/http/HttpRequestParams;->ew()Ljava/util/List;

    move-result-object v0

    iget-object v1, p0, Lmiui/net/http/HttpRequestParams;->dT:Ljava/lang/String;

    invoke-static {v0, v1}, Lorg/apache/http/client/utils/URLEncodedUtils;->format(Ljava/util/List;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method public remove(Ljava/lang/String;)Lmiui/net/http/HttpRequestParams;
    .locals 1

    .prologue
    .line 216
    iget-object v0, p0, Lmiui/net/http/HttpRequestParams;->OQ:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {v0, p1}, Ljava/util/concurrent/ConcurrentHashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    .line 217
    return-object p0
.end method

.method public setEncoding(Ljava/lang/String;)Lmiui/net/http/HttpRequestParams;
    .locals 0

    .prologue
    .line 96
    if-nez p1, :cond_0

    sget-object p1, Lmiui/net/http/HttpRequestParams;->DEFAULT_ENCODING:Ljava/lang/String;

    :cond_0
    iput-object p1, p0, Lmiui/net/http/HttpRequestParams;->dT:Ljava/lang/String;

    .line 97
    return-object p0
.end method
