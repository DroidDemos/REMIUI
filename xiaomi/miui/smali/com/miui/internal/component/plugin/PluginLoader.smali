.class public Lcom/miui/internal/component/plugin/PluginLoader;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field private static final gr:Ljava/lang/String; = "plugins-opt"

.field private static final ts:Ljava/lang/String; = ".apk"

.field private static final tt:Ljava/lang/String; = ".xml"

.field private static final tu:Ljava/lang/String; = "PluginManifest.xml"

.field private static final tv:Ljava/lang/String; = "assets/PluginManifest.xml"

.field private static tw:Ljava/util/Map;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map",
            "<",
            "Ljava/lang/String;",
            "Lcom/miui/internal/component/plugin/Plugin;",
            ">;"
        }
    .end annotation
.end field

.field private static tx:Ljava/util/Map;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map",
            "<",
            "Ljava/lang/String;",
            "Ljava/util/List",
            "<",
            "Lcom/miui/internal/component/plugin/Plugin;",
            ">;>;"
        }
    .end annotation
.end field

.field private static ty:Ljava/util/Map;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map",
            "<",
            "Ljava/lang/String;",
            "Ljava/lang/ClassLoader;",
            ">;"
        }
    .end annotation
.end field

.field private static tz:Ljava/util/Map;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map",
            "<",
            "Ljava/lang/String;",
            "Landroid/content/res/Resources;",
            ">;"
        }
    .end annotation
.end field


# instance fields
.field private gw:Ljava/io/File;

.field private tA:Ljava/io/File;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .prologue
    .line 36
    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    sput-object v0, Lcom/miui/internal/component/plugin/PluginLoader;->tw:Ljava/util/Map;

    .line 37
    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    sput-object v0, Lcom/miui/internal/component/plugin/PluginLoader;->tx:Ljava/util/Map;

    .line 39
    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    sput-object v0, Lcom/miui/internal/component/plugin/PluginLoader;->ty:Ljava/util/Map;

    .line 40
    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    sput-object v0, Lcom/miui/internal/component/plugin/PluginLoader;->tz:Ljava/util/Map;

    return-void
.end method

.method public constructor <init>(Ljava/io/File;)V
    .locals 3

    .prologue
    .line 45
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 46
    iput-object p1, p0, Lcom/miui/internal/component/plugin/PluginLoader;->tA:Ljava/io/File;

    .line 47
    new-instance v0, Ljava/io/File;

    iget-object v1, p0, Lcom/miui/internal/component/plugin/PluginLoader;->tA:Ljava/io/File;

    invoke-virtual {v1}, Ljava/io/File;->getParentFile()Ljava/io/File;

    move-result-object v1

    const-string v2, "plugins-opt"

    invoke-direct {v0, v1, v2}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    iput-object v0, p0, Lcom/miui/internal/component/plugin/PluginLoader;->gw:Ljava/io/File;

    .line 48
    iget-object v0, p0, Lcom/miui/internal/component/plugin/PluginLoader;->tA:Ljava/io/File;

    invoke-virtual {v0}, Ljava/io/File;->mkdirs()Z

    .line 49
    iget-object v0, p0, Lcom/miui/internal/component/plugin/PluginLoader;->gw:Ljava/io/File;

    invoke-virtual {v0}, Ljava/io/File;->mkdirs()Z

    .line 50
    invoke-direct {p0}, Lcom/miui/internal/component/plugin/PluginLoader;->bP()V

    .line 51
    invoke-direct {p0}, Lcom/miui/internal/component/plugin/PluginLoader;->bR()V

    .line 52
    return-void
.end method

.method private a(Ljava/io/File;Ljava/io/File;)Lcom/miui/internal/component/plugin/Plugin;
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;,
            Lcom/miui/internal/component/plugin/PluginParseException;
        }
    .end annotation

    .prologue
    .line 105
    new-instance v0, Ljava/io/FileInputStream;

    invoke-direct {v0, p1}, Ljava/io/FileInputStream;-><init>(Ljava/io/File;)V

    invoke-direct {p0, v0, p2}, Lcom/miui/internal/component/plugin/PluginLoader;->a(Ljava/io/InputStream;Ljava/io/File;)Lcom/miui/internal/component/plugin/Plugin;

    move-result-object v0

    return-object v0
.end method

.method private a(Ljava/io/InputStream;Ljava/io/File;)Lcom/miui/internal/component/plugin/Plugin;
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;,
            Lcom/miui/internal/component/plugin/PluginParseException;
        }
    .end annotation

    .prologue
    .line 109
    new-instance v0, Lcom/miui/internal/component/plugin/PluginManifestParser;

    invoke-direct {v0}, Lcom/miui/internal/component/plugin/PluginManifestParser;-><init>()V

    .line 110
    invoke-virtual {v0, p1}, Lcom/miui/internal/component/plugin/PluginManifestParser;->parsePlugin(Ljava/io/InputStream;)Lcom/miui/internal/component/plugin/Plugin;

    move-result-object v0

    .line 111
    invoke-virtual {v0, p2}, Lcom/miui/internal/component/plugin/Plugin;->setFile(Ljava/io/File;)V

    .line 112
    return-object v0
.end method

.method private a(Lcom/miui/internal/component/plugin/Plugin;)Ljava/lang/ClassLoader;
    .locals 5

    .prologue
    .line 149
    invoke-virtual {p1}, Lcom/miui/internal/component/plugin/Plugin;->getFile()Ljava/io/File;

    move-result-object v0

    invoke-virtual {v0}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object v0

    .line 150
    iget-object v1, p0, Lcom/miui/internal/component/plugin/PluginLoader;->gw:Ljava/io/File;

    invoke-virtual {v1}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object v1

    .line 151
    new-instance v2, Ldalvik/system/DexClassLoader;

    const/4 v3, 0x0

    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/Class;->getClassLoader()Ljava/lang/ClassLoader;

    move-result-object v4

    invoke-direct {v2, v0, v1, v3, v4}, Ldalvik/system/DexClassLoader;-><init>(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/ClassLoader;)V

    return-object v2
.end method

.method private a(Landroid/content/res/AssetManager;Ljava/lang/String;)V
    .locals 5

    .prologue
    .line 189
    :try_start_0
    const-class v0, Landroid/content/res/AssetManager;

    const-string v1, "addAssetPath"

    const/4 v2, 0x1

    new-array v2, v2, [Ljava/lang/Class;

    const/4 v3, 0x0

    const-class v4, Ljava/lang/String;

    aput-object v4, v2, v3

    invoke-virtual {v0, v1, v2}, Ljava/lang/Class;->getMethod(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;

    move-result-object v0

    const/4 v1, 0x1

    new-array v1, v1, [Ljava/lang/Object;

    const/4 v2, 0x0

    aput-object p2, v1, v2

    invoke-virtual {v0, p1, v1}, Ljava/lang/reflect/Method;->invoke(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    :try_end_0
    .catch Ljava/lang/IllegalAccessException; {:try_start_0 .. :try_end_0} :catch_0
    .catch Ljava/lang/IllegalArgumentException; {:try_start_0 .. :try_end_0} :catch_1
    .catch Ljava/lang/reflect/InvocationTargetException; {:try_start_0 .. :try_end_0} :catch_2
    .catch Ljava/lang/NoSuchMethodException; {:try_start_0 .. :try_end_0} :catch_3

    .line 203
    return-void

    .line 190
    :catch_0
    move-exception v0

    .line 191
    invoke-virtual {v0}, Ljava/lang/IllegalAccessException;->printStackTrace()V

    .line 192
    new-instance v1, Lcom/miui/internal/component/plugin/PluginException;

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "invoke asset manager encounter an error: "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v0}, Ljava/lang/IllegalAccessException;->getMessage()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-direct {v1, v0}, Lcom/miui/internal/component/plugin/PluginException;-><init>(Ljava/lang/String;)V

    throw v1

    .line 193
    :catch_1
    move-exception v0

    .line 194
    invoke-virtual {v0}, Ljava/lang/IllegalArgumentException;->printStackTrace()V

    .line 195
    new-instance v1, Lcom/miui/internal/component/plugin/PluginException;

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "invoke asset manager encounter an error: "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v0}, Ljava/lang/IllegalArgumentException;->getMessage()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-direct {v1, v0}, Lcom/miui/internal/component/plugin/PluginException;-><init>(Ljava/lang/String;)V

    throw v1

    .line 196
    :catch_2
    move-exception v0

    .line 197
    invoke-virtual {v0}, Ljava/lang/reflect/InvocationTargetException;->printStackTrace()V

    .line 198
    new-instance v1, Lcom/miui/internal/component/plugin/PluginException;

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "invoke asset manager encounter an error: "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v0}, Ljava/lang/reflect/InvocationTargetException;->getMessage()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-direct {v1, v0}, Lcom/miui/internal/component/plugin/PluginException;-><init>(Ljava/lang/String;)V

    throw v1

    .line 199
    :catch_3
    move-exception v0

    .line 200
    invoke-virtual {v0}, Ljava/lang/NoSuchMethodException;->printStackTrace()V

    .line 201
    new-instance v1, Lcom/miui/internal/component/plugin/PluginException;

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "invoke asset manager encounter an error: "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v0}, Ljava/lang/NoSuchMethodException;->getMessage()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-direct {v1, v0}, Lcom/miui/internal/component/plugin/PluginException;-><init>(Ljava/lang/String;)V

    throw v1
.end method

.method private b(Lcom/miui/internal/component/plugin/Plugin;)Landroid/content/res/Resources;
    .locals 4

    .prologue
    .line 166
    .line 168
    :try_start_0
    const-class v0, Landroid/content/res/AssetManager;

    invoke-virtual {v0}, Ljava/lang/Class;->newInstance()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/content/res/AssetManager;

    .line 169
    invoke-virtual {p1}, Lcom/miui/internal/component/plugin/Plugin;->getFile()Ljava/io/File;

    move-result-object v1

    invoke-virtual {v1}, Ljava/io/File;->getPath()Ljava/lang/String;

    move-result-object v1

    invoke-direct {p0, v0, v1}, Lcom/miui/internal/component/plugin/PluginLoader;->a(Landroid/content/res/AssetManager;Ljava/lang/String;)V

    .line 170
    invoke-virtual {p1}, Lcom/miui/internal/component/plugin/Plugin;->getDependencies()Ljava/util/Map;

    move-result-object v1

    invoke-interface {v1}, Ljava/util/Map;->values()Ljava/util/Collection;

    move-result-object v1

    invoke-interface {v1}, Ljava/util/Collection;->iterator()Ljava/util/Iterator;

    move-result-object v2

    :cond_0
    :goto_0
    invoke-interface {v2}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_1

    invoke-interface {v2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/miui/internal/component/plugin/Dependency;

    .line 171
    invoke-virtual {v1}, Lcom/miui/internal/component/plugin/Dependency;->isResourcesRequired()Z

    move-result v3

    if-eqz v3, :cond_0

    .line 172
    sget-object v3, Lcom/miui/internal/component/plugin/PluginLoader;->tw:Ljava/util/Map;

    invoke-virtual {v1}, Lcom/miui/internal/component/plugin/Dependency;->getName()Ljava/lang/String;

    move-result-object v1

    invoke-interface {v3, v1}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/miui/internal/component/plugin/Plugin;

    invoke-virtual {v1}, Lcom/miui/internal/component/plugin/Plugin;->getFile()Ljava/io/File;

    move-result-object v1

    invoke-virtual {v1}, Ljava/io/File;->getPath()Ljava/lang/String;

    move-result-object v1

    invoke-direct {p0, v0, v1}, Lcom/miui/internal/component/plugin/PluginLoader;->a(Landroid/content/res/AssetManager;Ljava/lang/String;)V
    :try_end_0
    .catch Ljava/lang/InstantiationException; {:try_start_0 .. :try_end_0} :catch_0
    .catch Ljava/lang/IllegalAccessException; {:try_start_0 .. :try_end_0} :catch_1

    goto :goto_0

    .line 177
    :catch_0
    move-exception v0

    .line 178
    invoke-virtual {v0}, Ljava/lang/InstantiationException;->printStackTrace()V

    .line 179
    new-instance v1, Lcom/miui/internal/component/plugin/PluginException;

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "invoke asset manager encounter an error: "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v0}, Ljava/lang/InstantiationException;->getMessage()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-direct {v1, v0}, Lcom/miui/internal/component/plugin/PluginException;-><init>(Ljava/lang/String;)V

    throw v1

    .line 175
    :cond_1
    :try_start_1
    invoke-static {}, Lcom/miui/internal/component/plugin/PluginContext;->getInstance()Lcom/miui/internal/component/plugin/PluginContext;

    move-result-object v1

    invoke-virtual {v1}, Lcom/miui/internal/component/plugin/PluginContext;->getApplicationContext()Landroid/content/Context;

    move-result-object v1

    invoke-virtual {v1}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    .line 176
    new-instance v2, Landroid/content/res/Resources;

    invoke-virtual {v1}, Landroid/content/res/Resources;->getDisplayMetrics()Landroid/util/DisplayMetrics;

    move-result-object v3

    invoke-virtual {v1}, Landroid/content/res/Resources;->getConfiguration()Landroid/content/res/Configuration;

    move-result-object v1

    invoke-direct {v2, v0, v3, v1}, Landroid/content/res/Resources;-><init>(Landroid/content/res/AssetManager;Landroid/util/DisplayMetrics;Landroid/content/res/Configuration;)V
    :try_end_1
    .catch Ljava/lang/InstantiationException; {:try_start_1 .. :try_end_1} :catch_0
    .catch Ljava/lang/IllegalAccessException; {:try_start_1 .. :try_end_1} :catch_1

    .line 184
    return-object v2

    .line 180
    :catch_1
    move-exception v0

    .line 181
    invoke-virtual {v0}, Ljava/lang/IllegalAccessException;->printStackTrace()V

    .line 182
    new-instance v1, Lcom/miui/internal/component/plugin/PluginException;

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "invoke asset manager encounter an error: "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v0}, Ljava/lang/IllegalAccessException;->getMessage()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-direct {v1, v0}, Lcom/miui/internal/component/plugin/PluginException;-><init>(Ljava/lang/String;)V

    throw v1
.end method

.method private bP()V
    .locals 8

    .prologue
    .line 55
    invoke-direct {p0}, Lcom/miui/internal/component/plugin/PluginLoader;->bQ()V

    .line 56
    iget-object v0, p0, Lcom/miui/internal/component/plugin/PluginLoader;->tA:Ljava/io/File;

    invoke-virtual {v0}, Ljava/io/File;->listFiles()[Ljava/io/File;

    move-result-object v2

    .line 57
    if-nez v2, :cond_1

    .line 77
    :cond_0
    return-void

    .line 60
    :cond_1
    array-length v3, v2

    const/4 v0, 0x0

    move v1, v0

    :goto_0
    if-ge v1, v3, :cond_0

    aget-object v0, v2, v1

    .line 61
    invoke-virtual {v0}, Ljava/io/File;->getName()Ljava/lang/String;

    move-result-object v4

    const-string v5, ".apk"

    invoke-virtual {v4, v5}, Ljava/lang/String;->endsWith(Ljava/lang/String;)Z

    move-result v4

    if-eqz v4, :cond_3

    .line 62
    new-instance v4, Ljava/io/File;

    iget-object v5, p0, Lcom/miui/internal/component/plugin/PluginLoader;->tA:Ljava/io/File;

    new-instance v6, Ljava/lang/StringBuilder;

    invoke-direct {v6}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v0}, Ljava/io/File;->getPath()Ljava/lang/String;

    move-result-object v7

    invoke-static {v7}, Lmiui/os/FileUtils;->getName(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v7

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v6

    const-string v7, ".xml"

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v6

    invoke-virtual {v6}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v6

    invoke-direct {v4, v5, v6}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    .line 64
    :try_start_0
    invoke-virtual {v4}, Ljava/io/File;->exists()Z

    move-result v5

    if-nez v5, :cond_2

    .line 65
    new-instance v5, Ljava/util/zip/ZipFile;

    invoke-direct {v5, v0}, Ljava/util/zip/ZipFile;-><init>(Ljava/io/File;)V

    .line 66
    new-instance v6, Ljava/util/zip/ZipEntry;

    const-string v7, "assets/PluginManifest.xml"

    invoke-direct {v6, v7}, Ljava/util/zip/ZipEntry;-><init>(Ljava/lang/String;)V

    invoke-virtual {v5, v6}, Ljava/util/zip/ZipFile;->getInputStream(Ljava/util/zip/ZipEntry;)Ljava/io/InputStream;

    move-result-object v5

    invoke-static {v5, v4}, Lmiui/os/FileUtils;->copyToFile(Ljava/io/InputStream;Ljava/io/File;)Z

    .line 68
    :cond_2
    invoke-direct {p0, v4, v0}, Lcom/miui/internal/component/plugin/PluginLoader;->a(Ljava/io/File;Ljava/io/File;)Lcom/miui/internal/component/plugin/Plugin;

    move-result-object v0

    .line 69
    sget-object v4, Lcom/miui/internal/component/plugin/PluginLoader;->tw:Ljava/util/Map;

    invoke-virtual {v0}, Lcom/miui/internal/component/plugin/Plugin;->getName()Ljava/lang/String;

    move-result-object v5

    invoke-interface {v4, v5, v0}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_0
    .catch Lcom/miui/internal/component/plugin/PluginParseException; {:try_start_0 .. :try_end_0} :catch_1

    .line 60
    :cond_3
    :goto_1
    add-int/lit8 v0, v1, 0x1

    move v1, v0

    goto :goto_0

    .line 70
    :catch_0
    move-exception v0

    .line 71
    invoke-virtual {v0}, Ljava/io/IOException;->printStackTrace()V

    goto :goto_1

    .line 72
    :catch_1
    move-exception v0

    .line 73
    invoke-virtual {v0}, Lcom/miui/internal/component/plugin/PluginParseException;->printStackTrace()V

    goto :goto_1
.end method

.method private bQ()V
    .locals 5

    .prologue
    .line 80
    const/4 v0, 0x0

    .line 81
    invoke-static {}, Lcom/miui/internal/component/plugin/PluginContext;->getInstance()Lcom/miui/internal/component/plugin/PluginContext;

    move-result-object v1

    invoke-virtual {v1}, Lcom/miui/internal/component/plugin/PluginContext;->getApplicationContext()Landroid/content/Context;

    move-result-object v2

    .line 83
    :try_start_0
    invoke-virtual {v2}, Landroid/content/Context;->getPackageResourcePath()Ljava/lang/String;

    move-result-object v1

    .line 84
    if-eqz v1, :cond_0

    .line 85
    new-instance v3, Ljava/io/File;

    invoke-direct {v3, v1}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    .line 86
    invoke-virtual {v2}, Landroid/content/Context;->getAssets()Landroid/content/res/AssetManager;

    move-result-object v1

    const-string v4, "PluginManifest.xml"

    invoke-virtual {v1, v4}, Landroid/content/res/AssetManager;->open(Ljava/lang/String;)Ljava/io/InputStream;

    move-result-object v1

    invoke-direct {p0, v1, v3}, Lcom/miui/internal/component/plugin/PluginLoader;->a(Ljava/io/InputStream;Ljava/io/File;)Lcom/miui/internal/component/plugin/Plugin;
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_1
    .catch Lcom/miui/internal/component/plugin/PluginParseException; {:try_start_0 .. :try_end_0} :catch_0

    move-result-object v0

    .line 93
    :cond_0
    :goto_0
    if-nez v0, :cond_1

    .line 94
    new-instance v0, Lcom/miui/internal/component/plugin/Plugin;

    invoke-direct {v0}, Lcom/miui/internal/component/plugin/Plugin;-><init>()V

    .line 95
    invoke-virtual {v2}, Landroid/content/Context;->getPackageName()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Lcom/miui/internal/component/plugin/Plugin;->setName(Ljava/lang/String;)V

    .line 96
    invoke-virtual {v2}, Landroid/content/Context;->getPackageName()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Lcom/miui/internal/component/plugin/Plugin;->setGroup(Ljava/lang/String;)V

    .line 97
    sget-object v1, Lcom/miui/internal/component/plugin/AccessPermission;->PRIVATE:Lcom/miui/internal/component/plugin/AccessPermission;

    invoke-virtual {v0, v1}, Lcom/miui/internal/component/plugin/Plugin;->setResources(Lcom/miui/internal/component/plugin/AccessPermission;)V

    .line 99
    :cond_1
    sget-object v1, Lcom/miui/internal/component/plugin/PluginLoader;->tw:Ljava/util/Map;

    invoke-virtual {v0}, Lcom/miui/internal/component/plugin/Plugin;->getName()Ljava/lang/String;

    move-result-object v3

    invoke-interface {v1, v3, v0}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 100
    sget-object v1, Lcom/miui/internal/component/plugin/PluginLoader;->ty:Ljava/util/Map;

    invoke-virtual {v0}, Lcom/miui/internal/component/plugin/Plugin;->getName()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/Class;->getClassLoader()Ljava/lang/ClassLoader;

    move-result-object v4

    invoke-interface {v1, v3, v4}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 101
    sget-object v1, Lcom/miui/internal/component/plugin/PluginLoader;->tz:Ljava/util/Map;

    invoke-virtual {v0}, Lcom/miui/internal/component/plugin/Plugin;->getName()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v2}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v2

    invoke-interface {v1, v0, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 102
    return-void

    .line 90
    :catch_0
    move-exception v1

    .line 91
    invoke-virtual {v1}, Lcom/miui/internal/component/plugin/PluginParseException;->printStackTrace()V

    goto :goto_0

    .line 88
    :catch_1
    move-exception v1

    goto :goto_0
.end method

.method private bR()V
    .locals 6

    .prologue
    .line 116
    sget-object v0, Lcom/miui/internal/component/plugin/PluginLoader;->tw:Ljava/util/Map;

    invoke-interface {v0}, Ljava/util/Map;->keySet()Ljava/util/Set;

    move-result-object v0

    invoke-interface {v0}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v3

    :cond_0
    invoke-interface {v3}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_2

    invoke-interface {v3}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/String;

    .line 117
    sget-object v1, Lcom/miui/internal/component/plugin/PluginLoader;->tw:Ljava/util/Map;

    invoke-interface {v1, v0}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/component/plugin/Plugin;

    .line 118
    invoke-virtual {v0}, Lcom/miui/internal/component/plugin/Plugin;->getExtensions()Ljava/util/Map;

    move-result-object v1

    invoke-interface {v1}, Ljava/util/Map;->keySet()Ljava/util/Set;

    move-result-object v1

    invoke-interface {v1}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v4

    :goto_0
    invoke-interface {v4}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_0

    invoke-interface {v4}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/lang/String;

    .line 119
    sget-object v2, Lcom/miui/internal/component/plugin/PluginLoader;->tx:Ljava/util/Map;

    invoke-interface {v2, v1}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/util/List;

    .line 120
    if-nez v2, :cond_1

    .line 121
    new-instance v2, Ljava/util/ArrayList;

    invoke-direct {v2}, Ljava/util/ArrayList;-><init>()V

    .line 122
    sget-object v5, Lcom/miui/internal/component/plugin/PluginLoader;->tx:Ljava/util/Map;

    invoke-interface {v5, v1, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 124
    :cond_1
    invoke-interface {v2, v0}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto :goto_0

    .line 127
    :cond_2
    return-void
.end method


# virtual methods
.method public getClassLoader(Lcom/miui/internal/component/plugin/Plugin;)Ljava/lang/ClassLoader;
    .locals 4

    .prologue
    .line 138
    sget-object v0, Lcom/miui/internal/component/plugin/PluginLoader;->ty:Ljava/util/Map;

    invoke-virtual {p1}, Lcom/miui/internal/component/plugin/Plugin;->getName()Ljava/lang/String;

    move-result-object v1

    invoke-interface {v0, v1}, Ljava/util/Map;->containsKey(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_1

    .line 139
    sget-object v1, Lcom/miui/internal/component/plugin/PluginLoader;->ty:Ljava/util/Map;

    monitor-enter v1

    .line 140
    :try_start_0
    sget-object v0, Lcom/miui/internal/component/plugin/PluginLoader;->ty:Ljava/util/Map;

    invoke-virtual {p1}, Lcom/miui/internal/component/plugin/Plugin;->getName()Ljava/lang/String;

    move-result-object v2

    invoke-interface {v0, v2}, Ljava/util/Map;->containsKey(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_0

    .line 141
    sget-object v0, Lcom/miui/internal/component/plugin/PluginLoader;->ty:Ljava/util/Map;

    invoke-virtual {p1}, Lcom/miui/internal/component/plugin/Plugin;->getName()Ljava/lang/String;

    move-result-object v2

    invoke-direct {p0, p1}, Lcom/miui/internal/component/plugin/PluginLoader;->a(Lcom/miui/internal/component/plugin/Plugin;)Ljava/lang/ClassLoader;

    move-result-object v3

    invoke-interface {v0, v2, v3}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 143
    :cond_0
    monitor-exit v1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 145
    :cond_1
    sget-object v0, Lcom/miui/internal/component/plugin/PluginLoader;->ty:Ljava/util/Map;

    invoke-virtual {p1}, Lcom/miui/internal/component/plugin/Plugin;->getName()Ljava/lang/String;

    move-result-object v1

    invoke-interface {v0, v1}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/ClassLoader;

    return-object v0

    .line 143
    :catchall_0
    move-exception v0

    :try_start_1
    monitor-exit v1
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw v0
.end method

.method public getPlugins(Ljava/lang/String;)Ljava/util/List;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            ")",
            "Ljava/util/List",
            "<",
            "Lcom/miui/internal/component/plugin/Plugin;",
            ">;"
        }
    .end annotation

    .prologue
    .line 134
    sget-object v0, Lcom/miui/internal/component/plugin/PluginLoader;->tx:Ljava/util/Map;

    invoke-interface {v0, p1}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/util/List;

    return-object v0
.end method

.method public getResources(Lcom/miui/internal/component/plugin/Plugin;)Landroid/content/res/Resources;
    .locals 4

    .prologue
    .line 155
    sget-object v0, Lcom/miui/internal/component/plugin/PluginLoader;->tz:Ljava/util/Map;

    invoke-virtual {p1}, Lcom/miui/internal/component/plugin/Plugin;->getName()Ljava/lang/String;

    move-result-object v1

    invoke-interface {v0, v1}, Ljava/util/Map;->containsKey(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_1

    .line 156
    sget-object v1, Lcom/miui/internal/component/plugin/PluginLoader;->tz:Ljava/util/Map;

    monitor-enter v1

    .line 157
    :try_start_0
    sget-object v0, Lcom/miui/internal/component/plugin/PluginLoader;->tz:Ljava/util/Map;

    invoke-virtual {p1}, Lcom/miui/internal/component/plugin/Plugin;->getName()Ljava/lang/String;

    move-result-object v2

    invoke-interface {v0, v2}, Ljava/util/Map;->containsKey(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_0

    .line 158
    sget-object v0, Lcom/miui/internal/component/plugin/PluginLoader;->tz:Ljava/util/Map;

    invoke-virtual {p1}, Lcom/miui/internal/component/plugin/Plugin;->getName()Ljava/lang/String;

    move-result-object v2

    invoke-direct {p0, p1}, Lcom/miui/internal/component/plugin/PluginLoader;->b(Lcom/miui/internal/component/plugin/Plugin;)Landroid/content/res/Resources;

    move-result-object v3

    invoke-interface {v0, v2, v3}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 160
    :cond_0
    monitor-exit v1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 162
    :cond_1
    sget-object v0, Lcom/miui/internal/component/plugin/PluginLoader;->tz:Ljava/util/Map;

    invoke-virtual {p1}, Lcom/miui/internal/component/plugin/Plugin;->getName()Ljava/lang/String;

    move-result-object v1

    invoke-interface {v0, v1}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/content/res/Resources;

    return-object v0

    .line 160
    :catchall_0
    move-exception v0

    :try_start_1
    monitor-exit v1
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw v0
.end method

.method public hasExtension(Ljava/lang/String;)Z
    .locals 1

    .prologue
    .line 130
    sget-object v0, Lcom/miui/internal/component/plugin/PluginLoader;->tx:Ljava/util/Map;

    invoke-interface {v0, p1}, Ljava/util/Map;->containsKey(Ljava/lang/Object;)Z

    move-result v0

    return v0
.end method
