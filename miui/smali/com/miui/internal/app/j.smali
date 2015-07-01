.class Lcom/miui/internal/app/j;
.super Landroid/webkit/WebViewClient;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/internal/app/LicenseActivity;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic gE:Lcom/miui/internal/app/LicenseActivity;

.field private gZ:Z


# direct methods
.method constructor <init>(Lcom/miui/internal/app/LicenseActivity;)V
    .locals 0

    .prologue
    .line 108
    iput-object p1, p0, Lcom/miui/internal/app/j;->gE:Lcom/miui/internal/app/LicenseActivity;

    invoke-direct {p0}, Landroid/webkit/WebViewClient;-><init>()V

    return-void
.end method


# virtual methods
.method public onPageFinished(Landroid/webkit/WebView;Ljava/lang/String;)V
    .locals 2

    .prologue
    .line 119
    iget-object v0, p0, Lcom/miui/internal/app/j;->gE:Lcom/miui/internal/app/LicenseActivity;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Lcom/miui/internal/app/LicenseActivity;->dismissDialog(I)V

    .line 120
    return-void
.end method

.method public onPageStarted(Landroid/webkit/WebView;Ljava/lang/String;Landroid/graphics/Bitmap;)V
    .locals 2

    .prologue
    .line 114
    iget-object v0, p0, Lcom/miui/internal/app/j;->gE:Lcom/miui/internal/app/LicenseActivity;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Lcom/miui/internal/app/LicenseActivity;->showDialog(I)V

    .line 115
    return-void
.end method

.method public onReceivedError(Landroid/webkit/WebView;ILjava/lang/String;Ljava/lang/String;)V
    .locals 2

    .prologue
    .line 124
    iget-object v0, p0, Lcom/miui/internal/app/j;->gE:Lcom/miui/internal/app/LicenseActivity;

    invoke-static {v0}, Lcom/miui/internal/app/LicenseActivity;->a(Lcom/miui/internal/app/LicenseActivity;)Z

    move-result v0

    if-nez v0, :cond_0

    .line 133
    :goto_0
    return-void

    .line 128
    :cond_0
    iget-object v0, p0, Lcom/miui/internal/app/j;->gE:Lcom/miui/internal/app/LicenseActivity;

    invoke-static {v0}, Lcom/miui/internal/app/LicenseActivity;->b(Lcom/miui/internal/app/LicenseActivity;)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_1

    iget-object v0, p0, Lcom/miui/internal/app/j;->gE:Lcom/miui/internal/app/LicenseActivity;

    invoke-static {v0}, Lcom/miui/internal/app/LicenseActivity;->b(Lcom/miui/internal/app/LicenseActivity;)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p4, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_2

    .line 129
    :cond_1
    iget-object v0, p0, Lcom/miui/internal/app/j;->gE:Lcom/miui/internal/app/LicenseActivity;

    invoke-static {v0}, Lcom/miui/internal/app/LicenseActivity;->c(Lcom/miui/internal/app/LicenseActivity;)V

    goto :goto_0

    .line 131
    :cond_2
    iget-object v0, p0, Lcom/miui/internal/app/j;->gE:Lcom/miui/internal/app/LicenseActivity;

    invoke-static {v0}, Lcom/miui/internal/app/LicenseActivity;->d(Lcom/miui/internal/app/LicenseActivity;)Landroid/webkit/WebView;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/internal/app/j;->gE:Lcom/miui/internal/app/LicenseActivity;

    invoke-static {v1}, Lcom/miui/internal/app/LicenseActivity;->b(Lcom/miui/internal/app/LicenseActivity;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Landroid/webkit/WebView;->loadUrl(Ljava/lang/String;)V

    goto :goto_0
.end method

.method public shouldOverrideUrlLoading(Landroid/webkit/WebView;Ljava/lang/String;)Z
    .locals 3

    .prologue
    const/4 v1, 0x1

    const/4 v0, 0x0

    .line 137
    iget-object v2, p0, Lcom/miui/internal/app/j;->gE:Lcom/miui/internal/app/LicenseActivity;

    invoke-static {v2}, Lcom/miui/internal/app/LicenseActivity;->a(Lcom/miui/internal/app/LicenseActivity;)Z

    move-result v2

    if-nez v2, :cond_0

    iget-boolean v2, p0, Lcom/miui/internal/app/j;->gZ:Z

    if-eqz v2, :cond_0

    .line 138
    invoke-virtual {p1, p2}, Landroid/webkit/WebView;->loadUrl(Ljava/lang/String;)V

    .line 152
    :goto_0
    return v0

    .line 142
    :cond_0
    iget-boolean v2, p0, Lcom/miui/internal/app/j;->gZ:Z

    if-eqz v2, :cond_1

    .line 143
    new-instance v0, Landroid/content/Intent;

    const-string v2, "android.intent.action.VIEW"

    invoke-direct {v0, v2}, Landroid/content/Intent;-><init>(Ljava/lang/String;)V

    .line 144
    invoke-static {p2}, Landroid/net/Uri;->parse(Ljava/lang/String;)Landroid/net/Uri;

    move-result-object v2

    invoke-virtual {v0, v2}, Landroid/content/Intent;->setData(Landroid/net/Uri;)Landroid/content/Intent;

    .line 146
    :try_start_0
    iget-object v2, p0, Lcom/miui/internal/app/j;->gE:Lcom/miui/internal/app/LicenseActivity;

    invoke-virtual {v2, v0}, Lcom/miui/internal/app/LicenseActivity;->startActivity(Landroid/content/Intent;)V
    :try_end_0
    .catch Landroid/content/ActivityNotFoundException; {:try_start_0 .. :try_end_0} :catch_0

    :goto_1
    move v0, v1

    .line 149
    goto :goto_0

    .line 151
    :cond_1
    iput-boolean v1, p0, Lcom/miui/internal/app/j;->gZ:Z

    goto :goto_0

    .line 147
    :catch_0
    move-exception v0

    goto :goto_1
.end method
