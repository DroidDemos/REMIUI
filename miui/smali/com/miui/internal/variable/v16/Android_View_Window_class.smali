.class public Lcom/miui/internal/variable/v16/Android_View_Window_class;
.super Lcom/miui/internal/variable/Android_View_Window_class;
.source "SourceFile"


# static fields
.field private static final yG:I = 0x1

.field private static final yH:I = 0x10

.field private static final yI:I = 0x11


# direct methods
.method public constructor <init>()V
    .locals 0

    .prologue
    .line 13
    invoke-direct {p0}, Lcom/miui/internal/variable/Android_View_Window_class;-><init>()V

    return-void
.end method


# virtual methods
.method public setTranslucentStatus(Landroid/view/Window;I)Z
    .locals 7

    .prologue
    const/16 v0, 0x11

    const/4 v1, 0x1

    const/4 v2, 0x0

    .line 24
    sget-object v3, Lcom/miui/internal/variable/v16/Android_View_Window_class;->setExtraFlags:Lmiui/reflect/Method;

    if-nez v3, :cond_0

    .line 47
    :goto_0
    return v2

    .line 29
    :cond_0
    if-nez p2, :cond_1

    .line 31
    :try_start_0
    sget-object v0, Lcom/miui/internal/variable/v16/Android_View_Window_class;->setExtraFlags:Lmiui/reflect/Method;

    const/4 v3, 0x0

    const/4 v4, 0x2

    new-array v4, v4, [Ljava/lang/Object;

    const/4 v5, 0x0

    const/4 v6, 0x0

    invoke-static {v6}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v6

    aput-object v6, v4, v5

    const/4 v5, 0x1

    const/16 v6, 0x11

    invoke-static {v6}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v6

    aput-object v6, v4, v5

    invoke-virtual {v0, v3, p1, v4}, Lmiui/reflect/Method;->invoke(Ljava/lang/Class;Ljava/lang/Object;[Ljava/lang/Object;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    :goto_1
    move v2, v1

    .line 47
    goto :goto_0

    .line 32
    :catch_0
    move-exception v0

    .line 33
    invoke-static {}, Lcom/miui/internal/variable/VariableExceptionHandler;->getInstance()Lcom/miui/internal/variable/VariableExceptionHandler;

    move-result-object v1

    const-string v3, "clearExtraFlags failed"

    invoke-virtual {v1, v3, v0}, Lcom/miui/internal/variable/VariableExceptionHandler;->onThrow(Ljava/lang/String;Ljava/lang/Throwable;)V

    move v1, v2

    .line 35
    goto :goto_1

    .line 37
    :cond_1
    if-ne p2, v1, :cond_2

    .line 40
    :goto_2
    :try_start_1
    sget-object v3, Lcom/miui/internal/variable/v16/Android_View_Window_class;->setExtraFlags:Lmiui/reflect/Method;

    const/4 v4, 0x0

    const/4 v5, 0x2

    new-array v5, v5, [Ljava/lang/Object;

    const/4 v6, 0x0

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    aput-object v0, v5, v6

    const/4 v0, 0x1

    const/16 v6, 0x11

    invoke-static {v6}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v6

    aput-object v6, v5, v0

    invoke-virtual {v3, v4, p1, v5}, Lmiui/reflect/Method;->invoke(Ljava/lang/Class;Ljava/lang/Object;[Ljava/lang/Object;)V
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_1

    goto :goto_1

    .line 41
    :catch_1
    move-exception v0

    .line 42
    invoke-static {}, Lcom/miui/internal/variable/VariableExceptionHandler;->getInstance()Lcom/miui/internal/variable/VariableExceptionHandler;

    move-result-object v1

    const-string v3, "addExtraFlags failed"

    invoke-virtual {v1, v3, v0}, Lcom/miui/internal/variable/VariableExceptionHandler;->onThrow(Ljava/lang/String;Ljava/lang/Throwable;)V

    move v1, v2

    .line 43
    goto :goto_1

    :cond_2
    move v0, v1

    .line 37
    goto :goto_2
.end method
