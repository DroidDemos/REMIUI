.class public Lmiui/widget/TimePicker;
.super Landroid/widget/FrameLayout;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lmiui/widget/TimePicker$a;,
        Lmiui/widget/TimePicker$OnTimeChangedListener;
    }
.end annotation


# static fields
.field private static final GP:I = 0xc

.field private static final GS:Lmiui/widget/TimePicker$OnTimeChangedListener;

.field private static final rI:Z = true


# instance fields
.field private GV:Z

.field private final GX:Lmiui/widget/NumberPicker;

.field private final GZ:Lmiui/widget/NumberPicker;

.field private final Ha:Lmiui/widget/NumberPicker;

.field private final Hb:Landroid/widget/Button;

.field private Hc:Lmiui/widget/TimePicker$OnTimeChangedListener;

.field private Hd:Lmiui/date/Calendar;

.field private rN:Ljava/util/Locale;

.field private sa:Z

.field private wI:Z


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .prologue
    .line 41
    new-instance v0, Lmiui/widget/u;

    invoke-direct {v0}, Lmiui/widget/u;-><init>()V

    sput-object v0, Lmiui/widget/TimePicker;->GS:Lmiui/widget/TimePicker$OnTimeChangedListener;

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;)V
    .locals 1

    .prologue
    .line 87
    const/4 v0, 0x0

    invoke-direct {p0, p1, v0}, Lmiui/widget/TimePicker;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    .line 88
    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 1

    .prologue
    .line 91
    const/4 v0, 0x0

    invoke-direct {p0, p1, p2, v0}, Lmiui/widget/TimePicker;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    .line 92
    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V
    .locals 7

    .prologue
    const/4 v6, 0x0

    const/4 v5, 0x5

    const/4 v4, 0x1

    const/4 v3, 0x0

    .line 95
    invoke-direct {p0, p1, p2, p3}, Landroid/widget/FrameLayout;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    .line 64
    iput-boolean v4, p0, Lmiui/widget/TimePicker;->sa:Z

    .line 98
    invoke-static {}, Ljava/util/Locale;->getDefault()Ljava/util/Locale;

    move-result-object v0

    invoke-direct {p0, v0}, Lmiui/widget/TimePicker;->a(Ljava/util/Locale;)V

    .line 100
    sget v1, Lcom/miui/internal/R$layout;->time_picker:I

    .line 102
    const-string v0, "layout_inflater"

    invoke-virtual {p1, v0}, Landroid/content/Context;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/view/LayoutInflater;

    .line 104
    invoke-virtual {v0, v1, p0, v4}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;Z)Landroid/view/View;

    .line 107
    sget v0, Lcom/miui/internal/R$id;->hour:I

    invoke-virtual {p0, v0}, Lmiui/widget/TimePicker;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Lmiui/widget/NumberPicker;

    iput-object v0, p0, Lmiui/widget/TimePicker;->GX:Lmiui/widget/NumberPicker;

    .line 108
    iget-object v0, p0, Lmiui/widget/TimePicker;->GX:Lmiui/widget/NumberPicker;

    new-instance v1, Lmiui/widget/TimePicker$1;

    invoke-direct {v1, p0}, Lmiui/widget/TimePicker$1;-><init>(Lmiui/widget/TimePicker;)V

    invoke-virtual {v0, v1}, Lmiui/widget/NumberPicker;->setOnValueChangedListener(Lmiui/widget/NumberPicker$OnValueChangeListener;)V

    .line 120
    iget-object v0, p0, Lmiui/widget/TimePicker;->GX:Lmiui/widget/NumberPicker;

    sget v1, Lcom/miui/internal/R$id;->numberpicker_input:I

    invoke-virtual {v0, v1}, Lmiui/widget/NumberPicker;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/EditText;

    .line 121
    invoke-virtual {v0, v5}, Landroid/widget/EditText;->setImeOptions(I)V

    .line 124
    sget v0, Lcom/miui/internal/R$id;->minute:I

    invoke-virtual {p0, v0}, Lmiui/widget/TimePicker;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Lmiui/widget/NumberPicker;

    iput-object v0, p0, Lmiui/widget/TimePicker;->GZ:Lmiui/widget/NumberPicker;

    .line 125
    iget-object v0, p0, Lmiui/widget/TimePicker;->GZ:Lmiui/widget/NumberPicker;

    invoke-virtual {v0, v3}, Lmiui/widget/NumberPicker;->setMinValue(I)V

    .line 126
    iget-object v0, p0, Lmiui/widget/TimePicker;->GZ:Lmiui/widget/NumberPicker;

    const/16 v1, 0x3b

    invoke-virtual {v0, v1}, Lmiui/widget/NumberPicker;->setMaxValue(I)V

    .line 127
    iget-object v0, p0, Lmiui/widget/TimePicker;->GZ:Lmiui/widget/NumberPicker;

    const-wide/16 v1, 0x64

    invoke-virtual {v0, v1, v2}, Lmiui/widget/NumberPicker;->setOnLongPressUpdateInterval(J)V

    .line 128
    iget-object v0, p0, Lmiui/widget/TimePicker;->GZ:Lmiui/widget/NumberPicker;

    sget-object v1, Lmiui/widget/NumberPicker;->DS:Lmiui/widget/NumberPicker$Formatter;

    invoke-virtual {v0, v1}, Lmiui/widget/NumberPicker;->setFormatter(Lmiui/widget/NumberPicker$Formatter;)V

    .line 129
    iget-object v0, p0, Lmiui/widget/TimePicker;->GZ:Lmiui/widget/NumberPicker;

    new-instance v1, Lmiui/widget/TimePicker$2;

    invoke-direct {v1, p0}, Lmiui/widget/TimePicker$2;-><init>(Lmiui/widget/TimePicker;)V

    invoke-virtual {v0, v1}, Lmiui/widget/NumberPicker;->setOnValueChangedListener(Lmiui/widget/NumberPicker$OnValueChangeListener;)V

    .line 134
    iget-object v0, p0, Lmiui/widget/TimePicker;->GZ:Lmiui/widget/NumberPicker;

    sget v1, Lcom/miui/internal/R$id;->numberpicker_input:I

    invoke-virtual {v0, v1}, Lmiui/widget/NumberPicker;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/EditText;

    .line 135
    invoke-virtual {v0, v5}, Landroid/widget/EditText;->setImeOptions(I)V

    .line 138
    sget v0, Lcom/miui/internal/R$id;->amPm:I

    invoke-virtual {p0, v0}, Lmiui/widget/TimePicker;->findViewById(I)Landroid/view/View;

    move-result-object v1

    .line 139
    instance-of v0, v1, Landroid/widget/Button;

    if-eqz v0, :cond_3

    .line 140
    iput-object v6, p0, Lmiui/widget/TimePicker;->Ha:Lmiui/widget/NumberPicker;

    move-object v0, v1

    .line 141
    check-cast v0, Landroid/widget/Button;

    iput-object v0, p0, Lmiui/widget/TimePicker;->Hb:Landroid/widget/Button;

    .line 142
    iget-object v0, p0, Lmiui/widget/TimePicker;->Hb:Landroid/widget/Button;

    new-instance v2, Lmiui/widget/TimePicker$3;

    invoke-direct {v2, p0}, Lmiui/widget/TimePicker$3;-><init>(Lmiui/widget/TimePicker;)V

    invoke-virtual {v0, v2}, Landroid/widget/Button;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 168
    :goto_0
    invoke-direct {p0}, Lmiui/widget/TimePicker;->dD()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 170
    sget v0, Lcom/miui/internal/R$id;->timePickerLayout:I

    invoke-virtual {p0, v0}, Lmiui/widget/TimePicker;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/view/ViewGroup;

    .line 171
    invoke-virtual {v0, v1}, Landroid/view/ViewGroup;->removeView(Landroid/view/View;)V

    .line 172
    invoke-virtual {v0, v1, v3}, Landroid/view/ViewGroup;->addView(Landroid/view/View;I)V

    .line 176
    :cond_0
    invoke-direct {p0}, Lmiui/widget/TimePicker;->dE()V

    .line 177
    invoke-direct {p0}, Lmiui/widget/TimePicker;->dF()V

    .line 179
    sget-object v0, Lmiui/widget/TimePicker;->GS:Lmiui/widget/TimePicker$OnTimeChangedListener;

    invoke-virtual {p0, v0}, Lmiui/widget/TimePicker;->setOnTimeChangedListener(Lmiui/widget/TimePicker$OnTimeChangedListener;)V

    .line 182
    iget-object v0, p0, Lmiui/widget/TimePicker;->Hd:Lmiui/date/Calendar;

    const/16 v1, 0x12

    invoke-virtual {v0, v1}, Lmiui/date/Calendar;->get(I)I

    move-result v0

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    invoke-virtual {p0, v0}, Lmiui/widget/TimePicker;->setCurrentHour(Ljava/lang/Integer;)V

    .line 183
    iget-object v0, p0, Lmiui/widget/TimePicker;->Hd:Lmiui/date/Calendar;

    const/16 v1, 0x14

    invoke-virtual {v0, v1}, Lmiui/date/Calendar;->get(I)I

    move-result v0

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    invoke-virtual {p0, v0}, Lmiui/widget/TimePicker;->setCurrentMinute(Ljava/lang/Integer;)V

    .line 185
    invoke-virtual {p0}, Lmiui/widget/TimePicker;->isEnabled()Z

    move-result v0

    if-nez v0, :cond_1

    .line 186
    invoke-virtual {p0, v3}, Lmiui/widget/TimePicker;->setEnabled(Z)V

    .line 190
    :cond_1
    invoke-virtual {p0}, Lmiui/widget/TimePicker;->getImportantForAccessibility()I

    move-result v0

    if-nez v0, :cond_2

    .line 191
    invoke-virtual {p0, v4}, Lmiui/widget/TimePicker;->setImportantForAccessibility(I)V

    .line 193
    :cond_2
    return-void

    .line 151
    :cond_3
    iput-object v6, p0, Lmiui/widget/TimePicker;->Hb:Landroid/widget/Button;

    move-object v0, v1

    .line 152
    check-cast v0, Lmiui/widget/NumberPicker;

    iput-object v0, p0, Lmiui/widget/TimePicker;->Ha:Lmiui/widget/NumberPicker;

    .line 153
    iget-object v0, p0, Lmiui/widget/TimePicker;->Ha:Lmiui/widget/NumberPicker;

    invoke-virtual {v0, v3}, Lmiui/widget/NumberPicker;->setMinValue(I)V

    .line 154
    iget-object v0, p0, Lmiui/widget/TimePicker;->Ha:Lmiui/widget/NumberPicker;

    invoke-virtual {v0, v4}, Lmiui/widget/NumberPicker;->setMaxValue(I)V

    .line 155
    iget-object v0, p0, Lmiui/widget/TimePicker;->Ha:Lmiui/widget/NumberPicker;

    invoke-static {}, Lmiui/date/CalendarFormatSymbols;->getDefault()Lmiui/date/CalendarFormatSymbols;

    move-result-object v2

    invoke-virtual {v2}, Lmiui/date/CalendarFormatSymbols;->getAmPms()[Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v0, v2}, Lmiui/widget/NumberPicker;->setDisplayedValues([Ljava/lang/String;)V

    .line 156
    iget-object v0, p0, Lmiui/widget/TimePicker;->Ha:Lmiui/widget/NumberPicker;

    new-instance v2, Lmiui/widget/TimePicker$4;

    invoke-direct {v2, p0}, Lmiui/widget/TimePicker$4;-><init>(Lmiui/widget/TimePicker;)V

    invoke-virtual {v0, v2}, Lmiui/widget/NumberPicker;->setOnValueChangedListener(Lmiui/widget/NumberPicker$OnValueChangeListener;)V

    .line 164
    iget-object v0, p0, Lmiui/widget/TimePicker;->Ha:Lmiui/widget/NumberPicker;

    sget v2, Lcom/miui/internal/R$id;->numberpicker_input:I

    invoke-virtual {v0, v2}, Lmiui/widget/NumberPicker;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/EditText;

    .line 165
    const/4 v2, 0x6

    invoke-virtual {v0, v2}, Landroid/widget/EditText;->setImeOptions(I)V

    goto/16 :goto_0
.end method

.method private a(Ljava/util/Locale;)V
    .locals 1

    .prologue
    .line 232
    iget-object v0, p0, Lmiui/widget/TimePicker;->rN:Ljava/util/Locale;

    invoke-virtual {p1, v0}, Ljava/util/Locale;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_1

    .line 239
    :cond_0
    :goto_0
    return-void

    .line 235
    :cond_1
    iput-object p1, p0, Lmiui/widget/TimePicker;->rN:Ljava/util/Locale;

    .line 236
    iget-object v0, p0, Lmiui/widget/TimePicker;->Hd:Lmiui/date/Calendar;

    if-nez v0, :cond_0

    .line 237
    new-instance v0, Lmiui/date/Calendar;

    invoke-direct {v0}, Lmiui/date/Calendar;-><init>()V

    iput-object v0, p0, Lmiui/widget/TimePicker;->Hd:Lmiui/date/Calendar;

    goto :goto_0
.end method

.method static synthetic a(Lmiui/widget/TimePicker;)Z
    .locals 1

    .prologue
    .line 31
    iget-boolean v0, p0, Lmiui/widget/TimePicker;->GV:Z

    return v0
.end method

.method static synthetic a(Lmiui/widget/TimePicker;Z)Z
    .locals 0

    .prologue
    .line 31
    iput-boolean p1, p0, Lmiui/widget/TimePicker;->GV:Z

    return p1
.end method

.method static synthetic b(Lmiui/widget/TimePicker;)V
    .locals 0

    .prologue
    .line 31
    invoke-direct {p0}, Lmiui/widget/TimePicker;->dF()V

    return-void
.end method

.method private c(Landroid/view/View;II)V
    .locals 2

    .prologue
    .line 475
    invoke-virtual {p1, p2}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    .line 476
    if-eqz v0, :cond_0

    .line 477
    invoke-virtual {p0}, Lmiui/widget/TimePicker;->getContext()Landroid/content/Context;

    move-result-object v1

    invoke-virtual {v1, p3}, Landroid/content/Context;->getString(I)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Landroid/view/View;->setContentDescription(Ljava/lang/CharSequence;)V

    .line 479
    :cond_0
    return-void
.end method

.method static synthetic c(Lmiui/widget/TimePicker;)V
    .locals 0

    .prologue
    .line 31
    invoke-direct {p0}, Lmiui/widget/TimePicker;->dG()V

    return-void
.end method

.method private dD()Z
    .locals 2

    .prologue
    .line 196
    invoke-virtual {p0}, Lmiui/widget/TimePicker;->getContext()Landroid/content/Context;

    move-result-object v0

    sget v1, Lcom/miui/internal/R$string;->fmt_time_12hour_pm:I

    invoke-virtual {v0, v1}, Landroid/content/Context;->getString(I)Ljava/lang/String;

    move-result-object v0

    const-string v1, "a"

    invoke-virtual {v0, v1}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v0

    return v0
.end method

.method private dE()V
    .locals 2

    .prologue
    .line 436
    invoke-virtual {p0}, Lmiui/widget/TimePicker;->is24HourView()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 437
    iget-object v0, p0, Lmiui/widget/TimePicker;->GX:Lmiui/widget/NumberPicker;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Lmiui/widget/NumberPicker;->setMinValue(I)V

    .line 438
    iget-object v0, p0, Lmiui/widget/TimePicker;->GX:Lmiui/widget/NumberPicker;

    const/16 v1, 0x17

    invoke-virtual {v0, v1}, Lmiui/widget/NumberPicker;->setMaxValue(I)V

    .line 439
    iget-object v0, p0, Lmiui/widget/TimePicker;->GX:Lmiui/widget/NumberPicker;

    sget-object v1, Lmiui/widget/NumberPicker;->DS:Lmiui/widget/NumberPicker$Formatter;

    invoke-virtual {v0, v1}, Lmiui/widget/NumberPicker;->setFormatter(Lmiui/widget/NumberPicker$Formatter;)V

    .line 445
    :goto_0
    return-void

    .line 441
    :cond_0
    iget-object v0, p0, Lmiui/widget/TimePicker;->GX:Lmiui/widget/NumberPicker;

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Lmiui/widget/NumberPicker;->setMinValue(I)V

    .line 442
    iget-object v0, p0, Lmiui/widget/TimePicker;->GX:Lmiui/widget/NumberPicker;

    const/16 v1, 0xc

    invoke-virtual {v0, v1}, Lmiui/widget/NumberPicker;->setMaxValue(I)V

    .line 443
    iget-object v0, p0, Lmiui/widget/TimePicker;->GX:Lmiui/widget/NumberPicker;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Lmiui/widget/NumberPicker;->setFormatter(Lmiui/widget/NumberPicker$Formatter;)V

    goto :goto_0
.end method

.method private dF()V
    .locals 4

    .prologue
    const/16 v2, 0x8

    const/4 v1, 0x0

    .line 448
    invoke-virtual {p0}, Lmiui/widget/TimePicker;->is24HourView()Z

    move-result v0

    if-eqz v0, :cond_1

    .line 449
    iget-object v0, p0, Lmiui/widget/TimePicker;->Ha:Lmiui/widget/NumberPicker;

    if-eqz v0, :cond_0

    .line 450
    iget-object v0, p0, Lmiui/widget/TimePicker;->Ha:Lmiui/widget/NumberPicker;

    invoke-virtual {v0, v2}, Lmiui/widget/NumberPicker;->setVisibility(I)V

    .line 464
    :goto_0
    const/4 v0, 0x4

    invoke-virtual {p0, v0}, Lmiui/widget/TimePicker;->sendAccessibilityEvent(I)V

    .line 465
    return-void

    .line 452
    :cond_0
    iget-object v0, p0, Lmiui/widget/TimePicker;->Hb:Landroid/widget/Button;

    invoke-virtual {v0, v2}, Landroid/widget/Button;->setVisibility(I)V

    goto :goto_0

    .line 455
    :cond_1
    iget-boolean v0, p0, Lmiui/widget/TimePicker;->GV:Z

    if-eqz v0, :cond_2

    move v0, v1

    .line 456
    :goto_1
    iget-object v2, p0, Lmiui/widget/TimePicker;->Ha:Lmiui/widget/NumberPicker;

    if-eqz v2, :cond_3

    .line 457
    iget-object v2, p0, Lmiui/widget/TimePicker;->Ha:Lmiui/widget/NumberPicker;

    invoke-virtual {v2, v0}, Lmiui/widget/NumberPicker;->setValue(I)V

    .line 458
    iget-object v0, p0, Lmiui/widget/TimePicker;->Ha:Lmiui/widget/NumberPicker;

    invoke-virtual {v0, v1}, Lmiui/widget/NumberPicker;->setVisibility(I)V

    goto :goto_0

    .line 455
    :cond_2
    const/4 v0, 0x1

    goto :goto_1

    .line 460
    :cond_3
    iget-object v2, p0, Lmiui/widget/TimePicker;->Hb:Landroid/widget/Button;

    invoke-static {}, Lmiui/date/CalendarFormatSymbols;->getDefault()Lmiui/date/CalendarFormatSymbols;

    move-result-object v3

    invoke-virtual {v3}, Lmiui/date/CalendarFormatSymbols;->getAmPms()[Ljava/lang/String;

    move-result-object v3

    aget-object v0, v3, v0

    invoke-virtual {v2, v0}, Landroid/widget/Button;->setText(Ljava/lang/CharSequence;)V

    .line 461
    iget-object v0, p0, Lmiui/widget/TimePicker;->Hb:Landroid/widget/Button;

    invoke-virtual {v0, v1}, Landroid/widget/Button;->setVisibility(I)V

    goto :goto_0
.end method

.method private dG()V
    .locals 3

    .prologue
    .line 468
    const/4 v0, 0x4

    invoke-virtual {p0, v0}, Lmiui/widget/TimePicker;->sendAccessibilityEvent(I)V

    .line 469
    iget-object v0, p0, Lmiui/widget/TimePicker;->Hc:Lmiui/widget/TimePicker$OnTimeChangedListener;

    if-eqz v0, :cond_0

    .line 470
    iget-object v0, p0, Lmiui/widget/TimePicker;->Hc:Lmiui/widget/TimePicker$OnTimeChangedListener;

    invoke-virtual {p0}, Lmiui/widget/TimePicker;->getCurrentHour()Ljava/lang/Integer;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/Integer;->intValue()I

    move-result v1

    invoke-virtual {p0}, Lmiui/widget/TimePicker;->getCurrentMinute()Ljava/lang/Integer;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/Integer;->intValue()I

    move-result v2

    invoke-interface {v0, p0, v1, v2}, Lmiui/widget/TimePicker$OnTimeChangedListener;->onTimeChanged(Lmiui/widget/TimePicker;II)V

    .line 472
    :cond_0
    return-void
.end method


# virtual methods
.method public dispatchPopulateAccessibilityEvent(Landroid/view/accessibility/AccessibilityEvent;)Z
    .locals 1

    .prologue
    .line 403
    invoke-virtual {p0, p1}, Lmiui/widget/TimePicker;->onPopulateAccessibilityEvent(Landroid/view/accessibility/AccessibilityEvent;)V

    .line 404
    const/4 v0, 0x1

    return v0
.end method

.method public getBaseline()I
    .locals 1

    .prologue
    .line 398
    iget-object v0, p0, Lmiui/widget/TimePicker;->GX:Lmiui/widget/NumberPicker;

    invoke-virtual {v0}, Lmiui/widget/NumberPicker;->getBaseline()I

    move-result v0

    return v0
.end method

.method public getCurrentHour()Ljava/lang/Integer;
    .locals 2

    .prologue
    .line 316
    iget-object v0, p0, Lmiui/widget/TimePicker;->GX:Lmiui/widget/NumberPicker;

    invoke-virtual {v0}, Lmiui/widget/NumberPicker;->getValue()I

    move-result v0

    .line 317
    invoke-virtual {p0}, Lmiui/widget/TimePicker;->is24HourView()Z

    move-result v1

    if-eqz v1, :cond_0

    .line 318
    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    .line 322
    :goto_0
    return-object v0

    .line 319
    :cond_0
    iget-boolean v1, p0, Lmiui/widget/TimePicker;->GV:Z

    if-eqz v1, :cond_1

    .line 320
    rem-int/lit8 v0, v0, 0xc

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    goto :goto_0

    .line 322
    :cond_1
    rem-int/lit8 v0, v0, 0xc

    add-int/lit8 v0, v0, 0xc

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    goto :goto_0
.end method

.method public getCurrentMinute()Ljava/lang/Integer;
    .locals 1

    .prologue
    .line 382
    iget-object v0, p0, Lmiui/widget/TimePicker;->GZ:Lmiui/widget/NumberPicker;

    invoke-virtual {v0}, Lmiui/widget/NumberPicker;->getValue()I

    move-result v0

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    return-object v0
.end method

.method public is24HourView()Z
    .locals 1

    .prologue
    .line 375
    iget-boolean v0, p0, Lmiui/widget/TimePicker;->wI:Z

    return v0
.end method

.method public isEnabled()Z
    .locals 1

    .prologue
    .line 217
    iget-boolean v0, p0, Lmiui/widget/TimePicker;->sa:Z

    return v0
.end method

.method protected onConfigurationChanged(Landroid/content/res/Configuration;)V
    .locals 1

    .prologue
    .line 222
    invoke-super {p0, p1}, Landroid/widget/FrameLayout;->onConfigurationChanged(Landroid/content/res/Configuration;)V

    .line 223
    iget-object v0, p1, Landroid/content/res/Configuration;->locale:Ljava/util/Locale;

    invoke-direct {p0, v0}, Lmiui/widget/TimePicker;->a(Ljava/util/Locale;)V

    .line 224
    return-void
.end method

.method public onInitializeAccessibilityEvent(Landroid/view/accessibility/AccessibilityEvent;)V
    .locals 1

    .prologue
    .line 425
    invoke-super {p0, p1}, Landroid/widget/FrameLayout;->onInitializeAccessibilityEvent(Landroid/view/accessibility/AccessibilityEvent;)V

    .line 426
    const-class v0, Lmiui/widget/TimePicker;

    invoke-virtual {v0}, Ljava/lang/Class;->getName()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p1, v0}, Landroid/view/accessibility/AccessibilityEvent;->setClassName(Ljava/lang/CharSequence;)V

    .line 427
    return-void
.end method

.method public onInitializeAccessibilityNodeInfo(Landroid/view/accessibility/AccessibilityNodeInfo;)V
    .locals 1

    .prologue
    .line 431
    invoke-super {p0, p1}, Landroid/widget/FrameLayout;->onInitializeAccessibilityNodeInfo(Landroid/view/accessibility/AccessibilityNodeInfo;)V

    .line 432
    const-class v0, Lmiui/widget/TimePicker;

    invoke-virtual {v0}, Ljava/lang/Class;->getName()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p1, v0}, Landroid/view/accessibility/AccessibilityNodeInfo;->setClassName(Ljava/lang/CharSequence;)V

    .line 433
    return-void
.end method

.method public onPopulateAccessibilityEvent(Landroid/view/accessibility/AccessibilityEvent;)V
    .locals 4

    .prologue
    .line 409
    invoke-super {p0, p1}, Landroid/widget/FrameLayout;->onPopulateAccessibilityEvent(Landroid/view/accessibility/AccessibilityEvent;)V

    .line 412
    iget-boolean v0, p0, Lmiui/widget/TimePicker;->wI:Z

    if-eqz v0, :cond_0

    .line 413
    const/16 v0, 0x2c

    .line 417
    :goto_0
    iget-object v1, p0, Lmiui/widget/TimePicker;->Hd:Lmiui/date/Calendar;

    const/16 v2, 0x12

    invoke-virtual {p0}, Lmiui/widget/TimePicker;->getCurrentHour()Ljava/lang/Integer;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/Integer;->intValue()I

    move-result v3

    invoke-virtual {v1, v2, v3}, Lmiui/date/Calendar;->set(II)Lmiui/date/Calendar;

    .line 418
    iget-object v1, p0, Lmiui/widget/TimePicker;->Hd:Lmiui/date/Calendar;

    const/16 v2, 0x14

    invoke-virtual {p0}, Lmiui/widget/TimePicker;->getCurrentMinute()Ljava/lang/Integer;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/Integer;->intValue()I

    move-result v3

    invoke-virtual {v1, v2, v3}, Lmiui/date/Calendar;->set(II)Lmiui/date/Calendar;

    .line 419
    iget-object v1, p0, Lmiui/widget/TimePicker;->Hd:Lmiui/date/Calendar;

    invoke-virtual {v1}, Lmiui/date/Calendar;->getTimeInMillis()J

    move-result-wide v1

    invoke-static {v1, v2, v0}, Lmiui/date/DateUtils;->formatDateTime(JI)Ljava/lang/String;

    move-result-object v0

    .line 420
    invoke-virtual {p1}, Landroid/view/accessibility/AccessibilityEvent;->getText()Ljava/util/List;

    move-result-object v1

    invoke-interface {v1, v0}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 421
    return-void

    .line 415
    :cond_0
    const/16 v0, 0x1c

    goto :goto_0
.end method

.method protected onRestoreInstanceState(Landroid/os/Parcelable;)V
    .locals 1

    .prologue
    .line 297
    check-cast p1, Lmiui/widget/TimePicker$a;

    .line 298
    invoke-virtual {p1}, Lmiui/widget/TimePicker$a;->getSuperState()Landroid/os/Parcelable;

    move-result-object v0

    invoke-super {p0, v0}, Landroid/widget/FrameLayout;->onRestoreInstanceState(Landroid/os/Parcelable;)V

    .line 299
    invoke-virtual {p1}, Lmiui/widget/TimePicker$a;->getHour()I

    move-result v0

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    invoke-virtual {p0, v0}, Lmiui/widget/TimePicker;->setCurrentHour(Ljava/lang/Integer;)V

    .line 300
    invoke-virtual {p1}, Lmiui/widget/TimePicker$a;->getMinute()I

    move-result v0

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    invoke-virtual {p0, v0}, Lmiui/widget/TimePicker;->setCurrentMinute(Ljava/lang/Integer;)V

    .line 301
    return-void
.end method

.method protected onSaveInstanceState()Landroid/os/Parcelable;
    .locals 5

    .prologue
    .line 291
    invoke-super {p0}, Landroid/widget/FrameLayout;->onSaveInstanceState()Landroid/os/Parcelable;

    move-result-object v0

    .line 292
    new-instance v1, Lmiui/widget/TimePicker$a;

    invoke-virtual {p0}, Lmiui/widget/TimePicker;->getCurrentHour()Ljava/lang/Integer;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/Integer;->intValue()I

    move-result v2

    invoke-virtual {p0}, Lmiui/widget/TimePicker;->getCurrentMinute()Ljava/lang/Integer;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/Integer;->intValue()I

    move-result v3

    const/4 v4, 0x0

    invoke-direct {v1, v0, v2, v3, v4}, Lmiui/widget/TimePicker$a;-><init>(Landroid/os/Parcelable;IILmiui/widget/u;)V

    return-object v1
.end method

.method public setCurrentHour(Ljava/lang/Integer;)V
    .locals 2

    .prologue
    const/16 v1, 0xc

    .line 331
    if-eqz p1, :cond_0

    invoke-virtual {p0}, Lmiui/widget/TimePicker;->getCurrentHour()Ljava/lang/Integer;

    move-result-object v0

    invoke-virtual {p1, v0}, Ljava/lang/Integer;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_1

    .line 351
    :cond_0
    :goto_0
    return-void

    .line 334
    :cond_1
    invoke-virtual {p0}, Lmiui/widget/TimePicker;->is24HourView()Z

    move-result v0

    if-nez v0, :cond_3

    .line 336
    invoke-virtual {p1}, Ljava/lang/Integer;->intValue()I

    move-result v0

    if-lt v0, v1, :cond_4

    .line 337
    const/4 v0, 0x0

    iput-boolean v0, p0, Lmiui/widget/TimePicker;->GV:Z

    .line 338
    invoke-virtual {p1}, Ljava/lang/Integer;->intValue()I

    move-result v0

    if-le v0, v1, :cond_2

    .line 339
    invoke-virtual {p1}, Ljava/lang/Integer;->intValue()I

    move-result v0

    add-int/lit8 v0, v0, -0xc

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p1

    .line 347
    :cond_2
    :goto_1
    invoke-direct {p0}, Lmiui/widget/TimePicker;->dF()V

    .line 349
    :cond_3
    iget-object v0, p0, Lmiui/widget/TimePicker;->GX:Lmiui/widget/NumberPicker;

    invoke-virtual {p1}, Ljava/lang/Integer;->intValue()I

    move-result v1

    invoke-virtual {v0, v1}, Lmiui/widget/NumberPicker;->setValue(I)V

    .line 350
    invoke-direct {p0}, Lmiui/widget/TimePicker;->dG()V

    goto :goto_0

    .line 342
    :cond_4
    const/4 v0, 0x1

    iput-boolean v0, p0, Lmiui/widget/TimePicker;->GV:Z

    .line 343
    invoke-virtual {p1}, Ljava/lang/Integer;->intValue()I

    move-result v0

    if-nez v0, :cond_2

    .line 344
    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p1

    goto :goto_1
.end method

.method public setCurrentMinute(Ljava/lang/Integer;)V
    .locals 2

    .prologue
    .line 389
    invoke-virtual {p0}, Lmiui/widget/TimePicker;->getCurrentMinute()Ljava/lang/Integer;

    move-result-object v0

    invoke-virtual {p1, v0}, Ljava/lang/Integer;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 394
    :goto_0
    return-void

    .line 392
    :cond_0
    iget-object v0, p0, Lmiui/widget/TimePicker;->GZ:Lmiui/widget/NumberPicker;

    invoke-virtual {p1}, Ljava/lang/Integer;->intValue()I

    move-result v1

    invoke-virtual {v0, v1}, Lmiui/widget/NumberPicker;->setValue(I)V

    .line 393
    invoke-direct {p0}, Lmiui/widget/TimePicker;->dG()V

    goto :goto_0
.end method

.method public setEnabled(Z)V
    .locals 1

    .prologue
    .line 201
    iget-boolean v0, p0, Lmiui/widget/TimePicker;->sa:Z

    if-ne v0, p1, :cond_0

    .line 213
    :goto_0
    return-void

    .line 204
    :cond_0
    invoke-super {p0, p1}, Landroid/widget/FrameLayout;->setEnabled(Z)V

    .line 205
    iget-object v0, p0, Lmiui/widget/TimePicker;->GZ:Lmiui/widget/NumberPicker;

    invoke-virtual {v0, p1}, Lmiui/widget/NumberPicker;->setEnabled(Z)V

    .line 206
    iget-object v0, p0, Lmiui/widget/TimePicker;->GX:Lmiui/widget/NumberPicker;

    invoke-virtual {v0, p1}, Lmiui/widget/NumberPicker;->setEnabled(Z)V

    .line 207
    iget-object v0, p0, Lmiui/widget/TimePicker;->Ha:Lmiui/widget/NumberPicker;

    if-eqz v0, :cond_1

    .line 208
    iget-object v0, p0, Lmiui/widget/TimePicker;->Ha:Lmiui/widget/NumberPicker;

    invoke-virtual {v0, p1}, Lmiui/widget/NumberPicker;->setEnabled(Z)V

    .line 212
    :goto_1
    iput-boolean p1, p0, Lmiui/widget/TimePicker;->sa:Z

    goto :goto_0

    .line 210
    :cond_1
    iget-object v0, p0, Lmiui/widget/TimePicker;->Hb:Landroid/widget/Button;

    invoke-virtual {v0, p1}, Landroid/widget/Button;->setEnabled(Z)V

    goto :goto_1
.end method

.method public setIs24HourView(Ljava/lang/Boolean;)V
    .locals 2

    .prologue
    .line 359
    iget-boolean v0, p0, Lmiui/widget/TimePicker;->wI:Z

    invoke-virtual {p1}, Ljava/lang/Boolean;->booleanValue()Z

    move-result v1

    if-ne v0, v1, :cond_0

    .line 369
    :goto_0
    return-void

    .line 362
    :cond_0
    invoke-virtual {p1}, Ljava/lang/Boolean;->booleanValue()Z

    move-result v0

    iput-boolean v0, p0, Lmiui/widget/TimePicker;->wI:Z

    .line 364
    invoke-virtual {p0}, Lmiui/widget/TimePicker;->getCurrentHour()Ljava/lang/Integer;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/Integer;->intValue()I

    move-result v0

    .line 365
    invoke-direct {p0}, Lmiui/widget/TimePicker;->dE()V

    .line 367
    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    invoke-virtual {p0, v0}, Lmiui/widget/TimePicker;->setCurrentHour(Ljava/lang/Integer;)V

    .line 368
    invoke-direct {p0}, Lmiui/widget/TimePicker;->dF()V

    goto :goto_0
.end method

.method public setOnTimeChangedListener(Lmiui/widget/TimePicker$OnTimeChangedListener;)V
    .locals 0

    .prologue
    .line 309
    iput-object p1, p0, Lmiui/widget/TimePicker;->Hc:Lmiui/widget/TimePicker$OnTimeChangedListener;

    .line 310
    return-void
.end method
