.class final enum Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;
.super Ljava/lang/Enum;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/util/DirectIndexedFile$DataItemDescriptor;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x401a
    name = "Type"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Enum",
        "<",
        "Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;",
        ">;"
    }
.end annotation


# static fields
.field public static final enum RB:Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;

.field public static final enum RC:Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;

.field public static final enum RD:Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;

.field public static final enum RE:Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;

.field public static final enum RF:Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;

.field public static final enum RG:Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;

.field public static final enum RH:Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;

.field public static final enum RI:Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;

.field public static final enum RJ:Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;

.field private static final synthetic RK:[Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;


# direct methods
.method static constructor <clinit>()V
    .locals 8

    .prologue
    const/4 v7, 0x4

    const/4 v6, 0x3

    const/4 v5, 0x2

    const/4 v4, 0x1

    const/4 v3, 0x0

    .line 105
    new-instance v0, Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;

    const-string v1, "BYTE"

    invoke-direct {v0, v1, v3}, Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;->RB:Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;

    .line 106
    new-instance v0, Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;

    const-string v1, "SHORT"

    invoke-direct {v0, v1, v4}, Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;->RC:Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;

    .line 107
    new-instance v0, Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;

    const-string v1, "INTEGER"

    invoke-direct {v0, v1, v5}, Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;->RD:Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;

    .line 108
    new-instance v0, Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;

    const-string v1, "LONG"

    invoke-direct {v0, v1, v6}, Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;->RE:Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;

    .line 109
    new-instance v0, Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;

    const-string v1, "STRING"

    invoke-direct {v0, v1, v7}, Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;->RF:Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;

    .line 110
    new-instance v0, Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;

    const-string v1, "BYTE_ARRAY"

    const/4 v2, 0x5

    invoke-direct {v0, v1, v2}, Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;->RG:Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;

    .line 111
    new-instance v0, Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;

    const-string v1, "SHORT_ARRAY"

    const/4 v2, 0x6

    invoke-direct {v0, v1, v2}, Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;->RH:Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;

    .line 112
    new-instance v0, Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;

    const-string v1, "INTEGER_ARRAY"

    const/4 v2, 0x7

    invoke-direct {v0, v1, v2}, Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;->RI:Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;

    .line 113
    new-instance v0, Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;

    const-string v1, "LONG_ARRAY"

    const/16 v2, 0x8

    invoke-direct {v0, v1, v2}, Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;->RJ:Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;

    .line 104
    const/16 v0, 0x9

    new-array v0, v0, [Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;

    sget-object v1, Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;->RB:Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;

    aput-object v1, v0, v3

    sget-object v1, Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;->RC:Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;

    aput-object v1, v0, v4

    sget-object v1, Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;->RD:Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;

    aput-object v1, v0, v5

    sget-object v1, Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;->RE:Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;

    aput-object v1, v0, v6

    sget-object v1, Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;->RF:Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;

    aput-object v1, v0, v7

    const/4 v1, 0x5

    sget-object v2, Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;->RG:Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;

    aput-object v2, v0, v1

    const/4 v1, 0x6

    sget-object v2, Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;->RH:Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;

    aput-object v2, v0, v1

    const/4 v1, 0x7

    sget-object v2, Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;->RI:Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;

    aput-object v2, v0, v1

    const/16 v1, 0x8

    sget-object v2, Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;->RJ:Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;

    aput-object v2, v0, v1

    sput-object v0, Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;->RK:[Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;

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
    .line 104
    invoke-direct {p0, p1, p2}, Ljava/lang/Enum;-><init>(Ljava/lang/String;I)V

    return-void
.end method

.method public static valueOf(Ljava/lang/String;)Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;
    .locals 1

    .prologue
    .line 104
    const-class v0, Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;

    invoke-static {v0, p0}, Ljava/lang/Enum;->valueOf(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Enum;

    move-result-object v0

    check-cast v0, Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;

    return-object v0
.end method

.method public static values()[Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;
    .locals 1

    .prologue
    .line 104
    sget-object v0, Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;->RK:[Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;

    invoke-virtual {v0}, [Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;->clone()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;

    return-object v0
.end method
