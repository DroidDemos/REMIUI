.class Lcom/miui/internal/app/LicenseActivity$1;
.super Landroid/webkit/WebChromeClient;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/internal/app/LicenseActivity;->onCreate(Landroid/os/Bundle;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic gE:Lcom/miui/internal/app/LicenseActivity;


# direct methods
.method constructor <init>(Lcom/miui/internal/app/LicenseActivity;)V
    .locals 0

    .prologue
    .line 164
    iput-object p1, p0, Lcom/miui/internal/app/LicenseActivity$1;->gE:Lcom/miui/internal/app/LicenseActivity;

    invoke-direct {p0}, Landroid/webkit/WebChromeClient;-><init>()V

    return-void
.end method


# virtual methods
.method public onProgressChanged(Landroid/webkit/WebView;I)V
    .locals 2

    .prologue
    .line 167
    const/16 v0, 0x64

    if-ne p2, v0, :cond_0

    .line 168
    iget-object v0, p0, Lcom/miui/internal/app/LicenseActivity$1;->gE:Lcom/miui/internal/app/LicenseActivity;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Lcom/miui/internal/app/LicenseActivity;->dismissDialog(I)V

    .line 170
    :cond_0
    return-void
.end method
