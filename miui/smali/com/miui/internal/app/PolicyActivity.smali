.class public Lcom/miui/internal/app/PolicyActivity;
.super Lmiui/app/Activity;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/internal/app/PolicyActivity$b;,
        Lcom/miui/internal/app/PolicyActivity$a;,
        Lcom/miui/internal/app/PolicyActivity$c;
    }
.end annotation


# static fields
.field private static final DEBUG:Z = true

.field private static final PACKAGE_NAME:Ljava/lang/String; = "packagename"

.field private static final TAG:Ljava/lang/String; = "PolicyActivity"

.field private static final oA:Ljava/lang/String; = "https://api.sec.miui.com/policy/policies"

.field private static final oB:Ljava/lang/String; = "http://www.miui.com/legal/sla/"

.field private static final oC:Ljava/lang/String; = "policies"

.field private static final oD:Ljava/lang/String; = "pid"

.field private static final oE:Ljava/lang/String; = "name"

.field private static final oF:Ljava/lang/String; = "lang"

.field private static final oG:Ljava/lang/String; = "title"

.field private static final oH:Ljava/lang/String; = "version"

.field private static final oI:Ljava/lang/String; = "url"

.field private static final oJ:Ljava/lang/String; = "style"

.field private static final oK:Ljava/lang/String; = "category"

.field private static final oL:Ljava/lang/String; = "cancel"

.field private static final oM:Ljava/lang/String; = "agree"

.field private static final oN:Ljava/lang/String; = "e1db93de-e5ec-4337-b309-65a99ffa4441"

.field private static final oO:I = 0x0

.field private static final oP:I = 0x1

.field private static final oQ:I = 0x1

.field private static final oR:I = 0x2

.field private static final ou:Ljava/lang/String; = "policytype"

.field private static final ov:Ljava/lang/String; = "policyname"

.field private static final ow:Ljava/lang/String; = "applicationname"

.field private static final ox:Ljava/lang/String; = "oldversion"

.field private static final oy:Ljava/lang/String; = "newversion"

.field private static final oz:Ljava/lang/String; = "showstyle"


# instance fields
.field private gi:Landroid/webkit/WebView;

.field private oS:I

.field private oT:Ljava/lang/String;

.field private oU:Ljava/lang/String;

.field private oV:Ljava/lang/String;

.field private oW:Lcom/miui/internal/app/PolicyActivity$c;

.field private oX:Landroid/view/View;

.field private oY:Landroid/view/ActionMode;


# direct methods
.method public constructor <init>()V
    .locals 0

    .prologue
    .line 50
    invoke-direct {p0}, Lmiui/app/Activity;-><init>()V

    .line 305
    return-void
.end method

.method static synthetic a(Lcom/miui/internal/app/PolicyActivity;Landroid/view/ActionMode;)Landroid/view/ActionMode;
    .locals 0

    .prologue
    .line 50
    iput-object p1, p0, Lcom/miui/internal/app/PolicyActivity;->oY:Landroid/view/ActionMode;

    return-object p1
.end method

.method private static a(Landroid/content/Context;Ljava/lang/String;)Ljava/lang/String;
    .locals 3

    .prologue
    .line 371
    invoke-virtual {p0}, Landroid/content/Context;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object v0

    const-string v1, "android_id"

    invoke-static {v0, v1}, Landroid/provider/Settings$Secure;->getString(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    .line 372
    const-string v0, ""

    .line 373
    invoke-static {v1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v2

    if-nez v2, :cond_0

    .line 375
    :try_start_0
    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, "-"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    const-string v2, "MD5"

    invoke-static {v1, v2}, Lmiui/security/DigestUtils;->get(Ljava/lang/CharSequence;Ljava/lang/String;)[B

    move-result-object v1

    invoke-static {v1}, Lmiui/text/ExtraTextUtils;->toHexReadable([B)Ljava/lang/String;
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    move-result-object v0

    .line 381
    :cond_0
    :goto_0
    return-object v0

    .line 377
    :catch_0
    move-exception v1

    .line 378
    invoke-virtual {v1}, Ljava/lang/Exception;->printStackTrace()V

    goto :goto_0
.end method

.method private a(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    .locals 10

    .prologue
    .line 332
    invoke-direct {p0}, Lcom/miui/internal/app/PolicyActivity;->getDeviceName()Ljava/lang/String;

    move-result-object v0

    .line 333
    const-string v1, "ro.carrier.name"

    const-string v2, ""

    invoke-static {v1, v2}, Lmiui/os/SystemProperties;->get(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    .line 334
    invoke-static {}, Lmiui/os/Build;->getRegion()Ljava/lang/String;

    move-result-object v2

    .line 335
    invoke-direct {p0}, Lcom/miui/internal/app/PolicyActivity;->getLanguage()Ljava/lang/String;

    move-result-object v3

    .line 336
    invoke-direct {p0}, Lcom/miui/internal/app/PolicyActivity;->bi()Ljava/lang/String;

    move-result-object v4

    .line 337
    invoke-direct {p0}, Lcom/miui/internal/app/PolicyActivity;->bh()Ljava/lang/String;

    move-result-object v5

    .line 338
    const-string v6, "e1db93de-e5ec-4337-b309-65a99ffa4441"

    invoke-static {p0, v6}, Lcom/miui/internal/app/PolicyActivity;->a(Landroid/content/Context;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v6

    .line 339
    const-string v7, "%s?d=%s&c=%s&r=%s&l=%s&v=%s&t=%s&mi=%s&pn=%s&pg=%s&ov=%s&nv=%s"

    const/16 v8, 0xc

    new-array v8, v8, [Ljava/lang/Object;

    const/4 v9, 0x0

    aput-object p1, v8, v9

    const/4 v9, 0x1

    aput-object v0, v8, v9

    const/4 v0, 0x2

    aput-object v1, v8, v0

    const/4 v0, 0x3

    aput-object v2, v8, v0

    const/4 v0, 0x4

    aput-object v3, v8, v0

    const/4 v0, 0x5

    aput-object v4, v8, v0

    const/4 v0, 0x6

    aput-object v5, v8, v0

    const/4 v0, 0x7

    aput-object v6, v8, v0

    const/16 v0, 0x8

    aput-object p2, v8, v0

    const/16 v0, 0x9

    aput-object p3, v8, v0

    const/16 v0, 0xa

    aput-object p4, v8, v0

    const/16 v0, 0xb

    aput-object p5, v8, v0

    invoke-static {v7, v8}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    .line 341
    return-object v0
.end method

.method private static a(Lorg/apache/http/client/methods/HttpUriRequest;)Lorg/apache/http/HttpEntity;
    .locals 3
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 410
    new-instance v0, Lorg/apache/http/params/BasicHttpParams;

    invoke-direct {v0}, Lorg/apache/http/params/BasicHttpParams;-><init>()V

    .line 411
    const/16 v1, 0xbb8

    invoke-static {v0, v1}, Lorg/apache/http/params/HttpConnectionParams;->setConnectionTimeout(Lorg/apache/http/params/HttpParams;I)V

    .line 412
    const/16 v1, 0x1388

    invoke-static {v0, v1}, Lorg/apache/http/params/HttpConnectionParams;->setSoTimeout(Lorg/apache/http/params/HttpParams;I)V

    .line 413
    new-instance v1, Lorg/apache/http/impl/client/DefaultHttpClient;

    invoke-direct {v1, v0}, Lorg/apache/http/impl/client/DefaultHttpClient;-><init>(Lorg/apache/http/params/HttpParams;)V

    .line 416
    :try_start_0
    invoke-interface {v1, p0}, Lorg/apache/http/client/HttpClient;->execute(Lorg/apache/http/client/methods/HttpUriRequest;)Lorg/apache/http/HttpResponse;

    move-result-object v0

    .line 417
    invoke-interface {v0}, Lorg/apache/http/HttpResponse;->getStatusLine()Lorg/apache/http/StatusLine;

    move-result-object v1

    invoke-interface {v1}, Lorg/apache/http/StatusLine;->getStatusCode()I

    move-result v1

    .line 418
    const/16 v2, 0xc8

    if-ne v1, v2, :cond_0

    .line 419
    invoke-interface {v0}, Lorg/apache/http/HttpResponse;->getEntity()Lorg/apache/http/HttpEntity;
    :try_end_0
    .catch Ljava/lang/IllegalArgumentException; {:try_start_0 .. :try_end_0} :catch_0

    move-result-object v0

    .line 424
    :goto_0
    return-object v0

    .line 421
    :catch_0
    move-exception v0

    .line 422
    invoke-virtual {v0}, Ljava/lang/IllegalArgumentException;->printStackTrace()V

    .line 424
    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method static synthetic a(Lcom/miui/internal/app/PolicyActivity;)V
    .locals 0

    .prologue
    .line 50
    invoke-direct {p0}, Lcom/miui/internal/app/PolicyActivity;->bf()V

    return-void
.end method

.method static synthetic a(Lcom/miui/internal/app/PolicyActivity;II)V
    .locals 0

    .prologue
    .line 50
    invoke-direct {p0, p1, p2}, Lcom/miui/internal/app/PolicyActivity;->setButton(II)V

    return-void
.end method

.method static synthetic a(Lcom/miui/internal/app/PolicyActivity;Ljava/lang/String;)V
    .locals 0

    .prologue
    .line 50
    invoke-direct {p0, p1}, Lcom/miui/internal/app/PolicyActivity;->g(Ljava/lang/String;)V

    return-void
.end method

.method private a(Lmiui/app/ActionBar;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    .locals 2

    .prologue
    .line 222
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v0, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    const-string v1, " "

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0, p3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    const-string v1, " "

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0, p4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lcom/miui/internal/app/PolicyActivity;->h(Ljava/lang/String;)V

    .line 223
    iget-object v0, p0, Lcom/miui/internal/app/PolicyActivity;->oY:Landroid/view/ActionMode;

    invoke-virtual {v0, p2}, Landroid/view/ActionMode;->setTitle(Ljava/lang/CharSequence;)V

    .line 224
    invoke-direct {p0, p3, p4}, Lcom/miui/internal/app/PolicyActivity;->b(Ljava/lang/String;Ljava/lang/String;)V

    .line 225
    return-void
.end method

.method static synthetic b(Lcom/miui/internal/app/PolicyActivity;)Landroid/view/ActionMode;
    .locals 1

    .prologue
    .line 50
    iget-object v0, p0, Lcom/miui/internal/app/PolicyActivity;->oY:Landroid/view/ActionMode;

    return-object v0
.end method

.method private b(Ljava/lang/String;Ljava/lang/String;)V
    .locals 2

    .prologue
    .line 262
    iget-object v0, p0, Lcom/miui/internal/app/PolicyActivity;->oY:Landroid/view/ActionMode;

    check-cast v0, Lmiui/view/EditActionMode;

    const v1, 0x1020019

    invoke-interface {v0, v1, p2}, Lmiui/view/EditActionMode;->setButton(ILjava/lang/CharSequence;)V

    .line 263
    iget-object v0, p0, Lcom/miui/internal/app/PolicyActivity;->oY:Landroid/view/ActionMode;

    check-cast v0, Lmiui/view/EditActionMode;

    const v1, 0x102001a

    invoke-interface {v0, v1, p1}, Lmiui/view/EditActionMode;->setButton(ILjava/lang/CharSequence;)V

    .line 264
    return-void
.end method

.method private bc()V
    .locals 2

    .prologue
    .line 184
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "mStyle="

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    iget v1, p0, Lcom/miui/internal/app/PolicyActivity;->oS:I

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v0

    const-string v1, ""

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lcom/miui/internal/app/PolicyActivity;->h(Ljava/lang/String;)V

    .line 185
    iget v0, p0, Lcom/miui/internal/app/PolicyActivity;->oS:I

    packed-switch v0, :pswitch_data_0

    .line 195
    :goto_0
    return-void

    .line 187
    :pswitch_0
    invoke-direct {p0}, Lcom/miui/internal/app/PolicyActivity;->bg()V

    goto :goto_0

    .line 190
    :pswitch_1
    invoke-direct {p0}, Lcom/miui/internal/app/PolicyActivity;->bd()V

    goto :goto_0

    .line 185
    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_0
        :pswitch_1
    .end packed-switch
.end method

.method private bd()V
    .locals 6

    .prologue
    .line 198
    invoke-virtual {p0}, Lcom/miui/internal/app/PolicyActivity;->getActionBar()Lmiui/app/ActionBar;

    move-result-object v3

    .line 199
    const-string v0, "showPolicyActivityWithButton()"

    invoke-static {v0}, Lcom/miui/internal/app/PolicyActivity;->h(Ljava/lang/String;)V

    .line 200
    if-eqz v3, :cond_2

    .line 201
    const v0, 0x104000a

    invoke-virtual {p0, v0}, Lcom/miui/internal/app/PolicyActivity;->getString(I)Ljava/lang/String;

    move-result-object v1

    .line 202
    const/high16 v0, 0x1040000

    invoke-virtual {p0, v0}, Lcom/miui/internal/app/PolicyActivity;->getString(I)Ljava/lang/String;

    move-result-object v2

    .line 203
    invoke-virtual {v3}, Lmiui/app/ActionBar;->getTitle()Ljava/lang/CharSequence;

    move-result-object v0

    if-eqz v0, :cond_3

    invoke-virtual {v3}, Lmiui/app/ActionBar;->getTitle()Ljava/lang/CharSequence;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v0

    .line 204
    :goto_0
    const-string v4, "actionBar!=null"

    invoke-static {v4}, Lcom/miui/internal/app/PolicyActivity;->h(Ljava/lang/String;)V

    .line 205
    iget-object v4, p0, Lcom/miui/internal/app/PolicyActivity;->oW:Lcom/miui/internal/app/PolicyActivity$c;

    if-eqz v4, :cond_4

    .line 206
    const-string v4, "mPolicy != null"

    invoke-static {v4}, Lcom/miui/internal/app/PolicyActivity;->h(Ljava/lang/String;)V

    .line 207
    iget-object v4, p0, Lcom/miui/internal/app/PolicyActivity;->oW:Lcom/miui/internal/app/PolicyActivity$c;

    iget-object v4, v4, Lcom/miui/internal/app/PolicyActivity$c;->fp:Ljava/lang/String;

    invoke-static {v4}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v4

    if-nez v4, :cond_0

    .line 208
    iget-object v0, p0, Lcom/miui/internal/app/PolicyActivity;->oW:Lcom/miui/internal/app/PolicyActivity$c;

    iget-object v0, v0, Lcom/miui/internal/app/PolicyActivity$c;->fp:Ljava/lang/String;

    .line 210
    :cond_0
    iget-object v4, p0, Lcom/miui/internal/app/PolicyActivity;->oW:Lcom/miui/internal/app/PolicyActivity$c;

    iget-object v4, v4, Lcom/miui/internal/app/PolicyActivity$c;->fs:Ljava/lang/String;

    invoke-static {v4}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v4

    if-nez v4, :cond_1

    .line 211
    iget-object v1, p0, Lcom/miui/internal/app/PolicyActivity;->oW:Lcom/miui/internal/app/PolicyActivity$c;

    iget-object v1, v1, Lcom/miui/internal/app/PolicyActivity$c;->fs:Ljava/lang/String;

    .line 213
    :cond_1
    iget-object v4, p0, Lcom/miui/internal/app/PolicyActivity;->oW:Lcom/miui/internal/app/PolicyActivity$c;

    iget-object v4, v4, Lcom/miui/internal/app/PolicyActivity$c;->fr:Ljava/lang/String;

    invoke-static {v4}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v4

    if-nez v4, :cond_4

    .line 214
    iget-object v2, p0, Lcom/miui/internal/app/PolicyActivity;->oW:Lcom/miui/internal/app/PolicyActivity$c;

    iget-object v2, v2, Lcom/miui/internal/app/PolicyActivity$c;->fr:Ljava/lang/String;

    move-object v5, v2

    move-object v2, v1

    move-object v1, v5

    .line 217
    :goto_1
    invoke-direct {p0, v3, v0, v2, v1}, Lcom/miui/internal/app/PolicyActivity;->a(Lmiui/app/ActionBar;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    .line 219
    :cond_2
    return-void

    .line 203
    :cond_3
    const-string v0, ""

    goto :goto_0

    :cond_4
    move-object v5, v2

    move-object v2, v1

    move-object v1, v5

    goto :goto_1
.end method

.method private be()V
    .locals 2

    .prologue
    .line 228
    iget-object v0, p0, Lcom/miui/internal/app/PolicyActivity;->oW:Lcom/miui/internal/app/PolicyActivity$c;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/internal/app/PolicyActivity;->oW:Lcom/miui/internal/app/PolicyActivity$c;

    iget-object v0, v0, Lcom/miui/internal/app/PolicyActivity$c;->eR:Ljava/lang/String;

    if-eqz v0, :cond_0

    .line 229
    iget-object v0, p0, Lcom/miui/internal/app/PolicyActivity;->gi:Landroid/webkit/WebView;

    iget-object v1, p0, Lcom/miui/internal/app/PolicyActivity;->oW:Lcom/miui/internal/app/PolicyActivity$c;

    iget-object v1, v1, Lcom/miui/internal/app/PolicyActivity$c;->eR:Ljava/lang/String;

    invoke-virtual {v0, v1}, Landroid/webkit/WebView;->loadUrl(Ljava/lang/String;)V

    .line 233
    :goto_0
    return-void

    .line 231
    :cond_0
    invoke-direct {p0}, Lcom/miui/internal/app/PolicyActivity;->bf()V

    goto :goto_0
.end method

.method private bf()V
    .locals 10

    .prologue
    const/4 v3, 0x1

    const/4 v9, 0x0

    .line 236
    sget v0, Lcom/miui/internal/R$string;->license_host_unreachable:I

    invoke-virtual {p0, v0}, Lcom/miui/internal/app/PolicyActivity;->getString(I)Ljava/lang/String;

    move-result-object v0

    new-array v1, v3, [Ljava/lang/Object;

    const-string v2, "http://www.miui.com/legal/sla/"

    aput-object v2, v1, v9

    invoke-static {v0, v1}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v4

    .line 237
    sget v0, Lcom/miui/internal/R$string;->app_name_which_call:I

    invoke-virtual {p0, v0}, Lcom/miui/internal/app/PolicyActivity;->getString(I)Ljava/lang/String;

    move-result-object v0

    new-array v1, v3, [Ljava/lang/Object;

    iget-object v2, p0, Lcom/miui/internal/app/PolicyActivity;->oU:Ljava/lang/String;

    aput-object v2, v1, v9

    invoke-static {v0, v1}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v5

    .line 238
    sget v0, Lcom/miui/internal/R$string;->app_version_which_call:I

    invoke-virtual {p0, v0}, Lcom/miui/internal/app/PolicyActivity;->getString(I)Ljava/lang/String;

    move-result-object v0

    new-array v1, v3, [Ljava/lang/Object;

    iget-object v2, p0, Lcom/miui/internal/app/PolicyActivity;->oT:Ljava/lang/String;

    aput-object v2, v1, v9

    invoke-static {v0, v1}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v6

    .line 239
    sget v0, Lcom/miui/internal/R$string;->policy_name:I

    invoke-virtual {p0, v0}, Lcom/miui/internal/app/PolicyActivity;->getString(I)Ljava/lang/String;

    move-result-object v0

    new-array v1, v3, [Ljava/lang/Object;

    iget-object v2, p0, Lcom/miui/internal/app/PolicyActivity;->oV:Ljava/lang/String;

    aput-object v2, v1, v9

    invoke-static {v0, v1}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v7

    .line 240
    iget-object v0, p0, Lcom/miui/internal/app/PolicyActivity;->oX:Landroid/view/View;

    sget v1, Lcom/miui/internal/R$id;->empty_textview:I

    invoke-virtual {v0, v1}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/TextView;

    .line 241
    iget-object v1, p0, Lcom/miui/internal/app/PolicyActivity;->oX:Landroid/view/View;

    sget v2, Lcom/miui/internal/R$id;->app_name_which_call:I

    invoke-virtual {v1, v2}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v1

    check-cast v1, Landroid/widget/TextView;

    .line 242
    iget-object v2, p0, Lcom/miui/internal/app/PolicyActivity;->oX:Landroid/view/View;

    sget v3, Lcom/miui/internal/R$id;->app_version_which_call:I

    invoke-virtual {v2, v3}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v2

    check-cast v2, Landroid/widget/TextView;

    .line 243
    iget-object v3, p0, Lcom/miui/internal/app/PolicyActivity;->oX:Landroid/view/View;

    sget v8, Lcom/miui/internal/R$id;->policy_name:I

    invoke-virtual {v3, v8}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v3

    check-cast v3, Landroid/widget/TextView;

    .line 244
    invoke-virtual {v0, v4}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 245
    invoke-virtual {v1, v5}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 246
    invoke-virtual {v2, v6}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 247
    invoke-virtual {v3, v7}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 248
    iget-object v0, p0, Lcom/miui/internal/app/PolicyActivity;->oX:Landroid/view/View;

    invoke-virtual {v0, v9}, Landroid/view/View;->setVisibility(I)V

    .line 249
    iget-object v0, p0, Lcom/miui/internal/app/PolicyActivity;->gi:Landroid/webkit/WebView;

    const/16 v1, 0x8

    invoke-virtual {v0, v1}, Landroid/webkit/WebView;->setVisibility(I)V

    .line 250
    return-void
.end method

.method private bg()V
    .locals 2

    .prologue
    .line 253
    invoke-virtual {p0}, Lcom/miui/internal/app/PolicyActivity;->getActionBar()Lmiui/app/ActionBar;

    move-result-object v0

    .line 254
    if-eqz v0, :cond_0

    .line 255
    iget-object v1, p0, Lcom/miui/internal/app/PolicyActivity;->oW:Lcom/miui/internal/app/PolicyActivity$c;

    if-eqz v1, :cond_0

    iget-object v1, p0, Lcom/miui/internal/app/PolicyActivity;->oW:Lcom/miui/internal/app/PolicyActivity$c;

    iget-object v1, v1, Lcom/miui/internal/app/PolicyActivity$c;->fp:Ljava/lang/String;

    if-eqz v1, :cond_0

    .line 256
    iget-object v1, p0, Lcom/miui/internal/app/PolicyActivity;->oW:Lcom/miui/internal/app/PolicyActivity$c;

    iget-object v1, v1, Lcom/miui/internal/app/PolicyActivity$c;->fp:Ljava/lang/String;

    invoke-virtual {v0, v1}, Lmiui/app/ActionBar;->setTitle(Ljava/lang/CharSequence;)V

    .line 259
    :cond_0
    return-void
.end method

.method private bh()Ljava/lang/String;
    .locals 1

    .prologue
    .line 351
    sget-boolean v0, Lmiui/os/Build;->IS_ALPHA_BUILD:Z

    if-eqz v0, :cond_0

    .line 352
    const-string v0, "alpha"

    .line 358
    :goto_0
    return-object v0

    .line 353
    :cond_0
    sget-boolean v0, Lmiui/os/Build;->IS_STABLE_VERSION:Z

    if-eqz v0, :cond_1

    .line 354
    const-string v0, "stable"

    goto :goto_0

    .line 356
    :cond_1
    const-string v0, "development"

    goto :goto_0
.end method

.method private bi()Ljava/lang/String;
    .locals 1

    .prologue
    .line 362
    sget-object v0, Landroid/os/Build$VERSION;->INCREMENTAL:Ljava/lang/String;

    return-object v0
.end method

.method private g(Ljava/lang/String;)V
    .locals 0

    .prologue
    .line 121
    invoke-direct {p0, p1}, Lcom/miui/internal/app/PolicyActivity;->i(Ljava/lang/String;)V

    .line 122
    invoke-direct {p0}, Lcom/miui/internal/app/PolicyActivity;->bc()V

    .line 123
    invoke-direct {p0}, Lcom/miui/internal/app/PolicyActivity;->be()V

    .line 124
    return-void
.end method

.method private getDeviceName()Ljava/lang/String;
    .locals 2

    .prologue
    .line 345
    const-string v0, "ro.product.mod_device"

    const/4 v1, 0x0

    invoke-static {v0, v1}, Lmiui/os/SystemProperties;->get(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 346
    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v1

    if-eqz v1, :cond_0

    sget-object v0, Lmiui/os/Build;->DEVICE:Ljava/lang/String;

    :cond_0
    return-object v0
.end method

.method private getLanguage()Ljava/lang/String;
    .locals 1

    .prologue
    .line 366
    invoke-virtual {p0}, Lcom/miui/internal/app/PolicyActivity;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    invoke-virtual {v0}, Landroid/content/res/Resources;->getConfiguration()Landroid/content/res/Configuration;

    move-result-object v0

    iget-object v0, v0, Landroid/content/res/Configuration;->locale:Ljava/util/Locale;

    invoke-virtual {v0}, Ljava/util/Locale;->toString()Ljava/lang/String;

    move-result-object v0

    .line 367
    return-object v0
.end method

.method private static h(Ljava/lang/String;)V
    .locals 1

    .prologue
    .line 128
    const-string v0, "PolicyActivity"

    invoke-static {v0, p0}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 130
    return-void
.end method

.method private i(Ljava/lang/String;)V
    .locals 3

    .prologue
    .line 169
    if-eqz p1, :cond_0

    .line 171
    :try_start_0
    new-instance v0, Lorg/json/JSONObject;

    invoke-direct {v0, p1}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V

    .line 172
    const-string v1, "policies"

    invoke-virtual {v0, v1}, Lorg/json/JSONObject;->getJSONArray(Ljava/lang/String;)Lorg/json/JSONArray;

    move-result-object v0

    .line 173
    invoke-virtual {v0}, Lorg/json/JSONArray;->length()I

    move-result v1

    if-lez v1, :cond_0

    .line 174
    new-instance v1, Lcom/miui/internal/app/PolicyActivity$c;

    const/4 v2, 0x0

    invoke-virtual {v0, v2}, Lorg/json/JSONArray;->getJSONObject(I)Lorg/json/JSONObject;

    move-result-object v0

    invoke-direct {v1, p0, v0}, Lcom/miui/internal/app/PolicyActivity$c;-><init>(Lcom/miui/internal/app/PolicyActivity;Lorg/json/JSONObject;)V

    iput-object v1, p0, Lcom/miui/internal/app/PolicyActivity;->oW:Lcom/miui/internal/app/PolicyActivity$c;
    :try_end_0
    .catch Lorg/json/JSONException; {:try_start_0 .. :try_end_0} :catch_0

    .line 180
    :cond_0
    :goto_0
    if-nez p1, :cond_1

    const-string p1, "null"

    :cond_1
    invoke-static {p1}, Lcom/miui/internal/app/PolicyActivity;->h(Ljava/lang/String;)V

    .line 181
    return-void

    .line 176
    :catch_0
    move-exception v0

    .line 177
    invoke-virtual {v0}, Lorg/json/JSONException;->printStackTrace()V

    goto :goto_0
.end method

.method private j(Ljava/lang/String;)V
    .locals 2

    .prologue
    .line 328
    new-instance v0, Lcom/miui/internal/app/PolicyActivity$b;

    invoke-direct {v0, p0, p1}, Lcom/miui/internal/app/PolicyActivity$b;-><init>(Lcom/miui/internal/app/PolicyActivity;Ljava/lang/String;)V

    const/4 v1, 0x0

    new-array v1, v1, [Ljava/lang/Void;

    invoke-virtual {v0, v1}, Lcom/miui/internal/app/PolicyActivity$b;->execute([Ljava/lang/Object;)Landroid/os/AsyncTask;

    .line 329
    return-void
.end method

.method private static k(Ljava/lang/String;)Ljava/lang/String;
    .locals 6
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    const/4 v0, 0x0

    .line 385
    new-instance v1, Lorg/apache/http/client/methods/HttpGet;

    invoke-direct {v1, p0}, Lorg/apache/http/client/methods/HttpGet;-><init>(Ljava/lang/String;)V

    .line 386
    invoke-static {v1}, Lcom/miui/internal/app/PolicyActivity;->a(Lorg/apache/http/client/methods/HttpUriRequest;)Lorg/apache/http/HttpEntity;

    move-result-object v2

    .line 389
    if-eqz v2, :cond_5

    .line 390
    :try_start_0
    new-instance v1, Ljava/io/BufferedReader;

    new-instance v3, Ljava/io/InputStreamReader;

    invoke-interface {v2}, Lorg/apache/http/HttpEntity;->getContent()Ljava/io/InputStream;

    move-result-object v4

    invoke-direct {v3, v4}, Ljava/io/InputStreamReader;-><init>(Ljava/io/InputStream;)V

    const/16 v4, 0x800

    invoke-direct {v1, v3, v4}, Ljava/io/BufferedReader;-><init>(Ljava/io/Reader;I)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_1

    .line 391
    :try_start_1
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    .line 393
    :goto_0
    invoke-virtual {v1}, Ljava/io/BufferedReader;->readLine()Ljava/lang/String;

    move-result-object v3

    if-eqz v3, :cond_2

    .line 394
    invoke-virtual {v0, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    goto :goto_0

    .line 399
    :catchall_0
    move-exception v0

    :goto_1
    if-eqz v2, :cond_0

    .line 400
    invoke-interface {v2}, Lorg/apache/http/HttpEntity;->consumeContent()V

    .line 402
    :cond_0
    if-eqz v1, :cond_1

    .line 403
    invoke-virtual {v1}, Ljava/io/BufferedReader;->close()V

    :cond_1
    throw v0

    .line 396
    :cond_2
    :try_start_2
    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    move-result-object v0

    .line 399
    if-eqz v2, :cond_3

    .line 400
    invoke-interface {v2}, Lorg/apache/http/HttpEntity;->consumeContent()V

    .line 402
    :cond_3
    if-eqz v1, :cond_4

    .line 403
    invoke-virtual {v1}, Ljava/io/BufferedReader;->close()V

    .line 406
    :cond_4
    :goto_2
    return-object v0

    .line 399
    :cond_5
    if-eqz v2, :cond_6

    .line 400
    invoke-interface {v2}, Lorg/apache/http/HttpEntity;->consumeContent()V

    .line 402
    :cond_6
    if-eqz v0, :cond_4

    .line 403
    invoke-virtual {v0}, Ljava/io/BufferedReader;->close()V

    goto :goto_2

    .line 399
    :catchall_1
    move-exception v1

    move-object v5, v1

    move-object v1, v0

    move-object v0, v5

    goto :goto_1
.end method

.method static synthetic l(Ljava/lang/String;)V
    .locals 0

    .prologue
    .line 50
    invoke-static {p0}, Lcom/miui/internal/app/PolicyActivity;->h(Ljava/lang/String;)V

    return-void
.end method

.method static synthetic m(Ljava/lang/String;)Ljava/lang/String;
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 50
    invoke-static {p0}, Lcom/miui/internal/app/PolicyActivity;->k(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method private setButton(II)V
    .locals 2

    .prologue
    .line 267
    iget-object v0, p0, Lcom/miui/internal/app/PolicyActivity;->oY:Landroid/view/ActionMode;

    check-cast v0, Lmiui/view/EditActionMode;

    const v1, 0x1020019

    invoke-interface {v0, v1, p2}, Lmiui/view/EditActionMode;->setButton(II)V

    .line 268
    iget-object v0, p0, Lcom/miui/internal/app/PolicyActivity;->oY:Landroid/view/ActionMode;

    check-cast v0, Lmiui/view/EditActionMode;

    const v1, 0x102001a

    invoke-interface {v0, v1, p1}, Lmiui/view/EditActionMode;->setButton(II)V

    .line 269
    return-void
.end method


# virtual methods
.method protected onCreate(Landroid/os/Bundle;)V
    .locals 7

    .prologue
    .line 134
    invoke-super {p0, p1}, Lmiui/app/Activity;->onCreate(Landroid/os/Bundle;)V

    .line 135
    sget v0, Lcom/miui/internal/R$layout;->license_activity:I

    invoke-virtual {p0, v0}, Lcom/miui/internal/app/PolicyActivity;->setContentView(I)V

    .line 136
    sget v0, Lcom/miui/internal/R$id;->web_view:I

    invoke-virtual {p0, v0}, Lcom/miui/internal/app/PolicyActivity;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/webkit/WebView;

    iput-object v0, p0, Lcom/miui/internal/app/PolicyActivity;->gi:Landroid/webkit/WebView;

    .line 137
    sget v0, Lcom/miui/internal/R$id;->empty_view:I

    invoke-virtual {p0, v0}, Lcom/miui/internal/app/PolicyActivity;->findViewById(I)Landroid/view/View;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/internal/app/PolicyActivity;->oX:Landroid/view/View;

    .line 138
    iget-object v0, p0, Lcom/miui/internal/app/PolicyActivity;->gi:Landroid/webkit/WebView;

    new-instance v1, Lcom/miui/internal/app/PolicyActivity$1;

    invoke-direct {v1, p0}, Lcom/miui/internal/app/PolicyActivity$1;-><init>(Lcom/miui/internal/app/PolicyActivity;)V

    invoke-virtual {v0, v1}, Landroid/webkit/WebView;->setWebViewClient(Landroid/webkit/WebViewClient;)V

    .line 144
    invoke-virtual {p0}, Lcom/miui/internal/app/PolicyActivity;->getIntent()Landroid/content/Intent;

    move-result-object v0

    .line 145
    const-string v1, "policytype"

    invoke-virtual {v0, v1}, Landroid/content/Intent;->getStringExtra(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    .line 146
    const-string v1, "packagename"

    invoke-virtual {v0, v1}, Landroid/content/Intent;->getStringExtra(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    .line 147
    const-string v1, "oldversion"

    invoke-virtual {v0, v1}, Landroid/content/Intent;->getStringExtra(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v4

    .line 148
    const-string v1, "newversion"

    invoke-virtual {v0, v1}, Landroid/content/Intent;->getStringExtra(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v5

    .line 149
    const-string v1, "showstyle"

    const/4 v6, 0x0

    invoke-virtual {v0, v1, v6}, Landroid/content/Intent;->getIntExtra(Ljava/lang/String;I)I

    move-result v1

    iput v1, p0, Lcom/miui/internal/app/PolicyActivity;->oS:I

    .line 150
    const-string v1, "applicationname"

    invoke-virtual {v0, v1}, Landroid/content/Intent;->getStringExtra(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    iput-object v1, p0, Lcom/miui/internal/app/PolicyActivity;->oU:Ljava/lang/String;

    .line 151
    const-string v1, "policyname"

    invoke-virtual {v0, v1}, Landroid/content/Intent;->getStringExtra(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/internal/app/PolicyActivity;->oV:Ljava/lang/String;

    .line 152
    iput-object v5, p0, Lcom/miui/internal/app/PolicyActivity;->oT:Ljava/lang/String;

    .line 154
    const-string v1, "https://api.sec.miui.com/policy/policies"

    move-object v0, p0

    invoke-direct/range {v0 .. v5}, Lcom/miui/internal/app/PolicyActivity;->a(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 155
    invoke-static {v0}, Lcom/miui/internal/app/PolicyActivity;->h(Ljava/lang/String;)V

    .line 157
    iget v1, p0, Lcom/miui/internal/app/PolicyActivity;->oS:I

    const/4 v2, 0x1

    if-ne v1, v2, :cond_0

    .line 158
    new-instance v1, Lcom/miui/internal/app/PolicyActivity$a;

    const/4 v2, 0x0

    invoke-direct {v1, p0, v2}, Lcom/miui/internal/app/PolicyActivity$a;-><init>(Lcom/miui/internal/app/PolicyActivity;Lcom/miui/internal/app/PolicyActivity$1;)V

    invoke-virtual {p0, v1}, Lcom/miui/internal/app/PolicyActivity;->startActionMode(Landroid/view/ActionMode$Callback;)Landroid/view/ActionMode;

    .line 160
    :cond_0
    invoke-static {}, Lmiui/net/ConnectivityHelper;->getInstance()Lmiui/net/ConnectivityHelper;

    move-result-object v1

    invoke-virtual {v1}, Lmiui/net/ConnectivityHelper;->isNetworkConnected()Z

    move-result v1

    if-eqz v1, :cond_1

    .line 161
    invoke-direct {p0, v0}, Lcom/miui/internal/app/PolicyActivity;->j(Ljava/lang/String;)V

    .line 166
    :goto_0
    return-void

    .line 163
    :cond_1
    invoke-direct {p0}, Lcom/miui/internal/app/PolicyActivity;->bc()V

    .line 164
    invoke-direct {p0}, Lcom/miui/internal/app/PolicyActivity;->bf()V

    goto :goto_0
.end method
