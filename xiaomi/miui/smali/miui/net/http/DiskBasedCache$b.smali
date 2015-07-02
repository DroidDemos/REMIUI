.class Lmiui/net/http/DiskBasedCache$b;
.super Ljava/io/FilterInputStream;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/net/http/DiskBasedCache;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0xa
    name = "b"
.end annotation


# instance fields
.field private JS:Lmiui/net/http/DiskBasedCache$a;


# direct methods
.method protected constructor <init>(Lmiui/net/http/DiskBasedCache$a;)V
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 666
    invoke-static {p1}, Lmiui/net/http/DiskBasedCache$b;->c(Lmiui/net/http/DiskBasedCache$a;)Ljava/io/InputStream;

    move-result-object v0

    invoke-direct {p0, v0}, Ljava/io/FilterInputStream;-><init>(Ljava/io/InputStream;)V

    .line 667
    iget-object v0, p0, Lmiui/net/http/DiskBasedCache$b;->in:Ljava/io/InputStream;

    if-eqz v0, :cond_0

    .line 668
    iput-object p1, p0, Lmiui/net/http/DiskBasedCache$b;->JS:Lmiui/net/http/DiskBasedCache$a;

    .line 670
    :cond_0
    return-void
.end method

.method private static c(Lmiui/net/http/DiskBasedCache$a;)Ljava/io/InputStream;
    .locals 5
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 673
    invoke-virtual {p0}, Lmiui/net/http/DiskBasedCache$a;->acquire()V

    .line 676
    :try_start_0
    new-instance v0, Ljava/io/FileInputStream;

    iget-object v1, p0, Lmiui/net/http/DiskBasedCache$a;->file:Ljava/io/File;

    invoke-direct {v0, v1}, Ljava/io/FileInputStream;-><init>(Ljava/io/File;)V

    .line 677
    iget-wide v1, p0, Lmiui/net/http/DiskBasedCache$a;->FH:J

    invoke-virtual {v0, v1, v2}, Ljava/io/InputStream;->skip(J)J

    move-result-wide v1

    iget-wide v3, p0, Lmiui/net/http/DiskBasedCache$a;->FH:J

    cmp-long v1, v1, v3

    if-eqz v1, :cond_0

    .line 678
    new-instance v0, Ljava/io/IOException;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "skip failed for file "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    iget-object v2, p0, Lmiui/net/http/DiskBasedCache$a;->file:Ljava/io/File;

    invoke-virtual {v2}, Ljava/io/File;->getName()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/io/IOException;-><init>(Ljava/lang/String;)V

    throw v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 683
    :catchall_0
    move-exception v0

    .line 684
    invoke-virtual {p0}, Lmiui/net/http/DiskBasedCache$a;->release()V

    throw v0

    :cond_0
    return-object v0
.end method


# virtual methods
.method public close()V
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 691
    iget-object v0, p0, Lmiui/net/http/DiskBasedCache$b;->JS:Lmiui/net/http/DiskBasedCache$a;

    invoke-virtual {v0}, Lmiui/net/http/DiskBasedCache$a;->release()V

    .line 692
    invoke-super {p0}, Ljava/io/FilterInputStream;->close()V

    .line 693
    return-void
.end method
