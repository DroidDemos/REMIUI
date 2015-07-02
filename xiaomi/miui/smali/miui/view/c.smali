.class final Lmiui/view/c;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/util/Comparator;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/view/ViewPager;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x8
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Object;",
        "Ljava/util/Comparator",
        "<",
        "Lmiui/view/ViewPager$a;",
        ">;"
    }
.end annotation


# direct methods
.method constructor <init>()V
    .locals 0

    .prologue
    .line 96
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public a(Lmiui/view/ViewPager$a;Lmiui/view/ViewPager$a;)I
    .locals 2

    .prologue
    .line 99
    iget v0, p1, Lmiui/view/ViewPager$a;->position:I

    iget v1, p2, Lmiui/view/ViewPager$a;->position:I

    sub-int/2addr v0, v1

    return v0
.end method

.method public bridge synthetic compare(Ljava/lang/Object;Ljava/lang/Object;)I
    .locals 1

    .prologue
    .line 96
    check-cast p1, Lmiui/view/ViewPager$a;

    check-cast p2, Lmiui/view/ViewPager$a;

    invoke-virtual {p0, p1, p2}, Lmiui/view/c;->a(Lmiui/view/ViewPager$a;Lmiui/view/ViewPager$a;)I

    move-result v0

    return v0
.end method
