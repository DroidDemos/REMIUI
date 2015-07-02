.class Lmiui/widget/NavigationLayout$b;
.super Landroid/view/View$BaseSavedState;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/widget/NavigationLayout;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0xa
    name = "b"
.end annotation


# static fields
.field public static final CREATOR:Landroid/os/Parcelable$Creator;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Landroid/os/Parcelable$Creator",
            "<",
            "Lmiui/widget/NavigationLayout$b;",
            ">;"
        }
    .end annotation
.end field


# instance fields
.field lg:F


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .prologue
    .line 974
    new-instance v0, Lmiui/widget/i;

    invoke-direct {v0}, Lmiui/widget/i;-><init>()V

    sput-object v0, Lmiui/widget/NavigationLayout$b;->CREATOR:Landroid/os/Parcelable$Creator;

    return-void
.end method

.method public constructor <init>(Landroid/os/Parcel;)V
    .locals 1

    .prologue
    .line 990
    invoke-direct {p0, p1}, Landroid/view/View$BaseSavedState;-><init>(Landroid/os/Parcel;)V

    .line 991
    invoke-virtual {p1}, Landroid/os/Parcel;->readFloat()F

    move-result v0

    iput v0, p0, Lmiui/widget/NavigationLayout$b;->lg:F

    .line 992
    return-void
.end method

.method private constructor <init>(Landroid/os/Parcelable;)V
    .locals 0

    .prologue
    .line 995
    invoke-direct {p0, p1}, Landroid/view/View$BaseSavedState;-><init>(Landroid/os/Parcelable;)V

    .line 996
    return-void
.end method

.method synthetic constructor <init>(Landroid/os/Parcelable;Lmiui/widget/f;)V
    .locals 0

    .prologue
    .line 972
    invoke-direct {p0, p1}, Lmiui/widget/NavigationLayout$b;-><init>(Landroid/os/Parcelable;)V

    return-void
.end method


# virtual methods
.method public writeToParcel(Landroid/os/Parcel;I)V
    .locals 1

    .prologue
    .line 1000
    invoke-super {p0, p1, p2}, Landroid/view/View$BaseSavedState;->writeToParcel(Landroid/os/Parcel;I)V

    .line 1001
    iget v0, p0, Lmiui/widget/NavigationLayout$b;->lg:F

    invoke-virtual {p1, v0}, Landroid/os/Parcel;->writeFloat(F)V

    .line 1002
    return-void
.end method
