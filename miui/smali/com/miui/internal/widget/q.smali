.class Lcom/miui/internal/widget/q;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroid/view/View$OnClickListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/internal/widget/ActionBarContextView;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic A:Lcom/miui/internal/widget/ActionBarContextView;


# direct methods
.method constructor <init>(Lcom/miui/internal/widget/ActionBarContextView;)V
    .locals 0

    .prologue
    .line 93
    iput-object p1, p0, Lcom/miui/internal/widget/q;->A:Lcom/miui/internal/widget/ActionBarContextView;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onClick(Landroid/view/View;)V
    .locals 3

    .prologue
    .line 95
    invoke-virtual {p1}, Landroid/view/View;->getId()I

    move-result v0

    const v1, 0x1020019

    if-ne v0, v1, :cond_1

    iget-object v0, p0, Lcom/miui/internal/widget/q;->A:Lcom/miui/internal/widget/ActionBarContextView;

    invoke-static {v0}, Lcom/miui/internal/widget/ActionBarContextView;->a(Lcom/miui/internal/widget/ActionBarContextView;)Lcom/miui/internal/view/menu/ActionMenuItem;

    move-result-object v0

    move-object v2, v0

    .line 98
    :goto_0
    iget-object v0, p0, Lcom/miui/internal/widget/q;->A:Lcom/miui/internal/widget/ActionBarContextView;

    invoke-static {v0}, Lcom/miui/internal/widget/ActionBarContextView;->c(Lcom/miui/internal/widget/ActionBarContextView;)Ljava/lang/ref/WeakReference;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/ref/WeakReference;->get()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/view/EditActionModeImpl;

    .line 99
    if-eqz v0, :cond_0

    .line 100
    invoke-virtual {v0}, Lcom/miui/internal/view/EditActionModeImpl;->getMenu()Landroid/view/Menu;

    move-result-object v1

    check-cast v1, Lcom/miui/internal/view/menu/MenuBuilder;

    check-cast v1, Lcom/miui/internal/view/menu/MenuBuilder;

    invoke-virtual {v0, v1, v2}, Lcom/miui/internal/view/EditActionModeImpl;->onMenuItemSelected(Lcom/miui/internal/view/menu/MenuBuilder;Landroid/view/MenuItem;)Z

    .line 102
    :cond_0
    return-void

    .line 95
    :cond_1
    iget-object v0, p0, Lcom/miui/internal/widget/q;->A:Lcom/miui/internal/widget/ActionBarContextView;

    invoke-static {v0}, Lcom/miui/internal/widget/ActionBarContextView;->b(Lcom/miui/internal/widget/ActionBarContextView;)Lcom/miui/internal/view/menu/ActionMenuItem;

    move-result-object v0

    move-object v2, v0

    goto :goto_0
.end method
