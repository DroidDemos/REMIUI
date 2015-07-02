.class public Lmiui/util/async/tasks/ContentResolverInsertTask;
.super Lmiui/util/async/Task;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lmiui/util/async/Task",
        "<",
        "Landroid/net/Uri;",
        ">;"
    }
.end annotation


# instance fields
.field private final wQ:Landroid/net/Uri;

.field private wT:Ljava/lang/String;

.field private final xO:Landroid/content/ContentValues;


# direct methods
.method public constructor <init>(Landroid/net/Uri;Landroid/content/ContentValues;)V
    .locals 0

    .prologue
    .line 42
    invoke-direct {p0}, Lmiui/util/async/Task;-><init>()V

    .line 43
    iput-object p1, p0, Lmiui/util/async/tasks/ContentResolverInsertTask;->wQ:Landroid/net/Uri;

    .line 44
    iput-object p2, p0, Lmiui/util/async/tasks/ContentResolverInsertTask;->xO:Landroid/content/ContentValues;

    .line 45
    return-void
.end method


# virtual methods
.method public doLoad()Landroid/net/Uri;
    .locals 3
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/Exception;
        }
    .end annotation

    .prologue
    .line 63
    invoke-static {}, Lcom/miui/internal/util/PackageConstants;->getCurrentApplication()Landroid/app/Application;

    move-result-object v0

    invoke-virtual {v0}, Landroid/app/Application;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object v0

    iget-object v1, p0, Lmiui/util/async/tasks/ContentResolverInsertTask;->wQ:Landroid/net/Uri;

    iget-object v2, p0, Lmiui/util/async/tasks/ContentResolverInsertTask;->xO:Landroid/content/ContentValues;

    invoke-virtual {v0, v1, v2}, Landroid/content/ContentResolver;->insert(Landroid/net/Uri;Landroid/content/ContentValues;)Landroid/net/Uri;

    move-result-object v0

    return-object v0
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
    invoke-virtual {p0}, Lmiui/util/async/tasks/ContentResolverInsertTask;->doLoad()Landroid/net/Uri;

    move-result-object v0

    return-object v0
.end method

.method public getDescription()Ljava/lang/String;
    .locals 2

    .prologue
    .line 49
    iget-object v0, p0, Lmiui/util/async/tasks/ContentResolverInsertTask;->wT:Ljava/lang/String;

    if-nez v0, :cond_0

    .line 50
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    iget-object v1, p0, Lmiui/util/async/tasks/ContentResolverInsertTask;->xO:Landroid/content/ContentValues;

    invoke-virtual {v1}, Landroid/content/ContentValues;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    const/16 v1, 0x40

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    move-result-object v0

    iget-object v1, p0, Lmiui/util/async/tasks/ContentResolverInsertTask;->wQ:Landroid/net/Uri;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lmiui/util/async/tasks/ContentResolverInsertTask;->wT:Ljava/lang/String;

    .line 52
    :cond_0
    iget-object v0, p0, Lmiui/util/async/tasks/ContentResolverInsertTask;->wT:Ljava/lang/String;

    return-object v0
.end method
