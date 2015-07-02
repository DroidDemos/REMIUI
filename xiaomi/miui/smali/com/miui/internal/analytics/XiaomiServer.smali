.class public Lcom/miui/internal/analytics/XiaomiServer;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field private static final TAG:Ljava/lang/String; = "XIAOMI_SERVER"

.field private static final TYPE:Ljava/lang/String; = "t"

.field private static final Tw:Ljava/lang/String; = "miui_apps"

.field private static final Tx:Ljava/lang/String; = "s"

.field private static final VALUE:Ljava/lang/String; = "value"

.field private static final f:Lmiui/util/SoftReferenceSingleton;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lmiui/util/SoftReferenceSingleton",
            "<",
            "Lcom/miui/internal/analytics/XiaomiServer;",
            ">;"
        }
    .end annotation
.end field

.field private static final fB:Ljava/lang/String; = "http://tracking.miui.com/tracks"


# instance fields
.field private Ty:Lorg/apache/http/client/methods/HttpPost;

.field private Tz:Lorg/apache/http/client/HttpClient;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .prologue
    .line 44
    new-instance v0, Lcom/miui/internal/analytics/e;

    invoke-direct {v0}, Lcom/miui/internal/analytics/e;-><init>()V

    sput-object v0, Lcom/miui/internal/analytics/XiaomiServer;->f:Lmiui/util/SoftReferenceSingleton;

    return-void
.end method

.method private constructor <init>()V
    .locals 0

    .prologue
    .line 52
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 53
    return-void
.end method

.method synthetic constructor <init>(Lcom/miui/internal/analytics/e;)V
    .locals 0

    .prologue
    .line 32
    invoke-direct {p0}, Lcom/miui/internal/analytics/XiaomiServer;-><init>()V

    return-void
.end method

.method public static getInstance()Lcom/miui/internal/analytics/XiaomiServer;
    .locals 1

    .prologue
    .line 56
    sget-object v0, Lcom/miui/internal/analytics/XiaomiServer;->f:Lmiui/util/SoftReferenceSingleton;

    invoke-virtual {v0}, Lmiui/util/SoftReferenceSingleton;->get()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/analytics/XiaomiServer;

    return-object v0
.end method


# virtual methods
.method public close()V
    .locals 1

    .prologue
    const/4 v0, 0x0

    .line 65
    iput-object v0, p0, Lcom/miui/internal/analytics/XiaomiServer;->Ty:Lorg/apache/http/client/methods/HttpPost;

    .line 66
    iput-object v0, p0, Lcom/miui/internal/analytics/XiaomiServer;->Tz:Lorg/apache/http/client/HttpClient;

    .line 67
    return-void
.end method

.method public init()V
    .locals 2

    .prologue
    .line 60
    new-instance v0, Lorg/apache/http/impl/client/DefaultHttpClient;

    invoke-direct {v0}, Lorg/apache/http/impl/client/DefaultHttpClient;-><init>()V

    iput-object v0, p0, Lcom/miui/internal/analytics/XiaomiServer;->Tz:Lorg/apache/http/client/HttpClient;

    .line 61
    new-instance v0, Lorg/apache/http/client/methods/HttpPost;

    const-string v1, "http://tracking.miui.com/tracks"

    invoke-direct {v0, v1}, Lorg/apache/http/client/methods/HttpPost;-><init>(Ljava/lang/String;)V

    iput-object v0, p0, Lcom/miui/internal/analytics/XiaomiServer;->Ty:Lorg/apache/http/client/methods/HttpPost;

    .line 62
    return-void
.end method

.method public send(Ljava/util/Map;)Z
    .locals 6
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/Map",
            "<",
            "Ljava/lang/String;",
            "Ljava/lang/Object;",
            ">;)Z"
        }
    .end annotation

    .prologue
    .line 70
    const/4 v0, 0x0

    .line 72
    iget-object v1, p0, Lcom/miui/internal/analytics/XiaomiServer;->Tz:Lorg/apache/http/client/HttpClient;

    if-eqz v1, :cond_0

    iget-object v1, p0, Lcom/miui/internal/analytics/XiaomiServer;->Ty:Lorg/apache/http/client/methods/HttpPost;

    if-eqz v1, :cond_0

    if-eqz p1, :cond_0

    .line 73
    new-instance v1, Lorg/json/JSONObject;

    invoke-direct {v1, p1}, Lorg/json/JSONObject;-><init>(Ljava/util/Map;)V

    .line 74
    invoke-virtual {v1}, Lorg/json/JSONObject;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/String;->getBytes()[B

    move-result-object v1

    const/4 v2, 0x2

    invoke-static {v1, v2}, Landroid/util/Base64;->encodeToString([BI)Ljava/lang/String;

    move-result-object v1

    .line 76
    new-instance v2, Ljava/util/LinkedList;

    invoke-direct {v2}, Ljava/util/LinkedList;-><init>()V

    .line 77
    new-instance v3, Lorg/apache/http/message/BasicNameValuePair;

    const-string v4, "t"

    const-string v5, "miui_apps"

    invoke-direct {v3, v4, v5}, Lorg/apache/http/message/BasicNameValuePair;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-interface {v2, v3}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 78
    new-instance v3, Lorg/apache/http/message/BasicNameValuePair;

    const-string v4, "value"

    invoke-direct {v3, v4, v1}, Lorg/apache/http/message/BasicNameValuePair;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-interface {v2, v3}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 79
    new-instance v1, Lorg/apache/http/message/BasicNameValuePair;

    const-string v3, "s"

    invoke-static {v2}, Lcom/miui/internal/analytics/SaltGenerate;->getKeyFromParams(Ljava/util/List;)Ljava/lang/String;

    move-result-object v4

    invoke-direct {v1, v3, v4}, Lorg/apache/http/message/BasicNameValuePair;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-interface {v2, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 82
    :try_start_0
    iget-object v1, p0, Lcom/miui/internal/analytics/XiaomiServer;->Ty:Lorg/apache/http/client/methods/HttpPost;

    new-instance v3, Lorg/apache/http/client/entity/UrlEncodedFormEntity;

    const-string v4, "UTF-8"

    invoke-direct {v3, v2, v4}, Lorg/apache/http/client/entity/UrlEncodedFormEntity;-><init>(Ljava/util/List;Ljava/lang/String;)V

    invoke-virtual {v1, v3}, Lorg/apache/http/client/methods/HttpPost;->setEntity(Lorg/apache/http/HttpEntity;)V

    .line 83
    iget-object v1, p0, Lcom/miui/internal/analytics/XiaomiServer;->Tz:Lorg/apache/http/client/HttpClient;

    iget-object v2, p0, Lcom/miui/internal/analytics/XiaomiServer;->Ty:Lorg/apache/http/client/methods/HttpPost;

    invoke-interface {v1, v2}, Lorg/apache/http/client/HttpClient;->execute(Lorg/apache/http/client/methods/HttpUriRequest;)Lorg/apache/http/HttpResponse;

    move-result-object v1

    .line 84
    const/16 v2, 0xc8

    invoke-interface {v1}, Lorg/apache/http/HttpResponse;->getStatusLine()Lorg/apache/http/StatusLine;

    move-result-object v3

    invoke-interface {v3}, Lorg/apache/http/StatusLine;->getStatusCode()I

    move-result v3

    if-ne v2, v3, :cond_0

    .line 85
    invoke-interface {v1}, Lorg/apache/http/HttpResponse;->getEntity()Lorg/apache/http/HttpEntity;

    move-result-object v1

    invoke-static {v1}, Lorg/apache/http/util/EntityUtils;->toString(Lorg/apache/http/HttpEntity;)Ljava/lang/String;

    move-result-object v1

    .line 86
    new-instance v2, Lorg/json/JSONObject;

    invoke-direct {v2, v1}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V

    .line 87
    const-string v1, "status"

    invoke-virtual {v2, v1}, Lorg/json/JSONObject;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    .line 88
    const-string v2, "ok"

    invoke-virtual {v1, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_1

    .line 89
    const/4 v0, 0x1

    .line 105
    :cond_0
    :goto_0
    return v0

    .line 91
    :cond_1
    const-string v2, "XIAOMI_SERVER"

    invoke-static {v2, v1}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I
    :try_end_0
    .catch Ljava/io/UnsupportedEncodingException; {:try_start_0 .. :try_end_0} :catch_0
    .catch Lorg/apache/http/client/ClientProtocolException; {:try_start_0 .. :try_end_0} :catch_1
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_2
    .catch Lorg/json/JSONException; {:try_start_0 .. :try_end_0} :catch_3

    goto :goto_0

    .line 94
    :catch_0
    move-exception v1

    .line 95
    const-string v2, "XIAOMI_SERVER"

    const-string v3, "UnsupportedEncodingException catched when sending data"

    invoke-static {v2, v3, v1}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    goto :goto_0

    .line 96
    :catch_1
    move-exception v1

    .line 97
    const-string v2, "XIAOMI_SERVER"

    const-string v3, "ClientProtocolException catched when sending data"

    invoke-static {v2, v3, v1}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    goto :goto_0

    .line 98
    :catch_2
    move-exception v1

    .line 99
    const-string v2, "XIAOMI_SERVER"

    const-string v3, "IOException catched when sending data"

    invoke-static {v2, v3, v1}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    goto :goto_0

    .line 100
    :catch_3
    move-exception v1

    .line 101
    const-string v2, "XIAOMI_SERVER"

    const-string v3, "JSONException catched when sending data"

    invoke-static {v2, v3, v1}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    goto :goto_0
.end method
