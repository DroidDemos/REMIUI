.class Lmiui/widget/ImmersionListPopupWindow$2;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lmiui/widget/ImmersionListPopupWindow;->p(Landroid/view/View;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic dv:Lmiui/widget/ImmersionListPopupWindow;

.field final synthetic yE:Lcom/miui/internal/app/IActivity;

.field final synthetic yF:I


# direct methods
.method constructor <init>(Lmiui/widget/ImmersionListPopupWindow;Lcom/miui/internal/app/IActivity;I)V
    .locals 0

    .prologue
    .line 180
    iput-object p1, p0, Lmiui/widget/ImmersionListPopupWindow$2;->dv:Lmiui/widget/ImmersionListPopupWindow;

    iput-object p2, p0, Lmiui/widget/ImmersionListPopupWindow$2;->yE:Lcom/miui/internal/app/IActivity;

    iput p3, p0, Lmiui/widget/ImmersionListPopupWindow$2;->yF:I

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .locals 2

    .prologue
    .line 183
    iget-object v0, p0, Lmiui/widget/ImmersionListPopupWindow$2;->yE:Lcom/miui/internal/app/IActivity;

    iget v1, p0, Lmiui/widget/ImmersionListPopupWindow$2;->yF:I

    invoke-interface {v0, v1}, Lcom/miui/internal/app/IActivity;->setTranslucentStatus(I)V

    .line 184
    return-void
.end method
