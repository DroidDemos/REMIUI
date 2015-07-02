.class public Lcom/miui/internal/variable/v16/Internal_Policy_Impl_PhoneWindow_class;
.super Lcom/miui/internal/variable/Internal_Policy_Impl_PhoneWindow_class;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/internal/variable/v16/Internal_Policy_Impl_PhoneWindow_class$1;,
        Lcom/miui/internal/variable/v16/Internal_Policy_Impl_PhoneWindow_class$a;
    }
.end annotation


# static fields
.field private static final rD:Ljava/lang/reflect/Field;


# direct methods
.method static constructor <clinit>()V
    .locals 2

    .prologue
    .line 20
    sget-object v0, Lcom/miui/internal/variable/v16/Internal_Policy_Impl_PhoneWindow_class;->TARGET_CLASS:Ljava/lang/Class;

    const-string v1, "mLayoutInflater"

    invoke-static {v0, v1}, Lcom/miui/internal/variable/v16/Internal_Policy_Impl_PhoneWindow_class;->a(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/reflect/Field;

    move-result-object v0

    sput-object v0, Lcom/miui/internal/variable/v16/Internal_Policy_Impl_PhoneWindow_class;->rD:Ljava/lang/reflect/Field;

    return-void
.end method

.method public constructor <init>()V
    .locals 0

    .prologue
    .line 17
    invoke-direct {p0}, Lcom/miui/internal/variable/Internal_Policy_Impl_PhoneWindow_class;-><init>()V

    .line 34
    return-void
.end method

.method private a(Landroid/view/Window;)Lcom/miui/internal/variable/v16/Internal_Policy_Impl_PhoneWindow_class$a;
    .locals 4

    .prologue
    const/4 v2, 0x0

    .line 61
    :try_start_0
    sget-object v0, Lcom/miui/internal/variable/v16/Internal_Policy_Impl_PhoneWindow_class;->rD:Ljava/lang/reflect/Field;

    invoke-virtual {v0, p1}, Ljava/lang/reflect/Field;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/view/LayoutInflater;

    .line 62
    new-instance v1, Lcom/miui/internal/variable/v16/Internal_Policy_Impl_PhoneWindow_class$a;

    const/4 v3, 0x0

    invoke-direct {v1, v0, p1, v3}, Lcom/miui/internal/variable/v16/Internal_Policy_Impl_PhoneWindow_class$a;-><init>(Landroid/view/LayoutInflater;Landroid/view/Window;Lcom/miui/internal/variable/v16/Internal_Policy_Impl_PhoneWindow_class$1;)V

    .line 63
    sget-object v0, Lcom/miui/internal/variable/v16/Internal_Policy_Impl_PhoneWindow_class;->rD:Ljava/lang/reflect/Field;

    invoke-virtual {v0, p1, v1}, Ljava/lang/reflect/Field;->set(Ljava/lang/Object;Ljava/lang/Object;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    move-object v0, v1

    .line 67
    :goto_0
    return-object v0

    .line 65
    :catch_0
    move-exception v0

    .line 66
    invoke-virtual {v0}, Ljava/lang/Exception;->printStackTrace()V

    move-object v0, v2

    .line 67
    goto :goto_0
.end method

.method private static a(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/reflect/Field;
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/Class",
            "<+",
            "Ljava/lang/Object;",
            ">;",
            "Ljava/lang/String;",
            ")",
            "Ljava/lang/reflect/Field;"
        }
    .end annotation

    .prologue
    .line 25
    :try_start_0
    invoke-virtual {p0, p1}, Ljava/lang/Class;->getDeclaredField(Ljava/lang/String;)Ljava/lang/reflect/Field;

    move-result-object v0

    .line 26
    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Ljava/lang/reflect/Field;->setAccessible(Z)V
    :try_end_0
    .catch Ljava/lang/NoSuchFieldException; {:try_start_0 .. :try_end_0} :catch_0

    .line 31
    :goto_0
    return-object v0

    .line 28
    :catch_0
    move-exception v0

    .line 29
    invoke-virtual {v0}, Ljava/lang/NoSuchFieldException;->printStackTrace()V

    .line 31
    const/4 v0, 0x0

    goto :goto_0
.end method

.method private a(Landroid/view/Window;Lcom/miui/internal/variable/v16/Internal_Policy_Impl_PhoneWindow_class$a;)V
    .locals 2

    .prologue
    .line 73
    :try_start_0
    sget-object v0, Lcom/miui/internal/variable/v16/Internal_Policy_Impl_PhoneWindow_class;->rD:Ljava/lang/reflect/Field;

    invoke-static {p2}, Lcom/miui/internal/variable/v16/Internal_Policy_Impl_PhoneWindow_class$a;->a(Lcom/miui/internal/variable/v16/Internal_Policy_Impl_PhoneWindow_class$a;)Landroid/view/LayoutInflater;

    move-result-object v1

    invoke-virtual {v0, p1, v1}, Ljava/lang/reflect/Field;->set(Ljava/lang/Object;Ljava/lang/Object;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    .line 77
    :goto_0
    return-void

    .line 74
    :catch_0
    move-exception v0

    .line 75
    invoke-virtual {v0}, Ljava/lang/Exception;->printStackTrace()V

    goto :goto_0
.end method


# virtual methods
.method public buildProxy()V
    .locals 2

    .prologue
    .line 82
    const-string v0, "installDecor"

    const-string v1, "()V"

    invoke-virtual {p0, v0, v1}, Lcom/miui/internal/variable/v16/Internal_Policy_Impl_PhoneWindow_class;->attachMethod(Ljava/lang/String;Ljava/lang/String;)J

    .line 83
    return-void
.end method

.method protected handle()V
    .locals 3

    .prologue
    .line 87
    const-wide/16 v0, 0x0

    const/4 v2, 0x0

    invoke-virtual {p0, v0, v1, v2}, Lcom/miui/internal/variable/v16/Internal_Policy_Impl_PhoneWindow_class;->handleInstallDecor(JLjava/lang/Object;)V

    .line 88
    return-void
.end method

.method protected handleInstallDecor(JLjava/lang/Object;)V
    .locals 2

    .prologue
    .line 96
    move-object v0, p3

    check-cast v0, Landroid/view/Window;

    .line 97
    invoke-virtual {v0}, Landroid/view/Window;->getContext()Landroid/content/Context;

    move-result-object v1

    invoke-static {v1}, Lmiui/os/Build;->isUsingMiui(Landroid/content/Context;)Z

    move-result v1

    .line 98
    if-eqz v1, :cond_0

    .line 99
    invoke-direct {p0, v0}, Lcom/miui/internal/variable/v16/Internal_Policy_Impl_PhoneWindow_class;->a(Landroid/view/Window;)Lcom/miui/internal/variable/v16/Internal_Policy_Impl_PhoneWindow_class$a;

    move-result-object v1

    .line 100
    invoke-virtual {p0, p1, p2, p3}, Lcom/miui/internal/variable/v16/Internal_Policy_Impl_PhoneWindow_class;->originalInstallDecor(JLjava/lang/Object;)V

    .line 101
    invoke-direct {p0, v0, v1}, Lcom/miui/internal/variable/v16/Internal_Policy_Impl_PhoneWindow_class;->a(Landroid/view/Window;Lcom/miui/internal/variable/v16/Internal_Policy_Impl_PhoneWindow_class$a;)V

    .line 105
    :goto_0
    return-void

    .line 103
    :cond_0
    invoke-virtual {p0, p1, p2, p3}, Lcom/miui/internal/variable/v16/Internal_Policy_Impl_PhoneWindow_class;->originalInstallDecor(JLjava/lang/Object;)V

    goto :goto_0
.end method

.method protected originalInstallDecor(JLjava/lang/Object;)V
    .locals 2

    .prologue
    .line 91
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "com.miui.internal.variable.v16.Internal_Policy_Impl_PhoneWindow_class.originalInstallDecor(long, Object)"

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0
.end method
