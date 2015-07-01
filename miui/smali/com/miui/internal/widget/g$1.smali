.class Lcom/miui/internal/widget/g$1;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/internal/widget/g;->postShowOverflowMenu()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic oh:Lcom/miui/internal/widget/g;


# direct methods
.method constructor <init>(Lcom/miui/internal/widget/g;)V
    .locals 0

    .prologue
    .line 148
    iput-object p1, p0, Lcom/miui/internal/widget/g$1;->oh:Lcom/miui/internal/widget/g;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .locals 1

    .prologue
    .line 150
    iget-object v0, p0, Lcom/miui/internal/widget/g$1;->oh:Lcom/miui/internal/widget/g;

    invoke-virtual {v0}, Lcom/miui/internal/widget/g;->showOverflowMenu()Z

    .line 151
    return-void
.end method
