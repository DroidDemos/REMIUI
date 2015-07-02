.class Lmiui/widget/BubbleBgView$a$1;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroid/animation/Animator$AnimatorListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lmiui/widget/BubbleBgView$a;->dz()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic Ax:Lmiui/widget/BubbleBgView$a;


# direct methods
.method constructor <init>(Lmiui/widget/BubbleBgView$a;)V
    .locals 0

    .prologue
    .line 146
    iput-object p1, p0, Lmiui/widget/BubbleBgView$a$1;->Ax:Lmiui/widget/BubbleBgView$a;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onAnimationCancel(Landroid/animation/Animator;)V
    .locals 0

    .prologue
    .line 158
    return-void
.end method

.method public onAnimationEnd(Landroid/animation/Animator;)V
    .locals 1

    .prologue
    .line 153
    iget-object v0, p0, Lmiui/widget/BubbleBgView$a$1;->Ax:Lmiui/widget/BubbleBgView$a;

    invoke-static {v0}, Lmiui/widget/BubbleBgView$a;->e(Lmiui/widget/BubbleBgView$a;)V

    .line 154
    return-void
.end method

.method public onAnimationRepeat(Landroid/animation/Animator;)V
    .locals 0

    .prologue
    .line 162
    return-void
.end method

.method public onAnimationStart(Landroid/animation/Animator;)V
    .locals 0

    .prologue
    .line 149
    return-void
.end method
