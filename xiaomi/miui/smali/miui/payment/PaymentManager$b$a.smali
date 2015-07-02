.class Lmiui/payment/PaymentManager$b$a;
.super Lmiui/payment/IPaymentManagerResponse$Stub;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/payment/PaymentManager$b;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = "a"
.end annotation


# instance fields
.field final synthetic gB:Lmiui/payment/PaymentManager$b;


# direct methods
.method constructor <init>(Lmiui/payment/PaymentManager$b;)V
    .locals 0

    .prologue
    .line 629
    iput-object p1, p0, Lmiui/payment/PaymentManager$b$a;->gB:Lmiui/payment/PaymentManager$b;

    invoke-direct {p0}, Lmiui/payment/IPaymentManagerResponse$Stub;-><init>()V

    return-void
.end method


# virtual methods
.method public onError(ILjava/lang/String;Landroid/os/Bundle;)V
    .locals 2
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Landroid/os/RemoteException;
        }
    .end annotation

    .prologue
    .line 649
    const/4 v0, 0x4

    if-ne p1, v0, :cond_0

    .line 651
    iget-object v0, p0, Lmiui/payment/PaymentManager$b$a;->gB:Lmiui/payment/PaymentManager$b;

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Lmiui/payment/PaymentManager$b;->cancel(Z)Z

    .line 652
    iget-object v0, p0, Lmiui/payment/PaymentManager$b$a;->gB:Lmiui/payment/PaymentManager$b;

    invoke-virtual {v0}, Lmiui/payment/PaymentManager$b;->K()V

    .line 656
    :goto_0
    return-void

    .line 655
    :cond_0
    iget-object v0, p0, Lmiui/payment/PaymentManager$b$a;->gB:Lmiui/payment/PaymentManager$b;

    iget-object v1, p0, Lmiui/payment/PaymentManager$b$a;->gB:Lmiui/payment/PaymentManager$b;

    invoke-static {v1, p1, p2, p3}, Lmiui/payment/PaymentManager$b;->a(Lmiui/payment/PaymentManager$b;ILjava/lang/String;Landroid/os/Bundle;)Ljava/lang/Exception;

    move-result-object v1

    invoke-virtual {v0, v1}, Lmiui/payment/PaymentManager$b;->setException(Ljava/lang/Throwable;)V

    goto :goto_0
.end method

.method public onResult(Landroid/os/Bundle;)V
    .locals 4
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Landroid/os/RemoteException;
        }
    .end annotation

    .prologue
    .line 633
    const-string v0, "intent"

    invoke-virtual {p1, v0}, Landroid/os/Bundle;->getParcelable(Ljava/lang/String;)Landroid/os/Parcelable;

    move-result-object v0

    check-cast v0, Landroid/content/Intent;

    .line 634
    if-eqz v0, :cond_1

    .line 635
    iget-object v1, p0, Lmiui/payment/PaymentManager$b$a;->gB:Lmiui/payment/PaymentManager$b;

    invoke-static {v1}, Lmiui/payment/PaymentManager$b;->a(Lmiui/payment/PaymentManager$b;)Landroid/app/Activity;

    move-result-object v1

    if-eqz v1, :cond_0

    .line 637
    iget-object v1, p0, Lmiui/payment/PaymentManager$b$a;->gB:Lmiui/payment/PaymentManager$b;

    invoke-static {v1}, Lmiui/payment/PaymentManager$b;->a(Lmiui/payment/PaymentManager$b;)Landroid/app/Activity;

    move-result-object v1

    invoke-virtual {v1, v0}, Landroid/app/Activity;->startActivity(Landroid/content/Intent;)V

    .line 645
    :goto_0
    return-void

    .line 640
    :cond_0
    iget-object v0, p0, Lmiui/payment/PaymentManager$b$a;->gB:Lmiui/payment/PaymentManager$b;

    new-instance v1, Lmiui/payment/exception/PaymentServiceFailureException;

    const/4 v2, 0x2

    const-string v3, "activity cannot be null"

    invoke-direct {v1, v2, v3}, Lmiui/payment/exception/PaymentServiceFailureException;-><init>(ILjava/lang/String;)V

    invoke-virtual {v0, v1}, Lmiui/payment/PaymentManager$b;->setException(Ljava/lang/Throwable;)V

    goto :goto_0

    .line 643
    :cond_1
    iget-object v0, p0, Lmiui/payment/PaymentManager$b$a;->gB:Lmiui/payment/PaymentManager$b;

    invoke-virtual {v0, p1}, Lmiui/payment/PaymentManager$b;->a(Landroid/os/Bundle;)V

    goto :goto_0
.end method
