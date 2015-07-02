.class public Lcom/miui/internal/component/ComponentManager;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field private static final tB:Ljava/lang/String; = "modules"

.field private static final tC:Ljava/lang/String; = "plugins"

.field private static final tD:Ljava/lang/String; = "modules"

.field private static final tE:Ljava/lang/String; = "plugins"

.field private static final tF:Ljava/lang/String; = "miuisdk"

.field private static tG:Lcom/miui/internal/component/module/ModuleLoader;

.field private static tH:Lcom/miui/internal/component/plugin/PluginLoader;


# direct methods
.method public constructor <init>()V
    .locals 0

    .prologue
    .line 24
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method private static a(Landroid/content/Context;Ljava/lang/String;Ljava/io/File;)V
    .locals 8

    .prologue
    .line 51
    :try_start_0
    invoke-virtual {p2}, Ljava/io/File;->mkdirs()Z

    .line 52
    invoke-virtual {p0}, Landroid/content/Context;->getAssets()Landroid/content/res/AssetManager;

    move-result-object v1

    .line 53
    invoke-virtual {v1, p1}, Landroid/content/res/AssetManager;->list(Ljava/lang/String;)[Ljava/lang/String;

    move-result-object v2

    .line 54
    array-length v3, v2

    const/4 v0, 0x0

    :goto_0
    if-ge v0, v3, :cond_0

    aget-object v4, v2, v0

    .line 55
    new-instance v5, Ljava/io/File;

    invoke-direct {v5, p2, v4}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    .line 56
    invoke-virtual {v5}, Ljava/io/File;->getParentFile()Ljava/io/File;

    move-result-object v6

    invoke-virtual {v6}, Ljava/io/File;->mkdirs()Z

    .line 57
    new-instance v6, Ljava/lang/StringBuilder;

    invoke-direct {v6}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v6, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v6

    const-string v7, "/"

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v6

    invoke-virtual {v6, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v1, v4}, Landroid/content/res/AssetManager;->open(Ljava/lang/String;)Ljava/io/InputStream;

    move-result-object v4

    invoke-static {v4, v5}, Lmiui/os/FileUtils;->copyToFile(Ljava/io/InputStream;Ljava/io/File;)Z
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_0

    .line 54
    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    .line 59
    :catch_0
    move-exception v0

    .line 60
    invoke-virtual {v0}, Ljava/io/IOException;->printStackTrace()V

    .line 62
    :cond_0
    return-void
.end method

.method private static d(Landroid/content/Context;)Ljava/io/File;
    .locals 3

    .prologue
    .line 38
    new-instance v0, Ljava/io/File;

    invoke-static {p0}, Lcom/miui/internal/component/ComponentManager;->f(Landroid/content/Context;)Ljava/io/File;

    move-result-object v1

    const-string v2, "modules"

    invoke-direct {v0, v1, v2}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    return-object v0
.end method

.method private static e(Landroid/content/Context;)Ljava/io/File;
    .locals 3

    .prologue
    .line 42
    new-instance v0, Ljava/io/File;

    invoke-static {p0}, Lcom/miui/internal/component/ComponentManager;->f(Landroid/content/Context;)Ljava/io/File;

    move-result-object v1

    const-string v2, "plugins"

    invoke-direct {v0, v1, v2}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    return-object v0
.end method

.method private static f(Landroid/content/Context;)Ljava/io/File;
    .locals 3

    .prologue
    .line 46
    new-instance v0, Ljava/io/File;

    invoke-virtual {p0}, Landroid/content/Context;->getFilesDir()Ljava/io/File;

    move-result-object v1

    const-string v2, "miuisdk"

    invoke-direct {v0, v1, v2}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    return-object v0
.end method

.method private static g(Landroid/content/Context;)V
    .locals 3

    .prologue
    .line 73
    invoke-virtual {p0}, Landroid/content/Context;->getFilesDir()Ljava/io/File;

    move-result-object v0

    .line 74
    new-instance v1, Ljava/io/File;

    const-string v2, "modules"

    invoke-direct {v1, v0, v2}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    invoke-virtual {v1}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Lmiui/os/FileUtils;->rm(Ljava/lang/String;)Z

    .line 75
    new-instance v1, Ljava/io/File;

    const-string v2, "dalvik-cache"

    invoke-direct {v1, v0, v2}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    invoke-virtual {v1}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Lmiui/os/FileUtils;->rm(Ljava/lang/String;)Z

    .line 76
    new-instance v1, Ljava/io/File;

    const-string v2, "plugins"

    invoke-direct {v1, v0, v2}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    invoke-virtual {v1}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Lmiui/os/FileUtils;->rm(Ljava/lang/String;)Z

    .line 77
    new-instance v1, Ljava/io/File;

    const-string v2, "opt"

    invoke-direct {v1, v0, v2}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    invoke-virtual {v1}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lmiui/os/FileUtils;->rm(Ljava/lang/String;)Z

    .line 78
    return-void
.end method

.method private static h(Landroid/content/Context;)V
    .locals 6

    .prologue
    const/4 v2, 0x0

    .line 81
    .line 82
    invoke-static {p0}, Lcom/miui/internal/component/ComponentManager;->d(Landroid/content/Context;)Ljava/io/File;

    move-result-object v1

    .line 84
    const-string v0, "com.miui.sdk"

    invoke-virtual {p0}, Landroid/content/Context;->getPackageName()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v0, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_3

    .line 87
    :try_start_0
    const-string v0, "com.miui.sdk"

    const/4 v3, 0x2

    invoke-virtual {p0, v0, v3}, Landroid/content/Context;->createPackageContext(Ljava/lang/String;I)Landroid/content/Context;
    :try_end_0
    .catch Landroid/content/pm/PackageManager$NameNotFoundException; {:try_start_0 .. :try_end_0} :catch_0

    move-result-object v0

    .line 92
    :goto_0
    if-eqz v0, :cond_6

    .line 93
    invoke-static {v0}, Lcom/miui/internal/component/ComponentManager;->d(Landroid/content/Context;)Ljava/io/File;

    move-result-object v0

    .line 96
    :goto_1
    if-eqz v0, :cond_0

    invoke-virtual {v0}, Ljava/io/File;->exists()Z

    move-result v2

    if-nez v2, :cond_2

    .line 99
    :cond_0
    invoke-virtual {v1}, Ljava/io/File;->exists()Z

    move-result v0

    if-nez v0, :cond_5

    .line 100
    const-string v0, "modules"

    invoke-static {p0, v0, v1}, Lcom/miui/internal/component/ComponentManager;->a(Landroid/content/Context;Ljava/lang/String;Ljava/io/File;)V

    move-object v0, v1

    .line 117
    :cond_1
    :goto_2
    new-instance v1, Lcom/miui/internal/component/module/ModuleLoader;

    invoke-direct {v1, v0}, Lcom/miui/internal/component/module/ModuleLoader;-><init>(Ljava/io/File;)V

    sput-object v1, Lcom/miui/internal/component/ComponentManager;->tG:Lcom/miui/internal/component/module/ModuleLoader;

    .line 118
    return-void

    .line 89
    :catch_0
    move-exception v0

    .line 90
    invoke-virtual {v0}, Landroid/content/pm/PackageManager$NameNotFoundException;->printStackTrace()V

    move-object v0, v2

    goto :goto_0

    .line 102
    :cond_2
    invoke-virtual {v1}, Ljava/io/File;->exists()Z

    move-result v2

    if-eqz v2, :cond_1

    .line 104
    invoke-virtual {v1}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Lmiui/os/FileUtils;->rm(Ljava/lang/String;)Z

    goto :goto_2

    .line 108
    :cond_3
    invoke-virtual {v1}, Ljava/io/File;->exists()Z

    move-result v0

    if-nez v0, :cond_4

    .line 109
    const-string v0, "modules"

    invoke-static {p0, v0, v1}, Lcom/miui/internal/component/ComponentManager;->a(Landroid/content/Context;Ljava/lang/String;Ljava/io/File;)V

    .line 111
    :cond_4
    invoke-virtual {v1}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object v0

    const/16 v2, 0x1f9

    invoke-static {v0, v2}, Lmiui/os/FileUtils;->chmod(Ljava/lang/String;I)Z

    .line 112
    invoke-virtual {v1}, Ljava/io/File;->list()[Ljava/lang/String;

    move-result-object v2

    array-length v3, v2

    const/4 v0, 0x0

    :goto_3
    if-ge v0, v3, :cond_5

    aget-object v4, v2, v0

    .line 113
    new-instance v5, Ljava/io/File;

    invoke-direct {v5, v1, v4}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    invoke-virtual {v5}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object v4

    const/16 v5, 0x1a4

    invoke-static {v4, v5}, Lmiui/os/FileUtils;->chmod(Ljava/lang/String;I)Z

    .line 112
    add-int/lit8 v0, v0, 0x1

    goto :goto_3

    :cond_5
    move-object v0, v1

    goto :goto_2

    :cond_6
    move-object v0, v2

    goto :goto_1
.end method

.method private static i(Landroid/content/Context;)V
    .locals 2

    .prologue
    .line 121
    invoke-static {}, Lcom/miui/internal/component/plugin/PluginContext;->getInstance()Lcom/miui/internal/component/plugin/PluginContext;

    move-result-object v0

    invoke-virtual {v0, p0}, Lcom/miui/internal/component/plugin/PluginContext;->setApplicationContext(Landroid/content/Context;)V

    .line 123
    invoke-static {p0}, Lcom/miui/internal/component/ComponentManager;->e(Landroid/content/Context;)Ljava/io/File;

    move-result-object v0

    .line 124
    invoke-virtual {v0}, Ljava/io/File;->exists()Z

    move-result v1

    if-nez v1, :cond_0

    .line 125
    const-string v1, "plugins"

    invoke-static {p0, v1, v0}, Lcom/miui/internal/component/ComponentManager;->a(Landroid/content/Context;Ljava/lang/String;Ljava/io/File;)V

    .line 128
    :cond_0
    new-instance v1, Lcom/miui/internal/component/plugin/PluginLoader;

    invoke-direct {v1, v0}, Lcom/miui/internal/component/plugin/PluginLoader;-><init>(Ljava/io/File;)V

    sput-object v1, Lcom/miui/internal/component/ComponentManager;->tH:Lcom/miui/internal/component/plugin/PluginLoader;

    .line 129
    invoke-static {}, Lcom/miui/internal/component/plugin/PluginContext;->getInstance()Lcom/miui/internal/component/plugin/PluginContext;

    move-result-object v0

    sget-object v1, Lcom/miui/internal/component/ComponentManager;->tH:Lcom/miui/internal/component/plugin/PluginLoader;

    invoke-virtual {v0, v1}, Lcom/miui/internal/component/plugin/PluginContext;->setPluginLoader(Lcom/miui/internal/component/plugin/PluginLoader;)V

    .line 131
    new-instance v0, Lcom/miui/internal/component/plugin/PluginClassLoader;

    sget-object v1, Lcom/miui/internal/component/ComponentManager;->tH:Lcom/miui/internal/component/plugin/PluginLoader;

    invoke-direct {v0, v1}, Lcom/miui/internal/component/plugin/PluginClassLoader;-><init>(Lcom/miui/internal/component/plugin/PluginLoader;)V

    .line 132
    invoke-static {}, Lcom/miui/internal/component/plugin/PluginContext;->getInstance()Lcom/miui/internal/component/plugin/PluginContext;

    move-result-object v1

    invoke-virtual {v1, v0}, Lcom/miui/internal/component/plugin/PluginContext;->setPluginClassLoader(Lcom/miui/internal/component/plugin/PluginClassLoader;)V

    .line 134
    new-instance v0, Lcom/miui/internal/component/plugin/PluginResourceLoader;

    sget-object v1, Lcom/miui/internal/component/ComponentManager;->tH:Lcom/miui/internal/component/plugin/PluginLoader;

    invoke-direct {v0, v1}, Lcom/miui/internal/component/plugin/PluginResourceLoader;-><init>(Lcom/miui/internal/component/plugin/PluginLoader;)V

    .line 135
    invoke-static {}, Lcom/miui/internal/component/plugin/PluginContext;->getInstance()Lcom/miui/internal/component/plugin/PluginContext;

    move-result-object v1

    invoke-virtual {v1, v0}, Lcom/miui/internal/component/plugin/PluginContext;->setPluginResourceLoader(Lcom/miui/internal/component/plugin/PluginResourceLoader;)V

    .line 136
    return-void
.end method

.method public static load(Lcom/miui/internal/core/Manifest;)V
    .locals 1

    .prologue
    .line 66
    sget-object v0, Lcom/miui/internal/util/PackageConstants;->sApplication:Landroid/app/Application;

    invoke-static {v0}, Lcom/miui/internal/component/ComponentManager;->g(Landroid/content/Context;)V

    .line 68
    sget-object v0, Lcom/miui/internal/util/PackageConstants;->sApplication:Landroid/app/Application;

    invoke-static {v0}, Lcom/miui/internal/component/ComponentManager;->h(Landroid/content/Context;)V

    .line 69
    sget-object v0, Lcom/miui/internal/util/PackageConstants;->sApplication:Landroid/app/Application;

    invoke-static {v0}, Lcom/miui/internal/component/ComponentManager;->i(Landroid/content/Context;)V

    .line 70
    return-void
.end method

.method public static loadClass(Ljava/lang/String;Ljava/lang/String;)V
    .locals 0

    .prologue
    .line 139
    invoke-static {p0, p1}, Lcom/miui/internal/component/module/ModuleLoader;->loadClass(Ljava/lang/String;Ljava/lang/String;)V

    .line 140
    return-void
.end method

.method public static loadResource(Ljava/lang/String;)V
    .locals 0

    .prologue
    .line 143
    invoke-static {p0}, Lcom/miui/internal/component/module/ModuleLoader;->loadResource(Ljava/lang/String;)V

    .line 144
    return-void
.end method
