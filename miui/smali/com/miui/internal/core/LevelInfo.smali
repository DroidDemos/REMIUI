.class public Lcom/miui/internal/core/LevelInfo;
.super Ljava/lang/Object;
.source "SourceFile"


# instance fields
.field private maxLevel:I

.field private minLevel:I

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
.method public getMaxLevel()I
    .locals 1

    .prologue
    .line 30
    iget v0, p0, Lcom/miui/internal/core/LevelInfo;->maxLevel:I

    return v0
.end method

.method public getMinLevel()I
    .locals 1

    .prologue
    .line 14
    iget v0, p0, Lcom/miui/internal/core/LevelInfo;->minLevel:I

    return v0
.end method

.method public getTargetLevel()I
    .locals 1

    .prologue
    .line 22
    iget v0, p0, Lcom/miui/internal/core/LevelInfo;->targetLevel:I

    return v0
.end method

.method public setMaxLevel(I)V
    .locals 0

    .prologue
    .line 34
    iput p1, p0, Lcom/miui/internal/core/LevelInfo;->maxLevel:I

    .line 35
    return-void
.end method

.method public setMinLevel(I)V
    .locals 0

    .prologue
    .line 18
    iput p1, p0, Lcom/miui/internal/core/LevelInfo;->minLevel:I

    .line 19
    return-void
.end method

.method public setTargetLevel(I)V
    .locals 0

    .prologue
    .line 26
    iput p1, p0, Lcom/miui/internal/core/LevelInfo;->targetLevel:I

    .line 27
    return-void
.end method
