.class public Lmiui/util/ViewUtils;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field private static final Ba:Lcom/miui/internal/variable/Android_View_ViewGroup_class;

.field private static final Ds:Lcom/miui/internal/variable/Android_Graphics_Drawable_StateListDrawable_class;

.field private static final Dt:I = 0x2710

.field static final TAG:Ljava/lang/String; = "ViewUtils"

.field private static final bX:Lcom/miui/internal/variable/Android_View_View_class;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .prologue
    .line 33
    invoke-static {}, Lcom/miui/internal/variable/Android_Graphics_Drawable_StateListDrawable_class$Factory;->getInstance()Lcom/miui/internal/variable/Android_Graphics_Drawable_StateListDrawable_class$Factory;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/internal/variable/Android_Graphics_Drawable_StateListDrawable_class$Factory;->get()Lcom/miui/internal/variable/Android_Graphics_Drawable_StateListDrawable_class;

    move-result-object v0

    sput-object v0, Lmiui/util/ViewUtils;->Ds:Lcom/miui/internal/variable/Android_Graphics_Drawable_StateListDrawable_class;

    .line 36
    invoke-static {}, Lcom/miui/internal/variable/Android_View_View_class$Factory;->getInstance()Lcom/miui/internal/variable/Android_View_View_class$Factory;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/internal/variable/Android_View_View_class$Factory;->get()Lcom/miui/internal/variable/Android_View_View_class;

    move-result-object v0

    sput-object v0, Lmiui/util/ViewUtils;->bX:Lcom/miui/internal/variable/Android_View_View_class;

    .line 39
    invoke-static {}, Lcom/miui/internal/variable/Android_View_ViewGroup_class$Factory;->getInstance()Lcom/miui/internal/variable/Android_View_ViewGroup_class$Factory;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/internal/variable/Android_View_ViewGroup_class$Factory;->get()Lcom/miui/internal/variable/Android_View_ViewGroup_class;

    move-result-object v0

    sput-object v0, Lmiui/util/ViewUtils;->Ba:Lcom/miui/internal/variable/Android_View_ViewGroup_class;

    return-void
.end method

.method private constructor <init>()V
    .locals 0

    .prologue
    .line 48
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 50
    return-void
.end method

.method private static a(Landroid/content/res/Resources;Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;II)Landroid/graphics/drawable/Drawable;
    .locals 5

    .prologue
    const/4 v4, 0x0

    .line 269
    if-eqz p1, :cond_0

    if-eqz p2, :cond_0

    if-lez p3, :cond_0

    if-gtz p4, :cond_1

    .line 280
    :cond_0
    :goto_0
    return-object p1

    .line 273
    :cond_1
    sget-object v0, Landroid/graphics/Bitmap$Config;->ARGB_8888:Landroid/graphics/Bitmap$Config;

    invoke-static {p3, p4, v0}, Landroid/graphics/Bitmap;->createBitmap(IILandroid/graphics/Bitmap$Config;)Landroid/graphics/Bitmap;

    move-result-object v0

    .line 274
    new-instance v1, Landroid/graphics/Canvas;

    invoke-direct {v1, v0}, Landroid/graphics/Canvas;-><init>(Landroid/graphics/Bitmap;)V

    .line 275
    new-instance v2, Landroid/graphics/PaintFlagsDrawFilter;

    const/4 v3, 0x3

    invoke-direct {v2, v4, v3}, Landroid/graphics/PaintFlagsDrawFilter;-><init>(II)V

    invoke-virtual {v1, v2}, Landroid/graphics/Canvas;->setDrawFilter(Landroid/graphics/DrawFilter;)V

    .line 276
    const/16 v2, 0x2710

    invoke-virtual {p1, v2}, Landroid/graphics/drawable/Drawable;->setLevel(I)Z

    .line 277
    invoke-virtual {p1, v4, v4, p3, p4}, Landroid/graphics/drawable/Drawable;->setBounds(IIII)V

    .line 278
    invoke-virtual {p1, v1}, Landroid/graphics/drawable/Drawable;->draw(Landroid/graphics/Canvas;)V

    .line 279
    invoke-virtual {p2, v1}, Landroid/graphics/drawable/Drawable;->draw(Landroid/graphics/Canvas;)V

    .line 280
    new-instance p1, Landroid/graphics/drawable/BitmapDrawable;

    invoke-direct {p1, p0, v0}, Landroid/graphics/drawable/BitmapDrawable;-><init>(Landroid/content/res/Resources;Landroid/graphics/Bitmap;)V

    goto :goto_0
.end method

.method public static containsPoint(Landroid/view/View;II)Z
    .locals 1

    .prologue
    .line 91
    invoke-virtual {p0}, Landroid/view/View;->getLeft()I

    move-result v0

    if-le p1, v0, :cond_0

    invoke-virtual {p0}, Landroid/view/View;->getRight()I

    move-result v0

    if-ge p1, v0, :cond_0

    invoke-virtual {p0}, Landroid/view/View;->getTop()I

    move-result v0

    if-le p2, v0, :cond_0

    invoke-virtual {p0}, Landroid/view/View;->getBottom()I

    move-result v0

    if-ge p2, v0, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public static getBackgroundHeight(Landroid/view/View;)I
    .locals 1

    .prologue
    .line 105
    invoke-virtual {p0}, Landroid/view/View;->getBackground()Landroid/graphics/drawable/Drawable;

    move-result-object v0

    .line 106
    if-eqz v0, :cond_0

    .line 107
    invoke-virtual {v0}, Landroid/graphics/drawable/Drawable;->getIntrinsicHeight()I

    move-result v0

    .line 109
    :goto_0
    return v0

    :cond_0
    const/4 v0, -0x1

    goto :goto_0
.end method

.method public static getBackgroundWidth(Landroid/view/View;)I
    .locals 1

    .prologue
    .line 120
    invoke-virtual {p0}, Landroid/view/View;->getBackground()Landroid/graphics/drawable/Drawable;

    move-result-object v0

    .line 121
    if-eqz v0, :cond_0

    .line 122
    invoke-virtual {v0}, Landroid/graphics/drawable/Drawable;->getIntrinsicWidth()I

    move-result v0

    .line 124
    :goto_0
    return v0

    :cond_0
    const/4 v0, -0x1

    goto :goto_0
.end method

.method public static getContentRect(Landroid/view/View;Landroid/graphics/Rect;)V
    .locals 2

    .prologue
    .line 59
    invoke-virtual {p0}, Landroid/view/View;->getScrollX()I

    move-result v0

    invoke-virtual {p0}, Landroid/view/View;->getPaddingLeft()I

    move-result v1

    add-int/2addr v0, v1

    iput v0, p1, Landroid/graphics/Rect;->left:I

    .line 60
    invoke-virtual {p0}, Landroid/view/View;->getScrollY()I

    move-result v0

    invoke-virtual {p0}, Landroid/view/View;->getPaddingTop()I

    move-result v1

    add-int/2addr v0, v1

    iput v0, p1, Landroid/graphics/Rect;->top:I

    .line 61
    invoke-virtual {p0}, Landroid/view/View;->getWidth()I

    move-result v0

    invoke-virtual {p0}, Landroid/view/View;->getPaddingRight()I

    move-result v1

    sub-int/2addr v0, v1

    iget v1, p1, Landroid/graphics/Rect;->left:I

    sub-int/2addr v0, v1

    iput v0, p1, Landroid/graphics/Rect;->right:I

    .line 62
    invoke-virtual {p0}, Landroid/view/View;->getHeight()I

    move-result v0

    invoke-virtual {p0}, Landroid/view/View;->getPaddingBottom()I

    move-result v1

    sub-int/2addr v0, v1

    iget v1, p1, Landroid/graphics/Rect;->top:I

    sub-int/2addr v0, v1

    iput v0, p1, Landroid/graphics/Rect;->bottom:I

    .line 63
    return-void
.end method

.method public static getHorizontalState(Landroid/view/View;)I
    .locals 1

    .prologue
    .line 134
    sget-object v0, Lmiui/util/ViewUtils;->bX:Lcom/miui/internal/variable/Android_View_View_class;

    invoke-virtual {v0, p0}, Lcom/miui/internal/variable/Android_View_View_class;->getHorizontalState(Landroid/view/View;)I

    move-result v0

    return v0
.end method

.method public static getTagChildrenSequenceState(Landroid/view/ViewGroup;)Z
    .locals 1

    .prologue
    .line 179
    sget-object v0, Lmiui/util/ViewUtils;->Ba:Lcom/miui/internal/variable/Android_View_ViewGroup_class;

    invoke-virtual {v0, p0}, Lcom/miui/internal/variable/Android_View_ViewGroup_class;->getTagChildrenSequenceState(Landroid/view/ViewGroup;)Z

    move-result v0

    return v0
.end method

.method public static getVerticalState(Landroid/view/View;)I
    .locals 1

    .prologue
    .line 144
    sget-object v0, Lmiui/util/ViewUtils;->bX:Lcom/miui/internal/variable/Android_View_View_class;

    invoke-virtual {v0, p0}, Lcom/miui/internal/variable/Android_View_View_class;->getVerticalState(Landroid/view/View;)I

    move-result v0

    return v0
.end method

.method public static intersectsWith(Landroid/view/View;Landroid/graphics/Rect;)Z
    .locals 2

    .prologue
    .line 75
    if-eqz p1, :cond_0

    invoke-virtual {p0}, Landroid/view/View;->getLeft()I

    move-result v0

    iget v1, p1, Landroid/graphics/Rect;->right:I

    if-ge v0, v1, :cond_0

    invoke-virtual {p0}, Landroid/view/View;->getTop()I

    move-result v0

    iget v1, p1, Landroid/graphics/Rect;->bottom:I

    if-ge v0, v1, :cond_0

    invoke-virtual {p0}, Landroid/view/View;->getRight()I

    move-result v0

    iget v1, p1, Landroid/graphics/Rect;->left:I

    if-le v0, v1, :cond_0

    invoke-virtual {p0}, Landroid/view/View;->getBottom()I

    move-result v0

    iget v1, p1, Landroid/graphics/Rect;->top:I

    if-le v0, v1, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public static setHorizontalState(Landroid/view/View;I)V
    .locals 1

    .prologue
    .line 157
    sget-object v0, Lmiui/util/ViewUtils;->bX:Lcom/miui/internal/variable/Android_View_View_class;

    invoke-virtual {v0, p0, p1}, Lcom/miui/internal/variable/Android_View_View_class;->setHorizontalState(Landroid/view/View;I)V

    .line 158
    return-void
.end method

.method public static setTagChildrenSequenceState(Landroid/view/ViewGroup;Z)V
    .locals 1

    .prologue
    .line 192
    sget-object v0, Lmiui/util/ViewUtils;->Ba:Lcom/miui/internal/variable/Android_View_ViewGroup_class;

    invoke-virtual {v0, p0, p1}, Lcom/miui/internal/variable/Android_View_ViewGroup_class;->setTagChildrenSequenceState(Landroid/view/ViewGroup;Z)V

    .line 193
    return-void
.end method

.method public static setVerticalState(Landroid/view/View;I)V
    .locals 1

    .prologue
    .line 170
    sget-object v0, Lmiui/util/ViewUtils;->bX:Lcom/miui/internal/variable/Android_View_View_class;

    invoke-virtual {v0, p0, p1}, Lcom/miui/internal/variable/Android_View_View_class;->setVerticalState(Landroid/view/View;I)V

    .line 171
    return-void
.end method

.method public static tileifyForProgressBar(Landroid/content/res/Resources;Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;IIZ)Landroid/graphics/drawable/Drawable;
    .locals 10

    .prologue
    const/16 v9, 0x2710

    const/4 v2, 0x3

    const/4 v1, 0x1

    const/4 v7, 0x0

    .line 201
    instance-of v0, p1, Landroid/graphics/drawable/NinePatchDrawable;

    if-nez v0, :cond_0

    instance-of v0, p1, Landroid/graphics/drawable/BitmapDrawable;

    if-eqz v0, :cond_1

    .line 203
    :cond_0
    invoke-static {p0, p1, p2, p3, p4}, Lmiui/util/ViewUtils;->a(Landroid/content/res/Resources;Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;II)Landroid/graphics/drawable/Drawable;

    move-result-object v0

    .line 204
    new-instance p1, Landroid/graphics/drawable/ClipDrawable;

    invoke-direct {p1, v0, v2, v1}, Landroid/graphics/drawable/ClipDrawable;-><init>(Landroid/graphics/drawable/Drawable;II)V

    .line 265
    :goto_0
    return-object p1

    .line 205
    :cond_1
    instance-of v0, p1, Landroid/graphics/drawable/ClipDrawable;

    if-eqz v0, :cond_2

    .line 206
    invoke-static {p0, p1, p2, p3, p4}, Lmiui/util/ViewUtils;->a(Landroid/content/res/Resources;Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;II)Landroid/graphics/drawable/Drawable;

    move-result-object v0

    .line 207
    if-eq v0, p1, :cond_d

    .line 208
    new-instance p1, Landroid/graphics/drawable/ClipDrawable;

    invoke-direct {p1, v0, v2, v1}, Landroid/graphics/drawable/ClipDrawable;-><init>(Landroid/graphics/drawable/Drawable;II)V

    goto :goto_0

    .line 210
    :cond_2
    instance-of v0, p1, Landroid/graphics/drawable/LayerDrawable;

    if-eqz v0, :cond_7

    .line 211
    check-cast p1, Landroid/graphics/drawable/LayerDrawable;

    .line 212
    invoke-virtual {p1}, Landroid/graphics/drawable/LayerDrawable;->getNumberOfLayers()I

    move-result v8

    .line 213
    new-array v9, v8, [Landroid/graphics/drawable/Drawable;

    move v6, v7

    .line 215
    :goto_1
    if-ge v6, v8, :cond_5

    .line 216
    invoke-virtual {p1, v6}, Landroid/graphics/drawable/LayerDrawable;->getId(I)I

    move-result v0

    .line 217
    invoke-virtual {p1, v6}, Landroid/graphics/drawable/LayerDrawable;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v1

    .line 218
    const v2, 0x102000d

    if-eq v0, v2, :cond_3

    const v2, 0x102000f

    if-ne v0, v2, :cond_4

    :cond_3
    move-object v0, p0

    move-object v2, p2

    move v3, p3

    move v4, p4

    move v5, p5

    .line 219
    invoke-static/range {v0 .. v5}, Lmiui/util/ViewUtils;->tileifyForProgressBar(Landroid/content/res/Resources;Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;IIZ)Landroid/graphics/drawable/Drawable;

    move-result-object v0

    aput-object v0, v9, v6

    .line 215
    :goto_2
    add-int/lit8 v0, v6, 0x1

    move v6, v0

    goto :goto_1

    .line 221
    :cond_4
    aput-object v1, v9, v6

    goto :goto_2

    .line 225
    :cond_5
    new-instance v0, Landroid/graphics/drawable/LayerDrawable;

    invoke-direct {v0, v9}, Landroid/graphics/drawable/LayerDrawable;-><init>([Landroid/graphics/drawable/Drawable;)V

    .line 226
    :goto_3
    if-ge v7, v8, :cond_6

    .line 227
    invoke-virtual {p1, v7}, Landroid/graphics/drawable/LayerDrawable;->getId(I)I

    move-result v1

    invoke-virtual {v0, v7, v1}, Landroid/graphics/drawable/LayerDrawable;->setId(II)V

    .line 226
    add-int/lit8 v7, v7, 0x1

    goto :goto_3

    :cond_6
    move-object p1, v0

    .line 231
    goto :goto_0

    :cond_7
    instance-of v0, p1, Landroid/graphics/drawable/StateListDrawable;

    if-eqz v0, :cond_9

    .line 232
    check-cast p1, Landroid/graphics/drawable/StateListDrawable;

    .line 233
    new-instance v6, Landroid/graphics/drawable/StateListDrawable;

    invoke-direct {v6}, Landroid/graphics/drawable/StateListDrawable;-><init>()V

    .line 234
    sget-object v0, Lmiui/util/ViewUtils;->Ds:Lcom/miui/internal/variable/Android_Graphics_Drawable_StateListDrawable_class;

    invoke-virtual {v0, p1}, Lcom/miui/internal/variable/Android_Graphics_Drawable_StateListDrawable_class;->getStateCount(Landroid/graphics/drawable/StateListDrawable;)I

    move-result v8

    .line 235
    :goto_4
    if-ge v7, v8, :cond_8

    .line 236
    sget-object v0, Lmiui/util/ViewUtils;->Ds:Lcom/miui/internal/variable/Android_Graphics_Drawable_StateListDrawable_class;

    invoke-virtual {v0, p1, v7}, Lcom/miui/internal/variable/Android_Graphics_Drawable_StateListDrawable_class;->getStateDrawable(Landroid/graphics/drawable/StateListDrawable;I)Landroid/graphics/drawable/Drawable;

    move-result-object v1

    .line 237
    sget-object v0, Lmiui/util/ViewUtils;->Ds:Lcom/miui/internal/variable/Android_Graphics_Drawable_StateListDrawable_class;

    invoke-virtual {v0, p1, v7}, Lcom/miui/internal/variable/Android_Graphics_Drawable_StateListDrawable_class;->getStateSet(Landroid/graphics/drawable/StateListDrawable;I)[I

    move-result-object v9

    move-object v0, p0

    move-object v2, p2

    move v3, p3

    move v4, p4

    move v5, p5

    .line 238
    invoke-static/range {v0 .. v5}, Lmiui/util/ViewUtils;->tileifyForProgressBar(Landroid/content/res/Resources;Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;IIZ)Landroid/graphics/drawable/Drawable;

    move-result-object v0

    .line 239
    invoke-virtual {v6, v9, v0}, Landroid/graphics/drawable/StateListDrawable;->addState([ILandroid/graphics/drawable/Drawable;)V

    .line 235
    add-int/lit8 v7, v7, 0x1

    goto :goto_4

    :cond_8
    move-object p1, v6

    .line 243
    goto/16 :goto_0

    :cond_9
    instance-of v0, p1, Landroid/graphics/drawable/AnimationDrawable;

    if-eqz v0, :cond_c

    .line 244
    check-cast p1, Landroid/graphics/drawable/AnimationDrawable;

    .line 245
    invoke-virtual {p1}, Landroid/graphics/drawable/AnimationDrawable;->getNumberOfFrames()I

    move-result v8

    .line 246
    new-instance v6, Landroid/graphics/drawable/AnimationDrawable;

    invoke-direct {v6}, Landroid/graphics/drawable/AnimationDrawable;-><init>()V

    .line 247
    invoke-virtual {p1}, Landroid/graphics/drawable/AnimationDrawable;->isOneShot()Z

    move-result v0

    invoke-virtual {v6, v0}, Landroid/graphics/drawable/AnimationDrawable;->setOneShot(Z)V

    .line 249
    :goto_5
    if-ge v7, v8, :cond_a

    .line 250
    invoke-virtual {p1, v7}, Landroid/graphics/drawable/AnimationDrawable;->getFrame(I)Landroid/graphics/drawable/Drawable;

    move-result-object v1

    move-object v0, p0

    move-object v2, p2

    move v3, p3

    move v4, p4

    move v5, p5

    invoke-static/range {v0 .. v5}, Lmiui/util/ViewUtils;->tileifyForProgressBar(Landroid/content/res/Resources;Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;IIZ)Landroid/graphics/drawable/Drawable;

    move-result-object v0

    .line 251
    invoke-virtual {v0, v9}, Landroid/graphics/drawable/Drawable;->setLevel(I)Z

    .line 252
    invoke-virtual {p1, v7}, Landroid/graphics/drawable/AnimationDrawable;->getDuration(I)I

    move-result v1

    invoke-virtual {v6, v0, v1}, Landroid/graphics/drawable/AnimationDrawable;->addFrame(Landroid/graphics/drawable/Drawable;I)V

    .line 249
    add-int/lit8 v0, v7, 0x1

    move v7, v0

    goto :goto_5

    .line 254
    :cond_a
    if-eqz p5, :cond_b

    .line 255
    invoke-virtual {v6, v9}, Landroid/graphics/drawable/AnimationDrawable;->setLevel(I)Z

    :goto_6
    move-object p1, v6

    .line 260
    goto/16 :goto_0

    .line 257
    :cond_b
    invoke-virtual {p1}, Landroid/graphics/drawable/AnimationDrawable;->getLevel()I

    move-result v0

    invoke-virtual {v6, v0}, Landroid/graphics/drawable/AnimationDrawable;->setLevel(I)Z

    goto :goto_6

    .line 261
    :cond_c
    const-string v0, "ViewUtils"

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "Unknown Drawable subclass, src="

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    goto/16 :goto_0

    :cond_d
    move-object p1, v0

    goto/16 :goto_0
.end method
