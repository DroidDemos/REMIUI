.class final Lmiui/preference/a;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroid/os/Parcelable$Creator;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/preference/VolumePreference$a;
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
        "Lmiui/preference/VolumePreference$a;",
        ">;"
    }
.end annotation


# direct methods
.method constructor <init>()V
    .locals 0

    .prologue
    .line 234
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public af(I)[Lmiui/preference/VolumePreference$a;
    .locals 1

    .prologue
    .line 240
    new-array v0, p1, [Lmiui/preference/VolumePreference$a;

    return-object v0
.end method

.method public bridge synthetic createFromParcel(Landroid/os/Parcel;)Ljava/lang/Object;
    .locals 1

    .prologue
    .line 234
    invoke-virtual {p0, p1}, Lmiui/preference/a;->i(Landroid/os/Parcel;)Lmiui/preference/VolumePreference$a;

    move-result-object v0

    return-object v0
.end method

.method public i(Landroid/os/Parcel;)Lmiui/preference/VolumePreference$a;
    .locals 1

    .prologue
    .line 236
    new-instance v0, Lmiui/preference/VolumePreference$a;

    invoke-direct {v0, p1}, Lmiui/preference/VolumePreference$a;-><init>(Landroid/os/Parcel;)V

    return-object v0
.end method

.method public bridge synthetic newArray(I)[Ljava/lang/Object;
    .locals 1

    .prologue
    .line 234
    invoke-virtual {p0, p1}, Lmiui/preference/a;->af(I)[Lmiui/preference/VolumePreference$a;

    move-result-object v0

    return-object v0
.end method
