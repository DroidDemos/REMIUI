.class Lmiui/preference/VolumePreference$a;
.super Landroid/preference/Preference$BaseSavedState;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/preference/VolumePreference;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0xa
    name = "a"
.end annotation


# static fields
.field public static final CREATOR:Landroid/os/Parcelable$Creator;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Landroid/os/Parcelable$Creator",
            "<",
            "Lmiui/preference/VolumePreference$a;",
            ">;"
        }
    .end annotation
.end field


# instance fields
.field Gd:Lmiui/preference/VolumePreference$VolumeStore;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .prologue
    .line 233
    new-instance v0, Lmiui/preference/a;

    invoke-direct {v0}, Lmiui/preference/a;-><init>()V

    sput-object v0, Lmiui/preference/VolumePreference$a;->CREATOR:Landroid/os/Parcelable$Creator;

    return-void
.end method

.method public constructor <init>(Landroid/os/Parcel;)V
    .locals 2

    .prologue
    .line 213
    invoke-direct {p0, p1}, Landroid/preference/Preference$BaseSavedState;-><init>(Landroid/os/Parcel;)V

    .line 210
    new-instance v0, Lmiui/preference/VolumePreference$VolumeStore;

    invoke-direct {v0}, Lmiui/preference/VolumePreference$VolumeStore;-><init>()V

    iput-object v0, p0, Lmiui/preference/VolumePreference$a;->Gd:Lmiui/preference/VolumePreference$VolumeStore;

    .line 214
    iget-object v0, p0, Lmiui/preference/VolumePreference$a;->Gd:Lmiui/preference/VolumePreference$VolumeStore;

    invoke-virtual {p1}, Landroid/os/Parcel;->readInt()I

    move-result v1

    iput v1, v0, Lmiui/preference/VolumePreference$VolumeStore;->volume:I

    .line 215
    iget-object v0, p0, Lmiui/preference/VolumePreference$a;->Gd:Lmiui/preference/VolumePreference$VolumeStore;

    invoke-virtual {p1}, Landroid/os/Parcel;->readInt()I

    move-result v1

    iput v1, v0, Lmiui/preference/VolumePreference$VolumeStore;->originalVolume:I

    .line 216
    return-void
.end method

.method public constructor <init>(Landroid/os/Parcelable;)V
    .locals 1

    .prologue
    .line 230
    invoke-direct {p0, p1}, Landroid/preference/Preference$BaseSavedState;-><init>(Landroid/os/Parcelable;)V

    .line 210
    new-instance v0, Lmiui/preference/VolumePreference$VolumeStore;

    invoke-direct {v0}, Lmiui/preference/VolumePreference$VolumeStore;-><init>()V

    iput-object v0, p0, Lmiui/preference/VolumePreference$a;->Gd:Lmiui/preference/VolumePreference$VolumeStore;

    .line 231
    return-void
.end method


# virtual methods
.method ds()Lmiui/preference/VolumePreference$VolumeStore;
    .locals 1

    .prologue
    .line 226
    iget-object v0, p0, Lmiui/preference/VolumePreference$a;->Gd:Lmiui/preference/VolumePreference$VolumeStore;

    return-object v0
.end method

.method public writeToParcel(Landroid/os/Parcel;I)V
    .locals 1

    .prologue
    .line 220
    invoke-super {p0, p1, p2}, Landroid/preference/Preference$BaseSavedState;->writeToParcel(Landroid/os/Parcel;I)V

    .line 221
    iget-object v0, p0, Lmiui/preference/VolumePreference$a;->Gd:Lmiui/preference/VolumePreference$VolumeStore;

    iget v0, v0, Lmiui/preference/VolumePreference$VolumeStore;->volume:I

    invoke-virtual {p1, v0}, Landroid/os/Parcel;->writeInt(I)V

    .line 222
    iget-object v0, p0, Lmiui/preference/VolumePreference$a;->Gd:Lmiui/preference/VolumePreference$VolumeStore;

    iget v0, v0, Lmiui/preference/VolumePreference$VolumeStore;->originalVolume:I

    invoke-virtual {p1, v0}, Landroid/os/Parcel;->writeInt(I)V

    .line 223
    return-void
.end method
