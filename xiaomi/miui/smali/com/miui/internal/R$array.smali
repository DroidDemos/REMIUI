.class public final Lcom/miui/internal/R$array;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/internal/R;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "array"
.end annotation


# static fields
.field public static alphabet_table:I

.field public static am_pms:I

.field public static chinese_days:I

.field public static chinese_digits:I

.field public static chinese_leap_months:I

.field public static chinese_months:I

.field public static chinese_symbol_animals:I

.field public static contact_infotype_entries:I

.field public static detailed_am_pms:I

.field public static earthly_branches:I

.field public static eras:I

.field public static heavenly_stems:I

.field public static months:I

.field public static months_short:I

.field public static months_shortest:I

.field public static preloaded_drawables:I

.field public static solar_terms:I

.field public static week_days:I

.field public static week_days_short:I

.field public static week_days_shortest:I


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .prologue
    .line 26
    const/high16 v0, 0x8090000

    sput v0, Lcom/miui/internal/R$array;->alphabet_table:I

    .line 27
    const v0, 0x8090004

    sput v0, Lcom/miui/internal/R$array;->am_pms:I

    .line 28
    const v0, 0x8090006

    sput v0, Lcom/miui/internal/R$array;->chinese_days:I

    .line 29
    const v0, 0x8090005

    sput v0, Lcom/miui/internal/R$array;->chinese_digits:I

    .line 30
    const v0, 0x8090007

    sput v0, Lcom/miui/internal/R$array;->chinese_leap_months:I

    .line 31
    const v0, 0x8090008

    sput v0, Lcom/miui/internal/R$array;->chinese_months:I

    .line 32
    const v0, 0x809000e

    sput v0, Lcom/miui/internal/R$array;->chinese_symbol_animals:I

    .line 33
    const v0, 0x8090013

    sput v0, Lcom/miui/internal/R$array;->contact_infotype_entries:I

    .line 34
    const v0, 0x8090003

    sput v0, Lcom/miui/internal/R$array;->detailed_am_pms:I

    .line 35
    const v0, 0x809000a

    sput v0, Lcom/miui/internal/R$array;->earthly_branches:I

    .line 36
    const v0, 0x809000f

    sput v0, Lcom/miui/internal/R$array;->eras:I

    .line 37
    const v0, 0x8090009

    sput v0, Lcom/miui/internal/R$array;->heavenly_stems:I

    .line 38
    const v0, 0x809000b

    sput v0, Lcom/miui/internal/R$array;->months:I

    .line 39
    const v0, 0x809000c

    sput v0, Lcom/miui/internal/R$array;->months_short:I

    .line 40
    const v0, 0x809000d

    sput v0, Lcom/miui/internal/R$array;->months_shortest:I

    .line 41
    const v0, 0x8090014

    sput v0, Lcom/miui/internal/R$array;->preloaded_drawables:I

    .line 42
    const v0, 0x8090002

    sput v0, Lcom/miui/internal/R$array;->solar_terms:I

    .line 43
    const v0, 0x8090010

    sput v0, Lcom/miui/internal/R$array;->week_days:I

    .line 44
    const v0, 0x8090011

    sput v0, Lcom/miui/internal/R$array;->week_days_short:I

    .line 45
    const v0, 0x8090012

    sput v0, Lcom/miui/internal/R$array;->week_days_shortest:I

    return-void
.end method

.method public constructor <init>()V
    .locals 0

    .prologue
    .line 23
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method
