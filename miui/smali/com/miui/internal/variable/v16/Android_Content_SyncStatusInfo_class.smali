.class public Lcom/miui/internal/variable/v16/Android_Content_SyncStatusInfo_class;
.super Lcom/miui/internal/variable/Android_Content_SyncStatusInfo_class;
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

.field private static final FY:Lmiui/reflect/Field;

.field private static final FZ:Lmiui/reflect/Field;

.field private static final Ga:Lmiui/reflect/Field;

.field private static final Gb:Lmiui/reflect/Field;

.field private static final Gc:Lmiui/reflect/Method;


# direct methods
.method static constructor <clinit>()V
    .locals 4

    .prologue
    .line 21
    const/4 v0, 0x0

    .line 23
    :try_start_0
    const-string v1, "android.content.SyncStatusInfo"

    invoke-static {v1}, Ljava/lang/Class;->forName(Ljava/lang/String;)Ljava/lang/Class;
    :try_end_0
    .catch Ljava/lang/ClassNotFoundException; {:try_start_0 .. :try_end_0} :catch_0

    move-result-object v0

    .line 27
    :goto_0
    sput-object v0, Lcom/miui/internal/variable/v16/Android_Content_SyncStatusInfo_class;->FA:Ljava/lang/Class;

    .line 30
    sget-object v0, Lcom/miui/internal/variable/v16/Android_Content_SyncStatusInfo_class;->FA:Ljava/lang/Class;

    const-string v1, "pending"

    const-string v2, "Z"

    invoke-static {v0, v1, v2}, Lmiui/reflect/Field;->of(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lmiui/reflect/Field;

    move-result-object v0

    sput-object v0, Lcom/miui/internal/variable/v16/Android_Content_SyncStatusInfo_class;->FY:Lmiui/reflect/Field;

    .line 33
    sget-object v0, Lcom/miui/internal/variable/v16/Android_Content_SyncStatusInfo_class;->FA:Ljava/lang/Class;

    const-string v1, "initialize"

    const-string v2, "Z"

    invoke-static {v0, v1, v2}, Lmiui/reflect/Field;->of(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lmiui/reflect/Field;

    move-result-object v0

    sput-object v0, Lcom/miui/internal/variable/v16/Android_Content_SyncStatusInfo_class;->FZ:Lmiui/reflect/Field;

    .line 36
    sget-object v0, Lcom/miui/internal/variable/v16/Android_Content_SyncStatusInfo_class;->FA:Ljava/lang/Class;

    const-string v1, "lastSuccessTime"

    const-string v2, "J"

    invoke-static {v0, v1, v2}, Lmiui/reflect/Field;->of(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lmiui/reflect/Field;

    move-result-object v0

    sput-object v0, Lcom/miui/internal/variable/v16/Android_Content_SyncStatusInfo_class;->Ga:Lmiui/reflect/Field;

    .line 39
    sget-object v0, Lcom/miui/internal/variable/v16/Android_Content_SyncStatusInfo_class;->FA:Ljava/lang/Class;

    const-string v1, "lastFailureTime"

    const-string v2, "J"

    invoke-static {v0, v1, v2}, Lmiui/reflect/Field;->of(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lmiui/reflect/Field;

    move-result-object v0

    sput-object v0, Lcom/miui/internal/variable/v16/Android_Content_SyncStatusInfo_class;->Gb:Lmiui/reflect/Field;

    .line 42
    sget-object v0, Lcom/miui/internal/variable/v16/Android_Content_SyncStatusInfo_class;->FA:Ljava/lang/Class;

    const-string v1, "getLastFailureMesgAsInt"

    const-string v2, "(I)I"

    invoke-static {v0, v1, v2}, Lmiui/reflect/Method;->of(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lmiui/reflect/Method;

    move-result-object v0

    sput-object v0, Lcom/miui/internal/variable/v16/Android_Content_SyncStatusInfo_class;->Gc:Lmiui/reflect/Method;

    return-void

    .line 24
    :catch_0
    move-exception v1

    .line 25
    invoke-static {}, Lcom/miui/internal/variable/VariableExceptionHandler;->getInstance()Lcom/miui/internal/variable/VariableExceptionHandler;

    move-result-object v2

    const-string v3, "android.content.SyncStatusInfo"

    invoke-virtual {v2, v3, v1}, Lcom/miui/internal/variable/VariableExceptionHandler;->onThrow(Ljava/lang/String;Ljava/lang/Throwable;)V

    goto :goto_0
.end method

.method public constructor <init>()V
    .locals 0

    .prologue
    .line 15
    invoke-direct {p0}, Lcom/miui/internal/variable/Android_Content_SyncStatusInfo_class;-><init>()V

    return-void
.end method


# virtual methods
.method public getInitialize(Ljava/lang/Object;)Z
    .locals 3

    .prologue
    .line 58
    :try_start_0
    sget-object v0, Lcom/miui/internal/variable/v16/Android_Content_SyncStatusInfo_class;->FZ:Lmiui/reflect/Field;

    invoke-virtual {v0, p1}, Lmiui/reflect/Field;->getBoolean(Ljava/lang/Object;)Z
    :try_end_0
    .catch Ljava/lang/RuntimeException; {:try_start_0 .. :try_end_0} :catch_0

    move-result v0

    .line 62
    :goto_0
    return v0

    .line 59
    :catch_0
    move-exception v0

    .line 60
    invoke-static {}, Lcom/miui/internal/variable/VariableExceptionHandler;->getInstance()Lcom/miui/internal/variable/VariableExceptionHandler;

    move-result-object v1

    const-string v2, "android.content.SyncStatusInfo.initialize"

    invoke-virtual {v1, v2, v0}, Lcom/miui/internal/variable/VariableExceptionHandler;->onThrow(Ljava/lang/String;Ljava/lang/Throwable;)V

    .line 62
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public getLastFailureMesgAsInt(Ljava/lang/Object;I)I
    .locals 5

    .prologue
    .line 88
    :try_start_0
    sget-object v0, Lcom/miui/internal/variable/v16/Android_Content_SyncStatusInfo_class;->Gc:Lmiui/reflect/Method;

    const/4 v1, 0x0

    const/4 v2, 0x1

    new-array v2, v2, [Ljava/lang/Object;

    const/4 v3, 0x0

    invoke-static {p2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v4

    aput-object v4, v2, v3

    invoke-virtual {v0, v1, p1, v2}, Lmiui/reflect/Method;->invokeInt(Ljava/lang/Class;Ljava/lang/Object;[Ljava/lang/Object;)I
    :try_end_0
    .catch Ljava/lang/RuntimeException; {:try_start_0 .. :try_end_0} :catch_0

    move-result p2

    .line 92
    :goto_0
    return p2

    .line 89
    :catch_0
    move-exception v0

    .line 90
    invoke-static {}, Lcom/miui/internal/variable/VariableExceptionHandler;->getInstance()Lcom/miui/internal/variable/VariableExceptionHandler;

    move-result-object v1

    const-string v2, "android.content.SyncStatusInfo.getLastFailureMesgAsInt"

    invoke-virtual {v1, v2, v0}, Lcom/miui/internal/variable/VariableExceptionHandler;->onThrow(Ljava/lang/String;Ljava/lang/Throwable;)V

    goto :goto_0
.end method

.method public getLastFailureTime(Ljava/lang/Object;)J
    .locals 3

    .prologue
    .line 78
    :try_start_0
    sget-object v0, Lcom/miui/internal/variable/v16/Android_Content_SyncStatusInfo_class;->Gb:Lmiui/reflect/Field;

    invoke-virtual {v0, p1}, Lmiui/reflect/Field;->getLong(Ljava/lang/Object;)J
    :try_end_0
    .catch Ljava/lang/RuntimeException; {:try_start_0 .. :try_end_0} :catch_0

    move-result-wide v0

    .line 82
    :goto_0
    return-wide v0

    .line 79
    :catch_0
    move-exception v0

    .line 80
    invoke-static {}, Lcom/miui/internal/variable/VariableExceptionHandler;->getInstance()Lcom/miui/internal/variable/VariableExceptionHandler;

    move-result-object v1

    const-string v2, "android.content.SyncStatusInfo.lastFailureTime"

    invoke-virtual {v1, v2, v0}, Lcom/miui/internal/variable/VariableExceptionHandler;->onThrow(Ljava/lang/String;Ljava/lang/Throwable;)V

    .line 82
    const-wide/16 v0, 0x0

    goto :goto_0
.end method

.method public getLastSuccessTime(Ljava/lang/Object;)J
    .locals 3

    .prologue
    .line 68
    :try_start_0
    sget-object v0, Lcom/miui/internal/variable/v16/Android_Content_SyncStatusInfo_class;->Ga:Lmiui/reflect/Field;

    invoke-virtual {v0, p1}, Lmiui/reflect/Field;->getLong(Ljava/lang/Object;)J
    :try_end_0
    .catch Ljava/lang/RuntimeException; {:try_start_0 .. :try_end_0} :catch_0

    move-result-wide v0

    .line 72
    :goto_0
    return-wide v0

    .line 69
    :catch_0
    move-exception v0

    .line 70
    invoke-static {}, Lcom/miui/internal/variable/VariableExceptionHandler;->getInstance()Lcom/miui/internal/variable/VariableExceptionHandler;

    move-result-object v1

    const-string v2, "android.content.SyncStatusInfo.lastSuccessTime"

    invoke-virtual {v1, v2, v0}, Lcom/miui/internal/variable/VariableExceptionHandler;->onThrow(Ljava/lang/String;Ljava/lang/Throwable;)V

    .line 72
    const-wide/16 v0, 0x0

    goto :goto_0
.end method

.method public getPending(Ljava/lang/Object;)Z
    .locals 3

    .prologue
    .line 48
    :try_start_0
    sget-object v0, Lcom/miui/internal/variable/v16/Android_Content_SyncStatusInfo_class;->FY:Lmiui/reflect/Field;

    invoke-virtual {v0, p1}, Lmiui/reflect/Field;->getBoolean(Ljava/lang/Object;)Z
    :try_end_0
    .catch Ljava/lang/RuntimeException; {:try_start_0 .. :try_end_0} :catch_0

    move-result v0

    .line 52
    :goto_0
    return v0

    .line 49
    :catch_0
    move-exception v0

    .line 50
    invoke-static {}, Lcom/miui/internal/variable/VariableExceptionHandler;->getInstance()Lcom/miui/internal/variable/VariableExceptionHandler;

    move-result-object v1

    const-string v2, "android.content.SyncStatusInfo.pending"

    invoke-virtual {v1, v2, v0}, Lcom/miui/internal/variable/VariableExceptionHandler;->onThrow(Ljava/lang/String;Ljava/lang/Throwable;)V

    .line 52
    const/4 v0, 0x0

    goto :goto_0
.end method
