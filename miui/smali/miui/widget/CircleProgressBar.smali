.class public Lmiui/widget/CircleProgressBar;
.super Landroid/widget/ProgressBar;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lmiui/widget/CircleProgressBar$OnProgressChangedListener;
    }
.end annotation


# static fields
.field private static final QK:I = 0xa

.field private static final QL:I = 0x12c

.field private static final QM:I = 0x12c


# instance fields
.field private QN:Landroid/graphics/RectF;

.field private QO:Landroid/animation/Animator;

.field private QP:[I

.field private QQ:[Landroid/graphics/drawable/Drawable;

.field private QR:[Landroid/graphics/drawable/Drawable;

.field private QS:[Landroid/graphics/drawable/Drawable;

.field private QT:Lmiui/widget/CircleProgressBar$OnProgressChangedListener;

.field private QU:I

.field private QV:Landroid/graphics/Bitmap;

.field private QW:Landroid/graphics/Canvas;

.field private QX:I

.field private QY:I

.field private QZ:I

.field private Ra:Landroid/graphics/drawable/Drawable;

.field private aP:Landroid/graphics/Paint;


# direct methods
.method public constructor <init>(Landroid/content/Context;)V
    .locals 1

    .prologue
    .line 63
    const/4 v0, 0x0

    invoke-direct {p0, p1, v0}, Lmiui/widget/CircleProgressBar;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    .line 64
    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 1

    .prologue
    .line 67
    const/4 v0, 0x0

    invoke-direct {p0, p1, p2, v0}, Lmiui/widget/CircleProgressBar;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    .line 68
    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V
    .locals 2

    .prologue
    .line 71
    invoke-direct {p0, p1, p2, p3}, Landroid/widget/ProgressBar;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    .line 59
    const/16 v0, 0x12c

    iput v0, p0, Lmiui/widget/CircleProgressBar;->QZ:I

    .line 72
    const/4 v0, 0x0

    invoke-virtual {p0, v0}, Lmiui/widget/CircleProgressBar;->setIndeterminate(Z)V

    .line 74
    new-instance v0, Landroid/graphics/Paint;

    invoke-direct {v0}, Landroid/graphics/Paint;-><init>()V

    iput-object v0, p0, Lmiui/widget/CircleProgressBar;->aP:Landroid/graphics/Paint;

    .line 75
    iget-object v0, p0, Lmiui/widget/CircleProgressBar;->aP:Landroid/graphics/Paint;

    const/high16 v1, -0x1000000

    invoke-virtual {v0, v1}, Landroid/graphics/Paint;->setColor(I)V

    .line 76
    return-void
.end method

.method private a(Landroid/graphics/Canvas;Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;FI)V
    .locals 8

    .prologue
    .line 327
    if-eqz p2, :cond_0

    .line 328
    invoke-virtual {p2, p6}, Landroid/graphics/drawable/Drawable;->setAlpha(I)V

    .line 329
    invoke-virtual {p2, p1}, Landroid/graphics/drawable/Drawable;->draw(Landroid/graphics/Canvas;)V

    .line 333
    :cond_0
    invoke-virtual {p1}, Landroid/graphics/Canvas;->isHardwareAccelerated()Z

    move-result v0

    if-eqz v0, :cond_3

    .line 334
    invoke-virtual {p4}, Landroid/graphics/drawable/Drawable;->getBounds()Landroid/graphics/Rect;

    move-result-object v0

    iget v0, v0, Landroid/graphics/Rect;->left:I

    int-to-float v1, v0

    invoke-virtual {p4}, Landroid/graphics/drawable/Drawable;->getBounds()Landroid/graphics/Rect;

    move-result-object v0

    iget v0, v0, Landroid/graphics/Rect;->top:I

    int-to-float v2, v0

    invoke-virtual {p4}, Landroid/graphics/drawable/Drawable;->getBounds()Landroid/graphics/Rect;

    move-result-object v0

    iget v0, v0, Landroid/graphics/Rect;->right:I

    int-to-float v3, v0

    invoke-virtual {p4}, Landroid/graphics/drawable/Drawable;->getBounds()Landroid/graphics/Rect;

    move-result-object v0

    iget v0, v0, Landroid/graphics/Rect;->bottom:I

    int-to-float v4, v0

    const/4 v5, 0x0

    const/16 v6, 0x10

    move-object v0, p1

    invoke-virtual/range {v0 .. v6}, Landroid/graphics/Canvas;->saveLayer(FFFFLandroid/graphics/Paint;I)I

    .line 337
    iget-object v1, p0, Lmiui/widget/CircleProgressBar;->QN:Landroid/graphics/RectF;

    const/high16 v2, -0x3d4c0000

    const/high16 v0, 0x43b40000

    mul-float v3, v0, p5

    const/4 v4, 0x1

    iget-object v5, p0, Lmiui/widget/CircleProgressBar;->aP:Landroid/graphics/Paint;

    move-object v0, p1

    invoke-virtual/range {v0 .. v5}, Landroid/graphics/Canvas;->drawArc(Landroid/graphics/RectF;FFZLandroid/graphics/Paint;)V

    .line 338
    invoke-virtual {p4, p6}, Landroid/graphics/drawable/Drawable;->setAlpha(I)V

    .line 339
    invoke-virtual {p4, p1}, Landroid/graphics/drawable/Drawable;->draw(Landroid/graphics/Canvas;)V

    .line 340
    invoke-virtual {p1}, Landroid/graphics/Canvas;->restore()V

    .line 360
    :goto_0
    iget-object v0, p0, Lmiui/widget/CircleProgressBar;->Ra:Landroid/graphics/drawable/Drawable;

    .line 361
    if-eqz v0, :cond_1

    .line 362
    invoke-virtual {p1}, Landroid/graphics/Canvas;->save()I

    .line 363
    invoke-virtual {p0}, Lmiui/widget/CircleProgressBar;->getWidth()I

    move-result v1

    invoke-virtual {p0}, Lmiui/widget/CircleProgressBar;->getPaddingLeft()I

    move-result v2

    sub-int/2addr v1, v2

    invoke-virtual {p0}, Lmiui/widget/CircleProgressBar;->getPaddingRight()I

    move-result v2

    sub-int/2addr v1, v2

    div-int/lit8 v1, v1, 0x2

    .line 364
    invoke-virtual {p0}, Lmiui/widget/CircleProgressBar;->getHeight()I

    move-result v2

    invoke-virtual {p0}, Lmiui/widget/CircleProgressBar;->getPaddingTop()I

    move-result v3

    sub-int/2addr v2, v3

    invoke-virtual {p0}, Lmiui/widget/CircleProgressBar;->getPaddingBottom()I

    move-result v3

    sub-int/2addr v2, v3

    div-int/lit8 v2, v2, 0x2

    .line 365
    invoke-virtual {v0}, Landroid/graphics/drawable/Drawable;->getIntrinsicWidth()I

    move-result v3

    .line 366
    invoke-virtual {v0}, Landroid/graphics/drawable/Drawable;->getIntrinsicHeight()I

    move-result v4

    .line 367
    const/high16 v5, 0x43b40000

    invoke-virtual {p0}, Lmiui/widget/CircleProgressBar;->getProgress()I

    move-result v6

    int-to-float v6, v6

    mul-float/2addr v5, v6

    invoke-virtual {p0}, Lmiui/widget/CircleProgressBar;->getMax()I

    move-result v6

    int-to-float v6, v6

    div-float/2addr v5, v6

    int-to-float v6, v1

    int-to-float v7, v2

    invoke-virtual {p1, v5, v6, v7}, Landroid/graphics/Canvas;->rotate(FFF)V

    .line 368
    div-int/lit8 v5, v3, 0x2

    sub-int v5, v1, v5

    div-int/lit8 v6, v4, 0x2

    sub-int v6, v2, v6

    div-int/lit8 v3, v3, 0x2

    add-int/2addr v1, v3

    div-int/lit8 v3, v4, 0x2

    add-int/2addr v2, v3

    invoke-virtual {v0, v5, v6, v1, v2}, Landroid/graphics/drawable/Drawable;->setBounds(IIII)V

    .line 369
    invoke-virtual {v0, p1}, Landroid/graphics/drawable/Drawable;->draw(Landroid/graphics/Canvas;)V

    .line 370
    invoke-virtual {p1}, Landroid/graphics/Canvas;->restore()V

    .line 374
    :cond_1
    if-eqz p3, :cond_2

    .line 375
    invoke-virtual {p3, p6}, Landroid/graphics/drawable/Drawable;->setAlpha(I)V

    .line 376
    invoke-virtual {p3, p1}, Landroid/graphics/drawable/Drawable;->draw(Landroid/graphics/Canvas;)V

    .line 378
    :cond_2
    return-void

    .line 342
    :cond_3
    iget-object v0, p0, Lmiui/widget/CircleProgressBar;->QV:Landroid/graphics/Bitmap;

    if-nez v0, :cond_4

    .line 343
    invoke-virtual {p4}, Landroid/graphics/drawable/Drawable;->getBounds()Landroid/graphics/Rect;

    move-result-object v0

    invoke-virtual {v0}, Landroid/graphics/Rect;->width()I

    move-result v0

    invoke-virtual {p4}, Landroid/graphics/drawable/Drawable;->getBounds()Landroid/graphics/Rect;

    move-result-object v1

    invoke-virtual {v1}, Landroid/graphics/Rect;->height()I

    move-result v1

    sget-object v2, Landroid/graphics/Bitmap$Config;->ARGB_8888:Landroid/graphics/Bitmap$Config;

    invoke-static {v0, v1, v2}, Landroid/graphics/Bitmap;->createBitmap(IILandroid/graphics/Bitmap$Config;)Landroid/graphics/Bitmap;

    move-result-object v0

    iput-object v0, p0, Lmiui/widget/CircleProgressBar;->QV:Landroid/graphics/Bitmap;

    .line 345
    new-instance v0, Landroid/graphics/Canvas;

    iget-object v1, p0, Lmiui/widget/CircleProgressBar;->QV:Landroid/graphics/Bitmap;

    invoke-direct {v0, v1}, Landroid/graphics/Canvas;-><init>(Landroid/graphics/Bitmap;)V

    iput-object v0, p0, Lmiui/widget/CircleProgressBar;->QW:Landroid/graphics/Canvas;

    .line 348
    :cond_4
    iget-object v0, p0, Lmiui/widget/CircleProgressBar;->QV:Landroid/graphics/Bitmap;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Landroid/graphics/Bitmap;->eraseColor(I)V

    .line 349
    iget-object v0, p0, Lmiui/widget/CircleProgressBar;->QW:Landroid/graphics/Canvas;

    invoke-virtual {v0}, Landroid/graphics/Canvas;->save()I

    .line 350
    iget-object v0, p0, Lmiui/widget/CircleProgressBar;->QW:Landroid/graphics/Canvas;

    invoke-virtual {p4}, Landroid/graphics/drawable/Drawable;->getBounds()Landroid/graphics/Rect;

    move-result-object v1

    iget v1, v1, Landroid/graphics/Rect;->left:I

    neg-int v1, v1

    int-to-float v1, v1

    invoke-virtual {p4}, Landroid/graphics/drawable/Drawable;->getBounds()Landroid/graphics/Rect;

    move-result-object v2

    iget v2, v2, Landroid/graphics/Rect;->top:I

    neg-int v2, v2

    int-to-float v2, v2

    invoke-virtual {v0, v1, v2}, Landroid/graphics/Canvas;->translate(FF)V

    .line 351
    iget-object v0, p0, Lmiui/widget/CircleProgressBar;->QW:Landroid/graphics/Canvas;

    iget-object v1, p0, Lmiui/widget/CircleProgressBar;->QN:Landroid/graphics/RectF;

    const/high16 v2, -0x3d4c0000

    const/high16 v3, 0x43b40000

    mul-float/2addr v3, p5

    const/4 v4, 0x1

    iget-object v5, p0, Lmiui/widget/CircleProgressBar;->aP:Landroid/graphics/Paint;

    invoke-virtual/range {v0 .. v5}, Landroid/graphics/Canvas;->drawArc(Landroid/graphics/RectF;FFZLandroid/graphics/Paint;)V

    .line 352
    invoke-virtual {p4, p6}, Landroid/graphics/drawable/Drawable;->setAlpha(I)V

    .line 353
    iget-object v0, p0, Lmiui/widget/CircleProgressBar;->QW:Landroid/graphics/Canvas;

    invoke-virtual {p4, v0}, Landroid/graphics/drawable/Drawable;->draw(Landroid/graphics/Canvas;)V

    .line 354
    iget-object v0, p0, Lmiui/widget/CircleProgressBar;->QW:Landroid/graphics/Canvas;

    invoke-virtual {v0}, Landroid/graphics/Canvas;->restore()V

    .line 356
    iget-object v0, p0, Lmiui/widget/CircleProgressBar;->QV:Landroid/graphics/Bitmap;

    invoke-virtual {p4}, Landroid/graphics/drawable/Drawable;->getBounds()Landroid/graphics/Rect;

    move-result-object v1

    iget v1, v1, Landroid/graphics/Rect;->left:I

    int-to-float v1, v1

    invoke-virtual {p4}, Landroid/graphics/drawable/Drawable;->getBounds()Landroid/graphics/Rect;

    move-result-object v2

    iget v2, v2, Landroid/graphics/Rect;->top:I

    int-to-float v2, v2

    const/4 v3, 0x0

    invoke-virtual {p1, v0, v1, v2, v3}, Landroid/graphics/Canvas;->drawBitmap(Landroid/graphics/Bitmap;FFLandroid/graphics/Paint;)V

    goto/16 :goto_0
.end method

.method private ar(I)Landroid/graphics/drawable/Drawable;
    .locals 1

    .prologue
    .line 171
    iget-object v0, p0, Lmiui/widget/CircleProgressBar;->QQ:[Landroid/graphics/drawable/Drawable;

    if-nez v0, :cond_0

    const/4 v0, 0x0

    :goto_0
    return-object v0

    :cond_0
    iget-object v0, p0, Lmiui/widget/CircleProgressBar;->QQ:[Landroid/graphics/drawable/Drawable;

    aget-object v0, v0, p1

    goto :goto_0
.end method

.method private as(I)Landroid/graphics/drawable/Drawable;
    .locals 1

    .prologue
    .line 175
    iget-object v0, p0, Lmiui/widget/CircleProgressBar;->QR:[Landroid/graphics/drawable/Drawable;

    if-nez v0, :cond_0

    const/4 v0, 0x0

    :goto_0
    return-object v0

    :cond_0
    iget-object v0, p0, Lmiui/widget/CircleProgressBar;->QR:[Landroid/graphics/drawable/Drawable;

    aget-object v0, v0, p1

    goto :goto_0
.end method

.method private at(I)Landroid/graphics/drawable/Drawable;
    .locals 1

    .prologue
    .line 179
    iget-object v0, p0, Lmiui/widget/CircleProgressBar;->QS:[Landroid/graphics/drawable/Drawable;

    if-nez v0, :cond_0

    const/4 v0, 0x0

    :goto_0
    return-object v0

    :cond_0
    iget-object v0, p0, Lmiui/widget/CircleProgressBar;->QS:[Landroid/graphics/drawable/Drawable;

    aget-object v0, v0, p1

    goto :goto_0
.end method

.method private au(I)I
    .locals 2

    .prologue
    .line 233
    mul-int/lit16 v0, p1, 0x3e8

    iget v1, p0, Lmiui/widget/CircleProgressBar;->QZ:I

    div-int/2addr v0, v1

    return v0
.end method

.method private d([I)[Landroid/graphics/drawable/Drawable;
    .locals 7

    .prologue
    const/4 v1, 0x0

    .line 157
    if-nez p1, :cond_0

    .line 158
    const/4 v0, 0x0

    .line 167
    :goto_0
    return-object v0

    .line 160
    :cond_0
    invoke-virtual {p0}, Lmiui/widget/CircleProgressBar;->getContext()Landroid/content/Context;

    move-result-object v0

    invoke-virtual {v0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v3

    .line 161
    array-length v0, p1

    new-array v2, v0, [Landroid/graphics/drawable/Drawable;

    move v0, v1

    .line 162
    :goto_1
    array-length v4, p1

    if-ge v0, v4, :cond_1

    .line 163
    aget v4, p1, v0

    invoke-virtual {v3, v4}, Landroid/content/res/Resources;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v4

    aput-object v4, v2, v0

    .line 164
    aget-object v4, v2, v0

    aget-object v5, v2, v0

    invoke-virtual {v5}, Landroid/graphics/drawable/Drawable;->getIntrinsicWidth()I

    move-result v5

    aget-object v6, v2, v0

    invoke-virtual {v6}, Landroid/graphics/drawable/Drawable;->getIntrinsicHeight()I

    move-result v6

    invoke-virtual {v4, v1, v1, v5, v6}, Landroid/graphics/drawable/Drawable;->setBounds(IIII)V

    .line 162
    add-int/lit8 v0, v0, 0x1

    goto :goto_1

    :cond_1
    move-object v0, v2

    .line 167
    goto :goto_0
.end method

.method private eP()F
    .locals 2

    .prologue
    .line 284
    invoke-virtual {p0}, Lmiui/widget/CircleProgressBar;->getProgress()I

    move-result v0

    int-to-float v0, v0

    invoke-virtual {p0}, Lmiui/widget/CircleProgressBar;->getMax()I

    move-result v1

    int-to-float v1, v1

    div-float/2addr v0, v1

    return v0
.end method

.method private getIntrinsicHeight()I
    .locals 3

    .prologue
    const/4 v2, 0x0

    .line 297
    invoke-direct {p0, v2}, Lmiui/widget/CircleProgressBar;->as(I)Landroid/graphics/drawable/Drawable;

    move-result-object v0

    invoke-virtual {v0}, Landroid/graphics/drawable/Drawable;->getIntrinsicHeight()I

    move-result v0

    .line 298
    iget-object v1, p0, Lmiui/widget/CircleProgressBar;->QS:[Landroid/graphics/drawable/Drawable;

    if-eqz v1, :cond_0

    .line 299
    iget-object v1, p0, Lmiui/widget/CircleProgressBar;->QS:[Landroid/graphics/drawable/Drawable;

    aget-object v1, v1, v2

    invoke-virtual {v1}, Landroid/graphics/drawable/Drawable;->getIntrinsicHeight()I

    move-result v1

    invoke-static {v0, v1}, Ljava/lang/Math;->max(II)I

    move-result v0

    .line 300
    :cond_0
    iget-object v1, p0, Lmiui/widget/CircleProgressBar;->QQ:[Landroid/graphics/drawable/Drawable;

    if-eqz v1, :cond_1

    .line 301
    iget-object v1, p0, Lmiui/widget/CircleProgressBar;->QQ:[Landroid/graphics/drawable/Drawable;

    aget-object v1, v1, v2

    invoke-virtual {v1}, Landroid/graphics/drawable/Drawable;->getIntrinsicHeight()I

    move-result v1

    invoke-static {v0, v1}, Ljava/lang/Math;->max(II)I

    move-result v0

    .line 302
    :cond_1
    return v0
.end method

.method private getIntrinsicWidth()I
    .locals 3

    .prologue
    const/4 v2, 0x0

    .line 288
    invoke-direct {p0, v2}, Lmiui/widget/CircleProgressBar;->as(I)Landroid/graphics/drawable/Drawable;

    move-result-object v0

    invoke-virtual {v0}, Landroid/graphics/drawable/Drawable;->getIntrinsicWidth()I

    move-result v0

    .line 289
    iget-object v1, p0, Lmiui/widget/CircleProgressBar;->QS:[Landroid/graphics/drawable/Drawable;

    if-eqz v1, :cond_0

    .line 290
    iget-object v1, p0, Lmiui/widget/CircleProgressBar;->QS:[Landroid/graphics/drawable/Drawable;

    aget-object v1, v1, v2

    invoke-virtual {v1}, Landroid/graphics/drawable/Drawable;->getIntrinsicWidth()I

    move-result v1

    invoke-static {v0, v1}, Ljava/lang/Math;->max(II)I

    move-result v0

    .line 291
    :cond_0
    iget-object v1, p0, Lmiui/widget/CircleProgressBar;->QQ:[Landroid/graphics/drawable/Drawable;

    if-eqz v1, :cond_1

    .line 292
    iget-object v1, p0, Lmiui/widget/CircleProgressBar;->QQ:[Landroid/graphics/drawable/Drawable;

    aget-object v1, v1, v2

    invoke-virtual {v1}, Landroid/graphics/drawable/Drawable;->getIntrinsicWidth()I

    move-result v1

    invoke-static {v0, v1}, Ljava/lang/Math;->max(II)I

    move-result v0

    .line 293
    :cond_1
    return v0
.end method


# virtual methods
.method protected drawableStateChanged()V
    .locals 4

    .prologue
    .line 238
    invoke-super {p0}, Landroid/widget/ProgressBar;->drawableStateChanged()V

    .line 239
    invoke-virtual {p0}, Lmiui/widget/CircleProgressBar;->getProgressLevelCount()I

    move-result v1

    .line 240
    const/4 v0, 0x0

    :goto_0
    if-ge v0, v1, :cond_3

    .line 241
    iget-object v2, p0, Lmiui/widget/CircleProgressBar;->QQ:[Landroid/graphics/drawable/Drawable;

    if-eqz v2, :cond_0

    iget-object v2, p0, Lmiui/widget/CircleProgressBar;->QQ:[Landroid/graphics/drawable/Drawable;

    aget-object v2, v2, v0

    invoke-virtual {p0}, Lmiui/widget/CircleProgressBar;->getDrawableState()[I

    move-result-object v3

    invoke-virtual {v2, v3}, Landroid/graphics/drawable/Drawable;->setState([I)Z

    .line 242
    :cond_0
    iget-object v2, p0, Lmiui/widget/CircleProgressBar;->QR:[Landroid/graphics/drawable/Drawable;

    if-eqz v2, :cond_1

    iget-object v2, p0, Lmiui/widget/CircleProgressBar;->QR:[Landroid/graphics/drawable/Drawable;

    aget-object v2, v2, v0

    invoke-virtual {p0}, Lmiui/widget/CircleProgressBar;->getDrawableState()[I

    move-result-object v3

    invoke-virtual {v2, v3}, Landroid/graphics/drawable/Drawable;->setState([I)Z

    .line 243
    :cond_1
    iget-object v2, p0, Lmiui/widget/CircleProgressBar;->QS:[Landroid/graphics/drawable/Drawable;

    if-eqz v2, :cond_2

    iget-object v2, p0, Lmiui/widget/CircleProgressBar;->QS:[Landroid/graphics/drawable/Drawable;

    aget-object v2, v2, v0

    invoke-virtual {p0}, Lmiui/widget/CircleProgressBar;->getDrawableState()[I

    move-result-object v3

    invoke-virtual {v2, v3}, Landroid/graphics/drawable/Drawable;->setState([I)Z

    .line 240
    :cond_2
    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    .line 245
    :cond_3
    invoke-virtual {p0}, Lmiui/widget/CircleProgressBar;->invalidate()V

    .line 246
    return-void
.end method

.method public getPrevAlpha()I
    .locals 1

    .prologue
    .line 394
    iget v0, p0, Lmiui/widget/CircleProgressBar;->QY:I

    return v0
.end method

.method public getProgressLevelCount()I
    .locals 1

    .prologue
    .line 91
    iget-object v0, p0, Lmiui/widget/CircleProgressBar;->QP:[I

    if-nez v0, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    iget-object v0, p0, Lmiui/widget/CircleProgressBar;->QP:[I

    array-length v0, v0

    add-int/lit8 v0, v0, 0x1

    goto :goto_0
.end method

.method protected declared-synchronized onDraw(Landroid/graphics/Canvas;)V
    .locals 7

    .prologue
    .line 313
    monitor-enter p0

    :try_start_0
    iget v0, p0, Lmiui/widget/CircleProgressBar;->QU:I

    invoke-direct {p0, v0}, Lmiui/widget/CircleProgressBar;->ar(I)Landroid/graphics/drawable/Drawable;

    move-result-object v2

    iget v0, p0, Lmiui/widget/CircleProgressBar;->QU:I

    invoke-direct {p0, v0}, Lmiui/widget/CircleProgressBar;->at(I)Landroid/graphics/drawable/Drawable;

    move-result-object v3

    iget v0, p0, Lmiui/widget/CircleProgressBar;->QU:I

    invoke-direct {p0, v0}, Lmiui/widget/CircleProgressBar;->as(I)Landroid/graphics/drawable/Drawable;

    move-result-object v4

    invoke-direct {p0}, Lmiui/widget/CircleProgressBar;->eP()F

    move-result v5

    iget v0, p0, Lmiui/widget/CircleProgressBar;->QY:I

    rsub-int v6, v0, 0xff

    move-object v0, p0

    move-object v1, p1

    invoke-direct/range {v0 .. v6}, Lmiui/widget/CircleProgressBar;->a(Landroid/graphics/Canvas;Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;FI)V

    .line 318
    iget v0, p0, Lmiui/widget/CircleProgressBar;->QY:I

    const/16 v1, 0xa

    if-lt v0, v1, :cond_0

    .line 319
    iget v0, p0, Lmiui/widget/CircleProgressBar;->QX:I

    invoke-direct {p0, v0}, Lmiui/widget/CircleProgressBar;->ar(I)Landroid/graphics/drawable/Drawable;

    move-result-object v2

    iget v0, p0, Lmiui/widget/CircleProgressBar;->QX:I

    invoke-direct {p0, v0}, Lmiui/widget/CircleProgressBar;->at(I)Landroid/graphics/drawable/Drawable;

    move-result-object v3

    iget v0, p0, Lmiui/widget/CircleProgressBar;->QX:I

    invoke-direct {p0, v0}, Lmiui/widget/CircleProgressBar;->as(I)Landroid/graphics/drawable/Drawable;

    move-result-object v4

    invoke-direct {p0}, Lmiui/widget/CircleProgressBar;->eP()F

    move-result v5

    iget v6, p0, Lmiui/widget/CircleProgressBar;->QY:I

    move-object v0, p0

    move-object v1, p1

    invoke-direct/range {v0 .. v6}, Lmiui/widget/CircleProgressBar;->a(Landroid/graphics/Canvas;Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;FI)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 322
    :cond_0
    monitor-exit p0

    return-void

    .line 313
    :catchall_0
    move-exception v0

    monitor-exit p0

    throw v0
.end method

.method protected declared-synchronized onMeasure(II)V
    .locals 2

    .prologue
    .line 307
    monitor-enter p0

    :try_start_0
    invoke-direct {p0}, Lmiui/widget/CircleProgressBar;->getIntrinsicWidth()I

    move-result v0

    invoke-direct {p0}, Lmiui/widget/CircleProgressBar;->getIntrinsicHeight()I

    move-result v1

    invoke-virtual {p0, v0, v1}, Lmiui/widget/CircleProgressBar;->setMeasuredDimension(II)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 308
    monitor-exit p0

    return-void

    .line 307
    :catchall_0
    move-exception v0

    monitor-exit p0

    throw v0
.end method

.method public setDrawablesForLevels([I[I[I)V
    .locals 3

    .prologue
    .line 152
    invoke-direct {p0, p1}, Lmiui/widget/CircleProgressBar;->d([I)[Landroid/graphics/drawable/Drawable;

    move-result-object v0

    invoke-direct {p0, p2}, Lmiui/widget/CircleProgressBar;->d([I)[Landroid/graphics/drawable/Drawable;

    move-result-object v1

    invoke-direct {p0, p3}, Lmiui/widget/CircleProgressBar;->d([I)[Landroid/graphics/drawable/Drawable;

    move-result-object v2

    invoke-virtual {p0, v0, v1, v2}, Lmiui/widget/CircleProgressBar;->setDrawablesForLevels([Landroid/graphics/drawable/Drawable;[Landroid/graphics/drawable/Drawable;[Landroid/graphics/drawable/Drawable;)V

    .line 154
    return-void
.end method

.method public setDrawablesForLevels([Landroid/graphics/drawable/Drawable;[Landroid/graphics/drawable/Drawable;[Landroid/graphics/drawable/Drawable;)V
    .locals 6

    .prologue
    const/4 v2, 0x0

    .line 102
    iput-object p1, p0, Lmiui/widget/CircleProgressBar;->QQ:[Landroid/graphics/drawable/Drawable;

    .line 103
    iput-object p2, p0, Lmiui/widget/CircleProgressBar;->QR:[Landroid/graphics/drawable/Drawable;

    .line 104
    iput-object p3, p0, Lmiui/widget/CircleProgressBar;->QS:[Landroid/graphics/drawable/Drawable;

    .line 107
    if-eqz p1, :cond_0

    .line 108
    array-length v1, p1

    move v0, v2

    :goto_0
    if-ge v0, v1, :cond_0

    aget-object v3, p1, v0

    .line 109
    invoke-virtual {v3}, Landroid/graphics/drawable/Drawable;->mutate()Landroid/graphics/drawable/Drawable;

    .line 108
    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    .line 112
    :cond_0
    if-eqz p2, :cond_1

    .line 113
    array-length v1, p2

    move v0, v2

    :goto_1
    if-ge v0, v1, :cond_1

    aget-object v3, p2, v0

    .line 114
    invoke-virtual {v3}, Landroid/graphics/drawable/Drawable;->mutate()Landroid/graphics/drawable/Drawable;

    .line 113
    add-int/lit8 v0, v0, 0x1

    goto :goto_1

    .line 117
    :cond_1
    if-eqz p3, :cond_2

    .line 118
    array-length v1, p3

    move v0, v2

    :goto_2
    if-ge v0, v1, :cond_2

    aget-object v3, p3, v0

    .line 119
    invoke-virtual {v3}, Landroid/graphics/drawable/Drawable;->mutate()Landroid/graphics/drawable/Drawable;

    .line 118
    add-int/lit8 v0, v0, 0x1

    goto :goto_2

    .line 124
    :cond_2
    array-length v3, p2

    move v1, v2

    :goto_3
    if-ge v1, v3, :cond_5

    aget-object v0, p2, v1

    .line 125
    instance-of v4, v0, Landroid/graphics/drawable/BitmapDrawable;

    if-eqz v4, :cond_3

    .line 126
    check-cast v0, Landroid/graphics/drawable/BitmapDrawable;

    invoke-virtual {v0}, Landroid/graphics/drawable/BitmapDrawable;->getPaint()Landroid/graphics/Paint;

    move-result-object v0

    new-instance v4, Landroid/graphics/PorterDuffXfermode;

    sget-object v5, Landroid/graphics/PorterDuff$Mode;->SRC_IN:Landroid/graphics/PorterDuff$Mode;

    invoke-direct {v4, v5}, Landroid/graphics/PorterDuffXfermode;-><init>(Landroid/graphics/PorterDuff$Mode;)V

    invoke-virtual {v0, v4}, Landroid/graphics/Paint;->setXfermode(Landroid/graphics/Xfermode;)Landroid/graphics/Xfermode;

    .line 124
    :goto_4
    add-int/lit8 v0, v1, 0x1

    move v1, v0

    goto :goto_3

    .line 128
    :cond_3
    instance-of v4, v0, Landroid/graphics/drawable/NinePatchDrawable;

    if-eqz v4, :cond_4

    .line 129
    check-cast v0, Landroid/graphics/drawable/NinePatchDrawable;

    invoke-virtual {v0}, Landroid/graphics/drawable/NinePatchDrawable;->getPaint()Landroid/graphics/Paint;

    move-result-object v0

    new-instance v4, Landroid/graphics/PorterDuffXfermode;

    sget-object v5, Landroid/graphics/PorterDuff$Mode;->SRC_IN:Landroid/graphics/PorterDuff$Mode;

    invoke-direct {v4, v5}, Landroid/graphics/PorterDuffXfermode;-><init>(Landroid/graphics/PorterDuff$Mode;)V

    invoke-virtual {v0, v4}, Landroid/graphics/Paint;->setXfermode(Landroid/graphics/Xfermode;)Landroid/graphics/Xfermode;

    goto :goto_4

    .line 132
    :cond_4
    new-instance v0, Ljava/lang/IllegalArgumentException;

    const-string v1, "\'middles\' must a bitmap or nine patch drawable."

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 138
    :cond_5
    new-instance v0, Landroid/graphics/RectF;

    aget-object v1, p2, v2

    invoke-virtual {v1}, Landroid/graphics/drawable/Drawable;->getBounds()Landroid/graphics/Rect;

    move-result-object v1

    iget v1, v1, Landroid/graphics/Rect;->left:I

    add-int/lit8 v1, v1, -0x5

    int-to-float v1, v1

    aget-object v3, p2, v2

    invoke-virtual {v3}, Landroid/graphics/drawable/Drawable;->getBounds()Landroid/graphics/Rect;

    move-result-object v3

    iget v3, v3, Landroid/graphics/Rect;->top:I

    add-int/lit8 v3, v3, -0x5

    int-to-float v3, v3

    aget-object v4, p2, v2

    invoke-virtual {v4}, Landroid/graphics/drawable/Drawable;->getBounds()Landroid/graphics/Rect;

    move-result-object v4

    iget v4, v4, Landroid/graphics/Rect;->right:I

    add-int/lit8 v4, v4, 0x5

    int-to-float v4, v4

    aget-object v2, p2, v2

    invoke-virtual {v2}, Landroid/graphics/drawable/Drawable;->getBounds()Landroid/graphics/Rect;

    move-result-object v2

    iget v2, v2, Landroid/graphics/Rect;->bottom:I

    add-int/lit8 v2, v2, 0x5

    int-to-float v2, v2

    invoke-direct {v0, v1, v3, v4, v2}, Landroid/graphics/RectF;-><init>(FFFF)V

    iput-object v0, p0, Lmiui/widget/CircleProgressBar;->QN:Landroid/graphics/RectF;

    .line 140
    return-void
.end method

.method public setOnProgressChangedListener(Lmiui/widget/CircleProgressBar$OnProgressChangedListener;)V
    .locals 0

    .prologue
    .line 203
    iput-object p1, p0, Lmiui/widget/CircleProgressBar;->QT:Lmiui/widget/CircleProgressBar$OnProgressChangedListener;

    .line 204
    return-void
.end method

.method public setPrevAlpha(I)V
    .locals 0

    .prologue
    .line 385
    iput p1, p0, Lmiui/widget/CircleProgressBar;->QY:I

    .line 386
    invoke-virtual {p0}, Lmiui/widget/CircleProgressBar;->invalidate()V

    .line 387
    return-void
.end method

.method public declared-synchronized setProgress(I)V
    .locals 4

    .prologue
    const/4 v2, -0x1

    const/4 v0, 0x0

    .line 250
    monitor-enter p0

    :try_start_0
    invoke-super {p0, p1}, Landroid/widget/ProgressBar;->setProgress(I)V

    .line 253
    iget-object v1, p0, Lmiui/widget/CircleProgressBar;->QP:[I

    if-nez v1, :cond_3

    .line 267
    :cond_0
    :goto_0
    iget v1, p0, Lmiui/widget/CircleProgressBar;->QU:I

    if-eq v0, v1, :cond_1

    .line 269
    iget v1, p0, Lmiui/widget/CircleProgressBar;->QU:I

    iput v1, p0, Lmiui/widget/CircleProgressBar;->QX:I

    .line 270
    iput v0, p0, Lmiui/widget/CircleProgressBar;->QU:I

    .line 271
    const/16 v0, 0xff

    invoke-virtual {p0, v0}, Lmiui/widget/CircleProgressBar;->setPrevAlpha(I)V

    .line 272
    const-string v0, "prevAlpha"

    const/4 v1, 0x1

    new-array v1, v1, [I

    const/4 v2, 0x0

    const/4 v3, 0x0

    aput v3, v1, v2

    invoke-static {p0, v0, v1}, Landroid/animation/ObjectAnimator;->ofInt(Ljava/lang/Object;Ljava/lang/String;[I)Landroid/animation/ObjectAnimator;

    move-result-object v0

    .line 273
    const-wide/16 v1, 0x12c

    invoke-virtual {v0, v1, v2}, Landroid/animation/Animator;->setDuration(J)Landroid/animation/Animator;

    .line 274
    new-instance v1, Landroid/view/animation/LinearInterpolator;

    invoke-direct {v1}, Landroid/view/animation/LinearInterpolator;-><init>()V

    invoke-virtual {v0, v1}, Landroid/animation/Animator;->setInterpolator(Landroid/animation/TimeInterpolator;)V

    .line 275
    invoke-virtual {v0}, Landroid/animation/Animator;->start()V

    .line 278
    :cond_1
    iget-object v0, p0, Lmiui/widget/CircleProgressBar;->QT:Lmiui/widget/CircleProgressBar$OnProgressChangedListener;

    if-eqz v0, :cond_2

    .line 279
    iget-object v0, p0, Lmiui/widget/CircleProgressBar;->QT:Lmiui/widget/CircleProgressBar$OnProgressChangedListener;

    invoke-interface {v0}, Lmiui/widget/CircleProgressBar$OnProgressChangedListener;->onProgressChanged()V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 281
    :cond_2
    monitor-exit p0

    return-void

    .line 256
    :cond_3
    :try_start_1
    iget-object v1, p0, Lmiui/widget/CircleProgressBar;->QP:[I

    array-length v1, v1

    .line 257
    :goto_1
    if-ge v0, v1, :cond_5

    .line 258
    iget-object v3, p0, Lmiui/widget/CircleProgressBar;->QP:[I

    aget v3, v3, v0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    if-ge p1, v3, :cond_4

    .line 263
    :goto_2
    if-ne v0, v2, :cond_0

    move v0, v1

    .line 264
    goto :goto_0

    .line 257
    :cond_4
    add-int/lit8 v0, v0, 0x1

    goto :goto_1

    .line 250
    :catchall_0
    move-exception v0

    monitor-exit p0

    throw v0

    :cond_5
    move v0, v2

    goto :goto_2
.end method

.method public setProgressByAnimator(I)V
    .locals 1

    .prologue
    .line 195
    const/4 v0, 0x0

    invoke-virtual {p0, p1, v0}, Lmiui/widget/CircleProgressBar;->setProgressByAnimator(ILandroid/animation/Animator$AnimatorListener;)V

    .line 196
    return-void
.end method

.method public setProgressByAnimator(ILandroid/animation/Animator$AnimatorListener;)V
    .locals 4

    .prologue
    .line 212
    invoke-virtual {p0}, Lmiui/widget/CircleProgressBar;->stopProgressAnimator()V

    .line 213
    invoke-virtual {p0}, Lmiui/widget/CircleProgressBar;->getProgress()I

    move-result v0

    sub-int v0, p1, v0

    int-to-float v0, v0

    invoke-virtual {p0}, Lmiui/widget/CircleProgressBar;->getMax()I

    move-result v1

    int-to-float v1, v1

    div-float/2addr v0, v1

    const/high16 v1, 0x43b40000

    mul-float/2addr v0, v1

    float-to-int v0, v0

    invoke-static {v0}, Ljava/lang/Math;->abs(I)I

    move-result v0

    .line 214
    const-string v1, "progress"

    const/4 v2, 0x1

    new-array v2, v2, [I

    const/4 v3, 0x0

    aput p1, v2, v3

    invoke-static {p0, v1, v2}, Landroid/animation/ObjectAnimator;->ofInt(Ljava/lang/Object;Ljava/lang/String;[I)Landroid/animation/ObjectAnimator;

    move-result-object v1

    iput-object v1, p0, Lmiui/widget/CircleProgressBar;->QO:Landroid/animation/Animator;

    .line 215
    iget-object v1, p0, Lmiui/widget/CircleProgressBar;->QO:Landroid/animation/Animator;

    invoke-direct {p0, v0}, Lmiui/widget/CircleProgressBar;->au(I)I

    move-result v0

    int-to-long v2, v0

    invoke-virtual {v1, v2, v3}, Landroid/animation/Animator;->setDuration(J)Landroid/animation/Animator;

    .line 216
    iget-object v0, p0, Lmiui/widget/CircleProgressBar;->QO:Landroid/animation/Animator;

    invoke-virtual {p0}, Lmiui/widget/CircleProgressBar;->getInterpolator()Landroid/view/animation/Interpolator;

    move-result-object v1

    invoke-virtual {v0, v1}, Landroid/animation/Animator;->setInterpolator(Landroid/animation/TimeInterpolator;)V

    .line 217
    if-eqz p2, :cond_0

    .line 218
    iget-object v0, p0, Lmiui/widget/CircleProgressBar;->QO:Landroid/animation/Animator;

    invoke-virtual {v0, p2}, Landroid/animation/Animator;->addListener(Landroid/animation/Animator$AnimatorListener;)V

    .line 220
    :cond_0
    iget-object v0, p0, Lmiui/widget/CircleProgressBar;->QO:Landroid/animation/Animator;

    invoke-virtual {v0}, Landroid/animation/Animator;->start()V

    .line 221
    return-void
.end method

.method public setProgressLevels([I)V
    .locals 0

    .prologue
    .line 83
    iput-object p1, p0, Lmiui/widget/CircleProgressBar;->QP:[I

    .line 84
    return-void
.end method

.method public setRotateVelocity(I)V
    .locals 0

    .prologue
    .line 187
    iput p1, p0, Lmiui/widget/CircleProgressBar;->QZ:I

    .line 188
    return-void
.end method

.method public setThumb(I)V
    .locals 1

    .prologue
    .line 402
    invoke-virtual {p0}, Lmiui/widget/CircleProgressBar;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    invoke-virtual {v0, p1}, Landroid/content/res/Resources;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v0

    invoke-virtual {p0, v0}, Lmiui/widget/CircleProgressBar;->setThumb(Landroid/graphics/drawable/Drawable;)V

    .line 403
    return-void
.end method

.method public setThumb(Landroid/graphics/drawable/Drawable;)V
    .locals 0

    .prologue
    .line 410
    iput-object p1, p0, Lmiui/widget/CircleProgressBar;->Ra:Landroid/graphics/drawable/Drawable;

    .line 411
    return-void
.end method

.method public stopProgressAnimator()V
    .locals 1

    .prologue
    .line 227
    iget-object v0, p0, Lmiui/widget/CircleProgressBar;->QO:Landroid/animation/Animator;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lmiui/widget/CircleProgressBar;->QO:Landroid/animation/Animator;

    invoke-virtual {v0}, Landroid/animation/Animator;->isRunning()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 228
    iget-object v0, p0, Lmiui/widget/CircleProgressBar;->QO:Landroid/animation/Animator;

    invoke-virtual {v0}, Landroid/animation/Animator;->cancel()V

    .line 230
    :cond_0
    return-void
.end method
