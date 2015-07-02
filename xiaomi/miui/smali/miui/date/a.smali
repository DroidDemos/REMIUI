.class final Lmiui/date/a;
.super Lmiui/util/SoftReferenceSingleton;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/date/CalendarFormatSymbols;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x8
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lmiui/util/SoftReferenceSingleton",
        "<",
        "Lmiui/date/CalendarFormatSymbols;",
        ">;"
    }
.end annotation


# direct methods
.method constructor <init>()V
    .locals 0

    .prologue
    .line 19
    invoke-direct {p0}, Lmiui/util/SoftReferenceSingleton;-><init>()V

    return-void
.end method


# virtual methods
.method protected bG()Lmiui/date/CalendarFormatSymbols;
    .locals 1

    .prologue
    .line 22
    new-instance v0, Lmiui/date/CalendarFormatSymbols;

    invoke-direct {v0}, Lmiui/date/CalendarFormatSymbols;-><init>()V

    return-object v0
.end method

.method protected bridge synthetic createInstance()Ljava/lang/Object;
    .locals 1

    .prologue
    .line 19
    invoke-virtual {p0}, Lmiui/date/a;->bG()Lmiui/date/CalendarFormatSymbols;

    move-result-object v0

    return-object v0
.end method
