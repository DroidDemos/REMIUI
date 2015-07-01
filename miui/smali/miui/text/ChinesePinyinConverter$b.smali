.class Lmiui/text/ChinesePinyinConverter$b;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/text/ChinesePinyinConverter;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0xa
    name = "b"
.end annotation


# static fields
.field private static final QJ:Ljava/lang/String; = "pinyinindex.idf"


# instance fields
.field private yB:Lmiui/util/DirectIndexedFile$Reader;


# direct methods
.method private constructor <init>()V
    .locals 3

    .prologue
    .line 417
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 418
    invoke-static {}, Lcom/miui/internal/util/PackageConstants;->getCurrentApplication()Landroid/app/Application;

    move-result-object v0

    const-string v1, "pinyinindex.idf"

    invoke-static {v0, v1}, Lcom/miui/internal/util/DirectIndexedFileExtractor;->getDirectIndexedFilePath(Landroid/content/Context;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 421
    if-eqz v0, :cond_0

    new-instance v1, Ljava/io/File;

    invoke-direct {v1, v0}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    invoke-virtual {v1}, Ljava/io/File;->exists()Z

    move-result v1

    if-eqz v1, :cond_0

    .line 423
    :try_start_0
    invoke-static {v0}, Lmiui/util/DirectIndexedFile;->open(Ljava/lang/String;)Lmiui/util/DirectIndexedFile$Reader;

    move-result-object v0

    iput-object v0, p0, Lmiui/text/ChinesePinyinConverter$b;->yB:Lmiui/util/DirectIndexedFile$Reader;
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_0

    .line 429
    :cond_0
    :goto_0
    iget-object v0, p0, Lmiui/text/ChinesePinyinConverter$b;->yB:Lmiui/util/DirectIndexedFile$Reader;

    if-nez v0, :cond_1

    .line 431
    :try_start_1
    invoke-static {}, Lcom/miui/internal/util/PackageConstants;->getCurrentApplication()Landroid/app/Application;

    move-result-object v0

    invoke-virtual {v0}, Landroid/app/Application;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    invoke-virtual {v0}, Landroid/content/res/Resources;->getAssets()Landroid/content/res/AssetManager;

    move-result-object v0

    .line 432
    const-string v1, "pinyinindex.idf"

    const/4 v2, 0x1

    invoke-virtual {v0, v1, v2}, Landroid/content/res/AssetManager;->open(Ljava/lang/String;I)Ljava/io/InputStream;

    move-result-object v0

    invoke-static {v0}, Lmiui/util/DirectIndexedFile;->open(Ljava/io/InputStream;)Lmiui/util/DirectIndexedFile$Reader;

    move-result-object v0

    iput-object v0, p0, Lmiui/text/ChinesePinyinConverter$b;->yB:Lmiui/util/DirectIndexedFile$Reader;
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_1

    .line 438
    :cond_1
    :goto_1
    return-void

    .line 424
    :catch_0
    move-exception v0

    .line 425
    invoke-virtual {v0}, Ljava/io/IOException;->printStackTrace()V

    goto :goto_0

    .line 434
    :catch_1
    move-exception v0

    .line 435
    const-string v0, "ChinesePinyinConverter"

    const-string v1, "Init resource IOException"

    invoke-static {v0, v1}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    goto :goto_1
.end method

.method synthetic constructor <init>(Lmiui/text/a;)V
    .locals 0

    .prologue
    .line 412
    invoke-direct {p0}, Lmiui/text/ChinesePinyinConverter$b;-><init>()V

    return-void
.end method


# virtual methods
.method public a(C)[Ljava/lang/String;
    .locals 4

    .prologue
    const/4 v1, 0x0

    const/4 v3, 0x0

    .line 441
    iget-object v0, p0, Lmiui/text/ChinesePinyinConverter$b;->yB:Lmiui/util/DirectIndexedFile$Reader;

    if-nez v0, :cond_0

    move-object v0, v1

    .line 450
    :goto_0
    return-object v0

    .line 444
    :cond_0
    add-int/lit16 v0, p1, -0x4e00

    .line 445
    iget-object v2, p0, Lmiui/text/ChinesePinyinConverter$b;->yB:Lmiui/util/DirectIndexedFile$Reader;

    invoke-virtual {v2, v3, v0, v3}, Lmiui/util/DirectIndexedFile$Reader;->get(III)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/String;

    .line 446
    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v2

    if-eqz v2, :cond_1

    .line 447
    const-string v0, "ChinesePinyinConverter"

    const-string v2, "The ChinesePinyinConverter dictionary is not correct, need rebuild or reset the ROM."

    invoke-static {v0, v2}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    move-object v0, v1

    .line 448
    goto :goto_0

    .line 450
    :cond_1
    const-string v1, ","

    invoke-virtual {v0, v1}, Ljava/lang/String;->split(Ljava/lang/String;)[Ljava/lang/String;

    move-result-object v0

    goto :goto_0
.end method

.method protected finalize()V
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/Throwable;
        }
    .end annotation

    .prologue
    .line 456
    iget-object v0, p0, Lmiui/text/ChinesePinyinConverter$b;->yB:Lmiui/util/DirectIndexedFile$Reader;

    if-eqz v0, :cond_0

    .line 457
    iget-object v0, p0, Lmiui/text/ChinesePinyinConverter$b;->yB:Lmiui/util/DirectIndexedFile$Reader;

    invoke-virtual {v0}, Lmiui/util/DirectIndexedFile$Reader;->close()V

    .line 459
    :cond_0
    invoke-super {p0}, Ljava/lang/Object;->finalize()V

    .line 460
    return-void
.end method
