.class public Lmiui/view/ViewPager;
.super Landroid/view/ViewGroup;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lmiui/view/ViewPager$LayoutParams;,
        Lmiui/view/ViewPager$d;,
        Lmiui/view/ViewPager$SavedState;,
        Lmiui/view/ViewPager$c;,
        Lmiui/view/ViewPager$b;,
        Lmiui/view/ViewPager$SimpleOnPageChangeListener;,
        Lmiui/view/ViewPager$OnPageChangeListener;,
        Lmiui/view/ViewPager$a;
    }
.end annotation


# static fields
.field private static final Cf:Z = false

.field private static final Cg:I = 0x3

.field private static final Ch:I = 0x19

.field private static final Ci:I = 0xfa

.field private static final Cj:I = 0x10

.field private static final Ck:[I

.field private static final Cl:Ljava/util/Comparator;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Comparator",
            "<",
            "Lmiui/view/ViewPager$a;",
            ">;"
        }
    .end annotation
.end field

.field private static final DEBUG:Z = false

.field private static final Dd:I = 0x2

.field static final Dq:F = 0.05f

.field private static final INVALID_POINTER:I = -0x1

.field public static final SCROLL_STATE_DRAGGING:I = 0x1

.field public static final SCROLL_STATE_IDLE:I = 0x0

.field public static final SCROLL_STATE_SETTLING:I = 0x2

.field private static final TAG:Ljava/lang/String; = "ViewPager"

.field private static final za:I = 0x320

.field private static final zs:Landroid/view/animation/Interpolator;


# instance fields
.field private CA:Z

.field private CB:Z

.field private CC:Z

.field private CE:I

.field private CJ:Z

.field private CP:Z

.field private CQ:Z

.field private CS:I

.field private CT:I

.field private CW:F

.field private CX:Z

.field private final Cm:Lmiui/view/ViewPager$a;

.field private Cn:Lmiui/view/PagerAdapter;

.field private Co:I

.field private Cp:I

.field private Cq:Landroid/os/Parcelable;

.field private Cr:Lmiui/view/ViewPager$d;

.field private Cs:I

.field private Ct:Landroid/graphics/drawable/Drawable;

.field private Cu:I

.field private Cv:I

.field private Cw:F

.field private Cx:F

.field private Cy:I

.field private Cz:I

.field private Da:I

.field private Db:I

.field private Dc:I

.field private De:Z

.field private Df:J

.field private Dg:Landroid/widget/EdgeEffect;

.field private Dh:Landroid/widget/EdgeEffect;

.field private Di:Z

.field private Dj:Z

.field private Dk:I

.field private Dl:Lmiui/view/ViewPager$OnPageChangeListener;

.field private Dm:Lmiui/view/ViewPager$OnPageChangeListener;

.field private Dn:Lmiui/view/ViewPager$b;

.field Do:Z

.field Dp:F

.field private final N:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList",
            "<",
            "Lmiui/view/ViewPager$a;",
            ">;"
        }
    .end annotation
.end field

.field private bR:Landroid/view/VelocityTracker;

.field private cL:Landroid/widget/Scroller;

.field private cT:I

.field private cU:I

.field private cV:I

.field private cm:Z

.field private mLastMotionX:F

.field private mLastMotionY:F

.field private nT:F

.field private final wY:Landroid/graphics/Rect;

.field private zY:I


# direct methods
.method static constructor <clinit>()V
    .locals 3

    .prologue
    .line 83
    const/4 v0, 0x1

    new-array v0, v0, [I

    const/4 v1, 0x0

    const v2, 0x10100b3

    aput v2, v0, v1

    sput-object v0, Lmiui/view/ViewPager;->Ck:[I

    .line 96
    new-instance v0, Lmiui/view/c;

    invoke-direct {v0}, Lmiui/view/c;-><init>()V

    sput-object v0, Lmiui/view/ViewPager;->Cl:Ljava/util/Comparator;

    .line 103
    new-instance v0, Lmiui/view/b;

    invoke-direct {v0}, Lmiui/view/b;-><init>()V

    sput-object v0, Lmiui/view/ViewPager;->zs:Landroid/view/animation/Interpolator;

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;)V
    .locals 4

    .prologue
    const/4 v3, 0x1

    const/4 v2, 0x0

    const/4 v1, -0x1

    .line 294
    invoke-direct {p0, p1}, Landroid/view/ViewGroup;-><init>(Landroid/content/Context;)V

    .line 110
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lmiui/view/ViewPager;->N:Ljava/util/ArrayList;

    .line 111
    new-instance v0, Lmiui/view/ViewPager$a;

    invoke-direct {v0}, Lmiui/view/ViewPager$a;-><init>()V

    iput-object v0, p0, Lmiui/view/ViewPager;->Cm:Lmiui/view/ViewPager$a;

    .line 113
    new-instance v0, Landroid/graphics/Rect;

    invoke-direct {v0}, Landroid/graphics/Rect;-><init>()V

    iput-object v0, p0, Lmiui/view/ViewPager;->wY:Landroid/graphics/Rect;

    .line 117
    iput v1, p0, Lmiui/view/ViewPager;->Cp:I

    .line 118
    const/4 v0, 0x0

    iput-object v0, p0, Lmiui/view/ViewPager;->Cq:Landroid/os/Parcelable;

    .line 130
    const v0, -0x800001

    iput v0, p0, Lmiui/view/ViewPager;->Cw:F

    .line 131
    const v0, 0x7f7fffff

    iput v0, p0, Lmiui/view/ViewPager;->Cx:F

    .line 140
    const/4 v0, 0x3

    iput v0, p0, Lmiui/view/ViewPager;->CE:I

    .line 160
    iput v1, p0, Lmiui/view/ViewPager;->cV:I

    .line 187
    iput-boolean v3, p0, Lmiui/view/ViewPager;->cm:Z

    .line 188
    iput-boolean v2, p0, Lmiui/view/ViewPager;->Di:Z

    .line 197
    iput-boolean v3, p0, Lmiui/view/ViewPager;->Do:Z

    .line 199
    const/4 v0, 0x0

    iput v0, p0, Lmiui/view/ViewPager;->Dp:F

    .line 219
    iput v2, p0, Lmiui/view/ViewPager;->zY:I

    .line 295
    invoke-virtual {p0}, Lmiui/view/ViewPager;->cP()V

    .line 296
    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 4

    .prologue
    const/4 v3, 0x1

    const/4 v2, 0x0

    const/4 v1, -0x1

    .line 299
    invoke-direct {p0, p1, p2}, Landroid/view/ViewGroup;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    .line 110
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lmiui/view/ViewPager;->N:Ljava/util/ArrayList;

    .line 111
    new-instance v0, Lmiui/view/ViewPager$a;

    invoke-direct {v0}, Lmiui/view/ViewPager$a;-><init>()V

    iput-object v0, p0, Lmiui/view/ViewPager;->Cm:Lmiui/view/ViewPager$a;

    .line 113
    new-instance v0, Landroid/graphics/Rect;

    invoke-direct {v0}, Landroid/graphics/Rect;-><init>()V

    iput-object v0, p0, Lmiui/view/ViewPager;->wY:Landroid/graphics/Rect;

    .line 117
    iput v1, p0, Lmiui/view/ViewPager;->Cp:I

    .line 118
    const/4 v0, 0x0

    iput-object v0, p0, Lmiui/view/ViewPager;->Cq:Landroid/os/Parcelable;

    .line 130
    const v0, -0x800001

    iput v0, p0, Lmiui/view/ViewPager;->Cw:F

    .line 131
    const v0, 0x7f7fffff

    iput v0, p0, Lmiui/view/ViewPager;->Cx:F

    .line 140
    const/4 v0, 0x3

    iput v0, p0, Lmiui/view/ViewPager;->CE:I

    .line 160
    iput v1, p0, Lmiui/view/ViewPager;->cV:I

    .line 187
    iput-boolean v3, p0, Lmiui/view/ViewPager;->cm:Z

    .line 188
    iput-boolean v2, p0, Lmiui/view/ViewPager;->Di:Z

    .line 197
    iput-boolean v3, p0, Lmiui/view/ViewPager;->Do:Z

    .line 199
    const/4 v0, 0x0

    iput v0, p0, Lmiui/view/ViewPager;->Dp:F

    .line 219
    iput v2, p0, Lmiui/view/ViewPager;->zY:I

    .line 300
    invoke-virtual {p0}, Lmiui/view/ViewPager;->cP()V

    .line 301
    return-void
.end method

.method private X(I)V
    .locals 1

    .prologue
    .line 327
    iget v0, p0, Lmiui/view/ViewPager;->zY:I

    if-ne v0, p1, :cond_1

    .line 339
    :cond_0
    :goto_0
    return-void

    .line 331
    :cond_1
    iput p1, p0, Lmiui/view/ViewPager;->zY:I

    .line 332
    iget-object v0, p0, Lmiui/view/ViewPager;->Dl:Lmiui/view/ViewPager$OnPageChangeListener;

    if-eqz v0, :cond_2

    .line 333
    iget-object v0, p0, Lmiui/view/ViewPager;->Dl:Lmiui/view/ViewPager$OnPageChangeListener;

    invoke-interface {v0, p1}, Lmiui/view/ViewPager$OnPageChangeListener;->onPageScrollStateChanged(I)V

    .line 336
    :cond_2
    iget-object v0, p0, Lmiui/view/ViewPager;->Dm:Lmiui/view/ViewPager$OnPageChangeListener;

    if-eqz v0, :cond_0

    .line 337
    iget-object v0, p0, Lmiui/view/ViewPager;->Dm:Lmiui/view/ViewPager$OnPageChangeListener;

    invoke-interface {v0, p1}, Lmiui/view/ViewPager$OnPageChangeListener;->onPageScrollStateChanged(I)V

    goto :goto_0
.end method

.method private a(IFII)I
    .locals 3

    .prologue
    .line 1976
    invoke-static {p4}, Ljava/lang/Math;->abs(I)I

    move-result v0

    iget v1, p0, Lmiui/view/ViewPager;->Db:I

    if-le v0, v1, :cond_2

    invoke-static {p3}, Ljava/lang/Math;->abs(I)I

    move-result v0

    iget v1, p0, Lmiui/view/ViewPager;->Da:I

    if-le v0, v1, :cond_2

    .line 1977
    if-lez p3, :cond_1

    .line 1982
    :goto_0
    iget-object v0, p0, Lmiui/view/ViewPager;->N:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    if-lez v0, :cond_0

    .line 1983
    iget-object v0, p0, Lmiui/view/ViewPager;->N:Ljava/util/ArrayList;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/view/ViewPager$a;

    .line 1984
    iget-object v1, p0, Lmiui/view/ViewPager;->N:Ljava/util/ArrayList;

    iget-object v2, p0, Lmiui/view/ViewPager;->N:Ljava/util/ArrayList;

    invoke-virtual {v2}, Ljava/util/ArrayList;->size()I

    move-result v2

    add-int/lit8 v2, v2, -0x1

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lmiui/view/ViewPager$a;

    .line 1987
    iget v0, v0, Lmiui/view/ViewPager$a;->position:I

    iget v1, v1, Lmiui/view/ViewPager$a;->position:I

    invoke-static {p1, v1}, Ljava/lang/Math;->min(II)I

    move-result v1

    invoke-static {v0, v1}, Ljava/lang/Math;->max(II)I

    move-result p1

    .line 1990
    :cond_0
    return p1

    .line 1977
    :cond_1
    add-int/lit8 p1, p1, 0x1

    goto :goto_0

    .line 1979
    :cond_2
    int-to-float v0, p1

    add-float/2addr v0, p2

    const/high16 v1, 0x3f000000

    add-float/2addr v0, v1

    float-to-int p1, v0

    goto :goto_0
.end method

.method private a(Landroid/graphics/Rect;Landroid/view/View;)Landroid/graphics/Rect;
    .locals 4

    .prologue
    const/4 v0, 0x0

    .line 2365
    if-nez p1, :cond_2

    .line 2366
    new-instance v1, Landroid/graphics/Rect;

    invoke-direct {v1}, Landroid/graphics/Rect;-><init>()V

    .line 2368
    :goto_0
    if-nez p2, :cond_0

    .line 2369
    invoke-virtual {v1, v0, v0, v0, v0}, Landroid/graphics/Rect;->set(IIII)V

    move-object v0, v1

    .line 2387
    :goto_1
    return-object v0

    .line 2372
    :cond_0
    invoke-virtual {p2}, Landroid/view/View;->getLeft()I

    move-result v0

    iput v0, v1, Landroid/graphics/Rect;->left:I

    .line 2373
    invoke-virtual {p2}, Landroid/view/View;->getRight()I

    move-result v0

    iput v0, v1, Landroid/graphics/Rect;->right:I

    .line 2374
    invoke-virtual {p2}, Landroid/view/View;->getTop()I

    move-result v0

    iput v0, v1, Landroid/graphics/Rect;->top:I

    .line 2375
    invoke-virtual {p2}, Landroid/view/View;->getBottom()I

    move-result v0

    iput v0, v1, Landroid/graphics/Rect;->bottom:I

    .line 2377
    invoke-virtual {p2}, Landroid/view/View;->getParent()Landroid/view/ViewParent;

    move-result-object v0

    .line 2378
    :goto_2
    instance-of v2, v0, Landroid/view/ViewGroup;

    if-eqz v2, :cond_1

    if-eq v0, p0, :cond_1

    .line 2379
    check-cast v0, Landroid/view/ViewGroup;

    .line 2380
    iget v2, v1, Landroid/graphics/Rect;->left:I

    invoke-virtual {v0}, Landroid/view/ViewGroup;->getLeft()I

    move-result v3

    add-int/2addr v2, v3

    iput v2, v1, Landroid/graphics/Rect;->left:I

    .line 2381
    iget v2, v1, Landroid/graphics/Rect;->right:I

    invoke-virtual {v0}, Landroid/view/ViewGroup;->getRight()I

    move-result v3

    add-int/2addr v2, v3

    iput v2, v1, Landroid/graphics/Rect;->right:I

    .line 2382
    iget v2, v1, Landroid/graphics/Rect;->top:I

    invoke-virtual {v0}, Landroid/view/ViewGroup;->getTop()I

    move-result v3

    add-int/2addr v2, v3

    iput v2, v1, Landroid/graphics/Rect;->top:I

    .line 2383
    iget v2, v1, Landroid/graphics/Rect;->bottom:I

    invoke-virtual {v0}, Landroid/view/ViewGroup;->getBottom()I

    move-result v3

    add-int/2addr v2, v3

    iput v2, v1, Landroid/graphics/Rect;->bottom:I

    .line 2385
    invoke-virtual {v0}, Landroid/view/ViewGroup;->getParent()Landroid/view/ViewParent;

    move-result-object v0

    goto :goto_2

    :cond_1
    move-object v0, v1

    .line 2387
    goto :goto_1

    :cond_2
    move-object v1, p1

    goto :goto_0
.end method

.method private a(Lmiui/view/ViewPager$a;ILmiui/view/ViewPager$a;)V
    .locals 12

    .prologue
    const/4 v4, 0x0

    const/high16 v10, 0x3f800000

    .line 946
    iget-object v0, p0, Lmiui/view/ViewPager;->Cn:Lmiui/view/PagerAdapter;

    invoke-virtual {v0}, Lmiui/view/PagerAdapter;->getCount()I

    move-result v7

    .line 947
    invoke-virtual {p0}, Lmiui/view/ViewPager;->getWidth()I

    move-result v0

    .line 948
    if-lez v0, :cond_0

    iget v1, p0, Lmiui/view/ViewPager;->Cs:I

    int-to-float v1, v1

    int-to-float v0, v0

    div-float v0, v1, v0

    move v6, v0

    .line 950
    :goto_0
    if-eqz p3, :cond_4

    .line 951
    iget v0, p3, Lmiui/view/ViewPager$a;->position:I

    .line 953
    iget v1, p1, Lmiui/view/ViewPager$a;->position:I

    if-ge v0, v1, :cond_2

    .line 956
    iget v1, p3, Lmiui/view/ViewPager$a;->lg:F

    iget v2, p3, Lmiui/view/ViewPager$a;->widthFactor:F

    add-float/2addr v1, v2

    add-float/2addr v1, v6

    .line 957
    add-int/lit8 v0, v0, 0x1

    move v2, v1

    move v3, v4

    move v1, v0

    .line 958
    :goto_1
    iget v0, p1, Lmiui/view/ViewPager$a;->position:I

    if-gt v1, v0, :cond_4

    iget-object v0, p0, Lmiui/view/ViewPager;->N:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    if-ge v3, v0, :cond_4

    .line 959
    iget-object v0, p0, Lmiui/view/ViewPager;->N:Ljava/util/ArrayList;

    invoke-virtual {v0, v3}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/view/ViewPager$a;

    .line 960
    :goto_2
    iget v5, v0, Lmiui/view/ViewPager$a;->position:I

    if-le v1, v5, :cond_e

    iget-object v5, p0, Lmiui/view/ViewPager;->N:Ljava/util/ArrayList;

    invoke-virtual {v5}, Ljava/util/ArrayList;->size()I

    move-result v5

    add-int/lit8 v5, v5, -0x1

    if-ge v3, v5, :cond_e

    .line 961
    add-int/lit8 v3, v3, 0x1

    .line 962
    iget-object v0, p0, Lmiui/view/ViewPager;->N:Ljava/util/ArrayList;

    invoke-virtual {v0, v3}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/view/ViewPager$a;

    goto :goto_2

    .line 948
    :cond_0
    const/4 v0, 0x0

    move v6, v0

    goto :goto_0

    .line 964
    :goto_3
    iget v5, v0, Lmiui/view/ViewPager$a;->position:I

    if-ge v2, v5, :cond_1

    .line 967
    iget-object v5, p0, Lmiui/view/ViewPager;->Cn:Lmiui/view/PagerAdapter;

    invoke-virtual {v5, v2}, Lmiui/view/PagerAdapter;->getPageWidth(I)F

    move-result v5

    add-float/2addr v5, v6

    add-float/2addr v5, v1

    .line 968
    add-int/lit8 v1, v2, 0x1

    move v2, v1

    move v1, v5

    goto :goto_3

    .line 970
    :cond_1
    iput v1, v0, Lmiui/view/ViewPager$a;->lg:F

    .line 971
    iget v0, v0, Lmiui/view/ViewPager$a;->widthFactor:F

    add-float/2addr v0, v6

    add-float/2addr v1, v0

    .line 958
    add-int/lit8 v0, v2, 0x1

    move v2, v1

    move v1, v0

    goto :goto_1

    .line 973
    :cond_2
    iget v1, p1, Lmiui/view/ViewPager$a;->position:I

    if-le v0, v1, :cond_4

    .line 974
    iget-object v1, p0, Lmiui/view/ViewPager;->N:Ljava/util/ArrayList;

    invoke-virtual {v1}, Ljava/util/ArrayList;->size()I

    move-result v1

    add-int/lit8 v2, v1, -0x1

    .line 976
    iget v1, p3, Lmiui/view/ViewPager$a;->lg:F

    .line 977
    add-int/lit8 v0, v0, -0x1

    move v3, v2

    move v2, v1

    move v1, v0

    .line 978
    :goto_4
    iget v0, p1, Lmiui/view/ViewPager$a;->position:I

    if-lt v1, v0, :cond_4

    if-ltz v3, :cond_4

    .line 979
    iget-object v0, p0, Lmiui/view/ViewPager;->N:Ljava/util/ArrayList;

    invoke-virtual {v0, v3}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/view/ViewPager$a;

    .line 980
    :goto_5
    iget v5, v0, Lmiui/view/ViewPager$a;->position:I

    if-ge v1, v5, :cond_d

    if-lez v3, :cond_d

    .line 981
    add-int/lit8 v3, v3, -0x1

    .line 982
    iget-object v0, p0, Lmiui/view/ViewPager;->N:Ljava/util/ArrayList;

    invoke-virtual {v0, v3}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/view/ViewPager$a;

    goto :goto_5

    .line 984
    :goto_6
    iget v5, v0, Lmiui/view/ViewPager$a;->position:I

    if-le v2, v5, :cond_3

    .line 987
    iget-object v5, p0, Lmiui/view/ViewPager;->Cn:Lmiui/view/PagerAdapter;

    invoke-virtual {v5, v2}, Lmiui/view/PagerAdapter;->getPageWidth(I)F

    move-result v5

    add-float/2addr v5, v6

    sub-float v5, v1, v5

    .line 988
    add-int/lit8 v1, v2, -0x1

    move v2, v1

    move v1, v5

    goto :goto_6

    .line 990
    :cond_3
    iget v5, v0, Lmiui/view/ViewPager$a;->widthFactor:F

    add-float/2addr v5, v6

    sub-float/2addr v1, v5

    .line 991
    iput v1, v0, Lmiui/view/ViewPager$a;->lg:F

    .line 978
    add-int/lit8 v0, v2, -0x1

    move v2, v1

    move v1, v0

    goto :goto_4

    .line 997
    :cond_4
    iget-object v0, p0, Lmiui/view/ViewPager;->N:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v8

    .line 998
    iget v2, p1, Lmiui/view/ViewPager$a;->lg:F

    .line 999
    iget v0, p1, Lmiui/view/ViewPager$a;->position:I

    add-int/lit8 v1, v0, -0x1

    .line 1000
    iget v0, p1, Lmiui/view/ViewPager$a;->position:I

    if-nez v0, :cond_5

    iget v0, p1, Lmiui/view/ViewPager$a;->lg:F

    :goto_7
    iput v0, p0, Lmiui/view/ViewPager;->Cw:F

    .line 1001
    iget v0, p1, Lmiui/view/ViewPager$a;->position:I

    add-int/lit8 v3, v7, -0x1

    if-ne v0, v3, :cond_6

    iget v0, p1, Lmiui/view/ViewPager$a;->lg:F

    iget v3, p1, Lmiui/view/ViewPager$a;->widthFactor:F

    add-float/2addr v0, v3

    sub-float/2addr v0, v10

    :goto_8
    iput v0, p0, Lmiui/view/ViewPager;->Cx:F

    .line 1004
    add-int/lit8 v0, p2, -0x1

    move v5, v0

    :goto_9
    if-ltz v5, :cond_9

    .line 1005
    iget-object v0, p0, Lmiui/view/ViewPager;->N:Ljava/util/ArrayList;

    invoke-virtual {v0, v5}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/view/ViewPager$a;

    move v3, v2

    .line 1006
    :goto_a
    iget v2, v0, Lmiui/view/ViewPager$a;->position:I

    if-le v1, v2, :cond_7

    .line 1007
    iget-object v9, p0, Lmiui/view/ViewPager;->Cn:Lmiui/view/PagerAdapter;

    add-int/lit8 v2, v1, -0x1

    invoke-virtual {v9, v1}, Lmiui/view/PagerAdapter;->getPageWidth(I)F

    move-result v1

    add-float/2addr v1, v6

    sub-float v1, v3, v1

    move v3, v1

    move v1, v2

    goto :goto_a

    .line 1000
    :cond_5
    const v0, -0x800001

    goto :goto_7

    .line 1001
    :cond_6
    const v0, 0x7f7fffff

    goto :goto_8

    .line 1009
    :cond_7
    iget v2, v0, Lmiui/view/ViewPager$a;->widthFactor:F

    add-float/2addr v2, v6

    sub-float v2, v3, v2

    .line 1010
    iput v2, v0, Lmiui/view/ViewPager$a;->lg:F

    .line 1011
    iget v0, v0, Lmiui/view/ViewPager$a;->position:I

    if-nez v0, :cond_8

    iput v2, p0, Lmiui/view/ViewPager;->Cw:F

    .line 1004
    :cond_8
    add-int/lit8 v0, v5, -0x1

    add-int/lit8 v1, v1, -0x1

    move v5, v0

    goto :goto_9

    .line 1013
    :cond_9
    iget v0, p1, Lmiui/view/ViewPager$a;->lg:F

    iget v1, p1, Lmiui/view/ViewPager$a;->widthFactor:F

    add-float/2addr v0, v1

    add-float v2, v0, v6

    .line 1014
    iget v0, p1, Lmiui/view/ViewPager$a;->position:I

    add-int/lit8 v1, v0, 0x1

    .line 1016
    add-int/lit8 v0, p2, 0x1

    move v5, v0

    :goto_b
    if-ge v5, v8, :cond_c

    .line 1017
    iget-object v0, p0, Lmiui/view/ViewPager;->N:Ljava/util/ArrayList;

    invoke-virtual {v0, v5}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/view/ViewPager$a;

    move v3, v2

    .line 1018
    :goto_c
    iget v2, v0, Lmiui/view/ViewPager$a;->position:I

    if-ge v1, v2, :cond_a

    .line 1019
    iget-object v9, p0, Lmiui/view/ViewPager;->Cn:Lmiui/view/PagerAdapter;

    add-int/lit8 v2, v1, 0x1

    invoke-virtual {v9, v1}, Lmiui/view/PagerAdapter;->getPageWidth(I)F

    move-result v1

    add-float/2addr v1, v6

    add-float/2addr v1, v3

    move v3, v1

    move v1, v2

    goto :goto_c

    .line 1021
    :cond_a
    iget v2, v0, Lmiui/view/ViewPager$a;->position:I

    add-int/lit8 v9, v7, -0x1

    if-ne v2, v9, :cond_b

    .line 1022
    iget v2, v0, Lmiui/view/ViewPager$a;->widthFactor:F

    add-float/2addr v2, v3

    sub-float/2addr v2, v10

    iput v2, p0, Lmiui/view/ViewPager;->Cx:F

    .line 1024
    :cond_b
    iput v3, v0, Lmiui/view/ViewPager$a;->lg:F

    .line 1025
    iget v0, v0, Lmiui/view/ViewPager$a;->widthFactor:F

    add-float/2addr v0, v6

    add-float v2, v3, v0

    .line 1016
    add-int/lit8 v0, v5, 0x1

    add-int/lit8 v1, v1, 0x1

    move v5, v0

    goto :goto_b

    .line 1028
    :cond_c
    iput-boolean v4, p0, Lmiui/view/ViewPager;->Di:Z

    .line 1029
    return-void

    :cond_d
    move v11, v1

    move v1, v2

    move v2, v11

    goto/16 :goto_6

    :cond_e
    move v11, v1

    move v1, v2

    move v2, v11

    goto/16 :goto_3
.end method

.method private c(FF)Z
    .locals 3

    .prologue
    const/4 v2, 0x0

    .line 1604
    iget v0, p0, Lmiui/view/ViewPager;->CT:I

    int-to-float v0, v0

    cmpg-float v0, p1, v0

    if-gez v0, :cond_0

    cmpl-float v0, p2, v2

    if-gtz v0, :cond_1

    :cond_0
    invoke-virtual {p0}, Lmiui/view/ViewPager;->getWidth()I

    move-result v0

    iget v1, p0, Lmiui/view/ViewPager;->CT:I

    sub-int/2addr v0, v1

    int-to-float v0, v0

    cmpl-float v0, p1, v0

    if-lez v0, :cond_2

    cmpg-float v0, p2, v2

    if-gez v0, :cond_2

    :cond_1
    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_2
    const/4 v0, 0x0

    goto :goto_0
.end method

.method private cQ()V
    .locals 2

    .prologue
    .line 386
    const/4 v0, 0x0

    move v1, v0

    :goto_0
    invoke-virtual {p0}, Lmiui/view/ViewPager;->getChildCount()I

    move-result v0

    if-ge v1, v0, :cond_1

    .line 387
    invoke-virtual {p0, v1}, Lmiui/view/ViewPager;->getChildAt(I)Landroid/view/View;

    move-result-object v0

    .line 388
    invoke-virtual {v0}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v0

    check-cast v0, Lmiui/view/ViewPager$LayoutParams;

    .line 389
    iget-boolean v0, v0, Lmiui/view/ViewPager$LayoutParams;->isDecor:Z

    if-nez v0, :cond_0

    .line 390
    invoke-virtual {p0, v1}, Lmiui/view/ViewPager;->removeViewAt(I)V

    .line 391
    add-int/lit8 v1, v1, -0x1

    .line 386
    :cond_0
    add-int/lit8 v0, v1, 0x1

    move v1, v0

    goto :goto_0

    .line 394
    :cond_1
    return-void
.end method

.method private cT()V
    .locals 7

    .prologue
    const/4 v2, 0x1

    const/4 v3, 0x0

    .line 1577
    iget v0, p0, Lmiui/view/ViewPager;->zY:I

    const/4 v1, 0x2

    if-ne v0, v1, :cond_3

    move v0, v2

    .line 1578
    :goto_0
    if-eqz v0, :cond_2

    .line 1580
    invoke-direct {p0, v3}, Lmiui/view/ViewPager;->setScrollingCacheEnabled(Z)V

    .line 1581
    iget-object v1, p0, Lmiui/view/ViewPager;->cL:Landroid/widget/Scroller;

    invoke-virtual {v1}, Landroid/widget/Scroller;->abortAnimation()V

    .line 1582
    invoke-virtual {p0}, Lmiui/view/ViewPager;->getScrollX()I

    move-result v1

    .line 1583
    invoke-virtual {p0}, Lmiui/view/ViewPager;->getScrollY()I

    move-result v4

    .line 1584
    iget-object v5, p0, Lmiui/view/ViewPager;->cL:Landroid/widget/Scroller;

    invoke-virtual {v5}, Landroid/widget/Scroller;->getCurrX()I

    move-result v5

    .line 1585
    iget-object v6, p0, Lmiui/view/ViewPager;->cL:Landroid/widget/Scroller;

    invoke-virtual {v6}, Landroid/widget/Scroller;->getCurrY()I

    move-result v6

    .line 1586
    if-ne v1, v5, :cond_0

    if-eq v4, v6, :cond_1

    .line 1587
    :cond_0
    invoke-virtual {p0, v5, v6}, Lmiui/view/ViewPager;->scrollTo(II)V

    .line 1589
    :cond_1
    invoke-direct {p0, v3}, Lmiui/view/ViewPager;->X(I)V

    .line 1591
    :cond_2
    iput-boolean v3, p0, Lmiui/view/ViewPager;->CC:Z

    .line 1592
    iget-object v1, p0, Lmiui/view/ViewPager;->N:Ljava/util/ArrayList;

    invoke-virtual {v1}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v4

    move v1, v0

    :goto_1
    invoke-interface {v4}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_4

    invoke-interface {v4}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/view/ViewPager$a;

    .line 1593
    iget-boolean v5, v0, Lmiui/view/ViewPager$a;->lf:Z

    if-eqz v5, :cond_6

    .line 1595
    iput-boolean v3, v0, Lmiui/view/ViewPager$a;->lf:Z

    move v0, v2

    :goto_2
    move v1, v0

    goto :goto_1

    :cond_3
    move v0, v3

    .line 1577
    goto :goto_0

    .line 1598
    :cond_4
    if-eqz v1, :cond_5

    .line 1599
    invoke-virtual {p0}, Lmiui/view/ViewPager;->cS()V

    .line 1601
    :cond_5
    return-void

    :cond_6
    move v0, v1

    goto :goto_2
.end method

.method private cU()Lmiui/view/ViewPager$a;
    .locals 13

    .prologue
    const/4 v3, 0x0

    const/4 v2, 0x0

    .line 1933
    invoke-virtual {p0}, Lmiui/view/ViewPager;->getWidth()I

    move-result v1

    .line 1934
    if-lez v1, :cond_3

    invoke-virtual {p0}, Lmiui/view/ViewPager;->getScrollX()I

    move-result v0

    int-to-float v0, v0

    int-to-float v4, v1

    div-float/2addr v0, v4

    move v9, v0

    .line 1935
    :goto_0
    if-lez v1, :cond_4

    iget v0, p0, Lmiui/view/ViewPager;->Cs:I

    int-to-float v0, v0

    int-to-float v1, v1

    div-float/2addr v0, v1

    move v1, v0

    .line 1936
    :goto_1
    const/4 v5, -0x1

    .line 1939
    const/4 v4, 0x1

    .line 1941
    const/4 v0, 0x0

    move v6, v2

    move v7, v2

    move v8, v5

    move v2, v3

    move v5, v4

    move-object v4, v0

    .line 1942
    :goto_2
    iget-object v0, p0, Lmiui/view/ViewPager;->N:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    if-ge v2, v0, :cond_2

    .line 1943
    iget-object v0, p0, Lmiui/view/ViewPager;->N:Ljava/util/ArrayList;

    invoke-virtual {v0, v2}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/view/ViewPager$a;

    .line 1945
    if-nez v5, :cond_6

    iget v10, v0, Lmiui/view/ViewPager$a;->position:I

    add-int/lit8 v11, v8, 0x1

    if-eq v10, v11, :cond_6

    .line 1947
    iget-object v0, p0, Lmiui/view/ViewPager;->Cm:Lmiui/view/ViewPager$a;

    .line 1948
    add-float/2addr v6, v7

    add-float/2addr v6, v1

    iput v6, v0, Lmiui/view/ViewPager$a;->lg:F

    .line 1949
    add-int/lit8 v6, v8, 0x1

    iput v6, v0, Lmiui/view/ViewPager$a;->position:I

    .line 1950
    iget-object v6, p0, Lmiui/view/ViewPager;->Cn:Lmiui/view/PagerAdapter;

    iget v7, v0, Lmiui/view/ViewPager$a;->position:I

    invoke-virtual {v6, v7}, Lmiui/view/PagerAdapter;->getPageWidth(I)F

    move-result v6

    iput v6, v0, Lmiui/view/ViewPager$a;->widthFactor:F

    .line 1951
    add-int/lit8 v2, v2, -0x1

    move-object v12, v0

    move v0, v2

    move-object v2, v12

    .line 1953
    :goto_3
    iget v6, v2, Lmiui/view/ViewPager$a;->lg:F

    .line 1956
    iget v7, v2, Lmiui/view/ViewPager$a;->widthFactor:F

    add-float/2addr v7, v6

    add-float/2addr v7, v1

    .line 1957
    if-nez v5, :cond_0

    cmpl-float v5, v9, v6

    if-ltz v5, :cond_2

    .line 1958
    :cond_0
    cmpg-float v4, v9, v7

    if-ltz v4, :cond_1

    iget-object v4, p0, Lmiui/view/ViewPager;->N:Ljava/util/ArrayList;

    invoke-virtual {v4}, Ljava/util/ArrayList;->size()I

    move-result v4

    add-int/lit8 v4, v4, -0x1

    if-ne v0, v4, :cond_5

    :cond_1
    move-object v4, v2

    .line 1971
    :cond_2
    return-object v4

    :cond_3
    move v9, v2

    .line 1934
    goto :goto_0

    :cond_4
    move v1, v2

    .line 1935
    goto :goto_1

    .line 1965
    :cond_5
    iget v5, v2, Lmiui/view/ViewPager$a;->position:I

    .line 1967
    iget v4, v2, Lmiui/view/ViewPager$a;->widthFactor:F

    .line 1942
    add-int/lit8 v0, v0, 0x1

    move v7, v6

    move v8, v5

    move v5, v3

    move v6, v4

    move-object v4, v2

    move v2, v0

    goto :goto_2

    :cond_6
    move-object v12, v0

    move v0, v2

    move-object v2, v12

    goto :goto_3
.end method

.method private cV()V
    .locals 1

    .prologue
    const/4 v0, 0x0

    .line 2223
    iput-boolean v0, p0, Lmiui/view/ViewPager;->CJ:Z

    .line 2224
    iput-boolean v0, p0, Lmiui/view/ViewPager;->CP:Z

    .line 2226
    iget-object v0, p0, Lmiui/view/ViewPager;->bR:Landroid/view/VelocityTracker;

    if-eqz v0, :cond_0

    .line 2227
    iget-object v0, p0, Lmiui/view/ViewPager;->bR:Landroid/view/VelocityTracker;

    invoke-virtual {v0}, Landroid/view/VelocityTracker;->recycle()V

    .line 2228
    const/4 v0, 0x0

    iput-object v0, p0, Lmiui/view/ViewPager;->bR:Landroid/view/VelocityTracker;

    .line 2230
    :cond_0
    return-void
.end method

.method static synthetic cY()[I
    .locals 1

    .prologue
    .line 70
    sget-object v0, Lmiui/view/ViewPager;->Ck:[I

    return-object v0
.end method

.method private d(Landroid/view/MotionEvent;)V
    .locals 3

    .prologue
    .line 2208
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getActionIndex()I

    move-result v0

    .line 2209
    invoke-virtual {p1, v0}, Landroid/view/MotionEvent;->getPointerId(I)I

    move-result v1

    .line 2210
    iget v2, p0, Lmiui/view/ViewPager;->cV:I

    if-ne v1, v2, :cond_0

    .line 2213
    if-nez v0, :cond_1

    const/4 v0, 0x1

    .line 2214
    :goto_0
    invoke-virtual {p1, v0}, Landroid/view/MotionEvent;->getX(I)F

    move-result v1

    iput v1, p0, Lmiui/view/ViewPager;->mLastMotionX:F

    .line 2215
    invoke-virtual {p1, v0}, Landroid/view/MotionEvent;->getPointerId(I)I

    move-result v0

    iput v0, p0, Lmiui/view/ViewPager;->cV:I

    .line 2216
    iget-object v0, p0, Lmiui/view/ViewPager;->bR:Landroid/view/VelocityTracker;

    if-eqz v0, :cond_0

    .line 2217
    iget-object v0, p0, Lmiui/view/ViewPager;->bR:Landroid/view/VelocityTracker;

    invoke-virtual {v0}, Landroid/view/VelocityTracker;->clear()V

    .line 2220
    :cond_0
    return-void

    .line 2213
    :cond_1
    const/4 v0, 0x0

    goto :goto_0
.end method

.method private e(IIII)V
    .locals 6

    .prologue
    const/4 v2, 0x0

    .line 1285
    if-lez p2, :cond_1

    iget-object v0, p0, Lmiui/view/ViewPager;->N:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_1

    .line 1286
    add-int v0, p1, p3

    .line 1287
    add-int v1, p2, p4

    .line 1288
    invoke-virtual {p0}, Lmiui/view/ViewPager;->getScrollX()I

    move-result v3

    .line 1289
    int-to-float v3, v3

    int-to-float v1, v1

    div-float v1, v3, v1

    .line 1290
    int-to-float v0, v0

    mul-float/2addr v0, v1

    float-to-int v1, v0

    .line 1292
    invoke-virtual {p0}, Lmiui/view/ViewPager;->getScrollY()I

    move-result v0

    invoke-virtual {p0, v1, v0}, Lmiui/view/ViewPager;->scrollTo(II)V

    .line 1293
    iget-object v0, p0, Lmiui/view/ViewPager;->cL:Landroid/widget/Scroller;

    invoke-virtual {v0}, Landroid/widget/Scroller;->isFinished()Z

    move-result v0

    if-nez v0, :cond_0

    .line 1295
    iget-object v0, p0, Lmiui/view/ViewPager;->cL:Landroid/widget/Scroller;

    invoke-virtual {v0}, Landroid/widget/Scroller;->getDuration()I

    move-result v0

    iget-object v3, p0, Lmiui/view/ViewPager;->cL:Landroid/widget/Scroller;

    invoke-virtual {v3}, Landroid/widget/Scroller;->timePassed()I

    move-result v3

    sub-int v5, v0, v3

    .line 1296
    iget v0, p0, Lmiui/view/ViewPager;->Co:I

    invoke-virtual {p0, v0}, Lmiui/view/ViewPager;->Z(I)Lmiui/view/ViewPager$a;

    move-result-object v3

    .line 1297
    iget-object v0, p0, Lmiui/view/ViewPager;->cL:Landroid/widget/Scroller;

    iget v3, v3, Lmiui/view/ViewPager$a;->lg:F

    int-to-float v4, p1

    mul-float/2addr v3, v4

    float-to-int v3, v3

    move v4, v2

    invoke-virtual/range {v0 .. v5}, Landroid/widget/Scroller;->startScroll(IIIII)V

    .line 1309
    :cond_0
    :goto_0
    return-void

    .line 1301
    :cond_1
    iget v0, p0, Lmiui/view/ViewPager;->Co:I

    invoke-virtual {p0, v0}, Lmiui/view/ViewPager;->Z(I)Lmiui/view/ViewPager$a;

    move-result-object v0

    .line 1302
    if-eqz v0, :cond_2

    iget v0, v0, Lmiui/view/ViewPager$a;->lg:F

    iget v1, p0, Lmiui/view/ViewPager;->Cx:F

    invoke-static {v0, v1}, Ljava/lang/Math;->min(FF)F

    move-result v0

    .line 1303
    :goto_1
    int-to-float v1, p1

    mul-float/2addr v0, v1

    float-to-int v0, v0

    .line 1304
    invoke-virtual {p0}, Lmiui/view/ViewPager;->getScrollX()I

    move-result v1

    if-eq v0, v1, :cond_0

    .line 1305
    invoke-direct {p0}, Lmiui/view/ViewPager;->cT()V

    .line 1306
    invoke-virtual {p0}, Lmiui/view/ViewPager;->getScrollY()I

    move-result v1

    invoke-virtual {p0, v0, v1}, Lmiui/view/ViewPager;->scrollTo(II)V

    goto :goto_0

    .line 1302
    :cond_2
    const/4 v0, 0x0

    goto :goto_1
.end method

.method private e(F)Z
    .locals 10

    .prologue
    const/4 v2, 0x0

    const/4 v3, 0x1

    .line 1880
    .line 1882
    iget v0, p0, Lmiui/view/ViewPager;->mLastMotionX:F

    sub-float/2addr v0, p1

    .line 1883
    iput p1, p0, Lmiui/view/ViewPager;->mLastMotionX:F

    .line 1885
    invoke-virtual {p0}, Lmiui/view/ViewPager;->getScrollX()I

    move-result v1

    int-to-float v1, v1

    .line 1886
    add-float v5, v1, v0

    .line 1887
    invoke-virtual {p0}, Lmiui/view/ViewPager;->getWidth()I

    move-result v7

    .line 1889
    int-to-float v0, v7

    iget v1, p0, Lmiui/view/ViewPager;->Cw:F

    mul-float v4, v0, v1

    .line 1890
    int-to-float v0, v7

    iget v1, p0, Lmiui/view/ViewPager;->Cx:F

    mul-float v6, v0, v1

    .line 1894
    iget-object v0, p0, Lmiui/view/ViewPager;->N:Ljava/util/ArrayList;

    invoke-virtual {v0, v2}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/view/ViewPager$a;

    .line 1895
    iget-object v1, p0, Lmiui/view/ViewPager;->N:Ljava/util/ArrayList;

    iget-object v8, p0, Lmiui/view/ViewPager;->N:Ljava/util/ArrayList;

    invoke-virtual {v8}, Ljava/util/ArrayList;->size()I

    move-result v8

    add-int/lit8 v8, v8, -0x1

    invoke-virtual {v1, v8}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lmiui/view/ViewPager$a;

    .line 1896
    iget v8, v0, Lmiui/view/ViewPager$a;->position:I

    if-eqz v8, :cond_5

    .line 1898
    iget v0, v0, Lmiui/view/ViewPager$a;->lg:F

    int-to-float v4, v7

    mul-float/2addr v0, v4

    move v4, v0

    move v0, v2

    .line 1900
    :goto_0
    iget v8, v1, Lmiui/view/ViewPager$a;->position:I

    iget-object v9, p0, Lmiui/view/ViewPager;->Cn:Lmiui/view/PagerAdapter;

    invoke-virtual {v9}, Lmiui/view/PagerAdapter;->getCount()I

    move-result v9

    add-int/lit8 v9, v9, -0x1

    if-eq v8, v9, :cond_4

    .line 1902
    iget v1, v1, Lmiui/view/ViewPager$a;->lg:F

    int-to-float v6, v7

    mul-float/2addr v1, v6

    move v6, v2

    .line 1905
    :goto_1
    cmpg-float v8, v5, v4

    if-gez v8, :cond_1

    .line 1906
    if-eqz v0, :cond_0

    .line 1907
    sub-float v0, v4, v5

    .line 1908
    iget-object v1, p0, Lmiui/view/ViewPager;->Dg:Landroid/widget/EdgeEffect;

    invoke-static {v0}, Ljava/lang/Math;->abs(F)F

    move-result v0

    int-to-float v2, v7

    div-float/2addr v0, v2

    invoke-virtual {v1, v0}, Landroid/widget/EdgeEffect;->onPull(F)V

    move v2, v3

    .line 1921
    :cond_0
    :goto_2
    iget v0, p0, Lmiui/view/ViewPager;->mLastMotionX:F

    float-to-int v1, v4

    int-to-float v1, v1

    sub-float v1, v4, v1

    add-float/2addr v0, v1

    iput v0, p0, Lmiui/view/ViewPager;->mLastMotionX:F

    .line 1922
    float-to-int v0, v4

    invoke-virtual {p0}, Lmiui/view/ViewPager;->getScrollY()I

    move-result v1

    invoke-virtual {p0, v0, v1}, Lmiui/view/ViewPager;->scrollTo(II)V

    .line 1923
    float-to-int v0, v4

    invoke-direct {p0, v0, v3}, Lmiui/view/ViewPager;->f(IZ)Z

    .line 1925
    return v2

    .line 1912
    :cond_1
    cmpl-float v0, v5, v1

    if-lez v0, :cond_3

    .line 1913
    if-eqz v6, :cond_2

    .line 1914
    sub-float v0, v5, v1

    .line 1915
    iget-object v2, p0, Lmiui/view/ViewPager;->Dh:Landroid/widget/EdgeEffect;

    invoke-static {v0}, Ljava/lang/Math;->abs(F)F

    move-result v0

    int-to-float v4, v7

    div-float/2addr v0, v4

    invoke-virtual {v2, v0}, Landroid/widget/EdgeEffect;->onPull(F)V

    move v2, v3

    :cond_2
    move v4, v1

    .line 1918
    goto :goto_2

    :cond_3
    move v4, v5

    goto :goto_2

    :cond_4
    move v1, v6

    move v6, v3

    goto :goto_1

    :cond_5
    move v0, v3

    goto :goto_0
.end method

.method private f(IZ)Z
    .locals 8

    .prologue
    const/4 v3, 0x1

    const/4 v4, 0x0

    .line 1454
    iget-object v0, p0, Lmiui/view/ViewPager;->N:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    if-nez v0, :cond_0

    .line 1455
    iput-boolean v4, p0, Lmiui/view/ViewPager;->Dj:Z

    .line 1456
    const/4 v0, 0x0

    invoke-virtual {p0, v4, v0, v4}, Lmiui/view/ViewPager;->onPageScrolled(IFI)V

    .line 1457
    iget-boolean v0, p0, Lmiui/view/ViewPager;->Dj:Z

    if-nez v0, :cond_7

    .line 1458
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "onPageScrolled did not call superclass implementation"

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 1463
    :cond_0
    invoke-direct {p0}, Lmiui/view/ViewPager;->cU()Lmiui/view/ViewPager$a;

    move-result-object v0

    .line 1464
    invoke-virtual {p0}, Lmiui/view/ViewPager;->getWidth()I

    move-result v2

    .line 1465
    iget v1, p0, Lmiui/view/ViewPager;->Cs:I

    add-int v5, v2, v1

    .line 1466
    iget v1, p0, Lmiui/view/ViewPager;->Cs:I

    int-to-float v1, v1

    int-to-float v6, v2

    div-float v6, v1, v6

    .line 1467
    iget v1, v0, Lmiui/view/ViewPager$a;->position:I

    .line 1468
    int-to-float v7, p1

    int-to-float v2, v2

    div-float v2, v7, v2

    iget v7, v0, Lmiui/view/ViewPager$a;->lg:F

    sub-float/2addr v2, v7

    iget v0, v0, Lmiui/view/ViewPager$a;->widthFactor:F

    add-float/2addr v0, v6

    div-float v6, v2, v0

    .line 1470
    int-to-float v0, v5

    mul-float/2addr v0, v6

    float-to-int v5, v0

    .line 1473
    if-eqz p2, :cond_3

    .line 1476
    iget v0, p0, Lmiui/view/ViewPager;->Dp:F

    cmpg-float v0, v0, v6

    if-gez v0, :cond_4

    const v0, 0x3f0ccccd

    cmpl-float v0, v6, v0

    if-lez v0, :cond_4

    .line 1478
    add-int/lit8 v0, v1, 0x1

    move v2, v3

    .line 1483
    :goto_0
    iput v6, p0, Lmiui/view/ViewPager;->Dp:F

    .line 1485
    if-eqz v2, :cond_3

    .line 1486
    if-gez v0, :cond_5

    move v0, v4

    .line 1492
    :cond_1
    :goto_1
    iget v2, p0, Lmiui/view/ViewPager;->Co:I

    if-eq v0, v2, :cond_3

    .line 1493
    invoke-virtual {p0, v0}, Lmiui/view/ViewPager;->Y(I)V

    .line 1494
    iget-object v2, p0, Lmiui/view/ViewPager;->Dl:Lmiui/view/ViewPager$OnPageChangeListener;

    if-eqz v2, :cond_2

    .line 1495
    iget-object v2, p0, Lmiui/view/ViewPager;->Dl:Lmiui/view/ViewPager$OnPageChangeListener;

    invoke-interface {v2, v0}, Lmiui/view/ViewPager$OnPageChangeListener;->onPageSelected(I)V

    .line 1497
    :cond_2
    iget-object v2, p0, Lmiui/view/ViewPager;->Dm:Lmiui/view/ViewPager$OnPageChangeListener;

    if-eqz v2, :cond_3

    .line 1498
    iget-object v2, p0, Lmiui/view/ViewPager;->Dm:Lmiui/view/ViewPager$OnPageChangeListener;

    invoke-interface {v2, v0}, Lmiui/view/ViewPager$OnPageChangeListener;->onPageSelected(I)V

    .line 1504
    :cond_3
    iput-boolean v4, p0, Lmiui/view/ViewPager;->Dj:Z

    .line 1505
    invoke-virtual {p0, v1, v6, v5}, Lmiui/view/ViewPager;->onPageScrolled(IFI)V

    .line 1506
    iget-boolean v0, p0, Lmiui/view/ViewPager;->Dj:Z

    if-nez v0, :cond_6

    .line 1507
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "onPageScrolled did not call superclass implementation"

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 1479
    :cond_4
    iget v0, p0, Lmiui/view/ViewPager;->Dp:F

    cmpl-float v0, v0, v6

    if-lez v0, :cond_8

    const v0, 0x3ee66666

    cmpg-float v0, v6, v0

    if-gez v0, :cond_8

    move v0, v1

    move v2, v3

    .line 1481
    goto :goto_0

    .line 1488
    :cond_5
    iget-object v2, p0, Lmiui/view/ViewPager;->Cn:Lmiui/view/PagerAdapter;

    invoke-virtual {v2}, Lmiui/view/PagerAdapter;->getCount()I

    move-result v2

    if-lt v0, v2, :cond_1

    .line 1489
    iget-object v0, p0, Lmiui/view/ViewPager;->Cn:Lmiui/view/PagerAdapter;

    invoke-virtual {v0}, Lmiui/view/PagerAdapter;->getCount()I

    move-result v0

    add-int/lit8 v0, v0, -0x1

    goto :goto_1

    :cond_6
    move v4, v3

    .line 1511
    :cond_7
    return v4

    :cond_8
    move v0, v1

    move v2, v4

    goto :goto_0
.end method

.method private setScrollingCacheEnabled(Z)V
    .locals 1

    .prologue
    .line 2233
    iget-boolean v0, p0, Lmiui/view/ViewPager;->CB:Z

    if-eq v0, p1, :cond_0

    .line 2234
    iput-boolean p1, p0, Lmiui/view/ViewPager;->CB:Z

    .line 2245
    :cond_0
    return-void
.end method


# virtual methods
.method Y(I)V
    .locals 14

    .prologue
    .line 793
    const/4 v0, 0x0

    .line 794
    iget v1, p0, Lmiui/view/ViewPager;->Co:I

    if-eq v1, p1, :cond_1e

    .line 795
    iget v0, p0, Lmiui/view/ViewPager;->Co:I

    invoke-virtual {p0, v0}, Lmiui/view/ViewPager;->Z(I)Lmiui/view/ViewPager$a;

    move-result-object v0

    .line 796
    iput p1, p0, Lmiui/view/ViewPager;->Co:I

    move-object v1, v0

    .line 799
    :goto_0
    iget-object v0, p0, Lmiui/view/ViewPager;->Cn:Lmiui/view/PagerAdapter;

    if-nez v0, :cond_1

    .line 943
    :cond_0
    return-void

    .line 807
    :cond_1
    iget-boolean v0, p0, Lmiui/view/ViewPager;->CC:Z

    if-nez v0, :cond_0

    .line 815
    invoke-virtual {p0}, Lmiui/view/ViewPager;->getWindowToken()Landroid/os/IBinder;

    move-result-object v0

    if-eqz v0, :cond_0

    .line 819
    iget-object v0, p0, Lmiui/view/ViewPager;->Cn:Lmiui/view/PagerAdapter;

    invoke-virtual {v0, p0}, Lmiui/view/PagerAdapter;->startUpdate(Landroid/view/ViewGroup;)V

    .line 821
    iget v0, p0, Lmiui/view/ViewPager;->CE:I

    .line 822
    const/4 v2, 0x0

    iget v3, p0, Lmiui/view/ViewPager;->Co:I

    sub-int/2addr v3, v0

    invoke-static {v2, v3}, Ljava/lang/Math;->max(II)I

    move-result v7

    .line 823
    iget-object v2, p0, Lmiui/view/ViewPager;->Cn:Lmiui/view/PagerAdapter;

    invoke-virtual {v2}, Lmiui/view/PagerAdapter;->getCount()I

    move-result v8

    .line 824
    add-int/lit8 v2, v8, -0x1

    iget v3, p0, Lmiui/view/ViewPager;->Co:I

    add-int/2addr v0, v3

    invoke-static {v2, v0}, Ljava/lang/Math;->min(II)I

    move-result v9

    .line 828
    const/4 v3, 0x0

    .line 829
    const/4 v0, 0x0

    move v2, v0

    :goto_1
    iget-object v0, p0, Lmiui/view/ViewPager;->N:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    if-ge v2, v0, :cond_1d

    .line 830
    iget-object v0, p0, Lmiui/view/ViewPager;->N:Ljava/util/ArrayList;

    invoke-virtual {v0, v2}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/view/ViewPager$a;

    .line 831
    iget v4, v0, Lmiui/view/ViewPager$a;->position:I

    iget v5, p0, Lmiui/view/ViewPager;->Co:I

    if-lt v4, v5, :cond_6

    .line 832
    iget v4, v0, Lmiui/view/ViewPager$a;->position:I

    iget v5, p0, Lmiui/view/ViewPager;->Co:I

    if-ne v4, v5, :cond_1d

    .line 837
    :goto_2
    if-nez v0, :cond_1c

    if-lez v8, :cond_1c

    .line 838
    iget v0, p0, Lmiui/view/ViewPager;->Co:I

    invoke-virtual {p0, v0, v2}, Lmiui/view/ViewPager;->j(II)Lmiui/view/ViewPager$a;

    move-result-object v0

    move-object v6, v0

    .line 844
    :goto_3
    if-eqz v6, :cond_4

    .line 845
    const/4 v5, 0x0

    .line 846
    add-int/lit8 v4, v2, -0x1

    .line 847
    if-ltz v4, :cond_7

    iget-object v0, p0, Lmiui/view/ViewPager;->N:Ljava/util/ArrayList;

    invoke-virtual {v0, v4}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/view/ViewPager$a;

    .line 848
    :goto_4
    const/high16 v3, 0x40000000

    iget v10, v6, Lmiui/view/ViewPager$a;->widthFactor:F

    sub-float v10, v3, v10

    .line 849
    iget v3, p0, Lmiui/view/ViewPager;->Co:I

    add-int/lit8 v3, v3, -0x1

    move v12, v3

    move v3, v5

    move v5, v12

    move v13, v4

    move v4, v2

    move v2, v13

    :goto_5
    if-ltz v5, :cond_2

    .line 850
    cmpl-float v11, v3, v10

    if-ltz v11, :cond_b

    if-ge v5, v7, :cond_b

    .line 851
    if-nez v0, :cond_8

    .line 873
    :cond_2
    iget v3, v6, Lmiui/view/ViewPager$a;->widthFactor:F

    .line 874
    add-int/lit8 v5, v4, 0x1

    .line 875
    const/high16 v0, 0x40000000

    cmpg-float v0, v3, v0

    if-gez v0, :cond_3

    .line 876
    iget-object v0, p0, Lmiui/view/ViewPager;->N:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    if-ge v5, v0, :cond_f

    iget-object v0, p0, Lmiui/view/ViewPager;->N:Ljava/util/ArrayList;

    invoke-virtual {v0, v5}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/view/ViewPager$a;

    .line 877
    :goto_6
    iget v2, p0, Lmiui/view/ViewPager;->Co:I

    add-int/lit8 v2, v2, 0x1

    move v12, v2

    move v2, v3

    move v3, v5

    move v5, v12

    :goto_7
    if-ge v5, v8, :cond_3

    .line 878
    const/high16 v7, 0x40000000

    cmpl-float v7, v2, v7

    if-ltz v7, :cond_12

    if-le v5, v9, :cond_12

    .line 879
    if-nez v0, :cond_10

    .line 900
    :cond_3
    invoke-direct {p0, v6, v4, v1}, Lmiui/view/ViewPager;->a(Lmiui/view/ViewPager$a;ILmiui/view/ViewPager$a;)V

    .line 910
    :cond_4
    iget-object v1, p0, Lmiui/view/ViewPager;->Cn:Lmiui/view/PagerAdapter;

    iget v2, p0, Lmiui/view/ViewPager;->Co:I

    if-eqz v6, :cond_16

    iget-object v0, v6, Lmiui/view/ViewPager$a;->le:Ljava/lang/Object;

    :goto_8
    invoke-virtual {v1, p0, v2, v0}, Lmiui/view/PagerAdapter;->setPrimaryItem(Landroid/view/ViewGroup;ILjava/lang/Object;)V

    .line 912
    iget-object v0, p0, Lmiui/view/ViewPager;->Cn:Lmiui/view/PagerAdapter;

    invoke-virtual {v0, p0}, Lmiui/view/PagerAdapter;->finishUpdate(Landroid/view/ViewGroup;)V

    .line 915
    invoke-virtual {p0}, Lmiui/view/ViewPager;->getChildCount()I

    move-result v2

    .line 916
    const/4 v0, 0x0

    move v1, v0

    :goto_9
    if-ge v1, v2, :cond_17

    .line 917
    invoke-virtual {p0, v1}, Lmiui/view/ViewPager;->getChildAt(I)Landroid/view/View;

    move-result-object v3

    .line 918
    invoke-virtual {v3}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v0

    check-cast v0, Lmiui/view/ViewPager$LayoutParams;

    .line 919
    iget-boolean v4, v0, Lmiui/view/ViewPager$LayoutParams;->isDecor:Z

    if-nez v4, :cond_5

    iget v4, v0, Lmiui/view/ViewPager$LayoutParams;->widthFactor:F

    const/4 v5, 0x0

    cmpl-float v4, v4, v5

    if-nez v4, :cond_5

    .line 921
    invoke-virtual {p0, v3}, Lmiui/view/ViewPager;->i(Landroid/view/View;)Lmiui/view/ViewPager$a;

    move-result-object v3

    .line 922
    if-eqz v3, :cond_5

    .line 923
    iget v3, v3, Lmiui/view/ViewPager$a;->widthFactor:F

    iput v3, v0, Lmiui/view/ViewPager$LayoutParams;->widthFactor:F

    .line 916
    :cond_5
    add-int/lit8 v0, v1, 0x1

    move v1, v0

    goto :goto_9

    .line 829
    :cond_6
    add-int/lit8 v0, v2, 0x1

    move v2, v0

    goto/16 :goto_1

    .line 847
    :cond_7
    const/4 v0, 0x0

    goto/16 :goto_4

    .line 854
    :cond_8
    iget v11, v0, Lmiui/view/ViewPager$a;->position:I

    if-ne v5, v11, :cond_9

    iget-boolean v11, v0, Lmiui/view/ViewPager$a;->lf:Z

    if-nez v11, :cond_9

    .line 855
    iget-object v11, p0, Lmiui/view/ViewPager;->N:Ljava/util/ArrayList;

    invoke-virtual {v11, v2}, Ljava/util/ArrayList;->remove(I)Ljava/lang/Object;

    .line 856
    iget-object v11, p0, Lmiui/view/ViewPager;->Cn:Lmiui/view/PagerAdapter;

    iget-object v0, v0, Lmiui/view/ViewPager$a;->le:Ljava/lang/Object;

    invoke-virtual {v11, p0, v5, v0}, Lmiui/view/PagerAdapter;->destroyItem(Landroid/view/ViewGroup;ILjava/lang/Object;)V

    .line 857
    add-int/lit8 v2, v2, -0x1

    .line 858
    add-int/lit8 v4, v4, -0x1

    .line 859
    if-ltz v2, :cond_a

    iget-object v0, p0, Lmiui/view/ViewPager;->N:Ljava/util/ArrayList;

    invoke-virtual {v0, v2}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/view/ViewPager$a;

    .line 849
    :cond_9
    :goto_a
    add-int/lit8 v5, v5, -0x1

    goto/16 :goto_5

    .line 859
    :cond_a
    const/4 v0, 0x0

    goto :goto_a

    .line 861
    :cond_b
    if-eqz v0, :cond_d

    iget v11, v0, Lmiui/view/ViewPager$a;->position:I

    if-ne v5, v11, :cond_d

    .line 862
    iget v0, v0, Lmiui/view/ViewPager$a;->widthFactor:F

    add-float/2addr v3, v0

    .line 863
    add-int/lit8 v2, v2, -0x1

    .line 864
    if-ltz v2, :cond_c

    iget-object v0, p0, Lmiui/view/ViewPager;->N:Ljava/util/ArrayList;

    invoke-virtual {v0, v2}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/view/ViewPager$a;

    goto :goto_a

    :cond_c
    const/4 v0, 0x0

    goto :goto_a

    .line 866
    :cond_d
    add-int/lit8 v0, v2, 0x1

    invoke-virtual {p0, v5, v0}, Lmiui/view/ViewPager;->j(II)Lmiui/view/ViewPager$a;

    move-result-object v0

    .line 867
    iget v0, v0, Lmiui/view/ViewPager$a;->widthFactor:F

    add-float/2addr v3, v0

    .line 868
    add-int/lit8 v4, v4, 0x1

    .line 869
    if-ltz v2, :cond_e

    iget-object v0, p0, Lmiui/view/ViewPager;->N:Ljava/util/ArrayList;

    invoke-virtual {v0, v2}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/view/ViewPager$a;

    goto :goto_a

    :cond_e
    const/4 v0, 0x0

    goto :goto_a

    .line 876
    :cond_f
    const/4 v0, 0x0

    goto/16 :goto_6

    .line 882
    :cond_10
    iget v7, v0, Lmiui/view/ViewPager$a;->position:I

    if-ne v5, v7, :cond_1b

    iget-boolean v7, v0, Lmiui/view/ViewPager$a;->lf:Z

    if-nez v7, :cond_1b

    .line 883
    iget-object v7, p0, Lmiui/view/ViewPager;->N:Ljava/util/ArrayList;

    invoke-virtual {v7, v3}, Ljava/util/ArrayList;->remove(I)Ljava/lang/Object;

    .line 884
    iget-object v7, p0, Lmiui/view/ViewPager;->Cn:Lmiui/view/PagerAdapter;

    iget-object v0, v0, Lmiui/view/ViewPager$a;->le:Ljava/lang/Object;

    invoke-virtual {v7, p0, v5, v0}, Lmiui/view/PagerAdapter;->destroyItem(Landroid/view/ViewGroup;ILjava/lang/Object;)V

    .line 885
    iget-object v0, p0, Lmiui/view/ViewPager;->N:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    if-ge v3, v0, :cond_11

    iget-object v0, p0, Lmiui/view/ViewPager;->N:Ljava/util/ArrayList;

    invoke-virtual {v0, v3}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/view/ViewPager$a;

    :goto_b
    move v12, v2

    move-object v2, v0

    move v0, v12

    .line 877
    :goto_c
    add-int/lit8 v5, v5, 0x1

    move v12, v0

    move-object v0, v2

    move v2, v12

    goto/16 :goto_7

    .line 885
    :cond_11
    const/4 v0, 0x0

    goto :goto_b

    .line 887
    :cond_12
    if-eqz v0, :cond_14

    iget v7, v0, Lmiui/view/ViewPager$a;->position:I

    if-ne v5, v7, :cond_14

    .line 888
    iget v0, v0, Lmiui/view/ViewPager$a;->widthFactor:F

    add-float/2addr v2, v0

    .line 889
    add-int/lit8 v3, v3, 0x1

    .line 890
    iget-object v0, p0, Lmiui/view/ViewPager;->N:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    if-ge v3, v0, :cond_13

    iget-object v0, p0, Lmiui/view/ViewPager;->N:Ljava/util/ArrayList;

    invoke-virtual {v0, v3}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/view/ViewPager$a;

    :goto_d
    move v12, v2

    move-object v2, v0

    move v0, v12

    goto :goto_c

    :cond_13
    const/4 v0, 0x0

    goto :goto_d

    .line 892
    :cond_14
    invoke-virtual {p0, v5, v3}, Lmiui/view/ViewPager;->j(II)Lmiui/view/ViewPager$a;

    move-result-object v0

    .line 893
    add-int/lit8 v3, v3, 0x1

    .line 894
    iget v0, v0, Lmiui/view/ViewPager$a;->widthFactor:F

    add-float/2addr v2, v0

    .line 895
    iget-object v0, p0, Lmiui/view/ViewPager;->N:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    if-ge v3, v0, :cond_15

    iget-object v0, p0, Lmiui/view/ViewPager;->N:Ljava/util/ArrayList;

    invoke-virtual {v0, v3}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/view/ViewPager$a;

    :goto_e
    move v12, v2

    move-object v2, v0

    move v0, v12

    goto :goto_c

    :cond_15
    const/4 v0, 0x0

    goto :goto_e

    .line 910
    :cond_16
    const/4 v0, 0x0

    goto/16 :goto_8

    .line 928
    :cond_17
    invoke-virtual {p0}, Lmiui/view/ViewPager;->hasFocus()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 929
    invoke-virtual {p0}, Lmiui/view/ViewPager;->findFocus()Landroid/view/View;

    move-result-object v0

    .line 930
    if-eqz v0, :cond_1a

    invoke-virtual {p0, v0}, Lmiui/view/ViewPager;->j(Landroid/view/View;)Lmiui/view/ViewPager$a;

    move-result-object v0

    .line 931
    :goto_f
    if-eqz v0, :cond_18

    iget v0, v0, Lmiui/view/ViewPager$a;->position:I

    iget v1, p0, Lmiui/view/ViewPager;->Co:I

    if-eq v0, v1, :cond_0

    .line 932
    :cond_18
    const/4 v0, 0x0

    :goto_10
    invoke-virtual {p0}, Lmiui/view/ViewPager;->getChildCount()I

    move-result v1

    if-ge v0, v1, :cond_0

    .line 933
    invoke-virtual {p0, v0}, Lmiui/view/ViewPager;->getChildAt(I)Landroid/view/View;

    move-result-object v1

    .line 934
    invoke-virtual {p0, v1}, Lmiui/view/ViewPager;->i(Landroid/view/View;)Lmiui/view/ViewPager$a;

    move-result-object v2

    .line 935
    if-eqz v2, :cond_19

    iget v2, v2, Lmiui/view/ViewPager$a;->position:I

    iget v3, p0, Lmiui/view/ViewPager;->Co:I

    if-ne v2, v3, :cond_19

    .line 936
    const/4 v2, 0x2

    invoke-virtual {v1, v2}, Landroid/view/View;->requestFocus(I)Z

    move-result v1

    if-nez v1, :cond_0

    .line 932
    :cond_19
    add-int/lit8 v0, v0, 0x1

    goto :goto_10

    .line 930
    :cond_1a
    const/4 v0, 0x0

    goto :goto_f

    :cond_1b
    move v12, v2

    move-object v2, v0

    move v0, v12

    goto/16 :goto_c

    :cond_1c
    move-object v6, v0

    goto/16 :goto_3

    :cond_1d
    move-object v0, v3

    goto/16 :goto_2

    :cond_1e
    move-object v1, v0

    goto/16 :goto_0
.end method

.method Z(I)Lmiui/view/ViewPager$a;
    .locals 3

    .prologue
    .line 1155
    iget-object v0, p0, Lmiui/view/ViewPager;->N:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :cond_0
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_1

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/view/ViewPager$a;

    .line 1156
    iget v2, v0, Lmiui/view/ViewPager$a;->position:I

    if-ne v2, p1, :cond_0

    .line 1160
    :goto_0
    return-object v0

    :cond_1
    const/4 v0, 0x0

    goto :goto_0
.end method

.method a(IZZ)V
    .locals 1

    .prologue
    .line 437
    const/4 v0, 0x0

    invoke-virtual {p0, p1, p2, p3, v0}, Lmiui/view/ViewPager;->a(IZZI)V

    .line 438
    return-void
.end method

.method a(IZZI)V
    .locals 6

    .prologue
    const/4 v2, 0x1

    const/4 v1, 0x0

    .line 441
    iget-object v0, p0, Lmiui/view/ViewPager;->Cn:Lmiui/view/PagerAdapter;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lmiui/view/ViewPager;->Cn:Lmiui/view/PagerAdapter;

    invoke-virtual {v0}, Lmiui/view/PagerAdapter;->getCount()I

    move-result v0

    if-gtz v0, :cond_2

    .line 442
    :cond_0
    invoke-direct {p0, v1}, Lmiui/view/ViewPager;->setScrollingCacheEnabled(Z)V

    .line 491
    :cond_1
    :goto_0
    return-void

    .line 445
    :cond_2
    if-nez p3, :cond_3

    iget v0, p0, Lmiui/view/ViewPager;->Co:I

    if-ne v0, p1, :cond_3

    iget-object v0, p0, Lmiui/view/ViewPager;->N:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    if-eqz v0, :cond_3

    .line 446
    invoke-direct {p0, v1}, Lmiui/view/ViewPager;->setScrollingCacheEnabled(Z)V

    goto :goto_0

    .line 450
    :cond_3
    if-gez p1, :cond_6

    move p1, v1

    .line 455
    :cond_4
    :goto_1
    iget v0, p0, Lmiui/view/ViewPager;->CE:I

    .line 456
    iget v3, p0, Lmiui/view/ViewPager;->Co:I

    add-int/2addr v3, v0

    if-gt p1, v3, :cond_5

    iget v3, p0, Lmiui/view/ViewPager;->Co:I

    sub-int v0, v3, v0

    if-ge p1, v0, :cond_7

    .line 460
    :cond_5
    iget-object v0, p0, Lmiui/view/ViewPager;->N:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v3

    :goto_2
    invoke-interface {v3}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_7

    invoke-interface {v3}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/view/ViewPager$a;

    .line 461
    iput-boolean v2, v0, Lmiui/view/ViewPager$a;->lf:Z

    goto :goto_2

    .line 452
    :cond_6
    iget-object v0, p0, Lmiui/view/ViewPager;->Cn:Lmiui/view/PagerAdapter;

    invoke-virtual {v0}, Lmiui/view/PagerAdapter;->getCount()I

    move-result v0

    if-lt p1, v0, :cond_4

    .line 453
    iget-object v0, p0, Lmiui/view/ViewPager;->Cn:Lmiui/view/PagerAdapter;

    invoke-virtual {v0}, Lmiui/view/PagerAdapter;->getCount()I

    move-result v0

    add-int/lit8 p1, v0, -0x1

    goto :goto_1

    .line 464
    :cond_7
    iget v0, p0, Lmiui/view/ViewPager;->Co:I

    if-eq v0, p1, :cond_9

    move v0, v2

    .line 465
    :goto_3
    invoke-virtual {p0, p1}, Lmiui/view/ViewPager;->Y(I)V

    .line 466
    invoke-virtual {p0, p1}, Lmiui/view/ViewPager;->Z(I)Lmiui/view/ViewPager$a;

    move-result-object v2

    .line 468
    if-eqz v2, :cond_d

    .line 469
    invoke-virtual {p0}, Lmiui/view/ViewPager;->getWidth()I

    move-result v3

    .line 470
    int-to-float v3, v3

    iget v4, p0, Lmiui/view/ViewPager;->Cw:F

    iget v2, v2, Lmiui/view/ViewPager$a;->lg:F

    iget v5, p0, Lmiui/view/ViewPager;->Cx:F

    invoke-static {v2, v5}, Ljava/lang/Math;->min(FF)F

    move-result v2

    invoke-static {v4, v2}, Ljava/lang/Math;->max(FF)F

    move-result v2

    mul-float/2addr v2, v3

    float-to-int v2, v2

    .line 473
    :goto_4
    if-eqz p2, :cond_a

    .line 474
    invoke-virtual {p0, v2, v1, p4}, Lmiui/view/ViewPager;->h(III)V

    .line 475
    if-eqz v0, :cond_8

    iget-object v1, p0, Lmiui/view/ViewPager;->Dl:Lmiui/view/ViewPager$OnPageChangeListener;

    if-eqz v1, :cond_8

    .line 476
    iget-object v1, p0, Lmiui/view/ViewPager;->Dl:Lmiui/view/ViewPager$OnPageChangeListener;

    invoke-interface {v1, p1}, Lmiui/view/ViewPager$OnPageChangeListener;->onPageSelected(I)V

    .line 478
    :cond_8
    if-eqz v0, :cond_1

    iget-object v0, p0, Lmiui/view/ViewPager;->Dm:Lmiui/view/ViewPager$OnPageChangeListener;

    if-eqz v0, :cond_1

    .line 479
    iget-object v0, p0, Lmiui/view/ViewPager;->Dm:Lmiui/view/ViewPager$OnPageChangeListener;

    invoke-interface {v0, p1}, Lmiui/view/ViewPager$OnPageChangeListener;->onPageSelected(I)V

    goto/16 :goto_0

    :cond_9
    move v0, v1

    .line 464
    goto :goto_3

    .line 482
    :cond_a
    if-eqz v0, :cond_b

    iget-object v3, p0, Lmiui/view/ViewPager;->Dl:Lmiui/view/ViewPager$OnPageChangeListener;

    if-eqz v3, :cond_b

    .line 483
    iget-object v3, p0, Lmiui/view/ViewPager;->Dl:Lmiui/view/ViewPager$OnPageChangeListener;

    invoke-interface {v3, p1}, Lmiui/view/ViewPager$OnPageChangeListener;->onPageSelected(I)V

    .line 485
    :cond_b
    if-eqz v0, :cond_c

    iget-object v0, p0, Lmiui/view/ViewPager;->Dm:Lmiui/view/ViewPager$OnPageChangeListener;

    if-eqz v0, :cond_c

    .line 486
    iget-object v0, p0, Lmiui/view/ViewPager;->Dm:Lmiui/view/ViewPager$OnPageChangeListener;

    invoke-interface {v0, p1}, Lmiui/view/ViewPager$OnPageChangeListener;->onPageSelected(I)V

    .line 488
    :cond_c
    invoke-direct {p0}, Lmiui/view/ViewPager;->cT()V

    .line 489
    invoke-virtual {p0, v2, v1}, Lmiui/view/ViewPager;->scrollTo(II)V

    goto/16 :goto_0

    :cond_d
    move v2, v1

    goto :goto_4
.end method

.method a(Lmiui/view/ViewPager$b;)V
    .locals 0

    .prologue
    .line 406
    iput-object p1, p0, Lmiui/view/ViewPager;->Dn:Lmiui/view/ViewPager$b;

    .line 407
    return-void
.end method

.method public addFocusables(Ljava/util/ArrayList;II)V
    .locals 6
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/ArrayList",
            "<",
            "Landroid/view/View;",
            ">;II)V"
        }
    .end annotation

    .prologue
    .line 2411
    invoke-virtual {p1}, Ljava/util/ArrayList;->size()I

    move-result v1

    .line 2413
    invoke-virtual {p0}, Lmiui/view/ViewPager;->getDescendantFocusability()I

    move-result v2

    .line 2415
    const/high16 v0, 0x60000

    if-eq v2, v0, :cond_1

    .line 2416
    const/4 v0, 0x0

    :goto_0
    invoke-virtual {p0}, Lmiui/view/ViewPager;->getChildCount()I

    move-result v3

    if-ge v0, v3, :cond_1

    .line 2417
    invoke-virtual {p0, v0}, Lmiui/view/ViewPager;->getChildAt(I)Landroid/view/View;

    move-result-object v3

    .line 2418
    invoke-virtual {v3}, Landroid/view/View;->getVisibility()I

    move-result v4

    if-nez v4, :cond_0

    .line 2419
    invoke-virtual {p0, v3}, Lmiui/view/ViewPager;->i(Landroid/view/View;)Lmiui/view/ViewPager$a;

    move-result-object v4

    .line 2420
    if-eqz v4, :cond_0

    iget v4, v4, Lmiui/view/ViewPager$a;->position:I

    iget v5, p0, Lmiui/view/ViewPager;->Co:I

    if-ne v4, v5, :cond_0

    .line 2421
    invoke-virtual {v3, p1, p2, p3}, Landroid/view/View;->addFocusables(Ljava/util/ArrayList;II)V

    .line 2416
    :cond_0
    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    .line 2431
    :cond_1
    const/high16 v0, 0x40000

    if-ne v2, v0, :cond_2

    invoke-virtual {p1}, Ljava/util/ArrayList;->size()I

    move-result v0

    if-ne v1, v0, :cond_3

    .line 2437
    :cond_2
    invoke-virtual {p0}, Lmiui/view/ViewPager;->isFocusable()Z

    move-result v0

    if-nez v0, :cond_4

    .line 2448
    :cond_3
    :goto_1
    return-void

    .line 2440
    :cond_4
    and-int/lit8 v0, p3, 0x1

    const/4 v1, 0x1

    if-ne v0, v1, :cond_5

    invoke-virtual {p0}, Lmiui/view/ViewPager;->isInTouchMode()Z

    move-result v0

    if-eqz v0, :cond_5

    invoke-virtual {p0}, Lmiui/view/ViewPager;->isFocusableInTouchMode()Z

    move-result v0

    if-eqz v0, :cond_3

    .line 2444
    :cond_5
    if-eqz p1, :cond_3

    .line 2445
    invoke-virtual {p1, p0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_1
.end method

.method public addTouchables(Ljava/util/ArrayList;)V
    .locals 4
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/ArrayList",
            "<",
            "Landroid/view/View;",
            ">;)V"
        }
    .end annotation

    .prologue
    .line 2458
    const/4 v0, 0x0

    :goto_0
    invoke-virtual {p0}, Lmiui/view/ViewPager;->getChildCount()I

    move-result v1

    if-ge v0, v1, :cond_1

    .line 2459
    invoke-virtual {p0, v0}, Lmiui/view/ViewPager;->getChildAt(I)Landroid/view/View;

    move-result-object v1

    .line 2460
    invoke-virtual {v1}, Landroid/view/View;->getVisibility()I

    move-result v2

    if-nez v2, :cond_0

    .line 2461
    invoke-virtual {p0, v1}, Lmiui/view/ViewPager;->i(Landroid/view/View;)Lmiui/view/ViewPager$a;

    move-result-object v2

    .line 2462
    if-eqz v2, :cond_0

    iget v2, v2, Lmiui/view/ViewPager$a;->position:I

    iget v3, p0, Lmiui/view/ViewPager;->Co:I

    if-ne v2, v3, :cond_0

    .line 2463
    invoke-virtual {v1, p1}, Landroid/view/View;->addTouchables(Ljava/util/ArrayList;)V

    .line 2458
    :cond_0
    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    .line 2467
    :cond_1
    return-void
.end method

.method public addView(Landroid/view/View;ILandroid/view/ViewGroup$LayoutParams;)V
    .locals 4

    .prologue
    .line 1110
    invoke-virtual {p0, p3}, Lmiui/view/ViewPager;->checkLayoutParams(Landroid/view/ViewGroup$LayoutParams;)Z

    move-result v0

    if-nez v0, :cond_2

    .line 1111
    invoke-virtual {p0, p3}, Lmiui/view/ViewPager;->generateLayoutParams(Landroid/view/ViewGroup$LayoutParams;)Landroid/view/ViewGroup$LayoutParams;

    move-result-object v1

    :goto_0
    move-object v0, v1

    .line 1113
    check-cast v0, Lmiui/view/ViewPager$LayoutParams;

    .line 1114
    iget-boolean v2, v0, Lmiui/view/ViewPager$LayoutParams;->isDecor:Z

    instance-of v3, p1, Lmiui/view/ViewPager$c;

    or-int/2addr v2, v3

    iput-boolean v2, v0, Lmiui/view/ViewPager$LayoutParams;->isDecor:Z

    .line 1115
    iget-boolean v2, p0, Lmiui/view/ViewPager;->CA:Z

    if-eqz v2, :cond_1

    .line 1116
    iget-boolean v2, v0, Lmiui/view/ViewPager$LayoutParams;->isDecor:Z

    if-eqz v2, :cond_0

    .line 1117
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "Cannot add pager decor view during layout"

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 1119
    :cond_0
    const/4 v2, 0x1

    iput-boolean v2, v0, Lmiui/view/ViewPager$LayoutParams;->needsMeasure:Z

    .line 1120
    invoke-virtual {p0, p1, p2, v1}, Lmiui/view/ViewPager;->addViewInLayout(Landroid/view/View;ILandroid/view/ViewGroup$LayoutParams;)Z

    .line 1132
    :goto_1
    return-void

    .line 1122
    :cond_1
    invoke-super {p0, p1, p2, v1}, Landroid/view/ViewGroup;->addView(Landroid/view/View;ILandroid/view/ViewGroup$LayoutParams;)V

    goto :goto_1

    :cond_2
    move-object v1, p3

    goto :goto_0
.end method

.method public arrowScroll(I)Z
    .locals 5

    .prologue
    const/16 v4, 0x42

    const/16 v3, 0x11

    .line 2322
    invoke-virtual {p0}, Lmiui/view/ViewPager;->findFocus()Landroid/view/View;

    move-result-object v0

    .line 2323
    if-ne v0, p0, :cond_0

    const/4 v0, 0x0

    .line 2325
    :cond_0
    const/4 v1, 0x0

    .line 2327
    invoke-static {}, Landroid/view/FocusFinder;->getInstance()Landroid/view/FocusFinder;

    move-result-object v2

    invoke-virtual {v2, p0, v0, p1}, Landroid/view/FocusFinder;->findNextFocus(Landroid/view/ViewGroup;Landroid/view/View;I)Landroid/view/View;

    move-result-object v2

    .line 2329
    if-eqz v2, :cond_5

    if-eq v2, v0, :cond_5

    .line 2330
    if-ne p1, v3, :cond_3

    .line 2333
    iget-object v1, p0, Lmiui/view/ViewPager;->wY:Landroid/graphics/Rect;

    invoke-direct {p0, v1, v2}, Lmiui/view/ViewPager;->a(Landroid/graphics/Rect;Landroid/view/View;)Landroid/graphics/Rect;

    move-result-object v1

    iget v1, v1, Landroid/graphics/Rect;->left:I

    .line 2334
    iget-object v3, p0, Lmiui/view/ViewPager;->wY:Landroid/graphics/Rect;

    invoke-direct {p0, v3, v0}, Lmiui/view/ViewPager;->a(Landroid/graphics/Rect;Landroid/view/View;)Landroid/graphics/Rect;

    move-result-object v3

    iget v3, v3, Landroid/graphics/Rect;->left:I

    .line 2335
    if-eqz v0, :cond_2

    if-lt v1, v3, :cond_2

    .line 2336
    invoke-virtual {p0}, Lmiui/view/ViewPager;->cW()Z

    move-result v0

    .line 2358
    :goto_0
    if-eqz v0, :cond_1

    .line 2359
    invoke-static {p1}, Landroid/view/SoundEffectConstants;->getContantForFocusDirection(I)I

    move-result v1

    invoke-virtual {p0, v1}, Lmiui/view/ViewPager;->playSoundEffect(I)V

    .line 2361
    :cond_1
    return v0

    .line 2338
    :cond_2
    invoke-virtual {v2}, Landroid/view/View;->requestFocus()Z

    move-result v0

    goto :goto_0

    .line 2340
    :cond_3
    if-ne p1, v4, :cond_9

    .line 2343
    iget-object v1, p0, Lmiui/view/ViewPager;->wY:Landroid/graphics/Rect;

    invoke-direct {p0, v1, v2}, Lmiui/view/ViewPager;->a(Landroid/graphics/Rect;Landroid/view/View;)Landroid/graphics/Rect;

    move-result-object v1

    iget v1, v1, Landroid/graphics/Rect;->left:I

    .line 2344
    iget-object v3, p0, Lmiui/view/ViewPager;->wY:Landroid/graphics/Rect;

    invoke-direct {p0, v3, v0}, Lmiui/view/ViewPager;->a(Landroid/graphics/Rect;Landroid/view/View;)Landroid/graphics/Rect;

    move-result-object v3

    iget v3, v3, Landroid/graphics/Rect;->left:I

    .line 2345
    if-eqz v0, :cond_4

    if-gt v1, v3, :cond_4

    .line 2346
    invoke-virtual {p0}, Lmiui/view/ViewPager;->cX()Z

    move-result v0

    goto :goto_0

    .line 2348
    :cond_4
    invoke-virtual {v2}, Landroid/view/View;->requestFocus()Z

    move-result v0

    goto :goto_0

    .line 2351
    :cond_5
    if-eq p1, v3, :cond_6

    const/4 v0, 0x1

    if-ne p1, v0, :cond_7

    .line 2353
    :cond_6
    invoke-virtual {p0}, Lmiui/view/ViewPager;->cW()Z

    move-result v0

    goto :goto_0

    .line 2354
    :cond_7
    if-eq p1, v4, :cond_8

    const/4 v0, 0x2

    if-ne p1, v0, :cond_9

    .line 2356
    :cond_8
    invoke-virtual {p0}, Lmiui/view/ViewPager;->cX()Z

    move-result v0

    goto :goto_0

    :cond_9
    move v0, v1

    goto :goto_0
.end method

.method public beginFakeDrag()Z
    .locals 9

    .prologue
    const/4 v8, 0x1

    const/4 v4, 0x0

    const/4 v5, 0x0

    .line 2098
    iget-boolean v0, p0, Lmiui/view/ViewPager;->CJ:Z

    if-eqz v0, :cond_0

    .line 2114
    :goto_0
    return v4

    .line 2101
    :cond_0
    iput-boolean v8, p0, Lmiui/view/ViewPager;->De:Z

    .line 2102
    invoke-direct {p0, v8}, Lmiui/view/ViewPager;->X(I)V

    .line 2103
    iput v5, p0, Lmiui/view/ViewPager;->mLastMotionX:F

    iput v5, p0, Lmiui/view/ViewPager;->nT:F

    .line 2104
    iget-object v0, p0, Lmiui/view/ViewPager;->bR:Landroid/view/VelocityTracker;

    if-nez v0, :cond_1

    .line 2105
    invoke-static {}, Landroid/view/VelocityTracker;->obtain()Landroid/view/VelocityTracker;

    move-result-object v0

    iput-object v0, p0, Lmiui/view/ViewPager;->bR:Landroid/view/VelocityTracker;

    .line 2109
    :goto_1
    invoke-static {}, Landroid/os/SystemClock;->uptimeMillis()J

    move-result-wide v0

    move-wide v2, v0

    move v6, v5

    move v7, v4

    .line 2110
    invoke-static/range {v0 .. v7}, Landroid/view/MotionEvent;->obtain(JJIFFI)Landroid/view/MotionEvent;

    move-result-object v2

    .line 2111
    iget-object v3, p0, Lmiui/view/ViewPager;->bR:Landroid/view/VelocityTracker;

    invoke-virtual {v3, v2}, Landroid/view/VelocityTracker;->addMovement(Landroid/view/MotionEvent;)V

    .line 2112
    invoke-virtual {v2}, Landroid/view/MotionEvent;->recycle()V

    .line 2113
    iput-wide v0, p0, Lmiui/view/ViewPager;->Df:J

    move v4, v8

    .line 2114
    goto :goto_0

    .line 2107
    :cond_1
    iget-object v0, p0, Lmiui/view/ViewPager;->bR:Landroid/view/VelocityTracker;

    invoke-virtual {v0}, Landroid/view/VelocityTracker;->clear()V

    goto :goto_1
.end method

.method cP()V
    .locals 4

    .prologue
    const/4 v3, 0x1

    .line 304
    const/4 v0, 0x0

    invoke-virtual {p0, v0}, Lmiui/view/ViewPager;->setWillNotDraw(Z)V

    .line 305
    const/high16 v0, 0x40000

    invoke-virtual {p0, v0}, Lmiui/view/ViewPager;->setDescendantFocusability(I)V

    .line 306
    invoke-virtual {p0, v3}, Lmiui/view/ViewPager;->setFocusable(Z)V

    .line 307
    invoke-virtual {p0}, Lmiui/view/ViewPager;->getContext()Landroid/content/Context;

    move-result-object v0

    .line 308
    new-instance v1, Landroid/widget/Scroller;

    sget-object v2, Lmiui/view/ViewPager;->zs:Landroid/view/animation/Interpolator;

    invoke-direct {v1, v0, v2}, Landroid/widget/Scroller;-><init>(Landroid/content/Context;Landroid/view/animation/Interpolator;)V

    iput-object v1, p0, Lmiui/view/ViewPager;->cL:Landroid/widget/Scroller;

    .line 309
    invoke-static {v0}, Landroid/view/ViewConfiguration;->get(Landroid/content/Context;)Landroid/view/ViewConfiguration;

    move-result-object v1

    .line 310
    invoke-virtual {v1}, Landroid/view/ViewConfiguration;->getScaledPagingTouchSlop()I

    move-result v2

    iput v2, p0, Lmiui/view/ViewPager;->cT:I

    .line 311
    invoke-virtual {v1}, Landroid/view/ViewConfiguration;->getScaledMinimumFlingVelocity()I

    move-result v2

    iput v2, p0, Lmiui/view/ViewPager;->Da:I

    .line 312
    invoke-virtual {v1}, Landroid/view/ViewConfiguration;->getScaledMaximumFlingVelocity()I

    move-result v1

    iput v1, p0, Lmiui/view/ViewPager;->cU:I

    .line 313
    new-instance v1, Landroid/widget/EdgeEffect;

    invoke-direct {v1, v0}, Landroid/widget/EdgeEffect;-><init>(Landroid/content/Context;)V

    iput-object v1, p0, Lmiui/view/ViewPager;->Dg:Landroid/widget/EdgeEffect;

    .line 314
    new-instance v1, Landroid/widget/EdgeEffect;

    invoke-direct {v1, v0}, Landroid/widget/EdgeEffect;-><init>(Landroid/content/Context;)V

    iput-object v1, p0, Lmiui/view/ViewPager;->Dh:Landroid/widget/EdgeEffect;

    .line 316
    invoke-virtual {v0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    invoke-virtual {v0}, Landroid/content/res/Resources;->getDisplayMetrics()Landroid/util/DisplayMetrics;

    move-result-object v0

    iget v0, v0, Landroid/util/DisplayMetrics;->density:F

    .line 317
    const/high16 v1, 0x41c80000

    mul-float/2addr v1, v0

    float-to-int v1, v1

    iput v1, p0, Lmiui/view/ViewPager;->Db:I

    .line 318
    const/high16 v1, 0x40000000

    mul-float/2addr v1, v0

    float-to-int v1, v1

    iput v1, p0, Lmiui/view/ViewPager;->Dc:I

    .line 319
    const/high16 v1, 0x41800000

    mul-float/2addr v0, v1

    float-to-int v0, v0

    iput v0, p0, Lmiui/view/ViewPager;->CS:I

    .line 321
    invoke-virtual {p0}, Lmiui/view/ViewPager;->getImportantForAccessibility()I

    move-result v0

    if-nez v0, :cond_0

    .line 322
    invoke-virtual {p0, v3}, Lmiui/view/ViewPager;->setImportantForAccessibility(I)V

    .line 324
    :cond_0
    return-void
.end method

.method cR()V
    .locals 11

    .prologue
    const/4 v1, 0x1

    const/4 v2, 0x0

    .line 703
    iget-object v0, p0, Lmiui/view/ViewPager;->N:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    iget v3, p0, Lmiui/view/ViewPager;->CE:I

    mul-int/lit8 v3, v3, 0x2

    add-int/lit8 v3, v3, 0x1

    if-ge v0, v3, :cond_0

    iget-object v0, p0, Lmiui/view/ViewPager;->N:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    iget-object v3, p0, Lmiui/view/ViewPager;->Cn:Lmiui/view/PagerAdapter;

    invoke-virtual {v3}, Lmiui/view/PagerAdapter;->getCount()I

    move-result v3

    if-ge v0, v3, :cond_0

    move v0, v1

    .line 705
    :goto_0
    iget v3, p0, Lmiui/view/ViewPager;->Co:I

    move v4, v2

    move v5, v2

    move v6, v3

    move v7, v0

    move v3, v2

    .line 709
    :goto_1
    iget-object v0, p0, Lmiui/view/ViewPager;->N:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    if-ge v3, v0, :cond_8

    .line 710
    iget-object v0, p0, Lmiui/view/ViewPager;->N:Ljava/util/ArrayList;

    invoke-virtual {v0, v3}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/view/ViewPager$a;

    .line 711
    iget-object v8, p0, Lmiui/view/ViewPager;->Cn:Lmiui/view/PagerAdapter;

    iget-object v9, v0, Lmiui/view/ViewPager$a;->le:Ljava/lang/Object;

    invoke-virtual {v8, v9}, Lmiui/view/PagerAdapter;->getItemPosition(Ljava/lang/Object;)I

    move-result v8

    .line 713
    const/4 v9, -0x1

    if-ne v8, v9, :cond_2

    .line 715
    iget-boolean v8, v0, Lmiui/view/ViewPager$a;->lh:Z

    iget-object v9, p0, Lmiui/view/ViewPager;->Cn:Lmiui/view/PagerAdapter;

    iget v10, v0, Lmiui/view/ViewPager$a;->position:I

    invoke-virtual {v9, v10}, Lmiui/view/PagerAdapter;->hasActionMenu(I)Z

    move-result v9

    if-eq v8, v9, :cond_e

    .line 716
    iget-boolean v5, v0, Lmiui/view/ViewPager$a;->lh:Z

    if-nez v5, :cond_1

    move v5, v1

    :goto_2
    iput-boolean v5, v0, Lmiui/view/ViewPager$a;->lh:Z

    move v0, v3

    move v5, v6

    move v3, v4

    move v6, v7

    move v4, v1

    .line 709
    :goto_3
    add-int/lit8 v0, v0, 0x1

    move v7, v6

    move v6, v5

    move v5, v4

    move v4, v3

    move v3, v0

    goto :goto_1

    :cond_0
    move v0, v2

    .line 703
    goto :goto_0

    :cond_1
    move v5, v2

    .line 716
    goto :goto_2

    .line 723
    :cond_2
    const/4 v9, -0x2

    if-ne v8, v9, :cond_4

    .line 724
    iget-object v7, p0, Lmiui/view/ViewPager;->N:Ljava/util/ArrayList;

    invoke-virtual {v7, v3}, Ljava/util/ArrayList;->remove(I)Ljava/lang/Object;

    .line 725
    add-int/lit8 v3, v3, -0x1

    .line 727
    if-nez v4, :cond_3

    .line 728
    iget-object v4, p0, Lmiui/view/ViewPager;->Cn:Lmiui/view/PagerAdapter;

    invoke-virtual {v4, p0}, Lmiui/view/PagerAdapter;->startUpdate(Landroid/view/ViewGroup;)V

    move v4, v1

    .line 732
    :cond_3
    iget-object v7, p0, Lmiui/view/ViewPager;->Cn:Lmiui/view/PagerAdapter;

    iget v8, v0, Lmiui/view/ViewPager$a;->position:I

    iget-object v9, v0, Lmiui/view/ViewPager$a;->le:Ljava/lang/Object;

    invoke-virtual {v7, p0, v8, v9}, Lmiui/view/PagerAdapter;->destroyItem(Landroid/view/ViewGroup;ILjava/lang/Object;)V

    .line 735
    iget v7, p0, Lmiui/view/ViewPager;->Co:I

    iget v0, v0, Lmiui/view/ViewPager$a;->position:I

    if-ne v7, v0, :cond_f

    .line 737
    iget v0, p0, Lmiui/view/ViewPager;->Co:I

    iget-object v6, p0, Lmiui/view/ViewPager;->Cn:Lmiui/view/PagerAdapter;

    invoke-virtual {v6}, Lmiui/view/PagerAdapter;->getCount()I

    move-result v6

    add-int/lit8 v6, v6, -0x1

    invoke-static {v0, v6}, Ljava/lang/Math;->min(II)I

    move-result v0

    invoke-static {v2, v0}, Ljava/lang/Math;->max(II)I

    move-result v6

    move v0, v3

    move v3, v4

    move v4, v5

    move v5, v6

    move v6, v1

    .line 738
    goto :goto_3

    .line 743
    :cond_4
    iget v9, v0, Lmiui/view/ViewPager$a;->position:I

    if-eq v9, v8, :cond_6

    .line 744
    iget v7, v0, Lmiui/view/ViewPager$a;->position:I

    iget v9, p0, Lmiui/view/ViewPager;->Co:I

    if-ne v7, v9, :cond_5

    move v6, v8

    .line 749
    :cond_5
    iput v8, v0, Lmiui/view/ViewPager$a;->position:I

    move v7, v1

    .line 754
    :cond_6
    iget-boolean v8, v0, Lmiui/view/ViewPager$a;->lh:Z

    iget-object v9, p0, Lmiui/view/ViewPager;->Cn:Lmiui/view/PagerAdapter;

    iget v10, v0, Lmiui/view/ViewPager$a;->position:I

    invoke-virtual {v9, v10}, Lmiui/view/PagerAdapter;->hasActionMenu(I)Z

    move-result v9

    if-eq v8, v9, :cond_e

    .line 755
    iget-boolean v5, v0, Lmiui/view/ViewPager$a;->lh:Z

    if-nez v5, :cond_7

    move v5, v1

    :goto_4
    iput-boolean v5, v0, Lmiui/view/ViewPager$a;->lh:Z

    move v0, v3

    move v5, v6

    move v3, v4

    move v6, v7

    move v4, v1

    .line 756
    goto :goto_3

    :cond_7
    move v5, v2

    .line 755
    goto :goto_4

    .line 760
    :cond_8
    if-eqz v4, :cond_9

    .line 761
    iget-object v0, p0, Lmiui/view/ViewPager;->Cn:Lmiui/view/PagerAdapter;

    invoke-virtual {v0, p0}, Lmiui/view/PagerAdapter;->finishUpdate(Landroid/view/ViewGroup;)V

    .line 764
    :cond_9
    iget-object v0, p0, Lmiui/view/ViewPager;->N:Ljava/util/ArrayList;

    sget-object v3, Lmiui/view/ViewPager;->Cl:Ljava/util/Comparator;

    invoke-static {v0, v3}, Ljava/util/Collections;->sort(Ljava/util/List;Ljava/util/Comparator;)V

    .line 766
    if-eqz v7, :cond_d

    .line 768
    invoke-virtual {p0}, Lmiui/view/ViewPager;->getChildCount()I

    move-result v4

    move v3, v2

    .line 769
    :goto_5
    if-ge v3, v4, :cond_b

    .line 770
    invoke-virtual {p0, v3}, Lmiui/view/ViewPager;->getChildAt(I)Landroid/view/View;

    move-result-object v0

    .line 771
    invoke-virtual {v0}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v0

    check-cast v0, Lmiui/view/ViewPager$LayoutParams;

    .line 772
    iget-boolean v5, v0, Lmiui/view/ViewPager$LayoutParams;->isDecor:Z

    if-nez v5, :cond_a

    .line 773
    const/4 v5, 0x0

    iput v5, v0, Lmiui/view/ViewPager$LayoutParams;->widthFactor:F

    .line 769
    :cond_a
    add-int/lit8 v0, v3, 0x1

    move v3, v0

    goto :goto_5

    .line 777
    :cond_b
    invoke-virtual {p0, v6, v2, v1}, Lmiui/view/ViewPager;->a(IZZ)V

    .line 778
    invoke-virtual {p0}, Lmiui/view/ViewPager;->requestLayout()V

    .line 783
    :goto_6
    if-eqz v2, :cond_c

    .line 784
    invoke-virtual {p0}, Lmiui/view/ViewPager;->requestLayout()V

    .line 786
    :cond_c
    return-void

    :cond_d
    move v2, v5

    goto :goto_6

    :cond_e
    move v0, v3

    move v3, v4

    move v4, v5

    move v5, v6

    move v6, v7

    goto/16 :goto_3

    :cond_f
    move v0, v3

    move v3, v4

    move v4, v5

    move v5, v6

    move v6, v1

    goto/16 :goto_3
.end method

.method cS()V
    .locals 1

    .prologue
    .line 789
    iget v0, p0, Lmiui/view/ViewPager;->Co:I

    invoke-virtual {p0, v0}, Lmiui/view/ViewPager;->Y(I)V

    .line 790
    return-void
.end method

.method cW()Z
    .locals 2

    .prologue
    const/4 v0, 0x1

    .line 2391
    iget v1, p0, Lmiui/view/ViewPager;->Co:I

    if-lez v1, :cond_0

    .line 2392
    iget v1, p0, Lmiui/view/ViewPager;->Co:I

    add-int/lit8 v1, v1, -0x1

    invoke-virtual {p0, v1, v0}, Lmiui/view/ViewPager;->setCurrentItem(IZ)V

    .line 2395
    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method cX()Z
    .locals 3

    .prologue
    const/4 v0, 0x1

    .line 2399
    iget-object v1, p0, Lmiui/view/ViewPager;->Cn:Lmiui/view/PagerAdapter;

    if-eqz v1, :cond_0

    iget v1, p0, Lmiui/view/ViewPager;->Co:I

    iget-object v2, p0, Lmiui/view/ViewPager;->Cn:Lmiui/view/PagerAdapter;

    invoke-virtual {v2}, Lmiui/view/PagerAdapter;->getCount()I

    move-result v2

    add-int/lit8 v2, v2, -0x1

    if-ge v1, v2, :cond_0

    .line 2400
    iget v1, p0, Lmiui/view/ViewPager;->Co:I

    add-int/lit8 v1, v1, 0x1

    invoke-virtual {p0, v1, v0}, Lmiui/view/ViewPager;->setCurrentItem(IZ)V

    .line 2403
    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method protected canScroll(Landroid/view/View;ZIII)Z
    .locals 10

    .prologue
    const/4 v2, 0x1

    .line 2259
    instance-of v0, p1, Landroid/view/ViewGroup;

    if-eqz v0, :cond_2

    move-object v6, p1

    .line 2260
    check-cast v6, Landroid/view/ViewGroup;

    .line 2261
    invoke-virtual {p1}, Landroid/view/View;->getScrollX()I

    move-result v8

    .line 2262
    invoke-virtual {p1}, Landroid/view/View;->getScrollY()I

    move-result v9

    .line 2263
    invoke-virtual {v6}, Landroid/view/ViewGroup;->getChildCount()I

    move-result v0

    .line 2265
    add-int/lit8 v0, v0, -0x1

    move v7, v0

    :goto_0
    if-ltz v7, :cond_2

    .line 2268
    invoke-virtual {v6, v7}, Landroid/view/ViewGroup;->getChildAt(I)Landroid/view/View;

    move-result-object v1

    .line 2269
    add-int v0, p4, v8

    invoke-virtual {v1}, Landroid/view/View;->getLeft()I

    move-result v3

    if-lt v0, v3, :cond_1

    add-int v0, p4, v8

    invoke-virtual {v1}, Landroid/view/View;->getRight()I

    move-result v3

    if-ge v0, v3, :cond_1

    add-int v0, p5, v9

    invoke-virtual {v1}, Landroid/view/View;->getTop()I

    move-result v3

    if-lt v0, v3, :cond_1

    add-int v0, p5, v9

    invoke-virtual {v1}, Landroid/view/View;->getBottom()I

    move-result v3

    if-ge v0, v3, :cond_1

    add-int v0, p4, v8

    invoke-virtual {v1}, Landroid/view/View;->getLeft()I

    move-result v3

    sub-int v4, v0, v3

    add-int v0, p5, v9

    invoke-virtual {v1}, Landroid/view/View;->getTop()I

    move-result v3

    sub-int v5, v0, v3

    move-object v0, p0

    move v3, p3

    invoke-virtual/range {v0 .. v5}, Lmiui/view/ViewPager;->canScroll(Landroid/view/View;ZIII)Z

    move-result v0

    if-eqz v0, :cond_1

    .line 2278
    :cond_0
    :goto_1
    return v2

    .line 2265
    :cond_1
    add-int/lit8 v0, v7, -0x1

    move v7, v0

    goto :goto_0

    .line 2278
    :cond_2
    if-eqz p2, :cond_3

    neg-int v0, p3

    invoke-virtual {p1, v0}, Landroid/view/View;->canScrollHorizontally(I)Z

    move-result v0

    if-nez v0, :cond_0

    :cond_3
    const/4 v2, 0x0

    goto :goto_1
.end method

.method protected checkLayoutParams(Landroid/view/ViewGroup$LayoutParams;)Z
    .locals 1

    .prologue
    .line 2536
    instance-of v0, p1, Lmiui/view/ViewPager$LayoutParams;

    if-eqz v0, :cond_0

    invoke-super {p0, p1}, Landroid/view/ViewGroup;->checkLayoutParams(Landroid/view/ViewGroup$LayoutParams;)Z

    move-result v0

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public computeScroll()V
    .locals 5

    .prologue
    const/4 v4, 0x0

    .line 1429
    iget-object v0, p0, Lmiui/view/ViewPager;->cL:Landroid/widget/Scroller;

    invoke-virtual {v0}, Landroid/widget/Scroller;->isFinished()Z

    move-result v0

    if-nez v0, :cond_2

    iget-object v0, p0, Lmiui/view/ViewPager;->cL:Landroid/widget/Scroller;

    invoke-virtual {v0}, Landroid/widget/Scroller;->computeScrollOffset()Z

    move-result v0

    if-eqz v0, :cond_2

    .line 1430
    invoke-virtual {p0}, Lmiui/view/ViewPager;->getScrollX()I

    move-result v0

    .line 1431
    invoke-virtual {p0}, Lmiui/view/ViewPager;->getScrollY()I

    move-result v1

    .line 1432
    iget-object v2, p0, Lmiui/view/ViewPager;->cL:Landroid/widget/Scroller;

    invoke-virtual {v2}, Landroid/widget/Scroller;->getCurrX()I

    move-result v2

    .line 1433
    iget-object v3, p0, Lmiui/view/ViewPager;->cL:Landroid/widget/Scroller;

    invoke-virtual {v3}, Landroid/widget/Scroller;->getCurrY()I

    move-result v3

    .line 1435
    if-ne v0, v2, :cond_0

    if-eq v1, v3, :cond_1

    .line 1436
    :cond_0
    invoke-virtual {p0, v2, v3}, Lmiui/view/ViewPager;->scrollTo(II)V

    .line 1437
    invoke-direct {p0, v2, v4}, Lmiui/view/ViewPager;->f(IZ)Z

    move-result v0

    if-nez v0, :cond_1

    .line 1438
    iget-object v0, p0, Lmiui/view/ViewPager;->cL:Landroid/widget/Scroller;

    invoke-virtual {v0}, Landroid/widget/Scroller;->abortAnimation()V

    .line 1439
    invoke-virtual {p0, v4, v3}, Lmiui/view/ViewPager;->scrollTo(II)V

    .line 1444
    :cond_1
    invoke-virtual {p0}, Lmiui/view/ViewPager;->postInvalidateOnAnimation()V

    .line 1450
    :goto_0
    return-void

    .line 1449
    :cond_2
    invoke-direct {p0}, Lmiui/view/ViewPager;->cT()V

    goto :goto_0
.end method

.method d(F)F
    .locals 4

    .prologue
    .line 623
    const/high16 v0, 0x3f000000

    sub-float v0, p1, v0

    .line 624
    float-to-double v0, v0

    const-wide v2, 0x3fde28c7460698c7L

    mul-double/2addr v0, v2

    double-to-float v0, v0

    .line 625
    float-to-double v0, v0

    invoke-static {v0, v1}, Ljava/lang/Math;->sin(D)D

    move-result-wide v0

    double-to-float v0, v0

    return v0
.end method

.method public dispatchKeyEvent(Landroid/view/KeyEvent;)Z
    .locals 1

    .prologue
    .line 2284
    invoke-super {p0, p1}, Landroid/view/ViewGroup;->dispatchKeyEvent(Landroid/view/KeyEvent;)Z

    move-result v0

    if-nez v0, :cond_0

    invoke-virtual {p0, p1}, Lmiui/view/ViewPager;->executeKeyEvent(Landroid/view/KeyEvent;)Z

    move-result v0

    if-eqz v0, :cond_1

    :cond_0
    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_1
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public dispatchPopulateAccessibilityEvent(Landroid/view/accessibility/AccessibilityEvent;)Z
    .locals 6

    .prologue
    const/4 v0, 0x0

    .line 2509
    invoke-virtual {p0}, Lmiui/view/ViewPager;->getChildCount()I

    move-result v2

    move v1, v0

    .line 2510
    :goto_0
    if-ge v1, v2, :cond_0

    .line 2511
    invoke-virtual {p0, v1}, Lmiui/view/ViewPager;->getChildAt(I)Landroid/view/View;

    move-result-object v3

    .line 2512
    invoke-virtual {v3}, Landroid/view/View;->getVisibility()I

    move-result v4

    if-nez v4, :cond_1

    .line 2513
    invoke-virtual {p0, v3}, Lmiui/view/ViewPager;->i(Landroid/view/View;)Lmiui/view/ViewPager$a;

    move-result-object v4

    .line 2514
    if-eqz v4, :cond_1

    iget v4, v4, Lmiui/view/ViewPager$a;->position:I

    iget v5, p0, Lmiui/view/ViewPager;->Co:I

    if-ne v4, v5, :cond_1

    invoke-virtual {v3, p1}, Landroid/view/View;->dispatchPopulateAccessibilityEvent(Landroid/view/accessibility/AccessibilityEvent;)Z

    move-result v3

    if-eqz v3, :cond_1

    .line 2516
    const/4 v0, 0x1

    .line 2521
    :cond_0
    return v0

    .line 2510
    :cond_1
    add-int/lit8 v1, v1, 0x1

    goto :goto_0
.end method

.method public draw(Landroid/graphics/Canvas;)V
    .locals 7

    .prologue
    const/4 v2, 0x1

    .line 1995
    invoke-super {p0, p1}, Landroid/view/ViewGroup;->draw(Landroid/graphics/Canvas;)V

    .line 1996
    const/4 v0, 0x0

    .line 1998
    invoke-virtual {p0}, Lmiui/view/ViewPager;->getOverScrollMode()I

    move-result v1

    .line 1999
    if-eqz v1, :cond_0

    if-ne v1, v2, :cond_4

    iget-object v1, p0, Lmiui/view/ViewPager;->Cn:Lmiui/view/PagerAdapter;

    if-eqz v1, :cond_4

    iget-object v1, p0, Lmiui/view/ViewPager;->Cn:Lmiui/view/PagerAdapter;

    invoke-virtual {v1}, Lmiui/view/PagerAdapter;->getCount()I

    move-result v1

    if-le v1, v2, :cond_4

    .line 2002
    :cond_0
    iget-object v1, p0, Lmiui/view/ViewPager;->Dg:Landroid/widget/EdgeEffect;

    invoke-virtual {v1}, Landroid/widget/EdgeEffect;->isFinished()Z

    move-result v1

    if-nez v1, :cond_1

    .line 2003
    invoke-virtual {p1}, Landroid/graphics/Canvas;->save()I

    move-result v1

    .line 2004
    invoke-virtual {p0}, Lmiui/view/ViewPager;->getHeight()I

    move-result v0

    invoke-virtual {p0}, Lmiui/view/ViewPager;->getPaddingTop()I

    move-result v2

    sub-int/2addr v0, v2

    invoke-virtual {p0}, Lmiui/view/ViewPager;->getPaddingBottom()I

    move-result v2

    sub-int/2addr v0, v2

    .line 2005
    invoke-virtual {p0}, Lmiui/view/ViewPager;->getWidth()I

    move-result v2

    .line 2007
    const/high16 v3, 0x43870000

    invoke-virtual {p1, v3}, Landroid/graphics/Canvas;->rotate(F)V

    .line 2008
    neg-int v3, v0

    invoke-virtual {p0}, Lmiui/view/ViewPager;->getPaddingTop()I

    move-result v4

    add-int/2addr v3, v4

    int-to-float v3, v3

    iget v4, p0, Lmiui/view/ViewPager;->Cw:F

    int-to-float v5, v2

    mul-float/2addr v4, v5

    invoke-virtual {p1, v3, v4}, Landroid/graphics/Canvas;->translate(FF)V

    .line 2009
    iget-object v3, p0, Lmiui/view/ViewPager;->Dg:Landroid/widget/EdgeEffect;

    invoke-virtual {v3, v0, v2}, Landroid/widget/EdgeEffect;->setSize(II)V

    .line 2010
    iget-object v0, p0, Lmiui/view/ViewPager;->Dg:Landroid/widget/EdgeEffect;

    invoke-virtual {v0, p1}, Landroid/widget/EdgeEffect;->draw(Landroid/graphics/Canvas;)Z

    move-result v0

    .line 2011
    invoke-virtual {p1, v1}, Landroid/graphics/Canvas;->restoreToCount(I)V

    .line 2014
    :cond_1
    iget-object v1, p0, Lmiui/view/ViewPager;->Dh:Landroid/widget/EdgeEffect;

    invoke-virtual {v1}, Landroid/widget/EdgeEffect;->isFinished()Z

    move-result v1

    if-nez v1, :cond_2

    .line 2015
    invoke-virtual {p1}, Landroid/graphics/Canvas;->save()I

    move-result v1

    .line 2016
    invoke-virtual {p0}, Lmiui/view/ViewPager;->getWidth()I

    move-result v2

    .line 2017
    invoke-virtual {p0}, Lmiui/view/ViewPager;->getHeight()I

    move-result v3

    invoke-virtual {p0}, Lmiui/view/ViewPager;->getPaddingTop()I

    move-result v4

    sub-int/2addr v3, v4

    invoke-virtual {p0}, Lmiui/view/ViewPager;->getPaddingBottom()I

    move-result v4

    sub-int/2addr v3, v4

    .line 2019
    const/high16 v4, 0x42b40000

    invoke-virtual {p1, v4}, Landroid/graphics/Canvas;->rotate(F)V

    .line 2020
    invoke-virtual {p0}, Lmiui/view/ViewPager;->getPaddingTop()I

    move-result v4

    neg-int v4, v4

    int-to-float v4, v4

    iget v5, p0, Lmiui/view/ViewPager;->Cx:F

    const/high16 v6, 0x3f800000

    add-float/2addr v5, v6

    neg-float v5, v5

    int-to-float v6, v2

    mul-float/2addr v5, v6

    invoke-virtual {p1, v4, v5}, Landroid/graphics/Canvas;->translate(FF)V

    .line 2021
    iget-object v4, p0, Lmiui/view/ViewPager;->Dh:Landroid/widget/EdgeEffect;

    invoke-virtual {v4, v3, v2}, Landroid/widget/EdgeEffect;->setSize(II)V

    .line 2022
    iget-object v2, p0, Lmiui/view/ViewPager;->Dh:Landroid/widget/EdgeEffect;

    invoke-virtual {v2, p1}, Landroid/widget/EdgeEffect;->draw(Landroid/graphics/Canvas;)Z

    move-result v2

    or-int/2addr v0, v2

    .line 2023
    invoke-virtual {p1, v1}, Landroid/graphics/Canvas;->restoreToCount(I)V

    .line 2030
    :cond_2
    :goto_0
    if-eqz v0, :cond_3

    .line 2032
    invoke-virtual {p0}, Lmiui/view/ViewPager;->postInvalidateOnAnimation()V

    .line 2034
    :cond_3
    return-void

    .line 2026
    :cond_4
    iget-object v1, p0, Lmiui/view/ViewPager;->Dg:Landroid/widget/EdgeEffect;

    invoke-virtual {v1}, Landroid/widget/EdgeEffect;->finish()V

    .line 2027
    iget-object v1, p0, Lmiui/view/ViewPager;->Dh:Landroid/widget/EdgeEffect;

    invoke-virtual {v1}, Landroid/widget/EdgeEffect;->finish()V

    goto :goto_0
.end method

.method protected drawableStateChanged()V
    .locals 2

    .prologue
    .line 611
    invoke-super {p0}, Landroid/view/ViewGroup;->drawableStateChanged()V

    .line 612
    iget-object v0, p0, Lmiui/view/ViewPager;->Ct:Landroid/graphics/drawable/Drawable;

    .line 613
    if-eqz v0, :cond_0

    invoke-virtual {v0}, Landroid/graphics/drawable/Drawable;->isStateful()Z

    move-result v1

    if-eqz v1, :cond_0

    .line 614
    invoke-virtual {p0}, Lmiui/view/ViewPager;->getDrawableState()[I

    move-result-object v1

    invoke-virtual {v0, v1}, Landroid/graphics/drawable/Drawable;->setState([I)Z

    .line 616
    :cond_0
    return-void
.end method

.method public endFakeDrag()V
    .locals 6

    .prologue
    const/4 v5, 0x1

    .line 2124
    iget-boolean v0, p0, Lmiui/view/ViewPager;->De:Z

    if-nez v0, :cond_0

    .line 2125
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "No fake drag in progress. Call beginFakeDrag first."

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 2128
    :cond_0
    iget-object v0, p0, Lmiui/view/ViewPager;->bR:Landroid/view/VelocityTracker;

    .line 2129
    const/16 v1, 0x3e8

    iget v2, p0, Lmiui/view/ViewPager;->cU:I

    int-to-float v2, v2

    invoke-virtual {v0, v1, v2}, Landroid/view/VelocityTracker;->computeCurrentVelocity(IF)V

    .line 2130
    iget v1, p0, Lmiui/view/ViewPager;->cV:I

    invoke-virtual {v0, v1}, Landroid/view/VelocityTracker;->getXVelocity(I)F

    move-result v0

    float-to-int v0, v0

    .line 2131
    iput-boolean v5, p0, Lmiui/view/ViewPager;->CC:Z

    .line 2132
    invoke-virtual {p0}, Lmiui/view/ViewPager;->getWidth()I

    move-result v1

    .line 2133
    invoke-virtual {p0}, Lmiui/view/ViewPager;->getScrollX()I

    move-result v2

    .line 2134
    invoke-direct {p0}, Lmiui/view/ViewPager;->cU()Lmiui/view/ViewPager$a;

    move-result-object v3

    .line 2135
    iget v4, v3, Lmiui/view/ViewPager$a;->position:I

    .line 2136
    int-to-float v2, v2

    int-to-float v1, v1

    div-float v1, v2, v1

    iget v2, v3, Lmiui/view/ViewPager$a;->lg:F

    sub-float/2addr v1, v2

    iget v2, v3, Lmiui/view/ViewPager$a;->widthFactor:F

    div-float/2addr v1, v2

    .line 2137
    iget v2, p0, Lmiui/view/ViewPager;->mLastMotionX:F

    iget v3, p0, Lmiui/view/ViewPager;->nT:F

    sub-float/2addr v2, v3

    float-to-int v2, v2

    .line 2138
    invoke-direct {p0, v4, v1, v0, v2}, Lmiui/view/ViewPager;->a(IFII)I

    move-result v1

    .line 2140
    invoke-virtual {p0, v1, v5, v5, v0}, Lmiui/view/ViewPager;->a(IZZI)V

    .line 2141
    invoke-direct {p0}, Lmiui/view/ViewPager;->cV()V

    .line 2143
    const/4 v0, 0x0

    iput-boolean v0, p0, Lmiui/view/ViewPager;->De:Z

    .line 2144
    return-void
.end method

.method public executeKeyEvent(Landroid/view/KeyEvent;)Z
    .locals 4

    .prologue
    const/4 v3, 0x1

    .line 2296
    const/4 v0, 0x0

    .line 2297
    invoke-virtual {p1}, Landroid/view/KeyEvent;->getAction()I

    move-result v1

    if-nez v1, :cond_0

    .line 2298
    invoke-virtual {p1}, Landroid/view/KeyEvent;->getKeyCode()I

    move-result v1

    sparse-switch v1, :sswitch_data_0

    .line 2318
    :cond_0
    :goto_0
    return v0

    .line 2300
    :sswitch_0
    const/16 v0, 0x11

    invoke-virtual {p0, v0}, Lmiui/view/ViewPager;->arrowScroll(I)Z

    move-result v0

    goto :goto_0

    .line 2303
    :sswitch_1
    const/16 v0, 0x42

    invoke-virtual {p0, v0}, Lmiui/view/ViewPager;->arrowScroll(I)Z

    move-result v0

    goto :goto_0

    .line 2306
    :sswitch_2
    sget v1, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v2, 0xb

    if-lt v1, v2, :cond_0

    .line 2309
    invoke-virtual {p1}, Landroid/view/KeyEvent;->hasNoModifiers()Z

    move-result v1

    if-eqz v1, :cond_1

    .line 2310
    const/4 v0, 0x2

    invoke-virtual {p0, v0}, Lmiui/view/ViewPager;->arrowScroll(I)Z

    move-result v0

    goto :goto_0

    .line 2311
    :cond_1
    invoke-virtual {p1, v3}, Landroid/view/KeyEvent;->hasModifiers(I)Z

    move-result v1

    if-eqz v1, :cond_0

    .line 2312
    invoke-virtual {p0, v3}, Lmiui/view/ViewPager;->arrowScroll(I)Z

    move-result v0

    goto :goto_0

    .line 2298
    nop

    :sswitch_data_0
    .sparse-switch
        0x15 -> :sswitch_0
        0x16 -> :sswitch_1
        0x3d -> :sswitch_2
    .end sparse-switch
.end method

.method public fakeDragBy(F)V
    .locals 8

    .prologue
    const/4 v7, 0x0

    .line 2154
    iget-boolean v0, p0, Lmiui/view/ViewPager;->De:Z

    if-nez v0, :cond_0

    .line 2155
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "No fake drag in progress. Call beginFakeDrag first."

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 2158
    :cond_0
    iget v0, p0, Lmiui/view/ViewPager;->mLastMotionX:F

    add-float/2addr v0, p1

    iput v0, p0, Lmiui/view/ViewPager;->mLastMotionX:F

    .line 2160
    invoke-virtual {p0}, Lmiui/view/ViewPager;->getScrollX()I

    move-result v0

    int-to-float v0, v0

    .line 2161
    sub-float v3, v0, p1

    .line 2162
    invoke-virtual {p0}, Lmiui/view/ViewPager;->getWidth()I

    move-result v5

    .line 2164
    int-to-float v0, v5

    iget v1, p0, Lmiui/view/ViewPager;->Cw:F

    mul-float v2, v0, v1

    .line 2165
    int-to-float v0, v5

    iget v1, p0, Lmiui/view/ViewPager;->Cx:F

    mul-float v4, v0, v1

    .line 2167
    iget-object v0, p0, Lmiui/view/ViewPager;->N:Ljava/util/ArrayList;

    invoke-virtual {v0, v7}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/view/ViewPager$a;

    .line 2168
    iget-object v1, p0, Lmiui/view/ViewPager;->N:Ljava/util/ArrayList;

    iget-object v6, p0, Lmiui/view/ViewPager;->N:Ljava/util/ArrayList;

    invoke-virtual {v6}, Ljava/util/ArrayList;->size()I

    move-result v6

    add-int/lit8 v6, v6, -0x1

    invoke-virtual {v1, v6}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lmiui/view/ViewPager$a;

    .line 2169
    iget v6, v0, Lmiui/view/ViewPager$a;->position:I

    if-eqz v6, :cond_4

    .line 2170
    iget v0, v0, Lmiui/view/ViewPager$a;->lg:F

    int-to-float v2, v5

    mul-float/2addr v0, v2

    .line 2172
    :goto_0
    iget v2, v1, Lmiui/view/ViewPager$a;->position:I

    iget-object v6, p0, Lmiui/view/ViewPager;->Cn:Lmiui/view/PagerAdapter;

    invoke-virtual {v6}, Lmiui/view/PagerAdapter;->getCount()I

    move-result v6

    add-int/lit8 v6, v6, -0x1

    if-eq v2, v6, :cond_3

    .line 2173
    iget v1, v1, Lmiui/view/ViewPager$a;->lg:F

    int-to-float v2, v5

    mul-float/2addr v1, v2

    .line 2176
    :goto_1
    cmpg-float v2, v3, v0

    if-gez v2, :cond_1

    .line 2182
    :goto_2
    iget v1, p0, Lmiui/view/ViewPager;->mLastMotionX:F

    float-to-int v2, v0

    int-to-float v2, v2

    sub-float v2, v0, v2

    add-float/2addr v1, v2

    iput v1, p0, Lmiui/view/ViewPager;->mLastMotionX:F

    .line 2183
    float-to-int v1, v0

    invoke-virtual {p0}, Lmiui/view/ViewPager;->getScrollY()I

    move-result v2

    invoke-virtual {p0, v1, v2}, Lmiui/view/ViewPager;->scrollTo(II)V

    .line 2184
    float-to-int v0, v0

    const/4 v1, 0x1

    invoke-direct {p0, v0, v1}, Lmiui/view/ViewPager;->f(IZ)Z

    .line 2187
    invoke-static {}, Landroid/os/SystemClock;->uptimeMillis()J

    move-result-wide v2

    .line 2188
    iget-wide v0, p0, Lmiui/view/ViewPager;->Df:J

    const/4 v4, 0x2

    iget v5, p0, Lmiui/view/ViewPager;->mLastMotionX:F

    const/4 v6, 0x0

    invoke-static/range {v0 .. v7}, Landroid/view/MotionEvent;->obtain(JJIFFI)Landroid/view/MotionEvent;

    move-result-object v0

    .line 2190
    iget-object v1, p0, Lmiui/view/ViewPager;->bR:Landroid/view/VelocityTracker;

    invoke-virtual {v1, v0}, Landroid/view/VelocityTracker;->addMovement(Landroid/view/MotionEvent;)V

    .line 2191
    invoke-virtual {v0}, Landroid/view/MotionEvent;->recycle()V

    .line 2192
    return-void

    .line 2178
    :cond_1
    cmpl-float v0, v3, v1

    if-lez v0, :cond_2

    move v0, v1

    .line 2179
    goto :goto_2

    :cond_2
    move v0, v3

    goto :goto_2

    :cond_3
    move v1, v4

    goto :goto_1

    :cond_4
    move v0, v2

    goto :goto_0
.end method

.method protected generateDefaultLayoutParams()Landroid/view/ViewGroup$LayoutParams;
    .locals 1

    .prologue
    .line 2526
    new-instance v0, Lmiui/view/ViewPager$LayoutParams;

    invoke-direct {v0}, Lmiui/view/ViewPager$LayoutParams;-><init>()V

    return-object v0
.end method

.method public generateLayoutParams(Landroid/util/AttributeSet;)Landroid/view/ViewGroup$LayoutParams;
    .locals 2

    .prologue
    .line 2541
    new-instance v0, Lmiui/view/ViewPager$LayoutParams;

    invoke-virtual {p0}, Lmiui/view/ViewPager;->getContext()Landroid/content/Context;

    move-result-object v1

    invoke-direct {v0, v1, p1}, Lmiui/view/ViewPager$LayoutParams;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    return-object v0
.end method

.method protected generateLayoutParams(Landroid/view/ViewGroup$LayoutParams;)Landroid/view/ViewGroup$LayoutParams;
    .locals 1

    .prologue
    .line 2531
    invoke-virtual {p0}, Lmiui/view/ViewPager;->generateDefaultLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v0

    return-object v0
.end method

.method public getAdapter()Lmiui/view/PagerAdapter;
    .locals 1

    .prologue
    .line 402
    iget-object v0, p0, Lmiui/view/ViewPager;->Cn:Lmiui/view/PagerAdapter;

    return-object v0
.end method

.method public getCurrentItem()I
    .locals 1

    .prologue
    .line 433
    iget v0, p0, Lmiui/view/ViewPager;->Co:I

    return v0
.end method

.method public getOffscreenPageLimit()I
    .locals 1

    .prologue
    .line 524
    iget v0, p0, Lmiui/view/ViewPager;->CE:I

    return v0
.end method

.method public getPageMargin()I
    .locals 1

    .prologue
    .line 580
    iget v0, p0, Lmiui/view/ViewPager;->Cs:I

    return v0
.end method

.method getSplitActionBarHeight()I
    .locals 2

    .prologue
    .line 2591
    invoke-static {p0}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->getActionBarOverlayLayout(Landroid/view/View;)Lcom/miui/internal/widget/ActionBarOverlayLayout;

    move-result-object v0

    .line 2592
    if-eqz v0, :cond_0

    invoke-virtual {v0}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->getActionBarView()Lcom/miui/internal/widget/ActionBarView;

    move-result-object v1

    if-eqz v1, :cond_0

    .line 2593
    invoke-virtual {v0}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->getActionBarView()Lcom/miui/internal/widget/ActionBarView;

    move-result-object v0

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Lcom/miui/internal/widget/ActionBarView;->getSplitActionBarHeight(Z)I

    move-result v0

    .line 2598
    :goto_0
    return v0

    .line 2595
    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method h(III)V
    .locals 9

    .prologue
    const/4 v5, 0x0

    const/high16 v8, 0x3f800000

    .line 646
    invoke-virtual {p0}, Lmiui/view/ViewPager;->getChildCount()I

    move-result v0

    if-nez v0, :cond_0

    .line 648
    invoke-direct {p0, v5}, Lmiui/view/ViewPager;->setScrollingCacheEnabled(Z)V

    .line 684
    :goto_0
    return-void

    .line 651
    :cond_0
    invoke-virtual {p0}, Lmiui/view/ViewPager;->getScrollX()I

    move-result v1

    .line 652
    invoke-virtual {p0}, Lmiui/view/ViewPager;->getScrollY()I

    move-result v2

    .line 653
    sub-int v3, p1, v1

    .line 654
    sub-int v4, p2, v2

    .line 655
    if-nez v3, :cond_1

    if-nez v4, :cond_1

    .line 656
    invoke-direct {p0}, Lmiui/view/ViewPager;->cT()V

    .line 657
    invoke-virtual {p0}, Lmiui/view/ViewPager;->cS()V

    .line 658
    invoke-direct {p0, v5}, Lmiui/view/ViewPager;->X(I)V

    goto :goto_0

    .line 662
    :cond_1
    const/4 v0, 0x1

    invoke-direct {p0, v0}, Lmiui/view/ViewPager;->setScrollingCacheEnabled(Z)V

    .line 663
    const/4 v0, 0x2

    invoke-direct {p0, v0}, Lmiui/view/ViewPager;->X(I)V

    .line 665
    invoke-virtual {p0}, Lmiui/view/ViewPager;->getWidth()I

    move-result v0

    .line 666
    div-int/lit8 v5, v0, 0x2

    .line 667
    invoke-static {v3}, Ljava/lang/Math;->abs(I)I

    move-result v6

    int-to-float v6, v6

    mul-float/2addr v6, v8

    int-to-float v7, v0

    div-float/2addr v6, v7

    invoke-static {v8, v6}, Ljava/lang/Math;->min(FF)F

    move-result v6

    .line 668
    int-to-float v7, v5

    int-to-float v5, v5

    invoke-virtual {p0, v6}, Lmiui/view/ViewPager;->d(F)F

    move-result v6

    mul-float/2addr v5, v6

    add-float/2addr v5, v7

    .line 672
    invoke-static {p3}, Ljava/lang/Math;->abs(I)I

    move-result v6

    .line 673
    if-lez v6, :cond_2

    .line 674
    const/high16 v0, 0x447a0000

    int-to-float v6, v6

    div-float/2addr v5, v6

    invoke-static {v5}, Ljava/lang/Math;->abs(F)F

    move-result v5

    mul-float/2addr v0, v5

    invoke-static {v0}, Ljava/lang/Math;->round(F)I

    move-result v0

    mul-int/lit8 v0, v0, 0x4

    .line 680
    :goto_1
    const/16 v5, 0x320

    invoke-static {v0, v5}, Ljava/lang/Math;->min(II)I

    move-result v5

    .line 682
    iget-object v0, p0, Lmiui/view/ViewPager;->cL:Landroid/widget/Scroller;

    invoke-virtual/range {v0 .. v5}, Landroid/widget/Scroller;->startScroll(IIIII)V

    .line 683
    invoke-virtual {p0}, Lmiui/view/ViewPager;->postInvalidateOnAnimation()V

    goto :goto_0

    .line 676
    :cond_2
    int-to-float v0, v0

    iget-object v5, p0, Lmiui/view/ViewPager;->Cn:Lmiui/view/PagerAdapter;

    iget v6, p0, Lmiui/view/ViewPager;->Co:I

    invoke-virtual {v5, v6}, Lmiui/view/PagerAdapter;->getPageWidth(I)F

    move-result v5

    mul-float/2addr v0, v5

    .line 677
    invoke-static {v3}, Ljava/lang/Math;->abs(I)I

    move-result v5

    int-to-float v5, v5

    iget v6, p0, Lmiui/view/ViewPager;->Cs:I

    int-to-float v6, v6

    add-float/2addr v0, v6

    div-float v0, v5, v0

    .line 678
    add-float/2addr v0, v8

    const/high16 v5, 0x437a0000

    mul-float/2addr v0, v5

    float-to-int v0, v0

    goto :goto_1
.end method

.method i(Landroid/view/View;)Lmiui/view/ViewPager$a;
    .locals 4

    .prologue
    .line 1135
    iget-object v0, p0, Lmiui/view/ViewPager;->N:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :cond_0
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_1

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/view/ViewPager$a;

    .line 1136
    iget-object v2, p0, Lmiui/view/ViewPager;->Cn:Lmiui/view/PagerAdapter;

    iget-object v3, v0, Lmiui/view/ViewPager$a;->le:Ljava/lang/Object;

    invoke-virtual {v2, p1, v3}, Lmiui/view/PagerAdapter;->isViewFromObject(Landroid/view/View;Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_0

    .line 1140
    :goto_0
    return-object v0

    :cond_1
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public isFakeDragging()Z
    .locals 1

    .prologue
    .line 2204
    iget-boolean v0, p0, Lmiui/view/ViewPager;->De:Z

    return v0
.end method

.method j(II)Lmiui/view/ViewPager$a;
    .locals 2

    .prologue
    .line 687
    new-instance v0, Lmiui/view/ViewPager$a;

    invoke-direct {v0}, Lmiui/view/ViewPager$a;-><init>()V

    .line 688
    iput p1, v0, Lmiui/view/ViewPager$a;->position:I

    .line 689
    iget-object v1, p0, Lmiui/view/ViewPager;->Cn:Lmiui/view/PagerAdapter;

    invoke-virtual {v1, p0, p1}, Lmiui/view/PagerAdapter;->instantiateItem(Landroid/view/ViewGroup;I)Ljava/lang/Object;

    move-result-object v1

    iput-object v1, v0, Lmiui/view/ViewPager$a;->le:Ljava/lang/Object;

    .line 690
    iget-object v1, p0, Lmiui/view/ViewPager;->Cn:Lmiui/view/PagerAdapter;

    invoke-virtual {v1, p1}, Lmiui/view/PagerAdapter;->getPageWidth(I)F

    move-result v1

    iput v1, v0, Lmiui/view/ViewPager$a;->widthFactor:F

    .line 691
    iget-object v1, p0, Lmiui/view/ViewPager;->Cn:Lmiui/view/PagerAdapter;

    invoke-virtual {v1, p1}, Lmiui/view/PagerAdapter;->hasActionMenu(I)Z

    move-result v1

    iput-boolean v1, v0, Lmiui/view/ViewPager$a;->lh:Z

    .line 692
    if-ltz p2, :cond_0

    iget-object v1, p0, Lmiui/view/ViewPager;->N:Ljava/util/ArrayList;

    invoke-virtual {v1}, Ljava/util/ArrayList;->size()I

    move-result v1

    if-lt p2, v1, :cond_1

    .line 693
    :cond_0
    iget-object v1, p0, Lmiui/view/ViewPager;->N:Ljava/util/ArrayList;

    invoke-virtual {v1, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 697
    :goto_0
    return-object v0

    .line 695
    :cond_1
    iget-object v1, p0, Lmiui/view/ViewPager;->N:Ljava/util/ArrayList;

    invoke-virtual {v1, p2, v0}, Ljava/util/ArrayList;->add(ILjava/lang/Object;)V

    goto :goto_0
.end method

.method j(Landroid/view/View;)Lmiui/view/ViewPager$a;
    .locals 2

    .prologue
    .line 1145
    :goto_0
    invoke-virtual {p1}, Landroid/view/View;->getParent()Landroid/view/ViewParent;

    move-result-object v0

    if-eq v0, p0, :cond_1

    .line 1146
    instance-of v1, v0, Landroid/view/View;

    if-nez v1, :cond_0

    .line 1147
    const/4 v0, 0x0

    .line 1151
    :goto_1
    return-object v0

    .line 1149
    :cond_0
    check-cast v0, Landroid/view/View;

    move-object p1, v0

    goto :goto_0

    .line 1151
    :cond_1
    invoke-virtual {p0, p1}, Lmiui/view/ViewPager;->i(Landroid/view/View;)Lmiui/view/ViewPager$a;

    move-result-object v0

    goto :goto_1
.end method

.method protected onAttachedToWindow()V
    .locals 1

    .prologue
    .line 1165
    invoke-super {p0}, Landroid/view/ViewGroup;->onAttachedToWindow()V

    .line 1166
    const/4 v0, 0x1

    iput-boolean v0, p0, Lmiui/view/ViewPager;->cm:Z

    .line 1167
    return-void
.end method

.method protected onDraw(Landroid/graphics/Canvas;)V
    .locals 16

    .prologue
    .line 2038
    invoke-super/range {p0 .. p1}, Landroid/view/ViewGroup;->onDraw(Landroid/graphics/Canvas;)V

    .line 2041
    move-object/from16 v0, p0

    iget v1, v0, Lmiui/view/ViewPager;->Cs:I

    if-lez v1, :cond_2

    move-object/from16 v0, p0

    iget-object v1, v0, Lmiui/view/ViewPager;->Ct:Landroid/graphics/drawable/Drawable;

    if-eqz v1, :cond_2

    move-object/from16 v0, p0

    iget-object v1, v0, Lmiui/view/ViewPager;->N:Ljava/util/ArrayList;

    invoke-virtual {v1}, Ljava/util/ArrayList;->size()I

    move-result v1

    if-lez v1, :cond_2

    move-object/from16 v0, p0

    iget-object v1, v0, Lmiui/view/ViewPager;->Cn:Lmiui/view/PagerAdapter;

    if-eqz v1, :cond_2

    .line 2042
    invoke-virtual/range {p0 .. p0}, Lmiui/view/ViewPager;->getScrollX()I

    move-result v6

    .line 2043
    invoke-virtual/range {p0 .. p0}, Lmiui/view/ViewPager;->getWidth()I

    move-result v7

    .line 2045
    move-object/from16 v0, p0

    iget v1, v0, Lmiui/view/ViewPager;->Cs:I

    int-to-float v1, v1

    int-to-float v2, v7

    div-float v8, v1, v2

    .line 2046
    const/4 v5, 0x0

    .line 2047
    move-object/from16 v0, p0

    iget-object v1, v0, Lmiui/view/ViewPager;->N:Ljava/util/ArrayList;

    const/4 v2, 0x0

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lmiui/view/ViewPager$a;

    .line 2048
    iget v4, v1, Lmiui/view/ViewPager$a;->lg:F

    .line 2049
    move-object/from16 v0, p0

    iget-object v2, v0, Lmiui/view/ViewPager;->N:Ljava/util/ArrayList;

    invoke-virtual {v2}, Ljava/util/ArrayList;->size()I

    move-result v9

    .line 2050
    iget v3, v1, Lmiui/view/ViewPager$a;->position:I

    .line 2051
    move-object/from16 v0, p0

    iget-object v2, v0, Lmiui/view/ViewPager;->N:Ljava/util/ArrayList;

    add-int/lit8 v10, v9, -0x1

    invoke-virtual {v2, v10}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lmiui/view/ViewPager$a;

    iget v10, v2, Lmiui/view/ViewPager$a;->position:I

    move v2, v5

    move v5, v3

    .line 2052
    :goto_0
    if-ge v5, v10, :cond_2

    .line 2053
    :goto_1
    iget v3, v1, Lmiui/view/ViewPager$a;->position:I

    if-le v5, v3, :cond_0

    if-ge v2, v9, :cond_0

    .line 2054
    move-object/from16 v0, p0

    iget-object v1, v0, Lmiui/view/ViewPager;->N:Ljava/util/ArrayList;

    add-int/lit8 v2, v2, 0x1

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lmiui/view/ViewPager$a;

    goto :goto_1

    .line 2058
    :cond_0
    iget v3, v1, Lmiui/view/ViewPager$a;->position:I

    if-ne v5, v3, :cond_3

    .line 2059
    iget v3, v1, Lmiui/view/ViewPager$a;->lg:F

    iget v4, v1, Lmiui/view/ViewPager$a;->widthFactor:F

    add-float/2addr v3, v4

    int-to-float v4, v7

    mul-float/2addr v3, v4

    .line 2060
    iget v4, v1, Lmiui/view/ViewPager$a;->lg:F

    iget v11, v1, Lmiui/view/ViewPager$a;->widthFactor:F

    add-float/2addr v4, v11

    add-float/2addr v4, v8

    .line 2067
    :goto_2
    move-object/from16 v0, p0

    iget v11, v0, Lmiui/view/ViewPager;->Cs:I

    int-to-float v11, v11

    add-float/2addr v11, v3

    int-to-float v12, v6

    cmpl-float v11, v11, v12

    if-lez v11, :cond_1

    .line 2068
    move-object/from16 v0, p0

    iget-object v11, v0, Lmiui/view/ViewPager;->Ct:Landroid/graphics/drawable/Drawable;

    float-to-int v12, v3

    move-object/from16 v0, p0

    iget v13, v0, Lmiui/view/ViewPager;->Cu:I

    move-object/from16 v0, p0

    iget v14, v0, Lmiui/view/ViewPager;->Cs:I

    int-to-float v14, v14

    add-float/2addr v14, v3

    const/high16 v15, 0x3f000000

    add-float/2addr v14, v15

    float-to-int v14, v14

    move-object/from16 v0, p0

    iget v15, v0, Lmiui/view/ViewPager;->Cv:I

    invoke-virtual {v11, v12, v13, v14, v15}, Landroid/graphics/drawable/Drawable;->setBounds(IIII)V

    .line 2070
    move-object/from16 v0, p0

    iget-object v11, v0, Lmiui/view/ViewPager;->Ct:Landroid/graphics/drawable/Drawable;

    move-object/from16 v0, p1

    invoke-virtual {v11, v0}, Landroid/graphics/drawable/Drawable;->draw(Landroid/graphics/Canvas;)V

    .line 2073
    :cond_1
    add-int v11, v6, v7

    int-to-float v11, v11

    cmpl-float v3, v3, v11

    if-lez v3, :cond_4

    .line 2078
    :cond_2
    return-void

    .line 2062
    :cond_3
    move-object/from16 v0, p0

    iget-object v3, v0, Lmiui/view/ViewPager;->Cn:Lmiui/view/PagerAdapter;

    invoke-virtual {v3, v5}, Lmiui/view/PagerAdapter;->getPageWidth(I)F

    move-result v11

    .line 2063
    add-float v3, v4, v11

    int-to-float v12, v7

    mul-float/2addr v3, v12

    .line 2064
    add-float/2addr v11, v8

    add-float/2addr v4, v11

    goto :goto_2

    .line 2052
    :cond_4
    add-int/lit8 v3, v5, 0x1

    move v5, v3

    goto :goto_0
.end method

.method public onInitializeAccessibilityEvent(Landroid/view/accessibility/AccessibilityEvent;)V
    .locals 1

    .prologue
    .line 2546
    invoke-super {p0, p1}, Landroid/view/ViewGroup;->onInitializeAccessibilityEvent(Landroid/view/accessibility/AccessibilityEvent;)V

    .line 2547
    const-class v0, Lmiui/view/ViewPager;

    invoke-virtual {v0}, Ljava/lang/Class;->getName()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p1, v0}, Landroid/view/accessibility/AccessibilityEvent;->setClassName(Ljava/lang/CharSequence;)V

    .line 2548
    return-void
.end method

.method public onInitializeAccessibilityNodeInfo(Landroid/view/accessibility/AccessibilityNodeInfo;)V
    .locals 2

    .prologue
    const/4 v0, 0x1

    .line 2552
    invoke-super {p0, p1}, Landroid/view/ViewGroup;->onInitializeAccessibilityNodeInfo(Landroid/view/accessibility/AccessibilityNodeInfo;)V

    .line 2553
    const-class v1, Lmiui/view/ViewPager;

    invoke-virtual {v1}, Ljava/lang/Class;->getName()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {p1, v1}, Landroid/view/accessibility/AccessibilityNodeInfo;->setClassName(Ljava/lang/CharSequence;)V

    .line 2554
    iget-object v1, p0, Lmiui/view/ViewPager;->Cn:Lmiui/view/PagerAdapter;

    if-eqz v1, :cond_2

    iget-object v1, p0, Lmiui/view/ViewPager;->Cn:Lmiui/view/PagerAdapter;

    invoke-virtual {v1}, Lmiui/view/PagerAdapter;->getCount()I

    move-result v1

    if-le v1, v0, :cond_2

    :goto_0
    invoke-virtual {p1, v0}, Landroid/view/accessibility/AccessibilityNodeInfo;->setScrollable(Z)V

    .line 2555
    iget-object v0, p0, Lmiui/view/ViewPager;->Cn:Lmiui/view/PagerAdapter;

    if-eqz v0, :cond_0

    iget v0, p0, Lmiui/view/ViewPager;->Co:I

    if-ltz v0, :cond_0

    iget v0, p0, Lmiui/view/ViewPager;->Co:I

    iget-object v1, p0, Lmiui/view/ViewPager;->Cn:Lmiui/view/PagerAdapter;

    invoke-virtual {v1}, Lmiui/view/PagerAdapter;->getCount()I

    move-result v1

    add-int/lit8 v1, v1, -0x1

    if-ge v0, v1, :cond_0

    .line 2556
    const/16 v0, 0x1000

    invoke-virtual {p1, v0}, Landroid/view/accessibility/AccessibilityNodeInfo;->addAction(I)V

    .line 2558
    :cond_0
    iget-object v0, p0, Lmiui/view/ViewPager;->Cn:Lmiui/view/PagerAdapter;

    if-eqz v0, :cond_1

    iget v0, p0, Lmiui/view/ViewPager;->Co:I

    if-lez v0, :cond_1

    iget v0, p0, Lmiui/view/ViewPager;->Co:I

    iget-object v1, p0, Lmiui/view/ViewPager;->Cn:Lmiui/view/PagerAdapter;

    invoke-virtual {v1}, Lmiui/view/PagerAdapter;->getCount()I

    move-result v1

    if-ge v0, v1, :cond_1

    .line 2559
    const/16 v0, 0x2000

    invoke-virtual {p1, v0}, Landroid/view/accessibility/AccessibilityNodeInfo;->addAction(I)V

    .line 2561
    :cond_1
    return-void

    .line 2554
    :cond_2
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public onInterceptTouchEvent(Landroid/view/MotionEvent;)Z
    .locals 13

    .prologue
    const/4 v3, -0x1

    const/4 v12, 0x0

    const/4 v6, 0x1

    const/4 v2, 0x0

    .line 1610
    iget-boolean v0, p0, Lmiui/view/ViewPager;->Do:Z

    if-nez v0, :cond_1

    .line 1758
    :cond_0
    :goto_0
    return v2

    .line 1620
    :cond_1
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getAction()I

    move-result v0

    and-int/lit16 v0, v0, 0xff

    .line 1623
    const/4 v1, 0x3

    if-eq v0, v1, :cond_2

    if-ne v0, v6, :cond_3

    .line 1626
    :cond_2
    iput-boolean v2, p0, Lmiui/view/ViewPager;->CJ:Z

    .line 1627
    iput-boolean v2, p0, Lmiui/view/ViewPager;->CP:Z

    .line 1628
    iput v3, p0, Lmiui/view/ViewPager;->cV:I

    .line 1629
    iget-object v0, p0, Lmiui/view/ViewPager;->bR:Landroid/view/VelocityTracker;

    if-eqz v0, :cond_0

    .line 1630
    iget-object v0, p0, Lmiui/view/ViewPager;->bR:Landroid/view/VelocityTracker;

    invoke-virtual {v0}, Landroid/view/VelocityTracker;->recycle()V

    .line 1631
    const/4 v0, 0x0

    iput-object v0, p0, Lmiui/view/ViewPager;->bR:Landroid/view/VelocityTracker;

    goto :goto_0

    .line 1638
    :cond_3
    if-eqz v0, :cond_5

    .line 1639
    iget-boolean v1, p0, Lmiui/view/ViewPager;->CJ:Z

    if-eqz v1, :cond_4

    move v2, v6

    .line 1641
    goto :goto_0

    .line 1643
    :cond_4
    iget-boolean v1, p0, Lmiui/view/ViewPager;->CP:Z

    if-nez v1, :cond_0

    .line 1649
    :cond_5
    sparse-switch v0, :sswitch_data_0

    .line 1749
    :cond_6
    :goto_1
    iget-object v0, p0, Lmiui/view/ViewPager;->bR:Landroid/view/VelocityTracker;

    if-nez v0, :cond_7

    .line 1750
    invoke-static {}, Landroid/view/VelocityTracker;->obtain()Landroid/view/VelocityTracker;

    move-result-object v0

    iput-object v0, p0, Lmiui/view/ViewPager;->bR:Landroid/view/VelocityTracker;

    .line 1752
    :cond_7
    iget-object v0, p0, Lmiui/view/ViewPager;->bR:Landroid/view/VelocityTracker;

    invoke-virtual {v0, p1}, Landroid/view/VelocityTracker;->addMovement(Landroid/view/MotionEvent;)V

    .line 1758
    iget-boolean v2, p0, Lmiui/view/ViewPager;->CJ:Z

    goto :goto_0

    .line 1660
    :sswitch_0
    iget v0, p0, Lmiui/view/ViewPager;->cV:I

    .line 1661
    if-eq v0, v3, :cond_6

    .line 1666
    invoke-virtual {p1, v0}, Landroid/view/MotionEvent;->findPointerIndex(I)I

    move-result v0

    .line 1669
    if-ltz v0, :cond_6

    .line 1673
    invoke-virtual {p1, v0}, Landroid/view/MotionEvent;->getX(I)F

    move-result v7

    .line 1674
    iget v1, p0, Lmiui/view/ViewPager;->mLastMotionX:F

    sub-float v8, v7, v1

    .line 1675
    invoke-static {v8}, Ljava/lang/Math;->abs(F)F

    move-result v9

    .line 1676
    invoke-virtual {p1, v0}, Landroid/view/MotionEvent;->getY(I)F

    move-result v10

    .line 1677
    iget v0, p0, Lmiui/view/ViewPager;->mLastMotionY:F

    sub-float v0, v10, v0

    invoke-static {v0}, Ljava/lang/Math;->abs(F)F

    move-result v11

    .line 1680
    cmpl-float v0, v8, v12

    if-eqz v0, :cond_8

    iget v0, p0, Lmiui/view/ViewPager;->mLastMotionX:F

    invoke-direct {p0, v0, v8}, Lmiui/view/ViewPager;->c(FF)Z

    move-result v0

    if-nez v0, :cond_8

    float-to-int v3, v8

    float-to-int v4, v7

    float-to-int v5, v10

    move-object v0, p0

    move-object v1, p0

    invoke-virtual/range {v0 .. v5}, Lmiui/view/ViewPager;->canScroll(Landroid/view/View;ZIII)Z

    move-result v0

    if-eqz v0, :cond_8

    .line 1683
    iput v7, p0, Lmiui/view/ViewPager;->mLastMotionX:F

    iput v7, p0, Lmiui/view/ViewPager;->nT:F

    .line 1684
    iput v10, p0, Lmiui/view/ViewPager;->mLastMotionY:F

    .line 1685
    iput-boolean v6, p0, Lmiui/view/ViewPager;->CP:Z

    goto/16 :goto_0

    .line 1688
    :cond_8
    iget v0, p0, Lmiui/view/ViewPager;->cT:I

    int-to-float v0, v0

    cmpl-float v0, v9, v0

    if-lez v0, :cond_b

    cmpl-float v0, v9, v11

    if-lez v0, :cond_b

    .line 1690
    iput-boolean v6, p0, Lmiui/view/ViewPager;->CJ:Z

    .line 1691
    invoke-direct {p0, v6}, Lmiui/view/ViewPager;->X(I)V

    .line 1692
    cmpl-float v0, v8, v12

    if-lez v0, :cond_a

    iget v0, p0, Lmiui/view/ViewPager;->nT:F

    iget v1, p0, Lmiui/view/ViewPager;->cT:I

    int-to-float v1, v1

    add-float/2addr v0, v1

    :goto_2
    iput v0, p0, Lmiui/view/ViewPager;->mLastMotionX:F

    .line 1694
    invoke-direct {p0, v6}, Lmiui/view/ViewPager;->setScrollingCacheEnabled(Z)V

    .line 1705
    :cond_9
    :goto_3
    iget-boolean v0, p0, Lmiui/view/ViewPager;->CJ:Z

    if-eqz v0, :cond_6

    .line 1707
    invoke-direct {p0, v7}, Lmiui/view/ViewPager;->e(F)Z

    move-result v0

    if-eqz v0, :cond_6

    .line 1708
    invoke-virtual {p0}, Lmiui/view/ViewPager;->postInvalidateOnAnimation()V

    goto/16 :goto_1

    .line 1692
    :cond_a
    iget v0, p0, Lmiui/view/ViewPager;->nT:F

    iget v1, p0, Lmiui/view/ViewPager;->cT:I

    int-to-float v1, v1

    sub-float/2addr v0, v1

    goto :goto_2

    .line 1696
    :cond_b
    iget v0, p0, Lmiui/view/ViewPager;->cT:I

    int-to-float v0, v0

    cmpl-float v0, v11, v0

    if-lez v0, :cond_9

    .line 1702
    iput-boolean v6, p0, Lmiui/view/ViewPager;->CP:Z

    goto :goto_3

    .line 1719
    :sswitch_1
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getX()F

    move-result v0

    iput v0, p0, Lmiui/view/ViewPager;->nT:F

    iput v0, p0, Lmiui/view/ViewPager;->mLastMotionX:F

    .line 1720
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getY()F

    move-result v0

    iput v0, p0, Lmiui/view/ViewPager;->mLastMotionY:F

    .line 1721
    invoke-virtual {p1, v2}, Landroid/view/MotionEvent;->getPointerId(I)I

    move-result v0

    iput v0, p0, Lmiui/view/ViewPager;->cV:I

    .line 1722
    iput-boolean v2, p0, Lmiui/view/ViewPager;->CP:Z

    .line 1724
    iget-object v0, p0, Lmiui/view/ViewPager;->cL:Landroid/widget/Scroller;

    invoke-virtual {v0}, Landroid/widget/Scroller;->computeScrollOffset()Z

    .line 1725
    iget v0, p0, Lmiui/view/ViewPager;->zY:I

    const/4 v1, 0x2

    if-ne v0, v1, :cond_c

    iget-object v0, p0, Lmiui/view/ViewPager;->cL:Landroid/widget/Scroller;

    invoke-virtual {v0}, Landroid/widget/Scroller;->getFinalX()I

    move-result v0

    iget-object v1, p0, Lmiui/view/ViewPager;->cL:Landroid/widget/Scroller;

    invoke-virtual {v1}, Landroid/widget/Scroller;->getCurrX()I

    move-result v1

    sub-int/2addr v0, v1

    invoke-static {v0}, Ljava/lang/Math;->abs(I)I

    move-result v0

    iget v1, p0, Lmiui/view/ViewPager;->Dc:I

    if-le v0, v1, :cond_c

    .line 1728
    iget-object v0, p0, Lmiui/view/ViewPager;->cL:Landroid/widget/Scroller;

    invoke-virtual {v0}, Landroid/widget/Scroller;->abortAnimation()V

    .line 1729
    iput-boolean v2, p0, Lmiui/view/ViewPager;->CC:Z

    .line 1730
    invoke-virtual {p0}, Lmiui/view/ViewPager;->cS()V

    .line 1731
    iput-boolean v6, p0, Lmiui/view/ViewPager;->CJ:Z

    .line 1732
    invoke-direct {p0, v6}, Lmiui/view/ViewPager;->X(I)V

    goto/16 :goto_1

    .line 1734
    :cond_c
    invoke-direct {p0}, Lmiui/view/ViewPager;->cT()V

    .line 1735
    iput-boolean v2, p0, Lmiui/view/ViewPager;->CJ:Z

    goto/16 :goto_1

    .line 1745
    :sswitch_2
    invoke-direct {p0, p1}, Lmiui/view/ViewPager;->d(Landroid/view/MotionEvent;)V

    goto/16 :goto_1

    .line 1649
    :sswitch_data_0
    .sparse-switch
        0x0 -> :sswitch_1
        0x2 -> :sswitch_0
        0x6 -> :sswitch_2
    .end sparse-switch
.end method

.method protected onLayout(ZIIII)V
    .locals 18

    .prologue
    .line 1313
    const/4 v1, 0x1

    move-object/from16 v0, p0

    iput-boolean v1, v0, Lmiui/view/ViewPager;->CA:Z

    .line 1314
    invoke-virtual/range {p0 .. p0}, Lmiui/view/ViewPager;->cS()V

    .line 1315
    const/4 v1, 0x0

    move-object/from16 v0, p0

    iput-boolean v1, v0, Lmiui/view/ViewPager;->CA:Z

    .line 1317
    invoke-virtual/range {p0 .. p0}, Lmiui/view/ViewPager;->getChildCount()I

    move-result v9

    .line 1318
    sub-int v10, p4, p2

    .line 1319
    sub-int v11, p5, p3

    .line 1320
    invoke-virtual/range {p0 .. p0}, Lmiui/view/ViewPager;->getPaddingLeft()I

    move-result v6

    .line 1321
    invoke-virtual/range {p0 .. p0}, Lmiui/view/ViewPager;->getPaddingTop()I

    move-result v2

    .line 1322
    invoke-virtual/range {p0 .. p0}, Lmiui/view/ViewPager;->getPaddingRight()I

    move-result v5

    .line 1323
    invoke-virtual/range {p0 .. p0}, Lmiui/view/ViewPager;->getPaddingBottom()I

    move-result v3

    .line 1324
    invoke-virtual/range {p0 .. p0}, Lmiui/view/ViewPager;->getScrollX()I

    move-result v12

    .line 1326
    const/4 v4, 0x0

    .line 1330
    const/4 v1, 0x0

    move v8, v1

    :goto_0
    if-ge v8, v9, :cond_0

    .line 1331
    move-object/from16 v0, p0

    invoke-virtual {v0, v8}, Lmiui/view/ViewPager;->getChildAt(I)Landroid/view/View;

    move-result-object v13

    .line 1332
    invoke-virtual {v13}, Landroid/view/View;->getVisibility()I

    move-result v1

    const/16 v7, 0x8

    if-eq v1, v7, :cond_6

    .line 1333
    invoke-virtual {v13}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v1

    check-cast v1, Lmiui/view/ViewPager$LayoutParams;

    .line 1336
    iget-boolean v7, v1, Lmiui/view/ViewPager$LayoutParams;->isDecor:Z

    if-eqz v7, :cond_6

    .line 1337
    iget v7, v1, Lmiui/view/ViewPager$LayoutParams;->gravity:I

    and-int/lit8 v7, v7, 0x7

    .line 1338
    iget v1, v1, Lmiui/view/ViewPager$LayoutParams;->gravity:I

    and-int/lit8 v14, v1, 0x70

    .line 1339
    packed-switch v7, :pswitch_data_0

    :pswitch_0
    move v7, v6

    .line 1356
    :goto_1
    sparse-switch v14, :sswitch_data_0

    move v1, v2

    move/from16 v17, v3

    move v3, v2

    move/from16 v2, v17

    .line 1373
    :goto_2
    add-int/2addr v7, v12

    .line 1374
    invoke-virtual {v13}, Landroid/view/View;->getMeasuredWidth()I

    move-result v14

    add-int/2addr v14, v7

    invoke-virtual {v13}, Landroid/view/View;->getMeasuredHeight()I

    move-result v15

    add-int/2addr v15, v1

    invoke-virtual {v13, v7, v1, v14, v15}, Landroid/view/View;->layout(IIII)V

    .line 1377
    add-int/lit8 v1, v4, 0x1

    move v4, v3

    move v3, v2

    move v2, v5

    move v5, v6

    .line 1330
    :goto_3
    add-int/lit8 v6, v8, 0x1

    move v8, v6

    move v6, v5

    move v5, v2

    move v2, v4

    move v4, v1

    goto :goto_0

    .line 1345
    :pswitch_1
    invoke-virtual {v13}, Landroid/view/View;->getMeasuredWidth()I

    move-result v1

    add-int/2addr v1, v6

    move v7, v6

    move v6, v1

    .line 1346
    goto :goto_1

    .line 1348
    :pswitch_2
    invoke-virtual {v13}, Landroid/view/View;->getMeasuredWidth()I

    move-result v1

    sub-int v1, v10, v1

    div-int/lit8 v1, v1, 0x2

    invoke-static {v1, v6}, Ljava/lang/Math;->max(II)I

    move-result v1

    move v7, v1

    .line 1350
    goto :goto_1

    .line 1352
    :pswitch_3
    sub-int v1, v10, v5

    invoke-virtual {v13}, Landroid/view/View;->getMeasuredWidth()I

    move-result v7

    sub-int/2addr v1, v7

    .line 1353
    invoke-virtual {v13}, Landroid/view/View;->getMeasuredWidth()I

    move-result v7

    add-int/2addr v5, v7

    move v7, v1

    goto :goto_1

    .line 1362
    :sswitch_0
    invoke-virtual {v13}, Landroid/view/View;->getMeasuredHeight()I

    move-result v1

    add-int/2addr v1, v2

    move/from16 v17, v2

    move v2, v3

    move v3, v1

    move/from16 v1, v17

    .line 1363
    goto :goto_2

    .line 1365
    :sswitch_1
    invoke-virtual {v13}, Landroid/view/View;->getMeasuredHeight()I

    move-result v1

    sub-int v1, v11, v1

    div-int/lit8 v1, v1, 0x2

    invoke-static {v1, v2}, Ljava/lang/Math;->max(II)I

    move-result v1

    move/from16 v17, v3

    move v3, v2

    move/from16 v2, v17

    .line 1367
    goto :goto_2

    .line 1369
    :sswitch_2
    sub-int v1, v11, v3

    invoke-virtual {v13}, Landroid/view/View;->getMeasuredHeight()I

    move-result v14

    sub-int/2addr v1, v14

    .line 1370
    invoke-virtual {v13}, Landroid/view/View;->getMeasuredHeight()I

    move-result v14

    add-int/2addr v3, v14

    move/from16 v17, v3

    move v3, v2

    move/from16 v2, v17

    goto :goto_2

    .line 1383
    :cond_0
    const/4 v1, 0x0

    move v7, v1

    :goto_4
    if-ge v7, v9, :cond_5

    .line 1384
    move-object/from16 v0, p0

    invoke-virtual {v0, v7}, Lmiui/view/ViewPager;->getChildAt(I)Landroid/view/View;

    move-result-object v8

    .line 1385
    invoke-virtual {v8}, Landroid/view/View;->getVisibility()I

    move-result v1

    const/16 v12, 0x8

    if-eq v1, v12, :cond_4

    .line 1386
    invoke-virtual {v8}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v1

    check-cast v1, Lmiui/view/ViewPager$LayoutParams;

    .line 1388
    iget-boolean v12, v1, Lmiui/view/ViewPager$LayoutParams;->isDecor:Z

    if-nez v12, :cond_4

    move-object/from16 v0, p0

    invoke-virtual {v0, v8}, Lmiui/view/ViewPager;->i(Landroid/view/View;)Lmiui/view/ViewPager$a;

    move-result-object v12

    if-eqz v12, :cond_4

    .line 1389
    int-to-float v13, v10

    iget v14, v12, Lmiui/view/ViewPager$a;->lg:F

    mul-float/2addr v13, v14

    float-to-int v13, v13

    .line 1390
    add-int/2addr v13, v6

    .line 1391
    move-object/from16 v0, p0

    iget-boolean v14, v0, Lmiui/view/ViewPager;->CX:Z

    if-nez v14, :cond_1

    iget-boolean v14, v1, Lmiui/view/ViewPager$LayoutParams;->needsMeasure:Z

    if-eqz v14, :cond_3

    .line 1394
    :cond_1
    const/4 v14, 0x0

    iput-boolean v14, v1, Lmiui/view/ViewPager$LayoutParams;->needsMeasure:Z

    .line 1395
    sub-int v14, v10, v6

    sub-int/2addr v14, v5

    int-to-float v14, v14

    iget v1, v1, Lmiui/view/ViewPager$LayoutParams;->widthFactor:F

    mul-float/2addr v1, v14

    float-to-int v1, v1

    const/high16 v14, 0x40000000

    invoke-static {v1, v14}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result v14

    .line 1399
    const/4 v1, 0x0

    move-object/from16 v0, p0

    iput-boolean v1, v0, Lmiui/view/ViewPager;->CX:Z

    .line 1402
    const/4 v1, 0x0

    .line 1403
    iget-boolean v12, v12, Lmiui/view/ViewPager$a;->lh:Z

    if-eqz v12, :cond_2

    .line 1404
    invoke-virtual/range {p0 .. p0}, Lmiui/view/ViewPager;->getSplitActionBarHeight()I

    move-result v1

    .line 1407
    :cond_2
    sub-int v12, v11, v2

    sub-int/2addr v12, v3

    int-to-float v12, v12

    int-to-float v1, v1

    const/high16 v15, 0x3f800000

    move-object/from16 v0, p0

    iget v0, v0, Lmiui/view/ViewPager;->CW:F

    move/from16 v16, v0

    sub-float v15, v15, v16

    mul-float/2addr v1, v15

    sub-float v1, v12, v1

    float-to-int v1, v1

    const/high16 v12, 0x40000000

    invoke-static {v1, v12}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result v1

    .line 1410
    invoke-virtual {v8, v14, v1}, Landroid/view/View;->measure(II)V

    .line 1415
    :cond_3
    invoke-virtual {v8}, Landroid/view/View;->getMeasuredWidth()I

    move-result v1

    add-int/2addr v1, v13

    invoke-virtual {v8}, Landroid/view/View;->getMeasuredHeight()I

    move-result v12

    add-int/2addr v12, v2

    invoke-virtual {v8, v13, v2, v1, v12}, Landroid/view/View;->layout(IIII)V

    .line 1383
    :cond_4
    add-int/lit8 v1, v7, 0x1

    move v7, v1

    goto :goto_4

    .line 1421
    :cond_5
    move-object/from16 v0, p0

    iput v2, v0, Lmiui/view/ViewPager;->Cu:I

    .line 1422
    sub-int v1, v11, v3

    move-object/from16 v0, p0

    iput v1, v0, Lmiui/view/ViewPager;->Cv:I

    .line 1423
    move-object/from16 v0, p0

    iput v4, v0, Lmiui/view/ViewPager;->Dk:I

    .line 1424
    const/4 v1, 0x0

    move-object/from16 v0, p0

    iput-boolean v1, v0, Lmiui/view/ViewPager;->cm:Z

    .line 1425
    return-void

    :cond_6
    move v1, v4

    move v4, v2

    move v2, v5

    move v5, v6

    goto/16 :goto_3

    .line 1339
    nop

    :pswitch_data_0
    .packed-switch 0x1
        :pswitch_2
        :pswitch_0
        :pswitch_1
        :pswitch_0
        :pswitch_3
    .end packed-switch

    .line 1356
    :sswitch_data_0
    .sparse-switch
        0x10 -> :sswitch_1
        0x30 -> :sswitch_0
        0x50 -> :sswitch_2
    .end sparse-switch
.end method

.method protected onMeasure(II)V
    .locals 13

    .prologue
    .line 1176
    const/4 v0, 0x0

    invoke-static {v0, p1}, Lmiui/view/ViewPager;->getDefaultSize(II)I

    move-result v0

    const/4 v1, 0x0

    invoke-static {v1, p2}, Lmiui/view/ViewPager;->getDefaultSize(II)I

    move-result v1

    invoke-virtual {p0, v0, v1}, Lmiui/view/ViewPager;->setMeasuredDimension(II)V

    .line 1179
    invoke-virtual {p0}, Lmiui/view/ViewPager;->getMeasuredWidth()I

    move-result v0

    .line 1180
    div-int/lit8 v1, v0, 0xa

    .line 1181
    iget v2, p0, Lmiui/view/ViewPager;->CS:I

    invoke-static {v1, v2}, Ljava/lang/Math;->min(II)I

    move-result v1

    iput v1, p0, Lmiui/view/ViewPager;->CT:I

    .line 1184
    invoke-virtual {p0}, Lmiui/view/ViewPager;->getPaddingLeft()I

    move-result v1

    sub-int/2addr v0, v1

    invoke-virtual {p0}, Lmiui/view/ViewPager;->getPaddingRight()I

    move-result v1

    sub-int v3, v0, v1

    .line 1185
    invoke-virtual {p0}, Lmiui/view/ViewPager;->getMeasuredHeight()I

    move-result v0

    invoke-virtual {p0}, Lmiui/view/ViewPager;->getPaddingTop()I

    move-result v1

    sub-int/2addr v0, v1

    invoke-virtual {p0}, Lmiui/view/ViewPager;->getPaddingBottom()I

    move-result v1

    sub-int v5, v0, v1

    .line 1192
    invoke-virtual {p0}, Lmiui/view/ViewPager;->getChildCount()I

    move-result v9

    .line 1193
    const/4 v0, 0x0

    move v8, v0

    :goto_0
    if-ge v8, v9, :cond_8

    .line 1194
    invoke-virtual {p0, v8}, Lmiui/view/ViewPager;->getChildAt(I)Landroid/view/View;

    move-result-object v10

    .line 1195
    invoke-virtual {v10}, Landroid/view/View;->getVisibility()I

    move-result v0

    const/16 v1, 0x8

    if-eq v0, v1, :cond_3

    .line 1196
    invoke-virtual {v10}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v0

    check-cast v0, Lmiui/view/ViewPager$LayoutParams;

    .line 1197
    if-eqz v0, :cond_3

    iget-boolean v1, v0, Lmiui/view/ViewPager$LayoutParams;->isDecor:Z

    if-eqz v1, :cond_3

    .line 1198
    iget v1, v0, Lmiui/view/ViewPager$LayoutParams;->gravity:I

    and-int/lit8 v6, v1, 0x7

    .line 1199
    iget v1, v0, Lmiui/view/ViewPager$LayoutParams;->gravity:I

    and-int/lit8 v4, v1, 0x70

    .line 1200
    const/high16 v2, -0x80000000

    .line 1201
    const/high16 v1, -0x80000000

    .line 1202
    const/16 v7, 0x30

    if-eq v4, v7, :cond_0

    const/16 v7, 0x50

    if-ne v4, v7, :cond_4

    :cond_0
    const/4 v4, 0x1

    move v7, v4

    .line 1203
    :goto_1
    const/4 v4, 0x3

    if-eq v6, v4, :cond_1

    const/4 v4, 0x5

    if-ne v6, v4, :cond_5

    :cond_1
    const/4 v4, 0x1

    move v6, v4

    .line 1205
    :goto_2
    if-eqz v7, :cond_6

    .line 1206
    const/high16 v2, 0x40000000

    .line 1214
    :cond_2
    :goto_3
    iget v4, v0, Lmiui/view/ViewPager$LayoutParams;->width:I

    const/4 v11, -0x2

    if-eq v4, v11, :cond_e

    .line 1215
    const/high16 v4, 0x40000000

    .line 1216
    iget v2, v0, Lmiui/view/ViewPager$LayoutParams;->width:I

    const/4 v11, -0x1

    if-eq v2, v11, :cond_d

    .line 1217
    iget v2, v0, Lmiui/view/ViewPager$LayoutParams;->width:I

    .line 1220
    :goto_4
    iget v11, v0, Lmiui/view/ViewPager$LayoutParams;->height:I

    const/4 v12, -0x2

    if-eq v11, v12, :cond_c

    .line 1221
    const/high16 v1, 0x40000000

    .line 1222
    iget v11, v0, Lmiui/view/ViewPager$LayoutParams;->height:I

    const/4 v12, -0x1

    if-eq v11, v12, :cond_c

    .line 1223
    iget v0, v0, Lmiui/view/ViewPager$LayoutParams;->height:I

    .line 1226
    :goto_5
    invoke-static {v2, v4}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result v2

    .line 1227
    invoke-static {v0, v1}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result v0

    .line 1228
    invoke-virtual {v10, v2, v0}, Landroid/view/View;->measure(II)V

    .line 1230
    if-eqz v7, :cond_7

    .line 1231
    invoke-virtual {v10}, Landroid/view/View;->getMeasuredHeight()I

    move-result v0

    sub-int/2addr v5, v0

    .line 1193
    :cond_3
    :goto_6
    add-int/lit8 v0, v8, 0x1

    move v8, v0

    goto :goto_0

    .line 1202
    :cond_4
    const/4 v4, 0x0

    move v7, v4

    goto :goto_1

    .line 1203
    :cond_5
    const/4 v4, 0x0

    move v6, v4

    goto :goto_2

    .line 1207
    :cond_6
    if-eqz v6, :cond_2

    .line 1208
    const/high16 v1, 0x40000000

    goto :goto_3

    .line 1232
    :cond_7
    if-eqz v6, :cond_3

    .line 1233
    invoke-virtual {v10}, Landroid/view/View;->getMeasuredWidth()I

    move-result v0

    sub-int/2addr v3, v0

    goto :goto_6

    .line 1239
    :cond_8
    const/high16 v0, 0x40000000

    invoke-static {v3, v0}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result v0

    iput v0, p0, Lmiui/view/ViewPager;->Cy:I

    .line 1240
    const/high16 v0, 0x40000000

    invoke-static {v5, v0}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result v0

    iput v0, p0, Lmiui/view/ViewPager;->Cz:I

    .line 1243
    const/4 v0, 0x1

    iput-boolean v0, p0, Lmiui/view/ViewPager;->CA:Z

    .line 1244
    invoke-virtual {p0}, Lmiui/view/ViewPager;->cS()V

    .line 1245
    const/4 v0, 0x0

    iput-boolean v0, p0, Lmiui/view/ViewPager;->CA:Z

    .line 1248
    invoke-virtual {p0}, Lmiui/view/ViewPager;->getChildCount()I

    move-result v2

    .line 1249
    const/4 v0, 0x0

    move v1, v0

    :goto_7
    if-ge v1, v2, :cond_a

    .line 1250
    invoke-virtual {p0, v1}, Lmiui/view/ViewPager;->getChildAt(I)Landroid/view/View;

    move-result-object v4

    .line 1251
    invoke-virtual {v4}, Landroid/view/View;->getVisibility()I

    move-result v0

    const/16 v6, 0x8

    if-eq v0, v6, :cond_9

    .line 1255
    invoke-virtual {v4}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v0

    check-cast v0, Lmiui/view/ViewPager$LayoutParams;

    .line 1256
    iget-boolean v6, v0, Lmiui/view/ViewPager$LayoutParams;->isDecor:Z

    if-nez v6, :cond_9

    .line 1257
    int-to-float v6, v3

    iget v0, v0, Lmiui/view/ViewPager$LayoutParams;->widthFactor:F

    mul-float/2addr v0, v6

    float-to-int v0, v0

    const/high16 v6, 0x40000000

    invoke-static {v0, v6}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result v6

    .line 1262
    invoke-virtual {p0, v4}, Lmiui/view/ViewPager;->i(Landroid/view/View;)Lmiui/view/ViewPager$a;

    move-result-object v0

    .line 1263
    if-eqz v0, :cond_b

    iget-boolean v0, v0, Lmiui/view/ViewPager$a;->lh:Z

    if-eqz v0, :cond_b

    .line 1264
    invoke-virtual {p0}, Lmiui/view/ViewPager;->getSplitActionBarHeight()I

    move-result v0

    int-to-float v0, v0

    const/high16 v7, 0x3f800000

    iget v8, p0, Lmiui/view/ViewPager;->CW:F

    sub-float/2addr v7, v8

    mul-float/2addr v0, v7

    float-to-int v0, v0

    sub-int v0, v5, v0

    .line 1266
    :goto_8
    const/high16 v7, 0x40000000

    invoke-static {v0, v7}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result v0

    .line 1268
    invoke-virtual {v4, v6, v0}, Landroid/view/View;->measure(II)V

    .line 1249
    :cond_9
    add-int/lit8 v0, v1, 0x1

    move v1, v0

    goto :goto_7

    .line 1272
    :cond_a
    return-void

    :cond_b
    move v0, v5

    goto :goto_8

    :cond_c
    move v0, v5

    goto/16 :goto_5

    :cond_d
    move v2, v3

    goto/16 :goto_4

    :cond_e
    move v4, v2

    move v2, v3

    goto/16 :goto_4
.end method

.method protected onPageScrolled(IFI)V
    .locals 10

    .prologue
    .line 1528
    iget v0, p0, Lmiui/view/ViewPager;->Dk:I

    if-lez v0, :cond_2

    .line 1529
    invoke-virtual {p0}, Lmiui/view/ViewPager;->getScrollX()I

    move-result v4

    .line 1530
    invoke-virtual {p0}, Lmiui/view/ViewPager;->getPaddingLeft()I

    move-result v1

    .line 1531
    invoke-virtual {p0}, Lmiui/view/ViewPager;->getPaddingRight()I

    move-result v2

    .line 1532
    invoke-virtual {p0}, Lmiui/view/ViewPager;->getWidth()I

    move-result v5

    .line 1533
    invoke-virtual {p0}, Lmiui/view/ViewPager;->getChildCount()I

    move-result v6

    .line 1534
    const/4 v0, 0x0

    move v3, v0

    :goto_0
    if-ge v3, v6, :cond_2

    .line 1535
    invoke-virtual {p0, v3}, Lmiui/view/ViewPager;->getChildAt(I)Landroid/view/View;

    move-result-object v7

    .line 1536
    invoke-virtual {v7}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v0

    check-cast v0, Lmiui/view/ViewPager$LayoutParams;

    .line 1537
    iget-boolean v8, v0, Lmiui/view/ViewPager$LayoutParams;->isDecor:Z

    if-nez v8, :cond_1

    move v9, v2

    move v2, v1

    move v1, v9

    .line 1534
    :cond_0
    :goto_1
    add-int/lit8 v0, v3, 0x1

    move v3, v0

    move v9, v1

    move v1, v2

    move v2, v9

    goto :goto_0

    .line 1539
    :cond_1
    iget v0, v0, Lmiui/view/ViewPager$LayoutParams;->gravity:I

    and-int/lit8 v0, v0, 0x7

    .line 1541
    packed-switch v0, :pswitch_data_0

    :pswitch_0
    move v0, v1

    move v9, v2

    move v2, v1

    move v1, v9

    .line 1558
    :goto_2
    add-int/2addr v0, v4

    .line 1560
    invoke-virtual {v7}, Landroid/view/View;->getLeft()I

    move-result v8

    sub-int/2addr v0, v8

    .line 1561
    if-eqz v0, :cond_0

    .line 1562
    invoke-virtual {v7, v0}, Landroid/view/View;->offsetLeftAndRight(I)V

    goto :goto_1

    .line 1547
    :pswitch_1
    invoke-virtual {v7}, Landroid/view/View;->getWidth()I

    move-result v0

    add-int/2addr v0, v1

    move v9, v1

    move v1, v2

    move v2, v0

    move v0, v9

    .line 1548
    goto :goto_2

    .line 1550
    :pswitch_2
    invoke-virtual {v7}, Landroid/view/View;->getMeasuredWidth()I

    move-result v0

    sub-int v0, v5, v0

    div-int/lit8 v0, v0, 0x2

    invoke-static {v0, v1}, Ljava/lang/Math;->max(II)I

    move-result v0

    move v9, v2

    move v2, v1

    move v1, v9

    .line 1552
    goto :goto_2

    .line 1554
    :pswitch_3
    sub-int v0, v5, v2

    invoke-virtual {v7}, Landroid/view/View;->getMeasuredWidth()I

    move-result v8

    sub-int/2addr v0, v8

    .line 1555
    invoke-virtual {v7}, Landroid/view/View;->getMeasuredWidth()I

    move-result v8

    add-int/2addr v2, v8

    move v9, v2

    move v2, v1

    move v1, v9

    goto :goto_2

    .line 1567
    :cond_2
    iget-object v0, p0, Lmiui/view/ViewPager;->Dl:Lmiui/view/ViewPager$OnPageChangeListener;

    if-eqz v0, :cond_3

    .line 1568
    iget-object v0, p0, Lmiui/view/ViewPager;->Dl:Lmiui/view/ViewPager$OnPageChangeListener;

    invoke-interface {v0, p1, p2, p3}, Lmiui/view/ViewPager$OnPageChangeListener;->onPageScrolled(IFI)V

    .line 1570
    :cond_3
    iget-object v0, p0, Lmiui/view/ViewPager;->Dm:Lmiui/view/ViewPager$OnPageChangeListener;

    if-eqz v0, :cond_4

    .line 1571
    iget-object v0, p0, Lmiui/view/ViewPager;->Dm:Lmiui/view/ViewPager$OnPageChangeListener;

    invoke-interface {v0, p1, p2, p3}, Lmiui/view/ViewPager$OnPageChangeListener;->onPageScrolled(IFI)V

    .line 1573
    :cond_4
    const/4 v0, 0x1

    iput-boolean v0, p0, Lmiui/view/ViewPager;->Dj:Z

    .line 1574
    return-void

    .line 1541
    nop

    :pswitch_data_0
    .packed-switch 0x1
        :pswitch_2
        :pswitch_0
        :pswitch_1
        :pswitch_0
        :pswitch_3
    .end packed-switch
.end method

.method protected onRequestFocusInDescendants(ILandroid/graphics/Rect;)Z
    .locals 8

    .prologue
    const/4 v2, 0x1

    const/4 v4, 0x0

    const/4 v1, -0x1

    .line 2478
    invoke-virtual {p0}, Lmiui/view/ViewPager;->getChildCount()I

    move-result v0

    .line 2479
    and-int/lit8 v3, p1, 0x2

    if-eqz v3, :cond_0

    move v1, v2

    move v3, v4

    .line 2488
    :goto_0
    if-eq v3, v0, :cond_2

    .line 2489
    invoke-virtual {p0, v3}, Lmiui/view/ViewPager;->getChildAt(I)Landroid/view/View;

    move-result-object v5

    .line 2490
    invoke-virtual {v5}, Landroid/view/View;->getVisibility()I

    move-result v6

    if-nez v6, :cond_1

    .line 2491
    invoke-virtual {p0, v5}, Lmiui/view/ViewPager;->i(Landroid/view/View;)Lmiui/view/ViewPager$a;

    move-result-object v6

    .line 2492
    if-eqz v6, :cond_1

    iget v6, v6, Lmiui/view/ViewPager$a;->position:I

    iget v7, p0, Lmiui/view/ViewPager;->Co:I

    if-ne v6, v7, :cond_1

    .line 2493
    invoke-virtual {v5, p1, p2}, Landroid/view/View;->requestFocus(ILandroid/graphics/Rect;)Z

    move-result v5

    if-eqz v5, :cond_1

    .line 2499
    :goto_1
    return v2

    .line 2484
    :cond_0
    add-int/lit8 v0, v0, -0x1

    move v3, v0

    move v0, v1

    .line 2486
    goto :goto_0

    .line 2488
    :cond_1
    add-int/2addr v3, v1

    goto :goto_0

    :cond_2
    move v2, v4

    .line 2499
    goto :goto_1
.end method

.method public onRestoreInstanceState(Landroid/os/Parcelable;)V
    .locals 3

    .prologue
    .line 1091
    instance-of v0, p1, Lmiui/view/ViewPager$SavedState;

    if-nez v0, :cond_0

    .line 1092
    invoke-super {p0, p1}, Landroid/view/ViewGroup;->onRestoreInstanceState(Landroid/os/Parcelable;)V

    .line 1106
    :goto_0
    return-void

    .line 1096
    :cond_0
    check-cast p1, Lmiui/view/ViewPager$SavedState;

    .line 1097
    invoke-virtual {p1}, Lmiui/view/ViewPager$SavedState;->getSuperState()Landroid/os/Parcelable;

    move-result-object v0

    invoke-super {p0, v0}, Landroid/view/ViewGroup;->onRestoreInstanceState(Landroid/os/Parcelable;)V

    .line 1099
    iget-object v0, p0, Lmiui/view/ViewPager;->Cn:Lmiui/view/PagerAdapter;

    if-eqz v0, :cond_1

    .line 1100
    iget-object v0, p0, Lmiui/view/ViewPager;->Cn:Lmiui/view/PagerAdapter;

    iget-object v1, p1, Lmiui/view/ViewPager$SavedState;->Mx:Landroid/os/Parcelable;

    const/4 v2, 0x0

    invoke-virtual {v0, v1, v2}, Lmiui/view/PagerAdapter;->restoreState(Landroid/os/Parcelable;Ljava/lang/ClassLoader;)V

    .line 1101
    iget v0, p1, Lmiui/view/ViewPager$SavedState;->position:I

    const/4 v1, 0x0

    const/4 v2, 0x1

    invoke-virtual {p0, v0, v1, v2}, Lmiui/view/ViewPager;->a(IZZ)V

    goto :goto_0

    .line 1103
    :cond_1
    iget v0, p1, Lmiui/view/ViewPager$SavedState;->position:I

    iput v0, p0, Lmiui/view/ViewPager;->Cp:I

    .line 1104
    iget-object v0, p1, Lmiui/view/ViewPager$SavedState;->Mx:Landroid/os/Parcelable;

    iput-object v0, p0, Lmiui/view/ViewPager;->Cq:Landroid/os/Parcelable;

    goto :goto_0
.end method

.method public onSaveInstanceState()Landroid/os/Parcelable;
    .locals 2

    .prologue
    .line 1080
    invoke-super {p0}, Landroid/view/ViewGroup;->onSaveInstanceState()Landroid/os/Parcelable;

    move-result-object v0

    .line 1081
    new-instance v1, Lmiui/view/ViewPager$SavedState;

    invoke-direct {v1, v0}, Lmiui/view/ViewPager$SavedState;-><init>(Landroid/os/Parcelable;)V

    .line 1082
    iget v0, p0, Lmiui/view/ViewPager;->Co:I

    iput v0, v1, Lmiui/view/ViewPager$SavedState;->position:I

    .line 1083
    iget-object v0, p0, Lmiui/view/ViewPager;->Cn:Lmiui/view/PagerAdapter;

    if-eqz v0, :cond_0

    .line 1084
    iget-object v0, p0, Lmiui/view/ViewPager;->Cn:Lmiui/view/PagerAdapter;

    invoke-virtual {v0}, Lmiui/view/PagerAdapter;->saveState()Landroid/os/Parcelable;

    move-result-object v0

    iput-object v0, v1, Lmiui/view/ViewPager$SavedState;->Mx:Landroid/os/Parcelable;

    .line 1086
    :cond_0
    return-object v1
.end method

.method protected onSizeChanged(IIII)V
    .locals 2

    .prologue
    .line 1276
    invoke-super {p0, p1, p2, p3, p4}, Landroid/view/ViewGroup;->onSizeChanged(IIII)V

    .line 1279
    if-eq p1, p3, :cond_0

    .line 1280
    iget v0, p0, Lmiui/view/ViewPager;->Cs:I

    iget v1, p0, Lmiui/view/ViewPager;->Cs:I

    invoke-direct {p0, p1, p3, v0, v1}, Lmiui/view/ViewPager;->e(IIII)V

    .line 1282
    :cond_0
    return-void
.end method

.method public onTouchEvent(Landroid/view/MotionEvent;)Z
    .locals 7

    .prologue
    const/4 v6, -0x1

    const/4 v2, 0x0

    const/4 v1, 0x1

    .line 1763
    iget-boolean v0, p0, Lmiui/view/ViewPager;->De:Z

    if-eqz v0, :cond_0

    move v0, v1

    .line 1876
    :goto_0
    return v0

    .line 1770
    :cond_0
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getAction()I

    move-result v0

    if-nez v0, :cond_1

    invoke-virtual {p1}, Landroid/view/MotionEvent;->getEdgeFlags()I

    move-result v0

    if-eqz v0, :cond_1

    move v0, v2

    .line 1773
    goto :goto_0

    .line 1776
    :cond_1
    iget-object v0, p0, Lmiui/view/ViewPager;->Cn:Lmiui/view/PagerAdapter;

    if-eqz v0, :cond_2

    iget-object v0, p0, Lmiui/view/ViewPager;->Cn:Lmiui/view/PagerAdapter;

    invoke-virtual {v0}, Lmiui/view/PagerAdapter;->getCount()I

    move-result v0

    if-nez v0, :cond_3

    :cond_2
    move v0, v2

    .line 1778
    goto :goto_0

    .line 1781
    :cond_3
    iget-object v0, p0, Lmiui/view/ViewPager;->bR:Landroid/view/VelocityTracker;

    if-nez v0, :cond_4

    .line 1782
    invoke-static {}, Landroid/view/VelocityTracker;->obtain()Landroid/view/VelocityTracker;

    move-result-object v0

    iput-object v0, p0, Lmiui/view/ViewPager;->bR:Landroid/view/VelocityTracker;

    .line 1784
    :cond_4
    iget-object v0, p0, Lmiui/view/ViewPager;->bR:Landroid/view/VelocityTracker;

    invoke-virtual {v0, p1}, Landroid/view/VelocityTracker;->addMovement(Landroid/view/MotionEvent;)V

    .line 1786
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getAction()I

    move-result v0

    .line 1789
    and-int/lit16 v0, v0, 0xff

    packed-switch v0, :pswitch_data_0

    .line 1873
    :cond_5
    :goto_1
    :pswitch_0
    if-eqz v2, :cond_6

    .line 1874
    invoke-virtual {p0}, Lmiui/view/ViewPager;->postInvalidateOnAnimation()V

    :cond_6
    move v0, v1

    .line 1876
    goto :goto_0

    .line 1791
    :pswitch_1
    iget-object v0, p0, Lmiui/view/ViewPager;->cL:Landroid/widget/Scroller;

    invoke-virtual {v0}, Landroid/widget/Scroller;->abortAnimation()V

    .line 1792
    iput-boolean v2, p0, Lmiui/view/ViewPager;->CC:Z

    .line 1793
    invoke-virtual {p0}, Lmiui/view/ViewPager;->cS()V

    .line 1794
    iput-boolean v1, p0, Lmiui/view/ViewPager;->CJ:Z

    .line 1795
    invoke-direct {p0, v1}, Lmiui/view/ViewPager;->X(I)V

    .line 1798
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getX()F

    move-result v0

    iput v0, p0, Lmiui/view/ViewPager;->nT:F

    iput v0, p0, Lmiui/view/ViewPager;->mLastMotionX:F

    .line 1799
    invoke-virtual {p1, v2}, Landroid/view/MotionEvent;->getPointerId(I)I

    move-result v0

    iput v0, p0, Lmiui/view/ViewPager;->cV:I

    goto :goto_1

    .line 1803
    :pswitch_2
    iget-boolean v0, p0, Lmiui/view/ViewPager;->CJ:Z

    if-nez v0, :cond_7

    .line 1804
    iget v0, p0, Lmiui/view/ViewPager;->cV:I

    invoke-virtual {p1, v0}, Landroid/view/MotionEvent;->findPointerIndex(I)I

    move-result v0

    .line 1805
    invoke-virtual {p1, v0}, Landroid/view/MotionEvent;->getX(I)F

    move-result v3

    .line 1806
    iget v4, p0, Lmiui/view/ViewPager;->mLastMotionX:F

    sub-float v4, v3, v4

    invoke-static {v4}, Ljava/lang/Math;->abs(F)F

    move-result v4

    .line 1807
    invoke-virtual {p1, v0}, Landroid/view/MotionEvent;->getY(I)F

    move-result v0

    .line 1808
    iget v5, p0, Lmiui/view/ViewPager;->mLastMotionY:F

    sub-float/2addr v0, v5

    invoke-static {v0}, Ljava/lang/Math;->abs(F)F

    move-result v0

    .line 1810
    iget v5, p0, Lmiui/view/ViewPager;->cT:I

    int-to-float v5, v5

    cmpl-float v5, v4, v5

    if-lez v5, :cond_7

    cmpl-float v0, v4, v0

    if-lez v0, :cond_7

    .line 1812
    iput-boolean v1, p0, Lmiui/view/ViewPager;->CJ:Z

    .line 1813
    iget v0, p0, Lmiui/view/ViewPager;->nT:F

    sub-float v0, v3, v0

    const/4 v3, 0x0

    cmpl-float v0, v0, v3

    if-lez v0, :cond_8

    iget v0, p0, Lmiui/view/ViewPager;->nT:F

    iget v3, p0, Lmiui/view/ViewPager;->cT:I

    int-to-float v3, v3

    add-float/2addr v0, v3

    :goto_2
    iput v0, p0, Lmiui/view/ViewPager;->mLastMotionX:F

    .line 1815
    invoke-direct {p0, v1}, Lmiui/view/ViewPager;->X(I)V

    .line 1816
    invoke-direct {p0, v1}, Lmiui/view/ViewPager;->setScrollingCacheEnabled(Z)V

    .line 1820
    :cond_7
    iget-boolean v0, p0, Lmiui/view/ViewPager;->CJ:Z

    if-eqz v0, :cond_5

    .line 1822
    iget v0, p0, Lmiui/view/ViewPager;->cV:I

    invoke-virtual {p1, v0}, Landroid/view/MotionEvent;->findPointerIndex(I)I

    move-result v0

    .line 1823
    invoke-virtual {p1, v0}, Landroid/view/MotionEvent;->getX(I)F

    move-result v0

    .line 1824
    invoke-direct {p0, v0}, Lmiui/view/ViewPager;->e(F)Z

    move-result v2

    goto :goto_1

    .line 1813
    :cond_8
    iget v0, p0, Lmiui/view/ViewPager;->nT:F

    iget v3, p0, Lmiui/view/ViewPager;->cT:I

    int-to-float v3, v3

    sub-float/2addr v0, v3

    goto :goto_2

    .line 1828
    :pswitch_3
    iget-boolean v0, p0, Lmiui/view/ViewPager;->CJ:Z

    if-eqz v0, :cond_5

    .line 1829
    iget-object v0, p0, Lmiui/view/ViewPager;->bR:Landroid/view/VelocityTracker;

    .line 1830
    const/16 v2, 0x3e8

    iget v3, p0, Lmiui/view/ViewPager;->cU:I

    int-to-float v3, v3

    invoke-virtual {v0, v2, v3}, Landroid/view/VelocityTracker;->computeCurrentVelocity(IF)V

    .line 1831
    iget v2, p0, Lmiui/view/ViewPager;->cV:I

    invoke-virtual {v0, v2}, Landroid/view/VelocityTracker;->getXVelocity(I)F

    move-result v0

    float-to-int v0, v0

    .line 1832
    iput-boolean v1, p0, Lmiui/view/ViewPager;->CC:Z

    .line 1833
    invoke-virtual {p0}, Lmiui/view/ViewPager;->getWidth()I

    move-result v2

    .line 1834
    invoke-virtual {p0}, Lmiui/view/ViewPager;->getScrollX()I

    move-result v3

    .line 1835
    invoke-direct {p0}, Lmiui/view/ViewPager;->cU()Lmiui/view/ViewPager$a;

    move-result-object v4

    .line 1836
    iget v5, v4, Lmiui/view/ViewPager$a;->position:I

    .line 1837
    int-to-float v3, v3

    int-to-float v2, v2

    div-float v2, v3, v2

    iget v3, v4, Lmiui/view/ViewPager$a;->lg:F

    sub-float/2addr v2, v3

    iget v3, v4, Lmiui/view/ViewPager$a;->widthFactor:F

    div-float/2addr v2, v3

    .line 1838
    iget v3, p0, Lmiui/view/ViewPager;->cV:I

    invoke-virtual {p1, v3}, Landroid/view/MotionEvent;->findPointerIndex(I)I

    move-result v3

    .line 1839
    invoke-virtual {p1, v3}, Landroid/view/MotionEvent;->getX(I)F

    move-result v3

    .line 1840
    iget v4, p0, Lmiui/view/ViewPager;->nT:F

    sub-float/2addr v3, v4

    float-to-int v3, v3

    .line 1841
    invoke-direct {p0, v5, v2, v0, v3}, Lmiui/view/ViewPager;->a(IFII)I

    move-result v2

    .line 1843
    invoke-virtual {p0, v2, v1, v1, v0}, Lmiui/view/ViewPager;->a(IZZI)V

    .line 1845
    iput v6, p0, Lmiui/view/ViewPager;->cV:I

    .line 1846
    invoke-direct {p0}, Lmiui/view/ViewPager;->cV()V

    .line 1847
    iget-object v0, p0, Lmiui/view/ViewPager;->Dg:Landroid/widget/EdgeEffect;

    invoke-virtual {v0}, Landroid/widget/EdgeEffect;->onRelease()V

    .line 1848
    iget-object v0, p0, Lmiui/view/ViewPager;->Dh:Landroid/widget/EdgeEffect;

    invoke-virtual {v0}, Landroid/widget/EdgeEffect;->onRelease()V

    .line 1849
    iget-object v0, p0, Lmiui/view/ViewPager;->Dg:Landroid/widget/EdgeEffect;

    invoke-virtual {v0}, Landroid/widget/EdgeEffect;->isFinished()Z

    move-result v0

    iget-object v2, p0, Lmiui/view/ViewPager;->Dh:Landroid/widget/EdgeEffect;

    invoke-virtual {v2}, Landroid/widget/EdgeEffect;->isFinished()Z

    move-result v2

    or-int/2addr v2, v0

    .line 1850
    goto/16 :goto_1

    .line 1853
    :pswitch_4
    iget-boolean v0, p0, Lmiui/view/ViewPager;->CJ:Z

    if-eqz v0, :cond_5

    .line 1854
    iget v0, p0, Lmiui/view/ViewPager;->Co:I

    invoke-virtual {p0, v0, v1, v1}, Lmiui/view/ViewPager;->a(IZZ)V

    .line 1855
    iput v6, p0, Lmiui/view/ViewPager;->cV:I

    .line 1856
    invoke-direct {p0}, Lmiui/view/ViewPager;->cV()V

    .line 1857
    iget-object v0, p0, Lmiui/view/ViewPager;->Dg:Landroid/widget/EdgeEffect;

    invoke-virtual {v0}, Landroid/widget/EdgeEffect;->onRelease()V

    .line 1858
    iget-object v0, p0, Lmiui/view/ViewPager;->Dh:Landroid/widget/EdgeEffect;

    invoke-virtual {v0}, Landroid/widget/EdgeEffect;->onRelease()V

    .line 1859
    iget-object v0, p0, Lmiui/view/ViewPager;->Dg:Landroid/widget/EdgeEffect;

    invoke-virtual {v0}, Landroid/widget/EdgeEffect;->isFinished()Z

    move-result v0

    iget-object v2, p0, Lmiui/view/ViewPager;->Dh:Landroid/widget/EdgeEffect;

    invoke-virtual {v2}, Landroid/widget/EdgeEffect;->isFinished()Z

    move-result v2

    or-int/2addr v2, v0

    goto/16 :goto_1

    .line 1863
    :pswitch_5
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getActionIndex()I

    move-result v0

    .line 1864
    invoke-virtual {p1, v0}, Landroid/view/MotionEvent;->getX(I)F

    move-result v3

    iput v3, p0, Lmiui/view/ViewPager;->mLastMotionX:F

    .line 1865
    invoke-virtual {p1, v0}, Landroid/view/MotionEvent;->getPointerId(I)I

    move-result v0

    iput v0, p0, Lmiui/view/ViewPager;->cV:I

    goto/16 :goto_1

    .line 1869
    :pswitch_6
    invoke-direct {p0, p1}, Lmiui/view/ViewPager;->d(Landroid/view/MotionEvent;)V

    .line 1870
    iget v0, p0, Lmiui/view/ViewPager;->cV:I

    invoke-virtual {p1, v0}, Landroid/view/MotionEvent;->findPointerIndex(I)I

    move-result v0

    invoke-virtual {p1, v0}, Landroid/view/MotionEvent;->getX(I)F

    move-result v0

    iput v0, p0, Lmiui/view/ViewPager;->mLastMotionX:F

    goto/16 :goto_1

    .line 1789
    nop

    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_1
        :pswitch_3
        :pswitch_2
        :pswitch_4
        :pswitch_0
        :pswitch_5
        :pswitch_6
    .end packed-switch
.end method

.method public performAccessibilityAction(ILandroid/os/Bundle;)Z
    .locals 4

    .prologue
    const/4 v0, 0x1

    const/4 v1, 0x0

    .line 2565
    invoke-super {p0, p1, p2}, Landroid/view/ViewGroup;->performAccessibilityAction(ILandroid/os/Bundle;)Z

    move-result v2

    if-eqz v2, :cond_0

    .line 2582
    :goto_0
    return v0

    .line 2568
    :cond_0
    sparse-switch p1, :sswitch_data_0

    move v0, v1

    .line 2582
    goto :goto_0

    .line 2570
    :sswitch_0
    iget-object v2, p0, Lmiui/view/ViewPager;->Cn:Lmiui/view/PagerAdapter;

    if-eqz v2, :cond_1

    iget v2, p0, Lmiui/view/ViewPager;->Co:I

    if-ltz v2, :cond_1

    iget v2, p0, Lmiui/view/ViewPager;->Co:I

    iget-object v3, p0, Lmiui/view/ViewPager;->Cn:Lmiui/view/PagerAdapter;

    invoke-virtual {v3}, Lmiui/view/PagerAdapter;->getCount()I

    move-result v3

    add-int/lit8 v3, v3, -0x1

    if-ge v2, v3, :cond_1

    .line 2571
    iget v1, p0, Lmiui/view/ViewPager;->Co:I

    add-int/lit8 v1, v1, 0x1

    invoke-virtual {p0, v1}, Lmiui/view/ViewPager;->setCurrentItem(I)V

    goto :goto_0

    :cond_1
    move v0, v1

    .line 2574
    goto :goto_0

    .line 2576
    :sswitch_1
    iget-object v2, p0, Lmiui/view/ViewPager;->Cn:Lmiui/view/PagerAdapter;

    if-eqz v2, :cond_2

    iget v2, p0, Lmiui/view/ViewPager;->Co:I

    if-lez v2, :cond_2

    iget v2, p0, Lmiui/view/ViewPager;->Co:I

    iget-object v3, p0, Lmiui/view/ViewPager;->Cn:Lmiui/view/PagerAdapter;

    invoke-virtual {v3}, Lmiui/view/PagerAdapter;->getCount()I

    move-result v3

    if-ge v2, v3, :cond_2

    .line 2577
    iget v1, p0, Lmiui/view/ViewPager;->Co:I

    add-int/lit8 v1, v1, -0x1

    invoke-virtual {p0, v1}, Lmiui/view/ViewPager;->setCurrentItem(I)V

    goto :goto_0

    :cond_2
    move v0, v1

    .line 2580
    goto :goto_0

    .line 2568
    :sswitch_data_0
    .sparse-switch
        0x1000 -> :sswitch_0
        0x2000 -> :sswitch_1
    .end sparse-switch
.end method

.method public setAdapter(Lmiui/view/PagerAdapter;)V
    .locals 7

    .prologue
    const/4 v6, 0x1

    const/4 v5, 0x0

    const/4 v4, 0x0

    .line 347
    iget-object v0, p0, Lmiui/view/ViewPager;->Cn:Lmiui/view/PagerAdapter;

    if-eqz v0, :cond_1

    .line 348
    iget-object v0, p0, Lmiui/view/ViewPager;->Cn:Lmiui/view/PagerAdapter;

    iget-object v1, p0, Lmiui/view/ViewPager;->Cr:Lmiui/view/ViewPager$d;

    invoke-virtual {v0, v1}, Lmiui/view/PagerAdapter;->unregisterDataSetObserver(Landroid/database/DataSetObserver;)V

    .line 349
    iget-object v0, p0, Lmiui/view/ViewPager;->Cn:Lmiui/view/PagerAdapter;

    invoke-virtual {v0, p0}, Lmiui/view/PagerAdapter;->startUpdate(Landroid/view/ViewGroup;)V

    .line 350
    iget-object v0, p0, Lmiui/view/ViewPager;->N:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :goto_0
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_0

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/view/ViewPager$a;

    .line 351
    iget-object v2, p0, Lmiui/view/ViewPager;->Cn:Lmiui/view/PagerAdapter;

    iget v3, v0, Lmiui/view/ViewPager$a;->position:I

    iget-object v0, v0, Lmiui/view/ViewPager$a;->le:Ljava/lang/Object;

    invoke-virtual {v2, p0, v3, v0}, Lmiui/view/PagerAdapter;->destroyItem(Landroid/view/ViewGroup;ILjava/lang/Object;)V

    goto :goto_0

    .line 353
    :cond_0
    iget-object v0, p0, Lmiui/view/ViewPager;->Cn:Lmiui/view/PagerAdapter;

    invoke-virtual {v0, p0}, Lmiui/view/PagerAdapter;->finishUpdate(Landroid/view/ViewGroup;)V

    .line 354
    iget-object v0, p0, Lmiui/view/ViewPager;->N:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->clear()V

    .line 355
    invoke-direct {p0}, Lmiui/view/ViewPager;->cQ()V

    .line 356
    iput v4, p0, Lmiui/view/ViewPager;->Co:I

    .line 357
    invoke-virtual {p0, v4, v4}, Lmiui/view/ViewPager;->scrollTo(II)V

    .line 360
    :cond_1
    iget-object v0, p0, Lmiui/view/ViewPager;->Cn:Lmiui/view/PagerAdapter;

    .line 361
    iput-object p1, p0, Lmiui/view/ViewPager;->Cn:Lmiui/view/PagerAdapter;

    .line 363
    iget-object v1, p0, Lmiui/view/ViewPager;->Cn:Lmiui/view/PagerAdapter;

    if-eqz v1, :cond_3

    .line 364
    iget-object v1, p0, Lmiui/view/ViewPager;->Cr:Lmiui/view/ViewPager$d;

    if-nez v1, :cond_2

    .line 365
    new-instance v1, Lmiui/view/ViewPager$d;

    invoke-direct {v1, p0, v5}, Lmiui/view/ViewPager$d;-><init>(Lmiui/view/ViewPager;Lmiui/view/c;)V

    iput-object v1, p0, Lmiui/view/ViewPager;->Cr:Lmiui/view/ViewPager$d;

    .line 367
    :cond_2
    iget-object v1, p0, Lmiui/view/ViewPager;->Cn:Lmiui/view/PagerAdapter;

    iget-object v2, p0, Lmiui/view/ViewPager;->Cr:Lmiui/view/ViewPager$d;

    invoke-virtual {v1, v2}, Lmiui/view/PagerAdapter;->registerDataSetObserver(Landroid/database/DataSetObserver;)V

    .line 368
    iput-boolean v4, p0, Lmiui/view/ViewPager;->CC:Z

    .line 369
    iput-boolean v6, p0, Lmiui/view/ViewPager;->cm:Z

    .line 370
    iget v1, p0, Lmiui/view/ViewPager;->Cp:I

    if-ltz v1, :cond_5

    .line 371
    iget-object v1, p0, Lmiui/view/ViewPager;->Cn:Lmiui/view/PagerAdapter;

    iget-object v2, p0, Lmiui/view/ViewPager;->Cq:Landroid/os/Parcelable;

    invoke-virtual {v1, v2, v5}, Lmiui/view/PagerAdapter;->restoreState(Landroid/os/Parcelable;Ljava/lang/ClassLoader;)V

    .line 372
    iget v1, p0, Lmiui/view/ViewPager;->Cp:I

    invoke-virtual {p0, v1, v4, v6}, Lmiui/view/ViewPager;->a(IZZ)V

    .line 373
    const/4 v1, -0x1

    iput v1, p0, Lmiui/view/ViewPager;->Cp:I

    .line 374
    iput-object v5, p0, Lmiui/view/ViewPager;->Cq:Landroid/os/Parcelable;

    .line 380
    :cond_3
    :goto_1
    iget-object v1, p0, Lmiui/view/ViewPager;->Dn:Lmiui/view/ViewPager$b;

    if-eqz v1, :cond_4

    if-eq v0, p1, :cond_4

    .line 381
    iget-object v1, p0, Lmiui/view/ViewPager;->Dn:Lmiui/view/ViewPager$b;

    invoke-interface {v1, v0, p1}, Lmiui/view/ViewPager$b;->a(Lmiui/view/PagerAdapter;Lmiui/view/PagerAdapter;)V

    .line 383
    :cond_4
    return-void

    .line 376
    :cond_5
    invoke-virtual {p0}, Lmiui/view/ViewPager;->cS()V

    goto :goto_1
.end method

.method public setBottomMarginProgress(F)V
    .locals 1

    .prologue
    .line 2617
    iput p1, p0, Lmiui/view/ViewPager;->CW:F

    .line 2618
    const/4 v0, 0x1

    iput-boolean v0, p0, Lmiui/view/ViewPager;->CX:Z

    .line 2619
    invoke-virtual {p0}, Lmiui/view/ViewPager;->requestLayout()V

    .line 2620
    invoke-virtual {p0}, Lmiui/view/ViewPager;->invalidate()V

    .line 2621
    return-void
.end method

.method public setCurrentItem(I)V
    .locals 2

    .prologue
    const/4 v1, 0x0

    .line 417
    iput-boolean v1, p0, Lmiui/view/ViewPager;->CC:Z

    .line 418
    iget-boolean v0, p0, Lmiui/view/ViewPager;->cm:Z

    if-nez v0, :cond_0

    const/4 v0, 0x1

    :goto_0
    invoke-virtual {p0, p1, v0, v1}, Lmiui/view/ViewPager;->a(IZZ)V

    .line 419
    return-void

    :cond_0
    move v0, v1

    .line 418
    goto :goto_0
.end method

.method public setCurrentItem(IZ)V
    .locals 1

    .prologue
    const/4 v0, 0x0

    .line 428
    iput-boolean v0, p0, Lmiui/view/ViewPager;->CC:Z

    .line 429
    invoke-virtual {p0, p1, p2, v0}, Lmiui/view/ViewPager;->a(IZZ)V

    .line 430
    return-void
.end method

.method public setDraggable(Z)V
    .locals 0

    .prologue
    .line 2586
    iput-boolean p1, p0, Lmiui/view/ViewPager;->Do:Z

    .line 2587
    return-void
.end method

.method public setInternalPageChangeListener(Lmiui/view/ViewPager$OnPageChangeListener;)Lmiui/view/ViewPager$OnPageChangeListener;
    .locals 1

    .prologue
    .line 511
    iget-object v0, p0, Lmiui/view/ViewPager;->Dm:Lmiui/view/ViewPager$OnPageChangeListener;

    .line 512
    iput-object p1, p0, Lmiui/view/ViewPager;->Dm:Lmiui/view/ViewPager$OnPageChangeListener;

    .line 513
    return-object v0
.end method

.method public setOffscreenPageLimit(I)V
    .locals 4

    .prologue
    const/4 v0, 0x3

    .line 545
    if-ge p1, v0, :cond_0

    .line 546
    const-string v1, "ViewPager"

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "Requested offscreen page limit "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v2

    const-string v3, " too small; defaulting to "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-static {v1, v2}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    move p1, v0

    .line 550
    :cond_0
    iget v0, p0, Lmiui/view/ViewPager;->CE:I

    if-eq p1, v0, :cond_1

    .line 551
    iput p1, p0, Lmiui/view/ViewPager;->CE:I

    .line 552
    invoke-virtual {p0}, Lmiui/view/ViewPager;->cS()V

    .line 554
    :cond_1
    return-void
.end method

.method public setOnPageChangeListener(Lmiui/view/ViewPager$OnPageChangeListener;)V
    .locals 0

    .prologue
    .line 500
    iput-object p1, p0, Lmiui/view/ViewPager;->Dl:Lmiui/view/ViewPager$OnPageChangeListener;

    .line 501
    return-void
.end method

.method public setPageMargin(I)V
    .locals 2

    .prologue
    .line 565
    iget v0, p0, Lmiui/view/ViewPager;->Cs:I

    .line 566
    iput p1, p0, Lmiui/view/ViewPager;->Cs:I

    .line 568
    invoke-virtual {p0}, Lmiui/view/ViewPager;->getWidth()I

    move-result v1

    .line 569
    invoke-direct {p0, v1, v1, p1, v0}, Lmiui/view/ViewPager;->e(IIII)V

    .line 571
    invoke-virtual {p0}, Lmiui/view/ViewPager;->requestLayout()V

    .line 572
    return-void
.end method

.method public setPageMarginDrawable(I)V
    .locals 1

    .prologue
    .line 601
    invoke-virtual {p0}, Lmiui/view/ViewPager;->getContext()Landroid/content/Context;

    move-result-object v0

    invoke-virtual {v0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    invoke-virtual {v0, p1}, Landroid/content/res/Resources;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v0

    invoke-virtual {p0, v0}, Lmiui/view/ViewPager;->setPageMarginDrawable(Landroid/graphics/drawable/Drawable;)V

    .line 602
    return-void
.end method

.method public setPageMarginDrawable(Landroid/graphics/drawable/Drawable;)V
    .locals 1

    .prologue
    .line 589
    iput-object p1, p0, Lmiui/view/ViewPager;->Ct:Landroid/graphics/drawable/Drawable;

    .line 590
    if-eqz p1, :cond_0

    invoke-virtual {p0}, Lmiui/view/ViewPager;->refreshDrawableState()V

    .line 591
    :cond_0
    if-nez p1, :cond_1

    const/4 v0, 0x1

    :goto_0
    invoke-virtual {p0, v0}, Lmiui/view/ViewPager;->setWillNotDraw(Z)V

    .line 592
    invoke-virtual {p0}, Lmiui/view/ViewPager;->invalidate()V

    .line 593
    return-void

    .line 591
    :cond_1
    const/4 v0, 0x0

    goto :goto_0
.end method

.method smoothScrollTo(II)V
    .locals 1

    .prologue
    .line 635
    const/4 v0, 0x0

    invoke-virtual {p0, p1, p2, v0}, Lmiui/view/ViewPager;->h(III)V

    .line 636
    return-void
.end method

.method protected verifyDrawable(Landroid/graphics/drawable/Drawable;)Z
    .locals 1

    .prologue
    .line 606
    invoke-super {p0, p1}, Landroid/view/ViewGroup;->verifyDrawable(Landroid/graphics/drawable/Drawable;)Z

    move-result v0

    if-nez v0, :cond_0

    iget-object v0, p0, Lmiui/view/ViewPager;->Ct:Landroid/graphics/drawable/Drawable;

    if-ne p1, v0, :cond_1

    :cond_0
    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_1
    const/4 v0, 0x0

    goto :goto_0
.end method
