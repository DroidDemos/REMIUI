.class Lcom/miui/internal/app/f;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/internal/app/FragmentDelegate;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic eP:Lcom/miui/internal/app/FragmentDelegate;


# direct methods
.method constructor <init>(Lcom/miui/internal/app/FragmentDelegate;)V
    .locals 0

    .prologue
    .line 52
    iput-object p1, p0, Lcom/miui/internal/app/f;->eP:Lcom/miui/internal/app/FragmentDelegate;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .locals 4

    .prologue
    const/4 v0, 0x1

    const/4 v3, 0x0

    const/4 v2, 0x0

    .line 55
    .line 56
    iget-object v1, p0, Lcom/miui/internal/app/f;->eP:Lcom/miui/internal/app/FragmentDelegate;

    invoke-static {v1}, Lcom/miui/internal/app/FragmentDelegate;->a(Lcom/miui/internal/app/FragmentDelegate;)B

    move-result v1

    and-int/lit8 v1, v1, 0x1

    if-ne v1, v0, :cond_0

    .line 57
    iget-object v1, p0, Lcom/miui/internal/app/f;->eP:Lcom/miui/internal/app/FragmentDelegate;

    invoke-static {v1, v2}, Lcom/miui/internal/app/FragmentDelegate;->a(Lcom/miui/internal/app/FragmentDelegate;Lcom/miui/internal/view/menu/MenuBuilder;)Lcom/miui/internal/view/menu/MenuBuilder;

    .line 60
    :cond_0
    iget-object v1, p0, Lcom/miui/internal/app/f;->eP:Lcom/miui/internal/app/FragmentDelegate;

    invoke-static {v1}, Lcom/miui/internal/app/FragmentDelegate;->b(Lcom/miui/internal/app/FragmentDelegate;)Lcom/miui/internal/view/menu/MenuBuilder;

    move-result-object v1

    if-nez v1, :cond_1

    .line 61
    iget-object v0, p0, Lcom/miui/internal/app/f;->eP:Lcom/miui/internal/app/FragmentDelegate;

    iget-object v1, p0, Lcom/miui/internal/app/f;->eP:Lcom/miui/internal/app/FragmentDelegate;

    invoke-virtual {v1}, Lcom/miui/internal/app/FragmentDelegate;->createMenu()Lcom/miui/internal/view/menu/MenuBuilder;

    move-result-object v1

    invoke-static {v0, v1}, Lcom/miui/internal/app/FragmentDelegate;->a(Lcom/miui/internal/app/FragmentDelegate;Lcom/miui/internal/view/menu/MenuBuilder;)Lcom/miui/internal/view/menu/MenuBuilder;

    .line 62
    iget-object v0, p0, Lcom/miui/internal/app/f;->eP:Lcom/miui/internal/app/FragmentDelegate;

    iget-object v1, p0, Lcom/miui/internal/app/f;->eP:Lcom/miui/internal/app/FragmentDelegate;

    invoke-static {v1}, Lcom/miui/internal/app/FragmentDelegate;->b(Lcom/miui/internal/app/FragmentDelegate;)Lcom/miui/internal/view/menu/MenuBuilder;

    move-result-object v1

    invoke-virtual {v0, v3, v1}, Lcom/miui/internal/app/FragmentDelegate;->onCreatePanelMenu(ILandroid/view/Menu;)Z

    move-result v0

    .line 65
    :cond_1
    if-eqz v0, :cond_2

    .line 66
    iget-object v0, p0, Lcom/miui/internal/app/f;->eP:Lcom/miui/internal/app/FragmentDelegate;

    iget-object v1, p0, Lcom/miui/internal/app/f;->eP:Lcom/miui/internal/app/FragmentDelegate;

    invoke-static {v1}, Lcom/miui/internal/app/FragmentDelegate;->b(Lcom/miui/internal/app/FragmentDelegate;)Lcom/miui/internal/view/menu/MenuBuilder;

    move-result-object v1

    invoke-virtual {v0, v3, v2, v1}, Lcom/miui/internal/app/FragmentDelegate;->onPreparePanel(ILandroid/view/View;Landroid/view/Menu;)Z

    move-result v0

    .line 69
    :cond_2
    if-eqz v0, :cond_3

    .line 70
    iget-object v0, p0, Lcom/miui/internal/app/f;->eP:Lcom/miui/internal/app/FragmentDelegate;

    iget-object v1, p0, Lcom/miui/internal/app/f;->eP:Lcom/miui/internal/app/FragmentDelegate;

    invoke-static {v1}, Lcom/miui/internal/app/FragmentDelegate;->b(Lcom/miui/internal/app/FragmentDelegate;)Lcom/miui/internal/view/menu/MenuBuilder;

    move-result-object v1

    invoke-virtual {v0, v1}, Lcom/miui/internal/app/FragmentDelegate;->setMenu(Lcom/miui/internal/view/menu/MenuBuilder;)V

    .line 76
    :goto_0
    iget-object v0, p0, Lcom/miui/internal/app/f;->eP:Lcom/miui/internal/app/FragmentDelegate;

    const/16 v1, -0x12

    invoke-static {v0, v1}, Lcom/miui/internal/app/FragmentDelegate;->a(Lcom/miui/internal/app/FragmentDelegate;I)B

    .line 77
    return-void

    .line 72
    :cond_3
    iget-object v0, p0, Lcom/miui/internal/app/f;->eP:Lcom/miui/internal/app/FragmentDelegate;

    invoke-virtual {v0, v2}, Lcom/miui/internal/app/FragmentDelegate;->setMenu(Lcom/miui/internal/view/menu/MenuBuilder;)V

    .line 73
    iget-object v0, p0, Lcom/miui/internal/app/f;->eP:Lcom/miui/internal/app/FragmentDelegate;

    invoke-static {v0, v2}, Lcom/miui/internal/app/FragmentDelegate;->a(Lcom/miui/internal/app/FragmentDelegate;Lcom/miui/internal/view/menu/MenuBuilder;)Lcom/miui/internal/view/menu/MenuBuilder;

    goto :goto_0
.end method
