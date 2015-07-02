.class public Lmiui/widget/ScreenView;
.super Landroid/view/ViewGroup;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lmiui/widget/ScreenView$h;,
        Lmiui/widget/ScreenView$f;,
        Lmiui/widget/ScreenView$d;,
        Lmiui/widget/ScreenView$b;,
        Lmiui/widget/ScreenView$g;,
        Lmiui/widget/ScreenView$i;,
        Lmiui/widget/ScreenView$a;,
        Lmiui/widget/ScreenView$e;,
        Lmiui/widget/ScreenView$c;,
        Lmiui/widget/ScreenView$SnapScreenOnceNotification;
    }
.end annotation


# static fields
.field public static final FLING_ALIGN:I = 0x4

.field public static final FLING_CANCEL:I = 0x3

.field public static final FLING_LEFT:I = 0x1

.field public static final FLING_RIGHT:I = 0x2

.field private static final INVALID_POINTER:I = -0x1

.field public static final SCREEN_ALIGN_CENTER:I = 0x2

.field public static final SCREEN_ALIGN_CUSTOMIZED:I = 0x0

.field public static final SCREEN_ALIGN_LEFT:I = 0x1

.field public static final SCREEN_ALIGN_RIGHT:I = 0x3

.field public static final SCREEN_TRANSITION_TYPE_CLASSIC:I = 0x0

.field public static final SCREEN_TRANSITION_TYPE_CLASSIC_NO_OVER_SHOOT:I = 0x1

.field public static final SCREEN_TRANSITION_TYPE_CROSSFADE:I = 0x2

.field public static final SCREEN_TRANSITION_TYPE_CUBE:I = 0x4

.field public static final SCREEN_TRANSITION_TYPE_CUSTOM:I = 0x9

.field public static final SCREEN_TRANSITION_TYPE_FALLDOWN:I = 0x3

.field public static final SCREEN_TRANSITION_TYPE_LEFTPAGE:I = 0x5

.field public static final SCREEN_TRANSITION_TYPE_RIGHTPAGE:I = 0x6

.field public static final SCREEN_TRANSITION_TYPE_ROTATE:I = 0x8

.field public static final SCREEN_TRANSITION_TYPE_STACK:I = 0x7

.field private static final TAG:Ljava/lang/String; = "ScreenView"

.field protected static final TOUCH_STATE_PINCHING:I = 0x4

.field protected static final TOUCH_STATE_REST:I = 0x0

.field protected static final TOUCH_STATE_SCROLLING:I = 0x1

.field protected static final TOUCH_STATE_SLIDING:I = 0x3

.field private static final bX:Lcom/miui/internal/variable/Android_View_View_class;

.field private static final bY:I = -0x1

.field private static final bZ:I = 0x30

.field private static final ca:Landroid/widget/LinearLayout$LayoutParams;

.field private static final cb:I = 0x3e8

.field private static final cc:I = 0x1f4

.field private static final cd:I = 0x12c

.field private static final ce:F = 1.0E9f

.field private static final cf:F = 0.75f

.field private static final cg:F

.field private static final ch:I = 0x12c

.field private static final ci:F = 2500.0f

.field private static final cj:F = 0.4f

.field private static final ck:F = 1.3f


# instance fields
.field private cA:I

.field private cB:I

.field private cC:I

.field private cD:I

.field private cE:I

.field private cF:I

.field private cG:I

.field private cH:I

.field private cI:I

.field private cJ:F

.field private cK:Z

.field private cL:Landroid/widget/Scroller;

.field private cM:F

.field private cN:I

.field private cO:Landroid/view/ScaleGestureDetector;

.field private cP:I

.field private cQ:Z

.field private cR:Z

.field private cS:Z

.field private cT:I

.field private cU:I

.field private cV:I

.field private cW:F

.field private cX:F

.field private cY:F

.field private cZ:I

.field private final cl:F

.field private cm:Z

.field private cn:I

.field private co:I

.field private cp:I

.field private cq:I

.field private cr:I

.field private cs:I

.field private ct:Lmiui/widget/ScreenView$g;

.field private cu:Lmiui/widget/ScreenView$g;

.field private cv:Lmiui/widget/ScreenView$b;

.field private cw:Lmiui/widget/ScreenView$d;

.field private cx:Z

.field private cy:Ljava/lang/Runnable;

.field private cz:I

.field private da:Lmiui/widget/ScreenView$i;

.field private db:I

.field private dc:F

.field private dd:Lmiui/widget/ScreenView$SnapScreenOnceNotification;

.field private de:Lmiui/widget/ScreenView$a;

.field protected mChildScreenWidth:I

.field protected mCurrentScreen:I

.field protected mLastMotionX:F

.field protected mLastMotionY:F

.field protected mScreenOffset:I

.field protected mVisibleRange:I


# direct methods
.method static constructor <clinit>()V
    .locals 4

    .prologue
    const/4 v2, -0x1

    .line 58
    invoke-static {}, Lcom/miui/internal/variable/Android_View_View_class$Factory;->getInstance()Lcom/miui/internal/variable/Android_View_View_class$Factory;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/internal/variable/Android_View_View_class$Factory;->get()Lcom/miui/internal/variable/Android_View_View_class;

    move-result-object v0

    sput-object v0, Lmiui/widget/ScreenView;->bX:Lcom/miui/internal/variable/Android_View_View_class;

    .line 161
    new-instance v0, Landroid/widget/LinearLayout$LayoutParams;

    const/high16 v1, 0x3f800000

    invoke-direct {v0, v2, v2, v1}, Landroid/widget/LinearLayout$LayoutParams;-><init>(IIF)V

    sput-object v0, Lmiui/widget/ScreenView;->ca:Landroid/widget/LinearLayout$LayoutParams;

    .line 190
    const-wide v0, 0x3f90624dd2f1a9fcL

    const-wide/high16 v2, 0x3fe8000000000000L

    invoke-static {v2, v3}, Ljava/lang/Math;->log(D)D

    move-result-wide v2

    div-double/2addr v0, v2

    double-to-float v0, v0

    sput v0, Lmiui/widget/ScreenView;->cg:F

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;)V
    .locals 6

    .prologue
    const/4 v5, -0x1

    const/high16 v4, 0x3f000000

    const/4 v3, 0x1

    const/4 v2, 0x0

    .line 331
    invoke-direct {p0, p1}, Landroid/view/ViewGroup;-><init>(Landroid/content/Context;)V

    .line 201
    invoke-static {}, Landroid/content/res/Resources;->getSystem()Landroid/content/res/Resources;

    move-result-object v0

    invoke-virtual {v0}, Landroid/content/res/Resources;->getDisplayMetrics()Landroid/util/DisplayMetrics;

    move-result-object v0

    iget v0, v0, Landroid/util/DisplayMetrics;->density:F

    const/high16 v1, 0x44a00000

    mul-float/2addr v0, v1

    iput v0, p0, Lmiui/widget/ScreenView;->cl:F

    .line 207
    iput-boolean v3, p0, Lmiui/widget/ScreenView;->cm:Z

    .line 211
    sget v0, Lcom/miui/internal/R$drawable;->screen_view_arrow_left:I

    iput v0, p0, Lmiui/widget/ScreenView;->co:I

    .line 213
    sget v0, Lcom/miui/internal/R$drawable;->screen_view_arrow_left_gray:I

    iput v0, p0, Lmiui/widget/ScreenView;->cp:I

    .line 215
    sget v0, Lcom/miui/internal/R$drawable;->screen_view_arrow_right:I

    iput v0, p0, Lmiui/widget/ScreenView;->cq:I

    .line 217
    sget v0, Lcom/miui/internal/R$drawable;->screen_view_arrow_right_gray:I

    iput v0, p0, Lmiui/widget/ScreenView;->cr:I

    .line 219
    sget v0, Lcom/miui/internal/R$drawable;->screen_view_seek_point_selector:I

    iput v0, p0, Lmiui/widget/ScreenView;->cs:I

    .line 237
    new-instance v0, Lmiui/widget/n;

    invoke-direct {v0, p0}, Lmiui/widget/n;-><init>(Lmiui/widget/ScreenView;)V

    iput-object v0, p0, Lmiui/widget/ScreenView;->cy:Ljava/lang/Runnable;

    .line 255
    iput v3, p0, Lmiui/widget/ScreenView;->mVisibleRange:I

    .line 260
    iput v2, p0, Lmiui/widget/ScreenView;->cD:I

    .line 269
    iput v5, p0, Lmiui/widget/ScreenView;->cG:I

    .line 275
    const v0, 0x3eaaaaab

    iput v0, p0, Lmiui/widget/ScreenView;->cJ:F

    .line 281
    iput v4, p0, Lmiui/widget/ScreenView;->cM:F

    .line 283
    iput v2, p0, Lmiui/widget/ScreenView;->cN:I

    .line 292
    iput v2, p0, Lmiui/widget/ScreenView;->cP:I

    .line 298
    iput-boolean v3, p0, Lmiui/widget/ScreenView;->cS:Z

    .line 304
    iput v5, p0, Lmiui/widget/ScreenView;->cV:I

    .line 310
    iput v4, p0, Lmiui/widget/ScreenView;->cY:F

    .line 312
    const/16 v0, 0x12c

    iput v0, p0, Lmiui/widget/ScreenView;->cZ:I

    .line 316
    iput v2, p0, Lmiui/widget/ScreenView;->db:I

    .line 318
    const v0, 0x3fa66666

    iput v0, p0, Lmiui/widget/ScreenView;->dc:F

    .line 322
    new-instance v0, Lmiui/widget/ScreenView$a;

    const/4 v1, 0x0

    invoke-direct {v0, p0, v1}, Lmiui/widget/ScreenView$a;-><init>(Lmiui/widget/ScreenView;Lmiui/widget/n;)V

    iput-object v0, p0, Lmiui/widget/ScreenView;->de:Lmiui/widget/ScreenView$a;

    .line 332
    invoke-direct {p0}, Lmiui/widget/ScreenView;->t()V

    .line 333
    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 1

    .prologue
    .line 343
    const/4 v0, 0x0

    invoke-direct {p0, p1, p2, v0}, Lmiui/widget/ScreenView;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    .line 344
    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V
    .locals 6

    .prologue
    const/4 v5, -0x1

    const/high16 v4, 0x3f000000

    const/4 v3, 0x1

    const/4 v2, 0x0

    .line 355
    invoke-direct {p0, p1, p2, p3}, Landroid/view/ViewGroup;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    .line 201
    invoke-static {}, Landroid/content/res/Resources;->getSystem()Landroid/content/res/Resources;

    move-result-object v0

    invoke-virtual {v0}, Landroid/content/res/Resources;->getDisplayMetrics()Landroid/util/DisplayMetrics;

    move-result-object v0

    iget v0, v0, Landroid/util/DisplayMetrics;->density:F

    const/high16 v1, 0x44a00000

    mul-float/2addr v0, v1

    iput v0, p0, Lmiui/widget/ScreenView;->cl:F

    .line 207
    iput-boolean v3, p0, Lmiui/widget/ScreenView;->cm:Z

    .line 211
    sget v0, Lcom/miui/internal/R$drawable;->screen_view_arrow_left:I

    iput v0, p0, Lmiui/widget/ScreenView;->co:I

    .line 213
    sget v0, Lcom/miui/internal/R$drawable;->screen_view_arrow_left_gray:I

    iput v0, p0, Lmiui/widget/ScreenView;->cp:I

    .line 215
    sget v0, Lcom/miui/internal/R$drawable;->screen_view_arrow_right:I

    iput v0, p0, Lmiui/widget/ScreenView;->cq:I

    .line 217
    sget v0, Lcom/miui/internal/R$drawable;->screen_view_arrow_right_gray:I

    iput v0, p0, Lmiui/widget/ScreenView;->cr:I

    .line 219
    sget v0, Lcom/miui/internal/R$drawable;->screen_view_seek_point_selector:I

    iput v0, p0, Lmiui/widget/ScreenView;->cs:I

    .line 237
    new-instance v0, Lmiui/widget/n;

    invoke-direct {v0, p0}, Lmiui/widget/n;-><init>(Lmiui/widget/ScreenView;)V

    iput-object v0, p0, Lmiui/widget/ScreenView;->cy:Ljava/lang/Runnable;

    .line 255
    iput v3, p0, Lmiui/widget/ScreenView;->mVisibleRange:I

    .line 260
    iput v2, p0, Lmiui/widget/ScreenView;->cD:I

    .line 269
    iput v5, p0, Lmiui/widget/ScreenView;->cG:I

    .line 275
    const v0, 0x3eaaaaab

    iput v0, p0, Lmiui/widget/ScreenView;->cJ:F

    .line 281
    iput v4, p0, Lmiui/widget/ScreenView;->cM:F

    .line 283
    iput v2, p0, Lmiui/widget/ScreenView;->cN:I

    .line 292
    iput v2, p0, Lmiui/widget/ScreenView;->cP:I

    .line 298
    iput-boolean v3, p0, Lmiui/widget/ScreenView;->cS:Z

    .line 304
    iput v5, p0, Lmiui/widget/ScreenView;->cV:I

    .line 310
    iput v4, p0, Lmiui/widget/ScreenView;->cY:F

    .line 312
    const/16 v0, 0x12c

    iput v0, p0, Lmiui/widget/ScreenView;->cZ:I

    .line 316
    iput v2, p0, Lmiui/widget/ScreenView;->db:I

    .line 318
    const v0, 0x3fa66666

    iput v0, p0, Lmiui/widget/ScreenView;->dc:F

    .line 322
    new-instance v0, Lmiui/widget/ScreenView$a;

    const/4 v1, 0x0

    invoke-direct {v0, p0, v1}, Lmiui/widget/ScreenView$a;-><init>(Lmiui/widget/ScreenView;Lmiui/widget/n;)V

    iput-object v0, p0, Lmiui/widget/ScreenView;->de:Lmiui/widget/ScreenView$a;

    .line 356
    invoke-direct {p0}, Lmiui/widget/ScreenView;->t()V

    .line 357
    return-void
.end method

.method private a(Landroid/view/MotionEvent;I)V
    .locals 4

    .prologue
    const/4 v1, 0x1

    const/4 v2, 0x0

    .line 1029
    iput p2, p0, Lmiui/widget/ScreenView;->cP:I

    .line 1030
    invoke-virtual {p0}, Lmiui/widget/ScreenView;->getParent()Landroid/view/ViewParent;

    move-result-object v3

    iget v0, p0, Lmiui/widget/ScreenView;->cP:I

    if-eqz v0, :cond_1

    move v0, v1

    :goto_0
    invoke-interface {v3, v0}, Landroid/view/ViewParent;->requestDisallowInterceptTouchEvent(Z)V

    .line 1032
    iget v0, p0, Lmiui/widget/ScreenView;->cP:I

    if-nez v0, :cond_2

    .line 1033
    const/4 v0, -0x1

    iput v0, p0, Lmiui/widget/ScreenView;->cV:I

    .line 1034
    iput-boolean v2, p0, Lmiui/widget/ScreenView;->cS:Z

    .line 1035
    iget-object v0, p0, Lmiui/widget/ScreenView;->de:Lmiui/widget/ScreenView$a;

    invoke-virtual {v0}, Lmiui/widget/ScreenView$a;->recycle()V

    .line 1057
    :cond_0
    :goto_1
    invoke-direct {p0}, Lmiui/widget/ScreenView;->u()V

    .line 1058
    return-void

    :cond_1
    move v0, v2

    .line 1030
    goto :goto_0

    .line 1037
    :cond_2
    if-eqz p1, :cond_3

    .line 1038
    invoke-virtual {p1, v2}, Landroid/view/MotionEvent;->getPointerId(I)I

    move-result v0

    iput v0, p0, Lmiui/widget/ScreenView;->cV:I

    .line 1039
    iget v0, p0, Lmiui/widget/ScreenView;->cV:I

    invoke-virtual {p1, v0}, Landroid/view/MotionEvent;->findPointerIndex(I)I

    move-result v0

    invoke-virtual {p1, v0}, Landroid/view/MotionEvent;->getX(I)F

    move-result v0

    iput v0, p0, Lmiui/widget/ScreenView;->mLastMotionX:F

    .line 1042
    :cond_3
    iget-boolean v0, p0, Lmiui/widget/ScreenView;->cS:Z

    if-eqz v0, :cond_4

    .line 1043
    iput-boolean v2, p0, Lmiui/widget/ScreenView;->cS:Z

    .line 1047
    iget v0, p0, Lmiui/widget/ScreenView;->mCurrentScreen:I

    invoke-virtual {p0, v0}, Lmiui/widget/ScreenView;->getChildAt(I)Landroid/view/View;

    move-result-object v0

    .line 1048
    if-eqz v0, :cond_4

    .line 1049
    invoke-virtual {v0}, Landroid/view/View;->cancelLongPress()V

    .line 1052
    :cond_4
    iget v0, p0, Lmiui/widget/ScreenView;->cP:I

    if-ne v0, v1, :cond_0

    .line 1053
    invoke-virtual {p0}, Lmiui/widget/ScreenView;->getScrollX()I

    move-result v0

    int-to-float v0, v0

    iput v0, p0, Lmiui/widget/ScreenView;->cX:F

    .line 1054
    invoke-static {}, Ljava/lang/System;->nanoTime()J

    move-result-wide v0

    long-to-float v0, v0

    const v1, 0x4e6e6b28

    div-float/2addr v0, v1

    iput v0, p0, Lmiui/widget/ScreenView;->cW:F

    goto :goto_1
.end method

.method static synthetic a(Lmiui/widget/ScreenView;)V
    .locals 0

    .prologue
    .line 56
    invoke-direct {p0}, Lmiui/widget/ScreenView;->v()V

    return-void
.end method

.method static synthetic a(Lmiui/widget/ScreenView;I)V
    .locals 0

    .prologue
    .line 56
    invoke-direct {p0, p1}, Lmiui/widget/ScreenView;->r(I)V

    return-void
.end method

.method static synthetic a(Lmiui/widget/ScreenView;II)V
    .locals 0

    .prologue
    .line 56
    invoke-direct {p0, p1, p2}, Lmiui/widget/ScreenView;->c(II)V

    return-void
.end method

.method static synthetic a(Lmiui/widget/ScreenView;Landroid/view/MotionEvent;I)V
    .locals 0

    .prologue
    .line 56
    invoke-direct {p0, p1, p2}, Lmiui/widget/ScreenView;->a(Landroid/view/MotionEvent;I)V

    return-void
.end method

.method private a(Landroid/view/MotionEvent;)Z
    .locals 4

    .prologue
    const/4 v0, 0x0

    .line 1107
    invoke-virtual {p1, v0}, Landroid/view/MotionEvent;->getX(I)F

    move-result v1

    iget v2, p0, Lmiui/widget/ScreenView;->mLastMotionX:F

    sub-float/2addr v1, v2

    invoke-static {v1}, Ljava/lang/Math;->abs(F)F

    move-result v1

    .line 1108
    invoke-virtual {p1, v0}, Landroid/view/MotionEvent;->getY(I)F

    move-result v2

    iget v3, p0, Lmiui/widget/ScreenView;->mLastMotionY:F

    sub-float/2addr v2, v3

    invoke-static {v2}, Ljava/lang/Math;->abs(F)F

    move-result v2

    .line 1109
    iget v3, p0, Lmiui/widget/ScreenView;->cY:F

    mul-float/2addr v2, v3

    cmpl-float v2, v1, v2

    if-lez v2, :cond_0

    iget v2, p0, Lmiui/widget/ScreenView;->cT:I

    invoke-virtual {p1}, Landroid/view/MotionEvent;->getPointerCount()I

    move-result v3

    mul-int/2addr v2, v3

    int-to-float v2, v2

    cmpl-float v1, v1, v2

    if-lez v1, :cond_0

    const/4 v0, 0x1

    :cond_0
    return v0
.end method

.method static synthetic b(Lmiui/widget/ScreenView;)Lmiui/widget/ScreenView$d;
    .locals 1

    .prologue
    .line 56
    iget-object v0, p0, Lmiui/widget/ScreenView;->cw:Lmiui/widget/ScreenView$d;

    return-object v0
.end method

.method private b(IZ)V
    .locals 12

    .prologue
    const/4 v2, 0x0

    .line 693
    invoke-virtual {p0}, Lmiui/widget/ScreenView;->getWidth()I

    move-result v0

    if-lez v0, :cond_1

    .line 694
    invoke-virtual {p0}, Lmiui/widget/ScreenView;->getScreenCount()I

    move-result v4

    .line 695
    invoke-virtual {p0}, Lmiui/widget/ScreenView;->getWidth()I

    move-result v5

    .line 696
    invoke-virtual {p0}, Lmiui/widget/ScreenView;->getHeight()I

    move-result v6

    move v1, v2

    .line 697
    :goto_0
    iget v0, p0, Lmiui/widget/ScreenView;->cn:I

    if-ge v1, v0, :cond_1

    .line 698
    add-int v0, v1, v4

    invoke-virtual {p0, v0}, Lmiui/widget/ScreenView;->getChildAt(I)Landroid/view/View;

    move-result-object v7

    .line 699
    invoke-virtual {v7}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v0

    check-cast v0, Landroid/widget/FrameLayout$LayoutParams;

    .line 702
    invoke-virtual {v7}, Landroid/view/View;->getMeasuredWidth()I

    move-result v8

    .line 703
    invoke-virtual {v7}, Landroid/view/View;->getMeasuredHeight()I

    move-result v9

    .line 706
    iget v3, v0, Landroid/widget/FrameLayout$LayoutParams;->gravity:I

    .line 707
    const/4 v10, -0x1

    if-eq v3, v10, :cond_2

    .line 708
    and-int/lit8 v10, v3, 0x7

    .line 710
    and-int/lit8 v11, v3, 0x70

    .line 712
    packed-switch v10, :pswitch_data_0

    .line 725
    :pswitch_0
    iget v3, v0, Landroid/widget/FrameLayout$LayoutParams;->leftMargin:I

    .line 727
    :goto_1
    sparse-switch v11, :sswitch_data_0

    .line 740
    iget v0, v0, Landroid/widget/FrameLayout$LayoutParams;->topMargin:I

    .line 743
    :goto_2
    if-nez p2, :cond_0

    invoke-virtual {v7}, Landroid/view/View;->getHeight()I

    move-result v10

    if-lez v10, :cond_0

    invoke-virtual {v7}, Landroid/view/View;->getWidth()I

    move-result v10

    if-lez v10, :cond_0

    .line 744
    int-to-float v0, p1

    invoke-virtual {v7, v0}, Landroid/view/View;->setTranslationX(F)V

    .line 697
    :goto_3
    add-int/lit8 v0, v1, 0x1

    move v1, v0

    goto :goto_0

    .line 714
    :pswitch_1
    iget v3, v0, Landroid/widget/FrameLayout$LayoutParams;->leftMargin:I

    goto :goto_1

    .line 717
    :pswitch_2
    sub-int v3, v5, v8

    div-int/lit8 v3, v3, 0x2

    iget v10, v0, Landroid/widget/FrameLayout$LayoutParams;->leftMargin:I

    add-int/2addr v3, v10

    iget v10, v0, Landroid/widget/FrameLayout$LayoutParams;->rightMargin:I

    sub-int/2addr v3, v10

    .line 719
    goto :goto_1

    .line 721
    :pswitch_3
    sub-int v3, v5, v8

    iget v10, v0, Landroid/widget/FrameLayout$LayoutParams;->rightMargin:I

    sub-int/2addr v3, v10

    .line 723
    goto :goto_1

    .line 729
    :sswitch_0
    iget v0, v0, Landroid/widget/FrameLayout$LayoutParams;->topMargin:I

    goto :goto_2

    .line 732
    :sswitch_1
    sub-int v10, v6, v9

    div-int/lit8 v10, v10, 0x2

    iget v11, v0, Landroid/widget/FrameLayout$LayoutParams;->topMargin:I

    add-int/2addr v10, v11

    iget v0, v0, Landroid/widget/FrameLayout$LayoutParams;->bottomMargin:I

    sub-int v0, v10, v0

    .line 734
    goto :goto_2

    .line 736
    :sswitch_2
    sub-int v10, v6, v9

    iget v0, v0, Landroid/widget/FrameLayout$LayoutParams;->bottomMargin:I

    sub-int v0, v10, v0

    .line 738
    goto :goto_2

    .line 746
    :cond_0
    add-int/2addr v8, v3

    add-int/2addr v9, v0

    invoke-virtual {v7, v3, v0, v8, v9}, Landroid/view/View;->layout(IIII)V

    goto :goto_3

    .line 750
    :cond_1
    return-void

    :cond_2
    move v0, v2

    move v3, v2

    goto :goto_2

    .line 712
    nop

    :pswitch_data_0
    .packed-switch 0x1
        :pswitch_2
        :pswitch_0
        :pswitch_1
        :pswitch_0
        :pswitch_3
    .end packed-switch

    .line 727
    :sswitch_data_0
    .sparse-switch
        0x10 -> :sswitch_1
        0x30 -> :sswitch_0
        0x50 -> :sswitch_2
    .end sparse-switch
.end method

.method private b(Landroid/view/MotionEvent;)V
    .locals 2

    .prologue
    .line 1114
    iget-object v0, p0, Lmiui/widget/ScreenView;->de:Lmiui/widget/ScreenView$a;

    invoke-virtual {v0, p1}, Lmiui/widget/ScreenView$a;->addMovement(Landroid/view/MotionEvent;)V

    .line 1115
    iget v0, p0, Lmiui/widget/ScreenView;->cP:I

    if-eqz v0, :cond_0

    const/4 v0, 0x4

    iget v1, p0, Lmiui/widget/ScreenView;->cP:I

    if-ne v0, v1, :cond_1

    .line 1117
    :cond_0
    iget-object v0, p0, Lmiui/widget/ScreenView;->cO:Landroid/view/ScaleGestureDetector;

    invoke-virtual {v0, p1}, Landroid/view/ScaleGestureDetector;->onTouchEvent(Landroid/view/MotionEvent;)Z

    .line 1119
    :cond_1
    return-void
.end method

.method private b(Landroid/view/View;)V
    .locals 10

    .prologue
    const v9, 0x459c4000

    const/high16 v5, 0x40000000

    const v8, 0x3e99999a

    const/high16 v7, 0x3f800000

    const/4 v1, 0x0

    .line 1741
    instance-of v0, p1, Lmiui/widget/ScreenView$c;

    if-eqz v0, :cond_0

    .line 1870
    :goto_0
    :pswitch_0
    return-void

    .line 1744
    :cond_0
    invoke-virtual {p1}, Landroid/view/View;->getMeasuredWidth()I

    move-result v0

    int-to-float v0, v0

    .line 1745
    invoke-virtual {p1}, Landroid/view/View;->getMeasuredHeight()I

    move-result v2

    int-to-float v2, v2

    .line 1746
    invoke-virtual {p0}, Lmiui/widget/ScreenView;->getMeasuredWidth()I

    move-result v3

    int-to-float v3, v3

    div-float/2addr v3, v5

    .line 1747
    div-float v4, v0, v5

    .line 1748
    div-float v5, v2, v5

    .line 1749
    invoke-virtual {p0}, Lmiui/widget/ScreenView;->getScrollX()I

    move-result v6

    int-to-float v6, v6

    add-float/2addr v3, v6

    invoke-virtual {p1}, Landroid/view/View;->getLeft()I

    move-result v6

    int-to-float v6, v6

    sub-float/2addr v3, v6

    sub-float/2addr v3, v4

    div-float/2addr v3, v0

    .line 1752
    iget v6, p0, Lmiui/widget/ScreenView;->db:I

    packed-switch v6, :pswitch_data_0

    goto :goto_0

    .line 1754
    :pswitch_1
    invoke-virtual {p0, p1}, Lmiui/widget/ScreenView;->resetTransformation(Landroid/view/View;)V

    goto :goto_0

    .line 1757
    :pswitch_2
    invoke-virtual {p0, p1}, Lmiui/widget/ScreenView;->resetTransformation(Landroid/view/View;)V

    goto :goto_0

    .line 1760
    :pswitch_3
    cmpl-float v0, v3, v1

    if-eqz v0, :cond_1

    invoke-static {v3}, Ljava/lang/Math;->abs(F)F

    move-result v0

    cmpl-float v0, v0, v7

    if-lez v0, :cond_2

    .line 1761
    :cond_1
    invoke-virtual {p0, p1}, Lmiui/widget/ScreenView;->resetTransformation(Landroid/view/View;)V

    goto :goto_0

    .line 1764
    :cond_2
    invoke-static {v3}, Ljava/lang/Math;->abs(F)F

    move-result v0

    sub-float v0, v7, v0

    const v2, 0x3f333333

    mul-float/2addr v0, v2

    add-float/2addr v0, v8

    invoke-virtual {p1, v0}, Landroid/view/View;->setAlpha(F)V

    .line 1765
    invoke-virtual {p1, v1}, Landroid/view/View;->setTranslationX(F)V

    .line 1766
    invoke-virtual {p1, v1}, Landroid/view/View;->setTranslationY(F)V

    .line 1767
    invoke-virtual {p1, v7}, Landroid/view/View;->setScaleX(F)V

    .line 1768
    invoke-virtual {p1, v7}, Landroid/view/View;->setScaleY(F)V

    .line 1769
    invoke-virtual {p1, v1}, Landroid/view/View;->setPivotX(F)V

    .line 1770
    invoke-virtual {p1, v1}, Landroid/view/View;->setPivotY(F)V

    .line 1771
    invoke-virtual {p1, v1}, Landroid/view/View;->setRotation(F)V

    .line 1772
    invoke-virtual {p1, v1}, Landroid/view/View;->setRotationX(F)V

    .line 1773
    invoke-virtual {p1, v1}, Landroid/view/View;->setRotationY(F)V

    .line 1774
    iget v0, p0, Lmiui/widget/ScreenView;->cl:F

    invoke-virtual {p1, v0}, Landroid/view/View;->setCameraDistance(F)V

    goto :goto_0

    .line 1777
    :pswitch_4
    cmpl-float v0, v3, v1

    if-eqz v0, :cond_3

    invoke-static {v3}, Ljava/lang/Math;->abs(F)F

    move-result v0

    cmpl-float v0, v0, v7

    if-lez v0, :cond_4

    .line 1778
    :cond_3
    invoke-virtual {p0, p1}, Lmiui/widget/ScreenView;->resetTransformation(Landroid/view/View;)V

    goto :goto_0

    .line 1781
    :cond_4
    invoke-virtual {p1, v7}, Landroid/view/View;->setAlpha(F)V

    .line 1782
    invoke-virtual {p1, v1}, Landroid/view/View;->setTranslationX(F)V

    .line 1783
    invoke-virtual {p1, v1}, Landroid/view/View;->setTranslationY(F)V

    .line 1784
    invoke-virtual {p1, v7}, Landroid/view/View;->setScaleX(F)V

    .line 1785
    invoke-virtual {p1, v7}, Landroid/view/View;->setScaleY(F)V

    .line 1786
    invoke-virtual {p1, v4}, Landroid/view/View;->setPivotX(F)V

    .line 1787
    invoke-virtual {p1, v2}, Landroid/view/View;->setPivotY(F)V

    .line 1788
    neg-float v0, v3

    const/high16 v2, 0x41f00000

    mul-float/2addr v0, v2

    invoke-virtual {p1, v0}, Landroid/view/View;->setRotation(F)V

    .line 1789
    invoke-virtual {p1, v1}, Landroid/view/View;->setRotationX(F)V

    .line 1790
    invoke-virtual {p1, v1}, Landroid/view/View;->setRotationY(F)V

    .line 1791
    iget v0, p0, Lmiui/widget/ScreenView;->cl:F

    invoke-virtual {p1, v0}, Landroid/view/View;->setCameraDistance(F)V

    goto/16 :goto_0

    .line 1794
    :pswitch_5
    cmpl-float v2, v3, v1

    if-eqz v2, :cond_5

    invoke-static {v3}, Ljava/lang/Math;->abs(F)F

    move-result v2

    cmpl-float v2, v2, v7

    if-lez v2, :cond_6

    .line 1795
    :cond_5
    invoke-virtual {p0, p1}, Lmiui/widget/ScreenView;->resetTransformation(Landroid/view/View;)V

    goto/16 :goto_0

    .line 1798
    :cond_6
    invoke-virtual {p1, v7}, Landroid/view/View;->setAlpha(F)V

    .line 1799
    invoke-virtual {p1, v1}, Landroid/view/View;->setTranslationX(F)V

    .line 1800
    invoke-virtual {p1, v1}, Landroid/view/View;->setTranslationY(F)V

    .line 1801
    invoke-virtual {p1, v7}, Landroid/view/View;->setScaleX(F)V

    .line 1802
    invoke-virtual {p1, v7}, Landroid/view/View;->setScaleY(F)V

    .line 1803
    cmpg-float v2, v3, v1

    if-gez v2, :cond_7

    move v0, v1

    :cond_7
    invoke-virtual {p1, v0}, Landroid/view/View;->setPivotX(F)V

    .line 1804
    invoke-virtual {p1, v5}, Landroid/view/View;->setPivotY(F)V

    .line 1805
    invoke-virtual {p1, v1}, Landroid/view/View;->setRotation(F)V

    .line 1806
    invoke-virtual {p1, v1}, Landroid/view/View;->setRotationX(F)V

    .line 1807
    const/high16 v0, -0x3d4c0000

    mul-float/2addr v0, v3

    invoke-virtual {p1, v0}, Landroid/view/View;->setRotationY(F)V

    .line 1808
    invoke-virtual {p1, v9}, Landroid/view/View;->setCameraDistance(F)V

    goto/16 :goto_0

    .line 1811
    :pswitch_6
    cmpl-float v2, v3, v1

    if-eqz v2, :cond_8

    invoke-static {v3}, Ljava/lang/Math;->abs(F)F

    move-result v2

    cmpl-float v2, v2, v7

    if-lez v2, :cond_9

    .line 1812
    :cond_8
    invoke-virtual {p0, p1}, Lmiui/widget/ScreenView;->resetTransformation(Landroid/view/View;)V

    goto/16 :goto_0

    .line 1815
    :cond_9
    invoke-static {v3}, Ljava/lang/Math;->abs(F)F

    move-result v2

    sub-float v2, v7, v2

    invoke-virtual {p1, v2}, Landroid/view/View;->setAlpha(F)V

    .line 1816
    invoke-virtual {p1, v1}, Landroid/view/View;->setTranslationY(F)V

    .line 1817
    mul-float v2, v0, v3

    invoke-static {v3}, Ljava/lang/Math;->abs(F)F

    move-result v4

    mul-float/2addr v0, v4

    mul-float/2addr v0, v8

    sub-float v0, v2, v0

    invoke-virtual {p1, v0}, Landroid/view/View;->setTranslationX(F)V

    .line 1819
    mul-float v0, v8, v3

    add-float/2addr v0, v7

    .line 1820
    invoke-virtual {p1, v0}, Landroid/view/View;->setScaleX(F)V

    .line 1821
    invoke-virtual {p1, v0}, Landroid/view/View;->setScaleY(F)V

    .line 1822
    invoke-virtual {p1, v1}, Landroid/view/View;->setPivotX(F)V

    .line 1823
    invoke-virtual {p1, v5}, Landroid/view/View;->setPivotY(F)V

    .line 1824
    invoke-virtual {p1, v1}, Landroid/view/View;->setRotation(F)V

    .line 1825
    invoke-virtual {p1, v1}, Landroid/view/View;->setRotationX(F)V

    .line 1826
    const/high16 v0, 0x42340000

    neg-float v1, v3

    mul-float/2addr v0, v1

    invoke-virtual {p1, v0}, Landroid/view/View;->setRotationY(F)V

    .line 1827
    invoke-virtual {p1, v9}, Landroid/view/View;->setCameraDistance(F)V

    goto/16 :goto_0

    .line 1832
    :pswitch_7
    cmpg-float v4, v3, v1

    if-gtz v4, :cond_a

    .line 1833
    invoke-virtual {p0, p1}, Lmiui/widget/ScreenView;->resetTransformation(Landroid/view/View;)V

    goto/16 :goto_0

    .line 1836
    :cond_a
    sub-float v4, v7, v3

    invoke-virtual {p1, v4}, Landroid/view/View;->setAlpha(F)V

    .line 1837
    const v4, 0x3f19999a

    const v5, 0x3ecccccd

    sub-float v3, v7, v3

    mul-float/2addr v3, v5

    add-float/2addr v3, v4

    .line 1838
    sub-float v4, v7, v3

    mul-float/2addr v0, v4

    const/high16 v4, 0x40400000

    mul-float/2addr v0, v4

    invoke-virtual {p1, v0}, Landroid/view/View;->setTranslationX(F)V

    .line 1839
    sub-float v0, v7, v3

    mul-float/2addr v0, v2

    const/high16 v2, 0x3f000000

    mul-float/2addr v0, v2

    invoke-virtual {p1, v0}, Landroid/view/View;->setTranslationY(F)V

    .line 1840
    invoke-virtual {p1, v3}, Landroid/view/View;->setScaleX(F)V

    .line 1841
    invoke-virtual {p1, v3}, Landroid/view/View;->setScaleY(F)V

    .line 1842
    invoke-virtual {p1, v1}, Landroid/view/View;->setPivotX(F)V

    .line 1843
    invoke-virtual {p1, v1}, Landroid/view/View;->setPivotY(F)V

    .line 1844
    invoke-virtual {p1, v1}, Landroid/view/View;->setRotation(F)V

    .line 1845
    invoke-virtual {p1, v1}, Landroid/view/View;->setRotationX(F)V

    .line 1846
    invoke-virtual {p1, v1}, Landroid/view/View;->setRotationY(F)V

    .line 1847
    iget v0, p0, Lmiui/widget/ScreenView;->cl:F

    invoke-virtual {p1, v0}, Landroid/view/View;->setCameraDistance(F)V

    goto/16 :goto_0

    .line 1850
    :pswitch_8
    cmpl-float v2, v3, v1

    if-eqz v2, :cond_b

    invoke-static {v3}, Ljava/lang/Math;->abs(F)F

    move-result v2

    cmpl-float v2, v2, v7

    if-lez v2, :cond_c

    .line 1851
    :cond_b
    invoke-virtual {p0, p1}, Lmiui/widget/ScreenView;->resetTransformation(Landroid/view/View;)V

    goto/16 :goto_0

    .line 1854
    :cond_c
    invoke-static {v3}, Ljava/lang/Math;->abs(F)F

    move-result v2

    sub-float v2, v7, v2

    invoke-virtual {p1, v2}, Landroid/view/View;->setAlpha(F)V

    .line 1855
    mul-float/2addr v0, v3

    invoke-virtual {p1, v0}, Landroid/view/View;->setTranslationX(F)V

    .line 1856
    invoke-virtual {p1, v1}, Landroid/view/View;->setTranslationY(F)V

    .line 1857
    invoke-virtual {p1, v7}, Landroid/view/View;->setScaleX(F)V

    .line 1858
    invoke-virtual {p1, v7}, Landroid/view/View;->setScaleY(F)V

    .line 1859
    invoke-virtual {p1, v4}, Landroid/view/View;->setPivotX(F)V

    .line 1860
    invoke-virtual {p1, v5}, Landroid/view/View;->setPivotY(F)V

    .line 1861
    invoke-virtual {p1, v1}, Landroid/view/View;->setRotation(F)V

    .line 1862
    invoke-virtual {p1, v1}, Landroid/view/View;->setRotationX(F)V

    .line 1863
    const/high16 v0, 0x42b40000

    neg-float v1, v3

    mul-float/2addr v0, v1

    invoke-virtual {p1, v0}, Landroid/view/View;->setRotationY(F)V

    .line 1864
    invoke-virtual {p1, v9}, Landroid/view/View;->setCameraDistance(F)V

    goto/16 :goto_0

    .line 1867
    :pswitch_9
    invoke-virtual {p0, p1, v3}, Lmiui/widget/ScreenView;->updateChildStaticTransformationByScreen(Landroid/view/View;F)V

    goto/16 :goto_0

    .line 1752
    nop

    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_1
        :pswitch_2
        :pswitch_3
        :pswitch_4
        :pswitch_5
        :pswitch_6
        :pswitch_0
        :pswitch_7
        :pswitch_8
        :pswitch_9
    .end packed-switch
.end method

.method static synthetic c(Lmiui/widget/ScreenView;)F
    .locals 1

    .prologue
    .line 56
    iget v0, p0, Lmiui/widget/ScreenView;->dc:F

    return v0
.end method

.method private c(II)V
    .locals 5

    .prologue
    const/4 v1, 0x0

    .line 1612
    iget-object v0, p0, Lmiui/widget/ScreenView;->cv:Lmiui/widget/ScreenView$b;

    if-eqz v0, :cond_1

    .line 1613
    invoke-virtual {p0}, Lmiui/widget/ScreenView;->getScreenCount()I

    move-result v2

    move v0, v1

    .line 1614
    :goto_0
    iget v3, p0, Lmiui/widget/ScreenView;->mVisibleRange:I

    if-ge v0, v3, :cond_0

    add-int v3, p1, v0

    if-ge v3, v2, :cond_0

    .line 1615
    iget-object v3, p0, Lmiui/widget/ScreenView;->cv:Lmiui/widget/ScreenView$b;

    add-int v4, p1, v0

    invoke-virtual {v3, v4}, Lmiui/widget/ScreenView$b;->getChildAt(I)Landroid/view/View;

    move-result-object v3

    invoke-virtual {v3, v1}, Landroid/view/View;->setSelected(Z)V

    .line 1614
    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    .line 1619
    :cond_0
    :goto_1
    iget v0, p0, Lmiui/widget/ScreenView;->mVisibleRange:I

    if-ge v1, v0, :cond_1

    add-int v0, p2, v1

    if-ge v0, v2, :cond_1

    .line 1620
    iget-object v0, p0, Lmiui/widget/ScreenView;->cv:Lmiui/widget/ScreenView$b;

    add-int v3, p2, v1

    invoke-virtual {v0, v3}, Lmiui/widget/ScreenView$b;->getChildAt(I)Landroid/view/View;

    move-result-object v0

    const/4 v3, 0x1

    invoke-virtual {v0, v3}, Landroid/view/View;->setSelected(Z)V

    .line 1619
    add-int/lit8 v1, v1, 0x1

    goto :goto_1

    .line 1623
    :cond_1
    return-void
.end method

.method static synthetic d(Lmiui/widget/ScreenView;)Landroid/widget/Scroller;
    .locals 1

    .prologue
    .line 56
    iget-object v0, p0, Lmiui/widget/ScreenView;->cL:Landroid/widget/Scroller;

    return-object v0
.end method

.method static synthetic e(Lmiui/widget/ScreenView;)I
    .locals 1

    .prologue
    .line 56
    iget v0, p0, Lmiui/widget/ScreenView;->cG:I

    return v0
.end method

.method static synthetic f(Lmiui/widget/ScreenView;)I
    .locals 1

    .prologue
    .line 56
    iget v0, p0, Lmiui/widget/ScreenView;->cP:I

    return v0
.end method

.method private o(I)V
    .locals 4

    .prologue
    .line 754
    invoke-virtual {p0}, Lmiui/widget/ScreenView;->getScreenCount()I

    move-result v0

    .line 755
    iget-object v1, p0, Lmiui/widget/ScreenView;->cw:Lmiui/widget/ScreenView$d;

    if-eqz v1, :cond_0

    if-lez v0, :cond_0

    .line 756
    iget-object v1, p0, Lmiui/widget/ScreenView;->cw:Lmiui/widget/ScreenView$d;

    invoke-virtual {v1}, Lmiui/widget/ScreenView$d;->bD()I

    move-result v1

    .line 757
    div-int v2, v1, v0

    iget v3, p0, Lmiui/widget/ScreenView;->mVisibleRange:I

    mul-int/2addr v2, v3

    const/16 v3, 0x30

    invoke-static {v2, v3}, Ljava/lang/Math;->max(II)I

    move-result v2

    .line 760
    iget v3, p0, Lmiui/widget/ScreenView;->mChildScreenWidth:I

    mul-int/2addr v0, v3

    .line 761
    if-gt v0, v1, :cond_1

    const/4 v0, 0x0

    .line 766
    :goto_0
    iget-object v1, p0, Lmiui/widget/ScreenView;->cw:Lmiui/widget/ScreenView$d;

    add-int/2addr v2, v0

    invoke-virtual {v1, v0, v2}, Lmiui/widget/ScreenView$d;->f(II)V

    .line 767
    invoke-virtual {p0}, Lmiui/widget/ScreenView;->isHardwareAccelerated()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 768
    iget-object v0, p0, Lmiui/widget/ScreenView;->cw:Lmiui/widget/ScreenView$d;

    invoke-virtual {v0}, Lmiui/widget/ScreenView$d;->invalidate()V

    .line 771
    :cond_0
    return-void

    .line 761
    :cond_1
    sub-int v3, v1, v2

    mul-int/2addr v3, p1

    sub-int/2addr v0, v1

    div-int v0, v3, v0

    goto :goto_0
.end method

.method private p(I)V
    .locals 3

    .prologue
    .line 774
    iget-object v0, p0, Lmiui/widget/ScreenView;->ct:Lmiui/widget/ScreenView$g;

    if-eqz v0, :cond_0

    .line 775
    iget-object v1, p0, Lmiui/widget/ScreenView;->ct:Lmiui/widget/ScreenView$g;

    if-gtz p1, :cond_1

    iget v0, p0, Lmiui/widget/ScreenView;->cp:I

    :goto_0
    invoke-virtual {v1, v0}, Lmiui/widget/ScreenView$g;->setImageResource(I)V

    .line 779
    iget-object v1, p0, Lmiui/widget/ScreenView;->cu:Lmiui/widget/ScreenView$g;

    invoke-virtual {p0}, Lmiui/widget/ScreenView;->getScreenCount()I

    move-result v0

    iget v2, p0, Lmiui/widget/ScreenView;->mChildScreenWidth:I

    mul-int/2addr v0, v2

    iget v2, p0, Lmiui/widget/ScreenView;->cD:I

    sub-int/2addr v0, v2

    iget v2, p0, Lmiui/widget/ScreenView;->cC:I

    sub-int/2addr v0, v2

    if-lt p1, v0, :cond_2

    iget v0, p0, Lmiui/widget/ScreenView;->cr:I

    :goto_1
    invoke-virtual {v1, v0}, Lmiui/widget/ScreenView$g;->setImageResource(I)V

    .line 785
    :cond_0
    return-void

    .line 775
    :cond_1
    iget v0, p0, Lmiui/widget/ScreenView;->co:I

    goto :goto_0

    .line 779
    :cond_2
    iget v0, p0, Lmiui/widget/ScreenView;->cq:I

    goto :goto_1
.end method

.method private q(I)V
    .locals 3

    .prologue
    .line 1181
    iget v0, p0, Lmiui/widget/ScreenView;->mChildScreenWidth:I

    if-lez v0, :cond_0

    invoke-virtual {p0}, Lmiui/widget/ScreenView;->getCurrentScreen()Landroid/view/View;

    move-result-object v0

    if-nez v0, :cond_1

    .line 1189
    :cond_0
    :goto_0
    return-void

    .line 1184
    :cond_1
    iget-object v0, p0, Lmiui/widget/ScreenView;->de:Lmiui/widget/ScreenView$a;

    const/16 v1, 0x3e8

    iget v2, p0, Lmiui/widget/ScreenView;->cU:I

    invoke-virtual {v0, v1, v2, p1}, Lmiui/widget/ScreenView$a;->a(III)F

    move-result v0

    float-to-int v0, v0

    .line 1186
    iget-object v1, p0, Lmiui/widget/ScreenView;->de:Lmiui/widget/ScreenView$a;

    invoke-static {v0}, Ljava/lang/Math;->abs(I)I

    move-result v2

    int-to-float v2, v2

    invoke-virtual {v1, v2}, Lmiui/widget/ScreenView$a;->a(F)I

    move-result v1

    .line 1188
    invoke-virtual {p0, v0, v1}, Lmiui/widget/ScreenView;->snapByVelocity(II)V

    goto :goto_0
.end method

.method private r(I)V
    .locals 1

    .prologue
    .line 1352
    iget v0, p0, Lmiui/widget/ScreenView;->mCurrentScreen:I

    invoke-direct {p0, v0, p1}, Lmiui/widget/ScreenView;->c(II)V

    .line 1353
    iput p1, p0, Lmiui/widget/ScreenView;->mCurrentScreen:I

    .line 1354
    const/4 v0, -0x1

    iput v0, p0, Lmiui/widget/ScreenView;->cG:I

    .line 1355
    return-void
.end method

.method private t()V
    .locals 4

    .prologue
    const/4 v0, 0x1

    .line 424
    invoke-virtual {p0, v0}, Lmiui/widget/ScreenView;->setAlwaysDrawnWithCacheEnabled(Z)V

    .line 425
    invoke-virtual {p0, v0}, Lmiui/widget/ScreenView;->setClipToPadding(Z)V

    .line 426
    new-instance v0, Lmiui/widget/ScreenView$i;

    invoke-direct {v0, p0}, Lmiui/widget/ScreenView$i;-><init>(Lmiui/widget/ScreenView;)V

    iput-object v0, p0, Lmiui/widget/ScreenView;->da:Lmiui/widget/ScreenView$i;

    .line 427
    new-instance v0, Landroid/widget/Scroller;

    invoke-virtual {p0}, Lmiui/widget/ScreenView;->getContext()Landroid/content/Context;

    move-result-object v1

    iget-object v2, p0, Lmiui/widget/ScreenView;->da:Lmiui/widget/ScreenView$i;

    invoke-direct {v0, v1, v2}, Landroid/widget/Scroller;-><init>(Landroid/content/Context;Landroid/view/animation/Interpolator;)V

    iput-object v0, p0, Lmiui/widget/ScreenView;->cL:Landroid/widget/Scroller;

    .line 428
    const/4 v0, 0x0

    invoke-direct {p0, v0}, Lmiui/widget/ScreenView;->r(I)V

    .line 429
    invoke-virtual {p0}, Lmiui/widget/ScreenView;->getContext()Landroid/content/Context;

    move-result-object v0

    invoke-static {v0}, Landroid/view/ViewConfiguration;->get(Landroid/content/Context;)Landroid/view/ViewConfiguration;

    move-result-object v0

    .line 430
    invoke-virtual {v0}, Landroid/view/ViewConfiguration;->getScaledTouchSlop()I

    move-result v1

    iput v1, p0, Lmiui/widget/ScreenView;->cT:I

    .line 431
    invoke-virtual {v0}, Landroid/view/ViewConfiguration;->getScaledMaximumFlingVelocity()I

    move-result v0

    invoke-virtual {p0, v0}, Lmiui/widget/ScreenView;->setMaximumSnapVelocity(I)V

    .line 432
    new-instance v0, Landroid/view/ScaleGestureDetector;

    invoke-virtual {p0}, Lmiui/widget/ScreenView;->getContext()Landroid/content/Context;

    move-result-object v1

    new-instance v2, Lmiui/widget/ScreenView$h;

    const/4 v3, 0x0

    invoke-direct {v2, p0, v3}, Lmiui/widget/ScreenView$h;-><init>(Lmiui/widget/ScreenView;Lmiui/widget/n;)V

    invoke-direct {v0, v1, v2}, Landroid/view/ScaleGestureDetector;-><init>(Landroid/content/Context;Landroid/view/ScaleGestureDetector$OnScaleGestureListener;)V

    iput-object v0, p0, Lmiui/widget/ScreenView;->cO:Landroid/view/ScaleGestureDetector;

    .line 434
    return-void
.end method

.method private u()V
    .locals 3

    .prologue
    .line 563
    iget-object v0, p0, Lmiui/widget/ScreenView;->cw:Lmiui/widget/ScreenView$d;

    if-eqz v0, :cond_0

    iget-boolean v0, p0, Lmiui/widget/ScreenView;->cx:Z

    if-nez v0, :cond_1

    .line 573
    :cond_0
    :goto_0
    return-void

    .line 566
    :cond_1
    iget-object v0, p0, Lmiui/widget/ScreenView;->cy:Ljava/lang/Runnable;

    invoke-virtual {p0, v0}, Lmiui/widget/ScreenView;->removeCallbacks(Ljava/lang/Runnable;)Z

    .line 567
    iget-object v0, p0, Lmiui/widget/ScreenView;->cw:Lmiui/widget/ScreenView$d;

    invoke-virtual {v0}, Lmiui/widget/ScreenView$d;->animate()Landroid/view/ViewPropertyAnimator;

    move-result-object v0

    invoke-virtual {v0}, Landroid/view/ViewPropertyAnimator;->cancel()V

    .line 568
    iget-object v0, p0, Lmiui/widget/ScreenView;->cw:Lmiui/widget/ScreenView$d;

    const/high16 v1, 0x3f800000

    invoke-virtual {v0, v1}, Lmiui/widget/ScreenView$d;->setAlpha(F)V

    .line 569
    iget-object v0, p0, Lmiui/widget/ScreenView;->cw:Lmiui/widget/ScreenView$d;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Lmiui/widget/ScreenView$d;->setVisibility(I)V

    .line 570
    iget v0, p0, Lmiui/widget/ScreenView;->cP:I

    if-nez v0, :cond_0

    .line 571
    iget-object v0, p0, Lmiui/widget/ScreenView;->cy:Ljava/lang/Runnable;

    const-wide/16 v1, 0x3e8

    invoke-virtual {p0, v0, v1, v2}, Lmiui/widget/ScreenView;->postDelayed(Ljava/lang/Runnable;J)Z

    goto :goto_0
.end method

.method private v()V
    .locals 3

    .prologue
    .line 576
    iget-boolean v0, p0, Lmiui/widget/ScreenView;->cx:Z

    if-nez v0, :cond_0

    .line 587
    :goto_0
    return-void

    .line 579
    :cond_0
    iget-object v0, p0, Lmiui/widget/ScreenView;->cw:Lmiui/widget/ScreenView$d;

    invoke-virtual {v0}, Lmiui/widget/ScreenView$d;->animate()Landroid/view/ViewPropertyAnimator;

    move-result-object v0

    const-wide/16 v1, 0x1f4

    invoke-virtual {v0, v1, v2}, Landroid/view/ViewPropertyAnimator;->setDuration(J)Landroid/view/ViewPropertyAnimator;

    move-result-object v0

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Landroid/view/ViewPropertyAnimator;->alpha(F)Landroid/view/ViewPropertyAnimator;

    move-result-object v0

    new-instance v1, Lmiui/widget/ScreenView$1;

    invoke-direct {v1, p0}, Lmiui/widget/ScreenView$1;-><init>(Lmiui/widget/ScreenView;)V

    invoke-virtual {v0, v1}, Landroid/view/ViewPropertyAnimator;->setListener(Landroid/animation/Animator$AnimatorListener;)Landroid/view/ViewPropertyAnimator;

    goto :goto_0
.end method

.method private w()V
    .locals 2

    .prologue
    .line 675
    iget v0, p0, Lmiui/widget/ScreenView;->cB:I

    packed-switch v0, :pswitch_data_0

    .line 689
    :goto_0
    iget v0, p0, Lmiui/widget/ScreenView;->cC:I

    invoke-virtual {p0}, Lmiui/widget/ScreenView;->getPaddingLeft()I

    move-result v1

    add-int/2addr v0, v1

    iput v0, p0, Lmiui/widget/ScreenView;->cC:I

    .line 690
    return-void

    .line 677
    :pswitch_0
    iget v0, p0, Lmiui/widget/ScreenView;->mScreenOffset:I

    iput v0, p0, Lmiui/widget/ScreenView;->cC:I

    goto :goto_0

    .line 680
    :pswitch_1
    const/4 v0, 0x0

    iput v0, p0, Lmiui/widget/ScreenView;->cC:I

    goto :goto_0

    .line 683
    :pswitch_2
    iget v0, p0, Lmiui/widget/ScreenView;->cD:I

    iget v1, p0, Lmiui/widget/ScreenView;->mChildScreenWidth:I

    sub-int/2addr v0, v1

    div-int/lit8 v0, v0, 0x2

    iput v0, p0, Lmiui/widget/ScreenView;->cC:I

    goto :goto_0

    .line 686
    :pswitch_3
    iget v0, p0, Lmiui/widget/ScreenView;->cD:I

    iget v1, p0, Lmiui/widget/ScreenView;->mChildScreenWidth:I

    sub-int/2addr v0, v1

    iput v0, p0, Lmiui/widget/ScreenView;->cC:I

    goto :goto_0

    .line 675
    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_0
        :pswitch_1
        :pswitch_2
        :pswitch_3
    .end packed-switch
.end method

.method private x()V
    .locals 3

    .prologue
    .line 810
    iget v0, p0, Lmiui/widget/ScreenView;->mChildScreenWidth:I

    neg-int v0, v0

    int-to-float v0, v0

    iget v1, p0, Lmiui/widget/ScreenView;->cJ:F

    mul-float/2addr v0, v1

    float-to-int v0, v0

    iget v1, p0, Lmiui/widget/ScreenView;->cC:I

    sub-int/2addr v0, v1

    iput v0, p0, Lmiui/widget/ScreenView;->cI:I

    .line 812
    iget-boolean v0, p0, Lmiui/widget/ScreenView;->cK:Z

    if-nez v0, :cond_0

    .line 813
    iget v0, p0, Lmiui/widget/ScreenView;->mChildScreenWidth:I

    int-to-float v0, v0

    invoke-virtual {p0}, Lmiui/widget/ScreenView;->getScreenCount()I

    move-result v1

    int-to-float v1, v1

    iget v2, p0, Lmiui/widget/ScreenView;->cJ:F

    add-float/2addr v1, v2

    mul-float/2addr v0, v1

    iget v1, p0, Lmiui/widget/ScreenView;->cD:I

    int-to-float v1, v1

    sub-float/2addr v0, v1

    float-to-int v0, v0

    iget v1, p0, Lmiui/widget/ScreenView;->cC:I

    add-int/2addr v0, v1

    iput v0, p0, Lmiui/widget/ScreenView;->cH:I

    .line 821
    :goto_0
    return-void

    .line 817
    :cond_0
    invoke-virtual {p0}, Lmiui/widget/ScreenView;->getScreenCount()I

    move-result v0

    add-int/lit8 v0, v0, -0x1

    iget v1, p0, Lmiui/widget/ScreenView;->mVisibleRange:I

    div-int/2addr v0, v1

    iget v1, p0, Lmiui/widget/ScreenView;->cD:I

    mul-int/2addr v0, v1

    int-to-float v0, v0

    iget v1, p0, Lmiui/widget/ScreenView;->mChildScreenWidth:I

    int-to-float v1, v1

    iget v2, p0, Lmiui/widget/ScreenView;->cJ:F

    mul-float/2addr v1, v2

    add-float/2addr v0, v1

    float-to-int v0, v0

    iput v0, p0, Lmiui/widget/ScreenView;->cH:I

    goto :goto_0
.end method

.method private y()Landroid/widget/ImageView;
    .locals 2

    .prologue
    .line 1605
    new-instance v0, Landroid/widget/ImageView;

    invoke-virtual {p0}, Lmiui/widget/ScreenView;->getContext()Landroid/content/Context;

    move-result-object v1

    invoke-direct {v0, v1}, Landroid/widget/ImageView;-><init>(Landroid/content/Context;)V

    .line 1606
    sget-object v1, Landroid/widget/ImageView$ScaleType;->CENTER:Landroid/widget/ImageView$ScaleType;

    invoke-virtual {v0, v1}, Landroid/widget/ImageView;->setScaleType(Landroid/widget/ImageView$ScaleType;)V

    .line 1607
    iget v1, p0, Lmiui/widget/ScreenView;->cs:I

    invoke-virtual {v0, v1}, Landroid/widget/ImageView;->setImageResource(I)V

    .line 1608
    return-object v0
.end method

.method static synthetic z()Lcom/miui/internal/variable/Android_View_View_class;
    .locals 1

    .prologue
    .line 56
    sget-object v0, Lmiui/widget/ScreenView;->bX:Lcom/miui/internal/variable/Android_View_View_class;

    return-object v0
.end method


# virtual methods
.method protected addIndicator(Landroid/view/View;Landroid/widget/FrameLayout$LayoutParams;)V
    .locals 1

    .prologue
    .line 1470
    iget v0, p0, Lmiui/widget/ScreenView;->cn:I

    add-int/lit8 v0, v0, 0x1

    iput v0, p0, Lmiui/widget/ScreenView;->cn:I

    .line 1471
    const/4 v0, -0x1

    invoke-super {p0, p1, v0, p2}, Landroid/view/ViewGroup;->addView(Landroid/view/View;ILandroid/view/ViewGroup$LayoutParams;)V

    .line 1472
    return-void
.end method

.method protected addIndicatorAt(Landroid/view/View;Landroid/widget/FrameLayout$LayoutParams;I)V
    .locals 2

    .prologue
    .line 1484
    const/4 v0, -0x1

    iget v1, p0, Lmiui/widget/ScreenView;->cn:I

    invoke-static {p3, v1}, Ljava/lang/Math;->min(II)I

    move-result v1

    invoke-static {v0, v1}, Ljava/lang/Math;->max(II)I

    move-result v0

    .line 1485
    if-ltz v0, :cond_0

    .line 1486
    invoke-virtual {p0}, Lmiui/widget/ScreenView;->getScreenCount()I

    move-result v1

    add-int/2addr v0, v1

    .line 1488
    :cond_0
    iget v1, p0, Lmiui/widget/ScreenView;->cn:I

    add-int/lit8 v1, v1, 0x1

    iput v1, p0, Lmiui/widget/ScreenView;->cn:I

    .line 1489
    invoke-super {p0, p1, v0, p2}, Landroid/view/ViewGroup;->addView(Landroid/view/View;ILandroid/view/ViewGroup$LayoutParams;)V

    .line 1490
    return-void
.end method

.method public addView(Landroid/view/View;ILandroid/view/ViewGroup$LayoutParams;)V
    .locals 4

    .prologue
    .line 1391
    invoke-virtual {p0}, Lmiui/widget/ScreenView;->getScreenCount()I

    move-result v0

    .line 1392
    if-gez p2, :cond_1

    .line 1397
    :goto_0
    iget-object v1, p0, Lmiui/widget/ScreenView;->cv:Lmiui/widget/ScreenView$b;

    if-eqz v1, :cond_0

    .line 1398
    iget-object v1, p0, Lmiui/widget/ScreenView;->cv:Lmiui/widget/ScreenView$b;

    invoke-direct {p0}, Lmiui/widget/ScreenView;->y()Landroid/widget/ImageView;

    move-result-object v2

    sget-object v3, Lmiui/widget/ScreenView;->ca:Landroid/widget/LinearLayout$LayoutParams;

    invoke-virtual {v1, v2, v0, v3}, Lmiui/widget/ScreenView$b;->addView(Landroid/view/View;ILandroid/view/ViewGroup$LayoutParams;)V

    .line 1401
    :cond_0
    iget v1, p0, Lmiui/widget/ScreenView;->cN:I

    add-int/lit8 v1, v1, 0x1

    iput v1, p0, Lmiui/widget/ScreenView;->cN:I

    .line 1402
    invoke-direct {p0}, Lmiui/widget/ScreenView;->x()V

    .line 1403
    invoke-super {p0, p1, v0, p3}, Landroid/view/ViewGroup;->addView(Landroid/view/View;ILandroid/view/ViewGroup$LayoutParams;)V

    .line 1404
    return-void

    .line 1395
    :cond_1
    invoke-static {p2, v0}, Ljava/lang/Math;->min(II)I

    move-result v0

    goto :goto_0
.end method

.method public allowLongPress()Z
    .locals 1

    .prologue
    .line 1568
    iget-boolean v0, p0, Lmiui/widget/ScreenView;->cS:Z

    return v0
.end method

.method public computeScroll()V
    .locals 6

    .prologue
    const/4 v5, 0x0

    const/4 v3, -0x1

    const v2, 0x4e6e6b28

    .line 846
    iget-object v0, p0, Lmiui/widget/ScreenView;->cL:Landroid/widget/Scroller;

    invoke-virtual {v0}, Landroid/widget/Scroller;->computeScrollOffset()Z

    move-result v0

    if-eqz v0, :cond_1

    .line 847
    iget-object v0, p0, Lmiui/widget/ScreenView;->cL:Landroid/widget/Scroller;

    invoke-virtual {v0}, Landroid/widget/Scroller;->getCurrX()I

    move-result v0

    int-to-float v0, v0

    iput v0, p0, Lmiui/widget/ScreenView;->cX:F

    .line 848
    sget-object v0, Lmiui/widget/ScreenView;->bX:Lcom/miui/internal/variable/Android_View_View_class;

    iget v1, p0, Lmiui/widget/ScreenView;->cX:F

    float-to-int v1, v1

    invoke-virtual {v0, p0, v1}, Lcom/miui/internal/variable/Android_View_View_class;->setScrollXDirectly(Landroid/view/View;I)V

    .line 849
    invoke-static {}, Ljava/lang/System;->nanoTime()J

    move-result-wide v0

    long-to-float v0, v0

    div-float/2addr v0, v2

    iput v0, p0, Lmiui/widget/ScreenView;->cW:F

    .line 850
    sget-object v0, Lmiui/widget/ScreenView;->bX:Lcom/miui/internal/variable/Android_View_View_class;

    iget-object v1, p0, Lmiui/widget/ScreenView;->cL:Landroid/widget/Scroller;

    invoke-virtual {v1}, Landroid/widget/Scroller;->getCurrY()I

    move-result v1

    invoke-virtual {v0, p0, v1}, Lcom/miui/internal/variable/Android_View_View_class;->setScrollYDirectly(Landroid/view/View;I)V

    .line 851
    invoke-virtual {p0}, Lmiui/widget/ScreenView;->postInvalidateOnAnimation()V

    .line 872
    :cond_0
    :goto_0
    invoke-virtual {p0}, Lmiui/widget/ScreenView;->getScrollX()I

    move-result v0

    invoke-direct {p0, v0, v5}, Lmiui/widget/ScreenView;->b(IZ)V

    .line 873
    invoke-virtual {p0}, Lmiui/widget/ScreenView;->getScrollX()I

    move-result v0

    invoke-direct {p0, v0}, Lmiui/widget/ScreenView;->o(I)V

    .line 874
    invoke-virtual {p0}, Lmiui/widget/ScreenView;->getScrollX()I

    move-result v0

    invoke-direct {p0, v0}, Lmiui/widget/ScreenView;->p(I)V

    .line 875
    return-void

    .line 852
    :cond_1
    iget v0, p0, Lmiui/widget/ScreenView;->cG:I

    if-eq v0, v3, :cond_2

    .line 853
    iget v0, p0, Lmiui/widget/ScreenView;->cG:I

    invoke-virtual {p0}, Lmiui/widget/ScreenView;->getScreenCount()I

    move-result v1

    add-int/lit8 v1, v1, -0x1

    invoke-static {v0, v1}, Ljava/lang/Math;->min(II)I

    move-result v0

    invoke-static {v5, v0}, Ljava/lang/Math;->max(II)I

    move-result v0

    invoke-direct {p0, v0}, Lmiui/widget/ScreenView;->r(I)V

    .line 855
    iput v3, p0, Lmiui/widget/ScreenView;->cG:I

    .line 856
    iget-object v0, p0, Lmiui/widget/ScreenView;->dd:Lmiui/widget/ScreenView$SnapScreenOnceNotification;

    if-eqz v0, :cond_0

    .line 857
    iget-object v0, p0, Lmiui/widget/ScreenView;->dd:Lmiui/widget/ScreenView$SnapScreenOnceNotification;

    invoke-interface {v0, p0}, Lmiui/widget/ScreenView$SnapScreenOnceNotification;->onSnapEnd(Lmiui/widget/ScreenView;)V

    .line 858
    const/4 v0, 0x0

    iput-object v0, p0, Lmiui/widget/ScreenView;->dd:Lmiui/widget/ScreenView$SnapScreenOnceNotification;

    goto :goto_0

    .line 860
    :cond_2
    iget v0, p0, Lmiui/widget/ScreenView;->cP:I

    const/4 v1, 0x1

    if-ne v0, v1, :cond_0

    .line 861
    invoke-static {}, Ljava/lang/System;->nanoTime()J

    move-result-wide v0

    long-to-float v0, v0

    div-float/2addr v0, v2

    .line 862
    iget v1, p0, Lmiui/widget/ScreenView;->cW:F

    sub-float v1, v0, v1

    sget v2, Lmiui/widget/ScreenView;->cg:F

    div-float/2addr v1, v2

    float-to-double v1, v1

    invoke-static {v1, v2}, Ljava/lang/Math;->exp(D)D

    move-result-wide v1

    double-to-float v1, v1

    .line 864
    iget v2, p0, Lmiui/widget/ScreenView;->cX:F

    invoke-virtual {p0}, Lmiui/widget/ScreenView;->getScrollX()I

    move-result v3

    int-to-float v3, v3

    sub-float/2addr v2, v3

    .line 865
    sget-object v3, Lmiui/widget/ScreenView;->bX:Lcom/miui/internal/variable/Android_View_View_class;

    invoke-virtual {p0}, Lmiui/widget/ScreenView;->getScrollX()I

    move-result v4

    int-to-float v4, v4

    mul-float/2addr v1, v2

    add-float/2addr v1, v4

    float-to-int v1, v1

    invoke-virtual {v3, p0, v1}, Lcom/miui/internal/variable/Android_View_View_class;->setScrollXDirectly(Landroid/view/View;I)V

    .line 866
    iput v0, p0, Lmiui/widget/ScreenView;->cW:F

    .line 868
    const/high16 v0, 0x3f800000

    cmpl-float v0, v2, v0

    if-gtz v0, :cond_3

    const/high16 v0, -0x40800000

    cmpg-float v0, v2, v0

    if-gez v0, :cond_0

    .line 869
    :cond_3
    invoke-virtual {p0}, Lmiui/widget/ScreenView;->postInvalidate()V

    goto :goto_0
.end method

.method public dispatchUnhandledMove(Landroid/view/View;I)Z
    .locals 3

    .prologue
    const/4 v0, 0x1

    .line 1009
    const/16 v1, 0x11

    if-ne p2, v1, :cond_0

    .line 1010
    iget v1, p0, Lmiui/widget/ScreenView;->mCurrentScreen:I

    if-lez v1, :cond_1

    .line 1011
    iget v1, p0, Lmiui/widget/ScreenView;->mCurrentScreen:I

    add-int/lit8 v1, v1, -0x1

    invoke-virtual {p0, v1}, Lmiui/widget/ScreenView;->snapToScreen(I)V

    .line 1020
    :goto_0
    return v0

    .line 1014
    :cond_0
    const/16 v1, 0x42

    if-ne p2, v1, :cond_1

    .line 1015
    iget v1, p0, Lmiui/widget/ScreenView;->mCurrentScreen:I

    invoke-virtual {p0}, Lmiui/widget/ScreenView;->getScreenCount()I

    move-result v2

    add-int/lit8 v2, v2, -0x1

    if-ge v1, v2, :cond_1

    .line 1016
    iget v1, p0, Lmiui/widget/ScreenView;->mCurrentScreen:I

    add-int/lit8 v1, v1, 0x1

    invoke-virtual {p0, v1}, Lmiui/widget/ScreenView;->snapToScreen(I)V

    goto :goto_0

    .line 1020
    :cond_1
    invoke-super {p0, p1, p2}, Landroid/view/ViewGroup;->dispatchUnhandledMove(Landroid/view/View;I)Z

    move-result v0

    goto :goto_0
.end method

.method protected drawChild(Landroid/graphics/Canvas;Landroid/view/View;J)Z
    .locals 1

    .prologue
    .line 989
    invoke-direct {p0, p2}, Lmiui/widget/ScreenView;->b(Landroid/view/View;)V

    .line 990
    invoke-super {p0, p1, p2, p3, p4}, Landroid/view/ViewGroup;->drawChild(Landroid/graphics/Canvas;Landroid/view/View;J)Z

    move-result v0

    return v0
.end method

.method protected finishCurrentGesture()V
    .locals 2

    .prologue
    .line 1904
    const/4 v0, 0x1

    iput-boolean v0, p0, Lmiui/widget/ScreenView;->cR:Z

    .line 1905
    const/4 v0, 0x0

    const/4 v1, 0x0

    invoke-direct {p0, v0, v1}, Lmiui/widget/ScreenView;->a(Landroid/view/MotionEvent;I)V

    .line 1906
    return-void
.end method

.method public getCurrentScreen()Landroid/view/View;
    .locals 1

    .prologue
    .line 1326
    iget v0, p0, Lmiui/widget/ScreenView;->mCurrentScreen:I

    invoke-virtual {p0, v0}, Lmiui/widget/ScreenView;->getScreen(I)Landroid/view/View;

    move-result-object v0

    return-object v0
.end method

.method public getCurrentScreenIndex()I
    .locals 2

    .prologue
    .line 1314
    iget v0, p0, Lmiui/widget/ScreenView;->cG:I

    const/4 v1, -0x1

    if-eq v0, v1, :cond_0

    .line 1315
    iget v0, p0, Lmiui/widget/ScreenView;->cG:I

    .line 1317
    :goto_0
    return v0

    :cond_0
    iget v0, p0, Lmiui/widget/ScreenView;->mCurrentScreen:I

    goto :goto_0
.end method

.method public getScreen(I)Landroid/view/View;
    .locals 1

    .prologue
    .line 1365
    if-ltz p1, :cond_0

    invoke-virtual {p0}, Lmiui/widget/ScreenView;->getScreenCount()I

    move-result v0

    if-lt p1, v0, :cond_1

    .line 1366
    :cond_0
    const/4 v0, 0x0

    .line 1368
    :goto_0
    return-object v0

    :cond_1
    invoke-virtual {p0, p1}, Lmiui/widget/ScreenView;->getChildAt(I)Landroid/view/View;

    move-result-object v0

    goto :goto_0
.end method

.method public final getScreenCount()I
    .locals 1

    .prologue
    .line 1305
    iget v0, p0, Lmiui/widget/ScreenView;->cN:I

    return v0
.end method

.method public getScreenTransitionType()I
    .locals 1

    .prologue
    .line 1652
    iget v0, p0, Lmiui/widget/ScreenView;->db:I

    return v0
.end method

.method public getSeekPointView(I)Landroid/widget/ImageView;
    .locals 1

    .prologue
    .line 1601
    iget-object v0, p0, Lmiui/widget/ScreenView;->cv:Lmiui/widget/ScreenView$b;

    invoke-virtual {v0, p1}, Lmiui/widget/ScreenView$b;->getChildAt(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/ImageView;

    return-object v0
.end method

.method protected getTouchState()I
    .locals 1

    .prologue
    .line 1025
    iget v0, p0, Lmiui/widget/ScreenView;->cP:I

    return v0
.end method

.method public getVisibleRange()I
    .locals 1

    .prologue
    .line 1380
    iget v0, p0, Lmiui/widget/ScreenView;->mVisibleRange:I

    return v0
.end method

.method protected onAttachedToWindow()V
    .locals 0

    .prologue
    .line 879
    invoke-super {p0}, Landroid/view/ViewGroup;->onAttachedToWindow()V

    .line 880
    invoke-virtual {p0}, Lmiui/widget/ScreenView;->computeScroll()V

    .line 881
    invoke-direct {p0}, Lmiui/widget/ScreenView;->u()V

    .line 882
    return-void
.end method

.method public onInterceptTouchEvent(Landroid/view/MotionEvent;)Z
    .locals 5

    .prologue
    const/4 v4, 0x3

    const/4 v1, 0x1

    const/4 v0, 0x0

    .line 1062
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getAction()I

    move-result v2

    and-int/lit16 v2, v2, 0xff

    packed-switch v2, :pswitch_data_0

    .line 1097
    :cond_0
    :goto_0
    const/4 v2, 0x2

    invoke-virtual {p1}, Landroid/view/MotionEvent;->getAction()I

    move-result v3

    and-int/lit16 v3, v3, 0xff

    if-eq v2, v3, :cond_1

    .line 1099
    invoke-direct {p0, p1}, Lmiui/widget/ScreenView;->b(Landroid/view/MotionEvent;)V

    .line 1101
    :cond_1
    iget-boolean v2, p0, Lmiui/widget/ScreenView;->cR:Z

    if-nez v2, :cond_2

    iget v2, p0, Lmiui/widget/ScreenView;->cP:I

    if-eqz v2, :cond_3

    iget v2, p0, Lmiui/widget/ScreenView;->cP:I

    if-eq v2, v4, :cond_3

    :cond_2
    move v0, v1

    :cond_3
    return v0

    .line 1064
    :pswitch_0
    invoke-direct {p0, p1}, Lmiui/widget/ScreenView;->b(Landroid/view/MotionEvent;)V

    .line 1065
    iget v2, p0, Lmiui/widget/ScreenView;->cP:I

    if-nez v2, :cond_0

    invoke-direct {p0, p1}, Lmiui/widget/ScreenView;->a(Landroid/view/MotionEvent;)Z

    move-result v2

    if-eqz v2, :cond_0

    .line 1066
    invoke-direct {p0, p1, v1}, Lmiui/widget/ScreenView;->a(Landroid/view/MotionEvent;I)V

    goto :goto_0

    .line 1071
    :pswitch_1
    invoke-virtual {p1, v4}, Landroid/view/MotionEvent;->setAction(I)V

    .line 1072
    iget-object v2, p0, Lmiui/widget/ScreenView;->cO:Landroid/view/ScaleGestureDetector;

    invoke-virtual {v2, p1}, Landroid/view/ScaleGestureDetector;->onTouchEvent(Landroid/view/MotionEvent;)Z

    .line 1073
    invoke-virtual {p1, v0}, Landroid/view/MotionEvent;->setAction(I)V

    .line 1074
    iput-boolean v0, p0, Lmiui/widget/ScreenView;->cR:Z

    .line 1075
    iput-boolean v0, p0, Lmiui/widget/ScreenView;->cQ:Z

    .line 1077
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getX()F

    move-result v2

    iput v2, p0, Lmiui/widget/ScreenView;->mLastMotionX:F

    .line 1078
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getY()F

    move-result v2

    iput v2, p0, Lmiui/widget/ScreenView;->mLastMotionY:F

    .line 1079
    iget-object v2, p0, Lmiui/widget/ScreenView;->cL:Landroid/widget/Scroller;

    invoke-virtual {v2}, Landroid/widget/Scroller;->isFinished()Z

    move-result v2

    if-eqz v2, :cond_4

    .line 1080
    iput-boolean v1, p0, Lmiui/widget/ScreenView;->cS:Z

    goto :goto_0

    .line 1087
    :cond_4
    iget-object v2, p0, Lmiui/widget/ScreenView;->cL:Landroid/widget/Scroller;

    invoke-virtual {v2}, Landroid/widget/Scroller;->abortAnimation()V

    .line 1088
    invoke-direct {p0, p1, v1}, Lmiui/widget/ScreenView;->a(Landroid/view/MotionEvent;I)V

    goto :goto_0

    .line 1094
    :pswitch_2
    invoke-direct {p0, p1, v0}, Lmiui/widget/ScreenView;->a(Landroid/view/MotionEvent;I)V

    goto :goto_0

    .line 1062
    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_1
        :pswitch_2
        :pswitch_0
        :pswitch_2
    .end packed-switch
.end method

.method protected onLayout(ZIIII)V
    .locals 8

    .prologue
    const/4 v0, 0x0

    .line 966
    invoke-virtual {p0}, Lmiui/widget/ScreenView;->getScrollX()I

    move-result v1

    const/4 v2, 0x1

    invoke-direct {p0, v1, v2}, Lmiui/widget/ScreenView;->b(IZ)V

    .line 968
    invoke-virtual {p0}, Lmiui/widget/ScreenView;->getScreenCount()I

    move-result v2

    move v1, v0

    .line 970
    :goto_0
    if-ge v1, v2, :cond_1

    .line 971
    invoke-virtual {p0, v1}, Lmiui/widget/ScreenView;->getChildAt(I)Landroid/view/View;

    move-result-object v3

    .line 972
    invoke-virtual {v3}, Landroid/view/View;->getVisibility()I

    move-result v4

    const/16 v5, 0x8

    if-eq v4, v5, :cond_0

    .line 973
    invoke-virtual {p0}, Lmiui/widget/ScreenView;->getPaddingTop()I

    move-result v4

    iget v5, p0, Lmiui/widget/ScreenView;->cz:I

    add-int/2addr v4, v5

    invoke-virtual {v3}, Landroid/view/View;->getMeasuredWidth()I

    move-result v5

    add-int/2addr v5, v0

    invoke-virtual {p0}, Lmiui/widget/ScreenView;->getPaddingTop()I

    move-result v6

    iget v7, p0, Lmiui/widget/ScreenView;->cz:I

    add-int/2addr v6, v7

    invoke-virtual {v3}, Landroid/view/View;->getMeasuredHeight()I

    move-result v7

    add-int/2addr v6, v7

    invoke-virtual {v3, v0, v4, v5, v6}, Landroid/view/View;->layout(IIII)V

    .line 979
    invoke-virtual {v3}, Landroid/view/View;->getMeasuredWidth()I

    move-result v3

    add-int/2addr v0, v3

    .line 970
    :cond_0
    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    .line 982
    :cond_1
    iget-boolean v0, p0, Lmiui/widget/ScreenView;->cK:Z

    if-eqz v0, :cond_2

    iget v0, p0, Lmiui/widget/ScreenView;->mCurrentScreen:I

    iget v1, p0, Lmiui/widget/ScreenView;->mVisibleRange:I

    rem-int/2addr v0, v1

    if-lez v0, :cond_2

    .line 983
    iget v0, p0, Lmiui/widget/ScreenView;->mCurrentScreen:I

    iget v1, p0, Lmiui/widget/ScreenView;->mCurrentScreen:I

    iget v2, p0, Lmiui/widget/ScreenView;->mVisibleRange:I

    rem-int/2addr v1, v2

    sub-int/2addr v0, v1

    invoke-virtual {p0, v0}, Lmiui/widget/ScreenView;->setCurrentScreen(I)V

    .line 985
    :cond_2
    return-void
.end method

.method protected onMeasure(II)V
    .locals 13

    .prologue
    const/4 v12, 0x1

    const/4 v1, 0x0

    .line 894
    iput p1, p0, Lmiui/widget/ScreenView;->cE:I

    .line 895
    iput p2, p0, Lmiui/widget/ScreenView;->cF:I

    .line 898
    invoke-virtual {p0}, Lmiui/widget/ScreenView;->getScreenCount()I

    move-result v6

    move v0, v1

    move v2, v1

    move v3, v1

    .line 900
    :goto_0
    iget v4, p0, Lmiui/widget/ScreenView;->cn:I

    if-ge v0, v4, :cond_0

    .line 901
    add-int v4, v0, v6

    invoke-virtual {p0, v4}, Lmiui/widget/ScreenView;->getChildAt(I)Landroid/view/View;

    move-result-object v4

    .line 902
    invoke-virtual {v4}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v5

    .line 903
    invoke-virtual {p0}, Lmiui/widget/ScreenView;->getPaddingLeft()I

    move-result v7

    invoke-virtual {p0}, Lmiui/widget/ScreenView;->getPaddingRight()I

    move-result v8

    add-int/2addr v7, v8

    iget v8, v5, Landroid/view/ViewGroup$LayoutParams;->width:I

    invoke-static {p1, v7, v8}, Lmiui/widget/ScreenView;->getChildMeasureSpec(III)I

    move-result v7

    .line 906
    invoke-virtual {p0}, Lmiui/widget/ScreenView;->getPaddingTop()I

    move-result v8

    iget v9, p0, Lmiui/widget/ScreenView;->cz:I

    add-int/2addr v8, v9

    invoke-virtual {p0}, Lmiui/widget/ScreenView;->getPaddingBottom()I

    move-result v9

    add-int/2addr v8, v9

    iget v9, p0, Lmiui/widget/ScreenView;->cA:I

    add-int/2addr v8, v9

    iget v5, v5, Landroid/view/ViewGroup$LayoutParams;->height:I

    invoke-static {p2, v8, v5}, Lmiui/widget/ScreenView;->getChildMeasureSpec(III)I

    move-result v5

    .line 911
    invoke-virtual {v4, v7, v5}, Landroid/view/View;->measure(II)V

    .line 912
    invoke-virtual {v4}, Landroid/view/View;->getMeasuredWidth()I

    move-result v5

    invoke-static {v2, v5}, Ljava/lang/Math;->max(II)I

    move-result v2

    .line 913
    invoke-virtual {v4}, Landroid/view/View;->getMeasuredHeight()I

    move-result v4

    invoke-static {v3, v4}, Ljava/lang/Math;->max(II)I

    move-result v3

    .line 900
    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    :cond_0
    move v0, v1

    move v4, v1

    move v5, v1

    .line 918
    :goto_1
    if-ge v0, v6, :cond_1

    .line 919
    invoke-virtual {p0, v0}, Lmiui/widget/ScreenView;->getChildAt(I)Landroid/view/View;

    move-result-object v7

    .line 920
    invoke-virtual {v7}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v8

    .line 921
    invoke-virtual {p0}, Lmiui/widget/ScreenView;->getPaddingLeft()I

    move-result v9

    invoke-virtual {p0}, Lmiui/widget/ScreenView;->getPaddingRight()I

    move-result v10

    add-int/2addr v9, v10

    iget v10, v8, Landroid/view/ViewGroup$LayoutParams;->width:I

    invoke-static {p1, v9, v10}, Lmiui/widget/ScreenView;->getChildMeasureSpec(III)I

    move-result v9

    .line 924
    invoke-virtual {p0}, Lmiui/widget/ScreenView;->getPaddingTop()I

    move-result v10

    iget v11, p0, Lmiui/widget/ScreenView;->cz:I

    add-int/2addr v10, v11

    invoke-virtual {p0}, Lmiui/widget/ScreenView;->getPaddingBottom()I

    move-result v11

    add-int/2addr v10, v11

    iget v11, p0, Lmiui/widget/ScreenView;->cA:I

    add-int/2addr v10, v11

    iget v8, v8, Landroid/view/ViewGroup$LayoutParams;->height:I

    invoke-static {p2, v10, v8}, Lmiui/widget/ScreenView;->getChildMeasureSpec(III)I

    move-result v8

    .line 929
    invoke-virtual {v7, v9, v8}, Landroid/view/View;->measure(II)V

    .line 930
    invoke-virtual {v7}, Landroid/view/View;->getMeasuredWidth()I

    move-result v8

    invoke-static {v4, v8}, Ljava/lang/Math;->max(II)I

    move-result v4

    .line 931
    invoke-virtual {v7}, Landroid/view/View;->getMeasuredHeight()I

    move-result v7

    invoke-static {v5, v7}, Ljava/lang/Math;->max(II)I

    move-result v5

    .line 918
    add-int/lit8 v0, v0, 0x1

    goto :goto_1

    .line 934
    :cond_1
    invoke-static {v4, v2}, Ljava/lang/Math;->max(II)I

    move-result v0

    .line 935
    invoke-static {v5, v3}, Ljava/lang/Math;->max(II)I

    move-result v2

    .line 937
    invoke-virtual {p0}, Lmiui/widget/ScreenView;->getPaddingLeft()I

    move-result v3

    invoke-virtual {p0}, Lmiui/widget/ScreenView;->getPaddingRight()I

    move-result v5

    add-int/2addr v3, v5

    add-int/2addr v0, v3

    .line 938
    invoke-virtual {p0}, Lmiui/widget/ScreenView;->getPaddingTop()I

    move-result v3

    iget v5, p0, Lmiui/widget/ScreenView;->cz:I

    add-int/2addr v3, v5

    invoke-virtual {p0}, Lmiui/widget/ScreenView;->getPaddingBottom()I

    move-result v5

    add-int/2addr v3, v5

    iget v5, p0, Lmiui/widget/ScreenView;->cA:I

    add-int/2addr v3, v5

    add-int/2addr v2, v3

    .line 940
    invoke-static {v0, p1}, Lmiui/widget/ScreenView;->resolveSize(II)I

    move-result v0

    invoke-static {v2, p2}, Lmiui/widget/ScreenView;->resolveSize(II)I

    move-result v2

    invoke-virtual {p0, v0, v2}, Lmiui/widget/ScreenView;->setMeasuredDimension(II)V

    .line 943
    if-lez v6, :cond_3

    .line 944
    iput v4, p0, Lmiui/widget/ScreenView;->mChildScreenWidth:I

    .line 945
    invoke-static {p1}, Landroid/view/View$MeasureSpec;->getSize(I)I

    move-result v0

    invoke-virtual {p0}, Lmiui/widget/ScreenView;->getPaddingLeft()I

    move-result v2

    sub-int/2addr v0, v2

    invoke-virtual {p0}, Lmiui/widget/ScreenView;->getPaddingRight()I

    move-result v2

    sub-int/2addr v0, v2

    iput v0, p0, Lmiui/widget/ScreenView;->cD:I

    .line 947
    invoke-direct {p0}, Lmiui/widget/ScreenView;->w()V

    .line 948
    iget v0, p0, Lmiui/widget/ScreenView;->mChildScreenWidth:I

    if-lez v0, :cond_2

    .line 949
    iget v0, p0, Lmiui/widget/ScreenView;->cD:I

    iget v2, p0, Lmiui/widget/ScreenView;->mChildScreenWidth:I

    int-to-float v2, v2

    iget v3, p0, Lmiui/widget/ScreenView;->cM:F

    mul-float/2addr v2, v3

    float-to-int v2, v2

    add-int/2addr v0, v2

    iget v2, p0, Lmiui/widget/ScreenView;->mChildScreenWidth:I

    div-int/2addr v0, v2

    invoke-static {v12, v0}, Ljava/lang/Math;->max(II)I

    move-result v0

    iput v0, p0, Lmiui/widget/ScreenView;->mVisibleRange:I

    .line 953
    :cond_2
    iget v0, p0, Lmiui/widget/ScreenView;->cJ:F

    invoke-virtual {p0, v0}, Lmiui/widget/ScreenView;->setOverScrollRatio(F)V

    .line 955
    :cond_3
    iget-boolean v0, p0, Lmiui/widget/ScreenView;->cm:Z

    if-eqz v0, :cond_4

    iget v0, p0, Lmiui/widget/ScreenView;->mVisibleRange:I

    if-lez v0, :cond_4

    .line 956
    iput-boolean v1, p0, Lmiui/widget/ScreenView;->cm:Z

    .line 957
    invoke-virtual {p0, v1}, Lmiui/widget/ScreenView;->setHorizontalScrollBarEnabled(Z)V

    .line 958
    iget v0, p0, Lmiui/widget/ScreenView;->mCurrentScreen:I

    invoke-virtual {p0, v0}, Lmiui/widget/ScreenView;->setCurrentScreen(I)V

    .line 959
    invoke-virtual {p0, v12}, Lmiui/widget/ScreenView;->setHorizontalScrollBarEnabled(Z)V

    .line 961
    :cond_4
    return-void
.end method

.method public onPause()V
    .locals 2

    .prologue
    .line 1637
    iget-object v0, p0, Lmiui/widget/ScreenView;->cL:Landroid/widget/Scroller;

    invoke-virtual {v0}, Landroid/widget/Scroller;->isFinished()Z

    move-result v0

    if-nez v0, :cond_0

    .line 1638
    iget-object v0, p0, Lmiui/widget/ScreenView;->cL:Landroid/widget/Scroller;

    invoke-virtual {v0}, Landroid/widget/Scroller;->getCurrX()I

    move-result v0

    iget v1, p0, Lmiui/widget/ScreenView;->mChildScreenWidth:I

    div-int/lit8 v1, v1, 0x2

    add-int/2addr v0, v1

    iget v1, p0, Lmiui/widget/ScreenView;->mChildScreenWidth:I

    div-int/2addr v0, v1

    int-to-double v0, v0

    invoke-static {v0, v1}, Ljava/lang/Math;->floor(D)D

    move-result-wide v0

    double-to-int v0, v0

    invoke-virtual {p0, v0}, Lmiui/widget/ScreenView;->setCurrentScreen(I)V

    .line 1641
    iget-object v0, p0, Lmiui/widget/ScreenView;->cL:Landroid/widget/Scroller;

    invoke-virtual {v0}, Landroid/widget/Scroller;->abortAnimation()V

    .line 1643
    :cond_0
    return-void
.end method

.method protected onPinchIn(Landroid/view/ScaleGestureDetector;)V
    .locals 0

    .prologue
    .line 1889
    return-void
.end method

.method protected onPinchOut(Landroid/view/ScaleGestureDetector;)V
    .locals 0

    .prologue
    .line 1898
    return-void
.end method

.method protected onRestoreInstanceState(Landroid/os/Parcelable;)V
    .locals 2

    .prologue
    .line 1916
    check-cast p1, Lmiui/widget/ScreenView$e;

    .line 1917
    invoke-virtual {p1}, Lmiui/widget/ScreenView$e;->getSuperState()Landroid/os/Parcelable;

    move-result-object v0

    invoke-super {p0, v0}, Landroid/view/ViewGroup;->onRestoreInstanceState(Landroid/os/Parcelable;)V

    .line 1918
    iget v0, p1, Lmiui/widget/ScreenView$e;->Dv:I

    const/4 v1, -0x1

    if-eq v0, v1, :cond_0

    .line 1919
    iget v0, p1, Lmiui/widget/ScreenView$e;->Dv:I

    invoke-virtual {p0, v0}, Lmiui/widget/ScreenView;->setCurrentScreen(I)V

    .line 1921
    :cond_0
    return-void
.end method

.method public onResume()V
    .locals 0

    .prologue
    .line 1630
    return-void
.end method

.method protected onSaveInstanceState()Landroid/os/Parcelable;
    .locals 2

    .prologue
    .line 1909
    new-instance v0, Lmiui/widget/ScreenView$e;

    invoke-super {p0}, Landroid/view/ViewGroup;->onSaveInstanceState()Landroid/os/Parcelable;

    move-result-object v1

    invoke-direct {v0, v1}, Lmiui/widget/ScreenView$e;-><init>(Landroid/os/Parcelable;)V

    .line 1910
    iget v1, p0, Lmiui/widget/ScreenView;->mCurrentScreen:I

    iput v1, v0, Lmiui/widget/ScreenView$e;->Dv:I

    .line 1911
    return-object v0
.end method

.method public onTouchEvent(Landroid/view/MotionEvent;)Z
    .locals 5

    .prologue
    const/4 v2, 0x0

    const/4 v1, 0x1

    .line 1123
    iget-boolean v0, p0, Lmiui/widget/ScreenView;->cR:Z

    if-eqz v0, :cond_0

    .line 1177
    :goto_0
    return v1

    .line 1126
    :cond_0
    iget-boolean v0, p0, Lmiui/widget/ScreenView;->cQ:Z

    if-eqz v0, :cond_1

    .line 1127
    invoke-direct {p0, p1}, Lmiui/widget/ScreenView;->b(Landroid/view/MotionEvent;)V

    .line 1129
    :cond_1
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getAction()I

    move-result v0

    and-int/lit16 v0, v0, 0xff

    packed-switch v0, :pswitch_data_0

    .line 1176
    :cond_2
    :goto_1
    :pswitch_0
    iput-boolean v1, p0, Lmiui/widget/ScreenView;->cQ:Z

    goto :goto_0

    .line 1133
    :pswitch_1
    iget v0, p0, Lmiui/widget/ScreenView;->cP:I

    if-nez v0, :cond_3

    invoke-direct {p0, p1}, Lmiui/widget/ScreenView;->a(Landroid/view/MotionEvent;)Z

    move-result v0

    if-eqz v0, :cond_3

    .line 1134
    invoke-direct {p0, p1, v1}, Lmiui/widget/ScreenView;->a(Landroid/view/MotionEvent;I)V

    .line 1136
    :cond_3
    iget v0, p0, Lmiui/widget/ScreenView;->cP:I

    if-ne v0, v1, :cond_2

    .line 1138
    iget v0, p0, Lmiui/widget/ScreenView;->cV:I

    invoke-virtual {p1, v0}, Landroid/view/MotionEvent;->findPointerIndex(I)I

    move-result v0

    .line 1139
    const/4 v3, -0x1

    if-ne v0, v3, :cond_4

    .line 1140
    invoke-direct {p0, p1, v1}, Lmiui/widget/ScreenView;->a(Landroid/view/MotionEvent;I)V

    .line 1141
    iget v0, p0, Lmiui/widget/ScreenView;->cV:I

    invoke-virtual {p1, v0}, Landroid/view/MotionEvent;->findPointerIndex(I)I

    move-result v0

    .line 1143
    :cond_4
    invoke-virtual {p1, v0}, Landroid/view/MotionEvent;->getX(I)F

    move-result v0

    .line 1144
    iget v3, p0, Lmiui/widget/ScreenView;->mLastMotionX:F

    sub-float/2addr v3, v0

    .line 1145
    iput v0, p0, Lmiui/widget/ScreenView;->mLastMotionX:F

    .line 1146
    const/4 v0, 0x0

    cmpl-float v0, v3, v0

    if-eqz v0, :cond_5

    .line 1147
    iget v0, p0, Lmiui/widget/ScreenView;->cX:F

    add-float/2addr v0, v3

    invoke-static {v0}, Ljava/lang/Math;->round(F)I

    move-result v0

    invoke-virtual {p0, v0, v2}, Lmiui/widget/ScreenView;->scrollTo(II)V

    goto :goto_1

    .line 1149
    :cond_5
    invoke-virtual {p0}, Lmiui/widget/ScreenView;->awakenScrollBars()Z

    goto :goto_1

    .line 1155
    :pswitch_2
    iget v0, p0, Lmiui/widget/ScreenView;->cP:I

    if-ne v0, v1, :cond_6

    .line 1156
    iget v0, p0, Lmiui/widget/ScreenView;->cV:I

    invoke-direct {p0, v0}, Lmiui/widget/ScreenView;->q(I)V

    .line 1158
    :cond_6
    invoke-direct {p0, p1, v2}, Lmiui/widget/ScreenView;->a(Landroid/view/MotionEvent;I)V

    goto :goto_1

    .line 1161
    :pswitch_3
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getAction()I

    move-result v0

    const v3, 0xff00

    and-int/2addr v0, v3

    shr-int/lit8 v0, v0, 0x8

    .line 1164
    invoke-virtual {p1, v0}, Landroid/view/MotionEvent;->getPointerId(I)I

    move-result v3

    .line 1165
    iget v4, p0, Lmiui/widget/ScreenView;->cV:I

    if-ne v3, v4, :cond_2

    .line 1169
    if-nez v0, :cond_7

    move v0, v1

    .line 1170
    :goto_2
    invoke-virtual {p1, v0}, Landroid/view/MotionEvent;->getX(I)F

    move-result v2

    iput v2, p0, Lmiui/widget/ScreenView;->mLastMotionX:F

    .line 1171
    invoke-virtual {p1, v0}, Landroid/view/MotionEvent;->getPointerId(I)I

    move-result v0

    iput v0, p0, Lmiui/widget/ScreenView;->cV:I

    .line 1172
    iget-object v0, p0, Lmiui/widget/ScreenView;->de:Lmiui/widget/ScreenView$a;

    iget v2, p0, Lmiui/widget/ScreenView;->cV:I

    invoke-virtual {v0, v2}, Lmiui/widget/ScreenView$a;->init(I)V

    goto :goto_1

    :cond_7
    move v0, v2

    .line 1169
    goto :goto_2

    .line 1129
    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_0
        :pswitch_2
        :pswitch_1
        :pswitch_2
        :pswitch_0
        :pswitch_0
        :pswitch_3
    .end packed-switch
.end method

.method public removeAllScreens()V
    .locals 2

    .prologue
    .line 1540
    const/4 v0, 0x0

    invoke-virtual {p0}, Lmiui/widget/ScreenView;->getScreenCount()I

    move-result v1

    invoke-virtual {p0, v0, v1}, Lmiui/widget/ScreenView;->removeScreensInLayout(II)V

    .line 1541
    invoke-virtual {p0}, Lmiui/widget/ScreenView;->requestLayout()V

    .line 1542
    invoke-virtual {p0}, Lmiui/widget/ScreenView;->invalidate()V

    .line 1543
    return-void
.end method

.method public removeAllViewsInLayout()V
    .locals 1

    .prologue
    .line 1458
    const/4 v0, 0x0

    iput v0, p0, Lmiui/widget/ScreenView;->cn:I

    iput v0, p0, Lmiui/widget/ScreenView;->cN:I

    .line 1459
    invoke-super {p0}, Landroid/view/ViewGroup;->removeAllViewsInLayout()V

    .line 1460
    return-void
.end method

.method protected removeIndicator(Landroid/view/View;)V
    .locals 2

    .prologue
    .line 1499
    invoke-virtual {p0, p1}, Lmiui/widget/ScreenView;->indexOfChild(Landroid/view/View;)I

    move-result v0

    .line 1500
    invoke-virtual {p0}, Lmiui/widget/ScreenView;->getScreenCount()I

    move-result v1

    if-ge v0, v1, :cond_0

    .line 1501
    new-instance v0, Ljava/security/InvalidParameterException;

    const-string v1, "The view passed through the parameter must be indicator."

    invoke-direct {v0, v1}, Ljava/security/InvalidParameterException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 1504
    :cond_0
    iget v1, p0, Lmiui/widget/ScreenView;->cn:I

    add-int/lit8 v1, v1, -0x1

    iput v1, p0, Lmiui/widget/ScreenView;->cn:I

    .line 1505
    invoke-super {p0, v0}, Landroid/view/ViewGroup;->removeViewAt(I)V

    .line 1506
    return-void
.end method

.method public removeScreen(I)V
    .locals 2

    .prologue
    .line 1515
    invoke-virtual {p0}, Lmiui/widget/ScreenView;->getScreenCount()I

    move-result v0

    if-lt p1, v0, :cond_0

    .line 1516
    new-instance v0, Ljava/security/InvalidParameterException;

    const-string v1, "The view specified by the index must be a screen."

    invoke-direct {v0, v1}, Ljava/security/InvalidParameterException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 1519
    :cond_0
    iget v0, p0, Lmiui/widget/ScreenView;->mCurrentScreen:I

    if-ne p1, v0, :cond_1

    .line 1520
    iget-boolean v0, p0, Lmiui/widget/ScreenView;->cK:Z

    if-nez v0, :cond_3

    .line 1521
    const/4 v0, 0x0

    add-int/lit8 v1, p1, -0x1

    invoke-static {v0, v1}, Ljava/lang/Math;->max(II)I

    move-result v0

    invoke-virtual {p0, v0}, Lmiui/widget/ScreenView;->setCurrentScreen(I)V

    .line 1529
    :cond_1
    :goto_0
    iget-object v0, p0, Lmiui/widget/ScreenView;->cv:Lmiui/widget/ScreenView$b;

    if-eqz v0, :cond_2

    .line 1530
    iget-object v0, p0, Lmiui/widget/ScreenView;->cv:Lmiui/widget/ScreenView$b;

    invoke-virtual {v0, p1}, Lmiui/widget/ScreenView$b;->removeViewAt(I)V

    .line 1532
    :cond_2
    iget v0, p0, Lmiui/widget/ScreenView;->cN:I

    add-int/lit8 v0, v0, -0x1

    iput v0, p0, Lmiui/widget/ScreenView;->cN:I

    .line 1533
    invoke-super {p0, p1}, Landroid/view/ViewGroup;->removeViewAt(I)V

    .line 1534
    return-void

    .line 1523
    :cond_3
    if-eqz p1, :cond_1

    invoke-virtual {p0}, Lmiui/widget/ScreenView;->getScreenCount()I

    move-result v0

    add-int/lit8 v0, v0, -0x1

    if-ne p1, v0, :cond_1

    .line 1525
    add-int/lit8 v0, p1, -0x1

    invoke-virtual {p0, v0}, Lmiui/widget/ScreenView;->snapToScreen(I)V

    goto :goto_0
.end method

.method public removeScreensInLayout(II)V
    .locals 2

    .prologue
    .line 1553
    if-ltz p1, :cond_0

    invoke-virtual {p0}, Lmiui/widget/ScreenView;->getScreenCount()I

    move-result v0

    if-lt p1, v0, :cond_1

    .line 1562
    :cond_0
    :goto_0
    return-void

    .line 1556
    :cond_1
    invoke-virtual {p0}, Lmiui/widget/ScreenView;->getScreenCount()I

    move-result v0

    sub-int/2addr v0, p1

    invoke-static {p2, v0}, Ljava/lang/Math;->min(II)I

    move-result v0

    .line 1557
    iget-object v1, p0, Lmiui/widget/ScreenView;->cv:Lmiui/widget/ScreenView$b;

    if-eqz v1, :cond_2

    .line 1558
    iget-object v1, p0, Lmiui/widget/ScreenView;->cv:Lmiui/widget/ScreenView$b;

    invoke-virtual {v1, p1, v0}, Lmiui/widget/ScreenView$b;->removeViewsInLayout(II)V

    .line 1560
    :cond_2
    const/4 v1, 0x0

    iput v1, p0, Lmiui/widget/ScreenView;->cN:I

    .line 1561
    invoke-super {p0, p1, v0}, Landroid/view/ViewGroup;->removeViewsInLayout(II)V

    goto :goto_0
.end method

.method public removeView(Landroid/view/View;)V
    .locals 2

    .prologue
    .line 1412
    new-instance v0, Ljava/lang/UnsupportedOperationException;

    const-string v1, "ScreenView doesn\'t support remove view directly."

    invoke-direct {v0, v1}, Ljava/lang/UnsupportedOperationException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

.method public removeViewAt(I)V
    .locals 2

    .prologue
    .line 1442
    new-instance v0, Ljava/lang/UnsupportedOperationException;

    const-string v1, "ScreenView doesn\'t support remove view directly."

    invoke-direct {v0, v1}, Ljava/lang/UnsupportedOperationException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

.method public removeViewInLayout(Landroid/view/View;)V
    .locals 2

    .prologue
    .line 1422
    new-instance v0, Ljava/lang/UnsupportedOperationException;

    const-string v1, "ScreenView doesn\'t support remove view directly."

    invoke-direct {v0, v1}, Ljava/lang/UnsupportedOperationException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

.method public removeViews(II)V
    .locals 2

    .prologue
    .line 1452
    new-instance v0, Ljava/lang/UnsupportedOperationException;

    const-string v1, "ScreenView doesn\'t support remove view directly."

    invoke-direct {v0, v1}, Ljava/lang/UnsupportedOperationException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

.method public removeViewsInLayout(II)V
    .locals 2

    .prologue
    .line 1432
    new-instance v0, Ljava/lang/UnsupportedOperationException;

    const-string v1, "ScreenView doesn\'t support remove view directly."

    invoke-direct {v0, v1}, Ljava/lang/UnsupportedOperationException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

.method public requestChildRectangleOnScreen(Landroid/view/View;Landroid/graphics/Rect;Z)Z
    .locals 2

    .prologue
    .line 996
    invoke-virtual {p0, p1}, Lmiui/widget/ScreenView;->indexOfChild(Landroid/view/View;)I

    move-result v0

    .line 997
    invoke-virtual {p0}, Lmiui/widget/ScreenView;->getScreenCount()I

    move-result v1

    if-ge v0, v1, :cond_2

    .line 998
    iget v1, p0, Lmiui/widget/ScreenView;->mCurrentScreen:I

    if-ne v0, v1, :cond_0

    iget-object v1, p0, Lmiui/widget/ScreenView;->cL:Landroid/widget/Scroller;

    invoke-virtual {v1}, Landroid/widget/Scroller;->isFinished()Z

    move-result v1

    if-nez v1, :cond_1

    .line 999
    :cond_0
    invoke-virtual {p0, v0}, Lmiui/widget/ScreenView;->snapToScreen(I)V

    .line 1000
    const/4 v0, 0x1

    .line 1004
    :goto_0
    return v0

    .line 1002
    :cond_1
    const/4 v0, 0x0

    goto :goto_0

    .line 1004
    :cond_2
    invoke-super {p0, p1, p2, p3}, Landroid/view/ViewGroup;->requestChildRectangleOnScreen(Landroid/view/View;Landroid/graphics/Rect;Z)Z

    move-result v0

    goto :goto_0
.end method

.method protected resetTransformation(Landroid/view/View;)V
    .locals 2

    .prologue
    const/high16 v1, 0x3f800000

    const/4 v0, 0x0

    .line 1722
    invoke-virtual {p1, v1}, Landroid/view/View;->setAlpha(F)V

    .line 1723
    invoke-virtual {p1, v0}, Landroid/view/View;->setTranslationX(F)V

    .line 1724
    invoke-virtual {p1, v0}, Landroid/view/View;->setTranslationY(F)V

    .line 1725
    invoke-virtual {p1, v0}, Landroid/view/View;->setPivotX(F)V

    .line 1726
    invoke-virtual {p1, v0}, Landroid/view/View;->setPivotY(F)V

    .line 1727
    invoke-virtual {p1, v0}, Landroid/view/View;->setRotation(F)V

    .line 1728
    invoke-virtual {p1, v0}, Landroid/view/View;->setRotationX(F)V

    .line 1729
    invoke-virtual {p1, v0}, Landroid/view/View;->setRotationY(F)V

    .line 1730
    iget v0, p0, Lmiui/widget/ScreenView;->cl:F

    invoke-virtual {p1, v0}, Landroid/view/View;->setCameraDistance(F)V

    .line 1731
    invoke-virtual {p1, v1}, Landroid/view/View;->setScaleX(F)V

    .line 1732
    invoke-virtual {p1, v1}, Landroid/view/View;->setScaleY(F)V

    .line 1733
    return-void
.end method

.method public scrollTo(II)V
    .locals 2

    .prologue
    .line 838
    iget v0, p0, Lmiui/widget/ScreenView;->cI:I

    iget v1, p0, Lmiui/widget/ScreenView;->cH:I

    invoke-static {p1, v1}, Ljava/lang/Math;->min(II)I

    move-result v1

    invoke-static {v0, v1}, Ljava/lang/Math;->max(II)I

    move-result v0

    .line 839
    int-to-float v0, v0

    iput v0, p0, Lmiui/widget/ScreenView;->cX:F

    .line 840
    invoke-static {}, Ljava/lang/System;->nanoTime()J

    move-result-wide v0

    long-to-float v0, v0

    const v1, 0x4e6e6b28

    div-float/2addr v0, v1

    iput v0, p0, Lmiui/widget/ScreenView;->cW:F

    .line 841
    iget v0, p0, Lmiui/widget/ScreenView;->cX:F

    float-to-int v0, v0

    invoke-super {p0, v0, p2}, Landroid/view/ViewGroup;->scrollTo(II)V

    .line 842
    return-void
.end method

.method public scrollToScreen(I)V
    .locals 2

    .prologue
    .line 829
    iget-boolean v0, p0, Lmiui/widget/ScreenView;->cK:Z

    if-eqz v0, :cond_0

    .line 830
    iget v0, p0, Lmiui/widget/ScreenView;->mVisibleRange:I

    rem-int v0, p1, v0

    sub-int/2addr p1, v0

    .line 832
    :cond_0
    iget v0, p0, Lmiui/widget/ScreenView;->cE:I

    iget v1, p0, Lmiui/widget/ScreenView;->cF:I

    invoke-virtual {p0, v0, v1}, Lmiui/widget/ScreenView;->measure(II)V

    .line 833
    iget v0, p0, Lmiui/widget/ScreenView;->mChildScreenWidth:I

    mul-int/2addr v0, p1

    iget v1, p0, Lmiui/widget/ScreenView;->cC:I

    sub-int/2addr v0, v1

    const/4 v1, 0x0

    invoke-virtual {p0, v0, v1}, Lmiui/widget/ScreenView;->scrollTo(II)V

    .line 834
    return-void
.end method

.method public setAllowLongPress(Z)V
    .locals 0

    .prologue
    .line 1576
    iput-boolean p1, p0, Lmiui/widget/ScreenView;->cS:Z

    .line 1577
    return-void
.end method

.method public setArrowIndicatorMarginRect(Landroid/graphics/Rect;)V
    .locals 6

    .prologue
    const/4 v1, 0x0

    const/4 v5, 0x0

    const/4 v3, -0x2

    .line 444
    if-eqz p1, :cond_2

    .line 447
    iget-object v0, p0, Lmiui/widget/ScreenView;->ct:Lmiui/widget/ScreenView$g;

    if-nez v0, :cond_1

    .line 448
    new-instance v0, Landroid/widget/FrameLayout$LayoutParams;

    const/16 v1, 0x13

    invoke-direct {v0, v3, v3, v1}, Landroid/widget/FrameLayout$LayoutParams;-><init>(III)V

    .line 452
    new-instance v1, Lmiui/widget/ScreenView$g;

    invoke-virtual {p0}, Lmiui/widget/ScreenView;->getContext()Landroid/content/Context;

    move-result-object v2

    invoke-direct {v1, p0, v2}, Lmiui/widget/ScreenView$g;-><init>(Lmiui/widget/ScreenView;Landroid/content/Context;)V

    iput-object v1, p0, Lmiui/widget/ScreenView;->ct:Lmiui/widget/ScreenView$g;

    .line 453
    iget-object v1, p0, Lmiui/widget/ScreenView;->ct:Lmiui/widget/ScreenView$g;

    iget v2, p0, Lmiui/widget/ScreenView;->co:I

    invoke-virtual {v1, v2}, Lmiui/widget/ScreenView$g;->setImageResource(I)V

    .line 454
    iget-object v1, p0, Lmiui/widget/ScreenView;->ct:Lmiui/widget/ScreenView$g;

    invoke-virtual {p0, v1, v0}, Lmiui/widget/ScreenView;->addIndicator(Landroid/view/View;Landroid/widget/FrameLayout$LayoutParams;)V

    .line 455
    new-instance v1, Landroid/widget/FrameLayout$LayoutParams;

    const/16 v2, 0x15

    invoke-direct {v1, v3, v3, v2}, Landroid/widget/FrameLayout$LayoutParams;-><init>(III)V

    .line 459
    new-instance v2, Lmiui/widget/ScreenView$g;

    invoke-virtual {p0}, Lmiui/widget/ScreenView;->getContext()Landroid/content/Context;

    move-result-object v3

    invoke-direct {v2, p0, v3}, Lmiui/widget/ScreenView$g;-><init>(Lmiui/widget/ScreenView;Landroid/content/Context;)V

    iput-object v2, p0, Lmiui/widget/ScreenView;->cu:Lmiui/widget/ScreenView$g;

    .line 460
    iget-object v2, p0, Lmiui/widget/ScreenView;->cu:Lmiui/widget/ScreenView$g;

    iget v3, p0, Lmiui/widget/ScreenView;->cq:I

    invoke-virtual {v2, v3}, Lmiui/widget/ScreenView$g;->setImageResource(I)V

    .line 461
    iget-object v2, p0, Lmiui/widget/ScreenView;->cu:Lmiui/widget/ScreenView$g;

    invoke-virtual {p0, v2, v1}, Lmiui/widget/ScreenView;->addIndicator(Landroid/view/View;Landroid/widget/FrameLayout$LayoutParams;)V

    .line 468
    :goto_0
    iget v2, p1, Landroid/graphics/Rect;->left:I

    iget v3, p1, Landroid/graphics/Rect;->top:I

    iget v4, p1, Landroid/graphics/Rect;->bottom:I

    invoke-virtual {v0, v2, v3, v5, v4}, Landroid/widget/FrameLayout$LayoutParams;->setMargins(IIII)V

    .line 470
    iget v0, p1, Landroid/graphics/Rect;->top:I

    iget v2, p1, Landroid/graphics/Rect;->right:I

    iget v3, p1, Landroid/graphics/Rect;->bottom:I

    invoke-virtual {v1, v5, v0, v2, v3}, Landroid/widget/FrameLayout$LayoutParams;->setMargins(IIII)V

    .line 478
    :cond_0
    :goto_1
    return-void

    .line 463
    :cond_1
    iget-object v0, p0, Lmiui/widget/ScreenView;->ct:Lmiui/widget/ScreenView$g;

    invoke-virtual {v0}, Lmiui/widget/ScreenView$g;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v0

    check-cast v0, Landroid/widget/FrameLayout$LayoutParams;

    .line 465
    iget-object v1, p0, Lmiui/widget/ScreenView;->cu:Lmiui/widget/ScreenView$g;

    invoke-virtual {v1}, Lmiui/widget/ScreenView$g;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v1

    check-cast v1, Landroid/widget/FrameLayout$LayoutParams;

    goto :goto_0

    .line 472
    :cond_2
    iget-object v0, p0, Lmiui/widget/ScreenView;->ct:Lmiui/widget/ScreenView$g;

    if-eqz v0, :cond_0

    .line 473
    iget-object v0, p0, Lmiui/widget/ScreenView;->ct:Lmiui/widget/ScreenView$g;

    invoke-virtual {p0, v0}, Lmiui/widget/ScreenView;->removeIndicator(Landroid/view/View;)V

    .line 474
    iget-object v0, p0, Lmiui/widget/ScreenView;->cu:Lmiui/widget/ScreenView$g;

    invoke-virtual {p0, v0}, Lmiui/widget/ScreenView;->removeIndicator(Landroid/view/View;)V

    .line 475
    iput-object v1, p0, Lmiui/widget/ScreenView;->ct:Lmiui/widget/ScreenView$g;

    .line 476
    iput-object v1, p0, Lmiui/widget/ScreenView;->cu:Lmiui/widget/ScreenView$g;

    goto :goto_1
.end method

.method public setArrowIndicatorResource(IIII)V
    .locals 0

    .prologue
    .line 487
    iput p1, p0, Lmiui/widget/ScreenView;->co:I

    .line 488
    iput p2, p0, Lmiui/widget/ScreenView;->cp:I

    .line 489
    iput p3, p0, Lmiui/widget/ScreenView;->cq:I

    .line 490
    iput p4, p0, Lmiui/widget/ScreenView;->cr:I

    .line 491
    return-void
.end method

.method public setConfirmHorizontalScrollRatio(F)V
    .locals 0

    .prologue
    .line 388
    iput p1, p0, Lmiui/widget/ScreenView;->cY:F

    .line 389
    return-void
.end method

.method public setCurrentScreen(I)V
    .locals 3

    .prologue
    const/4 v2, 0x0

    .line 1333
    iget-boolean v0, p0, Lmiui/widget/ScreenView;->cK:Z

    if-eqz v0, :cond_2

    .line 1334
    invoke-virtual {p0}, Lmiui/widget/ScreenView;->getScreenCount()I

    move-result v0

    add-int/lit8 v0, v0, -0x1

    invoke-static {p1, v0}, Ljava/lang/Math;->min(II)I

    move-result v0

    invoke-static {v2, v0}, Ljava/lang/Math;->max(II)I

    move-result v0

    .line 1336
    iget v1, p0, Lmiui/widget/ScreenView;->mVisibleRange:I

    rem-int v1, v0, v1

    sub-int/2addr v0, v1

    .line 1341
    :goto_0
    invoke-direct {p0, v0}, Lmiui/widget/ScreenView;->r(I)V

    .line 1342
    iget-boolean v0, p0, Lmiui/widget/ScreenView;->cm:Z

    if-nez v0, :cond_1

    .line 1343
    iget-object v0, p0, Lmiui/widget/ScreenView;->cL:Landroid/widget/Scroller;

    invoke-virtual {v0}, Landroid/widget/Scroller;->isFinished()Z

    move-result v0

    if-nez v0, :cond_0

    .line 1344
    iget-object v0, p0, Lmiui/widget/ScreenView;->cL:Landroid/widget/Scroller;

    invoke-virtual {v0}, Landroid/widget/Scroller;->abortAnimation()V

    .line 1346
    :cond_0
    iget v0, p0, Lmiui/widget/ScreenView;->mCurrentScreen:I

    invoke-virtual {p0, v0}, Lmiui/widget/ScreenView;->scrollToScreen(I)V

    .line 1347
    invoke-virtual {p0}, Lmiui/widget/ScreenView;->invalidate()V

    .line 1349
    :cond_1
    return-void

    .line 1338
    :cond_2
    invoke-virtual {p0}, Lmiui/widget/ScreenView;->getScreenCount()I

    move-result v0

    iget v1, p0, Lmiui/widget/ScreenView;->mVisibleRange:I

    sub-int/2addr v0, v1

    invoke-static {p1, v0}, Ljava/lang/Math;->min(II)I

    move-result v0

    invoke-static {v2, v0}, Ljava/lang/Math;->max(II)I

    move-result v0

    goto :goto_0
.end method

.method public setIndicatorBarVisibility(I)V
    .locals 0

    .prologue
    .line 598
    invoke-virtual {p0, p1}, Lmiui/widget/ScreenView;->setSeekBarVisibility(I)V

    .line 599
    invoke-virtual {p0, p1}, Lmiui/widget/ScreenView;->setSlideBarVisibility(I)V

    .line 600
    return-void
.end method

.method public setMaximumSnapVelocity(I)V
    .locals 0

    .prologue
    .line 407
    iput p1, p0, Lmiui/widget/ScreenView;->cU:I

    .line 408
    return-void
.end method

.method public setOnLongClickListener(Landroid/view/View$OnLongClickListener;)V
    .locals 3

    .prologue
    .line 1587
    invoke-virtual {p0}, Lmiui/widget/ScreenView;->getScreenCount()I

    move-result v1

    .line 1588
    const/4 v0, 0x0

    :goto_0
    if-ge v0, v1, :cond_0

    .line 1589
    invoke-virtual {p0, v0}, Lmiui/widget/ScreenView;->getChildAt(I)Landroid/view/View;

    move-result-object v2

    invoke-virtual {v2, p1}, Landroid/view/View;->setOnLongClickListener(Landroid/view/View$OnLongClickListener;)V

    .line 1588
    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    .line 1591
    :cond_0
    return-void
.end method

.method public setOverScrollRatio(F)V
    .locals 0

    .prologue
    .line 795
    iput p1, p0, Lmiui/widget/ScreenView;->cJ:F

    .line 796
    invoke-direct {p0}, Lmiui/widget/ScreenView;->x()V

    .line 797
    return-void
.end method

.method public setOvershootTension(F)V
    .locals 1

    .prologue
    .line 365
    iput p1, p0, Lmiui/widget/ScreenView;->dc:F

    .line 366
    iget-object v0, p0, Lmiui/widget/ScreenView;->da:Lmiui/widget/ScreenView$i;

    if-eqz v0, :cond_0

    .line 367
    iget-object v0, p0, Lmiui/widget/ScreenView;->da:Lmiui/widget/ScreenView$i;

    invoke-static {v0, p1}, Lmiui/widget/ScreenView$i;->a(Lmiui/widget/ScreenView$i;F)F

    .line 369
    :cond_0
    return-void
.end method

.method public setScreenAlignment(I)V
    .locals 0

    .prologue
    .line 657
    iput p1, p0, Lmiui/widget/ScreenView;->cB:I

    .line 658
    return-void
.end method

.method public setScreenOffset(I)V
    .locals 1

    .prologue
    .line 669
    iput p1, p0, Lmiui/widget/ScreenView;->mScreenOffset:I

    .line 670
    const/4 v0, 0x0

    iput v0, p0, Lmiui/widget/ScreenView;->cB:I

    .line 671
    invoke-virtual {p0}, Lmiui/widget/ScreenView;->requestLayout()V

    .line 672
    return-void
.end method

.method public setScreenPadding(Landroid/graphics/Rect;)V
    .locals 3

    .prologue
    const/4 v2, 0x0

    .line 638
    if-nez p1, :cond_0

    .line 639
    new-instance v0, Ljava/security/InvalidParameterException;

    const-string v1, "The padding parameter can not be null."

    invoke-direct {v0, v1}, Ljava/security/InvalidParameterException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 642
    :cond_0
    iget v0, p1, Landroid/graphics/Rect;->top:I

    iput v0, p0, Lmiui/widget/ScreenView;->cz:I

    .line 643
    iget v0, p1, Landroid/graphics/Rect;->bottom:I

    iput v0, p0, Lmiui/widget/ScreenView;->cA:I

    .line 644
    iget v0, p1, Landroid/graphics/Rect;->left:I

    iget v1, p1, Landroid/graphics/Rect;->right:I

    invoke-virtual {p0, v0, v2, v1, v2}, Lmiui/widget/ScreenView;->setPadding(IIII)V

    .line 645
    return-void
.end method

.method public setScreenSnapDuration(I)V
    .locals 0

    .prologue
    .line 397
    iput p1, p0, Lmiui/widget/ScreenView;->cZ:I

    .line 398
    return-void
.end method

.method public setScreenTransitionType(I)V
    .locals 6

    .prologue
    const/16 v5, 0x12c

    const/16 v4, 0x10e

    const/16 v3, 0x14a

    const v2, 0x3fa66666

    const/4 v1, 0x0

    .line 1671
    iget v0, p0, Lmiui/widget/ScreenView;->db:I

    if-eq p1, v0, :cond_0

    .line 1672
    iput p1, p0, Lmiui/widget/ScreenView;->db:I

    .line 1673
    iget v0, p0, Lmiui/widget/ScreenView;->db:I

    packed-switch v0, :pswitch_data_0

    .line 1714
    :cond_0
    :goto_0
    :pswitch_0
    return-void

    .line 1675
    :pswitch_1
    invoke-virtual {p0, v2}, Lmiui/widget/ScreenView;->setOvershootTension(F)V

    .line 1676
    invoke-virtual {p0, v5}, Lmiui/widget/ScreenView;->setScreenSnapDuration(I)V

    goto :goto_0

    .line 1680
    :pswitch_2
    invoke-virtual {p0, v1}, Lmiui/widget/ScreenView;->setOvershootTension(F)V

    .line 1681
    invoke-virtual {p0, v4}, Lmiui/widget/ScreenView;->setScreenSnapDuration(I)V

    goto :goto_0

    .line 1685
    :pswitch_3
    invoke-virtual {p0, v2}, Lmiui/widget/ScreenView;->setOvershootTension(F)V

    .line 1686
    invoke-virtual {p0, v5}, Lmiui/widget/ScreenView;->setScreenSnapDuration(I)V

    goto :goto_0

    .line 1689
    :pswitch_4
    invoke-virtual {p0, v1}, Lmiui/widget/ScreenView;->setOvershootTension(F)V

    .line 1690
    invoke-virtual {p0, v3}, Lmiui/widget/ScreenView;->setScreenSnapDuration(I)V

    goto :goto_0

    .line 1694
    :pswitch_5
    invoke-virtual {p0, v1}, Lmiui/widget/ScreenView;->setOvershootTension(F)V

    .line 1695
    invoke-virtual {p0, v3}, Lmiui/widget/ScreenView;->setScreenSnapDuration(I)V

    goto :goto_0

    .line 1701
    :pswitch_6
    invoke-virtual {p0, v1}, Lmiui/widget/ScreenView;->setOvershootTension(F)V

    .line 1702
    invoke-virtual {p0, v4}, Lmiui/widget/ScreenView;->setScreenSnapDuration(I)V

    goto :goto_0

    .line 1706
    :pswitch_7
    invoke-virtual {p0, v2}, Lmiui/widget/ScreenView;->setOvershootTension(F)V

    .line 1707
    invoke-virtual {p0, v3}, Lmiui/widget/ScreenView;->setScreenSnapDuration(I)V

    goto :goto_0

    .line 1673
    nop

    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_1
        :pswitch_2
        :pswitch_2
        :pswitch_3
        :pswitch_4
        :pswitch_5
        :pswitch_0
        :pswitch_6
        :pswitch_7
    .end packed-switch
.end method

.method public setScrollWholeScreen(Z)V
    .locals 0

    .prologue
    .line 417
    iput-boolean p1, p0, Lmiui/widget/ScreenView;->cK:Z

    .line 418
    return-void
.end method

.method public setSeekBarPosition(Landroid/widget/FrameLayout$LayoutParams;)V
    .locals 4

    .prologue
    const/4 v0, 0x0

    .line 508
    if-eqz p1, :cond_3

    .line 509
    iget-object v1, p0, Lmiui/widget/ScreenView;->cv:Lmiui/widget/ScreenView$b;

    if-nez v1, :cond_2

    .line 510
    new-instance v1, Lmiui/widget/ScreenView$b;

    invoke-virtual {p0}, Lmiui/widget/ScreenView;->getContext()Landroid/content/Context;

    move-result-object v2

    invoke-direct {v1, p0, v2}, Lmiui/widget/ScreenView$b;-><init>(Lmiui/widget/ScreenView;Landroid/content/Context;)V

    iput-object v1, p0, Lmiui/widget/ScreenView;->cv:Lmiui/widget/ScreenView$b;

    .line 511
    iget-object v1, p0, Lmiui/widget/ScreenView;->cv:Lmiui/widget/ScreenView$b;

    const/16 v2, 0x10

    invoke-virtual {v1, v2}, Lmiui/widget/ScreenView$b;->setGravity(I)V

    .line 512
    iget-object v1, p0, Lmiui/widget/ScreenView;->cv:Lmiui/widget/ScreenView$b;

    invoke-virtual {v1, v0}, Lmiui/widget/ScreenView$b;->setAnimationCacheEnabled(Z)V

    .line 513
    :goto_0
    invoke-virtual {p0}, Lmiui/widget/ScreenView;->getChildCount()I

    move-result v1

    if-ge v0, v1, :cond_0

    .line 514
    iget-object v1, p0, Lmiui/widget/ScreenView;->cv:Lmiui/widget/ScreenView$b;

    invoke-direct {p0}, Lmiui/widget/ScreenView;->y()Landroid/widget/ImageView;

    move-result-object v2

    sget-object v3, Lmiui/widget/ScreenView;->ca:Landroid/widget/LinearLayout$LayoutParams;

    invoke-virtual {v1, v2, v3}, Lmiui/widget/ScreenView$b;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 513
    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    .line 516
    :cond_0
    iget-object v0, p0, Lmiui/widget/ScreenView;->cv:Lmiui/widget/ScreenView$b;

    invoke-virtual {p0, v0, p1}, Lmiui/widget/ScreenView;->addIndicator(Landroid/view/View;Landroid/widget/FrameLayout$LayoutParams;)V

    .line 524
    :cond_1
    :goto_1
    return-void

    .line 518
    :cond_2
    iget-object v0, p0, Lmiui/widget/ScreenView;->cv:Lmiui/widget/ScreenView$b;

    invoke-virtual {v0, p1}, Lmiui/widget/ScreenView$b;->setLayoutParams(Landroid/view/ViewGroup$LayoutParams;)V

    goto :goto_1

    .line 520
    :cond_3
    iget-object v0, p0, Lmiui/widget/ScreenView;->cv:Lmiui/widget/ScreenView$b;

    if-eqz v0, :cond_1

    .line 521
    iget-object v0, p0, Lmiui/widget/ScreenView;->cv:Lmiui/widget/ScreenView$b;

    invoke-virtual {p0, v0}, Lmiui/widget/ScreenView;->removeIndicator(Landroid/view/View;)V

    .line 522
    const/4 v0, 0x0

    iput-object v0, p0, Lmiui/widget/ScreenView;->cv:Lmiui/widget/ScreenView$b;

    goto :goto_1
.end method

.method public setSeekBarVisibility(I)V
    .locals 1

    .prologue
    .line 611
    iget-object v0, p0, Lmiui/widget/ScreenView;->cv:Lmiui/widget/ScreenView$b;

    if-nez v0, :cond_0

    .line 615
    :goto_0
    return-void

    .line 614
    :cond_0
    iget-object v0, p0, Lmiui/widget/ScreenView;->cv:Lmiui/widget/ScreenView$b;

    invoke-virtual {v0, p1}, Lmiui/widget/ScreenView$b;->setVisibility(I)V

    goto :goto_0
.end method

.method public setSeekPointResource(I)V
    .locals 0

    .prologue
    .line 498
    iput p1, p0, Lmiui/widget/ScreenView;->cs:I

    .line 499
    return-void
.end method

.method public setSlideBarPosition(Landroid/widget/FrameLayout$LayoutParams;)V
    .locals 3

    .prologue
    .line 533
    sget v0, Lcom/miui/internal/R$drawable;->screen_view_slide_bar:I

    sget v1, Lcom/miui/internal/R$drawable;->screen_view_slide_bar_bg:I

    const/4 v2, 0x0

    invoke-virtual {p0, p1, v0, v1, v2}, Lmiui/widget/ScreenView;->setSlideBarPosition(Landroid/widget/FrameLayout$LayoutParams;IIZ)V

    .line 535
    return-void
.end method

.method public setSlideBarPosition(Landroid/widget/FrameLayout$LayoutParams;IIZ)V
    .locals 3

    .prologue
    const/4 v2, 0x0

    .line 545
    iput-boolean p4, p0, Lmiui/widget/ScreenView;->cx:Z

    .line 546
    if-eqz p1, :cond_2

    .line 547
    iget-object v0, p0, Lmiui/widget/ScreenView;->cw:Lmiui/widget/ScreenView$d;

    if-nez v0, :cond_1

    .line 548
    new-instance v0, Lmiui/widget/ScreenView$d;

    invoke-virtual {p0}, Lmiui/widget/ScreenView;->getContext()Landroid/content/Context;

    move-result-object v1

    invoke-direct {v0, p0, v1, p2, p3}, Lmiui/widget/ScreenView$d;-><init>(Lmiui/widget/ScreenView;Landroid/content/Context;II)V

    iput-object v0, p0, Lmiui/widget/ScreenView;->cw:Lmiui/widget/ScreenView$d;

    .line 550
    iget-object v0, p0, Lmiui/widget/ScreenView;->cw:Lmiui/widget/ScreenView$d;

    new-instance v1, Lmiui/widget/ScreenView$f;

    invoke-direct {v1, p0, v2}, Lmiui/widget/ScreenView$f;-><init>(Lmiui/widget/ScreenView;Lmiui/widget/n;)V

    invoke-virtual {v0, v1}, Lmiui/widget/ScreenView$d;->setOnTouchListener(Landroid/view/View$OnTouchListener;)V

    .line 551
    iget-object v0, p0, Lmiui/widget/ScreenView;->cw:Lmiui/widget/ScreenView$d;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Lmiui/widget/ScreenView$d;->setAnimationCacheEnabled(Z)V

    .line 552
    iget-object v0, p0, Lmiui/widget/ScreenView;->cw:Lmiui/widget/ScreenView$d;

    invoke-virtual {p0, v0, p1}, Lmiui/widget/ScreenView;->addIndicator(Landroid/view/View;Landroid/widget/FrameLayout$LayoutParams;)V

    .line 560
    :cond_0
    :goto_0
    return-void

    .line 554
    :cond_1
    iget-object v0, p0, Lmiui/widget/ScreenView;->cw:Lmiui/widget/ScreenView$d;

    invoke-virtual {v0, p1}, Lmiui/widget/ScreenView$d;->setLayoutParams(Landroid/view/ViewGroup$LayoutParams;)V

    goto :goto_0

    .line 556
    :cond_2
    iget-object v0, p0, Lmiui/widget/ScreenView;->cw:Lmiui/widget/ScreenView$d;

    if-eqz v0, :cond_0

    .line 557
    iget-object v0, p0, Lmiui/widget/ScreenView;->cw:Lmiui/widget/ScreenView$d;

    invoke-virtual {p0, v0}, Lmiui/widget/ScreenView;->removeIndicator(Landroid/view/View;)V

    .line 558
    iput-object v2, p0, Lmiui/widget/ScreenView;->cw:Lmiui/widget/ScreenView$d;

    goto :goto_0
.end method

.method public setSlideBarVisibility(I)V
    .locals 1

    .prologue
    .line 626
    iget-object v0, p0, Lmiui/widget/ScreenView;->cw:Lmiui/widget/ScreenView$d;

    if-nez v0, :cond_0

    .line 630
    :goto_0
    return-void

    .line 629
    :cond_0
    iget-object v0, p0, Lmiui/widget/ScreenView;->cw:Lmiui/widget/ScreenView$d;

    invoke-virtual {v0, p1}, Lmiui/widget/ScreenView$d;->setVisibility(I)V

    goto :goto_0
.end method

.method public setTouchSlop(I)V
    .locals 0

    .prologue
    .line 378
    iput p1, p0, Lmiui/widget/ScreenView;->cT:I

    .line 379
    return-void
.end method

.method public setVisibility(I)V
    .locals 0

    .prologue
    .line 886
    if-nez p1, :cond_0

    .line 887
    invoke-direct {p0}, Lmiui/widget/ScreenView;->u()V

    .line 889
    :cond_0
    invoke-super {p0, p1}, Landroid/view/ViewGroup;->setVisibility(I)V

    .line 890
    return-void
.end method

.method public setVisibleExtentionRatio(F)V
    .locals 0

    .prologue
    .line 806
    iput p1, p0, Lmiui/widget/ScreenView;->cM:F

    .line 807
    return-void
.end method

.method protected snapByVelocity(II)V
    .locals 3

    .prologue
    const/4 v1, 0x1

    .line 1198
    if-ne p2, v1, :cond_0

    iget v0, p0, Lmiui/widget/ScreenView;->mCurrentScreen:I

    if-lez v0, :cond_0

    .line 1199
    iget v0, p0, Lmiui/widget/ScreenView;->mCurrentScreen:I

    iget v2, p0, Lmiui/widget/ScreenView;->mVisibleRange:I

    sub-int/2addr v0, v2

    invoke-virtual {p0, v0, p1, v1}, Lmiui/widget/ScreenView;->snapToScreen(IIZ)V

    .line 1212
    :goto_0
    return-void

    .line 1200
    :cond_0
    const/4 v0, 0x2

    if-ne p2, v0, :cond_1

    iget v0, p0, Lmiui/widget/ScreenView;->mCurrentScreen:I

    invoke-virtual {p0}, Lmiui/widget/ScreenView;->getScreenCount()I

    move-result v2

    add-int/lit8 v2, v2, -0x1

    if-ge v0, v2, :cond_1

    .line 1202
    iget v0, p0, Lmiui/widget/ScreenView;->mCurrentScreen:I

    iget v2, p0, Lmiui/widget/ScreenView;->mVisibleRange:I

    add-int/2addr v0, v2

    invoke-virtual {p0, v0, p1, v1}, Lmiui/widget/ScreenView;->snapToScreen(IIZ)V

    goto :goto_0

    .line 1203
    :cond_1
    const/4 v0, 0x3

    if-ne p2, v0, :cond_2

    .line 1204
    iget v0, p0, Lmiui/widget/ScreenView;->mCurrentScreen:I

    invoke-virtual {p0, v0, p1, v1}, Lmiui/widget/ScreenView;->snapToScreen(IIZ)V

    goto :goto_0

    .line 1206
    :cond_2
    iget v2, p0, Lmiui/widget/ScreenView;->mChildScreenWidth:I

    iget-boolean v0, p0, Lmiui/widget/ScreenView;->cK:Z

    if-eqz v0, :cond_3

    iget v0, p0, Lmiui/widget/ScreenView;->mVisibleRange:I

    :goto_1
    mul-int/2addr v0, v2

    .line 1208
    invoke-virtual {p0}, Lmiui/widget/ScreenView;->getScrollX()I

    move-result v2

    shr-int/lit8 v0, v0, 0x1

    add-int/2addr v0, v2

    iget v2, p0, Lmiui/widget/ScreenView;->mChildScreenWidth:I

    div-int/2addr v0, v2

    .line 1210
    const/4 v2, 0x0

    invoke-virtual {p0, v0, v2, v1}, Lmiui/widget/ScreenView;->snapToScreen(IIZ)V

    goto :goto_0

    :cond_3
    move v0, v1

    .line 1206
    goto :goto_1
.end method

.method public snapToScreen(I)V
    .locals 1

    .prologue
    const/4 v0, 0x0

    .line 1220
    invoke-virtual {p0, p1, v0, v0}, Lmiui/widget/ScreenView;->snapToScreen(IIZ)V

    .line 1221
    return-void
.end method

.method protected snapToScreen(IIZ)V
    .locals 1

    .prologue
    .line 1296
    const/4 v0, 0x0

    invoke-virtual {p0, p1, p2, p3, v0}, Lmiui/widget/ScreenView;->snapToScreen(IIZLmiui/widget/ScreenView$SnapScreenOnceNotification;)V

    .line 1297
    return-void
.end method

.method protected snapToScreen(IIZLmiui/widget/ScreenView$SnapScreenOnceNotification;)V
    .locals 8

    .prologue
    const/4 v7, 0x1

    const/4 v2, 0x0

    .line 1244
    iget v0, p0, Lmiui/widget/ScreenView;->cD:I

    if-gtz v0, :cond_1

    .line 1286
    :cond_0
    :goto_0
    return-void

    .line 1247
    :cond_1
    iget-boolean v0, p0, Lmiui/widget/ScreenView;->cK:Z

    if-eqz v0, :cond_6

    .line 1248
    invoke-virtual {p0}, Lmiui/widget/ScreenView;->getScreenCount()I

    move-result v0

    add-int/lit8 v0, v0, -0x1

    invoke-static {p1, v0}, Ljava/lang/Math;->min(II)I

    move-result v0

    invoke-static {v2, v0}, Ljava/lang/Math;->max(II)I

    move-result v0

    iput v0, p0, Lmiui/widget/ScreenView;->cG:I

    .line 1250
    iget v0, p0, Lmiui/widget/ScreenView;->cG:I

    iget v1, p0, Lmiui/widget/ScreenView;->cG:I

    iget v3, p0, Lmiui/widget/ScreenView;->mVisibleRange:I

    rem-int/2addr v1, v3

    sub-int/2addr v0, v1

    iput v0, p0, Lmiui/widget/ScreenView;->cG:I

    .line 1255
    :goto_1
    iget v0, p0, Lmiui/widget/ScreenView;->cG:I

    iget v1, p0, Lmiui/widget/ScreenView;->mCurrentScreen:I

    sub-int/2addr v0, v1

    invoke-static {v0}, Ljava/lang/Math;->abs(I)I

    move-result v0

    invoke-static {v7, v0}, Ljava/lang/Math;->max(II)I

    move-result v1

    .line 1257
    iget-object v0, p0, Lmiui/widget/ScreenView;->cL:Landroid/widget/Scroller;

    invoke-virtual {v0}, Landroid/widget/Scroller;->isFinished()Z

    move-result v0

    if-nez v0, :cond_3

    .line 1258
    iget-object v0, p0, Lmiui/widget/ScreenView;->dd:Lmiui/widget/ScreenView$SnapScreenOnceNotification;

    if-eqz v0, :cond_2

    .line 1259
    iget-object v0, p0, Lmiui/widget/ScreenView;->dd:Lmiui/widget/ScreenView$SnapScreenOnceNotification;

    invoke-interface {v0, p0}, Lmiui/widget/ScreenView$SnapScreenOnceNotification;->onSnapCancelled(Lmiui/widget/ScreenView;)V

    .line 1261
    :cond_2
    iget-object v0, p0, Lmiui/widget/ScreenView;->cL:Landroid/widget/Scroller;

    invoke-virtual {v0}, Landroid/widget/Scroller;->abortAnimation()V

    .line 1263
    :cond_3
    iput-object p4, p0, Lmiui/widget/ScreenView;->dd:Lmiui/widget/ScreenView$SnapScreenOnceNotification;

    .line 1264
    invoke-static {p2}, Ljava/lang/Math;->abs(I)I

    move-result v4

    .line 1265
    if-eqz p3, :cond_7

    .line 1266
    iget-object v0, p0, Lmiui/widget/ScreenView;->da:Lmiui/widget/ScreenView$i;

    invoke-virtual {v0, v1, v4}, Lmiui/widget/ScreenView$i;->n(II)V

    .line 1270
    :goto_2
    iget v0, p0, Lmiui/widget/ScreenView;->cG:I

    iget v3, p0, Lmiui/widget/ScreenView;->mChildScreenWidth:I

    mul-int/2addr v0, v3

    iget v3, p0, Lmiui/widget/ScreenView;->cC:I

    sub-int/2addr v0, v3

    .line 1271
    invoke-virtual {p0}, Lmiui/widget/ScreenView;->getScrollX()I

    move-result v3

    sub-int v3, v0, v3

    .line 1272
    if-eqz v3, :cond_0

    .line 1275
    invoke-static {v3}, Ljava/lang/Math;->abs(I)I

    move-result v0

    iget v5, p0, Lmiui/widget/ScreenView;->cZ:I

    mul-int/2addr v0, v5

    iget v5, p0, Lmiui/widget/ScreenView;->cD:I

    div-int/2addr v0, v5

    .line 1276
    if-lez v4, :cond_4

    .line 1277
    int-to-float v5, v0

    int-to-float v4, v4

    const v6, 0x451c4000

    div-float/2addr v4, v6

    div-float v4, v5, v4

    const v5, 0x3ecccccd

    mul-float/2addr v4, v5

    float-to-int v4, v4

    add-int/2addr v0, v4

    .line 1280
    :cond_4
    iget v4, p0, Lmiui/widget/ScreenView;->cZ:I

    invoke-static {v4, v0}, Ljava/lang/Math;->max(II)I

    move-result v5

    .line 1281
    if-gt v1, v7, :cond_5

    .line 1282
    iget v0, p0, Lmiui/widget/ScreenView;->cZ:I

    mul-int/lit8 v0, v0, 0x2

    invoke-static {v5, v0}, Ljava/lang/Math;->min(II)I

    move-result v5

    .line 1284
    :cond_5
    iget-object v0, p0, Lmiui/widget/ScreenView;->cL:Landroid/widget/Scroller;

    invoke-virtual {p0}, Lmiui/widget/ScreenView;->getScrollX()I

    move-result v1

    move v4, v2

    invoke-virtual/range {v0 .. v5}, Landroid/widget/Scroller;->startScroll(IIIII)V

    .line 1285
    invoke-virtual {p0}, Lmiui/widget/ScreenView;->invalidate()V

    goto/16 :goto_0

    .line 1252
    :cond_6
    invoke-virtual {p0}, Lmiui/widget/ScreenView;->getScreenCount()I

    move-result v0

    iget v1, p0, Lmiui/widget/ScreenView;->mVisibleRange:I

    sub-int/2addr v0, v1

    invoke-static {p1, v0}, Ljava/lang/Math;->min(II)I

    move-result v0

    invoke-static {v2, v0}, Ljava/lang/Math;->max(II)I

    move-result v0

    iput v0, p0, Lmiui/widget/ScreenView;->cG:I

    goto/16 :goto_1

    .line 1268
    :cond_7
    iget-object v0, p0, Lmiui/widget/ScreenView;->da:Lmiui/widget/ScreenView$i;

    invoke-virtual {v0}, Lmiui/widget/ScreenView$i;->eZ()V

    goto :goto_2
.end method

.method public snapToScreen(ILmiui/widget/ScreenView$SnapScreenOnceNotification;)V
    .locals 1

    .prologue
    const/4 v0, 0x0

    .line 1231
    invoke-virtual {p0, p1, v0, v0, p2}, Lmiui/widget/ScreenView;->snapToScreen(IIZLmiui/widget/ScreenView$SnapScreenOnceNotification;)V

    .line 1232
    return-void
.end method

.method protected updateChildStaticTransformationByScreen(Landroid/view/View;F)V
    .locals 0

    .prologue
    .line 1880
    return-void
.end method
