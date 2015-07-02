.class final Lmiui/util/async/c;
.super Lmiui/util/SoftReferenceSingleton;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/util/async/TaskManager;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x8
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lmiui/util/SoftReferenceSingleton",
        "<",
        "Lmiui/util/async/TaskManager;",
        ">;"
    }
.end annotation


# direct methods
.method constructor <init>()V
    .locals 0

    .prologue
    .line 60
    invoke-direct {p0}, Lmiui/util/SoftReferenceSingleton;-><init>()V

    return-void
.end method


# virtual methods
.method protected aL()Lmiui/util/async/TaskManager;
    .locals 1

    .prologue
    .line 63
    new-instance v0, Lmiui/util/async/TaskManager;

    invoke-direct {v0}, Lmiui/util/async/TaskManager;-><init>()V

    return-object v0
.end method

.method protected bridge synthetic createInstance()Ljava/lang/Object;
    .locals 1

    .prologue
    .line 60
    invoke-virtual {p0}, Lmiui/util/async/c;->aL()Lmiui/util/async/TaskManager;

    move-result-object v0

    return-object v0
.end method
