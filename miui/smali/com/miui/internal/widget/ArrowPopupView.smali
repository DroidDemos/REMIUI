.class public Lcom/miui/internal/widget/ArrowPopupView;
.super Landroid/widget/FrameLayout;
.source "SourceFile"

# interfaces
.implements Landroid/view/View$OnTouchListener;


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/internal/widget/ArrowPopupView$a;
    }
.end annotation


# static fields
.field public static final ARROW_BOTTOM_MODE:I = 0x0

.field public static final ARROW_LEFT_MODE:I = 0x3

.field public static final ARROW_NONE_MODE:I = -0x1

.field public static final ARROW_RIGHT_MODE:I = 0x2

.field public static final ARROW_TOP_MODE:I = 0x1

.field private static final TAG:Ljava/lang/String; = "ArrowPopupView"

.field private static final uE:I = 0x4


# instance fields
.field private hI:Landroid/widget/LinearLayout;

.field private jD:Landroid/view/View;

.field private jG:I

.field private jH:I

.field private jL:Landroid/view/View$OnTouchListener;

.field private jN:Z

.field private jO:I

.field private ka:Landroid/animation/Animator$AnimatorListener;

.field private kb:Landroid/animation/Animator$AnimatorListener;

.field private kc:I

.field private lp:Landroid/graphics/drawable/Drawable;

.field private nN:Landroid/graphics/Rect;

.field private uA:I

.field private uB:I

.field private uC:Z

.field private uD:I

.field private uF:F

.field private ue:Landroid/widget/ImageView;

.field private uf:Landroid/widget/FrameLayout;

.field private ug:Landroid/widget/LinearLayout;

.field private uh:Landroid/widget/TextView;

.field private ui:Landroid/widget/Button;

.field private uj:Landroid/widget/Button;

.field private uk:Lcom/miui/internal/widget/ArrowPopupView$a;

.field private ul:Lcom/miui/internal/widget/ArrowPopupView$a;

.field private um:Landroid/graphics/drawable/Drawable;

.field private un:Landroid/graphics/drawable/Drawable;

.field private uo:Landroid/graphics/drawable/Drawable;

.field private uq:Landroid/graphics/drawable/Drawable;

.field private ur:Landroid/graphics/drawable/Drawable;

.field private us:Landroid/graphics/drawable/Drawable;

.field private ut:Landroid/graphics/drawable/Drawable;

.field private uu:Landroid/graphics/drawable/Drawable;

.field private uv:Lmiui/widget/ArrowPopupWindow;

.field private uw:Landroid/graphics/RectF;

.field private ux:Landroid/animation/AnimatorSet;

.field private uy:I

.field private uz:I


# direct methods
.method public constructor <init>(Landroid/content/Context;)V
    .locals 1

    .prologue
    .line 185
    const/4 v0, 0x0

    invoke-direct {p0, p1, v0}, Lcom/miui/internal/widget/ArrowPopupView;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    .line 186
    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 1

    .prologue
    .line 189
    sget v0, Lcom/miui/internal/R$attr;->arrowPopupViewStyle:I

    invoke-direct {p0, p1, p2, v0}, Lcom/miui/internal/widget/ArrowPopupView;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    .line 190
    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V
    .locals 2

    .prologue
    const/4 v1, 0x0

    .line 193
    invoke-direct {p0, p1, p2, p3}, Landroid/widget/FrameLayout;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    .line 105
    new-instance v0, Landroid/graphics/Rect;

    invoke-direct {v0}, Landroid/graphics/Rect;-><init>()V

    iput-object v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->nN:Landroid/graphics/Rect;

    .line 107
    new-instance v0, Landroid/graphics/RectF;

    invoke-direct {v0}, Landroid/graphics/RectF;-><init>()V

    iput-object v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->uw:Landroid/graphics/RectF;

    .line 123
    iput-boolean v1, p0, Lcom/miui/internal/widget/ArrowPopupView;->uC:Z

    .line 129
    new-instance v0, Lcom/miui/internal/widget/j;

    invoke-direct {v0, p0}, Lcom/miui/internal/widget/j;-><init>(Lcom/miui/internal/widget/ArrowPopupView;)V

    iput-object v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->ka:Landroid/animation/Animator$AnimatorListener;

    .line 154
    new-instance v0, Lcom/miui/internal/widget/k;

    invoke-direct {v0, p0}, Lcom/miui/internal/widget/k;-><init>(Lcom/miui/internal/widget/ArrowPopupView;)V

    iput-object v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->kb:Landroid/animation/Animator$AnimatorListener;

    .line 180
    const/4 v0, -0x1

    iput v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->kc:I

    .line 195
    sget-object v0, Lcom/miui/internal/R$styleable;->ArrowPopupView:[I

    invoke-virtual {p1, p2, v0, p3, v1}, Landroid/content/Context;->obtainStyledAttributes(Landroid/util/AttributeSet;[III)Landroid/content/res/TypedArray;

    move-result-object v0

    .line 199
    invoke-virtual {v0, v1}, Landroid/content/res/TypedArray;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v1

    iput-object v1, p0, Lcom/miui/internal/widget/ArrowPopupView;->lp:Landroid/graphics/drawable/Drawable;

    .line 200
    const/4 v1, 0x7

    invoke-virtual {v0, v1}, Landroid/content/res/TypedArray;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v1

    iput-object v1, p0, Lcom/miui/internal/widget/ArrowPopupView;->um:Landroid/graphics/drawable/Drawable;

    .line 201
    const/16 v1, 0x8

    invoke-virtual {v0, v1}, Landroid/content/res/TypedArray;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v1

    iput-object v1, p0, Lcom/miui/internal/widget/ArrowPopupView;->un:Landroid/graphics/drawable/Drawable;

    .line 202
    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Landroid/content/res/TypedArray;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v1

    iput-object v1, p0, Lcom/miui/internal/widget/ArrowPopupView;->uo:Landroid/graphics/drawable/Drawable;

    .line 203
    const/4 v1, 0x2

    invoke-virtual {v0, v1}, Landroid/content/res/TypedArray;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v1

    iput-object v1, p0, Lcom/miui/internal/widget/ArrowPopupView;->uq:Landroid/graphics/drawable/Drawable;

    .line 204
    const/4 v1, 0x3

    invoke-virtual {v0, v1}, Landroid/content/res/TypedArray;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v1

    iput-object v1, p0, Lcom/miui/internal/widget/ArrowPopupView;->ur:Landroid/graphics/drawable/Drawable;

    .line 205
    const/4 v1, 0x4

    invoke-virtual {v0, v1}, Landroid/content/res/TypedArray;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v1

    iput-object v1, p0, Lcom/miui/internal/widget/ArrowPopupView;->us:Landroid/graphics/drawable/Drawable;

    .line 206
    const/4 v1, 0x6

    invoke-virtual {v0, v1}, Landroid/content/res/TypedArray;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v1

    iput-object v1, p0, Lcom/miui/internal/widget/ArrowPopupView;->ut:Landroid/graphics/drawable/Drawable;

    .line 207
    const/4 v1, 0x5

    invoke-virtual {v0, v1}, Landroid/content/res/TypedArray;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v1

    iput-object v1, p0, Lcom/miui/internal/widget/ArrowPopupView;->uu:Landroid/graphics/drawable/Drawable;

    .line 209
    invoke-virtual {v0}, Landroid/content/res/TypedArray;->recycle()V

    .line 211
    invoke-virtual {p1}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    sget v1, Lcom/miui/internal/R$dimen;->arrow_popup_window_min_border:I

    invoke-virtual {v0, v1}, Landroid/content/res/Resources;->getDimensionPixelOffset(I)I

    move-result v0

    iput v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->jO:I

    .line 213
    return-void
.end method

.method static synthetic a(Lcom/miui/internal/widget/ArrowPopupView;I)I
    .locals 0

    .prologue
    .line 45
    iput p1, p0, Lcom/miui/internal/widget/ArrowPopupView;->uD:I

    return p1
.end method

.method static synthetic a(Lcom/miui/internal/widget/ArrowPopupView;Landroid/animation/AnimatorSet;)Landroid/animation/AnimatorSet;
    .locals 0

    .prologue
    .line 45
    iput-object p1, p0, Lcom/miui/internal/widget/ArrowPopupView;->ux:Landroid/animation/AnimatorSet;

    return-object p1
.end method

.method static synthetic a(Lcom/miui/internal/widget/ArrowPopupView;)Z
    .locals 1

    .prologue
    .line 45
    iget-boolean v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->uC:Z

    return v0
.end method

.method static synthetic a(Lcom/miui/internal/widget/ArrowPopupView;Z)Z
    .locals 0

    .prologue
    .line 45
    iput-boolean p1, p0, Lcom/miui/internal/widget/ArrowPopupView;->jN:Z

    return p1
.end method

.method private aw()V
    .locals 13

    .prologue
    const/4 v12, 0x4

    const/4 v11, 0x2

    const/4 v10, 0x1

    const/4 v2, 0x0

    .line 691
    new-array v0, v11, [I

    .line 692
    iget-object v1, p0, Lcom/miui/internal/widget/ArrowPopupView;->jD:Landroid/view/View;

    invoke-virtual {v1, v0}, Landroid/view/View;->getLocationInWindow([I)V

    .line 694
    invoke-virtual {p0}, Lcom/miui/internal/widget/ArrowPopupView;->getWidth()I

    move-result v1

    .line 695
    invoke-virtual {p0}, Lcom/miui/internal/widget/ArrowPopupView;->getHeight()I

    move-result v3

    .line 696
    iget-object v4, p0, Lcom/miui/internal/widget/ArrowPopupView;->ug:Landroid/widget/LinearLayout;

    invoke-virtual {v4}, Landroid/widget/LinearLayout;->getMeasuredWidth()I

    move-result v4

    .line 697
    iget-object v5, p0, Lcom/miui/internal/widget/ArrowPopupView;->ug:Landroid/widget/LinearLayout;

    invoke-virtual {v5}, Landroid/widget/LinearLayout;->getMeasuredHeight()I

    move-result v5

    .line 698
    iget-object v6, p0, Lcom/miui/internal/widget/ArrowPopupView;->jD:Landroid/view/View;

    invoke-virtual {v6}, Landroid/view/View;->getHeight()I

    move-result v6

    .line 699
    iget-object v7, p0, Lcom/miui/internal/widget/ArrowPopupView;->jD:Landroid/view/View;

    invoke-virtual {v7}, Landroid/view/View;->getWidth()I

    move-result v7

    .line 701
    new-array v8, v12, [I

    .line 702
    aget v9, v0, v10

    sub-int/2addr v9, v5

    aput v9, v8, v2

    .line 703
    aget v9, v0, v10

    sub-int/2addr v3, v9

    sub-int/2addr v3, v6

    sub-int/2addr v3, v5

    aput v3, v8, v10

    .line 704
    aget v3, v0, v2

    sub-int/2addr v3, v4

    aput v3, v8, v11

    .line 705
    const/4 v3, 0x3

    aget v0, v0, v2

    sub-int v0, v1, v0

    sub-int/2addr v0, v7

    sub-int/2addr v0, v4

    aput v0, v8, v3

    .line 710
    const/high16 v0, -0x80000000

    move v1, v2

    .line 711
    :goto_0
    if-ge v2, v12, :cond_2

    .line 712
    aget v3, v8, v2

    iget v4, p0, Lcom/miui/internal/widget/ArrowPopupView;->jO:I

    if-lt v3, v4, :cond_0

    .line 720
    :goto_1
    invoke-virtual {p0, v2}, Lcom/miui/internal/widget/ArrowPopupView;->setArrowMode(I)V

    .line 721
    return-void

    .line 715
    :cond_0
    aget v3, v8, v2

    if-le v3, v0, :cond_1

    .line 717
    aget v0, v8, v2

    move v1, v2

    .line 711
    :cond_1
    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    :cond_2
    move v2, v1

    goto :goto_1
.end method

.method private ax()V
    .locals 5

    .prologue
    .line 293
    iget v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->kc:I

    if-eqz v0, :cond_0

    iget v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->kc:I

    const/4 v1, 0x1

    if-ne v0, v1, :cond_4

    .line 294
    :cond_0
    invoke-direct {p0}, Lcom/miui/internal/widget/ArrowPopupView;->bU()V

    .line 300
    :goto_0
    invoke-virtual {p0}, Lcom/miui/internal/widget/ArrowPopupView;->getContentView()Landroid/view/View;

    move-result-object v0

    .line 301
    if-eqz v0, :cond_3

    .line 302
    invoke-virtual {v0}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v1

    .line 303
    invoke-virtual {v0}, Landroid/view/View;->getMeasuredHeight()I

    move-result v2

    iget-object v3, p0, Lcom/miui/internal/widget/ArrowPopupView;->ug:Landroid/widget/LinearLayout;

    invoke-virtual {v3}, Landroid/widget/LinearLayout;->getMeasuredHeight()I

    move-result v3

    iget-object v4, p0, Lcom/miui/internal/widget/ArrowPopupView;->hI:Landroid/widget/LinearLayout;

    invoke-virtual {v4}, Landroid/widget/LinearLayout;->getMeasuredHeight()I

    move-result v4

    sub-int/2addr v3, v4

    if-le v2, v3, :cond_5

    .line 304
    iget-object v2, p0, Lcom/miui/internal/widget/ArrowPopupView;->ug:Landroid/widget/LinearLayout;

    invoke-virtual {v2}, Landroid/widget/LinearLayout;->getMeasuredHeight()I

    move-result v2

    iget-object v3, p0, Lcom/miui/internal/widget/ArrowPopupView;->hI:Landroid/widget/LinearLayout;

    invoke-virtual {v3}, Landroid/widget/LinearLayout;->getMeasuredHeight()I

    move-result v3

    sub-int/2addr v2, v3

    iput v2, v1, Landroid/view/ViewGroup$LayoutParams;->height:I

    .line 305
    invoke-virtual {v0, v1}, Landroid/view/View;->setLayoutParams(Landroid/view/ViewGroup$LayoutParams;)V

    .line 310
    :cond_1
    :goto_1
    iget v0, v1, Landroid/view/ViewGroup$LayoutParams;->height:I

    if-lez v0, :cond_2

    iget v0, v1, Landroid/view/ViewGroup$LayoutParams;->width:I

    if-gtz v0, :cond_3

    .line 311
    :cond_2
    const-string v0, "ArrowPopupView"

    const-string v1, "Invalid LayoutPrams of content view, please check the anchor view"

    invoke-static {v0, v1}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    .line 314
    :cond_3
    return-void

    .line 296
    :cond_4
    invoke-direct {p0}, Lcom/miui/internal/widget/ArrowPopupView;->bV()V

    goto :goto_0

    .line 306
    :cond_5
    invoke-virtual {v0}, Landroid/view/View;->getMeasuredWidth()I

    move-result v2

    iget-object v3, p0, Lcom/miui/internal/widget/ArrowPopupView;->ug:Landroid/widget/LinearLayout;

    invoke-virtual {v3}, Landroid/widget/LinearLayout;->getMeasuredWidth()I

    move-result v3

    if-le v2, v3, :cond_1

    .line 307
    iget-object v2, p0, Lcom/miui/internal/widget/ArrowPopupView;->ug:Landroid/widget/LinearLayout;

    invoke-virtual {v2}, Landroid/widget/LinearLayout;->getMeasuredWidth()I

    move-result v2

    iput v2, v1, Landroid/view/ViewGroup$LayoutParams;->width:I

    .line 308
    invoke-virtual {v0, v1}, Landroid/view/View;->setLayoutParams(Landroid/view/ViewGroup$LayoutParams;)V

    goto :goto_1
.end method

.method static synthetic b(Lcom/miui/internal/widget/ArrowPopupView;)V
    .locals 0

    .prologue
    .line 45
    invoke-direct {p0}, Lcom/miui/internal/widget/ArrowPopupView;->bW()V

    return-void
.end method

.method private bU()V
    .locals 15

    .prologue
    const/4 v14, 0x1

    const/4 v3, 0x0

    .line 317
    iget-object v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->jD:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->getWidth()I

    move-result v2

    .line 318
    iget-object v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->jD:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->getHeight()I

    move-result v4

    .line 319
    invoke-virtual {p0}, Lcom/miui/internal/widget/ArrowPopupView;->getWidth()I

    move-result v5

    .line 320
    invoke-virtual {p0}, Lcom/miui/internal/widget/ArrowPopupView;->getHeight()I

    move-result v6

    .line 321
    iget-object v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->ug:Landroid/widget/LinearLayout;

    invoke-virtual {v0}, Landroid/widget/LinearLayout;->getMeasuredWidth()I

    move-result v0

    iget-object v1, p0, Lcom/miui/internal/widget/ArrowPopupView;->ug:Landroid/widget/LinearLayout;

    invoke-virtual {v1}, Landroid/widget/LinearLayout;->getMinimumWidth()I

    move-result v1

    if-le v0, v1, :cond_1

    iget-object v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->ug:Landroid/widget/LinearLayout;

    invoke-virtual {v0}, Landroid/widget/LinearLayout;->getMeasuredWidth()I

    move-result v0

    .line 323
    :goto_0
    iget-object v1, p0, Lcom/miui/internal/widget/ArrowPopupView;->ug:Landroid/widget/LinearLayout;

    invoke-virtual {v1}, Landroid/widget/LinearLayout;->getMeasuredHeight()I

    move-result v1

    iget-object v7, p0, Lcom/miui/internal/widget/ArrowPopupView;->ug:Landroid/widget/LinearLayout;

    invoke-virtual {v7}, Landroid/widget/LinearLayout;->getMinimumHeight()I

    move-result v7

    if-le v1, v7, :cond_2

    iget-object v1, p0, Lcom/miui/internal/widget/ArrowPopupView;->ug:Landroid/widget/LinearLayout;

    invoke-virtual {v1}, Landroid/widget/LinearLayout;->getMeasuredHeight()I

    move-result v1

    .line 326
    :goto_1
    iget-object v7, p0, Lcom/miui/internal/widget/ArrowPopupView;->ue:Landroid/widget/ImageView;

    invoke-virtual {v7}, Landroid/widget/ImageView;->getMeasuredWidth()I

    move-result v7

    .line 327
    iget-object v8, p0, Lcom/miui/internal/widget/ArrowPopupView;->ue:Landroid/widget/ImageView;

    invoke-virtual {v8}, Landroid/widget/ImageView;->getMeasuredHeight()I

    move-result v8

    .line 329
    const/4 v9, 0x2

    new-array v9, v9, [I

    .line 330
    iget-object v10, p0, Lcom/miui/internal/widget/ArrowPopupView;->jD:Landroid/view/View;

    invoke-virtual {v10, v9}, Landroid/view/View;->getLocationInWindow([I)V

    .line 331
    aget v10, v9, v3

    .line 332
    aget v11, v9, v14

    .line 333
    invoke-virtual {p0, v9}, Lcom/miui/internal/widget/ArrowPopupView;->getLocationInWindow([I)V

    .line 334
    div-int/lit8 v12, v2, 0x2

    add-int/2addr v12, v10

    aget v13, v9, v3

    sub-int/2addr v12, v13

    iput v12, p0, Lcom/miui/internal/widget/ArrowPopupView;->uy:I

    .line 335
    iget v12, p0, Lcom/miui/internal/widget/ArrowPopupView;->uy:I

    sub-int v12, v5, v12

    .line 336
    sub-int/2addr v2, v7

    div-int/lit8 v2, v2, 0x2

    add-int/2addr v2, v10

    aget v10, v9, v3

    sub-int/2addr v2, v10

    iput v2, p0, Lcom/miui/internal/widget/ArrowPopupView;->uA:I

    .line 338
    invoke-virtual {p0}, Lcom/miui/internal/widget/ArrowPopupView;->getTop()I

    move-result v2

    iget v10, p0, Lcom/miui/internal/widget/ArrowPopupView;->jH:I

    add-int/2addr v2, v10

    iput v2, p0, Lcom/miui/internal/widget/ArrowPopupView;->uz:I

    .line 340
    iget v2, p0, Lcom/miui/internal/widget/ArrowPopupView;->kc:I

    if-nez v2, :cond_3

    .line 341
    iget v2, p0, Lcom/miui/internal/widget/ArrowPopupView;->uz:I

    aget v4, v9, v14

    sub-int v4, v11, v4

    sub-int/2addr v4, v1

    iget-object v10, p0, Lcom/miui/internal/widget/ArrowPopupView;->ug:Landroid/widget/LinearLayout;

    invoke-virtual {v10}, Landroid/widget/LinearLayout;->getPaddingBottom()I

    move-result v10

    sub-int/2addr v10, v8

    add-int/2addr v4, v10

    add-int/2addr v2, v4

    iput v2, p0, Lcom/miui/internal/widget/ArrowPopupView;->uz:I

    .line 343
    aget v2, v9, v14

    sub-int v2, v11, v2

    sub-int/2addr v2, v8

    iget v4, p0, Lcom/miui/internal/widget/ArrowPopupView;->jH:I

    add-int/2addr v2, v4

    .line 350
    :goto_2
    div-int/lit8 v4, v0, 0x2

    .line 351
    sub-int v9, v0, v4

    .line 352
    iget v10, p0, Lcom/miui/internal/widget/ArrowPopupView;->uy:I

    if-lt v10, v4, :cond_4

    if-lt v12, v9, :cond_4

    .line 354
    iget v9, p0, Lcom/miui/internal/widget/ArrowPopupView;->uy:I

    sub-int v4, v9, v4

    iput v4, p0, Lcom/miui/internal/widget/ArrowPopupView;->uy:I

    .line 362
    :cond_0
    :goto_3
    iget v4, p0, Lcom/miui/internal/widget/ArrowPopupView;->uy:I

    iget v9, p0, Lcom/miui/internal/widget/ArrowPopupView;->jG:I

    add-int/2addr v4, v9

    iput v4, p0, Lcom/miui/internal/widget/ArrowPopupView;->uy:I

    .line 363
    iget v4, p0, Lcom/miui/internal/widget/ArrowPopupView;->uA:I

    iget v9, p0, Lcom/miui/internal/widget/ArrowPopupView;->jG:I

    add-int/2addr v4, v9

    iput v4, p0, Lcom/miui/internal/widget/ArrowPopupView;->uA:I

    .line 365
    iget-object v4, p0, Lcom/miui/internal/widget/ArrowPopupView;->ug:Landroid/widget/LinearLayout;

    iget v9, p0, Lcom/miui/internal/widget/ArrowPopupView;->uy:I

    invoke-static {v9, v3}, Ljava/lang/Math;->max(II)I

    move-result v9

    iget v10, p0, Lcom/miui/internal/widget/ArrowPopupView;->uz:I

    invoke-static {v10, v3}, Ljava/lang/Math;->max(II)I

    move-result v3

    iget v10, p0, Lcom/miui/internal/widget/ArrowPopupView;->uy:I

    add-int/2addr v0, v10

    invoke-static {v0, v5}, Ljava/lang/Math;->min(II)I

    move-result v0

    iget v5, p0, Lcom/miui/internal/widget/ArrowPopupView;->uz:I

    add-int/2addr v1, v5

    invoke-static {v1, v6}, Ljava/lang/Math;->min(II)I

    move-result v1

    invoke-virtual {v4, v9, v3, v0, v1}, Landroid/widget/LinearLayout;->layout(IIII)V

    .line 371
    iget-object v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->ue:Landroid/widget/ImageView;

    iget v1, p0, Lcom/miui/internal/widget/ArrowPopupView;->uA:I

    iget v3, p0, Lcom/miui/internal/widget/ArrowPopupView;->uA:I

    add-int/2addr v3, v7

    add-int v4, v2, v8

    invoke-virtual {v0, v1, v2, v3, v4}, Landroid/widget/ImageView;->layout(IIII)V

    .line 375
    return-void

    .line 321
    :cond_1
    iget-object v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->ug:Landroid/widget/LinearLayout;

    invoke-virtual {v0}, Landroid/widget/LinearLayout;->getMinimumWidth()I

    move-result v0

    goto/16 :goto_0

    .line 323
    :cond_2
    iget-object v1, p0, Lcom/miui/internal/widget/ArrowPopupView;->ug:Landroid/widget/LinearLayout;

    invoke-virtual {v1}, Landroid/widget/LinearLayout;->getMinimumHeight()I

    move-result v1

    goto/16 :goto_1

    .line 344
    :cond_3
    iget v2, p0, Lcom/miui/internal/widget/ArrowPopupView;->kc:I

    if-ne v2, v14, :cond_6

    .line 345
    iget v2, p0, Lcom/miui/internal/widget/ArrowPopupView;->uz:I

    add-int/2addr v4, v11

    aget v9, v9, v14

    sub-int/2addr v4, v9

    iget-object v9, p0, Lcom/miui/internal/widget/ArrowPopupView;->ug:Landroid/widget/LinearLayout;

    invoke-virtual {v9}, Landroid/widget/LinearLayout;->getPaddingTop()I

    move-result v9

    sub-int/2addr v4, v9

    add-int/2addr v4, v8

    add-int/2addr v2, v4

    iput v2, p0, Lcom/miui/internal/widget/ArrowPopupView;->uz:I

    .line 347
    iget v2, p0, Lcom/miui/internal/widget/ArrowPopupView;->uz:I

    goto :goto_2

    .line 355
    :cond_4
    if-ge v12, v9, :cond_5

    .line 357
    sub-int v4, v5, v0

    iput v4, p0, Lcom/miui/internal/widget/ArrowPopupView;->uy:I

    goto :goto_3

    .line 358
    :cond_5
    iget v9, p0, Lcom/miui/internal/widget/ArrowPopupView;->uy:I

    if-ge v9, v4, :cond_0

    .line 360
    iput v3, p0, Lcom/miui/internal/widget/ArrowPopupView;->uy:I

    goto :goto_3

    :cond_6
    move v2, v3

    goto/16 :goto_2
.end method

.method private bV()V
    .locals 15

    .prologue
    .line 378
    const/4 v0, 0x2

    new-array v3, v0, [I

    .line 379
    iget-object v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->jD:Landroid/view/View;

    invoke-virtual {v0, v3}, Landroid/view/View;->getLocationInWindow([I)V

    .line 380
    const/4 v0, 0x0

    aget v4, v3, v0

    .line 381
    const/4 v0, 0x1

    aget v2, v3, v0

    .line 382
    invoke-virtual {p0, v3}, Lcom/miui/internal/widget/ArrowPopupView;->getLocationInWindow([I)V

    .line 383
    iget-object v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->jD:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->getWidth()I

    move-result v5

    .line 384
    iget-object v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->jD:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->getHeight()I

    move-result v6

    .line 385
    invoke-virtual {p0}, Lcom/miui/internal/widget/ArrowPopupView;->getWidth()I

    move-result v7

    .line 386
    invoke-virtual {p0}, Lcom/miui/internal/widget/ArrowPopupView;->getHeight()I

    move-result v8

    .line 387
    iget-object v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->ug:Landroid/widget/LinearLayout;

    invoke-virtual {v0}, Landroid/widget/LinearLayout;->getMeasuredWidth()I

    move-result v0

    iget-object v1, p0, Lcom/miui/internal/widget/ArrowPopupView;->ug:Landroid/widget/LinearLayout;

    invoke-virtual {v1}, Landroid/widget/LinearLayout;->getMinimumWidth()I

    move-result v1

    if-le v0, v1, :cond_2

    iget-object v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->ug:Landroid/widget/LinearLayout;

    invoke-virtual {v0}, Landroid/widget/LinearLayout;->getMeasuredWidth()I

    move-result v0

    .line 390
    :goto_0
    iget-object v1, p0, Lcom/miui/internal/widget/ArrowPopupView;->ug:Landroid/widget/LinearLayout;

    invoke-virtual {v1}, Landroid/widget/LinearLayout;->getMeasuredHeight()I

    move-result v1

    iget-object v9, p0, Lcom/miui/internal/widget/ArrowPopupView;->ug:Landroid/widget/LinearLayout;

    invoke-virtual {v9}, Landroid/widget/LinearLayout;->getMinimumHeight()I

    move-result v9

    if-le v1, v9, :cond_3

    iget-object v1, p0, Lcom/miui/internal/widget/ArrowPopupView;->ug:Landroid/widget/LinearLayout;

    invoke-virtual {v1}, Landroid/widget/LinearLayout;->getMeasuredHeight()I

    move-result v1

    .line 393
    :goto_1
    iget-object v9, p0, Lcom/miui/internal/widget/ArrowPopupView;->ue:Landroid/widget/ImageView;

    invoke-virtual {v9}, Landroid/widget/ImageView;->getMeasuredWidth()I

    move-result v9

    .line 394
    iget-object v10, p0, Lcom/miui/internal/widget/ArrowPopupView;->ue:Landroid/widget/ImageView;

    invoke-virtual {v10}, Landroid/widget/ImageView;->getMeasuredHeight()I

    move-result v10

    .line 395
    div-int/lit8 v11, v6, 0x2

    add-int/2addr v11, v2

    const/4 v12, 0x1

    aget v12, v3, v12

    sub-int/2addr v11, v12

    iput v11, p0, Lcom/miui/internal/widget/ArrowPopupView;->uz:I

    .line 396
    iget v11, p0, Lcom/miui/internal/widget/ArrowPopupView;->uz:I

    sub-int v11, v8, v11

    .line 397
    sub-int/2addr v6, v10

    div-int/lit8 v6, v6, 0x2

    add-int/2addr v2, v6

    const/4 v6, 0x1

    aget v6, v3, v6

    sub-int/2addr v2, v6

    iput v2, p0, Lcom/miui/internal/widget/ArrowPopupView;->uB:I

    .line 399
    div-int/lit8 v6, v1, 0x2

    .line 400
    sub-int v12, v1, v6

    .line 402
    invoke-virtual {p0}, Lcom/miui/internal/widget/ArrowPopupView;->getLeft()I

    move-result v2

    iget v13, p0, Lcom/miui/internal/widget/ArrowPopupView;->jG:I

    add-int/2addr v2, v13

    iput v2, p0, Lcom/miui/internal/widget/ArrowPopupView;->uy:I

    .line 403
    const/4 v2, 0x0

    .line 404
    iget v13, p0, Lcom/miui/internal/widget/ArrowPopupView;->kc:I

    const/4 v14, 0x2

    if-ne v13, v14, :cond_4

    .line 405
    iget v2, p0, Lcom/miui/internal/widget/ArrowPopupView;->uy:I

    sub-int v5, v4, v0

    iget-object v13, p0, Lcom/miui/internal/widget/ArrowPopupView;->ug:Landroid/widget/LinearLayout;

    invoke-virtual {v13}, Landroid/widget/LinearLayout;->getPaddingRight()I

    move-result v13

    add-int/2addr v5, v13

    sub-int/2addr v5, v9

    const/4 v13, 0x0

    aget v13, v3, v13

    sub-int/2addr v5, v13

    add-int/2addr v2, v5

    iput v2, p0, Lcom/miui/internal/widget/ArrowPopupView;->uy:I

    .line 407
    sub-int v2, v4, v9

    const/4 v4, 0x0

    aget v3, v3, v4

    sub-int/2addr v2, v3

    iget v3, p0, Lcom/miui/internal/widget/ArrowPopupView;->jG:I

    add-int/2addr v2, v3

    .line 414
    :cond_0
    :goto_2
    iget v3, p0, Lcom/miui/internal/widget/ArrowPopupView;->uz:I

    if-lt v3, v6, :cond_5

    if-lt v11, v12, :cond_5

    .line 416
    iget v3, p0, Lcom/miui/internal/widget/ArrowPopupView;->uz:I

    sub-int/2addr v3, v6

    iget v4, p0, Lcom/miui/internal/widget/ArrowPopupView;->jH:I

    add-int/2addr v3, v4

    iput v3, p0, Lcom/miui/internal/widget/ArrowPopupView;->uz:I

    .line 424
    :cond_1
    :goto_3
    iget v3, p0, Lcom/miui/internal/widget/ArrowPopupView;->uB:I

    iget v4, p0, Lcom/miui/internal/widget/ArrowPopupView;->jH:I

    add-int/2addr v3, v4

    iput v3, p0, Lcom/miui/internal/widget/ArrowPopupView;->uB:I

    .line 426
    iget-object v3, p0, Lcom/miui/internal/widget/ArrowPopupView;->ug:Landroid/widget/LinearLayout;

    iget v4, p0, Lcom/miui/internal/widget/ArrowPopupView;->uy:I

    const/4 v5, 0x0

    invoke-static {v4, v5}, Ljava/lang/Math;->max(II)I

    move-result v4

    iget v5, p0, Lcom/miui/internal/widget/ArrowPopupView;->uz:I

    const/4 v6, 0x0

    invoke-static {v5, v6}, Ljava/lang/Math;->max(II)I

    move-result v5

    iget v6, p0, Lcom/miui/internal/widget/ArrowPopupView;->uy:I

    add-int/2addr v0, v6

    invoke-static {v0, v7}, Ljava/lang/Math;->min(II)I

    move-result v0

    iget v6, p0, Lcom/miui/internal/widget/ArrowPopupView;->uz:I

    add-int/2addr v1, v6

    invoke-static {v1, v8}, Ljava/lang/Math;->min(II)I

    move-result v1

    invoke-virtual {v3, v4, v5, v0, v1}, Landroid/widget/LinearLayout;->layout(IIII)V

    .line 432
    iget-object v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->ue:Landroid/widget/ImageView;

    iget v1, p0, Lcom/miui/internal/widget/ArrowPopupView;->uB:I

    add-int v3, v2, v9

    iget v4, p0, Lcom/miui/internal/widget/ArrowPopupView;->uB:I

    add-int/2addr v4, v10

    invoke-virtual {v0, v2, v1, v3, v4}, Landroid/widget/ImageView;->layout(IIII)V

    .line 436
    return-void

    .line 387
    :cond_2
    iget-object v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->ug:Landroid/widget/LinearLayout;

    invoke-virtual {v0}, Landroid/widget/LinearLayout;->getMinimumWidth()I

    move-result v0

    goto/16 :goto_0

    .line 390
    :cond_3
    iget-object v1, p0, Lcom/miui/internal/widget/ArrowPopupView;->ug:Landroid/widget/LinearLayout;

    invoke-virtual {v1}, Landroid/widget/LinearLayout;->getMinimumHeight()I

    move-result v1

    goto/16 :goto_1

    .line 408
    :cond_4
    iget v13, p0, Lcom/miui/internal/widget/ArrowPopupView;->kc:I

    const/4 v14, 0x3

    if-ne v13, v14, :cond_0

    .line 409
    iget v2, p0, Lcom/miui/internal/widget/ArrowPopupView;->uy:I

    add-int/2addr v4, v5

    iget-object v5, p0, Lcom/miui/internal/widget/ArrowPopupView;->ug:Landroid/widget/LinearLayout;

    invoke-virtual {v5}, Landroid/widget/LinearLayout;->getPaddingLeft()I

    move-result v5

    sub-int/2addr v4, v5

    add-int/2addr v4, v9

    const/4 v5, 0x0

    aget v3, v3, v5

    sub-int v3, v4, v3

    add-int/2addr v2, v3

    iput v2, p0, Lcom/miui/internal/widget/ArrowPopupView;->uy:I

    .line 411
    iget v2, p0, Lcom/miui/internal/widget/ArrowPopupView;->uy:I

    goto :goto_2

    .line 417
    :cond_5
    if-ge v11, v12, :cond_6

    .line 419
    sub-int v3, v8, v1

    iget v4, p0, Lcom/miui/internal/widget/ArrowPopupView;->jH:I

    add-int/2addr v3, v4

    iput v3, p0, Lcom/miui/internal/widget/ArrowPopupView;->uz:I

    goto :goto_3

    .line 420
    :cond_6
    iget v3, p0, Lcom/miui/internal/widget/ArrowPopupView;->uz:I

    if-ge v3, v6, :cond_1

    .line 422
    iget v3, p0, Lcom/miui/internal/widget/ArrowPopupView;->jH:I

    iput v3, p0, Lcom/miui/internal/widget/ArrowPopupView;->uz:I

    goto :goto_3
.end method

.method private bW()V
    .locals 11

    .prologue
    const/4 v10, 0x3

    const/4 v9, 0x1

    const/4 v8, 0x0

    const/4 v7, 0x2

    const/4 v6, 0x0

    .line 634
    iget-object v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->ux:Landroid/animation/AnimatorSet;

    if-eqz v0, :cond_0

    .line 635
    iget-object v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->ux:Landroid/animation/AnimatorSet;

    invoke-virtual {v0}, Landroid/animation/AnimatorSet;->cancel()V

    .line 637
    :cond_0
    new-instance v0, Landroid/animation/AnimatorSet;

    invoke-direct {v0}, Landroid/animation/AnimatorSet;-><init>()V

    iput-object v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->ux:Landroid/animation/AnimatorSet;

    .line 638
    invoke-virtual {p0}, Lcom/miui/internal/widget/ArrowPopupView;->getBackground()Landroid/graphics/drawable/Drawable;

    move-result-object v0

    const-string v1, "alpha"

    new-array v2, v7, [I

    fill-array-data v2, :array_0

    invoke-static {v0, v1, v2}, Landroid/animation/ObjectAnimator;->ofInt(Ljava/lang/Object;Ljava/lang/String;[I)Landroid/animation/ObjectAnimator;

    move-result-object v2

    .line 639
    new-instance v0, Lcom/miui/internal/widget/ArrowPopupView$2;

    invoke-direct {v0, p0}, Lcom/miui/internal/widget/ArrowPopupView$2;-><init>(Lcom/miui/internal/widget/ArrowPopupView;)V

    invoke-virtual {v2, v0}, Landroid/animation/ObjectAnimator;->addListener(Landroid/animation/Animator$AnimatorListener;)V

    .line 645
    const-wide/16 v0, 0x76c

    invoke-virtual {v2, v0, v1}, Landroid/animation/ObjectAnimator;->setDuration(J)Landroid/animation/ObjectAnimator;

    .line 647
    const/high16 v0, 0x40800000

    invoke-virtual {p0}, Lcom/miui/internal/widget/ArrowPopupView;->getContext()Landroid/content/Context;

    move-result-object v1

    invoke-virtual {v1}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    invoke-virtual {v1}, Landroid/content/res/Resources;->getDisplayMetrics()Landroid/util/DisplayMetrics;

    move-result-object v1

    iget v1, v1, Landroid/util/DisplayMetrics;->density:F

    mul-float/2addr v1, v0

    .line 648
    sget-object v0, Landroid/view/View;->TRANSLATION_Y:Landroid/util/Property;

    .line 649
    iget v3, p0, Lcom/miui/internal/widget/ArrowPopupView;->kc:I

    packed-switch v3, :pswitch_data_0

    .line 664
    :goto_0
    :pswitch_0
    iget-object v3, p0, Lcom/miui/internal/widget/ArrowPopupView;->ug:Landroid/widget/LinearLayout;

    new-array v4, v10, [F

    aput v6, v4, v8

    aput v1, v4, v9

    aput v6, v4, v7

    invoke-static {v3, v0, v4}, Landroid/animation/ObjectAnimator;->ofFloat(Ljava/lang/Object;Landroid/util/Property;[F)Landroid/animation/ObjectAnimator;

    move-result-object v3

    .line 665
    new-instance v4, Landroid/view/animation/AccelerateDecelerateInterpolator;

    invoke-direct {v4}, Landroid/view/animation/AccelerateDecelerateInterpolator;-><init>()V

    invoke-virtual {v3, v4}, Landroid/animation/ObjectAnimator;->setInterpolator(Landroid/animation/TimeInterpolator;)V

    .line 666
    const-wide/16 v4, 0x4b0

    invoke-virtual {v3, v4, v5}, Landroid/animation/ObjectAnimator;->setDuration(J)Landroid/animation/ObjectAnimator;

    .line 667
    const/4 v4, -0x1

    invoke-virtual {v3, v4}, Landroid/animation/ObjectAnimator;->setRepeatCount(I)V

    .line 668
    new-instance v4, Lcom/miui/internal/widget/ArrowPopupView$3;

    invoke-direct {v4, p0}, Lcom/miui/internal/widget/ArrowPopupView$3;-><init>(Lcom/miui/internal/widget/ArrowPopupView;)V

    invoke-virtual {v3, v4}, Landroid/animation/ObjectAnimator;->addUpdateListener(Landroid/animation/ValueAnimator$AnimatorUpdateListener;)V

    .line 678
    iget-object v4, p0, Lcom/miui/internal/widget/ArrowPopupView;->ue:Landroid/widget/ImageView;

    new-array v5, v10, [F

    aput v6, v5, v8

    aput v1, v5, v9

    aput v6, v5, v7

    invoke-static {v4, v0, v5}, Landroid/animation/ObjectAnimator;->ofFloat(Ljava/lang/Object;Landroid/util/Property;[F)Landroid/animation/ObjectAnimator;

    move-result-object v0

    .line 679
    new-instance v1, Landroid/view/animation/AccelerateDecelerateInterpolator;

    invoke-direct {v1}, Landroid/view/animation/AccelerateDecelerateInterpolator;-><init>()V

    invoke-virtual {v0, v1}, Landroid/animation/ObjectAnimator;->setInterpolator(Landroid/animation/TimeInterpolator;)V

    .line 680
    const-wide/16 v4, 0x4b0

    invoke-virtual {v0, v4, v5}, Landroid/animation/ObjectAnimator;->setDuration(J)Landroid/animation/ObjectAnimator;

    .line 681
    const/4 v1, -0x1

    invoke-virtual {v0, v1}, Landroid/animation/ObjectAnimator;->setRepeatCount(I)V

    .line 683
    iget-object v1, p0, Lcom/miui/internal/widget/ArrowPopupView;->ux:Landroid/animation/AnimatorSet;

    new-array v4, v10, [Landroid/animation/Animator;

    aput-object v2, v4, v8

    aput-object v3, v4, v9

    aput-object v0, v4, v7

    invoke-virtual {v1, v4}, Landroid/animation/AnimatorSet;->playTogether([Landroid/animation/Animator;)V

    .line 684
    iget-object v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->ux:Landroid/animation/AnimatorSet;

    invoke-virtual {v0}, Landroid/animation/AnimatorSet;->start()V

    .line 685
    return-void

    .line 651
    :pswitch_1
    neg-float v1, v1

    .line 652
    goto :goto_0

    .line 655
    :pswitch_2
    sget-object v0, Landroid/view/View;->TRANSLATION_X:Landroid/util/Property;

    goto :goto_0

    .line 659
    :pswitch_3
    neg-float v1, v1

    .line 660
    sget-object v0, Landroid/view/View;->TRANSLATION_X:Landroid/util/Property;

    goto :goto_0

    .line 638
    nop

    :array_0
    .array-data 4
        0xa6
        0x0
    .end array-data

    .line 649
    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_1
        :pswitch_0
        :pswitch_3
        :pswitch_2
    .end packed-switch
.end method

.method static synthetic c(Lcom/miui/internal/widget/ArrowPopupView;)Lmiui/widget/ArrowPopupWindow;
    .locals 1

    .prologue
    .line 45
    iget-object v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->uv:Lmiui/widget/ArrowPopupWindow;

    return-object v0
.end method

.method static synthetic d(Lcom/miui/internal/widget/ArrowPopupView;)Landroid/animation/AnimatorSet;
    .locals 1

    .prologue
    .line 45
    iget-object v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->ux:Landroid/animation/AnimatorSet;

    return-object v0
.end method

.method static synthetic e(Lcom/miui/internal/widget/ArrowPopupView;)Landroid/widget/LinearLayout;
    .locals 1

    .prologue
    .line 45
    iget-object v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->ug:Landroid/widget/LinearLayout;

    return-object v0
.end method

.method static synthetic f(Lcom/miui/internal/widget/ArrowPopupView;)Landroid/widget/ImageView;
    .locals 1

    .prologue
    .line 45
    iget-object v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->ue:Landroid/widget/ImageView;

    return-object v0
.end method

.method static synthetic g(Lcom/miui/internal/widget/ArrowPopupView;)Landroid/animation/Animator$AnimatorListener;
    .locals 1

    .prologue
    .line 45
    iget-object v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->ka:Landroid/animation/Animator$AnimatorListener;

    return-object v0
.end method

.method static synthetic h(Lcom/miui/internal/widget/ArrowPopupView;)I
    .locals 1

    .prologue
    .line 45
    iget v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->uD:I

    return v0
.end method


# virtual methods
.method public animateToDismiss()V
    .locals 9

    .prologue
    const-wide/16 v7, 0xb4

    const/4 v4, 0x0

    const/4 v6, 0x1

    const/4 v5, 0x0

    .line 612
    iget-boolean v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->jN:Z

    if-eqz v0, :cond_0

    .line 631
    :goto_0
    return-void

    .line 615
    :cond_0
    iget-object v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->ux:Landroid/animation/AnimatorSet;

    if-eqz v0, :cond_1

    .line 616
    iget-object v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->ux:Landroid/animation/AnimatorSet;

    invoke-virtual {v0}, Landroid/animation/AnimatorSet;->cancel()V

    .line 619
    :cond_1
    new-instance v0, Landroid/animation/AnimatorSet;

    invoke-direct {v0}, Landroid/animation/AnimatorSet;-><init>()V

    iput-object v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->ux:Landroid/animation/AnimatorSet;

    .line 620
    iget-object v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->ug:Landroid/widget/LinearLayout;

    sget-object v1, Landroid/view/View;->ALPHA:Landroid/util/Property;

    new-array v2, v6, [F

    aput v4, v2, v5

    invoke-static {v0, v1, v2}, Landroid/animation/ObjectAnimator;->ofFloat(Ljava/lang/Object;Landroid/util/Property;[F)Landroid/animation/ObjectAnimator;

    move-result-object v0

    .line 621
    invoke-virtual {v0, v7, v8}, Landroid/animation/ObjectAnimator;->setDuration(J)Landroid/animation/ObjectAnimator;

    .line 622
    iget-object v1, p0, Lcom/miui/internal/widget/ArrowPopupView;->ue:Landroid/widget/ImageView;

    sget-object v2, Landroid/view/View;->ALPHA:Landroid/util/Property;

    new-array v3, v6, [F

    aput v4, v3, v5

    invoke-static {v1, v2, v3}, Landroid/animation/ObjectAnimator;->ofFloat(Ljava/lang/Object;Landroid/util/Property;[F)Landroid/animation/ObjectAnimator;

    move-result-object v1

    .line 623
    invoke-virtual {v1, v7, v8}, Landroid/animation/ObjectAnimator;->setDuration(J)Landroid/animation/ObjectAnimator;

    .line 625
    const-string v2, "RollingPercent"

    new-array v3, v6, [F

    aput v4, v3, v5

    invoke-static {p0, v2, v3}, Landroid/animation/ObjectAnimator;->ofFloat(Ljava/lang/Object;Ljava/lang/String;[F)Landroid/animation/ObjectAnimator;

    move-result-object v2

    .line 626
    const-wide/16 v3, 0x96

    invoke-virtual {v2, v3, v4}, Landroid/animation/ObjectAnimator;->setDuration(J)Landroid/animation/ObjectAnimator;

    .line 628
    iget-object v3, p0, Lcom/miui/internal/widget/ArrowPopupView;->ux:Landroid/animation/AnimatorSet;

    const/4 v4, 0x3

    new-array v4, v4, [Landroid/animation/Animator;

    aput-object v0, v4, v5

    aput-object v1, v4, v6

    const/4 v0, 0x2

    aput-object v2, v4, v0

    invoke-virtual {v3, v4}, Landroid/animation/AnimatorSet;->playTogether([Landroid/animation/Animator;)V

    .line 629
    iget-object v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->ux:Landroid/animation/AnimatorSet;

    iget-object v1, p0, Lcom/miui/internal/widget/ArrowPopupView;->kb:Landroid/animation/Animator$AnimatorListener;

    invoke-virtual {v0, v1}, Landroid/animation/AnimatorSet;->addListener(Landroid/animation/Animator$AnimatorListener;)V

    .line 630
    iget-object v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->ux:Landroid/animation/AnimatorSet;

    invoke-virtual {v0}, Landroid/animation/AnimatorSet;->start()V

    goto :goto_0
.end method

.method public animateToShow()V
    .locals 3

    .prologue
    const/4 v2, 0x0

    .line 575
    invoke-virtual {p0}, Lcom/miui/internal/widget/ArrowPopupView;->getBackground()Landroid/graphics/drawable/Drawable;

    move-result-object v0

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Landroid/graphics/drawable/Drawable;->setAlpha(I)V

    .line 576
    invoke-virtual {p0, v2}, Lcom/miui/internal/widget/ArrowPopupView;->setRollingPercent(F)V

    .line 577
    iget-object v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->ug:Landroid/widget/LinearLayout;

    invoke-virtual {v0, v2}, Landroid/widget/LinearLayout;->setAlpha(F)V

    .line 579
    invoke-virtual {p0}, Lcom/miui/internal/widget/ArrowPopupView;->invalidate()V

    .line 580
    invoke-virtual {p0}, Lcom/miui/internal/widget/ArrowPopupView;->getViewTreeObserver()Landroid/view/ViewTreeObserver;

    move-result-object v0

    new-instance v1, Lcom/miui/internal/widget/ArrowPopupView$1;

    invoke-direct {v1, p0}, Lcom/miui/internal/widget/ArrowPopupView$1;-><init>(Lcom/miui/internal/widget/ArrowPopupView;)V

    invoke-virtual {v0, v1}, Landroid/view/ViewTreeObserver;->addOnPreDrawListener(Landroid/view/ViewTreeObserver$OnPreDrawListener;)V

    .line 609
    return-void
.end method

.method protected dispatchDraw(Landroid/graphics/Canvas;)V
    .locals 4

    .prologue
    const/high16 v2, 0x3f800000

    .line 533
    iget v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->uF:F

    cmpl-float v0, v0, v2

    if-eqz v0, :cond_0

    .line 534
    iget-object v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->uw:Landroid/graphics/RectF;

    .line 535
    iget-object v1, p0, Lcom/miui/internal/widget/ArrowPopupView;->ug:Landroid/widget/LinearLayout;

    invoke-virtual {v1}, Landroid/widget/LinearLayout;->getLeft()I

    move-result v1

    int-to-float v1, v1

    iput v1, v0, Landroid/graphics/RectF;->left:F

    .line 536
    iget-object v1, p0, Lcom/miui/internal/widget/ArrowPopupView;->ug:Landroid/widget/LinearLayout;

    invoke-virtual {v1}, Landroid/widget/LinearLayout;->getRight()I

    move-result v1

    int-to-float v1, v1

    iput v1, v0, Landroid/graphics/RectF;->right:F

    .line 537
    iget-object v1, p0, Lcom/miui/internal/widget/ArrowPopupView;->ug:Landroid/widget/LinearLayout;

    invoke-virtual {v1}, Landroid/widget/LinearLayout;->getBottom()I

    move-result v1

    int-to-float v1, v1

    iput v1, v0, Landroid/graphics/RectF;->bottom:F

    .line 538
    iget-object v1, p0, Lcom/miui/internal/widget/ArrowPopupView;->ug:Landroid/widget/LinearLayout;

    invoke-virtual {v1}, Landroid/widget/LinearLayout;->getTop()I

    move-result v1

    int-to-float v1, v1

    iput v1, v0, Landroid/graphics/RectF;->top:F

    .line 539
    iget v1, p0, Lcom/miui/internal/widget/ArrowPopupView;->uF:F

    sub-float v1, v2, v1

    .line 541
    iget v2, p0, Lcom/miui/internal/widget/ArrowPopupView;->kc:I

    packed-switch v2, :pswitch_data_0

    .line 557
    :goto_0
    const/4 v1, 0x0

    const/16 v2, 0x1f

    invoke-virtual {p1, v0, v1, v2}, Landroid/graphics/Canvas;->saveLayer(Landroid/graphics/RectF;Landroid/graphics/Paint;I)I

    .line 558
    invoke-super {p0, p1}, Landroid/widget/FrameLayout;->dispatchDraw(Landroid/graphics/Canvas;)V

    .line 559
    invoke-virtual {p1}, Landroid/graphics/Canvas;->restore()V

    .line 563
    :goto_1
    return-void

    .line 543
    :pswitch_0
    iget-object v2, p0, Lcom/miui/internal/widget/ArrowPopupView;->ug:Landroid/widget/LinearLayout;

    invoke-virtual {v2}, Landroid/widget/LinearLayout;->getBottom()I

    move-result v2

    int-to-float v2, v2

    iget-object v3, p0, Lcom/miui/internal/widget/ArrowPopupView;->ug:Landroid/widget/LinearLayout;

    invoke-virtual {v3}, Landroid/widget/LinearLayout;->getHeight()I

    move-result v3

    int-to-float v3, v3

    mul-float/2addr v1, v3

    sub-float v1, v2, v1

    float-to-int v1, v1

    int-to-float v1, v1

    iput v1, v0, Landroid/graphics/RectF;->bottom:F

    goto :goto_0

    .line 547
    :pswitch_1
    iget-object v2, p0, Lcom/miui/internal/widget/ArrowPopupView;->ug:Landroid/widget/LinearLayout;

    invoke-virtual {v2}, Landroid/widget/LinearLayout;->getTop()I

    move-result v2

    int-to-float v2, v2

    iget-object v3, p0, Lcom/miui/internal/widget/ArrowPopupView;->ug:Landroid/widget/LinearLayout;

    invoke-virtual {v3}, Landroid/widget/LinearLayout;->getHeight()I

    move-result v3

    int-to-float v3, v3

    mul-float/2addr v1, v3

    add-float/2addr v1, v2

    float-to-int v1, v1

    int-to-float v1, v1

    iput v1, v0, Landroid/graphics/RectF;->top:F

    goto :goto_0

    .line 550
    :pswitch_2
    iget-object v2, p0, Lcom/miui/internal/widget/ArrowPopupView;->ug:Landroid/widget/LinearLayout;

    invoke-virtual {v2}, Landroid/widget/LinearLayout;->getRight()I

    move-result v2

    int-to-float v2, v2

    iget-object v3, p0, Lcom/miui/internal/widget/ArrowPopupView;->ug:Landroid/widget/LinearLayout;

    invoke-virtual {v3}, Landroid/widget/LinearLayout;->getWidth()I

    move-result v3

    int-to-float v3, v3

    mul-float/2addr v1, v3

    sub-float v1, v2, v1

    float-to-int v1, v1

    int-to-float v1, v1

    iput v1, v0, Landroid/graphics/RectF;->right:F

    goto :goto_0

    .line 553
    :pswitch_3
    iget-object v2, p0, Lcom/miui/internal/widget/ArrowPopupView;->ug:Landroid/widget/LinearLayout;

    invoke-virtual {v2}, Landroid/widget/LinearLayout;->getLeft()I

    move-result v2

    int-to-float v2, v2

    iget-object v3, p0, Lcom/miui/internal/widget/ArrowPopupView;->ug:Landroid/widget/LinearLayout;

    invoke-virtual {v3}, Landroid/widget/LinearLayout;->getWidth()I

    move-result v3

    int-to-float v3, v3

    mul-float/2addr v1, v3

    add-float/2addr v1, v2

    float-to-int v1, v1

    int-to-float v1, v1

    iput v1, v0, Landroid/graphics/RectF;->left:F

    goto :goto_0

    .line 561
    :cond_0
    invoke-super {p0, p1}, Landroid/widget/FrameLayout;->dispatchDraw(Landroid/graphics/Canvas;)V

    goto :goto_1

    .line 541
    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_1
        :pswitch_0
        :pswitch_3
        :pswitch_2
    .end packed-switch
.end method

.method public enableShowingAnimation(Z)V
    .locals 0

    .prologue
    .line 781
    iput-boolean p1, p0, Lcom/miui/internal/widget/ArrowPopupView;->uC:Z

    .line 782
    return-void
.end method

.method public getArrowMode()I
    .locals 1

    .prologue
    .line 724
    iget v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->kc:I

    return v0
.end method

.method public getContentView()Landroid/view/View;
    .locals 2

    .prologue
    .line 254
    iget-object v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->uf:Landroid/widget/FrameLayout;

    invoke-virtual {v0}, Landroid/widget/FrameLayout;->getChildCount()I

    move-result v0

    if-lez v0, :cond_0

    .line 255
    iget-object v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->uf:Landroid/widget/FrameLayout;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Landroid/widget/FrameLayout;->getChildAt(I)Landroid/view/View;

    move-result-object v0

    .line 257
    :goto_0
    return-object v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public getNegativeButton()Landroid/widget/Button;
    .locals 1

    .prologue
    .line 286
    iget-object v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->uj:Landroid/widget/Button;

    return-object v0
.end method

.method public getPositiveButton()Landroid/widget/Button;
    .locals 1

    .prologue
    .line 282
    iget-object v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->ui:Landroid/widget/Button;

    return-object v0
.end method

.method public getRollingPercent()F
    .locals 1

    .prologue
    .line 566
    iget v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->uF:F

    return v0
.end method

.method protected onDraw(Landroid/graphics/Canvas;)V
    .locals 12

    .prologue
    const/high16 v11, 0x3f800000

    const/4 v4, 0x0

    const/4 v1, 0x0

    .line 449
    iget-object v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->lp:Landroid/graphics/drawable/Drawable;

    if-eqz v0, :cond_0

    .line 529
    :goto_0
    return-void

    .line 457
    :cond_0
    iget v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->uy:I

    iget-object v2, p0, Lcom/miui/internal/widget/ArrowPopupView;->ug:Landroid/widget/LinearLayout;

    invoke-virtual {v2}, Landroid/widget/LinearLayout;->getWidth()I

    move-result v2

    div-int/lit8 v2, v2, 0x2

    add-int v5, v0, v2

    .line 458
    iget v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->uz:I

    iget-object v2, p0, Lcom/miui/internal/widget/ArrowPopupView;->ug:Landroid/widget/LinearLayout;

    invoke-virtual {v2}, Landroid/widget/LinearLayout;->getHeight()I

    move-result v2

    div-int/lit8 v2, v2, 0x2

    add-int v6, v0, v2

    .line 460
    iget-object v7, p0, Lcom/miui/internal/widget/ArrowPopupView;->uw:Landroid/graphics/RectF;

    .line 461
    iget v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->uF:F

    cmpl-float v0, v0, v11

    if-eqz v0, :cond_1

    .line 462
    iput v4, v7, Landroid/graphics/RectF;->left:F

    .line 463
    iput v4, v7, Landroid/graphics/RectF;->top:F

    .line 464
    iget-object v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->ug:Landroid/widget/LinearLayout;

    invoke-virtual {v0}, Landroid/widget/LinearLayout;->getWidth()I

    move-result v0

    int-to-float v0, v0

    iput v0, v7, Landroid/graphics/RectF;->right:F

    .line 465
    iget-object v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->ug:Landroid/widget/LinearLayout;

    invoke-virtual {v0}, Landroid/widget/LinearLayout;->getHeight()I

    move-result v0

    int-to-float v0, v0

    iput v0, v7, Landroid/graphics/RectF;->bottom:F

    .line 468
    :cond_1
    iget v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->kc:I

    packed-switch v0, :pswitch_data_0

    move v0, v1

    move v2, v1

    move v3, v4

    .line 494
    :goto_1
    invoke-virtual {p1}, Landroid/graphics/Canvas;->save()I

    move-result v8

    .line 495
    int-to-float v9, v5

    int-to-float v10, v6

    invoke-virtual {p1, v3, v9, v10}, Landroid/graphics/Canvas;->rotate(FFF)V

    .line 496
    iget v3, p0, Lcom/miui/internal/widget/ArrowPopupView;->kc:I

    packed-switch v3, :pswitch_data_1

    .line 528
    :goto_2
    invoke-virtual {p1, v8}, Landroid/graphics/Canvas;->restoreToCount(I)V

    goto :goto_0

    .line 471
    :pswitch_0
    iget v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->uA:I

    iget-object v2, p0, Lcom/miui/internal/widget/ArrowPopupView;->ue:Landroid/widget/ImageView;

    invoke-virtual {v2}, Landroid/widget/ImageView;->getMeasuredWidth()I

    move-result v2

    div-int/lit8 v2, v2, 0x2

    add-int/2addr v0, v2

    .line 472
    iget v2, p0, Lcom/miui/internal/widget/ArrowPopupView;->uy:I

    sub-int v2, v0, v2

    .line 473
    iget-object v3, p0, Lcom/miui/internal/widget/ArrowPopupView;->ug:Landroid/widget/LinearLayout;

    invoke-virtual {v3}, Landroid/widget/LinearLayout;->getRight()I

    move-result v3

    sub-int v0, v3, v0

    move v3, v4

    .line 474
    goto :goto_1

    .line 476
    :pswitch_1
    const/high16 v3, 0x43340000

    .line 477
    iget v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->uA:I

    iget-object v2, p0, Lcom/miui/internal/widget/ArrowPopupView;->ue:Landroid/widget/ImageView;

    invoke-virtual {v2}, Landroid/widget/ImageView;->getMeasuredWidth()I

    move-result v2

    div-int/lit8 v2, v2, 0x2

    add-int/2addr v0, v2

    .line 478
    iget-object v2, p0, Lcom/miui/internal/widget/ArrowPopupView;->ug:Landroid/widget/LinearLayout;

    invoke-virtual {v2}, Landroid/widget/LinearLayout;->getRight()I

    move-result v2

    sub-int/2addr v2, v0

    .line 479
    iget v8, p0, Lcom/miui/internal/widget/ArrowPopupView;->uy:I

    sub-int/2addr v0, v8

    .line 480
    goto :goto_1

    .line 482
    :pswitch_2
    const/high16 v3, -0x3d4c0000

    .line 483
    iget v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->uB:I

    iget-object v2, p0, Lcom/miui/internal/widget/ArrowPopupView;->ue:Landroid/widget/ImageView;

    invoke-virtual {v2}, Landroid/widget/ImageView;->getMeasuredHeight()I

    move-result v2

    div-int/lit8 v2, v2, 0x2

    add-int/2addr v0, v2

    .line 484
    iget-object v2, p0, Lcom/miui/internal/widget/ArrowPopupView;->ug:Landroid/widget/LinearLayout;

    invoke-virtual {v2}, Landroid/widget/LinearLayout;->getBottom()I

    move-result v2

    sub-int/2addr v2, v0

    .line 485
    iget v8, p0, Lcom/miui/internal/widget/ArrowPopupView;->uz:I

    sub-int/2addr v0, v8

    .line 486
    goto :goto_1

    .line 488
    :pswitch_3
    const/high16 v3, 0x42b40000

    .line 489
    iget v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->uB:I

    iget-object v2, p0, Lcom/miui/internal/widget/ArrowPopupView;->ue:Landroid/widget/ImageView;

    invoke-virtual {v2}, Landroid/widget/ImageView;->getMeasuredHeight()I

    move-result v2

    div-int/lit8 v2, v2, 0x2

    add-int/2addr v0, v2

    .line 490
    iget v2, p0, Lcom/miui/internal/widget/ArrowPopupView;->uz:I

    sub-int v2, v0, v2

    .line 491
    iget-object v8, p0, Lcom/miui/internal/widget/ArrowPopupView;->ug:Landroid/widget/LinearLayout;

    invoke-virtual {v8}, Landroid/widget/LinearLayout;->getBottom()I

    move-result v8

    sub-int v0, v8, v0

    goto :goto_1

    .line 499
    :pswitch_4
    iget v3, p0, Lcom/miui/internal/widget/ArrowPopupView;->uy:I

    int-to-float v3, v3

    iget v5, p0, Lcom/miui/internal/widget/ArrowPopupView;->uz:I

    int-to-float v5, v5

    invoke-virtual {p1, v3, v5}, Landroid/graphics/Canvas;->translate(FF)V

    .line 500
    iget-object v3, p0, Lcom/miui/internal/widget/ArrowPopupView;->um:Landroid/graphics/drawable/Drawable;

    iget-object v5, p0, Lcom/miui/internal/widget/ArrowPopupView;->ug:Landroid/widget/LinearLayout;

    invoke-virtual {v5}, Landroid/widget/LinearLayout;->getHeight()I

    move-result v5

    invoke-virtual {v3, v1, v1, v2, v5}, Landroid/graphics/drawable/Drawable;->setBounds(IIII)V

    .line 501
    iget v3, p0, Lcom/miui/internal/widget/ArrowPopupView;->uF:F

    cmpl-float v3, v3, v11

    if-eqz v3, :cond_2

    .line 502
    iget-object v3, p0, Lcom/miui/internal/widget/ArrowPopupView;->ug:Landroid/widget/LinearLayout;

    invoke-virtual {v3}, Landroid/widget/LinearLayout;->getHeight()I

    move-result v3

    int-to-float v3, v3

    iget v5, p0, Lcom/miui/internal/widget/ArrowPopupView;->uF:F

    mul-float/2addr v3, v5

    float-to-int v3, v3

    int-to-float v3, v3

    iput v3, v7, Landroid/graphics/RectF;->bottom:F

    .line 503
    invoke-virtual {p1, v7}, Landroid/graphics/Canvas;->clipRect(Landroid/graphics/RectF;)Z

    .line 507
    :goto_3
    iget-object v3, p0, Lcom/miui/internal/widget/ArrowPopupView;->um:Landroid/graphics/drawable/Drawable;

    invoke-virtual {v3, p1}, Landroid/graphics/drawable/Drawable;->draw(Landroid/graphics/Canvas;)V

    .line 508
    int-to-float v2, v2

    invoke-virtual {p1, v2, v4}, Landroid/graphics/Canvas;->translate(FF)V

    .line 509
    iget-object v2, p0, Lcom/miui/internal/widget/ArrowPopupView;->un:Landroid/graphics/drawable/Drawable;

    iget-object v3, p0, Lcom/miui/internal/widget/ArrowPopupView;->ug:Landroid/widget/LinearLayout;

    invoke-virtual {v3}, Landroid/widget/LinearLayout;->getHeight()I

    move-result v3

    invoke-virtual {v2, v1, v1, v0, v3}, Landroid/graphics/drawable/Drawable;->setBounds(IIII)V

    .line 510
    iget-object v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->un:Landroid/graphics/drawable/Drawable;

    invoke-virtual {v0, p1}, Landroid/graphics/drawable/Drawable;->draw(Landroid/graphics/Canvas;)V

    goto/16 :goto_2

    .line 505
    :cond_2
    iget v3, p0, Lcom/miui/internal/widget/ArrowPopupView;->kc:I

    const/4 v5, 0x1

    if-ne v3, v5, :cond_3

    iget v3, p0, Lcom/miui/internal/widget/ArrowPopupView;->uD:I

    int-to-float v3, v3

    :goto_4
    invoke-virtual {p1, v4, v3}, Landroid/graphics/Canvas;->translate(FF)V

    goto :goto_3

    :cond_3
    iget v3, p0, Lcom/miui/internal/widget/ArrowPopupView;->uD:I

    neg-int v3, v3

    int-to-float v3, v3

    goto :goto_4

    .line 514
    :pswitch_5
    iget-object v3, p0, Lcom/miui/internal/widget/ArrowPopupView;->ug:Landroid/widget/LinearLayout;

    invoke-virtual {v3}, Landroid/widget/LinearLayout;->getHeight()I

    move-result v3

    div-int/lit8 v3, v3, 0x2

    sub-int v3, v5, v3

    int-to-float v3, v3

    iget-object v5, p0, Lcom/miui/internal/widget/ArrowPopupView;->ug:Landroid/widget/LinearLayout;

    invoke-virtual {v5}, Landroid/widget/LinearLayout;->getWidth()I

    move-result v5

    div-int/lit8 v5, v5, 0x2

    sub-int v5, v6, v5

    int-to-float v5, v5

    invoke-virtual {p1, v3, v5}, Landroid/graphics/Canvas;->translate(FF)V

    .line 515
    iget-object v3, p0, Lcom/miui/internal/widget/ArrowPopupView;->um:Landroid/graphics/drawable/Drawable;

    iget-object v5, p0, Lcom/miui/internal/widget/ArrowPopupView;->ug:Landroid/widget/LinearLayout;

    invoke-virtual {v5}, Landroid/widget/LinearLayout;->getWidth()I

    move-result v5

    invoke-virtual {v3, v1, v1, v2, v5}, Landroid/graphics/drawable/Drawable;->setBounds(IIII)V

    .line 516
    iget v3, p0, Lcom/miui/internal/widget/ArrowPopupView;->uF:F

    cmpl-float v3, v3, v11

    if-eqz v3, :cond_4

    .line 517
    iget-object v3, p0, Lcom/miui/internal/widget/ArrowPopupView;->ug:Landroid/widget/LinearLayout;

    invoke-virtual {v3}, Landroid/widget/LinearLayout;->getWidth()I

    move-result v3

    int-to-float v3, v3

    iget v5, p0, Lcom/miui/internal/widget/ArrowPopupView;->uF:F

    mul-float/2addr v3, v5

    float-to-int v3, v3

    int-to-float v3, v3

    iput v3, v7, Landroid/graphics/RectF;->bottom:F

    .line 518
    invoke-virtual {p1, v7}, Landroid/graphics/Canvas;->clipRect(Landroid/graphics/RectF;)Z

    .line 522
    :goto_5
    iget-object v3, p0, Lcom/miui/internal/widget/ArrowPopupView;->um:Landroid/graphics/drawable/Drawable;

    invoke-virtual {v3, p1}, Landroid/graphics/drawable/Drawable;->draw(Landroid/graphics/Canvas;)V

    .line 523
    int-to-float v2, v2

    invoke-virtual {p1, v2, v4}, Landroid/graphics/Canvas;->translate(FF)V

    .line 524
    iget-object v2, p0, Lcom/miui/internal/widget/ArrowPopupView;->un:Landroid/graphics/drawable/Drawable;

    iget-object v3, p0, Lcom/miui/internal/widget/ArrowPopupView;->ug:Landroid/widget/LinearLayout;

    invoke-virtual {v3}, Landroid/widget/LinearLayout;->getWidth()I

    move-result v3

    invoke-virtual {v2, v1, v1, v0, v3}, Landroid/graphics/drawable/Drawable;->setBounds(IIII)V

    .line 525
    iget-object v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->un:Landroid/graphics/drawable/Drawable;

    invoke-virtual {v0, p1}, Landroid/graphics/drawable/Drawable;->draw(Landroid/graphics/Canvas;)V

    goto/16 :goto_2

    .line 520
    :cond_4
    iget v3, p0, Lcom/miui/internal/widget/ArrowPopupView;->kc:I

    const/4 v5, 0x3

    if-ne v3, v5, :cond_5

    iget v3, p0, Lcom/miui/internal/widget/ArrowPopupView;->uD:I

    int-to-float v3, v3

    :goto_6
    invoke-virtual {p1, v4, v3}, Landroid/graphics/Canvas;->translate(FF)V

    goto :goto_5

    :cond_5
    iget v3, p0, Lcom/miui/internal/widget/ArrowPopupView;->uD:I

    neg-int v3, v3

    int-to-float v3, v3

    goto :goto_6

    .line 468
    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_1
        :pswitch_0
        :pswitch_3
        :pswitch_2
    .end packed-switch

    .line 496
    :pswitch_data_1
    .packed-switch 0x0
        :pswitch_4
        :pswitch_4
        :pswitch_5
        :pswitch_5
    .end packed-switch
.end method

.method protected onFinishInflate()V
    .locals 5

    .prologue
    .line 217
    invoke-super {p0}, Landroid/widget/FrameLayout;->onFinishInflate()V

    .line 218
    sget v0, Lcom/miui/internal/R$id;->popup_arrow:I

    invoke-virtual {p0, v0}, Lcom/miui/internal/widget/ArrowPopupView;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/ImageView;

    iput-object v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->ue:Landroid/widget/ImageView;

    .line 219
    const v0, 0x1020002

    invoke-virtual {p0, v0}, Lcom/miui/internal/widget/ArrowPopupView;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/FrameLayout;

    iput-object v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->uf:Landroid/widget/FrameLayout;

    .line 220
    sget v0, Lcom/miui/internal/R$id;->content_wrapper:I

    invoke-virtual {p0, v0}, Lcom/miui/internal/widget/ArrowPopupView;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/LinearLayout;

    iput-object v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->ug:Landroid/widget/LinearLayout;

    .line 221
    iget-object v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->ug:Landroid/widget/LinearLayout;

    iget-object v1, p0, Lcom/miui/internal/widget/ArrowPopupView;->lp:Landroid/graphics/drawable/Drawable;

    invoke-virtual {v0, v1}, Landroid/widget/LinearLayout;->setBackground(Landroid/graphics/drawable/Drawable;)V

    .line 223
    iget-object v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->um:Landroid/graphics/drawable/Drawable;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->un:Landroid/graphics/drawable/Drawable;

    if-eqz v0, :cond_0

    .line 224
    new-instance v0, Landroid/graphics/Rect;

    invoke-direct {v0}, Landroid/graphics/Rect;-><init>()V

    .line 225
    iget-object v1, p0, Lcom/miui/internal/widget/ArrowPopupView;->um:Landroid/graphics/drawable/Drawable;

    invoke-virtual {v1, v0}, Landroid/graphics/drawable/Drawable;->getPadding(Landroid/graphics/Rect;)Z

    .line 226
    iget-object v1, p0, Lcom/miui/internal/widget/ArrowPopupView;->ug:Landroid/widget/LinearLayout;

    iget v2, v0, Landroid/graphics/Rect;->top:I

    iget v3, v0, Landroid/graphics/Rect;->top:I

    iget v4, v0, Landroid/graphics/Rect;->top:I

    iget v0, v0, Landroid/graphics/Rect;->top:I

    invoke-virtual {v1, v2, v3, v4, v0}, Landroid/widget/LinearLayout;->setPadding(IIII)V

    .line 229
    :cond_0
    sget v0, Lcom/miui/internal/R$id;->title_layout:I

    invoke-virtual {p0, v0}, Lcom/miui/internal/widget/ArrowPopupView;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/LinearLayout;

    iput-object v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->hI:Landroid/widget/LinearLayout;

    .line 230
    iget-object v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->hI:Landroid/widget/LinearLayout;

    iget-object v1, p0, Lcom/miui/internal/widget/ArrowPopupView;->uo:Landroid/graphics/drawable/Drawable;

    invoke-virtual {v0, v1}, Landroid/widget/LinearLayout;->setBackground(Landroid/graphics/drawable/Drawable;)V

    .line 231
    const v0, 0x1020016

    invoke-virtual {p0, v0}, Lcom/miui/internal/widget/ArrowPopupView;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/TextView;

    iput-object v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->uh:Landroid/widget/TextView;

    .line 232
    const v0, 0x102001a

    invoke-virtual {p0, v0}, Lcom/miui/internal/widget/ArrowPopupView;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/Button;

    iput-object v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->ui:Landroid/widget/Button;

    .line 233
    const v0, 0x1020019

    invoke-virtual {p0, v0}, Lcom/miui/internal/widget/ArrowPopupView;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/Button;

    iput-object v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->uj:Landroid/widget/Button;

    .line 235
    new-instance v0, Lcom/miui/internal/widget/ArrowPopupView$a;

    invoke-direct {v0, p0}, Lcom/miui/internal/widget/ArrowPopupView$a;-><init>(Lcom/miui/internal/widget/ArrowPopupView;)V

    iput-object v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->uk:Lcom/miui/internal/widget/ArrowPopupView$a;

    .line 236
    new-instance v0, Lcom/miui/internal/widget/ArrowPopupView$a;

    invoke-direct {v0, p0}, Lcom/miui/internal/widget/ArrowPopupView$a;-><init>(Lcom/miui/internal/widget/ArrowPopupView;)V

    iput-object v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->ul:Lcom/miui/internal/widget/ArrowPopupView$a;

    .line 237
    iget-object v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->ui:Landroid/widget/Button;

    iget-object v1, p0, Lcom/miui/internal/widget/ArrowPopupView;->uk:Lcom/miui/internal/widget/ArrowPopupView$a;

    invoke-virtual {v0, v1}, Landroid/widget/Button;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 238
    iget-object v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->uj:Landroid/widget/Button;

    iget-object v1, p0, Lcom/miui/internal/widget/ArrowPopupView;->ul:Lcom/miui/internal/widget/ArrowPopupView$a;

    invoke-virtual {v0, v1}, Landroid/widget/Button;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 239
    return-void
.end method

.method protected onLayout(ZIIII)V
    .locals 2

    .prologue
    .line 440
    iget v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->kc:I

    const/4 v1, -0x1

    if-ne v0, v1, :cond_0

    .line 441
    invoke-direct {p0}, Lcom/miui/internal/widget/ArrowPopupView;->aw()V

    .line 445
    :goto_0
    return-void

    .line 443
    :cond_0
    invoke-direct {p0}, Lcom/miui/internal/widget/ArrowPopupView;->ax()V

    goto :goto_0
.end method

.method public onTouch(Landroid/view/View;Landroid/view/MotionEvent;)Z
    .locals 5

    .prologue
    const/4 v0, 0x1

    .line 767
    invoke-virtual {p2}, Landroid/view/MotionEvent;->getX()F

    move-result v1

    float-to-int v1, v1

    .line 768
    invoke-virtual {p2}, Landroid/view/MotionEvent;->getY()F

    move-result v2

    float-to-int v2, v2

    .line 771
    iget-object v3, p0, Lcom/miui/internal/widget/ArrowPopupView;->nN:Landroid/graphics/Rect;

    .line 772
    iget-object v4, p0, Lcom/miui/internal/widget/ArrowPopupView;->ug:Landroid/widget/LinearLayout;

    invoke-virtual {v4, v3}, Landroid/widget/LinearLayout;->getHitRect(Landroid/graphics/Rect;)V

    .line 773
    invoke-virtual {p2}, Landroid/view/MotionEvent;->getAction()I

    move-result v4

    if-nez v4, :cond_1

    invoke-virtual {v3, v1, v2}, Landroid/graphics/Rect;->contains(II)Z

    move-result v1

    if-nez v1, :cond_1

    .line 774
    iget-object v1, p0, Lcom/miui/internal/widget/ArrowPopupView;->uv:Lmiui/widget/ArrowPopupWindow;

    invoke-virtual {v1, v0}, Lmiui/widget/ArrowPopupWindow;->dismiss(Z)V

    .line 777
    :cond_0
    :goto_0
    return v0

    :cond_1
    iget-object v1, p0, Lcom/miui/internal/widget/ArrowPopupView;->jL:Landroid/view/View$OnTouchListener;

    if-eqz v1, :cond_2

    iget-object v1, p0, Lcom/miui/internal/widget/ArrowPopupView;->jL:Landroid/view/View$OnTouchListener;

    invoke-interface {v1, p1, p2}, Landroid/view/View$OnTouchListener;->onTouch(Landroid/view/View;Landroid/view/MotionEvent;)Z

    move-result v1

    if-nez v1, :cond_0

    :cond_2
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public setAnchor(Landroid/view/View;)V
    .locals 0

    .prologue
    .line 749
    iput-object p1, p0, Lcom/miui/internal/widget/ArrowPopupView;->jD:Landroid/view/View;

    .line 750
    return-void
.end method

.method public setArrowMode(I)V
    .locals 2

    .prologue
    .line 728
    iput p1, p0, Lcom/miui/internal/widget/ArrowPopupView;->kc:I

    .line 729
    packed-switch p1, :pswitch_data_0

    .line 746
    :goto_0
    return-void

    .line 731
    :pswitch_0
    iget-object v1, p0, Lcom/miui/internal/widget/ArrowPopupView;->ue:Landroid/widget/ImageView;

    iget-object v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->hI:Landroid/widget/LinearLayout;

    invoke-virtual {v0}, Landroid/widget/LinearLayout;->getVisibility()I

    move-result v0

    if-nez v0, :cond_0

    iget-object v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->ur:Landroid/graphics/drawable/Drawable;

    :goto_1
    invoke-virtual {v1, v0}, Landroid/widget/ImageView;->setImageDrawable(Landroid/graphics/drawable/Drawable;)V

    goto :goto_0

    :cond_0
    iget-object v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->uq:Landroid/graphics/drawable/Drawable;

    goto :goto_1

    .line 735
    :pswitch_1
    iget-object v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->ue:Landroid/widget/ImageView;

    iget-object v1, p0, Lcom/miui/internal/widget/ArrowPopupView;->us:Landroid/graphics/drawable/Drawable;

    invoke-virtual {v0, v1}, Landroid/widget/ImageView;->setImageDrawable(Landroid/graphics/drawable/Drawable;)V

    goto :goto_0

    .line 739
    :pswitch_2
    iget-object v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->ue:Landroid/widget/ImageView;

    iget-object v1, p0, Lcom/miui/internal/widget/ArrowPopupView;->uu:Landroid/graphics/drawable/Drawable;

    invoke-virtual {v0, v1}, Landroid/widget/ImageView;->setImageDrawable(Landroid/graphics/drawable/Drawable;)V

    goto :goto_0

    .line 743
    :pswitch_3
    iget-object v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->ue:Landroid/widget/ImageView;

    iget-object v1, p0, Lcom/miui/internal/widget/ArrowPopupView;->ut:Landroid/graphics/drawable/Drawable;

    invoke-virtual {v0, v1}, Landroid/widget/ImageView;->setImageDrawable(Landroid/graphics/drawable/Drawable;)V

    goto :goto_0

    .line 729
    nop

    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_1
        :pswitch_0
        :pswitch_3
        :pswitch_2
    .end packed-switch
.end method

.method public setArrowPopupWindow(Lmiui/widget/ArrowPopupWindow;)V
    .locals 0

    .prologue
    .line 758
    iput-object p1, p0, Lcom/miui/internal/widget/ArrowPopupView;->uv:Lmiui/widget/ArrowPopupWindow;

    .line 759
    return-void
.end method

.method public setContentView(I)V
    .locals 2

    .prologue
    .line 261
    invoke-virtual {p0}, Lcom/miui/internal/widget/ArrowPopupView;->getContext()Landroid/content/Context;

    move-result-object v0

    invoke-static {v0}, Landroid/view/LayoutInflater;->from(Landroid/content/Context;)Landroid/view/LayoutInflater;

    move-result-object v0

    const/4 v1, 0x0

    invoke-virtual {v0, p1, v1}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;)Landroid/view/View;

    move-result-object v0

    invoke-virtual {p0, v0}, Lcom/miui/internal/widget/ArrowPopupView;->setContentView(Landroid/view/View;)V

    .line 262
    return-void
.end method

.method public setContentView(Landroid/view/View;)V
    .locals 2

    .prologue
    const/4 v1, -0x2

    .line 242
    new-instance v0, Landroid/view/ViewGroup$LayoutParams;

    invoke-direct {v0, v1, v1}, Landroid/view/ViewGroup$LayoutParams;-><init>(II)V

    invoke-virtual {p0, p1, v0}, Lcom/miui/internal/widget/ArrowPopupView;->setContentView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 244
    return-void
.end method

.method public setContentView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V
    .locals 1

    .prologue
    .line 247
    iget-object v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->uf:Landroid/widget/FrameLayout;

    invoke-virtual {v0}, Landroid/widget/FrameLayout;->removeAllViews()V

    .line 248
    if-eqz p1, :cond_0

    .line 249
    iget-object v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->uf:Landroid/widget/FrameLayout;

    invoke-virtual {v0, p1, p2}, Landroid/widget/FrameLayout;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 251
    :cond_0
    return-void
.end method

.method public setNegativeButton(Ljava/lang/CharSequence;Landroid/view/View$OnClickListener;)V
    .locals 2

    .prologue
    .line 270
    iget-object v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->uj:Landroid/widget/Button;

    invoke-virtual {v0, p1}, Landroid/widget/Button;->setText(Ljava/lang/CharSequence;)V

    .line 271
    iget-object v1, p0, Lcom/miui/internal/widget/ArrowPopupView;->uj:Landroid/widget/Button;

    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_0

    const/16 v0, 0x8

    :goto_0
    invoke-virtual {v1, v0}, Landroid/widget/Button;->setVisibility(I)V

    .line 272
    iget-object v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->ul:Lcom/miui/internal/widget/ArrowPopupView$a;

    invoke-virtual {v0, p2}, Lcom/miui/internal/widget/ArrowPopupView$a;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 273
    return-void

    .line 271
    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public setOffset(II)V
    .locals 0

    .prologue
    .line 753
    iput p1, p0, Lcom/miui/internal/widget/ArrowPopupView;->jG:I

    .line 754
    iput p2, p0, Lcom/miui/internal/widget/ArrowPopupView;->jH:I

    .line 755
    return-void
.end method

.method public setPositiveButton(Ljava/lang/CharSequence;Landroid/view/View$OnClickListener;)V
    .locals 2

    .prologue
    .line 276
    iget-object v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->ui:Landroid/widget/Button;

    invoke-virtual {v0, p1}, Landroid/widget/Button;->setText(Ljava/lang/CharSequence;)V

    .line 277
    iget-object v1, p0, Lcom/miui/internal/widget/ArrowPopupView;->ui:Landroid/widget/Button;

    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_0

    const/16 v0, 0x8

    :goto_0
    invoke-virtual {v1, v0}, Landroid/widget/Button;->setVisibility(I)V

    .line 278
    iget-object v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->uk:Lcom/miui/internal/widget/ArrowPopupView$a;

    invoke-virtual {v0, p2}, Lcom/miui/internal/widget/ArrowPopupView$a;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 279
    return-void

    .line 277
    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public setRollingPercent(F)V
    .locals 0

    .prologue
    .line 570
    iput p1, p0, Lcom/miui/internal/widget/ArrowPopupView;->uF:F

    .line 571
    invoke-virtual {p0}, Lcom/miui/internal/widget/ArrowPopupView;->postInvalidateOnAnimation()V

    .line 572
    return-void
.end method

.method public setTitle(Ljava/lang/CharSequence;)V
    .locals 2

    .prologue
    .line 265
    iget-object v1, p0, Lcom/miui/internal/widget/ArrowPopupView;->hI:Landroid/widget/LinearLayout;

    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_0

    const/16 v0, 0x8

    :goto_0
    invoke-virtual {v1, v0}, Landroid/widget/LinearLayout;->setVisibility(I)V

    .line 266
    iget-object v0, p0, Lcom/miui/internal/widget/ArrowPopupView;->uh:Landroid/widget/TextView;

    invoke-virtual {v0, p1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 267
    return-void

    .line 265
    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public setTouchInterceptor(Landroid/view/View$OnTouchListener;)V
    .locals 0

    .prologue
    .line 762
    iput-object p1, p0, Lcom/miui/internal/widget/ArrowPopupView;->jL:Landroid/view/View$OnTouchListener;

    .line 763
    return-void
.end method
