.class Lcom/miui/internal/widget/SearchActionModeView$d;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lmiui/view/ActionModeAnimationListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/internal/widget/SearchActionModeView;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = "d"
.end annotation


# instance fields
.field final synthetic iC:Lcom/miui/internal/widget/SearchActionModeView;


# direct methods
.method constructor <init>(Lcom/miui/internal/widget/SearchActionModeView;)V
    .locals 0

    .prologue
    .line 624
    iput-object p1, p0, Lcom/miui/internal/widget/SearchActionModeView$d;->iC:Lcom/miui/internal/widget/SearchActionModeView;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onStart(Z)V
    .locals 0

    .prologue
    .line 628
    return-void
.end method

.method public onStop(Z)V
    .locals 2

    .prologue
    .line 643
    if-eqz p1, :cond_0

    .line 644
    iget-object v0, p0, Lcom/miui/internal/widget/SearchActionModeView$d;->iC:Lcom/miui/internal/widget/SearchActionModeView;

    invoke-virtual {v0}, Lcom/miui/internal/widget/SearchActionModeView;->getActionBarContainer()Lcom/miui/internal/widget/ActionBarContainer;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/internal/widget/ActionBarContainer;->getTabContainer()Landroid/view/View;

    move-result-object v0

    .line 645
    if-eqz v0, :cond_0

    .line 646
    const/16 v1, 0x8

    invoke-virtual {v0, v1}, Landroid/view/View;->setVisibility(I)V

    .line 649
    :cond_0
    return-void
.end method

.method public onUpdate(ZF)V
    .locals 4

    .prologue
    .line 632
    if-nez p1, :cond_0

    .line 633
    const/high16 v0, 0x3f800000

    sub-float p2, v0, p2

    .line 635
    :cond_0
    iget-object v0, p0, Lcom/miui/internal/widget/SearchActionModeView$d;->iC:Lcom/miui/internal/widget/SearchActionModeView;

    invoke-virtual {v0}, Lcom/miui/internal/widget/SearchActionModeView;->getActionBarContainer()Lcom/miui/internal/widget/ActionBarContainer;

    move-result-object v0

    .line 636
    if-eqz v0, :cond_1

    .line 637
    neg-float v1, p2

    invoke-virtual {v0}, Landroid/view/View;->getHeight()I

    move-result v2

    iget-object v3, p0, Lcom/miui/internal/widget/SearchActionModeView$d;->iC:Lcom/miui/internal/widget/SearchActionModeView;

    invoke-static {v3}, Lcom/miui/internal/widget/SearchActionModeView;->e(Lcom/miui/internal/widget/SearchActionModeView;)I

    move-result v3

    sub-int/2addr v2, v3

    int-to-float v2, v2

    mul-float/2addr v1, v2

    invoke-virtual {v0, v1}, Landroid/view/View;->setTranslationY(F)V

    .line 639
    :cond_1
    return-void
.end method
