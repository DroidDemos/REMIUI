.class final Lmiui/widget/l;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroid/os/Parcelable$Creator;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/widget/DatePicker$a;
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
        "Lmiui/widget/DatePicker$a;",
        ">;"
    }
.end annotation


# direct methods
.method constructor <init>()V
    .locals 0

    .prologue
    .line 773
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public S(I)[Lmiui/widget/DatePicker$a;
    .locals 1

    .prologue
    .line 780
    new-array v0, p1, [Lmiui/widget/DatePicker$a;

    return-object v0
.end method

.method public bridge synthetic createFromParcel(Landroid/os/Parcel;)Ljava/lang/Object;
    .locals 1

    .prologue
    .line 773
    invoke-virtual {p0, p1}, Lmiui/widget/l;->h(Landroid/os/Parcel;)Lmiui/widget/DatePicker$a;

    move-result-object v0

    return-object v0
.end method

.method public h(Landroid/os/Parcel;)Lmiui/widget/DatePicker$a;
    .locals 2

    .prologue
    .line 776
    new-instance v0, Lmiui/widget/DatePicker$a;

    const/4 v1, 0x0

    invoke-direct {v0, p1, v1}, Lmiui/widget/DatePicker$a;-><init>(Landroid/os/Parcel;Lmiui/widget/DatePicker$1;)V

    return-object v0
.end method

.method public bridge synthetic newArray(I)[Ljava/lang/Object;
    .locals 1

    .prologue
    .line 773
    invoke-virtual {p0, p1}, Lmiui/widget/l;->S(I)[Lmiui/widget/DatePicker$a;

    move-result-object v0

    return-object v0
.end method
