.class public Lmiui/date/CalendarFormatSymbols;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field private static final f:Lmiui/util/SoftReferenceSingleton;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lmiui/util/SoftReferenceSingleton",
            "<",
            "Lmiui/date/CalendarFormatSymbols;",
            ">;"
        }
    .end annotation
.end field


# instance fields
.field private J:Landroid/content/res/Resources;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .prologue
    .line 18
    new-instance v0, Lmiui/date/a;

    invoke-direct {v0}, Lmiui/date/a;-><init>()V

    sput-object v0, Lmiui/date/CalendarFormatSymbols;->f:Lmiui/util/SoftReferenceSingleton;

    return-void
.end method

.method public constructor <init>()V
    .locals 1

    .prologue
    .line 16
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 26
    invoke-static {}, Lcom/miui/internal/util/PackageConstants;->getCurrentApplication()Landroid/app/Application;

    move-result-object v0

    invoke-virtual {v0}, Landroid/app/Application;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    iput-object v0, p0, Lmiui/date/CalendarFormatSymbols;->J:Landroid/content/res/Resources;

    return-void
.end method

.method public static getDefault()Lmiui/date/CalendarFormatSymbols;
    .locals 1

    .prologue
    .line 32
    sget-object v0, Lmiui/date/CalendarFormatSymbols;->f:Lmiui/util/SoftReferenceSingleton;

    invoke-virtual {v0}, Lmiui/util/SoftReferenceSingleton;->get()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/date/CalendarFormatSymbols;

    return-object v0
.end method


# virtual methods
.method public getAmPms()[Ljava/lang/String;
    .locals 2

    .prologue
    .line 67
    iget-object v0, p0, Lmiui/date/CalendarFormatSymbols;->J:Landroid/content/res/Resources;

    sget v1, Lcom/miui/internal/R$array;->am_pms:I

    invoke-virtual {v0, v1}, Landroid/content/res/Resources;->getStringArray(I)[Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method public getChineseDays()[Ljava/lang/String;
    .locals 2

    .prologue
    .line 53
    iget-object v0, p0, Lmiui/date/CalendarFormatSymbols;->J:Landroid/content/res/Resources;

    sget v1, Lcom/miui/internal/R$array;->chinese_days:I

    invoke-virtual {v0, v1}, Landroid/content/res/Resources;->getStringArray(I)[Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method public getChineseDigits()[Ljava/lang/String;
    .locals 2

    .prologue
    .line 74
    iget-object v0, p0, Lmiui/date/CalendarFormatSymbols;->J:Landroid/content/res/Resources;

    sget v1, Lcom/miui/internal/R$array;->chinese_digits:I

    invoke-virtual {v0, v1}, Landroid/content/res/Resources;->getStringArray(I)[Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method public getChineseLeapMonths()[Ljava/lang/String;
    .locals 2

    .prologue
    .line 81
    iget-object v0, p0, Lmiui/date/CalendarFormatSymbols;->J:Landroid/content/res/Resources;

    sget v1, Lcom/miui/internal/R$array;->chinese_leap_months:I

    invoke-virtual {v0, v1}, Landroid/content/res/Resources;->getStringArray(I)[Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method public getChineseMonths()[Ljava/lang/String;
    .locals 2

    .prologue
    .line 88
    iget-object v0, p0, Lmiui/date/CalendarFormatSymbols;->J:Landroid/content/res/Resources;

    sget v1, Lcom/miui/internal/R$array;->chinese_months:I

    invoke-virtual {v0, v1}, Landroid/content/res/Resources;->getStringArray(I)[Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method public getChineseSymbolAnimals()[Ljava/lang/String;
    .locals 2

    .prologue
    .line 130
    iget-object v0, p0, Lmiui/date/CalendarFormatSymbols;->J:Landroid/content/res/Resources;

    sget v1, Lcom/miui/internal/R$array;->chinese_symbol_animals:I

    invoke-virtual {v0, v1}, Landroid/content/res/Resources;->getStringArray(I)[Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method public getDetailedAmPms()[Ljava/lang/String;
    .locals 2

    .prologue
    .line 60
    iget-object v0, p0, Lmiui/date/CalendarFormatSymbols;->J:Landroid/content/res/Resources;

    sget v1, Lcom/miui/internal/R$array;->detailed_am_pms:I

    invoke-virtual {v0, v1}, Landroid/content/res/Resources;->getStringArray(I)[Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method public getEarthlyBranches()[Ljava/lang/String;
    .locals 2

    .prologue
    .line 95
    iget-object v0, p0, Lmiui/date/CalendarFormatSymbols;->J:Landroid/content/res/Resources;

    sget v1, Lcom/miui/internal/R$array;->earthly_branches:I

    invoke-virtual {v0, v1}, Landroid/content/res/Resources;->getStringArray(I)[Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method public getEras()[Ljava/lang/String;
    .locals 2

    .prologue
    .line 137
    iget-object v0, p0, Lmiui/date/CalendarFormatSymbols;->J:Landroid/content/res/Resources;

    sget v1, Lcom/miui/internal/R$array;->eras:I

    invoke-virtual {v0, v1}, Landroid/content/res/Resources;->getStringArray(I)[Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method public getHeavenlyStems()[Ljava/lang/String;
    .locals 2

    .prologue
    .line 123
    iget-object v0, p0, Lmiui/date/CalendarFormatSymbols;->J:Landroid/content/res/Resources;

    sget v1, Lcom/miui/internal/R$array;->heavenly_stems:I

    invoke-virtual {v0, v1}, Landroid/content/res/Resources;->getStringArray(I)[Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method public getLocale()Ljava/util/Locale;
    .locals 1

    .prologue
    .line 39
    invoke-static {}, Ljava/util/Locale;->getDefault()Ljava/util/Locale;

    move-result-object v0

    return-object v0
.end method

.method public getMonths()[Ljava/lang/String;
    .locals 2

    .prologue
    .line 116
    iget-object v0, p0, Lmiui/date/CalendarFormatSymbols;->J:Landroid/content/res/Resources;

    sget v1, Lcom/miui/internal/R$array;->months:I

    invoke-virtual {v0, v1}, Landroid/content/res/Resources;->getStringArray(I)[Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method public getShortMonths()[Ljava/lang/String;
    .locals 2

    .prologue
    .line 102
    iget-object v0, p0, Lmiui/date/CalendarFormatSymbols;->J:Landroid/content/res/Resources;

    sget v1, Lcom/miui/internal/R$array;->months_short:I

    invoke-virtual {v0, v1}, Landroid/content/res/Resources;->getStringArray(I)[Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method public getShortWeekDays()[Ljava/lang/String;
    .locals 2

    .prologue
    .line 144
    iget-object v0, p0, Lmiui/date/CalendarFormatSymbols;->J:Landroid/content/res/Resources;

    sget v1, Lcom/miui/internal/R$array;->week_days_short:I

    invoke-virtual {v0, v1}, Landroid/content/res/Resources;->getStringArray(I)[Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method public getShortestMonths()[Ljava/lang/String;
    .locals 2

    .prologue
    .line 109
    iget-object v0, p0, Lmiui/date/CalendarFormatSymbols;->J:Landroid/content/res/Resources;

    sget v1, Lcom/miui/internal/R$array;->months_shortest:I

    invoke-virtual {v0, v1}, Landroid/content/res/Resources;->getStringArray(I)[Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method public getShortestWeekDays()[Ljava/lang/String;
    .locals 2

    .prologue
    .line 151
    iget-object v0, p0, Lmiui/date/CalendarFormatSymbols;->J:Landroid/content/res/Resources;

    sget v1, Lcom/miui/internal/R$array;->week_days_shortest:I

    invoke-virtual {v0, v1}, Landroid/content/res/Resources;->getStringArray(I)[Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method public getSolarTerms()[Ljava/lang/String;
    .locals 2

    .prologue
    .line 46
    iget-object v0, p0, Lmiui/date/CalendarFormatSymbols;->J:Landroid/content/res/Resources;

    sget v1, Lcom/miui/internal/R$array;->solar_terms:I

    invoke-virtual {v0, v1}, Landroid/content/res/Resources;->getStringArray(I)[Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method public getWeekDays()[Ljava/lang/String;
    .locals 2

    .prologue
    .line 158
    iget-object v0, p0, Lmiui/date/CalendarFormatSymbols;->J:Landroid/content/res/Resources;

    sget v1, Lcom/miui/internal/R$array;->week_days:I

    invoke-virtual {v0, v1}, Landroid/content/res/Resources;->getStringArray(I)[Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method
