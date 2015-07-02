.class public final Lmiui/R$layout;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/R;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "layout"
.end annotation


# static fields
.field public static immersion_popup_menu_item:I

.field public static search_stub:I

.field public static simple_arrow_popup_item:I

.field public static simple_spinner_dropdown_item:I


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .prologue
    .line 1349
    const v0, 0x8030003

    sput v0, Lmiui/R$layout;->immersion_popup_menu_item:I

    .line 1352
    const/high16 v0, 0x8030000

    sput v0, Lmiui/R$layout;->search_stub:I

    .line 1355
    const v0, 0x8030002

    sput v0, Lmiui/R$layout;->simple_arrow_popup_item:I

    .line 1358
    const v0, 0x8030001

    sput v0, Lmiui/R$layout;->simple_spinner_dropdown_item:I

    return-void
.end method

.method public constructor <init>()V
    .locals 0

    .prologue
    .line 1346
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method
