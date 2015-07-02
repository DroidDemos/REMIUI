.class final Lmiui/net/http/f;
.super Lmiui/util/SoftReferenceSingleton;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/net/http/DiskBasedCache;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x8
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lmiui/util/SoftReferenceSingleton",
        "<",
        "Lmiui/net/http/DiskBasedCache;",
        ">;"
    }
.end annotation


# direct methods
.method constructor <init>()V
    .locals 0

    .prologue
    .line 74
    invoke-direct {p0}, Lmiui/util/SoftReferenceSingleton;-><init>()V

    return-void
.end method


# virtual methods
.method protected bridge synthetic createInstance()Ljava/lang/Object;
    .locals 1

    .prologue
    .line 74
    invoke-virtual {p0}, Lmiui/net/http/f;->eV()Lmiui/net/http/DiskBasedCache;

    move-result-object v0

    return-object v0
.end method

.method protected eV()Lmiui/net/http/DiskBasedCache;
    .locals 1

    .prologue
    .line 77
    new-instance v0, Lmiui/net/http/DiskBasedCache;

    invoke-direct {v0}, Lmiui/net/http/DiskBasedCache;-><init>()V

    .line 78
    invoke-virtual {v0}, Lmiui/net/http/DiskBasedCache;->initialize()V

    .line 79
    return-object v0
.end method
