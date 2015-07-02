.class public Lmiui/widget/EditableListViewWrapper;
.super Ljava/lang/Object;
.source "SourceFile"


# instance fields
.field private final a:Lcom/miui/internal/widget/EditableListViewDelegate;


# direct methods
.method public constructor <init>(Landroid/widget/AbsListView;)V
    .locals 2

    .prologue
    .line 52
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 53
    if-nez p1, :cond_0

    .line 54
    new-instance v0, Ljava/lang/IllegalArgumentException;

    const-string v1, "absListView is null"

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 57
    :cond_0
    new-instance v0, Lcom/miui/internal/widget/EditableListViewDelegate;

    invoke-direct {v0}, Lcom/miui/internal/widget/EditableListViewDelegate;-><init>()V

    iput-object v0, p0, Lmiui/widget/EditableListViewWrapper;->a:Lcom/miui/internal/widget/EditableListViewDelegate;

    .line 58
    iget-object v0, p0, Lmiui/widget/EditableListViewWrapper;->a:Lcom/miui/internal/widget/EditableListViewDelegate;

    invoke-virtual {p1}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v1

    invoke-virtual {v0, p1, v1}, Lcom/miui/internal/widget/EditableListViewDelegate;->initialize(Landroid/widget/AbsListView;Ljava/lang/Class;)V

    .line 59
    iget-object v0, p0, Lmiui/widget/EditableListViewWrapper;->a:Lcom/miui/internal/widget/EditableListViewDelegate;

    new-instance v1, Lmiui/widget/EditableListViewWrapper$1;

    invoke-direct {v1, p0}, Lmiui/widget/EditableListViewWrapper$1;-><init>(Lmiui/widget/EditableListViewWrapper;)V

    invoke-virtual {v0, v1}, Lcom/miui/internal/widget/EditableListViewDelegate;->setUpdateListener(Lcom/miui/internal/widget/EditableListViewDelegate$UpdateListener;)V

    .line 70
    return-void
.end method


# virtual methods
.method public clearChoices()V
    .locals 1

    .prologue
    .line 96
    iget-object v0, p0, Lmiui/widget/EditableListViewWrapper;->a:Lcom/miui/internal/widget/EditableListViewDelegate;

    invoke-virtual {v0}, Lcom/miui/internal/widget/EditableListViewDelegate;->clearChoices()V

    .line 97
    return-void
.end method

.method protected getAdapter()Landroid/widget/ListAdapter;
    .locals 1

    .prologue
    .line 194
    iget-object v0, p0, Lmiui/widget/EditableListViewWrapper;->a:Lcom/miui/internal/widget/EditableListViewDelegate;

    invoke-virtual {v0}, Lcom/miui/internal/widget/EditableListViewDelegate;->getAdapter()Landroid/widget/ListAdapter;

    move-result-object v0

    return-object v0
.end method

.method public getCheckedItemCount()I
    .locals 1

    .prologue
    .line 125
    iget-object v0, p0, Lmiui/widget/EditableListViewWrapper;->a:Lcom/miui/internal/widget/EditableListViewDelegate;

    invoke-virtual {v0}, Lcom/miui/internal/widget/EditableListViewDelegate;->getListView()Landroid/widget/AbsListView;

    move-result-object v0

    invoke-virtual {v0}, Landroid/widget/AbsListView;->getCheckedItemCount()I

    move-result v0

    return v0
.end method

.method public getCheckedItemIds()[J
    .locals 1

    .prologue
    .line 182
    iget-object v0, p0, Lmiui/widget/EditableListViewWrapper;->a:Lcom/miui/internal/widget/EditableListViewDelegate;

    invoke-virtual {v0}, Lcom/miui/internal/widget/EditableListViewDelegate;->getListView()Landroid/widget/AbsListView;

    move-result-object v0

    invoke-virtual {v0}, Landroid/widget/AbsListView;->getCheckedItemIds()[J

    move-result-object v0

    return-object v0
.end method

.method public getCheckedItemPosition()I
    .locals 1

    .prologue
    .line 158
    iget-object v0, p0, Lmiui/widget/EditableListViewWrapper;->a:Lcom/miui/internal/widget/EditableListViewDelegate;

    invoke-virtual {v0}, Lcom/miui/internal/widget/EditableListViewDelegate;->getListView()Landroid/widget/AbsListView;

    move-result-object v0

    invoke-virtual {v0}, Landroid/widget/AbsListView;->getCheckedItemPosition()I

    move-result v0

    return v0
.end method

.method public getCheckedItemPositions()Landroid/util/SparseBooleanArray;
    .locals 1

    .prologue
    .line 171
    iget-object v0, p0, Lmiui/widget/EditableListViewWrapper;->a:Lcom/miui/internal/widget/EditableListViewDelegate;

    invoke-virtual {v0}, Lcom/miui/internal/widget/EditableListViewDelegate;->getCheckedItemPositions()Landroid/util/SparseBooleanArray;

    move-result-object v0

    return-object v0
.end method

.method public isAllItemsChecked()Z
    .locals 1

    .prologue
    .line 78
    iget-object v0, p0, Lmiui/widget/EditableListViewWrapper;->a:Lcom/miui/internal/widget/EditableListViewDelegate;

    invoke-virtual {v0}, Lcom/miui/internal/widget/EditableListViewDelegate;->isAllItemsChecked()Z

    move-result v0

    return v0
.end method

.method public isInActionMode()Z
    .locals 1

    .prologue
    .line 225
    iget-object v0, p0, Lmiui/widget/EditableListViewWrapper;->a:Lcom/miui/internal/widget/EditableListViewDelegate;

    invoke-virtual {v0}, Lcom/miui/internal/widget/EditableListViewDelegate;->isInActionMode()Z

    move-result v0

    return v0
.end method

.method public isItemChecked(I)Z
    .locals 1

    .prologue
    .line 136
    iget-object v0, p0, Lmiui/widget/EditableListViewWrapper;->a:Lcom/miui/internal/widget/EditableListViewDelegate;

    invoke-virtual {v0, p1}, Lcom/miui/internal/widget/EditableListViewDelegate;->isItemChecked(I)Z

    move-result v0

    return v0
.end method

.method public isItemIdChecked(J)Z
    .locals 1

    .prologue
    .line 147
    iget-object v0, p0, Lmiui/widget/EditableListViewWrapper;->a:Lcom/miui/internal/widget/EditableListViewDelegate;

    invoke-virtual {v0, p1, p2}, Lcom/miui/internal/widget/EditableListViewDelegate;->isItemIdChecked(J)Z

    move-result v0

    return v0
.end method

.method public setAdapter(Landroid/widget/ListAdapter;)V
    .locals 1

    .prologue
    .line 205
    iget-object v0, p0, Lmiui/widget/EditableListViewWrapper;->a:Lcom/miui/internal/widget/EditableListViewDelegate;

    invoke-virtual {v0, p1}, Lcom/miui/internal/widget/EditableListViewDelegate;->setAdapter(Landroid/widget/ListAdapter;)V

    .line 206
    return-void
.end method

.method public setAllItemsChecked(Z)V
    .locals 1

    .prologue
    .line 89
    iget-object v0, p0, Lmiui/widget/EditableListViewWrapper;->a:Lcom/miui/internal/widget/EditableListViewDelegate;

    invoke-virtual {v0, p1}, Lcom/miui/internal/widget/EditableListViewDelegate;->setAllItemsChecked(Z)V

    .line 90
    return-void
.end method

.method public setItemCheckFilter(Lmiui/widget/EditableListView$ItemCheckFilter;)V
    .locals 1

    .prologue
    .line 254
    iget-object v0, p0, Lmiui/widget/EditableListViewWrapper;->a:Lcom/miui/internal/widget/EditableListViewDelegate;

    invoke-virtual {v0, p1}, Lcom/miui/internal/widget/EditableListViewDelegate;->setItemCheckFilter(Lmiui/widget/EditableListView$ItemCheckFilter;)V

    .line 255
    return-void
.end method

.method public setItemChecked(IZ)V
    .locals 1

    .prologue
    .line 108
    iget-object v0, p0, Lmiui/widget/EditableListViewWrapper;->a:Lcom/miui/internal/widget/EditableListViewDelegate;

    invoke-virtual {v0, p1, p2}, Lcom/miui/internal/widget/EditableListViewDelegate;->setItemChecked(IZ)V

    .line 109
    return-void
.end method

.method public setMultiChoiceModeListener(Landroid/widget/AbsListView$MultiChoiceModeListener;)V
    .locals 1

    .prologue
    .line 216
    iget-object v0, p0, Lmiui/widget/EditableListViewWrapper;->a:Lcom/miui/internal/widget/EditableListViewDelegate;

    invoke-virtual {v0, p1}, Lcom/miui/internal/widget/EditableListViewDelegate;->setMultiChoiceModeListener(Landroid/widget/AbsListView$MultiChoiceModeListener;)V

    .line 217
    return-void
.end method

.method protected updateCheckStatus(Landroid/view/ActionMode;)V
    .locals 1

    .prologue
    .line 245
    iget-object v0, p0, Lmiui/widget/EditableListViewWrapper;->a:Lcom/miui/internal/widget/EditableListViewDelegate;

    invoke-virtual {v0, p1}, Lcom/miui/internal/widget/EditableListViewDelegate;->updateCheckStatus(Landroid/view/ActionMode;)V

    .line 246
    return-void
.end method

.method protected updateOnScreenCheckedView(Landroid/view/View;IJ)V
    .locals 1

    .prologue
    .line 236
    iget-object v0, p0, Lmiui/widget/EditableListViewWrapper;->a:Lcom/miui/internal/widget/EditableListViewDelegate;

    invoke-virtual {v0, p1, p2, p3, p4}, Lcom/miui/internal/widget/EditableListViewDelegate;->updateOnScreenCheckedView(Landroid/view/View;IJ)V

    .line 237
    return-void
.end method
