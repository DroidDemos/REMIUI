.class Lcom/miui/internal/widget/d;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroid/widget/AdapterView$OnItemSelectedListener;


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
    .line 132
    iput-object p1, p0, Lcom/miui/internal/widget/d;->mT:Lcom/miui/internal/widget/ActionBarView;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onItemSelected(Landroid/widget/AdapterView;Landroid/view/View;IJ)V
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Landroid/widget/AdapterView",
            "<*>;",
            "Landroid/view/View;",
            "IJ)V"
        }
    .end annotation

    .prologue
    .line 135
    iget-object v0, p0, Lcom/miui/internal/widget/d;->mT:Lcom/miui/internal/widget/ActionBarView;

    invoke-static {v0}, Lcom/miui/internal/widget/ActionBarView;->a(Lcom/miui/internal/widget/ActionBarView;)Landroid/app/ActionBar$OnNavigationListener;

    move-result-object v0

    if-eqz v0, :cond_0

    .line 136
    iget-object v0, p0, Lcom/miui/internal/widget/d;->mT:Lcom/miui/internal/widget/ActionBarView;

    invoke-static {v0}, Lcom/miui/internal/widget/ActionBarView;->a(Lcom/miui/internal/widget/ActionBarView;)Landroid/app/ActionBar$OnNavigationListener;

    move-result-object v0

    invoke-interface {v0, p3, p4, p5}, Landroid/app/ActionBar$OnNavigationListener;->onNavigationItemSelected(IJ)Z

    .line 138
    :cond_0
    return-void
.end method

.method public onNothingSelected(Landroid/widget/AdapterView;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Landroid/widget/AdapterView",
            "<*>;)V"
        }
    .end annotation

    .prologue
    .line 142
    return-void
.end method
