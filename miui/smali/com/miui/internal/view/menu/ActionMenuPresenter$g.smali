.class Lcom/miui/internal/view/menu/ActionMenuPresenter$g;
.super Lcom/miui/internal/view/menu/MenuPopupHelper;
.source "SourceFile"

# interfaces
.implements Lcom/miui/internal/view/menu/ActionMenuPresenter$d;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/internal/view/menu/ActionMenuPresenter;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x2
    name = "g"
.end annotation


# instance fields
.field final synthetic eo:Lcom/miui/internal/view/menu/ActionMenuPresenter;


# direct methods
.method public constructor <init>(Lcom/miui/internal/view/menu/ActionMenuPresenter;Landroid/content/Context;Lcom/miui/internal/view/menu/MenuBuilder;Landroid/view/View;Z)V
    .locals 1

    .prologue
    .line 575
    iput-object p1, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter$g;->eo:Lcom/miui/internal/view/menu/ActionMenuPresenter;

    .line 576
    invoke-direct {p0, p2, p3, p4, p5}, Lcom/miui/internal/view/menu/MenuPopupHelper;-><init>(Landroid/content/Context;Lcom/miui/internal/view/menu/MenuBuilder;Landroid/view/View;Z)V

    .line 577
    iget-object v0, p1, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mC:Lcom/miui/internal/view/menu/ActionMenuPresenter$f;

    invoke-virtual {p0, v0}, Lcom/miui/internal/view/menu/ActionMenuPresenter$g;->setCallback(Lcom/miui/internal/view/menu/MenuPresenter$Callback;)V

    .line 578
    sget v0, Lcom/miui/internal/R$layout;->overflow_popup_menu_item_layout:I

    invoke-virtual {p0, v0}, Lcom/miui/internal/view/menu/ActionMenuPresenter$g;->setMenuItemLayout(I)V

    .line 579
    return-void
.end method


# virtual methods
.method public e(Lcom/miui/internal/view/menu/MenuBuilder;)V
    .locals 0

    .prologue
    .line 590
    return-void
.end method

.method public onDismiss()V
    .locals 2

    .prologue
    .line 583
    invoke-super {p0}, Lcom/miui/internal/view/menu/MenuPopupHelper;->onDismiss()V

    .line 584
    iget-object v0, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter$g;->eo:Lcom/miui/internal/view/menu/ActionMenuPresenter;

    iget-object v0, v0, Lcom/miui/internal/view/menu/ActionMenuPresenter;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    invoke-virtual {v0}, Lcom/miui/internal/view/menu/MenuBuilder;->close()V

    .line 585
    iget-object v0, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter$g;->eo:Lcom/miui/internal/view/menu/ActionMenuPresenter;

    const/4 v1, 0x0

    invoke-static {v0, v1}, Lcom/miui/internal/view/menu/ActionMenuPresenter;->a(Lcom/miui/internal/view/menu/ActionMenuPresenter;Lcom/miui/internal/view/menu/ActionMenuPresenter$d;)Lcom/miui/internal/view/menu/ActionMenuPresenter$d;

    .line 586
    return-void
.end method
