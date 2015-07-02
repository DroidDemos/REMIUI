.class public Lcom/miui/internal/variable/v21/Android_View_View_class;
.super Lcom/miui/internal/variable/v16/Android_View_View_class;
.source "SourceFile"


# direct methods
.method public constructor <init>()V
    .locals 0

    .prologue
    .line 18
    invoke-direct {p0}, Lcom/miui/internal/variable/v16/Android_View_View_class;-><init>()V

    return-void
.end method


# virtual methods
.method protected attachSpecialMethods()V
    .locals 1

    .prologue
    .line 30
    const-string v0, "(Landroid/content/Context;Landroid/util/AttributeSet;II)V"

    invoke-virtual {p0, v0}, Lcom/miui/internal/variable/v21/Android_View_View_class;->attachConstructor(Ljava/lang/String;)J

    .line 31
    return-void
.end method

.method protected handle()V
    .locals 8

    .prologue
    const/4 v6, 0x0

    const/4 v3, 0x0

    .line 24
    const-wide/16 v1, 0x0

    move-object v0, p0

    move-object v4, v3

    move-object v5, v3

    move v7, v6

    invoke-virtual/range {v0 .. v7}, Lcom/miui/internal/variable/v21/Android_View_View_class;->handle_init_(JLandroid/view/View;Landroid/content/Context;Landroid/util/AttributeSet;II)V

    .line 25
    invoke-super {p0}, Lcom/miui/internal/variable/v16/Android_View_View_class;->handle()V

    .line 26
    return-void
.end method

.method protected handle_init_(JLandroid/view/View;Landroid/content/Context;Landroid/util/AttributeSet;II)V
    .locals 0

    .prologue
    .line 34
    invoke-virtual/range {p0 .. p7}, Lcom/miui/internal/variable/v21/Android_View_View_class;->original_init_(JLandroid/view/View;Landroid/content/Context;Landroid/util/AttributeSet;II)V

    .line 35
    invoke-static {p3, p5}, Lcom/miui/internal/util/TaggingDrawableHelper;->initViewSequenceStates(Landroid/view/View;Landroid/util/AttributeSet;)V

    .line 36
    return-void
.end method

.method protected original_init_(JLandroid/view/View;Landroid/content/Context;Landroid/util/AttributeSet;II)V
    .locals 2

    .prologue
    .line 40
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "com.miui.internal.variable.v21.Android_View_View_class.original_init_(long, View, Context, AttributeSet, int, int)"

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0
.end method
