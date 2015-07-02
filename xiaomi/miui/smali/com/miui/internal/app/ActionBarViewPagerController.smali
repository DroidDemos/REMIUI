.class public Lcom/miui/internal/app/ActionBarViewPagerController;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/internal/app/ActionBarViewPagerController$a;
    }
.end annotation


# instance fields
.field private io:Lcom/miui/internal/app/ActionBarImpl;

.field private ip:Lmiui/view/ViewPager;

.field private iq:Lcom/miui/internal/app/n;

.field private ir:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList",
            "<",
            "Lmiui/app/ActionBar$FragmentViewPagerChangeListener;",
            ">;"
        }
    .end annotation
.end field

.field private is:Landroid/app/ActionBar$TabListener;

.field private it:Lcom/miui/internal/app/ActionBarViewPagerController$a;

.field private iu:Landroid/animation/ObjectAnimator;


# direct methods
.method constructor <init>(Lcom/miui/internal/app/ActionBarImpl;Landroid/app/FragmentManager;Z)V
    .locals 4

    .prologue
    .line 63
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 39
    new-instance v0, Lcom/miui/internal/app/p;

    invoke-direct {v0, p0}, Lcom/miui/internal/app/p;-><init>(Lcom/miui/internal/app/ActionBarViewPagerController;)V

    iput-object v0, p0, Lcom/miui/internal/app/ActionBarViewPagerController;->is:Landroid/app/ActionBar$TabListener;

    .line 64
    iput-object p1, p0, Lcom/miui/internal/app/ActionBarViewPagerController;->io:Lcom/miui/internal/app/ActionBarImpl;

    .line 65
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarViewPagerController;->io:Lcom/miui/internal/app/ActionBarImpl;

    invoke-virtual {v0}, Lcom/miui/internal/app/ActionBarImpl;->ek()Lcom/miui/internal/widget/ActionBarOverlayLayout;

    move-result-object v1

    .line 66
    invoke-virtual {v1}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->getContext()Landroid/content/Context;

    move-result-object v2

    .line 68
    sget v0, Lmiui/R$id;->view_pager:I

    invoke-virtual {v1, v0}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->findViewById(I)Landroid/view/View;

    move-result-object v0

    .line 69
    instance-of v3, v0, Lmiui/view/ViewPager;

    if-eqz v3, :cond_1

    .line 70
    check-cast v0, Lmiui/view/ViewPager;

    iput-object v0, p0, Lcom/miui/internal/app/ActionBarViewPagerController;->ip:Lmiui/view/ViewPager;

    .line 78
    :goto_0
    new-instance v0, Lcom/miui/internal/app/n;

    iget-object v1, p0, Lcom/miui/internal/app/ActionBarViewPagerController;->ip:Lmiui/view/ViewPager;

    invoke-direct {v0, v2, p2, v1}, Lcom/miui/internal/app/n;-><init>(Landroid/content/Context;Landroid/app/FragmentManager;Lmiui/view/ViewPager;)V

    iput-object v0, p0, Lcom/miui/internal/app/ActionBarViewPagerController;->iq:Lcom/miui/internal/app/n;

    .line 79
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarViewPagerController;->ip:Lmiui/view/ViewPager;

    new-instance v1, Lcom/miui/internal/app/ActionBarViewPagerController$1;

    invoke-direct {v1, p0}, Lcom/miui/internal/app/ActionBarViewPagerController$1;-><init>(Lcom/miui/internal/app/ActionBarViewPagerController;)V

    invoke-virtual {v0, v1}, Lmiui/view/ViewPager;->setInternalPageChangeListener(Lmiui/view/ViewPager$OnPageChangeListener;)Lmiui/view/ViewPager$OnPageChangeListener;

    .line 116
    if-eqz p3, :cond_0

    .line 117
    new-instance v0, Lcom/miui/internal/app/o;

    iget-object v1, p0, Lcom/miui/internal/app/ActionBarViewPagerController;->ip:Lmiui/view/ViewPager;

    iget-object v2, p0, Lcom/miui/internal/app/ActionBarViewPagerController;->iq:Lcom/miui/internal/app/n;

    invoke-direct {v0, v1, v2}, Lcom/miui/internal/app/o;-><init>(Lmiui/view/ViewPager;Lcom/miui/internal/app/n;)V

    invoke-virtual {p0, v0}, Lcom/miui/internal/app/ActionBarViewPagerController;->addOnFragmentViewPagerChangeListener(Lmiui/app/ActionBar$FragmentViewPagerChangeListener;)V

    .line 119
    :cond_0
    return-void

    .line 72
    :cond_1
    new-instance v0, Lmiui/view/ViewPager;

    invoke-direct {v0, v2}, Lmiui/view/ViewPager;-><init>(Landroid/content/Context;)V

    iput-object v0, p0, Lcom/miui/internal/app/ActionBarViewPagerController;->ip:Lmiui/view/ViewPager;

    .line 73
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarViewPagerController;->ip:Lmiui/view/ViewPager;

    sget v3, Lmiui/R$id;->view_pager:I

    invoke-virtual {v0, v3}, Lmiui/view/ViewPager;->setId(I)V

    .line 74
    const v0, 0x1020002

    invoke-virtual {v1, v0}, Lcom/miui/internal/widget/ActionBarOverlayLayout;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/view/ViewGroup;

    .line 75
    iget-object v1, p0, Lcom/miui/internal/app/ActionBarViewPagerController;->ip:Lmiui/view/ViewPager;

    invoke-virtual {v0, v1}, Landroid/view/ViewGroup;->addView(Landroid/view/View;)V

    goto :goto_0
.end method

.method static synthetic a(Lcom/miui/internal/app/ActionBarViewPagerController;)Lcom/miui/internal/app/n;
    .locals 1

    .prologue
    .line 33
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarViewPagerController;->iq:Lcom/miui/internal/app/n;

    return-object v0
.end method

.method static synthetic b(Lcom/miui/internal/app/ActionBarViewPagerController;)Lmiui/view/ViewPager;
    .locals 1

    .prologue
    .line 33
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarViewPagerController;->ip:Lmiui/view/ViewPager;

    return-object v0
.end method

.method static synthetic c(Lcom/miui/internal/app/ActionBarViewPagerController;)Ljava/util/ArrayList;
    .locals 1

    .prologue
    .line 33
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarViewPagerController;->ir:Ljava/util/ArrayList;

    return-object v0
.end method

.method static synthetic d(Lcom/miui/internal/app/ActionBarViewPagerController;)Lcom/miui/internal/app/ActionBarImpl;
    .locals 1

    .prologue
    .line 33
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarViewPagerController;->io:Lcom/miui/internal/app/ActionBarImpl;

    return-object v0
.end method


# virtual methods
.method A(I)V
    .locals 1

    .prologue
    .line 134
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarViewPagerController;->iq:Lcom/miui/internal/app/n;

    invoke-virtual {v0, p1}, Lcom/miui/internal/app/n;->A(I)V

    .line 135
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarViewPagerController;->io:Lcom/miui/internal/app/ActionBarImpl;

    invoke-virtual {v0, p1}, Lcom/miui/internal/app/ActionBarImpl;->am(I)V

    .line 136
    return-void
.end method

.method a(Landroid/app/Fragment;)V
    .locals 2

    .prologue
    .line 164
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarViewPagerController;->iq:Lcom/miui/internal/app/n;

    invoke-virtual {v0, p1}, Lcom/miui/internal/app/n;->b(Landroid/app/Fragment;)I

    move-result v0

    .line 165
    if-ltz v0, :cond_0

    .line 166
    iget-object v1, p0, Lcom/miui/internal/app/ActionBarViewPagerController;->io:Lcom/miui/internal/app/ActionBarImpl;

    invoke-virtual {v1, v0}, Lcom/miui/internal/app/ActionBarImpl;->am(I)V

    .line 168
    :cond_0
    return-void
.end method

.method addFragmentTab(Ljava/lang/String;Landroid/app/ActionBar$Tab;ILjava/lang/Class;Landroid/os/Bundle;Z)I
    .locals 7
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            "Landroid/app/ActionBar$Tab;",
            "I",
            "Ljava/lang/Class",
            "<+",
            "Landroid/app/Fragment;",
            ">;",
            "Landroid/os/Bundle;",
            "Z)I"
        }
    .end annotation

    .prologue
    .line 128
    move-object v0, p2

    check-cast v0, Lcom/miui/internal/app/ActionBarImpl$TabImpl;

    iget-object v1, p0, Lcom/miui/internal/app/ActionBarViewPagerController;->is:Landroid/app/ActionBar$TabListener;

    invoke-virtual {v0, v1}, Lcom/miui/internal/app/ActionBarImpl$TabImpl;->setInternalTabListener(Landroid/app/ActionBar$TabListener;)Landroid/app/ActionBar$Tab;

    .line 129
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarViewPagerController;->io:Lcom/miui/internal/app/ActionBarImpl;

    invoke-virtual {v0, p2, p3}, Lcom/miui/internal/app/ActionBarImpl;->a(Landroid/app/ActionBar$Tab;I)V

    .line 130
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarViewPagerController;->iq:Lcom/miui/internal/app/n;

    move-object v1, p1

    move v2, p3

    move-object v3, p4

    move-object v4, p5

    move-object v5, p2

    move v6, p6

    invoke-virtual/range {v0 .. v6}, Lcom/miui/internal/app/n;->a(Ljava/lang/String;ILjava/lang/Class;Landroid/os/Bundle;Landroid/app/ActionBar$Tab;Z)I

    move-result v0

    return v0
.end method

.method addFragmentTab(Ljava/lang/String;Landroid/app/ActionBar$Tab;Ljava/lang/Class;Landroid/os/Bundle;Z)I
    .locals 6
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            "Landroid/app/ActionBar$Tab;",
            "Ljava/lang/Class",
            "<+",
            "Landroid/app/Fragment;",
            ">;",
            "Landroid/os/Bundle;",
            "Z)I"
        }
    .end annotation

    .prologue
    .line 122
    move-object v0, p2

    check-cast v0, Lcom/miui/internal/app/ActionBarImpl$TabImpl;

    iget-object v1, p0, Lcom/miui/internal/app/ActionBarViewPagerController;->is:Landroid/app/ActionBar$TabListener;

    invoke-virtual {v0, v1}, Lcom/miui/internal/app/ActionBarImpl$TabImpl;->setInternalTabListener(Landroid/app/ActionBar$TabListener;)Landroid/app/ActionBar$Tab;

    .line 123
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarViewPagerController;->io:Lcom/miui/internal/app/ActionBarImpl;

    invoke-virtual {v0, p2}, Lcom/miui/internal/app/ActionBarImpl;->b(Landroid/app/ActionBar$Tab;)V

    .line 124
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarViewPagerController;->iq:Lcom/miui/internal/app/n;

    move-object v1, p1

    move-object v2, p3

    move-object v3, p4

    move-object v4, p2

    move v5, p5

    invoke-virtual/range {v0 .. v5}, Lcom/miui/internal/app/n;->a(Ljava/lang/String;Ljava/lang/Class;Landroid/os/Bundle;Landroid/app/ActionBar$Tab;Z)I

    move-result v0

    return v0
.end method

.method addOnFragmentViewPagerChangeListener(Lmiui/app/ActionBar$FragmentViewPagerChangeListener;)V
    .locals 1

    .prologue
    .line 186
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarViewPagerController;->ir:Ljava/util/ArrayList;

    if-nez v0, :cond_0

    .line 187
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/miui/internal/app/ActionBarViewPagerController;->ir:Ljava/util/ArrayList;

    .line 189
    :cond_0
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarViewPagerController;->ir:Ljava/util/ArrayList;

    invoke-virtual {v0, p1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 190
    return-void
.end method

.method getFragmentAt(I)Landroid/app/Fragment;
    .locals 2

    .prologue
    .line 156
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarViewPagerController;->iq:Lcom/miui/internal/app/n;

    const/4 v1, 0x1

    invoke-virtual {v0, p1, v1}, Lcom/miui/internal/app/n;->d(IZ)Landroid/app/Fragment;

    move-result-object v0

    return-object v0
.end method

.method getFragmentTabCount()I
    .locals 1

    .prologue
    .line 160
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarViewPagerController;->iq:Lcom/miui/internal/app/n;

    invoke-virtual {v0}, Lcom/miui/internal/app/n;->getCount()I

    move-result v0

    return v0
.end method

.method getViewPagerOffscreenPageLimit()I
    .locals 1

    .prologue
    .line 199
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarViewPagerController;->ip:Lmiui/view/ViewPager;

    invoke-virtual {v0}, Lmiui/view/ViewPager;->getOffscreenPageLimit()I

    move-result v0

    return v0
.end method

.method removeAllFragmentTab()V
    .locals 1

    .prologue
    .line 151
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarViewPagerController;->io:Lcom/miui/internal/app/ActionBarImpl;

    invoke-virtual {v0}, Lcom/miui/internal/app/ActionBarImpl;->el()V

    .line 152
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarViewPagerController;->iq:Lcom/miui/internal/app/n;

    invoke-virtual {v0}, Lcom/miui/internal/app/n;->cz()V

    .line 153
    return-void
.end method

.method removeFragmentTab(Landroid/app/ActionBar$Tab;)V
    .locals 1

    .prologue
    .line 146
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarViewPagerController;->io:Lcom/miui/internal/app/ActionBarImpl;

    invoke-virtual {v0, p1}, Lcom/miui/internal/app/ActionBarImpl;->c(Landroid/app/ActionBar$Tab;)V

    .line 147
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarViewPagerController;->iq:Lcom/miui/internal/app/n;

    invoke-virtual {v0, p1}, Lcom/miui/internal/app/n;->a(Landroid/app/ActionBar$Tab;)I

    .line 148
    return-void
.end method

.method removeFragmentTab(Ljava/lang/String;)V
    .locals 1

    .prologue
    .line 139
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarViewPagerController;->iq:Lcom/miui/internal/app/n;

    invoke-virtual {v0, p1}, Lcom/miui/internal/app/n;->w(Ljava/lang/String;)I

    move-result v0

    .line 140
    if-ltz v0, :cond_0

    .line 141
    invoke-virtual {p0, v0}, Lcom/miui/internal/app/ActionBarViewPagerController;->A(I)V

    .line 143
    :cond_0
    return-void
.end method

.method removeOnFragmentViewPagerChangeListener(Lmiui/app/ActionBar$FragmentViewPagerChangeListener;)V
    .locals 1

    .prologue
    .line 193
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarViewPagerController;->ir:Ljava/util/ArrayList;

    if-eqz v0, :cond_0

    .line 194
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarViewPagerController;->ir:Ljava/util/ArrayList;

    invoke-virtual {v0, p1}, Ljava/util/ArrayList;->remove(Ljava/lang/Object;)Z

    .line 196
    :cond_0
    return-void
.end method

.method setFragmentActionMenuAt(IZ)V
    .locals 3

    .prologue
    .line 171
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarViewPagerController;->iq:Lcom/miui/internal/app/n;

    invoke-virtual {v0, p1, p2}, Lcom/miui/internal/app/n;->setFragmentActionMenuAt(IZ)V

    .line 172
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarViewPagerController;->ip:Lmiui/view/ViewPager;

    invoke-virtual {v0}, Lmiui/view/ViewPager;->getCurrentItem()I

    move-result v0

    if-ne p1, v0, :cond_1

    .line 173
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarViewPagerController;->it:Lcom/miui/internal/app/ActionBarViewPagerController$a;

    if-nez v0, :cond_0

    .line 174
    new-instance v0, Lcom/miui/internal/app/ActionBarViewPagerController$a;

    invoke-direct {v0, p0}, Lcom/miui/internal/app/ActionBarViewPagerController$a;-><init>(Lcom/miui/internal/app/ActionBarViewPagerController;)V

    iput-object v0, p0, Lcom/miui/internal/app/ActionBarViewPagerController;->it:Lcom/miui/internal/app/ActionBarViewPagerController$a;

    .line 175
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarViewPagerController;->it:Lcom/miui/internal/app/ActionBarViewPagerController$a;

    const-string v1, "Value"

    const/4 v2, 0x2

    new-array v2, v2, [F

    fill-array-data v2, :array_0

    invoke-static {v0, v1, v2}, Landroid/animation/ObjectAnimator;->ofFloat(Ljava/lang/Object;Ljava/lang/String;[F)Landroid/animation/ObjectAnimator;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/internal/app/ActionBarViewPagerController;->iu:Landroid/animation/ObjectAnimator;

    .line 176
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarViewPagerController;->iu:Landroid/animation/ObjectAnimator;

    iget-object v1, p0, Lcom/miui/internal/app/ActionBarViewPagerController;->ip:Lmiui/view/ViewPager;

    invoke-virtual {v1}, Lmiui/view/ViewPager;->getContext()Landroid/content/Context;

    move-result-object v1

    invoke-virtual {v1}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    const/high16 v2, 0x10e0000

    invoke-virtual {v1, v2}, Landroid/content/res/Resources;->getInteger(I)I

    move-result v1

    int-to-long v1, v1

    invoke-virtual {v0, v1, v2}, Landroid/animation/ObjectAnimator;->setDuration(J)Landroid/animation/ObjectAnimator;

    .line 180
    :cond_0
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarViewPagerController;->it:Lcom/miui/internal/app/ActionBarViewPagerController$a;

    invoke-virtual {v0, p1, p2}, Lcom/miui/internal/app/ActionBarViewPagerController$a;->h(IZ)V

    .line 181
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarViewPagerController;->iu:Landroid/animation/ObjectAnimator;

    invoke-virtual {v0}, Landroid/animation/ObjectAnimator;->start()V

    .line 183
    :cond_1
    return-void

    .line 175
    nop

    :array_0
    .array-data 4
        0x0
        0x3f800000
    .end array-data
.end method

.method setViewPagerOffscreenPageLimit(I)V
    .locals 1

    .prologue
    .line 203
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarViewPagerController;->ip:Lmiui/view/ViewPager;

    invoke-virtual {v0, p1}, Lmiui/view/ViewPager;->setOffscreenPageLimit(I)V

    .line 204
    return-void
.end method
