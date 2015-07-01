.class public Lmiui/date/DateUtils;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field public static final FORMAT_12HOUR:I = 0x10

.field public static final FORMAT_24HOUR:I = 0x20

.field public static final FORMAT_ABBREV_ALL:I = 0x7000

.field public static final FORMAT_ABBREV_MONTH:I = 0x1000

.field public static final FORMAT_ABBREV_TIME:I = 0x4000

.field public static final FORMAT_ABBREV_WEEKDAY:I = 0x2000

.field public static final FORMAT_NO_AM_PM:I = 0x40

.field public static final FORMAT_NUMERIC_DATE:I = 0x8000

.field public static final FORMAT_SHOW_BRIEF_TIME:I = 0xc

.field public static final FORMAT_SHOW_DATE:I = 0x380

.field public static final FORMAT_SHOW_HOUR:I = 0x8

.field public static final FORMAT_SHOW_MILLISECOND:I = 0x1

.field public static final FORMAT_SHOW_MINUTE:I = 0x4

.field public static final FORMAT_SHOW_MONTH:I = 0x100

.field public static final FORMAT_SHOW_MONTH_DAY:I = 0x80

.field public static final FORMAT_SHOW_SECOND:I = 0x2

.field public static final FORMAT_SHOW_TIME:I = 0xf

.field public static final FORMAT_SHOW_TIME_ZONE:I = 0x800

.field public static final FORMAT_SHOW_WEEKDAY:I = 0x400

.field public static final FORMAT_SHOW_YEAR:I = 0x200

.field private static final sN:Lmiui/util/Pools$Pool;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lmiui/util/Pools$Pool",
            "<",
            "Lmiui/date/Calendar;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method static constructor <clinit>()V
    .locals 2

    .prologue
    .line 206
    new-instance v0, Lmiui/date/b;

    invoke-direct {v0}, Lmiui/date/b;-><init>()V

    const/4 v1, 0x1

    invoke-static {v0, v1}, Lmiui/util/Pools;->createSoftReferencePool(Lmiui/util/Pools$Manager;I)Lmiui/util/Pools$SoftReferencePool;

    move-result-object v0

    sput-object v0, Lmiui/date/DateUtils;->sN:Lmiui/util/Pools$Pool;

    return-void
.end method

.method protected constructor <init>()V
    .locals 2
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/InstantiationException;
        }
    .end annotation

    .prologue
    .line 218
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 219
    new-instance v0, Ljava/lang/InstantiationException;

    const-string v1, "Cannot instantiate utility class"

    invoke-direct {v0, v1}, Ljava/lang/InstantiationException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

.method private static L(I)I
    .locals 5

    .prologue
    const v1, 0x8000

    const/16 v4, 0x200

    const/16 v3, 0x100

    const/16 v2, 0x80

    .line 505
    and-int v0, p0, v1

    if-ne v0, v1, :cond_6

    .line 506
    and-int/lit16 v0, p0, 0x200

    if-ne v0, v4, :cond_2

    .line 507
    and-int/lit16 v0, p0, 0x100

    if-ne v0, v3, :cond_1

    .line 508
    and-int/lit16 v0, p0, 0x80

    if-ne v0, v2, :cond_0

    .line 509
    sget v0, Lcom/miui/internal/R$string;->fmt_date_numeric_year_month_day:I

    .line 584
    :goto_0
    return v0

    .line 511
    :cond_0
    sget v0, Lcom/miui/internal/R$string;->fmt_date_numeric_year_month:I

    goto :goto_0

    .line 514
    :cond_1
    sget v0, Lcom/miui/internal/R$string;->fmt_date_numeric_year:I

    goto :goto_0

    .line 517
    :cond_2
    and-int/lit16 v0, p0, 0x100

    if-ne v0, v3, :cond_4

    .line 518
    and-int/lit16 v0, p0, 0x80

    if-ne v0, v2, :cond_3

    .line 519
    sget v0, Lcom/miui/internal/R$string;->fmt_date_numeric_month_day:I

    goto :goto_0

    .line 521
    :cond_3
    sget v0, Lcom/miui/internal/R$string;->fmt_date_numeric_month:I

    goto :goto_0

    .line 524
    :cond_4
    and-int/lit16 v0, p0, 0x80

    if-ne v0, v2, :cond_5

    .line 525
    sget v0, Lcom/miui/internal/R$string;->fmt_date_numeric_day:I

    goto :goto_0

    .line 527
    :cond_5
    new-instance v0, Ljava/lang/IllegalArgumentException;

    const-string v1, "no any time date"

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 531
    :cond_6
    and-int/lit16 v0, p0, 0x1000

    const/16 v1, 0x1000

    if-ne v0, v1, :cond_d

    .line 532
    and-int/lit16 v0, p0, 0x200

    if-ne v0, v4, :cond_9

    .line 533
    and-int/lit16 v0, p0, 0x100

    if-ne v0, v3, :cond_8

    .line 534
    and-int/lit16 v0, p0, 0x80

    if-ne v0, v2, :cond_7

    .line 535
    sget v0, Lcom/miui/internal/R$string;->fmt_date_short_year_month_day:I

    goto :goto_0

    .line 537
    :cond_7
    sget v0, Lcom/miui/internal/R$string;->fmt_date_short_year_month:I

    goto :goto_0

    .line 540
    :cond_8
    sget v0, Lcom/miui/internal/R$string;->fmt_date_year:I

    goto :goto_0

    .line 543
    :cond_9
    and-int/lit16 v0, p0, 0x100

    if-ne v0, v3, :cond_b

    .line 544
    and-int/lit16 v0, p0, 0x80

    if-ne v0, v2, :cond_a

    .line 545
    sget v0, Lcom/miui/internal/R$string;->fmt_date_short_month_day:I

    goto :goto_0

    .line 547
    :cond_a
    sget v0, Lcom/miui/internal/R$string;->fmt_date_short_month:I

    goto :goto_0

    .line 550
    :cond_b
    and-int/lit16 v0, p0, 0x80

    if-ne v0, v2, :cond_c

    .line 551
    sget v0, Lcom/miui/internal/R$string;->fmt_date_day:I

    goto :goto_0

    .line 553
    :cond_c
    new-instance v0, Ljava/lang/IllegalArgumentException;

    const-string v1, "no any time date"

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 558
    :cond_d
    and-int/lit16 v0, p0, 0x200

    if-ne v0, v4, :cond_10

    .line 559
    and-int/lit16 v0, p0, 0x100

    if-ne v0, v3, :cond_f

    .line 560
    and-int/lit16 v0, p0, 0x80

    if-ne v0, v2, :cond_e

    .line 561
    sget v0, Lcom/miui/internal/R$string;->fmt_date_long_year_month_day:I

    goto :goto_0

    .line 563
    :cond_e
    sget v0, Lcom/miui/internal/R$string;->fmt_date_long_year_month:I

    goto :goto_0

    .line 566
    :cond_f
    sget v0, Lcom/miui/internal/R$string;->fmt_date_year:I

    goto :goto_0

    .line 569
    :cond_10
    and-int/lit16 v0, p0, 0x100

    if-ne v0, v3, :cond_12

    .line 570
    and-int/lit16 v0, p0, 0x80

    if-ne v0, v2, :cond_11

    .line 571
    sget v0, Lcom/miui/internal/R$string;->fmt_date_long_month_day:I

    goto :goto_0

    .line 573
    :cond_11
    sget v0, Lcom/miui/internal/R$string;->fmt_date_long_month:I

    goto :goto_0

    .line 576
    :cond_12
    and-int/lit16 v0, p0, 0x80

    if-ne v0, v2, :cond_13

    .line 577
    sget v0, Lcom/miui/internal/R$string;->fmt_date_day:I

    goto/16 :goto_0

    .line 579
    :cond_13
    new-instance v0, Ljava/lang/IllegalArgumentException;

    const-string v1, "no any time date"

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

.method private static M(I)I
    .locals 2

    .prologue
    .line 589
    and-int/lit16 v0, p0, 0x2000

    const/16 v1, 0x2000

    if-ne v0, v1, :cond_0

    sget v0, Lcom/miui/internal/R$string;->fmt_weekday_short:I

    :goto_0
    return v0

    :cond_0
    sget v0, Lcom/miui/internal/R$string;->fmt_weekday_long:I

    goto :goto_0
.end method

.method private static N(I)I
    .locals 3

    .prologue
    const/16 v2, 0x800

    .line 608
    and-int/lit16 v0, p0, 0x400

    const/16 v1, 0x400

    if-ne v0, v1, :cond_7

    .line 609
    and-int/lit16 v0, p0, 0x380

    if-eqz v0, :cond_3

    .line 610
    and-int/lit8 v0, p0, 0xf

    if-eqz v0, :cond_1

    .line 611
    and-int/lit16 v0, p0, 0x800

    if-ne v0, v2, :cond_0

    sget v0, Lcom/miui/internal/R$string;->fmt_weekday_date_time_timezone:I

    .line 637
    :goto_0
    return v0

    .line 611
    :cond_0
    sget v0, Lcom/miui/internal/R$string;->fmt_weekday_date_time:I

    goto :goto_0

    .line 613
    :cond_1
    and-int/lit16 v0, p0, 0x800

    if-ne v0, v2, :cond_2

    sget v0, Lcom/miui/internal/R$string;->fmt_weekday_date_timezone:I

    goto :goto_0

    :cond_2
    sget v0, Lcom/miui/internal/R$string;->fmt_weekday_date:I

    goto :goto_0

    .line 616
    :cond_3
    and-int/lit8 v0, p0, 0xf

    if-eqz v0, :cond_5

    .line 617
    and-int/lit16 v0, p0, 0x800

    if-ne v0, v2, :cond_4

    sget v0, Lcom/miui/internal/R$string;->fmt_weekday_time_timezone:I

    goto :goto_0

    :cond_4
    sget v0, Lcom/miui/internal/R$string;->fmt_weekday_time:I

    goto :goto_0

    .line 619
    :cond_5
    and-int/lit16 v0, p0, 0x800

    if-ne v0, v2, :cond_6

    sget v0, Lcom/miui/internal/R$string;->fmt_weekday_timezone:I

    goto :goto_0

    :cond_6
    sget v0, Lcom/miui/internal/R$string;->fmt_weekday:I

    goto :goto_0

    .line 623
    :cond_7
    and-int/lit16 v0, p0, 0x380

    if-eqz v0, :cond_b

    .line 624
    and-int/lit8 v0, p0, 0xf

    if-eqz v0, :cond_9

    .line 625
    and-int/lit16 v0, p0, 0x800

    if-ne v0, v2, :cond_8

    sget v0, Lcom/miui/internal/R$string;->fmt_date_time_timezone:I

    goto :goto_0

    :cond_8
    sget v0, Lcom/miui/internal/R$string;->fmt_date_time:I

    goto :goto_0

    .line 627
    :cond_9
    and-int/lit16 v0, p0, 0x800

    if-ne v0, v2, :cond_a

    sget v0, Lcom/miui/internal/R$string;->fmt_date_timezone:I

    goto :goto_0

    :cond_a
    sget v0, Lcom/miui/internal/R$string;->fmt_date:I

    goto :goto_0

    .line 630
    :cond_b
    and-int/lit8 v0, p0, 0xf

    if-eqz v0, :cond_d

    .line 631
    and-int/lit16 v0, p0, 0x800

    if-ne v0, v2, :cond_c

    sget v0, Lcom/miui/internal/R$string;->fmt_time_timezone:I

    goto :goto_0

    :cond_c
    sget v0, Lcom/miui/internal/R$string;->fmt_time:I

    goto :goto_0

    .line 633
    :cond_d
    and-int/lit16 v0, p0, 0x800

    if-ne v0, v2, :cond_e

    sget v0, Lcom/miui/internal/R$string;->fmt_timezone:I

    goto :goto_0

    :cond_e
    sget v0, Lcom/miui/internal/R$string;->empty:I

    goto :goto_0
.end method

.method private static a(Lmiui/date/Calendar;I)I
    .locals 5

    .prologue
    const/4 v4, 0x4

    const/4 v3, 0x2

    const/4 v2, 0x1

    .line 405
    and-int/lit16 v0, p1, 0x4000

    const/16 v1, 0x4000

    if-ne v0, v1, :cond_2

    .line 406
    and-int/lit8 v0, p1, 0x1

    if-ne v0, v2, :cond_0

    const/16 v0, 0x16

    invoke-virtual {p0, v0}, Lmiui/date/Calendar;->get(I)I

    move-result v0

    if-nez v0, :cond_2

    :cond_0
    and-int/lit8 v0, p1, 0xe

    if-eqz v0, :cond_2

    .line 408
    and-int/lit8 p1, p1, -0x2

    .line 409
    and-int/lit8 v0, p1, 0x2

    if-ne v0, v3, :cond_1

    const/16 v0, 0x15

    invoke-virtual {p0, v0}, Lmiui/date/Calendar;->get(I)I

    move-result v0

    if-nez v0, :cond_2

    :cond_1
    and-int/lit8 v0, p1, 0xc

    if-eqz v0, :cond_2

    .line 411
    and-int/lit8 p1, p1, -0x3

    .line 412
    const/16 v0, 0x14

    invoke-virtual {p0, v0}, Lmiui/date/Calendar;->get(I)I

    move-result v0

    if-nez v0, :cond_2

    and-int/lit8 v0, p1, 0x8

    if-eqz v0, :cond_2

    .line 413
    and-int/lit8 p1, p1, -0x5

    .line 420
    :cond_2
    and-int/lit8 v0, p1, 0x8

    const/16 v1, 0x8

    if-ne v0, v1, :cond_e

    .line 421
    and-int/lit8 v0, p1, 0x10

    const/16 v1, 0x10

    if-ne v0, v1, :cond_a

    .line 422
    and-int/lit8 v0, p1, 0x40

    const/16 v1, 0x40

    if-ne v0, v1, :cond_6

    .line 423
    and-int/lit8 v0, p1, 0x4

    if-ne v0, v4, :cond_5

    .line 424
    and-int/lit8 v0, p1, 0x2

    if-ne v0, v3, :cond_4

    .line 425
    and-int/lit8 v0, p1, 0x1

    if-ne v0, v2, :cond_3

    .line 426
    sget v0, Lcom/miui/internal/R$string;->fmt_time_12hour_minute_second_millis:I

    .line 494
    :goto_0
    return v0

    .line 428
    :cond_3
    sget v0, Lcom/miui/internal/R$string;->fmt_time_12hour_minute_second:I

    goto :goto_0

    .line 431
    :cond_4
    sget v0, Lcom/miui/internal/R$string;->fmt_time_12hour_minute:I

    goto :goto_0

    .line 434
    :cond_5
    sget v0, Lcom/miui/internal/R$string;->fmt_time_12hour:I

    goto :goto_0

    .line 437
    :cond_6
    and-int/lit8 v0, p1, 0x4

    if-ne v0, v4, :cond_9

    .line 438
    and-int/lit8 v0, p1, 0x2

    if-ne v0, v3, :cond_8

    .line 439
    and-int/lit8 v0, p1, 0x1

    if-ne v0, v2, :cond_7

    .line 440
    sget v0, Lcom/miui/internal/R$string;->fmt_time_12hour_minute_second_millis_pm:I

    goto :goto_0

    .line 442
    :cond_7
    sget v0, Lcom/miui/internal/R$string;->fmt_time_12hour_minute_second_pm:I

    goto :goto_0

    .line 445
    :cond_8
    sget v0, Lcom/miui/internal/R$string;->fmt_time_12hour_minute_pm:I

    goto :goto_0

    .line 448
    :cond_9
    sget v0, Lcom/miui/internal/R$string;->fmt_time_12hour_pm:I

    goto :goto_0

    .line 452
    :cond_a
    and-int/lit8 v0, p1, 0x4

    if-ne v0, v4, :cond_d

    .line 453
    and-int/lit8 v0, p1, 0x2

    if-ne v0, v3, :cond_c

    .line 454
    and-int/lit8 v0, p1, 0x1

    if-ne v0, v2, :cond_b

    .line 455
    sget v0, Lcom/miui/internal/R$string;->fmt_time_24hour_minute_second_millis:I

    goto :goto_0

    .line 457
    :cond_b
    sget v0, Lcom/miui/internal/R$string;->fmt_time_24hour_minute_second:I

    goto :goto_0

    .line 460
    :cond_c
    sget v0, Lcom/miui/internal/R$string;->fmt_time_24hour_minute:I

    goto :goto_0

    .line 463
    :cond_d
    sget v0, Lcom/miui/internal/R$string;->fmt_time_24hour:I

    goto :goto_0

    .line 467
    :cond_e
    and-int/lit8 v0, p1, 0x4

    if-ne v0, v4, :cond_11

    .line 468
    and-int/lit8 v0, p1, 0x2

    if-ne v0, v3, :cond_10

    .line 469
    and-int/lit8 v0, p1, 0x1

    if-ne v0, v2, :cond_f

    .line 470
    sget v0, Lcom/miui/internal/R$string;->fmt_time_minute_second_millis:I

    goto :goto_0

    .line 472
    :cond_f
    sget v0, Lcom/miui/internal/R$string;->fmt_time_minute_second:I

    goto :goto_0

    .line 475
    :cond_10
    sget v0, Lcom/miui/internal/R$string;->fmt_time_minute:I

    goto :goto_0

    .line 478
    :cond_11
    and-int/lit8 v0, p1, 0x2

    if-ne v0, v3, :cond_13

    .line 479
    and-int/lit8 v0, p1, 0x1

    if-ne v0, v2, :cond_12

    .line 480
    sget v0, Lcom/miui/internal/R$string;->fmt_time_second_millis:I

    goto :goto_0

    .line 482
    :cond_12
    sget v0, Lcom/miui/internal/R$string;->fmt_time_second:I

    goto :goto_0

    .line 485
    :cond_13
    and-int/lit8 v0, p1, 0x1

    if-ne v0, v2, :cond_14

    .line 486
    sget v0, Lcom/miui/internal/R$string;->fmt_time_millis:I

    goto :goto_0

    .line 488
    :cond_14
    new-instance v0, Ljava/lang/IllegalArgumentException;

    const-string v1, "no any time date"

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

.method public static formatDateTime(JI)Ljava/lang/String;
    .locals 3

    .prologue
    .line 251
    invoke-static {}, Lmiui/util/Pools;->getStringBuilderPool()Lmiui/util/Pools$Pool;

    move-result-object v0

    invoke-interface {v0}, Lmiui/util/Pools$Pool;->acquire()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/StringBuilder;

    .line 252
    const/4 v1, 0x0

    invoke-static {v0, p0, p1, p2, v1}, Lmiui/date/DateUtils;->formatDateTime(Ljava/lang/StringBuilder;JILjava/util/TimeZone;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    .line 253
    invoke-static {}, Lmiui/util/Pools;->getStringBuilderPool()Lmiui/util/Pools$Pool;

    move-result-object v2

    invoke-interface {v2, v0}, Lmiui/util/Pools$Pool;->release(Ljava/lang/Object;)V

    .line 254
    return-object v1
.end method

.method public static formatDateTime(JILjava/util/TimeZone;)Ljava/lang/String;
    .locals 3

    .prologue
    .line 287
    invoke-static {}, Lmiui/util/Pools;->getStringBuilderPool()Lmiui/util/Pools$Pool;

    move-result-object v0

    invoke-interface {v0}, Lmiui/util/Pools$Pool;->acquire()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/StringBuilder;

    .line 288
    invoke-static {v0, p0, p1, p2, p3}, Lmiui/date/DateUtils;->formatDateTime(Ljava/lang/StringBuilder;JILjava/util/TimeZone;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    .line 289
    invoke-static {}, Lmiui/util/Pools;->getStringBuilderPool()Lmiui/util/Pools$Pool;

    move-result-object v2

    invoke-interface {v2, v0}, Lmiui/util/Pools$Pool;->release(Ljava/lang/Object;)V

    .line 290
    return-object v1
.end method

.method public static formatDateTime(Ljava/lang/StringBuilder;JI)Ljava/lang/StringBuilder;
    .locals 1

    .prologue
    .line 323
    const/4 v0, 0x0

    invoke-static {p0, p1, p2, p3, v0}, Lmiui/date/DateUtils;->formatDateTime(Ljava/lang/StringBuilder;JILjava/util/TimeZone;)Ljava/lang/StringBuilder;

    move-result-object v0

    return-object v0
.end method

.method public static formatDateTime(Ljava/lang/StringBuilder;JILjava/util/TimeZone;)Ljava/lang/StringBuilder;
    .locals 7

    .prologue
    .line 357
    invoke-static {}, Lcom/miui/internal/util/PackageConstants;->getCurrentApplication()Landroid/app/Application;

    move-result-object v3

    .line 359
    and-int/lit8 v0, p3, 0x10

    if-nez v0, :cond_0

    and-int/lit8 v0, p3, 0x20

    if-nez v0, :cond_0

    .line 360
    invoke-static {v3}, Landroid/text/format/DateFormat;->is24HourFormat(Landroid/content/Context;)Z

    move-result v0

    if-eqz v0, :cond_1

    const/16 v0, 0x20

    :goto_0
    or-int/2addr p3, v0

    .line 363
    :cond_0
    invoke-static {p3}, Lmiui/date/DateUtils;->N(I)I

    move-result v0

    invoke-virtual {v3, v0}, Landroid/content/Context;->getString(I)Ljava/lang/String;

    move-result-object v4

    .line 365
    invoke-static {}, Lmiui/util/Pools;->getStringBuilderPool()Lmiui/util/Pools$Pool;

    move-result-object v0

    invoke-interface {v0}, Lmiui/util/Pools$Pool;->acquire()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/StringBuilder;

    .line 367
    sget-object v1, Lmiui/date/DateUtils;->sN:Lmiui/util/Pools$Pool;

    invoke-interface {v1}, Lmiui/util/Pools$Pool;->acquire()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lmiui/date/Calendar;

    .line 368
    invoke-virtual {v1, p4}, Lmiui/date/Calendar;->setTimeZone(Ljava/util/TimeZone;)Lmiui/date/Calendar;

    .line 369
    invoke-virtual {v1, p1, p2}, Lmiui/date/Calendar;->setTimeInMillis(J)Lmiui/date/Calendar;

    .line 371
    const/4 v2, 0x0

    invoke-virtual {v4}, Ljava/lang/String;->length()I

    move-result v5

    :goto_1
    if-ge v2, v5, :cond_2

    .line 372
    invoke-virtual {v4, v2}, Ljava/lang/String;->charAt(I)C

    move-result v6

    .line 373
    sparse-switch v6, :sswitch_data_0

    .line 384
    invoke-virtual {v0, v6}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    .line 371
    :goto_2
    add-int/lit8 v2, v2, 0x1

    goto :goto_1

    .line 360
    :cond_1
    const/16 v0, 0x10

    goto :goto_0

    .line 375
    :sswitch_0
    invoke-static {p3}, Lmiui/date/DateUtils;->M(I)I

    move-result v6

    invoke-virtual {v3, v6}, Landroid/content/Context;->getString(I)Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v0, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    goto :goto_2

    .line 378
    :sswitch_1
    invoke-static {p3}, Lmiui/date/DateUtils;->L(I)I

    move-result v6

    invoke-virtual {v3, v6}, Landroid/content/Context;->getString(I)Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v0, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    goto :goto_2

    .line 381
    :sswitch_2
    invoke-static {v1, p3}, Lmiui/date/DateUtils;->a(Lmiui/date/Calendar;I)I

    move-result v6

    invoke-virtual {v3, v6}, Landroid/content/Context;->getString(I)Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v0, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    goto :goto_2

    .line 388
    :cond_2
    invoke-virtual {v1, p0, v0}, Lmiui/date/Calendar;->format(Ljava/lang/StringBuilder;Ljava/lang/CharSequence;)Ljava/lang/StringBuilder;

    .line 389
    invoke-static {}, Lmiui/util/Pools;->getStringBuilderPool()Lmiui/util/Pools$Pool;

    move-result-object v2

    invoke-interface {v2, v0}, Lmiui/util/Pools$Pool;->release(Ljava/lang/Object;)V

    .line 390
    sget-object v0, Lmiui/date/DateUtils;->sN:Lmiui/util/Pools$Pool;

    invoke-interface {v0, v1}, Lmiui/util/Pools$Pool;->release(Ljava/lang/Object;)V

    .line 391
    return-object p0

    .line 373
    :sswitch_data_0
    .sparse-switch
        0x44 -> :sswitch_1
        0x54 -> :sswitch_2
        0x57 -> :sswitch_0
    .end sparse-switch
.end method

.method public static formatRelativeTime(JZ)Ljava/lang/String;
    .locals 3

    .prologue
    .line 647
    invoke-static {}, Lmiui/util/Pools;->getStringBuilderPool()Lmiui/util/Pools$Pool;

    move-result-object v0

    invoke-interface {v0}, Lmiui/util/Pools$Pool;->acquire()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/StringBuilder;

    .line 648
    const/4 v1, 0x0

    invoke-static {v0, p0, p1, p2, v1}, Lmiui/date/DateUtils;->formatRelativeTime(Ljava/lang/StringBuilder;JZLjava/util/TimeZone;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    .line 649
    invoke-static {}, Lmiui/util/Pools;->getStringBuilderPool()Lmiui/util/Pools$Pool;

    move-result-object v2

    invoke-interface {v2, v0}, Lmiui/util/Pools$Pool;->release(Ljava/lang/Object;)V

    .line 650
    return-object v1
.end method

.method public static formatRelativeTime(JZLjava/util/TimeZone;)Ljava/lang/String;
    .locals 3

    .prologue
    .line 661
    invoke-static {}, Lmiui/util/Pools;->getStringBuilderPool()Lmiui/util/Pools$Pool;

    move-result-object v0

    invoke-interface {v0}, Lmiui/util/Pools$Pool;->acquire()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/StringBuilder;

    .line 662
    invoke-static {v0, p0, p1, p2, p3}, Lmiui/date/DateUtils;->formatRelativeTime(Ljava/lang/StringBuilder;JZLjava/util/TimeZone;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    .line 663
    invoke-static {}, Lmiui/util/Pools;->getStringBuilderPool()Lmiui/util/Pools$Pool;

    move-result-object v2

    invoke-interface {v2, v0}, Lmiui/util/Pools$Pool;->release(Ljava/lang/Object;)V

    .line 664
    return-object v1
.end method

.method public static formatRelativeTime(Ljava/lang/StringBuilder;JZ)Ljava/lang/StringBuilder;
    .locals 1

    .prologue
    .line 675
    const/4 v0, 0x0

    invoke-static {p0, p1, p2, p3, v0}, Lmiui/date/DateUtils;->formatRelativeTime(Ljava/lang/StringBuilder;JZLjava/util/TimeZone;)Ljava/lang/StringBuilder;

    move-result-object v0

    return-object v0
.end method

.method public static formatRelativeTime(Ljava/lang/StringBuilder;JZLjava/util/TimeZone;)Ljava/lang/StringBuilder;
    .locals 10

    .prologue
    .line 687
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v2

    .line 688
    cmp-long v0, v2, p1

    if-ltz v0, :cond_0

    const/4 v0, 0x1

    move v1, v0

    .line 689
    :goto_0
    sub-long v4, v2, p1

    invoke-static {v4, v5}, Ljava/lang/Math;->abs(J)J

    move-result-wide v4

    const-wide/32 v6, 0xea60

    div-long/2addr v4, v6

    .line 691
    invoke-static {}, Lcom/miui/internal/util/PackageConstants;->getCurrentApplication()Landroid/app/Application;

    move-result-object v0

    .line 692
    invoke-virtual {v0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v6

    .line 694
    const/16 v7, 0x3000

    .line 696
    const-wide/16 v8, 0x3c

    cmp-long v0, v4, v8

    if-gtz v0, :cond_8

    if-nez p3, :cond_8

    .line 698
    if-eqz v1, :cond_4

    .line 699
    const-wide/16 v0, 0x3c

    cmp-long v0, v4, v0

    if-nez v0, :cond_1

    .line 701
    sget v0, Lcom/miui/internal/R$plurals;->abbrev_a_hour_ago:I

    .line 725
    :goto_1
    long-to-int v1, v4

    invoke-virtual {v6, v0, v1}, Landroid/content/res/Resources;->getQuantityString(II)Ljava/lang/String;

    move-result-object v0

    .line 726
    const/4 v1, 0x1

    new-array v1, v1, [Ljava/lang/Object;

    const/4 v2, 0x0

    invoke-static {v4, v5}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v3

    aput-object v3, v1, v2

    invoke-static {v0, v1}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 756
    :goto_2
    return-object p0

    .line 688
    :cond_0
    const/4 v0, 0x0

    move v1, v0

    goto :goto_0

    .line 702
    :cond_1
    const-wide/16 v0, 0x1e

    cmp-long v0, v4, v0

    if-nez v0, :cond_2

    .line 704
    sget v0, Lcom/miui/internal/R$plurals;->abbrev_half_hour_ago:I

    goto :goto_1

    .line 705
    :cond_2
    const-wide/16 v0, 0x0

    cmp-long v0, v4, v0

    if-nez v0, :cond_3

    .line 707
    sget v0, Lcom/miui/internal/R$plurals;->abbrev_less_than_one_minute_ago:I

    goto :goto_1

    .line 709
    :cond_3
    sget v0, Lcom/miui/internal/R$plurals;->abbrev_num_minutes_ago:I

    goto :goto_1

    .line 712
    :cond_4
    const-wide/16 v0, 0x3c

    cmp-long v0, v4, v0

    if-nez v0, :cond_5

    .line 714
    sget v0, Lcom/miui/internal/R$plurals;->abbrev_in_a_hour:I

    goto :goto_1

    .line 715
    :cond_5
    const-wide/16 v0, 0x1e

    cmp-long v0, v4, v0

    if-nez v0, :cond_6

    .line 717
    sget v0, Lcom/miui/internal/R$plurals;->abbrev_in_half_hour:I

    goto :goto_1

    .line 718
    :cond_6
    const-wide/16 v0, 0x0

    cmp-long v0, v4, v0

    if-nez v0, :cond_7

    .line 720
    sget v0, Lcom/miui/internal/R$plurals;->abbrev_in_less_than_one_minute:I

    goto :goto_1

    .line 722
    :cond_7
    sget v0, Lcom/miui/internal/R$plurals;->abbrev_in_num_minutes:I

    goto :goto_1

    .line 728
    :cond_8
    sget-object v0, Lmiui/date/DateUtils;->sN:Lmiui/util/Pools$Pool;

    invoke-interface {v0}, Lmiui/util/Pools$Pool;->acquire()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/date/Calendar;

    .line 729
    invoke-virtual {v0, p4}, Lmiui/date/Calendar;->setTimeZone(Ljava/util/TimeZone;)Lmiui/date/Calendar;

    .line 730
    invoke-virtual {v0, v2, v3}, Lmiui/date/Calendar;->setTimeInMillis(J)Lmiui/date/Calendar;

    .line 731
    const/4 v2, 0x1

    invoke-virtual {v0, v2}, Lmiui/date/Calendar;->get(I)I

    move-result v2

    .line 732
    const/16 v3, 0xc

    invoke-virtual {v0, v3}, Lmiui/date/Calendar;->get(I)I

    move-result v4

    .line 733
    const/16 v3, 0xe

    invoke-virtual {v0, v3}, Lmiui/date/Calendar;->get(I)I

    move-result v5

    .line 734
    invoke-virtual {v0, p1, p2}, Lmiui/date/Calendar;->setTimeInMillis(J)Lmiui/date/Calendar;

    .line 735
    const/4 v3, 0x1

    invoke-virtual {v0, v3}, Lmiui/date/Calendar;->get(I)I

    move-result v3

    if-ne v2, v3, :cond_9

    const/4 v2, 0x1

    move v3, v2

    .line 736
    :goto_3
    if-eqz v3, :cond_a

    const/16 v2, 0xc

    invoke-virtual {v0, v2}, Lmiui/date/Calendar;->get(I)I

    move-result v2

    if-ne v4, v2, :cond_a

    .line 737
    const/16 v1, 0x300c

    .line 738
    invoke-static {p0, p1, p2, v1, p4}, Lmiui/date/DateUtils;->formatDateTime(Ljava/lang/StringBuilder;JILjava/util/TimeZone;)Ljava/lang/StringBuilder;

    .line 754
    :goto_4
    sget-object v1, Lmiui/date/DateUtils;->sN:Lmiui/util/Pools$Pool;

    invoke-interface {v1, v0}, Lmiui/util/Pools$Pool;->release(Ljava/lang/Object;)V

    goto :goto_2

    .line 735
    :cond_9
    const/4 v2, 0x0

    move v3, v2

    goto :goto_3

    .line 739
    :cond_a
    if-eqz v3, :cond_c

    const/16 v2, 0xc

    invoke-virtual {v0, v2}, Lmiui/date/Calendar;->get(I)I

    move-result v2

    sub-int v2, v4, v2

    invoke-static {v2}, Ljava/lang/Math;->abs(I)I

    move-result v2

    const/4 v8, 0x2

    if-ge v2, v8, :cond_c

    .line 740
    if-eqz v1, :cond_b

    sget v1, Lcom/miui/internal/R$string;->yesterday:I

    :goto_5
    invoke-virtual {v6, v1}, Landroid/content/res/Resources;->getString(I)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {p0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 741
    const/16 v1, 0x20

    invoke-virtual {p0, v1}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    .line 742
    const/16 v1, 0x300c

    .line 743
    invoke-static {p0, p1, p2, v1, p4}, Lmiui/date/DateUtils;->formatDateTime(Ljava/lang/StringBuilder;JILjava/util/TimeZone;)Ljava/lang/StringBuilder;

    goto :goto_4

    .line 740
    :cond_b
    sget v1, Lcom/miui/internal/R$string;->tomorrow:I

    goto :goto_5

    .line 744
    :cond_c
    if-eqz v3, :cond_e

    const/16 v2, 0xc

    invoke-virtual {v0, v2}, Lmiui/date/Calendar;->get(I)I

    move-result v2

    sub-int v2, v4, v2

    invoke-static {v2}, Ljava/lang/Math;->abs(I)I

    move-result v2

    const/4 v4, 0x7

    if-ge v2, v4, :cond_e

    const/16 v2, 0xe

    invoke-virtual {v0, v2}, Lmiui/date/Calendar;->get(I)I

    move-result v2

    if-le v5, v2, :cond_d

    const/4 v2, 0x1

    :goto_6
    if-ne v1, v2, :cond_e

    .line 745
    const/16 v1, 0x340c

    .line 746
    invoke-static {p0, p1, p2, v1, p4}, Lmiui/date/DateUtils;->formatDateTime(Ljava/lang/StringBuilder;JILjava/util/TimeZone;)Ljava/lang/StringBuilder;

    goto :goto_4

    .line 744
    :cond_d
    const/4 v2, 0x0

    goto :goto_6

    .line 747
    :cond_e
    if-eqz v3, :cond_10

    .line 748
    if-eqz p3, :cond_f

    const/16 v1, 0x18c

    :goto_7
    or-int/2addr v1, v7

    .line 749
    invoke-static {p0, p1, p2, v1, p4}, Lmiui/date/DateUtils;->formatDateTime(Ljava/lang/StringBuilder;JILjava/util/TimeZone;)Ljava/lang/StringBuilder;

    goto :goto_4

    .line 748
    :cond_f
    const/16 v1, 0x180

    goto :goto_7

    .line 751
    :cond_10
    if-eqz p3, :cond_11

    const/16 v1, 0x38c

    :goto_8
    or-int/2addr v1, v7

    .line 752
    invoke-static {p0, p1, p2, v1, p4}, Lmiui/date/DateUtils;->formatDateTime(Ljava/lang/StringBuilder;JILjava/util/TimeZone;)Ljava/lang/StringBuilder;

    goto :goto_4

    .line 751
    :cond_11
    const/16 v1, 0x380

    goto :goto_8
.end method
