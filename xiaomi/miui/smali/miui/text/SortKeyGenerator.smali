.class public Lmiui/text/SortKeyGenerator;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lmiui/text/SortKeyGenerator$b;,
        Lmiui/text/SortKeyGenerator$NameStyle;,
        Lmiui/text/SortKeyGenerator$a;
    }
.end annotation


# static fields
.field private static final f:Lmiui/util/SoftReferenceSingleton;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lmiui/util/SoftReferenceSingleton",
            "<",
            "Lmiui/text/SortKeyGenerator;",
            ">;"
        }
    .end annotation
.end field


# instance fields
.field private IF:[Lmiui/text/SortKeyGenerator$a;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .prologue
    .line 38
    new-instance v0, Lmiui/text/b;

    invoke-direct {v0}, Lmiui/text/b;-><init>()V

    sput-object v0, Lmiui/text/SortKeyGenerator;->f:Lmiui/util/SoftReferenceSingleton;

    return-void
.end method

.method private constructor <init>()V
    .locals 4

    .prologue
    .line 48
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 46
    invoke-static {}, Lmiui/text/SortKeyGenerator$NameStyle;->values()[Lmiui/text/SortKeyGenerator$NameStyle;

    move-result-object v0

    array-length v0, v0

    new-array v0, v0, [Lmiui/text/SortKeyGenerator$a;

    iput-object v0, p0, Lmiui/text/SortKeyGenerator;->IF:[Lmiui/text/SortKeyGenerator$a;

    .line 49
    iget-object v0, p0, Lmiui/text/SortKeyGenerator;->IF:[Lmiui/text/SortKeyGenerator$a;

    sget-object v1, Lmiui/text/SortKeyGenerator$NameStyle;->pW:Lmiui/text/SortKeyGenerator$NameStyle;

    invoke-virtual {v1}, Lmiui/text/SortKeyGenerator$NameStyle;->ordinal()I

    move-result v1

    new-instance v2, Lmiui/text/SortKeyGenerator$a;

    const/4 v3, 0x0

    invoke-direct {v2, p0, v3}, Lmiui/text/SortKeyGenerator$a;-><init>(Lmiui/text/SortKeyGenerator;Lmiui/text/b;)V

    aput-object v2, v0, v1

    .line 50
    return-void
.end method

.method synthetic constructor <init>(Lmiui/text/b;)V
    .locals 0

    .prologue
    .line 36
    invoke-direct {p0}, Lmiui/text/SortKeyGenerator;-><init>()V

    return-void
.end method

.method private declared-synchronized a(Lmiui/text/SortKeyGenerator$NameStyle;)Lmiui/text/SortKeyGenerator$a;
    .locals 3

    .prologue
    .line 228
    monitor-enter p0

    :try_start_0
    iget-object v0, p0, Lmiui/text/SortKeyGenerator;->IF:[Lmiui/text/SortKeyGenerator$a;

    invoke-virtual {p1}, Lmiui/text/SortKeyGenerator$NameStyle;->ordinal()I

    move-result v1

    aget-object v0, v0, v1

    .line 229
    if-nez v0, :cond_0

    .line 230
    sget-object v1, Lmiui/text/SortKeyGenerator$NameStyle;->pZ:Lmiui/text/SortKeyGenerator$NameStyle;

    if-ne p1, v1, :cond_0

    .line 231
    new-instance v0, Lmiui/text/SortKeyGenerator$b;

    const/4 v1, 0x0

    invoke-direct {v0, p0, v1}, Lmiui/text/SortKeyGenerator$b;-><init>(Lmiui/text/SortKeyGenerator;Lmiui/text/b;)V

    .line 232
    iget-object v1, p0, Lmiui/text/SortKeyGenerator;->IF:[Lmiui/text/SortKeyGenerator$a;

    invoke-virtual {p1}, Lmiui/text/SortKeyGenerator$NameStyle;->ordinal()I

    move-result v2

    aput-object v0, v1, v2

    .line 235
    :cond_0
    if-nez v0, :cond_1

    iget-object v0, p0, Lmiui/text/SortKeyGenerator;->IF:[Lmiui/text/SortKeyGenerator$a;

    sget-object v1, Lmiui/text/SortKeyGenerator$NameStyle;->pW:Lmiui/text/SortKeyGenerator$NameStyle;

    invoke-virtual {v1}, Lmiui/text/SortKeyGenerator$NameStyle;->ordinal()I

    move-result v1

    aget-object v0, v0, v1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    :cond_1
    monitor-exit p0

    return-object v0

    .line 228
    :catchall_0
    move-exception v0

    monitor-exit p0

    throw v0
.end method

.method public static getInstance()Lmiui/text/SortKeyGenerator;
    .locals 1

    .prologue
    .line 56
    sget-object v0, Lmiui/text/SortKeyGenerator;->f:Lmiui/util/SoftReferenceSingleton;

    invoke-virtual {v0}, Lmiui/util/SoftReferenceSingleton;->get()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/text/SortKeyGenerator;

    return-object v0
.end method


# virtual methods
.method public getSortKey(Ljava/lang/String;)Ljava/lang/String;
    .locals 1

    .prologue
    .line 245
    invoke-static {p1}, Lmiui/text/SortKeyGenerator$NameStyle;->o(Ljava/lang/String;)Lmiui/text/SortKeyGenerator$NameStyle;

    move-result-object v0

    .line 246
    invoke-direct {p0, v0}, Lmiui/text/SortKeyGenerator;->a(Lmiui/text/SortKeyGenerator$NameStyle;)Lmiui/text/SortKeyGenerator$a;

    move-result-object v0

    invoke-virtual {v0, p1}, Lmiui/text/SortKeyGenerator$a;->getSortKey(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method
