.class Lcom/miui/internal/app/PolicyActivity$1;
.super Landroid/webkit/WebViewClient;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/internal/app/PolicyActivity;->onCreate(Landroid/os/Bundle;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic ai:Lcom/miui/internal/app/PolicyActivity;


# direct methods
.method constructor <init>(Lcom/miui/internal/app/PolicyActivity;)V
    .locals 0

    .prologue
    .line 138
    iput-object p1, p0, Lcom/miui/internal/app/PolicyActivity$1;->ai:Lcom/miui/internal/app/PolicyActivity;

    invoke-direct {p0}, Landroid/webkit/WebViewClient;-><init>()V

    return-void
.end method


# virtual methods
.method public onReceivedError(Landroid/webkit/WebView;ILjava/lang/String;Ljava/lang/String;)V
    .locals 1

    .prologue
    .line 141
    iget-object v0, p0, Lcom/miui/internal/app/PolicyActivity$1;->ai:Lcom/miui/internal/app/PolicyActivity;

    invoke-static {v0}, Lcom/miui/internal/app/PolicyActivity;->a(Lcom/miui/internal/app/PolicyActivity;)V

    .line 142
    return-void
.end method
