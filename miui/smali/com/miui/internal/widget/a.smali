.class final Lcom/miui/internal/widget/a;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroid/os/Parcelable$Creator;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/internal/widget/ActionBarView$b;
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
        "Lcom/miui/internal/widget/ActionBarView$b;",
        ">;"
    }
.end annotation


# direct methods
.method constructor <init>()V
    .locals 0

    .prologue
    .line 1384
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public a(Landroid/os/Parcel;)Lcom/miui/internal/widget/ActionBarView$b;
    .locals 2

    .prologue
    .line 1386
    new-instance v0, Lcom/miui/internal/widget/ActionBarView$b;

    const/4 v1, 0x0

    invoke-direct {v0, p1, v1}, Lcom/miui/internal/widget/ActionBarView$b;-><init>(Landroid/os/Parcel;Lcom/miui/internal/widget/d;)V

    return-object v0
.end method

.method public a(I)[Lcom/miui/internal/widget/ActionBarView$b;
    .locals 1

    .prologue
    .line 1390
    new-array v0, p1, [Lcom/miui/internal/widget/ActionBarView$b;

    return-object v0
.end method

.method public bridge synthetic createFromParcel(Landroid/os/Parcel;)Ljava/lang/Object;
    .locals 1

    .prologue
    .line 1384
    invoke-virtual {p0, p1}, Lcom/miui/internal/widget/a;->a(Landroid/os/Parcel;)Lcom/miui/internal/widget/ActionBarView$b;

    move-result-object v0

    return-object v0
.end method

.method public bridge synthetic newArray(I)[Ljava/lang/Object;
    .locals 1

    .prologue
    .line 1384
    invoke-virtual {p0, p1}, Lcom/miui/internal/widget/a;->a(I)[Lcom/miui/internal/widget/ActionBarView$b;

    move-result-object v0

    return-object v0
.end method
