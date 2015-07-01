.class public Lmiui/widget/Rotation3DLayout;
.super Landroid/widget/FrameLayout;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lmiui/widget/Rotation3DLayout$LayoutParams;
    }
.end annotation


# static fields
.field private static final LAYOUT_DIRECTION_LTR:I = 0x0

.field private static final LAYOUT_DIRECTION_RTL:I = 0x1

.field private static final uP:I = 0x800033

.field private static final uQ:I = 0xa

.field private static final uR:F

.field private static final uS:I = 0x2d

.field private static final uV:I = 0x12c

.field private static final uW:[F


# instance fields
.field private uT:F

.field private uU:F

.field private uX:I

.field private uY:[F

.field private uZ:[F

.field private ux:Landroid/animation/AnimatorSet;

.field private va:[F

.field private vb:J

.field private vc:Z

.field private vd:Z

.field private ve:Z

.field private vf:Z

.field private vg:F

.field private vh:F

.field private vi:Landroid/hardware/Sensor;

.field private vj:Landroid/hardware/SensorManager;

.field private vk:Landroid/hardware/SensorEventListener;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .prologue
    .line 44
    const/high16 v0, 0x41200000

    invoke-static {v0}, Lmiui/widget/Rotation3DLayout;->c(F)F

    move-result v0

    sput v0, Lmiui/widget/Rotation3DLayout;->uR:F

    .line 52
    const/4 v0, 0x4

    new-array v0, v0, [F

    fill-array-data v0, :array_0

    sput-object v0, Lmiui/widget/Rotation3DLayout;->uW:[F

    return-void

    nop

    :array_0
    .array-data 4
        0x3f800000
        -0x41000000
        0x3e800000
        0x0
    .end array-data
.end method

.method public constructor <init>(Landroid/content/Context;)V
    .locals 1

    .prologue
    .line 75
    const/4 v0, 0x0

    invoke-direct {p0, p1, v0}, Lmiui/widget/Rotation3DLayout;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    .line 76
    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 1

    .prologue
    .line 79
    const/4 v0, 0x0

    invoke-direct {p0, p1, p2, v0}, Lmiui/widget/Rotation3DLayout;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    .line 80
    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V
    .locals 4

    .prologue
    const/4 v3, 0x1

    const/4 v2, 0x0

    .line 83
    invoke-direct {p0, p1, p2, p3}, Landroid/widget/FrameLayout;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    .line 48
    const/high16 v0, 0x41200000

    iput v0, p0, Lmiui/widget/Rotation3DLayout;->uT:F

    .line 49
    sget v0, Lmiui/widget/Rotation3DLayout;->uR:F

    iput v0, p0, Lmiui/widget/Rotation3DLayout;->uU:F

    .line 54
    const/16 v0, 0x12c

    iput v0, p0, Lmiui/widget/Rotation3DLayout;->uX:I

    .line 55
    sget-object v0, Lmiui/widget/Rotation3DLayout;->uW:[F

    iput-object v0, p0, Lmiui/widget/Rotation3DLayout;->uY:[F

    .line 56
    sget-object v0, Lmiui/widget/Rotation3DLayout;->uW:[F

    array-length v0, v0

    new-array v0, v0, [F

    iput-object v0, p0, Lmiui/widget/Rotation3DLayout;->uZ:[F

    .line 57
    sget-object v0, Lmiui/widget/Rotation3DLayout;->uW:[F

    array-length v0, v0

    new-array v0, v0, [F

    iput-object v0, p0, Lmiui/widget/Rotation3DLayout;->va:[F

    .line 59
    const-wide/16 v0, 0x0

    iput-wide v0, p0, Lmiui/widget/Rotation3DLayout;->vb:J

    .line 60
    iput-boolean v3, p0, Lmiui/widget/Rotation3DLayout;->vc:Z

    .line 62
    iput-boolean v2, p0, Lmiui/widget/Rotation3DLayout;->vd:Z

    .line 63
    iput-boolean v2, p0, Lmiui/widget/Rotation3DLayout;->ve:Z

    .line 64
    iput-boolean v2, p0, Lmiui/widget/Rotation3DLayout;->vf:Z

    .line 445
    new-instance v0, Lmiui/widget/s;

    invoke-direct {v0, p0}, Lmiui/widget/s;-><init>(Lmiui/widget/Rotation3DLayout;)V

    iput-object v0, p0, Lmiui/widget/Rotation3DLayout;->vk:Landroid/hardware/SensorEventListener;

    .line 84
    sget-object v0, Lcom/miui/internal/R$styleable;->Rotation3DLayout:[I

    invoke-virtual {p1, p2, v0, p3, v2}, Landroid/content/Context;->obtainStyledAttributes(Landroid/util/AttributeSet;[III)Landroid/content/res/TypedArray;

    move-result-object v0

    .line 85
    iget v1, p0, Lmiui/widget/Rotation3DLayout;->uT:F

    invoke-virtual {v0, v2, v1}, Landroid/content/res/TypedArray;->getFloat(IF)F

    move-result v1

    invoke-virtual {p0, v1}, Lmiui/widget/Rotation3DLayout;->setMaxRotationDegree(F)V

    .line 86
    iget-boolean v1, p0, Lmiui/widget/Rotation3DLayout;->vf:Z

    invoke-virtual {v0, v3, v1}, Landroid/content/res/TypedArray;->getBoolean(IZ)Z

    move-result v1

    iput-boolean v1, p0, Lmiui/widget/Rotation3DLayout;->vf:Z

    .line 87
    invoke-virtual {v0}, Landroid/content/res/TypedArray;->recycle()V

    .line 88
    return-void
.end method

.method static synthetic a(Lmiui/widget/Rotation3DLayout;)F
    .locals 1

    .prologue
    .line 37
    iget v0, p0, Lmiui/widget/Rotation3DLayout;->uT:F

    return v0
.end method

.method static synthetic a(Lmiui/widget/Rotation3DLayout;F)F
    .locals 0

    .prologue
    .line 37
    iput p1, p0, Lmiui/widget/Rotation3DLayout;->vg:F

    return p1
.end method

.method private a(IF)I
    .locals 4

    .prologue
    .line 438
    float-to-double v0, p2

    invoke-static {v0, v1}, Ljava/lang/Math;->toRadians(D)D

    move-result-wide v0

    invoke-static {v0, v1}, Ljava/lang/Math;->sin(D)D

    move-result-wide v0

    int-to-double v2, p1

    mul-double/2addr v0, v2

    double-to-int v0, v0

    return v0
.end method

.method private a(FZ)V
    .locals 6

    .prologue
    .line 289
    invoke-static {p1}, Ljava/lang/Math;->abs(F)F

    move-result v0

    iget v1, p0, Lmiui/widget/Rotation3DLayout;->uT:F

    cmpl-float v0, v0, v1

    if-lez v0, :cond_4

    .line 290
    const/4 v0, 0x0

    cmpl-float v0, p1, v0

    if-lez v0, :cond_1

    iget v0, p0, Lmiui/widget/Rotation3DLayout;->uT:F

    .line 293
    :goto_0
    invoke-virtual {p0}, Lmiui/widget/Rotation3DLayout;->getRotationX()F

    move-result v1

    cmpl-float v1, v0, v1

    if-nez v1, :cond_2

    .line 301
    :cond_0
    :goto_1
    return-void

    .line 290
    :cond_1
    iget v0, p0, Lmiui/widget/Rotation3DLayout;->uT:F

    neg-float v0, v0

    goto :goto_0

    .line 297
    :cond_2
    if-nez p2, :cond_3

    iget-boolean v1, p0, Lmiui/widget/Rotation3DLayout;->vd:Z

    if-nez v1, :cond_0

    iget-boolean v1, p0, Lmiui/widget/Rotation3DLayout;->ve:Z

    if-nez v1, :cond_0

    .line 298
    :cond_3
    invoke-super {p0, v0}, Landroid/widget/FrameLayout;->setRotationX(F)V

    .line 299
    const/4 v1, 0x0

    invoke-virtual {p0}, Lmiui/widget/Rotation3DLayout;->getLeft()I

    move-result v2

    invoke-virtual {p0}, Lmiui/widget/Rotation3DLayout;->getTop()I

    move-result v3

    invoke-virtual {p0}, Lmiui/widget/Rotation3DLayout;->getRight()I

    move-result v4

    invoke-virtual {p0}, Lmiui/widget/Rotation3DLayout;->getBottom()I

    move-result v5

    move-object v0, p0

    invoke-virtual/range {v0 .. v5}, Lmiui/widget/Rotation3DLayout;->onLayout(ZIIII)V

    goto :goto_1

    :cond_4
    move v0, p1

    goto :goto_0
.end method

.method static synthetic a(Lmiui/widget/Rotation3DLayout;FZ)V
    .locals 0

    .prologue
    .line 37
    invoke-direct {p0, p1, p2}, Lmiui/widget/Rotation3DLayout;->a(FZ)V

    return-void
.end method

.method static synthetic a(Lmiui/widget/Rotation3DLayout;Z)Z
    .locals 0

    .prologue
    .line 37
    iput-boolean p1, p0, Lmiui/widget/Rotation3DLayout;->ve:Z

    return p1
.end method

.method static synthetic b(Lmiui/widget/Rotation3DLayout;)F
    .locals 1

    .prologue
    .line 37
    iget v0, p0, Lmiui/widget/Rotation3DLayout;->vg:F

    return v0
.end method

.method static synthetic b(Lmiui/widget/Rotation3DLayout;F)F
    .locals 0

    .prologue
    .line 37
    iput p1, p0, Lmiui/widget/Rotation3DLayout;->vh:F

    return p1
.end method

.method private b(FZ)V
    .locals 6

    .prologue
    .line 304
    invoke-static {p1}, Ljava/lang/Math;->abs(F)F

    move-result v0

    iget v1, p0, Lmiui/widget/Rotation3DLayout;->uT:F

    cmpl-float v0, v0, v1

    if-lez v0, :cond_4

    .line 305
    const/4 v0, 0x0

    cmpl-float v0, p1, v0

    if-lez v0, :cond_1

    iget v0, p0, Lmiui/widget/Rotation3DLayout;->uT:F

    .line 308
    :goto_0
    invoke-virtual {p0}, Lmiui/widget/Rotation3DLayout;->getRotationY()F

    move-result v1

    cmpl-float v1, v0, v1

    if-nez v1, :cond_2

    .line 316
    :cond_0
    :goto_1
    return-void

    .line 305
    :cond_1
    iget v0, p0, Lmiui/widget/Rotation3DLayout;->uT:F

    neg-float v0, v0

    goto :goto_0

    .line 312
    :cond_2
    if-nez p2, :cond_3

    iget-boolean v1, p0, Lmiui/widget/Rotation3DLayout;->vd:Z

    if-nez v1, :cond_0

    iget-boolean v1, p0, Lmiui/widget/Rotation3DLayout;->ve:Z

    if-nez v1, :cond_0

    .line 313
    :cond_3
    invoke-super {p0, v0}, Landroid/widget/FrameLayout;->setRotationY(F)V

    .line 314
    const/4 v1, 0x0

    invoke-virtual {p0}, Lmiui/widget/Rotation3DLayout;->getLeft()I

    move-result v2

    invoke-virtual {p0}, Lmiui/widget/Rotation3DLayout;->getTop()I

    move-result v3

    invoke-virtual {p0}, Lmiui/widget/Rotation3DLayout;->getRight()I

    move-result v4

    invoke-virtual {p0}, Lmiui/widget/Rotation3DLayout;->getBottom()I

    move-result v5

    move-object v0, p0

    invoke-virtual/range {v0 .. v5}, Lmiui/widget/Rotation3DLayout;->onLayout(ZIIII)V

    goto :goto_1

    :cond_4
    move v0, p1

    goto :goto_0
.end method

.method static synthetic b(Lmiui/widget/Rotation3DLayout;FZ)V
    .locals 0

    .prologue
    .line 37
    invoke-direct {p0, p1, p2}, Lmiui/widget/Rotation3DLayout;->b(FZ)V

    return-void
.end method

.method private static c(F)F
    .locals 2

    .prologue
    .line 442
    invoke-static {}, Lcom/miui/internal/util/PackageConstants;->getCurrentApplication()Landroid/app/Application;

    move-result-object v0

    invoke-virtual {v0}, Landroid/app/Application;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    invoke-virtual {v0}, Landroid/content/res/Resources;->getDisplayMetrics()Landroid/util/DisplayMetrics;

    move-result-object v0

    iget v0, v0, Landroid/util/DisplayMetrics;->widthPixels:I

    int-to-float v0, v0

    const/high16 v1, 0x40000000

    div-float/2addr v0, v1

    div-float v0, p0, v0

    return v0
.end method

.method static synthetic c(Lmiui/widget/Rotation3DLayout;)F
    .locals 1

    .prologue
    .line 37
    iget v0, p0, Lmiui/widget/Rotation3DLayout;->vh:F

    return v0
.end method

.method private g(II)I
    .locals 4

    .prologue
    const v3, 0x800005

    const v2, 0x800003

    const/4 v1, 0x1

    .line 406
    .line 408
    const/high16 v0, 0x800000

    and-int/2addr v0, p1

    if-lez v0, :cond_1

    .line 409
    and-int v0, p1, v2

    if-ne v0, v2, :cond_3

    .line 411
    const v0, -0x800004

    and-int/2addr v0, p1

    .line 412
    if-ne p2, v1, :cond_2

    .line 414
    or-int/lit8 p1, v0, 0x5

    .line 432
    :cond_0
    :goto_0
    const v0, -0x800001

    and-int/2addr p1, v0

    .line 434
    :cond_1
    return p1

    .line 417
    :cond_2
    or-int/lit8 p1, v0, 0x3

    goto :goto_0

    .line 419
    :cond_3
    and-int v0, p1, v3

    if-ne v0, v3, :cond_0

    .line 421
    const v0, -0x800006

    and-int/2addr v0, p1

    .line 422
    if-ne p2, v1, :cond_4

    .line 424
    or-int/lit8 p1, v0, 0x3

    goto :goto_0

    .line 427
    :cond_4
    or-int/lit8 p1, v0, 0x5

    goto :goto_0
.end method

.method private j(Z)V
    .locals 4

    .prologue
    const/4 v3, 0x0

    .line 108
    if-eqz p1, :cond_3

    .line 109
    iget-object v0, p0, Lmiui/widget/Rotation3DLayout;->vj:Landroid/hardware/SensorManager;

    if-nez v0, :cond_0

    .line 110
    invoke-virtual {p0}, Lmiui/widget/Rotation3DLayout;->getContext()Landroid/content/Context;

    move-result-object v0

    const-string v1, "sensor"

    invoke-virtual {v0, v1}, Landroid/content/Context;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/hardware/SensorManager;

    iput-object v0, p0, Lmiui/widget/Rotation3DLayout;->vj:Landroid/hardware/SensorManager;

    .line 112
    :cond_0
    iget-object v0, p0, Lmiui/widget/Rotation3DLayout;->vi:Landroid/hardware/Sensor;

    if-nez v0, :cond_1

    .line 113
    iget-object v0, p0, Lmiui/widget/Rotation3DLayout;->vj:Landroid/hardware/SensorManager;

    const/4 v1, 0x3

    invoke-virtual {v0, v1}, Landroid/hardware/SensorManager;->getDefaultSensor(I)Landroid/hardware/Sensor;

    move-result-object v0

    iput-object v0, p0, Lmiui/widget/Rotation3DLayout;->vi:Landroid/hardware/Sensor;

    .line 115
    :cond_1
    iget-object v0, p0, Lmiui/widget/Rotation3DLayout;->vj:Landroid/hardware/SensorManager;

    iget-object v1, p0, Lmiui/widget/Rotation3DLayout;->vk:Landroid/hardware/SensorEventListener;

    iget-object v2, p0, Lmiui/widget/Rotation3DLayout;->vi:Landroid/hardware/Sensor;

    const/4 v3, 0x1

    invoke-virtual {v0, v1, v2, v3}, Landroid/hardware/SensorManager;->registerListener(Landroid/hardware/SensorEventListener;Landroid/hardware/Sensor;I)Z

    .line 122
    :cond_2
    :goto_0
    return-void

    .line 116
    :cond_3
    iget-object v0, p0, Lmiui/widget/Rotation3DLayout;->vj:Landroid/hardware/SensorManager;

    if-eqz v0, :cond_2

    iget-object v0, p0, Lmiui/widget/Rotation3DLayout;->vi:Landroid/hardware/Sensor;

    if-eqz v0, :cond_2

    .line 117
    iget-object v0, p0, Lmiui/widget/Rotation3DLayout;->vj:Landroid/hardware/SensorManager;

    iget-object v1, p0, Lmiui/widget/Rotation3DLayout;->vk:Landroid/hardware/SensorEventListener;

    iget-object v2, p0, Lmiui/widget/Rotation3DLayout;->vi:Landroid/hardware/Sensor;

    invoke-virtual {v0, v1, v2}, Landroid/hardware/SensorManager;->unregisterListener(Landroid/hardware/SensorEventListener;Landroid/hardware/Sensor;)V

    .line 118
    iput-object v3, p0, Lmiui/widget/Rotation3DLayout;->vi:Landroid/hardware/Sensor;

    .line 119
    iput-object v3, p0, Lmiui/widget/Rotation3DLayout;->vj:Landroid/hardware/SensorManager;

    .line 120
    invoke-virtual {p0}, Lmiui/widget/Rotation3DLayout;->resetRotation()V

    goto :goto_0
.end method


# virtual methods
.method public enableAutoRotationByGravity(Z)V
    .locals 1

    .prologue
    .line 103
    iput-boolean p1, p0, Lmiui/widget/Rotation3DLayout;->vf:Z

    .line 104
    iget-boolean v0, p0, Lmiui/widget/Rotation3DLayout;->vf:Z

    invoke-direct {p0, v0}, Lmiui/widget/Rotation3DLayout;->j(Z)V

    .line 105
    return-void
.end method

.method public enableTouchRotation(Z)V
    .locals 0

    .prologue
    .line 95
    iput-boolean p1, p0, Lmiui/widget/Rotation3DLayout;->vc:Z

    .line 96
    return-void
.end method

.method protected finalize()V
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/Throwable;
        }
    .end annotation

    .prologue
    .line 332
    invoke-super {p0}, Ljava/lang/Object;->finalize()V

    .line 333
    const/4 v0, 0x0

    invoke-direct {p0, v0}, Lmiui/widget/Rotation3DLayout;->j(Z)V

    .line 334
    return-void
.end method

.method protected bridge synthetic generateDefaultLayoutParams()Landroid/view/ViewGroup$LayoutParams;
    .locals 1

    .prologue
    .line 37
    invoke-virtual {p0}, Lmiui/widget/Rotation3DLayout;->generateDefaultLayoutParams()Landroid/widget/FrameLayout$LayoutParams;

    move-result-object v0

    return-object v0
.end method

.method protected generateDefaultLayoutParams()Landroid/widget/FrameLayout$LayoutParams;
    .locals 2

    .prologue
    const/4 v1, -0x1

    .line 468
    new-instance v0, Lmiui/widget/Rotation3DLayout$LayoutParams;

    invoke-direct {v0, v1, v1}, Lmiui/widget/Rotation3DLayout$LayoutParams;-><init>(II)V

    return-object v0
.end method

.method public bridge synthetic generateLayoutParams(Landroid/util/AttributeSet;)Landroid/view/ViewGroup$LayoutParams;
    .locals 1

    .prologue
    .line 37
    invoke-virtual {p0, p1}, Lmiui/widget/Rotation3DLayout;->generateLayoutParams(Landroid/util/AttributeSet;)Landroid/widget/FrameLayout$LayoutParams;

    move-result-object v0

    return-object v0
.end method

.method protected generateLayoutParams(Landroid/view/ViewGroup$LayoutParams;)Landroid/view/ViewGroup$LayoutParams;
    .locals 1

    .prologue
    .line 478
    new-instance v0, Lmiui/widget/Rotation3DLayout$LayoutParams;

    invoke-direct {v0, p1}, Lmiui/widget/Rotation3DLayout$LayoutParams;-><init>(Landroid/view/ViewGroup$LayoutParams;)V

    return-object v0
.end method

.method public generateLayoutParams(Landroid/util/AttributeSet;)Landroid/widget/FrameLayout$LayoutParams;
    .locals 2

    .prologue
    .line 473
    new-instance v0, Lmiui/widget/Rotation3DLayout$LayoutParams;

    invoke-virtual {p0}, Lmiui/widget/Rotation3DLayout;->getContext()Landroid/content/Context;

    move-result-object v1

    invoke-direct {v0, v1, p1}, Lmiui/widget/Rotation3DLayout$LayoutParams;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    return-object v0
.end method

.method public getMaxRotationDegree()F
    .locals 1

    .prologue
    .line 227
    iget v0, p0, Lmiui/widget/Rotation3DLayout;->uT:F

    return v0
.end method

.method public onInterceptTouchEvent(Landroid/view/MotionEvent;)Z
    .locals 1

    .prologue
    .line 232
    iget-boolean v0, p0, Lmiui/widget/Rotation3DLayout;->vc:Z

    if-nez v0, :cond_0

    .line 233
    invoke-super {p0, p1}, Landroid/widget/FrameLayout;->onInterceptTouchEvent(Landroid/view/MotionEvent;)Z

    move-result v0

    .line 235
    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x1

    goto :goto_0
.end method

.method protected onLayout(ZIIII)V
    .locals 14

    .prologue
    .line 338
    invoke-virtual {p0}, Lmiui/widget/Rotation3DLayout;->getChildCount()I

    move-result v4

    .line 340
    invoke-virtual {p0}, Lmiui/widget/Rotation3DLayout;->getPaddingLeft()I

    move-result v5

    .line 341
    sub-int v0, p4, p2

    invoke-virtual {p0}, Lmiui/widget/Rotation3DLayout;->getPaddingRight()I

    move-result v1

    sub-int v6, v0, v1

    .line 343
    invoke-virtual {p0}, Lmiui/widget/Rotation3DLayout;->getPaddingTop()I

    move-result v7

    .line 344
    sub-int v0, p5, p3

    invoke-virtual {p0}, Lmiui/widget/Rotation3DLayout;->getPaddingBottom()I

    move-result v1

    sub-int v8, v0, v1

    .line 346
    const/4 v0, 0x0

    move v3, v0

    :goto_0
    if-ge v3, v4, :cond_3

    .line 347
    invoke-virtual {p0, v3}, Lmiui/widget/Rotation3DLayout;->getChildAt(I)Landroid/view/View;

    move-result-object v9

    .line 348
    invoke-virtual {v9}, Landroid/view/View;->getVisibility()I

    move-result v0

    const/16 v1, 0x8

    if-eq v0, v1, :cond_2

    .line 349
    invoke-virtual {v9}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v0

    check-cast v0, Lmiui/widget/Rotation3DLayout$LayoutParams;

    .line 351
    invoke-virtual {v9}, Landroid/view/View;->getMeasuredWidth()I

    move-result v10

    .line 352
    invoke-virtual {v9}, Landroid/view/View;->getMeasuredHeight()I

    move-result v11

    .line 357
    iget v1, v0, Lmiui/widget/Rotation3DLayout$LayoutParams;->gravity:I

    .line 358
    const/4 v2, -0x1

    if-ne v1, v2, :cond_0

    .line 359
    const v1, 0x800033

    .line 363
    :cond_0
    const/4 v2, 0x0

    invoke-direct {p0, v1, v2}, Lmiui/widget/Rotation3DLayout;->g(II)I

    move-result v2

    .line 364
    and-int/lit8 v12, v1, 0x70

    .line 366
    and-int/lit8 v1, v2, 0x7

    packed-switch v1, :pswitch_data_0

    .line 378
    :pswitch_0
    iget v1, v0, Lmiui/widget/Rotation3DLayout$LayoutParams;->leftMargin:I

    add-int/2addr v1, v5

    move v2, v1

    .line 381
    :goto_1
    sparse-switch v12, :sswitch_data_0

    .line 393
    iget v1, v0, Lmiui/widget/Rotation3DLayout$LayoutParams;->topMargin:I

    add-int/2addr v1, v7

    .line 396
    :goto_2
    iget v12, v0, Lmiui/widget/Rotation3DLayout$LayoutParams;->zdistance:I

    invoke-virtual {p0}, Lmiui/widget/Rotation3DLayout;->getRotationY()F

    move-result v13

    invoke-direct {p0, v12, v13}, Lmiui/widget/Rotation3DLayout;->a(IF)I

    move-result v12

    add-int/2addr v2, v12

    .line 397
    iget v0, v0, Lmiui/widget/Rotation3DLayout$LayoutParams;->zdistance:I

    invoke-virtual {p0}, Lmiui/widget/Rotation3DLayout;->getRotationX()F

    move-result v12

    invoke-direct {p0, v0, v12}, Lmiui/widget/Rotation3DLayout;->a(IF)I

    move-result v0

    sub-int v0, v1, v0

    .line 398
    if-nez p1, :cond_1

    invoke-virtual {v9}, Landroid/view/View;->getLeft()I

    move-result v1

    if-ne v2, v1, :cond_1

    invoke-virtual {v9}, Landroid/view/View;->getTop()I

    move-result v1

    if-eq v0, v1, :cond_2

    .line 399
    :cond_1
    add-int v1, v2, v10

    add-int v10, v0, v11

    invoke-virtual {v9, v2, v0, v1, v10}, Landroid/view/View;->layout(IIII)V

    .line 346
    :cond_2
    add-int/lit8 v0, v3, 0x1

    move v3, v0

    goto :goto_0

    .line 368
    :pswitch_1
    iget v1, v0, Lmiui/widget/Rotation3DLayout$LayoutParams;->leftMargin:I

    add-int/2addr v1, v5

    move v2, v1

    .line 369
    goto :goto_1

    .line 371
    :pswitch_2
    sub-int v1, v6, v5

    sub-int/2addr v1, v10

    div-int/lit8 v1, v1, 0x2

    add-int/2addr v1, v5

    iget v2, v0, Lmiui/widget/Rotation3DLayout$LayoutParams;->leftMargin:I

    add-int/2addr v1, v2

    iget v2, v0, Lmiui/widget/Rotation3DLayout$LayoutParams;->rightMargin:I

    sub-int/2addr v1, v2

    move v2, v1

    .line 373
    goto :goto_1

    .line 375
    :pswitch_3
    sub-int v1, v6, v10

    iget v2, v0, Lmiui/widget/Rotation3DLayout$LayoutParams;->rightMargin:I

    sub-int/2addr v1, v2

    move v2, v1

    .line 376
    goto :goto_1

    .line 383
    :sswitch_0
    iget v1, v0, Lmiui/widget/Rotation3DLayout$LayoutParams;->topMargin:I

    add-int/2addr v1, v7

    .line 384
    goto :goto_2

    .line 386
    :sswitch_1
    sub-int v1, v8, v7

    sub-int/2addr v1, v11

    div-int/lit8 v1, v1, 0x2

    add-int/2addr v1, v7

    iget v12, v0, Lmiui/widget/Rotation3DLayout$LayoutParams;->topMargin:I

    add-int/2addr v1, v12

    iget v12, v0, Lmiui/widget/Rotation3DLayout$LayoutParams;->bottomMargin:I

    sub-int/2addr v1, v12

    .line 388
    goto :goto_2

    .line 390
    :sswitch_2
    sub-int v1, v8, v11

    iget v12, v0, Lmiui/widget/Rotation3DLayout$LayoutParams;->bottomMargin:I

    sub-int/2addr v1, v12

    .line 391
    goto :goto_2

    .line 403
    :cond_3
    return-void

    .line 366
    :pswitch_data_0
    .packed-switch 0x1
        :pswitch_2
        :pswitch_0
        :pswitch_1
        :pswitch_0
        :pswitch_3
    .end packed-switch

    .line 381
    :sswitch_data_0
    .sparse-switch
        0x10 -> :sswitch_1
        0x30 -> :sswitch_0
        0x50 -> :sswitch_2
    .end sparse-switch
.end method

.method public onTouchEvent(Landroid/view/MotionEvent;)Z
    .locals 6

    .prologue
    const/4 v5, 0x0

    const/4 v0, 0x1

    .line 241
    iget-boolean v1, p0, Lmiui/widget/Rotation3DLayout;->vc:Z

    if-nez v1, :cond_0

    .line 242
    invoke-super {p0, p1}, Landroid/widget/FrameLayout;->onTouchEvent(Landroid/view/MotionEvent;)Z

    move-result v0

    .line 275
    :goto_0
    return v0

    .line 245
    :cond_0
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getAction()I

    move-result v1

    packed-switch v1, :pswitch_data_0

    .line 274
    :goto_1
    invoke-super {p0, p1}, Landroid/widget/FrameLayout;->onTouchEvent(Landroid/view/MotionEvent;)Z

    goto :goto_0

    .line 247
    :pswitch_0
    invoke-static {}, Landroid/os/SystemClock;->elapsedRealtime()J

    move-result-wide v1

    iget-wide v3, p0, Lmiui/widget/Rotation3DLayout;->vb:J

    sub-long/2addr v1, v3

    invoke-static {}, Landroid/view/ViewConfiguration;->getTapTimeout()I

    move-result v3

    int-to-long v3, v3

    cmp-long v1, v1, v3

    if-lez v1, :cond_1

    .line 248
    invoke-virtual {p0}, Lmiui/widget/Rotation3DLayout;->getParent()Landroid/view/ViewParent;

    move-result-object v1

    invoke-interface {v1, v0}, Landroid/view/ViewParent;->requestDisallowInterceptTouchEvent(Z)V

    .line 252
    :cond_1
    :pswitch_1
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getAction()I

    move-result v1

    if-nez v1, :cond_2

    .line 253
    invoke-static {}, Landroid/os/SystemClock;->elapsedRealtime()J

    move-result-wide v1

    iput-wide v1, p0, Lmiui/widget/Rotation3DLayout;->vb:J

    .line 255
    :cond_2
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getX()F

    move-result v1

    invoke-static {v5, v1}, Ljava/lang/Math;->max(FF)F

    move-result v1

    invoke-virtual {p0}, Lmiui/widget/Rotation3DLayout;->getWidth()I

    move-result v2

    int-to-float v2, v2

    invoke-static {v1, v2}, Ljava/lang/Math;->min(FF)F

    move-result v1

    .line 256
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getY()F

    move-result v2

    invoke-static {v5, v2}, Ljava/lang/Math;->max(FF)F

    move-result v2

    invoke-virtual {p0}, Lmiui/widget/Rotation3DLayout;->getHeight()I

    move-result v3

    int-to-float v3, v3

    invoke-static {v2, v3}, Ljava/lang/Math;->min(FF)F

    move-result v2

    .line 257
    invoke-virtual {p0}, Lmiui/widget/Rotation3DLayout;->getWidth()I

    move-result v3

    div-int/lit8 v3, v3, 0x2

    .line 258
    invoke-virtual {p0}, Lmiui/widget/Rotation3DLayout;->getHeight()I

    move-result v4

    div-int/lit8 v4, v4, 0x2

    .line 259
    iget-object v5, p0, Lmiui/widget/Rotation3DLayout;->ux:Landroid/animation/AnimatorSet;

    if-eqz v5, :cond_3

    .line 260
    iget-object v5, p0, Lmiui/widget/Rotation3DLayout;->ux:Landroid/animation/AnimatorSet;

    invoke-virtual {v5}, Landroid/animation/AnimatorSet;->cancel()V

    .line 262
    :cond_3
    int-to-float v4, v4

    sub-float v2, v4, v2

    iget v4, p0, Lmiui/widget/Rotation3DLayout;->uU:F

    mul-float/2addr v2, v4

    invoke-direct {p0, v2, v0}, Lmiui/widget/Rotation3DLayout;->a(FZ)V

    .line 263
    int-to-float v2, v3

    sub-float/2addr v1, v2

    iget v2, p0, Lmiui/widget/Rotation3DLayout;->uU:F

    mul-float/2addr v1, v2

    invoke-direct {p0, v1, v0}, Lmiui/widget/Rotation3DLayout;->b(FZ)V

    .line 264
    iput-boolean v0, p0, Lmiui/widget/Rotation3DLayout;->vd:Z

    goto :goto_1

    .line 268
    :pswitch_2
    invoke-virtual {p0}, Lmiui/widget/Rotation3DLayout;->resetRotation()V

    .line 269
    const/4 v1, 0x0

    iput-boolean v1, p0, Lmiui/widget/Rotation3DLayout;->vd:Z

    goto :goto_1

    .line 245
    nop

    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_1
        :pswitch_2
        :pswitch_0
        :pswitch_2
    .end packed-switch
.end method

.method protected onVisibilityChanged(Landroid/view/View;I)V
    .locals 1

    .prologue
    .line 320
    invoke-super {p0, p1, p2}, Landroid/widget/FrameLayout;->onVisibilityChanged(Landroid/view/View;I)V

    .line 321
    if-nez p2, :cond_1

    .line 322
    iget-boolean v0, p0, Lmiui/widget/Rotation3DLayout;->vf:Z

    if-eqz v0, :cond_0

    .line 323
    const/4 v0, 0x1

    invoke-direct {p0, v0}, Lmiui/widget/Rotation3DLayout;->j(Z)V

    .line 328
    :cond_0
    :goto_0
    return-void

    .line 326
    :cond_1
    const/4 v0, 0x0

    invoke-direct {p0, v0}, Lmiui/widget/Rotation3DLayout;->j(Z)V

    goto :goto_0
.end method

.method protected resetRotation()V
    .locals 8

    .prologue
    const/4 v7, 0x1

    const/high16 v4, 0x3f400000

    const/4 v1, 0x0

    .line 131
    iget-object v0, p0, Lmiui/widget/Rotation3DLayout;->ux:Landroid/animation/AnimatorSet;

    if-nez v0, :cond_0

    .line 132
    new-instance v0, Landroid/animation/AnimatorSet;

    invoke-direct {v0}, Landroid/animation/AnimatorSet;-><init>()V

    iput-object v0, p0, Lmiui/widget/Rotation3DLayout;->ux:Landroid/animation/AnimatorSet;

    .line 133
    iget-object v0, p0, Lmiui/widget/Rotation3DLayout;->ux:Landroid/animation/AnimatorSet;

    new-instance v2, Lmiui/widget/Rotation3DLayout$1;

    invoke-direct {v2, p0}, Lmiui/widget/Rotation3DLayout$1;-><init>(Lmiui/widget/Rotation3DLayout;)V

    invoke-virtual {v0, v2}, Landroid/animation/AnimatorSet;->addListener(Landroid/animation/Animator$AnimatorListener;)V

    .line 154
    new-instance v0, Landroid/animation/ValueAnimator;

    invoke-direct {v0}, Landroid/animation/ValueAnimator;-><init>()V

    .line 155
    new-instance v2, Landroid/view/animation/DecelerateInterpolator;

    invoke-direct {v2, v4}, Landroid/view/animation/DecelerateInterpolator;-><init>(F)V

    invoke-virtual {v0, v2}, Landroid/animation/ValueAnimator;->setInterpolator(Landroid/animation/TimeInterpolator;)V

    .line 156
    new-instance v2, Lmiui/widget/Rotation3DLayout$2;

    invoke-direct {v2, p0}, Lmiui/widget/Rotation3DLayout$2;-><init>(Lmiui/widget/Rotation3DLayout;)V

    invoke-virtual {v0, v2}, Landroid/animation/ValueAnimator;->addUpdateListener(Landroid/animation/ValueAnimator$AnimatorUpdateListener;)V

    .line 163
    iget v2, p0, Lmiui/widget/Rotation3DLayout;->uX:I

    int-to-long v2, v2

    invoke-virtual {v0, v2, v3}, Landroid/animation/ValueAnimator;->setDuration(J)Landroid/animation/ValueAnimator;

    .line 165
    new-instance v2, Landroid/animation/ValueAnimator;

    invoke-direct {v2}, Landroid/animation/ValueAnimator;-><init>()V

    .line 166
    new-instance v3, Landroid/view/animation/DecelerateInterpolator;

    invoke-direct {v3, v4}, Landroid/view/animation/DecelerateInterpolator;-><init>(F)V

    invoke-virtual {v2, v3}, Landroid/animation/ValueAnimator;->setInterpolator(Landroid/animation/TimeInterpolator;)V

    .line 167
    new-instance v3, Lmiui/widget/Rotation3DLayout$3;

    invoke-direct {v3, p0}, Lmiui/widget/Rotation3DLayout$3;-><init>(Lmiui/widget/Rotation3DLayout;)V

    invoke-virtual {v2, v3}, Landroid/animation/ValueAnimator;->addUpdateListener(Landroid/animation/ValueAnimator$AnimatorUpdateListener;)V

    .line 174
    iget v3, p0, Lmiui/widget/Rotation3DLayout;->uX:I

    int-to-long v3, v3

    invoke-virtual {v2, v3, v4}, Landroid/animation/ValueAnimator;->setDuration(J)Landroid/animation/ValueAnimator;

    .line 175
    iget-object v3, p0, Lmiui/widget/Rotation3DLayout;->ux:Landroid/animation/AnimatorSet;

    const/4 v4, 0x2

    new-array v4, v4, [Landroid/animation/Animator;

    aput-object v0, v4, v1

    aput-object v2, v4, v7

    invoke-virtual {v3, v4}, Landroid/animation/AnimatorSet;->playTogether([Landroid/animation/Animator;)V

    .line 177
    :cond_0
    iget-object v0, p0, Lmiui/widget/Rotation3DLayout;->ux:Landroid/animation/AnimatorSet;

    invoke-virtual {v0}, Landroid/animation/AnimatorSet;->cancel()V

    .line 178
    invoke-virtual {p0}, Lmiui/widget/Rotation3DLayout;->getRotationX()F

    move-result v2

    .line 179
    invoke-virtual {p0}, Lmiui/widget/Rotation3DLayout;->getRotationY()F

    move-result v3

    move v0, v1

    .line 181
    :goto_0
    iget-object v4, p0, Lmiui/widget/Rotation3DLayout;->uY:[F

    array-length v4, v4

    if-ge v0, v4, :cond_1

    .line 182
    iget-object v4, p0, Lmiui/widget/Rotation3DLayout;->uZ:[F

    iget v5, p0, Lmiui/widget/Rotation3DLayout;->vg:F

    iget-object v6, p0, Lmiui/widget/Rotation3DLayout;->uY:[F

    aget v6, v6, v0

    mul-float/2addr v6, v2

    add-float/2addr v5, v6

    aput v5, v4, v0

    .line 183
    iget-object v4, p0, Lmiui/widget/Rotation3DLayout;->va:[F

    iget v5, p0, Lmiui/widget/Rotation3DLayout;->vh:F

    iget-object v6, p0, Lmiui/widget/Rotation3DLayout;->uY:[F

    aget v6, v6, v0

    mul-float/2addr v6, v3

    add-float/2addr v5, v6

    aput v5, v4, v0

    .line 181
    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    .line 185
    :cond_1
    iget-object v0, p0, Lmiui/widget/Rotation3DLayout;->ux:Landroid/animation/AnimatorSet;

    invoke-virtual {v0}, Landroid/animation/AnimatorSet;->getChildAnimations()Ljava/util/ArrayList;

    move-result-object v0

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/animation/ValueAnimator;

    iget-object v1, p0, Lmiui/widget/Rotation3DLayout;->uZ:[F

    invoke-virtual {v0, v1}, Landroid/animation/ValueAnimator;->setFloatValues([F)V

    .line 186
    iget-object v0, p0, Lmiui/widget/Rotation3DLayout;->ux:Landroid/animation/AnimatorSet;

    invoke-virtual {v0}, Landroid/animation/AnimatorSet;->getChildAnimations()Ljava/util/ArrayList;

    move-result-object v0

    invoke-virtual {v0, v7}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/animation/ValueAnimator;

    iget-object v1, p0, Lmiui/widget/Rotation3DLayout;->va:[F

    invoke-virtual {v0, v1}, Landroid/animation/ValueAnimator;->setFloatValues([F)V

    .line 187
    iget-object v0, p0, Lmiui/widget/Rotation3DLayout;->ux:Landroid/animation/AnimatorSet;

    invoke-virtual {v0}, Landroid/animation/AnimatorSet;->start()V

    .line 188
    return-void
.end method

.method public setMaxRotationDegree(F)V
    .locals 1

    .prologue
    .line 218
    iput p1, p0, Lmiui/widget/Rotation3DLayout;->uT:F

    .line 219
    invoke-static {p1}, Lmiui/widget/Rotation3DLayout;->c(F)F

    move-result v0

    iput v0, p0, Lmiui/widget/Rotation3DLayout;->uU:F

    .line 220
    return-void
.end method

.method public setResetAnimDuration(I)V
    .locals 0

    .prologue
    .line 196
    iput p1, p0, Lmiui/widget/Rotation3DLayout;->uX:I

    .line 197
    return-void
.end method

.method public setResetBouncePatterns([F)V
    .locals 2

    .prologue
    .line 206
    if-eqz p1, :cond_0

    array-length v0, p1

    const/4 v1, 0x1

    if-le v0, v1, :cond_0

    .line 207
    iput-object p1, p0, Lmiui/widget/Rotation3DLayout;->uY:[F

    .line 208
    array-length v0, p1

    new-array v0, v0, [F

    iput-object v0, p0, Lmiui/widget/Rotation3DLayout;->uZ:[F

    .line 209
    array-length v0, p1

    new-array v0, v0, [F

    iput-object v0, p0, Lmiui/widget/Rotation3DLayout;->va:[F

    .line 211
    :cond_0
    return-void
.end method

.method public setRotationX(F)V
    .locals 1

    .prologue
    .line 280
    const/4 v0, 0x0

    invoke-direct {p0, p1, v0}, Lmiui/widget/Rotation3DLayout;->a(FZ)V

    .line 281
    return-void
.end method

.method public setRotationY(F)V
    .locals 1

    .prologue
    .line 285
    const/4 v0, 0x0

    invoke-direct {p0, p1, v0}, Lmiui/widget/Rotation3DLayout;->b(FZ)V

    .line 286
    return-void
.end method

.method protected stopResetRotation()V
    .locals 1

    .prologue
    .line 125
    iget-object v0, p0, Lmiui/widget/Rotation3DLayout;->ux:Landroid/animation/AnimatorSet;

    if-eqz v0, :cond_0

    .line 126
    iget-object v0, p0, Lmiui/widget/Rotation3DLayout;->ux:Landroid/animation/AnimatorSet;

    invoke-virtual {v0}, Landroid/animation/AnimatorSet;->cancel()V

    .line 128
    :cond_0
    return-void
.end method
