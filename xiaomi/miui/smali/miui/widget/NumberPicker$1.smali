.class Lmiui/widget/NumberPicker$1;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroid/view/View$OnFocusChangeListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lmiui/widget/NumberPicker;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic aC:Lmiui/widget/NumberPicker;


# direct methods
.method constructor <init>(Lmiui/widget/NumberPicker;)V
    .locals 0

    .prologue
    .line 585
    iput-object p1, p0, Lmiui/widget/NumberPicker$1;->aC:Lmiui/widget/NumberPicker;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onFocusChange(Landroid/view/View;Z)V
    .locals 2

    .prologue
    const/4 v1, 0x0

    .line 587
    if-eqz p2, :cond_0

    .line 588
    iget-object v0, p0, Lmiui/widget/NumberPicker$1;->aC:Lmiui/widget/NumberPicker;

    invoke-static {v0}, Lmiui/widget/NumberPicker;->a(Lmiui/widget/NumberPicker;)Landroid/widget/EditText;

    move-result-object v0

    invoke-virtual {v0}, Landroid/widget/EditText;->selectAll()V

    .line 593
    :goto_0
    return-void

    .line 590
    :cond_0
    iget-object v0, p0, Lmiui/widget/NumberPicker$1;->aC:Lmiui/widget/NumberPicker;

    invoke-static {v0}, Lmiui/widget/NumberPicker;->a(Lmiui/widget/NumberPicker;)Landroid/widget/EditText;

    move-result-object v0

    invoke-virtual {v0, v1, v1}, Landroid/widget/EditText;->setSelection(II)V

    .line 591
    iget-object v0, p0, Lmiui/widget/NumberPicker$1;->aC:Lmiui/widget/NumberPicker;

    invoke-static {v0, p1}, Lmiui/widget/NumberPicker;->a(Lmiui/widget/NumberPicker;Landroid/view/View;)V

    goto :goto_0
.end method
