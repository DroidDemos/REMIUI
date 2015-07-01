.class Lmiui/widget/BubbleBgView$a;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/widget/BubbleBgView;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0xa
    name = "a"
.end annotation


# static fields
.field private static final GJ:Ljava/util/Random;

.field private static final Gt:I = 0xbb8

.field private static final Gu:I = 0x7d0


# instance fields
.field private final GC:I

.field private final Gv:I

.field private final Gw:I

.field private final Gx:I

.field private final Gy:I

.field private final Gz:I

.field private final color:I

.field private final radius:I

.field private ux:Landroid/animation/AnimatorSet;


# direct methods
.method static constructor <clinit>()V
    .locals 3

    .prologue
    .line 124
    new-instance v0, Ljava/util/Random;

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v1

    invoke-direct {v0, v1, v2}, Ljava/util/Random;-><init>(J)V

    sput-object v0, Lmiui/widget/BubbleBgView$a;->GJ:Ljava/util/Random;

    return-void
.end method

.method public constructor <init>(IIIIIIII)V
    .locals 1

    .prologue
    .line 126
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 127
    iput p1, p0, Lmiui/widget/BubbleBgView$a;->Gv:I

    .line 128
    iput p2, p0, Lmiui/widget/BubbleBgView$a;->Gw:I

    .line 129
    iput p3, p0, Lmiui/widget/BubbleBgView$a;->radius:I

    .line 130
    add-int v0, p4, p1

    iput v0, p0, Lmiui/widget/BubbleBgView$a;->Gy:I

    .line 131
    add-int v0, p5, p1

    iput v0, p0, Lmiui/widget/BubbleBgView$a;->Gx:I

    .line 132
    add-int v0, p6, p2

    iput v0, p0, Lmiui/widget/BubbleBgView$a;->GC:I

    .line 133
    add-int v0, p7, p2

    iput v0, p0, Lmiui/widget/BubbleBgView$a;->Gz:I

    .line 134
    iput p8, p0, Lmiui/widget/BubbleBgView$a;->color:I

    .line 135
    invoke-direct {p0}, Lmiui/widget/BubbleBgView$a;->dz()V

    .line 136
    return-void
.end method

.method static synthetic a(Lmiui/widget/BubbleBgView$a;)I
    .locals 1

    .prologue
    .line 112
    iget v0, p0, Lmiui/widget/BubbleBgView$a;->color:I

    return v0
.end method

.method static synthetic b(Lmiui/widget/BubbleBgView$a;)F
    .locals 1

    .prologue
    .line 112
    invoke-direct {p0}, Lmiui/widget/BubbleBgView$a;->dA()F

    move-result v0

    return v0
.end method

.method static synthetic c(Lmiui/widget/BubbleBgView$a;)F
    .locals 1

    .prologue
    .line 112
    invoke-direct {p0}, Lmiui/widget/BubbleBgView$a;->dB()F

    move-result v0

    return v0
.end method

.method static synthetic d(Lmiui/widget/BubbleBgView$a;)I
    .locals 1

    .prologue
    .line 112
    iget v0, p0, Lmiui/widget/BubbleBgView$a;->radius:I

    return v0
.end method

.method private dA()F
    .locals 2

    .prologue
    .line 174
    iget-object v0, p0, Lmiui/widget/BubbleBgView$a;->ux:Landroid/animation/AnimatorSet;

    if-nez v0, :cond_0

    .line 175
    iget v0, p0, Lmiui/widget/BubbleBgView$a;->Gv:I

    int-to-float v0, v0

    .line 177
    :goto_0
    return v0

    :cond_0
    iget-object v0, p0, Lmiui/widget/BubbleBgView$a;->ux:Landroid/animation/AnimatorSet;

    invoke-virtual {v0}, Landroid/animation/AnimatorSet;->getChildAnimations()Ljava/util/ArrayList;

    move-result-object v0

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/animation/ValueAnimator;

    invoke-virtual {v0}, Landroid/animation/ValueAnimator;->getAnimatedValue()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/Float;

    invoke-virtual {v0}, Ljava/lang/Float;->floatValue()F

    move-result v0

    goto :goto_0
.end method

.method private dB()F
    .locals 2

    .prologue
    .line 181
    iget-object v0, p0, Lmiui/widget/BubbleBgView$a;->ux:Landroid/animation/AnimatorSet;

    if-nez v0, :cond_0

    .line 182
    iget v0, p0, Lmiui/widget/BubbleBgView$a;->Gw:I

    int-to-float v0, v0

    .line 184
    :goto_0
    return v0

    :cond_0
    iget-object v0, p0, Lmiui/widget/BubbleBgView$a;->ux:Landroid/animation/AnimatorSet;

    invoke-virtual {v0}, Landroid/animation/AnimatorSet;->getChildAnimations()Ljava/util/ArrayList;

    move-result-object v0

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/animation/ValueAnimator;

    invoke-virtual {v0}, Landroid/animation/ValueAnimator;->getAnimatedValue()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/Float;

    invoke-virtual {v0}, Ljava/lang/Float;->floatValue()F

    move-result v0

    goto :goto_0
.end method

.method private dz()V
    .locals 9

    .prologue
    const/4 v8, 0x3

    const/4 v7, 0x2

    const/4 v6, 0x1

    const/4 v5, 0x0

    .line 139
    sget-object v0, Lmiui/widget/BubbleBgView$a;->GJ:Ljava/util/Random;

    const/16 v1, 0x3e8

    invoke-virtual {v0, v1}, Ljava/util/Random;->nextInt(I)I

    move-result v0

    add-int/lit16 v0, v0, 0x7d0

    .line 140
    new-instance v1, Landroid/animation/AnimatorSet;

    invoke-direct {v1}, Landroid/animation/AnimatorSet;-><init>()V

    iput-object v1, p0, Lmiui/widget/BubbleBgView$a;->ux:Landroid/animation/AnimatorSet;

    .line 141
    new-instance v1, Landroid/animation/ValueAnimator;

    invoke-direct {v1}, Landroid/animation/ValueAnimator;-><init>()V

    .line 142
    int-to-long v2, v0

    invoke-virtual {v1, v2, v3}, Landroid/animation/ValueAnimator;->setDuration(J)Landroid/animation/ValueAnimator;

    .line 143
    new-instance v2, Landroid/animation/ValueAnimator;

    invoke-direct {v2}, Landroid/animation/ValueAnimator;-><init>()V

    .line 144
    int-to-long v3, v0

    invoke-virtual {v2, v3, v4}, Landroid/animation/ValueAnimator;->setDuration(J)Landroid/animation/ValueAnimator;

    .line 145
    iget-object v0, p0, Lmiui/widget/BubbleBgView$a;->ux:Landroid/animation/AnimatorSet;

    new-array v3, v7, [Landroid/animation/Animator;

    aput-object v1, v3, v5

    aput-object v2, v3, v6

    invoke-virtual {v0, v3}, Landroid/animation/AnimatorSet;->playTogether([Landroid/animation/Animator;)V

    .line 146
    iget-object v0, p0, Lmiui/widget/BubbleBgView$a;->ux:Landroid/animation/AnimatorSet;

    new-instance v1, Lmiui/widget/BubbleBgView$a$1;

    invoke-direct {v1, p0}, Lmiui/widget/BubbleBgView$a$1;-><init>(Lmiui/widget/BubbleBgView$a;)V

    invoke-virtual {v0, v1}, Landroid/animation/AnimatorSet;->addListener(Landroid/animation/Animator$AnimatorListener;)V

    .line 165
    iget-object v0, p0, Lmiui/widget/BubbleBgView$a;->ux:Landroid/animation/AnimatorSet;

    invoke-virtual {v0}, Landroid/animation/AnimatorSet;->cancel()V

    .line 166
    sget-object v0, Lmiui/widget/BubbleBgView$a;->GJ:Ljava/util/Random;

    iget v1, p0, Lmiui/widget/BubbleBgView$a;->Gx:I

    iget v2, p0, Lmiui/widget/BubbleBgView$a;->Gy:I

    sub-int/2addr v1, v2

    invoke-virtual {v0, v1}, Ljava/util/Random;->nextInt(I)I

    move-result v0

    iget v1, p0, Lmiui/widget/BubbleBgView$a;->Gy:I

    add-int/2addr v1, v0

    .line 167
    sget-object v0, Lmiui/widget/BubbleBgView$a;->GJ:Ljava/util/Random;

    iget v2, p0, Lmiui/widget/BubbleBgView$a;->Gz:I

    iget v3, p0, Lmiui/widget/BubbleBgView$a;->GC:I

    sub-int/2addr v2, v3

    invoke-virtual {v0, v2}, Ljava/util/Random;->nextInt(I)I

    move-result v0

    iget v2, p0, Lmiui/widget/BubbleBgView$a;->GC:I

    add-int/2addr v2, v0

    .line 168
    iget-object v0, p0, Lmiui/widget/BubbleBgView$a;->ux:Landroid/animation/AnimatorSet;

    invoke-virtual {v0}, Landroid/animation/AnimatorSet;->getChildAnimations()Ljava/util/ArrayList;

    move-result-object v0

    invoke-virtual {v0, v5}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/animation/ValueAnimator;

    new-array v3, v8, [F

    iget v4, p0, Lmiui/widget/BubbleBgView$a;->Gv:I

    int-to-float v4, v4

    aput v4, v3, v5

    int-to-float v1, v1

    aput v1, v3, v6

    iget v1, p0, Lmiui/widget/BubbleBgView$a;->Gv:I

    int-to-float v1, v1

    aput v1, v3, v7

    invoke-virtual {v0, v3}, Landroid/animation/ValueAnimator;->setFloatValues([F)V

    .line 169
    iget-object v0, p0, Lmiui/widget/BubbleBgView$a;->ux:Landroid/animation/AnimatorSet;

    invoke-virtual {v0}, Landroid/animation/AnimatorSet;->getChildAnimations()Ljava/util/ArrayList;

    move-result-object v0

    invoke-virtual {v0, v6}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/animation/ValueAnimator;

    new-array v1, v8, [F

    iget v3, p0, Lmiui/widget/BubbleBgView$a;->Gw:I

    int-to-float v3, v3

    aput v3, v1, v5

    int-to-float v2, v2

    aput v2, v1, v6

    iget v2, p0, Lmiui/widget/BubbleBgView$a;->Gw:I

    int-to-float v2, v2

    aput v2, v1, v7

    invoke-virtual {v0, v1}, Landroid/animation/ValueAnimator;->setFloatValues([F)V

    .line 170
    iget-object v0, p0, Lmiui/widget/BubbleBgView$a;->ux:Landroid/animation/AnimatorSet;

    invoke-virtual {v0}, Landroid/animation/AnimatorSet;->start()V

    .line 171
    return-void
.end method

.method static synthetic e(Lmiui/widget/BubbleBgView$a;)V
    .locals 0

    .prologue
    .line 112
    invoke-direct {p0}, Lmiui/widget/BubbleBgView$a;->dz()V

    return-void
.end method
