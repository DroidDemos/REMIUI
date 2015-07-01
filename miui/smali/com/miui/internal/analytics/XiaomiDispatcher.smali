.class public Lcom/miui/internal/analytics/XiaomiDispatcher;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/miui/internal/analytics/Dispatchable;


# static fields
.field private static final EVENT_ID:Ljava/lang/String; = "_ei_"

.field private static final IK:Ljava/lang/String; = "_et_"

.field private static final IP:Ljava/lang/String; = "_ev_"

.field private static final IU:Ljava/lang/String; = "_ett_"

.field private static final IV:Ljava/lang/String; = "_app_"

.field private static final IW:Ljava/lang/String; = "_log_event_class_"

.field private static final IX:Ljava/lang/String; = "_log_event_message_"

.field private static final IY:Ljava/lang/String; = "imei"

.field private static final IZ:Ljava/lang/String; = "_miuiver_"

.field private static final Ja:Ljava/lang/String; = "_di_"

.field private static final Jb:Ljava/lang/String; = "_logs_"

.field public static final MAX_EVENT_NUM:I = 0x2710

.field private static final MODEL:Ljava/lang/String; = "_mod_"

.field public static final TAG:Ljava/lang/String; = "xiaomi"


# instance fields
.field private IH:Lcom/miui/internal/analytics/XiaomiServer;

.field private II:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List",
            "<",
            "Lorg/json/JSONObject;",
            ">;"
        }
    .end annotation
.end field

.field private IJ:Ljava/util/Map;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map",
            "<",
            "Ljava/lang/String;",
            "Ljava/lang/Object;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method public constructor <init>()V
    .locals 1

    .prologue
    .line 46
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 47
    invoke-static {}, Lcom/miui/internal/analytics/XiaomiServer;->getInstance()Lcom/miui/internal/analytics/XiaomiServer;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/internal/analytics/XiaomiDispatcher;->IH:Lcom/miui/internal/analytics/XiaomiServer;

    .line 48
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/miui/internal/analytics/XiaomiDispatcher;->II:Ljava/util/List;

    .line 49
    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    iput-object v0, p0, Lcom/miui/internal/analytics/XiaomiDispatcher;->IJ:Ljava/util/Map;

    .line 50
    return-void
.end method

.method private b(Lcom/miui/internal/analytics/Event;)Ljava/util/Map;
    .locals 4
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/miui/internal/analytics/Event;",
            ")",
            "Ljava/util/Map",
            "<",
            "Ljava/lang/String;",
            "Ljava/lang/Object;",
            ">;"
        }
    .end annotation

    .prologue
    .line 124
    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    .line 125
    const-string v1, "_ei_"

    invoke-virtual {p1}, Lcom/miui/internal/analytics/Event;->getEventId()Ljava/lang/String;

    move-result-object v2

    invoke-interface {v0, v1, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 126
    const-string v1, "_ett_"

    invoke-virtual {p1}, Lcom/miui/internal/analytics/Event;->getTrackTime()J

    move-result-wide v2

    invoke-static {v2, v3}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v2

    invoke-interface {v0, v1, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 127
    return-object v0
.end method

.method private dP()V
    .locals 4

    .prologue
    .line 131
    iget-object v0, p0, Lcom/miui/internal/analytics/XiaomiDispatcher;->II:Ljava/util/List;

    if-eqz v0, :cond_1

    iget-object v0, p0, Lcom/miui/internal/analytics/XiaomiDispatcher;->II:Ljava/util/List;

    invoke-interface {v0}, Ljava/util/List;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_1

    .line 132
    iget-object v0, p0, Lcom/miui/internal/analytics/XiaomiDispatcher;->IH:Lcom/miui/internal/analytics/XiaomiServer;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/internal/analytics/XiaomiDispatcher;->IJ:Ljava/util/Map;

    if-eqz v0, :cond_0

    .line 133
    iget-object v0, p0, Lcom/miui/internal/analytics/XiaomiDispatcher;->IJ:Ljava/util/Map;

    const-string v1, "_logs_"

    new-instance v2, Lorg/json/JSONArray;

    iget-object v3, p0, Lcom/miui/internal/analytics/XiaomiDispatcher;->II:Ljava/util/List;

    invoke-direct {v2, v3}, Lorg/json/JSONArray;-><init>(Ljava/util/Collection;)V

    invoke-interface {v0, v1, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 134
    iget-object v0, p0, Lcom/miui/internal/analytics/XiaomiDispatcher;->IH:Lcom/miui/internal/analytics/XiaomiServer;

    iget-object v1, p0, Lcom/miui/internal/analytics/XiaomiDispatcher;->IJ:Ljava/util/Map;

    invoke-virtual {v0, v1}, Lcom/miui/internal/analytics/XiaomiServer;->send(Ljava/util/Map;)Z

    .line 136
    :cond_0
    iget-object v0, p0, Lcom/miui/internal/analytics/XiaomiDispatcher;->II:Ljava/util/List;

    invoke-interface {v0}, Ljava/util/List;->clear()V

    .line 138
    :cond_1
    return-void
.end method


# virtual methods
.method public dispatchEvent(Lcom/miui/internal/analytics/TrackEvent;)V
    .locals 5

    .prologue
    .line 98
    iget-object v0, p0, Lcom/miui/internal/analytics/XiaomiDispatcher;->IH:Lcom/miui/internal/analytics/XiaomiServer;

    if-eqz v0, :cond_1

    iget-object v0, p0, Lcom/miui/internal/analytics/XiaomiDispatcher;->II:Ljava/util/List;

    if-eqz v0, :cond_1

    .line 99
    invoke-direct {p0, p1}, Lcom/miui/internal/analytics/XiaomiDispatcher;->b(Lcom/miui/internal/analytics/Event;)Ljava/util/Map;

    move-result-object v1

    .line 101
    invoke-virtual {p1}, Lcom/miui/internal/analytics/TrackEvent;->getParam()Ljava/util/Map;

    move-result-object v2

    .line 102
    if-eqz v2, :cond_0

    .line 103
    invoke-interface {v2}, Ljava/util/Map;->keySet()Ljava/util/Set;

    move-result-object v0

    invoke-interface {v0}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v3

    :goto_0
    invoke-interface {v3}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_0

    invoke-interface {v3}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/String;

    .line 104
    invoke-interface {v2, v0}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v4

    invoke-interface {v1, v0, v4}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    goto :goto_0

    .line 107
    :cond_0
    const-string v0, "_ev_"

    invoke-virtual {p1}, Lcom/miui/internal/analytics/TrackEvent;->getValue()J

    move-result-wide v2

    invoke-static {v2, v3}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v2

    invoke-interface {v1, v0, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 108
    iget-object v0, p0, Lcom/miui/internal/analytics/XiaomiDispatcher;->II:Ljava/util/List;

    new-instance v2, Lorg/json/JSONObject;

    invoke-direct {v2, v1}, Lorg/json/JSONObject;-><init>(Ljava/util/Map;)V

    invoke-interface {v0, v2}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 109
    iget-object v0, p0, Lcom/miui/internal/analytics/XiaomiDispatcher;->II:Ljava/util/List;

    invoke-interface {v0}, Ljava/util/List;->size()I

    move-result v0

    const/16 v1, 0x2710

    if-lt v0, v1, :cond_1

    .line 110
    invoke-direct {p0}, Lcom/miui/internal/analytics/XiaomiDispatcher;->dP()V

    .line 113
    :cond_1
    return-void
.end method

.method public dispatchLog(Lcom/miui/internal/analytics/LogEvent;)V
    .locals 3

    .prologue
    .line 85
    iget-object v0, p0, Lcom/miui/internal/analytics/XiaomiDispatcher;->IH:Lcom/miui/internal/analytics/XiaomiServer;

    if-eqz v0, :cond_0

    .line 86
    invoke-direct {p0, p1}, Lcom/miui/internal/analytics/XiaomiDispatcher;->b(Lcom/miui/internal/analytics/Event;)Ljava/util/Map;

    move-result-object v0

    .line 87
    const-string v1, "_log_event_class_"

    invoke-virtual {p1}, Lcom/miui/internal/analytics/LogEvent;->getErrorClass()Ljava/lang/String;

    move-result-object v2

    invoke-interface {v0, v1, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 88
    const-string v1, "_log_event_message_"

    invoke-virtual {p1}, Lcom/miui/internal/analytics/LogEvent;->getMessage()Ljava/lang/String;

    move-result-object v2

    invoke-interface {v0, v1, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 89
    iget-object v1, p0, Lcom/miui/internal/analytics/XiaomiDispatcher;->IH:Lcom/miui/internal/analytics/XiaomiServer;

    invoke-virtual {v1, v0}, Lcom/miui/internal/analytics/XiaomiServer;->send(Ljava/util/Map;)Z

    .line 91
    :cond_0
    return-void
.end method

.method public dispatchPageView(Lcom/miui/internal/analytics/TrackPageViewEvent;)V
    .locals 2

    .prologue
    .line 117
    iget-object v0, p0, Lcom/miui/internal/analytics/XiaomiDispatcher;->IH:Lcom/miui/internal/analytics/XiaomiServer;

    if-eqz v0, :cond_0

    .line 118
    invoke-direct {p0, p1}, Lcom/miui/internal/analytics/XiaomiDispatcher;->b(Lcom/miui/internal/analytics/Event;)Ljava/util/Map;

    move-result-object v0

    .line 119
    iget-object v1, p0, Lcom/miui/internal/analytics/XiaomiDispatcher;->IH:Lcom/miui/internal/analytics/XiaomiServer;

    invoke-virtual {v1, v0}, Lcom/miui/internal/analytics/XiaomiServer;->send(Ljava/util/Map;)Z

    .line 121
    :cond_0
    return-void
.end method

.method public start(Landroid/content/Context;Ljava/lang/String;)V
    .locals 4

    .prologue
    .line 54
    iget-object v0, p0, Lcom/miui/internal/analytics/XiaomiDispatcher;->IH:Lcom/miui/internal/analytics/XiaomiServer;

    invoke-virtual {v0}, Lcom/miui/internal/analytics/XiaomiServer;->init()V

    .line 56
    :try_start_0
    invoke-static {}, Lmiui/telephony/TelephonyHelper;->getInstance()Lmiui/telephony/TelephonyHelper;

    move-result-object v0

    invoke-virtual {v0}, Lmiui/telephony/TelephonyHelper;->getDeviceId()Ljava/lang/String;

    move-result-object v1

    .line 58
    invoke-static {v1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 59
    const-string v1, "null"

    .line 60
    const-string v0, "null"

    .line 67
    :goto_0
    iget-object v2, p0, Lcom/miui/internal/analytics/XiaomiDispatcher;->IJ:Ljava/util/Map;

    const-string v3, "_di_"

    invoke-interface {v2, v3, v0}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 68
    iget-object v0, p0, Lcom/miui/internal/analytics/XiaomiDispatcher;->IJ:Ljava/util/Map;

    const-string v2, "imei"

    invoke-interface {v0, v2, v1}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 69
    iget-object v0, p0, Lcom/miui/internal/analytics/XiaomiDispatcher;->IJ:Ljava/util/Map;

    const-string v1, "_app_"

    invoke-interface {v0, v1, p2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 70
    iget-object v0, p0, Lcom/miui/internal/analytics/XiaomiDispatcher;->IJ:Ljava/util/Map;

    const-string v1, "_et_"

    const/4 v2, 0x2

    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    invoke-interface {v0, v1, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 71
    iget-object v0, p0, Lcom/miui/internal/analytics/XiaomiDispatcher;->IJ:Ljava/util/Map;

    const-string v1, "_miuiver_"

    sget-object v2, Landroid/os/Build$VERSION;->INCREMENTAL:Ljava/lang/String;

    invoke-interface {v0, v1, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 72
    iget-object v0, p0, Lcom/miui/internal/analytics/XiaomiDispatcher;->IJ:Ljava/util/Map;

    const-string v1, "_mod_"

    sget-object v2, Landroid/os/Build;->MODEL:Ljava/lang/String;

    invoke-interface {v0, v1, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 75
    :goto_1
    return-void

    .line 62
    :cond_0
    const-string v0, "SHA-1"

    invoke-static {v1, v0}, Lmiui/security/DigestUtils;->get(Ljava/lang/CharSequence;Ljava/lang/String;)[B

    move-result-object v0

    const/16 v2, 0x8

    invoke-static {v0, v2}, Landroid/util/Base64;->encodeToString([BI)Ljava/lang/String;

    move-result-object v0

    const/4 v2, 0x0

    const/16 v3, 0x10

    invoke-virtual {v0, v2, v3}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object v0

    .line 64
    const-string v2, "MD5"

    invoke-static {v1, v2}, Lmiui/security/DigestUtils;->get(Ljava/lang/CharSequence;Ljava/lang/String;)[B

    move-result-object v1

    invoke-static {v1}, Lmiui/text/ExtraTextUtils;->toHexReadable([B)Ljava/lang/String;
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    move-result-object v1

    goto :goto_0

    .line 73
    :catch_0
    move-exception v0

    goto :goto_1
.end method

.method public stop()V
    .locals 1

    .prologue
    .line 79
    invoke-direct {p0}, Lcom/miui/internal/analytics/XiaomiDispatcher;->dP()V

    .line 80
    iget-object v0, p0, Lcom/miui/internal/analytics/XiaomiDispatcher;->IH:Lcom/miui/internal/analytics/XiaomiServer;

    invoke-virtual {v0}, Lcom/miui/internal/analytics/XiaomiServer;->close()V

    .line 81
    return-void
.end method
