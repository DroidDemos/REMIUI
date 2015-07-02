.class public Lmiui/os/DropBoxManager;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lmiui/os/DropBoxManager$Entry;
    }
.end annotation


# static fields
.field public static final ACTION_DROPBOX_ENTRY_ADDED:Ljava/lang/String; = "miui.intent.action.DROPBOX_ENTRY_ADDED"

.field public static final EXTRA_TAG:Ljava/lang/String; = "tag"

.field public static final EXTRA_TIME:Ljava/lang/String; = "time"

.field public static final IS_EMPTY:I = 0x1

.field public static final IS_GZIPPED:I = 0x4

.field public static final IS_TEXT:I = 0x2

.field private static final TAG:Ljava/lang/String; = "DropBoxManager"

.field private static final e:I = 0x8

.field private static final f:Lmiui/util/SoftReferenceSingleton;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lmiui/util/SoftReferenceSingleton",
            "<",
            "Lmiui/os/DropBoxManager;",
            ">;"
        }
    .end annotation
.end field

.field private static final g:I = 0x64


# instance fields
.field private h:Landroid/content/ServiceConnection;

.field private i:Ljava/util/concurrent/ArrayBlockingQueue;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/concurrent/ArrayBlockingQueue",
            "<",
            "Lmiui/os/DropBoxManager$Entry;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .prologue
    .line 85
    new-instance v0, Lmiui/os/a;

    invoke-direct {v0}, Lmiui/os/a;-><init>()V

    sput-object v0, Lmiui/os/DropBoxManager;->f:Lmiui/util/SoftReferenceSingleton;

    return-void
.end method

.method private constructor <init>()V
    .locals 2

    .prologue
    .line 277
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 278
    new-instance v0, Lmiui/os/DropBoxManager$1;

    invoke-direct {v0, p0}, Lmiui/os/DropBoxManager$1;-><init>(Lmiui/os/DropBoxManager;)V

    iput-object v0, p0, Lmiui/os/DropBoxManager;->h:Landroid/content/ServiceConnection;

    .line 302
    new-instance v0, Ljava/util/concurrent/ArrayBlockingQueue;

    const/16 v1, 0x64

    invoke-direct {v0, v1}, Ljava/util/concurrent/ArrayBlockingQueue;-><init>(I)V

    iput-object v0, p0, Lmiui/os/DropBoxManager;->i:Ljava/util/concurrent/ArrayBlockingQueue;

    .line 303
    return-void
.end method

.method synthetic constructor <init>(Lmiui/os/a;)V
    .locals 0

    .prologue
    .line 46
    invoke-direct {p0}, Lmiui/os/DropBoxManager;-><init>()V

    return-void
.end method

.method static synthetic a(Lmiui/os/DropBoxManager;)Ljava/util/concurrent/ArrayBlockingQueue;
    .locals 1

    .prologue
    .line 46
    iget-object v0, p0, Lmiui/os/DropBoxManager;->i:Ljava/util/concurrent/ArrayBlockingQueue;

    return-object v0
.end method

.method private a(Lmiui/os/DropBoxManager$Entry;)V
    .locals 2

    .prologue
    .line 345
    iget-object v0, p0, Lmiui/os/DropBoxManager;->i:Ljava/util/concurrent/ArrayBlockingQueue;

    invoke-virtual {v0, p1}, Ljava/util/concurrent/ArrayBlockingQueue;->offer(Ljava/lang/Object;)Z

    move-result v0

    .line 346
    if-nez v0, :cond_0

    .line 347
    const-string v0, "DropBoxManager"

    const-string v1, "Fail to addEntry for queue is full"

    invoke-static {v0, v1}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    .line 348
    invoke-virtual {p1}, Lmiui/os/DropBoxManager$Entry;->close()V

    .line 352
    :goto_0
    return-void

    .line 350
    :cond_0
    invoke-direct {p0}, Lmiui/os/DropBoxManager;->b()Z

    goto :goto_0
.end method

.method private b()Z
    .locals 4

    .prologue
    .line 355
    invoke-static {}, Lcom/miui/internal/util/PackageConstants;->getCurrentApplication()Landroid/app/Application;

    move-result-object v0

    .line 356
    new-instance v1, Landroid/content/Intent;

    const-string v2, "com.miui.internal.action.BIND_SERVICE"

    invoke-direct {v1, v2}, Landroid/content/Intent;-><init>(Ljava/lang/String;)V

    .line 357
    const-string v2, "com.miui.sdk"

    invoke-virtual {v1, v2}, Landroid/content/Intent;->setPackage(Ljava/lang/String;)Landroid/content/Intent;

    .line 358
    const-string v2, "extra_service_name"

    const-string v3, "DropBoxManagerService"

    invoke-virtual {v1, v2, v3}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;

    .line 361
    :try_start_0
    iget-object v2, p0, Lmiui/os/DropBoxManager;->h:Landroid/content/ServiceConnection;

    const/4 v3, 0x1

    invoke-virtual {v0, v1, v2, v3}, Landroid/content/Context;->bindService(Landroid/content/Intent;Landroid/content/ServiceConnection;I)Z
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    move-result v0

    .line 365
    :goto_0
    return v0

    .line 363
    :catch_0
    move-exception v0

    .line 364
    const-string v1, "DropBoxManager"

    const-string v2, "Fail to bind service"

    invoke-static {v1, v2, v0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    .line 365
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public static getInstance()Lmiui/os/DropBoxManager;
    .locals 1

    .prologue
    .line 94
    sget-object v0, Lmiui/os/DropBoxManager;->f:Lmiui/util/SoftReferenceSingleton;

    invoke-virtual {v0}, Lmiui/util/SoftReferenceSingleton;->get()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/os/DropBoxManager;

    return-object v0
.end method


# virtual methods
.method public addData(Ljava/lang/String;[BI)V
    .locals 6

    .prologue
    .line 325
    if-nez p2, :cond_0

    new-instance v0, Ljava/lang/NullPointerException;

    const-string v1, "data == null"

    invoke-direct {v0, v1}, Ljava/lang/NullPointerException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 326
    :cond_0
    new-instance v0, Lmiui/os/DropBoxManager$Entry;

    const-wide/16 v2, 0x0

    move-object v1, p1

    move-object v4, p2

    move v5, p3

    invoke-direct/range {v0 .. v5}, Lmiui/os/DropBoxManager$Entry;-><init>(Ljava/lang/String;J[BI)V

    invoke-direct {p0, v0}, Lmiui/os/DropBoxManager;->a(Lmiui/os/DropBoxManager$Entry;)V

    .line 327
    return-void
.end method

.method public addFile(Ljava/lang/String;Ljava/io/File;I)V
    .locals 6
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 339
    if-nez p2, :cond_0

    new-instance v0, Ljava/lang/NullPointerException;

    const-string v1, "file == null"

    invoke-direct {v0, v1}, Ljava/lang/NullPointerException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 340
    :cond_0
    new-instance v0, Lmiui/os/DropBoxManager$Entry;

    const-wide/16 v2, 0x0

    move-object v1, p1

    move-object v4, p2

    move v5, p3

    invoke-direct/range {v0 .. v5}, Lmiui/os/DropBoxManager$Entry;-><init>(Ljava/lang/String;JLjava/io/File;I)V

    invoke-direct {p0, v0}, Lmiui/os/DropBoxManager;->a(Lmiui/os/DropBoxManager$Entry;)V

    .line 342
    return-void
.end method

.method public addText(Ljava/lang/String;Ljava/lang/String;)V
    .locals 3

    .prologue
    .line 314
    new-instance v0, Lmiui/os/DropBoxManager$Entry;

    const-wide/16 v1, 0x0

    invoke-direct {v0, p1, v1, v2, p2}, Lmiui/os/DropBoxManager$Entry;-><init>(Ljava/lang/String;JLjava/lang/String;)V

    invoke-direct {p0, v0}, Lmiui/os/DropBoxManager;->a(Lmiui/os/DropBoxManager$Entry;)V

    .line 315
    return-void
.end method
