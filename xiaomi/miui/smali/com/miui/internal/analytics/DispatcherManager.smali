.class public Lcom/miui/internal/analytics/DispatcherManager;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field private static final Io:Ljava/lang/String; = "_device_info_event_"

.field private static final Ip:Ljava/lang/String; = "_device_imei_"

.field private static final Iq:Ljava/lang/String; = "_device_model_"

.field private static final TAG:Ljava/lang/String; = "DispatcherManager"


# instance fields
.field private Ik:Ljava/util/Map;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map",
            "<",
            "Lcom/miui/internal/analytics/Dispatchable;",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation
.end field

.field private Il:Lcom/miui/internal/analytics/ObjectBuilder;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lcom/miui/internal/analytics/ObjectBuilder",
            "<",
            "Lcom/miui/internal/analytics/Dispatchable;",
            ">;"
        }
    .end annotation
.end field

.field private Im:Lcom/miui/internal/analytics/EventReader;

.field private In:Lcom/miui/internal/analytics/PolicyHelper;

.field private mContext:Landroid/content/Context;


# direct methods
.method public constructor <init>(Landroid/content/Context;)V
    .locals 3

    .prologue
    .line 35
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 23
    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    iput-object v0, p0, Lcom/miui/internal/analytics/DispatcherManager;->Ik:Ljava/util/Map;

    .line 24
    new-instance v0, Lcom/miui/internal/analytics/ObjectBuilder;

    invoke-direct {v0}, Lcom/miui/internal/analytics/ObjectBuilder;-><init>()V

    iput-object v0, p0, Lcom/miui/internal/analytics/DispatcherManager;->Il:Lcom/miui/internal/analytics/ObjectBuilder;

    .line 25
    new-instance v0, Lcom/miui/internal/analytics/EventReader;

    invoke-direct {v0}, Lcom/miui/internal/analytics/EventReader;-><init>()V

    iput-object v0, p0, Lcom/miui/internal/analytics/DispatcherManager;->Im:Lcom/miui/internal/analytics/EventReader;

    .line 26
    new-instance v0, Lcom/miui/internal/analytics/PolicyHelper;

    invoke-direct {v0}, Lcom/miui/internal/analytics/PolicyHelper;-><init>()V

    iput-object v0, p0, Lcom/miui/internal/analytics/DispatcherManager;->In:Lcom/miui/internal/analytics/PolicyHelper;

    .line 36
    iput-object p1, p0, Lcom/miui/internal/analytics/DispatcherManager;->mContext:Landroid/content/Context;

    .line 37
    iget-object v0, p0, Lcom/miui/internal/analytics/DispatcherManager;->Il:Lcom/miui/internal/analytics/ObjectBuilder;

    const-class v1, Lcom/miui/internal/analytics/XiaomiDispatcher;

    const-string v2, "xiaomi"

    invoke-virtual {v0, v1, v2}, Lcom/miui/internal/analytics/ObjectBuilder;->registerClass(Ljava/lang/Class;Ljava/lang/String;)Z

    .line 38
    return-void
.end method

.method private B(Ljava/lang/String;)V
    .locals 7

    .prologue
    .line 63
    iget-object v0, p0, Lcom/miui/internal/analytics/DispatcherManager;->In:Lcom/miui/internal/analytics/PolicyHelper;

    const-string v1, "normal"

    invoke-virtual {v0, v1}, Lcom/miui/internal/analytics/PolicyHelper;->getPolicy(Ljava/lang/String;)Lcom/miui/internal/analytics/Policy;

    move-result-object v6

    .line 65
    new-instance v3, Ljava/util/HashMap;

    invoke-direct {v3}, Ljava/util/HashMap;-><init>()V

    .line 66
    invoke-static {}, Lmiui/telephony/TelephonyHelper;->getInstance()Lmiui/telephony/TelephonyHelper;

    move-result-object v0

    invoke-virtual {v0}, Lmiui/telephony/TelephonyHelper;->getDeviceId()Ljava/lang/String;

    move-result-object v0

    .line 67
    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v1

    if-eqz v1, :cond_1

    .line 68
    const-string v0, "null"

    .line 72
    :goto_0
    const-string v1, "_device_imei_"

    invoke-interface {v3, v1, v0}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 73
    const-string v0, "_device_model_"

    sget-object v1, Landroid/os/Build;->MODEL:Ljava/lang/String;

    invoke-interface {v3, v0, v1}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 75
    new-instance v0, Lcom/miui/internal/analytics/TrackEvent;

    const-string v2, "_device_info_event_"

    const-wide/16 v4, 0x0

    move-object v1, p1

    invoke-direct/range {v0 .. v5}, Lcom/miui/internal/analytics/TrackEvent;-><init>(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;J)V

    .line 76
    if-eqz v6, :cond_0

    invoke-static {}, Lmiui/net/ConnectivityHelper;->getInstance()Lmiui/net/ConnectivityHelper;

    move-result-object v1

    invoke-virtual {v1}, Lmiui/net/ConnectivityHelper;->isUnmeteredNetworkConnected()Z

    move-result v1

    if-eqz v1, :cond_0

    iget-object v1, p0, Lcom/miui/internal/analytics/DispatcherManager;->mContext:Landroid/content/Context;

    invoke-static {v1}, Lcom/miui/internal/analytics/EventUtils;->enableWrite(Landroid/content/Context;)Z

    move-result v1

    if-eqz v1, :cond_0

    .line 79
    invoke-virtual {v6, v0}, Lcom/miui/internal/analytics/Policy;->execute(Lcom/miui/internal/analytics/Event;)V

    .line 81
    :cond_0
    return-void

    .line 70
    :cond_1
    const-string v1, "MD5"

    invoke-static {v0, v1}, Lmiui/security/DigestUtils;->get(Ljava/lang/CharSequence;Ljava/lang/String;)[B

    move-result-object v0

    invoke-static {v0}, Lmiui/text/ExtraTextUtils;->toHexReadable([B)Ljava/lang/String;

    move-result-object v0

    goto :goto_0
.end method

.method private a(Ljava/lang/String;Ljava/util/List;)V
    .locals 5
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            "Ljava/util/List",
            "<",
            "Lcom/miui/internal/analytics/Item;",
            ">;)V"
        }
    .end annotation

    .prologue
    .line 100
    iget-object v0, p0, Lcom/miui/internal/analytics/DispatcherManager;->Im:Lcom/miui/internal/analytics/EventReader;

    iget-object v1, p0, Lcom/miui/internal/analytics/DispatcherManager;->mContext:Landroid/content/Context;

    invoke-virtual {v0, v1}, Lcom/miui/internal/analytics/EventReader;->open(Landroid/content/Context;)V

    .line 101
    iget-object v0, p0, Lcom/miui/internal/analytics/DispatcherManager;->Im:Lcom/miui/internal/analytics/EventReader;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "package = \""

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, "\""

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1, p2}, Lcom/miui/internal/analytics/EventReader;->readEvents(Ljava/lang/String;Ljava/util/List;)Ljava/util/List;

    move-result-object v0

    .line 102
    const/4 v1, 0x1

    .line 104
    invoke-interface {v0}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v2

    :cond_0
    :goto_0
    invoke-interface {v2}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_4

    invoke-interface {v2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/analytics/Event;

    .line 105
    iget-object v3, p0, Lcom/miui/internal/analytics/DispatcherManager;->In:Lcom/miui/internal/analytics/PolicyHelper;

    invoke-virtual {v0}, Lcom/miui/internal/analytics/Event;->getPolicy()Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v3, v4}, Lcom/miui/internal/analytics/PolicyHelper;->getPolicy(Ljava/lang/String;)Lcom/miui/internal/analytics/Policy;

    move-result-object v3

    .line 106
    if-eqz v3, :cond_0

    .line 108
    invoke-static {}, Lmiui/net/ConnectivityHelper;->getInstance()Lmiui/net/ConnectivityHelper;

    move-result-object v4

    invoke-virtual {v4}, Lmiui/net/ConnectivityHelper;->isUnmeteredNetworkConnected()Z

    move-result v4

    if-eqz v4, :cond_1

    iget-object v4, p0, Lcom/miui/internal/analytics/DispatcherManager;->mContext:Landroid/content/Context;

    invoke-static {v4}, Lcom/miui/internal/analytics/EventUtils;->enableWrite(Landroid/content/Context;)Z

    move-result v4

    if-nez v4, :cond_3

    .line 110
    :cond_1
    const/4 v0, 0x0

    .line 117
    :goto_1
    iget-object v1, p0, Lcom/miui/internal/analytics/DispatcherManager;->Im:Lcom/miui/internal/analytics/EventReader;

    invoke-virtual {v1}, Lcom/miui/internal/analytics/EventReader;->close()V

    .line 118
    if-eqz v0, :cond_2

    .line 119
    invoke-static {}, Lcom/miui/internal/analytics/a;->l()Lcom/miui/internal/analytics/a;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/internal/analytics/a;->m()V

    .line 121
    :cond_2
    return-void

    .line 113
    :cond_3
    invoke-virtual {v3, v0}, Lcom/miui/internal/analytics/Policy;->execute(Lcom/miui/internal/analytics/Event;)V

    goto :goto_0

    :cond_4
    move v0, v1

    goto :goto_1
.end method


# virtual methods
.method public dispatch(Ljava/lang/String;Ljava/util/List;)V
    .locals 4
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            "Ljava/util/List",
            "<",
            "Lcom/miui/internal/analytics/Item;",
            ">;)V"
        }
    .end annotation

    .prologue
    .line 84
    iget-object v0, p0, Lcom/miui/internal/analytics/DispatcherManager;->Ik:Ljava/util/Map;

    invoke-interface {v0}, Ljava/util/Map;->keySet()Ljava/util/Set;

    move-result-object v0

    invoke-interface {v0}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v2

    :goto_0
    invoke-interface {v2}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_0

    invoke-interface {v2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/analytics/Dispatchable;

    .line 85
    iget-object v3, p0, Lcom/miui/internal/analytics/DispatcherManager;->mContext:Landroid/content/Context;

    iget-object v1, p0, Lcom/miui/internal/analytics/DispatcherManager;->Ik:Ljava/util/Map;

    invoke-interface {v1, v0}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/lang/String;

    invoke-interface {v0, v3, v1}, Lcom/miui/internal/analytics/Dispatchable;->start(Landroid/content/Context;Ljava/lang/String;)V

    goto :goto_0

    .line 88
    :cond_0
    iget-object v0, p0, Lcom/miui/internal/analytics/DispatcherManager;->In:Lcom/miui/internal/analytics/PolicyHelper;

    invoke-virtual {v0}, Lcom/miui/internal/analytics/PolicyHelper;->clear()V

    .line 90
    invoke-direct {p0, p1, p2}, Lcom/miui/internal/analytics/DispatcherManager;->a(Ljava/lang/String;Ljava/util/List;)V

    .line 92
    iget-object v0, p0, Lcom/miui/internal/analytics/DispatcherManager;->In:Lcom/miui/internal/analytics/PolicyHelper;

    invoke-virtual {v0}, Lcom/miui/internal/analytics/PolicyHelper;->end()V

    .line 94
    iget-object v0, p0, Lcom/miui/internal/analytics/DispatcherManager;->Ik:Ljava/util/Map;

    invoke-interface {v0}, Ljava/util/Map;->keySet()Ljava/util/Set;

    move-result-object v0

    invoke-interface {v0}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :goto_1
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_1

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/analytics/Dispatchable;

    .line 95
    invoke-interface {v0}, Lcom/miui/internal/analytics/Dispatchable;->stop()V

    goto :goto_1

    .line 97
    :cond_1
    return-void
.end method

.method public switchDispatcher(Ljava/util/Map;)V
    .locals 6
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/Map",
            "<",
            "Ljava/lang/String;",
            "Ljava/lang/String;",
            ">;)V"
        }
    .end annotation

    .prologue
    .line 41
    iget-object v0, p0, Lcom/miui/internal/analytics/DispatcherManager;->Ik:Ljava/util/Map;

    invoke-interface {v0}, Ljava/util/Map;->clear()V

    .line 43
    if-eqz p1, :cond_2

    .line 44
    invoke-interface {p1}, Ljava/util/Map;->keySet()Ljava/util/Set;

    move-result-object v0

    invoke-interface {v0}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v2

    :goto_0
    invoke-interface {v2}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_1

    invoke-interface {v2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/String;

    .line 45
    iget-object v1, p0, Lcom/miui/internal/analytics/DispatcherManager;->Il:Lcom/miui/internal/analytics/ObjectBuilder;

    invoke-virtual {v1, v0}, Lcom/miui/internal/analytics/ObjectBuilder;->buildObject(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/miui/internal/analytics/Dispatchable;

    .line 46
    if-nez v1, :cond_0

    .line 47
    const-string v1, "DispatcherManager"

    const-string v3, "server:%s does not exist"

    const/4 v4, 0x1

    new-array v4, v4, [Ljava/lang/Object;

    const/4 v5, 0x0

    aput-object v0, v4, v5

    invoke-static {v3, v4}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    invoke-static {v1, v0}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    goto :goto_0

    .line 49
    :cond_0
    iget-object v3, p0, Lcom/miui/internal/analytics/DispatcherManager;->Ik:Ljava/util/Map;

    invoke-interface {p1, v0}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    invoke-interface {v3, v1, v0}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    goto :goto_0

    .line 53
    :cond_1
    invoke-interface {p1}, Ljava/util/Map;->size()I

    move-result v0

    if-lez v0, :cond_2

    .line 54
    new-instance v0, Ljava/util/ArrayList;

    iget-object v1, p0, Lcom/miui/internal/analytics/DispatcherManager;->Ik:Ljava/util/Map;

    invoke-interface {v1}, Ljava/util/Map;->keySet()Ljava/util/Set;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/util/ArrayList;-><init>(Ljava/util/Collection;)V

    invoke-static {v0}, Lcom/miui/internal/analytics/Event;->setDispatcher(Ljava/util/List;)V

    .line 57
    :cond_2
    return-void
.end method
