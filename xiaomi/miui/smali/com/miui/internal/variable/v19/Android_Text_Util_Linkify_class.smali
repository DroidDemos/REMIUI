.class public Lcom/miui/internal/variable/v19/Android_Text_Util_Linkify_class;
.super Lcom/miui/internal/variable/v16/Android_Text_Util_Linkify_class;
.source "SourceFile"


# static fields
.field protected static final mGatherTelLinks:Lmiui/reflect/Method;


# direct methods
.method static constructor <clinit>()V
    .locals 3

    .prologue
    .line 23
    const-class v0, Landroid/text/util/Linkify;

    const-string v1, "gatherTelLinks"

    const-string v2, "(Ljava/util/ArrayList;Landroid/text/Spannable;)V"

    invoke-static {v0, v1, v2}, Lmiui/reflect/Method;->of(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lmiui/reflect/Method;

    move-result-object v0

    sput-object v0, Lcom/miui/internal/variable/v19/Android_Text_Util_Linkify_class;->mGatherTelLinks:Lmiui/reflect/Method;

    return-void
.end method

.method public constructor <init>()V
    .locals 0

    .prologue
    .line 20
    invoke-direct {p0}, Lcom/miui/internal/variable/v16/Android_Text_Util_Linkify_class;-><init>()V

    return-void
.end method


# virtual methods
.method public gatherTelLinks(Ljava/util/ArrayList;Landroid/text/Spannable;Ljava/util/regex/Pattern;[Ljava/lang/String;Landroid/text/util/Linkify$MatchFilter;Landroid/text/util/Linkify$TransformFilter;)V
    .locals 4

    .prologue
    const/4 v3, 0x0

    .line 33
    sget-object v0, Lcom/miui/internal/variable/v19/Android_Text_Util_Linkify_class;->mGatherTelLinks:Lmiui/reflect/Method;

    const/4 v1, 0x2

    new-array v1, v1, [Ljava/lang/Object;

    const/4 v2, 0x0

    aput-object p1, v1, v2

    const/4 v2, 0x1

    aput-object p2, v1, v2

    invoke-virtual {v0, v3, v3, v1}, Lmiui/reflect/Method;->invoke(Ljava/lang/Class;Ljava/lang/Object;[Ljava/lang/Object;)V

    .line 34
    return-void
.end method
