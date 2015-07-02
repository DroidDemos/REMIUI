.class public Lcom/miui/internal/analytics/PolicyHelper;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field private static final SPLITTER:Ljava/lang/String; = ":"

.field private static final TAG:Ljava/lang/String; = "PolicyHelper"


# instance fields
.field private OV:Ljava/util/Map;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map",
            "<",
            "Ljava/lang/String;",
            "Lcom/miui/internal/analytics/Policy;",
            ">;"
        }
    .end annotation
.end field

.field private OW:Lcom/miui/internal/analytics/ObjectBuilder;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lcom/miui/internal/analytics/ObjectBuilder",
            "<",
            "Lcom/miui/internal/analytics/Policy;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method public constructor <init>()V
    .locals 3

    .prologue
    .line 21
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 17
    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    iput-object v0, p0, Lcom/miui/internal/analytics/PolicyHelper;->OV:Ljava/util/Map;

    .line 18
    new-instance v0, Lcom/miui/internal/analytics/ObjectBuilder;

    invoke-direct {v0}, Lcom/miui/internal/analytics/ObjectBuilder;-><init>()V

    iput-object v0, p0, Lcom/miui/internal/analytics/PolicyHelper;->OW:Lcom/miui/internal/analytics/ObjectBuilder;

    .line 22
    iget-object v0, p0, Lcom/miui/internal/analytics/PolicyHelper;->OW:Lcom/miui/internal/analytics/ObjectBuilder;

    const-class v1, Lcom/miui/internal/analytics/LastPolicy;

    const-string v2, "last"

    invoke-virtual {v0, v1, v2}, Lcom/miui/internal/analytics/ObjectBuilder;->registerClass(Ljava/lang/Class;Ljava/lang/String;)Z

    .line 23
    iget-object v0, p0, Lcom/miui/internal/analytics/PolicyHelper;->OW:Lcom/miui/internal/analytics/ObjectBuilder;

    const-class v1, Lcom/miui/internal/analytics/NormalPolicy;

    const-string v2, "normal"

    invoke-virtual {v0, v1, v2}, Lcom/miui/internal/analytics/ObjectBuilder;->registerClass(Ljava/lang/Class;Ljava/lang/String;)Z

    .line 24
    iget-object v0, p0, Lcom/miui/internal/analytics/PolicyHelper;->OW:Lcom/miui/internal/analytics/ObjectBuilder;

    const-class v1, Lcom/miui/internal/analytics/CountPolicy;

    const-string v2, "count"

    invoke-virtual {v0, v1, v2}, Lcom/miui/internal/analytics/ObjectBuilder;->registerClass(Ljava/lang/Class;Ljava/lang/String;)Z

    .line 25
    return-void
.end method


# virtual methods
.method public clear()V
    .locals 1

    .prologue
    .line 28
    iget-object v0, p0, Lcom/miui/internal/analytics/PolicyHelper;->OV:Ljava/util/Map;

    invoke-interface {v0}, Ljava/util/Map;->clear()V

    .line 29
    return-void
.end method

.method public end()V
    .locals 3

    .prologue
    .line 60
    iget-object v0, p0, Lcom/miui/internal/analytics/PolicyHelper;->OV:Ljava/util/Map;

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

    .line 61
    iget-object v2, p0, Lcom/miui/internal/analytics/PolicyHelper;->OV:Ljava/util/Map;

    invoke-interface {v2, v0}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/analytics/Policy;

    invoke-virtual {v0}, Lcom/miui/internal/analytics/Policy;->end()V

    goto :goto_0

    .line 63
    :cond_0
    return-void
.end method

.method public getPolicy(Ljava/lang/String;)Lcom/miui/internal/analytics/Policy;
    .locals 7

    .prologue
    const/4 v5, 0x1

    .line 33
    const/4 v2, 0x0

    .line 34
    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v1

    if-nez v1, :cond_3

    .line 36
    :try_start_0
    const-string v1, ":"

    invoke-virtual {p1, v1}, Ljava/lang/String;->split(Ljava/lang/String;)[Ljava/lang/String;

    move-result-object v3

    .line 37
    array-length v1, v3

    if-lez v1, :cond_3

    .line 38
    iget-object v1, p0, Lcom/miui/internal/analytics/PolicyHelper;->OV:Ljava/util/Map;

    const/4 v4, 0x0

    aget-object v4, v3, v4

    invoke-interface {v1, v4}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/miui/internal/analytics/Policy;
    :try_end_0
    .catch Ljava/util/regex/PatternSyntaxException; {:try_start_0 .. :try_end_0} :catch_1

    .line 39
    :try_start_1
    array-length v2, v3

    if-le v2, v5, :cond_1

    const/4 v2, 0x1

    aget-object v2, v3, v2

    move-object v3, v2

    .line 40
    :goto_0
    if-nez v1, :cond_2

    .line 41
    iget-object v2, p0, Lcom/miui/internal/analytics/PolicyHelper;->OW:Lcom/miui/internal/analytics/ObjectBuilder;

    invoke-virtual {v2, p1}, Lcom/miui/internal/analytics/ObjectBuilder;->buildObject(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v2

    move-object v0, v2

    check-cast v0, Lcom/miui/internal/analytics/Policy;

    move-object v1, v0

    .line 42
    if-eqz v1, :cond_0

    .line 43
    iget-object v2, p0, Lcom/miui/internal/analytics/PolicyHelper;->OV:Ljava/util/Map;

    invoke-interface {v2, p1, v1}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 44
    invoke-virtual {v1, v3}, Lcom/miui/internal/analytics/Policy;->setParam(Ljava/lang/String;)V

    .line 45
    invoke-virtual {v1}, Lcom/miui/internal/analytics/Policy;->prepare()V

    .line 56
    :cond_0
    :goto_1
    return-object v1

    .line 39
    :cond_1
    const-string v2, ""

    move-object v3, v2

    goto :goto_0

    .line 48
    :cond_2
    invoke-virtual {v1, v3}, Lcom/miui/internal/analytics/Policy;->setParam(Ljava/lang/String;)V
    :try_end_1
    .catch Ljava/util/regex/PatternSyntaxException; {:try_start_1 .. :try_end_1} :catch_0

    goto :goto_1

    .line 51
    :catch_0
    move-exception v2

    .line 52
    :goto_2
    const-string v3, "PolicyHelper"

    const-string v4, "PatternSyntaxException catched when getPolicy"

    invoke-static {v3, v4, v2}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    goto :goto_1

    .line 51
    :catch_1
    move-exception v1

    move-object v6, v1

    move-object v1, v2

    move-object v2, v6

    goto :goto_2

    :cond_3
    move-object v1, v2

    goto :goto_1
.end method
