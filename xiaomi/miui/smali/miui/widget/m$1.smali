.class Lmiui/widget/m$1;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lmiui/widget/m;->onReceive(Landroid/content/Context;Landroid/content/Intent;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic Hy:Lmiui/widget/m;


# direct methods
.method constructor <init>(Lmiui/widget/m;)V
    .locals 0

    .prologue
    .line 45
    iput-object p1, p0, Lmiui/widget/m$1;->Hy:Lmiui/widget/m;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .locals 1

    .prologue
    .line 47
    iget-object v0, p0, Lmiui/widget/m$1;->Hy:Lmiui/widget/m;

    iget-object v0, v0, Lmiui/widget/m;->Ol:Lmiui/widget/PictureClock;

    invoke-static {v0}, Lmiui/widget/PictureClock;->b(Lmiui/widget/PictureClock;)V

    .line 48
    return-void
.end method
