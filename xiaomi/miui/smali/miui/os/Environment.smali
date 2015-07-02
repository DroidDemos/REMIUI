.class public Lmiui/os/Environment;
.super Landroid/os/Environment;
.source "SourceFile"


# static fields
.field private static final mF:Lcom/miui/internal/variable/Android_Os_Process_class;

.field private static final mG:Ljava/lang/String; = "MIUI"

.field private static final mH:Ljava/io/File;

.field private static final mI:Ljava/io/File;

.field private static final mJ:Ljava/io/File;

.field private static final mK:Ljava/io/File;

.field private static final mL:Ljava/io/File;

.field private static mM:I


# direct methods
.method static constructor <clinit>()V
    .locals 3

    .prologue
    .line 26
    invoke-static {}, Lcom/miui/internal/variable/Android_Os_Process_class$Factory;->getInstance()Lcom/miui/internal/variable/Android_Os_Process_class$Factory;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/internal/variable/Android_Os_Process_class$Factory;->get()Lcom/miui/internal/variable/Android_Os_Process_class;

    move-result-object v0

    sput-object v0, Lmiui/os/Environment;->mF:Lcom/miui/internal/variable/Android_Os_Process_class;

    .line 31
    new-instance v0, Ljava/io/File;

    invoke-static {}, Lmiui/os/Environment;->getExternalStorageDirectory()Ljava/io/File;

    move-result-object v1

    const-string v2, "MIUI"

    invoke-direct {v0, v1, v2}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    sput-object v0, Lmiui/os/Environment;->mH:Ljava/io/File;

    .line 34
    new-instance v0, Ljava/io/File;

    const-string v1, "/data/miui/"

    invoke-direct {v0, v1}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    sput-object v0, Lmiui/os/Environment;->mI:Ljava/io/File;

    .line 36
    new-instance v0, Ljava/io/File;

    invoke-static {}, Lmiui/os/Environment;->getMiuiDataDirectory()Ljava/io/File;

    move-result-object v1

    const-string v2, "apps"

    invoke-direct {v0, v1, v2}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    sput-object v0, Lmiui/os/Environment;->mJ:Ljava/io/File;

    .line 38
    new-instance v0, Ljava/io/File;

    invoke-static {}, Lmiui/os/Environment;->getMiuiDataDirectory()Ljava/io/File;

    move-result-object v1

    const-string v2, "preset_apps"

    invoke-direct {v0, v1, v2}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    sput-object v0, Lmiui/os/Environment;->mK:Ljava/io/File;

    .line 40
    new-instance v0, Ljava/io/File;

    invoke-static {}, Lmiui/os/Environment;->getMiuiDataDirectory()Ljava/io/File;

    move-result-object v1

    const-string v2, "current"

    invoke-direct {v0, v1, v2}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    sput-object v0, Lmiui/os/Environment;->mL:Ljava/io/File;

    .line 77
    const/4 v0, 0x0

    sput v0, Lmiui/os/Environment;->mM:I

    return-void
.end method

.method protected constructor <init>()V
    .locals 2
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/InstantiationException;
        }
    .end annotation

    .prologue
    .line 22
    invoke-direct {p0}, Landroid/os/Environment;-><init>()V

    .line 23
    new-instance v0, Ljava/lang/InstantiationException;

    const-string v1, "Cannot instantiate utility class"

    invoke-direct {v0, v1}, Ljava/lang/InstantiationException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

.method public static getCpuCount()I
    .locals 6

    .prologue
    const/4 v0, 0x0

    .line 81
    sget v1, Lmiui/os/Environment;->mM:I

    if-nez v1, :cond_2

    .line 82
    const-string v1, "cpu[0-9]*"

    invoke-static {v1}, Ljava/util/regex/Pattern;->compile(Ljava/lang/String;)Ljava/util/regex/Pattern;

    move-result-object v2

    .line 84
    new-instance v1, Ljava/io/File;

    const-string v3, "/sys/devices/system/cpu/"

    invoke-direct {v1, v3}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    invoke-virtual {v1}, Ljava/io/File;->list()[Ljava/lang/String;

    move-result-object v3

    array-length v4, v3

    move v1, v0

    :goto_0
    if-ge v1, v4, :cond_1

    aget-object v5, v3, v1

    .line 85
    invoke-virtual {v2, v5}, Ljava/util/regex/Pattern;->matcher(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;

    move-result-object v5

    invoke-virtual {v5}, Ljava/util/regex/Matcher;->matches()Z

    move-result v5

    if-eqz v5, :cond_0

    .line 86
    add-int/lit8 v0, v0, 0x1

    .line 84
    :cond_0
    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    .line 89
    :cond_1
    sput v0, Lmiui/os/Environment;->mM:I

    .line 91
    :cond_2
    sget v0, Lmiui/os/Environment;->mM:I

    return v0
.end method

.method public static getExternalStorageMiuiDirectory()Ljava/io/File;
    .locals 1

    .prologue
    .line 44
    sget-object v0, Lmiui/os/Environment;->mH:Ljava/io/File;

    invoke-virtual {v0}, Ljava/io/File;->exists()Z

    move-result v0

    if-nez v0, :cond_0

    .line 45
    invoke-static {}, Lmiui/os/Environment;->getExternalStorageDirectory()Ljava/io/File;

    move-result-object v0

    invoke-virtual {v0}, Ljava/io/File;->exists()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 46
    sget-object v0, Lmiui/os/Environment;->mH:Ljava/io/File;

    invoke-virtual {v0}, Ljava/io/File;->mkdir()Z

    .line 49
    :cond_0
    sget-object v0, Lmiui/os/Environment;->mH:Ljava/io/File;

    return-object v0
.end method

.method public static getMiuiAppDirectory()Ljava/io/File;
    .locals 1

    .prologue
    .line 59
    sget-object v0, Lmiui/os/Environment;->mJ:Ljava/io/File;

    return-object v0
.end method

.method public static getMiuiCustomizedDirectory()Ljava/io/File;
    .locals 1

    .prologue
    .line 69
    sget-object v0, Lmiui/os/Environment;->mL:Ljava/io/File;

    return-object v0
.end method

.method public static getMiuiDataDirectory()Ljava/io/File;
    .locals 1

    .prologue
    .line 54
    sget-object v0, Lmiui/os/Environment;->mI:Ljava/io/File;

    return-object v0
.end method

.method public static getMiuiPresetAppDirectory()Ljava/io/File;
    .locals 1

    .prologue
    .line 64
    sget-object v0, Lmiui/os/Environment;->mK:Ljava/io/File;

    return-object v0
.end method

.method public static isExternalStorageMounted()Z
    .locals 2

    .prologue
    .line 74
    const-string v0, "mounted"

    invoke-static {}, Lmiui/os/Environment;->getExternalStorageState()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    return v0
.end method
