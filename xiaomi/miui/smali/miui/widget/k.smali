.class final Lmiui/widget/k;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroid/os/Parcelable$Creator;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/widget/TimePicker$a;
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
        "Lmiui/widget/TimePicker$a;",
        ">;"
    }
.end annotation


# direct methods
.method constructor <init>()V
    .locals 0

    .prologue
    .line 278
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public K(I)[Lmiui/widget/TimePicker$a;
    .locals 1

    .prologue
    .line 284
    new-array v0, p1, [Lmiui/widget/TimePicker$a;

    return-object v0
.end method

.method public bridge synthetic createFromParcel(Landroid/os/Parcel;)Ljava/lang/Object;
    .locals 1

    .prologue
    .line 278
    invoke-virtual {p0, p1}, Lmiui/widget/k;->g(Landroid/os/Parcel;)Lmiui/widget/TimePicker$a;

    move-result-object v0

    return-object v0
.end method

.method public g(Landroid/os/Parcel;)Lmiui/widget/TimePicker$a;
    .locals 2

    .prologue
    .line 280
    new-instance v0, Lmiui/widget/TimePicker$a;

    const/4 v1, 0x0

    invoke-direct {v0, p1, v1}, Lmiui/widget/TimePicker$a;-><init>(Landroid/os/Parcel;Lmiui/widget/u;)V

    return-object v0
.end method

.method public bridge synthetic newArray(I)[Ljava/lang/Object;
    .locals 1

    .prologue
    .line 278
    invoke-virtual {p0, p1}, Lmiui/widget/k;->K(I)[Lmiui/widget/TimePicker$a;

    move-result-object v0

    return-object v0
.end method
