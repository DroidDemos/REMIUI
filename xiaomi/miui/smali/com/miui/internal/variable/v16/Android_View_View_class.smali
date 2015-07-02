.class public Lcom/miui/internal/variable/v16/Android_View_View_class;
.super Lcom/miui/internal/variable/Android_View_View_class;
.source "SourceFile"


# static fields
.field private static final NM:Lmiui/reflect/Field;

.field private static final NN:Lmiui/reflect/Field;

.field private static final NQ:Lmiui/reflect/Field;

.field private static final NS:Lmiui/reflect/Field;

.field private static final NT:Lmiui/reflect/Field;

.field private static final NV:Lmiui/reflect/Field;

.field private static final NW:Lmiui/reflect/Field;

.field private static final NX:Lmiui/reflect/Method;

.field private static final NY:Lmiui/reflect/Method;

.field private static final Oa:Lmiui/reflect/Field;

.field private static final Ob:Lmiui/reflect/Field;

.field private static Oc:Ljava/lang/Class;

.field private static Od:Lmiui/reflect/Field;


# direct methods
.method static constructor <clinit>()V
    .locals 3

    .prologue
    .line 26
    const-class v0, Landroid/view/View;

    const-string v1, "mPrivateFlags"

    const-string v2, "I"

    invoke-static {v0, v1, v2}, Lmiui/reflect/Field;->of(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lmiui/reflect/Field;

    move-result-object v0

    sput-object v0, Lcom/miui/internal/variable/v16/Android_View_View_class;->NM:Lmiui/reflect/Field;

    .line 28
    const-class v0, Landroid/view/View;

    const-string v1, "mOldWidthMeasureSpec"

    const-string v2, "I"

    invoke-static {v0, v1, v2}, Lmiui/reflect/Field;->of(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lmiui/reflect/Field;

    move-result-object v0

    sput-object v0, Lcom/miui/internal/variable/v16/Android_View_View_class;->NN:Lmiui/reflect/Field;

    .line 31
    const-class v0, Landroid/view/View;

    const-string v1, "mOldHeightMeasureSpec"

    const-string v2, "I"

    invoke-static {v0, v1, v2}, Lmiui/reflect/Field;->of(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lmiui/reflect/Field;

    move-result-object v0

    sput-object v0, Lcom/miui/internal/variable/v16/Android_View_View_class;->NQ:Lmiui/reflect/Field;

    .line 34
    const-class v0, Landroid/view/View;

    const-string v1, "mScrollX"

    const-string v2, "I"

    invoke-static {v0, v1, v2}, Lmiui/reflect/Field;->of(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lmiui/reflect/Field;

    move-result-object v0

    sput-object v0, Lcom/miui/internal/variable/v16/Android_View_View_class;->NS:Lmiui/reflect/Field;

    .line 36
    const-class v0, Landroid/view/View;

    const-string v1, "mScrollY"

    const-string v2, "I"

    invoke-static {v0, v1, v2}, Lmiui/reflect/Field;->of(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lmiui/reflect/Field;

    move-result-object v0

    sput-object v0, Lcom/miui/internal/variable/v16/Android_View_View_class;->NT:Lmiui/reflect/Field;

    .line 38
    const-class v0, Landroid/view/View;

    const-string v1, "mLeft"

    const-string v2, "I"

    invoke-static {v0, v1, v2}, Lmiui/reflect/Field;->of(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lmiui/reflect/Field;

    move-result-object v0

    sput-object v0, Lcom/miui/internal/variable/v16/Android_View_View_class;->NV:Lmiui/reflect/Field;

    .line 40
    const-class v0, Landroid/view/View;

    const-string v1, "mRight"

    const-string v2, "I"

    invoke-static {v0, v1, v2}, Lmiui/reflect/Field;->of(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lmiui/reflect/Field;

    move-result-object v0

    sput-object v0, Lcom/miui/internal/variable/v16/Android_View_View_class;->NW:Lmiui/reflect/Field;

    .line 42
    const-class v0, Landroid/view/View;

    const-string v1, "getContextMenuInfo"

    const-string v2, "()Landroid/view/ContextMenu$ContextMenuInfo;"

    invoke-static {v0, v1, v2}, Lmiui/reflect/Method;->of(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lmiui/reflect/Method;

    move-result-object v0

    sput-object v0, Lcom/miui/internal/variable/v16/Android_View_View_class;->NX:Lmiui/reflect/Method;

    .line 45
    const-class v0, Landroid/view/View;

    const-string v1, "onCreateContextMenu"

    const-string v2, "(Landroid/view/ContextMenu;)V"

    invoke-static {v0, v1, v2}, Lmiui/reflect/Method;->of(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lmiui/reflect/Method;

    move-result-object v0

    sput-object v0, Lcom/miui/internal/variable/v16/Android_View_View_class;->NY:Lmiui/reflect/Method;

    .line 48
    const-class v0, Landroid/view/View;

    const-string v1, "mListenerInfo"

    const-string v2, "Landroid/view/View$ListenerInfo;"

    invoke-static {v0, v1, v2}, Lmiui/reflect/Field;->of(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lmiui/reflect/Field;

    move-result-object v0

    sput-object v0, Lcom/miui/internal/variable/v16/Android_View_View_class;->Oa:Lmiui/reflect/Field;

    .line 51
    const-class v0, Landroid/view/View;

    const-string v1, "mKeyedTags"

    const-string v2, "Landroid/util/SparseArray;"

    invoke-static {v0, v1, v2}, Lmiui/reflect/Field;->of(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lmiui/reflect/Field;

    move-result-object v0

    sput-object v0, Lcom/miui/internal/variable/v16/Android_View_View_class;->Ob:Lmiui/reflect/Field;

    return-void
.end method

.method public constructor <init>()V
    .locals 0

    .prologue
    .line 23
    invoke-direct {p0}, Lcom/miui/internal/variable/Android_View_View_class;-><init>()V

    return-void
.end method

.method private b(Landroid/view/View;I)V
    .locals 3

    .prologue
    .line 178
    :try_start_0
    sget-object v0, Lcom/miui/internal/variable/v16/Android_View_View_class;->NM:Lmiui/reflect/Field;

    invoke-virtual {v0, p1, p2}, Lmiui/reflect/Field;->set(Ljava/lang/Object;I)V
    :try_end_0
    .catch Ljava/lang/RuntimeException; {:try_start_0 .. :try_end_0} :catch_0

    .line 182
    :goto_0
    return-void

    .line 179
    :catch_0
    move-exception v0

    .line 180
    invoke-static {}, Lcom/miui/internal/variable/VariableExceptionHandler;->getInstance()Lcom/miui/internal/variable/VariableExceptionHandler;

    move-result-object v1

    const-string v2, "android.view.View.mPrivateFlags"

    invoke-virtual {v1, v2, v0}, Lcom/miui/internal/variable/VariableExceptionHandler;->onThrow(Ljava/lang/String;Ljava/lang/Throwable;)V

    goto :goto_0
.end method

.method private m(Landroid/view/View;)I
    .locals 3

    .prologue
    .line 169
    :try_start_0
    sget-object v0, Lcom/miui/internal/variable/v16/Android_View_View_class;->NM:Lmiui/reflect/Field;

    invoke-virtual {v0, p1}, Lmiui/reflect/Field;->getInt(Ljava/lang/Object;)I
    :try_end_0
    .catch Ljava/lang/RuntimeException; {:try_start_0 .. :try_end_0} :catch_0

    move-result v0

    .line 173
    :goto_0
    return v0

    .line 170
    :catch_0
    move-exception v0

    .line 171
    invoke-static {}, Lcom/miui/internal/variable/VariableExceptionHandler;->getInstance()Lcom/miui/internal/variable/VariableExceptionHandler;

    move-result-object v1

    const-string v2, "android.view.View.mPrivateFlags"

    invoke-virtual {v1, v2, v0}, Lcom/miui/internal/variable/VariableExceptionHandler;->onThrow(Ljava/lang/String;Ljava/lang/Throwable;)V

    .line 173
    const/4 v0, 0x0

    goto :goto_0
.end method

.method private n(Landroid/view/View;)I
    .locals 3

    .prologue
    .line 186
    :try_start_0
    sget-object v0, Lcom/miui/internal/variable/v16/Android_View_View_class;->NN:Lmiui/reflect/Field;

    invoke-virtual {v0, p1}, Lmiui/reflect/Field;->getInt(Ljava/lang/Object;)I
    :try_end_0
    .catch Ljava/lang/RuntimeException; {:try_start_0 .. :try_end_0} :catch_0

    move-result v0

    .line 190
    :goto_0
    return v0

    .line 187
    :catch_0
    move-exception v0

    .line 188
    invoke-static {}, Lcom/miui/internal/variable/VariableExceptionHandler;->getInstance()Lcom/miui/internal/variable/VariableExceptionHandler;

    move-result-object v1

    const-string v2, "android.view.View.mOldWidthMeasureSpec"

    invoke-virtual {v1, v2, v0}, Lcom/miui/internal/variable/VariableExceptionHandler;->onThrow(Ljava/lang/String;Ljava/lang/Throwable;)V

    .line 190
    const/4 v0, 0x0

    goto :goto_0
.end method

.method private o(Landroid/view/View;)I
    .locals 3

    .prologue
    .line 195
    :try_start_0
    sget-object v0, Lcom/miui/internal/variable/v16/Android_View_View_class;->NQ:Lmiui/reflect/Field;

    invoke-virtual {v0, p1}, Lmiui/reflect/Field;->getInt(Ljava/lang/Object;)I
    :try_end_0
    .catch Ljava/lang/RuntimeException; {:try_start_0 .. :try_end_0} :catch_0

    move-result v0

    .line 199
    :goto_0
    return v0

    .line 196
    :catch_0
    move-exception v0

    .line 197
    invoke-static {}, Lcom/miui/internal/variable/VariableExceptionHandler;->getInstance()Lcom/miui/internal/variable/VariableExceptionHandler;

    move-result-object v1

    const-string v2, "android.view.View.mOldHeightMeasureSpec"

    invoke-virtual {v1, v2, v0}, Lcom/miui/internal/variable/VariableExceptionHandler;->onThrow(Ljava/lang/String;Ljava/lang/Throwable;)V

    .line 199
    const/4 v0, 0x0

    goto :goto_0
.end method


# virtual methods
.method protected attachSpecialMethods()V
    .locals 1

    .prologue
    .line 77
    const-string v0, "(Landroid/content/Context;Landroid/util/AttributeSet;I)V"

    invoke-virtual {p0, v0}, Lcom/miui/internal/variable/v16/Android_View_View_class;->attachConstructor(Ljava/lang/String;)J

    .line 78
    return-void
.end method

.method public buildProxy()V
    .locals 2

    .prologue
    .line 58
    const-string v0, "refreshDrawableState"

    const-string v1, "()V"

    invoke-virtual {p0, v0, v1}, Lcom/miui/internal/variable/v16/Android_View_View_class;->attachMethod(Ljava/lang/String;Ljava/lang/String;)J

    .line 59
    const-string v0, "onCreateDrawableState"

    const-string v1, "(I)[I"

    invoke-virtual {p0, v0, v1}, Lcom/miui/internal/variable/v16/Android_View_View_class;->attachMethod(Ljava/lang/String;Ljava/lang/String;)J

    .line 60
    const-string v0, "createContextMenu"

    const-string v1, "(Landroid/view/ContextMenu;)V"

    invoke-virtual {p0, v0, v1}, Lcom/miui/internal/variable/v16/Android_View_View_class;->attachMethod(Ljava/lang/String;Ljava/lang/String;)J

    .line 61
    const-string v0, "(Landroid/content/Context;)V"

    invoke-virtual {p0, v0}, Lcom/miui/internal/variable/v16/Android_View_View_class;->attachConstructor(Ljava/lang/String;)J

    .line 62
    const-string v0, "()V"

    invoke-virtual {p0, v0}, Lcom/miui/internal/variable/v16/Android_View_View_class;->attachConstructor(Ljava/lang/String;)J

    .line 63
    invoke-virtual {p0}, Lcom/miui/internal/variable/v16/Android_View_View_class;->attachSpecialMethods()V

    .line 64
    return-void
.end method

.method protected getCustomizedValue(Landroid/view/View;)Lcom/miui/internal/variable/Android_View_View_class$ValuedSparseArray;
    .locals 4

    .prologue
    .line 253
    const/4 v1, 0x0

    .line 255
    :try_start_0
    sget-object v0, Lcom/miui/internal/variable/v16/Android_View_View_class;->Ob:Lmiui/reflect/Field;

    invoke-virtual {v0, p1}, Lmiui/reflect/Field;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/variable/Android_View_View_class$ValuedSparseArray;
    :try_end_0
    .catch Ljava/lang/RuntimeException; {:try_start_0 .. :try_end_0} :catch_0

    .line 259
    :goto_0
    return-object v0

    .line 256
    :catch_0
    move-exception v0

    .line 257
    invoke-static {}, Lcom/miui/internal/variable/VariableExceptionHandler;->getInstance()Lcom/miui/internal/variable/VariableExceptionHandler;

    move-result-object v2

    const-string v3, "android.view.View.mKeyedTags"

    invoke-virtual {v2, v3, v0}, Lcom/miui/internal/variable/VariableExceptionHandler;->onThrow(Ljava/lang/String;Ljava/lang/Throwable;)V

    move-object v0, v1

    goto :goto_0
.end method

.method protected handle()V
    .locals 7

    .prologue
    const/4 v6, 0x0

    const-wide/16 v1, 0x0

    const/4 v3, 0x0

    .line 68
    invoke-virtual {p0, v1, v2, v3}, Lcom/miui/internal/variable/v16/Android_View_View_class;->handleRefreshDrawableState(JLandroid/view/View;)V

    .line 69
    invoke-virtual {p0, v1, v2, v3, v6}, Lcom/miui/internal/variable/v16/Android_View_View_class;->handleOnCreateDrawableState(JLandroid/view/View;I)[I

    .line 70
    invoke-virtual {p0, v1, v2, v3, v3}, Lcom/miui/internal/variable/v16/Android_View_View_class;->handleCreateContextMenu(JLandroid/view/View;Landroid/view/ContextMenu;)V

    move-object v0, p0

    move-object v4, v3

    move-object v5, v3

    .line 71
    invoke-virtual/range {v0 .. v6}, Lcom/miui/internal/variable/v16/Android_View_View_class;->handle_init_(JLandroid/view/View;Landroid/content/Context;Landroid/util/AttributeSet;I)V

    .line 72
    invoke-virtual {p0, v1, v2, v3, v3}, Lcom/miui/internal/variable/v16/Android_View_View_class;->handle_init_(JLandroid/view/View;Landroid/content/Context;)V

    .line 73
    invoke-virtual {p0, v1, v2, v3}, Lcom/miui/internal/variable/v16/Android_View_View_class;->handle_init_(JLandroid/view/View;)V

    .line 74
    return-void
.end method

.method protected handleCreateContextMenu(JLandroid/view/View;Landroid/view/ContextMenu;)V
    .locals 5

    .prologue
    const/4 v4, 0x0

    .line 131
    instance-of v0, p4, Lcom/miui/internal/view/menu/ContextMenuBuilder;

    if-eqz v0, :cond_3

    .line 132
    sget-object v0, Lcom/miui/internal/variable/v16/Android_View_View_class;->Oc:Ljava/lang/Class;

    if-nez v0, :cond_0

    .line 134
    :try_start_0
    const-string v0, "android.view.View$ListenerInfo"

    invoke-static {v0}, Ljava/lang/Class;->forName(Ljava/lang/String;)Ljava/lang/Class;

    move-result-object v0

    sput-object v0, Lcom/miui/internal/variable/v16/Android_View_View_class;->Oc:Ljava/lang/Class;

    .line 135
    sget-object v0, Lcom/miui/internal/variable/v16/Android_View_View_class;->Oc:Ljava/lang/Class;

    const-string v1, "mOnCreateContextMenuListener"

    const-string v2, "Landroid/view/View$OnCreateContextMenuListener;"

    invoke-static {v0, v1, v2}, Lmiui/reflect/Field;->of(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lmiui/reflect/Field;

    move-result-object v0

    sput-object v0, Lcom/miui/internal/variable/v16/Android_View_View_class;->Od:Lmiui/reflect/Field;
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    .line 143
    :cond_0
    sget-object v0, Lcom/miui/internal/variable/v16/Android_View_View_class;->NX:Lmiui/reflect/Method;

    invoke-virtual {p3}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v1

    new-array v2, v4, [Ljava/lang/Object;

    invoke-virtual {v0, v1, p3, v2}, Lmiui/reflect/Method;->invokeObject(Ljava/lang/Class;Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/view/ContextMenu$ContextMenuInfo;

    check-cast v0, Landroid/view/ContextMenu$ContextMenuInfo;

    move-object v1, p4

    .line 144
    check-cast v1, Lcom/miui/internal/view/menu/ContextMenuBuilder;

    invoke-virtual {v1, v0}, Lcom/miui/internal/view/menu/ContextMenuBuilder;->setCurrentMenuInfo(Landroid/view/ContextMenu$ContextMenuInfo;)V

    .line 145
    sget-object v1, Lcom/miui/internal/variable/v16/Android_View_View_class;->NY:Lmiui/reflect/Method;

    invoke-virtual {p3}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v2

    const/4 v3, 0x1

    new-array v3, v3, [Ljava/lang/Object;

    aput-object p4, v3, v4

    invoke-virtual {v1, v2, p3, v3}, Lmiui/reflect/Method;->invoke(Ljava/lang/Class;Ljava/lang/Object;[Ljava/lang/Object;)V

    .line 146
    sget-object v1, Lcom/miui/internal/variable/v16/Android_View_View_class;->Oa:Lmiui/reflect/Field;

    invoke-virtual {v1, p3}, Lmiui/reflect/Field;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v1

    .line 147
    if-eqz v1, :cond_1

    .line 148
    sget-object v2, Lcom/miui/internal/variable/v16/Android_View_View_class;->Od:Lmiui/reflect/Field;

    invoke-virtual {v2, v1}, Lmiui/reflect/Field;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Landroid/view/View$OnCreateContextMenuListener;

    .line 149
    if-eqz v1, :cond_1

    .line 150
    invoke-interface {v1, p4, p3, v0}, Landroid/view/View$OnCreateContextMenuListener;->onCreateContextMenu(Landroid/view/ContextMenu;Landroid/view/View;Landroid/view/ContextMenu$ContextMenuInfo;)V

    :cond_1
    move-object v0, p4

    .line 153
    check-cast v0, Lcom/miui/internal/view/menu/ContextMenuBuilder;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Lcom/miui/internal/view/menu/ContextMenuBuilder;->setCurrentMenuInfo(Landroid/view/ContextMenu$ContextMenuInfo;)V

    .line 154
    invoke-virtual {p3}, Landroid/view/View;->getParent()Landroid/view/ViewParent;

    move-result-object v0

    if-eqz v0, :cond_2

    .line 155
    invoke-virtual {p3}, Landroid/view/View;->getParent()Landroid/view/ViewParent;

    move-result-object v0

    invoke-interface {v0, p4}, Landroid/view/ViewParent;->createContextMenu(Landroid/view/ContextMenu;)V

    .line 160
    :cond_2
    :goto_0
    return-void

    .line 137
    :catch_0
    move-exception v0

    .line 138
    invoke-virtual {v0}, Ljava/lang/Exception;->printStackTrace()V

    goto :goto_0

    .line 158
    :cond_3
    invoke-virtual {p0, p1, p2, p3, p4}, Lcom/miui/internal/variable/v16/Android_View_View_class;->originalCreateContextMenu(JLandroid/view/View;Landroid/view/ContextMenu;)V

    goto :goto_0
.end method

.method protected handleOnCreateDrawableState(JLandroid/view/View;I)[I
    .locals 1

    .prologue
    .line 91
    const/4 v0, 0x0

    invoke-virtual {p0, p1, p2, p3, v0}, Lcom/miui/internal/variable/v16/Android_View_View_class;->originalOnCreateDrawableState(JLandroid/view/View;I)[I

    move-result-object v0

    .line 92
    invoke-virtual {p0, p3, v0, p4}, Lcom/miui/internal/variable/v16/Android_View_View_class;->onCreateDrawableState(Landroid/view/View;[II)[I

    move-result-object v0

    return-object v0
.end method

.method protected handleRefreshDrawableState(JLandroid/view/View;)V
    .locals 0

    .prologue
    .line 81
    invoke-virtual {p0, p1, p2, p3}, Lcom/miui/internal/variable/v16/Android_View_View_class;->originalRefreshDrawableState(JLandroid/view/View;)V

    .line 82
    invoke-static {p3}, Lcom/miui/internal/util/TaggingDrawableHelper;->onDrawableStateChange(Landroid/view/View;)V

    .line 83
    return-void
.end method

.method protected handle_init_(JLandroid/view/View;)V
    .locals 3

    .prologue
    .line 121
    const-wide/16 v0, 0x0

    invoke-virtual {p0, v0, v1, p3}, Lcom/miui/internal/variable/v16/Android_View_View_class;->original_init_(JLandroid/view/View;)V

    .line 122
    sget-object v0, Lcom/miui/internal/variable/v16/Android_View_View_class;->Ob:Lmiui/reflect/Field;

    new-instance v1, Lcom/miui/internal/variable/Android_View_View_class$ValuedSparseArray;

    const/4 v2, 0x2

    invoke-direct {v1, v2}, Lcom/miui/internal/variable/Android_View_View_class$ValuedSparseArray;-><init>(I)V

    invoke-virtual {v0, p3, v1}, Lmiui/reflect/Field;->set(Ljava/lang/Object;Ljava/lang/Object;)V

    .line 123
    return-void
.end method

.method protected handle_init_(JLandroid/view/View;Landroid/content/Context;)V
    .locals 3

    .prologue
    .line 111
    const-wide/16 v0, 0x0

    invoke-virtual {p0, v0, v1, p3, p4}, Lcom/miui/internal/variable/v16/Android_View_View_class;->original_init_(JLandroid/view/View;Landroid/content/Context;)V

    .line 112
    sget-object v0, Lcom/miui/internal/variable/v16/Android_View_View_class;->Ob:Lmiui/reflect/Field;

    new-instance v1, Lcom/miui/internal/variable/Android_View_View_class$ValuedSparseArray;

    const/4 v2, 0x2

    invoke-direct {v1, v2}, Lcom/miui/internal/variable/Android_View_View_class$ValuedSparseArray;-><init>(I)V

    invoke-virtual {v0, p3, v1}, Lmiui/reflect/Field;->set(Ljava/lang/Object;Ljava/lang/Object;)V

    .line 113
    return-void
.end method

.method protected handle_init_(JLandroid/view/View;Landroid/content/Context;Landroid/util/AttributeSet;I)V
    .locals 0

    .prologue
    .line 101
    invoke-virtual/range {p0 .. p6}, Lcom/miui/internal/variable/v16/Android_View_View_class;->original_init_(JLandroid/view/View;Landroid/content/Context;Landroid/util/AttributeSet;I)V

    .line 102
    invoke-static {p3, p5}, Lcom/miui/internal/util/TaggingDrawableHelper;->initViewSequenceStates(Landroid/view/View;Landroid/util/AttributeSet;)V

    .line 103
    return-void
.end method

.method protected originalCreateContextMenu(JLandroid/view/View;Landroid/view/ContextMenu;)V
    .locals 2

    .prologue
    .line 163
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "com.miui.internal.variable.v16.Android_View_View_class.originalCreateContextMenu(long, View, ContextMenu)"

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

.method protected originalOnCreateDrawableState(JLandroid/view/View;I)[I
    .locals 2

    .prologue
    .line 96
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "com.miui.internal.variable.v16.Android_View_View_class.originalOnCreateDrawableState(long, View, int)"

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

.method protected originalRefreshDrawableState(JLandroid/view/View;)V
    .locals 2

    .prologue
    .line 86
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "com.miui.internal.variable.v16.Android_View_View_class.originalRefreshDrawableState(long, View)"

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

.method protected original_init_(JLandroid/view/View;)V
    .locals 2

    .prologue
    .line 116
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "com.miui.internal.variable.v16.Android_View_View_class.original_init_(long, View)"

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

.method protected original_init_(JLandroid/view/View;Landroid/content/Context;)V
    .locals 2

    .prologue
    .line 126
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "com.miui.internal.variable.v16.Android_View_View_class.original_init_(long, View, Context)"

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

.method protected original_init_(JLandroid/view/View;Landroid/content/Context;Landroid/util/AttributeSet;I)V
    .locals 2

    .prologue
    .line 106
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "com.miui.internal.variable.v16.Android_View_View_class.original_init_(long, View, Context, AttributeSet, int)"

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

.method public relayout(Landroid/view/View;)V
    .locals 5

    .prologue
    .line 204
    invoke-virtual {p1}, Landroid/view/View;->getHeight()I

    move-result v0

    if-eqz v0, :cond_0

    invoke-virtual {p1}, Landroid/view/View;->getWidth()I

    move-result v0

    if-eqz v0, :cond_0

    .line 205
    invoke-direct {p0, p1}, Lcom/miui/internal/variable/v16/Android_View_View_class;->m(Landroid/view/View;)I

    move-result v0

    .line 206
    invoke-virtual {p1}, Landroid/view/View;->forceLayout()V

    .line 207
    invoke-direct {p0, p1}, Lcom/miui/internal/variable/v16/Android_View_View_class;->n(Landroid/view/View;)I

    move-result v1

    .line 208
    invoke-direct {p0, p1}, Lcom/miui/internal/variable/v16/Android_View_View_class;->o(Landroid/view/View;)I

    move-result v2

    .line 209
    invoke-virtual {p1, v1, v2}, Landroid/view/View;->measure(II)V

    .line 210
    invoke-virtual {p1}, Landroid/view/View;->getLeft()I

    move-result v1

    invoke-virtual {p1}, Landroid/view/View;->getTop()I

    move-result v2

    invoke-virtual {p1}, Landroid/view/View;->getRight()I

    move-result v3

    invoke-virtual {p1}, Landroid/view/View;->getBottom()I

    move-result v4

    invoke-virtual {p1, v1, v2, v3, v4}, Landroid/view/View;->layout(IIII)V

    .line 211
    invoke-direct {p0, p1, v0}, Lcom/miui/internal/variable/v16/Android_View_View_class;->b(Landroid/view/View;I)V

    .line 213
    :cond_0
    return-void
.end method

.method public setLeftDirectly(Landroid/view/View;I)V
    .locals 3

    .prologue
    .line 236
    :try_start_0
    sget-object v0, Lcom/miui/internal/variable/v16/Android_View_View_class;->NV:Lmiui/reflect/Field;

    invoke-virtual {v0, p1, p2}, Lmiui/reflect/Field;->set(Ljava/lang/Object;I)V
    :try_end_0
    .catch Ljava/lang/RuntimeException; {:try_start_0 .. :try_end_0} :catch_0

    .line 240
    :goto_0
    return-void

    .line 237
    :catch_0
    move-exception v0

    .line 238
    invoke-static {}, Lcom/miui/internal/variable/VariableExceptionHandler;->getInstance()Lcom/miui/internal/variable/VariableExceptionHandler;

    move-result-object v1

    const-string v2, "android.view.View.mScrollY"

    invoke-virtual {v1, v2, v0}, Lcom/miui/internal/variable/VariableExceptionHandler;->onThrow(Ljava/lang/String;Ljava/lang/Throwable;)V

    goto :goto_0
.end method

.method public setRightDirectly(Landroid/view/View;I)V
    .locals 3

    .prologue
    .line 245
    :try_start_0
    sget-object v0, Lcom/miui/internal/variable/v16/Android_View_View_class;->NW:Lmiui/reflect/Field;

    invoke-virtual {v0, p1, p2}, Lmiui/reflect/Field;->set(Ljava/lang/Object;I)V
    :try_end_0
    .catch Ljava/lang/RuntimeException; {:try_start_0 .. :try_end_0} :catch_0

    .line 249
    :goto_0
    return-void

    .line 246
    :catch_0
    move-exception v0

    .line 247
    invoke-static {}, Lcom/miui/internal/variable/VariableExceptionHandler;->getInstance()Lcom/miui/internal/variable/VariableExceptionHandler;

    move-result-object v1

    const-string v2, "android.view.View.mScrollY"

    invoke-virtual {v1, v2, v0}, Lcom/miui/internal/variable/VariableExceptionHandler;->onThrow(Ljava/lang/String;Ljava/lang/Throwable;)V

    goto :goto_0
.end method

.method public setScrollXDirectly(Landroid/view/View;I)V
    .locals 3

    .prologue
    .line 218
    :try_start_0
    sget-object v0, Lcom/miui/internal/variable/v16/Android_View_View_class;->NS:Lmiui/reflect/Field;

    invoke-virtual {v0, p1, p2}, Lmiui/reflect/Field;->set(Ljava/lang/Object;I)V
    :try_end_0
    .catch Ljava/lang/RuntimeException; {:try_start_0 .. :try_end_0} :catch_0

    .line 222
    :goto_0
    return-void

    .line 219
    :catch_0
    move-exception v0

    .line 220
    invoke-static {}, Lcom/miui/internal/variable/VariableExceptionHandler;->getInstance()Lcom/miui/internal/variable/VariableExceptionHandler;

    move-result-object v1

    const-string v2, "android.view.View.mScrollX"

    invoke-virtual {v1, v2, v0}, Lcom/miui/internal/variable/VariableExceptionHandler;->onThrow(Ljava/lang/String;Ljava/lang/Throwable;)V

    goto :goto_0
.end method

.method public setScrollYDirectly(Landroid/view/View;I)V
    .locals 3

    .prologue
    .line 227
    :try_start_0
    sget-object v0, Lcom/miui/internal/variable/v16/Android_View_View_class;->NT:Lmiui/reflect/Field;

    invoke-virtual {v0, p1, p2}, Lmiui/reflect/Field;->set(Ljava/lang/Object;I)V
    :try_end_0
    .catch Ljava/lang/RuntimeException; {:try_start_0 .. :try_end_0} :catch_0

    .line 231
    :goto_0
    return-void

    .line 228
    :catch_0
    move-exception v0

    .line 229
    invoke-static {}, Lcom/miui/internal/variable/VariableExceptionHandler;->getInstance()Lcom/miui/internal/variable/VariableExceptionHandler;

    move-result-object v1

    const-string v2, "android.view.View.mScrollY"

    invoke-virtual {v1, v2, v0}, Lcom/miui/internal/variable/VariableExceptionHandler;->onThrow(Ljava/lang/String;Ljava/lang/Throwable;)V

    goto :goto_0
.end method
