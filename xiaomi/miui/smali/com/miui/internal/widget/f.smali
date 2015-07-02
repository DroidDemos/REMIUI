.class Lcom/miui/internal/widget/f;
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
    .line 145
    iput-object p1, p0, Lcom/miui/internal/widget/f;->mT:Lcom/miui/internal/widget/ActionBarView;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onClick(Landroid/view/View;)V
    .locals 1

    .prologue
    .line 148
    iget-object v0, p0, Lcom/miui/internal/widget/f;->mT:Lcom/miui/internal/widget/ActionBarView;

    invoke-static {v0}, Lcom/miui/internal/widget/ActionBarView;->b(Lcom/miui/internal/widget/ActionBarView;)Lcom/miui/internal/widget/ActionBarView$a;

    move-result-object v0

    iget-object v0, v0, Lcom/miui/internal/widget/ActionBarView$a;->mS:Lcom/miui/internal/view/menu/MenuItemImpl;

    .line 149
    if-eqz v0, :cond_0

    .line 150
    invoke-virtual {v0}, Lcom/miui/internal/view/menu/MenuItemImpl;->collapseActionView()Z

    .line 152
    :cond_0
    return-void
.end method
