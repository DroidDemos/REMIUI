.class public Lcom/miui/internal/analytics/ObjectBuilder;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/internal/analytics/ObjectBuilder$a;
    }
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "<T:",
        "Ljava/lang/Object;",
        ">",
        "Ljava/lang/Object;"
    }
.end annotation


# static fields
.field private static final TAG:Ljava/lang/String; = "ObjectBuilder"


# instance fields
.field private RA:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList",
            "<",
            "Lcom/miui/internal/analytics/ObjectBuilder",
            "<TT;>.a;>;"
        }
    .end annotation
.end field


# direct methods
.method public constructor <init>()V
    .locals 1

    .prologue
    .line 11
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 23
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/miui/internal/analytics/ObjectBuilder;->RA:Ljava/util/ArrayList;

    return-void
.end method


# virtual methods
.method public buildObject(Ljava/lang/String;)Ljava/lang/Object;
    .locals 5
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            ")TT;"
        }
    .end annotation

    .prologue
    .line 26
    const/4 v1, 0x0

    .line 27
    iget-object v0, p0, Lcom/miui/internal/analytics/ObjectBuilder;->RA:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v2

    :goto_0
    invoke-interface {v2}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_1

    invoke-interface {v2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/analytics/ObjectBuilder$a;

    .line 28
    iget-object v3, v0, Lcom/miui/internal/analytics/ObjectBuilder$a;->aV:Ljava/lang/String;

    invoke-virtual {v3, p1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_0

    .line 30
    :try_start_0
    iget-object v0, v0, Lcom/miui/internal/analytics/ObjectBuilder$a;->qh:Ljava/lang/Class;

    invoke-virtual {v0}, Ljava/lang/Class;->newInstance()Ljava/lang/Object;
    :try_end_0
    .catch Ljava/lang/InstantiationException; {:try_start_0 .. :try_end_0} :catch_0
    .catch Ljava/lang/IllegalAccessException; {:try_start_0 .. :try_end_0} :catch_1

    move-result-object v0

    :goto_1
    move-object v1, v0

    .line 35
    goto :goto_0

    .line 31
    :catch_0
    move-exception v0

    .line 32
    const-string v3, "ObjectBuilder"

    const-string v4, "InstantiationException catched when buildObject"

    invoke-static {v3, v4, v0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    move-object v0, v1

    .line 35
    goto :goto_1

    .line 33
    :catch_1
    move-exception v0

    .line 34
    const-string v3, "ObjectBuilder"

    const-string v4, "IllegalAccessException catched when buildObject"

    invoke-static {v3, v4, v0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    :cond_0
    move-object v0, v1

    goto :goto_1

    .line 39
    :cond_1
    return-object v1
.end method

.method public registerClass(Ljava/lang/Class;Ljava/lang/String;)Z
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/Class",
            "<+TT;>;",
            "Ljava/lang/String;",
            ")Z"
        }
    .end annotation

    .prologue
    .line 43
    iget-object v0, p0, Lcom/miui/internal/analytics/ObjectBuilder;->RA:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :cond_0
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_1

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/analytics/ObjectBuilder$a;

    .line 44
    iget-object v0, v0, Lcom/miui/internal/analytics/ObjectBuilder$a;->aV:Ljava/lang/String;

    invoke-virtual {p2, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 45
    const/4 v0, 0x0

    .line 49
    :goto_0
    return v0

    :cond_1
    iget-object v0, p0, Lcom/miui/internal/analytics/ObjectBuilder;->RA:Ljava/util/ArrayList;

    new-instance v1, Lcom/miui/internal/analytics/ObjectBuilder$a;

    invoke-direct {v1, p0, p1, p2}, Lcom/miui/internal/analytics/ObjectBuilder$a;-><init>(Lcom/miui/internal/analytics/ObjectBuilder;Ljava/lang/Class;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    move-result v0

    goto :goto_0
.end method
