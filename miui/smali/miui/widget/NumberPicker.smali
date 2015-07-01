.class public Lmiui/widget/NumberPicker;
.super Landroid/widget/LinearLayout;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lmiui/widget/NumberPicker$d;,
        Lmiui/widget/NumberPicker$c;,
        Lmiui/widget/NumberPicker$CustomEditText;,
        Lmiui/widget/NumberPicker$f;,
        Lmiui/widget/NumberPicker$a;,
        Lmiui/widget/NumberPicker$e;,
        Lmiui/widget/NumberPicker$b;,
        Lmiui/widget/NumberPicker$Formatter;,
        Lmiui/widget/NumberPicker$OnScrollListener;,
        Lmiui/widget/NumberPicker$OnValueChangeListener;
    }
.end annotation


# static fields
.field static final DA:I = 0x5

.field private static final DB:J = 0x12cL

.field private static final DC:I = 0x2

.field private static final DD:I = 0x8

.field private static final DF:I = 0x320

.field private static final DG:I = 0x12c

.field private static final DH:F = 0.9f

.field private static final DI:I = 0x2

.field private static final DL:I = 0x30

.field private static final DN:I

.field private static final DP:I = -0x1

.field static final DS:Lmiui/widget/NumberPicker$Formatter;

.field private static final EV:[I

.field private static final Ff:F = 45.0f

.field private static final Fh:I = 0xca

.field private static final Fm:[C


# instance fields
.field private DQ:I

.field private DR:I

.field private final DT:Landroid/widget/EditText;

.field private final DU:I

.field private final DV:I

.field private final DW:I

.field private final DX:I

.field private DY:I

.field private EA:I

.field private EB:I

.field private ED:Z

.field private final EF:Z

.field private final EH:I

.field private EI:Z

.field private EJ:Z

.field private EK:I

.field private EL:I

.field private EM:I

.field private EN:Z

.field private EO:Z

.field private EP:Lmiui/widget/NumberPicker$d;

.field private final EQ:Lmiui/widget/NumberPicker$e;

.field private EU:I

.field private EW:Landroid/graphics/Paint;

.field private EX:I

.field private EY:F

.field private EZ:I

.field private final Ea:Z

.field private Eb:I

.field private Ec:[Ljava/lang/String;

.field private Ed:I

.field private Ee:I

.field private Ef:I

.field private Eg:Lmiui/widget/NumberPicker$OnValueChangeListener;

.field private Eh:Lmiui/widget/NumberPicker$OnScrollListener;

.field private Ei:Lmiui/widget/NumberPicker$Formatter;

.field private Ej:J

.field private final Ek:Landroid/util/SparseArray;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Landroid/util/SparseArray",
            "<",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation
.end field

.field private final El:[I

.field private final Em:Landroid/graphics/Paint;

.field private final En:Landroid/graphics/drawable/Drawable;

.field private Eo:I

.field private Ep:I

.field private Eq:I

.field private final Er:Landroid/widget/Scroller;

.field private final Es:Landroid/widget/Scroller;

.field private Et:I

.field private Eu:Lmiui/widget/NumberPicker$a;

.field private Ev:Lmiui/widget/NumberPicker$f;

.field private Ew:Lmiui/widget/NumberPicker$c;

.field private Ex:F

.field private Ey:J

.field private Ez:F

.field private Fa:I

.field private Fb:I

.field private Fc:I

.field private Fd:I

.field private Fe:F

.field private Fg:I

.field private Fi:Ljava/lang/CharSequence;

.field private Fj:Landroid/media/SoundPool;

.field private Fk:I

.field private Fl:F

.field private bR:Landroid/view/VelocityTracker;

.field private cT:I

.field private final jX:I

.field private zY:I


# direct methods
.method static constructor <clinit>()V
    .locals 3

    .prologue
    .line 103
    sget v0, Lcom/miui/internal/R$layout;->numberpicker_layout:I

    sput v0, Lmiui/widget/NumberPicker;->DN:I

    .line 121
    new-instance v0, Lmiui/widget/g;

    invoke-direct {v0}, Lmiui/widget/g;-><init>()V

    sput-object v0, Lmiui/widget/NumberPicker;->DS:Lmiui/widget/NumberPicker$Formatter;

    .line 408
    const/4 v0, 0x1

    new-array v0, v0, [I

    const/4 v1, 0x0

    const v2, 0x10100a7

    aput v2, v0, v1

    sput-object v0, Lmiui/widget/NumberPicker;->EV:[I

    .line 1842
    const/16 v0, 0xa

    new-array v0, v0, [C

    fill-array-data v0, :array_0

    sput-object v0, Lmiui/widget/NumberPicker;->Fm:[C

    return-void

    :array_0
    .array-data 2
        0x30s
        0x31s
        0x32s
        0x33s
        0x34s
        0x35s
        0x36s
        0x37s
        0x38s
        0x39s
    .end array-data
.end method

.method public constructor <init>(Landroid/content/Context;)V
    .locals 1

    .prologue
    .line 492
    const/4 v0, 0x0

    invoke-direct {p0, p1, v0}, Lmiui/widget/NumberPicker;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    .line 493
    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 1

    .prologue
    .line 502
    sget v0, Lcom/miui/internal/R$attr;->numberPickerStyle:I

    invoke-direct {p0, p1, p2, v0}, Lmiui/widget/NumberPicker;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    .line 503
    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V
    .locals 8

    .prologue
    const/high16 v5, 0x3f800000

    const/4 v7, 0x2

    const/4 v2, 0x0

    const/4 v6, -0x1

    const/4 v1, 0x1

    .line 513
    invoke-direct {p0, p1, p2, p3}, Landroid/widget/LinearLayout;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    .line 110
    iput v1, p0, Lmiui/widget/NumberPicker;->DQ:I

    .line 111
    iput v7, p0, Lmiui/widget/NumberPicker;->DR:I

    .line 164
    const/16 v0, 0x190

    iput v0, p0, Lmiui/widget/NumberPicker;->DY:I

    .line 219
    const-wide/16 v3, 0x12c

    iput-wide v3, p0, Lmiui/widget/NumberPicker;->Ej:J

    .line 224
    new-instance v0, Landroid/util/SparseArray;

    invoke-direct {v0}, Landroid/util/SparseArray;-><init>()V

    iput-object v0, p0, Lmiui/widget/NumberPicker;->Ek:Landroid/util/SparseArray;

    .line 229
    const/4 v0, 0x5

    new-array v0, v0, [I

    iput-object v0, p0, Lmiui/widget/NumberPicker;->El:[I

    .line 249
    const/high16 v0, -0x80000000

    iput v0, p0, Lmiui/widget/NumberPicker;->Ep:I

    .line 340
    iput v2, p0, Lmiui/widget/NumberPicker;->zY:I

    .line 391
    iput v6, p0, Lmiui/widget/NumberPicker;->EU:I

    .line 458
    const/16 v0, 0x1e

    iput v0, p0, Lmiui/widget/NumberPicker;->EX:I

    .line 462
    const/16 v0, 0x19

    iput v0, p0, Lmiui/widget/NumberPicker;->EZ:I

    .line 464
    const/16 v0, 0xe

    iput v0, p0, Lmiui/widget/NumberPicker;->Fa:I

    .line 466
    const/16 v0, 0xa

    iput v0, p0, Lmiui/widget/NumberPicker;->Fb:I

    .line 468
    const v0, -0x49ffd

    iput v0, p0, Lmiui/widget/NumberPicker;->Fc:I

    .line 469
    const/high16 v0, 0x7f000000

    iput v0, p0, Lmiui/widget/NumberPicker;->Fd:I

    .line 475
    const v0, -0x49ffd

    iput v0, p0, Lmiui/widget/NumberPicker;->Fg:I

    .line 484
    iput v5, p0, Lmiui/widget/NumberPicker;->Fl:F

    .line 514
    invoke-virtual {p0}, Lmiui/widget/NumberPicker;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    invoke-virtual {v0}, Landroid/content/res/Resources;->getDisplayMetrics()Landroid/util/DisplayMetrics;

    move-result-object v0

    iget v0, v0, Landroid/util/DisplayMetrics;->density:F

    .line 515
    cmpl-float v3, v0, v5

    if-eqz v3, :cond_0

    .line 516
    iget v3, p0, Lmiui/widget/NumberPicker;->Fa:I

    int-to-float v3, v3

    mul-float/2addr v3, v0

    float-to-int v3, v3

    iput v3, p0, Lmiui/widget/NumberPicker;->Fa:I

    .line 517
    iget v3, p0, Lmiui/widget/NumberPicker;->EZ:I

    int-to-float v3, v3

    mul-float/2addr v3, v0

    float-to-int v3, v3

    iput v3, p0, Lmiui/widget/NumberPicker;->EZ:I

    .line 518
    iget v3, p0, Lmiui/widget/NumberPicker;->Fb:I

    int-to-float v3, v3

    mul-float/2addr v3, v0

    float-to-int v3, v3

    iput v3, p0, Lmiui/widget/NumberPicker;->Fb:I

    .line 519
    iget v3, p0, Lmiui/widget/NumberPicker;->DQ:I

    int-to-float v3, v3

    mul-float/2addr v3, v0

    float-to-int v3, v3

    iput v3, p0, Lmiui/widget/NumberPicker;->DQ:I

    .line 520
    iget v3, p0, Lmiui/widget/NumberPicker;->DR:I

    int-to-float v3, v3

    mul-float/2addr v3, v0

    float-to-int v3, v3

    iput v3, p0, Lmiui/widget/NumberPicker;->DR:I

    .line 523
    :cond_0
    invoke-virtual {p0}, Lmiui/widget/NumberPicker;->getContext()Landroid/content/Context;

    move-result-object v3

    sget-object v4, Lcom/miui/internal/R$styleable;->NumberPicker:[I

    invoke-virtual {v3, p2, v4, p3, v2}, Landroid/content/Context;->obtainStyledAttributes(Landroid/util/AttributeSet;[III)Landroid/content/res/TypedArray;

    move-result-object v3

    .line 524
    invoke-virtual {v3, v7}, Landroid/content/res/TypedArray;->getText(I)Ljava/lang/CharSequence;

    move-result-object v4

    iput-object v4, p0, Lmiui/widget/NumberPicker;->Fi:Ljava/lang/CharSequence;

    .line 525
    const/4 v4, 0x5

    iget v5, p0, Lmiui/widget/NumberPicker;->EZ:I

    invoke-virtual {v3, v4, v5}, Landroid/content/res/TypedArray;->getDimensionPixelSize(II)I

    move-result v4

    iput v4, p0, Lmiui/widget/NumberPicker;->EZ:I

    .line 526
    const/4 v4, 0x6

    iget v5, p0, Lmiui/widget/NumberPicker;->Fa:I

    invoke-virtual {v3, v4, v5}, Landroid/content/res/TypedArray;->getDimensionPixelSize(II)I

    move-result v4

    iput v4, p0, Lmiui/widget/NumberPicker;->Fa:I

    .line 527
    const/4 v4, 0x3

    iget v5, p0, Lmiui/widget/NumberPicker;->Fb:I

    invoke-virtual {v3, v4, v5}, Landroid/content/res/TypedArray;->getDimensionPixelSize(II)I

    move-result v4

    iput v4, p0, Lmiui/widget/NumberPicker;->Fb:I

    .line 528
    iget v4, p0, Lmiui/widget/NumberPicker;->Fc:I

    invoke-virtual {v3, v2, v4}, Landroid/content/res/TypedArray;->getColor(II)I

    move-result v4

    iput v4, p0, Lmiui/widget/NumberPicker;->Fc:I

    .line 529
    iget v4, p0, Lmiui/widget/NumberPicker;->Fd:I

    invoke-virtual {v3, v1, v4}, Landroid/content/res/TypedArray;->getColor(II)I

    move-result v4

    iput v4, p0, Lmiui/widget/NumberPicker;->Fd:I

    .line 530
    const/4 v4, 0x4

    iget v5, p0, Lmiui/widget/NumberPicker;->Fg:I

    invoke-virtual {v3, v4, v5}, Landroid/content/res/TypedArray;->getColor(II)I

    move-result v4

    iput v4, p0, Lmiui/widget/NumberPicker;->Fg:I

    .line 531
    const/4 v4, 0x7

    iget v5, p0, Lmiui/widget/NumberPicker;->EX:I

    invoke-virtual {v3, v4, v5}, Landroid/content/res/TypedArray;->getDimensionPixelSize(II)I

    move-result v4

    iput v4, p0, Lmiui/widget/NumberPicker;->EX:I

    .line 533
    invoke-virtual {v3}, Landroid/content/res/TypedArray;->recycle()V

    .line 535
    new-instance v3, Landroid/media/SoundPool;

    invoke-direct {v3, v1, v1, v2}, Landroid/media/SoundPool;-><init>(III)V

    iput-object v3, p0, Lmiui/widget/NumberPicker;->Fj:Landroid/media/SoundPool;

    .line 536
    iget-object v3, p0, Lmiui/widget/NumberPicker;->Fj:Landroid/media/SoundPool;

    invoke-virtual {p0}, Lmiui/widget/NumberPicker;->getContext()Landroid/content/Context;

    move-result-object v4

    sget v5, Lcom/miui/internal/R$raw;->numberpicker_value_change:I

    invoke-virtual {v3, v4, v5, v1}, Landroid/media/SoundPool;->load(Landroid/content/Context;II)I

    move-result v3

    iput v3, p0, Lmiui/widget/NumberPicker;->Fk:I

    .line 538
    sget v3, Lcom/miui/internal/R$layout;->numberpicker_layout:I

    .line 540
    iput-boolean v1, p0, Lmiui/widget/NumberPicker;->EF:Z

    .line 542
    const/high16 v4, 0x40000000

    invoke-virtual {p0}, Lmiui/widget/NumberPicker;->getResources()Landroid/content/res/Resources;

    move-result-object v5

    invoke-virtual {v5}, Landroid/content/res/Resources;->getDisplayMetrics()Landroid/util/DisplayMetrics;

    move-result-object v5

    invoke-static {v1, v4, v5}, Landroid/util/TypedValue;->applyDimension(IFLandroid/util/DisplayMetrics;)F

    move-result v4

    float-to-int v4, v4

    .line 545
    iput v4, p0, Lmiui/widget/NumberPicker;->EH:I

    .line 547
    const/high16 v4, 0x42340000

    mul-float/2addr v4, v0

    float-to-int v4, v4

    iput v4, p0, Lmiui/widget/NumberPicker;->DU:I

    .line 549
    iput v6, p0, Lmiui/widget/NumberPicker;->DV:I

    .line 551
    const/high16 v4, 0x434a0000

    mul-float/2addr v0, v4

    float-to-int v0, v0

    iput v0, p0, Lmiui/widget/NumberPicker;->DW:I

    .line 553
    iget v0, p0, Lmiui/widget/NumberPicker;->DV:I

    if-eq v0, v6, :cond_1

    iget v0, p0, Lmiui/widget/NumberPicker;->DW:I

    if-eq v0, v6, :cond_1

    iget v0, p0, Lmiui/widget/NumberPicker;->DV:I

    iget v4, p0, Lmiui/widget/NumberPicker;->DW:I

    if-le v0, v4, :cond_1

    .line 555
    new-instance v0, Ljava/lang/IllegalArgumentException;

    const-string v1, "minHeight > maxHeight"

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 558
    :cond_1
    iput v6, p0, Lmiui/widget/NumberPicker;->DX:I

    .line 560
    iput v6, p0, Lmiui/widget/NumberPicker;->DY:I

    .line 561
    iget v0, p0, Lmiui/widget/NumberPicker;->DX:I

    if-eq v0, v6, :cond_2

    iget v0, p0, Lmiui/widget/NumberPicker;->DY:I

    if-eq v0, v6, :cond_2

    iget v0, p0, Lmiui/widget/NumberPicker;->DX:I

    iget v4, p0, Lmiui/widget/NumberPicker;->DY:I

    if-le v0, v4, :cond_2

    .line 563
    new-instance v0, Ljava/lang/IllegalArgumentException;

    const-string v1, "minWidth > maxWidth"

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 566
    :cond_2
    iget v0, p0, Lmiui/widget/NumberPicker;->DY:I

    if-ne v0, v6, :cond_4

    move v0, v1

    :goto_0
    iput-boolean v0, p0, Lmiui/widget/NumberPicker;->Ea:Z

    .line 568
    invoke-virtual {p0}, Lmiui/widget/NumberPicker;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    sget v4, Lcom/miui/internal/R$drawable;->virtual_button:I

    invoke-virtual {v0, v4}, Landroid/content/res/Resources;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v0

    iput-object v0, p0, Lmiui/widget/NumberPicker;->En:Landroid/graphics/drawable/Drawable;

    .line 570
    new-instance v0, Lmiui/widget/NumberPicker$e;

    invoke-direct {v0, p0}, Lmiui/widget/NumberPicker$e;-><init>(Lmiui/widget/NumberPicker;)V

    iput-object v0, p0, Lmiui/widget/NumberPicker;->EQ:Lmiui/widget/NumberPicker$e;

    .line 577
    iget-boolean v0, p0, Lmiui/widget/NumberPicker;->EF:Z

    if-nez v0, :cond_5

    move v0, v1

    :goto_1
    invoke-virtual {p0, v0}, Lmiui/widget/NumberPicker;->setWillNotDraw(Z)V

    .line 579
    invoke-virtual {p0}, Lmiui/widget/NumberPicker;->getContext()Landroid/content/Context;

    move-result-object v0

    const-string v4, "layout_inflater"

    invoke-virtual {v0, v4}, Landroid/content/Context;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/view/LayoutInflater;

    .line 581
    invoke-virtual {v0, v3, p0, v1}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;Z)Landroid/view/View;

    .line 584
    sget v0, Lcom/miui/internal/R$id;->numberpicker_input:I

    invoke-virtual {p0, v0}, Lmiui/widget/NumberPicker;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/EditText;

    iput-object v0, p0, Lmiui/widget/NumberPicker;->DT:Landroid/widget/EditText;

    .line 585
    iget-object v0, p0, Lmiui/widget/NumberPicker;->DT:Landroid/widget/EditText;

    new-instance v3, Lmiui/widget/NumberPicker$1;

    invoke-direct {v3, p0}, Lmiui/widget/NumberPicker$1;-><init>(Lmiui/widget/NumberPicker;)V

    invoke-virtual {v0, v3}, Landroid/widget/EditText;->setOnFocusChangeListener(Landroid/view/View$OnFocusChangeListener;)V

    .line 595
    iget-object v0, p0, Lmiui/widget/NumberPicker;->DT:Landroid/widget/EditText;

    new-array v3, v1, [Landroid/text/InputFilter;

    new-instance v4, Lmiui/widget/NumberPicker$b;

    invoke-direct {v4, p0}, Lmiui/widget/NumberPicker$b;-><init>(Lmiui/widget/NumberPicker;)V

    aput-object v4, v3, v2

    invoke-virtual {v0, v3}, Landroid/widget/EditText;->setFilters([Landroid/text/InputFilter;)V

    .line 599
    iget-object v0, p0, Lmiui/widget/NumberPicker;->DT:Landroid/widget/EditText;

    invoke-virtual {v0, v7}, Landroid/widget/EditText;->setRawInputType(I)V

    .line 600
    iget-object v0, p0, Lmiui/widget/NumberPicker;->DT:Landroid/widget/EditText;

    const/4 v3, 0x6

    invoke-virtual {v0, v3}, Landroid/widget/EditText;->setImeOptions(I)V

    .line 602
    iget-object v0, p0, Lmiui/widget/NumberPicker;->DT:Landroid/widget/EditText;

    const/4 v3, 0x4

    invoke-virtual {v0, v3}, Landroid/widget/EditText;->setVisibility(I)V

    .line 603
    iget-object v0, p0, Lmiui/widget/NumberPicker;->DT:Landroid/widget/EditText;

    const/4 v3, 0x3

    invoke-virtual {v0, v3}, Landroid/widget/EditText;->setGravity(I)V

    .line 604
    iget-object v0, p0, Lmiui/widget/NumberPicker;->DT:Landroid/widget/EditText;

    const/4 v3, 0x0

    invoke-virtual {v0, v3}, Landroid/widget/EditText;->setScaleX(F)V

    .line 605
    iget-object v0, p0, Lmiui/widget/NumberPicker;->DT:Landroid/widget/EditText;

    invoke-virtual {v0, v2}, Landroid/widget/EditText;->setSaveEnabled(Z)V

    .line 606
    iget-object v0, p0, Lmiui/widget/NumberPicker;->DT:Landroid/widget/EditText;

    iget v2, p0, Lmiui/widget/NumberPicker;->EX:I

    iget-object v3, p0, Lmiui/widget/NumberPicker;->DT:Landroid/widget/EditText;

    invoke-virtual {v3}, Landroid/widget/EditText;->getPaddingTop()I

    move-result v3

    iget v4, p0, Lmiui/widget/NumberPicker;->EX:I

    iget-object v5, p0, Lmiui/widget/NumberPicker;->DT:Landroid/widget/EditText;

    invoke-virtual {v5}, Landroid/widget/EditText;->getPaddingRight()I

    move-result v5

    invoke-virtual {v0, v2, v3, v4, v5}, Landroid/widget/EditText;->setPadding(IIII)V

    .line 609
    invoke-static {p1}, Landroid/view/ViewConfiguration;->get(Landroid/content/Context;)Landroid/view/ViewConfiguration;

    move-result-object v0

    .line 610
    invoke-virtual {v0}, Landroid/view/ViewConfiguration;->getScaledTouchSlop()I

    move-result v2

    iput v2, p0, Lmiui/widget/NumberPicker;->cT:I

    .line 611
    invoke-virtual {v0}, Landroid/view/ViewConfiguration;->getScaledMinimumFlingVelocity()I

    move-result v2

    iput v2, p0, Lmiui/widget/NumberPicker;->EA:I

    .line 612
    invoke-virtual {v0}, Landroid/view/ViewConfiguration;->getScaledMaximumFlingVelocity()I

    move-result v0

    div-int/lit8 v0, v0, 0x8

    iput v0, p0, Lmiui/widget/NumberPicker;->EB:I

    .line 614
    iget-object v0, p0, Lmiui/widget/NumberPicker;->DT:Landroid/widget/EditText;

    invoke-virtual {v0}, Landroid/widget/EditText;->getTextSize()F

    move-result v0

    float-to-int v0, v0

    iput v0, p0, Lmiui/widget/NumberPicker;->jX:I

    .line 617
    new-instance v0, Landroid/graphics/Paint;

    invoke-direct {v0}, Landroid/graphics/Paint;-><init>()V

    .line 618
    invoke-virtual {v0, v1}, Landroid/graphics/Paint;->setAntiAlias(Z)V

    .line 619
    sget-object v2, Landroid/graphics/Paint$Align;->CENTER:Landroid/graphics/Paint$Align;

    invoke-virtual {v0, v2}, Landroid/graphics/Paint;->setTextAlign(Landroid/graphics/Paint$Align;)V

    .line 620
    iget v2, p0, Lmiui/widget/NumberPicker;->EZ:I

    int-to-float v2, v2

    invoke-virtual {v0, v2}, Landroid/graphics/Paint;->setTextSize(F)V

    .line 621
    iget-object v2, p0, Lmiui/widget/NumberPicker;->DT:Landroid/widget/EditText;

    invoke-virtual {v2}, Landroid/widget/EditText;->getTypeface()Landroid/graphics/Typeface;

    move-result-object v2

    invoke-virtual {v0, v2}, Landroid/graphics/Paint;->setTypeface(Landroid/graphics/Typeface;)Landroid/graphics/Typeface;

    .line 622
    iget-object v2, p0, Lmiui/widget/NumberPicker;->DT:Landroid/widget/EditText;

    invoke-virtual {v2}, Landroid/widget/EditText;->getTextColors()Landroid/content/res/ColorStateList;

    move-result-object v2

    .line 623
    sget-object v3, Lmiui/widget/NumberPicker;->ENABLED_STATE_SET:[I

    invoke-virtual {v2, v3, v6}, Landroid/content/res/ColorStateList;->getColorForState([II)I

    move-result v2

    .line 624
    invoke-virtual {v0, v2}, Landroid/graphics/Paint;->setColor(I)V

    .line 625
    iput-object v0, p0, Lmiui/widget/NumberPicker;->Em:Landroid/graphics/Paint;

    .line 626
    invoke-virtual {v0}, Landroid/graphics/Paint;->ascent()F

    move-result v0

    iput v0, p0, Lmiui/widget/NumberPicker;->Fe:F

    .line 628
    new-instance v0, Landroid/graphics/Paint;

    invoke-direct {v0}, Landroid/graphics/Paint;-><init>()V

    iput-object v0, p0, Lmiui/widget/NumberPicker;->EW:Landroid/graphics/Paint;

    .line 629
    iget-object v0, p0, Lmiui/widget/NumberPicker;->EW:Landroid/graphics/Paint;

    invoke-virtual {v0, v1}, Landroid/graphics/Paint;->setAntiAlias(Z)V

    .line 630
    iget-object v0, p0, Lmiui/widget/NumberPicker;->EW:Landroid/graphics/Paint;

    invoke-virtual {v0, v1}, Landroid/graphics/Paint;->setFakeBoldText(Z)V

    .line 631
    iget-object v0, p0, Lmiui/widget/NumberPicker;->EW:Landroid/graphics/Paint;

    iget v2, p0, Lmiui/widget/NumberPicker;->Fg:I

    invoke-virtual {v0, v2}, Landroid/graphics/Paint;->setColor(I)V

    .line 632
    iget-object v0, p0, Lmiui/widget/NumberPicker;->EW:Landroid/graphics/Paint;

    iget v2, p0, Lmiui/widget/NumberPicker;->Fb:I

    int-to-float v2, v2

    invoke-virtual {v0, v2}, Landroid/graphics/Paint;->setTextSize(F)V

    .line 635
    new-instance v0, Landroid/widget/Scroller;

    invoke-virtual {p0}, Lmiui/widget/NumberPicker;->getContext()Landroid/content/Context;

    move-result-object v2

    const/4 v3, 0x0

    invoke-direct {v0, v2, v3, v1}, Landroid/widget/Scroller;-><init>(Landroid/content/Context;Landroid/view/animation/Interpolator;Z)V

    iput-object v0, p0, Lmiui/widget/NumberPicker;->Er:Landroid/widget/Scroller;

    .line 636
    new-instance v0, Landroid/widget/Scroller;

    invoke-virtual {p0}, Lmiui/widget/NumberPicker;->getContext()Landroid/content/Context;

    move-result-object v2

    new-instance v3, Landroid/view/animation/DecelerateInterpolator;

    const/high16 v4, 0x40200000

    invoke-direct {v3, v4}, Landroid/view/animation/DecelerateInterpolator;-><init>(F)V

    invoke-direct {v0, v2, v3}, Landroid/widget/Scroller;-><init>(Landroid/content/Context;Landroid/view/animation/Interpolator;)V

    iput-object v0, p0, Lmiui/widget/NumberPicker;->Es:Landroid/widget/Scroller;

    .line 638
    invoke-direct {p0}, Lmiui/widget/NumberPicker;->de()Z

    .line 641
    invoke-virtual {p0}, Lmiui/widget/NumberPicker;->getImportantForAccessibility()I

    move-result v0

    if-nez v0, :cond_3

    .line 642
    invoke-virtual {p0, v1}, Lmiui/widget/NumberPicker;->setImportantForAccessibility(I)V

    .line 644
    :cond_3
    return-void

    :cond_4
    move v0, v2

    .line 566
    goto/16 :goto_0

    :cond_5
    move v0, v2

    .line 577
    goto/16 :goto_1
.end method

.method private a(FII)F
    .locals 2

    .prologue
    .line 1400
    const/high16 v0, 0x3f800000

    cmpl-float v0, p1, v0

    if-ltz v0, :cond_0

    .line 1401
    int-to-float v0, p3

    .line 1403
    :goto_0
    return v0

    :cond_0
    sub-int v0, p3, p2

    int-to-float v0, v0

    mul-float/2addr v0, p1

    int-to-float v1, p2

    add-float/2addr v0, v1

    goto :goto_0
.end method

.method private a(FIZ)I
    .locals 2

    .prologue
    .line 1391
    const/high16 v0, 0x3f800000

    cmpl-float v0, p1, v0

    if-ltz v0, :cond_0

    .line 1396
    :goto_0
    return p2

    .line 1394
    :cond_0
    if-eqz p3, :cond_1

    neg-float v0, p1

    invoke-static {p2}, Landroid/graphics/Color;->alpha(I)I

    move-result v1

    int-to-float v1, v1

    mul-float/2addr v0, v1

    invoke-static {p2}, Landroid/graphics/Color;->alpha(I)I

    move-result v1

    int-to-float v1, v1

    add-float/2addr v0, v1

    float-to-int v0, v0

    .line 1396
    :goto_1
    const v1, 0xffffff

    and-int/2addr v1, p2

    shl-int/lit8 v0, v0, 0x18

    or-int p2, v1, v0

    goto :goto_0

    .line 1394
    :cond_1
    invoke-static {p2}, Landroid/graphics/Color;->alpha(I)I

    move-result v0

    int-to-float v0, v0

    mul-float/2addr v0, p1

    float-to-int v0, v0

    goto :goto_1
.end method

.method static synthetic a(Lmiui/widget/NumberPicker;Ljava/lang/String;)I
    .locals 1

    .prologue
    .line 53
    invoke-direct {p0, p1}, Lmiui/widget/NumberPicker;->x(Ljava/lang/String;)I

    move-result v0

    return v0
.end method

.method static synthetic a(Lmiui/widget/NumberPicker;)Landroid/widget/EditText;
    .locals 1

    .prologue
    .line 53
    iget-object v0, p0, Lmiui/widget/NumberPicker;->DT:Landroid/widget/EditText;

    return-object v0
.end method

.method static synthetic a(Lmiui/widget/NumberPicker;Landroid/view/View;)V
    .locals 0

    .prologue
    .line 53
    invoke-direct {p0, p1}, Lmiui/widget/NumberPicker;->k(Landroid/view/View;)V

    return-void
.end method

.method private a(ZJ)V
    .locals 1

    .prologue
    .line 1735
    iget-object v0, p0, Lmiui/widget/NumberPicker;->Ev:Lmiui/widget/NumberPicker$f;

    if-nez v0, :cond_0

    .line 1736
    new-instance v0, Lmiui/widget/NumberPicker$f;

    invoke-direct {v0, p0}, Lmiui/widget/NumberPicker$f;-><init>(Lmiui/widget/NumberPicker;)V

    iput-object v0, p0, Lmiui/widget/NumberPicker;->Ev:Lmiui/widget/NumberPicker$f;

    .line 1740
    :goto_0
    iget-object v0, p0, Lmiui/widget/NumberPicker;->Ev:Lmiui/widget/NumberPicker$f;

    invoke-static {v0, p1}, Lmiui/widget/NumberPicker$f;->a(Lmiui/widget/NumberPicker$f;Z)V

    .line 1741
    iget-object v0, p0, Lmiui/widget/NumberPicker;->Ev:Lmiui/widget/NumberPicker$f;

    invoke-virtual {p0, v0, p2, p3}, Lmiui/widget/NumberPicker;->postDelayed(Ljava/lang/Runnable;J)Z

    .line 1742
    return-void

    .line 1738
    :cond_0
    iget-object v0, p0, Lmiui/widget/NumberPicker;->Ev:Lmiui/widget/NumberPicker$f;

    invoke-virtual {p0, v0}, Lmiui/widget/NumberPicker;->removeCallbacks(Ljava/lang/Runnable;)Z

    goto :goto_0
.end method

.method private a(Landroid/widget/Scroller;)Z
    .locals 6

    .prologue
    const/4 v1, 0x1

    const/4 v2, 0x0

    .line 709
    invoke-virtual {p1, v1}, Landroid/widget/Scroller;->forceFinished(Z)V

    .line 710
    invoke-virtual {p1}, Landroid/widget/Scroller;->getFinalY()I

    move-result v0

    invoke-virtual {p1}, Landroid/widget/Scroller;->getCurrY()I

    move-result v3

    sub-int v3, v0, v3

    .line 711
    iget v0, p0, Lmiui/widget/NumberPicker;->Eq:I

    add-int/2addr v0, v3

    iget v4, p0, Lmiui/widget/NumberPicker;->Eo:I

    rem-int/2addr v0, v4

    .line 712
    iget v4, p0, Lmiui/widget/NumberPicker;->Ep:I

    sub-int v0, v4, v0

    .line 713
    if-eqz v0, :cond_2

    .line 714
    invoke-static {v0}, Ljava/lang/Math;->abs(I)I

    move-result v4

    iget v5, p0, Lmiui/widget/NumberPicker;->Eo:I

    div-int/lit8 v5, v5, 0x2

    if-le v4, v5, :cond_0

    .line 715
    if-lez v0, :cond_1

    .line 716
    iget v4, p0, Lmiui/widget/NumberPicker;->Eo:I

    sub-int/2addr v0, v4

    .line 721
    :cond_0
    :goto_0
    add-int/2addr v0, v3

    .line 722
    invoke-virtual {p0, v2, v0}, Lmiui/widget/NumberPicker;->scrollBy(II)V

    move v0, v1

    .line 725
    :goto_1
    return v0

    .line 718
    :cond_1
    iget v4, p0, Lmiui/widget/NumberPicker;->Eo:I

    add-int/2addr v0, v4

    goto :goto_0

    :cond_2
    move v0, v2

    .line 725
    goto :goto_1
.end method

.method static synthetic a(Lmiui/widget/NumberPicker;I)Z
    .locals 1

    .prologue
    .line 53
    iget-boolean v0, p0, Lmiui/widget/NumberPicker;->EN:Z

    xor-int/2addr v0, p1

    int-to-byte v0, v0

    iput-boolean v0, p0, Lmiui/widget/NumberPicker;->EN:Z

    return v0
.end method

.method static synthetic a(Lmiui/widget/NumberPicker;Z)Z
    .locals 0

    .prologue
    .line 53
    iput-boolean p1, p0, Lmiui/widget/NumberPicker;->EN:Z

    return p1
.end method

.method private aa(I)V
    .locals 1

    .prologue
    .line 1587
    iget v0, p0, Lmiui/widget/NumberPicker;->zY:I

    if-ne v0, p1, :cond_1

    .line 1594
    :cond_0
    :goto_0
    return-void

    .line 1590
    :cond_1
    iput p1, p0, Lmiui/widget/NumberPicker;->zY:I

    .line 1591
    iget-object v0, p0, Lmiui/widget/NumberPicker;->Eh:Lmiui/widget/NumberPicker$OnScrollListener;

    if-eqz v0, :cond_0

    .line 1592
    iget-object v0, p0, Lmiui/widget/NumberPicker;->Eh:Lmiui/widget/NumberPicker$OnScrollListener;

    invoke-interface {v0, p0, p1}, Lmiui/widget/NumberPicker$OnScrollListener;->onScrollStateChange(Lmiui/widget/NumberPicker;I)V

    goto :goto_0
.end method

.method private ab(I)I
    .locals 4

    .prologue
    .line 1615
    iget v0, p0, Lmiui/widget/NumberPicker;->Ee:I

    if-le p1, v0, :cond_1

    .line 1616
    iget v0, p0, Lmiui/widget/NumberPicker;->Ed:I

    iget v1, p0, Lmiui/widget/NumberPicker;->Ee:I

    sub-int v1, p1, v1

    iget v2, p0, Lmiui/widget/NumberPicker;->Ee:I

    iget v3, p0, Lmiui/widget/NumberPicker;->Ed:I

    sub-int/2addr v2, v3

    rem-int/2addr v1, v2

    add-int/2addr v0, v1

    add-int/lit8 p1, v0, -0x1

    .line 1620
    :cond_0
    :goto_0
    return p1

    .line 1617
    :cond_1
    iget v0, p0, Lmiui/widget/NumberPicker;->Ed:I

    if-ge p1, v0, :cond_0

    .line 1618
    iget v0, p0, Lmiui/widget/NumberPicker;->Ee:I

    iget v1, p0, Lmiui/widget/NumberPicker;->Ed:I

    sub-int/2addr v1, p1

    iget v2, p0, Lmiui/widget/NumberPicker;->Ee:I

    iget v3, p0, Lmiui/widget/NumberPicker;->Ed:I

    sub-int/2addr v2, v3

    rem-int/2addr v1, v2

    sub-int/2addr v0, v1

    add-int/lit8 p1, v0, 0x1

    goto :goto_0
.end method

.method private ac(I)V
    .locals 3

    .prologue
    .line 1660
    iget-object v1, p0, Lmiui/widget/NumberPicker;->Ek:Landroid/util/SparseArray;

    .line 1661
    invoke-virtual {v1, p1}, Landroid/util/SparseArray;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/String;

    .line 1662
    if-eqz v0, :cond_0

    .line 1676
    :goto_0
    return-void

    .line 1665
    :cond_0
    iget v0, p0, Lmiui/widget/NumberPicker;->Ed:I

    if-lt p1, v0, :cond_1

    iget v0, p0, Lmiui/widget/NumberPicker;->Ee:I

    if-le p1, v0, :cond_2

    .line 1666
    :cond_1
    const-string v0, ""

    .line 1675
    :goto_1
    invoke-virtual {v1, p1, v0}, Landroid/util/SparseArray;->put(ILjava/lang/Object;)V

    goto :goto_0

    .line 1668
    :cond_2
    iget-object v0, p0, Lmiui/widget/NumberPicker;->Ec:[Ljava/lang/String;

    if-eqz v0, :cond_3

    .line 1669
    iget v0, p0, Lmiui/widget/NumberPicker;->Ed:I

    sub-int v0, p1, v0

    .line 1670
    iget-object v2, p0, Lmiui/widget/NumberPicker;->Ec:[Ljava/lang/String;

    aget-object v0, v2, v0

    goto :goto_1

    .line 1672
    :cond_3
    invoke-direct {p0, p1}, Lmiui/widget/NumberPicker;->ad(I)Ljava/lang/String;

    move-result-object v0

    goto :goto_1
.end method

.method private ad(I)Ljava/lang/String;
    .locals 1

    .prologue
    .line 1679
    iget-object v0, p0, Lmiui/widget/NumberPicker;->Ei:Lmiui/widget/NumberPicker$Formatter;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lmiui/widget/NumberPicker;->Ei:Lmiui/widget/NumberPicker$Formatter;

    invoke-interface {v0, p1}, Lmiui/widget/NumberPicker$Formatter;->format(I)Ljava/lang/String;

    move-result-object v0

    :goto_0
    return-object v0

    :cond_0
    invoke-static {p1}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v0

    goto :goto_0
.end method

.method private b(Landroid/widget/Scroller;)V
    .locals 2

    .prologue
    .line 1571
    iget-object v0, p0, Lmiui/widget/NumberPicker;->Er:Landroid/widget/Scroller;

    if-ne p1, v0, :cond_2

    .line 1572
    invoke-direct {p0}, Lmiui/widget/NumberPicker;->dj()Z

    move-result v0

    if-nez v0, :cond_0

    .line 1573
    invoke-direct {p0}, Lmiui/widget/NumberPicker;->de()Z

    .line 1575
    :cond_0
    const/4 v0, 0x0

    invoke-direct {p0, v0}, Lmiui/widget/NumberPicker;->aa(I)V

    .line 1581
    :cond_1
    :goto_0
    return-void

    .line 1577
    :cond_2
    iget v0, p0, Lmiui/widget/NumberPicker;->zY:I

    const/4 v1, 0x1

    if-eq v0, v1, :cond_1

    .line 1578
    invoke-direct {p0}, Lmiui/widget/NumberPicker;->de()Z

    goto :goto_0
.end method

.method static synthetic b(Lmiui/widget/NumberPicker;II)V
    .locals 0

    .prologue
    .line 53
    invoke-direct {p0, p1, p2}, Lmiui/widget/NumberPicker;->l(II)V

    return-void
.end method

.method private b([I)V
    .locals 2

    .prologue
    .line 1628
    const/4 v0, 0x0

    :goto_0
    array-length v1, p1

    add-int/lit8 v1, v1, -0x1

    if-ge v0, v1, :cond_0

    .line 1629
    add-int/lit8 v1, v0, 0x1

    aget v1, p1, v1

    aput v1, p1, v0

    .line 1628
    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    .line 1631
    :cond_0
    array-length v0, p1

    add-int/lit8 v0, v0, -0x2

    aget v0, p1, v0

    add-int/lit8 v0, v0, 0x1

    .line 1632
    iget-boolean v1, p0, Lmiui/widget/NumberPicker;->ED:Z

    if-eqz v1, :cond_1

    iget v1, p0, Lmiui/widget/NumberPicker;->Ee:I

    if-le v0, v1, :cond_1

    .line 1633
    iget v0, p0, Lmiui/widget/NumberPicker;->Ed:I

    .line 1635
    :cond_1
    array-length v1, p1

    add-int/lit8 v1, v1, -0x1

    aput v0, p1, v1

    .line 1636
    invoke-direct {p0, v0}, Lmiui/widget/NumberPicker;->ac(I)V

    .line 1637
    return-void
.end method

.method static synthetic b(Lmiui/widget/NumberPicker;I)Z
    .locals 1

    .prologue
    .line 53
    iget-boolean v0, p0, Lmiui/widget/NumberPicker;->EO:Z

    xor-int/2addr v0, p1

    int-to-byte v0, v0

    iput-boolean v0, p0, Lmiui/widget/NumberPicker;->EO:Z

    return v0
.end method

.method static synthetic b(Lmiui/widget/NumberPicker;Z)Z
    .locals 0

    .prologue
    .line 53
    iput-boolean p1, p0, Lmiui/widget/NumberPicker;->EO:Z

    return p1
.end method

.method static synthetic b(Lmiui/widget/NumberPicker;)[Ljava/lang/String;
    .locals 1

    .prologue
    .line 53
    iget-object v0, p0, Lmiui/widget/NumberPicker;->Ec:[Ljava/lang/String;

    return-object v0
.end method

.method static synthetic c(Lmiui/widget/NumberPicker;)I
    .locals 1

    .prologue
    .line 53
    iget v0, p0, Lmiui/widget/NumberPicker;->Ee:I

    return v0
.end method

.method static synthetic c(Lmiui/widget/NumberPicker;I)I
    .locals 1

    .prologue
    .line 53
    invoke-direct {p0, p1}, Lmiui/widget/NumberPicker;->ab(I)I

    move-result v0

    return v0
.end method

.method static synthetic c(Lmiui/widget/NumberPicker;Z)V
    .locals 0

    .prologue
    .line 53
    invoke-direct {p0, p1}, Lmiui/widget/NumberPicker;->k(Z)V

    return-void
.end method

.method private c([I)V
    .locals 2

    .prologue
    .line 1644
    array-length v0, p1

    add-int/lit8 v0, v0, -0x1

    :goto_0
    if-lez v0, :cond_0

    .line 1645
    add-int/lit8 v1, v0, -0x1

    aget v1, p1, v1

    aput v1, p1, v0

    .line 1644
    add-int/lit8 v0, v0, -0x1

    goto :goto_0

    .line 1647
    :cond_0
    const/4 v0, 0x1

    aget v0, p1, v0

    add-int/lit8 v0, v0, -0x1

    .line 1648
    iget-boolean v1, p0, Lmiui/widget/NumberPicker;->ED:Z

    if-eqz v1, :cond_1

    iget v1, p0, Lmiui/widget/NumberPicker;->Ed:I

    if-ge v0, v1, :cond_1

    .line 1649
    iget v0, p0, Lmiui/widget/NumberPicker;->Ee:I

    .line 1651
    :cond_1
    const/4 v1, 0x0

    aput v0, p1, v1

    .line 1652
    invoke-direct {p0, v0}, Lmiui/widget/NumberPicker;->ac(I)V

    .line 1653
    return-void
.end method

.method static synthetic d(Lmiui/widget/NumberPicker;I)Ljava/lang/String;
    .locals 1

    .prologue
    .line 53
    invoke-direct {p0, p1}, Lmiui/widget/NumberPicker;->ad(I)Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method static synthetic d(Lmiui/widget/NumberPicker;)Z
    .locals 1

    .prologue
    .line 53
    iget-boolean v0, p0, Lmiui/widget/NumberPicker;->EN:Z

    return v0
.end method

.method static synthetic d(Lmiui/widget/NumberPicker;Z)Z
    .locals 0

    .prologue
    .line 53
    iput-boolean p1, p0, Lmiui/widget/NumberPicker;->EI:Z

    return p1
.end method

.method private da()V
    .locals 5

    .prologue
    const/4 v0, 0x0

    .line 1103
    iget-boolean v1, p0, Lmiui/widget/NumberPicker;->Ea:Z

    if-nez v1, :cond_1

    .line 1138
    :cond_0
    :goto_0
    return-void

    .line 1106
    :cond_1
    const/high16 v1, -0x40800000

    .line 1107
    iget-object v2, p0, Lmiui/widget/NumberPicker;->Em:Landroid/graphics/Paint;

    iget v3, p0, Lmiui/widget/NumberPicker;->EZ:I

    int-to-float v3, v3

    invoke-virtual {v2, v3}, Landroid/graphics/Paint;->setTextSize(F)V

    .line 1108
    iget-object v2, p0, Lmiui/widget/NumberPicker;->Ec:[Ljava/lang/String;

    if-nez v2, :cond_4

    .line 1109
    const/4 v1, 0x0

    move v2, v0

    .line 1110
    :goto_1
    const/16 v0, 0x9

    if-ge v2, v0, :cond_2

    .line 1111
    iget-object v0, p0, Lmiui/widget/NumberPicker;->Em:Landroid/graphics/Paint;

    invoke-static {v2}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v0, v3}, Landroid/graphics/Paint;->measureText(Ljava/lang/String;)F

    move-result v0

    .line 1112
    cmpl-float v3, v0, v1

    if-lez v3, :cond_7

    .line 1110
    :goto_2
    add-int/lit8 v1, v2, 0x1

    move v2, v1

    move v1, v0

    goto :goto_1

    .line 1116
    :cond_2
    iget v0, p0, Lmiui/widget/NumberPicker;->Ee:I

    invoke-direct {p0, v0}, Lmiui/widget/NumberPicker;->ad(I)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/String;->length()I

    move-result v0

    .line 1117
    int-to-float v0, v0

    mul-float/2addr v0, v1

    float-to-int v0, v0

    int-to-float v1, v0

    .line 1128
    :cond_3
    iput v1, p0, Lmiui/widget/NumberPicker;->EY:F

    .line 1129
    iget-object v0, p0, Lmiui/widget/NumberPicker;->DT:Landroid/widget/EditText;

    invoke-virtual {v0}, Landroid/widget/EditText;->getPaddingLeft()I

    move-result v0

    iget-object v2, p0, Lmiui/widget/NumberPicker;->DT:Landroid/widget/EditText;

    invoke-virtual {v2}, Landroid/widget/EditText;->getPaddingRight()I

    move-result v2

    add-int/2addr v0, v2

    int-to-float v0, v0

    add-float/2addr v0, v1

    .line 1130
    invoke-virtual {p0}, Lmiui/widget/NumberPicker;->getPaddingLeft()I

    move-result v1

    int-to-float v1, v1

    add-float/2addr v0, v1

    invoke-virtual {p0}, Lmiui/widget/NumberPicker;->getPaddingRight()I

    move-result v1

    int-to-float v1, v1

    add-float/2addr v0, v1

    .line 1131
    iget v1, p0, Lmiui/widget/NumberPicker;->DY:I

    int-to-float v1, v1

    cmpl-float v1, v1, v0

    if-eqz v1, :cond_0

    .line 1132
    iget v1, p0, Lmiui/widget/NumberPicker;->DX:I

    int-to-float v1, v1

    cmpl-float v1, v0, v1

    if-lez v1, :cond_5

    .line 1133
    float-to-int v0, v0

    iput v0, p0, Lmiui/widget/NumberPicker;->DY:I

    goto :goto_0

    .line 1119
    :cond_4
    iget-object v2, p0, Lmiui/widget/NumberPicker;->Ec:[Ljava/lang/String;

    array-length v3, v2

    move v2, v0

    .line 1120
    :goto_3
    if-ge v2, v3, :cond_3

    .line 1121
    iget-object v0, p0, Lmiui/widget/NumberPicker;->Ec:[Ljava/lang/String;

    aget-object v0, v0, v2

    .line 1122
    iget-object v4, p0, Lmiui/widget/NumberPicker;->Em:Landroid/graphics/Paint;

    invoke-virtual {v4, v0}, Landroid/graphics/Paint;->measureText(Ljava/lang/String;)F

    move-result v0

    .line 1123
    cmpl-float v4, v0, v1

    if-lez v4, :cond_6

    .line 1120
    :goto_4
    add-int/lit8 v1, v2, 0x1

    move v2, v1

    move v1, v0

    goto :goto_3

    .line 1135
    :cond_5
    iget v0, p0, Lmiui/widget/NumberPicker;->DX:I

    iput v0, p0, Lmiui/widget/NumberPicker;->DY:I

    goto/16 :goto_0

    :cond_6
    move v0, v1

    goto :goto_4

    :cond_7
    move v0, v1

    goto :goto_2
.end method

.method private db()V
    .locals 5

    .prologue
    .line 1476
    iget-object v0, p0, Lmiui/widget/NumberPicker;->Ek:Landroid/util/SparseArray;

    invoke-virtual {v0}, Landroid/util/SparseArray;->clear()V

    .line 1477
    iget-object v2, p0, Lmiui/widget/NumberPicker;->El:[I

    .line 1478
    invoke-virtual {p0}, Lmiui/widget/NumberPicker;->getValue()I

    move-result v3

    .line 1479
    const/4 v0, 0x0

    :goto_0
    iget-object v1, p0, Lmiui/widget/NumberPicker;->El:[I

    array-length v1, v1

    if-ge v0, v1, :cond_1

    .line 1480
    add-int/lit8 v1, v0, -0x2

    add-int/2addr v1, v3

    .line 1481
    iget-boolean v4, p0, Lmiui/widget/NumberPicker;->ED:Z

    if-eqz v4, :cond_0

    .line 1482
    invoke-direct {p0, v1}, Lmiui/widget/NumberPicker;->ab(I)I

    move-result v1

    .line 1484
    :cond_0
    aput v1, v2, v0

    .line 1485
    aget v1, v2, v0

    invoke-direct {p0, v1}, Lmiui/widget/NumberPicker;->ac(I)V

    .line 1479
    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    .line 1487
    :cond_1
    return-void
.end method

.method private dc()V
    .locals 4

    .prologue
    .line 1546
    invoke-direct {p0}, Lmiui/widget/NumberPicker;->db()V

    .line 1547
    iget-object v0, p0, Lmiui/widget/NumberPicker;->El:[I

    .line 1548
    array-length v1, v0

    iget v2, p0, Lmiui/widget/NumberPicker;->jX:I

    mul-int/2addr v1, v2

    .line 1549
    invoke-virtual {p0}, Lmiui/widget/NumberPicker;->getBottom()I

    move-result v2

    invoke-virtual {p0}, Lmiui/widget/NumberPicker;->getTop()I

    move-result v3

    sub-int/2addr v2, v3

    sub-int v1, v2, v1

    int-to-float v1, v1

    .line 1550
    array-length v0, v0

    int-to-float v0, v0

    .line 1551
    div-float v0, v1, v0

    const/high16 v1, 0x3f000000

    add-float/2addr v0, v1

    float-to-int v0, v0

    iput v0, p0, Lmiui/widget/NumberPicker;->Eb:I

    .line 1552
    iget v0, p0, Lmiui/widget/NumberPicker;->jX:I

    iget v1, p0, Lmiui/widget/NumberPicker;->Eb:I

    add-int/2addr v0, v1

    iput v0, p0, Lmiui/widget/NumberPicker;->Eo:I

    .line 1555
    iget-object v0, p0, Lmiui/widget/NumberPicker;->DT:Landroid/widget/EditText;

    invoke-virtual {v0}, Landroid/widget/EditText;->getBaseline()I

    move-result v0

    iget-object v1, p0, Lmiui/widget/NumberPicker;->DT:Landroid/widget/EditText;

    invoke-virtual {v1}, Landroid/widget/EditText;->getTop()I

    move-result v1

    add-int/2addr v0, v1

    .line 1556
    iget v1, p0, Lmiui/widget/NumberPicker;->Eo:I

    mul-int/lit8 v1, v1, 0x2

    sub-int/2addr v0, v1

    iput v0, p0, Lmiui/widget/NumberPicker;->Ep:I

    .line 1558
    iget v0, p0, Lmiui/widget/NumberPicker;->Ep:I

    iput v0, p0, Lmiui/widget/NumberPicker;->Eq:I

    .line 1559
    invoke-direct {p0}, Lmiui/widget/NumberPicker;->de()Z

    .line 1560
    return-void
.end method

.method private dd()V
    .locals 2

    .prologue
    .line 1563
    const/4 v0, 0x1

    invoke-virtual {p0, v0}, Lmiui/widget/NumberPicker;->setVerticalFadingEdgeEnabled(Z)V

    .line 1564
    invoke-virtual {p0}, Lmiui/widget/NumberPicker;->getBottom()I

    move-result v0

    invoke-virtual {p0}, Lmiui/widget/NumberPicker;->getTop()I

    move-result v1

    sub-int/2addr v0, v1

    iget v1, p0, Lmiui/widget/NumberPicker;->jX:I

    sub-int/2addr v0, v1

    div-int/lit8 v0, v0, 0x2

    invoke-virtual {p0, v0}, Lmiui/widget/NumberPicker;->setFadingEdgeLength(I)V

    .line 1565
    return-void
.end method

.method private de()Z
    .locals 3

    .prologue
    .line 1708
    iget-object v0, p0, Lmiui/widget/NumberPicker;->Ec:[Ljava/lang/String;

    if-nez v0, :cond_0

    iget v0, p0, Lmiui/widget/NumberPicker;->Ef:I

    invoke-direct {p0, v0}, Lmiui/widget/NumberPicker;->ad(I)Ljava/lang/String;

    move-result-object v0

    .line 1710
    :goto_0
    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v1

    if-nez v1, :cond_1

    iget-object v1, p0, Lmiui/widget/NumberPicker;->DT:Landroid/widget/EditText;

    invoke-virtual {v1}, Landroid/widget/EditText;->getText()Landroid/text/Editable;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_1

    .line 1711
    iget-object v1, p0, Lmiui/widget/NumberPicker;->DT:Landroid/widget/EditText;

    invoke-virtual {v1, v0}, Landroid/widget/EditText;->setText(Ljava/lang/CharSequence;)V

    .line 1712
    const/4 v0, 0x1

    .line 1715
    :goto_1
    return v0

    .line 1708
    :cond_0
    iget-object v0, p0, Lmiui/widget/NumberPicker;->Ec:[Ljava/lang/String;

    iget v1, p0, Lmiui/widget/NumberPicker;->Ef:I

    iget v2, p0, Lmiui/widget/NumberPicker;->Ed:I

    sub-int/2addr v1, v2

    aget-object v0, v0, v1

    goto :goto_0

    .line 1715
    :cond_1
    const/4 v0, 0x0

    goto :goto_1
.end method

.method private df()V
    .locals 1

    .prologue
    .line 1748
    iget-object v0, p0, Lmiui/widget/NumberPicker;->Ev:Lmiui/widget/NumberPicker$f;

    if-eqz v0, :cond_0

    .line 1749
    iget-object v0, p0, Lmiui/widget/NumberPicker;->Ev:Lmiui/widget/NumberPicker$f;

    invoke-virtual {p0, v0}, Lmiui/widget/NumberPicker;->removeCallbacks(Ljava/lang/Runnable;)Z

    .line 1751
    :cond_0
    return-void
.end method

.method private dg()V
    .locals 3

    .prologue
    .line 1758
    iget-object v0, p0, Lmiui/widget/NumberPicker;->Ew:Lmiui/widget/NumberPicker$c;

    if-nez v0, :cond_0

    .line 1759
    new-instance v0, Lmiui/widget/NumberPicker$c;

    invoke-direct {v0, p0}, Lmiui/widget/NumberPicker$c;-><init>(Lmiui/widget/NumberPicker;)V

    iput-object v0, p0, Lmiui/widget/NumberPicker;->Ew:Lmiui/widget/NumberPicker$c;

    .line 1763
    :goto_0
    iget-object v0, p0, Lmiui/widget/NumberPicker;->Ew:Lmiui/widget/NumberPicker$c;

    invoke-static {}, Landroid/view/ViewConfiguration;->getLongPressTimeout()I

    move-result v1

    int-to-long v1, v1

    invoke-virtual {p0, v0, v1, v2}, Lmiui/widget/NumberPicker;->postDelayed(Ljava/lang/Runnable;J)Z

    .line 1764
    return-void

    .line 1761
    :cond_0
    iget-object v0, p0, Lmiui/widget/NumberPicker;->Ew:Lmiui/widget/NumberPicker$c;

    invoke-virtual {p0, v0}, Lmiui/widget/NumberPicker;->removeCallbacks(Ljava/lang/Runnable;)Z

    goto :goto_0
.end method

.method private dh()V
    .locals 1

    .prologue
    .line 1770
    iget-object v0, p0, Lmiui/widget/NumberPicker;->Ew:Lmiui/widget/NumberPicker$c;

    if-eqz v0, :cond_0

    .line 1771
    iget-object v0, p0, Lmiui/widget/NumberPicker;->Ew:Lmiui/widget/NumberPicker$c;

    invoke-virtual {p0, v0}, Lmiui/widget/NumberPicker;->removeCallbacks(Ljava/lang/Runnable;)Z

    .line 1773
    :cond_0
    return-void
.end method

.method private di()V
    .locals 1

    .prologue
    .line 1779
    iget-object v0, p0, Lmiui/widget/NumberPicker;->Ev:Lmiui/widget/NumberPicker$f;

    if-eqz v0, :cond_0

    .line 1780
    iget-object v0, p0, Lmiui/widget/NumberPicker;->Ev:Lmiui/widget/NumberPicker$f;

    invoke-virtual {p0, v0}, Lmiui/widget/NumberPicker;->removeCallbacks(Ljava/lang/Runnable;)Z

    .line 1782
    :cond_0
    iget-object v0, p0, Lmiui/widget/NumberPicker;->Eu:Lmiui/widget/NumberPicker$a;

    if-eqz v0, :cond_1

    .line 1783
    iget-object v0, p0, Lmiui/widget/NumberPicker;->Eu:Lmiui/widget/NumberPicker$a;

    invoke-virtual {p0, v0}, Lmiui/widget/NumberPicker;->removeCallbacks(Ljava/lang/Runnable;)Z

    .line 1785
    :cond_1
    iget-object v0, p0, Lmiui/widget/NumberPicker;->Ew:Lmiui/widget/NumberPicker$c;

    if-eqz v0, :cond_2

    .line 1786
    iget-object v0, p0, Lmiui/widget/NumberPicker;->Ew:Lmiui/widget/NumberPicker$c;

    invoke-virtual {p0, v0}, Lmiui/widget/NumberPicker;->removeCallbacks(Ljava/lang/Runnable;)Z

    .line 1788
    :cond_2
    iget-object v0, p0, Lmiui/widget/NumberPicker;->EQ:Lmiui/widget/NumberPicker$e;

    invoke-virtual {v0}, Lmiui/widget/NumberPicker$e;->cancel()V

    .line 1789
    return-void
.end method

.method private dj()Z
    .locals 6

    .prologue
    const/4 v1, 0x0

    .line 1920
    iget v0, p0, Lmiui/widget/NumberPicker;->Ep:I

    iget v2, p0, Lmiui/widget/NumberPicker;->Eq:I

    sub-int v4, v0, v2

    .line 1921
    if-eqz v4, :cond_1

    .line 1922
    iput v1, p0, Lmiui/widget/NumberPicker;->Et:I

    .line 1923
    invoke-static {v4}, Ljava/lang/Math;->abs(I)I

    move-result v0

    iget v2, p0, Lmiui/widget/NumberPicker;->Eo:I

    div-int/lit8 v2, v2, 0x2

    if-le v0, v2, :cond_0

    .line 1924
    if-lez v4, :cond_2

    iget v0, p0, Lmiui/widget/NumberPicker;->Eo:I

    neg-int v0, v0

    :goto_0
    add-int/2addr v4, v0

    .line 1926
    :cond_0
    iget-object v0, p0, Lmiui/widget/NumberPicker;->Es:Landroid/widget/Scroller;

    const/16 v5, 0x320

    move v2, v1

    move v3, v1

    invoke-virtual/range {v0 .. v5}, Landroid/widget/Scroller;->startScroll(IIIII)V

    .line 1927
    invoke-virtual {p0}, Lmiui/widget/NumberPicker;->invalidate()V

    .line 1928
    const/4 v1, 0x1

    .line 1930
    :cond_1
    return v1

    .line 1924
    :cond_2
    iget v0, p0, Lmiui/widget/NumberPicker;->Eo:I

    goto :goto_0
.end method

.method private dk()V
    .locals 0

    .prologue
    .line 2501
    invoke-direct {p0}, Lmiui/widget/NumberPicker;->db()V

    .line 2502
    invoke-virtual {p0}, Lmiui/widget/NumberPicker;->invalidate()V

    .line 2503
    return-void
.end method

.method static synthetic dl()[C
    .locals 1

    .prologue
    .line 53
    sget-object v0, Lmiui/widget/NumberPicker;->Fm:[C

    return-object v0
.end method

.method static synthetic e(Lmiui/widget/NumberPicker;)I
    .locals 1

    .prologue
    .line 53
    iget v0, p0, Lmiui/widget/NumberPicker;->EL:I

    return v0
.end method

.method static synthetic f(Lmiui/widget/NumberPicker;)Z
    .locals 1

    .prologue
    .line 53
    iget-boolean v0, p0, Lmiui/widget/NumberPicker;->EO:Z

    return v0
.end method

.method private fling(I)V
    .locals 9

    .prologue
    const v8, 0x7fffffff

    const/4 v1, 0x0

    .line 1600
    iput v1, p0, Lmiui/widget/NumberPicker;->Et:I

    .line 1602
    if-lez p1, :cond_0

    .line 1603
    iget-object v0, p0, Lmiui/widget/NumberPicker;->Er:Landroid/widget/Scroller;

    move v2, v1

    move v3, v1

    move v4, p1

    move v5, v1

    move v6, v1

    move v7, v1

    invoke-virtual/range {v0 .. v8}, Landroid/widget/Scroller;->fling(IIIIIIII)V

    .line 1608
    :goto_0
    invoke-virtual {p0}, Lmiui/widget/NumberPicker;->invalidate()V

    .line 1609
    return-void

    .line 1605
    :cond_0
    iget-object v0, p0, Lmiui/widget/NumberPicker;->Er:Landroid/widget/Scroller;

    move v2, v8

    move v3, v1

    move v4, p1

    move v5, v1

    move v6, v1

    move v7, v1

    invoke-virtual/range {v0 .. v8}, Landroid/widget/Scroller;->fling(IIIIIIII)V

    goto :goto_0
.end method

.method static synthetic g(Lmiui/widget/NumberPicker;)I
    .locals 1

    .prologue
    .line 53
    iget v0, p0, Lmiui/widget/NumberPicker;->EK:I

    return v0
.end method

.method private g(IZ)V
    .locals 2

    .prologue
    .line 1496
    iget v0, p0, Lmiui/widget/NumberPicker;->Ef:I

    if-ne v0, p1, :cond_0

    .line 1514
    :goto_0
    return-void

    .line 1500
    :cond_0
    iget-boolean v0, p0, Lmiui/widget/NumberPicker;->ED:Z

    if-eqz v0, :cond_2

    .line 1501
    invoke-direct {p0, p1}, Lmiui/widget/NumberPicker;->ab(I)I

    move-result v0

    .line 1506
    :goto_1
    iget v1, p0, Lmiui/widget/NumberPicker;->Ef:I

    .line 1507
    iput v0, p0, Lmiui/widget/NumberPicker;->Ef:I

    .line 1508
    invoke-direct {p0}, Lmiui/widget/NumberPicker;->de()Z

    .line 1509
    if-eqz p2, :cond_1

    .line 1510
    invoke-direct {p0, v1, v0}, Lmiui/widget/NumberPicker;->k(II)V

    .line 1512
    :cond_1
    invoke-direct {p0}, Lmiui/widget/NumberPicker;->db()V

    .line 1513
    invoke-virtual {p0}, Lmiui/widget/NumberPicker;->invalidate()V

    goto :goto_0

    .line 1503
    :cond_2
    iget v0, p0, Lmiui/widget/NumberPicker;->Ed:I

    invoke-static {p1, v0}, Ljava/lang/Math;->max(II)I

    move-result v0

    .line 1504
    iget v1, p0, Lmiui/widget/NumberPicker;->Ee:I

    invoke-static {v0, v1}, Ljava/lang/Math;->min(II)I

    move-result v0

    goto :goto_1
.end method

.method static synthetic h(Lmiui/widget/NumberPicker;)J
    .locals 2

    .prologue
    .line 53
    iget-wide v0, p0, Lmiui/widget/NumberPicker;->Ej:J

    return-wide v0
.end method

.method private i(III)I
    .locals 2

    .prologue
    .line 1463
    const/4 v0, -0x1

    if-eq p1, v0, :cond_0

    .line 1464
    invoke-static {p1, p2}, Ljava/lang/Math;->max(II)I

    move-result v0

    .line 1465
    const/4 v1, 0x0

    invoke-static {v0, p3, v1}, Lmiui/widget/NumberPicker;->resolveSizeAndState(III)I

    move-result p2

    .line 1467
    :cond_0
    return p2
.end method

.method static synthetic i(Lmiui/widget/NumberPicker;)I
    .locals 1

    .prologue
    .line 53
    iget v0, p0, Lmiui/widget/NumberPicker;->EH:I

    return v0
.end method

.method static synthetic j(Lmiui/widget/NumberPicker;)I
    .locals 1

    .prologue
    .line 53
    iget v0, p0, Lmiui/widget/NumberPicker;->Ef:I

    return v0
.end method

.method private k(II)V
    .locals 7

    .prologue
    const/4 v4, 0x0

    const/high16 v2, 0x3f800000

    .line 1723
    iget-object v0, p0, Lmiui/widget/NumberPicker;->Fj:Landroid/media/SoundPool;

    iget v1, p0, Lmiui/widget/NumberPicker;->Fk:I

    move v3, v2

    move v5, v4

    move v6, v2

    invoke-virtual/range {v0 .. v6}, Landroid/media/SoundPool;->play(IFFIIF)I

    .line 1724
    iget-object v0, p0, Lmiui/widget/NumberPicker;->Eg:Lmiui/widget/NumberPicker$OnValueChangeListener;

    if-eqz v0, :cond_0

    .line 1725
    iget-object v0, p0, Lmiui/widget/NumberPicker;->Eg:Lmiui/widget/NumberPicker$OnValueChangeListener;

    iget v1, p0, Lmiui/widget/NumberPicker;->Ef:I

    invoke-interface {v0, p0, p1, v1}, Lmiui/widget/NumberPicker$OnValueChangeListener;->onValueChange(Lmiui/widget/NumberPicker;II)V

    .line 1727
    :cond_0
    return-void
.end method

.method private k(Landroid/view/View;)V
    .locals 2

    .prologue
    .line 1683
    check-cast p1, Landroid/widget/TextView;

    invoke-virtual {p1}, Landroid/widget/TextView;->getText()Ljava/lang/CharSequence;

    move-result-object v0

    invoke-static {v0}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    .line 1684
    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v1

    if-eqz v1, :cond_0

    .line 1686
    invoke-direct {p0}, Lmiui/widget/NumberPicker;->de()Z

    .line 1692
    :goto_0
    return-void

    .line 1689
    :cond_0
    invoke-virtual {v0}, Ljava/lang/String;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-direct {p0, v0}, Lmiui/widget/NumberPicker;->x(Ljava/lang/String;)I

    move-result v0

    .line 1690
    const/4 v1, 0x1

    invoke-direct {p0, v0, v1}, Lmiui/widget/NumberPicker;->g(IZ)V

    goto :goto_0
.end method

.method private k(Z)V
    .locals 6

    .prologue
    const/16 v5, 0x12c

    const/4 v2, 0x1

    const/4 v1, 0x0

    .line 1524
    iget-boolean v0, p0, Lmiui/widget/NumberPicker;->EF:Z

    if-eqz v0, :cond_2

    .line 1525
    iget-object v0, p0, Lmiui/widget/NumberPicker;->DT:Landroid/widget/EditText;

    const/4 v2, 0x4

    invoke-virtual {v0, v2}, Landroid/widget/EditText;->setVisibility(I)V

    .line 1526
    iget-object v0, p0, Lmiui/widget/NumberPicker;->Er:Landroid/widget/Scroller;

    invoke-direct {p0, v0}, Lmiui/widget/NumberPicker;->a(Landroid/widget/Scroller;)Z

    move-result v0

    if-nez v0, :cond_0

    .line 1527
    iget-object v0, p0, Lmiui/widget/NumberPicker;->Es:Landroid/widget/Scroller;

    invoke-direct {p0, v0}, Lmiui/widget/NumberPicker;->a(Landroid/widget/Scroller;)Z

    .line 1529
    :cond_0
    iput v1, p0, Lmiui/widget/NumberPicker;->Et:I

    .line 1530
    if-eqz p1, :cond_1

    .line 1531
    iget-object v0, p0, Lmiui/widget/NumberPicker;->Er:Landroid/widget/Scroller;

    iget v2, p0, Lmiui/widget/NumberPicker;->Eo:I

    neg-int v4, v2

    move v2, v1

    move v3, v1

    invoke-virtual/range {v0 .. v5}, Landroid/widget/Scroller;->startScroll(IIIII)V

    .line 1535
    :goto_0
    invoke-virtual {p0}, Lmiui/widget/NumberPicker;->invalidate()V

    .line 1543
    :goto_1
    return-void

    .line 1533
    :cond_1
    iget-object v0, p0, Lmiui/widget/NumberPicker;->Er:Landroid/widget/Scroller;

    iget v4, p0, Lmiui/widget/NumberPicker;->Eo:I

    move v2, v1

    move v3, v1

    invoke-virtual/range {v0 .. v5}, Landroid/widget/Scroller;->startScroll(IIIII)V

    goto :goto_0

    .line 1537
    :cond_2
    if-eqz p1, :cond_3

    .line 1538
    iget v0, p0, Lmiui/widget/NumberPicker;->Ef:I

    add-int/lit8 v0, v0, 0x1

    invoke-direct {p0, v0, v2}, Lmiui/widget/NumberPicker;->g(IZ)V

    goto :goto_1

    .line 1540
    :cond_3
    iget v0, p0, Lmiui/widget/NumberPicker;->Ef:I

    add-int/lit8 v0, v0, -0x1

    invoke-direct {p0, v0, v2}, Lmiui/widget/NumberPicker;->g(IZ)V

    goto :goto_1
.end method

.method static synthetic k(Lmiui/widget/NumberPicker;)Z
    .locals 1

    .prologue
    .line 53
    iget-boolean v0, p0, Lmiui/widget/NumberPicker;->ED:Z

    return v0
.end method

.method static synthetic l(Lmiui/widget/NumberPicker;)I
    .locals 1

    .prologue
    .line 53
    iget v0, p0, Lmiui/widget/NumberPicker;->Ed:I

    return v0
.end method

.method private l(II)V
    .locals 1

    .prologue
    .line 1829
    iget-object v0, p0, Lmiui/widget/NumberPicker;->Eu:Lmiui/widget/NumberPicker$a;

    if-nez v0, :cond_0

    .line 1830
    new-instance v0, Lmiui/widget/NumberPicker$a;

    invoke-direct {v0, p0}, Lmiui/widget/NumberPicker$a;-><init>(Lmiui/widget/NumberPicker;)V

    iput-object v0, p0, Lmiui/widget/NumberPicker;->Eu:Lmiui/widget/NumberPicker$a;

    .line 1834
    :goto_0
    iget-object v0, p0, Lmiui/widget/NumberPicker;->Eu:Lmiui/widget/NumberPicker$a;

    invoke-static {v0, p1}, Lmiui/widget/NumberPicker$a;->a(Lmiui/widget/NumberPicker$a;I)I

    .line 1835
    iget-object v0, p0, Lmiui/widget/NumberPicker;->Eu:Lmiui/widget/NumberPicker$a;

    invoke-static {v0, p2}, Lmiui/widget/NumberPicker$a;->b(Lmiui/widget/NumberPicker$a;I)I

    .line 1836
    iget-object v0, p0, Lmiui/widget/NumberPicker;->Eu:Lmiui/widget/NumberPicker$a;

    invoke-virtual {p0, v0}, Lmiui/widget/NumberPicker;->post(Ljava/lang/Runnable;)Z

    .line 1837
    return-void

    .line 1832
    :cond_0
    iget-object v0, p0, Lmiui/widget/NumberPicker;->Eu:Lmiui/widget/NumberPicker$a;

    invoke-virtual {p0, v0}, Lmiui/widget/NumberPicker;->removeCallbacks(Ljava/lang/Runnable;)Z

    goto :goto_0
.end method

.method private makeMeasureSpec(II)I
    .locals 4

    .prologue
    const/high16 v2, 0x40000000

    .line 1434
    const/4 v0, -0x1

    if-ne p2, v0, :cond_0

    .line 1445
    :goto_0
    :sswitch_0
    return p1

    .line 1437
    :cond_0
    invoke-static {p1}, Landroid/view/View$MeasureSpec;->getSize(I)I

    move-result v0

    .line 1438
    invoke-static {p1}, Landroid/view/View$MeasureSpec;->getMode(I)I

    move-result v1

    .line 1439
    sparse-switch v1, :sswitch_data_0

    .line 1447
    new-instance v0, Ljava/lang/IllegalArgumentException;

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "Unknown measure mode: "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 1443
    :sswitch_1
    invoke-static {v0, p2}, Ljava/lang/Math;->min(II)I

    move-result v0

    invoke-static {v0, v2}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result p1

    goto :goto_0

    .line 1445
    :sswitch_2
    invoke-static {p2, v2}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result p1

    goto :goto_0

    .line 1439
    :sswitch_data_0
    .sparse-switch
        -0x80000000 -> :sswitch_1
        0x0 -> :sswitch_2
        0x40000000 -> :sswitch_0
    .end sparse-switch
.end method

.method private x(Ljava/lang/String;)I
    .locals 2

    .prologue
    .line 1795
    iget-object v0, p0, Lmiui/widget/NumberPicker;->Ec:[Ljava/lang/String;

    if-nez v0, :cond_0

    .line 1797
    :try_start_0
    invoke-static {p1}, Ljava/lang/Integer;->parseInt(Ljava/lang/String;)I
    :try_end_0
    .catch Ljava/lang/NumberFormatException; {:try_start_0 .. :try_end_0} :catch_0

    move-result v0

    .line 1821
    :goto_0
    return v0

    .line 1802
    :cond_0
    const/4 v0, 0x0

    :goto_1
    iget-object v1, p0, Lmiui/widget/NumberPicker;->Ec:[Ljava/lang/String;

    array-length v1, v1

    if-ge v0, v1, :cond_2

    .line 1804
    invoke-virtual {p1}, Ljava/lang/String;->toLowerCase()Ljava/lang/String;

    move-result-object p1

    .line 1805
    iget-object v1, p0, Lmiui/widget/NumberPicker;->Ec:[Ljava/lang/String;

    aget-object v1, v1, v0

    invoke-virtual {v1}, Ljava/lang/String;->toLowerCase()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v1, p1}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v1

    if-eqz v1, :cond_1

    .line 1806
    iget v1, p0, Lmiui/widget/NumberPicker;->Ed:I

    add-int/2addr v0, v1

    goto :goto_0

    .line 1802
    :cond_1
    add-int/lit8 v0, v0, 0x1

    goto :goto_1

    .line 1815
    :cond_2
    :try_start_1
    invoke-static {p1}, Ljava/lang/Integer;->parseInt(Ljava/lang/String;)I
    :try_end_1
    .catch Ljava/lang/NumberFormatException; {:try_start_1 .. :try_end_1} :catch_1

    move-result v0

    goto :goto_0

    .line 1798
    :catch_0
    move-exception v0

    .line 1821
    :goto_2
    iget v0, p0, Lmiui/widget/NumberPicker;->Ed:I

    goto :goto_0

    .line 1816
    :catch_1
    move-exception v0

    goto :goto_2
.end method


# virtual methods
.method public computeScroll()V
    .locals 4

    .prologue
    .line 965
    iget-object v0, p0, Lmiui/widget/NumberPicker;->Er:Landroid/widget/Scroller;

    .line 966
    invoke-virtual {v0}, Landroid/widget/Scroller;->isFinished()Z

    move-result v1

    if-eqz v1, :cond_0

    .line 967
    iget-object v0, p0, Lmiui/widget/NumberPicker;->Es:Landroid/widget/Scroller;

    .line 968
    invoke-virtual {v0}, Landroid/widget/Scroller;->isFinished()Z

    move-result v1

    if-eqz v1, :cond_0

    .line 984
    :goto_0
    return-void

    .line 972
    :cond_0
    invoke-virtual {v0}, Landroid/widget/Scroller;->computeScrollOffset()Z

    .line 973
    invoke-virtual {v0}, Landroid/widget/Scroller;->getCurrY()I

    move-result v1

    .line 974
    iget v2, p0, Lmiui/widget/NumberPicker;->Et:I

    if-nez v2, :cond_1

    .line 975
    invoke-virtual {v0}, Landroid/widget/Scroller;->getStartY()I

    move-result v2

    iput v2, p0, Lmiui/widget/NumberPicker;->Et:I

    .line 977
    :cond_1
    const/4 v2, 0x0

    iget v3, p0, Lmiui/widget/NumberPicker;->Et:I

    sub-int v3, v1, v3

    invoke-virtual {p0, v2, v3}, Lmiui/widget/NumberPicker;->scrollBy(II)V

    .line 978
    iput v1, p0, Lmiui/widget/NumberPicker;->Et:I

    .line 979
    invoke-virtual {v0}, Landroid/widget/Scroller;->isFinished()Z

    move-result v1

    if-eqz v1, :cond_2

    .line 980
    invoke-direct {p0, v0}, Lmiui/widget/NumberPicker;->b(Landroid/widget/Scroller;)V

    goto :goto_0

    .line 982
    :cond_2
    invoke-virtual {p0}, Lmiui/widget/NumberPicker;->invalidate()V

    goto :goto_0
.end method

.method protected dispatchHoverEvent(Landroid/view/MotionEvent;)Z
    .locals 8

    .prologue
    const/4 v7, 0x0

    const/16 v6, 0x100

    const/16 v5, 0x80

    const/16 v4, 0x40

    const/4 v3, -0x1

    .line 915
    iget-boolean v0, p0, Lmiui/widget/NumberPicker;->EF:Z

    if-nez v0, :cond_0

    .line 916
    invoke-super {p0, p1}, Landroid/widget/LinearLayout;->dispatchHoverEvent(Landroid/view/MotionEvent;)Z

    move-result v0

    .line 960
    :goto_0
    return v0

    .line 918
    :cond_0
    invoke-virtual {p0}, Lmiui/widget/NumberPicker;->getContext()Landroid/content/Context;

    move-result-object v0

    const-string v1, "accessibility"

    invoke-virtual {v0, v1}, Landroid/content/Context;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/view/accessibility/AccessibilityManager;

    .line 919
    invoke-virtual {v0}, Landroid/view/accessibility/AccessibilityManager;->isEnabled()Z

    move-result v0

    if-eqz v0, :cond_1

    .line 920
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getY()F

    move-result v0

    float-to-int v0, v0

    .line 922
    iget v1, p0, Lmiui/widget/NumberPicker;->EK:I

    if-ge v0, v1, :cond_2

    .line 923
    const/4 v0, 0x3

    move v1, v0

    .line 929
    :goto_1
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getActionMasked()I

    move-result v2

    .line 930
    invoke-virtual {p0}, Lmiui/widget/NumberPicker;->getAccessibilityNodeProvider()Landroid/view/accessibility/AccessibilityNodeProvider;

    move-result-object v0

    check-cast v0, Lmiui/widget/NumberPicker$d;

    .line 932
    packed-switch v2, :pswitch_data_0

    .line 960
    :cond_1
    :goto_2
    :pswitch_0
    const/4 v0, 0x0

    goto :goto_0

    .line 924
    :cond_2
    iget v1, p0, Lmiui/widget/NumberPicker;->EL:I

    if-le v0, v1, :cond_3

    .line 925
    const/4 v0, 0x1

    move v1, v0

    goto :goto_1

    .line 927
    :cond_3
    const/4 v0, 0x2

    move v1, v0

    goto :goto_1

    .line 934
    :pswitch_1
    invoke-virtual {v0, v1, v5}, Lmiui/widget/NumberPicker$d;->h(II)V

    .line 936
    iput v1, p0, Lmiui/widget/NumberPicker;->EM:I

    .line 937
    invoke-virtual {v0, v1, v4, v7}, Lmiui/widget/NumberPicker$d;->performAction(IILandroid/os/Bundle;)Z

    goto :goto_2

    .line 941
    :pswitch_2
    iget v2, p0, Lmiui/widget/NumberPicker;->EM:I

    if-eq v2, v1, :cond_1

    iget v2, p0, Lmiui/widget/NumberPicker;->EM:I

    if-eq v2, v3, :cond_1

    .line 943
    iget v2, p0, Lmiui/widget/NumberPicker;->EM:I

    invoke-virtual {v0, v2, v6}, Lmiui/widget/NumberPicker$d;->h(II)V

    .line 946
    invoke-virtual {v0, v1, v5}, Lmiui/widget/NumberPicker$d;->h(II)V

    .line 948
    iput v1, p0, Lmiui/widget/NumberPicker;->EM:I

    .line 949
    invoke-virtual {v0, v1, v4, v7}, Lmiui/widget/NumberPicker$d;->performAction(IILandroid/os/Bundle;)Z

    goto :goto_2

    .line 954
    :pswitch_3
    invoke-virtual {v0, v1, v6}, Lmiui/widget/NumberPicker$d;->h(II)V

    .line 956
    iput v3, p0, Lmiui/widget/NumberPicker;->EM:I

    goto :goto_2

    .line 932
    :pswitch_data_0
    .packed-switch 0x7
        :pswitch_2
        :pswitch_0
        :pswitch_1
        :pswitch_3
    .end packed-switch
.end method

.method public dispatchKeyEvent(Landroid/view/KeyEvent;)Z
    .locals 5

    .prologue
    const/16 v4, 0x14

    const/4 v1, 0x1

    .line 866
    invoke-virtual {p1}, Landroid/view/KeyEvent;->getKeyCode()I

    move-result v0

    .line 867
    sparse-switch v0, :sswitch_data_0

    .line 898
    :cond_0
    :goto_0
    invoke-super {p0, p1}, Landroid/widget/LinearLayout;->dispatchKeyEvent(Landroid/view/KeyEvent;)Z

    move-result v1

    :cond_1
    :goto_1
    return v1

    .line 870
    :sswitch_0
    invoke-direct {p0}, Lmiui/widget/NumberPicker;->di()V

    goto :goto_0

    .line 874
    :sswitch_1
    iget-boolean v2, p0, Lmiui/widget/NumberPicker;->EF:Z

    if-eqz v2, :cond_0

    .line 877
    invoke-virtual {p1}, Landroid/view/KeyEvent;->getAction()I

    move-result v2

    packed-switch v2, :pswitch_data_0

    goto :goto_0

    .line 879
    :pswitch_0
    iget-boolean v2, p0, Lmiui/widget/NumberPicker;->ED:Z

    if-nez v2, :cond_2

    if-ne v0, v4, :cond_3

    :cond_2
    invoke-virtual {p0}, Lmiui/widget/NumberPicker;->getValue()I

    move-result v2

    invoke-virtual {p0}, Lmiui/widget/NumberPicker;->getMaxValue()I

    move-result v3

    if-ge v2, v3, :cond_0

    .line 881
    :goto_2
    invoke-virtual {p0}, Lmiui/widget/NumberPicker;->requestFocus()Z

    .line 882
    iput v0, p0, Lmiui/widget/NumberPicker;->EU:I

    .line 883
    invoke-direct {p0}, Lmiui/widget/NumberPicker;->di()V

    .line 884
    iget-object v2, p0, Lmiui/widget/NumberPicker;->Er:Landroid/widget/Scroller;

    invoke-virtual {v2}, Landroid/widget/Scroller;->isFinished()Z

    move-result v2

    if-eqz v2, :cond_1

    .line 885
    if-ne v0, v4, :cond_4

    move v0, v1

    :goto_3
    invoke-direct {p0, v0}, Lmiui/widget/NumberPicker;->k(Z)V

    goto :goto_1

    .line 879
    :cond_3
    invoke-virtual {p0}, Lmiui/widget/NumberPicker;->getValue()I

    move-result v2

    invoke-virtual {p0}, Lmiui/widget/NumberPicker;->getMinValue()I

    move-result v3

    if-le v2, v3, :cond_0

    goto :goto_2

    .line 885
    :cond_4
    const/4 v0, 0x0

    goto :goto_3

    .line 891
    :pswitch_1
    iget v2, p0, Lmiui/widget/NumberPicker;->EU:I

    if-ne v2, v0, :cond_0

    .line 892
    const/4 v0, -0x1

    iput v0, p0, Lmiui/widget/NumberPicker;->EU:I

    goto :goto_1

    .line 867
    nop

    :sswitch_data_0
    .sparse-switch
        0x13 -> :sswitch_1
        0x14 -> :sswitch_1
        0x17 -> :sswitch_0
        0x42 -> :sswitch_0
    .end sparse-switch

    .line 877
    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_0
        :pswitch_1
    .end packed-switch
.end method

.method public dispatchTouchEvent(Landroid/view/MotionEvent;)Z
    .locals 1

    .prologue
    .line 854
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getActionMasked()I

    move-result v0

    .line 855
    packed-switch v0, :pswitch_data_0

    .line 861
    :goto_0
    :pswitch_0
    invoke-super {p0, p1}, Landroid/widget/LinearLayout;->dispatchTouchEvent(Landroid/view/MotionEvent;)Z

    move-result v0

    return v0

    .line 858
    :pswitch_1
    invoke-direct {p0}, Lmiui/widget/NumberPicker;->di()V

    goto :goto_0

    .line 855
    :pswitch_data_0
    .packed-switch 0x1
        :pswitch_1
        :pswitch_0
        :pswitch_1
    .end packed-switch
.end method

.method public dispatchTrackballEvent(Landroid/view/MotionEvent;)Z
    .locals 1

    .prologue
    .line 903
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getActionMasked()I

    move-result v0

    .line 904
    packed-switch v0, :pswitch_data_0

    .line 910
    :goto_0
    :pswitch_0
    invoke-super {p0, p1}, Landroid/widget/LinearLayout;->dispatchTrackballEvent(Landroid/view/MotionEvent;)Z

    move-result v0

    return v0

    .line 907
    :pswitch_1
    invoke-direct {p0}, Lmiui/widget/NumberPicker;->di()V

    goto :goto_0

    .line 904
    :pswitch_data_0
    .packed-switch 0x1
        :pswitch_1
        :pswitch_0
        :pswitch_1
    .end packed-switch
.end method

.method protected drawableStateChanged()V
    .locals 0

    .prologue
    .line 648
    invoke-super {p0}, Landroid/widget/LinearLayout;->drawableStateChanged()V

    .line 650
    invoke-direct {p0}, Lmiui/widget/NumberPicker;->da()V

    .line 651
    return-void
.end method

.method public getAccessibilityNodeProvider()Landroid/view/accessibility/AccessibilityNodeProvider;
    .locals 1

    .prologue
    .line 1417
    iget-boolean v0, p0, Lmiui/widget/NumberPicker;->EF:Z

    if-nez v0, :cond_0

    .line 1418
    invoke-super {p0}, Landroid/widget/LinearLayout;->getAccessibilityNodeProvider()Landroid/view/accessibility/AccessibilityNodeProvider;

    move-result-object v0

    .line 1423
    :goto_0
    return-object v0

    .line 1420
    :cond_0
    iget-object v0, p0, Lmiui/widget/NumberPicker;->EP:Lmiui/widget/NumberPicker$d;

    if-nez v0, :cond_1

    .line 1421
    new-instance v0, Lmiui/widget/NumberPicker$d;

    invoke-direct {v0, p0}, Lmiui/widget/NumberPicker$d;-><init>(Lmiui/widget/NumberPicker;)V

    iput-object v0, p0, Lmiui/widget/NumberPicker;->EP:Lmiui/widget/NumberPicker$d;

    .line 1423
    :cond_1
    iget-object v0, p0, Lmiui/widget/NumberPicker;->EP:Lmiui/widget/NumberPicker$d;

    goto :goto_0
.end method

.method protected getBottomFadingEdgeStrength()F
    .locals 1

    .prologue
    .line 1328
    const v0, 0x3f666666

    return v0
.end method

.method public getDisplayedValues()[Ljava/lang/String;
    .locals 1

    .prologue
    .line 1282
    iget-object v0, p0, Lmiui/widget/NumberPicker;->Ec:[Ljava/lang/String;

    return-object v0
.end method

.method public getMaxValue()I
    .locals 1

    .prologue
    .line 1244
    iget v0, p0, Lmiui/widget/NumberPicker;->Ee:I

    return v0
.end method

.method public getMinValue()I
    .locals 1

    .prologue
    .line 1206
    iget v0, p0, Lmiui/widget/NumberPicker;->Ed:I

    return v0
.end method

.method protected getTopFadingEdgeStrength()F
    .locals 1

    .prologue
    .line 1323
    const v0, 0x3f666666

    return v0
.end method

.method public getValue()I
    .locals 1

    .prologue
    .line 1197
    iget v0, p0, Lmiui/widget/NumberPicker;->Ef:I

    return v0
.end method

.method public getWrapSelectorWheel()Z
    .locals 1

    .prologue
    .line 1149
    iget-boolean v0, p0, Lmiui/widget/NumberPicker;->ED:Z

    return v0
.end method

.method protected onDetachedFromWindow()V
    .locals 1

    .prologue
    .line 1333
    invoke-super {p0}, Landroid/widget/LinearLayout;->onDetachedFromWindow()V

    .line 1334
    invoke-direct {p0}, Lmiui/widget/NumberPicker;->di()V

    .line 1335
    iget-object v0, p0, Lmiui/widget/NumberPicker;->Fj:Landroid/media/SoundPool;

    invoke-virtual {v0}, Landroid/media/SoundPool;->release()V

    .line 1336
    return-void
.end method

.method protected onDraw(Landroid/graphics/Canvas;)V
    .locals 14

    .prologue
    const/4 v2, 0x0

    const/high16 v13, 0x40000000

    .line 1340
    iget-boolean v0, p0, Lmiui/widget/NumberPicker;->EF:Z

    if-nez v0, :cond_1

    .line 1341
    invoke-super {p0, p1}, Landroid/widget/LinearLayout;->onDraw(Landroid/graphics/Canvas;)V

    .line 1388
    :cond_0
    :goto_0
    return-void

    .line 1344
    :cond_1
    invoke-virtual {p0}, Lmiui/widget/NumberPicker;->getWidth()I

    move-result v1

    .line 1346
    invoke-virtual {p0}, Lmiui/widget/NumberPicker;->getPaddingLeft()I

    move-result v3

    .line 1347
    invoke-virtual {p0}, Lmiui/widget/NumberPicker;->getPaddingRight()I

    move-result v4

    .line 1348
    invoke-virtual {p0}, Lmiui/widget/NumberPicker;->getRight()I

    move-result v0

    invoke-virtual {p0}, Lmiui/widget/NumberPicker;->getLeft()I

    move-result v5

    sub-int/2addr v0, v5

    add-int/2addr v0, v3

    sub-int/2addr v0, v4

    div-int/lit8 v0, v0, 0x2

    int-to-float v5, v0

    .line 1349
    iget v0, p0, Lmiui/widget/NumberPicker;->Eq:I

    int-to-float v0, v0

    .line 1352
    iget-object v6, p0, Lmiui/widget/NumberPicker;->En:Landroid/graphics/drawable/Drawable;

    if-eqz v6, :cond_3

    iget v6, p0, Lmiui/widget/NumberPicker;->zY:I

    if-nez v6, :cond_3

    .line 1354
    iget-boolean v6, p0, Lmiui/widget/NumberPicker;->EO:Z

    if-eqz v6, :cond_2

    .line 1355
    iget-object v6, p0, Lmiui/widget/NumberPicker;->En:Landroid/graphics/drawable/Drawable;

    sget-object v7, Lmiui/widget/NumberPicker;->EV:[I

    invoke-virtual {v6, v7}, Landroid/graphics/drawable/Drawable;->setState([I)Z

    .line 1356
    iget-object v6, p0, Lmiui/widget/NumberPicker;->En:Landroid/graphics/drawable/Drawable;

    sub-int v7, v1, v4

    iget v8, p0, Lmiui/widget/NumberPicker;->EK:I

    invoke-virtual {v6, v3, v2, v7, v8}, Landroid/graphics/drawable/Drawable;->setBounds(IIII)V

    .line 1357
    iget-object v6, p0, Lmiui/widget/NumberPicker;->En:Landroid/graphics/drawable/Drawable;

    invoke-virtual {v6, p1}, Landroid/graphics/drawable/Drawable;->draw(Landroid/graphics/Canvas;)V

    .line 1359
    :cond_2
    iget-boolean v6, p0, Lmiui/widget/NumberPicker;->EN:Z

    if-eqz v6, :cond_3

    .line 1360
    iget-object v6, p0, Lmiui/widget/NumberPicker;->En:Landroid/graphics/drawable/Drawable;

    sget-object v7, Lmiui/widget/NumberPicker;->EV:[I

    invoke-virtual {v6, v7}, Landroid/graphics/drawable/Drawable;->setState([I)Z

    .line 1361
    iget-object v6, p0, Lmiui/widget/NumberPicker;->En:Landroid/graphics/drawable/Drawable;

    iget v7, p0, Lmiui/widget/NumberPicker;->EL:I

    sub-int/2addr v1, v4

    invoke-virtual {p0}, Lmiui/widget/NumberPicker;->getBottom()I

    move-result v4

    invoke-virtual {v6, v3, v7, v1, v4}, Landroid/graphics/drawable/Drawable;->setBounds(IIII)V

    .line 1363
    iget-object v1, p0, Lmiui/widget/NumberPicker;->En:Landroid/graphics/drawable/Drawable;

    invoke-virtual {v1, p1}, Landroid/graphics/drawable/Drawable;->draw(Landroid/graphics/Canvas;)V

    .line 1367
    :cond_3
    iget v1, p0, Lmiui/widget/NumberPicker;->Ep:I

    iget v3, p0, Lmiui/widget/NumberPicker;->Eo:I

    mul-int/lit8 v3, v3, 0x2

    add-int/2addr v1, v3

    int-to-float v4, v1

    .line 1368
    iget-object v6, p0, Lmiui/widget/NumberPicker;->Ek:Landroid/util/SparseArray;

    .line 1369
    iget-object v7, p0, Lmiui/widget/NumberPicker;->El:[I

    move v1, v2

    move v3, v0

    .line 1370
    :goto_1
    array-length v0, v7

    if-ge v1, v0, :cond_5

    .line 1371
    aget v0, v7, v1

    .line 1372
    invoke-virtual {v6, v0}, Landroid/util/SparseArray;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/String;

    .line 1373
    sub-float v8, v4, v3

    invoke-static {v8}, Ljava/lang/Math;->abs(F)F

    move-result v8

    iget v9, p0, Lmiui/widget/NumberPicker;->Eo:I

    int-to-float v9, v9

    div-float/2addr v8, v9

    .line 1374
    iget v9, p0, Lmiui/widget/NumberPicker;->EZ:I

    iget v10, p0, Lmiui/widget/NumberPicker;->Fa:I

    invoke-direct {p0, v8, v9, v10}, Lmiui/widget/NumberPicker;->a(FII)F

    move-result v9

    .line 1375
    iget-object v10, p0, Lmiui/widget/NumberPicker;->Em:Landroid/graphics/Paint;

    invoke-virtual {v10, v9}, Landroid/graphics/Paint;->setTextSize(F)V

    .line 1376
    iget-object v10, p0, Lmiui/widget/NumberPicker;->Em:Landroid/graphics/Paint;

    iget v11, p0, Lmiui/widget/NumberPicker;->Fd:I

    invoke-direct {p0, v8, v11, v2}, Lmiui/widget/NumberPicker;->a(FIZ)I

    move-result v11

    invoke-virtual {v10, v11}, Landroid/graphics/Paint;->setColor(I)V

    .line 1377
    iget v10, p0, Lmiui/widget/NumberPicker;->Fa:I

    int-to-float v10, v10

    sub-float v10, v9, v10

    div-float/2addr v10, v13

    add-float/2addr v10, v3

    iget-object v11, p0, Lmiui/widget/NumberPicker;->Em:Landroid/graphics/Paint;

    invoke-virtual {p1, v0, v5, v10, v11}, Landroid/graphics/Canvas;->drawText(Ljava/lang/String;FFLandroid/graphics/Paint;)V

    .line 1378
    const/high16 v10, 0x3f800000

    cmpg-float v10, v8, v10

    if-gez v10, :cond_4

    .line 1379
    iget-object v10, p0, Lmiui/widget/NumberPicker;->Em:Landroid/graphics/Paint;

    iget v11, p0, Lmiui/widget/NumberPicker;->Fc:I

    const/4 v12, 0x1

    invoke-direct {p0, v8, v11, v12}, Lmiui/widget/NumberPicker;->a(FIZ)I

    move-result v8

    invoke-virtual {v10, v8}, Landroid/graphics/Paint;->setColor(I)V

    .line 1380
    iget v8, p0, Lmiui/widget/NumberPicker;->Fa:I

    int-to-float v8, v8

    sub-float v8, v9, v8

    div-float/2addr v8, v13

    add-float/2addr v8, v3

    iget-object v9, p0, Lmiui/widget/NumberPicker;->Em:Landroid/graphics/Paint;

    invoke-virtual {p1, v0, v5, v8, v9}, Landroid/graphics/Canvas;->drawText(Ljava/lang/String;FFLandroid/graphics/Paint;)V

    .line 1382
    :cond_4
    iget v0, p0, Lmiui/widget/NumberPicker;->Eo:I

    int-to-float v0, v0

    add-float/2addr v3, v0

    .line 1370
    add-int/lit8 v0, v1, 0x1

    move v1, v0

    goto :goto_1

    .line 1384
    :cond_5
    iget-object v0, p0, Lmiui/widget/NumberPicker;->Fi:Ljava/lang/CharSequence;

    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_0

    .line 1385
    iget v0, p0, Lmiui/widget/NumberPicker;->EZ:I

    div-int/lit8 v0, v0, 0x2

    int-to-float v0, v0

    sub-float v0, v4, v0

    iget v1, p0, Lmiui/widget/NumberPicker;->Fb:I

    div-int/lit8 v1, v1, 0x2

    int-to-float v1, v1

    add-float/2addr v0, v1

    iget v1, p0, Lmiui/widget/NumberPicker;->DR:I

    int-to-float v1, v1

    add-float/2addr v0, v1

    .line 1386
    iget-object v1, p0, Lmiui/widget/NumberPicker;->Fi:Ljava/lang/CharSequence;

    invoke-virtual {v1}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v1

    iget v2, p0, Lmiui/widget/NumberPicker;->EY:F

    div-float/2addr v2, v13

    add-float/2addr v2, v5

    iget v3, p0, Lmiui/widget/NumberPicker;->DQ:I

    int-to-float v3, v3

    add-float/2addr v2, v3

    iget-object v3, p0, Lmiui/widget/NumberPicker;->EW:Landroid/graphics/Paint;

    invoke-virtual {p1, v1, v2, v0, v3}, Landroid/graphics/Canvas;->drawText(Ljava/lang/String;FFLandroid/graphics/Paint;)V

    goto/16 :goto_0
.end method

.method public onInitializeAccessibilityEvent(Landroid/view/accessibility/AccessibilityEvent;)V
    .locals 2

    .prologue
    .line 1408
    invoke-super {p0, p1}, Landroid/widget/LinearLayout;->onInitializeAccessibilityEvent(Landroid/view/accessibility/AccessibilityEvent;)V

    .line 1409
    const-class v0, Lmiui/widget/NumberPicker;

    invoke-virtual {v0}, Ljava/lang/Class;->getName()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p1, v0}, Landroid/view/accessibility/AccessibilityEvent;->setClassName(Ljava/lang/CharSequence;)V

    .line 1410
    const/4 v0, 0x1

    invoke-virtual {p1, v0}, Landroid/view/accessibility/AccessibilityEvent;->setScrollable(Z)V

    .line 1411
    iget v0, p0, Lmiui/widget/NumberPicker;->Ed:I

    iget v1, p0, Lmiui/widget/NumberPicker;->Ef:I

    add-int/2addr v0, v1

    iget v1, p0, Lmiui/widget/NumberPicker;->Eo:I

    mul-int/2addr v0, v1

    invoke-virtual {p1, v0}, Landroid/view/accessibility/AccessibilityEvent;->setScrollY(I)V

    .line 1412
    iget v0, p0, Lmiui/widget/NumberPicker;->Ee:I

    iget v1, p0, Lmiui/widget/NumberPicker;->Ed:I

    sub-int/2addr v0, v1

    iget v1, p0, Lmiui/widget/NumberPicker;->Eo:I

    mul-int/2addr v0, v1

    invoke-virtual {p1, v0}, Landroid/view/accessibility/AccessibilityEvent;->setMaxScrollY(I)V

    .line 1413
    return-void
.end method

.method public onInterceptTouchEvent(Landroid/view/MotionEvent;)Z
    .locals 4

    .prologue
    const/4 v1, 0x0

    const/4 v0, 0x1

    .line 730
    iget-boolean v2, p0, Lmiui/widget/NumberPicker;->EF:Z

    if-eqz v2, :cond_0

    invoke-virtual {p0}, Lmiui/widget/NumberPicker;->isEnabled()Z

    move-result v2

    if-nez v2, :cond_1

    :cond_0
    move v0, v1

    .line 775
    :goto_0
    return v0

    .line 733
    :cond_1
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getActionMasked()I

    move-result v2

    .line 734
    packed-switch v2, :pswitch_data_0

    move v0, v1

    .line 775
    goto :goto_0

    .line 736
    :pswitch_0
    invoke-direct {p0}, Lmiui/widget/NumberPicker;->di()V

    .line 737
    iget-object v2, p0, Lmiui/widget/NumberPicker;->DT:Landroid/widget/EditText;

    const/4 v3, 0x4

    invoke-virtual {v2, v3}, Landroid/widget/EditText;->setVisibility(I)V

    .line 738
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getY()F

    move-result v2

    iput v2, p0, Lmiui/widget/NumberPicker;->Ex:F

    iput v2, p0, Lmiui/widget/NumberPicker;->Ez:F

    .line 739
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getEventTime()J

    move-result-wide v2

    iput-wide v2, p0, Lmiui/widget/NumberPicker;->Ey:J

    .line 740
    iput-boolean v1, p0, Lmiui/widget/NumberPicker;->EI:Z

    .line 741
    iput-boolean v1, p0, Lmiui/widget/NumberPicker;->EJ:Z

    .line 743
    iget v2, p0, Lmiui/widget/NumberPicker;->Ex:F

    iget v3, p0, Lmiui/widget/NumberPicker;->EK:I

    int-to-float v3, v3

    cmpg-float v2, v2, v3

    if-gez v2, :cond_3

    .line 744
    iget v2, p0, Lmiui/widget/NumberPicker;->zY:I

    if-nez v2, :cond_2

    .line 745
    iget-object v2, p0, Lmiui/widget/NumberPicker;->EQ:Lmiui/widget/NumberPicker$e;

    const/4 v3, 0x2

    invoke-virtual {v2, v3}, Lmiui/widget/NumberPicker$e;->ak(I)V

    .line 755
    :cond_2
    :goto_1
    iget-object v2, p0, Lmiui/widget/NumberPicker;->Er:Landroid/widget/Scroller;

    invoke-virtual {v2}, Landroid/widget/Scroller;->isFinished()Z

    move-result v2

    if-nez v2, :cond_4

    .line 756
    iget-object v2, p0, Lmiui/widget/NumberPicker;->Er:Landroid/widget/Scroller;

    invoke-virtual {v2, v0}, Landroid/widget/Scroller;->forceFinished(Z)V

    .line 757
    iget-object v2, p0, Lmiui/widget/NumberPicker;->Es:Landroid/widget/Scroller;

    invoke-virtual {v2, v0}, Landroid/widget/Scroller;->forceFinished(Z)V

    .line 758
    invoke-direct {p0, v1}, Lmiui/widget/NumberPicker;->aa(I)V

    goto :goto_0

    .line 748
    :cond_3
    iget v2, p0, Lmiui/widget/NumberPicker;->Ex:F

    iget v3, p0, Lmiui/widget/NumberPicker;->EL:I

    int-to-float v3, v3

    cmpl-float v2, v2, v3

    if-lez v2, :cond_2

    .line 749
    iget v2, p0, Lmiui/widget/NumberPicker;->zY:I

    if-nez v2, :cond_2

    .line 750
    iget-object v2, p0, Lmiui/widget/NumberPicker;->EQ:Lmiui/widget/NumberPicker$e;

    invoke-virtual {v2, v0}, Lmiui/widget/NumberPicker$e;->ak(I)V

    goto :goto_1

    .line 759
    :cond_4
    iget-object v2, p0, Lmiui/widget/NumberPicker;->Es:Landroid/widget/Scroller;

    invoke-virtual {v2}, Landroid/widget/Scroller;->isFinished()Z

    move-result v2

    if-nez v2, :cond_5

    .line 760
    iget-object v1, p0, Lmiui/widget/NumberPicker;->Er:Landroid/widget/Scroller;

    invoke-virtual {v1, v0}, Landroid/widget/Scroller;->forceFinished(Z)V

    .line 761
    iget-object v1, p0, Lmiui/widget/NumberPicker;->Es:Landroid/widget/Scroller;

    invoke-virtual {v1, v0}, Landroid/widget/Scroller;->forceFinished(Z)V

    goto :goto_0

    .line 762
    :cond_5
    iget v2, p0, Lmiui/widget/NumberPicker;->Ex:F

    iget v3, p0, Lmiui/widget/NumberPicker;->EK:I

    int-to-float v3, v3

    cmpg-float v2, v2, v3

    if-gez v2, :cond_6

    .line 763
    invoke-static {}, Landroid/view/ViewConfiguration;->getLongPressTimeout()I

    move-result v2

    int-to-long v2, v2

    invoke-direct {p0, v1, v2, v3}, Lmiui/widget/NumberPicker;->a(ZJ)V

    goto/16 :goto_0

    .line 765
    :cond_6
    iget v1, p0, Lmiui/widget/NumberPicker;->Ex:F

    iget v2, p0, Lmiui/widget/NumberPicker;->EL:I

    int-to-float v2, v2

    cmpl-float v1, v1, v2

    if-lez v1, :cond_7

    .line 766
    invoke-static {}, Landroid/view/ViewConfiguration;->getLongPressTimeout()I

    move-result v1

    int-to-long v1, v1

    invoke-direct {p0, v0, v1, v2}, Lmiui/widget/NumberPicker;->a(ZJ)V

    goto/16 :goto_0

    .line 769
    :cond_7
    iput-boolean v0, p0, Lmiui/widget/NumberPicker;->EJ:Z

    .line 770
    invoke-direct {p0}, Lmiui/widget/NumberPicker;->dg()V

    goto/16 :goto_0

    .line 734
    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_0
    .end packed-switch
.end method

.method protected onLayout(ZIIII)V
    .locals 5

    .prologue
    .line 655
    iget-boolean v0, p0, Lmiui/widget/NumberPicker;->EF:Z

    if-nez v0, :cond_1

    .line 656
    invoke-super/range {p0 .. p5}, Landroid/widget/LinearLayout;->onLayout(ZIIII)V

    .line 680
    :cond_0
    :goto_0
    return-void

    .line 659
    :cond_1
    invoke-virtual {p0}, Lmiui/widget/NumberPicker;->getMeasuredWidth()I

    move-result v0

    .line 660
    invoke-virtual {p0}, Lmiui/widget/NumberPicker;->getMeasuredHeight()I

    move-result v1

    .line 663
    iget-object v2, p0, Lmiui/widget/NumberPicker;->DT:Landroid/widget/EditText;

    invoke-virtual {v2}, Landroid/widget/EditText;->getMeasuredWidth()I

    move-result v2

    .line 664
    iget-object v3, p0, Lmiui/widget/NumberPicker;->DT:Landroid/widget/EditText;

    invoke-virtual {v3}, Landroid/widget/EditText;->getMeasuredHeight()I

    move-result v3

    .line 665
    sub-int/2addr v0, v2

    div-int/lit8 v0, v0, 0x2

    .line 666
    sub-int/2addr v1, v3

    div-int/lit8 v1, v1, 0x2

    .line 667
    add-int/2addr v2, v0

    .line 668
    add-int/2addr v3, v1

    .line 669
    iget-object v4, p0, Lmiui/widget/NumberPicker;->DT:Landroid/widget/EditText;

    invoke-virtual {v4, v0, v1, v2, v3}, Landroid/widget/EditText;->layout(IIII)V

    .line 671
    if-eqz p1, :cond_0

    .line 673
    invoke-direct {p0}, Lmiui/widget/NumberPicker;->dc()V

    .line 674
    invoke-direct {p0}, Lmiui/widget/NumberPicker;->dd()V

    .line 675
    invoke-virtual {p0}, Lmiui/widget/NumberPicker;->getHeight()I

    move-result v0

    iget v1, p0, Lmiui/widget/NumberPicker;->DU:I

    sub-int/2addr v0, v1

    div-int/lit8 v0, v0, 0x2

    iget v1, p0, Lmiui/widget/NumberPicker;->EH:I

    sub-int/2addr v0, v1

    iput v0, p0, Lmiui/widget/NumberPicker;->EK:I

    .line 677
    iget v0, p0, Lmiui/widget/NumberPicker;->EK:I

    iget v1, p0, Lmiui/widget/NumberPicker;->EH:I

    mul-int/lit8 v1, v1, 0x2

    add-int/2addr v0, v1

    iget v1, p0, Lmiui/widget/NumberPicker;->DU:I

    add-int/2addr v0, v1

    iput v0, p0, Lmiui/widget/NumberPicker;->EL:I

    goto :goto_0
.end method

.method protected onMeasure(II)V
    .locals 3

    .prologue
    .line 684
    iget-boolean v0, p0, Lmiui/widget/NumberPicker;->EF:Z

    if-nez v0, :cond_0

    .line 685
    invoke-super {p0, p1, p2}, Landroid/widget/LinearLayout;->onMeasure(II)V

    .line 698
    :goto_0
    return-void

    .line 689
    :cond_0
    iget v0, p0, Lmiui/widget/NumberPicker;->DY:I

    invoke-direct {p0, p1, v0}, Lmiui/widget/NumberPicker;->makeMeasureSpec(II)I

    move-result v0

    .line 690
    iget v1, p0, Lmiui/widget/NumberPicker;->DW:I

    invoke-direct {p0, p2, v1}, Lmiui/widget/NumberPicker;->makeMeasureSpec(II)I

    move-result v1

    .line 691
    invoke-super {p0, v0, v1}, Landroid/widget/LinearLayout;->onMeasure(II)V

    .line 693
    iget v0, p0, Lmiui/widget/NumberPicker;->DX:I

    invoke-virtual {p0}, Lmiui/widget/NumberPicker;->getMeasuredWidth()I

    move-result v1

    invoke-direct {p0, v0, v1, p1}, Lmiui/widget/NumberPicker;->i(III)I

    move-result v0

    .line 695
    iget v1, p0, Lmiui/widget/NumberPicker;->DV:I

    invoke-virtual {p0}, Lmiui/widget/NumberPicker;->getMeasuredHeight()I

    move-result v2

    invoke-direct {p0, v1, v2, p2}, Lmiui/widget/NumberPicker;->i(III)I

    move-result v1

    .line 697
    invoke-virtual {p0, v0, v1}, Lmiui/widget/NumberPicker;->setMeasuredDimension(II)V

    goto :goto_0
.end method

.method public onTouchEvent(Landroid/view/MotionEvent;)Z
    .locals 9

    .prologue
    const/4 v8, 0x2

    const/4 v1, 0x1

    const/4 v2, 0x0

    .line 780
    invoke-virtual {p0}, Lmiui/widget/NumberPicker;->isEnabled()Z

    move-result v0

    if-eqz v0, :cond_0

    iget-boolean v0, p0, Lmiui/widget/NumberPicker;->EF:Z

    if-nez v0, :cond_1

    :cond_0
    move v0, v2

    .line 849
    :goto_0
    return v0

    .line 783
    :cond_1
    iget-object v0, p0, Lmiui/widget/NumberPicker;->bR:Landroid/view/VelocityTracker;

    if-nez v0, :cond_2

    .line 784
    invoke-static {}, Landroid/view/VelocityTracker;->obtain()Landroid/view/VelocityTracker;

    move-result-object v0

    iput-object v0, p0, Lmiui/widget/NumberPicker;->bR:Landroid/view/VelocityTracker;

    .line 786
    :cond_2
    iget-object v0, p0, Lmiui/widget/NumberPicker;->bR:Landroid/view/VelocityTracker;

    invoke-virtual {v0, p1}, Landroid/view/VelocityTracker;->addMovement(Landroid/view/MotionEvent;)V

    .line 787
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getActionMasked()I

    move-result v0

    .line 788
    packed-switch v0, :pswitch_data_0

    :cond_3
    :goto_1
    move v0, v1

    .line 849
    goto :goto_0

    .line 790
    :pswitch_0
    iget-boolean v0, p0, Lmiui/widget/NumberPicker;->EI:Z

    if-nez v0, :cond_3

    .line 793
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getY()F

    move-result v0

    .line 794
    iget v3, p0, Lmiui/widget/NumberPicker;->zY:I

    if-eq v3, v1, :cond_5

    .line 795
    iget v2, p0, Lmiui/widget/NumberPicker;->Ex:F

    sub-float v2, v0, v2

    invoke-static {v2}, Ljava/lang/Math;->abs(F)F

    move-result v2

    float-to-int v2, v2

    .line 796
    iget v3, p0, Lmiui/widget/NumberPicker;->cT:I

    if-le v2, v3, :cond_4

    .line 797
    invoke-direct {p0}, Lmiui/widget/NumberPicker;->di()V

    .line 798
    invoke-direct {p0, v1}, Lmiui/widget/NumberPicker;->aa(I)V

    .line 805
    :cond_4
    :goto_2
    iput v0, p0, Lmiui/widget/NumberPicker;->Ez:F

    goto :goto_1

    .line 801
    :cond_5
    iget v3, p0, Lmiui/widget/NumberPicker;->Ez:F

    sub-float v3, v0, v3

    float-to-int v3, v3

    .line 802
    invoke-virtual {p0, v2, v3}, Lmiui/widget/NumberPicker;->scrollBy(II)V

    .line 803
    invoke-virtual {p0}, Lmiui/widget/NumberPicker;->invalidate()V

    goto :goto_2

    .line 808
    :pswitch_1
    invoke-direct {p0}, Lmiui/widget/NumberPicker;->dh()V

    .line 809
    invoke-direct {p0}, Lmiui/widget/NumberPicker;->df()V

    .line 810
    iget-object v0, p0, Lmiui/widget/NumberPicker;->EQ:Lmiui/widget/NumberPicker$e;

    invoke-virtual {v0}, Lmiui/widget/NumberPicker$e;->cancel()V

    .line 811
    iget-object v0, p0, Lmiui/widget/NumberPicker;->bR:Landroid/view/VelocityTracker;

    .line 812
    const/16 v3, 0x3e8

    iget v4, p0, Lmiui/widget/NumberPicker;->EB:I

    int-to-float v4, v4

    invoke-virtual {v0, v3, v4}, Landroid/view/VelocityTracker;->computeCurrentVelocity(IF)V

    .line 813
    invoke-virtual {v0}, Landroid/view/VelocityTracker;->getYVelocity()F

    move-result v0

    float-to-int v0, v0

    .line 814
    invoke-static {v0}, Ljava/lang/Math;->abs(I)I

    move-result v3

    iget v4, p0, Lmiui/widget/NumberPicker;->EB:I

    invoke-static {v4}, Ljava/lang/Math;->abs(I)I

    move-result v4

    if-lt v3, v4, :cond_6

    .line 815
    int-to-float v0, v0

    iget v3, p0, Lmiui/widget/NumberPicker;->Fl:F

    mul-float/2addr v0, v3

    float-to-int v0, v0

    .line 817
    :cond_6
    invoke-static {v0}, Ljava/lang/Math;->abs(I)I

    move-result v3

    iget v4, p0, Lmiui/widget/NumberPicker;->EA:I

    if-le v3, v4, :cond_7

    .line 818
    invoke-direct {p0, v0}, Lmiui/widget/NumberPicker;->fling(I)V

    .line 819
    invoke-direct {p0, v8}, Lmiui/widget/NumberPicker;->aa(I)V

    .line 845
    :goto_3
    iget-object v0, p0, Lmiui/widget/NumberPicker;->bR:Landroid/view/VelocityTracker;

    invoke-virtual {v0}, Landroid/view/VelocityTracker;->recycle()V

    .line 846
    const/4 v0, 0x0

    iput-object v0, p0, Lmiui/widget/NumberPicker;->bR:Landroid/view/VelocityTracker;

    goto :goto_1

    .line 821
    :cond_7
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getY()F

    move-result v0

    float-to-int v0, v0

    .line 822
    int-to-float v3, v0

    iget v4, p0, Lmiui/widget/NumberPicker;->Ex:F

    sub-float/2addr v3, v4

    invoke-static {v3}, Ljava/lang/Math;->abs(F)F

    move-result v3

    float-to-int v3, v3

    .line 823
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getEventTime()J

    move-result-wide v4

    iget-wide v6, p0, Lmiui/widget/NumberPicker;->Ey:J

    sub-long/2addr v4, v6

    .line 824
    iget v6, p0, Lmiui/widget/NumberPicker;->cT:I

    if-gt v3, v6, :cond_b

    invoke-static {}, Landroid/view/ViewConfiguration;->getTapTimeout()I

    move-result v3

    int-to-long v6, v3

    cmp-long v3, v4, v6

    if-gez v3, :cond_b

    .line 825
    iget-boolean v3, p0, Lmiui/widget/NumberPicker;->EJ:Z

    if-eqz v3, :cond_9

    .line 826
    iput-boolean v2, p0, Lmiui/widget/NumberPicker;->EJ:Z

    .line 843
    :cond_8
    :goto_4
    invoke-direct {p0, v2}, Lmiui/widget/NumberPicker;->aa(I)V

    goto :goto_3

    .line 828
    :cond_9
    iget v3, p0, Lmiui/widget/NumberPicker;->Eo:I

    div-int/2addr v0, v3

    add-int/lit8 v0, v0, -0x2

    .line 830
    if-lez v0, :cond_a

    .line 831
    invoke-direct {p0, v1}, Lmiui/widget/NumberPicker;->k(Z)V

    .line 832
    iget-object v0, p0, Lmiui/widget/NumberPicker;->EQ:Lmiui/widget/NumberPicker$e;

    invoke-virtual {v0, v1}, Lmiui/widget/NumberPicker$e;->al(I)V

    goto :goto_4

    .line 834
    :cond_a
    if-gez v0, :cond_8

    .line 835
    invoke-direct {p0, v2}, Lmiui/widget/NumberPicker;->k(Z)V

    .line 836
    iget-object v0, p0, Lmiui/widget/NumberPicker;->EQ:Lmiui/widget/NumberPicker$e;

    invoke-virtual {v0, v8}, Lmiui/widget/NumberPicker$e;->al(I)V

    goto :goto_4

    .line 841
    :cond_b
    invoke-direct {p0}, Lmiui/widget/NumberPicker;->dj()Z

    goto :goto_4

    .line 788
    :pswitch_data_0
    .packed-switch 0x1
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method

.method public scrollBy(II)V
    .locals 5

    .prologue
    const/4 v4, 0x1

    const/4 v3, 0x2

    .line 988
    iget-object v0, p0, Lmiui/widget/NumberPicker;->El:[I

    .line 989
    iget-boolean v1, p0, Lmiui/widget/NumberPicker;->ED:Z

    if-nez v1, :cond_1

    if-lez p2, :cond_1

    aget v1, v0, v3

    iget v2, p0, Lmiui/widget/NumberPicker;->Ed:I

    if-gt v1, v2, :cond_1

    .line 991
    iget v0, p0, Lmiui/widget/NumberPicker;->Ep:I

    iput v0, p0, Lmiui/widget/NumberPicker;->Eq:I

    .line 1016
    :cond_0
    :goto_0
    return-void

    .line 994
    :cond_1
    iget-boolean v1, p0, Lmiui/widget/NumberPicker;->ED:Z

    if-nez v1, :cond_2

    if-gez p2, :cond_2

    aget v1, v0, v3

    iget v2, p0, Lmiui/widget/NumberPicker;->Ee:I

    if-lt v1, v2, :cond_2

    .line 996
    iget v0, p0, Lmiui/widget/NumberPicker;->Ep:I

    iput v0, p0, Lmiui/widget/NumberPicker;->Eq:I

    goto :goto_0

    .line 999
    :cond_2
    iget v1, p0, Lmiui/widget/NumberPicker;->Eq:I

    add-int/2addr v1, p2

    iput v1, p0, Lmiui/widget/NumberPicker;->Eq:I

    .line 1000
    :cond_3
    :goto_1
    iget v1, p0, Lmiui/widget/NumberPicker;->Eq:I

    iget v2, p0, Lmiui/widget/NumberPicker;->Ep:I

    sub-int/2addr v1, v2

    iget v2, p0, Lmiui/widget/NumberPicker;->Eb:I

    if-le v1, v2, :cond_4

    .line 1001
    iget v1, p0, Lmiui/widget/NumberPicker;->Eq:I

    iget v2, p0, Lmiui/widget/NumberPicker;->Eo:I

    sub-int/2addr v1, v2

    iput v1, p0, Lmiui/widget/NumberPicker;->Eq:I

    .line 1002
    invoke-direct {p0, v0}, Lmiui/widget/NumberPicker;->c([I)V

    .line 1003
    aget v1, v0, v3

    invoke-direct {p0, v1, v4}, Lmiui/widget/NumberPicker;->g(IZ)V

    .line 1004
    iget-boolean v1, p0, Lmiui/widget/NumberPicker;->ED:Z

    if-nez v1, :cond_3

    aget v1, v0, v3

    iget v2, p0, Lmiui/widget/NumberPicker;->Ed:I

    if-gt v1, v2, :cond_3

    .line 1005
    iget v1, p0, Lmiui/widget/NumberPicker;->Ep:I

    iput v1, p0, Lmiui/widget/NumberPicker;->Eq:I

    goto :goto_1

    .line 1008
    :cond_4
    :goto_2
    iget v1, p0, Lmiui/widget/NumberPicker;->Eq:I

    iget v2, p0, Lmiui/widget/NumberPicker;->Ep:I

    sub-int/2addr v1, v2

    iget v2, p0, Lmiui/widget/NumberPicker;->Eb:I

    neg-int v2, v2

    if-ge v1, v2, :cond_0

    .line 1009
    iget v1, p0, Lmiui/widget/NumberPicker;->Eq:I

    iget v2, p0, Lmiui/widget/NumberPicker;->Eo:I

    add-int/2addr v1, v2

    iput v1, p0, Lmiui/widget/NumberPicker;->Eq:I

    .line 1010
    invoke-direct {p0, v0}, Lmiui/widget/NumberPicker;->b([I)V

    .line 1011
    aget v1, v0, v3

    invoke-direct {p0, v1, v4}, Lmiui/widget/NumberPicker;->g(IZ)V

    .line 1012
    iget-boolean v1, p0, Lmiui/widget/NumberPicker;->ED:Z

    if-nez v1, :cond_4

    aget v1, v0, v3

    iget v2, p0, Lmiui/widget/NumberPicker;->Ee:I

    if-lt v1, v2, :cond_4

    .line 1013
    iget v1, p0, Lmiui/widget/NumberPicker;->Ep:I

    iput v1, p0, Lmiui/widget/NumberPicker;->Eq:I

    goto :goto_2
.end method

.method public setDisplayedValues([Ljava/lang/String;)V
    .locals 2

    .prologue
    .line 1295
    iget-object v0, p0, Lmiui/widget/NumberPicker;->Ec:[Ljava/lang/String;

    if-ne v0, p1, :cond_0

    .line 1309
    :goto_0
    return-void

    .line 1298
    :cond_0
    iput-object p1, p0, Lmiui/widget/NumberPicker;->Ec:[Ljava/lang/String;

    .line 1299
    iget-object v0, p0, Lmiui/widget/NumberPicker;->Ec:[Ljava/lang/String;

    if-eqz v0, :cond_1

    .line 1301
    iget-object v0, p0, Lmiui/widget/NumberPicker;->DT:Landroid/widget/EditText;

    const v1, 0x80001

    invoke-virtual {v0, v1}, Landroid/widget/EditText;->setRawInputType(I)V

    .line 1306
    :goto_1
    invoke-direct {p0}, Lmiui/widget/NumberPicker;->de()Z

    .line 1307
    invoke-direct {p0}, Lmiui/widget/NumberPicker;->db()V

    .line 1308
    invoke-direct {p0}, Lmiui/widget/NumberPicker;->da()V

    goto :goto_0

    .line 1304
    :cond_1
    iget-object v0, p0, Lmiui/widget/NumberPicker;->DT:Landroid/widget/EditText;

    const/4 v1, 0x2

    invoke-virtual {v0, v1}, Landroid/widget/EditText;->setRawInputType(I)V

    goto :goto_1
.end method

.method public setFormatter(Lmiui/widget/NumberPicker$Formatter;)V
    .locals 1

    .prologue
    .line 1059
    iget-object v0, p0, Lmiui/widget/NumberPicker;->Ei:Lmiui/widget/NumberPicker$Formatter;

    if-ne p1, v0, :cond_0

    .line 1065
    :goto_0
    return-void

    .line 1062
    :cond_0
    iput-object p1, p0, Lmiui/widget/NumberPicker;->Ei:Lmiui/widget/NumberPicker$Formatter;

    .line 1063
    invoke-direct {p0}, Lmiui/widget/NumberPicker;->db()V

    .line 1064
    invoke-direct {p0}, Lmiui/widget/NumberPicker;->de()Z

    goto :goto_0
.end method

.method public setLabel(Ljava/lang/String;)V
    .locals 1

    .prologue
    .line 1023
    iget-object v0, p0, Lmiui/widget/NumberPicker;->Fi:Ljava/lang/CharSequence;

    if-nez v0, :cond_0

    if-nez p1, :cond_1

    :cond_0
    iget-object v0, p0, Lmiui/widget/NumberPicker;->Fi:Ljava/lang/CharSequence;

    if-eqz v0, :cond_2

    iget-object v0, p0, Lmiui/widget/NumberPicker;->Fi:Ljava/lang/CharSequence;

    invoke-virtual {v0, p1}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_2

    .line 1024
    :cond_1
    iput-object p1, p0, Lmiui/widget/NumberPicker;->Fi:Ljava/lang/CharSequence;

    .line 1025
    invoke-virtual {p0}, Lmiui/widget/NumberPicker;->invalidate()V

    .line 1027
    :cond_2
    return-void
.end method

.method public setMaxFlingSpeedFactor(F)V
    .locals 1

    .prologue
    .line 1316
    const/4 v0, 0x0

    cmpl-float v0, p1, v0

    if-ltz v0, :cond_0

    .line 1317
    iput p1, p0, Lmiui/widget/NumberPicker;->Fl:F

    .line 1319
    :cond_0
    return-void
.end method

.method public setMaxValue(I)V
    .locals 2

    .prologue
    .line 1258
    iget v0, p0, Lmiui/widget/NumberPicker;->Ee:I

    if-ne v0, p1, :cond_0

    .line 1274
    :goto_0
    return-void

    .line 1261
    :cond_0
    if-gez p1, :cond_1

    .line 1262
    new-instance v0, Ljava/lang/IllegalArgumentException;

    const-string v1, "maxValue must be >= 0"

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 1264
    :cond_1
    iput p1, p0, Lmiui/widget/NumberPicker;->Ee:I

    .line 1265
    iget v0, p0, Lmiui/widget/NumberPicker;->Ee:I

    iget v1, p0, Lmiui/widget/NumberPicker;->Ef:I

    if-ge v0, v1, :cond_2

    .line 1266
    iget v0, p0, Lmiui/widget/NumberPicker;->Ee:I

    iput v0, p0, Lmiui/widget/NumberPicker;->Ef:I

    .line 1268
    :cond_2
    iget v0, p0, Lmiui/widget/NumberPicker;->Ee:I

    iget v1, p0, Lmiui/widget/NumberPicker;->Ed:I

    sub-int/2addr v0, v1

    iget-object v1, p0, Lmiui/widget/NumberPicker;->El:[I

    array-length v1, v1

    if-le v0, v1, :cond_3

    const/4 v0, 0x1

    .line 1269
    :goto_1
    invoke-virtual {p0, v0}, Lmiui/widget/NumberPicker;->setWrapSelectorWheel(Z)V

    .line 1270
    invoke-direct {p0}, Lmiui/widget/NumberPicker;->db()V

    .line 1271
    invoke-direct {p0}, Lmiui/widget/NumberPicker;->de()Z

    .line 1272
    invoke-direct {p0}, Lmiui/widget/NumberPicker;->da()V

    .line 1273
    invoke-virtual {p0}, Lmiui/widget/NumberPicker;->invalidate()V

    goto :goto_0

    .line 1268
    :cond_3
    const/4 v0, 0x0

    goto :goto_1
.end method

.method public setMinValue(I)V
    .locals 2

    .prologue
    .line 1220
    iget v0, p0, Lmiui/widget/NumberPicker;->Ed:I

    if-ne v0, p1, :cond_0

    .line 1236
    :goto_0
    return-void

    .line 1223
    :cond_0
    if-gez p1, :cond_1

    .line 1224
    new-instance v0, Ljava/lang/IllegalArgumentException;

    const-string v1, "minValue must be >= 0"

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 1226
    :cond_1
    iput p1, p0, Lmiui/widget/NumberPicker;->Ed:I

    .line 1227
    iget v0, p0, Lmiui/widget/NumberPicker;->Ed:I

    iget v1, p0, Lmiui/widget/NumberPicker;->Ef:I

    if-le v0, v1, :cond_2

    .line 1228
    iget v0, p0, Lmiui/widget/NumberPicker;->Ed:I

    iput v0, p0, Lmiui/widget/NumberPicker;->Ef:I

    .line 1230
    :cond_2
    iget v0, p0, Lmiui/widget/NumberPicker;->Ee:I

    iget v1, p0, Lmiui/widget/NumberPicker;->Ed:I

    sub-int/2addr v0, v1

    iget-object v1, p0, Lmiui/widget/NumberPicker;->El:[I

    array-length v1, v1

    if-le v0, v1, :cond_3

    const/4 v0, 0x1

    .line 1231
    :goto_1
    invoke-virtual {p0, v0}, Lmiui/widget/NumberPicker;->setWrapSelectorWheel(Z)V

    .line 1232
    invoke-direct {p0}, Lmiui/widget/NumberPicker;->db()V

    .line 1233
    invoke-direct {p0}, Lmiui/widget/NumberPicker;->de()Z

    .line 1234
    invoke-direct {p0}, Lmiui/widget/NumberPicker;->da()V

    .line 1235
    invoke-virtual {p0}, Lmiui/widget/NumberPicker;->invalidate()V

    goto :goto_0

    .line 1230
    :cond_3
    const/4 v0, 0x0

    goto :goto_1
.end method

.method public setOnLongPressUpdateInterval(J)V
    .locals 0

    .prologue
    .line 1188
    iput-wide p1, p0, Lmiui/widget/NumberPicker;->Ej:J

    .line 1189
    return-void
.end method

.method public setOnScrollListener(Lmiui/widget/NumberPicker$OnScrollListener;)V
    .locals 0

    .prologue
    .line 1044
    iput-object p1, p0, Lmiui/widget/NumberPicker;->Eh:Lmiui/widget/NumberPicker$OnScrollListener;

    .line 1045
    return-void
.end method

.method public setOnValueChangedListener(Lmiui/widget/NumberPicker$OnValueChangeListener;)V
    .locals 0

    .prologue
    .line 1035
    iput-object p1, p0, Lmiui/widget/NumberPicker;->Eg:Lmiui/widget/NumberPicker$OnValueChangeListener;

    .line 1036
    return-void
.end method

.method public setValue(I)V
    .locals 1

    .prologue
    .line 1096
    const/4 v0, 0x0

    invoke-direct {p0, p1, v0}, Lmiui/widget/NumberPicker;->g(IZ)V

    .line 1097
    return-void
.end method

.method public setWrapSelectorWheel(Z)V
    .locals 2

    .prologue
    .line 1170
    iget v0, p0, Lmiui/widget/NumberPicker;->Ee:I

    iget v1, p0, Lmiui/widget/NumberPicker;->Ed:I

    sub-int/2addr v0, v1

    iget-object v1, p0, Lmiui/widget/NumberPicker;->El:[I

    array-length v1, v1

    if-lt v0, v1, :cond_2

    const/4 v0, 0x1

    .line 1171
    :goto_0
    if-eqz p1, :cond_0

    if-eqz v0, :cond_1

    :cond_0
    iget-boolean v0, p0, Lmiui/widget/NumberPicker;->ED:Z

    if-eq p1, v0, :cond_1

    .line 1172
    iput-boolean p1, p0, Lmiui/widget/NumberPicker;->ED:Z

    .line 1174
    :cond_1
    invoke-direct {p0}, Lmiui/widget/NumberPicker;->dk()V

    .line 1175
    return-void

    .line 1170
    :cond_2
    const/4 v0, 0x0

    goto :goto_0
.end method
