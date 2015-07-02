.class Lmiui/util/async/tasks/HttpTask$2;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lmiui/net/http/HttpSession$ProgressListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lmiui/util/async/tasks/HttpTask;->request()Lmiui/net/http/HttpResponse;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic Bs:Lmiui/util/async/tasks/HttpTask;


# direct methods
.method constructor <init>(Lmiui/util/async/tasks/HttpTask;)V
    .locals 0

    .prologue
    .line 122
    iput-object p1, p0, Lmiui/util/async/tasks/HttpTask$2;->Bs:Lmiui/util/async/tasks/HttpTask;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onProgress(JJ)V
    .locals 5

    .prologue
    const/high16 v4, 0x5f000000

    const-wide/32 v2, 0x7fffffff

    .line 125
    cmp-long v0, p1, v2

    if-lez v0, :cond_0

    .line 126
    mul-long v0, p1, v2

    long-to-float v0, v0

    div-float/2addr v0, v4

    float-to-int v0, v0

    int-to-long p1, v0

    .line 127
    mul-long v0, p1, v2

    long-to-float v0, v0

    div-float/2addr v0, v4

    float-to-int v0, v0

    int-to-long p3, v0

    .line 129
    :cond_0
    iget-object v0, p0, Lmiui/util/async/tasks/HttpTask$2;->Bs:Lmiui/util/async/tasks/HttpTask;

    long-to-int v1, p1

    long-to-int v2, p3

    invoke-virtual {v0, v1, v2}, Lmiui/util/async/tasks/HttpTask;->publishProgress(II)V

    .line 130
    return-void
.end method
