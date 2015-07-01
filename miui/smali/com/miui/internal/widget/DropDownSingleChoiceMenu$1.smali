.class Lcom/miui/internal/widget/DropDownSingleChoiceMenu$1;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroid/widget/AdapterView$OnItemClickListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/internal/widget/DropDownSingleChoiceMenu;->show()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic xb:Lcom/miui/internal/widget/DropDownSingleChoiceMenu;


# direct methods
.method constructor <init>(Lcom/miui/internal/widget/DropDownSingleChoiceMenu;)V
    .locals 0

    .prologue
    .line 84
    iput-object p1, p0, Lcom/miui/internal/widget/DropDownSingleChoiceMenu$1;->xb:Lcom/miui/internal/widget/DropDownSingleChoiceMenu;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onItemClick(Landroid/widget/AdapterView;Landroid/view/View;IJ)V
    .locals 2
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
    .line 87
    iget-object v0, p0, Lcom/miui/internal/widget/DropDownSingleChoiceMenu$1;->xb:Lcom/miui/internal/widget/DropDownSingleChoiceMenu;

    invoke-static {v0, p3}, Lcom/miui/internal/widget/DropDownSingleChoiceMenu;->a(Lcom/miui/internal/widget/DropDownSingleChoiceMenu;I)I

    .line 88
    iget-object v0, p0, Lcom/miui/internal/widget/DropDownSingleChoiceMenu$1;->xb:Lcom/miui/internal/widget/DropDownSingleChoiceMenu;

    invoke-static {v0}, Lcom/miui/internal/widget/DropDownSingleChoiceMenu;->a(Lcom/miui/internal/widget/DropDownSingleChoiceMenu;)Lcom/miui/internal/widget/DropDownSingleChoiceMenu$OnMenuListener;

    move-result-object v0

    if-eqz v0, :cond_0

    .line 89
    iget-object v0, p0, Lcom/miui/internal/widget/DropDownSingleChoiceMenu$1;->xb:Lcom/miui/internal/widget/DropDownSingleChoiceMenu;

    invoke-static {v0}, Lcom/miui/internal/widget/DropDownSingleChoiceMenu;->a(Lcom/miui/internal/widget/DropDownSingleChoiceMenu;)Lcom/miui/internal/widget/DropDownSingleChoiceMenu$OnMenuListener;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/internal/widget/DropDownSingleChoiceMenu$1;->xb:Lcom/miui/internal/widget/DropDownSingleChoiceMenu;

    invoke-interface {v0, v1, p3}, Lcom/miui/internal/widget/DropDownSingleChoiceMenu$OnMenuListener;->onItemSelected(Lcom/miui/internal/widget/DropDownSingleChoiceMenu;I)V

    .line 91
    :cond_0
    iget-object v0, p0, Lcom/miui/internal/widget/DropDownSingleChoiceMenu$1;->xb:Lcom/miui/internal/widget/DropDownSingleChoiceMenu;

    invoke-virtual {v0}, Lcom/miui/internal/widget/DropDownSingleChoiceMenu;->dismiss()V

    .line 92
    return-void
.end method
