.class Lmiui/payment/PaymentManager$3;
.super Lmiui/payment/PaymentManager$b;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lmiui/payment/PaymentManager;->a(Landroid/app/Activity;Ljava/lang/String;Landroid/os/Bundle;Lmiui/payment/PaymentManager$a;)Lmiui/payment/PaymentManager$c;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic BU:Landroid/os/Bundle;

.field final synthetic Qd:Ljava/lang/String;

.field final synthetic dN:Lmiui/payment/PaymentManager;


# direct methods
.method constructor <init>(Lmiui/payment/PaymentManager;Landroid/app/Activity;Lmiui/payment/PaymentManager$a;Landroid/os/Bundle;Ljava/lang/String;)V
    .locals 0

    .prologue
    .line 299
    iput-object p1, p0, Lmiui/payment/PaymentManager$3;->dN:Lmiui/payment/PaymentManager;

    iput-object p4, p0, Lmiui/payment/PaymentManager$3;->BU:Landroid/os/Bundle;

    iput-object p5, p0, Lmiui/payment/PaymentManager$3;->Qd:Ljava/lang/String;

    invoke-direct {p0, p1, p2, p3}, Lmiui/payment/PaymentManager$b;-><init>(Lmiui/payment/PaymentManager;Landroid/app/Activity;Lmiui/payment/PaymentManager$a;)V

    return-void
.end method


# virtual methods
.method protected H()V
    .locals 5
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Landroid/os/RemoteException;
        }
    .end annotation

    .prologue
    .line 302
    invoke-virtual {p0}, Lmiui/payment/PaymentManager$3;->G()Lmiui/payment/IPaymentManagerService;

    move-result-object v0

    .line 303
    new-instance v1, Landroid/os/Bundle;

    invoke-direct {v1}, Landroid/os/Bundle;-><init>()V

    .line 304
    iget-object v2, p0, Lmiui/payment/PaymentManager$3;->BU:Landroid/os/Bundle;

    if-eqz v2, :cond_0

    .line 305
    iget-object v2, p0, Lmiui/payment/PaymentManager$3;->BU:Landroid/os/Bundle;

    invoke-virtual {v1, v2}, Landroid/os/Bundle;->putAll(Landroid/os/Bundle;)V

    .line 307
    :cond_0
    invoke-virtual {p0}, Lmiui/payment/PaymentManager$3;->F()Lmiui/payment/IPaymentManagerResponse;

    move-result-object v2

    const/4 v3, 0x0

    iget-object v4, p0, Lmiui/payment/PaymentManager$3;->Qd:Ljava/lang/String;

    invoke-interface {v0, v2, v3, v4, v1}, Lmiui/payment/IPaymentManagerService;->payForOrder(Lmiui/payment/IPaymentManagerResponse;Landroid/accounts/Account;Ljava/lang/String;Landroid/os/Bundle;)V

    .line 308
    return-void
.end method
