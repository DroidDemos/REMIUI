.class final enum Lmiui/text/SortKeyGenerator$NameStyle;
.super Ljava/lang/Enum;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/text/SortKeyGenerator;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x401a
    name = "NameStyle"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Enum",
        "<",
        "Lmiui/text/SortKeyGenerator$NameStyle;",
        ">;"
    }
.end annotation


# static fields
.field public static final enum pW:Lmiui/text/SortKeyGenerator$NameStyle;

.field public static final enum pX:Lmiui/text/SortKeyGenerator$NameStyle;

.field public static final enum pY:Lmiui/text/SortKeyGenerator$NameStyle;

.field public static final enum pZ:Lmiui/text/SortKeyGenerator$NameStyle;

.field public static final enum qa:Lmiui/text/SortKeyGenerator$NameStyle;

.field public static final enum qb:Lmiui/text/SortKeyGenerator$NameStyle;

.field private static final synthetic qc:[Lmiui/text/SortKeyGenerator$NameStyle;


# direct methods
.method static constructor <clinit>()V
    .locals 8

    .prologue
    const/4 v7, 0x4

    const/4 v6, 0x3

    const/4 v5, 0x2

    const/4 v4, 0x1

    const/4 v3, 0x0

    .line 70
    new-instance v0, Lmiui/text/SortKeyGenerator$NameStyle;

    const-string v1, "UNDEFINED"

    invoke-direct {v0, v1, v3}, Lmiui/text/SortKeyGenerator$NameStyle;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lmiui/text/SortKeyGenerator$NameStyle;->pW:Lmiui/text/SortKeyGenerator$NameStyle;

    .line 71
    new-instance v0, Lmiui/text/SortKeyGenerator$NameStyle;

    const-string v1, "WESTERN"

    invoke-direct {v0, v1, v4}, Lmiui/text/SortKeyGenerator$NameStyle;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lmiui/text/SortKeyGenerator$NameStyle;->pX:Lmiui/text/SortKeyGenerator$NameStyle;

    .line 72
    new-instance v0, Lmiui/text/SortKeyGenerator$NameStyle;

    const-string v1, "CJK"

    invoke-direct {v0, v1, v5}, Lmiui/text/SortKeyGenerator$NameStyle;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lmiui/text/SortKeyGenerator$NameStyle;->pY:Lmiui/text/SortKeyGenerator$NameStyle;

    .line 73
    new-instance v0, Lmiui/text/SortKeyGenerator$NameStyle;

    const-string v1, "CHINESE"

    invoke-direct {v0, v1, v6}, Lmiui/text/SortKeyGenerator$NameStyle;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lmiui/text/SortKeyGenerator$NameStyle;->pZ:Lmiui/text/SortKeyGenerator$NameStyle;

    .line 74
    new-instance v0, Lmiui/text/SortKeyGenerator$NameStyle;

    const-string v1, "JAPANESE"

    invoke-direct {v0, v1, v7}, Lmiui/text/SortKeyGenerator$NameStyle;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lmiui/text/SortKeyGenerator$NameStyle;->qa:Lmiui/text/SortKeyGenerator$NameStyle;

    .line 75
    new-instance v0, Lmiui/text/SortKeyGenerator$NameStyle;

    const-string v1, "KOREAN"

    const/4 v2, 0x5

    invoke-direct {v0, v1, v2}, Lmiui/text/SortKeyGenerator$NameStyle;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lmiui/text/SortKeyGenerator$NameStyle;->qb:Lmiui/text/SortKeyGenerator$NameStyle;

    .line 69
    const/4 v0, 0x6

    new-array v0, v0, [Lmiui/text/SortKeyGenerator$NameStyle;

    sget-object v1, Lmiui/text/SortKeyGenerator$NameStyle;->pW:Lmiui/text/SortKeyGenerator$NameStyle;

    aput-object v1, v0, v3

    sget-object v1, Lmiui/text/SortKeyGenerator$NameStyle;->pX:Lmiui/text/SortKeyGenerator$NameStyle;

    aput-object v1, v0, v4

    sget-object v1, Lmiui/text/SortKeyGenerator$NameStyle;->pY:Lmiui/text/SortKeyGenerator$NameStyle;

    aput-object v1, v0, v5

    sget-object v1, Lmiui/text/SortKeyGenerator$NameStyle;->pZ:Lmiui/text/SortKeyGenerator$NameStyle;

    aput-object v1, v0, v6

    sget-object v1, Lmiui/text/SortKeyGenerator$NameStyle;->qa:Lmiui/text/SortKeyGenerator$NameStyle;

    aput-object v1, v0, v7

    const/4 v1, 0x5

    sget-object v2, Lmiui/text/SortKeyGenerator$NameStyle;->qb:Lmiui/text/SortKeyGenerator$NameStyle;

    aput-object v2, v0, v1

    sput-object v0, Lmiui/text/SortKeyGenerator$NameStyle;->qc:[Lmiui/text/SortKeyGenerator$NameStyle;

    return-void
.end method

.method private constructor <init>(Ljava/lang/String;I)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()V"
        }
    .end annotation

    .prologue
    .line 69
    invoke-direct {p0, p1, p2}, Ljava/lang/Enum;-><init>(Ljava/lang/String;I)V

    return-void
.end method

.method private static a(Ljava/lang/String;I)Lmiui/text/SortKeyGenerator$NameStyle;
    .locals 4

    .prologue
    .line 114
    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result v0

    .line 115
    :goto_0
    if-ge p1, v0, :cond_3

    .line 116
    invoke-static {p0, p1}, Ljava/lang/Character;->codePointAt(Ljava/lang/CharSequence;I)I

    move-result v1

    .line 117
    invoke-static {v1}, Ljava/lang/Character;->isLetter(I)Z

    move-result v2

    if-eqz v2, :cond_2

    .line 118
    invoke-static {v1}, Ljava/lang/Character$UnicodeBlock;->of(I)Ljava/lang/Character$UnicodeBlock;

    move-result-object v2

    .line 119
    invoke-static {v2}, Lmiui/text/SortKeyGenerator$NameStyle;->d(Ljava/lang/Character$UnicodeBlock;)Z

    move-result v3

    if-eqz v3, :cond_0

    .line 120
    sget-object v0, Lmiui/text/SortKeyGenerator$NameStyle;->qa:Lmiui/text/SortKeyGenerator$NameStyle;

    .line 133
    :goto_1
    return-object v0

    .line 122
    :cond_0
    invoke-static {v2}, Lmiui/text/SortKeyGenerator$NameStyle;->c(Ljava/lang/Character$UnicodeBlock;)Z

    move-result v2

    if-eqz v2, :cond_1

    .line 123
    sget-object v0, Lmiui/text/SortKeyGenerator$NameStyle;->qb:Lmiui/text/SortKeyGenerator$NameStyle;

    goto :goto_1

    .line 125
    :cond_1
    invoke-static {v1}, Ljava/lang/Character;->toChars(I)[C

    move-result-object v2

    .line 126
    invoke-static {v2}, Lmiui/text/SortKeyGenerator$NameStyle;->a([C)Z

    move-result v2

    if-eqz v2, :cond_2

    .line 127
    sget-object v0, Lmiui/text/SortKeyGenerator$NameStyle;->pZ:Lmiui/text/SortKeyGenerator$NameStyle;

    goto :goto_1

    .line 130
    :cond_2
    invoke-static {v1}, Ljava/lang/Character;->charCount(I)I

    move-result v1

    add-int/2addr p1, v1

    .line 131
    goto :goto_0

    .line 133
    :cond_3
    sget-object v0, Lmiui/text/SortKeyGenerator$NameStyle;->pY:Lmiui/text/SortKeyGenerator$NameStyle;

    goto :goto_1
.end method

.method private static a(Ljava/lang/Character$UnicodeBlock;)Z
    .locals 1

    .prologue
    .line 137
    sget-object v0, Ljava/lang/Character$UnicodeBlock;->BASIC_LATIN:Ljava/lang/Character$UnicodeBlock;

    if-eq p0, v0, :cond_0

    sget-object v0, Ljava/lang/Character$UnicodeBlock;->LATIN_1_SUPPLEMENT:Ljava/lang/Character$UnicodeBlock;

    if-eq p0, v0, :cond_0

    sget-object v0, Ljava/lang/Character$UnicodeBlock;->LATIN_EXTENDED_A:Ljava/lang/Character$UnicodeBlock;

    if-eq p0, v0, :cond_0

    sget-object v0, Ljava/lang/Character$UnicodeBlock;->LATIN_EXTENDED_B:Ljava/lang/Character$UnicodeBlock;

    if-eq p0, v0, :cond_0

    sget-object v0, Ljava/lang/Character$UnicodeBlock;->LATIN_EXTENDED_ADDITIONAL:Ljava/lang/Character$UnicodeBlock;

    if-ne p0, v0, :cond_1

    :cond_0
    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_1
    const/4 v0, 0x0

    goto :goto_0
.end method

.method private static a([C)Z
    .locals 5

    .prologue
    const/4 v0, 0x0

    .line 170
    array-length v2, p0

    move v1, v0

    :goto_0
    if-ge v1, v2, :cond_0

    aget-char v3, p0, v1

    .line 171
    invoke-static {}, Lmiui/text/ChinesePinyinConverter;->getInstance()Lmiui/text/ChinesePinyinConverter;

    move-result-object v4

    invoke-virtual {v4, v3}, Lmiui/text/ChinesePinyinConverter;->isChinesePinyinKnown(C)Z

    move-result v3

    if-eqz v3, :cond_1

    .line 172
    const/4 v0, 0x1

    .line 175
    :cond_0
    return v0

    .line 170
    :cond_1
    add-int/lit8 v1, v1, 0x1

    goto :goto_0
.end method

.method private static b(Ljava/lang/Character$UnicodeBlock;)Z
    .locals 1

    .prologue
    .line 145
    sget-object v0, Ljava/lang/Character$UnicodeBlock;->CJK_UNIFIED_IDEOGRAPHS:Ljava/lang/Character$UnicodeBlock;

    if-eq p0, v0, :cond_0

    sget-object v0, Ljava/lang/Character$UnicodeBlock;->CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A:Ljava/lang/Character$UnicodeBlock;

    if-eq p0, v0, :cond_0

    sget-object v0, Ljava/lang/Character$UnicodeBlock;->CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B:Ljava/lang/Character$UnicodeBlock;

    if-eq p0, v0, :cond_0

    sget-object v0, Ljava/lang/Character$UnicodeBlock;->CJK_SYMBOLS_AND_PUNCTUATION:Ljava/lang/Character$UnicodeBlock;

    if-eq p0, v0, :cond_0

    sget-object v0, Ljava/lang/Character$UnicodeBlock;->CJK_RADICALS_SUPPLEMENT:Ljava/lang/Character$UnicodeBlock;

    if-eq p0, v0, :cond_0

    sget-object v0, Ljava/lang/Character$UnicodeBlock;->CJK_COMPATIBILITY:Ljava/lang/Character$UnicodeBlock;

    if-eq p0, v0, :cond_0

    sget-object v0, Ljava/lang/Character$UnicodeBlock;->CJK_COMPATIBILITY_FORMS:Ljava/lang/Character$UnicodeBlock;

    if-eq p0, v0, :cond_0

    sget-object v0, Ljava/lang/Character$UnicodeBlock;->CJK_COMPATIBILITY_IDEOGRAPHS:Ljava/lang/Character$UnicodeBlock;

    if-eq p0, v0, :cond_0

    sget-object v0, Ljava/lang/Character$UnicodeBlock;->CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT:Ljava/lang/Character$UnicodeBlock;

    if-ne p0, v0, :cond_1

    :cond_0
    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_1
    const/4 v0, 0x0

    goto :goto_0
.end method

.method private static c(Ljava/lang/Character$UnicodeBlock;)Z
    .locals 1

    .prologue
    .line 157
    sget-object v0, Ljava/lang/Character$UnicodeBlock;->HANGUL_SYLLABLES:Ljava/lang/Character$UnicodeBlock;

    if-eq p0, v0, :cond_0

    sget-object v0, Ljava/lang/Character$UnicodeBlock;->HANGUL_JAMO:Ljava/lang/Character$UnicodeBlock;

    if-eq p0, v0, :cond_0

    sget-object v0, Ljava/lang/Character$UnicodeBlock;->HANGUL_COMPATIBILITY_JAMO:Ljava/lang/Character$UnicodeBlock;

    if-ne p0, v0, :cond_1

    :cond_0
    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_1
    const/4 v0, 0x0

    goto :goto_0
.end method

.method private static d(Ljava/lang/Character$UnicodeBlock;)Z
    .locals 1

    .prologue
    .line 163
    sget-object v0, Ljava/lang/Character$UnicodeBlock;->KATAKANA:Ljava/lang/Character$UnicodeBlock;

    if-eq p0, v0, :cond_0

    sget-object v0, Ljava/lang/Character$UnicodeBlock;->KATAKANA_PHONETIC_EXTENSIONS:Ljava/lang/Character$UnicodeBlock;

    if-eq p0, v0, :cond_0

    sget-object v0, Ljava/lang/Character$UnicodeBlock;->HALFWIDTH_AND_FULLWIDTH_FORMS:Ljava/lang/Character$UnicodeBlock;

    if-eq p0, v0, :cond_0

    sget-object v0, Ljava/lang/Character$UnicodeBlock;->HIRAGANA:Ljava/lang/Character$UnicodeBlock;

    if-ne p0, v0, :cond_1

    :cond_0
    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_1
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public static o(Ljava/lang/String;)Lmiui/text/SortKeyGenerator$NameStyle;
    .locals 6

    .prologue
    .line 78
    if-nez p0, :cond_1

    .line 79
    sget-object v0, Lmiui/text/SortKeyGenerator$NameStyle;->pW:Lmiui/text/SortKeyGenerator$NameStyle;

    .line 110
    :cond_0
    :goto_0
    return-object v0

    .line 82
    :cond_1
    sget-object v1, Lmiui/text/SortKeyGenerator$NameStyle;->pW:Lmiui/text/SortKeyGenerator$NameStyle;

    .line 83
    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result v2

    .line 84
    const/4 v0, 0x0

    move v5, v0

    move-object v0, v1

    move v1, v5

    .line 85
    :goto_1
    if-ge v1, v2, :cond_0

    .line 86
    invoke-static {p0, v1}, Ljava/lang/Character;->codePointAt(Ljava/lang/CharSequence;I)I

    move-result v3

    .line 87
    invoke-static {v3}, Ljava/lang/Character;->isLetter(I)Z

    move-result v4

    if-eqz v4, :cond_5

    .line 88
    invoke-static {v3}, Ljava/lang/Character$UnicodeBlock;->of(I)Ljava/lang/Character$UnicodeBlock;

    move-result-object v0

    .line 90
    invoke-static {v0}, Lmiui/text/SortKeyGenerator$NameStyle;->a(Ljava/lang/Character$UnicodeBlock;)Z

    move-result v4

    if-nez v4, :cond_4

    .line 92
    invoke-static {v0}, Lmiui/text/SortKeyGenerator$NameStyle;->b(Ljava/lang/Character$UnicodeBlock;)Z

    move-result v4

    if-eqz v4, :cond_2

    .line 95
    invoke-static {p0, v1}, Lmiui/text/SortKeyGenerator$NameStyle;->a(Ljava/lang/String;I)Lmiui/text/SortKeyGenerator$NameStyle;

    move-result-object v0

    goto :goto_0

    .line 98
    :cond_2
    invoke-static {v0}, Lmiui/text/SortKeyGenerator$NameStyle;->d(Ljava/lang/Character$UnicodeBlock;)Z

    move-result v4

    if-eqz v4, :cond_3

    .line 99
    sget-object v0, Lmiui/text/SortKeyGenerator$NameStyle;->qa:Lmiui/text/SortKeyGenerator$NameStyle;

    goto :goto_0

    .line 102
    :cond_3
    invoke-static {v0}, Lmiui/text/SortKeyGenerator$NameStyle;->c(Ljava/lang/Character$UnicodeBlock;)Z

    move-result v0

    if-eqz v0, :cond_4

    .line 103
    sget-object v0, Lmiui/text/SortKeyGenerator$NameStyle;->qb:Lmiui/text/SortKeyGenerator$NameStyle;

    goto :goto_0

    .line 106
    :cond_4
    sget-object v0, Lmiui/text/SortKeyGenerator$NameStyle;->pX:Lmiui/text/SortKeyGenerator$NameStyle;

    .line 108
    :cond_5
    invoke-static {v3}, Ljava/lang/Character;->charCount(I)I

    move-result v3

    add-int/2addr v1, v3

    .line 109
    goto :goto_1
.end method

.method public static valueOf(Ljava/lang/String;)Lmiui/text/SortKeyGenerator$NameStyle;
    .locals 1

    .prologue
    .line 69
    const-class v0, Lmiui/text/SortKeyGenerator$NameStyle;

    invoke-static {v0, p0}, Ljava/lang/Enum;->valueOf(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Enum;

    move-result-object v0

    check-cast v0, Lmiui/text/SortKeyGenerator$NameStyle;

    return-object v0
.end method

.method public static values()[Lmiui/text/SortKeyGenerator$NameStyle;
    .locals 1

    .prologue
    .line 69
    sget-object v0, Lmiui/text/SortKeyGenerator$NameStyle;->qc:[Lmiui/text/SortKeyGenerator$NameStyle;

    invoke-virtual {v0}, [Lmiui/text/SortKeyGenerator$NameStyle;->clone()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Lmiui/text/SortKeyGenerator$NameStyle;

    return-object v0
.end method
