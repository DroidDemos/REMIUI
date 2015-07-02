.class Lmiui/payment/PaymentManager$b$1;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lmiui/payment/PaymentManager$b;->done()V
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
    .line 592
    iput-object p1, p0, Lmiui/payment/PaymentManager$b$1;->gB:Lmiui/payment/PaymentManager$b;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .locals 2

    .prologue
    .line 594
    iget-object v0, p0, Lmiui/payment/PaymentManager$b$1;->gB:Lmiui/payment/PaymentManager$b;

    invoke-static {v0}, Lmiui/payment/PaymentManager$b;->b(Lmiui/payment/PaymentManager$b;)Lmiui/payment/PaymentManager$a;

    move-result-object v0

    iget-object v1, p0, Lmiui/payment/PaymentManager$b$1;->gB:Lmiui/payment/PaymentManager$b;

    invoke-interface {v0, v1}, Lmiui/payment/PaymentManager$a;->a(Lmiui/payment/PaymentManager$c;)V

    .line 596
    iget-object v0, p0, Lmiui/payment/PaymentManager$b$1;->gB:Lmiui/payment/PaymentManager$b;

    const/4 v1, 0x0

    invoke-static {v0, v1}, Lmiui/payment/PaymentManager$b;->a(Lmiui/payment/PaymentManager$b;Lmiui/payment/PaymentManager$a;)Lmiui/payment/PaymentManager$a;

    .line 597
    return-void
.end method
