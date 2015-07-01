.class Lcom/miui/internal/app/PolicyActivity$b;
.super Landroid/os/AsyncTask;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/internal/app/PolicyActivity;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x2
    name = "b"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Landroid/os/AsyncTask",
        "<",
        "Ljava/lang/Void;",
        "Ljava/lang/Void;",
        "Ljava/lang/String;",
        ">;"
    }
.end annotation


# instance fields
.field final synthetic ai:Lcom/miui/internal/app/PolicyActivity;

.field private eR:Ljava/lang/String;


# direct methods
.method public constructor <init>(Lcom/miui/internal/app/PolicyActivity;Ljava/lang/String;)V
    .locals 0

    .prologue
    .line 308
    iput-object p1, p0, Lcom/miui/internal/app/PolicyActivity$b;->ai:Lcom/miui/internal/app/PolicyActivity;

    invoke-direct {p0}, Landroid/os/AsyncTask;-><init>()V

    .line 309
    iput-object p2, p0, Lcom/miui/internal/app/PolicyActivity$b;->eR:Ljava/lang/String;

    .line 310
    return-void
.end method


# virtual methods
.method protected varargs a([Ljava/lang/Void;)Ljava/lang/String;
    .locals 1

    .prologue
    .line 314
    :try_start_0
    iget-object v0, p0, Lcom/miui/internal/app/PolicyActivity$b;->eR:Ljava/lang/String;

    invoke-static {v0}, Lcom/miui/internal/app/PolicyActivity;->m(Ljava/lang/String;)Ljava/lang/String;
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_0

    move-result-object v0

    .line 318
    :goto_0
    return-object v0

    .line 315
    :catch_0
    move-exception v0

    .line 316
    invoke-virtual {v0}, Ljava/io/IOException;->printStackTrace()V

    .line 318
    const/4 v0, 0x0

    goto :goto_0
.end method

.method protected c(Ljava/lang/String;)V
    .locals 1

    .prologue
    .line 323
    iget-object v0, p0, Lcom/miui/internal/app/PolicyActivity$b;->ai:Lcom/miui/internal/app/PolicyActivity;

    invoke-static {v0, p1}, Lcom/miui/internal/app/PolicyActivity;->a(Lcom/miui/internal/app/PolicyActivity;Ljava/lang/String;)V

    .line 324
    return-void
.end method

.method protected bridge synthetic doInBackground([Ljava/lang/Object;)Ljava/lang/Object;
    .locals 1

    .prologue
    .line 305
    check-cast p1, [Ljava/lang/Void;

    invoke-virtual {p0, p1}, Lcom/miui/internal/app/PolicyActivity$b;->a([Ljava/lang/Void;)Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method protected bridge synthetic onPostExecute(Ljava/lang/Object;)V
    .locals 0

    .prologue
    .line 305
    check-cast p1, Ljava/lang/String;

    invoke-virtual {p0, p1}, Lcom/miui/internal/app/PolicyActivity$b;->c(Ljava/lang/String;)V

    return-void
.end method
