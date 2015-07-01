.class Lmiui/widget/ScreenView$i;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroid/view/animation/Interpolator;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/widget/ScreenView;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x2
    name = "i"
.end annotation


# instance fields
.field private TA:F

.field final synthetic bW:Lmiui/widget/ScreenView;


# direct methods
.method public constructor <init>(Lmiui/widget/ScreenView;)V
    .locals 1

    .prologue
    .line 2090
    iput-object p1, p0, Lmiui/widget/ScreenView$i;->bW:Lmiui/widget/ScreenView;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 2091
    invoke-static {p1}, Lmiui/widget/ScreenView;->c(Lmiui/widget/ScreenView;)F

    move-result v0

    iput v0, p0, Lmiui/widget/ScreenView$i;->TA:F

    .line 2092
    return-void
.end method

.method static synthetic a(Lmiui/widget/ScreenView$i;F)F
    .locals 0

    .prologue
    .line 2086
    iput p1, p0, Lmiui/widget/ScreenView$i;->TA:F

    return p1
.end method


# virtual methods
.method public eZ()V
    .locals 1

    .prologue
    .line 2101
    const/4 v0, 0x0

    iput v0, p0, Lmiui/widget/ScreenView$i;->TA:F

    .line 2102
    return-void
.end method

.method public getInterpolation(F)F
    .locals 4

    .prologue
    const/high16 v3, 0x3f800000

    .line 2105
    sub-float v0, p1, v3

    .line 2106
    mul-float v1, v0, v0

    iget v2, p0, Lmiui/widget/ScreenView$i;->TA:F

    add-float/2addr v2, v3

    mul-float/2addr v0, v2

    iget v2, p0, Lmiui/widget/ScreenView$i;->TA:F

    add-float/2addr v0, v2

    mul-float/2addr v0, v1

    add-float/2addr v0, v3

    return v0
.end method

.method public n(II)V
    .locals 2

    .prologue
    .line 2095
    if-lez p1, :cond_0

    iget-object v0, p0, Lmiui/widget/ScreenView$i;->bW:Lmiui/widget/ScreenView;

    invoke-static {v0}, Lmiui/widget/ScreenView;->c(Lmiui/widget/ScreenView;)F

    move-result v0

    int-to-float v1, p1

    div-float/2addr v0, v1

    :goto_0
    iput v0, p0, Lmiui/widget/ScreenView$i;->TA:F

    .line 2098
    return-void

    .line 2095
    :cond_0
    iget-object v0, p0, Lmiui/widget/ScreenView$i;->bW:Lmiui/widget/ScreenView;

    invoke-static {v0}, Lmiui/widget/ScreenView;->c(Lmiui/widget/ScreenView;)F

    move-result v0

    goto :goto_0
.end method
