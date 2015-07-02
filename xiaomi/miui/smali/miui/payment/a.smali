.class Lmiui/payment/a;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/payment/PaymentManager$b;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic gB:Lmiui/payment/PaymentManager$b;


# direct methods
.method constructor <init>(Lmiui/payment/PaymentManager$b;)V
    .locals 0

    .prologue
    .line 439
    iput-object p1, p0, Lmiui/payment/a;->gB:Lmiui/payment/PaymentManager$b;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .locals 3

    .prologue
    .line 443
    iget-object v0, p0, Lmiui/payment/a;->gB:Lmiui/payment/PaymentManager$b;

    invoke-static {v0}, Lmiui/payment/PaymentManager$b;->a(Lmiui/payment/PaymentManager$b;)Landroid/app/Activity;

    move-result-object v0

    .line 444
    iget-object v1, p0, Lmiui/payment/a;->gB:Lmiui/payment/PaymentManager$b;

    invoke-virtual {v1}, Lmiui/payment/PaymentManager$b;->isDone()Z

    move-result v1

    if-nez v1, :cond_0

    if-eqz v0, :cond_0

    .line 445
    invoke-virtual {v0}, Landroid/app/Activity;->isFinishing()Z

    move-result v0

    if-eqz v0, :cond_1

    .line 446
    iget-object v0, p0, Lmiui/payment/a;->gB:Lmiui/payment/PaymentManager$b;

    new-instance v1, Lmiui/cloud/exception/OperationCancelledException;

    const-string v2, "Operation has been cancelled because host activity has finished."

    invoke-direct {v1, v2}, Lmiui/cloud/exception/OperationCancelledException;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Lmiui/payment/PaymentManager$b;->setException(Ljava/lang/Throwable;)V

    .line 451
    :cond_0
    :goto_0
    return-void

    .line 448
    :cond_1
    iget-object v0, p0, Lmiui/payment/a;->gB:Lmiui/payment/PaymentManager$b;

    iget-object v0, v0, Lmiui/payment/PaymentManager$b;->dN:Lmiui/payment/PaymentManager;

    invoke-static {v0}, Lmiui/payment/PaymentManager;->a(Lmiui/payment/PaymentManager;)Landroid/os/Handler;

    move-result-object v0

    const-wide/16 v1, 0x1388

    invoke-virtual {v0, p0, v1, v2}, Landroid/os/Handler;->postDelayed(Ljava/lang/Runnable;J)Z

    goto :goto_0
.end method
