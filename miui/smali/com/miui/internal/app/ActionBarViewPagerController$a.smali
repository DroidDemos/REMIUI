.class Lcom/miui/internal/app/ActionBarViewPagerController$a;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/internal/app/ActionBarViewPagerController;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = "a"
.end annotation


# instance fields
.field final synthetic Ft:Lcom/miui/internal/app/ActionBarViewPagerController;

.field private Qv:I

.field private Qw:Z


# direct methods
.method constructor <init>(Lcom/miui/internal/app/ActionBarViewPagerController;)V
    .locals 0

    .prologue
    .line 206
    iput-object p1, p0, Lcom/miui/internal/app/ActionBarViewPagerController$a;->Ft:Lcom/miui/internal/app/ActionBarViewPagerController;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method h(IZ)V
    .locals 0

    .prologue
    .line 211
    iput p1, p0, Lcom/miui/internal/app/ActionBarViewPagerController$a;->Qv:I

    .line 212
    iput-boolean p2, p0, Lcom/miui/internal/app/ActionBarViewPagerController$a;->Qw:Z

    .line 213
    return-void
.end method

.method public setValue(F)V
    .locals 6

    .prologue
    .line 216
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarViewPagerController$a;->Ft:Lcom/miui/internal/app/ActionBarViewPagerController;

    invoke-static {v0}, Lcom/miui/internal/app/ActionBarViewPagerController;->c(Lcom/miui/internal/app/ActionBarViewPagerController;)Ljava/util/ArrayList;

    move-result-object v0

    if-eqz v0, :cond_2

    .line 217
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarViewPagerController$a;->Ft:Lcom/miui/internal/app/ActionBarViewPagerController;

    invoke-static {v0}, Lcom/miui/internal/app/ActionBarViewPagerController;->c(Lcom/miui/internal/app/ActionBarViewPagerController;)Ljava/util/ArrayList;

    move-result-object v0

    invoke-virtual {v0}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v2

    :cond_0
    :goto_0
    invoke-interface {v2}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_2

    invoke-interface {v2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/app/ActionBar$FragmentViewPagerChangeListener;

    .line 218
    instance-of v1, v0, Lcom/miui/internal/widget/ActionBarContainer;

    if-eqz v1, :cond_0

    .line 219
    iget v3, p0, Lcom/miui/internal/app/ActionBarViewPagerController$a;->Qv:I

    const/high16 v1, 0x3f800000

    sub-float v4, v1, p1

    iget-boolean v5, p0, Lcom/miui/internal/app/ActionBarViewPagerController$a;->Qw:Z

    iget-boolean v1, p0, Lcom/miui/internal/app/ActionBarViewPagerController$a;->Qw:Z

    if-nez v1, :cond_1

    const/4 v1, 0x1

    :goto_1
    invoke-interface {v0, v3, v4, v5, v1}, Lmiui/app/ActionBar$FragmentViewPagerChangeListener;->onPageScrolled(IFZZ)V

    goto :goto_0

    :cond_1
    const/4 v1, 0x0

    goto :goto_1

    .line 223
    :cond_2
    return-void
.end method
