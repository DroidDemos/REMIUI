.class public final Lmiui/R$id;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/R;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "id"
.end annotation


# static fields
.field public static am_pm:I

.field public static content:I

.field public static navigation:I

.field public static time_display:I

.field public static view_pager:I


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .prologue
    .line 1327
    const v0, 0x80b0004

    sput v0, Lmiui/R$id;->am_pm:I

    .line 1330
    const v0, 0x80b0002

    sput v0, Lmiui/R$id;->content:I

    .line 1333
    const v0, 0x80b0001

    sput v0, Lmiui/R$id;->navigation:I

    .line 1336
    const v0, 0x80b0003

    sput v0, Lmiui/R$id;->time_display:I

    .line 1339
    const/high16 v0, 0x80b0000

    sput v0, Lmiui/R$id;->view_pager:I

    return-void
.end method

.method public constructor <init>()V
    .locals 0

    .prologue
    .line 1324
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method
