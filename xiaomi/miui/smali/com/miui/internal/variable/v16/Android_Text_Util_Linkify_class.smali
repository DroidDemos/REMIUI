.class public Lcom/miui/internal/variable/v16/Android_Text_Util_Linkify_class;
.super Lcom/miui/internal/variable/Android_Text_Util_Linkify_class;
.source "SourceFile"


# static fields
.field protected static final mApplyLink:Lmiui/reflect/Method;

.field protected static final mEnd:Lmiui/reflect/Field;

.field protected static final mGatherLinks:Lmiui/reflect/Method;

.field protected static final mGatherMapLinks:Lmiui/reflect/Method;

.field protected static final mPruneOverlaps:Lmiui/reflect/Method;

.field protected static final mStart:Lmiui/reflect/Field;

.field protected static final mUrl:Lmiui/reflect/Field;


# direct methods
.method static constructor <clinit>()V
    .locals 3

    .prologue
    .line 26
    const-class v0, Landroid/text/util/Linkify;

    const-string v1, "gatherLinks"

    const-string v2, "(Ljava/util/ArrayList;Landroid/text/Spannable;Ljava/util/regex/Pattern;[Ljava/lang/String;Landroid/text/util/Linkify$MatchFilter;Landroid/text/util/Linkify$TransformFilter;)V"

    invoke-static {v0, v1, v2}, Lmiui/reflect/Method;->of(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lmiui/reflect/Method;

    move-result-object v0

    sput-object v0, Lcom/miui/internal/variable/v16/Android_Text_Util_Linkify_class;->mGatherLinks:Lmiui/reflect/Method;

    .line 32
    const-class v0, Landroid/text/util/Linkify;

    const-string v1, "gatherMapLinks"

    const-string v2, "(Ljava/util/ArrayList;Landroid/text/Spannable;)V"

    invoke-static {v0, v1, v2}, Lmiui/reflect/Method;->of(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lmiui/reflect/Method;

    move-result-object v0

    sput-object v0, Lcom/miui/internal/variable/v16/Android_Text_Util_Linkify_class;->mGatherMapLinks:Lmiui/reflect/Method;

    .line 38
    const-class v0, Landroid/text/util/Linkify;

    const-string v1, "pruneOverlaps"

    const-string v2, "(Ljava/util/ArrayList;)V"

    invoke-static {v0, v1, v2}, Lmiui/reflect/Method;->of(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lmiui/reflect/Method;

    move-result-object v0

    sput-object v0, Lcom/miui/internal/variable/v16/Android_Text_Util_Linkify_class;->mPruneOverlaps:Lmiui/reflect/Method;

    .line 44
    const-class v0, Landroid/text/util/Linkify;

    const-string v1, "applyLink"

    const-string v2, "(Ljava/lang/String;IILandroid/text/Spannable;)V"

    invoke-static {v0, v1, v2}, Lmiui/reflect/Method;->of(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lmiui/reflect/Method;

    move-result-object v0

    sput-object v0, Lcom/miui/internal/variable/v16/Android_Text_Util_Linkify_class;->mApplyLink:Lmiui/reflect/Method;

    .line 50
    const-string v0, "android.text.util.LinkSpec"

    const-string v1, "url"

    const-string v2, "Ljava/lang/String;"

    invoke-static {v0, v1, v2}, Lmiui/reflect/Field;->of(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lmiui/reflect/Field;

    move-result-object v0

    sput-object v0, Lcom/miui/internal/variable/v16/Android_Text_Util_Linkify_class;->mUrl:Lmiui/reflect/Field;

    .line 53
    const-string v0, "android.text.util.LinkSpec"

    const-string v1, "start"

    const-string v2, "I"

    invoke-static {v0, v1, v2}, Lmiui/reflect/Field;->of(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lmiui/reflect/Field;

    move-result-object v0

    sput-object v0, Lcom/miui/internal/variable/v16/Android_Text_Util_Linkify_class;->mStart:Lmiui/reflect/Field;

    .line 56
    const-string v0, "android.text.util.LinkSpec"

    const-string v1, "end"

    const-string v2, "I"

    invoke-static {v0, v1, v2}, Lmiui/reflect/Field;->of(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lmiui/reflect/Field;

    move-result-object v0

    sput-object v0, Lcom/miui/internal/variable/v16/Android_Text_Util_Linkify_class;->mEnd:Lmiui/reflect/Field;

    return-void
.end method

.method public constructor <init>()V
    .locals 0

    .prologue
    .line 23
    invoke-direct {p0}, Lcom/miui/internal/variable/Android_Text_Util_Linkify_class;-><init>()V

    return-void
.end method


# virtual methods
.method public buildProxy()V
    .locals 2

    .prologue
    .line 61
    const-string v0, "addLinks"

    const-string v1, "(Landroid/text/Spannable;I)Z"

    invoke-virtual {p0, v0, v1}, Lcom/miui/internal/variable/v16/Android_Text_Util_Linkify_class;->attachMethod(Ljava/lang/String;Ljava/lang/String;)J

    .line 64
    invoke-super {p0}, Lcom/miui/internal/variable/Android_Text_Util_Linkify_class;->buildProxy()V

    .line 65
    return-void
.end method

.method protected gatherTelLinks(Ljava/util/ArrayList;Landroid/text/Spannable;Ljava/util/regex/Pattern;[Ljava/lang/String;Landroid/text/util/Linkify$MatchFilter;Landroid/text/util/Linkify$TransformFilter;)V
    .locals 7

    .prologue
    const/4 v6, 0x0

    const/4 v4, 0x1

    const/4 v5, 0x0

    .line 146
    sget-object v0, Lcom/miui/internal/variable/v16/Android_Text_Util_Linkify_class;->mGatherLinks:Lmiui/reflect/Method;

    const/4 v1, 0x6

    new-array v1, v1, [Ljava/lang/Object;

    aput-object p1, v1, v5

    aput-object p2, v1, v4

    const/4 v2, 0x2

    sget-object v3, Landroid/util/Patterns;->PHONE:Ljava/util/regex/Pattern;

    aput-object v3, v1, v2

    const/4 v2, 0x3

    new-array v3, v4, [Ljava/lang/String;

    const-string v4, "tel:"

    aput-object v4, v3, v5

    aput-object v3, v1, v2

    const/4 v2, 0x4

    sget-object v3, Landroid/text/util/Linkify;->sPhoneNumberMatchFilter:Landroid/text/util/Linkify$MatchFilter;

    aput-object v3, v1, v2

    const/4 v2, 0x5

    sget-object v3, Landroid/text/util/Linkify;->sPhoneNumberTransformFilter:Landroid/text/util/Linkify$TransformFilter;

    aput-object v3, v1, v2

    invoke-virtual {v0, v6, v6, v1}, Lmiui/reflect/Method;->invoke(Ljava/lang/Class;Ljava/lang/Object;[Ljava/lang/Object;)V

    .line 151
    return-void
.end method

.method protected handle()V
    .locals 6

    .prologue
    const/4 v3, 0x0

    .line 69
    const-wide/16 v1, 0x0

    const/4 v5, 0x0

    move-object v0, p0

    move-object v4, v3

    invoke-virtual/range {v0 .. v5}, Lcom/miui/internal/variable/v16/Android_Text_Util_Linkify_class;->handleAddLinks(JLandroid/text/util/Linkify;Landroid/text/Spannable;I)Z

    .line 70
    return-void
.end method

.method protected handleAddLinks(JLandroid/text/util/Linkify;Landroid/text/Spannable;I)Z
    .locals 9

    .prologue
    .line 73
    if-nez p5, :cond_0

    .line 74
    const/4 v0, 0x0

    .line 135
    :goto_0
    return v0

    .line 77
    :cond_0
    const/4 v0, 0x0

    invoke-interface {p4}, Landroid/text/Spannable;->length()I

    move-result v1

    const-class v2, Landroid/text/style/URLSpan;

    invoke-interface {p4, v0, v1, v2}, Landroid/text/Spannable;->getSpans(IILjava/lang/Class;)[Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Landroid/text/style/URLSpan;

    .line 79
    array-length v1, v0

    add-int/lit8 v1, v1, -0x1

    :goto_1
    if-ltz v1, :cond_1

    .line 80
    aget-object v2, v0, v1

    invoke-interface {p4, v2}, Landroid/text/Spannable;->removeSpan(Ljava/lang/Object;)V

    .line 79
    add-int/lit8 v1, v1, -0x1

    goto :goto_1

    .line 83
    :cond_1
    new-instance v1, Ljava/util/ArrayList;

    invoke-direct {v1}, Ljava/util/ArrayList;-><init>()V

    .line 85
    sget v0, Lcom/miui/internal/variable/v16/Android_Text_Util_Linkify_class;->TIME_PHRASES:I

    and-int/2addr v0, p5

    if-eqz v0, :cond_2

    .line 86
    sget-object v0, Lcom/miui/internal/variable/v16/Android_Text_Util_Linkify_class;->mGatherLinks:Lmiui/reflect/Method;

    const/4 v2, 0x0

    const/4 v3, 0x0

    const/4 v4, 0x6

    new-array v4, v4, [Ljava/lang/Object;

    const/4 v5, 0x0

    aput-object v1, v4, v5

    const/4 v5, 0x1

    aput-object p4, v4, v5

    const/4 v5, 0x2

    sget-object v6, Lmiui/util/Patterns;->CHINESE_TIME_PATTERN:Ljava/util/regex/Pattern;

    aput-object v6, v4, v5

    const/4 v5, 0x3

    const/4 v6, 0x1

    new-array v6, v6, [Ljava/lang/String;

    const/4 v7, 0x0

    const-string v8, "time:"

    aput-object v8, v6, v7

    aput-object v6, v4, v5

    const/4 v5, 0x4

    const/4 v6, 0x0

    aput-object v6, v4, v5

    const/4 v5, 0x5

    const/4 v6, 0x0

    aput-object v6, v4, v5

    invoke-virtual {v0, v2, v3, v4}, Lmiui/reflect/Method;->invoke(Ljava/lang/Class;Ljava/lang/Object;[Ljava/lang/Object;)V

    .line 90
    sget-object v0, Lcom/miui/internal/variable/v16/Android_Text_Util_Linkify_class;->mGatherLinks:Lmiui/reflect/Method;

    const/4 v2, 0x0

    const/4 v3, 0x0

    const/4 v4, 0x6

    new-array v4, v4, [Ljava/lang/Object;

    const/4 v5, 0x0

    aput-object v1, v4, v5

    const/4 v5, 0x1

    aput-object p4, v4, v5

    const/4 v5, 0x2

    sget-object v6, Lmiui/util/Patterns;->ENGLISH_TIME_PATTERN:Ljava/util/regex/Pattern;

    aput-object v6, v4, v5

    const/4 v5, 0x3

    const/4 v6, 0x1

    new-array v6, v6, [Ljava/lang/String;

    const/4 v7, 0x0

    const-string v8, "time:"

    aput-object v8, v6, v7

    aput-object v6, v4, v5

    const/4 v5, 0x4

    const/4 v6, 0x0

    aput-object v6, v4, v5

    const/4 v5, 0x5

    const/4 v6, 0x0

    aput-object v6, v4, v5

    invoke-virtual {v0, v2, v3, v4}, Lmiui/reflect/Method;->invoke(Ljava/lang/Class;Ljava/lang/Object;[Ljava/lang/Object;)V

    .line 96
    :cond_2
    and-int/lit8 v0, p5, 0x1

    if-eqz v0, :cond_3

    .line 97
    sget-object v0, Lcom/miui/internal/variable/v16/Android_Text_Util_Linkify_class;->mGatherLinks:Lmiui/reflect/Method;

    const/4 v2, 0x0

    const/4 v3, 0x0

    const/4 v4, 0x6

    new-array v4, v4, [Ljava/lang/Object;

    const/4 v5, 0x0

    aput-object v1, v4, v5

    const/4 v5, 0x1

    aput-object p4, v4, v5

    const/4 v5, 0x2

    sget-object v6, Lmiui/util/Patterns;->WEB_URL:Ljava/util/regex/Pattern;

    aput-object v6, v4, v5

    const/4 v5, 0x3

    const/4 v6, 0x3

    new-array v6, v6, [Ljava/lang/String;

    const/4 v7, 0x0

    const-string v8, "http://"

    aput-object v8, v6, v7

    const/4 v7, 0x1

    const-string v8, "https://"

    aput-object v8, v6, v7

    const/4 v7, 0x2

    const-string v8, "rtsp://"

    aput-object v8, v6, v7

    aput-object v6, v4, v5

    const/4 v5, 0x4

    sget-object v6, Landroid/text/util/Linkify;->sUrlMatchFilter:Landroid/text/util/Linkify$MatchFilter;

    aput-object v6, v4, v5

    const/4 v5, 0x5

    const/4 v6, 0x0

    aput-object v6, v4, v5

    invoke-virtual {v0, v2, v3, v4}, Lmiui/reflect/Method;->invoke(Ljava/lang/Class;Ljava/lang/Object;[Ljava/lang/Object;)V

    .line 104
    :cond_3
    and-int/lit8 v0, p5, 0x2

    if-eqz v0, :cond_4

    .line 105
    sget-object v0, Lcom/miui/internal/variable/v16/Android_Text_Util_Linkify_class;->mGatherLinks:Lmiui/reflect/Method;

    const/4 v2, 0x0

    const/4 v3, 0x0

    const/4 v4, 0x6

    new-array v4, v4, [Ljava/lang/Object;

    const/4 v5, 0x0

    aput-object v1, v4, v5

    const/4 v5, 0x1

    aput-object p4, v4, v5

    const/4 v5, 0x2

    sget-object v6, Landroid/util/Patterns;->EMAIL_ADDRESS:Ljava/util/regex/Pattern;

    aput-object v6, v4, v5

    const/4 v5, 0x3

    const/4 v6, 0x1

    new-array v6, v6, [Ljava/lang/String;

    const/4 v7, 0x0

    const-string v8, "mailto:"

    aput-object v8, v6, v7

    aput-object v6, v4, v5

    const/4 v5, 0x4

    const/4 v6, 0x0

    aput-object v6, v4, v5

    const/4 v5, 0x5

    const/4 v6, 0x0

    aput-object v6, v4, v5

    invoke-virtual {v0, v2, v3, v4}, Lmiui/reflect/Method;->invoke(Ljava/lang/Class;Ljava/lang/Object;[Ljava/lang/Object;)V

    .line 112
    :cond_4
    and-int/lit8 v0, p5, 0x4

    if-eqz v0, :cond_5

    .line 113
    sget-object v3, Landroid/util/Patterns;->PHONE:Ljava/util/regex/Pattern;

    const/4 v0, 0x1

    new-array v4, v0, [Ljava/lang/String;

    const/4 v0, 0x0

    const-string v2, "tel:"

    aput-object v2, v4, v0

    sget-object v5, Landroid/text/util/Linkify;->sPhoneNumberMatchFilter:Landroid/text/util/Linkify$MatchFilter;

    sget-object v6, Landroid/text/util/Linkify;->sPhoneNumberTransformFilter:Landroid/text/util/Linkify$TransformFilter;

    move-object v0, p0

    move-object v2, p4

    invoke-virtual/range {v0 .. v6}, Lcom/miui/internal/variable/v16/Android_Text_Util_Linkify_class;->gatherTelLinks(Ljava/util/ArrayList;Landroid/text/Spannable;Ljava/util/regex/Pattern;[Ljava/lang/String;Landroid/text/util/Linkify$MatchFilter;Landroid/text/util/Linkify$TransformFilter;)V

    .line 120
    :cond_5
    and-int/lit8 v0, p5, 0x8

    if-eqz v0, :cond_6

    .line 121
    sget-object v0, Lcom/miui/internal/variable/v16/Android_Text_Util_Linkify_class;->mGatherMapLinks:Lmiui/reflect/Method;

    const/4 v2, 0x0

    const/4 v3, 0x0

    const/4 v4, 0x2

    new-array v4, v4, [Ljava/lang/Object;

    const/4 v5, 0x0

    aput-object v1, v4, v5

    const/4 v5, 0x1

    aput-object p4, v4, v5

    invoke-virtual {v0, v2, v3, v4}, Lmiui/reflect/Method;->invoke(Ljava/lang/Class;Ljava/lang/Object;[Ljava/lang/Object;)V

    .line 124
    :cond_6
    sget-object v0, Lcom/miui/internal/variable/v16/Android_Text_Util_Linkify_class;->mPruneOverlaps:Lmiui/reflect/Method;

    const/4 v2, 0x0

    const/4 v3, 0x0

    const/4 v4, 0x1

    new-array v4, v4, [Ljava/lang/Object;

    const/4 v5, 0x0

    aput-object v1, v4, v5

    invoke-virtual {v0, v2, v3, v4}, Lmiui/reflect/Method;->invoke(Ljava/lang/Class;Ljava/lang/Object;[Ljava/lang/Object;)V

    .line 126
    invoke-virtual {v1}, Ljava/util/ArrayList;->size()I

    move-result v0

    if-nez v0, :cond_7

    .line 127
    const/4 v0, 0x0

    goto/16 :goto_0

    .line 130
    :cond_7
    const/4 v0, 0x0

    move v2, v0

    :goto_2
    invoke-virtual {v1}, Ljava/util/ArrayList;->size()I

    move-result v0

    if-ge v2, v0, :cond_8

    .line 131
    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v3

    .line 132
    sget-object v4, Lcom/miui/internal/variable/v16/Android_Text_Util_Linkify_class;->mApplyLink:Lmiui/reflect/Method;

    const/4 v5, 0x0

    const/4 v6, 0x0

    const/4 v0, 0x4

    new-array v7, v0, [Ljava/lang/Object;

    const/4 v8, 0x0

    sget-object v0, Lcom/miui/internal/variable/v16/Android_Text_Util_Linkify_class;->mUrl:Lmiui/reflect/Field;

    invoke-virtual {v0, v3}, Lmiui/reflect/Field;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/String;

    aput-object v0, v7, v8

    const/4 v0, 0x1

    sget-object v8, Lcom/miui/internal/variable/v16/Android_Text_Util_Linkify_class;->mStart:Lmiui/reflect/Field;

    invoke-virtual {v8, v3}, Lmiui/reflect/Field;->getInt(Ljava/lang/Object;)I

    move-result v8

    invoke-static {v8}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v8

    aput-object v8, v7, v0

    const/4 v0, 0x2

    sget-object v8, Lcom/miui/internal/variable/v16/Android_Text_Util_Linkify_class;->mEnd:Lmiui/reflect/Field;

    invoke-virtual {v8, v3}, Lmiui/reflect/Field;->getInt(Ljava/lang/Object;)I

    move-result v3

    invoke-static {v3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v3

    aput-object v3, v7, v0

    const/4 v0, 0x3

    aput-object p4, v7, v0

    invoke-virtual {v4, v5, v6, v7}, Lmiui/reflect/Method;->invoke(Ljava/lang/Class;Ljava/lang/Object;[Ljava/lang/Object;)V

    .line 130
    add-int/lit8 v0, v2, 0x1

    move v2, v0

    goto :goto_2

    .line 135
    :cond_8
    const/4 v0, 0x1

    goto/16 :goto_0
.end method

.method protected originalAddLinks(JLandroid/text/util/Linkify;Landroid/text/Spannable;I)Z
    .locals 2

    .prologue
    .line 139
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "com.miui.internal.variable.v16.Android_Text_Util_Linkify_class.originalAddLinks(long, Linkify, Spannable, int)"

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0
.end method
