.class Lmiui/util/async/e$1;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lmiui/util/concurrent/Queue$Predicate;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lmiui/util/async/e;->b(Lmiui/util/async/Task;Lmiui/util/async/Task$Delivery;Ljava/lang/Object;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Object;",
        "Lmiui/util/concurrent/Queue$Predicate",
        "<",
        "Lmiui/util/async/e$a;",
        ">;"
    }
.end annotation


# instance fields
.field final synthetic pl:Lmiui/util/async/Task;

.field final synthetic pn:Lmiui/util/async/e;


# direct methods
.method constructor <init>(Lmiui/util/async/e;Lmiui/util/async/Task;)V
    .locals 0

    .prologue
    .line 192
    iput-object p1, p0, Lmiui/util/async/e$1;->pn:Lmiui/util/async/e;

    iput-object p2, p0, Lmiui/util/async/e$1;->pl:Lmiui/util/async/Task;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public a(Lmiui/util/async/e$a;)Z
    .locals 2

    .prologue
    .line 195
    iget-object v0, p1, Lmiui/util/async/e$a;->sD:Lmiui/util/async/Task;

    iget-object v1, p0, Lmiui/util/async/e$1;->pl:Lmiui/util/async/Task;

    if-ne v0, v1, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public bridge synthetic apply(Ljava/lang/Object;)Z
    .locals 1

    .prologue
    .line 192
    check-cast p1, Lmiui/util/async/e$a;

    invoke-virtual {p0, p1}, Lmiui/util/async/e$1;->a(Lmiui/util/async/e$a;)Z

    move-result v0

    return v0
.end method
