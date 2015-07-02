.class Lmiui/widget/DatePicker$a;
.super Landroid/view/View$BaseSavedState;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/widget/DatePicker;
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
            "Lmiui/widget/DatePicker$a;",
            ">;"
        }
    .end annotation
.end field


# instance fields
.field private final ss:I

.field private final st:I

.field private final su:I

.field private final sv:Z


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .prologue
    .line 773
    new-instance v0, Lmiui/widget/l;

    invoke-direct {v0}, Lmiui/widget/l;-><init>()V

    sput-object v0, Lmiui/widget/DatePicker$a;->CREATOR:Landroid/os/Parcelable$Creator;

    return-void
.end method

.method private constructor <init>(Landroid/os/Parcel;)V
    .locals 2

    .prologue
    const/4 v0, 0x1

    .line 755
    invoke-direct {p0, p1}, Landroid/view/View$BaseSavedState;-><init>(Landroid/os/Parcel;)V

    .line 756
    invoke-virtual {p1}, Landroid/os/Parcel;->readInt()I

    move-result v1

    iput v1, p0, Lmiui/widget/DatePicker$a;->ss:I

    .line 757
    invoke-virtual {p1}, Landroid/os/Parcel;->readInt()I

    move-result v1

    iput v1, p0, Lmiui/widget/DatePicker$a;->st:I

    .line 758
    invoke-virtual {p1}, Landroid/os/Parcel;->readInt()I

    move-result v1

    iput v1, p0, Lmiui/widget/DatePicker$a;->su:I

    .line 759
    invoke-virtual {p1}, Landroid/os/Parcel;->readInt()I

    move-result v1

    if-ne v1, v0, :cond_0

    :goto_0
    iput-boolean v0, p0, Lmiui/widget/DatePicker$a;->sv:Z

    .line 760
    return-void

    .line 759
    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method synthetic constructor <init>(Landroid/os/Parcel;Lmiui/widget/DatePicker$1;)V
    .locals 0

    .prologue
    .line 730
    invoke-direct {p0, p1}, Lmiui/widget/DatePicker$a;-><init>(Landroid/os/Parcel;)V

    return-void
.end method

.method private constructor <init>(Landroid/os/Parcelable;IIIZ)V
    .locals 0

    .prologue
    .line 744
    invoke-direct {p0, p1}, Landroid/view/View$BaseSavedState;-><init>(Landroid/os/Parcelable;)V

    .line 745
    iput p2, p0, Lmiui/widget/DatePicker$a;->ss:I

    .line 746
    iput p3, p0, Lmiui/widget/DatePicker$a;->st:I

    .line 747
    iput p4, p0, Lmiui/widget/DatePicker$a;->su:I

    .line 748
    iput-boolean p5, p0, Lmiui/widget/DatePicker$a;->sv:Z

    .line 749
    return-void
.end method

.method synthetic constructor <init>(Landroid/os/Parcelable;IIIZLmiui/widget/DatePicker$1;)V
    .locals 0

    .prologue
    .line 730
    invoke-direct/range {p0 .. p5}, Lmiui/widget/DatePicker$a;-><init>(Landroid/os/Parcelable;IIIZ)V

    return-void
.end method

.method static synthetic a(Lmiui/widget/DatePicker$a;)I
    .locals 1

    .prologue
    .line 730
    iget v0, p0, Lmiui/widget/DatePicker$a;->ss:I

    return v0
.end method

.method static synthetic b(Lmiui/widget/DatePicker$a;)I
    .locals 1

    .prologue
    .line 730
    iget v0, p0, Lmiui/widget/DatePicker$a;->st:I

    return v0
.end method

.method static synthetic c(Lmiui/widget/DatePicker$a;)I
    .locals 1

    .prologue
    .line 730
    iget v0, p0, Lmiui/widget/DatePicker$a;->su:I

    return v0
.end method

.method static synthetic d(Lmiui/widget/DatePicker$a;)Z
    .locals 1

    .prologue
    .line 730
    iget-boolean v0, p0, Lmiui/widget/DatePicker$a;->sv:Z

    return v0
.end method


# virtual methods
.method public writeToParcel(Landroid/os/Parcel;I)V
    .locals 1

    .prologue
    .line 764
    invoke-super {p0, p1, p2}, Landroid/view/View$BaseSavedState;->writeToParcel(Landroid/os/Parcel;I)V

    .line 765
    iget v0, p0, Lmiui/widget/DatePicker$a;->ss:I

    invoke-virtual {p1, v0}, Landroid/os/Parcel;->writeInt(I)V

    .line 766
    iget v0, p0, Lmiui/widget/DatePicker$a;->st:I

    invoke-virtual {p1, v0}, Landroid/os/Parcel;->writeInt(I)V

    .line 767
    iget v0, p0, Lmiui/widget/DatePicker$a;->su:I

    invoke-virtual {p1, v0}, Landroid/os/Parcel;->writeInt(I)V

    .line 768
    iget-boolean v0, p0, Lmiui/widget/DatePicker$a;->sv:Z

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    :goto_0
    invoke-virtual {p1, v0}, Landroid/os/Parcel;->writeInt(I)V

    .line 769
    return-void

    .line 768
    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method
