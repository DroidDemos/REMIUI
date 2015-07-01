.class Lmiui/widget/DynamicListView$6;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroid/view/ViewTreeObserver$OnPreDrawListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lmiui/widget/DynamicListView;->cH()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic PC:Landroid/view/ViewTreeObserver;

.field final synthetic PD:J

.field final synthetic PI:I

.field final synthetic PJ:I

.field final synthetic hq:Lmiui/widget/DynamicListView;


# direct methods
.method constructor <init>(Lmiui/widget/DynamicListView;Landroid/view/ViewTreeObserver;JII)V
    .locals 0

    .prologue
    .line 665
    iput-object p1, p0, Lmiui/widget/DynamicListView$6;->hq:Lmiui/widget/DynamicListView;

    iput-object p2, p0, Lmiui/widget/DynamicListView$6;->PC:Landroid/view/ViewTreeObserver;

    iput-wide p3, p0, Lmiui/widget/DynamicListView$6;->PD:J

    iput p5, p0, Lmiui/widget/DynamicListView$6;->PI:I

    iput p6, p0, Lmiui/widget/DynamicListView$6;->PJ:I

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onPreDraw()Z
    .locals 6

    .prologue
    const/4 v5, 0x1

    .line 667
    iget-object v0, p0, Lmiui/widget/DynamicListView$6;->PC:Landroid/view/ViewTreeObserver;

    invoke-virtual {v0, p0}, Landroid/view/ViewTreeObserver;->removeOnPreDrawListener(Landroid/view/ViewTreeObserver$OnPreDrawListener;)V

    .line 668
    iget-object v0, p0, Lmiui/widget/DynamicListView$6;->hq:Lmiui/widget/DynamicListView;

    iget-wide v1, p0, Lmiui/widget/DynamicListView$6;->PD:J

    invoke-virtual {v0, v1, v2}, Lmiui/widget/DynamicListView;->getViewForId(J)Landroid/view/View;

    move-result-object v0

    .line 669
    iget-object v1, p0, Lmiui/widget/DynamicListView$6;->hq:Lmiui/widget/DynamicListView;

    iget v2, p0, Lmiui/widget/DynamicListView$6;->PI:I

    invoke-static {v1, v2}, Lmiui/widget/DynamicListView;->b(Lmiui/widget/DynamicListView;I)I

    .line 670
    invoke-virtual {v0}, Landroid/view/View;->getTop()I

    move-result v1

    .line 671
    iget v2, p0, Lmiui/widget/DynamicListView$6;->PJ:I

    sub-int v1, v2, v1

    .line 672
    int-to-float v1, v1

    invoke-virtual {v0, v1}, Landroid/view/View;->setTranslationY(F)V

    .line 673
    sget-object v1, Landroid/view/View;->TRANSLATION_Y:Landroid/util/Property;

    new-array v2, v5, [F

    const/4 v3, 0x0

    const/4 v4, 0x0

    aput v4, v2, v3

    invoke-static {v0, v1, v2}, Landroid/animation/ObjectAnimator;->ofFloat(Ljava/lang/Object;Landroid/util/Property;[F)Landroid/animation/ObjectAnimator;

    move-result-object v0

    .line 675
    iget-object v1, p0, Lmiui/widget/DynamicListView$6;->hq:Lmiui/widget/DynamicListView;

    invoke-static {v1}, Lmiui/widget/DynamicListView;->l(Lmiui/widget/DynamicListView;)J

    move-result-wide v1

    invoke-virtual {v0, v1, v2}, Landroid/animation/ObjectAnimator;->setDuration(J)Landroid/animation/ObjectAnimator;

    .line 676
    invoke-virtual {v0}, Landroid/animation/ObjectAnimator;->start()V

    .line 677
    return v5
.end method
