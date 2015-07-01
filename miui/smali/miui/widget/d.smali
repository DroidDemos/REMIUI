.class final Lmiui/widget/d;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroid/os/Parcelable$Creator;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/widget/DateTimePicker$b;
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
        "Lmiui/widget/DateTimePicker$b;",
        ">;"
    }
.end annotation


# direct methods
.method constructor <init>()V
    .locals 0

    .prologue
    .line 433
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public b(Landroid/os/Parcel;)Lmiui/widget/DateTimePicker$b;
    .locals 1

    .prologue
    .line 435
    new-instance v0, Lmiui/widget/DateTimePicker$b;

    invoke-direct {v0, p1}, Lmiui/widget/DateTimePicker$b;-><init>(Landroid/os/Parcel;)V

    return-object v0
.end method

.method public bridge synthetic createFromParcel(Landroid/os/Parcel;)Ljava/lang/Object;
    .locals 1

    .prologue
    .line 433
    invoke-virtual {p0, p1}, Lmiui/widget/d;->b(Landroid/os/Parcel;)Lmiui/widget/DateTimePicker$b;

    move-result-object v0

    return-object v0
.end method

.method public h(I)[Lmiui/widget/DateTimePicker$b;
    .locals 1

    .prologue
    .line 439
    new-array v0, p1, [Lmiui/widget/DateTimePicker$b;

    return-object v0
.end method

.method public bridge synthetic newArray(I)[Ljava/lang/Object;
    .locals 1

    .prologue
    .line 433
    invoke-virtual {p0, p1}, Lmiui/widget/d;->h(I)[Lmiui/widget/DateTimePicker$b;

    move-result-object v0

    return-object v0
.end method
