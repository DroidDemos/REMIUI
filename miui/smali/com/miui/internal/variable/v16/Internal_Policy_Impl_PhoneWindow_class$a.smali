.class Lcom/miui/internal/variable/v16/Internal_Policy_Impl_PhoneWindow_class$a;
.super Landroid/view/LayoutInflater;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/internal/variable/v16/Internal_Policy_Impl_PhoneWindow_class;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0xa
    name = "a"
.end annotation


# static fields
.field private static final qU:Lmiui/reflect/Field;


# instance fields
.field private qS:Landroid/view/LayoutInflater;

.field private qT:Landroid/view/Window;


# direct methods
.method static constructor <clinit>()V
    .locals 3

    .prologue
    .line 37
    const-class v0, Landroid/view/LayoutInflater;

    const-string v1, "mContext"

    const-string v2, "Landroid/content/Context;"

    invoke-static {v0, v1, v2}, Lmiui/reflect/Field;->of(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lmiui/reflect/Field;

    move-result-object v0

    sput-object v0, Lcom/miui/internal/variable/v16/Internal_Policy_Impl_PhoneWindow_class$a;->qU:Lmiui/reflect/Field;

    return-void
.end method

.method private constructor <init>(Landroid/view/LayoutInflater;Landroid/view/Window;)V
    .locals 1

    .prologue
    .line 41
    sget-object v0, Lcom/miui/internal/variable/v16/Internal_Policy_Impl_PhoneWindow_class$a;->qU:Lmiui/reflect/Field;

    invoke-virtual {v0, p1}, Lmiui/reflect/Field;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/content/Context;

    invoke-direct {p0, v0}, Landroid/view/LayoutInflater;-><init>(Landroid/content/Context;)V

    .line 42
    iput-object p1, p0, Lcom/miui/internal/variable/v16/Internal_Policy_Impl_PhoneWindow_class$a;->qS:Landroid/view/LayoutInflater;

    .line 43
    iput-object p2, p0, Lcom/miui/internal/variable/v16/Internal_Policy_Impl_PhoneWindow_class$a;->qT:Landroid/view/Window;

    .line 44
    return-void
.end method

.method synthetic constructor <init>(Landroid/view/LayoutInflater;Landroid/view/Window;Lcom/miui/internal/variable/v16/Internal_Policy_Impl_PhoneWindow_class$1;)V
    .locals 0

    .prologue
    .line 34
    invoke-direct {p0, p1, p2}, Lcom/miui/internal/variable/v16/Internal_Policy_Impl_PhoneWindow_class$a;-><init>(Landroid/view/LayoutInflater;Landroid/view/Window;)V

    return-void
.end method

.method static synthetic a(Lcom/miui/internal/variable/v16/Internal_Policy_Impl_PhoneWindow_class$a;)Landroid/view/LayoutInflater;
    .locals 1

    .prologue
    .line 34
    iget-object v0, p0, Lcom/miui/internal/variable/v16/Internal_Policy_Impl_PhoneWindow_class$a;->qS:Landroid/view/LayoutInflater;

    return-object v0
.end method


# virtual methods
.method public cloneInContext(Landroid/content/Context;)Landroid/view/LayoutInflater;
    .locals 3

    .prologue
    .line 48
    iget-object v0, p0, Lcom/miui/internal/variable/v16/Internal_Policy_Impl_PhoneWindow_class$a;->qS:Landroid/view/LayoutInflater;

    invoke-virtual {v0, p1}, Landroid/view/LayoutInflater;->cloneInContext(Landroid/content/Context;)Landroid/view/LayoutInflater;

    move-result-object v0

    .line 49
    new-instance v1, Lcom/miui/internal/variable/v16/Internal_Policy_Impl_PhoneWindow_class$a;

    iget-object v2, p0, Lcom/miui/internal/variable/v16/Internal_Policy_Impl_PhoneWindow_class$a;->qT:Landroid/view/Window;

    invoke-direct {v1, v0, v2}, Lcom/miui/internal/variable/v16/Internal_Policy_Impl_PhoneWindow_class$a;-><init>(Landroid/view/LayoutInflater;Landroid/view/Window;)V

    return-object v1
.end method

.method public inflate(ILandroid/view/ViewGroup;Z)Landroid/view/View;
    .locals 2

    .prologue
    .line 54
    iget-object v0, p0, Lcom/miui/internal/variable/v16/Internal_Policy_Impl_PhoneWindow_class$a;->qS:Landroid/view/LayoutInflater;

    iget-object v1, p0, Lcom/miui/internal/variable/v16/Internal_Policy_Impl_PhoneWindow_class$a;->qT:Landroid/view/Window;

    invoke-static {v1}, Lcom/miui/internal/app/ActivityDelegate;->getDecorViewLayoutRes(Landroid/view/Window;)I

    move-result v1

    invoke-virtual {v0, v1, p2, p3}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;Z)Landroid/view/View;

    move-result-object v0

    return-object v0
.end method
