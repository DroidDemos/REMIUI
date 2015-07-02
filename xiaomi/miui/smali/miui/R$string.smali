.class public final Lmiui/R$string;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/R;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "string"
.end annotation


# static fields
.field public static close:I

.field public static label:I

.field public static send:I


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .prologue
    .line 1368
    const v0, 0x80c0002

    sput v0, Lmiui/R$string;->close:I

    .line 1371
    const/high16 v0, 0x80c0000

    sput v0, Lmiui/R$string;->label:I

    .line 1375
    const v0, 0x80c0001

    sput v0, Lmiui/R$string;->send:I

    return-void
.end method

.method public constructor <init>()V
    .locals 0

    .prologue
    .line 1364
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method
