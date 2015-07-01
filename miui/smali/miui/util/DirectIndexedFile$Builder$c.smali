.class Lmiui/util/DirectIndexedFile$Builder$c;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Comparable;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/util/DirectIndexedFile$Builder;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x2
    name = "c"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Object;",
        "Ljava/lang/Comparable",
        "<",
        "Lmiui/util/DirectIndexedFile$Builder$c;",
        ">;"
    }
.end annotation


# instance fields
.field private HA:[Ljava/lang/Object;

.field final synthetic lJ:Lmiui/util/DirectIndexedFile$Builder;

.field private mIndex:I


# direct methods
.method private constructor <init>(Lmiui/util/DirectIndexedFile$Builder;I[Ljava/lang/Object;)V
    .locals 0

    .prologue
    .line 1115
    iput-object p1, p0, Lmiui/util/DirectIndexedFile$Builder$c;->lJ:Lmiui/util/DirectIndexedFile$Builder;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 1116
    iput p2, p0, Lmiui/util/DirectIndexedFile$Builder$c;->mIndex:I

    .line 1117
    iput-object p3, p0, Lmiui/util/DirectIndexedFile$Builder$c;->HA:[Ljava/lang/Object;

    .line 1118
    return-void
.end method

.method synthetic constructor <init>(Lmiui/util/DirectIndexedFile$Builder;I[Ljava/lang/Object;Lmiui/util/DirectIndexedFile$1;)V
    .locals 0

    .prologue
    .line 1111
    invoke-direct {p0, p1, p2, p3}, Lmiui/util/DirectIndexedFile$Builder$c;-><init>(Lmiui/util/DirectIndexedFile$Builder;I[Ljava/lang/Object;)V

    return-void
.end method

.method static synthetic b(Lmiui/util/DirectIndexedFile$Builder$c;)I
    .locals 1

    .prologue
    .line 1111
    iget v0, p0, Lmiui/util/DirectIndexedFile$Builder$c;->mIndex:I

    return v0
.end method

.method static synthetic c(Lmiui/util/DirectIndexedFile$Builder$c;)[Ljava/lang/Object;
    .locals 1

    .prologue
    .line 1111
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$Builder$c;->HA:[Ljava/lang/Object;

    return-object v0
.end method


# virtual methods
.method public a(Lmiui/util/DirectIndexedFile$Builder$c;)I
    .locals 2

    .prologue
    .line 1138
    iget v0, p0, Lmiui/util/DirectIndexedFile$Builder$c;->mIndex:I

    iget v1, p1, Lmiui/util/DirectIndexedFile$Builder$c;->mIndex:I

    sub-int/2addr v0, v1

    return v0
.end method

.method public bridge synthetic compareTo(Ljava/lang/Object;)I
    .locals 1

    .prologue
    .line 1111
    check-cast p1, Lmiui/util/DirectIndexedFile$Builder$c;

    invoke-virtual {p0, p1}, Lmiui/util/DirectIndexedFile$Builder$c;->a(Lmiui/util/DirectIndexedFile$Builder$c;)I

    move-result v0

    return v0
.end method

.method public equals(Ljava/lang/Object;)Z
    .locals 4

    .prologue
    const/4 v0, 0x1

    const/4 v1, 0x0

    .line 1127
    .line 1128
    if-ne p1, p0, :cond_1

    .line 1133
    :cond_0
    :goto_0
    return v0

    .line 1130
    :cond_1
    instance-of v2, p1, Lmiui/util/DirectIndexedFile$Builder$c;

    if-eqz v2, :cond_2

    .line 1131
    iget v2, p0, Lmiui/util/DirectIndexedFile$Builder$c;->mIndex:I

    check-cast p1, Lmiui/util/DirectIndexedFile$Builder$c;

    iget v3, p1, Lmiui/util/DirectIndexedFile$Builder$c;->mIndex:I

    if-eq v2, v3, :cond_0

    move v0, v1

    goto :goto_0

    :cond_2
    move v0, v1

    goto :goto_0
.end method

.method public hashCode()I
    .locals 1

    .prologue
    .line 1122
    iget v0, p0, Lmiui/util/DirectIndexedFile$Builder$c;->mIndex:I

    return v0
.end method
