.class public final Lcom/miui/internal/R$integer;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/internal/R;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "integer"
.end annotation


# static fields
.field public static abc_max_action_buttons:I

.field public static action_bar_tab_layout_weight:I

.field public static button_exit_fade_duration:I

.field public static preference_fragment_scrollbarStyle:I

.field public static window_translucent_status:I


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .prologue
    .line 1831
    const/high16 v0, 0x8080000

    sput v0, Lcom/miui/internal/R$integer;->abc_max_action_buttons:I

    .line 1832
    const v0, 0x8080004

    sput v0, Lcom/miui/internal/R$integer;->action_bar_tab_layout_weight:I

    .line 1835
    const v0, 0x8080001

    sput v0, Lcom/miui/internal/R$integer;->button_exit_fade_duration:I

    .line 1836
    const v0, 0x8080003

    sput v0, Lcom/miui/internal/R$integer;->preference_fragment_scrollbarStyle:I

    .line 1839
    const v0, 0x8080006

    sput v0, Lcom/miui/internal/R$integer;->window_translucent_status:I

    return-void
.end method

.method public constructor <init>()V
    .locals 0

    .prologue
    .line 1826
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method
