.class public Lcom/miui/internal/app/LicenseActivity;
.super Lmiui/app/Activity;
.source "SourceFile"


# static fields
.field private static final fL:Ljava/lang/String; = "http://www.miui.com/res/doc/copyright.html?region=%s&lang=%s"

.field private static final fM:Ljava/lang/String; = "http://www.miui.com/res/doc/privacy.html?region=%s&lang=%s"

.field private static final fN:Ljava/lang/String; = "http://www.miui.com/res/doc/eula.html?region=%s&lang=%s"

.field private static final fO:Ljava/lang/String; = "http://www.miui.com/res/doc/manual.html?lang=%s&miphone=%s&version=v6"

.field private static final fP:Ljava/lang/String; = "http://www.miui.com/res/doc/mibilicense.html?region=%s&lang=%s"

.field private static final fQ:Ljava/lang/String; = "https://i.mi.com/useragreement.htm?region=%s&lang=%s"

.field private static final fR:Ljava/lang/String; = "file:///android_asset/"

.field private static final fS:Ljava/lang/String; = "license/%s/%s/copyright.html"

.field private static final fT:Ljava/lang/String; = "license/%s/%s/privacy.html"

.field private static final fU:Ljava/lang/String; = "license/%s/%s/eula.html"

.field private static final fV:Ljava/lang/String; = "user_manual/%s/%s/index.html"

.field private static final fW:Ljava/lang/String; = "license/%s/%s/mibilicense.html"

.field private static final fX:Ljava/lang/String; = "license/%s/%s/miclouduseragreement.htm"

.field private static final fY:[Ljava/lang/String;

.field private static final fZ:[Ljava/lang/String;

.field private static final ga:[I

.field private static final gb:Ljava/lang/String; = "en_US"

.field private static final gc:Ljava/lang/String; = "default"

.field private static final gd:I = 0x0

.field private static final ge:I = 0x1


# instance fields
.field private gf:Ljava/lang/String;

.field private gg:Ljava/lang/String;

.field private gh:Ljava/lang/String;

.field private gi:Landroid/webkit/WebView;

.field private gj:Landroid/webkit/WebViewClient;

.field private mOnCancelListener:Landroid/content/DialogInterface$OnCancelListener;

.field private mOnClickListener:Landroid/content/DialogInterface$OnClickListener;


# direct methods
.method static constructor <clinit>()V
    .locals 8

    .prologue
    const/4 v7, 0x4

    const/4 v6, 0x3

    const/4 v5, 0x2

    const/4 v4, 0x1

    const/4 v3, 0x0

    .line 57
    const/4 v0, 0x6

    new-array v0, v0, [Ljava/lang/String;

    const-string v1, "http://www.miui.com/res/doc/copyright.html?region=%s&lang=%s"

    aput-object v1, v0, v3

    const-string v1, "http://www.miui.com/res/doc/privacy.html?region=%s&lang=%s"

    aput-object v1, v0, v4

    const-string v1, "http://www.miui.com/res/doc/eula.html?region=%s&lang=%s"

    aput-object v1, v0, v5

    const-string v1, "http://www.miui.com/res/doc/manual.html?lang=%s&miphone=%s&version=v6"

    aput-object v1, v0, v6

    const-string v1, "http://www.miui.com/res/doc/mibilicense.html?region=%s&lang=%s"

    aput-object v1, v0, v7

    const/4 v1, 0x5

    const-string v2, "https://i.mi.com/useragreement.htm?region=%s&lang=%s"

    aput-object v2, v0, v1

    sput-object v0, Lcom/miui/internal/app/LicenseActivity;->fY:[Ljava/lang/String;

    .line 66
    const/4 v0, 0x6

    new-array v0, v0, [Ljava/lang/String;

    const-string v1, "license/%s/%s/copyright.html"

    aput-object v1, v0, v3

    const-string v1, "license/%s/%s/privacy.html"

    aput-object v1, v0, v4

    const-string v1, "license/%s/%s/eula.html"

    aput-object v1, v0, v5

    const-string v1, "user_manual/%s/%s/index.html"

    aput-object v1, v0, v6

    const-string v1, "license/%s/%s/mibilicense.html"

    aput-object v1, v0, v7

    const/4 v1, 0x5

    const-string v2, "license/%s/%s/miclouduseragreement.htm"

    aput-object v2, v0, v1

    sput-object v0, Lcom/miui/internal/app/LicenseActivity;->fZ:[Ljava/lang/String;

    .line 75
    const/4 v0, 0x5

    new-array v0, v0, [I

    sget v1, Lcom/miui/internal/R$string;->copyright:I

    aput v1, v0, v3

    sget v1, Lcom/miui/internal/R$string;->privacy_policy:I

    aput v1, v0, v4

    sget v1, Lcom/miui/internal/R$string;->user_agreement:I

    aput v1, v0, v5

    sget v1, Lcom/miui/internal/R$string;->user_manual:I

    aput v1, v0, v6

    sget v1, Lcom/miui/internal/R$string;->mibi_license:I

    aput v1, v0, v7

    sput-object v0, Lcom/miui/internal/app/LicenseActivity;->ga:[I

    return-void
.end method

.method public constructor <init>()V
    .locals 1

    .prologue
    .line 40
    invoke-direct {p0}, Lmiui/app/Activity;-><init>()V

    .line 94
    new-instance v0, Lcom/miui/internal/app/i;

    invoke-direct {v0, p0}, Lcom/miui/internal/app/i;-><init>(Lcom/miui/internal/app/LicenseActivity;)V

    iput-object v0, p0, Lcom/miui/internal/app/LicenseActivity;->mOnCancelListener:Landroid/content/DialogInterface$OnCancelListener;

    .line 101
    new-instance v0, Lcom/miui/internal/app/h;

    invoke-direct {v0, p0}, Lcom/miui/internal/app/h;-><init>(Lcom/miui/internal/app/LicenseActivity;)V

    iput-object v0, p0, Lcom/miui/internal/app/LicenseActivity;->mOnClickListener:Landroid/content/DialogInterface$OnClickListener;

    .line 108
    new-instance v0, Lcom/miui/internal/app/j;

    invoke-direct {v0, p0}, Lcom/miui/internal/app/j;-><init>(Lcom/miui/internal/app/LicenseActivity;)V

    iput-object v0, p0, Lcom/miui/internal/app/LicenseActivity;->gj:Landroid/webkit/WebViewClient;

    return-void
.end method

.method private Y()Z
    .locals 3

    .prologue
    const/4 v0, 0x0

    .line 293
    invoke-virtual {p0}, Lcom/miui/internal/app/LicenseActivity;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object v1

    .line 294
    const-string v2, "device_provisioned"

    invoke-static {v1, v2, v0}, Landroid/provider/Settings$Secure;->getInt(Landroid/content/ContentResolver;Ljava/lang/String;I)I

    move-result v1

    if-eqz v1, :cond_0

    const/4 v0, 0x1

    :cond_0
    return v0
.end method

.method private Z()Z
    .locals 1

    .prologue
    .line 298
    const-string v0, "connectivity"

    invoke-virtual {p0, v0}, Lcom/miui/internal/app/LicenseActivity;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/net/ConnectivityManager;

    .line 299
    if-eqz v0, :cond_0

    invoke-virtual {v0}, Landroid/net/ConnectivityManager;->getActiveNetworkInfo()Landroid/net/NetworkInfo;

    move-result-object v0

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method private a(ILjava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    .locals 5

    .prologue
    const/4 v2, 0x2

    const/4 v4, 0x1

    const/4 v3, 0x0

    .line 234
    const/4 v0, 0x3

    if-ne p1, v0, :cond_1

    .line 235
    sget-object v0, Lmiui/os/Build;->DEVICE:Ljava/lang/String;

    .line 236
    sget-boolean v1, Lmiui/os/Build;->IS_MIFOUR:Z

    if-eqz v1, :cond_0

    .line 237
    const-string v0, "mi4"

    .line 239
    :cond_0
    sget-object v1, Lcom/miui/internal/app/LicenseActivity;->fY:[Ljava/lang/String;

    aget-object v1, v1, p1

    new-array v2, v2, [Ljava/lang/Object;

    aput-object p3, v2, v3

    aput-object v0, v2, v4

    invoke-static {v1, v2}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    .line 244
    :goto_0
    return-object v0

    .line 241
    :cond_1
    sget-object v0, Lcom/miui/internal/app/LicenseActivity;->fY:[Ljava/lang/String;

    aget-object v0, v0, p1

    new-array v1, v2, [Ljava/lang/Object;

    aput-object p2, v1, v3

    aput-object p3, v1, v4

    invoke-static {v0, v1}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    goto :goto_0
.end method

.method static synthetic a(Lcom/miui/internal/app/LicenseActivity;)Z
    .locals 1

    .prologue
    .line 40
    invoke-direct {p0}, Lcom/miui/internal/app/LicenseActivity;->Y()Z

    move-result v0

    return v0
.end method

.method private aa()V
    .locals 2

    .prologue
    .line 303
    iget-object v0, p0, Lcom/miui/internal/app/LicenseActivity;->gi:Landroid/webkit/WebView;

    const/16 v1, 0x8

    invoke-virtual {v0, v1}, Landroid/webkit/WebView;->setVisibility(I)V

    .line 304
    const/4 v0, 0x0

    invoke-virtual {p0, v0}, Lcom/miui/internal/app/LicenseActivity;->dismissDialog(I)V

    .line 305
    const/4 v0, 0x1

    invoke-virtual {p0, v0}, Lcom/miui/internal/app/LicenseActivity;->showDialog(I)V

    .line 306
    return-void
.end method

.method private b(ILjava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    .locals 6

    .prologue
    const/4 v5, 0x2

    const/4 v4, 0x1

    const/4 v3, 0x0

    .line 248
    const/4 v0, 0x0

    .line 249
    sget-object v1, Lcom/miui/internal/app/LicenseActivity;->fZ:[Ljava/lang/String;

    aget-object v1, v1, p1

    .line 251
    const/4 v2, 0x3

    if-ne p1, v2, :cond_1

    .line 252
    const-string v0, "user_manual/%s/%s/index.html"

    new-array v1, v5, [Ljava/lang/Object;

    sget-object v2, Lmiui/os/Build;->DEVICE:Ljava/lang/String;

    aput-object v2, v1, v3

    aput-object p3, v1, v4

    invoke-static {v0, v1}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    .line 253
    new-instance v1, Ljava/io/File;

    invoke-direct {v1, v0}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    invoke-virtual {v1}, Ljava/io/File;->exists()Z

    move-result v1

    if-nez v1, :cond_0

    .line 254
    const-string v0, "user_manual/%s/%s/index.html"

    new-array v1, v5, [Ljava/lang/Object;

    sget-object v2, Lmiui/os/Build;->DEVICE:Ljava/lang/String;

    aput-object v2, v1, v3

    const-string v2, "en_US"

    aput-object v2, v1, v4

    invoke-static {v0, v1}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    .line 271
    :cond_0
    :goto_0
    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "file:///android_asset/"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    return-object v0

    .line 256
    :cond_1
    invoke-static {v1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v2

    if-nez v2, :cond_0

    .line 257
    new-array v0, v5, [Ljava/lang/Object;

    aput-object p2, v0, v3

    aput-object p3, v0, v4

    invoke-static {v1, v0}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    .line 258
    invoke-direct {p0, v0}, Lcom/miui/internal/app/LicenseActivity;->e(Ljava/lang/String;)Z

    move-result v2

    if-nez v2, :cond_2

    .line 259
    new-array v0, v5, [Ljava/lang/Object;

    aput-object p2, v0, v3

    const-string v2, "en_US"

    aput-object v2, v0, v4

    invoke-static {v1, v0}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    .line 262
    :cond_2
    invoke-direct {p0, v0}, Lcom/miui/internal/app/LicenseActivity;->e(Ljava/lang/String;)Z

    move-result v2

    if-nez v2, :cond_3

    .line 263
    new-array v0, v5, [Ljava/lang/Object;

    const-string v2, "default"

    aput-object v2, v0, v3

    aput-object p3, v0, v4

    invoke-static {v1, v0}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    .line 266
    :cond_3
    invoke-direct {p0, v0}, Lcom/miui/internal/app/LicenseActivity;->e(Ljava/lang/String;)Z

    move-result v2

    if-nez v2, :cond_0

    .line 267
    new-array v0, v5, [Ljava/lang/Object;

    const-string v2, "default"

    aput-object v2, v0, v3

    const-string v2, "en_US"

    aput-object v2, v0, v4

    invoke-static {v1, v0}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    goto :goto_0
.end method

.method static synthetic b(Lcom/miui/internal/app/LicenseActivity;)Ljava/lang/String;
    .locals 1

    .prologue
    .line 40
    iget-object v0, p0, Lcom/miui/internal/app/LicenseActivity;->gh:Ljava/lang/String;

    return-object v0
.end method

.method static synthetic c(Lcom/miui/internal/app/LicenseActivity;)V
    .locals 0

    .prologue
    .line 40
    invoke-direct {p0}, Lcom/miui/internal/app/LicenseActivity;->aa()V

    return-void
.end method

.method static synthetic d(Lcom/miui/internal/app/LicenseActivity;)Landroid/webkit/WebView;
    .locals 1

    .prologue
    .line 40
    iget-object v0, p0, Lcom/miui/internal/app/LicenseActivity;->gi:Landroid/webkit/WebView;

    return-object v0
.end method

.method private e(Ljava/lang/String;)Z
    .locals 3

    .prologue
    .line 275
    const/4 v1, 0x0

    .line 276
    const/4 v0, 0x0

    .line 278
    :try_start_0
    invoke-virtual {p0}, Lcom/miui/internal/app/LicenseActivity;->getAssets()Landroid/content/res/AssetManager;

    move-result-object v2

    invoke-virtual {v2, p1}, Landroid/content/res/AssetManager;->open(Ljava/lang/String;)Ljava/io/InputStream;
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    move-result-object v1

    .line 279
    const/4 v0, 0x1

    .line 282
    if-eqz v1, :cond_0

    .line 284
    :try_start_1
    invoke-virtual {v1}, Ljava/io/InputStream;->close()V
    :try_end_1
    .catch Ljava/io/IOException; {:try_start_1 .. :try_end_1} :catch_2

    .line 289
    :cond_0
    :goto_0
    return v0

    .line 280
    :catch_0
    move-exception v2

    .line 282
    if-eqz v1, :cond_0

    .line 284
    :try_start_2
    invoke-virtual {v1}, Ljava/io/InputStream;->close()V
    :try_end_2
    .catch Ljava/io/IOException; {:try_start_2 .. :try_end_2} :catch_1

    goto :goto_0

    .line 285
    :catch_1
    move-exception v1

    goto :goto_0

    .line 282
    :catchall_0
    move-exception v0

    if-eqz v1, :cond_1

    .line 284
    :try_start_3
    invoke-virtual {v1}, Ljava/io/InputStream;->close()V
    :try_end_3
    .catch Ljava/io/IOException; {:try_start_3 .. :try_end_3} :catch_3

    .line 286
    :cond_1
    :goto_1
    throw v0

    .line 285
    :catch_2
    move-exception v1

    goto :goto_0

    :catch_3
    move-exception v1

    goto :goto_1
.end method

.method private w(I)Z
    .locals 1

    .prologue
    .line 229
    if-ltz p1, :cond_0

    const/4 v0, 0x5

    if-gt p1, v0, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method


# virtual methods
.method public onBackPressed()V
    .locals 1

    .prologue
    .line 310
    iget-object v0, p0, Lcom/miui/internal/app/LicenseActivity;->gi:Landroid/webkit/WebView;

    invoke-virtual {v0}, Landroid/webkit/WebView;->canGoBack()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 311
    iget-object v0, p0, Lcom/miui/internal/app/LicenseActivity;->gi:Landroid/webkit/WebView;

    invoke-virtual {v0}, Landroid/webkit/WebView;->goBack()V

    .line 316
    :goto_0
    return-void

    .line 315
    :cond_0
    invoke-super {p0}, Lmiui/app/Activity;->onBackPressed()V

    goto :goto_0
.end method

.method protected onCreate(Landroid/os/Bundle;)V
    .locals 4

    .prologue
    .line 158
    invoke-super {p0, p1}, Lmiui/app/Activity;->onCreate(Landroid/os/Bundle;)V

    .line 159
    sget v0, Lcom/miui/internal/R$layout;->license_activity:I

    invoke-virtual {p0, v0}, Lcom/miui/internal/app/LicenseActivity;->setContentView(I)V

    .line 161
    sget v0, Lcom/miui/internal/R$id;->web_view:I

    invoke-virtual {p0, v0}, Lcom/miui/internal/app/LicenseActivity;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/webkit/WebView;

    iput-object v0, p0, Lcom/miui/internal/app/LicenseActivity;->gi:Landroid/webkit/WebView;

    .line 162
    iget-object v0, p0, Lcom/miui/internal/app/LicenseActivity;->gi:Landroid/webkit/WebView;

    invoke-virtual {v0}, Landroid/webkit/WebView;->getSettings()Landroid/webkit/WebSettings;

    move-result-object v0

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Landroid/webkit/WebSettings;->setJavaScriptEnabled(Z)V

    .line 163
    iget-object v0, p0, Lcom/miui/internal/app/LicenseActivity;->gi:Landroid/webkit/WebView;

    iget-object v1, p0, Lcom/miui/internal/app/LicenseActivity;->gj:Landroid/webkit/WebViewClient;

    invoke-virtual {v0, v1}, Landroid/webkit/WebView;->setWebViewClient(Landroid/webkit/WebViewClient;)V

    .line 164
    iget-object v0, p0, Lcom/miui/internal/app/LicenseActivity;->gi:Landroid/webkit/WebView;

    new-instance v1, Lcom/miui/internal/app/LicenseActivity$1;

    invoke-direct {v1, p0}, Lcom/miui/internal/app/LicenseActivity$1;-><init>(Lcom/miui/internal/app/LicenseActivity;)V

    invoke-virtual {v0, v1}, Landroid/webkit/WebView;->setWebChromeClient(Landroid/webkit/WebChromeClient;)V

    .line 173
    invoke-virtual {p0}, Lcom/miui/internal/app/LicenseActivity;->getIntent()Landroid/content/Intent;

    move-result-object v0

    const-string v1, "android.intent.extra.LICENSE_TYPE"

    const/4 v2, -0x1

    invoke-virtual {v0, v1, v2}, Landroid/content/Intent;->getIntExtra(Ljava/lang/String;I)I

    move-result v0

    .line 174
    invoke-direct {p0, v0}, Lcom/miui/internal/app/LicenseActivity;->w(I)Z

    move-result v1

    if-nez v1, :cond_1

    .line 175
    invoke-virtual {p0}, Lcom/miui/internal/app/LicenseActivity;->finish()V

    .line 199
    :cond_0
    :goto_0
    return-void

    .line 179
    :cond_1
    sget-object v1, Lcom/miui/internal/app/LicenseActivity;->ga:[I

    aget v1, v1, v0

    invoke-virtual {p0, v1}, Lcom/miui/internal/app/LicenseActivity;->getString(I)Ljava/lang/String;

    move-result-object v1

    iput-object v1, p0, Lcom/miui/internal/app/LicenseActivity;->gg:Ljava/lang/String;

    .line 180
    invoke-virtual {p0}, Lcom/miui/internal/app/LicenseActivity;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    invoke-virtual {v1}, Landroid/content/res/Resources;->getConfiguration()Landroid/content/res/Configuration;

    move-result-object v1

    iget-object v1, v1, Landroid/content/res/Configuration;->locale:Ljava/util/Locale;

    invoke-virtual {v1}, Ljava/util/Locale;->toString()Ljava/lang/String;

    move-result-object v1

    .line 181
    const-string v2, "ro.miui.region"

    const-string v3, "default"

    invoke-static {v2, v3}, Lmiui/os/SystemProperties;->get(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    .line 182
    invoke-direct {p0, v0, v2, v1}, Lcom/miui/internal/app/LicenseActivity;->b(ILjava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    iput-object v3, p0, Lcom/miui/internal/app/LicenseActivity;->gh:Ljava/lang/String;

    .line 183
    invoke-direct {p0, v0, v2, v1}, Lcom/miui/internal/app/LicenseActivity;->a(ILjava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/internal/app/LicenseActivity;->gf:Ljava/lang/String;

    .line 185
    invoke-direct {p0}, Lcom/miui/internal/app/LicenseActivity;->Z()Z

    move-result v0

    if-eqz v0, :cond_2

    .line 186
    iget-object v0, p0, Lcom/miui/internal/app/LicenseActivity;->gi:Landroid/webkit/WebView;

    iget-object v1, p0, Lcom/miui/internal/app/LicenseActivity;->gf:Ljava/lang/String;

    invoke-virtual {v0, v1}, Landroid/webkit/WebView;->loadUrl(Ljava/lang/String;)V

    .line 195
    :goto_1
    invoke-virtual {p0}, Lcom/miui/internal/app/LicenseActivity;->getActionBar()Lmiui/app/ActionBar;

    move-result-object v0

    .line 196
    if-eqz v0, :cond_0

    .line 197
    iget-object v1, p0, Lcom/miui/internal/app/LicenseActivity;->gg:Ljava/lang/String;

    invoke-virtual {v0, v1}, Landroid/app/ActionBar;->setTitle(Ljava/lang/CharSequence;)V

    goto :goto_0

    .line 188
    :cond_2
    iget-object v0, p0, Lcom/miui/internal/app/LicenseActivity;->gh:Ljava/lang/String;

    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_3

    .line 189
    invoke-direct {p0}, Lcom/miui/internal/app/LicenseActivity;->aa()V

    goto :goto_1

    .line 191
    :cond_3
    iget-object v0, p0, Lcom/miui/internal/app/LicenseActivity;->gi:Landroid/webkit/WebView;

    iget-object v1, p0, Lcom/miui/internal/app/LicenseActivity;->gh:Ljava/lang/String;

    invoke-virtual {v0, v1}, Landroid/webkit/WebView;->loadUrl(Ljava/lang/String;)V

    goto :goto_1
.end method

.method protected onCreateDialog(I)Landroid/app/Dialog;
    .locals 5

    .prologue
    const/4 v4, 0x0

    .line 203
    const/4 v0, 0x0

    .line 204
    packed-switch p1, :pswitch_data_0

    .line 225
    :goto_0
    return-object v0

    .line 206
    :pswitch_0
    new-instance v0, Lmiui/app/ProgressDialog;

    invoke-direct {v0, p0}, Lmiui/app/ProgressDialog;-><init>(Landroid/content/Context;)V

    .line 207
    sget v1, Lcom/miui/internal/R$string;->loading:I

    invoke-virtual {p0, v1}, Lcom/miui/internal/app/LicenseActivity;->getString(I)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Lmiui/app/ProgressDialog;->setMessage(Ljava/lang/CharSequence;)V

    .line 208
    invoke-virtual {v0, v4}, Lmiui/app/ProgressDialog;->setProgressStyle(I)V

    goto :goto_0

    .line 214
    :pswitch_1
    new-instance v0, Lmiui/app/AlertDialog$Builder;

    invoke-direct {v0, p0}, Lmiui/app/AlertDialog$Builder;-><init>(Landroid/content/Context;)V

    .line 215
    sget v1, Lcom/miui/internal/R$string;->license_host_unreachable:I

    invoke-virtual {p0, v1}, Lcom/miui/internal/app/LicenseActivity;->getString(I)Ljava/lang/String;

    move-result-object v1

    const/4 v2, 0x1

    new-array v2, v2, [Ljava/lang/Object;

    iget-object v3, p0, Lcom/miui/internal/app/LicenseActivity;->gf:Ljava/lang/String;

    aput-object v3, v2, v4

    invoke-static {v1, v2}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    .line 216
    invoke-virtual {v0, v1}, Lmiui/app/AlertDialog$Builder;->setMessage(Ljava/lang/CharSequence;)Lmiui/app/AlertDialog$Builder;

    move-result-object v1

    iget-object v2, p0, Lcom/miui/internal/app/LicenseActivity;->gg:Ljava/lang/String;

    invoke-virtual {v1, v2}, Lmiui/app/AlertDialog$Builder;->setTitle(Ljava/lang/CharSequence;)Lmiui/app/AlertDialog$Builder;

    move-result-object v1

    const v2, 0x104000a

    iget-object v3, p0, Lcom/miui/internal/app/LicenseActivity;->mOnClickListener:Landroid/content/DialogInterface$OnClickListener;

    invoke-virtual {v1, v2, v3}, Lmiui/app/AlertDialog$Builder;->setPositiveButton(ILandroid/content/DialogInterface$OnClickListener;)Lmiui/app/AlertDialog$Builder;

    move-result-object v1

    iget-object v2, p0, Lcom/miui/internal/app/LicenseActivity;->mOnCancelListener:Landroid/content/DialogInterface$OnCancelListener;

    invoke-virtual {v1, v2}, Lmiui/app/AlertDialog$Builder;->setOnCancelListener(Landroid/content/DialogInterface$OnCancelListener;)Lmiui/app/AlertDialog$Builder;

    .line 220
    invoke-virtual {v0}, Lmiui/app/AlertDialog$Builder;->create()Lmiui/app/AlertDialog;

    move-result-object v0

    goto :goto_0

    .line 204
    nop

    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_0
        :pswitch_1
    .end packed-switch
.end method

.method public onOptionsItemSelected(Landroid/view/MenuItem;)Z
    .locals 2

    .prologue
    .line 320
    invoke-interface {p1}, Landroid/view/MenuItem;->getItemId()I

    move-result v0

    const v1, 0x102002c

    if-ne v0, v1, :cond_0

    .line 321
    invoke-virtual {p0}, Lcom/miui/internal/app/LicenseActivity;->finish()V

    .line 322
    const/4 v0, 0x1

    .line 325
    :goto_0
    return v0

    :cond_0
    invoke-super {p0, p1}, Lmiui/app/Activity;->onOptionsItemSelected(Landroid/view/MenuItem;)Z

    move-result v0

    goto :goto_0
.end method
