.class final Lcom/miui/internal/server/DropBoxManagerService$b;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Comparable;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/internal/server/DropBoxManagerService;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x1a
    name = "b"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Object;",
        "Ljava/lang/Comparable",
        "<",
        "Lcom/miui/internal/server/DropBoxManagerService$b;",
        ">;"
    }
.end annotation


# instance fields
.field public Bk:I

.field public final FX:Ljava/util/TreeSet;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/TreeSet",
            "<",
            "Lcom/miui/internal/server/DropBoxManagerService$a;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method private constructor <init>()V
    .locals 1

    .prologue
    .line 417
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 418
    const/4 v0, 0x0

    iput v0, p0, Lcom/miui/internal/server/DropBoxManagerService$b;->Bk:I

    .line 419
    new-instance v0, Ljava/util/TreeSet;

    invoke-direct {v0}, Ljava/util/TreeSet;-><init>()V

    iput-object v0, p0, Lcom/miui/internal/server/DropBoxManagerService$b;->FX:Ljava/util/TreeSet;

    return-void
.end method

.method synthetic constructor <init>(Lcom/miui/internal/server/a;)V
    .locals 0

    .prologue
    .line 417
    invoke-direct {p0}, Lcom/miui/internal/server/DropBoxManagerService$b;-><init>()V

    return-void
.end method


# virtual methods
.method public final a(Lcom/miui/internal/server/DropBoxManagerService$b;)I
    .locals 3

    .prologue
    const/4 v0, 0x0

    .line 423
    iget v1, p0, Lcom/miui/internal/server/DropBoxManagerService$b;->Bk:I

    iget v2, p1, Lcom/miui/internal/server/DropBoxManagerService$b;->Bk:I

    if-eq v1, v2, :cond_1

    iget v0, p1, Lcom/miui/internal/server/DropBoxManagerService$b;->Bk:I

    iget v1, p0, Lcom/miui/internal/server/DropBoxManagerService$b;->Bk:I

    sub-int/2addr v0, v1

    .line 427
    :cond_0
    :goto_0
    return v0

    .line 424
    :cond_1
    if-eq p0, p1, :cond_0

    .line 425
    invoke-virtual {p0}, Ljava/lang/Object;->hashCode()I

    move-result v1

    invoke-virtual {p1}, Ljava/lang/Object;->hashCode()I

    move-result v2

    if-ge v1, v2, :cond_2

    const/4 v0, -0x1

    goto :goto_0

    .line 426
    :cond_2
    invoke-virtual {p0}, Ljava/lang/Object;->hashCode()I

    move-result v1

    invoke-virtual {p1}, Ljava/lang/Object;->hashCode()I

    move-result v2

    if-le v1, v2, :cond_0

    const/4 v0, 0x1

    goto :goto_0
.end method

.method public bridge synthetic compareTo(Ljava/lang/Object;)I
    .locals 1

    .prologue
    .line 417
    check-cast p1, Lcom/miui/internal/server/DropBoxManagerService$b;

    invoke-virtual {p0, p1}, Lcom/miui/internal/server/DropBoxManagerService$b;->a(Lcom/miui/internal/server/DropBoxManagerService$b;)I

    move-result v0

    return v0
.end method
