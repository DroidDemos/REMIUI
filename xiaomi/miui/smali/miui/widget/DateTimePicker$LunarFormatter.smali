.class public Lmiui/widget/DateTimePicker$LunarFormatter;
.super Lmiui/widget/DateTimePicker$DayFormatter;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/widget/DateTimePicker;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x9
    name = "LunarFormatter"
.end annotation


# direct methods
.method public constructor <init>()V
    .locals 0

    .prologue
    .line 469
    invoke-direct {p0}, Lmiui/widget/DateTimePicker$DayFormatter;-><init>()V

    return-void
.end method


# virtual methods
.method public formatDay(III)Ljava/lang/String;
    .locals 3

    .prologue
    .line 472
    invoke-static {}, Lmiui/widget/DateTimePicker;->dN()Ljava/lang/ThreadLocal;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/ThreadLocal;->get()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/date/Calendar;

    .line 473
    if-nez v0, :cond_0

    .line 474
    new-instance v0, Lmiui/date/Calendar;

    invoke-direct {v0}, Lmiui/date/Calendar;-><init>()V

    .line 475
    invoke-static {}, Lmiui/widget/DateTimePicker;->dN()Ljava/lang/ThreadLocal;

    move-result-object v1

    invoke-virtual {v1, v0}, Ljava/lang/ThreadLocal;->set(Ljava/lang/Object;)V

    .line 477
    :cond_0
    const/4 v1, 0x1

    invoke-virtual {v0, v1, p1}, Lmiui/date/Calendar;->set(II)Lmiui/date/Calendar;

    .line 478
    const/4 v1, 0x5

    invoke-virtual {v0, v1, p2}, Lmiui/date/Calendar;->set(II)Lmiui/date/Calendar;

    .line 479
    const/16 v1, 0x9

    invoke-virtual {v0, v1, p3}, Lmiui/date/Calendar;->set(II)Lmiui/date/Calendar;

    .line 480
    invoke-static {}, Landroid/content/res/Resources;->getSystem()Landroid/content/res/Resources;

    move-result-object v1

    sget v2, Lcom/miui/internal/R$string;->fmt_chinese_date:I

    invoke-virtual {v1, v2}, Landroid/content/res/Resources;->getString(I)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Lmiui/date/Calendar;->format(Ljava/lang/CharSequence;)Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method
