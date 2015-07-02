.class public Lcom/miui/internal/util/TaggingDrawableHelper;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field private static final Ba:Lcom/miui/internal/variable/Android_View_ViewGroup_class;

.field static Bb:[I

.field private static final Bc:Ljava/lang/ThreadLocal;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/lang/ThreadLocal",
            "<",
            "Landroid/graphics/Rect;",
            ">;"
        }
    .end annotation
.end field

.field private static final bX:Lcom/miui/internal/variable/Android_View_View_class;

.field private static final eE:Lcom/miui/internal/variable/Android_Graphics_Drawable_Drawable_class;


# direct methods
.method static constructor <clinit>()V
    .locals 3

    .prologue
    .line 36
    invoke-static {}, Lcom/miui/internal/variable/Android_View_View_class$Factory;->getInstance()Lcom/miui/internal/variable/Android_View_View_class$Factory;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/internal/variable/Android_View_View_class$Factory;->get()Lcom/miui/internal/variable/Android_View_View_class;

    move-result-object v0

    sput-object v0, Lcom/miui/internal/util/TaggingDrawableHelper;->bX:Lcom/miui/internal/variable/Android_View_View_class;

    .line 37
    invoke-static {}, Lcom/miui/internal/variable/Android_View_ViewGroup_class$Factory;->getInstance()Lcom/miui/internal/variable/Android_View_ViewGroup_class$Factory;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/internal/variable/Android_View_ViewGroup_class$Factory;->get()Lcom/miui/internal/variable/Android_View_ViewGroup_class;

    move-result-object v0

    sput-object v0, Lcom/miui/internal/util/TaggingDrawableHelper;->Ba:Lcom/miui/internal/variable/Android_View_ViewGroup_class;

    .line 39
    invoke-static {}, Lcom/miui/internal/variable/Android_Graphics_Drawable_Drawable_class$Factory;->getInstance()Lcom/miui/internal/variable/Android_Graphics_Drawable_Drawable_class$Factory;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/internal/variable/Android_Graphics_Drawable_Drawable_class$Factory;->get()Lcom/miui/internal/variable/Android_Graphics_Drawable_Drawable_class;

    move-result-object v0

    sput-object v0, Lcom/miui/internal/util/TaggingDrawableHelper;->eE:Lcom/miui/internal/variable/Android_Graphics_Drawable_Drawable_class;

    .line 42
    const/4 v0, 0x1

    new-array v0, v0, [I

    const/4 v1, 0x0

    sget v2, Lmiui/R$attr;->children_sequence_state:I

    aput v2, v0, v1

    sput-object v0, Lcom/miui/internal/util/TaggingDrawableHelper;->Bb:[I

    .line 44
    new-instance v0, Ljava/lang/ThreadLocal;

    invoke-direct {v0}, Ljava/lang/ThreadLocal;-><init>()V

    sput-object v0, Lcom/miui/internal/util/TaggingDrawableHelper;->Bc:Ljava/lang/ThreadLocal;

    return-void
.end method

.method private constructor <init>()V
    .locals 0

    .prologue
    .line 32
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 34
    return-void
.end method

.method private static a(Landroid/view/ViewGroup;IZ)I
    .locals 3

    .prologue
    const/4 v2, 0x1

    const/4 v1, 0x0

    .line 67
    instance-of v0, p0, Lmiui/view/OnTaggingDrawableState;

    if-eqz v0, :cond_0

    move-object v0, p0

    .line 68
    check-cast v0, Lmiui/view/OnTaggingDrawableState;

    .line 69
    invoke-interface {v0, p0, p1, p2, v1}, Lmiui/view/OnTaggingDrawableState;->sameWithNeighbour(Landroid/view/ViewGroup;IZZ)Z

    move-result v1

    .line 70
    invoke-interface {v0, p0, p1, p2, v2}, Lmiui/view/OnTaggingDrawableState;->sameWithNeighbour(Landroid/view/ViewGroup;IZZ)Z

    move-result v0

    .line 76
    :goto_0
    if-eqz v1, :cond_4

    .line 77
    if-eqz v0, :cond_2

    .line 78
    if-eqz p2, :cond_1

    sget v0, Lmiui/R$attr;->state_middle_h:I

    .line 89
    :goto_1
    return v0

    .line 72
    :cond_0
    invoke-static {p0, p1, p2, v1}, Lcom/miui/internal/util/TaggingDrawableHelper;->sameWithNeighbour(Landroid/view/ViewGroup;IZZ)Z

    move-result v1

    .line 73
    invoke-static {p0, p1, p2, v2}, Lcom/miui/internal/util/TaggingDrawableHelper;->sameWithNeighbour(Landroid/view/ViewGroup;IZZ)Z

    move-result v0

    goto :goto_0

    .line 78
    :cond_1
    sget v0, Lmiui/R$attr;->state_middle_v:I

    goto :goto_1

    .line 80
    :cond_2
    if-eqz p2, :cond_3

    sget v0, Lmiui/R$attr;->state_last_h:I

    goto :goto_1

    :cond_3
    sget v0, Lmiui/R$attr;->state_last_v:I

    goto :goto_1

    .line 83
    :cond_4
    if-eqz v0, :cond_6

    .line 84
    if-eqz p2, :cond_5

    sget v0, Lmiui/R$attr;->state_first_h:I

    goto :goto_1

    :cond_5
    sget v0, Lmiui/R$attr;->state_first_v:I

    goto :goto_1

    .line 86
    :cond_6
    if-eqz p2, :cond_7

    sget v0, Lmiui/R$attr;->state_single_h:I

    goto :goto_1

    :cond_7
    sget v0, Lmiui/R$attr;->state_single_v:I

    goto :goto_1
.end method

.method private static a(Landroid/view/View;II)V
    .locals 5

    .prologue
    .line 184
    sget-object v0, Lcom/miui/internal/util/TaggingDrawableHelper;->bX:Lcom/miui/internal/variable/Android_View_View_class;

    invoke-virtual {v0, p0}, Lcom/miui/internal/variable/Android_View_View_class;->getHorizontalState(Landroid/view/View;)I

    move-result v0

    .line 185
    sget-object v1, Lcom/miui/internal/util/TaggingDrawableHelper;->bX:Lcom/miui/internal/variable/Android_View_View_class;

    invoke-virtual {v1, p0}, Lcom/miui/internal/variable/Android_View_View_class;->getVerticalState(Landroid/view/View;)I

    move-result v1

    .line 186
    if-ne v0, p1, :cond_0

    if-eq v1, p2, :cond_2

    .line 187
    :cond_0
    sget-object v0, Lcom/miui/internal/util/TaggingDrawableHelper;->bX:Lcom/miui/internal/variable/Android_View_View_class;

    invoke-virtual {v0, p0, p1}, Lcom/miui/internal/variable/Android_View_View_class;->setHorizontalState(Landroid/view/View;I)V

    .line 188
    sget-object v0, Lcom/miui/internal/util/TaggingDrawableHelper;->bX:Lcom/miui/internal/variable/Android_View_View_class;

    invoke-virtual {v0, p0, p2}, Lcom/miui/internal/variable/Android_View_View_class;->setVerticalState(Landroid/view/View;I)V

    .line 189
    invoke-virtual {p0}, Landroid/view/View;->getPaddingLeft()I

    move-result v0

    .line 190
    invoke-virtual {p0}, Landroid/view/View;->getPaddingTop()I

    move-result v1

    .line 191
    invoke-virtual {p0}, Landroid/view/View;->getPaddingRight()I

    move-result v2

    .line 192
    invoke-virtual {p0}, Landroid/view/View;->getPaddingBottom()I

    move-result v3

    .line 193
    invoke-virtual {p0}, Landroid/view/View;->refreshDrawableState()V

    .line 194
    invoke-virtual {p0}, Landroid/view/View;->getPaddingLeft()I

    move-result v4

    if-ne v0, v4, :cond_1

    invoke-virtual {p0}, Landroid/view/View;->getPaddingTop()I

    move-result v0

    if-ne v1, v0, :cond_1

    invoke-virtual {p0}, Landroid/view/View;->getPaddingRight()I

    move-result v0

    if-ne v2, v0, :cond_1

    invoke-virtual {p0}, Landroid/view/View;->getPaddingBottom()I

    move-result v0

    if-eq v3, v0, :cond_2

    .line 197
    :cond_1
    sget-object v0, Lcom/miui/internal/util/TaggingDrawableHelper;->bX:Lcom/miui/internal/variable/Android_View_View_class;

    invoke-virtual {v0, p0}, Lcom/miui/internal/variable/Android_View_View_class;->relayout(Landroid/view/View;)V

    .line 200
    :cond_2
    return-void
.end method

.method private static a(Landroid/view/View;Landroid/view/View;)Z
    .locals 5

    .prologue
    const/4 v0, 0x1

    const/4 v1, 0x0

    .line 165
    if-eqz p0, :cond_0

    if-nez p1, :cond_2

    :cond_0
    move v0, v1

    .line 180
    :cond_1
    :goto_0
    return v0

    .line 169
    :cond_2
    invoke-virtual {p0}, Landroid/view/View;->getBackground()Landroid/graphics/drawable/Drawable;

    move-result-object v2

    .line 170
    invoke-virtual {p1}, Landroid/view/View;->getBackground()Landroid/graphics/drawable/Drawable;

    move-result-object v3

    .line 171
    if-eq v2, v3, :cond_1

    .line 175
    if-eqz v2, :cond_3

    if-nez v3, :cond_4

    :cond_3
    move v0, v1

    .line 176
    goto :goto_0

    .line 178
    :cond_4
    sget-object v4, Lcom/miui/internal/util/TaggingDrawableHelper;->eE:Lcom/miui/internal/variable/Android_Graphics_Drawable_Drawable_class;

    invoke-virtual {v4, v2}, Lcom/miui/internal/variable/Android_Graphics_Drawable_Drawable_class;->getId(Landroid/graphics/drawable/Drawable;)I

    move-result v2

    .line 179
    sget-object v4, Lcom/miui/internal/util/TaggingDrawableHelper;->eE:Lcom/miui/internal/variable/Android_Graphics_Drawable_Drawable_class;

    invoke-virtual {v4, v3}, Lcom/miui/internal/variable/Android_Graphics_Drawable_Drawable_class;->getId(Landroid/graphics/drawable/Drawable;)I

    move-result v3

    .line 180
    if-eqz v2, :cond_5

    if-eq v2, v3, :cond_1

    :cond_5
    move v0, v1

    goto :goto_0
.end method

.method private static b(Landroid/view/ViewGroup;IZ)Z
    .locals 9

    .prologue
    const/4 v1, 0x1

    const/4 v0, -0x1

    .line 93
    if-eqz p2, :cond_1

    move v5, v1

    .line 94
    :goto_0
    if-eqz p2, :cond_0

    invoke-virtual {p0}, Landroid/view/ViewGroup;->getChildCount()I

    move-result v0

    .line 95
    :cond_0
    invoke-virtual {p0, p1}, Landroid/view/ViewGroup;->getChildAt(I)Landroid/view/View;

    move-result-object v6

    .line 96
    const/4 v3, 0x0

    .line 97
    add-int v2, p1, v5

    move v4, v2

    :goto_1
    if-eq v4, v0, :cond_4

    .line 98
    invoke-virtual {p0, v4}, Landroid/view/ViewGroup;->getChildAt(I)Landroid/view/View;

    move-result-object v2

    .line 99
    invoke-virtual {v2}, Landroid/view/View;->getVisibility()I

    move-result v7

    const/16 v8, 0x8

    if-eq v7, v8, :cond_2

    move-object v0, v2

    .line 104
    :goto_2
    if-eqz v0, :cond_3

    invoke-virtual {v0}, Landroid/view/View;->getVisibility()I

    move-result v2

    const/4 v3, 0x4

    if-eq v2, v3, :cond_3

    invoke-static {v0, v6}, Lcom/miui/internal/util/TaggingDrawableHelper;->a(Landroid/view/View;Landroid/view/View;)Z

    move-result v0

    if-eqz v0, :cond_3

    move v0, v1

    :goto_3
    return v0

    :cond_1
    move v5, v0

    .line 93
    goto :goto_0

    .line 97
    :cond_2
    add-int v2, v4, v5

    move v4, v2

    goto :goto_1

    .line 104
    :cond_3
    const/4 v0, 0x0

    goto :goto_3

    :cond_4
    move-object v0, v3

    goto :goto_2
.end method

.method public static initTagChildSequenceState(Landroid/view/ViewGroup;Landroid/util/AttributeSet;)V
    .locals 3

    .prologue
    .line 236
    invoke-virtual {p0}, Landroid/view/ViewGroup;->getContext()Landroid/content/Context;

    move-result-object v0

    sget-object v1, Lcom/miui/internal/util/TaggingDrawableHelper;->Bb:[I

    invoke-virtual {v0, p1, v1}, Landroid/content/Context;->obtainStyledAttributes(Landroid/util/AttributeSet;[I)Landroid/content/res/TypedArray;

    move-result-object v0

    .line 237
    const/4 v1, 0x0

    const/4 v2, 0x1

    invoke-virtual {v0, v1, v2}, Landroid/content/res/TypedArray;->getBoolean(IZ)Z

    move-result v1

    .line 238
    invoke-virtual {v0}, Landroid/content/res/TypedArray;->recycle()V

    .line 239
    sget-object v0, Lcom/miui/internal/util/TaggingDrawableHelper;->Ba:Lcom/miui/internal/variable/Android_View_ViewGroup_class;

    invoke-virtual {v0, p0, v1}, Lcom/miui/internal/variable/Android_View_ViewGroup_class;->setTagChildrenSequenceState(Landroid/view/ViewGroup;Z)V

    .line 240
    return-void
.end method

.method public static initViewSequenceStates(Landroid/view/View;Landroid/util/AttributeSet;)V
    .locals 7

    .prologue
    const/4 v3, 0x0

    .line 243
    invoke-virtual {p0}, Landroid/view/View;->getContext()Landroid/content/Context;

    move-result-object v0

    sget-object v1, Lmiui/R$styleable;->DrawableStates:[I

    invoke-virtual {v0, p1, v1}, Landroid/content/Context;->obtainStyledAttributes(Landroid/util/AttributeSet;[I)Landroid/content/res/TypedArray;

    move-result-object v4

    .line 244
    sget-object v0, Lmiui/R$styleable;->DrawableStates:[I

    array-length v5, v0

    .line 245
    sget v1, Lmiui/R$attr;->state_single_h:I

    .line 246
    sget v0, Lmiui/R$attr;->state_single_v:I

    move v2, v3

    .line 247
    :goto_0
    if-ge v2, v5, :cond_4

    .line 248
    invoke-virtual {v4, v2, v3}, Landroid/content/res/TypedArray;->getBoolean(IZ)Z

    move-result v6

    if-eqz v6, :cond_1

    .line 249
    if-eqz v2, :cond_0

    const/4 v6, 0x1

    if-eq v2, v6, :cond_0

    const/4 v6, 0x2

    if-eq v2, v6, :cond_0

    const/4 v6, 0x3

    if-ne v2, v6, :cond_2

    .line 253
    :cond_0
    sget-object v1, Lmiui/R$styleable;->DrawableStates:[I

    aget v1, v1, v2

    .line 247
    :cond_1
    :goto_1
    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    .line 254
    :cond_2
    const/4 v6, 0x4

    if-eq v2, v6, :cond_3

    const/4 v6, 0x5

    if-eq v2, v6, :cond_3

    const/4 v6, 0x6

    if-eq v2, v6, :cond_3

    const/4 v6, 0x7

    if-ne v2, v6, :cond_1

    .line 258
    :cond_3
    sget-object v0, Lmiui/R$styleable;->DrawableStates:[I

    aget v0, v0, v2

    goto :goto_1

    .line 262
    :cond_4
    invoke-virtual {v4}, Landroid/content/res/TypedArray;->recycle()V

    .line 263
    sget-object v2, Lcom/miui/internal/util/TaggingDrawableHelper;->bX:Lcom/miui/internal/variable/Android_View_View_class;

    invoke-virtual {v2, p0, v1}, Lcom/miui/internal/variable/Android_View_View_class;->setHorizontalState(Landroid/view/View;I)V

    .line 264
    sget-object v1, Lcom/miui/internal/util/TaggingDrawableHelper;->bX:Lcom/miui/internal/variable/Android_View_View_class;

    invoke-virtual {v1, p0, v0}, Lcom/miui/internal/variable/Android_View_View_class;->setVerticalState(Landroid/view/View;I)V

    .line 265
    return-void
.end method

.method public static onDrawableStateChange(Landroid/view/View;)V
    .locals 4

    .prologue
    .line 203
    invoke-virtual {p0}, Landroid/view/View;->getBackground()Landroid/graphics/drawable/Drawable;

    move-result-object v1

    .line 204
    if-eqz v1, :cond_0

    invoke-virtual {v1}, Landroid/graphics/drawable/Drawable;->isStateful()Z

    move-result v0

    if-nez v0, :cond_1

    .line 233
    :cond_0
    :goto_0
    return-void

    .line 208
    :cond_1
    invoke-virtual {v1}, Landroid/graphics/drawable/Drawable;->getConstantState()Landroid/graphics/drawable/Drawable$ConstantState;

    move-result-object v0

    .line 209
    invoke-virtual {v1}, Landroid/graphics/drawable/Drawable;->isStateful()Z

    move-result v2

    if-eqz v2, :cond_0

    if-eqz v0, :cond_0

    instance-of v2, v0, Landroid/graphics/drawable/DrawableContainer$DrawableContainerState;

    if-eqz v2, :cond_0

    .line 210
    check-cast v0, Landroid/graphics/drawable/DrawableContainer$DrawableContainerState;

    invoke-virtual {v0}, Landroid/graphics/drawable/DrawableContainer$DrawableContainerState;->getConstantPadding()Landroid/graphics/Rect;

    move-result-object v0

    if-nez v0, :cond_0

    .line 211
    sget-object v0, Lcom/miui/internal/util/TaggingDrawableHelper;->Bc:Ljava/lang/ThreadLocal;

    invoke-virtual {v0}, Ljava/lang/ThreadLocal;->get()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/graphics/Rect;

    .line 212
    if-nez v0, :cond_2

    .line 213
    new-instance v0, Landroid/graphics/Rect;

    invoke-direct {v0}, Landroid/graphics/Rect;-><init>()V

    .line 214
    sget-object v2, Lcom/miui/internal/util/TaggingDrawableHelper;->Bc:Ljava/lang/ThreadLocal;

    invoke-virtual {v2, v0}, Ljava/lang/ThreadLocal;->set(Ljava/lang/Object;)V

    .line 216
    :cond_2
    invoke-virtual {v1, v0}, Landroid/graphics/drawable/Drawable;->getPadding(Landroid/graphics/Rect;)Z

    move-result v2

    if-eqz v2, :cond_0

    .line 217
    iget v2, v0, Landroid/graphics/Rect;->left:I

    invoke-virtual {p0}, Landroid/view/View;->getPaddingLeft()I

    move-result v3

    if-ne v2, v3, :cond_3

    iget v2, v0, Landroid/graphics/Rect;->top:I

    invoke-virtual {p0}, Landroid/view/View;->getPaddingTop()I

    move-result v3

    if-ne v2, v3, :cond_3

    iget v2, v0, Landroid/graphics/Rect;->right:I

    invoke-virtual {p0}, Landroid/view/View;->getPaddingRight()I

    move-result v3

    if-ne v2, v3, :cond_3

    iget v2, v0, Landroid/graphics/Rect;->bottom:I

    invoke-virtual {p0}, Landroid/view/View;->getPaddingBottom()I

    move-result v3

    if-eq v2, v3, :cond_0

    .line 219
    :cond_3
    sget-object v2, Lcom/miui/internal/util/TaggingDrawableHelper;->eE:Lcom/miui/internal/variable/Android_Graphics_Drawable_Drawable_class;

    invoke-virtual {v2, v1}, Lcom/miui/internal/variable/Android_Graphics_Drawable_Drawable_class;->getLayoutDirection(Landroid/graphics/drawable/Drawable;)I

    move-result v1

    packed-switch v1, :pswitch_data_0

    .line 225
    iget v1, v0, Landroid/graphics/Rect;->left:I

    iget v2, v0, Landroid/graphics/Rect;->top:I

    iget v3, v0, Landroid/graphics/Rect;->right:I

    iget v0, v0, Landroid/graphics/Rect;->bottom:I

    invoke-virtual {p0, v1, v2, v3, v0}, Landroid/view/View;->setPadding(IIII)V

    goto :goto_0

    .line 221
    :pswitch_0
    iget v1, v0, Landroid/graphics/Rect;->right:I

    iget v2, v0, Landroid/graphics/Rect;->top:I

    iget v3, v0, Landroid/graphics/Rect;->left:I

    iget v0, v0, Landroid/graphics/Rect;->bottom:I

    invoke-virtual {p0, v1, v2, v3, v0}, Landroid/view/View;->setPadding(IIII)V

    goto :goto_0

    .line 219
    :pswitch_data_0
    .packed-switch 0x1
        :pswitch_0
    .end packed-switch
.end method

.method private static sameWithNeighbour(Landroid/view/ViewGroup;IZZ)Z
    .locals 8

    .prologue
    const/4 v2, 0x0

    const/4 v1, 0x1

    .line 108
    .line 109
    instance-of v0, p0, Landroid/widget/AbsListView;

    if-eqz v0, :cond_8

    .line 110
    check-cast p0, Landroid/widget/AbsListView;

    .line 111
    instance-of v0, p0, Landroid/widget/GridView;

    if-eqz v0, :cond_2

    move-object v0, p0

    check-cast v0, Landroid/widget/GridView;

    invoke-virtual {v0}, Landroid/widget/GridView;->getNumColumns()I

    move-result v0

    .line 112
    :goto_0
    invoke-virtual {p0}, Landroid/widget/AbsListView;->getChildCount()I

    move-result v3

    .line 113
    if-eqz p2, :cond_4

    .line 114
    rem-int v4, p1, v0

    .line 115
    if-eq v0, v1, :cond_e

    .line 116
    if-eqz p3, :cond_3

    .line 117
    add-int/lit8 v1, v4, 0x1

    if-ge v1, v0, :cond_e

    add-int/lit8 v0, p1, 0x1

    if-ge v0, v3, :cond_e

    .line 118
    invoke-virtual {p0, p1}, Landroid/widget/AbsListView;->getChildAt(I)Landroid/view/View;

    move-result-object v0

    add-int/lit8 v1, p1, 0x1

    invoke-virtual {p0, v1}, Landroid/widget/AbsListView;->getChildAt(I)Landroid/view/View;

    move-result-object v1

    invoke-static {v0, v1}, Lcom/miui/internal/util/TaggingDrawableHelper;->a(Landroid/view/View;Landroid/view/View;)Z

    move-result v0

    :goto_1
    move v1, v0

    :cond_0
    :goto_2
    move v2, v1

    .line 161
    :cond_1
    :goto_3
    return v2

    :cond_2
    move v0, v1

    .line 111
    goto :goto_0

    .line 121
    :cond_3
    add-int/lit8 v0, v4, -0x1

    if-ltz v0, :cond_e

    .line 122
    invoke-virtual {p0, p1}, Landroid/widget/AbsListView;->getChildAt(I)Landroid/view/View;

    move-result-object v0

    add-int/lit8 v1, p1, -0x1

    invoke-virtual {p0, v1}, Landroid/widget/AbsListView;->getChildAt(I)Landroid/view/View;

    move-result-object v1

    invoke-static {v0, v1}, Lcom/miui/internal/util/TaggingDrawableHelper;->a(Landroid/view/View;Landroid/view/View;)Z

    move-result v0

    goto :goto_1

    .line 127
    :cond_4
    invoke-virtual {p0}, Landroid/widget/AbsListView;->getFirstVisiblePosition()I

    move-result v4

    .line 128
    invoke-virtual {p0}, Landroid/widget/AbsListView;->getAdapter()Landroid/widget/Adapter;

    move-result-object v5

    .line 129
    invoke-virtual {p0}, Landroid/widget/AbsListView;->getCount()I

    move-result v6

    .line 131
    if-eqz p3, :cond_6

    .line 132
    add-int v7, p1, v0

    if-ge v7, v3, :cond_5

    .line 133
    invoke-virtual {p0, p1}, Landroid/widget/AbsListView;->getChildAt(I)Landroid/view/View;

    move-result-object v1

    add-int/2addr v0, p1

    invoke-virtual {p0, v0}, Landroid/widget/AbsListView;->getChildAt(I)Landroid/view/View;

    move-result-object v0

    invoke-static {v1, v0}, Lcom/miui/internal/util/TaggingDrawableHelper;->a(Landroid/view/View;Landroid/view/View;)Z

    move-result v1

    goto :goto_2

    .line 134
    :cond_5
    add-int v3, p1, v0

    add-int/2addr v3, v4

    if-ge v3, v6, :cond_d

    .line 135
    add-int v3, p1, v4

    invoke-interface {v5, v3}, Landroid/widget/Adapter;->getItemViewType(I)I

    move-result v3

    add-int/2addr v0, p1

    add-int/2addr v0, v4

    invoke-interface {v5, v0}, Landroid/widget/Adapter;->getItemViewType(I)I

    move-result v0

    if-eq v3, v0, :cond_0

    move v1, v2

    goto :goto_2

    .line 138
    :cond_6
    sub-int v3, p1, v0

    if-ltz v3, :cond_7

    .line 139
    invoke-virtual {p0, p1}, Landroid/widget/AbsListView;->getChildAt(I)Landroid/view/View;

    move-result-object v1

    sub-int v0, p1, v0

    invoke-virtual {p0, v0}, Landroid/widget/AbsListView;->getChildAt(I)Landroid/view/View;

    move-result-object v0

    invoke-static {v1, v0}, Lcom/miui/internal/util/TaggingDrawableHelper;->a(Landroid/view/View;Landroid/view/View;)Z

    move-result v1

    goto :goto_2

    .line 140
    :cond_7
    sub-int v3, p1, v0

    add-int/2addr v3, v4

    if-ltz v3, :cond_d

    .line 141
    add-int v3, p1, v4

    invoke-interface {v5, v3}, Landroid/widget/Adapter;->getItemViewType(I)I

    move-result v3

    sub-int v0, p1, v0

    add-int/2addr v0, v4

    invoke-interface {v5, v0}, Landroid/widget/Adapter;->getItemViewType(I)I

    move-result v0

    if-eq v3, v0, :cond_0

    move v1, v2

    goto :goto_2

    .line 147
    :cond_8
    instance-of v0, p0, Landroid/widget/LinearLayout;

    if-eqz v0, :cond_c

    move-object v0, p0

    .line 148
    check-cast v0, Landroid/widget/LinearLayout;

    .line 149
    invoke-virtual {v0}, Landroid/widget/LinearLayout;->getOrientation()I

    move-result v0

    .line 150
    if-eqz p2, :cond_9

    if-eqz v0, :cond_a

    :cond_9
    if-nez p2, :cond_c

    if-ne v0, v1, :cond_c

    .line 157
    :cond_a
    :goto_4
    if-nez v1, :cond_b

    instance-of v0, p0, Landroid/widget/LinearLayout;

    if-nez v0, :cond_1

    if-eqz p2, :cond_1

    .line 158
    :cond_b
    invoke-static {p0, p1, p3}, Lcom/miui/internal/util/TaggingDrawableHelper;->b(Landroid/view/ViewGroup;IZ)Z

    move-result v2

    goto/16 :goto_3

    :cond_c
    move v1, v2

    goto :goto_4

    :cond_d
    move v1, v2

    goto/16 :goto_2

    :cond_e
    move v0, v2

    goto/16 :goto_1
.end method

.method public static tagChildSequenceState(Landroid/view/ViewGroup;)V
    .locals 6

    .prologue
    const/4 v1, 0x0

    .line 47
    sget-object v0, Lcom/miui/internal/util/TaggingDrawableHelper;->Ba:Lcom/miui/internal/variable/Android_View_ViewGroup_class;

    invoke-virtual {v0, p0}, Lcom/miui/internal/variable/Android_View_ViewGroup_class;->getTagChildrenSequenceState(Landroid/view/ViewGroup;)Z

    move-result v0

    .line 48
    if-nez v0, :cond_1

    .line 61
    :cond_0
    return-void

    .line 52
    :cond_1
    invoke-virtual {p0}, Landroid/view/ViewGroup;->getChildCount()I

    move-result v2

    move v0, v1

    .line 53
    :goto_0
    if-ge v0, v2, :cond_0

    .line 54
    invoke-virtual {p0, v0}, Landroid/view/ViewGroup;->getChildAt(I)Landroid/view/View;

    move-result-object v3

    .line 55
    invoke-virtual {v3}, Landroid/view/View;->getVisibility()I

    move-result v4

    if-nez v4, :cond_2

    .line 56
    const/4 v4, 0x1

    invoke-static {p0, v0, v4}, Lcom/miui/internal/util/TaggingDrawableHelper;->a(Landroid/view/ViewGroup;IZ)I

    move-result v4

    .line 57
    invoke-static {p0, v0, v1}, Lcom/miui/internal/util/TaggingDrawableHelper;->a(Landroid/view/ViewGroup;IZ)I

    move-result v5

    .line 58
    invoke-static {v3, v4, v5}, Lcom/miui/internal/util/TaggingDrawableHelper;->a(Landroid/view/View;II)V

    .line 53
    :cond_2
    add-int/lit8 v0, v0, 0x1

    goto :goto_0
.end method
