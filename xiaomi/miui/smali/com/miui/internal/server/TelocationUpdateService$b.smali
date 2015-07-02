.class Lcom/miui/internal/server/TelocationUpdateService$b;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/internal/server/TelocationUpdateService;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0xa
    name = "b"
.end annotation


# static fields
.field public static final Ub:I = 0x0

.field public static final Uc:I = 0x1

.field public static final Ud:I = 0x0

.field public static final Ue:I = 0x1


# instance fields
.field public Uf:Z

.field public Ug:I

.field public Uh:I

.field public Ui:J

.field public Uj:J

.field public Uk:Ljava/lang/String;

.field public Ul:Ljava/lang/String;

.field public Um:Ljava/lang/String;

.field public Un:Ljava/lang/String;

.field public Uo:J

.field public action:I

.field public description:Ljava/lang/String;


# direct methods
.method private constructor <init>()V
    .locals 0

    .prologue
    .line 364
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method public static c(Lorg/json/JSONObject;)Lcom/miui/internal/server/TelocationUpdateService$b;
    .locals 4
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Lorg/json/JSONException;
        }
    .end annotation

    .prologue
    .line 391
    new-instance v0, Lcom/miui/internal/server/TelocationUpdateService$b;

    invoke-direct {v0}, Lcom/miui/internal/server/TelocationUpdateService$b;-><init>()V

    .line 393
    const-string v1, "result"

    invoke-virtual {p0, v1}, Lorg/json/JSONObject;->optBoolean(Ljava/lang/String;)Z

    move-result v1

    iput-boolean v1, v0, Lcom/miui/internal/server/TelocationUpdateService$b;->Uf:Z

    .line 394
    const-string v1, "description"

    invoke-virtual {p0, v1}, Lorg/json/JSONObject;->optString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    iput-object v1, v0, Lcom/miui/internal/server/TelocationUpdateService$b;->description:Ljava/lang/String;

    .line 395
    iget-boolean v1, v0, Lcom/miui/internal/server/TelocationUpdateService$b;->Uf:Z

    if-eqz v1, :cond_0

    .line 396
    const-string v1, "action"

    invoke-virtual {p0, v1}, Lorg/json/JSONObject;->optInt(Ljava/lang/String;)I

    move-result v1

    iput v1, v0, Lcom/miui/internal/server/TelocationUpdateService$b;->action:I

    .line 398
    const-string v1, "info"

    invoke-virtual {p0, v1}, Lorg/json/JSONObject;->optJSONObject(Ljava/lang/String;)Lorg/json/JSONObject;

    move-result-object v1

    .line 399
    const-string v2, "serviceType"

    invoke-virtual {v1, v2}, Lorg/json/JSONObject;->optInt(Ljava/lang/String;)I

    move-result v2

    iput v2, v0, Lcom/miui/internal/server/TelocationUpdateService$b;->Ug:I

    .line 400
    const-string v2, "patchType"

    invoke-virtual {v1, v2}, Lorg/json/JSONObject;->optInt(Ljava/lang/String;)I

    move-result v2

    iput v2, v0, Lcom/miui/internal/server/TelocationUpdateService$b;->Uh:I

    .line 401
    const-string v2, "oldVersion"

    invoke-virtual {v1, v2}, Lorg/json/JSONObject;->optLong(Ljava/lang/String;)J

    move-result-wide v2

    iput-wide v2, v0, Lcom/miui/internal/server/TelocationUpdateService$b;->Ui:J

    .line 402
    const-string v2, "newVersion"

    invoke-virtual {v1, v2}, Lorg/json/JSONObject;->optLong(Ljava/lang/String;)J

    move-result-wide v2

    iput-wide v2, v0, Lcom/miui/internal/server/TelocationUpdateService$b;->Uj:J

    .line 403
    const-string v2, "oldMd5Sum"

    invoke-virtual {v1, v2}, Lorg/json/JSONObject;->optString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    iput-object v2, v0, Lcom/miui/internal/server/TelocationUpdateService$b;->Uk:Ljava/lang/String;

    .line 404
    const-string v2, "newMd5Sum"

    invoke-virtual {v1, v2}, Lorg/json/JSONObject;->optString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    iput-object v2, v0, Lcom/miui/internal/server/TelocationUpdateService$b;->Ul:Ljava/lang/String;

    .line 405
    const-string v2, "md5Sum"

    invoke-virtual {v1, v2}, Lorg/json/JSONObject;->optString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    iput-object v2, v0, Lcom/miui/internal/server/TelocationUpdateService$b;->Um:Ljava/lang/String;

    .line 406
    const-string v2, "fileURL"

    invoke-virtual {v1, v2}, Lorg/json/JSONObject;->optString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    iput-object v2, v0, Lcom/miui/internal/server/TelocationUpdateService$b;->Un:Ljava/lang/String;

    .line 407
    const-string v2, "fileSize"

    invoke-virtual {v1, v2}, Lorg/json/JSONObject;->optLong(Ljava/lang/String;)J

    move-result-wide v1

    iput-wide v1, v0, Lcom/miui/internal/server/TelocationUpdateService$b;->Uo:J

    .line 410
    :cond_0
    return-object v0
.end method
