.class public Lcom/miui/internal/variable/v16/Android_Content_ContentResolver_class;
.super Lcom/miui/internal/variable/Android_Content_ContentResolver_class;
.source "SourceFile"


# static fields
.field private static final FA:Ljava/lang/Class;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/lang/Class",
            "<*>;"
        }
    .end annotation
.end field

.field private static final Mu:Lmiui/reflect/Field;

.field private static final Mv:Lmiui/reflect/Field;

.field private static final Mw:Lmiui/reflect/Method;


# direct methods
.method static constructor <clinit>()V
    .locals 4

    .prologue
    .line 19
    const/4 v0, 0x0

    .line 21
    :try_start_0
    const-string v1, "android.content.ContentResolver"

    invoke-static {v1}, Ljava/lang/Class;->forName(Ljava/lang/String;)Ljava/lang/Class;
    :try_end_0
    .catch Ljava/lang/ClassNotFoundException; {:try_start_0 .. :try_end_0} :catch_0

    move-result-object v0

    .line 25
    :goto_0
    sput-object v0, Lcom/miui/internal/variable/v16/Android_Content_ContentResolver_class;->FA:Ljava/lang/Class;

    .line 28
    sget-object v0, Lcom/miui/internal/variable/v16/Android_Content_ContentResolver_class;->FA:Ljava/lang/Class;

    const-string v1, "SYNC_ERROR_SYNC_ALREADY_IN_PROGRESS"

    const-string v2, "I"

    invoke-static {v0, v1, v2}, Lmiui/reflect/Field;->of(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lmiui/reflect/Field;

    move-result-object v0

    sput-object v0, Lcom/miui/internal/variable/v16/Android_Content_ContentResolver_class;->Mu:Lmiui/reflect/Field;

    .line 31
    sget-object v0, Lcom/miui/internal/variable/v16/Android_Content_ContentResolver_class;->FA:Ljava/lang/Class;

    const-string v1, "SYNC_OBSERVER_TYPE_STATUS"

    const-string v2, "I"

    invoke-static {v0, v1, v2}, Lmiui/reflect/Field;->of(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lmiui/reflect/Field;

    move-result-object v0

    sput-object v0, Lcom/miui/internal/variable/v16/Android_Content_ContentResolver_class;->Mv:Lmiui/reflect/Field;

    .line 34
    sget-object v0, Lcom/miui/internal/variable/v16/Android_Content_ContentResolver_class;->FA:Ljava/lang/Class;

    const-string v1, "getSyncStatus"

    const-string v2, "(Landroid/accounts/Account;Ljava/lang/String;)Landroid/content/SyncStatusInfo;"

    invoke-static {v0, v1, v2}, Lmiui/reflect/Method;->of(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lmiui/reflect/Method;

    move-result-object v0

    sput-object v0, Lcom/miui/internal/variable/v16/Android_Content_ContentResolver_class;->Mw:Lmiui/reflect/Method;

    return-void

    .line 22
    :catch_0
    move-exception v1

    .line 23
    invoke-static {}, Lcom/miui/internal/variable/VariableExceptionHandler;->getInstance()Lcom/miui/internal/variable/VariableExceptionHandler;

    move-result-object v2

    const-string v3, "android.content.ContentResolver"

    invoke-virtual {v2, v3, v1}, Lcom/miui/internal/variable/VariableExceptionHandler;->onThrow(Ljava/lang/String;Ljava/lang/Throwable;)V

    goto :goto_0
.end method

.method public constructor <init>()V
    .locals 0

    .prologue
    .line 13
    invoke-direct {p0}, Lcom/miui/internal/variable/Android_Content_ContentResolver_class;-><init>()V

    return-void
.end method


# virtual methods
.method public getSyncErrorSyncAlreadyInProgressConstants()I
    .locals 3

    .prologue
    .line 40
    :try_start_0
    sget-object v0, Lcom/miui/internal/variable/v16/Android_Content_ContentResolver_class;->Mu:Lmiui/reflect/Field;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Lmiui/reflect/Field;->getInt(Ljava/lang/Object;)I
    :try_end_0
    .catch Ljava/lang/RuntimeException; {:try_start_0 .. :try_end_0} :catch_0

    move-result v0

    .line 44
    :goto_0
    return v0

    .line 41
    :catch_0
    move-exception v0

    .line 42
    invoke-static {}, Lcom/miui/internal/variable/VariableExceptionHandler;->getInstance()Lcom/miui/internal/variable/VariableExceptionHandler;

    move-result-object v1

    const-string v2, "android.content.ContentResolver.SYNC_ERROR_SYNC_ALREADY_IN_PROGRESS"

    invoke-virtual {v1, v2, v0}, Lcom/miui/internal/variable/VariableExceptionHandler;->onThrow(Ljava/lang/String;Ljava/lang/Throwable;)V

    .line 44
    const/4 v0, 0x1

    goto :goto_0
.end method

.method public getSyncObserverTypeStatusConstants()I
    .locals 3

    .prologue
    .line 50
    :try_start_0
    sget-object v0, Lcom/miui/internal/variable/v16/Android_Content_ContentResolver_class;->Mv:Lmiui/reflect/Field;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Lmiui/reflect/Field;->getInt(Ljava/lang/Object;)I
    :try_end_0
    .catch Ljava/lang/RuntimeException; {:try_start_0 .. :try_end_0} :catch_0

    move-result v0

    .line 54
    :goto_0
    return v0

    .line 51
    :catch_0
    move-exception v0

    .line 52
    invoke-static {}, Lcom/miui/internal/variable/VariableExceptionHandler;->getInstance()Lcom/miui/internal/variable/VariableExceptionHandler;

    move-result-object v1

    const-string v2, "android.content.ContentResolver.SYNC_OBSERVER_TYPE_STATUS"

    invoke-virtual {v1, v2, v0}, Lcom/miui/internal/variable/VariableExceptionHandler;->onThrow(Ljava/lang/String;Ljava/lang/Throwable;)V

    .line 54
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public getSyncStatus(Landroid/accounts/Account;Ljava/lang/String;)Ljava/lang/Object;
    .locals 6

    .prologue
    const/4 v0, 0x0

    .line 60
    :try_start_0
    sget-object v1, Lcom/miui/internal/variable/v16/Android_Content_ContentResolver_class;->Mw:Lmiui/reflect/Method;

    const/4 v2, 0x0

    const/4 v3, 0x0

    const/4 v4, 0x2

    new-array v4, v4, [Ljava/lang/Object;

    const/4 v5, 0x0

    aput-object p1, v4, v5

    const/4 v5, 0x1

    aput-object p2, v4, v5

    invoke-virtual {v1, v2, v3, v4}, Lmiui/reflect/Method;->invokeObject(Ljava/lang/Class;Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    :try_end_0
    .catch Ljava/lang/RuntimeException; {:try_start_0 .. :try_end_0} :catch_0

    move-result-object v0

    .line 64
    :goto_0
    return-object v0

    .line 61
    :catch_0
    move-exception v1

    .line 62
    invoke-static {}, Lcom/miui/internal/variable/VariableExceptionHandler;->getInstance()Lcom/miui/internal/variable/VariableExceptionHandler;

    move-result-object v2

    const-string v3, "android.content.ContentResolver.getSyncStatus"

    invoke-virtual {v2, v3, v1}, Lcom/miui/internal/variable/VariableExceptionHandler;->onThrow(Ljava/lang/String;Ljava/lang/Throwable;)V

    goto :goto_0
.end method
