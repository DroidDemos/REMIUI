.class public Lmiui/widget/SlidingButton;
.super Landroid/widget/CheckBox;
.source "SourceFile"


# static fields
.field private static final ANIMATION_DURATION:I = 0xb4

.field private static final KS:I = 0x64

.field private static final zz:I = 0xff


# instance fields
.field private KT:Landroid/graphics/drawable/Drawable;

.field private KU:Landroid/graphics/drawable/Drawable;

.field private KV:I

.field private KX:Landroid/graphics/drawable/Drawable;

.field private KZ:Landroid/graphics/Bitmap;

.field private La:Landroid/graphics/Paint;

.field private Lb:Landroid/graphics/Bitmap;

.field private Lc:Landroid/graphics/Paint;

.field private Ld:Landroid/graphics/Bitmap;

.field private Le:I

.field private Lf:I

.field private Lg:I

.field private Lh:I

.field private Li:I

.field private Lj:I

.field private Lk:I

.field private Ll:I

.field private Lm:Z

.field private Ln:Z

.field private Lo:I

.field private Lp:Landroid/widget/CompoundButton$OnCheckedChangeListener;

.field private Lq:Landroid/animation/Animator;

.field private Lr:Landroid/graphics/drawable/Drawable;

.field private Ls:Landroid/graphics/drawable/Drawable;

.field private kF:Landroid/animation/Animator$AnimatorListener;

.field private nN:Landroid/graphics/Rect;

.field private pi:Z


# direct methods
.method public constructor <init>(Landroid/content/Context;)V
    .locals 1

    .prologue
    .line 132
    const/4 v0, 0x0

    invoke-direct {p0, p1, v0}, Lmiui/widget/SlidingButton;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    .line 133
    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 1

    .prologue
    .line 136
    const/4 v0, 0x0

    invoke-direct {p0, p1, p2, v0}, Lmiui/widget/SlidingButton;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    .line 137
    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V
    .locals 9

    .prologue
    const/4 v8, 0x7

    const/4 v4, 0x6

    const/4 v7, 0x1

    const/4 v6, 0x0

    .line 140
    invoke-direct {p0, p1, p2, p3}, Landroid/widget/CheckBox;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    .line 83
    new-instance v0, Landroid/graphics/Rect;

    invoke-direct {v0}, Landroid/graphics/Rect;-><init>()V

    iput-object v0, p0, Lmiui/widget/SlidingButton;->nN:Landroid/graphics/Rect;

    .line 87
    iput-boolean v6, p0, Lmiui/widget/SlidingButton;->pi:Z

    .line 93
    new-instance v0, Lmiui/widget/h;

    invoke-direct {v0, p0}, Lmiui/widget/h;-><init>(Lmiui/widget/SlidingButton;)V

    iput-object v0, p0, Lmiui/widget/SlidingButton;->kF:Landroid/animation/Animator$AnimatorListener;

    .line 141
    sget-object v0, Lcom/miui/internal/R$styleable;->SlidingButton:[I

    sget v1, Lcom/miui/internal/R$style;->Widget_SlidingButton:I

    invoke-virtual {p1, p2, v0, p3, v1}, Landroid/content/Context;->obtainStyledAttributes(Landroid/util/AttributeSet;[III)Landroid/content/res/TypedArray;

    move-result-object v1

    .line 145
    invoke-virtual {p0, v6}, Lmiui/widget/SlidingButton;->setDrawingCacheEnabled(Z)V

    .line 146
    invoke-static {p1}, Landroid/view/ViewConfiguration;->get(Landroid/content/Context;)Landroid/view/ViewConfiguration;

    move-result-object v0

    invoke-virtual {v0}, Landroid/view/ViewConfiguration;->getScaledTouchSlop()I

    move-result v0

    div-int/lit8 v0, v0, 0x2

    iput v0, p0, Lmiui/widget/SlidingButton;->Lo:I

    .line 148
    invoke-virtual {v1, v7}, Landroid/content/res/TypedArray;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v0

    iput-object v0, p0, Lmiui/widget/SlidingButton;->KT:Landroid/graphics/drawable/Drawable;

    .line 149
    const/4 v0, 0x2

    invoke-virtual {v1, v0}, Landroid/content/res/TypedArray;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v0

    iput-object v0, p0, Lmiui/widget/SlidingButton;->KU:Landroid/graphics/drawable/Drawable;

    .line 150
    const/4 v0, 0x3

    invoke-virtual {v1, v0}, Landroid/content/res/TypedArray;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v0

    iput-object v0, p0, Lmiui/widget/SlidingButton;->KX:Landroid/graphics/drawable/Drawable;

    .line 151
    invoke-virtual {v1, v6}, Landroid/content/res/TypedArray;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v0

    invoke-virtual {p0, v0}, Lmiui/widget/SlidingButton;->setBackground(Landroid/graphics/drawable/Drawable;)V

    .line 153
    iget-object v0, p0, Lmiui/widget/SlidingButton;->KT:Landroid/graphics/drawable/Drawable;

    invoke-virtual {v0}, Landroid/graphics/drawable/Drawable;->getIntrinsicWidth()I

    move-result v0

    iput v0, p0, Lmiui/widget/SlidingButton;->Le:I

    .line 154
    iget-object v0, p0, Lmiui/widget/SlidingButton;->KT:Landroid/graphics/drawable/Drawable;

    invoke-virtual {v0}, Landroid/graphics/drawable/Drawable;->getIntrinsicHeight()I

    move-result v0

    iput v0, p0, Lmiui/widget/SlidingButton;->Lf:I

    .line 156
    iget v0, p0, Lmiui/widget/SlidingButton;->Le:I

    iget-object v2, p0, Lmiui/widget/SlidingButton;->KU:Landroid/graphics/drawable/Drawable;

    invoke-virtual {v2}, Landroid/graphics/drawable/Drawable;->getIntrinsicWidth()I

    move-result v2

    invoke-static {v0, v2}, Ljava/lang/Math;->min(II)I

    move-result v0

    iput v0, p0, Lmiui/widget/SlidingButton;->Lg:I

    .line 157
    iput v6, p0, Lmiui/widget/SlidingButton;->Lh:I

    .line 158
    iget v0, p0, Lmiui/widget/SlidingButton;->Le:I

    iget v2, p0, Lmiui/widget/SlidingButton;->Lg:I

    sub-int/2addr v0, v2

    iput v0, p0, Lmiui/widget/SlidingButton;->Li:I

    .line 159
    iget v0, p0, Lmiui/widget/SlidingButton;->Lh:I

    iput v0, p0, Lmiui/widget/SlidingButton;->Lj:I

    .line 161
    new-instance v2, Landroid/util/TypedValue;

    invoke-direct {v2}, Landroid/util/TypedValue;-><init>()V

    .line 162
    invoke-virtual {v1, v4, v2}, Landroid/content/res/TypedArray;->getValue(ILandroid/util/TypedValue;)Z

    .line 163
    new-instance v3, Landroid/util/TypedValue;

    invoke-direct {v3}, Landroid/util/TypedValue;-><init>()V

    .line 164
    invoke-virtual {v1, v8, v3}, Landroid/content/res/TypedArray;->getValue(ILandroid/util/TypedValue;)Z

    .line 165
    invoke-virtual {v1, v4}, Landroid/content/res/TypedArray;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v0

    check-cast v0, Landroid/graphics/drawable/BitmapDrawable;

    .line 166
    invoke-virtual {v0}, Landroid/graphics/drawable/BitmapDrawable;->getBitmap()Landroid/graphics/Bitmap;

    move-result-object v0

    iget v4, p0, Lmiui/widget/SlidingButton;->Le:I

    mul-int/lit8 v4, v4, 0x2

    iget v5, p0, Lmiui/widget/SlidingButton;->Lg:I

    sub-int/2addr v4, v5

    iget v5, p0, Lmiui/widget/SlidingButton;->Lf:I

    invoke-static {v0, v4, v5, v7}, Landroid/graphics/Bitmap;->createScaledBitmap(Landroid/graphics/Bitmap;IIZ)Landroid/graphics/Bitmap;

    move-result-object v0

    iput-object v0, p0, Lmiui/widget/SlidingButton;->KZ:Landroid/graphics/Bitmap;

    .line 170
    iget v0, v2, Landroid/util/TypedValue;->type:I

    iget v4, v3, Landroid/util/TypedValue;->type:I

    if-ne v0, v4, :cond_0

    iget v0, v2, Landroid/util/TypedValue;->data:I

    iget v4, v3, Landroid/util/TypedValue;->data:I

    if-ne v0, v4, :cond_0

    iget v0, v2, Landroid/util/TypedValue;->resourceId:I

    iget v2, v3, Landroid/util/TypedValue;->resourceId:I

    if-ne v0, v2, :cond_0

    .line 173
    iget-object v0, p0, Lmiui/widget/SlidingButton;->KZ:Landroid/graphics/Bitmap;

    iput-object v0, p0, Lmiui/widget/SlidingButton;->Lb:Landroid/graphics/Bitmap;

    .line 181
    :goto_0
    iget-object v0, p0, Lmiui/widget/SlidingButton;->KT:Landroid/graphics/drawable/Drawable;

    iget v2, p0, Lmiui/widget/SlidingButton;->Le:I

    iget v3, p0, Lmiui/widget/SlidingButton;->Lf:I

    invoke-virtual {v0, v6, v6, v2, v3}, Landroid/graphics/drawable/Drawable;->setBounds(IIII)V

    .line 183
    const/4 v0, 0x4

    invoke-virtual {v1, v0}, Landroid/content/res/TypedArray;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v0

    .line 184
    iget v2, p0, Lmiui/widget/SlidingButton;->Le:I

    iget v3, p0, Lmiui/widget/SlidingButton;->Lf:I

    invoke-virtual {v0, v6, v6, v2, v3}, Landroid/graphics/drawable/Drawable;->setBounds(IIII)V

    .line 185
    invoke-direct {p0, v0}, Lmiui/widget/SlidingButton;->f(Landroid/graphics/drawable/Drawable;)Landroid/graphics/Bitmap;

    move-result-object v0

    iput-object v0, p0, Lmiui/widget/SlidingButton;->Ld:Landroid/graphics/Bitmap;

    .line 187
    new-instance v0, Landroid/graphics/Paint;

    invoke-direct {v0}, Landroid/graphics/Paint;-><init>()V

    iput-object v0, p0, Lmiui/widget/SlidingButton;->La:Landroid/graphics/Paint;

    .line 188
    new-instance v0, Landroid/graphics/Paint;

    invoke-direct {v0}, Landroid/graphics/Paint;-><init>()V

    iput-object v0, p0, Lmiui/widget/SlidingButton;->Lc:Landroid/graphics/Paint;

    .line 189
    iget-object v0, p0, Lmiui/widget/SlidingButton;->Lc:Landroid/graphics/Paint;

    new-instance v2, Landroid/graphics/PorterDuffXfermode;

    sget-object v3, Landroid/graphics/PorterDuff$Mode;->SRC_IN:Landroid/graphics/PorterDuff$Mode;

    invoke-direct {v2, v3}, Landroid/graphics/PorterDuffXfermode;-><init>(Landroid/graphics/PorterDuff$Mode;)V

    invoke-virtual {v0, v2}, Landroid/graphics/Paint;->setXfermode(Landroid/graphics/Xfermode;)Landroid/graphics/Xfermode;

    .line 190
    iget-object v0, p0, Lmiui/widget/SlidingButton;->La:Landroid/graphics/Paint;

    new-instance v2, Landroid/graphics/PorterDuffXfermode;

    sget-object v3, Landroid/graphics/PorterDuff$Mode;->SRC_IN:Landroid/graphics/PorterDuff$Mode;

    invoke-direct {v2, v3}, Landroid/graphics/PorterDuffXfermode;-><init>(Landroid/graphics/PorterDuff$Mode;)V

    invoke-virtual {v0, v2}, Landroid/graphics/Paint;->setXfermode(Landroid/graphics/Xfermode;)Landroid/graphics/Xfermode;

    .line 192
    invoke-direct {p0, v7}, Lmiui/widget/SlidingButton;->p(Z)Landroid/graphics/drawable/Drawable;

    move-result-object v0

    iput-object v0, p0, Lmiui/widget/SlidingButton;->Lr:Landroid/graphics/drawable/Drawable;

    .line 193
    invoke-direct {p0, v6}, Lmiui/widget/SlidingButton;->p(Z)Landroid/graphics/drawable/Drawable;

    move-result-object v0

    iput-object v0, p0, Lmiui/widget/SlidingButton;->Ls:Landroid/graphics/drawable/Drawable;

    .line 195
    invoke-virtual {v1}, Landroid/content/res/TypedArray;->recycle()V

    .line 196
    return-void

    .line 175
    :cond_0
    invoke-virtual {v1, v8}, Landroid/content/res/TypedArray;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v0

    check-cast v0, Landroid/graphics/drawable/BitmapDrawable;

    .line 176
    invoke-virtual {v0}, Landroid/graphics/drawable/BitmapDrawable;->getBitmap()Landroid/graphics/Bitmap;

    move-result-object v0

    iget v2, p0, Lmiui/widget/SlidingButton;->Le:I

    mul-int/lit8 v2, v2, 0x2

    iget v3, p0, Lmiui/widget/SlidingButton;->Lg:I

    sub-int/2addr v2, v3

    iget v3, p0, Lmiui/widget/SlidingButton;->Lf:I

    invoke-static {v0, v2, v3, v7}, Landroid/graphics/Bitmap;->createScaledBitmap(Landroid/graphics/Bitmap;IIZ)Landroid/graphics/Bitmap;

    move-result-object v0

    iput-object v0, p0, Lmiui/widget/SlidingButton;->Lb:Landroid/graphics/Bitmap;

    goto :goto_0
.end method

.method static synthetic a(Lmiui/widget/SlidingButton;)I
    .locals 1

    .prologue
    .line 33
    iget v0, p0, Lmiui/widget/SlidingButton;->Lj:I

    return v0
.end method

.method static synthetic a(Lmiui/widget/SlidingButton;Landroid/animation/Animator;)Landroid/animation/Animator;
    .locals 0

    .prologue
    .line 33
    iput-object p1, p0, Lmiui/widget/SlidingButton;->Lq:Landroid/animation/Animator;

    return-object p1
.end method

.method static synthetic a(Lmiui/widget/SlidingButton;Z)Z
    .locals 0

    .prologue
    .line 33
    iput-boolean p1, p0, Lmiui/widget/SlidingButton;->pi:Z

    return p1
.end method

.method private ai(I)V
    .locals 2

    .prologue
    .line 334
    iget v0, p0, Lmiui/widget/SlidingButton;->Lj:I

    add-int/2addr v0, p1

    iput v0, p0, Lmiui/widget/SlidingButton;->Lj:I

    .line 335
    iget v0, p0, Lmiui/widget/SlidingButton;->Lj:I

    iget v1, p0, Lmiui/widget/SlidingButton;->Lh:I

    if-ge v0, v1, :cond_1

    .line 336
    iget v0, p0, Lmiui/widget/SlidingButton;->Lh:I

    iput v0, p0, Lmiui/widget/SlidingButton;->Lj:I

    .line 340
    :cond_0
    :goto_0
    iget v0, p0, Lmiui/widget/SlidingButton;->Lj:I

    invoke-virtual {p0, v0}, Lmiui/widget/SlidingButton;->setSliderOffset(I)V

    .line 341
    return-void

    .line 337
    :cond_1
    iget v0, p0, Lmiui/widget/SlidingButton;->Lj:I

    iget v1, p0, Lmiui/widget/SlidingButton;->Li:I

    if-le v0, v1, :cond_0

    .line 338
    iget v0, p0, Lmiui/widget/SlidingButton;->Li:I

    iput v0, p0, Lmiui/widget/SlidingButton;->Lj:I

    goto :goto_0
.end method

.method private animateToggle()V
    .locals 1

    .prologue
    .line 312
    invoke-virtual {p0}, Lmiui/widget/SlidingButton;->isChecked()Z

    move-result v0

    if-nez v0, :cond_0

    const/4 v0, 0x1

    :goto_0
    invoke-direct {p0, v0}, Lmiui/widget/SlidingButton;->o(Z)V

    .line 313
    return-void

    .line 312
    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method static synthetic b(Lmiui/widget/SlidingButton;)I
    .locals 1

    .prologue
    .line 33
    iget v0, p0, Lmiui/widget/SlidingButton;->Li:I

    return v0
.end method

.method static synthetic c(Lmiui/widget/SlidingButton;)Landroid/widget/CompoundButton$OnCheckedChangeListener;
    .locals 1

    .prologue
    .line 33
    iget-object v0, p0, Lmiui/widget/SlidingButton;->Lp:Landroid/widget/CompoundButton$OnCheckedChangeListener;

    return-object v0
.end method

.method private f(Landroid/graphics/drawable/Drawable;)Landroid/graphics/Bitmap;
    .locals 3

    .prologue
    .line 206
    invoke-virtual {p1}, Landroid/graphics/drawable/Drawable;->getBounds()Landroid/graphics/Rect;

    move-result-object v0

    .line 207
    invoke-virtual {v0}, Landroid/graphics/Rect;->width()I

    move-result v1

    invoke-virtual {v0}, Landroid/graphics/Rect;->height()I

    move-result v0

    sget-object v2, Landroid/graphics/Bitmap$Config;->ALPHA_8:Landroid/graphics/Bitmap$Config;

    invoke-static {v1, v0, v2}, Landroid/graphics/Bitmap;->createBitmap(IILandroid/graphics/Bitmap$Config;)Landroid/graphics/Bitmap;

    move-result-object v0

    .line 208
    new-instance v1, Landroid/graphics/Canvas;

    invoke-direct {v1, v0}, Landroid/graphics/Canvas;-><init>(Landroid/graphics/Bitmap;)V

    .line 209
    invoke-virtual {p1, v1}, Landroid/graphics/drawable/Drawable;->draw(Landroid/graphics/Canvas;)V

    .line 210
    return-object v0
.end method

.method private h(Landroid/graphics/Canvas;)V
    .locals 3

    .prologue
    const/4 v2, 0x0

    .line 407
    iget-object v0, p0, Lmiui/widget/SlidingButton;->Lc:Landroid/graphics/Paint;

    invoke-virtual {v0}, Landroid/graphics/Paint;->getAlpha()I

    move-result v0

    if-eqz v0, :cond_0

    .line 408
    iget-object v0, p0, Lmiui/widget/SlidingButton;->Lb:Landroid/graphics/Bitmap;

    iget-object v1, p0, Lmiui/widget/SlidingButton;->Lc:Landroid/graphics/Paint;

    invoke-virtual {p1, v0, v2, v2, v1}, Landroid/graphics/Canvas;->drawBitmap(Landroid/graphics/Bitmap;FFLandroid/graphics/Paint;)V

    .line 411
    :cond_0
    iget-object v0, p0, Lmiui/widget/SlidingButton;->La:Landroid/graphics/Paint;

    invoke-virtual {v0}, Landroid/graphics/Paint;->getAlpha()I

    move-result v0

    if-eqz v0, :cond_1

    .line 412
    iget-object v0, p0, Lmiui/widget/SlidingButton;->KZ:Landroid/graphics/Bitmap;

    iget-object v1, p0, Lmiui/widget/SlidingButton;->La:Landroid/graphics/Paint;

    invoke-virtual {p1, v0, v2, v2, v1}, Landroid/graphics/Canvas;->drawBitmap(Landroid/graphics/Bitmap;FFLandroid/graphics/Paint;)V

    .line 414
    :cond_1
    return-void
.end method

.method private o(Z)V
    .locals 8

    .prologue
    const-wide/16 v6, 0xb4

    const/4 v5, 0x1

    const/4 v1, 0x0

    .line 316
    iget-object v0, p0, Lmiui/widget/SlidingButton;->Lq:Landroid/animation/Animator;

    if-eqz v0, :cond_0

    .line 317
    iget-object v0, p0, Lmiui/widget/SlidingButton;->Lq:Landroid/animation/Animator;

    invoke-virtual {v0}, Landroid/animation/Animator;->cancel()V

    .line 318
    const/4 v0, 0x0

    iput-object v0, p0, Lmiui/widget/SlidingButton;->Lq:Landroid/animation/Animator;

    .line 320
    :cond_0
    new-instance v2, Landroid/animation/AnimatorSet;

    invoke-direct {v2}, Landroid/animation/AnimatorSet;-><init>()V

    .line 321
    const-string v3, "SliderOffset"

    new-array v4, v5, [I

    if-eqz p1, :cond_1

    iget v0, p0, Lmiui/widget/SlidingButton;->Li:I

    :goto_0
    aput v0, v4, v1

    invoke-static {p0, v3, v4}, Landroid/animation/ObjectAnimator;->ofInt(Ljava/lang/Object;Ljava/lang/String;[I)Landroid/animation/ObjectAnimator;

    move-result-object v3

    .line 323
    const-string v4, "SliderOnAlpha"

    new-array v5, v5, [I

    if-eqz p1, :cond_2

    const/16 v0, 0xff

    :goto_1
    aput v0, v5, v1

    invoke-static {p0, v4, v5}, Landroid/animation/ObjectAnimator;->ofInt(Ljava/lang/Object;Ljava/lang/String;[I)Landroid/animation/ObjectAnimator;

    move-result-object v0

    .line 324
    invoke-virtual {v0, v6, v7}, Landroid/animation/ObjectAnimator;->setDuration(J)Landroid/animation/ObjectAnimator;

    .line 325
    invoke-virtual {v3, v6, v7}, Landroid/animation/ObjectAnimator;->setDuration(J)Landroid/animation/ObjectAnimator;

    .line 326
    invoke-virtual {v2, v0}, Landroid/animation/AnimatorSet;->play(Landroid/animation/Animator;)Landroid/animation/AnimatorSet$Builder;

    move-result-object v0

    invoke-virtual {v0, v3}, Landroid/animation/AnimatorSet$Builder;->after(Landroid/animation/Animator;)Landroid/animation/AnimatorSet$Builder;

    move-result-object v0

    const-wide/16 v3, 0x64

    invoke-virtual {v0, v3, v4}, Landroid/animation/AnimatorSet$Builder;->after(J)Landroid/animation/AnimatorSet$Builder;

    .line 327
    iput-object v2, p0, Lmiui/widget/SlidingButton;->Lq:Landroid/animation/Animator;

    .line 328
    iget-object v0, p0, Lmiui/widget/SlidingButton;->Lq:Landroid/animation/Animator;

    iget-object v1, p0, Lmiui/widget/SlidingButton;->kF:Landroid/animation/Animator$AnimatorListener;

    invoke-virtual {v0, v1}, Landroid/animation/Animator;->addListener(Landroid/animation/Animator$AnimatorListener;)V

    .line 329
    iget-object v0, p0, Lmiui/widget/SlidingButton;->Lq:Landroid/animation/Animator;

    invoke-virtual {v0}, Landroid/animation/Animator;->start()V

    .line 330
    return-void

    .line 321
    :cond_1
    iget v0, p0, Lmiui/widget/SlidingButton;->Lh:I

    goto :goto_0

    :cond_2
    move v0, v1

    .line 323
    goto :goto_1
.end method

.method private p(Z)Landroid/graphics/drawable/Drawable;
    .locals 6

    .prologue
    const/4 v5, 0x0

    const/4 v4, 0x0

    .line 417
    iget v0, p0, Lmiui/widget/SlidingButton;->Le:I

    iget v1, p0, Lmiui/widget/SlidingButton;->Lf:I

    sget-object v2, Landroid/graphics/Bitmap$Config;->ARGB_8888:Landroid/graphics/Bitmap$Config;

    invoke-static {v0, v1, v2}, Landroid/graphics/Bitmap;->createBitmap(IILandroid/graphics/Bitmap$Config;)Landroid/graphics/Bitmap;

    move-result-object v0

    .line 418
    new-instance v1, Landroid/graphics/Canvas;

    invoke-direct {v1, v0}, Landroid/graphics/Canvas;-><init>(Landroid/graphics/Bitmap;)V

    .line 421
    iget-object v2, p0, Lmiui/widget/SlidingButton;->Ld:Landroid/graphics/Bitmap;

    const/4 v3, 0x0

    invoke-virtual {v1, v2, v4, v4, v3}, Landroid/graphics/Canvas;->drawBitmap(Landroid/graphics/Bitmap;FFLandroid/graphics/Paint;)V

    .line 423
    if-eqz p1, :cond_0

    .line 424
    iget-object v2, p0, Lmiui/widget/SlidingButton;->Lb:Landroid/graphics/Bitmap;

    iget-object v3, p0, Lmiui/widget/SlidingButton;->Lc:Landroid/graphics/Paint;

    invoke-virtual {v1, v2, v4, v4, v3}, Landroid/graphics/Canvas;->drawBitmap(Landroid/graphics/Bitmap;FFLandroid/graphics/Paint;)V

    .line 429
    :goto_0
    new-instance v1, Landroid/graphics/drawable/BitmapDrawable;

    invoke-virtual {p0}, Lmiui/widget/SlidingButton;->getContext()Landroid/content/Context;

    move-result-object v2

    invoke-virtual {v2}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v2

    invoke-direct {v1, v2, v0}, Landroid/graphics/drawable/BitmapDrawable;-><init>(Landroid/content/res/Resources;Landroid/graphics/Bitmap;)V

    .line 430
    iget v0, p0, Lmiui/widget/SlidingButton;->Le:I

    iget v2, p0, Lmiui/widget/SlidingButton;->Lf:I

    invoke-virtual {v1, v5, v5, v0, v2}, Landroid/graphics/drawable/Drawable;->setBounds(IIII)V

    .line 431
    return-object v1

    .line 426
    :cond_0
    iget-object v2, p0, Lmiui/widget/SlidingButton;->KZ:Landroid/graphics/Bitmap;

    iget-object v3, p0, Lmiui/widget/SlidingButton;->La:Landroid/graphics/Paint;

    invoke-virtual {v1, v2, v4, v4, v3}, Landroid/graphics/Canvas;->drawBitmap(Landroid/graphics/Bitmap;FFLandroid/graphics/Paint;)V

    goto :goto_0
.end method


# virtual methods
.method protected drawableStateChanged()V
    .locals 2

    .prologue
    .line 234
    invoke-super {p0}, Landroid/widget/CheckBox;->drawableStateChanged()V

    .line 235
    iget-object v0, p0, Lmiui/widget/SlidingButton;->KU:Landroid/graphics/drawable/Drawable;

    invoke-virtual {p0}, Lmiui/widget/SlidingButton;->getDrawableState()[I

    move-result-object v1

    invoke-virtual {v0, v1}, Landroid/graphics/drawable/Drawable;->setState([I)Z

    .line 236
    return-void
.end method

.method public getSliderOffset()I
    .locals 1

    .prologue
    .line 347
    iget v0, p0, Lmiui/widget/SlidingButton;->Lj:I

    return v0
.end method

.method public getSliderOnAlpha()I
    .locals 1

    .prologue
    .line 362
    iget v0, p0, Lmiui/widget/SlidingButton;->KV:I

    return v0
.end method

.method protected onDraw(Landroid/graphics/Canvas;)V
    .locals 8

    .prologue
    const/4 v7, 0x0

    const/4 v1, 0x0

    .line 375
    invoke-virtual {p0}, Lmiui/widget/SlidingButton;->isEnabled()Z

    move-result v0

    if-eqz v0, :cond_1

    const/16 v5, 0xff

    .line 376
    :goto_0
    iget-boolean v0, p0, Lmiui/widget/SlidingButton;->Lm:Z

    if-nez v0, :cond_0

    iget-boolean v0, p0, Lmiui/widget/SlidingButton;->pi:Z

    if-eqz v0, :cond_2

    .line 377
    :cond_0
    iget-object v0, p0, Lmiui/widget/SlidingButton;->Ld:Landroid/graphics/Bitmap;

    invoke-virtual {v0}, Landroid/graphics/Bitmap;->getWidth()I

    move-result v0

    int-to-float v3, v0

    iget-object v0, p0, Lmiui/widget/SlidingButton;->Ld:Landroid/graphics/Bitmap;

    invoke-virtual {v0}, Landroid/graphics/Bitmap;->getHeight()I

    move-result v0

    int-to-float v4, v0

    const/4 v6, 0x4

    move-object v0, p1

    move v2, v1

    invoke-virtual/range {v0 .. v6}, Landroid/graphics/Canvas;->saveLayerAlpha(FFFFII)I

    .line 379
    iget-object v0, p0, Lmiui/widget/SlidingButton;->Ld:Landroid/graphics/Bitmap;

    const/4 v2, 0x0

    invoke-virtual {p1, v0, v1, v1, v2}, Landroid/graphics/Canvas;->drawBitmap(Landroid/graphics/Bitmap;FFLandroid/graphics/Paint;)V

    .line 381
    invoke-direct {p0, p1}, Lmiui/widget/SlidingButton;->h(Landroid/graphics/Canvas;)V

    .line 382
    invoke-virtual {p1}, Landroid/graphics/Canvas;->restore()V

    .line 392
    :goto_1
    iget-object v0, p0, Lmiui/widget/SlidingButton;->KT:Landroid/graphics/drawable/Drawable;

    invoke-virtual {v0, p1}, Landroid/graphics/drawable/Drawable;->draw(Landroid/graphics/Canvas;)V

    .line 394
    invoke-virtual {p0}, Lmiui/widget/SlidingButton;->isChecked()Z

    move-result v0

    if-eqz v0, :cond_4

    .line 395
    iget-object v0, p0, Lmiui/widget/SlidingButton;->KU:Landroid/graphics/drawable/Drawable;

    invoke-virtual {v0, v5}, Landroid/graphics/drawable/Drawable;->setAlpha(I)V

    .line 396
    iget-object v0, p0, Lmiui/widget/SlidingButton;->KU:Landroid/graphics/drawable/Drawable;

    iget v1, p0, Lmiui/widget/SlidingButton;->Lj:I

    iget v2, p0, Lmiui/widget/SlidingButton;->Lg:I

    iget v3, p0, Lmiui/widget/SlidingButton;->Lj:I

    add-int/2addr v2, v3

    iget v3, p0, Lmiui/widget/SlidingButton;->Lf:I

    invoke-virtual {v0, v1, v7, v2, v3}, Landroid/graphics/drawable/Drawable;->setBounds(IIII)V

    .line 397
    iget-object v0, p0, Lmiui/widget/SlidingButton;->KU:Landroid/graphics/drawable/Drawable;

    invoke-virtual {v0, p1}, Landroid/graphics/drawable/Drawable;->draw(Landroid/graphics/Canvas;)V

    .line 404
    :goto_2
    return-void

    .line 375
    :cond_1
    const/16 v5, 0x7f

    goto :goto_0

    .line 384
    :cond_2
    invoke-virtual {p0}, Lmiui/widget/SlidingButton;->isChecked()Z

    move-result v0

    if-eqz v0, :cond_3

    .line 385
    iget-object v0, p0, Lmiui/widget/SlidingButton;->Lr:Landroid/graphics/drawable/Drawable;

    invoke-virtual {v0, p1}, Landroid/graphics/drawable/Drawable;->draw(Landroid/graphics/Canvas;)V

    goto :goto_1

    .line 387
    :cond_3
    iget-object v0, p0, Lmiui/widget/SlidingButton;->Ls:Landroid/graphics/drawable/Drawable;

    invoke-virtual {v0, p1}, Landroid/graphics/drawable/Drawable;->draw(Landroid/graphics/Canvas;)V

    goto :goto_1

    .line 399
    :cond_4
    iget-object v0, p0, Lmiui/widget/SlidingButton;->KX:Landroid/graphics/drawable/Drawable;

    invoke-virtual {v0, v5}, Landroid/graphics/drawable/Drawable;->setAlpha(I)V

    .line 400
    iget-object v0, p0, Lmiui/widget/SlidingButton;->KX:Landroid/graphics/drawable/Drawable;

    iget v1, p0, Lmiui/widget/SlidingButton;->Lj:I

    iget v2, p0, Lmiui/widget/SlidingButton;->Lg:I

    iget v3, p0, Lmiui/widget/SlidingButton;->Lj:I

    add-int/2addr v2, v3

    iget v3, p0, Lmiui/widget/SlidingButton;->Lf:I

    invoke-virtual {v0, v1, v7, v2, v3}, Landroid/graphics/drawable/Drawable;->setBounds(IIII)V

    .line 401
    iget-object v0, p0, Lmiui/widget/SlidingButton;->KX:Landroid/graphics/drawable/Drawable;

    invoke-virtual {v0, p1}, Landroid/graphics/drawable/Drawable;->draw(Landroid/graphics/Canvas;)V

    goto :goto_2
.end method

.method protected onMeasure(II)V
    .locals 2

    .prologue
    .line 240
    iget v0, p0, Lmiui/widget/SlidingButton;->Le:I

    iget v1, p0, Lmiui/widget/SlidingButton;->Lf:I

    invoke-virtual {p0, v0, v1}, Lmiui/widget/SlidingButton;->setMeasuredDimension(II)V

    .line 241
    return-void
.end method

.method public onTouchEvent(Landroid/view/MotionEvent;)Z
    .locals 9

    .prologue
    const/4 v1, 0x1

    const/4 v2, 0x0

    .line 245
    invoke-virtual {p0}, Lmiui/widget/SlidingButton;->isEnabled()Z

    move-result v0

    if-nez v0, :cond_0

    .line 302
    :goto_0
    return v2

    .line 249
    :cond_0
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getAction()I

    move-result v0

    .line 250
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getX()F

    move-result v3

    float-to-int v3, v3

    .line 251
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getY()F

    move-result v4

    float-to-int v4, v4

    .line 252
    iget-object v5, p0, Lmiui/widget/SlidingButton;->nN:Landroid/graphics/Rect;

    .line 253
    iget v6, p0, Lmiui/widget/SlidingButton;->Lj:I

    iget v7, p0, Lmiui/widget/SlidingButton;->Lj:I

    iget v8, p0, Lmiui/widget/SlidingButton;->Lg:I

    add-int/2addr v7, v8

    iget v8, p0, Lmiui/widget/SlidingButton;->Lf:I

    invoke-virtual {v5, v6, v2, v7, v8}, Landroid/graphics/Rect;->set(IIII)V

    .line 255
    packed-switch v0, :pswitch_data_0

    :cond_1
    :goto_1
    move v2, v1

    .line 302
    goto :goto_0

    .line 257
    :pswitch_0
    invoke-virtual {v5, v3, v4}, Landroid/graphics/Rect;->contains(II)Z

    move-result v0

    if-eqz v0, :cond_2

    .line 258
    iput-boolean v1, p0, Lmiui/widget/SlidingButton;->Lm:Z

    .line 259
    invoke-virtual {p0, v1}, Lmiui/widget/SlidingButton;->setPressed(Z)V

    .line 263
    :goto_2
    iput v3, p0, Lmiui/widget/SlidingButton;->Lk:I

    .line 264
    iput v3, p0, Lmiui/widget/SlidingButton;->Ll:I

    .line 265
    iput-boolean v2, p0, Lmiui/widget/SlidingButton;->Ln:Z

    goto :goto_1

    .line 261
    :cond_2
    iput-boolean v2, p0, Lmiui/widget/SlidingButton;->Lm:Z

    goto :goto_2

    .line 269
    :pswitch_1
    iget-boolean v0, p0, Lmiui/widget/SlidingButton;->Lm:Z

    if-eqz v0, :cond_1

    .line 270
    iget v0, p0, Lmiui/widget/SlidingButton;->Lk:I

    sub-int v0, v3, v0

    invoke-direct {p0, v0}, Lmiui/widget/SlidingButton;->ai(I)V

    .line 271
    iput v3, p0, Lmiui/widget/SlidingButton;->Lk:I

    .line 272
    iget v0, p0, Lmiui/widget/SlidingButton;->Ll:I

    sub-int v0, v3, v0

    invoke-static {v0}, Ljava/lang/Math;->abs(I)I

    move-result v0

    iget v2, p0, Lmiui/widget/SlidingButton;->Lo:I

    if-lt v0, v2, :cond_1

    .line 273
    iput-boolean v1, p0, Lmiui/widget/SlidingButton;->Ln:Z

    .line 274
    invoke-virtual {p0}, Lmiui/widget/SlidingButton;->getParent()Landroid/view/ViewParent;

    move-result-object v0

    invoke-interface {v0, v1}, Landroid/view/ViewParent;->requestDisallowInterceptTouchEvent(Z)V

    goto :goto_1

    .line 280
    :pswitch_2
    iget-boolean v0, p0, Lmiui/widget/SlidingButton;->Lm:Z

    if-eqz v0, :cond_5

    .line 281
    iget-boolean v0, p0, Lmiui/widget/SlidingButton;->Ln:Z

    if-nez v0, :cond_3

    .line 282
    invoke-direct {p0}, Lmiui/widget/SlidingButton;->animateToggle()V

    .line 289
    :goto_3
    iput-boolean v2, p0, Lmiui/widget/SlidingButton;->Lm:Z

    .line 290
    iput-boolean v2, p0, Lmiui/widget/SlidingButton;->Ln:Z

    .line 291
    invoke-virtual {p0, v2}, Lmiui/widget/SlidingButton;->setPressed(Z)V

    goto :goto_1

    .line 284
    :cond_3
    iget v0, p0, Lmiui/widget/SlidingButton;->Lj:I

    iget v3, p0, Lmiui/widget/SlidingButton;->Li:I

    div-int/lit8 v3, v3, 0x2

    if-lt v0, v3, :cond_4

    move v0, v1

    :goto_4
    invoke-direct {p0, v0}, Lmiui/widget/SlidingButton;->o(Z)V

    goto :goto_3

    :cond_4
    move v0, v2

    goto :goto_4

    .line 287
    :cond_5
    invoke-direct {p0}, Lmiui/widget/SlidingButton;->animateToggle()V

    goto :goto_3

    .line 295
    :pswitch_3
    iput-boolean v2, p0, Lmiui/widget/SlidingButton;->Lm:Z

    .line 296
    iput-boolean v2, p0, Lmiui/widget/SlidingButton;->Ln:Z

    .line 297
    invoke-virtual {p0, v2}, Lmiui/widget/SlidingButton;->setPressed(Z)V

    .line 298
    iget v0, p0, Lmiui/widget/SlidingButton;->Lj:I

    iget v3, p0, Lmiui/widget/SlidingButton;->Li:I

    div-int/lit8 v3, v3, 0x2

    if-lt v0, v3, :cond_6

    move v2, v1

    :cond_6
    invoke-direct {p0, v2}, Lmiui/widget/SlidingButton;->o(Z)V

    goto :goto_1

    .line 255
    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_0
        :pswitch_2
        :pswitch_1
        :pswitch_3
    .end packed-switch
.end method

.method public setButtonDrawable(Landroid/graphics/drawable/Drawable;)V
    .locals 0

    .prologue
    .line 230
    return-void
.end method

.method public setChecked(Z)V
    .locals 4

    .prologue
    const/16 v1, 0xff

    const/4 v2, 0x0

    .line 215
    invoke-virtual {p0}, Lmiui/widget/SlidingButton;->isChecked()Z

    move-result v0

    .line 217
    if-eq v0, p1, :cond_0

    .line 218
    invoke-super {p0, p1}, Landroid/widget/CheckBox;->setChecked(Z)V

    .line 219
    if-eqz p1, :cond_1

    iget v0, p0, Lmiui/widget/SlidingButton;->Li:I

    :goto_0
    iput v0, p0, Lmiui/widget/SlidingButton;->Lj:I

    .line 220
    iget-object v3, p0, Lmiui/widget/SlidingButton;->Lc:Landroid/graphics/Paint;

    if-eqz p1, :cond_2

    move v0, v1

    :goto_1
    invoke-virtual {v3, v0}, Landroid/graphics/Paint;->setAlpha(I)V

    .line 221
    iget-object v3, p0, Lmiui/widget/SlidingButton;->La:Landroid/graphics/Paint;

    if-nez p1, :cond_3

    move v0, v1

    :goto_2
    invoke-virtual {v3, v0}, Landroid/graphics/Paint;->setAlpha(I)V

    .line 222
    if-eqz p1, :cond_4

    :goto_3
    iput v1, p0, Lmiui/widget/SlidingButton;->KV:I

    .line 223
    invoke-virtual {p0}, Lmiui/widget/SlidingButton;->invalidate()V

    .line 225
    :cond_0
    return-void

    .line 219
    :cond_1
    iget v0, p0, Lmiui/widget/SlidingButton;->Lh:I

    goto :goto_0

    :cond_2
    move v0, v2

    .line 220
    goto :goto_1

    :cond_3
    move v0, v2

    .line 221
    goto :goto_2

    :cond_4
    move v1, v2

    .line 222
    goto :goto_3
.end method

.method public setOnPerformCheckedChangeListener(Landroid/widget/CompoundButton$OnCheckedChangeListener;)V
    .locals 0

    .prologue
    .line 202
    iput-object p1, p0, Lmiui/widget/SlidingButton;->Lp:Landroid/widget/CompoundButton$OnCheckedChangeListener;

    .line 203
    return-void
.end method

.method public setPressed(Z)V
    .locals 0

    .prologue
    .line 307
    invoke-super {p0, p1}, Landroid/widget/CheckBox;->setPressed(Z)V

    .line 308
    invoke-virtual {p0}, Lmiui/widget/SlidingButton;->invalidate()V

    .line 309
    return-void
.end method

.method public setSliderOffset(I)V
    .locals 0

    .prologue
    .line 354
    iput p1, p0, Lmiui/widget/SlidingButton;->Lj:I

    .line 355
    invoke-virtual {p0}, Lmiui/widget/SlidingButton;->invalidate()V

    .line 356
    return-void
.end method

.method public setSliderOnAlpha(I)V
    .locals 0

    .prologue
    .line 369
    iput p1, p0, Lmiui/widget/SlidingButton;->KV:I

    .line 370
    invoke-virtual {p0}, Lmiui/widget/SlidingButton;->invalidate()V

    .line 371
    return-void
.end method
