.class public final Lcom/miui/internal/server/DropBoxManagerService;
.super Lcom/miui/internal/server/IDropBoxManagerService$Stub;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/internal/server/DropBoxManagerService$a;,
        Lcom/miui/internal/server/DropBoxManagerService$b;
    }
.end annotation


# static fields
.field private static final Jp:I = 0x3f480

.field private static final Jq:I = 0x3e8

.field private static final Jr:I = 0x1400

.field private static final Js:I = 0xa

.field private static final Jt:I = 0xa

.field private static final Ju:I = 0x1388

.field private static final Jv:I = 0x1

.field private static final Jw:Z = false

.field private static final Jx:Ljava/lang/String; = "dropbox:"

.field public static final SERVICE_NAME:Ljava/lang/String; = "DropBoxManagerService"

.field private static final TAG:Ljava/lang/String; = "DropBoxManagerService"

.field private static final f:Lmiui/util/SoftReferenceSingleton;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lmiui/util/SoftReferenceSingleton",
            "<",
            "Lcom/miui/internal/server/DropBoxManagerService;",
            ">;"
        }
    .end annotation
.end field


# instance fields
.field private JA:Ljava/util/HashMap;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/HashMap",
            "<",
            "Ljava/lang/String;",
            "Lcom/miui/internal/server/DropBoxManagerService$b;",
            ">;"
        }
    .end annotation
.end field

.field private JB:Landroid/os/StatFs;

.field private JC:I

.field private JD:I

.field private JF:J

.field private Jy:Ljava/io/File;

.field private Jz:Lcom/miui/internal/server/DropBoxManagerService$b;

.field private mContext:Landroid/content/Context;

.field private mHandler:Landroid/os/Handler;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .prologue
    .line 49
    new-instance v0, Lcom/miui/internal/server/a;

    invoke-direct {v0}, Lcom/miui/internal/server/a;-><init>()V

    sput-object v0, Lcom/miui/internal/server/DropBoxManagerService;->f:Lmiui/util/SoftReferenceSingleton;

    return-void
.end method

.method private constructor <init>()V
    .locals 3

    .prologue
    const/4 v1, 0x0

    const/4 v0, 0x0

    .line 108
    invoke-direct {p0}, Lcom/miui/internal/server/IDropBoxManagerService$Stub;-><init>()V

    .line 95
    iput-object v0, p0, Lcom/miui/internal/server/DropBoxManagerService;->Jz:Lcom/miui/internal/server/DropBoxManagerService$b;

    .line 96
    iput-object v0, p0, Lcom/miui/internal/server/DropBoxManagerService;->JA:Ljava/util/HashMap;

    .line 100
    iput-object v0, p0, Lcom/miui/internal/server/DropBoxManagerService;->JB:Landroid/os/StatFs;

    .line 101
    iput v1, p0, Lcom/miui/internal/server/DropBoxManagerService;->JC:I

    .line 102
    iput v1, p0, Lcom/miui/internal/server/DropBoxManagerService;->JD:I

    .line 103
    const-wide/16 v0, 0x0

    iput-wide v0, p0, Lcom/miui/internal/server/DropBoxManagerService;->JF:J

    .line 109
    invoke-static {}, Lcom/miui/internal/util/PackageConstants;->getCurrentApplication()Landroid/app/Application;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/internal/server/DropBoxManagerService;->mContext:Landroid/content/Context;

    .line 110
    new-instance v0, Ljava/io/File;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    iget-object v2, p0, Lcom/miui/internal/server/DropBoxManagerService;->mContext:Landroid/content/Context;

    invoke-virtual {v2}, Landroid/content/Context;->getFilesDir()Ljava/io/File;

    move-result-object v2

    invoke-virtual {v2}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    sget-object v2, Ljava/io/File;->separator:Ljava/lang/String;

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, "dropbox"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    iput-object v0, p0, Lcom/miui/internal/server/DropBoxManagerService;->Jy:Ljava/io/File;

    .line 112
    new-instance v0, Lcom/miui/internal/server/DropBoxManagerService$2;

    invoke-direct {v0, p0}, Lcom/miui/internal/server/DropBoxManagerService$2;-><init>(Lcom/miui/internal/server/DropBoxManagerService;)V

    iput-object v0, p0, Lcom/miui/internal/server/DropBoxManagerService;->mHandler:Landroid/os/Handler;

    .line 122
    invoke-direct {p0}, Lcom/miui/internal/server/DropBoxManagerService;->dT()V

    .line 126
    return-void
.end method

.method synthetic constructor <init>(Lcom/miui/internal/server/a;)V
    .locals 0

    .prologue
    .line 47
    invoke-direct {p0}, Lcom/miui/internal/server/DropBoxManagerService;-><init>()V

    return-void
.end method

.method private declared-synchronized a(Ljava/io/File;Ljava/lang/String;I)J
    .locals 13
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 619
    monitor-enter p0

    :try_start_0
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v4

    .line 625
    iget-object v0, p0, Lcom/miui/internal/server/DropBoxManagerService;->Jz:Lcom/miui/internal/server/DropBoxManagerService$b;

    iget-object v0, v0, Lcom/miui/internal/server/DropBoxManagerService$b;->FX:Ljava/util/TreeSet;

    new-instance v1, Lcom/miui/internal/server/DropBoxManagerService$a;

    const-wide/16 v2, 0x2710

    add-long/2addr v2, v4

    invoke-direct {v1, v2, v3}, Lcom/miui/internal/server/DropBoxManagerService$a;-><init>(J)V

    invoke-virtual {v0, v1}, Ljava/util/TreeSet;->tailSet(Ljava/lang/Object;)Ljava/util/SortedSet;

    move-result-object v1

    .line 626
    const/4 v0, 0x0

    .line 627
    invoke-interface {v1}, Ljava/util/SortedSet;->isEmpty()Z

    move-result v2

    if-nez v2, :cond_5

    .line 628
    invoke-interface {v1}, Ljava/util/SortedSet;->size()I

    move-result v0

    new-array v0, v0, [Lcom/miui/internal/server/DropBoxManagerService$a;

    invoke-interface {v1, v0}, Ljava/util/SortedSet;->toArray([Ljava/lang/Object;)[Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Lcom/miui/internal/server/DropBoxManagerService$a;

    .line 629
    invoke-interface {v1}, Ljava/util/SortedSet;->clear()V

    move-object v11, v0

    .line 632
    :goto_0
    iget-object v0, p0, Lcom/miui/internal/server/DropBoxManagerService;->Jz:Lcom/miui/internal/server/DropBoxManagerService$b;

    iget-object v0, v0, Lcom/miui/internal/server/DropBoxManagerService$b;->FX:Ljava/util/TreeSet;

    invoke-virtual {v0}, Ljava/util/TreeSet;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_0

    .line 633
    iget-object v0, p0, Lcom/miui/internal/server/DropBoxManagerService;->Jz:Lcom/miui/internal/server/DropBoxManagerService$b;

    iget-object v0, v0, Lcom/miui/internal/server/DropBoxManagerService$b;->FX:Ljava/util/TreeSet;

    invoke-virtual {v0}, Ljava/util/TreeSet;->last()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/server/DropBoxManagerService$a;

    iget-wide v0, v0, Lcom/miui/internal/server/DropBoxManagerService$a;->Bj:J

    const-wide/16 v2, 0x1

    add-long/2addr v0, v2

    invoke-static {v4, v5, v0, v1}, Ljava/lang/Math;->max(JJ)J

    move-result-wide v4

    .line 636
    :cond_0
    if-eqz v11, :cond_3

    .line 637
    array-length v12, v11

    const/4 v0, 0x0

    move v10, v0

    :goto_1
    if-ge v10, v12, :cond_3

    aget-object v6, v11, v10

    .line 638
    iget-object v0, p0, Lcom/miui/internal/server/DropBoxManagerService;->Jz:Lcom/miui/internal/server/DropBoxManagerService$b;

    iget v1, v0, Lcom/miui/internal/server/DropBoxManagerService$b;->Bk:I

    iget v2, v6, Lcom/miui/internal/server/DropBoxManagerService$a;->Bk:I

    sub-int/2addr v1, v2

    iput v1, v0, Lcom/miui/internal/server/DropBoxManagerService$b;->Bk:I

    .line 639
    iget-object v0, p0, Lcom/miui/internal/server/DropBoxManagerService;->JA:Ljava/util/HashMap;

    iget-object v1, v6, Lcom/miui/internal/server/DropBoxManagerService$a;->tag:Ljava/lang/String;

    invoke-virtual {v0, v1}, Ljava/util/HashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/server/DropBoxManagerService$b;

    .line 640
    if-eqz v0, :cond_1

    iget-object v1, v0, Lcom/miui/internal/server/DropBoxManagerService$b;->FX:Ljava/util/TreeSet;

    invoke-virtual {v1, v6}, Ljava/util/TreeSet;->remove(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_1

    .line 641
    iget v1, v0, Lcom/miui/internal/server/DropBoxManagerService$b;->Bk:I

    iget v2, v6, Lcom/miui/internal/server/DropBoxManagerService$a;->Bk:I

    sub-int/2addr v1, v2

    iput v1, v0, Lcom/miui/internal/server/DropBoxManagerService$b;->Bk:I

    .line 643
    :cond_1
    iget v0, v6, Lcom/miui/internal/server/DropBoxManagerService$a;->flags:I

    and-int/lit8 v0, v0, 0x1

    if-nez v0, :cond_2

    .line 644
    new-instance v0, Lcom/miui/internal/server/DropBoxManagerService$a;

    iget-object v1, v6, Lcom/miui/internal/server/DropBoxManagerService$a;->file:Ljava/io/File;

    iget-object v2, p0, Lcom/miui/internal/server/DropBoxManagerService;->Jy:Ljava/io/File;

    iget-object v3, v6, Lcom/miui/internal/server/DropBoxManagerService$a;->tag:Ljava/lang/String;

    const-wide/16 v7, 0x1

    add-long v8, v4, v7

    iget v6, v6, Lcom/miui/internal/server/DropBoxManagerService$a;->flags:I

    iget v7, p0, Lcom/miui/internal/server/DropBoxManagerService;->JC:I

    invoke-direct/range {v0 .. v7}, Lcom/miui/internal/server/DropBoxManagerService$a;-><init>(Ljava/io/File;Ljava/io/File;Ljava/lang/String;JII)V

    invoke-direct {p0, v0}, Lcom/miui/internal/server/DropBoxManagerService;->b(Lcom/miui/internal/server/DropBoxManagerService$a;)V

    move-wide v0, v8

    .line 637
    :goto_2
    add-int/lit8 v2, v10, 0x1

    move v10, v2

    move-wide v4, v0

    goto :goto_1

    .line 647
    :cond_2
    new-instance v2, Lcom/miui/internal/server/DropBoxManagerService$a;

    iget-object v3, p0, Lcom/miui/internal/server/DropBoxManagerService;->Jy:Ljava/io/File;

    iget-object v6, v6, Lcom/miui/internal/server/DropBoxManagerService$a;->tag:Ljava/lang/String;

    const-wide/16 v0, 0x1

    add-long/2addr v0, v4

    invoke-direct {v2, v3, v6, v4, v5}, Lcom/miui/internal/server/DropBoxManagerService$a;-><init>(Ljava/io/File;Ljava/lang/String;J)V

    invoke-direct {p0, v2}, Lcom/miui/internal/server/DropBoxManagerService;->b(Lcom/miui/internal/server/DropBoxManagerService$a;)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    goto :goto_2

    .line 619
    :catchall_0
    move-exception v0

    monitor-exit p0

    throw v0

    .line 652
    :cond_3
    if-nez p1, :cond_4

    .line 653
    :try_start_1
    new-instance v0, Lcom/miui/internal/server/DropBoxManagerService$a;

    iget-object v1, p0, Lcom/miui/internal/server/DropBoxManagerService;->Jy:Ljava/io/File;

    invoke-direct {v0, v1, p2, v4, v5}, Lcom/miui/internal/server/DropBoxManagerService$a;-><init>(Ljava/io/File;Ljava/lang/String;J)V

    invoke-direct {p0, v0}, Lcom/miui/internal/server/DropBoxManagerService;->b(Lcom/miui/internal/server/DropBoxManagerService$a;)V
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 657
    :goto_3
    monitor-exit p0

    return-wide v4

    .line 655
    :cond_4
    :try_start_2
    new-instance v0, Lcom/miui/internal/server/DropBoxManagerService$a;

    iget-object v2, p0, Lcom/miui/internal/server/DropBoxManagerService;->Jy:Ljava/io/File;

    iget v7, p0, Lcom/miui/internal/server/DropBoxManagerService;->JC:I

    move-object v1, p1

    move-object v3, p2

    move/from16 v6, p3

    invoke-direct/range {v0 .. v7}, Lcom/miui/internal/server/DropBoxManagerService$a;-><init>(Ljava/io/File;Ljava/io/File;Ljava/lang/String;JII)V

    invoke-direct {p0, v0}, Lcom/miui/internal/server/DropBoxManagerService;->b(Lcom/miui/internal/server/DropBoxManagerService$a;)V
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    goto :goto_3

    :cond_5
    move-object v11, v0

    goto/16 :goto_0
.end method

.method static synthetic a(Lcom/miui/internal/server/DropBoxManagerService;)Landroid/content/Context;
    .locals 1

    .prologue
    .line 47
    iget-object v0, p0, Lcom/miui/internal/server/DropBoxManagerService;->mContext:Landroid/content/Context;

    return-object v0
.end method

.method private declared-synchronized b(Lcom/miui/internal/server/DropBoxManagerService$a;)V
    .locals 3

    .prologue
    .line 600
    monitor-enter p0

    :try_start_0
    iget-object v0, p0, Lcom/miui/internal/server/DropBoxManagerService;->Jz:Lcom/miui/internal/server/DropBoxManagerService$b;

    iget-object v0, v0, Lcom/miui/internal/server/DropBoxManagerService$b;->FX:Ljava/util/TreeSet;

    invoke-virtual {v0, p1}, Ljava/util/TreeSet;->add(Ljava/lang/Object;)Z

    .line 601
    iget-object v0, p0, Lcom/miui/internal/server/DropBoxManagerService;->Jz:Lcom/miui/internal/server/DropBoxManagerService$b;

    iget v1, v0, Lcom/miui/internal/server/DropBoxManagerService$b;->Bk:I

    iget v2, p1, Lcom/miui/internal/server/DropBoxManagerService$a;->Bk:I

    add-int/2addr v1, v2

    iput v1, v0, Lcom/miui/internal/server/DropBoxManagerService$b;->Bk:I

    .line 606
    iget-object v0, p1, Lcom/miui/internal/server/DropBoxManagerService$a;->tag:Ljava/lang/String;

    if-eqz v0, :cond_1

    iget-object v0, p1, Lcom/miui/internal/server/DropBoxManagerService$a;->file:Ljava/io/File;

    if-eqz v0, :cond_1

    iget v0, p1, Lcom/miui/internal/server/DropBoxManagerService$a;->Bk:I

    if-lez v0, :cond_1

    .line 607
    iget-object v0, p0, Lcom/miui/internal/server/DropBoxManagerService;->JA:Ljava/util/HashMap;

    iget-object v1, p1, Lcom/miui/internal/server/DropBoxManagerService$a;->tag:Ljava/lang/String;

    invoke-virtual {v0, v1}, Ljava/util/HashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/server/DropBoxManagerService$b;

    .line 608
    if-nez v0, :cond_0

    .line 609
    new-instance v0, Lcom/miui/internal/server/DropBoxManagerService$b;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lcom/miui/internal/server/DropBoxManagerService$b;-><init>(Lcom/miui/internal/server/a;)V

    .line 610
    iget-object v1, p0, Lcom/miui/internal/server/DropBoxManagerService;->JA:Ljava/util/HashMap;

    iget-object v2, p1, Lcom/miui/internal/server/DropBoxManagerService$a;->tag:Ljava/lang/String;

    invoke-virtual {v1, v2, v0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 612
    :cond_0
    iget-object v1, v0, Lcom/miui/internal/server/DropBoxManagerService$b;->FX:Ljava/util/TreeSet;

    invoke-virtual {v1, p1}, Ljava/util/TreeSet;->add(Ljava/lang/Object;)Z

    .line 613
    iget v1, v0, Lcom/miui/internal/server/DropBoxManagerService$b;->Bk:I

    iget v2, p1, Lcom/miui/internal/server/DropBoxManagerService$a;->Bk:I

    add-int/2addr v1, v2

    iput v1, v0, Lcom/miui/internal/server/DropBoxManagerService$b;->Bk:I
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 615
    :cond_1
    monitor-exit p0

    return-void

    .line 600
    :catchall_0
    move-exception v0

    monitor-exit p0

    throw v0
.end method

.method static synthetic b(Lcom/miui/internal/server/DropBoxManagerService;)V
    .locals 0
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 47
    invoke-direct {p0}, Lcom/miui/internal/server/DropBoxManagerService;->init()V

    return-void
.end method

.method static synthetic c(Lcom/miui/internal/server/DropBoxManagerService;)J
    .locals 2

    .prologue
    .line 47
    invoke-direct {p0}, Lcom/miui/internal/server/DropBoxManagerService;->dU()J

    move-result-wide v0

    return-wide v0
.end method

.method private dT()V
    .locals 2

    .prologue
    .line 130
    const-wide/16 v0, 0x0

    iput-wide v0, p0, Lcom/miui/internal/server/DropBoxManagerService;->JF:J

    .line 135
    new-instance v0, Lcom/miui/internal/server/DropBoxManagerService$1;

    invoke-direct {v0, p0}, Lcom/miui/internal/server/DropBoxManagerService$1;-><init>(Lcom/miui/internal/server/DropBoxManagerService;)V

    invoke-virtual {v0}, Lcom/miui/internal/server/DropBoxManagerService$1;->start()V

    .line 145
    return-void
.end method

.method private declared-synchronized dU()J
    .locals 9

    .prologue
    const/16 v8, 0xa

    const/4 v2, 0x0

    .line 667
    monitor-enter p0

    .line 668
    const/16 v3, 0x3e8

    .line 669
    :try_start_0
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v0

    const v4, 0xf731400

    int-to-long v4, v4

    sub-long v4, v0, v4

    .line 670
    :cond_0
    :goto_0
    iget-object v0, p0, Lcom/miui/internal/server/DropBoxManagerService;->Jz:Lcom/miui/internal/server/DropBoxManagerService$b;

    iget-object v0, v0, Lcom/miui/internal/server/DropBoxManagerService$b;->FX:Ljava/util/TreeSet;

    invoke-virtual {v0}, Ljava/util/TreeSet;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_1

    .line 671
    iget-object v0, p0, Lcom/miui/internal/server/DropBoxManagerService;->Jz:Lcom/miui/internal/server/DropBoxManagerService$b;

    iget-object v0, v0, Lcom/miui/internal/server/DropBoxManagerService$b;->FX:Ljava/util/TreeSet;

    invoke-virtual {v0}, Ljava/util/TreeSet;->first()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/server/DropBoxManagerService$a;

    .line 672
    iget-wide v6, v0, Lcom/miui/internal/server/DropBoxManagerService$a;->Bj:J

    cmp-long v1, v6, v4

    if-lez v1, :cond_6

    iget-object v1, p0, Lcom/miui/internal/server/DropBoxManagerService;->Jz:Lcom/miui/internal/server/DropBoxManagerService$b;

    iget-object v1, v1, Lcom/miui/internal/server/DropBoxManagerService$b;->FX:Ljava/util/TreeSet;

    invoke-virtual {v1}, Ljava/util/TreeSet;->size()I

    move-result v1

    if-ge v1, v3, :cond_6

    .line 685
    :cond_1
    invoke-static {}, Landroid/os/SystemClock;->uptimeMillis()J

    move-result-wide v0

    .line 686
    iget-wide v3, p0, Lcom/miui/internal/server/DropBoxManagerService;->JF:J

    const-wide/16 v5, 0x1388

    add-long/2addr v3, v5

    cmp-long v3, v0, v3

    if-lez v3, :cond_2

    .line 691
    iget-object v3, p0, Lcom/miui/internal/server/DropBoxManagerService;->JB:Landroid/os/StatFs;

    iget-object v4, p0, Lcom/miui/internal/server/DropBoxManagerService;->Jy:Ljava/io/File;

    invoke-virtual {v4}, Ljava/io/File;->getPath()Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v3, v4}, Landroid/os/StatFs;->restat(Ljava/lang/String;)V

    .line 692
    iget-object v3, p0, Lcom/miui/internal/server/DropBoxManagerService;->JB:Landroid/os/StatFs;

    invoke-virtual {v3}, Landroid/os/StatFs;->getAvailableBlocks()I

    move-result v3

    .line 693
    iget-object v4, p0, Lcom/miui/internal/server/DropBoxManagerService;->JB:Landroid/os/StatFs;

    invoke-virtual {v4}, Landroid/os/StatFs;->getBlockCount()I

    move-result v4

    mul-int/2addr v4, v8

    div-int/lit8 v4, v4, 0x64

    sub-int/2addr v3, v4

    .line 694
    const/high16 v4, 0x500000

    iget v5, p0, Lcom/miui/internal/server/DropBoxManagerService;->JC:I

    div-int/2addr v4, v5

    .line 695
    const/4 v5, 0x0

    mul-int/2addr v3, v8

    div-int/lit8 v3, v3, 0x64

    invoke-static {v5, v3}, Ljava/lang/Math;->max(II)I

    move-result v3

    invoke-static {v4, v3}, Ljava/lang/Math;->min(II)I

    move-result v3

    iput v3, p0, Lcom/miui/internal/server/DropBoxManagerService;->JD:I

    .line 696
    iput-wide v0, p0, Lcom/miui/internal/server/DropBoxManagerService;->JF:J

    .line 713
    :cond_2
    iget-object v0, p0, Lcom/miui/internal/server/DropBoxManagerService;->Jz:Lcom/miui/internal/server/DropBoxManagerService$b;

    iget v0, v0, Lcom/miui/internal/server/DropBoxManagerService$b;->Bk:I

    iget v1, p0, Lcom/miui/internal/server/DropBoxManagerService;->JD:I

    if-le v0, v1, :cond_5

    .line 715
    iget-object v0, p0, Lcom/miui/internal/server/DropBoxManagerService;->Jz:Lcom/miui/internal/server/DropBoxManagerService$b;

    iget v0, v0, Lcom/miui/internal/server/DropBoxManagerService$b;->Bk:I

    .line 716
    new-instance v3, Ljava/util/TreeSet;

    iget-object v1, p0, Lcom/miui/internal/server/DropBoxManagerService;->JA:Ljava/util/HashMap;

    invoke-virtual {v1}, Ljava/util/HashMap;->values()Ljava/util/Collection;

    move-result-object v1

    invoke-direct {v3, v1}, Ljava/util/TreeSet;-><init>(Ljava/util/Collection;)V

    .line 717
    invoke-virtual {v3}, Ljava/util/TreeSet;->iterator()Ljava/util/Iterator;

    move-result-object v4

    move v1, v2

    move v2, v0

    :goto_1
    invoke-interface {v4}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_3

    invoke-interface {v4}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/server/DropBoxManagerService$b;

    .line 718
    if-lez v1, :cond_9

    iget v5, v0, Lcom/miui/internal/server/DropBoxManagerService$b;->Bk:I

    iget v6, p0, Lcom/miui/internal/server/DropBoxManagerService;->JD:I

    sub-int/2addr v6, v2

    div-int/2addr v6, v1

    if-gt v5, v6, :cond_9

    .line 724
    :cond_3
    iget v0, p0, Lcom/miui/internal/server/DropBoxManagerService;->JD:I

    sub-int/2addr v0, v2

    div-int v2, v0, v1

    .line 727
    invoke-virtual {v3}, Ljava/util/TreeSet;->iterator()Ljava/util/Iterator;

    move-result-object v3

    :cond_4
    invoke-interface {v3}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_5

    invoke-interface {v3}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/server/DropBoxManagerService$b;

    .line 728
    iget-object v1, p0, Lcom/miui/internal/server/DropBoxManagerService;->Jz:Lcom/miui/internal/server/DropBoxManagerService$b;

    iget v1, v1, Lcom/miui/internal/server/DropBoxManagerService$b;->Bk:I

    iget v4, p0, Lcom/miui/internal/server/DropBoxManagerService;->JD:I

    if-ge v1, v4, :cond_a

    .line 744
    :cond_5
    iget v0, p0, Lcom/miui/internal/server/DropBoxManagerService;->JD:I

    iget v1, p0, Lcom/miui/internal/server/DropBoxManagerService;->JC:I
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    mul-int/2addr v0, v1

    int-to-long v0, v0

    monitor-exit p0

    return-wide v0

    .line 674
    :cond_6
    :try_start_1
    iget-object v1, p0, Lcom/miui/internal/server/DropBoxManagerService;->JA:Ljava/util/HashMap;

    iget-object v6, v0, Lcom/miui/internal/server/DropBoxManagerService$a;->tag:Ljava/lang/String;

    invoke-virtual {v1, v6}, Ljava/util/HashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/miui/internal/server/DropBoxManagerService$b;

    .line 675
    if-eqz v1, :cond_7

    iget-object v6, v1, Lcom/miui/internal/server/DropBoxManagerService$b;->FX:Ljava/util/TreeSet;

    invoke-virtual {v6, v0}, Ljava/util/TreeSet;->remove(Ljava/lang/Object;)Z

    move-result v6

    if-eqz v6, :cond_7

    iget v6, v1, Lcom/miui/internal/server/DropBoxManagerService$b;->Bk:I

    iget v7, v0, Lcom/miui/internal/server/DropBoxManagerService$a;->Bk:I

    sub-int/2addr v6, v7

    iput v6, v1, Lcom/miui/internal/server/DropBoxManagerService$b;->Bk:I

    .line 676
    :cond_7
    iget-object v1, p0, Lcom/miui/internal/server/DropBoxManagerService;->Jz:Lcom/miui/internal/server/DropBoxManagerService$b;

    iget-object v1, v1, Lcom/miui/internal/server/DropBoxManagerService$b;->FX:Ljava/util/TreeSet;

    invoke-virtual {v1, v0}, Ljava/util/TreeSet;->remove(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_8

    iget-object v1, p0, Lcom/miui/internal/server/DropBoxManagerService;->Jz:Lcom/miui/internal/server/DropBoxManagerService$b;

    iget v6, v1, Lcom/miui/internal/server/DropBoxManagerService$b;->Bk:I

    iget v7, v0, Lcom/miui/internal/server/DropBoxManagerService$a;->Bk:I

    sub-int/2addr v6, v7

    iput v6, v1, Lcom/miui/internal/server/DropBoxManagerService$b;->Bk:I

    .line 677
    :cond_8
    iget-object v1, v0, Lcom/miui/internal/server/DropBoxManagerService$a;->file:Ljava/io/File;

    if-eqz v1, :cond_0

    iget-object v0, v0, Lcom/miui/internal/server/DropBoxManagerService$a;->file:Ljava/io/File;

    invoke-virtual {v0}, Ljava/io/File;->delete()Z
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    goto/16 :goto_0

    .line 667
    :catchall_0
    move-exception v0

    monitor-exit p0

    throw v0

    .line 721
    :cond_9
    :try_start_2
    iget v0, v0, Lcom/miui/internal/server/DropBoxManagerService$b;->Bk:I

    sub-int/2addr v2, v0

    .line 722
    add-int/lit8 v0, v1, 0x1

    move v1, v0

    goto :goto_1

    .line 729
    :cond_a
    :goto_2
    iget v1, v0, Lcom/miui/internal/server/DropBoxManagerService$b;->Bk:I

    if-le v1, v2, :cond_4

    iget-object v1, v0, Lcom/miui/internal/server/DropBoxManagerService$b;->FX:Ljava/util/TreeSet;

    invoke-virtual {v1}, Ljava/util/TreeSet;->isEmpty()Z

    move-result v1

    if-nez v1, :cond_4

    .line 730
    iget-object v1, v0, Lcom/miui/internal/server/DropBoxManagerService$b;->FX:Ljava/util/TreeSet;

    invoke-virtual {v1}, Ljava/util/TreeSet;->first()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/miui/internal/server/DropBoxManagerService$a;

    .line 731
    iget-object v4, v0, Lcom/miui/internal/server/DropBoxManagerService$b;->FX:Ljava/util/TreeSet;

    invoke-virtual {v4, v1}, Ljava/util/TreeSet;->remove(Ljava/lang/Object;)Z

    move-result v4

    if-eqz v4, :cond_b

    iget v4, v0, Lcom/miui/internal/server/DropBoxManagerService$b;->Bk:I

    iget v5, v1, Lcom/miui/internal/server/DropBoxManagerService$a;->Bk:I

    sub-int/2addr v4, v5

    iput v4, v0, Lcom/miui/internal/server/DropBoxManagerService$b;->Bk:I

    .line 732
    :cond_b
    iget-object v4, p0, Lcom/miui/internal/server/DropBoxManagerService;->Jz:Lcom/miui/internal/server/DropBoxManagerService$b;

    iget-object v4, v4, Lcom/miui/internal/server/DropBoxManagerService$b;->FX:Ljava/util/TreeSet;

    invoke-virtual {v4, v1}, Ljava/util/TreeSet;->remove(Ljava/lang/Object;)Z

    move-result v4

    if-eqz v4, :cond_c

    iget-object v4, p0, Lcom/miui/internal/server/DropBoxManagerService;->Jz:Lcom/miui/internal/server/DropBoxManagerService$b;

    iget v5, v4, Lcom/miui/internal/server/DropBoxManagerService$b;->Bk:I

    iget v6, v1, Lcom/miui/internal/server/DropBoxManagerService$a;->Bk:I

    sub-int/2addr v5, v6

    iput v5, v4, Lcom/miui/internal/server/DropBoxManagerService$b;->Bk:I
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    .line 735
    :cond_c
    :try_start_3
    iget-object v4, v1, Lcom/miui/internal/server/DropBoxManagerService$a;->file:Ljava/io/File;

    if-eqz v4, :cond_d

    iget-object v4, v1, Lcom/miui/internal/server/DropBoxManagerService$a;->file:Ljava/io/File;

    invoke-virtual {v4}, Ljava/io/File;->delete()Z

    .line 736
    :cond_d
    new-instance v4, Lcom/miui/internal/server/DropBoxManagerService$a;

    iget-object v5, p0, Lcom/miui/internal/server/DropBoxManagerService;->Jy:Ljava/io/File;

    iget-object v6, v1, Lcom/miui/internal/server/DropBoxManagerService$a;->tag:Ljava/lang/String;

    iget-wide v7, v1, Lcom/miui/internal/server/DropBoxManagerService$a;->Bj:J

    invoke-direct {v4, v5, v6, v7, v8}, Lcom/miui/internal/server/DropBoxManagerService$a;-><init>(Ljava/io/File;Ljava/lang/String;J)V

    invoke-direct {p0, v4}, Lcom/miui/internal/server/DropBoxManagerService;->b(Lcom/miui/internal/server/DropBoxManagerService$a;)V
    :try_end_3
    .catch Ljava/io/IOException; {:try_start_3 .. :try_end_3} :catch_0
    .catchall {:try_start_3 .. :try_end_3} :catchall_0

    goto :goto_2

    .line 737
    :catch_0
    move-exception v1

    .line 738
    :try_start_4
    const-string v4, "DropBoxManagerService"

    const-string v5, "Can\'t write tombstone file"

    invoke-static {v4, v5, v1}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    :try_end_4
    .catchall {:try_start_4 .. :try_end_4} :catchall_0

    goto :goto_2
.end method

.method public static getInstance()Lcom/miui/internal/server/DropBoxManagerService;
    .locals 1

    .prologue
    .line 58
    sget-object v0, Lcom/miui/internal/server/DropBoxManagerService;->f:Lmiui/util/SoftReferenceSingleton;

    invoke-virtual {v0}, Lmiui/util/SoftReferenceSingleton;->get()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/server/DropBoxManagerService;

    return-object v0
.end method

.method private declared-synchronized init()V
    .locals 9
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 556
    monitor-enter p0

    :try_start_0
    iget-object v0, p0, Lcom/miui/internal/server/DropBoxManagerService;->JB:Landroid/os/StatFs;

    if-nez v0, :cond_1

    .line 557
    iget-object v0, p0, Lcom/miui/internal/server/DropBoxManagerService;->Jy:Ljava/io/File;

    invoke-virtual {v0}, Ljava/io/File;->isDirectory()Z

    move-result v0

    if-nez v0, :cond_0

    iget-object v0, p0, Lcom/miui/internal/server/DropBoxManagerService;->Jy:Ljava/io/File;

    invoke-virtual {v0}, Ljava/io/File;->mkdirs()Z

    move-result v0

    if-nez v0, :cond_0

    .line 558
    new-instance v0, Ljava/io/IOException;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "Can\'t mkdir: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    iget-object v2, p0, Lcom/miui/internal/server/DropBoxManagerService;->Jy:Ljava/io/File;

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/io/IOException;-><init>(Ljava/lang/String;)V

    throw v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 556
    :catchall_0
    move-exception v0

    monitor-exit p0

    throw v0

    .line 561
    :cond_0
    :try_start_1
    new-instance v0, Landroid/os/StatFs;

    iget-object v1, p0, Lcom/miui/internal/server/DropBoxManagerService;->Jy:Ljava/io/File;

    invoke-virtual {v1}, Ljava/io/File;->getPath()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Landroid/os/StatFs;-><init>(Ljava/lang/String;)V

    iput-object v0, p0, Lcom/miui/internal/server/DropBoxManagerService;->JB:Landroid/os/StatFs;

    .line 562
    iget-object v0, p0, Lcom/miui/internal/server/DropBoxManagerService;->JB:Landroid/os/StatFs;

    invoke-virtual {v0}, Landroid/os/StatFs;->getBlockSize()I

    move-result v0

    iput v0, p0, Lcom/miui/internal/server/DropBoxManagerService;->JC:I
    :try_end_1
    .catch Ljava/lang/IllegalArgumentException; {:try_start_1 .. :try_end_1} :catch_0
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 568
    :cond_1
    :try_start_2
    iget-object v0, p0, Lcom/miui/internal/server/DropBoxManagerService;->Jz:Lcom/miui/internal/server/DropBoxManagerService$b;

    if-nez v0, :cond_6

    .line 569
    iget-object v0, p0, Lcom/miui/internal/server/DropBoxManagerService;->Jy:Ljava/io/File;

    invoke-virtual {v0}, Ljava/io/File;->listFiles()[Ljava/io/File;

    move-result-object v1

    .line 570
    if-nez v1, :cond_2

    new-instance v0, Ljava/io/IOException;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "Can\'t list files: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    iget-object v2, p0, Lcom/miui/internal/server/DropBoxManagerService;->Jy:Ljava/io/File;

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/io/IOException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 563
    :catch_0
    move-exception v0

    .line 564
    new-instance v0, Ljava/io/IOException;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "Can\'t statfs: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    iget-object v2, p0, Lcom/miui/internal/server/DropBoxManagerService;->Jy:Ljava/io/File;

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/io/IOException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 572
    :cond_2
    new-instance v0, Lcom/miui/internal/server/DropBoxManagerService$b;

    const/4 v2, 0x0

    invoke-direct {v0, v2}, Lcom/miui/internal/server/DropBoxManagerService$b;-><init>(Lcom/miui/internal/server/a;)V

    iput-object v0, p0, Lcom/miui/internal/server/DropBoxManagerService;->Jz:Lcom/miui/internal/server/DropBoxManagerService$b;

    .line 573
    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    iput-object v0, p0, Lcom/miui/internal/server/DropBoxManagerService;->JA:Ljava/util/HashMap;

    .line 576
    array-length v2, v1

    const/4 v0, 0x0

    :goto_0
    if-ge v0, v2, :cond_6

    aget-object v3, v1, v0

    .line 577
    invoke-virtual {v3}, Ljava/io/File;->getName()Ljava/lang/String;

    move-result-object v4

    const-string v5, ".tmp"

    invoke-virtual {v4, v5}, Ljava/lang/String;->endsWith(Ljava/lang/String;)Z

    move-result v4

    if-eqz v4, :cond_3

    .line 578
    const-string v4, "DropBoxManagerService"

    new-instance v5, Ljava/lang/StringBuilder;

    invoke-direct {v5}, Ljava/lang/StringBuilder;-><init>()V

    const-string v6, "Cleaning temp file: "

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v5

    invoke-virtual {v5, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v5

    invoke-virtual {v5}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v5

    invoke-static {v4, v5}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I

    .line 579
    invoke-virtual {v3}, Ljava/io/File;->delete()Z

    .line 576
    :goto_1
    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    .line 583
    :cond_3
    new-instance v4, Lcom/miui/internal/server/DropBoxManagerService$a;

    iget v5, p0, Lcom/miui/internal/server/DropBoxManagerService;->JC:I

    invoke-direct {v4, v3, v5}, Lcom/miui/internal/server/DropBoxManagerService$a;-><init>(Ljava/io/File;I)V

    .line 584
    iget-object v5, v4, Lcom/miui/internal/server/DropBoxManagerService$a;->tag:Ljava/lang/String;

    if-nez v5, :cond_4

    .line 585
    const-string v4, "DropBoxManagerService"

    new-instance v5, Ljava/lang/StringBuilder;

    invoke-direct {v5}, Ljava/lang/StringBuilder;-><init>()V

    const-string v6, "Unrecognized file: "

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v5

    invoke-virtual {v5, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-static {v4, v3}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    goto :goto_1

    .line 587
    :cond_4
    iget-wide v5, v4, Lcom/miui/internal/server/DropBoxManagerService$a;->Bj:J

    const-wide/16 v7, 0x0

    cmp-long v5, v5, v7

    if-nez v5, :cond_5

    .line 588
    const-string v4, "DropBoxManagerService"

    new-instance v5, Ljava/lang/StringBuilder;

    invoke-direct {v5}, Ljava/lang/StringBuilder;-><init>()V

    const-string v6, "Invalid filename: "

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v5

    invoke-virtual {v5, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v5

    invoke-virtual {v5}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v5

    invoke-static {v4, v5}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    .line 589
    invoke-virtual {v3}, Ljava/io/File;->delete()Z

    goto :goto_1

    .line 593
    :cond_5
    invoke-direct {p0, v4}, Lcom/miui/internal/server/DropBoxManagerService;->b(Lcom/miui/internal/server/DropBoxManagerService$a;)V
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    goto :goto_1

    .line 596
    :cond_6
    monitor-exit p0

    return-void
.end method

.method public static onReceive(Landroid/content/Context;Landroid/content/Intent;)V
    .locals 1

    .prologue
    .line 62
    const-string v0, "android.intent.action.DEVICE_STORAGE_LOW"

    invoke-static {p1, v0}, Lcom/miui/internal/server/Receiver;->isActionEquals(Landroid/content/Intent;Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 63
    invoke-static {}, Lcom/miui/internal/server/DropBoxManagerService;->getInstance()Lcom/miui/internal/server/DropBoxManagerService;

    move-result-object v0

    invoke-direct {v0}, Lcom/miui/internal/server/DropBoxManagerService;->dT()V

    .line 65
    :cond_0
    return-void
.end method


# virtual methods
.method public add(Lmiui/os/DropBoxManager$Entry;)V
    .locals 21

    .prologue
    .line 149
    const/4 v4, 0x0

    .line 150
    const/4 v3, 0x0

    .line 151
    invoke-virtual/range {p1 .. p1}, Lmiui/os/DropBoxManager$Entry;->getTag()Ljava/lang/String;

    move-result-object v13

    .line 153
    :try_start_0
    invoke-virtual/range {p1 .. p1}, Lmiui/os/DropBoxManager$Entry;->getFlags()I

    move-result v6

    .line 154
    and-int/lit8 v2, v6, 0x1

    if-eqz v2, :cond_2

    new-instance v2, Ljava/lang/IllegalArgumentException;

    invoke-direct {v2}, Ljava/lang/IllegalArgumentException;-><init>()V

    throw v2
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 226
    :catch_0
    move-exception v2

    .line 227
    :goto_0
    :try_start_1
    const-string v5, "DropBoxManagerService"

    new-instance v6, Ljava/lang/StringBuilder;

    invoke-direct {v6}, Ljava/lang/StringBuilder;-><init>()V

    const-string v7, "Can\'t write: "

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v6

    invoke-virtual {v6, v13}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v6

    invoke-virtual {v6}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v6

    invoke-static {v5, v6, v2}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 229
    if-eqz v3, :cond_0

    :try_start_2
    invoke-virtual {v3}, Ljava/io/OutputStream;->close()V
    :try_end_2
    .catch Ljava/io/IOException; {:try_start_2 .. :try_end_2} :catch_4

    .line 230
    :cond_0
    :goto_1
    invoke-virtual/range {p1 .. p1}, Lmiui/os/DropBoxManager$Entry;->close()V

    .line 231
    if-eqz v4, :cond_1

    invoke-virtual {v4}, Ljava/io/File;->delete()Z

    .line 233
    :cond_1
    :goto_2
    return-void

    .line 156
    :cond_2
    :try_start_3
    invoke-direct/range {p0 .. p0}, Lcom/miui/internal/server/DropBoxManagerService;->init()V

    .line 157
    move-object/from16 v0, p0

    invoke-virtual {v0, v13}, Lcom/miui/internal/server/DropBoxManagerService;->isTagEnabled(Ljava/lang/String;)Z
    :try_end_3
    .catch Ljava/io/IOException; {:try_start_3 .. :try_end_3} :catch_0
    .catchall {:try_start_3 .. :try_end_3} :catchall_0

    move-result v2

    if-nez v2, :cond_4

    .line 229
    if-eqz v3, :cond_3

    :try_start_4
    invoke-virtual {v3}, Ljava/io/OutputStream;->close()V
    :try_end_4
    .catch Ljava/io/IOException; {:try_start_4 .. :try_end_4} :catch_2

    .line 230
    :cond_3
    :goto_3
    invoke-virtual/range {p1 .. p1}, Lmiui/os/DropBoxManager$Entry;->close()V

    .line 231
    if-eqz v4, :cond_1

    invoke-virtual {v4}, Ljava/io/File;->delete()Z

    goto :goto_2

    .line 158
    :cond_4
    :try_start_5
    invoke-direct/range {p0 .. p0}, Lcom/miui/internal/server/DropBoxManagerService;->dU()J

    move-result-wide v10

    .line 159
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v8

    .line 161
    move-object/from16 v0, p0

    iget v2, v0, Lcom/miui/internal/server/DropBoxManagerService;->JC:I

    new-array v14, v2, [B

    .line 162
    invoke-virtual/range {p1 .. p1}, Lmiui/os/DropBoxManager$Entry;->getInputStream()Ljava/io/InputStream;

    move-result-object v15

    .line 167
    const/4 v2, 0x0

    .line 168
    :goto_4
    array-length v5, v14

    if-ge v2, v5, :cond_5

    .line 169
    array-length v5, v14

    sub-int/2addr v5, v2

    invoke-virtual {v15, v14, v2, v5}, Ljava/io/InputStream;->read([BII)I

    move-result v5

    .line 170
    if-gtz v5, :cond_8

    .line 177
    :cond_5
    new-instance v5, Ljava/io/File;

    move-object/from16 v0, p0

    iget-object v7, v0, Lcom/miui/internal/server/DropBoxManagerService;->Jy:Ljava/io/File;

    new-instance v12, Ljava/lang/StringBuilder;

    invoke-direct {v12}, Ljava/lang/StringBuilder;-><init>()V

    const-string v16, "drop"

    move-object/from16 v0, v16

    invoke-virtual {v12, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v12

    invoke-static {}, Ljava/lang/Thread;->currentThread()Ljava/lang/Thread;

    move-result-object v16

    invoke-virtual/range {v16 .. v16}, Ljava/lang/Thread;->getId()J

    move-result-wide v16

    move-wide/from16 v0, v16

    invoke-virtual {v12, v0, v1}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    move-result-object v12

    const-string v16, ".tmp"

    move-object/from16 v0, v16

    invoke-virtual {v12, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v12

    invoke-virtual {v12}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v12

    invoke-direct {v5, v7, v12}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V
    :try_end_5
    .catch Ljava/io/IOException; {:try_start_5 .. :try_end_5} :catch_0
    .catchall {:try_start_5 .. :try_end_5} :catchall_0

    .line 178
    :try_start_6
    move-object/from16 v0, p0

    iget v4, v0, Lcom/miui/internal/server/DropBoxManagerService;->JC:I

    .line 179
    const/16 v7, 0x1000

    if-le v4, v7, :cond_6

    const/16 v4, 0x1000

    .line 180
    :cond_6
    const/16 v7, 0x200

    if-ge v4, v7, :cond_10

    const/16 v4, 0x200

    move v7, v4

    .line 181
    :goto_5
    new-instance v16, Ljava/io/FileOutputStream;

    move-object/from16 v0, v16

    invoke-direct {v0, v5}, Ljava/io/FileOutputStream;-><init>(Ljava/io/File;)V

    .line 182
    new-instance v4, Ljava/io/BufferedOutputStream;

    move-object/from16 v0, v16

    invoke-direct {v4, v0, v7}, Ljava/io/BufferedOutputStream;-><init>(Ljava/io/OutputStream;I)V
    :try_end_6
    .catch Ljava/io/IOException; {:try_start_6 .. :try_end_6} :catch_1
    .catchall {:try_start_6 .. :try_end_6} :catchall_1

    .line 183
    :try_start_7
    array-length v3, v14

    if-ne v2, v3, :cond_f

    and-int/lit8 v3, v6, 0x4

    if-nez v3, :cond_f

    .line 184
    new-instance v7, Ljava/util/zip/GZIPOutputStream;

    invoke-direct {v7, v4}, Ljava/util/zip/GZIPOutputStream;-><init>(Ljava/io/OutputStream;)V
    :try_end_7
    .catch Ljava/io/IOException; {:try_start_7 .. :try_end_7} :catch_6
    .catchall {:try_start_7 .. :try_end_7} :catchall_2

    .line 185
    or-int/lit8 v3, v6, 0x4

    move v12, v3

    move-object v3, v7

    .line 189
    :goto_6
    const/4 v4, 0x0

    :try_start_8
    invoke-virtual {v3, v14, v4, v2}, Ljava/io/OutputStream;->write([BII)V

    .line 191
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v6

    .line 192
    sub-long v17, v6, v8

    const-wide/16 v19, 0x7530

    cmp-long v2, v17, v19

    if-lez v2, :cond_e

    .line 193
    invoke-direct/range {p0 .. p0}, Lcom/miui/internal/server/DropBoxManagerService;->dU()J

    move-result-wide v8

    .line 197
    :goto_7
    invoke-virtual {v15, v14}, Ljava/io/InputStream;->read([B)I

    move-result v2

    .line 198
    if-gtz v2, :cond_9

    .line 199
    invoke-static/range {v16 .. v16}, Lmiui/os/FileUtils;->sync(Ljava/io/FileOutputStream;)Z

    .line 200
    invoke-virtual {v3}, Ljava/io/OutputStream;->close()V

    .line 201
    const/4 v3, 0x0

    .line 206
    :goto_8
    invoke-virtual {v5}, Ljava/io/File;->length()J

    move-result-wide v10

    .line 207
    cmp-long v4, v10, v8

    if-lez v4, :cond_a

    .line 208
    const-string v2, "DropBoxManagerService"

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    const-string v6, "Dropping: "

    invoke-virtual {v4, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4, v13}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    const-string v6, " ("

    invoke-virtual {v4, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v5}, Ljava/io/File;->length()J

    move-result-wide v6

    invoke-virtual {v4, v6, v7}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    move-result-object v4

    const-string v6, " > "

    invoke-virtual {v4, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4, v8, v9}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    move-result-object v4

    const-string v6, " bytes)"

    invoke-virtual {v4, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-static {v2, v4}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    .line 209
    invoke-virtual {v5}, Ljava/io/File;->delete()Z
    :try_end_8
    .catch Ljava/io/IOException; {:try_start_8 .. :try_end_8} :catch_1
    .catchall {:try_start_8 .. :try_end_8} :catchall_1

    .line 210
    const/4 v4, 0x0

    .line 215
    :goto_9
    :try_start_9
    move-object/from16 v0, p0

    invoke-direct {v0, v4, v13, v12}, Lcom/miui/internal/server/DropBoxManagerService;->a(Ljava/io/File;Ljava/lang/String;I)J

    move-result-wide v5

    .line 216
    const/4 v4, 0x0

    .line 218
    new-instance v2, Landroid/content/Intent;

    const-string v7, "miui.intent.action.DROPBOX_ENTRY_ADDED"

    invoke-direct {v2, v7}, Landroid/content/Intent;-><init>(Ljava/lang/String;)V

    .line 219
    const-string v7, "tag"

    invoke-virtual {v2, v7, v13}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;

    .line 220
    const-string v7, "time"

    invoke-virtual {v2, v7, v5, v6}, Landroid/content/Intent;->putExtra(Ljava/lang/String;J)Landroid/content/Intent;

    .line 225
    move-object/from16 v0, p0

    iget-object v5, v0, Lcom/miui/internal/server/DropBoxManagerService;->mHandler:Landroid/os/Handler;

    move-object/from16 v0, p0

    iget-object v6, v0, Lcom/miui/internal/server/DropBoxManagerService;->mHandler:Landroid/os/Handler;

    const/4 v7, 0x1

    invoke-virtual {v6, v7, v2}, Landroid/os/Handler;->obtainMessage(ILjava/lang/Object;)Landroid/os/Message;

    move-result-object v2

    invoke-virtual {v5, v2}, Landroid/os/Handler;->sendMessage(Landroid/os/Message;)Z
    :try_end_9
    .catch Ljava/io/IOException; {:try_start_9 .. :try_end_9} :catch_0
    .catchall {:try_start_9 .. :try_end_9} :catchall_0

    .line 229
    if-eqz v3, :cond_7

    :try_start_a
    invoke-virtual {v3}, Ljava/io/OutputStream;->close()V
    :try_end_a
    .catch Ljava/io/IOException; {:try_start_a .. :try_end_a} :catch_3

    .line 230
    :cond_7
    :goto_a
    invoke-virtual/range {p1 .. p1}, Lmiui/os/DropBoxManager$Entry;->close()V

    .line 231
    if-eqz v4, :cond_1

    invoke-virtual {v4}, Ljava/io/File;->delete()Z

    goto/16 :goto_2

    .line 171
    :cond_8
    add-int/2addr v2, v5

    .line 172
    goto/16 :goto_4

    .line 203
    :cond_9
    :try_start_b
    invoke-virtual {v3}, Ljava/io/OutputStream;->flush()V
    :try_end_b
    .catch Ljava/io/IOException; {:try_start_b .. :try_end_b} :catch_1
    .catchall {:try_start_b .. :try_end_b} :catchall_1

    goto :goto_8

    .line 226
    :catch_1
    move-exception v2

    move-object v4, v5

    goto/16 :goto_0

    .line 213
    :cond_a
    if-gtz v2, :cond_d

    move-object v4, v5

    goto :goto_9

    .line 229
    :catchall_0
    move-exception v2

    :goto_b
    if-eqz v3, :cond_b

    :try_start_c
    invoke-virtual {v3}, Ljava/io/OutputStream;->close()V
    :try_end_c
    .catch Ljava/io/IOException; {:try_start_c .. :try_end_c} :catch_5

    .line 230
    :cond_b
    :goto_c
    invoke-virtual/range {p1 .. p1}, Lmiui/os/DropBoxManager$Entry;->close()V

    .line 231
    if-eqz v4, :cond_c

    invoke-virtual {v4}, Ljava/io/File;->delete()Z

    :cond_c
    throw v2

    .line 229
    :catch_2
    move-exception v2

    goto/16 :goto_3

    :catch_3
    move-exception v2

    goto :goto_a

    :catch_4
    move-exception v2

    goto/16 :goto_1

    :catch_5
    move-exception v3

    goto :goto_c

    :catchall_1
    move-exception v2

    move-object v4, v5

    goto :goto_b

    :catchall_2
    move-exception v2

    move-object v3, v4

    move-object v4, v5

    goto :goto_b

    .line 226
    :catch_6
    move-exception v2

    move-object v3, v4

    move-object v4, v5

    goto/16 :goto_0

    :cond_d
    move-wide v10, v8

    move-wide v8, v6

    goto/16 :goto_6

    :cond_e
    move-wide v6, v8

    move-wide v8, v10

    goto/16 :goto_7

    :cond_f
    move v12, v6

    move-object v3, v4

    goto/16 :goto_6

    :cond_10
    move v7, v4

    goto/16 :goto_5
.end method

.method public declared-synchronized dump(Ljava/io/FileDescriptor;Ljava/io/PrintWriter;[Ljava/lang/String;)V
    .locals 19

    .prologue
    .line 281
    monitor-enter p0

    :try_start_0
    move-object/from16 v0, p0

    iget-object v1, v0, Lcom/miui/internal/server/DropBoxManagerService;->mContext:Landroid/content/Context;

    const-string v2, "android.permission.DUMP"

    invoke-virtual {v1, v2}, Landroid/content/Context;->checkCallingOrSelfPermission(Ljava/lang/String;)I

    move-result v1

    if-eqz v1, :cond_0

    .line 284
    const-string v1, "Permission Denial: Can\'t dump DropBoxManagerService"

    move-object/from16 v0, p2

    invoke-virtual {v0, v1}, Ljava/io/PrintWriter;->println(Ljava/lang/String;)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 412
    :goto_0
    monitor-exit p0

    return-void

    .line 289
    :cond_0
    :try_start_1
    invoke-direct/range {p0 .. p0}, Lcom/miui/internal/server/DropBoxManagerService;->init()V
    :try_end_1
    .catch Ljava/io/IOException; {:try_start_1 .. :try_end_1} :catch_0
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 298
    :try_start_2
    new-instance v13, Ljava/lang/StringBuilder;

    invoke-direct {v13}, Ljava/lang/StringBuilder;-><init>()V

    .line 299
    const/4 v9, 0x0

    const/4 v8, 0x0

    .line 300
    new-instance v14, Ljava/util/ArrayList;

    invoke-direct {v14}, Ljava/util/ArrayList;-><init>()V

    .line 301
    const/4 v1, 0x0

    move v3, v1

    :goto_1
    if-eqz p3, :cond_6

    move-object/from16 v0, p3

    array-length v1, v0

    if-ge v3, v1, :cond_6

    .line 302
    aget-object v1, p3, v3

    const-string v2, "-p"

    invoke-virtual {v1, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_1

    aget-object v1, p3, v3

    const-string v2, "--print"

    invoke-virtual {v1, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_2

    .line 303
    :cond_1
    const/4 v1, 0x1

    move v2, v1

    move v1, v8

    .line 301
    :goto_2
    add-int/lit8 v3, v3, 0x1

    move v8, v1

    move v9, v2

    goto :goto_1

    .line 290
    :catch_0
    move-exception v1

    .line 291
    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "Can\'t initialize: "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    move-object/from16 v0, p2

    invoke-virtual {v0, v2}, Ljava/io/PrintWriter;->println(Ljava/lang/String;)V

    .line 292
    const-string v2, "DropBoxManagerService"

    const-string v3, "Can\'t init"

    invoke-static {v2, v3, v1}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    goto :goto_0

    .line 281
    :catchall_0
    move-exception v1

    monitor-exit p0

    throw v1

    .line 304
    :cond_2
    :try_start_3
    aget-object v1, p3, v3

    const-string v2, "-f"

    invoke-virtual {v1, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_3

    aget-object v1, p3, v3

    const-string v2, "--file"

    invoke-virtual {v1, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_4

    .line 305
    :cond_3
    const/4 v1, 0x1

    move v2, v9

    goto :goto_2

    .line 306
    :cond_4
    aget-object v1, p3, v3

    const-string v2, "-"

    invoke-virtual {v1, v2}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v1

    if-eqz v1, :cond_5

    .line 307
    const-string v1, "Unknown argument: "

    invoke-virtual {v13, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    aget-object v2, p3, v3

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, "\n"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move v1, v8

    move v2, v9

    goto :goto_2

    .line 309
    :cond_5
    aget-object v1, p3, v3

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    move v1, v8

    move v2, v9

    goto :goto_2

    .line 313
    :cond_6
    const-string v1, "Drop box contents: "

    invoke-virtual {v13, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    move-object/from16 v0, p0

    iget-object v2, v0, Lcom/miui/internal/server/DropBoxManagerService;->Jz:Lcom/miui/internal/server/DropBoxManagerService$b;

    iget-object v2, v2, Lcom/miui/internal/server/DropBoxManagerService$b;->FX:Ljava/util/TreeSet;

    invoke-virtual {v2}, Ljava/util/TreeSet;->size()I

    move-result v2

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, " entries\n"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 315
    invoke-virtual {v14}, Ljava/util/ArrayList;->isEmpty()Z

    move-result v1

    if-nez v1, :cond_8

    .line 316
    const-string v1, "Searching for:"

    invoke-virtual {v13, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 317
    invoke-virtual {v14}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v2

    :goto_3
    invoke-interface {v2}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_7

    invoke-interface {v2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/lang/String;

    const-string v3, " "

    invoke-virtual {v13, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    goto :goto_3

    .line 318
    :cond_7
    const-string v1, "\n"

    invoke-virtual {v13, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 321
    :cond_8
    const/4 v1, 0x0

    invoke-virtual {v14}, Ljava/util/ArrayList;->size()I

    move-result v15

    .line 322
    new-instance v16, Landroid/text/format/Time;

    invoke-direct/range {v16 .. v16}, Landroid/text/format/Time;-><init>()V

    .line 323
    const-string v2, "\n"

    invoke-virtual {v13, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 324
    move-object/from16 v0, p0

    iget-object v2, v0, Lcom/miui/internal/server/DropBoxManagerService;->Jz:Lcom/miui/internal/server/DropBoxManagerService$b;

    iget-object v2, v2, Lcom/miui/internal/server/DropBoxManagerService$b;->FX:Ljava/util/TreeSet;

    invoke-virtual {v2}, Ljava/util/TreeSet;->iterator()Ljava/util/Iterator;

    move-result-object v17

    move v3, v1

    :cond_9
    :goto_4
    invoke-interface/range {v17 .. v17}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_24

    invoke-interface/range {v17 .. v17}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    move-object v0, v1

    check-cast v0, Lcom/miui/internal/server/DropBoxManagerService$a;

    move-object v7, v0

    .line 325
    iget-wide v1, v7, Lcom/miui/internal/server/DropBoxManagerService$a;->Bj:J

    move-object/from16 v0, v16

    invoke-virtual {v0, v1, v2}, Landroid/text/format/Time;->set(J)V

    .line 326
    const-string v1, "%Y-%m-%d %H:%M:%S"

    move-object/from16 v0, v16

    invoke-virtual {v0, v1}, Landroid/text/format/Time;->format(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v4

    .line 327
    const/4 v2, 0x1

    .line 328
    const/4 v1, 0x0

    move/from16 v18, v1

    move v1, v2

    move/from16 v2, v18

    :goto_5
    if-ge v2, v15, :cond_c

    if-eqz v1, :cond_c

    .line 329
    invoke-virtual {v14, v2}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/lang/String;

    .line 330
    invoke-virtual {v4, v1}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v5

    if-nez v5, :cond_a

    iget-object v5, v7, Lcom/miui/internal/server/DropBoxManagerService$a;->tag:Ljava/lang/String;

    invoke-virtual {v1, v5}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_b

    :cond_a
    const/4 v1, 0x1

    .line 328
    :goto_6
    add-int/lit8 v2, v2, 0x1

    goto :goto_5

    .line 330
    :cond_b
    const/4 v1, 0x0

    goto :goto_6

    .line 332
    :cond_c
    if-eqz v1, :cond_9

    .line 334
    add-int/lit8 v12, v3, 0x1

    .line 335
    if-eqz v9, :cond_d

    const-string v1, "========================================\n"

    invoke-virtual {v13, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 336
    :cond_d
    invoke-virtual {v13, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, " "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    iget-object v1, v7, Lcom/miui/internal/server/DropBoxManagerService$a;->tag:Ljava/lang/String;

    if-nez v1, :cond_e

    const-string v1, "(no tag)"

    :goto_7
    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 337
    iget-object v1, v7, Lcom/miui/internal/server/DropBoxManagerService$a;->file:Ljava/io/File;

    if-nez v1, :cond_f

    .line 338
    const-string v1, " (no file)\n"

    invoke-virtual {v13, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move v3, v12

    .line 339
    goto :goto_4

    .line 336
    :cond_e
    iget-object v1, v7, Lcom/miui/internal/server/DropBoxManagerService$a;->tag:Ljava/lang/String;

    goto :goto_7

    .line 340
    :cond_f
    iget v1, v7, Lcom/miui/internal/server/DropBoxManagerService$a;->flags:I

    and-int/lit8 v1, v1, 0x1

    if-eqz v1, :cond_10

    .line 341
    const-string v1, " (contents lost)\n"

    invoke-virtual {v13, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move v3, v12

    .line 342
    goto :goto_4

    .line 344
    :cond_10
    const-string v1, " ("

    invoke-virtual {v13, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 345
    iget v1, v7, Lcom/miui/internal/server/DropBoxManagerService$a;->flags:I

    and-int/lit8 v1, v1, 0x4

    if-eqz v1, :cond_11

    const-string v1, "compressed "

    invoke-virtual {v13, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 346
    :cond_11
    iget v1, v7, Lcom/miui/internal/server/DropBoxManagerService$a;->flags:I

    and-int/lit8 v1, v1, 0x2

    if-eqz v1, :cond_1b

    const-string v1, "text"

    :goto_8
    invoke-virtual {v13, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 347
    const-string v1, ", "

    invoke-virtual {v13, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    iget-object v2, v7, Lcom/miui/internal/server/DropBoxManagerService$a;->file:Ljava/io/File;

    invoke-virtual {v2}, Ljava/io/File;->length()J

    move-result-wide v2

    invoke-virtual {v1, v2, v3}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, " bytes)\n"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 350
    if-nez v8, :cond_12

    if-eqz v9, :cond_14

    iget v1, v7, Lcom/miui/internal/server/DropBoxManagerService$a;->flags:I

    and-int/lit8 v1, v1, 0x2

    if-nez v1, :cond_14

    .line 351
    :cond_12
    if-nez v9, :cond_13

    const-string v1, "    "

    invoke-virtual {v13, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 352
    :cond_13
    iget-object v1, v7, Lcom/miui/internal/server/DropBoxManagerService$a;->file:Ljava/io/File;

    invoke-virtual {v1}, Ljava/io/File;->getPath()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v13, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, "\n"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 355
    :cond_14
    iget v1, v7, Lcom/miui/internal/server/DropBoxManagerService$a;->flags:I
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_0

    and-int/lit8 v1, v1, 0x2

    if-eqz v1, :cond_19

    if-nez v9, :cond_15

    if-nez v8, :cond_19

    .line 356
    :cond_15
    const/4 v11, 0x0

    .line 357
    const/4 v10, 0x0

    .line 359
    :try_start_4
    new-instance v1, Lmiui/os/DropBoxManager$Entry;

    iget-object v2, v7, Lcom/miui/internal/server/DropBoxManagerService$a;->tag:Ljava/lang/String;

    iget-wide v3, v7, Lcom/miui/internal/server/DropBoxManagerService$a;->Bj:J

    iget-object v5, v7, Lcom/miui/internal/server/DropBoxManagerService$a;->file:Ljava/io/File;

    iget v6, v7, Lcom/miui/internal/server/DropBoxManagerService$a;->flags:I

    invoke-direct/range {v1 .. v6}, Lmiui/os/DropBoxManager$Entry;-><init>(Ljava/lang/String;JLjava/io/File;I)V
    :try_end_4
    .catch Ljava/io/IOException; {:try_start_4 .. :try_end_4} :catch_5
    .catchall {:try_start_4 .. :try_end_4} :catchall_1

    .line 362
    if-eqz v9, :cond_1f

    .line 363
    :try_start_5
    new-instance v3, Ljava/io/InputStreamReader;

    invoke-virtual {v1}, Lmiui/os/DropBoxManager$Entry;->getInputStream()Ljava/io/InputStream;

    move-result-object v2

    invoke-direct {v3, v2}, Ljava/io/InputStreamReader;-><init>(Ljava/io/InputStream;)V
    :try_end_5
    .catch Ljava/io/IOException; {:try_start_5 .. :try_end_5} :catch_6
    .catchall {:try_start_5 .. :try_end_5} :catchall_2

    .line 364
    const/16 v2, 0x1000

    :try_start_6
    new-array v4, v2, [C

    .line 365
    const/4 v2, 0x0

    .line 367
    :cond_16
    :goto_9
    invoke-virtual {v3, v4}, Ljava/io/InputStreamReader;->read([C)I

    move-result v5

    .line 368
    if-gtz v5, :cond_1c

    .line 378
    if-nez v2, :cond_17

    const-string v2, "\n"

    invoke-virtual {v13, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;
    :try_end_6
    .catch Ljava/io/IOException; {:try_start_6 .. :try_end_6} :catch_1
    .catchall {:try_start_6 .. :try_end_6} :catchall_3

    .line 390
    :cond_17
    :goto_a
    if-eqz v1, :cond_18

    :try_start_7
    invoke-virtual {v1}, Lmiui/os/DropBoxManager$Entry;->close()V
    :try_end_7
    .catchall {:try_start_7 .. :try_end_7} :catchall_0

    .line 391
    :cond_18
    if-eqz v3, :cond_19

    .line 393
    :try_start_8
    invoke-virtual {v3}, Ljava/io/InputStreamReader;->close()V
    :try_end_8
    .catch Ljava/io/IOException; {:try_start_8 .. :try_end_8} :catch_3
    .catchall {:try_start_8 .. :try_end_8} :catchall_0

    .line 400
    :cond_19
    :goto_b
    if-eqz v9, :cond_1a

    :try_start_9
    const-string v1, "\n"

    invoke-virtual {v13, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    :cond_1a
    move v3, v12

    .line 401
    goto/16 :goto_4

    .line 346
    :cond_1b
    const-string v1, "data"
    :try_end_9
    .catchall {:try_start_9 .. :try_end_9} :catchall_0

    goto/16 :goto_8

    .line 369
    :cond_1c
    const/4 v2, 0x0

    :try_start_a
    invoke-virtual {v13, v4, v2, v5}, Ljava/lang/StringBuilder;->append([CII)Ljava/lang/StringBuilder;

    .line 370
    add-int/lit8 v2, v5, -0x1

    aget-char v2, v4, v2

    const/16 v5, 0xa

    if-ne v2, v5, :cond_1e

    const/4 v2, 0x1

    .line 373
    :goto_c
    invoke-virtual {v13}, Ljava/lang/StringBuilder;->length()I

    move-result v5

    const/high16 v6, 0x10000

    if-le v5, v6, :cond_16

    .line 374
    invoke-virtual {v13}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v5

    move-object/from16 v0, p2

    invoke-virtual {v0, v5}, Ljava/io/PrintWriter;->write(Ljava/lang/String;)V

    .line 375
    const/4 v5, 0x0

    invoke-virtual {v13, v5}, Ljava/lang/StringBuilder;->setLength(I)V
    :try_end_a
    .catch Ljava/io/IOException; {:try_start_a .. :try_end_a} :catch_1
    .catchall {:try_start_a .. :try_end_a} :catchall_3

    goto :goto_9

    .line 386
    :catch_1
    move-exception v2

    move-object/from16 v18, v2

    move-object v2, v3

    move-object v3, v1

    move-object/from16 v1, v18

    .line 387
    :goto_d
    :try_start_b
    const-string v4, "*** "

    invoke-virtual {v13, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v1}, Ljava/io/IOException;->toString()Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    const-string v5, "\n"

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 388
    const-string v4, "DropBoxManagerService"

    new-instance v5, Ljava/lang/StringBuilder;

    invoke-direct {v5}, Ljava/lang/StringBuilder;-><init>()V

    const-string v6, "Can\'t read: "

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v5

    iget-object v6, v7, Lcom/miui/internal/server/DropBoxManagerService$a;->file:Ljava/io/File;

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v5

    invoke-virtual {v5}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v5

    invoke-static {v4, v5, v1}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    :try_end_b
    .catchall {:try_start_b .. :try_end_b} :catchall_4

    .line 390
    if-eqz v3, :cond_1d

    :try_start_c
    invoke-virtual {v3}, Lmiui/os/DropBoxManager$Entry;->close()V
    :try_end_c
    .catchall {:try_start_c .. :try_end_c} :catchall_0

    .line 391
    :cond_1d
    if-eqz v2, :cond_19

    .line 393
    :try_start_d
    invoke-virtual {v2}, Ljava/io/InputStreamReader;->close()V
    :try_end_d
    .catch Ljava/io/IOException; {:try_start_d .. :try_end_d} :catch_2
    .catchall {:try_start_d .. :try_end_d} :catchall_0

    goto :goto_b

    .line 394
    :catch_2
    move-exception v1

    goto :goto_b

    .line 370
    :cond_1e
    const/4 v2, 0x0

    goto :goto_c

    .line 380
    :cond_1f
    const/16 v2, 0x46

    :try_start_e
    invoke-virtual {v1, v2}, Lmiui/os/DropBoxManager$Entry;->getText(I)Ljava/lang/String;

    move-result-object v3

    .line 381
    invoke-virtual {v3}, Ljava/lang/String;->length()I

    move-result v2

    const/16 v4, 0x46

    if-ne v2, v4, :cond_21

    const/4 v2, 0x1

    .line 382
    :goto_e
    const-string v4, "    "

    invoke-virtual {v13, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v3}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v3

    const/16 v5, 0xa

    const/16 v6, 0x2f

    invoke-virtual {v3, v5, v6}, Ljava/lang/String;->replace(CC)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v4, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 383
    if-eqz v2, :cond_20

    const-string v2, " ..."

    invoke-virtual {v13, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 384
    :cond_20
    const-string v2, "\n"

    invoke-virtual {v13, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;
    :try_end_e
    .catch Ljava/io/IOException; {:try_start_e .. :try_end_e} :catch_6
    .catchall {:try_start_e .. :try_end_e} :catchall_2

    move-object v3, v10

    goto/16 :goto_a

    .line 381
    :cond_21
    const/4 v2, 0x0

    goto :goto_e

    .line 390
    :catchall_1
    move-exception v1

    move-object v3, v11

    :goto_f
    if-eqz v3, :cond_22

    :try_start_f
    invoke-virtual {v3}, Lmiui/os/DropBoxManager$Entry;->close()V
    :try_end_f
    .catchall {:try_start_f .. :try_end_f} :catchall_0

    .line 391
    :cond_22
    if-eqz v10, :cond_23

    .line 393
    :try_start_10
    invoke-virtual {v10}, Ljava/io/InputStreamReader;->close()V
    :try_end_10
    .catch Ljava/io/IOException; {:try_start_10 .. :try_end_10} :catch_4
    .catchall {:try_start_10 .. :try_end_10} :catchall_0

    .line 395
    :cond_23
    :goto_10
    :try_start_11
    throw v1

    .line 403
    :cond_24
    if-nez v3, :cond_25

    const-string v1, "(No entries found.)\n"

    invoke-virtual {v13, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 405
    :cond_25
    if-eqz p3, :cond_26

    move-object/from16 v0, p3

    array-length v1, v0

    if-nez v1, :cond_28

    .line 406
    :cond_26
    if-nez v9, :cond_27

    const-string v1, "\n"

    invoke-virtual {v13, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 407
    :cond_27
    const-string v1, "Usage: dumpsys dropbox [--print|--file] [YYYY-mm-dd] [HH:MM:SS] [tag]\n"

    invoke-virtual {v13, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 410
    :cond_28
    invoke-virtual {v13}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    move-object/from16 v0, p2

    invoke-virtual {v0, v1}, Ljava/io/PrintWriter;->write(Ljava/lang/String;)V
    :try_end_11
    .catchall {:try_start_11 .. :try_end_11} :catchall_0

    goto/16 :goto_0

    .line 394
    :catch_3
    move-exception v1

    goto/16 :goto_b

    :catch_4
    move-exception v2

    goto :goto_10

    .line 390
    :catchall_2
    move-exception v2

    move-object v3, v1

    move-object v1, v2

    goto :goto_f

    :catchall_3
    move-exception v2

    move-object v10, v3

    move-object v3, v1

    move-object v1, v2

    goto :goto_f

    :catchall_4
    move-exception v1

    move-object v10, v2

    goto :goto_f

    .line 386
    :catch_5
    move-exception v1

    move-object v2, v10

    move-object v3, v11

    goto/16 :goto_d

    :catch_6
    move-exception v2

    move-object v3, v1

    move-object v1, v2

    move-object v2, v10

    goto/16 :goto_d
.end method

.method public declared-synchronized getNextEntry(Ljava/lang/String;J)Lmiui/os/DropBoxManager$Entry;
    .locals 10

    .prologue
    const/4 v8, 0x0

    .line 247
    monitor-enter p0

    :try_start_0
    iget-object v1, p0, Lcom/miui/internal/server/DropBoxManagerService;->mContext:Landroid/content/Context;

    const-string v2, "miui.permission.READ_LOGS"

    invoke-virtual {v1, v2}, Landroid/content/Context;->checkCallingOrSelfPermission(Ljava/lang/String;)I

    move-result v1

    if-eqz v1, :cond_0

    .line 250
    new-instance v1, Ljava/lang/SecurityException;

    const-string v2, "READ_LOGS permission required"

    invoke-direct {v1, v2}, Ljava/lang/SecurityException;-><init>(Ljava/lang/String;)V

    throw v1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 247
    :catchall_0
    move-exception v1

    monitor-exit p0

    throw v1

    .line 254
    :cond_0
    :try_start_1
    invoke-direct {p0}, Lcom/miui/internal/server/DropBoxManagerService;->init()V
    :try_end_1
    .catch Ljava/io/IOException; {:try_start_1 .. :try_end_1} :catch_0
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 260
    if-nez p1, :cond_1

    :try_start_2
    iget-object v1, p0, Lcom/miui/internal/server/DropBoxManagerService;->Jz:Lcom/miui/internal/server/DropBoxManagerService$b;
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    .line 261
    :goto_0
    if-nez v1, :cond_2

    move-object v1, v8

    .line 277
    :goto_1
    monitor-exit p0

    return-object v1

    .line 255
    :catch_0
    move-exception v1

    .line 256
    :try_start_3
    const-string v2, "DropBoxManagerService"

    const-string v3, "Can\'t init"

    invoke-static {v2, v3, v1}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    move-object v1, v8

    .line 257
    goto :goto_1

    .line 260
    :cond_1
    iget-object v1, p0, Lcom/miui/internal/server/DropBoxManagerService;->JA:Ljava/util/HashMap;

    invoke-virtual {v1, p1}, Ljava/util/HashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/miui/internal/server/DropBoxManagerService$b;

    goto :goto_0

    .line 263
    :cond_2
    iget-object v1, v1, Lcom/miui/internal/server/DropBoxManagerService$b;->FX:Ljava/util/TreeSet;

    new-instance v2, Lcom/miui/internal/server/DropBoxManagerService$a;

    const-wide/16 v3, 0x1

    add-long/2addr v3, p2

    invoke-direct {v2, v3, v4}, Lcom/miui/internal/server/DropBoxManagerService$a;-><init>(J)V

    invoke-virtual {v1, v2}, Ljava/util/TreeSet;->tailSet(Ljava/lang/Object;)Ljava/util/SortedSet;

    move-result-object v1

    invoke-interface {v1}, Ljava/util/SortedSet;->iterator()Ljava/util/Iterator;

    move-result-object v9

    :cond_3
    :goto_2
    invoke-interface {v9}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_5

    invoke-interface {v9}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    move-object v0, v1

    check-cast v0, Lcom/miui/internal/server/DropBoxManagerService$a;

    move-object v7, v0

    .line 264
    iget-object v1, v7, Lcom/miui/internal/server/DropBoxManagerService$a;->tag:Ljava/lang/String;

    if-eqz v1, :cond_3

    .line 265
    iget v1, v7, Lcom/miui/internal/server/DropBoxManagerService$a;->flags:I

    and-int/lit8 v1, v1, 0x1

    if-eqz v1, :cond_4

    .line 266
    new-instance v1, Lmiui/os/DropBoxManager$Entry;

    iget-object v2, v7, Lcom/miui/internal/server/DropBoxManagerService$a;->tag:Ljava/lang/String;

    iget-wide v3, v7, Lcom/miui/internal/server/DropBoxManagerService$a;->Bj:J

    invoke-direct {v1, v2, v3, v4}, Lmiui/os/DropBoxManager$Entry;-><init>(Ljava/lang/String;J)V
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_0

    goto :goto_1

    .line 269
    :cond_4
    :try_start_4
    new-instance v1, Lmiui/os/DropBoxManager$Entry;

    iget-object v2, v7, Lcom/miui/internal/server/DropBoxManagerService$a;->tag:Ljava/lang/String;

    iget-wide v3, v7, Lcom/miui/internal/server/DropBoxManagerService$a;->Bj:J

    iget-object v5, v7, Lcom/miui/internal/server/DropBoxManagerService$a;->file:Ljava/io/File;

    iget v6, v7, Lcom/miui/internal/server/DropBoxManagerService$a;->flags:I

    invoke-direct/range {v1 .. v6}, Lmiui/os/DropBoxManager$Entry;-><init>(Ljava/lang/String;JLjava/io/File;I)V
    :try_end_4
    .catch Ljava/io/IOException; {:try_start_4 .. :try_end_4} :catch_1
    .catchall {:try_start_4 .. :try_end_4} :catchall_0

    goto :goto_1

    .line 271
    :catch_1
    move-exception v1

    .line 272
    :try_start_5
    const-string v2, "DropBoxManagerService"

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    const-string v4, "Can\'t read: "

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    iget-object v4, v7, Lcom/miui/internal/server/DropBoxManagerService$a;->file:Ljava/io/File;

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-static {v2, v3, v1}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    :try_end_5
    .catchall {:try_start_5 .. :try_end_5} :catchall_0

    goto :goto_2

    :cond_5
    move-object v1, v8

    .line 277
    goto :goto_1
.end method

.method public isTagEnabled(Ljava/lang/String;)Z
    .locals 5

    .prologue
    .line 237
    invoke-static {}, Landroid/os/Binder;->clearCallingIdentity()J

    move-result-wide v1

    .line 239
    :try_start_0
    const-string v0, "disabled"

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    const-string v4, "dropbox:"

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v0, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    move-result v0

    if-nez v0, :cond_0

    const/4 v0, 0x1

    .line 241
    :goto_0
    invoke-static {v1, v2}, Landroid/os/Binder;->restoreCallingIdentity(J)V

    return v0

    .line 239
    :cond_0
    const/4 v0, 0x0

    goto :goto_0

    .line 241
    :catchall_0
    move-exception v0

    invoke-static {v1, v2}, Landroid/os/Binder;->restoreCallingIdentity(J)V

    throw v0
.end method
