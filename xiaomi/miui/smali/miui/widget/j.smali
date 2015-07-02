.class final Lmiui/widget/j;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroid/os/Parcelable$Creator;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/widget/ScreenView$e;
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
        "Lmiui/widget/ScreenView$e;",
        ">;"
    }
.end annotation


# direct methods
.method constructor <init>()V
    .locals 0

    .prologue
    .line 1951
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public H(I)[Lmiui/widget/ScreenView$e;
    .locals 1

    .prologue
    .line 1957
    new-array v0, p1, [Lmiui/widget/ScreenView$e;

    return-object v0
.end method

.method public bridge synthetic createFromParcel(Landroid/os/Parcel;)Ljava/lang/Object;
    .locals 1

    .prologue
    .line 1951
    invoke-virtual {p0, p1}, Lmiui/widget/j;->f(Landroid/os/Parcel;)Lmiui/widget/ScreenView$e;

    move-result-object v0

    return-object v0
.end method

.method public f(Landroid/os/Parcel;)Lmiui/widget/ScreenView$e;
    .locals 2

    .prologue
    .line 1953
    new-instance v0, Lmiui/widget/ScreenView$e;

    const/4 v1, 0x0

    invoke-direct {v0, p1, v1}, Lmiui/widget/ScreenView$e;-><init>(Landroid/os/Parcel;Lmiui/widget/n;)V

    return-object v0
.end method

.method public bridge synthetic newArray(I)[Ljava/lang/Object;
    .locals 1

    .prologue
    .line 1951
    invoke-virtual {p0, p1}, Lmiui/widget/j;->H(I)[Lmiui/widget/ScreenView$e;

    move-result-object v0

    return-object v0
.end method
