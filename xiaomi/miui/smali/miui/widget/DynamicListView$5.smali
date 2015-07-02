.class Lmiui/widget/DynamicListView$5;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroid/animation/ValueAnimator$AnimatorUpdateListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lmiui/widget/DynamicListView;->cI()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic hq:Lmiui/widget/DynamicListView;


# direct methods
.method constructor <init>(Lmiui/widget/DynamicListView;)V
    .locals 0

    .prologue
    .line 715
    iput-object p1, p0, Lmiui/widget/DynamicListView$5;->hq:Lmiui/widget/DynamicListView;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onAnimationUpdate(Landroid/animation/ValueAnimator;)V
    .locals 3

    .prologue
    .line 719
    iget-object v0, p0, Lmiui/widget/DynamicListView$5;->hq:Lmiui/widget/DynamicListView;

    invoke-static {v0}, Lmiui/widget/DynamicListView;->k(Lmiui/widget/DynamicListView;)Landroid/graphics/drawable/Drawable;

    move-result-object v0

    const/high16 v1, 0x3f800000

    invoke-virtual {p1}, Landroid/animation/ValueAnimator;->getAnimatedFraction()F

    move-result v2

    sub-float/2addr v1, v2

    const/high16 v2, 0x437f0000

    mul-float/2addr v1, v2

    float-to-int v1, v1

    invoke-virtual {v0, v1}, Landroid/graphics/drawable/Drawable;->setAlpha(I)V

    .line 721
    iget-object v0, p0, Lmiui/widget/DynamicListView$5;->hq:Lmiui/widget/DynamicListView;

    invoke-virtual {v0}, Lmiui/widget/DynamicListView;->invalidate()V

    .line 722
    return-void
.end method
