.class public abstract Lmiui/payment/IPaymentManagerResponse$Stub;
.super Landroid/os/Binder;
.source "SourceFile"

# interfaces
.implements Lmiui/payment/IPaymentManagerResponse;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/payment/IPaymentManagerResponse;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x409
    name = "Stub"
.end annotation

.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lmiui/payment/IPaymentManagerResponse$Stub$a;
    }
.end annotation


# static fields
.field static final JI:I = 0x1

.field static final JJ:I = 0x2

.field private static final qA:Ljava/lang/String; = "miui.payment.IPaymentManagerResponse"


# direct methods
.method public constructor <init>()V
    .locals 1

    .prologue
    .line 17
    invoke-direct {p0}, Landroid/os/Binder;-><init>()V

    .line 18
    const-string v0, "miui.payment.IPaymentManagerResponse"

    invoke-virtual {p0, p0, v0}, Lmiui/payment/IPaymentManagerResponse$Stub;->attachInterface(Landroid/os/IInterface;Ljava/lang/String;)V

    .line 19
    return-void
.end method

.method public static asInterface(Landroid/os/IBinder;)Lmiui/payment/IPaymentManagerResponse;
    .locals 2

    .prologue
    .line 26
    if-nez p0, :cond_0

    .line 27
    const/4 v0, 0x0

    .line 33
    :goto_0
    return-object v0

    .line 29
    :cond_0
    const-string v0, "miui.payment.IPaymentManagerResponse"

    invoke-interface {p0, v0}, Landroid/os/IBinder;->queryLocalInterface(Ljava/lang/String;)Landroid/os/IInterface;

    move-result-object v0

    .line 30
    if-eqz v0, :cond_1

    instance-of v1, v0, Lmiui/payment/IPaymentManagerResponse;

    if-eqz v1, :cond_1

    .line 31
    check-cast v0, Lmiui/payment/IPaymentManagerResponse;

    goto :goto_0

    .line 33
    :cond_1
    new-instance v0, Lmiui/payment/IPaymentManagerResponse$Stub$a;

    invoke-direct {v0, p0}, Lmiui/payment/IPaymentManagerResponse$Stub$a;-><init>(Landroid/os/IBinder;)V

    goto :goto_0
.end method


# virtual methods
.method public asBinder()Landroid/os/IBinder;
    .locals 0

    .prologue
    .line 37
    return-object p0
.end method

.method public onTransact(ILandroid/os/Parcel;Landroid/os/Parcel;I)Z
    .locals 5
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Landroid/os/RemoteException;
        }
    .end annotation

    .prologue
    const/4 v0, 0x0

    const/4 v1, 0x1

    .line 41
    sparse-switch p1, :sswitch_data_0

    .line 79
    invoke-super {p0, p1, p2, p3, p4}, Landroid/os/Binder;->onTransact(ILandroid/os/Parcel;Landroid/os/Parcel;I)Z

    move-result v0

    :goto_0
    return v0

    .line 45
    :sswitch_0
    const-string v0, "miui.payment.IPaymentManagerResponse"

    invoke-virtual {p3, v0}, Landroid/os/Parcel;->writeString(Ljava/lang/String;)V

    move v0, v1

    .line 46
    goto :goto_0

    .line 50
    :sswitch_1
    const-string v2, "miui.payment.IPaymentManagerResponse"

    invoke-virtual {p2, v2}, Landroid/os/Parcel;->enforceInterface(Ljava/lang/String;)V

    .line 52
    invoke-virtual {p2}, Landroid/os/Parcel;->readInt()I

    move-result v2

    if-eqz v2, :cond_0

    .line 53
    sget-object v0, Landroid/os/Bundle;->CREATOR:Landroid/os/Parcelable$Creator;

    invoke-interface {v0, p2}, Landroid/os/Parcelable$Creator;->createFromParcel(Landroid/os/Parcel;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/os/Bundle;

    .line 58
    :cond_0
    invoke-virtual {p0, v0}, Lmiui/payment/IPaymentManagerResponse$Stub;->onResult(Landroid/os/Bundle;)V

    move v0, v1

    .line 59
    goto :goto_0

    .line 63
    :sswitch_2
    const-string v2, "miui.payment.IPaymentManagerResponse"

    invoke-virtual {p2, v2}, Landroid/os/Parcel;->enforceInterface(Ljava/lang/String;)V

    .line 65
    invoke-virtual {p2}, Landroid/os/Parcel;->readInt()I

    move-result v2

    .line 67
    invoke-virtual {p2}, Landroid/os/Parcel;->readString()Ljava/lang/String;

    move-result-object v3

    .line 69
    invoke-virtual {p2}, Landroid/os/Parcel;->readInt()I

    move-result v4

    if-eqz v4, :cond_1

    .line 70
    sget-object v0, Landroid/os/Bundle;->CREATOR:Landroid/os/Parcelable$Creator;

    invoke-interface {v0, p2}, Landroid/os/Parcelable$Creator;->createFromParcel(Landroid/os/Parcel;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/os/Bundle;

    .line 75
    :cond_1
    invoke-virtual {p0, v2, v3, v0}, Lmiui/payment/IPaymentManagerResponse$Stub;->onError(ILjava/lang/String;Landroid/os/Bundle;)V

    move v0, v1

    .line 76
    goto :goto_0

    .line 41
    nop

    :sswitch_data_0
    .sparse-switch
        0x1 -> :sswitch_1
        0x2 -> :sswitch_2
        0x5f4e5446 -> :sswitch_0
    .end sparse-switch
.end method
