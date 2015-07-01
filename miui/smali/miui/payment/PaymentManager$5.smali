.class Lmiui/payment/PaymentManager$5;
.super Lmiui/payment/PaymentManager$b;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lmiui/payment/PaymentManager;->a(Landroid/app/Activity;Ljava/lang/String;Ljava/lang/String;Lmiui/payment/PaymentManager$a;)Lmiui/payment/PaymentManager$c;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic Qb:Ljava/lang/String;

.field final synthetic Qc:Ljava/lang/String;

.field final synthetic dN:Lmiui/payment/PaymentManager;


# direct methods
.method constructor <init>(Lmiui/payment/PaymentManager;Landroid/app/Activity;Lmiui/payment/PaymentManager$a;Ljava/lang/String;Ljava/lang/String;)V
    .locals 0

    .prologue
    .line 325
    iput-object p1, p0, Lmiui/payment/PaymentManager$5;->dN:Lmiui/payment/PaymentManager;

    iput-object p4, p0, Lmiui/payment/PaymentManager$5;->Qb:Ljava/lang/String;

    iput-object p5, p0, Lmiui/payment/PaymentManager$5;->Qc:Ljava/lang/String;

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
    .line 328
    invoke-virtual {p0}, Lmiui/payment/PaymentManager$5;->G()Lmiui/payment/IPaymentManagerService;

    move-result-object v0

    .line 329
    invoke-virtual {p0}, Lmiui/payment/PaymentManager$5;->F()Lmiui/payment/IPaymentManagerResponse;

    move-result-object v1

    const/4 v2, 0x0

    iget-object v3, p0, Lmiui/payment/PaymentManager$5;->Qb:Ljava/lang/String;

    iget-object v4, p0, Lmiui/payment/PaymentManager$5;->Qc:Ljava/lang/String;

    invoke-interface {v0, v1, v2, v3, v4}, Lmiui/payment/IPaymentManagerService;->getMiliBalance(Lmiui/payment/IPaymentManagerResponse;Landroid/accounts/Account;Ljava/lang/String;Ljava/lang/String;)V

    .line 330
    return-void
.end method
