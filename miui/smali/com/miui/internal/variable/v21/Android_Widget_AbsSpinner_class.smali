.class public Lcom/miui/internal/variable/v21/Android_Widget_AbsSpinner_class;
.super Lcom/miui/internal/variable/v16/Android_Widget_AbsSpinner_class;
.source "SourceFile"


# direct methods
.method public constructor <init>()V
    .locals 0

    .prologue
    .line 15
    invoke-direct {p0}, Lcom/miui/internal/variable/v16/Android_Widget_AbsSpinner_class;-><init>()V

    return-void
.end method


# virtual methods
.method public attachSpecialMethod()V
    .locals 1

    .prologue
    .line 20
    const-string v0, "(Landroid/content/Context;Landroid/util/AttributeSet;II)V"

    invoke-virtual {p0, v0}, Lcom/miui/internal/variable/v21/Android_Widget_AbsSpinner_class;->attachConstructor(Ljava/lang/String;)J

    .line 21
    return-void
.end method

.method protected handle()V
    .locals 8

    .prologue
    const/4 v6, 0x0

    const/4 v3, 0x0

    .line 25
    const-wide/16 v1, 0x0

    move-object v0, p0

    move-object v4, v3

    move-object v5, v3

    move v7, v6

    invoke-virtual/range {v0 .. v7}, Lcom/miui/internal/variable/v21/Android_Widget_AbsSpinner_class;->handle_init_(JLandroid/widget/AbsSpinner;Landroid/content/Context;Landroid/util/AttributeSet;II)V

    .line 26
    return-void
.end method

.method protected handle_init_(JLandroid/widget/AbsSpinner;Landroid/content/Context;Landroid/util/AttributeSet;II)V
    .locals 0

    .prologue
    .line 30
    invoke-virtual/range {p0 .. p7}, Lcom/miui/internal/variable/v21/Android_Widget_AbsSpinner_class;->original_init_(JLandroid/widget/AbsSpinner;Landroid/content/Context;Landroid/util/AttributeSet;II)V

    .line 31
    invoke-virtual {p0, p3, p4}, Lcom/miui/internal/variable/v21/Android_Widget_AbsSpinner_class;->doInit(Landroid/widget/AbsSpinner;Landroid/content/Context;)V

    .line 32
    return-void
.end method

.method protected original_init_(JLandroid/widget/AbsSpinner;Landroid/content/Context;Landroid/util/AttributeSet;II)V
    .locals 2

    .prologue
    .line 35
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "com.miui.internal.variable.v21.Android_Widget_AbsSpinner_class.original_init_(long, AbsSpinner, Context, AttributeSet, int, int)"

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0
.end method
