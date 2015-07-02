.class Lmiui/widget/h$1;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lmiui/widget/h;->onAnimationEnd(Landroid/animation/Animator;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic iI:Z

.field final synthetic iJ:Lmiui/widget/h;


# direct methods
.method constructor <init>(Lmiui/widget/h;Z)V
    .locals 0

    .prologue
    .line 118
    iput-object p1, p0, Lmiui/widget/h$1;->iJ:Lmiui/widget/h;

    iput-boolean p2, p0, Lmiui/widget/h$1;->iI:Z

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .locals 3

    .prologue
    .line 121
    iget-object v0, p0, Lmiui/widget/h$1;->iJ:Lmiui/widget/h;

    iget-object v0, v0, Lmiui/widget/h;->OB:Lmiui/widget/SlidingButton;

    invoke-static {v0}, Lmiui/widget/SlidingButton;->c(Lmiui/widget/SlidingButton;)Landroid/widget/CompoundButton$OnCheckedChangeListener;

    move-result-object v0

    if-eqz v0, :cond_0

    .line 122
    iget-object v0, p0, Lmiui/widget/h$1;->iJ:Lmiui/widget/h;

    iget-object v0, v0, Lmiui/widget/h;->OB:Lmiui/widget/SlidingButton;

    invoke-static {v0}, Lmiui/widget/SlidingButton;->c(Lmiui/widget/SlidingButton;)Landroid/widget/CompoundButton$OnCheckedChangeListener;

    move-result-object v0

    iget-object v1, p0, Lmiui/widget/h$1;->iJ:Lmiui/widget/h;

    iget-object v1, v1, Lmiui/widget/h;->OB:Lmiui/widget/SlidingButton;

    iget-boolean v2, p0, Lmiui/widget/h$1;->iI:Z

    invoke-interface {v0, v1, v2}, Landroid/widget/CompoundButton$OnCheckedChangeListener;->onCheckedChanged(Landroid/widget/CompoundButton;Z)V

    .line 124
    :cond_0
    return-void
.end method
