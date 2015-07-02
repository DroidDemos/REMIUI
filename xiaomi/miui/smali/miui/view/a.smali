.class final Lmiui/view/a;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroid/os/Parcelable$Creator;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/view/ViewPager$SavedState;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x8
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Object;",
        "Landroid/os/Parcelable$Creator",
        "<",
        "Lmiui/view/ViewPager$SavedState;",
        ">;"
    }
.end annotation


# direct methods
.method constructor <init>()V
    .locals 0

    .prologue
    .line 1060
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public an(I)[Lmiui/view/ViewPager$SavedState;
    .locals 1

    .prologue
    .line 1067
    new-array v0, p1, [Lmiui/view/ViewPager$SavedState;

    return-object v0
.end method

.method public bridge synthetic createFromParcel(Landroid/os/Parcel;)Ljava/lang/Object;
    .locals 1

    .prologue
    .line 1060
    invoke-virtual {p0, p1}, Lmiui/view/a;->k(Landroid/os/Parcel;)Lmiui/view/ViewPager$SavedState;

    move-result-object v0

    return-object v0
.end method

.method public k(Landroid/os/Parcel;)Lmiui/view/ViewPager$SavedState;
    .locals 1

    .prologue
    .line 1063
    new-instance v0, Lmiui/view/ViewPager$SavedState;

    invoke-direct {v0, p1}, Lmiui/view/ViewPager$SavedState;-><init>(Landroid/os/Parcel;)V

    return-object v0
.end method

.method public bridge synthetic newArray(I)[Ljava/lang/Object;
    .locals 1

    .prologue
    .line 1060
    invoke-virtual {p0, p1}, Lmiui/view/a;->an(I)[Lmiui/view/ViewPager$SavedState;

    move-result-object v0

    return-object v0
.end method
