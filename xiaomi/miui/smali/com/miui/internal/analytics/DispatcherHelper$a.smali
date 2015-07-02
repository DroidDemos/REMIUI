.class Lcom/miui/internal/analytics/DispatcherHelper$a;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/miui/internal/analytics/DispatcherHelper$b;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/internal/analytics/DispatcherHelper;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x2
    name = "a"
.end annotation


# instance fields
.field final synthetic dO:Lcom/miui/internal/analytics/DispatcherHelper;


# direct methods
.method private constructor <init>(Lcom/miui/internal/analytics/DispatcherHelper;)V
    .locals 0

    .prologue
    .line 63
    iput-object p1, p0, Lcom/miui/internal/analytics/DispatcherHelper$a;->dO:Lcom/miui/internal/analytics/DispatcherHelper;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method synthetic constructor <init>(Lcom/miui/internal/analytics/DispatcherHelper;Lcom/miui/internal/analytics/DispatcherHelper$1;)V
    .locals 0

    .prologue
    .line 63
    invoke-direct {p0, p1}, Lcom/miui/internal/analytics/DispatcherHelper$a;-><init>(Lcom/miui/internal/analytics/DispatcherHelper;)V

    return-void
.end method


# virtual methods
.method public O()Lorg/json/JSONArray;
    .locals 6

    .prologue
    .line 66
    new-instance v0, Lorg/apache/http/impl/client/DefaultHttpClient;

    invoke-direct {v0}, Lorg/apache/http/impl/client/DefaultHttpClient;-><init>()V

    .line 67
    const-string v1, "http://app.miui.com/mobile/analytics2.json"

    .line 69
    :try_start_0
    new-instance v2, Lorg/apache/http/client/methods/HttpGet;

    invoke-direct {v2, v1}, Lorg/apache/http/client/methods/HttpGet;-><init>(Ljava/lang/String;)V

    .line 70
    invoke-interface {v0, v2}, Lorg/apache/http/client/HttpClient;->execute(Lorg/apache/http/client/methods/HttpUriRequest;)Lorg/apache/http/HttpResponse;

    move-result-object v0

    .line 71
    invoke-interface {v0}, Lorg/apache/http/HttpResponse;->getStatusLine()Lorg/apache/http/StatusLine;

    move-result-object v2

    .line 72
    if-eqz v2, :cond_0

    const/16 v3, 0xc8

    invoke-interface {v2}, Lorg/apache/http/StatusLine;->getStatusCode()I

    move-result v2

    if-ne v3, v2, :cond_0

    .line 73
    invoke-interface {v0}, Lorg/apache/http/HttpResponse;->getEntity()Lorg/apache/http/HttpEntity;

    move-result-object v2

    .line 74
    new-instance v0, Lorg/json/JSONArray;

    invoke-static {v2}, Lorg/apache/http/util/EntityUtils;->toString(Lorg/apache/http/HttpEntity;)Ljava/lang/String;

    move-result-object v2

    invoke-direct {v0, v2}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V
    :try_end_0
    .catch Lorg/apache/http/client/ClientProtocolException; {:try_start_0 .. :try_end_0} :catch_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_1
    .catch Lorg/json/JSONException; {:try_start_0 .. :try_end_0} :catch_2
    .catch Ljava/lang/NumberFormatException; {:try_start_0 .. :try_end_0} :catch_3
    .catch Ljava/lang/IllegalArgumentException; {:try_start_0 .. :try_end_0} :catch_4
    .catch Ljava/lang/NullPointerException; {:try_start_0 .. :try_end_0} :catch_5
    .catch Ljava/lang/AssertionError; {:try_start_0 .. :try_end_0} :catch_6

    .line 92
    :goto_0
    return-object v0

    .line 77
    :catch_0
    move-exception v0

    .line 78
    const-string v1, "DispatcherHelper"

    const-string v2, "ClientProtocolException catched when load configuration from server"

    invoke-static {v1, v2, v0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    .line 92
    :cond_0
    :goto_1
    const/4 v0, 0x0

    goto :goto_0

    .line 79
    :catch_1
    move-exception v0

    .line 80
    const-string v1, "DispatcherHelper"

    const-string v2, "IOException catched when load configuration from server"

    invoke-static {v1, v2, v0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    goto :goto_1

    .line 81
    :catch_2
    move-exception v0

    .line 82
    const-string v1, "DispatcherHelper"

    const-string v2, "JSONException catched when load configuration from server"

    invoke-static {v1, v2, v0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    goto :goto_1

    .line 83
    :catch_3
    move-exception v0

    .line 84
    const-string v1, "DispatcherHelper"

    const-string v2, "NumberFormatException catched when load configuration from server"

    invoke-static {v1, v2, v0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    goto :goto_1

    .line 85
    :catch_4
    move-exception v0

    .line 86
    const-string v2, "DispatcherHelper"

    const-string v3, "Error URL Argrment:%s"

    const/4 v4, 0x1

    new-array v4, v4, [Ljava/lang/Object;

    const/4 v5, 0x0

    aput-object v1, v4, v5

    invoke-static {v3, v4}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    invoke-static {v2, v1, v0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    goto :goto_1

    .line 87
    :catch_5
    move-exception v0

    .line 88
    const-string v1, "DispatcherHelper"

    const-string v2, "NullPointerException catched when load configuration from server"

    invoke-static {v1, v2, v0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    goto :goto_1

    .line 89
    :catch_6
    move-exception v0

    .line 90
    const-string v1, "DispatcherHelper"

    const-string v2, "AssertionError catched when load configuration from server"

    invoke-static {v1, v2, v0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    goto :goto_1
.end method
