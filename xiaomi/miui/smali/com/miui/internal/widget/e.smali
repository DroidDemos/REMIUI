.class Lcom/miui/internal/widget/e;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroid/view/View$OnClickListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/internal/widget/ActionBarView;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic mT:Lcom/miui/internal/widget/ActionBarView;


# direct methods
.method constructor <init>(Lcom/miui/internal/widget/ActionBarView;)V
    .locals 0

    .prologue
    .line 155
    iput-object p1, p0, Lcom/miui/internal/widget/e;->mT:Lcom/miui/internal/widget/ActionBarView;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onClick(Landroid/view/View;)V
    .locals 3

    .prologue
    .line 157
    iget-object v0, p0, Lcom/miui/internal/widget/e;->mT:Lcom/miui/internal/widget/ActionBarView;

    iget-object v0, v0, Lcom/miui/internal/widget/ActionBarView;->ij:Landroid/view/Window$Callback;

    const/4 v1, 0x0

    iget-object v2, p0, Lcom/miui/internal/widget/e;->mT:Lcom/miui/internal/widget/ActionBarView;

    invoke-static {v2}, Lcom/miui/internal/widget/ActionBarView;->c(Lcom/miui/internal/widget/ActionBarView;)Lcom/miui/internal/view/menu/ActionMenuItem;

    move-result-object v2

    invoke-interface {v0, v1, v2}, Landroid/view/Window$Callback;->onMenuItemSelected(ILandroid/view/MenuItem;)Z

    .line 158
    return-void
.end method
