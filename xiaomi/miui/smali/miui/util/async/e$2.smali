.class Lmiui/util/async/e$2;
.super Ljava/lang/Thread;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lmiui/util/async/e;->setCallbackThread(Z)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic pn:Lmiui/util/async/e;


# direct methods
.method constructor <init>(Lmiui/util/async/e;)V
    .locals 0

    .prologue
    .line 154
    iput-object p1, p0, Lmiui/util/async/e$2;->pn:Lmiui/util/async/e;

    invoke-direct {p0}, Ljava/lang/Thread;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .locals 3

    .prologue
    .line 157
    invoke-static {}, Landroid/os/Looper;->prepare()V

    .line 158
    iget-object v0, p0, Lmiui/util/async/e$2;->pn:Lmiui/util/async/e;

    new-instance v1, Lmiui/util/async/e$b;

    invoke-static {}, Landroid/os/Looper;->myLooper()Landroid/os/Looper;

    move-result-object v2

    invoke-direct {v1, v2}, Lmiui/util/async/e$b;-><init>(Landroid/os/Looper;)V

    invoke-static {v0, v1}, Lmiui/util/async/e;->a(Lmiui/util/async/e;Lmiui/util/async/e$b;)Lmiui/util/async/e$b;

    .line 159
    invoke-static {}, Landroid/os/Looper;->loop()V

    .line 160
    return-void
.end method
