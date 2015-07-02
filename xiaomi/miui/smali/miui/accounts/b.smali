.class final Lmiui/accounts/b;
.super Lmiui/util/SoftReferenceSingleton;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/accounts/ExtraAccountManager;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x8
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lmiui/util/SoftReferenceSingleton",
        "<",
        "Lmiui/accounts/ExtraAccountManager;",
        ">;"
    }
.end annotation


# direct methods
.method constructor <init>()V
    .locals 0

    .prologue
    .line 100
    invoke-direct {p0}, Lmiui/util/SoftReferenceSingleton;-><init>()V

    return-void
.end method


# virtual methods
.method protected cO()Lmiui/accounts/ExtraAccountManager;
    .locals 3

    .prologue
    .line 103
    new-instance v0, Lmiui/accounts/ExtraAccountManager;

    invoke-static {}, Lcom/miui/internal/util/PackageConstants;->getCurrentApplication()Landroid/app/Application;

    move-result-object v1

    const/4 v2, 0x0

    invoke-direct {v0, v1, v2}, Lmiui/accounts/ExtraAccountManager;-><init>(Landroid/content/Context;Lmiui/accounts/b;)V

    return-object v0
.end method

.method protected bridge synthetic createInstance()Ljava/lang/Object;
    .locals 1

    .prologue
    .line 100
    invoke-virtual {p0}, Lmiui/accounts/b;->cO()Lmiui/accounts/ExtraAccountManager;

    move-result-object v0

    return-object v0
.end method
