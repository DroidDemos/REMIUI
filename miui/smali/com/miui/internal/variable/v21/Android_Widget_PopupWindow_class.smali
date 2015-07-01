.class public Lcom/miui/internal/variable/v21/Android_Widget_PopupWindow_class;
.super Lcom/miui/internal/variable/v16/Android_Widget_PopupWindow_class;
.source "SourceFile"


# static fields
.field private static final yH:I = 0x10

.field private static final yV:I = 0x4000000

.field private static yW:Lmiui/reflect/Field;


# direct methods
.method static constructor <clinit>()V
    .locals 3

    .prologue
    .line 22
    const-class v0, Landroid/view/WindowManager$LayoutParams;

    const-string v1, "extraFlags"

    const-string v2, "I"

    invoke-static {v0, v1, v2}, Lmiui/reflect/Field;->of(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lmiui/reflect/Field;

    move-result-object v0

    sput-object v0, Lcom/miui/internal/variable/v21/Android_Widget_PopupWindow_class;->yW:Lmiui/reflect/Field;

    return-void
.end method

.method public constructor <init>()V
    .locals 0

    .prologue
    .line 16
    invoke-direct {p0}, Lcom/miui/internal/variable/v16/Android_Widget_PopupWindow_class;-><init>()V

    return-void
.end method


# virtual methods
.method public buildProxy()V
    .locals 2

    .prologue
    .line 26
    const-string v0, "createPopupLayout"

    const-string v1, "(Landroid/os/IBinder;)Landroid/view/WindowManager$LayoutParams;"

    invoke-virtual {p0, v0, v1}, Lcom/miui/internal/variable/v21/Android_Widget_PopupWindow_class;->attachMethod(Ljava/lang/String;Ljava/lang/String;)J

    .line 28
    return-void
.end method

.method protected handle()V
    .locals 3

    .prologue
    const/4 v2, 0x0

    .line 32
    const-wide/16 v0, 0x0

    invoke-virtual {p0, v0, v1, v2, v2}, Lcom/miui/internal/variable/v21/Android_Widget_PopupWindow_class;->handleCreatePopupLayout(JLandroid/widget/PopupWindow;Landroid/os/IBinder;)Landroid/view/WindowManager$LayoutParams;

    .line 33
    return-void
.end method

.method protected handleCreatePopupLayout(JLandroid/widget/PopupWindow;Landroid/os/IBinder;)Landroid/view/WindowManager$LayoutParams;
    .locals 5

    .prologue
    const/4 v4, 0x1

    .line 37
    invoke-virtual {p0, p1, p2, p3, p4}, Lcom/miui/internal/variable/v21/Android_Widget_PopupWindow_class;->originalCreatePopupLayout(JLandroid/widget/PopupWindow;Landroid/os/IBinder;)Landroid/view/WindowManager$LayoutParams;

    move-result-object v1

    .line 38
    invoke-virtual {p3}, Landroid/widget/PopupWindow;->getContentView()Landroid/view/View;

    move-result-object v0

    invoke-virtual {v0}, Landroid/view/View;->getContext()Landroid/content/Context;

    move-result-object v0

    .line 40
    invoke-static {v0}, Lmiui/os/Build;->isUsingMiui(Landroid/content/Context;)Z

    move-result v2

    if-eqz v2, :cond_0

    .line 41
    const v2, 0x101020d

    const/4 v3, 0x0

    invoke-static {v0, v2, v3}, Lmiui/util/AttributeResolver;->resolveBoolean(Landroid/content/Context;IZ)Z

    move-result v2

    .line 43
    if-eqz v2, :cond_1

    .line 44
    iget v0, v1, Landroid/view/WindowManager$LayoutParams;->flags:I

    or-int/lit16 v0, v0, 0x400

    iput v0, v1, Landroid/view/WindowManager$LayoutParams;->flags:I

    .line 59
    :cond_0
    :goto_0
    return-object v1

    .line 46
    :cond_1
    iget v2, v1, Landroid/view/WindowManager$LayoutParams;->flags:I

    const/high16 v3, 0x4000000

    or-int/2addr v2, v3

    iput v2, v1, Landroid/view/WindowManager$LayoutParams;->flags:I

    .line 48
    sget v2, Lcom/miui/internal/R$attr;->windowTranslucentStatus:I

    invoke-static {v0, v2, v4}, Lmiui/util/AttributeResolver;->resolveInt(Landroid/content/Context;II)I

    move-result v0

    .line 51
    if-ne v0, v4, :cond_0

    .line 53
    sget-object v0, Lcom/miui/internal/variable/v21/Android_Widget_PopupWindow_class;->yW:Lmiui/reflect/Field;

    invoke-virtual {v0, v1}, Lmiui/reflect/Field;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/Integer;

    invoke-virtual {v0}, Ljava/lang/Integer;->intValue()I

    move-result v0

    .line 54
    or-int/lit8 v0, v0, 0x10

    .line 55
    sget-object v2, Lcom/miui/internal/variable/v21/Android_Widget_PopupWindow_class;->yW:Lmiui/reflect/Field;

    invoke-virtual {v2, v1, v0}, Lmiui/reflect/Field;->set(Ljava/lang/Object;I)V

    goto :goto_0
.end method

.method protected originalCreatePopupLayout(JLandroid/widget/PopupWindow;Landroid/os/IBinder;)Landroid/view/WindowManager$LayoutParams;
    .locals 2

    .prologue
    .line 64
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "com.miui.internal.variable.v21.Android_Widget_PopupWindow_class.originalCreatePopupLayout(long, PopupWindow, IBinder)"

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0
.end method
