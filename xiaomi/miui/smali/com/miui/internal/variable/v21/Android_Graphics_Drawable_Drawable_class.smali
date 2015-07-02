.class public Lcom/miui/internal/variable/v21/Android_Graphics_Drawable_Drawable_class;
.super Lcom/miui/internal/variable/v17/Android_Graphics_Drawable_Drawable_class;
.source "SourceFile"


# direct methods
.method public constructor <init>()V
    .locals 0

    .prologue
    .line 21
    invoke-direct {p0}, Lcom/miui/internal/variable/v17/Android_Graphics_Drawable_Drawable_class;-><init>()V

    return-void
.end method


# virtual methods
.method public buildProxy()V
    .locals 2

    .prologue
    .line 26
    const-string v0, "createFromXmlInner"

    const-string v1, "(Landroid/content/res/Resources;Lorg/xmlpull/v1/XmlPullParser;Landroid/util/AttributeSet;Landroid/content/res/Resources$Theme;)Landroid/graphics/drawable/Drawable;"

    invoke-virtual {p0, v0, v1}, Lcom/miui/internal/variable/v21/Android_Graphics_Drawable_Drawable_class;->attachMethod(Ljava/lang/String;Ljava/lang/String;)J

    .line 28
    return-void
.end method

.method protected handle()V
    .locals 8

    .prologue
    .line 33
    const-wide/16 v1, 0x0

    const/4 v3, 0x0

    const/4 v4, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x0

    const/4 v7, 0x0

    move-object v0, p0

    :try_start_0
    invoke-virtual/range {v0 .. v7}, Lcom/miui/internal/variable/v21/Android_Graphics_Drawable_Drawable_class;->handleCreateFromXmlInner(JLandroid/graphics/drawable/Drawable;Landroid/content/res/Resources;Lorg/xmlpull/v1/XmlPullParser;Landroid/util/AttributeSet;Landroid/content/res/Resources$Theme;)Landroid/graphics/drawable/Drawable;
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    .line 37
    :goto_0
    return-void

    .line 34
    :catch_0
    move-exception v0

    .line 35
    invoke-static {}, Lcom/miui/internal/variable/VariableExceptionHandler;->getInstance()Lcom/miui/internal/variable/VariableExceptionHandler;

    move-result-object v1

    const-string v2, "android.graphics.drawable.Drawable.createFromXmlInner"

    invoke-virtual {v1, v2, v0}, Lcom/miui/internal/variable/VariableExceptionHandler;->onThrow(Ljava/lang/String;Ljava/lang/Throwable;)V

    goto :goto_0
.end method

.method protected handleCreateFromXmlInner(JLandroid/graphics/drawable/Drawable;Landroid/content/res/Resources;Lorg/xmlpull/v1/XmlPullParser;Landroid/util/AttributeSet;Landroid/content/res/Resources$Theme;)Landroid/graphics/drawable/Drawable;
    .locals 2
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Lorg/xmlpull/v1/XmlPullParserException;,
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 41
    invoke-interface {p5}, Lorg/xmlpull/v1/XmlPullParser;->getName()Ljava/lang/String;

    move-result-object v0

    .line 43
    const-string v1, "outside-compound"

    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_1

    .line 44
    new-instance v0, Lcom/miui/internal/graphics/drawable/OutsideCompoundDrawable;

    invoke-direct {v0}, Lcom/miui/internal/graphics/drawable/OutsideCompoundDrawable;-><init>()V

    .line 49
    if-eqz v0, :cond_0

    .line 50
    invoke-virtual {v0, p4, p5, p6}, Landroid/graphics/drawable/Drawable;->inflate(Landroid/content/res/Resources;Lorg/xmlpull/v1/XmlPullParser;Landroid/util/AttributeSet;)V

    .line 52
    :cond_0
    :goto_0
    return-object v0

    .line 46
    :cond_1
    invoke-virtual/range {p0 .. p7}, Lcom/miui/internal/variable/v21/Android_Graphics_Drawable_Drawable_class;->originalCreateFromXmlInner(JLandroid/graphics/drawable/Drawable;Landroid/content/res/Resources;Lorg/xmlpull/v1/XmlPullParser;Landroid/util/AttributeSet;Landroid/content/res/Resources$Theme;)Landroid/graphics/drawable/Drawable;

    move-result-object v0

    goto :goto_0
.end method

.method protected originalCreateFromXmlInner(JLandroid/graphics/drawable/Drawable;Landroid/content/res/Resources;Lorg/xmlpull/v1/XmlPullParser;Landroid/util/AttributeSet;Landroid/content/res/Resources$Theme;)Landroid/graphics/drawable/Drawable;
    .locals 2
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Lorg/xmlpull/v1/XmlPullParserException;,
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 57
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "com.miui.internal.variable.v21.Android_Graphics_Drawable_Drawable_class.originalCreateFromXmlInner(long, Drawable, Resources, XmlPullParser, AttributeSet, Theme)"

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0
.end method
