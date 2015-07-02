.class Lcom/miui/internal/app/PolicyActivity$c;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/internal/app/PolicyActivity;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x2
    name = "c"
.end annotation


# instance fields
.field final synthetic ai:Lcom/miui/internal/app/PolicyActivity;

.field public eR:Ljava/lang/String;

.field public fm:Ljava/lang/String;

.field public fn:Ljava/lang/String;

.field public fo:Ljava/lang/String;

.field public fp:Ljava/lang/String;

.field public fq:Ljava/lang/String;

.field public fr:Ljava/lang/String;

.field public fs:Ljava/lang/String;

.field public name:Ljava/lang/String;


# direct methods
.method public constructor <init>(Lcom/miui/internal/app/PolicyActivity;Lorg/json/JSONObject;)V
    .locals 2
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Lorg/json/JSONException;
        }
    .end annotation

    .prologue
    .line 105
    iput-object p1, p0, Lcom/miui/internal/app/PolicyActivity$c;->ai:Lcom/miui/internal/app/PolicyActivity;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 106
    if-nez p2, :cond_0

    const-string v0, "null"

    :goto_0
    invoke-static {v0}, Lcom/miui/internal/app/PolicyActivity;->l(Ljava/lang/String;)V

    .line 107
    const-string v0, "pid"

    invoke-virtual {p2, v0}, Lorg/json/JSONObject;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/internal/app/PolicyActivity$c;->fm:Ljava/lang/String;

    .line 108
    const-string v0, "name"

    invoke-virtual {p2, v0}, Lorg/json/JSONObject;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/internal/app/PolicyActivity$c;->name:Ljava/lang/String;

    .line 109
    const-string v0, "lang"

    invoke-virtual {p2, v0}, Lorg/json/JSONObject;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/internal/app/PolicyActivity$c;->fn:Ljava/lang/String;

    .line 110
    const-string v0, "version"

    invoke-virtual {p2, v0}, Lorg/json/JSONObject;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/internal/app/PolicyActivity$c;->fo:Ljava/lang/String;

    .line 111
    const-string v0, "title"

    invoke-virtual {p2, v0}, Lorg/json/JSONObject;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/internal/app/PolicyActivity$c;->fp:Ljava/lang/String;

    .line 112
    const-string v0, "url"

    invoke-virtual {p2, v0}, Lorg/json/JSONObject;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/internal/app/PolicyActivity$c;->eR:Ljava/lang/String;

    .line 113
    const-string v0, "style"

    invoke-virtual {p2, v0}, Lorg/json/JSONObject;->getJSONObject(Ljava/lang/String;)Lorg/json/JSONObject;

    move-result-object v0

    .line 114
    const-string v1, "category"

    invoke-virtual {v0, v1}, Lorg/json/JSONObject;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    iput-object v1, p0, Lcom/miui/internal/app/PolicyActivity$c;->fq:Ljava/lang/String;

    .line 115
    const-string v1, "cancel"

    invoke-virtual {v0, v1}, Lorg/json/JSONObject;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    iput-object v1, p0, Lcom/miui/internal/app/PolicyActivity$c;->fr:Ljava/lang/String;

    .line 116
    const-string v1, "agree"

    invoke-virtual {v0, v1}, Lorg/json/JSONObject;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/internal/app/PolicyActivity$c;->fs:Ljava/lang/String;

    .line 117
    return-void

    .line 106
    :cond_0
    invoke-virtual {p2}, Lorg/json/JSONObject;->toString()Ljava/lang/String;

    move-result-object v0

    goto :goto_0
.end method
