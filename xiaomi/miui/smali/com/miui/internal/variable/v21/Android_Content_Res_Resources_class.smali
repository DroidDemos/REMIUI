.class public Lcom/miui/internal/variable/v21/Android_Content_Res_Resources_class;
.super Lcom/miui/internal/variable/Android_Content_Res_Resources_class;
.source "SourceFile"


# static fields
.field private static final eE:Lcom/miui/internal/variable/Android_Graphics_Drawable_Drawable_class;

.field private static final eF:Lmiui/reflect/Field;


# direct methods
.method static constructor <clinit>()V
    .locals 3

    .prologue
    .line 24
    invoke-static {}, Lcom/miui/internal/variable/Android_Graphics_Drawable_Drawable_class$Factory;->getInstance()Lcom/miui/internal/variable/Android_Graphics_Drawable_Drawable_class$Factory;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/internal/variable/Android_Graphics_Drawable_Drawable_class$Factory;->get()Lcom/miui/internal/variable/Android_Graphics_Drawable_Drawable_class;

    move-result-object v0

    sput-object v0, Lcom/miui/internal/variable/v21/Android_Content_Res_Resources_class;->eE:Lcom/miui/internal/variable/Android_Graphics_Drawable_Drawable_class;

    .line 27
    const-class v0, Landroid/content/res/Resources;

    const-string v1, "mAssets"

    const-string v2, "Landroid/content/res/AssetManager;"

    invoke-static {v0, v1, v2}, Lmiui/reflect/Field;->of(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lmiui/reflect/Field;

    move-result-object v0

    sput-object v0, Lcom/miui/internal/variable/v21/Android_Content_Res_Resources_class;->eF:Lmiui/reflect/Field;

    return-void
.end method

.method public constructor <init>()V
    .locals 0

    .prologue
    .line 21
    invoke-direct {p0}, Lcom/miui/internal/variable/Android_Content_Res_Resources_class;-><init>()V

    return-void
.end method


# virtual methods
.method public buildProxy()V
    .locals 2

    .prologue
    .line 31
    const-string v0, "loadDrawable"

    const-string v1, "(Landroid/util/TypedValue;ILandroid/content/res/Resources$Theme;)Landroid/graphics/drawable/Drawable;"

    invoke-virtual {p0, v0, v1}, Lcom/miui/internal/variable/v21/Android_Content_Res_Resources_class;->attachMethod(Ljava/lang/String;Ljava/lang/String;)J

    .line 32
    return-void
.end method

.method protected handle()V
    .locals 7

    .prologue
    const/4 v3, 0x0

    .line 36
    const-wide/16 v1, 0x0

    const/4 v5, 0x0

    move-object v0, p0

    move-object v4, v3

    move-object v6, v3

    invoke-virtual/range {v0 .. v6}, Lcom/miui/internal/variable/v21/Android_Content_Res_Resources_class;->handleLoadDrawable(JLandroid/content/res/Resources;Landroid/util/TypedValue;ILandroid/content/res/Resources$Theme;)Landroid/graphics/drawable/Drawable;

    .line 37
    return-void
.end method

.method protected handleLoadDrawable(JLandroid/content/res/Resources;Landroid/util/TypedValue;ILandroid/content/res/Resources$Theme;)Landroid/graphics/drawable/Drawable;
    .locals 2

    .prologue
    .line 40
    invoke-virtual/range {p0 .. p6}, Lcom/miui/internal/variable/v21/Android_Content_Res_Resources_class;->originalLoadDrawable(JLandroid/content/res/Resources;Landroid/util/TypedValue;ILandroid/content/res/Resources$Theme;)Landroid/graphics/drawable/Drawable;

    move-result-object v0

    .line 41
    if-eqz v0, :cond_0

    invoke-virtual {v0}, Landroid/graphics/drawable/Drawable;->isStateful()Z

    move-result v1

    if-eqz v1, :cond_0

    .line 42
    sget-object v1, Lcom/miui/internal/variable/v21/Android_Content_Res_Resources_class;->eE:Lcom/miui/internal/variable/Android_Graphics_Drawable_Drawable_class;

    invoke-virtual {v1, v0, p5}, Lcom/miui/internal/variable/Android_Graphics_Drawable_Drawable_class;->setId(Landroid/graphics/drawable/Drawable;I)V

    .line 44
    :cond_0
    return-object v0
.end method

.method protected originalLoadDrawable(JLandroid/content/res/Resources;Landroid/util/TypedValue;ILandroid/content/res/Resources$Theme;)Landroid/graphics/drawable/Drawable;
    .locals 2

    .prologue
    .line 48
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "com.miui.internal.variable.v21.Android_Content_Res_Resources_class.originalLoadDrawable(long, Resources, TypedValue, int, Resources.Theme)"

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

.method public setAssetManager(Landroid/content/res/Resources;Landroid/content/res/AssetManager;)V
    .locals 3

    .prologue
    .line 55
    :try_start_0
    sget-object v0, Lcom/miui/internal/variable/v21/Android_Content_Res_Resources_class;->eF:Lmiui/reflect/Field;

    invoke-virtual {v0, p1, p2}, Lmiui/reflect/Field;->set(Ljava/lang/Object;Ljava/lang/Object;)V
    :try_end_0
    .catch Ljava/lang/RuntimeException; {:try_start_0 .. :try_end_0} :catch_0

    .line 59
    :goto_0
    return-void

    .line 56
    :catch_0
    move-exception v0

    .line 57
    invoke-static {}, Lcom/miui/internal/variable/VariableExceptionHandler;->getInstance()Lcom/miui/internal/variable/VariableExceptionHandler;

    move-result-object v1

    const-string v2, "mAssets"

    invoke-virtual {v1, v2, v0}, Lcom/miui/internal/variable/VariableExceptionHandler;->onThrow(Ljava/lang/String;Ljava/lang/Throwable;)V

    goto :goto_0
.end method
