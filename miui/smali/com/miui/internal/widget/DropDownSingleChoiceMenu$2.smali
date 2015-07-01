.class Lcom/miui/internal/widget/DropDownSingleChoiceMenu$2;
.super Lcom/miui/internal/widget/DropDownPopupWindow$DefaultContainerController;
.source "SourceFile"


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
    .line 66
    iput-object p1, p0, Lcom/miui/internal/widget/DropDownSingleChoiceMenu$2;->xb:Lcom/miui/internal/widget/DropDownSingleChoiceMenu;

    invoke-direct {p0}, Lcom/miui/internal/widget/DropDownPopupWindow$DefaultContainerController;-><init>()V

    return-void
.end method


# virtual methods
.method public onDismiss()V
    .locals 1

    .prologue
    .line 76
    iget-object v0, p0, Lcom/miui/internal/widget/DropDownSingleChoiceMenu$2;->xb:Lcom/miui/internal/widget/DropDownSingleChoiceMenu;

    invoke-static {v0}, Lcom/miui/internal/widget/DropDownSingleChoiceMenu;->b(Lcom/miui/internal/widget/DropDownSingleChoiceMenu;)V

    .line 77
    return-void
.end method

.method public onShow()V
    .locals 1

    .prologue
    .line 69
    iget-object v0, p0, Lcom/miui/internal/widget/DropDownSingleChoiceMenu$2;->xb:Lcom/miui/internal/widget/DropDownSingleChoiceMenu;

    invoke-static {v0}, Lcom/miui/internal/widget/DropDownSingleChoiceMenu;->a(Lcom/miui/internal/widget/DropDownSingleChoiceMenu;)Lcom/miui/internal/widget/DropDownSingleChoiceMenu$OnMenuListener;

    move-result-object v0

    if-eqz v0, :cond_0

    .line 70
    iget-object v0, p0, Lcom/miui/internal/widget/DropDownSingleChoiceMenu$2;->xb:Lcom/miui/internal/widget/DropDownSingleChoiceMenu;

    invoke-static {v0}, Lcom/miui/internal/widget/DropDownSingleChoiceMenu;->a(Lcom/miui/internal/widget/DropDownSingleChoiceMenu;)Lcom/miui/internal/widget/DropDownSingleChoiceMenu$OnMenuListener;

    move-result-object v0

    invoke-interface {v0}, Lcom/miui/internal/widget/DropDownSingleChoiceMenu$OnMenuListener;->onShow()V

    .line 72
    :cond_0
    return-void
.end method
