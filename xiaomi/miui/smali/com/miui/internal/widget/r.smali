.class Lcom/miui/internal/widget/r;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/internal/widget/ViewDragHelper;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic Qx:Lcom/miui/internal/widget/ViewDragHelper;


# direct methods
.method constructor <init>(Lcom/miui/internal/widget/ViewDragHelper;)V
    .locals 0

    .prologue
    .line 329
    iput-object p1, p0, Lcom/miui/internal/widget/r;->Qx:Lcom/miui/internal/widget/ViewDragHelper;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .locals 2

    .prologue
    .line 331
    iget-object v0, p0, Lcom/miui/internal/widget/r;->Qx:Lcom/miui/internal/widget/ViewDragHelper;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Lcom/miui/internal/widget/ViewDragHelper;->V(I)V

    .line 332
    return-void
.end method
