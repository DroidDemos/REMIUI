.class public Lcom/miui/internal/component/plugin/Plugin;
.super Ljava/lang/Object;
.source "SourceFile"


# instance fields
.field private file:Ljava/io/File;

.field private gU:I

.field private gV:Lcom/miui/internal/component/plugin/AccessPermission;

.field private gW:Ljava/util/Map;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map",
            "<",
            "Ljava/lang/String;",
            "Lcom/miui/internal/component/plugin/Dependency;",
            ">;"
        }
    .end annotation
.end field

.field private gX:Ljava/util/Map;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map",
            "<",
            "Ljava/lang/String;",
            "Lcom/miui/internal/component/plugin/Extension;",
            ">;"
        }
    .end annotation
.end field

.field private gY:Ljava/util/Map;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map",
            "<",
            "Ljava/lang/String;",
            "Lcom/miui/internal/component/plugin/Outlet;",
            ">;"
        }
    .end annotation
.end field

.field private group:Ljava/lang/String;

.field private level:I

.field private name:Ljava/lang/String;

.field private versionCode:I

.field private versionName:Ljava/lang/String;


# direct methods
.method public constructor <init>()V
    .locals 1

    .prologue
    .line 11
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 26
    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    iput-object v0, p0, Lcom/miui/internal/component/plugin/Plugin;->gW:Ljava/util/Map;

    .line 27
    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    iput-object v0, p0, Lcom/miui/internal/component/plugin/Plugin;->gX:Ljava/util/Map;

    .line 28
    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    iput-object v0, p0, Lcom/miui/internal/component/plugin/Plugin;->gY:Ljava/util/Map;

    return-void
.end method


# virtual methods
.method public addDependency(Lcom/miui/internal/component/plugin/Dependency;)V
    .locals 2

    .prologue
    .line 107
    iget-object v0, p0, Lcom/miui/internal/component/plugin/Plugin;->gW:Ljava/util/Map;

    invoke-virtual {p1}, Lcom/miui/internal/component/plugin/Dependency;->getName()Ljava/lang/String;

    move-result-object v1

    invoke-interface {v0, v1, p1}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 108
    return-void
.end method

.method public addExtension(Lcom/miui/internal/component/plugin/Extension;)V
    .locals 2

    .prologue
    .line 127
    iget-object v0, p0, Lcom/miui/internal/component/plugin/Plugin;->gX:Ljava/util/Map;

    invoke-virtual {p1}, Lcom/miui/internal/component/plugin/Extension;->getName()Ljava/lang/String;

    move-result-object v1

    invoke-interface {v0, v1, p1}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 128
    return-void
.end method

.method public addOutlet(Lcom/miui/internal/component/plugin/Outlet;)V
    .locals 2

    .prologue
    .line 147
    iget-object v0, p0, Lcom/miui/internal/component/plugin/Plugin;->gY:Ljava/util/Map;

    invoke-virtual {p1}, Lcom/miui/internal/component/plugin/Outlet;->getName()Ljava/lang/String;

    move-result-object v1

    invoke-interface {v0, v1, p1}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 148
    return-void
.end method

.method public clearDependencies()V
    .locals 1

    .prologue
    .line 111
    iget-object v0, p0, Lcom/miui/internal/component/plugin/Plugin;->gW:Ljava/util/Map;

    invoke-interface {v0}, Ljava/util/Map;->clear()V

    .line 112
    return-void
.end method

.method public clearExtensions()V
    .locals 1

    .prologue
    .line 131
    iget-object v0, p0, Lcom/miui/internal/component/plugin/Plugin;->gX:Ljava/util/Map;

    invoke-interface {v0}, Ljava/util/Map;->clear()V

    .line 132
    return-void
.end method

.method public clearOutlets()V
    .locals 1

    .prologue
    .line 151
    iget-object v0, p0, Lcom/miui/internal/component/plugin/Plugin;->gY:Ljava/util/Map;

    invoke-interface {v0}, Ljava/util/Map;->clear()V

    .line 152
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
            "Lcom/miui/internal/component/plugin/Dependency;",
            ">;"
        }
    .end annotation

    .prologue
    .line 95
    iget-object v0, p0, Lcom/miui/internal/component/plugin/Plugin;->gW:Ljava/util/Map;

    return-object v0
.end method

.method public getDependency(Ljava/lang/String;)Lcom/miui/internal/component/plugin/Dependency;
    .locals 1

    .prologue
    .line 103
    iget-object v0, p0, Lcom/miui/internal/component/plugin/Plugin;->gW:Ljava/util/Map;

    invoke-interface {v0, p1}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/component/plugin/Dependency;

    return-object v0
.end method

.method public getExtension(Ljava/lang/String;)Lcom/miui/internal/component/plugin/Extension;
    .locals 1

    .prologue
    .line 123
    iget-object v0, p0, Lcom/miui/internal/component/plugin/Plugin;->gX:Ljava/util/Map;

    invoke-interface {v0, p1}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/component/plugin/Extension;

    return-object v0
.end method

.method public getExtensions()Ljava/util/Map;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/Map",
            "<",
            "Ljava/lang/String;",
            "Lcom/miui/internal/component/plugin/Extension;",
            ">;"
        }
    .end annotation

    .prologue
    .line 115
    iget-object v0, p0, Lcom/miui/internal/component/plugin/Plugin;->gX:Ljava/util/Map;

    return-object v0
.end method

.method public getFile()Ljava/io/File;
    .locals 1

    .prologue
    .line 31
    iget-object v0, p0, Lcom/miui/internal/component/plugin/Plugin;->file:Ljava/io/File;

    return-object v0
.end method

.method public getGroup()Ljava/lang/String;
    .locals 1

    .prologue
    .line 47
    iget-object v0, p0, Lcom/miui/internal/component/plugin/Plugin;->group:Ljava/lang/String;

    return-object v0
.end method

.method public getLevel()I
    .locals 1

    .prologue
    .line 71
    iget v0, p0, Lcom/miui/internal/component/plugin/Plugin;->level:I

    return v0
.end method

.method public getMinCapatibleLevel()I
    .locals 1

    .prologue
    .line 79
    iget v0, p0, Lcom/miui/internal/component/plugin/Plugin;->gU:I

    return v0
.end method

.method public getName()Ljava/lang/String;
    .locals 1

    .prologue
    .line 39
    iget-object v0, p0, Lcom/miui/internal/component/plugin/Plugin;->name:Ljava/lang/String;

    return-object v0
.end method

.method public getOutlet(Ljava/lang/String;)Lcom/miui/internal/component/plugin/Outlet;
    .locals 1

    .prologue
    .line 143
    iget-object v0, p0, Lcom/miui/internal/component/plugin/Plugin;->gY:Ljava/util/Map;

    invoke-interface {v0, p1}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/component/plugin/Outlet;

    return-object v0
.end method

.method public getOutlets()Ljava/util/Map;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/Map",
            "<",
            "Ljava/lang/String;",
            "Lcom/miui/internal/component/plugin/Outlet;",
            ">;"
        }
    .end annotation

    .prologue
    .line 135
    iget-object v0, p0, Lcom/miui/internal/component/plugin/Plugin;->gY:Ljava/util/Map;

    return-object v0
.end method

.method public getResources()Lcom/miui/internal/component/plugin/AccessPermission;
    .locals 1

    .prologue
    .line 87
    iget-object v0, p0, Lcom/miui/internal/component/plugin/Plugin;->gV:Lcom/miui/internal/component/plugin/AccessPermission;

    return-object v0
.end method

.method public getVersionCode()I
    .locals 1

    .prologue
    .line 55
    iget v0, p0, Lcom/miui/internal/component/plugin/Plugin;->versionCode:I

    return v0
.end method

.method public getVersionName()Ljava/lang/String;
    .locals 1

    .prologue
    .line 63
    iget-object v0, p0, Lcom/miui/internal/component/plugin/Plugin;->versionName:Ljava/lang/String;

    return-object v0
.end method

.method public setDependencies(Ljava/util/Map;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/Map",
            "<",
            "Ljava/lang/String;",
            "Lcom/miui/internal/component/plugin/Dependency;",
            ">;)V"
        }
    .end annotation

    .prologue
    .line 99
    iput-object p1, p0, Lcom/miui/internal/component/plugin/Plugin;->gW:Ljava/util/Map;

    .line 100
    return-void
.end method

.method public setExtensions(Ljava/util/Map;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/Map",
            "<",
            "Ljava/lang/String;",
            "Lcom/miui/internal/component/plugin/Extension;",
            ">;)V"
        }
    .end annotation

    .prologue
    .line 119
    iput-object p1, p0, Lcom/miui/internal/component/plugin/Plugin;->gX:Ljava/util/Map;

    .line 120
    return-void
.end method

.method public setFile(Ljava/io/File;)V
    .locals 0

    .prologue
    .line 35
    iput-object p1, p0, Lcom/miui/internal/component/plugin/Plugin;->file:Ljava/io/File;

    .line 36
    return-void
.end method

.method public setGroup(Ljava/lang/String;)V
    .locals 0

    .prologue
    .line 51
    iput-object p1, p0, Lcom/miui/internal/component/plugin/Plugin;->group:Ljava/lang/String;

    .line 52
    return-void
.end method

.method public setLevel(I)V
    .locals 0

    .prologue
    .line 75
    iput p1, p0, Lcom/miui/internal/component/plugin/Plugin;->level:I

    .line 76
    return-void
.end method

.method public setMinCapatibleLevel(I)V
    .locals 0

    .prologue
    .line 83
    iput p1, p0, Lcom/miui/internal/component/plugin/Plugin;->gU:I

    .line 84
    return-void
.end method

.method public setName(Ljava/lang/String;)V
    .locals 0

    .prologue
    .line 43
    iput-object p1, p0, Lcom/miui/internal/component/plugin/Plugin;->name:Ljava/lang/String;

    .line 44
    return-void
.end method

.method public setOutlets(Ljava/util/Map;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/Map",
            "<",
            "Ljava/lang/String;",
            "Lcom/miui/internal/component/plugin/Outlet;",
            ">;)V"
        }
    .end annotation

    .prologue
    .line 139
    iput-object p1, p0, Lcom/miui/internal/component/plugin/Plugin;->gY:Ljava/util/Map;

    .line 140
    return-void
.end method

.method public setResources(Lcom/miui/internal/component/plugin/AccessPermission;)V
    .locals 0

    .prologue
    .line 91
    iput-object p1, p0, Lcom/miui/internal/component/plugin/Plugin;->gV:Lcom/miui/internal/component/plugin/AccessPermission;

    .line 92
    return-void
.end method

.method public setVersionCode(I)V
    .locals 0

    .prologue
    .line 59
    iput p1, p0, Lcom/miui/internal/component/plugin/Plugin;->versionCode:I

    .line 60
    return-void
.end method

.method public setVersionName(Ljava/lang/String;)V
    .locals 0

    .prologue
    .line 67
    iput-object p1, p0, Lcom/miui/internal/component/plugin/Plugin;->versionName:Ljava/lang/String;

    .line 68
    return-void
.end method
