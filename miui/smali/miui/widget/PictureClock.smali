.class public Lmiui/widget/PictureClock;
.super Landroid/widget/LinearLayout;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lmiui/widget/PictureClock$a;
    }
.end annotation


# instance fields
.field private final bo:Landroid/content/BroadcastReceiver;

.field private bp:Lmiui/date/Calendar;

.field private bq:Lmiui/widget/ImageTextView;

.field private br:Lmiui/widget/PictureClock$a;

.field private bs:Z

.field private bt:Z

.field private final mHandler:Landroid/os/Handler;


# direct methods
.method public constructor <init>(Landroid/content/Context;)V
    .locals 1

    .prologue
    .line 64
    const/4 v0, 0x0

    invoke-direct {p0, p1, v0}, Lmiui/widget/PictureClock;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    .line 65
    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 1

    .prologue
    .line 68
    invoke-direct {p0, p1, p2}, Landroid/widget/LinearLayout;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    .line 34
    new-instance v0, Landroid/os/Handler;

    invoke-direct {v0}, Landroid/os/Handler;-><init>()V

    iput-object v0, p0, Lmiui/widget/PictureClock;->mHandler:Landroid/os/Handler;

    .line 36
    new-instance v0, Lmiui/widget/m;

    invoke-direct {v0, p0}, Lmiui/widget/m;-><init>(Lmiui/widget/PictureClock;)V

    iput-object v0, p0, Lmiui/widget/PictureClock;->bo:Landroid/content/BroadcastReceiver;

    .line 59
    const/4 v0, 0x1

    iput-boolean v0, p0, Lmiui/widget/PictureClock;->bs:Z

    .line 69
    return-void
.end method

.method static synthetic a(Lmiui/widget/PictureClock;Lmiui/date/Calendar;)Lmiui/date/Calendar;
    .locals 0

    .prologue
    .line 31
    iput-object p1, p0, Lmiui/widget/PictureClock;->bp:Lmiui/date/Calendar;

    return-object p1
.end method

.method static synthetic a(Lmiui/widget/PictureClock;)Z
    .locals 1

    .prologue
    .line 31
    iget-boolean v0, p0, Lmiui/widget/PictureClock;->bs:Z

    return v0
.end method

.method static synthetic b(Lmiui/widget/PictureClock;)V
    .locals 0

    .prologue
    .line 31
    invoke-direct {p0}, Lmiui/widget/PictureClock;->n()V

    return-void
.end method

.method static synthetic c(Lmiui/widget/PictureClock;)Landroid/os/Handler;
    .locals 1

    .prologue
    .line 31
    iget-object v0, p0, Lmiui/widget/PictureClock;->mHandler:Landroid/os/Handler;

    return-object v0
.end method

.method private n()V
    .locals 6

    .prologue
    const/4 v1, 0x1

    const/4 v2, 0x0

    .line 118
    iget-boolean v0, p0, Lmiui/widget/PictureClock;->bs:Z

    if-eqz v0, :cond_0

    .line 119
    iget-object v0, p0, Lmiui/widget/PictureClock;->bp:Lmiui/date/Calendar;

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v3

    invoke-virtual {v0, v3, v4}, Lmiui/date/Calendar;->setTimeInMillis(J)Lmiui/date/Calendar;

    .line 122
    :cond_0
    iget-object v0, p0, Lmiui/widget/PictureClock;->bq:Lmiui/widget/ImageTextView;

    iget-object v3, p0, Lmiui/widget/PictureClock;->bp:Lmiui/date/Calendar;

    invoke-virtual {v3}, Lmiui/date/Calendar;->getTimeInMillis()J

    move-result-wide v3

    const/16 v5, 0x4c

    invoke-static {v3, v4, v5}, Lmiui/date/DateUtils;->formatDateTime(JI)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v0, v3}, Lmiui/widget/ImageTextView;->setText(Ljava/lang/CharSequence;)V

    .line 125
    iget-object v3, p0, Lmiui/widget/PictureClock;->br:Lmiui/widget/PictureClock$a;

    invoke-virtual {p0}, Lmiui/widget/PictureClock;->getContext()Landroid/content/Context;

    move-result-object v0

    invoke-static {v0}, Landroid/text/format/DateFormat;->is24HourFormat(Landroid/content/Context;)Z

    move-result v0

    if-nez v0, :cond_1

    move v0, v1

    :goto_0
    invoke-virtual {v3, v0}, Lmiui/widget/PictureClock$a;->m(Z)V

    .line 126
    iget-object v0, p0, Lmiui/widget/PictureClock;->br:Lmiui/widget/PictureClock$a;

    iget-object v3, p0, Lmiui/widget/PictureClock;->bp:Lmiui/date/Calendar;

    const/16 v4, 0x11

    invoke-virtual {v3, v4}, Lmiui/date/Calendar;->get(I)I

    move-result v3

    if-nez v3, :cond_2

    :goto_1
    invoke-virtual {v0, v1}, Lmiui/widget/PictureClock$a;->n(Z)V

    .line 127
    return-void

    :cond_1
    move v0, v2

    .line 125
    goto :goto_0

    :cond_2
    move v1, v2

    .line 126
    goto :goto_1
.end method


# virtual methods
.method f(Z)V
    .locals 0

    .prologue
    .line 130
    iput-boolean p1, p0, Lmiui/widget/PictureClock;->bs:Z

    .line 131
    return-void
.end method

.method protected onAttachedToWindow()V
    .locals 3

    .prologue
    .line 81
    invoke-super {p0}, Landroid/widget/LinearLayout;->onAttachedToWindow()V

    .line 83
    iget-boolean v0, p0, Lmiui/widget/PictureClock;->bt:Z

    if-eqz v0, :cond_0

    .line 101
    :goto_0
    return-void

    .line 86
    :cond_0
    const/4 v0, 0x1

    iput-boolean v0, p0, Lmiui/widget/PictureClock;->bt:Z

    .line 88
    iget-boolean v0, p0, Lmiui/widget/PictureClock;->bs:Z

    if-eqz v0, :cond_1

    .line 90
    new-instance v0, Landroid/content/IntentFilter;

    invoke-direct {v0}, Landroid/content/IntentFilter;-><init>()V

    .line 91
    const-string v1, "android.intent.action.TIME_TICK"

    invoke-virtual {v0, v1}, Landroid/content/IntentFilter;->addAction(Ljava/lang/String;)V

    .line 92
    const-string v1, "android.intent.action.TIME_SET"

    invoke-virtual {v0, v1}, Landroid/content/IntentFilter;->addAction(Ljava/lang/String;)V

    .line 93
    const-string v1, "android.intent.action.TIMEZONE_CHANGED"

    invoke-virtual {v0, v1}, Landroid/content/IntentFilter;->addAction(Ljava/lang/String;)V

    .line 94
    const-string v1, "android.intent.action.CONFIGURATION_CHANGED"

    invoke-virtual {v0, v1}, Landroid/content/IntentFilter;->addAction(Ljava/lang/String;)V

    .line 96
    invoke-virtual {p0}, Lmiui/widget/PictureClock;->getContext()Landroid/content/Context;

    move-result-object v1

    iget-object v2, p0, Lmiui/widget/PictureClock;->bo:Landroid/content/BroadcastReceiver;

    invoke-virtual {v1, v2, v0}, Landroid/content/Context;->registerReceiver(Landroid/content/BroadcastReceiver;Landroid/content/IntentFilter;)Landroid/content/Intent;

    .line 98
    :cond_1
    new-instance v0, Lmiui/date/Calendar;

    invoke-direct {v0}, Lmiui/date/Calendar;-><init>()V

    iput-object v0, p0, Lmiui/widget/PictureClock;->bp:Lmiui/date/Calendar;

    .line 100
    invoke-direct {p0}, Lmiui/widget/PictureClock;->n()V

    goto :goto_0
.end method

.method protected onDetachedFromWindow()V
    .locals 2

    .prologue
    .line 105
    invoke-super {p0}, Landroid/widget/LinearLayout;->onDetachedFromWindow()V

    .line 107
    iget-boolean v0, p0, Lmiui/widget/PictureClock;->bt:Z

    if-nez v0, :cond_1

    .line 115
    :cond_0
    :goto_0
    return-void

    .line 110
    :cond_1
    const/4 v0, 0x0

    iput-boolean v0, p0, Lmiui/widget/PictureClock;->bt:Z

    .line 112
    iget-boolean v0, p0, Lmiui/widget/PictureClock;->bs:Z

    if-eqz v0, :cond_0

    .line 113
    invoke-virtual {p0}, Lmiui/widget/PictureClock;->getContext()Landroid/content/Context;

    move-result-object v0

    iget-object v1, p0, Lmiui/widget/PictureClock;->bo:Landroid/content/BroadcastReceiver;

    invoke-virtual {v0, v1}, Landroid/content/Context;->unregisterReceiver(Landroid/content/BroadcastReceiver;)V

    goto :goto_0
.end method

.method protected onFinishInflate()V
    .locals 1

    .prologue
    .line 73
    invoke-super {p0}, Landroid/widget/LinearLayout;->onFinishInflate()V

    .line 75
    sget v0, Lcom/miui/internal/R$id;->time_display:I

    invoke-virtual {p0, v0}, Lmiui/widget/PictureClock;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Lmiui/widget/ImageTextView;

    iput-object v0, p0, Lmiui/widget/PictureClock;->bq:Lmiui/widget/ImageTextView;

    .line 76
    new-instance v0, Lmiui/widget/PictureClock$a;

    invoke-direct {v0, p0}, Lmiui/widget/PictureClock$a;-><init>(Landroid/view/View;)V

    iput-object v0, p0, Lmiui/widget/PictureClock;->br:Lmiui/widget/PictureClock$a;

    .line 77
    return-void
.end method
