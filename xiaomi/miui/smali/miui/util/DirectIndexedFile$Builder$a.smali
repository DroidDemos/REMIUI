.class Lmiui/util/DirectIndexedFile$Builder$a;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/util/DirectIndexedFile$Builder;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x2
    name = "a"
.end annotation


# instance fields
.field private lH:Ljava/util/HashMap;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/HashMap",
            "<",
            "Ljava/lang/Object;",
            "Ljava/lang/Integer;",
            ">;"
        }
    .end annotation
.end field

.field private lI:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList",
            "<",
            "Ljava/lang/Object;",
            ">;"
        }
    .end annotation
.end field

.field final synthetic lJ:Lmiui/util/DirectIndexedFile$Builder;


# direct methods
.method private constructor <init>(Lmiui/util/DirectIndexedFile$Builder;)V
    .locals 1

    .prologue
    .line 1142
    iput-object p1, p0, Lmiui/util/DirectIndexedFile$Builder$a;->lJ:Lmiui/util/DirectIndexedFile$Builder;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 1143
    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    iput-object v0, p0, Lmiui/util/DirectIndexedFile$Builder$a;->lH:Ljava/util/HashMap;

    .line 1144
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lmiui/util/DirectIndexedFile$Builder$a;->lI:Ljava/util/ArrayList;

    return-void
.end method

.method synthetic constructor <init>(Lmiui/util/DirectIndexedFile$Builder;Lmiui/util/DirectIndexedFile$1;)V
    .locals 0

    .prologue
    .line 1142
    invoke-direct {p0, p1}, Lmiui/util/DirectIndexedFile$Builder$a;-><init>(Lmiui/util/DirectIndexedFile$Builder;)V

    return-void
.end method

.method static synthetic a(Lmiui/util/DirectIndexedFile$Builder$a;)I
    .locals 1

    .prologue
    .line 1142
    invoke-direct {p0}, Lmiui/util/DirectIndexedFile$Builder$a;->size()I

    move-result v0

    return v0
.end method

.method static synthetic a(Lmiui/util/DirectIndexedFile$Builder$a;Ljava/lang/Object;)Ljava/lang/Integer;
    .locals 1

    .prologue
    .line 1142
    invoke-direct {p0, p1}, Lmiui/util/DirectIndexedFile$Builder$a;->e(Ljava/lang/Object;)Ljava/lang/Integer;

    move-result-object v0

    return-object v0
.end method

.method private aI()Ljava/util/ArrayList;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/ArrayList",
            "<",
            "Ljava/lang/Object;",
            ">;"
        }
    .end annotation

    .prologue
    .line 1161
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$Builder$a;->lI:Ljava/util/ArrayList;

    return-object v0
.end method

.method static synthetic b(Lmiui/util/DirectIndexedFile$Builder$a;)Ljava/util/ArrayList;
    .locals 1

    .prologue
    .line 1142
    invoke-direct {p0}, Lmiui/util/DirectIndexedFile$Builder$a;->aI()Ljava/util/ArrayList;

    move-result-object v0

    return-object v0
.end method

.method private e(Ljava/lang/Object;)Ljava/lang/Integer;
    .locals 2

    .prologue
    .line 1147
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$Builder$a;->lH:Ljava/util/HashMap;

    invoke-virtual {v0, p1}, Ljava/util/HashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/Integer;

    .line 1148
    if-nez v0, :cond_0

    .line 1149
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$Builder$a;->lI:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    .line 1150
    iget-object v1, p0, Lmiui/util/DirectIndexedFile$Builder$a;->lH:Ljava/util/HashMap;

    invoke-virtual {v1, p1, v0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 1151
    iget-object v1, p0, Lmiui/util/DirectIndexedFile$Builder$a;->lI:Ljava/util/ArrayList;

    invoke-virtual {v1, p1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 1153
    :cond_0
    return-object v0
.end method

.method private size()I
    .locals 1

    .prologue
    .line 1157
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$Builder$a;->lI:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    return v0
.end method
