.class public Lcom/miui/internal/component/module/Module;
.super Ljava/lang/Object;
.source "SourceFile"


# instance fields
.field private iO:Ljava/lang/String;

.field private name:Ljava/lang/String;


# direct methods
.method public constructor <init>()V
    .locals 0

    .prologue
    .line 7
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public getName()Ljava/lang/String;
    .locals 1

    .prologue
    .line 14
    iget-object v0, p0, Lcom/miui/internal/component/module/Module;->name:Ljava/lang/String;

    return-object v0
.end method

.method public getSystemApkName()Ljava/lang/String;
    .locals 1

    .prologue
    .line 22
    iget-object v0, p0, Lcom/miui/internal/component/module/Module;->iO:Ljava/lang/String;

    return-object v0
.end method

.method public setName(Ljava/lang/String;)V
    .locals 0

    .prologue
    .line 18
    iput-object p1, p0, Lcom/miui/internal/component/module/Module;->name:Ljava/lang/String;

    .line 19
    return-void
.end method

.method public setSystemApkName(Ljava/lang/String;)V
    .locals 0

    .prologue
    .line 26
    iput-object p1, p0, Lcom/miui/internal/component/module/Module;->iO:Ljava/lang/String;

    .line 27
    return-void
.end method
