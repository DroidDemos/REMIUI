.class final Lcom/miui/internal/widget/t;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroid/os/Parcelable$Creator;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/internal/widget/ActionBarContextView$a;
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
        "Lcom/miui/internal/widget/ActionBarContextView$a;",
        ">;"
    }
.end annotation


# direct methods
.method constructor <init>()V
    .locals 0

    .prologue
    .line 583
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public av(I)[Lcom/miui/internal/widget/ActionBarContextView$a;
    .locals 1

    .prologue
    .line 589
    new-array v0, p1, [Lcom/miui/internal/widget/ActionBarContextView$a;

    return-object v0
.end method

.method public bridge synthetic createFromParcel(Landroid/os/Parcel;)Ljava/lang/Object;
    .locals 1

    .prologue
    .line 583
    invoke-virtual {p0, p1}, Lcom/miui/internal/widget/t;->l(Landroid/os/Parcel;)Lcom/miui/internal/widget/ActionBarContextView$a;

    move-result-object v0

    return-object v0
.end method

.method public l(Landroid/os/Parcel;)Lcom/miui/internal/widget/ActionBarContextView$a;
    .locals 1

    .prologue
    .line 585
    new-instance v0, Lcom/miui/internal/widget/ActionBarContextView$a;

    invoke-direct {v0, p1}, Lcom/miui/internal/widget/ActionBarContextView$a;-><init>(Landroid/os/Parcel;)V

    return-object v0
.end method

.method public bridge synthetic newArray(I)[Ljava/lang/Object;
    .locals 1

    .prologue
    .line 583
    invoke-virtual {p0, p1}, Lcom/miui/internal/widget/t;->av(I)[Lcom/miui/internal/widget/ActionBarContextView$a;

    move-result-object v0

    return-object v0
.end method
