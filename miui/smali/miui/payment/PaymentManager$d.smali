.class Lmiui/payment/PaymentManager$d;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lmiui/payment/PaymentManager$a;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/payment/PaymentManager;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x2
    name = "d"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Object;",
        "Lmiui/payment/PaymentManager$a",
        "<",
        "Landroid/os/Bundle;",
        ">;"
    }
.end annotation


# instance fields
.field private Qq:Ljava/lang/String;

.field private Qr:Ljava/lang/String;

.field private Qs:Lmiui/payment/PaymentManager$PaymentListener;

.field final synthetic dN:Lmiui/payment/PaymentManager;


# direct methods
.method public constructor <init>(Lmiui/payment/PaymentManager;Ljava/lang/String;Ljava/lang/String;Lmiui/payment/PaymentManager$PaymentListener;)V
    .locals 0

    .prologue
    .line 396
    iput-object p1, p0, Lmiui/payment/PaymentManager$d;->dN:Lmiui/payment/PaymentManager;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 397
    iput-object p2, p0, Lmiui/payment/PaymentManager$d;->Qq:Ljava/lang/String;

    .line 398
    iput-object p3, p0, Lmiui/payment/PaymentManager$d;->Qr:Ljava/lang/String;

    .line 399
    iput-object p4, p0, Lmiui/payment/PaymentManager$d;->Qs:Lmiui/payment/PaymentManager$PaymentListener;

    .line 400
    return-void
.end method


# virtual methods
.method public a(Lmiui/payment/PaymentManager$c;)V
    .locals 6
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lmiui/payment/PaymentManager$c",
            "<",
            "Landroid/os/Bundle;",
            ">;)V"
        }
    .end annotation

    .prologue
    const/4 v5, 0x0

    .line 404
    iget-object v0, p0, Lmiui/payment/PaymentManager$d;->Qs:Lmiui/payment/PaymentManager$PaymentListener;

    if-nez v0, :cond_0

    .line 425
    :goto_0
    return-void

    .line 408
    :cond_0
    :try_start_0
    invoke-interface {p1}, Lmiui/payment/PaymentManager$c;->getResult()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/os/Bundle;

    .line 409
    if-eqz v0, :cond_1

    .line 410
    iget-object v1, p0, Lmiui/payment/PaymentManager$d;->Qs:Lmiui/payment/PaymentManager$PaymentListener;

    iget-object v2, p0, Lmiui/payment/PaymentManager$d;->Qr:Ljava/lang/String;

    invoke-interface {v1, v2, v0}, Lmiui/payment/PaymentManager$PaymentListener;->onSuccess(Ljava/lang/String;Landroid/os/Bundle;)V
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_0
    .catch Lmiui/cloud/exception/OperationCancelledException; {:try_start_0 .. :try_end_0} :catch_1
    .catch Lmiui/cloud/exception/AuthenticationFailureException; {:try_start_0 .. :try_end_0} :catch_2
    .catch Lmiui/payment/exception/PaymentServiceFailureException; {:try_start_0 .. :try_end_0} :catch_3
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 423
    :goto_1
    iput-object v5, p0, Lmiui/payment/PaymentManager$d;->Qs:Lmiui/payment/PaymentManager$PaymentListener;

    goto :goto_0

    .line 412
    :cond_1
    :try_start_1
    iget-object v0, p0, Lmiui/payment/PaymentManager$d;->Qs:Lmiui/payment/PaymentManager$PaymentListener;

    iget-object v1, p0, Lmiui/payment/PaymentManager$d;->Qr:Ljava/lang/String;

    const/4 v2, 0x1

    const-string v3, "error"

    new-instance v4, Landroid/os/Bundle;

    invoke-direct {v4}, Landroid/os/Bundle;-><init>()V

    invoke-interface {v0, v1, v2, v3, v4}, Lmiui/payment/PaymentManager$PaymentListener;->onFailed(Ljava/lang/String;ILjava/lang/String;Landroid/os/Bundle;)V
    :try_end_1
    .catch Ljava/io/IOException; {:try_start_1 .. :try_end_1} :catch_0
    .catch Lmiui/cloud/exception/OperationCancelledException; {:try_start_1 .. :try_end_1} :catch_1
    .catch Lmiui/cloud/exception/AuthenticationFailureException; {:try_start_1 .. :try_end_1} :catch_2
    .catch Lmiui/payment/exception/PaymentServiceFailureException; {:try_start_1 .. :try_end_1} :catch_3
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    goto :goto_1

    .line 414
    :catch_0
    move-exception v0

    .line 415
    :try_start_2
    iget-object v1, p0, Lmiui/payment/PaymentManager$d;->Qs:Lmiui/payment/PaymentManager$PaymentListener;

    iget-object v2, p0, Lmiui/payment/PaymentManager$d;->Qr:Ljava/lang/String;

    const/4 v3, 0x3

    invoke-virtual {v0}, Ljava/io/IOException;->getMessage()Ljava/lang/String;

    move-result-object v0

    new-instance v4, Landroid/os/Bundle;

    invoke-direct {v4}, Landroid/os/Bundle;-><init>()V

    invoke-interface {v1, v2, v3, v0, v4}, Lmiui/payment/PaymentManager$PaymentListener;->onFailed(Ljava/lang/String;ILjava/lang/String;Landroid/os/Bundle;)V
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    .line 423
    iput-object v5, p0, Lmiui/payment/PaymentManager$d;->Qs:Lmiui/payment/PaymentManager$PaymentListener;

    goto :goto_0

    .line 416
    :catch_1
    move-exception v0

    .line 417
    :try_start_3
    iget-object v1, p0, Lmiui/payment/PaymentManager$d;->Qs:Lmiui/payment/PaymentManager$PaymentListener;

    iget-object v2, p0, Lmiui/payment/PaymentManager$d;->Qr:Ljava/lang/String;

    const/4 v3, 0x4

    invoke-virtual {v0}, Lmiui/cloud/exception/OperationCancelledException;->getMessage()Ljava/lang/String;

    move-result-object v0

    new-instance v4, Landroid/os/Bundle;

    invoke-direct {v4}, Landroid/os/Bundle;-><init>()V

    invoke-interface {v1, v2, v3, v0, v4}, Lmiui/payment/PaymentManager$PaymentListener;->onFailed(Ljava/lang/String;ILjava/lang/String;Landroid/os/Bundle;)V
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_0

    .line 423
    iput-object v5, p0, Lmiui/payment/PaymentManager$d;->Qs:Lmiui/payment/PaymentManager$PaymentListener;

    goto :goto_0

    .line 418
    :catch_2
    move-exception v0

    .line 419
    :try_start_4
    iget-object v1, p0, Lmiui/payment/PaymentManager$d;->Qs:Lmiui/payment/PaymentManager$PaymentListener;

    iget-object v2, p0, Lmiui/payment/PaymentManager$d;->Qr:Ljava/lang/String;

    const/4 v3, 0x5

    invoke-virtual {v0}, Lmiui/cloud/exception/AuthenticationFailureException;->getMessage()Ljava/lang/String;

    move-result-object v0

    new-instance v4, Landroid/os/Bundle;

    invoke-direct {v4}, Landroid/os/Bundle;-><init>()V

    invoke-interface {v1, v2, v3, v0, v4}, Lmiui/payment/PaymentManager$PaymentListener;->onFailed(Ljava/lang/String;ILjava/lang/String;Landroid/os/Bundle;)V
    :try_end_4
    .catchall {:try_start_4 .. :try_end_4} :catchall_0

    .line 423
    iput-object v5, p0, Lmiui/payment/PaymentManager$d;->Qs:Lmiui/payment/PaymentManager$PaymentListener;

    goto :goto_0

    .line 420
    :catch_3
    move-exception v0

    .line 421
    :try_start_5
    iget-object v1, p0, Lmiui/payment/PaymentManager$d;->Qs:Lmiui/payment/PaymentManager$PaymentListener;

    iget-object v2, p0, Lmiui/payment/PaymentManager$d;->Qr:Ljava/lang/String;

    invoke-virtual {v0}, Lmiui/payment/exception/PaymentServiceFailureException;->getError()I

    move-result v3

    invoke-virtual {v0}, Lmiui/payment/exception/PaymentServiceFailureException;->getMessage()Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v0}, Lmiui/payment/exception/PaymentServiceFailureException;->getErrorResult()Landroid/os/Bundle;

    move-result-object v0

    invoke-interface {v1, v2, v3, v4, v0}, Lmiui/payment/PaymentManager$PaymentListener;->onFailed(Ljava/lang/String;ILjava/lang/String;Landroid/os/Bundle;)V
    :try_end_5
    .catchall {:try_start_5 .. :try_end_5} :catchall_0

    .line 423
    iput-object v5, p0, Lmiui/payment/PaymentManager$d;->Qs:Lmiui/payment/PaymentManager$PaymentListener;

    goto :goto_0

    :catchall_0
    move-exception v0

    iput-object v5, p0, Lmiui/payment/PaymentManager$d;->Qs:Lmiui/payment/PaymentManager$PaymentListener;

    throw v0
.end method
