.class public Lcom/miui/internal/variable/v16/Android_Widget_ListView_class;
.super Lcom/miui/internal/variable/Android_Widget_ListView_class;
.source "SourceFile"


# direct methods
.method public constructor <init>()V
    .locals 0

    .prologue
    .line 16
    invoke-direct {p0}, Lcom/miui/internal/variable/Android_Widget_ListView_class;-><init>()V

    return-void
.end method


# virtual methods
.method protected attachSpecialMethod()V
    .locals 1

    .prologue
    .line 34
    const-string v0, "(Landroid/content/Context;Landroid/util/AttributeSet;I)V"

    invoke-virtual {p0, v0}, Lcom/miui/internal/variable/v16/Android_Widget_ListView_class;->attachConstructor(Ljava/lang/String;)J

    .line 35
    return-void
.end method

.method public buildProxy()V
    .locals 2

    .prologue
    .line 21
    const-string v0, "layoutChildren"

    const-string v1, "()V"

    invoke-virtual {p0, v0, v1}, Lcom/miui/internal/variable/v16/Android_Widget_ListView_class;->attachMethod(Ljava/lang/String;Ljava/lang/String;)J

    .line 22
    const-string v0, "fillGap"

    const-string v1, "(Z)V"

    invoke-virtual {p0, v0, v1}, Lcom/miui/internal/variable/v16/Android_Widget_ListView_class;->attachMethod(Ljava/lang/String;Ljava/lang/String;)J

    .line 23
    invoke-virtual {p0}, Lcom/miui/internal/variable/v16/Android_Widget_ListView_class;->attachSpecialMethod()V

    .line 24
    return-void
.end method

.method protected doInit(Landroid/widget/ListView;Landroid/content/Context;)V
    .locals 3

    .prologue
    .line 39
    invoke-virtual {p1}, Landroid/widget/ListView;->getPaddingLeft()I

    move-result v0

    if-nez v0, :cond_0

    invoke-virtual {p1}, Landroid/widget/ListView;->getPaddingRight()I

    move-result v0

    if-nez v0, :cond_0

    .line 40
    invoke-virtual {p2}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    sget v1, Lcom/miui/internal/R$dimen;->listview_horizontal_padding:I

    invoke-virtual {v0, v1}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v0

    .line 42
    if-eqz v0, :cond_0

    .line 43
    invoke-virtual {p1}, Landroid/widget/ListView;->getPaddingTop()I

    move-result v1

    invoke-virtual {p1}, Landroid/widget/ListView;->getPaddingBottom()I

    move-result v2

    invoke-virtual {p1, v0, v1, v0, v2}, Landroid/widget/ListView;->setPadding(IIII)V

    .line 48
    :cond_0
    return-void
.end method

.method protected handle()V
    .locals 7

    .prologue
    const/4 v6, 0x0

    const-wide/16 v1, 0x0

    const/4 v3, 0x0

    .line 28
    invoke-virtual {p0, v1, v2, v3}, Lcom/miui/internal/variable/v16/Android_Widget_ListView_class;->handleLayoutChildren(JLandroid/widget/ListView;)V

    .line 29
    invoke-virtual {p0, v1, v2, v3, v6}, Lcom/miui/internal/variable/v16/Android_Widget_ListView_class;->handleFillGap(JLandroid/widget/ListView;Z)V

    move-object v0, p0

    move-object v4, v3

    move-object v5, v3

    .line 30
    invoke-virtual/range {v0 .. v6}, Lcom/miui/internal/variable/v16/Android_Widget_ListView_class;->handle_init_(JLandroid/widget/ListView;Landroid/content/Context;Landroid/util/AttributeSet;I)V

    .line 31
    return-void
.end method

.method protected handleFillGap(JLandroid/widget/ListView;Z)V
    .locals 0

    .prologue
    .line 61
    invoke-virtual {p0, p1, p2, p3, p4}, Lcom/miui/internal/variable/v16/Android_Widget_ListView_class;->originalFillGap(JLandroid/widget/ListView;Z)V

    .line 62
    invoke-static {p3}, Lcom/miui/internal/util/TaggingDrawableHelper;->tagChildSequenceState(Landroid/view/ViewGroup;)V

    .line 63
    return-void
.end method

.method protected handleLayoutChildren(JLandroid/widget/ListView;)V
    .locals 0

    .prologue
    .line 51
    invoke-virtual {p0, p1, p2, p3}, Lcom/miui/internal/variable/v16/Android_Widget_ListView_class;->originalLayoutChildren(JLandroid/widget/ListView;)V

    .line 52
    invoke-static {p3}, Lcom/miui/internal/util/TaggingDrawableHelper;->tagChildSequenceState(Landroid/view/ViewGroup;)V

    .line 53
    return-void
.end method

.method protected handle_init_(JLandroid/widget/ListView;Landroid/content/Context;Landroid/util/AttributeSet;I)V
    .locals 0

    .prologue
    .line 73
    invoke-virtual/range {p0 .. p6}, Lcom/miui/internal/variable/v16/Android_Widget_ListView_class;->original_init_(JLandroid/widget/ListView;Landroid/content/Context;Landroid/util/AttributeSet;I)V

    .line 74
    invoke-virtual {p0, p3, p4}, Lcom/miui/internal/variable/v16/Android_Widget_ListView_class;->doInit(Landroid/widget/ListView;Landroid/content/Context;)V

    .line 75
    return-void
.end method

.method protected originalFillGap(JLandroid/widget/ListView;Z)V
    .locals 2

    .prologue
    .line 66
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "com.miui.internal.variable.v16.Android_Widget_ListView_class.originalFillGap(long, ListView, boolean)"

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

.method protected originalLayoutChildren(JLandroid/widget/ListView;)V
    .locals 2

    .prologue
    .line 56
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "com.miui.internal.variable.v16.Android_Widget_ListView_class.originalLayoutChildren(long, ListView)"

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

.method protected original_init_(JLandroid/widget/ListView;Landroid/content/Context;Landroid/util/AttributeSet;I)V
    .locals 2

    .prologue
    .line 78
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "com.miui.internal.variable.v16.Android_Widget_ListView_class.original_init_(long, ListView, Context, AttributeSet, int)"

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0
.end method
