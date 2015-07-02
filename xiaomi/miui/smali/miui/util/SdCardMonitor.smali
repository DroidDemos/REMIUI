.class public Lmiui/util/SdCardMonitor;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lmiui/util/SdCardMonitor$SdCardStatusListener;,
        Lmiui/util/SdCardMonitor$a;
    }
.end annotation


# instance fields
.field private sA:Ljava/lang/Boolean;

.field private sy:Landroid/content/BroadcastReceiver;

.field private sz:Ljava/util/HashSet;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/HashSet",
            "<",
            "Lmiui/util/SdCardMonitor$SdCardStatusListener;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method private constructor <init>()V
    .locals 1

    .prologue
    .line 44
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 36
    new-instance v0, Ljava/util/HashSet;

    invoke-direct {v0}, Ljava/util/HashSet;-><init>()V

    iput-object v0, p0, Lmiui/util/SdCardMonitor;->sz:Ljava/util/HashSet;

    .line 45
    new-instance v0, Lmiui/util/SdCardMonitor$1;

    invoke-direct {v0, p0}, Lmiui/util/SdCardMonitor$1;-><init>(Lmiui/util/SdCardMonitor;)V

    iput-object v0, p0, Lmiui/util/SdCardMonitor;->sy:Landroid/content/BroadcastReceiver;

    .line 55
    return-void
.end method

.method synthetic constructor <init>(Lmiui/util/SdCardMonitor$1;)V
    .locals 0

    .prologue
    .line 21
    invoke-direct {p0}, Lmiui/util/SdCardMonitor;-><init>()V

    return-void
.end method

.method static synthetic a(Lmiui/util/SdCardMonitor;)Ljava/lang/Boolean;
    .locals 1

    .prologue
    .line 21
    iget-object v0, p0, Lmiui/util/SdCardMonitor;->sA:Ljava/lang/Boolean;

    return-object v0
.end method

.method static synthetic a(Lmiui/util/SdCardMonitor;Ljava/lang/Boolean;)Ljava/lang/Boolean;
    .locals 0

    .prologue
    .line 21
    iput-object p1, p0, Lmiui/util/SdCardMonitor;->sA:Ljava/lang/Boolean;

    return-object p1
.end method

.method static synthetic a(Lmiui/util/SdCardMonitor;Z)V
    .locals 0

    .prologue
    .line 21
    invoke-direct {p0, p1}, Lmiui/util/SdCardMonitor;->i(Z)V

    return-void
.end method

.method public static getInstance()Lmiui/util/SdCardMonitor;
    .locals 1

    .prologue
    .line 61
    invoke-static {}, Lmiui/util/SdCardMonitor$a;->aP()Lmiui/util/SdCardMonitor;

    move-result-object v0

    return-object v0
.end method

.method private i(Z)V
    .locals 2

    .prologue
    .line 98
    iget-object v0, p0, Lmiui/util/SdCardMonitor;->sz:Ljava/util/HashSet;

    invoke-virtual {v0}, Ljava/util/HashSet;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :goto_0
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_0

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/util/SdCardMonitor$SdCardStatusListener;

    .line 99
    invoke-interface {v0, p1}, Lmiui/util/SdCardMonitor$SdCardStatusListener;->onStatusChanged(Z)V

    goto :goto_0

    .line 101
    :cond_0
    return-void
.end method

.method public static isSdCardAvailable()Z
    .locals 1

    .prologue
    .line 107
    invoke-static {}, Lmiui/os/Environment;->isExternalStorageMounted()Z

    move-result v0

    return v0
.end method


# virtual methods
.method public addListener(Lmiui/util/SdCardMonitor$SdCardStatusListener;)V
    .locals 3

    .prologue
    .line 72
    iget-object v0, p0, Lmiui/util/SdCardMonitor;->sz:Ljava/util/HashSet;

    invoke-virtual {v0}, Ljava/util/HashSet;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 73
    new-instance v0, Landroid/content/IntentFilter;

    invoke-direct {v0}, Landroid/content/IntentFilter;-><init>()V

    .line 74
    const-string v1, "android.intent.action.MEDIA_SHARED"

    invoke-virtual {v0, v1}, Landroid/content/IntentFilter;->addAction(Ljava/lang/String;)V

    .line 75
    const-string v1, "android.intent.action.MEDIA_MOUNTED"

    invoke-virtual {v0, v1}, Landroid/content/IntentFilter;->addAction(Ljava/lang/String;)V

    .line 76
    const-string v1, "android.intent.action.MEDIA_UNMOUNTED"

    invoke-virtual {v0, v1}, Landroid/content/IntentFilter;->addAction(Ljava/lang/String;)V

    .line 77
    const-string v1, "android.intent.action.MEDIA_BAD_REMOVAL"

    invoke-virtual {v0, v1}, Landroid/content/IntentFilter;->addAction(Ljava/lang/String;)V

    .line 78
    const-string v1, "file"

    invoke-virtual {v0, v1}, Landroid/content/IntentFilter;->addDataScheme(Ljava/lang/String;)V

    .line 79
    invoke-static {}, Lcom/miui/internal/util/PackageConstants;->getCurrentApplication()Landroid/app/Application;

    move-result-object v1

    iget-object v2, p0, Lmiui/util/SdCardMonitor;->sy:Landroid/content/BroadcastReceiver;

    invoke-virtual {v1, v2, v0}, Landroid/app/Application;->registerReceiver(Landroid/content/BroadcastReceiver;Landroid/content/IntentFilter;)Landroid/content/Intent;

    .line 81
    :cond_0
    iget-object v0, p0, Lmiui/util/SdCardMonitor;->sz:Ljava/util/HashSet;

    invoke-virtual {v0, p1}, Ljava/util/HashSet;->add(Ljava/lang/Object;)Z

    .line 82
    return-void
.end method

.method public removeListener(Lmiui/util/SdCardMonitor$SdCardStatusListener;)V
    .locals 2

    .prologue
    .line 90
    iget-object v0, p0, Lmiui/util/SdCardMonitor;->sz:Ljava/util/HashSet;

    invoke-virtual {v0, p1}, Ljava/util/HashSet;->remove(Ljava/lang/Object;)Z

    .line 91
    iget-object v0, p0, Lmiui/util/SdCardMonitor;->sz:Ljava/util/HashSet;

    invoke-virtual {v0}, Ljava/util/HashSet;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 92
    invoke-static {}, Lcom/miui/internal/util/PackageConstants;->getCurrentApplication()Landroid/app/Application;

    move-result-object v0

    iget-object v1, p0, Lmiui/util/SdCardMonitor;->sy:Landroid/content/BroadcastReceiver;

    invoke-virtual {v0, v1}, Landroid/app/Application;->unregisterReceiver(Landroid/content/BroadcastReceiver;)V

    .line 94
    :cond_0
    return-void
.end method
