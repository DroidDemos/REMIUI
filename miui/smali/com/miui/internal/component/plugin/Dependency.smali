.class public Lcom/miui/internal/component/plugin/Dependency;
.super Ljava/lang/Object;
.source "SourceFile"


# instance fields
.field private Ce:Z

.field private KQ:Z

.field private minLevel:I

.field private name:Ljava/lang/String;

.field private targetLevel:I


# direct methods
.method public constructor <init>()V
    .locals 0

    .prologue
    .line 7
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public getMinLevel()I
    .locals 1

    .prologue
    .line 41
    iget v0, p0, Lcom/miui/internal/component/plugin/Dependency;->minLevel:I

    return v0
.end method

.method public getName()Ljava/lang/String;
    .locals 1

    .prologue
    .line 17
    iget-object v0, p0, Lcom/miui/internal/component/plugin/Dependency;->name:Ljava/lang/String;

    return-object v0
.end method

.method public getTargetLevel()I
    .locals 1

    .prologue
    .line 49
    iget v0, p0, Lcom/miui/internal/component/plugin/Dependency;->targetLevel:I

    return v0
.end method

.method public isOptional()Z
    .locals 1

    .prologue
    .line 33
    iget-boolean v0, p0, Lcom/miui/internal/component/plugin/Dependency;->Ce:Z

    return v0
.end method

.method public isResourcesRequired()Z
    .locals 1

    .prologue
    .line 25
    iget-boolean v0, p0, Lcom/miui/internal/component/plugin/Dependency;->KQ:Z

    return v0
.end method

.method public setMinLevel(I)V
    .locals 0

    .prologue
    .line 45
    iput p1, p0, Lcom/miui/internal/component/plugin/Dependency;->minLevel:I

    .line 46
    return-void
.end method

.method public setName(Ljava/lang/String;)V
    .locals 0

    .prologue
    .line 21
    iput-object p1, p0, Lcom/miui/internal/component/plugin/Dependency;->name:Ljava/lang/String;

    .line 22
    return-void
.end method

.method public setOptional(Z)V
    .locals 0

    .prologue
    .line 37
    iput-boolean p1, p0, Lcom/miui/internal/component/plugin/Dependency;->Ce:Z

    .line 38
    return-void
.end method

.method public setResourcesRequired(Z)V
    .locals 0

    .prologue
    .line 29
    iput-boolean p1, p0, Lcom/miui/internal/component/plugin/Dependency;->KQ:Z

    .line 30
    return-void
.end method

.method public setTargetLevel(I)V
    .locals 0

    .prologue
    .line 53
    iput p1, p0, Lcom/miui/internal/component/plugin/Dependency;->targetLevel:I

    .line 54
    return-void
.end method
