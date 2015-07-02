.class public Lcom/miui/internal/provider/MiCloudSmsCmd;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field private static final TAG:Ljava/lang/String; = "MiCloudSmsCmd"

.field public static final TYPE_DISCARD_TOKEN:Ljava/lang/String; = "d"

.field public static final TYPE_LOCATION:Ljava/lang/String; = "l"

.field public static final TYPE_LOCK:Ljava/lang/String; = "k"

.field public static final TYPE_NOISE:Ljava/lang/String; = "n"

.field public static final TYPE_WIPE:Ljava/lang/String; = "w"

.field private static final ee:Ljava/lang/String; = "mfc"

.field private static final ef:Ljava/lang/String; = ","

.field private static final eg:Ljava/lang/String; = "##"

.field private static final eh:I = 0x0

.field private static final ei:I = 0x1

.field private static final ej:I = 0x2

.field private static final ek:I = 0x3

.field private static el:Ljava/lang/String;

.field private static em:[Z


# direct methods
.method static constructor <clinit>()V
    .locals 3

    .prologue
    const/4 v2, 0x1

    .line 25
    const-string v0, "AC/"

    sput-object v0, Lcom/miui/internal/provider/MiCloudSmsCmd;->el:Ljava/lang/String;

    .line 141
    const/16 v0, 0x7b

    new-array v0, v0, [Z

    sput-object v0, Lcom/miui/internal/provider/MiCloudSmsCmd;->em:[Z

    .line 143
    const/16 v0, 0x41

    :goto_0
    const/16 v1, 0x5a

    if-gt v0, v1, :cond_0

    .line 144
    sget-object v1, Lcom/miui/internal/provider/MiCloudSmsCmd;->em:[Z

    aput-boolean v2, v1, v0

    .line 143
    add-int/lit8 v0, v0, 0x1

    int-to-char v0, v0

    goto :goto_0

    .line 146
    :cond_0
    const/16 v0, 0x61

    :goto_1
    const/16 v1, 0x7a

    if-gt v0, v1, :cond_1

    .line 147
    sget-object v1, Lcom/miui/internal/provider/MiCloudSmsCmd;->em:[Z

    aput-boolean v2, v1, v0

    .line 146
    add-int/lit8 v0, v0, 0x1

    int-to-char v0, v0

    goto :goto_1

    .line 149
    :cond_1
    const/16 v0, 0x30

    :goto_2
    const/16 v1, 0x39

    if-gt v0, v1, :cond_2

    .line 150
    sget-object v1, Lcom/miui/internal/provider/MiCloudSmsCmd;->em:[Z

    aput-boolean v2, v1, v0

    .line 149
    add-int/lit8 v0, v0, 0x1

    int-to-char v0, v0

    goto :goto_2

    .line 152
    :cond_2
    return-void
.end method

.method private constructor <init>()V
    .locals 0

    .prologue
    .line 27
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 28
    return-void
.end method

.method private static a(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    .locals 3

    .prologue
    .line 76
    new-instance v1, Landroid/content/Intent;

    invoke-direct {v1}, Landroid/content/Intent;-><init>()V

    .line 77
    const-string v0, "android.intent.extra.device_msgId"

    invoke-virtual {v1, v0, p1}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;

    .line 78
    const-string v0, "android.intent.extra.device_time"

    invoke-virtual {v1, v0, p2}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;

    .line 79
    const-string v0, "android.intent.extra.device_digest"

    invoke-virtual {v1, v0, p5}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;

    .line 80
    const-string v0, "android.intent.extra.device_cmd"

    invoke-virtual {v1, v0, p3}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;

    .line 81
    const-string v0, "android.intent.extra.lock_password"

    invoke-virtual {v1, v0, p4}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;

    .line 82
    const-string v0, "android.intent.extra.from_address"

    invoke-virtual {v1, v0, p6}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;

    .line 84
    const/4 v0, 0x0

    .line 85
    const-string v2, "l"

    invoke-virtual {v2, p3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_2

    .line 86
    const-string v0, "miui.intent.action.REQUEST_LOCATION"

    .line 97
    :cond_0
    :goto_0
    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v2

    if-nez v2, :cond_1

    .line 98
    invoke-virtual {v1, v0}, Landroid/content/Intent;->setAction(Ljava/lang/String;)Landroid/content/Intent;

    .line 99
    const-string v0, "com.miui.cloudservice"

    invoke-virtual {v1, v0}, Landroid/content/Intent;->setPackage(Ljava/lang/String;)Landroid/content/Intent;

    .line 100
    invoke-virtual {p0, v1}, Landroid/content/Context;->startService(Landroid/content/Intent;)Landroid/content/ComponentName;

    .line 102
    :cond_1
    return-void

    .line 87
    :cond_2
    const-string v2, "n"

    invoke-virtual {v2, p3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_3

    .line 88
    const-string v0, "miui.intent.action.NOISE"

    goto :goto_0

    .line 89
    :cond_3
    const-string v2, "k"

    invoke-virtual {v2, p3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_4

    .line 90
    const-string v0, "miui.intent.action.LOCK_DEVICE"

    goto :goto_0

    .line 91
    :cond_4
    const-string v2, "w"

    invoke-virtual {v2, p3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_5

    .line 92
    const-string v0, "miui.intent.action.WIPE_DATA"

    goto :goto_0

    .line 93
    :cond_5
    const-string v2, "d"

    invoke-virtual {v2, p3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_0

    .line 94
    const-string v0, "miui.intent.action.DISCARD_FIND_DEVICE_TOKEN"

    goto :goto_0
.end method

.method private static a(Ljava/lang/String;II)Z
    .locals 2

    .prologue
    .line 155
    :goto_0
    if-ge p1, p2, :cond_2

    .line 156
    invoke-virtual {p0, p1}, Ljava/lang/String;->charAt(I)C

    move-result v0

    .line 157
    sget-object v1, Lcom/miui/internal/provider/MiCloudSmsCmd;->em:[Z

    array-length v1, v1

    if-ge v0, v1, :cond_0

    sget-object v1, Lcom/miui/internal/provider/MiCloudSmsCmd;->em:[Z

    aget-boolean v0, v1, v0

    if-nez v0, :cond_1

    .line 158
    :cond_0
    const/4 v0, 0x0

    .line 161
    :goto_1
    return v0

    .line 155
    :cond_1
    add-int/lit8 p1, p1, 0x1

    goto :goto_0

    .line 161
    :cond_2
    const/4 v0, 0x1

    goto :goto_1
.end method

.method public static checkAndDispatchActivationSms(Landroid/content/Context;ILjava/lang/String;Ljava/lang/String;)Z
    .locals 7

    .prologue
    const/4 v0, 0x0

    .line 105
    sget-object v1, Lcom/miui/internal/provider/MiCloudSmsCmd;->el:Ljava/lang/String;

    invoke-virtual {p3, v1}, Ljava/lang/String;->indexOf(Ljava/lang/String;)I

    move-result v1

    .line 106
    const/4 v2, -0x1

    if-ne v1, v2, :cond_0

    .line 138
    :goto_0
    return v0

    .line 109
    :cond_0
    const-string v2, "MiCloudSmsCmd"

    const-string v3, "checkAndDispatchActivationSms: The message looks like an activation"

    invoke-static {v2, v3}, Landroid/util/Log;->v(Ljava/lang/String;Ljava/lang/String;)I

    .line 110
    sget-object v2, Lcom/miui/internal/provider/MiCloudSmsCmd;->el:Ljava/lang/String;

    invoke-virtual {v2}, Ljava/lang/String;->length()I

    move-result v2

    add-int/2addr v1, v2

    .line 111
    add-int/lit8 v2, v1, 0x20

    .line 112
    add-int/lit8 v3, v2, 0x1

    .line 113
    add-int/lit8 v4, v3, 0xb

    .line 114
    invoke-virtual {p3}, Ljava/lang/String;->length()I

    move-result v5

    if-ge v5, v4, :cond_1

    .line 115
    const-string v1, "MiCloudSmsCmd"

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "checkAndDispatchActivationSms: length check failed, "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {p3}, Ljava/lang/String;->length()I

    move-result v3

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v2

    const-string v3, " < "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2, v4}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-static {v1, v2}, Landroid/util/Log;->v(Ljava/lang/String;Ljava/lang/String;)I

    goto :goto_0

    .line 118
    :cond_1
    invoke-static {p3, v1, v2}, Lcom/miui/internal/provider/MiCloudSmsCmd;->a(Ljava/lang/String;II)Z

    move-result v5

    if-nez v5, :cond_2

    .line 119
    const-string v1, "MiCloudSmsCmd"

    const-string v2, "checkAndDispatchActivationSms: left hex check failed"

    invoke-static {v1, v2}, Landroid/util/Log;->v(Ljava/lang/String;Ljava/lang/String;)I

    goto :goto_0

    .line 122
    :cond_2
    invoke-virtual {p3, v2}, Ljava/lang/String;->charAt(I)C

    move-result v5

    const/16 v6, 0x3a

    if-eq v5, v6, :cond_3

    .line 123
    const-string v1, "MiCloudSmsCmd"

    const-string v2, "checkAndDispatchActivationSms: colon check failed"

    invoke-static {v1, v2}, Landroid/util/Log;->v(Ljava/lang/String;Ljava/lang/String;)I

    goto :goto_0

    .line 126
    :cond_3
    invoke-static {p3, v3, v4}, Lcom/miui/internal/provider/MiCloudSmsCmd;->a(Ljava/lang/String;II)Z

    move-result v5

    if-nez v5, :cond_4

    .line 127
    const-string v1, "MiCloudSmsCmd"

    const-string v2, "checkAndDispatchActivationSms: right hex check failed"

    invoke-static {v1, v2}, Landroid/util/Log;->v(Ljava/lang/String;Ljava/lang/String;)I

    goto :goto_0

    .line 130
    :cond_4
    const-string v0, "MiCloudSmsCmd"

    const-string v5, "checkAndDispatchActivationSms: activation SMS acknowledged, broadcasting..."

    invoke-static {v0, v5}, Landroid/util/Log;->v(Ljava/lang/String;Ljava/lang/String;)I

    .line 131
    new-instance v0, Landroid/content/Intent;

    const-string v5, "com.xiaomi.action.ACTIVATION_SMS_RECEIVED"

    invoke-direct {v0, v5}, Landroid/content/Intent;-><init>(Ljava/lang/String;)V

    .line 132
    const-string v5, "extra_sim_index"

    invoke-virtual {v0, v5, p1}, Landroid/content/Intent;->putExtra(Ljava/lang/String;I)Landroid/content/Intent;

    .line 133
    const-string v5, "extra_address"

    invoke-virtual {v0, v5, p2}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;

    .line 134
    const-string v5, "extra_msg_id"

    invoke-virtual {p3, v3, v4}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v0, v5, v3}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;

    .line 135
    const-string v3, "extra_vkey1"

    invoke-virtual {p3, v1, v2}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v3, v1}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;

    .line 136
    const-string v1, "com.xiaomi.xmsf"

    invoke-virtual {v0, v1}, Landroid/content/Intent;->setPackage(Ljava/lang/String;)Landroid/content/Intent;

    .line 137
    invoke-virtual {p0, v0}, Landroid/content/Context;->sendBroadcast(Landroid/content/Intent;)V

    .line 138
    const/4 v0, 0x1

    goto/16 :goto_0
.end method

.method public static checkSmsCmd(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)Z
    .locals 10

    .prologue
    const/4 v9, 0x5

    const/4 v8, 0x4

    const/4 v7, 0x1

    const/4 v5, 0x0

    .line 48
    invoke-static {p2}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_0

    const-string v0, "mfc"

    invoke-virtual {p2, v0}, Ljava/lang/String;->indexOf(Ljava/lang/String;)I

    move-result v0

    if-ltz v0, :cond_0

    const-string v0, "##"

    invoke-virtual {p2, v0}, Ljava/lang/String;->indexOf(Ljava/lang/String;)I

    move-result v0

    if-gez v0, :cond_1

    :cond_0
    move v0, v5

    .line 72
    :goto_0
    return v0

    .line 52
    :cond_1
    const-string v0, ","

    invoke-virtual {p2, v0}, Ljava/lang/String;->split(Ljava/lang/String;)[Ljava/lang/String;

    move-result-object v0

    .line 53
    if-eqz v0, :cond_2

    array-length v1, v0

    if-lt v1, v9, :cond_2

    aget-object v1, v0, v5

    const-string v2, "mfc"

    invoke-virtual {v1, v2}, Ljava/lang/String;->indexOf(Ljava/lang/String;)I

    move-result v1

    if-gez v1, :cond_3

    :cond_2
    move v0, v5

    .line 54
    goto :goto_0

    .line 57
    :cond_3
    aget-object v3, v0, v7

    .line 58
    const/4 v1, 0x2

    aget-object v1, v0, v1

    .line 59
    const/4 v2, 0x3

    aget-object v2, v0, v2

    .line 61
    const/4 v4, 0x0

    .line 62
    const-string v6, "k"

    invoke-virtual {v6, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v6

    if-eqz v6, :cond_4

    .line 63
    aget-object v4, v0, v8

    .line 64
    aget-object v0, v0, v9

    .line 68
    :goto_1
    const-string v6, "##"

    invoke-virtual {v0, v6}, Ljava/lang/String;->lastIndexOf(Ljava/lang/String;)I

    move-result v6

    .line 69
    invoke-virtual {v0, v5, v6}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object v5

    move-object v0, p0

    move-object v6, p1

    .line 71
    invoke-static/range {v0 .. v6}, Lcom/miui/internal/provider/MiCloudSmsCmd;->a(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    move v0, v7

    .line 72
    goto :goto_0

    .line 66
    :cond_4
    aget-object v0, v0, v8

    goto :goto_1
.end method
