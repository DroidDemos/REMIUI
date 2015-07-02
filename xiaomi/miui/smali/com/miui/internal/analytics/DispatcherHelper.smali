.class public Lcom/miui/internal/analytics/DispatcherHelper;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/internal/analytics/DispatcherHelper$1;,
        Lcom/miui/internal/analytics/DispatcherHelper$c;,
        Lcom/miui/internal/analytics/DispatcherHelper$a;,
        Lcom/miui/internal/analytics/DispatcherHelper$b;
    }
.end annotation


# static fields
.field private static final APP_ID:Ljava/lang/String; = "app_id"

.field private static final TAG:Ljava/lang/String; = "DispatcherHelper"

.field private static final VERSION:Ljava/lang/String; = "version"

.field private static final fA:Ljava/lang/String; = "version_regex"

.field private static final fB:Ljava/lang/String; = "http://app.miui.com/mobile/analytics2.json"

.field private static final fC:J = 0x64L

.field private static final fD:Ljava/lang/String; = ".*"

.field private static final fE:Ljava/lang/String; = ".*"

.field private static final fF:Ljava/lang/String; = "normal"

.field private static final ft:Ljava/lang/String; = "servers"

.field private static final fu:Ljava/lang/String; = "server_name"

.field private static final fv:Ljava/lang/String; = "app_name"

.field private static final fw:Ljava/lang/String; = "probability"

.field private static final fx:Ljava/lang/String; = "items"

.field private static final fy:Ljava/lang/String; = "id_regex"

.field private static final fz:Ljava/lang/String; = "policy"


# instance fields
.field private fG:Lcom/miui/internal/analytics/DispatcherManager;

.field private fH:Lcom/miui/internal/analytics/DispatcherHelper$b;

.field private mContext:Landroid/content/Context;


# direct methods
.method public constructor <init>(Landroid/content/Context;)V
    .locals 2

    .prologue
    .line 111
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 112
    iput-object p1, p0, Lcom/miui/internal/analytics/DispatcherHelper;->mContext:Landroid/content/Context;

    .line 113
    new-instance v0, Lcom/miui/internal/analytics/DispatcherHelper$a;

    const/4 v1, 0x0

    invoke-direct {v0, p0, v1}, Lcom/miui/internal/analytics/DispatcherHelper$a;-><init>(Lcom/miui/internal/analytics/DispatcherHelper;Lcom/miui/internal/analytics/DispatcherHelper$1;)V

    iput-object v0, p0, Lcom/miui/internal/analytics/DispatcherHelper;->fH:Lcom/miui/internal/analytics/DispatcherHelper$b;

    .line 114
    new-instance v0, Lcom/miui/internal/analytics/DispatcherManager;

    iget-object v1, p0, Lcom/miui/internal/analytics/DispatcherHelper;->mContext:Landroid/content/Context;

    invoke-direct {v0, v1}, Lcom/miui/internal/analytics/DispatcherManager;-><init>(Landroid/content/Context;)V

    iput-object v0, p0, Lcom/miui/internal/analytics/DispatcherHelper;->fG:Lcom/miui/internal/analytics/DispatcherManager;

    .line 115
    return-void
.end method

.method private a(Lorg/json/JSONArray;Ljava/lang/String;Ljava/lang/String;J)Ljava/util/List;
    .locals 9
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lorg/json/JSONArray;",
            "Ljava/lang/String;",
            "Ljava/lang/String;",
            "J)",
            "Ljava/util/List",
            "<",
            "Lcom/miui/internal/analytics/Item;",
            ">;"
        }
    .end annotation

    .prologue
    .line 202
    new-instance v7, Ljava/util/ArrayList;

    invoke-direct {v7}, Ljava/util/ArrayList;-><init>()V

    .line 203
    invoke-virtual {p1}, Lorg/json/JSONArray;->length()I

    move-result v8

    .line 205
    const/4 v0, 0x0

    move v6, v0

    :goto_0
    if-ge v6, v8, :cond_0

    .line 206
    :try_start_0
    invoke-virtual {p1, v6}, Lorg/json/JSONArray;->getJSONObject(I)Lorg/json/JSONObject;

    move-result-object v4

    .line 207
    new-instance v0, Lcom/miui/internal/analytics/Item;

    const-string v1, "id_regex"

    invoke-virtual {v4, v1}, Lorg/json/JSONObject;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    const-string v2, "policy"

    invoke-virtual {v4, v2}, Lorg/json/JSONObject;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    const-string v3, "version_regex"

    invoke-virtual {v4, v3, p3}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    const-string v5, "probability"

    invoke-virtual {v4, v5, p4, p5}, Lorg/json/JSONObject;->optLong(Ljava/lang/String;J)J

    move-result-wide v4

    invoke-direct/range {v0 .. v5}, Lcom/miui/internal/analytics/Item;-><init>(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;J)V

    invoke-interface {v7, v0}, Ljava/util/List;->add(Ljava/lang/Object;)Z
    :try_end_0
    .catch Ljava/util/regex/PatternSyntaxException; {:try_start_0 .. :try_end_0} :catch_0
    .catch Lorg/json/JSONException; {:try_start_0 .. :try_end_0} :catch_1

    .line 205
    add-int/lit8 v0, v6, 0x1

    move v6, v0

    goto :goto_0

    .line 210
    :catch_0
    move-exception v0

    .line 211
    const-string v1, "DispatcherHelper"

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "Failed to compile items regex for app: "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-static {v1, v2, v0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    .line 216
    :cond_0
    :goto_1
    return-object v7

    .line 212
    :catch_1
    move-exception v0

    .line 213
    const-string v1, "DispatcherHelper"

    const-string v2, "JSONException catched when get item register"

    invoke-static {v1, v2, v0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    goto :goto_1
.end method

.method private a(Lorg/json/JSONArray;)Ljava/util/Map;
    .locals 6
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lorg/json/JSONArray;",
            ")",
            "Ljava/util/Map",
            "<",
            "Ljava/lang/String;",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation

    .prologue
    .line 186
    new-instance v1, Ljava/util/LinkedHashMap;

    invoke-direct {v1}, Ljava/util/LinkedHashMap;-><init>()V

    .line 187
    invoke-virtual {p1}, Lorg/json/JSONArray;->length()I

    move-result v2

    .line 189
    const/4 v0, 0x0

    :goto_0
    if-ge v0, v2, :cond_0

    .line 190
    :try_start_0
    invoke-virtual {p1, v0}, Lorg/json/JSONArray;->getJSONObject(I)Lorg/json/JSONObject;

    move-result-object v3

    .line 191
    const-string v4, "server_name"

    invoke-virtual {v3, v4}, Lorg/json/JSONObject;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v4

    const-string v5, "app_id"

    invoke-virtual {v3, v5}, Lorg/json/JSONObject;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    invoke-interface {v1, v4, v3}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    :try_end_0
    .catch Lorg/json/JSONException; {:try_start_0 .. :try_end_0} :catch_0

    .line 189
    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    .line 193
    :catch_0
    move-exception v0

    .line 194
    const-string v2, "DispatcherHelper"

    const-string v3, "JSONException catched when get servers"

    invoke-static {v2, v3, v0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    .line 197
    :cond_0
    return-object v1
.end method


# virtual methods
.method public dispatch()V
    .locals 15

    .prologue
    .line 137
    iget-object v0, p0, Lcom/miui/internal/analytics/DispatcherHelper;->mContext:Landroid/content/Context;

    invoke-static {v0}, Lcom/miui/internal/analytics/EventUtils;->enableWrite(Landroid/content/Context;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 138
    iget-object v0, p0, Lcom/miui/internal/analytics/DispatcherHelper;->fH:Lcom/miui/internal/analytics/DispatcherHelper$b;

    invoke-interface {v0}, Lcom/miui/internal/analytics/DispatcherHelper$b;->O()Lorg/json/JSONArray;

    move-result-object v13

    .line 139
    if-eqz v13, :cond_0

    .line 140
    invoke-virtual {v13}, Lorg/json/JSONArray;->length()I

    move-result v14

    .line 141
    const/4 v0, 0x0

    move v12, v0

    :goto_0
    if-ge v12, v14, :cond_0

    .line 143
    invoke-static {}, Lmiui/net/ConnectivityHelper;->getInstance()Lmiui/net/ConnectivityHelper;

    move-result-object v0

    invoke-virtual {v0}, Lmiui/net/ConnectivityHelper;->isUnmeteredNetworkConnected()Z

    move-result v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/internal/analytics/DispatcherHelper;->mContext:Landroid/content/Context;

    invoke-static {v0}, Lcom/miui/internal/analytics/EventUtils;->enableWrite(Landroid/content/Context;)Z

    move-result v0

    if-nez v0, :cond_1

    .line 182
    :cond_0
    return-void

    .line 148
    :cond_1
    :try_start_0
    invoke-virtual {v13, v12}, Lorg/json/JSONArray;->getJSONObject(I)Lorg/json/JSONObject;

    move-result-object v0

    .line 149
    const-wide/16 v4, 0x64

    .line 150
    const-string v3, ".*"
    :try_end_0
    .catch Lorg/json/JSONException; {:try_start_0 .. :try_end_0} :catch_1

    .line 152
    :try_start_1
    const-string v1, "probability"

    invoke-virtual {v0, v1}, Lorg/json/JSONObject;->getLong(Ljava/lang/String;)J
    :try_end_1
    .catch Lorg/json/JSONException; {:try_start_1 .. :try_end_1} :catch_3

    move-result-wide v4

    .line 157
    :goto_1
    :try_start_2
    const-string v1, "version"

    invoke-virtual {v0, v1}, Lorg/json/JSONObject;->getString(Ljava/lang/String;)Ljava/lang/String;
    :try_end_2
    .catch Lorg/json/JSONException; {:try_start_2 .. :try_end_2} :catch_2

    move-result-object v3

    .line 162
    :goto_2
    :try_start_3
    invoke-static {v3}, Ljava/util/regex/Pattern;->compile(Ljava/lang/String;)Ljava/util/regex/Pattern;

    move-result-object v1

    sget-object v2, Landroid/os/Build$VERSION;->INCREMENTAL:Ljava/lang/String;

    invoke-virtual {v1, v2}, Ljava/util/regex/Pattern;->matcher(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;

    move-result-object v1

    invoke-virtual {v1}, Ljava/util/regex/Matcher;->matches()Z

    move-result v1

    if-eqz v1, :cond_2

    .line 164
    iget-object v1, p0, Lcom/miui/internal/analytics/DispatcherHelper;->fG:Lcom/miui/internal/analytics/DispatcherManager;

    const-string v2, "servers"

    invoke-virtual {v0, v2}, Lorg/json/JSONObject;->getJSONArray(Ljava/lang/String;)Lorg/json/JSONArray;

    move-result-object v2

    invoke-direct {p0, v2}, Lcom/miui/internal/analytics/DispatcherHelper;->a(Lorg/json/JSONArray;)Ljava/util/Map;

    move-result-object v2

    invoke-virtual {v1, v2}, Lcom/miui/internal/analytics/DispatcherManager;->switchDispatcher(Ljava/util/Map;)V

    .line 166
    const-string v1, "app_name"

    invoke-virtual {v0, v1}, Lorg/json/JSONObject;->getString(Ljava/lang/String;)Ljava/lang/String;
    :try_end_3
    .catch Lorg/json/JSONException; {:try_start_3 .. :try_end_3} :catch_1

    move-result-object v2

    .line 169
    :try_start_4
    const-string v1, "items"

    invoke-virtual {v0, v1}, Lorg/json/JSONObject;->getJSONArray(Ljava/lang/String;)Lorg/json/JSONArray;

    move-result-object v1

    move-object v0, p0

    invoke-direct/range {v0 .. v5}, Lcom/miui/internal/analytics/DispatcherHelper;->a(Lorg/json/JSONArray;Ljava/lang/String;Ljava/lang/String;J)Ljava/util/List;
    :try_end_4
    .catch Lorg/json/JSONException; {:try_start_4 .. :try_end_4} :catch_0

    move-result-object v0

    .line 174
    :goto_3
    :try_start_5
    iget-object v1, p0, Lcom/miui/internal/analytics/DispatcherHelper;->fG:Lcom/miui/internal/analytics/DispatcherManager;

    invoke-virtual {v1, v2, v0}, Lcom/miui/internal/analytics/DispatcherManager;->dispatch(Ljava/lang/String;Ljava/util/List;)V

    .line 141
    :cond_2
    :goto_4
    add-int/lit8 v0, v12, 0x1

    move v12, v0

    goto :goto_0

    .line 170
    :catch_0
    move-exception v0

    .line 171
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    .line 172
    new-instance v6, Lcom/miui/internal/analytics/Item;

    const-string v7, ".*"

    const-string v8, "normal"

    move-object v9, v3

    move-wide v10, v4

    invoke-direct/range {v6 .. v11}, Lcom/miui/internal/analytics/Item;-><init>(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;J)V

    invoke-interface {v0, v6}, Ljava/util/List;->add(Ljava/lang/Object;)Z
    :try_end_5
    .catch Lorg/json/JSONException; {:try_start_5 .. :try_end_5} :catch_1

    goto :goto_3

    .line 176
    :catch_1
    move-exception v0

    .line 177
    const-string v1, "DispatcherHelper"

    const-string v2, "JSONException catched when dispatch events"

    invoke-static {v1, v2, v0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    goto :goto_4

    .line 158
    :catch_2
    move-exception v1

    goto :goto_2

    .line 153
    :catch_3
    move-exception v1

    goto :goto_1
.end method
