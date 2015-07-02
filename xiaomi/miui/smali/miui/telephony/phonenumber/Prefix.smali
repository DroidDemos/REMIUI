.class public Lmiui/telephony/phonenumber/Prefix;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field public static final EMPTY:Ljava/lang/String; = ""

.field public static final PREFIX_10193:Ljava/lang/String; = "10193"

.field public static final PREFIX_11808:Ljava/lang/String; = "11808"

.field public static final PREFIX_12520:Ljava/lang/String; = "12520"

.field public static final PREFIX_12583:Ljava/lang/String; = "12583"

.field public static final PREFIX_12593:Ljava/lang/String; = "12593"

.field public static final PREFIX_17900:Ljava/lang/String; = "17900"

.field public static final PREFIX_17901:Ljava/lang/String; = "17901"

.field public static final PREFIX_17908:Ljava/lang/String; = "17908"

.field public static final PREFIX_17909:Ljava/lang/String; = "17909"

.field public static final PREFIX_17911:Ljava/lang/String; = "17911"

.field public static final PREFIX_17950:Ljava/lang/String; = "17950"

.field public static final PREFIX_17951:Ljava/lang/String; = "17951"

.field public static final PREFIX_17960:Ljava/lang/String; = "17960"

.field public static final PREFIX_17961:Ljava/lang/String; = "17961"

.field public static final PREFIX_17968:Ljava/lang/String; = "17968"

.field public static final PREFIX_17969:Ljava/lang/String; = "17969"

.field public static final PREFIX_17990:Ljava/lang/String; = "17990"

.field public static final PREFIX_17991:Ljava/lang/String; = "17991"

.field public static final PREFIX_17995:Ljava/lang/String; = "17995"

.field public static final PREFIX_17996:Ljava/lang/String; = "17996"

.field public static final SMS_PREFIXES:[Ljava/lang/String;


# direct methods
.method static constructor <clinit>()V
    .locals 3

    .prologue
    .line 32
    const/4 v0, 0x1

    new-array v0, v0, [Ljava/lang/String;

    const/4 v1, 0x0

    const-string v2, "12520"

    aput-object v2, v0, v1

    sput-object v0, Lmiui/telephony/phonenumber/Prefix;->SMS_PREFIXES:[Ljava/lang/String;

    return-void
.end method

.method public constructor <init>()V
    .locals 0

    .prologue
    .line 5
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method public static isSmsPrefix(Ljava/lang/String;)Z
    .locals 5

    .prologue
    const/4 v0, 0x0

    .line 108
    invoke-static {p0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v1

    if-nez v1, :cond_0

    .line 109
    sget-object v2, Lmiui/telephony/phonenumber/Prefix;->SMS_PREFIXES:[Ljava/lang/String;

    array-length v3, v2

    move v1, v0

    :goto_0
    if-ge v1, v3, :cond_0

    aget-object v4, v2, v1

    .line 110
    invoke-virtual {v4, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v4

    if-eqz v4, :cond_1

    .line 111
    const/4 v0, 0x1

    .line 115
    :cond_0
    return v0

    .line 109
    :cond_1
    add-int/lit8 v1, v1, 0x1

    goto :goto_0
.end method

.method public static parse(Ljava/lang/StringBuffer;II)Ljava/lang/String;
    .locals 7

    .prologue
    const/16 v6, 0x38

    const/16 v5, 0x36

    const/16 v4, 0x31

    const/16 v3, 0x39

    const/16 v2, 0x30

    .line 37
    if-gtz p2, :cond_0

    .line 38
    const-string v0, ""

    .line 104
    :goto_0
    return-object v0

    .line 41
    :cond_0
    invoke-virtual {p0, p1}, Ljava/lang/StringBuffer;->charAt(I)C

    move-result v0

    packed-switch v0, :pswitch_data_0

    .line 104
    :cond_1
    :goto_1
    const-string v0, ""

    goto :goto_0

    .line 43
    :pswitch_0
    const/4 v0, 0x4

    if-le p2, v0, :cond_1

    .line 44
    add-int/lit8 v0, p1, 0x1

    invoke-virtual {p0, v0}, Ljava/lang/StringBuffer;->charAt(I)C

    move-result v0

    packed-switch v0, :pswitch_data_1

    :pswitch_1
    goto :goto_1

    .line 46
    :pswitch_2
    add-int/lit8 v0, p1, 0x2

    invoke-virtual {p0, v0}, Ljava/lang/StringBuffer;->charAt(I)C

    move-result v0

    if-ne v0, v4, :cond_1

    .line 47
    add-int/lit8 v0, p1, 0x3

    invoke-virtual {p0, v0}, Ljava/lang/StringBuffer;->charAt(I)C

    move-result v0

    if-ne v0, v3, :cond_1

    add-int/lit8 v0, p1, 0x4

    invoke-virtual {p0, v0}, Ljava/lang/StringBuffer;->charAt(I)C

    move-result v0

    const/16 v1, 0x33

    if-ne v0, v1, :cond_1

    const-string v0, "10193"

    goto :goto_0

    .line 51
    :pswitch_3
    add-int/lit8 v0, p1, 0x2

    invoke-virtual {p0, v0}, Ljava/lang/StringBuffer;->charAt(I)C

    move-result v0

    if-ne v0, v6, :cond_1

    .line 52
    add-int/lit8 v0, p1, 0x3

    invoke-virtual {p0, v0}, Ljava/lang/StringBuffer;->charAt(I)C

    move-result v0

    if-ne v0, v2, :cond_1

    add-int/lit8 v0, p1, 0x4

    invoke-virtual {p0, v0}, Ljava/lang/StringBuffer;->charAt(I)C

    move-result v0

    if-ne v0, v6, :cond_1

    const-string v0, "11808"

    goto :goto_0

    .line 56
    :pswitch_4
    add-int/lit8 v0, p1, 0x2

    invoke-virtual {p0, v0}, Ljava/lang/StringBuffer;->charAt(I)C

    move-result v0

    const/16 v1, 0x35

    if-ne v0, v1, :cond_1

    .line 57
    add-int/lit8 v0, p1, 0x3

    invoke-virtual {p0, v0}, Ljava/lang/StringBuffer;->charAt(I)C

    move-result v0

    if-ne v0, v6, :cond_2

    add-int/lit8 v0, p1, 0x4

    invoke-virtual {p0, v0}, Ljava/lang/StringBuffer;->charAt(I)C

    move-result v0

    const/16 v1, 0x33

    if-ne v0, v1, :cond_2

    const-string v0, "12583"

    goto :goto_0

    .line 58
    :cond_2
    add-int/lit8 v0, p1, 0x3

    invoke-virtual {p0, v0}, Ljava/lang/StringBuffer;->charAt(I)C

    move-result v0

    if-ne v0, v3, :cond_3

    add-int/lit8 v0, p1, 0x4

    invoke-virtual {p0, v0}, Ljava/lang/StringBuffer;->charAt(I)C

    move-result v0

    const/16 v1, 0x33

    if-ne v0, v1, :cond_3

    const-string v0, "12593"

    goto/16 :goto_0

    .line 59
    :cond_3
    add-int/lit8 v0, p1, 0x3

    invoke-virtual {p0, v0}, Ljava/lang/StringBuffer;->charAt(I)C

    move-result v0

    const/16 v1, 0x32

    if-ne v0, v1, :cond_1

    add-int/lit8 v0, p1, 0x4

    invoke-virtual {p0, v0}, Ljava/lang/StringBuffer;->charAt(I)C

    move-result v0

    if-ne v0, v2, :cond_1

    const-string v0, "12520"

    goto/16 :goto_0

    .line 63
    :pswitch_5
    add-int/lit8 v0, p1, 0x2

    invoke-virtual {p0, v0}, Ljava/lang/StringBuffer;->charAt(I)C

    move-result v0

    if-ne v0, v3, :cond_1

    .line 64
    add-int/lit8 v0, p1, 0x3

    invoke-virtual {p0, v0}, Ljava/lang/StringBuffer;->charAt(I)C

    move-result v0

    if-ne v0, v2, :cond_4

    add-int/lit8 v0, p1, 0x4

    invoke-virtual {p0, v0}, Ljava/lang/StringBuffer;->charAt(I)C

    move-result v0

    if-ne v0, v2, :cond_4

    const-string v0, "17900"

    goto/16 :goto_0

    .line 65
    :cond_4
    add-int/lit8 v0, p1, 0x3

    invoke-virtual {p0, v0}, Ljava/lang/StringBuffer;->charAt(I)C

    move-result v0

    if-ne v0, v2, :cond_5

    add-int/lit8 v0, p1, 0x4

    invoke-virtual {p0, v0}, Ljava/lang/StringBuffer;->charAt(I)C

    move-result v0

    if-ne v0, v4, :cond_5

    const-string v0, "17901"

    goto/16 :goto_0

    .line 66
    :cond_5
    add-int/lit8 v0, p1, 0x3

    invoke-virtual {p0, v0}, Ljava/lang/StringBuffer;->charAt(I)C

    move-result v0

    if-ne v0, v2, :cond_6

    add-int/lit8 v0, p1, 0x4

    invoke-virtual {p0, v0}, Ljava/lang/StringBuffer;->charAt(I)C

    move-result v0

    if-ne v0, v6, :cond_6

    const-string v0, "17908"

    goto/16 :goto_0

    .line 67
    :cond_6
    add-int/lit8 v0, p1, 0x3

    invoke-virtual {p0, v0}, Ljava/lang/StringBuffer;->charAt(I)C

    move-result v0

    if-ne v0, v2, :cond_7

    add-int/lit8 v0, p1, 0x4

    invoke-virtual {p0, v0}, Ljava/lang/StringBuffer;->charAt(I)C

    move-result v0

    if-ne v0, v3, :cond_7

    const-string v0, "17909"

    goto/16 :goto_0

    .line 68
    :cond_7
    add-int/lit8 v0, p1, 0x3

    invoke-virtual {p0, v0}, Ljava/lang/StringBuffer;->charAt(I)C

    move-result v0

    if-ne v0, v4, :cond_8

    add-int/lit8 v0, p1, 0x4

    invoke-virtual {p0, v0}, Ljava/lang/StringBuffer;->charAt(I)C

    move-result v0

    if-ne v0, v4, :cond_8

    const-string v0, "17911"

    goto/16 :goto_0

    .line 69
    :cond_8
    add-int/lit8 v0, p1, 0x3

    invoke-virtual {p0, v0}, Ljava/lang/StringBuffer;->charAt(I)C

    move-result v0

    const/16 v1, 0x35

    if-ne v0, v1, :cond_9

    add-int/lit8 v0, p1, 0x4

    invoke-virtual {p0, v0}, Ljava/lang/StringBuffer;->charAt(I)C

    move-result v0

    if-ne v0, v2, :cond_9

    const-string v0, "17950"

    goto/16 :goto_0

    .line 70
    :cond_9
    add-int/lit8 v0, p1, 0x3

    invoke-virtual {p0, v0}, Ljava/lang/StringBuffer;->charAt(I)C

    move-result v0

    const/16 v1, 0x35

    if-ne v0, v1, :cond_a

    add-int/lit8 v0, p1, 0x4

    invoke-virtual {p0, v0}, Ljava/lang/StringBuffer;->charAt(I)C

    move-result v0

    if-ne v0, v4, :cond_a

    const-string v0, "17951"

    goto/16 :goto_0

    .line 71
    :cond_a
    add-int/lit8 v0, p1, 0x3

    invoke-virtual {p0, v0}, Ljava/lang/StringBuffer;->charAt(I)C

    move-result v0

    if-ne v0, v5, :cond_b

    add-int/lit8 v0, p1, 0x4

    invoke-virtual {p0, v0}, Ljava/lang/StringBuffer;->charAt(I)C

    move-result v0

    if-ne v0, v2, :cond_b

    const-string v0, "17960"

    goto/16 :goto_0

    .line 72
    :cond_b
    add-int/lit8 v0, p1, 0x3

    invoke-virtual {p0, v0}, Ljava/lang/StringBuffer;->charAt(I)C

    move-result v0

    if-ne v0, v5, :cond_c

    add-int/lit8 v0, p1, 0x4

    invoke-virtual {p0, v0}, Ljava/lang/StringBuffer;->charAt(I)C

    move-result v0

    if-ne v0, v4, :cond_c

    const-string v0, "17961"

    goto/16 :goto_0

    .line 73
    :cond_c
    add-int/lit8 v0, p1, 0x3

    invoke-virtual {p0, v0}, Ljava/lang/StringBuffer;->charAt(I)C

    move-result v0

    if-ne v0, v5, :cond_d

    add-int/lit8 v0, p1, 0x4

    invoke-virtual {p0, v0}, Ljava/lang/StringBuffer;->charAt(I)C

    move-result v0

    if-ne v0, v6, :cond_d

    const-string v0, "17968"

    goto/16 :goto_0

    .line 74
    :cond_d
    add-int/lit8 v0, p1, 0x3

    invoke-virtual {p0, v0}, Ljava/lang/StringBuffer;->charAt(I)C

    move-result v0

    if-ne v0, v5, :cond_e

    add-int/lit8 v0, p1, 0x4

    invoke-virtual {p0, v0}, Ljava/lang/StringBuffer;->charAt(I)C

    move-result v0

    if-ne v0, v3, :cond_e

    const-string v0, "17969"

    goto/16 :goto_0

    .line 75
    :cond_e
    add-int/lit8 v0, p1, 0x3

    invoke-virtual {p0, v0}, Ljava/lang/StringBuffer;->charAt(I)C

    move-result v0

    if-ne v0, v3, :cond_f

    add-int/lit8 v0, p1, 0x4

    invoke-virtual {p0, v0}, Ljava/lang/StringBuffer;->charAt(I)C

    move-result v0

    if-ne v0, v2, :cond_f

    const-string v0, "17990"

    goto/16 :goto_0

    .line 76
    :cond_f
    add-int/lit8 v0, p1, 0x3

    invoke-virtual {p0, v0}, Ljava/lang/StringBuffer;->charAt(I)C

    move-result v0

    if-ne v0, v3, :cond_10

    add-int/lit8 v0, p1, 0x4

    invoke-virtual {p0, v0}, Ljava/lang/StringBuffer;->charAt(I)C

    move-result v0

    if-ne v0, v4, :cond_10

    const-string v0, "17991"

    goto/16 :goto_0

    .line 77
    :cond_10
    add-int/lit8 v0, p1, 0x3

    invoke-virtual {p0, v0}, Ljava/lang/StringBuffer;->charAt(I)C

    move-result v0

    if-ne v0, v3, :cond_11

    add-int/lit8 v0, p1, 0x4

    invoke-virtual {p0, v0}, Ljava/lang/StringBuffer;->charAt(I)C

    move-result v0

    const/16 v1, 0x35

    if-ne v0, v1, :cond_11

    const-string v0, "17995"

    goto/16 :goto_0

    .line 78
    :cond_11
    add-int/lit8 v0, p1, 0x3

    invoke-virtual {p0, v0}, Ljava/lang/StringBuffer;->charAt(I)C

    move-result v0

    if-ne v0, v3, :cond_1

    add-int/lit8 v0, p1, 0x4

    invoke-virtual {p0, v0}, Ljava/lang/StringBuffer;->charAt(I)C

    move-result v0

    if-ne v0, v5, :cond_1

    const-string v0, "17996"

    goto/16 :goto_0

    .line 91
    :pswitch_6
    const/16 v0, 0xa

    if-lt p2, v0, :cond_1

    .line 92
    add-int/lit8 v0, p1, 0x1

    invoke-virtual {p0, v0}, Ljava/lang/StringBuffer;->charAt(I)C

    move-result v0

    packed-switch v0, :pswitch_data_2

    goto/16 :goto_1

    .line 94
    :pswitch_7
    add-int/lit8 v0, p1, 0x2

    invoke-virtual {p0, v0}, Ljava/lang/StringBuffer;->charAt(I)C

    move-result v0

    if-lt v0, v4, :cond_1

    add-int/lit8 v0, p1, 0x2

    invoke-virtual {p0, v0}, Ljava/lang/StringBuffer;->charAt(I)C

    move-result v0

    if-gt v0, v3, :cond_1

    .line 95
    add-int/lit8 v0, p1, 0x3

    invoke-virtual {p0, p1, v0}, Ljava/lang/StringBuffer;->substring(II)Ljava/lang/String;

    move-result-object v0

    goto/16 :goto_0

    .line 41
    :pswitch_data_0
    .packed-switch 0x31
        :pswitch_0
        :pswitch_6
        :pswitch_6
        :pswitch_6
        :pswitch_6
        :pswitch_6
        :pswitch_6
        :pswitch_6
    .end packed-switch

    .line 44
    :pswitch_data_1
    .packed-switch 0x30
        :pswitch_2
        :pswitch_3
        :pswitch_4
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_5
    .end packed-switch

    .line 92
    :pswitch_data_2
    .packed-switch 0x30
        :pswitch_7
    .end packed-switch
.end method
