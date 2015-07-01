.class Lcom/miui/internal/view/menu/ActionMenuPresenter$c;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroid/os/Parcelable;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/internal/view/menu/ActionMenuPresenter;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0xa
    name = "c"
.end annotation


# static fields
.field public static final CREATOR:Landroid/os/Parcelable$Creator;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Landroid/os/Parcelable$Creator",
            "<",
            "Lcom/miui/internal/view/menu/ActionMenuPresenter$c;",
            ">;"
        }
    .end annotation
.end field


# instance fields
.field public lK:I


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .prologue
    .line 456
    new-instance v0, Lcom/miui/internal/view/menu/a;

    invoke-direct {v0}, Lcom/miui/internal/view/menu/a;-><init>()V

    sput-object v0, Lcom/miui/internal/view/menu/ActionMenuPresenter$c;->CREATOR:Landroid/os/Parcelable$Creator;

    return-void
.end method

.method constructor <init>()V
    .locals 0

    .prologue
    .line 439
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 440
    return-void
.end method

.method constructor <init>(Landroid/os/Parcel;)V
    .locals 1

    .prologue
    .line 442
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 443
    invoke-virtual {p1}, Landroid/os/Parcel;->readInt()I

    move-result v0

    iput v0, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter$c;->lK:I

    .line 444
    return-void
.end method


# virtual methods
.method public describeContents()I
    .locals 1

    .prologue
    .line 448
    const/4 v0, 0x0

    return v0
.end method

.method public writeToParcel(Landroid/os/Parcel;I)V
    .locals 1

    .prologue
    .line 453
    iget v0, p0, Lcom/miui/internal/view/menu/ActionMenuPresenter$c;->lK:I

    invoke-virtual {p1, v0}, Landroid/os/Parcel;->writeInt(I)V

    .line 454
    return-void
.end method
