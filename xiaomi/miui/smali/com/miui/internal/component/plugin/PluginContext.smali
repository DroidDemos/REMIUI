.class public Lcom/miui/internal/component/plugin/PluginContext;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field private static volatile Hq:Lcom/miui/internal/component/plugin/PluginContext;


# instance fields
.field private Hr:Landroid/content/Context;

.field private Hs:Lcom/miui/internal/component/plugin/PluginClassLoader;

.field private Ht:Lcom/miui/internal/component/plugin/PluginResourceLoader;

.field private dg:Lcom/miui/internal/component/plugin/PluginLoader;


# direct methods
.method private constructor <init>()V
    .locals 0

    .prologue
    .line 24
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 25
    return-void
.end method

.method public static getInstance()Lcom/miui/internal/component/plugin/PluginContext;
    .locals 2

    .prologue
    .line 14
    sget-object v0, Lcom/miui/internal/component/plugin/PluginContext;->Hq:Lcom/miui/internal/component/plugin/PluginContext;

    if-nez v0, :cond_1

    .line 15
    const-class v1, Lcom/miui/internal/component/plugin/PluginContext;

    monitor-enter v1

    .line 16
    :try_start_0
    sget-object v0, Lcom/miui/internal/component/plugin/PluginContext;->Hq:Lcom/miui/internal/component/plugin/PluginContext;

    if-nez v0, :cond_0

    .line 17
    new-instance v0, Lcom/miui/internal/component/plugin/PluginContext;

    invoke-direct {v0}, Lcom/miui/internal/component/plugin/PluginContext;-><init>()V

    sput-object v0, Lcom/miui/internal/component/plugin/PluginContext;->Hq:Lcom/miui/internal/component/plugin/PluginContext;

    .line 19
    :cond_0
    monitor-exit v1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 21
    :cond_1
    sget-object v0, Lcom/miui/internal/component/plugin/PluginContext;->Hq:Lcom/miui/internal/component/plugin/PluginContext;

    return-object v0

    .line 19
    :catchall_0
    move-exception v0

    :try_start_1
    monitor-exit v1
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw v0
.end method


# virtual methods
.method public getApplicationContext()Landroid/content/Context;
    .locals 1

    .prologue
    .line 34
    iget-object v0, p0, Lcom/miui/internal/component/plugin/PluginContext;->Hr:Landroid/content/Context;

    return-object v0
.end method

.method public getPluginClassLoader()Lcom/miui/internal/component/plugin/PluginClassLoader;
    .locals 1

    .prologue
    .line 50
    iget-object v0, p0, Lcom/miui/internal/component/plugin/PluginContext;->Hs:Lcom/miui/internal/component/plugin/PluginClassLoader;

    return-object v0
.end method

.method public getPluginLoader()Lcom/miui/internal/component/plugin/PluginLoader;
    .locals 1

    .prologue
    .line 42
    iget-object v0, p0, Lcom/miui/internal/component/plugin/PluginContext;->dg:Lcom/miui/internal/component/plugin/PluginLoader;

    return-object v0
.end method

.method public getPluginResourceLoader()Lcom/miui/internal/component/plugin/PluginResourceLoader;
    .locals 1

    .prologue
    .line 58
    iget-object v0, p0, Lcom/miui/internal/component/plugin/PluginContext;->Ht:Lcom/miui/internal/component/plugin/PluginResourceLoader;

    return-object v0
.end method

.method public setApplicationContext(Landroid/content/Context;)V
    .locals 0

    .prologue
    .line 38
    iput-object p1, p0, Lcom/miui/internal/component/plugin/PluginContext;->Hr:Landroid/content/Context;

    .line 39
    return-void
.end method

.method public setPluginClassLoader(Lcom/miui/internal/component/plugin/PluginClassLoader;)V
    .locals 0

    .prologue
    .line 54
    iput-object p1, p0, Lcom/miui/internal/component/plugin/PluginContext;->Hs:Lcom/miui/internal/component/plugin/PluginClassLoader;

    .line 55
    return-void
.end method

.method public setPluginLoader(Lcom/miui/internal/component/plugin/PluginLoader;)V
    .locals 0

    .prologue
    .line 46
    iput-object p1, p0, Lcom/miui/internal/component/plugin/PluginContext;->dg:Lcom/miui/internal/component/plugin/PluginLoader;

    .line 47
    return-void
.end method

.method public setPluginResourceLoader(Lcom/miui/internal/component/plugin/PluginResourceLoader;)V
    .locals 0

    .prologue
    .line 62
    iput-object p1, p0, Lcom/miui/internal/component/plugin/PluginContext;->Ht:Lcom/miui/internal/component/plugin/PluginResourceLoader;

    .line 63
    return-void
.end method
