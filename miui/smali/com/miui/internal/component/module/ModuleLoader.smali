.class public Lcom/miui/internal/component/module/ModuleLoader;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field private static final gr:Ljava/lang/String; = "modules-opt"

.field private static final gs:Ljava/lang/String; = ".apk"

.field private static final gt:Ljava/lang/String; = ".xml"

.field private static final gu:Ljava/lang/String; = "modules.xml"


# instance fields
.field private gv:Ljava/io/File;

.field private gw:Ljava/io/File;

.field private gx:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List",
            "<",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method public constructor <init>(Ljava/io/File;)V
    .locals 3

    .prologue
    .line 28
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 26
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/miui/internal/component/module/ModuleLoader;->gx:Ljava/util/List;

    .line 29
    iput-object p1, p0, Lcom/miui/internal/component/module/ModuleLoader;->gv:Ljava/io/File;

    .line 30
    new-instance v0, Ljava/io/File;

    iget-object v1, p0, Lcom/miui/internal/component/module/ModuleLoader;->gv:Ljava/io/File;

    invoke-virtual {v1}, Ljava/io/File;->getParentFile()Ljava/io/File;

    move-result-object v1

    const-string v2, "modules-opt"

    invoke-direct {v0, v1, v2}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    iput-object v0, p0, Lcom/miui/internal/component/module/ModuleLoader;->gw:Ljava/io/File;

    .line 31
    iget-object v0, p0, Lcom/miui/internal/component/module/ModuleLoader;->gv:Ljava/io/File;

    invoke-virtual {v0}, Ljava/io/File;->mkdirs()Z

    .line 32
    iget-object v0, p0, Lcom/miui/internal/component/module/ModuleLoader;->gw:Ljava/io/File;

    invoke-virtual {v0}, Ljava/io/File;->mkdirs()Z

    .line 33
    invoke-direct {p0}, Lcom/miui/internal/component/module/ModuleLoader;->ac()V

    .line 34
    return-void
.end method

.method private a(Ljava/io/File;)Ljava/util/List;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/io/File;",
            ")",
            "Ljava/util/List",
            "<",
            "Lcom/miui/internal/component/module/Module;",
            ">;"
        }
    .end annotation

    .prologue
    .line 70
    invoke-virtual {p1}, Ljava/io/File;->exists()Z

    move-result v0

    if-nez v0, :cond_0

    .line 71
    const/4 v0, 0x0

    .line 75
    :goto_0
    return-object v0

    .line 73
    :cond_0
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    goto :goto_0
.end method

.method private a(Ljava/io/File;Z)V
    .locals 2

    .prologue
    .line 58
    invoke-virtual {p1}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object v1

    .line 60
    const/4 v0, 0x0

    .line 61
    if-eqz p2, :cond_0

    .line 62
    iget-object v0, p0, Lcom/miui/internal/component/module/ModuleLoader;->gw:Ljava/io/File;

    invoke-virtual {v0}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object v0

    .line 64
    :cond_0
    invoke-static {v1, v0}, Lcom/miui/internal/component/module/ModuleLoader;->loadClass(Ljava/lang/String;Ljava/lang/String;)V

    .line 66
    invoke-static {v1}, Lcom/miui/internal/component/module/ModuleLoader;->loadResource(Ljava/lang/String;)V

    .line 67
    return-void
.end method

.method private ac()V
    .locals 7

    .prologue
    .line 37
    new-instance v0, Ljava/io/File;

    iget-object v1, p0, Lcom/miui/internal/component/module/ModuleLoader;->gv:Ljava/io/File;

    const-string v2, "modules.xml"

    invoke-direct {v0, v1, v2}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    invoke-direct {p0, v0}, Lcom/miui/internal/component/module/ModuleLoader;->a(Ljava/io/File;)Ljava/util/List;

    move-result-object v0

    .line 38
    if-nez v0, :cond_0

    .line 39
    invoke-direct {p0}, Lcom/miui/internal/component/module/ModuleLoader;->ad()Ljava/util/List;

    move-result-object v0

    .line 41
    :cond_0
    invoke-interface {v0}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v3

    :goto_0
    invoke-interface {v3}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_3

    invoke-interface {v3}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/component/module/Module;

    .line 42
    const/4 v2, 0x1

    .line 43
    new-instance v1, Ljava/io/File;

    iget-object v4, p0, Lcom/miui/internal/component/module/ModuleLoader;->gv:Ljava/io/File;

    new-instance v5, Ljava/lang/StringBuilder;

    invoke-direct {v5}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v0}, Lcom/miui/internal/component/module/Module;->getName()Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v5

    const-string v6, ".apk"

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v5

    invoke-virtual {v5}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v5

    invoke-direct {v1, v4, v5}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    .line 44
    invoke-virtual {v1}, Ljava/io/File;->exists()Z

    move-result v4

    if-nez v4, :cond_1

    .line 45
    new-instance v1, Ljava/io/File;

    sget-object v2, Lcom/miui/internal/util/PackageConstants;->sApplication:Landroid/app/Application;

    invoke-virtual {v0}, Lcom/miui/internal/component/module/Module;->getName()Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v0}, Lcom/miui/internal/component/module/Module;->getSystemApkName()Ljava/lang/String;

    move-result-object v5

    invoke-static {v2, v4, v5}, Lcom/miui/internal/util/PackageHelper;->getApkPath(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    invoke-direct {v1, v2}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    .line 47
    const/4 v2, 0x0

    .line 49
    :cond_1
    invoke-virtual {v1}, Ljava/io/File;->exists()Z

    move-result v4

    if-nez v4, :cond_2

    .line 50
    new-instance v1, Lcom/miui/internal/component/module/ModuleNotFoundException;

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "no such module found: "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v0}, Lcom/miui/internal/component/module/Module;->getName()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-direct {v1, v0}, Lcom/miui/internal/component/module/ModuleNotFoundException;-><init>(Ljava/lang/String;)V

    throw v1

    .line 52
    :cond_2
    invoke-direct {p0, v1, v2}, Lcom/miui/internal/component/module/ModuleLoader;->a(Ljava/io/File;Z)V

    .line 53
    iget-object v0, p0, Lcom/miui/internal/component/module/ModuleLoader;->gx:Ljava/util/List;

    invoke-virtual {v1}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object v1

    invoke-interface {v0, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto :goto_0

    .line 55
    :cond_3
    return-void
.end method

.method private ad()Ljava/util/List;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/List",
            "<",
            "Lcom/miui/internal/component/module/Module;",
            ">;"
        }
    .end annotation

    .prologue
    .line 79
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    .line 86
    return-object v0
.end method

.method public static loadClass(Ljava/lang/String;Ljava/lang/String;)V
    .locals 2

    .prologue
    .line 90
    const/4 v0, 0x0

    sget-object v1, Lcom/miui/internal/util/PackageConstants;->sApplication:Landroid/app/Application;

    invoke-virtual {v1}, Landroid/app/Application;->getClassLoader()Ljava/lang/ClassLoader;

    move-result-object v1

    invoke-static {p0, p1, v0, v1}, Lcom/miui/internal/component/module/ModuleClassLoader;->load(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/ClassLoader;)Z

    .line 91
    return-void
.end method

.method public static loadResource(Ljava/lang/String;)V
    .locals 0

    .prologue
    .line 94
    invoke-static {p0}, Lcom/miui/internal/component/module/ModuleResourceLoader;->load(Ljava/lang/String;)V

    .line 95
    return-void
.end method
