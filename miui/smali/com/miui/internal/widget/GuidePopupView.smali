.class public Lcom/miui/internal/widget/GuidePopupView;
.super Landroid/widget/FrameLayout;
.source "SourceFile"

# interfaces
.implements Landroid/view/View$OnTouchListener;


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/internal/widget/GuidePopupView$a;
    }
.end annotation


# static fields
.field public static final ARROW_BOTTOM_LEFT_MODE:I = 0x7

.field public static final ARROW_BOTTOM_MODE:I = 0x0

.field public static final ARROW_BOTTOM_RIGHT_MODE:I = 0x5

.field public static final ARROW_LEFT_MODE:I = 0x3

.field public static final ARROW_NONE_MODE:I = -0x1

.field public static final ARROW_RIGHT_MODE:I = 0x2

.field public static final ARROW_TOP_LEFT_MODE:I = 0x4

.field public static final ARROW_TOP_MODE:I = 0x1

.field public static final ARROW_TOP_RIGHT_MODE:I = 0x6


# instance fields
.field private final aP:Landroid/graphics/Paint;

.field private jD:Landroid/view/View;

.field private jE:Landroid/widget/LinearLayout;

.field private jF:Landroid/widget/LinearLayout;

.field private jG:I

.field private jH:I

.field private jI:Z

.field private jJ:I

.field private jK:Lmiui/widget/GuidePopupWindow;

.field private jL:Landroid/view/View$OnTouchListener;

.field private jM:Landroid/animation/ObjectAnimator;

.field private jN:Z

.field private jO:I

.field private jP:I

.field private jQ:I

.field private jR:I

.field private jS:I

.field private jT:F

.field private jU:F

.field private jV:F

.field private jW:I

.field private jX:I

.field private jY:Landroid/content/res/ColorStateList;

.field private jZ:Z

.field private ka:Landroid/animation/Animator$AnimatorListener;

.field private kb:Landroid/animation/Animator$AnimatorListener;

.field private kc:I

.field private mContext:Landroid/content/Context;


# direct methods
.method public constructor <init>(Landroid/content/Context;)V
    .locals 1

    .prologue
    .line 162
    const/4 v0, 0x0

    invoke-direct {p0, p1, v0}, Lcom/miui/internal/widget/GuidePopupView;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    .line 163
    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 1

    .prologue
    .line 166
    sget v0, Lcom/miui/internal/R$attr;->guidePopupViewStyle:I

    invoke-direct {p0, p1, p2, v0}, Lcom/miui/internal/widget/GuidePopupView;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    .line 167
    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V
    .locals 6

    .prologue
    const/4 v5, 0x1

    const/4 v4, -0x1

    const/4 v3, 0x0

    const/4 v2, 0x0

    .line 170
    invoke-direct {p0, p1, p2, p3}, Landroid/widget/FrameLayout;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    .line 73
    iput-boolean v5, p0, Lcom/miui/internal/widget/GuidePopupView;->jI:Z

    .line 105
    const/4 v0, 0x0

    iput-object v0, p0, Lcom/miui/internal/widget/GuidePopupView;->jY:Landroid/content/res/ColorStateList;

    .line 107
    new-instance v0, Landroid/graphics/Paint;

    invoke-direct {v0}, Landroid/graphics/Paint;-><init>()V

    iput-object v0, p0, Lcom/miui/internal/widget/GuidePopupView;->aP:Landroid/graphics/Paint;

    .line 111
    new-instance v0, Lcom/miui/internal/widget/m;

    invoke-direct {v0, p0}, Lcom/miui/internal/widget/m;-><init>(Lcom/miui/internal/widget/GuidePopupView;)V

    iput-object v0, p0, Lcom/miui/internal/widget/GuidePopupView;->ka:Landroid/animation/Animator$AnimatorListener;

    .line 133
    new-instance v0, Lcom/miui/internal/widget/l;

    invoke-direct {v0, p0}, Lcom/miui/internal/widget/l;-><init>(Lcom/miui/internal/widget/GuidePopupView;)V

    iput-object v0, p0, Lcom/miui/internal/widget/GuidePopupView;->kb:Landroid/animation/Animator$AnimatorListener;

    .line 159
    iput v4, p0, Lcom/miui/internal/widget/GuidePopupView;->kc:I

    .line 172
    iput-object p1, p0, Lcom/miui/internal/widget/GuidePopupView;->mContext:Landroid/content/Context;

    .line 174
    sget-object v0, Lcom/miui/internal/R$styleable;->GuidePopupView:[I

    invoke-virtual {p1, p2, v0, p3, v3}, Landroid/content/Context;->obtainStyledAttributes(Landroid/util/AttributeSet;[III)Landroid/content/res/TypedArray;

    move-result-object v0

    .line 178
    const/4 v1, 0x4

    invoke-virtual {v0, v1, v2}, Landroid/content/res/TypedArray;->getDimension(IF)F

    move-result v1

    iput v1, p0, Lcom/miui/internal/widget/GuidePopupView;->jT:F

    .line 179
    const/4 v1, 0x5

    invoke-virtual {v0, v1, v2}, Landroid/content/res/TypedArray;->getDimension(IF)F

    move-result v1

    iput v1, p0, Lcom/miui/internal/widget/GuidePopupView;->jU:F

    .line 180
    const/4 v1, 0x6

    invoke-virtual {v0, v1, v2}, Landroid/content/res/TypedArray;->getDimension(IF)F

    move-result v1

    iput v1, p0, Lcom/miui/internal/widget/GuidePopupView;->jV:F

    .line 181
    invoke-virtual {v0, v3, v3}, Landroid/content/res/TypedArray;->getColor(II)I

    move-result v1

    iput v1, p0, Lcom/miui/internal/widget/GuidePopupView;->jW:I

    .line 183
    const/4 v1, 0x3

    invoke-virtual {v0, v1, v4}, Landroid/content/res/TypedArray;->getColor(II)I

    move-result v1

    .line 184
    iget-object v2, p0, Lcom/miui/internal/widget/GuidePopupView;->aP:Landroid/graphics/Paint;

    invoke-virtual {v2, v1}, Landroid/graphics/Paint;->setColor(I)V

    .line 186
    const/16 v1, 0xf

    invoke-virtual {v0, v5, v1}, Landroid/content/res/TypedArray;->getDimensionPixelSize(II)I

    move-result v1

    iput v1, p0, Lcom/miui/internal/widget/GuidePopupView;->jX:I

    .line 187
    const/4 v1, 0x2

    invoke-virtual {v0, v1}, Landroid/content/res/TypedArray;->getColorStateList(I)Landroid/content/res/ColorStateList;

    move-result-object v1

    iput-object v1, p0, Lcom/miui/internal/widget/GuidePopupView;->jY:Landroid/content/res/ColorStateList;

    .line 189
    invoke-virtual {v0}, Landroid/content/res/TypedArray;->recycle()V

    .line 191
    iget v0, p0, Lcom/miui/internal/widget/GuidePopupView;->jU:F

    iget v1, p0, Lcom/miui/internal/widget/GuidePopupView;->jV:F

    const/high16 v2, 0x40200000

    mul-float/2addr v1, v2

    add-float/2addr v0, v1

    float-to-int v0, v0

    iput v0, p0, Lcom/miui/internal/widget/GuidePopupView;->jO:I

    .line 192
    return-void
.end method

.method static synthetic a(Lcom/miui/internal/widget/GuidePopupView;Landroid/animation/ObjectAnimator;)Landroid/animation/ObjectAnimator;
    .locals 0

    .prologue
    .line 41
    iput-object p1, p0, Lcom/miui/internal/widget/GuidePopupView;->jM:Landroid/animation/ObjectAnimator;

    return-object p1
.end method

.method static synthetic a(Lcom/miui/internal/widget/GuidePopupView;)Lmiui/widget/GuidePopupWindow;
    .locals 1

    .prologue
    .line 41
    iget-object v0, p0, Lcom/miui/internal/widget/GuidePopupView;->jK:Lmiui/widget/GuidePopupWindow;

    return-object v0
.end method

.method private a(ILandroid/widget/LinearLayout;II)V
    .locals 7

    .prologue
    const/4 v0, 0x0

    .line 386
    iget v1, p0, Lcom/miui/internal/widget/GuidePopupView;->jJ:I

    int-to-float v1, v1

    iget v2, p0, Lcom/miui/internal/widget/GuidePopupView;->jU:F

    add-float/2addr v1, v2

    iget v2, p0, Lcom/miui/internal/widget/GuidePopupView;->jV:F

    add-float/2addr v2, v1

    .line 387
    iget v1, p0, Lcom/miui/internal/widget/GuidePopupView;->jP:I

    iget v3, p0, Lcom/miui/internal/widget/GuidePopupView;->jR:I

    div-int/lit8 v3, v3, 0x2

    add-int/2addr v1, v3

    .line 388
    iget v3, p0, Lcom/miui/internal/widget/GuidePopupView;->jQ:I

    iget v4, p0, Lcom/miui/internal/widget/GuidePopupView;->jS:I

    div-int/lit8 v4, v4, 0x2

    add-int/2addr v3, v4

    .line 391
    packed-switch p1, :pswitch_data_0

    move v1, v0

    .line 417
    :goto_0
    float-to-double v3, v2

    const-wide v5, 0x3fe921fb54442d18L

    invoke-static {v5, v6}, Ljava/lang/Math;->sin(D)D

    move-result-wide v5

    mul-double/2addr v3, v5

    double-to-int v3, v3

    .line 418
    int-to-float v4, v3

    sub-float/2addr v2, v4

    float-to-int v2, v2

    .line 420
    packed-switch p1, :pswitch_data_1

    .line 438
    :goto_1
    add-int/2addr v1, p3

    .line 439
    add-int/2addr v0, p4

    .line 441
    invoke-virtual {p2}, Landroid/widget/LinearLayout;->getMeasuredWidth()I

    move-result v2

    add-int/2addr v2, v1

    .line 442
    invoke-virtual {p2}, Landroid/widget/LinearLayout;->getMeasuredHeight()I

    move-result v3

    add-int/2addr v3, v0

    .line 443
    invoke-virtual {p2, v1, v0, v2, v3}, Landroid/widget/LinearLayout;->layout(IIII)V

    .line 444
    return-void

    .line 395
    :pswitch_0
    invoke-virtual {p2}, Landroid/widget/LinearLayout;->getMeasuredWidth()I

    move-result v0

    div-int/lit8 v0, v0, 0x2

    sub-int/2addr v1, v0

    .line 396
    int-to-float v0, v3

    add-float/2addr v0, v2

    invoke-virtual {p2}, Landroid/widget/LinearLayout;->getMeasuredHeight()I

    move-result v3

    div-int/lit8 v3, v3, 0x2

    int-to-float v3, v3

    sub-float/2addr v0, v3

    float-to-int v0, v0

    .line 398
    goto :goto_0

    .line 402
    :pswitch_1
    invoke-virtual {p2}, Landroid/widget/LinearLayout;->getMeasuredWidth()I

    move-result v0

    div-int/lit8 v0, v0, 0x2

    sub-int/2addr v1, v0

    .line 403
    int-to-float v0, v3

    sub-float/2addr v0, v2

    invoke-virtual {p2}, Landroid/widget/LinearLayout;->getMeasuredHeight()I

    move-result v3

    div-int/lit8 v3, v3, 0x2

    int-to-float v3, v3

    sub-float/2addr v0, v3

    float-to-int v0, v0

    .line 404
    goto :goto_0

    .line 406
    :pswitch_2
    int-to-float v0, v1

    add-float/2addr v0, v2

    invoke-virtual {p2}, Landroid/widget/LinearLayout;->getMeasuredWidth()I

    move-result v1

    div-int/lit8 v1, v1, 0x2

    int-to-float v1, v1

    sub-float/2addr v0, v1

    float-to-int v1, v0

    .line 408
    invoke-virtual {p2}, Landroid/widget/LinearLayout;->getMeasuredHeight()I

    move-result v0

    div-int/lit8 v0, v0, 0x2

    sub-int v0, v3, v0

    .line 409
    goto :goto_0

    .line 411
    :pswitch_3
    int-to-float v0, v1

    sub-float/2addr v0, v2

    invoke-virtual {p2}, Landroid/widget/LinearLayout;->getMeasuredWidth()I

    move-result v1

    div-int/lit8 v1, v1, 0x2

    int-to-float v1, v1

    sub-float/2addr v0, v1

    float-to-int v1, v0

    .line 412
    invoke-virtual {p2}, Landroid/widget/LinearLayout;->getMeasuredHeight()I

    move-result v0

    div-int/lit8 v0, v0, 0x2

    sub-int v0, v3, v0

    goto :goto_0

    .line 422
    :pswitch_4
    add-int/2addr v1, v3

    .line 423
    sub-int/2addr v0, v2

    .line 424
    goto :goto_1

    .line 426
    :pswitch_5
    sub-int/2addr v1, v3

    .line 427
    sub-int/2addr v0, v2

    .line 428
    goto :goto_1

    .line 430
    :pswitch_6
    add-int/2addr v1, v3

    .line 431
    add-int/2addr v0, v2

    .line 432
    goto :goto_1

    .line 434
    :pswitch_7
    sub-int/2addr v1, v3

    .line 435
    add-int/2addr v0, v2

    goto :goto_1

    .line 391
    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_1
        :pswitch_0
        :pswitch_3
        :pswitch_2
        :pswitch_0
        :pswitch_1
        :pswitch_0
        :pswitch_1
    .end packed-switch

    .line 420
    :pswitch_data_1
    .packed-switch 0x4
        :pswitch_4
        :pswitch_7
        :pswitch_5
        :pswitch_6
    .end packed-switch
.end method

.method private a(Landroid/graphics/Canvas;III)V
    .locals 9

    .prologue
    const/high16 v8, 0x40800000

    const/high16 v3, 0x40000000

    const/4 v1, 0x0

    .line 325
    iget-object v0, p0, Lcom/miui/internal/widget/GuidePopupView;->aP:Landroid/graphics/Paint;

    const/4 v2, 0x1

    invoke-virtual {v0, v2}, Landroid/graphics/Paint;->setAntiAlias(Z)V

    .line 326
    iget-object v0, p0, Lcom/miui/internal/widget/GuidePopupView;->aP:Landroid/graphics/Paint;

    sget-object v2, Landroid/graphics/Paint$Style;->FILL:Landroid/graphics/Paint$Style;

    invoke-virtual {v0, v2}, Landroid/graphics/Paint;->setStyle(Landroid/graphics/Paint$Style;)V

    .line 327
    iget v0, p0, Lcom/miui/internal/widget/GuidePopupView;->jP:I

    iget v2, p0, Lcom/miui/internal/widget/GuidePopupView;->jR:I

    div-int/lit8 v2, v2, 0x2

    add-int/2addr v0, v2

    add-int/2addr v0, p3

    int-to-float v6, v0

    .line 328
    iget v0, p0, Lcom/miui/internal/widget/GuidePopupView;->jQ:I

    iget v2, p0, Lcom/miui/internal/widget/GuidePopupView;->jS:I

    div-int/lit8 v2, v2, 0x2

    add-int/2addr v0, v2

    add-int/2addr v0, p4

    int-to-float v2, v0

    .line 331
    packed-switch p2, :pswitch_data_0

    move v0, v1

    .line 358
    :goto_0
    invoke-virtual {p1}, Landroid/graphics/Canvas;->save()I

    .line 360
    invoke-virtual {p1, v0, v6, v2}, Landroid/graphics/Canvas;->rotate(FFF)V

    .line 361
    iget v0, p0, Lcom/miui/internal/widget/GuidePopupView;->jJ:I

    int-to-float v0, v0

    invoke-virtual {p1, v1, v0}, Landroid/graphics/Canvas;->translate(FF)V

    .line 363
    invoke-virtual {p1}, Landroid/graphics/Canvas;->save()I

    move-result v7

    .line 364
    sub-float v1, v6, v3

    add-float/2addr v3, v6

    iget v0, p0, Lcom/miui/internal/widget/GuidePopupView;->jT:F

    add-float v4, v2, v0

    sget-object v5, Landroid/graphics/Region$Op;->DIFFERENCE:Landroid/graphics/Region$Op;

    move-object v0, p1

    invoke-virtual/range {v0 .. v5}, Landroid/graphics/Canvas;->clipRect(FFFFLandroid/graphics/Region$Op;)Z

    .line 365
    iget v0, p0, Lcom/miui/internal/widget/GuidePopupView;->jT:F

    iget-object v1, p0, Lcom/miui/internal/widget/GuidePopupView;->aP:Landroid/graphics/Paint;

    invoke-virtual {p1, v6, v2, v0, v1}, Landroid/graphics/Canvas;->drawCircle(FFFLandroid/graphics/Paint;)V

    .line 366
    invoke-virtual {p1, v7}, Landroid/graphics/Canvas;->restoreToCount(I)V

    .line 368
    iget-object v0, p0, Lcom/miui/internal/widget/GuidePopupView;->aP:Landroid/graphics/Paint;

    sget-object v1, Landroid/graphics/Paint$Style;->STROKE:Landroid/graphics/Paint$Style;

    invoke-virtual {v0, v1}, Landroid/graphics/Paint;->setStyle(Landroid/graphics/Paint$Style;)V

    .line 369
    iget-object v0, p0, Lcom/miui/internal/widget/GuidePopupView;->aP:Landroid/graphics/Paint;

    invoke-virtual {v0, v8}, Landroid/graphics/Paint;->setStrokeWidth(F)V

    .line 371
    iget v0, p0, Lcom/miui/internal/widget/GuidePopupView;->jU:F

    add-float v4, v2, v0

    iget-object v5, p0, Lcom/miui/internal/widget/GuidePopupView;->aP:Landroid/graphics/Paint;

    move-object v0, p1

    move v1, v6

    move v3, v6

    invoke-virtual/range {v0 .. v5}, Landroid/graphics/Canvas;->drawLine(FFFFLandroid/graphics/Paint;)V

    .line 374
    iget v0, p0, Lcom/miui/internal/widget/GuidePopupView;->jU:F

    add-float/2addr v0, v2

    .line 376
    iget v1, p0, Lcom/miui/internal/widget/GuidePopupView;->jV:F

    add-float/2addr v0, v1

    .line 377
    iget-object v1, p0, Lcom/miui/internal/widget/GuidePopupView;->aP:Landroid/graphics/Paint;

    sget-object v2, Landroid/graphics/Paint$Style;->STROKE:Landroid/graphics/Paint$Style;

    invoke-virtual {v1, v2}, Landroid/graphics/Paint;->setStyle(Landroid/graphics/Paint$Style;)V

    .line 378
    iget-object v1, p0, Lcom/miui/internal/widget/GuidePopupView;->aP:Landroid/graphics/Paint;

    invoke-virtual {v1, v8}, Landroid/graphics/Paint;->setStrokeWidth(F)V

    .line 379
    iget v1, p0, Lcom/miui/internal/widget/GuidePopupView;->jV:F

    iget-object v2, p0, Lcom/miui/internal/widget/GuidePopupView;->aP:Landroid/graphics/Paint;

    invoke-virtual {p1, v6, v0, v1, v2}, Landroid/graphics/Canvas;->drawCircle(FFFLandroid/graphics/Paint;)V

    .line 381
    invoke-virtual {p1}, Landroid/graphics/Canvas;->restore()V

    .line 382
    return-void

    :pswitch_0
    move v0, v1

    .line 334
    goto :goto_0

    .line 336
    :pswitch_1
    const/high16 v0, 0x43340000

    .line 337
    goto :goto_0

    .line 339
    :pswitch_2
    const/high16 v0, -0x3d4c0000

    .line 340
    goto :goto_0

    .line 342
    :pswitch_3
    const/high16 v0, 0x42b40000

    .line 343
    goto :goto_0

    .line 345
    :pswitch_4
    const/high16 v0, -0x3dcc0000

    .line 346
    goto :goto_0

    .line 348
    :pswitch_5
    const/high16 v0, 0x42340000

    .line 349
    goto :goto_0

    .line 351
    :pswitch_6
    const/high16 v0, -0x3cf90000

    .line 352
    goto :goto_0

    .line 354
    :pswitch_7
    const/high16 v0, 0x43070000

    goto :goto_0

    .line 331
    nop

    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_1
        :pswitch_0
        :pswitch_3
        :pswitch_2
        :pswitch_4
        :pswitch_7
        :pswitch_5
        :pswitch_6
    .end packed-switch
.end method

.method static synthetic a(Lcom/miui/internal/widget/GuidePopupView;Z)Z
    .locals 0

    .prologue
    .line 41
    iput-boolean p1, p0, Lcom/miui/internal/widget/GuidePopupView;->jN:Z

    return p1
.end method

.method private aw()V
    .locals 14

    .prologue
    const/4 v3, 0x7

    const/4 v6, 0x6

    const/4 v4, 0x5

    const/4 v2, 0x0

    const/4 v5, 0x4

    .line 207
    invoke-virtual {p0}, Lcom/miui/internal/widget/GuidePopupView;->getWidth()I

    move-result v7

    .line 208
    invoke-virtual {p0}, Lcom/miui/internal/widget/GuidePopupView;->getHeight()I

    move-result v8

    .line 210
    new-array v9, v5, [I

    .line 211
    iget v0, p0, Lcom/miui/internal/widget/GuidePopupView;->jQ:I

    aput v0, v9, v2

    .line 212
    const/4 v0, 0x1

    iget v1, p0, Lcom/miui/internal/widget/GuidePopupView;->jQ:I

    sub-int v1, v8, v1

    iget v10, p0, Lcom/miui/internal/widget/GuidePopupView;->jS:I

    sub-int/2addr v1, v10

    aput v1, v9, v0

    .line 213
    const/4 v0, 0x2

    iget v1, p0, Lcom/miui/internal/widget/GuidePopupView;->jP:I

    aput v1, v9, v0

    .line 214
    const/4 v0, 0x3

    iget v1, p0, Lcom/miui/internal/widget/GuidePopupView;->jP:I

    sub-int v1, v7, v1

    iget v10, p0, Lcom/miui/internal/widget/GuidePopupView;->jR:I

    sub-int/2addr v1, v10

    aput v1, v9, v0

    .line 220
    const/high16 v0, -0x80000000

    .line 221
    iget v1, p0, Lcom/miui/internal/widget/GuidePopupView;->jP:I

    iget v10, p0, Lcom/miui/internal/widget/GuidePopupView;->jR:I

    div-int/lit8 v10, v10, 0x2

    add-int/2addr v10, v1

    .line 222
    iget v1, p0, Lcom/miui/internal/widget/GuidePopupView;->jQ:I

    iget v11, p0, Lcom/miui/internal/widget/GuidePopupView;->jS:I

    div-int/lit8 v11, v11, 0x2

    add-int/2addr v11, v1

    move v1, v2

    .line 223
    :goto_0
    if-ge v2, v5, :cond_7

    .line 224
    aget v12, v9, v2

    iget v13, p0, Lcom/miui/internal/widget/GuidePopupView;->jO:I

    if-lt v12, v13, :cond_1

    move v0, v2

    .line 232
    :goto_1
    packed-switch v0, :pswitch_data_0

    .line 263
    :cond_0
    :goto_2
    invoke-virtual {p0, v0}, Lcom/miui/internal/widget/GuidePopupView;->setArrowMode(I)V

    .line 264
    return-void

    .line 227
    :cond_1
    aget v12, v9, v2

    if-le v12, v0, :cond_2

    .line 229
    aget v0, v9, v2

    move v1, v2

    .line 223
    :cond_2
    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    .line 234
    :pswitch_0
    int-to-float v1, v10

    iget v2, p0, Lcom/miui/internal/widget/GuidePopupView;->jV:F

    cmpg-float v1, v1, v2

    if-gez v1, :cond_3

    move v0, v3

    .line 235
    goto :goto_2

    .line 236
    :cond_3
    sub-int v1, v7, v10

    int-to-float v1, v1

    iget v2, p0, Lcom/miui/internal/widget/GuidePopupView;->jV:F

    cmpg-float v1, v1, v2

    if-gez v1, :cond_0

    move v0, v4

    .line 237
    goto :goto_2

    .line 241
    :pswitch_1
    int-to-float v1, v10

    iget v2, p0, Lcom/miui/internal/widget/GuidePopupView;->jV:F

    cmpg-float v1, v1, v2

    if-gez v1, :cond_4

    move v0, v5

    .line 242
    goto :goto_2

    .line 243
    :cond_4
    sub-int v1, v7, v10

    int-to-float v1, v1

    iget v2, p0, Lcom/miui/internal/widget/GuidePopupView;->jV:F

    cmpg-float v1, v1, v2

    if-gez v1, :cond_0

    move v0, v6

    .line 244
    goto :goto_2

    .line 248
    :pswitch_2
    int-to-float v1, v11

    iget v2, p0, Lcom/miui/internal/widget/GuidePopupView;->jV:F

    cmpg-float v1, v1, v2

    if-gez v1, :cond_5

    move v0, v6

    .line 249
    goto :goto_2

    .line 250
    :cond_5
    sub-int v1, v8, v11

    int-to-float v1, v1

    iget v2, p0, Lcom/miui/internal/widget/GuidePopupView;->jV:F

    cmpg-float v1, v1, v2

    if-gez v1, :cond_0

    move v0, v4

    .line 251
    goto :goto_2

    .line 255
    :pswitch_3
    int-to-float v1, v11

    iget v2, p0, Lcom/miui/internal/widget/GuidePopupView;->jV:F

    cmpg-float v1, v1, v2

    if-gez v1, :cond_6

    move v0, v5

    .line 256
    goto :goto_2

    .line 257
    :cond_6
    sub-int v1, v8, v11

    int-to-float v1, v1

    iget v2, p0, Lcom/miui/internal/widget/GuidePopupView;->jV:F

    cmpg-float v1, v1, v2

    if-gez v1, :cond_0

    move v0, v3

    .line 258
    goto :goto_2

    :cond_7
    move v0, v1

    goto :goto_1

    .line 232
    nop

    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_0
        :pswitch_1
        :pswitch_2
        :pswitch_3
    .end packed-switch
.end method

.method private ax()V
    .locals 4

    .prologue
    .line 270
    invoke-direct {p0}, Lcom/miui/internal/widget/GuidePopupView;->ay()V

    .line 272
    iget v0, p0, Lcom/miui/internal/widget/GuidePopupView;->kc:I

    iget-object v1, p0, Lcom/miui/internal/widget/GuidePopupView;->jE:Landroid/widget/LinearLayout;

    iget v2, p0, Lcom/miui/internal/widget/GuidePopupView;->jG:I

    iget v3, p0, Lcom/miui/internal/widget/GuidePopupView;->jH:I

    invoke-direct {p0, v0, v1, v2, v3}, Lcom/miui/internal/widget/GuidePopupView;->a(ILandroid/widget/LinearLayout;II)V

    .line 273
    iget-boolean v0, p0, Lcom/miui/internal/widget/GuidePopupView;->jZ:Z

    if-eqz v0, :cond_0

    .line 274
    invoke-direct {p0}, Lcom/miui/internal/widget/GuidePopupView;->az()I

    move-result v0

    .line 275
    iget-object v1, p0, Lcom/miui/internal/widget/GuidePopupView;->jF:Landroid/widget/LinearLayout;

    iget v2, p0, Lcom/miui/internal/widget/GuidePopupView;->jG:I

    neg-int v2, v2

    iget v3, p0, Lcom/miui/internal/widget/GuidePopupView;->jH:I

    neg-int v3, v3

    invoke-direct {p0, v0, v1, v2, v3}, Lcom/miui/internal/widget/GuidePopupView;->a(ILandroid/widget/LinearLayout;II)V

    .line 277
    :cond_0
    return-void
.end method

.method private ay()V
    .locals 8

    .prologue
    const-wide/high16 v6, 0x4000000000000000L

    .line 447
    iget-boolean v0, p0, Lcom/miui/internal/widget/GuidePopupView;->jI:Z

    if-nez v0, :cond_0

    .line 448
    const/4 v0, 0x0

    iput v0, p0, Lcom/miui/internal/widget/GuidePopupView;->jJ:I

    .line 467
    :goto_0
    return-void

    .line 451
    :cond_0
    iget v0, p0, Lcom/miui/internal/widget/GuidePopupView;->jR:I

    div-int/lit8 v0, v0, 0x2

    .line 452
    iget v1, p0, Lcom/miui/internal/widget/GuidePopupView;->jS:I

    div-int/lit8 v1, v1, 0x2

    .line 453
    int-to-double v2, v0

    invoke-static {v2, v3, v6, v7}, Ljava/lang/Math;->pow(DD)D

    move-result-wide v2

    int-to-double v4, v1

    invoke-static {v4, v5, v6, v7}, Ljava/lang/Math;->pow(DD)D

    move-result-wide v4

    add-double/2addr v2, v4

    invoke-static {v2, v3}, Ljava/lang/Math;->sqrt(D)D

    move-result-wide v2

    double-to-int v2, v2

    .line 454
    iget v3, p0, Lcom/miui/internal/widget/GuidePopupView;->kc:I

    packed-switch v3, :pswitch_data_0

    .line 464
    iput v2, p0, Lcom/miui/internal/widget/GuidePopupView;->jJ:I

    goto :goto_0

    .line 457
    :pswitch_0
    iput v1, p0, Lcom/miui/internal/widget/GuidePopupView;->jJ:I

    goto :goto_0

    .line 461
    :pswitch_1
    iput v0, p0, Lcom/miui/internal/widget/GuidePopupView;->jJ:I

    goto :goto_0

    .line 454
    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_0
        :pswitch_0
        :pswitch_1
        :pswitch_1
    .end packed-switch
.end method

.method private az()I
    .locals 2

    .prologue
    const/4 v0, -0x1

    .line 470
    iget v1, p0, Lcom/miui/internal/widget/GuidePopupView;->kc:I

    if-ne v1, v0, :cond_0

    .line 476
    :goto_0
    return v0

    .line 473
    :cond_0
    iget v0, p0, Lcom/miui/internal/widget/GuidePopupView;->kc:I

    rem-int/lit8 v0, v0, 0x2

    if-nez v0, :cond_1

    .line 474
    iget v0, p0, Lcom/miui/internal/widget/GuidePopupView;->kc:I

    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    .line 476
    :cond_1
    iget v0, p0, Lcom/miui/internal/widget/GuidePopupView;->kc:I

    add-int/lit8 v0, v0, -0x1

    goto :goto_0
.end method

.method static synthetic b(Lcom/miui/internal/widget/GuidePopupView;)Landroid/animation/ObjectAnimator;
    .locals 1

    .prologue
    .line 41
    iget-object v0, p0, Lcom/miui/internal/widget/GuidePopupView;->jM:Landroid/animation/ObjectAnimator;

    return-object v0
.end method

.method static synthetic c(Lcom/miui/internal/widget/GuidePopupView;)Landroid/animation/Animator$AnimatorListener;
    .locals 1

    .prologue
    .line 41
    iget-object v0, p0, Lcom/miui/internal/widget/GuidePopupView;->ka:Landroid/animation/Animator$AnimatorListener;

    return-object v0
.end method


# virtual methods
.method public addGuideTextView(Landroid/widget/LinearLayout;Ljava/lang/String;)V
    .locals 8

    .prologue
    const/4 v2, 0x0

    .line 563
    invoke-static {p2}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_1

    .line 579
    :cond_0
    return-void

    .line 566
    :cond_1
    const-string v0, "\n"

    invoke-virtual {p2, v0}, Ljava/lang/String;->split(Ljava/lang/String;)[Ljava/lang/String;

    move-result-object v3

    .line 567
    if-eqz v3, :cond_0

    array-length v0, v3

    if-eqz v0, :cond_0

    .line 570
    array-length v4, v3

    move v1, v2

    :goto_0
    if-ge v1, v4, :cond_0

    aget-object v5, v3, v1

    .line 571
    iget-object v0, p0, Lcom/miui/internal/widget/GuidePopupView;->mContext:Landroid/content/Context;

    sget v6, Lcom/miui/internal/R$layout;->guide_popup_text_view:I

    const/4 v7, 0x0

    invoke-static {v0, v6, v7}, Lcom/miui/internal/widget/GuidePopupView;->inflate(Landroid/content/Context;ILandroid/view/ViewGroup;)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/TextView;

    .line 572
    invoke-virtual {v0, v5}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 573
    iget v5, p0, Lcom/miui/internal/widget/GuidePopupView;->jX:I

    int-to-float v5, v5

    invoke-virtual {v0, v2, v5}, Landroid/widget/TextView;->setTextSize(IF)V

    .line 574
    iget-object v5, p0, Lcom/miui/internal/widget/GuidePopupView;->jY:Landroid/content/res/ColorStateList;

    if-eqz v5, :cond_2

    .line 575
    iget-object v5, p0, Lcom/miui/internal/widget/GuidePopupView;->jY:Landroid/content/res/ColorStateList;

    invoke-virtual {v0, v5}, Landroid/widget/TextView;->setTextColor(Landroid/content/res/ColorStateList;)V

    .line 577
    :cond_2
    invoke-virtual {p1, v0}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    .line 570
    add-int/lit8 v0, v1, 0x1

    move v1, v0

    goto :goto_0
.end method

.method public animateToDismiss()V
    .locals 4

    .prologue
    .line 501
    iget-boolean v0, p0, Lcom/miui/internal/widget/GuidePopupView;->jN:Z

    if-eqz v0, :cond_0

    .line 511
    :goto_0
    return-void

    .line 504
    :cond_0
    iget-object v0, p0, Lcom/miui/internal/widget/GuidePopupView;->jM:Landroid/animation/ObjectAnimator;

    if-eqz v0, :cond_1

    .line 505
    iget-object v0, p0, Lcom/miui/internal/widget/GuidePopupView;->jM:Landroid/animation/ObjectAnimator;

    invoke-virtual {v0}, Landroid/animation/ObjectAnimator;->cancel()V

    .line 507
    :cond_1
    sget-object v0, Landroid/view/View;->ALPHA:Landroid/util/Property;

    const/4 v1, 0x1

    new-array v1, v1, [F

    const/4 v2, 0x0

    const/4 v3, 0x0

    aput v3, v1, v2

    invoke-static {p0, v0, v1}, Landroid/animation/ObjectAnimator;->ofFloat(Ljava/lang/Object;Landroid/util/Property;[F)Landroid/animation/ObjectAnimator;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/internal/widget/GuidePopupView;->jM:Landroid/animation/ObjectAnimator;

    .line 508
    iget-object v0, p0, Lcom/miui/internal/widget/GuidePopupView;->jM:Landroid/animation/ObjectAnimator;

    const-wide/16 v1, 0xc8

    invoke-virtual {v0, v1, v2}, Landroid/animation/ObjectAnimator;->setDuration(J)Landroid/animation/ObjectAnimator;

    .line 509
    iget-object v0, p0, Lcom/miui/internal/widget/GuidePopupView;->jM:Landroid/animation/ObjectAnimator;

    iget-object v1, p0, Lcom/miui/internal/widget/GuidePopupView;->kb:Landroid/animation/Animator$AnimatorListener;

    invoke-virtual {v0, v1}, Landroid/animation/ObjectAnimator;->addListener(Landroid/animation/Animator$AnimatorListener;)V

    .line 510
    iget-object v0, p0, Lcom/miui/internal/widget/GuidePopupView;->jM:Landroid/animation/ObjectAnimator;

    invoke-virtual {v0}, Landroid/animation/ObjectAnimator;->start()V

    goto :goto_0
.end method

.method public animateToShow()V
    .locals 2

    .prologue
    .line 480
    const/4 v0, 0x0

    invoke-virtual {p0, v0}, Lcom/miui/internal/widget/GuidePopupView;->setAlpha(F)V

    .line 482
    invoke-virtual {p0}, Lcom/miui/internal/widget/GuidePopupView;->invalidate()V

    .line 483
    invoke-virtual {p0}, Lcom/miui/internal/widget/GuidePopupView;->getViewTreeObserver()Landroid/view/ViewTreeObserver;

    move-result-object v0

    new-instance v1, Lcom/miui/internal/widget/GuidePopupView$1;

    invoke-direct {v1, p0}, Lcom/miui/internal/widget/GuidePopupView$1;-><init>(Lcom/miui/internal/widget/GuidePopupView;)V

    invoke-virtual {v0, v1}, Landroid/view/ViewTreeObserver;->addOnPreDrawListener(Landroid/view/ViewTreeObserver$OnPreDrawListener;)V

    .line 498
    return-void
.end method

.method public clearOffset()V
    .locals 1

    .prologue
    const/4 v0, 0x0

    .line 550
    invoke-virtual {p0, v0, v0}, Lcom/miui/internal/widget/GuidePopupView;->setOffset(II)V

    .line 551
    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/miui/internal/widget/GuidePopupView;->jI:Z

    .line 552
    return-void
.end method

.method protected dispatchDraw(Landroid/graphics/Canvas;)V
    .locals 3

    .prologue
    const/4 v2, 0x0

    .line 304
    invoke-super {p0, p1}, Landroid/widget/FrameLayout;->dispatchDraw(Landroid/graphics/Canvas;)V

    .line 306
    invoke-virtual {p1}, Landroid/graphics/Canvas;->save()I

    .line 308
    iget v0, p0, Lcom/miui/internal/widget/GuidePopupView;->jP:I

    int-to-float v0, v0

    iget v1, p0, Lcom/miui/internal/widget/GuidePopupView;->jQ:I

    int-to-float v1, v1

    invoke-virtual {p1, v0, v1}, Landroid/graphics/Canvas;->translate(FF)V

    .line 309
    iget-object v0, p0, Lcom/miui/internal/widget/GuidePopupView;->jD:Landroid/view/View;

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Landroid/view/View;->setDrawingCacheEnabled(Z)V

    .line 310
    iget-object v0, p0, Lcom/miui/internal/widget/GuidePopupView;->jD:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->buildDrawingCache()V

    .line 311
    iget-object v0, p0, Lcom/miui/internal/widget/GuidePopupView;->jD:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->getDrawingCache()Landroid/graphics/Bitmap;

    move-result-object v0

    .line 312
    const/4 v1, 0x0

    invoke-virtual {p1, v0, v2, v2, v1}, Landroid/graphics/Canvas;->drawBitmap(Landroid/graphics/Bitmap;FFLandroid/graphics/Paint;)V

    .line 313
    iget-object v0, p0, Lcom/miui/internal/widget/GuidePopupView;->jD:Landroid/view/View;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Landroid/view/View;->setDrawingCacheEnabled(Z)V

    .line 315
    invoke-virtual {p1}, Landroid/graphics/Canvas;->restore()V

    .line 317
    iget v0, p0, Lcom/miui/internal/widget/GuidePopupView;->kc:I

    iget v1, p0, Lcom/miui/internal/widget/GuidePopupView;->jG:I

    iget v2, p0, Lcom/miui/internal/widget/GuidePopupView;->jH:I

    invoke-direct {p0, p1, v0, v1, v2}, Lcom/miui/internal/widget/GuidePopupView;->a(Landroid/graphics/Canvas;III)V

    .line 318
    iget-boolean v0, p0, Lcom/miui/internal/widget/GuidePopupView;->jZ:Z

    if-eqz v0, :cond_0

    .line 319
    invoke-direct {p0}, Lcom/miui/internal/widget/GuidePopupView;->az()I

    move-result v0

    .line 320
    iget v1, p0, Lcom/miui/internal/widget/GuidePopupView;->jG:I

    neg-int v1, v1

    iget v2, p0, Lcom/miui/internal/widget/GuidePopupView;->jH:I

    neg-int v2, v2

    invoke-direct {p0, p1, v0, v1, v2}, Lcom/miui/internal/widget/GuidePopupView;->a(Landroid/graphics/Canvas;III)V

    .line 322
    :cond_0
    return-void
.end method

.method public getArrowMode()I
    .locals 1

    .prologue
    .line 514
    iget v0, p0, Lcom/miui/internal/widget/GuidePopupView;->kc:I

    return v0
.end method

.method public getColorBackground()I
    .locals 1

    .prologue
    .line 559
    iget v0, p0, Lcom/miui/internal/widget/GuidePopupView;->jW:I

    return v0
.end method

.method protected onFinishInflate()V
    .locals 1

    .prologue
    .line 196
    invoke-super {p0}, Landroid/widget/FrameLayout;->onFinishInflate()V

    .line 198
    sget v0, Lcom/miui/internal/R$id;->text_group:I

    invoke-virtual {p0, v0}, Lcom/miui/internal/widget/GuidePopupView;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/LinearLayout;

    iput-object v0, p0, Lcom/miui/internal/widget/GuidePopupView;->jE:Landroid/widget/LinearLayout;

    .line 199
    sget v0, Lcom/miui/internal/R$id;->mirrored_text_group:I

    invoke-virtual {p0, v0}, Lcom/miui/internal/widget/GuidePopupView;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/LinearLayout;

    iput-object v0, p0, Lcom/miui/internal/widget/GuidePopupView;->jF:Landroid/widget/LinearLayout;

    .line 200
    return-void
.end method

.method protected onLayout(ZIIII)V
    .locals 6

    .prologue
    const-wide/high16 v4, 0x4000000000000000L

    .line 281
    iget v0, p0, Lcom/miui/internal/widget/GuidePopupView;->jR:I

    if-eqz v0, :cond_0

    iget v0, p0, Lcom/miui/internal/widget/GuidePopupView;->jS:I

    if-nez v0, :cond_1

    .line 282
    :cond_0
    iget-object v0, p0, Lcom/miui/internal/widget/GuidePopupView;->jD:Landroid/view/View;

    invoke-virtual {p0, v0}, Lcom/miui/internal/widget/GuidePopupView;->setAnchor(Landroid/view/View;)V

    .line 284
    :cond_1
    iget-object v0, p0, Lcom/miui/internal/widget/GuidePopupView;->jE:Landroid/widget/LinearLayout;

    invoke-virtual {v0}, Landroid/widget/LinearLayout;->getMeasuredWidth()I

    move-result v0

    .line 285
    iget-object v1, p0, Lcom/miui/internal/widget/GuidePopupView;->jE:Landroid/widget/LinearLayout;

    invoke-virtual {v1}, Landroid/widget/LinearLayout;->getMeasuredHeight()I

    move-result v1

    .line 286
    int-to-double v2, v0

    invoke-static {v2, v3, v4, v5}, Ljava/lang/Math;->pow(DD)D

    move-result-wide v2

    int-to-double v0, v1

    invoke-static {v0, v1, v4, v5}, Ljava/lang/Math;->pow(DD)D

    move-result-wide v0

    add-double/2addr v0, v2

    invoke-static {v0, v1}, Ljava/lang/Math;->sqrt(D)D

    move-result-wide v0

    .line 287
    div-double/2addr v0, v4

    iget v2, p0, Lcom/miui/internal/widget/GuidePopupView;->jV:F

    float-to-double v2, v2

    invoke-static {v0, v1, v2, v3}, Ljava/lang/Math;->max(DD)D

    move-result-wide v0

    double-to-float v0, v0

    iput v0, p0, Lcom/miui/internal/widget/GuidePopupView;->jV:F

    .line 288
    iget-boolean v0, p0, Lcom/miui/internal/widget/GuidePopupView;->jZ:Z

    if-eqz v0, :cond_2

    .line 289
    iget-object v0, p0, Lcom/miui/internal/widget/GuidePopupView;->jF:Landroid/widget/LinearLayout;

    invoke-virtual {v0}, Landroid/widget/LinearLayout;->getMeasuredWidth()I

    move-result v0

    .line 290
    iget-object v1, p0, Lcom/miui/internal/widget/GuidePopupView;->jF:Landroid/widget/LinearLayout;

    invoke-virtual {v1}, Landroid/widget/LinearLayout;->getMeasuredHeight()I

    move-result v1

    .line 291
    int-to-double v2, v0

    invoke-static {v2, v3, v4, v5}, Ljava/lang/Math;->pow(DD)D

    move-result-wide v2

    int-to-double v0, v1

    invoke-static {v0, v1, v4, v5}, Ljava/lang/Math;->pow(DD)D

    move-result-wide v0

    add-double/2addr v0, v2

    invoke-static {v0, v1}, Ljava/lang/Math;->sqrt(D)D

    move-result-wide v0

    .line 292
    div-double/2addr v0, v4

    iget v2, p0, Lcom/miui/internal/widget/GuidePopupView;->jV:F

    float-to-double v2, v2

    invoke-static {v0, v1, v2, v3}, Ljava/lang/Math;->max(DD)D

    move-result-wide v0

    double-to-float v0, v0

    iput v0, p0, Lcom/miui/internal/widget/GuidePopupView;->jV:F

    .line 295
    :cond_2
    iget v0, p0, Lcom/miui/internal/widget/GuidePopupView;->kc:I

    const/4 v1, -0x1

    if-ne v0, v1, :cond_3

    .line 296
    invoke-direct {p0}, Lcom/miui/internal/widget/GuidePopupView;->aw()V

    .line 300
    :goto_0
    return-void

    .line 298
    :cond_3
    invoke-direct {p0}, Lcom/miui/internal/widget/GuidePopupView;->ax()V

    goto :goto_0
.end method

.method public onTouch(Landroid/view/View;Landroid/view/MotionEvent;)Z
    .locals 9

    .prologue
    const/4 v8, 0x1

    .line 587
    invoke-virtual {p2}, Landroid/view/MotionEvent;->getX()F

    move-result v0

    float-to-int v0, v0

    .line 588
    invoke-virtual {p2}, Landroid/view/MotionEvent;->getY()F

    move-result v1

    float-to-int v1, v1

    .line 590
    new-instance v2, Landroid/graphics/Rect;

    iget v3, p0, Lcom/miui/internal/widget/GuidePopupView;->jP:I

    iget v4, p0, Lcom/miui/internal/widget/GuidePopupView;->jQ:I

    iget v5, p0, Lcom/miui/internal/widget/GuidePopupView;->jP:I

    iget-object v6, p0, Lcom/miui/internal/widget/GuidePopupView;->jD:Landroid/view/View;

    invoke-virtual {v6}, Landroid/view/View;->getWidth()I

    move-result v6

    add-int/2addr v5, v6

    iget v6, p0, Lcom/miui/internal/widget/GuidePopupView;->jQ:I

    iget-object v7, p0, Lcom/miui/internal/widget/GuidePopupView;->jD:Landroid/view/View;

    invoke-virtual {v7}, Landroid/view/View;->getHeight()I

    move-result v7

    add-int/2addr v6, v7

    invoke-direct {v2, v3, v4, v5, v6}, Landroid/graphics/Rect;-><init>(IIII)V

    .line 592
    invoke-virtual {p2}, Landroid/view/MotionEvent;->getAction()I

    move-result v3

    if-nez v3, :cond_0

    invoke-virtual {v2, v0, v1}, Landroid/graphics/Rect;->contains(II)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 593
    iget-object v0, p0, Lcom/miui/internal/widget/GuidePopupView;->jD:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->callOnClick()Z

    .line 597
    :goto_0
    return v8

    .line 596
    :cond_0
    iget-object v0, p0, Lcom/miui/internal/widget/GuidePopupView;->jK:Lmiui/widget/GuidePopupWindow;

    invoke-virtual {v0, v8}, Lmiui/widget/GuidePopupWindow;->dismiss(Z)V

    goto :goto_0
.end method

.method public setAnchor(Landroid/view/View;)V
    .locals 2

    .prologue
    .line 532
    iput-object p1, p0, Lcom/miui/internal/widget/GuidePopupView;->jD:Landroid/view/View;

    .line 534
    iget-object v0, p0, Lcom/miui/internal/widget/GuidePopupView;->jD:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->getWidth()I

    move-result v0

    iput v0, p0, Lcom/miui/internal/widget/GuidePopupView;->jR:I

    .line 535
    iget-object v0, p0, Lcom/miui/internal/widget/GuidePopupView;->jD:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->getHeight()I

    move-result v0

    iput v0, p0, Lcom/miui/internal/widget/GuidePopupView;->jS:I

    .line 537
    const/4 v0, 0x2

    new-array v0, v0, [I

    .line 538
    iget-object v1, p0, Lcom/miui/internal/widget/GuidePopupView;->jD:Landroid/view/View;

    invoke-virtual {v1, v0}, Landroid/view/View;->getLocationInWindow([I)V

    .line 539
    const/4 v1, 0x0

    aget v1, v0, v1

    iput v1, p0, Lcom/miui/internal/widget/GuidePopupView;->jP:I

    .line 540
    const/4 v1, 0x1

    aget v0, v0, v1

    iput v0, p0, Lcom/miui/internal/widget/GuidePopupView;->jQ:I

    .line 541
    return-void
.end method

.method public setArrowMode(I)V
    .locals 0

    .prologue
    .line 518
    iput p1, p0, Lcom/miui/internal/widget/GuidePopupView;->kc:I

    .line 519
    return-void
.end method

.method public setArrowMode(IZ)V
    .locals 2

    .prologue
    .line 522
    invoke-virtual {p0, p1}, Lcom/miui/internal/widget/GuidePopupView;->setArrowMode(I)V

    .line 523
    iput-boolean p2, p0, Lcom/miui/internal/widget/GuidePopupView;->jZ:Z

    .line 524
    iget-boolean v0, p0, Lcom/miui/internal/widget/GuidePopupView;->jZ:Z

    if-eqz v0, :cond_0

    .line 525
    iget-object v0, p0, Lcom/miui/internal/widget/GuidePopupView;->jF:Landroid/widget/LinearLayout;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Landroid/widget/LinearLayout;->setVisibility(I)V

    .line 529
    :goto_0
    return-void

    .line 527
    :cond_0
    iget-object v0, p0, Lcom/miui/internal/widget/GuidePopupView;->jF:Landroid/widget/LinearLayout;

    const/16 v1, 0x8

    invoke-virtual {v0, v1}, Landroid/widget/LinearLayout;->setVisibility(I)V

    goto :goto_0
.end method

.method public setGuidePopupWindow(Lmiui/widget/GuidePopupWindow;)V
    .locals 0

    .prologue
    .line 555
    iput-object p1, p0, Lcom/miui/internal/widget/GuidePopupView;->jK:Lmiui/widget/GuidePopupWindow;

    .line 556
    return-void
.end method

.method public setOffset(II)V
    .locals 1

    .prologue
    .line 544
    iput p1, p0, Lcom/miui/internal/widget/GuidePopupView;->jG:I

    .line 545
    iput p2, p0, Lcom/miui/internal/widget/GuidePopupView;->jH:I

    .line 546
    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/miui/internal/widget/GuidePopupView;->jI:Z

    .line 547
    return-void
.end method

.method public setTouchInterceptor(Landroid/view/View$OnTouchListener;)V
    .locals 0

    .prologue
    .line 582
    iput-object p1, p0, Lcom/miui/internal/widget/GuidePopupView;->jL:Landroid/view/View$OnTouchListener;

    .line 583
    return-void
.end method
