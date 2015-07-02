.class Lmiui/accounts/ExtraAccountManager$1;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lmiui/accounts/ExtraAccountManager;->a(Landroid/os/Handler;Lmiui/accounts/MiuiOnAccountsUpdateListener;Landroid/accounts/Account;ILandroid/os/Bundle;Z)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic BK:Z

.field final synthetic BL:Lmiui/accounts/MiuiOnAccountsUpdateListener;

.field final synthetic BP:Landroid/accounts/Account;

.field final synthetic BQ:I

.field final synthetic BU:Landroid/os/Bundle;

.field final synthetic BV:Lmiui/accounts/ExtraAccountManager;


# direct methods
.method constructor <init>(Lmiui/accounts/ExtraAccountManager;ZLmiui/accounts/MiuiOnAccountsUpdateListener;Landroid/accounts/Account;ILandroid/os/Bundle;)V
    .locals 0

    .prologue
    .line 208
    iput-object p1, p0, Lmiui/accounts/ExtraAccountManager$1;->BV:Lmiui/accounts/ExtraAccountManager;

    iput-boolean p2, p0, Lmiui/accounts/ExtraAccountManager$1;->BK:Z

    iput-object p3, p0, Lmiui/accounts/ExtraAccountManager$1;->BL:Lmiui/accounts/MiuiOnAccountsUpdateListener;

    iput-object p4, p0, Lmiui/accounts/ExtraAccountManager$1;->BP:Landroid/accounts/Account;

    iput p5, p0, Lmiui/accounts/ExtraAccountManager$1;->BQ:I

    iput-object p6, p0, Lmiui/accounts/ExtraAccountManager$1;->BU:Landroid/os/Bundle;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .locals 4

    .prologue
    .line 211
    :try_start_0
    iget-boolean v0, p0, Lmiui/accounts/ExtraAccountManager$1;->BK:Z

    if-eqz v0, :cond_0

    .line 212
    iget-object v0, p0, Lmiui/accounts/ExtraAccountManager$1;->BL:Lmiui/accounts/MiuiOnAccountsUpdateListener;

    iget-object v1, p0, Lmiui/accounts/ExtraAccountManager$1;->BP:Landroid/accounts/Account;

    iget v2, p0, Lmiui/accounts/ExtraAccountManager$1;->BQ:I

    iget-object v3, p0, Lmiui/accounts/ExtraAccountManager$1;->BU:Landroid/os/Bundle;

    invoke-interface {v0, v1, v2, v3}, Lmiui/accounts/MiuiOnAccountsUpdateListener;->onPreAccountUpdated(Landroid/accounts/Account;ILandroid/os/Bundle;)V

    .line 221
    :goto_0
    return-void

    .line 214
    :cond_0
    iget-object v0, p0, Lmiui/accounts/ExtraAccountManager$1;->BL:Lmiui/accounts/MiuiOnAccountsUpdateListener;

    iget-object v1, p0, Lmiui/accounts/ExtraAccountManager$1;->BP:Landroid/accounts/Account;

    iget v2, p0, Lmiui/accounts/ExtraAccountManager$1;->BQ:I

    iget-object v3, p0, Lmiui/accounts/ExtraAccountManager$1;->BU:Landroid/os/Bundle;

    invoke-interface {v0, v1, v2, v3}, Lmiui/accounts/MiuiOnAccountsUpdateListener;->onPostAccountUpdated(Landroid/accounts/Account;ILandroid/os/Bundle;)V
    :try_end_0
    .catch Landroid/database/SQLException; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    .line 216
    :catch_0
    move-exception v0

    .line 219
    const-string v1, "ExtraAccountManager"

    const-string v2, "Can\'t update accounts"

    invoke-static {v1, v2, v0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    goto :goto_0
.end method
