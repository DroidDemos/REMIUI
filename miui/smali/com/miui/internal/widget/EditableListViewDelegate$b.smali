.class Lcom/miui/internal/widget/EditableListViewDelegate$b;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lmiui/widget/EditableListView$MultiChoiceModeListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/internal/widget/EditableListViewDelegate;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x2
    name = "b"
.end annotation


# instance fields
.field private Al:Landroid/widget/AbsListView$MultiChoiceModeListener;

.field private Am:Lmiui/view/ActionModeAnimationListener;

.field final synthetic ay:Lcom/miui/internal/widget/EditableListViewDelegate;


# direct methods
.method public constructor <init>(Lcom/miui/internal/widget/EditableListViewDelegate;)V
    .locals 1

    .prologue
    .line 439
    iput-object p1, p0, Lcom/miui/internal/widget/EditableListViewDelegate$b;->ay:Lcom/miui/internal/widget/EditableListViewDelegate;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 396
    new-instance v0, Lcom/miui/internal/widget/o;

    invoke-direct {v0, p0}, Lcom/miui/internal/widget/o;-><init>(Lcom/miui/internal/widget/EditableListViewDelegate$b;)V

    iput-object v0, p0, Lcom/miui/internal/widget/EditableListViewDelegate$b;->Am:Lmiui/view/ActionModeAnimationListener;

    .line 440
    return-void
.end method


# virtual methods
.method public a(Landroid/widget/AbsListView$MultiChoiceModeListener;)V
    .locals 0

    .prologue
    .line 443
    iput-object p1, p0, Lcom/miui/internal/widget/EditableListViewDelegate$b;->Al:Landroid/widget/AbsListView$MultiChoiceModeListener;

    .line 444
    return-void
.end method

.method public onActionItemClicked(Landroid/view/ActionMode;Landroid/view/MenuItem;)Z
    .locals 2

    .prologue
    .line 474
    invoke-interface {p2}, Landroid/view/MenuItem;->getItemId()I

    move-result v0

    const v1, 0x1020019

    if-ne v0, v1, :cond_1

    .line 475
    invoke-virtual {p1}, Landroid/view/ActionMode;->finish()V

    .line 480
    :cond_0
    :goto_0
    iget-object v0, p0, Lcom/miui/internal/widget/EditableListViewDelegate$b;->Al:Landroid/widget/AbsListView$MultiChoiceModeListener;

    invoke-interface {v0, p1, p2}, Landroid/widget/AbsListView$MultiChoiceModeListener;->onActionItemClicked(Landroid/view/ActionMode;Landroid/view/MenuItem;)Z

    move-result v0

    return v0

    .line 476
    :cond_1
    invoke-interface {p2}, Landroid/view/MenuItem;->getItemId()I

    move-result v0

    const v1, 0x102001a

    if-ne v0, v1, :cond_0

    .line 477
    iget-object v1, p0, Lcom/miui/internal/widget/EditableListViewDelegate$b;->ay:Lcom/miui/internal/widget/EditableListViewDelegate;

    iget-object v0, p0, Lcom/miui/internal/widget/EditableListViewDelegate$b;->ay:Lcom/miui/internal/widget/EditableListViewDelegate;

    invoke-virtual {v0}, Lcom/miui/internal/widget/EditableListViewDelegate;->isAllItemsChecked()Z

    move-result v0

    if-nez v0, :cond_2

    const/4 v0, 0x1

    :goto_1
    invoke-virtual {v1, v0}, Lcom/miui/internal/widget/EditableListViewDelegate;->setAllItemsChecked(Z)V

    goto :goto_0

    :cond_2
    const/4 v0, 0x0

    goto :goto_1
.end method

.method public onAllItemCheckedStateChanged(Landroid/view/ActionMode;Z)V
    .locals 2

    .prologue
    .line 512
    iget-object v0, p0, Lcom/miui/internal/widget/EditableListViewDelegate$b;->Al:Landroid/widget/AbsListView$MultiChoiceModeListener;

    .line 513
    instance-of v1, v0, Lmiui/widget/EditableListView$MultiChoiceModeListener;

    if-eqz v1, :cond_0

    .line 514
    check-cast v0, Lmiui/widget/EditableListView$MultiChoiceModeListener;

    .line 515
    iget-object v1, p0, Lcom/miui/internal/widget/EditableListViewDelegate$b;->ay:Lcom/miui/internal/widget/EditableListViewDelegate;

    invoke-static {v1}, Lcom/miui/internal/widget/EditableListViewDelegate;->d(Lcom/miui/internal/widget/EditableListViewDelegate;)Landroid/view/ActionMode;

    move-result-object v1

    invoke-interface {v0, v1, p2}, Lmiui/widget/EditableListView$MultiChoiceModeListener;->onAllItemCheckedStateChanged(Landroid/view/ActionMode;Z)V

    .line 517
    :cond_0
    return-void
.end method

.method public onCreateActionMode(Landroid/view/ActionMode;Landroid/view/Menu;)Z
    .locals 2

    .prologue
    const/4 v0, 0x0

    .line 459
    iget-object v1, p0, Lcom/miui/internal/widget/EditableListViewDelegate$b;->ay:Lcom/miui/internal/widget/EditableListViewDelegate;

    invoke-static {v1, v0}, Lcom/miui/internal/widget/EditableListViewDelegate;->a(Lcom/miui/internal/widget/EditableListViewDelegate;I)I

    .line 460
    sget v1, Lcom/miui/internal/R$string;->select_item:I

    invoke-virtual {p1, v1}, Landroid/view/ActionMode;->setTitle(I)V

    .line 461
    iget-object v1, p0, Lcom/miui/internal/widget/EditableListViewDelegate$b;->Al:Landroid/widget/AbsListView$MultiChoiceModeListener;

    invoke-interface {v1, p1, p2}, Landroid/widget/AbsListView$MultiChoiceModeListener;->onCreateActionMode(Landroid/view/ActionMode;Landroid/view/Menu;)Z

    move-result v1

    if-eqz v1, :cond_0

    .line 462
    iget-object v0, p0, Lcom/miui/internal/widget/EditableListViewDelegate$b;->ay:Lcom/miui/internal/widget/EditableListViewDelegate;

    invoke-static {v0, p1}, Lcom/miui/internal/widget/EditableListViewDelegate;->a(Lcom/miui/internal/widget/EditableListViewDelegate;Landroid/view/ActionMode;)Landroid/view/ActionMode;

    .line 463
    iget-object v0, p0, Lcom/miui/internal/widget/EditableListViewDelegate$b;->ay:Lcom/miui/internal/widget/EditableListViewDelegate;

    invoke-static {v0}, Lcom/miui/internal/widget/EditableListViewDelegate;->d(Lcom/miui/internal/widget/EditableListViewDelegate;)Landroid/view/ActionMode;

    move-result-object v0

    check-cast v0, Lmiui/view/EditActionMode;

    iget-object v1, p0, Lcom/miui/internal/widget/EditableListViewDelegate$b;->Am:Lmiui/view/ActionModeAnimationListener;

    invoke-interface {v0, v1}, Lmiui/view/EditActionMode;->addAnimationListener(Lmiui/view/ActionModeAnimationListener;)V

    .line 464
    iget-object v0, p0, Lcom/miui/internal/widget/EditableListViewDelegate$b;->ay:Lcom/miui/internal/widget/EditableListViewDelegate;

    invoke-static {v0}, Lcom/miui/internal/widget/EditableListViewDelegate;->e(Lcom/miui/internal/widget/EditableListViewDelegate;)Lcom/miui/internal/widget/EditableListViewDelegate$UpdateListener;

    move-result-object v0

    invoke-interface {v0, p1}, Lcom/miui/internal/widget/EditableListViewDelegate$UpdateListener;->updateCheckStatus(Landroid/view/ActionMode;)V

    .line 465
    iget-object v0, p0, Lcom/miui/internal/widget/EditableListViewDelegate$b;->ay:Lcom/miui/internal/widget/EditableListViewDelegate;

    const/4 v1, -0x1

    invoke-static {v0, v1}, Lcom/miui/internal/widget/EditableListViewDelegate;->b(Lcom/miui/internal/widget/EditableListViewDelegate;I)I

    .line 466
    const/4 v0, 0x1

    .line 469
    :cond_0
    return v0
.end method

.method public onDestroyActionMode(Landroid/view/ActionMode;)V
    .locals 2

    .prologue
    .line 453
    iget-object v0, p0, Lcom/miui/internal/widget/EditableListViewDelegate$b;->ay:Lcom/miui/internal/widget/EditableListViewDelegate;

    const/4 v1, 0x0

    invoke-static {v0, v1}, Lcom/miui/internal/widget/EditableListViewDelegate;->a(Lcom/miui/internal/widget/EditableListViewDelegate;I)I

    .line 454
    iget-object v0, p0, Lcom/miui/internal/widget/EditableListViewDelegate$b;->Al:Landroid/widget/AbsListView$MultiChoiceModeListener;

    invoke-interface {v0, p1}, Landroid/widget/AbsListView$MultiChoiceModeListener;->onDestroyActionMode(Landroid/view/ActionMode;)V

    .line 455
    return-void
.end method

.method public onItemCheckedStateChanged(Landroid/view/ActionMode;IJZ)V
    .locals 7

    .prologue
    const/4 v6, -0x1

    .line 485
    iget-object v0, p0, Lcom/miui/internal/widget/EditableListViewDelegate$b;->ay:Lcom/miui/internal/widget/EditableListViewDelegate;

    invoke-static {v0}, Lcom/miui/internal/widget/EditableListViewDelegate;->f(Lcom/miui/internal/widget/EditableListViewDelegate;)Z

    move-result v0

    if-eqz v0, :cond_1

    .line 508
    :cond_0
    :goto_0
    return-void

    .line 489
    :cond_1
    iget-object v0, p0, Lcom/miui/internal/widget/EditableListViewDelegate$b;->ay:Lcom/miui/internal/widget/EditableListViewDelegate;

    invoke-static {v0}, Lcom/miui/internal/widget/EditableListViewDelegate;->g(Lcom/miui/internal/widget/EditableListViewDelegate;)Z

    move-result v0

    if-eqz v0, :cond_2

    iget-object v0, p0, Lcom/miui/internal/widget/EditableListViewDelegate$b;->ay:Lcom/miui/internal/widget/EditableListViewDelegate;

    invoke-static {v0}, Lcom/miui/internal/widget/EditableListViewDelegate;->b(Lcom/miui/internal/widget/EditableListViewDelegate;)Landroid/widget/AbsListView;

    move-result-object v0

    check-cast v0, Landroid/widget/ListView;

    invoke-virtual {v0}, Landroid/widget/ListView;->getHeaderViewsCount()I

    move-result v0

    .line 490
    :goto_1
    iget-object v1, p0, Lcom/miui/internal/widget/EditableListViewDelegate$b;->ay:Lcom/miui/internal/widget/EditableListViewDelegate;

    invoke-virtual {v1}, Lcom/miui/internal/widget/EditableListViewDelegate;->getAdapter()Landroid/widget/ListAdapter;

    move-result-object v1

    invoke-interface {v1}, Landroid/widget/ListAdapter;->getCount()I

    move-result v1

    .line 491
    if-lt p2, v0, :cond_0

    add-int/2addr v1, v0

    if-ge p2, v1, :cond_0

    iget-object v1, p0, Lcom/miui/internal/widget/EditableListViewDelegate$b;->ay:Lcom/miui/internal/widget/EditableListViewDelegate;

    sub-int v0, p2, v0

    invoke-static {v1, v0}, Lcom/miui/internal/widget/EditableListViewDelegate;->c(Lcom/miui/internal/widget/EditableListViewDelegate;I)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 496
    iget-object v1, p0, Lcom/miui/internal/widget/EditableListViewDelegate$b;->ay:Lcom/miui/internal/widget/EditableListViewDelegate;

    if-eqz p5, :cond_3

    const/4 v0, 0x1

    :goto_2
    invoke-static {v1, v0}, Lcom/miui/internal/widget/EditableListViewDelegate;->d(Lcom/miui/internal/widget/EditableListViewDelegate;I)I

    .line 498
    iget-object v0, p0, Lcom/miui/internal/widget/EditableListViewDelegate$b;->ay:Lcom/miui/internal/widget/EditableListViewDelegate;

    invoke-static {v0}, Lcom/miui/internal/widget/EditableListViewDelegate;->b(Lcom/miui/internal/widget/EditableListViewDelegate;)Landroid/widget/AbsListView;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/internal/widget/EditableListViewDelegate$b;->ay:Lcom/miui/internal/widget/EditableListViewDelegate;

    invoke-static {v1}, Lcom/miui/internal/widget/EditableListViewDelegate;->b(Lcom/miui/internal/widget/EditableListViewDelegate;)Landroid/widget/AbsListView;

    move-result-object v1

    invoke-virtual {v1}, Landroid/widget/AbsListView;->getFirstVisiblePosition()I

    move-result v1

    sub-int v1, p2, v1

    invoke-virtual {v0, v1}, Landroid/widget/AbsListView;->getChildAt(I)Landroid/view/View;

    move-result-object v0

    .line 499
    iget-object v1, p0, Lcom/miui/internal/widget/EditableListViewDelegate$b;->ay:Lcom/miui/internal/widget/EditableListViewDelegate;

    invoke-static {v1}, Lcom/miui/internal/widget/EditableListViewDelegate;->e(Lcom/miui/internal/widget/EditableListViewDelegate;)Lcom/miui/internal/widget/EditableListViewDelegate$UpdateListener;

    move-result-object v1

    invoke-interface {v1, v0, p2, p3, p4}, Lcom/miui/internal/widget/EditableListViewDelegate$UpdateListener;->updateOnScreenCheckedView(Landroid/view/View;IJ)V

    .line 500
    iget-object v0, p0, Lcom/miui/internal/widget/EditableListViewDelegate$b;->ay:Lcom/miui/internal/widget/EditableListViewDelegate;

    invoke-static {v0}, Lcom/miui/internal/widget/EditableListViewDelegate;->e(Lcom/miui/internal/widget/EditableListViewDelegate;)Lcom/miui/internal/widget/EditableListViewDelegate$UpdateListener;

    move-result-object v0

    invoke-interface {v0, p1}, Lcom/miui/internal/widget/EditableListViewDelegate$UpdateListener;->updateCheckStatus(Landroid/view/ActionMode;)V

    .line 502
    iget-object v0, p0, Lcom/miui/internal/widget/EditableListViewDelegate$b;->Al:Landroid/widget/AbsListView$MultiChoiceModeListener;

    move-object v1, p1

    move v2, p2

    move-wide v3, p3

    move v5, p5

    invoke-interface/range {v0 .. v5}, Landroid/widget/AbsListView$MultiChoiceModeListener;->onItemCheckedStateChanged(Landroid/view/ActionMode;IJZ)V

    .line 504
    iget-object v0, p0, Lcom/miui/internal/widget/EditableListViewDelegate$b;->ay:Lcom/miui/internal/widget/EditableListViewDelegate;

    invoke-static {v0}, Lcom/miui/internal/widget/EditableListViewDelegate;->c(Lcom/miui/internal/widget/EditableListViewDelegate;)I

    move-result v0

    if-ne v0, v6, :cond_0

    .line 505
    iget-object v0, p0, Lcom/miui/internal/widget/EditableListViewDelegate$b;->ay:Lcom/miui/internal/widget/EditableListViewDelegate;

    invoke-static {v0}, Lcom/miui/internal/widget/EditableListViewDelegate;->b(Lcom/miui/internal/widget/EditableListViewDelegate;)Landroid/widget/AbsListView;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/internal/widget/EditableListViewDelegate$b;->ay:Lcom/miui/internal/widget/EditableListViewDelegate;

    invoke-static {v1}, Lcom/miui/internal/widget/EditableListViewDelegate;->b(Lcom/miui/internal/widget/EditableListViewDelegate;)Landroid/widget/AbsListView;

    move-result-object v1

    invoke-virtual {v1}, Landroid/widget/AbsListView;->getFirstVisiblePosition()I

    move-result v1

    sub-int v1, p2, v1

    invoke-virtual {v0, v1}, Landroid/widget/AbsListView;->getChildAt(I)Landroid/view/View;

    move-result-object v0

    .line 506
    iget-object v1, p0, Lcom/miui/internal/widget/EditableListViewDelegate$b;->ay:Lcom/miui/internal/widget/EditableListViewDelegate;

    invoke-virtual {v0}, Landroid/view/View;->getBottom()I

    move-result v0

    invoke-static {v1, v0}, Lcom/miui/internal/widget/EditableListViewDelegate;->b(Lcom/miui/internal/widget/EditableListViewDelegate;I)I

    goto/16 :goto_0

    .line 489
    :cond_2
    const/4 v0, 0x0

    goto :goto_1

    :cond_3
    move v0, v6

    .line 496
    goto :goto_2
.end method

.method public onPrepareActionMode(Landroid/view/ActionMode;Landroid/view/Menu;)Z
    .locals 1

    .prologue
    .line 448
    iget-object v0, p0, Lcom/miui/internal/widget/EditableListViewDelegate$b;->Al:Landroid/widget/AbsListView$MultiChoiceModeListener;

    invoke-interface {v0, p1, p2}, Landroid/widget/AbsListView$MultiChoiceModeListener;->onPrepareActionMode(Landroid/view/ActionMode;Landroid/view/Menu;)Z

    move-result v0

    return v0
.end method
