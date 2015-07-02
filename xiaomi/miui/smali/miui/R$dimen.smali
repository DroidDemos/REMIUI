.class public final Lmiui/R$dimen;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/R;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "dimen"
.end annotation


# static fields
.field public static button_text_size:I

.field public static dialog_custom_vertical_padding:I

.field public static large_text_size:I

.field public static list_preferred_item_height:I

.field public static list_preferred_item_height_large:I

.field public static list_preferred_item_height_small:I

.field public static listview_horizontal_padding:I

.field public static normal_text_size:I

.field public static preference_custom_widget_margin_right:I

.field public static preference_horizontal_extra_padding:I

.field public static secondary_text_size:I

.field public static small_text_size:I


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .prologue
    .line 914
    const v0, 0x80a0004

    sput v0, Lmiui/R$dimen;->button_text_size:I

    .line 920
    const v0, 0x80a0005

    sput v0, Lmiui/R$dimen;->dialog_custom_vertical_padding:I

    .line 926
    const v0, 0x80a0001

    sput v0, Lmiui/R$dimen;->large_text_size:I

    .line 944
    const v0, 0x80a000a

    sput v0, Lmiui/R$dimen;->list_preferred_item_height:I

    .line 945
    const v0, 0x80a000b

    sput v0, Lmiui/R$dimen;->list_preferred_item_height_large:I

    .line 946
    const v0, 0x80a0009

    sput v0, Lmiui/R$dimen;->list_preferred_item_height_small:I

    .line 952
    const v0, 0x80a0007

    sput v0, Lmiui/R$dimen;->listview_horizontal_padding:I

    .line 967
    const/high16 v0, 0x80a0000

    sput v0, Lmiui/R$dimen;->normal_text_size:I

    .line 973
    const v0, 0x80a0006

    sput v0, Lmiui/R$dimen;->preference_custom_widget_margin_right:I

    .line 974
    const v0, 0x80a0008

    sput v0, Lmiui/R$dimen;->preference_horizontal_extra_padding:I

    .line 990
    const v0, 0x80a0003

    sput v0, Lmiui/R$dimen;->secondary_text_size:I

    .line 1005
    const v0, 0x80a0002

    sput v0, Lmiui/R$dimen;->small_text_size:I

    return-void
.end method

.method public constructor <init>()V
    .locals 0

    .prologue
    .line 898
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method
