.class public Lmiui/telephony/phonenumber/ChineseTelocationConverter;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field public static final AREACODE_INDEX:I = 0x1

.field private static final EMPTY:Ljava/lang/String; = ""

.field public static final LOCATION_INDEX:I = 0x0

.field public static final LOCATION_KIND:I = 0x0

.field private static final TAG:Ljava/lang/String; = "ChineseTelocation"

.field public static final UNIQUE_ID_NONE:I = 0x0

.field private static final mO:Ljava/lang/String; = "telocation.idf"

.field private static mP:Lmiui/telephony/phonenumber/ChineseTelocationConverter;


# instance fields
.field private mQ:Ljava/util/HashMap;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/HashMap",
            "<",
            "Ljava/lang/String;",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation
.end field

.field private mR:Lmiui/util/DirectIndexedFile$Reader;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .prologue
    .line 29
    new-instance v0, Lmiui/telephony/phonenumber/ChineseTelocationConverter;

    invoke-direct {v0}, Lmiui/telephony/phonenumber/ChineseTelocationConverter;-><init>()V

    sput-object v0, Lmiui/telephony/phonenumber/ChineseTelocationConverter;->mP:Lmiui/telephony/phonenumber/ChineseTelocationConverter;

    return-void
.end method

.method private constructor <init>()V
    .locals 4

    .prologue
    .line 35
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 31
    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    iput-object v0, p0, Lmiui/telephony/phonenumber/ChineseTelocationConverter;->mQ:Ljava/util/HashMap;

    .line 36
    invoke-static {}, Lcom/miui/internal/util/PackageConstants;->getCurrentApplication()Landroid/app/Application;

    move-result-object v0

    const-string v1, "telocation.idf"

    invoke-static {v0, v1}, Lcom/miui/internal/util/DirectIndexedFileExtractor;->getDirectIndexedFilePath(Landroid/content/Context;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 39
    if-eqz v0, :cond_0

    new-instance v1, Ljava/io/File;

    invoke-direct {v1, v0}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    invoke-virtual {v1}, Ljava/io/File;->exists()Z

    move-result v1

    if-eqz v1, :cond_0

    .line 41
    :try_start_0
    invoke-static {v0}, Lmiui/util/DirectIndexedFile;->open(Ljava/lang/String;)Lmiui/util/DirectIndexedFile$Reader;

    move-result-object v1

    iput-object v1, p0, Lmiui/telephony/phonenumber/ChineseTelocationConverter;->mR:Lmiui/util/DirectIndexedFile$Reader;

    .line 42
    const-string v1, "ChineseTelocation"

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "Read Telocation from "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-static {v1, v0}, Landroid/util/Log;->v(Ljava/lang/String;Ljava/lang/String;)I
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_0

    .line 48
    :cond_0
    :goto_0
    iget-object v0, p0, Lmiui/telephony/phonenumber/ChineseTelocationConverter;->mR:Lmiui/util/DirectIndexedFile$Reader;

    if-nez v0, :cond_1

    .line 50
    :try_start_1
    invoke-static {}, Lcom/miui/internal/util/PackageConstants;->getCurrentApplication()Landroid/app/Application;

    move-result-object v0

    invoke-virtual {v0}, Landroid/app/Application;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    invoke-virtual {v0}, Landroid/content/res/Resources;->getAssets()Landroid/content/res/AssetManager;

    move-result-object v0

    .line 51
    const-string v1, "telocation.idf"

    const/4 v2, 0x1

    invoke-virtual {v0, v1, v2}, Landroid/content/res/AssetManager;->open(Ljava/lang/String;I)Ljava/io/InputStream;

    move-result-object v0

    invoke-static {v0}, Lmiui/util/DirectIndexedFile;->open(Ljava/io/InputStream;)Lmiui/util/DirectIndexedFile$Reader;

    move-result-object v0

    iput-object v0, p0, Lmiui/telephony/phonenumber/ChineseTelocationConverter;->mR:Lmiui/util/DirectIndexedFile$Reader;

    .line 53
    const-string v0, "ChineseTelocation"

    const-string v1, "Read Telocation from assets"

    invoke-static {v0, v1}, Landroid/util/Log;->v(Ljava/lang/String;Ljava/lang/String;)I
    :try_end_1
    .catch Ljava/io/IOException; {:try_start_1 .. :try_end_1} :catch_1

    .line 58
    :cond_1
    :goto_1
    return-void

    .line 43
    :catch_0
    move-exception v0

    .line 44
    invoke-virtual {v0}, Ljava/io/IOException;->printStackTrace()V

    goto :goto_0

    .line 54
    :catch_1
    move-exception v0

    .line 55
    const-string v0, "ChineseTelocation"

    const-string v1, "Can\'t read telocation.idf, NO mobile telocation supported!"

    invoke-static {v0, v1}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    goto :goto_1
.end method

.method public static getInstance()Lmiui/telephony/phonenumber/ChineseTelocationConverter;
    .locals 1

    .prologue
    .line 70
    sget-object v0, Lmiui/telephony/phonenumber/ChineseTelocationConverter;->mP:Lmiui/telephony/phonenumber/ChineseTelocationConverter;

    return-object v0
.end method

.method public static reloadInstance()V
    .locals 1

    .prologue
    .line 78
    new-instance v0, Lmiui/telephony/phonenumber/ChineseTelocationConverter;

    invoke-direct {v0}, Lmiui/telephony/phonenumber/ChineseTelocationConverter;-><init>()V

    sput-object v0, Lmiui/telephony/phonenumber/ChineseTelocationConverter;->mP:Lmiui/telephony/phonenumber/ChineseTelocationConverter;

    .line 79
    return-void
.end method


# virtual methods
.method protected finalize()V
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/Throwable;
        }
    .end annotation

    .prologue
    .line 62
    iget-object v0, p0, Lmiui/telephony/phonenumber/ChineseTelocationConverter;->mR:Lmiui/util/DirectIndexedFile$Reader;

    if-eqz v0, :cond_0

    .line 63
    iget-object v0, p0, Lmiui/telephony/phonenumber/ChineseTelocationConverter;->mR:Lmiui/util/DirectIndexedFile$Reader;

    invoke-virtual {v0}, Lmiui/util/DirectIndexedFile$Reader;->close()V

    .line 66
    :cond_0
    invoke-super {p0}, Ljava/lang/Object;->finalize()V

    .line 67
    return-void
.end method

.method public getAreaCode(Landroid/location/Address;)Ljava/lang/String;
    .locals 6

    .prologue
    const/4 v0, 0x0

    .line 168
    if-eqz p1, :cond_0

    iget-object v1, p0, Lmiui/telephony/phonenumber/ChineseTelocationConverter;->mR:Lmiui/util/DirectIndexedFile$Reader;

    if-nez v1, :cond_1

    .line 169
    :cond_0
    const-string v0, ""

    .line 200
    :goto_0
    return-object v0

    .line 172
    :cond_1
    iget-object v2, p0, Lmiui/telephony/phonenumber/ChineseTelocationConverter;->mQ:Ljava/util/HashMap;

    monitor-enter v2

    .line 173
    :try_start_0
    iget-object v1, p0, Lmiui/telephony/phonenumber/ChineseTelocationConverter;->mQ:Ljava/util/HashMap;

    invoke-virtual {v1}, Ljava/util/HashMap;->size()I

    move-result v1

    if-nez v1, :cond_3

    move v1, v0

    .line 174
    :goto_1
    const/16 v0, 0x3e8

    if-ge v1, v0, :cond_3

    .line 175
    iget-object v0, p0, Lmiui/telephony/phonenumber/ChineseTelocationConverter;->mR:Lmiui/util/DirectIndexedFile$Reader;

    const/4 v3, 0x0

    const/4 v4, 0x0

    invoke-virtual {v0, v3, v1, v4}, Lmiui/util/DirectIndexedFile$Reader;->get(III)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/String;

    .line 176
    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v3

    if-nez v3, :cond_2

    .line 177
    iget-object v3, p0, Lmiui/telephony/phonenumber/ChineseTelocationConverter;->mQ:Ljava/util/HashMap;

    invoke-static {v1}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v3, v4, v0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 174
    :cond_2
    add-int/lit8 v0, v1, 0x1

    move v1, v0

    goto :goto_1

    .line 181
    :cond_3
    monitor-exit v2
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 183
    invoke-virtual {p1}, Landroid/location/Address;->getAdminArea()Ljava/lang/String;

    move-result-object v0

    .line 184
    invoke-virtual {p1}, Landroid/location/Address;->getLocality()Ljava/lang/String;

    move-result-object v1

    .line 185
    const-string v2, "ChineseTelocation"

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    const-string v4, "adminArea:"

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    const-string v4, " locality:"

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-static {v2, v3}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 186
    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v2

    if-nez v2, :cond_5

    invoke-static {v1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v2

    if-nez v2, :cond_5

    .line 188
    const-string v2, "\u7701"

    const-string v3, ""

    invoke-virtual {v0, v2, v3}, Ljava/lang/String;->replace(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;

    move-result-object v0

    .line 189
    const-string v2, "\u5e02"

    const-string v3, ""

    invoke-virtual {v0, v2, v3}, Ljava/lang/String;->replace(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;

    move-result-object v2

    .line 190
    const-string v0, "\u5e02"

    const-string v3, ""

    invoke-virtual {v1, v0, v3}, Ljava/lang/String;->replace(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;

    move-result-object v0

    .line 191
    const-string v1, "\u533a"

    const-string v3, ""

    invoke-virtual {v0, v1, v3}, Ljava/lang/String;->replace(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;

    move-result-object v0

    .line 192
    const-string v1, "\u53bf"

    const-string v3, ""

    invoke-virtual {v0, v1, v3}, Ljava/lang/String;->replace(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;

    move-result-object v3

    .line 193
    iget-object v0, p0, Lmiui/telephony/phonenumber/ChineseTelocationConverter;->mQ:Ljava/util/HashMap;

    invoke-virtual {v0}, Ljava/util/HashMap;->entrySet()Ljava/util/Set;

    move-result-object v0

    invoke-interface {v0}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v4

    :cond_4
    invoke-interface {v4}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_5

    invoke-interface {v4}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/util/Map$Entry;

    .line 194
    invoke-interface {v0}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/lang/String;

    .line 195
    invoke-virtual {v1, v2}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v5

    if-eqz v5, :cond_4

    invoke-virtual {v1, v3}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v1

    if-eqz v1, :cond_4

    .line 196
    invoke-interface {v0}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/String;

    goto/16 :goto_0

    .line 181
    :catchall_0
    move-exception v0

    :try_start_1
    monitor-exit v2
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw v0

    .line 200
    :cond_5
    const-string v0, ""

    goto/16 :goto_0
.end method

.method public getAreaCode(Ljava/lang/CharSequence;II)Ljava/lang/String;
    .locals 4

    .prologue
    const/4 v3, 0x1

    .line 146
    iget-object v0, p0, Lmiui/telephony/phonenumber/ChineseTelocationConverter;->mR:Lmiui/util/DirectIndexedFile$Reader;

    if-nez v0, :cond_0

    .line 147
    const-string v0, ""

    .line 150
    :goto_0
    return-object v0

    .line 149
    :cond_0
    invoke-virtual {p0, p1, p2, p3, v3}, Lmiui/telephony/phonenumber/ChineseTelocationConverter;->getUniqId(Ljava/lang/CharSequence;IIZ)I

    move-result v0

    .line 150
    iget-object v1, p0, Lmiui/telephony/phonenumber/ChineseTelocationConverter;->mR:Lmiui/util/DirectIndexedFile$Reader;

    const/4 v2, 0x0

    invoke-virtual {v1, v2, v0, v3}, Lmiui/util/DirectIndexedFile$Reader;->get(III)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/String;

    goto :goto_0
.end method

.method public getLocation(Ljava/lang/CharSequence;IIZ)Ljava/lang/String;
    .locals 3

    .prologue
    const/4 v2, 0x0

    .line 126
    iget-object v0, p0, Lmiui/telephony/phonenumber/ChineseTelocationConverter;->mR:Lmiui/util/DirectIndexedFile$Reader;

    if-nez v0, :cond_0

    .line 127
    const-string v0, ""

    .line 141
    :goto_0
    return-object v0

    .line 131
    :cond_0
    if-nez p4, :cond_1

    .line 132
    const-string v0, ""

    goto :goto_0

    .line 135
    :cond_1
    const/4 v0, 0x1

    invoke-virtual {p0, p1, p2, p3, v0}, Lmiui/telephony/phonenumber/ChineseTelocationConverter;->getUniqId(Ljava/lang/CharSequence;IIZ)I

    move-result v0

    .line 137
    if-gtz v0, :cond_2

    .line 138
    const-string v0, ""

    goto :goto_0

    .line 141
    :cond_2
    iget-object v1, p0, Lmiui/telephony/phonenumber/ChineseTelocationConverter;->mR:Lmiui/util/DirectIndexedFile$Reader;

    invoke-virtual {v1, v2, v0, v2}, Lmiui/util/DirectIndexedFile$Reader;->get(III)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/String;

    goto :goto_0
.end method

.method public getUniqId(Ljava/lang/CharSequence;IIZ)I
    .locals 6

    .prologue
    const/16 v1, 0xa

    const/4 v0, 0x0

    const/16 v5, 0x30

    .line 82
    if-lez p3, :cond_0

    invoke-interface {p1, p2}, Ljava/lang/CharSequence;->charAt(I)C

    move-result v2

    if-ne v2, v5, :cond_0

    .line 83
    add-int/lit8 p2, p2, 0x1

    .line 84
    add-int/lit8 p3, p3, -0x1

    .line 86
    :cond_0
    const/4 v2, 0x1

    if-gt p3, v2, :cond_2

    .line 121
    :cond_1
    :goto_0
    :pswitch_0
    return v0

    .line 91
    :cond_2
    invoke-interface {p1, p2}, Ljava/lang/CharSequence;->charAt(I)C

    move-result v2

    packed-switch v2, :pswitch_data_0

    .line 115
    const/4 v1, 0x2

    if-le p3, v1, :cond_1

    .line 116
    invoke-interface {p1, p2}, Ljava/lang/CharSequence;->charAt(I)C

    move-result v0

    add-int/lit8 v0, v0, -0x30

    mul-int/lit8 v0, v0, 0xa

    add-int/lit8 v1, p2, 0x1

    invoke-interface {p1, v1}, Ljava/lang/CharSequence;->charAt(I)C

    move-result v1

    add-int/lit8 v1, v1, -0x30

    add-int/2addr v0, v1

    mul-int/lit8 v0, v0, 0xa

    add-int/lit8 v1, p2, 0x2

    invoke-interface {p1, v1}, Ljava/lang/CharSequence;->charAt(I)C

    move-result v1

    add-int/lit8 v1, v1, -0x30

    add-int/2addr v0, v1

    .line 118
    goto :goto_0

    .line 95
    :pswitch_1
    add-int/lit8 v2, p2, 0x1

    invoke-interface {p1, v2}, Ljava/lang/CharSequence;->charAt(I)C

    move-result v2

    if-ne v2, v5, :cond_3

    move v0, v1

    .line 96
    goto :goto_0

    .line 97
    :cond_3
    if-eqz p4, :cond_1

    const/4 v2, 0x6

    if-le p3, v2, :cond_1

    .line 98
    const v2, 0xf4240

    add-int/lit8 v3, p2, 0x1

    invoke-interface {p1, v3}, Ljava/lang/CharSequence;->charAt(I)C

    move-result v3

    add-int/lit8 v3, v3, -0x30

    const v4, 0x186a0

    mul-int/2addr v3, v4

    add-int/2addr v2, v3

    add-int/lit8 v3, p2, 0x2

    invoke-interface {p1, v3}, Ljava/lang/CharSequence;->charAt(I)C

    move-result v3

    add-int/lit8 v3, v3, -0x30

    mul-int/lit16 v3, v3, 0x2710

    add-int/2addr v2, v3

    add-int/lit8 v3, p2, 0x3

    invoke-interface {p1, v3}, Ljava/lang/CharSequence;->charAt(I)C

    move-result v3

    add-int/lit8 v3, v3, -0x30

    mul-int/lit16 v3, v3, 0x3e8

    add-int/2addr v2, v3

    add-int/lit8 v3, p2, 0x4

    invoke-interface {p1, v3}, Ljava/lang/CharSequence;->charAt(I)C

    move-result v3

    add-int/lit8 v3, v3, -0x30

    mul-int/lit8 v3, v3, 0x64

    add-int/2addr v2, v3

    add-int/lit8 v3, p2, 0x5

    invoke-interface {p1, v3}, Ljava/lang/CharSequence;->charAt(I)C

    move-result v3

    add-int/lit8 v3, v3, -0x30

    mul-int/lit8 v3, v3, 0xa

    add-int/2addr v2, v3

    add-int/lit8 v3, p2, 0x6

    invoke-interface {p1, v3}, Ljava/lang/CharSequence;->charAt(I)C

    move-result v3

    add-int/lit8 v3, v3, -0x30

    add-int/2addr v2, v3

    .line 102
    const v3, 0x150ead

    if-ne v2, v3, :cond_4

    if-le p3, v1, :cond_4

    add-int/lit8 v1, p2, 0x7

    invoke-interface {p1, v1}, Ljava/lang/CharSequence;->charAt(I)C

    move-result v1

    const/16 v3, 0x38

    if-ne v1, v3, :cond_4

    add-int/lit8 v1, p2, 0x8

    invoke-interface {p1, v1}, Ljava/lang/CharSequence;->charAt(I)C

    move-result v1

    if-ne v1, v5, :cond_4

    add-int/lit8 v1, p2, 0x9

    invoke-interface {p1, v1}, Ljava/lang/CharSequence;->charAt(I)C

    move-result v1

    if-ne v1, v5, :cond_4

    add-int/lit8 v1, p2, 0xa

    invoke-interface {p1, v1}, Ljava/lang/CharSequence;->charAt(I)C

    move-result v1

    if-eq v1, v5, :cond_1

    :cond_4
    move v0, v2

    .line 108
    goto/16 :goto_0

    .line 112
    :pswitch_2
    add-int/lit8 v0, p2, 0x1

    invoke-interface {p1, v0}, Ljava/lang/CharSequence;->charAt(I)C

    move-result v0

    add-int/lit8 v0, v0, -0x30

    add-int/lit8 v0, v0, 0x14

    .line 113
    goto/16 :goto_0

    .line 91
    :pswitch_data_0
    .packed-switch 0x30
        :pswitch_0
        :pswitch_1
        :pswitch_2
    .end packed-switch
.end method

.method public getVersion()I
    .locals 1

    .prologue
    .line 204
    iget-object v0, p0, Lmiui/telephony/phonenumber/ChineseTelocationConverter;->mR:Lmiui/util/DirectIndexedFile$Reader;

    invoke-virtual {v0}, Lmiui/util/DirectIndexedFile$Reader;->getDataVersion()I

    move-result v0

    return v0
.end method

.method public parseAreaCode(Ljava/lang/CharSequence;II)Ljava/lang/String;
    .locals 4

    .prologue
    const/4 v3, 0x0

    .line 155
    iget-object v0, p0, Lmiui/telephony/phonenumber/ChineseTelocationConverter;->mR:Lmiui/util/DirectIndexedFile$Reader;

    if-nez v0, :cond_0

    .line 156
    const-string v0, ""

    .line 159
    :goto_0
    return-object v0

    .line 158
    :cond_0
    invoke-virtual {p0, p1, p2, p3, v3}, Lmiui/telephony/phonenumber/ChineseTelocationConverter;->getUniqId(Ljava/lang/CharSequence;IIZ)I

    move-result v0

    .line 159
    iget-object v1, p0, Lmiui/telephony/phonenumber/ChineseTelocationConverter;->mR:Lmiui/util/DirectIndexedFile$Reader;

    const/4 v2, 0x1

    invoke-virtual {v1, v3, v0, v2}, Lmiui/util/DirectIndexedFile$Reader;->get(III)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/String;

    goto :goto_0
.end method
