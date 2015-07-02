.class public Lcom/miui/internal/variable/v16/Android_Os_Process_class;
.super Lcom/miui/internal/variable/Android_Os_Process_class;
.source "SourceFile"


# static fields
.field private static final oZ:Lmiui/reflect/Method;

.field private static final pa:Lmiui/reflect/Method;


# direct methods
.method static constructor <clinit>()V
    .locals 3

    .prologue
    .line 16
    const-class v0, Landroid/os/Process;

    const-string v1, "getTotalMemory"

    const-string v2, "()J"

    invoke-static {v0, v1, v2}, Lmiui/reflect/Method;->of(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lmiui/reflect/Method;

    move-result-object v0

    sput-object v0, Lcom/miui/internal/variable/v16/Android_Os_Process_class;->oZ:Lmiui/reflect/Method;

    .line 19
    const-class v0, Landroid/os/Process;

    const-string v1, "getFreeMemory"

    const-string v2, "()J"

    invoke-static {v0, v1, v2}, Lmiui/reflect/Method;->of(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lmiui/reflect/Method;

    move-result-object v0

    sput-object v0, Lcom/miui/internal/variable/v16/Android_Os_Process_class;->pa:Lmiui/reflect/Method;

    return-void
.end method

.method public constructor <init>()V
    .locals 0

    .prologue
    .line 14
    invoke-direct {p0}, Lcom/miui/internal/variable/Android_Os_Process_class;-><init>()V

    return-void
.end method


# virtual methods
.method public getFreeMemory()J
    .locals 4

    .prologue
    .line 35
    :try_start_0
    sget-object v0, Lcom/miui/internal/variable/v16/Android_Os_Process_class;->pa:Lmiui/reflect/Method;

    const/4 v1, 0x0

    const/4 v2, 0x0

    const/4 v3, 0x0

    new-array v3, v3, [Ljava/lang/Object;

    invoke-virtual {v0, v1, v2, v3}, Lmiui/reflect/Method;->invokeLong(Ljava/lang/Class;Ljava/lang/Object;[Ljava/lang/Object;)J
    :try_end_0
    .catch Ljava/lang/RuntimeException; {:try_start_0 .. :try_end_0} :catch_0

    move-result-wide v0

    .line 39
    :goto_0
    return-wide v0

    .line 36
    :catch_0
    move-exception v0

    .line 37
    invoke-static {}, Lcom/miui/internal/variable/VariableExceptionHandler;->getInstance()Lcom/miui/internal/variable/VariableExceptionHandler;

    move-result-object v1

    const-string v2, "android.os.Process.getFreeMemory"

    invoke-virtual {v1, v2, v0}, Lcom/miui/internal/variable/VariableExceptionHandler;->onThrow(Ljava/lang/String;Ljava/lang/Throwable;)V

    .line 39
    const-wide/16 v0, 0x0

    goto :goto_0
.end method

.method public getTotalMemory()J
    .locals 4

    .prologue
    .line 25
    :try_start_0
    sget-object v0, Lcom/miui/internal/variable/v16/Android_Os_Process_class;->oZ:Lmiui/reflect/Method;

    const/4 v1, 0x0

    const/4 v2, 0x0

    const/4 v3, 0x0

    new-array v3, v3, [Ljava/lang/Object;

    invoke-virtual {v0, v1, v2, v3}, Lmiui/reflect/Method;->invokeLong(Ljava/lang/Class;Ljava/lang/Object;[Ljava/lang/Object;)J
    :try_end_0
    .catch Ljava/lang/RuntimeException; {:try_start_0 .. :try_end_0} :catch_0

    move-result-wide v0

    .line 29
    :goto_0
    return-wide v0

    .line 26
    :catch_0
    move-exception v0

    .line 27
    invoke-static {}, Lcom/miui/internal/variable/VariableExceptionHandler;->getInstance()Lcom/miui/internal/variable/VariableExceptionHandler;

    move-result-object v1

    const-string v2, "android.os.Process.getTotalMemory"

    invoke-virtual {v1, v2, v0}, Lcom/miui/internal/variable/VariableExceptionHandler;->onThrow(Ljava/lang/String;Ljava/lang/Throwable;)V

    .line 29
    const-wide/16 v0, 0x0

    goto :goto_0
.end method
