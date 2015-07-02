.class public abstract Lmiui/payment/IPaymentManagerService$Stub;
.super Landroid/os/Binder;
.source "SourceFile"

# interfaces
.implements Lmiui/payment/IPaymentManagerService;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/payment/IPaymentManagerService;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x409
    name = "Stub"
.end annotation

.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lmiui/payment/IPaymentManagerService$Stub$a;
    }
.end annotation


# static fields
.field private static final qA:Ljava/lang/String; = "miui.payment.IPaymentManagerService"

.field static final qB:I = 0x1

.field static final qC:I = 0x2

.field static final qD:I = 0x3

.field static final qE:I = 0x4

.field static final qF:I = 0x5

.field static final qG:I = 0x6


# direct methods
.method public constructor <init>()V
    .locals 1

    .prologue
    .line 17
    invoke-direct {p0}, Landroid/os/Binder;-><init>()V

    .line 18
    const-string v0, "miui.payment.IPaymentManagerService"

    invoke-virtual {p0, p0, v0}, Lmiui/payment/IPaymentManagerService$Stub;->attachInterface(Landroid/os/IInterface;Ljava/lang/String;)V

    .line 19
    return-void
.end method

.method public static asInterface(Landroid/os/IBinder;)Lmiui/payment/IPaymentManagerService;
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
    const-string v0, "miui.payment.IPaymentManagerService"

    invoke-interface {p0, v0}, Landroid/os/IBinder;->queryLocalInterface(Ljava/lang/String;)Landroid/os/IInterface;

    move-result-object v0

    .line 30
    if-eqz v0, :cond_1

    instance-of v1, v0, Lmiui/payment/IPaymentManagerService;

    if-eqz v1, :cond_1

    .line 31
    check-cast v0, Lmiui/payment/IPaymentManagerService;

    goto :goto_0

    .line 33
    :cond_1
    new-instance v0, Lmiui/payment/IPaymentManagerService$Stub$a;

    invoke-direct {v0, p0}, Lmiui/payment/IPaymentManagerService$Stub$a;-><init>(Landroid/os/IBinder;)V

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
    .locals 6
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Landroid/os/RemoteException;
        }
    .end annotation

    .prologue
    const/4 v2, 0x0

    const/4 v3, 0x1

    .line 41
    sparse-switch p1, :sswitch_data_0

    .line 170
    invoke-super {p0, p1, p2, p3, p4}, Landroid/os/Binder;->onTransact(ILandroid/os/Parcel;Landroid/os/Parcel;I)Z

    move-result v0

    :goto_0
    return v0

    .line 45
    :sswitch_0
    const-string v0, "miui.payment.IPaymentManagerService"

    invoke-virtual {p3, v0}, Landroid/os/Parcel;->writeString(Ljava/lang/String;)V

    move v0, v3

    .line 46
    goto :goto_0

    .line 50
    :sswitch_1
    const-string v0, "miui.payment.IPaymentManagerService"

    invoke-virtual {p2, v0}, Landroid/os/Parcel;->enforceInterface(Ljava/lang/String;)V

    .line 52
    invoke-virtual {p2}, Landroid/os/Parcel;->readStrongBinder()Landroid/os/IBinder;

    move-result-object v0

    invoke-static {v0}, Lmiui/payment/IPaymentManagerResponse$Stub;->asInterface(Landroid/os/IBinder;)Lmiui/payment/IPaymentManagerResponse;

    move-result-object v4

    .line 54
    invoke-virtual {p2}, Landroid/os/Parcel;->readInt()I

    move-result v0

    if-eqz v0, :cond_0

    .line 55
    sget-object v0, Landroid/accounts/Account;->CREATOR:Landroid/os/Parcelable$Creator;

    invoke-interface {v0, p2}, Landroid/os/Parcelable$Creator;->createFromParcel(Landroid/os/Parcel;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/accounts/Account;

    move-object v1, v0

    .line 61
    :goto_1
    invoke-virtual {p2}, Landroid/os/Parcel;->readString()Ljava/lang/String;

    move-result-object v5

    .line 63
    invoke-virtual {p2}, Landroid/os/Parcel;->readInt()I

    move-result v0

    if-eqz v0, :cond_1

    .line 64
    sget-object v0, Landroid/os/Bundle;->CREATOR:Landroid/os/Parcelable$Creator;

    invoke-interface {v0, p2}, Landroid/os/Parcelable$Creator;->createFromParcel(Landroid/os/Parcel;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/os/Bundle;

    .line 69
    :goto_2
    invoke-virtual {p0, v4, v1, v5, v0}, Lmiui/payment/IPaymentManagerService$Stub;->payForOrder(Lmiui/payment/IPaymentManagerResponse;Landroid/accounts/Account;Ljava/lang/String;Landroid/os/Bundle;)V

    .line 70
    invoke-virtual {p3}, Landroid/os/Parcel;->writeNoException()V

    move v0, v3

    .line 71
    goto :goto_0

    :cond_0
    move-object v1, v2

    .line 58
    goto :goto_1

    :cond_1
    move-object v0, v2

    .line 67
    goto :goto_2

    .line 75
    :sswitch_2
    const-string v0, "miui.payment.IPaymentManagerService"

    invoke-virtual {p2, v0}, Landroid/os/Parcel;->enforceInterface(Ljava/lang/String;)V

    .line 77
    invoke-virtual {p2}, Landroid/os/Parcel;->readStrongBinder()Landroid/os/IBinder;

    move-result-object v0

    invoke-static {v0}, Lmiui/payment/IPaymentManagerResponse$Stub;->asInterface(Landroid/os/IBinder;)Lmiui/payment/IPaymentManagerResponse;

    move-result-object v1

    .line 79
    invoke-virtual {p2}, Landroid/os/Parcel;->readInt()I

    move-result v0

    if-eqz v0, :cond_2

    .line 80
    sget-object v0, Landroid/accounts/Account;->CREATOR:Landroid/os/Parcelable$Creator;

    invoke-interface {v0, p2}, Landroid/os/Parcelable$Creator;->createFromParcel(Landroid/os/Parcel;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/accounts/Account;

    .line 85
    :goto_3
    invoke-virtual {p0, v1, v0}, Lmiui/payment/IPaymentManagerService$Stub;->showMiliCenter(Lmiui/payment/IPaymentManagerResponse;Landroid/accounts/Account;)V

    .line 86
    invoke-virtual {p3}, Landroid/os/Parcel;->writeNoException()V

    move v0, v3

    .line 87
    goto :goto_0

    :cond_2
    move-object v0, v2

    .line 83
    goto :goto_3

    .line 91
    :sswitch_3
    const-string v0, "miui.payment.IPaymentManagerService"

    invoke-virtual {p2, v0}, Landroid/os/Parcel;->enforceInterface(Ljava/lang/String;)V

    .line 93
    invoke-virtual {p2}, Landroid/os/Parcel;->readStrongBinder()Landroid/os/IBinder;

    move-result-object v0

    invoke-static {v0}, Lmiui/payment/IPaymentManagerResponse$Stub;->asInterface(Landroid/os/IBinder;)Lmiui/payment/IPaymentManagerResponse;

    move-result-object v1

    .line 95
    invoke-virtual {p2}, Landroid/os/Parcel;->readInt()I

    move-result v0

    if-eqz v0, :cond_3

    .line 96
    sget-object v0, Landroid/accounts/Account;->CREATOR:Landroid/os/Parcelable$Creator;

    invoke-interface {v0, p2}, Landroid/os/Parcelable$Creator;->createFromParcel(Landroid/os/Parcel;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/accounts/Account;

    .line 102
    :goto_4
    invoke-virtual {p2}, Landroid/os/Parcel;->readString()Ljava/lang/String;

    move-result-object v2

    .line 104
    invoke-virtual {p2}, Landroid/os/Parcel;->readString()Ljava/lang/String;

    move-result-object v4

    .line 105
    invoke-virtual {p0, v1, v0, v2, v4}, Lmiui/payment/IPaymentManagerService$Stub;->showRechargeRecord(Lmiui/payment/IPaymentManagerResponse;Landroid/accounts/Account;Ljava/lang/String;Ljava/lang/String;)V

    .line 106
    invoke-virtual {p3}, Landroid/os/Parcel;->writeNoException()V

    move v0, v3

    .line 107
    goto/16 :goto_0

    :cond_3
    move-object v0, v2

    .line 99
    goto :goto_4

    .line 111
    :sswitch_4
    const-string v0, "miui.payment.IPaymentManagerService"

    invoke-virtual {p2, v0}, Landroid/os/Parcel;->enforceInterface(Ljava/lang/String;)V

    .line 113
    invoke-virtual {p2}, Landroid/os/Parcel;->readStrongBinder()Landroid/os/IBinder;

    move-result-object v0

    invoke-static {v0}, Lmiui/payment/IPaymentManagerResponse$Stub;->asInterface(Landroid/os/IBinder;)Lmiui/payment/IPaymentManagerResponse;

    move-result-object v1

    .line 115
    invoke-virtual {p2}, Landroid/os/Parcel;->readInt()I

    move-result v0

    if-eqz v0, :cond_4

    .line 116
    sget-object v0, Landroid/accounts/Account;->CREATOR:Landroid/os/Parcelable$Creator;

    invoke-interface {v0, p2}, Landroid/os/Parcelable$Creator;->createFromParcel(Landroid/os/Parcel;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/accounts/Account;

    .line 122
    :goto_5
    invoke-virtual {p2}, Landroid/os/Parcel;->readString()Ljava/lang/String;

    move-result-object v2

    .line 124
    invoke-virtual {p2}, Landroid/os/Parcel;->readString()Ljava/lang/String;

    move-result-object v4

    .line 125
    invoke-virtual {p0, v1, v0, v2, v4}, Lmiui/payment/IPaymentManagerService$Stub;->showPayRecord(Lmiui/payment/IPaymentManagerResponse;Landroid/accounts/Account;Ljava/lang/String;Ljava/lang/String;)V

    .line 126
    invoke-virtual {p3}, Landroid/os/Parcel;->writeNoException()V

    move v0, v3

    .line 127
    goto/16 :goto_0

    :cond_4
    move-object v0, v2

    .line 119
    goto :goto_5

    .line 131
    :sswitch_5
    const-string v0, "miui.payment.IPaymentManagerService"

    invoke-virtual {p2, v0}, Landroid/os/Parcel;->enforceInterface(Ljava/lang/String;)V

    .line 133
    invoke-virtual {p2}, Landroid/os/Parcel;->readStrongBinder()Landroid/os/IBinder;

    move-result-object v0

    invoke-static {v0}, Lmiui/payment/IPaymentManagerResponse$Stub;->asInterface(Landroid/os/IBinder;)Lmiui/payment/IPaymentManagerResponse;

    move-result-object v1

    .line 135
    invoke-virtual {p2}, Landroid/os/Parcel;->readInt()I

    move-result v0

    if-eqz v0, :cond_5

    .line 136
    sget-object v0, Landroid/accounts/Account;->CREATOR:Landroid/os/Parcelable$Creator;

    invoke-interface {v0, p2}, Landroid/os/Parcelable$Creator;->createFromParcel(Landroid/os/Parcel;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/accounts/Account;

    .line 142
    :goto_6
    invoke-virtual {p2}, Landroid/os/Parcel;->readString()Ljava/lang/String;

    move-result-object v2

    .line 144
    invoke-virtual {p2}, Landroid/os/Parcel;->readString()Ljava/lang/String;

    move-result-object v4

    .line 145
    invoke-virtual {p0, v1, v0, v2, v4}, Lmiui/payment/IPaymentManagerService$Stub;->recharge(Lmiui/payment/IPaymentManagerResponse;Landroid/accounts/Account;Ljava/lang/String;Ljava/lang/String;)V

    .line 146
    invoke-virtual {p3}, Landroid/os/Parcel;->writeNoException()V

    move v0, v3

    .line 147
    goto/16 :goto_0

    :cond_5
    move-object v0, v2

    .line 139
    goto :goto_6

    .line 151
    :sswitch_6
    const-string v0, "miui.payment.IPaymentManagerService"

    invoke-virtual {p2, v0}, Landroid/os/Parcel;->enforceInterface(Ljava/lang/String;)V

    .line 153
    invoke-virtual {p2}, Landroid/os/Parcel;->readStrongBinder()Landroid/os/IBinder;

    move-result-object v0

    invoke-static {v0}, Lmiui/payment/IPaymentManagerResponse$Stub;->asInterface(Landroid/os/IBinder;)Lmiui/payment/IPaymentManagerResponse;

    move-result-object v1

    .line 155
    invoke-virtual {p2}, Landroid/os/Parcel;->readInt()I

    move-result v0

    if-eqz v0, :cond_6

    .line 156
    sget-object v0, Landroid/accounts/Account;->CREATOR:Landroid/os/Parcelable$Creator;

    invoke-interface {v0, p2}, Landroid/os/Parcelable$Creator;->createFromParcel(Landroid/os/Parcel;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/accounts/Account;

    .line 162
    :goto_7
    invoke-virtual {p2}, Landroid/os/Parcel;->readString()Ljava/lang/String;

    move-result-object v2

    .line 164
    invoke-virtual {p2}, Landroid/os/Parcel;->readString()Ljava/lang/String;

    move-result-object v4

    .line 165
    invoke-virtual {p0, v1, v0, v2, v4}, Lmiui/payment/IPaymentManagerService$Stub;->getMiliBalance(Lmiui/payment/IPaymentManagerResponse;Landroid/accounts/Account;Ljava/lang/String;Ljava/lang/String;)V

    .line 166
    invoke-virtual {p3}, Landroid/os/Parcel;->writeNoException()V

    move v0, v3

    .line 167
    goto/16 :goto_0

    :cond_6
    move-object v0, v2

    .line 159
    goto :goto_7

    .line 41
    :sswitch_data_0
    .sparse-switch
        0x1 -> :sswitch_1
        0x2 -> :sswitch_2
        0x3 -> :sswitch_3
        0x4 -> :sswitch_4
        0x5 -> :sswitch_5
        0x6 -> :sswitch_6
        0x5f4e5446 -> :sswitch_0
    .end sparse-switch
.end method
