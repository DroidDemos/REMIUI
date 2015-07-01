.class public Lmiui/accounts/ExtraAccountManager;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field public static final ACTION_VIEW_XIAOMI_ACCOUNT:Ljava/lang/String; = "android.settings.XIAOMI_ACCOUNT_SYNC_SETTINGS"

.field public static final BIND_SNS_TYPE_SINA:I = 0x1

.field public static final EXTRA_ACCOUNT:Ljava/lang/String; = "extra_account"

.field public static final EXTRA_ADD_ACCOUNT_FROM_PROVISION:Ljava/lang/String; = "extra_add_account_from_provision"

.field public static final EXTRA_BIND_SNS_TYPE:Ljava/lang/String; = "extra_bind_sns_type"

.field public static final EXTRA_BUNDLE:Ljava/lang/String; = "extra_bundle"

.field public static final EXTRA_CLEAR_WHEN_RESET:Ljava/lang/String; = "extra_clear_when_reset"

.field public static final EXTRA_DISABLE_BACK_KEY:Ljava/lang/String; = "extra_disable_back_key"

.field public static final EXTRA_ENTER_ACCOUNT_FORWORD_IN_PROVISION:Ljava/lang/String; = "is_to_next_in_provision"

.field public static final EXTRA_FIND_PASSWORD_ON_PC:Ljava/lang/String; = "extra_find_pwd_on_pc"

.field public static final EXTRA_SHOW_ACCOUNT_SETTINGS:Ljava/lang/String; = "show_account_settings"

.field public static final EXTRA_SHOW_FIND_DEVICE:Ljava/lang/String; = "extra_show_find_device"

.field public static final EXTRA_SHOW_SKIP_LOGIN:Ljava/lang/String; = "extra_show_skip_login"

.field public static final EXTRA_SHOW_SYNC_SETTINGS:Ljava/lang/String; = "show_sync_settings"

.field public static final EXTRA_UPDATE_TYPE:Ljava/lang/String; = "extra_update_type"

.field public static final EXTRA_WIPE_DATA:Ljava/lang/String; = "extra_wipe_data"

.field public static final KEY_CAPTCHA_CODE:Ljava/lang/String; = "captcha_code"

.field public static final KEY_CAPTCHA_ICK:Ljava/lang/String; = "captcha_ick"

.field public static final KEY_CAPTCHA_URL:Ljava/lang/String; = "captcha_url"

.field public static final KEY_ENCRYPTED_USER_ID:Ljava/lang/String; = "encrypted_user_id"

.field public static final KEY_SERVICE_ID:Ljava/lang/String; = "service_id"

.field public static final KEY_TITLE:Ljava/lang/String; = "title"

.field public static final LOGIN_ACCOUNTS_POST_CHANGED_ACTION:Ljava/lang/String; = "android.accounts.LOGIN_ACCOUNTS_POST_CHANGED"

.field public static final LOGIN_ACCOUNTS_PRE_CHANGED_ACTION:Ljava/lang/String; = "android.accounts.LOGIN_ACCOUNTS_PRE_CHANGED"

.field private static final TAG:Ljava/lang/String; = "ExtraAccountManager"

.field public static final TYPE_ADD:I = 0x2

.field public static final TYPE_REMOVE:I = 0x1

.field public static final XIAOMI_ACCOUNT_PACKAGE_NAME:Ljava/lang/String; = "com.xiaomi.account"

.field private static final f:Lmiui/util/SoftReferenceSingleton;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lmiui/util/SoftReferenceSingleton",
            "<",
            "Lmiui/accounts/ExtraAccountManager;",
            ">;"
        }
    .end annotation
.end field


# instance fields
.field private mContext:Landroid/content/Context;

.field private qd:Landroid/accounts/AccountManager;

.field private qe:Landroid/os/Handler;

.field private final qf:Ljava/util/HashMap;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/HashMap",
            "<",
            "Lmiui/accounts/MiuiOnAccountsUpdateListener;",
            "Landroid/os/Handler;",
            ">;"
        }
    .end annotation
.end field

.field private final qg:Landroid/content/BroadcastReceiver;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .prologue
    .line 99
    new-instance v0, Lmiui/accounts/b;

    invoke-direct {v0}, Lmiui/accounts/b;-><init>()V

    sput-object v0, Lmiui/accounts/ExtraAccountManager;->f:Lmiui/util/SoftReferenceSingleton;

    return-void
.end method

.method private constructor <init>(Landroid/content/Context;)V
    .locals 2

    .prologue
    .line 107
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 96
    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    iput-object v0, p0, Lmiui/accounts/ExtraAccountManager;->qf:Ljava/util/HashMap;

    .line 225
    new-instance v0, Lmiui/accounts/a;

    invoke-direct {v0, p0}, Lmiui/accounts/a;-><init>(Lmiui/accounts/ExtraAccountManager;)V

    iput-object v0, p0, Lmiui/accounts/ExtraAccountManager;->qg:Landroid/content/BroadcastReceiver;

    .line 108
    iput-object p1, p0, Lmiui/accounts/ExtraAccountManager;->mContext:Landroid/content/Context;

    .line 109
    iget-object v0, p0, Lmiui/accounts/ExtraAccountManager;->mContext:Landroid/content/Context;

    invoke-static {v0}, Landroid/accounts/AccountManager;->get(Landroid/content/Context;)Landroid/accounts/AccountManager;

    move-result-object v0

    iput-object v0, p0, Lmiui/accounts/ExtraAccountManager;->qd:Landroid/accounts/AccountManager;

    .line 110
    new-instance v0, Landroid/os/Handler;

    iget-object v1, p0, Lmiui/accounts/ExtraAccountManager;->mContext:Landroid/content/Context;

    invoke-virtual {v1}, Landroid/content/Context;->getMainLooper()Landroid/os/Looper;

    move-result-object v1

    invoke-direct {v0, v1}, Landroid/os/Handler;-><init>(Landroid/os/Looper;)V

    iput-object v0, p0, Lmiui/accounts/ExtraAccountManager;->qe:Landroid/os/Handler;

    .line 111
    return-void
.end method

.method synthetic constructor <init>(Landroid/content/Context;Lmiui/accounts/b;)V
    .locals 0

    .prologue
    .line 25
    invoke-direct {p0, p1}, Lmiui/accounts/ExtraAccountManager;-><init>(Landroid/content/Context;)V

    return-void
.end method

.method static synthetic a(Lmiui/accounts/ExtraAccountManager;)Ljava/util/HashMap;
    .locals 1

    .prologue
    .line 25
    iget-object v0, p0, Lmiui/accounts/ExtraAccountManager;->qf:Ljava/util/HashMap;

    return-object v0
.end method

.method private a(Landroid/os/Handler;Lmiui/accounts/MiuiOnAccountsUpdateListener;Landroid/accounts/Account;ILandroid/os/Bundle;Z)V
    .locals 7

    .prologue
    .line 207
    if-nez p1, :cond_0

    iget-object p1, p0, Lmiui/accounts/ExtraAccountManager;->qe:Landroid/os/Handler;

    .line 208
    :cond_0
    new-instance v0, Lmiui/accounts/ExtraAccountManager$1;

    move-object v1, p0

    move v2, p6

    move-object v3, p2

    move-object v4, p3

    move v5, p4

    move-object v6, p5

    invoke-direct/range {v0 .. v6}, Lmiui/accounts/ExtraAccountManager$1;-><init>(Lmiui/accounts/ExtraAccountManager;ZLmiui/accounts/MiuiOnAccountsUpdateListener;Landroid/accounts/Account;ILandroid/os/Bundle;)V

    invoke-virtual {p1, v0}, Landroid/os/Handler;->post(Ljava/lang/Runnable;)Z

    .line 223
    return-void
.end method

.method private a(Landroid/os/Handler;Lmiui/accounts/MiuiOnAccountsUpdateListener;[Landroid/accounts/Account;)V
    .locals 3

    .prologue
    const/4 v2, 0x0

    .line 185
    array-length v0, p3

    new-array v0, v0, [Landroid/accounts/Account;

    .line 188
    array-length v1, v0

    invoke-static {p3, v2, v0, v2, v1}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    .line 189
    if-nez p1, :cond_0

    iget-object p1, p0, Lmiui/accounts/ExtraAccountManager;->qe:Landroid/os/Handler;

    .line 190
    :cond_0
    new-instance v1, Lmiui/accounts/ExtraAccountManager$2;

    invoke-direct {v1, p0, p2, v0}, Lmiui/accounts/ExtraAccountManager$2;-><init>(Lmiui/accounts/ExtraAccountManager;Lmiui/accounts/MiuiOnAccountsUpdateListener;[Landroid/accounts/Account;)V

    invoke-virtual {p1, v1}, Landroid/os/Handler;->post(Ljava/lang/Runnable;)Z

    .line 201
    return-void
.end method

.method static synthetic a(Lmiui/accounts/ExtraAccountManager;Landroid/os/Handler;Lmiui/accounts/MiuiOnAccountsUpdateListener;Landroid/accounts/Account;ILandroid/os/Bundle;Z)V
    .locals 0

    .prologue
    .line 25
    invoke-direct/range {p0 .. p6}, Lmiui/accounts/ExtraAccountManager;->a(Landroid/os/Handler;Lmiui/accounts/MiuiOnAccountsUpdateListener;Landroid/accounts/Account;ILandroid/os/Bundle;Z)V

    return-void
.end method

.method static synthetic a(Lmiui/accounts/ExtraAccountManager;Landroid/os/Handler;Lmiui/accounts/MiuiOnAccountsUpdateListener;[Landroid/accounts/Account;)V
    .locals 0

    .prologue
    .line 25
    invoke-direct {p0, p1, p2, p3}, Lmiui/accounts/ExtraAccountManager;->a(Landroid/os/Handler;Lmiui/accounts/MiuiOnAccountsUpdateListener;[Landroid/accounts/Account;)V

    return-void
.end method

.method static synthetic b(Lmiui/accounts/ExtraAccountManager;)Landroid/accounts/AccountManager;
    .locals 1

    .prologue
    .line 25
    iget-object v0, p0, Lmiui/accounts/ExtraAccountManager;->qd:Landroid/accounts/AccountManager;

    return-object v0
.end method

.method public static getInstance(Landroid/content/Context;)Lmiui/accounts/ExtraAccountManager;
    .locals 1

    .prologue
    .line 118
    sget-object v0, Lmiui/accounts/ExtraAccountManager;->f:Lmiui/util/SoftReferenceSingleton;

    invoke-virtual {v0}, Lmiui/util/SoftReferenceSingleton;->get()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/accounts/ExtraAccountManager;

    return-object v0
.end method

.method public static getXiaomiAccount(Landroid/content/Context;)Landroid/accounts/Account;
    .locals 3

    .prologue
    .line 267
    const/4 v0, 0x0

    .line 268
    invoke-static {p0}, Landroid/accounts/AccountManager;->get(Landroid/content/Context;)Landroid/accounts/AccountManager;

    move-result-object v1

    .line 269
    const-string v2, "com.xiaomi"

    invoke-virtual {v1, v2}, Landroid/accounts/AccountManager;->getAccountsByType(Ljava/lang/String;)[Landroid/accounts/Account;

    move-result-object v1

    .line 270
    array-length v2, v1

    if-lez v2, :cond_0

    .line 271
    const/4 v0, 0x0

    aget-object v0, v1, v0

    .line 273
    :cond_0
    return-object v0
.end method


# virtual methods
.method public addOnAccountsUpdatedListener(Lmiui/accounts/MiuiOnAccountsUpdateListener;Landroid/os/Handler;Z)V
    .locals 4

    .prologue
    .line 130
    if-nez p1, :cond_0

    .line 131
    new-instance v0, Ljava/lang/IllegalArgumentException;

    const-string v1, "the listener is null"

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 133
    :cond_0
    iget-object v1, p0, Lmiui/accounts/ExtraAccountManager;->qf:Ljava/util/HashMap;

    monitor-enter v1

    .line 134
    :try_start_0
    iget-object v0, p0, Lmiui/accounts/ExtraAccountManager;->qf:Ljava/util/HashMap;

    invoke-virtual {v0, p1}, Ljava/util/HashMap;->containsKey(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_1

    .line 135
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v2, "this listener is already added"

    invoke-direct {v0, v2}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 154
    :catchall_0
    move-exception v0

    monitor-exit v1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw v0

    .line 138
    :cond_1
    :try_start_1
    iget-object v0, p0, Lmiui/accounts/ExtraAccountManager;->qf:Ljava/util/HashMap;

    invoke-virtual {v0}, Ljava/util/HashMap;->isEmpty()Z

    move-result v0

    .line 140
    iget-object v2, p0, Lmiui/accounts/ExtraAccountManager;->qf:Ljava/util/HashMap;

    invoke-virtual {v2, p1, p2}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 142
    if-eqz v0, :cond_2

    .line 144
    new-instance v0, Landroid/content/IntentFilter;

    invoke-direct {v0}, Landroid/content/IntentFilter;-><init>()V

    .line 145
    const-string v2, "android.accounts.LOGIN_ACCOUNTS_CHANGED"

    invoke-virtual {v0, v2}, Landroid/content/IntentFilter;->addAction(Ljava/lang/String;)V

    .line 147
    const-string v2, "android.accounts.LOGIN_ACCOUNTS_PRE_CHANGED"

    invoke-virtual {v0, v2}, Landroid/content/IntentFilter;->addAction(Ljava/lang/String;)V

    .line 148
    const-string v2, "android.accounts.LOGIN_ACCOUNTS_POST_CHANGED"

    invoke-virtual {v0, v2}, Landroid/content/IntentFilter;->addAction(Ljava/lang/String;)V

    .line 150
    const-string v2, "android.intent.action.DEVICE_STORAGE_OK"

    invoke-virtual {v0, v2}, Landroid/content/IntentFilter;->addAction(Ljava/lang/String;)V

    .line 151
    iget-object v2, p0, Lmiui/accounts/ExtraAccountManager;->mContext:Landroid/content/Context;

    iget-object v3, p0, Lmiui/accounts/ExtraAccountManager;->qg:Landroid/content/BroadcastReceiver;

    invoke-virtual {v2, v3, v0}, Landroid/content/Context;->registerReceiver(Landroid/content/BroadcastReceiver;Landroid/content/IntentFilter;)Landroid/content/Intent;

    .line 154
    :cond_2
    monitor-exit v1
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 156
    if-eqz p3, :cond_3

    .line 157
    iget-object v0, p0, Lmiui/accounts/ExtraAccountManager;->qd:Landroid/accounts/AccountManager;

    invoke-virtual {v0}, Landroid/accounts/AccountManager;->getAccounts()[Landroid/accounts/Account;

    move-result-object v0

    invoke-direct {p0, p2, p1, v0}, Lmiui/accounts/ExtraAccountManager;->a(Landroid/os/Handler;Lmiui/accounts/MiuiOnAccountsUpdateListener;[Landroid/accounts/Account;)V

    .line 159
    :cond_3
    return-void
.end method

.method public removeOnAccountsUpdatedListener(Lmiui/accounts/MiuiOnAccountsUpdateListener;)V
    .locals 3

    .prologue
    .line 167
    if-nez p1, :cond_0

    .line 168
    new-instance v0, Ljava/lang/IllegalArgumentException;

    const-string v1, "listener is null"

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 170
    :cond_0
    iget-object v1, p0, Lmiui/accounts/ExtraAccountManager;->qf:Ljava/util/HashMap;

    monitor-enter v1

    .line 171
    :try_start_0
    iget-object v0, p0, Lmiui/accounts/ExtraAccountManager;->qf:Ljava/util/HashMap;

    invoke-virtual {v0, p1}, Ljava/util/HashMap;->containsKey(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_1

    .line 172
    const-string v0, "ExtraAccountManager"

    const-string v2, "Listener was not previously added"

    invoke-static {v0, v2}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 173
    monitor-exit v1

    .line 180
    :goto_0
    return-void

    .line 175
    :cond_1
    iget-object v0, p0, Lmiui/accounts/ExtraAccountManager;->qf:Ljava/util/HashMap;

    invoke-virtual {v0, p1}, Ljava/util/HashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    .line 176
    iget-object v0, p0, Lmiui/accounts/ExtraAccountManager;->qf:Ljava/util/HashMap;

    invoke-virtual {v0}, Ljava/util/HashMap;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_2

    .line 177
    iget-object v0, p0, Lmiui/accounts/ExtraAccountManager;->mContext:Landroid/content/Context;

    iget-object v2, p0, Lmiui/accounts/ExtraAccountManager;->qg:Landroid/content/BroadcastReceiver;

    invoke-virtual {v0, v2}, Landroid/content/Context;->unregisterReceiver(Landroid/content/BroadcastReceiver;)V

    .line 179
    :cond_2
    monitor-exit v1

    goto :goto_0

    :catchall_0
    move-exception v0

    monitor-exit v1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw v0
.end method
