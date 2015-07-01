.class public Lcom/miui/internal/core/ManifestParser;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field private static final ELEMENT_DEPENDENCIES:Ljava/lang/String; = "dependencies"

.field private static final ELEMENT_DEPENDENCY:Ljava/lang/String; = "dependency"

.field private static final pR:Ljava/lang/String; = "com.miui.sdk.manifest"

.field private static final pS:Ljava/lang/String; = "miui_manifest"

.field private static final pT:Ljava/lang/String; = "manifest"

.field private static final pU:Ljava/lang/String; = "uses-sdk"


# instance fields
.field private J:Landroid/content/res/Resources;

.field private pV:Landroid/content/res/XmlResourceParser;


# direct methods
.method private constructor <init>(Landroid/content/res/Resources;Landroid/content/res/XmlResourceParser;)V
    .locals 0

    .prologue
    .line 37
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 38
    iput-object p1, p0, Lcom/miui/internal/core/ManifestParser;->J:Landroid/content/res/Resources;

    .line 39
    iput-object p2, p0, Lcom/miui/internal/core/ManifestParser;->pV:Landroid/content/res/XmlResourceParser;

    .line 40
    return-void
.end method

.method private a(Lcom/miui/internal/core/LevelInfo;)I
    .locals 1

    .prologue
    .line 175
    const/4 v0, 0x1

    return v0
.end method

.method private a(Lcom/miui/internal/core/Manifest;)Lcom/miui/internal/core/Manifest;
    .locals 2

    .prologue
    .line 164
    invoke-virtual {p1}, Lcom/miui/internal/core/Manifest;->getSdkDependency()Lcom/miui/internal/core/LevelInfo;

    move-result-object v0

    if-nez v0, :cond_0

    .line 165
    new-instance v0, Lcom/miui/internal/core/LevelInfo;

    invoke-direct {v0}, Lcom/miui/internal/core/LevelInfo;-><init>()V

    .line 166
    invoke-direct {p0, v0}, Lcom/miui/internal/core/ManifestParser;->a(Lcom/miui/internal/core/LevelInfo;)I

    move-result v1

    invoke-virtual {v0, v1}, Lcom/miui/internal/core/LevelInfo;->setMinLevel(I)V

    .line 167
    invoke-direct {p0, v0}, Lcom/miui/internal/core/ManifestParser;->b(Lcom/miui/internal/core/LevelInfo;)I

    move-result v1

    invoke-virtual {v0, v1}, Lcom/miui/internal/core/LevelInfo;->setTargetLevel(I)V

    .line 168
    invoke-direct {p0, v0}, Lcom/miui/internal/core/ManifestParser;->c(Lcom/miui/internal/core/LevelInfo;)I

    move-result v1

    invoke-virtual {v0, v1}, Lcom/miui/internal/core/LevelInfo;->setMaxLevel(I)V

    .line 169
    invoke-virtual {p1, v0}, Lcom/miui/internal/core/Manifest;->setSdkDependency(Lcom/miui/internal/core/LevelInfo;)V

    .line 171
    :cond_0
    return-object p1
.end method

.method private a(Lcom/miui/internal/core/Manifest;Landroid/content/res/Resources;Landroid/content/res/XmlResourceParser;)V
    .locals 5
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Lorg/xmlpull/v1/XmlPullParserException;,
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    const/4 v4, 0x3

    const/4 v2, 0x0

    const/4 v3, 0x1

    .line 101
    sget-object v0, Lmiui/R$styleable;->MiuiManifest:[I

    invoke-virtual {p2, p3, v0}, Landroid/content/res/Resources;->obtainAttributes(Landroid/util/AttributeSet;[I)Landroid/content/res/TypedArray;

    move-result-object v0

    .line 102
    invoke-virtual {v0, v2}, Landroid/content/res/TypedArray;->getString(I)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {p1, v1}, Lcom/miui/internal/core/Manifest;->setName(Ljava/lang/String;)V

    .line 103
    invoke-virtual {v0, v3, v2}, Landroid/content/res/TypedArray;->getInteger(II)I

    move-result v1

    invoke-virtual {p1, v1}, Lcom/miui/internal/core/Manifest;->setLevel(I)V

    .line 104
    const/4 v1, 0x2

    invoke-virtual {v0, v1, v3}, Landroid/content/res/TypedArray;->getBoolean(IZ)Z

    move-result v1

    invoke-virtual {p1, v1}, Lcom/miui/internal/core/Manifest;->setResourcesRequired(Z)V

    .line 105
    invoke-virtual {v0}, Landroid/content/res/TypedArray;->recycle()V

    .line 108
    invoke-interface {p3}, Landroid/content/res/XmlResourceParser;->getDepth()I

    move-result v0

    .line 110
    :cond_0
    :goto_0
    invoke-interface {p3}, Landroid/content/res/XmlResourceParser;->next()I

    move-result v1

    if-eq v1, v3, :cond_3

    if-ne v1, v4, :cond_1

    invoke-interface {p3}, Landroid/content/res/XmlResourceParser;->getDepth()I

    move-result v2

    if-le v2, v0, :cond_3

    .line 111
    :cond_1
    if-eq v1, v4, :cond_0

    const/4 v2, 0x4

    if-eq v1, v2, :cond_0

    .line 115
    invoke-interface {p3}, Landroid/content/res/XmlResourceParser;->getName()Ljava/lang/String;

    move-result-object v1

    .line 116
    const-string v2, "uses-sdk"

    invoke-virtual {v2, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_2

    .line 117
    invoke-direct {p0, p1, p2, p3}, Lcom/miui/internal/core/ManifestParser;->b(Lcom/miui/internal/core/Manifest;Landroid/content/res/Resources;Landroid/content/res/XmlResourceParser;)V

    goto :goto_0

    .line 118
    :cond_2
    const-string v2, "dependencies"

    invoke-virtual {v2, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_0

    .line 119
    invoke-direct {p0, p1, p2, p3}, Lcom/miui/internal/core/ManifestParser;->c(Lcom/miui/internal/core/Manifest;Landroid/content/res/Resources;Landroid/content/res/XmlResourceParser;)V

    goto :goto_0

    .line 122
    :cond_3
    return-void
.end method

.method private b(Lcom/miui/internal/core/LevelInfo;)I
    .locals 1

    .prologue
    .line 179
    invoke-virtual {p1}, Lcom/miui/internal/core/LevelInfo;->getMinLevel()I

    move-result v0

    return v0
.end method

.method private b(Lcom/miui/internal/core/Manifest;Landroid/content/res/Resources;Landroid/content/res/XmlResourceParser;)V
    .locals 4

    .prologue
    .line 125
    sget-object v0, Lmiui/R$styleable;->MiuiManifestUsesSdk:[I

    invoke-virtual {p2, p3, v0}, Landroid/content/res/Resources;->obtainAttributes(Landroid/util/AttributeSet;[I)Landroid/content/res/TypedArray;

    move-result-object v0

    .line 126
    new-instance v1, Lcom/miui/internal/core/LevelInfo;

    invoke-direct {v1}, Lcom/miui/internal/core/LevelInfo;-><init>()V

    .line 127
    const/4 v2, 0x0

    invoke-direct {p0, v1}, Lcom/miui/internal/core/ManifestParser;->a(Lcom/miui/internal/core/LevelInfo;)I

    move-result v3

    invoke-virtual {v0, v2, v3}, Landroid/content/res/TypedArray;->getInteger(II)I

    move-result v2

    invoke-virtual {v1, v2}, Lcom/miui/internal/core/LevelInfo;->setMinLevel(I)V

    .line 128
    const/4 v2, 0x1

    invoke-direct {p0, v1}, Lcom/miui/internal/core/ManifestParser;->b(Lcom/miui/internal/core/LevelInfo;)I

    move-result v3

    invoke-virtual {v0, v2, v3}, Landroid/content/res/TypedArray;->getInteger(II)I

    move-result v2

    invoke-virtual {v1, v2}, Lcom/miui/internal/core/LevelInfo;->setTargetLevel(I)V

    .line 129
    const/4 v2, 0x2

    invoke-direct {p0, v1}, Lcom/miui/internal/core/ManifestParser;->c(Lcom/miui/internal/core/LevelInfo;)I

    move-result v3

    invoke-virtual {v0, v2, v3}, Landroid/content/res/TypedArray;->getInteger(II)I

    move-result v2

    invoke-virtual {v1, v2}, Lcom/miui/internal/core/LevelInfo;->setMaxLevel(I)V

    .line 130
    invoke-virtual {p1, v1}, Lcom/miui/internal/core/Manifest;->setSdkDependency(Lcom/miui/internal/core/LevelInfo;)V

    .line 131
    invoke-virtual {v0}, Landroid/content/res/TypedArray;->recycle()V

    .line 132
    return-void
.end method

.method private c(Lcom/miui/internal/core/LevelInfo;)I
    .locals 1

    .prologue
    .line 183
    const v0, 0x7fffffff

    return v0
.end method

.method private c(Lcom/miui/internal/core/Manifest;Landroid/content/res/Resources;Landroid/content/res/XmlResourceParser;)V
    .locals 4
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Lorg/xmlpull/v1/XmlPullParserException;,
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    const/4 v3, 0x3

    .line 136
    .line 137
    invoke-interface {p3}, Landroid/content/res/XmlResourceParser;->getDepth()I

    move-result v0

    .line 139
    :cond_0
    :goto_0
    invoke-interface {p3}, Landroid/content/res/XmlResourceParser;->next()I

    move-result v1

    const/4 v2, 0x1

    if-eq v1, v2, :cond_2

    if-ne v1, v3, :cond_1

    invoke-interface {p3}, Landroid/content/res/XmlResourceParser;->getDepth()I

    move-result v2

    if-le v2, v0, :cond_2

    .line 140
    :cond_1
    if-eq v1, v3, :cond_0

    const/4 v2, 0x4

    if-eq v1, v2, :cond_0

    .line 144
    invoke-interface {p3}, Landroid/content/res/XmlResourceParser;->getName()Ljava/lang/String;

    move-result-object v1

    .line 145
    const-string v2, "dependency"

    invoke-virtual {v2, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_0

    .line 146
    invoke-direct {p0, p1, p2, p3}, Lcom/miui/internal/core/ManifestParser;->d(Lcom/miui/internal/core/Manifest;Landroid/content/res/Resources;Landroid/content/res/XmlResourceParser;)V

    goto :goto_0

    .line 149
    :cond_2
    return-void
.end method

.method public static create(Landroid/content/Context;)Lcom/miui/internal/core/ManifestParser;
    .locals 6

    .prologue
    .line 43
    const/4 v1, 0x0

    .line 45
    :try_start_0
    invoke-virtual {p0}, Landroid/content/Context;->getPackageManager()Landroid/content/pm/PackageManager;

    move-result-object v0

    invoke-virtual {p0}, Landroid/content/Context;->getPackageName()Ljava/lang/String;

    move-result-object v2

    const/16 v3, 0x80

    invoke-virtual {v0, v2, v3}, Landroid/content/pm/PackageManager;->getApplicationInfo(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;

    move-result-object v0

    iget-object v0, v0, Landroid/content/pm/ApplicationInfo;->metaData:Landroid/os/Bundle;

    .line 47
    if-eqz v0, :cond_1

    .line 48
    const-string v2, "com.miui.sdk.manifest"

    invoke-virtual {v0, v2}, Landroid/os/Bundle;->getInt(Ljava/lang/String;)I
    :try_end_0
    .catch Landroid/content/pm/PackageManager$NameNotFoundException; {:try_start_0 .. :try_end_0} :catch_0

    move-result v0

    .line 50
    :goto_0
    if-nez v0, :cond_0

    .line 51
    :try_start_1
    invoke-virtual {p0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    const-string v2, "miui_manifest"

    const-string v3, "xml"

    invoke-virtual {p0}, Landroid/content/Context;->getPackageName()Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v1, v2, v3, v4}, Landroid/content/res/Resources;->getIdentifier(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I
    :try_end_1
    .catch Landroid/content/pm/PackageManager$NameNotFoundException; {:try_start_1 .. :try_end_1} :catch_1

    move-result v0

    .line 56
    :cond_0
    :goto_1
    invoke-static {p0, v0}, Lcom/miui/internal/core/ManifestParser;->createFromResId(Landroid/content/Context;I)Lcom/miui/internal/core/ManifestParser;

    move-result-object v0

    return-object v0

    .line 53
    :catch_0
    move-exception v0

    move-object v5, v0

    move v0, v1

    move-object v1, v5

    .line 54
    :goto_2
    invoke-virtual {v1}, Landroid/content/pm/PackageManager$NameNotFoundException;->printStackTrace()V

    goto :goto_1

    .line 53
    :catch_1
    move-exception v1

    goto :goto_2

    :cond_1
    move v0, v1

    goto :goto_0
.end method

.method public static createFromResId(Landroid/content/Context;I)Lcom/miui/internal/core/ManifestParser;
    .locals 2

    .prologue
    .line 60
    invoke-virtual {p0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    .line 61
    if-nez p1, :cond_0

    const/4 v0, 0x0

    .line 62
    :goto_0
    invoke-static {v1, v0}, Lcom/miui/internal/core/ManifestParser;->createFromXmlParser(Landroid/content/res/Resources;Landroid/content/res/XmlResourceParser;)Lcom/miui/internal/core/ManifestParser;

    move-result-object v0

    return-object v0

    .line 61
    :cond_0
    invoke-virtual {v1, p1}, Landroid/content/res/Resources;->getXml(I)Landroid/content/res/XmlResourceParser;

    move-result-object v0

    goto :goto_0
.end method

.method public static createFromXmlParser(Landroid/content/res/Resources;Landroid/content/res/XmlResourceParser;)Lcom/miui/internal/core/ManifestParser;
    .locals 1

    .prologue
    .line 66
    new-instance v0, Lcom/miui/internal/core/ManifestParser;

    invoke-direct {v0, p0, p1}, Lcom/miui/internal/core/ManifestParser;-><init>(Landroid/content/res/Resources;Landroid/content/res/XmlResourceParser;)V

    return-object v0
.end method

.method private d(Lcom/miui/internal/core/Manifest;Landroid/content/res/Resources;Landroid/content/res/XmlResourceParser;)V
    .locals 5
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Lorg/xmlpull/v1/XmlPullParserException;,
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 153
    sget-object v0, Lmiui/R$styleable;->MiuiManifestDependency:[I

    invoke-virtual {p2, p3, v0}, Landroid/content/res/Resources;->obtainAttributes(Landroid/util/AttributeSet;[I)Landroid/content/res/TypedArray;

    move-result-object v0

    .line 154
    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Landroid/content/res/TypedArray;->getString(I)Ljava/lang/String;

    move-result-object v1

    .line 155
    new-instance v2, Lcom/miui/internal/core/LevelInfo;

    invoke-direct {v2}, Lcom/miui/internal/core/LevelInfo;-><init>()V

    .line 156
    const/4 v3, 0x1

    invoke-direct {p0, v2}, Lcom/miui/internal/core/ManifestParser;->a(Lcom/miui/internal/core/LevelInfo;)I

    move-result v4

    invoke-virtual {v0, v3, v4}, Landroid/content/res/TypedArray;->getInteger(II)I

    move-result v3

    invoke-virtual {v2, v3}, Lcom/miui/internal/core/LevelInfo;->setMinLevel(I)V

    .line 157
    const/4 v3, 0x2

    invoke-direct {p0, v2}, Lcom/miui/internal/core/ManifestParser;->b(Lcom/miui/internal/core/LevelInfo;)I

    move-result v4

    invoke-virtual {v0, v3, v4}, Landroid/content/res/TypedArray;->getInteger(II)I

    move-result v3

    invoke-virtual {v2, v3}, Lcom/miui/internal/core/LevelInfo;->setTargetLevel(I)V

    .line 158
    const/4 v3, 0x3

    invoke-direct {p0, v2}, Lcom/miui/internal/core/ManifestParser;->c(Lcom/miui/internal/core/LevelInfo;)I

    move-result v4

    invoke-virtual {v0, v3, v4}, Landroid/content/res/TypedArray;->getInteger(II)I

    move-result v3

    invoke-virtual {v2, v3}, Lcom/miui/internal/core/LevelInfo;->setMaxLevel(I)V

    .line 159
    invoke-virtual {p1, v1, v2}, Lcom/miui/internal/core/Manifest;->addDependency(Ljava/lang/String;Lcom/miui/internal/core/LevelInfo;)V

    .line 160
    invoke-virtual {v0}, Landroid/content/res/TypedArray;->recycle()V

    .line 161
    return-void
.end method


# virtual methods
.method public parse(Ljava/util/Map;)Lcom/miui/internal/core/Manifest;
    .locals 5
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/Map",
            "<",
            "Ljava/lang/String;",
            "Ljava/lang/Object;",
            ">;)",
            "Lcom/miui/internal/core/Manifest;"
        }
    .end annotation

    .prologue
    .line 70
    if-nez p1, :cond_0

    .line 71
    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    .line 73
    :cond_0
    new-instance v1, Lcom/miui/internal/core/Manifest;

    invoke-direct {v1}, Lcom/miui/internal/core/Manifest;-><init>()V

    .line 74
    iget-object v0, p0, Lcom/miui/internal/core/ManifestParser;->pV:Landroid/content/res/XmlResourceParser;

    if-eqz v0, :cond_4

    .line 76
    :try_start_0
    iget-object v0, p0, Lcom/miui/internal/core/ManifestParser;->J:Landroid/content/res/Resources;

    .line 77
    iget-object v2, p0, Lcom/miui/internal/core/ManifestParser;->pV:Landroid/content/res/XmlResourceParser;

    .line 80
    :cond_1
    invoke-interface {v2}, Landroid/content/res/XmlResourceParser;->next()I

    move-result v3

    const/4 v4, 0x2

    if-eq v3, v4, :cond_2

    const/4 v4, 0x1

    if-ne v3, v4, :cond_1

    .line 84
    :cond_2
    invoke-interface {v2}, Landroid/content/res/XmlResourceParser;->getName()Ljava/lang/String;

    move-result-object v3

    .line 85
    const-string v4, "manifest"

    invoke-virtual {v4, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_3

    .line 86
    invoke-direct {p0, v1, v0, v2}, Lcom/miui/internal/core/ManifestParser;->a(Lcom/miui/internal/core/Manifest;Landroid/content/res/Resources;Landroid/content/res/XmlResourceParser;)V
    :try_end_0
    .catch Lorg/xmlpull/v1/XmlPullParserException; {:try_start_0 .. :try_end_0} :catch_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_1
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 93
    :cond_3
    iget-object v0, p0, Lcom/miui/internal/core/ManifestParser;->pV:Landroid/content/res/XmlResourceParser;

    invoke-interface {v0}, Landroid/content/res/XmlResourceParser;->close()V

    .line 96
    :cond_4
    :goto_0
    invoke-direct {p0, v1}, Lcom/miui/internal/core/ManifestParser;->a(Lcom/miui/internal/core/Manifest;)Lcom/miui/internal/core/Manifest;

    move-result-object v0

    return-object v0

    .line 88
    :catch_0
    move-exception v0

    .line 89
    :try_start_1
    invoke-virtual {v0}, Lorg/xmlpull/v1/XmlPullParserException;->printStackTrace()V
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 93
    iget-object v0, p0, Lcom/miui/internal/core/ManifestParser;->pV:Landroid/content/res/XmlResourceParser;

    invoke-interface {v0}, Landroid/content/res/XmlResourceParser;->close()V

    goto :goto_0

    .line 90
    :catch_1
    move-exception v0

    .line 91
    :try_start_2
    invoke-virtual {v0}, Ljava/io/IOException;->printStackTrace()V
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    .line 93
    iget-object v0, p0, Lcom/miui/internal/core/ManifestParser;->pV:Landroid/content/res/XmlResourceParser;

    invoke-interface {v0}, Landroid/content/res/XmlResourceParser;->close()V

    goto :goto_0

    :catchall_0
    move-exception v0

    iget-object v1, p0, Lcom/miui/internal/core/ManifestParser;->pV:Landroid/content/res/XmlResourceParser;

    invoke-interface {v1}, Landroid/content/res/XmlResourceParser;->close()V

    throw v0
.end method
