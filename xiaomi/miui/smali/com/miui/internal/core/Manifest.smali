.class public Lcom/miui/internal/core/Manifest;
.super Ljava/lang/Object;
.source "SourceFile"


# instance fields
.field private KQ:Z

.field private QG:Lcom/miui/internal/core/LevelInfo;

.field private gW:Ljava/util/Map;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map",
            "<",
            "Ljava/lang/String;",
            "Lcom/miui/internal/core/LevelInfo;",
            ">;"
        }
    .end annotation
.end field

.field private level:I

.field private name:Ljava/lang/String;


# direct methods
.method public constructor <init>()V
    .locals 1

    .prologue
    .line 10
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 17
    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    iput-object v0, p0, Lcom/miui/internal/core/Manifest;->gW:Ljava/util/Map;

    return-void
.end method


# virtual methods
.method public addDependency(Ljava/lang/String;Lcom/miui/internal/core/LevelInfo;)V
    .locals 1

    .prologue
    .line 64
    iget-object v0, p0, Lcom/miui/internal/core/Manifest;->gW:Ljava/util/Map;

    invoke-interface {v0, p1, p2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 65
    return-void
.end method

.method public clearDependencies()V
    .locals 1

    .prologue
    .line 68
    iget-object v0, p0, Lcom/miui/internal/core/Manifest;->gW:Ljava/util/Map;

    invoke-interface {v0}, Ljava/util/Map;->clear()V

    .line 69
    return-void
.end method

.method public getDependencies()Ljava/util/Map;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/Map",
            "<",
            "Ljava/lang/String;",
            "Lcom/miui/internal/core/LevelInfo;",
            ">;"
        }
    .end annotation

    .prologue
    .line 52
    iget-object v0, p0, Lcom/miui/internal/core/Manifest;->gW:Ljava/util/Map;

    return-object v0
.end method

.method public getDependency(Ljava/lang/String;)Lcom/miui/internal/core/LevelInfo;
    .locals 1

    .prologue
    .line 60
    iget-object v0, p0, Lcom/miui/internal/core/Manifest;->gW:Ljava/util/Map;

    invoke-interface {v0, p1}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/core/LevelInfo;

    return-object v0
.end method

.method public getLevel()I
    .locals 1

    .prologue
    .line 28
    iget v0, p0, Lcom/miui/internal/core/Manifest;->level:I

    return v0
.end method

.method public getName()Ljava/lang/String;
    .locals 1

    .prologue
    .line 20
    iget-object v0, p0, Lcom/miui/internal/core/Manifest;->name:Ljava/lang/String;

    return-object v0
.end method

.method public getSdkDependency()Lcom/miui/internal/core/LevelInfo;
    .locals 1

    .prologue
    .line 44
    iget-object v0, p0, Lcom/miui/internal/core/Manifest;->QG:Lcom/miui/internal/core/LevelInfo;

    return-object v0
.end method

.method public isResourcesRequired()Z
    .locals 1

    .prologue
    .line 36
    iget-boolean v0, p0, Lcom/miui/internal/core/Manifest;->KQ:Z

    return v0
.end method

.method public setDependencies(Ljava/util/Map;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/Map",
            "<",
            "Ljava/lang/String;",
            "Lcom/miui/internal/core/LevelInfo;",
            ">;)V"
        }
    .end annotation

    .prologue
    .line 56
    iput-object p1, p0, Lcom/miui/internal/core/Manifest;->gW:Ljava/util/Map;

    .line 57
    return-void
.end method

.method public setLevel(I)V
    .locals 0

    .prologue
    .line 32
    iput p1, p0, Lcom/miui/internal/core/Manifest;->level:I

    .line 33
    return-void
.end method

.method public setName(Ljava/lang/String;)V
    .locals 0

    .prologue
    .line 24
    iput-object p1, p0, Lcom/miui/internal/core/Manifest;->name:Ljava/lang/String;

    .line 25
    return-void
.end method

.method public setResourcesRequired(Z)V
    .locals 0

    .prologue
    .line 40
    iput-boolean p1, p0, Lcom/miui/internal/core/Manifest;->KQ:Z

    .line 41
    return-void
.end method

.method public setSdkDependency(Lcom/miui/internal/core/LevelInfo;)V
    .locals 0

    .prologue
    .line 48
    iput-object p1, p0, Lcom/miui/internal/core/Manifest;->QG:Lcom/miui/internal/core/LevelInfo;

    .line 49
    return-void
.end method
