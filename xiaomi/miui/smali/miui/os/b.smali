.class final Lmiui/os/b;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroid/os/Parcelable$Creator;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/os/DropBoxManager$Entry;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x8
    name = null
.end annotation


# direct methods
.method constructor <init>()V
    .locals 0

    .prologue
    .line 236
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public ah(I)[Lmiui/os/DropBoxManager$Entry;
    .locals 1

    .prologue
    .line 237
    new-array v0, p1, [Lmiui/os/DropBoxManager$Entry;

    return-object v0
.end method

.method public bridge synthetic createFromParcel(Landroid/os/Parcel;)Ljava/lang/Object;
    .locals 1

    .prologue
    .line 236
    invoke-virtual {p0, p1}, Lmiui/os/b;->j(Landroid/os/Parcel;)Lmiui/os/DropBoxManager$Entry;

    move-result-object v0

    return-object v0
.end method

.method public j(Landroid/os/Parcel;)Lmiui/os/DropBoxManager$Entry;
    .locals 6

    .prologue
    .line 239
    invoke-virtual {p1}, Landroid/os/Parcel;->readString()Ljava/lang/String;

    move-result-object v1

    .line 240
    invoke-virtual {p1}, Landroid/os/Parcel;->readLong()J

    move-result-wide v2

    .line 241
    invoke-virtual {p1}, Landroid/os/Parcel;->readInt()I

    move-result v5

    .line 242
    and-int/lit8 v0, v5, 0x8

    if-eqz v0, :cond_0

    .line 243
    new-instance v0, Lmiui/os/DropBoxManager$Entry;

    invoke-virtual {p1}, Landroid/os/Parcel;->createByteArray()[B

    move-result-object v4

    and-int/lit8 v5, v5, -0x9

    invoke-direct/range {v0 .. v5}, Lmiui/os/DropBoxManager$Entry;-><init>(Ljava/lang/String;J[BI)V

    .line 245
    :goto_0
    return-object v0

    :cond_0
    new-instance v0, Lmiui/os/DropBoxManager$Entry;

    invoke-virtual {p1}, Landroid/os/Parcel;->readFileDescriptor()Landroid/os/ParcelFileDescriptor;

    move-result-object v4

    invoke-direct/range {v0 .. v5}, Lmiui/os/DropBoxManager$Entry;-><init>(Ljava/lang/String;JLandroid/os/ParcelFileDescriptor;I)V

    goto :goto_0
.end method

.method public bridge synthetic newArray(I)[Ljava/lang/Object;
    .locals 1

    .prologue
    .line 236
    invoke-virtual {p0, p1}, Lmiui/os/b;->ah(I)[Lmiui/os/DropBoxManager$Entry;

    move-result-object v0

    return-object v0
.end method
