.class Lcom/miui/internal/widget/ScrollingTabContainerView$1;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/internal/widget/ScrollingTabContainerView;->animateToTab(I)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic iA:Lcom/miui/internal/widget/ScrollingTabContainerView;

.field final synthetic kM:Landroid/view/View;


# direct methods
.method constructor <init>(Lcom/miui/internal/widget/ScrollingTabContainerView;Landroid/view/View;)V
    .locals 0

    .prologue
    .line 318
    iput-object p1, p0, Lcom/miui/internal/widget/ScrollingTabContainerView$1;->iA:Lcom/miui/internal/widget/ScrollingTabContainerView;

    iput-object p2, p0, Lcom/miui/internal/widget/ScrollingTabContainerView$1;->kM:Landroid/view/View;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .locals 3

    .prologue
    .line 320
    iget-object v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView$1;->kM:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->getLeft()I

    move-result v0

    iget-object v1, p0, Lcom/miui/internal/widget/ScrollingTabContainerView$1;->iA:Lcom/miui/internal/widget/ScrollingTabContainerView;

    invoke-virtual {v1}, Lcom/miui/internal/widget/ScrollingTabContainerView;->getWidth()I

    move-result v1

    iget-object v2, p0, Lcom/miui/internal/widget/ScrollingTabContainerView$1;->kM:Landroid/view/View;

    invoke-virtual {v2}, Landroid/view/View;->getWidth()I

    move-result v2

    sub-int/2addr v1, v2

    div-int/lit8 v1, v1, 0x2

    sub-int/2addr v0, v1

    .line 321
    iget-object v1, p0, Lcom/miui/internal/widget/ScrollingTabContainerView$1;->iA:Lcom/miui/internal/widget/ScrollingTabContainerView;

    const/4 v2, 0x0

    invoke-virtual {v1, v0, v2}, Lcom/miui/internal/widget/ScrollingTabContainerView;->smoothScrollTo(II)V

    .line 322
    iget-object v0, p0, Lcom/miui/internal/widget/ScrollingTabContainerView$1;->iA:Lcom/miui/internal/widget/ScrollingTabContainerView;

    const/4 v1, 0x0

    iput-object v1, v0, Lcom/miui/internal/widget/ScrollingTabContainerView;->aE:Ljava/lang/Runnable;

    .line 323
    return-void
.end method
