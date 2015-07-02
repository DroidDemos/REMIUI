.class Lmiui/widget/ListPopupWindow$1;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroid/widget/AdapterView$OnItemSelectedListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lmiui/widget/ListPopupWindow;->eY()I
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic D:Lmiui/widget/ListPopupWindow;


# direct methods
.method constructor <init>(Lmiui/widget/ListPopupWindow;)V
    .locals 0

    .prologue
    .line 929
    iput-object p1, p0, Lmiui/widget/ListPopupWindow$1;->D:Lmiui/widget/ListPopupWindow;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onItemSelected(Landroid/widget/AdapterView;Landroid/view/View;IJ)V
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
    .line 933
    const/4 v0, -0x1

    if-eq p3, v0, :cond_0

    .line 934
    iget-object v0, p0, Lmiui/widget/ListPopupWindow$1;->D:Lmiui/widget/ListPopupWindow;

    invoke-static {v0}, Lmiui/widget/ListPopupWindow;->a(Lmiui/widget/ListPopupWindow;)Lmiui/widget/ListPopupWindow$e;

    move-result-object v0

    .line 936
    if-eqz v0, :cond_0

    .line 937
    const/4 v1, 0x0

    invoke-static {v0, v1}, Lmiui/widget/ListPopupWindow$e;->a(Lmiui/widget/ListPopupWindow$e;Z)Z

    .line 940
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
    .line 943
    return-void
.end method
