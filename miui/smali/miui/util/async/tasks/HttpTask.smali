.class public abstract Lmiui/util/async/tasks/HttpTask;
.super Lmiui/util/async/Task;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lmiui/util/async/tasks/HttpTask$1;,
        Lmiui/util/async/tasks/HttpTask$Method;
    }
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "<T:",
        "Ljava/lang/Object;",
        ">",
        "Lmiui/util/async/Task",
        "<TT;>;"
    }
.end annotation


# instance fields
.field private final Pg:Lmiui/net/http/HttpSession;

.field private final Ph:Lmiui/util/async/tasks/HttpTask$Method;

.field private final Pi:Lmiui/net/http/HttpRequestParams;

.field private final gf:Ljava/lang/String;

.field private t:Lmiui/net/http/HttpSession$ProgressListener;


# direct methods
.method public constructor <init>(Ljava/lang/String;)V
    .locals 2

    .prologue
    const/4 v1, 0x0

    .line 66
    sget-object v0, Lmiui/util/async/tasks/HttpTask$Method;->Get:Lmiui/util/async/tasks/HttpTask$Method;

    invoke-direct {p0, v1, v0, p1, v1}, Lmiui/util/async/tasks/HttpTask;-><init>(Lmiui/net/http/HttpSession;Lmiui/util/async/tasks/HttpTask$Method;Ljava/lang/String;Ljava/util/Map;)V

    .line 67
    return-void
.end method

.method public constructor <init>(Lmiui/net/http/HttpSession;Ljava/lang/String;)V
    .locals 2

    .prologue
    .line 76
    sget-object v0, Lmiui/util/async/tasks/HttpTask$Method;->Get:Lmiui/util/async/tasks/HttpTask$Method;

    const/4 v1, 0x0

    invoke-direct {p0, p1, v0, p2, v1}, Lmiui/util/async/tasks/HttpTask;-><init>(Lmiui/net/http/HttpSession;Lmiui/util/async/tasks/HttpTask$Method;Ljava/lang/String;Ljava/util/Map;)V

    .line 77
    return-void
.end method

.method public constructor <init>(Lmiui/net/http/HttpSession;Lmiui/util/async/tasks/HttpTask$Method;Ljava/lang/String;Ljava/util/Map;)V
    .locals 3
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lmiui/net/http/HttpSession;",
            "Lmiui/util/async/tasks/HttpTask$Method;",
            "Ljava/lang/String;",
            "Ljava/util/Map",
            "<",
            "Ljava/lang/String;",
            "Ljava/lang/String;",
            ">;)V"
        }
    .end annotation

    .prologue
    .line 87
    invoke-direct {p0}, Lmiui/util/async/Task;-><init>()V

    .line 88
    if-nez p1, :cond_0

    .line 89
    invoke-static {}, Lmiui/net/http/HttpSession;->getDefault()Lmiui/net/http/HttpSession;

    move-result-object p1

    .line 92
    :cond_0
    iput-object p1, p0, Lmiui/util/async/tasks/HttpTask;->Pg:Lmiui/net/http/HttpSession;

    .line 93
    iput-object p2, p0, Lmiui/util/async/tasks/HttpTask;->Ph:Lmiui/util/async/tasks/HttpTask$Method;

    .line 94
    sget-object v0, Lmiui/util/async/tasks/HttpTask$Method;->Get:Lmiui/util/async/tasks/HttpTask$Method;

    if-ne p2, v0, :cond_3

    .line 95
    if-eqz p4, :cond_2

    invoke-interface {p4}, Ljava/util/Map;->size()I

    move-result v0

    if-lez v0, :cond_2

    .line 96
    new-instance v0, Lmiui/net/http/HttpRequestParams;

    const/4 v1, 0x1

    new-array v1, v1, [Ljava/lang/Object;

    const/4 v2, 0x0

    aput-object p4, v1, v2

    invoke-direct {v0, v1}, Lmiui/net/http/HttpRequestParams;-><init>([Ljava/lang/Object;)V

    .line 97
    const/16 v1, 0x3f

    invoke-virtual {p3, v1}, Ljava/lang/String;->indexOf(I)I

    move-result v1

    if-ltz v1, :cond_1

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v1, p3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, "&"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v0}, Lmiui/net/http/HttpRequestParams;->getParamString()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    :goto_0
    iput-object v0, p0, Lmiui/util/async/tasks/HttpTask;->gf:Ljava/lang/String;

    .line 102
    :goto_1
    const/4 v0, 0x0

    iput-object v0, p0, Lmiui/util/async/tasks/HttpTask;->Pi:Lmiui/net/http/HttpRequestParams;

    .line 108
    :goto_2
    return-void

    .line 97
    :cond_1
    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v1, p3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, "?"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v0}, Lmiui/net/http/HttpRequestParams;->getParamString()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    goto :goto_0

    .line 100
    :cond_2
    iput-object p3, p0, Lmiui/util/async/tasks/HttpTask;->gf:Ljava/lang/String;

    goto :goto_1

    .line 104
    :cond_3
    iput-object p3, p0, Lmiui/util/async/tasks/HttpTask;->gf:Ljava/lang/String;

    .line 105
    new-instance v0, Lmiui/net/http/HttpRequestParams;

    invoke-direct {v0}, Lmiui/net/http/HttpRequestParams;-><init>()V

    iput-object v0, p0, Lmiui/util/async/tasks/HttpTask;->Pi:Lmiui/net/http/HttpRequestParams;

    .line 106
    iget-object v0, p0, Lmiui/util/async/tasks/HttpTask;->Pi:Lmiui/net/http/HttpRequestParams;

    invoke-virtual {v0, p4}, Lmiui/net/http/HttpRequestParams;->add(Ljava/util/Map;)Lmiui/net/http/HttpRequestParams;

    goto :goto_2
.end method


# virtual methods
.method public getDescription()Ljava/lang/String;
    .locals 1

    .prologue
    .line 112
    iget-object v0, p0, Lmiui/util/async/tasks/HttpTask;->gf:Ljava/lang/String;

    return-object v0
.end method

.method protected final request()Lmiui/net/http/HttpResponse;
    .locals 5
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/Exception;
        }
    .end annotation

    .prologue
    const/4 v0, 0x0

    .line 121
    iget-object v1, p0, Lmiui/util/async/tasks/HttpTask;->t:Lmiui/net/http/HttpSession$ProgressListener;

    if-nez v1, :cond_0

    .line 122
    new-instance v1, Lmiui/util/async/tasks/HttpTask$2;

    invoke-direct {v1, p0}, Lmiui/util/async/tasks/HttpTask$2;-><init>(Lmiui/util/async/tasks/HttpTask;)V

    iput-object v1, p0, Lmiui/util/async/tasks/HttpTask;->t:Lmiui/net/http/HttpSession$ProgressListener;

    .line 135
    :cond_0
    sget-object v1, Lmiui/util/async/tasks/HttpTask$1;->Br:[I

    iget-object v2, p0, Lmiui/util/async/tasks/HttpTask;->Ph:Lmiui/util/async/tasks/HttpTask$Method;

    invoke-virtual {v2}, Lmiui/util/async/tasks/HttpTask$Method;->ordinal()I

    move-result v2

    aget v1, v1, v2

    packed-switch v1, :pswitch_data_0

    .line 144
    :goto_0
    return-object v0

    .line 137
    :pswitch_0
    iget-object v1, p0, Lmiui/util/async/tasks/HttpTask;->Pg:Lmiui/net/http/HttpSession;

    iget-object v2, p0, Lmiui/util/async/tasks/HttpTask;->gf:Ljava/lang/String;

    iget-object v3, p0, Lmiui/util/async/tasks/HttpTask;->Pi:Lmiui/net/http/HttpRequestParams;

    iget-object v4, p0, Lmiui/util/async/tasks/HttpTask;->t:Lmiui/net/http/HttpSession$ProgressListener;

    invoke-virtual {v1, v2, v0, v3, v4}, Lmiui/net/http/HttpSession;->get(Ljava/lang/String;Ljava/util/Map;Lmiui/net/http/HttpRequestParams;Lmiui/net/http/HttpSession$ProgressListener;)Lmiui/net/http/HttpResponse;

    move-result-object v0

    goto :goto_0

    .line 140
    :pswitch_1
    iget-object v1, p0, Lmiui/util/async/tasks/HttpTask;->Pg:Lmiui/net/http/HttpSession;

    iget-object v2, p0, Lmiui/util/async/tasks/HttpTask;->gf:Ljava/lang/String;

    iget-object v3, p0, Lmiui/util/async/tasks/HttpTask;->Pi:Lmiui/net/http/HttpRequestParams;

    iget-object v4, p0, Lmiui/util/async/tasks/HttpTask;->t:Lmiui/net/http/HttpSession$ProgressListener;

    invoke-virtual {v1, v2, v0, v3, v4}, Lmiui/net/http/HttpSession;->post(Ljava/lang/String;Ljava/util/Map;Lmiui/net/http/HttpRequestParams;Lmiui/net/http/HttpSession$ProgressListener;)Lmiui/net/http/HttpResponse;

    move-result-object v0

    goto :goto_0

    .line 135
    :pswitch_data_0
    .packed-switch 0x1
        :pswitch_0
        :pswitch_1
    .end packed-switch
.end method
