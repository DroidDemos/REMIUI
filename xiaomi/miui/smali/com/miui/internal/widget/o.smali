.class Lcom/miui/internal/widget/o;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lmiui/view/ActionModeAnimationListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/internal/widget/EditableListViewDelegate$b;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic Ig:Lcom/miui/internal/widget/EditableListViewDelegate$b;


# direct methods
.method constructor <init>(Lcom/miui/internal/widget/EditableListViewDelegate$b;)V
    .locals 0

    .prologue
    .line 396
    iput-object p1, p0, Lcom/miui/internal/widget/o;->Ig:Lcom/miui/internal/widget/EditableListViewDelegate$b;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onStart(Z)V
    .locals 3

    .prologue
    .line 399
    if-eqz p1, :cond_0

    .line 400
    iget-object v0, p0, Lcom/miui/internal/widget/o;->Ig:Lcom/miui/internal/widget/EditableListViewDelegate$b;

    iget-object v0, v0, Lcom/miui/internal/widget/EditableListViewDelegate$b;->ay:Lcom/miui/internal/widget/EditableListViewDelegate;

    invoke-static {v0}, Lcom/miui/internal/widget/EditableListViewDelegate;->a(Lcom/miui/internal/widget/EditableListViewDelegate;)Ljava/util/List;

    move-result-object v0

    .line 401
    invoke-interface {v0}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :goto_0
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_0

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/widget/CheckBox;

    .line 402
    const/4 v2, 0x0

    invoke-virtual {v0, v2}, Landroid/widget/CheckBox;->setVisibility(I)V

    .line 403
    invoke-virtual {v0}, Landroid/widget/CheckBox;->getWidth()I

    move-result v2

    int-to-float v2, v2

    invoke-virtual {v0, v2}, Landroid/widget/CheckBox;->setTranslationX(F)V

    .line 404
    const/4 v2, 0x0

    invoke-virtual {v0, v2}, Landroid/widget/CheckBox;->setAlpha(F)V

    goto :goto_0

    .line 407
    :cond_0
    return-void
.end method

.method public onStop(Z)V
    .locals 3

    .prologue
    .line 429
    if-nez p1, :cond_0

    .line 430
    iget-object v0, p0, Lcom/miui/internal/widget/o;->Ig:Lcom/miui/internal/widget/EditableListViewDelegate$b;

    iget-object v0, v0, Lcom/miui/internal/widget/EditableListViewDelegate$b;->ay:Lcom/miui/internal/widget/EditableListViewDelegate;

    const/4 v1, 0x0

    invoke-static {v0, v1}, Lcom/miui/internal/widget/EditableListViewDelegate;->a(Lcom/miui/internal/widget/EditableListViewDelegate;Landroid/view/ActionMode;)Landroid/view/ActionMode;

    .line 431
    iget-object v0, p0, Lcom/miui/internal/widget/o;->Ig:Lcom/miui/internal/widget/EditableListViewDelegate$b;

    iget-object v0, v0, Lcom/miui/internal/widget/EditableListViewDelegate$b;->ay:Lcom/miui/internal/widget/EditableListViewDelegate;

    invoke-static {v0}, Lcom/miui/internal/widget/EditableListViewDelegate;->a(Lcom/miui/internal/widget/EditableListViewDelegate;)Ljava/util/List;

    move-result-object v0

    .line 432
    invoke-interface {v0}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :goto_0
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_0

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/widget/CheckBox;

    .line 433
    const/16 v2, 0x8

    invoke-virtual {v0, v2}, Landroid/widget/CheckBox;->setVisibility(I)V

    goto :goto_0

    .line 436
    :cond_0
    return-void
.end method

.method public onUpdate(ZF)V
    .locals 5

    .prologue
    const/high16 v4, 0x3f800000

    .line 411
    if-nez p1, :cond_0

    .line 412
    sub-float p2, v4, p2

    .line 414
    :cond_0
    iget-object v0, p0, Lcom/miui/internal/widget/o;->Ig:Lcom/miui/internal/widget/EditableListViewDelegate$b;

    iget-object v0, v0, Lcom/miui/internal/widget/EditableListViewDelegate$b;->ay:Lcom/miui/internal/widget/EditableListViewDelegate;

    invoke-static {v0}, Lcom/miui/internal/widget/EditableListViewDelegate;->a(Lcom/miui/internal/widget/EditableListViewDelegate;)Ljava/util/List;

    move-result-object v0

    .line 415
    invoke-interface {v0}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :goto_0
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_1

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/widget/CheckBox;

    .line 416
    invoke-virtual {v0, p2}, Landroid/widget/CheckBox;->setAlpha(F)V

    .line 417
    invoke-virtual {v0}, Landroid/widget/CheckBox;->getWidth()I

    move-result v2

    int-to-float v2, v2

    sub-float v3, v4, p2

    mul-float/2addr v2, v3

    invoke-virtual {v0, v2}, Landroid/widget/CheckBox;->setTranslationX(F)V

    goto :goto_0

    .line 419
    :cond_1
    if-eqz p1, :cond_2

    cmpl-float v0, p2, v4

    if-nez v0, :cond_2

    .line 420
    iget-object v0, p0, Lcom/miui/internal/widget/o;->Ig:Lcom/miui/internal/widget/EditableListViewDelegate$b;

    iget-object v0, v0, Lcom/miui/internal/widget/EditableListViewDelegate$b;->ay:Lcom/miui/internal/widget/EditableListViewDelegate;

    invoke-static {v0}, Lcom/miui/internal/widget/EditableListViewDelegate;->b(Lcom/miui/internal/widget/EditableListViewDelegate;)Landroid/widget/AbsListView;

    move-result-object v0

    invoke-virtual {v0}, Landroid/widget/AbsListView;->getHeight()I

    move-result v0

    .line 421
    iget-object v1, p0, Lcom/miui/internal/widget/o;->Ig:Lcom/miui/internal/widget/EditableListViewDelegate$b;

    iget-object v1, v1, Lcom/miui/internal/widget/EditableListViewDelegate$b;->ay:Lcom/miui/internal/widget/EditableListViewDelegate;

    invoke-static {v1}, Lcom/miui/internal/widget/EditableListViewDelegate;->c(Lcom/miui/internal/widget/EditableListViewDelegate;)I

    move-result v1

    if-le v1, v0, :cond_2

    .line 422
    iget-object v1, p0, Lcom/miui/internal/widget/o;->Ig:Lcom/miui/internal/widget/EditableListViewDelegate$b;

    iget-object v1, v1, Lcom/miui/internal/widget/EditableListViewDelegate$b;->ay:Lcom/miui/internal/widget/EditableListViewDelegate;

    invoke-static {v1}, Lcom/miui/internal/widget/EditableListViewDelegate;->b(Lcom/miui/internal/widget/EditableListViewDelegate;)Landroid/widget/AbsListView;

    move-result-object v1

    iget-object v2, p0, Lcom/miui/internal/widget/o;->Ig:Lcom/miui/internal/widget/EditableListViewDelegate$b;

    iget-object v2, v2, Lcom/miui/internal/widget/EditableListViewDelegate$b;->ay:Lcom/miui/internal/widget/EditableListViewDelegate;

    invoke-static {v2}, Lcom/miui/internal/widget/EditableListViewDelegate;->c(Lcom/miui/internal/widget/EditableListViewDelegate;)I

    move-result v2

    sub-int v0, v2, v0

    const/16 v2, 0x64

    invoke-virtual {v1, v0, v2}, Landroid/widget/AbsListView;->smoothScrollBy(II)V

    .line 425
    :cond_2
    return-void
.end method
