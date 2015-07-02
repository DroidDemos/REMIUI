.class public Lcom/miui/internal/variable/v21/Android_Widget_ListView_class;
.super Lcom/miui/internal/variable/v16/Android_Widget_ListView_class;
.source "SourceFile"


# direct methods
.method public constructor <init>()V
    .locals 0

    .prologue
    .line 14
    invoke-direct {p0}, Lcom/miui/internal/variable/v16/Android_Widget_ListView_class;-><init>()V

    return-void
.end method


# virtual methods
.method protected attachSpecialMethod()V
    .locals 1

    .prologue
    .line 25
    const-string v0, "(Landroid/content/Context;Landroid/util/AttributeSet;II)V"

    invoke-virtual {p0, v0}, Lcom/miui/internal/variable/v21/Android_Widget_ListView_class;->attachConstructor(Ljava/lang/String;)J

    .line 26
    return-void
.end method

.method protected handle()V
    .locals 7

    .prologue
    const/4 v3, 0x0

    .line 19
    const-wide/16 v1, 0x0

    const/4 v6, 0x0

    move-object v0, p0

    move-object v4, v3

    move-object v5, v3

    invoke-virtual/range {v0 .. v6}, Lcom/miui/internal/variable/v21/Android_Widget_ListView_class;->handle_init_(JLandroid/widget/ListView;Landroid/content/Context;Landroid/util/AttributeSet;I)V

    .line 20
    invoke-super {p0}, Lcom/miui/internal/variable/v16/Android_Widget_ListView_class;->handle()V

    .line 21
    return-void
.end method

.method protected handle_init_(JLandroid/widget/ListView;Landroid/content/Context;Landroid/util/AttributeSet;II)V
    .locals 0

    .prologue
    .line 30
    invoke-virtual/range {p0 .. p7}, Lcom/miui/internal/variable/v21/Android_Widget_ListView_class;->original_init_(JLandroid/widget/ListView;Landroid/content/Context;Landroid/util/AttributeSet;II)V

    .line 31
    invoke-virtual {p0, p3, p4}, Lcom/miui/internal/variable/v21/Android_Widget_ListView_class;->doInit(Landroid/widget/ListView;Landroid/content/Context;)V

    .line 32
    return-void
.end method

.method protected original_init_(JLandroid/widget/ListView;Landroid/content/Context;Landroid/util/AttributeSet;II)V
    .locals 2

    .prologue
    .line 35
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "com.miui.internal.variable.v21.Android_Widget_ListView_class.original_init_(long, ListView, Context, AttributeSet, int, int)"

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0
.end method
