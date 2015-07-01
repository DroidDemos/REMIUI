.class Lmiui/widget/ScreenView$1;
.super Landroid/animation/AnimatorListenerAdapter;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lmiui/widget/ScreenView;->v()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic bW:Lmiui/widget/ScreenView;


# direct methods
.method constructor <init>(Lmiui/widget/ScreenView;)V
    .locals 0

    .prologue
    .line 582
    iput-object p1, p0, Lmiui/widget/ScreenView$1;->bW:Lmiui/widget/ScreenView;

    invoke-direct {p0}, Landroid/animation/AnimatorListenerAdapter;-><init>()V

    return-void
.end method


# virtual methods
.method public onAnimationEnd(Landroid/animation/Animator;)V
    .locals 2

    .prologue
    .line 584
    iget-object v0, p0, Lmiui/widget/ScreenView$1;->bW:Lmiui/widget/ScreenView;

    invoke-static {v0}, Lmiui/widget/ScreenView;->b(Lmiui/widget/ScreenView;)Lmiui/widget/ScreenView$d;

    move-result-object v0

    const/4 v1, 0x4

    invoke-virtual {v0, v1}, Lmiui/widget/ScreenView$d;->setVisibility(I)V

    .line 585
    return-void
.end method
