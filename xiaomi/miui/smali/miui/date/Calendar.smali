.class public Lmiui/date/Calendar;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/io/Serializable;
.implements Ljava/lang/Cloneable;
.implements Ljava/lang/Comparable;


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Object;",
        "Ljava/io/Serializable;",
        "Ljava/lang/Cloneable;",
        "Ljava/lang/Comparable",
        "<",
        "Lmiui/date/Calendar;",
        ">;"
    }
.end annotation


# static fields
.field public static final AD:I = 0x1

.field public static final AFTERNOON:I = 0x4

.field public static final AM:I = 0x0

.field public static final AM_PM:I = 0x11

.field public static final APRIL:I = 0x3

.field public static final AUGUST:I = 0x7

.field public static final AUTUMN_BEGINS:I = 0xf

.field public static final AUTUMN_EQUINOX:I = 0x12

.field public static final BC:I = 0x0

.field public static final CHICKEN:I = 0x9

.field public static final CHINESE_ERA_DAY:I = 0xb

.field public static final CHINESE_ERA_HOUR:I = 0x13

.field public static final CHINESE_ERA_MONTH:I = 0x7

.field public static final CHINESE_ERA_YEAR:I = 0x4

.field public static final CHINESE_MONTH:I = 0x6

.field public static final CHINESE_MONTH_IS_LEAP:I = 0x8

.field public static final CHINESE_YEAR:I = 0x2

.field public static final CHINESE_YEAR_SYMBOL_ANIMAL:I = 0x3

.field public static final CLEAR_AND_BRIGHT:I = 0x7

.field public static final COLD_DEWS:I = 0x13

.field public static final COW:I = 0x1

.field public static final DAY_OF_CHINESE_MONTH:I = 0xa

.field public static final DAY_OF_CHINESE_YEAR:I = 0xd

.field public static final DAY_OF_MONTH:I = 0x9

.field public static final DAY_OF_WEEK:I = 0xe

.field public static final DAY_OF_YEAR:I = 0xc

.field public static final DECEMBER:I = 0xb

.field public static final DETAIL_AM_PM:I = 0x10

.field public static final DOG:I = 0xa

.field public static final DRAGON:I = 0x4

.field public static final DST_OFFSET:I = 0x18

.field public static final EARLY_MORNING:I = 0x1

.field public static final ERA:I = 0x0

.field public static final EVENING:I = 0x5

.field public static final FEBRUARY:I = 0x1

.field public static final FIELD_COUNT:I = 0x19

.field public static final FRIDAY:I = 0x6

.field public static final GRAIN_BUDS:I = 0xa

.field public static final GRAIN_IN_EAR:I = 0xb

.field public static final GRAIN_RAIN:I = 0x8

.field public static final GREAT_COLD:I = 0x2

.field public static final GREAT_HEAT:I = 0xe

.field public static final HEAVY_SNOW:I = 0x17

.field public static final HOAR_FROST_FALLS:I = 0x14

.field public static final HORSE:I = 0x6

.field public static final HOUR:I = 0x12

.field public static final INSECTS_AWAKEN:I = 0x5

.field public static final IS_CHINESE_LEAP_MONTH:I = 0x1

.field public static final JANUARY:I = 0x0

.field public static final JULY:I = 0x6

.field public static final JUNE:I = 0x5

.field public static final LIGHT_SNOW:I = 0x16

.field public static final MARCH:I = 0x2

.field public static final MAY:I = 0x4

.field public static final MIDNIGHT:I = 0x0

.field public static final MILLISECOND:I = 0x16

.field public static final MILLISECOND_OF_DAY:I = 0x5265c00

.field public static final MILLISECOND_OF_HOUR:I = 0x36ee80

.field public static final MILLISECOND_OF_MINUTE:I = 0xea60

.field public static final MILLISECOND_OF_SECOND:I = 0x3e8

.field public static final MINUTE:I = 0x14

.field public static final MONDAY:I = 0x2

.field public static final MONKEY:I = 0x8

.field public static final MONTH:I = 0x5

.field public static final MORNING:I = 0x2

.field public static final MOUSE:I = 0x0

.field public static final NIGHT:I = 0x6

.field public static final NOON:I = 0x3

.field public static final NOT_CHINESE_LEAP_MONTH:I = 0x0

.field public static final NOVEMBER:I = 0xa

.field public static final NO_SOLAR_TERM:I = 0x0

.field public static final OCTOBER:I = 0x9

.field public static final PIG:I = 0xb

.field public static final PM:I = 0x1

.field public static final RABBIT:I = 0x3

.field public static final SATURDAY:I = 0x7

.field public static final SECOND:I = 0x15

.field public static final SEPTEMBER:I = 0x8

.field public static final SHEEP:I = 0x7

.field public static final SLIGHT_COLD:I = 0x1

.field public static final SLIGHT_HEAT:I = 0xd

.field public static final SNAKE:I = 0x5

.field public static final SOLAR_TERM:I = 0xf

.field public static final SPRING_BEGINS:I = 0x3

.field public static final STOPPING_THE_HEAT:I = 0x10

.field public static final SUMMER_BEGINS:I = 0x9

.field public static final SUMMER_SOLSTICE:I = 0xc

.field public static final SUNDAY:I = 0x1

.field public static final THE_RAINS:I = 0x4

.field public static final THURSDAY:I = 0x5

.field public static final TIGER:I = 0x2

.field public static final TUESDAY:I = 0x3

.field public static final VERNAL_EQUINOX:I = 0x6

.field public static final WEDNESDAY:I = 0x4

.field public static final WHITE_DEWS:I = 0x11

.field public static final WINTER_BEGINS:I = 0x15

.field public static final WINTER_SOLSTICE:I = 0x18

.field public static final YEAR:I = 0x1

.field public static final ZONE_OFFSET:I = 0x17

.field private static final bA:[I

.field private static final bB:[Ljava/lang/String;

.field private static final bC:[I

.field private static final bD:[I

.field private static final bE:[I

.field private static final bF:[I

.field private static final bG:[B

.field private static final bH:[B

.field private static final bI:J = -0xb1d069b5400L

.field private static final bu:I = 0x76c

.field private static final bv:I = 0x834

.field private static final bw:J = -0x201b77f5c00L

.field private static final bx:J = 0x3c314a71400L

.field private static final by:I = -0x63c1

.field private static final bz:[B

.field private static final serialVersionUID:J = 0x1L


# instance fields
.field private transient bJ:J

.field private transient bK:I

.field private transient bL:I

.field private transient bM:I

.field private transient bN:I

.field private transient bO:I

.field private mFields:[I

.field private mMillisecond:J

.field private mTimeZone:Ljava/util/TimeZone;


# direct methods
.method static constructor <clinit>()V
    .locals 6

    .prologue
    const/16 v5, 0x18

    const/16 v4, 0xd

    const/16 v3, 0xc

    .line 95
    new-array v0, v3, [B

    fill-array-data v0, :array_0

    sput-object v0, Lmiui/date/Calendar;->bz:[B

    .line 99
    new-array v0, v4, [I

    fill-array-data v0, :array_1

    sput-object v0, Lmiui/date/Calendar;->bA:[I

    .line 323
    const/16 v0, 0x19

    new-array v0, v0, [Ljava/lang/String;

    const/4 v1, 0x0

    const-string v2, "ERA"

    aput-object v2, v0, v1

    const/4 v1, 0x1

    const-string v2, "YEAR"

    aput-object v2, v0, v1

    const/4 v1, 0x2

    const-string v2, "CHINESE_YEAR"

    aput-object v2, v0, v1

    const/4 v1, 0x3

    const-string v2, "CHINESE_YEAR_SYMBOL_ANIMAL"

    aput-object v2, v0, v1

    const/4 v1, 0x4

    const-string v2, "CHINESE_ERA_YEAR"

    aput-object v2, v0, v1

    const/4 v1, 0x5

    const-string v2, "MONTH"

    aput-object v2, v0, v1

    const/4 v1, 0x6

    const-string v2, "CHINESE_MONTH"

    aput-object v2, v0, v1

    const/4 v1, 0x7

    const-string v2, "CHINESE_ERA_MONTH"

    aput-object v2, v0, v1

    const/16 v1, 0x8

    const-string v2, "CHINESE_MONTH_IS_LEAP"

    aput-object v2, v0, v1

    const/16 v1, 0x9

    const-string v2, "DAY_OF_MONTH"

    aput-object v2, v0, v1

    const/16 v1, 0xa

    const-string v2, "DAY_OF_CHINESE_MONTH"

    aput-object v2, v0, v1

    const/16 v1, 0xb

    const-string v2, "CHINESE_ERA_DAY"

    aput-object v2, v0, v1

    const-string v1, "DAY_OF_YEAR"

    aput-object v1, v0, v3

    const-string v1, "DAY_OF_CHINESE_YEAR"

    aput-object v1, v0, v4

    const/16 v1, 0xe

    const-string v2, "DAY_OF_WEEK"

    aput-object v2, v0, v1

    const/16 v1, 0xf

    const-string v2, "SOLAR_TERM"

    aput-object v2, v0, v1

    const/16 v1, 0x10

    const-string v2, "DETAIL_AM_PM"

    aput-object v2, v0, v1

    const/16 v1, 0x11

    const-string v2, "AM_PM"

    aput-object v2, v0, v1

    const/16 v1, 0x12

    const-string v2, "HOUR"

    aput-object v2, v0, v1

    const/16 v1, 0x13

    const-string v2, "CHINESE_ERA_HOUR"

    aput-object v2, v0, v1

    const/16 v1, 0x14

    const-string v2, "MINUTE"

    aput-object v2, v0, v1

    const/16 v1, 0x15

    const-string v2, "SECOND"

    aput-object v2, v0, v1

    const/16 v1, 0x16

    const-string v2, "MILLISECOND"

    aput-object v2, v0, v1

    const/16 v1, 0x17

    const-string v2, "ZONE_OFFSET"

    aput-object v2, v0, v1

    const-string v1, "DST_OFFSET"

    aput-object v1, v0, v5

    sput-object v0, Lmiui/date/Calendar;->bB:[Ljava/lang/String;

    .line 678
    const/16 v0, 0x3a

    new-array v0, v0, [I

    fill-array-data v0, :array_2

    sput-object v0, Lmiui/date/Calendar;->bC:[I

    .line 687
    const/16 v0, 0xca

    new-array v0, v0, [I

    fill-array-data v0, :array_3

    sput-object v0, Lmiui/date/Calendar;->bD:[I

    .line 711
    const/16 v0, 0xc9

    new-array v0, v0, [I

    fill-array-data v0, :array_4

    sput-object v0, Lmiui/date/Calendar;->bE:[I

    .line 735
    new-array v0, v5, [I

    fill-array-data v0, :array_5

    sput-object v0, Lmiui/date/Calendar;->bF:[I

    .line 739
    const-string v0, "0123415341536789:;<9:=<>:=1>?012@015@015@015AB78CDE8CD=1FD01GH01GH01IH01IJ0KLMN;LMBEOPDQRST0RUH0RVH0RWH0RWM0XYMNZ[MB\\]PT^_ST`_WH`_WH`_WM`_WM`aYMbc[Mde]Sfe]gfh_gih_Wih_WjhaWjka[jkl[jmn]ope]qph_qrh_sth_W"

    invoke-virtual {v0}, Ljava/lang/String;->getBytes()[B

    move-result-object v0

    sput-object v0, Lmiui/date/Calendar;->bG:[B

    .line 745
    const-string v0, "211122112122112121222211221122122222212222222221222122222232222222222222222233223232223232222222322222112122112121222211222122222222222222222222322222112122112121222111211122122222212221222221221122122222222222222222222223222232222232222222222222112122112121122111211122122122212221222221221122122222222222222221211122112122212221222211222122222232222232222222222222112122112121111111222222112121112121111111222222111121112121111111211122112122112121122111222212111121111121111111111122112122112121122111211122112122212221222221222211111121111121111111222111111121111111111111111122112121112121111111222111111111111111111111111122111121112121111111221122122222212221222221222111011111111111111111111122111121111121111111211122112122112121122211221111011111101111111111111112111121111121111111211122112122112221222211221111011111101111111110111111111121111111111111111122112121112121122111111011111121111111111111111011111111112111111111111011111111111111111111221111011111101110111110111011011111111111111111221111011011101110111110111011011111101111111111211111001011101110111110110011011111101111111111211111001011001010111110110011011111101111111110211111001011001010111100110011011011101110111110211111001011001010011100110011001011101110111110211111001010001010011000100011001011001010111110111111001010001010011000111111111111111111111111100011001011001010111100111111001010001010000000111111000010000010000000100011001011001010011100110011001011001110111110100011001010001010011000110011001011001010111110111100000010000000000000000011001010001010011000111100000000000000000000000011001010001010000000111000000000000000000000000011001010000010000000"

    invoke-virtual {v0}, Ljava/lang/String;->getBytes()[B

    move-result-object v0

    sput-object v0, Lmiui/date/Calendar;->bH:[B

    return-void

    .line 95
    nop

    :array_0
    .array-data 1
        0x1ft
        0x1ct
        0x1ft
        0x1et
        0x1ft
        0x1et
        0x1ft
        0x1ft
        0x1et
        0x1ft
        0x1et
        0x1ft
    .end array-data

    .line 99
    :array_1
    .array-data 4
        0x0
        0x1f
        0x3b
        0x5a
        0x78
        0x97
        0xb5
        0xd4
        0xf3
        0x111
        0x130
        0x14e
        0x16d
    .end array-data

    .line 678
    :array_2
    .array-data 4
        0x3
        -0x1
        -0x1
        0xc
        0xe
        0x1a
        0x0
        0x12
        0x13
        -0x1
        0x12
        0x5
        0x5
        0x7
        -0x1
        -0x1
        -0x1
        -0x1
        0x16
        -0x1
        -0x1
        -0x1
        0x1a
        -0x1
        0x4
        0x19
        -0x1
        -0x1
        -0x1
        -0x1
        -0x1
        -0x1
        0x10
        -0x1
        0xe
        0x9
        0x7
        -0x1
        -0x1
        0x12
        -0x1
        -0x1
        0x12
        -0x1
        0x14
        -0x1
        -0x1
        -0x1
        -0x1
        -0x1
        0x15
        0xf
        -0x1
        -0x1
        0x1a
        -0x1
        0x1
        0x19
    .end array-data

    .line 687
    :array_3
    .array-data 4
        0x0
        0x180
        0x2e2
        0x445
        0x5c4
        0x726
        0x889
        0xa09
        0xb6b
        0xcce
        0xe4e
        0xfb0
        0x1130
        0x1292
        0x13f4
        0x1574
        0x16d6
        0x1839
        0x19b9
        0x1b1c
        0x1c9c
        0x1dfe
        0x1f60
        0x20e0
        0x2242
        0x23a4
        0x2525
        0x2687
        0x27ea
        0x296a
        0x2acc
        0x2c4b
        0x2dad
        0x2f10
        0x3090
        0x31f3
        0x3355
        0x34d5
        0x3637
        0x37b7
        0x3919
        0x3a7b
        0x3bfb
        0x3d5e
        0x3ec0
        0x4041
        0x41a3
        0x4305
        0x4485
        0x45e7
        0x4767
        0x48c9
        0x4a2c
        0x4bac
        0x4d0e
        0x4e71
        0x4ff1
        0x5153
        0x52d2
        0x5435
        0x5597
        0x5717
        0x587a
        0x59dc
        0x5b5c
        0x5cbf
        0x5e20
        0x5fa0
        0x6103
        0x6283
        0x63e5
        0x6548
        0x66c8
        0x682a
        0x698c
        0x6b0c
        0x6c6e
        0x6dee
        0x6f50
        0x70b3
        0x7233
        0x7396
        0x74f8
        0x7678
        0x77da
        0x795a
        0x7abc
        0x7c1e
        0x7d9e
        0x7f01
        0x8064
        0x81e4
        0x8346
        0x84a8
        0x8627
        0x878a
        0x890a
        0x8a6c
        0x8bcf
        0x8d4f
        0x8eb1
        0x9013
        0x9193
        0x92f5
        0x9458
        0x95d8
        0x973a
        0x98bb
        0x9a1d
        0x9b7f
        0x9cff
        0x9e61
        0x9fc3
        0xa143
        0xa2a6
        0xa426
        0xa588
        0xa6eb
        0xa86b
        0xa9cd
        0xab2f
        0xacaf
        0xae11
        0xaf74
        0xb0f4
        0xb256
        0xb3d6
        0xb538
        0xb69a
        0xb81a
        0xb97d
        0xbadf
        0xbc5f
        0xbdc2
        0xbf42
        0xc0a4
        0xc206
        0xc386
        0xc4e8
        0xc64a
        0xc7ca
        0xc92d
        0xca90
        0xcc10
        0xcd72
        0xcef2
        0xd054
        0xd1b6
        0xd336
        0xd498
        0xd5fb
        0xd77b
        0xd8de
        0xda5e
        0xdbc0
        0xdd22
        0xdea1
        0xe004
        0xe166
        0xe2e6
        0xe449
        0xe5ab
        0xe72b
        0xe88d
        0xea0d
        0xeb6f
        0xecd2
        0xee52
        0xefb4
        0xf117
        0xf297
        0xf3f9
        0xf579
        0xf6db
        0xf83d
        0xf9bd
        0xfb20
        0xfc82
        0xfe02
        0xff65
        0x100c7
        0x10247
        0x103a9
        0x10529
        0x1068b
        0x107ee
        0x1096e
        0x10ad0
        0x10c33
        0x10db2
        0x10f14
        0x11094
        0x111f6
        0x11359
        0x114d9
        0x1163c
        0x1179e
        0x1191e
        0x11a80
        0x11be2
        0x11d62
        0x11ec4
    .end array-data

    .line 711
    :array_4
    .array-data 4
        0x4bd8
        0x4ae0
        0xa570
        0x54d5
        0xd260
        0xd950
        0x5554
        0x56af
        0x9ad0
        0x55d2
        0x4ae0
        0xa5b6
        0xa4d0
        0xd250
        0xd295
        0xb54f
        0xd6a0
        0xada2
        0x95b0
        0x4977
        0x497f
        0xa4b0
        0xb4b5
        0x6a50
        0x6d40
        0xab54
        0x2b6f
        0x9570
        0x52f2
        0x4970
        0x6566
        0xd4a0
        0xea50
        0x6a95
        0x5adf
        0x2b60
        0x86e3
        0x92ef
        0xc8d7
        0xc95f
        0xd4a0
        0xd8a6
        0xb55f
        0x56a0
        0xa5b4
        0x25df
        0x92d0
        0xd2b2
        0xa950
        0xb557
        0x6ca0
        0xb550
        0x5355
        0x4daf
        0xa5b0
        0x4573
        0x52bf
        0xa9a8
        0xe950
        0x6aa0
        0xaea6
        0xab50
        0x4b60
        0xaae4
        0xa570
        0x5260
        0xf263
        0xd950
        0x5b57
        0x56a0
        0x96d0
        0x4dd5
        0x4ad0
        0xa4d0
        0xd4d4
        0xd250
        0xd558
        0xb540
        0xb6a0
        0x95a6
        0x95bf
        0x49b0
        0xa974
        0xa4b0
        0xb27a
        0x6a50
        0x6d40
        0xaf46
        0xab60
        0x9570
        0x4af5
        0x4970
        0x64b0
        0x74a3
        0xea50
        0x6b58
        0x5ac0
        0xab60
        0x96d5
        0x92e0
        0xc960
        0xd954
        0xd4a0
        0xda50
        0x7552
        0x56a0
        0xabb7
        0x25d0
        0x92d0
        0xcab5
        0xa950
        0xb4a0
        0xbaa4
        0xad50
        0x55d9
        0x4ba0
        0xa5b0
        0x5176
        0x52bf
        0xa930
        0x7954
        0x6aa0
        0xad50
        0x5b52
        0x4b60
        0xa6e6
        0xa4e0
        0xd260
        0xea65
        0xd530
        0x5aa0
        0x76a3
        0x96d0
        0x4afb
        0x4ad0
        0xa4d0
        0xd0b6
        0xd25f
        0xd520
        0xdd45
        0xb5a0
        0x56d0
        0x55b2
        0x49b0
        0xa577
        0xa4b0
        0xaa50
        0xb255
        0x6d2f
        0xada0
        0x4b63
        0x937f
        0x49f8
        0x4970
        0x64b0
        0x68a6
        0xea5f
        0x6b20
        0xa6c4
        0xaaef
        0x92e0
        0xd2e3
        0xc960
        0xd557
        0xd4a0
        0xda50
        0x5d55
        0x56a0
        0xa6d0
        0x55d4
        0x52d0
        0xa9b8
        0xa950
        0xb4a0
        0xb6a6
        0xad50
        0x55a0
        0xaba4
        0xa5b0
        0x52b0
        0xb273
        0x6930
        0x7337
        0x6aa0
        0xad50
        0x4b55
        0x4b6f
        0xa570
        0x54e4
        0xd260
        0xe968
        0xd520
        0xdaa0
        0x6aa6
        0x56df
        0x4ae0
        0xa9d4
        0xa4d0
        0xd150
        0xf252
        0xd520
    .end array-data

    .line 735
    :array_5
    .array-data 4
        0x4
        0x13
        0x3
        0x12
        0x4
        0x13
        0x4
        0x13
        0x4
        0x14
        0x4
        0x14
        0x6
        0x16
        0x6
        0x16
        0x6
        0x16
        0x7
        0x16
        0x6
        0x15
        0x6
        0x15
    .end array-data
.end method

.method public constructor <init>()V
    .locals 1

    .prologue
    .line 782
    const/4 v0, 0x0

    invoke-direct {p0, v0}, Lmiui/date/Calendar;-><init>(Ljava/util/TimeZone;)V

    .line 783
    return-void
.end method

.method public constructor <init>(Ljava/util/TimeZone;)V
    .locals 2

    .prologue
    .line 789
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 107
    const/16 v0, 0x19

    new-array v0, v0, [I

    iput-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    .line 1413
    const-wide v0, -0xb1d069b5400L

    iput-wide v0, p0, Lmiui/date/Calendar;->bJ:J

    .line 1415
    const/16 v0, 0x62e

    iput v0, p0, Lmiui/date/Calendar;->bK:I

    .line 1417
    iget v0, p0, Lmiui/date/Calendar;->bK:I

    div-int/lit8 v0, v0, 0x64

    iget v1, p0, Lmiui/date/Calendar;->bK:I

    div-int/lit16 v1, v1, 0x190

    sub-int/2addr v0, v1

    add-int/lit8 v0, v0, -0x2

    iput v0, p0, Lmiui/date/Calendar;->bL:I

    .line 1419
    iget v0, p0, Lmiui/date/Calendar;->bK:I

    add-int/lit16 v0, v0, -0x7d0

    div-int/lit16 v0, v0, 0x190

    iget v1, p0, Lmiui/date/Calendar;->bL:I

    add-int/2addr v0, v1

    iget v1, p0, Lmiui/date/Calendar;->bK:I

    add-int/lit16 v1, v1, -0x7d0

    div-int/lit8 v1, v1, 0x64

    sub-int/2addr v0, v1

    iput v0, p0, Lmiui/date/Calendar;->bM:I

    .line 1422
    const/16 v0, 0xa

    iput v0, p0, Lmiui/date/Calendar;->bN:I

    .line 1424
    const/4 v0, 0x0

    iput v0, p0, Lmiui/date/Calendar;->bO:I

    .line 790
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v0

    iput-wide v0, p0, Lmiui/date/Calendar;->mMillisecond:J

    .line 791
    invoke-virtual {p0, p1}, Lmiui/date/Calendar;->setTimeZone(Ljava/util/TimeZone;)Lmiui/date/Calendar;

    .line 792
    return-void
.end method

.method static a(II)I
    .locals 3

    .prologue
    .line 1937
    sget-object v0, Lmiui/date/Calendar;->bE:[I

    add-int/lit16 v1, p0, -0x76c

    aget v0, v0, v1

    const/high16 v1, 0x10000

    add-int/lit8 v2, p1, 0x1

    shr-int/2addr v1, v2

    and-int/2addr v0, v1

    if-eqz v0, :cond_0

    const/16 v0, 0x1e

    :goto_0
    return v0

    :cond_0
    const/16 v0, 0x1d

    goto :goto_0
.end method

.method private static a(JI)I
    .locals 3

    .prologue
    .line 1926
    int-to-long v0, p2

    rem-long v0, p0, v0

    long-to-int v0, v0

    .line 1927
    const-wide/16 v1, 0x0

    cmp-long v1, p0, v1

    if-gez v1, :cond_0

    if-gez v0, :cond_0

    .line 1928
    add-int/2addr v0, p2

    .line 1930
    :cond_0
    return v0
.end method

.method private static a(ZI)I
    .locals 1

    .prologue
    .line 1893
    if-eqz p0, :cond_0

    const/4 v0, 0x1

    if-ne p1, v0, :cond_0

    .line 1894
    sget-object v0, Lmiui/date/Calendar;->bz:[B

    aget-byte v0, v0, p1

    add-int/lit8 v0, v0, 0x1

    .line 1897
    :goto_0
    return v0

    :cond_0
    sget-object v0, Lmiui/date/Calendar;->bz:[B

    aget-byte v0, v0, p1

    goto :goto_0
.end method

.method private a(J)V
    .locals 10

    .prologue
    const/16 v9, 0x9

    const/16 v8, 0x3c

    const/4 v7, 0x5

    const/4 v6, 0x1

    .line 1318
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    aget v0, v0, v6

    .line 1319
    iget-object v1, p0, Lmiui/date/Calendar;->mFields:[I

    aget v1, v1, v7

    const/4 v2, 0x2

    if-ge v1, v2, :cond_0

    .line 1320
    add-int/lit8 v0, v0, -0x1

    .line 1323
    :cond_0
    iget-object v1, p0, Lmiui/date/Calendar;->mFields:[I

    aget v1, v1, v7

    if-ne v1, v6, :cond_1

    .line 1324
    iget-object v1, p0, Lmiui/date/Calendar;->mFields:[I

    aget v1, v1, v9

    iget-object v2, p0, Lmiui/date/Calendar;->mFields:[I

    aget v2, v2, v6

    invoke-static {v2, v6}, Lmiui/date/Calendar;->b(II)I

    move-result v2

    shr-int/lit8 v2, v2, 0x8

    if-lt v1, v2, :cond_1

    .line 1325
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    aget v0, v0, v6

    .line 1328
    :cond_1
    iget-object v1, p0, Lmiui/date/Calendar;->mFields:[I

    const/4 v2, 0x3

    add-int/lit16 v3, v0, -0x76c

    add-int/lit8 v3, v3, 0xc

    int-to-long v3, v3

    const/16 v5, 0xc

    invoke-static {v3, v4, v5}, Lmiui/date/Calendar;->a(JI)I

    move-result v3

    aput v3, v1, v2

    .line 1329
    iget-object v1, p0, Lmiui/date/Calendar;->mFields:[I

    const/4 v2, 0x4

    add-int/lit16 v0, v0, -0x76c

    add-int/lit8 v0, v0, 0x24

    int-to-long v3, v0

    invoke-static {v3, v4, v8}, Lmiui/date/Calendar;->a(JI)I

    move-result v0

    aput v0, v1, v2

    .line 1331
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    aget v0, v0, v6

    iget-object v1, p0, Lmiui/date/Calendar;->mFields:[I

    aget v1, v1, v7

    invoke-static {v0, v1}, Lmiui/date/Calendar;->b(II)I

    move-result v0

    shr-int/lit8 v1, v0, 0x8

    .line 1332
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    aget v0, v0, v6

    add-int/lit16 v0, v0, -0x76c

    mul-int/lit8 v0, v0, 0xc

    iget-object v2, p0, Lmiui/date/Calendar;->mFields:[I

    aget v2, v2, v7

    add-int/2addr v0, v2

    .line 1333
    iget-object v2, p0, Lmiui/date/Calendar;->mFields:[I

    aget v2, v2, v9

    if-lt v2, v1, :cond_2

    .line 1334
    add-int/lit8 v0, v0, 0x1

    .line 1336
    :cond_2
    iget-object v1, p0, Lmiui/date/Calendar;->mFields:[I

    const/4 v2, 0x7

    add-int/lit8 v0, v0, 0xc

    int-to-long v3, v0

    invoke-static {v3, v4, v8}, Lmiui/date/Calendar;->a(JI)I

    move-result v0

    aput v0, v1, v2

    .line 1338
    const-wide/16 v0, -0x63c1

    sub-long v0, p1, v0

    long-to-int v0, v0

    .line 1339
    iget-object v1, p0, Lmiui/date/Calendar;->mFields:[I

    const/16 v2, 0xb

    add-int/lit8 v3, v0, 0x28

    int-to-long v3, v3

    invoke-static {v3, v4, v8}, Lmiui/date/Calendar;->a(JI)I

    move-result v3

    aput v3, v1, v2

    .line 1341
    iget-object v1, p0, Lmiui/date/Calendar;->mFields:[I

    const/16 v2, 0x13

    mul-int/lit8 v0, v0, 0xc

    iget-object v3, p0, Lmiui/date/Calendar;->mFields:[I

    const/16 v4, 0x12

    aget v3, v3, v4

    add-int/lit8 v3, v3, 0x1

    rem-int/lit8 v3, v3, 0x18

    div-int/lit8 v3, v3, 0x2

    add-int/2addr v0, v3

    int-to-long v3, v0

    invoke-static {v3, v4, v8}, Lmiui/date/Calendar;->a(JI)I

    move-result v0

    aput v0, v1, v2

    .line 1342
    return-void
.end method

.method private a(JJ)V
    .locals 5

    .prologue
    const/4 v3, 0x1

    .line 1822
    invoke-direct {p0, p1, p2, p3, p4}, Lmiui/date/Calendar;->b(JJ)I

    move-result v0

    .line 1823
    iget-object v1, p0, Lmiui/date/Calendar;->mFields:[I

    const/16 v2, 0xc

    aput v0, v1, v2

    .line 1824
    iget-object v1, p0, Lmiui/date/Calendar;->mFields:[I

    aget v1, v1, v3

    iget v2, p0, Lmiui/date/Calendar;->bK:I

    if-ne v1, v2, :cond_0

    iget-wide v1, p0, Lmiui/date/Calendar;->bJ:J

    cmp-long v1, v1, p3

    if-gtz v1, :cond_0

    .line 1825
    iget v1, p0, Lmiui/date/Calendar;->bN:I

    add-int/2addr v0, v1

    .line 1827
    :cond_0
    div-int/lit8 v1, v0, 0x20

    .line 1828
    iget-object v2, p0, Lmiui/date/Calendar;->mFields:[I

    aget v2, v2, v3

    invoke-virtual {p0, v2}, Lmiui/date/Calendar;->isLeapYear(I)Z

    move-result v2

    .line 1829
    invoke-static {v2, v1}, Lmiui/date/Calendar;->b(ZI)I

    move-result v3

    sub-int/2addr v0, v3

    .line 1830
    invoke-static {v2, v1}, Lmiui/date/Calendar;->a(ZI)I

    move-result v3

    if-le v0, v3, :cond_1

    .line 1831
    invoke-static {v2, v1}, Lmiui/date/Calendar;->a(ZI)I

    move-result v2

    sub-int/2addr v0, v2

    .line 1832
    add-int/lit8 v1, v1, 0x1

    .line 1835
    :cond_1
    iget-object v2, p0, Lmiui/date/Calendar;->mFields:[I

    const/4 v3, 0x5

    aput v1, v2, v3

    .line 1836
    iget-object v1, p0, Lmiui/date/Calendar;->mFields:[I

    const/16 v2, 0x9

    aput v0, v1, v2

    .line 1837
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    const/16 v1, 0xe

    const-wide/16 v2, 0x3

    sub-long v2, p1, v2

    const/4 v4, 0x7

    invoke-static {v2, v3, v4}, Lmiui/date/Calendar;->a(JI)I

    move-result v2

    add-int/lit8 v2, v2, 0x1

    aput v2, v0, v1

    .line 1838
    return-void
.end method

.method private static a(Ljava/lang/StringBuilder;II)V
    .locals 3

    .prologue
    const/16 v2, 0x30

    .line 2225
    const/16 v0, 0x2710

    if-lt p2, v0, :cond_1

    .line 2226
    invoke-static {p2}, Ljava/lang/Integer;->toString(I)Ljava/lang/String;

    move-result-object v1

    .line 2227
    invoke-virtual {v1}, Ljava/lang/String;->length()I

    move-result v0

    :goto_0
    if-ge v0, p1, :cond_0

    .line 2228
    invoke-virtual {p0, v2}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    .line 2227
    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    .line 2230
    :cond_0
    invoke-virtual {p0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 2248
    :goto_1
    return-void

    .line 2233
    :cond_1
    const/16 v0, 0x3e8

    if-lt p2, v0, :cond_2

    .line 2234
    const/4 v0, 0x4

    .line 2243
    :goto_2
    if-ge v0, p1, :cond_5

    .line 2244
    invoke-virtual {p0, v2}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    .line 2243
    add-int/lit8 v0, v0, 0x1

    goto :goto_2

    .line 2235
    :cond_2
    const/16 v0, 0x64

    if-lt p2, v0, :cond_3

    .line 2236
    const/4 v0, 0x3

    goto :goto_2

    .line 2237
    :cond_3
    const/16 v0, 0xa

    if-lt p2, v0, :cond_4

    .line 2238
    const/4 v0, 0x2

    goto :goto_2

    .line 2240
    :cond_4
    const/4 v0, 0x1

    goto :goto_2

    .line 2246
    :cond_5
    invoke-virtual {p0, p2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    goto :goto_1
.end method

.method private a(Ljava/lang/StringBuilder;Lmiui/date/CalendarFormatSymbols;CII)V
    .locals 6

    .prologue
    const/4 v5, 0x0

    const/4 v4, 0x4

    const/4 v1, 0x1

    const/4 v3, 0x5

    const/4 v2, 0x2

    .line 2101
    packed-switch p3, :pswitch_data_0

    .line 2222
    :cond_0
    :goto_0
    :pswitch_0
    return-void

    .line 2103
    :pswitch_1
    invoke-virtual {p2}, Lmiui/date/CalendarFormatSymbols;->getChineseSymbolAnimals()[Ljava/lang/String;

    move-result-object v0

    iget-object v1, p0, Lmiui/date/Calendar;->mFields:[I

    const/4 v2, 0x3

    aget v1, v1, v2

    aget-object v0, v0, v1

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    goto :goto_0

    .line 2111
    :pswitch_2
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    aget v0, v0, p5

    invoke-static {p1, p4, v0}, Lmiui/date/Calendar;->a(Ljava/lang/StringBuilder;II)V

    goto :goto_0

    .line 2115
    :pswitch_3
    if-ne p4, v4, :cond_1

    .line 2116
    invoke-virtual {p2}, Lmiui/date/CalendarFormatSymbols;->getWeekDays()[Ljava/lang/String;

    move-result-object v0

    iget-object v1, p0, Lmiui/date/Calendar;->mFields:[I

    const/16 v2, 0xe

    aget v1, v1, v2

    add-int/lit8 v1, v1, -0x1

    aget-object v0, v0, v1

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    goto :goto_0

    .line 2117
    :cond_1
    if-ne p4, v3, :cond_2

    .line 2118
    invoke-virtual {p2}, Lmiui/date/CalendarFormatSymbols;->getShortestWeekDays()[Ljava/lang/String;

    move-result-object v0

    iget-object v1, p0, Lmiui/date/Calendar;->mFields:[I

    const/16 v2, 0xe

    aget v1, v1, v2

    add-int/lit8 v1, v1, -0x1

    aget-object v0, v0, v1

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    goto :goto_0

    .line 2120
    :cond_2
    invoke-virtual {p2}, Lmiui/date/CalendarFormatSymbols;->getShortWeekDays()[Ljava/lang/String;

    move-result-object v0

    iget-object v1, p0, Lmiui/date/Calendar;->mFields:[I

    const/16 v2, 0xe

    aget v1, v1, v2

    add-int/lit8 v1, v1, -0x1

    aget-object v0, v0, v1

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    goto :goto_0

    .line 2129
    :pswitch_4
    invoke-virtual {p2}, Lmiui/date/CalendarFormatSymbols;->getEras()[Ljava/lang/String;

    move-result-object v0

    iget-object v1, p0, Lmiui/date/Calendar;->mFields:[I

    aget v1, v1, v5

    aget-object v0, v0, v1

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    goto :goto_0

    .line 2132
    :pswitch_5
    if-ne p4, v2, :cond_3

    .line 2133
    invoke-virtual {p2}, Lmiui/date/CalendarFormatSymbols;->getHeavenlyStems()[Ljava/lang/String;

    move-result-object v0

    iget-object v1, p0, Lmiui/date/Calendar;->mFields:[I

    const/16 v2, 0x13

    aget v1, v1, v2

    rem-int/lit8 v1, v1, 0xa

    aget-object v0, v0, v1

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 2135
    :cond_3
    invoke-virtual {p2}, Lmiui/date/CalendarFormatSymbols;->getEarthlyBranches()[Ljava/lang/String;

    move-result-object v0

    iget-object v1, p0, Lmiui/date/Calendar;->mFields:[I

    const/16 v2, 0x13

    aget v1, v1, v2

    rem-int/lit8 v1, v1, 0xc

    aget-object v0, v0, v1

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    goto/16 :goto_0

    .line 2138
    :pswitch_6
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    const/16 v1, 0x12

    aget v0, v0, v1

    rem-int/lit8 v0, v0, 0xc

    invoke-static {p1, p4, v0}, Lmiui/date/Calendar;->a(Ljava/lang/StringBuilder;II)V

    goto/16 :goto_0

    .line 2142
    :pswitch_7
    const/4 v0, 0x3

    if-ge p4, v0, :cond_4

    .line 2143
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    aget v0, v0, v3

    add-int/lit8 v0, v0, 0x1

    invoke-static {p1, p4, v0}, Lmiui/date/Calendar;->a(Ljava/lang/StringBuilder;II)V

    goto/16 :goto_0

    .line 2144
    :cond_4
    if-ne p4, v4, :cond_5

    .line 2145
    invoke-virtual {p2}, Lmiui/date/CalendarFormatSymbols;->getMonths()[Ljava/lang/String;

    move-result-object v0

    iget-object v1, p0, Lmiui/date/Calendar;->mFields:[I

    aget v1, v1, v3

    aget-object v0, v0, v1

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    goto/16 :goto_0

    .line 2146
    :cond_5
    if-ne p4, v3, :cond_6

    .line 2147
    invoke-virtual {p2}, Lmiui/date/CalendarFormatSymbols;->getShortestMonths()[Ljava/lang/String;

    move-result-object v0

    iget-object v1, p0, Lmiui/date/Calendar;->mFields:[I

    aget v1, v1, v3

    aget-object v0, v0, v1

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    goto/16 :goto_0

    .line 2149
    :cond_6
    invoke-virtual {p2}, Lmiui/date/CalendarFormatSymbols;->getShortMonths()[Ljava/lang/String;

    move-result-object v0

    iget-object v1, p0, Lmiui/date/Calendar;->mFields:[I

    aget v1, v1, v3

    aget-object v0, v0, v1

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    goto/16 :goto_0

    .line 2153
    :pswitch_8
    if-eq p4, v2, :cond_7

    .line 2154
    invoke-virtual {p2}, Lmiui/date/CalendarFormatSymbols;->getChineseLeapMonths()[Ljava/lang/String;

    move-result-object v0

    iget-object v1, p0, Lmiui/date/Calendar;->mFields:[I

    const/16 v2, 0x8

    aget v1, v1, v2

    aget-object v0, v0, v1

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 2155
    invoke-virtual {p2}, Lmiui/date/CalendarFormatSymbols;->getChineseMonths()[Ljava/lang/String;

    move-result-object v0

    iget-object v1, p0, Lmiui/date/Calendar;->mFields:[I

    const/4 v2, 0x6

    aget v1, v1, v2

    aget-object v0, v0, v1

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    goto/16 :goto_0

    .line 2157
    :cond_7
    invoke-virtual {p2}, Lmiui/date/CalendarFormatSymbols;->getHeavenlyStems()[Ljava/lang/String;

    move-result-object v0

    iget-object v1, p0, Lmiui/date/Calendar;->mFields:[I

    const/4 v2, 0x7

    aget v1, v1, v2

    rem-int/lit8 v1, v1, 0xa

    aget-object v0, v0, v1

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 2158
    invoke-virtual {p2}, Lmiui/date/CalendarFormatSymbols;->getEarthlyBranches()[Ljava/lang/String;

    move-result-object v0

    iget-object v1, p0, Lmiui/date/Calendar;->mFields:[I

    const/4 v2, 0x7

    aget v1, v1, v2

    rem-int/lit8 v1, v1, 0xc

    aget-object v0, v0, v1

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    goto/16 :goto_0

    .line 2162
    :pswitch_9
    if-eq p4, v2, :cond_8

    .line 2163
    invoke-virtual {p2}, Lmiui/date/CalendarFormatSymbols;->getChineseDigits()[Ljava/lang/String;

    move-result-object v1

    .line 2164
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    aget v0, v0, v2

    .line 2165
    invoke-virtual {p1}, Ljava/lang/StringBuilder;->length()I

    move-result v2

    .line 2166
    :goto_1
    if-lez v0, :cond_0

    .line 2167
    rem-int/lit8 v3, v0, 0xa

    .line 2168
    div-int/lit8 v0, v0, 0xa

    .line 2169
    aget-object v3, v1, v3

    invoke-virtual {p1, v2, v3}, Ljava/lang/StringBuilder;->insert(ILjava/lang/String;)Ljava/lang/StringBuilder;

    goto :goto_1

    .line 2172
    :cond_8
    invoke-virtual {p2}, Lmiui/date/CalendarFormatSymbols;->getHeavenlyStems()[Ljava/lang/String;

    move-result-object v0

    iget-object v1, p0, Lmiui/date/Calendar;->mFields:[I

    aget v1, v1, v4

    rem-int/lit8 v1, v1, 0xa

    aget-object v0, v0, v1

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 2173
    invoke-virtual {p2}, Lmiui/date/CalendarFormatSymbols;->getEarthlyBranches()[Ljava/lang/String;

    move-result-object v0

    iget-object v1, p0, Lmiui/date/Calendar;->mFields:[I

    aget v1, v1, v4

    rem-int/lit8 v1, v1, 0xc

    aget-object v0, v0, v1

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    goto/16 :goto_0

    .line 2177
    :pswitch_a
    if-ne p4, v4, :cond_9

    .line 2178
    invoke-direct {p0, p1, v1, v1}, Lmiui/date/Calendar;->a(Ljava/lang/StringBuilder;ZZ)V

    goto/16 :goto_0

    .line 2179
    :cond_9
    if-ne p4, v3, :cond_a

    .line 2180
    invoke-direct {p0, p1, v5, v1}, Lmiui/date/Calendar;->a(Ljava/lang/StringBuilder;ZZ)V

    goto/16 :goto_0

    .line 2182
    :cond_a
    invoke-direct {p0, p1, v5, v5}, Lmiui/date/Calendar;->a(Ljava/lang/StringBuilder;ZZ)V

    goto/16 :goto_0

    .line 2186
    :pswitch_b
    if-eq p4, v2, :cond_b

    .line 2187
    invoke-virtual {p2}, Lmiui/date/CalendarFormatSymbols;->getAmPms()[Ljava/lang/String;

    move-result-object v0

    iget-object v1, p0, Lmiui/date/Calendar;->mFields:[I

    const/16 v2, 0x11

    aget v1, v1, v2

    aget-object v0, v0, v1

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    goto/16 :goto_0

    .line 2189
    :cond_b
    invoke-virtual {p2}, Lmiui/date/CalendarFormatSymbols;->getDetailedAmPms()[Ljava/lang/String;

    move-result-object v0

    iget-object v1, p0, Lmiui/date/Calendar;->mFields:[I

    const/16 v2, 0x10

    aget v1, v1, v2

    aget-object v0, v0, v1

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    goto/16 :goto_0

    .line 2193
    :pswitch_c
    if-eq p4, v2, :cond_c

    .line 2194
    invoke-virtual {p2}, Lmiui/date/CalendarFormatSymbols;->getChineseDays()[Ljava/lang/String;

    move-result-object v0

    iget-object v1, p0, Lmiui/date/Calendar;->mFields:[I

    const/16 v2, 0xa

    aget v1, v1, v2

    add-int/lit8 v1, v1, -0x1

    aget-object v0, v0, v1

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    goto/16 :goto_0

    .line 2196
    :cond_c
    invoke-virtual {p2}, Lmiui/date/CalendarFormatSymbols;->getHeavenlyStems()[Ljava/lang/String;

    move-result-object v0

    iget-object v1, p0, Lmiui/date/Calendar;->mFields:[I

    const/16 v2, 0xb

    aget v1, v1, v2

    rem-int/lit8 v1, v1, 0xa

    aget-object v0, v0, v1

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 2197
    invoke-virtual {p2}, Lmiui/date/CalendarFormatSymbols;->getEarthlyBranches()[Ljava/lang/String;

    move-result-object v0

    iget-object v1, p0, Lmiui/date/Calendar;->mFields:[I

    const/16 v2, 0xb

    aget v1, v1, v2

    rem-int/lit8 v1, v1, 0xc

    aget-object v0, v0, v1

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    goto/16 :goto_0

    .line 2201
    :pswitch_d
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    const/16 v1, 0x12

    aget v0, v0, v1

    rem-int/lit8 v0, v0, 0xc

    .line 2202
    if-nez v0, :cond_d

    const/16 v0, 0xc

    :cond_d
    invoke-static {p1, p4, v0}, Lmiui/date/Calendar;->a(Ljava/lang/StringBuilder;II)V

    goto/16 :goto_0

    .line 2206
    :pswitch_e
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    const/16 v1, 0x12

    aget v0, v0, v1

    if-nez v0, :cond_e

    const/16 v0, 0x18

    :goto_2
    invoke-static {p1, p4, v0}, Lmiui/date/Calendar;->a(Ljava/lang/StringBuilder;II)V

    goto/16 :goto_0

    :cond_e
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    const/16 v1, 0x12

    aget v0, v0, v1

    goto :goto_2

    .line 2209
    :pswitch_f
    invoke-virtual {p2}, Lmiui/date/CalendarFormatSymbols;->getSolarTerms()[Ljava/lang/String;

    move-result-object v0

    iget-object v1, p0, Lmiui/date/Calendar;->mFields:[I

    const/16 v2, 0xf

    aget v1, v1, v2

    aget-object v0, v0, v1

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    goto/16 :goto_0

    .line 2212
    :pswitch_10
    if-ne p4, v2, :cond_f

    .line 2213
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    aget v0, v0, v1

    rem-int/lit8 v0, v0, 0x64

    invoke-static {p1, p4, v0}, Lmiui/date/Calendar;->a(Ljava/lang/StringBuilder;II)V

    goto/16 :goto_0

    .line 2215
    :cond_f
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    aget v0, v0, v1

    invoke-static {p1, p4, v0}, Lmiui/date/Calendar;->a(Ljava/lang/StringBuilder;II)V

    goto/16 :goto_0

    .line 2219
    :pswitch_11
    invoke-direct {p0, p1, p2, p4}, Lmiui/date/Calendar;->a(Ljava/lang/StringBuilder;Lmiui/date/CalendarFormatSymbols;I)V

    goto/16 :goto_0

    .line 2101
    :pswitch_data_0
    .packed-switch 0x41
        :pswitch_1
        :pswitch_0
        :pswitch_0
        :pswitch_2
        :pswitch_3
        :pswitch_0
        :pswitch_4
        :pswitch_2
        :pswitch_5
        :pswitch_0
        :pswitch_6
        :pswitch_7
        :pswitch_7
        :pswitch_8
        :pswitch_0
        :pswitch_0
        :pswitch_0
        :pswitch_0
        :pswitch_2
        :pswitch_0
        :pswitch_0
        :pswitch_0
        :pswitch_0
        :pswitch_0
        :pswitch_9
        :pswitch_a
        :pswitch_0
        :pswitch_0
        :pswitch_0
        :pswitch_0
        :pswitch_0
        :pswitch_0
        :pswitch_b
        :pswitch_0
        :pswitch_3
        :pswitch_2
        :pswitch_c
        :pswitch_0
        :pswitch_0
        :pswitch_d
        :pswitch_0
        :pswitch_0
        :pswitch_e
        :pswitch_0
        :pswitch_2
        :pswitch_0
        :pswitch_0
        :pswitch_0
        :pswitch_0
        :pswitch_0
        :pswitch_2
        :pswitch_f
        :pswitch_0
        :pswitch_0
        :pswitch_0
        :pswitch_0
        :pswitch_10
        :pswitch_11
    .end packed-switch
.end method

.method private a(Ljava/lang/StringBuilder;Lmiui/date/CalendarFormatSymbols;I)V
    .locals 5

    .prologue
    const/4 v0, 0x1

    const/4 v1, 0x0

    .line 2251
    iget-object v3, p0, Lmiui/date/Calendar;->mTimeZone:Ljava/util/TimeZone;

    iget-object v2, p0, Lmiui/date/Calendar;->mFields:[I

    const/16 v4, 0x18

    aget v2, v2, v4

    if-eqz v2, :cond_1

    move v2, v0

    :goto_0
    const/4 v4, 0x4

    if-eq p3, v4, :cond_0

    move v0, v1

    :cond_0
    invoke-virtual {p2}, Lmiui/date/CalendarFormatSymbols;->getLocale()Ljava/util/Locale;

    move-result-object v4

    invoke-virtual {v3, v2, v0, v4}, Ljava/util/TimeZone;->getDisplayName(ZILjava/util/Locale;)Ljava/lang/String;

    move-result-object v0

    .line 2254
    if-eqz v0, :cond_2

    .line 2255
    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 2259
    :goto_1
    return-void

    :cond_1
    move v2, v1

    .line 2251
    goto :goto_0

    .line 2257
    :cond_2
    invoke-direct {p0, p1, v1, v1}, Lmiui/date/Calendar;->a(Ljava/lang/StringBuilder;ZZ)V

    goto :goto_1
.end method

.method private a(Ljava/lang/StringBuilder;ZZ)V
    .locals 5

    .prologue
    const v4, 0x36ee80

    const/4 v3, 0x2

    .line 2266
    const/16 v0, 0x17

    invoke-virtual {p0, v0}, Lmiui/date/Calendar;->get(I)I

    move-result v0

    const/16 v1, 0x18

    invoke-virtual {p0, v1}, Lmiui/date/Calendar;->get(I)I

    move-result v1

    add-int/2addr v1, v0

    .line 2267
    const/16 v0, 0x2b

    .line 2268
    if-gez v1, :cond_0

    .line 2269
    const/16 v0, 0x2d

    .line 2270
    neg-int v1, v1

    .line 2272
    :cond_0
    if-eqz p2, :cond_1

    .line 2273
    const-string v2, "GMT"

    invoke-virtual {p1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 2275
    :cond_1
    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    .line 2276
    div-int v0, v1, v4

    invoke-static {p1, v3, v0}, Lmiui/date/Calendar;->a(Ljava/lang/StringBuilder;II)V

    .line 2277
    if-eqz p3, :cond_2

    .line 2278
    const/16 v0, 0x3a

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    .line 2280
    :cond_2
    rem-int v0, v1, v4

    const v1, 0xea60

    div-int/2addr v0, v1

    invoke-static {p1, v3, v0}, Lmiui/date/Calendar;->a(Ljava/lang/StringBuilder;II)V

    .line 2281
    return-void
.end method

.method static b(II)I
    .locals 4

    .prologue
    .line 1975
    const/16 v0, 0x834

    if-le p0, v0, :cond_0

    .line 1976
    const/4 v0, 0x0

    .line 1985
    :goto_0
    return v0

    .line 1979
    :cond_0
    sget-object v0, Lmiui/date/Calendar;->bG:[B

    add-int/lit16 v1, p0, -0x76c

    aget-byte v0, v0, v1

    add-int/lit8 v0, v0, -0x30

    .line 1980
    mul-int/lit8 v1, p1, 0x2

    .line 1981
    mul-int/lit8 v0, v0, 0x18

    add-int/2addr v0, v1

    .line 1982
    add-int/lit8 v2, v0, 0x1

    .line 1983
    sget-object v3, Lmiui/date/Calendar;->bH:[B

    aget-byte v0, v3, v0

    add-int/lit8 v0, v0, -0x30

    sget-object v3, Lmiui/date/Calendar;->bF:[I

    aget v3, v3, v1

    add-int/2addr v0, v3

    .line 1984
    sget-object v3, Lmiui/date/Calendar;->bH:[B

    aget-byte v2, v3, v2

    add-int/lit8 v2, v2, -0x30

    sget-object v3, Lmiui/date/Calendar;->bF:[I

    add-int/lit8 v1, v1, 0x1

    aget v1, v3, v1

    add-int/2addr v1, v2

    .line 1985
    shl-int/lit8 v0, v0, 0x8

    add-int/2addr v0, v1

    goto :goto_0
.end method

.method private b(JJ)I
    .locals 5

    .prologue
    .line 1845
    const/16 v2, 0x7b2

    .line 1847
    iget-wide v0, p0, Lmiui/date/Calendar;->bJ:J

    cmp-long v0, p3, v0

    if-gez v0, :cond_2

    .line 1848
    iget v0, p0, Lmiui/date/Calendar;->bM:I

    int-to-long v0, v0

    sub-long v0, p1, v0

    .line 1852
    :goto_0
    const-wide/16 v3, 0x16d

    div-long v3, v0, v3

    long-to-int v3, v3

    if-eqz v3, :cond_0

    .line 1853
    add-int/2addr v2, v3

    .line 1854
    int-to-long v0, v2

    invoke-direct {p0, v0, v1}, Lmiui/date/Calendar;->c(J)J

    move-result-wide v0

    sub-long v0, p1, v0

    goto :goto_0

    .line 1856
    :cond_0
    const-wide/16 v3, 0x0

    cmp-long v3, v0, v3

    if-gez v3, :cond_1

    .line 1857
    add-int/lit8 v2, v2, -0x1

    .line 1858
    invoke-direct {p0, v2}, Lmiui/date/Calendar;->j(I)I

    move-result v3

    int-to-long v3, v3

    add-long/2addr v0, v3

    .line 1860
    :cond_1
    iget-object v3, p0, Lmiui/date/Calendar;->mFields:[I

    const/4 v4, 0x1

    aput v2, v3, v4

    .line 1861
    long-to-int v0, v0

    add-int/lit8 v0, v0, 0x1

    return v0

    :cond_2
    move-wide v0, p1

    goto :goto_0
.end method

.method private static b(ZI)I
    .locals 1

    .prologue
    .line 1918
    if-eqz p0, :cond_0

    const/4 v0, 0x1

    if-le p1, v0, :cond_0

    .line 1919
    sget-object v0, Lmiui/date/Calendar;->bA:[I

    aget v0, v0, p1

    add-int/lit8 v0, v0, 0x1

    .line 1922
    :goto_0
    return v0

    :cond_0
    sget-object v0, Lmiui/date/Calendar;->bA:[I

    aget v0, v0, p1

    goto :goto_0
.end method

.method private b(J)V
    .locals 10

    .prologue
    const/16 v0, 0x834

    const/4 v4, 0x1

    const/4 v2, 0x0

    .line 1356
    const-wide/16 v5, -0x63c1

    sub-long v5, p1, v5

    long-to-int v1, v5

    .line 1358
    iget-object v3, p0, Lmiui/date/Calendar;->mFields:[I

    aget v3, v3, v4

    if-lt v3, v0, :cond_3

    .line 1359
    :goto_0
    sget-object v3, Lmiui/date/Calendar;->bD:[I

    add-int/lit16 v5, v0, -0x76c

    aget v3, v3, v5

    sub-int/2addr v1, v3

    .line 1360
    if-gez v1, :cond_0

    .line 1361
    add-int/lit8 v0, v0, -0x1

    .line 1362
    invoke-static {v0}, Lmiui/date/Calendar;->k(I)I

    move-result v3

    add-int/2addr v1, v3

    .line 1364
    :cond_0
    if-gez v1, :cond_1

    .line 1365
    add-int/lit8 v0, v0, -0x1

    .line 1366
    invoke-static {v0}, Lmiui/date/Calendar;->k(I)I

    move-result v3

    add-int/2addr v1, v3

    .line 1369
    :cond_1
    iget-object v3, p0, Lmiui/date/Calendar;->mFields:[I

    const/4 v5, 0x2

    aput v0, v3, v5

    .line 1370
    iget-object v3, p0, Lmiui/date/Calendar;->mFields:[I

    const/16 v5, 0xd

    add-int/lit8 v6, v1, 0x1

    aput v6, v3, v5

    .line 1373
    invoke-static {v0}, Lmiui/date/Calendar;->m(I)I

    move-result v8

    move v6, v2

    move v5, v2

    move v3, v1

    move v1, v2

    .line 1377
    :goto_1
    const/16 v7, 0xc

    if-ge v1, v7, :cond_5

    if-lez v3, :cond_5

    .line 1378
    if-ltz v8, :cond_4

    add-int/lit8 v6, v8, 0x1

    if-ne v1, v6, :cond_4

    if-nez v5, :cond_4

    .line 1379
    add-int/lit8 v5, v1, -0x1

    .line 1381
    invoke-static {v0}, Lmiui/date/Calendar;->l(I)I

    move-result v1

    move v6, v5

    move v5, v1

    move v1, v4

    .line 1386
    :goto_2
    if-eqz v1, :cond_2

    add-int/lit8 v7, v8, 0x1

    if-ne v6, v7, :cond_2

    move v1, v2

    .line 1390
    :cond_2
    sub-int v7, v3, v5

    .line 1377
    add-int/lit8 v3, v6, 0x1

    move v6, v5

    move v5, v1

    move v1, v3

    move v3, v7

    goto :goto_1

    .line 1358
    :cond_3
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    aget v0, v0, v4

    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    .line 1383
    :cond_4
    invoke-static {v0, v1}, Lmiui/date/Calendar;->a(II)I

    move-result v6

    move v9, v6

    move v6, v1

    move v1, v5

    move v5, v9

    goto :goto_2

    .line 1393
    :cond_5
    if-nez v3, :cond_9

    if-lez v8, :cond_9

    add-int/lit8 v0, v8, 0x1

    if-ne v1, v0, :cond_9

    .line 1394
    if-eqz v5, :cond_7

    move v0, v1

    move v5, v2

    .line 1402
    :goto_3
    if-gez v3, :cond_8

    .line 1403
    add-int v1, v3, v6

    .line 1404
    add-int/lit8 v0, v0, -0x1

    .line 1406
    :goto_4
    iget-object v3, p0, Lmiui/date/Calendar;->mFields:[I

    const/16 v6, 0x8

    if-eqz v5, :cond_6

    move v2, v4

    :cond_6
    aput v2, v3, v6

    .line 1407
    iget-object v2, p0, Lmiui/date/Calendar;->mFields:[I

    const/4 v3, 0x6

    aput v0, v2, v3

    .line 1408
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    const/16 v2, 0xa

    add-int/lit8 v1, v1, 0x1

    aput v1, v0, v2

    .line 1409
    return-void

    .line 1398
    :cond_7
    add-int/lit8 v0, v1, -0x1

    move v5, v4

    goto :goto_3

    :cond_8
    move v1, v3

    goto :goto_4

    :cond_9
    move v0, v1

    goto :goto_3
.end method

.method private c(J)J
    .locals 12

    .prologue
    const-wide/16 v10, 0x190

    const-wide/16 v8, 0x64

    const-wide/16 v6, 0x16d

    const-wide/16 v4, 0x4

    const-wide/16 v2, 0x7b2

    .line 1868
    cmp-long v0, p1, v2

    if-ltz v0, :cond_3

    .line 1869
    sub-long v0, p1, v2

    mul-long/2addr v0, v6

    const-wide/16 v2, 0x7b1

    sub-long v2, p1, v2

    div-long/2addr v2, v4

    add-long/2addr v0, v2

    .line 1870
    iget v2, p0, Lmiui/date/Calendar;->bK:I

    int-to-long v2, v2

    cmp-long v2, p1, v2

    if-lez v2, :cond_0

    .line 1871
    const-wide/16 v2, 0x76d

    sub-long v2, p1, v2

    div-long/2addr v2, v8

    const-wide/16 v4, 0x641

    sub-long v4, p1, v4

    div-long/2addr v4, v10

    sub-long/2addr v2, v4

    sub-long/2addr v0, v2

    .line 1885
    :goto_0
    return-wide v0

    .line 1873
    :cond_0
    iget v2, p0, Lmiui/date/Calendar;->bK:I

    int-to-long v2, v2

    cmp-long v2, p1, v2

    if-nez v2, :cond_1

    .line 1874
    iget v2, p0, Lmiui/date/Calendar;->bN:I

    int-to-long v2, v2

    add-long/2addr v0, v2

    goto :goto_0

    .line 1875
    :cond_1
    iget v2, p0, Lmiui/date/Calendar;->bK:I

    add-int/lit8 v2, v2, -0x1

    int-to-long v2, v2

    cmp-long v2, p1, v2

    if-nez v2, :cond_2

    .line 1876
    iget v2, p0, Lmiui/date/Calendar;->bO:I

    int-to-long v2, v2

    add-long/2addr v0, v2

    goto :goto_0

    .line 1878
    :cond_2
    iget v2, p0, Lmiui/date/Calendar;->bM:I

    int-to-long v2, v2

    add-long/2addr v0, v2

    goto :goto_0

    .line 1882
    :cond_3
    iget v0, p0, Lmiui/date/Calendar;->bK:I

    int-to-long v0, v0

    cmp-long v0, p1, v0

    if-gtz v0, :cond_4

    .line 1883
    sub-long v0, p1, v2

    mul-long/2addr v0, v6

    const-wide/16 v2, 0x7b4

    sub-long v2, p1, v2

    div-long/2addr v2, v4

    add-long/2addr v0, v2

    iget v2, p0, Lmiui/date/Calendar;->bM:I

    int-to-long v2, v2

    add-long/2addr v0, v2

    goto :goto_0

    .line 1885
    :cond_4
    sub-long v0, p1, v2

    mul-long/2addr v0, v6

    const-wide/16 v2, 0x7b4

    sub-long v2, p1, v2

    div-long/2addr v2, v4

    add-long/2addr v0, v2

    const-wide/16 v2, 0x7d0

    sub-long v2, p1, v2

    div-long/2addr v2, v8

    sub-long/2addr v0, v2

    const-wide/16 v2, 0x7d0

    sub-long v2, p1, v2

    div-long/2addr v2, v10

    add-long/2addr v0, v2

    goto :goto_0
.end method

.method private j(I)I
    .locals 2

    .prologue
    .line 1904
    invoke-virtual {p0, p1}, Lmiui/date/Calendar;->isLeapYear(I)Z

    move-result v0

    if-eqz v0, :cond_2

    const/16 v0, 0x16e

    .line 1905
    :goto_0
    iget v1, p0, Lmiui/date/Calendar;->bK:I

    if-ne p1, v1, :cond_0

    .line 1906
    iget v1, p0, Lmiui/date/Calendar;->bN:I

    sub-int/2addr v0, v1

    .line 1908
    :cond_0
    iget v1, p0, Lmiui/date/Calendar;->bK:I

    add-int/lit8 v1, v1, -0x1

    if-ne p1, v1, :cond_1

    .line 1909
    iget v1, p0, Lmiui/date/Calendar;->bO:I

    sub-int/2addr v0, v1

    .line 1911
    :cond_1
    return v0

    .line 1904
    :cond_2
    const/16 v0, 0x16d

    goto :goto_0
.end method

.method static k(I)I
    .locals 3

    .prologue
    .line 1945
    sget-object v0, Lmiui/date/Calendar;->bD:[I

    add-int/lit16 v1, p0, -0x76c

    add-int/lit8 v1, v1, 0x1

    aget v0, v0, v1

    sget-object v1, Lmiui/date/Calendar;->bD:[I

    add-int/lit16 v2, p0, -0x76c

    aget v1, v1, v2

    sub-int/2addr v0, v1

    return v0
.end method

.method static l(I)I
    .locals 2

    .prologue
    .line 1955
    invoke-static {p0}, Lmiui/date/Calendar;->m(I)I

    move-result v0

    if-ltz v0, :cond_1

    sget-object v0, Lmiui/date/Calendar;->bE:[I

    add-int/lit16 v1, p0, -0x76c

    add-int/lit8 v1, v1, 0x1

    aget v0, v0, v1

    and-int/lit8 v0, v0, 0xf

    const/16 v1, 0xf

    if-ne v0, v1, :cond_0

    const/16 v0, 0x1e

    :goto_0
    return v0

    :cond_0
    const/16 v0, 0x1d

    goto :goto_0

    :cond_1
    const/4 v0, 0x0

    goto :goto_0
.end method

.method private static m(I)I
    .locals 2

    .prologue
    .line 1964
    sget-object v0, Lmiui/date/Calendar;->bE:[I

    add-int/lit16 v1, p0, -0x76c

    aget v0, v0, v1

    and-int/lit8 v0, v0, 0xf

    .line 1965
    const/16 v1, 0xf

    if-ne v0, v1, :cond_0

    const/4 v0, -0x1

    :goto_0
    return v0

    :cond_0
    add-int/lit8 v0, v0, -0x1

    goto :goto_0
.end method

.method private n(I)I
    .locals 8

    .prologue
    const/4 v7, 0x0

    const/4 v1, 0x1

    .line 2284
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    aget v0, v0, v1

    if-gtz v0, :cond_1

    move v0, v7

    .line 2286
    :goto_0
    iget-object v2, p0, Lmiui/date/Calendar;->mFields:[I

    aget v1, v2, v1

    if-lez v1, :cond_0

    .line 2287
    iget-object v1, p0, Lmiui/date/Calendar;->mFields:[I

    const/16 v2, 0x17

    aget v1, v1, v2

    sub-int v7, v0, v1

    .line 2290
    :cond_0
    return v7

    .line 2284
    :cond_1
    iget-object v0, p0, Lmiui/date/Calendar;->mTimeZone:Ljava/util/TimeZone;

    iget-object v2, p0, Lmiui/date/Calendar;->mFields:[I

    aget v2, v2, v1

    iget-object v3, p0, Lmiui/date/Calendar;->mFields:[I

    const/4 v4, 0x5

    aget v3, v3, v4

    iget-object v4, p0, Lmiui/date/Calendar;->mFields:[I

    const/16 v5, 0x9

    aget v4, v4, v5

    iget-object v5, p0, Lmiui/date/Calendar;->mFields:[I

    const/16 v6, 0xe

    aget v5, v5, v6

    move v6, p1

    invoke-virtual/range {v0 .. v6}, Ljava/util/TimeZone;->getOffset(IIIIII)I

    move-result v0

    goto :goto_0
.end method

.method private o()V
    .locals 11

    .prologue
    const/4 v1, 0x1

    .line 1216
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    aget v2, v0, v1

    .line 1217
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    const/4 v3, 0x5

    aget v3, v0, v3

    .line 1218
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    const/16 v4, 0x9

    aget v4, v0, v4

    .line 1220
    int-to-long v5, v2

    invoke-direct {p0, v5, v6}, Lmiui/date/Calendar;->c(J)J

    move-result-wide v5

    .line 1221
    invoke-virtual {p0, v2}, Lmiui/date/Calendar;->isLeapYear(I)Z

    move-result v0

    invoke-static {v0, v3}, Lmiui/date/Calendar;->b(ZI)I

    move-result v0

    add-int/2addr v0, v4

    add-int/lit8 v0, v0, -0x1

    int-to-long v7, v0

    add-long/2addr v7, v5

    .line 1223
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    const/16 v6, 0xe

    const-wide/16 v9, 0x3

    sub-long v9, v7, v9

    const/4 v5, 0x7

    invoke-static {v9, v10, v5}, Lmiui/date/Calendar;->a(JI)I

    move-result v5

    add-int/lit8 v5, v5, 0x1

    aput v5, v0, v6

    .line 1225
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    const/16 v6, 0x12

    aget v0, v0, v6

    const v6, 0x36ee80

    mul-int/2addr v0, v6

    iget-object v6, p0, Lmiui/date/Calendar;->mFields:[I

    const/16 v9, 0x14

    aget v6, v6, v9

    const v9, 0xea60

    mul-int/2addr v6, v9

    add-int/2addr v0, v6

    iget-object v6, p0, Lmiui/date/Calendar;->mFields:[I

    const/16 v9, 0x15

    aget v6, v6, v9

    mul-int/lit16 v6, v6, 0x3e8

    add-int/2addr v0, v6

    iget-object v6, p0, Lmiui/date/Calendar;->mFields:[I

    const/16 v9, 0x16

    aget v6, v6, v9

    add-int/2addr v6, v0

    .line 1227
    const-wide/32 v9, 0x5265c00

    mul-long/2addr v7, v9

    int-to-long v9, v6

    add-long/2addr v7, v9

    iput-wide v7, p0, Lmiui/date/Calendar;->mMillisecond:J

    .line 1229
    iget-object v0, p0, Lmiui/date/Calendar;->mTimeZone:Ljava/util/TimeZone;

    invoke-virtual/range {v0 .. v6}, Ljava/util/TimeZone;->getOffset(IIIIII)I

    move-result v0

    int-to-long v0, v0

    .line 1231
    new-instance v3, Ljava/util/Date;

    iget-wide v4, p0, Lmiui/date/Calendar;->mMillisecond:J

    invoke-direct {v3, v4, v5}, Ljava/util/Date;-><init>(J)V

    .line 1232
    iget-object v4, p0, Lmiui/date/Calendar;->mTimeZone:Ljava/util/TimeZone;

    invoke-virtual {v4, v3}, Ljava/util/TimeZone;->inDaylightTime(Ljava/util/Date;)Z

    move-result v3

    if-eqz v3, :cond_0

    .line 1233
    invoke-direct {p0, v6}, Lmiui/date/Calendar;->n(I)I

    move-result v3

    int-to-long v3, v3

    sub-long/2addr v0, v3

    .line 1235
    :cond_0
    iget-wide v3, p0, Lmiui/date/Calendar;->mMillisecond:J

    if-gtz v2, :cond_1

    const-wide/16 v0, 0x0

    :cond_1
    sub-long v0, v3, v0

    iput-wide v0, p0, Lmiui/date/Calendar;->mMillisecond:J

    .line 1237
    invoke-direct {p0}, Lmiui/date/Calendar;->q()V

    .line 1238
    return-void
.end method

.method private p()V
    .locals 7

    .prologue
    const/4 v6, 0x6

    const/4 v5, 0x2

    .line 1241
    const-wide/16 v0, -0x63c1

    .line 1242
    sget-object v2, Lmiui/date/Calendar;->bD:[I

    iget-object v3, p0, Lmiui/date/Calendar;->mFields:[I

    aget v3, v3, v5

    add-int/lit16 v3, v3, -0x76c

    aget v2, v2, v3

    int-to-long v2, v2

    add-long v1, v0, v2

    .line 1244
    const/4 v0, 0x0

    :goto_0
    iget-object v3, p0, Lmiui/date/Calendar;->mFields:[I

    aget v3, v3, v6

    if-ge v0, v3, :cond_0

    .line 1245
    iget-object v3, p0, Lmiui/date/Calendar;->mFields:[I

    aget v3, v3, v5

    invoke-static {v3, v0}, Lmiui/date/Calendar;->a(II)I

    move-result v3

    int-to-long v3, v3

    add-long/2addr v1, v3

    .line 1244
    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    .line 1248
    :cond_0
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    const/16 v3, 0x8

    aget v0, v0, v3

    const/4 v3, 0x1

    if-ne v0, v3, :cond_2

    .line 1249
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    aget v0, v0, v5

    iget-object v3, p0, Lmiui/date/Calendar;->mFields:[I

    aget v3, v3, v6

    invoke-static {v0, v3}, Lmiui/date/Calendar;->a(II)I

    move-result v0

    int-to-long v3, v0

    add-long/2addr v1, v3

    .line 1256
    :cond_1
    :goto_1
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    const/16 v3, 0xa

    aget v0, v0, v3

    add-int/lit8 v0, v0, -0x1

    int-to-long v3, v0

    add-long v0, v1, v3

    .line 1258
    const-wide/16 v2, 0x0

    invoke-direct {p0, v0, v1, v2, v3}, Lmiui/date/Calendar;->a(JJ)V

    .line 1259
    invoke-direct {p0}, Lmiui/date/Calendar;->o()V

    .line 1260
    return-void

    .line 1251
    :cond_2
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    aget v0, v0, v5

    invoke-static {v0}, Lmiui/date/Calendar;->m(I)I

    move-result v0

    .line 1252
    if-ltz v0, :cond_1

    iget-object v3, p0, Lmiui/date/Calendar;->mFields:[I

    aget v3, v3, v6

    if-ge v0, v3, :cond_1

    .line 1253
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    aget v0, v0, v5

    invoke-static {v0}, Lmiui/date/Calendar;->l(I)I

    move-result v0

    int-to-long v3, v0

    add-long/2addr v1, v3

    goto :goto_1
.end method

.method private q()V
    .locals 3

    .prologue
    .line 1301
    invoke-direct {p0}, Lmiui/date/Calendar;->s()J

    move-result-wide v0

    .line 1302
    invoke-virtual {p0}, Lmiui/date/Calendar;->outOfChineseCalendarRange()Z

    move-result v2

    if-nez v2, :cond_0

    .line 1303
    invoke-direct {p0, v0, v1}, Lmiui/date/Calendar;->b(J)V

    .line 1304
    invoke-direct {p0}, Lmiui/date/Calendar;->r()V

    .line 1305
    invoke-direct {p0, v0, v1}, Lmiui/date/Calendar;->a(J)V

    .line 1307
    :cond_0
    return-void
.end method

.method private r()V
    .locals 6

    .prologue
    const/16 v5, 0x9

    const/16 v4, 0xf

    const/4 v3, 0x5

    .line 1345
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    const/4 v1, 0x1

    aget v0, v0, v1

    iget-object v1, p0, Lmiui/date/Calendar;->mFields:[I

    aget v1, v1, v3

    invoke-static {v0, v1}, Lmiui/date/Calendar;->b(II)I

    move-result v0

    .line 1346
    iget-object v1, p0, Lmiui/date/Calendar;->mFields:[I

    aget v1, v1, v5

    shr-int/lit8 v2, v0, 0x8

    if-ne v1, v2, :cond_0

    .line 1347
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    iget-object v1, p0, Lmiui/date/Calendar;->mFields:[I

    aget v1, v1, v3

    mul-int/lit8 v1, v1, 0x2

    add-int/lit8 v1, v1, 0x1

    aput v1, v0, v4

    .line 1353
    :goto_0
    return-void

    .line 1348
    :cond_0
    iget-object v1, p0, Lmiui/date/Calendar;->mFields:[I

    aget v1, v1, v5

    and-int/lit16 v0, v0, 0xff

    if-ne v1, v0, :cond_1

    .line 1349
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    iget-object v1, p0, Lmiui/date/Calendar;->mFields:[I

    aget v1, v1, v3

    mul-int/lit8 v1, v1, 0x2

    add-int/lit8 v1, v1, 0x2

    aput v1, v0, v4

    goto :goto_0

    .line 1351
    :cond_1
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    const/4 v1, 0x0

    aput v1, v0, v4

    goto :goto_0
.end method

.method private s()J
    .locals 15

    .prologue
    const/4 v8, 0x0

    const/4 v7, 0x1

    const v14, 0x5265c00

    const/16 v13, 0x10

    const-wide/16 v11, 0x0

    .line 1456
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    const/16 v1, 0x17

    iget-object v2, p0, Lmiui/date/Calendar;->mTimeZone:Ljava/util/TimeZone;

    invoke-virtual {v2}, Ljava/util/TimeZone;->getRawOffset()I

    move-result v2

    aput v2, v0, v1

    .line 1458
    iget-wide v0, p0, Lmiui/date/Calendar;->mMillisecond:J

    const-wide/32 v2, 0x5265c00

    div-long v1, v0, v2

    .line 1459
    iget-wide v3, p0, Lmiui/date/Calendar;->mMillisecond:J

    const-wide/32 v5, 0x5265c00

    rem-long/2addr v3, v5

    long-to-int v0, v3

    .line 1461
    if-gez v0, :cond_0

    .line 1462
    add-int/2addr v0, v14

    .line 1463
    const-wide/16 v3, 0x1

    sub-long/2addr v1, v3

    .line 1466
    :cond_0
    iget-object v3, p0, Lmiui/date/Calendar;->mFields:[I

    const/16 v4, 0x17

    aget v3, v3, v4

    add-int/2addr v0, v3

    move-wide v5, v1

    move v2, v0

    .line 1467
    :goto_0
    if-gez v2, :cond_1

    .line 1468
    add-int/2addr v2, v14

    .line 1469
    const-wide/16 v0, 0x1

    sub-long/2addr v5, v0

    goto :goto_0

    .line 1471
    :cond_1
    :goto_1
    if-lt v2, v14, :cond_2

    .line 1472
    sub-int/2addr v2, v14

    .line 1473
    const-wide/16 v0, 0x1

    add-long/2addr v5, v0

    goto :goto_1

    .line 1476
    :cond_2
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    const/16 v1, 0x17

    aget v3, v0, v1

    .line 1477
    iget-wide v0, p0, Lmiui/date/Calendar;->mMillisecond:J

    int-to-long v9, v3

    add-long/2addr v0, v9

    .line 1478
    iget-wide v9, p0, Lmiui/date/Calendar;->mMillisecond:J

    cmp-long v4, v9, v11

    if-lez v4, :cond_6

    cmp-long v4, v0, v11

    if-gez v4, :cond_6

    if-lez v3, :cond_6

    .line 1479
    const-wide v0, 0x7fffffffffffffffL

    .line 1484
    :cond_3
    :goto_2
    invoke-direct {p0, v5, v6, v0, v1}, Lmiui/date/Calendar;->a(JJ)V

    .line 1486
    invoke-direct {p0, v2}, Lmiui/date/Calendar;->n(I)I

    move-result v0

    .line 1487
    iget-object v1, p0, Lmiui/date/Calendar;->mFields:[I

    const/16 v3, 0x18

    aput v0, v1, v3

    .line 1488
    if-eqz v0, :cond_c

    .line 1490
    add-int/2addr v2, v0

    .line 1491
    if-gez v2, :cond_7

    .line 1492
    add-int/2addr v2, v14

    .line 1493
    const-wide/16 v0, 0x1

    sub-long v3, v5, v0

    .line 1498
    :goto_3
    cmp-long v0, v5, v3

    if-eqz v0, :cond_5

    .line 1499
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    const/16 v1, 0x18

    aget v0, v0, v1

    iget-object v1, p0, Lmiui/date/Calendar;->mFields:[I

    const/16 v5, 0x17

    aget v1, v1, v5

    sub-int v5, v0, v1

    .line 1500
    iget-wide v0, p0, Lmiui/date/Calendar;->mMillisecond:J

    int-to-long v9, v5

    add-long/2addr v0, v9

    .line 1501
    iget-wide v9, p0, Lmiui/date/Calendar;->mMillisecond:J

    cmp-long v6, v9, v11

    if-lez v6, :cond_8

    cmp-long v6, v0, v11

    if-gez v6, :cond_8

    if-lez v5, :cond_8

    .line 1502
    const-wide v0, 0x7fffffffffffffffL

    .line 1507
    :cond_4
    :goto_4
    invoke-direct {p0, v3, v4, v0, v1}, Lmiui/date/Calendar;->a(JJ)V

    :cond_5
    move v0, v2

    move-wide v1, v3

    .line 1511
    :goto_5
    iget-object v3, p0, Lmiui/date/Calendar;->mFields:[I

    aget v3, v3, v7

    if-gtz v3, :cond_9

    .line 1512
    iget-object v3, p0, Lmiui/date/Calendar;->mFields:[I

    aput v8, v3, v8

    .line 1513
    iget-object v3, p0, Lmiui/date/Calendar;->mFields:[I

    iget-object v4, p0, Lmiui/date/Calendar;->mFields:[I

    aget v4, v4, v7

    rsub-int/lit8 v4, v4, 0x1

    aput v4, v3, v7

    .line 1518
    :goto_6
    iget-object v3, p0, Lmiui/date/Calendar;->mFields:[I

    const/16 v4, 0x16

    rem-int/lit16 v5, v0, 0x3e8

    aput v5, v3, v4

    .line 1519
    div-int/lit16 v0, v0, 0x3e8

    .line 1520
    iget-object v3, p0, Lmiui/date/Calendar;->mFields:[I

    const/16 v4, 0x15

    rem-int/lit8 v5, v0, 0x3c

    aput v5, v3, v4

    .line 1521
    div-int/lit8 v0, v0, 0x3c

    .line 1522
    iget-object v3, p0, Lmiui/date/Calendar;->mFields:[I

    const/16 v4, 0x14

    rem-int/lit8 v5, v0, 0x3c

    aput v5, v3, v4

    .line 1523
    div-int/lit8 v0, v0, 0x3c

    .line 1524
    iget-object v3, p0, Lmiui/date/Calendar;->mFields:[I

    const/16 v4, 0x12

    rem-int/lit8 v0, v0, 0x18

    aput v0, v3, v4

    .line 1526
    iget-object v3, p0, Lmiui/date/Calendar;->mFields:[I

    const/16 v4, 0x11

    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    const/16 v5, 0x12

    aget v0, v0, v5

    const/16 v5, 0xb

    if-le v0, v5, :cond_a

    move v0, v7

    :goto_7
    aput v0, v3, v4

    .line 1528
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    const/16 v3, 0x12

    aget v0, v0, v3

    packed-switch v0, :pswitch_data_0

    .line 1568
    :goto_8
    return-wide v1

    .line 1480
    :cond_6
    iget-wide v9, p0, Lmiui/date/Calendar;->mMillisecond:J

    cmp-long v4, v9, v11

    if-gez v4, :cond_3

    cmp-long v4, v0, v11

    if-lez v4, :cond_3

    if-gez v3, :cond_3

    .line 1481
    const-wide/high16 v0, -0x8000000000000000L

    goto/16 :goto_2

    .line 1494
    :cond_7
    if-lt v2, v14, :cond_b

    .line 1495
    sub-int/2addr v2, v14

    .line 1496
    const-wide/16 v0, 0x1

    add-long v3, v5, v0

    goto/16 :goto_3

    .line 1503
    :cond_8
    iget-wide v9, p0, Lmiui/date/Calendar;->mMillisecond:J

    cmp-long v6, v9, v11

    if-gez v6, :cond_4

    cmp-long v6, v0, v11

    if-lez v6, :cond_4

    if-gez v5, :cond_4

    .line 1504
    const-wide/high16 v0, -0x8000000000000000L

    goto/16 :goto_4

    .line 1515
    :cond_9
    iget-object v3, p0, Lmiui/date/Calendar;->mFields:[I

    aput v7, v3, v8

    goto :goto_6

    :cond_a
    move v0, v8

    .line 1526
    goto :goto_7

    .line 1530
    :pswitch_0
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    aput v8, v0, v13

    goto :goto_8

    .line 1537
    :pswitch_1
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    aput v7, v0, v13

    goto :goto_8

    .line 1545
    :pswitch_2
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    const/4 v3, 0x2

    aput v3, v0, v13

    goto :goto_8

    .line 1548
    :pswitch_3
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    const/4 v3, 0x3

    aput v3, v0, v13

    goto :goto_8

    .line 1555
    :pswitch_4
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    const/4 v3, 0x4

    aput v3, v0, v13

    goto :goto_8

    .line 1558
    :pswitch_5
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    const/4 v3, 0x5

    aput v3, v0, v13

    goto :goto_8

    .line 1565
    :pswitch_6
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    const/4 v3, 0x6

    aput v3, v0, v13

    goto :goto_8

    :cond_b
    move-wide v3, v5

    goto/16 :goto_3

    :cond_c
    move v0, v2

    move-wide v1, v5

    goto/16 :goto_5

    .line 1528
    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_0
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_2
        :pswitch_2
        :pswitch_2
        :pswitch_2
        :pswitch_2
        :pswitch_2
        :pswitch_3
        :pswitch_4
        :pswitch_4
        :pswitch_4
        :pswitch_4
        :pswitch_4
        :pswitch_5
        :pswitch_6
        :pswitch_6
        :pswitch_6
        :pswitch_6
        :pswitch_6
    .end packed-switch
.end method


# virtual methods
.method public add(II)Lmiui/date/Calendar;
    .locals 9

    .prologue
    const/16 v8, 0x8

    const/4 v7, 0x2

    const/4 v6, 0x6

    const/4 v3, 0x0

    const/4 v2, 0x1

    .line 1013
    if-ltz p1, :cond_0

    const/16 v0, 0x19

    if-lt p1, v0, :cond_1

    .line 1014
    :cond_0
    new-instance v0, Ljava/lang/IllegalArgumentException;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "Field out of range [0-25]: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 1017
    :cond_1
    packed-switch p1, :pswitch_data_0

    .line 1210
    :pswitch_0
    new-instance v0, Ljava/lang/IllegalArgumentException;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "unsupported set field:"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    sget-object v2, Lmiui/date/Calendar;->bB:[Ljava/lang/String;

    aget-object v2, v2, p1

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 1019
    :pswitch_1
    if-eqz p2, :cond_2

    .line 1020
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    aget v0, v0, v3

    add-int/2addr v0, p2

    rem-int/lit8 v0, v0, 0x2

    .line 1021
    iget-object v1, p0, Lmiui/date/Calendar;->mFields:[I

    aget v1, v1, v3

    if-eq v1, v0, :cond_2

    .line 1022
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    aget v0, v0, v3

    if-nez v0, :cond_2

    .line 1023
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    iget-object v1, p0, Lmiui/date/Calendar;->mFields:[I

    aget v1, v1, v2

    rsub-int/lit8 v1, v1, -0x1

    aput v1, v0, v2

    .line 1024
    invoke-direct {p0}, Lmiui/date/Calendar;->o()V

    .line 1212
    :cond_2
    :goto_0
    return-object p0

    .line 1031
    :pswitch_2
    if-eqz p2, :cond_2

    .line 1032
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    aget v0, v0, v3

    if-nez v0, :cond_3

    .line 1033
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    iget-object v1, p0, Lmiui/date/Calendar;->mFields:[I

    aget v1, v1, v2

    rsub-int/lit8 v1, v1, -0x1

    aput v1, v0, v2

    .line 1035
    :cond_3
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    aget v1, v0, v2

    add-int/2addr v1, p2

    aput v1, v0, v2

    .line 1036
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    aget v0, v0, v2

    invoke-virtual {p0, v0}, Lmiui/date/Calendar;->isLeapYear(I)Z

    move-result v0

    iget-object v1, p0, Lmiui/date/Calendar;->mFields:[I

    const/4 v2, 0x5

    aget v1, v1, v2

    invoke-static {v0, v1}, Lmiui/date/Calendar;->a(ZI)I

    move-result v0

    .line 1037
    iget-object v1, p0, Lmiui/date/Calendar;->mFields:[I

    const/16 v2, 0x9

    aget v1, v1, v2

    if-le v1, v0, :cond_4

    .line 1038
    iget-object v1, p0, Lmiui/date/Calendar;->mFields:[I

    const/16 v2, 0x9

    aput v0, v1, v2

    .line 1040
    :cond_4
    invoke-direct {p0}, Lmiui/date/Calendar;->o()V

    goto :goto_0

    .line 1045
    :pswitch_3
    if-eqz p2, :cond_2

    .line 1046
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    aget v0, v0, v7

    add-int/2addr v0, p2

    .line 1047
    invoke-virtual {p0}, Lmiui/date/Calendar;->outOfChineseCalendarRange()Z

    move-result v1

    if-nez v1, :cond_5

    const/16 v1, 0x76c

    if-lt v0, v1, :cond_5

    const/16 v1, 0x834

    if-le v0, v1, :cond_6

    .line 1048
    :cond_5
    new-instance v0, Ljava/lang/IllegalArgumentException;

    const-string v1, "out of range of Chinese Lunar Year"

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 1051
    :cond_6
    iget-object v1, p0, Lmiui/date/Calendar;->mFields:[I

    aput v0, v1, v7

    .line 1052
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    aget v0, v0, v8

    if-ne v0, v2, :cond_7

    .line 1053
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    aget v0, v0, v6

    iget-object v1, p0, Lmiui/date/Calendar;->mFields:[I

    aget v1, v1, v7

    invoke-static {v1}, Lmiui/date/Calendar;->m(I)I

    move-result v1

    if-ne v0, v1, :cond_7

    .line 1054
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    aput v3, v0, v8

    .line 1058
    :cond_7
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    aget v0, v0, v8

    if-ne v0, v2, :cond_9

    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    aget v0, v0, v7

    invoke-static {v0}, Lmiui/date/Calendar;->l(I)I

    move-result v0

    .line 1061
    :goto_1
    iget-object v1, p0, Lmiui/date/Calendar;->mFields:[I

    const/16 v2, 0xa

    aget v1, v1, v2

    if-le v1, v0, :cond_8

    .line 1062
    iget-object v1, p0, Lmiui/date/Calendar;->mFields:[I

    const/16 v2, 0xa

    aput v0, v1, v2

    .line 1064
    :cond_8
    invoke-direct {p0}, Lmiui/date/Calendar;->p()V

    goto/16 :goto_0

    .line 1058
    :cond_9
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    aget v0, v0, v7

    iget-object v1, p0, Lmiui/date/Calendar;->mFields:[I

    aget v1, v1, v6

    invoke-static {v0, v1}, Lmiui/date/Calendar;->a(II)I

    move-result v0

    goto :goto_1

    .line 1069
    :pswitch_4
    if-eqz p2, :cond_2

    .line 1070
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    const/4 v1, 0x5

    aget v0, v0, v1

    add-int v1, p2, v0

    .line 1071
    div-int/lit8 v0, v1, 0xc

    .line 1072
    rem-int/lit8 v1, v1, 0xc

    .line 1073
    if-gez v1, :cond_a

    .line 1074
    add-int/lit8 v1, v1, 0xc

    .line 1075
    add-int/lit8 v0, v0, -0x1

    .line 1078
    :cond_a
    iget-object v4, p0, Lmiui/date/Calendar;->mFields:[I

    const/4 v5, 0x5

    aput v1, v4, v5

    .line 1079
    if-nez v0, :cond_d

    .line 1080
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    aget v0, v0, v3

    if-nez v0, :cond_b

    .line 1081
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    iget-object v1, p0, Lmiui/date/Calendar;->mFields:[I

    aget v1, v1, v2

    rsub-int/lit8 v1, v1, -0x1

    aput v1, v0, v2

    .line 1084
    :cond_b
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    aget v0, v0, v2

    invoke-virtual {p0, v0}, Lmiui/date/Calendar;->isLeapYear(I)Z

    move-result v0

    iget-object v1, p0, Lmiui/date/Calendar;->mFields:[I

    const/4 v2, 0x5

    aget v1, v1, v2

    invoke-static {v0, v1}, Lmiui/date/Calendar;->a(ZI)I

    move-result v0

    .line 1085
    iget-object v1, p0, Lmiui/date/Calendar;->mFields:[I

    const/16 v2, 0x9

    aget v1, v1, v2

    if-le v1, v0, :cond_c

    .line 1086
    iget-object v1, p0, Lmiui/date/Calendar;->mFields:[I

    const/16 v2, 0x9

    aput v0, v1, v2

    .line 1088
    :cond_c
    invoke-direct {p0}, Lmiui/date/Calendar;->o()V

    goto/16 :goto_0

    .line 1090
    :cond_d
    invoke-virtual {p0, v2, v0}, Lmiui/date/Calendar;->add(II)Lmiui/date/Calendar;

    goto/16 :goto_0

    .line 1096
    :pswitch_5
    if-eqz p2, :cond_2

    .line 1097
    invoke-virtual {p0}, Lmiui/date/Calendar;->outOfChineseCalendarRange()Z

    move-result v0

    if-eqz v0, :cond_e

    .line 1098
    new-instance v0, Ljava/lang/IllegalArgumentException;

    const-string v1, "out of range of Chinese Lunar Year"

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 1101
    :cond_e
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    aget v0, v0, v7

    invoke-static {v0}, Lmiui/date/Calendar;->m(I)I

    move-result v0

    move v1, p2

    .line 1102
    :goto_2
    if-lez v1, :cond_12

    .line 1103
    iget-object v4, p0, Lmiui/date/Calendar;->mFields:[I

    aget v4, v4, v6

    if-ne v4, v0, :cond_10

    iget-object v4, p0, Lmiui/date/Calendar;->mFields:[I

    aget v4, v4, v8

    if-nez v4, :cond_10

    .line 1104
    iget-object v4, p0, Lmiui/date/Calendar;->mFields:[I

    aput v2, v4, v8

    .line 1102
    :cond_f
    :goto_3
    add-int/lit8 v1, v1, -0x1

    goto :goto_2

    .line 1106
    :cond_10
    iget-object v4, p0, Lmiui/date/Calendar;->mFields:[I

    aget v5, v4, v6

    add-int/lit8 v5, v5, 0x1

    aput v5, v4, v6

    .line 1107
    iget-object v4, p0, Lmiui/date/Calendar;->mFields:[I

    aput v3, v4, v8

    .line 1108
    iget-object v4, p0, Lmiui/date/Calendar;->mFields:[I

    aget v4, v4, v6

    const/16 v5, 0xb

    if-le v4, v5, :cond_f

    .line 1109
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    aput v3, v0, v6

    .line 1110
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    aget v4, v0, v7

    add-int/lit8 v4, v4, 0x1

    aput v4, v0, v7

    .line 1111
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    aget v0, v0, v7

    const/16 v4, 0x834

    if-le v0, v4, :cond_11

    .line 1112
    new-instance v0, Ljava/lang/IllegalArgumentException;

    const-string v1, "out of range of Chinese Lunar Year"

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 1114
    :cond_11
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    aget v0, v0, v7

    invoke-static {v0}, Lmiui/date/Calendar;->m(I)I

    move-result v0

    goto :goto_3

    .line 1119
    :cond_12
    :goto_4
    if-gez v1, :cond_17

    .line 1120
    iget-object v4, p0, Lmiui/date/Calendar;->mFields:[I

    aget v4, v4, v6

    if-ne v4, v0, :cond_14

    iget-object v4, p0, Lmiui/date/Calendar;->mFields:[I

    aget v4, v4, v8

    if-ne v4, v2, :cond_14

    .line 1121
    iget-object v4, p0, Lmiui/date/Calendar;->mFields:[I

    aput v3, v4, v8

    .line 1119
    :cond_13
    :goto_5
    add-int/lit8 v1, v1, 0x1

    goto :goto_4

    .line 1123
    :cond_14
    iget-object v4, p0, Lmiui/date/Calendar;->mFields:[I

    aget v5, v4, v6

    add-int/lit8 v5, v5, -0x1

    aput v5, v4, v6

    .line 1124
    iget-object v4, p0, Lmiui/date/Calendar;->mFields:[I

    aget v4, v4, v6

    if-gez v4, :cond_16

    .line 1125
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    const/16 v4, 0xb

    aput v4, v0, v6

    .line 1126
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    aget v4, v0, v6

    add-int/lit8 v4, v4, -0x1

    aput v4, v0, v6

    .line 1127
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    aget v0, v0, v7

    const/16 v4, 0x76c

    if-ge v0, v4, :cond_15

    .line 1128
    new-instance v0, Ljava/lang/IllegalArgumentException;

    const-string v1, "out of range of Chinese Lunar Year"

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 1130
    :cond_15
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    aget v0, v0, v2

    invoke-static {v0}, Lmiui/date/Calendar;->m(I)I

    move-result v0

    .line 1132
    :cond_16
    iget-object v4, p0, Lmiui/date/Calendar;->mFields:[I

    aget v4, v4, v6

    if-ne v4, v0, :cond_13

    .line 1133
    iget-object v4, p0, Lmiui/date/Calendar;->mFields:[I

    aput v2, v4, v8

    goto :goto_5

    .line 1138
    :cond_17
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    aget v0, v0, v8

    if-ne v0, v2, :cond_19

    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    aget v0, v0, v7

    invoke-static {v0}, Lmiui/date/Calendar;->l(I)I

    move-result v0

    .line 1141
    :goto_6
    iget-object v1, p0, Lmiui/date/Calendar;->mFields:[I

    const/16 v2, 0xa

    aget v1, v1, v2

    if-le v1, v0, :cond_18

    .line 1142
    iget-object v1, p0, Lmiui/date/Calendar;->mFields:[I

    const/16 v2, 0xa

    aput v0, v1, v2

    .line 1144
    :cond_18
    invoke-direct {p0}, Lmiui/date/Calendar;->p()V

    goto/16 :goto_0

    .line 1138
    :cond_19
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    aget v0, v0, v7

    iget-object v1, p0, Lmiui/date/Calendar;->mFields:[I

    aget v1, v1, v6

    invoke-static {v0, v1}, Lmiui/date/Calendar;->a(II)I

    move-result v0

    goto :goto_6

    .line 1152
    :pswitch_6
    if-eqz p2, :cond_2

    .line 1153
    iget-wide v0, p0, Lmiui/date/Calendar;->mMillisecond:J

    int-to-long v4, p2

    const-wide/32 v6, 0x5265c00

    mul-long/2addr v4, v6

    add-long/2addr v4, v0

    .line 1154
    if-lez p2, :cond_1a

    move v0, v2

    :goto_7
    iget-wide v6, p0, Lmiui/date/Calendar;->mMillisecond:J

    cmp-long v1, v4, v6

    if-lez v1, :cond_1b

    :goto_8
    if-eq v0, v2, :cond_1c

    .line 1155
    new-instance v0, Ljava/lang/IllegalArgumentException;

    const-string v1, "out of range"

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    :cond_1a
    move v0, v3

    .line 1154
    goto :goto_7

    :cond_1b
    move v2, v3

    goto :goto_8

    .line 1157
    :cond_1c
    iput-wide v4, p0, Lmiui/date/Calendar;->mMillisecond:J

    .line 1158
    invoke-direct {p0}, Lmiui/date/Calendar;->q()V

    goto/16 :goto_0

    .line 1163
    :pswitch_7
    if-eqz p2, :cond_2

    .line 1164
    iget-wide v0, p0, Lmiui/date/Calendar;->mMillisecond:J

    int-to-long v4, p2

    const-wide/32 v6, 0x36ee80

    mul-long/2addr v4, v6

    add-long/2addr v4, v0

    .line 1165
    if-lez p2, :cond_1d

    move v0, v2

    :goto_9
    iget-wide v6, p0, Lmiui/date/Calendar;->mMillisecond:J

    cmp-long v1, v4, v6

    if-lez v1, :cond_1e

    :goto_a
    if-eq v0, v2, :cond_1f

    .line 1166
    new-instance v0, Ljava/lang/IllegalArgumentException;

    const-string v1, "out of range"

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    :cond_1d
    move v0, v3

    .line 1165
    goto :goto_9

    :cond_1e
    move v2, v3

    goto :goto_a

    .line 1168
    :cond_1f
    iput-wide v4, p0, Lmiui/date/Calendar;->mMillisecond:J

    .line 1169
    invoke-direct {p0}, Lmiui/date/Calendar;->q()V

    goto/16 :goto_0

    .line 1175
    :pswitch_8
    if-eqz p2, :cond_2

    .line 1176
    iget-wide v0, p0, Lmiui/date/Calendar;->mMillisecond:J

    int-to-long v4, p2

    const-wide/32 v6, 0xea60

    mul-long/2addr v4, v6

    add-long/2addr v4, v0

    .line 1177
    if-lez p2, :cond_20

    move v0, v2

    :goto_b
    iget-wide v6, p0, Lmiui/date/Calendar;->mMillisecond:J

    cmp-long v1, v4, v6

    if-lez v1, :cond_21

    :goto_c
    if-eq v0, v2, :cond_22

    .line 1178
    new-instance v0, Ljava/lang/IllegalArgumentException;

    const-string v1, "out of range"

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    :cond_20
    move v0, v3

    .line 1177
    goto :goto_b

    :cond_21
    move v2, v3

    goto :goto_c

    .line 1180
    :cond_22
    iput-wide v4, p0, Lmiui/date/Calendar;->mMillisecond:J

    .line 1181
    invoke-direct {p0}, Lmiui/date/Calendar;->q()V

    goto/16 :goto_0

    .line 1187
    :pswitch_9
    if-eqz p2, :cond_2

    .line 1188
    iget-wide v0, p0, Lmiui/date/Calendar;->mMillisecond:J

    int-to-long v4, p2

    const-wide/16 v6, 0x3e8

    mul-long/2addr v4, v6

    add-long/2addr v4, v0

    .line 1189
    if-lez p2, :cond_23

    move v0, v2

    :goto_d
    iget-wide v6, p0, Lmiui/date/Calendar;->mMillisecond:J

    cmp-long v1, v4, v6

    if-lez v1, :cond_24

    :goto_e
    if-eq v0, v2, :cond_25

    .line 1190
    new-instance v0, Ljava/lang/IllegalArgumentException;

    const-string v1, "out of range"

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    :cond_23
    move v0, v3

    .line 1189
    goto :goto_d

    :cond_24
    move v2, v3

    goto :goto_e

    .line 1192
    :cond_25
    iput-wide v4, p0, Lmiui/date/Calendar;->mMillisecond:J

    .line 1193
    invoke-direct {p0}, Lmiui/date/Calendar;->q()V

    goto/16 :goto_0

    .line 1199
    :pswitch_a
    if-eqz p2, :cond_2

    .line 1200
    iget-wide v0, p0, Lmiui/date/Calendar;->mMillisecond:J

    int-to-long v4, p2

    add-long/2addr v4, v0

    .line 1201
    if-lez p2, :cond_26

    move v0, v2

    :goto_f
    iget-wide v6, p0, Lmiui/date/Calendar;->mMillisecond:J

    cmp-long v1, v4, v6

    if-lez v1, :cond_27

    :goto_10
    if-eq v0, v2, :cond_28

    .line 1202
    new-instance v0, Ljava/lang/IllegalArgumentException;

    const-string v1, "out of range"

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    :cond_26
    move v0, v3

    .line 1201
    goto :goto_f

    :cond_27
    move v2, v3

    goto :goto_10

    .line 1204
    :cond_28
    iput-wide v4, p0, Lmiui/date/Calendar;->mMillisecond:J

    .line 1205
    invoke-direct {p0}, Lmiui/date/Calendar;->q()V

    goto/16 :goto_0

    .line 1017
    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_1
        :pswitch_2
        :pswitch_3
        :pswitch_0
        :pswitch_0
        :pswitch_4
        :pswitch_5
        :pswitch_0
        :pswitch_0
        :pswitch_6
        :pswitch_6
        :pswitch_0
        :pswitch_6
        :pswitch_6
        :pswitch_0
        :pswitch_0
        :pswitch_0
        :pswitch_0
        :pswitch_7
        :pswitch_0
        :pswitch_8
        :pswitch_9
        :pswitch_a
    .end packed-switch
.end method

.method public after(Lmiui/date/Calendar;)Z
    .locals 4

    .prologue
    .line 1674
    invoke-virtual {p0}, Lmiui/date/Calendar;->getTimeInMillis()J

    move-result-wide v0

    invoke-virtual {p1}, Lmiui/date/Calendar;->getTimeInMillis()J

    move-result-wide v2

    cmp-long v0, v0, v2

    if-lez v0, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public before(Lmiui/date/Calendar;)Z
    .locals 4

    .prologue
    .line 1690
    invoke-virtual {p0}, Lmiui/date/Calendar;->getTimeInMillis()J

    move-result-wide v0

    invoke-virtual {p1}, Lmiui/date/Calendar;->getTimeInMillis()J

    move-result-wide v2

    cmp-long v0, v0, v2

    if-gez v0, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public final clone()Ljava/lang/Object;
    .locals 2

    .prologue
    .line 1651
    :try_start_0
    invoke-super {p0}, Ljava/lang/Object;->clone()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/date/Calendar;

    .line 1652
    iget-object v1, p0, Lmiui/date/Calendar;->mFields:[I

    invoke-virtual {v1}, [I->clone()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, [I

    iput-object v1, v0, Lmiui/date/Calendar;->mFields:[I

    .line 1653
    iget-object v1, p0, Lmiui/date/Calendar;->mTimeZone:Ljava/util/TimeZone;

    invoke-virtual {v1}, Ljava/util/TimeZone;->clone()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/util/TimeZone;

    iput-object v1, v0, Lmiui/date/Calendar;->mTimeZone:Ljava/util/TimeZone;
    :try_end_0
    .catch Ljava/lang/CloneNotSupportedException; {:try_start_0 .. :try_end_0} :catch_0

    .line 1654
    return-object v0

    .line 1655
    :catch_0
    move-exception v0

    .line 1657
    new-instance v1, Ljava/lang/RuntimeException;

    invoke-direct {v1, v0}, Ljava/lang/RuntimeException;-><init>(Ljava/lang/Throwable;)V

    throw v1
.end method

.method public bridge synthetic compareTo(Ljava/lang/Object;)I
    .locals 1

    .prologue
    .line 63
    check-cast p1, Lmiui/date/Calendar;

    invoke-virtual {p0, p1}, Lmiui/date/Calendar;->compareTo(Lmiui/date/Calendar;)I

    move-result v0

    return v0
.end method

.method public compareTo(Lmiui/date/Calendar;)I
    .locals 4

    .prologue
    .line 1600
    iget-wide v0, p0, Lmiui/date/Calendar;->mMillisecond:J

    iget-wide v2, p1, Lmiui/date/Calendar;->mMillisecond:J

    cmp-long v0, v0, v2

    if-gez v0, :cond_0

    const/4 v0, -0x1

    :goto_0
    return v0

    :cond_0
    iget-wide v0, p0, Lmiui/date/Calendar;->mMillisecond:J

    iget-wide v2, p1, Lmiui/date/Calendar;->mMillisecond:J

    cmp-long v0, v0, v2

    if-nez v0, :cond_1

    const/4 v0, 0x0

    goto :goto_0

    :cond_1
    const/4 v0, 0x1

    goto :goto_0
.end method

.method public equals(Ljava/lang/Object;)Z
    .locals 4

    .prologue
    .line 1618
    if-eq p1, p0, :cond_0

    instance-of v0, p1, Lmiui/date/Calendar;

    if-eqz v0, :cond_1

    iget-wide v0, p0, Lmiui/date/Calendar;->mMillisecond:J

    check-cast p1, Lmiui/date/Calendar;

    iget-wide v2, p1, Lmiui/date/Calendar;->mMillisecond:J

    cmp-long v0, v0, v2

    if-nez v0, :cond_1

    :cond_0
    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_1
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public format(Ljava/lang/CharSequence;)Ljava/lang/String;
    .locals 1

    .prologue
    .line 2000
    const/4 v0, 0x0

    invoke-virtual {p0, p1, v0}, Lmiui/date/Calendar;->format(Ljava/lang/CharSequence;Lmiui/date/CalendarFormatSymbols;)Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method public format(Ljava/lang/CharSequence;Lmiui/date/CalendarFormatSymbols;)Ljava/lang/String;
    .locals 3

    .prologue
    .line 2016
    invoke-static {}, Lmiui/util/Pools;->getStringBuilderPool()Lmiui/util/Pools$Pool;

    move-result-object v0

    invoke-interface {v0}, Lmiui/util/Pools$Pool;->acquire()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/StringBuilder;

    .line 2017
    invoke-virtual {p0, v0, p1, p2}, Lmiui/date/Calendar;->format(Ljava/lang/StringBuilder;Ljava/lang/CharSequence;Lmiui/date/CalendarFormatSymbols;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    .line 2018
    invoke-static {}, Lmiui/util/Pools;->getStringBuilderPool()Lmiui/util/Pools$Pool;

    move-result-object v2

    invoke-interface {v2, v0}, Lmiui/util/Pools$Pool;->release(Ljava/lang/Object;)V

    .line 2019
    return-object v1
.end method

.method public format(Ljava/lang/StringBuilder;Ljava/lang/CharSequence;)Ljava/lang/StringBuilder;
    .locals 1

    .prologue
    .line 2037
    const/4 v0, 0x0

    invoke-virtual {p0, p1, p2, v0}, Lmiui/date/Calendar;->format(Ljava/lang/StringBuilder;Ljava/lang/CharSequence;Lmiui/date/CalendarFormatSymbols;)Ljava/lang/StringBuilder;

    move-result-object v0

    return-object v0
.end method

.method public format(Ljava/lang/StringBuilder;Ljava/lang/CharSequence;Lmiui/date/CalendarFormatSymbols;)Ljava/lang/StringBuilder;
    .locals 12

    .prologue
    const/16 v11, 0x27

    const/4 v9, 0x1

    const/4 v8, 0x0

    .line 2054
    if-nez p3, :cond_8

    .line 2055
    invoke-static {}, Lmiui/date/CalendarFormatSymbols;->getDefault()Lmiui/date/CalendarFormatSymbols;

    move-result-object v2

    .line 2061
    :goto_0
    invoke-interface {p2}, Ljava/lang/CharSequence;->length()I

    move-result v10

    move v0, v8

    move v7, v8

    :goto_1
    if-ge v0, v10, :cond_7

    .line 2062
    invoke-interface {p2, v0}, Ljava/lang/CharSequence;->charAt(I)C

    move-result v3

    .line 2064
    if-eqz v7, :cond_2

    .line 2065
    if-ne v3, v11, :cond_1

    .line 2066
    add-int/lit8 v1, v0, 0x1

    if-ge v1, v10, :cond_0

    add-int/lit8 v1, v0, 0x1

    invoke-interface {p2, v1}, Ljava/lang/CharSequence;->charAt(I)C

    move-result v1

    if-ne v1, v3, :cond_0

    .line 2067
    add-int/lit8 v0, v0, 0x1

    .line 2068
    invoke-virtual {p1, v3}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    move v1, v7

    .line 2061
    :goto_2
    add-int/lit8 v0, v0, 0x1

    move v7, v1

    goto :goto_1

    :cond_0
    move v1, v8

    .line 2070
    goto :goto_2

    .line 2073
    :cond_1
    invoke-virtual {p1, v3}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    move v1, v7

    .line 2075
    goto :goto_2

    .line 2078
    :cond_2
    if-ne v3, v11, :cond_4

    .line 2079
    add-int/lit8 v1, v0, 0x1

    if-ge v1, v10, :cond_3

    add-int/lit8 v1, v0, 0x1

    invoke-interface {p2, v1}, Ljava/lang/CharSequence;->charAt(I)C

    move-result v1

    if-ne v1, v3, :cond_3

    .line 2080
    add-int/lit8 v0, v0, 0x1

    .line 2081
    invoke-virtual {p1, v3}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    move v1, v7

    goto :goto_2

    :cond_3
    move v1, v9

    .line 2083
    goto :goto_2

    .line 2086
    :cond_4
    const/16 v1, 0x41

    if-lt v3, v1, :cond_6

    const/16 v1, 0x7a

    if-gt v3, v1, :cond_6

    sget-object v1, Lmiui/date/Calendar;->bC:[I

    add-int/lit8 v4, v3, -0x41

    aget v1, v1, v4

    if-ltz v1, :cond_6

    move v6, v0

    move v4, v9

    .line 2087
    :goto_3
    add-int/lit8 v0, v6, 0x1

    if-ge v0, v10, :cond_5

    add-int/lit8 v0, v6, 0x1

    invoke-interface {p2, v0}, Ljava/lang/CharSequence;->charAt(I)C

    move-result v0

    if-ne v0, v3, :cond_5

    .line 2088
    add-int/lit8 v6, v6, 0x1

    .line 2087
    add-int/lit8 v4, v4, 0x1

    goto :goto_3

    .line 2090
    :cond_5
    sget-object v0, Lmiui/date/Calendar;->bC:[I

    add-int/lit8 v1, v3, -0x41

    aget v5, v0, v1

    move-object v0, p0

    move-object v1, p1

    invoke-direct/range {v0 .. v5}, Lmiui/date/Calendar;->a(Ljava/lang/StringBuilder;Lmiui/date/CalendarFormatSymbols;CII)V

    move v0, v6

    move v1, v7

    goto :goto_2

    .line 2092
    :cond_6
    invoke-virtual {p1, v3}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    move v1, v7

    goto :goto_2

    .line 2097
    :cond_7
    return-object p1

    :cond_8
    move-object v2, p3

    goto :goto_0
.end method

.method public get(I)I
    .locals 3

    .prologue
    .line 1294
    if-ltz p1, :cond_0

    const/16 v0, 0x19

    if-lt p1, v0, :cond_1

    .line 1295
    :cond_0
    new-instance v0, Ljava/lang/IllegalArgumentException;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "Field out of range [0-25]: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 1297
    :cond_1
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    aget v0, v0, p1

    return v0
.end method

.method public getActualMaximum(I)I
    .locals 6

    .prologue
    const/4 v3, 0x6

    const/16 v2, 0x3b

    const/4 v5, 0x2

    const/4 v0, 0x0

    const/4 v1, 0x1

    .line 1698
    if-ltz p1, :cond_0

    const/16 v4, 0x19

    if-lt p1, v4, :cond_1

    .line 1699
    :cond_0
    new-instance v0, Ljava/lang/IllegalArgumentException;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "Field out of range [0-25]: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 1702
    :cond_1
    packed-switch p1, :pswitch_data_0

    .line 1747
    new-instance v0, Ljava/lang/IllegalArgumentException;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "unsupported field: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    sget-object v2, Lmiui/date/Calendar;->bB:[Ljava/lang/String;

    aget-object v2, v2, p1

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    :pswitch_0
    move v0, v1

    .line 1745
    :cond_2
    :goto_0
    return v0

    .line 1706
    :pswitch_1
    iget-object v2, p0, Lmiui/date/Calendar;->mFields:[I

    aget v0, v2, v0

    if-ne v0, v1, :cond_3

    const v0, 0x116bd2d2

    goto :goto_0

    :cond_3
    const v0, 0x116babff

    goto :goto_0

    .line 1708
    :pswitch_2
    const/16 v0, 0x834

    goto :goto_0

    .line 1710
    :pswitch_3
    const/16 v0, 0xb

    goto :goto_0

    :pswitch_4
    move v0, v2

    .line 1715
    goto :goto_0

    .line 1718
    :pswitch_5
    const/16 v0, 0xb

    goto :goto_0

    :pswitch_6
    move v0, v1

    .line 1720
    goto :goto_0

    .line 1722
    :pswitch_7
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    aget v0, v0, v1

    invoke-virtual {p0, v0}, Lmiui/date/Calendar;->isLeapYear(I)Z

    move-result v0

    iget-object v1, p0, Lmiui/date/Calendar;->mFields:[I

    const/4 v2, 0x5

    aget v1, v1, v2

    invoke-static {v0, v1}, Lmiui/date/Calendar;->a(ZI)I

    move-result v0

    goto :goto_0

    .line 1724
    :pswitch_8
    invoke-virtual {p0}, Lmiui/date/Calendar;->outOfChineseCalendarRange()Z

    move-result v1

    if-nez v1, :cond_2

    invoke-virtual {p0}, Lmiui/date/Calendar;->isChineseLeapMonth()Z

    move-result v0

    if-eqz v0, :cond_4

    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    aget v0, v0, v5

    invoke-static {v0}, Lmiui/date/Calendar;->l(I)I

    move-result v0

    goto :goto_0

    :cond_4
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    aget v0, v0, v5

    iget-object v1, p0, Lmiui/date/Calendar;->mFields:[I

    aget v1, v1, v3

    invoke-static {v0, v1}, Lmiui/date/Calendar;->a(II)I

    move-result v0

    goto :goto_0

    .line 1727
    :pswitch_9
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    aget v0, v0, v1

    invoke-direct {p0, v0}, Lmiui/date/Calendar;->j(I)I

    move-result v0

    goto :goto_0

    .line 1729
    :pswitch_a
    invoke-virtual {p0}, Lmiui/date/Calendar;->outOfChineseCalendarRange()Z

    move-result v1

    if-nez v1, :cond_2

    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    aget v0, v0, v5

    invoke-static {v0}, Lmiui/date/Calendar;->k(I)I

    move-result v0

    goto :goto_0

    .line 1731
    :pswitch_b
    const/4 v0, 0x7

    goto :goto_0

    .line 1733
    :pswitch_c
    const/16 v0, 0x18

    goto :goto_0

    :pswitch_d
    move v0, v3

    .line 1735
    goto :goto_0

    :pswitch_e
    move v0, v1

    .line 1737
    goto :goto_0

    .line 1739
    :pswitch_f
    const/16 v0, 0x17

    goto :goto_0

    :pswitch_10
    move v0, v2

    .line 1741
    goto :goto_0

    :pswitch_11
    move v0, v2

    .line 1743
    goto :goto_0

    .line 1745
    :pswitch_12
    const/16 v0, 0x3e7

    goto :goto_0

    .line 1702
    nop

    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_0
        :pswitch_1
        :pswitch_2
        :pswitch_3
        :pswitch_4
        :pswitch_5
        :pswitch_5
        :pswitch_4
        :pswitch_6
        :pswitch_7
        :pswitch_8
        :pswitch_4
        :pswitch_9
        :pswitch_a
        :pswitch_b
        :pswitch_c
        :pswitch_d
        :pswitch_e
        :pswitch_f
        :pswitch_4
        :pswitch_10
        :pswitch_11
        :pswitch_12
    .end packed-switch
.end method

.method public getActualMinimum(I)I
    .locals 3

    .prologue
    const/4 v1, 0x1

    const/4 v0, 0x0

    .line 1754
    if-ltz p1, :cond_0

    const/16 v2, 0x19

    if-lt p1, v2, :cond_1

    .line 1755
    :cond_0
    new-instance v0, Ljava/lang/IllegalArgumentException;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "Field out of range [0-25]: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 1758
    :cond_1
    packed-switch p1, :pswitch_data_0

    .line 1802
    new-instance v0, Ljava/lang/IllegalArgumentException;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "unsupported field: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    sget-object v2, Lmiui/date/Calendar;->bB:[Ljava/lang/String;

    aget-object v2, v2, p1

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    :pswitch_0
    move v0, v1

    .line 1800
    :cond_2
    :goto_0
    :pswitch_1
    return v0

    .line 1764
    :pswitch_2
    const/16 v0, 0x76c

    goto :goto_0

    :pswitch_3
    move v0, v1

    .line 1778
    goto :goto_0

    .line 1780
    :pswitch_4
    invoke-virtual {p0}, Lmiui/date/Calendar;->outOfChineseCalendarRange()Z

    move-result v2

    if-nez v2, :cond_2

    move v0, v1

    goto :goto_0

    :pswitch_5
    move v0, v1

    .line 1782
    goto :goto_0

    .line 1784
    :pswitch_6
    invoke-virtual {p0}, Lmiui/date/Calendar;->outOfChineseCalendarRange()Z

    move-result v2

    if-nez v2, :cond_2

    move v0, v1

    goto :goto_0

    :pswitch_7
    move v0, v1

    .line 1786
    goto :goto_0

    .line 1758
    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_1
        :pswitch_0
        :pswitch_2
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_3
        :pswitch_4
        :pswitch_1
        :pswitch_5
        :pswitch_6
        :pswitch_7
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
    .end packed-switch
.end method

.method public getChineseLeapMonth()I
    .locals 2

    .prologue
    .line 1586
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    const/4 v1, 0x2

    aget v0, v0, v1

    invoke-static {v0}, Lmiui/date/Calendar;->m(I)I

    move-result v0

    return v0
.end method

.method public getTimeInMillis()J
    .locals 2

    .prologue
    .line 824
    iget-wide v0, p0, Lmiui/date/Calendar;->mMillisecond:J

    return-wide v0
.end method

.method public getTimeZone()Ljava/util/TimeZone;
    .locals 1

    .prologue
    .line 816
    iget-object v0, p0, Lmiui/date/Calendar;->mTimeZone:Ljava/util/TimeZone;

    return-object v0
.end method

.method public hashCode()I
    .locals 5

    .prologue
    .line 1608
    iget-wide v0, p0, Lmiui/date/Calendar;->mMillisecond:J

    iget-wide v2, p0, Lmiui/date/Calendar;->mMillisecond:J

    const/16 v4, 0x20

    ushr-long/2addr v2, v4

    xor-long/2addr v0, v2

    long-to-int v0, v0

    return v0
.end method

.method public isChineseLeapMonth()Z
    .locals 3

    .prologue
    const/4 v0, 0x1

    .line 1578
    iget-object v1, p0, Lmiui/date/Calendar;->mFields:[I

    const/16 v2, 0x8

    aget v1, v1, v2

    if-ne v1, v0, :cond_0

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public isLeapYear(I)Z
    .locals 3

    .prologue
    const/4 v0, 0x1

    const/4 v1, 0x0

    .line 1811
    iget v2, p0, Lmiui/date/Calendar;->bK:I

    if-le p1, v2, :cond_2

    .line 1812
    rem-int/lit8 v2, p1, 0x4

    if-nez v2, :cond_1

    rem-int/lit8 v2, p1, 0x64

    if-nez v2, :cond_0

    rem-int/lit16 v2, p1, 0x190

    if-nez v2, :cond_1

    .line 1815
    :cond_0
    :goto_0
    return v0

    :cond_1
    move v0, v1

    .line 1812
    goto :goto_0

    .line 1815
    :cond_2
    rem-int/lit8 v2, p1, 0x4

    if-eqz v2, :cond_0

    move v0, v1

    goto :goto_0
.end method

.method public outOfChineseCalendarRange()Z
    .locals 8

    .prologue
    const/16 v7, 0x18

    const/16 v6, 0x17

    .line 1313
    iget-wide v0, p0, Lmiui/date/Calendar;->mMillisecond:J

    const-wide v2, -0x201b77f5c00L

    iget-object v4, p0, Lmiui/date/Calendar;->mFields:[I

    aget v4, v4, v6

    int-to-long v4, v4

    sub-long/2addr v2, v4

    iget-object v4, p0, Lmiui/date/Calendar;->mFields:[I

    aget v4, v4, v7

    int-to-long v4, v4

    sub-long/2addr v2, v4

    cmp-long v0, v0, v2

    if-ltz v0, :cond_0

    iget-wide v0, p0, Lmiui/date/Calendar;->mMillisecond:J

    const-wide v2, 0x3c314a71400L

    iget-object v4, p0, Lmiui/date/Calendar;->mFields:[I

    aget v4, v4, v6

    int-to-long v4, v4

    sub-long/2addr v2, v4

    iget-object v4, p0, Lmiui/date/Calendar;->mFields:[I

    aget v4, v4, v7

    int-to-long v4, v4

    sub-long/2addr v2, v4

    cmp-long v0, v0, v2

    if-ltz v0, :cond_1

    :cond_0
    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_1
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public set(II)Lmiui/date/Calendar;
    .locals 7

    .prologue
    const/16 v6, 0xa

    const/4 v5, 0x1

    const/16 v4, 0x8

    const/4 v2, 0x6

    const/4 v3, 0x2

    .line 961
    if-ne p1, v2, :cond_5

    .line 962
    if-gez p2, :cond_2

    .line 963
    neg-int p2, p2

    .line 964
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    aget v0, v0, v3

    invoke-static {v0}, Lmiui/date/Calendar;->m(I)I

    move-result v0

    if-eq p2, v0, :cond_0

    .line 965
    new-instance v0, Ljava/lang/IllegalArgumentException;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "year "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    iget-object v2, p0, Lmiui/date/Calendar;->mFields:[I

    aget v2, v2, v3

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, " has no such leap month:"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 967
    :cond_0
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    aput v5, v0, v4

    .line 975
    :goto_0
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    aput p2, v0, v2

    .line 976
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    aget v0, v0, v4

    if-ne v0, v5, :cond_4

    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    aget v0, v0, v3

    invoke-static {v0}, Lmiui/date/Calendar;->l(I)I

    move-result v0

    .line 979
    :goto_1
    iget-object v1, p0, Lmiui/date/Calendar;->mFields:[I

    aget v1, v1, v6

    if-le v1, v0, :cond_1

    .line 980
    iget-object v1, p0, Lmiui/date/Calendar;->mFields:[I

    aput v0, v1, v6

    .line 982
    :cond_1
    invoke-direct {p0}, Lmiui/date/Calendar;->p()V

    .line 996
    :goto_2
    return-object p0

    .line 969
    :cond_2
    invoke-virtual {p0, p1}, Lmiui/date/Calendar;->getActualMinimum(I)I

    move-result v0

    if-ge p2, v0, :cond_3

    invoke-virtual {p0, p1}, Lmiui/date/Calendar;->getActualMaximum(I)I

    move-result v0

    if-le p2, v0, :cond_3

    .line 970
    new-instance v0, Ljava/lang/IllegalArgumentException;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "value is out of date range ["

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {p0, p1}, Lmiui/date/Calendar;->getActualMinimum(I)I

    move-result v2

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, "-"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {p0, p1}, Lmiui/date/Calendar;->getActualMaximum(I)I

    move-result v2

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, "]: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 973
    :cond_3
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    const/4 v1, 0x0

    aput v1, v0, v4

    goto :goto_0

    .line 976
    :cond_4
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    aget v0, v0, v3

    iget-object v1, p0, Lmiui/date/Calendar;->mFields:[I

    aget v1, v1, v2

    invoke-static {v0, v1}, Lmiui/date/Calendar;->a(II)I

    move-result v0

    goto :goto_1

    .line 986
    :cond_5
    invoke-virtual {p0, p1}, Lmiui/date/Calendar;->getActualMinimum(I)I

    move-result v0

    if-ge p2, v0, :cond_6

    invoke-virtual {p0, p1}, Lmiui/date/Calendar;->getActualMaximum(I)I

    move-result v0

    if-le p2, v0, :cond_6

    .line 987
    new-instance v0, Ljava/lang/IllegalArgumentException;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "value is out of date range ["

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {p0, p1}, Lmiui/date/Calendar;->getActualMinimum(I)I

    move-result v2

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, "-"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {p0, p1}, Lmiui/date/Calendar;->getActualMaximum(I)I

    move-result v2

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, "]: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 992
    :cond_6
    :try_start_0
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    aget v0, v0, p1

    sub-int v0, p2, v0

    invoke-virtual {p0, p1, v0}, Lmiui/date/Calendar;->add(II)Lmiui/date/Calendar;
    :try_end_0
    .catch Ljava/lang/IllegalArgumentException; {:try_start_0 .. :try_end_0} :catch_0

    goto/16 :goto_2

    .line 993
    :catch_0
    move-exception v0

    .line 994
    new-instance v0, Ljava/lang/IllegalArgumentException;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "unsupported set field:"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    sget-object v2, Lmiui/date/Calendar;->bB:[Ljava/lang/String;

    aget-object v2, v2, p1

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

.method public set(IIIIIII)Lmiui/date/Calendar;
    .locals 3

    .prologue
    const/16 v1, 0x3b

    .line 850
    if-ltz p2, :cond_0

    const/16 v0, 0xb

    if-le p2, v0, :cond_1

    .line 851
    :cond_0
    new-instance v0, Ljava/lang/IllegalArgumentException;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "Year "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, " has no month "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 854
    :cond_1
    if-lez p3, :cond_2

    invoke-virtual {p0, p1}, Lmiui/date/Calendar;->isLeapYear(I)Z

    move-result v0

    invoke-static {v0, p2}, Lmiui/date/Calendar;->a(ZI)I

    move-result v0

    if-le p3, v0, :cond_3

    .line 855
    :cond_2
    new-instance v0, Ljava/lang/IllegalArgumentException;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "Year "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, " month "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, " has no day "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p3}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 858
    :cond_3
    if-ltz p4, :cond_4

    const/16 v0, 0x17

    if-le p4, v0, :cond_5

    .line 859
    :cond_4
    new-instance v0, Ljava/lang/IllegalArgumentException;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "Invalid hour "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p4}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 862
    :cond_5
    if-ltz p5, :cond_6

    if-le p5, v1, :cond_7

    .line 863
    :cond_6
    new-instance v0, Ljava/lang/IllegalArgumentException;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "Invalid minute "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p5}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 866
    :cond_7
    if-ltz p6, :cond_8

    if-le p6, v1, :cond_9

    .line 867
    :cond_8
    new-instance v0, Ljava/lang/IllegalArgumentException;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "Invalid second "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p6}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 870
    :cond_9
    if-ltz p7, :cond_a

    const/16 v0, 0x3e7

    if-le p7, v0, :cond_b

    .line 871
    :cond_a
    new-instance v0, Ljava/lang/IllegalArgumentException;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "Invalid millisecond "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p7}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 874
    :cond_b
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    const/4 v1, 0x1

    aput p1, v0, v1

    .line 875
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    const/4 v1, 0x5

    aput p2, v0, v1

    .line 876
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    const/16 v1, 0x9

    aput p3, v0, v1

    .line 877
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    const/16 v1, 0x12

    aput p4, v0, v1

    .line 878
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    const/16 v1, 0x14

    aput p5, v0, v1

    .line 879
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    const/16 v1, 0x15

    aput p6, v0, v1

    .line 880
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    const/16 v1, 0x16

    aput p7, v0, v1

    .line 882
    invoke-direct {p0}, Lmiui/date/Calendar;->o()V

    .line 883
    return-object p0
.end method

.method public setChineseTime(IIIZIIII)Lmiui/date/Calendar;
    .locals 3

    .prologue
    const/16 v1, 0x3b

    .line 899
    const/16 v0, 0x76c

    if-lt p1, v0, :cond_0

    const/16 v0, 0x834

    if-le p1, v0, :cond_1

    .line 900
    :cond_0
    new-instance v0, Ljava/lang/IllegalArgumentException;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "Date out of range [1900 - 2100] expected, but year is "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 903
    :cond_1
    if-ltz p2, :cond_2

    const/16 v0, 0xb

    if-le p2, v0, :cond_3

    .line 904
    :cond_2
    new-instance v0, Ljava/lang/IllegalArgumentException;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "Year "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, " has no month "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 907
    :cond_3
    if-eqz p4, :cond_4

    invoke-static {p1}, Lmiui/date/Calendar;->m(I)I

    move-result v0

    if-eq v0, p2, :cond_4

    .line 908
    new-instance v0, Ljava/lang/IllegalArgumentException;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "Year "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, " has no leap month "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 911
    :cond_4
    if-eqz p4, :cond_6

    .line 912
    if-lez p3, :cond_5

    invoke-static {p1}, Lmiui/date/Calendar;->l(I)I

    move-result v0

    if-le p3, v0, :cond_8

    .line 913
    :cond_5
    new-instance v0, Ljava/lang/IllegalArgumentException;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "Year "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, " month "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, " has no day "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p3}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 916
    :cond_6
    if-lez p3, :cond_7

    invoke-static {p1, p2}, Lmiui/date/Calendar;->a(II)I

    move-result v0

    if-le p3, v0, :cond_8

    .line 917
    :cond_7
    new-instance v0, Ljava/lang/IllegalArgumentException;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "Year "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, " month "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, " has no day "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p3}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 921
    :cond_8
    if-ltz p5, :cond_9

    const/16 v0, 0x17

    if-le p5, v0, :cond_a

    .line 922
    :cond_9
    new-instance v0, Ljava/lang/IllegalArgumentException;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "Invalid hour "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p5}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 925
    :cond_a
    if-ltz p6, :cond_b

    if-le p6, v1, :cond_c

    .line 926
    :cond_b
    new-instance v0, Ljava/lang/IllegalArgumentException;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "Invalid minute "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p6}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 929
    :cond_c
    if-ltz p7, :cond_d

    if-le p7, v1, :cond_e

    .line 930
    :cond_d
    new-instance v0, Ljava/lang/IllegalArgumentException;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "Invalid second "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p7}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 933
    :cond_e
    if-ltz p8, :cond_f

    const/16 v0, 0x3e8

    if-le p8, v0, :cond_10

    .line 934
    :cond_f
    new-instance v0, Ljava/lang/IllegalArgumentException;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "Invalid millisecond "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p8}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 937
    :cond_10
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    const/4 v1, 0x2

    aput p1, v0, v1

    .line 938
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    const/4 v1, 0x6

    aput p2, v0, v1

    .line 939
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    const/16 v1, 0xa

    aput p3, v0, v1

    .line 940
    iget-object v1, p0, Lmiui/date/Calendar;->mFields:[I

    const/16 v2, 0x8

    if-eqz p4, :cond_11

    const/4 v0, 0x1

    :goto_0
    aput v0, v1, v2

    .line 941
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    const/16 v1, 0x12

    aput p5, v0, v1

    .line 942
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    const/16 v1, 0x14

    aput p6, v0, v1

    .line 943
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    const/16 v1, 0x15

    aput p7, v0, v1

    .line 944
    iget-object v0, p0, Lmiui/date/Calendar;->mFields:[I

    const/16 v1, 0x16

    aput p8, v0, v1

    .line 946
    invoke-direct {p0}, Lmiui/date/Calendar;->p()V

    .line 947
    return-object p0

    .line 940
    :cond_11
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public setGregorianChange(J)V
    .locals 4

    .prologue
    const/4 v3, 0x0

    .line 1431
    iput-wide p1, p0, Lmiui/date/Calendar;->bJ:J

    .line 1432
    new-instance v0, Lmiui/date/Calendar;

    const-string v1, "GMT"

    invoke-static {v1}, Ljava/util/TimeZone;->getTimeZone(Ljava/lang/String;)Ljava/util/TimeZone;

    move-result-object v1

    invoke-direct {v0, v1}, Lmiui/date/Calendar;-><init>(Ljava/util/TimeZone;)V

    .line 1433
    invoke-virtual {v0, p1, p2}, Lmiui/date/Calendar;->setTimeInMillis(J)Lmiui/date/Calendar;

    .line 1435
    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Lmiui/date/Calendar;->get(I)I

    move-result v1

    iput v1, p0, Lmiui/date/Calendar;->bK:I

    .line 1436
    invoke-virtual {v0, v3}, Lmiui/date/Calendar;->get(I)I

    move-result v1

    if-nez v1, :cond_0

    .line 1437
    iget v1, p0, Lmiui/date/Calendar;->bK:I

    rsub-int/lit8 v1, v1, 0x1

    iput v1, p0, Lmiui/date/Calendar;->bK:I

    .line 1440
    :cond_0
    iget v1, p0, Lmiui/date/Calendar;->bK:I

    div-int/lit8 v1, v1, 0x64

    iget v2, p0, Lmiui/date/Calendar;->bK:I

    div-int/lit16 v2, v2, 0x190

    sub-int/2addr v1, v2

    add-int/lit8 v1, v1, -0x2

    iput v1, p0, Lmiui/date/Calendar;->bL:I

    .line 1442
    iget v1, p0, Lmiui/date/Calendar;->bK:I

    add-int/lit16 v1, v1, -0x7d0

    div-int/lit16 v1, v1, 0x190

    iget v2, p0, Lmiui/date/Calendar;->bL:I

    add-int/2addr v1, v2

    iget v2, p0, Lmiui/date/Calendar;->bK:I

    add-int/lit16 v2, v2, -0x7d0

    div-int/lit8 v2, v2, 0x64

    sub-int/2addr v1, v2

    iput v1, p0, Lmiui/date/Calendar;->bM:I

    .line 1445
    const/16 v1, 0xc

    invoke-virtual {v0, v1}, Lmiui/date/Calendar;->get(I)I

    move-result v0

    .line 1446
    iget v1, p0, Lmiui/date/Calendar;->bM:I

    if-ge v0, v1, :cond_1

    .line 1447
    add-int/lit8 v1, v0, -0x1

    iput v1, p0, Lmiui/date/Calendar;->bN:I

    .line 1448
    iget v1, p0, Lmiui/date/Calendar;->bM:I

    sub-int v0, v1, v0

    add-int/lit8 v0, v0, 0x1

    iput v0, p0, Lmiui/date/Calendar;->bO:I

    .line 1453
    :goto_0
    return-void

    .line 1450
    :cond_1
    iput v3, p0, Lmiui/date/Calendar;->bO:I

    .line 1451
    iget v0, p0, Lmiui/date/Calendar;->bM:I

    iput v0, p0, Lmiui/date/Calendar;->bN:I

    goto :goto_0
.end method

.method public setTimeInMillis(J)Lmiui/date/Calendar;
    .locals 0

    .prologue
    .line 833
    iput-wide p1, p0, Lmiui/date/Calendar;->mMillisecond:J

    .line 834
    invoke-direct {p0}, Lmiui/date/Calendar;->q()V

    .line 835
    return-object p0
.end method

.method public setTimeZone(Ljava/util/TimeZone;)Lmiui/date/Calendar;
    .locals 2

    .prologue
    .line 800
    if-nez p1, :cond_0

    .line 801
    invoke-static {}, Ljava/util/TimeZone;->getDefault()Ljava/util/TimeZone;

    move-result-object p1

    .line 804
    :cond_0
    iget-object v0, p0, Lmiui/date/Calendar;->mTimeZone:Ljava/util/TimeZone;

    if-eqz v0, :cond_1

    iget-object v0, p0, Lmiui/date/Calendar;->mTimeZone:Ljava/util/TimeZone;

    invoke-virtual {v0}, Ljava/util/TimeZone;->getID()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p1}, Ljava/util/TimeZone;->getID()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_2

    .line 805
    :cond_1
    iput-object p1, p0, Lmiui/date/Calendar;->mTimeZone:Ljava/util/TimeZone;

    .line 806
    invoke-direct {p0}, Lmiui/date/Calendar;->q()V

    .line 808
    :cond_2
    return-object p0
.end method

.method public toString()Ljava/lang/String;
    .locals 3

    .prologue
    .line 1626
    invoke-static {}, Lmiui/util/Pools;->getStringBuilderPool()Lmiui/util/Pools$Pool;

    move-result-object v0

    invoke-interface {v0}, Lmiui/util/Pools$Pool;->acquire()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/StringBuilder;

    .line 1627
    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/Class;->getName()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1628
    const-string v1, "[time"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1629
    iget-wide v1, p0, Lmiui/date/Calendar;->mMillisecond:J

    invoke-virtual {v0, v1, v2}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    .line 1630
    const-string v1, ",zone="

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1631
    iget-object v1, p0, Lmiui/date/Calendar;->mTimeZone:Ljava/util/TimeZone;

    invoke-virtual {v1}, Ljava/util/TimeZone;->getID()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1633
    const/4 v1, 0x0

    :goto_0
    const/16 v2, 0x19

    if-ge v1, v2, :cond_0

    .line 1634
    const/16 v2, 0x2c

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    .line 1635
    sget-object v2, Lmiui/date/Calendar;->bB:[Ljava/lang/String;

    aget-object v2, v2, v1

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1636
    const/16 v2, 0x3d

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    .line 1637
    iget-object v2, p0, Lmiui/date/Calendar;->mFields:[I

    aget v2, v2, v1

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    .line 1633
    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    .line 1639
    :cond_0
    const/16 v1, 0x5d

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    .line 1640
    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    .line 1641
    invoke-static {}, Lmiui/util/Pools;->getStringBuilderPool()Lmiui/util/Pools$Pool;

    move-result-object v2

    invoke-interface {v2, v0}, Lmiui/util/Pools$Pool;->release(Ljava/lang/Object;)V

    .line 1642
    return-object v1
.end method
