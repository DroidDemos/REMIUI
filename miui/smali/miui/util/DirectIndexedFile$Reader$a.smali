.class Lmiui/util/DirectIndexedFile$Reader$a;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/util/DirectIndexedFile$Reader;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0xa
    name = "a"
.end annotation


# instance fields
.field private lY:[Lmiui/util/DirectIndexedFile$a;

.field private lZ:[Lmiui/util/DirectIndexedFile$DataItemDescriptor;

.field private ma:[[Ljava/lang/Object;

.field private mb:I


# direct methods
.method private constructor <init>()V
    .locals 0

    .prologue
    .line 839
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method synthetic constructor <init>(Lmiui/util/DirectIndexedFile$1;)V
    .locals 0

    .prologue
    .line 839
    invoke-direct {p0}, Lmiui/util/DirectIndexedFile$Reader$a;-><init>()V

    return-void
.end method

.method static synthetic a(Lmiui/util/DirectIndexedFile$Reader$a;I)I
    .locals 0

    .prologue
    .line 839
    iput p1, p0, Lmiui/util/DirectIndexedFile$Reader$a;->mb:I

    return p1
.end method

.method static synthetic a(Lmiui/util/DirectIndexedFile$Reader$a;[Lmiui/util/DirectIndexedFile$DataItemDescriptor;)[Lmiui/util/DirectIndexedFile$DataItemDescriptor;
    .locals 0

    .prologue
    .line 839
    iput-object p1, p0, Lmiui/util/DirectIndexedFile$Reader$a;->lZ:[Lmiui/util/DirectIndexedFile$DataItemDescriptor;

    return-object p1
.end method

.method static synthetic a(Lmiui/util/DirectIndexedFile$Reader$a;)[Lmiui/util/DirectIndexedFile$a;
    .locals 1

    .prologue
    .line 839
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$Reader$a;->lY:[Lmiui/util/DirectIndexedFile$a;

    return-object v0
.end method

.method static synthetic a(Lmiui/util/DirectIndexedFile$Reader$a;[Lmiui/util/DirectIndexedFile$a;)[Lmiui/util/DirectIndexedFile$a;
    .locals 0

    .prologue
    .line 839
    iput-object p1, p0, Lmiui/util/DirectIndexedFile$Reader$a;->lY:[Lmiui/util/DirectIndexedFile$a;

    return-object p1
.end method

.method static synthetic a(Lmiui/util/DirectIndexedFile$Reader$a;[[Ljava/lang/Object;)[[Ljava/lang/Object;
    .locals 0

    .prologue
    .line 839
    iput-object p1, p0, Lmiui/util/DirectIndexedFile$Reader$a;->ma:[[Ljava/lang/Object;

    return-object p1
.end method

.method static synthetic b(Lmiui/util/DirectIndexedFile$Reader$a;I)I
    .locals 1

    .prologue
    .line 839
    iget v0, p0, Lmiui/util/DirectIndexedFile$Reader$a;->mb:I

    add-int/2addr v0, p1

    iput v0, p0, Lmiui/util/DirectIndexedFile$Reader$a;->mb:I

    return v0
.end method

.method static synthetic b(Lmiui/util/DirectIndexedFile$Reader$a;)[Lmiui/util/DirectIndexedFile$DataItemDescriptor;
    .locals 1

    .prologue
    .line 839
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$Reader$a;->lZ:[Lmiui/util/DirectIndexedFile$DataItemDescriptor;

    return-object v0
.end method

.method static synthetic c(Lmiui/util/DirectIndexedFile$Reader$a;)[[Ljava/lang/Object;
    .locals 1

    .prologue
    .line 839
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$Reader$a;->ma:[[Ljava/lang/Object;

    return-object v0
.end method

.method static synthetic d(Lmiui/util/DirectIndexedFile$Reader$a;)I
    .locals 1

    .prologue
    .line 839
    iget v0, p0, Lmiui/util/DirectIndexedFile$Reader$a;->mb:I

    return v0
.end method
