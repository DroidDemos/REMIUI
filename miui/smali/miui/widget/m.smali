.class Lmiui/widget/m;
.super Landroid/content/BroadcastReceiver;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/widget/PictureClock;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic Ol:Lmiui/widget/PictureClock;


# direct methods
.method constructor <init>(Lmiui/widget/PictureClock;)V
    .locals 0

    .prologue
    .line 36
    iput-object p1, p0, Lmiui/widget/m;->Ol:Lmiui/widget/PictureClock;

    invoke-direct {p0}, Landroid/content/BroadcastReceiver;-><init>()V

    return-void
.end method


# virtual methods
.method public onReceive(Landroid/content/Context;Landroid/content/Intent;)V
    .locals 3

    .prologue
    .line 39
    iget-object v0, p0, Lmiui/widget/m;->Ol:Lmiui/widget/PictureClock;

    invoke-static {v0}, Lmiui/widget/PictureClock;->a(Lmiui/widget/PictureClock;)Z

    move-result v0

    if-eqz v0, :cond_0

    invoke-virtual {p2}, Landroid/content/Intent;->getAction()Ljava/lang/String;

    move-result-object v0

    const-string v1, "android.intent.action.TIMEZONE_CHANGED"

    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 41
    const-string v0, "time-zone"

    invoke-virtual {p2, v0}, Landroid/content/Intent;->getStringExtra(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 42
    iget-object v1, p0, Lmiui/widget/m;->Ol:Lmiui/widget/PictureClock;

    new-instance v2, Lmiui/date/Calendar;

    invoke-static {v0}, Ljava/util/TimeZone;->getTimeZone(Ljava/lang/String;)Ljava/util/TimeZone;

    move-result-object v0

    invoke-direct {v2, v0}, Lmiui/date/Calendar;-><init>(Ljava/util/TimeZone;)V

    invoke-static {v1, v2}, Lmiui/widget/PictureClock;->a(Lmiui/widget/PictureClock;Lmiui/date/Calendar;)Lmiui/date/Calendar;

    .line 45
    :cond_0
    iget-object v0, p0, Lmiui/widget/m;->Ol:Lmiui/widget/PictureClock;

    invoke-static {v0}, Lmiui/widget/PictureClock;->c(Lmiui/widget/PictureClock;)Landroid/os/Handler;

    move-result-object v0

    new-instance v1, Lmiui/widget/m$1;

    invoke-direct {v1, p0}, Lmiui/widget/m$1;-><init>(Lmiui/widget/m;)V

    invoke-virtual {v0, v1}, Landroid/os/Handler;->post(Ljava/lang/Runnable;)Z

    .line 50
    return-void
.end method
