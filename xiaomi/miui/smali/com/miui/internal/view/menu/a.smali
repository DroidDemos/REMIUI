.class final Lcom/miui/internal/view/menu/a;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroid/os/Parcelable$Creator;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/internal/view/menu/ActionMenuPresenter$c;
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
        "Lcom/miui/internal/view/menu/ActionMenuPresenter$c;",
        ">;"
    }
.end annotation


# direct methods
.method constructor <init>()V
    .locals 0

    .prologue
    .line 457
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public c(Landroid/os/Parcel;)Lcom/miui/internal/view/menu/ActionMenuPresenter$c;
    .locals 1

    .prologue
    .line 459
    new-instance v0, Lcom/miui/internal/view/menu/ActionMenuPresenter$c;

    invoke-direct {v0, p1}, Lcom/miui/internal/view/menu/ActionMenuPresenter$c;-><init>(Landroid/os/Parcel;)V

    return-object v0
.end method

.method public bridge synthetic createFromParcel(Landroid/os/Parcel;)Ljava/lang/Object;
    .locals 1

    .prologue
    .line 457
    invoke-virtual {p0, p1}, Lcom/miui/internal/view/menu/a;->c(Landroid/os/Parcel;)Lcom/miui/internal/view/menu/ActionMenuPresenter$c;

    move-result-object v0

    return-object v0
.end method

.method public bridge synthetic newArray(I)[Ljava/lang/Object;
    .locals 1

    .prologue
    .line 457
    invoke-virtual {p0, p1}, Lcom/miui/internal/view/menu/a;->t(I)[Lcom/miui/internal/view/menu/ActionMenuPresenter$c;

    move-result-object v0

    return-object v0
.end method

.method public t(I)[Lcom/miui/internal/view/menu/ActionMenuPresenter$c;
    .locals 1

    .prologue
    .line 463
    new-array v0, p1, [Lcom/miui/internal/view/menu/ActionMenuPresenter$c;

    return-object v0
.end method
