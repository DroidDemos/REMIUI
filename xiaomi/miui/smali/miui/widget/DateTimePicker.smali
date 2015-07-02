.class public Lmiui/widget/DateTimePicker;
.super Landroid/widget/LinearLayout;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lmiui/widget/DateTimePicker$1;,
        Lmiui/widget/DateTimePicker$a;,
        Lmiui/widget/DateTimePicker$LunarFormatter;,
        Lmiui/widget/DateTimePicker$DayFormatter;,
        Lmiui/widget/DateTimePicker$b;,
        Lmiui/widget/DateTimePicker$OnDateTimeChangedListener;
    }
.end annotation


# static fields
.field private static final HB:I = 0x1

.field private static final HC:I = 0x7

.field private static final HD:I = 0x6

.field private static final HE:I = 0x2

.field private static final HL:Lmiui/widget/DateTimePicker$DayFormatter;

.field private static final HP:Ljava/lang/ThreadLocal;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/lang/ThreadLocal",
            "<",
            "Lmiui/date/Calendar;",
            ">;"
        }
    .end annotation
.end field

.field private static HW:Ljava/lang/ThreadLocal;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/lang/ThreadLocal",
            "<",
            "Lmiui/date/Calendar;",
            ">;"
        }
    .end annotation
.end field


# instance fields
.field private HF:Lmiui/widget/NumberPicker;

.field private HG:Lmiui/widget/NumberPicker;

.field private HH:Lmiui/widget/NumberPicker;

.field private HI:[Ljava/lang/String;

.field private HJ:Lmiui/widget/DateTimePicker$DayFormatter;

.field private HM:Lmiui/widget/DateTimePicker$DayFormatter;

.field private HO:Lmiui/widget/DateTimePicker$OnDateTimeChangedListener;

.field private HQ:I

.field private HS:I

.field HV:[Ljava/lang/String;

.field private bp:Lmiui/date/Calendar;

.field private rX:Lmiui/date/Calendar;

.field private rY:Lmiui/date/Calendar;

.field private sb:Z


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .prologue
    .line 42
    new-instance v0, Lmiui/widget/DateTimePicker$DayFormatter;

    invoke-direct {v0}, Lmiui/widget/DateTimePicker$DayFormatter;-><init>()V

    sput-object v0, Lmiui/widget/DateTimePicker;->HL:Lmiui/widget/DateTimePicker$DayFormatter;

    .line 47
    new-instance v0, Ljava/lang/ThreadLocal;

    invoke-direct {v0}, Ljava/lang/ThreadLocal;-><init>()V

    sput-object v0, Lmiui/widget/DateTimePicker;->HP:Ljava/lang/ThreadLocal;

    .line 59
    new-instance v0, Ljava/lang/ThreadLocal;

    invoke-direct {v0}, Ljava/lang/ThreadLocal;-><init>()V

    sput-object v0, Lmiui/widget/DateTimePicker;->HW:Ljava/lang/ThreadLocal;

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;)V
    .locals 1

    .prologue
    .line 64
    const/4 v0, 0x0

    invoke-direct {p0, p1, v0}, Lmiui/widget/DateTimePicker;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    .line 65
    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 1

    .prologue
    .line 68
    sget v0, Lcom/miui/internal/R$attr;->dateTimePickerStyle:I

    invoke-direct {p0, p1, p2, v0}, Lmiui/widget/DateTimePicker;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    .line 69
    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V
    .locals 5

    .prologue
    const/4 v3, 0x1

    const/4 v2, 0x0

    const/4 v4, 0x0

    .line 72
    invoke-direct {p0, p1, p2, p3}, Landroid/widget/LinearLayout;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    .line 50
    iput v3, p0, Lmiui/widget/DateTimePicker;->HQ:I

    .line 54
    iput-object v2, p0, Lmiui/widget/DateTimePicker;->rX:Lmiui/date/Calendar;

    .line 55
    iput-object v2, p0, Lmiui/widget/DateTimePicker;->rY:Lmiui/date/Calendar;

    .line 57
    iput-object v2, p0, Lmiui/widget/DateTimePicker;->HV:[Ljava/lang/String;

    .line 61
    iput-boolean v4, p0, Lmiui/widget/DateTimePicker;->sb:Z

    .line 74
    const-string v0, "layout_inflater"

    invoke-virtual {p1, v0}, Landroid/content/Context;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/view/LayoutInflater;

    .line 76
    sget v1, Lcom/miui/internal/R$layout;->datetime_picker:I

    invoke-virtual {v0, v1, p0, v3}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;Z)Landroid/view/View;

    .line 78
    new-instance v1, Lmiui/widget/DateTimePicker$a;

    invoke-direct {v1, p0, v2}, Lmiui/widget/DateTimePicker$a;-><init>(Lmiui/widget/DateTimePicker;Lmiui/widget/DateTimePicker$1;)V

    .line 80
    new-instance v0, Lmiui/date/Calendar;

    invoke-direct {v0}, Lmiui/date/Calendar;-><init>()V

    iput-object v0, p0, Lmiui/widget/DateTimePicker;->bp:Lmiui/date/Calendar;

    .line 81
    iget-object v0, p0, Lmiui/widget/DateTimePicker;->bp:Lmiui/date/Calendar;

    invoke-direct {p0, v0, v3}, Lmiui/widget/DateTimePicker;->a(Lmiui/date/Calendar;Z)V

    .line 83
    sget-object v0, Lmiui/widget/DateTimePicker;->HP:Ljava/lang/ThreadLocal;

    invoke-virtual {v0}, Ljava/lang/ThreadLocal;->get()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/date/Calendar;

    .line 84
    if-nez v0, :cond_0

    .line 85
    new-instance v0, Lmiui/date/Calendar;

    invoke-direct {v0}, Lmiui/date/Calendar;-><init>()V

    .line 86
    sget-object v2, Lmiui/widget/DateTimePicker;->HP:Ljava/lang/ThreadLocal;

    invoke-virtual {v2, v0}, Ljava/lang/ThreadLocal;->set(Ljava/lang/Object;)V

    .line 88
    :cond_0
    const-wide/16 v2, 0x0

    invoke-virtual {v0, v2, v3}, Lmiui/date/Calendar;->setTimeInMillis(J)Lmiui/date/Calendar;

    .line 90
    sget v0, Lcom/miui/internal/R$id;->day:I

    invoke-virtual {p0, v0}, Lmiui/widget/DateTimePicker;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Lmiui/widget/NumberPicker;

    iput-object v0, p0, Lmiui/widget/DateTimePicker;->HF:Lmiui/widget/NumberPicker;

    .line 91
    sget v0, Lcom/miui/internal/R$id;->hour:I

    invoke-virtual {p0, v0}, Lmiui/widget/DateTimePicker;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Lmiui/widget/NumberPicker;

    iput-object v0, p0, Lmiui/widget/DateTimePicker;->HG:Lmiui/widget/NumberPicker;

    .line 92
    sget v0, Lcom/miui/internal/R$id;->minute:I

    invoke-virtual {p0, v0}, Lmiui/widget/DateTimePicker;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Lmiui/widget/NumberPicker;

    iput-object v0, p0, Lmiui/widget/DateTimePicker;->HH:Lmiui/widget/NumberPicker;

    .line 93
    iget-object v0, p0, Lmiui/widget/DateTimePicker;->HF:Lmiui/widget/NumberPicker;

    invoke-virtual {v0, v1}, Lmiui/widget/NumberPicker;->setOnValueChangedListener(Lmiui/widget/NumberPicker$OnValueChangeListener;)V

    .line 94
    iget-object v0, p0, Lmiui/widget/DateTimePicker;->HF:Lmiui/widget/NumberPicker;

    const/high16 v2, 0x40400000

    invoke-virtual {v0, v2}, Lmiui/widget/NumberPicker;->setMaxFlingSpeedFactor(F)V

    .line 95
    iget-object v0, p0, Lmiui/widget/DateTimePicker;->HG:Lmiui/widget/NumberPicker;

    invoke-virtual {v0, v1}, Lmiui/widget/NumberPicker;->setOnValueChangedListener(Lmiui/widget/NumberPicker$OnValueChangeListener;)V

    .line 96
    iget-object v0, p0, Lmiui/widget/DateTimePicker;->HH:Lmiui/widget/NumberPicker;

    invoke-virtual {v0, v1}, Lmiui/widget/NumberPicker;->setOnValueChangedListener(Lmiui/widget/NumberPicker$OnValueChangeListener;)V

    .line 97
    iget-object v0, p0, Lmiui/widget/DateTimePicker;->HH:Lmiui/widget/NumberPicker;

    invoke-virtual {v0, v4}, Lmiui/widget/NumberPicker;->setMinValue(I)V

    .line 98
    iget-object v0, p0, Lmiui/widget/DateTimePicker;->HH:Lmiui/widget/NumberPicker;

    const/16 v1, 0x3b

    invoke-virtual {v0, v1}, Lmiui/widget/NumberPicker;->setMaxValue(I)V

    .line 99
    iget-object v0, p0, Lmiui/widget/DateTimePicker;->HG:Lmiui/widget/NumberPicker;

    sget-object v1, Lmiui/widget/NumberPicker;->DS:Lmiui/widget/NumberPicker$Formatter;

    invoke-virtual {v0, v1}, Lmiui/widget/NumberPicker;->setFormatter(Lmiui/widget/NumberPicker$Formatter;)V

    .line 100
    sget-object v0, Lcom/miui/internal/R$styleable;->DateTimePicker:[I

    invoke-virtual {p1, p2, v0, p3, v4}, Landroid/content/Context;->obtainStyledAttributes(Landroid/util/AttributeSet;[III)Landroid/content/res/TypedArray;

    move-result-object v0

    .line 102
    invoke-virtual {v0, v4, v4}, Landroid/content/res/TypedArray;->getBoolean(IZ)Z

    move-result v1

    iput-boolean v1, p0, Lmiui/widget/DateTimePicker;->sb:Z

    .line 103
    invoke-virtual {v0}, Landroid/content/res/TypedArray;->recycle()V

    .line 105
    invoke-direct {p0}, Lmiui/widget/DateTimePicker;->dJ()V

    .line 106
    invoke-direct {p0}, Lmiui/widget/DateTimePicker;->dM()V

    .line 107
    invoke-direct {p0}, Lmiui/widget/DateTimePicker;->dL()V

    .line 108
    invoke-direct {p0}, Lmiui/widget/DateTimePicker;->dK()V

    .line 109
    return-void
.end method

.method private a(Lmiui/date/Calendar;Lmiui/date/Calendar;)I
    .locals 9

    .prologue
    const/16 v8, 0x15

    const/16 v5, 0x14

    const/16 v4, 0x12

    const-wide/16 v6, 0x3c

    const/4 v3, 0x0

    .line 201
    invoke-virtual {p1}, Lmiui/date/Calendar;->clone()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/date/Calendar;

    .line 202
    invoke-virtual {p2}, Lmiui/date/Calendar;->clone()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lmiui/date/Calendar;

    .line 203
    invoke-virtual {v0, v4, v3}, Lmiui/date/Calendar;->set(II)Lmiui/date/Calendar;

    .line 204
    invoke-virtual {v0, v5, v3}, Lmiui/date/Calendar;->set(II)Lmiui/date/Calendar;

    .line 205
    invoke-virtual {v0, v8, v3}, Lmiui/date/Calendar;->set(II)Lmiui/date/Calendar;

    .line 206
    const/16 v2, 0x16

    invoke-virtual {v0, v2, v3}, Lmiui/date/Calendar;->set(II)Lmiui/date/Calendar;

    .line 207
    invoke-virtual {v1, v4, v3}, Lmiui/date/Calendar;->set(II)Lmiui/date/Calendar;

    .line 208
    invoke-virtual {v1, v5, v3}, Lmiui/date/Calendar;->set(II)Lmiui/date/Calendar;

    .line 209
    invoke-virtual {v1, v8, v3}, Lmiui/date/Calendar;->set(II)Lmiui/date/Calendar;

    .line 210
    const/16 v2, 0x16

    invoke-virtual {v1, v2, v3}, Lmiui/date/Calendar;->set(II)Lmiui/date/Calendar;

    .line 211
    invoke-virtual {v0}, Lmiui/date/Calendar;->getTimeInMillis()J

    move-result-wide v2

    const-wide/16 v4, 0x3e8

    div-long/2addr v2, v4

    div-long/2addr v2, v6

    div-long/2addr v2, v6

    const-wide/16 v4, 0x18

    div-long/2addr v2, v4

    invoke-virtual {v1}, Lmiui/date/Calendar;->getTimeInMillis()J

    move-result-wide v0

    const-wide/16 v4, 0x3e8

    div-long/2addr v0, v4

    div-long/2addr v0, v6

    div-long/2addr v0, v6

    const-wide/16 v4, 0x18

    div-long/2addr v0, v4

    sub-long v0, v2, v0

    long-to-int v0, v0

    return v0
.end method

.method static synthetic a(Lmiui/widget/DateTimePicker;I)I
    .locals 0

    .prologue
    .line 24
    iput p1, p0, Lmiui/widget/DateTimePicker;->HS:I

    return p1
.end method

.method private a(Lmiui/date/Calendar;Z)V
    .locals 3

    .prologue
    const/4 v1, 0x0

    const/16 v2, 0x14

    .line 112
    const/16 v0, 0x16

    invoke-virtual {p1, v0, v1}, Lmiui/date/Calendar;->set(II)Lmiui/date/Calendar;

    .line 113
    const/16 v0, 0x15

    invoke-virtual {p1, v0, v1}, Lmiui/date/Calendar;->set(II)Lmiui/date/Calendar;

    .line 114
    invoke-virtual {p1, v2}, Lmiui/date/Calendar;->get(I)I

    move-result v0

    iget v1, p0, Lmiui/widget/DateTimePicker;->HQ:I

    rem-int/2addr v0, v1

    .line 115
    if-eqz v0, :cond_0

    .line 116
    if-eqz p2, :cond_1

    .line 117
    iget v1, p0, Lmiui/widget/DateTimePicker;->HQ:I

    sub-int v0, v1, v0

    invoke-virtual {p1, v2, v0}, Lmiui/date/Calendar;->add(II)Lmiui/date/Calendar;

    .line 122
    :cond_0
    :goto_0
    return-void

    .line 119
    :cond_1
    neg-int v0, v0

    invoke-virtual {p1, v2, v0}, Lmiui/date/Calendar;->add(II)Lmiui/date/Calendar;

    goto :goto_0
.end method

.method static synthetic b(Lmiui/widget/DateTimePicker;)Lmiui/widget/NumberPicker;
    .locals 1

    .prologue
    .line 24
    iget-object v0, p0, Lmiui/widget/DateTimePicker;->HF:Lmiui/widget/NumberPicker;

    return-object v0
.end method

.method static synthetic c(Lmiui/widget/DateTimePicker;)I
    .locals 1

    .prologue
    .line 24
    iget v0, p0, Lmiui/widget/DateTimePicker;->HS:I

    return v0
.end method

.method private c(Lmiui/widget/NumberPicker;II)V
    .locals 2

    .prologue
    .line 280
    invoke-virtual {p1}, Lmiui/widget/NumberPicker;->getDisplayedValues()[Ljava/lang/String;

    move-result-object v0

    .line 281
    if-eqz v0, :cond_0

    array-length v0, v0

    sub-int v1, p3, p2

    add-int/lit8 v1, v1, 0x1

    if-ge v0, v1, :cond_0

    .line 282
    const/4 v0, 0x0

    invoke-virtual {p1, v0}, Lmiui/widget/NumberPicker;->setDisplayedValues([Ljava/lang/String;)V

    .line 284
    :cond_0
    return-void
.end method

.method static synthetic d(Lmiui/widget/DateTimePicker;)Lmiui/date/Calendar;
    .locals 1

    .prologue
    .line 24
    iget-object v0, p0, Lmiui/widget/DateTimePicker;->bp:Lmiui/date/Calendar;

    return-object v0
.end method

.method private dJ()V
    .locals 4

    .prologue
    .line 125
    iget-object v0, p0, Lmiui/widget/DateTimePicker;->rX:Lmiui/date/Calendar;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lmiui/widget/DateTimePicker;->rX:Lmiui/date/Calendar;

    invoke-virtual {v0}, Lmiui/date/Calendar;->getTimeInMillis()J

    move-result-wide v0

    iget-object v2, p0, Lmiui/widget/DateTimePicker;->bp:Lmiui/date/Calendar;

    invoke-virtual {v2}, Lmiui/date/Calendar;->getTimeInMillis()J

    move-result-wide v2

    cmp-long v0, v0, v2

    if-lez v0, :cond_0

    .line 126
    iget-object v0, p0, Lmiui/widget/DateTimePicker;->bp:Lmiui/date/Calendar;

    iget-object v1, p0, Lmiui/widget/DateTimePicker;->rX:Lmiui/date/Calendar;

    invoke-virtual {v1}, Lmiui/date/Calendar;->getTimeInMillis()J

    move-result-wide v1

    invoke-virtual {v0, v1, v2}, Lmiui/date/Calendar;->setTimeInMillis(J)Lmiui/date/Calendar;

    .line 128
    :cond_0
    iget-object v0, p0, Lmiui/widget/DateTimePicker;->rY:Lmiui/date/Calendar;

    if-eqz v0, :cond_1

    iget-object v0, p0, Lmiui/widget/DateTimePicker;->rY:Lmiui/date/Calendar;

    invoke-virtual {v0}, Lmiui/date/Calendar;->getTimeInMillis()J

    move-result-wide v0

    iget-object v2, p0, Lmiui/widget/DateTimePicker;->bp:Lmiui/date/Calendar;

    invoke-virtual {v2}, Lmiui/date/Calendar;->getTimeInMillis()J

    move-result-wide v2

    cmp-long v0, v0, v2

    if-gez v0, :cond_1

    .line 129
    iget-object v0, p0, Lmiui/widget/DateTimePicker;->bp:Lmiui/date/Calendar;

    iget-object v1, p0, Lmiui/widget/DateTimePicker;->rY:Lmiui/date/Calendar;

    invoke-virtual {v1}, Lmiui/date/Calendar;->getTimeInMillis()J

    move-result-wide v1

    invoke-virtual {v0, v1, v2}, Lmiui/date/Calendar;->setTimeInMillis(J)Lmiui/date/Calendar;

    .line 131
    :cond_1
    return-void
.end method

.method private dK()V
    .locals 8

    .prologue
    const/16 v7, 0x3c

    const/16 v6, 0x14

    const/4 v1, 0x1

    const/16 v5, 0x12

    const/4 v2, 0x0

    .line 134
    .line 135
    iget-object v0, p0, Lmiui/widget/DateTimePicker;->rY:Lmiui/date/Calendar;

    if-eqz v0, :cond_5

    .line 136
    iget-object v0, p0, Lmiui/widget/DateTimePicker;->bp:Lmiui/date/Calendar;

    iget-object v3, p0, Lmiui/widget/DateTimePicker;->rY:Lmiui/date/Calendar;

    invoke-direct {p0, v0, v3}, Lmiui/widget/DateTimePicker;->a(Lmiui/date/Calendar;Lmiui/date/Calendar;)I

    move-result v0

    if-nez v0, :cond_5

    iget-object v0, p0, Lmiui/widget/DateTimePicker;->bp:Lmiui/date/Calendar;

    invoke-virtual {v0, v5}, Lmiui/date/Calendar;->get(I)I

    move-result v0

    iget-object v3, p0, Lmiui/widget/DateTimePicker;->rY:Lmiui/date/Calendar;

    invoke-virtual {v3, v5}, Lmiui/date/Calendar;->get(I)I

    move-result v3

    if-ne v0, v3, :cond_5

    .line 138
    iget-object v0, p0, Lmiui/widget/DateTimePicker;->rY:Lmiui/date/Calendar;

    invoke-virtual {v0, v6}, Lmiui/date/Calendar;->get(I)I

    move-result v0

    .line 139
    iget-object v3, p0, Lmiui/widget/DateTimePicker;->HH:Lmiui/widget/NumberPicker;

    invoke-virtual {v3, v2}, Lmiui/widget/NumberPicker;->setMinValue(I)V

    .line 140
    iget-object v3, p0, Lmiui/widget/DateTimePicker;->HH:Lmiui/widget/NumberPicker;

    iget v4, p0, Lmiui/widget/DateTimePicker;->HQ:I

    div-int/2addr v0, v4

    invoke-virtual {v3, v0}, Lmiui/widget/NumberPicker;->setMaxValue(I)V

    .line 141
    iget-object v0, p0, Lmiui/widget/DateTimePicker;->HH:Lmiui/widget/NumberPicker;

    invoke-virtual {v0, v2}, Lmiui/widget/NumberPicker;->setWrapSelectorWheel(Z)V

    move v0, v1

    .line 145
    :goto_0
    iget-object v3, p0, Lmiui/widget/DateTimePicker;->rX:Lmiui/date/Calendar;

    if-eqz v3, :cond_0

    .line 146
    iget-object v3, p0, Lmiui/widget/DateTimePicker;->bp:Lmiui/date/Calendar;

    iget-object v4, p0, Lmiui/widget/DateTimePicker;->rX:Lmiui/date/Calendar;

    invoke-direct {p0, v3, v4}, Lmiui/widget/DateTimePicker;->a(Lmiui/date/Calendar;Lmiui/date/Calendar;)I

    move-result v3

    if-nez v3, :cond_0

    iget-object v3, p0, Lmiui/widget/DateTimePicker;->bp:Lmiui/date/Calendar;

    invoke-virtual {v3, v5}, Lmiui/date/Calendar;->get(I)I

    move-result v3

    iget-object v4, p0, Lmiui/widget/DateTimePicker;->rX:Lmiui/date/Calendar;

    invoke-virtual {v4, v5}, Lmiui/date/Calendar;->get(I)I

    move-result v4

    if-ne v3, v4, :cond_0

    .line 148
    iget-object v0, p0, Lmiui/widget/DateTimePicker;->rX:Lmiui/date/Calendar;

    invoke-virtual {v0, v6}, Lmiui/date/Calendar;->get(I)I

    move-result v0

    .line 149
    iget-object v3, p0, Lmiui/widget/DateTimePicker;->HH:Lmiui/widget/NumberPicker;

    iget v4, p0, Lmiui/widget/DateTimePicker;->HQ:I

    div-int/2addr v0, v4

    invoke-virtual {v3, v0}, Lmiui/widget/NumberPicker;->setMinValue(I)V

    .line 150
    iget-object v0, p0, Lmiui/widget/DateTimePicker;->HH:Lmiui/widget/NumberPicker;

    invoke-virtual {v0, v2}, Lmiui/widget/NumberPicker;->setWrapSelectorWheel(Z)V

    move v0, v1

    .line 154
    :cond_0
    if-nez v0, :cond_1

    .line 155
    iget-object v0, p0, Lmiui/widget/DateTimePicker;->HH:Lmiui/widget/NumberPicker;

    iget v3, p0, Lmiui/widget/DateTimePicker;->HQ:I

    div-int v3, v7, v3

    add-int/lit8 v3, v3, -0x1

    invoke-direct {p0, v0, v2, v3}, Lmiui/widget/DateTimePicker;->c(Lmiui/widget/NumberPicker;II)V

    .line 156
    iget-object v0, p0, Lmiui/widget/DateTimePicker;->HH:Lmiui/widget/NumberPicker;

    invoke-virtual {v0, v2}, Lmiui/widget/NumberPicker;->setMinValue(I)V

    .line 157
    iget-object v0, p0, Lmiui/widget/DateTimePicker;->HH:Lmiui/widget/NumberPicker;

    iget v3, p0, Lmiui/widget/DateTimePicker;->HQ:I

    div-int v3, v7, v3

    add-int/lit8 v3, v3, -0x1

    invoke-virtual {v0, v3}, Lmiui/widget/NumberPicker;->setMaxValue(I)V

    .line 158
    iget-object v0, p0, Lmiui/widget/DateTimePicker;->HH:Lmiui/widget/NumberPicker;

    invoke-virtual {v0, v1}, Lmiui/widget/NumberPicker;->setWrapSelectorWheel(Z)V

    .line 160
    :cond_1
    iget-object v0, p0, Lmiui/widget/DateTimePicker;->HH:Lmiui/widget/NumberPicker;

    invoke-virtual {v0}, Lmiui/widget/NumberPicker;->getMaxValue()I

    move-result v0

    iget-object v1, p0, Lmiui/widget/DateTimePicker;->HH:Lmiui/widget/NumberPicker;

    invoke-virtual {v1}, Lmiui/widget/NumberPicker;->getMinValue()I

    move-result v1

    sub-int/2addr v0, v1

    add-int/lit8 v0, v0, 0x1

    .line 161
    iget-object v1, p0, Lmiui/widget/DateTimePicker;->HI:[Ljava/lang/String;

    if-eqz v1, :cond_2

    iget-object v1, p0, Lmiui/widget/DateTimePicker;->HI:[Ljava/lang/String;

    array-length v1, v1

    if-eq v1, v0, :cond_4

    .line 162
    :cond_2
    new-array v1, v0, [Ljava/lang/String;

    iput-object v1, p0, Lmiui/widget/DateTimePicker;->HI:[Ljava/lang/String;

    .line 163
    :goto_1
    if-ge v2, v0, :cond_3

    .line 164
    iget-object v1, p0, Lmiui/widget/DateTimePicker;->HI:[Ljava/lang/String;

    sget-object v3, Lmiui/widget/NumberPicker;->DS:Lmiui/widget/NumberPicker$Formatter;

    iget-object v4, p0, Lmiui/widget/DateTimePicker;->HH:Lmiui/widget/NumberPicker;

    invoke-virtual {v4}, Lmiui/widget/NumberPicker;->getMinValue()I

    move-result v4

    add-int/2addr v4, v2

    iget v5, p0, Lmiui/widget/DateTimePicker;->HQ:I

    mul-int/2addr v4, v5

    invoke-interface {v3, v4}, Lmiui/widget/NumberPicker$Formatter;->format(I)Ljava/lang/String;

    move-result-object v3

    aput-object v3, v1, v2

    .line 163
    add-int/lit8 v2, v2, 0x1

    goto :goto_1

    .line 167
    :cond_3
    iget-object v0, p0, Lmiui/widget/DateTimePicker;->HH:Lmiui/widget/NumberPicker;

    iget-object v1, p0, Lmiui/widget/DateTimePicker;->HI:[Ljava/lang/String;

    invoke-virtual {v0, v1}, Lmiui/widget/NumberPicker;->setDisplayedValues([Ljava/lang/String;)V

    .line 169
    :cond_4
    iget-object v0, p0, Lmiui/widget/DateTimePicker;->bp:Lmiui/date/Calendar;

    invoke-virtual {v0, v6}, Lmiui/date/Calendar;->get(I)I

    move-result v0

    iget v1, p0, Lmiui/widget/DateTimePicker;->HQ:I

    div-int/2addr v0, v1

    .line 170
    iget-object v1, p0, Lmiui/widget/DateTimePicker;->HH:Lmiui/widget/NumberPicker;

    invoke-virtual {v1, v0}, Lmiui/widget/NumberPicker;->setValue(I)V

    .line 171
    return-void

    :cond_5
    move v0, v2

    goto/16 :goto_0
.end method

.method private dL()V
    .locals 6

    .prologue
    const/16 v5, 0x12

    const/4 v1, 0x1

    const/4 v2, 0x0

    .line 174
    .line 175
    iget-object v0, p0, Lmiui/widget/DateTimePicker;->rY:Lmiui/date/Calendar;

    if-eqz v0, :cond_2

    .line 176
    iget-object v0, p0, Lmiui/widget/DateTimePicker;->bp:Lmiui/date/Calendar;

    iget-object v3, p0, Lmiui/widget/DateTimePicker;->rY:Lmiui/date/Calendar;

    invoke-direct {p0, v0, v3}, Lmiui/widget/DateTimePicker;->a(Lmiui/date/Calendar;Lmiui/date/Calendar;)I

    move-result v0

    if-nez v0, :cond_2

    .line 177
    iget-object v0, p0, Lmiui/widget/DateTimePicker;->rY:Lmiui/date/Calendar;

    invoke-virtual {v0, v5}, Lmiui/date/Calendar;->get(I)I

    move-result v0

    .line 178
    iget-object v3, p0, Lmiui/widget/DateTimePicker;->HG:Lmiui/widget/NumberPicker;

    invoke-virtual {v3, v0}, Lmiui/widget/NumberPicker;->setMaxValue(I)V

    .line 179
    iget-object v0, p0, Lmiui/widget/DateTimePicker;->HG:Lmiui/widget/NumberPicker;

    invoke-virtual {v0, v2}, Lmiui/widget/NumberPicker;->setWrapSelectorWheel(Z)V

    move v0, v1

    .line 183
    :goto_0
    iget-object v3, p0, Lmiui/widget/DateTimePicker;->rX:Lmiui/date/Calendar;

    if-eqz v3, :cond_0

    .line 184
    iget-object v3, p0, Lmiui/widget/DateTimePicker;->bp:Lmiui/date/Calendar;

    iget-object v4, p0, Lmiui/widget/DateTimePicker;->rX:Lmiui/date/Calendar;

    invoke-direct {p0, v3, v4}, Lmiui/widget/DateTimePicker;->a(Lmiui/date/Calendar;Lmiui/date/Calendar;)I

    move-result v3

    if-nez v3, :cond_0

    .line 185
    iget-object v0, p0, Lmiui/widget/DateTimePicker;->rX:Lmiui/date/Calendar;

    invoke-virtual {v0, v5}, Lmiui/date/Calendar;->get(I)I

    move-result v0

    .line 186
    iget-object v3, p0, Lmiui/widget/DateTimePicker;->HG:Lmiui/widget/NumberPicker;

    invoke-virtual {v3, v0}, Lmiui/widget/NumberPicker;->setMinValue(I)V

    .line 187
    iget-object v0, p0, Lmiui/widget/DateTimePicker;->HG:Lmiui/widget/NumberPicker;

    invoke-virtual {v0, v2}, Lmiui/widget/NumberPicker;->setWrapSelectorWheel(Z)V

    move v0, v1

    .line 191
    :cond_0
    if-nez v0, :cond_1

    .line 192
    iget-object v0, p0, Lmiui/widget/DateTimePicker;->HG:Lmiui/widget/NumberPicker;

    invoke-virtual {v0, v2}, Lmiui/widget/NumberPicker;->setMinValue(I)V

    .line 193
    iget-object v0, p0, Lmiui/widget/DateTimePicker;->HG:Lmiui/widget/NumberPicker;

    const/16 v2, 0x17

    invoke-virtual {v0, v2}, Lmiui/widget/NumberPicker;->setMaxValue(I)V

    .line 194
    iget-object v0, p0, Lmiui/widget/DateTimePicker;->HG:Lmiui/widget/NumberPicker;

    invoke-virtual {v0, v1}, Lmiui/widget/NumberPicker;->setWrapSelectorWheel(Z)V

    .line 196
    :cond_1
    iget-object v0, p0, Lmiui/widget/DateTimePicker;->bp:Lmiui/date/Calendar;

    invoke-virtual {v0, v5}, Lmiui/date/Calendar;->get(I)I

    move-result v0

    .line 197
    iget-object v1, p0, Lmiui/widget/DateTimePicker;->HG:Lmiui/widget/NumberPicker;

    invoke-virtual {v1, v0}, Lmiui/widget/NumberPicker;->setValue(I)V

    .line 198
    return-void

    :cond_2
    move v0, v2

    goto :goto_0
.end method

.method private dM()V
    .locals 11

    .prologue
    const/16 v10, 0x9

    const/4 v9, 0x5

    const/4 v6, 0x2

    const/4 v2, 0x1

    const/4 v5, 0x0

    .line 215
    iget-object v0, p0, Lmiui/widget/DateTimePicker;->rX:Lmiui/date/Calendar;

    if-nez v0, :cond_6

    const v0, 0x7fffffff

    .line 216
    :goto_0
    iget-object v1, p0, Lmiui/widget/DateTimePicker;->rY:Lmiui/date/Calendar;

    if-nez v1, :cond_7

    const v1, 0x7fffffff

    .line 217
    :goto_1
    if-gt v0, v6, :cond_8

    if-gt v1, v6, :cond_8

    .line 218
    iget-object v1, p0, Lmiui/widget/DateTimePicker;->rY:Lmiui/date/Calendar;

    iget-object v3, p0, Lmiui/widget/DateTimePicker;->rX:Lmiui/date/Calendar;

    invoke-direct {p0, v1, v3}, Lmiui/widget/DateTimePicker;->a(Lmiui/date/Calendar;Lmiui/date/Calendar;)I

    move-result v1

    .line 219
    iget-object v3, p0, Lmiui/widget/DateTimePicker;->HF:Lmiui/widget/NumberPicker;

    invoke-direct {p0, v3, v5, v1}, Lmiui/widget/DateTimePicker;->c(Lmiui/widget/NumberPicker;II)V

    .line 220
    iget-object v3, p0, Lmiui/widget/DateTimePicker;->HF:Lmiui/widget/NumberPicker;

    invoke-virtual {v3, v5}, Lmiui/widget/NumberPicker;->setMinValue(I)V

    .line 221
    iget-object v3, p0, Lmiui/widget/DateTimePicker;->HF:Lmiui/widget/NumberPicker;

    invoke-virtual {v3, v1}, Lmiui/widget/NumberPicker;->setMaxValue(I)V

    .line 222
    iget-object v1, p0, Lmiui/widget/DateTimePicker;->HF:Lmiui/widget/NumberPicker;

    invoke-virtual {v1, v0}, Lmiui/widget/NumberPicker;->setValue(I)V

    .line 223
    iput v0, p0, Lmiui/widget/DateTimePicker;->HS:I

    .line 224
    iget-object v0, p0, Lmiui/widget/DateTimePicker;->HF:Lmiui/widget/NumberPicker;

    invoke-virtual {v0, v5}, Lmiui/widget/NumberPicker;->setWrapSelectorWheel(Z)V

    .line 243
    :cond_0
    :goto_2
    iget-object v0, p0, Lmiui/widget/DateTimePicker;->HF:Lmiui/widget/NumberPicker;

    invoke-virtual {v0}, Lmiui/widget/NumberPicker;->getMaxValue()I

    move-result v0

    iget-object v1, p0, Lmiui/widget/DateTimePicker;->HF:Lmiui/widget/NumberPicker;

    invoke-virtual {v1}, Lmiui/widget/NumberPicker;->getMinValue()I

    move-result v1

    sub-int/2addr v0, v1

    add-int/lit8 v0, v0, 0x1

    .line 244
    iget-object v1, p0, Lmiui/widget/DateTimePicker;->HV:[Ljava/lang/String;

    if-eqz v1, :cond_1

    iget-object v1, p0, Lmiui/widget/DateTimePicker;->HV:[Ljava/lang/String;

    array-length v1, v1

    if-eq v1, v0, :cond_2

    .line 245
    :cond_1
    new-array v0, v0, [Ljava/lang/String;

    iput-object v0, p0, Lmiui/widget/DateTimePicker;->HV:[Ljava/lang/String;

    .line 248
    :cond_2
    iget-object v0, p0, Lmiui/widget/DateTimePicker;->HF:Lmiui/widget/NumberPicker;

    invoke-virtual {v0}, Lmiui/widget/NumberPicker;->getValue()I

    move-result v3

    .line 249
    sget-object v0, Lmiui/widget/DateTimePicker;->HP:Ljava/lang/ThreadLocal;

    invoke-virtual {v0}, Ljava/lang/ThreadLocal;->get()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/date/Calendar;

    .line 250
    if-nez v0, :cond_3

    .line 251
    new-instance v0, Lmiui/date/Calendar;

    invoke-direct {v0}, Lmiui/date/Calendar;-><init>()V

    .line 252
    sget-object v1, Lmiui/widget/DateTimePicker;->HP:Ljava/lang/ThreadLocal;

    invoke-virtual {v1, v0}, Ljava/lang/ThreadLocal;->set(Ljava/lang/Object;)V

    .line 254
    :cond_3
    iget-object v1, p0, Lmiui/widget/DateTimePicker;->bp:Lmiui/date/Calendar;

    invoke-virtual {v1}, Lmiui/date/Calendar;->getTimeInMillis()J

    move-result-wide v4

    invoke-virtual {v0, v4, v5}, Lmiui/date/Calendar;->setTimeInMillis(J)Lmiui/date/Calendar;

    .line 255
    iget-object v1, p0, Lmiui/widget/DateTimePicker;->HV:[Ljava/lang/String;

    invoke-virtual {v0, v2}, Lmiui/date/Calendar;->get(I)I

    move-result v4

    invoke-virtual {v0, v9}, Lmiui/date/Calendar;->get(I)I

    move-result v5

    invoke-virtual {v0, v10}, Lmiui/date/Calendar;->get(I)I

    move-result v6

    invoke-direct {p0, v4, v5, v6}, Lmiui/widget/DateTimePicker;->formatDay(III)Ljava/lang/String;

    move-result-object v4

    aput-object v4, v1, v3

    move v1, v2

    .line 257
    :goto_3
    const/4 v4, 0x3

    if-gt v1, v4, :cond_4

    .line 258
    const/16 v4, 0xc

    invoke-virtual {v0, v4, v2}, Lmiui/date/Calendar;->add(II)Lmiui/date/Calendar;

    .line 259
    add-int v4, v3, v1

    rem-int/lit8 v4, v4, 0x7

    .line 260
    iget-object v5, p0, Lmiui/widget/DateTimePicker;->HV:[Ljava/lang/String;

    array-length v5, v5

    if-lt v4, v5, :cond_b

    .line 266
    :cond_4
    iget-object v1, p0, Lmiui/widget/DateTimePicker;->bp:Lmiui/date/Calendar;

    invoke-virtual {v1}, Lmiui/date/Calendar;->getTimeInMillis()J

    move-result-wide v4

    invoke-virtual {v0, v4, v5}, Lmiui/date/Calendar;->setTimeInMillis(J)Lmiui/date/Calendar;

    move v1, v2

    .line 267
    :goto_4
    const/4 v4, 0x3

    if-gt v1, v4, :cond_5

    .line 268
    const/16 v4, 0xc

    const/4 v5, -0x1

    invoke-virtual {v0, v4, v5}, Lmiui/date/Calendar;->add(II)Lmiui/date/Calendar;

    .line 269
    sub-int v4, v3, v1

    add-int/lit8 v4, v4, 0x7

    rem-int/lit8 v4, v4, 0x7

    .line 270
    iget-object v5, p0, Lmiui/widget/DateTimePicker;->HV:[Ljava/lang/String;

    array-length v5, v5

    if-lt v4, v5, :cond_c

    .line 276
    :cond_5
    iget-object v0, p0, Lmiui/widget/DateTimePicker;->HF:Lmiui/widget/NumberPicker;

    iget-object v1, p0, Lmiui/widget/DateTimePicker;->HV:[Ljava/lang/String;

    invoke-virtual {v0, v1}, Lmiui/widget/NumberPicker;->setDisplayedValues([Ljava/lang/String;)V

    .line 277
    return-void

    .line 215
    :cond_6
    iget-object v0, p0, Lmiui/widget/DateTimePicker;->bp:Lmiui/date/Calendar;

    iget-object v1, p0, Lmiui/widget/DateTimePicker;->rX:Lmiui/date/Calendar;

    invoke-direct {p0, v0, v1}, Lmiui/widget/DateTimePicker;->a(Lmiui/date/Calendar;Lmiui/date/Calendar;)I

    move-result v0

    goto/16 :goto_0

    .line 216
    :cond_7
    iget-object v1, p0, Lmiui/widget/DateTimePicker;->rY:Lmiui/date/Calendar;

    iget-object v3, p0, Lmiui/widget/DateTimePicker;->bp:Lmiui/date/Calendar;

    invoke-direct {p0, v1, v3}, Lmiui/widget/DateTimePicker;->a(Lmiui/date/Calendar;Lmiui/date/Calendar;)I

    move-result v1

    goto/16 :goto_1

    .line 226
    :cond_8
    iget-object v3, p0, Lmiui/widget/DateTimePicker;->HF:Lmiui/widget/NumberPicker;

    const/4 v4, 0x6

    invoke-direct {p0, v3, v5, v4}, Lmiui/widget/DateTimePicker;->c(Lmiui/widget/NumberPicker;II)V

    .line 227
    iget-object v3, p0, Lmiui/widget/DateTimePicker;->HF:Lmiui/widget/NumberPicker;

    invoke-virtual {v3, v5}, Lmiui/widget/NumberPicker;->setMinValue(I)V

    .line 228
    iget-object v3, p0, Lmiui/widget/DateTimePicker;->HF:Lmiui/widget/NumberPicker;

    const/4 v4, 0x6

    invoke-virtual {v3, v4}, Lmiui/widget/NumberPicker;->setMaxValue(I)V

    .line 229
    if-gt v0, v6, :cond_9

    .line 230
    iget-object v3, p0, Lmiui/widget/DateTimePicker;->HF:Lmiui/widget/NumberPicker;

    invoke-virtual {v3, v0}, Lmiui/widget/NumberPicker;->setValue(I)V

    .line 231
    iput v0, p0, Lmiui/widget/DateTimePicker;->HS:I

    .line 232
    iget-object v3, p0, Lmiui/widget/DateTimePicker;->HF:Lmiui/widget/NumberPicker;

    invoke-virtual {v3, v5}, Lmiui/widget/NumberPicker;->setWrapSelectorWheel(Z)V

    .line 234
    :cond_9
    if-gt v1, v6, :cond_a

    .line 235
    rsub-int/lit8 v3, v1, 0x6

    iput v3, p0, Lmiui/widget/DateTimePicker;->HS:I

    .line 236
    iget-object v3, p0, Lmiui/widget/DateTimePicker;->HF:Lmiui/widget/NumberPicker;

    iget v4, p0, Lmiui/widget/DateTimePicker;->HS:I

    invoke-virtual {v3, v4}, Lmiui/widget/NumberPicker;->setValue(I)V

    .line 237
    iget-object v3, p0, Lmiui/widget/DateTimePicker;->HF:Lmiui/widget/NumberPicker;

    invoke-virtual {v3, v5}, Lmiui/widget/NumberPicker;->setWrapSelectorWheel(Z)V

    .line 239
    :cond_a
    if-le v0, v6, :cond_0

    if-le v1, v6, :cond_0

    .line 240
    iget-object v0, p0, Lmiui/widget/DateTimePicker;->HF:Lmiui/widget/NumberPicker;

    invoke-virtual {v0, v2}, Lmiui/widget/NumberPicker;->setWrapSelectorWheel(Z)V

    goto/16 :goto_2

    .line 263
    :cond_b
    iget-object v5, p0, Lmiui/widget/DateTimePicker;->HV:[Ljava/lang/String;

    invoke-virtual {v0, v2}, Lmiui/date/Calendar;->get(I)I

    move-result v6

    invoke-virtual {v0, v9}, Lmiui/date/Calendar;->get(I)I

    move-result v7

    invoke-virtual {v0, v10}, Lmiui/date/Calendar;->get(I)I

    move-result v8

    invoke-direct {p0, v6, v7, v8}, Lmiui/widget/DateTimePicker;->formatDay(III)Ljava/lang/String;

    move-result-object v6

    aput-object v6, v5, v4

    .line 257
    add-int/lit8 v1, v1, 0x1

    goto/16 :goto_3

    .line 273
    :cond_c
    iget-object v5, p0, Lmiui/widget/DateTimePicker;->HV:[Ljava/lang/String;

    invoke-virtual {v0, v2}, Lmiui/date/Calendar;->get(I)I

    move-result v6

    invoke-virtual {v0, v9}, Lmiui/date/Calendar;->get(I)I

    move-result v7

    invoke-virtual {v0, v10}, Lmiui/date/Calendar;->get(I)I

    move-result v8

    invoke-direct {p0, v6, v7, v8}, Lmiui/widget/DateTimePicker;->formatDay(III)Ljava/lang/String;

    move-result-object v6

    aput-object v6, v5, v4

    .line 267
    add-int/lit8 v1, v1, 0x1

    goto/16 :goto_4
.end method

.method static synthetic dN()Ljava/lang/ThreadLocal;
    .locals 1

    .prologue
    .line 24
    sget-object v0, Lmiui/widget/DateTimePicker;->HW:Ljava/lang/ThreadLocal;

    return-object v0
.end method

.method static synthetic e(Lmiui/widget/DateTimePicker;)Lmiui/widget/NumberPicker;
    .locals 1

    .prologue
    .line 24
    iget-object v0, p0, Lmiui/widget/DateTimePicker;->HG:Lmiui/widget/NumberPicker;

    return-object v0
.end method

.method static synthetic f(Lmiui/widget/DateTimePicker;)Lmiui/widget/NumberPicker;
    .locals 1

    .prologue
    .line 24
    iget-object v0, p0, Lmiui/widget/DateTimePicker;->HH:Lmiui/widget/NumberPicker;

    return-object v0
.end method

.method private formatDay(III)Ljava/lang/String;
    .locals 2

    .prologue
    .line 503
    sget-object v0, Lmiui/widget/DateTimePicker;->HL:Lmiui/widget/DateTimePicker$DayFormatter;

    .line 504
    iget-boolean v1, p0, Lmiui/widget/DateTimePicker;->sb:Z

    if-eqz v1, :cond_1

    .line 505
    iget-object v0, p0, Lmiui/widget/DateTimePicker;->HM:Lmiui/widget/DateTimePicker$DayFormatter;

    if-nez v0, :cond_0

    .line 506
    new-instance v0, Lmiui/widget/DateTimePicker$LunarFormatter;

    invoke-direct {v0}, Lmiui/widget/DateTimePicker$LunarFormatter;-><init>()V

    iput-object v0, p0, Lmiui/widget/DateTimePicker;->HM:Lmiui/widget/DateTimePicker$DayFormatter;

    .line 508
    :cond_0
    iget-object v0, p0, Lmiui/widget/DateTimePicker;->HM:Lmiui/widget/DateTimePicker$DayFormatter;

    .line 510
    :cond_1
    iget-object v1, p0, Lmiui/widget/DateTimePicker;->HJ:Lmiui/widget/DateTimePicker$DayFormatter;

    if-eqz v1, :cond_2

    iget-object v0, p0, Lmiui/widget/DateTimePicker;->HJ:Lmiui/widget/DateTimePicker$DayFormatter;

    .line 511
    :cond_2
    invoke-virtual {v0, p1, p2, p3}, Lmiui/widget/DateTimePicker$DayFormatter;->formatDay(III)Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method static synthetic g(Lmiui/widget/DateTimePicker;)I
    .locals 1

    .prologue
    .line 24
    iget v0, p0, Lmiui/widget/DateTimePicker;->HQ:I

    return v0
.end method

.method static synthetic h(Lmiui/widget/DateTimePicker;)V
    .locals 0

    .prologue
    .line 24
    invoke-direct {p0}, Lmiui/widget/DateTimePicker;->dJ()V

    return-void
.end method

.method static synthetic i(Lmiui/widget/DateTimePicker;)V
    .locals 0

    .prologue
    .line 24
    invoke-direct {p0}, Lmiui/widget/DateTimePicker;->dM()V

    return-void
.end method

.method static synthetic j(Lmiui/widget/DateTimePicker;)V
    .locals 0

    .prologue
    .line 24
    invoke-direct {p0}, Lmiui/widget/DateTimePicker;->dL()V

    return-void
.end method

.method static synthetic k(Lmiui/widget/DateTimePicker;)V
    .locals 0

    .prologue
    .line 24
    invoke-direct {p0}, Lmiui/widget/DateTimePicker;->dK()V

    return-void
.end method

.method static synthetic l(Lmiui/widget/DateTimePicker;)Lmiui/widget/DateTimePicker$OnDateTimeChangedListener;
    .locals 1

    .prologue
    .line 24
    iget-object v0, p0, Lmiui/widget/DateTimePicker;->HO:Lmiui/widget/DateTimePicker$OnDateTimeChangedListener;

    return-object v0
.end method


# virtual methods
.method protected dispatchRestoreInstanceState(Landroid/util/SparseArray;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Landroid/util/SparseArray",
            "<",
            "Landroid/os/Parcelable;",
            ">;)V"
        }
    .end annotation

    .prologue
    .line 384
    invoke-virtual {p0, p1}, Lmiui/widget/DateTimePicker;->dispatchThawSelfOnly(Landroid/util/SparseArray;)V

    .line 385
    return-void
.end method

.method public getTimeInMillis()J
    .locals 2

    .prologue
    .line 331
    iget-object v0, p0, Lmiui/widget/DateTimePicker;->bp:Lmiui/date/Calendar;

    invoke-virtual {v0}, Lmiui/date/Calendar;->getTimeInMillis()J

    move-result-wide v0

    return-wide v0
.end method

.method protected onRestoreInstanceState(Landroid/os/Parcelable;)V
    .locals 2

    .prologue
    .line 394
    check-cast p1, Lmiui/widget/DateTimePicker$b;

    .line 395
    invoke-virtual {p1}, Lmiui/widget/DateTimePicker$b;->getSuperState()Landroid/os/Parcelable;

    move-result-object v0

    invoke-super {p0, v0}, Landroid/widget/LinearLayout;->onRestoreInstanceState(Landroid/os/Parcelable;)V

    .line 396
    invoke-virtual {p1}, Lmiui/widget/DateTimePicker$b;->getTimeInMillis()J

    move-result-wide v0

    invoke-virtual {p0, v0, v1}, Lmiui/widget/DateTimePicker;->update(J)V

    .line 397
    return-void
.end method

.method protected onSaveInstanceState()Landroid/os/Parcelable;
    .locals 4

    .prologue
    .line 389
    invoke-super {p0}, Landroid/widget/LinearLayout;->onSaveInstanceState()Landroid/os/Parcelable;

    move-result-object v0

    .line 390
    new-instance v1, Lmiui/widget/DateTimePicker$b;

    invoke-virtual {p0}, Lmiui/widget/DateTimePicker;->getTimeInMillis()J

    move-result-wide v2

    invoke-direct {v1, v0, v2, v3}, Lmiui/widget/DateTimePicker$b;-><init>(Landroid/os/Parcelable;J)V

    return-object v1
.end method

.method public setDayFormatter(Lmiui/widget/DateTimePicker$DayFormatter;)V
    .locals 0

    .prologue
    .line 489
    iput-object p1, p0, Lmiui/widget/DateTimePicker;->HJ:Lmiui/widget/DateTimePicker$DayFormatter;

    .line 490
    invoke-direct {p0}, Lmiui/widget/DateTimePicker;->dM()V

    .line 491
    return-void
.end method

.method public setLunarMode(Z)V
    .locals 0

    .prologue
    .line 498
    iput-boolean p1, p0, Lmiui/widget/DateTimePicker;->sb:Z

    .line 499
    invoke-direct {p0}, Lmiui/widget/DateTimePicker;->dM()V

    .line 500
    return-void
.end method

.method public setMaxDateTime(J)V
    .locals 4

    .prologue
    .line 363
    const-wide/16 v0, 0x0

    cmp-long v0, p1, v0

    if-gtz v0, :cond_1

    .line 364
    const/4 v0, 0x0

    iput-object v0, p0, Lmiui/widget/DateTimePicker;->rY:Lmiui/date/Calendar;

    .line 373
    :cond_0
    :goto_0
    invoke-direct {p0}, Lmiui/widget/DateTimePicker;->dJ()V

    .line 374
    invoke-direct {p0}, Lmiui/widget/DateTimePicker;->dM()V

    .line 375
    invoke-direct {p0}, Lmiui/widget/DateTimePicker;->dL()V

    .line 376
    invoke-direct {p0}, Lmiui/widget/DateTimePicker;->dK()V

    .line 377
    return-void

    .line 366
    :cond_1
    new-instance v0, Lmiui/date/Calendar;

    invoke-direct {v0}, Lmiui/date/Calendar;-><init>()V

    iput-object v0, p0, Lmiui/widget/DateTimePicker;->rY:Lmiui/date/Calendar;

    .line 367
    iget-object v0, p0, Lmiui/widget/DateTimePicker;->rY:Lmiui/date/Calendar;

    invoke-virtual {v0, p1, p2}, Lmiui/date/Calendar;->setTimeInMillis(J)Lmiui/date/Calendar;

    .line 368
    iget-object v0, p0, Lmiui/widget/DateTimePicker;->rY:Lmiui/date/Calendar;

    const/4 v1, 0x0

    invoke-direct {p0, v0, v1}, Lmiui/widget/DateTimePicker;->a(Lmiui/date/Calendar;Z)V

    .line 369
    iget-object v0, p0, Lmiui/widget/DateTimePicker;->rX:Lmiui/date/Calendar;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lmiui/widget/DateTimePicker;->rX:Lmiui/date/Calendar;

    invoke-virtual {v0}, Lmiui/date/Calendar;->getTimeInMillis()J

    move-result-wide v0

    iget-object v2, p0, Lmiui/widget/DateTimePicker;->rY:Lmiui/date/Calendar;

    invoke-virtual {v2}, Lmiui/date/Calendar;->getTimeInMillis()J

    move-result-wide v2

    cmp-long v0, v0, v2

    if-lez v0, :cond_0

    .line 370
    iget-object v0, p0, Lmiui/widget/DateTimePicker;->rY:Lmiui/date/Calendar;

    iget-object v1, p0, Lmiui/widget/DateTimePicker;->rX:Lmiui/date/Calendar;

    invoke-virtual {v1}, Lmiui/date/Calendar;->getTimeInMillis()J

    move-result-wide v1

    invoke-virtual {v0, v1, v2}, Lmiui/date/Calendar;->setTimeInMillis(J)Lmiui/date/Calendar;

    goto :goto_0
.end method

.method public setMinDateTime(J)V
    .locals 4

    .prologue
    const/4 v2, 0x1

    .line 339
    const-wide/16 v0, 0x0

    cmp-long v0, p1, v0

    if-gtz v0, :cond_1

    .line 340
    const/4 v0, 0x0

    iput-object v0, p0, Lmiui/widget/DateTimePicker;->rX:Lmiui/date/Calendar;

    .line 352
    :cond_0
    :goto_0
    invoke-direct {p0}, Lmiui/widget/DateTimePicker;->dJ()V

    .line 353
    invoke-direct {p0}, Lmiui/widget/DateTimePicker;->dM()V

    .line 354
    invoke-direct {p0}, Lmiui/widget/DateTimePicker;->dL()V

    .line 355
    invoke-direct {p0}, Lmiui/widget/DateTimePicker;->dK()V

    .line 356
    return-void

    .line 342
    :cond_1
    new-instance v0, Lmiui/date/Calendar;

    invoke-direct {v0}, Lmiui/date/Calendar;-><init>()V

    iput-object v0, p0, Lmiui/widget/DateTimePicker;->rX:Lmiui/date/Calendar;

    .line 343
    iget-object v0, p0, Lmiui/widget/DateTimePicker;->rX:Lmiui/date/Calendar;

    invoke-virtual {v0, p1, p2}, Lmiui/date/Calendar;->setTimeInMillis(J)Lmiui/date/Calendar;

    .line 344
    iget-object v0, p0, Lmiui/widget/DateTimePicker;->rX:Lmiui/date/Calendar;

    const/16 v1, 0x15

    invoke-virtual {v0, v1}, Lmiui/date/Calendar;->get(I)I

    move-result v0

    if-nez v0, :cond_2

    iget-object v0, p0, Lmiui/widget/DateTimePicker;->rX:Lmiui/date/Calendar;

    const/16 v1, 0x16

    invoke-virtual {v0, v1}, Lmiui/date/Calendar;->get(I)I

    move-result v0

    if-eqz v0, :cond_3

    .line 345
    :cond_2
    iget-object v0, p0, Lmiui/widget/DateTimePicker;->rX:Lmiui/date/Calendar;

    const/16 v1, 0x14

    invoke-virtual {v0, v1, v2}, Lmiui/date/Calendar;->add(II)Lmiui/date/Calendar;

    .line 347
    :cond_3
    iget-object v0, p0, Lmiui/widget/DateTimePicker;->rX:Lmiui/date/Calendar;

    invoke-direct {p0, v0, v2}, Lmiui/widget/DateTimePicker;->a(Lmiui/date/Calendar;Z)V

    .line 348
    iget-object v0, p0, Lmiui/widget/DateTimePicker;->rY:Lmiui/date/Calendar;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lmiui/widget/DateTimePicker;->rY:Lmiui/date/Calendar;

    invoke-virtual {v0}, Lmiui/date/Calendar;->getTimeInMillis()J

    move-result-wide v0

    iget-object v2, p0, Lmiui/widget/DateTimePicker;->rX:Lmiui/date/Calendar;

    invoke-virtual {v2}, Lmiui/date/Calendar;->getTimeInMillis()J

    move-result-wide v2

    cmp-long v0, v0, v2

    if-gez v0, :cond_0

    .line 349
    iget-object v0, p0, Lmiui/widget/DateTimePicker;->rX:Lmiui/date/Calendar;

    iget-object v1, p0, Lmiui/widget/DateTimePicker;->rY:Lmiui/date/Calendar;

    invoke-virtual {v1}, Lmiui/date/Calendar;->getTimeInMillis()J

    move-result-wide v1

    invoke-virtual {v0, v1, v2}, Lmiui/date/Calendar;->setTimeInMillis(J)Lmiui/date/Calendar;

    goto :goto_0
.end method

.method public setMinuteInterval(I)V
    .locals 2

    .prologue
    .line 301
    iget v0, p0, Lmiui/widget/DateTimePicker;->HQ:I

    if-ne v0, p1, :cond_0

    .line 310
    :goto_0
    return-void

    .line 305
    :cond_0
    iput p1, p0, Lmiui/widget/DateTimePicker;->HQ:I

    .line 306
    iget-object v0, p0, Lmiui/widget/DateTimePicker;->bp:Lmiui/date/Calendar;

    const/4 v1, 0x1

    invoke-direct {p0, v0, v1}, Lmiui/widget/DateTimePicker;->a(Lmiui/date/Calendar;Z)V

    .line 308
    invoke-direct {p0}, Lmiui/widget/DateTimePicker;->dJ()V

    .line 309
    invoke-direct {p0}, Lmiui/widget/DateTimePicker;->dK()V

    goto :goto_0
.end method

.method public setOnTimeChangedListener(Lmiui/widget/DateTimePicker$OnDateTimeChangedListener;)V
    .locals 0

    .prologue
    .line 292
    iput-object p1, p0, Lmiui/widget/DateTimePicker;->HO:Lmiui/widget/DateTimePicker$OnDateTimeChangedListener;

    .line 293
    return-void
.end method

.method public update(J)V
    .locals 2

    .prologue
    .line 318
    iget-object v0, p0, Lmiui/widget/DateTimePicker;->bp:Lmiui/date/Calendar;

    invoke-virtual {v0, p1, p2}, Lmiui/date/Calendar;->setTimeInMillis(J)Lmiui/date/Calendar;

    .line 319
    iget-object v0, p0, Lmiui/widget/DateTimePicker;->bp:Lmiui/date/Calendar;

    const/4 v1, 0x1

    invoke-direct {p0, v0, v1}, Lmiui/widget/DateTimePicker;->a(Lmiui/date/Calendar;Z)V

    .line 320
    invoke-direct {p0}, Lmiui/widget/DateTimePicker;->dJ()V

    .line 321
    invoke-direct {p0}, Lmiui/widget/DateTimePicker;->dM()V

    .line 322
    invoke-direct {p0}, Lmiui/widget/DateTimePicker;->dL()V

    .line 323
    invoke-direct {p0}, Lmiui/widget/DateTimePicker;->dK()V

    .line 324
    return-void
.end method
