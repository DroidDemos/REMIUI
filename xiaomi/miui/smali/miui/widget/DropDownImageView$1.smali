.class Lmiui/widget/DropDownImageView$1;
.super Landroid/animation/AnimatorListenerAdapter;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lmiui/widget/DropDownImageView;->d()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic wU:Lmiui/widget/DropDownImageView;


# direct methods
.method constructor <init>(Lmiui/widget/DropDownImageView;)V
    .locals 0

    .prologue
    .line 46
    iput-object p1, p0, Lmiui/widget/DropDownImageView$1;->wU:Lmiui/widget/DropDownImageView;

    invoke-direct {p0}, Landroid/animation/AnimatorListenerAdapter;-><init>()V

    return-void
.end method


# virtual methods
.method public onAnimationEnd(Landroid/animation/Animator;)V
    .locals 2

    .prologue
    .line 48
    check-cast p1, Landroid/animation/ObjectAnimator;

    invoke-virtual {p1}, Landroid/animation/ObjectAnimator;->getTarget()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/widget/ImageView;

    .line 49
    const/high16 v1, -0x3d4c0000

    invoke-virtual {v0, v1}, Landroid/widget/ImageView;->setRotationX(F)V

    .line 50
    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Landroid/widget/ImageView;->setImageLevel(I)V

    .line 51
    const/high16 v1, 0x42b40000

    invoke-virtual {v0, v1}, Landroid/widget/ImageView;->setRotationX(F)V

    .line 52
    return-void
.end method
