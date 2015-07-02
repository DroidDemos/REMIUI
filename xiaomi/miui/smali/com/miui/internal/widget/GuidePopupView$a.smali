.class Lcom/miui/internal/widget/GuidePopupView$a;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroid/view/View$OnClickListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/internal/widget/GuidePopupView;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = "a"
.end annotation


# instance fields
.field public ba:Landroid/view/View$OnClickListener;

.field final synthetic bb:Lcom/miui/internal/widget/GuidePopupView;


# direct methods
.method constructor <init>(Lcom/miui/internal/widget/GuidePopupView;)V
    .locals 0

    .prologue
    .line 603
    iput-object p1, p0, Lcom/miui/internal/widget/GuidePopupView$a;->bb:Lcom/miui/internal/widget/GuidePopupView;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onClick(Landroid/view/View;)V
    .locals 2

    .prologue
    .line 613
    iget-object v0, p0, Lcom/miui/internal/widget/GuidePopupView$a;->ba:Landroid/view/View$OnClickListener;

    if-eqz v0, :cond_0

    .line 614
    iget-object v0, p0, Lcom/miui/internal/widget/GuidePopupView$a;->ba:Landroid/view/View$OnClickListener;

    invoke-interface {v0, p1}, Landroid/view/View$OnClickListener;->onClick(Landroid/view/View;)V

    .line 616
    :cond_0
    iget-object v0, p0, Lcom/miui/internal/widget/GuidePopupView$a;->bb:Lcom/miui/internal/widget/GuidePopupView;

    invoke-static {v0}, Lcom/miui/internal/widget/GuidePopupView;->a(Lcom/miui/internal/widget/GuidePopupView;)Lmiui/widget/GuidePopupWindow;

    move-result-object v0

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Lmiui/widget/GuidePopupWindow;->dismiss(Z)V

    .line 617
    return-void
.end method

.method public setOnClickListener(Landroid/view/View$OnClickListener;)V
    .locals 0

    .prologue
    .line 608
    iput-object p1, p0, Lcom/miui/internal/widget/GuidePopupView$a;->ba:Landroid/view/View$OnClickListener;

    .line 609
    return-void
.end method
