.class public Lmiui/util/async/tasks/DatabaseBitmapTask;
.super Lmiui/util/async/tasks/ContentResolverQueryTask;
.source "SourceFile"

# interfaces
.implements Lmiui/util/async/Cacheable;


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lmiui/util/async/tasks/ContentResolverQueryTask",
        "<",
        "Landroid/graphics/Bitmap;",
        ">;",
        "Lmiui/util/async/Cacheable;"
    }
.end annotation


# instance fields
.field private oo:Landroid/graphics/BitmapFactory$Options;


# direct methods
.method public constructor <init>(Landroid/net/Uri;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)V
    .locals 6

    .prologue
    .line 35
    const/4 v5, 0x0

    move-object v0, p0

    move-object v1, p1

    move-object v2, p2

    move-object v3, p3

    move-object v4, p4

    invoke-direct/range {v0 .. v5}, Lmiui/util/async/tasks/DatabaseBitmapTask;-><init>(Landroid/net/Uri;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Landroid/graphics/BitmapFactory$Options;)V

    .line 36
    return-void
.end method

.method public constructor <init>(Landroid/net/Uri;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Landroid/graphics/BitmapFactory$Options;)V
    .locals 6

    .prologue
    const/4 v5, 0x0

    .line 51
    if-nez p2, :cond_0

    move-object v2, v5

    :goto_0
    move-object v0, p0

    move-object v1, p1

    move-object v3, p3

    move-object v4, p4

    invoke-direct/range {v0 .. v5}, Lmiui/util/async/tasks/ContentResolverQueryTask;-><init>(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)V

    .line 52
    iput-object p5, p0, Lmiui/util/async/tasks/DatabaseBitmapTask;->oo:Landroid/graphics/BitmapFactory$Options;

    .line 53
    return-void

    .line 51
    :cond_0
    const/4 v0, 0x1

    new-array v2, v0, [Ljava/lang/String;

    const/4 v0, 0x0

    aput-object p2, v2, v0

    goto :goto_0
.end method


# virtual methods
.method public doLoad()Landroid/graphics/Bitmap;
    .locals 5
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/Exception;
        }
    .end annotation

    .prologue
    const/4 v0, 0x0

    .line 61
    invoke-virtual {p0}, Lmiui/util/async/tasks/DatabaseBitmapTask;->query()Landroid/database/Cursor;

    move-result-object v1

    .line 63
    if-eqz v1, :cond_0

    :try_start_0
    invoke-interface {v1}, Landroid/database/Cursor;->moveToNext()Z
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_1

    move-result v2

    if-nez v2, :cond_2

    .line 77
    :cond_0
    if-eqz v1, :cond_1

    .line 78
    invoke-interface {v1}, Landroid/database/Cursor;->close()V

    :cond_1
    :goto_0
    return-object v0

    .line 66
    :cond_2
    const/4 v2, 0x0

    :try_start_1
    invoke-interface {v1, v2}, Landroid/database/Cursor;->getBlob(I)[B
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_1

    move-result-object v2

    .line 67
    if-nez v2, :cond_3

    .line 77
    if-eqz v1, :cond_1

    .line 78
    invoke-interface {v1}, Landroid/database/Cursor;->close()V

    goto :goto_0

    .line 70
    :cond_3
    :try_start_2
    sget-object v0, Lmiui/util/async/tasks/FileBitmapTask;->om:Ljava/util/concurrent/Semaphore;

    invoke-virtual {v0}, Ljava/util/concurrent/Semaphore;->acquireUninterruptibly()V
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_1

    .line 72
    const/4 v0, 0x0

    :try_start_3
    array-length v3, v2

    iget-object v4, p0, Lmiui/util/async/tasks/DatabaseBitmapTask;->oo:Landroid/graphics/BitmapFactory$Options;

    invoke-static {v2, v0, v3, v4}, Lmiui/graphics/BitmapFactory;->decodeByteArray([BIILandroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_0

    move-result-object v0

    .line 74
    :try_start_4
    sget-object v2, Lmiui/util/async/tasks/FileBitmapTask;->om:Ljava/util/concurrent/Semaphore;

    invoke-virtual {v2}, Ljava/util/concurrent/Semaphore;->release()V
    :try_end_4
    .catchall {:try_start_4 .. :try_end_4} :catchall_1

    .line 77
    if-eqz v1, :cond_1

    .line 78
    invoke-interface {v1}, Landroid/database/Cursor;->close()V

    goto :goto_0

    .line 74
    :catchall_0
    move-exception v0

    :try_start_5
    sget-object v2, Lmiui/util/async/tasks/FileBitmapTask;->om:Ljava/util/concurrent/Semaphore;

    invoke-virtual {v2}, Ljava/util/concurrent/Semaphore;->release()V

    throw v0
    :try_end_5
    .catchall {:try_start_5 .. :try_end_5} :catchall_1

    .line 77
    :catchall_1
    move-exception v0

    if-eqz v1, :cond_4

    .line 78
    invoke-interface {v1}, Landroid/database/Cursor;->close()V

    :cond_4
    throw v0
.end method

.method public bridge synthetic doLoad()Ljava/lang/Object;
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/Exception;
        }
    .end annotation

    .prologue
    .line 17
    invoke-virtual {p0}, Lmiui/util/async/tasks/DatabaseBitmapTask;->doLoad()Landroid/graphics/Bitmap;

    move-result-object v0

    return-object v0
.end method

.method public getCacheKey()Ljava/lang/String;
    .locals 1

    .prologue
    .line 85
    invoke-virtual {p0}, Lmiui/util/async/tasks/DatabaseBitmapTask;->getDescription()Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method public sizeOf(Ljava/lang/Object;)I
    .locals 1

    .prologue
    .line 90
    instance-of v0, p1, Landroid/graphics/Bitmap;

    if-eqz v0, :cond_0

    .line 91
    check-cast p1, Landroid/graphics/Bitmap;

    invoke-virtual {p1}, Landroid/graphics/Bitmap;->getByteCount()I

    move-result v0

    .line 93
    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method
