.class Lcom/miui/internal/analytics/DispatcherHelper$c;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/miui/internal/analytics/DispatcherHelper$b;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/internal/analytics/DispatcherHelper;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x2
    name = "c"
.end annotation


# instance fields
.field final synthetic dO:Lcom/miui/internal/analytics/DispatcherHelper;


# direct methods
.method private constructor <init>(Lcom/miui/internal/analytics/DispatcherHelper;)V
    .locals 0

    .prologue
    .line 97
    iput-object p1, p0, Lcom/miui/internal/analytics/DispatcherHelper$c;->dO:Lcom/miui/internal/analytics/DispatcherHelper;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public O()Lorg/json/JSONArray;
    .locals 3

    .prologue
    .line 100
    const-string v2, "[{server:\"google\", app_name:\"com.android.fileexplorer\", app_id:\"UA-33608129-1\", items:[{id_regex:\"capacity_total\", policy:\"last\"},{id_regex:\"capacity_used\", policy:\"normal\"}]}]"

    .line 101
    const/4 v1, 0x0

    .line 103
    :try_start_0
    new-instance v0, Lorg/json/JSONArray;

    invoke-direct {v0, v2}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V
    :try_end_0
    .catch Lorg/json/JSONException; {:try_start_0 .. :try_end_0} :catch_0

    .line 107
    :goto_0
    return-object v0

    .line 104
    :catch_0
    move-exception v0

    .line 105
    invoke-virtual {v0}, Lorg/json/JSONException;->printStackTrace()V

    move-object v0, v1

    goto :goto_0
.end method
