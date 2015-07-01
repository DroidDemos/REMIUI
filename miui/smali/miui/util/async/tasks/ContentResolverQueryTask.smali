.class public abstract Lmiui/util/async/tasks/ContentResolverQueryTask;
.super Lmiui/util/async/Task;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lmiui/util/async/tasks/ContentResolverQueryTask$a;,
        Lmiui/util/async/tasks/ContentResolverQueryTask$Cookie;
    }
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "<T:",
        "Ljava/lang/Object;",
        ">",
        "Lmiui/util/async/Task",
        "<TT;>;"
    }
.end annotation


# instance fields
.field private final Ir:[Ljava/lang/String;

.field private final Is:Ljava/lang/String;

.field private final It:Ljava/lang/String;

.field private Iu:Z

.field private Iv:Z

.field private Iw:Z

.field private Ix:Lmiui/util/async/tasks/ContentResolverQueryTask$a;

.field private jw:Ljava/lang/ref/WeakReference;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/lang/ref/WeakReference",
            "<",
            "Lmiui/util/async/TaskManager;",
            ">;"
        }
    .end annotation
.end field

.field private final wQ:Landroid/net/Uri;

.field private final wS:[Ljava/lang/String;

.field private wT:Ljava/lang/String;


# direct methods
.method public constructor <init>(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)V
    .locals 0

    .prologue
    .line 131
    invoke-direct {p0}, Lmiui/util/async/Task;-><init>()V

    .line 132
    iput-object p1, p0, Lmiui/util/async/tasks/ContentResolverQueryTask;->wQ:Landroid/net/Uri;

    .line 133
    iput-object p2, p0, Lmiui/util/async/tasks/ContentResolverQueryTask;->Ir:[Ljava/lang/String;

    .line 134
    iput-object p3, p0, Lmiui/util/async/tasks/ContentResolverQueryTask;->Is:Ljava/lang/String;

    .line 135
    iput-object p4, p0, Lmiui/util/async/tasks/ContentResolverQueryTask;->wS:[Ljava/lang/String;

    .line 136
    iput-object p5, p0, Lmiui/util/async/tasks/ContentResolverQueryTask;->It:Ljava/lang/String;

    .line 137
    return-void
.end method


# virtual methods
.method public disableAutoRequery()V
    .locals 2

    .prologue
    .line 158
    const/4 v0, 0x0

    iput-boolean v0, p0, Lmiui/util/async/tasks/ContentResolverQueryTask;->Iu:Z

    .line 159
    iget-object v0, p0, Lmiui/util/async/tasks/ContentResolverQueryTask;->Ix:Lmiui/util/async/tasks/ContentResolverQueryTask$a;

    if-eqz v0, :cond_0

    .line 160
    invoke-static {}, Lcom/miui/internal/util/PackageConstants;->getCurrentApplication()Landroid/app/Application;

    move-result-object v0

    invoke-virtual {v0}, Landroid/app/Application;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object v0

    iget-object v1, p0, Lmiui/util/async/tasks/ContentResolverQueryTask;->Ix:Lmiui/util/async/tasks/ContentResolverQueryTask$a;

    invoke-virtual {v0, v1}, Landroid/content/ContentResolver;->unregisterContentObserver(Landroid/database/ContentObserver;)V

    .line 162
    const/4 v0, 0x0

    iput-object v0, p0, Lmiui/util/async/tasks/ContentResolverQueryTask;->Ix:Lmiui/util/async/tasks/ContentResolverQueryTask$a;

    .line 164
    :cond_0
    return-void
.end method

.method public enableAutoRequery()Lmiui/util/async/tasks/ContentResolverQueryTask$Cookie;
    .locals 4
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Lmiui/util/async/tasks/ContentResolverQueryTask",
            "<TT;>.Cookie;"
        }
    .end annotation

    .prologue
    const/4 v3, 0x1

    .line 145
    iget-boolean v0, p0, Lmiui/util/async/tasks/ContentResolverQueryTask;->Iu:Z

    if-nez v0, :cond_0

    .line 146
    iput-boolean v3, p0, Lmiui/util/async/tasks/ContentResolverQueryTask;->Iu:Z

    .line 147
    new-instance v0, Lmiui/util/async/tasks/ContentResolverQueryTask$a;

    invoke-direct {v0, p0}, Lmiui/util/async/tasks/ContentResolverQueryTask$a;-><init>(Lmiui/util/async/tasks/ContentResolverQueryTask;)V

    iput-object v0, p0, Lmiui/util/async/tasks/ContentResolverQueryTask;->Ix:Lmiui/util/async/tasks/ContentResolverQueryTask$a;

    .line 148
    invoke-static {}, Lcom/miui/internal/util/PackageConstants;->getCurrentApplication()Landroid/app/Application;

    move-result-object v0

    invoke-virtual {v0}, Landroid/app/Application;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object v0

    iget-object v1, p0, Lmiui/util/async/tasks/ContentResolverQueryTask;->wQ:Landroid/net/Uri;

    iget-object v2, p0, Lmiui/util/async/tasks/ContentResolverQueryTask;->Ix:Lmiui/util/async/tasks/ContentResolverQueryTask$a;

    invoke-virtual {v0, v1, v3, v2}, Landroid/content/ContentResolver;->registerContentObserver(Landroid/net/Uri;ZLandroid/database/ContentObserver;)V

    .line 151
    :cond_0
    new-instance v0, Lmiui/util/async/tasks/ContentResolverQueryTask$Cookie;

    invoke-direct {v0, p0}, Lmiui/util/async/tasks/ContentResolverQueryTask$Cookie;-><init>(Lmiui/util/async/tasks/ContentResolverQueryTask;)V

    return-object v0
.end method

.method public getDescription()Ljava/lang/String;
    .locals 7

    .prologue
    const/16 v6, 0x5b

    const/16 v5, 0x3b

    const/4 v2, 0x1

    const/4 v4, 0x0

    .line 168
    iget-object v0, p0, Lmiui/util/async/tasks/ContentResolverQueryTask;->wT:Ljava/lang/String;

    if-nez v0, :cond_6

    .line 169
    invoke-static {}, Lmiui/util/Pools;->getStringBuilderPool()Lmiui/util/Pools$Pool;

    move-result-object v0

    invoke-interface {v0}, Lmiui/util/Pools$Pool;->acquire()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/StringBuilder;

    .line 170
    iget-object v1, p0, Lmiui/util/async/tasks/ContentResolverQueryTask;->Ir:[Ljava/lang/String;

    if-eqz v1, :cond_1

    .line 171
    invoke-virtual {v0, v6}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    .line 172
    iget-object v1, p0, Lmiui/util/async/tasks/ContentResolverQueryTask;->Ir:[Ljava/lang/String;

    aget-object v1, v1, v4

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move v1, v2

    .line 173
    :goto_0
    iget-object v3, p0, Lmiui/util/async/tasks/ContentResolverQueryTask;->Ir:[Ljava/lang/String;

    array-length v3, v3

    if-ge v1, v3, :cond_0

    .line 174
    invoke-virtual {v0, v5}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    .line 175
    iget-object v3, p0, Lmiui/util/async/tasks/ContentResolverQueryTask;->Ir:[Ljava/lang/String;

    aget-object v3, v3, v1

    invoke-virtual {v0, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 173
    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    .line 177
    :cond_0
    const-string v1, "]@"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 179
    :cond_1
    iget-object v1, p0, Lmiui/util/async/tasks/ContentResolverQueryTask;->wQ:Landroid/net/Uri;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    .line 180
    iget-object v1, p0, Lmiui/util/async/tasks/ContentResolverQueryTask;->Is:Ljava/lang/String;

    if-eqz v1, :cond_4

    .line 181
    const-string v1, " WHERE "

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 182
    iget-object v1, p0, Lmiui/util/async/tasks/ContentResolverQueryTask;->wS:[Ljava/lang/String;

    if-eqz v1, :cond_3

    .line 183
    invoke-virtual {v0, v6}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    .line 184
    iget-object v1, p0, Lmiui/util/async/tasks/ContentResolverQueryTask;->wS:[Ljava/lang/String;

    aget-object v1, v1, v4

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 185
    :goto_1
    iget-object v1, p0, Lmiui/util/async/tasks/ContentResolverQueryTask;->wS:[Ljava/lang/String;

    array-length v1, v1

    if-ge v2, v1, :cond_2

    .line 186
    invoke-virtual {v0, v5}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    .line 187
    iget-object v1, p0, Lmiui/util/async/tasks/ContentResolverQueryTask;->wS:[Ljava/lang/String;

    aget-object v1, v1, v2

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 185
    add-int/lit8 v2, v2, 0x1

    goto :goto_1

    .line 189
    :cond_2
    const-string v1, "]@"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 191
    :cond_3
    iget-object v1, p0, Lmiui/util/async/tasks/ContentResolverQueryTask;->Is:Ljava/lang/String;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 193
    :cond_4
    iget-object v1, p0, Lmiui/util/async/tasks/ContentResolverQueryTask;->It:Ljava/lang/String;

    if-eqz v1, :cond_5

    .line 194
    const-string v1, " ORDER BY "

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 195
    iget-object v1, p0, Lmiui/util/async/tasks/ContentResolverQueryTask;->It:Ljava/lang/String;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 198
    :cond_5
    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    iput-object v1, p0, Lmiui/util/async/tasks/ContentResolverQueryTask;->wT:Ljava/lang/String;

    .line 199
    invoke-static {}, Lmiui/util/Pools;->getStringBuilderPool()Lmiui/util/Pools$Pool;

    move-result-object v1

    invoke-interface {v1, v0}, Lmiui/util/Pools$Pool;->release(Ljava/lang/Object;)V

    .line 201
    :cond_6
    iget-object v0, p0, Lmiui/util/async/tasks/ContentResolverQueryTask;->wT:Ljava/lang/String;

    return-object v0
.end method

.method public onPrepare(Lmiui/util/async/TaskManager;)V
    .locals 1

    .prologue
    .line 259
    new-instance v0, Ljava/lang/ref/WeakReference;

    invoke-direct {v0, p1}, Ljava/lang/ref/WeakReference;-><init>(Ljava/lang/Object;)V

    iput-object v0, p0, Lmiui/util/async/tasks/ContentResolverQueryTask;->jw:Ljava/lang/ref/WeakReference;

    .line 260
    return-void
.end method

.method pause()V
    .locals 1

    .prologue
    .line 242
    const/4 v0, 0x1

    iput-boolean v0, p0, Lmiui/util/async/tasks/ContentResolverQueryTask;->Iv:Z

    .line 243
    return-void
.end method

.method protected query()Landroid/database/Cursor;
    .locals 6
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/Exception;
        }
    .end annotation

    .prologue
    .line 215
    invoke-static {}, Lcom/miui/internal/util/PackageConstants;->getCurrentApplication()Landroid/app/Application;

    move-result-object v0

    invoke-virtual {v0}, Landroid/app/Application;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object v0

    iget-object v1, p0, Lmiui/util/async/tasks/ContentResolverQueryTask;->wQ:Landroid/net/Uri;

    iget-object v2, p0, Lmiui/util/async/tasks/ContentResolverQueryTask;->Ir:[Ljava/lang/String;

    iget-object v3, p0, Lmiui/util/async/tasks/ContentResolverQueryTask;->Is:Ljava/lang/String;

    iget-object v4, p0, Lmiui/util/async/tasks/ContentResolverQueryTask;->wS:[Ljava/lang/String;

    iget-object v5, p0, Lmiui/util/async/tasks/ContentResolverQueryTask;->It:Ljava/lang/String;

    invoke-virtual/range {v0 .. v5}, Landroid/content/ContentResolver;->query(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;

    move-result-object v0

    return-object v0
.end method

.method requery()V
    .locals 1

    .prologue
    .line 223
    iget-boolean v0, p0, Lmiui/util/async/tasks/ContentResolverQueryTask;->Iv:Z

    if-eqz v0, :cond_1

    .line 224
    const/4 v0, 0x1

    iput-boolean v0, p0, Lmiui/util/async/tasks/ContentResolverQueryTask;->Iw:Z

    .line 236
    :cond_0
    :goto_0
    return-void

    .line 226
    :cond_1
    iget-object v0, p0, Lmiui/util/async/tasks/ContentResolverQueryTask;->jw:Ljava/lang/ref/WeakReference;

    if-eqz v0, :cond_0

    .line 227
    iget-object v0, p0, Lmiui/util/async/tasks/ContentResolverQueryTask;->jw:Ljava/lang/ref/WeakReference;

    invoke-virtual {v0}, Ljava/lang/ref/WeakReference;->get()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/util/async/TaskManager;

    .line 228
    if-eqz v0, :cond_2

    .line 229
    invoke-virtual {p0}, Lmiui/util/async/tasks/ContentResolverQueryTask;->restart()Z

    .line 230
    invoke-virtual {v0, p0}, Lmiui/util/async/TaskManager;->add(Lmiui/util/async/Task;)V

    goto :goto_0

    .line 232
    :cond_2
    invoke-virtual {p0}, Lmiui/util/async/tasks/ContentResolverQueryTask;->disableAutoRequery()V

    goto :goto_0
.end method

.method resume()V
    .locals 2

    .prologue
    const/4 v1, 0x0

    .line 249
    iput-boolean v1, p0, Lmiui/util/async/tasks/ContentResolverQueryTask;->Iv:Z

    .line 251
    iget-boolean v0, p0, Lmiui/util/async/tasks/ContentResolverQueryTask;->Iw:Z

    if-eqz v0, :cond_0

    .line 252
    iput-boolean v1, p0, Lmiui/util/async/tasks/ContentResolverQueryTask;->Iw:Z

    .line 253
    invoke-virtual {p0}, Lmiui/util/async/tasks/ContentResolverQueryTask;->requery()V

    .line 255
    :cond_0
    return-void
.end method
