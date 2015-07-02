.class final Lmiui/util/f;
.super Lmiui/util/Pools$Manager;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/util/IOUtils;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x8
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lmiui/util/Pools$Manager",
        "<",
        "Ljava/io/CharArrayWriter;",
        ">;"
    }
.end annotation


# direct methods
.method constructor <init>()V
    .locals 0

    .prologue
    .line 54
    invoke-direct {p0}, Lmiui/util/Pools$Manager;-><init>()V

    return-void
.end method


# virtual methods
.method public a(Ljava/io/CharArrayWriter;)V
    .locals 0

    .prologue
    .line 62
    invoke-virtual {p1}, Ljava/io/CharArrayWriter;->reset()V

    .line 63
    return-void
.end method

.method public bridge synthetic createInstance()Ljava/lang/Object;
    .locals 1

    .prologue
    .line 54
    invoke-virtual {p0}, Lmiui/util/f;->eJ()Ljava/io/CharArrayWriter;

    move-result-object v0

    return-object v0
.end method

.method public eJ()Ljava/io/CharArrayWriter;
    .locals 1

    .prologue
    .line 57
    new-instance v0, Ljava/io/CharArrayWriter;

    invoke-direct {v0}, Ljava/io/CharArrayWriter;-><init>()V

    return-object v0
.end method

.method public bridge synthetic onRelease(Ljava/lang/Object;)V
    .locals 0

    .prologue
    .line 54
    check-cast p1, Ljava/io/CharArrayWriter;

    invoke-virtual {p0, p1}, Lmiui/util/f;->a(Ljava/io/CharArrayWriter;)V

    return-void
.end method
