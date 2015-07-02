.class final Lmiui/date/c;
.super Lmiui/util/SoftReferenceSingleton;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/date/Holidays;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x8
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lmiui/util/SoftReferenceSingleton",
        "<",
        "Lmiui/date/Holidays;",
        ">;"
    }
.end annotation


# direct methods
.method constructor <init>()V
    .locals 0

    .prologue
    .line 123
    invoke-direct {p0}, Lmiui/util/SoftReferenceSingleton;-><init>()V

    return-void
.end method


# virtual methods
.method protected bridge synthetic createInstance()Ljava/lang/Object;
    .locals 1

    .prologue
    .line 123
    invoke-virtual {p0}, Lmiui/date/c;->et()Lmiui/date/Holidays;

    move-result-object v0

    return-object v0
.end method

.method protected et()Lmiui/date/Holidays;
    .locals 2

    .prologue
    .line 126
    new-instance v0, Lmiui/date/Holidays;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lmiui/date/Holidays;-><init>(Lmiui/date/c;)V

    return-object v0
.end method
