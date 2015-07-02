.class Lmiui/widget/n;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/widget/ScreenView;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic bW:Lmiui/widget/ScreenView;


# direct methods
.method constructor <init>(Lmiui/widget/ScreenView;)V
    .locals 0

    .prologue
    .line 237
    iput-object p1, p0, Lmiui/widget/n;->bW:Lmiui/widget/ScreenView;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .locals 1

    .prologue
    .line 239
    iget-object v0, p0, Lmiui/widget/n;->bW:Lmiui/widget/ScreenView;

    invoke-static {v0}, Lmiui/widget/ScreenView;->a(Lmiui/widget/ScreenView;)V

    .line 240
    return-void
.end method
