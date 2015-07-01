.class Lmiui/widget/TimePicker$a;
.super Landroid/view/View$BaseSavedState;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/widget/TimePicker;
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
            "Lmiui/widget/TimePicker$a;",
            ">;"
        }
    .end annotation
.end field


# instance fields
.field private final dE:I

.field private final dF:I


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .prologue
    .line 278
    new-instance v0, Lmiui/widget/k;

    invoke-direct {v0}, Lmiui/widget/k;-><init>()V

    sput-object v0, Lmiui/widget/TimePicker$a;->CREATOR:Landroid/os/Parcelable$Creator;

    return-void
.end method

.method private constructor <init>(Landroid/os/Parcel;)V
    .locals 1

    .prologue
    .line 257
    invoke-direct {p0, p1}, Landroid/view/View$BaseSavedState;-><init>(Landroid/os/Parcel;)V

    .line 258
    invoke-virtual {p1}, Landroid/os/Parcel;->readInt()I

    move-result v0

    iput v0, p0, Lmiui/widget/TimePicker$a;->dE:I

    .line 259
    invoke-virtual {p1}, Landroid/os/Parcel;->readInt()I

    move-result v0

    iput v0, p0, Lmiui/widget/TimePicker$a;->dF:I

    .line 260
    return-void
.end method

.method synthetic constructor <init>(Landroid/os/Parcel;Lmiui/widget/u;)V
    .locals 0

    .prologue
    .line 244
    invoke-direct {p0, p1}, Lmiui/widget/TimePicker$a;-><init>(Landroid/os/Parcel;)V

    return-void
.end method

.method private constructor <init>(Landroid/os/Parcelable;II)V
    .locals 0

    .prologue
    .line 251
    invoke-direct {p0, p1}, Landroid/view/View$BaseSavedState;-><init>(Landroid/os/Parcelable;)V

    .line 252
    iput p2, p0, Lmiui/widget/TimePicker$a;->dE:I

    .line 253
    iput p3, p0, Lmiui/widget/TimePicker$a;->dF:I

    .line 254
    return-void
.end method

.method synthetic constructor <init>(Landroid/os/Parcelable;IILmiui/widget/u;)V
    .locals 0

    .prologue
    .line 244
    invoke-direct {p0, p1, p2, p3}, Lmiui/widget/TimePicker$a;-><init>(Landroid/os/Parcelable;II)V

    return-void
.end method


# virtual methods
.method public getHour()I
    .locals 1

    .prologue
    .line 263
    iget v0, p0, Lmiui/widget/TimePicker$a;->dE:I

    return v0
.end method

.method public getMinute()I
    .locals 1

    .prologue
    .line 267
    iget v0, p0, Lmiui/widget/TimePicker$a;->dF:I

    return v0
.end method

.method public writeToParcel(Landroid/os/Parcel;I)V
    .locals 1

    .prologue
    .line 272
    invoke-super {p0, p1, p2}, Landroid/view/View$BaseSavedState;->writeToParcel(Landroid/os/Parcel;I)V

    .line 273
    iget v0, p0, Lmiui/widget/TimePicker$a;->dE:I

    invoke-virtual {p1, v0}, Landroid/os/Parcel;->writeInt(I)V

    .line 274
    iget v0, p0, Lmiui/widget/TimePicker$a;->dF:I

    invoke-virtual {p1, v0}, Landroid/os/Parcel;->writeInt(I)V

    .line 275
    return-void
.end method
