.class Lmiui/util/async/e$a;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/util/async/e;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x8
    name = "a"
.end annotation


# instance fields
.field public sD:Lmiui/util/async/Task;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lmiui/util/async/Task",
            "<*>;"
        }
    .end annotation
.end field

.field public sE:Lmiui/util/async/Task$Delivery;

.field public sF:Ljava/lang/Object;


# direct methods
.method constructor <init>()V
    .locals 0

    .prologue
    .line 25
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public clear()V
    .locals 1

    .prologue
    const/4 v0, 0x0

    .line 46
    iput-object v0, p0, Lmiui/util/async/e$a;->sD:Lmiui/util/async/Task;

    .line 47
    iput-object v0, p0, Lmiui/util/async/e$a;->sE:Lmiui/util/async/Task$Delivery;

    .line 48
    iput-object v0, p0, Lmiui/util/async/e$a;->sF:Ljava/lang/Object;

    .line 49
    return-void
.end method
