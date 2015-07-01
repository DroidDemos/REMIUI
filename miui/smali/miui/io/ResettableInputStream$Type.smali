.class final enum Lmiui/io/ResettableInputStream$Type;
.super Ljava/lang/Enum;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/io/ResettableInputStream;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x401a
    name = "Type"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Enum",
        "<",
        "Lmiui/io/ResettableInputStream$Type;",
        ">;"
    }
.end annotation


# static fields
.field public static final enum yQ:Lmiui/io/ResettableInputStream$Type;

.field public static final enum yR:Lmiui/io/ResettableInputStream$Type;

.field public static final enum yS:Lmiui/io/ResettableInputStream$Type;

.field public static final enum yT:Lmiui/io/ResettableInputStream$Type;

.field private static final synthetic yU:[Lmiui/io/ResettableInputStream$Type;


# direct methods
.method static constructor <clinit>()V
    .locals 6

    .prologue
    const/4 v5, 0x3

    const/4 v4, 0x2

    const/4 v3, 0x1

    const/4 v2, 0x0

    .line 23
    new-instance v0, Lmiui/io/ResettableInputStream$Type;

    const-string v1, "File"

    invoke-direct {v0, v1, v2}, Lmiui/io/ResettableInputStream$Type;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lmiui/io/ResettableInputStream$Type;->yQ:Lmiui/io/ResettableInputStream$Type;

    .line 24
    new-instance v0, Lmiui/io/ResettableInputStream$Type;

    const-string v1, "Uri"

    invoke-direct {v0, v1, v3}, Lmiui/io/ResettableInputStream$Type;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lmiui/io/ResettableInputStream$Type;->yR:Lmiui/io/ResettableInputStream$Type;

    .line 25
    new-instance v0, Lmiui/io/ResettableInputStream$Type;

    const-string v1, "Asset"

    invoke-direct {v0, v1, v4}, Lmiui/io/ResettableInputStream$Type;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lmiui/io/ResettableInputStream$Type;->yS:Lmiui/io/ResettableInputStream$Type;

    .line 26
    new-instance v0, Lmiui/io/ResettableInputStream$Type;

    const-string v1, "ByteArray"

    invoke-direct {v0, v1, v5}, Lmiui/io/ResettableInputStream$Type;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lmiui/io/ResettableInputStream$Type;->yT:Lmiui/io/ResettableInputStream$Type;

    .line 22
    const/4 v0, 0x4

    new-array v0, v0, [Lmiui/io/ResettableInputStream$Type;

    sget-object v1, Lmiui/io/ResettableInputStream$Type;->yQ:Lmiui/io/ResettableInputStream$Type;

    aput-object v1, v0, v2

    sget-object v1, Lmiui/io/ResettableInputStream$Type;->yR:Lmiui/io/ResettableInputStream$Type;

    aput-object v1, v0, v3

    sget-object v1, Lmiui/io/ResettableInputStream$Type;->yS:Lmiui/io/ResettableInputStream$Type;

    aput-object v1, v0, v4

    sget-object v1, Lmiui/io/ResettableInputStream$Type;->yT:Lmiui/io/ResettableInputStream$Type;

    aput-object v1, v0, v5

    sput-object v0, Lmiui/io/ResettableInputStream$Type;->yU:[Lmiui/io/ResettableInputStream$Type;

    return-void
.end method

.method private constructor <init>(Ljava/lang/String;I)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()V"
        }
    .end annotation

    .prologue
    .line 22
    invoke-direct {p0, p1, p2}, Ljava/lang/Enum;-><init>(Ljava/lang/String;I)V

    return-void
.end method

.method public static valueOf(Ljava/lang/String;)Lmiui/io/ResettableInputStream$Type;
    .locals 1

    .prologue
    .line 22
    const-class v0, Lmiui/io/ResettableInputStream$Type;

    invoke-static {v0, p0}, Ljava/lang/Enum;->valueOf(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Enum;

    move-result-object v0

    check-cast v0, Lmiui/io/ResettableInputStream$Type;

    return-object v0
.end method

.method public static values()[Lmiui/io/ResettableInputStream$Type;
    .locals 1

    .prologue
    .line 22
    sget-object v0, Lmiui/io/ResettableInputStream$Type;->yU:[Lmiui/io/ResettableInputStream$Type;

    invoke-virtual {v0}, [Lmiui/io/ResettableInputStream$Type;->clone()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Lmiui/io/ResettableInputStream$Type;

    return-object v0
.end method
