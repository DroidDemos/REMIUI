.class Lcom/miui/internal/widget/ActionBarView$b;
.super Landroid/view/View$BaseSavedState;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/internal/widget/ActionBarView;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x8
    name = "b"
.end annotation


# static fields
.field public static final CREATOR:Landroid/os/Parcelable$Creator;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Landroid/os/Parcelable$Creator",
            "<",
            "Lcom/miui/internal/widget/ActionBarView$b;",
            ">;"
        }
    .end annotation
.end field


# instance fields
.field QH:I

.field QI:Z


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .prologue
    .line 1383
    new-instance v0, Lcom/miui/internal/widget/a;

    invoke-direct {v0}, Lcom/miui/internal/widget/a;-><init>()V

    sput-object v0, Lcom/miui/internal/widget/ActionBarView$b;->CREATOR:Landroid/os/Parcelable$Creator;

    return-void
.end method

.method private constructor <init>(Landroid/os/Parcel;)V
    .locals 1

    .prologue
    .line 1371
    invoke-direct {p0, p1}, Landroid/view/View$BaseSavedState;-><init>(Landroid/os/Parcel;)V

    .line 1372
    invoke-virtual {p1}, Landroid/os/Parcel;->readInt()I

    move-result v0

    iput v0, p0, Lcom/miui/internal/widget/ActionBarView$b;->QH:I

    .line 1373
    invoke-virtual {p1}, Landroid/os/Parcel;->readInt()I

    move-result v0

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    :goto_0
    iput-boolean v0, p0, Lcom/miui/internal/widget/ActionBarView$b;->QI:Z

    .line 1374
    return-void

    .line 1373
    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method synthetic constructor <init>(Landroid/os/Parcel;Lcom/miui/internal/widget/d;)V
    .locals 0

    .prologue
    .line 1361
    invoke-direct {p0, p1}, Lcom/miui/internal/widget/ActionBarView$b;-><init>(Landroid/os/Parcel;)V

    return-void
.end method

.method constructor <init>(Landroid/os/Parcelable;)V
    .locals 0

    .prologue
    .line 1367
    invoke-direct {p0, p1}, Landroid/view/View$BaseSavedState;-><init>(Landroid/os/Parcelable;)V

    .line 1368
    return-void
.end method


# virtual methods
.method public writeToParcel(Landroid/os/Parcel;I)V
    .locals 1

    .prologue
    .line 1378
    invoke-super {p0, p1, p2}, Landroid/view/View$BaseSavedState;->writeToParcel(Landroid/os/Parcel;I)V

    .line 1379
    iget v0, p0, Lcom/miui/internal/widget/ActionBarView$b;->QH:I

    invoke-virtual {p1, v0}, Landroid/os/Parcel;->writeInt(I)V

    .line 1380
    iget-boolean v0, p0, Lcom/miui/internal/widget/ActionBarView$b;->QI:Z

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    :goto_0
    invoke-virtual {p1, v0}, Landroid/os/Parcel;->writeInt(I)V

    .line 1381
    return-void

    .line 1380
    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method
