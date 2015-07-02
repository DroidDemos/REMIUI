.class final Lmiui/telephony/a;
.super Lmiui/util/SoftReferenceSingleton;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/telephony/TelephonyHelper;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x8
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lmiui/util/SoftReferenceSingleton",
        "<",
        "Lmiui/telephony/TelephonyHelper;",
        ">;"
    }
.end annotation


# direct methods
.method constructor <init>()V
    .locals 0

    .prologue
    .line 23
    invoke-direct {p0}, Lmiui/util/SoftReferenceSingleton;-><init>()V

    return-void
.end method


# virtual methods
.method protected aZ()Lmiui/telephony/TelephonyHelper;
    .locals 2

    .prologue
    .line 26
    new-instance v0, Lmiui/telephony/TelephonyHelper;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lmiui/telephony/TelephonyHelper;-><init>(Lmiui/telephony/a;)V

    return-object v0
.end method

.method protected bridge synthetic createInstance()Ljava/lang/Object;
    .locals 1

    .prologue
    .line 23
    invoke-virtual {p0}, Lmiui/telephony/a;->aZ()Lmiui/telephony/TelephonyHelper;

    move-result-object v0

    return-object v0
.end method
