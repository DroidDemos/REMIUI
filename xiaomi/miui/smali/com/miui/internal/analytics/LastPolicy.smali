.class public Lcom/miui/internal/analytics/LastPolicy;
.super Lcom/miui/internal/analytics/Policy;
.source "SourceFile"


# static fields
.field public static final TAG:Ljava/lang/String; = "last"


# instance fields
.field private qk:Ljava/util/Map;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map",
            "<",
            "Ljava/lang/String;",
            "Lcom/miui/internal/analytics/Event;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method public constructor <init>()V
    .locals 1

    .prologue
    .line 10
    invoke-direct {p0}, Lcom/miui/internal/analytics/Policy;-><init>()V

    .line 12
    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    iput-object v0, p0, Lcom/miui/internal/analytics/LastPolicy;->qk:Ljava/util/Map;

    return-void
.end method


# virtual methods
.method public end()V
    .locals 3

    .prologue
    .line 25
    iget-object v0, p0, Lcom/miui/internal/analytics/LastPolicy;->qk:Ljava/util/Map;

    invoke-interface {v0}, Ljava/util/Map;->keySet()Ljava/util/Set;

    move-result-object v0

    invoke-interface {v0}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :goto_0
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_0

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/String;

    .line 26
    iget-object v2, p0, Lcom/miui/internal/analytics/LastPolicy;->qk:Ljava/util/Map;

    invoke-interface {v2, v0}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/analytics/Event;

    invoke-virtual {v0}, Lcom/miui/internal/analytics/Event;->dispatch()V

    goto :goto_0

    .line 28
    :cond_0
    iget-object v0, p0, Lcom/miui/internal/analytics/LastPolicy;->qk:Ljava/util/Map;

    invoke-interface {v0}, Ljava/util/Map;->clear()V

    .line 29
    return-void
.end method

.method public execute(Lcom/miui/internal/analytics/Event;)V
    .locals 2

    .prologue
    .line 20
    iget-object v0, p0, Lcom/miui/internal/analytics/LastPolicy;->qk:Ljava/util/Map;

    invoke-virtual {p1}, Lcom/miui/internal/analytics/Event;->getEventId()Ljava/lang/String;

    move-result-object v1

    invoke-interface {v0, v1, p1}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 21
    return-void
.end method

.method public prepare()V
    .locals 0

    .prologue
    .line 16
    return-void
.end method
