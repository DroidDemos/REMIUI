.class Lmiui/accounts/a;
.super Landroid/content/BroadcastReceiver;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/accounts/ExtraAccountManager;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic BV:Lmiui/accounts/ExtraAccountManager;


# direct methods
.method constructor <init>(Lmiui/accounts/ExtraAccountManager;)V
    .locals 0

    .prologue
    .line 226
    iput-object p1, p0, Lmiui/accounts/a;->BV:Lmiui/accounts/ExtraAccountManager;

    invoke-direct {p0}, Landroid/content/BroadcastReceiver;-><init>()V

    return-void
.end method


# virtual methods
.method public onReceive(Landroid/content/Context;Landroid/content/Intent;)V
    .locals 11

    .prologue
    .line 228
    invoke-virtual {p2}, Landroid/content/Intent;->getAction()Ljava/lang/String;

    move-result-object v8

    .line 229
    const-string v1, "android.accounts.LOGIN_ACCOUNTS_PRE_CHANGED"

    invoke-virtual {v1, v8}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_0

    const-string v1, "android.accounts.LOGIN_ACCOUNTS_POST_CHANGED"

    invoke-virtual {v1, v8}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_3

    .line 231
    :cond_0
    const-string v1, "extra_account"

    invoke-virtual {p2, v1}, Landroid/content/Intent;->getParcelableExtra(Ljava/lang/String;)Landroid/os/Parcelable;

    move-result-object v4

    check-cast v4, Landroid/accounts/Account;

    .line 232
    const-string v1, "extra_bundle"

    invoke-virtual {p2, v1}, Landroid/content/Intent;->getParcelableExtra(Ljava/lang/String;)Landroid/os/Parcelable;

    move-result-object v6

    check-cast v6, Landroid/os/Bundle;

    .line 233
    const-string v1, "extra_update_type"

    const/4 v2, -0x1

    invoke-virtual {p2, v1, v2}, Landroid/content/Intent;->getIntExtra(Ljava/lang/String;I)I

    move-result v5

    .line 234
    if-eqz v4, :cond_2

    if-lez v5, :cond_2

    .line 235
    iget-object v1, p0, Lmiui/accounts/a;->BV:Lmiui/accounts/ExtraAccountManager;

    invoke-static {v1}, Lmiui/accounts/ExtraAccountManager;->a(Lmiui/accounts/ExtraAccountManager;)Ljava/util/HashMap;

    move-result-object v9

    monitor-enter v9

    .line 237
    :try_start_0
    iget-object v1, p0, Lmiui/accounts/a;->BV:Lmiui/accounts/ExtraAccountManager;

    invoke-static {v1}, Lmiui/accounts/ExtraAccountManager;->a(Lmiui/accounts/ExtraAccountManager;)Ljava/util/HashMap;

    move-result-object v1

    invoke-virtual {v1}, Ljava/util/HashMap;->entrySet()Ljava/util/Set;

    move-result-object v1

    invoke-interface {v1}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v10

    :goto_0
    invoke-interface {v10}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_1

    invoke-interface {v10}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    move-object v0, v1

    check-cast v0, Ljava/util/Map$Entry;

    move-object v3, v0

    .line 238
    iget-object v1, p0, Lmiui/accounts/a;->BV:Lmiui/accounts/ExtraAccountManager;

    invoke-interface {v3}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Landroid/os/Handler;

    invoke-interface {v3}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Lmiui/accounts/MiuiOnAccountsUpdateListener;

    const-string v7, "android.accounts.LOGIN_ACCOUNTS_PRE_CHANGED"

    invoke-virtual {v7, v8}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v7

    invoke-static/range {v1 .. v7}, Lmiui/accounts/ExtraAccountManager;->a(Lmiui/accounts/ExtraAccountManager;Landroid/os/Handler;Lmiui/accounts/MiuiOnAccountsUpdateListener;Landroid/accounts/Account;ILandroid/os/Bundle;Z)V

    goto :goto_0

    .line 243
    :catchall_0
    move-exception v1

    monitor-exit v9
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw v1

    :cond_1
    :try_start_1
    monitor-exit v9
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 258
    :goto_1
    return-void

    .line 245
    :cond_2
    const-string v1, "ExtraAccountManager"

    const-string v2, "account changed, but no account or type"

    invoke-static {v1, v2}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    goto :goto_1

    .line 248
    :cond_3
    iget-object v1, p0, Lmiui/accounts/a;->BV:Lmiui/accounts/ExtraAccountManager;

    invoke-static {v1}, Lmiui/accounts/ExtraAccountManager;->b(Lmiui/accounts/ExtraAccountManager;)Landroid/accounts/AccountManager;

    move-result-object v1

    invoke-virtual {v1}, Landroid/accounts/AccountManager;->getAccounts()[Landroid/accounts/Account;

    move-result-object v3

    .line 250
    iget-object v1, p0, Lmiui/accounts/a;->BV:Lmiui/accounts/ExtraAccountManager;

    invoke-static {v1}, Lmiui/accounts/ExtraAccountManager;->a(Lmiui/accounts/ExtraAccountManager;)Ljava/util/HashMap;

    move-result-object v4

    monitor-enter v4

    .line 252
    :try_start_2
    iget-object v1, p0, Lmiui/accounts/a;->BV:Lmiui/accounts/ExtraAccountManager;

    invoke-static {v1}, Lmiui/accounts/ExtraAccountManager;->a(Lmiui/accounts/ExtraAccountManager;)Ljava/util/HashMap;

    move-result-object v1

    invoke-virtual {v1}, Ljava/util/HashMap;->entrySet()Ljava/util/Set;

    move-result-object v1

    invoke-interface {v1}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v5

    :goto_2
    invoke-interface {v5}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_4

    invoke-interface {v5}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/util/Map$Entry;

    .line 253
    iget-object v6, p0, Lmiui/accounts/a;->BV:Lmiui/accounts/ExtraAccountManager;

    invoke-interface {v1}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Landroid/os/Handler;

    invoke-interface {v1}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lmiui/accounts/MiuiOnAccountsUpdateListener;

    invoke-static {v6, v2, v1, v3}, Lmiui/accounts/ExtraAccountManager;->a(Lmiui/accounts/ExtraAccountManager;Landroid/os/Handler;Lmiui/accounts/MiuiOnAccountsUpdateListener;[Landroid/accounts/Account;)V

    goto :goto_2

    .line 256
    :catchall_1
    move-exception v1

    monitor-exit v4
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_1

    throw v1

    :cond_4
    :try_start_3
    monitor-exit v4
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_1

    goto :goto_1
.end method
