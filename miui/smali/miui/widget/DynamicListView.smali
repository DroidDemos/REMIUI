.class public Lmiui/widget/DynamicListView;
.super Landroid/widget/ListView;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lmiui/widget/DynamicListView$RearrangeListener;,
        Lmiui/widget/DynamicListView$OnItemRemoveListener;
    }
.end annotation


# static fields
.field private static final zA:Landroid/animation/TypeEvaluator;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Landroid/animation/TypeEvaluator",
            "<",
            "Landroid/graphics/Rect;",
            ">;"
        }
    .end annotation
.end field

.field private static final zB:I = 0x5

.field private static final zC:I = 0x32

.field private static final zz:I = 0xff


# instance fields
.field private Aa:I

.field private Ab:Landroid/widget/AbsListView$OnScrollListener;

.field private Ac:Landroid/animation/ObjectAnimator;

.field private Ad:Landroid/animation/ObjectAnimator;

.field private Ae:Lmiui/widget/DynamicListView$RearrangeListener;

.field private Af:Lmiui/widget/DynamicListView$OnItemRemoveListener;

.field private Ag:Ljava/util/Map;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map",
            "<",
            "Ljava/lang/Long;",
            "Ljava/lang/Integer;",
            ">;"
        }
    .end annotation
.end field

.field private Ah:Landroid/graphics/Bitmap;

.field private Ai:Landroid/animation/AnimatorListenerAdapter;

.field private Aj:Ljava/lang/Runnable;

.field private Ak:Landroid/widget/AbsListView$OnScrollListener;

.field private final INVALID_POINTER_ID:I

.field private aP:Landroid/graphics/Paint;

.field private cV:I

.field private final zD:I

.field private zE:F

.field private zF:J

.field private zG:I

.field private zH:I

.field private zI:I

.field private zJ:Z

.field private zK:Z

.field private zL:I

.field private zM:I

.field private zN:I

.field private zO:I

.field private zP:J

.field private zQ:J

.field private zR:J

.field private zS:Landroid/graphics/drawable/BitmapDrawable;

.field private zT:Landroid/graphics/Rect;

.field private zU:Landroid/graphics/Rect;

.field private zV:Landroid/graphics/Rect;

.field private zW:Landroid/graphics/Rect;

.field private zX:Z

.field private zY:I

.field private zZ:Landroid/graphics/drawable/Drawable;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .prologue
    .line 52
    new-instance v0, Lmiui/widget/o;

    invoke-direct {v0}, Lmiui/widget/o;-><init>()V

    sput-object v0, Lmiui/widget/DynamicListView;->zA:Landroid/animation/TypeEvaluator;

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;)V
    .locals 6

    .prologue
    const-wide/16 v4, -0x1

    const/4 v3, -0x1

    const/4 v2, 0x0

    .line 301
    invoke-direct {p0, p1}, Landroid/widget/ListView;-><init>(Landroid/content/Context;)V

    .line 76
    iput v3, p0, Lmiui/widget/DynamicListView;->zD:I

    .line 78
    iput v3, p0, Lmiui/widget/DynamicListView;->INVALID_POINTER_ID:I

    .line 80
    const/4 v0, 0x0

    iput v0, p0, Lmiui/widget/DynamicListView;->zE:F

    .line 82
    const-wide/16 v0, 0xc8

    iput-wide v0, p0, Lmiui/widget/DynamicListView;->zF:J

    .line 84
    iput v3, p0, Lmiui/widget/DynamicListView;->zG:I

    .line 86
    iput v3, p0, Lmiui/widget/DynamicListView;->zH:I

    .line 88
    iput v2, p0, Lmiui/widget/DynamicListView;->zI:I

    .line 90
    iput-boolean v2, p0, Lmiui/widget/DynamicListView;->zJ:Z

    .line 92
    iput-boolean v2, p0, Lmiui/widget/DynamicListView;->zK:Z

    .line 94
    iput v2, p0, Lmiui/widget/DynamicListView;->zL:I

    .line 100
    iput v2, p0, Lmiui/widget/DynamicListView;->zO:I

    .line 102
    iput-wide v4, p0, Lmiui/widget/DynamicListView;->zP:J

    .line 104
    iput-wide v4, p0, Lmiui/widget/DynamicListView;->zQ:J

    .line 106
    iput-wide v4, p0, Lmiui/widget/DynamicListView;->zR:J

    .line 114
    new-instance v0, Landroid/graphics/Rect;

    invoke-direct {v0}, Landroid/graphics/Rect;-><init>()V

    iput-object v0, p0, Lmiui/widget/DynamicListView;->zV:Landroid/graphics/Rect;

    .line 116
    new-instance v0, Landroid/graphics/Rect;

    invoke-direct {v0}, Landroid/graphics/Rect;-><init>()V

    iput-object v0, p0, Lmiui/widget/DynamicListView;->zW:Landroid/graphics/Rect;

    .line 118
    iput v3, p0, Lmiui/widget/DynamicListView;->cV:I

    .line 120
    iput-boolean v2, p0, Lmiui/widget/DynamicListView;->zX:Z

    .line 122
    iput v2, p0, Lmiui/widget/DynamicListView;->zY:I

    .line 138
    new-instance v0, Lmiui/util/ArrayMap;

    invoke-direct {v0}, Lmiui/util/ArrayMap;-><init>()V

    iput-object v0, p0, Lmiui/widget/DynamicListView;->Ag:Ljava/util/Map;

    .line 140
    new-instance v0, Landroid/graphics/Paint;

    invoke-direct {v0}, Landroid/graphics/Paint;-><init>()V

    iput-object v0, p0, Lmiui/widget/DynamicListView;->aP:Landroid/graphics/Paint;

    .line 144
    new-instance v0, Lmiui/widget/r;

    invoke-direct {v0, p0}, Lmiui/widget/r;-><init>(Lmiui/widget/DynamicListView;)V

    iput-object v0, p0, Lmiui/widget/DynamicListView;->Ai:Landroid/animation/AnimatorListenerAdapter;

    .line 181
    new-instance v0, Lmiui/widget/q;

    invoke-direct {v0, p0}, Lmiui/widget/q;-><init>(Lmiui/widget/DynamicListView;)V

    iput-object v0, p0, Lmiui/widget/DynamicListView;->Aj:Ljava/lang/Runnable;

    .line 200
    new-instance v0, Lmiui/widget/p;

    invoke-direct {v0, p0}, Lmiui/widget/p;-><init>(Lmiui/widget/DynamicListView;)V

    iput-object v0, p0, Lmiui/widget/DynamicListView;->Ak:Landroid/widget/AbsListView$OnScrollListener;

    .line 302
    invoke-direct {p0, p1}, Lmiui/widget/DynamicListView;->m(Landroid/content/Context;)V

    .line 303
    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 6

    .prologue
    const-wide/16 v4, -0x1

    const/4 v3, -0x1

    const/4 v2, 0x0

    .line 319
    invoke-direct {p0, p1, p2}, Landroid/widget/ListView;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    .line 76
    iput v3, p0, Lmiui/widget/DynamicListView;->zD:I

    .line 78
    iput v3, p0, Lmiui/widget/DynamicListView;->INVALID_POINTER_ID:I

    .line 80
    const/4 v0, 0x0

    iput v0, p0, Lmiui/widget/DynamicListView;->zE:F

    .line 82
    const-wide/16 v0, 0xc8

    iput-wide v0, p0, Lmiui/widget/DynamicListView;->zF:J

    .line 84
    iput v3, p0, Lmiui/widget/DynamicListView;->zG:I

    .line 86
    iput v3, p0, Lmiui/widget/DynamicListView;->zH:I

    .line 88
    iput v2, p0, Lmiui/widget/DynamicListView;->zI:I

    .line 90
    iput-boolean v2, p0, Lmiui/widget/DynamicListView;->zJ:Z

    .line 92
    iput-boolean v2, p0, Lmiui/widget/DynamicListView;->zK:Z

    .line 94
    iput v2, p0, Lmiui/widget/DynamicListView;->zL:I

    .line 100
    iput v2, p0, Lmiui/widget/DynamicListView;->zO:I

    .line 102
    iput-wide v4, p0, Lmiui/widget/DynamicListView;->zP:J

    .line 104
    iput-wide v4, p0, Lmiui/widget/DynamicListView;->zQ:J

    .line 106
    iput-wide v4, p0, Lmiui/widget/DynamicListView;->zR:J

    .line 114
    new-instance v0, Landroid/graphics/Rect;

    invoke-direct {v0}, Landroid/graphics/Rect;-><init>()V

    iput-object v0, p0, Lmiui/widget/DynamicListView;->zV:Landroid/graphics/Rect;

    .line 116
    new-instance v0, Landroid/graphics/Rect;

    invoke-direct {v0}, Landroid/graphics/Rect;-><init>()V

    iput-object v0, p0, Lmiui/widget/DynamicListView;->zW:Landroid/graphics/Rect;

    .line 118
    iput v3, p0, Lmiui/widget/DynamicListView;->cV:I

    .line 120
    iput-boolean v2, p0, Lmiui/widget/DynamicListView;->zX:Z

    .line 122
    iput v2, p0, Lmiui/widget/DynamicListView;->zY:I

    .line 138
    new-instance v0, Lmiui/util/ArrayMap;

    invoke-direct {v0}, Lmiui/util/ArrayMap;-><init>()V

    iput-object v0, p0, Lmiui/widget/DynamicListView;->Ag:Ljava/util/Map;

    .line 140
    new-instance v0, Landroid/graphics/Paint;

    invoke-direct {v0}, Landroid/graphics/Paint;-><init>()V

    iput-object v0, p0, Lmiui/widget/DynamicListView;->aP:Landroid/graphics/Paint;

    .line 144
    new-instance v0, Lmiui/widget/r;

    invoke-direct {v0, p0}, Lmiui/widget/r;-><init>(Lmiui/widget/DynamicListView;)V

    iput-object v0, p0, Lmiui/widget/DynamicListView;->Ai:Landroid/animation/AnimatorListenerAdapter;

    .line 181
    new-instance v0, Lmiui/widget/q;

    invoke-direct {v0, p0}, Lmiui/widget/q;-><init>(Lmiui/widget/DynamicListView;)V

    iput-object v0, p0, Lmiui/widget/DynamicListView;->Aj:Ljava/lang/Runnable;

    .line 200
    new-instance v0, Lmiui/widget/p;

    invoke-direct {v0, p0}, Lmiui/widget/p;-><init>(Lmiui/widget/DynamicListView;)V

    iput-object v0, p0, Lmiui/widget/DynamicListView;->Ak:Landroid/widget/AbsListView$OnScrollListener;

    .line 320
    invoke-direct {p0, p1}, Lmiui/widget/DynamicListView;->m(Landroid/content/Context;)V

    .line 321
    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V
    .locals 6

    .prologue
    const-wide/16 v4, -0x1

    const/4 v3, -0x1

    const/4 v2, 0x0

    .line 310
    invoke-direct {p0, p1, p2, p3}, Landroid/widget/ListView;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    .line 76
    iput v3, p0, Lmiui/widget/DynamicListView;->zD:I

    .line 78
    iput v3, p0, Lmiui/widget/DynamicListView;->INVALID_POINTER_ID:I

    .line 80
    const/4 v0, 0x0

    iput v0, p0, Lmiui/widget/DynamicListView;->zE:F

    .line 82
    const-wide/16 v0, 0xc8

    iput-wide v0, p0, Lmiui/widget/DynamicListView;->zF:J

    .line 84
    iput v3, p0, Lmiui/widget/DynamicListView;->zG:I

    .line 86
    iput v3, p0, Lmiui/widget/DynamicListView;->zH:I

    .line 88
    iput v2, p0, Lmiui/widget/DynamicListView;->zI:I

    .line 90
    iput-boolean v2, p0, Lmiui/widget/DynamicListView;->zJ:Z

    .line 92
    iput-boolean v2, p0, Lmiui/widget/DynamicListView;->zK:Z

    .line 94
    iput v2, p0, Lmiui/widget/DynamicListView;->zL:I

    .line 100
    iput v2, p0, Lmiui/widget/DynamicListView;->zO:I

    .line 102
    iput-wide v4, p0, Lmiui/widget/DynamicListView;->zP:J

    .line 104
    iput-wide v4, p0, Lmiui/widget/DynamicListView;->zQ:J

    .line 106
    iput-wide v4, p0, Lmiui/widget/DynamicListView;->zR:J

    .line 114
    new-instance v0, Landroid/graphics/Rect;

    invoke-direct {v0}, Landroid/graphics/Rect;-><init>()V

    iput-object v0, p0, Lmiui/widget/DynamicListView;->zV:Landroid/graphics/Rect;

    .line 116
    new-instance v0, Landroid/graphics/Rect;

    invoke-direct {v0}, Landroid/graphics/Rect;-><init>()V

    iput-object v0, p0, Lmiui/widget/DynamicListView;->zW:Landroid/graphics/Rect;

    .line 118
    iput v3, p0, Lmiui/widget/DynamicListView;->cV:I

    .line 120
    iput-boolean v2, p0, Lmiui/widget/DynamicListView;->zX:Z

    .line 122
    iput v2, p0, Lmiui/widget/DynamicListView;->zY:I

    .line 138
    new-instance v0, Lmiui/util/ArrayMap;

    invoke-direct {v0}, Lmiui/util/ArrayMap;-><init>()V

    iput-object v0, p0, Lmiui/widget/DynamicListView;->Ag:Ljava/util/Map;

    .line 140
    new-instance v0, Landroid/graphics/Paint;

    invoke-direct {v0}, Landroid/graphics/Paint;-><init>()V

    iput-object v0, p0, Lmiui/widget/DynamicListView;->aP:Landroid/graphics/Paint;

    .line 144
    new-instance v0, Lmiui/widget/r;

    invoke-direct {v0, p0}, Lmiui/widget/r;-><init>(Lmiui/widget/DynamicListView;)V

    iput-object v0, p0, Lmiui/widget/DynamicListView;->Ai:Landroid/animation/AnimatorListenerAdapter;

    .line 181
    new-instance v0, Lmiui/widget/q;

    invoke-direct {v0, p0}, Lmiui/widget/q;-><init>(Lmiui/widget/DynamicListView;)V

    iput-object v0, p0, Lmiui/widget/DynamicListView;->Aj:Ljava/lang/Runnable;

    .line 200
    new-instance v0, Lmiui/widget/p;

    invoke-direct {v0, p0}, Lmiui/widget/p;-><init>(Lmiui/widget/DynamicListView;)V

    iput-object v0, p0, Lmiui/widget/DynamicListView;->Ak:Landroid/widget/AbsListView$OnScrollListener;

    .line 311
    invoke-direct {p0, p1}, Lmiui/widget/DynamicListView;->m(Landroid/content/Context;)V

    .line 312
    return-void
.end method

.method private W(I)Landroid/view/View;
    .locals 2

    .prologue
    .line 519
    invoke-virtual {p0}, Lmiui/widget/DynamicListView;->getFirstVisiblePosition()I

    move-result v0

    .line 520
    if-lt p1, v0, :cond_0

    invoke-virtual {p0}, Lmiui/widget/DynamicListView;->getLastVisiblePosition()I

    move-result v1

    if-gt p1, v1, :cond_0

    .line 522
    sub-int v0, p1, v0

    invoke-virtual {p0, v0}, Lmiui/widget/DynamicListView;->getChildAt(I)Landroid/view/View;

    move-result-object v0

    .line 524
    :goto_0
    return-object v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method static synthetic a(Lmiui/widget/DynamicListView;I)I
    .locals 0

    .prologue
    .line 43
    iput p1, p0, Lmiui/widget/DynamicListView;->zY:I

    return p1
.end method

.method static synthetic a(Lmiui/widget/DynamicListView;)J
    .locals 2

    .prologue
    .line 43
    iget-wide v0, p0, Lmiui/widget/DynamicListView;->zQ:J

    return-wide v0
.end method

.method static synthetic a(Lmiui/widget/DynamicListView;J)J
    .locals 0

    .prologue
    .line 43
    iput-wide p1, p0, Lmiui/widget/DynamicListView;->zP:J

    return-wide p1
.end method

.method static synthetic a(Lmiui/widget/DynamicListView;Landroid/animation/ObjectAnimator;)Landroid/animation/ObjectAnimator;
    .locals 0

    .prologue
    .line 43
    iput-object p1, p0, Lmiui/widget/DynamicListView;->Ad:Landroid/animation/ObjectAnimator;

    return-object p1
.end method

.method static synthetic a(Lmiui/widget/DynamicListView;Landroid/graphics/Bitmap;)Landroid/graphics/Bitmap;
    .locals 0

    .prologue
    .line 43
    iput-object p1, p0, Lmiui/widget/DynamicListView;->Ah:Landroid/graphics/Bitmap;

    return-object p1
.end method

.method static synthetic a(Lmiui/widget/DynamicListView;Landroid/graphics/drawable/BitmapDrawable;)Landroid/graphics/drawable/BitmapDrawable;
    .locals 0

    .prologue
    .line 43
    iput-object p1, p0, Lmiui/widget/DynamicListView;->zS:Landroid/graphics/drawable/BitmapDrawable;

    return-object p1
.end method

.method private a(Ljava/util/List;)Ljava/util/List;
    .locals 3
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List",
            "<",
            "Ljava/lang/Integer;",
            ">;)",
            "Ljava/util/List",
            "<",
            "Landroid/view/View;",
            ">;"
        }
    .end annotation

    .prologue
    .line 534
    new-instance v1, Ljava/util/ArrayList;

    const/4 v0, 0x1

    invoke-direct {v1, v0}, Ljava/util/ArrayList;-><init>(I)V

    .line 536
    invoke-interface {p1}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v2

    :cond_0
    :goto_0
    invoke-interface {v2}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_1

    invoke-interface {v2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/Integer;

    .line 537
    invoke-virtual {v0}, Ljava/lang/Integer;->intValue()I

    move-result v0

    invoke-direct {p0, v0}, Lmiui/widget/DynamicListView;->W(I)Landroid/view/View;

    move-result-object v0

    .line 538
    if-eqz v0, :cond_0

    .line 539
    invoke-interface {v1, v0}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto :goto_0

    .line 542
    :cond_1
    return-object v1
.end method

.method private a(Ljava/util/List;Ljava/util/List;)V
    .locals 8
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List",
            "<",
            "Ljava/lang/Long;",
            ">;",
            "Ljava/util/List",
            "<",
            "Ljava/lang/Integer;",
            ">;)V"
        }
    .end annotation

    .prologue
    .line 942
    invoke-virtual {p0}, Lmiui/widget/DynamicListView;->getAdapter()Landroid/widget/ListAdapter;

    move-result-object v0

    check-cast v0, Landroid/widget/BaseAdapter;

    .line 943
    new-instance v2, Ljava/util/ArrayList;

    const/4 v1, 0x1

    invoke-direct {v2, v1}, Ljava/util/ArrayList;-><init>(I)V

    .line 945
    invoke-interface {p2}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v3

    :cond_0
    :goto_0
    invoke-interface {v3}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_1

    invoke-interface {v3}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/lang/Integer;

    .line 946
    invoke-virtual {v1}, Ljava/lang/Integer;->intValue()I

    move-result v1

    invoke-direct {p0, v1}, Lmiui/widget/DynamicListView;->W(I)Landroid/view/View;

    move-result-object v1

    .line 947
    if-eqz v1, :cond_0

    .line 948
    invoke-interface {v2, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto :goto_0

    .line 951
    :cond_1
    invoke-virtual {p0}, Lmiui/widget/DynamicListView;->getFirstVisiblePosition()I

    move-result v3

    .line 952
    iget-object v1, p0, Lmiui/widget/DynamicListView;->Ag:Ljava/util/Map;

    invoke-interface {v1}, Ljava/util/Map;->clear()V

    .line 953
    const/4 v1, 0x0

    :goto_1
    invoke-virtual {p0}, Lmiui/widget/DynamicListView;->getChildCount()I

    move-result v4

    if-ge v1, v4, :cond_3

    .line 954
    invoke-virtual {p0, v1}, Lmiui/widget/DynamicListView;->getChildAt(I)Landroid/view/View;

    move-result-object v4

    .line 955
    const/4 v5, 0x0

    invoke-virtual {v4, v5}, Landroid/view/View;->setTranslationX(F)V

    .line 956
    invoke-interface {v2, v4}, Ljava/util/List;->contains(Ljava/lang/Object;)Z

    move-result v5

    if-nez v5, :cond_2

    .line 957
    add-int v5, v3, v1

    invoke-virtual {v0, v5}, Landroid/widget/BaseAdapter;->getItemId(I)J

    move-result-wide v5

    .line 958
    iget-object v7, p0, Lmiui/widget/DynamicListView;->Ag:Ljava/util/Map;

    invoke-static {v5, v6}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v5

    invoke-virtual {v4}, Landroid/view/View;->getTop()I

    move-result v4

    invoke-static {v4}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v4

    invoke-interface {v7, v5, v4}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 953
    :cond_2
    add-int/lit8 v1, v1, 0x1

    goto :goto_1

    .line 962
    :cond_3
    iget-object v1, p0, Lmiui/widget/DynamicListView;->Af:Lmiui/widget/DynamicListView$OnItemRemoveListener;

    invoke-interface {v1, p1}, Lmiui/widget/DynamicListView$OnItemRemoveListener;->onItemRemove(Ljava/util/List;)V

    .line 963
    invoke-virtual {v0}, Landroid/widget/BaseAdapter;->notifyDataSetChanged()V

    .line 964
    invoke-virtual {p0}, Lmiui/widget/DynamicListView;->getViewTreeObserver()Landroid/view/ViewTreeObserver;

    move-result-object v1

    new-instance v2, Lmiui/widget/DynamicListView$1;

    invoke-direct {v2, p0, v0, p2}, Lmiui/widget/DynamicListView$1;-><init>(Lmiui/widget/DynamicListView;Landroid/widget/BaseAdapter;Ljava/util/List;)V

    invoke-virtual {v1, v2}, Landroid/view/ViewTreeObserver;->addOnPreDrawListener(Landroid/view/ViewTreeObserver$OnPreDrawListener;)V

    .line 1035
    return-void
.end method

.method static synthetic a(Lmiui/widget/DynamicListView;Ljava/util/List;Ljava/util/List;)V
    .locals 0

    .prologue
    .line 43
    invoke-direct {p0, p1, p2}, Lmiui/widget/DynamicListView;->a(Ljava/util/List;Ljava/util/List;)V

    return-void
.end method

.method static synthetic b(Lmiui/widget/DynamicListView;I)I
    .locals 1

    .prologue
    .line 43
    iget v0, p0, Lmiui/widget/DynamicListView;->zI:I

    add-int/2addr v0, p1

    iput v0, p0, Lmiui/widget/DynamicListView;->zI:I

    return v0
.end method

.method static synthetic b(Lmiui/widget/DynamicListView;J)J
    .locals 0

    .prologue
    .line 43
    iput-wide p1, p0, Lmiui/widget/DynamicListView;->zQ:J

    return-wide p1
.end method

.method static synthetic b(Lmiui/widget/DynamicListView;Landroid/animation/ObjectAnimator;)Landroid/animation/ObjectAnimator;
    .locals 0

    .prologue
    .line 43
    iput-object p1, p0, Lmiui/widget/DynamicListView;->Ac:Landroid/animation/ObjectAnimator;

    return-object p1
.end method

.method static synthetic b(Lmiui/widget/DynamicListView;)Lmiui/widget/DynamicListView$RearrangeListener;
    .locals 1

    .prologue
    .line 43
    iget-object v0, p0, Lmiui/widget/DynamicListView;->Ae:Lmiui/widget/DynamicListView$RearrangeListener;

    return-object v0
.end method

.method static synthetic c(Lmiui/widget/DynamicListView;J)J
    .locals 0

    .prologue
    .line 43
    iput-wide p1, p0, Lmiui/widget/DynamicListView;->zR:J

    return-wide p1
.end method

.method static synthetic c(Lmiui/widget/DynamicListView;)Z
    .locals 1

    .prologue
    .line 43
    iget-boolean v0, p0, Lmiui/widget/DynamicListView;->zK:Z

    return v0
.end method

.method private cG()V
    .locals 6

    .prologue
    const/high16 v4, 0x3f000000

    .line 379
    new-instance v0, Landroid/graphics/Rect;

    iget-object v1, p0, Lmiui/widget/DynamicListView;->zU:Landroid/graphics/Rect;

    invoke-direct {v0, v1}, Landroid/graphics/Rect;-><init>(Landroid/graphics/Rect;)V

    .line 380
    invoke-virtual {v0}, Landroid/graphics/Rect;->width()I

    move-result v1

    int-to-float v1, v1

    iget v2, p0, Lmiui/widget/DynamicListView;->zE:F

    mul-float/2addr v1, v2

    mul-float/2addr v1, v4

    float-to-int v1, v1

    .line 382
    invoke-virtual {v0}, Landroid/graphics/Rect;->height()I

    move-result v2

    int-to-float v2, v2

    iget v3, p0, Lmiui/widget/DynamicListView;->zE:F

    mul-float/2addr v2, v3

    mul-float/2addr v2, v4

    float-to-int v2, v2

    .line 384
    iget v3, v0, Landroid/graphics/Rect;->left:I

    iget v4, v0, Landroid/graphics/Rect;->top:I

    sub-int/2addr v4, v2

    iget v5, v0, Landroid/graphics/Rect;->right:I

    mul-int/lit8 v1, v1, 0x2

    add-int/2addr v1, v5

    iget v5, v0, Landroid/graphics/Rect;->bottom:I

    add-int/2addr v2, v5

    invoke-virtual {v0, v3, v4, v1, v2}, Landroid/graphics/Rect;->set(IIII)V

    .line 387
    iget-object v1, p0, Lmiui/widget/DynamicListView;->zT:Landroid/graphics/Rect;

    invoke-virtual {v1, v0}, Landroid/graphics/Rect;->set(Landroid/graphics/Rect;)V

    .line 388
    iget-object v1, p0, Lmiui/widget/DynamicListView;->zU:Landroid/graphics/Rect;

    invoke-virtual {v1, v0}, Landroid/graphics/Rect;->set(Landroid/graphics/Rect;)V

    .line 389
    const-string v1, "HoverCellBounds"

    sget-object v2, Lmiui/widget/DynamicListView;->zA:Landroid/animation/TypeEvaluator;

    const/4 v3, 0x1

    new-array v3, v3, [Ljava/lang/Object;

    const/4 v4, 0x0

    aput-object v0, v3, v4

    invoke-static {p0, v1, v2, v3}, Landroid/animation/ObjectAnimator;->ofObject(Ljava/lang/Object;Ljava/lang/String;Landroid/animation/TypeEvaluator;[Ljava/lang/Object;)Landroid/animation/ObjectAnimator;

    move-result-object v0

    iput-object v0, p0, Lmiui/widget/DynamicListView;->Ac:Landroid/animation/ObjectAnimator;

    .line 392
    iget-object v0, p0, Lmiui/widget/DynamicListView;->Ac:Landroid/animation/ObjectAnimator;

    iget-wide v1, p0, Lmiui/widget/DynamicListView;->zF:J

    invoke-virtual {v0, v1, v2}, Landroid/animation/ObjectAnimator;->setDuration(J)Landroid/animation/ObjectAnimator;

    .line 393
    iget-object v0, p0, Lmiui/widget/DynamicListView;->Ac:Landroid/animation/ObjectAnimator;

    new-instance v1, Lmiui/widget/DynamicListView$8;

    invoke-direct {v1, p0}, Lmiui/widget/DynamicListView$8;-><init>(Lmiui/widget/DynamicListView;)V

    invoke-virtual {v0, v1}, Landroid/animation/ObjectAnimator;->addUpdateListener(Landroid/animation/ValueAnimator$AnimatorUpdateListener;)V

    .line 402
    iget-object v0, p0, Lmiui/widget/DynamicListView;->Ac:Landroid/animation/ObjectAnimator;

    new-instance v1, Lmiui/widget/DynamicListView$7;

    invoke-direct {v1, p0}, Lmiui/widget/DynamicListView$7;-><init>(Lmiui/widget/DynamicListView;)V

    invoke-virtual {v0, v1}, Landroid/animation/ObjectAnimator;->addListener(Landroid/animation/Animator$AnimatorListener;)V

    .line 415
    iget-object v0, p0, Lmiui/widget/DynamicListView;->Ac:Landroid/animation/ObjectAnimator;

    invoke-virtual {v0}, Landroid/animation/ObjectAnimator;->start()V

    .line 416
    return-void
.end method

.method private cH()V
    .locals 11

    .prologue
    const/4 v2, 0x1

    const/4 v7, 0x0

    .line 635
    iget v0, p0, Lmiui/widget/DynamicListView;->zG:I

    iget v1, p0, Lmiui/widget/DynamicListView;->zH:I

    sub-int v5, v0, v1

    .line 636
    iget-object v0, p0, Lmiui/widget/DynamicListView;->zU:Landroid/graphics/Rect;

    iget v0, v0, Landroid/graphics/Rect;->top:I

    iget v1, p0, Lmiui/widget/DynamicListView;->zI:I

    add-int/2addr v0, v1

    add-int v3, v0, v5

    .line 637
    iget-object v0, p0, Lmiui/widget/DynamicListView;->zT:Landroid/graphics/Rect;

    invoke-virtual {v0}, Landroid/graphics/Rect;->height()I

    move-result v0

    div-int/lit8 v4, v0, 0x2

    .line 638
    iget-wide v0, p0, Lmiui/widget/DynamicListView;->zR:J

    invoke-virtual {p0, v0, v1}, Lmiui/widget/DynamicListView;->getViewForId(J)Landroid/view/View;

    move-result-object v0

    .line 639
    iget-wide v8, p0, Lmiui/widget/DynamicListView;->zQ:J

    invoke-virtual {p0, v8, v9}, Lmiui/widget/DynamicListView;->getViewForId(J)Landroid/view/View;

    move-result-object v8

    .line 640
    iget-wide v9, p0, Lmiui/widget/DynamicListView;->zP:J

    invoke-virtual {p0, v9, v10}, Lmiui/widget/DynamicListView;->getViewForId(J)Landroid/view/View;

    move-result-object v1

    .line 641
    if-eqz v0, :cond_4

    add-int v6, v3, v4

    invoke-virtual {v0}, Landroid/view/View;->getTop()I

    move-result v9

    if-le v6, v9, :cond_4

    move v6, v2

    .line 643
    :goto_0
    if-eqz v1, :cond_5

    sub-int/2addr v3, v4

    invoke-virtual {v1}, Landroid/view/View;->getTop()I

    move-result v4

    if-ge v3, v4, :cond_5

    .line 645
    :goto_1
    if-nez v6, :cond_0

    if-eqz v2, :cond_3

    .line 646
    :cond_0
    if-eqz v6, :cond_6

    iget-wide v3, p0, Lmiui/widget/DynamicListView;->zR:J

    .line 647
    :goto_2
    if-eqz v6, :cond_1

    move-object v1, v0

    .line 648
    :cond_1
    invoke-virtual {p0, v8}, Lmiui/widget/DynamicListView;->getPositionForView(Landroid/view/View;)I

    move-result v0

    .line 650
    iget-object v2, p0, Lmiui/widget/DynamicListView;->Ae:Lmiui/widget/DynamicListView$RearrangeListener;

    if-eqz v2, :cond_2

    .line 651
    iget-object v2, p0, Lmiui/widget/DynamicListView;->Ae:Lmiui/widget/DynamicListView$RearrangeListener;

    invoke-virtual {p0, v1}, Lmiui/widget/DynamicListView;->getPositionForView(Landroid/view/View;)I

    move-result v6

    invoke-interface {v2, v0, v6}, Lmiui/widget/DynamicListView$RearrangeListener;->onOrderChanged(II)V

    .line 655
    :cond_2
    invoke-virtual {p0}, Lmiui/widget/DynamicListView;->getAdapter()Landroid/widget/ListAdapter;

    move-result-object v0

    check-cast v0, Landroid/widget/BaseAdapter;

    invoke-virtual {v0}, Landroid/widget/BaseAdapter;->notifyDataSetChanged()V

    .line 656
    iget v0, p0, Lmiui/widget/DynamicListView;->zG:I

    iput v0, p0, Lmiui/widget/DynamicListView;->zH:I

    .line 657
    invoke-virtual {v1}, Landroid/view/View;->getTop()I

    move-result v6

    .line 659
    invoke-virtual {v8, v7}, Landroid/view/View;->setVisibility(I)V

    .line 660
    const/4 v0, 0x4

    invoke-virtual {v1, v0}, Landroid/view/View;->setVisibility(I)V

    .line 662
    iget-wide v0, p0, Lmiui/widget/DynamicListView;->zQ:J

    invoke-direct {p0, v0, v1}, Lmiui/widget/DynamicListView;->g(J)V

    .line 663
    invoke-virtual {p0}, Lmiui/widget/DynamicListView;->getViewTreeObserver()Landroid/view/ViewTreeObserver;

    move-result-object v2

    .line 664
    new-instance v0, Lmiui/widget/DynamicListView$6;

    move-object v1, p0

    invoke-direct/range {v0 .. v6}, Lmiui/widget/DynamicListView$6;-><init>(Lmiui/widget/DynamicListView;Landroid/view/ViewTreeObserver;JII)V

    invoke-virtual {v2, v0}, Landroid/view/ViewTreeObserver;->addOnPreDrawListener(Landroid/view/ViewTreeObserver$OnPreDrawListener;)V

    .line 681
    :cond_3
    return-void

    :cond_4
    move v6, v7

    .line 641
    goto :goto_0

    :cond_5
    move v2, v7

    .line 643
    goto :goto_1

    .line 646
    :cond_6
    iget-wide v3, p0, Lmiui/widget/DynamicListView;->zP:J

    goto :goto_2
.end method

.method private cI()V
    .locals 8

    .prologue
    const/4 v7, 0x1

    const/4 v6, 0x0

    .line 688
    iget-wide v0, p0, Lmiui/widget/DynamicListView;->zQ:J

    invoke-virtual {p0, v0, v1}, Lmiui/widget/DynamicListView;->getViewForId(J)Landroid/view/View;

    move-result-object v0

    .line 689
    iget-boolean v1, p0, Lmiui/widget/DynamicListView;->zJ:Z

    if-nez v1, :cond_0

    iget-boolean v1, p0, Lmiui/widget/DynamicListView;->zX:Z

    if-eqz v1, :cond_2

    .line 690
    :cond_0
    iput-boolean v6, p0, Lmiui/widget/DynamicListView;->zJ:Z

    .line 691
    iput-boolean v6, p0, Lmiui/widget/DynamicListView;->zX:Z

    .line 692
    iput-boolean v6, p0, Lmiui/widget/DynamicListView;->zK:Z

    .line 693
    const/4 v1, -0x1

    iput v1, p0, Lmiui/widget/DynamicListView;->cV:I

    .line 699
    iget v1, p0, Lmiui/widget/DynamicListView;->zY:I

    if-eqz v1, :cond_1

    .line 700
    iput-boolean v7, p0, Lmiui/widget/DynamicListView;->zX:Z

    .line 729
    :goto_0
    return-void

    .line 703
    :cond_1
    iget-object v1, p0, Lmiui/widget/DynamicListView;->zT:Landroid/graphics/Rect;

    iget-object v2, p0, Lmiui/widget/DynamicListView;->zU:Landroid/graphics/Rect;

    iget v2, v2, Landroid/graphics/Rect;->left:I

    invoke-virtual {v0}, Landroid/view/View;->getTop()I

    move-result v3

    invoke-virtual {v1, v2, v3}, Landroid/graphics/Rect;->offsetTo(II)V

    .line 705
    iget-object v1, p0, Lmiui/widget/DynamicListView;->zT:Landroid/graphics/Rect;

    iget-object v2, p0, Lmiui/widget/DynamicListView;->zU:Landroid/graphics/Rect;

    iget v2, v2, Landroid/graphics/Rect;->left:I

    invoke-virtual {v0}, Landroid/view/View;->getTop()I

    move-result v3

    iget-object v4, p0, Lmiui/widget/DynamicListView;->zU:Landroid/graphics/Rect;

    iget v4, v4, Landroid/graphics/Rect;->left:I

    invoke-virtual {v0}, Landroid/view/View;->getWidth()I

    move-result v5

    add-int/2addr v4, v5

    invoke-virtual {v0}, Landroid/view/View;->getTop()I

    move-result v5

    invoke-virtual {v0}, Landroid/view/View;->getHeight()I

    move-result v0

    add-int/2addr v0, v5

    invoke-virtual {v1, v2, v3, v4, v0}, Landroid/graphics/Rect;->set(IIII)V

    .line 710
    const-string v0, "HoverCellBounds"

    sget-object v1, Lmiui/widget/DynamicListView;->zA:Landroid/animation/TypeEvaluator;

    new-array v2, v7, [Ljava/lang/Object;

    iget-object v3, p0, Lmiui/widget/DynamicListView;->zT:Landroid/graphics/Rect;

    aput-object v3, v2, v6

    invoke-static {p0, v0, v1, v2}, Landroid/animation/ObjectAnimator;->ofObject(Ljava/lang/Object;Ljava/lang/String;Landroid/animation/TypeEvaluator;[Ljava/lang/Object;)Landroid/animation/ObjectAnimator;

    move-result-object v0

    iput-object v0, p0, Lmiui/widget/DynamicListView;->Ad:Landroid/animation/ObjectAnimator;

    .line 713
    iget-object v0, p0, Lmiui/widget/DynamicListView;->Ad:Landroid/animation/ObjectAnimator;

    iget-wide v1, p0, Lmiui/widget/DynamicListView;->zF:J

    invoke-virtual {v0, v1, v2}, Landroid/animation/ObjectAnimator;->setDuration(J)Landroid/animation/ObjectAnimator;

    .line 714
    iget-object v0, p0, Lmiui/widget/DynamicListView;->Ad:Landroid/animation/ObjectAnimator;

    new-instance v1, Lmiui/widget/DynamicListView$5;

    invoke-direct {v1, p0}, Lmiui/widget/DynamicListView$5;-><init>(Lmiui/widget/DynamicListView;)V

    invoke-virtual {v0, v1}, Landroid/animation/ObjectAnimator;->addUpdateListener(Landroid/animation/ValueAnimator$AnimatorUpdateListener;)V

    .line 724
    iget-object v0, p0, Lmiui/widget/DynamicListView;->Ad:Landroid/animation/ObjectAnimator;

    iget-object v1, p0, Lmiui/widget/DynamicListView;->Ai:Landroid/animation/AnimatorListenerAdapter;

    invoke-virtual {v0, v1}, Landroid/animation/ObjectAnimator;->addListener(Landroid/animation/Animator$AnimatorListener;)V

    .line 725
    iget-object v0, p0, Lmiui/widget/DynamicListView;->Ad:Landroid/animation/ObjectAnimator;

    invoke-virtual {v0}, Landroid/animation/ObjectAnimator;->start()V

    goto :goto_0

    .line 727
    :cond_2
    invoke-direct {p0}, Lmiui/widget/DynamicListView;->cJ()V

    goto :goto_0
.end method

.method private cJ()V
    .locals 5

    .prologue
    const-wide/16 v3, -0x1

    const/4 v2, 0x0

    .line 735
    iget-wide v0, p0, Lmiui/widget/DynamicListView;->zQ:J

    invoke-virtual {p0, v0, v1}, Lmiui/widget/DynamicListView;->getViewForId(J)Landroid/view/View;

    move-result-object v0

    .line 736
    iget-boolean v1, p0, Lmiui/widget/DynamicListView;->zJ:Z

    if-eqz v1, :cond_0

    .line 737
    iput-wide v3, p0, Lmiui/widget/DynamicListView;->zP:J

    .line 738
    iput-wide v3, p0, Lmiui/widget/DynamicListView;->zQ:J

    .line 739
    iput-wide v3, p0, Lmiui/widget/DynamicListView;->zR:J

    .line 740
    invoke-virtual {v0, v2}, Landroid/view/View;->setVisibility(I)V

    .line 741
    const/4 v0, 0x0

    iput-object v0, p0, Lmiui/widget/DynamicListView;->zS:Landroid/graphics/drawable/BitmapDrawable;

    .line 742
    invoke-virtual {p0}, Lmiui/widget/DynamicListView;->invalidate()V

    .line 744
    :cond_0
    iput-boolean v2, p0, Lmiui/widget/DynamicListView;->zJ:Z

    .line 745
    iput-boolean v2, p0, Lmiui/widget/DynamicListView;->zK:Z

    .line 746
    const/4 v0, -0x1

    iput v0, p0, Lmiui/widget/DynamicListView;->cV:I

    .line 747
    return-void
.end method

.method private cK()V
    .locals 1

    .prologue
    .line 754
    iget-object v0, p0, Lmiui/widget/DynamicListView;->zT:Landroid/graphics/Rect;

    invoke-virtual {p0, v0}, Lmiui/widget/DynamicListView;->handleMobileCellScroll(Landroid/graphics/Rect;)Z

    move-result v0

    iput-boolean v0, p0, Lmiui/widget/DynamicListView;->zK:Z

    .line 755
    return-void
.end method

.method static synthetic d(Lmiui/widget/DynamicListView;)I
    .locals 1

    .prologue
    .line 43
    iget v0, p0, Lmiui/widget/DynamicListView;->zL:I

    return v0
.end method

.method static synthetic d(Lmiui/widget/DynamicListView;J)V
    .locals 0

    .prologue
    .line 43
    invoke-direct {p0, p1, p2}, Lmiui/widget/DynamicListView;->g(J)V

    return-void
.end method

.method static synthetic e(Lmiui/widget/DynamicListView;)Landroid/widget/AbsListView$OnScrollListener;
    .locals 1

    .prologue
    .line 43
    iget-object v0, p0, Lmiui/widget/DynamicListView;->Ab:Landroid/widget/AbsListView$OnScrollListener;

    return-object v0
.end method

.method static synthetic f(Lmiui/widget/DynamicListView;)Z
    .locals 1

    .prologue
    .line 43
    iget-boolean v0, p0, Lmiui/widget/DynamicListView;->zJ:Z

    return v0
.end method

.method private g(Landroid/view/View;)Landroid/graphics/drawable/BitmapDrawable;
    .locals 7

    .prologue
    .line 446
    invoke-virtual {p1}, Landroid/view/View;->getWidth()I

    move-result v0

    .line 447
    invoke-virtual {p1}, Landroid/view/View;->getHeight()I

    move-result v1

    .line 448
    invoke-virtual {p1}, Landroid/view/View;->getTop()I

    move-result v2

    .line 449
    invoke-virtual {p1}, Landroid/view/View;->getLeft()I

    move-result v3

    .line 450
    invoke-direct {p0, p1}, Lmiui/widget/DynamicListView;->h(Landroid/view/View;)Landroid/graphics/Bitmap;

    move-result-object v4

    .line 451
    new-instance v5, Landroid/graphics/drawable/BitmapDrawable;

    invoke-virtual {p0}, Lmiui/widget/DynamicListView;->getResources()Landroid/content/res/Resources;

    move-result-object v6

    invoke-direct {v5, v6, v4}, Landroid/graphics/drawable/BitmapDrawable;-><init>(Landroid/content/res/Resources;Landroid/graphics/Bitmap;)V

    .line 452
    new-instance v4, Landroid/graphics/Rect;

    add-int/2addr v0, v3

    add-int/2addr v1, v2

    invoke-direct {v4, v3, v2, v0, v1}, Landroid/graphics/Rect;-><init>(IIII)V

    iput-object v4, p0, Lmiui/widget/DynamicListView;->zU:Landroid/graphics/Rect;

    .line 453
    new-instance v0, Landroid/graphics/Rect;

    iget-object v1, p0, Lmiui/widget/DynamicListView;->zU:Landroid/graphics/Rect;

    invoke-direct {v0, v1}, Landroid/graphics/Rect;-><init>(Landroid/graphics/Rect;)V

    iput-object v0, p0, Lmiui/widget/DynamicListView;->zT:Landroid/graphics/Rect;

    .line 454
    iget-object v0, p0, Lmiui/widget/DynamicListView;->zT:Landroid/graphics/Rect;

    invoke-virtual {v5, v0}, Landroid/graphics/drawable/BitmapDrawable;->setBounds(Landroid/graphics/Rect;)V

    .line 455
    return-object v5
.end method

.method private g(J)V
    .locals 4

    .prologue
    .line 476
    invoke-virtual {p0, p1, p2}, Lmiui/widget/DynamicListView;->getPositionForId(J)I

    move-result v1

    .line 477
    invoke-virtual {p0}, Lmiui/widget/DynamicListView;->getAdapter()Landroid/widget/ListAdapter;

    move-result-object v0

    check-cast v0, Landroid/widget/BaseAdapter;

    .line 478
    add-int/lit8 v2, v1, -0x1

    invoke-virtual {v0, v2}, Landroid/widget/BaseAdapter;->getItemId(I)J

    move-result-wide v2

    iput-wide v2, p0, Lmiui/widget/DynamicListView;->zP:J

    .line 479
    add-int/lit8 v1, v1, 0x1

    invoke-virtual {v0, v1}, Landroid/widget/BaseAdapter;->getItemId(I)J

    move-result-wide v0

    iput-wide v0, p0, Lmiui/widget/DynamicListView;->zR:J

    .line 480
    return-void
.end method

.method static synthetic g(Lmiui/widget/DynamicListView;)V
    .locals 0

    .prologue
    .line 43
    invoke-direct {p0}, Lmiui/widget/DynamicListView;->cK()V

    return-void
.end method

.method private h(Landroid/view/View;)Landroid/graphics/Bitmap;
    .locals 3

    .prologue
    .line 462
    invoke-virtual {p1}, Landroid/view/View;->getWidth()I

    move-result v0

    invoke-virtual {p1}, Landroid/view/View;->getHeight()I

    move-result v1

    sget-object v2, Landroid/graphics/Bitmap$Config;->ARGB_8888:Landroid/graphics/Bitmap$Config;

    invoke-static {v0, v1, v2}, Landroid/graphics/Bitmap;->createBitmap(IILandroid/graphics/Bitmap$Config;)Landroid/graphics/Bitmap;

    move-result-object v0

    .line 464
    new-instance v1, Landroid/graphics/Canvas;

    invoke-direct {v1, v0}, Landroid/graphics/Canvas;-><init>(Landroid/graphics/Bitmap;)V

    .line 465
    invoke-virtual {p1, v1}, Landroid/view/View;->draw(Landroid/graphics/Canvas;)V

    .line 466
    return-object v0
.end method

.method static synthetic h(Lmiui/widget/DynamicListView;)Z
    .locals 1

    .prologue
    .line 43
    iget-boolean v0, p0, Lmiui/widget/DynamicListView;->zX:Z

    return v0
.end method

.method static synthetic i(Lmiui/widget/DynamicListView;)V
    .locals 0

    .prologue
    .line 43
    invoke-direct {p0}, Lmiui/widget/DynamicListView;->cI()V

    return-void
.end method

.method static synthetic j(Lmiui/widget/DynamicListView;)V
    .locals 0

    .prologue
    .line 43
    invoke-direct {p0}, Lmiui/widget/DynamicListView;->cH()V

    return-void
.end method

.method static synthetic k(Lmiui/widget/DynamicListView;)Landroid/graphics/drawable/Drawable;
    .locals 1

    .prologue
    .line 43
    iget-object v0, p0, Lmiui/widget/DynamicListView;->zZ:Landroid/graphics/drawable/Drawable;

    return-object v0
.end method

.method static synthetic l(Lmiui/widget/DynamicListView;)J
    .locals 2

    .prologue
    .line 43
    iget-wide v0, p0, Lmiui/widget/DynamicListView;->zF:J

    return-wide v0
.end method

.method static synthetic m(Lmiui/widget/DynamicListView;)Landroid/graphics/Paint;
    .locals 1

    .prologue
    .line 43
    iget-object v0, p0, Lmiui/widget/DynamicListView;->aP:Landroid/graphics/Paint;

    return-object v0
.end method

.method private m(Landroid/content/Context;)V
    .locals 4

    .prologue
    .line 324
    iget-object v0, p0, Lmiui/widget/DynamicListView;->Ak:Landroid/widget/AbsListView$OnScrollListener;

    invoke-super {p0, v0}, Landroid/widget/ListView;->setOnScrollListener(Landroid/widget/AbsListView$OnScrollListener;)V

    .line 325
    invoke-virtual {p0}, Lmiui/widget/DynamicListView;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    .line 326
    invoke-virtual {v0}, Landroid/content/res/Resources;->getDisplayMetrics()Landroid/util/DisplayMetrics;

    move-result-object v1

    .line 327
    const/high16 v2, 0x40a00000

    iget v3, v1, Landroid/util/DisplayMetrics;->density:F

    mul-float/2addr v2, v3

    float-to-int v2, v2

    iput v2, p0, Lmiui/widget/DynamicListView;->zN:I

    .line 329
    const/high16 v2, 0x3fc00000

    iget v3, p0, Lmiui/widget/DynamicListView;->zN:I

    int-to-float v3, v3

    mul-float/2addr v2, v3

    float-to-int v2, v2

    iput v2, p0, Lmiui/widget/DynamicListView;->zM:I

    .line 330
    const/high16 v2, 0x42480000

    iget v1, v1, Landroid/util/DisplayMetrics;->density:F

    mul-float/2addr v1, v2

    float-to-int v1, v1

    iput v1, p0, Lmiui/widget/DynamicListView;->zO:I

    .line 331
    sget v1, Lcom/miui/internal/R$drawable;->dynamic_listview_dragging_item_shadow:I

    invoke-virtual {v0, v1}, Landroid/content/res/Resources;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v0

    iput-object v0, p0, Lmiui/widget/DynamicListView;->zZ:Landroid/graphics/drawable/Drawable;

    .line 333
    iget-object v0, p0, Lmiui/widget/DynamicListView;->zZ:Landroid/graphics/drawable/Drawable;

    invoke-virtual {v0}, Landroid/graphics/drawable/Drawable;->getIntrinsicHeight()I

    move-result v0

    iput v0, p0, Lmiui/widget/DynamicListView;->Aa:I

    .line 334
    return-void
.end method

.method static synthetic n(Lmiui/widget/DynamicListView;)Ljava/util/Map;
    .locals 1

    .prologue
    .line 43
    iget-object v0, p0, Lmiui/widget/DynamicListView;->Ag:Ljava/util/Map;

    return-object v0
.end method


# virtual methods
.method protected dispatchDraw(Landroid/graphics/Canvas;)V
    .locals 3

    .prologue
    const/4 v2, 0x0

    .line 552
    invoke-super {p0, p1}, Landroid/widget/ListView;->dispatchDraw(Landroid/graphics/Canvas;)V

    .line 553
    iget-object v0, p0, Lmiui/widget/DynamicListView;->zS:Landroid/graphics/drawable/BitmapDrawable;

    if-eqz v0, :cond_0

    .line 554
    iget-object v0, p0, Lmiui/widget/DynamicListView;->zZ:Landroid/graphics/drawable/Drawable;

    invoke-virtual {v0, p1}, Landroid/graphics/drawable/Drawable;->draw(Landroid/graphics/Canvas;)V

    .line 555
    iget-object v0, p0, Lmiui/widget/DynamicListView;->zS:Landroid/graphics/drawable/BitmapDrawable;

    invoke-virtual {v0, p1}, Landroid/graphics/drawable/BitmapDrawable;->draw(Landroid/graphics/Canvas;)V

    .line 557
    :cond_0
    iget-object v0, p0, Lmiui/widget/DynamicListView;->Ah:Landroid/graphics/Bitmap;

    if-eqz v0, :cond_1

    .line 558
    iget-object v0, p0, Lmiui/widget/DynamicListView;->Ah:Landroid/graphics/Bitmap;

    iget-object v1, p0, Lmiui/widget/DynamicListView;->aP:Landroid/graphics/Paint;

    invoke-virtual {p1, v0, v2, v2, v1}, Landroid/graphics/Canvas;->drawBitmap(Landroid/graphics/Bitmap;FFLandroid/graphics/Paint;)V

    .line 560
    :cond_1
    return-void
.end method

.method public getHoverCellBounds()Landroid/graphics/Rect;
    .locals 1

    .prologue
    .line 422
    iget-object v0, p0, Lmiui/widget/DynamicListView;->zS:Landroid/graphics/drawable/BitmapDrawable;

    invoke-virtual {v0}, Landroid/graphics/drawable/BitmapDrawable;->getBounds()Landroid/graphics/Rect;

    move-result-object v0

    return-object v0
.end method

.method public getLastStateAlpha()I
    .locals 1

    .prologue
    .line 921
    iget-object v0, p0, Lmiui/widget/DynamicListView;->aP:Landroid/graphics/Paint;

    invoke-virtual {v0}, Landroid/graphics/Paint;->getAlpha()I

    move-result v0

    return v0
.end method

.method public getPositionForId(J)I
    .locals 1

    .prologue
    .line 503
    invoke-virtual {p0, p1, p2}, Lmiui/widget/DynamicListView;->getViewForId(J)Landroid/view/View;

    move-result-object v0

    .line 504
    if-nez v0, :cond_0

    .line 505
    const/4 v0, -0x1

    .line 507
    :goto_0
    return v0

    :cond_0
    invoke-virtual {p0, v0}, Lmiui/widget/DynamicListView;->getPositionForView(Landroid/view/View;)I

    move-result v0

    goto :goto_0
.end method

.method public getViewForId(J)Landroid/view/View;
    .locals 6

    .prologue
    .line 486
    invoke-virtual {p0}, Lmiui/widget/DynamicListView;->getFirstVisiblePosition()I

    move-result v3

    .line 487
    invoke-virtual {p0}, Lmiui/widget/DynamicListView;->getAdapter()Landroid/widget/ListAdapter;

    move-result-object v0

    check-cast v0, Landroid/widget/BaseAdapter;

    .line 488
    const/4 v1, 0x0

    :goto_0
    invoke-virtual {p0}, Lmiui/widget/DynamicListView;->getChildCount()I

    move-result v2

    if-ge v1, v2, :cond_1

    .line 489
    invoke-virtual {p0, v1}, Lmiui/widget/DynamicListView;->getChildAt(I)Landroid/view/View;

    move-result-object v2

    .line 490
    add-int v4, v3, v1

    .line 491
    invoke-virtual {v0, v4}, Landroid/widget/BaseAdapter;->getItemId(I)J

    move-result-wide v4

    .line 492
    cmp-long v4, v4, p1

    if-nez v4, :cond_0

    move-object v0, v2

    .line 496
    :goto_1
    return-object v0

    .line 488
    :cond_0
    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    .line 496
    :cond_1
    const/4 v0, 0x0

    goto :goto_1
.end method

.method public handleMobileCellScroll(Landroid/graphics/Rect;)Z
    .locals 11

    .prologue
    const-wide/16 v9, 0xa

    const/4 v0, 0x1

    .line 763
    invoke-virtual {p0}, Lmiui/widget/DynamicListView;->computeVerticalScrollOffset()I

    move-result v1

    .line 764
    invoke-virtual {p0}, Lmiui/widget/DynamicListView;->getHeight()I

    move-result v2

    .line 765
    invoke-virtual {p0}, Lmiui/widget/DynamicListView;->computeVerticalScrollExtent()I

    move-result v3

    .line 766
    invoke-virtual {p0}, Lmiui/widget/DynamicListView;->computeVerticalScrollRange()I

    move-result v4

    .line 767
    iget v5, p1, Landroid/graphics/Rect;->top:I

    .line 768
    invoke-virtual {p1}, Landroid/graphics/Rect;->height()I

    move-result v6

    .line 769
    iget v7, p0, Lmiui/widget/DynamicListView;->zO:I

    mul-int/lit8 v7, v7, 0x2

    if-gt v5, v7, :cond_1

    if-lez v1, :cond_1

    .line 771
    iget v1, p0, Lmiui/widget/DynamicListView;->zN:I

    neg-int v1, v1

    iput v1, p0, Lmiui/widget/DynamicListView;->zL:I

    .line 772
    iget v1, p0, Lmiui/widget/DynamicListView;->zO:I

    if-gt v5, v1, :cond_0

    .line 773
    iget v1, p0, Lmiui/widget/DynamicListView;->zM:I

    neg-int v1, v1

    iput v1, p0, Lmiui/widget/DynamicListView;->zL:I

    .line 775
    :cond_0
    iget-object v1, p0, Lmiui/widget/DynamicListView;->Aj:Ljava/lang/Runnable;

    invoke-virtual {p0, v1, v9, v10}, Lmiui/widget/DynamicListView;->postDelayed(Ljava/lang/Runnable;J)Z

    .line 789
    :goto_0
    return v0

    .line 778
    :cond_1
    add-int v7, v5, v6

    iget v8, p0, Lmiui/widget/DynamicListView;->zO:I

    mul-int/lit8 v8, v8, 0x2

    sub-int v8, v2, v8

    if-lt v7, v8, :cond_3

    add-int/2addr v1, v3

    if-ge v1, v4, :cond_3

    .line 781
    iget v1, p0, Lmiui/widget/DynamicListView;->zN:I

    iput v1, p0, Lmiui/widget/DynamicListView;->zL:I

    .line 782
    add-int v1, v5, v6

    iget v3, p0, Lmiui/widget/DynamicListView;->zO:I

    sub-int/2addr v2, v3

    if-lt v1, v2, :cond_2

    .line 783
    iget v1, p0, Lmiui/widget/DynamicListView;->zM:I

    iput v1, p0, Lmiui/widget/DynamicListView;->zL:I

    .line 785
    :cond_2
    iget-object v1, p0, Lmiui/widget/DynamicListView;->Aj:Ljava/lang/Runnable;

    invoke-virtual {p0, v1, v9, v10}, Lmiui/widget/DynamicListView;->postDelayed(Ljava/lang/Runnable;J)Z

    goto :goto_0

    .line 788
    :cond_3
    iget-object v0, p0, Lmiui/widget/DynamicListView;->Aj:Ljava/lang/Runnable;

    invoke-virtual {p0, v0}, Lmiui/widget/DynamicListView;->removeCallbacks(Ljava/lang/Runnable;)Z

    .line 789
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public onTouchEvent(Landroid/view/MotionEvent;)Z
    .locals 4

    .prologue
    const/4 v1, 0x0

    .line 564
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getAction()I

    move-result v0

    and-int/lit16 v0, v0, 0xff

    packed-switch v0, :pswitch_data_0

    .line 622
    :cond_0
    :goto_0
    :pswitch_0
    invoke-super {p0, p1}, Landroid/widget/ListView;->onTouchEvent(Landroid/view/MotionEvent;)Z

    move-result v1

    :goto_1
    return v1

    .line 566
    :pswitch_1
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getY()F

    move-result v0

    float-to-int v0, v0

    iput v0, p0, Lmiui/widget/DynamicListView;->zH:I

    .line 567
    invoke-virtual {p1, v1}, Landroid/view/MotionEvent;->getPointerId(I)I

    move-result v0

    iput v0, p0, Lmiui/widget/DynamicListView;->cV:I

    goto :goto_0

    .line 570
    :pswitch_2
    iget v0, p0, Lmiui/widget/DynamicListView;->cV:I

    const/4 v2, -0x1

    if-eq v0, v2, :cond_0

    .line 573
    iget v0, p0, Lmiui/widget/DynamicListView;->cV:I

    invoke-virtual {p1, v0}, Landroid/view/MotionEvent;->findPointerIndex(I)I

    move-result v0

    .line 574
    invoke-virtual {p1, v0}, Landroid/view/MotionEvent;->getY(I)F

    move-result v0

    float-to-int v0, v0

    iput v0, p0, Lmiui/widget/DynamicListView;->zG:I

    .line 575
    iget v0, p0, Lmiui/widget/DynamicListView;->zG:I

    iget v2, p0, Lmiui/widget/DynamicListView;->zH:I

    sub-int/2addr v0, v2

    .line 576
    iget-boolean v2, p0, Lmiui/widget/DynamicListView;->zJ:Z

    if-eqz v2, :cond_0

    .line 577
    iget-object v2, p0, Lmiui/widget/DynamicListView;->Ac:Landroid/animation/ObjectAnimator;

    if-eqz v2, :cond_1

    iget-object v2, p0, Lmiui/widget/DynamicListView;->Ac:Landroid/animation/ObjectAnimator;

    invoke-virtual {v2}, Landroid/animation/ObjectAnimator;->isRunning()Z

    move-result v2

    if-eqz v2, :cond_1

    .line 578
    iget-object v2, p0, Lmiui/widget/DynamicListView;->Ac:Landroid/animation/ObjectAnimator;

    invoke-virtual {v2}, Landroid/animation/ObjectAnimator;->end()V

    .line 581
    :cond_1
    iget-object v2, p0, Lmiui/widget/DynamicListView;->zU:Landroid/graphics/Rect;

    iget v2, v2, Landroid/graphics/Rect;->top:I

    add-int/2addr v0, v2

    iget v2, p0, Lmiui/widget/DynamicListView;->zI:I

    add-int/2addr v0, v2

    .line 583
    if-gez v0, :cond_3

    move v0, v1

    .line 589
    :cond_2
    :goto_2
    iget-object v2, p0, Lmiui/widget/DynamicListView;->zT:Landroid/graphics/Rect;

    iget-object v3, p0, Lmiui/widget/DynamicListView;->zU:Landroid/graphics/Rect;

    iget v3, v3, Landroid/graphics/Rect;->left:I

    invoke-virtual {v2, v3, v0}, Landroid/graphics/Rect;->offsetTo(II)V

    .line 591
    iget-object v0, p0, Lmiui/widget/DynamicListView;->zT:Landroid/graphics/Rect;

    invoke-virtual {p0, v0}, Lmiui/widget/DynamicListView;->setHoverCellBounds(Landroid/graphics/Rect;)V

    .line 592
    invoke-direct {p0}, Lmiui/widget/DynamicListView;->cH()V

    .line 593
    iput-boolean v1, p0, Lmiui/widget/DynamicListView;->zK:Z

    .line 594
    invoke-direct {p0}, Lmiui/widget/DynamicListView;->cK()V

    goto :goto_1

    .line 585
    :cond_3
    invoke-virtual {p0}, Lmiui/widget/DynamicListView;->getHeight()I

    move-result v2

    iget-object v3, p0, Lmiui/widget/DynamicListView;->zT:Landroid/graphics/Rect;

    invoke-virtual {v3}, Landroid/graphics/Rect;->height()I

    move-result v3

    sub-int/2addr v2, v3

    if-le v0, v2, :cond_2

    .line 586
    invoke-virtual {p0}, Lmiui/widget/DynamicListView;->getHeight()I

    move-result v0

    iget-object v2, p0, Lmiui/widget/DynamicListView;->zT:Landroid/graphics/Rect;

    invoke-virtual {v2}, Landroid/graphics/Rect;->height()I

    move-result v2

    sub-int/2addr v0, v2

    goto :goto_2

    .line 599
    :pswitch_3
    invoke-direct {p0}, Lmiui/widget/DynamicListView;->cI()V

    goto :goto_0

    .line 602
    :pswitch_4
    invoke-direct {p0}, Lmiui/widget/DynamicListView;->cJ()V

    goto/16 :goto_0

    .line 611
    :pswitch_5
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getAction()I

    move-result v0

    const v1, 0xff00

    and-int/2addr v0, v1

    shr-int/lit8 v0, v0, 0x8

    .line 614
    invoke-virtual {p1, v0}, Landroid/view/MotionEvent;->getPointerId(I)I

    move-result v0

    .line 615
    iget v1, p0, Lmiui/widget/DynamicListView;->cV:I

    if-ne v0, v1, :cond_0

    .line 616
    invoke-direct {p0}, Lmiui/widget/DynamicListView;->cI()V

    goto/16 :goto_0

    .line 564
    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_1
        :pswitch_3
        :pswitch_2
        :pswitch_4
        :pswitch_0
        :pswitch_0
        :pswitch_5
    .end packed-switch
.end method

.method public removeItems(Ljava/util/List;)V
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List",
            "<",
            "Ljava/lang/Long;",
            ">;)V"
        }
    .end annotation

    .prologue
    .line 809
    const/4 v0, 0x0

    invoke-virtual {p0, p1, v0}, Lmiui/widget/DynamicListView;->removeItems(Ljava/util/List;Ljava/util/List;)V

    .line 810
    return-void
.end method

.method public removeItems(Ljava/util/List;Ljava/util/List;)V
    .locals 17
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List",
            "<",
            "Ljava/lang/Long;",
            ">;",
            "Ljava/util/List",
            "<",
            "Ljava/lang/Long;",
            ">;)V"
        }
    .end annotation

    .prologue
    .line 820
    new-instance v5, Ljava/util/ArrayList;

    move-object/from16 v0, p1

    invoke-direct {v5, v0}, Ljava/util/ArrayList;-><init>(Ljava/util/Collection;)V

    .line 821
    new-instance v6, Ljava/util/ArrayList;

    invoke-interface/range {p1 .. p1}, Ljava/util/List;->size()I

    move-result v2

    invoke-direct {v6, v2}, Ljava/util/ArrayList;-><init>(I)V

    .line 822
    new-instance v4, Ljava/util/ArrayList;

    invoke-direct {v4}, Ljava/util/ArrayList;-><init>()V

    .line 823
    const/4 v3, 0x0

    .line 824
    invoke-interface {v5}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v7

    :cond_0
    :goto_0
    invoke-interface {v7}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_7

    invoke-interface {v7}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/Long;

    .line 825
    invoke-virtual {v2}, Ljava/lang/Long;->longValue()J

    move-result-wide v8

    move-object/from16 v0, p0

    invoke-virtual {v0, v8, v9}, Lmiui/widget/DynamicListView;->getPositionForId(J)I

    move-result v8

    .line 826
    const/4 v9, -0x1

    if-eq v8, v9, :cond_1

    .line 827
    invoke-static {v8}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v9

    invoke-interface {v6, v9}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 828
    if-eqz p2, :cond_0

    move-object/from16 v0, p2

    invoke-interface {v0, v2}, Ljava/util/List;->contains(Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_0

    .line 830
    invoke-static {v8}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    invoke-interface {v4, v2}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto :goto_0

    .line 833
    :cond_1
    const/4 v2, 0x1

    .line 837
    :goto_1
    if-eqz v2, :cond_3

    .line 841
    move-object/from16 v0, p0

    move-object/from16 v1, p0

    invoke-direct {v0, v1}, Lmiui/widget/DynamicListView;->h(Landroid/view/View;)Landroid/graphics/Bitmap;

    move-result-object v2

    move-object/from16 v0, p0

    iput-object v2, v0, Lmiui/widget/DynamicListView;->Ah:Landroid/graphics/Bitmap;

    .line 842
    move-object/from16 v0, p0

    iget-object v2, v0, Lmiui/widget/DynamicListView;->Af:Lmiui/widget/DynamicListView$OnItemRemoveListener;

    move-object/from16 v0, p1

    invoke-interface {v2, v0}, Lmiui/widget/DynamicListView$OnItemRemoveListener;->onItemRemove(Ljava/util/List;)V

    .line 843
    invoke-virtual/range {p0 .. p0}, Lmiui/widget/DynamicListView;->getAdapter()Landroid/widget/ListAdapter;

    move-result-object v2

    check-cast v2, Landroid/widget/BaseAdapter;

    invoke-virtual {v2}, Landroid/widget/BaseAdapter;->notifyDataSetChanged()V

    .line 844
    const-string v2, "LastStateAlpha"

    const/4 v3, 0x1

    new-array v3, v3, [I

    const/4 v4, 0x0

    const/4 v5, 0x0

    aput v5, v3, v4

    move-object/from16 v0, p0

    invoke-static {v0, v2, v3}, Landroid/animation/ObjectAnimator;->ofInt(Ljava/lang/Object;Ljava/lang/String;[I)Landroid/animation/ObjectAnimator;

    move-result-object v2

    .line 846
    move-object/from16 v0, p0

    iget-wide v3, v0, Lmiui/widget/DynamicListView;->zF:J

    invoke-virtual {v2, v3, v4}, Landroid/animation/ObjectAnimator;->setDuration(J)Landroid/animation/ObjectAnimator;

    .line 847
    invoke-virtual {v2}, Landroid/animation/ObjectAnimator;->start()V

    .line 848
    new-instance v3, Lmiui/widget/DynamicListView$4;

    move-object/from16 v0, p0

    invoke-direct {v3, v0}, Lmiui/widget/DynamicListView$4;-><init>(Lmiui/widget/DynamicListView;)V

    invoke-virtual {v2, v3}, Landroid/animation/ObjectAnimator;->addListener(Landroid/animation/Animator$AnimatorListener;)V

    .line 915
    :cond_2
    :goto_2
    return-void

    .line 858
    :cond_3
    invoke-static {v6}, Ljava/util/Collections;->sort(Ljava/util/List;)V

    .line 859
    move-object/from16 v0, p0

    invoke-direct {v0, v6}, Lmiui/widget/DynamicListView;->a(Ljava/util/List;)Ljava/util/List;

    move-result-object v7

    .line 860
    move-object/from16 v0, p0

    invoke-direct {v0, v4}, Lmiui/widget/DynamicListView;->a(Ljava/util/List;)Ljava/util/List;

    move-result-object v8

    .line 861
    invoke-interface {v7}, Ljava/util/List;->isEmpty()Z

    move-result v2

    if-nez v2, :cond_2

    .line 862
    const/4 v2, 0x0

    move-object/from16 v0, p0

    invoke-virtual {v0, v2}, Lmiui/widget/DynamicListView;->setEnabled(Z)V

    .line 863
    new-instance v9, Landroid/animation/AnimatorSet;

    invoke-direct {v9}, Landroid/animation/AnimatorSet;-><init>()V

    .line 864
    new-instance v10, Ljava/util/ArrayList;

    invoke-interface {v7}, Ljava/util/List;->size()I

    move-result v2

    invoke-direct {v10, v2}, Ljava/util/ArrayList;-><init>(I)V

    .line 865
    new-instance v11, Ljava/util/ArrayList;

    invoke-interface {v7}, Ljava/util/List;->size()I

    move-result v2

    invoke-direct {v11, v2}, Ljava/util/ArrayList;-><init>(I)V

    .line 867
    invoke-interface {v7}, Ljava/util/List;->size()I

    move-result v12

    .line 869
    const/4 v2, 0x0

    move v4, v2

    :goto_3
    if-ge v4, v12, :cond_5

    .line 870
    invoke-interface {v7, v4}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Landroid/view/View;

    .line 871
    invoke-interface {v8, v2}, Ljava/util/List;->contains(Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_4

    invoke-virtual {v2}, Landroid/view/View;->getWidth()I

    move-result v3

    neg-int v3, v3

    .line 874
    :goto_4
    sget-object v13, Landroid/view/View;->TRANSLATION_X:Landroid/util/Property;

    const/4 v14, 0x1

    new-array v14, v14, [F

    const/4 v15, 0x0

    int-to-float v3, v3

    aput v3, v14, v15

    invoke-static {v2, v13, v14}, Landroid/animation/ObjectAnimator;->ofFloat(Ljava/lang/Object;Landroid/util/Property;[F)Landroid/animation/ObjectAnimator;

    move-result-object v3

    .line 877
    new-instance v13, Lmiui/widget/DynamicListView$3;

    move-object/from16 v0, p0

    invoke-direct {v13, v0, v2}, Lmiui/widget/DynamicListView$3;-><init>(Lmiui/widget/DynamicListView;Landroid/view/View;)V

    invoke-virtual {v3, v13}, Landroid/animation/ObjectAnimator;->addListener(Landroid/animation/Animator$AnimatorListener;)V

    .line 896
    invoke-interface {v10, v3}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 897
    move-object/from16 v0, p0

    iget-wide v13, v0, Lmiui/widget/DynamicListView;->zF:J

    invoke-virtual {v3, v13, v14}, Landroid/animation/ObjectAnimator;->setDuration(J)Landroid/animation/ObjectAnimator;

    .line 898
    const/4 v2, 0x2

    new-array v2, v2, [F

    fill-array-data v2, :array_0

    invoke-static {v2}, Landroid/animation/ValueAnimator;->ofFloat([F)Landroid/animation/ValueAnimator;

    move-result-object v2

    .line 899
    move-object/from16 v0, p0

    iget-wide v13, v0, Lmiui/widget/DynamicListView;->zF:J

    const-wide/16 v15, 0x3

    div-long/2addr v13, v15

    int-to-long v15, v4

    mul-long/2addr v13, v15

    invoke-virtual {v2, v13, v14}, Landroid/animation/ValueAnimator;->setDuration(J)Landroid/animation/ValueAnimator;

    .line 900
    invoke-interface {v11, v2}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 869
    add-int/lit8 v2, v4, 0x1

    move v4, v2

    goto :goto_3

    .line 871
    :cond_4
    invoke-virtual {v2}, Landroid/view/View;->getWidth()I

    move-result v3

    goto :goto_4

    .line 902
    :cond_5
    invoke-virtual {v9, v11}, Landroid/animation/AnimatorSet;->playTogether(Ljava/util/Collection;)V

    .line 903
    const/4 v2, 0x0

    move v3, v2

    :goto_5
    if-ge v3, v12, :cond_6

    .line 904
    invoke-interface {v10, v3}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Landroid/animation/Animator;

    invoke-virtual {v9, v2}, Landroid/animation/AnimatorSet;->play(Landroid/animation/Animator;)Landroid/animation/AnimatorSet$Builder;

    move-result-object v4

    invoke-interface {v11, v3}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Landroid/animation/Animator;

    invoke-virtual {v4, v2}, Landroid/animation/AnimatorSet$Builder;->after(Landroid/animation/Animator;)Landroid/animation/AnimatorSet$Builder;

    .line 903
    add-int/lit8 v2, v3, 0x1

    move v3, v2

    goto :goto_5

    .line 906
    :cond_6
    new-instance v2, Lmiui/widget/DynamicListView$2;

    move-object/from16 v0, p0

    invoke-direct {v2, v0, v5, v6}, Lmiui/widget/DynamicListView$2;-><init>(Lmiui/widget/DynamicListView;Ljava/util/List;Ljava/util/List;)V

    invoke-virtual {v9, v2}, Landroid/animation/AnimatorSet;->addListener(Landroid/animation/Animator$AnimatorListener;)V

    .line 913
    invoke-virtual {v9}, Landroid/animation/AnimatorSet;->start()V

    goto/16 :goto_2

    :cond_7
    move v2, v3

    goto/16 :goto_1

    .line 898
    nop

    :array_0
    .array-data 4
        0x0
        0x3f800000
    .end array-data
.end method

.method public setDuration(J)V
    .locals 0

    .prologue
    .line 342
    iput-wide p1, p0, Lmiui/widget/DynamicListView;->zF:J

    .line 343
    return-void
.end method

.method public setHoverCellBounds(Landroid/graphics/Rect;)V
    .locals 6

    .prologue
    .line 429
    iget-object v0, p0, Lmiui/widget/DynamicListView;->zW:Landroid/graphics/Rect;

    iget-object v1, p0, Lmiui/widget/DynamicListView;->zV:Landroid/graphics/Rect;

    invoke-virtual {v0, v1}, Landroid/graphics/Rect;->set(Landroid/graphics/Rect;)V

    .line 430
    iget-object v0, p0, Lmiui/widget/DynamicListView;->zS:Landroid/graphics/drawable/BitmapDrawable;

    invoke-virtual {v0, p1}, Landroid/graphics/drawable/BitmapDrawable;->setBounds(Landroid/graphics/Rect;)V

    .line 431
    iget-object v0, p0, Lmiui/widget/DynamicListView;->zV:Landroid/graphics/Rect;

    iget v1, p1, Landroid/graphics/Rect;->left:I

    iget v2, p1, Landroid/graphics/Rect;->top:I

    iget v3, p0, Lmiui/widget/DynamicListView;->Aa:I

    div-int/lit8 v3, v3, 0x2

    sub-int/2addr v2, v3

    iget v3, p1, Landroid/graphics/Rect;->right:I

    iget v4, p1, Landroid/graphics/Rect;->bottom:I

    iget v5, p0, Lmiui/widget/DynamicListView;->Aa:I

    div-int/lit8 v5, v5, 0x2

    add-int/2addr v4, v5

    invoke-virtual {v0, v1, v2, v3, v4}, Landroid/graphics/Rect;->set(IIII)V

    .line 435
    iget-object v0, p0, Lmiui/widget/DynamicListView;->zZ:Landroid/graphics/drawable/Drawable;

    iget-object v1, p0, Lmiui/widget/DynamicListView;->zV:Landroid/graphics/Rect;

    invoke-virtual {v0, v1}, Landroid/graphics/drawable/Drawable;->setBounds(Landroid/graphics/Rect;)V

    .line 436
    iget-object v0, p0, Lmiui/widget/DynamicListView;->zW:Landroid/graphics/Rect;

    iget-object v1, p0, Lmiui/widget/DynamicListView;->zV:Landroid/graphics/Rect;

    invoke-virtual {v0, v1}, Landroid/graphics/Rect;->union(Landroid/graphics/Rect;)V

    .line 437
    iget-object v0, p0, Lmiui/widget/DynamicListView;->zW:Landroid/graphics/Rect;

    invoke-virtual {p0, v0}, Lmiui/widget/DynamicListView;->invalidate(Landroid/graphics/Rect;)V

    .line 438
    return-void
.end method

.method public setLastStateAlpha(I)V
    .locals 1

    .prologue
    .line 928
    iget-object v0, p0, Lmiui/widget/DynamicListView;->aP:Landroid/graphics/Paint;

    invoke-virtual {v0, p1}, Landroid/graphics/Paint;->setAlpha(I)V

    .line 929
    invoke-virtual {p0}, Lmiui/widget/DynamicListView;->invalidate()V

    .line 930
    return-void
.end method

.method public setOnItemRemoveListener(Lmiui/widget/DynamicListView$OnItemRemoveListener;)V
    .locals 0

    .prologue
    .line 1043
    iput-object p1, p0, Lmiui/widget/DynamicListView;->Af:Lmiui/widget/DynamicListView$OnItemRemoveListener;

    .line 1044
    return-void
.end method

.method public setOnScrollListener(Landroid/widget/AbsListView$OnScrollListener;)V
    .locals 0

    .prologue
    .line 800
    iput-object p1, p0, Lmiui/widget/DynamicListView;->Ab:Landroid/widget/AbsListView$OnScrollListener;

    .line 801
    return-void
.end method

.method public setRearrangeListener(Lmiui/widget/DynamicListView$RearrangeListener;)V
    .locals 0

    .prologue
    .line 1052
    iput-object p1, p0, Lmiui/widget/DynamicListView;->Ae:Lmiui/widget/DynamicListView$RearrangeListener;

    .line 1053
    return-void
.end method

.method public setScaleFactor(F)V
    .locals 0

    .prologue
    .line 352
    iput p1, p0, Lmiui/widget/DynamicListView;->zE:F

    .line 353
    return-void
.end method

.method public startDragging(I)V
    .locals 3

    .prologue
    .line 361
    iget-object v0, p0, Lmiui/widget/DynamicListView;->Ad:Landroid/animation/ObjectAnimator;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lmiui/widget/DynamicListView;->Ad:Landroid/animation/ObjectAnimator;

    invoke-virtual {v0}, Landroid/animation/ObjectAnimator;->isRunning()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 362
    iget-object v0, p0, Lmiui/widget/DynamicListView;->Ad:Landroid/animation/ObjectAnimator;

    invoke-virtual {v0}, Landroid/animation/ObjectAnimator;->end()V

    .line 363
    iget-object v0, p0, Lmiui/widget/DynamicListView;->Ai:Landroid/animation/AnimatorListenerAdapter;

    iget-object v1, p0, Lmiui/widget/DynamicListView;->Ad:Landroid/animation/ObjectAnimator;

    invoke-virtual {v0, v1}, Landroid/animation/AnimatorListenerAdapter;->onAnimationEnd(Landroid/animation/Animator;)V

    .line 365
    :cond_0
    const/4 v0, 0x0

    iput v0, p0, Lmiui/widget/DynamicListView;->zI:I

    .line 366
    invoke-direct {p0, p1}, Lmiui/widget/DynamicListView;->W(I)Landroid/view/View;

    move-result-object v0

    .line 367
    invoke-virtual {p0}, Lmiui/widget/DynamicListView;->getAdapter()Landroid/widget/ListAdapter;

    move-result-object v1

    invoke-interface {v1, p1}, Landroid/widget/ListAdapter;->getItemId(I)J

    move-result-wide v1

    iput-wide v1, p0, Lmiui/widget/DynamicListView;->zQ:J

    .line 368
    invoke-direct {p0, v0}, Lmiui/widget/DynamicListView;->g(Landroid/view/View;)Landroid/graphics/drawable/BitmapDrawable;

    move-result-object v1

    iput-object v1, p0, Lmiui/widget/DynamicListView;->zS:Landroid/graphics/drawable/BitmapDrawable;

    .line 369
    invoke-direct {p0}, Lmiui/widget/DynamicListView;->cG()V

    .line 370
    const/4 v1, 0x4

    invoke-virtual {v0, v1}, Landroid/view/View;->setVisibility(I)V

    .line 371
    const/4 v0, 0x1

    iput-boolean v0, p0, Lmiui/widget/DynamicListView;->zJ:Z

    .line 372
    iget-wide v0, p0, Lmiui/widget/DynamicListView;->zQ:J

    invoke-direct {p0, v0, v1}, Lmiui/widget/DynamicListView;->g(J)V

    .line 373
    iget-object v0, p0, Lmiui/widget/DynamicListView;->Ae:Lmiui/widget/DynamicListView$RearrangeListener;

    if-eqz v0, :cond_1

    .line 374
    iget-object v0, p0, Lmiui/widget/DynamicListView;->Ae:Lmiui/widget/DynamicListView$RearrangeListener;

    invoke-interface {v0}, Lmiui/widget/DynamicListView$RearrangeListener;->onDragStart()V

    .line 376
    :cond_1
    return-void
.end method
