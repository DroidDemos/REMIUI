.class Lmiui/payment/PaymentManager$6;
.super Lmiui/payment/PaymentManager$b;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lmiui/payment/PaymentManager;->a(Landroid/app/Activity;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic dN:Lmiui/payment/PaymentManager;


# direct methods
.method constructor <init>(Lmiui/payment/PaymentManager;Landroid/app/Activity;)V
    .locals 0

    .prologue
    .line 335
    iput-object p1, p0, Lmiui/payment/PaymentManager$6;->dN:Lmiui/payment/PaymentManager;

    invoke-direct {p0, p1, p2}, Lmiui/payment/PaymentManager$b;-><init>(Lmiui/payment/PaymentManager;Landroid/app/Activity;)V

    return-void
.end method


# virtual methods
.method protected H()V
    .locals 3
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Landroid/os/RemoteException;
        }
    .end annotation

    .prologue
    .line 338
    invoke-virtual {p0}, Lmiui/payment/PaymentManager$6;->G()Lmiui/payment/IPaymentManagerService;

    move-result-object v0

    .line 339
    invoke-virtual {p0}, Lmiui/payment/PaymentManager$6;->F()Lmiui/payment/IPaymentManagerResponse;

    move-result-object v1

    const/4 v2, 0x0

    invoke-interface {v0, v1, v2}, Lmiui/payment/IPaymentManagerService;->showMiliCenter(Lmiui/payment/IPaymentManagerResponse;Landroid/accounts/Account;)V

    .line 340
    return-void
.end method
