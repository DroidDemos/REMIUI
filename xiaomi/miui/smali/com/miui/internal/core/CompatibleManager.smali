.class public Lcom/miui/internal/core/CompatibleManager;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field private static final On:I = 0x1


# instance fields
.field private Oo:Lcom/miui/internal/core/Manifest;

.field private mContext:Landroid/content/Context;


# direct methods
.method public constructor <init>(Landroid/content/Context;Lcom/miui/internal/core/Manifest;)V
    .locals 0

    .prologue
    .line 16
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 17
    iput-object p1, p0, Lcom/miui/internal/core/CompatibleManager;->mContext:Landroid/content/Context;

    .line 18
    iput-object p2, p0, Lcom/miui/internal/core/CompatibleManager;->Oo:Lcom/miui/internal/core/Manifest;

    .line 19
    return-void
.end method


# virtual methods
.method public isCompatible()Z
    .locals 2

    .prologue
    const/4 v0, 0x1

    .line 22
    iget-object v1, p0, Lcom/miui/internal/core/CompatibleManager;->Oo:Lcom/miui/internal/core/Manifest;

    invoke-virtual {v1}, Lcom/miui/internal/core/Manifest;->getSdkDependency()Lcom/miui/internal/core/LevelInfo;

    move-result-object v1

    invoke-virtual {v1}, Lcom/miui/internal/core/LevelInfo;->getMinLevel()I

    move-result v1

    if-gt v1, v0, :cond_0

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public isCurrent()Z
    .locals 2

    .prologue
    const/4 v0, 0x1

    .line 26
    iget-object v1, p0, Lcom/miui/internal/core/CompatibleManager;->Oo:Lcom/miui/internal/core/Manifest;

    invoke-virtual {v1}, Lcom/miui/internal/core/Manifest;->getSdkDependency()Lcom/miui/internal/core/LevelInfo;

    move-result-object v1

    invoke-virtual {v1}, Lcom/miui/internal/core/LevelInfo;->getTargetLevel()I

    move-result v1

    if-ne v1, v0, :cond_0

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method
