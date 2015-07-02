.class Lcom/miui/internal/view/menu/MenuItemImpl$1;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroid/view/ActionProvider$VisibilityListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/internal/view/menu/MenuItemImpl;->setSupportActionProvider(Landroid/view/ActionProvider;)Landroid/view/MenuItem;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic aD:Lcom/miui/internal/view/menu/MenuItemImpl;


# direct methods
.method constructor <init>(Lcom/miui/internal/view/menu/MenuItemImpl;)V
    .locals 0

    .prologue
    .line 633
    iput-object p1, p0, Lcom/miui/internal/view/menu/MenuItemImpl$1;->aD:Lcom/miui/internal/view/menu/MenuItemImpl;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onActionProviderVisibilityChanged(Z)V
    .locals 2

    .prologue
    .line 636
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuItemImpl$1;->aD:Lcom/miui/internal/view/menu/MenuItemImpl;

    invoke-static {v0}, Lcom/miui/internal/view/menu/MenuItemImpl;->c(Lcom/miui/internal/view/menu/MenuItemImpl;)Lcom/miui/internal/view/menu/MenuBuilder;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/internal/view/menu/MenuItemImpl$1;->aD:Lcom/miui/internal/view/menu/MenuItemImpl;

    invoke-virtual {v0, v1}, Lcom/miui/internal/view/menu/MenuBuilder;->a(Lcom/miui/internal/view/menu/MenuItemImpl;)V

    .line 637
    return-void
.end method
