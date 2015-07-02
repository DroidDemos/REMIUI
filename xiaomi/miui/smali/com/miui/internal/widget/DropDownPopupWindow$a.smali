.class Lcom/miui/internal/widget/DropDownPopupWindow$a;
.super Landroid/widget/FrameLayout;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/internal/widget/DropDownPopupWindow;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x2
    name = "a"
.end annotation


# instance fields
.field final synthetic pI:Lcom/miui/internal/widget/DropDownPopupWindow;


# direct methods
.method public constructor <init>(Lcom/miui/internal/widget/DropDownPopupWindow;Landroid/content/Context;Landroid/util/AttributeSet;I)V
    .locals 0

    .prologue
    .line 79
    iput-object p1, p0, Lcom/miui/internal/widget/DropDownPopupWindow$a;->pI:Lcom/miui/internal/widget/DropDownPopupWindow;

    .line 80
    invoke-direct {p0, p2, p3, p4}, Landroid/widget/FrameLayout;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    .line 81
    return-void
.end method


# virtual methods
.method public onKeyPreIme(ILandroid/view/KeyEvent;)Z
    .locals 2

    .prologue
    const/4 v0, 0x1

    .line 95
    const/4 v1, 0x4

    if-ne p1, v1, :cond_0

    .line 98
    invoke-virtual {p2}, Landroid/view/KeyEvent;->getAction()I

    move-result v1

    if-ne v1, v0, :cond_0

    .line 99
    iget-object v1, p0, Lcom/miui/internal/widget/DropDownPopupWindow$a;->pI:Lcom/miui/internal/widget/DropDownPopupWindow;

    invoke-virtual {v1}, Lcom/miui/internal/widget/DropDownPopupWindow;->dismiss()V

    .line 103
    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public onTouchEvent(Landroid/view/MotionEvent;)Z
    .locals 2

    .prologue
    const/4 v1, 0x1

    .line 85
    invoke-super {p0, p1}, Landroid/widget/FrameLayout;->onTouchEvent(Landroid/view/MotionEvent;)Z

    move-result v0

    if-nez v0, :cond_0

    .line 86
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getAction()I

    move-result v0

    if-ne v0, v1, :cond_0

    .line 87
    iget-object v0, p0, Lcom/miui/internal/widget/DropDownPopupWindow$a;->pI:Lcom/miui/internal/widget/DropDownPopupWindow;

    invoke-virtual {v0}, Lcom/miui/internal/widget/DropDownPopupWindow;->dismiss()V

    .line 90
    :cond_0
    return v1
.end method
