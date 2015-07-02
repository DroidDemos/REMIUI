.class Lmiui/widget/f;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/widget/NavigationLayout;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic ep:Lmiui/widget/NavigationLayout;


# direct methods
.method constructor <init>(Lmiui/widget/NavigationLayout;)V
    .locals 0

    .prologue
    .line 165
    iput-object p1, p0, Lmiui/widget/f;->ep:Lmiui/widget/NavigationLayout;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .locals 5

    .prologue
    .line 168
    iget-object v0, p0, Lmiui/widget/f;->ep:Lmiui/widget/NavigationLayout;

    invoke-static {v0}, Lmiui/widget/NavigationLayout;->a(Lmiui/widget/NavigationLayout;)Landroid/view/View;

    move-result-object v1

    .line 170
    iget-object v0, p0, Lmiui/widget/f;->ep:Lmiui/widget/NavigationLayout;

    invoke-static {v0}, Lmiui/widget/NavigationLayout;->b(Lmiui/widget/NavigationLayout;)Lcom/miui/internal/widget/ViewDragHelper;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/internal/widget/ViewDragHelper;->getEdgeSize()I

    move-result v2

    .line 171
    if-eqz v1, :cond_1

    invoke-virtual {v1}, Landroid/view/View;->getWidth()I

    move-result v0

    neg-int v0, v0

    :goto_0
    add-int/2addr v2, v0

    .line 173
    if-eqz v1, :cond_0

    invoke-virtual {v1}, Landroid/view/View;->getLeft()I

    move-result v0

    if-ge v0, v2, :cond_0

    iget-object v0, p0, Lmiui/widget/f;->ep:Lmiui/widget/NavigationLayout;

    invoke-virtual {v0}, Lmiui/widget/NavigationLayout;->getDrawerLockMode()I

    move-result v0

    if-nez v0, :cond_0

    .line 174
    invoke-virtual {v1}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v0

    check-cast v0, Lmiui/widget/NavigationLayout$LayoutParams;

    .line 175
    iget-object v3, p0, Lmiui/widget/f;->ep:Lmiui/widget/NavigationLayout;

    invoke-static {v3}, Lmiui/widget/NavigationLayout;->b(Lmiui/widget/NavigationLayout;)Lcom/miui/internal/widget/ViewDragHelper;

    move-result-object v3

    invoke-virtual {v1}, Landroid/view/View;->getTop()I

    move-result v4

    invoke-virtual {v3, v1, v2, v4}, Lcom/miui/internal/widget/ViewDragHelper;->smoothSlideViewTo(Landroid/view/View;II)Z

    .line 176
    const/4 v1, 0x1

    iput-boolean v1, v0, Lmiui/widget/NavigationLayout$LayoutParams;->Bl:Z

    .line 177
    iget-object v0, p0, Lmiui/widget/f;->ep:Lmiui/widget/NavigationLayout;

    invoke-virtual {v0}, Lmiui/widget/NavigationLayout;->invalidate()V

    .line 179
    iget-object v0, p0, Lmiui/widget/f;->ep:Lmiui/widget/NavigationLayout;

    invoke-static {v0}, Lmiui/widget/NavigationLayout;->c(Lmiui/widget/NavigationLayout;)V

    .line 181
    :cond_0
    return-void

    .line 171
    :cond_1
    const/4 v0, 0x0

    goto :goto_0
.end method
