.class Lmiui/widget/a;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroid/animation/ValueAnimator$AnimatorUpdateListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/widget/AlphabetFastIndexer;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic ar:Lmiui/widget/AlphabetFastIndexer;


# direct methods
.method constructor <init>(Lmiui/widget/AlphabetFastIndexer;)V
    .locals 0

    .prologue
    .line 105
    iput-object p1, p0, Lmiui/widget/a;->ar:Lmiui/widget/AlphabetFastIndexer;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onAnimationUpdate(Landroid/animation/ValueAnimator;)V
    .locals 3

    .prologue
    .line 108
    iget-object v0, p0, Lmiui/widget/a;->ar:Lmiui/widget/AlphabetFastIndexer;

    invoke-static {v0}, Lmiui/widget/AlphabetFastIndexer;->a(Lmiui/widget/AlphabetFastIndexer;)Lmiui/widget/AlphabetFastIndexer$b;

    move-result-object v1

    iget-object v0, p0, Lmiui/widget/a;->ar:Lmiui/widget/AlphabetFastIndexer;

    invoke-virtual {v0}, Lmiui/widget/AlphabetFastIndexer;->getWidth()I

    move-result v0

    int-to-float v0, v0

    const/high16 v2, 0x40000000

    div-float v2, v0, v2

    invoke-virtual {p1}, Landroid/animation/ValueAnimator;->getAnimatedValue()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/Float;

    invoke-virtual {v0}, Ljava/lang/Float;->floatValue()F

    move-result v0

    invoke-virtual {v1, v2, v0}, Lmiui/widget/AlphabetFastIndexer$b;->b(FF)V

    .line 109
    iget-object v0, p0, Lmiui/widget/a;->ar:Lmiui/widget/AlphabetFastIndexer;

    invoke-virtual {v0}, Lmiui/widget/AlphabetFastIndexer;->postInvalidate()V

    .line 110
    return-void
.end method
