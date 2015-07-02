.class Lcom/miui/internal/widget/SearchActionModeView$e;
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
    name = "e"
.end annotation


# instance fields
.field final synthetic iC:Lcom/miui/internal/widget/SearchActionModeView;


# direct methods
.method constructor <init>(Lcom/miui/internal/widget/SearchActionModeView;)V
    .locals 0

    .prologue
    .line 731
    iput-object p1, p0, Lcom/miui/internal/widget/SearchActionModeView$e;->iC:Lcom/miui/internal/widget/SearchActionModeView;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onStart(Z)V
    .locals 2

    .prologue
    .line 735
    if-eqz p1, :cond_0

    .line 736
    iget-object v0, p0, Lcom/miui/internal/widget/SearchActionModeView$e;->iC:Lcom/miui/internal/widget/SearchActionModeView;

    invoke-static {v0}, Lcom/miui/internal/widget/SearchActionModeView;->o(Lcom/miui/internal/widget/SearchActionModeView;)Landroid/view/View;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/internal/widget/SearchActionModeView$e;->iC:Lcom/miui/internal/widget/SearchActionModeView;

    invoke-virtual {v0, v1}, Landroid/view/View;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 737
    iget-object v0, p0, Lcom/miui/internal/widget/SearchActionModeView$e;->iC:Lcom/miui/internal/widget/SearchActionModeView;

    invoke-static {v0}, Lcom/miui/internal/widget/SearchActionModeView;->o(Lcom/miui/internal/widget/SearchActionModeView;)Landroid/view/View;

    move-result-object v0

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Landroid/view/View;->setVisibility(I)V

    .line 738
    iget-object v0, p0, Lcom/miui/internal/widget/SearchActionModeView$e;->iC:Lcom/miui/internal/widget/SearchActionModeView;

    invoke-static {v0}, Lcom/miui/internal/widget/SearchActionModeView;->o(Lcom/miui/internal/widget/SearchActionModeView;)Landroid/view/View;

    move-result-object v0

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Landroid/view/View;->setAlpha(F)V

    .line 740
    :cond_0
    return-void
.end method

.method public onStop(Z)V
    .locals 2

    .prologue
    const/16 v1, 0x8

    .line 750
    if-eqz p1, :cond_1

    .line 751
    iget-object v0, p0, Lcom/miui/internal/widget/SearchActionModeView$e;->iC:Lcom/miui/internal/widget/SearchActionModeView;

    invoke-static {v0}, Lcom/miui/internal/widget/SearchActionModeView;->b(Lcom/miui/internal/widget/SearchActionModeView;)Landroid/widget/EditText;

    move-result-object v0

    invoke-virtual {v0}, Landroid/widget/EditText;->getText()Landroid/text/Editable;

    move-result-object v0

    invoke-interface {v0}, Landroid/text/Editable;->length()I

    move-result v0

    if-lez v0, :cond_0

    .line 752
    iget-object v0, p0, Lcom/miui/internal/widget/SearchActionModeView$e;->iC:Lcom/miui/internal/widget/SearchActionModeView;

    invoke-static {v0}, Lcom/miui/internal/widget/SearchActionModeView;->o(Lcom/miui/internal/widget/SearchActionModeView;)Landroid/view/View;

    move-result-object v0

    invoke-virtual {v0, v1}, Landroid/view/View;->setVisibility(I)V

    .line 758
    :cond_0
    :goto_0
    return-void

    .line 755
    :cond_1
    iget-object v0, p0, Lcom/miui/internal/widget/SearchActionModeView$e;->iC:Lcom/miui/internal/widget/SearchActionModeView;

    invoke-static {v0}, Lcom/miui/internal/widget/SearchActionModeView;->o(Lcom/miui/internal/widget/SearchActionModeView;)Landroid/view/View;

    move-result-object v0

    invoke-virtual {v0, v1}, Landroid/view/View;->setVisibility(I)V

    .line 756
    iget-object v0, p0, Lcom/miui/internal/widget/SearchActionModeView$e;->iC:Lcom/miui/internal/widget/SearchActionModeView;

    invoke-static {v0}, Lcom/miui/internal/widget/SearchActionModeView;->o(Lcom/miui/internal/widget/SearchActionModeView;)Landroid/view/View;

    move-result-object v0

    const/high16 v1, 0x3f800000

    invoke-virtual {v0, v1}, Landroid/view/View;->setAlpha(F)V

    goto :goto_0
.end method

.method public onUpdate(ZF)V
    .locals 1

    .prologue
    .line 744
    if-eqz p1, :cond_0

    .line 745
    :goto_0
    iget-object v0, p0, Lcom/miui/internal/widget/SearchActionModeView$e;->iC:Lcom/miui/internal/widget/SearchActionModeView;

    invoke-static {v0}, Lcom/miui/internal/widget/SearchActionModeView;->o(Lcom/miui/internal/widget/SearchActionModeView;)Landroid/view/View;

    move-result-object v0

    invoke-virtual {v0, p2}, Landroid/view/View;->setAlpha(F)V

    .line 746
    return-void

    .line 744
    :cond_0
    const/high16 v0, 0x3f800000

    sub-float p2, v0, p2

    goto :goto_0
.end method
