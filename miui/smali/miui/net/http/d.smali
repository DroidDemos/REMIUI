.class final Lmiui/net/http/d;
.super Lmiui/util/SoftReferenceSingleton;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/net/http/PersistentCookieStore;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x8
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lmiui/util/SoftReferenceSingleton",
        "<",
        "Lmiui/net/http/PersistentCookieStore;",
        ">;"
    }
.end annotation


# direct methods
.method constructor <init>()V
    .locals 0

    .prologue
    .line 42
    invoke-direct {p0}, Lmiui/util/SoftReferenceSingleton;-><init>()V

    return-void
.end method


# virtual methods
.method protected aA()Lmiui/net/http/PersistentCookieStore;
    .locals 2

    .prologue
    .line 45
    new-instance v0, Lmiui/net/http/PersistentCookieStore;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lmiui/net/http/PersistentCookieStore;-><init>(Lmiui/net/http/d;)V

    return-object v0
.end method

.method protected bridge synthetic createInstance()Ljava/lang/Object;
    .locals 1

    .prologue
    .line 42
    invoke-virtual {p0}, Lmiui/net/http/d;->aA()Lmiui/net/http/PersistentCookieStore;

    move-result-object v0

    return-object v0
.end method
