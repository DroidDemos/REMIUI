.class public Lmiui/widget/DatePicker;
.super Landroid/widget/FrameLayout;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lmiui/widget/DatePicker$a;,
        Lmiui/widget/DatePicker$OnDateChangedListener;
    }
.end annotation


# static fields
.field private static final DATE_FORMAT:Ljava/lang/String; = "MM/dd/yyyy"

.field private static final LOG_TAG:Ljava/lang/String;

.field private static final rE:I = 0x76c

.field private static final rF:I = 0x834

.field private static final rG:Z = false

.field private static final rH:Z = true

.field private static final rI:Z = true

.field private static final rQ:[Ljava/lang/String;

.field private static final rR:[Ljava/lang/String;

.field private static final rS:[Ljava/lang/String;

.field private static final rT:Ljava/lang/String;


# instance fields
.field private fe:Lmiui/widget/DatePicker$OnDateChangedListener;

.field private final rJ:Landroid/widget/LinearLayout;

.field private final rK:Lmiui/widget/NumberPicker;

.field private final rL:Lmiui/widget/NumberPicker;

.field private final rM:Lmiui/widget/NumberPicker;

.field private rN:Ljava/util/Locale;

.field private rO:[Ljava/lang/String;

.field private rP:[C

.field private final rU:Ljava/text/DateFormat;

.field private rV:I

.field private rW:Lmiui/date/Calendar;

.field private rX:Lmiui/date/Calendar;

.field private rY:Lmiui/date/Calendar;

.field private rZ:Lmiui/date/Calendar;

.field private sa:Z

.field private sb:Z


# direct methods
.method static constructor <clinit>()V
    .locals 5

    .prologue
    .line 45
    const-class v0, Lmiui/widget/DatePicker;

    invoke-virtual {v0}, Ljava/lang/Class;->getSimpleName()Ljava/lang/String;

    move-result-object v0

    sput-object v0, Lmiui/widget/DatePicker;->LOG_TAG:Ljava/lang/String;

    .line 75
    invoke-static {}, Lmiui/date/CalendarFormatSymbols;->getDefault()Lmiui/date/CalendarFormatSymbols;

    move-result-object v0

    invoke-virtual {v0}, Lmiui/date/CalendarFormatSymbols;->getChineseDays()[Ljava/lang/String;

    move-result-object v0

    sput-object v0, Lmiui/widget/DatePicker;->rQ:[Ljava/lang/String;

    .line 76
    invoke-static {}, Lmiui/date/CalendarFormatSymbols;->getDefault()Lmiui/date/CalendarFormatSymbols;

    move-result-object v0

    invoke-virtual {v0}, Lmiui/date/CalendarFormatSymbols;->getChineseMonths()[Ljava/lang/String;

    move-result-object v0

    sput-object v0, Lmiui/widget/DatePicker;->rR:[Ljava/lang/String;

    .line 78
    invoke-static {}, Lcom/miui/internal/util/PackageConstants;->getCurrentApplication()Landroid/app/Application;

    move-result-object v0

    invoke-virtual {v0}, Landroid/app/Application;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    .line 79
    const/4 v0, 0x0

    :goto_0
    sget-object v2, Lmiui/widget/DatePicker;->rR:[Ljava/lang/String;

    array-length v2, v2

    if-ge v0, v2, :cond_0

    .line 80
    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    sget-object v3, Lmiui/widget/DatePicker;->rR:[Ljava/lang/String;

    aget-object v4, v3, v0

    invoke-virtual {v2, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    sget v4, Lcom/miui/internal/R$string;->chinese_month:I

    invoke-virtual {v1, v4}, Landroid/content/res/Resources;->getString(I)Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v2, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    aput-object v2, v3, v0

    .line 79
    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    .line 83
    :cond_0
    sget-object v0, Lmiui/widget/DatePicker;->rR:[Ljava/lang/String;

    array-length v0, v0

    add-int/lit8 v0, v0, 0x1

    new-array v0, v0, [Ljava/lang/String;

    sput-object v0, Lmiui/widget/DatePicker;->rS:[Ljava/lang/String;

    .line 84
    invoke-static {}, Lmiui/date/CalendarFormatSymbols;->getDefault()Lmiui/date/CalendarFormatSymbols;

    move-result-object v0

    invoke-virtual {v0}, Lmiui/date/CalendarFormatSymbols;->getChineseLeapMonths()[Ljava/lang/String;

    move-result-object v0

    const/4 v1, 0x1

    aget-object v0, v0, v1

    sput-object v0, Lmiui/widget/DatePicker;->rT:Ljava/lang/String;

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;)V
    .locals 1

    .prologue
    .line 119
    const/4 v0, 0x0

    invoke-direct {p0, p1, v0}, Lmiui/widget/DatePicker;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    .line 120
    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 1

    .prologue
    .line 123
    sget v0, Lcom/miui/internal/R$attr;->datePickerStyle:I

    invoke-direct {p0, p1, p2, v0}, Lmiui/widget/DatePicker;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    .line 124
    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V
    .locals 12

    .prologue
    .line 127
    invoke-direct {p0, p1, p2, p3}, Landroid/widget/FrameLayout;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    .line 86
    new-instance v0, Ljava/text/SimpleDateFormat;

    const-string v1, "MM/dd/yyyy"

    invoke-direct {v0, v1}, Ljava/text/SimpleDateFormat;-><init>(Ljava/lang/String;)V

    iput-object v0, p0, Lmiui/widget/DatePicker;->rU:Ljava/text/DateFormat;

    .line 98
    const/4 v0, 0x1

    iput-boolean v0, p0, Lmiui/widget/DatePicker;->sa:Z

    .line 100
    const/4 v0, 0x0

    iput-boolean v0, p0, Lmiui/widget/DatePicker;->sb:Z

    .line 129
    new-instance v0, Lmiui/date/Calendar;

    invoke-direct {v0}, Lmiui/date/Calendar;-><init>()V

    iput-object v0, p0, Lmiui/widget/DatePicker;->rW:Lmiui/date/Calendar;

    .line 130
    new-instance v0, Lmiui/date/Calendar;

    invoke-direct {v0}, Lmiui/date/Calendar;-><init>()V

    iput-object v0, p0, Lmiui/widget/DatePicker;->rX:Lmiui/date/Calendar;

    .line 131
    new-instance v0, Lmiui/date/Calendar;

    invoke-direct {v0}, Lmiui/date/Calendar;-><init>()V

    iput-object v0, p0, Lmiui/widget/DatePicker;->rY:Lmiui/date/Calendar;

    .line 132
    new-instance v0, Lmiui/date/Calendar;

    invoke-direct {v0}, Lmiui/date/Calendar;-><init>()V

    iput-object v0, p0, Lmiui/widget/DatePicker;->rZ:Lmiui/date/Calendar;

    .line 134
    sget-object v0, Lcom/miui/internal/R$styleable;->DatePicker:[I

    const/4 v1, 0x0

    invoke-virtual {p1, p2, v0, p3, v1}, Landroid/content/Context;->obtainStyledAttributes(Landroid/util/AttributeSet;[III)Landroid/content/res/TypedArray;

    move-result-object v0

    .line 136
    const/4 v1, 0x6

    const/4 v2, 0x1

    invoke-virtual {v0, v1, v2}, Landroid/content/res/TypedArray;->getBoolean(IZ)Z

    move-result v2

    .line 138
    const/4 v1, 0x4

    const/16 v3, 0x76c

    invoke-virtual {v0, v1, v3}, Landroid/content/res/TypedArray;->getInt(II)I

    move-result v1

    .line 140
    const/4 v3, 0x5

    const/16 v4, 0x834

    invoke-virtual {v0, v3, v4}, Landroid/content/res/TypedArray;->getInt(II)I

    move-result v8

    .line 141
    const/16 v3, 0x8

    invoke-virtual {v0, v3}, Landroid/content/res/TypedArray;->getString(I)Ljava/lang/String;

    move-result-object v3

    .line 142
    const/16 v4, 0x9

    invoke-virtual {v0, v4}, Landroid/content/res/TypedArray;->getString(I)Ljava/lang/String;

    move-result-object v9

    .line 143
    sget v4, Lcom/miui/internal/R$layout;->date_picker:I

    .line 144
    const/4 v5, 0x0

    const/4 v6, 0x0

    invoke-virtual {v0, v5, v6}, Landroid/content/res/TypedArray;->getBoolean(IZ)Z

    move-result v5

    iput-boolean v5, p0, Lmiui/widget/DatePicker;->sb:Z

    .line 145
    const/4 v5, 0x1

    const/4 v6, 0x1

    invoke-virtual {v0, v5, v6}, Landroid/content/res/TypedArray;->getBoolean(IZ)Z

    move-result v5

    .line 146
    const/4 v6, 0x2

    const/4 v7, 0x1

    invoke-virtual {v0, v6, v7}, Landroid/content/res/TypedArray;->getBoolean(IZ)Z

    move-result v6

    .line 147
    const/4 v7, 0x3

    const/4 v10, 0x1

    invoke-virtual {v0, v7, v10}, Landroid/content/res/TypedArray;->getBoolean(IZ)Z

    move-result v7

    .line 148
    invoke-virtual {v0}, Landroid/content/res/TypedArray;->recycle()V

    .line 151
    invoke-static {}, Ljava/util/Locale;->getDefault()Ljava/util/Locale;

    move-result-object v0

    invoke-direct {p0, v0}, Lmiui/widget/DatePicker;->a(Ljava/util/Locale;)V

    .line 153
    const-string v0, "layout_inflater"

    invoke-virtual {p1, v0}, Landroid/content/Context;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/view/LayoutInflater;

    .line 155
    const/4 v10, 0x1

    invoke-virtual {v0, v4, p0, v10}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;Z)Landroid/view/View;

    .line 157
    new-instance v4, Lmiui/widget/DatePicker$1;

    invoke-direct {v4, p0}, Lmiui/widget/DatePicker$1;-><init>(Lmiui/widget/DatePicker;)V

    .line 180
    sget v0, Lcom/miui/internal/R$id;->pickers:I

    invoke-virtual {p0, v0}, Lmiui/widget/DatePicker;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/LinearLayout;

    iput-object v0, p0, Lmiui/widget/DatePicker;->rJ:Landroid/widget/LinearLayout;

    .line 183
    sget v0, Lcom/miui/internal/R$id;->day:I

    invoke-virtual {p0, v0}, Lmiui/widget/DatePicker;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Lmiui/widget/NumberPicker;

    iput-object v0, p0, Lmiui/widget/DatePicker;->rK:Lmiui/widget/NumberPicker;

    .line 184
    iget-object v0, p0, Lmiui/widget/DatePicker;->rK:Lmiui/widget/NumberPicker;

    sget-object v10, Lmiui/widget/NumberPicker;->DS:Lmiui/widget/NumberPicker$Formatter;

    invoke-virtual {v0, v10}, Lmiui/widget/NumberPicker;->setFormatter(Lmiui/widget/NumberPicker$Formatter;)V

    .line 185
    iget-object v0, p0, Lmiui/widget/DatePicker;->rK:Lmiui/widget/NumberPicker;

    const-wide/16 v10, 0x64

    invoke-virtual {v0, v10, v11}, Lmiui/widget/NumberPicker;->setOnLongPressUpdateInterval(J)V

    .line 186
    iget-object v0, p0, Lmiui/widget/DatePicker;->rK:Lmiui/widget/NumberPicker;

    invoke-virtual {v0, v4}, Lmiui/widget/NumberPicker;->setOnValueChangedListener(Lmiui/widget/NumberPicker$OnValueChangeListener;)V

    .line 187
    if-nez v7, :cond_0

    .line 188
    iget-object v0, p0, Lmiui/widget/DatePicker;->rK:Lmiui/widget/NumberPicker;

    const/16 v7, 0x8

    invoke-virtual {v0, v7}, Lmiui/widget/NumberPicker;->setVisibility(I)V

    .line 192
    :cond_0
    sget v0, Lcom/miui/internal/R$id;->month:I

    invoke-virtual {p0, v0}, Lmiui/widget/DatePicker;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Lmiui/widget/NumberPicker;

    iput-object v0, p0, Lmiui/widget/DatePicker;->rL:Lmiui/widget/NumberPicker;

    .line 193
    iget-object v0, p0, Lmiui/widget/DatePicker;->rL:Lmiui/widget/NumberPicker;

    const/4 v7, 0x0

    invoke-virtual {v0, v7}, Lmiui/widget/NumberPicker;->setMinValue(I)V

    .line 194
    iget-object v0, p0, Lmiui/widget/DatePicker;->rL:Lmiui/widget/NumberPicker;

    iget v7, p0, Lmiui/widget/DatePicker;->rV:I

    add-int/lit8 v7, v7, -0x1

    invoke-virtual {v0, v7}, Lmiui/widget/NumberPicker;->setMaxValue(I)V

    .line 195
    iget-object v0, p0, Lmiui/widget/DatePicker;->rL:Lmiui/widget/NumberPicker;

    iget-object v7, p0, Lmiui/widget/DatePicker;->rO:[Ljava/lang/String;

    invoke-virtual {v0, v7}, Lmiui/widget/NumberPicker;->setDisplayedValues([Ljava/lang/String;)V

    .line 196
    iget-object v0, p0, Lmiui/widget/DatePicker;->rL:Lmiui/widget/NumberPicker;

    const-wide/16 v10, 0xc8

    invoke-virtual {v0, v10, v11}, Lmiui/widget/NumberPicker;->setOnLongPressUpdateInterval(J)V

    .line 197
    iget-object v0, p0, Lmiui/widget/DatePicker;->rL:Lmiui/widget/NumberPicker;

    invoke-virtual {v0, v4}, Lmiui/widget/NumberPicker;->setOnValueChangedListener(Lmiui/widget/NumberPicker$OnValueChangeListener;)V

    .line 198
    if-nez v6, :cond_1

    .line 199
    iget-object v0, p0, Lmiui/widget/DatePicker;->rL:Lmiui/widget/NumberPicker;

    const/16 v6, 0x8

    invoke-virtual {v0, v6}, Lmiui/widget/NumberPicker;->setVisibility(I)V

    .line 203
    :cond_1
    sget v0, Lcom/miui/internal/R$id;->year:I

    invoke-virtual {p0, v0}, Lmiui/widget/DatePicker;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Lmiui/widget/NumberPicker;

    iput-object v0, p0, Lmiui/widget/DatePicker;->rM:Lmiui/widget/NumberPicker;

    .line 204
    iget-object v0, p0, Lmiui/widget/DatePicker;->rM:Lmiui/widget/NumberPicker;

    const-wide/16 v6, 0x64

    invoke-virtual {v0, v6, v7}, Lmiui/widget/NumberPicker;->setOnLongPressUpdateInterval(J)V

    .line 205
    iget-object v0, p0, Lmiui/widget/DatePicker;->rM:Lmiui/widget/NumberPicker;

    invoke-virtual {v0, v4}, Lmiui/widget/NumberPicker;->setOnValueChangedListener(Lmiui/widget/NumberPicker$OnValueChangeListener;)V

    .line 206
    if-nez v5, :cond_2

    .line 207
    iget-object v0, p0, Lmiui/widget/DatePicker;->rM:Lmiui/widget/NumberPicker;

    const/16 v4, 0x8

    invoke-virtual {v0, v4}, Lmiui/widget/NumberPicker;->setVisibility(I)V

    .line 212
    :cond_2
    if-nez v2, :cond_6

    .line 213
    const/4 v0, 0x1

    invoke-virtual {p0, v0}, Lmiui/widget/DatePicker;->setSpinnersShown(Z)V

    .line 219
    :goto_0
    iget-object v0, p0, Lmiui/widget/DatePicker;->rW:Lmiui/date/Calendar;

    const-wide/16 v4, 0x0

    invoke-virtual {v0, v4, v5}, Lmiui/date/Calendar;->setTimeInMillis(J)Lmiui/date/Calendar;

    .line 220
    invoke-static {v3}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_7

    .line 221
    iget-object v0, p0, Lmiui/widget/DatePicker;->rW:Lmiui/date/Calendar;

    invoke-direct {p0, v3, v0}, Lmiui/widget/DatePicker;->a(Ljava/lang/String;Lmiui/date/Calendar;)Z

    move-result v0

    if-nez v0, :cond_3

    .line 222
    iget-object v0, p0, Lmiui/widget/DatePicker;->rW:Lmiui/date/Calendar;

    const/4 v2, 0x0

    const/4 v3, 0x1

    const/4 v4, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x0

    const/4 v7, 0x0

    invoke-virtual/range {v0 .. v7}, Lmiui/date/Calendar;->set(IIIIIII)Lmiui/date/Calendar;

    .line 227
    :cond_3
    :goto_1
    iget-object v0, p0, Lmiui/widget/DatePicker;->rW:Lmiui/date/Calendar;

    invoke-virtual {v0}, Lmiui/date/Calendar;->getTimeInMillis()J

    move-result-wide v0

    invoke-virtual {p0, v0, v1}, Lmiui/widget/DatePicker;->setMinDate(J)V

    .line 230
    iget-object v0, p0, Lmiui/widget/DatePicker;->rW:Lmiui/date/Calendar;

    const-wide/16 v1, 0x0

    invoke-virtual {v0, v1, v2}, Lmiui/date/Calendar;->setTimeInMillis(J)Lmiui/date/Calendar;

    .line 231
    invoke-static {v9}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_8

    .line 232
    iget-object v0, p0, Lmiui/widget/DatePicker;->rW:Lmiui/date/Calendar;

    invoke-direct {p0, v9, v0}, Lmiui/widget/DatePicker;->a(Ljava/lang/String;Lmiui/date/Calendar;)Z

    move-result v0

    if-nez v0, :cond_4

    .line 233
    iget-object v0, p0, Lmiui/widget/DatePicker;->rW:Lmiui/date/Calendar;

    const/16 v2, 0xb

    const/16 v3, 0x1f

    const/4 v4, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x0

    const/4 v7, 0x0

    move v1, v8

    invoke-virtual/range {v0 .. v7}, Lmiui/date/Calendar;->set(IIIIIII)Lmiui/date/Calendar;

    .line 238
    :cond_4
    :goto_2
    iget-object v0, p0, Lmiui/widget/DatePicker;->rW:Lmiui/date/Calendar;

    invoke-virtual {v0}, Lmiui/date/Calendar;->getTimeInMillis()J

    move-result-wide v0

    invoke-virtual {p0, v0, v1}, Lmiui/widget/DatePicker;->setMaxDate(J)V

    .line 241
    iget-object v0, p0, Lmiui/widget/DatePicker;->rZ:Lmiui/date/Calendar;

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v1

    invoke-virtual {v0, v1, v2}, Lmiui/date/Calendar;->setTimeInMillis(J)Lmiui/date/Calendar;

    .line 242
    iget-object v0, p0, Lmiui/widget/DatePicker;->rZ:Lmiui/date/Calendar;

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Lmiui/date/Calendar;->get(I)I

    move-result v0

    iget-object v1, p0, Lmiui/widget/DatePicker;->rZ:Lmiui/date/Calendar;

    const/4 v2, 0x5

    invoke-virtual {v1, v2}, Lmiui/date/Calendar;->get(I)I

    move-result v1

    iget-object v2, p0, Lmiui/widget/DatePicker;->rZ:Lmiui/date/Calendar;

    const/16 v3, 0x9

    invoke-virtual {v2, v3}, Lmiui/date/Calendar;->get(I)I

    move-result v2

    const/4 v3, 0x0

    invoke-virtual {p0, v0, v1, v2, v3}, Lmiui/widget/DatePicker;->init(IIILmiui/widget/DatePicker$OnDateChangedListener;)V

    .line 246
    invoke-direct {p0}, Lmiui/widget/DatePicker;->bA()V

    .line 249
    invoke-virtual {p0}, Lmiui/widget/DatePicker;->getImportantForAccessibility()I

    move-result v0

    if-nez v0, :cond_5

    .line 250
    const/4 v0, 0x1

    invoke-virtual {p0, v0}, Lmiui/widget/DatePicker;->setImportantForAccessibility(I)V

    .line 252
    :cond_5
    return-void

    .line 215
    :cond_6
    invoke-virtual {p0, v2}, Lmiui/widget/DatePicker;->setSpinnersShown(Z)V

    goto/16 :goto_0

    .line 225
    :cond_7
    iget-object v0, p0, Lmiui/widget/DatePicker;->rW:Lmiui/date/Calendar;

    const/4 v2, 0x0

    const/4 v3, 0x1

    const/4 v4, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x0

    const/4 v7, 0x0

    invoke-virtual/range {v0 .. v7}, Lmiui/date/Calendar;->set(IIIIIII)Lmiui/date/Calendar;

    goto :goto_1

    .line 236
    :cond_8
    iget-object v0, p0, Lmiui/widget/DatePicker;->rW:Lmiui/date/Calendar;

    const/16 v2, 0xb

    const/16 v3, 0x1f

    const/4 v4, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x0

    const/4 v7, 0x0

    move v1, v8

    invoke-virtual/range {v0 .. v7}, Lmiui/date/Calendar;->set(IIIIIII)Lmiui/date/Calendar;

    goto :goto_2
.end method

.method static synthetic a(Lmiui/widget/DatePicker;)Lmiui/date/Calendar;
    .locals 1

    .prologue
    .line 43
    iget-object v0, p0, Lmiui/widget/DatePicker;->rZ:Lmiui/date/Calendar;

    return-object v0
.end method

.method private a(Ljava/util/Locale;)V
    .locals 2

    .prologue
    .line 432
    iget-object v0, p0, Lmiui/widget/DatePicker;->rN:Ljava/util/Locale;

    invoke-virtual {p1, v0}, Ljava/util/Locale;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 440
    :goto_0
    return-void

    .line 436
    :cond_0
    iput-object p1, p0, Lmiui/widget/DatePicker;->rN:Ljava/util/Locale;

    .line 438
    iget-object v0, p0, Lmiui/widget/DatePicker;->rW:Lmiui/date/Calendar;

    const/4 v1, 0x5

    invoke-virtual {v0, v1}, Lmiui/date/Calendar;->getActualMaximum(I)I

    move-result v0

    add-int/lit8 v0, v0, 0x1

    iput v0, p0, Lmiui/widget/DatePicker;->rV:I

    .line 439
    invoke-direct {p0}, Lmiui/widget/DatePicker;->bz()V

    goto :goto_0
.end method

.method static synthetic a(Lmiui/widget/DatePicker;III)V
    .locals 0

    .prologue
    .line 43
    invoke-direct {p0, p1, p2, p3}, Lmiui/widget/DatePicker;->d(III)V

    return-void
.end method

.method private a(Lmiui/widget/NumberPicker;II)V
    .locals 2

    .prologue
    .line 707
    add-int/lit8 v0, p2, -0x1

    if-ge p3, v0, :cond_0

    .line 708
    const/4 v0, 0x5

    move v1, v0

    .line 712
    :goto_0
    sget v0, Lcom/miui/internal/R$id;->numberpicker_input:I

    invoke-virtual {p1, v0}, Lmiui/widget/NumberPicker;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/TextView;

    .line 713
    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setImeOptions(I)V

    .line 714
    return-void

    .line 710
    :cond_0
    const/4 v0, 0x6

    move v1, v0

    goto :goto_0
.end method

.method private a(Ljava/lang/String;Lmiui/date/Calendar;)Z
    .locals 3

    .prologue
    .line 565
    :try_start_0
    iget-object v0, p0, Lmiui/widget/DatePicker;->rU:Ljava/text/DateFormat;

    invoke-virtual {v0, p1}, Ljava/text/DateFormat;->parse(Ljava/lang/String;)Ljava/util/Date;

    move-result-object v0

    invoke-virtual {v0}, Ljava/util/Date;->getTime()J

    move-result-wide v0

    invoke-virtual {p2, v0, v1}, Lmiui/date/Calendar;->setTimeInMillis(J)Lmiui/date/Calendar;
    :try_end_0
    .catch Ljava/text/ParseException; {:try_start_0 .. :try_end_0} :catch_0

    .line 566
    const/4 v0, 0x1

    .line 569
    :goto_0
    return v0

    .line 567
    :catch_0
    move-exception v0

    .line 568
    sget-object v0, Lmiui/widget/DatePicker;->LOG_TAG:Ljava/lang/String;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "Date: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, " not in format: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, "MM/dd/yyyy"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    .line 569
    const/4 v0, 0x0

    goto :goto_0
.end method

.method static synthetic b(Lmiui/widget/DatePicker;)Lmiui/date/Calendar;
    .locals 1

    .prologue
    .line 43
    iget-object v0, p0, Lmiui/widget/DatePicker;->rW:Lmiui/date/Calendar;

    return-object v0
.end method

.method private bA()V
    .locals 5

    .prologue
    .line 472
    iget-object v0, p0, Lmiui/widget/DatePicker;->rJ:Landroid/widget/LinearLayout;

    invoke-virtual {v0}, Landroid/widget/LinearLayout;->removeAllViews()V

    .line 474
    iget-object v0, p0, Lmiui/widget/DatePicker;->rP:[C

    if-nez v0, :cond_0

    .line 475
    invoke-virtual {p0}, Lmiui/widget/DatePicker;->getContext()Landroid/content/Context;

    move-result-object v0

    invoke-static {v0}, Landroid/text/format/DateFormat;->getDateFormatOrder(Landroid/content/Context;)[C

    move-result-object v0

    .line 479
    :goto_0
    array-length v2, v0

    .line 480
    const/4 v1, 0x0

    :goto_1
    if-ge v1, v2, :cond_1

    .line 481
    aget-char v3, v0, v1

    sparse-switch v3, :sswitch_data_0

    .line 495
    new-instance v0, Ljava/lang/IllegalArgumentException;

    invoke-direct {v0}, Ljava/lang/IllegalArgumentException;-><init>()V

    throw v0

    .line 477
    :cond_0
    iget-object v0, p0, Lmiui/widget/DatePicker;->rP:[C

    goto :goto_0

    .line 483
    :sswitch_0
    iget-object v3, p0, Lmiui/widget/DatePicker;->rJ:Landroid/widget/LinearLayout;

    iget-object v4, p0, Lmiui/widget/DatePicker;->rK:Lmiui/widget/NumberPicker;

    invoke-virtual {v3, v4}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    .line 484
    iget-object v3, p0, Lmiui/widget/DatePicker;->rK:Lmiui/widget/NumberPicker;

    invoke-direct {p0, v3, v2, v1}, Lmiui/widget/DatePicker;->a(Lmiui/widget/NumberPicker;II)V

    .line 480
    :goto_2
    add-int/lit8 v1, v1, 0x1

    goto :goto_1

    .line 487
    :sswitch_1
    iget-object v3, p0, Lmiui/widget/DatePicker;->rJ:Landroid/widget/LinearLayout;

    iget-object v4, p0, Lmiui/widget/DatePicker;->rL:Lmiui/widget/NumberPicker;

    invoke-virtual {v3, v4}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    .line 488
    iget-object v3, p0, Lmiui/widget/DatePicker;->rL:Lmiui/widget/NumberPicker;

    invoke-direct {p0, v3, v2, v1}, Lmiui/widget/DatePicker;->a(Lmiui/widget/NumberPicker;II)V

    goto :goto_2

    .line 491
    :sswitch_2
    iget-object v3, p0, Lmiui/widget/DatePicker;->rJ:Landroid/widget/LinearLayout;

    iget-object v4, p0, Lmiui/widget/DatePicker;->rM:Lmiui/widget/NumberPicker;

    invoke-virtual {v3, v4}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    .line 492
    iget-object v3, p0, Lmiui/widget/DatePicker;->rM:Lmiui/widget/NumberPicker;

    invoke-direct {p0, v3, v2, v1}, Lmiui/widget/DatePicker;->a(Lmiui/widget/NumberPicker;II)V

    goto :goto_2

    .line 498
    :cond_1
    return-void

    .line 481
    nop

    :sswitch_data_0
    .sparse-switch
        0x4d -> :sswitch_1
        0x64 -> :sswitch_0
        0x79 -> :sswitch_2
    .end sparse-switch
.end method

.method private bB()V
    .locals 9

    .prologue
    const/4 v4, 0x5

    const/4 v8, 0x0

    const/4 v3, 0x6

    const/4 v5, 0x0

    const/4 v1, 0x1

    .line 589
    iget-boolean v0, p0, Lmiui/widget/DatePicker;->sb:Z

    if-eqz v0, :cond_4

    .line 590
    iget-object v0, p0, Lmiui/widget/DatePicker;->rK:Lmiui/widget/NumberPicker;

    invoke-virtual {v0, v8}, Lmiui/widget/NumberPicker;->setLabel(Ljava/lang/String;)V

    .line 591
    iget-object v0, p0, Lmiui/widget/DatePicker;->rL:Lmiui/widget/NumberPicker;

    invoke-virtual {v0, v8}, Lmiui/widget/NumberPicker;->setLabel(Ljava/lang/String;)V

    .line 592
    iget-object v0, p0, Lmiui/widget/DatePicker;->rM:Lmiui/widget/NumberPicker;

    invoke-virtual {v0, v8}, Lmiui/widget/NumberPicker;->setLabel(Ljava/lang/String;)V

    .line 598
    :goto_0
    iget-object v0, p0, Lmiui/widget/DatePicker;->rK:Lmiui/widget/NumberPicker;

    invoke-virtual {v0, v8}, Lmiui/widget/NumberPicker;->setDisplayedValues([Ljava/lang/String;)V

    .line 599
    iget-object v0, p0, Lmiui/widget/DatePicker;->rK:Lmiui/widget/NumberPicker;

    invoke-virtual {v0, v1}, Lmiui/widget/NumberPicker;->setMinValue(I)V

    .line 600
    iget-object v2, p0, Lmiui/widget/DatePicker;->rK:Lmiui/widget/NumberPicker;

    iget-boolean v0, p0, Lmiui/widget/DatePicker;->sb:Z

    if-eqz v0, :cond_5

    iget-object v0, p0, Lmiui/widget/DatePicker;->rZ:Lmiui/date/Calendar;

    const/16 v6, 0xa

    invoke-virtual {v0, v6}, Lmiui/date/Calendar;->getActualMaximum(I)I

    move-result v0

    :goto_1
    invoke-virtual {v2, v0}, Lmiui/widget/NumberPicker;->setMaxValue(I)V

    .line 602
    iget-object v0, p0, Lmiui/widget/DatePicker;->rK:Lmiui/widget/NumberPicker;

    invoke-virtual {v0, v1}, Lmiui/widget/NumberPicker;->setWrapSelectorWheel(Z)V

    .line 603
    iget-object v0, p0, Lmiui/widget/DatePicker;->rL:Lmiui/widget/NumberPicker;

    invoke-virtual {v0, v8}, Lmiui/widget/NumberPicker;->setDisplayedValues([Ljava/lang/String;)V

    .line 604
    iget-object v0, p0, Lmiui/widget/DatePicker;->rL:Lmiui/widget/NumberPicker;

    invoke-virtual {v0, v5}, Lmiui/widget/NumberPicker;->setMinValue(I)V

    .line 605
    iget-object v2, p0, Lmiui/widget/DatePicker;->rL:Lmiui/widget/NumberPicker;

    iget-boolean v0, p0, Lmiui/widget/DatePicker;->sb:Z

    if-eqz v0, :cond_7

    iget-object v0, p0, Lmiui/widget/DatePicker;->rZ:Lmiui/date/Calendar;

    invoke-virtual {v0}, Lmiui/date/Calendar;->getChineseLeapMonth()I

    move-result v0

    if-ltz v0, :cond_6

    const/16 v0, 0xc

    :goto_2
    invoke-virtual {v2, v0}, Lmiui/widget/NumberPicker;->setMaxValue(I)V

    .line 606
    iget-object v0, p0, Lmiui/widget/DatePicker;->rL:Lmiui/widget/NumberPicker;

    invoke-virtual {v0, v1}, Lmiui/widget/NumberPicker;->setWrapSelectorWheel(Z)V

    .line 609
    iget-boolean v0, p0, Lmiui/widget/DatePicker;->sb:Z

    if-eqz v0, :cond_8

    const/4 v0, 0x2

    .line 610
    :goto_3
    iget-object v2, p0, Lmiui/widget/DatePicker;->rZ:Lmiui/date/Calendar;

    invoke-virtual {v2, v0}, Lmiui/date/Calendar;->get(I)I

    move-result v2

    iget-object v6, p0, Lmiui/widget/DatePicker;->rX:Lmiui/date/Calendar;

    invoke-virtual {v6, v0}, Lmiui/date/Calendar;->get(I)I

    move-result v6

    if-ne v2, v6, :cond_0

    .line 611
    iget-object v6, p0, Lmiui/widget/DatePicker;->rL:Lmiui/widget/NumberPicker;

    iget-boolean v2, p0, Lmiui/widget/DatePicker;->sb:Z

    if-eqz v2, :cond_9

    iget-object v2, p0, Lmiui/widget/DatePicker;->rX:Lmiui/date/Calendar;

    invoke-virtual {v2, v3}, Lmiui/date/Calendar;->get(I)I

    move-result v2

    :goto_4
    invoke-virtual {v6, v2}, Lmiui/widget/NumberPicker;->setMinValue(I)V

    .line 612
    iget-object v2, p0, Lmiui/widget/DatePicker;->rL:Lmiui/widget/NumberPicker;

    invoke-virtual {v2, v5}, Lmiui/widget/NumberPicker;->setWrapSelectorWheel(Z)V

    .line 614
    iget-boolean v2, p0, Lmiui/widget/DatePicker;->sb:Z

    if-eqz v2, :cond_a

    move v2, v3

    .line 615
    :goto_5
    iget-object v6, p0, Lmiui/widget/DatePicker;->rZ:Lmiui/date/Calendar;

    invoke-virtual {v6, v2}, Lmiui/date/Calendar;->get(I)I

    move-result v6

    iget-object v7, p0, Lmiui/widget/DatePicker;->rX:Lmiui/date/Calendar;

    invoke-virtual {v7, v2}, Lmiui/date/Calendar;->get(I)I

    move-result v2

    if-ne v6, v2, :cond_0

    .line 616
    iget-object v6, p0, Lmiui/widget/DatePicker;->rK:Lmiui/widget/NumberPicker;

    iget-boolean v2, p0, Lmiui/widget/DatePicker;->sb:Z

    if-eqz v2, :cond_b

    iget-object v2, p0, Lmiui/widget/DatePicker;->rX:Lmiui/date/Calendar;

    const/16 v7, 0xa

    invoke-virtual {v2, v7}, Lmiui/date/Calendar;->get(I)I

    move-result v2

    :goto_6
    invoke-virtual {v6, v2}, Lmiui/widget/NumberPicker;->setMinValue(I)V

    .line 618
    iget-object v2, p0, Lmiui/widget/DatePicker;->rK:Lmiui/widget/NumberPicker;

    invoke-virtual {v2, v5}, Lmiui/widget/NumberPicker;->setWrapSelectorWheel(Z)V

    .line 622
    :cond_0
    iget-object v2, p0, Lmiui/widget/DatePicker;->rZ:Lmiui/date/Calendar;

    invoke-virtual {v2, v0}, Lmiui/date/Calendar;->get(I)I

    move-result v2

    iget-object v6, p0, Lmiui/widget/DatePicker;->rY:Lmiui/date/Calendar;

    invoke-virtual {v6, v0}, Lmiui/date/Calendar;->get(I)I

    move-result v0

    if-ne v2, v0, :cond_1

    .line 623
    iget-object v2, p0, Lmiui/widget/DatePicker;->rL:Lmiui/widget/NumberPicker;

    iget-boolean v0, p0, Lmiui/widget/DatePicker;->sb:Z

    if-eqz v0, :cond_c

    iget-object v0, p0, Lmiui/widget/DatePicker;->rX:Lmiui/date/Calendar;

    invoke-virtual {v0, v3}, Lmiui/date/Calendar;->get(I)I

    move-result v0

    :goto_7
    invoke-virtual {v2, v0}, Lmiui/widget/NumberPicker;->setMaxValue(I)V

    .line 624
    iget-object v0, p0, Lmiui/widget/DatePicker;->rL:Lmiui/widget/NumberPicker;

    invoke-virtual {v0, v5}, Lmiui/widget/NumberPicker;->setWrapSelectorWheel(Z)V

    .line 625
    iget-object v0, p0, Lmiui/widget/DatePicker;->rL:Lmiui/widget/NumberPicker;

    invoke-virtual {v0, v8}, Lmiui/widget/NumberPicker;->setDisplayedValues([Ljava/lang/String;)V

    .line 627
    iget-boolean v0, p0, Lmiui/widget/DatePicker;->sb:Z

    if-eqz v0, :cond_d

    move v0, v3

    .line 628
    :goto_8
    iget-object v2, p0, Lmiui/widget/DatePicker;->rZ:Lmiui/date/Calendar;

    invoke-virtual {v2, v0}, Lmiui/date/Calendar;->get(I)I

    move-result v2

    iget-object v6, p0, Lmiui/widget/DatePicker;->rY:Lmiui/date/Calendar;

    invoke-virtual {v6, v0}, Lmiui/date/Calendar;->get(I)I

    move-result v0

    if-ne v2, v0, :cond_1

    .line 629
    iget-object v2, p0, Lmiui/widget/DatePicker;->rK:Lmiui/widget/NumberPicker;

    iget-boolean v0, p0, Lmiui/widget/DatePicker;->sb:Z

    if-eqz v0, :cond_e

    iget-object v0, p0, Lmiui/widget/DatePicker;->rY:Lmiui/date/Calendar;

    const/16 v6, 0xa

    invoke-virtual {v0, v6}, Lmiui/date/Calendar;->get(I)I

    move-result v0

    :goto_9
    invoke-virtual {v2, v0}, Lmiui/widget/NumberPicker;->setMaxValue(I)V

    .line 631
    iget-object v0, p0, Lmiui/widget/DatePicker;->rK:Lmiui/widget/NumberPicker;

    invoke-virtual {v0, v5}, Lmiui/widget/NumberPicker;->setWrapSelectorWheel(Z)V

    .line 637
    :cond_1
    iget-object v0, p0, Lmiui/widget/DatePicker;->rO:[Ljava/lang/String;

    iget-object v2, p0, Lmiui/widget/DatePicker;->rL:Lmiui/widget/NumberPicker;

    invoke-virtual {v2}, Lmiui/widget/NumberPicker;->getMinValue()I

    move-result v2

    iget-object v6, p0, Lmiui/widget/DatePicker;->rO:[Ljava/lang/String;

    array-length v6, v6

    invoke-static {v0, v2, v6}, Ljava/util/Arrays;->copyOfRange([Ljava/lang/Object;II)[Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Ljava/lang/String;

    .line 639
    iget-object v2, p0, Lmiui/widget/DatePicker;->rL:Lmiui/widget/NumberPicker;

    invoke-virtual {v2, v0}, Lmiui/widget/NumberPicker;->setDisplayedValues([Ljava/lang/String;)V

    .line 640
    iget-boolean v0, p0, Lmiui/widget/DatePicker;->sb:Z

    if-eqz v0, :cond_2

    .line 641
    sget-object v0, Lmiui/widget/DatePicker;->rQ:[Ljava/lang/String;

    iget-object v2, p0, Lmiui/widget/DatePicker;->rK:Lmiui/widget/NumberPicker;

    invoke-virtual {v2}, Lmiui/widget/NumberPicker;->getMinValue()I

    move-result v2

    add-int/lit8 v2, v2, -0x1

    sget-object v6, Lmiui/widget/DatePicker;->rQ:[Ljava/lang/String;

    array-length v6, v6

    invoke-static {v0, v2, v6}, Ljava/util/Arrays;->copyOfRange([Ljava/lang/Object;II)[Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Ljava/lang/String;

    .line 642
    iget-object v2, p0, Lmiui/widget/DatePicker;->rK:Lmiui/widget/NumberPicker;

    invoke-virtual {v2, v0}, Lmiui/widget/NumberPicker;->setDisplayedValues([Ljava/lang/String;)V

    .line 646
    :cond_2
    iget-object v0, p0, Lmiui/widget/DatePicker;->rM:Lmiui/widget/NumberPicker;

    iget-object v2, p0, Lmiui/widget/DatePicker;->rX:Lmiui/date/Calendar;

    invoke-virtual {v2, v1}, Lmiui/date/Calendar;->get(I)I

    move-result v2

    invoke-virtual {v0, v2}, Lmiui/widget/NumberPicker;->setMinValue(I)V

    .line 647
    iget-object v0, p0, Lmiui/widget/DatePicker;->rM:Lmiui/widget/NumberPicker;

    iget-object v2, p0, Lmiui/widget/DatePicker;->rY:Lmiui/date/Calendar;

    invoke-virtual {v2, v1}, Lmiui/date/Calendar;->get(I)I

    move-result v2

    invoke-virtual {v0, v2}, Lmiui/widget/NumberPicker;->setMaxValue(I)V

    .line 648
    iget-object v0, p0, Lmiui/widget/DatePicker;->rM:Lmiui/widget/NumberPicker;

    invoke-virtual {v0, v5}, Lmiui/widget/NumberPicker;->setWrapSelectorWheel(Z)V

    .line 651
    iget-object v0, p0, Lmiui/widget/DatePicker;->rZ:Lmiui/date/Calendar;

    invoke-virtual {v0}, Lmiui/date/Calendar;->getChineseLeapMonth()I

    move-result v0

    .line 652
    if-ltz v0, :cond_f

    iget-object v2, p0, Lmiui/widget/DatePicker;->rZ:Lmiui/date/Calendar;

    invoke-virtual {v2}, Lmiui/date/Calendar;->isChineseLeapMonth()Z

    move-result v2

    if-nez v2, :cond_3

    iget-object v2, p0, Lmiui/widget/DatePicker;->rZ:Lmiui/date/Calendar;

    invoke-virtual {v2, v3}, Lmiui/date/Calendar;->get(I)I

    move-result v2

    if-le v2, v0, :cond_f

    :cond_3
    move v0, v1

    .line 653
    :goto_a
    iget-object v2, p0, Lmiui/widget/DatePicker;->rM:Lmiui/widget/NumberPicker;

    iget-boolean v5, p0, Lmiui/widget/DatePicker;->sb:Z

    if-eqz v5, :cond_10

    iget-object v1, p0, Lmiui/widget/DatePicker;->rZ:Lmiui/date/Calendar;

    const/4 v5, 0x2

    invoke-virtual {v1, v5}, Lmiui/date/Calendar;->get(I)I

    move-result v1

    :goto_b
    invoke-virtual {v2, v1}, Lmiui/widget/NumberPicker;->setValue(I)V

    .line 654
    iget-object v1, p0, Lmiui/widget/DatePicker;->rL:Lmiui/widget/NumberPicker;

    iget-boolean v2, p0, Lmiui/widget/DatePicker;->sb:Z

    if-eqz v2, :cond_12

    if-eqz v0, :cond_11

    iget-object v0, p0, Lmiui/widget/DatePicker;->rZ:Lmiui/date/Calendar;

    invoke-virtual {v0, v3}, Lmiui/date/Calendar;->get(I)I

    move-result v0

    add-int/lit8 v0, v0, 0x1

    :goto_c
    invoke-virtual {v1, v0}, Lmiui/widget/NumberPicker;->setValue(I)V

    .line 656
    iget-object v1, p0, Lmiui/widget/DatePicker;->rK:Lmiui/widget/NumberPicker;

    iget-boolean v0, p0, Lmiui/widget/DatePicker;->sb:Z

    if-eqz v0, :cond_13

    iget-object v0, p0, Lmiui/widget/DatePicker;->rZ:Lmiui/date/Calendar;

    const/16 v2, 0xa

    invoke-virtual {v0, v2}, Lmiui/date/Calendar;->get(I)I

    move-result v0

    :goto_d
    invoke-virtual {v1, v0}, Lmiui/widget/NumberPicker;->setValue(I)V

    .line 658
    return-void

    .line 594
    :cond_4
    iget-object v0, p0, Lmiui/widget/DatePicker;->rK:Lmiui/widget/NumberPicker;

    invoke-virtual {p0}, Lmiui/widget/DatePicker;->getContext()Landroid/content/Context;

    move-result-object v2

    sget v6, Lcom/miui/internal/R$string;->date_picker_label_day:I

    invoke-virtual {v2, v6}, Landroid/content/Context;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v0, v2}, Lmiui/widget/NumberPicker;->setLabel(Ljava/lang/String;)V

    .line 595
    iget-object v0, p0, Lmiui/widget/DatePicker;->rL:Lmiui/widget/NumberPicker;

    invoke-virtual {p0}, Lmiui/widget/DatePicker;->getContext()Landroid/content/Context;

    move-result-object v2

    sget v6, Lcom/miui/internal/R$string;->date_picker_label_month:I

    invoke-virtual {v2, v6}, Landroid/content/Context;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v0, v2}, Lmiui/widget/NumberPicker;->setLabel(Ljava/lang/String;)V

    .line 596
    iget-object v0, p0, Lmiui/widget/DatePicker;->rM:Lmiui/widget/NumberPicker;

    invoke-virtual {p0}, Lmiui/widget/DatePicker;->getContext()Landroid/content/Context;

    move-result-object v2

    sget v6, Lcom/miui/internal/R$string;->date_picker_label_year:I

    invoke-virtual {v2, v6}, Landroid/content/Context;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v0, v2}, Lmiui/widget/NumberPicker;->setLabel(Ljava/lang/String;)V

    goto/16 :goto_0

    .line 600
    :cond_5
    iget-object v0, p0, Lmiui/widget/DatePicker;->rZ:Lmiui/date/Calendar;

    const/16 v6, 0x9

    invoke-virtual {v0, v6}, Lmiui/date/Calendar;->getActualMaximum(I)I

    move-result v0

    goto/16 :goto_1

    .line 605
    :cond_6
    const/16 v0, 0xb

    goto/16 :goto_2

    :cond_7
    const/16 v0, 0xb

    goto/16 :goto_2

    :cond_8
    move v0, v1

    .line 609
    goto/16 :goto_3

    .line 611
    :cond_9
    iget-object v2, p0, Lmiui/widget/DatePicker;->rX:Lmiui/date/Calendar;

    invoke-virtual {v2, v4}, Lmiui/date/Calendar;->get(I)I

    move-result v2

    goto/16 :goto_4

    :cond_a
    move v2, v4

    .line 614
    goto/16 :goto_5

    .line 616
    :cond_b
    iget-object v2, p0, Lmiui/widget/DatePicker;->rX:Lmiui/date/Calendar;

    const/16 v7, 0x9

    invoke-virtual {v2, v7}, Lmiui/date/Calendar;->get(I)I

    move-result v2

    goto/16 :goto_6

    .line 623
    :cond_c
    iget-object v0, p0, Lmiui/widget/DatePicker;->rY:Lmiui/date/Calendar;

    invoke-virtual {v0, v4}, Lmiui/date/Calendar;->get(I)I

    move-result v0

    goto/16 :goto_7

    :cond_d
    move v0, v4

    .line 627
    goto/16 :goto_8

    .line 629
    :cond_e
    iget-object v0, p0, Lmiui/widget/DatePicker;->rY:Lmiui/date/Calendar;

    const/16 v6, 0x9

    invoke-virtual {v0, v6}, Lmiui/date/Calendar;->get(I)I

    move-result v0

    goto/16 :goto_9

    :cond_f
    move v0, v5

    .line 652
    goto/16 :goto_a

    .line 653
    :cond_10
    iget-object v5, p0, Lmiui/widget/DatePicker;->rZ:Lmiui/date/Calendar;

    invoke-virtual {v5, v1}, Lmiui/date/Calendar;->get(I)I

    move-result v1

    goto/16 :goto_b

    .line 654
    :cond_11
    iget-object v0, p0, Lmiui/widget/DatePicker;->rZ:Lmiui/date/Calendar;

    invoke-virtual {v0, v3}, Lmiui/date/Calendar;->get(I)I

    move-result v0

    goto/16 :goto_c

    :cond_12
    iget-object v0, p0, Lmiui/widget/DatePicker;->rZ:Lmiui/date/Calendar;

    invoke-virtual {v0, v4}, Lmiui/date/Calendar;->get(I)I

    move-result v0

    goto/16 :goto_c

    .line 656
    :cond_13
    iget-object v0, p0, Lmiui/widget/DatePicker;->rZ:Lmiui/date/Calendar;

    const/16 v2, 0x9

    invoke-virtual {v0, v2}, Lmiui/date/Calendar;->get(I)I

    move-result v0

    goto/16 :goto_d
.end method

.method private bC()V
    .locals 6

    .prologue
    .line 692
    const/4 v0, 0x4

    invoke-virtual {p0, v0}, Lmiui/widget/DatePicker;->sendAccessibilityEvent(I)V

    .line 693
    iget-object v0, p0, Lmiui/widget/DatePicker;->fe:Lmiui/widget/DatePicker$OnDateChangedListener;

    if-eqz v0, :cond_0

    .line 694
    iget-object v0, p0, Lmiui/widget/DatePicker;->fe:Lmiui/widget/DatePicker$OnDateChangedListener;

    invoke-virtual {p0}, Lmiui/widget/DatePicker;->getYear()I

    move-result v2

    invoke-virtual {p0}, Lmiui/widget/DatePicker;->getMonth()I

    move-result v3

    invoke-virtual {p0}, Lmiui/widget/DatePicker;->getDayOfMonth()I

    move-result v4

    iget-boolean v5, p0, Lmiui/widget/DatePicker;->sb:Z

    move-object v1, p0

    invoke-interface/range {v0 .. v5}, Lmiui/widget/DatePicker$OnDateChangedListener;->onDateChanged(Lmiui/widget/DatePicker;IIIZ)V

    .line 696
    :cond_0
    return-void
.end method

.method private bz()V
    .locals 6

    .prologue
    const/4 v1, 0x0

    .line 443
    iget-boolean v0, p0, Lmiui/widget/DatePicker;->sb:Z

    if-eqz v0, :cond_2

    .line 444
    iget-object v0, p0, Lmiui/widget/DatePicker;->rZ:Lmiui/date/Calendar;

    invoke-virtual {v0}, Lmiui/date/Calendar;->getChineseLeapMonth()I

    move-result v0

    .line 445
    if-gez v0, :cond_1

    .line 446
    sget-object v0, Lmiui/widget/DatePicker;->rR:[Ljava/lang/String;

    iput-object v0, p0, Lmiui/widget/DatePicker;->rO:[Ljava/lang/String;

    .line 464
    :cond_0
    :goto_0
    return-void

    .line 448
    :cond_1
    sget-object v2, Lmiui/widget/DatePicker;->rS:[Ljava/lang/String;

    iput-object v2, p0, Lmiui/widget/DatePicker;->rO:[Ljava/lang/String;

    .line 449
    sget-object v2, Lmiui/widget/DatePicker;->rR:[Ljava/lang/String;

    iget-object v3, p0, Lmiui/widget/DatePicker;->rO:[Ljava/lang/String;

    add-int/lit8 v4, v0, 0x1

    invoke-static {v2, v1, v3, v1, v4}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    .line 450
    sget-object v1, Lmiui/widget/DatePicker;->rR:[Ljava/lang/String;

    iget-object v2, p0, Lmiui/widget/DatePicker;->rO:[Ljava/lang/String;

    add-int/lit8 v3, v0, 0x1

    sget-object v4, Lmiui/widget/DatePicker;->rR:[Ljava/lang/String;

    array-length v4, v4

    sub-int/2addr v4, v0

    invoke-static {v1, v0, v2, v3, v4}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    .line 452
    iget-object v1, p0, Lmiui/widget/DatePicker;->rO:[Ljava/lang/String;

    add-int/lit8 v2, v0, 0x1

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    sget-object v4, Lmiui/widget/DatePicker;->rT:Ljava/lang/String;

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    iget-object v4, p0, Lmiui/widget/DatePicker;->rO:[Ljava/lang/String;

    add-int/lit8 v0, v0, 0x1

    aget-object v0, v4, v0

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    aput-object v0, v1, v2

    goto :goto_0

    .line 455
    :cond_2
    const-string v0, "en"

    iget-object v2, p0, Lmiui/widget/DatePicker;->rN:Ljava/util/Locale;

    invoke-virtual {v2}, Ljava/util/Locale;->getLanguage()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/String;->toLowerCase()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v0, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_3

    .line 456
    invoke-static {}, Lmiui/date/CalendarFormatSymbols;->getDefault()Lmiui/date/CalendarFormatSymbols;

    move-result-object v0

    invoke-virtual {v0}, Lmiui/date/CalendarFormatSymbols;->getShortMonths()[Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lmiui/widget/DatePicker;->rO:[Ljava/lang/String;

    goto :goto_0

    .line 458
    :cond_3
    const/16 v0, 0xc

    new-array v0, v0, [Ljava/lang/String;

    iput-object v0, p0, Lmiui/widget/DatePicker;->rO:[Ljava/lang/String;

    move v0, v1

    .line 459
    :goto_1
    iget-object v2, p0, Lmiui/widget/DatePicker;->rO:[Ljava/lang/String;

    array-length v2, v2

    if-ge v0, v2, :cond_0

    .line 460
    iget-object v2, p0, Lmiui/widget/DatePicker;->rO:[Ljava/lang/String;

    const-string v3, "%02d"

    const/4 v4, 0x1

    new-array v4, v4, [Ljava/lang/Object;

    add-int/lit8 v5, v0, 0x1

    invoke-static {v5}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v5

    aput-object v5, v4, v1

    invoke-static {v3, v4}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v3

    aput-object v3, v2, v0

    .line 459
    add-int/lit8 v0, v0, 0x1

    goto :goto_1
.end method

.method static synthetic c(Lmiui/widget/DatePicker;)Lmiui/widget/NumberPicker;
    .locals 1

    .prologue
    .line 43
    iget-object v0, p0, Lmiui/widget/DatePicker;->rK:Lmiui/widget/NumberPicker;

    return-object v0
.end method

.method private c(III)Z
    .locals 3

    .prologue
    const/4 v0, 0x1

    .line 574
    iget-object v1, p0, Lmiui/widget/DatePicker;->rZ:Lmiui/date/Calendar;

    invoke-virtual {v1, v0}, Lmiui/date/Calendar;->get(I)I

    move-result v1

    if-ne v1, p1, :cond_0

    iget-object v1, p0, Lmiui/widget/DatePicker;->rZ:Lmiui/date/Calendar;

    const/4 v2, 0x5

    invoke-virtual {v1, v2}, Lmiui/date/Calendar;->get(I)I

    move-result v1

    if-ne v1, p3, :cond_0

    iget-object v1, p0, Lmiui/widget/DatePicker;->rZ:Lmiui/date/Calendar;

    const/16 v2, 0x9

    invoke-virtual {v1, v2}, Lmiui/date/Calendar;->get(I)I

    move-result v1

    if-eq v1, p2, :cond_1

    :cond_0
    :goto_0
    return v0

    :cond_1
    const/4 v0, 0x0

    goto :goto_0
.end method

.method private d(III)V
    .locals 8

    .prologue
    const/4 v4, 0x0

    .line 580
    iget-object v0, p0, Lmiui/widget/DatePicker;->rZ:Lmiui/date/Calendar;

    move v1, p1

    move v2, p2

    move v3, p3

    move v5, v4

    move v6, v4

    move v7, v4

    invoke-virtual/range {v0 .. v7}, Lmiui/date/Calendar;->set(IIIIIII)Lmiui/date/Calendar;

    .line 581
    iget-object v0, p0, Lmiui/widget/DatePicker;->rZ:Lmiui/date/Calendar;

    iget-object v1, p0, Lmiui/widget/DatePicker;->rX:Lmiui/date/Calendar;

    invoke-virtual {v0, v1}, Lmiui/date/Calendar;->before(Lmiui/date/Calendar;)Z

    move-result v0

    if-eqz v0, :cond_1

    .line 582
    iget-object v0, p0, Lmiui/widget/DatePicker;->rZ:Lmiui/date/Calendar;

    iget-object v1, p0, Lmiui/widget/DatePicker;->rX:Lmiui/date/Calendar;

    invoke-virtual {v1}, Lmiui/date/Calendar;->getTimeInMillis()J

    move-result-wide v1

    invoke-virtual {v0, v1, v2}, Lmiui/date/Calendar;->setTimeInMillis(J)Lmiui/date/Calendar;

    .line 586
    :cond_0
    :goto_0
    return-void

    .line 583
    :cond_1
    iget-object v0, p0, Lmiui/widget/DatePicker;->rZ:Lmiui/date/Calendar;

    iget-object v1, p0, Lmiui/widget/DatePicker;->rY:Lmiui/date/Calendar;

    invoke-virtual {v0, v1}, Lmiui/date/Calendar;->after(Lmiui/date/Calendar;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 584
    iget-object v0, p0, Lmiui/widget/DatePicker;->rZ:Lmiui/date/Calendar;

    iget-object v1, p0, Lmiui/widget/DatePicker;->rY:Lmiui/date/Calendar;

    invoke-virtual {v1}, Lmiui/date/Calendar;->getTimeInMillis()J

    move-result-wide v1

    invoke-virtual {v0, v1, v2}, Lmiui/date/Calendar;->setTimeInMillis(J)Lmiui/date/Calendar;

    goto :goto_0
.end method

.method static synthetic d(Lmiui/widget/DatePicker;)Z
    .locals 1

    .prologue
    .line 43
    iget-boolean v0, p0, Lmiui/widget/DatePicker;->sb:Z

    return v0
.end method

.method static synthetic e(Lmiui/widget/DatePicker;)Lmiui/widget/NumberPicker;
    .locals 1

    .prologue
    .line 43
    iget-object v0, p0, Lmiui/widget/DatePicker;->rL:Lmiui/widget/NumberPicker;

    return-object v0
.end method

.method static synthetic f(Lmiui/widget/DatePicker;)Lmiui/widget/NumberPicker;
    .locals 1

    .prologue
    .line 43
    iget-object v0, p0, Lmiui/widget/DatePicker;->rM:Lmiui/widget/NumberPicker;

    return-object v0
.end method

.method static synthetic g(Lmiui/widget/DatePicker;)V
    .locals 0

    .prologue
    .line 43
    invoke-direct {p0}, Lmiui/widget/DatePicker;->bz()V

    return-void
.end method

.method static synthetic h(Lmiui/widget/DatePicker;)V
    .locals 0

    .prologue
    .line 43
    invoke-direct {p0}, Lmiui/widget/DatePicker;->bB()V

    return-void
.end method

.method static synthetic i(Lmiui/widget/DatePicker;)V
    .locals 0

    .prologue
    .line 43
    invoke-direct {p0}, Lmiui/widget/DatePicker;->bC()V

    return-void
.end method


# virtual methods
.method public dispatchPopulateAccessibilityEvent(Landroid/view/accessibility/AccessibilityEvent;)Z
    .locals 1

    .prologue
    .line 341
    invoke-virtual {p0, p1}, Lmiui/widget/DatePicker;->onPopulateAccessibilityEvent(Landroid/view/accessibility/AccessibilityEvent;)V

    .line 342
    const/4 v0, 0x1

    return v0
.end method

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
    .line 519
    invoke-virtual {p0, p1}, Lmiui/widget/DatePicker;->dispatchThawSelfOnly(Landroid/util/SparseArray;)V

    .line 520
    return-void
.end method

.method public getDayOfMonth()I
    .locals 2

    .prologue
    .line 678
    iget-object v1, p0, Lmiui/widget/DatePicker;->rZ:Lmiui/date/Calendar;

    iget-boolean v0, p0, Lmiui/widget/DatePicker;->sb:Z

    if-eqz v0, :cond_0

    const/16 v0, 0xa

    :goto_0
    invoke-virtual {v1, v0}, Lmiui/date/Calendar;->get(I)I

    move-result v0

    return v0

    :cond_0
    const/16 v0, 0x9

    goto :goto_0
.end method

.method public getMaxDate()J
    .locals 2

    .prologue
    .line 299
    iget-object v0, p0, Lmiui/widget/DatePicker;->rY:Lmiui/date/Calendar;

    invoke-virtual {v0}, Lmiui/date/Calendar;->getTimeInMillis()J

    move-result-wide v0

    return-wide v0
.end method

.method public getMinDate()J
    .locals 2

    .prologue
    .line 265
    iget-object v0, p0, Lmiui/widget/DatePicker;->rX:Lmiui/date/Calendar;

    invoke-virtual {v0}, Lmiui/date/Calendar;->getTimeInMillis()J

    move-result-wide v0

    return-wide v0
.end method

.method public getMonth()I
    .locals 2

    .prologue
    .line 671
    iget-object v1, p0, Lmiui/widget/DatePicker;->rZ:Lmiui/date/Calendar;

    iget-boolean v0, p0, Lmiui/widget/DatePicker;->sb:Z

    if-eqz v0, :cond_0

    const/4 v0, 0x6

    :goto_0
    invoke-virtual {v1, v0}, Lmiui/date/Calendar;->get(I)I

    move-result v0

    return v0

    :cond_0
    const/4 v0, 0x5

    goto :goto_0
.end method

.method public getSpinnersShown()Z
    .locals 1

    .prologue
    .line 378
    iget-object v0, p0, Lmiui/widget/DatePicker;->rJ:Landroid/widget/LinearLayout;

    invoke-virtual {v0}, Landroid/widget/LinearLayout;->isShown()Z

    move-result v0

    return v0
.end method

.method public getYear()I
    .locals 2

    .prologue
    .line 664
    iget-object v1, p0, Lmiui/widget/DatePicker;->rZ:Lmiui/date/Calendar;

    iget-boolean v0, p0, Lmiui/widget/DatePicker;->sb:Z

    if-eqz v0, :cond_0

    const/4 v0, 0x2

    :goto_0
    invoke-virtual {v1, v0}, Lmiui/date/Calendar;->get(I)I

    move-result v0

    return v0

    :cond_0
    const/4 v0, 0x1

    goto :goto_0
.end method

.method public init(IIILmiui/widget/DatePicker$OnDateChangedListener;)V
    .locals 0

    .prologue
    .line 552
    invoke-direct {p0, p1, p2, p3}, Lmiui/widget/DatePicker;->d(III)V

    .line 553
    invoke-direct {p0}, Lmiui/widget/DatePicker;->bB()V

    .line 554
    iput-object p4, p0, Lmiui/widget/DatePicker;->fe:Lmiui/widget/DatePicker$OnDateChangedListener;

    .line 555
    return-void
.end method

.method public isEnabled()Z
    .locals 1

    .prologue
    .line 336
    iget-boolean v0, p0, Lmiui/widget/DatePicker;->sa:Z

    return v0
.end method

.method public isLunarMode()Z
    .locals 1

    .prologue
    .line 685
    iget-boolean v0, p0, Lmiui/widget/DatePicker;->sb:Z

    return v0
.end method

.method protected onConfigurationChanged(Landroid/content/res/Configuration;)V
    .locals 1

    .prologue
    .line 368
    invoke-super {p0, p1}, Landroid/widget/FrameLayout;->onConfigurationChanged(Landroid/content/res/Configuration;)V

    .line 369
    iget-object v0, p1, Landroid/content/res/Configuration;->locale:Ljava/util/Locale;

    invoke-direct {p0, v0}, Lmiui/widget/DatePicker;->a(Ljava/util/Locale;)V

    .line 370
    return-void
.end method

.method public onInitializeAccessibilityEvent(Landroid/view/accessibility/AccessibilityEvent;)V
    .locals 1

    .prologue
    .line 356
    invoke-super {p0, p1}, Landroid/widget/FrameLayout;->onInitializeAccessibilityEvent(Landroid/view/accessibility/AccessibilityEvent;)V

    .line 357
    const-class v0, Lmiui/widget/DatePicker;

    invoke-virtual {v0}, Ljava/lang/Class;->getName()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p1, v0}, Landroid/view/accessibility/AccessibilityEvent;->setClassName(Ljava/lang/CharSequence;)V

    .line 358
    return-void
.end method

.method public onInitializeAccessibilityNodeInfo(Landroid/view/accessibility/AccessibilityNodeInfo;)V
    .locals 1

    .prologue
    .line 362
    invoke-super {p0, p1}, Landroid/widget/FrameLayout;->onInitializeAccessibilityNodeInfo(Landroid/view/accessibility/AccessibilityNodeInfo;)V

    .line 363
    const-class v0, Lmiui/widget/DatePicker;

    invoke-virtual {v0}, Ljava/lang/Class;->getName()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p1, v0}, Landroid/view/accessibility/AccessibilityNodeInfo;->setClassName(Ljava/lang/CharSequence;)V

    .line 364
    return-void
.end method

.method public onPopulateAccessibilityEvent(Landroid/view/accessibility/AccessibilityEvent;)V
    .locals 3

    .prologue
    .line 347
    invoke-super {p0, p1}, Landroid/widget/FrameLayout;->onPopulateAccessibilityEvent(Landroid/view/accessibility/AccessibilityEvent;)V

    .line 350
    iget-object v0, p0, Lmiui/widget/DatePicker;->rZ:Lmiui/date/Calendar;

    invoke-virtual {v0}, Lmiui/date/Calendar;->getTimeInMillis()J

    move-result-wide v0

    const/16 v2, 0x380

    invoke-static {v0, v1, v2}, Lmiui/date/DateUtils;->formatDateTime(JI)Ljava/lang/String;

    move-result-object v0

    .line 351
    invoke-virtual {p1}, Landroid/view/accessibility/AccessibilityEvent;->getText()Ljava/util/List;

    move-result-object v1

    invoke-interface {v1, v0}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 352
    return-void
.end method

.method protected onRestoreInstanceState(Landroid/os/Parcelable;)V
    .locals 3

    .prologue
    .line 533
    check-cast p1, Lmiui/widget/DatePicker$a;

    .line 534
    invoke-virtual {p1}, Lmiui/widget/DatePicker$a;->getSuperState()Landroid/os/Parcelable;

    move-result-object v0

    invoke-super {p0, v0}, Landroid/widget/FrameLayout;->onRestoreInstanceState(Landroid/os/Parcelable;)V

    .line 535
    invoke-static {p1}, Lmiui/widget/DatePicker$a;->a(Lmiui/widget/DatePicker$a;)I

    move-result v0

    invoke-static {p1}, Lmiui/widget/DatePicker$a;->b(Lmiui/widget/DatePicker$a;)I

    move-result v1

    invoke-static {p1}, Lmiui/widget/DatePicker$a;->c(Lmiui/widget/DatePicker$a;)I

    move-result v2

    invoke-direct {p0, v0, v1, v2}, Lmiui/widget/DatePicker;->d(III)V

    .line 536
    invoke-static {p1}, Lmiui/widget/DatePicker$a;->d(Lmiui/widget/DatePicker$a;)Z

    move-result v0

    iput-boolean v0, p0, Lmiui/widget/DatePicker;->sb:Z

    .line 537
    invoke-direct {p0}, Lmiui/widget/DatePicker;->bB()V

    .line 538
    return-void
.end method

.method protected onSaveInstanceState()Landroid/os/Parcelable;
    .locals 7

    .prologue
    .line 524
    invoke-super {p0}, Landroid/widget/FrameLayout;->onSaveInstanceState()Landroid/os/Parcelable;

    move-result-object v1

    .line 525
    iget-object v0, p0, Lmiui/widget/DatePicker;->rZ:Lmiui/date/Calendar;

    const/4 v2, 0x1

    invoke-virtual {v0, v2}, Lmiui/date/Calendar;->get(I)I

    move-result v2

    .line 526
    iget-object v0, p0, Lmiui/widget/DatePicker;->rZ:Lmiui/date/Calendar;

    const/4 v3, 0x5

    invoke-virtual {v0, v3}, Lmiui/date/Calendar;->get(I)I

    move-result v3

    .line 527
    iget-object v0, p0, Lmiui/widget/DatePicker;->rZ:Lmiui/date/Calendar;

    const/16 v4, 0x9

    invoke-virtual {v0, v4}, Lmiui/date/Calendar;->get(I)I

    move-result v4

    .line 528
    new-instance v0, Lmiui/widget/DatePicker$a;

    iget-boolean v5, p0, Lmiui/widget/DatePicker;->sb:Z

    const/4 v6, 0x0

    invoke-direct/range {v0 .. v6}, Lmiui/widget/DatePicker$a;-><init>(Landroid/os/Parcelable;IIIZLmiui/widget/DatePicker$1;)V

    return-object v0
.end method

.method public setDateFormatOrder([C)V
    .locals 0

    .prologue
    .line 723
    iput-object p1, p0, Lmiui/widget/DatePicker;->rP:[C

    .line 724
    invoke-direct {p0}, Lmiui/widget/DatePicker;->bA()V

    .line 725
    return-void
.end method

.method public setEnabled(Z)V
    .locals 1

    .prologue
    .line 324
    iget-boolean v0, p0, Lmiui/widget/DatePicker;->sa:Z

    if-ne v0, p1, :cond_0

    .line 332
    :goto_0
    return-void

    .line 327
    :cond_0
    invoke-super {p0, p1}, Landroid/widget/FrameLayout;->setEnabled(Z)V

    .line 328
    iget-object v0, p0, Lmiui/widget/DatePicker;->rK:Lmiui/widget/NumberPicker;

    invoke-virtual {v0, p1}, Lmiui/widget/NumberPicker;->setEnabled(Z)V

    .line 329
    iget-object v0, p0, Lmiui/widget/DatePicker;->rL:Lmiui/widget/NumberPicker;

    invoke-virtual {v0, p1}, Lmiui/widget/NumberPicker;->setEnabled(Z)V

    .line 330
    iget-object v0, p0, Lmiui/widget/DatePicker;->rM:Lmiui/widget/NumberPicker;

    invoke-virtual {v0, p1}, Lmiui/widget/NumberPicker;->setEnabled(Z)V

    .line 331
    iput-boolean p1, p0, Lmiui/widget/DatePicker;->sa:Z

    goto :goto_0
.end method

.method public setLunarMode(Z)V
    .locals 1

    .prologue
    .line 395
    iget-boolean v0, p0, Lmiui/widget/DatePicker;->sb:Z

    if-eq p1, v0, :cond_0

    .line 396
    iput-boolean p1, p0, Lmiui/widget/DatePicker;->sb:Z

    .line 397
    invoke-direct {p0}, Lmiui/widget/DatePicker;->bz()V

    .line 398
    invoke-direct {p0}, Lmiui/widget/DatePicker;->bB()V

    .line 400
    :cond_0
    return-void
.end method

.method public setMaxDate(J)V
    .locals 4

    .prologue
    const/16 v3, 0xc

    const/4 v2, 0x1

    .line 310
    iget-object v0, p0, Lmiui/widget/DatePicker;->rW:Lmiui/date/Calendar;

    invoke-virtual {v0, p1, p2}, Lmiui/date/Calendar;->setTimeInMillis(J)Lmiui/date/Calendar;

    .line 311
    iget-object v0, p0, Lmiui/widget/DatePicker;->rW:Lmiui/date/Calendar;

    invoke-virtual {v0, v2}, Lmiui/date/Calendar;->get(I)I

    move-result v0

    iget-object v1, p0, Lmiui/widget/DatePicker;->rY:Lmiui/date/Calendar;

    invoke-virtual {v1, v2}, Lmiui/date/Calendar;->get(I)I

    move-result v1

    if-ne v0, v1, :cond_0

    iget-object v0, p0, Lmiui/widget/DatePicker;->rW:Lmiui/date/Calendar;

    invoke-virtual {v0, v3}, Lmiui/date/Calendar;->get(I)I

    move-result v0

    iget-object v1, p0, Lmiui/widget/DatePicker;->rY:Lmiui/date/Calendar;

    invoke-virtual {v1, v3}, Lmiui/date/Calendar;->get(I)I

    move-result v1

    if-eq v0, v1, :cond_0

    .line 320
    :goto_0
    return-void

    .line 315
    :cond_0
    iget-object v0, p0, Lmiui/widget/DatePicker;->rY:Lmiui/date/Calendar;

    invoke-virtual {v0, p1, p2}, Lmiui/date/Calendar;->setTimeInMillis(J)Lmiui/date/Calendar;

    .line 316
    iget-object v0, p0, Lmiui/widget/DatePicker;->rZ:Lmiui/date/Calendar;

    iget-object v1, p0, Lmiui/widget/DatePicker;->rY:Lmiui/date/Calendar;

    invoke-virtual {v0, v1}, Lmiui/date/Calendar;->after(Lmiui/date/Calendar;)Z

    move-result v0

    if-eqz v0, :cond_1

    .line 317
    iget-object v0, p0, Lmiui/widget/DatePicker;->rZ:Lmiui/date/Calendar;

    iget-object v1, p0, Lmiui/widget/DatePicker;->rY:Lmiui/date/Calendar;

    invoke-virtual {v1}, Lmiui/date/Calendar;->getTimeInMillis()J

    move-result-wide v1

    invoke-virtual {v0, v1, v2}, Lmiui/date/Calendar;->setTimeInMillis(J)Lmiui/date/Calendar;

    .line 319
    :cond_1
    invoke-direct {p0}, Lmiui/widget/DatePicker;->bB()V

    goto :goto_0
.end method

.method public setMinDate(J)V
    .locals 4

    .prologue
    const/16 v3, 0xc

    const/4 v2, 0x1

    .line 276
    iget-object v0, p0, Lmiui/widget/DatePicker;->rW:Lmiui/date/Calendar;

    invoke-virtual {v0, p1, p2}, Lmiui/date/Calendar;->setTimeInMillis(J)Lmiui/date/Calendar;

    .line 277
    iget-object v0, p0, Lmiui/widget/DatePicker;->rW:Lmiui/date/Calendar;

    invoke-virtual {v0, v2}, Lmiui/date/Calendar;->get(I)I

    move-result v0

    iget-object v1, p0, Lmiui/widget/DatePicker;->rX:Lmiui/date/Calendar;

    invoke-virtual {v1, v2}, Lmiui/date/Calendar;->get(I)I

    move-result v1

    if-ne v0, v1, :cond_0

    iget-object v0, p0, Lmiui/widget/DatePicker;->rW:Lmiui/date/Calendar;

    invoke-virtual {v0, v3}, Lmiui/date/Calendar;->get(I)I

    move-result v0

    iget-object v1, p0, Lmiui/widget/DatePicker;->rX:Lmiui/date/Calendar;

    invoke-virtual {v1, v3}, Lmiui/date/Calendar;->get(I)I

    move-result v1

    if-eq v0, v1, :cond_0

    .line 286
    :goto_0
    return-void

    .line 281
    :cond_0
    iget-object v0, p0, Lmiui/widget/DatePicker;->rX:Lmiui/date/Calendar;

    invoke-virtual {v0, p1, p2}, Lmiui/date/Calendar;->setTimeInMillis(J)Lmiui/date/Calendar;

    .line 282
    iget-object v0, p0, Lmiui/widget/DatePicker;->rZ:Lmiui/date/Calendar;

    iget-object v1, p0, Lmiui/widget/DatePicker;->rX:Lmiui/date/Calendar;

    invoke-virtual {v0, v1}, Lmiui/date/Calendar;->before(Lmiui/date/Calendar;)Z

    move-result v0

    if-eqz v0, :cond_1

    .line 283
    iget-object v0, p0, Lmiui/widget/DatePicker;->rZ:Lmiui/date/Calendar;

    iget-object v1, p0, Lmiui/widget/DatePicker;->rX:Lmiui/date/Calendar;

    invoke-virtual {v1}, Lmiui/date/Calendar;->getTimeInMillis()J

    move-result-wide v1

    invoke-virtual {v0, v1, v2}, Lmiui/date/Calendar;->setTimeInMillis(J)Lmiui/date/Calendar;

    .line 285
    :cond_1
    invoke-direct {p0}, Lmiui/widget/DatePicker;->bB()V

    goto :goto_0
.end method

.method public setSpinnersShown(Z)V
    .locals 2

    .prologue
    .line 387
    iget-object v1, p0, Lmiui/widget/DatePicker;->rJ:Landroid/widget/LinearLayout;

    if-eqz p1, :cond_0

    const/4 v0, 0x0

    :goto_0
    invoke-virtual {v1, v0}, Landroid/widget/LinearLayout;->setVisibility(I)V

    .line 388
    return-void

    .line 387
    :cond_0
    const/16 v0, 0x8

    goto :goto_0
.end method

.method public showDayPicker(Z)V
    .locals 2

    .prologue
    .line 423
    iget-object v1, p0, Lmiui/widget/DatePicker;->rK:Lmiui/widget/NumberPicker;

    if-eqz p1, :cond_0

    const/4 v0, 0x0

    :goto_0
    invoke-virtual {v1, v0}, Lmiui/widget/NumberPicker;->setVisibility(I)V

    .line 424
    return-void

    .line 423
    :cond_0
    const/16 v0, 0x8

    goto :goto_0
.end method

.method public showMonthPicker(Z)V
    .locals 2

    .prologue
    .line 415
    iget-object v1, p0, Lmiui/widget/DatePicker;->rL:Lmiui/widget/NumberPicker;

    if-eqz p1, :cond_0

    const/4 v0, 0x0

    :goto_0
    invoke-virtual {v1, v0}, Lmiui/widget/NumberPicker;->setVisibility(I)V

    .line 416
    return-void

    .line 415
    :cond_0
    const/16 v0, 0x8

    goto :goto_0
.end method

.method public showYearPicker(Z)V
    .locals 2

    .prologue
    .line 407
    iget-object v1, p0, Lmiui/widget/DatePicker;->rM:Lmiui/widget/NumberPicker;

    if-eqz p1, :cond_0

    const/4 v0, 0x0

    :goto_0
    invoke-virtual {v1, v0}, Lmiui/widget/NumberPicker;->setVisibility(I)V

    .line 408
    return-void

    .line 407
    :cond_0
    const/16 v0, 0x8

    goto :goto_0
.end method

.method public updateDate(III)V
    .locals 1

    .prologue
    .line 508
    invoke-direct {p0, p1, p2, p3}, Lmiui/widget/DatePicker;->c(III)Z

    move-result v0

    if-nez v0, :cond_0

    .line 514
    :goto_0
    return-void

    .line 511
    :cond_0
    invoke-direct {p0, p1, p2, p3}, Lmiui/widget/DatePicker;->d(III)V

    .line 512
    invoke-direct {p0}, Lmiui/widget/DatePicker;->bB()V

    .line 513
    invoke-direct {p0}, Lmiui/widget/DatePicker;->bC()V

    goto :goto_0
.end method
