.class Lmiui/widget/ScreenView$e;
.super Landroid/view/View$BaseSavedState;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/widget/ScreenView;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x8
    name = "e"
.end annotation


# static fields
.field public static final CREATOR:Landroid/os/Parcelable$Creator;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Landroid/os/Parcelable$Creator",
            "<",
            "Lmiui/widget/ScreenView$e;",
            ">;"
        }
    .end annotation
.end field


# instance fields
.field Dv:I


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .prologue
    .line 1950
    new-instance v0, Lmiui/widget/j;

    invoke-direct {v0}, Lmiui/widget/j;-><init>()V

    sput-object v0, Lmiui/widget/ScreenView$e;->CREATOR:Landroid/os/Parcelable$Creator;

    return-void
.end method

.method private constructor <init>(Landroid/os/Parcel;)V
    .locals 1

    .prologue
    .line 1968
    invoke-direct {p0, p1}, Landroid/view/View$BaseSavedState;-><init>(Landroid/os/Parcel;)V

    .line 1961
    const/4 v0, -0x1

    iput v0, p0, Lmiui/widget/ScreenView$e;->Dv:I

    .line 1969
    invoke-virtual {p1}, Landroid/os/Parcel;->readInt()I

    move-result v0

    iput v0, p0, Lmiui/widget/ScreenView$e;->Dv:I

    .line 1970
    return-void
.end method

.method synthetic constructor <init>(Landroid/os/Parcel;Lmiui/widget/n;)V
    .locals 0

    .prologue
    .line 1948
    invoke-direct {p0, p1}, Lmiui/widget/ScreenView$e;-><init>(Landroid/os/Parcel;)V

    return-void
.end method

.method constructor <init>(Landroid/os/Parcelable;)V
    .locals 1

    .prologue
    .line 1964
    invoke-direct {p0, p1}, Landroid/view/View$BaseSavedState;-><init>(Landroid/os/Parcelable;)V

    .line 1961
    const/4 v0, -0x1

    iput v0, p0, Lmiui/widget/ScreenView$e;->Dv:I

    .line 1965
    return-void
.end method


# virtual methods
.method public writeToParcel(Landroid/os/Parcel;I)V
    .locals 1

    .prologue
    .line 1974
    invoke-super {p0, p1, p2}, Landroid/view/View$BaseSavedState;->writeToParcel(Landroid/os/Parcel;I)V

    .line 1975
    iget v0, p0, Lmiui/widget/ScreenView$e;->Dv:I

    invoke-virtual {p1, v0}, Landroid/os/Parcel;->writeInt(I)V

    .line 1976
    return-void
.end method
