.class public final Lmiui/R$integer;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/R;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "integer"
.end annotation


# static fields
.field public static button_exit_fade_duration:I


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .prologue
    .line 1344
    const v0, 0x8080001

    sput v0, Lmiui/R$integer;->button_exit_fade_duration:I

    return-void
.end method

.method public constructor <init>()V
    .locals 0

    .prologue
    .line 1341
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method
