.class final Lmiui/widget/i;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroid/os/Parcelable$Creator;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/widget/NavigationLayout$b;
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
        "Lmiui/widget/NavigationLayout$b;",
        ">;"
    }
.end annotation


# direct methods
.method constructor <init>()V
    .locals 0

    .prologue
    .line 975
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public F(I)[Lmiui/widget/NavigationLayout$b;
    .locals 1

    .prologue
    .line 983
    new-array v0, p1, [Lmiui/widget/NavigationLayout$b;

    return-object v0
.end method

.method public bridge synthetic createFromParcel(Landroid/os/Parcel;)Ljava/lang/Object;
    .locals 1

    .prologue
    .line 975
    invoke-virtual {p0, p1}, Lmiui/widget/i;->e(Landroid/os/Parcel;)Lmiui/widget/NavigationLayout$b;

    move-result-object v0

    return-object v0
.end method

.method public e(Landroid/os/Parcel;)Lmiui/widget/NavigationLayout$b;
    .locals 1

    .prologue
    .line 978
    new-instance v0, Lmiui/widget/NavigationLayout$b;

    invoke-direct {v0, p1}, Lmiui/widget/NavigationLayout$b;-><init>(Landroid/os/Parcel;)V

    return-object v0
.end method

.method public bridge synthetic newArray(I)[Ljava/lang/Object;
    .locals 1

    .prologue
    .line 975
    invoke-virtual {p0, p1}, Lmiui/widget/i;->F(I)[Lmiui/widget/NavigationLayout$b;

    move-result-object v0

    return-object v0
.end method
