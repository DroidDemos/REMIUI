.class abstract Lmiui/payment/PaymentManager$b;
.super Ljava/util/concurrent/FutureTask;
.source "SourceFile"

# interfaces
.implements Landroid/content/ServiceConnection;
.implements Lmiui/payment/PaymentManager$c;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/payment/PaymentManager;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x402
    name = "b"
.end annotation

.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lmiui/payment/PaymentManager$b$a;
    }
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/util/concurrent/FutureTask",
        "<",
        "Landroid/os/Bundle;",
        ">;",
        "Lmiui/payment/PaymentManager$c",
        "<",
        "Landroid/os/Bundle;",
        ">;",
        "Landroid/content/ServiceConnection;"
    }
.end annotation


# instance fields
.field private dG:Z

.field private dH:Lmiui/payment/IPaymentManagerResponse;

.field private dI:Lmiui/payment/IPaymentManagerService;

.field private dJ:Lmiui/payment/PaymentManager$a;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lmiui/payment/PaymentManager$a",
            "<",
            "Landroid/os/Bundle;",
            ">;"
        }
    .end annotation
.end field

.field private dK:Landroid/app/Activity;

.field private final dL:I

.field private dM:Ljava/lang/Runnable;

.field final synthetic dN:Lmiui/payment/PaymentManager;

.field private mHandler:Landroid/os/Handler;


# direct methods
.method protected constructor <init>(Lmiui/payment/PaymentManager;Landroid/app/Activity;)V
    .locals 1

    .prologue
    .line 455
    const/4 v0, 0x0

    invoke-direct {p0, p1, p2, v0}, Lmiui/payment/PaymentManager$b;-><init>(Lmiui/payment/PaymentManager;Landroid/app/Activity;Lmiui/payment/PaymentManager$a;)V

    .line 456
    return-void
.end method

.method protected constructor <init>(Lmiui/payment/PaymentManager;Landroid/app/Activity;Landroid/os/Handler;Lmiui/payment/PaymentManager$a;)V
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Landroid/app/Activity;",
            "Landroid/os/Handler;",
            "Lmiui/payment/PaymentManager$a",
            "<",
            "Landroid/os/Bundle;",
            ">;)V"
        }
    .end annotation

    .prologue
    .line 462
    iput-object p1, p0, Lmiui/payment/PaymentManager$b;->dN:Lmiui/payment/PaymentManager;

    .line 463
    new-instance v0, Lmiui/payment/PaymentManager$b$2;

    invoke-direct {v0, p1}, Lmiui/payment/PaymentManager$b$2;-><init>(Lmiui/payment/PaymentManager;)V

    invoke-direct {p0, v0}, Ljava/util/concurrent/FutureTask;-><init>(Ljava/util/concurrent/Callable;)V

    .line 431
    const/4 v0, 0x0

    iput-boolean v0, p0, Lmiui/payment/PaymentManager$b;->dG:Z

    .line 438
    const/16 v0, 0x1388

    iput v0, p0, Lmiui/payment/PaymentManager$b;->dL:I

    .line 439
    new-instance v0, Lmiui/payment/a;

    invoke-direct {v0, p0}, Lmiui/payment/a;-><init>(Lmiui/payment/PaymentManager$b;)V

    iput-object v0, p0, Lmiui/payment/PaymentManager$b;->dM:Ljava/lang/Runnable;

    .line 471
    iput-object p2, p0, Lmiui/payment/PaymentManager$b;->dK:Landroid/app/Activity;

    .line 472
    iput-object p3, p0, Lmiui/payment/PaymentManager$b;->mHandler:Landroid/os/Handler;

    .line 473
    iput-object p4, p0, Lmiui/payment/PaymentManager$b;->dJ:Lmiui/payment/PaymentManager$a;

    .line 474
    new-instance v0, Lmiui/payment/PaymentManager$b$a;

    invoke-direct {v0, p0}, Lmiui/payment/PaymentManager$b$a;-><init>(Lmiui/payment/PaymentManager$b;)V

    iput-object v0, p0, Lmiui/payment/PaymentManager$b;->dH:Lmiui/payment/IPaymentManagerResponse;

    .line 475
    return-void
.end method

.method protected constructor <init>(Lmiui/payment/PaymentManager;Landroid/app/Activity;Lmiui/payment/PaymentManager$a;)V
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Landroid/app/Activity;",
            "Lmiui/payment/PaymentManager$a",
            "<",
            "Landroid/os/Bundle;",
            ">;)V"
        }
    .end annotation

    .prologue
    .line 459
    const/4 v0, 0x0

    invoke-direct {p0, p1, p2, v0, p3}, Lmiui/payment/PaymentManager$b;-><init>(Lmiui/payment/PaymentManager;Landroid/app/Activity;Landroid/os/Handler;Lmiui/payment/PaymentManager$a;)V

    .line 460
    return-void
.end method

.method private N()V
    .locals 3

    .prologue
    .line 675
    invoke-static {}, Landroid/os/Looper;->myLooper()Landroid/os/Looper;

    move-result-object v0

    .line 676
    if-eqz v0, :cond_0

    iget-object v1, p0, Lmiui/payment/PaymentManager$b;->dN:Lmiui/payment/PaymentManager;

    invoke-static {v1}, Lmiui/payment/PaymentManager;->b(Lmiui/payment/PaymentManager;)Landroid/content/Context;

    move-result-object v1

    invoke-virtual {v1}, Landroid/content/Context;->getMainLooper()Landroid/os/Looper;

    move-result-object v1

    if-ne v0, v1, :cond_0

    .line 677
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "calling this from your main thread can lead to deadlock"

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    .line 679
    const-string v1, "PaymentManager"

    const-string v2, "calling this from your main thread can lead to deadlock and/or ANRs"

    invoke-static {v1, v2, v0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    .line 681
    throw v0

    .line 683
    :cond_0
    return-void
.end method

.method static synthetic a(Lmiui/payment/PaymentManager$b;)Landroid/app/Activity;
    .locals 1

    .prologue
    .line 428
    iget-object v0, p0, Lmiui/payment/PaymentManager$b;->dK:Landroid/app/Activity;

    return-object v0
.end method

.method private a(Ljava/lang/Long;Ljava/util/concurrent/TimeUnit;)Landroid/os/Bundle;
    .locals 3
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;,
            Lmiui/cloud/exception/OperationCancelledException;,
            Lmiui/cloud/exception/AuthenticationFailureException;,
            Lmiui/payment/exception/PaymentServiceFailureException;
        }
    .end annotation

    .prologue
    const/4 v2, 0x1

    .line 525
    invoke-virtual {p0}, Lmiui/payment/PaymentManager$b;->isDone()Z

    move-result v0

    if-nez v0, :cond_0

    .line 526
    invoke-direct {p0}, Lmiui/payment/PaymentManager$b;->N()V

    .line 529
    :cond_0
    if-nez p1, :cond_1

    .line 530
    :try_start_0
    invoke-virtual {p0}, Lmiui/payment/PaymentManager$b;->get()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/os/Bundle;
    :try_end_0
    .catch Ljava/util/concurrent/CancellationException; {:try_start_0 .. :try_end_0} :catch_0
    .catch Ljava/util/concurrent/TimeoutException; {:try_start_0 .. :try_end_0} :catch_1
    .catch Ljava/lang/InterruptedException; {:try_start_0 .. :try_end_0} :catch_2
    .catch Ljava/util/concurrent/ExecutionException; {:try_start_0 .. :try_end_0} :catch_3
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 558
    invoke-virtual {p0, v2}, Lmiui/payment/PaymentManager$b;->cancel(Z)Z

    :goto_0
    return-object v0

    .line 532
    :cond_1
    :try_start_1
    invoke-virtual {p1}, Ljava/lang/Long;->longValue()J

    move-result-wide v0

    invoke-virtual {p0, v0, v1, p2}, Lmiui/payment/PaymentManager$b;->get(JLjava/util/concurrent/TimeUnit;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/os/Bundle;
    :try_end_1
    .catch Ljava/util/concurrent/CancellationException; {:try_start_1 .. :try_end_1} :catch_0
    .catch Ljava/util/concurrent/TimeoutException; {:try_start_1 .. :try_end_1} :catch_1
    .catch Ljava/lang/InterruptedException; {:try_start_1 .. :try_end_1} :catch_2
    .catch Ljava/util/concurrent/ExecutionException; {:try_start_1 .. :try_end_1} :catch_3
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 558
    invoke-virtual {p0, v2}, Lmiui/payment/PaymentManager$b;->cancel(Z)Z

    goto :goto_0

    .line 534
    :catch_0
    move-exception v0

    .line 535
    :try_start_2
    new-instance v0, Lmiui/cloud/exception/OperationCancelledException;

    const-string v1, "cancelled by user"

    invoke-direct {v0, v1}, Lmiui/cloud/exception/OperationCancelledException;-><init>(Ljava/lang/String;)V

    throw v0
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    .line 558
    :catchall_0
    move-exception v0

    invoke-virtual {p0, v2}, Lmiui/payment/PaymentManager$b;->cancel(Z)Z

    throw v0

    .line 536
    :catch_1
    move-exception v0

    .line 558
    invoke-virtual {p0, v2}, Lmiui/payment/PaymentManager$b;->cancel(Z)Z

    .line 560
    :goto_1
    new-instance v0, Lmiui/cloud/exception/OperationCancelledException;

    const-string v1, "cancelled by exception"

    invoke-direct {v0, v1}, Lmiui/cloud/exception/OperationCancelledException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 538
    :catch_2
    move-exception v0

    .line 558
    invoke-virtual {p0, v2}, Lmiui/payment/PaymentManager$b;->cancel(Z)Z

    goto :goto_1

    .line 540
    :catch_3
    move-exception v0

    .line 541
    :try_start_3
    invoke-virtual {v0}, Ljava/util/concurrent/ExecutionException;->getCause()Ljava/lang/Throwable;

    move-result-object v0

    .line 542
    instance-of v1, v0, Ljava/io/IOException;

    if-eqz v1, :cond_2

    .line 543
    check-cast v0, Ljava/io/IOException;

    throw v0

    .line 544
    :cond_2
    instance-of v1, v0, Lmiui/payment/exception/PaymentServiceFailureException;

    if-eqz v1, :cond_3

    .line 545
    check-cast v0, Lmiui/payment/exception/PaymentServiceFailureException;

    throw v0

    .line 546
    :cond_3
    instance-of v1, v0, Lmiui/cloud/exception/AuthenticationFailureException;

    if-eqz v1, :cond_4

    .line 547
    check-cast v0, Lmiui/cloud/exception/AuthenticationFailureException;

    throw v0

    .line 548
    :cond_4
    instance-of v1, v0, Lmiui/cloud/exception/OperationCancelledException;

    if-eqz v1, :cond_5

    .line 549
    check-cast v0, Lmiui/cloud/exception/OperationCancelledException;

    throw v0

    .line 550
    :cond_5
    instance-of v1, v0, Ljava/lang/RuntimeException;

    if-eqz v1, :cond_6

    .line 551
    check-cast v0, Ljava/lang/RuntimeException;

    throw v0

    .line 552
    :cond_6
    instance-of v1, v0, Ljava/lang/Error;

    if-eqz v1, :cond_7

    .line 553
    check-cast v0, Ljava/lang/Error;

    throw v0

    .line 555
    :cond_7
    new-instance v1, Ljava/lang/IllegalStateException;

    invoke-direct {v1, v0}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/Throwable;)V

    throw v1
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_0
.end method

.method private a(ILjava/lang/String;Landroid/os/Bundle;)Ljava/lang/Exception;
    .locals 1

    .prologue
    .line 660
    const/4 v0, 0x3

    if-ne p1, v0, :cond_0

    .line 661
    new-instance v0, Ljava/io/IOException;

    invoke-direct {v0, p2}, Ljava/io/IOException;-><init>(Ljava/lang/String;)V

    .line 671
    :goto_0
    return-object v0

    .line 664
    :cond_0
    const/4 v0, 0x5

    if-ne p1, v0, :cond_1

    .line 665
    new-instance v0, Lmiui/cloud/exception/AuthenticationFailureException;

    invoke-direct {v0, p2}, Lmiui/cloud/exception/AuthenticationFailureException;-><init>(Ljava/lang/String;)V

    goto :goto_0

    .line 668
    :cond_1
    invoke-static {p2}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_2

    .line 669
    const-string p2, "Unknown payment failure"

    .line 671
    :cond_2
    new-instance v0, Lmiui/payment/exception/PaymentServiceFailureException;

    invoke-direct {v0, p1, p2, p3}, Lmiui/payment/exception/PaymentServiceFailureException;-><init>(ILjava/lang/String;Landroid/os/Bundle;)V

    goto :goto_0
.end method

.method static synthetic a(Lmiui/payment/PaymentManager$b;ILjava/lang/String;Landroid/os/Bundle;)Ljava/lang/Exception;
    .locals 1

    .prologue
    .line 428
    invoke-direct {p0, p1, p2, p3}, Lmiui/payment/PaymentManager$b;->a(ILjava/lang/String;Landroid/os/Bundle;)Ljava/lang/Exception;

    move-result-object v0

    return-object v0
.end method

.method static synthetic a(Lmiui/payment/PaymentManager$b;Lmiui/payment/PaymentManager$a;)Lmiui/payment/PaymentManager$a;
    .locals 0

    .prologue
    .line 428
    iput-object p1, p0, Lmiui/payment/PaymentManager$b;->dJ:Lmiui/payment/PaymentManager$a;

    return-object p1
.end method

.method static synthetic b(Lmiui/payment/PaymentManager$b;)Lmiui/payment/PaymentManager$a;
    .locals 1

    .prologue
    .line 428
    iget-object v0, p0, Lmiui/payment/PaymentManager$b;->dJ:Lmiui/payment/PaymentManager$a;

    return-object v0
.end method


# virtual methods
.method protected F()Lmiui/payment/IPaymentManagerResponse;
    .locals 1

    .prologue
    .line 478
    iget-object v0, p0, Lmiui/payment/PaymentManager$b;->dH:Lmiui/payment/IPaymentManagerResponse;

    return-object v0
.end method

.method protected G()Lmiui/payment/IPaymentManagerService;
    .locals 1

    .prologue
    .line 482
    iget-object v0, p0, Lmiui/payment/PaymentManager$b;->dI:Lmiui/payment/IPaymentManagerService;

    return-object v0
.end method

.method protected abstract H()V
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Landroid/os/RemoteException;
        }
    .end annotation
.end method

.method public final I()Lmiui/payment/PaymentManager$c;
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Lmiui/payment/PaymentManager$c",
            "<",
            "Landroid/os/Bundle;",
            ">;"
        }
    .end annotation

    .prologue
    .line 488
    invoke-virtual {p0}, Lmiui/payment/PaymentManager$b;->J()V

    .line 489
    return-object p0
.end method

.method protected J()V
    .locals 3

    .prologue
    const/4 v2, 0x1

    .line 493
    iget-boolean v0, p0, Lmiui/payment/PaymentManager$b;->dG:Z

    if-nez v0, :cond_0

    .line 494
    invoke-virtual {p0}, Lmiui/payment/PaymentManager$b;->L()Z

    move-result v0

    if-nez v0, :cond_1

    .line 495
    new-instance v0, Lmiui/payment/exception/PaymentServiceFailureException;

    const-string v1, "bind to service failed"

    invoke-direct {v0, v2, v1}, Lmiui/payment/exception/PaymentServiceFailureException;-><init>(ILjava/lang/String;)V

    invoke-virtual {p0, v0}, Lmiui/payment/PaymentManager$b;->setException(Ljava/lang/Throwable;)V

    .line 503
    :cond_0
    :goto_0
    return-void

    .line 497
    :cond_1
    iput-boolean v2, p0, Lmiui/payment/PaymentManager$b;->dG:Z

    .line 499
    const-string v0, "PaymentManager"

    const-string v1, "service bound"

    invoke-static {v0, v1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    goto :goto_0
.end method

.method protected K()V
    .locals 2

    .prologue
    .line 506
    iget-boolean v0, p0, Lmiui/payment/PaymentManager$b;->dG:Z

    if-nez v0, :cond_0

    .line 514
    :goto_0
    return-void

    .line 509
    :cond_0
    iget-object v0, p0, Lmiui/payment/PaymentManager$b;->dN:Lmiui/payment/PaymentManager;

    invoke-static {v0}, Lmiui/payment/PaymentManager;->b(Lmiui/payment/PaymentManager;)Landroid/content/Context;

    move-result-object v0

    invoke-virtual {v0, p0}, Landroid/content/Context;->unbindService(Landroid/content/ServiceConnection;)V

    .line 510
    const/4 v0, 0x0

    iput-boolean v0, p0, Lmiui/payment/PaymentManager$b;->dG:Z

    .line 512
    const-string v0, "PaymentManager"

    const-string v1, "service unbinded"

    invoke-static {v0, v1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    goto :goto_0
.end method

.method protected L()Z
    .locals 3

    .prologue
    .line 517
    new-instance v0, Landroid/content/Intent;

    const-string v1, "com.xiaomi.xmsf.action.PAYMENT"

    invoke-direct {v0, v1}, Landroid/content/Intent;-><init>(Ljava/lang/String;)V

    .line 518
    const-string v1, "com.xiaomi.payment"

    invoke-virtual {v0, v1}, Landroid/content/Intent;->setPackage(Ljava/lang/String;)Landroid/content/Intent;

    .line 519
    iget-object v1, p0, Lmiui/payment/PaymentManager$b;->dN:Lmiui/payment/PaymentManager;

    invoke-static {v1}, Lmiui/payment/PaymentManager;->b(Lmiui/payment/PaymentManager;)Landroid/content/Context;

    move-result-object v1

    const/4 v2, 0x1

    invoke-virtual {v1, v0, p0, v2}, Landroid/content/Context;->bindService(Landroid/content/Intent;Landroid/content/ServiceConnection;I)Z

    move-result v0

    return v0
.end method

.method public M()Landroid/os/Bundle;
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;,
            Lmiui/cloud/exception/OperationCancelledException;,
            Lmiui/cloud/exception/AuthenticationFailureException;,
            Lmiui/payment/exception/PaymentServiceFailureException;
        }
    .end annotation

    .prologue
    const/4 v0, 0x0

    .line 586
    invoke-direct {p0, v0, v0}, Lmiui/payment/PaymentManager$b;->a(Ljava/lang/Long;Ljava/util/concurrent/TimeUnit;)Landroid/os/Bundle;

    move-result-object v0

    return-object v0
.end method

.method public a(JLjava/util/concurrent/TimeUnit;)Landroid/os/Bundle;
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;,
            Lmiui/cloud/exception/OperationCancelledException;,
            Lmiui/cloud/exception/AuthenticationFailureException;,
            Lmiui/payment/exception/PaymentServiceFailureException;
        }
    .end annotation

    .prologue
    .line 579
    invoke-static {p1, p2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v0

    invoke-direct {p0, v0, p3}, Lmiui/payment/PaymentManager$b;->a(Ljava/lang/Long;Ljava/util/concurrent/TimeUnit;)Landroid/os/Bundle;

    move-result-object v0

    return-object v0
.end method

.method protected a(Landroid/os/Bundle;)V
    .locals 0

    .prologue
    .line 565
    invoke-super {p0, p1}, Ljava/util/concurrent/FutureTask;->set(Ljava/lang/Object;)V

    .line 566
    invoke-virtual {p0}, Lmiui/payment/PaymentManager$b;->K()V

    .line 567
    return-void
.end method

.method protected done()V
    .locals 3

    .prologue
    const/4 v2, 0x0

    .line 590
    iget-object v0, p0, Lmiui/payment/PaymentManager$b;->dJ:Lmiui/payment/PaymentManager$a;

    if-eqz v0, :cond_0

    .line 591
    iget-object v0, p0, Lmiui/payment/PaymentManager$b;->mHandler:Landroid/os/Handler;

    if-nez v0, :cond_1

    iget-object v0, p0, Lmiui/payment/PaymentManager$b;->dN:Lmiui/payment/PaymentManager;

    invoke-static {v0}, Lmiui/payment/PaymentManager;->a(Lmiui/payment/PaymentManager;)Landroid/os/Handler;

    move-result-object v0

    .line 592
    :goto_0
    new-instance v1, Lmiui/payment/PaymentManager$b$1;

    invoke-direct {v1, p0}, Lmiui/payment/PaymentManager$b$1;-><init>(Lmiui/payment/PaymentManager$b;)V

    invoke-virtual {v0, v1}, Landroid/os/Handler;->post(Ljava/lang/Runnable;)Z

    .line 600
    :cond_0
    iget-object v0, p0, Lmiui/payment/PaymentManager$b;->dN:Lmiui/payment/PaymentManager;

    invoke-static {v0}, Lmiui/payment/PaymentManager;->a(Lmiui/payment/PaymentManager;)Landroid/os/Handler;

    move-result-object v0

    iget-object v1, p0, Lmiui/payment/PaymentManager$b;->dM:Ljava/lang/Runnable;

    invoke-virtual {v0, v1}, Landroid/os/Handler;->removeCallbacks(Ljava/lang/Runnable;)V

    .line 602
    iput-object v2, p0, Lmiui/payment/PaymentManager$b;->mHandler:Landroid/os/Handler;

    .line 603
    iput-object v2, p0, Lmiui/payment/PaymentManager$b;->dK:Landroid/app/Activity;

    .line 604
    return-void

    .line 591
    :cond_1
    iget-object v0, p0, Lmiui/payment/PaymentManager$b;->mHandler:Landroid/os/Handler;

    goto :goto_0
.end method

.method public bridge synthetic getResult()Ljava/lang/Object;
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;,
            Lmiui/cloud/exception/OperationCancelledException;,
            Lmiui/cloud/exception/AuthenticationFailureException;,
            Lmiui/payment/exception/PaymentServiceFailureException;
        }
    .end annotation

    .prologue
    .line 428
    invoke-virtual {p0}, Lmiui/payment/PaymentManager$b;->M()Landroid/os/Bundle;

    move-result-object v0

    return-object v0
.end method

.method public bridge synthetic getResult(JLjava/util/concurrent/TimeUnit;)Ljava/lang/Object;
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;,
            Lmiui/cloud/exception/OperationCancelledException;,
            Lmiui/cloud/exception/AuthenticationFailureException;,
            Lmiui/payment/exception/PaymentServiceFailureException;
        }
    .end annotation

    .prologue
    .line 428
    invoke-virtual {p0, p1, p2, p3}, Lmiui/payment/PaymentManager$b;->a(JLjava/util/concurrent/TimeUnit;)Landroid/os/Bundle;

    move-result-object v0

    return-object v0
.end method

.method public onServiceConnected(Landroid/content/ComponentName;Landroid/os/IBinder;)V
    .locals 4

    .prologue
    .line 609
    const-string v0, "PaymentManager"

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "onServiceConnected, component:"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 611
    invoke-static {p2}, Lmiui/payment/IPaymentManagerService$Stub;->asInterface(Landroid/os/IBinder;)Lmiui/payment/IPaymentManagerService;

    move-result-object v0

    iput-object v0, p0, Lmiui/payment/PaymentManager$b;->dI:Lmiui/payment/IPaymentManagerService;

    .line 613
    :try_start_0
    invoke-virtual {p0}, Lmiui/payment/PaymentManager$b;->H()V

    .line 614
    iget-object v0, p0, Lmiui/payment/PaymentManager$b;->dN:Lmiui/payment/PaymentManager;

    invoke-static {v0}, Lmiui/payment/PaymentManager;->a(Lmiui/payment/PaymentManager;)Landroid/os/Handler;

    move-result-object v0

    iget-object v1, p0, Lmiui/payment/PaymentManager$b;->dM:Ljava/lang/Runnable;

    const-wide/16 v2, 0x1388

    invoke-virtual {v0, v1, v2, v3}, Landroid/os/Handler;->postDelayed(Ljava/lang/Runnable;J)Z
    :try_end_0
    .catch Landroid/os/RemoteException; {:try_start_0 .. :try_end_0} :catch_0

    .line 618
    :goto_0
    return-void

    .line 615
    :catch_0
    move-exception v0

    .line 616
    invoke-virtual {p0, v0}, Lmiui/payment/PaymentManager$b;->setException(Ljava/lang/Throwable;)V

    goto :goto_0
.end method

.method public onServiceDisconnected(Landroid/content/ComponentName;)V
    .locals 3

    .prologue
    .line 622
    invoke-virtual {p0}, Lmiui/payment/PaymentManager$b;->isDone()Z

    move-result v0

    if-nez v0, :cond_0

    .line 623
    const-string v0, "PaymentManager"

    const-string v1, "payment service disconnected, but task is not completed"

    invoke-static {v0, v1}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 624
    new-instance v0, Lmiui/payment/exception/PaymentServiceFailureException;

    const/4 v1, 0x1

    const-string v2, "active service exits unexpectedly"

    invoke-direct {v0, v1, v2}, Lmiui/payment/exception/PaymentServiceFailureException;-><init>(ILjava/lang/String;)V

    invoke-virtual {p0, v0}, Lmiui/payment/PaymentManager$b;->setException(Ljava/lang/Throwable;)V

    .line 626
    :cond_0
    const/4 v0, 0x0

    iput-object v0, p0, Lmiui/payment/PaymentManager$b;->dI:Lmiui/payment/IPaymentManagerService;

    .line 627
    return-void
.end method

.method protected bridge synthetic set(Ljava/lang/Object;)V
    .locals 0

    .prologue
    .line 428
    check-cast p1, Landroid/os/Bundle;

    invoke-virtual {p0, p1}, Lmiui/payment/PaymentManager$b;->a(Landroid/os/Bundle;)V

    return-void
.end method

.method protected setException(Ljava/lang/Throwable;)V
    .locals 0

    .prologue
    .line 571
    invoke-super {p0, p1}, Ljava/util/concurrent/FutureTask;->setException(Ljava/lang/Throwable;)V

    .line 572
    invoke-virtual {p0}, Lmiui/payment/PaymentManager$b;->K()V

    .line 573
    return-void
.end method
