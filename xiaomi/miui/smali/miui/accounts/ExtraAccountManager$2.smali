.class Lmiui/accounts/ExtraAccountManager$2;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lmiui/accounts/ExtraAccountManager;->a(Landroid/os/Handler;Lmiui/accounts/MiuiOnAccountsUpdateListener;[Landroid/accounts/Account;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic BL:Lmiui/accounts/MiuiOnAccountsUpdateListener;

.field final synthetic BV:Lmiui/accounts/ExtraAccountManager;

.field final synthetic BX:[Landroid/accounts/Account;


# direct methods
.method constructor <init>(Lmiui/accounts/ExtraAccountManager;Lmiui/accounts/MiuiOnAccountsUpdateListener;[Landroid/accounts/Account;)V
    .locals 0

    .prologue
    .line 190
    iput-object p1, p0, Lmiui/accounts/ExtraAccountManager$2;->BV:Lmiui/accounts/ExtraAccountManager;

    iput-object p2, p0, Lmiui/accounts/ExtraAccountManager$2;->BL:Lmiui/accounts/MiuiOnAccountsUpdateListener;

    iput-object p3, p0, Lmiui/accounts/ExtraAccountManager$2;->BX:[Landroid/accounts/Account;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .locals 3

    .prologue
    .line 193
    :try_start_0
    iget-object v0, p0, Lmiui/accounts/ExtraAccountManager$2;->BL:Lmiui/accounts/MiuiOnAccountsUpdateListener;

    iget-object v1, p0, Lmiui/accounts/ExtraAccountManager$2;->BX:[Landroid/accounts/Account;

    invoke-interface {v0, v1}, Lmiui/accounts/MiuiOnAccountsUpdateListener;->onAccountsUpdated([Landroid/accounts/Account;)V
    :try_end_0
    .catch Landroid/database/SQLException; {:try_start_0 .. :try_end_0} :catch_0

    .line 199
    :goto_0
    return-void

    .line 194
    :catch_0
    move-exception v0

    .line 197
    const-string v1, "ExtraAccountManager"

    const-string v2, "Can\'t update accounts"

    invoke-static {v1, v2, v0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    goto :goto_0
.end method
